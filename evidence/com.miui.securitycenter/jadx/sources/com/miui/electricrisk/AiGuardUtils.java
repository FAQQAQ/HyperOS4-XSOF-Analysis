package com.miui.electricrisk;

import android.app.AppOpsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import androidx.annotation.Keep;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.List;
import miuix.core.util.SystemProperties;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
@Keep
public class AiGuardUtils {
    static final String AI_GUARD_PRIVACY_AGREED = "com.miui.guardprovider.AI_GUARD_PRIVACY_AGREEMENT";
    public static final int CAPABILITY_NONE = 0;
    public static final int CAPABILITY_PHONE = 1;
    public static final int CAPABILITY_VIDEO = 2;
    private static final String TAG = "AiGuardUtils";

    @Retention(RetentionPolicy.SOURCE)
    @interface Capability {
    }

    static boolean checkGuardProviderVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.miui.guardprovider", 128).applicationInfo.metaData.getInt("miui.aiGuardVersion") >= 1;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    static boolean checkIfGuardProviderCanProject(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getPackageInfo("com.miui.guardprovider", 128).applicationInfo.metaData.getInt("miui.aiGuardVersion") >= 2 && packageManager.checkPermission("android.permission.MANAGE_MEDIA_PROJECTION", "com.miui.guardprovider") == 0 && packageManager.checkPermission("android.permission.FOREGROUND_SERVICE_MEDIA_PROJECTION", "com.miui.guardprovider") == 0 && packageManager.checkPermission("android.permission.CAPTURE_VIDEO_OUTPUT", "com.miui.guardprovider") == 0;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    static boolean checkServiceAvailable() {
        if (SystemProperties.getBoolean("debug.config.media.video.voip.detect", false)) {
            return true;
        }
        String str = Build.DEVICE;
        return "dada".equals(str) || "haotian".equals(str);
    }

    static int getCapabilities(Context context) {
        boolean zIsPhoneScamSupported = isPhoneScamSupported(context);
        return isVideoScamSupported(context) ? (zIsPhoneScamSupported ? 1 : 0) | 2 : zIsPhoneScamSupported ? 1 : 0;
    }

    public static boolean isAiGuardEnabled(Context context) {
        return androidx.preference.g.c(context).getBoolean("ai_guard_function_switch", false) && isPreRequirementsMet(context);
    }

    public static boolean isAiGuardEnabledForFraud(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), "ai_guard_function_switch", 0) == 1 && isPreRequirementsMet(context);
    }

    public static boolean isAiGuardSupported(Context context) {
        if (!((TelephonyManager) context.getSystemService(TelephonyManager.class)).isVoiceCapable()) {
            Log.i(TAG, "isAiGuardSupported=false, voiceCapable=false");
            return false;
        }
        if (!AiGuardCloudController.loadCachedCloudData(context)) {
            Log.i(TAG, "isAiGuardSupported=false, cloudAvailable=false");
            return false;
        }
        int capabilities = getCapabilities(context);
        boolean z10 = capabilities != 0;
        Log.d(TAG, "isAiGuardSupported=" + z10 + ", capabilities=" + capabilities);
        return z10;
    }

    private static boolean isPhoneScamSupported(Context context) {
        return Build.VERSION.SDK_INT >= 35 && checkGuardProviderVersion(context);
    }

    public static boolean isPreRequirementsMet(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), AI_GUARD_PRIVACY_AGREED, 0) == 1 && (context.getPackageManager().checkPermission("android.permission.RECORD_AUDIO", "com.miui.guardprovider") == 0);
    }

    static boolean isUsingCamera(@NotNull Context context, @NotNull String str) {
        Object obj;
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(AppOpsManager.class);
        try {
            List list = (List) appOpsManager.getClass().getMethod("getOpsForPackage", Integer.TYPE, String.class, String[].class).invoke(appOpsManager, Integer.valueOf(context.getPackageManager().getPackageUid(str, 0)), str, new String[]{"android:camera"});
            if (list != null && !list.isEmpty()) {
                Object obj2 = list.get(0);
                List list2 = (List) obj2.getClass().getMethod("getOps", null).invoke(obj2, null);
                if (list2 == null || list2.isEmpty() || (obj = list2.get(0)) == null) {
                    return false;
                }
                return ((Boolean) obj.getClass().getMethod("isRunning", null).invoke(obj, null)).booleanValue();
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    private static boolean isVideoScamSupported(Context context) {
        return checkServiceAvailable() || checkIfGuardProviderCanProject(context);
    }

    public static void setAiGuardEnable(Context context, boolean z10) {
        androidx.preference.g.c(context).edit().putBoolean("ai_guard_function_switch", z10).commit();
        Settings.Secure.putInt(context.getContentResolver(), AI_GUARD_PRIVACY_AGREED, z10 ? 1 : 0);
        ig.g.f32536a.h("com.miui.guardprovider", Collections.singletonMap("android.permission.RECORD_AUDIO", Integer.valueOf(z10 ? 3 : 0)));
    }

    public static void setPosterActivityState(Context context, boolean z10) {
        context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, (Class<?>) AiGuardPosterActivity.class), z10 ? 1 : 2, 1);
    }
}
