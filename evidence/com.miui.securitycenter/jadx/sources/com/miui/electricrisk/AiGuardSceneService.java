package com.miui.electricrisk;

import android.app.ActivityManager;
import android.app.IMiuiProcessObserver;
import android.app.IProcessObserver;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.AudioManager$OnModeChangedListener;
import android.media.AudioRecordingConfiguration;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import androidx.annotation.RequiresApi;
import androidx.exifinterface.media.ExifInterface;
import com.miui.common.utils.m2;
import com.miui.luckymoney.config.AppConstants;
import com.miui.maml.folme.AnimatedProperty;
import com.miui.networkassistant.vpn.miui.ActivityManagerCompat;
import com.miui.riskyapp.model.RiskApp;
import com.xiaomi.onetrack.api.am;
import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import miui.cloud.CloudPushConstants;
import miui.yellowpage.YellowPageUtils;
import xp.d1;
import xp.k0;
import xp.o0;
import xp.p0;
import xp.s1;
import xp.t2;
import xp.y0;

/* JADX INFO: loaded from: classes2.dex */
@RequiresApi(33)
@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J3\u0010\f\u001a\u00020\u000b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0010\u0010\n\u001a\f\u0012\u0006\b\u0001\u0012\u00020\t\u0018\u00010\bH\u0014¢\u0006\u0004\b\f\u0010\rJ\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0013\u0010\u0003J\u0017\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\tH\u0007¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\tH\u0007¢\u0006\u0004\b\u0018\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0019\u0010\u0003J\u0015\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\t¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001e\u001a\u00020\u001dH\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010 \u001a\u00020\u000bH\u0002¢\u0006\u0004\b \u0010\u0003J\u000f\u0010!\u001a\u00020\u000bH\u0002¢\u0006\u0004\b!\u0010\u0003J\u000f\u0010\"\u001a\u00020\u0015H\u0002¢\u0006\u0004\b\"\u0010#J\u000f\u0010$\u001a\u00020\u0015H\u0002¢\u0006\u0004\b$\u0010#J\u000f\u0010%\u001a\u00020\u000bH\u0002¢\u0006\u0004\b%\u0010\u0003J#\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001d0(2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\t0&H\u0002¢\u0006\u0004\b)\u0010*J#\u0010,\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u001d\u0012\u0006\u0012\u0004\u0018\u00010\t0+0(H\u0002¢\u0006\u0004\b,\u0010-J\u0015\u0010.\u001a\b\u0012\u0004\u0012\u00020\t0(H\u0002¢\u0006\u0004\b.\u0010-J\u0094\u0002\u0010:\u001a\u00020\u000b21\u00105\u001a-\b\u0001\u0012\u0013\u0012\u00110\t¢\u0006\f\b0\u0012\b\b1\u0012\u0004\b\b(2\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b03\u0012\u0006\u0012\u0004\u0018\u0001040/21\u00106\u001a-\b\u0001\u0012\u0013\u0012\u00110\t¢\u0006\f\b0\u0012\b\b1\u0012\u0004\b\b(2\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b03\u0012\u0006\u0012\u0004\u0018\u0001040/21\u00107\u001a-\b\u0001\u0012\u0013\u0012\u00110\t¢\u0006\f\b0\u0012\b\b1\u0012\u0004\b\b(2\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b03\u0012\u0006\u0012\u0004\u0018\u0001040/23\u00108\u001a/\b\u0001\u0012\u0015\u0012\u0013\u0018\u00010\t¢\u0006\f\b0\u0012\b\b1\u0012\u0004\b\b(2\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b03\u0012\u0006\u0012\u0004\u0018\u0001040/25\b\u0002\u00109\u001a/\b\u0001\u0012\u0015\u0012\u0013\u0018\u00010\t¢\u0006\f\b0\u0012\b\b1\u0012\u0004\b\b(2\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b03\u0012\u0006\u0012\u0004\u0018\u0001040/H\u0002¢\u0006\u0004\b:\u0010;J=\u0010@\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020?\u0012\u0004\u0012\u00020?0+0(2\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\t0<2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\t0<H\u0002¢\u0006\u0004\b@\u0010AJ\u0017\u0010B\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\tH\u0002¢\u0006\u0004\bB\u0010\u0017J\u0017\u0010D\u001a\u00020\u000e2\u0006\u0010C\u001a\u00020\u001dH\u0002¢\u0006\u0004\bD\u0010EJ\u000f\u0010F\u001a\u00020\u0015H\u0002¢\u0006\u0004\bF\u0010#J\u000f\u0010G\u001a\u00020\u000bH\u0002¢\u0006\u0004\bG\u0010\u0003R\u0014\u0010K\u001a\u00020H8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bI\u0010JR\u0014\u0010M\u001a\u00020\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bL\u0010:R\u0014\u0010Q\u001a\u00020N8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bO\u0010PR\u0016\u0010T\u001a\u00020\t8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bR\u0010SR\u0018\u0010W\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bU\u0010VR\u0018\u0010[\u001a\u0004\u0018\u00010X8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bY\u0010ZR\u0016\u0010^\u001a\u00020\\8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b]\u0010.R\u0014\u0010`\u001a\u00020\\8\u0002X\u0082D¢\u0006\u0006\n\u0004\b_\u0010.R\u0014\u0010c\u001a\u0002048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\ba\u0010bR\u0016\u0010e\u001a\u00020\u001d8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bd\u0010:¨\u0006f"}, d2 = {"Lcom/miui/electricrisk/AiGuardSceneService;", "Landroid/app/Service;", "<init>", "()V", "Ljava/io/FileDescriptor;", "fd", "Ljava/io/PrintWriter;", "writer", "", "", "args", "Lwo/w;", "dump", "(Ljava/io/FileDescriptor;Ljava/io/PrintWriter;[Ljava/lang/String;)V", "Landroid/content/Intent;", "intent", "Landroid/os/IBinder;", "onBind", "(Landroid/content/Intent;)Landroid/os/IBinder;", "onCreate", "phoneNumber", "", AnimatedProperty.PROPERTY_NAME_X, "(Ljava/lang/String;)Z", "z", "onDestroy", RiskApp.Columns.RISK_PACKAGE_NAME, am.f27442b, "(Ljava/lang/String;)V", "", "u", "()I", "F", "G", AnimatedProperty.PROPERTY_NAME_W, "()Z", "C", "D", "Ljava/util/concurrent/atomic/AtomicReference;", "pkgRef", "Laq/f;", "K", "(Ljava/util/concurrent/atomic/AtomicReference;)Laq/f;", "Lwo/o;", "t", "()Laq/f;", "J", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "who", "Lbp/f;", "", "onEstablished", "onTopResumed", "onPaused", "onDisconnected", "onAlerted", "I", "(Lkp/p;Lkp/p;Lkp/p;Lkp/p;Lkp/p;)V", "", "packages", "activities", "Landroid/content/ComponentName;", am.f27441a, "(Ljava/util/List;Ljava/util/List;)Laq/f;", AnimatedProperty.PROPERTY_NAME_Y, "command", "v", "(I)Landroid/content/Intent;", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, ExifInterface.LONGITUDE_EAST, "Lxp/o0;", "a", "Lxp/o0;", "scope", "b", "userId", "Ljava/util/concurrent/atomic/AtomicBoolean;", "c", "Ljava/util/concurrent/atomic/AtomicBoolean;", "workerStarted", "d", "Ljava/lang/String;", "currentVoipPackageName", "e", "Landroid/content/Intent;", "lastWorkerIntent", "Landroid/app/IProcessObserver;", "f", "Landroid/app/IProcessObserver;", "processObserver", "", CloudPushConstants.WATERMARK_TYPE.GLOBAL, "lastRestartTime", AnimatedProperty.PROPERTY_NAME_H, "restartDebounceMs", "i", "Ljava/lang/Object;", "restartLock", "j", "guardProviderUid", "app_cnPhoneRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AiGuardSceneService extends Service {

    /* JADX INFO: renamed from: d, reason: collision with root package name and from kotlin metadata */
    private String currentVoipPackageName;

    /* JADX INFO: renamed from: e, reason: collision with root package name and from kotlin metadata */
    private Intent lastWorkerIntent;

    /* JADX INFO: renamed from: f, reason: collision with root package name and from kotlin metadata */
    private IProcessObserver processObserver;

    /* JADX INFO: renamed from: g, reason: collision with root package name and from kotlin metadata */
    private long lastRestartTime;

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
    private final o0 scope = p0.a(d1.a().D0(t2.b(null, 1, null)));

    /* JADX INFO: renamed from: b, reason: collision with root package name and from kotlin metadata */
    private final int userId = m2.F();

    /* JADX INFO: renamed from: c, reason: collision with root package name and from kotlin metadata */
    private final AtomicBoolean workerStarted = new AtomicBoolean(false);

    /* JADX INFO: renamed from: h, reason: collision with root package name and from kotlin metadata */
    private final long restartDebounceMs = 5000;

    /* JADX INFO: renamed from: i, reason: collision with root package name and from kotlin metadata */
    private final Object restartLock = new Object();

    /* JADX INFO: renamed from: j, reason: collision with root package name and from kotlin metadata */
    private int guardProviderUid = -1;

    static final class a extends kotlin.coroutines.jvm.internal.j implements kp.p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f15954a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private /* synthetic */ Object f15955b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ TelephonyManager f15956c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ AiGuardSceneService f15957d;

        /* JADX INFO: renamed from: com.miui.electricrisk.AiGuardSceneService$a$a, reason: collision with other inner class name */
        public static final class C0232a extends PhoneStateListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ AiGuardSceneService f15958a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ zp.t f15959b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0232a(AiGuardSceneService aiGuardSceneService, zp.t tVar, Executor executor) {
                super(executor);
                this.f15958a = aiGuardSceneService;
                this.f15959b = tVar;
            }

            @Override // android.telephony.PhoneStateListener
            public void onCallStateChanged(int i10, String str) {
                if (this.f15958a.A()) {
                    return;
                }
                this.f15959b.b(wo.t.a(Integer.valueOf(i10), str));
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(TelephonyManager telephonyManager, AiGuardSceneService aiGuardSceneService, bp.f fVar) {
            super(2, fVar);
            this.f15956c = telephonyManager;
            this.f15957d = aiGuardSceneService;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final wo.w n(TelephonyManager telephonyManager, C0232a c0232a) {
            telephonyManager.listen(c0232a, 0);
            return wo.w.f45339a;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final bp.f create(Object obj, bp.f fVar) {
            a aVar = new a(this.f15956c, this.f15957d, fVar);
            aVar.f15955b = obj;
            return aVar;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) {
            Object objE = cp.b.e();
            int i10 = this.f15954a;
            if (i10 == 0) {
                wo.q.b(obj);
                zp.t tVar = (zp.t) this.f15955b;
                bp.j.b bVarI = tVar.getCoroutineContext().i(k0.f46328b);
                lp.n.c(bVarI);
                final C0232a c0232a = new C0232a(this.f15957d, tVar, s1.a((k0) bVarI));
                this.f15956c.listen(c0232a, 32);
                final TelephonyManager telephonyManager = this.f15956c;
                kp.a aVar = new kp.a() { // from class: com.miui.electricrisk.d
                    @Override // kp.a
                    public final Object invoke() {
                        return AiGuardSceneService.a.n(telephonyManager, c0232a);
                    }
                };
                this.f15954a = 1;
                if (zp.r.a(tVar, aVar, this) == objE) {
                    return objE;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                wo.q.b(obj);
            }
            return wo.w.f45339a;
        }

        /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
        @Override // kp.p
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public final Object invoke(zp.t tVar, bp.f fVar) {
            return ((a) create(tVar, fVar)).invokeSuspend(wo.w.f45339a);
        }
    }

    static final class b extends kotlin.coroutines.jvm.internal.j implements kp.p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f15960a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f15962c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ lp.a0 f15963d;

        static final class a implements aq.g {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ AiGuardSceneService f15964a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ int f15965b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ lp.a0 f15966c;

            /* JADX INFO: renamed from: com.miui.electricrisk.AiGuardSceneService$b$a$a, reason: collision with other inner class name */
            static final class C0233a extends kotlin.coroutines.jvm.internal.d {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                int f15967a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                /* synthetic */ Object f15968b;

                /* JADX INFO: renamed from: d, reason: collision with root package name */
                int f15970d;

                C0233a(bp.f fVar) {
                    super(fVar);
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    this.f15968b = obj;
                    this.f15970d |= Integer.MIN_VALUE;
                    return a.this.emit(null, this);
                }
            }

            a(AiGuardSceneService aiGuardSceneService, int i10, lp.a0 a0Var) {
                this.f15964a = aiGuardSceneService;
                this.f15965b = i10;
                this.f15966c = a0Var;
            }

            /* JADX DEBUG: Method merged with bridge method: emit(Ljava/lang/Object;Lbp/f;)Ljava/lang/Object; */
            /* JADX WARN: Code duplicated, block: B:7:0x0013  */
            @Override // aq.g
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final Object emit(wo.o oVar, bp.f fVar) {
                C0233a c0233a;
                int iIntValue;
                int i10;
                if (fVar instanceof C0233a) {
                    c0233a = (C0233a) fVar;
                    int i11 = c0233a.f15970d;
                    if ((i11 & Integer.MIN_VALUE) != 0) {
                        c0233a.f15970d = i11 - Integer.MIN_VALUE;
                    } else {
                        c0233a = new C0233a(fVar);
                    }
                } else {
                    c0233a = new C0233a(fVar);
                }
                Object obj = c0233a.f15968b;
                Object objE = cp.b.e();
                int i12 = c0233a.f15970d;
                if (i12 == 0) {
                    wo.q.b(obj);
                    iIntValue = ((Number) oVar.a()).intValue();
                    String str = (String) oVar.b();
                    if (iIntValue != 0) {
                        if (iIntValue == 2 && str != null && this.f15964a.y(str)) {
                            Intent intentPutExtra = this.f15964a.v(1).putExtra("PHONE_SCAM_SCENE", 2).putExtra("CAPABILITIES", this.f15965b);
                            lp.n.e(intentPutExtra, "putExtra(...)");
                            this.f15964a.startService(intentPutExtra);
                            this.f15964a.lastWorkerIntent = intentPutExtra;
                            this.f15964a.workerStarted.set(true);
                            this.f15964a.F();
                            Log.i("AiGuardDaemon", "Phone scam detection is starting.");
                        }
                    } else if (this.f15966c.f34783a == 2) {
                        c0233a.f15967a = iIntValue;
                        c0233a.f15970d = 1;
                        if (y0.a(500L, c0233a) == objE) {
                            return objE;
                        }
                        i10 = iIntValue;
                    }
                    this.f15966c.f34783a = iIntValue;
                    return wo.w.f45339a;
                }
                if (i12 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                i10 = c0233a.f15967a;
                wo.q.b(obj);
                this.f15964a.D();
                this.f15964a.workerStarted.set(false);
                iIntValue = i10;
                this.f15966c.f34783a = iIntValue;
                return wo.w.f45339a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(int i10, lp.a0 a0Var, bp.f fVar) {
            super(2, fVar);
            this.f15962c = i10;
            this.f15963d = a0Var;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final bp.f create(Object obj, bp.f fVar) {
            return AiGuardSceneService.this.new b(this.f15962c, this.f15963d, fVar);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) {
            Object objE = cp.b.e();
            int i10 = this.f15960a;
            if (i10 == 0) {
                wo.q.b(obj);
                aq.f fVarT = AiGuardSceneService.this.t();
                a aVar = new a(AiGuardSceneService.this, this.f15962c, this.f15963d);
                this.f15960a = 1;
                if (fVarT.collect(aVar, this) == objE) {
                    return objE;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                wo.q.b(obj);
            }
            return wo.w.f45339a;
        }

        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
        /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
        @Override // kp.p
        public final Object invoke(o0 o0Var, bp.f fVar) {
            return ((b) create(o0Var, fVar)).invokeSuspend(wo.w.f45339a);
        }
    }

    static final class c extends kotlin.coroutines.jvm.internal.j implements kp.p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f15971a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        /* synthetic */ Object f15972b;

        c(bp.f fVar) {
            super(2, fVar);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final bp.f create(Object obj, bp.f fVar) {
            c cVar = new c(fVar);
            cVar.f15972b = obj;
            return cVar;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) {
            cp.b.e();
            if (this.f15971a != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            wo.q.b(obj);
            String str = (String) this.f15972b;
            Log.i("AiGuardDaemon", "Established: " + str + ".");
            jo.k.f33544c.a().m1(new io.n(str, System.currentTimeMillis(), 0L, 0L, true, false));
            return wo.w.f45339a;
        }

        /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
        @Override // kp.p
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public final Object invoke(String str, bp.f fVar) {
            return ((c) create(str, fVar)).invokeSuspend(wo.w.f45339a);
        }
    }

    static final class d extends kotlin.coroutines.jvm.internal.j implements kp.p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f15973a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        /* synthetic */ Object f15974b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f15975c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ AiGuardSceneService f15976d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(int i10, AiGuardSceneService aiGuardSceneService, bp.f fVar) {
            super(2, fVar);
            this.f15975c = i10;
            this.f15976d = aiGuardSceneService;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final bp.f create(Object obj, bp.f fVar) {
            d dVar = new d(this.f15975c, this.f15976d, fVar);
            dVar.f15974b = obj;
            return dVar;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) {
            int[] iArr;
            cp.b.e();
            if (this.f15973a != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            wo.q.b(obj);
            String str = (String) this.f15974b;
            Log.i("AiGuardDaemon", "VoIP scam detection is starting.");
            if (lp.n.b(str, AppConstants.Package.PACKAGE_NAME_QQ)) {
                iArr = new int[]{3, 1};
            } else {
                if (!lp.n.b(str, AppConstants.Package.PACKAGE_NAME_MM)) {
                    Log.e("AiGuardDaemon", "Activity missing:" + str);
                    return wo.w.f45339a;
                }
                iArr = new int[]{4, 2};
            }
            int i10 = iArr[0];
            int i11 = iArr[1];
            if ((this.f15975c & 1) != 0) {
                Intent intentPutExtra = this.f15976d.v(1).putExtra("PHONE_SCAM_SCENE", i10).putExtra("CAPABILITIES", this.f15975c);
                lp.n.e(intentPutExtra, "putExtra(...)");
                this.f15976d.startService(intentPutExtra);
                this.f15976d.lastWorkerIntent = intentPutExtra;
                this.f15976d.workerStarted.set(true);
                this.f15976d.F();
            }
            boolean zIsUsingCamera = AiGuardUtils.isUsingCamera(this.f15976d, str);
            if ((this.f15975c & 2) != 0 && zIsUsingCamera) {
                Intent intentPutExtra2 = this.f15976d.v(2).putExtra("VIDEO_SCAM_SCENE", i11).putExtra("CAPABILITIES", this.f15975c);
                lp.n.e(intentPutExtra2, "putExtra(...)");
                this.f15976d.startService(intentPutExtra2);
                this.f15976d.lastWorkerIntent = intentPutExtra2;
                this.f15976d.workerStarted.set(true);
                this.f15976d.F();
            }
            return wo.w.f45339a;
        }

        /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
        @Override // kp.p
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public final Object invoke(String str, bp.f fVar) {
            return ((d) create(str, fVar)).invokeSuspend(wo.w.f45339a);
        }
    }

    static final class e extends kotlin.coroutines.jvm.internal.j implements kp.p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f15977a;

        e(bp.f fVar) {
            super(2, fVar);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final bp.f create(Object obj, bp.f fVar) {
            return AiGuardSceneService.this.new e(fVar);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) {
            Object objE = cp.b.e();
            int i10 = this.f15977a;
            if (i10 == 0) {
                wo.q.b(obj);
                if (AiGuardSceneService.this.workerStarted.get()) {
                    this.f15977a = 1;
                    if (y0.a(500L, this) == objE) {
                        return objE;
                    }
                }
                Log.i("AiGuardDaemon", "Paused.");
                return wo.w.f45339a;
            }
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            wo.q.b(obj);
            AiGuardSceneService.this.D();
            AiGuardSceneService.this.workerStarted.set(false);
            Log.i("AiGuardDaemon", "Paused.");
            return wo.w.f45339a;
        }

        /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
        @Override // kp.p
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public final Object invoke(String str, bp.f fVar) {
            return ((e) create(str, fVar)).invokeSuspend(wo.w.f45339a);
        }
    }

    static final class f extends kotlin.coroutines.jvm.internal.j implements kp.p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f15979a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        /* synthetic */ Object f15980b;

        f(bp.f fVar) {
            super(2, fVar);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final bp.f create(Object obj, bp.f fVar) {
            f fVar2 = AiGuardSceneService.this.new f(fVar);
            fVar2.f15980b = obj;
            return fVar2;
        }

        /* JADX WARN: Code duplicated, block: B:17:0x0055  */
        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) {
            String str;
            String str2;
            Object objE = cp.b.e();
            int i10 = this.f15979a;
            if (i10 == 0) {
                wo.q.b(obj);
                str = (String) this.f15980b;
                if (AiGuardSceneService.this.workerStarted.get()) {
                    this.f15980b = str;
                    this.f15979a = 1;
                    if (y0.a(500L, this) == objE) {
                        return objE;
                    }
                    str2 = str;
                }
                Log.i("AiGuardDaemon", "Stopped.");
                if (str != null) {
                    AiGuardSceneService.this.H(str);
                }
                return wo.w.f45339a;
            }
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            str2 = (String) this.f15980b;
            wo.q.b(obj);
            AiGuardSceneService.this.D();
            AiGuardSceneService.this.workerStarted.set(false);
            str = str2;
            Log.i("AiGuardDaemon", "Stopped.");
            if (str != null) {
                AiGuardSceneService.this.H(str);
            }
            return wo.w.f45339a;
        }

        /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
        @Override // kp.p
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public final Object invoke(String str, bp.f fVar) {
            return ((f) create(str, fVar)).invokeSuspend(wo.w.f45339a);
        }
    }

    static final class g extends kotlin.coroutines.jvm.internal.j implements kp.p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f15982a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        /* synthetic */ Object f15983b;

        g(bp.f fVar) {
            super(2, fVar);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final bp.f create(Object obj, bp.f fVar) {
            g gVar = new g(fVar);
            gVar.f15983b = obj;
            return gVar;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) {
            cp.b.e();
            if (this.f15982a != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            wo.q.b(obj);
            String str = (String) this.f15983b;
            Log.i("AiGuardDaemon", "Alerted. " + str);
            if (str != null) {
                jo.k.f33544c.a().f2(new io.n(str, 0L, System.currentTimeMillis(), 0L, true, true), io.p.TYPE_CHECK_IS_FRAUD_CALL);
            }
            return wo.w.f45339a;
        }

        /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
        @Override // kp.p
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public final Object invoke(String str, bp.f fVar) {
            return ((g) create(str, fVar)).invokeSuspend(wo.w.f45339a);
        }
    }

    static final class h extends kotlin.coroutines.jvm.internal.j implements kp.p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f15984a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ AtomicReference f15986c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ kp.p f15987d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ lp.y f15988e;

        static final class a implements aq.g {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ kp.p f15989a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ AtomicReference f15990b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ AiGuardSceneService f15991c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ lp.y f15992d;

            /* JADX INFO: renamed from: com.miui.electricrisk.AiGuardSceneService$h$a$a, reason: collision with other inner class name */
            static final class C0234a extends kotlin.coroutines.jvm.internal.d {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                /* synthetic */ Object f15993a;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                int f15995c;

                C0234a(bp.f fVar) {
                    super(fVar);
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    this.f15993a = obj;
                    this.f15995c |= Integer.MIN_VALUE;
                    return a.this.a(0, this);
                }
            }

            a(kp.p pVar, AtomicReference atomicReference, AiGuardSceneService aiGuardSceneService, lp.y yVar) {
                this.f15989a = pVar;
                this.f15990b = atomicReference;
                this.f15991c = aiGuardSceneService;
                this.f15992d = yVar;
            }

            /* JADX WARN: Code duplicated, block: B:7:0x0013  */
            public final Object a(int i10, bp.f fVar) {
                C0234a c0234a;
                if (fVar instanceof C0234a) {
                    c0234a = (C0234a) fVar;
                    int i11 = c0234a.f15995c;
                    if ((i11 & Integer.MIN_VALUE) != 0) {
                        c0234a.f15995c = i11 - Integer.MIN_VALUE;
                    } else {
                        c0234a = new C0234a(fVar);
                    }
                } else {
                    c0234a = new C0234a(fVar);
                }
                Object obj = c0234a.f15993a;
                Object objE = cp.b.e();
                int i12 = c0234a.f15995c;
                if (i12 == 0) {
                    wo.q.b(obj);
                    kp.p pVar = this.f15989a;
                    Object obj2 = this.f15990b.get();
                    c0234a.f15995c = 1;
                    if (pVar.invoke(obj2, c0234a) == objE) {
                        return objE;
                    }
                } else {
                    if (i12 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    wo.q.b(obj);
                }
                this.f15990b.set(null);
                this.f15991c.currentVoipPackageName = "";
                this.f15992d.f34811a = false;
                return wo.w.f45339a;
            }

            @Override // aq.g
            public /* bridge */ /* synthetic */ Object emit(Object obj, bp.f fVar) {
                return a(((Number) obj).intValue(), fVar);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        h(AtomicReference atomicReference, kp.p pVar, lp.y yVar, bp.f fVar) {
            super(2, fVar);
            this.f15986c = atomicReference;
            this.f15987d = pVar;
            this.f15988e = yVar;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final bp.f create(Object obj, bp.f fVar) {
            return AiGuardSceneService.this.new h(this.f15986c, this.f15987d, this.f15988e, fVar);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) {
            Object objE = cp.b.e();
            int i10 = this.f15984a;
            if (i10 == 0) {
                wo.q.b(obj);
                aq.f fVarK = AiGuardSceneService.this.K(this.f15986c);
                a aVar = new a(this.f15987d, this.f15986c, AiGuardSceneService.this, this.f15988e);
                this.f15984a = 1;
                if (fVarK.collect(aVar, this) == objE) {
                    return objE;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                wo.q.b(obj);
            }
            return wo.w.f45339a;
        }

        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
        /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
        @Override // kp.p
        public final Object invoke(o0 o0Var, bp.f fVar) {
            return ((h) create(o0Var, fVar)).invokeSuspend(wo.w.f45339a);
        }
    }

    static final class i extends kotlin.coroutines.jvm.internal.j implements kp.p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f15996a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ aq.f f15997b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ lp.c0 f15998c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ kp.p f15999d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ AtomicReference f16000e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ lp.y f16001f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ kp.p f16002g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ kp.p f16003h;

        static final class a implements aq.g {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ lp.c0 f16004a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ kp.p f16005b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ AtomicReference f16006c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ lp.y f16007d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ kp.p f16008e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            final /* synthetic */ kp.p f16009f;

            /* JADX INFO: renamed from: com.miui.electricrisk.AiGuardSceneService$i$a$a, reason: collision with other inner class name */
            static final class C0235a extends kotlin.coroutines.jvm.internal.d {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                Object f16010a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                Object f16011b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                /* synthetic */ Object f16012c;

                /* JADX INFO: renamed from: e, reason: collision with root package name */
                int f16014e;

                C0235a(bp.f fVar) {
                    super(fVar);
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    this.f16012c = obj;
                    this.f16014e |= Integer.MIN_VALUE;
                    return a.this.emit(null, this);
                }
            }

            a(lp.c0 c0Var, kp.p pVar, AtomicReference atomicReference, lp.y yVar, kp.p pVar2, kp.p pVar3) {
                this.f16004a = c0Var;
                this.f16005b = pVar;
                this.f16006c = atomicReference;
                this.f16007d = yVar;
                this.f16008e = pVar2;
                this.f16009f = pVar3;
            }

            /* JADX DEBUG: Method merged with bridge method: emit(Ljava/lang/Object;Lbp/f;)Ljava/lang/Object; */
            /* JADX WARN: Code duplicated, block: B:31:0x00b9 A[PHI: r10
              0x00b9: PHI (r10v5 android.content.ComponentName) = 
              (r10v3 android.content.ComponentName)
              (r10v3 android.content.ComponentName)
              (r10v3 android.content.ComponentName)
              (r10v17 android.content.ComponentName)
             binds: [B:25:0x0091, B:27:0x009f, B:29:0x00b6, B:16:0x003b] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Code duplicated, block: B:33:0x00c1  */
            /* JADX WARN: Code duplicated, block: B:7:0x0013  */
            /* JADX WARN: Code restructure failed: missing block: B:21:0x0084, code lost:
            
                if (r11.invoke(r8, r0) == r1) goto L39;
             */
            /* JADX WARN: Code restructure failed: missing block: B:38:0x00ea, code lost:
            
                if (r10.invoke(r11, r0) == r1) goto L39;
             */
            @Override // aq.g
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final Object emit(wo.o oVar, bp.f fVar) {
                C0235a c0235a;
                ComponentName componentName;
                ComponentName componentName2;
                if (fVar instanceof C0235a) {
                    c0235a = (C0235a) fVar;
                    int i10 = c0235a.f16014e;
                    if ((i10 & Integer.MIN_VALUE) != 0) {
                        c0235a.f16014e = i10 - Integer.MIN_VALUE;
                    } else {
                        c0235a = new C0235a(fVar);
                    }
                } else {
                    c0235a = new C0235a(fVar);
                }
                Object obj = c0235a.f16012c;
                Object objE = cp.b.e();
                int i11 = c0235a.f16014e;
                if (i11 != 0) {
                    if (i11 == 1) {
                        componentName2 = (ComponentName) c0235a.f16011b;
                        componentName = (ComponentName) c0235a.f16010a;
                        wo.q.b(obj);
                    } else if (i11 == 2) {
                        componentName2 = (ComponentName) c0235a.f16010a;
                        wo.q.b(obj);
                        if (this.f16006c.get() != null || !com.miui.electricrisk.k.f16118c.contains(componentName2.getClassName()) || this.f16007d.f34811a) {
                            return wo.w.f45339a;
                        }
                        kp.p pVar = this.f16009f;
                        Object obj2 = this.f16006c.get();
                        lp.n.e(obj2, "get(...)");
                        c0235a.f16010a = null;
                        c0235a.f16011b = null;
                        c0235a.f16014e = 3;
                    } else {
                        if (i11 != 3) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        wo.q.b(obj);
                    }
                    return wo.w.f45339a;
                }
                wo.q.b(obj);
                componentName = (ComponentName) oVar.a();
                componentName2 = (ComponentName) oVar.b();
                this.f16004a.f34786a = componentName2.getClassName();
                if (lp.n.b(componentName2, com.miui.electricrisk.k.f16117b)) {
                    kp.p pVar2 = this.f16005b;
                    Object obj3 = this.f16006c.get();
                    c0235a.f16010a = componentName;
                    c0235a.f16011b = componentName2;
                    c0235a.f16014e = 1;
                } else {
                    if (this.f16006c.get() != null || !com.miui.electricrisk.k.f16118c.contains(componentName.getClassName())) {
                        if (this.f16006c.get() != null) {
                        }
                        return wo.w.f45339a;
                    }
                    kp.p pVar3 = this.f16008e;
                    Object obj4 = this.f16006c.get();
                    lp.n.e(obj4, "get(...)");
                    c0235a.f16010a = componentName2;
                    c0235a.f16011b = null;
                    c0235a.f16014e = 2;
                    if (pVar3.invoke(obj4, c0235a) != objE) {
                        if (this.f16006c.get() != null) {
                        }
                        return wo.w.f45339a;
                    }
                }
                return objE;
                this.f16007d.f34811a = true;
                if (this.f16006c.get() != null) {
                    if (this.f16006c.get() != null) {
                    }
                    return wo.w.f45339a;
                }
                if (this.f16006c.get() != null) {
                }
                return wo.w.f45339a;
                return objE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        i(aq.f fVar, lp.c0 c0Var, kp.p pVar, AtomicReference atomicReference, lp.y yVar, kp.p pVar2, kp.p pVar3, bp.f fVar2) {
            super(2, fVar2);
            this.f15997b = fVar;
            this.f15998c = c0Var;
            this.f15999d = pVar;
            this.f16000e = atomicReference;
            this.f16001f = yVar;
            this.f16002g = pVar2;
            this.f16003h = pVar3;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final bp.f create(Object obj, bp.f fVar) {
            return new i(this.f15997b, this.f15998c, this.f15999d, this.f16000e, this.f16001f, this.f16002g, this.f16003h, fVar);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) {
            Object objE = cp.b.e();
            int i10 = this.f15996a;
            if (i10 == 0) {
                wo.q.b(obj);
                aq.f fVar = this.f15997b;
                a aVar = new a(this.f15998c, this.f15999d, this.f16000e, this.f16001f, this.f16002g, this.f16003h);
                this.f15996a = 1;
                if (fVar.collect(aVar, this) == objE) {
                    return objE;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                wo.q.b(obj);
            }
            return wo.w.f45339a;
        }

        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
        /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
        @Override // kp.p
        public final Object invoke(o0 o0Var, bp.f fVar) {
            return ((i) create(o0Var, fVar)).invokeSuspend(wo.w.f45339a);
        }
    }

    static final class j extends kotlin.coroutines.jvm.internal.j implements kp.p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f16015a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ aq.f f16016b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ kp.p f16017c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ AtomicReference f16018d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ AiGuardSceneService f16019e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ lp.c0 f16020f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ lp.y f16021g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ kp.p f16022h;

        static final class a implements aq.g {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ kp.p f16023a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ AtomicReference f16024b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ AiGuardSceneService f16025c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ lp.c0 f16026d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ lp.y f16027e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            final /* synthetic */ kp.p f16028f;

            /* JADX INFO: renamed from: com.miui.electricrisk.AiGuardSceneService$j$a$a, reason: collision with other inner class name */
            static final class C0236a extends kotlin.coroutines.jvm.internal.d {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                Object f16029a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                /* synthetic */ Object f16030b;

                /* JADX INFO: renamed from: d, reason: collision with root package name */
                int f16032d;

                C0236a(bp.f fVar) {
                    super(fVar);
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    this.f16030b = obj;
                    this.f16032d |= Integer.MIN_VALUE;
                    return a.this.emit(null, this);
                }
            }

            a(kp.p pVar, AtomicReference atomicReference, AiGuardSceneService aiGuardSceneService, lp.c0 c0Var, lp.y yVar, kp.p pVar2) {
                this.f16023a = pVar;
                this.f16024b = atomicReference;
                this.f16025c = aiGuardSceneService;
                this.f16026d = c0Var;
                this.f16027e = yVar;
                this.f16028f = pVar2;
            }

            /* JADX DEBUG: Method merged with bridge method: emit(Ljava/lang/Object;Lbp/f;)Ljava/lang/Object; */
            /* JADX WARN: Code duplicated, block: B:7:0x0013  */
            /* JADX WARN: Code restructure failed: missing block: B:26:0x0081, code lost:
            
                if (r7.invoke(r6, r0) == r1) goto L27;
             */
            @Override // aq.g
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final Object emit(String str, bp.f fVar) {
                C0236a c0236a;
                if (fVar instanceof C0236a) {
                    c0236a = (C0236a) fVar;
                    int i10 = c0236a.f16032d;
                    if ((i10 & Integer.MIN_VALUE) != 0) {
                        c0236a.f16032d = i10 - Integer.MIN_VALUE;
                    } else {
                        c0236a = new C0236a(fVar);
                    }
                } else {
                    c0236a = new C0236a(fVar);
                }
                Object obj = c0236a.f16030b;
                Object objE = cp.b.e();
                int i11 = c0236a.f16032d;
                if (i11 != 0) {
                    if (i11 == 1) {
                        str = (String) c0236a.f16029a;
                        wo.q.b(obj);
                    } else {
                        if (i11 != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        wo.q.b(obj);
                    }
                    return wo.w.f45339a;
                }
                wo.q.b(obj);
                if (com.miui.electricrisk.k.f16116a.contains(str)) {
                    kp.p pVar = this.f16023a;
                    c0236a.f16029a = str;
                    c0236a.f16032d = 1;
                    if (pVar.invoke(str, c0236a) != objE) {
                    }
                    return objE;
                }
                return wo.w.f45339a;
                this.f16024b.set(str);
                this.f16025c.currentVoipPackageName = str;
                if (yo.o.I(com.miui.electricrisk.k.f16118c, this.f16026d.f34786a) && !this.f16027e.f34811a) {
                    kp.p pVar2 = this.f16028f;
                    c0236a.f16029a = null;
                    c0236a.f16032d = 2;
                }
                return wo.w.f45339a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        j(aq.f fVar, kp.p pVar, AtomicReference atomicReference, AiGuardSceneService aiGuardSceneService, lp.c0 c0Var, lp.y yVar, kp.p pVar2, bp.f fVar2) {
            super(2, fVar2);
            this.f16016b = fVar;
            this.f16017c = pVar;
            this.f16018d = atomicReference;
            this.f16019e = aiGuardSceneService;
            this.f16020f = c0Var;
            this.f16021g = yVar;
            this.f16022h = pVar2;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final bp.f create(Object obj, bp.f fVar) {
            return new j(this.f16016b, this.f16017c, this.f16018d, this.f16019e, this.f16020f, this.f16021g, this.f16022h, fVar);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) {
            Object objE = cp.b.e();
            int i10 = this.f16015a;
            if (i10 == 0) {
                wo.q.b(obj);
                aq.f fVar = this.f16016b;
                a aVar = new a(this.f16017c, this.f16018d, this.f16019e, this.f16020f, this.f16021g, this.f16022h);
                this.f16015a = 1;
                if (fVar.collect(aVar, this) == objE) {
                    return objE;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                wo.q.b(obj);
            }
            return wo.w.f45339a;
        }

        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
        /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
        @Override // kp.p
        public final Object invoke(o0 o0Var, bp.f fVar) {
            return ((j) create(o0Var, fVar)).invokeSuspend(wo.w.f45339a);
        }
    }

    static final class k extends kotlin.coroutines.jvm.internal.j implements kp.p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f16033a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private /* synthetic */ Object f16034b;

        public static final class a extends BroadcastReceiver {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ AiGuardSceneService f16036a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ zp.t f16037b;

            a(AiGuardSceneService aiGuardSceneService, zp.t tVar) {
                this.f16036a = aiGuardSceneService;
                this.f16037b = tVar;
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String stringExtra;
                if (this.f16036a.A() || intent == null || (stringExtra = intent.getStringExtra("pkg")) == null) {
                    return;
                }
                zp.k.b(this.f16037b.b(stringExtra));
            }
        }

        k(bp.f fVar) {
            super(2, fVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final wo.w n(AiGuardSceneService aiGuardSceneService, a aVar) {
            aiGuardSceneService.unregisterReceiver(aVar);
            return wo.w.f45339a;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final bp.f create(Object obj, bp.f fVar) {
            k kVar = AiGuardSceneService.this.new k(fVar);
            kVar.f16034b = obj;
            return kVar;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) {
            Object objE = cp.b.e();
            int i10 = this.f16033a;
            if (i10 == 0) {
                wo.q.b(obj);
                zp.t tVar = (zp.t) this.f16034b;
                final a aVar = new a(AiGuardSceneService.this, tVar);
                com.miui.common.utils.d0.r(AiGuardSceneService.this, aVar, m2.G(0), new IntentFilter("miui.media.AUDIO_VOIP_RECORD_STATE_CHANGED_ACTION"), "com.miui.permission.AUDIO_VOIP_RECORD_STATE_CHANGED_ACTION", null, 2);
                final AiGuardSceneService aiGuardSceneService = AiGuardSceneService.this;
                kp.a aVar2 = new kp.a() { // from class: com.miui.electricrisk.f
                    @Override // kp.a
                    public final Object invoke() {
                        return AiGuardSceneService.k.n(aiGuardSceneService, aVar);
                    }
                };
                this.f16033a = 1;
                if (zp.r.a(tVar, aVar2, this) == objE) {
                    return objE;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                wo.q.b(obj);
            }
            return wo.w.f45339a;
        }

        /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
        @Override // kp.p
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public final Object invoke(zp.t tVar, bp.f fVar) {
            return ((k) create(tVar, fVar)).invokeSuspend(wo.w.f45339a);
        }
    }

    static final class l extends kotlin.coroutines.jvm.internal.j implements kp.p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f16038a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private /* synthetic */ Object f16039b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ AudioManager f16040c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ AiGuardSceneService f16041d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ AtomicReference f16042e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        l(AudioManager audioManager, AiGuardSceneService aiGuardSceneService, AtomicReference atomicReference, bp.f fVar) {
            super(2, fVar);
            this.f16040c = audioManager;
            this.f16041d = aiGuardSceneService;
            this.f16042e = atomicReference;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void p(AiGuardSceneService aiGuardSceneService, AudioManager audioManager, AtomicReference atomicReference, zp.t tVar, int i10) {
            if (aiGuardSceneService.A()) {
                return;
            }
            List<AudioRecordingConfiguration> activeRecordingConfigurations = audioManager.getActiveRecordingConfigurations();
            lp.n.e(activeRecordingConfigurations, "getActiveRecordingConfigurations(...)");
            List<AudioRecordingConfiguration> list = activeRecordingConfigurations;
            ArrayList arrayList = new ArrayList(yo.o.r(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(((AudioRecordingConfiguration) it.next()).getClientPackageName());
            }
            if (i10 != 0 || arrayList.contains(atomicReference.get())) {
                return;
            }
            tVar.b(Integer.valueOf(i10));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final wo.w q(AudioManager audioManager, AudioManager$OnModeChangedListener audioManager$OnModeChangedListener) {
            audioManager.removeOnModeChangedListener(audioManager$OnModeChangedListener);
            return wo.w.f45339a;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final bp.f create(Object obj, bp.f fVar) {
            l lVar = new l(this.f16040c, this.f16041d, this.f16042e, fVar);
            lVar.f16039b = obj;
            return lVar;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) {
            Object objE = cp.b.e();
            int i10 = this.f16038a;
            if (i10 == 0) {
                wo.q.b(obj);
                final zp.t tVar = (zp.t) this.f16039b;
                final AiGuardSceneService aiGuardSceneService = this.f16041d;
                final AudioManager audioManager = this.f16040c;
                final AtomicReference atomicReference = this.f16042e;
                final AudioManager$OnModeChangedListener audioManager$OnModeChangedListener = new AudioManager$OnModeChangedListener() { // from class: com.miui.electricrisk.i
                    public final void onModeChanged(int i11) {
                        AiGuardSceneService.l.p(aiGuardSceneService, audioManager, atomicReference, tVar, i11);
                    }
                };
                AudioManager audioManager2 = this.f16040c;
                bp.j.b bVarI = tVar.getCoroutineContext().i(k0.f46328b);
                lp.n.c(bVarI);
                audioManager2.addOnModeChangedListener(s1.a((k0) bVarI), audioManager$OnModeChangedListener);
                final AudioManager audioManager3 = this.f16040c;
                kp.a aVar = new kp.a() { // from class: com.miui.electricrisk.j
                    @Override // kp.a
                    public final Object invoke() {
                        return AiGuardSceneService.l.q(audioManager3, audioManager$OnModeChangedListener);
                    }
                };
                this.f16038a = 1;
                if (zp.r.a(tVar, aVar, this) == objE) {
                    return objE;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                wo.q.b(obj);
            }
            return wo.w.f45339a;
        }

        /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
        @Override // kp.p
        /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
        public final Object invoke(zp.t tVar, bp.f fVar) {
            return ((l) create(tVar, fVar)).invokeSuspend(wo.w.f45339a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean A() {
        return m2.f() != this.userId;
    }

    private final aq.f B(List packages, List activities) {
        return aq.h.s(aq.h.f(new AiGuardSceneService$monitorActivities$1(packages, activities, this, null)), d1.c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean C() {
        try {
            List historicalProcessExitReasons = ((ActivityManager) getSystemService(ActivityManager.class)).getHistoricalProcessExitReasons("com.miui.guardprovider", 0, 1);
            lp.n.e(historicalProcessExitReasons, "getHistoricalProcessExitReasons(...)");
            if (historicalProcessExitReasons.isEmpty()) {
                Log.d("AiGuardDaemon", "No exit reasons found, allow restart");
                return true;
            }
            int reason = a2.j.a(historicalProcessExitReasons.get(0)).getReason();
            boolean z10 = reason != 1;
            Log.d("AiGuardDaemon", "ai process last exit: reason=" + reason + " shouldRestart=" + z10);
            return z10;
        } catch (Exception e10) {
            Log.e("AiGuardDaemon", "shouldRestartByExitReason failed", e10);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void D() {
        G();
        stopService(new Intent().setComponent(com.miui.electricrisk.k.f16119d));
        Log.i("AiGuardDaemon", "shutdownWorkerServiceNow");
    }

    private final void E() {
        int capabilities = AiGuardUtils.getCapabilities(this);
        if ((capabilities & 1) != 0) {
            lp.a0 a0Var = new lp.a0();
            a0Var.f34783a = -1;
            xp.k.d(this.scope, null, null, new b(capabilities, a0Var, null), 3, null);
        }
        I(new c(null), new d(capabilities, this, null), new e(null), new f(null), new g(null));
        Log.i("AiGuardDaemon", "Daemon Started.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void F() {
        if (this.processObserver != null) {
            return;
        }
        synchronized (this.restartLock) {
            this.lastRestartTime = 0L;
            wo.w wVar = wo.w.f45339a;
        }
        IMiuiProcessObserver iMiuiProcessObserver = new IMiuiProcessObserver() { // from class: com.miui.electricrisk.AiGuardSceneService$startProcessMonitor$2

            static final class a extends kotlin.coroutines.jvm.internal.j implements kp.p {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                int f16051a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ AiGuardSceneService f16052b;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                a(AiGuardSceneService aiGuardSceneService, bp.f fVar) {
                    super(2, fVar);
                    this.f16052b = aiGuardSceneService;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final bp.f create(Object obj, bp.f fVar) {
                    return new a(this.f16052b, fVar);
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    Object objE = cp.b.e();
                    int i10 = this.f16051a;
                    if (i10 == 0) {
                        wo.q.b(obj);
                        this.f16051a = 1;
                        if (y0.a(1500L, this) == objE) {
                            return objE;
                        }
                    } else {
                        if (i10 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        wo.q.b(obj);
                    }
                    if (!this.f16052b.workerStarted.get()) {
                        return wo.w.f45339a;
                    }
                    if (!this.f16052b.C()) {
                        Log.d("AiGuardDaemon", "ai process exited by itself, skip restart");
                        return wo.w.f45339a;
                    }
                    Object obj2 = this.f16052b.restartLock;
                    AiGuardSceneService aiGuardSceneService = this.f16052b;
                    synchronized (obj2) {
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        if (jCurrentTimeMillis - aiGuardSceneService.lastRestartTime < aiGuardSceneService.restartDebounceMs) {
                            return wo.w.f45339a;
                        }
                        if (!aiGuardSceneService.w()) {
                            Log.w("AiGuardDaemon", "GuardProvider:ai process died, restarting");
                            Intent intent = aiGuardSceneService.lastWorkerIntent;
                            if (intent != null) {
                                try {
                                    aiGuardSceneService.lastRestartTime = jCurrentTimeMillis;
                                    aiGuardSceneService.startService(intent);
                                    Log.i("AiGuardDaemon", "GuardProvider:ai restarted by process monitor");
                                } catch (Exception e10) {
                                    Log.e("AiGuardDaemon", "Process monitor restart failed", e10);
                                }
                            }
                        }
                        return wo.w.f45339a;
                    }
                }

                /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
                /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
                @Override // kp.p
                public final Object invoke(o0 o0Var, bp.f fVar) {
                    return ((a) create(o0Var, fVar)).invokeSuspend(wo.w.f45339a);
                }
            }

            @Override // android.app.IMiuiProcessObserver, android.app.IProcessObserver
            public void onForegroundActivitiesChanged(int pid, int uid, boolean foregroundActivities) {
            }

            @Override // android.app.IMiuiProcessObserver, android.app.IProcessObserver
            public void onForegroundServicesChanged(int pid, int uid, int serviceTypes) {
            }

            @Override // android.app.IMiuiProcessObserver
            public void onImportanceChanged(int pid, int uid, int importance) {
            }

            @Override // android.app.IMiuiProcessObserver, android.app.IProcessObserver
            public void onProcessDied(int pid, int uid) {
                if (this.f16050a.workerStarted.get()) {
                    int iU = this.f16050a.u();
                    if (iU == -1 || uid == iU) {
                        xp.k.d(this.f16050a.scope, null, null, new a(this.f16050a, null), 3, null);
                    }
                }
            }

            @Override // android.app.IProcessObserver
            public void onProcessStarted(int pid, int processUid, int packageUid, String packageName, String processName) {
                if (lp.n.b(processName, "com.miui.guardprovider:ai")) {
                    Log.i("AiGuardDaemon", "GuardProvider:ai process started (pid=" + pid + ")");
                }
            }

            @Override // android.app.IMiuiProcessObserver
            public void onProcessStateChanged(int pid, int uid, int procState) {
            }
        };
        this.processObserver = iMiuiProcessObserver;
        try {
            ActivityManagerCompat.registerProcessObserver2(iMiuiProcessObserver);
            Log.i("AiGuardDaemon", "Process monitor started");
        } catch (Exception e10) {
            Log.e("AiGuardDaemon", "registerProcessObserver failed", e10);
            this.processObserver = null;
        }
    }

    private final void G() {
        IProcessObserver iProcessObserver = this.processObserver;
        if (iProcessObserver != null) {
            try {
                ActivityManagerCompat.unRegisterProcessObserver(iProcessObserver);
            } catch (Exception unused) {
            }
        }
        this.processObserver = null;
        this.lastWorkerIntent = null;
        Log.i("AiGuardDaemon", "Process monitor stopped");
    }

    private final void I(kp.p onEstablished, kp.p onTopResumed, kp.p onPaused, kp.p onDisconnected, kp.p onAlerted) {
        aq.f fVarJ = J();
        aq.f fVarB = B(com.miui.electricrisk.k.f16116a, com.miui.electricrisk.k.f16118c);
        AtomicReference atomicReference = new AtomicReference();
        lp.c0 c0Var = new lp.c0();
        lp.y yVar = new lp.y();
        xp.k.d(this.scope, null, null, new h(atomicReference, onDisconnected, yVar, null), 3, null);
        xp.k.d(this.scope, null, null, new i(fVarB, c0Var, onAlerted, atomicReference, yVar, onPaused, onTopResumed, null), 3, null);
        xp.k.d(this.scope, null, null, new j(fVarJ, onEstablished, atomicReference, this, c0Var, yVar, onTopResumed, null), 3, null);
    }

    private final aq.f J() {
        return aq.h.s(aq.h.f(new k(null)), d1.c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final aq.f K(AtomicReference pkgRef) {
        return aq.h.f(new l((AudioManager) getSystemService(AudioManager.class), this, pkgRef, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final aq.f t() {
        return aq.h.s(aq.h.f(new a((TelephonyManager) getSystemService(TelephonyManager.class), this, null)), d1.c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int u() {
        if (this.guardProviderUid == -1) {
            try {
                this.guardProviderUid = getPackageManager().getApplicationInfo("com.miui.guardprovider", 0).uid;
            } catch (Exception e10) {
                Log.e("AiGuardDaemon", "getGuardProviderUid failed", e10);
            }
        }
        return this.guardProviderUid;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Intent v(int command) {
        Intent intentPutExtra = new Intent().setComponent(com.miui.electricrisk.k.f16119d).putExtra("COMMAND", command);
        lp.n.e(intentPutExtra, "putExtra(...)");
        return intentPutExtra;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean w() {
        try {
            Process processExec = Runtime.getRuntime().exec(new String[]{"pidof", "com.miui.guardprovider:ai"});
            InputStream inputStream = processExec.getInputStream();
            lp.n.e(inputStream, "getInputStream(...)");
            String string = up.n.D0(hp.j.h(new BufferedReader(new InputStreamReader(inputStream, up.d.f44051b), 8192))).toString();
            processExec.waitFor();
            return string.length() > 0;
        } catch (Exception e10) {
            Log.e("AiGuardDaemon", "isAiProcessAlive check failed", e10);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean y(String phoneNumber) {
        return (x(phoneNumber) || z(phoneNumber)) ? false : true;
    }

    public final void H(String packageName) {
        lp.n.f(packageName, RiskApp.Columns.RISK_PACKAGE_NAME);
        jo.k.f33544c.a().f2(new io.n(packageName, 0L, System.currentTimeMillis(), 0L, false, false), io.p.TYPE_END_CALL);
    }

    @Override // android.app.Service
    protected void dump(FileDescriptor fd2, PrintWriter writer, String[] args) {
        lp.n.f(writer, "writer");
        writer.println("Capabilities: " + AiGuardUtils.getCapabilities(this));
        writer.println("Function Switch ON: " + AiGuardUtils.isAiGuardEnabled(this));
        writer.println("CloudSettings: " + AiGuardCloudController.loadCachedCloudData(this));
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        lp.n.f(intent, "intent");
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        E();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        G();
        String str = null;
        p0.d(this.scope, null, 1, null);
        Log.i("AiGuardDaemon", "Daemon stopped.");
        String str2 = this.currentVoipPackageName;
        if (str2 != null) {
            if (str2 == null) {
                lp.n.s("currentVoipPackageName");
                str2 = null;
            }
            if (str2.length() > 0) {
                String str3 = this.currentVoipPackageName;
                if (str3 == null) {
                    lp.n.s("currentVoipPackageName");
                } else {
                    str = str3;
                }
                H(str);
            }
        }
    }

    public final boolean x(String phoneNumber) throws IOException {
        lp.n.f(phoneNumber, "phoneNumber");
        Cursor cursorQuery = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{"data1"}, "replace(data1,' ','') LIKE ?", new String[]{phoneNumber}, null);
        if (cursorQuery != null) {
            Cursor cursor = cursorQuery;
            try {
                if (cursor.moveToNext()) {
                    hp.b.a(cursor, null);
                    return true;
                }
                wo.w wVar = wo.w.f45339a;
                hp.b.a(cursor, null);
            } catch (Throwable th2) {
                try {
                    throw th2;
                } catch (Throwable th3) {
                    hp.b.a(cursor, th2);
                    throw th3;
                }
            }
        }
        Log.i("AiGuardDaemon", "isInContacts: false");
        return false;
    }

    public final boolean z(String phoneNumber) {
        lp.n.f(phoneNumber, "phoneNumber");
        boolean z10 = YellowPageUtils.getPhoneInfo(this, phoneNumber, true) != null;
        Log.i("AiGuardDaemon", "isYellowPageRecognized: " + z10);
        return z10;
    }
}
