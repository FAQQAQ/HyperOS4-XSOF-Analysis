package defpackage;

import android.content.Context;
import com.miui.guardprovider.GuardApplication;
import com.xiaomi.onetrack.g.b;
import com.xiaomi.onetrack.util.a;
import java.io.IOException;
import java.lang.invoke.VarHandle;
import java.net.ProtocolException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

/* JADX INFO: loaded from: classes.dex */
public final class pq implements oy0 {
    public static final pq b = new pq(0);
    public static final pq c = new pq(1);
    public final /* synthetic */ int a;

    /* JADX DEBUG: Marked for inline */
    /* JADX DEBUG: Method not inlined, still used in: [h40.a():vd2, l82.e():cd2, pq.<clinit>():void, t20.get():java.lang.Object, zy.get():java.lang.Object] */
    public /* synthetic */ pq(int i) {
        this.a = i;
    }

    /* JADX WARN: Code duplicated, block: B:171:0x0438  */
    /* JADX WARN: Code duplicated, block: B:175:0x0443  */
    /* JADX WARN: Code duplicated, block: B:178:0x044d A[Catch: IOException -> 0x0459, TryCatch #0 {IOException -> 0x0459, blocks: (B:176:0x0444, B:178:0x044d, B:181:0x045c, B:190:0x0483, B:192:0x048c, B:193:0x0495, B:194:0x04ae, B:199:0x04c2, B:205:0x04d1, B:206:0x04d8, B:208:0x04db, B:211:0x04e4, B:217:0x04f1, B:219:0x0528, B:221:0x053b, B:225:0x0545, B:232:0x055e, B:235:0x056b, B:236:0x058f, B:227:0x054b, B:218:0x0513), top: B:243:0x0444 }] */
    /* JADX WARN: Code duplicated, block: B:192:0x048c A[Catch: IOException -> 0x0459, TryCatch #0 {IOException -> 0x0459, blocks: (B:176:0x0444, B:178:0x044d, B:181:0x045c, B:190:0x0483, B:192:0x048c, B:193:0x0495, B:194:0x04ae, B:199:0x04c2, B:205:0x04d1, B:206:0x04d8, B:208:0x04db, B:211:0x04e4, B:217:0x04f1, B:219:0x0528, B:221:0x053b, B:225:0x0545, B:232:0x055e, B:235:0x056b, B:236:0x058f, B:227:0x054b, B:218:0x0513), top: B:243:0x0444 }] */
    /* JADX WARN: Code duplicated, block: B:196:0x04bc  */
    /* JADX WARN: Code duplicated, block: B:197:0x04bf  */
    /* JADX WARN: Code duplicated, block: B:199:0x04c2 A[Catch: IOException -> 0x0459, TryCatch #0 {IOException -> 0x0459, blocks: (B:176:0x0444, B:178:0x044d, B:181:0x045c, B:190:0x0483, B:192:0x048c, B:193:0x0495, B:194:0x04ae, B:199:0x04c2, B:205:0x04d1, B:206:0x04d8, B:208:0x04db, B:211:0x04e4, B:217:0x04f1, B:219:0x0528, B:221:0x053b, B:225:0x0545, B:232:0x055e, B:235:0x056b, B:236:0x058f, B:227:0x054b, B:218:0x0513), top: B:243:0x0444 }] */
    /* JADX WARN: Code duplicated, block: B:201:0x04ca  */
    /* JADX WARN: Code duplicated, block: B:202:0x04cd  */
    /* JADX WARN: Code duplicated, block: B:204:0x04d0  */
    /* JADX WARN: Code duplicated, block: B:205:0x04d1 A[Catch: IOException -> 0x0459, TryCatch #0 {IOException -> 0x0459, blocks: (B:176:0x0444, B:178:0x044d, B:181:0x045c, B:190:0x0483, B:192:0x048c, B:193:0x0495, B:194:0x04ae, B:199:0x04c2, B:205:0x04d1, B:206:0x04d8, B:208:0x04db, B:211:0x04e4, B:217:0x04f1, B:219:0x0528, B:221:0x053b, B:225:0x0545, B:232:0x055e, B:235:0x056b, B:236:0x058f, B:227:0x054b, B:218:0x0513), top: B:243:0x0444 }] */
    /* JADX WARN: Code duplicated, block: B:208:0x04db A[Catch: IOException -> 0x0459, TryCatch #0 {IOException -> 0x0459, blocks: (B:176:0x0444, B:178:0x044d, B:181:0x045c, B:190:0x0483, B:192:0x048c, B:193:0x0495, B:194:0x04ae, B:199:0x04c2, B:205:0x04d1, B:206:0x04d8, B:208:0x04db, B:211:0x04e4, B:217:0x04f1, B:219:0x0528, B:221:0x053b, B:225:0x0545, B:232:0x055e, B:235:0x056b, B:236:0x058f, B:227:0x054b, B:218:0x0513), top: B:243:0x0444 }] */
    /* JADX WARN: Code duplicated, block: B:210:0x04e3  */
    /* JADX WARN: Code duplicated, block: B:213:0x04ea  */
    /* JADX WARN: Code duplicated, block: B:214:0x04eb  */
    /* JADX WARN: Code duplicated, block: B:218:0x0513 A[Catch: IOException -> 0x0459, TryCatch #0 {IOException -> 0x0459, blocks: (B:176:0x0444, B:178:0x044d, B:181:0x045c, B:190:0x0483, B:192:0x048c, B:193:0x0495, B:194:0x04ae, B:199:0x04c2, B:205:0x04d1, B:206:0x04d8, B:208:0x04db, B:211:0x04e4, B:217:0x04f1, B:219:0x0528, B:221:0x053b, B:225:0x0545, B:232:0x055e, B:235:0x056b, B:236:0x058f, B:227:0x054b, B:218:0x0513), top: B:243:0x0444 }] */
    /* JADX WARN: Code duplicated, block: B:221:0x053b A[Catch: IOException -> 0x0459, TryCatch #0 {IOException -> 0x0459, blocks: (B:176:0x0444, B:178:0x044d, B:181:0x045c, B:190:0x0483, B:192:0x048c, B:193:0x0495, B:194:0x04ae, B:199:0x04c2, B:205:0x04d1, B:206:0x04d8, B:208:0x04db, B:211:0x04e4, B:217:0x04f1, B:219:0x0528, B:221:0x053b, B:225:0x0545, B:232:0x055e, B:235:0x056b, B:236:0x058f, B:227:0x054b, B:218:0x0513), top: B:243:0x0444 }] */
    /* JADX WARN: Code duplicated, block: B:223:0x0543  */
    /* JADX WARN: Code duplicated, block: B:224:0x0544  */
    /* JADX WARN: Code duplicated, block: B:227:0x054b A[Catch: IOException -> 0x0459, TryCatch #0 {IOException -> 0x0459, blocks: (B:176:0x0444, B:178:0x044d, B:181:0x045c, B:190:0x0483, B:192:0x048c, B:193:0x0495, B:194:0x04ae, B:199:0x04c2, B:205:0x04d1, B:206:0x04d8, B:208:0x04db, B:211:0x04e4, B:217:0x04f1, B:219:0x0528, B:221:0x053b, B:225:0x0545, B:232:0x055e, B:235:0x056b, B:236:0x058f, B:227:0x054b, B:218:0x0513), top: B:243:0x0444 }] */
    /* JADX WARN: Code duplicated, block: B:242:0x0598 A[ADDED_TO_REGION, REMOVE] */
    /* JADX WARN: Code duplicated, block: B:275:0x0495 A[SYNTHETIC] */
    @Override // defpackage.oy0
    public final cd2 a(p82 p82Var) throws Throwable {
        ad2 ad2VarD;
        boolean z;
        boolean z2;
        IOException iOException;
        ad2 ad2VarD2;
        cd2 cd2VarA;
        int i;
        boolean z3;
        cd2 cd2VarA2;
        ti tiVar;
        String strB;
        String strB2;
        boolean z4;
        boolean z5;
        ad2 ad2Var;
        Object dt0Var;
        boolean z6;
        String str = null;
        switch (this.a) {
            case 0:
                ge0 ge0Var = p82Var.d;
                ge0Var.getClass();
                ti tiVar2 = p82Var.e;
                ic2 ic2Var = (ic2) tiVar2.e;
                long jCurrentTimeMillis = System.currentTimeMillis();
                boolean z7 = uw0.y((String) tiVar2.h) && ic2Var != null;
                boolean zEqualsIgnoreCase = "upgrade".equalsIgnoreCase(((ss0) tiVar2.d).b(b.h));
                try {
                    try {
                        ((l82) ge0Var.b).e.getClass();
                        ((ei0) ge0Var.d).j(tiVar2);
                        ((l82) ge0Var.b).e.getClass();
                        if (z7) {
                            try {
                                if ("100-continue".equalsIgnoreCase(((ss0) tiVar2.d).b("Expect"))) {
                                    try {
                                        try {
                                            ((ei0) ge0Var.d).f();
                                            z5 = true;
                                            ad2VarD = ge0Var.d(true);
                                            try {
                                                ((l82) ge0Var.b).e.getClass();
                                                ad2Var = ad2VarD;
                                                z = false;
                                            } catch (IOException e) {
                                                e = e;
                                                z = true;
                                                z2 = true;
                                                if (e instanceof w00) {
                                                    throw e;
                                                }
                                                throw e;
                                            }
                                        } catch (IOException e2) {
                                            ((l82) ge0Var.b).e.getClass();
                                            ge0Var.e(e2);
                                            throw e2;
                                        }
                                    } catch (IOException e3) {
                                        e = e3;
                                        ad2VarD = null;
                                    }
                                } else {
                                    z5 = true;
                                    ad2Var = null;
                                    z = true;
                                }
                                if (ad2Var != null) {
                                    try {
                                        z2 = z5;
                                        try {
                                            ((l82) ge0Var.b).f(ge0Var, true, false, false, false, null);
                                            if (!(ge0Var.b().i != null ? z2 : false)) {
                                                ((ei0) ge0Var.d).h().e();
                                            }
                                        } catch (IOException e4) {
                                            e = e4;
                                            ad2VarD = ad2Var;
                                            if (e instanceof w00) {
                                                throw e;
                                            }
                                            throw e;
                                        }
                                    } catch (IOException e5) {
                                        e = e5;
                                        z2 = z5;
                                        ad2VarD = ad2Var;
                                        if (e instanceof w00) {
                                            throw e;
                                        }
                                        throw e;
                                    }
                                    break;
                                } else {
                                    try {
                                        ic2Var.getClass();
                                        ic2 ic2Var2 = (ic2) tiVar2.e;
                                        ic2Var2.getClass();
                                        long jA = ic2Var2.a();
                                        ((l82) ge0Var.b).e.getClass();
                                        bi0 bi0Var = new bi0(ge0Var, ((ei0) ge0Var.d).i(tiVar2, jA), jA, false);
                                        g82 g82Var = new g82();
                                        g82Var.b = bi0Var;
                                        g82Var.c = new ao();
                                        VarHandle.storeStoreFence();
                                        ic2Var.d(g82Var);
                                        g82Var.close();
                                        z2 = z5;
                                    } catch (IOException e6) {
                                        e = e6;
                                        z2 = z5;
                                        ad2VarD = ad2Var;
                                        if ((e instanceof w00) || !ge0Var.a) {
                                            throw e;
                                        }
                                        ad2 ad2Var2 = ad2VarD;
                                        iOException = e;
                                        ad2VarD2 = ad2Var2;
                                        if (ad2VarD2 == null) {
                                            try {
                                                ad2VarD2 = ge0Var.d(false);
                                                ad2VarD2.getClass();
                                                if (z) {
                                                    ((l82) ge0Var.b).e.getClass();
                                                    z = false;
                                                }
                                            } catch (IOException e7) {
                                                if (iOException == null) {
                                                    throw e7;
                                                }
                                                oe2.k(iOException, e7);
                                                throw iOException;
                                            }
                                        }
                                        ad2VarD2.a = tiVar2;
                                        ad2VarD2.e = ge0Var.b().f;
                                        ad2VarD2.l = jCurrentTimeMillis;
                                        ad2VarD2.m = System.currentTimeMillis();
                                        cd2VarA = ad2VarD2.a();
                                        i = cd2VarA.e;
                                        while (true) {
                                            if (i != 100) {
                                                ((l82) ge0Var.b).e.getClass();
                                                if (i == 101) {
                                                    z3 = z2;
                                                } else {
                                                    z3 = false;
                                                }
                                                if (z3) {
                                                    if (ge0Var.b().i != null) {
                                                        z4 = z2;
                                                    } else {
                                                        z4 = false;
                                                    }
                                                    if (!z4) {
                                                        throw new ProtocolException("Unexpected 101 code on HTTP/2 connection");
                                                    }
                                                }
                                                if (z3) {
                                                    strB2 = cd2VarA.g.b(b.h);
                                                    if (strB2 == null) {
                                                        strB2 = null;
                                                    }
                                                    if ("upgrade".equalsIgnoreCase(strB2)) {
                                                        z2 = false;
                                                    }
                                                } else {
                                                    z2 = false;
                                                }
                                                if (zEqualsIgnoreCase) {
                                                    q82 q82VarC = ge0Var.c(cd2VarA);
                                                    ad2 ad2VarA = cd2VarA.a();
                                                    ad2VarA.g = q82VarC;
                                                    ad2VarA.o = new fo3();
                                                    cd2VarA2 = ad2VarA.a();
                                                } else {
                                                    q82 q82VarC2 = ge0Var.c(cd2VarA);
                                                    ad2 ad2VarA2 = cd2VarA.a();
                                                    ad2VarA2.g = q82VarC2;
                                                    ad2VarA2.o = new fo3();
                                                    cd2VarA2 = ad2VarA2.a();
                                                }
                                                tiVar = cd2VarA2.b;
                                                tiVar.getClass();
                                                if ("close".equalsIgnoreCase(((ss0) tiVar.d).b(b.h))) {
                                                    ((ei0) ge0Var.d).h().e();
                                                } else {
                                                    strB = cd2VarA2.g.b(b.h);
                                                    if (strB == null) {
                                                        str = strB;
                                                    }
                                                    if ("close".equalsIgnoreCase(str)) {
                                                        ((ei0) ge0Var.d).h().e();
                                                    }
                                                }
                                                if (i == 204) {
                                                    throw new ProtocolException("HTTP " + i + " had non-zero Content-Length: " + cd2VarA2.h.b());
                                                }
                                                throw new ProtocolException("HTTP " + i + " had non-zero Content-Length: " + cd2VarA2.h.b());
                                                return cd2VarA2;
                                            }
                                            ad2 ad2VarD3 = ge0Var.d(false);
                                            ad2VarD3.getClass();
                                            if (z) {
                                                ((l82) ge0Var.b).e.getClass();
                                            }
                                            ad2VarD3.a = tiVar2;
                                            ad2VarD3.e = ge0Var.b().f;
                                            ad2VarD3.l = jCurrentTimeMillis;
                                            ad2VarD3.m = System.currentTimeMillis();
                                            cd2VarA = ad2VarD3.a();
                                            i = cd2VarA.e;
                                        }
                                    }
                                }
                                ad2VarD = ad2Var;
                            } catch (IOException e8) {
                                e = e8;
                                z2 = true;
                                ad2VarD = null;
                                z = true;
                                if (e instanceof w00) {
                                    throw e;
                                }
                                throw e;
                            }
                        } else {
                            z2 = true;
                            ((l82) ge0Var.b).f(ge0Var, true, false, false, false, null);
                            ad2VarD = null;
                            z = true;
                        }
                        try {
                            ((ei0) ge0Var.d).a();
                            ad2VarD2 = ad2VarD;
                            iOException = null;
                            while (true) {
                                if (i != 100 && (102 > i || i >= 200)) {
                                }
                                ad2 ad2VarD4 = ge0Var.d(false);
                                ad2VarD4.getClass();
                                if (z) {
                                    ((l82) ge0Var.b).e.getClass();
                                }
                                ad2VarD4.a = tiVar2;
                                ad2VarD4.e = ge0Var.b().f;
                                ad2VarD4.l = jCurrentTimeMillis;
                                ad2VarD4.m = System.currentTimeMillis();
                                cd2VarA = ad2VarD4.a();
                                i = cd2VarA.e;
                            }
                        } catch (IOException e9) {
                            try {
                                ((l82) ge0Var.b).e.getClass();
                                ge0Var.e(e9);
                                throw e9;
                            } catch (IOException e10) {
                                e = e10;
                                if (e instanceof w00) {
                                    throw e;
                                }
                                throw e;
                            }
                        }
                    } catch (IOException e11) {
                        ((l82) ge0Var.b).e.getClass();
                        ge0Var.e(e11);
                        throw e11;
                    }
                } catch (IOException e12) {
                    e = e12;
                }
                if (ad2VarD2 == null) {
                    ad2VarD2 = ge0Var.d(false);
                    ad2VarD2.getClass();
                    if (z) {
                        ((l82) ge0Var.b).e.getClass();
                        z = false;
                    }
                }
                ad2VarD2.a = tiVar2;
                ad2VarD2.e = ge0Var.b().f;
                ad2VarD2.l = jCurrentTimeMillis;
                ad2VarD2.m = System.currentTimeMillis();
                cd2VarA = ad2VarD2.a();
                i = cd2VarA.e;
                ((l82) ge0Var.b).e.getClass();
                if (i == 101) {
                    z3 = z2;
                } else {
                    z3 = false;
                }
                if (z3) {
                    if (ge0Var.b().i != null) {
                        z4 = z2;
                    } else {
                        z4 = false;
                    }
                    if (!z4) {
                        throw new ProtocolException("Unexpected 101 code on HTTP/2 connection");
                    }
                }
                if (z3) {
                    z2 = false;
                } else {
                    strB2 = cd2VarA.g.b(b.h);
                    if (strB2 == null) {
                        strB2 = null;
                    }
                    if ("upgrade".equalsIgnoreCase(strB2)) {
                        z2 = false;
                    }
                }
                if (zEqualsIgnoreCase || !z2) {
                    q82 q82VarC3 = ge0Var.c(cd2VarA);
                    ad2 ad2VarA3 = cd2VarA.a();
                    ad2VarA3.g = q82VarC3;
                    ad2VarA3.o = new fo3();
                    cd2VarA2 = ad2VarA3.a();
                } else {
                    ad2 ad2VarA4 = cd2VarA.a();
                    ad2VarA4.g = new i13(cd2VarA.h.g(), cd2VarA.h.b());
                    ad2VarA4.h = ge0Var.f();
                    cd2VarA2 = ad2VarA4.a();
                }
                tiVar = cd2VarA2.b;
                tiVar.getClass();
                if ("close".equalsIgnoreCase(((ss0) tiVar.d).b(b.h))) {
                    ((ei0) ge0Var.d).h().e();
                } else {
                    strB = cd2VarA2.g.b(b.h);
                    if (strB == null) {
                        str = strB;
                    }
                    if ("close".equalsIgnoreCase(str)) {
                        ((ei0) ge0Var.d).h().e();
                    }
                }
                if ((i == 204 && i != 205) || cd2VarA2.h.b() <= 0) {
                    return cd2VarA2;
                }
                throw new ProtocolException("HTTP " + i + " had non-zero Content-Length: " + cd2VarA2.h.b());
            case 1:
                l82 l82Var = p82Var.a;
                synchronized (l82Var) {
                    if (!l82Var.q) {
                        throw new IllegalStateException("released");
                    }
                    if (l82Var.n || l82Var.m || l82Var.p || l82Var.o) {
                        throw new IllegalStateException("Check failed.");
                    }
                }
                fi0 fi0Var = l82Var.i;
                fi0Var.getClass();
                m82 m82VarA = fi0Var.a();
                ew1 ew1Var = l82Var.b;
                m82VarA.getClass();
                ew1Var.getClass();
                int i2 = p82Var.g;
                hq3 hq3Var = m82VarA.h;
                mt0 mt0Var = m82VarA.i;
                if (mt0Var != null) {
                    nt0 nt0Var = new nt0();
                    nt0Var.a = m82VarA;
                    nt0Var.b = p82Var;
                    nt0Var.c = mt0Var;
                    List list = ew1Var.r;
                    i62 i62Var = i62.H2_PRIOR_KNOWLEDGE;
                    if (!list.contains(i62Var)) {
                        i62Var = i62.HTTP_2;
                    }
                    nt0Var.e = i62Var;
                    VarHandle.storeStoreFence();
                    dt0Var = nt0Var;
                } else {
                    m82VarA.e.setSoTimeout(i2);
                    ((h82) hq3Var.d).b.f().g(i2);
                    ((g82) hq3Var.e).b.f().g(p82Var.h);
                    dt0Var = new dt0(ew1Var, m82VarA, hq3Var);
                }
                ge0 ge0Var2 = new ge0();
                ge0Var2.b = l82Var;
                ge0Var2.c = fi0Var;
                ge0Var2.d = dt0Var;
                VarHandle.storeStoreFence();
                l82Var.l = ge0Var2;
                l82Var.s = ge0Var2;
                synchronized (l82Var) {
                    l82Var.m = true;
                    l82Var.n = true;
                }
                if (!l82Var.r) {
                    return p82.a(p82Var, 0, ge0Var2, null, 2097149).b(p82Var.e);
                }
                ik.u("Canceled");
                return null;
            case 2:
                wp1 wp1Var = p82Var.l;
                ti tiVar3 = p82Var.e;
                re3 re3VarB = tiVar3.B();
                bu0 bu0Var = (bu0) tiVar3.c;
                ss0 ss0Var = (ss0) tiVar3.d;
                ic2 ic2Var3 = (ic2) tiVar3.e;
                if (ic2Var3 != null) {
                    im1 im1VarB = ic2Var3.b();
                    if (im1VarB != null) {
                        re3VarB.s("Content-Type", im1VarB.a);
                    }
                    long jA2 = ic2Var3.a();
                    if (jA2 != -1) {
                        re3VarB.s("Content-Length", String.valueOf(jA2));
                        ((vb2) re3VarB.b).i("Transfer-Encoding");
                    } else {
                        re3VarB.s("Transfer-Encoding", "chunked");
                        ((vb2) re3VarB.b).i("Content-Length");
                    }
                }
                if (ss0Var.b("Host") == null) {
                    re3VarB.s("Host", hb3.h(bu0Var, false));
                }
                if (ss0Var.b(b.h) == null) {
                    re3VarB.s(b.h, "Keep-Alive");
                }
                if (ss0Var.b("Accept-Encoding") == null && ss0Var.b("Range") == null) {
                    re3VarB.s("Accept-Encoding", "gzip");
                    z6 = true;
                } else {
                    z6 = false;
                }
                wp1Var.getClass();
                bu0Var.getClass();
                if (ss0Var.b("User-Agent") == null) {
                    re3VarB.s("User-Agent", "okhttp/5.4.0");
                }
                ti tiVarK = re3VarB.k();
                cd2 cd2VarB = p82Var.b(tiVarK);
                ss0 ss0Var2 = cd2VarB.g;
                wt0.b(wp1Var, (bu0) tiVarK.c, ss0Var2);
                ad2 ad2VarA5 = cd2VarB.a();
                ad2VarA5.a = tiVarK;
                if (z6) {
                    String strB3 = ss0Var2.b("Content-Encoding");
                    if (strB3 == null) {
                        strB3 = null;
                    }
                    if ("gzip".equalsIgnoreCase(strB3) && wt0.a(cd2VarB)) {
                        mo moVarK = cd2VarB.h.k();
                        moVarK.getClass();
                        ds0 ds0Var = new ds0();
                        h82 h82Var = new h82(moVarK);
                        ds0Var.c = h82Var;
                        Inflater inflater = new Inflater(true);
                        ds0Var.d = inflater;
                        jx0 jx0Var = new jx0();
                        jx0Var.b = h82Var;
                        jx0Var.c = inflater;
                        VarHandle.storeStoreFence();
                        ds0Var.e = jx0Var;
                        ds0Var.f = new CRC32();
                        VarHandle.storeStoreFence();
                        vb2 vb2VarG = ss0Var2.g();
                        vb2VarG.i("Content-Encoding");
                        vb2VarG.i("Content-Length");
                        ad2VarA5.f = vb2VarG.b().g();
                        String strB4 = ss0Var2.b("Content-Type");
                        ad2VarA5.g = new q82(strB4 == null ? null : strB4, -1L, new h82(ds0Var));
                    }
                }
                return ad2VarA5.a();
            case 3:
                GuardApplication guardApplication = GuardApplication.application;
                Object objInvoke = GuardApplication.class.getMethod("isAllowConnectNetwork", Context.class).invoke(null, GuardApplication.class.getField("application").get(null));
                objInvoke.getClass();
                if (((Boolean) objInvoke).booleanValue()) {
                    return p82Var.b(p82Var.e);
                }
                ik.u("Network access disabled.");
                return null;
            default:
                ti tiVar4 = p82Var.e;
                TreeMap treeMap = new TreeMap();
                bu0 bu0Var2 = (bu0) tiVar4.c;
                for (String str2 : bu0Var2.h()) {
                    if (az0.j(str2, "sign")) {
                        cw1.f("You shouldn't pass sign parameter because it is auto generated.");
                        return null;
                    }
                    String strG = bu0Var2.g(str2);
                    if (strG != null) {
                        treeMap.put(str2, strG);
                    }
                }
                au0 au0VarF = bu0Var2.f();
                StringJoiner stringJoiner = new StringJoiner("&", a.c, a.c);
                for (Map.Entry entry : treeMap.entrySet()) {
                    stringJoiner.add(((String) entry.getKey()) + "=" + entry.getValue());
                }
                stringJoiner.add("62b36629-ecb4-4725-9f33-ccb0fd158191");
                String string = stringJoiner.toString();
                string.getClass();
                Charset charset = StandardCharsets.UTF_8;
                charset.getClass();
                byte[] bytes = string.getBytes(charset);
                bytes.getClass();
                au0VarF.a("sign", vh3.u("MD5", bytes, true));
                bu0 bu0VarB = au0VarF.b();
                re3 re3VarB2 = tiVar4.B();
                re3VarB2.a = bu0VarB;
                return p82Var.b(re3VarB2.k());
        }
    }
}
