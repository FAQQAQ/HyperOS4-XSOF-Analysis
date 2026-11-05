package com.xiaomi.trustservice.remoteauthservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.util.Slog;
import com.xiaomi.trustservice.common.CommService;

/* JADX INFO: loaded from: classes.dex */
public class remoteAuthService extends Service {
    public static boolean h = false;
    public P0.c a;
    public long b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f1733c = 0;
    public long d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f1734e = new Object();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final l f1735f = new l(this);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final m f1736g = new m(this);

    public remoteAuthService() {
        Slog.v("MiTrustService/remoteAuthService", "Construct");
    }

    public static void a() {
        if (androidx.emoji2.text.o.G(androidx.emoji2.text.o.J(Binder.getCallingUid()), "miui.permission.REMOTE_AUTH") == 0) {
            return;
        }
        String str = "Permission Denial from pid=" + Binder.getCallingPid() + ", uid=" + Binder.getCallingUid() + " requires miui.permission.REMOTE_AUTH";
        Slog.w("MiTrustService/remoteAuthService", str);
        throw new SecurityException(str);
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        Slog.d("MiTrustService/remoteAuthService", "onBind");
        String action = intent.getAction();
        P0.c cVar = this.a;
        if (cVar != null) {
            k kVar = cVar.f246p;
            if (kVar.a == null) {
                Slog.d("MiTrustService/quickAuthEventHandle", "try init SystemUI Service");
                Intent intent2 = new Intent("miui.intent.action.CROSS_ENDS_UNLOCK_V2");
                intent2.setPackage("com.android.systemui");
                Slog.d("MiTrustService/quickAuthEventHandle", "res is " + P0.c.f234v.a.bindService(intent2, kVar.f1692H, 1));
            }
        } else {
            Slog.d("MiTrustService/remoteAuthService", "commHandle is null");
        }
        if (action.equals(IMiRemoteAuthService.DESCRIPTOR)) {
            return this.f1735f;
        }
        if (action.equals(IMiRemoteAuthUnlockUI.DESCRIPTOR)) {
            return this.f1736g;
        }
        return null;
    }

    @Override // android.app.Service
    public final void onCreate() {
        Slog.v("MiTrustService/remoteAuthService", "OnCreate");
        super.onCreate();
        h = true;
        a.a().getClass();
        try {
            PackageManager packageManager = getPackageManager();
            getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo("com.xiaomi.mi_connect_service", 128);
            if (!applicationInfo.metaData.containsKey("com.xiaomi.continuity.VERSION_CODE") || ((Integer) applicationInfo.metaData.get("com.xiaomi.continuity.VERSION_CODE")).intValue() <= 0) {
                Slog.d("MiTrustService/remoteAuthService", "continuity not found");
            } else {
                this.a = P0.c.c(this);
            }
        } catch (PackageManager.NameNotFoundException unused) {
            Slog.d("MiTrustService/remoteAuthService", "continuity not found");
        }
        Context applicationContext = getApplicationContext();
        g.f1673m = applicationContext;
        if (applicationContext == null) {
            return;
        }
        try {
            SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("remoteauth_bind", 0);
            if (sharedPreferences.contains("apk_record_reboot")) {
                g.f1671k = sharedPreferences.getBoolean("apk_record_reboot", true);
                Slog.d("MiTrustService/queryStatusEventHandle", "restoreRebootFlag from SP: " + g.f1671k);
            } else {
                Slog.d("MiTrustService/queryStatusEventHandle", "restoreRebootFlag: no persisted value, keep default true");
            }
        } catch (Exception e2) {
            Slog.d("MiTrustService/queryStatusEventHandle", "restoreRebootFlag error: " + e2);
        }
    }

    @Override // android.app.Service
    public final void onDestroy() {
        Slog.v("MiTrustService/remoteAuthService", "onDestroy");
        P0.c cVar = this.a;
        if (cVar != null) {
            cVar.e(2);
        }
        h = false;
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i2, int i3) {
        Slog.v("MiTrustService/remoteAuthService", "onStartCommand");
        if (this.a == null) {
            return 2;
        }
        Slog.d("MiTrustService/remoteAuthService", "startCommService");
        P0.c cVar = this.a;
        cVar.getClass();
        Slog.d("MiTrustService/CommHandle", "try start CommService");
        cVar.a = this;
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - cVar.f249s <= 200) {
            return 2;
        }
        cVar.f249s = jCurrentTimeMillis;
        Intent intent2 = new Intent();
        intent2.setClass(this, CommService.class);
        cVar.a.bindService(intent2, cVar.f250t, 1);
        return 2;
    }
}
