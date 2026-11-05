package fo;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.miui.common.utils.v1;
import com.xiaomi.onetrack.c.y;
import gk.l;
import j7.i;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Map f30659a = new HashMap();

    private static boolean a(Context context, String str) {
        return System.currentTimeMillis() <= context.getSharedPreferences("xsof_authority_preferences", 0).getLong(str, -1L);
    }

    public static int b(Context context, String str, String str2, String str3) {
        String str4;
        int iC;
        if (f(str)) {
            return 1012;
        }
        if (a(context, str)) {
            str4 = "本地";
            iC = 1010;
        } else {
            Map map = f30659a;
            map.put(str, Boolean.TRUE);
            if (v1.b()) {
                iC = c(context, str, str2, str3);
                str4 = "联网";
            } else {
                str4 = "无网络";
                iC = AnalyticsListener.EVENT_VIDEO_FRAME_PROCESSING_OFFSET;
            }
            map.remove(str);
        }
        oo.d.e("xsof_app_auth", str, str4, iC == 1010 ? "成功" : "失败", iC);
        return iC;
    }

    private static int c(Context context, String str, String str2, String str3) {
        HashMap map = new HashMap(6);
        map.put(y.f27828b, str3);
        map.put("appKey", str2);
        map.put("pkgName", str);
        map.put("timestamp", String.valueOf(System.currentTimeMillis()));
        map.put("appSignature", oo.c.a(context, str));
        return e(context, str, l.D(map, b.a(), "13zt5239-a34f-3ty9-eb73-75456785ns5c", false, false, new i("XSOF")));
    }

    private static long d(long j10) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j11 = 86400000 + jCurrentTimeMillis;
        if (j10 < j11) {
            return j11;
        }
        long j12 = jCurrentTimeMillis + 604800000;
        return j10 > j12 ? j12 : j10;
    }

    private static int e(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return 1005;
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            oo.b.a("AppAuthManager", "Request authority response: " + str2);
            int i10 = jSONObject.getInt("code");
            boolean zOptBoolean = jSONObject.optBoolean("data");
            if (i10 == 200 && zOptBoolean) {
                context.getSharedPreferences("xsof_authority_preferences", 0).edit().putLong(str, d(jSONObject.getLong("timestamp"))).apply();
                return 1010;
            }
            if (i10 == c.QPS_LIMIT_ERROR.b()) {
                return AnalyticsListener.EVENT_DRM_SESSION_RELEASED;
            }
            if (i10 == c.APP_KEY_ERROR.b()) {
                return AnalyticsListener.EVENT_DRM_SESSION_ACQUIRED;
            }
            if (i10 == c.SIGNATURE_ERROR.b()) {
                return AnalyticsListener.EVENT_DRM_KEYS_LOADED;
            }
            if (i10 == c.DATE_ERROR.b()) {
                return 1024;
            }
            if (i10 == c.QUOTA_EXCEEDED_ERROR.b()) {
                return AnalyticsListener.EVENT_DRM_KEYS_RESTORED;
            }
            return i10 == c.SIGN_ERROR.b() ? AnalyticsListener.EVENT_DRM_KEYS_REMOVED : AnalyticsListener.EVENT_PLAYER_RELEASED;
        } catch (Exception unused) {
            Log.e("AppAuthManager", "Request authority data format error!");
            return 1011;
        }
    }

    private static boolean f(String str) {
        Map map = f30659a;
        if (map.containsKey(str)) {
            return Boolean.TRUE.equals(map.get(str));
        }
        return false;
    }
}
