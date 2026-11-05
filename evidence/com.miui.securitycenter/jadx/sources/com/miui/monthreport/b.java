package com.miui.monthreport;

import aj.y;
import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.google.android.exoplayer2.C;
import com.miui.common.utils.f2;
import com.miui.common.utils.h2;
import com.miui.common.utils.j0;
import com.miui.securitycenter.Application;
import com.xiaomi.onetrack.b.e;
import gk.l;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import miui.os.Build;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private long f20222c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private JSONObject f20223d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f20224e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f20225f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private String f20226g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f20227h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private String f20228i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private String f20229j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private String f20230k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private String f20231l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private String f20232m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private String f20233n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private long f20234o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private long f20235p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private String f20236q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private String f20237r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private String f20238s;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private Context f20241v = Application.B();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private List f20221b = new ArrayList();

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private JSONObject f20239t = null;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private String f20240u = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f20220a = 0;

    private b(String str) {
        this.f20237r = str;
    }

    private void a() {
        Calendar calendar = Calendar.getInstance();
        int i10 = calendar.get(1);
        int i11 = calendar.get(2);
        int i12 = calendar.get(5);
        calendar.clear();
        calendar.set(i10, i11, i12);
        this.f20222c = calendar.getTimeInMillis();
        this.f20224e = (Build.IS_INTERNATIONAL_BUILD || l.v()) ? "" : td.b.b(this.f20241v);
        this.f20233n = td.b.a(this.f20241v);
        this.f20238s = y.a(this.f20241v);
        this.f20227h = f2.c("ro.product.device", "unknown");
        this.f20228i = f2.c("ro.carrier.name", "unknown");
        this.f20229j = Build.getRegion();
        this.f20230k = Locale.getDefault().toString();
        this.f20231l = "MIUI-" + android.os.Build.VERSION.INCREMENTAL;
        this.f20232m = j0.i();
        this.f20236q = j0.l(this.f20241v);
    }

    public static b e(String str, Exception exc) throws Throwable {
        b bVar = new b(str);
        bVar.a();
        bVar.l(exc);
        return bVar;
    }

    private JSONObject f() throws Throwable {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArrayH = h();
            if (jSONArrayH != null && jSONArrayH.length() != 0) {
                jSONObject.put("reportId", this.f20226g);
                jSONObject.put("d", this.f20227h);
                jSONObject.put("c", this.f20228i);
                jSONObject.put("r", this.f20229j);
                jSONObject.put(e.f27645a, this.f20230k);
                jSONObject.put("v", this.f20231l);
                jSONObject.put("t", this.f20232m);
                jSONObject.put("a", this.f20233n);
                jSONObject.put(com.xiaomi.onetrack.b.l.f27678a, this.f20238s);
                jSONObject.put("startTime", this.f20234o);
                jSONObject.put("endTime", this.f20235p);
                jSONObject.put("date", this.f20225f);
                jSONObject.put("events", jSONArrayH);
                jSONObject.put("o", this.f20236q);
                jSONObject.put("moduleName", this.f20237r);
                return jSONObject;
            }
            return null;
        } catch (JSONException e10) {
            e10.printStackTrace();
            return null;
        }
    }

    private JSONArray h() throws Throwable {
        List listH = a.f().h(this.f20237r, 500);
        if (listH == null || listH.isEmpty()) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        this.f20234o = Long.MAX_VALUE;
        this.f20235p = 0L;
        this.f20225f = "";
        this.f20221b.clear();
        Iterator it = listH.iterator();
        while (it.hasNext()) {
            JSONObject jSONObjectG = g((a.c) it.next());
            if (jSONObjectG != null) {
                jSONArray.put(jSONObjectG);
            }
        }
        return jSONArray;
    }

    private void l(Exception exc) throws Throwable {
        JSONObject jSONObjectF = f();
        this.f20223d = jSONObjectF;
        if (jSONObjectF == null) {
            return;
        }
        try {
            m(Base64.encodeToString(jSONObjectF.toString().getBytes(), 0));
            JSONObject jSONObject = new JSONObject();
            this.f20226g = UUID.randomUUID().toString().replace("-", "") + System.currentTimeMillis();
            jSONObject.put(com.xiaomi.onetrack.b.l.f27678a, this.f20238s);
            jSONObject.put("reportId", this.f20226g);
            jSONObject.put("date", this.f20225f);
            jSONObject.put("module", this.f20237r);
            if (exc != null) {
                jSONObject.put("exception", URLEncoder.encode(String.format("class:%s, msg:%s", exc.getClass().getName(), exc.getMessage()), C.UTF8_NAME));
            }
            n(jSONObject);
        } catch (Exception e10) {
            e10.printStackTrace();
        }
    }

    private void m(String str) {
        this.f20240u = str;
    }

    private void n(JSONObject jSONObject) {
        this.f20239t = jSONObject;
    }

    public List b() {
        return this.f20221b;
    }

    public int c() {
        return this.f20220a;
    }

    public String d() {
        return this.f20237r;
    }

    public JSONObject g(a.c cVar) {
        try {
            if (cVar.f20214b > 0 && cVar.f20215c >= 1 && !TextUtils.isEmpty(cVar.f20217e) && !TextUtils.isEmpty(cVar.f20218f)) {
                long j10 = cVar.f20216d;
                if (j10 >= this.f20222c) {
                    return null;
                }
                String strA = h2.a(j10, "yyyyMMdd");
                if (TextUtils.isEmpty(this.f20225f)) {
                    this.f20225f = strA;
                }
                if (!this.f20225f.equals(strA)) {
                    return null;
                }
                long j11 = cVar.f20216d;
                if (j11 < this.f20234o) {
                    this.f20234o = j11;
                }
                if (j11 > this.f20235p) {
                    this.f20235p = j11;
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("eventId", cVar.f20213a);
                jSONObject.put("eventTime", cVar.f20216d);
                jSONObject.put("pkgName", cVar.f20217e);
                jSONObject.put("eventType", cVar.f20214b);
                jSONObject.put("version", cVar.f20215c);
                jSONObject.put("data", new JSONObject(cVar.f20218f.replace("\\", "")));
                this.f20221b.add(cVar.f20213a);
                return jSONObject;
            }
            this.f20221b.add(cVar.f20213a);
            return null;
        } catch (JSONException e10) {
            e10.printStackTrace();
            return null;
        }
    }

    public String i() {
        return this.f20240u;
    }

    public JSONObject j() {
        return this.f20239t;
    }

    public void k() {
        this.f20220a++;
    }

    public String toString() {
        return "Task : reportId :" + this.f20226g + ", moduleName :" + this.f20237r + ", date :" + this.f20225f;
    }
}
