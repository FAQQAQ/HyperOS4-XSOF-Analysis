package G;

import A0.o;
import Y.K;
import android.content.res.Resources;
import android.os.Looper;
import android.util.Log;
import android.util.Slog;
import android.view.ContextThemeWrapper;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.misight.mievent.MiSight;
import com.xiaomi.trustservice.u;
import com.xiaomi.trustservice.v;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Runnable {
    public final /* synthetic */ int a;
    public final int b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f138c;

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public /* synthetic */ b(Object obj, int i2, int i3) {
        this.a = i3;
        this.f138c = obj;
        this.b = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str;
        switch (this.a) {
            case 0:
                A.b bVar = (A.b) ((B.h) this.f138c).b;
                if (bVar != null) {
                    bVar.g(this.b);
                }
                break;
            case 1:
                Looper.prepare();
                P0.c cVar = P0.c.f234v;
                if (cVar != null) {
                    Resources resources = cVar.a.getResources();
                    int i2 = this.b;
                    if (i2 != 1) {
                        String str2 = (String) this.f138c;
                        if (i2 == 2) {
                            str = "" + resources.getString(u.remote_system_tempering, P0.c.f234v.b(str2));
                        } else if (i2 == 4) {
                            str = "" + resources.getString(u.remote_virtual_device, P0.c.f234v.b(str2));
                        } else if (i2 == 5) {
                            str = "" + resources.getString(u.security_module_shutdown);
                        } else if (i2 != 6) {
                            str = "" + resources.getString(u.unknow_error);
                        } else {
                            str = "" + resources.getString(u.remote_security_module_shutdown, P0.c.f234v.b(str2));
                        }
                    } else {
                        str = "" + resources.getString(u.system_tempering);
                    }
                    Slog.d("MiTrustService/statusEventHandle", str);
                    U0.c cVar2 = new U0.c(new ContextThemeWrapper(P0.c.f234v.a, v.Theme_MiuixStyleDialog));
                    cVar2.setTitle(u.system_warning);
                    cVar2.a.h = str;
                    cVar2.a.f276m = cVar2.getContext().getText(u.alert_confirm);
                    cVar2.getWindow().setType(2038);
                    cVar2.show();
                    Looper.loop();
                } else {
                    Slog.d("MiTrustService/statusEventHandle", "remote serice has been destoryed");
                }
                break;
            case 2:
                ArrayList arrayList = (ArrayList) this.f138c;
                int size = arrayList.size();
                int i3 = 0;
                if (this.b == 1) {
                    while (i3 < size) {
                        ((androidx.emoji2.text.f) arrayList.get(i3)).a();
                        i3++;
                    }
                } else {
                    while (i3 < size) {
                        ((androidx.emoji2.text.f) arrayList.get(i3)).getClass();
                        i3++;
                    }
                }
                break;
            case 3:
                RecyclerView recyclerView = ((com.google.android.material.datepicker.l) this.f138c).f1302Y;
                if (!recyclerView.f1043u) {
                    K k2 = recyclerView.f1028l;
                    if (k2 != null) {
                        k2.s0(recyclerView, this.b);
                    } else {
                        Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
                    }
                    break;
                }
                break;
            default:
                D0.a aVar = new D0.a();
                E1.c cVar3 = new E1.c();
                aVar.b = cVar3;
                aVar.a = 1000;
                System.currentTimeMillis();
                try {
                    ((E1.c) aVar.b).p("31000401973", "appId");
                } catch (E1.b e2) {
                    D0.a.c(e2);
                }
                try {
                    ((E1.c) aVar.b).p("OpenSecCam", "eventName");
                } catch (E1.b e3) {
                    D0.a.c(e3);
                }
                try {
                    byte[] bArrCopyOfRange = Arrays.copyOfRange(MessageDigest.getInstance("SHA-256").digest(o.O(((com.xiaomi.trustservice.secmedia.d) this.f138c).a, this.b).getBytes()), 0, 16);
                    try {
                        ((E1.c) aVar.b).p(o.B0(bArrCopyOfRange), "CallerPackageName");
                    } catch (E1.b e4) {
                        D0.a.c(e4);
                    }
                    try {
                        cVar3.p(1, "CallTimes");
                    } catch (E1.b e5) {
                        D0.a.c(e5);
                    }
                    MiSight.a(aVar);
                    Slog.d("MiTrustService/MiSecCamService", "finish send event:" + o.B0(bArrCopyOfRange));
                } catch (NoSuchAlgorithmException e6) {
                    Slog.d("MiTrustService/MiSecCamService", "messageDigest error:" + e6);
                }
                break;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
    public b(int i2, String str) {
        this.a = 1;
        this.b = i2;
        this.f138c = str;
    }

    public b(List list, int i2, Throwable th) {
        this.a = 2;
        o.l(list, "initCallbacks cannot be null");
        this.f138c = new ArrayList(list);
        this.b = i2;
    }
}
