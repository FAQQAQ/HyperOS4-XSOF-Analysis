package com.xiaomi.analytics;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.xiaomi.onetrack.api.ax;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONObject;
import rm.c;
import sm.a;
import sm.b;
import sm.e;
import sm.k;
import sm.m;

/* JADX INFO: loaded from: classes3.dex */
public class Analytics {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static volatile boolean f26284c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static volatile Analytics f26285d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private LoggerFactory f26286a = new LoggerFactory();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f26287b;

    private Analytics(Context context) {
        Context contextA = b.a(context);
        this.f26287b = contextA;
        BaseLogger.d(contextA);
        d();
        c.F(this.f26287b);
        rm.b.a(this.f26287b).b();
        k.a(this.f26287b);
    }

    private static boolean b(Context context) {
        boolean zB = e.b(b.c(context, context.getPackageName()));
        Log.d(a.a("Analytics"), String.format("%s is platform signatures : %b", context.getPackageName(), Boolean.valueOf(zB)));
        return zB;
    }

    private static boolean c(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        return (applicationInfo == null || (applicationInfo.flags & 1) == 0) ? false : true;
    }

    private void d() {
        new Tracker("");
    }

    public static synchronized Analytics getInstance(Context context) {
        try {
            if (f26285d == null) {
                f26285d = new Analytics(context);
            }
        } catch (Throwable th2) {
            throw th2;
        }
        return f26285d;
    }

    public static boolean isUpdateEnable() {
        return f26284c;
    }

    public static void setUpdateEnable(boolean z10) {
        f26284c = z10;
    }

    public static void setUseSystemAnalyticsOnly(Context context) {
        if (!tm.c.o(context)) {
            Log.e(a.a("Analytics"), "system analytics is not exist.");
            return;
        }
        Log.d(a.a("Analytics"), "use system analytics only");
        c.c0();
        setUpdateEnable(false);
    }

    public static void trackSystem(Context context, String str, Action action) throws Exception {
        if (!c(context) && !b(context)) {
            throw new IllegalArgumentException("App is not allowed to use this method to track event, except system or platform signed apps. Use getTracker instead.");
        }
        Intent intent = new Intent();
        intent.setClassName(ax.f27499a, "com.miui.analytics.EventService");
        if (str == null) {
            str = "";
        }
        intent.putExtra("key", str);
        intent.putExtra("content", action.g().toString());
        intent.putExtra("extra", action.h().toString());
        if (context.getApplicationContext() != null) {
            intent.putExtra("appid", context.getPackageName());
        }
        if (action instanceof AdAction) {
            intent.putExtra("type", LogEvent.LogType.TYPE_AD.value());
        } else {
            intent.putExtra("type", LogEvent.LogType.TYPE_EVENT.value());
        }
        context.startService(intent);
    }

    public void addJavascriptInterface(WebView webView, String str) {
        webView.addJavascriptInterface(this, str);
    }

    public void deleteAllEvents() {
        tm.a aVarC = c.F(this.f26287b).C();
        if (aVarC != null) {
            aVarC.deleteAllEvents(this.f26287b.getPackageName());
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public String getClientExtraSync(String str) throws TimeoutException {
        return getClientExtraSync(str, 5000);
    }

    public Tracker getTracker(String str) {
        return (Tracker) this.f26286a.getLogger(Tracker.class, str);
    }

    public void setDebugOn(boolean z10) {
        a.f42732a = z10;
        tm.a aVarC = c.F(this.f26287b).C();
        if (aVarC != null) {
            aVarC.setDebugOn(z10);
        }
    }

    public void setDontUseSystemAnalytics(boolean z10) {
        c.F(this.f26287b).Z(z10);
    }

    public void setPolicyConfiguration(PolicyConfiguration policyConfiguration) {
        c.F(this.f26287b).b0(policyConfiguration);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    @JavascriptInterface
    public void trackAdAction(String str, String str2, String str3) {
        try {
            AdAction adActionNewAdAction = Actions.newAdAction(str2);
            try {
                adActionNewAdAction.d(new JSONObject(str3));
            } catch (Exception unused) {
            }
            getTracker(str).track(adActionNewAdAction);
        } catch (Exception e10) {
            a.d("Analytics", "JavascriptInterface trackAdAction exception:", e10);
        }
    }

    @JavascriptInterface
    public void trackCustomAction(String str, String str2) {
        try {
            CustomAction customActionNewCustomAction = Actions.newCustomAction();
            try {
                customActionNewCustomAction.d(new JSONObject(str2));
            } catch (Exception unused) {
            }
            getTracker(str).track(customActionNewCustomAction);
        } catch (Exception e10) {
            a.d("Analytics", "JavascriptInterface trackCustomAction exception:", e10);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    @JavascriptInterface
    public void trackEventAction(String str, String str2, String str3) {
        try {
            EventAction eventActionNewEventAction = Actions.newEventAction(str2);
            try {
                eventActionNewEventAction.d(new JSONObject(str3));
            } catch (Exception unused) {
            }
            getTracker(str).track(eventActionNewEventAction);
        } catch (Exception e10) {
            a.d("Analytics", "JavascriptInterface trackEventAction exception:", e10);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
    public String getClientExtraSync(final String str, final int i10) throws TimeoutException {
        try {
            FutureTask futureTask = new FutureTask(new Callable<String>() { // from class: com.xiaomi.analytics.Analytics.1
                /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
                /* JADX DEBUG: Method merged with bridge method: call()Ljava/lang/Object; */
                @Override // java.util.concurrent.Callable
                public String call() throws Exception {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    while (System.currentTimeMillis() - jCurrentTimeMillis < i10) {
                        tm.a aVarC = c.F(Analytics.this.f26287b).C();
                        if (aVarC != null) {
                            String strB = aVarC.b(str);
                            if (!TextUtils.isEmpty(strB) || aVarC.a(str)) {
                                return strB;
                            }
                        }
                        Thread.sleep(1000L);
                    }
                    return null;
                }
            });
            m.a(futureTask);
            String str2 = (String) futureTask.get(i10, TimeUnit.MILLISECONDS);
            tm.a aVarC = c.F(this.f26287b).C();
            if (aVarC != null && (!TextUtils.isEmpty(str2) || aVarC.a(str))) {
                return str2;
            }
        } catch (Exception unused) {
        }
        throw new TimeoutException();
    }

    @JavascriptInterface
    public void trackAdAction(String str, String str2, String str3, String str4) {
        try {
            AdAction adActionNewAdAction = Actions.newAdAction(str2, str3);
            try {
                adActionNewAdAction.d(new JSONObject(str4));
            } catch (Exception unused) {
            }
            getTracker(str).track(adActionNewAdAction);
        } catch (Exception e10) {
            a.d("Analytics", "JavascriptInterface trackAdAction exception:", e10);
        }
    }

    @JavascriptInterface
    public void trackEventAction(String str, String str2, String str3, String str4) {
        try {
            EventAction eventActionNewEventAction = Actions.newEventAction(str2, str3);
            try {
                eventActionNewEventAction.d(new JSONObject(str4));
            } catch (Exception unused) {
            }
            getTracker(str).track(eventActionNewEventAction);
        } catch (Exception e10) {
            a.d("Analytics", "JavascriptInterface trackEventAction exception:", e10);
        }
    }
}
