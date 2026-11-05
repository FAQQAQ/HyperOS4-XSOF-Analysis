package a.b.a.c.s;

import a.b.a.c.q.f;
import a.b.a.c.v.i;
import a.b.a.c.v.k;
import android.content.Context;
import android.text.TextUtils;
import com.miui.analytics.internal.LogEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static volatile b f92c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f93a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public f f94b;

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public b(Context context) {
        new HashMap();
        this.f93a = a.b.a.a.a.c.a.I(context);
        if (f.r == null) {
            synchronized (f.class) {
                if (f.r == null) {
                    f.r = new f(context.getApplicationContext());
                }
            }
        }
        this.f94b = f.r;
        a.b.a.a.a.c.a.x(new a(this));
    }

    public static synchronized b a(Context context) {
        if (f92c == null) {
            f92c = new b(context);
        }
        return f92c;
    }

    public final String b(Date date) {
        if (date == null) {
            date = new Date(System.currentTimeMillis());
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return (calendar.get(2) + 1) + "-" + calendar.get(5);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final String c() {
        return "http://api.ad.xiaomi.com/video/uploadLog?appKey=PHONE_VIDEO";
    }

    public final void d(String str, boolean z) throws Throwable {
        JSONObject jSONObjectOptJSONObject;
        try {
            b(new Date(System.currentTimeMillis() - 86400000));
            String strB = b(new Date(System.currentTimeMillis()));
            String strP = this.f94b.P(str);
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            if (!TextUtils.isEmpty(strP) && (jSONObjectOptJSONObject = new JSONObject(strP).optJSONObject(strB)) != null) {
                jSONObject2 = jSONObjectOptJSONObject;
            }
            jSONObject.put(strB, jSONObject2);
            if (z) {
                jSONObject2.put("success", jSONObject2.optLong("success", 0L) + 1);
            } else {
                jSONObject2.put("failure", jSONObject2.optLong("failure", 0L) + 1);
            }
            this.f94b.O(str, jSONObject.toString());
        } catch (Exception e) {
            i.d("ReporterService", "handleSentEvent", e);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final String e(LogEvent logEvent) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ck", logEvent.f1251b);
            if (!TextUtils.isEmpty(logEvent.f)) {
                JSONObject jSONObject2 = new JSONObject(logEvent.f);
                String strOptString = jSONObject2.optString("_action_", null);
                if (!TextUtils.isEmpty(strOptString)) {
                    jSONObject.put("action", strOptString);
                }
                String strOptString2 = jSONObject2.optString("_category_", null);
                if (!TextUtils.isEmpty(strOptString2)) {
                    jSONObject.put("category", strOptString2);
                }
                String strOptString3 = jSONObject2.optString("_event_id_", null);
                if (!TextUtils.isEmpty(strOptString3)) {
                    jSONObject.put("event_id", strOptString3);
                }
            }
        } catch (Exception unused) {
        }
        return jSONObject.toString();
    }

    public final String f(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (str.startsWith(c())) {
                str = str.substring(0, c().length());
            }
            jSONObject.put("url", str);
        } catch (Exception unused) {
        }
        return jSONObject.toString();
    }

    public synchronized void g(LogEvent logEvent) {
        if (k(logEvent) && k.d(this.f93a)) {
            d(e(logEvent), false);
        }
    }

    public synchronized void h(LogEvent logEvent) {
        if (k(logEvent)) {
            d(e(logEvent), true);
        }
    }

    public synchronized void i(String str) {
        if (l(str) && k.d(this.f93a)) {
            d(f(str), false);
        }
    }

    public synchronized void j(String str) {
        if (l(str)) {
            d(f(str), true);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final boolean k(LogEvent logEvent) {
        return (logEvent == null || "analytics_bugreport".equals(logEvent.f1251b)) ? false : true;
    }

    public final boolean l(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith(c());
    }
}
