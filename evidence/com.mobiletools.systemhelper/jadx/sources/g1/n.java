package g1;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f2687a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f2688b = "";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f2689c = "";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f2690d = "";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f2691e = "";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f2692f = "";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f2693g = "";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f2694h = "";

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f2695i = "";

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f2696j = "";

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public String f2697k = "";

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f2698l = "";

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f2699m = "";

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f2700n = "";

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public String f2701o = "";

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f2702p = "";

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f2703q = "";

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String f2704r = "";

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public String f2705s = "";

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public String f2706t = "";

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public String f2707u = "";

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public String f2708v = "";

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public String f2709w = "";

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public String f2710x = "";

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public String f2711y = "";

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public String f2712z = "";
    public String A = "";
    public String B = "";
    public String C = "";
    public String D = "";
    public String E = "";
    public String F = "";
    public String G = "";
    public String H = "";
    public String I = "";
    public String J = "";
    public String K = "";
    public String L = "";
    public String M = "";
    public String N = "";
    public String O = "";

    public n() {
        this.f2687a = "";
        this.f2687a = l.e();
    }

    public JSONObject a() {
        String str;
        String str2 = this.f2688b;
        if (str2 == null || this.f2691e == null || this.f2693g == null || this.f2695i == null || this.f2696j == null || this.f2697k == null || this.f2698l == null || this.f2692f == null) {
            str = "imei/mnc/imsi/tac/cid/pci/networkType/mncch is null!";
        } else {
            if (!str2.isEmpty() && !this.f2691e.isEmpty() && !this.f2693g.isEmpty() && !this.f2695i.isEmpty() && !this.f2696j.isEmpty() && !this.f2697k.isEmpty() && !this.f2698l.isEmpty() && !this.f2692f.isEmpty()) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("Time", this.f2687a);
                    jSONObject.put("IMEI", this.f2688b);
                    jSONObject.put("ICCID", this.f2689c);
                    jSONObject.put("ClientVersion", this.f2690d);
                    jSONObject.put("MNC", this.f2691e);
                    jSONObject.put("IMSI", this.f2693g);
                    jSONObject.put("TAC", this.f2695i);
                    jSONObject.put("CID", this.f2696j);
                    jSONObject.put("PCI", this.f2697k);
                    jSONObject.put("NetworkType", this.f2698l);
                    jSONObject.put("ARFCN", this.f2699m);
                    jSONObject.put("Bands", this.f2700n);
                    jSONObject.put("Bandwidth", this.f2701o);
                    jSONObject.put("CQITableIndex", this.f2702p);
                    jSONObject.put("CQIIndex", this.f2703q);
                    jSONObject.put("RSRP", this.f2704r);
                    jSONObject.put("RSRQ", this.f2706t);
                    jSONObject.put("SINR", this.f2705s);
                    jSONObject.put("CSIRSRP", this.f2707u);
                    jSONObject.put("CSIRSRQ", this.f2708v);
                    jSONObject.put("CSISINR", this.f2709w);
                    jSONObject.put("Uspeed", this.f2710x);
                    jSONObject.put("Dspeed", this.f2711y);
                    jSONObject.put("CallDisconnectCause", this.f2712z);
                    jSONObject.put("DataFailCause", this.A);
                    jSONObject.put("EPSFallback", this.B);
                    jSONObject.put("LONGITUDE", this.D);
                    jSONObject.put("LATITUDE", this.E);
                    jSONObject.put("ALTITUDE", this.F);
                    jSONObject.put("District", this.G);
                    jSONObject.put("SPEED", this.H);
                    jSONObject.put("BEARING", this.I);
                    jSONObject.put("BSSID", this.J);
                    jSONObject.put("SSID", this.K);
                    jSONObject.put("Radius", this.L);
                } catch (JSONException e2) {
                    h.b("SignalInfo", "getJsonObject->" + e2.getMessage());
                }
                return jSONObject;
            }
            h.a("SignalInfo", "imei:" + this.f2688b + "mnc:" + this.f2691e + "imsi:" + this.f2693g + "tac:" + this.f2695i + "cid:" + this.f2696j + "pci:" + this.f2697k + "networkType:" + this.f2698l + "mncch:" + this.f2692f);
            str = "SignalInfo->getJsonObject==null";
        }
        h.b("SignalInfo", str);
        return null;
    }
}
