package com.miui.securitycenter;

import aj.c0;
import aj.g0;
import aj.h0;
import aj.j0;
import aj.z;
import ak.q;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.MessageQueue;
import android.provider.Settings;
import android.util.Log;
import androidx.lifecycle.v0;
import androidx.lifecycle.w0;
import b4.d;
import com.google.android.exoplayer2.audio.SilenceSkippingAudioProcessor;
import com.miui.analytics.AnalyticsUtil;
import com.miui.antivirus.service.VirusScanJobService;
import com.miui.common.h;
import com.miui.common.utils.a1;
import com.miui.common.utils.b0;
import com.miui.common.utils.d0;
import com.miui.common.utils.m2;
import com.miui.common.utils.q1;
import com.miui.common.utils.v1;
import com.miui.gamebooster.beauty.BeautyService;
import com.miui.gamebooster.service.GameBoosterService;
import com.miui.gamebooster.utils.i0;
import com.miui.gamebooster.utils.n;
import com.miui.gamebooster.utils.p0;
import com.miui.networkassistant.config.Constants;
import com.miui.networkassistant.config.SharedPreferenceHelper;
import com.miui.networkassistant.dual.SimCardHelper;
import com.miui.networkassistant.service.FirewallServiceLauncher;
import com.miui.networkassistant.service.wrapper.TmBinderCacher;
import com.miui.networkassistant.traffic.statistic.PreSetGroup;
import com.miui.nfcaccess.NfcAccessManager;
import com.miui.permcenter.p;
import com.miui.permcenter.w;
import com.miui.permcenter.y;
import com.miui.securitycenter.Application;
import com.miui.securitycenter.memory.NativeMemoryManager;
import com.miui.securitycenter.migration.BehaviorRecordInitializer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import mb.v;
import miui.os.Build;
import oj.o;
import sf.x;
import u9.l;
import x7.m;
import zi.i;

/* JADX INFO: loaded from: classes3.dex */
public class Application extends h implements w0, androidx.work.a.c {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private static int f24327q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private static Application f24328r;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f24329e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f24330f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f24331g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f24332h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private float f24333i;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private Activity f24338n;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final List f24334j = new ArrayList();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Set f24335k = new HashSet();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final v0 f24336l = new v0();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private List f24337m = new ArrayList();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private Runnable f24339o = new e();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private Runnable f24340p = new f();

    class a extends AsyncTask {
        a() {
        }

