package a.b.a.c.q;

import a.b.a.c.v.o;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: classes.dex */
public class b extends d {
    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public b(Context context) {
        super(context, "analyticsv2.db", 3, 10000L, 300000L);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX WARN: Code duplicated, block: B:20:0x0078 A[PHI: r11
      0x0078: PHI (r11v4 java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock) = 
      (r11v3 java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock)
      (r11v6 java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock)
     binds: [B:19:0x0076, B:9:0x005a] A[DONT_GENERATE, DONT_INLINE]] */
    public boolean M(List<LogEvent> list) {
        ReentrantReadWriteLock.WriteLock writeLock;
        o.a("DeleteEvents");
        a.b.a.c.v.i.b("AnalyticsDBV2", "delete ");
        SQLiteDatabase sQLiteDatabaseH = null;
        try {
            try {
                this.k.lock();
                sQLiteDatabaseH = H();
                sQLiteDatabaseH.beginTransaction();
                Iterator<LogEvent> it = list.iterator();
                while (it.hasNext()) {
                    sQLiteDatabaseH.execSQL(String.format("delete from analyticsv2 where _id = %s", it.next().f1250a + ""));
                }
                sQLiteDatabaseH.setTransactionSuccessful();
                a.b.a.a.a.c.a.v(sQLiteDatabaseH);
                o.b();
                J();
                writeLock = this.k;
                if (writeLock != null) {
                    writeLock.unlock();
                }
            } catch (Exception e) {
                Log.e(a.b.a.c.v.i.a("AnalyticsDBV2"), "delete e", e);
                a.b.a.a.a.c.a.v(sQLiteDatabaseH);
                o.b();
                if (sQLiteDatabaseH != null) {
                    J();
                }
                writeLock = this.k;
                if (writeLock != null) {
                    writeLock.unlock();
                }
            }
            return true;
        } catch (Throwable th) {
            a.b.a.a.a.c.a.v(sQLiteDatabaseH);
            o.b();
            if (sQLiteDatabaseH != null) {
                J();
            }
            ReentrantReadWriteLock.WriteLock writeLock2 = this.k;
            if (writeLock2 != null) {
                writeLock2.unlock();
            }
            throw th;
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:10:0x0039 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:30:0x000c */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:8:0x0037 */
    /* JADX WARN: Code duplicated, block: B:24:0x0059  */
    /* JADX WARN: Code duplicated, block: B:27:0x0060  */
    public void N() throws Throwable {
        SQLiteDatabase sQLiteDatabaseH;
        Exception e;
        ReentrantReadWriteLock.WriteLock writeLock;
        ReentrantReadWriteLock.WriteLock writeLock2;
        Object obj;
        Object obj2 = "[ANALYTICS-DBV2-TRUNCATE] drop table analytics";
        Log.w(a.b.a.c.v.i.a("AnalyticsDBV2"), "[ANALYTICS-DBV2-TRUNCATE] drop table analytics");
        Object obj3 = null;
        try {
            try {
                this.k.lock();
                sQLiteDatabaseH = H();
                try {
                    a.b.a.c.v.i.b("AnalyticsDBV2", "deleteAllItemsInAnalyticsTable deleteCount" + sQLiteDatabaseH.delete("analyticsv2", null, null));
                    J();
                    writeLock2 = this.k;
                    obj = sQLiteDatabaseH;
                    obj2 = sQLiteDatabaseH;
                    if (writeLock2 != null) {
                        writeLock2.unlock();
                        obj2 = obj;
                    }
                } catch (Exception e2) {
                    e = e2;
                    Log.e(a.b.a.c.v.i.a("AnalyticsDBV2"), "deleteAllItemsInAnalyticsTable e", e);
                    if (sQLiteDatabaseH != null) {
                        J();
                    }
                    writeLock2 = this.k;
                    obj = sQLiteDatabaseH;
                    obj2 = sQLiteDatabaseH;
                    if (writeLock2 != null) {
                    }
                }
            } catch (Throwable th) {
                th = th;
                obj3 = obj2;
                if (obj3 != null) {
                    J();
                }
                writeLock = this.k;
                if (writeLock != null) {
                    writeLock.unlock();
                }
                throw th;
            }
        } catch (Exception e3) {
            sQLiteDatabaseH = null;
            e = e3;
        } catch (Throwable th2) {
            th = th2;
            if (obj3 != null) {
                J();
            }
            writeLock = this.k;
            if (writeLock != null) {
                writeLock.unlock();
            }
            throw th;
        }
    }

    public void O() {
        ReentrantReadWriteLock.WriteLock writeLock;
        SQLiteDatabase sQLiteDatabaseH = null;
        try {
            try {
                this.k.lock();
                sQLiteDatabaseH = H();
                a.b.a.c.v.i.b("AnalyticsDBV2", "deleteEventSendCountExpired deleteCount:" + sQLiteDatabaseH.delete("analyticsv2", "send_count >= ? ", new String[]{String.valueOf(4)}));
                J();
                writeLock = this.k;
                if (writeLock == null) {
                    return;
                }
            } catch (Exception e) {
                Log.e(a.b.a.c.v.i.a("AnalyticsDBV2"), "deleteEventSendCountExpired e", e);
                if (sQLiteDatabaseH != null) {
                    J();
                }
                writeLock = this.k;
                if (writeLock == null) {
                    return;
                }
            }
            writeLock.unlock();
        } catch (Throwable th) {
            if (sQLiteDatabaseH != null) {
                J();
            }
            ReentrantReadWriteLock.WriteLock writeLock2 = this.k;
            if (writeLock2 != null) {
                writeLock2.unlock();
            }
            throw th;
        }
    }

    public void P(long j) {
        ReentrantReadWriteLock.WriteLock writeLock;
        long jCurrentTimeMillis = System.currentTimeMillis() - j;
        SQLiteDatabase sQLiteDatabaseH = null;
        try {
            try {
                this.k.lock();
                sQLiteDatabaseH = H();
                a.b.a.c.v.i.b("AnalyticsDBV2", "deleteEventTimeExpired deleteCount" + sQLiteDatabaseH.delete("analyticsv2", "event_time < ? ", new String[]{String.valueOf(jCurrentTimeMillis)}));
                J();
                writeLock = this.k;
                if (writeLock == null) {
                    return;
                }
            } catch (Exception e) {
                Log.e(a.b.a.c.v.i.a("AnalyticsDBV2"), "deleteEventTimeExpired e", e);
                if (sQLiteDatabaseH != null) {
                    J();
                }
                writeLock = this.k;
                if (writeLock == null) {
                    return;
                }
            }
            writeLock.unlock();
        } catch (Throwable th) {
            if (sQLiteDatabaseH != null) {
                J();
            }
            ReentrantReadWriteLock.WriteLock writeLock2 = this.k;
            if (writeLock2 != null) {
                writeLock2.unlock();
            }
            throw th;
        }
    }

    public final void Q(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            sQLiteDatabase.execSQL(str);
        } catch (Exception e) {
            Log.e(a.b.a.c.v.i.a("AnalyticsDBV2"), "exeSQL e:", e);
        }
    }

    public long R() {
        ReentrantReadWriteLock.ReadLock readLock;
        Cursor cursorX = null;
        try {
            try {
                this.j.lock();
                cursorX = X("select count(*) from analyticsv2");
                if (cursorX != null && cursorX.moveToFirst()) {
                    a.b.a.c.v.i.b("AnalyticsDBV2", "db count is " + cursorX.getLong(0));
                    long j = cursorX.getLong(0);
                    a.b.a.a.a.c.a.i(cursorX);
                    J();
                    ReentrantReadWriteLock.ReadLock readLock2 = this.j;
                    if (readLock2 != null) {
                        readLock2.unlock();
                    }
                    return j;
                }
                a.b.a.a.a.c.a.i(cursorX);
                J();
                readLock = this.j;
                if (readLock == null) {
                    return 0L;
                }
            } catch (Exception e) {
                Log.e(a.b.a.c.v.i.a("AnalyticsDBV2"), "getCount e", e);
                a.b.a.a.a.c.a.i(cursorX);
                J();
                readLock = this.j;
                if (readLock == null) {
                    return 0L;
                }
            }
            readLock.unlock();
            return 0L;
        } catch (Throwable th) {
            a.b.a.a.a.c.a.i(cursorX);
            J();
            ReentrantReadWriteLock.ReadLock readLock3 = this.j;
            if (readLock3 != null) {
                readLock3.unlock();
            }
            throw th;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public void S(List<LogEvent> list) {
        ReentrantReadWriteLock.WriteLock writeLock;
        if (list == null || list.size() == 0) {
            return;
        }
        SQLiteDatabase sQLiteDatabaseH = null;
        try {
            try {
                this.k.lock();
                sQLiteDatabaseH = H();
                Iterator<LogEvent> it = list.iterator();
                while (it.hasNext()) {
                    String str = String.format("update %s set %s = %s + 1 where %s = %s", "analyticsv2", "send_count", "send_count", "_id", Long.valueOf(it.next().f1250a));
                    sQLiteDatabaseH.execSQL(str);
                    a.b.a.c.v.i.b("AnalyticsDBV2", "increaseRetryCountFromDBV2 sql: " + str);
                }
                if (sQLiteDatabaseH != null) {
                    J();
                }
                writeLock = this.k;
                if (writeLock == null) {
                    return;
                }
            } catch (Exception e) {
                Log.e(a.b.a.c.v.i.a("AnalyticsDBV2"), "increaseRetryCount e", e);
                if (sQLiteDatabaseH != null) {
                    J();
                }
                writeLock = this.k;
                if (writeLock == null) {
                    return;
                }
            }
            writeLock.unlock();
        } catch (Throwable th) {
            if (sQLiteDatabaseH != null) {
                J();
            }
            ReentrantReadWriteLock.WriteLock writeLock2 = this.k;
            if (writeLock2 != null) {
                writeLock2.unlock();
            }
            throw th;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX WARN: Code duplicated, block: B:28:0x00ef A[PHI: r15
      0x00ef: PHI (r15v4 java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock) = 
      (r15v2 java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock)
      (r15v6 java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock)
     binds: [B:27:0x00ed, B:17:0x00d1] A[DONT_GENERATE, DONT_INLINE]] */
    public boolean T(List<LogEvent> list) {
        ReentrantReadWriteLock.WriteLock writeLock;
        o.a("InsertEvents");
        a.b.a.c.v.i.b("AnalyticsDBV2", "insert events " + list.size());
        SQLiteDatabase sQLiteDatabaseH = null;
        try {
            try {
                this.k.lock();
                sQLiteDatabaseH = H();
                sQLiteDatabaseH.beginTransaction();
                a.b.a.c.t.g gVarG = a.b.a.c.t.g.g(this.h);
                for (LogEvent logEvent : list) {
                    SQLiteStatement sQLiteStatementCompileStatement = sQLiteDatabaseH.compileStatement(String.format("insert into %s values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", "analyticsv2"));
                    sQLiteStatementCompileStatement.bindString(1, logEvent.f1251b);
                    sQLiteStatementCompileStatement.bindLong(2, logEvent.e);
                    sQLiteStatementCompileStatement.bindString(3, logEvent.f1253d);
                    sQLiteStatementCompileStatement.bindString(4, logEvent.f1252c);
                    sQLiteStatementCompileStatement.bindString(5, logEvent.g);
                    sQLiteStatementCompileStatement.bindString(6, logEvent.f);
                    sQLiteStatementCompileStatement.bindString(7, logEvent.h);
                    long j = 1;
                    sQLiteStatementCompileStatement.bindLong(8, gVarG.n(logEvent) ? 1L : 0L);
                    if (!gVarG.w(logEvent)) {
                        j = 0;
                    }
                    sQLiteStatementCompileStatement.bindLong(9, j);
                    sQLiteStatementCompileStatement.bindLong(10, 0L);
                    sQLiteStatementCompileStatement.bindLong(11, gVarG.j(logEvent));
                    sQLiteStatementCompileStatement.bindLong(12, logEvent.i);
                    sQLiteStatementCompileStatement.bindLong(13, logEvent.k);
                    sQLiteStatementCompileStatement.executeInsert();
                    sQLiteStatementCompileStatement.clearBindings();
                }
                sQLiteDatabaseH.setTransactionSuccessful();
                a.b.a.a.a.c.a.v(sQLiteDatabaseH);
                o.b();
                J();
                writeLock = this.k;
                if (writeLock != null) {
                    writeLock.unlock();
                }
            } catch (Exception e) {
                Log.e(a.b.a.c.v.i.a("AnalyticsDBV2"), "insert e", e);
                a.b.a.a.a.c.a.v(sQLiteDatabaseH);
                o.b();
                if (sQLiteDatabaseH != null) {
                    J();
                }
                writeLock = this.k;
                if (writeLock != null) {
                    writeLock.unlock();
                }
            }
            return true;
        } catch (Throwable th) {
            a.b.a.a.a.c.a.v(sQLiteDatabaseH);
            o.b();
            if (sQLiteDatabaseH != null) {
                J();
            }
            ReentrantReadWriteLock.WriteLock writeLock2 = this.k;
            if (writeLock2 != null) {
                writeLock2.unlock();
            }
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0075 A[PHI: r5
      0x0075: PHI (r5v4 java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock) = 
      (r5v3 java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock)
      (r5v8 java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock)
     binds: [B:20:0x0073, B:12:0x0059] A[DONT_GENERATE, DONT_INLINE]] */
    public List<LogEvent> U(int i) {
        ReentrantReadWriteLock.ReadLock readLock;
        String str = "select * from analyticsv2 where real_time = 0  order by event_time asc limit " + i;
        a.b.a.c.v.i.b("AnalyticsDBV2", "queryPollingEventsFromEventDBV2 : " + str);
        ArrayList arrayList = new ArrayList();
        Cursor cursorX = null;
        try {
            this.j.lock();
            cursorX = X(str);
            if (cursorX != null && cursorX.moveToFirst()) {
                do {
                    arrayList.add(new LogEvent(cursorX, "analyticsv2.db"));
                } while (cursorX.moveToNext());
            }
            a.b.a.a.a.c.a.i(cursorX);
            J();
            readLock = this.j;
            if (readLock != null) {
                readLock.unlock();
            }
        } catch (Exception unused) {
            a.b.a.a.a.c.a.i(cursorX);
            J();
            readLock = this.j;
            if (readLock != null) {
                readLock.unlock();
            }
        } catch (Throwable th) {
            a.b.a.a.a.c.a.i(cursorX);
            J();
            ReentrantReadWriteLock.ReadLock readLock2 = this.j;
            if (readLock2 != null) {
                readLock2.unlock();
            }
            throw th;
        }
        a.b.a.c.v.i.b("AnalyticsDBV2", String.format("queryPollingEventsFromEventDBV2 %d events from db. ", Integer.valueOf(arrayList.size())));
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0075 A[PHI: r5
      0x0075: PHI (r5v4 java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock) = 
      (r5v3 java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock)
      (r5v8 java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock)
     binds: [B:20:0x0073, B:12:0x0059] A[DONT_GENERATE, DONT_INLINE]] */
    public List<LogEvent> V(int i) {
        ReentrantReadWriteLock.ReadLock readLock;
        String str = "select * from analyticsv2 where real_time = 0 and polling_wifi_only = 0  order by event_time asc limit " + i;
        a.b.a.c.v.i.b("AnalyticsDBV2", "queryPollingEventNetAllFromEventDBV2 : " + str);
        ArrayList arrayList = new ArrayList();
        Cursor cursorX = null;
        try {
            this.j.lock();
            cursorX = X(str);
            if (cursorX != null && cursorX.moveToFirst()) {
                do {
                    arrayList.add(new LogEvent(cursorX, "analyticsv2.db"));
                } while (cursorX.moveToNext());
            }
            a.b.a.a.a.c.a.i(cursorX);
            J();
            readLock = this.j;
            if (readLock != null) {
                readLock.unlock();
            }
        } catch (Exception unused) {
            a.b.a.a.a.c.a.i(cursorX);
            J();
            readLock = this.j;
            if (readLock != null) {
                readLock.unlock();
            }
        } catch (Throwable th) {
            a.b.a.a.a.c.a.i(cursorX);
            J();
            ReentrantReadWriteLock.ReadLock readLock2 = this.j;
            if (readLock2 != null) {
                readLock2.unlock();
            }
            throw th;
        }
        a.b.a.c.v.i.b("AnalyticsDBV2", String.format("queryPollingEventNetAllFromEventDBV2 %d events from db. ", Integer.valueOf(arrayList.size())));
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0055 A[PHI: r2
      0x0055: PHI (r2v4 java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock) = 
      (r2v3 java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock)
      (r2v9 java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock)
     binds: [B:18:0x0053, B:10:0x0038] A[DONT_GENERATE, DONT_INLINE]] */
    public List<LogEvent> W() {
        ReentrantReadWriteLock.ReadLock readLock;
        a.b.a.c.v.i.b("AnalyticsDBV2", "queryRealTimeEvents :select * from analyticsv2 where real_time = 1");
        ArrayList arrayList = new ArrayList();
        Cursor cursorX = null;
        try {
            this.j.lock();
            cursorX = X("select * from analyticsv2 where real_time = 1");
            if (cursorX != null && cursorX.moveToFirst()) {
                do {
                    arrayList.add(new LogEvent(cursorX, "analyticsv2.db"));
                } while (cursorX.moveToNext());
            }
            a.b.a.a.a.c.a.i(cursorX);
            J();
            readLock = this.j;
            if (readLock != null) {
                readLock.unlock();
            }
        } catch (Exception unused) {
            a.b.a.a.a.c.a.i(cursorX);
            J();
            readLock = this.j;
            if (readLock != null) {
                readLock.unlock();
            }
        } catch (Throwable th) {
            a.b.a.a.a.c.a.i(cursorX);
            J();
            ReentrantReadWriteLock.ReadLock readLock2 = this.j;
            if (readLock2 != null) {
                readLock2.unlock();
            }
            throw th;
        }
        a.b.a.c.v.i.b("AnalyticsDBV2", String.format("queryRealTimeEventsFromEventDbV2 %d events from db. ", Integer.valueOf(arrayList.size())));
        return arrayList;
    }

    public final Cursor X(String str) {
        try {
            return G().rawQuery(str, null);
        } catch (Exception e) {
            Log.e(a.b.a.c.v.i.a("AnalyticsDBV2"), "rawQuery e", e);
            return null;
        }
    }

    @Override // a.b.a.c.q.d, android.database.sqlite.SQLiteOpenHelper
    public void onConfigure(SQLiteDatabase sQLiteDatabase) {
        super.onConfigure(sQLiteDatabase);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format("create table %s(_id INTEGER PRIMARY KEY,config_key TEXT,event_time INT8, session_id TEXT,app_id TEXT,sn TEXT,body TEXT, header TEXT,real_time INT,polling_wifi_only INT,send_count INT,level INT)", "analyticsv2"));
        onUpgrade(sQLiteDatabase, 1, 3);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL(String.format("drop table if exists %s", "analyticsv2"));
        sQLiteDatabase.execSQL(String.format("create table %s(_id INTEGER PRIMARY KEY,config_key TEXT,event_time INT8, session_id TEXT,app_id TEXT,sn TEXT,body TEXT, header TEXT,real_time INT,polling_wifi_only INT,send_count INT,level INT)", "analyticsv2"));
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        String str;
        for (int i3 = i + 1; i3 <= i2; i3++) {
            if (i3 != 2) {
                if (i3 == 3) {
                    str = String.format("alter table %s add column %s %s", "analyticsv2", "id_type", " INT DEFAULT(0)");
                }
            } else {
                str = String.format("alter table %s add column %s %s", "analyticsv2", "event_type", " INT DEFAULT(0)");
            }
            Q(sQLiteDatabase, str);
        }
    }
}
