package com.xiaomi.security.xsof;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.xiaomi.onetrack.util.aa;
import eo.b;
import fo.d;
import go.c;
import go.e;
import go.f;
import go.g;
import go.h;
import go.i;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import miui.security.SecurityManager;
import no.j;

/* JADX INFO: loaded from: classes4.dex */
public class MiSafetyDetectService extends Service {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final Queue f28768g = new LinkedList();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map f28769a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private j f28770b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private b f28771c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private mo.b f28772d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private HandlerThread f28773e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private volatile a f28774f;

    public class MiSafetyDetectImpl extends IMiSafetyDetectServer.Stub {
        public MiSafetyDetectImpl() {
        }

        private boolean o(int i10, String str) {
            if (d.a(MiSafetyDetectService.this.getApplicationContext(), MiSafetyDetectService.this.h(i10), "com.xiaomi.security.permission.ACCESS_XSOF")) {
                return true;
            }
            oo.b.a("Mi-SDS", "Missing Declare permission, from calling package " + str);
            return false;
        }

        private void u(IMiSafetyDetectCallback iMiSafetyDetectCallback, String str, int i10) {
            if (iMiSafetyDetectCallback == null) {
                return;
            }
            try {
                iMiSafetyDetectCallback.H2(str, i10);
            } catch (Exception unused) {
            }
        }

        @Override // com.xiaomi.security.xsof.IMiSafetyDetectServer
        public void V4(String str, IMiSafetyDetectCallback iMiSafetyDetectCallback) {
            int callingPid = Binder.getCallingPid();
            String strH = MiSafetyDetectService.this.h(callingPid);
            oo.d.f("xsof_risk_app", strH);
            if (!o(callingPid, strH)) {
                u(iMiSafetyDetectCallback, null, AnalyticsListener.EVENT_VIDEO_INPUT_FORMAT_CHANGED);
                return;
            }
            oo.b.a("Mi-SDS", "Call API 002 from package : " + strH);
            go.a aVarA = go.a.a(str, 11);
            if (!(aVarA instanceof g)) {
                u(iMiSafetyDetectCallback, null, 1006);
            } else if (!TextUtils.equals(strH, aVarA.d())) {
                u(iMiSafetyDetectCallback, null, 1006);
            } else {
                MiSafetyDetectService.this.p(new co.a(11, aVarA, iMiSafetyDetectCallback));
            }
        }

        @Override // com.xiaomi.security.xsof.IMiSafetyDetectServer
        public void c2(String str, String str2, IMiSafetyDetectCallback iMiSafetyDetectCallback) {
            int callingPid = Binder.getCallingPid();
            String strH = MiSafetyDetectService.this.h(callingPid);
            oo.d.f("xsof_fraud_detection_current", strH);
            if (!o(callingPid, strH)) {
                u(iMiSafetyDetectCallback, null, AnalyticsListener.EVENT_VIDEO_INPUT_FORMAT_CHANGED);
                return;
            }
            oo.b.a("Mi-SDS", "Call API 004_1 from package : " + strH);
            go.a aVarA = go.a.a(str, 13);
            if (!(aVarA instanceof c)) {
                u(iMiSafetyDetectCallback, null, 1006);
                return;
            }
            c cVar = (c) aVarA;
            if (!TextUtils.equals(strH, aVarA.d())) {
                u(iMiSafetyDetectCallback, null, 1006);
                return;
            }
            a aVar = MiSafetyDetectService.this.f28774f;
            if (aVar != null) {
                aVar.sendEmptyMessage(1);
            }
            cVar.f(str2);
            MiSafetyDetectService.this.p(new co.a(13, cVar, iMiSafetyDetectCallback));
        }

