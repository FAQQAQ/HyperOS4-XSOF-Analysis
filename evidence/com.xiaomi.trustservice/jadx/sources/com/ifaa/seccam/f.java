package com.ifaa.seccam;

import android.hardware.common.NativeHandle;
import android.os.HwBinder;
import android.os.HwParcel;
import android.os.IBinder;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.util.Slog;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import vendor.xiaomi.hardware.seccam.IMiSecCam;

/* JADX INFO: loaded from: classes.dex */
public final class f {
    public int a;
    public F1.a b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public IMiSecCam f1467c;
    public IFAASecCamCallback d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public d f1468e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public e f1469f;

    public static NativeHandle b(android.os.NativeHandle nativeHandle) throws Exception {
        if (nativeHandle == null) {
            return null;
        }
        NativeHandle nativeHandle2 = new NativeHandle();
        FileDescriptor[] fileDescriptors = nativeHandle.getFileDescriptors();
        try {
            nativeHandle2.ints = (int[]) nativeHandle.getInts().clone();
            nativeHandle2.fds = new ParcelFileDescriptor[fileDescriptors.length];
            for (int i2 = 0; i2 < fileDescriptors.length; i2++) {
                nativeHandle2.fds[i2] = ParcelFileDescriptor.dup(fileDescriptors[i2]);
            }
            return nativeHandle2;
        } catch (Exception e2) {
            Slog.e("MiTrustService/ServiceCompatible", "NativeHandle getInts() error", e2);
            throw e2;
        }
    }

    public final int a() {
        IMiSecCam iMiSecCam = this.f1467c;
        if (iMiSecCam != null) {
            return iMiSecCam.closeSecCam();
        }
        F1.a aVar = this.b;
        if (aVar == null) {
            return -1;
        }
        aVar.getClass();
        HwParcel hwParcel = new HwParcel();
        hwParcel.writeInterfaceToken("vendor.xiaomi.hardware.miface@1.0::IMiFace");
        HwParcel hwParcel2 = new HwParcel();
        try {
            aVar.a.transact(7, hwParcel, hwParcel2, 0);
            hwParcel2.verifySuccess();
            hwParcel.releaseTemporaryStorage();
            return hwParcel2.readInt32();
        } finally {
            hwParcel2.release();
        }
    }

    public final int c(int i2, android.os.NativeHandle nativeHandle) {
        IMiSecCam iMiSecCam = this.f1467c;
        if (iMiSecCam != null) {
            try {
                return iMiSecCam.getSecImg(i2, b(nativeHandle));
            } catch (IOException e2) {
                Slog.d("MiTrustService/ServiceCompatible", e2.toString());
                return -2;
            }
        }
        F1.a aVar = this.b;
        if (aVar == null) {
            return -2;
        }
        aVar.getClass();
        HwParcel hwParcel = new HwParcel();
        hwParcel.writeInterfaceToken("vendor.xiaomi.hardware.miface@1.0::IMiFace");
        hwParcel.writeInt32(i2);
        hwParcel.writeNativeHandle(nativeHandle);
        HwParcel hwParcel2 = new HwParcel();
        try {
            aVar.a.transact(8, hwParcel, hwParcel2, 0);
            hwParcel2.verifySuccess();
            hwParcel.releaseTemporaryStorage();
            return hwParcel2.readInt32();
        } finally {
            hwParcel2.release();
        }
    }

    public final int d(android.os.NativeHandle nativeHandle) {
        IMiSecCam iMiSecCam = this.f1467c;
        if (iMiSecCam != null) {
            try {
                return iMiSecCam.initSecCam(b(nativeHandle));
            } catch (IOException e2) {
                Slog.d("MiTrustService/ServiceCompatible", e2.toString());
                return -2;
            }
        }
        F1.a aVar = this.b;
        if (aVar == null) {
            return -2;
        }
        aVar.getClass();
        HwParcel hwParcel = new HwParcel();
        hwParcel.writeInterfaceToken("vendor.xiaomi.hardware.miface@1.0::IMiFace");
        hwParcel.writeNativeHandle(nativeHandle);
        HwParcel hwParcel2 = new HwParcel();
        try {
            aVar.a.transact(5, hwParcel, hwParcel2, 0);
            hwParcel2.verifySuccess();
            hwParcel.releaseTemporaryStorage();
            return hwParcel2.readInt32();
        } finally {
            hwParcel2.release();
        }
    }

