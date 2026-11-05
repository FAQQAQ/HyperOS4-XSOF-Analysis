package i1;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.storage.StorageManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.registration.R;
import com.xiaomi.registration.autoregistration.AutoRegIntentService;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import miui.os.Build;
import miui.telephony.SubscriptionManager;
import miui.util.HardwareInfo;

/* JADX INFO: loaded from: classes.dex */
public final class c extends Handler {
    public final /* synthetic */ int a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f1701b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c(Object obj, Looper looper, int i3) {
        super(looper);
        this.a = i3;
        this.f1701b = obj;
    }

    /* JADX WARN: Code duplicated, block: B:197:0x04da A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:198:0x04dc  */
    /* JADX WARN: Code duplicated, block: B:202:0x04ec A[PHI: r9
      0x04ec: PHI (r9v2 int) = (r9v1 int), (r9v3 int) binds: [B:201:0x04ea, B:195:0x04d7] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:238:0x05c1 A[PHI: r3
      0x05c1: PHI (r3v6 j1.b) = (r3v5 j1.b), (r3v9 j1.b) binds: [B:237:0x05bf, B:228:0x05a6] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:244:0x05d1 A[PHI: r3
      0x05d1: PHI (r3v7 j1.b) = (r3v5 j1.b), (r3v5 j1.b), (r3v9 j1.b), (r3v9 j1.b) binds: [B:240:0x05c8, B:243:0x05cf, B:231:0x05ad, B:234:0x05b4] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int iG;
        j1.b bVar;
        String strC;
        j1.b bVar2;
        String strD;
        j1.b bVar3;
        long jPow;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        String strSubstring;
        int i8;
        int i9;
        String string;
        int i10;
        int i11;
        int i12;
        int i13;
        NetworkCapabilities networkCapabilities;
        j1.b bVar4;
        j1.b bVar5;
        boolean z2;
        int i14 = this.a;
        Object obj = this.f1701b;
        switch (i14) {
            case 0:
                try {
                    AutoRegIntentService.a((AutoRegIntentService) obj, (Intent) message.obj);
                } catch (Throwable th) {
                    Log.e("AutoReg_IntentSvc", "onHandleIntent threw", th);
                }
                ((AutoRegIntentService) obj).stopSelf(message.arg1);
                break;
            default:
                if (message.what == 1) {
                    j1.a aVar = (j1.a) message.obj;
                    Message message2 = aVar.f1781b;
                    String str = aVar.a;
                    try {
                        iG = !"5GRCSSW".equals(str) ? androidx.activity.result.a.g(str) : 36;
                    } catch (Exception unused) {
                        iG = 32;
                    }
                    String macAddress = null;
                    String string2 = "";
                    String string3 = "0";
                    String strO = "null";
                    String str2 = "1";
                    switch (l.k.a(iG)) {
                        case 0:
                            bVar = (j1.b) obj;
                            bVar.getClass();
                            strC = "12.0";
                            bVar.s(message2, strC);
                            break;
                        case 1:
                            j1.b bVar6 = (j1.b) obj;
                            SharedPreferences sharedPreferences = (SharedPreferences) bVar6.f1788f;
                            String string4 = sharedPreferences.getString("pref_meid", "");
                            if (TextUtils.isEmpty(string4) || string4.equals("0") || string4.equals("00000000000000")) {
                                int i15 = 0;
                                while (true) {
                                    TelephonyManager telephonyManager = bVar6.f1785c;
                                    if (i15 < telephonyManager.getPhoneCount()) {
                                        string4 = telephonyManager.getMeid(i15);
                                        if (TextUtils.isEmpty(string4) || string4.equals("0") || string4.equals("00000000000000")) {
                                            i15++;
                                        } else {
                                            j1.c.i("Meid: " + string4);
                                        }
                                    }
                                }
                                strO = string4 != null ? string4 : "null";
                                sharedPreferences.edit().putString("pref_meid", strO).apply();
                                string4 = strO;
                            } else {
                                j1.c.i("Meid = " + string4);
                            }
                            bVar6.s(message2, string4);
                            break;
                        case 2:
                            bVar = (j1.b) obj;
                            strC = j1.c.c((Context) bVar.f1787e);
                            bVar.s(message2, strC);
                            break;
                        case 3:
                            bVar = (j1.b) obj;
                            strC = j1.c.a((Context) bVar.f1787e);
                            bVar.s(message2, strC);
                            break;
                        case 4:
                            bVar2 = (j1.b) obj;
                            if (bVar2.p(0) == -1) {
                                strD = "";
                            } else if (!j1.c.f(0) || (strD = f2.e.d(0)) == null) {
                                strD = "null";
                            }
                            bVar2.s(message2, strD);
                            break;
                        case 5:
                            bVar2 = (j1.b) obj;
                            if (bVar2.p(1) == -1) {
                                strD = "";
                            } else if (!j1.c.f(1) || (strD = f2.e.d(1)) == null) {
                                strD = "null";
                            }
                            bVar2.s(message2, strD);
                            break;
                        case 6:
                            bVar = (j1.b) obj;
                            bVar.getClass();
                            if (Build.IS_TABLET) {
                                str2 = "10";
                            } else {
                                int i16 = j1.c.a;
                                if (miui.telephony.TelephonyManager.getDefault().isFiveGCapable()) {
                                    str2 = "5";
                                }
                            }
                            strC = str2;
                            bVar.s(message2, strC);
                            break;
                        case 7:
                            bVar = (j1.b) obj;
                            strC = bVar.o(0);
                            bVar.s(message2, strC);
                            break;
                        case 8:
                            bVar = (j1.b) obj;
                            strC = bVar.o(1);
                            bVar.s(message2, strC);
                            break;
                        case 9:
                            bVar = (j1.b) obj;
                            strC = bVar.n(0);
                            bVar.s(message2, strC);
                            break;
                        case 10:
                            bVar = (j1.b) obj;
                            strC = bVar.n(1);
                            bVar.s(message2, strC);
                            break;
                        case 11:
                            bVar = (j1.b) obj;
                            strC = j1.b.d(bVar, 0);
                            bVar.s(message2, strC);
                            break;
                        case 12:
                            bVar3 = (j1.b) obj;
                            WifiInfo connectionInfo = ((WifiManager) ((Context) bVar3.f1787e).getSystemService("wifi")).getConnectionInfo();
                            macAddress = connectionInfo != null ? connectionInfo.getMacAddress() : null;
                            if (macAddress == null || macAddress.isEmpty()) {
                                macAddress = " ";
                            }
                            bVar3.s(message2, macAddress);
                            break;
                        case 13:
                            bVar = (j1.b) obj;
                            bVar.getClass();
                            strC = android.os.Build.MANUFACTURER;
                            bVar.s(message2, strC);
                            break;
                        case 14:
                            bVar = (j1.b) obj;
                            bVar.getClass();
                            strC = "android" + android.os.Build.VERSION.RELEASE;
                            bVar.s(message2, strC);
                            break;
                        case 15:
                            bVar = (j1.b) obj;
                            Object obj2 = bVar.f1787e;
                            ArrayList arrayList = new ArrayList();
                            arrayList.add(Environment.getDataDirectory());
                            if (!"mixed".equals(f2.e.g("ro.boot.sdcard.type", "mixed"))) {
                                String externalStorageState = Environment.getExternalStorageState();
                                if ("mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState)) {
                                    arrayList.add(Environment.getExternalStorageDirectory());
                                }
                            }
                            Iterator it = arrayList.iterator();
                            long totalSpace = 0;
                            while (it.hasNext()) {
                                totalSpace = ((File) it.next()).getTotalSpace() + totalSpace;
                            }
                            if (j1.b.f1783i == 0) {
                                j1.b.f1783i = totalSpace;
                                long j3 = totalSpace / 1000000000;
                                j1.b.f1783i = (totalSpace >= 8000000000L ? (long) Math.pow(2.0d, Math.ceil(Math.log(j3) / Math.log(2.0d))) : j3 + 1) * 1000000000;
                            }
                            long j4 = j1.b.f1783i;
                            long jLongValue = -1;
                            try {
                                StorageManager storageManager = (StorageManager) ((Context) obj2).getSystemService("storage");
                                jLongValue = ((Long) storageManager.getClass().getDeclaredMethod("getPrimaryStorageSize", null).invoke(storageManager, new Object[0])).longValue() - (((long) j1.b.k((Context) obj2)) * 1000000000);
                                jPow = ((long) Math.pow(2.0d, Math.ceil(Math.log(jLongValue / 1000000000) / Math.log(2.0d)))) * 1000000000;
                                if (jPow < jLongValue) {
                                    long j5 = 1;
                                    long j6 = 1;
                                    while (true) {
                                        long j7 = j5 * j6;
                                        if (j7 < jLongValue) {
                                            j5 <<= 1;
                                            if (j5 > 512) {
                                                j6 *= 1000;
                                                j5 = 1;
                                            }
                                        } else {
                                            jPow = j7;
                                        }
                                    }
                                }
                            } catch (Exception e3) {
                                Log.e("AutoReg_DeviceInfoPool", "getTotalRomBytes Error", e3);
                                jPow = jLongValue;
                            }
                            Log.d("AutoReg_DeviceInfoPool", "rom sz1:" + j4 + " sz2:" + jPow);
                            if (jPow > 1000) {
                                j4 = jPow;
                            }
                            Context context = (Context) obj2;
                            if (context == null) {
                                string = "";
                                i9 = 1;
                            } else {
                                float f3 = j4;
                                if (f3 > 900.0f) {
                                    f3 /= 1000.0f;
                                    i3 = R.string.size_kilo_byte;
                                } else {
                                    i3 = R.string.size_byte;
                                }
                                if (f3 > 900.0f) {
                                    f3 /= 1000.0f;
                                    i4 = R.string.size_mega_byte;
                                } else {
                                    i4 = i3;
                                }
                                if (f3 > 900.0f) {
                                    f3 /= 1000.0f;
                                    i5 = R.string.size_giga_byte;
                                } else {
                                    i5 = i4;
                                }
                                if (f3 > 900.0f) {
                                    f3 /= 1000.0f;
                                    i6 = R.string.size_tera_byte;
                                } else {
                                    i6 = i5;
                                }
                                if (f3 > 900.0f) {
                                    f3 /= 1000.0f;
                                    i7 = R.string.size_peta_byte;
                                } else {
                                    i7 = i6;
                                }
                                if (f3 < 1.0f) {
                                    strSubstring = String.format("%.2f", Float.valueOf(f3));
                                } else if (f3 < 10.0f) {
                                    strSubstring = String.format("%.1f", Float.valueOf(f3));
                                } else {
                                    strSubstring = f3 < 100.0f ? String.format("%.0f", Float.valueOf(f3)) : String.format("%.0f", Float.valueOf(f3));
                                }
                                int length = strSubstring.length();
                                if (length > 3) {
                                    i8 = length - 3;
                                    if (strSubstring.charAt(i8) == '.' && strSubstring.charAt(length - 2) == '0' && strSubstring.charAt(length - 1) == '0') {
                                        strSubstring = strSubstring.substring(0, i8);
                                    } else if (length > 2) {
                                        i8 = length - 2;
                                        if (strSubstring.charAt(i8) == '.' && strSubstring.charAt(length - 1) == '0') {
                                            strSubstring = strSubstring.substring(0, i8);
                                        }
                                    }
                                } else if (length > 2) {
                                    i8 = length - 2;
                                    if (strSubstring.charAt(i8) == '.') {
                                        strSubstring = strSubstring.substring(0, i8);
                                    }
                                }
                                Resources resources = context.getResources();
                                String string5 = context.getString(i7);
                                i9 = 1;
                                string = resources.getString(R.string.size_suffix, strSubstring, string5);
                            }
                            strC = string.substring(0, string.length() - i9);
                            bVar.s(message2, strC);
                            break;
                        case 16:
                            j1.b bVar7 = (j1.b) obj;
                            Context context2 = (Context) bVar7.f1787e;
                            long totalPhysicalMemory = HardwareInfo.getTotalPhysicalMemory();
                            if (context2 == null) {
                                bVar7 = bVar7;
                            } else {
                                float f4 = totalPhysicalMemory;
                                if (f4 > 921.6d) {
                                    f4 /= 1024.0f;
                                    i10 = R.string.size_kilo_byte;
                                } else {
                                    i10 = R.string.size_byte;
                                }
                                if (f4 > 921.6d) {
                                    f4 /= 1024.0f;
                                    i11 = R.string.size_mega_byte;
                                } else {
                                    i11 = i10;
                                }
                                if (f4 > 921.6d) {
                                    f4 /= 1024.0f;
                                    i12 = R.string.size_giga_byte;
                                } else {
                                    i12 = i11;
                                }
                                if (f4 > 921.6d) {
                                    f4 /= 1024.0f;
                                    i12 = R.string.size_tera_byte;
                                }
                                if (f4 > 921.6d) {
                                    f4 /= 1024.0f;
                                    i13 = R.string.size_peta_byte;
                                } else {
                                    i13 = i12;
                                }
                                float fCeil = (float) Math.ceil(f4);
                                Log.d("AutoReg_DeviceInfoPool", "formatSizeWith1024" + fCeil);
                                string2 = context2.getResources().getString(R.string.size_suffix, Integer.toString((int) Math.floor((double) fCeil)), context2.getString(i13));
                            }
                            Log.d("AutoReg_DeviceInfoPool", "getRAMCapability: " + string2);
                            bVar7.s(message2, string2);
                            break;
                        case 17:
                            bVar = (j1.b) obj;
                            bVar.getClass();
                            strC = String.valueOf(1);
                            ConnectivityManager connectivityManager = (ConnectivityManager) ((Context) bVar.f1787e).getSystemService("connectivity");
                            Network activeNetwork = connectivityManager.getActiveNetwork();
                            if (activeNetwork != null && (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) != null) {
                                if (networkCapabilities.hasTransport(1)) {
                                    strC = String.valueOf(2);
                                } else if (networkCapabilities.hasTransport(0)) {
                                    strC = String.valueOf(1);
                                }
                            }
                            bVar.s(message2, strC);
                            break;
                        case 18:
                            bVar = (j1.b) obj;
                            bVar.getClass();
                            strC = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(new Date());
                            bVar.s(message2, strC);
                            break;
                        case 19:
                            bVar = (j1.b) obj;
                            bVar.getClass();
                            strC = miui.telephony.TelephonyManager.getDefault().getImeiForSlot(0);
                            bVar.s(message2, strC);
                            break;
                        case 20:
                            bVar = (j1.b) obj;
                            bVar.getClass();
                            strC = miui.telephony.TelephonyManager.getDefault().getImeiForSlot(1);
                            bVar.s(message2, strC);
                            break;
                        case 21:
                            bVar = (j1.b) obj;
                            strC = bVar.j(0);
                            bVar.s(message2, strC);
                            break;
                        case 22:
                            bVar = (j1.b) obj;
                            strC = bVar.j(1);
                            bVar.s(message2, strC);
                            break;
                        case 23:
                            bVar = (j1.b) obj;
                            bVar.getClass();
                            strC = String.valueOf(SubscriptionManager.getDefault().getDefaultDataSlotId() + 1);
                            bVar.s(message2, strC);
                            break;
                        case 24:
                            bVar = (j1.b) obj;
                            strC = j1.b.e(bVar, 0);
                            bVar.s(message2, strC);
                            break;
                        case 25:
                            bVar = (j1.b) obj;
                            strC = j1.b.e(bVar, 1);
                            bVar.s(message2, strC);
                            break;
                        case 26:
                            bVar = (j1.b) obj;
                            strC = j1.b.d(bVar, 1);
                            bVar.s(message2, strC);
                            break;
                        case 27:
                            bVar = (j1.b) obj;
                            strC = bVar.i(0);
                            bVar.s(message2, strC);
                            break;
                        case 28:
                            bVar = (j1.b) obj;
                            strC = bVar.i(1);
                            bVar.s(message2, strC);
                            break;
                        case 29:
                            bVar = (j1.b) obj;
                            strC = j1.b.b(bVar, 0);
                            bVar.s(message2, strC);
                            break;
                        case 30:
                            bVar = (j1.b) obj;
                            strC = j1.b.b(bVar, 1);
                            bVar.s(message2, strC);
                            break;
                        case 31:
                        default:
                            bVar3 = (j1.b) obj;
                            bVar3.s(message2, macAddress);
                            break;
                        case 32:
                            bVar4 = (j1.b) obj;
                            int iG2 = b.g((Context) bVar4.f1787e);
                            if (-1 != iG2) {
                                strO = bVar4.o(iG2);
                            }
                            bVar4.s(message2, strO);
                            break;
                        case 33:
                            bVar4 = (j1.b) obj;
                            int iG3 = b.g((Context) bVar4.f1787e);
                            if (-1 != iG3) {
                                strO = bVar4.n(iG3);
                            }
                            bVar4.s(message2, strO);
                            break;
                        case 34:
                            bVar = (j1.b) obj;
                            int i17 = j1.c.a;
                            strC = f2.e.g("ro.vendor.ril.imeitt", "null");
                            j1.c.i("imei3: " + strC);
                            bVar.s(message2, strC);
                            break;
                        case 35:
                            bVar5 = (j1.b) obj;
                            bVar5.getClass();
                            Log.d("AutoReg_DeviceInfoPool", "Start get5GRcsSwitch status");
                            String[] strArr = {"process_static_white_list_base", "process_static_white_list_lite", "process_static_white_list"};
                            try {
                                Resources system = Resources.getSystem();
                                int i18 = 0;
                                while (true) {
                                    if (i18 >= 3) {
                                        z2 = false;
                                    } else {
                                        int identifier = system.getIdentifier(strArr[i18], "array", "android.miui");
                                        if (identifier != 0) {
                                            String[] stringArray = system.getStringArray(identifier);
                                            int length2 = stringArray.length;
                                            int i19 = 0;
                                            while (true) {
                                                if (i19 >= length2) {
                                                    continue;
                                                } else if ("com.android.mms:mms_service".equals(stringArray[i19])) {
                                                    z2 = true;
                                                } else {
                                                    i19++;
                                                }
                                                z2 = false;
                                            }
                                        }
                                        i18++;
                                    }
                                }
                            } catch (Exception e4) {
                                Log.d("AutoReg_DeviceInfoPool", "isFiveGRcsSupported: check white-list failed", e4);
                            }
                            if (z2) {
                                ContentResolver contentResolver = ((Context) bVar5.f1787e).getContentResolver();
                                boolean z3 = Settings.System.getInt(contentResolver, "rcs_enable_cm", 0) != 0;
                                String str3 = z3 ? "pref_rcs_enable_cm" : "pref_rcs_enable";
                                int i20 = Settings.System.getInt(contentResolver, str3, 0);
                                Log.d("AutoReg_DeviceInfoPool", "get5GRcsSwitch: isCmRcs=" + z3 + " key=" + str3 + " val=" + i20);
                                if (i20 == 1) {
                                    string3 = "1";
                                }
                            } else {
                                Log.d("AutoReg_DeviceInfoPool", "get5GRcsSwitch: 5G RCS not supported");
                                string3 = "2";
                            }
                            bVar5.s(message2, string3);
                            break;
                        case 36:
                            bVar = (j1.b) obj;
                            strC = j1.b.c(bVar, 0);
                            bVar.s(message2, strC);
                            break;
                        case 37:
                            bVar = (j1.b) obj;
                            strC = j1.b.c(bVar, 1);
                            bVar.s(message2, strC);
                            break;
                        case 38:
                            bVar = (j1.b) obj;
                            strC = j1.b.a(bVar, 0);
                            bVar.s(message2, strC);
                            break;
                        case 39:
                            bVar = (j1.b) obj;
                            strC = j1.b.a(bVar, 1);
                            bVar.s(message2, strC);
                            break;
                        case 40:
                            bVar5 = (j1.b) obj;
                            BatteryManager batteryManager = (BatteryManager) ((Context) bVar5.f1787e).getSystemService("batterymanager");
                            string3 = batteryManager != null ? Integer.toString(batteryManager.getIntProperty(4)) : "0";
                            Log.d("AutoReg_DeviceInfoPool", "getBatteryLevel =" + string3);
                            bVar5.s(message2, string3);
                            break;
                        case 41:
                            bVar = (j1.b) obj;
                            strC = j1.b.f(bVar, 0);
                            bVar.s(message2, strC);
                            break;
                        case 42:
                            bVar = (j1.b) obj;
                            strC = j1.b.f(bVar, 1);
                            bVar.s(message2, strC);
                            break;
                    }
                    break;
                }
                break;
        }
    }
}
