package com.miui.securitycenter.service;

import aj.z;
import android.app.Service;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.os.UserHandle;
import android.provider.Settings;
import android.service.autofill.FillResponse;
import android.text.TextUtils;
import android.util.Log;
import c5.q;
import com.google.android.exoplayer2.util.MimeTypes;
import com.miui.antivirus.service.VirusAutoUpdateJobService;
import com.miui.antivirus.service.VirusScanJobService;
import com.miui.apppredict.utils.f;
import com.miui.bubbles.services.MiuiBubbleServiceManager;
import com.miui.common.utils.f2;
import com.miui.common.utils.h0;
import com.miui.common.utils.l1;
import com.miui.common.utils.r1;
import com.miui.earthquakewarning.EarthquakeWarningManager;
import com.miui.electricrisk.AiGuardUtils;
import com.miui.gamebooster.gbservices.AntiMsgAccessibilityService;
import com.miui.gamebooster.utils.c;
import com.miui.gamebooster.utils.c0;
import com.miui.gamebooster.utils.n;
import com.miui.gamebooster.utils.o0;
import com.miui.gamebooster.utils.p0;
import com.miui.gamebooster.utils.s3;
import com.miui.gamebooster.utils.u3;
import com.miui.luckymoney.upgrade.LuckyMoneyHelper;
import com.miui.networkassistant.dual.SimCardHelper;
import com.miui.optimizecenter.storage.fbo.FboJobService;
import com.miui.permcenter.compact.AppOpsUtilsCompat;
import com.miui.permcenter.privacymanager.widget.BehaviorWidget;
import com.miui.permcenter.w;
import com.miui.permcenter.y;
import com.miui.permission.PermissionManager;
import com.miui.securitycenter.R;
import com.miui.securitycenter.receiver.BootReceiver;
import com.miui.securitycenter.receiver.CleanerReceiver;
import com.miui.securityscan.job.ScanJobService;
import com.miui.securityscan.shortcut.d;
import com.miui.simlock.SimLockUtils;
import com.miui.superpower.notification.SuperPowerTileService;
import com.miui.warningcenter.caraccident.CarAccidentUtils;
import com.miui.warningcenter.disasterwarning.Utils;
import com.miui.warningcenter.disasterwarning.WarningCenterDisasterManager;
import dl.e;
import e9.b;
import ig.b0;
import ig.r;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import miui.security.SystemSettingsProtectedService;
import miui.yellowpage.YellowPageUtils;
import miuix.appcompat.app.floatingactivity.multiapp.MethodCodeHelper;
import pj.p;
import r8.v;
import rk.j;
import t6.k;
import u9.l;
import v4.g;
import zi.h;

/* JADX INFO: loaded from: classes3.dex */
public class CacheService extends Service {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f24500a = 0;

    private void A() {
        if (p.g(this)) {
            return;
        }
        d.b bVar = d.b.NETWORK_DIAGNOSTICS;
        if (d.p(this, bVar)) {
            d.u(this, bVar);
        }
        p.v(this, true);
    }

    private void B() {
        String strH = H();
        boolean z10 = Q("com.miui.passwords") && R();
        boolean zQ = Q("com.miui.contentcatcher");
        if (TextUtils.isEmpty(strH)) {
            return;
        }
        if (z10) {
            if (strH.contains("com.miui.contentcatcher")) {
                x0("autofill_service", R.string.config_defaultAutofillService_Passwords);
            }
        } else if (zQ && strH.contains("com.miui.passwords")) {
            x0("autofill_service", R.string.config_defaultAutofillService_MiuiContentCatcher);
        }
    }

    private void C() {
        SharedPreferences sharedPreferences = getSharedPreferences("credential_service_config", 0);
        if (sharedPreferences.getBoolean("is_default_credential_configured", false)) {
            return;
        }
        w0("credential_service", R.array.config_enabledCredentialProviderService);
        w0("credential_service_primary", R.array.config_primaryCredentialProviderService);
        sharedPreferences.edit().putBoolean("is_default_credential_configured", true).remove("is_credential_configured").apply();
    }

    private static boolean D(File file) {
        if (file.isFile()) {
            return file.delete();
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                D(file2);
            }
        }
        return file.delete();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E() {
        try {
            if (z.m()) {
                return;
            }
            z.d0(true);
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/MIUI/debug_log/com.miui.privacy");
            D(file);
            Log.e("CacheService", "deletePrivacyDir," + file.exists());
        } catch (Throwable th2) {
            Log.e("CacheService", "deletePrivacyDir: ", th2);
        }
    }

