package a.b.a.c.v;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Looper;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f165a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f166b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f167c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String f168d = null;
    public static volatile String e = "";
    public static volatile long f;

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f169a;

        public a(Context context) {
            this.f169a = context;
        }

        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
        @Override // java.lang.Runnable
        public void run() {
            n.e(this.f169a);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void a(Context context) {
        try {
            m mVar = new m(context, "co", "_m_rec");
            if (mVar.b("has_deleted_id", false)) {
                return;
            }
            SharedPreferences.Editor editorEdit = mVar.f162a.edit();
            editorEdit.remove("imei");
            editorEdit.apply();
            SharedPreferences.Editor editorEdit2 = mVar.f162a.edit();
            editorEdit2.remove("mac");
            editorEdit2.apply();
            SharedPreferences.Editor editorEdit3 = mVar.f162a.edit();
            editorEdit3.remove("stable_imei");
            editorEdit3.apply();
            SharedPreferences.Editor editorEdit4 = mVar.f162a.edit();
            editorEdit4.putBoolean("has_deleted_id", true);
            editorEdit4.apply();
        } catch (Exception e2) {
            Log.e(i.a("SysUtils"), "deleteDeviceIdInSpFile exception", e2);
        }
    }

    public static String b() {
        return a.b.a.a.a.c.a.F("ro.build.version.sdk", "");
    }

    public static String c() {
        return a.b.a.a.a.c.a.F("ro.build.product", "");
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static String d(Context context) {
        try {
            if (!h.f()) {
                i.b("SysUtils", "Can not get GAID in non-international system.");
                return "";
            }
            if (Looper.myLooper() == Looper.getMainLooper()) {
                i.b("SysUtils", "getGaid in main thread.");
                if (TextUtils.isEmpty(e) || a.b.a.a.a.c.a.A(f, 86400000L)) {
                    a.b.a.a.a.c.a.x(new a(context));
                }
                return TextUtils.isEmpty(e) ? new m(context, "co").e("gaid", "") : e;
            }
            if (!TextUtils.isEmpty(e) && !a.b.a.a.a.c.a.A(f, 86400000L)) {
                return e;
            }
            if (TextUtils.isEmpty(e(context))) {
                return new m(context, "co").e("gaid", "");
            }
            return "";
        } catch (Exception e2) {
            Log.e(i.a("SysUtils"), "getGaid Exception:" + e2);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static synchronized String e(Context context) {
        try {
            String strA = a.b.a.c.v.a.b().a(context);
            if (!TextUtils.isEmpty(strA)) {
                e = strA;
                f = System.currentTimeMillis();
                SharedPreferences sharedPreferences = context.getSharedPreferences("co", 0);
                String str = e;
                if (!TextUtils.isEmpty(str) && str.getBytes().length > 200) {
                    Log.e(i.a("PrefsHelper"), "please don not put large string(> 200) into sharedpreferences, use file instead");
                }
                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                editorEdit.putString("gaid", str);
                editorEdit.apply();
                long j = f;
                SharedPreferences.Editor editorEdit2 = sharedPreferences.edit();
                editorEdit2.putLong("gaid_lt", j);
                editorEdit2.apply();
                return e;
            }
        } catch (Exception e2) {
            Log.e(i.a("SysUtils"), "getGaidInner Exception:" + e2);
        }
        return "";
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static String f(Context context, String str) {
        long jCurrentTimeMillis;
        if (context == null || TextUtils.isEmpty(str)) {
            i.b("SysUtils", "context or appId is null, and appId = " + str);
            return "";
        }
        try {
            if (a.b.a.c.q.e.r == null) {
                synchronized (a.b.a.c.q.e.class) {
                    if (a.b.a.c.q.e.r == null) {
                        a.b.a.c.q.e.r = new a.b.a.c.q.e(context.getApplicationContext());
                    }
                }
            }
            a.b.a.c.q.e eVar = a.b.a.c.q.e.r;
            Object[] objArrN = eVar.N(str);
            String string = "";
            long jLongValue = 0;
            if (objArrN != null) {
                string = (String) objArrN[0];
                jLongValue = ((Long) objArrN[1]).longValue();
            }
            i.b("SysUtils", "appid = " + str + " guid = " + string + " timestamp = " + jLongValue);
            if (TextUtils.isEmpty(string) || System.currentTimeMillis() - jLongValue > 7776000000L) {
                string = UUID.randomUUID().toString();
                i.b("SysUtils", "create new guid: " + string);
                jCurrentTimeMillis = System.currentTimeMillis();
            } else {
                i.b("SysUtils", "update the timestamp of GUID to current time.");
                jCurrentTimeMillis = System.currentTimeMillis();
            }
            eVar.M(str, string, jCurrentTimeMillis);
            return string;
        } catch (Exception e2) {
            Log.e(i.a("SysUtils"), "getGuid exception", e2);
            return "";
        }
    }

    public static synchronized String g(Context context) {
        if (TextUtils.isEmpty(f166b)) {
            try {
                m mVar = new m(context, "co", "_m_rec");
                String strE = mVar.e("imei_md5", null);
                f166b = strE;
                if (TextUtils.isEmpty(strE)) {
                    String strI = i(context);
                    if (TextUtils.isEmpty(strI)) {
                        return "";
                    }
                    String strB = c.b(strI);
                    f166b = strB;
                    if (!TextUtils.isEmpty(strB)) {
                        mVar.f("imei_md5", f166b);
                    }
                }
            } catch (Exception e2) {
                Log.e("SysUtils", "getHashedIMEI exception: ", e2);
            }
        }
        return f166b;
    }

    public static synchronized String h(Context context) {
        if (TextUtils.isEmpty(f168d)) {
            try {
                m mVar = new m(context, "co", "_m_rec");
                String strE = mVar.e("mac_md5", null);
                f168d = strE;
                if (TextUtils.isEmpty(strE)) {
                    String strK = k(context);
                    if (TextUtils.isEmpty(strK)) {
                        return "";
                    }
                    String strB = c.b(strK);
                    f168d = strB;
                    if (!TextUtils.isEmpty(strB)) {
                        mVar.f("mac_md5", f168d);
                    }
                }
            } catch (Exception e2) {
                Log.e("SysUtils", "getHashedMac exception: ", e2);
            }
        }
        return f168d;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX WARN: Code duplicated, block: B:15:0x002d A[Catch: Exception -> 0x003a, TRY_LEAVE, TryCatch #0 {Exception -> 0x003a, blocks: (B:8:0x0013, B:10:0x0019, B:12:0x001f, B:15:0x002d), top: B:27:0x0013, outer: #1 }] */
    public static String i(Context context) {
        String deviceId;
        if (TextUtils.isEmpty(f165a)) {
            try {
                if (h.j(context, "SysUtils")) {
                    return f165a;
                }
                try {
                    List<String> listA = f.a(context);
                    if (listA == null || listA.size() <= 0) {
                        deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                    } else {
                        deviceId = listA.get(0);
                        if (!f.f(deviceId)) {
                            deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                        }
                    }
                } catch (Exception e2) {
                    Log.e(i.a("DeviceIdHelper"), "getImei exception", e2);
                    deviceId = "";
                }
                f165a = deviceId;
                if (TextUtils.isEmpty(deviceId)) {
                    Log.w("SysUtils", "device id is null");
                }
            } catch (Exception e3) {
                Log.e("SysUtils", "getIMEI exception: ", e3);
            }
        }
        return f165a;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static String j() {
        if (!TextUtils.isEmpty(a.b.a.a.a.c.a.F("persist.sys.language", ""))) {
            return a.b.a.a.a.c.a.F("persist.sys.language", "");
        }
        try {
            return Locale.getDefault().toString();
        } catch (Exception e2) {
            i.d("SysUtils", "getLocalLanguage Exception: ", e2);
            return "";
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static String k(Context context) {
        String string;
        if (TextUtils.isEmpty(f167c) && !h.j(context, "SysUtils")) {
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                    while (true) {
                        if (!networkInterfaces.hasMoreElements()) {
                            string = "";
                            break;
                        }
                        NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                        byte[] hardwareAddress = networkInterfaceNextElement.getHardwareAddress();
                        if (hardwareAddress != null && hardwareAddress.length != 0) {
                            StringBuilder sb = new StringBuilder();
                            for (byte b2 : hardwareAddress) {
                                sb.append(String.format("%02x:", Byte.valueOf(b2)));
                            }
                            if (sb.length() > 0) {
                                sb.deleteCharAt(sb.length() - 1);
                            }
                            string = sb.toString();
                            if ("wlan0".equals(networkInterfaceNextElement.getName())) {
                                break;
                            }
                        }
                    }
                } catch (Exception e2) {
                    Log.e(i.a("SysUtils"), "getMacAboveAndroidM exception: ", e2);
                }
                f167c = string;
            }
            if (TextUtils.isEmpty(f167c)) {
                try {
                    f167c = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
                } catch (Exception e3) {
                    i.d("SysUtils", "get mac failed!", e3);
                }
            }
            if (TextUtils.isEmpty(f167c)) {
                Log.w("SysUtils", "mac is null");
            }
            return f167c;
        }
        return f167c;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static String l() {
        boolean z;
        boolean z2;
        if (h.e()) {
            return "A";
        }
        boolean z3 = false;
        try {
            z = miui.os.Build.IS_STABLE_VERSION;
        } catch (Throwable th) {
            i.c("MIUI", "isStableBuild: " + th);
            z = false;
        }
        if (z) {
            return "S";
        }
        try {
            z2 = miui.os.Build.IS_DEVELOPMENT_VERSION && !Build.VERSION.INCREMENTAL.contains(".DEV");
        } catch (Throwable unused) {
        }
        if (z2) {
            return "D";
        }
        try {
            boolean zContains = Build.VERSION.INCREMENTAL.contains(".DEV");
            if (miui.os.Build.IS_DEVELOPMENT_VERSION && zContains) {
                z3 = true;
            }
        } catch (Throwable unused2) {
        }
        return z3 ? "X" : "";
    }

    public static String m() {
        try {
            String strF = a.b.a.a.a.c.a.F("ro.miui.region", "");
            if (TextUtils.isEmpty(strF)) {
                strF = a.b.a.a.a.c.a.F("ro.product.locale.region", "");
            }
            if (TextUtils.isEmpty(strF) && Build.VERSION.SDK_INT >= 24) {
                Object objInvoke = Class.forName("android.os.LocaleList").getMethod("getDefault", new Class[0]).invoke(null, new Object[0]);
                Object objInvoke2 = objInvoke.getClass().getMethod("size", new Class[0]).invoke(objInvoke, new Object[0]);
                if ((objInvoke2 instanceof Integer) && ((Integer) objInvoke2).intValue() > 0) {
                    Object objInvoke3 = objInvoke.getClass().getMethod("get", Integer.TYPE).invoke(objInvoke, 0);
                    Object objInvoke4 = objInvoke3.getClass().getMethod("getCountry", new Class[0]).invoke(objInvoke3, new Object[0]);
                    if (objInvoke4 instanceof String) {
                        strF = (String) objInvoke4;
                    }
                }
            }
            if (TextUtils.isEmpty(strF)) {
                strF = Locale.getDefault().getCountry();
            }
            if (!TextUtils.isEmpty(strF)) {
                return strF.trim();
            }
        } catch (Exception e2) {
            i.d("SysUtils", "getRegion Exception: ", e2);
        }
        return "";
    }

    public static String n(Context context) {
        try {
            String strI = i(context);
            String strK = k(context);
            StringBuffer stringBuffer = new StringBuffer();
            if (!TextUtils.isEmpty(strI)) {
                stringBuffer.append(strI);
            }
            if (!TextUtils.isEmpty(strK)) {
                stringBuffer.append("_");
                stringBuffer.append(strK);
            }
            return c.b(stringBuffer.toString());
        } catch (Exception e2) {
            i.d("SysUtils", "getUUID Exception: ", e2);
            return "";
        }
    }

    public static int o() {
        Integer num = null;
        try {
            Method declaredMethod = Class.forName("android.os.UserHandle").getDeclaredMethod("getUserId", Integer.TYPE);
            declaredMethod.setAccessible(true);
            num = (Integer) declaredMethod.invoke(null, Integer.valueOf(Process.myUid()));
        } catch (Exception e2) {
            Log.e(i.a("SysUtils"), "getUserId exception: ", e2);
        }
        if (num == null) {
            num = 0;
        }
        return num.intValue();
    }

    public static void p(long j) {
        try {
            Thread.sleep(j);
        } catch (Exception e2) {
            i.d("SysUtils", "SysUtils sleep failed!", e2);
        }
    }
}
