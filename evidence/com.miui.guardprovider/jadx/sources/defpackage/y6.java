package defpackage;

import android.database.sqlite.SQLiteTransactionListener;
import android.os.CancellationSignal;
import com.miui.guardprovider.aiguard.AiGuardService;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class y6 implements np0 {
    public final /* synthetic */ int b;

    /* JADX DEBUG: Marked for inline */
    /* JADX DEBUG: Method not inlined, still used in: [com.miui.guardprovider.aiguard.AiGuardService.<init>():void, cp0.<clinit>():void] */
    public /* synthetic */ y6(int i) {
        this.b = i;
    }

    @Override // defpackage.np0
    public final Object e() {
        Class<?> returnType;
        switch (this.b) {
            case 0:
                int i = AiGuardService.o;
                g40 g40Var = new g40();
                t20 t20Var = new t20(1);
                VarHandle.storeStoreFence();
                j62 j62VarA = hd0.a(t20Var);
                x8 x8Var = new x8(8);
                x8Var.b = j62VarA;
                VarHandle.storeStoreFence();
                j62 j62VarA2 = hd0.a(x8Var);
                g40Var.a = j62VarA2;
                o40 o40Var = new o40(8);
                o40Var.b = j62VarA2;
                VarHandle.storeStoreFence();
                g40Var.b = hd0.a(o40Var);
                j62 j62Var = g40Var.a;
                o40 o40Var2 = new o40(9);
                o40Var2.b = j62Var;
                VarHandle.storeStoreFence();
                g40Var.c = hd0.a(o40Var2);
                return g40Var;
            default:
                try {
                    String[] strArr = cp0.c;
                    Method method = (Method) cp0.e.getValue();
                    if (method != null && (returnType = method.getReturnType()) != null) {
                        Class cls = Integer.TYPE;
                        return returnType.getDeclaredMethod("beginTransaction", cls, SQLiteTransactionListener.class, cls, CancellationSignal.class);
                    }
                } catch (Throwable unused) {
                }
                return null;
        }
    }
}
