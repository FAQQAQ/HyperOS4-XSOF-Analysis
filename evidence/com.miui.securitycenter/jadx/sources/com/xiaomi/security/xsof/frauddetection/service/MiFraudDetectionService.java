package com.xiaomi.security.xsof.frauddetection.service;

import aj.c0;
import android.app.AppOpsManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.provider.Settings;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.miui.common.utils.d0;
import com.miui.common.utils.h0;
import com.miui.common.utils.m2;
import com.miui.common.utils.v1;
import com.miui.maml.folme.AnimatedProperty;
import com.miui.networkassistant.config.Constants;
import com.miui.riskyapp.model.RiskApp;
import com.miui.warningcenter.mijia.pojo.MijiaAlertWarning;
import com.xiaomi.onetrack.api.am;
import com.xiaomi.security.xsof.frauddetection.service.MiFraudDetectionService;
import io.j;
import io.m;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicReference;
import jo.k;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kp.a;
import lp.n;
import miui.cloud.CloudPushConstants;
import miui.os.Build;
import wo.h;
import wo.i;
import wo.p;
import wo.q;
import wo.w;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 \u001a2\u00020\u0001:\u0001(B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0010\u0010\u0003J\u000f\u0010\u0011\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0011\u0010\u0003J\u000f\u0010\u0012\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0012\u0010\u0003J\u000f\u0010\u0013\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0013\u0010\u0003J\u000f\u0010\u0014\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0014\u0010\u0003J\u000f\u0010\u0015\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0015\u0010\u0003J\u000f\u0010\u0016\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0016\u0010\u0003J\u000f\u0010\u0017\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0017\u0010\u0003J\u0017\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u0018H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u0018H\u0002¢\u0006\u0004\b\u001c\u0010\u001bJ\u000f\u0010\u001d\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u001d\u0010\u0003J\u000f\u0010\u001e\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u001e\u0010\u0003J\u0019\u0010!\u001a\u00020\u000f2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0007¢\u0006\u0004\b!\u0010\"J\u001b\u0010$\u001a\u0004\u0018\u00010#2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0016¢\u0006\u0004\b$\u0010%J\u000f\u0010&\u001a\u00020\u000fH\u0016¢\u0006\u0004\b&\u0010\u0003R\u0018\u0010*\u001a\u0004\u0018\u00010'8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b(\u0010)R\u0018\u0010.\u001a\u0004\u0018\u00010+8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b,\u0010-R\u0016\u00101\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b/\u00100R\u001a\u00106\u001a\b\u0012\u0004\u0012\u000203028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00105R\u001a\u0010:\u001a\b\u0012\u0004\u0012\u00020\u000b078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b8\u00109R\u0014\u0010>\u001a\u00020;8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b<\u0010=R\u0014\u0010@\u001a\u00020;8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010=R\u0016\u0010B\u001a\u0004\u0018\u00010;8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bA\u0010=R\u0014\u0010C\u001a\u00020;8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010=R\u0014\u0010E\u001a\u00020;8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bD\u0010=R\"\u0010J\u001a\u0010\u0012\f\u0012\n G*\u0004\u0018\u00010\u000b0\u000b0F8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bH\u0010IR\u001d\u0010N\u001a\b\u0012\u0004\u0012\u00020\u000b0F8\u0006¢\u0006\f\n\u0004\bK\u0010I\u001a\u0004\bL\u0010MR\u001b\u0010R\u001a\u0002038BX\u0082\u0084\u0002¢\u0006\f\u001a\u0004\bK\u0010O*\u0004\bP\u0010Q¨\u0006S"}, d2 = {"Lcom/xiaomi/security/xsof/frauddetection/service/MiFraudDetectionService;", "Landroid/app/Service;", "<init>", "()V", "", "r", "()Z", "", MijiaAlertWarning.JSON_PROPERTY_UID, "q", "(I)Z", "", "permission", am.f27441a, "(Ljava/lang/String;)I", "Lwo/w;", "z", "p", AnimatedProperty.PROPERTY_NAME_X, AnimatedProperty.PROPERTY_NAME_Y, "u", "v", AnimatedProperty.PROPERTY_NAME_W, "C", "Landroid/content/Intent;", "intent", "m", "(Landroid/content/Intent;)V", "n", "D", "onCreate", "Landroid/content/Context;", "context", "i", "(Landroid/content/Context;)V", "Landroid/os/IBinder;", "onBind", "(Landroid/content/Intent;)Landroid/os/IBinder;", "onDestroy", "Landroid/os/Handler;", "a", "Landroid/os/Handler;", "mUiHandler", "Landroid/os/HandlerThread;", "b", "Landroid/os/HandlerThread;", "mHandlerThread", "c", "Z", "mTelephonyReceiversRegistered", "Lwo/h;", "Ljava/util/concurrent/ExecutorService;", "d", "Lwo/h;", "mSingleExecutorLazy", "", "e", "Ljava/util/List;", "mIgnoreXsofScreenShareAppList", "Landroid/content/BroadcastReceiver;", "f", "Landroid/content/BroadcastReceiver;", "mScreenshotReceiver", CloudPushConstants.WATERMARK_TYPE.GLOBAL, "mSimStateReceiver", AnimatedProperty.PROPERTY_NAME_H, "mScNetworkStatusReceiver", "mAirplaneModeChangeReceiver", "j", "mPhoneStateChangeReceiver", "Ljava/util/concurrent/atomic/AtomicReference;", "kotlin.jvm.PlatformType", "k", "Ljava/util/concurrent/atomic/AtomicReference;", "mCallState", com.xiaomi.onetrack.b.e.f27645a, "getInComingCallNumber", "()Ljava/util/concurrent/atomic/AtomicReference;", "inComingCallNumber", "()Ljava/util/concurrent/ExecutorService;", "getMSingleExecutor$delegate", "(Lcom/xiaomi/security/xsof/frauddetection/service/MiFraudDetectionService;)Ljava/lang/Object;", "mSingleExecutor", "core-service_cnRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMiFraudDetectionService.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MiFraudDetectionService.kt\ncom/xiaomi/security/xsof/frauddetection/service/MiFraudDetectionService\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,483:1\n13472#2,2:484\n1#3:486\n*S KotlinDebug\n*F\n+ 1 MiFraudDetectionService.kt\ncom/xiaomi/security/xsof/frauddetection/service/MiFraudDetectionService\n*L\n78#1:484,2\n*E\n"})
public final class MiFraudDetectionService extends Service {

