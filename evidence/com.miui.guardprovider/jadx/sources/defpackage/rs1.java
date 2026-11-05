package defpackage;

import android.content.Context;
import java.io.File;
import java.lang.invoke.VarHandle;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public final class rs1 implements aj0 {
    public final /* synthetic */ int a;
    public j62 b;
    public Object c;
    public j62 d;

    /* JADX DEBUG: Marked for inline */
    /* JADX DEBUG: Method not inlined, still used in: [kl0.<init>(android.content.ContextWrapper, java.lang.String, java.util.concurrent.ExecutorService):void] */
    public /* synthetic */ rs1(int i) {
        this.a = i;
    }

    public static vd2 a(os1 os1Var, ew1 ew1Var, ir0 ir0Var) {
        ew1Var.getClass();
        ir0Var.getClass();
        v vVar = new v();
        vVar.p("https://virus-engine.sec.miui.com");
        vVar.c = ew1Var;
        kr0 kr0Var = new kr0();
        kr0Var.a = ir0Var;
        VarHandle.storeStoreFence();
        vVar.m(kr0Var);
        return vVar.r();
    }

    @Override // defpackage.j62
    public final Object get() {
        switch (this.a) {
            case 0:
                return a((os1) this.c, (ew1) ((qs1) this.d).get(), (ir0) this.b.get());
            default:
                Context context = (Context) this.b.get();
                rd2 rd2Var = (rd2) ((r40) this.c).get();
                ir0 ir0Var = (ir0) this.d.get();
                context.getClass();
                ir0Var.getClass();
                ec2 ec2Var = new ec2();
                ec2Var.a = context;
                ec2Var.b = rd2Var;
                ec2Var.c = ir0Var;
                File file = new File(context.getFilesDir(), "upload");
                file.mkdirs();
                ec2Var.d = file;
                ec2Var.e = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
                ec2Var.f = new fr1();
                ec2Var.g = new SecureRandom();
                ec2Var.h = new oz0(0, ec2Var, ec2.class, "isNetworkAvailable", "isNetworkAvailable()Z", 0, 0, 2);
                VarHandle.storeStoreFence();
                return ec2Var;
        }
    }
}
