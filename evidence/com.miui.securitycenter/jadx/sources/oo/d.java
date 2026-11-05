package oo;

import android.util.Log;
import com.miui.analytics.AnalyticsUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Map f40006a;

    static {
        HashMap map = new HashMap();
        f40006a = map;
        map.put("xsof_app_auth", "1392.0.0.0.34162");
        map.put("xsof_device_trust", "1392.0.0.0.34167");
        map.put("xsof_device_trust_result", "1392.0.0.0.34210");
        map.put("xsof_simulated_touch", "1392.0.0.0.34168");
        map.put("xsof_simulated_touch_result", "1392.0.0.0.34213");
        map.put("xsof_risk_app", "1392.0.0.0.34182");
        map.put("xsof_risk_app_result", "1392.0.0.0.34214");
        map.put("xsof_fraud_detection_current", "1392.0.0.0.47205");
        map.put("xsof_fraud_detection_current_result", "1392.0.0.0.47206");
        map.put("xsof_fraud_detection_past", "1392.0.0.0.47207");
        map.put("xsof_fraud_detection_past_result", "1392.0.0.0.47208");
        map.put("xsof_fraud_detection_duration", "1392.0.0.0.47209");
        map.put("xsof_fraud_detection_duration_result", "1392.0.0.0.47210");
        map.put("xsof_fraud_detection_score", "1392.0.0.0.47211");
        map.put("xsof_fraud_detection_score_result", "1392.0.0.0.47212");
    }

    public static String a(int i10) {
        switch (i10) {
            case 10:
                return "xsof_device_trust_result";
            case 11:
                return "xsof_risk_app_result";
            case 12:
                return "xsof_simulated_touch_result";
            case 13:
                return "xsof_fraud_detection_current_result";
            case 14:
                return "xsof_fraud_detection_past_result";
            case 15:
                return "xsof_fraud_detection_duration_result";
            case 16:
                return "xsof_fraud_detection_score_result";
            default:
                return "";
        }
    }

    public static String b(int i10, String str, int i11) {
        if (i11 != 0) {
            return String.valueOf(i11);
        }
        switch (i10) {
            case 10:
                return "成功";
            case 11:
                return c(str);
            case 12:
                return d(str);
            case 13:
            case 14:
            case 15:
            case 16:
                return "成功";
            default:
                return "";
        }
    }

    private static String c(String str) {
        int length;
        if (str != null) {
            try {
                length = new JSONArray(str).length();
            } catch (Exception e10) {
                Log.d("XSOFTrackUtils", "getRiskAppCount: " + e10);
                length = -1;
            }
        } else {
            length = -1;
        }
        return String.valueOf(length);
    }

    private static String d(String str) {
        if (str == null) {
            return "";
        }
        try {
            return new JSONObject(str).optString("result");
        } catch (Exception e10) {
            Log.d("XSOFTrackUtils", "getSimulatedResultForTrack: " + e10);
            return "";
        }
    }

    public static void e(String str, String str2, String str3, String str4, int i10) {
        HashMap map = new HashMap();
        map.put("tip", f40006a.get(str));
        map.put("package", str2);
        map.put("app_auth_type", str3);
        map.put("result", str4);
        map.put("code", Integer.valueOf(i10));
        AnalyticsUtil.trackXSOFEvents(str, map);
    }

    public static void f(String str, String str2) {
        HashMap map = new HashMap();
        map.put("tip", f40006a.get(str));
        map.put("package", str2);
        AnalyticsUtil.trackXSOFEvents(str, map);
        b.a("XSOFTrackUtils", "trackXSOFQuery: " + str + ", packageName: " + str2);
    }

    public static void g(String str, String str2, String str3) {
        HashMap map = new HashMap();
        map.put("tip", f40006a.get(str));
        map.put("package", str2);
        map.put("result", str3);
        AnalyticsUtil.trackXSOFEvents(str, map);
        b.a("XSOFTrackUtils", "trackXSOFResult: " + str + ", packageName: " + str2 + ", result: " + str3);
    }
}
