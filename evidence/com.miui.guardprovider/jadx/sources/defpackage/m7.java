package defpackage;

import android.util.Log;
import com.miui.guardprovider.aiguard.AiGuardService;

/* JADX INFO: loaded from: classes.dex */
public final class m7 implements dm0 {
    public final /* synthetic */ int b;
    public /* synthetic */ Object c;

    /* JADX WARN: Code duplicated, block: B:9:0x001a  */
    @Override // defpackage.dm0
    public final Object i(Object obj, j20 j20Var) {
        uy uyVar;
        int i = this.b;
        c13 c13Var = c13.a;
        switch (i) {
            case 0:
                int iIntValue = ((Number) obj).intValue();
                Log.i("AiGuardService", "ThermalState: " + iIntValue);
                if (iIntValue >= 3) {
                    Log.i("AiGuardService", "Stop service caused by thermal");
                    ((AiGuardService) this.c).stopSelf();
                }
                return c13Var;
            default:
                if (j20Var instanceof uy) {
                    uyVar = (uy) j20Var;
                    int i2 = uyVar.g;
                    if ((i2 & Integer.MIN_VALUE) != 0) {
                        uyVar.g = i2 - Integer.MIN_VALUE;
                    } else {
                        uyVar = new uy(this, j20Var);
                    }
                } else {
                    uyVar = new uy(this, j20Var);
                }
                Object obj2 = uyVar.e;
                h30 h30Var = h30.b;
                int i3 = uyVar.g;
                if (i3 != 0) {
                    if (i3 == 1) {
                        kk3.y(obj2);
                        return c13Var;
                    }
                    ik.j("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                kk3.y(obj2);
                m22 m22Var = (m22) this.c;
                m22Var.getClass();
                if (obj == null) {
                    obj = vh3.c;
                }
                uyVar.g = 1;
                return m22Var.g.j(uyVar, obj) == h30Var ? h30Var : c13Var;
        }
    }
}