        @Override // com.xiaomi.security.xsof.IMiSafetyDetectServer
        public void e3(String str, long j10, IMiSafetyDetectCallback iMiSafetyDetectCallback) {
            int callingPid = Binder.getCallingPid();
            String strH = MiSafetyDetectService.this.h(callingPid);
            oo.d.f("xsof_simulated_touch", strH);
            if (!o(callingPid, strH)) {
                u(iMiSafetyDetectCallback, null, AnalyticsListener.EVENT_VIDEO_INPUT_FORMAT_CHANGED);
                return;
            }
            oo.b.a("Mi-SDS", "Call API 003 from uid = " + strH);
            go.a aVarA = go.a.a(str, 12);
            if (!(aVarA instanceof h)) {
                u(iMiSafetyDetectCallback, null, 1006);
                return;
            }
            if (!TextUtils.equals(strH, aVarA.d())) {
                u(iMiSafetyDetectCallback, null, 1006);
                return;
            }
            h hVar = (h) aVarA;
            hVar.f(j10);
            MiSafetyDetectService.this.p(new co.a(12, hVar, iMiSafetyDetectCallback));
        }

        @Override // com.xiaomi.security.xsof.IMiSafetyDetectServer
        public void f7(String str, String str2, int[] iArr, int[] iArr2, IMiSafetyDetectCallback iMiSafetyDetectCallback) {
            int callingPid = Binder.getCallingPid();
            String strH = MiSafetyDetectService.this.h(callingPid);
            oo.d.f("xsof_fraud_detection_duration", strH);
            if (!o(callingPid, strH)) {
                u(iMiSafetyDetectCallback, null, AnalyticsListener.EVENT_VIDEO_INPUT_FORMAT_CHANGED);
                return;
            }
            oo.b.a("Mi-SDS", "Call API 004_3 from package : " + strH);
            go.a aVarA = go.a.a(str, 15);
            if (!(aVarA instanceof go.d)) {
                u(iMiSafetyDetectCallback, null, 1006);
                return;
            }
            go.d dVar = (go.d) aVarA;
            if (!TextUtils.equals(strH, aVarA.d())) {
                u(iMiSafetyDetectCallback, null, 1006);
                return;
            }
            a aVar = MiSafetyDetectService.this.f28774f;
            if (aVar != null) {
                aVar.sendEmptyMessage(1);
            }
            dVar.f(str2);
            dVar.k(iArr);
            dVar.j(iArr2);
            MiSafetyDetectService.this.p(new co.a(15, dVar, iMiSafetyDetectCallback));
        }

        @Override // com.xiaomi.security.xsof.IMiSafetyDetectServer
        public void h4(String str, String str2, int i10, IMiSafetyDetectCallback iMiSafetyDetectCallback) {
            int callingPid = Binder.getCallingPid();
            String strH = MiSafetyDetectService.this.h(callingPid);
            oo.d.f("xsof_device_trust", strH);
            if (!o(callingPid, strH)) {
                u(iMiSafetyDetectCallback, null, AnalyticsListener.EVENT_VIDEO_INPUT_FORMAT_CHANGED);
                return;
            }
            oo.b.a("Mi-SDS", "Call API 001 from package : " + strH);
            go.a aVarA = go.a.a(str, 10);
            if (!(aVarA instanceof i)) {
                u(iMiSafetyDetectCallback, null, 1006);
                return;
            }
            if (!TextUtils.equals(strH, aVarA.d())) {
                u(iMiSafetyDetectCallback, null, 1006);
                return;
            }
            a aVar = MiSafetyDetectService.this.f28774f;
            if (aVar != null) {
                aVar.sendEmptyMessage(1);
            }
            i iVar = (i) aVarA;
            iVar.f(str2);
            iVar.g(i10);
            MiSafetyDetectService.this.p(new co.a(10, iVar, iMiSafetyDetectCallback));
        }

