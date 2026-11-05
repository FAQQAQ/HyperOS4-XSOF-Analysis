package f1;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class n extends a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final Pattern f2552d = Pattern.compile("^[a-zA-Z][0-9]{1,3}$");

    private static String A(Context context, String str, String str2) {
        String str3 = null;
        if (context != null) {
            try {
                int identifier = context.getResources().getIdentifier("developer_build", "string", context.getPackageName());
                if (identifier != 0) {
                    String string = context.getString(identifier);
                    if (!TextUtils.isEmpty(string) && str.endsWith(string)) {
                        str3 = string;
                    }
                }
            } catch (Exception unused) {
            }
        }
        if (str3 == null && str.endsWith("Beta")) {
            str3 = "Beta";
        }
        if (str3 == null) {
            return str + "." + str2;
        }
        return str.substring(0, str.length() - str3.length()).trim() + "." + str2 + " " + str3;
    }

    private static boolean B(String str) {
        return !TextUtils.isEmpty(str) && f2552d.matcher(str).matches();
    }

    public static String l(Context context) {
        String strY = y();
        String strZ = z();
        return TextUtils.isEmpty(strZ) ? strY : A(context, strY, strZ);
    }

    private boolean v(Context context) {
        Boolean boolValueOf = Boolean.valueOf(Settings.Global.getInt(context.getContentResolver(), "device_provisioned", 0) == 1);
        g1.h.a("DeviceInfoReaderXiaomi", "isBootWizardCompleted: " + boolValueOf);
        return boolValueOf.booleanValue();
    }

    private static String w(String str, String str2) {
        char lowerCase = Character.toLowerCase(str.charAt(0));
        char lowerCase2 = Character.toLowerCase(str2.charAt(0));
        if (lowerCase != lowerCase2) {
            return lowerCase > lowerCase2 ? str : str2;
        }
        try {
            try {
                return Integer.parseInt(str.substring(1)) >= Integer.parseInt(str2.substring(1)) ? str : str2;
            } catch (Exception e2) {
                g1.h.a("DeviceInfoReaderXiaomi", "ParseInt v2 err: " + e2);
                return str;
            }
        } catch (Exception e3) {
            g1.h.a("DeviceInfoReaderXiaomi", "ParseInt v1 err: " + e3);
            return str2;
        }
    }

    private String x(e1.b bVar) {
        return g1.l.o(bVar.f2445b.equals("2106118C") ? "ro.build.hardware.version" : "ro.miui.cust_hardware");
    }

    private static String y() {
        String strP = g1.l.p("ro.mi.os.version.incremental", "");
        return (TextUtils.isEmpty(strP) || !strP.startsWith("OS") || strP.length() <= 2) ? strP : strP.substring(2);
    }

    private static String z() {
        String strP = g1.l.p("persist.sys.xms.version", "");
        String strP2 = g1.l.p("ro.mi.xms.version.incremental", "");
        boolean zB = B(strP);
        boolean zB2 = B(strP2);
        if (!zB && !zB2) {
            return null;
        }
        if (!zB || zB2) {
            return (zB || !zB2) ? w(strP, strP2) : strP2;
        }
        return strP;
    }

    @Override // f1.a, f1.p.c
    public void b(Context context, e1.b bVar) {
        super.b(context, bVar);
        boolean zV = v(context);
        g1.d.f2649e = zV;
        if (zV) {
            g1.h.a("DeviceInfoReaderXiaomi", "GlobalSetting.isUserPermissionOpen" + g1.d.f2649e);
            bVar.f2447c = x(bVar);
            bVar.f2449d = l(context);
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 33) {
                q(context, bVar);
                return;
            }
            g1.h.b("DeviceInfoReaderXiaomi", "Unsupported API:" + i2);
        }
    }
}
