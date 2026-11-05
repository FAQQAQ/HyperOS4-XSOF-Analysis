package a.b.a.e.e0;

import a.b.a.e.h0.w;
import a.b.a.e.l0.p;
import a.b.a.e.l0.r;
import a.b.a.e.l0.u;
import a.b.a.e.y;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class d {
    public static volatile d f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AtomicBoolean f275a = new AtomicBoolean(false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public AtomicBoolean f276b = new AtomicBoolean(false);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static ConcurrentHashMap<Integer, Integer> f273c = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static ConcurrentHashMap<Integer, Integer> f274d = new ConcurrentHashMap<>();
    public static volatile boolean e = false;
    public static volatile boolean g = false;

    public class a implements Runnable {
        public a() {
        }

        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
        @Override // java.lang.Runnable
        public void run() {
            if (a.b.a.a.a.c.a.m0()) {
                d.this.g();
            }
        }
    }

    public d() {
        j();
    }

    public static d b() {
        if (f == null) {
            synchronized (d.class) {
                if (f == null) {
                    f = new d();
                }
            }
        }
        return f;
    }

    public final int a(int i) {
        int i2 = i - 900000;
        if (i <= 900000) {
            i2 = 900000;
        }
        double dRandom = Math.random();
        double d2 = i2;
        Double.isNaN(d2);
        return 900000 + ((int) (dRandom * d2));
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public Map<Integer, Integer> c() {
        try {
            if (!f273c.isEmpty()) {
                return f273c;
            }
            if (!e) {
                j();
            }
            if (!f273c.isEmpty()) {
                return f273c;
            }
            if (f274d.isEmpty()) {
                f274d.put(1, 1000);
                f274d.put(2, 15000);
                f274d.put(3, 900000);
                f274d.put(4, Integer.valueOf(a(1800000)));
                f274d.put(5, 3600000);
            }
            return f274d;
        } catch (Exception e2) {
            StringBuilder sbC = a.a.a.a.a.c("getLevelIntervalConfig error: ");
            sbC.append(e2.getMessage());
            p.s("CommonConfigUpdater", sbC.toString());
        }
    }

    public boolean d() {
        return this.f276b.get();
    }

    public boolean e() {
        return this.f275a.get();
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX WARN: Code duplicated, block: B:19:0x0049  */
    /* JADX WARN: Code duplicated, block: B:21:0x004c  */
    /* JADX WARN: Code duplicated, block: B:28:0x0080  */
    /* JADX WARN: Code duplicated, block: B:29:0x0084  */
    /* JADX WARN: Code duplicated, block: B:32:? A[RETURN, SYNTHETIC] */
    public void f() {
        String str;
        boolean z = false;
        if (!g) {
            if (!a.b.a.a.a.c.a.m0()) {
                p.g("CommonConfigUpdater", "net is not connected!");
            } else if (a.b.a.b.a.f()) {
                str = "CommonConfigUpdater isCloseOneTrackPullConfig";
            } else if (TextUtils.isEmpty(u.c())) {
                z = true;
            } else {
                long jG = u.g("next_update_common_conf_time", 0L);
                if (jG < System.currentTimeMillis() || jG - System.currentTimeMillis() > 345600000) {
                    z = true;
                }
            }
            if (z) {
                p.c("CommonConfigUpdater", "CommonConfigUpdater Does not meet prerequisites for request");
                return;
            }
            if (TextUtils.isEmpty(u.c()) && a.b.a.e.l0.e.a().c(System.currentTimeMillis())) {
                a.b.a.e.l0.e.a().e("getConfigCommon", 3, new a());
                return;
            } else {
                if (a.b.a.e.l0.e.a().f574b.contains("getConfigCommon")) {
                }
                g();
            }
        }
        str = "repeated request";
        p.c("CommonConfigUpdater", str);
        if (z) {
            p.c("CommonConfigUpdater", "CommonConfigUpdater Does not meet prerequisites for request");
            return;
        }
        if (TextUtils.isEmpty(u.c())) {
        }
        if (a.b.a.e.l0.e.a().f574b.contains("getConfigCommon")) {
            g();
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final void g() {
        String str;
        String strA;
        if (r.w("CommonConfigUpdater") || g) {
            return;
        }
        g = true;
        HashMap map = new HashMap();
        try {
            Context contextB = a.b.a.e.e.b();
            if (r.q()) {
                str = "ga";
                strA = a.b.a.e.l0.c.g(contextB);
            } else {
                str = "oa";
                strA = a.b.a.e.l0.m.a(contextB);
            }
            map.put(str, strA);
            map.put("ov", Build.VERSION.INCREMENTAL);
            map.put("ob", r.k());
            map.put("ii", r.q() ? "1" : "0");
            map.put("sv", a.b.a.e.e.f263c);
            map.put("appVer", a.b.a.e.e.f263c);
            Class cls = r.f609a;
            map.put("av", Build.VERSION.RELEASE);
            map.put("ml", Build.MODEL);
            map.put("re", r.j());
            map.put("platform", "Android");
            String strH = u.h("common_config_hash", "");
            if (!TextUtils.isEmpty(strH)) {
                map.put("hs", strH);
            }
            p.c("CommonConfigUpdater", "params: " + map.toString());
            ConcurrentHashMap<String, String> concurrentHashMap = y.f663c;
            y yVar = y.b.f667a;
            String strA2 = yVar.a("https://", yVar.c(), "/api/v4/detail/config_common");
            String strJ = a.b.a.e.i0.c.n().j(strA2, map, true);
            p.c("CommonConfigUpdater", "url:" + strA2 + " response:" + strJ);
            h(strJ);
        } catch (Exception e2) {
            StringBuilder sbC = a.a.a.a.a.c("requestCloudData error: ");
            sbC.append(e2.getMessage());
            p.g("CommonConfigUpdater", sbC.toString());
            a.b.a.a.a.c.a.y0("tr", "s23", new Exception(map.toString(), e2));
        }
        g = false;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final void h(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt("code") == 0) {
                String strOptString = jSONObject.optString("hash");
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
                if (jSONObjectOptJSONObject != null) {
                    i(jSONObjectOptJSONObject);
                    w.b.f459a.b(jSONObjectOptJSONObject);
                    u.m("common_cloud_data", jSONObjectOptJSONObject.toString());
                    u.m("common_config_hash", strOptString);
                    e = false;
                }
                u.l("next_update_common_conf_time", System.currentTimeMillis() + 172800000 + ((long) new Random().nextInt(172800000)));
            }
        } catch (JSONException e2) {
            StringBuilder sbC = a.a.a.a.a.c("saveCommonCloudData error: ");
            sbC.append(e2.getMessage());
            p.s("CommonConfigUpdater", sbC.toString());
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final void i(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("regionUrl");
        if (jSONObjectOptJSONObject != null) {
            ConcurrentHashMap<String, String> concurrentHashMap = y.f663c;
            y.b.f667a.g(jSONObjectOptJSONObject);
        }
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("regionUrl-bak");
        if (jSONObjectOptJSONObject2 != null) {
            ConcurrentHashMap<String, String> concurrentHashMap2 = y.f663c;
            y.b.f667a.f(jSONObjectOptJSONObject2);
        }
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("levels");
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i);
                int iOptInt = jSONObject2.optInt("l");
                int iOptInt2 = jSONObject2.optInt("t");
                if (iOptInt > 0 && iOptInt2 > 0) {
                    f273c.put(Integer.valueOf(iOptInt), Integer.valueOf(iOptInt2));
                }
            }
            try {
                ConcurrentHashMap<Integer, Integer> concurrentHashMap3 = f273c;
                if (concurrentHashMap3 != null && !concurrentHashMap3.isEmpty() && f273c.containsKey(4)) {
                    f273c.put(4, Integer.valueOf(a(f273c.get(4).intValue())));
                }
            } catch (Exception e2) {
                a.a.a.a.a.f(e2, a.a.a.a.a.c("updateDiscreteValue error: "), "CommonConfigUpdater");
            }
        }
        this.f275a.set(jSONObject.optInt("hpack") == 1);
        this.f276b.set(jSONObject.optInt("h2") == 1);
        p.c("CommonConfigUpdater", "updateCache isHPackEnable = " + this.f275a.get() + ", isH2Enable = " + this.f276b.get());
    }

    public final void j() {
        String strC = u.c();
        if (TextUtils.isEmpty(strC)) {
            e = true;
            return;
        }
        try {
            i(new JSONObject(strC));
        } catch (Exception e2) {
            StringBuilder sbC = a.a.a.a.a.c("updateCacheBySp error: ");
            sbC.append(e2.getMessage());
            p.s("CommonConfigUpdater", sbC.toString());
        }
    }
}
