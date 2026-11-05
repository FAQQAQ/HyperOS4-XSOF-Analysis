package a.b.a.c.u;

import a.b.a.c.v.i;
import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.service.AnalyticsError;
import com.miui.analytics.internal.service.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class g extends f<Void> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public List<LogEvent> f141c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f142d;
    public a.b.a.c.r.a e;
    public boolean f;
    public boolean g;
    public int h;
    public int i;
    public String j;

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public g(Context context, String str, List<LogEvent> list) {
        super(str == null ? "https://tracking.miui.com/track/v1" : str);
        ArrayList arrayList = new ArrayList();
        this.f141c = arrayList;
        this.f = true;
        this.g = false;
        this.h = LogEvent.LogType.TYPE_EVENT.f1261a;
        this.i = LogEvent.IdType.TYPE_DEFAULT.f1257a;
        this.j = "";
        this.f142d = context;
        arrayList.addAll(list);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX WARN: Code duplicated, block: B:27:0x00aa A[Catch: Exception -> 0x0149, TRY_ENTER, TryCatch #0 {Exception -> 0x0149, blocks: (B:6:0x001c, B:8:0x0055, B:13:0x0065, B:15:0x006e, B:17:0x0074, B:19:0x007a, B:27:0x00aa, B:29:0x00d9, B:31:0x010d, B:32:0x0115, B:28:0x00be), top: B:37:0x001c }] */
    /* JADX WARN: Code duplicated, block: B:28:0x00be A[Catch: Exception -> 0x0149, TryCatch #0 {Exception -> 0x0149, blocks: (B:6:0x001c, B:8:0x0055, B:13:0x0065, B:15:0x006e, B:17:0x0074, B:19:0x007a, B:27:0x00aa, B:29:0x00d9, B:31:0x010d, B:32:0x0115, B:28:0x00be), top: B:37:0x001c }] */
    /* JADX WARN: Code duplicated, block: B:31:0x010d A[Catch: Exception -> 0x0149, LOOP:0: B:30:0x010b->B:31:0x010d, LOOP_END, TryCatch #0 {Exception -> 0x0149, blocks: (B:6:0x001c, B:8:0x0055, B:13:0x0065, B:15:0x006e, B:17:0x0074, B:19:0x007a, B:27:0x00aa, B:29:0x00d9, B:31:0x010d, B:32:0x0115, B:28:0x00be), top: B:37:0x001c }] */
    @Override // a.b.a.c.u.f
    public HttpRequest a() {
        String strEncodeToString;
        boolean z;
        List<a.b.a.a.a.a> list;
        a.b.a.a.a.b.a aVar;
        a.b.a.a.a.a[] aVarArr;
        ArrayList arrayList;
        int i;
        byte[] bArrQ;
        a.b.a.c.r.a aVar2 = this.e;
        if (aVar2 != null) {
            aVar2.f(this.f141c);
        }
        HttpRequest httpRequest = new HttpRequest(this.f139a);
        httpRequest.f1282a = HttpRequest.Method.POST;
        try {
            long j = this.f140b;
            String strD = d();
            String strG = g();
            i.b("TrackingServer", String.format("eventType = %s\nurl = %s \npayload = %s", Integer.valueOf(this.h), this.f139a, strG));
            a.b.a.c.t.g gVarG = a.b.a.c.t.g.g(this.f142d);
            a.b.a.c.t.a aVarF = gVarG.f(gVarG.f110a.getPackageName());
            if ((aVarF != null ? aVarF.f95a.contains("zip") : false) && strG.length() > 100000 && (bArrQ = a.b.a.a.a.c.a.q(strG)) != null) {
                strEncodeToString = Base64.encodeToString(bArrQ, 10);
                if (strEncodeToString != null) {
                    float length = strEncodeToString.length() / strG.length();
                    i.b("TrackingServer", "compress = " + length);
                    z = length < 0.3f;
                }
                if (z) {
                    httpRequest.f1284c.add(new a.b.a.a.a.b.a("value", strEncodeToString));
                    list = httpRequest.f1285d;
                    aVar = new a.b.a.a.a.b.a("gzip", "1");
                } else {
                    strEncodeToString = Base64.encodeToString(strG.getBytes(), 10);
                    httpRequest.f1284c.add(new a.b.a.a.a.b.a("value", strEncodeToString));
                    list = httpRequest.f1285d;
                    aVar = new a.b.a.a.a.b.a("gzip", "0");
                }
                list.add(aVar);
                aVarArr = new a.b.a.a.a.a[]{new a.b.a.a.a.b.a("value", strEncodeToString), new a.b.a.a.a.b.a("ts", j + ""), new a.b.a.a.a.b.a("nonce", strD)};
                arrayList = new ArrayList();
                for (i = 0; i < 3; i++) {
                    arrayList.add(aVarArr[i]);
                }
                httpRequest.f1284c.add(new a.b.a.a.a.b.a("sign", f(arrayList)));
                httpRequest.f1284c.add(new a.b.a.a.a.b.a("ts", j + ""));
                httpRequest.f1284c.add(new a.b.a.a.a.b.a("nonce", strD));
                return httpRequest;
            }
            strEncodeToString = "";
            if (z) {
                httpRequest.f1284c.add(new a.b.a.a.a.b.a("value", strEncodeToString));
                list = httpRequest.f1285d;
                aVar = new a.b.a.a.a.b.a("gzip", "1");
            } else {
                strEncodeToString = Base64.encodeToString(strG.getBytes(), 10);
                httpRequest.f1284c.add(new a.b.a.a.a.b.a("value", strEncodeToString));
                list = httpRequest.f1285d;
                aVar = new a.b.a.a.a.b.a("gzip", "0");
            }
            list.add(aVar);
            aVarArr = new a.b.a.a.a.a[]{new a.b.a.a.a.b.a("value", strEncodeToString), new a.b.a.a.a.b.a("ts", j + ""), new a.b.a.a.a.b.a("nonce", strD)};
            arrayList = new ArrayList();
            while (i < 3) {
                arrayList.add(aVarArr[i]);
            }
            httpRequest.f1284c.add(new a.b.a.a.a.b.a("sign", f(arrayList)));
            httpRequest.f1284c.add(new a.b.a.a.a.b.a("ts", j + ""));
            httpRequest.f1284c.add(new a.b.a.a.a.b.a("nonce", strD));
        } catch (Exception e) {
            Log.e(i.a("TrackingServer"), "buildHttpRequest exception: ", e);
        }
        return httpRequest;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    @Override // a.b.a.c.u.f
    public e<Void> e(d dVar) {
        String strC = c(dVar);
        if (TextUtils.isEmpty(strC)) {
            Log.w(i.a("TrackingServer"), "http response is empty");
        } else {
            try {
                if (new JSONObject(strC).optInt("code") == 1) {
                    a.b.a.c.r.a aVar = this.e;
                    if (aVar != null) {
                        aVar.b(true, this.f141c);
                    }
                    return new e<>((Object) null);
                }
            } catch (Exception e) {
                Log.d(i.a("TrackingServer"), "http response:" + strC);
                Log.e(i.a("TrackingServer"), "parseHttpResponse exception: ", e);
            }
        }
        a.b.a.c.r.a aVar2 = this.e;
        if (aVar2 != null) {
            aVar2.b(false, this.f141c);
        }
        return new e<>(new AnalyticsError());
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final String g() {
        JSONObject jSONObject;
        JSONObject jSONObject2 = new JSONObject();
        try {
            JSONObject jSONObjectK = a.b.a.a.a.c.a.k(this.f142d, Boolean.valueOf(this.f), this.h, this.i, this.j, this.g);
            jSONObjectK.put("st", "" + System.currentTimeMillis());
            jSONObject2.put("H", jSONObjectK);
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < this.f141c.size(); i++) {
                JSONObject jSONObject3 = new JSONObject();
                LogEvent logEvent = this.f141c.get(i);
                JSONObject jSONObjectY = a.b.a.a.a.c.a.Y(logEvent, this.g);
                if (jSONObjectK.names() != null) {
                    JSONArray jSONArrayNames = jSONObjectK.names();
                    for (int i2 = 0; i2 < jSONArrayNames.length(); i2++) {
                        String strOptString = jSONArrayNames.optString(i2);
                        if (!TextUtils.isEmpty(strOptString)) {
                            String strOptString2 = jSONObjectK.optString(strOptString);
                            String strOptString3 = jSONObjectY.optString(strOptString);
                            if ((!TextUtils.isEmpty(strOptString3) && strOptString3.equals(strOptString2)) || (TextUtils.isEmpty(strOptString3) && TextUtils.isEmpty(strOptString2))) {
                                jSONObjectY.remove(strOptString);
                            }
                        }
                    }
                }
                jSONObject3.put("H", jSONObjectY);
                try {
                    jSONObject = !TextUtils.isEmpty(logEvent.f) ? new JSONObject(logEvent.f) : new JSONObject();
                } catch (Exception e) {
                    Log.e(i.a("ServiceHelper"), "getPayloadBody exception: ", e);
                }
                jSONObject3.put("B", jSONObject);
                jSONArray.put(jSONObject3);
            }
            jSONObject2.put("B", jSONArray);
        } catch (Exception e2) {
            Log.e(i.a("TrackingServer"), "buildPayload exception: ", e2);
        }
        return jSONObject2.toString();
    }
}
