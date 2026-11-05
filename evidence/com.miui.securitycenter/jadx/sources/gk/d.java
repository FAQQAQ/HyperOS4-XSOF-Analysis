package gk;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.miui.common.utils.f2;
import com.miui.hybrid.accessory.utils.network.Network;
import com.miui.maml.folme.AnimatedProperty;
import com.xiaomi.onetrack.api.ay;
import java.security.MessageDigest;
import miui.os.Build;
import miuix.appcompat.app.floatingactivity.multiapp.MethodCodeHelper;

/* JADX INFO: loaded from: classes3.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f31395a = "d";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final boolean f31396b = l();

    public static String a(Context context) {
        String string;
        try {
            string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception e10) {
            e10.printStackTrace();
            string = null;
        }
        return TextUtils.isEmpty(string) ? "" : string;
    }

    public static CharSequence b(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            return packageInfo != null ? packageInfo.applicationInfo.loadLabel(packageManager) : str;
        } catch (PackageManager.NameNotFoundException e10) {
            e10.printStackTrace();
            return str;
        }
    }

    public static String c(Context context) {
        String deviceId;
        if (Build.IS_INTERNATIONAL_BUILD || f31396b) {
            return "";
        }
        try {
            deviceId = ((TelephonyManager) context.getSystemService(ay.f27518d)).getDeviceId();
        } catch (Exception unused) {
            deviceId = null;
        }
        return deviceId == null ? "" : deviceId;
    }

    public static final String d() {
        if (Build.IS_STABLE_VERSION) {
            return "stable";
        }
        return Build.IS_ALPHA_BUILD ? AnimatedProperty.PROPERTY_NAME_ALPHA : "development";
    }

    public static String e(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return "NA";
        }
        if (activeNetworkInfo.getType() == 1) {
            return "WIFI";
        }
        if (activeNetworkInfo.getType() != 0) {
            return "NA";
        }
        int subtype = activeNetworkInfo.getSubtype();
        if (subtype == 20) {
            return "5G";
        }
        switch (subtype) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return "2G";
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return "3G";
            case 13:
                return "4G";
            default:
                String subtypeName = activeNetworkInfo.getSubtypeName();
                return (subtypeName.equalsIgnoreCase(Network.CHINA_3G_TD_SCDMA) || subtypeName.equalsIgnoreCase(Network.CHINA_3G_WCDMA) || subtypeName.equalsIgnoreCase(Network.CHINA_3G_CDMA2000)) ? "3G" : subtypeName;
        }
    }

    public static String f(PackageInfo packageInfo) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA256");
            messageDigest.update(packageInfo.signatures[0].toByteArray());
            StringBuilder sb2 = new StringBuilder();
            byte[] bArrDigest = messageDigest.digest();
            int length = bArrDigest.length;
            for (int i10 = 0; i10 < length; i10++) {
                if (i10 > 0) {
                    sb2.append(MethodCodeHelper.IDENTITY_INFO_SEPARATOR);
                }
                sb2.append(Integer.toString((bArrDigest[i10] & 255) + 256, 16).substring(1));
            }
            return sb2.toString().toUpperCase();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String g() {
        Application applicationD = com.miui.common.e.d();
        try {
            String str = applicationD.getPackageManager().getPackageInfo(applicationD.getPackageName(), 0).versionName;
            if (str.endsWith("1")) {
                return "dev";
            }
            return (str.endsWith("2") || str.endsWith("3")) ? "stable" : "beta";
        } catch (Exception e10) {
            Log.e(f31395a, "getVersion code fail", e10);
            return "unKnow";
        }
    }

    public static String h(Context context) {
        String simOperator = ((TelephonyManager) context.getSystemService(ay.f27518d)).getSimOperator();
        return TextUtils.isEmpty(simOperator) ? "" : simOperator;
    }

    public static String i() {
        String smallDeviceId = miui.telephony.TelephonyManager.getDefault().getSmallDeviceId();
        return smallDeviceId == null ? "" : smallDeviceId;
    }

    public static boolean j(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 0;
    }

    public static boolean k(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean l() {
        return "1".equals(f2.c("ro.miui.restrict_imei", "")) || "1".equals(f2.c("ro.miui.restrict_imei_p", ""));
    }

    public static boolean m(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getType() == 1;
    }

    public static void n(Context context, int i10) {
        Toast.makeText(context, i10, 0).show();
    }
}
