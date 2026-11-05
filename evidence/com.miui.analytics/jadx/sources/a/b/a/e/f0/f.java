package a.b.a.e.f0;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteBlobTooBigException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import com.miui.analytics.ICore;
import com.miui.analytics.log.internal.Level;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class f implements a.b.a.e.i0.b.InterfaceC0012b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int f327b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static volatile f f328c;
    public static d f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f330a = new c(a.b.a.e.e.b());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final AtomicLong f329d = new AtomicLong(0);
    public static final AtomicLong e = new AtomicLong(0);
    public static final long g = a.b.a.e.l0.c.t();

    public class a implements Runnable {
        public a() {
        }

        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
        /* JADX WARN: Code duplicated, block: B:46:0x0170  */
        /* JADX WARN: Code duplicated, block: B:47:0x0172  */
        @Override // java.lang.Runnable
        public void run() {
            Cursor cursor;
            SQLiteDatabase sQLiteDatabaseH;
            Cursor cursorQuery;
            String str;
            String str2;
            AtomicLong atomicLong;
            boolean z;
            if (f.this.f330a != null) {
                AtomicLong atomicLong2 = f.e;
                if (atomicLong2.get() > 10485760) {
                    synchronized (f.this.f330a) {
                        try {
                            try {
                                try {
                                    sQLiteDatabaseH = f.this.f330a.H();
                                    try {
                                        long jF = a.b.a.e.l0.v.f(7);
                                        long jF2 = a.b.a.e.l0.v.f(14);
                                        String[] strArr = {Long.toString(jF)};
                                        cursorQuery = sQLiteDatabaseH.query("events", new String[]{"event_name", "type", "timestamp"}, "timestamp < ? ", strArr, null, null, "timestamp ASC");
                                        try {
                                            int count = cursorQuery.getCount();
                                            a.b.a.e.l0.p.c("EventManager", "*** obsolete time: " + jF + ", obsoleteTimeForActive: " + jF2 + ". all: " + count);
                                            if (count != 0) {
                                                int[] iArrA = f.a(f.this, cursorQuery, jF2);
                                                a.b.a.e.l0.p.c("EventManager", "activeArr: " + Arrays.toString(iArrA));
                                                if (iArrA != null && iArrA.length == 3) {
                                                    if (count == iArrA[0]) {
                                                        if (iArrA[1] > 0) {
                                                            f.c(f.this, sQLiteDatabaseH, "timestamp < ? ", jF2, iArrA[1], iArrA[2]);
                                                        } else {
                                                            str = "EventManager";
                                                            str2 = "all events are active and not obsolete, skip delete";
                                                        }
                                                    } else if (count > iArrA[0]) {
                                                        int iDelete = sQLiteDatabaseH.delete("events", "event_name != 'onetrack_active' and timestamp < ? ", strArr);
                                                        a.b.a.e.l0.p.c("EventManager", "there are " + (count - iArrA[0]) + " obsolete non-active events, delete return count: " + iDelete);
                                                        if (iDelete > 0) {
                                                            f.f329d.addAndGet(-iDelete);
                                                            atomicLong2.set(f.this.o());
                                                            f.h();
                                                        }
                                                        if (iArrA[1] > 0) {
                                                            f.c(f.this, sQLiteDatabaseH, "timestamp < ? ", jF2, iArrA[1], iArrA[2]);
                                                        } else {
                                                            str = "EventManager";
                                                            str2 = "there are no obsolete active events, skip delete";
                                                        }
                                                    } else {
                                                        a.b.a.e.l0.p.s("EventManager", "something wrong with recordObsoleteEventsToDB, arr[0] is larger than all");
                                                    }
                                                    atomicLong = f.f329d;
                                                    if (atomicLong.get() == 0) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    a.b.a.e.e0.k.k(z);
                                                    a.b.a.e.l0.p.c("EventManager", "after delete obsolete record remains=" + atomicLong.get());
                                                    a.b.a.a.a.c.a.i(cursorQuery);
                                                    f.this.f330a.J();
                                                    return;
                                                }
                                                a.b.a.e.l0.p.s("EventManager", "something wrong with recordObsoleteEventsToDB, activeArr is null or size is not 3");
                                                a.b.a.a.a.c.a.i(cursorQuery);
                                                f.this.f330a.J();
                                                return;
                                            }
                                            str = "EventManager";
                                            str2 = "*** no obsolete item in database.";
                                            a.b.a.e.l0.p.c(str, str2);
                                            atomicLong = f.f329d;
                                            if (atomicLong.get() == 0) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            a.b.a.e.e0.k.k(z);
                                            a.b.a.e.l0.p.c("EventManager", "after delete obsolete record remains=" + atomicLong.get());
                                            a.b.a.a.a.c.a.i(cursorQuery);
                                        } catch (Exception e) {
                                            e = e;
                                            a.b.a.e.l0.p.h("EventManager", "remove obsolete events error, e: ", e);
                                            a.b.a.a.a.c.a.i(cursorQuery);
                                            if (sQLiteDatabaseH != null) {
                                            }
                                            return;
                                        }
                                    } catch (Exception e2) {
                                        e = e2;
                                        cursorQuery = null;
                                    } catch (Throwable th) {
                                        th = th;
                                        cursor = null;
                                        a.b.a.a.a.c.a.i(cursor);
                                        if (sQLiteDatabaseH != null) {
                                            f.this.f330a.J();
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                }
                            } catch (Exception e3) {
                                e = e3;
                                cursorQuery = null;
                                sQLiteDatabaseH = null;
                            } catch (Throwable th3) {
                                th = th3;
                                cursor = null;
                                sQLiteDatabaseH = null;
                            }
                            f.this.f330a.J();
                            return;
                        } catch (Throwable th4) {
                            throw th4;
                        }
                    }
                }
            }
            StringBuilder sbC = a.a.a.a.a.c("dbFileSize:");
            sbC.append(f.e);
            sbC.append(",<10M or DBHelper is null, return removeObsolete");
            a.b.a.e.l0.p.c("EventManager", sbC.toString());
        }
    }

    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f332a;

        public b(f fVar, boolean z) {
            this.f332a = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (a.b.a.a.a.c.a.m0()) {
                StringBuilder sbC = a.a.a.a.a.c("screen recevier, act :");
                sbC.append(this.f332a);
                a.b.a.e.l0.p.c("EventManager", sbC.toString());
                if (this.f332a) {
                    z.e().d(0);
                } else {
                    z.e().d(2);
                    z.e().d(4);
                }
                a.b.a.e.c0.f.b.c().b();
                a.b.a.g.e.a.c().b();
                o.d().b(0, false);
            }
        }
    }

    public static class c extends a.b.a.c.q.d {
        public c(Context context) {
            super(context, "onetrack", 2, 30000L, 300000L);
            try {
                if (Build.VERSION.SDK_INT >= 28) {
                    setOpenParams(a.b.a.e.l0.b.f562a);
                }
            } catch (Throwable th) {
                StringBuilder sbC = a.a.a.a.a.c("EventManager  setOpenParams");
                sbC.append(th.getMessage());
                Log.w("EventManager", sbC.toString());
            }
        }

        @Override // a.b.a.c.q.d, android.database.sqlite.SQLiteOpenHelper
        public void onConfigure(SQLiteDatabase sQLiteDatabase) {
            super.onConfigure(sQLiteDatabase);
            try {
                sQLiteDatabase.execSQL("PRAGMA cache_size = -50;");
            } catch (Throwable th) {
                StringBuilder sbC = a.a.a.a.a.c("DB onConfigure failed: ");
                sbC.append(th.getMessage());
                a.b.a.e.l0.p.s("EventManager", sbC.toString());
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE events (_id INTEGER PRIMARY KEY AUTOINCREMENT,appid TEXT,package TEXT,event_name TEXT,priority INTEGER,data BLOB,timestamp INTEGER,type INTEGER DEFAULT 0)");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            try {
                sQLiteDatabase.execSQL("ALTER TABLE events ADD COLUMN type INTEGER DEFAULT 0");
            } catch (Throwable th) {
                StringBuilder sbC = a.a.a.a.a.c("onUpgrade error: ");
                sbC.append(th.getMessage());
                a.b.a.e.l0.p.s("EventManager", sbC.toString());
            }
        }
    }

    public static class d extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<f> f333a;

        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
        public d(Looper looper, f fVar, a aVar) {
            super(looper);
            this.f333a = new WeakReference<>(fVar);
        }

        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            f fVar = this.f333a.get();
            if (fVar == null) {
                removeMessages(1);
                removeMessages(2);
                return;
            }
            int i = message.what;
            if (i == 1) {
                e.a(new g(fVar));
            } else if (i == 2) {
                fVar.D();
            }
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public f() {
        f = new d(Looper.getMainLooper(), this, null);
        long jT = t();
        f329d.set(jT);
        a.b.a.e.l0.p.c("EventManager", "EventManager init, There are " + jT + " events in DB");
        try {
            e.set(o());
            h();
        } catch (Exception e2) {
            a.b.a.e.l0.p.h("EventManager", "checkDatabaseSize error", e2);
        }
        D();
        a.b.a.e.i0.b.c().b(this);
    }

    public static String A(byte[] bArr) {
        byte[] bArrB = a.b.a.e.g0.a.b(bArr, a.b.a.e.g0.c.d().getBytes());
        return (bArrB == null || bArrB.length == 0) ? "" : new String(bArrB);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static int[] a(f fVar, Cursor cursor, long j) {
        Objects.requireNonNull(fVar);
        try {
            int columnIndex = cursor.getColumnIndex("event_name");
            int columnIndex2 = cursor.getColumnIndex("type");
            int columnIndex3 = cursor.getColumnIndex("timestamp");
            ArrayMap arrayMap = new ArrayMap();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (cursor.moveToNext()) {
                try {
                    String string = cursor.getString(columnIndex);
                    int i4 = columnIndex2 >= 0 ? cursor.getInt(columnIndex2) : 0;
                    long j2 = cursor.getLong(columnIndex3);
                    if (i4 == 1) {
                        Integer num = (Integer) arrayMap.get("onetrack_channel_ad");
                        arrayMap.put("onetrack_channel_ad", Integer.valueOf((num == null ? 0 : num.intValue()) + 1));
                    }
                    if (a.b.a.g.f.b.f892b.contains(string)) {
                        String strM = "";
                        if ("onetrack_active".equals(string)) {
                            i++;
                            if (j2 < j) {
                                if (i4 == 3) {
                                    i3 = 1;
                                }
                                i2++;
                                strM = a.b.a.g.f.a.m(string);
                            }
                        } else if ("onetrack_app_stat".equals(string)) {
                            String strH = a.b.a.a.a.c.a.H(i4);
                            if (!TextUtils.isEmpty(strH)) {
                                strM = a.b.a.g.f.a.l(strH);
                            }
                        } else {
                            strM = a.b.a.g.f.a.m(string);
                        }
                        if (!TextUtils.isEmpty(strM)) {
                            Integer num2 = (Integer) arrayMap.get(strM);
                            arrayMap.put(strM, Integer.valueOf((num2 == null ? 0 : num2.intValue()) + 1));
                        }
                    }
                } catch (Exception e2) {
                    a.b.a.e.l0.p.s("EventManager", "record a event failed with " + e2.getMessage());
                }
            }
            if (arrayMap.size() > 0) {
                for (Map.Entry entry : arrayMap.entrySet()) {
                    String str = (String) entry.getKey();
                    Integer num3 = (Integer) entry.getValue();
                    a.b.a.e.l0.p.c("EventManager", "recordObsoleteEventsToDB, dbEventName: " + str + ", count: " + num3);
                    a.b.a.g.c.c.f(false, str, num3.intValue(), "delete_obsolete");
                }
            } else {
                a.b.a.e.l0.p.c("EventManager", "recordObsoleteEventsToDB, monitorEventsCounts is empty, skip it");
            }
            return new int[]{i, i2, i3};
        } catch (Exception e3) {
            a.a.a.a.a.g(e3, a.a.a.a.a.c("record obsolete events failed with "), "EventManager");
            return null;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void c(f fVar, SQLiteDatabase sQLiteDatabase, String str, long j, int i, int i2) {
        Objects.requireNonNull(fVar);
        int iDelete = sQLiteDatabase.delete("events", "event_name = 'onetrack_active' and " + str, new String[]{Long.toString(j)});
        if (iDelete <= 0) {
            a.b.a.a.a.c.a.n(a.b.a.e.w.i, "EventManager", "there are " + i + " obsolete active events, delete failed ", true);
            return;
        }
        a.b.a.a.a.c.a.n(a.b.a.e.w.i, "EventManager", "there are " + i + " obsolete active events, delete success count: " + iDelete, true);
        f329d.addAndGet((long) (-iDelete));
        e.set(fVar.o());
        h();
        if (i2 == 1) {
            a.b.a.e.l0.u.k("activation_in_db", false);
            a.b.a.e.w.i = false;
        }
    }

    public static void h() {
        long j;
        String str;
        try {
            AtomicLong atomicLong = e;
            long j2 = atomicLong.get();
            long j3 = g;
            if (j2 > 31457280 + j3) {
                j = 120000;
            } else {
                j = atomicLong.get() > j3 ? 300000L : -1L;
            }
            if (j != -1) {
                if (f.hasMessages(1)) {
                    return;
                }
                f.sendEmptyMessageDelayed(1, j);
                str = "check db size,start MESSAGE_CHECK_OVERSIZE";
            } else {
                if (!f.hasMessages(1)) {
                    return;
                }
                f.removeMessages(1);
                str = "check db size,stop MESSAGE_CHECK_OVERSIZE";
            }
            a.b.a.e.l0.p.c("EventManager", str);
        } catch (Exception e2) {
            a.b.a.e.l0.p.h("EventManager", "checkDatabaseSize error", e2);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static int i(a.b.a.e.f fVar, String str, boolean z) {
        try {
            if ("et7".equals(str)) {
                return 1;
            }
            String str2 = fVar.f311c;
            if ("onetrack_active".equals(str2)) {
                return z ? 3 : 2;
            }
            if ("onetrack_app_stat".equals(str2)) {
                try {
                    JSONObject jSONObjectOptJSONObject = fVar.e.optJSONObject("B");
                    return a.b.a.a.a.c.a.g0(jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.optString("action", "") : "");
                } catch (Exception unused) {
                    return 0;
                }
            }
        } catch (Exception e2) {
            StringBuilder sbC = a.a.a.a.a.c("computeEventType error: ");
            sbC.append(e2.getMessage());
            a.b.a.e.l0.p.s("EventManager", sbC.toString());
        }
        return 0;
    }

    public static String j(byte[] bArr) {
        byte[] bArrB = a.b.a.e.g0.a.b(bArr, a.b.a.e.g0.d.a(a.b.a.e.g0.c.f429d, true).getBytes());
        if (bArrB == null || bArrB.length == 0) {
            bArrB = a.b.a.e.g0.a.b(bArr, a.b.a.e.g0.c.d().getBytes());
        }
        return (bArrB == null || bArrB.length == 0) ? "" : new String(bArrB);
    }

    public static byte[] m(String str) {
        return a.b.a.e.g0.a.e(str.getBytes(), a.b.a.e.g0.d.a(a.b.a.e.g0.c.f429d, true).getBytes());
    }

    public static f p() {
        if (f328c == null) {
            a.b.a.e.e.b();
            v();
        }
        return f328c;
    }

    public static void v() {
        if (f328c == null) {
            synchronized (f.class) {
                if (f328c == null) {
                    f328c = new f();
                }
            }
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final void B(a.b.a.e.f fVar, boolean z) {
        if (!TextUtils.equals("001", fVar.f309a)) {
            StringBuilder sbC = a.a.a.a.a.c("addEvent: ");
            sbC.append(fVar.f311c);
            sbC.append(", appId: ");
            sbC.append(fVar.f309a);
            a.b.a.e.l0.p.c("EventManager", sbC.toString());
            return;
        }
        if (!"onetrack_active".equals(fVar.f311c)) {
            String str = fVar.f311c;
            a.b.a.e.l0.p.f("EventManager", "addEvent: " + str + ", data:" + fVar.e.toString(), a.b.a.e.l0.p.m(str));
            return;
        }
        StringBuilder sbC2 = a.a.a.a.a.c("addEvent: onetrack_active, data:");
        sbC2.append(fVar.e.toString());
        String string = sbC2.toString();
        boolean zM = a.b.a.e.l0.p.m(fVar.f311c);
        Level level = Level.DEBUG;
        if (zM) {
            a.b.a.e.l0.p.c("EventManager", string);
        } else {
            a.b.a.d.b.d dVarB = a.b.a.d.b.b(a.b.a.e.e.b());
            if (dVarB != null) {
                dVarB.a(level, "##XLogger##, TAG: ", a.a.a.a.a.b("EventManager", ": ", string), null);
            }
        }
        a.b.a.d.b.d dVarV = a.b.a.a.a.c.a.V(z);
        if (dVarV != null) {
            dVarV.a(level, "##XLogger##, TAG: ", a.a.a.a.a.b("EventManager", ": ", string), null);
        }
    }

    public final void C(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return;
        }
        a.b.a.e.l0.p.c("EventManager", String.format(str3, str, str2));
    }

    public void D() {
        e.b(new a(), 1000L);
        d dVar = f;
        if (dVar == null || dVar.hasMessages(2)) {
            return;
        }
        f.sendEmptyMessageDelayed(2, 86400000L);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final boolean E(a.b.a.e.f fVar) {
        boolean z;
        String strG;
        JSONArray jSONArrayD;
        StringBuilder sb;
        StringBuilder sbC;
        JSONObject jSONObject = fVar.e;
        if (jSONObject == null) {
            sbC = a.a.a.a.a.c("AppId is ");
            sbC.append(fVar.f309a);
            sbC.append(", this event has no data, skip it.");
        } else {
            String str = fVar.f309a;
            if (!TextUtils.isEmpty(str) ? a.b.a.e.e0.g.c.f287a.g(str, "wl") : false) {
                a.b.a.e.l0.p.c("EventManager", fVar.f309a + ",This appId and device in whiteList");
                return true;
            }
            String str2 = fVar.f309a;
            if (!TextUtils.isEmpty(str2) ? a.b.a.e.e0.g.c.f287a.g(str2, "disable_log") : false) {
                sbC = a.a.a.a.a.c("This appId ");
                sbC.append(fVar.f309a);
                sbC.append(" disabled tracking data, skip it.");
            } else {
                String strG2 = "";
                if (a.b.a.e.e0.k.j(fVar.f309a)) {
                    try {
                        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("H");
                        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("B");
                        String strOptString = jSONObjectOptJSONObject2 == null ? "" : jSONObjectOptJSONObject2.optString("tip");
                        JSONObject jSONObjectI = a.b.a.e.e0.g.c.f287a.i(fVar.f309a, fVar.f311c);
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("eventConfig: ");
                        sb2.append(jSONObjectI != null ? jSONObjectI.toString() : "");
                        a.b.a.e.l0.p.c("EventManager", sb2.toString());
                        if (!TextUtils.isEmpty(strOptString) && (jSONArrayD = a.b.a.e.e0.k.d(jSONObjectI, "nc", null)) != null) {
                            int i = 0;
                            while (true) {
                                if (i >= jSONArrayD.length()) {
                                    z = false;
                                    strG = "";
                                    break;
                                }
                                JSONObject jSONObject2 = jSONArrayD.getJSONObject(i);
                                String strG3 = a.b.a.e.e0.k.g(jSONObject2, "needId", "");
                                C(strG3, strOptString, "needIds: %s, tip: %s");
                                if (y(strOptString, strG3)) {
                                    if (!w(jSONObject2, fVar)) {
                                        strG = a.b.a.e.e0.k.g(jSONObject2, "bannedParams", "");
                                        z = true;
                                        break;
                                    }
                                    return false;
                                }
                                i++;
                            }
                        } else {
                            z = false;
                            strG = "";
                            break;
                        }
                        if (!z && jSONObjectI != null) {
                            if (w(jSONObjectI, fVar)) {
                                return false;
                            }
                            strG = a.b.a.e.e0.k.g(jSONObjectI, "bannedParams", "");
                        }
                        String strD = a.b.a.e.e0.g.c.f287a.d(fVar.f309a);
                        C(strG, strD, "bannedParamsForEvent: %s, bannedParamsForApp: %s");
                        Set<String> setV0 = a.b.a.a.a.c.a.v0(strD, strG, ",");
                        n(jSONObjectOptJSONObject, setV0);
                        n(jSONObjectOptJSONObject2, setV0);
                        return true;
                    } catch (Exception e2) {
                        a.a.a.a.a.g(e2, a.a.a.a.a.c("shouldUploadByCloudControl error："), "EventManager");
                        return false;
                    }
                }
                StringBuilder sbC2 = a.a.a.a.a.c("This appId ");
                sbC2.append(fVar.f309a);
                sbC2.append(" is oldUploadByCloudControl");
                a.b.a.e.l0.p.c("EventManager", sbC2.toString());
                try {
                    JSONObject jSONObject3 = fVar.e;
                    JSONObject jSONObjectOptJSONObject3 = jSONObject3.optJSONObject("H");
                    JSONObject jSONObjectOptJSONObject4 = jSONObject3.optJSONObject("B");
                    a.b.a.e.e0.g gVar = a.b.a.e.e0.g.c.f287a;
                    JSONObject jSONObjectI2 = gVar.i(fVar.f309a, fVar.f311c);
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("eventConfig: ");
                    sb3.append(jSONObjectI2 != null ? jSONObjectI2.toString() : "");
                    a.b.a.e.l0.p.c("EventManager", sb3.toString());
                    String strG4 = a.b.a.e.e0.k.g(jSONObjectI2, "needIds", "");
                    String strOptString2 = jSONObjectOptJSONObject4 == null ? "" : jSONObjectOptJSONObject4.optString("tip");
                    C(strG4, strOptString2, "needIds: %s, tip: %s");
                    if (x(strOptString2, strG4)) {
                        if (a.b.a.e.e0.k.c(jSONObjectI2, "disable_log", false)) {
                            sb = new StringBuilder();
                            sb.append("AppId is ");
                            sb.append(fVar.f309a);
                            sb.append(", this event ");
                            sb.append(fVar.f311c);
                            sb.append(" disabled tracking data, skip it.");
                        } else if (F(jSONObjectI2, fVar)) {
                            strG2 = a.b.a.e.e0.k.g(jSONObjectI2, "bannedParams", "");
                        } else {
                            sb = new StringBuilder();
                            sb.append("AppId is ");
                            sb.append(fVar.f309a);
                            sb.append(", this event ");
                            sb.append(fVar.f311c);
                            sb.append(" shouldUploadBySampling disabled tracking data, skip it.");
                        }
                        a.b.a.e.l0.p.c("EventManager", sb.toString());
                        return false;
                    }
                    String strD2 = gVar.d(fVar.f309a);
                    C(strG2, strD2, "bannedParamsForEvent: %s, bannedParamsForApp: %s");
                    Set<String> setV1 = a.b.a.a.a.c.a.v0(strD2, strG2, ",");
                    n(jSONObjectOptJSONObject3, setV1);
                    n(jSONObjectOptJSONObject4, setV1);
                    return true;
                } catch (Exception e3) {
                    a.a.a.a.a.g(e3, a.a.a.a.a.c("shouldUploadByCloudControl error："), "EventManager");
                }
            }
        }
        a.b.a.e.l0.p.c("EventManager", sbC.toString());
        return false;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final boolean F(JSONObject jSONObject, a.b.a.e.f fVar) {
        long jF = a.b.a.e.e0.k.f(jSONObject, fVar.f309a, fVar.f311c);
        Context context = a.b.a.e.e.f261a;
        long jAbs = Math.abs((a.b.a.e.l0.r.q() ? a.b.a.e.l0.c.g(context) : a.b.a.e.l0.m.a(context)).hashCode()) % 100;
        boolean z = jF > jAbs;
        StringBuilder sbC = a.a.a.a.a.c("shouldUploadBySampling ");
        sbC.append(fVar.f311c);
        sbC.append(",  shouldUpload=");
        sbC.append(z);
        sbC.append(", sample=");
        sbC.append(jF);
        sbC.append(", val=");
        sbC.append(jAbs);
        a.b.a.e.l0.p.c("EventManager", sbC.toString());
        return z;
    }

    public void G(JSONObject jSONObject, long j) {
        synchronized (this.f330a) {
            try {
                byte[] bArrM = m(jSONObject.toString());
                SQLiteDatabase sQLiteDatabaseH = this.f330a.H();
                try {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("data", bArrM);
                    sQLiteDatabaseH.update("events", contentValues, "_id = ? ", new String[]{Long.toString(j)});
                    a.b.a.e.l0.p.c("EventManager", "update data finish. id: " + j);
                    this.f330a.J();
                } catch (Throwable th) {
                    if (sQLiteDatabaseH != null) {
                        this.f330a.J();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                a.b.a.e.l0.p.g("EventManager", "updateDataInDB error : " + e2.getMessage());
            }
        }
    }

    @Override // a.b.a.e.i0.b.InterfaceC0012b
    public void b(boolean z) {
        e.a(new b(this, z));
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public boolean d(a.b.a.e.f fVar, String str, boolean z) {
        Exception e2;
        boolean zE;
        String str2;
        List<a.b.a.e.c0.e.a> list;
        String str3;
        try {
            if (!"onetrack_active".equals(fVar.f311c) && !E(fVar)) {
                if (z(fVar, str)) {
                    if (!a.b.a.g.f.a.n(str)) {
                        str.hashCode();
                        byte b2 = -1;
                        switch (str.hashCode()) {
                            case 100706:
                                if (str.equals("et1")) {
                                    b2 = 0;
                                }
                                break;
                            case 100707:
                                if (str.equals("et2")) {
                                    b2 = 1;
                                }
                                break;
                            case 100709:
                                if (str.equals("et4")) {
                                    b2 = 2;
                                }
                                break;
                            case 100710:
                                if (str.equals("et5")) {
                                    b2 = 3;
                                }
                                break;
                            case 100711:
                                if (str.equals("et6")) {
                                    b2 = 4;
                                }
                                break;
                            case 100712:
                                if (str.equals("et7")) {
                                    b2 = 5;
                                }
                                break;
                        }
                        switch (b2) {
                            case 0:
                            case 2:
                            case ICore.Stub.TRANSACTION_trackEvent /* 5 */:
                                str3 = "r6";
                                break;
                            case 1:
                            case 4:
                                str3 = "r7";
                                break;
                            case 3:
                                str3 = "r5";
                                break;
                            default:
                                str3 = "";
                                break;
                        }
                    } else {
                        str3 = "r12";
                    }
                    a.b.a.g.c.c.f(false, str, 1, str3);
                }
                return true;
            }
            zE = e(fVar, str, z);
            try {
                B(fVar, z);
                if (!TextUtils.equals("001", fVar.f309a) || !"onetrack_bug_report".equals(fVar.f311c)) {
                    int i = fVar.f312d;
                    if (zE && (fVar instanceof a.b.a.e.c0.e.b) && (list = ((a.b.a.e.c0.e.b) fVar).g) != null && !list.isEmpty()) {
                        a.b.a.e.c0.d.i().f(list);
                    }
                    a.b.a.e.c0.f.b.c().b();
                    z.e().d(i);
                    if (TextUtils.equals("001", fVar.f309a) && "onetrack_active".equals(fVar.f311c)) {
                        o.d().b(i, z);
                    }
                }
            } catch (Exception e3) {
                e2 = e3;
                a.b.a.e.l0.p.i(fVar.f311c, "EventManager", "EventManager.addEvent exception: ", e2, z);
                if (zE || !"onetrack_exception".equals(fVar.f311c)) {
                    a.b.a.a.a.c.a.y0("sa", "s1", e2);
                } else {
                    z zVarE = z.e();
                    Objects.requireNonNull(zVarE);
                    try {
                        if (!a.b.a.a.a.c.a.m0()) {
                            str2 = "uploadCaughtExceptionEvent, 不用处理消息, 当前未连接网络";
                        } else if (a.b.a.e.e0.k.a(false)) {
                            ConcurrentHashMap<String, String> concurrentHashMap = a.b.a.e.y.f663c;
                            String strE = a.b.a.e.y.b.f667a.e();
                            JSONArray jSONArray = new JSONArray();
                            jSONArray.put(fVar.e);
                            String strF = zVarE.f(jSONArray);
                            byte[] bArrC = zVarE.c(a.b.a.a.a.c.a.U0(strF));
                            if (bArrC != null && bArrC.length != 0) {
                                a.b.a.e.l0.p.c("UploaderEngine", "uploadCaughtExceptionEvent->before zip and encrypt, len=" + strF.length() + ", after=" + bArrC.length);
                                String strL = a.b.a.e.i0.c.n().l(strE, bArrC, false);
                                StringBuilder sb = new StringBuilder();
                                sb.append("uploadCaughtExceptionEvent->sendDataToServer response: ");
                                sb.append(strL);
                                a.b.a.e.l0.p.c("UploaderEngine", sb.toString());
                                if (!TextUtils.isEmpty(strL)) {
                                    zVarE.g(strL);
                                }
                            }
                        } else {
                            str2 = "uploadCaughtExceptionEvent, not available, skip it";
                        }
                        a.b.a.e.l0.p.c("UploaderEngine", str2);
                    } catch (Exception e4) {
                        a.b.a.e.l0.p.h("UploaderEngine", "uploadCaughtExceptionEvent->Exception while uploading ", e4);
                    }
                }
            }
            return zE;
        } catch (Exception e5) {
            e2 = e5;
            zE = false;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX WARN: Code duplicated, block: B:83:0x01c9 A[Catch: all -> 0x01dd, TRY_ENTER, TRY_LEAVE, TryCatch #9 {all -> 0x01dd, blocks: (B:23:0x00b5, B:24:0x00ba, B:94:0x01de, B:83:0x01c9, B:91:0x01d7, B:92:0x01dc, B:43:0x00f7, B:44:0x00fc, B:81:0x01ad), top: B:121:0x003c, inners: #3 }] */
    public final boolean e(a.b.a.e.f fVar, String str, boolean z) throws Throwable {
        boolean zK;
        boolean z2 = false;
        try {
            AtomicLong atomicLong = e;
            try {
                if (atomicLong.get() > g + 32505856) {
                    a.b.a.e.l0.p.s("EventManager", "Database exceeds the maximum size of 31MB, discard event " + fVar.f311c);
                    h();
                    return false;
                }
                synchronized (this.f330a) {
                    SQLiteDatabase sQLiteDatabase = null;
                    try {
                        try {
                            SQLiteDatabase sQLiteDatabaseH = this.f330a.H();
                            try {
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("appid", fVar.f309a);
                                contentValues.put("package", fVar.f310b);
                                contentValues.put("event_name", fVar.f311c);
                                contentValues.put("priority", Integer.valueOf(fVar.f312d));
                                contentValues.put("timestamp", Long.valueOf(System.currentTimeMillis()));
                                contentValues.put("type", Integer.valueOf(i(fVar, str, z)));
                                byte[] bArrM = m(fVar.e.toString());
                                contentValues.put("data", bArrM);
                                try {
                                    if (!"et7".equals(str)) {
                                        if (bArrM.length > (!"001".equals(fVar.f309a) && "onetrack_bug_report".equals(fVar.f311c) ? 512000 : 307200)) {
                                            a.b.a.e.l0.p.j(fVar.f311c, "EventManager", "Too large blob ***", z);
                                            if (z(fVar, str)) {
                                                a.b.a.g.c.c.f(false, str, 1, a.b.a.g.f.a.g(str));
                                            }
                                            if (sQLiteDatabaseH != null) {
                                                this.f330a.J();
                                            }
                                            return false;
                                        }
                                    } else if (bArrM.length > 512000) {
                                        a.b.a.e.l0.p.g("EventManager", "ad Too large blob ***");
                                        if (z(fVar, str)) {
                                            a.b.a.g.c.c.f(false, str, 1, a.b.a.g.f.a.g(str));
                                        }
                                        if (sQLiteDatabaseH != null) {
                                            this.f330a.J();
                                        }
                                        return false;
                                    }
                                    long jInsertOrThrow = sQLiteDatabaseH.insertOrThrow("events", null, contentValues);
                                    boolean z3 = jInsertOrThrow != -1;
                                    try {
                                        if (z3) {
                                            try {
                                                long jIncrementAndGet = f329d.incrementAndGet();
                                                String str2 = fVar.f311c;
                                                StringBuilder sb = new StringBuilder();
                                                boolean z4 = z3;
                                                sb.append("添加后，DB 中事件个数为 ");
                                                sb.append(jIncrementAndGet);
                                                sb.append(", row=");
                                                sb.append(jInsertOrThrow);
                                                a.b.a.e.l0.p.e(str2, "EventManager", sb.toString(), z);
                                                if (TextUtils.equals("onetrack_active", fVar.f311c) && z) {
                                                    a.b.a.e.l0.u.k("activation_in_db", true);
                                                    a.b.a.e.w.i = true;
                                                }
                                                atomicLong.addAndGet(bArrM.length);
                                                h();
                                                a.b.a.e.e0.k.k(false);
                                                if (z(fVar, str)) {
                                                    a.b.a.g.c.c.f(false, str, 1, "saved_db");
                                                }
                                                zK = z4;
                                            } catch (Throwable th) {
                                                th = th;
                                                boolean z5 = z3;
                                                z2 = z5;
                                                try {
                                                    a.b.a.e.l0.p.g("EventManager", "addEventToDatabase e:" + th.getMessage());
                                                    if (sQLiteDatabaseH != null) {
                                                        this.f330a.J();
                                                    }
                                                    zK = z2;
                                                } catch (Throwable th2) {
                                                    if (sQLiteDatabaseH != null) {
                                                        this.f330a.J();
                                                    }
                                                    throw th2;
                                                }
                                            }
                                        } else {
                                            zK = z.e().k(fVar, z);
                                            try {
                                                if (zK) {
                                                    a.b.a.e.l0.p.e(fVar.f311c, "EventManager", "addEventToDatabase failed but upload success", z);
                                                } else {
                                                    a.b.a.e.l0.p.e(fVar.f311c, "EventManager", "addEventToDatabase failed and upload failed", z);
                                                    if (z(fVar, str)) {
                                                        a.b.a.g.c.c.f(false, str, 1, a.b.a.g.f.a.h(str));
                                                    }
                                                }
                                            } catch (Throwable th3) {
                                                th = th3;
                                                z2 = zK;
                                                a.b.a.e.l0.p.g("EventManager", "addEventToDatabase e:" + th.getMessage());
                                                if (sQLiteDatabaseH != null) {
                                                    this.f330a.J();
                                                }
                                                zK = z2;
                                            }
                                        }
                                        try {
                                            this.f330a.J();
                                        } catch (Throwable th4) {
                                            th = th4;
                                            throw th;
                                        }
                                    } catch (Throwable th5) {
                                        th = th5;
                                    }
                                } catch (Throwable th6) {
                                    th = th6;
                                }
                            } catch (Throwable th7) {
                                th = th7;
                                sQLiteDatabase = sQLiteDatabaseH;
                                sQLiteDatabaseH = sQLiteDatabase;
                                z2 = false;
                                a.b.a.e.l0.p.g("EventManager", "addEventToDatabase e:" + th.getMessage());
                                if (sQLiteDatabaseH != null) {
                                    this.f330a.J();
                                }
                                zK = z2;
                                return zK;
                            }
                        } catch (Throwable th8) {
                            th = th8;
                        }
                        return zK;
                    } catch (Throwable th9) {
                        th = th9;
                        throw th;
                    }
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            z2 = false;
        }
        a.b.a.e.l0.p.i(fVar.f311c, "EventManager", "addEventToDatabase e: ", e, z);
        if (z2) {
            return z2;
        }
        boolean zK2 = z.e().k(fVar, z);
        if ("onetrack_exception".equals(fVar.f311c) || zK2) {
            return zK2;
        }
        a.b.a.a.a.c.a.y0("sa", "s2", e);
        return zK2;
    }

    public final void f(ArrayMap<String, Integer> arrayMap, String str, JSONObject jSONObject) {
        String strL = "onetrack_app_stat".equals(str) ? a.b.a.g.f.a.l(jSONObject.optJSONObject("B").optString("action")) : a.b.a.g.f.a.m(str);
        arrayMap.put(strL, arrayMap.get(strL) == null ? 1 : Integer.valueOf(arrayMap.get(strL).intValue() + 1));
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public boolean g(x xVar) {
        SQLiteDatabase sQLiteDatabaseH;
        synchronized (this.f330a) {
            SQLiteDatabase sQLiteDatabase = null;
            try {
                try {
                    try {
                        ArrayList<t> arrayList = xVar.f382a;
                        if (arrayList != null && arrayList.size() != 0) {
                            sQLiteDatabaseH = this.f330a.H();
                            try {
                                sQLiteDatabaseH.beginTransaction();
                                for (t tVar : xVar.f382a) {
                                    ContentValues contentValues = new ContentValues();
                                    contentValues.put("appid", tVar.f376b);
                                    contentValues.put("package", tVar.f377c);
                                    contentValues.put("event_name", tVar.f378d);
                                    contentValues.put("priority", Integer.valueOf(tVar.h));
                                    contentValues.put("timestamp", Long.valueOf(tVar.j));
                                    contentValues.put("data", tVar.g);
                                    contentValues.put("type", Integer.valueOf(tVar.k));
                                    if (sQLiteDatabaseH.insert("events", null, contentValues) != -1) {
                                        a.b.a.e.l0.p.c("EventManager", "添加后，DB 中事件个数为 " + f329d.incrementAndGet());
                                        e.addAndGet((long) tVar.g.length);
                                    }
                                }
                                a.b.a.e.e0.k.k(false);
                                sQLiteDatabaseH.setTransactionSuccessful();
                                a.b.a.a.a.c.a.v(sQLiteDatabaseH);
                                this.f330a.J();
                                return true;
                            } catch (Exception e2) {
                                e = e2;
                                sQLiteDatabase = sQLiteDatabaseH;
                                a.b.a.e.l0.p.g("EventManager", "DB-Thread: addTransformDataToDB , exception:" + e.getMessage());
                                a.b.a.a.a.c.a.v(sQLiteDatabase);
                                if (sQLiteDatabase != null) {
                                    this.f330a.J();
                                }
                                return false;
                            } catch (Throwable th) {
                                th = th;
                                a.b.a.a.a.c.a.v(sQLiteDatabaseH);
                                if (sQLiteDatabaseH != null) {
                                    this.f330a.J();
                                }
                                throw th;
                            }
                        }
                        a.b.a.e.l0.p.c("EventManager", "TransformEvent记录为空，即将返回");
                        a.b.a.a.a.c.a.v(null);
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        sQLiteDatabaseH = null;
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            } catch (Throwable th3) {
                throw th3;
            }
        }
    }

    public int k(ArrayList<Long> arrayList) {
        synchronized (this.f330a) {
            if (arrayList != null) {
                if (arrayList.size() != 0) {
                    SQLiteDatabase sQLiteDatabase = null;
                    try {
                        try {
                            SQLiteDatabase sQLiteDatabaseH = this.f330a.H();
                            try {
                                boolean z = true;
                                StringBuilder sb = new StringBuilder(((Long.toString(arrayList.get(0).longValue()).length() + 1) * arrayList.size()) + 16);
                                sb.append("_id");
                                sb.append(" in (");
                                sb.append(arrayList.get(0));
                                int size = arrayList.size();
                                for (int i = 1; i < size; i++) {
                                    sb.append(",");
                                    sb.append(arrayList.get(i));
                                }
                                sb.append(")");
                                int iDelete = sQLiteDatabaseH.delete("events", sb.toString(), null);
                                if (iDelete > 0) {
                                    a.b.a.e.l0.p.c("EventManager", "delete events success, remains=" + f329d.addAndGet(-iDelete));
                                    e.set(o());
                                    h();
                                } else {
                                    a.b.a.e.l0.p.c("EventManager", "delete events failed, remains=" + f329d.get());
                                }
                                if (f329d.get() != 0) {
                                    z = false;
                                }
                                a.b.a.e.e0.k.k(z);
                                this.f330a.J();
                                return iDelete;
                            } catch (Exception e2) {
                                e = e2;
                                sQLiteDatabase = sQLiteDatabaseH;
                                a.b.a.e.l0.p.h("EventManager", "deleteEventsSync error, e: ", e);
                                if (sQLiteDatabase != null) {
                                    this.f330a.J();
                                }
                                return 0;
                            } catch (Throwable th) {
                                th = th;
                                sQLiteDatabase = sQLiteDatabaseH;
                                if (sQLiteDatabase != null) {
                                    this.f330a.J();
                                }
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (Exception e3) {
                        e = e3;
                    }
                }
            }
            return 0;
        }
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0096  */
    public final void l() throws Throwable {
        SQLiteDatabase sQLiteDatabaseH;
        Exception e2;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabaseH = this.f330a.H();
            try {
                try {
                    int iDelete = sQLiteDatabaseH.delete("events", null, null);
                    a.b.a.e.l0.p.c("EventManager", "delete table events return result=" + iDelete);
                    if (iDelete > 0) {
                        a.b.a.e.l0.p.c("EventManager", "delete table success, remains=" + f329d.addAndGet(-iDelete));
                        e.set(o());
                        h();
                    } else {
                        a.b.a.e.l0.p.c("EventManager", "delete table failed, remains=" + f329d.get());
                    }
                } catch (Exception e3) {
                    e2 = e3;
                    a.b.a.e.l0.p.g("EventManager", "deleteTable failed with " + e2.getMessage());
                    if (sQLiteDatabaseH == null) {
                        return;
                    }
                }
            } catch (Throwable th) {
                th = th;
                sQLiteDatabase = sQLiteDatabaseH;
                if (sQLiteDatabase != null) {
                    this.f330a.J();
                }
                throw th;
            }
        } catch (Exception e4) {
            sQLiteDatabaseH = null;
            e2 = e4;
        } catch (Throwable th2) {
            th = th2;
            if (sQLiteDatabase != null) {
                this.f330a.J();
            }
            throw th;
        }
        this.f330a.J();
    }

    public final void n(JSONObject jSONObject, Set<String> set) {
        if (jSONObject == null || set.isEmpty()) {
            return;
        }
        try {
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                if (set.contains(itKeys.next())) {
                    itKeys.remove();
                }
            }
        } catch (Exception e2) {
            a.b.a.e.l0.p.h("EventManager", "filterParams error：", e2);
        }
    }

    public final long o() {
        try {
            File databasePath = a.b.a.e.e.b().getDatabasePath(this.f330a.getDatabaseName());
            if (databasePath == null || !databasePath.exists()) {
                return 0L;
            }
            long length = databasePath.length();
            File file = new File(databasePath.getAbsolutePath() + "-wal");
            if (file.exists()) {
                length += file.length();
            }
            File file2 = new File(databasePath.getAbsolutePath() + "-shm");
            return file2.exists() ? length + file2.length() : length;
        } catch (Exception e2) {
            a.a.a.a.a.g(e2, a.a.a.a.a.c("getDatabaseFileSize error: "), "EventManager");
            return 0L;
        }
    }

    public void q(ArrayMap<String, Integer> arrayMap, ArrayMap<String, Integer> arrayMap2, String str, JSONObject jSONObject, long j, boolean z) {
        if (a.b.a.g.f.b.f892b.contains(str)) {
            if (a.b.a.e.l0.v.h(j)) {
                f(arrayMap, str, jSONObject);
                return;
            } else {
                f(arrayMap2, str, jSONObject);
                return;
            }
        }
        if (z) {
            if (a.b.a.e.l0.v.h(j)) {
                f(arrayMap, "onetrack_channel_ad", jSONObject);
            } else {
                f(arrayMap2, "onetrack_channel_ad", jSONObject);
            }
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX WARN: Not initialized variable reg: 4, insn: 0x014f: MOVE (r3 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:68:0x014f */
    public x r() {
        Cursor cursor;
        Cursor cursor2;
        SQLiteDatabase sQLiteDatabaseG;
        Cursor cursorQuery;
        boolean z;
        synchronized (this.f330a) {
            try {
                try {
                    try {
                        sQLiteDatabaseG = this.f330a.G();
                        try {
                            cursorQuery = sQLiteDatabaseG.query("events", null, "appid != '001' or event_name != 'onetrack_active'", null, null, null, "priority ASC, _id ASC", String.valueOf(300));
                            try {
                                int columnIndex = cursorQuery.getColumnIndex("_id");
                                int columnIndex2 = cursorQuery.getColumnIndex("appid");
                                int columnIndex3 = cursorQuery.getColumnIndex("package");
                                int columnIndex4 = cursorQuery.getColumnIndex("event_name");
                                int columnIndex5 = cursorQuery.getColumnIndex("data");
                                int columnIndex6 = cursorQuery.getColumnIndex("timestamp");
                                int columnIndex7 = cursorQuery.getColumnIndex("priority");
                                int columnIndex8 = cursorQuery.getColumnIndex("type");
                                ArrayList arrayList = new ArrayList();
                                ArrayList arrayList2 = new ArrayList();
                                int i = 0;
                                int i2 = 0;
                                while (cursorQuery.moveToNext()) {
                                    long j = cursorQuery.getLong(columnIndex);
                                    String string = cursorQuery.getString(columnIndex2);
                                    String string2 = cursorQuery.getString(columnIndex4);
                                    String string3 = cursorQuery.getString(columnIndex3);
                                    byte[] blob = cursorQuery.getBlob(columnIndex5);
                                    long j2 = cursorQuery.getLong(columnIndex6);
                                    int i3 = cursorQuery.getInt(columnIndex7);
                                    int i4 = columnIndex8 >= 0 ? cursorQuery.getInt(columnIndex8) : 0;
                                    int i5 = columnIndex;
                                    try {
                                        t tVar = new t(j, string, string3, string2, blob, j2, i3);
                                        tVar.k = i4;
                                        arrayList.add(tVar);
                                        arrayList2.add(Long.valueOf(j));
                                        i++;
                                    } catch (Exception e2) {
                                        a.b.a.e.l0.p.h("EventManager", "*** error ***", e2);
                                    }
                                    int length = i2 + blob.length;
                                    if (length >= 5242880) {
                                        a.b.a.e.l0.p.c("EventManager", "reached max len: " + length);
                                        break;
                                    }
                                    i2 = length;
                                    columnIndex2 = columnIndex2;
                                    columnIndex = i5;
                                }
                                int i6 = i;
                                if (arrayList2.size() > 0) {
                                    if (!cursorQuery.isAfterLast()) {
                                        z = false;
                                    } else if (i6 == 300) {
                                        a.b.a.e.l0.p.c("EventManager", "cursor max one query number");
                                        z = false;
                                    } else {
                                        a.b.a.e.l0.p.c("EventManager", "cursor isAfterLast");
                                        z = true;
                                    }
                                    x xVar = new x(arrayList, i6, arrayList2, z);
                                    a.b.a.a.a.c.a.i(cursorQuery);
                                    this.f330a.J();
                                    return xVar;
                                }
                                a.b.a.a.a.c.a.i(cursorQuery);
                            } catch (SQLiteBlobTooBigException e3) {
                                e = e3;
                                a.b.a.e.l0.p.h("EventManager", "blob too big ***", e);
                                l();
                                a.b.a.a.a.c.a.i(cursorQuery);
                                if (sQLiteDatabaseG != null) {
                                }
                                return null;
                            } catch (Exception e4) {
                                e = e4;
                                a.b.a.e.l0.p.h("EventManager", "getNeedToPSDBData error", e);
                                a.b.a.a.a.c.a.i(cursorQuery);
                                if (sQLiteDatabaseG != null) {
                                }
                                return null;
                            }
                        } catch (SQLiteBlobTooBigException e5) {
                            e = e5;
                            cursorQuery = null;
                        } catch (Exception e6) {
                            e = e6;
                            cursorQuery = null;
                        } catch (Throwable th) {
                            th = th;
                            cursor2 = null;
                            a.b.a.a.a.c.a.i(cursor2);
                            if (sQLiteDatabaseG != null) {
                                this.f330a.J();
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        throw th2;
                    }
                } catch (SQLiteBlobTooBigException e7) {
                    e = e7;
                    cursorQuery = null;
                    sQLiteDatabaseG = null;
                } catch (Exception e8) {
                    e = e8;
                    cursorQuery = null;
                    sQLiteDatabaseG = null;
                } catch (Throwable th3) {
                    th = th3;
                    cursor2 = null;
                    sQLiteDatabaseG = null;
                }
                this.f330a.J();
                return null;
            } catch (Throwable th4) {
                th = th4;
                cursor2 = cursor;
            }
        }
    }

    public long s() {
        long jQueryNumEntries;
        synchronized (this.f330a) {
            SQLiteDatabase sQLiteDatabaseG = null;
            try {
                try {
                    sQLiteDatabaseG = this.f330a.G();
                    jQueryNumEntries = DatabaseUtils.queryNumEntries(sQLiteDatabaseG, "events", "timestamp < ? ", new String[]{Long.toString(a.b.a.e.l0.v.f(0))});
                    if (sQLiteDatabaseG != null) {
                        this.f330a.J();
                    }
                } catch (Exception e2) {
                    a.b.a.e.l0.p.g("EventManager", "getTotalEventsNumberBeforeTodaySync failed with " + e2.getMessage());
                    if (sQLiteDatabaseG != null) {
                        this.f330a.J();
                    }
                    return 0L;
                }
            } catch (Throwable th) {
                if (sQLiteDatabaseG != null) {
                    this.f330a.J();
                }
                throw th;
            }
        }
        return jQueryNumEntries;
    }

    public final long t() {
        long jQueryNumEntries;
        synchronized (this.f330a) {
            SQLiteDatabase sQLiteDatabaseG = null;
            try {
                try {
                    sQLiteDatabaseG = this.f330a.G();
                    jQueryNumEntries = DatabaseUtils.queryNumEntries(sQLiteDatabaseG, "events");
                    if (sQLiteDatabaseG != null) {
                        this.f330a.J();
                    }
                } catch (Exception e2) {
                    a.b.a.e.l0.p.g("EventManager", "getTotalEventsNumberSync failed with " + e2.getMessage());
                    if (sQLiteDatabaseG != null) {
                        this.f330a.J();
                    }
                    return -1L;
                }
            } catch (Throwable th) {
                if (sQLiteDatabaseG != null) {
                    this.f330a.J();
                }
                throw th;
            }
        }
        return jQueryNumEntries;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:113:0x01de */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:146:0x004f */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:147:0x004f */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v33 */
    /* JADX WARN: Type inference failed for: r4v34 */
    /* JADX WARN: Type inference failed for: r4v35 */
    /* JADX WARN: Type inference failed for: r4v36 */
    /* JADX WARN: Type inference failed for: r4v37 */
    /* JADX WARN: Type inference failed for: r4v38 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    public Boolean u(a.b.a.e.c0.e.a aVar, int i, String str, Throwable th) {
        SQLiteDatabase sQLiteDatabaseH;
        ?? r4;
        Cursor cursor;
        Cursor cursor2;
        c cVar;
        ?? r5;
        int i2;
        synchronized (this.f330a) {
            try {
                try {
                    try {
                        sQLiteDatabaseH = this.f330a.H();
                        try {
                            boolean z = true;
                            Cursor cursorQuery = sQLiteDatabaseH.query("events", new String[]{"_id", "data"}, "appid = ? AND event_name = ? AND priority = ?", new String[]{"001", "onetrack_exception", String.valueOf(2)}, null, null, null, "50");
                            if (cursorQuery != null) {
                                try {
                                    try {
                                        if (cursorQuery.getCount() != 0) {
                                            int columnIndex = cursorQuery.getColumnIndex("_id");
                                            int columnIndex2 = cursorQuery.getColumnIndex("data");
                                            while (cursorQuery.moveToNext()) {
                                                long j = cursorQuery.getLong(columnIndex);
                                                byte[] blob = cursorQuery.getBlob(columnIndex2);
                                                String strJ = blob != null ? j(blob) : null;
                                                if (a.b.a.a.a.c.a.w0(strJ)) {
                                                    try {
                                                        JSONObject jSONObject = new JSONObject(strJ);
                                                        if (aVar.g.equals(jSONObject.optJSONObject("H").optString("ot_event_id"))) {
                                                            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("B");
                                                            String strOptString = jSONObjectOptJSONObject.optString("type");
                                                            int iOptInt = jSONObjectOptJSONObject.optInt("url_index");
                                                            int iOptInt2 = jSONObjectOptJSONObject.optInt("err_code");
                                                            String strOptString2 = jSONObjectOptJSONObject.optString("err_body");
                                                            String strOptString3 = jSONObjectOptJSONObject.optString("message");
                                                            i2 = columnIndex;
                                                            try {
                                                                if ("adm".equals(strOptString)) {
                                                                    if (aVar.h == iOptInt && i == iOptInt2) {
                                                                        try {
                                                                            if (!TextUtils.isEmpty(str)) {
                                                                                try {
                                                                                    if (str.equals(strOptString2)) {
                                                                                    }
                                                                                } catch (Exception e2) {
                                                                                    e = e2;
                                                                                    a.b.a.e.l0.p.c("EventManager", "hasAndUpdateSameAdMExceptionEvent exception:" + e);
                                                                                    columnIndex = i2;
                                                                                    z = true;
                                                                                }
                                                                            }
                                                                            if (th == null || TextUtils.isEmpty(th.getMessage()) || th.getMessage().equals(strOptString3)) {
                                                                                jSONObjectOptJSONObject.put("retry", jSONObjectOptJSONObject.optInt("retry", 0) + 1);
                                                                                byte[] bArrM = m(jSONObject.toString());
                                                                                ContentValues contentValues = new ContentValues();
                                                                                contentValues.put("data", bArrM);
                                                                                try {
                                                                                    String[] strArr = new String[1];
                                                                                    try {
                                                                                        strArr[0] = String.valueOf(j);
                                                                                        int iUpdate = sQLiteDatabaseH.update("events", contentValues, "_id = ?", strArr);
                                                                                        if (iUpdate > 0) {
                                                                                            a.b.a.e.l0.p.c("EventManager", "updata same adMException rowsupdated:" + iUpdate);
                                                                                            Boolean bool = Boolean.TRUE;
                                                                                            a.b.a.a.a.c.a.i(cursorQuery);
                                                                                            this.f330a.J();
                                                                                            return bool;
                                                                                        }
                                                                                    } catch (Exception e3) {
                                                                                        e = e3;
                                                                                        a.b.a.e.l0.p.c("EventManager", "hasAndUpdateSameAdMExceptionEvent exception:" + e);
                                                                                    }
                                                                                } catch (Exception e4) {
                                                                                    e = e4;
                                                                                }
                                                                            }
                                                                        } catch (Exception e5) {
                                                                            e = e5;
                                                                            a.b.a.e.l0.p.c("EventManager", "hasAndUpdateSameAdMExceptionEvent exception:" + e);
                                                                            columnIndex = i2;
                                                                            z = true;
                                                                        }
                                                                    }
                                                                    a.b.a.e.l0.p.c("EventManager", "item exception no same");
                                                                } else {
                                                                    a.b.a.e.l0.p.c("EventManager", "no admException");
                                                                }
                                                            } catch (Exception e6) {
                                                                e = e6;
                                                            }
                                                            columnIndex = i2;
                                                            z = true;
                                                        }
                                                    } catch (Exception e7) {
                                                        e = e7;
                                                        i2 = columnIndex;
                                                    }
                                                }
                                            }
                                            a.b.a.a.a.c.a.i(cursorQuery);
                                            cVar = this.f330a;
                                            r5 = z;
                                            cVar.J();
                                            r4 = r5;
                                            return Boolean.FALSE;
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        r4 = cursorQuery;
                                        a.b.a.a.a.c.a.i(r4);
                                        if (sQLiteDatabaseH != null) {
                                            this.f330a.J();
                                        }
                                        throw th;
                                    }
                                } catch (SQLiteBlobTooBigException e8) {
                                    e = e8;
                                    cursor2 = cursorQuery;
                                    a.b.a.e.l0.p.h("EventManager", "blob too big ***", e);
                                    l();
                                    a.b.a.a.a.c.a.i(cursor2);
                                    r4 = cursor2;
                                    if (sQLiteDatabaseH != null) {
                                        cVar = this.f330a;
                                        r5 = cursor2;
                                    }
                                    return Boolean.FALSE;
                                } catch (Exception e9) {
                                    e = e9;
                                    cursor = cursorQuery;
                                    a.b.a.e.l0.p.h("EventManager", "getAppIdSingleEventData error, e: ", e);
                                    a.b.a.a.a.c.a.i(cursor);
                                    r4 = cursor;
                                    if (sQLiteDatabaseH != null) {
                                        cVar = this.f330a;
                                        r5 = cursor;
                                    }
                                    return Boolean.FALSE;
                                }
                            }
                            Boolean bool2 = Boolean.FALSE;
                            a.b.a.a.a.c.a.i(cursorQuery);
                            this.f330a.J();
                            return bool2;
                        } catch (SQLiteBlobTooBigException e10) {
                            e = e10;
                            cursor2 = null;
                        } catch (Exception e11) {
                            e = e11;
                            cursor = null;
                        } catch (Throwable th3) {
                            th = th3;
                            r4 = 0;
                        }
                    } catch (Throwable th4) {
                        throw th4;
                    }
                } catch (SQLiteBlobTooBigException e12) {
                    e = e12;
                    cursor2 = null;
                    sQLiteDatabaseH = null;
                } catch (Exception e13) {
                    e = e13;
                    cursor = null;
                    sQLiteDatabaseH = null;
                } catch (Throwable th5) {
                    th = th5;
                    r4 = 0;
                    sQLiteDatabaseH = null;
                }
            } catch (Throwable th6) {
                th = th6;
            }
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX WARN: Code duplicated, block: B:27:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:28:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:30:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:33:0x00c3 A[RETURN] */
    public final boolean w(JSONObject jSONObject, a.b.a.e.f fVar) {
        boolean z;
        String string;
        if (a.b.a.e.e0.k.c(jSONObject, "disable_log", false)) {
            StringBuilder sbC = a.a.a.a.a.c("AppId is ");
            sbC.append(fVar.f309a);
            sbC.append(", this event ");
            sbC.append(fVar.f311c);
            sbC.append(" disabled tracking data, skip it.");
            string = sbC.toString();
        } else {
            try {
                String strG = a.b.a.e.e0.k.g(jSONObject, "cycle", "");
                if (!TextUtils.isEmpty(strG)) {
                    a.b.a.e.l0.p.c("EventManager", "shouldUploadByCycle " + fVar.f311c + "_cycle: " + strG);
                    String[] strArrSplit = strG.split(",");
                    int iD = a.b.a.e.l0.v.d();
                    int iC = a.b.a.e.l0.v.c();
                    int length = strArrSplit.length;
                    int i = 0;
                    z = true;
                    while (i < length) {
                        try {
                            String str = strArrSplit[i];
                            int i2 = Integer.parseInt(str.substring(1));
                            if (str.startsWith("w")) {
                                if (iD != i2) {
                                    i++;
                                    z = false;
                                }
                            } else if (!str.startsWith("m") || iC != i2) {
                                i++;
                                z = false;
                            }
                        } catch (Exception e2) {
                            e = e2;
                            StringBuilder sbC2 = a.a.a.a.a.c("shouldUploadByCycle ");
                            sbC2.append(fVar.f311c);
                            sbC2.append("_exception: ");
                            sbC2.append(e.getMessage());
                            a.b.a.e.l0.p.c("EventManager", sbC2.toString());
                        }
                    }
                    if (!z) {
                        string = "This event should not upload by Cycle , skip it.";
                    } else {
                        if (!F(jSONObject, fVar)) {
                            return false;
                        }
                        string = "This event should not upload by sampling , skip it.";
                    }
                }
                z = true;
                break;
            } catch (Exception e3) {
                e = e3;
                z = true;
            }
            if (!z) {
                string = "This event should not upload by Cycle , skip it.";
            } else {
                if (!F(jSONObject, fVar)) {
                    return false;
                }
                string = "This event should not upload by sampling , skip it.";
            }
        }
        a.b.a.e.l0.p.c("EventManager", string);
        return true;
    }

    public final boolean x(String str, String str2) {
        List<String> listL0;
        if (TextUtils.isEmpty(str2)) {
            return true;
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            String[] strArrSplit = str.split("\\.");
            if (strArrSplit != null && strArrSplit.length >= 5 && (listL0 = a.b.a.a.a.c.a.L0(str2, ",")) != null && listL0.contains(strArrSplit[4])) {
                a.b.a.e.l0.p.c("EventManager", "The id is matched");
                return true;
            }
        } catch (Exception e2) {
            a.a.a.a.a.g(e2, a.a.a.a.a.c("isMatchId error："), "EventManager");
        }
        return false;
    }

    public final boolean y(String str, String str2) {
        List<String> listL0;
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str)) {
            try {
                String[] strArrSplit = str.split("\\.");
                if (strArrSplit != null && strArrSplit.length >= 5 && (listL0 = a.b.a.a.a.c.a.L0(str2, ",")) != null && listL0.contains(strArrSplit[4])) {
                    a.b.a.e.l0.p.c("EventManager", "The id is matched");
                    return true;
                }
            } catch (Exception e2) {
                a.a.a.a.a.g(e2, a.a.a.a.a.c("isMatchId error："), "EventManager");
            }
        }
        return false;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final boolean z(a.b.a.e.f fVar, String str) {
        try {
            if ("et7".equals(str)) {
                return true;
            }
            return TextUtils.equals("001", fVar.f309a) && a.b.a.g.f.b.f892b.contains(fVar.f311c);
        } catch (Exception unused) {
            return false;
        }
    }
}
