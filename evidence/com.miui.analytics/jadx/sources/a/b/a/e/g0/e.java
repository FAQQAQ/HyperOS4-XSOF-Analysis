package a.b.a.e.g0;

import a.b.a.e.f0.k;
import a.b.a.e.l0.p;
import a.b.a.e.l0.r;
import a.b.a.e.l0.u;
import a.b.a.e.w;
import a.b.a.e.y;
import android.content.Context;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class e {
    public static final /* synthetic */ int e = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public JSONObject f433b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String[] f434c = new String[2];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String[] f435d = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f432a = a.b.a.e.e.b();

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final e f436a = new e(null);
    }

    static {
        new JSONObject();
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public e(a aVar) {
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public synchronized String[] a(boolean z) {
        b(z);
        String[] strArr = this.f434c;
        JSONObject jSONObject = this.f433b;
        strArr[0] = jSONObject != null ? jSONObject.optString("key") : "";
        String[] strArr2 = this.f434c;
        JSONObject jSONObject2 = this.f433b;
        strArr2[1] = jSONObject2 != null ? jSONObject2.optString("sid") : "";
        if (TextUtils.isEmpty(this.f434c[0]) || TextUtils.isEmpty(this.f434c[1])) {
            if (z) {
                a.b.a.a.a.c.a.n(w.i, "SecretKeyManager", "getKeySid key is invalid ! mKeySid: " + Arrays.toString(this.f434c), true);
            } else {
                k.e(false, "SecretKeyManager", "getKeySid key is invalid ! mKeySid: " + Arrays.toString(this.f434c));
            }
        }
        return this.f434c;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final void b(boolean z) {
        if (this.f433b == null) {
            JSONObject jSONObject = null;
            try {
                String strH = u.h("secret_key_data", "");
                if (!TextUtils.isEmpty(strH)) {
                    jSONObject = new JSONObject(a.b.a.e.g0.b.b(this.f432a, strH));
                }
            } catch (Throwable th) {
                if (z) {
                    a.b.a.a.a.c.a.r("SecretKeyManager", "decodeAndroidKeyStore error : ", th);
                } else {
                    p.h("SecretKeyManager", "decodeAndroidKeyStore error : ", th);
                }
                a.b.a.a.a.c.a.y0("up", "s2", th);
            }
            this.f433b = jSONObject;
        }
        if (this.f433b == null) {
            c(z);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public synchronized void c(boolean z) {
        try {
            if (r.w("SecretKeyManager")) {
                return;
            }
            byte[] encoded = a.b.a.e.g0.a.f420a.generateKey().getEncoded();
            String strC = c.c(a.b.a.a.a.c.a.t(encoded));
            if (TextUtils.isEmpty(strC)) {
                return;
            }
            HashMap map = new HashMap();
            map.put("secretKey", strC);
            ConcurrentHashMap<String, String> concurrentHashMap = y.f663c;
            y yVar = y.b.f667a;
            String strJ = a.b.a.e.i0.c.n().j(yVar.a("https://", yVar.d(), "/track/key_get"), map, true);
            if (z) {
                a.b.a.a.a.c.a.n(w.i, "SecretKeyManager", "requestSecretData response: " + strJ, false);
            }
            if (TextUtils.isEmpty(strJ)) {
                p.c("SecretKeyManager", "requestSecretData response isEmpty");
            } else {
                JSONObject jSONObject = new JSONObject(strJ);
                int iOptInt = jSONObject.optInt("code");
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
                if (iOptInt == 0 && jSONObjectOptJSONObject != null) {
                    String strOptString = jSONObjectOptJSONObject.optString("key");
                    String strOptString2 = jSONObjectOptJSONObject.optString("sid");
                    if (!TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2)) {
                        String strC2 = c.c(a.b.a.e.g0.a.b(c.a(strOptString), encoded));
                        if (!TextUtils.isEmpty(strC2)) {
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("key", strC2);
                            jSONObject2.put("sid", strOptString2);
                            this.f433b = jSONObject2;
                            u.m("secret_key_data", a.b.a.e.g0.b.d(this.f432a, jSONObject2.toString()));
                            u.l("last_secret_key_time", System.currentTimeMillis());
                        }
                    }
                }
            }
        } catch (Throwable th) {
            try {
                if (z) {
                    a.b.a.a.a.c.a.r("SecretKeyManager", "requestSecretData error : ", th);
                } else {
                    p.h("SecretKeyManager", "requestSecretData error : ", th);
                }
                a.b.a.a.a.c.a.y0("up", "s3", th);
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }
}