    /* JADX INFO: renamed from: m, reason: collision with root package name and from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
    private Handler mUiHandler;

    /* JADX INFO: renamed from: b, reason: collision with root package name and from kotlin metadata */
    private HandlerThread mHandlerThread;

    /* JADX INFO: renamed from: c, reason: collision with root package name and from kotlin metadata */
    private boolean mTelephonyReceiversRegistered;

    /* JADX INFO: renamed from: d, reason: collision with root package name and from kotlin metadata */
    private final h mSingleExecutorLazy = i.a(new a() { // from class: ko.c
        @Override // kp.a
        public final Object invoke() {
            return MiFraudDetectionService.s();
        }
    });

    /* JADX INFO: renamed from: e, reason: collision with root package name and from kotlin metadata */
    private final List mIgnoreXsofScreenShareAppList = new ArrayList();

    /* JADX INFO: renamed from: f, reason: collision with root package name and from kotlin metadata */
    private final BroadcastReceiver mScreenshotReceiver;

    /* JADX INFO: renamed from: g, reason: collision with root package name and from kotlin metadata */
    private final BroadcastReceiver mSimStateReceiver;

    /* JADX INFO: renamed from: h, reason: collision with root package name and from kotlin metadata */
    private final BroadcastReceiver mScNetworkStatusReceiver;

    /* JADX INFO: renamed from: i, reason: collision with root package name and from kotlin metadata */
    private final BroadcastReceiver mAirplaneModeChangeReceiver;

    /* JADX INFO: renamed from: j, reason: collision with root package name and from kotlin metadata */
    private final BroadcastReceiver mPhoneStateChangeReceiver;

    /* JADX INFO: renamed from: k, reason: collision with root package name and from kotlin metadata */
    private final AtomicReference mCallState;

    /* JADX INFO: renamed from: l, reason: collision with root package name and from kotlin metadata */
    private final AtomicReference inComingCallNumber;

    /* JADX INFO: renamed from: com.xiaomi.security.xsof.frauddetection.service.MiFraudDetectionService$a, reason: from kotlin metadata */
    public static final class Companion {
        /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x0000: CONSTRUCTOR  A[MD:():void (m)] (LINE:1) call: com.xiaomi.security.xsof.frauddetection.service.MiFraudDetectionService.a.<init>():void type: THIS */
        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
        public /* synthetic */ Companion(lp.h hVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final io.d b(Integer num, Boolean bool, long j10) {
            io.d dVar = new io.d(num, n.b(bool, Boolean.TRUE) ? 1 : 0, j10);
            dVar.i(j10);
            return dVar;
        }

        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
        private Companion() {
        }
    }

