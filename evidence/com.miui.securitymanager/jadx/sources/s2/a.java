package s2;

import android.os.IBinder;
import android.os.ServiceManager;
import android.util.Log;

/* JADX INFO: compiled from: r8-map-id-439d64a6e73e6da60b62f1cd0d3775067af568959c6537234e5044e8e318ed73 */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static a b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f3789a;

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public a() {
        try {
            this.f3789a = Class.forName("com.xiaomi.mimotion.IMimotionModeService$Stub").getMethod("asInterface", IBinder.class).invoke(null, ServiceManager.getService("MimotionModeService"));
        } catch (Exception unused) {
            Log.e("MiMotionCloudConfig", "M2CloudConfig init failed... ");
        }
    }
}