        /* JADX DEBUG: Method merged with bridge method: doInBackground([Ljava/lang/Object;)Ljava/lang/Object; */
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            a1.d("No securitycenter installed on pad");
            q1.l(Application.f24328r, Application.this.getPackageName());
            return null;
        }
    }

    class b extends BroadcastReceiver {
        b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, Intent intent) {
            Log.i("ANTI.V2App", "cloud updated, re-initializing SIO");
            c0.e().a(new Runnable() { // from class: com.miui.securitycenter.a
                @Override // java.lang.Runnable
                public final void run() {
                    d.s(context);
                }
            });
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Application.this.P();
            j0.a().postDelayed(this, 900000L);
        }
    }

    class d implements android.app.Application.ActivityLifecycleCallbacks {
        d() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            if (com.miui.common.utils.j0.y()) {
                Application.this.f24334j.add(activity);
            }
            if (ll.b.d(activity)) {
                Application.this.f24337m.add(activity);
            }
            Application.this.f24335k.add(activity.getComponentName().getClassName());
            final String className = activity.getComponentName().getClassName();
            b7.a.b(new Runnable() { // from class: com.miui.securitycenter.b
                @Override // java.lang.Runnable
                public final void run() {
                    gk.h.b(className);
                }
            });
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            if (com.miui.common.utils.j0.y()) {
                Application.this.f24334j.remove(activity);
            }
            if (ll.b.d(activity)) {
                Application.this.f24337m.remove(activity);
            }
            Application.this.f24335k.remove(activity.getComponentName().getClassName());
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            Application.this.f24338n = null;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            Application.this.f24338n = activity;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            Application.this.T(false);
            Application.f24327q++;
            Application.this.w();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            Application.f24327q--;
            Application.this.T(true);
            Application.this.w();
        }
    }

    class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Application.this.y();
        }
    }

    class f implements Runnable {
        f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a1.a("ImageLoader clear memory info");
            if (yl.e.o().q()) {
                yl.e.o().d();
            }
            System.gc();
        }
    }

    class g extends ContentObserver {
        g(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z10) {
            super.onChange(z10);
            Application.this.x();
        }
    }

    public static Resources A() {
        return f24328r.getResources();
    }

    public static Application B() {
        return f24328r;
    }

    private void D() {
        d0.o(f24328r, new p4.a(), new IntentFilter("miui.intent.action.FIREWALL_UPDATED"), 2);
    }

    private void E(Context context) {
        TmBinderCacher.initForUIProcess(context);
        SharedPreferenceHelper.initForUIProcess();
    }

    private void F(Context context) {
        PreSetGroup.initGroupMap(context);
        SimCardHelper.asyncInit(context);
    }

    private static void G(Context context) {
        h0.c(BeautyService.r0(context));
        l.w(context);
        ia.l.m().z();
        if (p0.c(context)) {
            h0.e(1);
            return;
        }
        if (n.c()) {
            h0.e(2);
            return;
        }
        if (!e9.b.b(context)) {
            h0.e(4);
        } else if (!g9.a.e(context).y()) {
            h0.e(8);
        } else {
            context.startService(new Intent(context, (Class<?>) GameBoosterService.class));
            h0.e(128);
        }
    }

    private static void H(Context context) {
        la.c.f34481a.g(context, v1.b());
    }

    private void I() {
        Log.d("Application", "initRemoteProcessOnIdle");
        startService(new Intent().setClassName(getPackageName(), "com.miui.securitycenter.service.CacheService"));
        VirusScanJobService.u(f24328r);
        h0.a();
        b7.a.b(new Runnable() { // from class: aj.v
            @Override // java.lang.Runnable
            public final void run() {
                this.f1092a.L();
            }
        });
    }

    private static void J(Context context) {
        if (!v.f()) {
            h0.f(1);
            return;
        }
        if (!lb.d.F(context)) {
            h0.f(2);
            Log.i("Application", "initVideoToolBoxService: video box switch is off, skip start");
            return;
        }
        try {
            context.startService(new Intent().setClassName(context, "com.miui.gamebooster.service.VideoToolBoxService"));
            h0.f(128);
        } catch (Exception e10) {
            e10.printStackTrace();
            h0.f(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void L() {
        kk.c.m(this);
        boolean z10 = Build.IS_INTERNATIONAL_BUILD;
        if (!z10) {
            ed.a.a();
            ck.a.d(f24328r);
        }
        ji.b.c(f24328r).f();
        G(f24328r);
        J(f24328r);
        new FirewallServiceLauncher(f24328r).register();
        if (!com.miui.common.utils.h0.B()) {
            d0.y(f24328r, new Intent().setClassName(f24328r, "com.miui.networkassistant.service.tm.TrafficManageService"), m2.H());
        }
        w4.c.f(f24328r).h();
        o.c(f24328r);
        D();
        d0.o(f24328r, new a5.d(), new IntentFilter(i0.c("ACTION_USER_SWITCHED")), 2);
        mi.a.b();
        if (!z10 && el.a.u(f24328r)) {
            el.a.n(f24328r).o();
        }
        if (z10) {
            aj.i0.a(f24328r).b();
        }
        h0.d(m.b(f24328r));
        p.m(f24328r);
        AnalyticsUtil.initMiStats(f24328r);
        if (b0.h() > 7) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Constants.System.ACTION_USER_PRESENT);
            d0.o(f24328r, new o5.b(), intentFilter, 4);
        }
        t6.l.b().c(f24328r);
        if (!z10) {
            if (zi.h.i0(f24328r)) {
                zi.d.g(f24328r).h();
            }
            y.k(f24328r);
            s6.b.l(f24328r);
            getContentResolver().registerContentObserver(Settings.Secure.getUriFor("default_input_method"), false, new zi.h.b(j0.a()));
            startService(new Intent().setClassName(getPackageName(), "com.xiaomi.security.xsof.frauddetection.service.MiFraudDetectionService"));
        }
        if (zi.e.b()) {
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addAction("miui.intent.action.INPUT_METHOD_VISIBLE_HEIGHT_CHANGED");
            d0.o(f24328r, new i(), intentFilter2, 4);
        }
        if (w.f22715z) {
            x();
            getContentResolver().registerContentObserver(Settings.Secure.getUriFor("key_back_screen_stealth_mode_enabled"), false, new g(j0.a()));
        }
        ji.b.c(this).g();
        Log.d("Application", "initRemoteProcess on idle over");
        R();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void M() {
        v1.e();
        AnalyticsUtil.initMiStats(f24328r);
        F(f24328r);
        i5.g.l(f24328r).h();
        i5.h.q();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean N() {
        E(f24328r);
        com.miui.common.f.g(f24328r);
        ll.b.n();
        NfcAccessManager.x();
        try {
            com.miui.appmanager.d.g(f24328r, 12);
        } catch (Throwable unused) {
        }
        b7.a.b(new Runnable() { // from class: aj.u
            @Override // java.lang.Runnable
            public final void run() {
                Application.f();
            }
        });
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean O() {
        I();
        P();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void P() {
        try {
            long jA = NativeMemoryManager.a();
            if (jA < 0) {
                Log.w("Application", "nativeTrimMemory: native lib not loaded, skip");
                return;
            }
            Log.i("Application", "nativeTrimMemory done, cost_us=" + jA);
        } catch (Throwable th2) {
            Log.e("Application", "nativeTrimMemory: ", th2);
        }
    }

    private void R() {
        j0.a().postDelayed(new c(), 900000L);
    }

    private void S(Context context) {
        if (s6.b.g(context)) {
            s6.c.c(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void T(boolean z10) {
        if (f24327q == 0 && z.L(getContentResolver())) {
            sendBroadcast(new Intent("com.miui.securitycenter.action.UPDATE_NOTIFICATION").putExtra("notify", z10));
        }
    }

    public static /* synthetic */ void e() {
        ck.a.d(f24328r);
        q.a(new ck.a());
        q.a(new bk.a());
    }

    public static /* synthetic */ void f() {
        try {
            sj.h.c(f24328r);
        } catch (Throwable unused) {
        }
        ck.a.d(f24328r);
        q.a(new ck.a());
        q.a(new bk.a());
    }

    public static /* synthetic */ boolean k() {
        H(f24328r);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        if (f24327q <= 0) {
            j0.a().postDelayed(this.f24339o, ((long) com.miui.common.utils.j0.e()) * 120000);
            S(getApplicationContext());
        } else {
            j0.a().removeCallbacks(this.f24339o);
            j0.a().removeCallbacks(this.f24340p);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        boolean zA = cl.c.a(getContentResolver(), "key_back_screen_stealth_mode_enabled", false);
        boolean zB = w.a.b(getBaseContext());
        if (zA) {
            ig.p.b(getBaseContext(), zB, true, false);
        } else {
            ig.p.a(getBaseContext());
        }
        if (Build.IS_INTERNATIONAL_BUILD) {
            b7.a.b(new Runnable() { // from class: aj.w
                @Override // java.lang.Runnable
                public final void run() {
                    q1.d();
                }
            });
        }
    }

    public Activity C() {
        return this.f24338n;
    }

    public boolean K() {
        return this.f24329e;
    }

    protected void Q() {
        registerActivityLifecycleCallbacks(new d());
    }

    @Override // androidx.work.a.c
    public androidx.work.a a() {
        return new androidx.work.a.C0086a().v(4).a();
    }

    @Override // com.miui.common.h, android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        f24328r = this;
        String strA = q1.A(context);
        this.f24330f = strA;
        com.miui.common.e.g("com.miui.securitycenter.remote".equals(strA));
    }

    @Override // androidx.lifecycle.w0
    public v0 getViewModelStore() {
        return this.f24336l;
    }

    @Override // qq.d, android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if ("com.miui.securitycenter.remote".equals(this.f24330f) && this.f24331g != configuration.uiMode) {
            com.miui.permcenter.privacymanager.widget.a.f(f24328r);
            this.f24331g = configuration.uiMode;
        }
        if ("com.miui.securitycenter.remote".equals(this.f24330f)) {
            if (!(this.f24332h == configuration.fontScale && this.f24333i == getResources().getDisplayMetrics().density) && w.D()) {
                x.Y(f24328r).V0();
                this.f24332h = configuration.fontScale;
                this.f24333i = getResources().getDisplayMetrics().density;
            }
        }
    }

    @Override // com.miui.common.h, miuix.autodensity.l, qq.d, android.app.Application
    public void onCreate() {
        super.onCreate();
        String str = android.os.Build.DEVICE;
        if ("cappu".equals(str) || "clover".equals(str)) {
            new a().execute(new Void[0]);
        }
        if (getPackageName().equals(this.f24330f)) {
            b7.a.b(new Runnable() { // from class: aj.o
                @Override // java.lang.Runnable
                public final void run() {
                    this.f1089a.M();
                }
            });
            Q();
            if (t3.g.k()) {
                c0.e().a(new Runnable() { // from class: aj.p
                    @Override // java.lang.Runnable
                    public final void run() {
                        z3.f.b(Application.f24328r);
                    }
                });
                d0.o(f24328r, new b(), new IntentFilter("com.miui.securitycenter.action.PEEP_PROTECTION_CLOUD_UPDATED"), 4);
            }
            Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() { // from class: aj.q
                @Override // android.os.MessageQueue.IdleHandler
                public final boolean queueIdle() {
                    return this.f1090a.N();
                }
            });
            return;
        }
        if ("com.miui.securitycenter.uniauth".equals(this.f24330f)) {
            return;
        }
        if ("com.miui.securitycenter:ui".equals(this.f24330f)) {
            oj.e.p(f24328r);
            return;
        }
        if (!"com.miui.securitycenter.remote".equals(this.f24330f)) {
            if ("com.miui.securitycenter:cache".equals(this.f24330f)) {
                Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() { // from class: aj.t
                    @Override // android.os.MessageQueue.IdleHandler
                    public final boolean queueIdle() {
                        return Application.k();
                    }
                });
                return;
            }
            return;
        }
        Log.d("Application", "init remote process");
        BehaviorRecordInitializer.initialize(this);
        this.f24331g = getResources().getConfiguration().uiMode;
        this.f24332h = getResources().getConfiguration().fontScale;
        this.f24333i = getResources().getDisplayMetrics().density;
        b7.a.b(new Runnable() { // from class: aj.r
            @Override // java.lang.Runnable
            public final void run() {
                Application.e();
            }
        });
        g0.a(f24328r);
        this.f24329e = true;
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() { // from class: aj.s
            @Override // android.os.MessageQueue.IdleHandler
            public final boolean queueIdle() {
                return this.f1091a.O();
            }
        });
    }

    @Override // android.app.Application, android.content.ComponentCallbacks2
    public void onTrimMemory(int i10) {
        super.onTrimMemory(i10);
        if (i10 >= 20) {
            j0.a().postDelayed(this.f24340p, SilenceSkippingAudioProcessor.DEFAULT_PADDING_SILENCE_US);
        }
        if (!this.f24329e || i10 < 40) {
            return;
        }
        P();
    }

    public void y() {
        if (com.miui.common.utils.j0.y()) {
            Iterator it = this.f24334j.iterator();
            while (it.hasNext()) {
                ((Activity) it.next()).finish();
            }
            this.f24334j.clear();
            this.f24335k.clear();
            System.exit(0);
        }
    }

    public void z() {
        try {
            Iterator it = this.f24337m.iterator();
            while (it.hasNext()) {
                ((Activity) it.next()).finish();
            }
            this.f24337m.clear();
        } catch (Exception e10) {
            e10.printStackTrace();
        }
    }
}
