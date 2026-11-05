package com.xiaomi.trustservice.secmedia;

import android.os.RemoteException;
import android.os.SharedMemory;
import android.system.ErrnoException;
import android.util.Slog;
import java.util.Arrays;
import vendor.xiaomi.hardware.misecmedia.IMiSecMediaCallback;

/* JADX INFO: loaded from: classes.dex */
public final class c extends IMiSecMediaCallback.Stub {
    public final /* synthetic */ MiSecCamService a;

    public c(MiSecCamService miSecCamService) {
        this.a = miSecCamService;
    }

    @Override // vendor.xiaomi.hardware.misecmedia.IMiSecMediaCallback
    public final String getInterfaceHash() {
        return null;
    }

    @Override // vendor.xiaomi.hardware.misecmedia.IMiSecMediaCallback
    public final int getInterfaceVersion() {
        return 0;
    }

    @Override // vendor.xiaomi.hardware.misecmedia.IMiSecMediaCallback
    public final boolean onFrameCallback(int i2) {
        SharedMemory sharedMemory;
        MiSecCamService miSecCamService = this.a;
        Slog.d("MiTrustService/MiSecCamService", "get callback");
        try {
            if (miSecCamService.b == null || (sharedMemory = miSecCamService.f1764c) == null) {
                Slog.d("MiTrustService/MiSecCamService", "callback is null or jpgmemory is null");
            } else {
                miSecCamService.d = sharedMemory.mapReadOnly();
                miSecCamService.d.get(miSecCamService.f1765e);
                byte[] bArr = miSecCamService.f1765e;
                int i3 = (bArr[0] & 255) | ((bArr[3] & 255) << 24) | ((bArr[2] & 255) << 16) | ((bArr[1] & 255) << 8);
                Slog.d("MiTrustService/MiSecCamService", "length is:" + i3);
                miSecCamService.b.onFrameCallback(Arrays.copyOfRange(miSecCamService.f1765e, 4, i3 + 4));
            }
        } catch (RemoteException | ErrnoException e2) {
            Slog.d("MiTrustService/MiSecCamService", "[onFrameCallback] transact fail: " + e2);
        }
        return true;
    }
}
