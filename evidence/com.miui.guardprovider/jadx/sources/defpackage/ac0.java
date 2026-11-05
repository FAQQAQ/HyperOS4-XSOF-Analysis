package defpackage;

/* JADX INFO: loaded from: classes.dex */
public abstract class ac0 {
    public static final bc0 a;

    static {
        dw1 dw1Var = new dw1();
        dw1Var.a(15L);
        dw1Var.b(15L);
        dw1Var.c.add(new v62());
        ew1 ew1Var = new ew1(dw1Var);
        v vVar = new v();
        vVar.p("https://push-service.sec.miui.com");
        vVar.c = ew1Var;
        vVar.m(kr0.c());
        a = (bc0) vVar.r().b(bc0.class);
    }
}
