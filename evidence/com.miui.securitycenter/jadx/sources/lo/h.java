package lo;

import android.content.Context;
import android.telephony.TelephonyManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import miui.os.Build;
import miui.yellowpage.YellowPageUtils;
import wo.w;
import xp.d1;
import xp.o0;
import yo.o;

/* JADX INFO: loaded from: classes4.dex */
public abstract class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f34704a = new a(null);

    public static final class a {

        /* JADX INFO: renamed from: lo.h$a$a, reason: collision with other inner class name */
        public static final class C0478a implements io.g {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ int[] f34705a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ int[] f34706b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ xp.n f34707c;

            C0478a(int[] iArr, int[] iArr2, xp.n nVar) {
                this.f34705a = iArr;
                this.f34706b = iArr2;
                this.f34707c = nVar;
            }

            /* JADX DEBUG: Method merged with bridge method: onSuccess(Ljava/lang/Object;)V */
            @Override // io.g
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List list) {
                if (list != null) {
                    this.f34707c.resumeWith(wo.p.b(a.m(h.f34704a, list, io.e.ScreenShareInfo, this.f34705a, this.f34706b, 0L, 16, null)));
                }
            }
        }

        public static final class b implements io.g {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ io.e f34708a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ int[] f34709b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ int[] f34710c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ xp.n f34711d;

            b(io.e eVar, int[] iArr, int[] iArr2, xp.n nVar) {
                this.f34708a = eVar;
                this.f34709b = iArr;
                this.f34710c = iArr2;
                this.f34711d = nVar;
            }

            /* JADX DEBUG: Method merged with bridge method: onSuccess(Ljava/lang/Object;)V */
            @Override // io.g
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List list) {
                if (list != null) {
                    this.f34711d.resumeWith(wo.p.b(a.m(h.f34704a, list, this.f34708a, this.f34709b, this.f34710c, 0L, 16, null)));
                }
            }
        }

        public static final class c implements io.g {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ int[] f34712a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ int[] f34713b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ xp.n f34714c;

            c(int[] iArr, int[] iArr2, xp.n nVar) {
                this.f34712a = iArr;
                this.f34713b = iArr2;
                this.f34714c = nVar;
            }

            /* JADX DEBUG: Method merged with bridge method: onSuccess(Ljava/lang/Object;)V */
            @Override // io.g
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List list) {
                if (list != null) {
                    this.f34714c.resumeWith(wo.p.b(a.m(h.f34704a, list, io.e.VoipRecordInfo, this.f34712a, this.f34713b, 0L, 16, null)));
                }
            }
        }

        public static final class d implements io.g {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ int[] f34715a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ xp.n f34716b;

            d(int[] iArr, xp.n nVar) {
                this.f34715a = iArr;
                this.f34716b = nVar;
            }

            /* JADX DEBUG: Method merged with bridge method: onSuccess(Ljava/lang/Object;)V */
            @Override // io.g
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List list) {
                if (list != null) {
                    this.f34716b.resumeWith(wo.p.b(a.p(h.f34704a, list, io.e.FraudUrlInfo, this.f34715a, 0L, 8, null)));
                }
            }
        }

        public static final class e implements io.g {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ int[] f34717a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ xp.n f34718b;

            e(int[] iArr, xp.n nVar) {
                this.f34717a = iArr;
                this.f34718b = nVar;
            }

            /* JADX DEBUG: Method merged with bridge method: onSuccess(Ljava/lang/Object;)V */
            @Override // io.g
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List list) {
                if (list != null) {
                    int[] iArr = this.f34717a;
                    xp.n nVar = this.f34718b;
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    ArrayList arrayList3 = new ArrayList();
                    ArrayList arrayList4 = new ArrayList();
                    ArrayList arrayList5 = new ArrayList();
                    ArrayList arrayList6 = new ArrayList();
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        io.b bVar = (io.b) it.next();
                        Integer numI = bVar.i();
                        int iB = lo.b.EnumC0477b.FINANCIAL.b();
                        if (numI != null && numI.intValue() == iB) {
                            arrayList.add(bVar);
                        } else {
                            int iB2 = lo.b.EnumC0477b.LOAN.b();
                            if (numI != null && numI.intValue() == iB2) {
                                arrayList2.add(bVar);
                            } else {
                                int iB3 = lo.b.EnumC0477b.CONFERENCE.b();
                                if (numI != null && numI.intValue() == iB3) {
                                    arrayList3.add(bVar);
                                } else {
                                    int iB4 = lo.b.EnumC0477b.RISK.b();
                                    if (numI != null && numI.intValue() == iB4) {
                                        arrayList4.add(bVar);
                                    } else {
                                        int iB5 = lo.b.EnumC0477b.SUSPICIOUS.b();
                                        if (numI != null && numI.intValue() == iB5) {
                                            arrayList5.add(bVar);
                                        } else {
                                            int iB6 = lo.b.EnumC0477b.INDISTINGUISHABLE.b();
                                            if (numI != null && numI.intValue() == iB6) {
                                                arrayList6.add(bVar);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    a aVar = h.f34704a;
                    io.e eVar = io.e.AppInfo;
                    Map mapP = a.p(aVar, arrayList, eVar, iArr, 0L, 8, null);
                    Map mapP2 = a.p(aVar, arrayList2, eVar, iArr, 0L, 8, null);
                    Map mapP3 = a.p(aVar, arrayList3, eVar, iArr, 0L, 8, null);
                    Map mapP4 = a.p(aVar, arrayList4, eVar, iArr, 0L, 8, null);
                    Map mapP5 = a.p(aVar, arrayList5, eVar, iArr, 0L, 8, null);
                    Map mapP6 = a.p(aVar, arrayList6, eVar, iArr, 0L, 8, null);
                    linkedHashMap.put(Integer.valueOf(lo.b.EnumC0477b.FINANCIAL.b()), mapP);
                    linkedHashMap.put(Integer.valueOf(lo.b.EnumC0477b.LOAN.b()), mapP2);
                    linkedHashMap.put(Integer.valueOf(lo.b.EnumC0477b.CONFERENCE.b()), mapP3);
                    linkedHashMap.put(Integer.valueOf(lo.b.EnumC0477b.RISK.b()), mapP4);
                    linkedHashMap.put(Integer.valueOf(lo.b.EnumC0477b.SUSPICIOUS.b()), mapP5);
                    linkedHashMap.put(Integer.valueOf(lo.b.EnumC0477b.INDISTINGUISHABLE.b()), mapP6);
                    nVar.resumeWith(wo.p.b(linkedHashMap));
                }
            }
        }

        public static final class f implements io.g {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ int[] f34719a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ xp.n f34720b;

            f(int[] iArr, xp.n nVar) {
                this.f34719a = iArr;
                this.f34720b = nVar;
            }

            /* JADX DEBUG: Method merged with bridge method: onSuccess(Ljava/lang/Object;)V */
            @Override // io.g
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List list) {
                if (list != null) {
                    this.f34720b.resumeWith(wo.p.b(a.p(h.f34704a, list, io.e.AirplaneModeInfo, this.f34719a, 0L, 8, null)));
                }
            }
        }

        public static final class g implements io.g {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ int[] f34721a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ xp.n f34722b;

            g(int[] iArr, xp.n nVar) {
                this.f34721a = iArr;
                this.f34722b = nVar;
            }

            /* JADX DEBUG: Method merged with bridge method: onSuccess(Ljava/lang/Object;)V */
            @Override // io.g
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List list) {
                if (list != null) {
                    this.f34722b.resumeWith(wo.p.b(a.p(h.f34704a, list, io.e.CallForwardingInfo, this.f34721a, 0L, 8, null)));
                }
            }
        }

        /* JADX INFO: renamed from: lo.h$a$h, reason: collision with other inner class name */
        public static final class C0479h implements io.g {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ int[] f34723a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ xp.n f34724b;

            C0479h(int[] iArr, xp.n nVar) {
                this.f34723a = iArr;
                this.f34724b = nVar;
            }

            /* JADX DEBUG: Method merged with bridge method: onSuccess(Ljava/lang/Object;)V */
            @Override // io.g
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List list) {
                if (list != null) {
                    this.f34724b.resumeWith(wo.p.b(a.p(h.f34704a, list, io.e.ScreenShareInfo, this.f34723a, 0L, 8, null)));
                }
            }
        }

        public static final class i implements io.g {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ int[] f34725a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ xp.n f34726b;

            i(int[] iArr, xp.n nVar) {
                this.f34725a = iArr;
                this.f34726b = nVar;
            }

            /* JADX DEBUG: Method merged with bridge method: onSuccess(Ljava/lang/Object;)V */
            @Override // io.g
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List list) {
                if (list != null) {
                    this.f34726b.resumeWith(wo.p.b(a.p(h.f34704a, list, io.e.ScreenshotInfo, this.f34725a, 0L, 8, null)));
                }
            }
        }

        public static final class j implements io.g {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ int[] f34727a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ xp.n f34728b;

            j(int[] iArr, xp.n nVar) {
                this.f34727a = iArr;
                this.f34728b = nVar;
            }

            /* JADX DEBUG: Method merged with bridge method: onSuccess(Ljava/lang/Object;)V */
            @Override // io.g
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List list) {
                if (list != null) {
                    this.f34728b.resumeWith(wo.p.b(a.p(h.f34704a, list, io.e.SimCardFraudInfo, this.f34727a, 0L, 8, null)));
                }
            }
        }

        public static final class k implements io.g {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ int[] f34729a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ xp.n f34730b;

            k(int[] iArr, xp.n nVar) {
                this.f34729a = iArr;
                this.f34730b = nVar;
            }

            /* JADX DEBUG: Method merged with bridge method: onSuccess(Ljava/lang/Object;)V */
            @Override // io.g
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List list) {
                if (list != null) {
                    this.f34730b.resumeWith(wo.p.b(a.p(h.f34704a, list, io.e.VoipRecordInfo, this.f34729a, 0L, 8, null)));
                }
            }
        }

        static final class l extends kotlin.coroutines.jvm.internal.j implements kp.p {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            int f34731a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ int[] f34732b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ int[] f34733c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            l(int[] iArr, int[] iArr2, bp.f fVar) {
                super(2, fVar);
                this.f34732b = iArr;
                this.f34733c = iArr2;
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final bp.f create(Object obj, bp.f fVar) {
                return new l(this.f34732b, this.f34733c, fVar);
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                Object objE = cp.b.e();
                int i10 = this.f34731a;
                if (i10 == 0) {
                    wo.q.b(obj);
                    a aVar = h.f34704a;
                    int[] iArr = this.f34732b;
                    int[] iArr2 = this.f34733c;
                    this.f34731a = 1;
                    obj = aVar.q(iArr, iArr2, this);
                    if (obj == objE) {
                        return objE;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    wo.q.b(obj);
                }
                Map map = (Map) obj;
                oo.b.a("PastFraudUtil", "getDurationScreenShareResult: " + map);
                return map;
            }

            /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
            /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
            @Override // kp.p
            public final Object invoke(o0 o0Var, bp.f fVar) {
                return ((l) create(o0Var, fVar)).invokeSuspend(w.f45339a);
            }
        }

        static final class m extends kotlin.coroutines.jvm.internal.j implements kp.p {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            int f34734a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ io.l f34735b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ io.e f34736c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ int[] f34737d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ int[] f34738e;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            m(io.l lVar, io.e eVar, int[] iArr, int[] iArr2, bp.f fVar) {
                super(2, fVar);
                this.f34735b = lVar;
                this.f34736c = eVar;
                this.f34737d = iArr;
                this.f34738e = iArr2;
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final bp.f create(Object obj, bp.f fVar) {
                return new m(this.f34735b, this.f34736c, this.f34737d, this.f34738e, fVar);
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                Object objE = cp.b.e();
                int i10 = this.f34734a;
                if (i10 == 0) {
                    wo.q.b(obj);
                    a aVar = h.f34704a;
                    io.l lVar = this.f34735b;
                    io.e eVar = this.f34736c;
                    int[] iArr = this.f34737d;
                    int[] iArr2 = this.f34738e;
                    this.f34734a = 1;
                    obj = aVar.r(lVar, eVar, iArr, iArr2, this);
                    if (obj == objE) {
                        return objE;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    wo.q.b(obj);
                }
                Map map = (Map) obj;
                oo.b.a("PastFraudUtil", "getDurationSimCardCallResult: " + map);
                return map;
            }

            /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
            /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
            @Override // kp.p
            public final Object invoke(o0 o0Var, bp.f fVar) {
                return ((m) create(o0Var, fVar)).invokeSuspend(w.f45339a);
            }
        }

        static final class n extends kotlin.coroutines.jvm.internal.j implements kp.p {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            int f34739a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ io.o f34740b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ int[] f34741c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ int[] f34742d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            n(io.o oVar, int[] iArr, int[] iArr2, bp.f fVar) {
                super(2, fVar);
                this.f34740b = oVar;
                this.f34741c = iArr;
                this.f34742d = iArr2;
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final bp.f create(Object obj, bp.f fVar) {
                return new n(this.f34740b, this.f34741c, this.f34742d, fVar);
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                Object objE = cp.b.e();
                int i10 = this.f34739a;
                if (i10 == 0) {
                    wo.q.b(obj);
                    a aVar = h.f34704a;
                    io.o oVar = this.f34740b;
                    int[] iArr = this.f34741c;
                    int[] iArr2 = this.f34742d;
                    this.f34739a = 1;
                    obj = aVar.s(oVar, iArr, iArr2, this);
                    if (obj == objE) {
                        return objE;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    wo.q.b(obj);
                }
                Map map = (Map) obj;
                oo.b.a("PastFraudUtil", "getDurationVoipResult: " + map);
                return map;
            }

            /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
            /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
            @Override // kp.p
            public final Object invoke(o0 o0Var, bp.f fVar) {
                return ((n) create(o0Var, fVar)).invokeSuspend(w.f45339a);
            }
        }

        static final class o extends kotlin.coroutines.jvm.internal.j implements kp.p {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            int f34743a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ int[] f34744b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            o(int[] iArr, bp.f fVar) {
                super(2, fVar);
                this.f34744b = iArr;
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final bp.f create(Object obj, bp.f fVar) {
                return new o(this.f34744b, fVar);
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                Object objE = cp.b.e();
                int i10 = this.f34743a;
                if (i10 == 0) {
                    wo.q.b(obj);
                    oo.b.a("PastFraudUtil", "getPastAirplaneModeResult---> start");
                    a aVar = h.f34704a;
                    int[] iArr = this.f34744b;
                    this.f34743a = 1;
                    obj = aVar.v(iArr, this);
                    if (obj == objE) {
                        return objE;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    wo.q.b(obj);
                }
                Map map = (Map) obj;
                oo.b.a("PastFraudUtil", "getPastAirplaneModeResult end : " + map);
                return map;
            }

            /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
            /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
            @Override // kp.p
            public final Object invoke(o0 o0Var, bp.f fVar) {
                return ((o) create(o0Var, fVar)).invokeSuspend(w.f45339a);
            }
        }

        static final class p extends kotlin.coroutines.jvm.internal.j implements kp.p {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            int f34745a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ int[] f34746b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            p(int[] iArr, bp.f fVar) {
                super(2, fVar);
                this.f34746b = iArr;
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final bp.f create(Object obj, bp.f fVar) {
                return new p(this.f34746b, fVar);
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                Object objE = cp.b.e();
                int i10 = this.f34745a;
                if (i10 == 0) {
                    wo.q.b(obj);
                    oo.b.a("PastFraudUtil", "getPastCallForwardingResult---> start");
                    a aVar = h.f34704a;
                    int[] iArr = this.f34746b;
                    this.f34745a = 1;
                    obj = aVar.w(iArr, this);
                    if (obj == objE) {
                        return objE;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    wo.q.b(obj);
                }
                Map map = (Map) obj;
                oo.b.a("PastFraudUtil", "getPastCallForwardingResult: " + map);
                return map;
            }

            /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
            /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
            @Override // kp.p
            public final Object invoke(o0 o0Var, bp.f fVar) {
                return ((p) create(o0Var, fVar)).invokeSuspend(w.f45339a);
            }
        }

        static final class q extends kotlin.coroutines.jvm.internal.j implements kp.p {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            int f34747a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ int[] f34748b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            q(int[] iArr, bp.f fVar) {
                super(2, fVar);
                this.f34748b = iArr;
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final bp.f create(Object obj, bp.f fVar) {
                return new q(this.f34748b, fVar);
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                Object objE = cp.b.e();
                int i10 = this.f34747a;
                if (i10 == 0) {
                    wo.q.b(obj);
                    oo.b.a("PastFraudUtil", "getPastFraudUrlResult---> start");
                    a aVar = h.f34704a;
                    int[] iArr = this.f34748b;
                    this.f34747a = 1;
                    obj = aVar.t(iArr, this);
                    if (obj == objE) {
                        return objE;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    wo.q.b(obj);
                }
                Map map = (Map) obj;
                oo.b.a("PastFraudUtil", "getPastFraudUrlResult end : " + map);
                return map;
            }

            /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
            /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
            @Override // kp.p
            public final Object invoke(o0 o0Var, bp.f fVar) {
                return ((q) create(o0Var, fVar)).invokeSuspend(w.f45339a);
            }
        }

        static final class r extends kotlin.coroutines.jvm.internal.j implements kp.p {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            int f34749a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ int[] f34750b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            r(int[] iArr, bp.f fVar) {
                super(2, fVar);
                this.f34750b = iArr;
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final bp.f create(Object obj, bp.f fVar) {
                return new r(this.f34750b, fVar);
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                Object objE = cp.b.e();
                int i10 = this.f34749a;
                if (i10 == 0) {
                    wo.q.b(obj);
                    oo.b.a("PastFraudUtil", "getPastInstallAppResult---> start");
                    a aVar = h.f34704a;
                    int[] iArr = this.f34750b;
                    this.f34749a = 1;
                    obj = aVar.u(iArr, this);
                    if (obj == objE) {
                        return objE;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    wo.q.b(obj);
                }
                Map map = (Map) obj;
                oo.b.a("PastFraudUtil", "getPastInstallAppResult end : " + map);
                return map;
            }

            /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
            /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
            @Override // kp.p
            public final Object invoke(o0 o0Var, bp.f fVar) {
                return ((r) create(o0Var, fVar)).invokeSuspend(w.f45339a);
            }
        }

        static final class s extends kotlin.coroutines.jvm.internal.j implements kp.p {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            int f34751a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ int[] f34752b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            s(int[] iArr, bp.f fVar) {
                super(2, fVar);
                this.f34752b = iArr;
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final bp.f create(Object obj, bp.f fVar) {
                return new s(this.f34752b, fVar);
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                Object objE = cp.b.e();
                int i10 = this.f34751a;
                if (i10 == 0) {
                    wo.q.b(obj);
                    a aVar = h.f34704a;
                    int[] iArr = this.f34752b;
                    this.f34751a = 1;
                    obj = aVar.x(iArr, this);
                    if (obj == objE) {
                        return objE;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    wo.q.b(obj);
                }
                Map map = (Map) obj;
                oo.b.a("PastFraudUtil", "getPastScreenShareResult: " + map);
                return map;
            }

            /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
            /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
            @Override // kp.p
            public final Object invoke(o0 o0Var, bp.f fVar) {
                return ((s) create(o0Var, fVar)).invokeSuspend(w.f45339a);
            }
        }

        static final class t extends kotlin.coroutines.jvm.internal.j implements kp.p {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            int f34753a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ int[] f34754b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            t(int[] iArr, bp.f fVar) {
                super(2, fVar);
                this.f34754b = iArr;
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final bp.f create(Object obj, bp.f fVar) {
                return new t(this.f34754b, fVar);
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                Object objE = cp.b.e();
                int i10 = this.f34753a;
                if (i10 == 0) {
                    wo.q.b(obj);
                    a aVar = h.f34704a;
                    int[] iArr = this.f34754b;
                    this.f34753a = 1;
                    obj = aVar.y(iArr, this);
                    if (obj == objE) {
                        return objE;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    wo.q.b(obj);
                }
                Map map = (Map) obj;
                oo.b.a("PastFraudUtil", "getPastScreenshotResult: " + map);
                return map;
            }

            /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
            /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
            @Override // kp.p
            public final Object invoke(o0 o0Var, bp.f fVar) {
                return ((t) create(o0Var, fVar)).invokeSuspend(w.f45339a);
            }
        }

        static final class u extends kotlin.coroutines.jvm.internal.j implements kp.p {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            int f34755a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ io.l f34756b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ int[] f34757c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            u(io.l lVar, int[] iArr, bp.f fVar) {
                super(2, fVar);
                this.f34756b = lVar;
                this.f34757c = iArr;
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final bp.f create(Object obj, bp.f fVar) {
                return new u(this.f34756b, this.f34757c, fVar);
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                Object objE = cp.b.e();
                int i10 = this.f34755a;
                if (i10 == 0) {
                    wo.q.b(obj);
                    oo.b.a("PastFraudUtil", "getPastSimCardFraudCallResult---> start");
                    a aVar = h.f34704a;
                    io.l lVar = this.f34756b;
                    int[] iArr = this.f34757c;
                    this.f34755a = 1;
                    obj = aVar.z(lVar, iArr, this);
                    if (obj == objE) {
                        return objE;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    wo.q.b(obj);
                }
                Map map = (Map) obj;
                oo.b.a("PastFraudUtil", "getPastSimCardFraudCallResult end : " + map);
                return map;
            }

            /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
            /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
            @Override // kp.p
            public final Object invoke(o0 o0Var, bp.f fVar) {
                return ((u) create(o0Var, fVar)).invokeSuspend(w.f45339a);
            }
        }

        static final class v extends kotlin.coroutines.jvm.internal.j implements kp.p {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            int f34758a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ int[] f34759b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            v(int[] iArr, bp.f fVar) {
                super(2, fVar);
                this.f34759b = iArr;
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final bp.f create(Object obj, bp.f fVar) {
                return new v(this.f34759b, fVar);
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                Object objE = cp.b.e();
                int i10 = this.f34758a;
                if (i10 == 0) {
                    wo.q.b(obj);
                    oo.b.a("PastFraudUtil", "getPastVoipFraudCallResult---> start");
                    a aVar = h.f34704a;
                    int[] iArr = this.f34759b;
                    this.f34758a = 1;
                    obj = aVar.A(iArr, this);
                    if (obj == objE) {
                        return objE;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    wo.q.b(obj);
                }
                Map map = (Map) obj;
                oo.b.a("PastFraudUtil", "getPastVoipFraudCallResult end : " + map);
                return map;
            }

            /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
            /* JADX DEBUG: Method merged with bridge method: invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; */
            @Override // kp.p
            public final Object invoke(o0 o0Var, bp.f fVar) {
                return ((v) create(o0Var, fVar)).invokeSuspend(w.f45339a);
            }
        }

        /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x0000: CONSTRUCTOR  A[MD:():void (m)] (LINE:1) call: lo.h.a.<init>():void type: THIS */
        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
        public /* synthetic */ a(lp.h hVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Object A(int[] iArr, bp.f fVar) {
            xp.p pVar = new xp.p(cp.b.c(fVar), 1);
            pVar.G();
            jo.k.f33544c.a().X1(new k(iArr, pVar), io.o.TYPE_FRAUD_CALL);
            Object objA = pVar.A();
            if (objA == cp.b.e()) {
                kotlin.coroutines.jvm.internal.g.c(fVar);
            }
            return objA;
        }

        public static /* synthetic */ Map m(a aVar, List list, io.e eVar, int[] iArr, int[] iArr2, long j10, int i10, Object obj) {
            if ((i10 & 16) != 0) {
                j10 = System.currentTimeMillis();
            }
            return aVar.l(list, eVar, iArr, iArr2, j10);
        }

        private final boolean n(long j10, io.h hVar, c cVar) {
            long jC = j10 - hVar.c();
            long jA = j10 - hVar.a();
            long jD = cVar.d();
            if (jC <= cVar.c() && jD <= jC) {
                return true;
            }
            long jD2 = cVar.d();
            if (jA > cVar.c() || jD2 > jA) {
                return jC >= cVar.c() && jA <= cVar.d();
            }
            return true;
        }

        public static /* synthetic */ Map p(a aVar, List list, io.e eVar, int[] iArr, long j10, int i10, Object obj) {
            if ((i10 & 8) != 0) {
                j10 = System.currentTimeMillis();
            }
            return aVar.o(list, eVar, iArr, j10);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Object q(int[] iArr, int[] iArr2, bp.f fVar) {
            xp.p pVar = new xp.p(cp.b.c(fVar), 1);
            pVar.G();
            jo.k.f33544c.a().M1(new C0478a(iArr, iArr2, pVar));
            Object objA = pVar.A();
            if (objA == cp.b.e()) {
                kotlin.coroutines.jvm.internal.g.c(fVar);
            }
            return objA;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Object r(io.l lVar, io.e eVar, int[] iArr, int[] iArr2, bp.f fVar) {
            xp.p pVar = new xp.p(cp.b.c(fVar), 1);
            pVar.G();
            jo.k.f33544c.a().U1(new b(eVar, iArr, iArr2, pVar), lVar);
            Object objA = pVar.A();
            if (objA == cp.b.e()) {
                kotlin.coroutines.jvm.internal.g.c(fVar);
            }
            return objA;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Object s(io.o oVar, int[] iArr, int[] iArr2, bp.f fVar) {
            xp.p pVar = new xp.p(cp.b.c(fVar), 1);
            pVar.G();
            jo.k.f33544c.a().X1(new c(iArr, iArr2, pVar), oVar);
            Object objA = pVar.A();
            if (objA == cp.b.e()) {
                kotlin.coroutines.jvm.internal.g.c(fVar);
            }
            return objA;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Object t(int[] iArr, bp.f fVar) {
            xp.p pVar = new xp.p(cp.b.c(fVar), 1);
            pVar.G();
            jo.k.f33544c.a().F1(new d(iArr, pVar));
            Object objA = pVar.A();
            if (objA == cp.b.e()) {
                kotlin.coroutines.jvm.internal.g.c(fVar);
            }
            return objA;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Object u(int[] iArr, bp.f fVar) {
            xp.p pVar = new xp.p(cp.b.c(fVar), 1);
            pVar.G();
            jo.k.f33544c.a().I1(new e(iArr, pVar));
            Object objA = pVar.A();
            if (objA == cp.b.e()) {
                kotlin.coroutines.jvm.internal.g.c(fVar);
            }
            return objA;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Object v(int[] iArr, bp.f fVar) {
            xp.p pVar = new xp.p(cp.b.c(fVar), 1);
            pVar.G();
            jo.k.f33544c.a().t1(new f(iArr, pVar));
            Object objA = pVar.A();
            if (objA == cp.b.e()) {
                kotlin.coroutines.jvm.internal.g.c(fVar);
            }
            return objA;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Object w(int[] iArr, bp.f fVar) {
            xp.p pVar = new xp.p(cp.b.c(fVar), 1);
            pVar.G();
            jo.k.f33544c.a().z1(new g(iArr, pVar));
            Object objA = pVar.A();
            if (objA == cp.b.e()) {
                kotlin.coroutines.jvm.internal.g.c(fVar);
            }
            return objA;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Object x(int[] iArr, bp.f fVar) {
            xp.p pVar = new xp.p(cp.b.c(fVar), 1);
            pVar.G();
            jo.k.f33544c.a().M1(new C0479h(iArr, pVar));
            Object objA = pVar.A();
            if (objA == cp.b.e()) {
                kotlin.coroutines.jvm.internal.g.c(fVar);
            }
            return objA;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Object y(int[] iArr, bp.f fVar) {
            xp.p pVar = new xp.p(cp.b.c(fVar), 1);
            pVar.G();
            jo.k.f33544c.a().O1(new i(iArr, pVar));
            Object objA = pVar.A();
            if (objA == cp.b.e()) {
                kotlin.coroutines.jvm.internal.g.c(fVar);
            }
            return objA;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Object z(io.l lVar, int[] iArr, bp.f fVar) {
            xp.p pVar = new xp.p(cp.b.c(fVar), 1);
            pVar.G();
            jo.k.f33544c.a().U1(new j(iArr, pVar), lVar);
            Object objA = pVar.A();
            if (objA == cp.b.e()) {
                kotlin.coroutines.jvm.internal.g.c(fVar);
            }
            return objA;
        }

        public final int B(io.e eVar) {
            lp.n.f(eVar, "fraudInfoDefaultResultType");
            return G(false, null, eVar);
        }

        public final long C(long j10, long j11) {
            return j11 > 0 ? j11 - j10 : System.currentTimeMillis() - j10;
        }

        public final Object D(int[] iArr, int[] iArr2, bp.f fVar) {
            return xp.i.g(d1.b(), new l(iArr, iArr2, null), fVar);
        }

        public final Object E(io.l lVar, io.e eVar, int[] iArr, int[] iArr2, bp.f fVar) {
            return xp.i.g(d1.b(), new m(lVar, eVar, iArr, iArr2, null), fVar);
        }

        public final Object F(io.o oVar, int[] iArr, int[] iArr2, bp.f fVar) {
            return xp.i.g(d1.b(), new n(oVar, iArr, iArr2, null), fVar);
        }

        public final int G(boolean z10, io.c cVar, io.e eVar) {
            TelephonyManager telephonyManager;
            TelephonyManager telephonyManager2;
            TelephonyManager telephonyManager3;
            lp.n.f(eVar, "type");
            if (z10) {
                return 1;
            }
            try {
                if (Build.IS_INTERNATIONAL_BUILD) {
                    return 3;
                }
                if (!(cVar instanceof io.k) && eVar != io.e.ScreenshotInfo && !(cVar instanceof io.j) && eVar != io.e.ScreenShareInfo) {
                    if (!(cVar instanceof io.d) && eVar != io.e.CallForwardingInfo) {
                        if (!(cVar instanceof io.a) && eVar != io.e.AirplaneModeInfo) {
                            if (eVar == io.e.SimCardFraudInfo) {
                                Context contextC = com.miui.common.e.c();
                                if (contextC == null || (telephonyManager3 = (TelephonyManager) contextC.getSystemService(TelephonyManager.class)) == null || !telephonyManager3.isVoiceCapable()) {
                                    return 3;
                                }
                                return !YellowPageUtils.isYellowPageEnable(com.miui.common.e.c()) ? 2 : 0;
                            }
                            if (eVar != io.e.SimCardInfo && eVar != io.e.OverseasCallInfo && eVar != io.e.VirtualOperatorCallInfo) {
                                if (!(cVar instanceof io.n) && eVar != io.e.VoipRecordInfo) {
                                    if (!(cVar instanceof io.f) && eVar != io.e.FraudUrlInfo) {
                                        if ((cVar instanceof io.b) || eVar == io.e.AppInfo) {
                                            if (cVar != null) {
                                                Integer numI = ((io.b) cVar).i();
                                                int iB = lo.b.EnumC0477b.RISK.b();
                                                if (numI != null && numI.intValue() == iB) {
                                                    return 0;
                                                }
                                            }
                                            lo.g.a aVar = lo.g.f34703a;
                                            return (aVar.d() || aVar.e()) ? 0 : 3;
                                        }
                                        return 2;
                                    }
                                    return lo.g.f34703a.c() ? 0 : 3;
                                }
                                if (lo.a.c(com.miui.common.e.c())) {
                                    return !lo.a.b(com.miui.common.e.c()) ? 2 : 0;
                                }
                                return 3;
                            }
                            Context contextC2 = com.miui.common.e.c();
                            return (contextC2 == null || (telephonyManager2 = (TelephonyManager) contextC2.getSystemService(TelephonyManager.class)) == null || !telephonyManager2.isVoiceCapable()) ? 3 : 0;
                        }
                        return 0;
                    }
                    Context contextC3 = com.miui.common.e.c();
                    return (contextC3 == null || (telephonyManager = (TelephonyManager) contextC3.getSystemService(TelephonyManager.class)) == null || !telephonyManager.isVoiceCapable() || !lo.g.f34703a.a()) ? 3 : 0;
                }
                return 0;
            } catch (Exception e10) {
                oo.b.b("PastFraudUtil", "getFraudResultType error: " + e10.getMessage());
            }
        }

        public final Object H(int[] iArr, bp.f fVar) {
            return xp.i.g(d1.b(), new o(iArr, null), fVar);
        }

        public final Object I(int[] iArr, bp.f fVar) {
            return xp.i.g(d1.b(), new p(iArr, null), fVar);
        }

        public final Object J(int[] iArr, bp.f fVar) {
            return xp.i.g(d1.b(), new q(iArr, null), fVar);
        }

        public final Object K(int[] iArr, bp.f fVar) {
            return xp.i.g(d1.b(), new r(iArr, null), fVar);
        }

        public final Object L(int[] iArr, bp.f fVar) {
            return xp.i.g(d1.b(), new s(iArr, null), fVar);
        }

        public final Object M(int[] iArr, bp.f fVar) {
            return xp.i.g(d1.b(), new t(iArr, null), fVar);
        }

        public final Object N(io.l lVar, int[] iArr, bp.f fVar) {
            return xp.i.g(d1.b(), new u(lVar, iArr, null), fVar);
        }

        public final Object O(int[] iArr, bp.f fVar) {
            return xp.i.g(d1.b(), new v(iArr, null), fVar);
        }

        public final Map l(List list, io.e eVar, int[] iArr, int[] iArr2, long j10) {
            int i10;
            int i11;
            io.c cVar;
            Object obj;
            int[] iArr3 = iArr;
            lp.n.f(eVar, "fraudInfoDefaultResultType");
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            if (iArr3 != null) {
                try {
                    int length = iArr3.length;
                    int i12 = 0;
                    while (i12 < length) {
                        int i13 = iArr3[i12];
                        c cVarA = c.f34769c.a(i13);
                        if (cVarA == null) {
                            a aVar = h.f34704a;
                            oo.b.c("PastFraudUtil", "Invalid timestamp type: " + i13);
                            i10 = length;
                        } else {
                            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                            if (iArr2 != null) {
                                int length2 = iArr2.length;
                                int i14 = 0;
                                while (i14 < length2) {
                                    int i15 = iArr2[i14];
                                    b bVarA = b.f34760c.a(i15);
                                    if (bVarA == null) {
                                        a aVar2 = h.f34704a;
                                        oo.b.c("PastFraudUtil", "Invalid duration: " + i15);
                                        i11 = length;
                                    } else {
                                        if (list != null) {
                                            Iterator it = list.iterator();
                                            while (true) {
                                                if (!it.hasNext()) {
                                                    i11 = length;
                                                    obj = null;
                                                    break;
                                                }
                                                Object next = it.next();
                                                Object obj2 = (io.c) next;
                                                if (obj2 instanceof io.h) {
                                                    i11 = length;
                                                    boolean zN = h.f34704a.n(j10, (io.h) obj2, cVarA);
                                                    long jD = ((io.h) obj2).d();
                                                    if (zN) {
                                                        long jD2 = bVarA.d();
                                                        if (jD <= bVarA.c() && jD2 <= jD) {
                                                            obj = next;
                                                            break;
                                                        }
                                                    } else {
                                                        continue;
                                                    }
                                                } else {
                                                    i11 = length;
                                                }
                                                length = i11;
                                            }
                                            cVar = (io.c) obj;
                                        } else {
                                            i11 = length;
                                            cVar = null;
                                        }
                                        linkedHashMap2.put(Integer.valueOf(i15), Integer.valueOf(cVar != null ? 1 : h.f34704a.B(eVar)));
                                    }
                                    i14++;
                                    length = i11;
                                }
                            }
                            i10 = length;
                            linkedHashMap.put(Integer.valueOf(i13), linkedHashMap2);
                        }
                        i12++;
                        iArr3 = iArr;
                        length = i10;
                    }
                } catch (Exception e10) {
                    oo.b.b("PastFraudUtil", "checkDurationBehaviorOccurrence error: " + e10.getMessage());
                }
            }
            return linkedHashMap;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r12v4, resolved type: io.c */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        public final Map o(List list, io.e eVar, int[] iArr, long j10) {
            boolean z10;
            boolean zN;
            int iG;
            lp.n.f(list, "events");
            lp.n.f(eVar, "fraudInfoDefaultResultType");
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            if (iArr != null) {
                try {
                    for (int i10 : iArr) {
                        c cVarA = c.f34769c.a(i10);
                        if (cVarA != null) {
                            int iB = h.f34704a.B(eVar);
                            List list2 = list;
                            if ((list2 instanceof Collection) && list2.isEmpty()) {
                                z10 = false;
                                linkedHashMap.put(Integer.valueOf(i10), Integer.valueOf(iB));
                                oo.b.a("PastFraudUtil", "checkPastBehaviorOccurrence-->  hasOccurred: " + z10 + ", fraudRiskResult: " + iB);
                            } else {
                                Iterator it = list2.iterator();
                                while (true) {
                                    if (it.hasNext()) {
                                        io.c cVar = (io.c) it.next();
                                        z10 = true;
                                        if (cVar instanceof io.i) {
                                            long jB = j10 - ((io.i) cVar).b();
                                            zN = jB <= cVarA.c() && cVarA.d() <= jB;
                                            iG = h.f34704a.G(zN, cVar, eVar);
                                        } else if (cVar instanceof io.h) {
                                            a aVar = h.f34704a;
                                            zN = aVar.n(j10, (io.h) cVar, cVarA);
                                            iG = aVar.G(zN, cVar, eVar);
                                        }
                                        iB = iG;
                                        if (zN) {
                                            linkedHashMap.put(Integer.valueOf(i10), Integer.valueOf(iB));
                                            oo.b.a("PastFraudUtil", "checkPastBehaviorOccurrence-->  hasOccurred: " + z10 + ", fraudRiskResult: " + iB);
                                        }
                                    } else {
                                        z10 = false;
                                        linkedHashMap.put(Integer.valueOf(i10), Integer.valueOf(iB));
                                        oo.b.a("PastFraudUtil", "checkPastBehaviorOccurrence-->  hasOccurred: " + z10 + ", fraudRiskResult: " + iB);
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e10) {
                    oo.b.b("PastFraudUtil", "checkPastBehaviorOccurrence error: " + e10.getMessage());
                }
            }
            return linkedHashMap;
        }

        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
        private a() {
        }
    }

    public enum b {
        LESS_THAN_1_MIN(0, 59999),
        BETWEEN_1_3_MIN(60000, 179999),
        BETWEEN_3_10_MIN(180000, 599999),
        MORE_THAN_10_MIN(600000, Long.MAX_VALUE);


        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final long f34767a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final long f34768b;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private static final /* synthetic */ dp.a f34766i = dp.b.a(a());

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final a f34760c = new a(null);

        public static final class a {
            /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x0000: CONSTRUCTOR  A[MD:():void (m)] (LINE:1) call: lo.h.b.a.<init>():void type: THIS */
            /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
            public /* synthetic */ a(lp.h hVar) {
                this();
            }

            public final b a(int i10) {
                return (b) o.S(b.b(), i10);
            }

            /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
            private a() {
            }
        }

        b(long j10, long j11) {
            this.f34767a = j10;
            this.f34768b = j11;
        }

        public static dp.a b() {
            return f34766i;
        }

        public final long c() {
            return this.f34768b;
        }

        public final long d() {
            return this.f34767a;
        }
    }

    public enum c {
        LESS_THAN_1_MIN(0, 59999),
        BETWEEN_1_30_MIN(60000, 1799999),
        BETWEEN_30_60_MIN(1800000, 3599999),
        BETWEEN_1_6_HOUR(3600000, 21599999),
        BETWEEN_6_12_HOUR(21600000, 43199999),
        BETWEEN_12_24_HOUR(43200000, 86399999),
        BETWEEN_24_72_HOUR(86400000, 259199999);


        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final long f34779a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final long f34780b;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        private static final /* synthetic */ dp.a f34778l = dp.b.a(a());

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final a f34769c = new a(null);

        public static final class a {
            /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x0000: CONSTRUCTOR  A[MD:():void (m)] (LINE:1) call: lo.h.c.a.<init>():void type: THIS */
            /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
            public /* synthetic */ a(lp.h hVar) {
                this();
            }

            public final c a(int i10) {
                return (c) o.S(c.b(), i10);
            }

            /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
            private a() {
            }
        }

        c(long j10, long j11) {
            this.f34779a = j10;
            this.f34780b = j11;
        }

        public static dp.a b() {
            return f34778l;
        }

        public final long c() {
            return this.f34780b;
        }

        public final long d() {
            return this.f34779a;
        }
    }
}
