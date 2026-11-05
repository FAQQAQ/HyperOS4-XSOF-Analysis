package a.b.a.c.t;

import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f103a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f104b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f105c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public double f106d;
    public String g;
    public h e = null;
    public String f = null;
    public ArrayList<String> h = new ArrayList<>();

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public e(JSONObject jSONObject) {
        this.f103a = 0;
        this.f106d = 1.0d;
        this.g = null;
        if (jSONObject == null) {
            return;
        }
        try {
            a(jSONObject);
            b(jSONObject);
            try {
                this.f106d = Double.parseDouble(jSONObject.optString("sample_rate"));
            } catch (Exception e) {
                Log.e(a.b.a.c.v.i.a("KeyPolicy"), "parseSampleRate exception:", e);
            }
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("log_level");
                if (jSONObject2 != null) {
                    this.f103a = Integer.parseInt(jSONObject2.optString("v"));
                }
            } catch (Exception e2) {
                Log.e(a.b.a.c.v.i.a("KeyPolicy"), "parseLogLevel exception:", e2);
            }
            try {
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("upload_policy");
                if (jSONObjectOptJSONObject != null) {
                    this.f104b = jSONObjectOptJSONObject.optString("v");
                }
            } catch (Exception e3) {
                Log.e(a.b.a.c.v.i.a("KeyPolicy"), "parseUploadPolicy exception:", e3);
            }
            try {
                JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("network");
                if (jSONObjectOptJSONObject2 != null) {
                    this.f105c = jSONObjectOptJSONObject2.optString("v");
                }
            } catch (Exception e4) {
                Log.e(a.b.a.c.v.i.a("KeyPolicy"), "parseNetworkPolicy exception:", e4);
            }
            try {
                this.g = jSONObject.optString("local_config");
            } catch (Exception e5) {
                Log.e(a.b.a.c.v.i.a("KeyPolicy"), "parseLocalConfig exception:", e5);
            }
        } catch (Exception e6) {
            Log.e(a.b.a.c.v.i.a("KeyPolicy"), "KeyPolicy exception:", e6);
        }
    }

    public final void a(JSONObject jSONObject) {
        try {
            this.e = "no".equals(jSONObject.getJSONObject("privacy_policy").getString("v")) ? new a.b.a.c.t.l.d() : new a.b.a.c.t.l.k();
        } catch (Exception e) {
            Log.e(a.b.a.c.v.i.a("KeyPolicy"), "parsePrivacyPolicy exception:", e);
        }
    }

    public final void b(JSONObject jSONObject) {
        try {
            this.f = jSONObject.optString("upload_url", "https://tracking.miui.com/track/v1");
            this.h.clear();
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("upload_url_use_https");
            if (jSONArrayOptJSONArray != null) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                    if (jSONObjectOptJSONObject != null && !TextUtils.isEmpty(jSONObjectOptJSONObject.optString("v"))) {
                        this.h.add(jSONObjectOptJSONObject.getString("v"));
                    }
                }
            }
        } catch (Exception e) {
            Log.e(a.b.a.c.v.i.a("KeyPolicy"), "parseTrackingServer exception:", e);
        }
    }
}
