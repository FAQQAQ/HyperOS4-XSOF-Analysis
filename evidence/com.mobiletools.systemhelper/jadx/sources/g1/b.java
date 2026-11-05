package g1;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f2631a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f2632b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private long f2633c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private long f2634d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private long f2635e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private long f2636f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private long f2637g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f2638h = -1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private long f2639i = 0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private long f2640j = 0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private long f2641k = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private long f2642l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private long f2643m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private long f2644n = 0;

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        String strE = l.e();
        try {
            if (this.f2632b == null) {
                this.f2632b = "";
            }
            jSONObject.put("appLabel", this.f2632b);
            if (this.f2631a == null) {
                this.f2631a = "";
            }
            jSONObject.put("pkgName", this.f2631a);
            jSONObject.put("Time", strE);
            jSONObject.put("runTime", this.f2633c + "min");
            jSONObject.put("launchCount", String.valueOf(this.f2634d));
            jSONObject.put("tx1", this.f2639i + "KB");
            jSONObject.put("rx1", this.f2640j + "KB");
            jSONObject.put("tx2", this.f2641k + "KB");
            jSONObject.put("rx2", this.f2642l + "KB");
            jSONObject.put("totalData1", this.f2635e + "KB");
            jSONObject.put("totalData2", this.f2636f + "KB");
            jSONObject.put("totalDataWiFi", this.f2637g + "MB");
        } catch (JSONException e2) {
            h.b("AppInfo", "getJsonObject->" + e2.getMessage());
        }
        return jSONObject;
    }

    public int b() {
        return this.f2638h;
    }

    public void c(String str) {
        this.f2632b = str;
    }

    public void d(long j2) {
        this.f2634d = j2;
    }

    public void e(String str) {
        this.f2631a = str;
    }

    public void f(long j2) {
        this.f2633c = j2;
    }

    public void g(long j2) {
        this.f2640j = j2;
    }

    public void h(long j2) {
        this.f2642l = j2;
    }

    public void i(long j2) {
        this.f2644n = j2;
    }

    public void j(long j2) {
        this.f2635e = j2;
    }

    public void k(long j2) {
        this.f2636f = j2;
    }

    public void l(long j2) {
        this.f2637g = j2;
    }

    public void m(long j2) {
        this.f2639i = j2;
    }

    public void n(long j2) {
        this.f2641k = j2;
    }

    public void o(long j2) {
        this.f2643m = j2;
    }

    public void p(int i2) {
        this.f2638h = i2;
    }
}
