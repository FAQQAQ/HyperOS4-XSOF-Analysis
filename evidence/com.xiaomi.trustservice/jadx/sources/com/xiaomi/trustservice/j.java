package com.xiaomi.trustservice;

import android.os.Binder;
import android.os.RemoteException;
import android.util.Slog;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeoutException;
import vendor.xiaomi.hardware.mrm.IMrm;

/* JADX INFO: loaded from: classes.dex */
public final class j extends IMiTrustService.Stub {
    public final /* synthetic */ MiTrustService a;

    public j(MiTrustService miTrustService) {
        this.a = miTrustService;
    }

    @Override // com.xiaomi.trustservice.IMiTrustService
    public final String getTrustStatus(String str, byte[] bArr, String str2) {
        Slog.d("MiTrustService", "getTrustStatus");
        int callingPid = Binder.getCallingPid();
        MiTrustService miTrustService = this.a;
        String strO = A0.o.O(miTrustService, callingPid);
        if (!strO.equals("com.miui.securitycenter") && !strO.equals("com.xiaomi.mitrusttest30")) {
            Slog.d("MiTrustService", "cant be called by this apk:".concat(strO));
            return A0.o.I((byte) -125);
        }
        try {
            new E1.c(str);
            ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
            FutureTask futureTask = new FutureTask(new i(miTrustService));
            executorServiceNewSingleThreadExecutor.submit(futureTask);
            try {
                IMrm iMrm = miTrustService.a;
                if (iMrm == null) {
                    Slog.d("MiTrustService", "failed to get mrmd service");
                    Slog.d("MiTrustService", "initService failed");
                    return A0.o.I((byte) -127);
                }
                try {
                    p001a1.a aVarT0 = A0.o.t0(iMrm.getStatusByAuth(str.getBytes(), str2.getBytes(StandardCharsets.US_ASCII), bArr));
                    byte b = aVarT0.a;
                    return b != 0 ? A0.o.I(b) : new String(aVarT0.b);
                } catch (RemoteException unused) {
                    Slog.d("MiTrustService", "mrm service may crash");
                    miTrustService.a = null;
                    return A0.o.I((byte) -126);
                }
            } catch (InterruptedException | ExecutionException | TimeoutException e2) {
                Slog.d("MiTrustService", "error: " + e2.toString());
            }
        } catch (E1.b unused2) {
            Slog.d("MiTrustService", "json format error");
            return A0.o.I((byte) -128);
        }
    }

    @Override // com.xiaomi.trustservice.IMiTrustService
    public final int getVersion() {
        Slog.d("MiTrustService", "miTrustServiceBinder getVersion");
        return 117;
    }
}
