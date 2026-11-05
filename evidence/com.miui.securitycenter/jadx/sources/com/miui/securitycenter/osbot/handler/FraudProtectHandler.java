package com.miui.securitycenter.osbot.handler;

import aj.y;
import aj.z;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.miui.common.utils.q1;
import com.miui.earthquakewarning.utils.Utils;
import com.miui.electricrisk.AiGuardSceneService;
import com.miui.electricrisk.AiGuardUtils;
import com.miui.securitycenter.osbot.ToolResultBuilder;
import com.miui.securitycenter.osbot.appfunctions.AppResult;
import java.util.concurrent.Executors;
import miuix.core.util.SystemProperties;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class FraudProtectHandler {
    static final String SWITCH_PREFERENCE_KEY = "ai_guard_function_switch";
    private static final String TAG = "ST_FraudProtectHandler";

    private static AppResult buildToggleResult(String str, boolean z10) {
        return AppResult.success(new ToolResultBuilder().put("featureId", str).put("enabled", z10).build());
    }

    private static boolean checkGuardProviderVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.miui.guardprovider", 128).applicationInfo.metaData.getInt("miui.aiGuardVersion") >= 1;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    private static boolean checkIfGuardProviderCanProject(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getPackageInfo("com.miui.guardprovider", 128).applicationInfo.metaData.getInt("miui.aiGuardVersion") >= 2 && packageManager.checkPermission("android.permission.MANAGE_MEDIA_PROJECTION", "com.miui.guardprovider") == 0 && packageManager.checkPermission("android.permission.FOREGROUND_SERVICE_MEDIA_PROJECTION", "com.miui.guardprovider") == 0 && packageManager.checkPermission("android.permission.CAPTURE_VIDEO_OUTPUT", "com.miui.guardprovider") == 0;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    private static boolean checkServiceAvailable() {
        if (SystemProperties.getBoolean("debug.config.media.video.voip.detect", false)) {
            return true;
        }
        String str = Build.DEVICE;
        return "dada".equals(str) || "haotian".equals(str);
    }

    private static int getCapabilities(Context context) {
        boolean zIsPhoneScamSupported = isPhoneScamSupported(context);
        return isVideoScamSupported(context) ? (zIsPhoneScamSupported ? 1 : 0) | 2 : zIsPhoneScamSupported ? 1 : 0;
    }

    static void grantCTA(final Context context) {
        if (context == null) {
            return;
        }
        gk.r.c(context, true);
        Executors.newSingleThreadExecutor().execute(new Runnable() { // from class: com.miui.securitycenter.osbot.handler.k
            @Override // java.lang.Runnable
            public final void run() {
                Context context2 = context;
                hi.e.b(context2, gk.r.f31419a, y.a(context2));
            }
        });
    }

    public static AppResult handle(Context context, String str, String str2, Boolean bool) {
        boolean zIsVoiceCapable = Utils.isVoiceCapable();
        if (!zIsVoiceCapable) {
            Log.i(TAG, "handle: isSupportCall = " + zIsVoiceCapable);
            return AppResult.error("The device is not supported.");
        }
        if (str == null) {
            return AppResult.error("action is required");
        }
        Context applicationContext = context.getApplicationContext();
        if (str.equals("toggle")) {
            return handleSwitchFraudProtectStatus(applicationContext, str2, bool);
        }
        if (str.equals("get_status")) {
            return handleGetFraudProtectStatus(applicationContext);
        }
        return AppResult.error("unknown action: " + str + ", supported: get_status, toggle");
    }

    private static AppResult handleAiAntifraud(Context context, String str, Boolean bool) {
        if (!AiGuardUtils.isAiGuardSupported(context)) {
            return AppResult.error("AI anti-fraud is not supported on this device.");
        }
        if (!bool.booleanValue()) {
            Settings.Secure.putInt(context.getContentResolver(), SWITCH_PREFERENCE_KEY, 0);
            androidx.preference.g.c(context).edit().putBoolean(SWITCH_PREFERENCE_KEY, false).apply();
            shutdownMonitorService(context);
            shutdownWorkerService(context);
        } else {
            if (!z.D()) {
                Log.w(TAG, "Need to agree to the privacy policy and grant 'microphone' permission to enable it.");
                return AppResult.error("This feature requires your CTA authorization, You can manually access 'Phone Manager - > Privacy & Security - > Security - > Telecom Fraud Protection - >Call Protection' the operate it.");
            }
            if (!AiGuardUtils.isPreRequirementsMet(context)) {
                context.startActivity(new Intent().putExtra("capabilities", getCapabilities(context)).addFlags(268435456).setComponent(ComponentName.unflattenFromString("com.miui.guardprovider/.aiguard.PermissionRequestActivity")));
                Log.w(TAG, "Need to agree to the privacy policy and grant 'microphone' permission to enable it.");
                return AppResult.error("Need to agree to the privacy policy and grant 'microphone' permission to enable it.");
            }
            grantCTA(context);
            Settings.Secure.putInt(context.getContentResolver(), SWITCH_PREFERENCE_KEY, 1);
            androidx.preference.g.c(context).edit().putBoolean(SWITCH_PREFERENCE_KEY, true).apply();
            startMonitorService(context);
        }
        return AppResult.success(new ToolResultBuilder().put("featureId", str).put("enabled", bool.booleanValue()).build());
    }

    private static AppResult handleElectricProtectPhone(Context context, String str, Boolean bool) {
        if (!bool.booleanValue() || (com.miui.electricrisk.m.j() && com.miui.electricrisk.m.k(context))) {
            com.miui.electricrisk.m.v(bool.booleanValue());
            return AppResult.success(new ToolResultBuilder().put("featureId", str).put("enabled", bool.booleanValue()).build());
        }
        try {
            Intent uri = Intent.parseUri("#Intent;component=com.miui.yellowpage/com.miui.yellowpage.activity.SettingActivity;end", 0);
            uri.addFlags(268435456);
            context.startActivity(uri);
            return AppResult.error("Grant the required permissions to be able to identify unknown numbers, the authorization page has been opened for you");
        } catch (Exception e10) {
            Log.e(TAG, "handleElectricProtectPhone error: ", e10);
            return AppResult.error("Grant the required permissions to be able to identify unknown numbers.");
        }
    }

    private static AppResult handleGetFraudProtectStatus(Context context) {
        Log.i(TAG, "handleGetFraudProtectStatus");
        try {
            JSONArray jSONArray = new JSONArray();
            boolean zL = com.miui.electricrisk.m.l(context);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("featureId", "call_stranger_identify");
            jSONObject.put("enabled", zL);
            jSONArray.put(jSONObject);
            if (AiGuardUtils.isAiGuardSupported(context)) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("featureId", "call_ai_antifraud");
                if (AiGuardUtils.isPreRequirementsMet(context)) {
                    jSONObject2.put("enabled", Settings.Secure.getInt(context.getContentResolver(), SWITCH_PREFERENCE_KEY, 0) == 1);
                } else {
                    jSONObject2.put("enabled", false);
                }
                jSONArray.put(jSONObject2);
            }
            boolean zF = com.miui.electricrisk.m.f();
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("featureId", "sms_risk_identify");
            jSONObject3.put("enabled", zF);
            jSONArray.put(jSONObject3);
            boolean zI = com.miui.electricrisk.m.i();
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("featureId", "sms_code_forward_protect");
            jSONObject4.put("enabled", zI);
            jSONArray.put(jSONObject4);
            boolean zB = !mi.b.c().f() ? com.miui.electricrisk.m.b() : mi.b.c().d(context);
            JSONObject jSONObject5 = new JSONObject();
            jSONObject5.put("featureId", "app_risk_warning");
            jSONObject5.put("enabled", zB);
            jSONArray.put(jSONObject5);
            boolean zD = com.miui.electricrisk.m.d();
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("featureId", "app_transfer_warning");
            jSONObject6.put("enabled", zD);
            jSONArray.put(jSONObject6);
            if (com.miui.electricrisk.m.q(context)) {
                boolean zP = com.miui.electricrisk.m.p();
                JSONObject jSONObject7 = new JSONObject();
                jSONObject7.put("featureId", "web_risk_warning");
                jSONObject7.put("enabled", zP);
                jSONArray.put(jSONObject7);
            }
            if (com.miui.electricrisk.m.o()) {
                boolean zN = com.miui.electricrisk.m.n();
                JSONObject jSONObject8 = new JSONObject();
                jSONObject8.put("featureId", "screen_share_protect");
                jSONObject8.put("enabled", zN);
                jSONArray.put(jSONObject8);
            }
            return AppResult.success(new ToolResultBuilder().put("features", jSONArray).build());
        } catch (Exception e10) {
            Log.e(TAG, "handleGetFraudProtectStatus failed", e10);
            return AppResult.error("handleGetFraudProtectStatus failed: " + e10.getMessage());
        }
    }

    static AppResult handleRiskAppWaringChange(Context context, boolean z10) {
        if (!mi.b.c().f()) {
            com.miui.electricrisk.m.r(z10);
        } else if (z10 != mi.b.c().d(context)) {
            if (!z10 && (c5.o.a(context) || c5.o.c(context))) {
                Log.w(TAG, "handleRiskAppWaringChange: calling or Screen sharing");
                return AppResult.error("Security risks detected. Can't turn off now.");
            }
            c5.b.c(context, z10);
        }
        return buildToggleResult("app_risk_warning", z10);
    }

    private static AppResult handleSwitchFraudProtectStatus(Context context, String str, Boolean bool) {
        Log.i(TAG, "handleSwitchFraudProtectStatus featureId: " + str + " enabled: " + bool);
        if (bool == null || TextUtils.isEmpty(str)) {
            return AppResult.error("featureId and enabled is necessary");
        }
        try {
            return switchFraudFeature(context, str, bool.booleanValue());
        } catch (Exception e10) {
            Log.e(TAG, "handleSwitchFraudProtectStatus failed: ", e10);
            return AppResult.error("handleSwitchFraudProtectStatus failed: " + e10.getMessage());
        }
    }

    private static AppResult handleSwitchScreenShareProtection(Context context, boolean z10) {
        if (!com.miui.electricrisk.m.o()) {
            return AppResult.error("Screen Sharing Protection is not supported.");
        }
        com.miui.electricrisk.m.w(z10);
        return buildToggleResult("screen_share_protect", z10);
    }

    private static AppResult handleSwitchWebProtection(Context context, String str, boolean z10) {
        if (!com.miui.electricrisk.m.q(context)) {
            return AppResult.error("Web protection is not supported.");
        }
        if (!"web_risk_warning".equals(str)) {
            return AppResult.error("unknown featureId: " + str + ", supported: web_risk_warning");
        }
        try {
            Intent intent = new Intent("com.android.browser.OPEN_SETTINGS");
            intent.putExtra("current_page_type", 31);
            intent.addFlags(268435456);
            return !q1.Z(context, intent) ? AppResult.error("App isn't installed on your device.") : AppResult.success("Tools are not supported at this time, the page has opened for you.");
        } catch (Exception e10) {
            Log.e(TAG, "handleSwitchWebProtection failed: ", e10);
            return AppResult.error("Tools are not supported at this time, You can manually access 'Phone Manager - > Privacy & Security - > Security - > Telecom Fraud Protection - >Web page protection' the operate it.");
        }
    }

    private static boolean isPhoneScamSupported(Context context) {
        return Build.VERSION.SDK_INT >= 35 && checkGuardProviderVersion(context);
    }

    private static boolean isVideoScamSupported(Context context) {
        return checkServiceAvailable() || checkIfGuardProviderCanProject(context);
    }

    private static void shutdownMonitorService(Context context) {
        context.stopService(new Intent(context, (Class<?>) AiGuardSceneService.class));
    }

    private static void shutdownWorkerService(Context context) {
        context.stopService(new Intent().setComponent(ComponentName.unflattenFromString("com.miui.guardprovider/.aiguard.AiGuardService")));
    }

    private static void startMonitorService(Context context) {
        context.startService(new Intent(context, (Class<?>) AiGuardSceneService.class));
    }

    private static AppResult switchFraudFeature(Context context, String str, boolean z10) {
        Log.i(TAG, "switchFraudFeature featureId: " + str + " enabled: " + z10);
        str.getClass();
        switch (str) {
            case "call_ai_antifraud":
                return handleAiAntifraud(context, str, Boolean.valueOf(z10));
            case "sms_risk_identify":
                com.miui.electricrisk.m.t(z10);
                break;
            case "screen_share_protect":
                return handleSwitchScreenShareProtection(context, z10);
            case "app_transfer_warning":
                com.miui.electricrisk.m.s(z10);
                break;
            case "sms_code_forward_protect":
                com.miui.electricrisk.m.u(z10);
                break;
            case "app_risk_warning":
                return handleRiskAppWaringChange(context, z10);
            case "web_risk_warning":
                return handleSwitchWebProtection(context, str, z10);
            case "call_stranger_identify":
                return handleElectricProtectPhone(context, str, Boolean.valueOf(z10));
            default:
                return AppResult.error("unknown action: " + str + ", supported: call_stranger_identify, call_ai_antifraud, sms_risk_identify, sms_code_forward_protect, app_risk_warning, app_transfer_warning, web_risk_warning, screen_share_protect");
        }
        return buildToggleResult(str, z10);
    }
}
