package a.b.a.e;

import android.text.TextUtils;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class y {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static ConcurrentHashMap<String, String> f663c = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static ConcurrentHashMap<String, ConcurrentHashMap<String, String>> f664d = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile boolean f665a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile boolean f666b = false;

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final y f667a = new y(null);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public y(a aVar) {
        f663c.put("IN", "tracking.india.miui.com");
        f663c.put("RU", "tracking.rus.miui.com");
        try {
            String strH = a.b.a.e.l0.u.h("region_rul", "");
            if (!TextUtils.isEmpty(strH)) {
                g(new JSONObject(strH));
            }
            String strH2 = a.b.a.e.l0.u.h("backup_host_data", "");
            if (TextUtils.isEmpty(strH2)) {
                return;
            }
            f(new JSONObject(strH2));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String a(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final String b(String str, String str2) {
        String str3;
        ConcurrentHashMap<String, String> concurrentHashMap = f664d.get(str);
        if (concurrentHashMap != null) {
            Class cls = a.b.a.e.l0.r.f609a;
            if (a.b.a.a.a.c.a.w0(str2)) {
                str3 = "CN";
                if (!"CN".equalsIgnoreCase(str2)) {
                    str3 = "RU";
                    if (!"RU".equalsIgnoreCase(str2)) {
                        if ("IN".equalsIgnoreCase(str2)) {
                            str3 = "IN";
                        } else {
                            str3 = a.b.a.e.l0.a.f560a.contains(str2.toUpperCase(Locale.US)) ? "EU" : "INTL";
                        }
                    }
                }
            } else {
                str3 = "";
            }
            String str4 = concurrentHashMap.get(str3);
            if (!TextUtils.isEmpty(str4)) {
                return str4;
            }
        }
        if (!TextUtils.equals(str2, "IN")) {
            return null;
        }
        if ("tracking".equals(str)) {
            return "pn-tracking-bak.onetrack.mi.com";
        }
        if ("sdkconfig".equals(str)) {
            return "pn-sdkconfig-bak.onetrack.mi.com";
        }
        return null;
    }

    public final String c() {
        String strJ = a.b.a.e.l0.r.q() ? a.b.a.e.l0.r.j() : "CN";
        if (this.f666b) {
            String strB = b("sdkconfig", strJ);
            if (!TextUtils.isEmpty(strB)) {
                return strB;
            }
        }
        if (!a.b.a.e.l0.r.q()) {
            return "sdkconfig.xiaomi.com";
        }
        if (TextUtils.equals(strJ, "IN")) {
            return "sdkconfig.india.xiaomi.com";
        }
        if (TextUtils.equals(strJ, "RU")) {
            return "sdkconfig.rus.xiaomi.com";
        }
        try {
            return a.b.a.e.l0.a.f560a.contains(strJ.toUpperCase()) ? "sdkconfig.eu.xiaomi.com" : "sdkconfig.intl.xiaomi.com";
        } catch (Exception e) {
            a.b.a.e.l0.p.h("RegionDomainManager", "getSdkConfigHost error: ", e);
            return "sdkconfig.intl.xiaomi.com";
        }
    }

    public final String d() {
        String strJ = a.b.a.e.l0.r.q() ? a.b.a.e.l0.r.j() : "CN";
        if (this.f665a) {
            String strB = b("tracking", strJ);
            if (!TextUtils.isEmpty(strB)) {
                return strB;
            }
        }
        if (!a.b.a.e.l0.r.q()) {
            return "tracking.miui.com";
        }
        String str = f663c.get(strJ);
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            if (a.b.a.e.l0.a.f560a.contains(strJ.toUpperCase())) {
                return "tracking.eu.miui.com";
            }
        } catch (Exception e) {
            a.b.a.e.l0.p.h("RegionDomainManager", "getTrackingHostname error: ", e);
        }
        return "tracking.intl.miui.com";
    }

    public String e() {
        try {
            if (TextUtils.isEmpty(a.b.a.e.l0.u.c())) {
                a.b.a.e.e0.d.b().f();
            }
        } catch (Exception e) {
            a.b.a.e.l0.p.h("RegionDomainManager", "getTrackingUrl error: ", e);
        }
        return a("https://", d(), "/track/v4");
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public synchronized void f(JSONObject jSONObject) {
        try {
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(next);
                if (jSONObjectOptJSONObject != null) {
                    ConcurrentHashMap<String, String> concurrentHashMap = f664d.get(next);
                    if (concurrentHashMap == null) {
                        concurrentHashMap = new ConcurrentHashMap<>();
                        f664d.put(next, concurrentHashMap);
                    }
                    Iterator<String> itKeys2 = jSONObjectOptJSONObject.keys();
                    while (itKeys2.hasNext()) {
                        String next2 = itKeys2.next();
                        String strOptString = jSONObjectOptJSONObject.optString(next2);
                        if (!TextUtils.isEmpty(next2) && !TextUtils.isEmpty(strOptString)) {
                            concurrentHashMap.put(next2, strOptString);
                        }
                    }
                }
            }
            a.b.a.e.l0.u.m("backup_host_data", jSONObject.toString());
        } catch (Exception e) {
            a.b.a.e.l0.p.g("RegionDomainManager", "updateBackupHostMap error: " + e.toString());
        }
        if (a.b.a.e.l0.p.f602a) {
            a.b.a.e.l0.p.c("RegionDomainManager", "backupHostMap:" + f664d.toString());
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public synchronized void g(JSONObject jSONObject) {
        try {
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                String strOptString = jSONObject.optString(next);
                if (!TextUtils.isEmpty(next) && !TextUtils.isEmpty(strOptString)) {
                    f663c.put(next, strOptString);
                }
            }
            a.b.a.e.l0.u.m("region_rul", new JSONObject(f663c).toString());
        } catch (Exception e) {
            a.b.a.e.l0.p.c("RegionDomainManager", "updateHostMap: " + e.toString());
        }
        if (a.b.a.e.l0.p.f602a) {
            a.b.a.e.l0.p.c("RegionDomainManager", "merge config:" + new JSONObject(f663c).toString());
        }
    }
}