        @Override // com.xiaomi.security.xsof.IMiSafetyDetectServer
        public void m6(String str, String str2, IMiSafetyDetectCallback iMiSafetyDetectCallback) {
            int callingPid = Binder.getCallingPid();
            String strH = MiSafetyDetectService.this.h(callingPid);
            oo.d.f("xsof_fraud_detection_score", strH);
            if (!o(callingPid, strH)) {
                u(iMiSafetyDetectCallback, null, AnalyticsListener.EVENT_VIDEO_INPUT_FORMAT_CHANGED);
                return;
            }
            oo.b.a("Mi-SDS", "Call API 004_4 from package : " + strH);
            go.a aVarA = go.a.a(str, 16);
            if (!(aVarA instanceof f)) {
                u(iMiSafetyDetectCallback, null, 1006);
                return;
            }
            f fVar = (f) aVarA;
            if (!TextUtils.equals(strH, aVarA.d())) {
                u(iMiSafetyDetectCallback, null, 1006);
                return;
            }
            a aVar = MiSafetyDetectService.this.f28774f;
            if (aVar != null) {
                aVar.sendEmptyMessage(1);
            }
            fVar.f(str2);
            MiSafetyDetectService.this.p(new co.a(16, fVar, iMiSafetyDetectCallback));
        }

        @Override // com.xiaomi.security.xsof.IMiSafetyDetectServer
        public void p6(String str, String str2, int[] iArr, IMiSafetyDetectCallback iMiSafetyDetectCallback) {
            int callingPid = Binder.getCallingPid();
            String strH = MiSafetyDetectService.this.h(callingPid);
            oo.d.f("xsof_fraud_detection_past", strH);
            if (!o(callingPid, strH)) {
                u(iMiSafetyDetectCallback, null, AnalyticsListener.EVENT_VIDEO_INPUT_FORMAT_CHANGED);
                return;
            }
            oo.b.a("Mi-SDS", "Call API 004_2 from package : " + strH);
            go.a aVarA = go.a.a(str, 14);
            if (!(aVarA instanceof e)) {
                u(iMiSafetyDetectCallback, null, 1006);
                return;
            }
            e eVar = (e) aVarA;
            if (!TextUtils.equals(strH, aVarA.d())) {
                u(iMiSafetyDetectCallback, null, 1006);
                return;
            }
            a aVar = MiSafetyDetectService.this.f28774f;
            if (aVar != null) {
                aVar.sendEmptyMessage(1);
            }
            eVar.f(str2);
            eVar.i(iArr);
            MiSafetyDetectService.this.p(new co.a(14, eVar, iMiSafetyDetectCallback));
        }
    }

