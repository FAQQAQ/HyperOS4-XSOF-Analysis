package com.miui.analytics;

import a.b.a.a.a.c.a;
import a.b.a.c.b;
import a.b.a.c.i;
import a.b.a.c.j;
import a.b.a.c.v.h;
import a.b.a.e.c;
import a.b.a.e.e;
import a.b.a.e.e0.l;
import a.b.a.e.g;
import a.b.a.e.l0.m;
import a.b.a.e.l0.n;
import a.b.a.e.l0.p;
import a.b.a.e.q;
import a.b.a.e.r;
import a.b.a.e.t;
import a.b.a.e.w;
import android.annotation.SuppressLint;
import android.app.Application;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.JobSchedulerService;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class AnalyticsApp extends Application {
    private static final String TAG = "AnalyticsApp";
    private AtomicBoolean mIsInit = new AtomicBoolean(false);
    private BroadcastReceiver mReceiver;

    private void initAsync() {
        a.x(new Runnable() { // from class: com.miui.analytics.AnalyticsApp.1
            /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Context applicationContext = AnalyticsApp.this.getApplicationContext();
                    boolean z = p.f602a;
                    try {
                        p.b(applicationContext);
                        p.l();
                    } catch (Exception e) {
                        p.c("LogUtil", "e:" + e.getMessage());
                    }
                    Analytics.initialize(AnalyticsApp.this.getApplicationContext(), a.L(applicationContext), a.M(applicationContext));
                    e.d(applicationContext);
                    b bVar = new b();
                    try {
                        bVar.b(applicationContext);
                        bVar.a(applicationContext);
                    } catch (Exception e2) {
                        a.O0(applicationContext, "AlarmClock", "setupAlarms exception: ", e2);
                    }
                    new i().a(applicationContext);
                    j.i(applicationContext).e();
                    AnalyticsApp.this.startJobScheduler();
                    AnalyticsApp.this.registerReceiver();
                    AnalyticsApp.this.registerApkReceiver();
                } catch (Exception e3) {
                    Log.e(a.b.a.c.v.i.a(AnalyticsApp.TAG), "initAsync e", e3);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerApkReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addDataScheme("package");
        registerReceiver(new BroadcastReceiver() { // from class: com.miui.analytics.AnalyticsApp.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(final Context context, final Intent intent) {
                if (intent == null) {
                    return;
                }
                a.x(new Runnable() { // from class: com.miui.analytics.AnalyticsApp.3.1
                    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
                    @Override // java.lang.Runnable
                    public void run() {
                        PackageInfo packageInfo;
                        a.b.a.e.d0.a.c cVar;
                        try {
                            String action = intent.getAction();
                            p.c(AnalyticsApp.TAG, "onReceive: " + action + "\tsticky: " + isInitialStickyBroadcast() + "\tThread: " + Thread.currentThread());
                            String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
                            boolean z = true;
                            if (TextUtils.equals("android.intent.action.PACKAGE_REMOVED", action)) {
                                g.h(true);
                                g.b(intent);
                                c cVarE = c.e();
                                Intent intent2 = intent;
                                Objects.requireNonNull(cVarE);
                                try {
                                    if (c.g()) {
                                        a.y(new a.b.a.e.a(cVarE, intent2));
                                    }
                                } catch (Throwable unused) {
                                }
                                t tVarD = t.d();
                                Intent intent3 = intent;
                                Objects.requireNonNull(tVarD);
                                try {
                                    if (t.e()) {
                                        a.z(new q(tVarD, intent3));
                                    }
                                } catch (Throwable unused2) {
                                }
                                n.d(intent);
                                return;
                            }
                            if (TextUtils.equals("android.intent.action.PACKAGE_ADDED", action)) {
                                g.b(intent);
                                c cVarE2 = c.e();
                                Intent intent4 = intent;
                                Objects.requireNonNull(cVarE2);
                                try {
                                    if (c.g()) {
                                        a.y(new a.b.a.e.b(cVarE2, intent4));
                                    }
                                } catch (Throwable th) {
                                    p.c("AppStatActiveCacheControl", "comparePackageAdded: " + th.getMessage());
                                }
                                t tVarD2 = t.d();
                                Intent intent5 = intent;
                                Objects.requireNonNull(tVarD2);
                                try {
                                    if (t.e()) {
                                        a.z(new r(tVarD2, intent5));
                                        return;
                                    }
                                    return;
                                } catch (Throwable unused3) {
                                    return;
                                }
                            }
                            if (TextUtils.equals("android.intent.action.PACKAGE_REPLACED", action)) {
                                String strN = a.b.a.e.l0.r.n(schemeSpecificPart);
                                try {
                                    packageInfo = context.getPackageManager().getPackageInfo(schemeSpecificPart, 16384);
                                } catch (Exception unused4) {
                                    packageInfo = null;
                                }
                                if (packageInfo != null) {
                                    long j = packageInfo.lastUpdateTime;
                                    a.b.a.e.d0.a.d dVar = a.b.a.e.d0.a.d.C0006a.f255a;
                                    Objects.requireNonNull(dVar);
                                    if (dVar.f254b.get(schemeSpecificPart) == null) {
                                        z = false;
                                    }
                                    if (z) {
                                        Log.d("AppVerDbManager", "will update");
                                        cVar = dVar.f254b.get(schemeSpecificPart);
                                        cVar.f250b = cVar.f252d;
                                    } else {
                                        Log.d("AppVerDbManager", "will add");
                                        a.b.a.e.d0.a.c cVar2 = new a.b.a.e.d0.a.c();
                                        cVar2.f249a = schemeSpecificPart;
                                        cVar2.f250b = null;
                                        dVar.f254b.put(schemeSpecificPart, cVar2);
                                        cVar = cVar2;
                                    }
                                    cVar.f251c = j;
                                    cVar.f252d = strN;
                                    dVar.a(cVar);
                                    g.b(intent);
                                }
                            }
                        } catch (Exception e) {
                            p.h(AnalyticsApp.TAG, "apkReceiver onReceive failed,", e);
                            a.y0("tr", "s5", e);
                        }
                    }
                });
            }
        }, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerReceiver() {
        this.mReceiver = new BroadcastReceiver() { // from class: com.miui.analytics.AnalyticsApp.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(final Context context, final Intent intent) {
                try {
                    a.x(new Runnable() { // from class: com.miui.analytics.AnalyticsApp.2.1
                        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                if (intent != null && !h.j(context, AnalyticsApp.TAG)) {
                                    String action = intent.getAction();
                                    if (TextUtils.isEmpty(action)) {
                                        return;
                                    }
                                    a.b.a.c.v.i.b(AnalyticsApp.TAG, "onReceive: " + action);
                                    int i = 1;
                                    if (!action.equals("miui.intent.action.ad.AAID_RESET")) {
                                        if (action.equals("miui.intent.action.oaid_changed")) {
                                            m.f = null;
                                            m.g = 0L;
                                            p.c("IdentifierManager", "OAID cache cleared");
                                            g.c();
                                            l.b.f306a.a(5000L, true);
                                            return;
                                        }
                                        return;
                                    }
                                    if (a.b.a.e.l0.r.s()) {
                                        p.c(AnalyticsApp.TAG, "isNotAllowGetAaid");
                                        return;
                                    }
                                    Bundle extras = intent.getExtras();
                                    if (extras == null || !extras.containsKey("new_aaid")) {
                                        return;
                                    }
                                    String string = extras.getString("new_aaid");
                                    if (!BuildConfig.APPLICATION_ID.equals(extras.getString("from", ""))) {
                                        i = 0;
                                    }
                                    h.a(string, i);
                                }
                            } catch (Exception e) {
                                Log.e(a.b.a.c.v.i.a(AnalyticsApp.TAG), "mReceiver onReceive e", e);
                                a.y0("tr", "s6", e);
                            }
                        }
                    });
                } catch (Exception e) {
                    Log.e(a.b.a.c.v.i.a(AnalyticsApp.TAG), "mReceiver onReceive e", e);
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        if (!a.b.a.e.l0.r.s()) {
            intentFilter.addAction("miui.intent.action.ad.AAID_RESET");
        }
        intentFilter.addAction("miui.intent.action.oaid_changed");
        if (Build.VERSION.SDK_INT >= 33) {
            registerReceiver(this.mReceiver, intentFilter, 2);
        } else {
            registerReceiver(this.mReceiver, intentFilter);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    public void startJobScheduler() {
        try {
            if (Build.VERSION.SDK_INT < 21) {
                return;
            }
            Class.forName("android.app.job.JobScheduler");
            JobScheduler jobScheduler = (JobScheduler) getSystemService("jobscheduler");
            jobScheduler.cancel(1);
            JobInfo.Builder builder = new JobInfo.Builder(1, new ComponentName(BuildConfig.APPLICATION_ID, JobSchedulerService.class.getName()));
            builder.setRequiredNetworkType(1);
            builder.setOverrideDeadline(900000L);
            builder.setBackoffCriteria(300000L, 1);
            int iSchedule = jobScheduler.schedule(builder.build());
            Log.i(a.b.a.c.v.i.a(TAG), "startJobScheduler goes with code " + iSchedule);
        } catch (Throwable th) {
            Log.e(a.b.a.c.v.i.a(TAG), "startJobScheduler exception:", th);
        }
    }

    @Override // android.app.Application
    public void onCreate() {
        try {
            super.onCreate();
            e.d(getApplicationContext());
            initAsync();
            w.d(getApplicationContext());
        } catch (Throwable th) {
            Log.e(a.b.a.c.v.i.a(TAG), "analytics app onCreate exception:", th);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    @Override // android.app.Application, android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        if (i >= 10) {
            a.b.a.e.l0.j.a("onTrimMemory lvl:" + i, 30000L);
        }
    }
}
