package a.b.a.e;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f309a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f310b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f311c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f312d;
    public JSONObject e;
    public long f;

    public static long b() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        String strValueOf = String.valueOf(jCurrentTimeMillis);
        int length = strValueOf.length();
        if (length < 12) {
            return jCurrentTimeMillis;
        }
        int[] iArr = {2, 2, 3, 4, 4, 5, 6, 7, 8, 8, 9};
        int iCharAt = 0;
        for (int i = 0; i < 11; i++) {
            if (iArr[i] < 2 || iArr[i] >= 12) {
                return jCurrentTimeMillis;
            }
            iCharAt += ((length - iArr[i]) - 1) * (strValueOf.charAt((length - iArr[i]) - 1) - '0');
        }
        return ((jCurrentTimeMillis / 100) * 100) + ((long) (iCharAt % 100));
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX WARN: Code duplicated, block: B:21:0x0064  */
    /* JADX WARN: Code duplicated, block: B:24:0x006c  */
    /* JADX WARN: Code duplicated, block: B:26:0x006f  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public JSONObject a() throws JSONException {
        String strH;
        boolean z;
        JSONObject jSONObject = new JSONObject();
        Context contextB = e.b();
        jSONObject.put("event", this.f311c);
        if (!a.b.a.e.l0.r.q()) {
            strH = a.b.a.e.l0.c.h(contextB);
            String str = TextUtils.isEmpty(strH) ? "android_id" : "imei";
            jSONObject.put(str, strH);
            jSONObject.put("oaid", a.b.a.e.l0.m.a(contextB));
            String str2 = this.f311c;
            if (a.b.a.c.v.h.f()) {
                z = false;
            } else {
                z = false;
            }
            if (!z) {
                jSONObject.put("instance_id", a.b.a.e.l0.c.q(contextB));
            }
            jSONObject.put("mfrs", Build.MANUFACTURER);
            jSONObject.put("model", Build.MODEL);
            jSONObject.put("platform", "Android");
            jSONObject.put("miui", Build.VERSION.INCREMENTAL);
            jSONObject.put("mi_os", a.b.a.e.l0.r.g());
            jSONObject.put("build", a.b.a.e.l0.r.k());
            Class cls = a.b.a.e.l0.r.f609a;
            jSONObject.put("os_ver", Build.VERSION.RELEASE);
            jSONObject.put("app_ver", e.f263c);
            jSONObject.put("e_ts", b());
            jSONObject.put("tz", a.b.a.e.l0.r.l());
            jSONObject.put("net", a.b.a.a.a.c.a.W(contextB));
            String strJ = a.b.a.e.l0.r.j();
            a.b.a.e.e0.a.d().k(strJ);
            jSONObject.put("region", strJ);
            jSONObject.put("user_id", a.b.a.e.l0.r.m());
            jSONObject.put("app_id", "001");
            jSONObject.put("pkg", e.f264d);
            jSONObject.put("market_name", a.b.a.e.l0.c.s());
            jSONObject.put("ot_xms_os", a.b.a.e.l0.c.C());
            a.b.a.e.e0.l.b.f306a.b(jSONObject);
            return jSONObject;
        }
        String strG = a.b.a.e.l0.c.g(contextB);
        if (a.b.a.e.l0.p.f602a && !TextUtils.isEmpty(strG)) {
            jSONObject.put("gaid", strG);
        }
        if (!TextUtils.isEmpty(strG) && !TextUtils.equals(this.f311c, "onetrack_active")) {
            jSONObject.put("gaid", strG);
        }
        jSONObject.put("oaid", a.b.a.e.l0.m.a(contextB));
        String str3 = this.f311c;
        if (a.b.a.c.v.h.f() || !TextUtils.equals(str3, "onetrack_active")) {
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            jSONObject.put("instance_id", a.b.a.e.l0.c.q(contextB));
        }
        jSONObject.put("mfrs", Build.MANUFACTURER);
        jSONObject.put("model", Build.MODEL);
        jSONObject.put("platform", "Android");
        jSONObject.put("miui", Build.VERSION.INCREMENTAL);
        jSONObject.put("mi_os", a.b.a.e.l0.r.g());
        jSONObject.put("build", a.b.a.e.l0.r.k());
        Class cls2 = a.b.a.e.l0.r.f609a;
        jSONObject.put("os_ver", Build.VERSION.RELEASE);
        jSONObject.put("app_ver", e.f263c);
        jSONObject.put("e_ts", b());
        jSONObject.put("tz", a.b.a.e.l0.r.l());
        jSONObject.put("net", a.b.a.a.a.c.a.W(contextB));
        String strJ2 = a.b.a.e.l0.r.j();
        a.b.a.e.e0.a.d().k(strJ2);
        jSONObject.put("region", strJ2);
        jSONObject.put("user_id", a.b.a.e.l0.r.m());
        jSONObject.put("app_id", "001");
        jSONObject.put("pkg", e.f264d);
        jSONObject.put("market_name", a.b.a.e.l0.c.s());
        jSONObject.put("ot_xms_os", a.b.a.e.l0.c.C());
        a.b.a.e.e0.l.b.f306a.b(jSONObject);
        return jSONObject;
        strH = a.b.a.e.l0.c.d(contextB);
        jSONObject.put(str, strH);
        jSONObject.put("oaid", a.b.a.e.l0.m.a(contextB));
        String str4 = this.f311c;
        if (a.b.a.c.v.h.f()) {
            z = false;
        } else {
            z = false;
        }
        if (!z) {
            jSONObject.put("instance_id", a.b.a.e.l0.c.q(contextB));
        }
        jSONObject.put("mfrs", Build.MANUFACTURER);
        jSONObject.put("model", Build.MODEL);
        jSONObject.put("platform", "Android");
        jSONObject.put("miui", Build.VERSION.INCREMENTAL);
        jSONObject.put("mi_os", a.b.a.e.l0.r.g());
        jSONObject.put("build", a.b.a.e.l0.r.k());
        Class cls3 = a.b.a.e.l0.r.f609a;
        jSONObject.put("os_ver", Build.VERSION.RELEASE);
        jSONObject.put("app_ver", e.f263c);
        jSONObject.put("e_ts", b());
        jSONObject.put("tz", a.b.a.e.l0.r.l());
        jSONObject.put("net", a.b.a.a.a.c.a.W(contextB));
        String strJ3 = a.b.a.e.l0.r.j();
        a.b.a.e.e0.a.d().k(strJ3);
        jSONObject.put("region", strJ3);
        jSONObject.put("user_id", a.b.a.e.l0.r.m());
        jSONObject.put("app_id", "001");
        jSONObject.put("pkg", e.f264d);
        jSONObject.put("market_name", a.b.a.e.l0.c.s());
        jSONObject.put("ot_xms_os", a.b.a.e.l0.c.C());
        a.b.a.e.e0.l.b.f306a.b(jSONObject);
        return jSONObject;
    }

    public boolean c() {
        try {
            JSONObject jSONObject = this.e;
            return (jSONObject == null || !jSONObject.has("H") || !this.e.has("B") || TextUtils.isEmpty(this.f309a) || TextUtils.isEmpty(this.f310b)) ? false : true;
        } catch (Exception e) {
            a.b.a.e.l0.p.h("Event", "check event isValid error, ", e);
            return false;
        }
    }
}
