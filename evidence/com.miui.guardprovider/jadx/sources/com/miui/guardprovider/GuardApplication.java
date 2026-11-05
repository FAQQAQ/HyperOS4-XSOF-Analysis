package com.miui.guardprovider;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.util.Log;
import com.xiaomi.onetrack.util.a;
import com.xiaomi.push.service.receivers.NetworkStatusReceiver;
import defpackage.bd3;
import defpackage.dg;
import defpackage.f03;
import defpackage.fp1;
import defpackage.g;
import defpackage.iu0;
import defpackage.kg3;
import defpackage.kk2;
import defpackage.md3;
import defpackage.mp3;
import defpackage.oi3;
import defpackage.qr0;
import defpackage.ro;
import defpackage.rq0;
import defpackage.rr0;
import defpackage.sc3;
import defpackage.se0;
import defpackage.uy2;
import defpackage.vq3;
import defpackage.wj2;
import defpackage.zd3;
import java.lang.invoke.VarHandle;
import java.time.Instant;
import miui.os.Build;

/* JADX INFO: loaded from: classes.dex */
public class GuardApplication extends Application implements rq0 {
    public static GuardApplication application;
    public boolean b = false;
    public final dg c;
    public qr0 d;

    public GuardApplication() {
        ro roVar = new ro(16);
        VarHandle.storeStoreFence();
        dg dgVar = new dg();
        dgVar.c = new Object();
        dgVar.d = roVar;
        VarHandle.storeStoreFence();
        this.c = dgVar;
    }

    public static boolean isAllowConnectNetwork(Context context) {
        boolean zBooleanValue;
        if (!Build.IS_INTERNATIONAL_BUILD) {
            return ((SharedPreferences) kk2.b().b).getInt("network_status", 0) == 1 || bd3.y();
        }
        String strV = bd3.v("ro.miui.customized.region", a.c);
        try {
            zBooleanValue = ((Boolean) uy2.i(Class.forName("android.provider.MiuiSettings$Privacy"), "isEnabled", new Class[]{Context.class, String.class}, context, "com.miui.securitycenter")).booleanValue();
        } catch (Exception e) {
            Log.i("MiuiSettingsCompat", e.toString());
            zBooleanValue = false;
        }
        boolean z = zBooleanValue && bd3.y();
        if ("jp_kd".equals(strV)) {
            return z && (Settings.Secure.getInt(context.getContentResolver(), "antivirus_engine_network_enable", 0) == 1);
        }
        return z;
    }

    @Override // defpackage.rq0
    public final Object a() {
        return this.c.a();
    }

    @Override // android.content.ContextWrapper
    public final void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        application = this;
        if (f03.E(context)) {
            kk2.c(context);
            return;
        }
        IntentFilter intentFilter = new IntentFilter("android.intent.action.USER_UNLOCKED");
        if (this.d == null) {
            this.d = new qr0(0, this);
        }
        registerReceiver(this.d, intentFilter, null, null, 2);
    }

    public final void b() {
        if (!this.b) {
            this.b = true;
            ((rr0) this.c.a()).getClass();
        }
        super.onCreate();
    }

    /* JADX WARN: Code duplicated, block: B:10:0x004a  */
    /* JADX WARN: Code duplicated, block: B:12:0x0062  */
    /* JADX WARN: Code duplicated, block: B:15:0x0074  */
    @Override // android.app.Application
    public final void onCreate() {
        Context applicationContext;
        b();
        GeneratedFunctionRegistry.register(wj2.b(), this);
        if (isAllowConnectNetwork(this) && Application.getProcessName().equals(getPackageName())) {
            if (getSharedPreferences("device_token", 0).getString("token", null) != null) {
                if (Instant.now().getEpochSecond() - getSharedPreferences("device_token", 0).getLong("timestamp", 0L) >= 2592000) {
                    Log.i("PushManager", "Register: Device token is null or expired.");
                    Context context = fp1.a;
                    iu0 iu0Var = new iu0(23);
                    applicationContext = getApplicationContext();
                    fp1.a = applicationContext;
                    if (applicationContext == null) {
                        fp1.a = this;
                    }
                    Context context2 = fp1.a;
                    Context context3 = vq3.a;
                    vq3.a = context2.getApplicationContext();
                    if (!NetworkStatusReceiver.a()) {
                        Context context4 = fp1.a;
                        try {
                            IntentFilter intentFilter = new IntentFilter();
                            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                            intentFilter.addCategory("android.intent.category.DEFAULT");
                            mp3.d(context4.getApplicationContext(), new NetworkStatusReceiver(null), intentFilter);
                        } catch (Throwable th) {
                            kg3.e("dynamic register network status receiver failed:" + th);
                        }
                        zd3.b(fp1.a);
                    }
                    oi3 oi3VarA = oi3.a(fp1.a);
                    oi3VarA.b = iu0Var;
                    md3.c(oi3VarA.a).n(67, true);
                    oi3VarA.b.getClass();
                    oi3VarA.b.getClass();
                    oi3VarA.b.getClass();
                    oi3VarA.b.getClass();
                    sc3 sc3VarE = sc3.e(context2);
                    se0 se0Var = new se0(1);
                    VarHandle.storeStoreFence();
                    sc3VarE.i(se0Var, 0);
                }
            } else {
                Log.i("PushManager", "Register: Device token is null or expired.");
                Context context5 = fp1.a;
                iu0 iu0Var2 = new iu0(23);
                applicationContext = getApplicationContext();
                fp1.a = applicationContext;
                if (applicationContext == null) {
                    fp1.a = this;
                }
                Context context6 = fp1.a;
                Context context7 = vq3.a;
                vq3.a = context6.getApplicationContext();
                if (!NetworkStatusReceiver.a()) {
                    Context context8 = fp1.a;
                    IntentFilter intentFilter2 = new IntentFilter();
                    intentFilter2.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                    intentFilter2.addCategory("android.intent.category.DEFAULT");
                    mp3.d(context8.getApplicationContext(), new NetworkStatusReceiver(null), intentFilter2);
                    zd3.b(fp1.a);
                }
                oi3 oi3VarA2 = oi3.a(fp1.a);
                oi3VarA2.b = iu0Var2;
                md3.c(oi3VarA2.a).n(67, true);
                oi3VarA2.b.getClass();
                oi3VarA2.b.getClass();
                oi3VarA2.b.getClass();
                oi3VarA2.b.getClass();
                sc3 sc3VarE2 = sc3.e(context6);
                se0 se0Var2 = new se0(1);
                VarHandle.storeStoreFence();
                sc3VarE2.i(se0Var2, 0);
            }
        }
        g gVar = new g(8);
        gVar.c = this;
        VarHandle.storeStoreFence();
        new Thread(gVar, "device_state_check").start();
    }
}
