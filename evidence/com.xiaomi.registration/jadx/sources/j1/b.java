package j1;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.storage.StorageManager;
import android.preference.PreferenceManager;
import android.telephony.CellIdentity;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityNr;
import android.telephony.NetworkRegistrationInfo;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.telephony.UiccCardInfo;
import android.telephony.ims.ImsManager;
import android.text.TextUtils;
import android.util.Log;
import b2.k;
import b2.n;
import f2.e;
import java.io.InterruptedIOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import miui.telephony.SubscriptionManager;
import x1.o;
import x1.r;
import y1.f;
import y1.h;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static b f1782h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static long f1783i;
    public int a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1784b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final TelephonyManager f1785c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f1786d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f1787e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f1788f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f1789g;

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public b() {
        this.a = 64;
        this.f1784b = 5;
        this.f1787e = new ArrayDeque();
        this.f1788f = new ArrayDeque();
        this.f1789g = new ArrayDeque();
    }

    /* JADX WARN: Code duplicated, block: B:31:0x00c3  */
    /* JADX WARN: Instruction removed from duplicated block: B:31:0x00c3, please report this as an issue */
    public static String a(b bVar, int i3) {
        String string;
        StringBuilder sb;
        bVar.getClass();
        Log.d("AutoReg_DeviceInfoPool", "getCardType for slot " + i3);
        if (i3 >= 0) {
            TelephonyManager telephonyManager = bVar.f1785c;
            if (i3 >= telephonyManager.getPhoneCount()) {
                Log.e("AutoReg_DeviceInfoPool", "getCardType: invalid slot id " + i3);
            } else {
                if (bVar.p(i3) == -1) {
                    Log.d("AutoReg_DeviceInfoPool", "getCardType: slot " + i3 + " has no card");
                    return "";
                }
                List<UiccCardInfo> uiccCardsInfo = telephonyManager.getUiccCardsInfo();
                if (uiccCardsInfo == null || uiccCardsInfo.isEmpty()) {
                    string = "getCardType: uiccCardInfos is null or empty";
                } else {
                    Iterator<UiccCardInfo> it = uiccCardsInfo.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            sb = new StringBuilder("getCardType: no card info found for slot ");
                            sb.append(i3);
                            break;
                        }
                        UiccCardInfo next = it.next();
                        if (next.getSlotIndex() == i3) {
                            if (!next.isEuicc()) {
                                if (next.getCardId() < 0) {
                                    sb = new StringBuilder("getCardType: slot ");
                                    sb.append(i3);
                                    sb.append(" is other type");
                                    break;
                                }
                                Log.d("AutoReg_DeviceInfoPool", "getCardType: slot " + i3 + " is physical card");
                                return "1";
                            }
                            Log.d("AutoReg_DeviceInfoPool", "getCardType: slot " + i3 + " is eSIM");
                            return "2";
                        }
                    }
                    string = sb.toString();
                }
                Log.d("AutoReg_DeviceInfoPool", string);
            }
        } else {
            Log.e("AutoReg_DeviceInfoPool", "getCardType: invalid slot id " + i3);
        }
        return "3";
    }

    public static String b(b bVar, int i3) {
        boolean zIsDualNrEnabled;
        if (bVar.p(i3) == -1) {
            return "";
        }
        int defaultDataSlotId = SubscriptionManager.getDefault().getDefaultDataSlotId();
        int i4 = c.a;
        if (miui.telephony.TelephonyManager.getDefault().isFiveGCapable()) {
            if (defaultDataSlotId == i3) {
                c.i("card" + (i3 + 1) + " is master card");
                zIsDualNrEnabled = miui.telephony.TelephonyManager.getDefault().isUserFiveGEnabled();
            } else if (miui.telephony.TelephonyManager.getDefault().isDualNrSupported()) {
                c.i("card" + (i3 + 1) + " is secondary card and SupportDualNr");
                zIsDualNrEnabled = miui.telephony.TelephonyManager.getDefault().isDualNrEnabled();
            } else {
                c.i("card" + (i3 + 1) + " is secondary card and notSupportDualNr");
            }
            StringBuilder sb = new StringBuilder("card");
            int i5 = i3 + 1;
            sb.append(i5);
            sb.append(" 5G switch is ");
            sb.append(zIsDualNrEnabled);
            c.i(sb.toString());
            if (!zIsDualNrEnabled) {
                return "0";
            }
            if (!e.k()) {
                return "1";
            }
            c.i("card" + i5 + " 5G switch is hide");
            return "3";
        }
        return "2";
    }

    public static String c(b bVar, int i3) {
        String str;
        StringBuilder sb;
        String str2;
        bVar.getClass();
        Log.d("AutoReg_DeviceInfoPool", "getSatelliteSwitch for slot " + i3);
        String str3 = "FF";
        if (i3 >= 0) {
            TelephonyManager telephonyManager = bVar.f1785c;
            if (i3 < telephonyManager.getPhoneCount()) {
                if (bVar.p(i3) == -1) {
                    sb = new StringBuilder("getSatelliteSwitch: slot ");
                    sb.append(i3);
                    str2 = " has no card";
                } else {
                    if (telephonyManager.getSimState(i3) == 5) {
                        if (!e.l()) {
                            str = "getSatelliteSwitch: device does not support satellite";
                        } else if (c.f(i3)) {
                            String str4 = c.h((Context) bVar.f1787e) ? "1" : "0";
                            str3 = str4;
                            str = "getSatelliteSwitch: slot " + i3 + " satellite switch is " + str4;
                        } else {
                            sb = new StringBuilder("getSatelliteSwitch: slot ");
                            sb.append(i3);
                            str2 = " is not CT SIM (non-CT network)";
                        }
                        Log.d("AutoReg_DeviceInfoPool", str);
                        return str3;
                    }
                    sb = new StringBuilder("getSatelliteSwitch: slot ");
                    sb.append(i3);
                    str2 = " SIM not ready";
                }
                sb.append(str2);
                Log.d("AutoReg_DeviceInfoPool", sb.toString());
                return "";
            }
        }
        Log.e("AutoReg_DeviceInfoPool", "getSatelliteSwitch: invalid slot id " + i3);
        return "FF";
    }

    /* JADX WARN: Code duplicated, block: B:17:0x003c  */
    public static String d(b bVar, int i3) {
        int phoneCount;
        TelephonyManager telephonyManager = bVar.f1785c;
        if (telephonyManager.getSimState(i3) != 5) {
            return "FF";
        }
        if (bVar.a < 0) {
            phoneCount = bVar.f1784b;
        } else {
            String strO = bVar.o(bVar.f1784b);
            if (strO != null) {
                String[] strArrSplit = "898603,898611,898612".split(",");
                if (strArrSplit.length <= 0) {
                    phoneCount = -1;
                } else if (strO.startsWith(strArrSplit[0])) {
                    phoneCount = bVar.f1784b;
                } else {
                    phoneCount = bVar.a;
                }
            } else {
                phoneCount = (bVar.f1784b + 1) % telephonyManager.getPhoneCount();
            }
        }
        return telephonyManager.createForSubscriptionId(bVar.p(phoneCount)).hasIccCard() ? "2" : "1";
    }

    public static String e(b bVar, int i3) {
        if (bVar.f1785c.getSimState(i3) == 5) {
            if (((miui.telephony.TelephonyManager.getDefault().isDualVolteSupported() || i3 == SubscriptionManager.getDefault().getDefaultDataSlotId()) && android.telephony.SubscriptionManager.isValidSubscriptionId(i3)) && miui.telephony.TelephonyManager.getDefault().isVolteEnabledByPlatform(i3) && ((ImsManager) ((Context) bVar.f1787e).getSystemService(ImsManager.class)).getImsMmTelManager(bVar.p(i3)).isAdvancedCallingSettingEnabled()) {
                return "1";
            }
        }
        return "2";
    }

    public static String f(b bVar, int i3) {
        int iQ;
        StringBuilder sb;
        StringBuilder sb2;
        String str;
        bVar.getClass();
        Log.d("AutoReg_DeviceInfoPool", "getTac for slot " + i3);
        String str2 = "null";
        if (i3 >= 0) {
            TelephonyManager telephonyManager = bVar.f1785c;
            if (i3 < telephonyManager.getPhoneCount()) {
                int iP = bVar.p(i3);
                if (iP != -1) {
                    if (!c.f(i3)) {
                        sb = new StringBuilder("getTac: slot ");
                        sb.append(i3);
                        sb.append(" is not CT SIM");
                    } else if (telephonyManager.getSimState(i3) == 1) {
                        sb2 = new StringBuilder("getTac: slot ");
                        sb2.append(i3);
                        str = " SIM absent";
                    } else {
                        try {
                            ServiceState serviceState = telephonyManager.createForSubscriptionId(iP).getServiceState();
                            if (serviceState != null) {
                                iQ = q(m(serviceState, 2));
                                if (iQ == -1 || iQ == Integer.MAX_VALUE) {
                                    try {
                                        iQ = q(m(serviceState, 1));
                                    } catch (Exception e3) {
                                        e = e3;
                                        Log.e("AutoReg_DeviceInfoPool", "getTac: failed to get ServiceState", e);
                                    }
                                }
                            } else {
                                iQ = -1;
                            }
                        } catch (Exception e4) {
                            e = e4;
                            iQ = -1;
                        }
                        if (iQ == -1 || iQ == Integer.MAX_VALUE) {
                            sb = new StringBuilder("getTac: failed to get valid TAC for slot ");
                            sb.append(i3);
                        } else {
                            String hexString = Integer.toHexString(iQ);
                            StringBuilder sb3 = new StringBuilder("getTac: slot=");
                            sb3.append(i3);
                            sb3.append(" tac=");
                            sb3.append(iQ);
                            sb3.append(" hex=");
                            sb3.append(hexString);
                            str2 = hexString;
                            sb = sb3;
                        }
                    }
                    Log.d("AutoReg_DeviceInfoPool", sb.toString());
                    return str2;
                }
                sb2 = new StringBuilder("getTac: slot ");
                sb2.append(i3);
                str = " has no valid subscription";
                sb2.append(str);
                Log.d("AutoReg_DeviceInfoPool", sb2.toString());
                return "";
            }
        }
        Log.e("AutoReg_DeviceInfoPool", "getTac: invalid slot id " + i3);
        return "null";
    }

    public static int k(Context context) {
        int iIntValue;
        try {
            StorageManager storageManager = (StorageManager) context.getSystemService("storage");
            iIntValue = ((Integer) storageManager.getClass().getDeclaredMethod("getEUASupportSize", null).invoke(storageManager, new Object[0])).intValue();
        } catch (Exception e3) {
            Log.e("AutoReg_DeviceInfoPool", "getEUASupportSize Error", e3);
            iIntValue = -1;
        }
        int i3 = iIntValue >= 0 ? iIntValue : 0;
        Log.i("AutoReg_DeviceInfoPool", "EUA_SupportSize is " + i3);
        return i3;
    }

    public static b l(Context context) {
        if (f1782h == null) {
            f1782h = new b(context);
        }
        return f1782h;
    }

    public static NetworkRegistrationInfo m(ServiceState serviceState, int i3) {
        try {
            Class cls = Integer.TYPE;
            return (NetworkRegistrationInfo) ServiceState.class.getMethod("getNetworkRegistrationInfo", cls, cls).invoke(serviceState, Integer.valueOf(i3), 1);
        } catch (Exception e3) {
            Log.e("AutoReg_DeviceInfoPool", "getNetworkRegistrationInfo: reflection failed", e3);
            return null;
        }
    }

    public static int q(NetworkRegistrationInfo networkRegistrationInfo) {
        if (networkRegistrationInfo != null && networkRegistrationInfo.isRegistered()) {
            CellIdentity cellIdentity = networkRegistrationInfo.getCellIdentity();
            if (cellIdentity instanceof CellIdentityNr) {
                return ((CellIdentityNr) cellIdentity).getTac();
            }
            if (cellIdentity instanceof CellIdentityLte) {
                return ((CellIdentityLte) cellIdentity).getTac();
            }
        }
        return -1;
    }

    public final k g(String str) {
        for (k kVar : (ArrayDeque) this.f1788f) {
            if (l1.c.h(((r) kVar.f587c.f589b.f1467b).f2670d, str)) {
                return kVar;
            }
        }
        for (k kVar2 : (ArrayDeque) this.f1787e) {
            if (l1.c.h(((r) kVar2.f587c.f589b.f1467b).f2670d, str)) {
                return kVar2;
            }
        }
        return null;
    }

    public final void h(k kVar) {
        Runnable runnable;
        l1.c.w(kVar, "call");
        kVar.f586b.decrementAndGet();
        ArrayDeque arrayDeque = (ArrayDeque) this.f1788f;
        synchronized (this) {
            if (!arrayDeque.remove(kVar)) {
                throw new AssertionError("Call wasn't in-flight!");
            }
            runnable = (Runnable) this.f1785c;
        }
        if (r() || runnable == null) {
            return;
        }
        runnable.run();
    }

    public final String i(int i3) {
        int iP = p(i3);
        if (iP == -1) {
            return "";
        }
        String strF = e.f(this.f1785c.createForSubscriptionId(iP).getDataNetworkType());
        strF.getClass();
        int i4 = 3;
        switch (strF) {
            case "2G":
                i4 = 2;
                break;
            case "3G":
                break;
            case "4G":
                i4 = 4;
                break;
            case "5G":
                i4 = 5;
                break;
            default:
                i4 = 0;
                break;
        }
        Log.d("AutoReg_DeviceInfoPool", "getCdmaNetworkType dds slot id = " + i3 + "; networkTypeClass = " + i4);
        return String.valueOf(i4);
    }

    public final String j(int i3) {
        if (p(i3) == -1) {
            return "";
        }
        if (!c.f(i3)) {
            return "null";
        }
        String strE = e.e(i3);
        c.i("getCellId cellId:" + strE + ", slot:" + i3);
        if (strE == null || strE.equals("-1")) {
            return "null";
        }
        return !"".equals(strE) ? Long.toHexString(Long.parseLong(strE)) : strE;
    }

    public final String n(int i3) {
        StringBuilder sb = new StringBuilder("card");
        sb.append(i3 + 1);
        sb.append(" getSimState is : ");
        TelephonyManager telephonyManager = this.f1785c;
        sb.append(telephonyManager.getSimState(i3));
        c.i(sb.toString());
        if (telephonyManager.getSimState(i3) == 5) {
            String subscriberId = telephonyManager.createForSubscriptionId(p(i3)).getSubscriberId();
            return (subscriberId == null || subscriberId.isEmpty()) ? " " : subscriberId;
        }
        c.i("getSimIMSI: sim" + i3 + " is not ready, imsi is null");
        return null;
    }

    public final String o(int i3) {
        StringBuilder sb;
        String str;
        if (p(i3) != -1) {
            List<UiccCardInfo> uiccCardsInfo = this.f1785c.getUiccCardsInfo();
            if (uiccCardsInfo == null) {
                return null;
            }
            for (UiccCardInfo uiccCardInfo : uiccCardsInfo) {
                if (uiccCardInfo.getSlotIndex() == i3) {
                    String iccId = uiccCardInfo.getIccId();
                    if (iccId == null || !iccId.matches("F+")) {
                        return iccId;
                    }
                    sb = new StringBuilder("getSimIccId: slot ");
                    sb.append(i3);
                    str = " has sanitized ICCID, treating as absent";
                }
            }
            return null;
        }
        sb = new StringBuilder("getSimIccId: slot ");
        sb.append(i3);
        str = " has no valid subscription";
        sb.append(str);
        Log.d("AutoReg_DeviceInfoPool", sb.toString());
        return null;
    }

    public final int p(int i3) {
        int[] subscriptionIds = ((android.telephony.SubscriptionManager) this.f1786d).getSubscriptionIds(i3);
        if (subscriptionIds == null || subscriptionIds.length <= 0) {
            return -1;
        }
        return subscriptionIds[0];
    }

    /* JADX WARN: Code duplicated, block: B:23:0x006b  */
    /* JADX WARN: Code duplicated, block: B:27:0x0078 A[Catch: all -> 0x00e8, TryCatch #1 {, blocks: (B:25:0x0072, B:27:0x0078, B:28:0x00aa), top: B:53:0x0072 }] */
    /* JADX WARN: Code duplicated, block: B:53:0x0072 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:27:0x0078, please report this as an issue */
    public final boolean r() {
        int size;
        int size2;
        int i3;
        k kVar;
        o oVar = h.a;
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            Iterator it = ((ArrayDeque) this.f1787e).iterator();
            l1.c.v(it, "readyAsyncCalls.iterator()");
            while (it.hasNext()) {
                k kVar2 = (k) it.next();
                if (((ArrayDeque) this.f1788f).size() >= this.a) {
                    break;
                }
                if (kVar2.f586b.get() < this.f1784b) {
                    it.remove();
                    kVar2.f586b.incrementAndGet();
                    arrayList.add(kVar2);
                    ((ArrayDeque) this.f1788f).add(kVar2);
                }
            }
            synchronized (this) {
                size = ((ArrayDeque) this.f1788f).size() + ((ArrayDeque) this.f1789g).size();
            }
            size2 = arrayList.size();
            for (i3 = 0; i3 < size2; i3++) {
                kVar = (k) arrayList.get(i3);
                synchronized (this) {
                    if (((ExecutorService) this.f1786d) == null) {
                        TimeUnit timeUnit = TimeUnit.SECONDS;
                        SynchronousQueue synchronousQueue = new SynchronousQueue();
                        String str = h.f2819c + " Dispatcher";
                        l1.c.w(str, "name");
                        this.f1786d = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, timeUnit, synchronousQueue, new f(str, false));
                    }
                    ExecutorService executorService = (ExecutorService) this.f1786d;
                    l1.c.t(executorService);
                }
                kVar.getClass();
                n nVar = kVar.f587c;
                b bVar = nVar.a.a;
                o oVar2 = h.a;
                try {
                    try {
                        executorService.execute(kVar);
                    } catch (RejectedExecutionException e3) {
                        InterruptedIOException interruptedIOException = new InterruptedIOException("executor rejected");
                        interruptedIOException.initCause(e3);
                        nVar.i(interruptedIOException);
                        kVar.a.h(interruptedIOException);
                        nVar.a.a.h(kVar);
                    }
                } catch (Throwable th) {
                    nVar.a.a.h(kVar);
                    throw th;
                }
            }
            return z;
        }
        boolean z2 = size > 0;
        size2 = arrayList.size();
        while (i3 < size2) {
            kVar = (k) arrayList.get(i3);
            synchronized (this) {
                if (((ExecutorService) this.f1786d) == null) {
                    TimeUnit timeUnit2 = TimeUnit.SECONDS;
                    SynchronousQueue synchronousQueue2 = new SynchronousQueue();
                    String str2 = h.f2819c + " Dispatcher";
                    l1.c.w(str2, "name");
                    this.f1786d = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, timeUnit2, synchronousQueue2, new f(str2, false));
                }
                ExecutorService executorService2 = (ExecutorService) this.f1786d;
                l1.c.t(executorService2);
                kVar.getClass();
                n nVar2 = kVar.f587c;
                b bVar2 = nVar2.a.a;
                o oVar3 = h.a;
                executorService2.execute(kVar);
            }
        }
        return z2;
    }

    public final void s(Message message, String str) {
        if (message == null) {
            return;
        }
        if (TextUtils.isEmpty(str) && this.f1785c.getPhoneCount() == 2) {
            str = "FF";
        }
        if (str.equals("null")) {
            str = "";
        }
        Bundle bundle = new Bundle();
        bundle.putString("result", str);
        Object obj = message.obj;
        if (obj instanceof Parcelable) {
            bundle.putParcelable("userobj", (Parcelable) obj);
        }
        message.obj = bundle;
        Messenger messenger = message.replyTo;
        if (messenger != null) {
            try {
                messenger.send(message);
                return;
            } catch (RemoteException e3) {
                Log.w("AutoReg_DeviceInfoPool", "failed to response result", e3);
                return;
            }
        }
        if (message.getTarget() != null) {
            message.sendToTarget();
        } else {
            Log.w("AutoReg_DeviceInfoPool", "can't response the result, replyTo and target are all null!");
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
    public b(Context context) {
        this.a = 0;
        this.f1784b = 0;
        this.f1789g = new i1.c(this, Looper.getMainLooper(), 1);
        this.f1787e = context;
        this.f1788f = PreferenceManager.getDefaultSharedPreferences(context);
        this.f1785c = (TelephonyManager) context.getSystemService("phone");
        this.f1786d = (android.telephony.SubscriptionManager) context.getSystemService("telephony_subscription_service");
    }
}