    public static final class b extends BroadcastReceiver {
        b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void b(MiFraudDetectionService miFraudDetectionService) {
            if (Settings.Global.getInt(miFraudDetectionService.getContentResolver(), "airplane_mode_on", 0) == 1) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                io.a aVar = new io.a(true, jCurrentTimeMillis);
                aVar.g(jCurrentTimeMillis);
                k.f33544c.a().X0(aVar);
            }
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            n.f(intent, "intent");
            try {
                if (n.b("android.intent.action.AIRPLANE_MODE", intent.getAction())) {
                    oo.b.a("MiFraudDetectionService", "receive AirplaneModeChange");
                    c0 c0VarE = c0.e();
                    final MiFraudDetectionService miFraudDetectionService = MiFraudDetectionService.this;
                    c0VarE.b(new Runnable() { // from class: ko.g
                        @Override // java.lang.Runnable
                        public final void run() {
                            MiFraudDetectionService.b.b(miFraudDetectionService);
                        }
                    });
                }
            } catch (Exception e10) {
                oo.b.b("MiFraudDetectionService", "mAirplaneModeChangeReceiver onReceive Exception: " + e10);
            }
        }
    }

    public static final class c extends BroadcastReceiver {
        c() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void b(MiFraudDetectionService miFraudDetectionService, Intent intent) {
            miFraudDetectionService.m(intent);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, final Intent intent) {
            n.f(context, "context");
            n.f(intent, "intent");
            try {
                oo.b.a("MiFraudDetectionService", "receive phoneStateChange");
                if (!Build.IS_INTERNATIONAL_BUILD && ((TelephonyManager) context.getSystemService(TelephonyManager.class)).isVoiceCapable() && n.b("android.intent.action.PHONE_STATE", intent.getAction())) {
                    c0 c0VarE = c0.e();
                    final MiFraudDetectionService miFraudDetectionService = MiFraudDetectionService.this;
                    c0VarE.b(new Runnable() { // from class: ko.h
                        @Override // java.lang.Runnable
                        public final void run() {
                            MiFraudDetectionService.c.b(miFraudDetectionService, intent);
                        }
                    });
                }
            } catch (Exception e10) {
                oo.b.b("MiFraudDetectionService", "mPhoneStateChangeReceiver onReceive Exception: " + e10);
            }
        }
    }