    private class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i10 = message.what;
            if (i10 == 1) {
                MiSafetyDetectService.this.f28770b.m();
                return;
            }
            if (i10 == 2) {
                MiSafetyDetectService.this.f28770b.H();
                return;
            }
            switch (i10) {
                case 10:
                case 13:
                case 14:
                case 15:
                case 16:
                    MiSafetyDetectService.this.o();
                    break;
                case 11:
                case 12:
                    break;
                default:
                    return;
            }
            MiSafetyDetectService.this.n(message.obj);
        }
    }

    private int g(String str, String str2, String str3) {
        int iB = fo.a.b(this, str, str2, str3);
        if (iB != 1010) {
            Log.w("Mi-SDS", "Authentication failed, [" + str + aa.f28090b + iB + "]");
        }
        return iB;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String h(int i10) {
        return ((SecurityManager) getApplicationContext().getSystemService("security")).getPackageNameByPid(i10);
    }

    private boolean i(String str, int i10) {
        try {
            Pair pair = new Pair(str, Integer.valueOf(i10));
            long jLongValue = this.f28769a.containsKey(pair) ? ((Long) this.f28769a.get(pair)).longValue() : 0L;
            long jCurrentTimeMillis = System.currentTimeMillis();
            this.f28769a.put(pair, Long.valueOf(jCurrentTimeMillis));
            return jCurrentTimeMillis - jLongValue < 1000;
        } catch (Exception e10) {
            oo.b.c("Mi-SDS", "invokeFrequently error" + e10.getMessage());
            return false;
        }
    }

    private boolean j(String str, int i10) {
        try {
            if (lo.g.f34703a.g(str)) {
                return false;
            }
            Pair pair = new Pair(str, Integer.valueOf(i10));
            return System.currentTimeMillis() - (this.f28770b.z(pair) ? this.f28770b.q(pair) : 0L) < 30000;
        } catch (Exception e10) {
            oo.b.c("Mi-SDS", "invokeFrequently error" + e10.getMessage());
            return false;
        }
    }

    private boolean k() {
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            Queue queue = f28768g;
            if (queue.size() < 10) {
                queue.offer(Long.valueOf(jCurrentTimeMillis));
                return false;
            }
            if (jCurrentTimeMillis - ((Long) queue.peek()).longValue() < 60000) {
                return true;
            }
            queue.poll();
            queue.offer(Long.valueOf(jCurrentTimeMillis));
            return false;
        } catch (Exception e10) {
            oo.b.c("Mi-SDS", "isBusy error" + e10.getMessage());
            return false;
        }
    }

    private boolean l(String str, int i10) {
        return this.f28770b.y(i10) ? j(str, i10) : k();
    }

    private int m(String str, int i10) {
        if (!i(str, i10)) {
            if (!l(str, i10)) {
                return 0;
            }
            Log.w("Mi-SDS", "internal error , service is busy, retries later!");
            return AnalyticsListener.EVENT_VIDEO_DECODER_INITIALIZED;
        }
        Log.w("Mi-SDS", "The package [" + str + "] calls the api frequently, retries late!");
        return 1007;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(Object obj) {
        if (!(obj instanceof co.a)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("internal error, task type : ");
            sb2.append(obj != null ? obj.getClass().getCanonicalName() : null);
            Log.e("Mi-SDS", sb2.toString());
        }
        co.a aVar = (co.a) obj;
        if (aVar.b()) {
            Log.i("Mi-SDS", "Invalid Task , reason:  [canceled, " + aVar.f8917a + "]");
            return;
        }
        int i10 = aVar.f8917a;
        go.a aVarA = aVar.a();
        int iM = m(aVarA.d(), i10);
        if (iM != 0) {
            aVar.c(null, iM);
            return;
        }
        int iG = g(aVarA.d(), aVarA.c(), aVarA.b());
        if (iG == 1012) {
            q(aVar, 200L);
            return;
        }
        if (iG != 1010) {
            aVar.c(null, iG);
            return;
        }
        switch (i10) {
            case 10:
            case 13:
            case 14:
            case 15:
            case 16:
                this.f28770b.t(aVar);
                break;
            case 11:
                this.f28771c.b(aVar);
                break;
            case 12:
                this.f28772d.b(aVar);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        a aVar = this.f28774f;
        if (aVar != null) {
            aVar.removeMessages(2);
            aVar.sendEmptyMessageDelayed(2, C.DEFAULT_SEEK_FORWARD_INCREMENT_MS);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(co.a aVar) {
        q(aVar, 0L);
    }

    private void q(co.a aVar, long j10) {
        a aVar2 = this.f28774f;
        if (aVar2 == null) {
            Log.w("Mi-SDS", "sendMessageDelayed: mWorkHandler is null");
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = aVar.f8917a;
        messageObtain.obj = aVar;
        aVar2.sendMessageDelayed(messageObtain, j10);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Log.i("Mi-SDS", "onBind: " + intent);
        return new MiSafetyDetectImpl();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.f28770b = new j(this);
        this.f28771c = new b(this);
        this.f28772d = new mo.b(this);
        HandlerThread handlerThread = new HandlerThread("MiSafetyDetectService");
        this.f28773e = handlerThread;
        handlerThread.start();
        this.f28774f = new a(this.f28773e.getLooper());
        Log.i("Mi-SDS", "create");
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        HandlerThread handlerThread = this.f28773e;
        if (handlerThread != null && handlerThread.isAlive()) {
            this.f28773e.quitSafely();
        }
        this.f28770b.H();
        if (this.f28774f != null) {
            this.f28774f.removeCallbacksAndMessages(null);
            this.f28774f = null;
        }
        Log.i("Mi-SDS", "destroy");
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        Log.i("Mi-SDS", "onUnbind: " + intent);
        return super.onUnbind(intent);
    }
}
