package i1;

import android.app.AlarmManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.util.Log;
import x1.t;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f1696b = 0;
    public final /* synthetic */ l.d a;

    static {
        int i3 = t.f2676b;
        y1.c.a("application/json");
    }

    public a(l.d dVar, Context context) {
        this.a = dVar;
        new j(1, this, context).start();
    }

    public final void a(int i3) {
        l.d dVar = this.a;
        if (i3 != 1) {
            if (i3 != 2) {
                if (i3 != 3) {
                    return;
                }
                SharedPreferences sharedPreferences = (SharedPreferences) dVar.f1850c;
                int i4 = sharedPreferences.getInt("register_request_count", 0) + 1;
                sharedPreferences.edit().putInt("register_request_count", i4).apply();
                if (i4 <= 3) {
                    Object obj = dVar.a;
                    try {
                        ((AlarmManager) ((b) obj).f1700e).setExactAndAllowWhileIdle(2, SystemClock.elapsedRealtime() + 1800000, ((b) obj).a("miui.intent.action.CTEI_AUTO_REGISTRATION_RETRY"));
                        return;
                    } catch (Exception e3) {
                        Log.e("Ctei_Work", "retryRegisterTriggered", e3);
                        return;
                    }
                }
            }
            l.d.a(dVar);
        }
        ((SharedPreferences) dVar.f1850c).edit().putString("register_regtime", j1.c.d()).apply();
        l.d.b(dVar);
        l.d.a(dVar);
    }
}
