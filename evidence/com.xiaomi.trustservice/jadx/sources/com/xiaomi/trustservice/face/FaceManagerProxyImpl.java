package com.xiaomi.trustservice.face;

import A0.o;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.face.FaceManager;
import android.os.Binder;
import android.os.CancellationSignal;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.util.Slog;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class FaceManagerProxyImpl extends Service {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final HashMap f1561e;
    public final d a = new d(this);
    public FaceManager b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public e f1562c;
    public CancellationSignal d;

    static {
        HashMap map = new HashMap();
        f1561e = map;
        map.put("com.mi.car.mobile", new f(0));
        map.put("com.xiaomi.subscreencenter", new f());
    }

    public static boolean a(FaceManagerProxyImpl faceManagerProxyImpl) {
        int callingPid = Binder.getCallingPid();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) faceManagerProxyImpl.getSystemService(ActivityManager.class)).getRunningAppProcesses();
        String str = null;
        if (runningAppProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == callingPid) {
                    str = runningAppProcessInfo.processName;
                    break;
                }
            }
        }
        if (TextUtils.isEmpty(str)) {
            Slog.w("MiTrustService/FaceManagerProxy", "unable to obtain packageName from specified PID(" + callingPid + ")");
            return false;
        }
        Slog.i("MiTrustService/FaceManagerProxy", "received request check. packageName: " + str + ", pid: " + callingPid);
        HashMap map = f1561e;
        if (!map.containsKey(str)) {
            if (!str.contains(":")) {
                return false;
            }
            str = str.split(":")[0];
            if (!map.containsKey(str)) {
                return false;
            }
        }
        f fVar = (f) map.get(str);
        Objects.requireNonNull(fVar);
        PackageManager packageManager = faceManagerProxyImpl.getPackageManager();
        String str2 = fVar.a;
        if (!str2.equals(str)) {
            return false;
        }
        try {
            String strP = o.P(packageManager.getPackageInfo(str, 134217728));
            Log.i("MiTrustService/FaceManagerProxy", "pkg: " + str + ", signatureSha256: " + strP);
            if (fVar.f1563c) {
                try {
                    return o.P(packageManager.getPackageInfo("com.android.settings", 134217728)).equalsIgnoreCase(strP);
                } catch (PackageManager.NameNotFoundException e2) {
                    throw new RuntimeException(e2);
                }
            }
            String str3 = fVar.b;
            Objects.requireNonNull(str3);
            return str3.equalsIgnoreCase(strP);
        } catch (PackageManager.NameNotFoundException unused) {
            Slog.w("MiTrustService/FaceManagerProxy", "the specified package name could not be found. packageName: ".concat(str2));
            return false;
        }
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        Slog.v("MiTrustService/FaceManagerProxy", "FaceManagerProxyImpl onBind");
        return this.a;
    }

    @Override // android.app.Service
    public final void onCreate() {
        Slog.v("MiTrustService/FaceManagerProxy", "FaceManagerProxyImpl OnCreate");
        super.onCreate();
    }

    @Override // android.app.Service
    public final void onDestroy() {
        Slog.d("MiTrustService/FaceManagerProxy", "Task FaceManagerProxy destroy");
    }
}
