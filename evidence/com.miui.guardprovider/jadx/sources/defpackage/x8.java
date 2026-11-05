package defpackage;

import com.miui.guardprovider.sdk.android.db.EngineDatabase;
import java.lang.invoke.VarHandle;

/* JADX INFO: loaded from: classes.dex */
public final class x8 implements aj0 {
    public final /* synthetic */ int a;
    public j62 b;

    /* JADX DEBUG: Marked for inline */
    /* JADX DEBUG: Method not inlined, still used in: [d8.onClick(android.content.DialogInterface, int):void, kl0.<init>(android.content.ContextWrapper, java.lang.String, java.util.concurrent.ExecutorService):void] */
    public /* synthetic */ x8(int i) {
        this.a = i;
    }

    @Override // defpackage.j62
    public final Object get() {
        switch (this.a) {
            case 0:
                w63 w63Var = new w63();
                ir0 ir0Var = (ir0) this.b.get();
                ir0Var.getClass();
                w8 w8Var = new w8();
                w8Var.a = w63Var;
                w8Var.b = ir0Var;
                VarHandle.storeStoreFence();
                return w8Var;
            case 1:
                EngineDatabase engineDatabase = (EngineDatabase) this.b.get();
                engineDatabase.getClass();
                vm vmVarA = engineDatabase.A();
                uk3.h(vmVarA);
                return vmVarA;
            case 2:
                EngineDatabase engineDatabase2 = (EngineDatabase) this.b.get();
                engineDatabase2.getClass();
                wr0 wr0VarD = engineDatabase2.D();
                uk3.h(wr0VarD);
                return wr0VarD;
            case 3:
                EngineDatabase engineDatabase3 = (EngineDatabase) this.b.get();
                engineDatabase3.getClass();
                wv2 wv2VarB = engineDatabase3.B();
                uk3.h(wv2VarB);
                return wv2VarB;
            case 4:
                EngineDatabase engineDatabase4 = (EngineDatabase) this.b.get();
                engineDatabase4.getClass();
                u33 u33VarH = engineDatabase4.H();
                uk3.h(u33VarH);
                return u33VarH;
            case 5:
                vd2 vd2Var = (vd2) this.b.get();
                vd2Var.getClass();
                Object objB = vd2Var.b(xm.class);
                objB.getClass();
                return (xm) objB;
            case 6:
                vd2 vd2Var2 = (vd2) this.b.get();
                vd2Var2.getClass();
                Object objB2 = vd2Var2.b(oj0.class);
                objB2.getClass();
                return (oj0) objB2;
            case 7:
                vd2 vd2Var3 = (vd2) this.b.get();
                vd2Var3.getClass();
                Object objB3 = vd2Var3.b(vx0.class);
                objB3.getClass();
                return (vx0) objB3;
            case 8:
                ew1 ew1Var = (ew1) this.b.get();
                ew1Var.getClass();
                v vVar = new v();
                vVar.p("https://detection-aigc.engine.miui.com");
                vVar.c = ew1Var;
                vVar.m(kr0.c());
                return vVar.r();
            default:
                vd2 vd2Var4 = (vd2) this.b.get();
                vd2Var4.getClass();
                Object objB4 = vd2Var4.b(v03.class);
                objB4.getClass();
                return (v03) objB4;
        }
    }
}
