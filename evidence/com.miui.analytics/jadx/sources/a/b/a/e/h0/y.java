package a.b.a.e.h0;

import android.annotation.TargetApi;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Pair;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class y extends a.b.a.e.f {
    public static boolean h = true;
    public static Map<String, Long> i = new HashMap();
    public int g = 0;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f460a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f461b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f462c;

        public a(String str, int i, long j) {
            this.f460a = str;
            this.f461b = i;
            this.f462c = j;
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f463a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f464b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f465c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f466d;

        public b(String str, String str2, int i, long j) {
            this.f463a = str;
            this.f464b = str2;
            this.f465c = i;
            this.f466d = j;
        }
    }

    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SharedPreferences f467a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static SharedPreferences.Editor f468b;

        public static void a() {
            if (f468b != null) {
                return;
            }
            synchronized (c.class) {
                if (f468b == null) {
                    SharedPreferences sharedPreferences = a.b.a.e.e.b().getSharedPreferences("one_track_pref_kk_usage_stats", 0);
                    f467a = sharedPreferences;
                    f468b = sharedPreferences.edit();
                }
            }
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public y(long j, long j2, boolean z) {
        JSONObject jSONObject;
        this.f309a = "001";
        this.f311c = "onetrack_usage";
        this.f310b = a.b.a.e.e.f264d;
        this.f312d = 1;
        try {
            jSONObject = new JSONObject();
            JSONObject jSONObjectA = a();
            JSONObject jSONObjectG = g(j, j2, z);
            if (jSONObjectG != null) {
                jSONObject.put("H", jSONObjectA);
                jSONObject.put("B", jSONObjectG);
            } else {
                jSONObject = null;
            }
        } catch (Exception e) {
            a.b.a.e.l0.p.h("UsageEvent", "create usage event error", e);
        }
        this.e = jSONObject;
        this.f = System.currentTimeMillis();
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static Map<String, Pair<Long, Integer>> l() {
        String string;
        if (a.b.a.e.l0.r.p()) {
            c.a();
            string = c.f467a.getString("today_usage_till_now", null);
        } else {
            string = null;
        }
        a.b.a.e.l0.p.c("UsageEvent", "get from syspref, prev usage=" + string);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            long j = jSONObject.getLong("last_time");
            if (!a.b.a.e.l0.v.g(j)) {
                a.b.a.e.l0.p.c("UsageEvent", "lastTime: " + j + ". not in today");
                return null;
            }
            JSONArray jSONArray = jSONObject.getJSONArray("prev_usg");
            HashMap map = new HashMap();
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                map.put(jSONObject2.getString("pkg"), new Pair(Long.valueOf(jSONObject2.getLong("duration")), Integer.valueOf(jSONObject2.getInt("launch_cnt"))));
            }
            return map;
        } catch (Exception e) {
            a.a.a.a.a.h("get prev usage exception", e, "UsageEvent");
            return null;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final JSONObject d(List<b> list) throws JSONException {
        boolean z = false;
        if (list != null && list.size() != 0) {
            Iterator<b> it = list.iterator();
            boolean z2 = false;
            boolean z3 = false;
            boolean z4 = false;
            while (it.hasNext()) {
                int i2 = it.next().f465c;
                if (i2 == 1) {
                    z2 = true;
                } else if (i2 == 2) {
                    z3 = true;
                } else if (i2 == 3) {
                    z4 = true;
                }
            }
            if (z2 && z3 && z4) {
                z = true;
            }
        }
        String str = null;
        if (!z) {
            return null;
        }
        long j = 0;
        long j2 = 0;
        for (b bVar : list) {
            int i3 = bVar.f465c;
            if (i3 == 2) {
                j = bVar.f466d;
            } else if (i3 == 3) {
                j2 = bVar.f466d;
                str = bVar.f463a;
            }
        }
        long j3 = j - j2;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("event", 10004);
        jSONObject.put("pkg", str);
        jSONObject.put("ts", j2);
        jSONObject.put("duration", j3 > 0 ? j3 : 0L);
        return jSONObject;
    }

    public final JSONObject e(int i2, String str, String str2, long j, long j2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("event", i2);
        jSONObject.put("pkg", str);
        if (str2 != null) {
            jSONObject.put("class", str2);
        }
        jSONObject.put("ts", j);
        if (j2 < 0 || j2 > 86400000) {
            j2 = 0;
        }
        jSONObject.put("duration", j2);
        return jSONObject;
    }

    public final JSONObject f(int i2, String str, String str2, long j, long j2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("event", i2);
        jSONObject.put("pkg", str);
        if (str2 != null) {
            jSONObject.put("class", str2);
        }
        jSONObject.put("ts", j);
        String strA = a.b.a.e.d0.a.e.f256a.a(j, str);
        if (strA == null) {
            strA = "";
        }
        jSONObject.put("vn", strA);
        if (i2 == 2 || i2 == 10002) {
            if (j2 < 0 || j2 > 86400000) {
                j2 = 0;
            }
            jSONObject.put("duration", j2);
        }
        return jSONObject;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public JSONObject g(long j, long j2, boolean z) throws JSONException {
        JSONObject jSONObject;
        JSONArray jSONArrayU;
        Map<String, Pair<Long, Integer>> map;
        Field field;
        Field field2;
        String str;
        JSONObject jSONObject2 = new JSONObject();
        int i2 = Build.VERSION.SDK_INT;
        if (i2 > 19) {
            jSONArrayU = u(j, j2, z);
            jSONObject = jSONObject2;
        } else {
            if (i2 != 19) {
                a.b.a.e.l0.p.c("UsageEvent", "Don't collect usage below Android KK 4.4");
                return jSONObject2;
            }
            StringBuilder sbD = a.a.a.a.a.d("queryToadyUsageStatsForKK enter, begin=", j, ", end=");
            sbD.append(j2);
            a.b.a.e.l0.p.c("UsageEvent", sbD.toString());
            try {
                Object[] objArr = (Object[]) Class.forName("com.android.internal.app.IUsageStats").getDeclaredMethod("getAllPkgUsageStats", new Class[0]).invoke(Class.forName("com.android.internal.app.IUsageStats$Stub").getMethod("asInterface", IBinder.class).invoke(null, Class.forName("android.os.ServiceManager").getDeclaredMethod("getService", String.class).invoke(null, "usagestats")), new Object[0]);
                try {
                    if (objArr == null || objArr.length == 0) {
                        jSONObject = jSONObject2;
                        a.b.a.e.l0.p.c("UsageEvent", "will return, stats=" + objArr);
                        jSONArrayU = null;
                    } else {
                        Class<?> cls = Class.forName("com.android.internal.os.PkgUsageStats");
                        Field declaredField = cls.getDeclaredField("packageName");
                        declaredField.setAccessible(true);
                        Field declaredField2 = cls.getDeclaredField("usageTime");
                        declaredField2.setAccessible(true);
                        Field declaredField3 = cls.getDeclaredField("launchCount");
                        declaredField3.setAccessible(true);
                        Map<String, Pair<Long, Integer>> mapL = l();
                        JSONArray jSONArray = new JSONArray();
                        JSONArray jSONArray2 = new JSONArray();
                        int length = objArr.length;
                        int i3 = 0;
                        int i4 = 0;
                        while (true) {
                            if (i3 >= length) {
                                jSONObject = jSONObject2;
                                break;
                            }
                            int i5 = length;
                            Object obj = objArr[i3];
                            long j3 = declaredField2.getLong(obj);
                            Field field3 = declaredField2;
                            int i6 = declaredField3.getInt(obj);
                            if (i6 <= 0 || j3 <= 0) {
                                jSONObject = jSONObject2;
                                map = mapL;
                                field = declaredField3;
                                field2 = declaredField;
                                str = "launch cnt=" + i6 + ", timeFg=" + j3;
                            } else {
                                String str2 = (String) declaredField.get(obj);
                                field = declaredField3;
                                StringBuilder sb = new StringBuilder();
                                field2 = declaredField;
                                sb.append("get from system, pkg=");
                                sb.append(str2);
                                sb.append(", fgTime=");
                                sb.append(j3);
                                sb.append(", launch=");
                                sb.append(i6);
                                a.b.a.e.l0.p.c("UsageEvent", sb.toString());
                                JSONObject jSONObject3 = new JSONObject();
                                jSONObject3.put("pkg", str2);
                                jSONObject3.put("duration", j3);
                                jSONObject3.put("launch_cnt", i6);
                                jSONArray.put(jSONObject3);
                                JSONObject jSONObject4 = new JSONObject();
                                if (mapL == null || !mapL.containsKey(str2)) {
                                    jSONObject = jSONObject2;
                                    map = mapL;
                                    jSONObject4.put("duration", j3);
                                    jSONObject4.put("launch_cnt", i6);
                                } else {
                                    map = mapL;
                                    Pair<Long, Integer> pair = mapL.get(str2);
                                    jSONObject = jSONObject2;
                                    long jLongValue = ((Long) pair.first).longValue();
                                    int iIntValue = ((Integer) pair.second).intValue();
                                    long j4 = j3 - jLongValue;
                                    if (j4 > 0 || i6 - iIntValue > 0) {
                                        jSONObject4.put("duration", j4);
                                        jSONObject4.put("launch_cnt", i6 - iIntValue);
                                    } else {
                                        str = "app not used since last update time";
                                    }
                                }
                                jSONObject4.put("event", 10006);
                                jSONObject4.put("pkg", str2);
                                jSONArray2.put(jSONObject4);
                                int length2 = i4 + jSONObject4.toString().length();
                                if (length2 >= 286720) {
                                    a.b.a.e.l0.p.c("UsageEvent", "usage reached max len: " + jSONObject3);
                                    break;
                                }
                                i4 = length2;
                                i3++;
                                length = i5;
                                declaredField2 = field3;
                                declaredField3 = field;
                                declaredField = field2;
                                jSONObject2 = jSONObject;
                                mapL = map;
                            }
                            a.b.a.e.l0.p.c("UsageEvent", str);
                            i3++;
                            length = i5;
                            declaredField2 = field3;
                            declaredField3 = field;
                            declaredField = field2;
                            jSONObject2 = jSONObject;
                            mapL = map;
                        }
                        if (a.b.a.e.l0.p.f602a) {
                            a.b.a.e.l0.p.c("UsageEvent", "put to syspref, usageTotal=" + jSONArray.toString());
                        }
                        JSONObject jSONObject5 = new JSONObject();
                        jSONObject5.put("last_time", System.currentTimeMillis());
                        jSONObject5.put("prev_usg", jSONArray);
                        String string = jSONObject5.toString();
                        if (a.b.a.e.l0.r.p()) {
                            c.a();
                            c.f468b.putString("today_usage_till_now", string).apply();
                        }
                        jSONArrayU = jSONArray2;
                    }
                } catch (Exception e) {
                    e = e;
                    a.b.a.e.l0.p.h("UsageEvent", "queryToadyUsageStatsForKK exception:", e);
                    a.b.a.e.l0.p.c("UsageEvent", "queryToadyUsageStatsForKK will return null");
                }
            } catch (Exception e2) {
                e = e2;
                jSONObject = jSONObject2;
            }
        }
        if (jSONArrayU == null || jSONArrayU.length() == 0) {
            a.b.a.e.l0.p.c("UsageEvent", "No new usage data to report");
            return null;
        }
        JSONObject jSONObject6 = jSONObject;
        jSONObject6.put("start", j);
        jSONObject6.put("end", j2);
        jSONObject6.put("radio", a.b.a.e.l0.c.z());
        jSONObject6.put("radio2", a.b.a.e.l0.c.A(1));
        jSONObject6.put("seq", jSONArrayU);
        if (a.b.a.e.l0.r.q()) {
            jSONObject6.put("custom_version", a.b.a.e.l0.c.e());
        }
        if (a.b.a.e.l0.p.f602a) {
            String string2 = jSONObject6.toString();
            byte[] bytes = string2.getBytes();
            StringBuilder sbC = a.a.a.a.a.c("Usage Sequence size(K): ");
            sbC.append(bytes.length / 1024);
            sbC.append(", content=");
            sbC.append(string2);
            a.b.a.e.l0.p.c("UsageEvent", sbC.toString());
        }
        return jSONObject6;
    }

    public final long h(String str) {
        if (i.containsKey(str)) {
            return i.get(str).longValue();
        }
        return 0L;
    }

    @TargetApi(21)
    public final long i(List<Pair<String, Long>> list, UsageEvents.Event event, long j) {
        if (list.isEmpty() || j == 0) {
            return 0L;
        }
        Pair<String, Long> pair = list.get(list.size() - 1);
        StringBuilder sbD = a.a.a.a.a.d("getFirstActivityDuration->lastChangeStateTime: ", j, ", item: ");
        sbD.append(pair.toString());
        a.b.a.e.l0.p.c("UsageEvent", sbD.toString());
        if (((Long) pair.second).longValue() >= j || j >= event.getTimeStamp()) {
            return 0L;
        }
        if (TextUtils.equals((CharSequence) pair.first, event.getPackageName() + "/" + event.getClassName())) {
            return event.getTimeStamp() - j;
        }
        return 0L;
    }

    public final Pair<Integer, Long> j(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            String[] strArrSplit = str.split(",");
            if (strArrSplit.length != 2) {
                return null;
            }
            return Pair.create(Integer.valueOf(Integer.parseInt(strArrSplit[0])), Long.valueOf(Long.parseLong(strArrSplit[1])));
        } catch (Exception e) {
            a.b.a.e.l0.p.h("UsageEvent", "getLastDurationPair error, lastDuration: " + str + ", e:", e);
            return null;
        }
    }

    @TargetApi(21)
    public final long k(UsageEvents.Event event, List<Pair<String, Long>> list, long j) {
        StringBuilder sbC = a.a.a.a.a.c("getLatestActivityDuration->notAssembledEventList: ");
        sbC.append(list.toString());
        a.b.a.e.l0.p.c("UsageEvent", sbC.toString());
        if (list.isEmpty()) {
            return 0L;
        }
        Pair<String, Long> pair = list.get(list.size() - 1);
        StringBuilder sbD = a.a.a.a.a.d("getLatestActivityDuration->lastStateTime: ", j, ", latest: ");
        sbD.append(pair.toString());
        a.b.a.e.l0.p.c("UsageEvent", sbD.toString());
        return j > ((Long) pair.second).longValue() ? event.getTimeStamp() - j : event.getTimeStamp() - ((Long) pair.second).longValue();
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    @TargetApi(21)
    public final JSONObject m(UsageEvents.Event event, List<Pair<String, Long>> list, JSONArray jSONArray, List<UsageEvents.Event> list2) throws JSONException {
        String str = event.getPackageName() + "/" + event.getClassName();
        Iterator<Pair<String, Long>> it = list.iterator();
        long jLongValue = 0;
        while (it.hasNext()) {
            Pair<String, Long> next = it.next();
            if (TextUtils.equals(str, (CharSequence) next.first)) {
                jLongValue = ((Long) next.second).longValue();
                it.remove();
            }
        }
        JSONObject jSONObjectF = f(2, event.getPackageName(), event.getClassName(), event.getTimeStamp(), jLongValue != 0 ? event.getTimeStamp() - jLongValue : 0L);
        jSONArray.put(jSONObjectF);
        if (TextUtils.equals("com.miui.hybrid", event.getPackageName())) {
            list2.add(event);
        }
        return jSONObjectF;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    @TargetApi(21)
    public final JSONObject n(UsageEvents.Event event, List<Pair<String, Long>> list, JSONArray jSONArray, List<UsageEvents.Event> list2) throws JSONException {
        String str = event.getPackageName() + "/" + event.getClassName();
        Iterator<Pair<String, Long>> it = list.iterator();
        while (it.hasNext()) {
            if (TextUtils.equals(str, (CharSequence) it.next().first)) {
                it.remove();
            }
        }
        JSONObject jSONObjectF = f(1, event.getPackageName(), event.getClassName(), event.getTimeStamp(), 0L);
        list.add(new Pair<>(str, Long.valueOf(event.getTimeStamp())));
        jSONArray.put(jSONObjectF);
        if (TextUtils.equals("com.miui.hybrid", event.getPackageName())) {
            list2.add(event);
        }
        return jSONObjectF;
    }

    @TargetApi(21)
    public final JSONObject o(UsageEvents.Event event, a aVar) throws JSONException {
        if (aVar.f461b == event.getEventType()) {
            return null;
        }
        if (event.getEventType() == 10001) {
            return f(10001, event.getPackageName(), null, event.getTimeStamp(), 0L);
        }
        if (event.getEventType() != 10002) {
            return null;
        }
        return f(10002, event.getPackageName(), null, event.getTimeStamp(), TextUtils.equals(event.getPackageName(), aVar.f460a) ? event.getTimeStamp() - aVar.f462c : 0L);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX WARN: Code duplicated, block: B:112:0x0226  */
    /* JADX WARN: Code duplicated, block: B:118:0x024b  */
    /* JADX WARN: Code duplicated, block: B:119:0x0253 A[PHI: r5
      0x0253: PHI (r5v34 long) = (r5v27 long), (r5v44 long) binds: [B:117:0x0249, B:103:0x0214] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:120:0x025b  */
    /* JADX WARN: Code duplicated, block: B:136:0x02d5  */
    /* JADX WARN: Code duplicated, block: B:139:0x02f9  */
    /* JADX WARN: Code duplicated, block: B:149:0x02e4 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:152:0x02fb A[SYNTHETIC] */
    @TargetApi(21)
    public final JSONArray p(UsageEvents usageEvents, List<Pair<String, Long>> list, List<UsageEvents.Event> list2, a aVar, boolean z) throws JSONException {
        long jG;
        boolean z2;
        int i2;
        String str;
        long timeStamp;
        UsageEvents.Event event;
        int length;
        int eventType;
        long jLongValue;
        long timeStamp2;
        long j;
        long j2 = 0;
        String str2 = "pad_last_rotate_time";
        String str3 = "pad_last_duration";
        if (z) {
            Pair<Integer, Long> pairJ = j(a.b.a.e.l0.u.h("pad_last_duration", ""));
            int iIntValue = pairJ == null ? 38 : ((Integer) pairJ.first).intValue();
            long jLongValue2 = pairJ == null ? 0L : ((Long) pairJ.second).longValue();
            i2 = iIntValue;
            jG = a.b.a.e.l0.u.g("pad_last_rotate_time", 0L);
            j2 = jLongValue2;
            z2 = true;
        } else {
            list.clear();
            jG = 0;
            z2 = false;
            i2 = 0;
        }
        a.b.a.e.l0.p.c("UsageEvent", "handlePad->rotation: " + i2 + ", duration: " + j2 + ", lastRotateTime: " + jG + ", isInit: " + z2);
        JSONArray jSONArray = new JSONArray();
        int i3 = i2;
        int i4 = 0;
        boolean z3 = z2;
        long timeStamp3 = jG;
        long j3 = j2;
        while (usageEvents.hasNextEvent()) {
            UsageEvents.Event event2 = new UsageEvents.Event();
            str = str2;
            usageEvents.getNextEvent(event2);
            jSONObjectN = null;
            jSONObjectN = null;
            jSONObjectN = null;
            jSONObjectN = null;
            JSONObject jSONObjectN = null;
            int i5 = i3;
            if (event2.getEventType() == 10001) {
                timeStamp = 0;
                event = event2;
            } else if (event2.getEventType() == 10002) {
                event = event2;
                timeStamp = 0;
            } else {
                if (event2.getEventType() == 1 || event2.getEventType() == 2 || event2.getEventType() == 38 || event2.getEventType() == 39 || event2.getEventType() == 40 || event2.getEventType() == 41) {
                    t(event2);
                    if (z3) {
                        z3 = z3;
                        eventType = i5;
                    } else {
                        if (event2.getEventType() == 1 || event2.getEventType() == 2) {
                            try {
                                z3 = z3;
                                try {
                                    PersistableBundle persistableBundle = (PersistableBundle) event2.getClass().getMethod("getExtrasEx", new Class[0]).invoke(event2, new Object[0]);
                                    eventType = persistableBundle != null ? persistableBundle.getInt("deviceRotation") : 0;
                                    a.b.a.e.l0.p.c("UsageEvent", "initRotation, deviceRotation: " + eventType);
                                } catch (Throwable th) {
                                    th = th;
                                    a.b.a.e.l0.p.h("UsageEvent", "initRotation error, e: ", th);
                                    eventType = 0;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                z3 = z3;
                            }
                        } else if (event2.getEventType() == 38 || event2.getEventType() == 39 || event2.getEventType() == 40 || event2.getEventType() == 41) {
                            eventType = event2.getEventType();
                            z3 = z3;
                        } else {
                            z3 = z3;
                            eventType = 0;
                        }
                        if (eventType == 0) {
                            jSONArray = jSONArray;
                            str3 = str3;
                            i3 = i5;
                            z3 = z3;
                            jSONArray = jSONArray;
                            str2 = str;
                            str3 = str3;
                        } else {
                            z3 = true;
                        }
                    }
                    if (event2.getEventType() == 1) {
                        jSONObjectN = n(event2, list, jSONArray, list2);
                        i3 = eventType;
                        z3 = z3;
                    } else {
                        if (event2.getEventType() == 2) {
                            long jI = timeStamp3 == 0 ? 0L : i(list, event2, timeStamp3);
                            StringBuilder sb = new StringBuilder();
                            str3 = str3;
                            sb.append("firstActivityDuration: ");
                            sb.append(jI);
                            a.b.a.e.l0.p.c("UsageEvent", sb.toString());
                            JSONObject jSONObjectM = m(event2, list, jSONArray, list2);
                            if (jI == 0) {
                                jI = jSONObjectM.optLong("duration");
                            }
                            j3 += jI;
                            jSONObjectN = jSONObjectM;
                        } else {
                            str3 = str3;
                            if (event2.getEventType() == 38 || event2.getEventType() == 40) {
                                if (eventType == 39 || eventType == 41) {
                                    if (list.isEmpty()) {
                                        timeStamp2 = j3;
                                    } else {
                                        jLongValue = ((Long) list.get(list.size() - 1).second).longValue();
                                        if (timeStamp3 > jLongValue) {
                                            timeStamp2 = (event2.getTimeStamp() - timeStamp3) + j3;
                                        } else {
                                            timeStamp2 = (event2.getTimeStamp() - jLongValue) + j3;
                                        }
                                    }
                                    jSONArray = jSONArray;
                                    JSONObject jSONObjectE = e(event2.getEventType(), event2.getPackageName(), event2.getClassName(), event2.getTimeStamp(), timeStamp2);
                                    jSONObjectE.put("last_event", eventType);
                                    jSONArray.put(jSONObjectE);
                                    eventType = event2.getEventType();
                                    timeStamp3 = event2.getTimeStamp();
                                    jSONObjectN = jSONObjectE;
                                    j = 0;
                                    i3 = eventType;
                                    j3 = j;
                                    z3 = z3;
                                    if (jSONObjectN == null) {
                                        continue;
                                    } else {
                                        length = jSONObjectN.toString().length() + i4;
                                        if (length >= 286720) {
                                            a.b.a.e.l0.p.c("UsageEvent", "usage reached max len: " + length);
                                            a.b.a.e.l0.u.a();
                                            a.b.a.e.l0.u.m(str3, i3 + "," + j3);
                                            a.b.a.e.l0.u.a();
                                            a.b.a.e.l0.u.l(str, timeStamp3);
                                            return jSONArray;
                                        }
                                        i4 = length;
                                    }
                                    jSONArray = jSONArray;
                                    str2 = str;
                                    str3 = str3;
                                } else if (eventType != event2.getEventType()) {
                                    eventType = event2.getEventType();
                                }
                            } else if (event2.getEventType() == 39 || event2.getEventType() == 41) {
                                if (eventType == 38 || eventType == 40) {
                                    if (list.isEmpty()) {
                                        timeStamp2 = j3;
                                    } else {
                                        jLongValue = ((Long) list.get(list.size() - 1).second).longValue();
                                        if (timeStamp3 > jLongValue) {
                                            timeStamp2 = (event2.getTimeStamp() - timeStamp3) + j3;
                                        } else {
                                            timeStamp2 = (event2.getTimeStamp() - jLongValue) + j3;
                                        }
                                    }
                                    jSONArray = jSONArray;
                                    JSONObject jSONObjectE2 = e(event2.getEventType(), event2.getPackageName(), event2.getClassName(), event2.getTimeStamp(), timeStamp2);
                                    jSONObjectE2.put("last_event", eventType);
                                    jSONArray.put(jSONObjectE2);
                                    eventType = event2.getEventType();
                                    timeStamp3 = event2.getTimeStamp();
                                    jSONObjectN = jSONObjectE2;
                                    j = 0;
                                    i3 = eventType;
                                    j3 = j;
                                    z3 = z3;
                                    if (jSONObjectN == null) {
                                        continue;
                                    } else {
                                        length = jSONObjectN.toString().length() + i4;
                                        if (length >= 286720) {
                                            a.b.a.e.l0.p.c("UsageEvent", "usage reached max len: " + length);
                                            a.b.a.e.l0.u.a();
                                            a.b.a.e.l0.u.m(str3, i3 + "," + j3);
                                            a.b.a.e.l0.u.a();
                                            a.b.a.e.l0.u.l(str, timeStamp3);
                                            return jSONArray;
                                        }
                                        i4 = length;
                                    }
                                    jSONArray = jSONArray;
                                    str2 = str;
                                    str3 = str3;
                                } else if (eventType != event2.getEventType()) {
                                    eventType = event2.getEventType();
                                }
                            }
                        }
                        j = j3;
                        jSONArray = jSONArray;
                        i3 = eventType;
                        j3 = j;
                        z3 = z3;
                        if (jSONObjectN == null) {
                            continue;
                        } else {
                            length = jSONObjectN.toString().length() + i4;
                            if (length >= 286720) {
                                a.b.a.e.l0.p.c("UsageEvent", "usage reached max len: " + length);
                                a.b.a.e.l0.u.a();
                                a.b.a.e.l0.u.m(str3, i3 + "," + j3);
                                a.b.a.e.l0.u.a();
                                a.b.a.e.l0.u.l(str, timeStamp3);
                                return jSONArray;
                            }
                            i4 = length;
                        }
                        jSONArray = jSONArray;
                        str2 = str;
                        str3 = str3;
                    }
                } else {
                    i3 = i5;
                }
                jSONArray = jSONArray;
                str3 = str3;
                if (jSONObjectN == null) {
                    continue;
                } else {
                    length = jSONObjectN.toString().length() + i4;
                    if (length >= 286720) {
                        a.b.a.e.l0.p.c("UsageEvent", "usage reached max len: " + length);
                        a.b.a.e.l0.u.a();
                        a.b.a.e.l0.u.m(str3, i3 + "," + j3);
                        a.b.a.e.l0.u.a();
                        a.b.a.e.l0.u.l(str, timeStamp3);
                        return jSONArray;
                    }
                    i4 = length;
                }
                jSONArray = jSONArray;
                str2 = str;
                str3 = str3;
            }
            t(event);
            JSONObject jSONObjectO = o(event, aVar);
            if (jSONObjectO == null) {
                i3 = i5;
                z3 = z3;
            } else {
                jSONArray.put(jSONObjectO);
                aVar.f461b = event.getEventType();
                if (event.getEventType() == 10001) {
                    timeStamp = event.getTimeStamp();
                }
                aVar.f462c = timeStamp;
                aVar.f460a = event.getEventType() == 10001 ? event.getPackageName() : null;
                i3 = i5;
                jSONObjectN = jSONObjectO;
                z3 = z3;
                if (jSONObjectN == null) {
                    continue;
                } else {
                    length = jSONObjectN.toString().length() + i4;
                    if (length >= 286720) {
                        a.b.a.e.l0.p.c("UsageEvent", "usage reached max len: " + length);
                        a.b.a.e.l0.u.a();
                        a.b.a.e.l0.u.m(str3, i3 + "," + j3);
                        a.b.a.e.l0.u.a();
                        a.b.a.e.l0.u.l(str, timeStamp3);
                        return jSONArray;
                    }
                    i4 = length;
                }
            }
            jSONArray = jSONArray;
            str2 = str;
            str3 = str3;
        }
        jSONArray = jSONArray;
        str = str2;
        str3 = str3;
        a.b.a.e.l0.u.a();
        a.b.a.e.l0.u.m(str3, i3 + "," + j3);
        a.b.a.e.l0.u.a();
        a.b.a.e.l0.u.l(str, timeStamp3);
        return jSONArray;
    }

    @TargetApi(21)
    public final Pair<Boolean, Integer> q(UsageEvents.Event event) {
        try {
            if (event.getEventType() == 1 || event.getEventType() == 2) {
                PersistableBundle persistableBundle = (PersistableBundle) event.getClass().getMethod("getExtrasEx", new Class[0]).invoke(event, new Object[0]);
                int i2 = persistableBundle != null ? persistableBundle.getInt("deviceState") : 0;
                int i3 = persistableBundle != null ? persistableBundle.getInt("deviceRotation") : 0;
                a.b.a.e.l0.p.c("UsageEvent", "initFoldAndRotation, deviceState: " + i2 + ", deviceRotation: " + i3);
                if (i2 == 0 || i3 == 0) {
                    return null;
                }
                return new Pair<>(Boolean.valueOf(i2 == 42), Integer.valueOf(i3));
            }
            if (event.getEventType() != 38 && event.getEventType() != 39 && event.getEventType() != 40 && event.getEventType() != 41) {
                if (event.getEventType() == 42 || event.getEventType() == 43) {
                    return new Pair<>(Boolean.valueOf(event.getEventType() == 42), 0);
                }
                return null;
            }
            PersistableBundle persistableBundle2 = (PersistableBundle) event.getClass().getMethod("getExtrasEx", new Class[0]).invoke(event, new Object[0]);
            int i4 = persistableBundle2 != null ? persistableBundle2.getInt("deviceState") : 0;
            a.b.a.e.l0.p.c("UsageEvent", "initFoldAndRotation, deviceState: " + i4);
            if (i4 != 0) {
                return new Pair<>(Boolean.valueOf(i4 == 42), Integer.valueOf(event.getEventType()));
            }
            return null;
        } catch (Throwable th) {
            a.b.a.e.l0.p.h("UsageEvent", "initFoldAndRotation error, e: ", th);
            return null;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public boolean r() {
        String str;
        if (c()) {
            JSONArray jSONArrayOptJSONArray = this.e.optJSONObject("B").optJSONArray("seq");
            if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                return false;
            }
            a.b.a.e.l0.p.c("UsageEvent", "no usage sequence, won't track");
            str = "r5";
        } else {
            a.b.a.e.l0.p.c("UsageEvent", "mData or mAppId or mPackage is invalid, won't track");
            str = "r4";
        }
        a.b.a.g.c.c.f(false, "et2", 1, str);
        return true;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final List<List<b>> s(List<b> list) {
        String string;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        boolean z = false;
        boolean z2 = false;
        for (int i2 = 0; i2 < list.size(); i2++) {
            b bVar = list.get(i2);
            if (bVar.f465c == 1) {
                z = true;
            }
            if (z) {
                arrayList2.add(bVar);
            }
            if (bVar.f465c == 2) {
                z2 = true;
            }
            if (z2) {
                arrayList.add(arrayList2);
                arrayList2 = new ArrayList();
                z = false;
                z2 = false;
            }
        }
        a.b.a.e.e.b();
        if (arrayList2.size() <= 0 || arrayList2.size() > 2) {
            string = "";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i3 = 0; i3 < arrayList2.size(); i3++) {
                b bVar2 = (b) arrayList2.get(i3);
                sb.append(bVar2.f463a + "," + bVar2.f464b + "," + bVar2.f466d + "," + bVar2.f465c);
                if (i3 != arrayList2.size() - 1) {
                    sb.append("|");
                }
            }
            string = sb.toString();
        }
        a.b.a.e.l0.u.m("last_unpaired_hybrid_event", string);
        a.b.a.e.l0.p.c("UsageEvent", "saved not assembled hybrid event: " + string);
        return arrayList;
    }

    @TargetApi(21)
    public final void t(UsageEvents.Event event) {
        StringBuilder sbC = a.a.a.a.a.c("type: ");
        sbC.append(event.getEventType());
        sbC.append(", pkg: ");
        sbC.append(event.getPackageName());
        sbC.append(", class: ");
        sbC.append(event.getClassName());
        sbC.append(", time: ");
        sbC.append(event.getTimeStamp());
        a.b.a.e.l0.p.c("UsageEvent", sbC.toString());
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX WARN: Code duplicated, block: B:11:0x0057  */
    /* JADX WARN: Code duplicated, block: B:133:0x045b  */
    /* JADX WARN: Code duplicated, block: B:134:0x0462  */
    /* JADX WARN: Code duplicated, block: B:175:0x06a3  */
    /* JADX WARN: Code duplicated, block: B:178:0x06cb  */
    /* JADX WARN: Code duplicated, block: B:300:0x0aec A[PHI: r7 r11 r12 r18 r30 r35 r36 r37 r40 r50
      0x0aec: PHI (r7v22 java.lang.String) = (r7v14 java.lang.String), (r7v27 java.lang.String) binds: [B:299:0x0aea, B:284:0x0a99] A[DONT_GENERATE, DONT_INLINE]
      0x0aec: PHI (r11v26 java.util.ArrayList) = (r11v21 java.util.ArrayList), (r11v30 java.util.ArrayList) binds: [B:299:0x0aea, B:284:0x0a99] A[DONT_GENERATE, DONT_INLINE]
      0x0aec: PHI (r12v23 java.lang.String) = (r12v18 java.lang.String), (r12v27 java.lang.String) binds: [B:299:0x0aea, B:284:0x0a99] A[DONT_GENERATE, DONT_INLINE]
      0x0aec: PHI (r18v19 long) = (r18v14 long), (r18v23 long) binds: [B:299:0x0aea, B:284:0x0a99] A[DONT_GENERATE, DONT_INLINE]
      0x0aec: PHI (r30v21 java.util.ArrayList) = (r30v16 java.util.ArrayList), (r30v25 java.util.ArrayList) binds: [B:299:0x0aea, B:284:0x0a99] A[DONT_GENERATE, DONT_INLINE]
      0x0aec: PHI (r35v15 android.app.usage.UsageEvents) = (r35v10 android.app.usage.UsageEvents), (r35v19 android.app.usage.UsageEvents) binds: [B:299:0x0aea, B:284:0x0a99] A[DONT_GENERATE, DONT_INLINE]
      0x0aec: PHI (r36v12 java.lang.String) = (r36v7 java.lang.String), (r36v16 java.lang.String) binds: [B:299:0x0aea, B:284:0x0a99] A[DONT_GENERATE, DONT_INLINE]
      0x0aec: PHI (r37v8 char) = (r37v4 char), (r37v12 char) binds: [B:299:0x0aea, B:284:0x0a99] A[DONT_GENERATE, DONT_INLINE]
      0x0aec: PHI (r40v12 long) = (r40v7 long), (r40v16 long) binds: [B:299:0x0aea, B:284:0x0a99] A[DONT_GENERATE, DONT_INLINE]
      0x0aec: PHI (r50v12 java.lang.String) = (r50v7 java.lang.String), (r50v16 java.lang.String) binds: [B:299:0x0aea, B:284:0x0a99] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:308:0x0b1b A[PHI: r7 r11 r12 r13 r18 r30 r35 r36 r37 r40 r50
      0x0b1b: PHI (r7v20 java.lang.String) = (r7v14 java.lang.String), (r7v27 java.lang.String) binds: [B:307:0x0b19, B:289:0x0ab8] A[DONT_GENERATE, DONT_INLINE]
      0x0b1b: PHI (r11v24 java.util.ArrayList) = (r11v21 java.util.ArrayList), (r11v30 java.util.ArrayList) binds: [B:307:0x0b19, B:289:0x0ab8] A[DONT_GENERATE, DONT_INLINE]
      0x0b1b: PHI (r12v21 java.lang.String) = (r12v18 java.lang.String), (r12v27 java.lang.String) binds: [B:307:0x0b19, B:289:0x0ab8] A[DONT_GENERATE, DONT_INLINE]
      0x0b1b: PHI (r13v7 int) = (r13v4 int), (r13v10 int) binds: [B:307:0x0b19, B:289:0x0ab8] A[DONT_GENERATE, DONT_INLINE]
      0x0b1b: PHI (r18v17 long) = (r18v14 long), (r18v23 long) binds: [B:307:0x0b19, B:289:0x0ab8] A[DONT_GENERATE, DONT_INLINE]
      0x0b1b: PHI (r30v19 java.util.ArrayList) = (r30v16 java.util.ArrayList), (r30v25 java.util.ArrayList) binds: [B:307:0x0b19, B:289:0x0ab8] A[DONT_GENERATE, DONT_INLINE]
      0x0b1b: PHI (r35v13 android.app.usage.UsageEvents) = (r35v10 android.app.usage.UsageEvents), (r35v19 android.app.usage.UsageEvents) binds: [B:307:0x0b19, B:289:0x0ab8] A[DONT_GENERATE, DONT_INLINE]
      0x0b1b: PHI (r36v10 java.lang.String) = (r36v7 java.lang.String), (r36v16 java.lang.String) binds: [B:307:0x0b19, B:289:0x0ab8] A[DONT_GENERATE, DONT_INLINE]
      0x0b1b: PHI (r37v6 char) = (r37v4 char), (r37v12 char) binds: [B:307:0x0b19, B:289:0x0ab8] A[DONT_GENERATE, DONT_INLINE]
      0x0b1b: PHI (r40v10 long) = (r40v7 long), (r40v16 long) binds: [B:307:0x0b19, B:289:0x0ab8] A[DONT_GENERATE, DONT_INLINE]
      0x0b1b: PHI (r50v10 java.lang.String) = (r50v7 java.lang.String), (r50v16 java.lang.String) binds: [B:307:0x0b19, B:289:0x0ab8] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:309:0x0b24 A[PHI: r1 r7 r11 r12 r13 r18 r30 r35 r36 r37 r40 r50
      0x0b24: PHI (r1v57 long) = (r1v49 long), (r1v71 long) binds: [B:307:0x0b19, B:289:0x0ab8] A[DONT_GENERATE, DONT_INLINE]
      0x0b24: PHI (r7v21 java.lang.String) = (r7v14 java.lang.String), (r7v27 java.lang.String) binds: [B:307:0x0b19, B:289:0x0ab8] A[DONT_GENERATE, DONT_INLINE]
      0x0b24: PHI (r11v25 java.util.ArrayList) = (r11v21 java.util.ArrayList), (r11v30 java.util.ArrayList) binds: [B:307:0x0b19, B:289:0x0ab8] A[DONT_GENERATE, DONT_INLINE]
      0x0b24: PHI (r12v22 java.lang.String) = (r12v18 java.lang.String), (r12v27 java.lang.String) binds: [B:307:0x0b19, B:289:0x0ab8] A[DONT_GENERATE, DONT_INLINE]
      0x0b24: PHI (r13v8 int) = (r13v4 int), (r13v10 int) binds: [B:307:0x0b19, B:289:0x0ab8] A[DONT_GENERATE, DONT_INLINE]
      0x0b24: PHI (r18v18 long) = (r18v14 long), (r18v23 long) binds: [B:307:0x0b19, B:289:0x0ab8] A[DONT_GENERATE, DONT_INLINE]
      0x0b24: PHI (r30v20 java.util.ArrayList) = (r30v16 java.util.ArrayList), (r30v25 java.util.ArrayList) binds: [B:307:0x0b19, B:289:0x0ab8] A[DONT_GENERATE, DONT_INLINE]
      0x0b24: PHI (r35v14 android.app.usage.UsageEvents) = (r35v10 android.app.usage.UsageEvents), (r35v19 android.app.usage.UsageEvents) binds: [B:307:0x0b19, B:289:0x0ab8] A[DONT_GENERATE, DONT_INLINE]
      0x0b24: PHI (r36v11 java.lang.String) = (r36v7 java.lang.String), (r36v16 java.lang.String) binds: [B:307:0x0b19, B:289:0x0ab8] A[DONT_GENERATE, DONT_INLINE]
      0x0b24: PHI (r37v7 char) = (r37v4 char), (r37v12 char) binds: [B:307:0x0b19, B:289:0x0ab8] A[DONT_GENERATE, DONT_INLINE]
      0x0b24: PHI (r40v11 long) = (r40v7 long), (r40v16 long) binds: [B:307:0x0b19, B:289:0x0ab8] A[DONT_GENERATE, DONT_INLINE]
      0x0b24: PHI (r50v11 java.lang.String) = (r50v7 java.lang.String), (r50v16 java.lang.String) binds: [B:307:0x0b19, B:289:0x0ab8] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:324:0x0bb9  */
    /* JADX WARN: Code duplicated, block: B:327:0x0be7  */
    /* JADX WARN: Code duplicated, block: B:328:0x0bec  */
    /* JADX WARN: Code duplicated, block: B:368:0x0d40  */
    /* JADX WARN: Code duplicated, block: B:371:0x0d66  */
    /* JADX WARN: Code duplicated, block: B:372:0x0d6a  */
    /* JADX WARN: Code duplicated, block: B:378:0x0d8b  */
    /* JADX WARN: Code duplicated, block: B:380:0x0d92  */
    /* JADX WARN: Code duplicated, block: B:381:0x0d95  */
    /* JADX WARN: Code duplicated, block: B:384:0x0d9d  */
    /* JADX WARN: Code duplicated, block: B:386:0x0dc2  */
    /* JADX WARN: Code duplicated, block: B:389:0x0dcf  */
    /* JADX WARN: Code duplicated, block: B:38:0x00df  */
    /* JADX WARN: Code duplicated, block: B:393:0x0dd8  */
    /* JADX WARN: Code duplicated, block: B:396:0x0df7  */
    /* JADX WARN: Code duplicated, block: B:397:0x0e0e  */
    /* JADX WARN: Code duplicated, block: B:400:0x0e5b  */
    /* JADX WARN: Code duplicated, block: B:403:0x0e65 A[LOOP:3: B:401:0x0e5f->B:403:0x0e65, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:407:0x0ead A[LOOP:4: B:405:0x0ea7->B:407:0x0ead, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:411:0x0ee4  */
    /* JADX WARN: Code duplicated, block: B:414:0x0eef  */
    /* JADX WARN: Code duplicated, block: B:416:0x0ef3  */
    /* JADX WARN: Code duplicated, block: B:425:0x0f39 A[Catch: JSONException -> 0x0fb8, TryCatch #1 {JSONException -> 0x0fb8, blocks: (B:422:0x0f29, B:423:0x0f33, B:425:0x0f39, B:427:0x0f45, B:428:0x0f48, B:430:0x0f4c, B:431:0x0f55, B:433:0x0f5b, B:434:0x0f99, B:438:0x0fac, B:437:0x0fa8), top: B:447:0x0f29 }] */
    /* JADX WARN: Code duplicated, block: B:427:0x0f45 A[Catch: JSONException -> 0x0fb8, TryCatch #1 {JSONException -> 0x0fb8, blocks: (B:422:0x0f29, B:423:0x0f33, B:425:0x0f39, B:427:0x0f45, B:428:0x0f48, B:430:0x0f4c, B:431:0x0f55, B:433:0x0f5b, B:434:0x0f99, B:438:0x0fac, B:437:0x0fa8), top: B:447:0x0f29 }] */
    /* JADX WARN: Code duplicated, block: B:433:0x0f5b A[Catch: JSONException -> 0x0fb8, LOOP:7: B:431:0x0f55->B:433:0x0f5b, LOOP_END, TryCatch #1 {JSONException -> 0x0fb8, blocks: (B:422:0x0f29, B:423:0x0f33, B:425:0x0f39, B:427:0x0f45, B:428:0x0f48, B:430:0x0f4c, B:431:0x0f55, B:433:0x0f5b, B:434:0x0f99, B:438:0x0fac, B:437:0x0fa8), top: B:447:0x0f29 }] */
    /* JADX WARN: Code duplicated, block: B:436:0x0fa5  */
    /* JADX WARN: Code duplicated, block: B:437:0x0fa8 A[Catch: JSONException -> 0x0fb8, TryCatch #1 {JSONException -> 0x0fb8, blocks: (B:422:0x0f29, B:423:0x0f33, B:425:0x0f39, B:427:0x0f45, B:428:0x0f48, B:430:0x0f4c, B:431:0x0f55, B:433:0x0f5b, B:434:0x0f99, B:438:0x0fac, B:437:0x0fa8), top: B:447:0x0f29 }] */
    /* JADX WARN: Code duplicated, block: B:445:0x0efe A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:459:0x06b2 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:473:0x0dc7 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:478:0x0f1a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:480:0x0f4c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:482:0x0f33 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:485:0x0bc8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:491:0x0d4e A[SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:384:0x0d9d, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:393:0x0dd8, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:396:0x0df7, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:414:0x0eef, please report this as an issue */
    @TargetApi(21)
    public final JSONArray u(long j, long j2, boolean z) throws JSONException {
        Pair pairCreate;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        a aVar;
        JSONArray jSONArray;
        ArrayList<UsageEvents.Event> arrayList;
        String str6;
        ArrayList arrayList2;
        char c2;
        JSONObject jSONObjectN;
        int length;
        JSONArray jSONArrayP;
        long jG;
        boolean z2;
        boolean z3;
        long jG2;
        long j3;
        long j4;
        long j5;
        int i2;
        long j6;
        String str7;
        String str8;
        String str9;
        ArrayList arrayList3;
        char c3;
        long j7;
        long j8;
        UsageEvents usageEvents;
        long j9;
        String str10;
        long timeStamp;
        int i3;
        ArrayList arrayList4;
        char c4;
        JSONObject jSONObject;
        boolean z4;
        JSONObject jSONObjectE;
        int length2;
        int i4;
        ArrayList arrayList5;
        ArrayList arrayList6;
        long j10;
        int i5;
        String str11;
        long jLongValue;
        int eventType;
        JSONObject jSONObjectM;
        int i6;
        long jLongValue2;
        long j11;
        String string;
        String str12;
        ArrayList arrayList7;
        ArrayList arrayList8;
        ArrayList arrayList9;
        String strH;
        JSONObject jSONObjectD;
        String string2;
        String[] strArrSplit;
        int i7;
        String[] strArrSplit2;
        Iterator it;
        int size;
        int i8;
        StringBuilder sb;
        long jG3;
        int eventType2;
        long j12;
        boolean zB;
        long j13;
        long j14;
        boolean z5;
        long j15;
        ArrayList arrayList10;
        long j16;
        long j17;
        String str13;
        String str14;
        ArrayList arrayList11;
        String str15;
        long j18;
        String str16;
        String str17;
        String str18;
        long j19;
        UsageEvents usageEvents2;
        long j20;
        String str19;
        a aVar2;
        JSONObject jSONObjectN2;
        int i9;
        JSONObject jSONObjectE2;
        int length3;
        int iIntValue;
        ArrayList arrayList12;
        long j21;
        String str20;
        String str21;
        ArrayList arrayList13;
        int i10;
        String str22;
        long timeStamp2;
        int eventType3;
        long timeStamp3;
        long timeStamp4;
        UsageEvents usageEvents3;
        boolean z6;
        long timeStamp5;
        long jLongValue3;
        long j22;
        y yVar = this;
        StringBuilder sbD = a.a.a.a.a.d("queryUsageEventSequence: startTime:", j, "  endTime:");
        sbD.append(j2);
        a.b.a.e.l0.p.c("UsageEvent", sbD.toString());
        Context contextB = a.b.a.e.e.b();
        JSONArray jSONArray2 = new JSONArray();
        UsageEvents usageEventsQueryEvents = ((UsageStatsManager) contextB.getSystemService("usagestats")).queryEvents(j, j2);
        if (usageEventsQueryEvents == null) {
            a.b.a.e.l0.p.s("UsageEvent", "queryUsageEventSequence usageEvents is null");
            return jSONArray2;
        }
        String str23 = "last_usage_resume_event";
        String str24 = "";
        String strH2 = a.b.a.e.l0.u.h("last_usage_resume_event", "");
        ArrayList arrayList14 = new ArrayList();
        String str25 = ",";
        String str26 = "\\|";
        if (!TextUtils.isEmpty(strH2)) {
            String[] strArrSplit3 = strH2.split("\\|");
            if (strArrSplit3.length <= 2) {
                int length4 = strArrSplit3.length;
                int i11 = 0;
                while (i11 < length4) {
                    String[] strArrSplit4 = strArrSplit3[i11].split(",");
                    String[] strArr = strArrSplit3;
                    int i12 = length4;
                    if (strArrSplit4.length == 2) {
                        try {
                            arrayList14.add(Pair.create(strArrSplit4[0], Long.valueOf(Long.parseLong(strArrSplit4[1]))));
                        } catch (Exception e) {
                            a.b.a.e.l0.p.h("UsageEvent", "getLastNotAssembledEvent error, e:", e);
                        }
                    }
                    i11++;
                    strArrSplit3 = strArr;
                    length4 = i12;
                }
            }
        }
        if (arrayList14.size() != 0) {
            a.b.a.e.l0.p.c("UsageEvent", "assemble usage events before->last not assembled event : " + arrayList14);
        }
        String str27 = "last_usage_input_shown_event";
        String strH3 = a.b.a.e.l0.u.h("last_usage_input_shown_event", "");
        if (TextUtils.isEmpty(strH3)) {
            pairCreate = null;
        } else {
            String[] strArrSplit5 = strH3.split(",");
            if (strArrSplit5.length != 2) {
                pairCreate = null;
            } else {
                try {
                    pairCreate = Pair.create(strArrSplit5[0], Long.valueOf(Long.parseLong(strArrSplit5[1])));
                } catch (Exception e2) {
                    a.b.a.e.l0.p.h("UsageEvent", "getLastNotAssembledInput error, e:", e2);
                    pairCreate = null;
                }
            }
        }
        a aVar3 = pairCreate != null ? new a((String) pairCreate.first, 10001, ((Long) pairCreate.second).longValue()) : new a("", 0, 0L);
        ArrayList arrayList15 = new ArrayList();
        int i13 = a.b.a.e.l0.q.f608c;
        String str28 = "last_event";
        String str29 = "usage reached max len: ";
        try {
            if (!(i13 == 4) || Build.VERSION.SDK_INT < 36) {
                a aVar4 = aVar3;
                str = "last_usage_input_shown_event";
                ArrayList arrayList16 = arrayList14;
                String str30 = "last_unfold_time";
                String str31 = "UsageEvent";
                UsageEvents usageEvents4 = usageEventsQueryEvents;
                str2 = str26;
                str3 = str23;
                str4 = str25;
                String str32 = str29;
                String str33 = "last_event";
                ArrayList arrayList17 = arrayList15;
                str5 = "";
                if (!(i13 == 3 || i13 == 5) || Build.VERSION.SDK_INT < 36) {
                    str5 = str5;
                    ArrayList arrayList18 = arrayList17;
                    aVar = aVar4;
                    UsageEvents usageEvents5 = usageEvents4;
                    if (!a.b.a.e.l0.q.f606a || Build.VERSION.SDK_INT < 36) {
                        int i14 = 0;
                        while (true) {
                            if (usageEvents5.hasNextEvent()) {
                                UsageEvents.Event event = new UsageEvents.Event();
                                usageEvents5 = usageEvents5;
                                usageEvents5.getNextEvent(event);
                                if (event.getEventType() == 1) {
                                    yVar = this;
                                    yVar.t(event);
                                    arrayList = arrayList18;
                                    arrayList2 = arrayList16;
                                    jSONObjectN = yVar.n(event, arrayList2, jSONArray2, arrayList);
                                } else {
                                    yVar = this;
                                    arrayList = arrayList18;
                                    arrayList2 = arrayList16;
                                    if (event.getEventType() == 2) {
                                        yVar.t(event);
                                        jSONObjectN = yVar.m(event, arrayList2, jSONArray2, arrayList);
                                    } else {
                                        if (event.getEventType() == 10001 || event.getEventType() == 10002) {
                                            yVar.t(event);
                                            JSONObject jSONObjectO = yVar.o(event, aVar);
                                            if (jSONObjectO == null) {
                                                arrayList16 = arrayList2;
                                                arrayList18 = arrayList;
                                            } else {
                                                jSONArray2.put(jSONObjectO);
                                                aVar.f461b = event.getEventType();
                                                aVar.f462c = event.getEventType() == 10001 ? event.getTimeStamp() : 0L;
                                                aVar.f460a = event.getEventType() == 10001 ? event.getPackageName() : null;
                                                jSONObjectN = jSONObjectO;
                                            }
                                        } else {
                                            jSONArray2 = jSONArray2;
                                            jSONObjectN = null;
                                        }
                                        if (jSONObjectN == null) {
                                            length = jSONObjectN.toString().length() + i14;
                                            if (length >= 286720) {
                                                str6 = str31;
                                                a.b.a.e.l0.p.c(str6, str32 + length);
                                                jSONArrayP = jSONArray2;
                                            } else {
                                                i14 = length;
                                            }
                                        }
                                        str31 = str31;
                                        arrayList16 = arrayList2;
                                        arrayList18 = arrayList;
                                        jSONArray2 = jSONArray2;
                                    }
                                }
                                jSONArray2 = jSONArray2;
                                if (jSONObjectN == null) {
                                    length = jSONObjectN.toString().length() + i14;
                                    if (length >= 286720) {
                                        str6 = str31;
                                        a.b.a.e.l0.p.c(str6, str32 + length);
                                        jSONArrayP = jSONArray2;
                                    } else {
                                        i14 = length;
                                    }
                                }
                                str31 = str31;
                                arrayList16 = arrayList2;
                                arrayList18 = arrayList;
                                jSONArray2 = jSONArray2;
                            } else {
                                yVar = this;
                                jSONArray = jSONArray2;
                                arrayList = arrayList18;
                                str6 = str31;
                                arrayList2 = arrayList16;
                                c2 = 0;
                            }
                        }
                    } else {
                        yVar = this;
                        jSONArrayP = p(usageEvents5, arrayList16, arrayList18, aVar, z);
                        arrayList = arrayList18;
                        str6 = str31;
                        arrayList2 = arrayList16;
                    }
                    c2 = 0;
                    if (arrayList2.size() > 0) {
                        size = arrayList2.size();
                        if (size > 2) {
                            i8 = size - 2;
                        } else {
                            i8 = 0;
                        }
                        sb = new StringBuilder();
                        while (i8 < size) {
                            Pair pair = (Pair) arrayList2.get(i8);
                            sb.append(((String) pair.first) + str4 + pair.second);
                            if (i8 != size - 1) {
                                sb.append("|");
                            }
                            i8++;
                        }
                        string = sb.toString();
                    } else {
                        string = str5;
                    }
                    if (!TextUtils.isEmpty(string)) {
                        a.b.a.e.l0.p.c(str6, "assemble usage events after->last not assembled event : " + string);
                    }
                    a.b.a.e.l0.u.m(str3, string);
                    if (aVar.f461b == 10001) {
                        str12 = aVar.f460a + str4 + aVar.f462c;
                    } else {
                        str12 = str5;
                    }
                    a.b.a.e.l0.u.m(str, str12);
                    a.b.a.e.l0.p.c(str6, "saved not assembled input event: " + str12);
                    a.b.a.e.l0.p.c(str6, "begin append hybrid usage event");
                    arrayList7 = new ArrayList();
                    List<a.b.a.e.f0.s.a> listE = a.b.a.e.f0.s.b().e(j, j2);
                    StringBuilder sbC = a.a.a.a.a.c("hybridUsages:");
                    arrayList8 = (ArrayList) listE;
                    sbC.append(arrayList8.size());
                    a.b.a.e.l0.p.c(str6, sbC.toString());
                    if (arrayList8.size() > 0) {
                        for (it = arrayList8.iterator(); it.hasNext(); it = it) {
                            a.b.a.e.f0.s.a aVar5 = (a.b.a.e.f0.s.a) it.next();
                            Object[] objArr = new Object[3];
                            objArr[c2] = aVar5.f372a;
                            objArr[1] = aVar5.f373b;
                            objArr[2] = Long.valueOf(aVar5.f374c);
                            a.b.a.e.l0.p.c(str6, String.format("----package: %s class: %s timestamp: %d", objArr));
                            arrayList7.add(new b(aVar5.f372a, aVar5.f373b, 3, aVar5.f374c));
                        }
                    }
                    for (UsageEvents.Event event2 : arrayList) {
                        arrayList7.add(new b(event2.getPackageName(), event2.getClassName(), event2.getEventType(), event2.getTimeStamp()));
                    }
                    a.b.a.e.e.b();
                    arrayList9 = new ArrayList();
                    strH = a.b.a.e.l0.u.h("last_unpaired_hybrid_event", str5);
                    if (!TextUtils.isEmpty(strH)) {
                        strArrSplit = strH.split(str2);
                        if (strArrSplit.length <= 3) {
                            for (String str34 : strArrSplit) {
                                strArrSplit2 = str34.split(str4);
                                if (strArrSplit2.length == 4) {
                                    try {
                                        arrayList9.add(new b(strArrSplit2[c2], strArrSplit2[1], Integer.parseInt(strArrSplit2[3]), Long.parseLong(strArrSplit2[2])));
                                    } catch (Exception unused) {
                                    }
                                }
                            }
                        }
                    }
                    arrayList7.addAll(arrayList9);
                    Collections.sort(arrayList7, new x(yVar));
                    for (List<b> list : (ArrayList) yVar.s(arrayList7)) {
                        jSONObjectD = yVar.d(list);
                        if (jSONObjectD != null) {
                            jSONArrayP.put(jSONObjectD);
                        }
                        if (a.b.a.e.l0.p.f602a) {
                            a.b.a.e.l0.p.c(str6, "--------------------------------------------------");
                            for (b bVar : list) {
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("####");
                                Object[] objArr2 = new Object[4];
                                objArr2[c2] = bVar.f463a;
                                objArr2[1] = bVar.f464b;
                                objArr2[2] = Long.valueOf(bVar.f466d);
                                objArr2[3] = Integer.valueOf(bVar.f465c);
                                sb2.append(String.format("package: %s class: %s timestamp: %d, event: %d", objArr2));
                                a.b.a.e.l0.p.c(str6, sb2.toString());
                            }
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append("compact result: ");
                            if (jSONObjectD == null) {
                                string2 = "not contains";
                            } else {
                                string2 = jSONObjectD.toString();
                            }
                            sb3.append(string2);
                            a.b.a.e.l0.p.c(str6, sb3.toString());
                        }
                    }
                    yVar.g = jSONArrayP.length();
                    yVar.v(jSONArrayP);
                    return jSONArrayP;
                }
                if (z) {
                    boolean zB2 = a.b.a.e.l0.u.b("last_fold_state", false);
                    jG = a.b.a.e.l0.u.g(str30, 0L);
                    long jG4 = a.b.a.e.l0.u.g("last_fold_time", 0L);
                    Pair<Integer, Long> pairJ = yVar.j(a.b.a.e.l0.u.h("fold_last_inter_duration", str5));
                    int iIntValue2 = pairJ == null ? 38 : ((Integer) pairJ.first).intValue();
                    if (pairJ == null) {
                        j11 = 0;
                        jLongValue2 = 0;
                    } else {
                        jLongValue2 = ((Long) pairJ.second).longValue();
                        j11 = 0;
                    }
                    long j23 = j11;
                    long jG5 = a.b.a.e.l0.u.g("fold_last_rotate_time", j23);
                    z2 = zB2;
                    j4 = jLongValue2;
                    jG2 = a.b.a.e.l0.u.g("fold_last_exter_duration", j23);
                    i2 = iIntValue2;
                    j5 = jG5;
                    j3 = jG4;
                    z3 = true;
                } else {
                    arrayList16.clear();
                    jG = 0;
                    z2 = false;
                    z3 = false;
                    jG2 = 0;
                    j3 = 0;
                    j4 = 0;
                    j5 = 0;
                    i2 = 0;
                }
                long j24 = jG;
                String str35 = "fold_last_exter_duration";
                String str36 = "fold_last_inter_duration";
                long j25 = j24;
                a.b.a.e.l0.p.c(str31, "handleFold->isFold: " + z2 + ", lastUnfoldTime: " + j25 + ", lastFoldTime: " + j3 + ", interRotation: " + i2 + ", interDuration: " + j4 + ", lastRotateTime: " + j5 + ", lastExterDuration: " + jG2);
                JSONArray jSONArray3 = new JSONArray();
                int eventType4 = i2;
                long timeStamp6 = j5;
                long timeStamp7 = j4;
                int i15 = 0;
                long j26 = j3;
                long j27 = jG2;
                while (true) {
                    if (!usageEvents4.hasNextEvent()) {
                        j6 = j26;
                        str7 = str35;
                        str8 = str36;
                        str9 = str30;
                        long j28 = j25;
                        arrayList3 = arrayList17;
                        aVar = aVar4;
                        c3 = 0;
                        j7 = j28;
                        j8 = timeStamp6;
                        break;
                    }
                    UsageEvents.Event event3 = new UsageEvents.Event();
                    UsageEvents usageEvents6 = usageEvents4;
                    usageEvents6.getNextEvent(event3);
                    if (event3.getEventType() == 10001 || event3.getEventType() == 10002) {
                        usageEvents = usageEvents6;
                        j6 = j26;
                        j9 = j27;
                        str7 = str35;
                        str8 = str36;
                        str9 = str30;
                        str10 = str33;
                        timeStamp = j25;
                        arrayList3 = arrayList17;
                        i3 = i15;
                        arrayList4 = arrayList16;
                        c4 = 0;
                        yVar.t(event3);
                        aVar = aVar4;
                        JSONObject jSONObjectO2 = yVar.o(event3, aVar);
                        if (jSONObjectO2 == null) {
                            str32 = str32;
                        } else {
                            jSONArray3.put(jSONObjectO2);
                            aVar.f461b = event3.getEventType();
                            aVar.f462c = event3.getEventType() == 10001 ? event3.getTimeStamp() : 0L;
                            aVar.f460a = event3.getEventType() == 10001 ? event3.getPackageName() : null;
                            jSONObject = jSONObjectO2;
                            z4 = z2;
                            jSONObjectE = jSONObject;
                            c3 = c4;
                            if (jSONObjectE != null) {
                                length2 = jSONObjectE.toString().length() + i3;
                                if (length2 >= 286720) {
                                    a.b.a.e.l0.p.c(str31, str32 + length2);
                                    arrayList16 = arrayList4;
                                    j7 = timeStamp;
                                    z2 = z4;
                                    j8 = timeStamp6;
                                    j27 = j9;
                                    break;
                                }
                                i3 = length2;
                            }
                            z2 = z4;
                        }
                        str32 = str32;
                        aVar4 = aVar;
                        arrayList16 = arrayList4;
                        str33 = str10;
                        i15 = i3;
                        j25 = timeStamp;
                        arrayList17 = arrayList3;
                        j27 = j9;
                        usageEvents4 = usageEvents;
                        str35 = str7;
                        str30 = str9;
                        str36 = str8;
                        j26 = j6;
                    } else {
                        if (event3.getEventType() == 1 || event3.getEventType() == 2 || event3.getEventType() == 38 || event3.getEventType() == 39 || event3.getEventType() == 40 || event3.getEventType() == 41 || event3.getEventType() == 42 || event3.getEventType() == 43) {
                            yVar.t(event3);
                            if (z3) {
                                z4 = z2;
                                i4 = eventType4;
                            } else {
                                Pair<Boolean, Integer> pairQ = yVar.q(event3);
                                if (pairQ == null) {
                                    usageEvents = usageEvents6;
                                    j6 = j26;
                                    j9 = j27;
                                    str7 = str35;
                                    str8 = str36;
                                    str9 = str30;
                                    str10 = str33;
                                    timeStamp = j25;
                                    arrayList3 = arrayList17;
                                    aVar = aVar4;
                                    i3 = i15;
                                    arrayList4 = arrayList16;
                                } else {
                                    boolean zBooleanValue = ((Boolean) pairQ.first).booleanValue();
                                    if (!zBooleanValue) {
                                        eventType4 = ((Integer) pairQ.second).intValue();
                                    }
                                    z4 = zBooleanValue;
                                    i4 = eventType4;
                                    z3 = true;
                                }
                                str32 = str32;
                                str32 = str32;
                                aVar4 = aVar;
                                arrayList16 = arrayList4;
                                str33 = str10;
                                i15 = i3;
                                j25 = timeStamp;
                                arrayList17 = arrayList3;
                                j27 = j9;
                                usageEvents4 = usageEvents;
                                str35 = str7;
                                str30 = str9;
                                str36 = str8;
                                j26 = j6;
                            }
                            if (event3.getEventType() == 1) {
                                arrayList5 = arrayList17;
                                arrayList6 = arrayList16;
                                jSONObjectM = yVar.n(event3, arrayList6, jSONArray3, arrayList5);
                            } else {
                                arrayList5 = arrayList17;
                                arrayList6 = arrayList16;
                                int i16 = i4;
                                i3 = i15;
                                j10 = j26;
                                if (event3.getEventType() != 2) {
                                    if (event3.getEventType() == 38 || event3.getEventType() == 40) {
                                        i5 = i16;
                                        arrayList4 = arrayList6;
                                        usageEvents = usageEvents6;
                                        str7 = str35;
                                        str8 = str36;
                                        str9 = str30;
                                        j6 = j10;
                                        arrayList3 = arrayList5;
                                        timeStamp = j25;
                                        str11 = str33;
                                        c3 = 0;
                                        if (!z4) {
                                            if (i5 == 39 || i5 == 41) {
                                                if (!arrayList4.isEmpty()) {
                                                    jLongValue = ((Long) ((Pair) arrayList4.get(arrayList4.size() - 1)).second).longValue();
                                                    if (timeStamp6 > jLongValue) {
                                                        timeStamp7 = (event3.getTimeStamp() - timeStamp6) + timeStamp7;
                                                    } else {
                                                        timeStamp7 = (event3.getTimeStamp() - jLongValue) + timeStamp7;
                                                    }
                                                }
                                                j9 = j27;
                                                str10 = str11;
                                                jSONObjectE = e(event3.getEventType(), event3.getPackageName(), event3.getClassName(), event3.getTimeStamp(), timeStamp7);
                                                jSONObjectE.put(str10, i5);
                                                jSONArray3.put(jSONObjectE);
                                                eventType4 = event3.getEventType();
                                                timeStamp6 = event3.getTimeStamp();
                                                aVar = aVar4;
                                                timeStamp7 = 0;
                                            } else {
                                                if (i5 != event3.getEventType()) {
                                                    eventType = event3.getEventType();
                                                } else {
                                                    eventType = i5;
                                                }
                                                jSONObjectE = null;
                                                eventType4 = eventType;
                                                j9 = j27;
                                                str10 = str11;
                                                aVar = aVar4;
                                            }
                                        }
                                        eventType4 = i5;
                                        j9 = j27;
                                        z2 = z4;
                                        str10 = str11;
                                        aVar = aVar4;
                                        str32 = str32;
                                        str32 = str32;
                                        aVar4 = aVar;
                                        arrayList16 = arrayList4;
                                        str33 = str10;
                                        i15 = i3;
                                        j25 = timeStamp;
                                        arrayList17 = arrayList3;
                                        j27 = j9;
                                        usageEvents4 = usageEvents;
                                        str35 = str7;
                                        str30 = str9;
                                        str36 = str8;
                                        j26 = j6;
                                    } else {
                                        if (event3.getEventType() == 39 || event3.getEventType() == 41) {
                                            i5 = i16;
                                            arrayList4 = arrayList6;
                                            usageEvents = usageEvents6;
                                            str7 = str35;
                                            str8 = str36;
                                            str9 = str30;
                                            j6 = j10;
                                            arrayList3 = arrayList5;
                                            timeStamp = j25;
                                            str11 = str33;
                                            c3 = 0;
                                            if (!z4) {
                                                if (i5 == 38 || i5 == 40) {
                                                    if (!arrayList4.isEmpty()) {
                                                        jLongValue = ((Long) ((Pair) arrayList4.get(arrayList4.size() - 1)).second).longValue();
                                                        if (timeStamp6 > jLongValue) {
                                                            timeStamp7 = (event3.getTimeStamp() - timeStamp6) + timeStamp7;
                                                        } else {
                                                            timeStamp7 = (event3.getTimeStamp() - jLongValue) + timeStamp7;
                                                        }
                                                    }
                                                    j9 = j27;
                                                    str10 = str11;
                                                    jSONObjectE = e(event3.getEventType(), event3.getPackageName(), event3.getClassName(), event3.getTimeStamp(), timeStamp7);
                                                    jSONObjectE.put(str10, i5);
                                                    jSONArray3.put(jSONObjectE);
                                                    eventType4 = event3.getEventType();
                                                    timeStamp6 = event3.getTimeStamp();
                                                    aVar = aVar4;
                                                    timeStamp7 = 0;
                                                } else {
                                                    if (i5 != event3.getEventType()) {
                                                        eventType = event3.getEventType();
                                                    }
                                                    jSONObjectE = null;
                                                    eventType4 = eventType;
                                                    j9 = j27;
                                                    str10 = str11;
                                                    aVar = aVar4;
                                                }
                                            }
                                            eventType4 = i5;
                                            j9 = j27;
                                            z2 = z4;
                                            str10 = str11;
                                            aVar = aVar4;
                                            str32 = str32;
                                            str32 = str32;
                                            aVar4 = aVar;
                                            arrayList16 = arrayList4;
                                            str33 = str10;
                                            i15 = i3;
                                            j25 = timeStamp;
                                            arrayList17 = arrayList3;
                                            j27 = j9;
                                            usageEvents4 = usageEvents;
                                            str35 = str7;
                                            str30 = str9;
                                            str36 = str8;
                                            j26 = j6;
                                        } else {
                                            if (event3.getEventType() == 42) {
                                                long timeStamp8 = event3.getTimeStamp();
                                                c3 = 0;
                                                arrayList4 = arrayList6;
                                                str9 = str30;
                                                arrayList3 = arrayList5;
                                                usageEvents = usageEvents6;
                                                str7 = str35;
                                                str8 = str36;
                                                timeStamp = j25;
                                                jSONObjectE = e(42, event3.getPackageName(), event3.getClassName(), event3.getTimeStamp(), yVar.k(event3, arrayList6, j25) + timeStamp7);
                                                str11 = str33;
                                                jSONObjectE.put(str11, i16);
                                                jSONArray3.put(jSONObjectE);
                                                z4 = true;
                                                eventType = i16;
                                                j6 = timeStamp8;
                                                timeStamp7 = 0;
                                            } else {
                                                i5 = i16;
                                                arrayList4 = arrayList6;
                                                usageEvents = usageEvents6;
                                                str7 = str35;
                                                str8 = str36;
                                                str9 = str30;
                                                arrayList3 = arrayList5;
                                                timeStamp = j25;
                                                str11 = str33;
                                                c3 = 0;
                                                if (event3.getEventType() == 43) {
                                                    timeStamp = event3.getTimeStamp();
                                                    j6 = j10;
                                                    jSONObjectE = e(43, event3.getPackageName(), event3.getClassName(), event3.getTimeStamp(), j27 + yVar.k(event3, arrayList4, j10));
                                                    jSONArray3.put(jSONObjectE);
                                                    z4 = false;
                                                    eventType = 38;
                                                    str11 = str11;
                                                    j27 = 0;
                                                } else {
                                                    j6 = j10;
                                                }
                                            }
                                            eventType4 = eventType;
                                            j9 = j27;
                                            str10 = str11;
                                            aVar = aVar4;
                                        }
                                        eventType = i5;
                                        jSONObjectE = null;
                                        eventType4 = eventType;
                                        j9 = j27;
                                        str10 = str11;
                                        aVar = aVar4;
                                    }
                                    if (arrayList2.size() > 0) {
                                        size = arrayList2.size();
                                        if (size > 2) {
                                            i8 = size - 2;
                                        } else {
                                            i8 = 0;
                                        }
                                        sb = new StringBuilder();
                                        while (i8 < size) {
                                            Pair pair2 = (Pair) arrayList2.get(i8);
                                            sb.append(((String) pair2.first) + str4 + pair2.second);
                                            if (i8 != size - 1) {
                                                sb.append("|");
                                            }
                                            i8++;
                                        }
                                        string = sb.toString();
                                    } else {
                                        string = str5;
                                    }
                                    if (!TextUtils.isEmpty(string)) {
                                        a.b.a.e.l0.p.c(str6, "assemble usage events after->last not assembled event : " + string);
                                    }
                                    a.b.a.e.l0.u.m(str3, string);
                                    if (aVar.f461b == 10001) {
                                        str12 = aVar.f460a + str4 + aVar.f462c;
                                    } else {
                                        str12 = str5;
                                    }
                                    a.b.a.e.l0.u.m(str, str12);
                                    a.b.a.e.l0.p.c(str6, "saved not assembled input event: " + str12);
                                    a.b.a.e.l0.p.c(str6, "begin append hybrid usage event");
                                    arrayList7 = new ArrayList();
                                    List<a.b.a.e.f0.s.a> listE2 = a.b.a.e.f0.s.b().e(j, j2);
                                    StringBuilder sbC2 = a.a.a.a.a.c("hybridUsages:");
                                    arrayList8 = (ArrayList) listE2;
                                    sbC2.append(arrayList8.size());
                                    a.b.a.e.l0.p.c(str6, sbC2.toString());
                                    if (arrayList8.size() > 0) {
                                        while (it.hasNext()) {
                                            a.b.a.e.f0.s.a aVar6 = (a.b.a.e.f0.s.a) it.next();
                                            Object[] objArr3 = new Object[3];
                                            objArr3[c2] = aVar6.f372a;
                                            objArr3[1] = aVar6.f373b;
                                            objArr3[2] = Long.valueOf(aVar6.f374c);
                                            a.b.a.e.l0.p.c(str6, String.format("----package: %s class: %s timestamp: %d", objArr3));
                                            arrayList7.add(new b(aVar6.f372a, aVar6.f373b, 3, aVar6.f374c));
                                        }
                                    }
                                    while (r4.hasNext()) {
                                        arrayList7.add(new b(event2.getPackageName(), event2.getClassName(), event2.getEventType(), event2.getTimeStamp()));
                                    }
                                    a.b.a.e.e.b();
                                    arrayList9 = new ArrayList();
                                    strH = a.b.a.e.l0.u.h("last_unpaired_hybrid_event", str5);
                                    if (!TextUtils.isEmpty(strH)) {
                                        strArrSplit = strH.split(str2);
                                        if (strArrSplit.length <= 3) {
                                            while (i7 < r6) {
                                                strArrSplit2 = str34.split(str4);
                                                if (strArrSplit2.length == 4) {
                                                    arrayList9.add(new b(strArrSplit2[c2], strArrSplit2[1], Integer.parseInt(strArrSplit2[3]), Long.parseLong(strArrSplit2[2])));
                                                }
                                            }
                                        }
                                    }
                                    arrayList7.addAll(arrayList9);
                                    Collections.sort(arrayList7, new x(yVar));
                                    while (r3.hasNext()) {
                                        jSONObjectD = yVar.d(list);
                                        if (jSONObjectD != null) {
                                            jSONArrayP.put(jSONObjectD);
                                        }
                                        if (a.b.a.e.l0.p.f602a) {
                                            a.b.a.e.l0.p.c(str6, "--------------------------------------------------");
                                            while (r4.hasNext()) {
                                                StringBuilder sb4 = new StringBuilder();
                                                sb4.append("####");
                                                Object[] objArr4 = new Object[4];
                                                objArr4[c2] = bVar.f463a;
                                                objArr4[1] = bVar.f464b;
                                                objArr4[2] = Long.valueOf(bVar.f466d);
                                                objArr4[3] = Integer.valueOf(bVar.f465c);
                                                sb4.append(String.format("package: %s class: %s timestamp: %d, event: %d", objArr4));
                                                a.b.a.e.l0.p.c(str6, sb4.toString());
                                            }
                                            StringBuilder sb5 = new StringBuilder();
                                            sb5.append("compact result: ");
                                            if (jSONObjectD == null) {
                                                string2 = "not contains";
                                            } else {
                                                string2 = jSONObjectD.toString();
                                            }
                                            sb5.append(string2);
                                            a.b.a.e.l0.p.c(str6, sb5.toString());
                                        }
                                    }
                                    yVar.g = jSONArrayP.length();
                                    yVar.v(jSONArrayP);
                                    return jSONArrayP;
                                }
                                long jI = yVar.i(arrayList6, event3, z4 ? j10 : j25);
                                a.b.a.e.l0.p.c(str31, "handleFold->firstActivityDuration: " + jI);
                                jSONObjectM = yVar.m(event3, arrayList6, jSONArray3, arrayList5);
                                if (z4) {
                                    if (jI == 0) {
                                        jI = jSONObjectM.optLong("duration");
                                    }
                                    j27 += jI;
                                } else {
                                    if (jI == 0) {
                                        jI = jSONObjectM.optLong("duration");
                                    }
                                    timeStamp7 += jI;
                                }
                                i6 = i16;
                                jSONObject = jSONObjectM;
                                z2 = z4;
                                arrayList4 = arrayList6;
                                usageEvents = usageEvents6;
                                str7 = str35;
                                str8 = str36;
                                str9 = str30;
                                j6 = j10;
                                arrayList3 = arrayList5;
                                eventType4 = i6;
                                timeStamp = j25;
                                j9 = j27;
                                aVar = aVar4;
                                str10 = str33;
                                c4 = 0;
                                z4 = z2;
                                jSONObjectE = jSONObject;
                                c3 = c4;
                            }
                            if (jSONObjectE != null) {
                                length2 = jSONObjectE.toString().length() + i3;
                                if (length2 >= 286720) {
                                    a.b.a.e.l0.p.c(str31, str32 + length2);
                                    arrayList16 = arrayList4;
                                    j7 = timeStamp;
                                    z2 = z4;
                                    j8 = timeStamp6;
                                    j27 = j9;
                                    break;
                                }
                                i3 = length2;
                            }
                            z2 = z4;
                            str32 = str32;
                            aVar4 = aVar;
                            arrayList16 = arrayList4;
                            str33 = str10;
                            i15 = i3;
                            j25 = timeStamp;
                            arrayList17 = arrayList3;
                            j27 = j9;
                            usageEvents4 = usageEvents;
                            str35 = str7;
                            str30 = str9;
                            str36 = str8;
                            j26 = j6;
                        } else {
                            z4 = z2;
                            i4 = eventType4;
                            arrayList5 = arrayList17;
                            jSONObjectM = null;
                            arrayList6 = arrayList16;
                        }
                        i6 = i4;
                        i3 = i15;
                        j10 = j26;
                        jSONObject = jSONObjectM;
                        z2 = z4;
                        arrayList4 = arrayList6;
                        usageEvents = usageEvents6;
                        str7 = str35;
                        str8 = str36;
                        str9 = str30;
                        j6 = j10;
                        arrayList3 = arrayList5;
                        eventType4 = i6;
                        timeStamp = j25;
                        j9 = j27;
                        aVar = aVar4;
                        str10 = str33;
                        c4 = 0;
                        z4 = z2;
                        jSONObjectE = jSONObject;
                        c3 = c4;
                        if (jSONObjectE != null) {
                            length2 = jSONObjectE.toString().length() + i3;
                            if (length2 >= 286720) {
                                a.b.a.e.l0.p.c(str31, str32 + length2);
                                arrayList16 = arrayList4;
                                j7 = timeStamp;
                                z2 = z4;
                                j8 = timeStamp6;
                                j27 = j9;
                                break;
                            }
                            i3 = length2;
                        }
                        z2 = z4;
                        str32 = str32;
                        aVar4 = aVar;
                        arrayList16 = arrayList4;
                        str33 = str10;
                        i15 = i3;
                        j25 = timeStamp;
                        arrayList17 = arrayList3;
                        j27 = j9;
                        usageEvents4 = usageEvents;
                        str35 = str7;
                        str30 = str9;
                        str36 = str8;
                        j26 = j6;
                    }
                }
                a.b.a.e.l0.u.a();
                a.b.a.e.l0.u.k("last_fold_state", z2);
                a.b.a.e.l0.u.a();
                a.b.a.e.l0.u.l("last_fold_time", j6);
                a.b.a.e.l0.u.a();
                a.b.a.e.l0.u.l(str9, j7);
                StringBuilder sb6 = new StringBuilder();
                sb6.append(eventType4);
                str4 = str4;
                sb6.append(str4);
                sb6.append(timeStamp7);
                String string3 = sb6.toString();
                a.b.a.e.l0.u.a();
                a.b.a.e.l0.u.m(str8, string3);
                a.b.a.e.l0.u.a();
                a.b.a.e.l0.u.l(str7, j27);
                a.b.a.e.l0.u.a();
                a.b.a.e.l0.u.l("fold_last_rotate_time", j8);
                yVar = this;
                jSONArray = jSONArray3;
                arrayList = arrayList3;
                str6 = str31;
                c2 = c3;
                arrayList2 = arrayList16;
            } else {
                String str37 = "flip_last_exter_duration";
                a aVar7 = aVar3;
                if (z) {
                    long jG6 = a.b.a.e.l0.u.g("flip_last_inter_duration", 0L);
                    String strH4 = a.b.a.e.l0.u.h("flip_last_exter_duration", "");
                    a.b.a.e.l0.p.c("UsageEvent", "handleFlip->lastInterDuration: " + jG6 + ", lastExterDuration: " + strH4);
                    Pair<Integer, Long> pairJ2 = yVar.j(strH4);
                    int iIntValue3 = pairJ2 == null ? 38 : ((Integer) pairJ2.first).intValue();
                    if (pairJ2 == null) {
                        j22 = 0;
                        jLongValue3 = 0;
                    } else {
                        jLongValue3 = ((Long) pairJ2.second).longValue();
                        j22 = 0;
                    }
                    long j29 = j22;
                    long jG7 = a.b.a.e.l0.u.g("last_unfold_time", j29);
                    long jG8 = a.b.a.e.l0.u.g("last_fold_time", j29);
                    jG3 = a.b.a.e.l0.u.g("flip_last_rotate_time", j29);
                    z5 = true;
                    eventType2 = iIntValue3;
                    zB = a.b.a.e.l0.u.b("last_fold_state", false);
                    j12 = jG6;
                    j13 = jG7;
                    j14 = jLongValue3;
                    j15 = jG8;
                } else {
                    arrayList14.clear();
                    jG3 = 0;
                    eventType2 = 0;
                    j12 = 0;
                    zB = false;
                    j13 = 0;
                    j14 = 0;
                    z5 = false;
                    j15 = 0;
                }
                StringBuilder sb7 = new StringBuilder();
                sb7.append("handleFlip->lastInterDuration: ");
                sb7.append(j12);
                sb7.append(", rotation: ");
                sb7.append(eventType2);
                sb7.append(", rotationDuration: ");
                sb7.append(j14);
                sb7.append(", lastUnfoldTime: ");
                sb7.append(j13);
                sb7.append(", lastFoldTime: ");
                sb7.append(j15);
                sb7.append(", lastRotateTime: ");
                sb7.append(jG3);
                String str38 = ", lastRotateTime: ";
                sb7.append(", isFold: ");
                sb7.append(zB);
                String str39 = "UsageEvent";
                a.b.a.e.l0.p.c(str39, sb7.toString());
                JSONArray jSONArray4 = new JSONArray();
                int i17 = 0;
                long j30 = j12;
                long j31 = j15;
                long j32 = j13;
                long j33 = j30;
                long j34 = jG3;
                long j35 = j14;
                long j36 = j34;
                while (true) {
                    if (!usageEventsQueryEvents.hasNextEvent()) {
                        arrayList10 = arrayList14;
                        j16 = j36;
                        j17 = j32;
                        str2 = str26;
                        str3 = str23;
                        str13 = str25;
                        str14 = str37;
                        arrayList11 = arrayList15;
                        str = str27;
                        str15 = str24;
                        j18 = j33;
                        str16 = str39;
                        break;
                    }
                    j16 = j36;
                    UsageEvents.Event event4 = new UsageEvents.Event();
                    UsageEvents usageEvents7 = usageEventsQueryEvents;
                    usageEvents7.getNextEvent(event4);
                    int i18 = eventType2;
                    boolean zBooleanValue2 = zB;
                    if (event4.getEventType() == 10001 || event4.getEventType() == 10002) {
                        arrayList10 = arrayList14;
                        j17 = j32;
                        str2 = str26;
                        str3 = str23;
                        str13 = str25;
                        str14 = str37;
                        str17 = str29;
                        arrayList11 = arrayList15;
                        str18 = str28;
                        str = str27;
                        j19 = j31;
                        usageEvents2 = usageEvents7;
                        j18 = j33;
                        str15 = str24;
                        j20 = j16;
                        str19 = str39;
                        yVar.t(event4);
                        aVar2 = aVar7;
                        JSONObject jSONObjectO3 = yVar.o(event4, aVar2);
                        if (jSONObjectO3 == null) {
                            j16 = j20;
                            j31 = j19;
                            eventType2 = i18;
                            zB = zBooleanValue2;
                        } else {
                            jSONArray4.put(jSONObjectO3);
                            aVar2.f461b = event4.getEventType();
                            j16 = j20;
                            aVar2.f462c = event4.getEventType() == 10001 ? event4.getTimeStamp() : 0L;
                            aVar2.f460a = event4.getEventType() == 10001 ? event4.getPackageName() : null;
                            jSONObjectN2 = jSONObjectO3;
                            i9 = i18;
                            i18 = i9;
                            zBooleanValue2 = zBooleanValue2;
                            jSONObjectE2 = jSONObjectN2;
                            j31 = j19;
                            eventType2 = i18;
                            zB = zBooleanValue2;
                            if (jSONObjectE2 != null) {
                                length3 = jSONObjectE2.toString().length() + i17;
                                if (length3 >= 286720) {
                                    a.b.a.e.l0.p.c(str19, str17 + length3);
                                    aVar7 = aVar2;
                                    str16 = str19;
                                    break;
                                }
                                i17 = length3;
                            }
                        }
                        str24 = str15;
                        aVar7 = aVar2;
                        str39 = str19;
                        str28 = str18;
                        str29 = str17;
                        j33 = j18;
                        usageEventsQueryEvents = usageEvents2;
                        j36 = j16;
                        j32 = j17;
                        str37 = str14;
                        str27 = str;
                        arrayList14 = arrayList10;
                        str25 = str13;
                        str23 = str3;
                        arrayList15 = arrayList11;
                        str26 = str2;
                    } else {
                        if (event4.getEventType() == 1 || event4.getEventType() == 2 || event4.getEventType() == 38 || event4.getEventType() == 39 || event4.getEventType() == 40 || event4.getEventType() == 41 || event4.getEventType() == 42 || event4.getEventType() == 43) {
                            yVar.t(event4);
                            if (z5) {
                                iIntValue = i18;
                            } else {
                                Pair<Boolean, Integer> pairQ2 = yVar.q(event4);
                                if (pairQ2 == null) {
                                    arrayList10 = arrayList14;
                                    j17 = j32;
                                    str2 = str26;
                                    str3 = str23;
                                    str13 = str25;
                                    str14 = str37;
                                    str17 = str29;
                                    arrayList11 = arrayList15;
                                    str18 = str28;
                                    str = str27;
                                    j19 = j31;
                                    usageEvents2 = usageEvents7;
                                    j18 = j33;
                                    aVar2 = aVar7;
                                    str15 = str24;
                                    j20 = j16;
                                    str19 = str39;
                                    j16 = j20;
                                    j31 = j19;
                                    eventType2 = i18;
                                    zB = zBooleanValue2;
                                    str24 = str15;
                                    aVar7 = aVar2;
                                    str39 = str19;
                                    str28 = str18;
                                    str29 = str17;
                                    j33 = j18;
                                    usageEventsQueryEvents = usageEvents2;
                                    j36 = j16;
                                    j32 = j17;
                                    str37 = str14;
                                    str27 = str;
                                    arrayList14 = arrayList10;
                                    str25 = str13;
                                    str23 = str3;
                                    arrayList15 = arrayList11;
                                    str26 = str2;
                                } else {
                                    zBooleanValue2 = ((Boolean) pairQ2.first).booleanValue();
                                    z5 = true;
                                    iIntValue = ((Integer) pairQ2.second).intValue();
                                }
                            }
                            i18 = iIntValue;
                            if (event4.getEventType() == 1) {
                                arrayList12 = arrayList15;
                                jSONObjectN2 = yVar.n(event4, arrayList14, jSONArray4, arrayList12);
                            } else {
                                arrayList12 = arrayList15;
                                j21 = j31;
                                if (event4.getEventType() == 2) {
                                    long jI2 = yVar.i(arrayList14, event4, zBooleanValue2 ? j21 : j32);
                                    StringBuilder sb8 = new StringBuilder();
                                    usageEvents2 = usageEvents7;
                                    sb8.append("handleFlip->firstActivityDuration: ");
                                    sb8.append(jI2);
                                    a.b.a.e.l0.p.c(str39, sb8.toString());
                                    jSONObjectN2 = yVar.m(event4, arrayList14, jSONArray4, arrayList12);
                                    if (zBooleanValue2) {
                                        if (jI2 == 0) {
                                            jI2 = jSONObjectN2.optLong("duration");
                                        }
                                        j35 += jI2;
                                    } else {
                                        if (jI2 == 0) {
                                            jI2 = jSONObjectN2.optLong("duration");
                                        }
                                        j33 += jI2;
                                    }
                                    arrayList10 = arrayList14;
                                    j17 = j32;
                                    str2 = str26;
                                    str3 = str23;
                                    str13 = str25;
                                    str14 = str37;
                                    str17 = str29;
                                    str18 = str28;
                                    str = str27;
                                    str15 = str24;
                                    j19 = j21;
                                    arrayList11 = arrayList12;
                                    j18 = j33;
                                    aVar2 = aVar7;
                                    str19 = str39;
                                    jSONObjectE2 = jSONObjectN2;
                                    j31 = j19;
                                    eventType2 = i18;
                                    zB = zBooleanValue2;
                                    if (jSONObjectE2 != null) {
                                        length3 = jSONObjectE2.toString().length() + i17;
                                        if (length3 >= 286720) {
                                            a.b.a.e.l0.p.c(str19, str17 + length3);
                                            aVar7 = aVar2;
                                            str16 = str19;
                                            break;
                                        }
                                        i17 = length3;
                                    }
                                    str24 = str15;
                                    aVar7 = aVar2;
                                    str39 = str19;
                                    str28 = str18;
                                    str29 = str17;
                                    j33 = j18;
                                    usageEventsQueryEvents = usageEvents2;
                                    j36 = j16;
                                    j32 = j17;
                                    str37 = str14;
                                    str27 = str;
                                    arrayList14 = arrayList10;
                                    str25 = str13;
                                    str23 = str3;
                                    arrayList15 = arrayList11;
                                    str26 = str2;
                                } else {
                                    usageEvents2 = usageEvents7;
                                    if (event4.getEventType() == 38 || event4.getEventType() == 39 || event4.getEventType() == 40 || event4.getEventType() == 41) {
                                        j17 = j32;
                                        str2 = str26;
                                        str3 = str23;
                                        str13 = str25;
                                        str14 = str37;
                                        str17 = str29;
                                        str20 = str28;
                                        str = str27;
                                        str15 = str24;
                                        j19 = j21;
                                        str21 = str38;
                                        j18 = j33;
                                        str19 = str39;
                                        arrayList13 = arrayList12;
                                        if (zBooleanValue2) {
                                            eventType2 = event4.getEventType();
                                            if (i18 == 0) {
                                                timeStamp4 = event4.getTimeStamp();
                                                jSONObjectE2 = null;
                                                arrayList10 = arrayList14;
                                                arrayList11 = arrayList13;
                                                str38 = str21;
                                                aVar2 = aVar7;
                                                j16 = timeStamp4;
                                                zB = zBooleanValue2;
                                                str18 = str20;
                                                j31 = j19;
                                                if (jSONObjectE2 != null) {
                                                    length3 = jSONObjectE2.toString().length() + i17;
                                                    if (length3 >= 286720) {
                                                        a.b.a.e.l0.p.c(str19, str17 + length3);
                                                        aVar7 = aVar2;
                                                        str16 = str19;
                                                        break;
                                                    }
                                                    i17 = length3;
                                                }
                                                str24 = str15;
                                                aVar7 = aVar2;
                                                str39 = str19;
                                                str28 = str18;
                                                str29 = str17;
                                                j33 = j18;
                                                usageEventsQueryEvents = usageEvents2;
                                                j36 = j16;
                                                j32 = j17;
                                                str37 = str14;
                                                str27 = str;
                                                arrayList14 = arrayList10;
                                                str25 = str13;
                                                str23 = str3;
                                                arrayList15 = arrayList11;
                                                str26 = str2;
                                            } else if (i18 != eventType2) {
                                                if (arrayList14.isEmpty()) {
                                                    arrayList10 = arrayList14;
                                                    str22 = str20;
                                                    timeStamp2 = j35;
                                                } else {
                                                    arrayList10 = arrayList14;
                                                    long jLongValue4 = ((Long) ((Pair) arrayList14.get(arrayList14.size() - 1)).second).longValue();
                                                    StringBuilder sbD2 = a.a.a.a.a.d("handleFlip->lastActivityResumeTime: ", jLongValue4, str21);
                                                    str22 = str20;
                                                    sbD2.append(j16);
                                                    a.b.a.e.l0.p.c(str19, sbD2.toString());
                                                    timeStamp2 = j16 > jLongValue4 ? (event4.getTimeStamp() - j16) + j35 : (event4.getTimeStamp() - jLongValue4) + j35;
                                                }
                                                a.b.a.e.l0.p.c(str19, "handleFlip->rotationDuration: " + timeStamp2);
                                                arrayList11 = arrayList13;
                                                str38 = str21;
                                                str18 = str22;
                                                jSONObjectE2 = e(event4.getEventType(), event4.getPackageName(), event4.getClassName(), event4.getTimeStamp(), timeStamp2);
                                                jSONObjectE2.put(str18, i18);
                                                jSONArray4.put(jSONObjectE2);
                                                eventType3 = event4.getEventType();
                                                timeStamp3 = event4.getTimeStamp();
                                                j16 = timeStamp3;
                                                j31 = j19;
                                                aVar2 = aVar7;
                                                eventType2 = eventType3;
                                                j35 = 0;
                                                zB = zBooleanValue2;
                                                if (jSONObjectE2 != null) {
                                                    length3 = jSONObjectE2.toString().length() + i17;
                                                    if (length3 >= 286720) {
                                                        a.b.a.e.l0.p.c(str19, str17 + length3);
                                                        aVar7 = aVar2;
                                                        str16 = str19;
                                                        break;
                                                    }
                                                    i17 = length3;
                                                }
                                                str24 = str15;
                                                aVar7 = aVar2;
                                                str39 = str19;
                                                str28 = str18;
                                                str29 = str17;
                                                j33 = j18;
                                                usageEventsQueryEvents = usageEvents2;
                                                j36 = j16;
                                                j32 = j17;
                                                str37 = str14;
                                                str27 = str;
                                                arrayList14 = arrayList10;
                                                str25 = str13;
                                                str23 = str3;
                                                arrayList15 = arrayList11;
                                                str26 = str2;
                                            } else {
                                                arrayList10 = arrayList14;
                                                arrayList11 = arrayList13;
                                                str38 = str21;
                                                str18 = str20;
                                                i10 = i18;
                                                j16 = j16;
                                                i9 = i10;
                                                jSONObjectN2 = null;
                                                aVar2 = aVar7;
                                                i18 = i9;
                                                zBooleanValue2 = zBooleanValue2;
                                                jSONObjectE2 = jSONObjectN2;
                                                j31 = j19;
                                                eventType2 = i18;
                                                zB = zBooleanValue2;
                                                if (jSONObjectE2 != null) {
                                                    length3 = jSONObjectE2.toString().length() + i17;
                                                    if (length3 >= 286720) {
                                                        a.b.a.e.l0.p.c(str19, str17 + length3);
                                                        aVar7 = aVar2;
                                                        str16 = str19;
                                                        break;
                                                    }
                                                    i17 = length3;
                                                }
                                                str24 = str15;
                                                aVar7 = aVar2;
                                                str39 = str19;
                                                str28 = str18;
                                                str29 = str17;
                                                j33 = j18;
                                                usageEventsQueryEvents = usageEvents2;
                                                j36 = j16;
                                                j32 = j17;
                                                str37 = str14;
                                                str27 = str;
                                                arrayList14 = arrayList10;
                                                str25 = str13;
                                                str23 = str3;
                                                arrayList15 = arrayList11;
                                                str26 = str2;
                                            }
                                        } else {
                                            str28 = str20;
                                            eventType2 = i18;
                                            str24 = str15;
                                            str39 = str19;
                                            str38 = str21;
                                            j31 = j19;
                                            usageEventsQueryEvents = usageEvents2;
                                            j36 = j16;
                                            zB = zBooleanValue2;
                                            str37 = str14;
                                            str26 = str2;
                                            str27 = str;
                                            arrayList15 = arrayList13;
                                            str29 = str17;
                                            j33 = j18;
                                            j32 = j17;
                                            str25 = str13;
                                            str23 = str3;
                                        }
                                    } else if (event4.getEventType() == 42) {
                                        long timeStamp9 = event4.getTimeStamp();
                                        long jK = yVar.k(event4, arrayList14, j32) + j33;
                                        str19 = str39;
                                        j17 = j32;
                                        str21 = str38;
                                        str17 = str29;
                                        str14 = str37;
                                        str2 = str26;
                                        str = str27;
                                        ArrayList arrayList19 = arrayList14;
                                        str13 = str25;
                                        String str40 = str28;
                                        arrayList13 = arrayList12;
                                        str15 = str24;
                                        jSONObjectE2 = e(42, event4.getPackageName(), event4.getClassName(), event4.getTimeStamp(), jK);
                                        jSONArray4.put(jSONObjectE2);
                                        if (usageEvents2.hasNextEvent()) {
                                            UsageEvents.Event event5 = new UsageEvents.Event();
                                            usageEvents3 = usageEvents2;
                                            usageEvents3.getNextEvent(event5);
                                            StringBuilder sbC3 = a.a.a.a.a.c("isScreenOff->type: ");
                                            sbC3.append(event5.getEventType());
                                            sbC3.append(", pkg: ");
                                            sbC3.append(event5.getPackageName());
                                            sbC3.append(", class: ");
                                            sbC3.append(event5.getClassName());
                                            sbC3.append(", time: ");
                                            sbC3.append(event5.getTimeStamp());
                                            a.b.a.e.l0.p.c(str19, sbC3.toString());
                                            if (event5.getEventType() != 16) {
                                                z6 = false;
                                            }
                                            a.b.a.e.l0.p.c(str19, "handleFlip->isScreenOff: " + z6);
                                            if (z6) {
                                                eventType2 = 0;
                                                timeStamp5 = j16;
                                            } else {
                                                timeStamp5 = event4.getTimeStamp();
                                                eventType2 = 38;
                                            }
                                            timeStamp4 = timeStamp5;
                                            usageEvents2 = usageEvents3;
                                            str3 = str23;
                                            j35 = 0;
                                            j19 = timeStamp9;
                                            str20 = str40;
                                            zBooleanValue2 = true;
                                            j18 = 0;
                                            arrayList14 = arrayList19;
                                            arrayList10 = arrayList14;
                                            arrayList11 = arrayList13;
                                            str38 = str21;
                                            aVar2 = aVar7;
                                            j16 = timeStamp4;
                                            zB = zBooleanValue2;
                                            str18 = str20;
                                            j31 = j19;
                                            if (jSONObjectE2 != null) {
                                                length3 = jSONObjectE2.toString().length() + i17;
                                                if (length3 >= 286720) {
                                                    a.b.a.e.l0.p.c(str19, str17 + length3);
                                                    aVar7 = aVar2;
                                                    str16 = str19;
                                                    break;
                                                }
                                                i17 = length3;
                                            }
                                            str24 = str15;
                                            aVar7 = aVar2;
                                            str39 = str19;
                                            str28 = str18;
                                            str29 = str17;
                                            j33 = j18;
                                            usageEventsQueryEvents = usageEvents2;
                                            j36 = j16;
                                            j32 = j17;
                                            str37 = str14;
                                            str27 = str;
                                            arrayList14 = arrayList10;
                                            str25 = str13;
                                            str23 = str3;
                                            arrayList15 = arrayList11;
                                            str26 = str2;
                                        } else {
                                            usageEvents3 = usageEvents2;
                                        }
                                        z6 = true;
                                        a.b.a.e.l0.p.c(str19, "handleFlip->isScreenOff: " + z6);
                                        if (z6) {
                                            timeStamp5 = event4.getTimeStamp();
                                            eventType2 = 38;
                                        } else {
                                            eventType2 = 0;
                                            timeStamp5 = j16;
                                        }
                                        timeStamp4 = timeStamp5;
                                        usageEvents2 = usageEvents3;
                                        str3 = str23;
                                        j35 = 0;
                                        j19 = timeStamp9;
                                        str20 = str40;
                                        zBooleanValue2 = true;
                                        j18 = 0;
                                        arrayList14 = arrayList19;
                                        arrayList10 = arrayList14;
                                        arrayList11 = arrayList13;
                                        str38 = str21;
                                        aVar2 = aVar7;
                                        j16 = timeStamp4;
                                        zB = zBooleanValue2;
                                        str18 = str20;
                                        j31 = j19;
                                        if (jSONObjectE2 != null) {
                                            length3 = jSONObjectE2.toString().length() + i17;
                                            if (length3 >= 286720) {
                                                a.b.a.e.l0.p.c(str19, str17 + length3);
                                                aVar7 = aVar2;
                                                str16 = str19;
                                                break;
                                            }
                                            i17 = length3;
                                        }
                                        str24 = str15;
                                        aVar7 = aVar2;
                                        str39 = str19;
                                        str28 = str18;
                                        str29 = str17;
                                        j33 = j18;
                                        usageEventsQueryEvents = usageEvents2;
                                        j36 = j16;
                                        j32 = j17;
                                        str37 = str14;
                                        str27 = str;
                                        arrayList14 = arrayList10;
                                        str25 = str13;
                                        str23 = str3;
                                        arrayList15 = arrayList11;
                                        str26 = str2;
                                    } else {
                                        arrayList10 = arrayList14;
                                        j17 = j32;
                                        str2 = str26;
                                        String str41 = str23;
                                        str13 = str25;
                                        str14 = str37;
                                        str17 = str29;
                                        String str42 = str28;
                                        str = str27;
                                        str15 = str24;
                                        String str43 = str38;
                                        j18 = j33;
                                        str19 = str39;
                                        if (event4.getEventType() == 43) {
                                            long timeStamp10 = event4.getTimeStamp();
                                            usageEvents2 = usageEvents2;
                                            long jK2 = yVar.k(event4, arrayList10, j21) + j35;
                                            a.b.a.e.l0.p.c(str19, "handleFlip->rotationDuration: " + jK2);
                                            str3 = str41;
                                            j19 = j21;
                                            jSONObjectE2 = e(43, event4.getPackageName(), event4.getClassName(), event4.getTimeStamp(), jK2);
                                            jSONObjectE2.put(str42, i18);
                                            jSONArray4.put(jSONObjectE2);
                                            eventType3 = 38;
                                            zBooleanValue2 = false;
                                            arrayList10 = arrayList10;
                                            arrayList11 = arrayList12;
                                            str38 = str43;
                                            j17 = timeStamp10;
                                            j18 = 0;
                                            str18 = str42;
                                            timeStamp3 = j16;
                                            j16 = timeStamp3;
                                            j31 = j19;
                                            aVar2 = aVar7;
                                            eventType2 = eventType3;
                                            j35 = 0;
                                            zB = zBooleanValue2;
                                            if (jSONObjectE2 != null) {
                                                length3 = jSONObjectE2.toString().length() + i17;
                                                if (length3 >= 286720) {
                                                    a.b.a.e.l0.p.c(str19, str17 + length3);
                                                    aVar7 = aVar2;
                                                    str16 = str19;
                                                    break;
                                                }
                                                i17 = length3;
                                            }
                                            str24 = str15;
                                            aVar7 = aVar2;
                                            str39 = str19;
                                            str28 = str18;
                                            str29 = str17;
                                            j33 = j18;
                                            usageEventsQueryEvents = usageEvents2;
                                            j36 = j16;
                                            j32 = j17;
                                            str37 = str14;
                                            str27 = str;
                                            arrayList14 = arrayList10;
                                            str25 = str13;
                                            str23 = str3;
                                            arrayList15 = arrayList11;
                                            str26 = str2;
                                        } else {
                                            usageEvents2 = usageEvents2;
                                            str3 = str41;
                                            j19 = j21;
                                            arrayList11 = arrayList12;
                                            str38 = str43;
                                            i10 = i18;
                                            str18 = str42;
                                            j16 = j16;
                                            i9 = i10;
                                            jSONObjectN2 = null;
                                            aVar2 = aVar7;
                                            i18 = i9;
                                            zBooleanValue2 = zBooleanValue2;
                                            jSONObjectE2 = jSONObjectN2;
                                            j31 = j19;
                                            eventType2 = i18;
                                            zB = zBooleanValue2;
                                            if (jSONObjectE2 != null) {
                                                length3 = jSONObjectE2.toString().length() + i17;
                                                if (length3 >= 286720) {
                                                    a.b.a.e.l0.p.c(str19, str17 + length3);
                                                    aVar7 = aVar2;
                                                    str16 = str19;
                                                    break;
                                                }
                                                i17 = length3;
                                            }
                                            str24 = str15;
                                            aVar7 = aVar2;
                                            str39 = str19;
                                            str28 = str18;
                                            str29 = str17;
                                            j33 = j18;
                                            usageEventsQueryEvents = usageEvents2;
                                            j36 = j16;
                                            j32 = j17;
                                            str37 = str14;
                                            str27 = str;
                                            arrayList14 = arrayList10;
                                            str25 = str13;
                                            str23 = str3;
                                            arrayList15 = arrayList11;
                                            str26 = str2;
                                        }
                                    }
                                }
                            }
                        } else {
                            jSONObjectN2 = null;
                            arrayList12 = arrayList15;
                        }
                        i18 = i18;
                        j21 = j31;
                        usageEvents2 = usageEvents7;
                        arrayList10 = arrayList14;
                        j17 = j32;
                        str2 = str26;
                        str3 = str23;
                        str13 = str25;
                        str14 = str37;
                        str17 = str29;
                        str18 = str28;
                        str = str27;
                        str15 = str24;
                        j19 = j21;
                        arrayList11 = arrayList12;
                        j18 = j33;
                        aVar2 = aVar7;
                        str19 = str39;
                        jSONObjectE2 = jSONObjectN2;
                        j31 = j19;
                        eventType2 = i18;
                        zB = zBooleanValue2;
                        if (jSONObjectE2 != null) {
                            length3 = jSONObjectE2.toString().length() + i17;
                            if (length3 >= 286720) {
                                a.b.a.e.l0.p.c(str19, str17 + length3);
                                aVar7 = aVar2;
                                str16 = str19;
                                break;
                            }
                            i17 = length3;
                        }
                        str24 = str15;
                        aVar7 = aVar2;
                        str39 = str19;
                        str28 = str18;
                        str29 = str17;
                        j33 = j18;
                        usageEventsQueryEvents = usageEvents2;
                        j36 = j16;
                        j32 = j17;
                        str37 = str14;
                        str27 = str;
                        arrayList14 = arrayList10;
                        str25 = str13;
                        str23 = str3;
                        arrayList15 = arrayList11;
                        str26 = str2;
                    }
                }
                a.b.a.e.l0.u.a();
                a.b.a.e.l0.u.k("last_fold_state", zB);
                a.b.a.e.l0.u.a();
                a.b.a.e.l0.u.l("last_unfold_time", j17);
                a.b.a.e.l0.u.a();
                a.b.a.e.l0.u.l("last_fold_time", j31);
                a.b.a.e.l0.u.a();
                a.b.a.e.l0.u.l("flip_last_rotate_time", j16);
                a.b.a.e.l0.u.a();
                a.b.a.e.l0.u.l("flip_last_inter_duration", j18);
                StringBuilder sb9 = new StringBuilder();
                sb9.append(eventType2);
                str4 = str13;
                sb9.append(str4);
                sb9.append(j35);
                String string4 = sb9.toString();
                a.b.a.e.l0.u.a();
                a.b.a.e.l0.u.m(str14, string4);
                c2 = 0;
                str5 = str15;
                arrayList = arrayList11;
                aVar = aVar7;
                str6 = str16;
                arrayList2 = arrayList10;
                jSONArray = jSONArray4;
            }
            while (r3.hasNext()) {
                jSONObjectD = yVar.d(list);
                if (jSONObjectD != null) {
                    jSONArrayP.put(jSONObjectD);
                }
                if (a.b.a.e.l0.p.f602a) {
                    a.b.a.e.l0.p.c(str6, "--------------------------------------------------");
                    while (r4.hasNext()) {
                        StringBuilder sb10 = new StringBuilder();
                        sb10.append("####");
                        Object[] objArr5 = new Object[4];
                        objArr5[c2] = bVar.f463a;
                        objArr5[1] = bVar.f464b;
                        objArr5[2] = Long.valueOf(bVar.f466d);
                        objArr5[3] = Integer.valueOf(bVar.f465c);
                        sb10.append(String.format("package: %s class: %s timestamp: %d, event: %d", objArr5));
                        a.b.a.e.l0.p.c(str6, sb10.toString());
                    }
                    StringBuilder sb11 = new StringBuilder();
                    sb11.append("compact result: ");
                    if (jSONObjectD == null) {
                        string2 = "not contains";
                    } else {
                        string2 = jSONObjectD.toString();
                    }
                    sb11.append(string2);
                    a.b.a.e.l0.p.c(str6, sb11.toString());
                }
            }
        } catch (JSONException e3) {
            a.b.a.e.l0.p.h(str6, "appendHybridUsageEvent error ", e3);
        }
        jSONArrayP = jSONArray;
        if (arrayList2.size() > 0) {
            size = arrayList2.size();
            if (size > 2) {
                i8 = size - 2;
            } else {
                i8 = 0;
            }
            sb = new StringBuilder();
            while (i8 < size) {
                Pair pair3 = (Pair) arrayList2.get(i8);
                sb.append(((String) pair3.first) + str4 + pair3.second);
                if (i8 != size - 1) {
                    sb.append("|");
                }
                i8++;
            }
            string = sb.toString();
        } else {
            string = str5;
        }
        if (!TextUtils.isEmpty(string)) {
            a.b.a.e.l0.p.c(str6, "assemble usage events after->last not assembled event : " + string);
        }
        a.b.a.e.l0.u.m(str3, string);
        if (aVar.f461b == 10001) {
            str12 = aVar.f460a + str4 + aVar.f462c;
        } else {
            str12 = str5;
        }
        a.b.a.e.l0.u.m(str, str12);
        a.b.a.e.l0.p.c(str6, "saved not assembled input event: " + str12);
        a.b.a.e.l0.p.c(str6, "begin append hybrid usage event");
        arrayList7 = new ArrayList();
        List<a.b.a.e.f0.s.a> listE3 = a.b.a.e.f0.s.b().e(j, j2);
        StringBuilder sbC4 = a.a.a.a.a.c("hybridUsages:");
        arrayList8 = (ArrayList) listE3;
        sbC4.append(arrayList8.size());
        a.b.a.e.l0.p.c(str6, sbC4.toString());
        if (arrayList8.size() > 0) {
            while (it.hasNext()) {
                a.b.a.e.f0.s.a aVar8 = (a.b.a.e.f0.s.a) it.next();
                Object[] objArr6 = new Object[3];
                objArr6[c2] = aVar8.f372a;
                objArr6[1] = aVar8.f373b;
                objArr6[2] = Long.valueOf(aVar8.f374c);
                a.b.a.e.l0.p.c(str6, String.format("----package: %s class: %s timestamp: %d", objArr6));
                arrayList7.add(new b(aVar8.f372a, aVar8.f373b, 3, aVar8.f374c));
            }
        }
        while (r4.hasNext()) {
            arrayList7.add(new b(event2.getPackageName(), event2.getClassName(), event2.getEventType(), event2.getTimeStamp()));
        }
        a.b.a.e.e.b();
        arrayList9 = new ArrayList();
        strH = a.b.a.e.l0.u.h("last_unpaired_hybrid_event", str5);
        if (!TextUtils.isEmpty(strH)) {
            strArrSplit = strH.split(str2);
            if (strArrSplit.length <= 3) {
                while (i7 < r6) {
                    strArrSplit2 = str34.split(str4);
                    if (strArrSplit2.length == 4) {
                        arrayList9.add(new b(strArrSplit2[c2], strArrSplit2[1], Integer.parseInt(strArrSplit2[3]), Long.parseLong(strArrSplit2[2])));
                    }
                }
            }
        }
        arrayList7.addAll(arrayList9);
        Collections.sort(arrayList7, new x(yVar));
        yVar.g = jSONArrayP.length();
        yVar.v(jSONArrayP);
        return jSONArrayP;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final JSONArray v(JSONArray jSONArray) throws JSONException {
        if (h) {
            h = false;
            String strH = a.b.a.e.l0.u.h("last_not_ended_event_array", null);
            if (strH != null) {
                JSONArray jSONArray2 = new JSONArray(strH);
                int length = jSONArray2.length();
                for (int i2 = 0; i2 < length; i2++) {
                    JSONObject jSONObject = jSONArray2.getJSONObject(i2);
                    i.put(jSONObject.getString("pkg"), Long.valueOf(jSONObject.getLong("total_duration")));
                }
            }
            StringBuilder sbC = a.a.a.a.a.c("tag->sStartedApp: ");
            sbC.append(i.toString());
            a.b.a.e.l0.p.c("UsageEvent", sbC.toString());
        }
        int length2 = jSONArray.length();
        int i3 = 0;
        while (true) {
            if (i3 >= length2) {
                break;
            }
            JSONObject jSONObject2 = (JSONObject) jSONArray.get(i3);
            int i4 = jSONObject2.getInt("event");
            if (i4 == 1 || i4 == 2) {
                String string = jSONObject2.getString("pkg");
                if (i4 == 1 && !i.containsKey(string)) {
                    jSONObject2.put("stat", "app_start");
                    i.put(string, 0L);
                } else if (i4 == 2) {
                    i.put(string, Long.valueOf(h(string) + jSONObject2.getLong("duration")));
                    int i5 = i3 + 1;
                    if (i5 != jSONArray.length() ? true ^ jSONArray.getJSONObject(i3).getString("pkg").equals(jSONArray.getJSONObject(i5).getString("pkg")) : true) {
                        long jH = h(string);
                        jSONObject2.put("stat", "app_end");
                        jSONObject2.put("app_duration", jH);
                        i.remove(string);
                    }
                }
                if (i3 + 1 == length2) {
                    JSONArray jSONArray3 = new JSONArray();
                    for (String str : i.keySet()) {
                        long jLongValue = i.get(str).longValue();
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("pkg", str);
                        jSONObject3.put("total_duration", jLongValue);
                        jSONArray3.put(jSONObject3);
                    }
                    a.b.a.e.l0.u.m("last_not_ended_event_array", jSONArray3.toString());
                }
            }
            i3++;
        }
        if (a.b.a.c.v.h.f()) {
            String strJ = a.b.a.e.l0.r.j();
            for (int i6 = length2 - 1; i6 >= 0; i6--) {
                JSONObject jSONObject4 = (JSONObject) jSONArray.get(i6);
                String strOptString = jSONObject4.optString("pkg");
                String strOptString2 = jSONObject4.optString("class");
                boolean zC = w.b.f459a.c(strJ, strOptString, strOptString2);
                a.b.a.e.l0.p.c("UsageEvent", "pkg: " + strOptString + ", className: " + strOptString2 + ", shouldCollect: " + zC);
                if (!zC) {
                    if (jSONObject4.has("class") && !jSONObject4.has("stat")) {
                        jSONArray.remove(i6);
                    }
                    jSONObject4.remove("class");
                    jSONObject4.remove("stat");
                }
            }
        }
        if (a.b.a.e.l0.p.f602a) {
            StringBuilder sbC2 = a.a.a.a.a.c("------------ after tagged ----------sequence size: ");
            sbC2.append(jSONArray.length());
            a.b.a.e.l0.p.c("UsageEvent", sbC2.toString());
            for (int i7 = 0; i7 < jSONArray.length(); i7++) {
                try {
                    a.b.a.e.l0.p.c("UsageEvent", jSONArray.get(i7).toString());
                } catch (Exception e) {
                    a.b.a.e.l0.p.h("UsageEvent", "??", e);
                }
            }
        }
        return jSONArray;
    }
}
