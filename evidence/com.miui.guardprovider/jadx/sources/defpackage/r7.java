package defpackage;

import com.miui.guardprovider.aiguard.AiGuardService;

/* JADX INFO: loaded from: classes.dex */
public final class r7 extends lr2 implements pp0 {
    public final /* synthetic */ int f = 0;
    public int g;
    public /* synthetic */ int h;
    public final /* synthetic */ Object i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r7(int i, j20 j20Var, AiGuardService aiGuardService) {
        super(2, j20Var);
        this.i = aiGuardService;
        this.h = i;
    }

    @Override // defpackage.pp0
    public final Object H(Object obj, Object obj2) {
        int i = this.f;
        c13 c13Var = c13.a;
        switch (i) {
            case 0:
                return ((r7) k((j20) obj2, (g30) obj)).o(c13Var);
            default:
                return ((r7) k((j20) obj2, Integer.valueOf(((Number) obj).intValue()))).o(c13Var);
        }
    }

    @Override // defpackage.sk
    public final j20 k(j20 j20Var, Object obj) {
        int i = this.f;
        Object obj2 = this.i;
        switch (i) {
            case 0:
                return new r7(this.h, j20Var, (AiGuardService) obj2);
            default:
                r7 r7Var = new r7((pp0) obj2, j20Var);
                r7Var.h = ((Number) obj).intValue();
                return r7Var;
        }
    }

    @Override // defpackage.sk
    public final Object o(Object obj) {
        int i = this.f;
        c13 c13Var = c13.a;
        Object obj2 = this.i;
        switch (i) {
            case 0:
                h30 h30Var = h30.b;
                int i2 = this.g;
                if (i2 == 0) {
                    kk3.y(obj);
                    int i3 = this.h;
                    this.g = 1;
                    return AiGuardService.c((AiGuardService) obj2, i3, this) == h30Var ? h30Var : c13Var;
                }
                if (i2 == 1) {
                    kk3.y(obj);
                    return c13Var;
                }
                ik.j("call to 'resume' before 'invoke' with coroutine");
                return null;
            default:
                int i4 = this.h;
                h30 h30Var2 = h30.b;
                int i5 = this.g;
                if (i5 != 0) {
                    if (i5 == 1) {
                        kk3.y(obj);
                        return c13Var;
                    }
                    ik.j("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                kk3.y(obj);
                pp0 pp0Var = (pp0) obj2;
                if (pp0Var == null) {
                    return c13Var;
                }
                Integer num = new Integer(i4);
                this.h = i4;
                this.g = 1;
                return pp0Var.H(num, this) == h30Var2 ? h30Var2 : c13Var;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r7(pp0 pp0Var, j20 j20Var) {
        super(2, j20Var);
        this.i = pp0Var;
    }
}
