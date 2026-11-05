package a.b.a.e.i0;

import a.b.a.c.j;
import a.b.a.e.e0.m;
import a.b.a.e.l0.p;
import a.b.a.e.l0.r;
import a.b.a.e.l0.u;
import a.b.a.e.w;
import a.b.a.e.y;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class c {
    public static volatile c e = null;
    public static boolean f = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public List<String> f477a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List<String> f478b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f479c = 3600000;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f480d = 86400000;

    public c() {
        f = r.q();
        t(null);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void b(String str, Exception exc) {
        if (exc == null || TextUtils.isEmpty(str) || !p(exc)) {
            return;
        }
        if (!str.contains("/track/")) {
            if (str.contains("/api/")) {
                ConcurrentHashMap<String, String> concurrentHashMap = y.f663c;
                y yVar = y.b.f667a;
                yVar.f666b = !yVar.f666b;
                return;
            }
            return;
        }
        ConcurrentHashMap<String, String> concurrentHashMap2 = y.f663c;
        y yVar2 = y.b.f667a;
        yVar2.f665a = !yVar2.f665a;
        if (w.i) {
            a.b.a.a.a.c.a.r("HttpChannelControl", "Connection exception on tracking url, toggled domain. url:" + str, exc);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static c n() {
        if (e == null && e == null) {
            synchronized (c.class) {
                if (e == null) {
                    e = new c();
                }
            }
        }
        return e;
    }

    public static boolean p(Exception exc) {
        return (exc instanceof UnknownHostException) || (exc instanceof ConnectException) || (exc instanceof SocketTimeoutException) || (exc instanceof UnknownServiceException);
    }

    public final void a(String str, a.b.a.e.i0.j.a aVar) {
        Exception exc = aVar.f518b;
        if (exc == null) {
            return;
        }
        b(str, exc);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX WARN: Code duplicated, block: B:14:0x0021  */
    public boolean c(String str, a.b.a.e.i0.j.a aVar) {
        boolean z;
        boolean z2;
        if (aVar.f518b != null && (z = f)) {
            if (z) {
                String strO = o(str);
                if (a.b.a.a.a.c.a.w0(strO) && this.f478b.contains(strO)) {
                    z2 = true;
                } else {
                    z2 = false;
                }
            } else {
                z2 = false;
            }
            if (z2 && !aVar.f520d && p(aVar.f518b)) {
                return true;
            }
        }
        return false;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public void d() {
        try {
            List<String> list = this.f477a;
            if (list != null) {
                list.clear();
            }
            List<String> list2 = this.f478b;
            if (list2 != null) {
                list2.clear();
            }
            this.f479c = 3600000L;
            this.f480d = 86400000L;
            u.m("net_to_okhttp_or_dns_data", "");
        } catch (Exception e2) {
            a.a.a.a.a.h("clearCacheNetPolicy exception:", e2, "HttpChannelControl");
        }
    }

    public boolean e(boolean z, String str) {
        if (!f) {
            return false;
        }
        if (a.b.a.a.a.c.a.w0(str) && z) {
            if (str.equals("U1")) {
                str = "PU1";
            } else if (str.equals("U2")) {
                str = "PU2";
            }
        }
        return a.b.a.a.a.c.a.w0(str) && this.f478b.contains(str);
    }

    public String f(boolean z, String str) {
        String strF;
        if (TextUtils.isEmpty(str) || s()) {
            return "";
        }
        try {
            if (q("U5") || a.b.a.e.e0.d.b().d()) {
                a.b.a.e.i0.j.a aVarB = a.b.a.e.i0.j.b.l().b(z, str, false);
                if (c("U5", aVarB)) {
                    p.c("HttpChannelControl", "start ip call ,needDnsRequest url:" + str);
                    aVarB = a.b.a.e.i0.j.b.l().b(z, str, true);
                }
                a(str, aVarB);
                strF = aVarB.f517a;
            } else {
                strF = j.i(a.b.a.e.e.b()).f(z, str);
            }
            return strF;
        } catch (Exception e2) {
            a.a.a.a.a.h("doCheckUpdateRequest exception:", e2, "HttpChannelControl");
            return "";
        }
    }

    public byte[] g(boolean z, String str) {
        if (s()) {
            return null;
        }
        try {
            return q("U6") ? a.b.a.e.i0.j.b.l().c(z, str) : j.i(a.b.a.e.e.b()).g(z, str);
        } catch (Exception e2) {
            a.a.a.a.a.h("doDownloadRequest exception:", e2, "HttpChannelControl");
            return null;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public String h(String str, Map<String, String> map, boolean z) {
        String strD;
        String str2;
        String str3 = "";
        if (TextUtils.isEmpty(str) || !h.e().a(o(str)) || s()) {
            return "";
        }
        try {
            if (q(str) || a.b.a.e.e0.d.b().d()) {
                a.b.a.e.i0.j.a aVarD = a.b.a.e.i0.j.b.l().d(str, map, z, false);
                if (c(str, aVarD)) {
                    p.c("HttpChannelControl", "start ip call ,needDnsRequest url:" + str);
                    aVarD = a.b.a.e.i0.j.b.l().d(str, map, z, true);
                }
                a(str, aVarD);
                strD = aVarD.f517a;
            } else {
                String strJ = d.j(map, z);
                if (strJ != null) {
                    str2 = str + "?" + strJ;
                } else {
                    str2 = str;
                }
                strD = d.d("GET", str2, strJ);
            }
            str3 = strD;
            if (!TextUtils.isEmpty(str3)) {
                h.e().d(o(str), new JSONObject(str3));
            }
        } catch (Exception e2) {
            a.a.a.a.a.h("doGet exception:", e2, "HttpChannelControl");
        }
        return str3;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public boolean i(a.b.a.e.c0.e.a aVar) {
        if (TextUtils.isEmpty(aVar.f240b)) {
            p.c("HttpChannelControl", "doGetAdMonitor response true ,monitor or url is null");
            return true;
        }
        if (s()) {
            return false;
        }
        try {
            return q("MDU1") ? a.b.a.e.i0.j.b.l().e(aVar) : d.e(aVar);
        } catch (Exception e2) {
            a.a.a.a.a.h("doGetAdMonitor exception:", e2, "HttpChannelControl");
            return false;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public String j(String str, Map<String, String> map, boolean z) {
        String strD;
        String str2 = "";
        if (TextUtils.isEmpty(str) || !h.e().a(o(str)) || s()) {
            return "";
        }
        try {
            if (q(str) || a.b.a.e.e0.d.b().d()) {
                a.b.a.e.i0.j.b bVarL = a.b.a.e.i0.j.b.l();
                a.b.a.e.i0.j.a aVarA = bVarL.a("POST", str, bVarL.h(map, z), false);
                if (c(str, aVarA)) {
                    p.c("HttpChannelControl", "start ip call ,needDnsRequest url:" + str);
                    a.b.a.e.i0.j.b bVarL2 = a.b.a.e.i0.j.b.l();
                    aVarA = bVarL2.a("POST", str, bVarL2.h(map, z), true);
                }
                a(str, aVarA);
                strD = aVarA.f517a;
            } else {
                a.b.a.g.c.c.d(false, d.k(str), d.i(str), 1, "request_count");
                strD = d.d("POST", str, d.j(map, z));
            }
            str2 = strD;
            if (!TextUtils.isEmpty(str2)) {
                h.e().d(o(str), new JSONObject(str2));
            }
        } catch (Exception e2) {
            a.a.a.a.a.h("doPost exception:", e2, "HttpChannelControl");
        }
        return str2;
    }

    public a.b.a.f.g.c k(String str, byte[] bArr, String str2) {
        if (TextUtils.isEmpty(str) || !h.e().a(o("PU1")) || s()) {
            return null;
        }
        try {
            return q("PU1") ? a.b.a.e.i0.j.b.l().f(str, bArr, str2) : a.b.a.f.g.b.b().a(str, bArr, str2);
        } catch (Exception e2) {
            a.a.a.a.a.h("doPublish exception:", e2, "HttpChannelControl");
            return null;
        }
    }

    public String l(String str, byte[] bArr, boolean z) {
        String strF;
        String str2 = "";
        if (TextUtils.isEmpty(str) || !h.e().a(o(str)) || s()) {
            return "";
        }
        try {
            if (q(str) || a.b.a.e.e0.d.b().d()) {
                boolean zV = str.endsWith("/track/v4") ? v(z) : false;
                a.b.a.e.i0.j.a aVarG = a.b.a.e.i0.j.b.l().g(str, bArr, false, zV);
                if (c(str, aVarG)) {
                    p.c("HttpChannelControl", "start ip call ,needDnsRequest url:" + str);
                    aVarG = a.b.a.e.i0.j.b.l().g(str, bArr, true, zV);
                }
                a(str, aVarG);
                strF = aVarG.f517a;
            } else {
                strF = d.f(str, bArr, z);
            }
            str2 = strF;
            if (!TextUtils.isEmpty(str2)) {
                JSONObject jSONObject = new JSONObject(str2);
                h.e().d(o(str), jSONObject);
                a.b.a.g.b.f().b(jSONObject);
            }
        } catch (Exception e2) {
            a.a.a.a.a.h("doUpload ,exception:", e2, "HttpChannelControl");
        }
        return str2;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public String m(String str, byte[] bArr, boolean z) {
        String strH;
        String str2 = "";
        if (TextUtils.isEmpty(str) || !h.e().a(o(str)) || s()) {
            return "";
        }
        try {
            if (r(str, z) || a.b.a.e.e0.d.b().d()) {
                boolean zV = v(true);
                a.b.a.e.i0.j.a aVarG = a.b.a.e.i0.j.b.l().g(str, bArr, false, zV);
                if (c(str, aVarG)) {
                    a.b.a.a.a.c.a.n(z, "HttpChannelControl", "start ip call ,needDnsRequest url:" + str, true);
                    aVarG = a.b.a.e.i0.j.b.l().g(str, bArr, true, zV);
                }
                a(str, aVarG);
                strH = aVarG.f517a;
            } else {
                strH = d.h(str, bArr, z);
            }
            str2 = strH;
            if (!TextUtils.isEmpty(str2)) {
                JSONObject jSONObject = new JSONObject(str2);
                h.e().d(o(str), jSONObject);
                a.b.a.g.b.f().b(jSONObject);
            }
        } catch (Exception e2) {
            p.h("HttpChannelControl", "doUpload exception: ", e2);
            a.b.a.a.a.c.a.w(z, "HttpChannelControl", "doUpload exception: ", e2);
        }
        return str2;
    }

    public final String o(String str) {
        if (str.endsWith("/track/v4")) {
            return "U1";
        }
        if (str.endsWith("/api/v4/detail/config")) {
            return "U2";
        }
        if (str.endsWith("/track/key_get")) {
            return "U3";
        }
        if (str.endsWith("/api/v4/detail/config_common")) {
            return "U4";
        }
        if ("U5".equals(str)) {
            return "U5";
        }
        if ("U6".equals(str)) {
            return "U6";
        }
        if (str.endsWith("/api/v4/detail/d")) {
            return "U7";
        }
        if (str.endsWith("/track/getNonce")) {
            return "U8";
        }
        if (str.endsWith("/track/mt")) {
            return "U9";
        }
        if (str.endsWith("/api/ex/lastUseful")) {
            return "U10";
        }
        if ("PU1".equals(str)) {
            return "PU1";
        }
        if (str.endsWith("/api/v1/token")) {
            return "PU2";
        }
        return "MDU1".equals(str) ? "MDU1" : "";
    }

    public boolean q(String str) {
        String strO = o(str);
        return a.b.a.a.a.c.a.w0(strO) && this.f477a.contains(strO);
    }

    public boolean r(String str, boolean z) {
        String strO = o(str);
        a.b.a.a.a.c.a.n(z, "HttpChannelControl", "isToOkHttp:" + str + " _policyUrl:" + strO + "_toOkList size:" + this.f477a.size(), true);
        return a.b.a.a.a.c.a.w0(strO) && this.f477a.contains(strO);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final boolean s() {
        try {
            Context contextB = a.b.a.e.e.b();
            String simOperator = null;
            if (contextB != null) {
                try {
                    simOperator = ((TelephonyManager) contextB.getSystemService("phone")).getSimOperator();
                } catch (Exception e2) {
                    p.h("HttpChannelControl", "Get MCC/MNC exception", e2);
                }
            }
            if (p.f602a) {
                p.c("HttpChannelControl", "MCC MNC = " + simOperator);
            }
            return simOperator.contains("00101");
        } catch (Exception e3) {
            p.h("HttpChannelControl", "isWhiteCard exception", e3);
            return false;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final void t(String str) {
        try {
            if (!a.b.a.a.a.c.a.w0(str)) {
                str = u.h("net_to_okhttp_or_dns_data", "");
            }
            p.c("HttpChannelControl", "getCacheNetPolicy netPolicy: " + str);
            if (a.b.a.a.a.c.a.w0(str)) {
                JSONObject jSONObject = new JSONObject(str);
                this.f479c = jSONObject.optLong("ut") * 1000;
                this.f480d = jSONObject.optLong("ct") * 1000;
                this.f477a.clear();
                this.f478b.clear();
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("tok");
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    this.f477a.add(jSONArrayOptJSONArray.getString(i));
                }
                JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("tip");
                for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                    this.f478b.add(jSONArrayOptJSONArray2.getString(i2));
                }
            }
        } catch (Exception e2) {
            a.a.a.a.a.h("parseCacheNetPolicy exception:", e2, "HttpChannelControl");
        }
    }

    public String u(String str, String str2, Map<String, String> map) {
        String strL;
        String str3 = "";
        if (TextUtils.isEmpty(str2) || !h.e().a(o(str2)) || s()) {
            return "";
        }
        try {
            if (q(str2) || a.b.a.e.e0.d.b().d()) {
                a.b.a.e.i0.j.a aVarO = a.b.a.e.i0.j.b.l().o(str, str2, map, false);
                if (c(str2, aVarO)) {
                    p.c("HttpChannelControl", "start ip call ,needDnsRequest url:" + str2);
                    aVarO = a.b.a.e.i0.j.b.l().o(str, str2, map, true);
                }
                a(str2, aVarO);
                strL = aVarO.f517a;
            } else {
                strL = d.l(str, str2, map);
            }
            str3 = strL;
            if (!TextUtils.isEmpty(str3)) {
                h.e().d(o(str2), new JSONObject(str3));
            }
        } catch (Exception e2) {
            a.a.a.a.a.h("postGetToken exception:", e2, "HttpChannelControl");
        }
        return str3;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final boolean v(boolean z) {
        boolean zD = m.b.f308a.d();
        StringBuilder sbC = a.a.a.a.a.c("isInternationalBuild: ");
        sbC.append(r.q());
        sbC.append(", hasActiveEvent: ");
        sbC.append(z);
        sbC.append(", shouldRequest: ");
        sbC.append(zD);
        p.c("HttpChannelControl", sbC.toString());
        return r.q() && z && zD;
    }
}
