package ho;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.miui.common.utils.GsonUtils;
import com.xiaomi.security.xsof.base.ScoreConfigBean;
import com.xiaomi.security.xsof.frauddetection.bean.CurrentFraudMetadata;
import com.xiaomi.security.xsof.frauddetection.bean.DurationFraudData;
import com.xiaomi.security.xsof.frauddetection.bean.FraudDetectionScore;
import com.xiaomi.security.xsof.frauddetection.bean.PastFraudData;
import io.m;
import io.n;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kp.p;
import lo.c;
import wo.h;
import wo.i;
import wo.l;
import wo.o;
import wo.q;
import wo.t;
import wo.w;
import xp.d1;
import xp.o0;
import xp.p0;
import xp.v0;
import yo.f0;

/* JADX INFO: loaded from: classes4.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f31788a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final h f31789b = i.b(l.SYNCHRONIZED, new kp.a() { // from class: ho.a
        @Override // kp.a
        public final Object invoke() {
            return b.l();
        }
    });

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int[] f31790c = {0, 1, 2, 3, 4, 5, 6};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final int[] f31791d = {0, 1, 2, 3};

    public static final class a {

        /* JADX INFO: renamed from: ho.b$a$a, reason: collision with other inner class name */
        static final class C0424a extends kotlin.coroutines.jvm.internal.d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            /* synthetic */ Object f31792a;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            int f31794c;

            C0424a(bp.f fVar) {
                super(fVar);
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                this.f31792a = obj;
                this.f31794c |= Integer.MIN_VALUE;
                return a.this.a(this);
            }
        }

        /* JADX INFO: renamed from: ho.b$a$b, reason: collision with other inner class name */
        static final class C0425b extends kotlin.coroutines.jvm.internal.j implements p {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            long f31795a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            int f31796b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            private /* synthetic */ Object f31797c;

            /* JADX INFO: renamed from: ho.b$a$b$a, reason: collision with other inner class name */
            static final class C0426a extends kotlin.coroutines.jvm.internal.j implements p {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                int f31798a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                private /* synthetic */ Object f31799b;

                C0426a(bp.f fVar) {
                    super(2, fVar);
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final bp.f create(Object obj, bp.f fVar) {
                    C0426a c0426a = new C0426a(fVar);
                    c0426a.f31799b = obj;
                    return c0426a;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    Object objB;
                    cp.b.e();
                    if (this.f31798a != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    q.b(obj);
                    try {
                        wo.p.a aVar = wo.p.f45327b;
                        objB = wo.p.b(lo.d.f34700a.g());
                    } catch (Throwable th2) {
                        wo.p.a aVar2 = wo.p.f45327b;
                        objB = wo.p.b(q.a(th2));
                    }
                    Throwable thD = wo.p.d(objB);
                    if (thD == null) {
                        return objB;
                    }
                    oo.b.a("FraudDetectionManager", "获取呼叫转移数据失败, case " + thD.getMessage());
                    return null;
                }

                /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
                /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
                @Override // kp.p
                public final Object invoke(o0 o0Var, bp.f fVar) {
                    return ((C0426a) create(o0Var, fVar)).invokeSuspend(w.f45339a);
                }
            }

            /* JADX INFO: renamed from: ho.b$a$b$b, reason: collision with other inner class name */
            static final class C0427b extends kotlin.coroutines.jvm.internal.j implements p {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                int f31800a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                private /* synthetic */ Object f31801b;

                C0427b(bp.f fVar) {
                    super(2, fVar);
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final bp.f create(Object obj, bp.f fVar) {
                    C0427b c0427b = new C0427b(fVar);
                    c0427b.f31801b = obj;
                    return c0427b;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    Object objB;
                    cp.b.e();
                    if (this.f31800a != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    q.b(obj);
                    try {
                        wo.p.a aVar = wo.p.f45327b;
                        objB = wo.p.b(kotlin.coroutines.jvm.internal.b.b(lo.d.f34700a.b()));
                    } catch (Throwable th2) {
                        wo.p.a aVar2 = wo.p.f45327b;
                        objB = wo.p.b(q.a(th2));
                    }
                    Throwable thD = wo.p.d(objB);
                    if (thD == null) {
                        return objB;
                    }
                    oo.b.a("FraudDetectionManager", "获取屏幕共享数据失败, case " + thD.getMessage());
                    return null;
                }

                /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
                /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
                @Override // kp.p
                public final Object invoke(o0 o0Var, bp.f fVar) {
                    return ((C0427b) create(o0Var, fVar)).invokeSuspend(w.f45339a);
                }
            }

            /* JADX INFO: renamed from: ho.b$a$b$c */
            static final class c extends kotlin.coroutines.jvm.internal.j implements p {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                int f31802a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                private /* synthetic */ Object f31803b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ m f31804c;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                c(m mVar, bp.f fVar) {
                    super(2, fVar);
                    this.f31804c = mVar;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final bp.f create(Object obj, bp.f fVar) {
                    c cVar = new c(this.f31804c, fVar);
                    cVar.f31803b = obj;
                    return cVar;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    Object objB;
                    cp.b.e();
                    if (this.f31802a != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    q.b(obj);
                    m mVar = this.f31804c;
                    try {
                        wo.p.a aVar = wo.p.f45327b;
                        objB = wo.p.b(kotlin.coroutines.jvm.internal.b.b(lo.d.f34700a.c(false, mVar)));
                    } catch (Throwable th2) {
                        wo.p.a aVar2 = wo.p.f45327b;
                        objB = wo.p.b(q.a(th2));
                    }
                    Throwable thD = wo.p.d(objB);
                    if (thD == null) {
                        return objB;
                    }
                    oo.b.a("FraudDetectionManager", "获取SIM卡通话数据失败, case " + thD.getMessage());
                    return null;
                }

                /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
                /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
                @Override // kp.p
                public final Object invoke(o0 o0Var, bp.f fVar) {
                    return ((c) create(o0Var, fVar)).invokeSuspend(w.f45339a);
                }
            }

            /* JADX INFO: renamed from: ho.b$a$b$d */
            static final class d extends kotlin.coroutines.jvm.internal.j implements p {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                int f31805a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                private /* synthetic */ Object f31806b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ m f31807c;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                d(m mVar, bp.f fVar) {
                    super(2, fVar);
                    this.f31807c = mVar;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final bp.f create(Object obj, bp.f fVar) {
                    d dVar = new d(this.f31807c, fVar);
                    dVar.f31806b = obj;
                    return dVar;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    Object objB;
                    cp.b.e();
                    if (this.f31805a != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    q.b(obj);
                    m mVar = this.f31807c;
                    try {
                        wo.p.a aVar = wo.p.f45327b;
                        objB = wo.p.b(kotlin.coroutines.jvm.internal.b.b(lo.d.f34700a.c(true, mVar)));
                    } catch (Throwable th2) {
                        wo.p.a aVar2 = wo.p.f45327b;
                        objB = wo.p.b(q.a(th2));
                    }
                    Throwable thD = wo.p.d(objB);
                    if (thD == null) {
                        return objB;
                    }
                    oo.b.a("FraudDetectionManager", "获取SIM卡欺诈通话失败, case " + thD.getMessage());
                    return null;
                }

                /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
                /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
                @Override // kp.p
                public final Object invoke(o0 o0Var, bp.f fVar) {
                    return ((d) create(o0Var, fVar)).invokeSuspend(w.f45339a);
                }
            }

            /* JADX INFO: renamed from: ho.b$a$b$e */
            static final class e extends kotlin.coroutines.jvm.internal.j implements p {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                int f31808a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                private /* synthetic */ Object f31809b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ n f31810c;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                e(n nVar, bp.f fVar) {
                    super(2, fVar);
                    this.f31810c = nVar;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final bp.f create(Object obj, bp.f fVar) {
                    e eVar = new e(this.f31810c, fVar);
                    eVar.f31809b = obj;
                    return eVar;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    Object objB;
                    cp.b.e();
                    if (this.f31808a != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    q.b(obj);
                    n nVar = this.f31810c;
                    try {
                        wo.p.a aVar = wo.p.f45327b;
                        objB = wo.p.b(kotlin.coroutines.jvm.internal.b.b(lo.d.f34700a.d(nVar)));
                    } catch (Throwable th2) {
                        wo.p.a aVar2 = wo.p.f45327b;
                        objB = wo.p.b(q.a(th2));
                    }
                    Throwable thD = wo.p.d(objB);
                    if (thD == null) {
                        return objB;
                    }
                    oo.b.a("FraudDetectionManager", "获取VoIP通话状态失败, case " + thD.getMessage());
                    return null;
                }

                /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
                /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
                @Override // kp.p
                public final Object invoke(o0 o0Var, bp.f fVar) {
                    return ((e) create(o0Var, fVar)).invokeSuspend(w.f45339a);
                }
            }

            /* JADX INFO: renamed from: ho.b$a$b$f */
            static final class f extends kotlin.coroutines.jvm.internal.j implements p {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                int f31811a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                private /* synthetic */ Object f31812b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ n f31813c;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                f(n nVar, bp.f fVar) {
                    super(2, fVar);
                    this.f31813c = nVar;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final bp.f create(Object obj, bp.f fVar) {
                    f fVar2 = new f(this.f31813c, fVar);
                    fVar2.f31812b = obj;
                    return fVar2;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    Object objB;
                    cp.b.e();
                    if (this.f31811a != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    q.b(obj);
                    n nVar = this.f31813c;
                    try {
                        wo.p.a aVar = wo.p.f45327b;
                        objB = wo.p.b(kotlin.coroutines.jvm.internal.b.b(lo.d.f34700a.a(nVar)));
                    } catch (Throwable th2) {
                        wo.p.a aVar2 = wo.p.f45327b;
                        objB = wo.p.b(q.a(th2));
                    }
                    Throwable thD = wo.p.d(objB);
                    if (thD == null) {
                        return objB;
                    }
                    oo.b.a("FraudDetectionManager", "获取VoIP欺诈检测失败, case " + thD.getMessage());
                    return null;
                }

                /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
                /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
                @Override // kp.p
                public final Object invoke(o0 o0Var, bp.f fVar) {
                    return ((f) create(o0Var, fVar)).invokeSuspend(w.f45339a);
                }
            }

            C0425b(bp.f fVar) {
                super(2, fVar);
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final bp.f create(Object obj, bp.f fVar) {
                C0425b c0425b = new C0425b(fVar);
                c0425b.f31797c = obj;
                return c0425b;
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                int i10;
                int i11;
                int i12;
                Object objB;
                Object objB2;
                int i13;
                Object objA;
                long j10;
                Object objB3;
                Object objE = cp.b.e();
                int i14 = this.f31796b;
                try {
                    if (i14 == 0) {
                        q.b(obj);
                        o0 o0Var = (o0) this.f31797c;
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        i10 = 4;
                        i11 = 3;
                        i12 = 5;
                        v0 v0VarB = xp.k.b(o0Var, null, null, new C0427b(null), 3, null);
                        try {
                            wo.p.a aVar = wo.p.f45327b;
                            objB = wo.p.b(jo.k.f33544c.a().D1());
                        } catch (Throwable th2) {
                            wo.p.a aVar2 = wo.p.f45327b;
                            objB = wo.p.b(q.a(th2));
                        }
                        Object obj2 = objB;
                        if (wo.p.f(obj2)) {
                            obj2 = null;
                        }
                        n nVar = (n) obj2;
                        try {
                            objB2 = wo.p.b(jo.k.f33544c.a().C1());
                        } catch (Throwable th3) {
                            wo.p.a aVar3 = wo.p.f45327b;
                            objB2 = wo.p.b(q.a(th3));
                        }
                        if (wo.p.f(objB2)) {
                            objB2 = null;
                        }
                        o oVarA = t.a(nVar, (m) objB2);
                        n nVar2 = (n) oVarA.a();
                        m mVar = (m) oVarA.b();
                        i13 = 2;
                        v0[] v0VarArr = {v0VarB, xp.k.b(o0Var, null, null, new e(nVar2, null), 3, null), xp.k.b(o0Var, null, null, new f(nVar2, null), 3, null), xp.k.b(o0Var, null, null, new c(mVar, null), 3, null), xp.k.b(o0Var, null, null, new d(mVar, null), 3, null), xp.k.b(o0Var, null, null, new C0426a(null), 3, null)};
                        this.f31797c = o0Var;
                        this.f31795a = jCurrentTimeMillis;
                        this.f31796b = 1;
                        objA = xp.f.a(v0VarArr, this);
                        if (objA == objE) {
                            return objE;
                        }
                        j10 = jCurrentTimeMillis;
                    } else {
                        if (i14 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        j10 = this.f31795a;
                        q.b(obj);
                        objA = obj;
                        i10 = 4;
                        i11 = 3;
                        i12 = 5;
                        i13 = 2;
                    }
                    List list = (List) objA;
                    Integer num = (Integer) list.get(0);
                    Integer num2 = (Integer) list.get(1);
                    Integer num3 = (Integer) list.get(i13);
                    Integer num4 = (Integer) list.get(i11);
                    Integer num5 = (Integer) list.get(i10);
                    Integer num6 = (Integer) list.get(i12);
                    CurrentFraudMetadata currentFraudMetadata = new CurrentFraudMetadata(num != null ? num.intValue() : lo.h.f34704a.B(io.e.ScreenShareInfo), num2 != null ? num2.intValue() : lo.h.f34704a.B(io.e.VoipRecordInfo), num3 != null ? num3.intValue() : lo.h.f34704a.B(io.e.VoipRecordInfo), num4 != null ? num4.intValue() : lo.h.f34704a.B(io.e.SimCardFraudInfo), num5 != null ? num5.intValue() : lo.h.f34704a.B(io.e.SimCardFraudInfo), num6 != null ? num6.intValue() : lo.h.f34704a.B(io.e.CallForwardingInfo));
                    try {
                        wo.p.a aVar4 = wo.p.f45327b;
                        objB3 = wo.p.b(GsonUtils.b(currentFraudMetadata));
                    } catch (Throwable th4) {
                        wo.p.a aVar5 = wo.p.f45327b;
                        objB3 = wo.p.b(q.a(th4));
                    }
                    Throwable thD = wo.p.d(objB3);
                    if (thD != null) {
                        oo.b.b("FraudDetectionManager", "JSON序列化失败, case " + thD.getMessage());
                        objB3 = "";
                    }
                    String str = (String) objB3;
                    oo.b.a("FraudDetectionManager", "getCurrentFraudResult: " + str + " , cost time: " + (System.currentTimeMillis() - j10) + "ms");
                    return str;
                } catch (Exception e10) {
                    oo.b.b("FraudDetectionManager", "getCurrentFraudResult error: " + e10);
                    return "";
                }
            }

            /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
            /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
            @Override // kp.p
            public final Object invoke(o0 o0Var, bp.f fVar) {
                return ((C0425b) create(o0Var, fVar)).invokeSuspend(w.f45339a);
            }
        }

        static final class c extends kotlin.coroutines.jvm.internal.j implements p {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            int f31814a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ kp.l f31815b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            c(kp.l lVar, bp.f fVar) {
                super(2, fVar);
                this.f31815b = lVar;
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final bp.f create(Object obj, bp.f fVar) {
                return new c(this.f31815b, fVar);
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                Object objE = cp.b.e();
                int i10 = this.f31814a;
                try {
                    if (i10 == 0) {
                        q.b(obj);
                        a aVar = b.f31788a;
                        this.f31814a = 1;
                        obj = aVar.a(this);
                        if (obj == objE) {
                            return objE;
                        }
                    } else {
                        if (i10 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        q.b(obj);
                    }
                } catch (Exception e10) {
                    oo.b.b("FraudDetectionManager", "getCurrentFraudResultAsync error: " + e10);
                }
                return w.f45339a;
            }

            /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
            /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
            @Override // kp.p
            public final Object invoke(o0 o0Var, bp.f fVar) {
                return ((c) create(o0Var, fVar)).invokeSuspend(w.f45339a);
            }
        }

        static final class d extends kotlin.coroutines.jvm.internal.d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            /* synthetic */ Object f31816a;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            int f31818c;

            d(bp.f fVar) {
                super(fVar);
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                this.f31816a = obj;
                this.f31818c |= Integer.MIN_VALUE;
                return a.this.c(null, null, this);
            }
        }

        static final class e extends kotlin.coroutines.jvm.internal.j implements p {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            long f31819a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            int f31820b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            private /* synthetic */ Object f31821c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ int[] f31822d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ int[] f31823e;

            /* JADX INFO: renamed from: ho.b$a$e$a, reason: collision with other inner class name */
            static final class C0428a extends kotlin.coroutines.jvm.internal.j implements p {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                int f31824a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                private /* synthetic */ Object f31825b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ int[] f31826c;

                /* JADX INFO: renamed from: d, reason: collision with root package name */
                final /* synthetic */ int[] f31827d;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C0428a(int[] iArr, int[] iArr2, bp.f fVar) {
                    super(2, fVar);
                    this.f31826c = iArr;
                    this.f31827d = iArr2;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final bp.f create(Object obj, bp.f fVar) {
                    C0428a c0428a = new C0428a(this.f31826c, this.f31827d, fVar);
                    c0428a.f31825b = obj;
                    return c0428a;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    Object objB;
                    Object objE = cp.b.e();
                    int i10 = this.f31824a;
                    try {
                        if (i10 == 0) {
                            q.b(obj);
                            int[] iArr = this.f31826c;
                            int[] iArr2 = this.f31827d;
                            wo.p.a aVar = wo.p.f45327b;
                            lo.h.a aVar2 = lo.h.f34704a;
                            this.f31824a = 1;
                            obj = aVar2.D(iArr, iArr2, this);
                            if (obj == objE) {
                                return objE;
                            }
                        } else {
                            if (i10 != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            q.b(obj);
                        }
                        objB = wo.p.b((Map) obj);
                    } catch (Throwable th2) {
                        wo.p.a aVar3 = wo.p.f45327b;
                        objB = wo.p.b(q.a(th2));
                    }
                    Throwable thD = wo.p.d(objB);
                    if (thD == null) {
                        return objB;
                    }
                    oo.b.a("FraudDetectionManager", "获取时长屏幕共享数据失败，case " + thD.getMessage());
                    return f0.h();
                }

                /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
                /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
                @Override // kp.p
                public final Object invoke(o0 o0Var, bp.f fVar) {
                    return ((C0428a) create(o0Var, fVar)).invokeSuspend(w.f45339a);
                }
            }

            /* JADX INFO: renamed from: ho.b$a$e$b, reason: collision with other inner class name */
            static final class C0429b extends kotlin.coroutines.jvm.internal.j implements p {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                int f31828a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                private /* synthetic */ Object f31829b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ int[] f31830c;

                /* JADX INFO: renamed from: d, reason: collision with root package name */
                final /* synthetic */ int[] f31831d;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C0429b(int[] iArr, int[] iArr2, bp.f fVar) {
                    super(2, fVar);
                    this.f31830c = iArr;
                    this.f31831d = iArr2;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final bp.f create(Object obj, bp.f fVar) {
                    C0429b c0429b = new C0429b(this.f31830c, this.f31831d, fVar);
                    c0429b.f31829b = obj;
                    return c0429b;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    Object objB;
                    Object objE = cp.b.e();
                    int i10 = this.f31828a;
                    try {
                        if (i10 == 0) {
                            q.b(obj);
                            int[] iArr = this.f31830c;
                            int[] iArr2 = this.f31831d;
                            wo.p.a aVar = wo.p.f45327b;
                            lo.h.a aVar2 = lo.h.f34704a;
                            io.l lVar = io.l.TYPE_ALL_CALL;
                            io.e eVar = io.e.SimCardInfo;
                            this.f31828a = 1;
                            obj = aVar2.E(lVar, eVar, iArr, iArr2, this);
                            if (obj == objE) {
                                return objE;
                            }
                        } else {
                            if (i10 != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            q.b(obj);
                        }
                        objB = wo.p.b((Map) obj);
                    } catch (Throwable th2) {
                        wo.p.a aVar3 = wo.p.f45327b;
                        objB = wo.p.b(q.a(th2));
                    }
                    Throwable thD = wo.p.d(objB);
                    if (thD == null) {
                        return objB;
                    }
                    oo.b.a("FraudDetectionManager", "获取时长SIM卡通话数据失败，case " + thD.getMessage());
                    return f0.h();
                }

                /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
                /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
                @Override // kp.p
                public final Object invoke(o0 o0Var, bp.f fVar) {
                    return ((C0429b) create(o0Var, fVar)).invokeSuspend(w.f45339a);
                }
            }

            static final class c extends kotlin.coroutines.jvm.internal.j implements p {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                int f31832a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                private /* synthetic */ Object f31833b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ int[] f31834c;

                /* JADX INFO: renamed from: d, reason: collision with root package name */
                final /* synthetic */ int[] f31835d;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                c(int[] iArr, int[] iArr2, bp.f fVar) {
                    super(2, fVar);
                    this.f31834c = iArr;
                    this.f31835d = iArr2;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final bp.f create(Object obj, bp.f fVar) {
                    c cVar = new c(this.f31834c, this.f31835d, fVar);
                    cVar.f31833b = obj;
                    return cVar;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    Object objB;
                    Object objE = cp.b.e();
                    int i10 = this.f31832a;
                    try {
                        if (i10 == 0) {
                            q.b(obj);
                            int[] iArr = this.f31834c;
                            int[] iArr2 = this.f31835d;
                            wo.p.a aVar = wo.p.f45327b;
                            lo.h.a aVar2 = lo.h.f34704a;
                            io.l lVar = io.l.TYPE_FRAUD_CALL;
                            io.e eVar = io.e.SimCardFraudInfo;
                            this.f31832a = 1;
                            obj = aVar2.E(lVar, eVar, iArr, iArr2, this);
                            if (obj == objE) {
                                return objE;
                            }
                        } else {
                            if (i10 != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            q.b(obj);
                        }
                        objB = wo.p.b((Map) obj);
                    } catch (Throwable th2) {
                        wo.p.a aVar3 = wo.p.f45327b;
                        objB = wo.p.b(q.a(th2));
                    }
                    Throwable thD = wo.p.d(objB);
                    if (thD == null) {
                        return objB;
                    }
                    oo.b.a("FraudDetectionManager", "获取时长SIM卡欺诈通话数据失败，case " + thD.getMessage());
                    return f0.h();
                }

                /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
                /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
                @Override // kp.p
                public final Object invoke(o0 o0Var, bp.f fVar) {
                    return ((c) create(o0Var, fVar)).invokeSuspend(w.f45339a);
                }
            }

            static final class d extends kotlin.coroutines.jvm.internal.j implements p {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                int f31836a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                private /* synthetic */ Object f31837b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ int[] f31838c;

                /* JADX INFO: renamed from: d, reason: collision with root package name */
                final /* synthetic */ int[] f31839d;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                d(int[] iArr, int[] iArr2, bp.f fVar) {
                    super(2, fVar);
                    this.f31838c = iArr;
                    this.f31839d = iArr2;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final bp.f create(Object obj, bp.f fVar) {
                    d dVar = new d(this.f31838c, this.f31839d, fVar);
                    dVar.f31837b = obj;
                    return dVar;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    Object objB;
                    Object objE = cp.b.e();
                    int i10 = this.f31836a;
                    try {
                        if (i10 == 0) {
                            q.b(obj);
                            int[] iArr = this.f31838c;
                            int[] iArr2 = this.f31839d;
                            wo.p.a aVar = wo.p.f45327b;
                            lo.h.a aVar2 = lo.h.f34704a;
                            io.l lVar = io.l.TYPE_OVERSEAS_CALL;
                            io.e eVar = io.e.OverseasCallInfo;
                            this.f31836a = 1;
                            obj = aVar2.E(lVar, eVar, iArr, iArr2, this);
                            if (obj == objE) {
                                return objE;
                            }
                        } else {
                            if (i10 != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            q.b(obj);
                        }
                        objB = wo.p.b((Map) obj);
                    } catch (Throwable th2) {
                        wo.p.a aVar3 = wo.p.f45327b;
                        objB = wo.p.b(q.a(th2));
                    }
                    Throwable thD = wo.p.d(objB);
                    if (thD == null) {
                        return objB;
                    }
                    oo.b.a("FraudDetectionManager", "获取时长SIM卡境外通话数据失败，case " + thD.getMessage());
                    return f0.h();
                }

                /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
                /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
                @Override // kp.p
                public final Object invoke(o0 o0Var, bp.f fVar) {
                    return ((d) create(o0Var, fVar)).invokeSuspend(w.f45339a);
                }
            }

            /* JADX INFO: renamed from: ho.b$a$e$e, reason: collision with other inner class name */
            static final class C0430e extends kotlin.coroutines.jvm.internal.j implements p {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                int f31840a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                private /* synthetic */ Object f31841b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ int[] f31842c;

                /* JADX INFO: renamed from: d, reason: collision with root package name */
                final /* synthetic */ int[] f31843d;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C0430e(int[] iArr, int[] iArr2, bp.f fVar) {
                    super(2, fVar);
                    this.f31842c = iArr;
                    this.f31843d = iArr2;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final bp.f create(Object obj, bp.f fVar) {
                    C0430e c0430e = new C0430e(this.f31842c, this.f31843d, fVar);
                    c0430e.f31841b = obj;
                    return c0430e;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    Object objB;
                    Object objE = cp.b.e();
                    int i10 = this.f31840a;
                    try {
                        if (i10 == 0) {
                            q.b(obj);
                            int[] iArr = this.f31842c;
                            int[] iArr2 = this.f31843d;
                            wo.p.a aVar = wo.p.f45327b;
                            lo.h.a aVar2 = lo.h.f34704a;
                            io.l lVar = io.l.TYPE_VIRTUAL_OPERATOR_CALL;
                            io.e eVar = io.e.VirtualOperatorCallInfo;
                            this.f31840a = 1;
                            obj = aVar2.E(lVar, eVar, iArr, iArr2, this);
                            if (obj == objE) {
                                return objE;
                            }
                        } else {
                            if (i10 != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            q.b(obj);
                        }
                        objB = wo.p.b((Map) obj);
                    } catch (Throwable th2) {
                        wo.p.a aVar3 = wo.p.f45327b;
                        objB = wo.p.b(q.a(th2));
                    }
                    Throwable thD = wo.p.d(objB);
                    if (thD == null) {
                        return objB;
                    }
                    oo.b.a("FraudDetectionManager", "获取时长SIM卡虚拟运营商通话数据失败，case " + thD.getMessage());
                    return f0.h();
                }

                /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
                /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
                @Override // kp.p
                public final Object invoke(o0 o0Var, bp.f fVar) {
                    return ((C0430e) create(o0Var, fVar)).invokeSuspend(w.f45339a);
                }
            }

            static final class f extends kotlin.coroutines.jvm.internal.j implements p {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                int f31844a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                private /* synthetic */ Object f31845b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ int[] f31846c;

                /* JADX INFO: renamed from: d, reason: collision with root package name */
                final /* synthetic */ int[] f31847d;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                f(int[] iArr, int[] iArr2, bp.f fVar) {
                    super(2, fVar);
                    this.f31846c = iArr;
                    this.f31847d = iArr2;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final bp.f create(Object obj, bp.f fVar) {
                    f fVar2 = new f(this.f31846c, this.f31847d, fVar);
                    fVar2.f31845b = obj;
                    return fVar2;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    Object objB;
                    Object objE = cp.b.e();
                    int i10 = this.f31844a;
                    try {
                        if (i10 == 0) {
                            q.b(obj);
                            int[] iArr = this.f31846c;
                            int[] iArr2 = this.f31847d;
                            wo.p.a aVar = wo.p.f45327b;
                            lo.h.a aVar2 = lo.h.f34704a;
                            io.o oVar = io.o.TYPE_ALL_CALL;
                            this.f31844a = 1;
                            obj = aVar2.F(oVar, iArr, iArr2, this);
                            if (obj == objE) {
                                return objE;
                            }
                        } else {
                            if (i10 != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            q.b(obj);
                        }
                        objB = wo.p.b((Map) obj);
                    } catch (Throwable th2) {
                        wo.p.a aVar3 = wo.p.f45327b;
                        objB = wo.p.b(q.a(th2));
                    }
                    Throwable thD = wo.p.d(objB);
                    if (thD == null) {
                        return objB;
                    }
                    oo.b.a("FraudDetectionManager", "获取时长VoIP通话数据失败，case " + thD.getMessage());
                    return f0.h();
                }

                /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
                /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
                @Override // kp.p
                public final Object invoke(o0 o0Var, bp.f fVar) {
                    return ((f) create(o0Var, fVar)).invokeSuspend(w.f45339a);
                }
            }

            static final class g extends kotlin.coroutines.jvm.internal.j implements p {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                int f31848a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                private /* synthetic */ Object f31849b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ int[] f31850c;

                /* JADX INFO: renamed from: d, reason: collision with root package name */
                final /* synthetic */ int[] f31851d;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                g(int[] iArr, int[] iArr2, bp.f fVar) {
                    super(2, fVar);
                    this.f31850c = iArr;
                    this.f31851d = iArr2;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final bp.f create(Object obj, bp.f fVar) {
                    g gVar = new g(this.f31850c, this.f31851d, fVar);
                    gVar.f31849b = obj;
                    return gVar;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    Object objB;
                    Object objE = cp.b.e();
                    int i10 = this.f31848a;
                    try {
                        if (i10 == 0) {
                            q.b(obj);
                            int[] iArr = this.f31850c;
                            int[] iArr2 = this.f31851d;
                            wo.p.a aVar = wo.p.f45327b;
                            lo.h.a aVar2 = lo.h.f34704a;
                            io.o oVar = io.o.TYPE_FRAUD_CALL;
                            this.f31848a = 1;
                            obj = aVar2.F(oVar, iArr, iArr2, this);
                            if (obj == objE) {
                                return objE;
                            }
                        } else {
                            if (i10 != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            q.b(obj);
                        }
                        objB = wo.p.b((Map) obj);
                    } catch (Throwable th2) {
                        wo.p.a aVar3 = wo.p.f45327b;
                        objB = wo.p.b(q.a(th2));
                    }
                    Throwable thD = wo.p.d(objB);
                    if (thD == null) {
                        return objB;
                    }
                    oo.b.a("FraudDetectionManager", "获取时长VoIP欺诈通话数据失败，case " + thD.getMessage());
                    return f0.h();
                }

                /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
                /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
                @Override // kp.p
                public final Object invoke(o0 o0Var, bp.f fVar) {
                    return ((g) create(o0Var, fVar)).invokeSuspend(w.f45339a);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            e(int[] iArr, int[] iArr2, bp.f fVar) {
                super(2, fVar);
                this.f31822d = iArr;
                this.f31823e = iArr2;
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final bp.f create(Object obj, bp.f fVar) {
                e eVar = new e(this.f31822d, this.f31823e, fVar);
                eVar.f31821c = obj;
                return eVar;
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                int i10;
                int i11;
                int i12;
                int i13;
                int i14;
                int i15;
                Object objA;
                long j10;
                Object objB;
                String strO;
                String strO2;
                Object objE = cp.b.e();
                int i16 = this.f31820b;
                try {
                    if (i16 == 0) {
                        q.b(obj);
                        o0 o0Var = (o0) this.f31821c;
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        i10 = 6;
                        i11 = 5;
                        i12 = 4;
                        i13 = 3;
                        i14 = 2;
                        i15 = 0;
                        v0[] v0VarArr = {xp.k.b(o0Var, null, null, new C0428a(this.f31822d, this.f31823e, null), 3, null), xp.k.b(o0Var, null, null, new f(this.f31822d, this.f31823e, null), 3, null), xp.k.b(o0Var, null, null, new g(this.f31822d, this.f31823e, null), 3, null), xp.k.b(o0Var, null, null, new C0429b(this.f31822d, this.f31823e, null), 3, null), xp.k.b(o0Var, null, null, new c(this.f31822d, this.f31823e, null), 3, null), xp.k.b(o0Var, null, null, new d(this.f31822d, this.f31823e, null), 3, null), xp.k.b(o0Var, null, null, new C0430e(this.f31822d, this.f31823e, null), 3, null)};
                        this.f31821c = o0Var;
                        this.f31819a = jCurrentTimeMillis;
                        this.f31820b = 1;
                        objA = xp.f.a(v0VarArr, this);
                        if (objA == objE) {
                            return objE;
                        }
                        j10 = jCurrentTimeMillis;
                    } else {
                        if (i16 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        j10 = this.f31819a;
                        q.b(obj);
                        objA = obj;
                        i10 = 6;
                        i11 = 5;
                        i12 = 4;
                        i13 = 3;
                        i14 = 2;
                        i15 = 0;
                    }
                    List list = (List) objA;
                    long jCurrentTimeMillis2 = System.currentTimeMillis();
                    long j11 = jCurrentTimeMillis2 - j10;
                    Map map = (Map) list.get(i15);
                    Map map2 = (Map) list.get(1);
                    Map map3 = (Map) list.get(i14);
                    Map map4 = (Map) list.get(i13);
                    Map map5 = (Map) list.get(i12);
                    Map map6 = (Map) list.get(i11);
                    Map map7 = (Map) list.get(i10);
                    long jCurrentTimeMillis3 = System.currentTimeMillis() - jCurrentTimeMillis2;
                    DurationFraudData durationFraudData = new DurationFraudData(map, map2, map3, map4, map5, map6, map7);
                    try {
                        wo.p.a aVar = wo.p.f45327b;
                        objB = wo.p.b(GsonUtils.b(durationFraudData));
                    } catch (Throwable th2) {
                        wo.p.a aVar2 = wo.p.f45327b;
                        objB = wo.p.b(q.a(th2));
                    }
                    Throwable thD = wo.p.d(objB);
                    if (thD != null) {
                        oo.b.b("FraudDetectionManager", "JSON序列化失败, case " + thD.getMessage());
                        objB = "";
                    }
                    String str = (String) objB;
                    long jCurrentTimeMillis4 = System.currentTimeMillis() - j10;
                    int length = str.length();
                    int[] iArr = this.f31822d;
                    String str2 = "null";
                    if (iArr == null || (strO = yo.i.O(iArr, null, null, null, 0, null, null, 63, null)) == null) {
                        strO = "null";
                    }
                    int[] iArr2 = this.f31823e;
                    if (iArr2 != null && (strO2 = yo.i.O(iArr2, null, null, null, 0, null, null, 63, null)) != null) {
                        str2 = strO2;
                    }
                    oo.b.a("FraudDetectionManager", up.n.p("getDurationFraudResult性能统计:\n            |并发等待时间: " + j11 + "ms\n            |数据处理时间: " + jCurrentTimeMillis3 + "ms\n            |总耗时: " + jCurrentTimeMillis4 + "ms\n            |数据大小: " + length + "字符\n            |时间范围: " + strO + "\n            |时长范围: " + str2 + "\n            ", null, 1, null));
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("getDurationFraudResult: ");
                    sb2.append(str);
                    oo.b.a("FraudDetectionManager", sb2.toString());
                    return str;
                } catch (Exception e10) {
                    oo.b.b("FraudDetectionManager", "getDurationFraudResult error: " + e10);
                    return "";
                }
            }

            /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
            /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
            @Override // kp.p
            public final Object invoke(o0 o0Var, bp.f fVar) {
                return ((e) create(o0Var, fVar)).invokeSuspend(w.f45339a);
            }
        }

        static final class f extends kotlin.coroutines.jvm.internal.j implements p {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            int f31852a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ int[] f31853b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ int[] f31854c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ kp.l f31855d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            f(int[] iArr, int[] iArr2, kp.l lVar, bp.f fVar) {
                super(2, fVar);
                this.f31853b = iArr;
                this.f31854c = iArr2;
                this.f31855d = lVar;
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final bp.f create(Object obj, bp.f fVar) {
                return new f(this.f31853b, this.f31854c, this.f31855d, fVar);
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                Object objE = cp.b.e();
                int i10 = this.f31852a;
                try {
                    if (i10 == 0) {
                        q.b(obj);
                        a aVar = b.f31788a;
                        int[] iArr = this.f31853b;
                        int[] iArr2 = this.f31854c;
                        this.f31852a = 1;
                        obj = aVar.c(iArr, iArr2, this);
                        if (obj == objE) {
                            return objE;
                        }
                    } else {
                        if (i10 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        q.b(obj);
                    }
                } catch (Exception e10) {
                    oo.b.b("FraudDetectionManager", "getDurationFraudResultAsync error: " + e10);
                }
                return w.f45339a;
            }

            /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
            /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
            @Override // kp.p
            public final Object invoke(o0 o0Var, bp.f fVar) {
                return ((f) create(o0Var, fVar)).invokeSuspend(w.f45339a);
            }
        }

        static final class g extends kotlin.coroutines.jvm.internal.d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            /* synthetic */ Object f31856a;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            int f31858c;

            g(bp.f fVar) {
                super(fVar);
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                this.f31856a = obj;
                this.f31858c |= Integer.MIN_VALUE;
                return a.this.g(null, this);
            }
        }

        static final class h extends kotlin.coroutines.jvm.internal.j implements p {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            long f31859a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            int f31860b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            private /* synthetic */ Object f31861c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ int[] f31862d;

            /* JADX INFO: renamed from: ho.b$a$h$a, reason: collision with other inner class name */
            static final class C0431a extends kotlin.coroutines.jvm.internal.j implements p {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                int f31863a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                private /* synthetic */ Object f31864b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ int[] f31865c;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C0431a(int[] iArr, bp.f fVar) {
                    super(2, fVar);
                    this.f31865c = iArr;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final bp.f create(Object obj, bp.f fVar) {
                    C0431a c0431a = new C0431a(this.f31865c, fVar);
                    c0431a.f31864b = obj;
                    return c0431a;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    Object objB;
                    Object objE = cp.b.e();
                    int i10 = this.f31863a;
                    try {
                        if (i10 == 0) {
                            q.b(obj);
                            int[] iArr = this.f31865c;
                            wo.p.a aVar = wo.p.f45327b;
                            lo.h.a aVar2 = lo.h.f34704a;
                            this.f31863a = 1;
                            obj = aVar2.H(iArr, this);
                            if (obj == objE) {
                                return objE;
                            }
                        } else {
                            if (i10 != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            q.b(obj);
                        }
                        objB = wo.p.b((Map) obj);
                    } catch (Throwable th2) {
                        wo.p.a aVar3 = wo.p.f45327b;
                        objB = wo.p.b(q.a(th2));
                    }
                    Throwable thD = wo.p.d(objB);
                    if (thD == null) {
                        return objB;
                    }
                    oo.b.a("FraudDetectionManager", "获取历史飞行模式数据失败, case " + thD.getMessage());
                    return f0.h();
                }

                /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
                /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
                @Override // kp.p
                public final Object invoke(o0 o0Var, bp.f fVar) {
                    return ((C0431a) create(o0Var, fVar)).invokeSuspend(w.f45339a);
                }
            }

            /* JADX INFO: renamed from: ho.b$a$h$b, reason: collision with other inner class name */
            static final class C0432b extends kotlin.coroutines.jvm.internal.j implements p {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                int f31866a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                private /* synthetic */ Object f31867b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ int[] f31868c;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C0432b(int[] iArr, bp.f fVar) {
                    super(2, fVar);
                    this.f31868c = iArr;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final bp.f create(Object obj, bp.f fVar) {
                    C0432b c0432b = new C0432b(this.f31868c, fVar);
                    c0432b.f31867b = obj;
                    return c0432b;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    Object objB;
                    Object objE = cp.b.e();
                    int i10 = this.f31866a;
                    try {
                        if (i10 == 0) {
                            q.b(obj);
                            int[] iArr = this.f31868c;
                            wo.p.a aVar = wo.p.f45327b;
                            lo.h.a aVar2 = lo.h.f34704a;
                            this.f31866a = 1;
                            obj = aVar2.K(iArr, this);
                            if (obj == objE) {
                                return objE;
                            }
                        } else {
                            if (i10 != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            q.b(obj);
                        }
                        objB = wo.p.b((Map) obj);
                    } catch (Throwable th2) {
                        wo.p.a aVar3 = wo.p.f45327b;
                        objB = wo.p.b(q.a(th2));
                    }
                    Throwable thD = wo.p.d(objB);
                    if (thD == null) {
                        return objB;
                    }
                    oo.b.a("FraudDetectionManager", "获取历史应用安装数据失败, case " + thD.getMessage());
                    return f0.h();
                }

                /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
                /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
                @Override // kp.p
                public final Object invoke(o0 o0Var, bp.f fVar) {
                    return ((C0432b) create(o0Var, fVar)).invokeSuspend(w.f45339a);
                }
            }

            static final class c extends kotlin.coroutines.jvm.internal.j implements p {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                int f31869a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                private /* synthetic */ Object f31870b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ int[] f31871c;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                c(int[] iArr, bp.f fVar) {
                    super(2, fVar);
                    this.f31871c = iArr;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final bp.f create(Object obj, bp.f fVar) {
                    c cVar = new c(this.f31871c, fVar);
                    cVar.f31870b = obj;
                    return cVar;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    Object objB;
                    Object objE = cp.b.e();
                    int i10 = this.f31869a;
                    try {
                        if (i10 == 0) {
                            q.b(obj);
                            int[] iArr = this.f31871c;
                            wo.p.a aVar = wo.p.f45327b;
                            lo.h.a aVar2 = lo.h.f34704a;
                            this.f31869a = 1;
                            obj = aVar2.I(iArr, this);
                            if (obj == objE) {
                                return objE;
                            }
                        } else {
                            if (i10 != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            q.b(obj);
                        }
                        objB = wo.p.b((Map) obj);
                    } catch (Throwable th2) {
                        wo.p.a aVar3 = wo.p.f45327b;
                        objB = wo.p.b(q.a(th2));
                    }
                    Throwable thD = wo.p.d(objB);
                    if (thD == null) {
                        return objB;
                    }
                    oo.b.a("FraudDetectionManager", "获取历史呼叫转移数据失败, case " + thD.getMessage());
                    return f0.h();
                }

                /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
                /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
                @Override // kp.p
                public final Object invoke(o0 o0Var, bp.f fVar) {
                    return ((c) create(o0Var, fVar)).invokeSuspend(w.f45339a);
                }
            }

            static final class d extends kotlin.coroutines.jvm.internal.j implements p {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                int f31872a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                private /* synthetic */ Object f31873b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ int[] f31874c;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                d(int[] iArr, bp.f fVar) {
                    super(2, fVar);
                    this.f31874c = iArr;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final bp.f create(Object obj, bp.f fVar) {
                    d dVar = new d(this.f31874c, fVar);
                    dVar.f31873b = obj;
                    return dVar;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    Object objB;
                    Object objE = cp.b.e();
                    int i10 = this.f31872a;
                    try {
                        if (i10 == 0) {
                            q.b(obj);
                            int[] iArr = this.f31874c;
                            wo.p.a aVar = wo.p.f45327b;
                            lo.h.a aVar2 = lo.h.f34704a;
                            this.f31872a = 1;
                            obj = aVar2.J(iArr, this);
                            if (obj == objE) {
                                return objE;
                            }
                        } else {
                            if (i10 != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            q.b(obj);
                        }
                        objB = wo.p.b((Map) obj);
                    } catch (Throwable th2) {
                        wo.p.a aVar3 = wo.p.f45327b;
                        objB = wo.p.b(q.a(th2));
                    }
                    Throwable thD = wo.p.d(objB);
                    if (thD == null) {
                        return objB;
                    }
                    oo.b.a("FraudDetectionManager", "获取历史欺诈URL数据失败, case " + thD.getMessage());
                    return f0.h();
                }

                /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
                /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
                @Override // kp.p
                public final Object invoke(o0 o0Var, bp.f fVar) {
                    return ((d) create(o0Var, fVar)).invokeSuspend(w.f45339a);
                }
            }

            static final class e extends kotlin.coroutines.jvm.internal.j implements p {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                int f31875a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                private /* synthetic */ Object f31876b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ int[] f31877c;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                e(int[] iArr, bp.f fVar) {
                    super(2, fVar);
                    this.f31877c = iArr;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final bp.f create(Object obj, bp.f fVar) {
                    e eVar = new e(this.f31877c, fVar);
                    eVar.f31876b = obj;
                    return eVar;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    Object objB;
                    Object objE = cp.b.e();
                    int i10 = this.f31875a;
                    try {
                        if (i10 == 0) {
                            q.b(obj);
                            int[] iArr = this.f31877c;
                            wo.p.a aVar = wo.p.f45327b;
                            lo.h.a aVar2 = lo.h.f34704a;
                            this.f31875a = 1;
                            obj = aVar2.L(iArr, this);
                            if (obj == objE) {
                                return objE;
                            }
                        } else {
                            if (i10 != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            q.b(obj);
                        }
                        objB = wo.p.b((Map) obj);
                    } catch (Throwable th2) {
                        wo.p.a aVar3 = wo.p.f45327b;
                        objB = wo.p.b(q.a(th2));
                    }
                    Throwable thD = wo.p.d(objB);
                    if (thD == null) {
                        return objB;
                    }
                    oo.b.a("FraudDetectionManager", "获取历史屏幕共享数据失败, case " + thD.getMessage());
                    return f0.h();
                }

                /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
                /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
                @Override // kp.p
                public final Object invoke(o0 o0Var, bp.f fVar) {
                    return ((e) create(o0Var, fVar)).invokeSuspend(w.f45339a);
                }
            }

            static final class f extends kotlin.coroutines.jvm.internal.j implements p {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                int f31878a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                private /* synthetic */ Object f31879b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ int[] f31880c;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                f(int[] iArr, bp.f fVar) {
                    super(2, fVar);
                    this.f31880c = iArr;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final bp.f create(Object obj, bp.f fVar) {
                    f fVar2 = new f(this.f31880c, fVar);
                    fVar2.f31879b = obj;
                    return fVar2;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    Object objB;
                    Object objE = cp.b.e();
                    int i10 = this.f31878a;
                    try {
                        if (i10 == 0) {
                            q.b(obj);
                            int[] iArr = this.f31880c;
                            wo.p.a aVar = wo.p.f45327b;
                            lo.h.a aVar2 = lo.h.f34704a;
                            this.f31878a = 1;
                            obj = aVar2.M(iArr, this);
                            if (obj == objE) {
                                return objE;
                            }
                        } else {
                            if (i10 != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            q.b(obj);
                        }
                        objB = wo.p.b((Map) obj);
                    } catch (Throwable th2) {
                        wo.p.a aVar3 = wo.p.f45327b;
                        objB = wo.p.b(q.a(th2));
                    }
                    Throwable thD = wo.p.d(objB);
                    if (thD == null) {
                        return objB;
                    }
                    oo.b.a("FraudDetectionManager", "获取历史截屏数据失败， case " + thD.getMessage());
                    return f0.h();
                }

                /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
                /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
                @Override // kp.p
                public final Object invoke(o0 o0Var, bp.f fVar) {
                    return ((f) create(o0Var, fVar)).invokeSuspend(w.f45339a);
                }
            }

            static final class g extends kotlin.coroutines.jvm.internal.j implements p {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                int f31881a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                private /* synthetic */ Object f31882b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ int[] f31883c;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                g(int[] iArr, bp.f fVar) {
                    super(2, fVar);
                    this.f31883c = iArr;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final bp.f create(Object obj, bp.f fVar) {
                    g gVar = new g(this.f31883c, fVar);
                    gVar.f31882b = obj;
                    return gVar;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    Object objB;
                    Object objE = cp.b.e();
                    int i10 = this.f31881a;
                    try {
                        if (i10 == 0) {
                            q.b(obj);
                            int[] iArr = this.f31883c;
                            wo.p.a aVar = wo.p.f45327b;
                            lo.h.a aVar2 = lo.h.f34704a;
                            io.l lVar = io.l.TYPE_FRAUD_CALL;
                            this.f31881a = 1;
                            obj = aVar2.N(lVar, iArr, this);
                            if (obj == objE) {
                                return objE;
                            }
                        } else {
                            if (i10 != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            q.b(obj);
                        }
                        objB = wo.p.b((Map) obj);
                    } catch (Throwable th2) {
                        wo.p.a aVar3 = wo.p.f45327b;
                        objB = wo.p.b(q.a(th2));
                    }
                    Throwable thD = wo.p.d(objB);
                    if (thD == null) {
                        return objB;
                    }
                    oo.b.a("FraudDetectionManager", "获取历史SIM卡欺诈通话数据失败, case " + thD.getMessage());
                    return f0.h();
                }

                /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
                /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
                @Override // kp.p
                public final Object invoke(o0 o0Var, bp.f fVar) {
                    return ((g) create(o0Var, fVar)).invokeSuspend(w.f45339a);
                }
            }

            /* JADX INFO: renamed from: ho.b$a$h$h, reason: collision with other inner class name */
            static final class C0433h extends kotlin.coroutines.jvm.internal.j implements p {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                int f31884a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                private /* synthetic */ Object f31885b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ int[] f31886c;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C0433h(int[] iArr, bp.f fVar) {
                    super(2, fVar);
                    this.f31886c = iArr;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final bp.f create(Object obj, bp.f fVar) {
                    C0433h c0433h = new C0433h(this.f31886c, fVar);
                    c0433h.f31885b = obj;
                    return c0433h;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    Object objB;
                    Object objE = cp.b.e();
                    int i10 = this.f31884a;
                    try {
                        if (i10 == 0) {
                            q.b(obj);
                            int[] iArr = this.f31886c;
                            wo.p.a aVar = wo.p.f45327b;
                            lo.h.a aVar2 = lo.h.f34704a;
                            this.f31884a = 1;
                            obj = aVar2.O(iArr, this);
                            if (obj == objE) {
                                return objE;
                            }
                        } else {
                            if (i10 != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            q.b(obj);
                        }
                        objB = wo.p.b((Map) obj);
                    } catch (Throwable th2) {
                        wo.p.a aVar3 = wo.p.f45327b;
                        objB = wo.p.b(q.a(th2));
                    }
                    Throwable thD = wo.p.d(objB);
                    if (thD == null) {
                        return objB;
                    }
                    oo.b.a("FraudDetectionManager", "获取历史VoIP欺诈通话数据失败, case " + thD.getMessage());
                    return f0.h();
                }

                /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
                /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
                @Override // kp.p
                public final Object invoke(o0 o0Var, bp.f fVar) {
                    return ((C0433h) create(o0Var, fVar)).invokeSuspend(w.f45339a);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            h(int[] iArr, bp.f fVar) {
                super(2, fVar);
                this.f31862d = iArr;
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final bp.f create(Object obj, bp.f fVar) {
                h hVar = new h(this.f31862d, fVar);
                hVar.f31861c = obj;
                return hVar;
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                int i10;
                int i11;
                int i12;
                int i13;
                int i14;
                int i15;
                int i16;
                Object objA;
                long j10;
                Object objB;
                String strO;
                Object objE = cp.b.e();
                int i17 = this.f31860b;
                try {
                    if (i17 == 0) {
                        q.b(obj);
                        o0 o0Var = (o0) this.f31861c;
                        i10 = 6;
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        i11 = 7;
                        i12 = 5;
                        i13 = 4;
                        i14 = 3;
                        i15 = 2;
                        i16 = 0;
                        v0[] v0VarArr = {xp.k.b(o0Var, null, null, new f(this.f31862d, null), 3, null), xp.k.b(o0Var, null, null, new e(this.f31862d, null), 3, null), xp.k.b(o0Var, null, null, new c(this.f31862d, null), 3, null), xp.k.b(o0Var, null, null, new C0431a(this.f31862d, null), 3, null), xp.k.b(o0Var, null, null, new g(this.f31862d, null), 3, null), xp.k.b(o0Var, null, null, new C0433h(this.f31862d, null), 3, null), xp.k.b(o0Var, null, null, new d(this.f31862d, null), 3, null), xp.k.b(o0Var, null, null, new C0432b(this.f31862d, null), 3, null)};
                        this.f31861c = o0Var;
                        this.f31859a = jCurrentTimeMillis;
                        this.f31860b = 1;
                        objA = xp.f.a(v0VarArr, this);
                        if (objA == objE) {
                            return objE;
                        }
                        j10 = jCurrentTimeMillis;
                    } else {
                        if (i17 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        j10 = this.f31859a;
                        q.b(obj);
                        objA = obj;
                        i11 = 7;
                        i10 = 6;
                        i12 = 5;
                        i13 = 4;
                        i14 = 3;
                        i15 = 2;
                        i16 = 0;
                    }
                    List list = (List) objA;
                    long jCurrentTimeMillis2 = System.currentTimeMillis();
                    long j11 = jCurrentTimeMillis2 - j10;
                    Map map = (Map) list.get(i16);
                    Map map2 = (Map) list.get(1);
                    Map map3 = (Map) list.get(i15);
                    Map map4 = (Map) list.get(i14);
                    Map map5 = (Map) list.get(i13);
                    Map map6 = (Map) list.get(i12);
                    Map map7 = (Map) list.get(i10);
                    Object obj2 = list.get(i11);
                    lp.n.d(obj2, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.Int, kotlin.collections.Map<kotlin.Int, kotlin.Int>?>");
                    Map map8 = (Map) obj2;
                    Map map9 = (Map) map8.get(kotlin.coroutines.jvm.internal.b.b(lo.b.EnumC0477b.FINANCIAL.b()));
                    Map map10 = (Map) map8.get(kotlin.coroutines.jvm.internal.b.b(lo.b.EnumC0477b.LOAN.b()));
                    Map map11 = (Map) map8.get(kotlin.coroutines.jvm.internal.b.b(lo.b.EnumC0477b.CONFERENCE.b()));
                    Map map12 = (Map) map8.get(kotlin.coroutines.jvm.internal.b.b(lo.b.EnumC0477b.RISK.b()));
                    Map map13 = (Map) map8.get(kotlin.coroutines.jvm.internal.b.b(lo.b.EnumC0477b.SUSPICIOUS.b()));
                    Map map14 = (Map) map8.get(kotlin.coroutines.jvm.internal.b.b(lo.b.EnumC0477b.INDISTINGUISHABLE.b()));
                    long jCurrentTimeMillis3 = System.currentTimeMillis() - jCurrentTimeMillis2;
                    PastFraudData pastFraudData = new PastFraudData(map, map2, map3, map4, map5, map6, map7, map9, map10, map11, map12, map13, map14);
                    try {
                        wo.p.a aVar = wo.p.f45327b;
                        objB = wo.p.b(GsonUtils.b(pastFraudData));
                    } catch (Throwable th2) {
                        wo.p.a aVar2 = wo.p.f45327b;
                        objB = wo.p.b(q.a(th2));
                    }
                    Throwable thD = wo.p.d(objB);
                    if (thD != null) {
                        oo.b.b("FraudDetectionManager", "JSON序列化失败, case " + thD.getMessage());
                        objB = "";
                    }
                    String str = (String) objB;
                    long jCurrentTimeMillis4 = System.currentTimeMillis() - j10;
                    int length = str.length();
                    int[] iArr = this.f31862d;
                    if (iArr == null || (strO = yo.i.O(iArr, null, null, null, 0, null, null, 63, null)) == null) {
                        strO = "null";
                    }
                    oo.b.a("FraudDetectionManager", up.n.p("getPastFraudResult性能统计:\n            |并发等待时间: " + j11 + "ms\n            |数据处理时间: " + jCurrentTimeMillis3 + "ms\n            |总耗时: " + jCurrentTimeMillis4 + "ms\n            |数据大小: " + length + "字符\n            |时间范围: " + strO + "\n            ", null, 1, null));
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("getPastFraudResult: ");
                    sb2.append(str);
                    oo.b.a("FraudDetectionManager", sb2.toString());
                    return str;
                } catch (Exception e10) {
                    oo.b.b("FraudDetectionManager", "getPastFraudResult error: " + e10);
                    return "";
                }
            }

            /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
            /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
            @Override // kp.p
            public final Object invoke(o0 o0Var, bp.f fVar) {
                return ((h) create(o0Var, fVar)).invokeSuspend(w.f45339a);
            }
        }

        static final class i extends kotlin.coroutines.jvm.internal.j implements p {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            int f31887a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ int[] f31888b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ kp.l f31889c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            i(int[] iArr, kp.l lVar, bp.f fVar) {
                super(2, fVar);
                this.f31888b = iArr;
                this.f31889c = lVar;
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final bp.f create(Object obj, bp.f fVar) {
                return new i(this.f31888b, this.f31889c, fVar);
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                Object objE = cp.b.e();
                int i10 = this.f31887a;
                try {
                    if (i10 == 0) {
                        q.b(obj);
                        a aVar = b.f31788a;
                        int[] iArr = this.f31888b;
                        this.f31887a = 1;
                        obj = aVar.g(iArr, this);
                        if (obj == objE) {
                            return objE;
                        }
                    } else {
                        if (i10 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        q.b(obj);
                    }
                } catch (Exception e10) {
                    oo.b.b("FraudDetectionManager", "getPastFraudResultAsync error: " + e10);
                }
                return w.f45339a;
            }

            /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
            /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
            @Override // kp.p
            public final Object invoke(o0 o0Var, bp.f fVar) {
                return ((i) create(o0Var, fVar)).invokeSuspend(w.f45339a);
            }
        }

        static final class j extends kotlin.coroutines.jvm.internal.d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            /* synthetic */ Object f31890a;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            int f31892c;

            j(bp.f fVar) {
                super(fVar);
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                this.f31890a = obj;
                this.f31892c |= Integer.MIN_VALUE;
                return a.this.i(null, this);
            }
        }

        static final class k extends kotlin.coroutines.jvm.internal.j implements p {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            Object f31893a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            long f31894b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            int f31895c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            private /* synthetic */ Object f31896d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ Context f31897e;

            /* JADX INFO: renamed from: ho.b$a$k$a, reason: collision with other inner class name */
            static final class C0434a extends kotlin.coroutines.jvm.internal.j implements p {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                int f31898a;

                C0434a(bp.f fVar) {
                    super(2, fVar);
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final bp.f create(Object obj, bp.f fVar) {
                    return new C0434a(fVar);
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    Object objE = cp.b.e();
                    int i10 = this.f31898a;
                    if (i10 == 0) {
                        q.b(obj);
                        a aVar = b.f31788a;
                        int[] iArrK = aVar.k();
                        int[] iArrE = aVar.e();
                        this.f31898a = 1;
                        obj = aVar.c(iArrK, iArrE, this);
                        if (obj == objE) {
                            return objE;
                        }
                    } else {
                        if (i10 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        q.b(obj);
                    }
                    DurationFraudData durationFraudData = (DurationFraudData) GsonUtils.d((String) obj, DurationFraudData.class);
                    oo.b.a("FraudDetectionManager", "getTrustStatusLocked durationFraudData: " + durationFraudData);
                    return durationFraudData;
                }

                /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
                /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
                @Override // kp.p
                public final Object invoke(o0 o0Var, bp.f fVar) {
                    return ((C0434a) create(o0Var, fVar)).invokeSuspend(w.f45339a);
                }
            }

            /* JADX INFO: renamed from: ho.b$a$k$b, reason: collision with other inner class name */
            static final class C0435b extends kotlin.coroutines.jvm.internal.j implements p {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                int f31899a;

                C0435b(bp.f fVar) {
                    super(2, fVar);
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final bp.f create(Object obj, bp.f fVar) {
                    return new C0435b(fVar);
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    Object objE = cp.b.e();
                    int i10 = this.f31899a;
                    if (i10 == 0) {
                        q.b(obj);
                        a aVar = b.f31788a;
                        int[] iArrK = aVar.k();
                        this.f31899a = 1;
                        obj = aVar.g(iArrK, this);
                        if (obj == objE) {
                            return objE;
                        }
                    } else {
                        if (i10 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        q.b(obj);
                    }
                    PastFraudData pastFraudData = (PastFraudData) GsonUtils.d((String) obj, PastFraudData.class);
                    oo.b.a("FraudDetectionManager", "getTrustStatusLocked pastFraudData: " + pastFraudData);
                    return pastFraudData;
                }

                /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
                /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
                @Override // kp.p
                public final Object invoke(o0 o0Var, bp.f fVar) {
                    return ((C0435b) create(o0Var, fVar)).invokeSuspend(w.f45339a);
                }
            }

            static final class c extends kotlin.coroutines.jvm.internal.j implements p {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                int f31900a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ Context f31901b;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                c(Context context, bp.f fVar) {
                    super(2, fVar);
                    this.f31901b = context;
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final bp.f create(Object obj, bp.f fVar) {
                    return new c(this.f31901b, fVar);
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    cp.b.e();
                    if (this.f31900a != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    q.b(obj);
                    String strI = b.f31788a.f().i(this.f31901b, "score_cloud_config", "score_config");
                    oo.b.a("FraudDetectionManager", "getTrustStatusLocked config: " + strI);
                    ScoreConfigBean scoreConfigBean = (ScoreConfigBean) GsonUtils.d(strI, ScoreConfigBean.class);
                    oo.b.a("FraudDetectionManager", "getTrustStatusLocked scoreConfigBean: " + scoreConfigBean);
                    return scoreConfigBean;
                }

                /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
                /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
                @Override // kp.p
                public final Object invoke(o0 o0Var, bp.f fVar) {
                    return ((c) create(o0Var, fVar)).invokeSuspend(w.f45339a);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            k(Context context, bp.f fVar) {
                super(2, fVar);
                this.f31897e = context;
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final bp.f create(Object obj, bp.f fVar) {
                k kVar = new k(this.f31897e, fVar);
                kVar.f31896d = obj;
                return kVar;
            }

            /* JADX WARN: Code duplicated, block: B:31:0x00c0  */
            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                long jCurrentTimeMillis;
                v0 v0VarB;
                Object objZ;
                v0 v0Var;
                ScoreConfigBean scoreConfigBean;
                Object objZ2;
                PastFraudData pastFraudData;
                Object objZ3;
                PastFraudData pastFraudData2;
                ScoreConfigBean scoreConfigBean2;
                Object objE = cp.b.e();
                int i10 = this.f31895c;
                try {
                    if (i10 == 0) {
                        q.b(obj);
                        o0 o0Var = (o0) this.f31896d;
                        jCurrentTimeMillis = System.currentTimeMillis();
                        v0 v0VarB2 = xp.k.b(o0Var, null, null, new c(this.f31897e, null), 3, null);
                        v0 v0VarB3 = xp.k.b(o0Var, null, null, new C0435b(null), 3, null);
                        v0VarB = xp.k.b(o0Var, null, null, new C0434a(null), 3, null);
                        this.f31896d = v0VarB3;
                        this.f31893a = v0VarB;
                        this.f31894b = jCurrentTimeMillis;
                        this.f31895c = 1;
                        objZ = v0VarB2.Z(this);
                        if (objZ != objE) {
                            v0Var = v0VarB3;
                        }
                        return objE;
                    }
                    if (i10 == 1) {
                        long j10 = this.f31894b;
                        v0 v0Var2 = (v0) this.f31893a;
                        v0Var = (v0) this.f31896d;
                        q.b(obj);
                        jCurrentTimeMillis = j10;
                        v0VarB = v0Var2;
                        objZ = obj;
                    } else {
                        if (i10 == 2) {
                            long j11 = this.f31894b;
                            scoreConfigBean = (ScoreConfigBean) this.f31893a;
                            v0VarB = (v0) this.f31896d;
                            q.b(obj);
                            jCurrentTimeMillis = j11;
                            objZ2 = obj;
                            pastFraudData = (PastFraudData) objZ2;
                            this.f31896d = scoreConfigBean;
                            this.f31893a = pastFraudData;
                            this.f31894b = jCurrentTimeMillis;
                            this.f31895c = 3;
                            objZ3 = v0VarB.Z(this);
                            if (objZ3 != objE) {
                                pastFraudData2 = pastFraudData;
                                scoreConfigBean2 = scoreConfigBean;
                            }
                            return objE;
                        }
                        if (i10 != 3) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        long j12 = this.f31894b;
                        pastFraudData2 = (PastFraudData) this.f31893a;
                        scoreConfigBean2 = (ScoreConfigBean) this.f31896d;
                        q.b(obj);
                        jCurrentTimeMillis = j12;
                        objZ3 = obj;
                    }
                    float fJ = b.f31788a.f().j(scoreConfigBean2, pastFraudData2, (DurationFraudData) objZ3);
                    oo.b.a("FraudDetectionManager", "getScoreResult score: " + fJ);
                    oo.b.a("FraudDetectionManager", "getScoreResult cost time: " + (System.currentTimeMillis() - jCurrentTimeMillis));
                    return GsonUtils.b(new FraudDetectionScore(String.valueOf(fJ)));
                    scoreConfigBean = (ScoreConfigBean) objZ;
                    this.f31896d = v0VarB;
                    this.f31893a = scoreConfigBean;
                    this.f31894b = jCurrentTimeMillis;
                    this.f31895c = 2;
                    objZ2 = v0Var.Z(this);
                    if (objZ2 != objE) {
                        pastFraudData = (PastFraudData) objZ2;
                        this.f31896d = scoreConfigBean;
                        this.f31893a = pastFraudData;
                        this.f31894b = jCurrentTimeMillis;
                        this.f31895c = 3;
                        objZ3 = v0VarB.Z(this);
                        if (objZ3 != objE) {
                            pastFraudData2 = pastFraudData;
                            scoreConfigBean2 = scoreConfigBean;
                            float fJ2 = b.f31788a.f().j(scoreConfigBean2, pastFraudData2, (DurationFraudData) objZ3);
                            oo.b.a("FraudDetectionManager", "getScoreResult score: " + fJ2);
                            oo.b.a("FraudDetectionManager", "getScoreResult cost time: " + (System.currentTimeMillis() - jCurrentTimeMillis));
                            return GsonUtils.b(new FraudDetectionScore(String.valueOf(fJ2)));
                        }
                    }
                    return objE;
                } catch (Exception e10) {
                    oo.b.b("FraudDetectionManager", "getScoreResult error: " + e10);
                    return "";
                }
            }

            /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
            /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
            @Override // kp.p
            public final Object invoke(o0 o0Var, bp.f fVar) {
                return ((k) create(o0Var, fVar)).invokeSuspend(w.f45339a);
            }
        }

        static final class l extends kotlin.coroutines.jvm.internal.j implements p {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            int f31902a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Context f31903b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ kp.l f31904c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            l(Context context, kp.l lVar, bp.f fVar) {
                super(2, fVar);
                this.f31903b = context;
                this.f31904c = lVar;
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final bp.f create(Object obj, bp.f fVar) {
                return new l(this.f31903b, this.f31904c, fVar);
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                Object objE = cp.b.e();
                int i10 = this.f31902a;
                try {
                    if (i10 == 0) {
                        q.b(obj);
                        a aVar = b.f31788a;
                        Context context = this.f31903b;
                        this.f31902a = 1;
                        obj = aVar.i(context, this);
                        if (obj == objE) {
                            return objE;
                        }
                    } else {
                        if (i10 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        q.b(obj);
                    }
                } catch (Exception e10) {
                    oo.b.b("FraudDetectionManager", "getScoreResultAsync error: " + e10);
                }
                return w.f45339a;
            }

            /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
            /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
            @Override // kp.p
            public final Object invoke(o0 o0Var, bp.f fVar) {
                return ((l) create(o0Var, fVar)).invokeSuspend(w.f45339a);
            }
        }

        /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x0000: CONSTRUCTOR  A[MD:():void (m)] (LINE:1) call: ho.b.a.<init>():void type: THIS */
        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
        public /* synthetic */ a(lp.h hVar) {
            this();
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0013  */
        public final Object a(bp.f fVar) {
            C0424a c0424a;
            if (fVar instanceof C0424a) {
                c0424a = (C0424a) fVar;
                int i10 = c0424a.f31794c;
                if ((i10 & Integer.MIN_VALUE) != 0) {
                    c0424a.f31794c = i10 - Integer.MIN_VALUE;
                } else {
                    c0424a = new C0424a(fVar);
                }
            } else {
                c0424a = new C0424a(fVar);
            }
            Object objE = c0424a.f31792a;
            Object objE2 = cp.b.e();
            int i11 = c0424a.f31794c;
            if (i11 == 0) {
                q.b(objE);
                C0425b c0425b = new C0425b(null);
                c0424a.f31794c = 1;
                objE = p0.e(c0425b, c0424a);
                if (objE == objE2) {
                    return objE2;
                }
            } else {
                if (i11 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                q.b(objE);
            }
            lp.n.e(objE, "coroutineScope(...)");
            return objE;
        }

        public final void b(kp.l lVar) {
            lp.n.f(lVar, "callback");
            xp.k.d(p0.a(d1.b()), null, null, new c(lVar, null), 3, null);
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0013  */
        public final Object c(int[] iArr, int[] iArr2, bp.f fVar) {
            d dVar;
            if (fVar instanceof d) {
                dVar = (d) fVar;
                int i10 = dVar.f31818c;
                if ((i10 & Integer.MIN_VALUE) != 0) {
                    dVar.f31818c = i10 - Integer.MIN_VALUE;
                } else {
                    dVar = new d(fVar);
                }
            } else {
                dVar = new d(fVar);
            }
            Object objE = dVar.f31816a;
            Object objE2 = cp.b.e();
            int i11 = dVar.f31818c;
            if (i11 == 0) {
                q.b(objE);
                e eVar = new e(iArr, iArr2, null);
                dVar.f31818c = 1;
                objE = p0.e(eVar, dVar);
                if (objE == objE2) {
                    return objE2;
                }
            } else {
                if (i11 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                q.b(objE);
            }
            lp.n.e(objE, "coroutineScope(...)");
            return objE;
        }

        public final void d(int[] iArr, int[] iArr2, kp.l lVar) {
            lp.n.f(lVar, "callback");
            xp.k.d(p0.a(d1.b()), null, null, new f(iArr, iArr2, lVar, null), 3, null);
        }

        public final int[] e() {
            return b.f31791d;
        }

        public final b f() {
            return (b) b.f31789b.getValue();
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0013  */
        public final Object g(int[] iArr, bp.f fVar) {
            g gVar;
            if (fVar instanceof g) {
                gVar = (g) fVar;
                int i10 = gVar.f31858c;
                if ((i10 & Integer.MIN_VALUE) != 0) {
                    gVar.f31858c = i10 - Integer.MIN_VALUE;
                } else {
                    gVar = new g(fVar);
                }
            } else {
                gVar = new g(fVar);
            }
            Object objE = gVar.f31856a;
            Object objE2 = cp.b.e();
            int i11 = gVar.f31858c;
            if (i11 == 0) {
                q.b(objE);
                h hVar = new h(iArr, null);
                gVar.f31858c = 1;
                objE = p0.e(hVar, gVar);
                if (objE == objE2) {
                    return objE2;
                }
            } else {
                if (i11 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                q.b(objE);
            }
            lp.n.e(objE, "coroutineScope(...)");
            return objE;
        }

        public final void h(int[] iArr, kp.l lVar) {
            lp.n.f(lVar, "callback");
            xp.k.d(p0.a(d1.b()), null, null, new i(iArr, lVar, null), 3, null);
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0013  */
        public final Object i(Context context, bp.f fVar) {
            j jVar;
            if (fVar instanceof j) {
                jVar = (j) fVar;
                int i10 = jVar.f31892c;
                if ((i10 & Integer.MIN_VALUE) != 0) {
                    jVar.f31892c = i10 - Integer.MIN_VALUE;
                } else {
                    jVar = new j(fVar);
                }
            } else {
                jVar = new j(fVar);
            }
            Object objE = jVar.f31890a;
            Object objE2 = cp.b.e();
            int i11 = jVar.f31892c;
            if (i11 == 0) {
                q.b(objE);
                k kVar = new k(context, null);
                jVar.f31892c = 1;
                objE = p0.e(kVar, jVar);
                if (objE == objE2) {
                    return objE2;
                }
            } else {
                if (i11 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                q.b(objE);
            }
            lp.n.e(objE, "coroutineScope(...)");
            return objE;
        }

        public final void j(Context context, kp.l lVar) {
            lp.n.f(context, "mContext");
            lp.n.f(lVar, "callback");
            xp.k.d(p0.a(d1.b()), null, null, new l(context, lVar, null), 3, null);
        }

        public final int[] k() {
            return b.f31790c;
        }

        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
        private a() {
        }
    }

    private b() {
    }

    public static final void e(kp.l lVar) {
        f31788a.b(lVar);
    }

    public static final void f(int[] iArr, int[] iArr2, kp.l lVar) {
        f31788a.d(iArr, iArr2, lVar);
    }

    public static final void h(int[] iArr, kp.l lVar) {
        f31788a.h(iArr, lVar);
    }

    public static final void k(Context context, kp.l lVar) {
        f31788a.j(context, lVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final b l() {
        return new b();
    }

    public final byte[] g() {
        return new byte[]{0, 0};
    }

    public final String i(Context context, String str, String str2) {
        lp.n.f(context, "context");
        lp.n.f(str, "key");
        lp.n.f(str2, "module");
        String strA = lo.a.a(context.getContentResolver(), str, str2, null);
        Log.i("FraudDetectionManager", "cloud config data is empty: " + TextUtils.isEmpty(strA));
        return TextUtils.isEmpty(strA) ? c.a(context, "score_config.json") : strA;
    }

    public final float j(ScoreConfigBean scoreConfigBean, PastFraudData pastFraudData, DurationFraudData durationFraudData) {
        Map<Integer, Map<Integer, Integer>> durationMap;
        Map<Integer, Integer> mapH;
        Map<Integer, Integer> eventMap;
        Integer num;
        double d10 = 0.0d;
        if (scoreConfigBean != null) {
            try {
                for (ScoreConfigBean.TimeWindowsBean timeWindowsBean : scoreConfigBean.getTimeWindows()) {
                    int timeWindow = timeWindowsBean.getTimeWindow();
                    double wight = timeWindowsBean.getWight();
                    oo.b.a("FraudDetectionManager", ">>>>>>>>>>>  getScoreResult timeWindow: " + timeWindow + ", weight: " + wight);
                    float score = 0.0f;
                    for (ScoreConfigBean.EventListBean eventListBean : scoreConfigBean.getEventList()) {
                        String eventName = eventListBean.getEventName();
                        int eventScore = eventListBean.getEventScore();
                        if (pastFraudData != null) {
                            lp.n.c(eventName);
                            eventMap = pastFraudData.getEventMap(eventName);
                        } else {
                            eventMap = null;
                        }
                        oo.b.a("FraudDetectionManager", "getScoreResult pastEvent: " + eventName + ", windowScore " + score + ", eventScore: " + eventScore + ", eventMap " + eventMap);
                        if (eventMap != null && (num = eventMap.get(Integer.valueOf(timeWindow))) != null && num.intValue() == 1) {
                            score += eventScore;
                            oo.b.a("FraudDetectionManager", "++++++++++ getScoreResult add windowScore 1: " + score);
                        }
                        pastFraudData = pastFraudData;
                    }
                    Iterator<ScoreConfigBean.EventTimeListBean> it = scoreConfigBean.getEventTimeList().iterator();
                    while (it.hasNext()) {
                        ScoreConfigBean.EventTimeListBean next = it.next();
                        String eventName2 = next.getEventName();
                        if (durationFraudData != null) {
                            lp.n.c(eventName2);
                            durationMap = durationFraudData.getDurationMap(eventName2);
                        } else {
                            durationMap = null;
                        }
                        if (durationMap == null || (mapH = durationMap.get(Integer.valueOf(timeWindow))) == null) {
                            mapH = f0.h();
                        }
                        StringBuilder sb2 = new StringBuilder();
                        Iterator<ScoreConfigBean.EventTimeListBean> it2 = it;
                        sb2.append("getScoreResult durationEvent: ");
                        sb2.append(eventName2);
                        sb2.append(", durationMap ");
                        sb2.append(durationMap);
                        sb2.append(", timeWindowDurationMap: ");
                        sb2.append(mapH);
                        oo.b.a("FraudDetectionManager", sb2.toString());
                        Iterator<ScoreConfigBean.EventTimeListBean.EventTimeScoreBean> it3 = next.getEventTimeScore().iterator();
                        while (it3.hasNext()) {
                            ScoreConfigBean.EventTimeListBean.EventTimeScoreBean next2 = it3.next();
                            int time = next2.getTime();
                            int score2 = next2.getScore();
                            StringBuilder sb3 = new StringBuilder();
                            Iterator<ScoreConfigBean.EventTimeListBean.EventTimeScoreBean> it4 = it3;
                            sb3.append("getScoreResult durationEvent:, durationType: ");
                            sb3.append(time);
                            sb3.append(", durationScore: ");
                            sb3.append(score2);
                            sb3.append(" windowScore: ");
                            sb3.append(score);
                            oo.b.a("FraudDetectionManager", sb3.toString());
                            Integer num2 = mapH.get(Integer.valueOf(next2.getTime()));
                            if (num2 != null && num2.intValue() == 1) {
                                score += next2.getScore();
                                oo.b.a("FraudDetectionManager", "++++++++ getScoreResult add windowScore 2: " + score);
                            }
                            it3 = it4;
                        }
                        it = it2;
                    }
                    d10 += ((double) score) * wight;
                    oo.b.a("FraudDetectionManager", "<<<<<<<<<<<  getScoreResult totalScore: " + d10 + ", windowScore " + score + ",windowWeight " + wight + " ");
                }
            } catch (Exception e10) {
                oo.b.b("FraudDetectionManager", "getScoreResult error: " + e10);
            }
        }
        return (float) d10;
    }
}
