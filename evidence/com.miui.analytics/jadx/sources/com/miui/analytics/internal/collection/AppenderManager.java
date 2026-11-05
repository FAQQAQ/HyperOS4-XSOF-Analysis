package com.miui.analytics.internal.collection;

import a.b.a.a.a.c.a;
import a.b.a.c.q.c;
import a.b.a.c.v.h;
import a.b.a.c.v.i;
import a.b.a.c.v.m;
import a.b.a.c.v.n;
import a.b.a.e.l0.p;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AppenderManager {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static AppenderManager f1265d;
    public static final HashMap<String, String> e = new HashMap<String, String>() { // from class: com.miui.analytics.internal.collection.AppenderManager.1
        {
            put("com.baidu.input_mi", "c2b0b497d0389e6de1505e7fd8f4d539");
            put("com.example.analyticsdemo", "443477abb7aebcc8ca41230297fd5c9c");
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f1266a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c f1267b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Map<String, Long> f1268c = new ConcurrentHashMap();

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public AppenderManager(Context context) {
        this.f1266a = a.I(context);
        if (c.r == null) {
            synchronized (c.class) {
                if (c.r == null) {
                    c.r = new c(context.getApplicationContext());
                }
            }
        }
        this.f1267b = c.r;
        m mVar = new m(this.f1266a, "co");
        if (mVar.b("cleared_appender", false)) {
            return;
        }
        a.x(new a.b.a.c.p.a(this, mVar));
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX WARN: Code duplicated, block: B:20:0x005d  */
    /* JADX WARN: Code duplicated, block: B:22:0x0064 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:23:0x0066  */
    /* JADX WARN: Code duplicated, block: B:24:0x0068 A[Catch: Exception -> 0x011b, TryCatch #1 {Exception -> 0x011b, blocks: (B:3:0x0010, B:5:0x001c, B:8:0x002d, B:18:0x0057, B:21:0x005f, B:25:0x006c, B:27:0x0074, B:29:0x0085, B:31:0x00c1, B:34:0x00c8, B:42:0x010c, B:40:0x00ff, B:44:0x0115, B:24:0x0068, B:11:0x0042, B:13:0x004a, B:7:0x0024, B:35:0x00f0), top: B:51:0x0010, inners: #0 }] */
    /* JADX WARN: Code duplicated, block: B:27:0x0074 A[Catch: Exception -> 0x011b, TryCatch #1 {Exception -> 0x011b, blocks: (B:3:0x0010, B:5:0x001c, B:8:0x002d, B:18:0x0057, B:21:0x005f, B:25:0x006c, B:27:0x0074, B:29:0x0085, B:31:0x00c1, B:34:0x00c8, B:42:0x010c, B:40:0x00ff, B:44:0x0115, B:24:0x0068, B:11:0x0042, B:13:0x004a, B:7:0x0024, B:35:0x00f0), top: B:51:0x0010, inners: #0 }] */
    /* JADX WARN: Code duplicated, block: B:28:0x0083 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:29:0x0085 A[Catch: Exception -> 0x011b, TryCatch #1 {Exception -> 0x011b, blocks: (B:3:0x0010, B:5:0x001c, B:8:0x002d, B:18:0x0057, B:21:0x005f, B:25:0x006c, B:27:0x0074, B:29:0x0085, B:31:0x00c1, B:34:0x00c8, B:42:0x010c, B:40:0x00ff, B:44:0x0115, B:24:0x0068, B:11:0x0042, B:13:0x004a, B:7:0x0024, B:35:0x00f0), top: B:51:0x0010, inners: #0 }] */
    /* JADX WARN: Code duplicated, block: B:38:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:42:0x010c A[Catch: Exception -> 0x011b, TryCatch #1 {Exception -> 0x011b, blocks: (B:3:0x0010, B:5:0x001c, B:8:0x002d, B:18:0x0057, B:21:0x005f, B:25:0x006c, B:27:0x0074, B:29:0x0085, B:31:0x00c1, B:34:0x00c8, B:42:0x010c, B:40:0x00ff, B:44:0x0115, B:24:0x0068, B:11:0x0042, B:13:0x004a, B:7:0x0024, B:35:0x00f0), top: B:51:0x0010, inners: #0 }] */
    public Bundle a(String str, Bundle bundle) {
        String str2;
        boolean z;
        long jLongValue;
        String strOptString;
        long jOptLong;
        String strOptString2;
        Object objOptString;
        Object objOptString2;
        JSONObject jSONObject;
        Context context;
        String str3;
        Bundle bundle2 = new Bundle();
        try {
            Long l = this.f1268c.get(str);
            if (l == null || l.longValue() == 0) {
                this.f1268c.put(str, 0L);
            }
            boolean z2 = false;
            if (!a.b.a.c.v.c.d(this.f1266a, str, e.get(str))) {
                if (a.n0(this.f1266a, str) || a.b.a.c.v.c.f(this.f1266a, str)) {
                    str2 = "sys app or platform sign";
                } else {
                    z = false;
                }
                if (z) {
                    if (l == null) {
                        jLongValue = 0;
                    } else {
                        jLongValue = l.longValue();
                    }
                    if (!a.A(jLongValue, 1000L)) {
                        if (bundle != null) {
                            this.f1268c.put(str, Long.valueOf(System.currentTimeMillis()));
                            JSONObject jSONObject2 = new JSONObject(bundle.getString("data"));
                            strOptString = jSONObject2.optString("i");
                            jOptLong = jSONObject2.optLong("t", 0L);
                            strOptString2 = jSONObject2.optString("v");
                            objOptString = jSONObject2.optString("n");
                            objOptString2 = jSONObject2.optString("k");
                            if (!TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2)) {
                                jSONObject = new JSONObject();
                                jSONObject.put("i", strOptString);
                                jSONObject.put("it", jOptLong);
                                jSONObject.put("iv", strOptString2);
                                jSONObject.put("in", objOptString);
                                jSONObject.put("ik", objOptString2);
                                context = this.f1266a;
                                String[] strArr = h.f153a;
                                try {
                                    if (context.getApplicationContext().getPackageManager().getPackageInfo("com.xiaomi.xmsf", 0).versionCode >= 108) {
                                        z2 = true;
                                    }
                                } catch (Exception e2) {
                                    Log.w(p.a("MIUI"), "isSupportXMSFUpload failed:", e2);
                                }
                                if (z2) {
                                    b(this.f1266a, jSONObject);
                                }
                                str3 = "success";
                            }
                            bundle2.putString("result", "input is empty");
                            return bundle2;
                        }
                        return bundle2;
                    }
                    str3 = String.format("frequent, try %dms later", 1000L);
                } else {
                    str3 = "not permitted";
                }
                bundle2.putString("result", str3);
                return bundle2;
            }
            str2 = "signature matched";
            i.b("AppenderManager", str2);
            z = true;
            if (z) {
                str3 = "not permitted";
            } else {
                if (l == null) {
                    jLongValue = 0;
                } else {
                    jLongValue = l.longValue();
                }
                if (!a.A(jLongValue, 1000L)) {
                    if (bundle != null) {
                        this.f1268c.put(str, Long.valueOf(System.currentTimeMillis()));
                        JSONObject jSONObject3 = new JSONObject(bundle.getString("data"));
                        strOptString = jSONObject3.optString("i");
                        jOptLong = jSONObject3.optLong("t", 0L);
                        strOptString2 = jSONObject3.optString("v");
                        objOptString = jSONObject3.optString("n");
                        objOptString2 = jSONObject3.optString("k");
                        if (!TextUtils.isEmpty(strOptString)) {
                            jSONObject = new JSONObject();
                            jSONObject.put("i", strOptString);
                            jSONObject.put("it", jOptLong);
                            jSONObject.put("iv", strOptString2);
                            jSONObject.put("in", objOptString);
                            jSONObject.put("ik", objOptString2);
                            context = this.f1266a;
                            String[] strArr2 = h.f153a;
                            if (context.getApplicationContext().getPackageManager().getPackageInfo("com.xiaomi.xmsf", 0).versionCode >= 108) {
                                z2 = true;
                            }
                            if (z2) {
                                b(this.f1266a, jSONObject);
                            }
                            str3 = "success";
                        }
                        bundle2.putString("result", "input is empty");
                        return bundle2;
                    }
                    return bundle2;
                }
                str3 = String.format("frequent, try %dms later", 1000L);
            }
            bundle2.putString("result", str3);
        } catch (Exception e3) {
            Log.e(p.a("AppenderManager"), "recordInputMaterials exception: ", e3);
            bundle2.putString("result", e3.getMessage());
        }
        return bundle2;
    }

    public final void b(Context context, JSONObject jSONObject) {
        if (jSONObject.length() == 0) {
            return;
        }
        try {
            jSONObject.put("aaid", h.d(this.f1266a));
            jSONObject.put("iced", n.g(context));
            Intent intent = new Intent("com.xiaomi.xmsf.push.XMSF_UPLOAD_ACTIVE");
            intent.setClassName("com.xiaomi.xmsf", "com.xiaomi.xmsf.push.service.receivers.XMSFUploadReceiver");
            intent.putExtra("pkgname", context.getPackageName());
            intent.putExtra("category", "analytics_app_material");
            intent.putExtra("name", "pulse");
            intent.putExtra("data", jSONObject.toString());
            context.sendBroadcast(intent, "com.xiaomi.xmsf.permission.USE_XMSF_UPLOAD");
            p.c("AppenderManager", "upload material via keep alive: " + jSONObject.toString());
        } catch (Exception e2) {
            Log.e(p.a("AppenderManager"), "uploadThroughSystemKeepAliveService exception", e2);
        }
    }
}
