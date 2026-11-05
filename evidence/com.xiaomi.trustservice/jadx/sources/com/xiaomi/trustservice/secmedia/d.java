package com.xiaomi.trustservice.secmedia;

import A0.o;
import G.f;
import android.os.Binder;
import android.os.Bundle;
import android.os.NativeHandle;
import android.os.RemoteException;
import android.os.SharedMemory;
import android.system.ErrnoException;
import android.util.Log;
import android.util.Slog;
import com.xiaomi.xms.auth.IAuthService;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;
import p004c1.n;

/* JADX INFO: loaded from: classes.dex */
public final class d extends IMiSecCamService.Stub {
    public final /* synthetic */ MiSecCamService a;

    public d(MiSecCamService miSecCamService) {
        this.a = miSecCamService;
    }

    @Override // com.xiaomi.trustservice.secmedia.IMiSecCamService
    public final void closeMiSecCam() {
        Slog.d("MiTrustService/MiSecCamService", "closeMiSecCam");
        MiSecCamService.a();
        MiSecCamService miSecCamService = this.a;
        if (miSecCamService.b()) {
            try {
                miSecCamService.a.cancel();
            } catch (RemoteException unused) {
                Slog.d("MiTrustService/MiSecCamService", "misecmedia service may crash");
                miSecCamService.a = null;
            }
        }
    }

    @Override // com.xiaomi.trustservice.secmedia.IMiSecCamService
    public final int openMiSecCam(IMiSecCamCallback iMiSecCamCallback) {
        b1.d dVar;
        Slog.d("MiTrustService/MiSecCamService", "openMiSecCam");
        MiSecCamService.a();
        int callingPid = Binder.getCallingPid();
        new Thread(new G.b(this, callingPid, 4)).start();
        final b1.c cVar = new b1.c();
        MiSecCamService miSecCamService = this.a;
        cVar.a = o.O(miSecCamService, callingPid);
        cVar.b = 200L;
        final f fVar = b1.b.a;
        fVar.getClass();
        CompletableFuture completableFutureSupplyAsync = CompletableFuture.supplyAsync(new Supplier() { // from class: b1.a
            @Override // java.util.function.Supplier
            public final Object get() {
                d dVar2;
                f fVar2 = fVar;
                c cVar2 = cVar;
                synchronized (fVar2) {
                    try {
                        if (!fVar2.b()) {
                            fVar2.f139c = new CountDownLatch(1);
                            androidx.emoji2.text.o.H("AuthConnectManagerForApk", "connect");
                            e eVar = (e) fVar2.f141f;
                            if (eVar != null) {
                                n.a.a(eVar);
                            }
                            boolean zAwait = ((CountDownLatch) fVar2.f139c).await(1000L, TimeUnit.MILLISECONDS);
                            fVar2.f139c = null;
                            if (!zAwait) {
                                androidx.emoji2.text.o.L("Sync connect timed out!");
                                return new d(-110, "Sync connect timed out!", null);
                            }
                            if (!fVar2.b()) {
                                androidx.emoji2.text.o.L("Sync connect error.");
                                return new d(-100, "Sync connect error.", (Bundle) fVar2.f140e);
                            }
                        }
                        dVar2 = d.a(((IAuthService) fVar2.b).syncAuth(cVar2.a()));
                    } catch (Exception e2) {
                        Log.e("AuthConnectManager", "[XMS][SDK_AUTH_VERSION: 1.0.2] ".concat("Sync auth error"), e2);
                        dVar2 = new d(-100, "Sync auth error, " + e2.getMessage(), null);
                    }
                    return dVar2;
                }
            }
        });
        try {
            long j2 = cVar.b;
            dVar = j2 > 0 ? (b1.d) completableFutureSupplyAsync.get(j2, TimeUnit.MILLISECONDS) : (b1.d) completableFutureSupplyAsync.get();
        } catch (TimeoutException unused) {
            dVar = new b1.d(-110, "Sync auth timed out!", null);
        } catch (Exception e2) {
            Log.e("AuthConnectManager", "[XMS][SDK_AUTH_VERSION: 1.0.2] ".concat("Sync auth error."), e2);
            dVar = new b1.d(-100, "Sync auth error, " + e2.getMessage(), null);
        }
        int iMisec_media_get_data = 1;
        completableFutureSupplyAsync.cancel(true);
        fVar.f140e = null;
        androidx.emoji2.text.o.L("Sync auth result: " + dVar);
        int i2 = dVar.a;
        if (i2 != 0) {
            StringBuilder sb = new StringBuilder("auth result is fail, error code is:");
            sb.append(i2);
            sb.append(",msh=");
            B.b.i(sb, dVar.b, "MiTrustService/MiSecCamService");
            if (i2 == -200 || i2 == -300) {
                return 132;
            }
        }
        if (!miSecCamService.b()) {
            return 128;
        }
        if (miSecCamService.f1764c == null) {
            Slog.d("MiTrustService/MiSecCamService", "try init shared memory");
            try {
                miSecCamService.f1764c = SharedMemory.create("secMediaMemory", 512000);
            } catch (ErrnoException unused2) {
                miSecCamService.f1764c = null;
            }
        }
        if (miSecCamService.f1764c == null) {
            Slog.d("MiTrustService/MiSecCamService", "failed to create shared memory");
            return 129;
        }
        miSecCamService.b = iMiSecCamCallback;
        Slog.d("MiTrustService/MiSecCamService", "try start call getdate");
        try {
            try {
                NativeHandle nativeHandle = (NativeHandle) NativeHandle.class.getConstructor(FileDescriptor.class, Boolean.TYPE).newInstance((FileDescriptor) miSecCamService.f1764c.getClass().getMethod("getFileDescriptor", null).invoke(miSecCamService.f1764c, null), Boolean.FALSE);
                if (!miSecCamService.a.misec_media_init(1, true)) {
                    return 130;
                }
                int i3 = 0;
                while (i3 <= 30) {
                    iMisec_media_get_data = miSecCamService.a.misec_media_get_data(miSecCamService.f1766f, com.ifaa.seccam.f.b(nativeHandle));
                    if (iMisec_media_get_data != 13) {
                        break;
                    }
                    i3++;
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException unused3) {
                    }
                }
                Slog.d("MiTrustService/MiSecCamService", "misec_media_get_data ends with res " + iMisec_media_get_data + " counter " + i3);
            } catch (IOException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e3) {
                Slog.d("MiTrustService/MiSecCamService", "error: " + e3);
            }
            return iMisec_media_get_data;
        } catch (RemoteException unused4) {
            Slog.d("MiTrustService/MiSecCamService", "misecmedia service may crash");
            miSecCamService.a = null;
            return 131;
        }
    }
}
