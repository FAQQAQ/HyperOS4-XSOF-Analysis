package com.ifaa.seccam;

import android.os.Build;
import android.os.NativeHandle;
import android.os.RemoteException;
import android.os.SharedMemory;
import android.util.Slog;
import java.io.FileDescriptor;

/* JADX INFO: loaded from: classes.dex */
public final class c extends IFAASecCamInterface.Stub {
    public final /* synthetic */ IFAASecCamService a;

    public c(IFAASecCamService iFAASecCamService) {
        this.a = iFAASecCamService;
    }

    @Override // com.ifaa.seccam.IFAASecCamInterface
    public final int closeSecCam() {
        IFAASecCamService iFAASecCamService = this.a;
        IFAASecCamService.a();
        try {
            if (iFAASecCamService.a.e()) {
                return iFAASecCamService.a.a();
            }
            Slog.e("MiTrustService/IFAASecCamService", "[closeSecCam] IMISecCamService not found");
            return -1;
        } catch (RemoteException e2) {
            Slog.e("MiTrustService/IFAASecCamService", "[closeSecCam] transact fail: " + e2);
            return -1;
        }
    }

    @Override // com.ifaa.seccam.IFAASecCamInterface
    public final String getDeviceModel() {
        String str = "Xiaomi-" + Build.DEVICE;
        Slog.d("MiTrustService/IFAASecCamService", "[getDeviceModel] devcieModel: " + str);
        return str;
    }

    @Override // com.ifaa.seccam.IFAASecCamInterface
    public final int getSecImg(int i2, SharedMemory sharedMemory) {
        IFAASecCamService iFAASecCamService = this.a;
        IFAASecCamService.a();
        if (sharedMemory == null) {
            Slog.e("MiTrustService/IFAASecCamService", "[getSecImg] imgBuff is null");
            return -1;
        }
        try {
            if (!iFAASecCamService.a.e()) {
                Slog.e("MiTrustService/IFAASecCamService", "[getSecImg] IMISecCamService not found");
                return -2;
            }
            return iFAASecCamService.a.c(i2, (NativeHandle) NativeHandle.class.getConstructor(FileDescriptor.class, Boolean.TYPE).newInstance((FileDescriptor) sharedMemory.getClass().getMethod("getFileDescriptor", null).invoke(sharedMemory, null), Boolean.FALSE));
        } catch (RemoteException e2) {
            Slog.e("MiTrustService/IFAASecCamService", "[getSecImg] transact fail: " + e2);
            return -2;
        } catch (Exception e3) {
            e3.printStackTrace();
            return -2;
        }
    }

    @Override // com.ifaa.seccam.IFAASecCamInterface
    public final int getVersion() {
        Slog.d("MiTrustService/IFAASecCamService", "Try get version");
        return 301;
    }

    @Override // com.ifaa.seccam.IFAASecCamInterface
    public final int initSecCam(SharedMemory sharedMemory) {
        IFAASecCamService iFAASecCamService = this.a;
        IFAASecCamService.a();
        if (sharedMemory == null) {
            Slog.e("MiTrustService/IFAASecCamService", "[getSecImg] pubBuff is null");
            return -1;
        }
        try {
            if (!iFAASecCamService.a.e()) {
                Slog.e("MiTrustService/IFAASecCamService", "[closeSecCam] IMISecCamService not found");
                return -2;
            }
            return iFAASecCamService.a.d((NativeHandle) NativeHandle.class.getConstructor(FileDescriptor.class, Boolean.TYPE).newInstance((FileDescriptor) sharedMemory.getClass().getMethod("getFileDescriptor", null).invoke(sharedMemory, null), Boolean.FALSE));
        } catch (RemoteException e2) {
            Slog.e("MiTrustService/IFAASecCamService", "[initSecCam] transact fail: " + e2);
            return -2;
        } catch (Exception e3) {
            e3.printStackTrace();
            return -2;
        }
    }

    @Override // com.ifaa.seccam.IFAASecCamInterface
    public final int openSecCam(int i2, IFAASecCamCallback iFAASecCamCallback) {
        IFAASecCamService iFAASecCamService = this.a;
        IFAASecCamService.a();
        try {
            if (!iFAASecCamService.a.e()) {
                Slog.e("MiTrustService/IFAASecCamService", "[openSecCam] IMISecCamService not found");
                return -1;
            }
            f fVar = iFAASecCamService.a;
            fVar.d = iFAASecCamCallback;
            return fVar.f(i2);
        } catch (RemoteException e2) {
            Slog.e("MiTrustService/IFAASecCamService", "[openSecCam] transact fail: " + e2);
            return -1;
        }
    }
}
