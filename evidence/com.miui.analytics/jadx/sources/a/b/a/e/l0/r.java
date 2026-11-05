package a.b.a.e.l0;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Class f609a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Method f610b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Boolean f611c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Method f612d = null;
    public static Boolean e = null;
    public static String f = "";

    static {
        try {
            f612d = Class.forName("android.os.SystemProperties").getMethod("get", String.class);
        } catch (Throwable unused) {
        }
        try {
            f609a = Class.forName("miui.os.Build");
        } catch (Throwable unused2) {
        }
        try {
            Method declaredMethod = Class.forName("android.provider.MiuiSettings$Secure").getDeclaredMethod("isUserExperienceProgramEnable", ContentResolver.class);
            f610b = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (Throwable unused3) {
        }
    }

    public static void a(StringBuilder sb, int i) {
        String string = Integer.toString(i);
        for (int i2 = 0; i2 < 2 - string.length(); i2++) {
            sb.append('0');
        }
        sb.append(string);
    }

    public static int b(Context context) {
        try {
            return Settings.Secure.getInt(context.getContentResolver(), "upload_debug_log_pref", 0);
        } catch (Exception unused) {
            return 0;
        }
    }

    public static boolean c(Context context, String str) {
        try {
            int applicationEnabledSetting = context.getPackageManager().getApplicationEnabledSetting(str);
            return applicationEnabledSetting == 0 || applicationEnabledSetting == 1;
        } catch (Throwable th) {
            StringBuilder sbC = a.a.a.a.a.c("getAppStatsEnabled throwable: ");
            sbC.append(th.getMessage());
            p.g("OSUtil", sbC.toString());
            return false;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:15:0x0078 */
    /* JADX DEBUG: Multi-variable search result rejected for r6v14, resolved type: int */
    /* JADX DEBUG: Multi-variable search result rejected for r6v3, resolved type: int */
    /* JADX DEBUG: Multi-variable search result rejected for r6v4, resolved type: int */
    /* JADX DEBUG: Multi-variable search result rejected for r6v5, resolved type: int */
    /* JADX DEBUG: Multi-variable search result rejected for r6v7, resolved type: int */
    /* JADX WARN: Multi-variable type inference failed */
    public static int d(Context context, String str) {
        int iBooleanValue;
        boolean zBooleanValue = false;
        try {
            iBooleanValue = ((Boolean) Class.forName("miui.os.MiuiInit").getMethod("isPreinstalledPackage", String.class).invoke(null, str)).booleanValue();
        } catch (Exception unused) {
            StringBuilder sbC = a.a.a.a.a.c("isPreInstallApp failed, isMiui: ");
            sbC.append(r());
            p.g("OSUtil", sbC.toString());
            iBooleanValue = 0;
        }
        try {
            zBooleanValue = ((Boolean) Class.forName("miui.os.MiuiInit").getMethod("isPreinstalledPAIPackage", String.class).invoke(null, str)).booleanValue();
        } catch (Exception unused2) {
            StringBuilder sbC2 = a.a.a.a.a.c("isPreinstalledPAIPackage failed isMiui: ");
            sbC2.append(r());
            p.g("OSUtil", sbC2.toString());
        }
        int iU = u(context, str);
        if (iBooleanValue == 0 && !zBooleanValue && iU == -1) {
            return -1;
        }
        if (zBooleanValue) {
            iBooleanValue = (iBooleanValue == true ? 1 : 0) | 2;
        }
        return iU == 1 ? iBooleanValue | 4 : iBooleanValue;
    }

    public static String e() {
        Locale locale = Locale.getDefault();
        return locale.getLanguage() + "_" + locale.getCountry();
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static long f(String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = a.b.a.e.e.b().getPackageManager().getPackageInfo(str, 16384);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        } catch (Exception e2) {
            e2.printStackTrace();
            packageInfo = null;
        }
        return packageInfo != null ? packageInfo.lastUpdateTime : System.currentTimeMillis() - 432000000;
    }

    public static String g() {
        return a.b.a.a.a.c.a.G("ro.mi.os.version.incremental", "");
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static int h(Context context) {
        try {
            if ((a.b.a.c.p.b.a.f66b == null || a.b.a.c.p.b.a.f65a == null) ? false : true) {
                return Settings.Secure.getInt(context.getContentResolver(), "allow_oaid_used", 1);
            }
            return -1;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static String i(String str) {
        if (a.b.a.a.a.c.a.w0(str)) {
            if ("IN".equalsIgnoreCase(str)) {
                return "in";
            }
            if (a.f560a.contains(str.toUpperCase(Locale.US))) {
                return "eu";
            }
        }
        return "sg";
    }

    public static String j() {
        try {
            String strG = a.b.a.a.a.c.a.G("ro.miui.region", "");
            if (TextUtils.isEmpty(strG)) {
                strG = a.b.a.a.a.c.a.G("ro.product.locale.region", "");
            }
            if (TextUtils.isEmpty(strG) && Build.VERSION.SDK_INT >= 24) {
                Object objInvoke = Class.forName("android.os.LocaleList").getMethod("getDefault", new Class[0]).invoke(null, new Object[0]);
                Object objInvoke2 = objInvoke.getClass().getMethod("size", new Class[0]).invoke(objInvoke, new Object[0]);
                if ((objInvoke2 instanceof Integer) && ((Integer) objInvoke2).intValue() > 0) {
                    Object objInvoke3 = objInvoke.getClass().getMethod("get", Integer.TYPE).invoke(objInvoke, 0);
                    Object objInvoke4 = objInvoke3.getClass().getMethod("getCountry", new Class[0]).invoke(objInvoke3, new Object[0]);
                    if (objInvoke4 instanceof String) {
                        strG = (String) objInvoke4;
                    }
                }
            }
            if (TextUtils.isEmpty(strG)) {
                strG = Locale.getDefault().getCountry();
            }
            if (!TextUtils.isEmpty(strG)) {
                return strG.trim();
            }
        } catch (Exception e2) {
            p.h("OSUtil", "getRegion Exception: ", e2);
        }
        return "";
    }

    public static String k() {
        if (!TextUtils.isEmpty(f)) {
            return f;
        }
        Class cls = f609a;
        if (cls != null) {
            try {
                if (((Boolean) cls.getField("IS_ALPHA_BUILD").get(null)).booleanValue()) {
                    f = "A";
                    return "A";
                }
                boolean zContains = Build.VERSION.INCREMENTAL.contains(".DEV");
                boolean zContains2 = Build.VERSION.INCREMENTAL.contains(".BETA");
                boolean zBooleanValue = ((Boolean) f609a.getField("IS_DEVELOPMENT_VERSION").get(null)).booleanValue();
                if (((Boolean) f609a.getField("IS_STABLE_VERSION").get(null)).booleanValue()) {
                    f = "S";
                    return "S";
                }
                if (zBooleanValue && zContains2) {
                    f = "B";
                    return "B";
                }
                if (zBooleanValue && zContains) {
                    f = "X";
                    return "X";
                }
                if (zBooleanValue) {
                    f = "D";
                    return "D";
                }
            } catch (Exception e2) {
                String strA = p.a("OSUtil");
                StringBuilder sbC = a.a.a.a.a.c("getRomBuildCode failed: ");
                sbC.append(e2.getMessage());
                Log.e(strA, sbC.toString());
            }
        }
        return f;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static String l() {
        try {
            int offset = TimeZone.getDefault().getOffset(System.currentTimeMillis()) / 60000;
            char c2 = '+';
            if (offset < 0) {
                c2 = '-';
                offset = -offset;
            }
            StringBuilder sb = new StringBuilder(9);
            sb.append("GMT");
            sb.append(c2);
            a(sb, offset / 60);
            sb.append(':');
            a(sb, offset % 60);
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static int m() {
        Integer num;
        Exception e2;
        try {
            Method declaredMethod = Class.forName("android.os.UserHandle").getDeclaredMethod("getUserId", Integer.TYPE);
            declaredMethod.setAccessible(true);
            int iMyUid = Process.myUid();
            num = (Integer) declaredMethod.invoke(null, Integer.valueOf(iMyUid));
            try {
                p.c("OSUtil", String.format(Locale.US, "getUserId, uid:%d, userId:%d", Integer.valueOf(iMyUid), num));
            } catch (Exception e3) {
                e2 = e3;
                Log.e(p.a("OSUtil"), "getUserId exception: ", e2);
            }
        } catch (Exception e4) {
            num = null;
            e2 = e4;
        }
        if (num == null) {
            num = 0;
        }
        return num.intValue();
    }

    public static String n(String str) {
        try {
            return a.b.a.e.e.b().getPackageManager().getPackageInfo(str, 16384).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static boolean o(Context context, String str) {
        try {
            return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        } catch (Exception unused) {
        }
    }

    @TargetApi(17)
    public static boolean p() {
        try {
            int i = Settings.Global.getInt(a.b.a.e.e.b().getContentResolver(), "device_provisioned", 0);
            a.b.a.c.v.i.b("OSUtil", "provisioned: " + i);
            boolean z = i != 0;
            if (!z) {
                if (c.k) {
                    a.b.a.a.a.c.a.m(false, "OSUtil", "Provisioned: " + i);
                } else {
                    p.c("OSUtil", "Provisioned: " + i);
                }
            }
            return z;
        } catch (Exception e2) {
            if (c.k) {
                a.b.a.a.a.c.a.s(false, "OSUtil", "isDeviceProvisioned exception", e2);
            } else {
                p.h("OSUtil", "isDeviceProvisioned exception", e2);
            }
            return true;
        }
    }

    public static boolean q() {
        if (e == null) {
            Class cls = f609a;
            if (cls != null) {
                try {
                    e = (Boolean) cls.getField("IS_INTERNATIONAL_BUILD").get(null);
                } catch (Exception e2) {
                    a.a.a.a.a.g(e2, a.a.a.a.a.c("isInternationalBuild failed: "), "OSUtil");
                    return false;
                }
            } else {
                e = Boolean.FALSE;
            }
        }
        return e.booleanValue();
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static boolean r() {
        Boolean bool = f611c;
        if (bool != null) {
            return bool.booleanValue();
        }
        String strValueOf = null;
        try {
            Method method = f612d;
            if (method != null) {
                strValueOf = String.valueOf(method.invoke(null, "ro.miui.ui.version.code"));
            }
        } catch (Throwable th) {
            a.a.a.a.a.j(th, a.a.a.a.a.c("getProp failed ex: "), "OSUtil");
        }
        f611c = !TextUtils.isEmpty(strValueOf) ? Boolean.TRUE : Boolean.FALSE;
        return f611c.booleanValue();
    }

    public static boolean s() {
        return "2407FRK8EC".equals(Build.MODEL);
    }

    public static boolean t(String str) {
        try {
            return TextUtils.isEmpty(str) || a.f561b.contains(str.toUpperCase(Locale.US));
        } catch (Exception e2) {
            a.a.a.a.a.g(e2, a.a.a.a.a.c("isOTChannelRegion failed: "), "OSUtil");
            return false;
        }
    }

    public static int u(Context context, String str) {
        StringBuilder sbC;
        String message;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            return (packageInfo == null || (packageInfo.applicationInfo.flags & 1) == 0) ? 0 : 1;
        } catch (PackageManager.NameNotFoundException e2) {
            sbC = a.a.a.a.a.c("isSystemApp NameNotFoundException: ");
            message = e2.getMessage();
            sbC.append(message);
            p.g("OSUtil", sbC.toString());
            return -1;
        } catch (Exception e3) {
            sbC = a.a.a.a.a.c("isSystemApp error: ");
            message = e3.getMessage();
            sbC.append(message);
            p.g("OSUtil", sbC.toString());
            return -1;
        }
    }

    public static boolean v(Context context) {
        Method method = f610b;
        if (method == null) {
            return true;
        }
        try {
            return ((Boolean) method.invoke(null, context.getContentResolver())).booleanValue();
        } catch (Exception e2) {
            String strA = p.a("OSUtil");
            StringBuilder sbC = a.a.a.a.a.c("isUserExperiencePlanEnabled failed: ");
            sbC.append(e2.getMessage());
            Log.e(strA, sbC.toString());
            return true;
        }
    }

    public static boolean w(String str) {
        if (p()) {
            return false;
        }
        if (c.k) {
            a.b.a.a.a.c.a.m(false, str, "should not access network or location, not provisioned");
            return true;
        }
        p.c(str, "should not access network or location, not provisioned");
        return true;
    }
}