    public static final class d extends BroadcastReceiver {
        d() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            n.f(context, "context");
            n.f(intent, "intent");
            try {
                boolean booleanExtra = intent.getBooleanExtra("extra_network_status", false);
                oo.b.a("MiFraudDetectionService", "mScNetworkStatusReceiver : " + booleanExtra);
                if (booleanExtra) {
                    MiFraudDetectionService.this.i(com.miui.common.e.c());
                }
            } catch (Exception e10) {
                oo.b.b("MiFraudDetectionService", "mScNetworkStatusReceiver error: " + e10.getMessage());
            }
        }
    }

    public static final class e extends BroadcastReceiver {
        e() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            n.f(intent, "intent");
            if (n.b("miui.intent.TAKE_SCREENSHOT", intent.getAction())) {
                MiFraudDetectionService.this.n(intent);
            }
        }
    }

    public static final class f extends BroadcastReceiver {
        f() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            n.f(context, "context");
            n.f(intent, "intent");
            try {
                String stringExtra = intent.hasExtra("ss") ? intent.getStringExtra("ss") : "";
                oo.b.a("MiFraudDetectionService", "mSimStateReceiver : " + stringExtra);
                if (n.b("LOADED", stringExtra)) {
                    MiFraudDetectionService.this.i(com.miui.common.e.c());
                } else {
                    if (!n.b("ABSENT", stringExtra) || intent.getExtras() == null) {
                        return;
                    }
                    lo.d.f34700a.m(context, 0);
                }
            } catch (Exception e10) {
                oo.b.b("MiFraudDetectionService", "mSimStateReceiver error: " + e10.getMessage());
            }
        }
    }

    public MiFraudDetectionService() {
        try {
            p.a aVar = p.f45327b;
            String[] stringArray = com.miui.common.e.c().getResources().getStringArray(to.a.f43423a);
            n.e(stringArray, "getStringArray(...)");
            for (String str : stringArray) {
                List list = this.mIgnoreXsofScreenShareAppList;
                n.c(str);
                list.add(str);
            }
            p.b(w.f45339a);
        } catch (Throwable th2) {
            p.a aVar2 = p.f45327b;
            p.b(q.a(th2));
        }
        this.mScreenshotReceiver = new e();
        this.mSimStateReceiver = new f();
        this.mScNetworkStatusReceiver = new d();
        this.mAirplaneModeChangeReceiver = new b();
        this.mPhoneStateChangeReceiver = new c();
        this.mCallState = new AtomicReference("");
        this.inComingCallNumber = new AtomicReference();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void A(MiFraudDetectionService miFraudDetectionService, String str, int i10, String str2, boolean z10) {
        n.f(str, "op");
        n.f(str2, RiskApp.Columns.RISK_PACKAGE_NAME);
        oo.b.c("MiFraudDetectionService", "startWatchingPrivacyUse op: " + str + ", uid: " + i10 + ", packageName: " + str2 + ", active: " + z10);
        if (miFraudDetectionService.q(i10) && miFraudDetectionService.B(str) == 10042 && !miFraudDetectionService.mIgnoreXsofScreenShareAppList.contains(str2)) {
            if (z10) {
                k.f33544c.a().e1(new j(str2, System.currentTimeMillis(), 0L, 0L, true));
            } else {
                k.f33544c.a().b2(new j(str2, 0L, System.currentTimeMillis(), 0L, false));
            }
        }
    }

    private final int B(String permission) {
        Object objB;
        try {
            p.a aVar = p.f45327b;
            Object objH = dl.e.h(AppOpsManager.class, "strOpToOp", new Class[]{String.class}, permission);
            n.d(objH, "null cannot be cast to non-null type kotlin.Int");
            Integer num = (Integer) objH;
            num.intValue();
            objB = p.b(num);
        } catch (Throwable th2) {
            p.a aVar2 = p.f45327b;
            objB = p.b(q.a(th2));
        }
        if (p.f(objB)) {
            objB = -1;
        }
        return ((Number) objB).intValue();
    }

    private final void C() {
        try {
            BroadcastReceiver broadcastReceiver = this.mScNetworkStatusReceiver;
            if (broadcastReceiver == null) {
                return;
            }
            unregisterReceiver(broadcastReceiver);
        } catch (Exception e10) {
            oo.b.b("MiFraudDetectionService", "unRegisterScNetworkStatusReceiver error: " + e10.getMessage());
        }
    }

    private final void D() {
        try {
            unregisterReceiver(this.mScreenshotReceiver);
            unregisterReceiver(this.mSimStateReceiver);
            unregisterReceiver(this.mAirplaneModeChangeReceiver);
            if (this.mTelephonyReceiversRegistered) {
                unregisterReceiver(this.mPhoneStateChangeReceiver);
            }
            lo.e.f34701a.d(this);
            C();
        } catch (Exception e10) {
            oo.b.b("MiFraudDetectionService", "unregisterReceiver error: " + e10);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object j(Context context, Object obj, Method method, Object[] objArr) throws Throwable {
        int i10 = 0;
        if (!n.b(method != null ? method.getName() : null, "onCallForwardingInfoAvailable")) {
            if (n.b(method != null ? method.getName() : null, "onError")) {
                oo.b.b("MiFraudDetectionService", "onError: ");
                if (objArr != null && objArr.length > 0) {
                    oo.b.b("MiFraudDetectionService", "onError: " + objArr[0]);
                }
            }
            return null;
        }
        oo.b.c("MiFraudDetectionService", "onCallForwardingInfoAvailable: ");
        if (objArr != null && objArr.length > 0) {
            Object obj2 = objArr[0];
            oo.b.a("MiFraudDetectionService", "onCallForwardingInfoAvailable: " + obj2 + ", infoClass=" + obj2.getClass());
            try {
                Integer num = (Integer) dl.e.b(obj2, Integer.TYPE, "getReason", null, new Object[0]);
                Boolean bool = (Boolean) dl.e.b(obj2, Boolean.TYPE, "isEnabled", null, new Object[0]);
                final io.d dVarB = INSTANCE.b(num, bool, System.currentTimeMillis());
                lo.d.a aVar = lo.d.f34700a;
                io.d dVarE = aVar.e();
                oo.b.a("MiFraudDetectionService", "onReceive , info=" + dVarB + ", latestCallForwardingInfo=" + dVarE);
                if (n.b(bool, Boolean.TRUE) && num != null && num.intValue() == 0) {
                    i10 = 1;
                }
                aVar.m(context, i10);
                if (dVarE == null || !dVarE.e(dVarB)) {
                    oo.b.a("MiFraudDetectionService", "checkCallForwarding: equals = false");
                    c0.e().b(new Runnable() { // from class: ko.f
                        @Override // java.lang.Runnable
                        public final void run() {
                            MiFraudDetectionService.k(dVarB);
                        }
                    });
                } else {
                    oo.b.a("MiFraudDetectionService", "checkCallForwarding: equals = true");
                }
            } catch (Exception e10) {
                oo.b.b("MiFraudDetectionService", "Exception: " + e10);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void k(io.d dVar) {
        k.f33544c.a().b1(dVar);
    }

    private final ExecutorService l() {
        return (ExecutorService) this.mSingleExecutorLazy.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void m(Intent intent) {
        try {
            String stringExtra = intent.getStringExtra("state");
            String stringExtra2 = intent.getStringExtra("incoming_number");
            if (n.b(this.mCallState.get(), stringExtra)) {
                return;
            }
            if (stringExtra2 == null) {
                oo.b.b("MiFraudDetectionService", "handlePhoneStateChange number is null");
                return;
            }
            this.mCallState.set(stringExtra);
            if (n.b(TelephonyManager.EXTRA_STATE_RINGING, stringExtra)) {
                if (TextUtils.isEmpty(stringExtra2)) {
                    return;
                }
                this.inComingCallNumber.set(null);
                return;
            }
            if (!n.b(TelephonyManager.EXTRA_STATE_OFFHOOK, stringExtra)) {
                if (n.b(TelephonyManager.EXTRA_STATE_IDLE, stringExtra)) {
                    k kVarA = k.f33544c.a();
                    String strD = oo.a.d((String) this.inComingCallNumber.get());
                    n.e(strD, "getMD5String(...)");
                    kVarA.d2(strD);
                    this.inComingCallNumber.set(null);
                    return;
                }
                return;
            }
            this.inComingCallNumber.set(stringExtra2);
            String str = (String) this.inComingCallNumber.get();
            if (TextUtils.isEmpty(str)) {
                return;
            }
            lo.d.a aVar = lo.d.f34700a;
            n.c(str);
            boolean zK = aVar.k(this, str);
            oo.b.a("MiFraudDetectionService", "handlePhoneStateChange isSimCardFraudCall: " + zK);
            long jCurrentTimeMillis = System.currentTimeMillis();
            String strD2 = oo.a.d(str);
            n.e(strD2, "getMD5String(...)");
            k.f33544c.a().i1(new m(strD2, jCurrentTimeMillis, 0L, aVar.h(this, str), aVar.i(this, str), aVar.j(str), aVar.l(this, str), true, 0L, zK));
        } catch (Exception e10) {
            oo.b.b("MiFraudDetectionService", "handlePhoneStateChange error: " + e10);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void n(Intent intent) {
        oo.b.a("MiFraudDetectionService", "handleScreenShot:");
        try {
            boolean booleanExtra = intent.getBooleanExtra("IsFinished", false);
            oo.b.a("MiFraudDetectionService", "handleScreenShot: mIsScreenShotFinish = " + booleanExtra);
            if (booleanExtra) {
                c0.e().b(new Runnable() { // from class: ko.e
                    @Override // java.lang.Runnable
                    public final void run() {
                        MiFraudDetectionService.o();
                    }
                });
            }
        } catch (Exception e10) {
            oo.b.b("MiFraudDetectionService", "handleScreenShot error: " + e10);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void o() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        io.k kVar = new io.k(jCurrentTimeMillis);
        kVar.f(jCurrentTimeMillis);
        k.f33544c.a().g1(kVar);
    }

    private final void p() {
        this.mUiHandler = new Handler(Looper.getMainLooper());
        HandlerThread handlerThread = new HandlerThread("fraud_detection_handler_thread");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
    }

    private final boolean q(int uid) {
        if (m2.F() != m2.o(uid)) {
            return m2.o(uid) == 999 && m2.v();
        }
        return true;
    }

    private final boolean r() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TelephonyManager.class);
        return telephonyManager != null && telephonyManager.isVoiceCapable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ExecutorService s() {
        return Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: ko.d
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return MiFraudDetectionService.t(runnable);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Thread t(Runnable runnable) {
        Thread thread = new Thread(runnable, "fraud_detection_executor");
        thread.setDaemon(true);
        return thread;
    }

    private final void u() {
        d0.o(this, this.mAirplaneModeChangeReceiver, new IntentFilter("android.intent.action.AIRPLANE_MODE"), 2);
    }

    private final void v() {
        d0.o(this, this.mPhoneStateChangeReceiver, new IntentFilter("android.intent.action.PHONE_STATE"), 2);
    }

    private final void w() {
        if (this.mScNetworkStatusReceiver == null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_update_sc_network_allow");
        d0.o(this, this.mScNetworkStatusReceiver, intentFilter, 4);
    }

    private final void x() {
        d0.p(this, this.mScreenshotReceiver, new IntentFilter("miui.intent.TAKE_SCREENSHOT"), "miui.permission.USE_INTERNAL_GENERAL_API", this.mUiHandler, 2);
    }

    private final void y() {
        d0.o(this, this.mSimStateReceiver, new IntentFilter(Constants.System.ACTION_SIM_STATE_CHANGED), 2);
    }

    private final void z() {
        try {
            p.a aVar = p.f45327b;
            Object systemService = getApplicationContext().getSystemService("appops");
            n.d(systemService, "null cannot be cast to non-null type android.app.AppOpsManager");
            AppOpsManager appOpsManager = (AppOpsManager) systemService;
            String[] strArr = {"MIUI:10042"};
            if (android.os.Build.VERSION.SDK_INT >= 30) {
                appOpsManager.startWatchingActive(strArr, l(), new AppOpsManager.OnOpActiveChangedListener() { // from class: ko.a
                    @Override // android.app.AppOpsManager.OnOpActiveChangedListener
                    public final void onOpActiveChanged(String str, int i10, String str2, boolean z10) {
                        MiFraudDetectionService.A(this.f34227a, str, i10, str2, z10);
                    }
                });
            }
            p.b(w.f45339a);
        } catch (Throwable th2) {
            p.a aVar2 = p.f45327b;
            p.b(q.a(th2));
        }
    }

    public final void i(final Context context) {
        if (!v1.b()) {
            oo.b.a("MiFraudDetectionService", "checkCallForwarding return, case cta is fail");
            return;
        }
        SubscriptionManager subscriptionManager = (SubscriptionManager) getSystemService(SubscriptionManager.class);
        if (subscriptionManager != null) {
            List<SubscriptionInfo> activeSubscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();
            oo.b.a("MiFraudDetectionService", "checkCallForwarding: subscriptionManager.activeSubscriptionInfoList " + (activeSubscriptionInfoList != null ? Integer.valueOf(activeSubscriptionInfoList.size()) : null));
            List<SubscriptionInfo> activeSubscriptionInfoList2 = subscriptionManager.getActiveSubscriptionInfoList();
            if (activeSubscriptionInfoList2 == null || !(!activeSubscriptionInfoList2.isEmpty()) || context == null) {
                return;
            }
            try {
                Class<?> cls = Class.forName("android.telephony.TelephonyManager$CallForwardingInfoCallback");
                TelephonyManager.class.getDeclaredMethod("getCallForwarding", Integer.TYPE, Executor.class, cls).invoke(context.getSystemService(TelephonyManager.class), 0, l(), Proxy.newProxyInstance(getClassLoader(), new Class[]{cls}, new InvocationHandler() { // from class: ko.b
                    @Override // java.lang.reflect.InvocationHandler
                    public final Object invoke(Object obj, Method method, Object[] objArr) {
                        return MiFraudDetectionService.j(context, obj, method, objArr);
                    }
                }));
            } catch (Exception e10) {
                oo.b.b("MiFraudDetectionService", "getCallForwarding error: " + e10);
                w wVar = w.f45339a;
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        p();
        x();
        y();
        u();
        lo.e.f34701a.c(this);
        w();
        i(com.miui.common.e.c());
        if (!Build.IS_INTERNATIONAL_BUILD && r()) {
            v();
            this.mTelephonyReceiversRegistered = true;
        }
        if (h0.k()) {
            z();
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        HandlerThread handlerThread;
        super.onDestroy();
        D();
        HandlerThread handlerThread2 = this.mHandlerThread;
        if (handlerThread2 != null && handlerThread2.isAlive() && (handlerThread = this.mHandlerThread) != null) {
            handlerThread.quitSafely();
        }
        if (this.mSingleExecutorLazy.isInitialized()) {
            l().shutdownNow();
        }
        oo.b.c("MiFraudDetectionService", "onDestroy: ...");
    }
}
