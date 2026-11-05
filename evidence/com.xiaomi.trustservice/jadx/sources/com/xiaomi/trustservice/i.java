package com.xiaomi.trustservice;

import android.os.IBinder;
import android.util.Slog;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Callable;
import vendor.xiaomi.hardware.mrm.IMrm;

/* JADX INFO: loaded from: classes.dex */
public final class i implements Callable {
    public final /* synthetic */ MiTrustService a;

    public i(MiTrustService miTrustService) {
        this.a = miTrustService;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        MiTrustService miTrustService = this.a;
        if (miTrustService.a != null) {
            return "Operation result";
        }
        Slog.d("MiTrustService", "try init mrmd Service");
        try {
            Class<?> cls = Class.forName("android.os.ServiceManager");
            miTrustService.a = IMrm.Stub.asInterface((IBinder) cls.getDeclaredMethod("waitForService", String.class).invoke(cls, "vendor.xiaomi.hardware.mrm.IMrm/default"));
            return "Operation result";
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            Slog.d("MiTrustService", "initService error " + e2);
            return "Operation result";
        }
    }
}
