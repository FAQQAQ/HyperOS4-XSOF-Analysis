package defpackage;

import android.util.Log;
import com.miui.guardprovider.aiguard.AiGuardService;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class w7 extends lr2 implements pp0 {
    public final /* synthetic */ int f;
    public int g;
    public /* synthetic */ Object h;
    public final /* synthetic */ Object i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ w7(Object obj, Object obj2, j20 j20Var, int i) {
        super(2, j20Var);
        this.f = i;
        this.h = obj;
        this.i = obj2;
    }

    @Override // defpackage.pp0
    public final Object H(Object obj, Object obj2) {
        int i = this.f;
        c13 c13Var = c13.a;
        switch (i) {
            case 0:
                return ((w7) k((j20) obj2, (g30) obj)).o(c13Var);
            case 1:
                return ((w7) k((j20) obj2, (Throwable) obj)).o(c13Var);
            case 2:
                return ((w7) k((j20) obj2, (g30) obj)).o(c13Var);
            case 3:
                return ((w7) k((j20) obj2, (lz1) obj)).o(c13Var);
            default:
                return ((w7) k((j20) obj2, (g30) obj)).o(c13Var);
        }
    }

    @Override // defpackage.sk
    public final j20 k(j20 j20Var, Object obj) {
        int i = this.f;
        Object obj2 = this.i;
        switch (i) {
            case 0:
                return new w7((AiGuardService) this.h, (Path) obj2, j20Var, 0);
            case 1:
                w7 w7Var = new w7((pp0) obj2, j20Var, 1);
                w7Var.h = obj;
                return w7Var;
            case 2:
                return new w7((pp0) this.h, (qz1) obj2, j20Var, 2);
            case 3:
                w7 w7Var2 = new w7((tv2) obj2, j20Var, 3);
                w7Var2.h = obj;
                return w7Var2;
            default:
                return new w7((tv2) this.h, (np0) obj2, j20Var, 4);
        }
    }

    @Override // defpackage.sk
    public final Object o(Object obj) throws IOException {
        int i = this.f;
        c13 c13Var = c13.a;
        Object obj2 = this.i;
        switch (i) {
            case 0:
                AiGuardService aiGuardService = (AiGuardService) this.h;
                Path path = (Path) obj2;
                h30 h30Var = h30.b;
                int i2 = this.g;
                boolean zBooleanValue = false;
                try {
                    if (i2 == 0) {
                        kk3.y(obj);
                        this.g = 1;
                        obj = aiGuardService.e(path, this);
                        if (obj == h30Var) {
                            return h30Var;
                        }
                    } else {
                        if (i2 != 1) {
                            ik.j("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        kk3.y(obj);
                    }
                    if (((Boolean) obj).booleanValue()) {
                        int i3 = AiGuardService.o;
                        Files.copy(path, aiGuardService.f().c, (CopyOption[]) Arrays.copyOf(new CopyOption[]{StandardCopyOption.REPLACE_EXISTING}, 1)).getClass();
                    }
                    zBooleanValue = ((Boolean) obj).booleanValue();
                    break;
                } catch (IOException e) {
                    Log.e("AiGuardService", "Request failed: ", e);
                } finally {
                    Files.delete(path);
                }
                return Boolean.valueOf(zBooleanValue);
            case 1:
                Throwable th = (Throwable) this.h;
                h30 h30Var2 = h30.b;
                int i4 = this.g;
                if (i4 != 0) {
                    if (i4 == 1) {
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
                this.h = null;
                this.g = 1;
                return pp0Var.H(th, this) == h30Var2 ? h30Var2 : c13Var;
            case 2:
                h30 h30Var3 = h30.b;
                int i5 = this.g;
                if (i5 == 0) {
                    kk3.y(obj);
                    this.g = 1;
                    Object objH = ((pp0) this.h).H((qz1) obj2, this);
                    return objH == h30Var3 ? h30Var3 : objH;
                }
                if (i5 == 1) {
                    kk3.y(obj);
                    return obj;
                }
                ik.j("call to 'resume' before 'invoke' with coroutine");
                return null;
            case 3:
                h30 h30Var4 = h30.b;
                int i6 = this.g;
                if (i6 != 0) {
                    if (i6 == 1) {
                        kk3.y(obj);
                        return obj;
                    }
                    ik.j("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                kk3.y(obj);
                lz1 lz1Var = (lz1) this.h;
                this.g = 1;
                Object objA = tv2.a((tv2) obj2, lz1Var, this);
                return objA == h30Var4 ? h30Var4 : objA;
            default:
                np0 np0Var = (np0) obj2;
                h30 h30Var5 = h30.b;
                int i7 = this.g;
                try {
                    if (i7 == 0) {
                        kk3.y(obj);
                        tv2 tv2Var = (tv2) this.h;
                        this.g = 1;
                        obj = tv2.b(tv2Var, this);
                        if (obj == h30Var5) {
                            return h30Var5;
                        }
                    } else {
                        if (i7 != 1) {
                            ik.j("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        kk3.y(obj);
                    }
                    np0Var.e();
                    return c13Var;
                } catch (Throwable th2) {
                    np0Var.e();
                    throw th2;
                }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ w7(Object obj, j20 j20Var, int i) {
        super(2, j20Var);
        this.f = i;
        this.i = obj;
    }
}