    public final boolean e() {
        try {
            Class<?> cls = Class.forName("android.os.ServiceManager");
            F1.a aVar = null;
            if (this.a == 0) {
                String str = SystemProperties.get("vendor.mseccam.auto.start", "true");
                Slog.d("MiTrustService/ServiceCompatible", str);
                if (str.equals("false")) {
                    Slog.d("MiTrustService/ServiceCompatible", "miseccam not auto start");
                    this.a = 1;
                } else {
                    try {
                        for (String str2 : (String[]) cls.getDeclaredMethod("listServices", null).invoke(cls, null)) {
                            if (str2.equals("vendor.xiaomi.hardware.seccam.IMiSecCam/default")) {
                                Slog.d("MiTrustService/ServiceCompatible", "aidl service found");
                                this.a = 1;
                                break;
                            }
                        }
                        if (this.a != 1) {
                            Slog.d("MiTrustService/ServiceCompatible", "aidl service not found, maybe hidl service");
                            this.a = 2;
                        }
                    } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
                        Slog.e("MiTrustService/ServiceCompatible", "listServices errror" + e2);
                    }
                }
            }
            if (this.a == 1 && this.f1467c == null) {
                try {
                    this.f1467c = IMiSecCam.Stub.asInterface((IBinder) cls.getDeclaredMethod("waitForService", String.class).invoke(cls, "vendor.xiaomi.hardware.seccam.IMiSecCam/default"));
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e3) {
                    Slog.e("MiTrustService/ServiceCompatible", "initService error" + e3);
                }
            }
            if (this.a == 2 && this.b == null) {
                IHwBinder service = HwBinder.getService("vendor.xiaomi.hardware.miface@1.0::IMiFace", "default");
                if (service != null) {
                    IHwInterface iHwInterfaceQueryLocalInterface = service.queryLocalInterface("vendor.xiaomi.hardware.miface@1.0::IMiFace");
                    if (iHwInterfaceQueryLocalInterface == null || !(iHwInterfaceQueryLocalInterface instanceof F1.a)) {
                        F1.a aVar2 = new F1.a();
                        aVar2.a = service;
                        try {
                            HwParcel hwParcel = new HwParcel();
                            hwParcel.writeInterfaceToken("android.hidl.base@1.0::IBase");
                            HwParcel hwParcel2 = new HwParcel();
                            try {
                                service.transact(256067662, hwParcel, hwParcel2, 0);
                                hwParcel2.verifySuccess();
                                hwParcel.releaseTemporaryStorage();
                                ArrayList stringVector = hwParcel2.readStringVector();
                                hwParcel2.release();
                                Iterator it = stringVector.iterator();
                                while (it.hasNext()) {
                                    if (((String) it.next()).equals("vendor.xiaomi.hardware.miface@1.0::IMiFace")) {
                                        aVar = aVar2;
                                        break;
                                    }
                                }
                            } catch (Throwable th) {
                                hwParcel2.release();
                                throw th;
                            }
                        } catch (RemoteException unused) {
                        }
                    } else {
                        aVar = (F1.a) iHwInterfaceQueryLocalInterface;
                    }
                }
                this.b = aVar;
            }
            return (this.f1467c == null && this.b == null) ? false : true;
        } catch (ClassNotFoundException e4) {
            Slog.e("MiTrustService/ServiceCompatible", "ClassNotFoundException" + e4);
            return false;
        }
    }

    public final int f(int i2) {
        IMiSecCam iMiSecCam = this.f1467c;
        if (iMiSecCam != null) {
            return iMiSecCam.openSecCam(i2, this.f1469f);
        }
        F1.a aVar = this.b;
        if (aVar == null) {
            return -1;
        }
        IHwBinder iHwBinder = this.f1468e;
        HwParcel hwParcel = new HwParcel();
        hwParcel.writeInterfaceToken("vendor.xiaomi.hardware.miface@1.0::IMiFace");
        hwParcel.writeInt32(i2);
        if (iHwBinder == null) {
            iHwBinder = null;
        }
        hwParcel.writeStrongBinder(iHwBinder);
        HwParcel hwParcel2 = new HwParcel();
        try {
            aVar.a.transact(6, hwParcel, hwParcel2, 0);
            hwParcel2.verifySuccess();
            hwParcel.releaseTemporaryStorage();
            return hwParcel2.readInt32();
        } finally {
            hwParcel2.release();
        }
    }
}
