package f1;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.CellIdentity;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityNr;
import android.telephony.CellIdentityTdscdma;
import android.telephony.CellIdentityWcdma;
import android.telephony.NetworkRegistrationInfo;
import android.telephony.ServiceState;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.euicc.EuiccManager;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public class a implements p.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile Executor f2548a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f2549b = i("persist.vendor.radio.5g_mode_pref", "0");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f2550c = i("persist.radio.vivo.fiveg", "false");

    public static String[] h(Context context) {
        String string;
        String string2 = "";
        if (y.a.a(context, "android.permission.BLUETOOTH_CONNECT") != 0) {
            g1.h.a("DeviceInfoReaderBase", "BLUETOOTH_CONNECT权限未授予！");
            return new String[]{"", ""};
        }
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && defaultAdapter.isEnabled()) {
            Set<BluetoothDevice> bondedDevices = defaultAdapter.getBondedDevices();
            if (bondedDevices.size() > 0) {
                StringBuilder sb = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                for (BluetoothDevice bluetoothDevice : bondedDevices) {
                    String name = bluetoothDevice.getName();
                    String address = bluetoothDevice.getAddress();
                    if (name != null && !name.isEmpty()) {
                        if (sb.length() > 0) {
                            sb.append(", ");
                        }
                        sb.append(name);
                    }
                    if (address != null && !address.isEmpty()) {
                        if (sb2.length() > 0) {
                            sb2.append(", ");
                        }
                        sb2.append(address);
                    }
                }
                string2 = sb.toString();
                string = sb2.toString();
            } else {
                string = "";
            }
            return new String[]{string2, string};
        }
        return new String[]{"", ""};
    }

    public static String i(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Object objInvoke = cls.getMethod("get", String.class, String.class).invoke(cls, str, str2);
            return (objInvoke == null || !(objInvoke instanceof String)) ? str2 : (String) objInvoke;
        } catch (Exception e2) {
            Log.e("DeviceInfoReaderBase", "getString failed", e2);
            return str2;
        }
    }

    public static int j(int i2) {
        String strO = g1.l.o("ro.product.manufacturer");
        if (strO == null || strO.isEmpty()) {
            return -1;
        }
        if (!strO.equalsIgnoreCase("Xiaomi") && !strO.equalsIgnoreCase("SamSung") && !strO.equalsIgnoreCase("WIKO") && !strO.equalsIgnoreCase("ZTE") && !strO.equalsIgnoreCase("Tianyi") && !strO.equalsIgnoreCase("motorola")) {
            int[] iArr = (int[]) g1.k.c(g1.k.b(g1.k.a("android.telephony.SubscriptionManager"), "getSubId", Integer.TYPE), null, Integer.valueOf(i2));
            if (iArr != null) {
                return iArr[0];
            }
            return -1;
        }
        Class<?> clsA = g1.k.a("android.telephony.SubscriptionManager");
        if (Build.VERSION.SDK_INT > 33) {
            return ((Integer) g1.k.c(g1.k.b(clsA, "getSubscriptionId", Integer.TYPE), null, Integer.valueOf(i2))).intValue();
        }
        int[] iArr2 = (int[]) g1.k.c(g1.k.b(clsA, "getSubId", Integer.TYPE), null, Integer.valueOf(i2));
        if (iArr2 != null) {
            return iArr2[0];
        }
        return -1;
    }

    private static Executor k() {
        if (f2548a == null) {
            synchronized (a.class) {
                if (f2548a == null) {
                    f2548a = Executors.newSingleThreadExecutor();
                }
            }
        }
        return f2548a;
    }

    private String l(Context context) {
        String string = Settings.Global.getString(context.getContentResolver(), "miui_version_name");
        String strO = g1.l.o("ro.build.version.incremental");
        if (!TextUtils.isEmpty(string) && string.length() > 1) {
            return string.replaceAll("\r|\n", "");
        }
        if (strO == null || strO.length() == 0) {
            return strO;
        }
        int iLastIndexOf = strO.lastIndexOf(".");
        String strSubstring = strO.substring(1, iLastIndexOf);
        return "MIUI " + strSubstring.substring(0, strSubstring.indexOf(".", strSubstring.indexOf(".", strSubstring.indexOf(".") + 1) + 1)) + strSubstring + "(" + strO.substring(iLastIndexOf + 1) + ")";
    }

    private boolean m(Context context, int i2) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                return false;
            }
            return (telephonyManager.getSimState(i2) == 5) && (i2 != -1);
        } catch (Exception e2) {
            Log.e("EsimStatus", "检查SIM卡存在状态失败: " + e2.getMessage());
            return false;
        }
    }

    private boolean o(String str, String str2) {
        return (str == null || str.isEmpty() || str2 == null || str2.isEmpty() || !str.matches("\\d+") || !str2.matches("\\d+")) ? false : true;
    }

    public static synchronized void p(Context context) {
        String str;
        String str2;
        g1.h.a("DeviceInfoReaderBase", "listenNetworkState---->");
        if (g1.d.f2649e) {
            try {
                try {
                    int iJ = j(0);
                    g1.h.a("DeviceInfoReaderBase", "subIdSlot1:" + iJ);
                    if (iJ > 0 && g1.d.f2650f == null) {
                        g1.d.f2650f = ((TelephonyManager) context.getSystemService("phone")).createForSubscriptionId(iJ);
                        g1.h.a("DeviceInfoReaderBase", "telephonyManagerSlot1" + g1.d.f2650f);
                        if (g1.d.f2652h == null) {
                            g1.h.a("DeviceInfoReaderBase", "registerTelephonyCallback--mDMTelephonyCallback1");
                            g1.d.f2652h = new e1.a(context.getApplicationContext(), 0);
                            g1.d.f2650f.registerTelephonyCallback(k(), g1.d.f2652h);
                        }
                    }
                    int iJ2 = j(1);
                    g1.h.a("DeviceInfoReaderBase", "subIdSlot2:" + iJ2);
                    if (iJ2 > 0 && g1.d.f2651g == null) {
                        g1.d.f2651g = ((TelephonyManager) context.getSystemService("phone")).createForSubscriptionId(iJ2);
                        g1.h.a("DeviceInfoReaderBase", "telephonyManagerSlot2" + g1.d.f2651g);
                        if (g1.d.f2653i == null) {
                            g1.h.a("DeviceInfoReaderBase", "registerTelephonyCallback--mDMTelephonyCallback2");
                            g1.d.f2653i = new e1.a(context.getApplicationContext(), 1);
                            g1.d.f2651g.registerTelephonyCallback(k(), g1.d.f2653i);
                        }
                    }
                } catch (RuntimeException e2) {
                    str = "DeviceInfoReaderBase";
                    str2 = "listenNetworkState telephony服务暂不可用，跳过本次注册: " + e2.getMessage();
                    g1.h.b(str, str2);
                }
            } catch (Exception e3) {
                str = "DeviceInfoReaderBase";
                str2 = "listenNetworkState 异常: " + e3.getMessage();
                g1.h.b(str, str2);
            }
        }
    }

    private String s(SubscriptionInfo subscriptionInfo) {
        try {
            return subscriptionInfo.getMccString() != null ? subscriptionInfo.getMccString() : "";
        } catch (Exception unused) {
            return "";
        }
    }

    private String t(SubscriptionInfo subscriptionInfo) {
        try {
            return subscriptionInfo.getMncString() != null ? subscriptionInfo.getMncString() : "";
        } catch (Exception unused) {
            return "";
        }
    }

    public static synchronized void u(Context context) {
        TelephonyManager telephonyManager;
        TelephonyManager telephonyManager2;
        g1.h.a("DeviceInfoReaderBase", "unlistenNetworkState-->");
        try {
            if (g1.d.f2652h == null || (telephonyManager2 = g1.d.f2650f) == null) {
                g1.d.f2650f = null;
                g1.d.f2652h = null;
                try {
                    if (g1.d.f2653i != null && (telephonyManager = g1.d.f2651g) != null) {
                        telephonyManager.unregisterTelephonyCallback(g1.d.f2653i);
                    }
                } catch (Exception e2) {
                    g1.h.b("DeviceInfoReaderBase", "unregister slot2 callback 异常: " + e2.getMessage());
                }
                g1.d.f2651g = null;
                g1.d.f2653i = null;
            } else {
                telephonyManager2.unregisterTelephonyCallback(g1.d.f2652h);
                g1.d.f2650f = null;
                g1.d.f2652h = null;
                if (g1.d.f2653i != null) {
                    telephonyManager.unregisterTelephonyCallback(g1.d.f2653i);
                }
                g1.d.f2651g = null;
                g1.d.f2653i = null;
            }
        } catch (Exception e3) {
            g1.h.b("DeviceInfoReaderBase", "unregister slot1 callback 异常: " + e3.getMessage());
        }
        throw th;
    }

    @Override // f1.p.c
    public boolean a(Context context) {
        return new ServiceState().getRoaming();
    }

    @Override // f1.p.c
    public void b(Context context, e1.b bVar) {
        StringBuilder sb;
        String str;
        String str2 = Build.MODEL;
        bVar.f2445b = str2;
        String str3 = p.f2553a.get(str2);
        bVar.f2443a = str3;
        if (str3 == null || str3.isEmpty()) {
            bVar.f2443a = Build.MANUFACTURER;
        }
        if (bVar.f2443a.equals("vivo")) {
            boolean zA = g1.l.a(context);
            g1.d.f2649e = zA;
            if (!zA) {
                sb = new StringBuilder();
            }
            sb.append("vivo DM isUserPermissionOpen : ");
            sb.append(g1.d.f2649e);
            g1.h.a("DeviceInfoReaderBase", sb.toString());
            return;
        }
        g1.d.f2649e = c(context);
        g1.h.a("DeviceInfoReaderBase", "queryAppUsageStatsDescend  isUserPermissionOpen :" + g1.d.f2649e);
        g1.h.a("DeviceInfoReaderBase", "GlobalSetting.isUserPermissionOpen" + g1.d.f2649e);
        if (!g1.d.f2649e) {
            bVar.f2445b = str2;
            String str4 = p.f2553a.get(str2);
            bVar.f2443a = str4;
            if (str4 == null || str4.isEmpty()) {
                bVar.f2443a = Build.MANUFACTURER;
            }
            bVar.f2450e = "Android";
            bVar.f2451f = Build.VERSION.RELEASE;
            bVar.f2449d = Build.DISPLAY;
            bVar.f2447c = g1.l.q("ro.hardware.version", "Unknown");
            bVar.U = g1.g.c(context);
            return;
        }
        bVar.f2450e = "Android";
        bVar.f2451f = Build.VERSION.RELEASE;
        bVar.f2449d = bVar.f2443a.equals("Xiaomi") ? l(context) : Build.DISPLAY;
        bVar.f2447c = g1.l.q("ro.hardware.version", "Unknown");
        bVar.U = g1.g.c(context);
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager != null) {
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo == null || connectionInfo.getNetworkId() == -1) {
                    bVar.H = "NWC";
                    bVar.I = "NWC";
                    bVar.J = "NWC";
                    bVar.K = "NWC";
                    str = "没有连接wifi！";
                } else {
                    bVar.H = connectionInfo.getBSSID();
                    bVar.I = String.valueOf(connectionInfo.getLinkSpeed());
                    bVar.J = String.valueOf(connectionInfo.getRssi());
                    bVar.K = String.valueOf(connectionInfo.getFrequency());
                    str = "wifiManager :路由器的mac地址： " + connectionInfo.getBSSID();
                }
                g1.h.a("DeviceInfoReaderBase", str);
            } else {
                g1.h.a("DeviceInfoReaderBase", "设备不支持wifi");
                bVar.H = "NW";
            }
        } catch (Exception e2) {
            g1.h.a("DeviceInfoReaderBase", "Exception " + e2);
        }
        String[] strArrH = h(g1.d.f2648d);
        bVar.N = strArrH[0];
        bVar.O = strArrH[1];
        if (!bVar.f2443a.equals("vivo")) {
            q(context, bVar);
            return;
        }
        boolean zA2 = g1.l.a(g1.d.f2648d);
        g1.d.f2649e = zA2;
        if (!zA2) {
            sb = new StringBuilder();
            sb.append("vivo DM isUserPermissionOpen : ");
            sb.append(g1.d.f2649e);
            g1.h.a("DeviceInfoReaderBase", sb.toString());
            return;
        }
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 33) {
            g1.h.a("DeviceInfoReaderBase", "enter DeviceInfoReaderBase super.read read vivo");
            r(context, bVar);
        } else {
            g1.h.b("DeviceInfoReaderBase", "Unsupported API:" + i2);
        }
    }

    protected synchronized boolean c(Context context) {
        Boolean boolValueOf;
        boolValueOf = Boolean.valueOf(Settings.Global.getInt(context.getContentResolver(), "device_provisioned", 0) == 1);
        g1.h.a("DeviceInfoReaderBase", "isBootWizardCompleted: " + boolValueOf);
        return boolValueOf.booleanValue();
    }

    public String d(Context context, int i2, int i3) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            Log.w("EsimStatus", "Telephony服务不可用");
            return "00";
        }
        if (telephonyManager.getPhoneCount() >= 2) {
            return e(context, i2, i3);
        }
        Log.d("EsimStatus", "设备只支持单SIM卡");
        boolean z2 = m(context, i2) && n(context, i2);
        StringBuilder sb = new StringBuilder();
        sb.append(z2 ? "1" : "0");
        sb.append("0");
        return sb.toString();
    }

    public String e(Context context, int i2, int i3) {
        boolean zN = n(context, i2);
        boolean zN2 = n(context, i3);
        StringBuilder sb = new StringBuilder();
        sb.append(zN ? "1" : "0");
        sb.append(zN2 ? "1" : "0");
        return sb.toString();
    }

    public String f(Context context) {
        SubscriptionManager subscriptionManager;
        List<SubscriptionInfo> allSubscriptionInfoList;
        List<SubscriptionInfo> activeSubscriptionInfoList;
        if (y.a.a(context, "android.permission.READ_PHONE_STATE") != 0 || (subscriptionManager = (SubscriptionManager) context.getSystemService("telephony_subscription_service")) == null) {
            return "";
        }
        ArrayList arrayList = new ArrayList();
        try {
            if (Build.VERSION.SDK_INT >= 34) {
                allSubscriptionInfoList = subscriptionManager.getAllSubscriptionInfoList();
                activeSubscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();
            } else {
                allSubscriptionInfoList = null;
                activeSubscriptionInfoList = null;
            }
            if (allSubscriptionInfoList == null) {
                return "";
            }
            ArrayList<SubscriptionInfo> arrayList2 = new ArrayList();
            if (!allSubscriptionInfoList.isEmpty()) {
                if (activeSubscriptionInfoList == null || activeSubscriptionInfoList.isEmpty()) {
                    arrayList2.addAll(allSubscriptionInfoList);
                } else {
                    HashSet hashSet = new HashSet();
                    Iterator<SubscriptionInfo> it = activeSubscriptionInfoList.iterator();
                    while (it.hasNext()) {
                        hashSet.add(Integer.valueOf(it.next().getSubscriptionId()));
                    }
                    for (SubscriptionInfo subscriptionInfo : allSubscriptionInfoList) {
                        if (!hashSet.contains(Integer.valueOf(subscriptionInfo.getSubscriptionId()))) {
                            arrayList2.add(subscriptionInfo);
                        }
                    }
                }
            }
            for (SubscriptionInfo subscriptionInfo2 : arrayList2) {
                try {
                    if (subscriptionInfo2.isEmbedded()) {
                        String strS = s(subscriptionInfo2);
                        String strT = t(subscriptionInfo2);
                        if (o(strS, strT)) {
                            arrayList.add(strS + strT);
                        }
                    }
                } catch (Exception unused) {
                }
            }
        } catch (Exception e2) {
            g1.h.a("DeviceInfoReaderBase", "exception" + e2);
        }
        return TextUtils.join(",", arrayList);
    }

    protected synchronized void g(Context context, int i2, e1.b bVar) {
        if (y.a.a(context, "android.permission.ACCESS_COARSE_LOCATION") == 0 && y.a.a(context, "android.permission.READ_PHONE_STATE") == 0) {
            String strValueOf = "";
            String strValueOf2 = "";
            String strValueOf3 = "";
            String str = "";
            String str2 = "1";
            TelephonyManager telephonyManagerCreateForSubscriptionId = ((TelephonyManager) context.getSystemService("phone")).createForSubscriptionId(j(i2));
            String simSerialNumber = telephonyManagerCreateForSubscriptionId.getSimSerialNumber();
            g1.h.a("DeviceInfoReaderBase", "iccid:" + simSerialNumber);
            if (simSerialNumber == null || simSerialNumber.isEmpty()) {
                simSerialNumber = "ex01";
            }
            String subscriberId = telephonyManagerCreateForSubscriptionId.getSubscriberId();
            if (subscriberId == null || subscriberId.isEmpty()) {
                subscriberId = "ex01";
            }
            String networkOperator = telephonyManagerCreateForSubscriptionId.getNetworkOperator();
            g1.h.a("DeviceInfoReaderBase", "MNCCH register network:" + networkOperator);
            String simOperator = telephonyManagerCreateForSubscriptionId.getSimOperator();
            g1.h.a("DeviceInfoReaderBase", "tm.getSimOperator mnc :" + simOperator);
            if (simOperator == null || simOperator.isEmpty()) {
                simOperator = "ex01";
            }
            ServiceState serviceState = telephonyManagerCreateForSubscriptionId.getServiceState();
            if (serviceState == null) {
                g1.h.a("DeviceInfoReaderBase", "setCellLoc failed!");
                return;
            }
            for (NetworkRegistrationInfo networkRegistrationInfo : serviceState.getNetworkRegistrationInfoList()) {
                if (networkRegistrationInfo.getDomain() == 2 && networkRegistrationInfo.getTransportType() == 1) {
                    String string = networkRegistrationInfo.toString();
                    CellIdentity cellIdentity = networkRegistrationInfo.getCellIdentity();
                    if (cellIdentity == null) {
                        g1.h.b("DeviceInfoReaderBase", "cellIdentity==null!");
                        break;
                    }
                    if (cellIdentity instanceof CellIdentityGsm) {
                        CellIdentityGsm cellIdentityGsm = (CellIdentityGsm) cellIdentity;
                        strValueOf = String.valueOf(cellIdentityGsm.getCid());
                        strValueOf2 = String.valueOf(cellIdentityGsm.getLac());
                        str = "GSM";
                        break;
                    }
                    if (cellIdentity instanceof CellIdentityWcdma) {
                        CellIdentityWcdma cellIdentityWcdma = (CellIdentityWcdma) cellIdentity;
                        strValueOf = String.valueOf(cellIdentityWcdma.getCid());
                        strValueOf2 = String.valueOf(cellIdentityWcdma.getLac());
                        str = "WCDMA";
                        break;
                    }
                    if (cellIdentity instanceof CellIdentityTdscdma) {
                        CellIdentityTdscdma cellIdentityTdscdma = (CellIdentityTdscdma) cellIdentity;
                        strValueOf = String.valueOf(cellIdentityTdscdma.getCid());
                        strValueOf2 = String.valueOf(cellIdentityTdscdma.getLac());
                        str = "TD-SCDMA";
                        break;
                    }
                    if (cellIdentity instanceof CellIdentityCdma) {
                        CellIdentityCdma cellIdentityCdma = (CellIdentityCdma) cellIdentity;
                        strValueOf = String.valueOf(cellIdentityCdma.getBasestationId());
                        int longitude = cellIdentityCdma.getLongitude();
                        int latitude = cellIdentityCdma.getLatitude();
                        if (longitude != Integer.MAX_VALUE && latitude != Integer.MAX_VALUE) {
                            strValueOf2 = String.format("%d:%d", Integer.valueOf(longitude), Integer.valueOf(latitude));
                        }
                        str = "CDMA";
                        break;
                    }
                    if (cellIdentity instanceof CellIdentityLte) {
                        CellIdentityLte cellIdentityLte = (CellIdentityLte) cellIdentity;
                        strValueOf = String.valueOf(cellIdentityLte.getCi());
                        strValueOf2 = String.valueOf(cellIdentityLte.getTac());
                        strValueOf3 = String.valueOf(cellIdentityLte.getPci());
                        str = string.contains("isEnDcAvailable = true") ? "NSA" : "LTE";
                        str2 = "1";
                        break;
                    }
                    if (cellIdentity instanceof CellIdentityNr) {
                        CellIdentityNr cellIdentityNr = (CellIdentityNr) cellIdentity;
                        strValueOf = String.valueOf(cellIdentityNr.getNci());
                        strValueOf2 = String.valueOf(cellIdentityNr.getTac());
                        strValueOf3 = String.valueOf(cellIdentityNr.getPci());
                        str = "SA";
                        break;
                    }
                }
            }
            if (strValueOf == null || strValueOf.isEmpty()) {
                strValueOf = "ex01";
            }
            if (strValueOf2 == null || strValueOf2.isEmpty()) {
                strValueOf2 = "ex01";
            }
            if (strValueOf3 == null || strValueOf3.isEmpty()) {
                strValueOf3 = "ex01";
            }
            if (str.isEmpty()) {
                str = "ex01";
            }
            g1.h.a("DeviceInfoReaderBase", "getNetworkRegistrationInfo->slot:" + i2 + ", tac:" + strValueOf2 + ", cid:" + strValueOf + "，pci:" + strValueOf3 + "，networkType:" + str + ", voNREnable:" + str2);
            if (i2 == 0) {
                bVar.f2453h = simSerialNumber;
                bVar.f2454i = subscriberId;
                bVar.f2456k = simOperator;
                bVar.f2457l = networkOperator;
                bVar.f2460o = strValueOf;
                bVar.f2459n = strValueOf2;
                bVar.f2461p = strValueOf3;
                bVar.f2458m = str;
                bVar.f2464s = str2;
            } else if (i2 == 1) {
                bVar.f2466u = simSerialNumber;
                bVar.f2467v = subscriberId;
                bVar.f2469x = simOperator;
                bVar.f2470y = networkOperator;
                bVar.B = strValueOf;
                bVar.A = strValueOf2;
                bVar.C = strValueOf3;
                bVar.f2471z = str;
                bVar.F = str2;
            }
            return;
        }
        g1.h.b("DeviceInfoReaderBase", "getNetworkRegistrationInfo no enough permission!");
    }

    public boolean n(Context context, int i2) {
        List<SubscriptionInfo> activeSubscriptionInfoList;
        if (y.a.a(context, "android.permission.READ_PHONE_STATE") != 0) {
            Log.e("DeviceInfoReaderBase", "缺少 READ_PHONE_STATE 权限");
            return false;
        }
        SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService("telephony_subscription_service");
        if (subscriptionManager != null && (activeSubscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList()) != null) {
            for (SubscriptionInfo subscriptionInfo : activeSubscriptionInfoList) {
                if (subscriptionInfo.getSubscriptionId() == i2) {
                    return subscriptionInfo.isEmbedded();
                }
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:44:0x0165  */
    /* JADX WARN: Code duplicated, block: B:46:0x0183  */
    /* JADX WARN: Code duplicated, block: B:47:0x0188  */
    /* JADX WARN: Code duplicated, block: B:60:0x01fa  */
    /* JADX WARN: Code duplicated, block: B:61:0x0202  */
    /* JADX WARN: Code duplicated, block: B:63:0x0218  */
    /* JADX WARN: Code duplicated, block: B:64:0x0220  */
    /* JADX WARN: Code duplicated, block: B:66:0x019f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:68:0x0137 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:44:0x0165, please report this as an issue */
    void q(Context context, e1.b bVar) {
        String str;
        int activeDataSubscriptionId;
        String strValueOf;
        boolean zBooleanValue;
        EuiccManager euiccManager;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        bVar.V = "000005";
        if (bVar.f2443a.equals("Xiaomi")) {
            bVar.V = "000005";
            Class<?> clsA = g1.k.a("miui.telephony.TelephonyManager");
            Object objC = g1.k.c(g1.k.b(clsA, "getDefault", new Class[0]), null, new Object[0]);
            try {
                Class cls = Integer.TYPE;
                bVar.f2452g = (String) g1.k.c(g1.k.b(clsA, "getImeiForSlot", cls), objC, 0);
                bVar.f2465t = (String) g1.k.c(g1.k.b(clsA, "getImeiForSlot", cls), objC, 1);
            } catch (Exception unused) {
                str = "Exception:getImeiForSlot";
                g1.h.b("DeviceInfoReaderBase", str);
            }
        } else {
            try {
                bVar.f2452g = telephonyManager.getImei(0);
                bVar.f2465t = telephonyManager.getImei(1);
            } catch (Exception e2) {
                str = "readAndroidT getImei 异常: " + e2.getMessage();
                g1.h.b("DeviceInfoReaderBase", str);
            }
        }
        String str2 = bVar.f2452g;
        if (str2 == null || str2.isEmpty()) {
            bVar.f2452g = "ex00";
        }
        String str3 = bVar.f2465t;
        if (str3 == null || str3.isEmpty()) {
            bVar.f2465t = "ex00";
        }
        SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService("telephony_subscription_service");
        int iJ = j(0);
        int iJ2 = j(1);
        try {
            activeDataSubscriptionId = SubscriptionManager.getActiveDataSubscriptionId();
        } catch (RuntimeException e3) {
            g1.h.b("DeviceInfoReaderBase", "readAndroidT getActiveDataSubscriptionId 异常: " + e3.getMessage());
            activeDataSubscriptionId = -1;
        }
        g1.h.a("DeviceInfoReaderBase", "subId0:" + iJ + "，subId1:" + iJ2 + ", activeDataSubId：" + activeDataSubscriptionId);
        try {
            if (iJ < 0 && iJ2 < 0) {
                strValueOf = "0";
            } else {
                if (activeDataSubscriptionId != iJ) {
                    if (activeDataSubscriptionId == iJ2) {
                        strValueOf = String.valueOf(2);
                    }
                    g1.h.a("DeviceInfoReaderBase", "20240524dataslot: " + bVar.G);
                    if (g1.l.o("ro.product.manufacturer").equalsIgnoreCase("Xiaomi")) {
                        try {
                            Class<?> cls2 = Class.forName("miui.telephony.TelephonyManagerEx");
                            zBooleanValue = ((Boolean) cls2.getMethod("isEsimEnabled", new Class[0]).invoke(cls2.getMethod("getDefault", new Class[0]).invoke(null, new Object[0]), new Object[0])).booleanValue();
                        } catch (Exception e4) {
                            Log.e("DeviceInfoReaderBase", "isEsimSupport failed", e4);
                            zBooleanValue = false;
                        }
                    } else {
                        euiccManager = (EuiccManager) context.getSystemService("euicc");
                        g1.h.a("DeviceInfoReaderBase", "eSIM判断情况根据euiccManager是否为空，euiccManager：" + euiccManager);
                        if (euiccManager != null) {
                            zBooleanValue = euiccManager.isEnabled();
                        } else {
                            zBooleanValue = false;
                        }
                    }
                    g1.h.a("DeviceInfoReaderBase", "iseSIM的值：" + zBooleanValue);
                    if (zBooleanValue) {
                        try {
                            bVar.L = d(g1.d.f2648d, iJ, iJ2);
                            bVar.M = f(g1.d.f2648d);
                        } catch (Exception e5) {
                            g1.h.a("DeviceInfoReaderBase", "get eSIM Exception " + e5);
                        }
                    }
                    g1.h.a("DeviceInfoReaderBase", "20240524defaultDataSubId: " + ((Integer) subscriptionManager.getClass().getMethod("getDefaultDataSubscriptionId", new Class[0]).invoke(subscriptionManager, new Object[0])).intValue());
                    if (iJ >= 0) {
                        bVar.f2463r = "1";
                        g(context, 0, bVar);
                        bVar.f2462q = "1";
                    } else {
                        bVar.f2453h = "ex00";
                        bVar.f2454i = "ex00";
                        bVar.f2456k = "ex00";
                        bVar.f2458m = "ex00";
                        bVar.f2459n = "ex00";
                        bVar.f2460o = "ex00";
                        bVar.f2461p = "ex00";
                        bVar.f2464s = "ex00";
                        bVar.f2462q = "ex00";
                        bVar.f2463r = "ex00";
                    }
                    if (iJ2 >= 0) {
                        bVar.E = "1";
                        g(context, 1, bVar);
                        bVar.D = "1";
                        return;
                    }
                    bVar.f2466u = "ex00";
                    bVar.f2467v = "ex00";
                    bVar.f2469x = "ex00";
                    bVar.f2471z = "ex00";
                    bVar.A = "ex00";
                    bVar.B = "ex00";
                    bVar.C = "ex00";
                    bVar.F = "ex00";
                    bVar.D = "ex00";
                    bVar.E = "ex00";
                }
                strValueOf = String.valueOf(1);
            }
            g1.h.a("DeviceInfoReaderBase", "20240524defaultDataSubId: " + ((Integer) subscriptionManager.getClass().getMethod("getDefaultDataSubscriptionId", new Class[0]).invoke(subscriptionManager, new Object[0])).intValue());
        } catch (Exception e6) {
            e6.printStackTrace();
        }
        bVar.G = strValueOf;
        g1.h.a("DeviceInfoReaderBase", "20240524dataslot: " + bVar.G);
        if (g1.l.o("ro.product.manufacturer").equalsIgnoreCase("Xiaomi")) {
            Class<?> cls3 = Class.forName("miui.telephony.TelephonyManagerEx");
            zBooleanValue = ((Boolean) cls3.getMethod("isEsimEnabled", new Class[0]).invoke(cls3.getMethod("getDefault", new Class[0]).invoke(null, new Object[0]), new Object[0])).booleanValue();
        } else {
            euiccManager = (EuiccManager) context.getSystemService("euicc");
            g1.h.a("DeviceInfoReaderBase", "eSIM判断情况根据euiccManager是否为空，euiccManager：" + euiccManager);
            if (euiccManager != null) {
                zBooleanValue = euiccManager.isEnabled();
            } else {
                zBooleanValue = false;
            }
        }
        g1.h.a("DeviceInfoReaderBase", "iseSIM的值：" + zBooleanValue);
        if (zBooleanValue) {
            bVar.L = d(g1.d.f2648d, iJ, iJ2);
            bVar.M = f(g1.d.f2648d);
        }
        if (iJ >= 0) {
            bVar.f2463r = "1";
            g(context, 0, bVar);
            bVar.f2462q = "1";
        } else {
            bVar.f2453h = "ex00";
            bVar.f2454i = "ex00";
            bVar.f2456k = "ex00";
            bVar.f2458m = "ex00";
            bVar.f2459n = "ex00";
            bVar.f2460o = "ex00";
            bVar.f2461p = "ex00";
            bVar.f2464s = "ex00";
            bVar.f2462q = "ex00";
            bVar.f2463r = "ex00";
        }
        if (iJ2 >= 0) {
            bVar.E = "1";
            g(context, 1, bVar);
            bVar.D = "1";
            return;
        }
        bVar.f2466u = "ex00";
        bVar.f2467v = "ex00";
        bVar.f2469x = "ex00";
        bVar.f2471z = "ex00";
        bVar.A = "ex00";
        bVar.B = "ex00";
        bVar.C = "ex00";
        bVar.F = "ex00";
        bVar.D = "ex00";
        bVar.E = "ex00";
    }

    /* JADX WARN: Code duplicated, block: B:34:0x00db A[Catch: Exception -> 0x00ec, TRY_LEAVE, TryCatch #2 {Exception -> 0x00ec, blocks: (B:32:0x00bb, B:34:0x00db), top: B:50:0x00bb }] */
    /* JADX WARN: Code duplicated, block: B:40:0x0105  */
    /* JADX WARN: Code duplicated, block: B:41:0x010d  */
    /* JADX WARN: Code duplicated, block: B:43:0x0123  */
    /* JADX WARN: Code duplicated, block: B:44:0x012b  */
    void r(Context context, e1.b bVar) {
        String strValueOf;
        boolean zHasSystemFeature;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        bVar.V = "000005";
        try {
            bVar.f2452g = telephonyManager.getImei(0);
        } catch (RuntimeException e2) {
            g1.h.b("DeviceInfoReaderBase", "readvivoAndroidT getImei(0) 异常: " + e2.getMessage());
        }
        String str = bVar.f2452g;
        if (str == null || str.isEmpty()) {
            bVar.f2452g = "ex00";
        }
        try {
            bVar.f2465t = telephonyManager.getImei(1);
        } catch (RuntimeException e3) {
            g1.h.b("DeviceInfoReaderBase", "readvivoAndroidT getImei(1) 异常: " + e3.getMessage());
        }
        String str2 = bVar.f2465t;
        if (str2 == null || str2.isEmpty()) {
            bVar.f2465t = "ex00";
        }
        int iJ = j(0);
        int iJ2 = j(1);
        int activeDataSubscriptionId = SubscriptionManager.getActiveDataSubscriptionId();
        g1.h.a("DeviceInfoReaderBase", "subId0:" + iJ + "，subId1:" + iJ2 + ", activeDataSubId：" + activeDataSubscriptionId);
        try {
            if (iJ < 0 && iJ2 < 0) {
                strValueOf = "0";
            } else {
                if (activeDataSubscriptionId != iJ) {
                    if (activeDataSubscriptionId == iJ2) {
                        strValueOf = String.valueOf(2);
                    }
                    zHasSystemFeature = context.getPackageManager().hasSystemFeature("android.hardware.telephony.euicc");
                    g1.h.a("DeviceInfoReaderBase", "iseSIM的值：" + zHasSystemFeature);
                    if (zHasSystemFeature) {
                        bVar.L = d(g1.d.f2648d, iJ, iJ2);
                        bVar.M = f(g1.d.f2648d);
                    }
                    if (iJ >= 0) {
                        bVar.f2463r = "1";
                        g(context, 0, bVar);
                        bVar.f2462q = "1";
                    } else {
                        bVar.f2453h = "ex00";
                        bVar.f2454i = "ex00";
                        bVar.f2456k = "ex00";
                        bVar.f2458m = "ex00";
                        bVar.f2459n = "ex00";
                        bVar.f2460o = "ex00";
                        bVar.f2461p = "ex00";
                        bVar.f2464s = "ex00";
                        bVar.f2462q = "ex00";
                        bVar.f2463r = "ex00";
                    }
                    if (iJ2 >= 0) {
                        bVar.E = "1";
                        g(context, 1, bVar);
                        bVar.D = "1";
                        return;
                    }
                    bVar.f2466u = "ex00";
                    bVar.f2467v = "ex00";
                    bVar.f2469x = "ex00";
                    bVar.f2471z = "ex00";
                    bVar.A = "ex00";
                    bVar.B = "ex00";
                    bVar.C = "ex00";
                    bVar.F = "ex00";
                    bVar.D = "ex00";
                    bVar.E = "ex00";
                }
                strValueOf = String.valueOf(1);
            }
            zHasSystemFeature = context.getPackageManager().hasSystemFeature("android.hardware.telephony.euicc");
            g1.h.a("DeviceInfoReaderBase", "iseSIM的值：" + zHasSystemFeature);
            if (zHasSystemFeature) {
                bVar.L = d(g1.d.f2648d, iJ, iJ2);
                bVar.M = f(g1.d.f2648d);
            }
        } catch (Exception e4) {
            g1.h.a("DeviceInfoReaderBase", "get eSIM Exception " + e4);
        }
        bVar.G = strValueOf;
        if (iJ >= 0) {
            bVar.f2463r = "1";
            g(context, 0, bVar);
            bVar.f2462q = "1";
        } else {
            bVar.f2453h = "ex00";
            bVar.f2454i = "ex00";
            bVar.f2456k = "ex00";
            bVar.f2458m = "ex00";
            bVar.f2459n = "ex00";
            bVar.f2460o = "ex00";
            bVar.f2461p = "ex00";
            bVar.f2464s = "ex00";
            bVar.f2462q = "ex00";
            bVar.f2463r = "ex00";
        }
        if (iJ2 >= 0) {
            bVar.E = "1";
            g(context, 1, bVar);
            bVar.D = "1";
            return;
        }
        bVar.f2466u = "ex00";
        bVar.f2467v = "ex00";
        bVar.f2469x = "ex00";
        bVar.f2471z = "ex00";
        bVar.A = "ex00";
        bVar.B = "ex00";
        bVar.C = "ex00";
        bVar.F = "ex00";
        bVar.D = "ex00";
        bVar.E = "ex00";
    }
}
