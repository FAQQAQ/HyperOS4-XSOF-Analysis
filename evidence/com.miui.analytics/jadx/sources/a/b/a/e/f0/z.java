package a.b.a.e.f0;

import android.database.Cursor;
import android.database.sqlite.SQLiteBlobTooBigException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.ArrayMap;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPOutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class z {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile z f397b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public y f398a = new y();

    public class a implements Callable<Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ a.b.a.e.f f399a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f400b;

        public a(a.b.a.e.f fVar, boolean z) {
            this.f399a = fVar;
            this.f400b = z;
        }

        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // java.util.concurrent.Callable
        public Boolean call() throws Exception {
            boolean zB = false;
            try {
                z.a(z.this, this.f399a, this.f400b);
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(this.f399a.e);
                String strF = z.this.f(jSONArray);
                byte[] bArrC = z.this.c(a.b.a.a.a.c.a.U0(strF));
                if (bArrC != null && bArrC.length != 0) {
                    a.b.a.e.l0.p.e(this.f399a.f311c, "UploaderEngine", "uploadAddDbFailedEvent before zip and encrypt, len=" + strF.length() + ", after=" + bArrC.length, this.f400b);
                    for (int i = 1; i >= 0 && !zB; i--) {
                        zB = z.b(z.this, bArrC, this.f399a.f311c, this.f400b);
                    }
                    return Boolean.valueOf(zB);
                }
                return Boolean.FALSE;
            } catch (Exception e) {
                a.b.a.e.l0.p.i(this.f399a.f311c, "UploaderEngine", "uploadAddDbFailedEvent call e: ", e, this.f400b);
                a.b.a.a.a.c.a.y0("sa", "s8", e);
            }
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f402a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f403b;

        public b(boolean z, boolean z2) {
            this.f402a = z;
            this.f403b = z2;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void a(z zVar, a.b.a.e.f fVar, boolean z) {
        String str;
        Objects.requireNonNull(zVar);
        JSONObject jSONObjectOptJSONObject = fVar.e.optJSONObject("H");
        if (jSONObjectOptJSONObject != null) {
            a.b.a.e.e0.l.b.f306a.b(jSONObjectOptJSONObject);
        }
        if ("onetrack_active".equals(fVar.f311c)) {
            JSONObject jSONObjectOptJSONObject2 = fVar.e.optJSONObject("B");
            if (jSONObjectOptJSONObject2 == null) {
                str = "body is null, return!";
            } else {
                if (!jSONObjectOptJSONObject2.has("tz_content")) {
                    try {
                        o.d().f((a.b.a.e.h0.b) fVar, z);
                        return;
                    } catch (Exception e) {
                        a.b.a.a.a.c.a.s(z, "UploaderEngine", "addParamsToEvent e: ", e);
                        a.b.a.a.a.c.a.y0("sa", "s7", e);
                        return;
                    }
                }
                str = "body has tz_content field, return!";
            }
            a.b.a.a.a.c.a.m(z, "UploaderEngine", str);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static boolean b(z zVar, byte[] bArr, String str, boolean z) {
        Objects.requireNonNull(zVar);
        try {
            ConcurrentHashMap<String, String> concurrentHashMap = a.b.a.e.y.f663c;
            String strL = a.b.a.e.i0.c.n().l(a.b.a.e.y.b.f667a.e(), bArr, "onetrack_active".equals(str));
            a.b.a.e.l0.p.e(str, "UploaderEngine", "doUpload response: " + strL, z);
            if (!TextUtils.isEmpty(strL)) {
                return zVar.g(strL).f402a;
            }
        } catch (Exception e) {
            a.b.a.e.l0.p.h("UploaderEngine", "doUpload get exception, ", e);
        }
        return false;
    }

    public static z e() {
        if (f397b == null) {
            synchronized (z.class) {
                if (f397b == null) {
                    f397b = new z();
                }
            }
        }
        return f397b;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public byte[] c(byte[] bArr) {
        if (bArr == null) {
            a.b.a.e.l0.p.g("UploaderEngine", "content is null");
            return null;
        }
        int i = a.b.a.e.g0.e.e;
        return a.b.a.e.g0.a.e(bArr, a.b.a.e.g0.c.a(a.b.a.e.g0.e.b.f436a.a(false)[0]));
    }

    public synchronized void d(int i) {
        y yVar = this.f398a;
        if (yVar != null) {
            yVar.b(i);
        } else {
            a.b.a.e.l0.p.g("UploaderEngine", "*** impossible, upload timer should not be null");
        }
    }

    public final String f(JSONArray jSONArray) {
        try {
            if (a.b.a.e.e0.d.b().e()) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    a.b.a.e.l0.i.a(jSONArray.getJSONObject(i));
                }
            }
            return jSONArray.toString();
        } catch (Exception e) {
            a.b.a.e.l0.p.h("UploaderEngine", "getPayload error: ", e);
            return null;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public b g(String str) {
        boolean z;
        boolean z2 = false;
        b bVar = new b(false, false);
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
                a.b.a.e.l0.p.c("UploaderEngine", "成功发送数据到服务端");
                if (a.b.a.e.l0.r.q() && jSONObject.has("ot_ri")) {
                    a.b.a.e.e0.m.b.f308a.e(jSONObject.optString("ot_ri"));
                }
                if (!a.b.a.e.l0.r.q() && jSONObject.has("log")) {
                    a.b.a.e.j0.a.a.a().b(jSONObject.optJSONObject("log"));
                }
                if (!a.b.a.b.a.f()) {
                    a.b.a.e.e0.a aVarD = a.b.a.e.e0.a.d();
                    Objects.requireNonNull(aVarD);
                    a.b.a.e.e0.b bVar2 = new a.b.a.e.e0.b(aVarD, jSONObject);
                    ThreadPoolExecutor threadPoolExecutor = a.b.a.e.l0.g.f580a;
                    try {
                        a.b.a.e.l0.g.f580a.execute(bVar2);
                    } catch (Throwable unused) {
                    }
                }
                z2 = true;
            } else if (iOptInt == -3) {
                a.b.a.e.l0.p.g("UploaderEngine", "signature expired, will update");
                int i = a.b.a.e.g0.e.e;
                a.b.a.e.g0.e.b.f436a.c(false);
            } else {
                a.b.a.e.l0.p.g("UploaderEngine", "Error: status code=" + iOptInt);
            }
            return new b(z2, z);
        } catch (Exception e) {
            a.b.a.e.l0.p.h("UploaderEngine", "parseUploadingResult exception ", e);
            a.b.a.a.a.c.a.y0("up", "s9", e);
            return bVar;
        }
    }

    public void h(ArrayMap<String, Integer> arrayMap, ArrayMap<String, Integer> arrayMap2) {
        try {
            if (arrayMap.size() <= 0 && arrayMap2.size() <= 0) {
                a.b.a.e.l0.p.c("UploaderEngine", "recordReportEventsToDB, this report has no monitor events, skip it");
                return;
            }
            for (String str : a.b.a.g.f.b.f891a) {
                int iIntValue = 0;
                int iIntValue2 = arrayMap.get(str) == null ? 0 : arrayMap.get(str).intValue();
                if (arrayMap2.get(str) != null) {
                    iIntValue = arrayMap2.get(str).intValue();
                }
                if (iIntValue2 != 0 || iIntValue != 0) {
                    a.b.a.e.l0.p.c("UploaderEngine", "recordReportEventsToDB, dbEventName: " + str + ", todayCount: " + iIntValue2 + ", notTodayCount: " + iIntValue);
                    a.b.a.g.c.c.g(false, str, iIntValue2 + iIntValue, "report_total", iIntValue2, "report_today");
                }
            }
        } catch (Exception e) {
            a.b.a.e.l0.p.h("UploaderEngine", "recordReportEventsToDB error: ", e);
        }
    }

    public void i(ArrayMap<String, Integer> arrayMap, ArrayMap<String, Integer> arrayMap2) {
        try {
            if (arrayMap.size() <= 0 && arrayMap2.size() <= 0) {
                a.b.a.e.l0.p.c("UploaderEngine", "recordReportSuccessEventsToDB, this report has no monitor events, skip it");
                return;
            }
            for (String str : a.b.a.g.f.b.f891a) {
                int iIntValue = 0;
                int iIntValue2 = arrayMap.get(str) == null ? 0 : arrayMap.get(str).intValue();
                if (arrayMap2.get(str) != null) {
                    iIntValue = arrayMap2.get(str).intValue();
                }
                if (iIntValue2 != 0 || iIntValue != 0) {
                    if (a.b.a.e.l0.p.f602a) {
                        a.b.a.e.l0.p.c("UploaderEngine", "recordReportSuccessEventsToDB, dbEventName: " + str + ", todayCount: " + iIntValue2 + ", notTodayCount: " + iIntValue);
                    }
                    a.b.a.g.c.c.g(false, str, iIntValue2 + iIntValue, "report_total_success", iIntValue2, "report_today_success");
                }
            }
        } catch (Exception e) {
            a.b.a.e.l0.p.h("UploaderEngine", "recordReportSuccessEventsToDB error: ", e);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final void j(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.has("id") || !jSONObject.has("app_id") || !jSONObject.has("rand_delay")) {
            StringBuilder sbC = a.a.a.a.a.c("invalid refresh: ");
            sbC.append(jSONObject.toString());
            a.b.a.e.l0.p.c("UploaderEngine", sbC.toString());
            return;
        }
        String string = jSONObject.getString("id");
        String strH = a.b.a.e.l0.u.h("refresh_cfg_cmd", null);
        boolean z = false;
        boolean z2 = true;
        if (strH != null) {
            JSONObject jSONObject2 = new JSONObject(strH);
            if (jSONObject2.has("id") && jSONObject2.has("app_id") && jSONObject2.has("delay_to")) {
                String string2 = jSONObject2.getString("id");
                String string3 = jSONObject2.getString("app_id");
                long j = jSONObject2.getLong("delay_to");
                long jCurrentTimeMillis = System.currentTimeMillis();
                if ((!string2.equals(string) && string3 != null) || Math.abs(jCurrentTimeMillis - j) > 86400000) {
                    a.b.a.e.l0.p.c("UploaderEngine", "not the same id, or over 1 day, prevId=" + string2 + ", curId=" + string + ", now=" + jCurrentTimeMillis + ", prevDelayTo=" + j);
                    z = true;
                }
                z2 = z;
            } else {
                a.b.a.e.l0.p.c("UploaderEngine", "invalid cfg " + strH);
            }
        }
        if (z2) {
            long j2 = jSONObject.getLong("rand_delay");
            jSONObject.put("delay_to", System.currentTimeMillis() + j2);
            a.b.a.e.l0.p.c("UploaderEngine", "save to pref " + jSONObject.toString());
            if (j2 > 0) {
                a.b.a.e.l0.u.m("refresh_cfg_cmd", jSONObject.toString());
                String string4 = jSONObject.getString("app_id");
                y yVar = this.f398a;
                Objects.requireNonNull(yVar);
                Message messageObtain = Message.obtain();
                messageObtain.what = 1001;
                Bundle bundle = new Bundle();
                bundle.putString("app_id", string4);
                messageObtain.setData(bundle);
                yVar.f387b.sendMessageDelayed(messageObtain, j2);
            }
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public boolean k(a.b.a.e.f fVar, boolean z) {
        String str;
        String str2;
        boolean zBooleanValue = false;
        if (fVar.e == null) {
            str = fVar.f311c;
            str2 = "uploadAddDbFailedEvent, event not available";
        } else if (!a.b.a.a.a.c.a.m0()) {
            str = fVar.f311c;
            str2 = "uploadAddDbFailedEvent, no network connected";
        } else if (a.b.a.e.e0.k.a(z)) {
            FutureTask futureTask = new FutureTask(new a(fVar, z));
            ThreadPoolExecutor threadPoolExecutor = a.b.a.e.l0.g.f580a;
            try {
                a.b.a.e.l0.g.f580a.execute(futureTask);
            } catch (Throwable unused) {
            }
            try {
                zBooleanValue = ((Boolean) futureTask.get(10L, TimeUnit.SECONDS)).booleanValue();
            } catch (Exception e) {
                a.b.a.e.l0.p.i(fVar.f311c, "UploaderEngine", "uploadAddDbFailedEvent uploadTask e: ", e, z);
            }
            str = fVar.f311c;
            str2 = "uploadAddDbFailedEvent result: " + zBooleanValue;
        } else {
            str = fVar.f311c;
            str2 = "uploadAddDbFailedEvent, not available, skip it";
        }
        a.b.a.e.l0.p.e(str, "UploaderEngine", str2, z);
        return zBooleanValue;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX WARN: Code duplicated, block: B:101:0x0292  */
    /* JADX WARN: Code duplicated, block: B:132:0x0325 A[Catch: all -> 0x0390, Exception -> 0x0394, SQLiteBlobTooBigException -> 0x0398, TRY_LEAVE, TryCatch #32 {SQLiteBlobTooBigException -> 0x0398, Exception -> 0x0394, all -> 0x0390, blocks: (B:130:0x031f, B:132:0x0325, B:149:0x0359, B:144:0x034e), top: B:333:0x031f }] */
    /* JADX WARN: Code duplicated, block: B:134:0x032b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:136:0x0337 A[Catch: all -> 0x0341, Exception -> 0x034a, SQLiteBlobTooBigException -> 0x034c, TRY_LEAVE, TryCatch #34 {SQLiteBlobTooBigException -> 0x034c, Exception -> 0x034a, all -> 0x0341, blocks: (B:135:0x032d, B:136:0x0337, B:118:0x02dd), top: B:330:0x032d }] */
    /* JADX WARN: Code duplicated, block: B:144:0x034e A[Catch: all -> 0x0390, Exception -> 0x0394, SQLiteBlobTooBigException -> 0x0398, TRY_ENTER, TryCatch #32 {SQLiteBlobTooBigException -> 0x0398, Exception -> 0x0394, all -> 0x0390, blocks: (B:130:0x031f, B:132:0x0325, B:149:0x0359, B:144:0x034e), top: B:333:0x031f }] */
    /* JADX WARN: Code duplicated, block: B:146:0x0354  */
    /* JADX WARN: Code duplicated, block: B:147:0x0356  */
    /* JADX WARN: Code duplicated, block: B:168:0x039c A[Catch: all -> 0x0135, TRY_LEAVE, TryCatch #9 {all -> 0x0135, blocks: (B:27:0x0125, B:28:0x0130, B:151:0x036e, B:152:0x037b, B:212:0x0421, B:214:0x0426, B:215:0x0428, B:226:0x045b, B:227:0x045e, B:223:0x0453, B:225:0x0458, B:168:0x039c), top: B:301:0x0125 }] */
    /* JADX WARN: Code duplicated, block: B:210:0x040f A[Catch: all -> 0x05d0, TryCatch #27 {all -> 0x05d0, blocks: (B:208:0x0406, B:210:0x040f, B:211:0x041a, B:219:0x0437, B:221:0x043e, B:222:0x0449), top: B:315:0x0406 }] */
    /* JADX WARN: Code duplicated, block: B:214:0x0426 A[Catch: all -> 0x0135, TryCatch #9 {all -> 0x0135, blocks: (B:27:0x0125, B:28:0x0130, B:151:0x036e, B:152:0x037b, B:212:0x0421, B:214:0x0426, B:215:0x0428, B:226:0x045b, B:227:0x045e, B:223:0x0453, B:225:0x0458, B:168:0x039c), top: B:301:0x0125 }] */
    /* JADX WARN: Code duplicated, block: B:221:0x043e A[Catch: all -> 0x05d0, TryCatch #27 {all -> 0x05d0, blocks: (B:208:0x0406, B:210:0x040f, B:211:0x041a, B:219:0x0437, B:221:0x043e, B:222:0x0449), top: B:315:0x0406 }] */
    /* JADX WARN: Code duplicated, block: B:225:0x0458 A[Catch: all -> 0x0135, TryCatch #9 {all -> 0x0135, blocks: (B:27:0x0125, B:28:0x0130, B:151:0x036e, B:152:0x037b, B:212:0x0421, B:214:0x0426, B:215:0x0428, B:226:0x045b, B:227:0x045e, B:223:0x0453, B:225:0x0458, B:168:0x039c), top: B:301:0x0125 }] */
    /* JADX WARN: Code duplicated, block: B:230:0x0463  */
    /* JADX WARN: Code duplicated, block: B:285:0x05da A[Catch: all -> 0x05e7, TryCatch #4 {all -> 0x05e7, blocks: (B:290:0x05e5, B:283:0x05d5, B:285:0x05da, B:286:0x05df, B:287:0x05e2), top: B:298:0x0013 }] */
    /* JADX WARN: Code duplicated, block: B:313:0x0276 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:330:0x032d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:343:0x05b1 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:69:0x01e3 A[Catch: all -> 0x02a3, TryCatch #2 {all -> 0x02a3, blocks: (B:62:0x01ba, B:65:0x01c2, B:69:0x01e3, B:71:0x01eb, B:73:0x01f3, B:75:0x01fb, B:81:0x0215, B:83:0x021d, B:84:0x0223), top: B:294:0x01ba }] */
    /* JADX WARN: Code duplicated, block: B:71:0x01eb A[Catch: all -> 0x02a3, TryCatch #2 {all -> 0x02a3, blocks: (B:62:0x01ba, B:65:0x01c2, B:69:0x01e3, B:71:0x01eb, B:73:0x01f3, B:75:0x01fb, B:81:0x0215, B:83:0x021d, B:84:0x0223), top: B:294:0x01ba }] */
    /* JADX WARN: Code duplicated, block: B:73:0x01f3 A[Catch: all -> 0x02a3, TryCatch #2 {all -> 0x02a3, blocks: (B:62:0x01ba, B:65:0x01c2, B:69:0x01e3, B:71:0x01eb, B:73:0x01f3, B:75:0x01fb, B:81:0x0215, B:83:0x021d, B:84:0x0223), top: B:294:0x01ba }] */
    /* JADX WARN: Code duplicated, block: B:75:0x01fb A[Catch: all -> 0x02a3, TryCatch #2 {all -> 0x02a3, blocks: (B:62:0x01ba, B:65:0x01c2, B:69:0x01e3, B:71:0x01eb, B:73:0x01f3, B:75:0x01fb, B:81:0x0215, B:83:0x021d, B:84:0x0223), top: B:294:0x01ba }] */
    /* JADX WARN: Code duplicated, block: B:81:0x0215 A[Catch: all -> 0x02a3, TryCatch #2 {all -> 0x02a3, blocks: (B:62:0x01ba, B:65:0x01c2, B:69:0x01e3, B:71:0x01eb, B:73:0x01f3, B:75:0x01fb, B:81:0x0215, B:83:0x021d, B:84:0x0223), top: B:294:0x01ba }] */
    /* JADX WARN: Code duplicated, block: B:83:0x021d A[Catch: all -> 0x02a3, TryCatch #2 {all -> 0x02a3, blocks: (B:62:0x01ba, B:65:0x01c2, B:69:0x01e3, B:71:0x01eb, B:73:0x01f3, B:75:0x01fb, B:81:0x0215, B:83:0x021d, B:84:0x0223), top: B:294:0x01ba }] */
    /* JADX WARN: Code duplicated, block: B:84:0x0223 A[Catch: all -> 0x02a3, TRY_LEAVE, TryCatch #2 {all -> 0x02a3, blocks: (B:62:0x01ba, B:65:0x01c2, B:69:0x01e3, B:71:0x01eb, B:73:0x01f3, B:75:0x01fb, B:81:0x0215, B:83:0x021d, B:84:0x0223), top: B:294:0x01ba }] */
    /* JADX WARN: Code duplicated, block: B:88:0x0249 A[Catch: all -> 0x029f, TryCatch #40 {all -> 0x029f, blocks: (B:86:0x0244, B:88:0x0249, B:90:0x024e, B:91:0x0253), top: B:325:0x0244 }] */
    /* JADX WARN: Code duplicated, block: B:90:0x024e A[Catch: all -> 0x029f, TryCatch #40 {all -> 0x029f, blocks: (B:86:0x0244, B:88:0x0249, B:90:0x024e, B:91:0x0253), top: B:325:0x0244 }] */
    /* JADX WARN: Unreachable blocks removed: 1, instructions: 1 */
    public b l(int i) throws Throwable {
        f fVar;
        GZIPOutputStream gZIPOutputStream;
        SQLiteDatabase sQLiteDatabaseG;
        int i2;
        Cursor cursor;
        f fVar2;
        boolean z;
        char c2;
        Cursor cursor2;
        int i3;
        f.c cVar;
        h hVar;
        int i4;
        ArrayList<Long> arrayList;
        z zVar;
        Cursor cursor3;
        f fVar3;
        ByteArrayOutputStream byteArrayOutputStream;
        int i5;
        int i6;
        ArrayList arrayList2;
        int i7;
        boolean z2;
        boolean z3;
        boolean z4;
        String strJ;
        boolean z5;
        boolean z6;
        int i8;
        int i9;
        int i10;
        String string;
        JSONObject jSONObject;
        int i11;
        String str;
        JSONObject jSONObjectOptJSONObject;
        boolean zB;
        long jOptLong;
        int i12;
        boolean zOptBoolean;
        JSONObject jSONObject2;
        int length;
        JSONObject jSONObjectOptJSONObject2;
        z zVar2 = this;
        a.b.a.e.l0.p.c("UploaderEngine", "即将读取数据库并上传数据");
        int size = 0;
        while (true) {
            f fVarP = f.p();
            synchronized (fVarP.f330a) {
                try {
                    try {
                        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                        Cursor cursor4 = null;
                        try {
                            a.b.a.e.l0.p.c("EventManager", "getEventsDataSync," + i);
                            long jCurrentTimeMillis = System.currentTimeMillis();
                            sQLiteDatabaseG = fVarP.f330a.G();
                            try {
                                Cursor cursorQuery = sQLiteDatabaseG.query("events", new String[]{"_id", "event_name", "appid", "priority", "data", "timestamp"}, null, null, null, null, "priority ASC, _id ASC", String.valueOf(300));
                                try {
                                    try {
                                        int columnIndex = cursorQuery.getColumnIndex("_id");
                                        int columnIndex2 = cursorQuery.getColumnIndex("appid");
                                        int columnIndex3 = cursorQuery.getColumnIndex("event_name");
                                        int columnIndex4 = cursorQuery.getColumnIndex("priority");
                                        int columnIndex5 = cursorQuery.getColumnIndex("data");
                                        int columnIndex6 = cursorQuery.getColumnIndex("timestamp");
                                        i2 = size;
                                        try {
                                            ArrayList arrayList3 = new ArrayList();
                                            ArrayMap<String, Integer> arrayMap = new ArrayMap<>();
                                            ArrayMap<String, Integer> arrayMap2 = new ArrayMap<>();
                                            new ArrayMap();
                                            ArrayList arrayList4 = arrayList3;
                                            gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream2);
                                            try {
                                                try {
                                                    boolean zE = a.b.a.e.e0.d.b().e();
                                                    gZIPOutputStream.write(91);
                                                    boolean z7 = true;
                                                    boolean z8 = true;
                                                    int i13 = 0;
                                                    int i14 = 0;
                                                    boolean z9 = false;
                                                    while (true) {
                                                        try {
                                                            if (cursorQuery.moveToNext()) {
                                                                try {
                                                                    long j = cursorQuery.getLong(columnIndex);
                                                                    long j2 = cursorQuery.getLong(columnIndex6);
                                                                    String string2 = cursorQuery.getString(columnIndex2);
                                                                    byte[] blob = cursorQuery.getBlob(columnIndex5);
                                                                    if (blob != null) {
                                                                        try {
                                                                            strJ = f.j(blob);
                                                                        } catch (Throwable th) {
                                                                            th = th;
                                                                            cursor4 = cursorQuery;
                                                                            fVar = fVarP;
                                                                            a.b.a.a.a.c.a.i(cursor4);
                                                                            if (sQLiteDatabaseG != null) {
                                                                                fVar.f330a.J();
                                                                            }
                                                                            a.b.a.a.a.c.a.g(gZIPOutputStream);
                                                                            throw th;
                                                                        }
                                                                    } else {
                                                                        strJ = null;
                                                                    }
                                                                    int i15 = cursorQuery.getInt(columnIndex4);
                                                                    try {
                                                                        try {
                                                                            try {
                                                                                try {
                                                                                    try {
                                                                                        if (z7) {
                                                                                            a.b.a.e.l0.p.c("EventManager", "priority[" + i + "], first record priority=" + i15);
                                                                                            if (i15 > i) {
                                                                                                a.b.a.e.l0.p.c("EventManager", "No records of priority[" + i + "], first record priority=" + i15);
                                                                                                try {
                                                                                                    a.b.a.a.a.c.a.i(cursorQuery);
                                                                                                    fVarP.f330a.J();
                                                                                                    a.b.a.a.a.c.a.g(gZIPOutputStream);
                                                                                                    hVar = null;
                                                                                                } catch (Throwable th2) {
                                                                                                    th = th2;
                                                                                                    throw th;
                                                                                                }
                                                                                            } else {
                                                                                                z5 = false;
                                                                                            }
                                                                                            z = true;
                                                                                            if (hVar != null || (arrayList = hVar.f336b) == null || arrayList.isEmpty() || hVar.f335a == null) {
                                                                                                a.b.a.e.l0.p.c("UploaderEngine", "满足条件的记录为空，即将返回, priority=" + i);
                                                                                                return new b(z, false);
                                                                                            }
                                                                                            size = hVar.f336b.size() + i2;
                                                                                            b bVar = new b(false, false);
                                                                                            try {
                                                                                                if (a.b.a.e.l0.p.f602a) {
                                                                                                    a.b.a.e.l0.p.c("UploaderEngine", "start to upload data: gzippedLen=" + hVar.f335a.length + ", ids: " + hVar.f336b.toString());
                                                                                                }
                                                                                                ConcurrentHashMap<String, String> concurrentHashMap = a.b.a.e.y.f663c;
                                                                                                String strE = a.b.a.e.y.b.f667a.e();
                                                                                                zVar = this;
                                                                                                try {
                                                                                                    byte[] bArrC = zVar.c(hVar.f335a);
                                                                                                    if (bArrC == null || bArrC.length == 0) {
                                                                                                        a.b.a.e.l0.p.s("UploaderEngine", "zip and encrypt failed!");
                                                                                                    } else {
                                                                                                        a.b.a.e.l0.p.c("UploaderEngine", "before zip and encrypt, after=" + bArrC.length);
                                                                                                        zVar.h(hVar.e, hVar.f);
                                                                                                        String strL = a.b.a.e.i0.c.n().l(strE, bArrC, hVar.f338d);
                                                                                                        a.b.a.e.l0.p.c("UploaderEngine", "sendDataToServer response: " + strL);
                                                                                                        if (!TextUtils.isEmpty(strL)) {
                                                                                                            bVar = zVar.g(strL);
                                                                                                        }
                                                                                                    }
                                                                                                } catch (Exception e) {
                                                                                                    e = e;
                                                                                                    a.b.a.e.l0.p.h("UploaderEngine", "Exception while uploading ", e);
                                                                                                    a.b.a.a.a.c.a.y0("up", "s5", e);
                                                                                                }
                                                                                            } catch (Exception e2) {
                                                                                                e = e2;
                                                                                                zVar = this;
                                                                                            }
                                                                                            if (!bVar.f402a) {
                                                                                                return bVar;
                                                                                            }
                                                                                            zVar.i(hVar.e, hVar.f);
                                                                                            if (f.p().k(hVar.f336b) == 0) {
                                                                                                a.b.a.e.l0.p.h("UploaderEngine", "delete DB failed!", new Throwable());
                                                                                                break;
                                                                                            }
                                                                                            if (bVar.f403b) {
                                                                                                return bVar;
                                                                                            }
                                                                                            if (hVar.f337c) {
                                                                                                a.b.a.e.l0.p.c("UploaderEngine", "No more records for prio=" + i);
                                                                                                break;
                                                                                            }
                                                                                            zVar2 = zVar;
                                                                                        } else {
                                                                                            z5 = z7;
                                                                                        }
                                                                                        if (a.b.a.e.l0.p.f603b || i15 <= i) {
                                                                                            z6 = z5;
                                                                                        } else {
                                                                                            z6 = z5;
                                                                                            if (Math.abs(jCurrentTimeMillis - j2) < a.b.a.e.e0.k.h(i15)) {
                                                                                                z6 = z6;
                                                                                                i11 = columnIndex4;
                                                                                                i12 = columnIndex3;
                                                                                            }
                                                                                            z7 = z6;
                                                                                            columnIndex4 = i11;
                                                                                            columnIndex3 = i12;
                                                                                        }
                                                                                        jSONObjectOptJSONObject = jSONObject.optJSONObject("H");
                                                                                        z6 = z6;
                                                                                        i11 = columnIndex4;
                                                                                    } catch (Exception e3) {
                                                                                        e = e3;
                                                                                        i11 = columnIndex4;
                                                                                        a.b.a.e.l0.p.h("EventManager", "*** error ***", e);
                                                                                        if (blob != null) {
                                                                                            strJ = f.A(blob);
                                                                                            jSONObject = new JSONObject(strJ);
                                                                                            jSONObjectOptJSONObject = jSONObject.optJSONObject("H");
                                                                                        } else {
                                                                                            str = strJ;
                                                                                            jSONObjectOptJSONObject = null;
                                                                                        }
                                                                                        zB = a.b.a.e.e0.l.b.f306a.b(jSONObjectOptJSONObject);
                                                                                        jOptLong = jSONObjectOptJSONObject.optLong("e_ts");
                                                                                        i12 = columnIndex3;
                                                                                        zOptBoolean = jSONObjectOptJSONObject.optBoolean("ot_ad", false);
                                                                                        if (zB) {
                                                                                            if (!"001".equals(string2)) {
                                                                                                a.b.a.e.e0.m.b.f308a.b(jSONObjectOptJSONObject);
                                                                                            } else if ("onetrack_active".equals(string)) {
                                                                                                jSONObjectOptJSONObject2 = jSONObject.optJSONObject("B");
                                                                                                if (jSONObjectOptJSONObject2 == null) {
                                                                                                }
                                                                                                z7 = z6;
                                                                                                columnIndex4 = i11;
                                                                                                columnIndex3 = i12;
                                                                                            } else if (TextUtils.equals(string, "onetrack_installed_app")) {
                                                                                                a.b.a.e.e0.m.b.f308a.a(jSONObjectOptJSONObject);
                                                                                            }
                                                                                            int i16 = i11;
                                                                                            f fVar4 = fVarP;
                                                                                            i8 = i12;
                                                                                            i9 = columnIndex2;
                                                                                            i10 = columnIndex;
                                                                                            cursor3 = cursorQuery;
                                                                                            fVar3 = fVarP;
                                                                                            byteArrayOutputStream = byteArrayOutputStream2;
                                                                                            i5 = 300;
                                                                                            i6 = i16;
                                                                                            jSONObject2 = jSONObject;
                                                                                            fVar4.q(arrayMap, arrayMap2, string, jSONObject, jOptLong, zOptBoolean);
                                                                                            if (zE) {
                                                                                                a.b.a.e.l0.i.a(jSONObject2);
                                                                                            }
                                                                                            if (!z8) {
                                                                                                gZIPOutputStream.write(44);
                                                                                            }
                                                                                            gZIPOutputStream.write(jSONObject2.toString().getBytes(StandardCharsets.UTF_8));
                                                                                            arrayList2 = arrayList4;
                                                                                            arrayList2.add(Long.valueOf(j));
                                                                                            i13++;
                                                                                            length = i14 + str.length();
                                                                                            if (length >= 512000) {
                                                                                                a.b.a.e.l0.p.c("EventManager", "reached max len: " + length);
                                                                                                i7 = i13;
                                                                                                z2 = z9;
                                                                                                if (arrayList2.size() <= 0) {
                                                                                                    if (!cursor3.isAfterLast()) {
                                                                                                        if (cursor3.getInt(i6) > i) {
                                                                                                            z3 = true;
                                                                                                        } else {
                                                                                                            z3 = false;
                                                                                                        }
                                                                                                        z4 = z3;
                                                                                                    } else if (i7 == i5) {
                                                                                                        a.b.a.e.l0.p.c("EventManager", "cursor max one query number");
                                                                                                        z4 = false;
                                                                                                    } else {
                                                                                                        a.b.a.e.l0.p.c("EventManager", "cursor isAfterLast");
                                                                                                        z4 = true;
                                                                                                    }
                                                                                                    gZIPOutputStream.write(93);
                                                                                                    gZIPOutputStream.close();
                                                                                                    h hVar2 = new h(byteArrayOutputStream.toByteArray(), arrayList2, z4, z2, arrayMap, arrayMap2);
                                                                                                    a.b.a.a.a.c.a.i(cursor3);
                                                                                                    fVar3.f330a.J();
                                                                                                    a.b.a.a.a.c.a.g(null);
                                                                                                    hVar = hVar2;
                                                                                                    z = true;
                                                                                                } else {
                                                                                                    a.b.a.a.a.c.a.i(cursor3);
                                                                                                    fVar3.f330a.J();
                                                                                                    z = true;
                                                                                                    a.b.a.a.a.c.a.g(gZIPOutputStream);
                                                                                                    hVar = null;
                                                                                                }
                                                                                            } else {
                                                                                                i14 = length;
                                                                                                z8 = false;
                                                                                                cursorQuery = cursor3;
                                                                                                arrayList4 = arrayList2;
                                                                                                columnIndex4 = i6;
                                                                                                z7 = z6;
                                                                                                columnIndex3 = i8;
                                                                                                fVarP = fVar3;
                                                                                                columnIndex2 = i9;
                                                                                                byteArrayOutputStream2 = byteArrayOutputStream;
                                                                                                columnIndex = i10;
                                                                                            }
                                                                                        } else {
                                                                                            try {
                                                                                                try {
                                                                                                    try {
                                                                                                        if (!"001".equals(string2)) {
                                                                                                            a.b.a.e.e0.m.b.f308a.b(jSONObjectOptJSONObject);
                                                                                                        } else if ("onetrack_active".equals(string)) {
                                                                                                            jSONObjectOptJSONObject2 = jSONObject.optJSONObject("B");
                                                                                                            if (jSONObjectOptJSONObject2 == null) {
                                                                                                            }
                                                                                                            z7 = z6;
                                                                                                            columnIndex4 = i11;
                                                                                                            columnIndex3 = i12;
                                                                                                        } else if (TextUtils.equals(string, "onetrack_installed_app")) {
                                                                                                            a.b.a.e.e0.m.b.f308a.a(jSONObjectOptJSONObject);
                                                                                                        }
                                                                                                        arrayList2.add(Long.valueOf(j));
                                                                                                        i13++;
                                                                                                        length = i14 + str.length();
                                                                                                        if (length >= 512000) {
                                                                                                            try {
                                                                                                                a.b.a.e.l0.p.c("EventManager", "reached max len: " + length);
                                                                                                                i7 = i13;
                                                                                                                z2 = z9;
                                                                                                                if (arrayList2.size() <= 0) {
                                                                                                                    if (!cursor3.isAfterLast()) {
                                                                                                                        if (cursor3.getInt(i6) > i) {
                                                                                                                            z3 = true;
                                                                                                                        } else {
                                                                                                                            z3 = false;
                                                                                                                        }
                                                                                                                        z4 = z3;
                                                                                                                    } else if (i7 == i5) {
                                                                                                                        try {
                                                                                                                            a.b.a.e.l0.p.c("EventManager", "cursor max one query number");
                                                                                                                            z4 = false;
                                                                                                                        } catch (SQLiteBlobTooBigException e4) {
                                                                                                                            e = e4;
                                                                                                                            cursor2 = cursor3;
                                                                                                                            fVar2 = fVar3;
                                                                                                                            z = true;
                                                                                                                            c2 = 3;
                                                                                                                            i4 = f.f327b + (z ? 1 : 0);
                                                                                                                            f.f327b = i4;
                                                                                                                            if (i4 <= c2) {
                                                                                                                                a.b.a.a.a.c.a.O0(a.b.a.e.e.b(), "EventManager", "getEventsDataSync SQLiteBlobTooBigException", e);
                                                                                                                            }
                                                                                                                            a.b.a.e.l0.p.h("EventManager", "blob too big ***", e);
                                                                                                                            fVar2.l();
                                                                                                                            a.b.a.a.a.c.a.i(cursor2);
                                                                                                                            if (sQLiteDatabaseG != null) {
                                                                                                                                cVar = fVar2.f330a;
                                                                                                                                cVar.J();
                                                                                                                            }
                                                                                                                            a.b.a.a.a.c.a.g(gZIPOutputStream);
                                                                                                                            hVar = null;
                                                                                                                        } catch (Exception e5) {
                                                                                                                            e = e5;
                                                                                                                            cursor = cursor3;
                                                                                                                            fVar = fVar3;
                                                                                                                            try {
                                                                                                                                z = true;
                                                                                                                                z = true;
                                                                                                                                i3 = f.f327b + 1;
                                                                                                                                f.f327b = i3;
                                                                                                                                if (i3 <= 3) {
                                                                                                                                    a.b.a.a.a.c.a.O0(a.b.a.e.e.b(), "EventManager", "getEventsDataSync Exception", e);
                                                                                                                                }
                                                                                                                                a.b.a.e.l0.p.h("EventManager", "getEventsDataSync error", e);
                                                                                                                                a.b.a.a.a.c.a.i(cursor);
                                                                                                                                if (sQLiteDatabaseG != null) {
                                                                                                                                    cVar = fVar.f330a;
                                                                                                                                    cVar.J();
                                                                                                                                }
                                                                                                                                a.b.a.a.a.c.a.g(gZIPOutputStream);
                                                                                                                                hVar = null;
                                                                                                                            } catch (Throwable th3) {
                                                                                                                                th = th3;
                                                                                                                                cursor4 = cursor;
                                                                                                                                a.b.a.a.a.c.a.i(cursor4);
                                                                                                                                if (sQLiteDatabaseG != null) {
                                                                                                                                    fVar.f330a.J();
                                                                                                                                }
                                                                                                                                a.b.a.a.a.c.a.g(gZIPOutputStream);
                                                                                                                                throw th;
                                                                                                                            }
                                                                                                                        } catch (Throwable th4) {
                                                                                                                            th = th4;
                                                                                                                            cursor4 = cursor3;
                                                                                                                            fVar = fVar3;
                                                                                                                            a.b.a.a.a.c.a.i(cursor4);
                                                                                                                            if (sQLiteDatabaseG != null) {
                                                                                                                                fVar.f330a.J();
                                                                                                                            }
                                                                                                                            a.b.a.a.a.c.a.g(gZIPOutputStream);
                                                                                                                            throw th;
                                                                                                                        }
                                                                                                                    } else {
                                                                                                                        a.b.a.e.l0.p.c("EventManager", "cursor isAfterLast");
                                                                                                                        z4 = true;
                                                                                                                    }
                                                                                                                    gZIPOutputStream.write(93);
                                                                                                                    gZIPOutputStream.close();
                                                                                                                    try {
                                                                                                                        h hVar3 = new h(byteArrayOutputStream.toByteArray(), arrayList2, z4, z2, arrayMap, arrayMap2);
                                                                                                                        a.b.a.a.a.c.a.i(cursor3);
                                                                                                                        fVar3.f330a.J();
                                                                                                                        a.b.a.a.a.c.a.g(null);
                                                                                                                        hVar = hVar3;
                                                                                                                        z = true;
                                                                                                                    } catch (SQLiteBlobTooBigException e6) {
                                                                                                                        e = e6;
                                                                                                                        fVar2 = fVar3;
                                                                                                                        cursor2 = cursor3;
                                                                                                                        gZIPOutputStream = null;
                                                                                                                        z = true;
                                                                                                                        c2 = 3;
                                                                                                                        i4 = f.f327b + (z ? 1 : 0);
                                                                                                                        f.f327b = i4;
                                                                                                                        if (i4 <= c2) {
                                                                                                                            a.b.a.a.a.c.a.O0(a.b.a.e.e.b(), "EventManager", "getEventsDataSync SQLiteBlobTooBigException", e);
                                                                                                                        }
                                                                                                                        a.b.a.e.l0.p.h("EventManager", "blob too big ***", e);
                                                                                                                        fVar2.l();
                                                                                                                        a.b.a.a.a.c.a.i(cursor2);
                                                                                                                        if (sQLiteDatabaseG != null) {
                                                                                                                            cVar = fVar2.f330a;
                                                                                                                            cVar.J();
                                                                                                                        }
                                                                                                                        a.b.a.a.a.c.a.g(gZIPOutputStream);
                                                                                                                        hVar = null;
                                                                                                                    } catch (Exception e7) {
                                                                                                                        e = e7;
                                                                                                                        fVar = fVar3;
                                                                                                                        cursor = cursor3;
                                                                                                                        gZIPOutputStream = null;
                                                                                                                        z = true;
                                                                                                                        z = true;
                                                                                                                        i3 = f.f327b + 1;
                                                                                                                        f.f327b = i3;
                                                                                                                        if (i3 <= 3) {
                                                                                                                            a.b.a.a.a.c.a.O0(a.b.a.e.e.b(), "EventManager", "getEventsDataSync Exception", e);
                                                                                                                        }
                                                                                                                        a.b.a.e.l0.p.h("EventManager", "getEventsDataSync error", e);
                                                                                                                        a.b.a.a.a.c.a.i(cursor);
                                                                                                                        if (sQLiteDatabaseG != null) {
                                                                                                                            cVar = fVar.f330a;
                                                                                                                            cVar.J();
                                                                                                                        }
                                                                                                                        a.b.a.a.a.c.a.g(gZIPOutputStream);
                                                                                                                        hVar = null;
                                                                                                                    } catch (Throwable th5) {
                                                                                                                        th = th5;
                                                                                                                        fVar = fVar3;
                                                                                                                        gZIPOutputStream = null;
                                                                                                                        cursor4 = cursor3;
                                                                                                                        a.b.a.a.a.c.a.i(cursor4);
                                                                                                                        if (sQLiteDatabaseG != null) {
                                                                                                                            fVar.f330a.J();
                                                                                                                        }
                                                                                                                        a.b.a.a.a.c.a.g(gZIPOutputStream);
                                                                                                                        throw th;
                                                                                                                    }
                                                                                                                } else {
                                                                                                                    a.b.a.a.a.c.a.i(cursor3);
                                                                                                                    fVar3.f330a.J();
                                                                                                                    z = true;
                                                                                                                    a.b.a.a.a.c.a.g(gZIPOutputStream);
                                                                                                                    hVar = null;
                                                                                                                }
                                                                                                            } catch (Throwable th6) {
                                                                                                                th = th6;
                                                                                                                i14 = length;
                                                                                                                z8 = false;
                                                                                                                a.b.a.e.l0.p.h("EventManager", "Finally *** error ***", th);
                                                                                                                cursorQuery = cursor3;
                                                                                                                arrayList4 = arrayList2;
                                                                                                                columnIndex4 = i6;
                                                                                                                z7 = z6;
                                                                                                                columnIndex3 = i8;
                                                                                                                fVarP = fVar3;
                                                                                                                columnIndex2 = i9;
                                                                                                                byteArrayOutputStream2 = byteArrayOutputStream;
                                                                                                                columnIndex = i10;
                                                                                                            }
                                                                                                        } else {
                                                                                                            i14 = length;
                                                                                                            z8 = false;
                                                                                                            cursorQuery = cursor3;
                                                                                                            arrayList4 = arrayList2;
                                                                                                            columnIndex4 = i6;
                                                                                                            z7 = z6;
                                                                                                            columnIndex3 = i8;
                                                                                                            fVarP = fVar3;
                                                                                                            columnIndex2 = i9;
                                                                                                            byteArrayOutputStream2 = byteArrayOutputStream;
                                                                                                            columnIndex = i10;
                                                                                                        }
                                                                                                    } catch (Throwable th7) {
                                                                                                        th = th7;
                                                                                                    }
                                                                                                    arrayList2 = arrayList4;
                                                                                                } catch (Throwable th8) {
                                                                                                    th = th8;
                                                                                                    arrayList2 = arrayList4;
                                                                                                }
                                                                                                fVar4.q(arrayMap, arrayMap2, string, jSONObject, jOptLong, zOptBoolean);
                                                                                                if (zE) {
                                                                                                    a.b.a.e.l0.i.a(jSONObject2);
                                                                                                }
                                                                                                if (!z8) {
                                                                                                    gZIPOutputStream.write(44);
                                                                                                }
                                                                                                gZIPOutputStream.write(jSONObject2.toString().getBytes(StandardCharsets.UTF_8));
                                                                                            } catch (Throwable th9) {
                                                                                                th = th9;
                                                                                                arrayList2 = arrayList4;
                                                                                                a.b.a.e.l0.p.h("EventManager", "Finally *** error ***", th);
                                                                                                cursorQuery = cursor3;
                                                                                                arrayList4 = arrayList2;
                                                                                                columnIndex4 = i6;
                                                                                                z7 = z6;
                                                                                                columnIndex3 = i8;
                                                                                                fVarP = fVar3;
                                                                                                columnIndex2 = i9;
                                                                                                byteArrayOutputStream2 = byteArrayOutputStream;
                                                                                                columnIndex = i10;
                                                                                            }
                                                                                            int i17 = i11;
                                                                                            f fVar5 = fVarP;
                                                                                            i8 = i12;
                                                                                            i9 = columnIndex2;
                                                                                            i10 = columnIndex;
                                                                                            cursor3 = cursorQuery;
                                                                                            fVar3 = fVarP;
                                                                                            byteArrayOutputStream = byteArrayOutputStream2;
                                                                                            i5 = 300;
                                                                                            i6 = i17;
                                                                                            jSONObject2 = jSONObject;
                                                                                        }
                                                                                        if (hVar != null) {
                                                                                        }
                                                                                        a.b.a.e.l0.p.c("UploaderEngine", "满足条件的记录为空，即将返回, priority=" + i);
                                                                                        return new b(z, false);
                                                                                    }
                                                                                    jSONObject = new JSONObject(strJ);
                                                                                } catch (Exception e8) {
                                                                                    e = e8;
                                                                                    jSONObject = null;
                                                                                }
                                                                                zOptBoolean = jSONObjectOptJSONObject.optBoolean("ot_ad", false);
                                                                                if (zB || zOptBoolean || System.currentTimeMillis() - jOptLong > 600000) {
                                                                                    if (!"001".equals(string2)) {
                                                                                        a.b.a.e.e0.m.b.f308a.b(jSONObjectOptJSONObject);
                                                                                    } else if ("onetrack_active".equals(string)) {
                                                                                        jSONObjectOptJSONObject2 = jSONObject.optJSONObject("B");
                                                                                        if (jSONObjectOptJSONObject2 == null && jSONObjectOptJSONObject2.has("tz_content")) {
                                                                                            z9 = true;
                                                                                        } else {
                                                                                            z7 = z6;
                                                                                            columnIndex4 = i11;
                                                                                            columnIndex3 = i12;
                                                                                        }
                                                                                    } else if (TextUtils.equals(string, "onetrack_installed_app")) {
                                                                                        a.b.a.e.e0.m.b.f308a.a(jSONObjectOptJSONObject);
                                                                                    }
                                                                                    int i18 = i11;
                                                                                    f fVar6 = fVarP;
                                                                                    i8 = i12;
                                                                                    i9 = columnIndex2;
                                                                                    i10 = columnIndex;
                                                                                    cursor3 = cursorQuery;
                                                                                    fVar3 = fVarP;
                                                                                    byteArrayOutputStream = byteArrayOutputStream2;
                                                                                    i5 = 300;
                                                                                    i6 = i18;
                                                                                    jSONObject2 = jSONObject;
                                                                                    fVar6.q(arrayMap, arrayMap2, string, jSONObject, jOptLong, zOptBoolean);
                                                                                    if (zE) {
                                                                                        a.b.a.e.l0.i.a(jSONObject2);
                                                                                    }
                                                                                    if (!z8) {
                                                                                        gZIPOutputStream.write(44);
                                                                                    }
                                                                                    gZIPOutputStream.write(jSONObject2.toString().getBytes(StandardCharsets.UTF_8));
                                                                                    arrayList2 = arrayList4;
                                                                                    arrayList2.add(Long.valueOf(j));
                                                                                    i13++;
                                                                                    length = i14 + str.length();
                                                                                    if (length >= 512000) {
                                                                                        a.b.a.e.l0.p.c("EventManager", "reached max len: " + length);
                                                                                    } else {
                                                                                        i14 = length;
                                                                                        z8 = false;
                                                                                    }
                                                                                } else {
                                                                                    i10 = columnIndex;
                                                                                    cursor3 = cursorQuery;
                                                                                    fVar3 = fVarP;
                                                                                    byteArrayOutputStream = byteArrayOutputStream2;
                                                                                    arrayList2 = arrayList4;
                                                                                    i6 = i11;
                                                                                    i8 = i12;
                                                                                    i9 = columnIndex2;
                                                                                }
                                                                            } catch (Throwable th10) {
                                                                                th = th10;
                                                                                i10 = columnIndex;
                                                                                cursor3 = cursorQuery;
                                                                                fVar3 = fVarP;
                                                                                byteArrayOutputStream = byteArrayOutputStream2;
                                                                                arrayList2 = arrayList4;
                                                                                i6 = i11;
                                                                                i8 = i12;
                                                                                i9 = columnIndex2;
                                                                            }
                                                                            zB = a.b.a.e.e0.l.b.f306a.b(jSONObjectOptJSONObject);
                                                                            jOptLong = jSONObjectOptJSONObject.optLong("e_ts");
                                                                            i12 = columnIndex3;
                                                                        } catch (Throwable th11) {
                                                                            th = th11;
                                                                            i9 = columnIndex2;
                                                                            i10 = columnIndex;
                                                                            cursor3 = cursorQuery;
                                                                            fVar3 = fVarP;
                                                                            byteArrayOutputStream = byteArrayOutputStream2;
                                                                            arrayList2 = arrayList4;
                                                                            i6 = i11;
                                                                            i8 = columnIndex3;
                                                                        }
                                                                        string = cursorQuery.getString(columnIndex3);
                                                                        str = strJ;
                                                                    } catch (Throwable th12) {
                                                                        th = th12;
                                                                        z6 = z6;
                                                                        i8 = columnIndex3;
                                                                        i9 = columnIndex2;
                                                                        i10 = columnIndex;
                                                                        cursor3 = cursorQuery;
                                                                        fVar3 = fVarP;
                                                                        byteArrayOutputStream = byteArrayOutputStream2;
                                                                        i6 = columnIndex4;
                                                                    }
                                                                    cursorQuery = cursor3;
                                                                    arrayList4 = arrayList2;
                                                                    columnIndex4 = i6;
                                                                    z7 = z6;
                                                                    columnIndex3 = i8;
                                                                    fVarP = fVar3;
                                                                    columnIndex2 = i9;
                                                                    byteArrayOutputStream2 = byteArrayOutputStream;
                                                                    columnIndex = i10;
                                                                } catch (SQLiteBlobTooBigException e9) {
                                                                    e = e9;
                                                                    cursor3 = cursorQuery;
                                                                    fVar3 = fVarP;
                                                                    cursor2 = cursor3;
                                                                    fVar2 = fVar3;
                                                                    z = true;
                                                                    c2 = 3;
                                                                    i4 = f.f327b + (z ? 1 : 0);
                                                                    f.f327b = i4;
                                                                    if (i4 <= c2) {
                                                                        a.b.a.a.a.c.a.O0(a.b.a.e.e.b(), "EventManager", "getEventsDataSync SQLiteBlobTooBigException", e);
                                                                    }
                                                                    a.b.a.e.l0.p.h("EventManager", "blob too big ***", e);
                                                                    fVar2.l();
                                                                    a.b.a.a.a.c.a.i(cursor2);
                                                                    if (sQLiteDatabaseG != null) {
                                                                        cVar = fVar2.f330a;
                                                                        cVar.J();
                                                                    }
                                                                    a.b.a.a.a.c.a.g(gZIPOutputStream);
                                                                    hVar = null;
                                                                    if (hVar != null) {
                                                                    }
                                                                    a.b.a.e.l0.p.c("UploaderEngine", "满足条件的记录为空，即将返回, priority=" + i);
                                                                    return new b(z, false);
                                                                } catch (Exception e10) {
                                                                    e = e10;
                                                                    cursor3 = cursorQuery;
                                                                    fVar3 = fVarP;
                                                                    cursor = cursor3;
                                                                    fVar = fVar3;
                                                                    z = true;
                                                                    z = true;
                                                                    i3 = f.f327b + 1;
                                                                    f.f327b = i3;
                                                                    if (i3 <= 3) {
                                                                        a.b.a.a.a.c.a.O0(a.b.a.e.e.b(), "EventManager", "getEventsDataSync Exception", e);
                                                                    }
                                                                    a.b.a.e.l0.p.h("EventManager", "getEventsDataSync error", e);
                                                                    a.b.a.a.a.c.a.i(cursor);
                                                                    if (sQLiteDatabaseG != null) {
                                                                        cVar = fVar.f330a;
                                                                        cVar.J();
                                                                    }
                                                                    a.b.a.a.a.c.a.g(gZIPOutputStream);
                                                                    hVar = null;
                                                                    if (hVar != null) {
                                                                    }
                                                                    a.b.a.e.l0.p.c("UploaderEngine", "满足条件的记录为空，即将返回, priority=" + i);
                                                                    return new b(z, false);
                                                                } catch (Throwable th13) {
                                                                    th = th13;
                                                                    cursor3 = cursorQuery;
                                                                    fVar3 = fVarP;
                                                                    cursor4 = cursor3;
                                                                    fVar = fVar3;
                                                                    a.b.a.a.a.c.a.i(cursor4);
                                                                    if (sQLiteDatabaseG != null) {
                                                                        fVar.f330a.J();
                                                                    }
                                                                    a.b.a.a.a.c.a.g(gZIPOutputStream);
                                                                    throw th;
                                                                }
                                                            } else {
                                                                cursor3 = cursorQuery;
                                                                fVar3 = fVarP;
                                                                byteArrayOutputStream = byteArrayOutputStream2;
                                                                i5 = 300;
                                                                i6 = columnIndex4;
                                                                arrayList2 = arrayList4;
                                                            }
                                                            if (arrayList2.size() <= 0) {
                                                                a.b.a.a.a.c.a.i(cursor3);
                                                                fVar3.f330a.J();
                                                                z = true;
                                                                a.b.a.a.a.c.a.g(gZIPOutputStream);
                                                                hVar = null;
                                                                if (hVar != null) {
                                                                }
                                                                a.b.a.e.l0.p.c("UploaderEngine", "满足条件的记录为空，即将返回, priority=" + i);
                                                                return new b(z, false);
                                                            }
                                                            if (!cursor3.isAfterLast()) {
                                                                if (cursor3.getInt(i6) > i) {
                                                                    z3 = true;
                                                                } else {
                                                                    z3 = false;
                                                                }
                                                                z4 = z3;
                                                            } else if (i7 == i5) {
                                                                a.b.a.e.l0.p.c("EventManager", "cursor max one query number");
                                                                z4 = false;
                                                            } else {
                                                                a.b.a.e.l0.p.c("EventManager", "cursor isAfterLast");
                                                                z4 = true;
                                                            }
                                                            gZIPOutputStream.write(93);
                                                            gZIPOutputStream.close();
                                                            h hVar4 = new h(byteArrayOutputStream.toByteArray(), arrayList2, z4, z2, arrayMap, arrayMap2);
                                                            a.b.a.a.a.c.a.i(cursor3);
                                                            fVar3.f330a.J();
                                                            a.b.a.a.a.c.a.g(null);
                                                            hVar = hVar4;
                                                            z = true;
                                                            if (hVar != null) {
                                                            }
                                                            a.b.a.e.l0.p.c("UploaderEngine", "满足条件的记录为空，即将返回, priority=" + i);
                                                            return new b(z, false);
                                                        } catch (SQLiteBlobTooBigException e11) {
                                                            e = e11;
                                                            fVar2 = fVar3;
                                                            cursor2 = cursor3;
                                                            z = true;
                                                            c2 = 3;
                                                            i4 = f.f327b + (z ? 1 : 0);
                                                            f.f327b = i4;
                                                            if (i4 <= c2) {
                                                                a.b.a.a.a.c.a.O0(a.b.a.e.e.b(), "EventManager", "getEventsDataSync SQLiteBlobTooBigException", e);
                                                            }
                                                            a.b.a.e.l0.p.h("EventManager", "blob too big ***", e);
                                                            fVar2.l();
                                                            a.b.a.a.a.c.a.i(cursor2);
                                                            if (sQLiteDatabaseG != null) {
                                                                cVar = fVar2.f330a;
                                                                cVar.J();
                                                            }
                                                        } catch (Exception e12) {
                                                            e = e12;
                                                            fVar = fVar3;
                                                            cursor = cursor3;
                                                            z = true;
                                                            z = true;
                                                            i3 = f.f327b + 1;
                                                            f.f327b = i3;
                                                            if (i3 <= 3) {
                                                                a.b.a.a.a.c.a.O0(a.b.a.e.e.b(), "EventManager", "getEventsDataSync Exception", e);
                                                            }
                                                            a.b.a.e.l0.p.h("EventManager", "getEventsDataSync error", e);
                                                            a.b.a.a.a.c.a.i(cursor);
                                                            if (sQLiteDatabaseG != null) {
                                                                cVar = fVar.f330a;
                                                                cVar.J();
                                                            }
                                                        } catch (Throwable th14) {
                                                            th = th14;
                                                            fVar = fVar3;
                                                            cursor4 = cursor3;
                                                            a.b.a.a.a.c.a.i(cursor4);
                                                            if (sQLiteDatabaseG != null) {
                                                                fVar.f330a.J();
                                                            }
                                                            a.b.a.a.a.c.a.g(gZIPOutputStream);
                                                            throw th;
                                                        }
                                                        i7 = i13;
                                                        z2 = z9;
                                                    }
                                                } catch (Throwable th15) {
                                                    th = th15;
                                                    cursor3 = cursorQuery;
                                                    fVar = fVarP;
                                                }
                                            } catch (SQLiteBlobTooBigException e13) {
                                                e = e13;
                                                cursor3 = cursorQuery;
                                                fVar2 = fVarP;
                                                cursor2 = cursor3;
                                                z = true;
                                                c2 = 3;
                                                i4 = f.f327b + (z ? 1 : 0);
                                                f.f327b = i4;
                                                if (i4 <= c2) {
                                                    a.b.a.a.a.c.a.O0(a.b.a.e.e.b(), "EventManager", "getEventsDataSync SQLiteBlobTooBigException", e);
                                                }
                                                a.b.a.e.l0.p.h("EventManager", "blob too big ***", e);
                                                fVar2.l();
                                                a.b.a.a.a.c.a.i(cursor2);
                                                if (sQLiteDatabaseG != null) {
                                                    cVar = fVar2.f330a;
                                                    cVar.J();
                                                }
                                                a.b.a.a.a.c.a.g(gZIPOutputStream);
                                                hVar = null;
                                                if (hVar != null) {
                                                }
                                                a.b.a.e.l0.p.c("UploaderEngine", "满足条件的记录为空，即将返回, priority=" + i);
                                                return new b(z, false);
                                            } catch (Exception e14) {
                                                e = e14;
                                                cursor3 = cursorQuery;
                                                fVar = fVarP;
                                            }
                                        } catch (SQLiteBlobTooBigException e15) {
                                            e = e15;
                                            cursor3 = cursorQuery;
                                            fVar2 = fVarP;
                                            cursor2 = cursor3;
                                            gZIPOutputStream = null;
                                            z = true;
                                            c2 = 3;
                                            i4 = f.f327b + (z ? 1 : 0);
                                            f.f327b = i4;
                                            if (i4 <= c2) {
                                                a.b.a.a.a.c.a.O0(a.b.a.e.e.b(), "EventManager", "getEventsDataSync SQLiteBlobTooBigException", e);
                                            }
                                            a.b.a.e.l0.p.h("EventManager", "blob too big ***", e);
                                            fVar2.l();
                                            a.b.a.a.a.c.a.i(cursor2);
                                            if (sQLiteDatabaseG != null) {
                                                cVar = fVar2.f330a;
                                                cVar.J();
                                            }
                                            a.b.a.a.a.c.a.g(gZIPOutputStream);
                                            hVar = null;
                                            if (hVar != null) {
                                            }
                                            a.b.a.e.l0.p.c("UploaderEngine", "满足条件的记录为空，即将返回, priority=" + i);
                                            return new b(z, false);
                                        } catch (Exception e16) {
                                            e = e16;
                                            cursor3 = cursorQuery;
                                            fVar = fVarP;
                                            cursor = cursor3;
                                            gZIPOutputStream = null;
                                            z = true;
                                            z = true;
                                            i3 = f.f327b + 1;
                                            f.f327b = i3;
                                            if (i3 <= 3) {
                                                a.b.a.a.a.c.a.O0(a.b.a.e.e.b(), "EventManager", "getEventsDataSync Exception", e);
                                            }
                                            a.b.a.e.l0.p.h("EventManager", "getEventsDataSync error", e);
                                            a.b.a.a.a.c.a.i(cursor);
                                            if (sQLiteDatabaseG != null) {
                                                cVar = fVar.f330a;
                                                cVar.J();
                                            }
                                            a.b.a.a.a.c.a.g(gZIPOutputStream);
                                            hVar = null;
                                            if (hVar != null) {
                                            }
                                            a.b.a.e.l0.p.c("UploaderEngine", "满足条件的记录为空，即将返回, priority=" + i);
                                            return new b(z, false);
                                        }
                                    } catch (Throwable th16) {
                                        th = th16;
                                        cursor3 = cursorQuery;
                                        fVar = fVarP;
                                    }
                                } catch (SQLiteBlobTooBigException e17) {
                                    e = e17;
                                    i2 = size;
                                } catch (Exception e18) {
                                    e = e18;
                                    i2 = size;
                                }
                            } catch (SQLiteBlobTooBigException e19) {
                                e = e19;
                                i2 = size;
                                fVar2 = fVarP;
                                cursor2 = null;
                            } catch (Exception e20) {
                                e = e20;
                                i2 = size;
                                fVar = fVarP;
                                cursor = null;
                            } catch (Throwable th17) {
                                th = th17;
                                fVar = fVarP;
                                gZIPOutputStream = null;
                            }
                        } catch (SQLiteBlobTooBigException e21) {
                            e = e21;
                            i2 = size;
                            fVar2 = fVarP;
                            z = true;
                            c2 = 3;
                            gZIPOutputStream = null;
                            cursor2 = null;
                            sQLiteDatabaseG = null;
                        } catch (Exception e22) {
                            e = e22;
                            i2 = size;
                            fVar = fVarP;
                            gZIPOutputStream = null;
                            cursor = null;
                            sQLiteDatabaseG = null;
                        } catch (Throwable th18) {
                            th = th18;
                            fVar = fVarP;
                            gZIPOutputStream = null;
                            sQLiteDatabaseG = null;
                        }
                    } catch (Throwable th19) {
                        th = th19;
                    }
                } catch (Throwable th20) {
                    th = th20;
                }
            }
        }
        if (size >= 200) {
            a.b.a.e.l0.j.a("uploadData prio=" + i + " count=" + size, 3000L);
        } else if (size >= 80) {
            a.b.a.e.l0.j.a("uploadData prio=" + i + " count=" + size, 30000L);
        }
        return new b(z, false);
    }
}
