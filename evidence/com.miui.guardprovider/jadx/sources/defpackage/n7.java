package defpackage;

import android.os.PowerManager;
import android.util.Log;
import com.miui.guardprovider.aiguard.AiGuardService;
import java.lang.invoke.VarHandle;

/* JADX INFO: loaded from: classes.dex */
public final class n7 extends lr2 implements pp0 {
    public final /* synthetic */ int f;
    public int g;
    public final /* synthetic */ AiGuardService h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n7(int i, j20 j20Var, AiGuardService aiGuardService) {
        super(2, j20Var);
        this.f = i;
        this.h = aiGuardService;
    }

    @Override // defpackage.pp0
    public final Object H(Object obj, Object obj2) {
        int i = this.f;
        c13 c13Var = c13.a;
        g30 g30Var = (g30) obj;
        j20 j20Var = (j20) obj2;
        switch (i) {
            case 0:
                break;
        }
        return ((n7) k(j20Var, g30Var)).o(c13Var);
    }

    @Override // defpackage.sk
    public final j20 k(j20 j20Var, Object obj) {
        int i = this.f;
        AiGuardService aiGuardService = this.h;
        switch (i) {
            case 0:
                return new n7(0, j20Var, aiGuardService);
            default:
                return new n7(1, j20Var, aiGuardService);
        }
    }

    @Override // defpackage.sk
    public final Object o(Object obj) {
        int i = this.f;
        AiGuardService aiGuardService = this.h;
        c13 c13Var = c13.a;
        j20 j20Var = null;
        int i2 = 1;
        switch (i) {
            case 0:
                h30 h30Var = h30.b;
                int i3 = this.g;
                if (i3 != 0) {
                    if (i3 == 1) {
                        kk3.y(obj);
                        return c13Var;
                    }
                    ik.j("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                kk3.y(obj);
                int i4 = AiGuardService.o;
                ar arVar = new ar(new f7((PowerManager) aiGuardService.getSystemService(PowerManager.class), j20Var, i2), lf0.b, -2, bo.b);
                m7 m7Var = new m7(0);
                m7Var.c = aiGuardService;
                VarHandle.storeStoreFence();
                this.g = 1;
                return arVar.a(m7Var, this) == h30Var ? h30Var : c13Var;
            default:
                h30 h30Var2 = h30.b;
                int i5 = this.g;
                if (i5 == 0) {
                    kk3.y(obj);
                    vg3 vg3Var = vd0.b;
                    int i6 = AiGuardService.o;
                    long jD = tn.D(((hx) aiGuardService.k.getValue()).d, xd0.MINUTES);
                    this.g = 1;
                    Object objT = f03.t(f03.L(jD), this);
                    if (objT != h30Var2) {
                        objT = c13Var;
                    }
                    if (objT == h30Var2) {
                        return h30Var2;
                    }
                } else {
                    if (i5 != 1) {
                        ik.j("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    kk3.y(obj);
                }
                Log.i("AiGuardService", "Shutdown because of timeout.");
                aiGuardService.stopSelf();
                return c13Var;
        }
    }
}
