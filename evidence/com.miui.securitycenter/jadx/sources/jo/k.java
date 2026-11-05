package jo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.miui.common.utils.m2;
import com.miui.earthquakewarning.model.SaveAreaResult;
import com.miui.gamebooster.aihelper.GameAIHttpResp;
import com.xiaomi.continuity.constant.MiContinuityStatus;
import io.m;
import io.o;
import io.p;
import java.util.ArrayList;
import java.util.List;
import lp.n;
import wo.l;

/* JADX INFO: loaded from: classes4.dex */
public final class k extends SQLiteOpenHelper implements jo.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a f33544c = new a(null);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f33545d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f33546e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final String f33547f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final wo.h f33548g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private b f33549a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f33550b;

    public static final class a {
        /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x0000: CONSTRUCTOR  A[MD:():void (m)] (LINE:1) call: jo.k.a.<init>():void type: THIS */
        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
        public /* synthetic */ a(lp.h hVar) {
            this();
        }

        public final k a() {
            return (k) k.f33548g.getValue();
        }

        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
        private a() {
        }
    }

    private static final class b extends Handler {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(Looper looper) {
            super(looper);
            n.f(looper, "looper");
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            n.f(message, "msg");
            super.dispatchMessage(message);
            int i10 = message.what;
            if (i10 == 101) {
                if (message.obj instanceof io.k) {
                    k kVarA = k.f33544c.a();
                    Object obj = message.obj;
                    n.d(obj, "null cannot be cast to non-null type com.xiaomi.security.xsof.frauddetection.bean.ScreenshotInfo");
                    kVarA.h1((io.k) obj);
                    return;
                }
                return;
            }
            if (i10 == 102) {
                if (message.obj instanceof io.g) {
                    k kVarA2 = k.f33544c.a();
                    Object obj2 = message.obj;
                    n.d(obj2, "null cannot be cast to non-null type com.xiaomi.security.xsof.frauddetection.bean.ICallBack<kotlin.collections.MutableList<com.xiaomi.security.xsof.frauddetection.bean.ScreenshotInfo>?>");
                    kVarA2.P1((io.g) obj2);
                    return;
                }
                return;
            }
            if (i10 == 301) {
                if (message.obj instanceof io.b) {
                    k kVarA3 = k.f33544c.a();
                    Object obj3 = message.obj;
                    n.d(obj3, "null cannot be cast to non-null type com.xiaomi.security.xsof.frauddetection.bean.AppInfo");
                    kVarA3.a1((io.b) obj3);
                    return;
                }
                return;
            }
            if (i10 == 302) {
                if (message.obj instanceof io.g) {
                    k kVarA4 = k.f33544c.a();
                    Object obj4 = message.obj;
                    n.d(obj4, "null cannot be cast to non-null type com.xiaomi.security.xsof.frauddetection.bean.ICallBack<kotlin.collections.MutableList<com.xiaomi.security.xsof.frauddetection.bean.AppInfo>?>");
                    kVarA4.J1((io.g) obj4);
                    return;
                }
                return;
            }
            if (i10 == 401) {
                if (message.obj instanceof io.f) {
                    k kVarA5 = k.f33544c.a();
                    Object obj5 = message.obj;
                    n.d(obj5, "null cannot be cast to non-null type com.xiaomi.security.xsof.frauddetection.bean.FraudUrlInfo");
                    kVarA5.l1((io.f) obj5);
                    return;
                }
                return;
            }
            if (i10 == 402) {
                k kVarA6 = k.f33544c.a();
                Object obj6 = message.obj;
                n.d(obj6, "null cannot be cast to non-null type com.xiaomi.security.xsof.frauddetection.bean.ICallBack<kotlin.collections.MutableList<com.xiaomi.security.xsof.frauddetection.bean.FraudUrlInfo>?>");
                kVarA6.G1((io.g) obj6);
                return;
            }
            if (i10 == 501) {
                if (message.obj instanceof io.d) {
                    k kVarA7 = k.f33544c.a();
                    Object obj7 = message.obj;
                    n.d(obj7, "null cannot be cast to non-null type com.xiaomi.security.xsof.frauddetection.bean.CallForwardingInfo");
                    kVarA7.c1((io.d) obj7);
                    return;
                }
                return;
            }
            if (i10 == 502) {
                if (message.obj instanceof io.g) {
                    k kVarA8 = k.f33544c.a();
                    Object obj8 = message.obj;
                    n.d(obj8, "null cannot be cast to non-null type com.xiaomi.security.xsof.frauddetection.bean.ICallBack<kotlin.collections.MutableList<com.xiaomi.security.xsof.frauddetection.bean.CallForwardingInfo>?>");
                    kVarA8.A1((io.g) obj8);
                    return;
                }
                return;
            }
            if (i10 == 601) {
                if (message.obj instanceof io.a) {
                    k kVarA9 = k.f33544c.a();
                    Object obj9 = message.obj;
                    n.d(obj9, "null cannot be cast to non-null type com.xiaomi.security.xsof.frauddetection.bean.AirplaneModeInfo");
                    kVarA9.Y0((io.a) obj9);
                    return;
                }
                return;
            }
            if (i10 == 602) {
                if (message.obj instanceof io.g) {
                    k kVarA10 = k.f33544c.a();
                    Object obj10 = message.obj;
                    n.d(obj10, "null cannot be cast to non-null type com.xiaomi.security.xsof.frauddetection.bean.ICallBack<kotlin.collections.MutableList<com.xiaomi.security.xsof.frauddetection.bean.AirplaneModeInfo>?>");
                    kVarA10.u1((io.g) obj10);
                    return;
                }
                return;
            }
            if (i10 == 901) {
                if (message.obj instanceof io.b) {
                    k kVarA11 = k.f33544c.a();
                    Object obj11 = message.obj;
                    n.d(obj11, "null cannot be cast to non-null type com.xiaomi.security.xsof.frauddetection.bean.AppInfo");
                    kVarA11.a1((io.b) obj11);
                    return;
                }
                return;
            }
            if (i10 == 902) {
                if (message.obj instanceof io.g) {
                    k kVarA12 = k.f33544c.a();
                    Object obj12 = message.obj;
                    n.d(obj12, "null cannot be cast to non-null type com.xiaomi.security.xsof.frauddetection.bean.ICallBack<kotlin.collections.MutableList<com.xiaomi.security.xsof.frauddetection.bean.AppInfo>?>");
                    kVarA12.J1((io.g) obj12);
                    return;
                }
                return;
            }
            switch (i10) {
                case 201:
                    if (message.obj instanceof io.j) {
                        k kVarA13 = k.f33544c.a();
                        Object obj13 = message.obj;
                        n.d(obj13, "null cannot be cast to non-null type com.xiaomi.security.xsof.frauddetection.bean.ScreenShareInfo");
                        kVarA13.f1((io.j) obj13);
                    }
                    break;
                case MiContinuityStatus.CLIENT_CHANNEL_INACTIVE /* 202 */:
                    if (message.obj instanceof io.j) {
                        k kVarA14 = k.f33544c.a();
                        Object obj14 = message.obj;
                        n.d(obj14, "null cannot be cast to non-null type com.xiaomi.security.xsof.frauddetection.bean.ScreenShareInfo");
                        kVarA14.c2((io.j) obj14);
                    }
                    break;
                case MiContinuityStatus.CLIENT_SOCKET_CLOSE /* 203 */:
                    if (message.obj instanceof io.g) {
                        k kVarA15 = k.f33544c.a();
                        Object obj15 = message.obj;
                        n.d(obj15, "null cannot be cast to non-null type com.xiaomi.security.xsof.frauddetection.bean.ICallBack<kotlin.collections.MutableList<com.xiaomi.security.xsof.frauddetection.bean.ScreenShareInfo>?>");
                        kVarA15.R1((io.g) obj15);
                    }
                    break;
                default:
                    switch (i10) {
                        case 701:
                            if (message.obj instanceof m) {
                                k kVarA16 = k.f33544c.a();
                                Object obj16 = message.obj;
                                n.d(obj16, "null cannot be cast to non-null type com.xiaomi.security.xsof.frauddetection.bean.SimCardFraudInfo");
                                kVarA16.j1((m) obj16);
                            }
                            break;
                        case 702:
                            k kVarA17 = k.f33544c.a();
                            Object obj17 = message.obj;
                            n.d(obj17, "null cannot be cast to non-null type kotlin.String");
                            kVarA17.e2((String) obj17);
                            break;
                        case 703:
                            k kVarA18 = k.f33544c.a();
                            Object obj18 = message.obj;
                            n.d(obj18, "null cannot be cast to non-null type com.xiaomi.security.xsof.frauddetection.bean.ICallBack<kotlin.collections.MutableList<com.xiaomi.security.xsof.frauddetection.bean.SimCardFraudInfo>?>");
                            kVarA18.V1((io.g) obj18, message.arg1);
                            break;
                        default:
                            switch (i10) {
                                case GameAIHttpResp.CODE_PARAM_ERROR /* 801 */:
                                    if (message.obj instanceof io.n) {
                                        k kVarA19 = k.f33544c.a();
                                        Object obj19 = message.obj;
                                        n.d(obj19, "null cannot be cast to non-null type com.xiaomi.security.xsof.frauddetection.bean.VoipRecordInfo");
                                        kVarA19.n1((io.n) obj19);
                                    }
                                    break;
                                case 802:
                                    if (message.obj instanceof io.n) {
                                        k kVarA20 = k.f33544c.a();
                                        Object obj20 = message.obj;
                                        n.d(obj20, "null cannot be cast to non-null type com.xiaomi.security.xsof.frauddetection.bean.VoipRecordInfo");
                                        kVarA20.g2((io.n) obj20, message.arg1);
                                    }
                                    break;
                                case 803:
                                    k kVarA21 = k.f33544c.a();
                                    Object obj21 = message.obj;
                                    n.d(obj21, "null cannot be cast to non-null type com.xiaomi.security.xsof.frauddetection.bean.ICallBack<kotlin.collections.MutableList<com.xiaomi.security.xsof.frauddetection.bean.VoipRecordInfo>?>");
                                    kVarA21.Y1((io.g) obj21, message.arg1);
                                    break;
                            }
                            break;
                    }
                    break;
            }
        }
    }

    static {
        String simpleName = k.class.getSimpleName();
        n.e(simpleName, "getSimpleName(...)");
        f33545d = simpleName;
        String str = "/data/misc/user/" + m2.F() + "/securitycenter/";
        f33546e = str;
        f33547f = str + "fraud_detection_data.db";
        f33548g = wo.i.b(l.SYNCHRONIZED, new kp.a() { // from class: jo.b
            @Override // kp.a
            public final Object invoke() {
                return k.o1();
            }
        });
    }

    private k() {
        super(com.miui.common.e.c(), f33547f, (SQLiteDatabase.CursorFactory) null, 1);
        HandlerThread handlerThread = new HandlerThread("FraudDetectionDBThread");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        n.e(looper, "getLooper(...)");
        this.f33549a = new b(looper);
        this.f33550b = new Handler(Looper.getMainLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void A1(final io.g gVar) {
        final List listY1 = y1();
        Handler handler = this.f33550b;
        if (handler != null) {
            handler.post(new Runnable() { // from class: jo.g
                @Override // java.lang.Runnable
                public final void run() {
                    k.B1(gVar, listY1);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void B1(io.g gVar, List list) {
        gVar.onSuccess(list);
    }

    private final List E1() {
        ArrayList arrayList = new ArrayList();
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            SQLiteDatabase readableDatabase = f33544c.a().getReadableDatabase();
            n.e(readableDatabase, "getReadableDatabase(...)");
            Cursor cursorRawQuery = null;
            try {
                try {
                    cursorRawQuery = readableDatabase.rawQuery(jo.a.f33526u.h("fraud_url", "access_time"), null);
                    while (cursorRawQuery.moveToNext()) {
                        io.f fVarS0 = S0(cursorRawQuery);
                        if (fVarS0 != null) {
                            arrayList.add(fVarS0);
                        }
                    }
                    oo.b.a(f33545d, "search fraud url list size = " + arrayList.size());
                    cursorRawQuery.close();
                    return arrayList;
                } catch (Exception e10) {
                    oo.b.b(f33545d, "searchFraudUrlList: " + e10.getMessage());
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                }
            } catch (Throwable th2) {
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                throw th2;
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void G1(final io.g gVar) {
        final List listE1 = E1();
        Handler handler = this.f33550b;
        if (handler != null) {
            handler.post(new Runnable() { // from class: jo.d
                @Override // java.lang.Runnable
                public final void run() {
                    k.H1(gVar, listE1);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void H1(io.g gVar, List list) {
        gVar.onSuccess(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void J1(final io.g gVar) {
        final List listX1 = x1();
        Handler handler = this.f33550b;
        if (handler != null) {
            handler.post(new Runnable() { // from class: jo.e
                @Override // java.lang.Runnable
                public final void run() {
                    k.K1(gVar, listX1);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void K1(io.g gVar, List list) {
        gVar.onSuccess(list);
    }

    private final List L1() {
        ArrayList arrayList = new ArrayList();
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            SQLiteDatabase readableDatabase = f33544c.a().getReadableDatabase();
            n.e(readableDatabase, "getReadableDatabase(...)");
            Cursor cursorRawQuery = null;
            try {
                try {
                    cursorRawQuery = readableDatabase.rawQuery("SELECT * FROM screen_share WHERE screen_share_start_time > " + (System.currentTimeMillis() - 259200000) + " ORDER BY screen_share_start_time DESC", null);
                    while (cursorRawQuery.moveToNext()) {
                        io.j jVarT0 = T0(cursorRawQuery);
                        if (jVarT0 != null) {
                            arrayList.add(jVarT0);
                        }
                    }
                    oo.b.a(f33545d, "search screenShare size = " + arrayList.size());
                    cursorRawQuery.close();
                    return arrayList;
                } catch (Exception e10) {
                    oo.b.b(f33545d, "searchScreenShare: " + e10.getMessage());
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return arrayList;
                }
            } catch (Throwable th2) {
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                throw th2;
            }
        }
        return arrayList;
    }

    private final ContentValues M0(io.a aVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("airplane_mode_on", Integer.valueOf(aVar.f() ? 1 : 0));
        contentValues.put("airplane_mode_change_time", Long.valueOf(aVar.e()));
        return contentValues;
    }

    private final ContentValues N0(io.b bVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("pkg_name", bVar.h());
        contentValues.put("app_name", bVar.g());
        contentValues.put("version_code", bVar.l());
        contentValues.put("md5", bVar.f());
        contentValues.put("install_finish_time", Long.valueOf(bVar.k()));
        contentValues.put("app_tag_id", bVar.e());
        contentValues.put("security_tag_id", bVar.i());
        contentValues.put(SaveAreaResult.Columns.source, bVar.j());
        return contentValues;
    }

    private final List N1() {
        ArrayList arrayList = new ArrayList();
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            SQLiteDatabase readableDatabase = f33544c.a().getReadableDatabase();
            n.e(readableDatabase, "getReadableDatabase(...)");
            Cursor cursorRawQuery = null;
            try {
                try {
                    cursorRawQuery = readableDatabase.rawQuery(jo.a.f33526u.h("screenshot", "screenshort_time"), null);
                    while (cursorRawQuery.moveToNext()) {
                        arrayList.add(U0(cursorRawQuery));
                    }
                    oo.b.a(f33545d, "search screenshots size = " + arrayList.size());
                    cursorRawQuery.close();
                    return arrayList;
                } catch (Exception e10) {
                    oo.b.b(f33545d, "searchScreenshots: " + e10.getMessage());
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                }
            } catch (Throwable th2) {
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                throw th2;
            }
        }
        return arrayList;
    }

    private final ContentValues O0(io.d dVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(com.xiaomi.onetrack.h.b.a.f27989g, dVar.g());
        contentValues.put("enabled", Integer.valueOf(dVar.f()));
        contentValues.put("timestamp", Long.valueOf(dVar.h()));
        return contentValues;
    }

    private final io.a P0(Cursor cursor) {
        try {
            long j10 = cursor.getLong(cursor.getColumnIndex("airplane_mode_change_time"));
            boolean z10 = true;
            if (cursor.getInt(cursor.getColumnIndex("airplane_mode_on")) != 1) {
                z10 = false;
            }
            return new io.a(z10, j10);
        } catch (Exception e10) {
            oo.b.b(f33545d, "cursor2AirplaneModeInfo: " + e10.getMessage());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void P1(final io.g gVar) {
        final List listN1 = N1();
        Handler handler = this.f33550b;
        if (handler != null) {
            handler.post(new Runnable() { // from class: jo.h
                @Override // java.lang.Runnable
                public final void run() {
                    k.Q1(gVar, listN1);
                }
            });
        }
    }

    private final io.b Q0(Cursor cursor) {
        try {
            String string = cursor.getString(cursor.getColumnIndex("app_name"));
            String string2 = cursor.getString(cursor.getColumnIndex("pkg_name"));
            int i10 = cursor.getInt(cursor.getColumnIndex("version_code"));
            return new io.b(string2, string, Integer.valueOf(i10), cursor.getString(cursor.getColumnIndex("md5")), cursor.getLong(cursor.getColumnIndex("install_finish_time")), Long.valueOf(cursor.getLong(cursor.getColumnIndex("app_tag_id"))), Integer.valueOf(cursor.getInt(cursor.getColumnIndex("security_tag_id"))), cursor.getString(cursor.getColumnIndex(SaveAreaResult.Columns.source)));
        } catch (Exception e10) {
            oo.b.b(f33545d, "cursor2AppInfo: " + e10.getMessage());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void Q1(io.g gVar, List list) {
        gVar.onSuccess(list);
    }

    private final io.d R0(Cursor cursor) {
        try {
            return new io.d(Integer.valueOf(cursor.getInt(cursor.getColumnIndex(com.xiaomi.onetrack.h.b.a.f27989g))), cursor.getInt(cursor.getColumnIndex("enabled")), cursor.getLong(cursor.getColumnIndex("timestamp")));
        } catch (Exception e10) {
            oo.b.b(f33545d, "cursor2CallForwardingInfo: " + e10.getMessage());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void R1(final io.g gVar) {
        final List listL1 = L1();
        Handler handler = this.f33550b;
        if (handler != null) {
            handler.post(new Runnable() { // from class: jo.f
                @Override // java.lang.Runnable
                public final void run() {
                    k.S1(gVar, listL1);
                }
            });
        }
    }

    private final io.f S0(Cursor cursor) {
        try {
            return new io.f(cursor.getLong(cursor.getColumnIndex("access_time")), cursor.getInt(cursor.getColumnIndex("url_type")));
        } catch (Exception e10) {
            oo.b.b(f33545d, "cursor2FraudUrlInfo: " + e10.getMessage());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void S1(io.g gVar, List list) {
        gVar.onSuccess(list);
    }

    private final io.j T0(Cursor cursor) {
        try {
            String string = cursor.getString(cursor.getColumnIndex("screen_share_package_name"));
            long j10 = cursor.getLong(cursor.getColumnIndex("screen_share_start_time"));
            long j11 = cursor.getLong(cursor.getColumnIndex("screen_share_end_time"));
            boolean z10 = cursor.getInt(cursor.getColumnIndex("is_active")) == 1;
            long j12 = cursor.getLong(cursor.getColumnIndex("duration"));
            n.c(string);
            return new io.j(string, j10, j11, j12, z10);
        } catch (Exception e10) {
            oo.b.b(f33545d, "cursor2ScreenShareInfo: " + e10.getMessage());
            return null;
        }
    }

    private final List T1(int i10) {
        String strD;
        ArrayList arrayList = new ArrayList();
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            SQLiteDatabase readableDatabase = f33544c.a().getReadableDatabase();
            n.e(readableDatabase, "getReadableDatabase(...)");
            if (i10 == io.l.TYPE_ALL_CALL.b()) {
                strD = jo.a.f33526u.a();
            } else if (i10 == io.l.TYPE_FRAUD_CALL.b()) {
                strD = jo.a.f33526u.b();
            } else if (i10 == io.l.TYPE_OVERSEAS_CALL.b()) {
                strD = jo.a.f33526u.c();
            } else {
                if (i10 != io.l.TYPE_VIRTUAL_OPERATOR_CALL.b()) {
                    oo.b.b(f33545d, "searchSimCardFraud: searchType error");
                    return arrayList;
                }
                strD = jo.a.f33526u.d();
            }
            Cursor cursorRawQuery = null;
            try {
                try {
                    cursorRawQuery = readableDatabase.rawQuery(strD, null);
                    while (cursorRawQuery.moveToNext()) {
                        m mVarV0 = V0(cursorRawQuery);
                        if (mVarV0 != null) {
                            arrayList.add(mVarV0);
                        }
                    }
                    oo.b.a(f33545d, "search sim card fraud size = " + arrayList.size());
                    cursorRawQuery.close();
                    return arrayList;
                } catch (Exception e10) {
                    oo.b.b(f33545d, "searchSimCardFraud: " + e10.getMessage());
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                }
            } catch (Throwable th2) {
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                throw th2;
            }
        }
        return arrayList;
    }

    private final io.k U0(Cursor cursor) {
        return new io.k(cursor.getLong(cursor.getColumnIndex("screenshort_time")));
    }

    private final m V0(Cursor cursor) {
        try {
            String string = cursor.getString(cursor.getColumnIndex("tel_number_md5"));
            long j10 = cursor.getLong(cursor.getColumnIndex("call_start_time"));
            long j11 = cursor.getLong(cursor.getColumnIndex("call_end_time"));
            String string2 = cursor.getString(cursor.getColumnIndex("tel_location"));
            String string3 = cursor.getString(cursor.getColumnIndex("operator"));
            boolean z10 = cursor.getInt(cursor.getColumnIndex("is_chinese_number")) == 1;
            boolean z11 = cursor.getInt(cursor.getColumnIndex("is_virtual_operator")) == 1;
            boolean z12 = cursor.getInt(cursor.getColumnIndex("is_active")) == 1;
            long j12 = cursor.getLong(cursor.getColumnIndex("duration"));
            boolean z13 = cursor.getInt(cursor.getColumnIndex("is_fraud_call")) == 1;
            n.c(string);
            return new m(string, j10, j11, string2, string3, z10, z11, z12, j12, z13);
        } catch (Exception e10) {
            oo.b.b(f33545d, "cursor2SimCardFraudInfo: " + e10.getMessage());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void V1(final io.g gVar, int i10) {
        final List listT1 = T1(i10);
        Handler handler = this.f33550b;
        if (handler != null) {
            handler.post(new Runnable() { // from class: jo.i
                @Override // java.lang.Runnable
                public final void run() {
                    k.W1(gVar, listT1);
                }
            });
        }
    }

    private final io.n W0(Cursor cursor) {
        try {
            String string = cursor.getString(cursor.getColumnIndex("voip_record_package_name"));
            n.e(string, "getString(...)");
            return new io.n(string, cursor.getLong(cursor.getColumnIndex("voip_record_start_time")), cursor.getLong(cursor.getColumnIndex("voip_record_end_time")), cursor.getLong(cursor.getColumnIndex("duration")), cursor.getInt(cursor.getColumnIndex("is_active")) == 1, cursor.getInt(cursor.getColumnIndex("is_fraud_call")) == 1);
        } catch (Exception e10) {
            oo.b.b(f33545d, "cursor2VoipRecordInfo::" + e10.getMessage());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void W1(io.g gVar, List list) {
        gVar.onSuccess(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void Y0(io.a aVar) {
        try {
            String str = f33545d;
            oo.b.a(str, "insertAirplaneModeInfoInHandlerThread: " + aVar + " ");
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                return;
            }
            oo.b.a(str, "insert insertAirplaneModeInfoInHandlerThread result = " + f33544c.a().getWritableDatabase().insert("airplane_mode", null, M0(aVar)));
            q1("airplane_mode", "airplane_mode_change_time");
        } catch (Exception e10) {
            oo.b.b(f33545d, "insertAirplaneModeInfoInHandlerThread: " + e10.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void Y1(final io.g gVar, int i10) {
        final List listA2 = a2(i10);
        Handler handler = this.f33550b;
        if (handler != null) {
            handler.post(new Runnable() { // from class: jo.c
                @Override // java.lang.Runnable
                public final void run() {
                    k.Z1(gVar, listA2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void Z1(io.g gVar, List list) {
        gVar.onSuccess(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a1(io.b bVar) {
        try {
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                return;
            }
            long jInsert = f33544c.a().getWritableDatabase().insert("install_app", null, N0(bVar));
            oo.b.a(f33545d, "insert appInfo result = " + jInsert);
            q1("install_app", "install_finish_time");
        } catch (Exception e10) {
            oo.b.b(f33545d, "insertAppInfoData: " + e10.getMessage());
        }
    }

    private final List a2(int i10) {
        String strG;
        ArrayList arrayList = new ArrayList();
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            SQLiteDatabase readableDatabase = f33544c.a().getReadableDatabase();
            n.e(readableDatabase, "getReadableDatabase(...)");
            if (i10 == o.TYPE_ALL_CALL.b()) {
                strG = jo.a.f33526u.e();
            } else {
                if (i10 != o.TYPE_FRAUD_CALL.b()) {
                    oo.b.b(f33545d, "searchVoipRecordList::searchType is errorType");
                    return arrayList;
                }
                strG = jo.a.f33526u.g();
            }
            Cursor cursorRawQuery = null;
            try {
                try {
                    cursorRawQuery = readableDatabase.rawQuery(strG, null);
                    while (cursorRawQuery.moveToNext()) {
                        io.n nVarW0 = W0(cursorRawQuery);
                        if (nVarW0 != null) {
                            arrayList.add(nVarW0);
                        }
                    }
                    oo.b.a(f33545d, "search voip record list size = " + arrayList.size());
                    cursorRawQuery.close();
                    return arrayList;
                } catch (Exception e10) {
                    oo.b.b(f33545d, "searchVoipRecordList::" + e10.getMessage());
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                }
            } catch (Throwable th2) {
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                throw th2;
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void c1(io.d dVar) {
        try {
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                return;
            }
            long jInsert = f33544c.a().getWritableDatabase().insert("call_forwarding", null, O0(dVar));
            oo.b.a(f33545d, "insert callForwardingInfo result = " + jInsert);
            q1("call_forwarding", "timestamp");
        } catch (Exception e10) {
            oo.b.b(f33545d, "insertCallForwardingInfoInHandlerThread: " + e10.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void c2(io.j jVar) {
        String str = f33545d;
        oo.b.a(str, "updateScreenShareWhenFinishedInHandlerThread: " + jVar + " ");
        if (n.b(Looper.myLooper(), Looper.getMainLooper())) {
            return;
        }
        String[] strArr = {jVar.h(), "1"};
        SQLiteDatabase writableDatabase = f33544c.a().getWritableDatabase();
        n.e(writableDatabase, "getWritableDatabase(...)");
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = writableDatabase.query("screen_share", null, "screen_share_package_name = ? AND is_active = ?", strArr, null, null, "screen_share_start_time DESC");
                if (cursorQuery.moveToNext()) {
                    long j10 = cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("screen_share_start_time"));
                    if (j10 > jVar.g()) {
                        cursorQuery.close();
                        return;
                    }
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("screen_share_end_time", Long.valueOf(jVar.g()));
                    contentValues.put("is_active", (Integer) 0);
                    contentValues.put("duration", Long.valueOf(jVar.g() - j10));
                    oo.b.a(str, "updateScreenShareEndTime::result = " + writableDatabase.update("screen_share", contentValues, "screen_share_package_name = ? AND is_active = ?", strArr));
                }
                cursorQuery.close();
            } catch (Exception e10) {
                oo.b.b(f33545d, "updateScreenShareWhenFinishedInHandlerThread: " + e10.getMessage());
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            }
        } catch (Throwable th2) {
            if (cursorQuery == null) {
                throw th2;
            }
            cursorQuery.close();
            throw th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void e2(String str) {
        String str2 = f33545d;
        oo.b.a(str2, "updateSimCardFraudStateWhenFinishedInHandlerThread: " + str + " ");
        if (n.b(Looper.myLooper(), Looper.getMainLooper())) {
            return;
        }
        String[] strArr = {str, "1"};
        SQLiteDatabase writableDatabase = f33544c.a().getWritableDatabase();
        n.e(writableDatabase, "getWritableDatabase(...)");
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = writableDatabase.query("fraud_phone", null, "tel_number_md5 = ? AND is_active = ?", strArr, null, null, "call_start_time DESC");
                if (cursorQuery.moveToNext()) {
                    long j10 = cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("call_start_time"));
                    ContentValues contentValues = new ContentValues();
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    contentValues.put("call_end_time", Long.valueOf(jCurrentTimeMillis));
                    contentValues.put("is_active", (Integer) 0);
                    contentValues.put("duration", Long.valueOf(jCurrentTimeMillis - j10));
                    oo.b.a(str2, "updateSimCardFraudStateWhenFinished::result = " + writableDatabase.update("fraud_phone", contentValues, "tel_number_md5 = ? AND is_active = ?", strArr));
                }
                cursorQuery.close();
            } catch (Exception e10) {
                oo.b.b(f33545d, "updateSimCardFraudStateWhenFinishedInHandlerThread: " + e10.getMessage());
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            }
        } catch (Throwable th2) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void f1(io.j jVar) {
        try {
            String str = f33545d;
            oo.b.a(str, "insertScreenShareInHandlerThread: " + jVar + " ");
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                return;
            }
            oo.b.a(str, "insert screen share = " + f33544c.a().getWritableDatabase().insert("screen_share", null, r1(jVar)));
            q1("screen_share", "screen_share_start_time");
        } catch (Exception e10) {
            oo.b.b(f33545d, "insertScreenShareData: " + e10.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void g2(io.n nVar, int i10) {
        String str = f33545d;
        oo.b.a(str, "updateVoipRecordWhenFinishedInHandlerThread: " + nVar + " ");
        if (n.b(Looper.myLooper(), Looper.getMainLooper())) {
            return;
        }
        String[] strArr = {nVar.h(), "1"};
        SQLiteDatabase writableDatabase = f33544c.a().getWritableDatabase();
        n.e(writableDatabase, "getWritableDatabase(...)");
        Cursor cursor = null;
        try {
            try {
                Cursor cursorQuery = writableDatabase.query("voip_record", null, "voip_record_package_name = ? AND is_active = ?", strArr, null, null, "voip_record_start_time DESC");
                if (cursorQuery.moveToNext()) {
                    long j10 = cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("voip_record_start_time"));
                    if (j10 > nVar.g()) {
                        cursorQuery.close();
                        return;
                    }
                    ContentValues contentValues = new ContentValues();
                    if (i10 == p.TYPE_CHECK_IS_FRAUD_CALL.b()) {
                        contentValues.put("is_fraud_call", Integer.valueOf(nVar.j() ? 1 : 0));
                    } else if (i10 != p.TYPE_END_CALL.b()) {
                        oo.b.b(str, "updateVoipRecordEndTime::updateType is errorType");
                        cursorQuery.close();
                        return;
                    } else {
                        contentValues.put("voip_record_end_time", Long.valueOf(nVar.g()));
                        contentValues.put("is_active", (Integer) 0);
                        contentValues.put("duration", Long.valueOf(nVar.g() - j10));
                    }
                    oo.b.a(str, "updateVoipRecordEndTime::updateType = " + i10 + ", result = " + writableDatabase.update("voip_record", contentValues, "voip_record_package_name = ? AND is_active = ?", strArr));
                }
                cursorQuery.close();
            } catch (Exception e10) {
                oo.b.b(f33545d, "updateVoipRecordEndTime::" + e10.getMessage());
                if (0 != 0) {
                    cursor.close();
                }
            }
        } catch (Throwable th2) {
            if (0 == 0) {
                throw th2;
            }
            cursor.close();
            throw th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void h1(io.k kVar) {
        try {
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                return;
            }
            long jInsert = f33544c.a().getWritableDatabase().insert("screenshot", null, s1(kVar));
            oo.b.a(f33545d, "insert screenshot = " + jInsert);
            q1("screenshot", "screenshort_time");
        } catch (Exception e10) {
            oo.b.b(f33545d, "insertScreenshotData: " + e10.getMessage());
        }
    }

    private final ContentValues h2(io.f fVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("access_time", Long.valueOf(fVar.e()));
        contentValues.put("url_type", Integer.valueOf(fVar.f()));
        return contentValues;
    }

    private final ContentValues i2(io.n nVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("voip_record_package_name", nVar.h());
        contentValues.put("voip_record_start_time", Long.valueOf(nVar.i()));
        contentValues.put("voip_record_end_time", Long.valueOf(nVar.g()));
        contentValues.put("is_active", Integer.valueOf(nVar.e() ? 1 : 0));
        contentValues.put("is_fraud_call", Integer.valueOf(nVar.j() ? 1 : 0));
        contentValues.put("duration", Long.valueOf(nVar.f()));
        return contentValues;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void j1(m mVar) {
        try {
            String str = f33545d;
            oo.b.a(str, "insertSimCardFraudCallInHandlerThread: " + mVar + " ");
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                Log.i(str, "insertSimCardFraudCallInHandlerThread: thread in main, reject");
                return;
            }
            oo.b.a(str, "insert insertPhoneNumberFraudInHandlerThread = " + f33544c.a().getWritableDatabase().insert("fraud_phone", null, p1(mVar)));
            q1("fraud_phone", "call_start_time");
        } catch (Exception e10) {
            oo.b.b(f33545d, "insertSimCardFraudCallInHandlerThread: " + e10.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void l1(io.f fVar) {
        try {
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                return;
            }
            long jInsert = f33544c.a().getWritableDatabase().insert("fraud_url", null, h2(fVar));
            oo.b.a(f33545d, "insert urlInfo result = " + jInsert);
            q1("fraud_url", "access_time");
        } catch (Exception e10) {
            oo.b.b(f33545d, "insertUrlInfoInHandlerThread: " + e10.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void n1(io.n nVar) {
        try {
            String str = f33545d;
            oo.b.a(str, "insertVoipRecordInHandlerThread: " + nVar + " ");
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                return;
            }
            oo.b.a(str, "insert voip record result = " + f33544c.a().getWritableDatabase().insert("voip_record", null, i2(nVar)));
            q1("voip_record", "voip_record_start_time");
        } catch (Exception e10) {
            oo.b.b(f33545d, "insertVoipRecordInHandlerThread: " + e10.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final k o1() {
        return new k();
    }

    private final ContentValues p1(m mVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("tel_number_md5", mVar.k());
        contentValues.put("call_start_time", Long.valueOf(mVar.i()));
        contentValues.put("call_end_time", Long.valueOf(mVar.g()));
        contentValues.put("tel_location", mVar.j());
        contentValues.put("operator", mVar.h());
        contentValues.put("is_chinese_number", Integer.valueOf(mVar.l() ? 1 : 0));
        contentValues.put("is_virtual_operator", Integer.valueOf(mVar.n() ? 1 : 0));
        contentValues.put("is_active", Integer.valueOf(mVar.e() ? 1 : 0));
        contentValues.put("duration", Long.valueOf(mVar.f()));
        contentValues.put("is_fraud_call", Integer.valueOf(mVar.m() ? 1 : 0));
        return contentValues;
    }

    private final ContentValues r1(io.j jVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("screen_share_package_name", jVar.h());
        contentValues.put("screen_share_start_time", Long.valueOf(jVar.i()));
        contentValues.put("screen_share_end_time", Long.valueOf(jVar.g()));
        contentValues.put("is_active", Integer.valueOf(jVar.e() ? 1 : 0));
        contentValues.put("duration", Long.valueOf(jVar.f()));
        return contentValues;
    }

    private final ContentValues s1(io.k kVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("screenshort_time", Long.valueOf(kVar.e()));
        return contentValues;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void u1(final io.g gVar) {
        final List listW1 = w1();
        Handler handler = this.f33550b;
        if (handler != null) {
            handler.post(new Runnable() { // from class: jo.j
                @Override // java.lang.Runnable
                public final void run() {
                    k.v1(gVar, listW1);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void v1(io.g gVar, List list) {
        gVar.onSuccess(list);
    }

    private final List w1() {
        ArrayList arrayList = new ArrayList();
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            SQLiteDatabase readableDatabase = f33544c.a().getReadableDatabase();
            n.e(readableDatabase, "getReadableDatabase(...)");
            Cursor cursorRawQuery = null;
            try {
                try {
                    cursorRawQuery = readableDatabase.rawQuery(jo.a.f33526u.h("airplane_mode", "airplane_mode_change_time"), null);
                    while (cursorRawQuery.moveToNext()) {
                        io.a aVarP0 = P0(cursorRawQuery);
                        if (aVarP0 != null) {
                            arrayList.add(aVarP0);
                        }
                    }
                    oo.b.a(f33545d, "search airplaneMode size = " + arrayList.size());
                    cursorRawQuery.close();
                    return arrayList;
                } catch (Exception e10) {
                    oo.b.b(f33545d, "searchAirplaneModes: " + e10.getMessage());
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                }
            } catch (Throwable th2) {
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                throw th2;
            }
        }
        return arrayList;
    }

    private final List x1() {
        ArrayList arrayList = new ArrayList();
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            SQLiteDatabase readableDatabase = f33544c.a().getReadableDatabase();
            n.e(readableDatabase, "getReadableDatabase(...)");
            Cursor cursorRawQuery = null;
            try {
                try {
                    cursorRawQuery = readableDatabase.rawQuery(jo.a.f33526u.h("install_app", "install_finish_time"), null);
                    while (cursorRawQuery.moveToNext()) {
                        io.b bVarQ0 = Q0(cursorRawQuery);
                        if (bVarQ0 != null) {
                            arrayList.add(bVarQ0);
                        }
                    }
                    oo.b.a(f33545d, "search app info list size = " + arrayList.size());
                    cursorRawQuery.close();
                    return arrayList;
                } catch (Exception e10) {
                    oo.b.b(f33545d, "searchAppInfoList: " + e10.getMessage());
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                }
            } catch (Throwable th2) {
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                throw th2;
            }
        }
        return arrayList;
    }

    private final List y1() {
        ArrayList arrayList = new ArrayList();
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            SQLiteDatabase readableDatabase = f33544c.a().getReadableDatabase();
            n.e(readableDatabase, "getReadableDatabase(...)");
            Cursor cursorRawQuery = null;
            try {
                try {
                    cursorRawQuery = readableDatabase.rawQuery(jo.a.f33526u.f(), null);
                    while (cursorRawQuery.moveToNext()) {
                        io.d dVarR0 = R0(cursorRawQuery);
                        if (dVarR0 != null) {
                            arrayList.add(dVarR0);
                        }
                    }
                    oo.b.a(f33545d, "search callForwarding size = " + arrayList.size());
                    cursorRawQuery.close();
                    return arrayList;
                } catch (Exception e10) {
                    oo.b.b(f33545d, "searchCallForwarding: " + e10.getMessage());
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return arrayList;
                }
            } catch (Throwable th2) {
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                throw th2;
            }
        }
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x007d  */
    public final m C1() throws Throwable {
        Cursor cursorRawQuery;
        Cursor cursor = null;
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            oo.b.a(f33545d, "searchCurrentSimCardRecord: thread in main, reject");
            return null;
        }
        SQLiteDatabase readableDatabase = f33544c.a().getReadableDatabase();
        n.e(readableDatabase, "getReadableDatabase(...)");
        try {
            cursorRawQuery = readableDatabase.rawQuery("SELECT * FROM fraud_phone WHERE is_active = 1 ORDER BY call_start_time DESC LIMIT 1 ", null);
            try {
                try {
                    m mVarV0 = cursorRawQuery.moveToFirst() ? V0(cursorRawQuery) : null;
                    oo.b.a(f33545d, "search current simCardRecord info = " + mVarV0);
                    cursorRawQuery.close();
                    return mVarV0;
                } catch (Exception e10) {
                    e = e10;
                    oo.b.b(f33545d, "searchCurrentSimCardRecord: " + e.getMessage());
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
                cursor = cursorRawQuery;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Exception e11) {
            e = e11;
            cursorRawQuery = null;
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:28:0x007d  */
    public final io.n D1() throws Throwable {
        Cursor cursorRawQuery;
        Cursor cursor = null;
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            oo.b.a(f33545d, "searchCurrentVoipRecord: thread in main, reject");
            return null;
        }
        SQLiteDatabase readableDatabase = f33544c.a().getReadableDatabase();
        n.e(readableDatabase, "getReadableDatabase(...)");
        try {
            cursorRawQuery = readableDatabase.rawQuery("SELECT * FROM voip_record WHERE is_active = 1 ORDER BY voip_record_start_time DESC LIMIT 1 ", null);
            try {
                try {
                    io.n nVarW0 = cursorRawQuery.moveToFirst() ? W0(cursorRawQuery) : null;
                    oo.b.a(f33545d, "search current voip info = " + nVarW0);
                    cursorRawQuery.close();
                    return nVarW0;
                } catch (Exception e10) {
                    e = e10;
                    oo.b.b(f33545d, "searchCurrentVoipRecord: " + e.getMessage());
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
                cursor = cursorRawQuery;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Exception e11) {
            e = e11;
            cursorRawQuery = null;
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public final void F1(io.g gVar) {
        n.f(gVar, "callBack");
        Message messageObtain = Message.obtain();
        messageObtain.what = 402;
        messageObtain.obj = gVar;
        b bVar = this.f33549a;
        if (bVar != null) {
            bVar.sendMessage(messageObtain);
        }
    }

    public final void I1(io.g gVar) {
        n.f(gVar, "callBack");
        Message messageObtain = Message.obtain();
        messageObtain.what = 302;
        messageObtain.obj = gVar;
        b bVar = this.f33549a;
        if (bVar != null) {
            bVar.sendMessage(messageObtain);
        }
    }

    public final void M1(io.g gVar) {
        n.f(gVar, "callBack");
        Message messageObtain = Message.obtain();
        messageObtain.what = MiContinuityStatus.CLIENT_SOCKET_CLOSE;
        messageObtain.obj = gVar;
        b bVar = this.f33549a;
        if (bVar != null) {
            bVar.sendMessage(messageObtain);
        }
    }

    public final void O1(io.g gVar) {
        n.f(gVar, "callBack");
        Message messageObtain = Message.obtain();
        messageObtain.what = 102;
        messageObtain.obj = gVar;
        b bVar = this.f33549a;
        if (bVar != null) {
            bVar.sendMessage(messageObtain);
        }
    }

    public final void U1(io.g gVar, io.l lVar) {
        n.f(gVar, "callBack");
        n.f(lVar, "searchType");
        Message messageObtain = Message.obtain();
        messageObtain.what = 703;
        messageObtain.obj = gVar;
        messageObtain.arg1 = lVar.b();
        b bVar = this.f33549a;
        if (bVar != null) {
            bVar.sendMessage(messageObtain);
        }
    }

    public final void X0(io.a aVar) {
        n.f(aVar, "info");
        Message messageObtain = Message.obtain();
        messageObtain.what = 601;
        messageObtain.obj = aVar;
        b bVar = this.f33549a;
        if (bVar != null) {
            bVar.sendMessage(messageObtain);
        }
    }

    public final void X1(io.g gVar, o oVar) {
        n.f(gVar, "callBack");
        n.f(oVar, "searchType");
        Message messageObtain = Message.obtain();
        messageObtain.what = 803;
        messageObtain.obj = gVar;
        messageObtain.arg1 = oVar.b();
        b bVar = this.f33549a;
        if (bVar != null) {
            bVar.sendMessage(messageObtain);
        }
    }

    public final void Z0(io.b bVar) {
        n.f(bVar, "appInfo");
        Message messageObtain = Message.obtain();
        messageObtain.what = 301;
        messageObtain.obj = bVar;
        b bVar2 = this.f33549a;
        if (bVar2 != null) {
            bVar2.sendMessage(messageObtain);
        }
    }

    public final void b1(io.d dVar) {
        n.f(dVar, "callForwardingInfo");
        Message messageObtain = Message.obtain();
        messageObtain.what = MiContinuityStatus.SOCKET_CHANNEL_EXCEPTION;
        messageObtain.obj = dVar;
        b bVar = this.f33549a;
        if (bVar != null) {
            bVar.sendMessage(messageObtain);
        }
    }

    public final void b2(io.j jVar) {
        n.f(jVar, "info");
        Message messageObtain = Message.obtain();
        messageObtain.what = MiContinuityStatus.CLIENT_CHANNEL_INACTIVE;
        messageObtain.obj = jVar;
        b bVar = this.f33549a;
        if (bVar != null) {
            bVar.sendMessage(messageObtain);
        }
    }

    public final void d1(io.f fVar) {
        n.f(fVar, "urlInfo");
        Message messageObtain = Message.obtain();
        messageObtain.what = com.xiaomi.onetrack.g.d.f27959b;
        messageObtain.obj = fVar;
        b bVar = this.f33549a;
        if (bVar != null) {
            bVar.sendMessage(messageObtain);
        }
    }

    public final void d2(String str) {
        n.f(str, "number");
        Message messageObtain = Message.obtain();
        messageObtain.what = 702;
        messageObtain.obj = str;
        b bVar = this.f33549a;
        if (bVar != null) {
            bVar.sendMessage(messageObtain);
        }
    }

    public final void e1(io.j jVar) {
        n.f(jVar, "info");
        Message messageObtain = Message.obtain();
        messageObtain.what = 201;
        messageObtain.obj = jVar;
        b bVar = this.f33549a;
        if (bVar != null) {
            bVar.sendMessage(messageObtain);
        }
    }

    public final void f2(io.n nVar, p pVar) {
        n.f(nVar, "info");
        n.f(pVar, "updateType");
        Message messageObtain = Message.obtain();
        messageObtain.what = 802;
        messageObtain.obj = nVar;
        messageObtain.arg1 = pVar.b();
        b bVar = this.f33549a;
        if (bVar != null) {
            bVar.sendMessage(messageObtain);
        }
    }

    public final void g1(io.k kVar) {
        if (kVar == null) {
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = 101;
        messageObtain.obj = kVar;
        b bVar = this.f33549a;
        if (bVar != null) {
            bVar.sendMessage(messageObtain);
        }
    }

    public final void i1(m mVar) {
        n.f(mVar, "info");
        Message messageObtain = Message.obtain();
        messageObtain.what = 701;
        messageObtain.obj = mVar;
        b bVar = this.f33549a;
        if (bVar != null) {
            bVar.sendMessage(messageObtain);
        }
    }

    public final void k1(io.b bVar) {
        n.f(bVar, "info");
        Message messageObtain = Message.obtain();
        messageObtain.what = 901;
        messageObtain.obj = bVar;
        b bVar2 = this.f33549a;
        if (bVar2 != null) {
            bVar2.sendMessage(messageObtain);
        }
    }

    public final void m1(io.n nVar) {
        n.f(nVar, "info");
        Message messageObtain = Message.obtain();
        messageObtain.what = GameAIHttpResp.CODE_PARAM_ERROR;
        messageObtain.obj = nVar;
        b bVar = this.f33549a;
        if (bVar != null) {
            bVar.sendMessage(messageObtain);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        n.f(sQLiteDatabase, "db");
        oo.b.c(f33545d, "db onCreate");
        try {
            sQLiteDatabase.execSQL("CREATE TABLE screenshot(_id INTEGER PRIMARY KEY AUTOINCREMENT,screenshort_time INTEGER NOT NULL);");
            sQLiteDatabase.execSQL("CREATE TABLE screen_share(_id INTEGER PRIMARY KEY AUTOINCREMENT,screen_share_package_name TEXT NOT NULL,screen_share_start_time INTEGER NOT NULL,screen_share_end_time INTEGER NOT NULL,is_active INTEGER NOT NULL,duration INTEGER NOT NULL);");
            sQLiteDatabase.execSQL("CREATE TABLE call_forwarding(_id INTEGER PRIMARY KEY AUTOINCREMENT,reason INTEGER NOT NULL,enabled INTEGER NOT NULL,timestamp INTEGER NOT NULL);");
            sQLiteDatabase.execSQL("CREATE TABLE airplane_mode(_id INTEGER PRIMARY KEY AUTOINCREMENT,airplane_mode_on INTEGER NOT NULL,airplane_mode_change_time INTEGER NOT NULL);");
            sQLiteDatabase.execSQL("CREATE TABLE fraud_phone(_id INTEGER PRIMARY KEY AUTOINCREMENT,call_start_time INTEGER NOT NULL,call_end_time INTEGER NOT NULL,is_fraud_call INTEGER NOT NULL,tel_location TEXT NOT NULL,tel_number_md5 TEXT NOT NULL,operator TEXT NOT NULL,is_chinese_number INTEGER NOT NULL,is_virtual_operator INTEGER NOT NULL,is_active INTEGER NOT NULL,duration INTEGER NOT NULL);");
            sQLiteDatabase.execSQL("CREATE TABLE voip_record(_id INTEGER PRIMARY KEY AUTOINCREMENT,voip_record_package_name TEXT NOT NULL,voip_record_start_time INTEGER NOT NULL,voip_record_end_time INTEGER NOT NULL,is_active INTEGER NOT NULL,is_fraud_call INTEGER NOT NULL,duration INTEGER NOT NULL);");
            sQLiteDatabase.execSQL("CREATE TABLE fraud_url(_id INTEGER PRIMARY KEY AUTOINCREMENT,access_time INTEGER NOT NULL,url_type INTEGER NOT NULL);");
            sQLiteDatabase.execSQL("CREATE TABLE install_app(_id INTEGER PRIMARY KEY AUTOINCREMENT,pkg_name TEXT NOT NULL,app_name TEXT NOT NULL,source TEXT NOT NULL,version_code INTEGER NOT NULL,md5 TEXT NOT NULL,install_finish_time INTEGER NOT NULL,app_tag_id INTEGER NOT NULL,security_tag_id INTEGER NOT NULL);");
        } catch (Exception e10) {
            oo.b.b(f33545d, "onCreate error: " + e10.getMessage());
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i10, int i11) {
        oo.b.c(f33545d, "onUpgrade: " + i10 + " ---> " + i11);
    }

    public final void q1(String str, String str2) {
        n.f(str, "tableName");
        n.f(str2, "timeColumn");
        try {
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis() - 259200000;
            if (jCurrentTimeMillis < 0) {
                return;
            }
            String[] strArr = {String.valueOf(jCurrentTimeMillis)};
            SQLiteDatabase writableDatabase = f33544c.a().getWritableDatabase();
            n.e(writableDatabase, "getWritableDatabase(...)");
            int iDelete = writableDatabase.delete(str, str2 + " < ?", strArr);
            oo.b.a(f33545d, "removeOldInfos " + str + " , result = " + iDelete);
        } catch (Exception e10) {
            oo.b.b(f33545d, "removeOldInfos " + str + " , error: " + e10.getMessage());
        }
    }

    public final void t1(io.g gVar) {
        n.f(gVar, "callBack");
        Message messageObtain = Message.obtain();
        messageObtain.what = 602;
        messageObtain.obj = gVar;
        b bVar = this.f33549a;
        if (bVar != null) {
            bVar.sendMessage(messageObtain);
        }
    }

    public final void z1(io.g gVar) {
        n.f(gVar, "callBack");
        Message messageObtain = Message.obtain();
        messageObtain.what = MiContinuityStatus.SYSTEM_SERVICE_EXCEPTION;
        messageObtain.obj = gVar;
        b bVar = this.f33549a;
        if (bVar != null) {
            bVar.sendMessage(messageObtain);
        }
    }
}
