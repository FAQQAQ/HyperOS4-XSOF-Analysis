package e1;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Looper;
import android.os.MessageQueue;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import com.miui.securitymain.SCMainEntryActivity;
import h3.a0;
import h3.s;
import h3.z;
import i.n0;
import java.lang.reflect.Method;
import r2.g;

/* JADX INFO: compiled from: r8-map-id-439d64a6e73e6da60b62f1cd0d3775067af568959c6537234e5044e8e318ed73 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1047a;
    public final /* synthetic */ Object b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f1048c;

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public /* synthetic */ a(Object obj, Object obj2, int i4) {
        this.f1047a = i4;
        this.b = obj;
        this.f1048c = obj2;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX WARN: Code duplicated, block: B:31:0x00d3  */
    @Override // java.lang.Runnable
    public final void run() {
        int i4 = this.f1047a;
        Object obj = this.f1048c;
        Object obj2 = this.b;
        switch (i4) {
            case 0:
                SCMainEntryActivity sCMainEntryActivity = (SCMainEntryActivity) obj2;
                final Context context = (Context) obj;
                int i5 = SCMainEntryActivity.f852n;
                Intent intent = new Intent();
                intent.setAction("miui.intent.action.SECURITY_CENTER");
                intent.setPackage("com.miui.securitycenter");
                intent.addFlags(268435456);
                if (v2.a.b) {
                    try {
                        Configuration configuration = sCMainEntryActivity.getResources().getConfiguration();
                        Log.i("FreeformUtil", "configuration : " + configuration.toString());
                        if (configuration.toString().contains("mWindowingMode=freeform")) {
                            Log.i("SCMainEntryActivity", "is padFreeform");
                            ActivityOptions activityOptions = null;
                            try {
                                Class<?> cls = Class.forName("android.util.MiuiMultiWindowUtils");
                                Class<?>[] clsArr = {Context.class, String.class, Boolean.TYPE};
                                Object[] objArr = {sCMainEntryActivity, "com.miui.securitycenter", Boolean.TRUE};
                                Method declaredMethod = cls.getDeclaredMethod("getActivityOptions", clsArr);
                                declaredMethod.setAccessible(true);
                                activityOptions = (ActivityOptions) declaredMethod.invoke(null, objArr);
                            } catch (Exception e4) {
                                Log.e("FreeformUtil", e4.toString());
                            }
                            if (activityOptions != null) {
                                Log.i("SCMainEntryActivity", "smallWindowStartRealMainActivity");
                                sCMainEntryActivity.startActivity(intent, activityOptions.toBundle());
                            } else {
                                Log.i("SCMainEntryActivity", "startRealMainActivity");
                                sCMainEntryActivity.startActivity(intent);
                            }
                        } else {
                            Log.i("SCMainEntryActivity", "startRealMainActivity");
                            sCMainEntryActivity.startActivity(intent);
                        }
                    } catch (Exception e5) {
                        Log.e("FreeformUtil", e5.toString());
                    }
                } else {
                    Log.i("SCMainEntryActivity", "startRealMainActivity");
                    sCMainEntryActivity.startActivity(intent);
                }
                Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() { // from class: e1.b
                    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
                    @Override // android.os.MessageQueue.IdleHandler
                    public final boolean queueIdle() {
                        Context context2 = context;
                        int i6 = SCMainEntryActivity.f852n;
                        try {
                            c1.a.a(context2);
                            return false;
                        } catch (Throwable th) {
                            Log.e("SCMainEntryActivity", "addShortcuts failed", th);
                            return false;
                        }
                    }
                });
                sCMainEntryActivity.finish();
                break;
            case 1:
                View view = (View) obj;
                a0 a0Var = (a0) ((s) obj2).b;
                z zVar = a0Var.f1426a;
                if (zVar != null && zVar.isAttachedToWindow()) {
                    a0Var.H(view);
                    break;
                }
                break;
            case 2:
                g gVar = (g) obj2;
                gVar.getClass();
                WindowInsets rootWindowInsets = ((View) obj).getRootWindowInsets();
                if (rootWindowInsets != null) {
                    gVar.f3595a.f(rootWindowInsets);
                }
                break;
            default:
                ((n0) obj2).b((Typeface) obj);
                break;
        }
    }
}