    private void F() {
        if (pr.a.f40580a) {
            return;
        }
        getPackageManager().setComponentEnabledSetting(new ComponentName(this, "com.miui.tvm.service.TvmManagerService"), (TextUtils.isEmpty(f2.c("vendor.xiaomi.trustedvm.version", null)) && TextUtils.isEmpty(f2.c("ro.vendor.mitee_tui.support", null))) ? 2 : 1, 1);
    }

    private void G() throws Throwable {
        v.K1();
        e9.a.a(this);
        l.B(this);
        if (p0.c(this) || n.c() || !b.b(this)) {
            if (s3.g(this, null)) {
                u3.i(this, null);
                return;
            }
            return;
        }
        u3.e(this);
        if (o0.c0() && g9.a.K(false)) {
            c.a(this);
            g9.a.x0(false);
        }
        if (u3.g()) {
            g9.a.h0(0);
            u3.k(this);
        }
    }

    private String H() {
        try {
            String str = (String) e.n(Settings.Secure.class, "AUTOFILL_SERVICE", String.class);
            int iIntValue = 0;
            try {
                iIntValue = ((Integer) e.g(UserHandle.class, Integer.class, "myUserId", new Class[0], new Object[0])).intValue();
            } catch (Exception unused) {
            }
            String str2 = (String) e.g(Settings.Secure.class, String.class, "getStringForUser", new Class[]{ContentResolver.class, String.class, Integer.TYPE}, getContentResolver(), str, Integer.valueOf(iIntValue));
            return str2 == null ? "" : str2;
        } catch (Exception unused2) {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I() {
        if (pr.a.f40580a) {
            return;
        }
        try {
            Method method = AiGuardUtils.class.getMethod("isAiGuardSupported", Context.class);
            Boolean bool = Boolean.TRUE;
            boolean zEquals = bool.equals(method.invoke(null, this));
            AiGuardUtils.class.getMethod("setPosterActivityState", Context.class, Boolean.TYPE).invoke(null, this, Boolean.valueOf(zEquals));
            if (zEquals && bool.equals(AiGuardUtils.class.getMethod("isAiGuardEnabled", Context.class).invoke(null, this))) {
                startService(new Intent().setClassName(getPackageName(), "com.miui.electricrisk.AiGuardSceneService"));
            }
        } catch (Throwable th2) {
            Log.e("CacheService", "initAiGuard: ", th2);
        }
    }

    private void J() {
        g.Z(this);
    }

    private void K(Context context) throws Throwable {
        v.K1();
        e9.a.a(context);
        l.B(context);
        L(context);
        if (o0.c0() && g9.a.K(false)) {
            c.a(context);
            g9.a.x0(false);
        }
    }

    private void L(Context context) throws Throwable {
        if (p0.c(context) || n.c() || !b.b(context)) {
            if (s3.g(context, null)) {
                u3.i(context, null);
            }
        } else {
            u3.e(context);
            if (u3.g()) {
                g9.a.h0(0);
                u3.k(context);
            }
        }
    }

    private void M(Context context) {
        try {
            boolean zM = e8.d.m(context);
            boolean zE = e8.d.e(context);
            if (!zM && c0.d() && zE) {
                e8.d.F(true);
                e8.d.G(true);
            }
            wi.b.b();
            Log.i("CacheService", "dock guide flag set");
        } catch (Exception e10) {
            Log.e("CacheService", "initGlobalDockOffload error", e10);
        }
    }

    private void N() {
        ArrayList arrayList = new ArrayList(w4.l.j(this));
        ArrayList arrayListQ = q.q(this);
        if (arrayListQ.containsAll(arrayList)) {
            return;
        }
        arrayList.retainAll(arrayListQ);
        w4.l.I(arrayList);
    }

    private void O() {
        ComponentName componentName = new ComponentName(getPackageName(), SuperPowerTileService.class.getName());
        if (!j.D(this) || getPackageManager().getComponentEnabledSetting(componentName) == 1) {
            return;
        }
        getPackageManager().setComponentEnabledSetting(componentName, 1, 1);
        Settings.Secure.putInt(getContentResolver(), "power_supersave_tile_enabled", 1);
    }

    private void P(Context context) {
        e9.a.b(context);
    }

    private boolean Q(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            getPackageManager().getApplicationInfo(str, 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    private boolean R() {
        try {
            Boolean bool = (Boolean) e.g(FillResponse.class, Boolean.class, "isSupportNewPasswords", new Class[0], new Object[0]);
            return bool != null && bool.booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void S() throws Throwable {
        K(getApplicationContext());
        P(getApplicationContext());
        M(getApplicationContext());
        G();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void T() {
        ConnectivityChangeJobService2.v(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void U() {
        f.f();
        i5.g.y(this);
        ei.a.g(this);
        ll.b.j(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void V() {
        AutoScanGameJobService.c(this);
        y.c();
        VirusAutoUpdateJobService.d(this);
        if (pr.a.f40580a) {
            return;
        }
        com.miui.permcenter.j.a(this).c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void W() {
        lc.b.a(this);
        qc.a.a();
        com.miui.permcenter.d.a(this);
        oj.e.j(this);
        com.miui.permcenter.v.q(this);
        rl.d.c(this);
        F();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void X() {
        g6.d.k(this);
        FboJobService.c(this);
        u0();
        CleanerReceiver.c();
        O();
        yh.c0.I();
        SimCardHelper.asyncInit(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void Y() {
        N();
        VirusScanJobService.v(this);
        t6.l.b().a(this);
        J();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void Z() {
        com.miui.permcenter.p.o(this);
        y.u(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a0() {
        yh.c0.R0(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b0() {
        e9.a.b(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c0() {
        LuckyMoneyHelper.init(this);
        LuckyMoneyHelper.luckMoneyLogic();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d0() {
        WarningCenterDisasterManager.startDisasterWarningService(this);
        EarthquakeWarningManager.startEarthquakeWarningService(this);
        CarAccidentUtils.initCarAccidentDetectService(this);
        if (s6.b.h(this) && s6.b.g(this)) {
            String str = s6.b.a(this) == 1 ? "miui.carsickness.remind_always" : "miui.carsickness.remind_driving";
            Intent className = new Intent().setClassName(this, "com.miui.carsickness.service.CarSicknessService");
            className.setAction(str);
            startService(className);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e0() {
        i5.g.i(this);
        i5.g.d(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f0() {
        kk.c.o(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g0() {
        if (w4.l.n()) {
            Intent className = new Intent().setClassName(this, "com.miui.antivirus.service.GuardService");
            className.setAction("action_register_foreground_notification");
            startService(className);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h0() {
        try {
            s0("AgentReset", new Runnable() { // from class: nj.p
                @Override // java.lang.Runnable
                public final void run() {
                    ue.m.M();
                }
            });
            s0("AppLockManager", new Runnable() { // from class: nj.h
                @Override // java.lang.Runnable
                public final void run() throws Throwable {
                    this.f39201a.S();
                }
            });
            s0("deletePrivacyDir", new Runnable() { // from class: nj.n
                @Override // java.lang.Runnable
                public final void run() {
                    this.f39207a.E();
                }
            });
            s0("setSchedule", new Runnable() { // from class: nj.o
                @Override // java.lang.Runnable
                public final void run() {
                    this.f39208a.T();
                }
            });
            s0("AppLockManager", new Runnable() { // from class: nj.q
                @Override // java.lang.Runnable
                public final void run() {
                    this.f39209a.e0();
                }
            });
            s0("startAppManagementService", new Runnable() { // from class: nj.r
                @Override // java.lang.Runnable
                public final void run() {
                    this.f39210a.i0();
                }
            });
            s0("notificationAndOptimize", new Runnable() { // from class: nj.s
                @Override // java.lang.Runnable
                public final void run() {
                    this.f39211a.j0();
                }
            });
            s0("turnOffOriginalSOS", new Runnable() { // from class: nj.t
                @Override // java.lang.Runnable
                public final void run() {
                    this.f39212a.k0();
                }
            });
            s0("regionSpecificInit", new Runnable() { // from class: nj.u
                @Override // java.lang.Runnable
                public final void run() {
                    this.f39213a.l0();
                }
            });
            s0("cleanerAndNotification", new Runnable() { // from class: nj.v
                @Override // java.lang.Runnable
                public final void run() {
                    this.f39214a.m0();
                }
            });
            s0("widgetAndShortcut", new Runnable() { // from class: nj.w
                @Override // java.lang.Runnable
                public final void run() {
                    this.f39215a.n0();
                }
            });
            s0("shortcutAndAntispam", new Runnable() { // from class: nj.x
                @Override // java.lang.Runnable
                public final void run() {
                    this.f39216a.o0();
                }
            });
            s0("appLockAndPrivacy", new Runnable() { // from class: nj.y
                @Override // java.lang.Runnable
                public final void run() {
                    this.f39217a.U();
                }
            });
            s0("securityAndVirus", new Runnable() { // from class: nj.z
                @Override // java.lang.Runnable
                public final void run() {
                    this.f39218a.V();
                }
            });
            s0("satisfactionAndPermission", new Runnable() { // from class: nj.a0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f39186a.W();
                }
            });
            s0("jobAndConfig", new Runnable() { // from class: nj.b0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f39192a.X();
                }
            });
            s0("scanAndClean", new Runnable() { // from class: nj.c0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f39194a.Y();
                }
            });
            s0("permissionCenterOneTime", new Runnable() { // from class: nj.d0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f39196a.Z();
                }
            });
            s0("aiGuardInit", new Runnable() { // from class: nj.f
                @Override // java.lang.Runnable
                public final void run() {
                    this.f39199a.I();
                }
            });
            s0("migratedPower", new Runnable() { // from class: nj.g
                @Override // java.lang.Runnable
                public final void run() {
                    this.f39200a.a0();
                }
            });
            s0("migratedVideoToolBox", new Runnable() { // from class: nj.i
                @Override // java.lang.Runnable
                public final void run() {
                    this.f39202a.b0();
                }
            });
            s0("migratedLuckyMoney", new Runnable() { // from class: nj.j
                @Override // java.lang.Runnable
                public final void run() {
                    this.f39203a.c0();
                }
            });
            s0("migratedWarningServices", new Runnable() { // from class: nj.k
                @Override // java.lang.Runnable
                public final void run() {
                    this.f39204a.d0();
                }
            });
            s0("migratedPredict", new Runnable() { // from class: nj.l
                @Override // java.lang.Runnable
                public final void run() {
                    this.f39205a.f0();
                }
            });
            s0("migratedGuardService", new Runnable() { // from class: nj.m
                @Override // java.lang.Runnable
                public final void run() {
                    this.f39206a.g0();
                }
            });
        } finally {
            Log.i("CacheService", "CacheService finished " + this.f24500a + " subtasks, stopSelf");
            stopSelf();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i0() {
        if (pr.a.f40580a || !w.f22710u) {
            return;
        }
        kk.c.l(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j0() {
        v0();
        AppOpsUtilsCompat.autoOptmize(this);
        z();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k0() {
        Utils.turnOffOriginalSOS(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l0() {
        if (pr.a.f40580a) {
            r.i(this);
            return;
        }
        h.c(this);
        h.E(this);
        A();
        m6.a.d().b(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m0() {
        k.c(this, MimeTypes.BASE_TYPE_APPLICATION);
        y0();
        if (TextUtils.isEmpty(z.q())) {
            z.k0(h.m(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n0() {
        t0();
        q0();
        com.miui.earthquakewarning.utils.Utils.initEarthquakeWarningInSetting(this);
        if (!pr.a.f40580a) {
            SimLockUtils.o(this);
        }
        b0.b();
        MiuiBubbleServiceManager.startBubbleRemoteServicesIfNeed(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o0() {
        boolean z10 = pr.a.f40580a;
        ll.b.a(this, z10 ? null : "com.miui.securitymanager");
        r0();
        SystemSettingsProtectedService.startSettingsProtectService(this);
        if (z10) {
            y.v(this);
        }
    }

    private void p0() {
        SharedPreferences sharedPreferences = getSharedPreferences("antispam_migration", 0);
        if (!sharedPreferences.getBoolean("empty_call_default_migrated", false)) {
            boolean z10 = pr.a.f40580a;
            k4.b.z(this, "empty_call_mode", 1, z10 ? 1 : 0);
            k4.b.z(this, "empty_call_mode", 2, z10 ? 1 : 0);
            sharedPreferences.edit().putBoolean("empty_call_default_migrated", true).apply();
        }
        if (sharedPreferences.getBoolean("mark_number_default_migrated_v1", false) || !YellowPageUtils.isYellowPageAvailable(this)) {
            return;
        }
        k4.b.G(this, 1, true);
        k4.b.G(this, 2, true);
        k4.b.L(this, 1, true);
        k4.b.L(this, 2, true);
        sharedPreferences.edit().putBoolean("mark_number_default_migrated_v1", true).apply();
    }

    private void q0() {
        if (pr.a.f40580a || Build.VERSION.SDK_INT < 31) {
            return;
        }
        try {
            String string = Settings.Secure.getString(getContentResolver(), "accessibility_button_targets");
            if (TextUtils.isEmpty(string)) {
                return;
            }
            String str = getPackageName() + "/" + AntiMsgAccessibilityService.class.getName();
            Log.d("CacheService", "removeAllyServiceShortCutIfNecessary: targets=" + string);
            if (string.contains(str)) {
                Settings.Secure.putString(getContentResolver(), "accessibility_button_targets", string.replace(str, ""));
            }
        } catch (Throwable th2) {
            Log.e("CacheService", "removeAllyServiceShortCutIfNecessary run: " + th2.getMessage());
        }
    }

    private void r0() {
        v4.a.w(this);
        if (!p.l(this)) {
            v4.a.x(this);
            p.o(this, true);
        }
        p0();
    }

    private void s0(String str, Runnable runnable) {
        try {
            runnable.run();
        } catch (Throwable th2) {
            try {
                Log.e("CacheService", "CacheService task [" + str + "] error", th2);
            } finally {
                this.f24500a++;
            }
        }
    }

    private void t0() {
        Boolean boolB = l1.b(this);
        if (boolB == null) {
            Log.w("CacheService", "setBehaviorWidgetAndScanJobEnable: query failed, skip");
            return;
        }
        if (boolB.booleanValue()) {
            ScanJobService.k(this);
        }
        getPackageManager().setComponentEnabledSetting(new ComponentName(this, (Class<?>) BehaviorWidget.class), boolB.booleanValue() ? 1 : 2, 1);
    }

    private void u0() {
        try {
            if (pr.a.f40580a || !h0.i()) {
                return;
            }
            C();
            B();
        } catch (Exception e10) {
            Log.e("CacheService", "setDefaultConfigForAutofillAndCredentialManager error: " + e10);
        }
    }

    private void v0() {
        ei.a aVar = new ei.a(this);
        if (aVar.d()) {
            ei.a.j(this, false);
            aVar.i(false);
        }
    }

    private void w0(String str, int i10) {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.addAll(Arrays.asList(getResources().getStringArray(i10)));
        } catch (Exception e10) {
            Log.e("CacheService", "setStringArrayResourceConfigIfNeed error for " + str + ": " + e10);
        }
        if (arrayList.isEmpty()) {
            return;
        }
        Settings.Secure.putString(getContentResolver(), str, String.join(MethodCodeHelper.IDENTITY_INFO_SEPARATOR, arrayList));
    }

    private void x0(String str, int i10) {
        String string;
        try {
            string = getResources().getString(i10);
        } catch (Exception e10) {
            Log.e("CacheService", "setStringResourceConfigIfNeed error for " + str + ": " + e10);
            string = "";
        }
        if (TextUtils.isEmpty(string)) {
            return;
        }
        Settings.Secure.putString(getContentResolver(), str, string);
    }

    private void y0() {
        if (com.miui.common.utils.b0.h() == 9) {
            for (String str : BootReceiver.f24485a) {
                try {
                    PermissionManager.getInstance(this).setApplicationPermission(PermissionManager.PERM_ID_NOTIFICATION, ((Boolean) dl.d.b("CacheService", getSystemService("security"), "areNotificationsEnabledForPackage", new Class[]{String.class, Integer.TYPE}, str, Integer.valueOf(getPackageManager().getApplicationInfo(str, 0).uid))).booleanValue() ? 3 : 1, str);
                } catch (Exception e10) {
                    Log.e("CacheService", "not found pkg", e10);
                }
            }
        }
    }

    private void z() {
        if (pr.a.f40580a || r1.a(this, "com.miui.cleanmaster")) {
            return;
        }
        d.b bVar = d.b.CLEANMASTER;
        if (d.p(this, bVar)) {
            d.u(this, bVar);
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.i("CacheService", "onCreate: CacheService");
        b7.a.b(new Runnable() { // from class: nj.e
            @Override // java.lang.Runnable
            public final void run() {
                this.f39197a.h0();
            }
        });
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i10, int i11) {
        return 2;
    }
}
