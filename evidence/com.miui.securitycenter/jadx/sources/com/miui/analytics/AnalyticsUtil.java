package com.miui.analytics;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.miui.common.e;
import com.miui.common.utils.v1;
import com.miui.networkassistant.config.Constants;
import java.util.HashMap;
import java.util.Map;
import miui.os.Build;

/* JADX INFO: loaded from: classes2.dex */
public class AnalyticsUtil {
    public static final String DOCK_PLUGIN_ID = "securitycenter_sidebar";
    private static final String TAG = "AnalyticsUtil";

    public static String createEventName(String str, String str2) {
        return (str + "_" + str2).replace(".", "_");
    }

    public static void initMiStats(Context context) {
        StatManager.getInstance().initSdk(context);
        setDataUploadingEnabled(v1.b());
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void recordCalculateEvent(String str, String str2, Map<String, String> map) {
        HashMap map2 = new HashMap();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                map2.put(entry.getKey(), entry.getValue());
            }
        }
        trackEvent(createEventName(str, str2), map2);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void recordCountEvent(String str, String str2, Map<String, String> map) {
        trackEvent(createEventName(str, str2), map);
    }

    public static void recordGameTurboEvent(String str, String str2, Map map) {
        trackGameTurboEvent(createEventName(str, str2), map);
    }

    public static void recordNumericEvent(String str, String str2, long j10) {
        trackEvent(createEventName(str, str2), str2, j10);
    }

    public static void recordPageEnd(String str) {
    }

    public static void recordPageStart(String str) {
    }

    public static void recordStringPropertyEvent(String str, String str2, String str3) {
        trackEvent(createEventName(str, str2), str2, str3);
    }

    public static void setDataUploadingEnabled(boolean z10) {
        if (Build.IS_INTERNATIONAL_BUILD) {
            z10 = Settings.Secure.getInt(e.d().getContentResolver(), Constants.System.UPLOAD_LOG, 0) == 1 && z10;
        }
        StatManager.getInstance().setAccessNetworkEnable(z10);
    }

    public static void trackAppManEvent(String str, Map map) {
        StatManager.getInstance().trackAppMan(str, map);
    }

    public static void trackConversationToolBoxEvent(String str, Map map) {
        if (Build.IS_INTERNATIONAL_BUILD) {
            return;
        }
        StatManager.getInstance().track(str, map, StatConstants.Channel.CONVERSATION_TOOL_BOX);
    }

    public static void trackConversationToolBoxEventWithGlobal(String str, Map map) {
        if (Build.IS_INTERNATIONAL_BUILD) {
            StatManager.getInstance().track(str, map);
        } else {
            StatManager.getInstance().track(str, map, StatConstants.Channel.CONVERSATION_TOOL_BOX);
        }
    }

    public static void trackDockEvent(String str, Map map) {
        if (Build.IS_INTERNATIONAL_BUILD) {
            StatManager.getInstance().track(str, map);
        } else {
            StatManager.getInstance().trackPluginEvent(DOCK_PLUGIN_ID, str, map, StatConstants.Channel.DOCK);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void trackEvent(String str) {
        trackEvent(str, new HashMap());
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    private static void trackEventByOneTrack(String str, String str2, long j10) {
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(str2)) {
            map.put(str2, Long.valueOf(j10));
        }
        trackEvent(str, map);
    }

    public static void trackEventV2(String str, Map map) {
        StatManager.getInstance().trackV2(str, map);
    }

    public static void trackException(Throwable th2) {
    }

    public static void trackGameBoxEvent(String str, Map map) {
        StatManager.getInstance().track(str, map, StatConstants.Channel.GAMEBOX);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void trackGameTurboEvent(String str, Map map, String str2) {
        if (Build.IS_INTERNATIONAL_BUILD) {
            StatManager.getInstance().track(str, map);
        } else {
            StatManager.getInstance().track(str, map, str2);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void trackImeEvent(String str, String str2, String str3) {
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(str2)) {
            map.put(str2, str3);
        }
        StatManager.getInstance().track(str, map, StatConstants.Channel.INPUT_METHOD);
    }

    public static void trackNetworkAssistantEvent(String str, Map map) {
        StatManager.getInstance().track(str, map, StatConstants.Channel.NETWORK_ASSISTANT);
    }

    public static void trackPowerBatteryEvent(String str, Map map) {
        if (Build.IS_INTERNATIONAL_BUILD) {
            StatManager.getInstance().track(str, map);
        } else {
            StatManager.getInstance().track(str, map, StatConstants.Channel.MI_POWER_BATTERY);
        }
    }

    public static void trackStorageEvent(String str, Map<String, Object> map) {
        map.put("data_version", "1.2");
        if (uj.a.f43927a) {
            Log.i("STORAGE_TRACE", "event = " + str + ", params = " + map);
        }
        StatManager.getInstance().track(str, map);
    }

    public static void trackXSOFEvents(String str, Map<String, Object> map) {
        StatManager.getInstance().track(str, map, StatConstants.Channel.MI_SAFETY_OPEN_SERVICE);
    }

    public static void triggerUpload() {
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
    public static void recordCountEvent(String str, String str2) {
        trackEvent(createEventName(str, str2));
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
    public static void trackEvent(String str, String str2, String str3) {
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(str2)) {
            map.put(str2, str3);
        }
        trackEvent(str, map);
    }

    public static void trackGameTurboEvent(String str, Map map) {
        trackGameTurboEvent(str, map, StatConstants.Channel.GAMETURBO);
    }

    private static void trackEventByOneTrack(String str, String str2, double d10) {
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(str2)) {
            map.put(str2, Double.valueOf(d10));
        }
        trackEvent(str, map);
    }

    public static void trackImeEvent(String str, Map map) {
        StatManager.getInstance().track(str, map, StatConstants.Channel.INPUT_METHOD);
    }

    public static void recordCalculateEvent(String str, String str2, long j10, Map<String, String> map) {
        HashMap map2 = new HashMap();
        map2.put(str2, Long.valueOf(j10));
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                map2.put(entry.getKey(), entry.getValue());
            }
        }
        trackEvent(createEventName(str, str2), map2);
    }

    public static void trackEvent(String str, String str2, long j10) {
        trackEventByOneTrack(str, str2, j10);
    }

    public static void trackEvent(String str, String str2, double d10) {
        trackEventByOneTrack(str, str2, d10);
    }

    public static void trackEvent(String str, String str2, String str3, long j10) {
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(str3)) {
            map.put(str3, Long.valueOf(j10));
        }
        trackEvent(str, map);
    }

    public static void recordCalculateEvent(String str, String str2, long j10) {
        trackEvent(createEventName(str, str2), str2, j10);
    }

    public static void trackEvent(String str, Map map) {
        StatManager.getInstance().track(str, map);
    }
}
