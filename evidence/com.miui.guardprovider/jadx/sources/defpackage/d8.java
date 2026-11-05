package defpackage;

import android.content.Context;
import android.content.DialogInterface;
import com.miui.guardprovider.action.impl.AlertActivity;
import com.xiaomi.onetrack.api.al;
import java.io.IOException;
import java.lang.invoke.VarHandle;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class d8 implements DialogInterface.OnClickListener {
    public final /* synthetic */ int b;
    public /* synthetic */ jc0 c;

    /* JADX DEBUG: Marked for inline */
    /* JADX DEBUG: Method not inlined, still used in: [com.miui.guardprovider.action.impl.AlertActivity.a.w(android.os.Bundle):android.app.Dialog, com.miui.guardprovider.aiguard.AlertActivity.b.<init>():void] */
    public /* synthetic */ d8(int i) {
        this.b = i;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        int i2 = this.b;
        jc0 jc0Var = this.c;
        switch (i2) {
            case 0:
                AlertActivity.a aVar = (AlertActivity.a) jc0Var;
                aVar.j0 = i;
                aVar.v(false, false);
                break;
            case 1:
                AlertActivity.a aVar2 = (AlertActivity.a) jc0Var;
                aVar2.j0 = i;
                aVar2.v(false, false);
                break;
            default:
                com.miui.guardprovider.aiguard.AlertActivity.b bVar = (com.miui.guardprovider.aiguard.AlertActivity.b) jc0Var;
                com.miui.guardprovider.aiguard.AlertActivity alertActivity = (com.miui.guardprovider.aiguard.AlertActivity) bVar.q();
                if (i == -2) {
                    ((wu2) bVar.j0).a.track("click", xl1.G(new qy1[]{new qy1(al.ae, "1127.1.0.1.45358"), new qy1("click_content", "取消"), new qy1("trigger_scenario", alertActivity.F)}));
                    pj0 pj0VarP = n53.p(alertActivity);
                    try {
                        Files.deleteIfExists(pj0VarP.a);
                        Files.deleteIfExists(pj0VarP.c);
                        break;
                    } catch (IOException unused) {
                    }
                } else if (i == -1) {
                    ((wu2) bVar.j0).a.track("click", xl1.G(new qy1[]{new qy1(al.ae, "1127.1.0.1.45358"), new qy1("click_content", "标记为误报"), new qy1("trigger_scenario", alertActivity.F)}));
                    bVar.k0 = new ro(bVar.q());
                    boolean z = bVar.l0;
                    t20 t20Var = new t20(1);
                    VarHandle.storeStoreFence();
                    j62 j62VarA = hd0.a(t20Var);
                    x8 x8Var = new x8(8);
                    x8Var.b = j62VarA;
                    VarHandle.storeStoreFence();
                    j62 j62VarA2 = hd0.a(x8Var);
                    x8 x8Var2 = new x8(6);
                    x8Var2.b = j62VarA2;
                    VarHandle.storeStoreFence();
                    oj0 oj0Var = (oj0) hd0.a(x8Var2).get();
                    ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
                    Context applicationContext = bVar.q().getApplicationContext();
                    pj0 pj0VarP2 = n53.p(applicationContext);
                    e8 e8Var = new e8();
                    e8Var.b = bVar;
                    e8Var.c = z;
                    e8Var.d = pj0VarP2;
                    e8Var.e = oj0Var;
                    e8Var.f = applicationContext;
                    VarHandle.storeStoreFence();
                    executorServiceNewSingleThreadExecutor.execute(e8Var);
                }
                alertActivity.finish();
                break;
        }
    }
}
