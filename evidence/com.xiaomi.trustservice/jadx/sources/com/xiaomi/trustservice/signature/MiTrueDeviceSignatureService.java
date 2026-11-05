package com.xiaomi.trustservice.signature;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.xiaomi.continuity.netbus.h;
import java.lang.reflect.InvocationTargetException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import vendor.xiaomi.hardware.mitruedevice.IMiTrueDeviceService;

/* JADX INFO: loaded from: classes.dex */
public class MiTrueDeviceSignatureService extends Service {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final HashMap f1768e;
    public final I0.b a;
    public IMiTrueDeviceService b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final h f1769c = new h(9, this);
    public final b d = new b(this);

    static {
        HashMap map = new HashMap();
        f1768e = map;
        map.put("com.xiaomi.mitrust.testapp", "C9:00:9D:01:EB:F9:F5:D0:30:2B:C7:1B:2F:E9:AA:9A:47:A4:32:BB:A1:73:08:A3:11:1B:75:D7:B2:14:90:25");
        map.put("com.rongcard.eid", "D4:5F:07:6F:E2:3A:1A:5B:7F:48:6E:3F:F4:15:47:A2:02:3D:BF:E1:FE:73:35:3B:1E:48:EB:DF:ED:72:CC:6F");
    }

    public MiTrueDeviceSignatureService() {
        try {
            I0.b bVar = new I0.b();
            this.a = bVar;
            synchronized (bVar) {
                bVar.d = "SECURE_SALT_2025";
            }
        } catch (CertificateException e2) {
            Log.e("MiTrueDeviceSignatureService", "Failed to load root certificate", e2);
        }
    }

    public static boolean a(MiTrueDeviceSignatureService miTrueDeviceSignatureService) {
        for (int i2 = 0; i2 < 3; i2++) {
            if (i2 > 0) {
                try {
                    Thread.sleep(200L);
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            }
            try {
                try {
                    Class<?> cls = Class.forName("android.os.ServiceManager");
                    IBinder iBinder = (IBinder) cls.getDeclaredMethod("waitForService", String.class).invoke(cls, "vendor.xiaomi.hardware.mitruedevice.IMiTrueDeviceService/default");
                    if (iBinder != null) {
                        IMiTrueDeviceService iMiTrueDeviceServiceAsInterface = IMiTrueDeviceService.Stub.asInterface(iBinder);
                        miTrueDeviceSignatureService.b = iMiTrueDeviceServiceAsInterface;
                        if (iMiTrueDeviceServiceAsInterface != null) {
                            Log.d("MiTrueDeviceSignatureService", "TrueDeviceService initialized in " + (i2 + 1) + " attempts");
                            return true;
                        }
                        Log.w("MiTrueDeviceSignatureService", "Null IMiTrueDeviceService instance");
                        miTrueDeviceSignatureService.b = null;
                    } else {
                        continue;
                    }
                } catch (Exception e2) {
                    Log.e("MiTrueDeviceSignatureService", "Unexpected error: " + e2.getMessage());
                }
            } catch (ClassNotFoundException | NoSuchMethodException e3) {
                Log.e("MiTrueDeviceSignatureService", "System API missing: " + e3.getMessage());
            } catch (IllegalAccessException | InvocationTargetException e4) {
                Log.e("MiTrueDeviceSignatureService", "Reflection error: " + e4.getCause().getMessage());
            }
        }
        Log.e("MiTrueDeviceSignatureService", "Failed to initialize TrueDeviceService after 3 attempts");
        miTrueDeviceSignatureService.b = null;
        return false;
    }

    public static boolean b(String str) {
        if (str.length() % 4 != 0) {
            return false;
        }
        int iIndexOf = str.indexOf(61);
        if (iIndexOf != -1) {
            for (int i2 = iIndexOf; i2 < str.length(); i2++) {
                if (str.charAt(i2) != '=') {
                    return false;
                }
            }
            if (str.length() - iIndexOf > 2) {
                return false;
            }
        }
        for (int i3 = 0; i3 < str.length(); i3++) {
            char cCharAt = str.charAt(i3);
            if (cCharAt != '=' && "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(cCharAt) == -1) {
                return false;
            }
        }
        return true;
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.d;
    }
}
