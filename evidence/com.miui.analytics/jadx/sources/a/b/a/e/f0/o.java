package a.b.a.e.f0;

import android.database.Cursor;
import android.database.sqlite.SQLiteBlobTooBigException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.ArrayMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static volatile o f358c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f360b = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public k f359a = new k();

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f361a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f362b;

        public a(boolean z, boolean z2) {
            this.f361a = z;
            this.f362b = z2;
        }

        public String toString() {
            StringBuilder sbC = a.a.a.a.a.c("UploadResult{success=");
            sbC.append(this.f361a);
            sbC.append(", delay=");
            sbC.append(this.f362b);
            sbC.append('}');
            return sbC.toString();
        }
    }

    public static o d() {
        if (f358c == null) {
            synchronized (o.class) {
                if (f358c == null) {
                    f358c = new o();
                }
            }
        }
        return f358c;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final void a(j jVar, JSONObject jSONObject, long j) {
        try {
            jVar.f341a.put(jSONObject);
            jVar.f343c.add(Long.valueOf(j));
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("H");
            f.p().q(jVar.f, jVar.g, jSONObjectOptJSONObject.optString("event"), jSONObject, jSONObjectOptJSONObject.optLong("e_ts"), false);
        } catch (Exception e) {
            boolean z = this.f360b;
            a.b.a.e.l0.p.h("NonceUploaderEngine", "addToUpload error,", e);
            a.b.a.a.a.c.a.w(z, "NonceUploaderEngine", "addToUpload error,", e);
        }
    }

    public synchronized void b(int i, boolean z) {
        k kVar = this.f359a;
        if (kVar != null) {
            kVar.b(i, z);
        } else {
            k.e(z, "NonceUploaderEngine", "*** impossible, nonce upload timer should not be null");
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final void c(HashMap<JSONObject, Long> map, Set<Long> set) {
        try {
            Iterator<Map.Entry<JSONObject, Long>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<JSONObject, Long> next = it.next();
                JSONObject key = next.getKey();
                long jLongValue = next.getValue().longValue();
                JSONObject jSONObjectOptJSONObject = key.optJSONObject("B");
                int iOptInt = jSONObjectOptJSONObject.optInt("tz_retry_count");
                if (iOptInt != 0 && iOptInt < 5 && jSONObjectOptJSONObject.optLong("tz_next_get_nonce_time") > System.currentTimeMillis()) {
                    a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "filterNotGetNonce->remove event that not get nonce. id: " + jLongValue, true);
                    set.add(Long.valueOf(jLongValue));
                    it.remove();
                }
            }
        } catch (Exception e) {
            boolean z = this.f360b;
            a.b.a.e.l0.p.h("NonceUploaderEngine", "filterNotGetNonce error,", e);
            a.b.a.a.a.c.a.w(z, "NonceUploaderEngine", "filterNotGetNonce error,", e);
            a.b.a.a.a.c.a.y0("up", "s13", e);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final void e(Set<Long> set, j jVar, String str, String str2) {
        try {
            String strF0 = a.b.a.a.a.c.a.F0(a.b.a.e.g0.d.d(str2), this.f360b);
            for (Map.Entry<JSONObject, Long> entry : jVar.f342b.entrySet()) {
                JSONObject key = entry.getKey();
                long jLongValue = entry.getValue().longValue();
                JSONObject jSONObjectOptJSONObject = key.optJSONObject("B");
                int iOptInt = jSONObjectOptJSONObject.optInt("tz_retry_count");
                a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "handleGetNonceFail->retryCount: " + iOptInt, true);
                if (iOptInt >= 4) {
                    k(jSONObjectOptJSONObject);
                    a.b.a.e.h0.b.e(jSONObjectOptJSONObject, str2, str, 0, strF0, false);
                    a(jVar, key, jLongValue);
                } else {
                    int i = iOptInt + 1;
                    jSONObjectOptJSONObject.put("tz_retry_count", i);
                    long jC = (((long) a.b.a.e.l0.o.c("NonceUploaderEngine", i)) * 1000) + System.currentTimeMillis();
                    a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "handleGetNonceFail->nextGetNonceTime: " + jC + ", id: " + jLongValue, true);
                    jSONObjectOptJSONObject.put("tz_next_get_nonce_time", jC);
                    f.p().G(key, jLongValue);
                    set.add(Long.valueOf(jLongValue));
                }
            }
        } catch (Exception e) {
            boolean z = this.f360b;
            a.b.a.e.l0.p.h("NonceUploaderEngine", "handleGetNonceFail error,", e);
            a.b.a.a.a.c.a.w(z, "NonceUploaderEngine", "handleGetNonceFail error,", e);
            a.b.a.a.a.c.a.y0("up", "s15", e);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public void f(a.b.a.e.h0.b bVar, boolean z) throws JSONException {
        String strP;
        int i;
        String strC = a.b.a.e.g0.c.c(a.b.a.a.a.c.a.t(a.b.a.e.h0.b.h(a.b.a.e.e.b(), z).getBytes()));
        JSONObject jSONObjectOptJSONObject = bVar.e.optJSONObject("B");
        String strX0 = null;
        if (!a.b.a.a.a.c.a.G0(a.b.a.e.e.b(), Boolean.valueOf(z))) {
            if (jSONObjectOptJSONObject != null) {
                jSONObjectOptJSONObject.put("tz_content", strC);
                jSONObjectOptJSONObject.put("tz_support", false);
                jSONObjectOptJSONObject.put("tz_cpuid", a.b.a.e.h0.b.g(null, z));
                return;
            }
            return;
        }
        boolean zF = a.b.a.e.l0.u.f();
        a.b.a.a.a.c.a.m(z, "NonceUploaderEngine", "handleSingleNoTZActiveEvent->isGetNonce: " + zF);
        String strE0 = a.b.a.a.a.c.a.E0(a.b.a.e.g0.d.d(strC), Boolean.valueOf(z));
        if (zF) {
            String strP2 = a.b.a.a.a.c.a.P(a.b.a.e.e.b(), Boolean.valueOf(z));
            if (!TextUtils.isEmpty(strP2)) {
                HashMap map = new HashMap();
                map.put("f", strP2);
                map.put("v", "1.0");
                try {
                    a.b.a.e.i0.c cVarN = a.b.a.e.i0.c.n();
                    ConcurrentHashMap<String, String> concurrentHashMap = a.b.a.e.y.f663c;
                    a.b.a.e.y yVar = a.b.a.e.y.b.f667a;
                    String strJ = cVarN.j(yVar.a("https://", yVar.d(), "/track/getNonce"), map, true);
                    a.b.a.a.a.c.a.m(z, "NonceManager", "getNonce response : " + strJ);
                    strX0 = a.b.a.a.a.c.a.x0(strJ);
                } catch (Exception e) {
                    a.b.a.a.a.c.a.s(z, "NonceManager", "getNonce failed, e: ", e);
                    a.b.a.a.a.c.a.y0("up", "s14", e);
                }
            }
            String str = strX0;
            if (!TextUtils.isEmpty(strP2) && !TextUtils.isEmpty(str)) {
                a.b.a.e.h0.b.d(jSONObjectOptJSONObject, strC, strP2, str, a.b.a.a.a.c.a.E0(a.b.a.e.g0.d.d(strC) + str, Boolean.valueOf(z)), z);
                return;
            }
            strP = strP2;
            i = 3;
        } else {
            strP = a.b.a.a.a.c.a.P(a.b.a.e.e.b(), Boolean.valueOf(z));
            i = 1;
        }
        a.b.a.e.h0.b.e(jSONObjectOptJSONObject, strC, strP, i, strE0, z);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final a g(String str) {
        boolean z;
        boolean z2 = false;
        a aVar = new a(false, false);
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("refresh_cfg")) {
                j(jSONObject.getJSONObject("refresh_cfg"));
            }
            int iOptInt = jSONObject.optInt("code");
            long jOptLong = jSONObject.optLong("delay");
            if (jOptLong <= 0 || jOptLong >= System.currentTimeMillis() + 172800000) {
                z = false;
            } else {
                a.b.a.e.l0.u.l("forbidden_upload_until", System.currentTimeMillis() + jOptLong);
                z = true;
            }
            if (iOptInt == 0) {
                a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "成功发送数据到服务端", true);
                if (a.b.a.e.l0.r.q() && jSONObject.has("ot_ri")) {
                    a.b.a.e.e0.m.b.f308a.e(jSONObject.optString("ot_ri"));
                }
                boolean zF = a.b.a.b.a.f();
                a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "isCloseOneTrackPullConfig: " + zF, true);
                if (!zF) {
                    a.b.a.e.e0.a aVarD = a.b.a.e.e0.a.d();
                    Objects.requireNonNull(aVarD);
                    a.b.a.e.e0.b bVar = new a.b.a.e.e0.b(aVarD, jSONObject);
                    ThreadPoolExecutor threadPoolExecutor = a.b.a.e.l0.g.f580a;
                    try {
                        a.b.a.e.l0.g.f580a.execute(bVar);
                    } catch (Throwable unused) {
                    }
                }
                z2 = true;
            } else if (iOptInt == -3) {
                a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "signature expired, will update", true);
                int i = a.b.a.e.g0.e.e;
                a.b.a.e.g0.e.b.f436a.c(true);
            } else {
                a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "Error: status code=" + iOptInt, true);
            }
            return new a(z2, z);
        } catch (Exception e) {
            boolean z3 = this.f360b;
            a.b.a.e.l0.p.h("NonceUploaderEngine", "parseUploadingResult exception ", e);
            a.b.a.a.a.c.a.w(z3, "NonceUploaderEngine", "parseUploadingResult exception ", e);
            a.b.a.a.a.c.a.y0("up", "s19", e);
            return aVar;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final void h(ArrayMap<String, Integer> arrayMap, ArrayMap<String, Integer> arrayMap2) {
        try {
            if (arrayMap.size() <= 0 && arrayMap2.size() <= 0) {
                a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "recordReportEventsToDB, this report has no monitor events, skip it", true);
                return;
            }
            for (String str : a.b.a.g.f.b.f891a) {
                int iIntValue = 0;
                int iIntValue2 = arrayMap.get(str) == null ? 0 : arrayMap.get(str).intValue();
                if (arrayMap2.get(str) != null) {
                    iIntValue = arrayMap2.get(str).intValue();
                }
                if (iIntValue2 != 0 || iIntValue != 0) {
                    a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "recordReportEventsToDB, dbEventName: " + str + ", todayCount: " + iIntValue2 + ", notTodayCount: " + iIntValue, true);
                    a.b.a.g.c.c.g(false, str, iIntValue2 + iIntValue, "report_total", iIntValue2, "report_today");
                }
            }
        } catch (Exception e) {
            boolean z = this.f360b;
            a.b.a.e.l0.p.h("NonceUploaderEngine", "recordReportEventsToDB error: ", e);
            a.b.a.a.a.c.a.w(z, "NonceUploaderEngine", "recordReportEventsToDB error: ", e);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final void i(ArrayMap<String, Integer> arrayMap, ArrayMap<String, Integer> arrayMap2) {
        try {
            if (arrayMap.size() <= 0 && arrayMap2.size() <= 0) {
                a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "recordReportSuccessEventsToDB, this report has no monitor events, skip it", true);
                return;
            }
            for (String str : a.b.a.g.f.b.f891a) {
                int iIntValue = 0;
                int iIntValue2 = arrayMap.get(str) == null ? 0 : arrayMap.get(str).intValue();
                if (arrayMap2.get(str) != null) {
                    iIntValue = arrayMap2.get(str).intValue();
                }
                if (iIntValue2 != 0 || iIntValue != 0) {
                    a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "recordReportSuccessEventsToDB, dbEventName: " + str + ", todayCount: " + iIntValue2 + ", notTodayCount: " + iIntValue, true);
                    a.b.a.g.c.c.g(false, str, iIntValue2 + iIntValue, "report_total_success", iIntValue2, "report_today_success");
                }
            }
        } catch (Exception e) {
            boolean z = this.f360b;
            a.b.a.e.l0.p.h("NonceUploaderEngine", "recordReportSuccessEventsToDB error: ", e);
            a.b.a.a.a.c.a.w(z, "NonceUploaderEngine", "recordReportSuccessEventsToDB error: ", e);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final void j(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.has("id") || !jSONObject.has("app_id") || !jSONObject.has("rand_delay")) {
            a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "invalid refresh: " + jSONObject, true);
            return;
        }
        String string = jSONObject.getString("id");
        String strH = a.b.a.e.l0.u.h("refresh_cfg_cmd", null);
        boolean z = false;
        if (strH == null) {
            z = true;
        } else {
            JSONObject jSONObject2 = new JSONObject(strH);
            if (jSONObject2.has("id") && jSONObject2.has("app_id") && jSONObject2.has("delay_to")) {
                String string2 = jSONObject2.getString("id");
                String string3 = jSONObject2.getString("app_id");
                long j = jSONObject2.getLong("delay_to");
                long jCurrentTimeMillis = System.currentTimeMillis();
                if ((!string2.equals(string) && string3 != null) || Math.abs(jCurrentTimeMillis - j) > 86400000) {
                    a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "not the same id, or over 1 day, prevId=" + string2 + ", curId=" + string + ", now=" + jCurrentTimeMillis + ", prevDelayTo=" + j, true);
                }
            } else {
                a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "invalid cfg " + strH, true);
            }
            z = true;
        }
        if (z) {
            long j2 = jSONObject.getLong("rand_delay");
            jSONObject.put("delay_to", System.currentTimeMillis() + j2);
            a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "save to pref " + jSONObject, true);
            if (j2 > 0) {
                a.b.a.e.l0.u.m("refresh_cfg_cmd", jSONObject.toString());
                String string4 = jSONObject.getString("app_id");
                k kVar = this.f359a;
                Objects.requireNonNull(kVar);
                Message messageObtain = Message.obtain();
                messageObtain.what = 1001;
                Bundle bundle = new Bundle();
                bundle.putString("app_id", string4);
                messageObtain.setData(bundle);
                kVar.f347b.sendMessageDelayed(messageObtain, j2);
            }
        }
    }

    public final void k(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        jSONObject.remove("tz_retry_count");
        jSONObject.remove("tz_next_get_nonce_time");
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX WARN: Code duplicated, block: B:137:0x02b8  */
    /* JADX WARN: Code duplicated, block: B:139:0x02bf  */
    /* JADX WARN: Code duplicated, block: B:142:0x02d8  */
    /* JADX WARN: Code duplicated, block: B:143:0x02da  */
    /* JADX WARN: Code duplicated, block: B:148:0x02f1 A[Catch: Exception -> 0x0397, TryCatch #7 {Exception -> 0x0397, blocks: (B:146:0x02eb, B:148:0x02f1, B:150:0x0312, B:152:0x0331, B:153:0x0336, B:155:0x035c, B:157:0x036c, B:159:0x0374, B:161:0x037a, B:164:0x0381, B:165:0x0385, B:166:0x0389), top: B:241:0x02eb }] */
    /* JADX WARN: Code duplicated, block: B:150:0x0312 A[Catch: Exception -> 0x0397, TryCatch #7 {Exception -> 0x0397, blocks: (B:146:0x02eb, B:148:0x02f1, B:150:0x0312, B:152:0x0331, B:153:0x0336, B:155:0x035c, B:157:0x036c, B:159:0x0374, B:161:0x037a, B:164:0x0381, B:165:0x0385, B:166:0x0389), top: B:241:0x02eb }] */
    /* JADX WARN: Code duplicated, block: B:152:0x0331 A[Catch: Exception -> 0x0397, TryCatch #7 {Exception -> 0x0397, blocks: (B:146:0x02eb, B:148:0x02f1, B:150:0x0312, B:152:0x0331, B:153:0x0336, B:155:0x035c, B:157:0x036c, B:159:0x0374, B:161:0x037a, B:164:0x0381, B:165:0x0385, B:166:0x0389), top: B:241:0x02eb }] */
    /* JADX WARN: Code duplicated, block: B:153:0x0336 A[Catch: Exception -> 0x0397, TryCatch #7 {Exception -> 0x0397, blocks: (B:146:0x02eb, B:148:0x02f1, B:150:0x0312, B:152:0x0331, B:153:0x0336, B:155:0x035c, B:157:0x036c, B:159:0x0374, B:161:0x037a, B:164:0x0381, B:165:0x0385, B:166:0x0389), top: B:241:0x02eb }] */
    /* JADX WARN: Code duplicated, block: B:155:0x035c A[Catch: Exception -> 0x0397, TryCatch #7 {Exception -> 0x0397, blocks: (B:146:0x02eb, B:148:0x02f1, B:150:0x0312, B:152:0x0331, B:153:0x0336, B:155:0x035c, B:157:0x036c, B:159:0x0374, B:161:0x037a, B:164:0x0381, B:165:0x0385, B:166:0x0389), top: B:241:0x02eb }] */
    /* JADX WARN: Code duplicated, block: B:157:0x036c A[Catch: Exception -> 0x0397, TryCatch #7 {Exception -> 0x0397, blocks: (B:146:0x02eb, B:148:0x02f1, B:150:0x0312, B:152:0x0331, B:153:0x0336, B:155:0x035c, B:157:0x036c, B:159:0x0374, B:161:0x037a, B:164:0x0381, B:165:0x0385, B:166:0x0389), top: B:241:0x02eb }] */
    /* JADX WARN: Code duplicated, block: B:158:0x0373  */
    /* JADX WARN: Code duplicated, block: B:161:0x037a A[Catch: Exception -> 0x0397, TryCatch #7 {Exception -> 0x0397, blocks: (B:146:0x02eb, B:148:0x02f1, B:150:0x0312, B:152:0x0331, B:153:0x0336, B:155:0x035c, B:157:0x036c, B:159:0x0374, B:161:0x037a, B:164:0x0381, B:165:0x0385, B:166:0x0389), top: B:241:0x02eb }] */
    /* JADX WARN: Code duplicated, block: B:165:0x0385 A[Catch: Exception -> 0x0397, TryCatch #7 {Exception -> 0x0397, blocks: (B:146:0x02eb, B:148:0x02f1, B:150:0x0312, B:152:0x0331, B:153:0x0336, B:155:0x035c, B:157:0x036c, B:159:0x0374, B:161:0x037a, B:164:0x0381, B:165:0x0385, B:166:0x0389), top: B:241:0x02eb }] */
    /* JADX WARN: Code duplicated, block: B:166:0x0389 A[Catch: Exception -> 0x0397, TRY_LEAVE, TryCatch #7 {Exception -> 0x0397, blocks: (B:146:0x02eb, B:148:0x02f1, B:150:0x0312, B:152:0x0331, B:153:0x0336, B:155:0x035c, B:157:0x036c, B:159:0x0374, B:161:0x037a, B:164:0x0381, B:165:0x0385, B:166:0x0389), top: B:241:0x02eb }] */
    /* JADX WARN: Code duplicated, block: B:172:0x03af  */
    /* JADX WARN: Code duplicated, block: B:228:0x0524 A[Catch: all -> 0x052a, TryCatch #8 {, blocks: (B:30:0x0120, B:31:0x0125, B:131:0x0295, B:92:0x022a, B:93:0x0232, B:95:0x0235, B:130:0x0291, B:226:0x051f, B:228:0x0524, B:229:0x0529, B:122:0x0278, B:128:0x028c), top: B:243:0x0120 }] */
    /* JADX WARN: Code duplicated, block: B:241:0x02eb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:266:0x029a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:267:0x04fd A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:276:0x01be A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:279:0x01eb A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:58:0x018c A[Catch: all -> 0x01d8, TryCatch #13 {all -> 0x01d8, blocks: (B:54:0x0177, B:56:0x017f, B:58:0x018c, B:60:0x019a, B:63:0x01a4, B:66:0x01ad, B:67:0x01b4, B:69:0x01be), top: B:247:0x0177 }] */
    /* JADX WARN: Code duplicated, block: B:60:0x019a A[Catch: all -> 0x01d8, TryCatch #13 {all -> 0x01d8, blocks: (B:54:0x0177, B:56:0x017f, B:58:0x018c, B:60:0x019a, B:63:0x01a4, B:66:0x01ad, B:67:0x01b4, B:69:0x01be), top: B:247:0x0177 }] */
    /* JADX WARN: Code duplicated, block: B:63:0x01a4 A[Catch: all -> 0x01d8, TryCatch #13 {all -> 0x01d8, blocks: (B:54:0x0177, B:56:0x017f, B:58:0x018c, B:60:0x019a, B:63:0x01a4, B:66:0x01ad, B:67:0x01b4, B:69:0x01be), top: B:247:0x0177 }] */
    /* JADX WARN: Code duplicated, block: B:83:0x0202 A[Catch: all -> 0x023a, Exception -> 0x023f, SQLiteBlobTooBigException -> 0x0241, TryCatch #19 {SQLiteBlobTooBigException -> 0x0241, Exception -> 0x023f, all -> 0x023a, blocks: (B:81:0x01fa, B:83:0x0202, B:85:0x0208, B:91:0x0221, B:86:0x0216, B:78:0x01e4), top: B:264:0x01fa }] */
    /* JADX WARN: Code duplicated, block: B:85:0x0208 A[Catch: all -> 0x023a, Exception -> 0x023f, SQLiteBlobTooBigException -> 0x0241, TryCatch #19 {SQLiteBlobTooBigException -> 0x0241, Exception -> 0x023f, all -> 0x023a, blocks: (B:81:0x01fa, B:83:0x0202, B:85:0x0208, B:91:0x0221, B:86:0x0216, B:78:0x01e4), top: B:264:0x01fa }] */
    /* JADX WARN: Code duplicated, block: B:86:0x0216 A[Catch: all -> 0x023a, Exception -> 0x023f, SQLiteBlobTooBigException -> 0x0241, TryCatch #19 {SQLiteBlobTooBigException -> 0x0241, Exception -> 0x023f, all -> 0x023a, blocks: (B:81:0x01fa, B:83:0x0202, B:85:0x0208, B:91:0x0221, B:86:0x0216, B:78:0x01e4), top: B:264:0x01fa }] */
    /* JADX WARN: Code duplicated, block: B:88:0x021c  */
    /* JADX WARN: Code duplicated, block: B:89:0x021e  */
    /* JADX WARN: Code duplicated, block: B:95:0x0235 A[Catch: all -> 0x052a, TRY_LEAVE, TryCatch #8 {, blocks: (B:30:0x0120, B:31:0x0125, B:131:0x0295, B:92:0x022a, B:93:0x0232, B:95:0x0235, B:130:0x0291, B:226:0x051f, B:228:0x0524, B:229:0x0529, B:122:0x0278, B:128:0x028c), top: B:243:0x0120 }] */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x027b, code lost:
    
        if (r15 != null) goto L130;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x028f, code lost:
    
        if (r15 != null) goto L130;
     */
    /* JADX WARN: Instruction removed from duplicated block: B:150:0x0312, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:153:0x0336, please report this as an issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public a l(int i) throws Throwable {
        Cursor cursor;
        SQLiteDatabase sQLiteDatabaseG;
        SQLiteDatabase sQLiteDatabase;
        Cursor cursorQuery;
        SQLiteDatabase sQLiteDatabase2;
        j jVar;
        f.c cVar;
        j jVar2;
        boolean z;
        HashMap<JSONObject, Long> map;
        HashMap<JSONObject, Long> map2;
        int size;
        String strC;
        boolean zF;
        String strQ;
        String strX;
        JSONArray jSONArray;
        String string;
        byte[] bArrE;
        SQLiteDatabase sQLiteDatabase3;
        boolean z2;
        boolean z3;
        boolean z4;
        String strJ;
        boolean z5;
        int i2;
        String string2;
        JSONObject jSONObject;
        JSONObject jSONObjectOptJSONObject;
        boolean zB;
        k.e(false, "NonceUploaderEngine", "即将读取数据库并上传数据");
        HashSet hashSet = new HashSet();
        do {
            f fVarP = f.p();
            synchronized (fVarP.f330a) {
                boolean z6 = true;
                try {
                    sQLiteDatabaseG = fVarP.f330a.G();
                    try {
                        try {
                            StringBuilder sb = new StringBuilder();
                            sb.append("appid = '001'");
                            sb.append(" and ");
                            sb.append("event_name = 'onetrack_active'");
                            if (hashSet.size() > 0) {
                                try {
                                    sb.append(" and ");
                                    sb.append("_id");
                                    sb.append(" not in (");
                                    Iterator it = hashSet.iterator();
                                    while (it.hasNext()) {
                                        sb.append(it.next());
                                        sb.append(",");
                                    }
                                    sb.deleteCharAt(sb.length() - 1);
                                    sb.append(")");
                                    a.b.a.a.a.c.a.n(a.b.a.e.w.i, "EventManager", "getNoTZActiveEventsDataSync->selection: " + ((Object) sb), true);
                                } catch (Exception e) {
                                    a.b.a.a.a.c.a.r("EventManager", "assemble sq error", e);
                                }
                            }
                            cursorQuery = sQLiteDatabaseG.query("events", new String[]{"_id", "event_name", "priority", "data"}, sb.toString(), null, null, null, "priority ASC, _id ASC");
                            try {
                                try {
                                    int columnIndex = cursorQuery.getColumnIndex("_id");
                                    int columnIndex2 = cursorQuery.getColumnIndex("event_name");
                                    int columnIndex3 = cursorQuery.getColumnIndex("priority");
                                    int columnIndex4 = cursorQuery.getColumnIndex("data");
                                    JSONArray jSONArray2 = new JSONArray();
                                    HashMap map3 = new HashMap();
                                    ArrayList arrayList = new ArrayList();
                                    ArrayMap arrayMap = new ArrayMap();
                                    ArrayMap arrayMap2 = new ArrayMap();
                                    boolean z7 = true;
                                    boolean z8 = false;
                                    int length = 0;
                                    while (true) {
                                        try {
                                            if (cursorQuery.moveToNext()) {
                                                long j = cursorQuery.getLong(columnIndex);
                                                byte[] blob = cursorQuery.getBlob(columnIndex4);
                                                if (blob != null) {
                                                    try {
                                                        strJ = f.j(blob);
                                                    } catch (Throwable th) {
                                                        th = th;
                                                        cursor = cursorQuery;
                                                        a.b.a.a.a.c.a.i(cursor);
                                                        if (sQLiteDatabaseG != null) {
                                                            fVarP.f330a.J();
                                                        }
                                                        throw th;
                                                    }
                                                } else {
                                                    strJ = null;
                                                }
                                                int i3 = columnIndex;
                                                String strA = strJ;
                                                try {
                                                    try {
                                                        try {
                                                            try {
                                                                try {
                                                                    try {
                                                                        if (z7) {
                                                                            int i4 = cursorQuery.getInt(columnIndex3);
                                                                            if (i4 > i) {
                                                                                a.b.a.a.a.c.a.n(a.b.a.e.w.i, "EventManager", "No records of priority[" + i + "], first record priority=" + i4, z6);
                                                                                a.b.a.a.a.c.a.i(cursorQuery);
                                                                                cVar = fVarP.f330a;
                                                                            } else {
                                                                                z5 = false;
                                                                            }
                                                                            cVar.J();
                                                                            jVar = null;
                                                                        } else {
                                                                            z5 = z7;
                                                                        }
                                                                        jSONObjectOptJSONObject = jSONObject.optJSONObject("H");
                                                                    } catch (Exception e2) {
                                                                        e = e2;
                                                                        JSONObject jSONObject2 = jSONObject;
                                                                        a.b.a.a.a.c.a.r("EventManager", "*** error ***", e);
                                                                        if (blob != null) {
                                                                            strA = f.A(blob);
                                                                            jSONObject = new JSONObject(strA);
                                                                            jSONObjectOptJSONObject = jSONObject.optJSONObject("H");
                                                                        } else {
                                                                            jSONObjectOptJSONObject = null;
                                                                            strA = strA;
                                                                            jSONObject = jSONObject2;
                                                                        }
                                                                        zB = a.b.a.e.e0.l.b.f306a.b(jSONObjectOptJSONObject);
                                                                        sQLiteDatabase3 = sQLiteDatabaseG;
                                                                        long jOptLong = jSONObjectOptJSONObject.optLong("e_ts");
                                                                        if (zB) {
                                                                            JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("B");
                                                                            if ("onetrack_active".equals(string2)) {
                                                                                if (!z8) {
                                                                                    z8 = true;
                                                                                }
                                                                                map3.put(jSONObject, Long.valueOf(j));
                                                                            }
                                                                            length += strA.length();
                                                                            if (length >= 512000) {
                                                                                a.b.a.a.a.c.a.n(a.b.a.e.w.i, "EventManager", "reached max len: " + length, true);
                                                                                z2 = z8;
                                                                                if (map3.size() > 0) {
                                                                                    if (cursorQuery.isAfterLast()) {
                                                                                        a.b.a.a.a.c.a.n(a.b.a.e.w.i, "EventManager", "cursor isAfterLast", true);
                                                                                        z4 = true;
                                                                                    } else {
                                                                                        if (cursorQuery.getInt(columnIndex3) > i) {
                                                                                            z3 = true;
                                                                                        } else {
                                                                                            z3 = false;
                                                                                        }
                                                                                        z4 = z3;
                                                                                    }
                                                                                    jVar = new j(jSONArray2, map3, arrayList, z2, z4, arrayMap, arrayMap2);
                                                                                    a.b.a.a.a.c.a.i(cursorQuery);
                                                                                    fVarP.f330a.J();
                                                                                } else {
                                                                                    a.b.a.a.a.c.a.i(cursorQuery);
                                                                                    cVar = fVarP.f330a;
                                                                                    cVar.J();
                                                                                    jVar = null;
                                                                                }
                                                                                jVar2 = jVar;
                                                                                if (jVar2 == null) {
                                                                                    k.e(false, "NonceUploaderEngine", "满足条件的记录为空，即将返回, priority=" + i);
                                                                                    return new a(true, false);
                                                                                }
                                                                                z = jVar2.f344d;
                                                                                this.f360b = z;
                                                                                if (!z) {
                                                                                    a.b.a.a.a.c.a.n(a.b.a.e.w.i, "NonceUploaderEngine", "this loop has no activation", true);
                                                                                }
                                                                                map = jVar2.f342b;
                                                                                boolean z9 = this.f360b;
                                                                                StringBuilder sbC = a.a.a.a.a.c("uploadData->noTZActiveEventsData length: ");
                                                                                map2 = jVar2.f342b;
                                                                                if (map2 == null) {
                                                                                    size = 0;
                                                                                } else {
                                                                                    size = map2.size();
                                                                                }
                                                                                sbC.append(size);
                                                                                a.b.a.a.a.c.a.n(z9, "NonceUploaderEngine", sbC.toString(), true);
                                                                                if (map != null) {
                                                                                    if (map.size() > 0) {
                                                                                        strC = a.b.a.e.g0.c.c(a.b.a.a.a.c.a.t(a.b.a.e.h0.b.h(a.b.a.e.e.b(), false).getBytes()));
                                                                                        if (a.b.a.a.a.c.a.H0(a.b.a.e.e.b(), this.f360b)) {
                                                                                            zF = a.b.a.e.l0.u.f();
                                                                                            a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "uploadData->isGetNonce: " + zF, true);
                                                                                            if (zF) {
                                                                                                c(map, hashSet);
                                                                                                a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "uploadData->after filter event that not get nonce. size: " + map.size(), true);
                                                                                                if (map.size() > 0) {
                                                                                                    strQ = a.b.a.a.a.c.a.Q(a.b.a.e.e.b(), this.f360b);
                                                                                                    if (TextUtils.isEmpty(strQ)) {
                                                                                                        strX = null;
                                                                                                    } else {
                                                                                                        strX = a.b.a.a.a.c.a.X(strQ, this.f360b);
                                                                                                    }
                                                                                                    if (TextUtils.isEmpty(strQ)) {
                                                                                                        e(hashSet, jVar2, strQ, strC);
                                                                                                    } else {
                                                                                                        e(hashSet, jVar2, strQ, strC);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                n(jVar2, strC);
                                                                                            }
                                                                                        } else {
                                                                                            a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "should not go here, but since it is here, let's deal with it", true);
                                                                                            o(jVar2, strC);
                                                                                        }
                                                                                    }
                                                                                }
                                                                                jSONArray = jVar2.f341a;
                                                                                if (jSONArray != null) {
                                                                                }
                                                                                a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "准备上报的的记录为空，即将返回, priority=" + i, true);
                                                                                return new a(true, false);
                                                                            }
                                                                        } else {
                                                                            JSONObject jSONObjectOptJSONObject3 = jSONObject.optJSONObject("B");
                                                                            if ("onetrack_active".equals(string2)) {
                                                                                if (!z8) {
                                                                                    z8 = true;
                                                                                }
                                                                                map3.put(jSONObject, Long.valueOf(j));
                                                                            }
                                                                            length += strA.length();
                                                                            if (length >= 512000) {
                                                                                a.b.a.a.a.c.a.n(a.b.a.e.w.i, "EventManager", "reached max len: " + length, true);
                                                                                z2 = z8;
                                                                                if (map3.size() > 0) {
                                                                                    if (cursorQuery.isAfterLast()) {
                                                                                        a.b.a.a.a.c.a.n(a.b.a.e.w.i, "EventManager", "cursor isAfterLast", true);
                                                                                        z4 = true;
                                                                                    } else {
                                                                                        if (cursorQuery.getInt(columnIndex3) > i) {
                                                                                            z3 = true;
                                                                                        } else {
                                                                                            z3 = false;
                                                                                        }
                                                                                        z4 = z3;
                                                                                    }
                                                                                    jVar = new j(jSONArray2, map3, arrayList, z2, z4, arrayMap, arrayMap2);
                                                                                    a.b.a.a.a.c.a.i(cursorQuery);
                                                                                    fVarP.f330a.J();
                                                                                } else {
                                                                                    a.b.a.a.a.c.a.i(cursorQuery);
                                                                                    cVar = fVarP.f330a;
                                                                                    cVar.J();
                                                                                    jVar = null;
                                                                                }
                                                                                jVar2 = jVar;
                                                                                if (jVar2 == null) {
                                                                                    k.e(false, "NonceUploaderEngine", "满足条件的记录为空，即将返回, priority=" + i);
                                                                                    return new a(true, false);
                                                                                }
                                                                                z = jVar2.f344d;
                                                                                this.f360b = z;
                                                                                if (!z) {
                                                                                    a.b.a.a.a.c.a.n(a.b.a.e.w.i, "NonceUploaderEngine", "this loop has no activation", true);
                                                                                }
                                                                                map = jVar2.f342b;
                                                                                boolean z10 = this.f360b;
                                                                                StringBuilder sbC2 = a.a.a.a.a.c("uploadData->noTZActiveEventsData length: ");
                                                                                map2 = jVar2.f342b;
                                                                                if (map2 == null) {
                                                                                    size = 0;
                                                                                } else {
                                                                                    size = map2.size();
                                                                                }
                                                                                sbC2.append(size);
                                                                                a.b.a.a.a.c.a.n(z10, "NonceUploaderEngine", sbC2.toString(), true);
                                                                                if (map != null) {
                                                                                    try {
                                                                                        if (map.size() > 0) {
                                                                                            strC = a.b.a.e.g0.c.c(a.b.a.a.a.c.a.t(a.b.a.e.h0.b.h(a.b.a.e.e.b(), false).getBytes()));
                                                                                            if (a.b.a.a.a.c.a.H0(a.b.a.e.e.b(), this.f360b)) {
                                                                                                zF = a.b.a.e.l0.u.f();
                                                                                                a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "uploadData->isGetNonce: " + zF, true);
                                                                                                if (zF) {
                                                                                                    n(jVar2, strC);
                                                                                                } else {
                                                                                                    c(map, hashSet);
                                                                                                    a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "uploadData->after filter event that not get nonce. size: " + map.size(), true);
                                                                                                    if (map.size() > 0) {
                                                                                                        strQ = a.b.a.a.a.c.a.Q(a.b.a.e.e.b(), this.f360b);
                                                                                                        if (TextUtils.isEmpty(strQ)) {
                                                                                                            strX = a.b.a.a.a.c.a.X(strQ, this.f360b);
                                                                                                        } else {
                                                                                                            strX = null;
                                                                                                        }
                                                                                                        if (TextUtils.isEmpty(strQ)) {
                                                                                                            e(hashSet, jVar2, strQ, strC);
                                                                                                        } else {
                                                                                                            e(hashSet, jVar2, strQ, strC);
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "should not go here, but since it is here, let's deal with it", true);
                                                                                                o(jVar2, strC);
                                                                                            }
                                                                                        }
                                                                                    } catch (Exception e3) {
                                                                                        boolean z11 = this.f360b;
                                                                                        a.b.a.e.l0.p.h("NonceUploaderEngine", "Handle no tz active events for NonceUploaderEngine failed, e: ", e3);
                                                                                        a.b.a.a.a.c.a.w(z11, "NonceUploaderEngine", "Handle no tz active events for NonceUploaderEngine failed, e: ", e3);
                                                                                        a.b.a.a.a.c.a.y0("up", "s11", e3);
                                                                                    }
                                                                                }
                                                                                jSONArray = jVar2.f341a;
                                                                                if (jSONArray != null) {
                                                                                }
                                                                                a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "准备上报的的记录为空，即将返回, priority=" + i, true);
                                                                                return new a(true, false);
                                                                            }
                                                                        }
                                                                        z6 = true;
                                                                        columnIndex4 = columnIndex4;
                                                                        z7 = z5;
                                                                        columnIndex = i3;
                                                                        columnIndex2 = i2;
                                                                        sQLiteDatabaseG = sQLiteDatabase3;
                                                                    }
                                                                    long jOptLong2 = jSONObjectOptJSONObject.optLong("e_ts");
                                                                    if (zB || System.currentTimeMillis() - jOptLong2 > 600000) {
                                                                        JSONObject jSONObjectOptJSONObject4 = jSONObject.optJSONObject("B");
                                                                        if ("onetrack_active".equals(string2) && !jSONObjectOptJSONObject4.has("tz_content")) {
                                                                            if (!z8 && jSONObjectOptJSONObject4.optBoolean("ot_activation")) {
                                                                                z8 = true;
                                                                            }
                                                                            map3.put(jSONObject, Long.valueOf(j));
                                                                        }
                                                                        length += strA.length();
                                                                        if (length >= 512000) {
                                                                            a.b.a.a.a.c.a.n(a.b.a.e.w.i, "EventManager", "reached max len: " + length, true);
                                                                        }
                                                                    }
                                                                } catch (Throwable th2) {
                                                                    th = th2;
                                                                    a.b.a.a.a.c.a.r("EventManager", "Finally *** error ***", th);
                                                                }
                                                                zB = a.b.a.e.e0.l.b.f306a.b(jSONObjectOptJSONObject);
                                                                sQLiteDatabase3 = sQLiteDatabaseG;
                                                            } catch (Throwable th3) {
                                                                th = th3;
                                                                sQLiteDatabase3 = sQLiteDatabaseG;
                                                                a.b.a.a.a.c.a.r("EventManager", "Finally *** error ***", th);
                                                                z6 = true;
                                                                columnIndex4 = columnIndex4;
                                                                z7 = z5;
                                                                columnIndex = i3;
                                                                columnIndex2 = i2;
                                                                sQLiteDatabaseG = sQLiteDatabase3;
                                                            }
                                                            jSONObject = new JSONObject(strA);
                                                        } catch (Throwable th4) {
                                                            th = th4;
                                                            columnIndex4 = columnIndex4;
                                                            sQLiteDatabase3 = sQLiteDatabaseG;
                                                            a.b.a.a.a.c.a.r("EventManager", "Finally *** error ***", th);
                                                            z6 = true;
                                                            columnIndex4 = columnIndex4;
                                                            z7 = z5;
                                                            columnIndex = i3;
                                                            columnIndex2 = i2;
                                                            sQLiteDatabaseG = sQLiteDatabase3;
                                                        }
                                                    } catch (Exception e4) {
                                                        e = e4;
                                                        jSONObject = null;
                                                    }
                                                    string2 = cursorQuery.getString(columnIndex2);
                                                    i2 = columnIndex2;
                                                } catch (Throwable th5) {
                                                    th = th5;
                                                    i2 = columnIndex2;
                                                }
                                                z6 = true;
                                                columnIndex4 = columnIndex4;
                                                z7 = z5;
                                                columnIndex = i3;
                                                columnIndex2 = i2;
                                                sQLiteDatabaseG = sQLiteDatabase3;
                                            } else {
                                                sQLiteDatabase3 = sQLiteDatabaseG;
                                            }
                                            z2 = z8;
                                            if (map3.size() > 0) {
                                                if (cursorQuery.isAfterLast()) {
                                                    a.b.a.a.a.c.a.n(a.b.a.e.w.i, "EventManager", "cursor isAfterLast", true);
                                                    z4 = true;
                                                } else {
                                                    if (cursorQuery.getInt(columnIndex3) > i) {
                                                        z3 = true;
                                                    } else {
                                                        z3 = false;
                                                    }
                                                    z4 = z3;
                                                }
                                                jVar = new j(jSONArray2, map3, arrayList, z2, z4, arrayMap, arrayMap2);
                                                a.b.a.a.a.c.a.i(cursorQuery);
                                                fVarP.f330a.J();
                                            } else {
                                                a.b.a.a.a.c.a.i(cursorQuery);
                                                cVar = fVarP.f330a;
                                                cVar.J();
                                                jVar = null;
                                            }
                                        } catch (SQLiteBlobTooBigException e5) {
                                            e = e5;
                                            sQLiteDatabase2 = sQLiteDatabase3;
                                            a.b.a.a.a.c.a.r("EventManager", "blob too big ***", e);
                                            fVarP.l();
                                            a.b.a.a.a.c.a.i(cursorQuery);
                                        } catch (Exception e6) {
                                            e = e6;
                                            sQLiteDatabase = sQLiteDatabase3;
                                            a.b.a.a.a.c.a.r("EventManager", "getNoTZActiveEventsDataSync error", e);
                                            a.b.a.a.a.c.a.i(cursorQuery);
                                        } catch (Throwable th6) {
                                            th = th6;
                                            sQLiteDatabaseG = sQLiteDatabase3;
                                            cursor = cursorQuery;
                                            a.b.a.a.a.c.a.i(cursor);
                                            if (sQLiteDatabaseG != null) {
                                                fVarP.f330a.J();
                                            }
                                            throw th;
                                        }
                                    }
                                } catch (Throwable th7) {
                                    th = th7;
                                }
                            } catch (SQLiteBlobTooBigException e7) {
                                e = e7;
                                sQLiteDatabase3 = sQLiteDatabaseG;
                            } catch (Exception e8) {
                                e = e8;
                                sQLiteDatabase3 = sQLiteDatabaseG;
                            }
                        } catch (Throwable th8) {
                            th = th8;
                            cursor = null;
                            sQLiteDatabaseG = sQLiteDatabaseG;
                        }
                    } catch (SQLiteBlobTooBigException e9) {
                        e = e9;
                        sQLiteDatabase3 = sQLiteDatabaseG;
                        cursorQuery = null;
                    } catch (Exception e10) {
                        e = e10;
                        sQLiteDatabase3 = sQLiteDatabaseG;
                        cursorQuery = null;
                    }
                } catch (SQLiteBlobTooBigException e11) {
                    e = e11;
                    sQLiteDatabase2 = null;
                    cursorQuery = null;
                } catch (Exception e12) {
                    e = e12;
                    sQLiteDatabase = null;
                    cursorQuery = null;
                } catch (Throwable th9) {
                    th = th9;
                    cursor = null;
                    sQLiteDatabaseG = null;
                }
            }
            jVar2 = jVar;
            if (jVar2 == null) {
                k.e(false, "NonceUploaderEngine", "满足条件的记录为空，即将返回, priority=" + i);
                return new a(true, false);
            }
            z = jVar2.f344d;
            this.f360b = z;
            if (!z) {
                a.b.a.a.a.c.a.n(a.b.a.e.w.i, "NonceUploaderEngine", "this loop has no activation", true);
            }
            map = jVar2.f342b;
            boolean z12 = this.f360b;
            StringBuilder sbC3 = a.a.a.a.a.c("uploadData->noTZActiveEventsData length: ");
            map2 = jVar2.f342b;
            if (map2 == null) {
                size = 0;
            } else {
                size = map2.size();
            }
            sbC3.append(size);
            a.b.a.a.a.c.a.n(z12, "NonceUploaderEngine", sbC3.toString(), true);
            if (map != null) {
                if (map.size() > 0) {
                    strC = a.b.a.e.g0.c.c(a.b.a.a.a.c.a.t(a.b.a.e.h0.b.h(a.b.a.e.e.b(), false).getBytes()));
                    if (a.b.a.a.a.c.a.H0(a.b.a.e.e.b(), this.f360b)) {
                        zF = a.b.a.e.l0.u.f();
                        a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "uploadData->isGetNonce: " + zF, true);
                        if (zF) {
                            n(jVar2, strC);
                        } else {
                            c(map, hashSet);
                            a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "uploadData->after filter event that not get nonce. size: " + map.size(), true);
                            if (map.size() > 0) {
                                strQ = a.b.a.a.a.c.a.Q(a.b.a.e.e.b(), this.f360b);
                                if (TextUtils.isEmpty(strQ)) {
                                    strX = a.b.a.a.a.c.a.X(strQ, this.f360b);
                                } else {
                                    strX = null;
                                }
                                if (TextUtils.isEmpty(strQ) || TextUtils.isEmpty(strX)) {
                                    e(hashSet, jVar2, strQ, strC);
                                } else {
                                    m(jVar2, strQ, strC, strX);
                                }
                            }
                        }
                    } else {
                        a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "should not go here, but since it is here, let's deal with it", true);
                        o(jVar2, strC);
                    }
                }
            }
            jSONArray = jVar2.f341a;
            if (jSONArray != null || jSONArray.length() == 0) {
                a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "准备上报的的记录为空，即将返回, priority=" + i, true);
                return new a(true, false);
            }
            a aVar = new a(false, false);
            try {
                ConcurrentHashMap<String, String> concurrentHashMap = a.b.a.e.y.f663c;
                String strE = a.b.a.e.y.b.f667a.e();
                JSONArray jSONArray3 = jVar2.f341a;
                try {
                    if (a.b.a.e.e0.d.b().e()) {
                        for (int i5 = 0; i5 < jSONArray3.length(); i5++) {
                            a.b.a.e.l0.i.a(jSONArray3.getJSONObject(i5));
                        }
                    }
                    string = jSONArray3.toString();
                } catch (Exception e13) {
                    a.b.a.e.l0.p.h("NonceUploaderEngine", "getPayload error: ", e13);
                    string = null;
                }
                byte[] bArrU0 = a.b.a.a.a.c.a.U0(string);
                if (bArrU0 == null) {
                    a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "content is null", true);
                    bArrE = null;
                } else {
                    int i6 = a.b.a.e.g0.e.e;
                    bArrE = a.b.a.e.g0.a.e(bArrU0, a.b.a.e.g0.c.a(a.b.a.e.g0.e.b.f436a.a(true)[0]));
                }
                if (bArrE == null || bArrE.length == 0) {
                    a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "encrypted data is null !!!", true);
                } else {
                    a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "before zip and encrypt, len=" + string.length() + ", after=" + bArrE.length, true);
                    h(jVar2.f, jVar2.g);
                    String strM = a.b.a.e.i0.c.n().m(strE, bArrE, this.f360b);
                    a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "sendDataToServer response: " + strM, true);
                    aVar = TextUtils.isEmpty(strM) ? new a(false, false) : g(strM);
                }
            } catch (Exception e14) {
                boolean z13 = this.f360b;
                a.b.a.e.l0.p.h("NonceUploaderEngine", "Exception while uploading ", e14);
                a.b.a.a.a.c.a.w(z13, "NonceUploaderEngine", "Exception while uploading ", e14);
                a.b.a.a.a.c.a.y0("up", "s18", e14);
            }
            if (!aVar.f361a) {
                return aVar;
            }
            i(jVar2.f, jVar2.g);
            if (f.p().k(jVar2.f343c) == 0) {
                boolean z14 = this.f360b;
                Throwable th10 = new Throwable();
                a.b.a.e.l0.p.h("NonceUploaderEngine", "delete DB failed!", th10);
                a.b.a.a.a.c.a.w(z14, "NonceUploaderEngine", "delete DB failed!", th10);
            } else {
                if (this.f360b) {
                    a.b.a.a.a.c.a.n(true, "NonceUploaderEngine", "activation event upload success and delete success from db", true);
                    a.b.a.e.l0.u.k("activation_in_db", false);
                    a.b.a.e.w.i = false;
                }
                if (aVar.f362b) {
                    return aVar;
                }
            }
            return new a(true, false);
        } while (!jVar2.e);
        a.b.a.a.a.c.a.n(this.f360b, "NonceUploaderEngine", "No more records for prio=" + i, true);
        return new a(true, false);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final void m(j jVar, String str, String str2, String str3) {
        try {
            String strF0 = a.b.a.a.a.c.a.F0(a.b.a.e.g0.d.d(str2) + str3, false);
            for (Map.Entry<JSONObject, Long> entry : jVar.f342b.entrySet()) {
                JSONObject key = entry.getKey();
                long jLongValue = entry.getValue().longValue();
                JSONObject jSONObjectOptJSONObject = key.optJSONObject("B");
                k(jSONObjectOptJSONObject);
                a.b.a.e.h0.b.d(jSONObjectOptJSONObject, str2, str, str3, strF0, false);
                a(jVar, key, jLongValue);
            }
        } catch (Exception e) {
            boolean z = this.f360b;
            a.b.a.e.l0.p.h("NonceUploaderEngine", "uploadGetNonceSuccess error,", e);
            a.b.a.a.a.c.a.w(z, "NonceUploaderEngine", "uploadGetNonceSuccess error,", e);
            a.b.a.a.a.c.a.y0("up", "s16", e);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final void n(j jVar, String str) {
        try {
            String strF0 = a.b.a.a.a.c.a.F0(a.b.a.e.g0.d.d(str), this.f360b);
            for (Map.Entry<JSONObject, Long> entry : jVar.f342b.entrySet()) {
                JSONObject key = entry.getKey();
                long jLongValue = entry.getValue().longValue();
                JSONObject jSONObjectOptJSONObject = key.optJSONObject("B");
                k(jSONObjectOptJSONObject);
                a.b.a.e.h0.b.e(jSONObjectOptJSONObject, str, a.b.a.a.a.c.a.P(a.b.a.e.e.b(), Boolean.FALSE), 1, strF0, false);
                a(jVar, key, jLongValue);
            }
        } catch (Exception e) {
            boolean z = this.f360b;
            a.b.a.e.l0.p.h("NonceUploaderEngine", "uploadNotGetNonce error,", e);
            a.b.a.a.a.c.a.w(z, "NonceUploaderEngine", "uploadNotGetNonce error,", e);
            a.b.a.a.a.c.a.y0("up", "s12", e);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final void o(j jVar, String str) {
        try {
            for (Map.Entry<JSONObject, Long> entry : jVar.f342b.entrySet()) {
                JSONObject key = entry.getKey();
                long jLongValue = entry.getValue().longValue();
                JSONObject jSONObjectOptJSONObject = key.optJSONObject("B");
                k(jSONObjectOptJSONObject);
                jSONObjectOptJSONObject.put("tz_content", str);
                jSONObjectOptJSONObject.put("tz_support", false);
                jSONObjectOptJSONObject.put("tz_cpuid", a.b.a.e.h0.b.g(null, false));
                a(jVar, key, jLongValue);
            }
        } catch (Exception e) {
            boolean z = this.f360b;
            a.b.a.e.l0.p.h("NonceUploaderEngine", "uploadNotSupportSign error,", e);
            a.b.a.a.a.c.a.w(z, "NonceUploaderEngine", "uploadNotSupportSign error,", e);
            a.b.a.a.a.c.a.y0("up", "s17", e);
        }
    }
}
