package com.ifaa.seccam;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Slog;
import androidx.emoji2.text.o;

/* JADX INFO: loaded from: classes.dex */
public class IFAASecCamService extends Service {
    public f a;
    public final c b;

    public IFAASecCamService() {
        f fVar = new f();
        fVar.a = 0;
        fVar.b = null;
        fVar.f1467c = null;
        fVar.d = null;
        fVar.f1468e = new d(fVar);
        fVar.f1469f = new e(fVar);
        this.a = fVar;
        this.b = new c(this);
        Slog.v("MiTrustService/IFAASecCamService", "IFAASecCamService Construct");
    }

    public static void a() {
        if (o.G(o.J(Binder.getCallingUid()), "android.permission.CAMERA") == 0) {
            return;
        }
        String str = "Permission Denial from pid=" + Binder.getCallingPid() + ", uid=" + Binder.getCallingUid() + " requires android.permission.CAMERA";
        Slog.w("MiTrustService/IFAASecCamService", str);
        throw new SecurityException(str);
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        Slog.v("MiTrustService/IFAASecCamService", "OnBind");
        return this.b;
    }

    @Override // android.app.Service
    public final void onCreate() {
        Slog.v("MiTrustService/IFAASecCamService", "OnCreate");
        super.onCreate();
    }

    @Override // android.app.Service
    public final void onDestroy() {
        Slog.v("MiTrustService/IFAASecCamService", "onDestroy");
        this.a = null;
    }
}
