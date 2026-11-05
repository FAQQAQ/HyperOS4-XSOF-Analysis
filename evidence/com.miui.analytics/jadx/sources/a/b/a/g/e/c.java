package a.b.a.g.e;

import a.b.a.e.e0.d;
import a.b.a.e.g0.e;
import a.b.a.e.l0.i;
import a.b.a.e.l0.p;
import a.b.a.e.l0.v;
import a.b.a.g.c.h;
import a.b.a.g.c.m;
import android.text.TextUtils;
import android.util.ArrayMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static ArrayMap<String, Integer> f888a = new ArrayMap<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static ArrayMap<String, Integer> f889b = new ArrayMap<>();

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f890a;

        public a(boolean z, boolean z2) {
            this.f890a = z;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void a(String str, String str2, List<a.b.a.f.e.b> list) {
        ArrayMap<String, Integer> arrayMap;
        Integer num;
        ArrayMap<String, Integer> arrayMap2;
        if ("miui-analytics".equals(str) && a.b.a.g.f.b.f892b.contains(str2)) {
            f888a.clear();
            f889b.clear();
            Iterator<a.b.a.f.e.b> it = list.iterator();
            while (it.hasNext()) {
                if (v.h(it.next().g)) {
                    if (f888a.get(str2) == null) {
                        arrayMap = f888a;
                        arrayMap.put(str2, 1);
                    } else {
                        num = f888a.get(str2);
                        arrayMap2 = f888a;
                        arrayMap2.put(str2, Integer.valueOf(num.intValue() + 1));
                    }
                } else if (f889b.get(str2) == null) {
                    arrayMap = f889b;
                    arrayMap.put(str2, 1);
                } else {
                    num = f889b.get(str2);
                    arrayMap2 = f889b;
                    arrayMap2.put(str2, Integer.valueOf(num.intValue() + 1));
                }
            }
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static List<a.b.a.f.e.b> b(List<a.b.a.f.e.b> list) throws Throwable {
        String[] strArr;
        if (list.size() == 0) {
            p.c("EventMonitorUploaderDispenseForPub", "messages is empty, return null");
            return null;
        }
        HashMap map = new HashMap();
        ArrayList arrayList = new ArrayList();
        int i = e.e;
        e eVar = e.b.f436a;
        String str = eVar.a(false)[1];
        String str2 = eVar.a(false)[0];
        boolean z = TextUtils.isEmpty(str) || TextUtils.isEmpty(str2);
        if (z) {
            synchronized (eVar) {
                try {
                    String[] strArr2 = eVar.f435d;
                    if (strArr2 == null || TextUtils.isEmpty(strArr2[0]) || TextUtils.isEmpty(eVar.f435d[1])) {
                        try {
                            byte[] encoded = a.b.a.e.g0.a.f420a.generateKey().getEncoded();
                            String strC = a.b.a.e.g0.c.c(encoded);
                            String strC2 = a.b.a.e.g0.c.c(a.b.a.a.a.c.a.u(encoded));
                            if (TextUtils.isEmpty(strC) || TextUtils.isEmpty(strC2)) {
                                strArr = null;
                            } else {
                                String[] strArr3 = {strC, strC2};
                                eVar.f435d = strArr3;
                                strArr = strArr3;
                            }
                        } catch (Exception e) {
                            p.h("SecretKeyManager", "getOrGenerateLocalKeySid error: ", e);
                        }
                    } else {
                        strArr = eVar.f435d;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (strArr == null || TextUtils.isEmpty(strArr[0]) || TextUtils.isEmpty(strArr[1])) {
                p.g("EventMonitorUploaderDispenseForPub", "both server key and local key are unavailable");
                return null;
            }
            p.c("EventMonitorUploaderDispenseForPub", "use local key");
            str2 = strArr[0];
            str = strArr[1];
        }
        try {
            for (a.b.a.f.e.b bVar : list) {
                Map<String, String> map2 = bVar.f;
                if (map2 != null) {
                    if (map.get(map2) == null) {
                        map.put(map2, new JSONArray());
                    }
                    String string = bVar.e;
                    if (d.b().e()) {
                        JSONObject jSONObject = new JSONObject(string);
                        i.a(jSONObject);
                        string = jSONObject.toString();
                    }
                    ((JSONArray) map.get(map2)).put(string);
                }
            }
            for (Map.Entry entry : map.entrySet()) {
                Map map3 = (Map) entry.getKey();
                map3.put("pub_gzipencrypt", "true");
                map3.put("pub_sid", str);
                if (z) {
                    map3.put("enc_version", "1");
                }
                JSONArray jSONArray = (JSONArray) entry.getValue();
                if (jSONArray != null && jSONArray.length() != 0) {
                    p.c("EventMonitorUploaderDispenseForPub", "zip before : " + jSONArray.toString().length());
                    byte[] bArrU0 = a.b.a.a.a.c.a.U0(jSONArray.toString());
                    if (bArrU0 != null && bArrU0.length != 0) {
                        p.c("EventMonitorUploaderDispenseForPub", "zip after : " + bArrU0.length);
                        byte[] bArrE = a.b.a.e.g0.a.e(bArrU0, a.b.a.e.g0.c.a(str2));
                        if (bArrE != null && bArrE.length != 0) {
                            arrayList.add(new a.b.a.f.e.b(-1L, null, null, a.b.a.e.g0.c.c(bArrE), map3, -1L));
                        }
                    }
                }
            }
        } catch (Exception e2) {
            p.h("EventMonitorUploaderDispenseForPub", "compressGzipEncryptByAttribute error: ", e2);
            a.b.a.a.a.c.a.y0("up", "s36", e2);
        }
        return arrayList;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static a c(a.b.a.f.c.c cVar, String str, String str2, List<a.b.a.f.e.b> list) {
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                if (cVar != null && !TextUtils.isEmpty(cVar.f683c)) {
                    String strD = d(list);
                    if (TextUtils.isEmpty(strD)) {
                        return new a(false, false);
                    }
                    a.b.a.f.g.c cVarK = a.b.a.e.i0.c.n().k(String.format("%s/v1/projects/%s/topics/%s:publish", "https://pubsub.googleapis.com", str, str2), strD.getBytes("UTF-8"), cVar.f683c);
                    if (cVarK != null) {
                        int i = cVarK.f723a;
                        if (i == 401) {
                            cVar.i = true;
                            a.b.a.f.c.d dVarB = a.b.a.f.c.d.b();
                            Objects.requireNonNull(dVarB);
                            int i2 = a.b.a.f.c.d.f687d + 1;
                            a.b.a.f.c.d.f687d = i2;
                            if (i2 >= 2) {
                                dVarB.a();
                            }
                            dVarB.c();
                            return c(a.b.a.f.c.b.e().b(), str, str2, list);
                        }
                        if (i == 404) {
                            if ("NOT_FOUND".equals(cVarK.f726d) && !TextUtils.isEmpty(cVarK.f725c) && (cVarK.f725c.contains(str) || cVarK.f725c.contains(str2))) {
                                return new a(false, true);
                            }
                        } else if (i == 200) {
                            a.b.a.f.c.d dVarB2 = a.b.a.f.c.d.b();
                            Objects.requireNonNull(dVarB2);
                            a.b.a.f.c.d.f686c = 0;
                            a.b.a.f.c.d.f687d = 0;
                            a.b.a.f.c.d.f685b = 0L;
                            dVarB2.c();
                            return new a(true, false);
                        }
                    }
                    return new a(false, false);
                }
                return new a(false, false);
            }
            return new a(false, false);
        } catch (Exception e) {
            p.h("EventMonitorUploaderDispenseForPub", "Exception while uploading ", e);
            a.b.a.a.a.c.a.y0("up", "s37", e);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static String d(List<a.b.a.f.e.b> list) {
        if (list == null) {
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            for (a.b.a.f.e.b bVar : list) {
                JSONObject jSONObject2 = new JSONObject();
                Map<String, String> map = bVar.f;
                if (map != null) {
                    JSONObject jSONObject3 = new JSONObject();
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        if (!TextUtils.isEmpty(entry.getKey())) {
                            jSONObject3.put(entry.getKey(), entry.getValue());
                        }
                    }
                    jSONObject2.put("attributes", jSONObject3);
                }
                jSONObject2.put("data", bVar.e);
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("messages", jSONArray);
            return jSONObject.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static void e() {
        try {
            if (f888a.size() <= 0 && f889b.size() <= 0) {
                p.c("EventMonitorUploaderDispenseForPub", "recordReportSuccessEventsToDB, this report has no monitor events, skip it");
                return;
            }
            for (String str : a.b.a.g.f.b.f892b) {
                int iIntValue = 0;
                int iIntValue2 = f888a.get(str) == null ? 0 : f888a.get(str).intValue();
                if (f889b.get(str) != null) {
                    iIntValue = f889b.get(str).intValue();
                }
                if (iIntValue2 != 0 || iIntValue != 0) {
                    p.c("EventMonitorUploaderDispenseForPub", "recordReportSuccessEventsToDB, eventName: " + str + ", todayCount: " + iIntValue2 + ", notTodayCount: " + iIntValue);
                    a.b.a.g.c.c.g(true, a.b.a.g.f.a.m(str), iIntValue2 + iIntValue, "report_total_success", iIntValue2, "report_today_success");
                }
            }
        } catch (Exception e) {
            p.h("EventMonitorUploaderDispenseForPub", "recordReportSuccessEventsToDB error: ", e);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void f(String str, String str2, List<a.b.a.f.e.b> list, a.b.a.f.c.c cVar) {
        List<a.b.a.f.e.b> listB;
        int iIntValue;
        if (list != null) {
            try {
                if (list.size() <= 0 || (listB = b(list)) == null || listB.size() <= 0) {
                    return;
                }
                a(str, str2, list);
                if (cVar.i) {
                    p.c("EventMonitorUploaderDispenseForPub", "token is inValid,need get new");
                    cVar = a.b.a.f.c.b.e().i();
                }
                if (c(cVar, str, str2, listB).f890a) {
                    e();
                    h hVarH = h.H();
                    Objects.requireNonNull(hVarH);
                    try {
                        FutureTask futureTask = new FutureTask(new m(hVarH, list));
                        a.b.a.g.c.c.h(futureTask);
                        iIntValue = ((Integer) futureTask.get(10L, TimeUnit.SECONDS)).intValue();
                    } catch (Exception e) {
                        p.h("MonitorDBManager", "deleteMonitorEventForPubsub futureTask error: ", e);
                        iIntValue = 0;
                    }
                    p.c("EventMonitorUploaderDispenseForPub", "uploadGzipEncryptData deleted:" + iIntValue);
                }
            } catch (Throwable th) {
                p.h("EventMonitorUploaderDispenseForPub", "sendGzipEncryptData exception: ", th);
                a.b.a.a.a.c.a.y0("up", "s35", th);
            }
        }
    }
}
