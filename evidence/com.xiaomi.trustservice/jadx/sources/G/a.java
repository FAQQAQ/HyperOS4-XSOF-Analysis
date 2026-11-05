package G;

import J.AbstractC0026s;
import J.G;
import android.graphics.Typeface;
import android.os.RemoteException;
import android.util.Slog;
import android.view.View;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.xiaomi.trustservice.rootpub.PubCertRevise;
import java.io.IOException;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Runnable {
    public final /* synthetic */ int a;
    public final Object b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f137c;

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public /* synthetic */ a(Object obj, int i2, Object obj2) {
        this.a = i2;
        this.b = obj;
        this.f137c = obj2;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:33:0x0020 */
    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: G.a */
    /* JADX DEBUG: Multi-variable search result rejected for r5v4, resolved type: G.a */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v8, types: [com.xiaomi.trustservice.rootpub.PubCertRevise] */
    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                A.b bVar = (A.b) ((B.h) this.b).b;
                if (bVar != null) {
                    bVar.h((Typeface) this.f137c);
                    return;
                }
                return;
            case 1:
                ((h) this.b).a(this.f137c);
                return;
            case 2:
                try {
                    try {
                        if (((String) this.b).equals("com.xiaomi.trustservice.rootpub.PubCertRevise")) {
                            ((PubCertRevise) this.f137c).a();
                        } else if (((String) this.b).equals("com.xiaomi.trustservice.rootpub.RotateKeyRevise")) {
                            ((PubCertRevise) this.f137c).b();
                        }
                        break;
                    } catch (E1.b | RemoteException | IOException e2) {
                        Slog.e("MiTrustService/PubCertRevise", "PubCertRevise failed: " + e2.getClass().getSimpleName() + ": " + e2.getMessage(), e2);
                    }
                    return;
                } finally {
                    ((PubCertRevise) this.f137c).a = null;
                }
            default:
                P.e eVar = ((SwipeDismissBehavior) this.f137c).a;
                if (eVar == null || !eVar.f()) {
                    return;
                }
                WeakHashMap weakHashMap = G.a;
                AbstractC0026s.m((View) this.b, this);
                return;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
    public a(PubCertRevise pubCertRevise, String str) {
        this.a = 2;
        this.f137c = pubCertRevise;
        this.b = str;
    }

    public a(SwipeDismissBehavior swipeDismissBehavior, View view, boolean z2) {
        this.a = 3;
        this.f137c = swipeDismissBehavior;
        this.b = view;
    }
}
