package defpackage;

import android.content.Context;
import android.content.ContextWrapper;
import com.miui.guardprovider.sdk.android.db.EngineDatabase;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.ObjIntConsumer;

/* JADX INFO: loaded from: classes.dex */
public final class kl0 {
    public static volatile kl0 g;
    public static final AtomicBoolean h = new AtomicBoolean(false);
    public final Context a;
    public final a30 b;
    public final f20 c;
    public kn2 d;
    public kn2 e;
    public final h40 f;

    public kl0(ContextWrapper contextWrapper, String str, ExecutorService executorService) {
        contextWrapper.getClass();
        this.a = contextWrapper;
        if (uw0.b == null) {
            synchronized (uw0.class) {
                if (uw0.b == null) {
                    uw0.b = str;
                }
            }
        }
        a30 a30VarN = executorService != null ? al2.n(executorService) : xc0.a;
        this.b = a30VarN;
        qq2 qq2VarC = cb3.c();
        a30VarN.getClass();
        this.c = az0.a(w10.B(a30VarN, qq2VarC));
        f00 f00Var = new f00();
        f00Var.a = contextWrapper;
        VarHandle.storeStoreFence();
        vg3 vg3Var = new vg3(12);
        os1 os1Var = new os1();
        h40 h40Var = new h40();
        h40Var.a = os1Var;
        h40Var.b = vg3Var;
        int i = 1;
        r40 r40Var = new r40(i);
        r40Var.b = f00Var;
        VarHandle.storeStoreFence();
        h40Var.c = hd0.a(r40Var);
        zy zyVar = new zy(i);
        VarHandle.storeStoreFence();
        h40Var.d = hd0.a(zyVar);
        int i2 = 0;
        zy zyVar2 = new zy(i2);
        VarHandle.storeStoreFence();
        h40Var.e = hd0.a(zyVar2);
        int i3 = 3;
        zy zyVar3 = new zy(i3);
        VarHandle.storeStoreFence();
        h40Var.f = hd0.a(zyVar3);
        h40Var.g = hd0.a(q62.c);
        h40Var.h = hd0.a(n61.a);
        h40Var.i = hd0.a(tn.b);
        j62 j62VarA = hd0.a(bi3.c);
        j62 j62Var = h40Var.g;
        j62 j62Var2 = h40Var.h;
        j62 j62Var3 = h40Var.i;
        ps1 ps1Var = new ps1();
        ps1Var.a = j62Var;
        ps1Var.b = j62Var2;
        ps1Var.c = j62Var3;
        ps1Var.d = j62VarA;
        VarHandle.storeStoreFence();
        j62 j62VarA2 = hd0.a(ps1Var);
        h40Var.j = j62VarA2;
        x8 x8Var = new x8(i2);
        x8Var.b = j62VarA2;
        VarHandle.storeStoreFence();
        j62 j62VarA3 = hd0.a(x8Var);
        j62 j62Var4 = h40Var.d;
        j62 j62Var5 = h40Var.e;
        j62 j62Var6 = h40Var.f;
        p40 p40Var = new p40(i2);
        p40Var.b = j62Var4;
        p40Var.c = j62Var5;
        p40Var.d = j62Var6;
        p40Var.e = j62VarA3;
        VarHandle.storeStoreFence();
        h40Var.k = hd0.a(p40Var);
        int i4 = ck2.c;
        List list = Collections.EMPTY_LIST;
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(h40Var.k);
        ck2 ck2Var = new ck2();
        ck2Var.a = list;
        ck2Var.b = arrayList;
        VarHandle.storeStoreFence();
        int i5 = 2;
        r40 r40Var2 = new r40(i5);
        r40Var2.b = ck2Var;
        VarHandle.storeStoreFence();
        j62 j62VarA4 = hd0.a(r40Var2);
        j62 j62Var7 = h40Var.c;
        p6 p6Var = new p6(i);
        p6Var.b = j62Var7;
        p6Var.c = j62VarA4;
        VarHandle.storeStoreFence();
        j62 j62VarA5 = hd0.a(p6Var);
        h40Var.l = j62VarA5;
        o40 o40Var = new o40(i);
        o40Var.b = j62VarA5;
        VarHandle.storeStoreFence();
        h40Var.m = o40Var;
        x8 x8Var2 = new x8(i);
        x8Var2.b = j62VarA5;
        VarHandle.storeStoreFence();
        h40Var.n = x8Var2;
        p6 p6Var2 = new p6(i2);
        p6Var2.b = o40Var;
        p6Var2.c = x8Var2;
        VarHandle.storeStoreFence();
        r40 r40Var3 = new r40(i2);
        r40Var3.b = p6Var2;
        VarHandle.storeStoreFence();
        h40Var.o = r40Var3;
        int i6 = 4;
        x8 x8Var3 = new x8(i6);
        x8Var3.b = j62VarA5;
        VarHandle.storeStoreFence();
        r40 r40Var4 = new r40(i6);
        r40Var4.b = x8Var3;
        VarHandle.storeStoreFence();
        j62 j62VarA6 = hd0.a(r40Var4);
        h40Var.p = j62VarA6;
        r40 r40Var5 = h40Var.o;
        mx mxVar = new mx(i2);
        mxVar.b = r40Var5;
        mxVar.c = j62VarA6;
        VarHandle.storeStoreFence();
        h40Var.q = hd0.a(mxVar);
        zy zyVar4 = new zy(i6);
        VarHandle.storeStoreFence();
        t20 t20Var = new t20(i2);
        VarHandle.storeStoreFence();
        sy0 sy0Var = new sy0(i2);
        sy0Var.b = t20Var;
        VarHandle.storeStoreFence();
        zy zyVar5 = new zy(i5);
        VarHandle.storeStoreFence();
        qy0 qy0Var = new qy0(i2);
        qy0Var.b = zyVar5;
        VarHandle.storeStoreFence();
        sy0 sy0Var2 = new sy0(i);
        sy0Var2.b = t20Var;
        VarHandle.storeStoreFence();
        qy0 qy0Var2 = new qy0(i);
        qy0Var2.b = zyVar5;
        VarHandle.storeStoreFence();
        j62 j62Var8 = h40Var.j;
        mx mxVar2 = new mx(i);
        mxVar2.b = j62Var8;
        mxVar2.c = zyVar5;
        VarHandle.storeStoreFence();
        qs1 qs1Var = new qs1();
        qs1Var.a = os1Var;
        qs1Var.b = zyVar4;
        qs1Var.c = sy0Var;
        qs1Var.d = qy0Var;
        qs1Var.e = sy0Var2;
        qs1Var.f = qy0Var2;
        qs1Var.g = mxVar2;
        VarHandle.storeStoreFence();
        rs1 rs1Var = new rs1(i2);
        rs1Var.c = os1Var;
        rs1Var.d = qs1Var;
        rs1Var.b = j62Var8;
        VarHandle.storeStoreFence();
        h40Var.r = rs1Var;
        r40 r40Var6 = new r40(i3);
        r40Var6.b = rs1Var;
        VarHandle.storeStoreFence();
        j62 j62Var9 = h40Var.c;
        rs1 rs1Var2 = new rs1(i);
        rs1Var2.b = j62Var9;
        rs1Var2.c = r40Var6;
        rs1Var2.d = j62Var8;
        VarHandle.storeStoreFence();
        h40Var.s = hd0.a(rs1Var2);
        rs1 rs1Var3 = h40Var.r;
        o40 o40Var2 = new o40(6);
        o40Var2.b = rs1Var3;
        VarHandle.storeStoreFence();
        h40Var.t = o40Var2;
        j62 j62Var10 = h40Var.l;
        o40 o40Var3 = new o40(i3);
        o40Var3.b = j62Var10;
        VarHandle.storeStoreFence();
        j62 j62VarA7 = hd0.a(o40Var3);
        o40 o40Var4 = h40Var.t;
        o40 o40Var5 = h40Var.m;
        j62 j62Var11 = h40Var.p;
        en enVar = new en(i5);
        enVar.b = o40Var4;
        enVar.c = o40Var5;
        enVar.d = j62Var11;
        enVar.e = j62VarA7;
        VarHandle.storeStoreFence();
        rs1 rs1Var4 = h40Var.r;
        int i7 = 7;
        o40 o40Var6 = new o40(i7);
        o40Var6.b = rs1Var4;
        VarHandle.storeStoreFence();
        j62 j62Var12 = h40Var.l;
        x8 x8Var4 = new x8(i5);
        x8Var4.b = j62Var12;
        VarHandle.storeStoreFence();
        p40 p40Var2 = new p40(i);
        p40Var2.d = o40Var6;
        p40Var2.e = x8Var4;
        p40Var2.b = j62Var11;
        p40Var2.c = j62VarA7;
        VarHandle.storeStoreFence();
        x8 x8Var5 = new x8(i7);
        x8Var5.b = rs1Var4;
        VarHandle.storeStoreFence();
        o40 o40Var7 = new o40(i5);
        o40Var7.b = j62Var12;
        VarHandle.storeStoreFence();
        x8 x8Var6 = new x8(i3);
        x8Var6.b = j62Var12;
        VarHandle.storeStoreFence();
        ux0 ux0Var = new ux0();
        ux0Var.a = x8Var5;
        ux0Var.b = o40Var7;
        ux0Var.c = x8Var6;
        ux0Var.d = j62Var11;
        ux0Var.e = j62VarA7;
        VarHandle.storeStoreFence();
        int i8 = 5;
        x8 x8Var7 = new x8(i8);
        x8Var7.b = rs1Var4;
        VarHandle.storeStoreFence();
        x8 x8Var8 = h40Var.n;
        en enVar2 = new en(i);
        enVar2.b = x8Var8;
        enVar2.d = x8Var7;
        enVar2.c = j62Var11;
        enVar2.e = j62VarA7;
        VarHandle.storeStoreFence();
        o40 o40Var8 = new o40(i2);
        o40Var8.b = j62Var12;
        VarHandle.storeStoreFence();
        o40 o40Var9 = new o40(i8);
        o40Var9.b = rs1Var4;
        VarHandle.storeStoreFence();
        en enVar3 = new en(i2);
        enVar3.d = o40Var8;
        enVar3.e = o40Var9;
        enVar3.b = j62Var11;
        enVar3.c = j62VarA7;
        VarHandle.storeStoreFence();
        x8 x8Var9 = new x8(9);
        x8Var9.b = rs1Var4;
        VarHandle.storeStoreFence();
        o40 o40Var10 = new o40(i6);
        o40Var10.b = j62Var12;
        VarHandle.storeStoreFence();
        p40 p40Var3 = new p40(2);
        p40Var3.d = x8Var9;
        p40Var3.e = o40Var10;
        p40Var3.b = j62Var11;
        p40Var3.c = j62VarA7;
        VarHandle.storeStoreFence();
        q40 q40Var = new q40();
        q40Var.a = enVar;
        q40Var.b = p40Var2;
        q40Var.c = ux0Var;
        q40Var.d = enVar2;
        q40Var.e = enVar3;
        q40Var.f = p40Var3;
        VarHandle.storeStoreFence();
        h40Var.u = hd0.a(q40Var);
        VarHandle.storeStoreFence();
        this.f = h40Var;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x001f  */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0286, code lost:
    
        if (defpackage.c13.a == r7) goto L38;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object a(kl0 kl0Var, ArrayList arrayList, f7 f7Var, al0 al0Var, bl0 bl0Var, bl0 bl0Var2, cl0 cl0Var, int i, k20 k20Var) {
        el0 el0Var;
        gh2 gh2Var;
        int i2;
        h40 h40Var = kl0Var.f;
        if (k20Var instanceof el0) {
            el0Var = (el0) k20Var;
            int i3 = el0Var.i;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                el0Var.i = i3 - Integer.MIN_VALUE;
            } else {
                el0Var = new el0(kl0Var, k20Var);
            }
        } else {
            el0Var = new el0(kl0Var, k20Var);
        }
        Object obj = el0Var.g;
        h30 h30Var = h30.b;
        int i4 = el0Var.i;
        int i5 = 2;
        j20 j20Var = null;
        if (i4 == 0) {
            kk3.y(obj);
            vg3 vg3Var = h40Var.b;
            EngineDatabase engineDatabase = (EngineDatabase) h40Var.l.get();
            engineDatabase.getClass();
            xx0 xx0VarE = engineDatabase.E();
            uk3.h(xx0VarE);
            EngineDatabase engineDatabase2 = (EngineDatabase) h40Var.l.get();
            engineDatabase2.getClass();
            wv2 wv2VarB = engineDatabase2.B();
            uk3.h(wv2VarB);
            w63 w63Var = new w63();
            c9 c9Var = new c9();
            c9Var.a = xx0VarE;
            c9Var.b = wv2VarB;
            c9Var.c = w63Var;
            VarHandle.storeStoreFence();
            AtomicInteger atomicInteger = new AtomicInteger();
            AtomicInteger atomicInteger2 = new AtomicInteger();
            c9Var.d = new fl0(bl0Var2, atomicInteger, j20Var, 0);
            Context context = kl0Var.a;
            context.getClass();
            f00 f00Var = new f00();
            f00Var.a = context;
            VarHandle.storeStoreFence();
            t7 t7Var = new t7(bl0Var, atomicInteger2, j20Var, i5);
            gl0 gl0Var = new gl0(al0Var, atomicInteger, null);
            arrayList.getClass();
            vt vtVar = new vt(new e00(f00Var, arrayList, t7Var, gl0Var, null), lf0.b, -2, bo.b, 0);
            w60 w60Var = xc0.a;
            o60 o60Var = o60.d;
            cm0 cm0VarT = bi3.t(vtVar);
            lx lxVar = (lx) h40Var.q.get();
            Object objB = h40Var.a().b(bx.class);
            objB.getClass();
            EngineDatabase engineDatabase3 = (EngineDatabase) h40Var.l.get();
            engineDatabase3.getClass();
            x03 x03VarG = engineDatabase3.G();
            uk3.h(x03VarG);
            fx fxVar = new fx();
            fxVar.a = (bx) objB;
            fxVar.b = x03VarG;
            fxVar.c = 3;
            dx dxVar = new dx();
            dxVar.d = fxVar;
            VarHandle.storeStoreFence();
            fxVar.e = dxVar;
            VarHandle.storeStoreFence();
            EngineDatabase engineDatabase4 = (EngineDatabase) h40Var.l.get();
            engineDatabase4.getClass();
            wr0 wr0VarD = engineDatabase4.D();
            uk3.h(wr0VarD);
            EngineDatabase engineDatabase5 = (EngineDatabase) h40Var.l.get();
            engineDatabase5.getClass();
            om omVarZ = engineDatabase5.z();
            uk3.h(omVarZ);
            w63 w63Var2 = new w63();
            hm1 hm1Var = new hm1();
            hm1Var.a = wr0VarD;
            hm1Var.b = omVarZ;
            hm1Var.c = w63Var2;
            em1 em1Var = new em1();
            em1Var.c = hm1Var;
            VarHandle.storeStoreFence();
            hm1Var.d = em1Var;
            VarHandle.storeStoreFence();
            c43 c43Var = (c43) h40Var.p.get();
            EngineDatabase engineDatabase6 = (EngineDatabase) h40Var.l.get();
            engineDatabase6.getClass();
            sg2 sg2VarF = engineDatabase6.F();
            uk3.h(sg2VarF);
            c43Var.getClass();
            fq fqVar = new fq();
            fqVar.a = c43Var;
            fqVar.b = sg2VarF;
            VarHandle.storeStoreFence();
            Object objB2 = h40Var.a().b(vx0.class);
            objB2.getClass();
            c43 c43Var2 = (c43) h40Var.p.get();
            ec2 ec2Var = (ec2) h40Var.s.get();
            Context context2 = (Context) h40Var.c.get();
            lxVar.getClass();
            c43Var2.getClass();
            ec2Var.getClass();
            context2.getClass();
            w82 w82Var = new w82();
            w82Var.a = lxVar;
            w82Var.b = fxVar;
            w82Var.c = hm1Var;
            w82Var.d = fqVar;
            w82Var.e = (vx0) objB2;
            w82Var.f = c43Var2;
            w82Var.g = ec2Var;
            w82Var.h = context2;
            VarHandle.storeStoreFence();
            ArrayList arrayList2 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            HashMap map = new HashMap();
            map.put("scan_params", Integer.valueOf(i));
            map.put("virus_scanning", Boolean.valueOf((i & 3) != 0));
            map.put("black_gray_scanning", Boolean.valueOf((i & 4) != 0));
            map.put("flamingo_dispatcher", kl0Var.b);
            linkedHashMap.putAll(map);
            int size = arrayList.size();
            r7 r7Var = new r7(f7Var, null);
            hl0 hl0Var = new hl0(bl0Var2, null);
            w7 w7Var = new w7(cl0Var, null, 1);
            u22 u22Var = new u22();
            u22Var.a = size;
            u22Var.b = r7Var;
            u22Var.c = hl0Var;
            u22Var.d = w7Var;
            VarHandle.storeStoreFence();
            arrayList2.add(u22Var);
            arrayList2.add(c9Var);
            gh2Var = new gh2(arrayList2, cm0VarT, linkedHashMap, w82Var, 0);
            x20 x20Var = el0Var.c;
            x20Var.getClass();
            q21 q21Var = (q21) x20Var.B(f70.g);
            if (!(q21Var != null ? q21Var.a() : true) && cl0Var != null) {
                Object cancellationException = new CancellationException();
                el0Var.e = gh2Var;
                el0Var.f = i;
                el0Var.i = 1;
                cl0Var.H(cancellationException, el0Var);
            }
            i2 = i;
        } else {
            if (i4 != 1) {
                if (i4 == 2) {
                    kk3.y(obj);
                    return obj;
                }
                ik.j("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            i2 = el0Var.f;
            gh2Var = el0Var.e;
            kk3.y(obj);
        }
        el0Var.e = null;
        el0Var.f = i2;
        el0Var.i = 2;
        Object objC = gh2Var.c(gh2Var.b, el0Var);
        return objC == h30Var ? h30Var : objC;
    }

    public static void b(kl0 kl0Var, ArrayList arrayList, qp0 qp0Var, yo1 yo1Var, jp1 jp1Var, ObjIntConsumer objIntConsumer, Consumer consumer, int i, int i2) {
        yo1 yo1Var2 = (i2 & 4) != 0 ? null : yo1Var;
        jp1 jp1Var2 = (i2 & 8) != 0 ? null : jp1Var;
        xk0 xk0Var = new xk0();
        kl0Var.getClass();
        kn2 kn2Var = kl0Var.e;
        if (kn2Var != null) {
            gd3.o(kn2Var, "Cancel the previous.");
        }
        kl0Var.e = uk3.z(kl0Var.c, null, null, new dl0(kl0Var, arrayList, i, xk0Var, yo1Var2, qp0Var, jp1Var2, objIntConsumer, consumer, null), 3);
    }

    public static void c(kl0 kl0Var, IntConsumer intConsumer, np1 np1Var, int i) {
        kg kgVar = new kg(1);
        np1 np1Var2 = (i & 4) != 0 ? null : np1Var;
        kl0Var.getClass();
        kn2 kn2Var = kl0Var.d;
        if (kn2Var != null) {
            gd3.o(kn2Var, "Cancel the previous.");
        }
        kl0Var.d = uk3.z(kl0Var.c, null, null, new jl0(kl0Var, kgVar, np1Var2, intConsumer, null), 3);
    }
}
