package defpackage;

import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.net.Uri;
import android.os.Build;
import android.os.HandlerThread;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.miui.guardprovider.GuardApplication;
import com.xiaomi.onetrack.OneTrack;
import com.xiaomi.onetrack.api.al;
import com.xiaomi.onetrack.util.a;
import java.io.IOException;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;
import tmsdkobf.pb;
import tmsdkobf.v5;

/* JADX INFO: loaded from: classes.dex */
public final class ro implements q20, w20, bo1, y82, f40, n22, qq3, v5 {
    public static final ro c = new ro(0);
    public static final ro d = new ro(1);
    public static final /* synthetic */ ro e = new ro(2);
    public static final ro f = new ro(3);
    public static final ro g = new ro(4);
    public static final ro h = new ro(5);
    public static final ro i = new ro(6);
    public static final cx1 j = new cx1();
    public static final bx1 k = new bx1();
    public final /* synthetic */ int b;

    public ro(ContextWrapper contextWrapper) {
        this.b = 12;
        synchronized (wp1.class) {
            if (wp1.b == null) {
                wp1 wp1Var = new wp1();
                wp1.c = contextWrapper.getApplicationContext();
                wp1.b = wp1Var;
            }
        }
    }

    public static LinkedHashSet A(String str, String... strArr) {
        return y("java/util/".concat(str), (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public static boolean B(pz2 pz2Var, lf1 lf1Var) {
        pz2Var.getClass();
        lf1Var.getClass();
        by2 by2VarA0 = pz2Var.a0(lf1Var);
        by2VarA0.getClass();
        if (!pz2Var.z(by2VarA0)) {
            return false;
        }
        pz2Var.m(lf1Var);
        return (pz2Var.D(lf1Var) || pz2Var.X(lf1Var) || pz2Var.l0(lf1Var)) ? false : true;
    }

    public static boolean C(float f2, float f3, float f4) {
        return ((double) f2) <= 1.3680000121593476d * ((double) (f3 + f4));
    }

    public static boolean D(yx2 yx2Var, pz2 pz2Var, nx2 nx2Var, ge2 ge2Var) {
        boolean zE;
        pz2Var.getClass();
        nx2Var.getClass();
        by2 by2VarA = pz2Var.A(ge2Var);
        int iK = pz2Var.k(nx2Var);
        by2VarA.getClass();
        int iM = pz2Var.M(by2VarA);
        if (iK == iM && iK == pz2Var.c(ge2Var)) {
            for (int i2 = 0; i2 < iM; i2++) {
                ox2 ox2VarK0 = pz2Var.k0(ge2Var, i2);
                ox2VarK0.getClass();
                lf1 lf1VarN = pz2Var.n(ox2VarK0);
                if (lf1VarN != null) {
                    ox2 ox2VarI0 = pz2Var.i0(nx2Var, i2);
                    ox2VarI0.getClass();
                    pz2Var.l(ox2VarI0);
                    vz2 vz2Var = vz2.INV;
                    lf1 lf1VarN2 = pz2Var.n(ox2VarI0);
                    lf1VarN2.getClass();
                    vz2 vz2VarV = pz2Var.v(pz2Var.U(by2VarA, i2));
                    vz2 vz2VarL = pz2Var.l(ox2VarK0);
                    if (vz2VarV == vz2Var) {
                        vz2VarV = vz2VarL;
                    } else if (vz2VarL != vz2Var && vz2VarV != vz2VarL) {
                        vz2VarV = null;
                    }
                    if (vz2VarV == null) {
                        return yx2Var.a;
                    }
                    if (vz2VarV == vz2Var) {
                        F(pz2Var, lf1VarN2, lf1VarN);
                        F(pz2Var, lf1VarN, lf1VarN2);
                    }
                    int i3 = yx2Var.f;
                    if (i3 > 100) {
                        ik.h(lf1VarN2, "Arguments depth is too high. Some related argument: ");
                        return false;
                    }
                    yx2Var.f = i3 + 1;
                    int iOrdinal = vz2VarV.ordinal();
                    ro roVar = d;
                    if (iOrdinal == 0) {
                        zE = E(roVar, yx2Var, lf1VarN, lf1VarN2);
                    } else if (iOrdinal == 1) {
                        zE = E(roVar, yx2Var, lf1VarN2, lf1VarN);
                    } else {
                        if (iOrdinal != 2) {
                            c.b();
                            return false;
                        }
                        zE = t(yx2Var, lf1VarN2, lf1VarN);
                    }
                    yx2Var.f--;
                    if (!zE) {
                    }
                }
            }
            return true;
        }
        return false;
    }

    public static boolean E(ro roVar, yx2 yx2Var, lf1 lf1Var, lf1 lf1Var2) {
        pz2 pz2Var = yx2Var.c;
        lf1Var.getClass();
        lf1Var2.getClass();
        if (lf1Var == lf1Var2) {
            return true;
        }
        pp0 pp0VarH = pz2Var.H();
        Boolean bool = pp0VarH != null ? (Boolean) pp0VarH.H(lf1Var, lf1Var2) : null;
        return bool != null ? bool.booleanValue() : d.o(yx2Var, pz2Var, lf1Var, lf1Var2);
    }

    public static void F(pz2 pz2Var, lf1 lf1Var, lf1 lf1Var2) {
        pz2Var.getClass();
        ge2 ge2VarD0 = pz2Var.d0(lf1Var);
        if (ge2VarD0 instanceof ft) {
            ft ftVar = (ft) ge2VarD0;
            if (pz2Var.i(ftVar)) {
                return;
            }
            et etVarP = pz2Var.p(ftVar);
            etVarP.getClass();
            ox2 ox2VarS = pz2Var.S(etVarP);
            ox2VarS.getClass();
            if (pz2Var.j(ox2VarS) && pz2Var.B(ftVar) == us.b) {
                pz2Var.a0(lf1Var2);
            }
        }
    }

    public static boolean G(float f2, float f3, float f4) {
        return ((double) f2) <= 1.3680000121593476d * ((double) (f3 + f4));
    }

    public static l70 H(c23 c23Var, boolean z) {
        boolean zE;
        c23Var.getClass();
        if (c23Var instanceof l70) {
            return (l70) c23Var;
        }
        c23Var.o0();
        if ((c23Var.o0().w() instanceof zy2) || (c23Var instanceof vs1)) {
            ow owVarW = c23Var.o0().w();
            az2 az2Var = owVarW instanceof az2 ? (az2) owVarW : null;
            zE = true;
            if (az2Var == null || az2Var.o) {
                zE = (z && (c23Var.o0().w() instanceof zy2)) ? uz2.e(c23Var) : true ^ gd3.v(vg3.k.z0(), ph3.u(c23Var), wx2.h);
            }
        } else {
            zE = false;
        }
        if (!zE) {
            return null;
        }
        if (c23Var instanceof nl0) {
            nl0 nl0Var = (nl0) c23Var;
            az0.j(nl0Var.c.o0(), nl0Var.d.o0());
        }
        return new l70(ph3.u(c23Var).s0(false), z);
    }

    public static wu2 I(Context context) throws IllegalAccessException, InvocationTargetException {
        GuardApplication guardApplication = GuardApplication.application;
        Object objInvoke = GuardApplication.class.getMethod("isAllowConnectNetwork", Context.class).invoke(null, GuardApplication.class.getField("application").get(null));
        objInvoke.getClass();
        OneTrack.setAccessNetworkEnable(context, ((Boolean) objInvoke).booleanValue());
        if (xu2.a == null) {
            synchronized (xu2.class) {
                if (xu2.a == null) {
                    xu2.a = new wu2(context);
                }
            }
        }
        wu2 wu2Var = xu2.a;
        wu2Var.getClass();
        return wu2Var;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x00ff  */
    public static l00 J() throws k00 {
        String strB;
        ConcurrentHashMap concurrentHashMap = ub3.a;
        Log.d("MipccService", "start pcc_open_session");
        rb3 rb3Var = new rb3();
        rb3Var.a = false;
        String string = UUID.randomUUID().toString();
        ob3 ob3Var = new ob3();
        ob3Var.b = new Object();
        ob3Var.i = true;
        ob3Var.a = "157a2c2f09b3ba4d31fbb21fadb4d36f";
        rg3 rg3Var = new rg3();
        rg3Var.d = new WeakReference(ob3Var);
        rg3Var.c = "https://detection-aigc.engine.miui.com/pcc/attestation/token";
        VarHandle.storeStoreFence();
        ob3Var.c = rg3Var;
        CompletableFuture completableFuture = new CompletableFuture();
        ob3Var.d = completableFuture;
        VarHandle.storeStoreFence();
        ub3.a.put(string, ob3Var);
        Context context = wp1.c;
        ib3 ib3Var = new ib3();
        ib3Var.a = ob3Var;
        ib3Var.b = null;
        ib3Var.c = rb3Var;
        VarHandle.storeStoreFence();
        CompletableFuture.supplyAsync(ib3Var, Executors.newSingleThreadExecutor());
        try {
            completableFuture.get(20L, TimeUnit.SECONDS);
            Log.d("MipccService", "open session successful");
            strB = ub3.b(0, string);
        } catch (Exception e2) {
            Log.e("MipccService", "Handle exception");
            e2.printStackTrace();
            ob3Var.a();
            ub3.a.remove(string);
            ub3.d("157a2c2f09b3ba4d31fbb21fadb4d36f");
            if (rb3Var.a) {
                context.getApplicationContext().getSharedPreferences("PccPrefs", 0).edit().remove(null).apply();
            }
            String message = e2.getMessage();
            if (message == null) {
                strB = ub3.b(-1004, "Unknown error");
            } else if (message.contains("Chain validation failed")) {
                Log.e("MipccService", "System time error");
                strB = ub3.b(-1001, a.c);
            } else if (message.contains("No address associated with hostname")) {
                Log.e("MipccService", "Network connection failed");
                strB = ub3.b(-1002, a.c);
            } else if (message.contains("certificate chaintimestamp")) {
                Log.e("MipccService", "Verify that the certificate timestamp has expired");
                strB = ub3.b(-1003, a.c);
            } else if (message.contains("Response verification failed")) {
                Log.e("MipccService", "Verify response failed");
                strB = ub3.b(-1005, a.c);
            } else {
                strB = ub3.b(-1004, "Unknown error");
            }
        }
        if (TextUtils.isEmpty(strB)) {
            throw new k00("Null session result");
        }
        try {
            JSONObject jSONObject = new JSONObject(strB);
            int iOptInt = jSONObject.optInt(al.N);
            if (iOptInt != 0) {
                throw new k00(d61.p(iOptInt, "Session error: "));
            }
            String strOptString = jSONObject.optString("session_id");
            l00 l00Var = new l00();
            l00Var.b = strOptString;
            VarHandle.storeStoreFence();
            return l00Var;
        } catch (JSONException e3) {
            throw new k00("Unexpected json string:", e3);
        }
    }

    public static String c(String str) throws IOException {
        Uri.Builder builderBuildUpon = Uri.parse(str).buildUpon();
        builderBuildUpon.appendQueryParameter("sdkver", String.valueOf(xr0.a()));
        builderBuildUpon.appendQueryParameter("osver", String.valueOf(Build.VERSION.SDK_INT));
        builderBuildUpon.appendQueryParameter("os", tm3.b(f03.u()));
        builderBuildUpon.appendQueryParameter("mi", String.valueOf(vq3.a()));
        String string = builderBuildUpon.toString();
        kg3.q("fetch bucket from : " + string);
        URL url = new URL(string);
        int port = url.getPort() == -1 ? 80 : url.getPort();
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            String strF = zd3.f(vq3.a, url);
            sl3.j(url.getHost() + ":" + port, (int) (System.currentTimeMillis() - jCurrentTimeMillis), null);
            return strF;
        } catch (IOException e2) {
            sl3.j(url.getHost() + ":" + port, -1, e2);
            throw e2;
        }
    }

    /* JADX DEBUG: Class process forced to load method for inline: kh.B(java.lang.Object[], java.lang.Object[], int, int, int):void */
    public static final void e(wi wiVar) {
        d3 d3Var = wi.h;
        if (wi.i == null) {
            wi.i = new wi();
            vi viVar = new vi("Okio Watchdog");
            viVar.setDaemon(true);
            viVar.start();
        }
        long jNanoTime = System.nanoTime();
        long j2 = wiVar.c;
        boolean z = wiVar.a;
        if (j2 != 0 && z) {
            wiVar.g = Math.min(j2, wiVar.c() - jNanoTime) + jNanoTime;
        } else if (j2 != 0) {
            wiVar.g = jNanoTime + j2;
        } else {
            if (!z) {
                throw new AssertionError();
            }
            wiVar.g = wiVar.c();
        }
        d3 d3Var2 = wi.h;
        int i2 = d3Var2.a + 1;
        d3Var2.a = i2;
        wi[] wiVarArr = (wi[]) d3Var2.b;
        if (i2 == wiVarArr.length) {
            wi[] wiVarArr2 = new wi[i2 * 2];
            kh.B(wiVarArr, wiVarArr2, 0, 0, 14);
            d3Var2.b = wiVarArr2;
        }
        d3Var2.d(i2, wiVar);
        if (wiVar.f == 1) {
            wi.k.signal();
        }
    }

    public static wi g() throws InterruptedException {
        d3 d3Var = wi.h;
        wi wiVar = ((wi[]) d3Var.b)[1];
        if (wiVar == null) {
            long jNanoTime = System.nanoTime();
            wi.k.await(wi.l, TimeUnit.MILLISECONDS);
            if (((wi[]) d3Var.b)[1] != null || System.nanoTime() - jNanoTime < wi.m) {
                return null;
            }
            return wi.i;
        }
        long jNanoTime2 = wiVar.g - System.nanoTime();
        if (jNanoTime2 > 0) {
            wi.k.await(jNanoTime2, TimeUnit.NANOSECONDS);
            return null;
        }
        d3Var.e(wiVar);
        wiVar.e = 2;
        return wiVar;
    }

    public static yl2 j(RectF rectF, float[] fArr, float f2, float f3) {
        if (fArr == null) {
            return null;
        }
        float fWidth = rectF.width();
        float fHeight = rectF.height();
        yl2 yl2Var = new yl2();
        yl2Var.a = fWidth;
        yl2Var.b = fHeight;
        yl2Var.c = null;
        yl2Var.d = null;
        yl2Var.e = null;
        yl2Var.f = null;
        float[] fArr2 = new float[8];
        fArr2[0] = 0.0f;
        fArr2[1] = 0.0f;
        fArr2[2] = 0.0f;
        fArr2[3] = 0.0f;
        fArr2[4] = 0.0f;
        fArr2[5] = 0.0f;
        fArr2[6] = 0.0f;
        fArr2[7] = 0.0f;
        for (int i2 = 0; i2 < Math.min(8, fArr.length); i2++) {
            if (!Float.isNaN(fArr[i2])) {
                fArr2[i2] = fArr[i2];
            }
        }
        float f4 = fArr2[0];
        float f5 = fArr2[1];
        float f6 = fArr2[2];
        float f7 = fArr2[3];
        float f8 = fArr2[4];
        float f9 = fArr2[5];
        float f10 = fArr2[6];
        float f11 = fArr2[7];
        float f12 = f4 + f6;
        if (f12 > fWidth) {
            f4 = (f4 * fWidth) / f12;
            f6 = (f6 * fWidth) / f12;
        }
        float f13 = f7 + f9;
        if (f13 > fHeight) {
            f7 = (f7 * fHeight) / f13;
            f9 = (f9 * fHeight) / f13;
        }
        float f14 = f8 + f10;
        if (f14 > fWidth) {
            f8 = (f8 * fWidth) / f14;
            f10 = (fWidth * f10) / f14;
        }
        float f15 = f11 + f5;
        if (f15 > fHeight) {
            f11 = (f11 * fHeight) / f15;
            f5 = (fHeight * f5) / f15;
        }
        if (yl2Var.c == null) {
            yl2Var.c = new xl2();
        }
        if (yl2Var.d == null) {
            yl2Var.d = new xl2();
        }
        if (yl2Var.e == null) {
            yl2Var.e = new xl2();
        }
        if (yl2Var.f == null) {
            yl2Var.f = new xl2();
        }
        yl2Var.c.a(Math.min(f4, f5), rectF, f2, f3, 0);
        yl2Var.d.a(Math.min(f6, f7), rectF, f2, f3, 1);
        yl2Var.e.a(Math.min(f8, f9), rectF, f2, f3, 2);
        yl2Var.f.a(Math.min(f10, f11), rectF, f2, f3, 3);
        return yl2Var;
    }

    public static final boolean k(pz2 pz2Var, ge2 ge2Var) {
        pz2Var.getClass();
        if (!pz2Var.G(ge2Var)) {
            if (!(ge2Var instanceof ft)) {
                return false;
            }
            et etVarP = pz2Var.p((ft) ge2Var);
            etVarP.getClass();
            ox2 ox2VarS = pz2Var.S(etVarP);
            ox2VarS.getClass();
            lf1 lf1VarN = pz2Var.n(ox2VarS);
            if (lf1VarN == null || !pz2Var.G(pz2Var.N(lf1VarN))) {
                return false;
            }
        }
        return true;
    }

    public static final boolean l(pz2 pz2Var, yx2 yx2Var, ge2 ge2Var, ge2 ge2Var2, boolean z) {
        pz2Var.getClass();
        Collection<lf1> collectionJ = pz2Var.J(ge2Var);
        if ((collectionJ instanceof Collection) && collectionJ.isEmpty()) {
            return false;
        }
        for (lf1 lf1Var : collectionJ) {
            lf1Var.getClass();
            if (az0.j(pz2Var.a0(lf1Var), pz2Var.A(ge2Var2))) {
                return true;
            }
            if (z && E(d, yx2Var, ge2Var2, lf1Var)) {
                return true;
            }
        }
        return false;
    }

    public static List m(yx2 yx2Var, pz2 pz2Var, ge2 ge2Var, by2 by2Var) {
        w10 w10VarI;
        xx2 xx2Var = xx2.g;
        pz2Var.getClass();
        pz2Var.o0(ge2Var, by2Var);
        if (pz2Var.r(by2Var) || !pz2Var.t(ge2Var)) {
            if (!pz2Var.j0(by2Var)) {
                sl2 sl2Var = new sl2();
                yx2Var.b();
                pz2 pz2Var2 = yx2Var.c;
                ArrayDeque arrayDeque = yx2Var.g;
                arrayDeque.getClass();
                ul2 ul2Var = yx2Var.h;
                ul2Var.getClass();
                arrayDeque.push(ge2Var);
                while (!arrayDeque.isEmpty()) {
                    ge2 ge2Var2 = (ge2) arrayDeque.pop();
                    ge2Var2.getClass();
                    if (ul2Var.add(ge2Var2)) {
                        us usVar = us.b;
                        ge2 ge2VarC0 = pz2Var.c0(ge2Var2);
                        if (ge2VarC0 == null) {
                            ge2VarC0 = ge2Var2;
                        }
                        if (pz2Var.V(pz2Var.A(ge2VarC0), by2Var)) {
                            sl2Var.add(ge2VarC0);
                            w10VarI = xx2Var;
                        } else {
                            w10VarI = pz2Var.c(ge2VarC0) == 0 ? wx2.h : pz2Var2.I(ge2VarC0);
                        }
                        if (w10VarI.equals(xx2Var)) {
                            w10VarI = null;
                        }
                        if (w10VarI != null) {
                            Iterator it = pz2Var2.y(pz2Var2.A(ge2Var2)).iterator();
                            while (it.hasNext()) {
                                arrayDeque.add(w10VarI.Q(yx2Var, (lf1) it.next()));
                            }
                        }
                    }
                }
                yx2Var.a();
                return sl2Var;
            }
            if (pz2Var.V(pz2Var.A(ge2Var), by2Var)) {
                us usVar2 = us.b;
                ge2 ge2VarC1 = pz2Var.c0(ge2Var);
                if (ge2VarC1 != null) {
                    ge2Var = ge2VarC1;
                }
                return kk3.s(ge2Var);
            }
        }
        return nf0.b;
    }

    public static List n(yx2 yx2Var, pz2 pz2Var, ge2 ge2Var, by2 by2Var) {
        List listM = m(yx2Var, pz2Var, ge2Var, by2Var);
        if (listM.size() >= 2) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : listM) {
                ge2 ge2Var2 = (ge2) obj;
                ge2Var2.getClass();
                nx2 nx2VarP0 = pz2Var.p0(ge2Var2);
                int iK = pz2Var.k(nx2VarP0);
                int i2 = 0;
                while (true) {
                    if (i2 >= iK) {
                        arrayList.add(obj);
                        break;
                    }
                    ox2 ox2VarI0 = pz2Var.i0(nx2VarP0, i2);
                    ox2VarI0.getClass();
                    lf1 lf1VarN = pz2Var.n(ox2VarI0);
                    if ((lf1VarN != null ? pz2Var.Z(lf1VarN) : null) != null) {
                        break;
                    }
                    i2++;
                }
            }
            if (!arrayList.isEmpty()) {
                return arrayList;
            }
        }
        return listM;
    }

    public static String[] p(String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add("<init>(" + str + ")V");
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static boolean t(yx2 yx2Var, lf1 lf1Var, lf1 lf1Var2) {
        bi3 bi3Var = yx2Var.d;
        kk3 kk3Var = yx2Var.e;
        lf1Var.getClass();
        lf1Var2.getClass();
        pz2 pz2Var = yx2Var.c;
        if (lf1Var == lf1Var2) {
            return true;
        }
        if (B(pz2Var, lf1Var) && B(pz2Var, lf1Var2)) {
            lf1 lf1VarF = bi3Var.F(kk3Var.w(lf1Var));
            lf1 lf1VarF2 = bi3Var.F(kk3Var.w(lf1Var2));
            ge2 ge2VarW = pz2Var.w(lf1VarF);
            if (!pz2Var.V(pz2Var.a0(lf1VarF), pz2Var.a0(lf1VarF2))) {
                return false;
            }
            if (pz2Var.c(ge2VarW) == 0) {
                return pz2Var.C(lf1VarF) || pz2Var.C(lf1VarF2) || pz2Var.n0(ge2VarW) == pz2Var.n0(pz2Var.w(lf1VarF2));
            }
        }
        ro roVar = d;
        return E(roVar, yx2Var, lf1Var, lf1Var2) && E(roVar, yx2Var, lf1Var2, lf1Var);
    }

    public static Font u(FontFamily fontFamily, int i2) {
        FontStyle fontStyle = new FontStyle((i2 & 1) != 0 ? 700 : 400, (i2 & 2) != 0 ? 1 : 0);
        Font font = fontFamily.getFont(0);
        int iW = w(fontStyle, font.getStyle());
        for (int i3 = 1; i3 < fontFamily.getSize(); i3++) {
            Font font2 = fontFamily.getFont(i3);
            int iW2 = w(fontStyle, font2.getStyle());
            if (iW2 < iW) {
                font = font2;
                iW = iW2;
            }
        }
        return font;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x004b  */
    public static FontFamily v(kn0[] kn0VarArr, ContentResolver contentResolver) {
        Font fontBuild;
        FontFamily.Builder builder = null;
        for (kn0 kn0Var : kn0VarArr) {
            boolean zEquals = Objects.equals(kn0Var.a.getScheme(), "systemfont");
            Uri uri = kn0Var.a;
            String str = kn0Var.e;
            if (zEquals) {
                String authority = Objects.equals(uri.getScheme(), "systemfont") ? uri.getAuthority() : null;
                if (authority == null) {
                    fontBuild = null;
                } else {
                    Typeface typefaceCreate = Typeface.create(authority, 0);
                    Typeface typefaceCreate2 = Typeface.create(Typeface.DEFAULT, 0);
                    if (typefaceCreate == null || typefaceCreate.equals(typefaceCreate2)) {
                        typefaceCreate = null;
                    }
                    if (typefaceCreate == null || (fontBuild = a03.f(typefaceCreate)) == null) {
                        fontBuild = null;
                    } else if (!TextUtils.isEmpty(str)) {
                        try {
                            fontBuild = new Font.Builder(fontBuild).setFontVariationSettings(str).build();
                        } catch (IOException unused) {
                            Log.e("TypefaceCompatApi31Impl", "Failed to clone Font instance. Fall back to provider font.");
                            fontBuild = null;
                        }
                    }
                }
            } else {
                try {
                    ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = contentResolver.openFileDescriptor(uri, "r", null);
                    if (parcelFileDescriptorOpenFileDescriptor == null) {
                        if (parcelFileDescriptorOpenFileDescriptor != null) {
                            parcelFileDescriptorOpenFileDescriptor.close();
                        }
                        fontBuild = null;
                    } else {
                        try {
                            Font.Builder ttcIndex = new Font.Builder(parcelFileDescriptorOpenFileDescriptor).setWeight(kn0Var.c).setSlant(kn0Var.d ? 1 : 0).setTtcIndex(kn0Var.b);
                            if (!TextUtils.isEmpty(str)) {
                                ttcIndex.setFontVariationSettings(str);
                            }
                            fontBuild = ttcIndex.build();
                            parcelFileDescriptorOpenFileDescriptor.close();
                        } catch (Throwable th) {
                            try {
                                parcelFileDescriptorOpenFileDescriptor.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                            throw th;
                        }
                    }
                } catch (IOException e2) {
                    Log.w("TypefaceCompatApi29Impl", "Font load failed", e2);
                }
            }
            if (fontBuild != null) {
                if (builder == null) {
                    builder = new FontFamily.Builder(fontBuild);
                } else {
                    builder.addFont(fontBuild);
                }
            }
        }
        if (builder == null) {
            return null;
        }
        return builder.build();
    }

    public static int w(FontStyle fontStyle, FontStyle fontStyle2) {
        return (Math.abs(fontStyle.getWeight() - fontStyle2.getWeight()) / 100) + (fontStyle.getSlant() == fontStyle2.getSlant() ? 0 : 2);
    }

    public static bz2 x(pz2 pz2Var, lf1 lf1Var, ge2 ge2Var) {
        lf1 lf1VarN;
        pz2Var.getClass();
        int iC = pz2Var.c(lf1Var);
        int i2 = 0;
        while (true) {
            if (i2 >= iC) {
                return null;
            }
            ox2 ox2VarK0 = pz2Var.k0(lf1Var, i2);
            ox2VarK0.getClass();
            ox2 ox2Var = pz2Var.j(ox2VarK0) ? null : ox2VarK0;
            if (ox2Var != null && (lf1VarN = pz2Var.n(ox2Var)) != null) {
                boolean z = pz2Var.W(pz2Var.w(lf1VarN)) && pz2Var.W(pz2Var.w(ge2Var));
                if (lf1VarN.equals(ge2Var) || (z && az0.j(pz2Var.a0(lf1VarN), pz2Var.a0(ge2Var)))) {
                    by2 by2VarA0 = pz2Var.a0(lf1Var);
                    by2VarA0.getClass();
                    return pz2Var.U(by2VarA0, i2);
                }
                bz2 bz2VarX = x(pz2Var, lf1VarN, ge2Var);
                if (bz2VarX != null) {
                    return bz2VarX;
                }
            }
            i2++;
        }
    }

    public static LinkedHashSet y(String str, String... strArr) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (String str2 : strArr) {
            linkedHashSet.add(str + '.' + str2);
        }
        return linkedHashSet;
    }

    public static LinkedHashSet z(String str, String... strArr) {
        return y("java/lang/".concat(str), (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    @Override // tmsdkobf.v5
    public HandlerThread a(String str, int i2) {
        return pb.f().a(str, i2);
    }

    @Override // tmsdkobf.v5
    public void b(Runnable runnable, String str) {
        pb.f().a(runnable, str);
    }

    @Override // defpackage.f40
    public Iterable d(Object obj) {
        return (Iterable) ea1.i.get((p91) obj);
    }

    @Override // defpackage.bo1
    public boolean f(hn1 hn1Var) {
        return false;
    }

    @Override // defpackage.y82
    public if1 getType() {
        throw new IllegalStateException("This method should not be called");
    }

    @Override // defpackage.n22
    public void h() {
        Log.d("ProfileInstaller", "DIAGNOSTIC_PROFILE_IS_COMPRESSED");
    }

    @Override // defpackage.n22
    public void i(int i2, Object obj) {
        String str;
        switch (i2) {
            case 1:
                str = "RESULT_INSTALL_SUCCESS";
                break;
            case 2:
                str = "RESULT_ALREADY_INSTALLED";
                break;
            case 3:
                str = "RESULT_UNSUPPORTED_ART_VERSION";
                break;
            case 4:
                str = "RESULT_NOT_WRITABLE";
                break;
            case 5:
                str = "RESULT_DESIRED_FORMAT_UNSUPPORTED";
                break;
            case 6:
                str = "RESULT_BASELINE_PROFILE_NOT_FOUND";
                break;
            case 7:
                str = "RESULT_IO_EXCEPTION";
                break;
            case 8:
                str = "RESULT_PARSE_EXCEPTION";
                break;
            case 9:
            default:
                str = a.c;
                break;
            case 10:
                str = "RESULT_INSTALL_SKIP_FILE_SUCCESS";
                break;
            case 11:
                str = "RESULT_DELETE_SKIP_FILE_SUCCESS";
                break;
        }
        if (i2 == 6 || i2 == 7 || i2 == 8) {
            Log.e("ProfileInstaller", str, (Throwable) obj);
        } else {
            Log.d("ProfileInstaller", str);
        }
    }

    /* JADX WARN: Code duplicated, block: B:131:0x0220  */
    /* JADX WARN: Code duplicated, block: B:159:0x0282  */
    /* JADX WARN: Code duplicated, block: B:170:0x02ac  */
    /* JADX WARN: Code duplicated, block: B:173:0x02ba  */
    /* JADX WARN: Code duplicated, block: B:175:0x02c3  */
    /* JADX WARN: Code duplicated, block: B:177:0x02cb  */
    /* JADX WARN: Code duplicated, block: B:185:0x02f7  */
    /* JADX WARN: Code duplicated, block: B:189:0x030c  */
    /* JADX WARN: Code duplicated, block: B:190:0x0311  */
    /* JADX WARN: Code duplicated, block: B:194:0x0319  */
    /* JADX WARN: Code duplicated, block: B:200:0x032f A[LOOP:9: B:198:0x0329->B:200:0x032f, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:204:0x0357 A[LOOP:10: B:202:0x0351->B:204:0x0357, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:209:0x0383  */
    /* JADX WARN: Code duplicated, block: B:212:0x039c  */
    /* JADX WARN: Code duplicated, block: B:216:0x03a7 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:217:0x03a9  */
    /* JADX WARN: Code duplicated, block: B:219:0x03ba  */
    /* JADX WARN: Code duplicated, block: B:222:0x03cc  */
    /* JADX WARN: Code duplicated, block: B:225:0x03df  */
    /* JADX WARN: Code duplicated, block: B:227:0x03ee  */
    /* JADX WARN: Code duplicated, block: B:22:0x0080  */
    /* JADX WARN: Code duplicated, block: B:230:0x03f9  */
    /* JADX WARN: Code duplicated, block: B:232:0x03fc  */
    /* JADX WARN: Code duplicated, block: B:241:0x044f  */
    /* JADX WARN: Code duplicated, block: B:244:0x045b  */
    /* JADX WARN: Code duplicated, block: B:249:0x0472  */
    /* JADX WARN: Code duplicated, block: B:24:0x008d  */
    /* JADX WARN: Code duplicated, block: B:251:0x0484  */
    /* JADX WARN: Code duplicated, block: B:253:0x0493  */
    /* JADX WARN: Code duplicated, block: B:255:0x0498  */
    /* JADX WARN: Code duplicated, block: B:258:0x04a6  */
    /* JADX WARN: Code duplicated, block: B:261:0x04bc  */
    /* JADX WARN: Code duplicated, block: B:265:0x04d1  */
    /* JADX WARN: Code duplicated, block: B:266:0x04d3  */
    /* JADX WARN: Code duplicated, block: B:270:0x04db  */
    /* JADX WARN: Code duplicated, block: B:276:0x04f1  */
    /* JADX WARN: Code duplicated, block: B:280:0x050f A[LOOP:7: B:274:0x04eb->B:280:0x050f, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:296:0x039d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:297:0x0408 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:298:0x0408 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:29:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:300:0x0446 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:301:0x03c6 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:304:0x0464 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:306:0x0455 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:309:0x04cb A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:310:0x04df A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:311:0x050b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:313:0x04b6 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:314:0x04b6 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:317:0x0306 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:318:0x031d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:320:0x02f1 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:321:0x02f1 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:32:0x00af  */
    /* JADX WARN: Code duplicated, block: B:330:0x00bd A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:331:0x00a3 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:332:? A[LOOP:12: B:30:0x00a9->B:332:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:333:0x0111 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:334:0x00fe A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:335:? A[LOOP:13: B:46:0x00ed->B:335:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:336:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:337:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:36:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:38:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:40:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:45:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:48:0x00f3  */
    /* JADX WARN: Code duplicated, block: B:51:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:53:0x0104  */
    /* JADX WARN: Code duplicated, block: B:56:0x0111  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r19v0, types: [java.lang.Object, pz2] */
    /* JADX WARN: Type inference failed for: r6v30 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8, types: [int] */
    public boolean o(yx2 yx2Var, pz2 pz2Var, lf1 lf1Var, lf1 lf1Var2) {
        Boolean boolValueOf;
        Boolean bool;
        by2 by2VarA;
        sl2<ge2> sl2Var;
        ArrayDeque arrayDeque;
        ul2 ul2Var;
        boolean z;
        ArrayList arrayList;
        List listM;
        ge2 ge2Var;
        w10 w10Var;
        Iterator it;
        ArrayList<ge2> arrayList2;
        int size;
        by2 by2VarA2;
        by2 by2VarA3;
        ArrayDeque arrayDeque2;
        ul2 ul2Var2;
        ge2 ge2Var2;
        w10 w10Var2;
        Iterator it2;
        ge2 ge2VarQ;
        by2 by2VarA4;
        mg mgVar;
        int iM;
        ?? r6;
        boolean zD;
        boolean zD2;
        ox2 ox2VarU;
        lf1 lf1VarN;
        ge2 ge2VarD0;
        by2 by2VarA5;
        by2 by2VarA6;
        bz2 bz2VarX;
        Collection collectionY;
        Iterator it3;
        Collection collectionY2;
        Iterator it4;
        boolean z2;
        lf1Var.getClass();
        kk3 kk3Var = yx2Var.e;
        lf1 lf1VarW = kk3Var.w(lf1Var);
        bi3 bi3Var = yx2Var.d;
        lf1 lf1VarF = bi3Var.F(lf1VarW);
        lf1Var2.getClass();
        lf1 lf1VarF2 = bi3Var.F(kk3Var.w(lf1Var2));
        pz2Var.getClass();
        lf1VarF.getClass();
        ge2 ge2VarW = pz2Var.w(lf1VarF);
        lf1VarF2.getClass();
        ge2 ge2VarN = pz2Var.N(lf1VarF2);
        boolean z3 = false;
        boolean z4 = true;
        if (!pz2Var.s0(ge2VarW) && !pz2Var.s0(ge2VarN)) {
            pz2Var.Y(ge2VarW);
            pz2Var.L(ge2VarW);
            pz2Var.L(ge2VarN);
            ft ftVarE0 = pz2Var.e0(ge2VarN);
            lf1 lf1VarX = ftVarE0 != null ? pz2Var.x(ftVarE0) : null;
            if (ftVarE0 == null || lf1VarX == null) {
                by2VarA5 = pz2Var.A(ge2VarN);
                by2VarA5.getClass();
                if (pz2Var.o(by2VarA5)) {
                    pz2Var.n0(ge2VarN);
                    collectionY2 = pz2Var.y(by2VarA5);
                    if (!(collectionY2 instanceof Collection) && collectionY2.isEmpty()) {
                        z2 = true;
                        break;
                    }
                    it4 = collectionY2.iterator();
                    while (true) {
                        if (!it4.hasNext()) {
                            z2 = true;
                            break;
                        }
                        if (!E(d, yx2Var, ge2VarW, (lf1) it4.next())) {
                            z2 = false;
                            break;
                        }
                    }
                    boolValueOf = Boolean.valueOf(z2);
                } else {
                    by2VarA6 = pz2Var.A(ge2VarW);
                    if (ge2VarW instanceof ft) {
                        bz2VarX = x(pz2Var, ge2VarN, ge2VarW);
                        if (bz2VarX == null) {
                            boolValueOf = null;
                        } else {
                            boolValueOf = null;
                        }
                    } else {
                        by2VarA6.getClass();
                        if (pz2Var.o(by2VarA6)) {
                            collectionY = pz2Var.y(by2VarA6);
                            if ((collectionY instanceof Collection) || !collectionY.isEmpty()) {
                                it3 = collectionY.iterator();
                                while (true) {
                                    if (!it3.hasNext()) {
                                        bz2VarX = x(pz2Var, ge2VarN, ge2VarW);
                                        if (bz2VarX == null && pz2Var.b(bz2VarX, pz2Var.A(ge2VarN))) {
                                            boolValueOf = Boolean.TRUE;
                                        }
                                    } else if (!(((lf1) it3.next()) instanceof ft)) {
                                    }
                                }
                            } else {
                                bz2VarX = x(pz2Var, ge2VarN, ge2VarW);
                                if (bz2VarX == null) {
                                }
                            }
                        }
                        boolValueOf = null;
                    }
                }
            } else {
                if (pz2Var.n0(ge2VarN)) {
                    lf1VarX = pz2Var.r0(lf1VarX);
                } else if (pz2Var.F(ge2VarN)) {
                    lf1VarX = pz2Var.K(lf1VarX);
                }
                ux2[] ux2VarArr = ux2.b;
                if (E(this, yx2Var, ge2VarW, lf1VarX)) {
                    boolValueOf = Boolean.TRUE;
                } else {
                    by2VarA5 = pz2Var.A(ge2VarN);
                    by2VarA5.getClass();
                    if (pz2Var.o(by2VarA5)) {
                        pz2Var.n0(ge2VarN);
                        collectionY2 = pz2Var.y(by2VarA5);
                        if (!(collectionY2 instanceof Collection)) {
                            it4 = collectionY2.iterator();
                            while (true) {
                                if (!it4.hasNext()) {
                                    z2 = true;
                                    break;
                                }
                                if (!E(d, yx2Var, ge2VarW, (lf1) it4.next())) {
                                    z2 = false;
                                    break;
                                }
                            }
                        } else {
                            it4 = collectionY2.iterator();
                            while (true) {
                                if (!it4.hasNext()) {
                                    z2 = true;
                                    break;
                                }
                                if (!E(d, yx2Var, ge2VarW, (lf1) it4.next())) {
                                    z2 = false;
                                    break;
                                }
                            }
                        }
                        boolValueOf = Boolean.valueOf(z2);
                    } else {
                        by2VarA6 = pz2Var.A(ge2VarW);
                        if (ge2VarW instanceof ft) {
                            by2VarA6.getClass();
                            if (pz2Var.o(by2VarA6)) {
                                collectionY = pz2Var.y(by2VarA6);
                                if (collectionY instanceof Collection) {
                                    it3 = collectionY.iterator();
                                    while (true) {
                                        if (!it3.hasNext()) {
                                            bz2VarX = x(pz2Var, ge2VarN, ge2VarW);
                                            if (bz2VarX == null) {
                                            }
                                        } else if (!(((lf1) it3.next()) instanceof ft)) {
                                        }
                                    }
                                } else {
                                    it3 = collectionY.iterator();
                                    while (true) {
                                        if (!it3.hasNext()) {
                                            bz2VarX = x(pz2Var, ge2VarN, ge2VarW);
                                            if (bz2VarX == null) {
                                            }
                                        } else if (!(((lf1) it3.next()) instanceof ft)) {
                                        }
                                    }
                                }
                            }
                            boolValueOf = null;
                        } else {
                            bz2VarX = x(pz2Var, ge2VarN, ge2VarW);
                            if (bz2VarX == null) {
                                boolValueOf = null;
                            } else {
                                boolValueOf = null;
                            }
                        }
                    }
                }
            }
        } else if (yx2Var.a) {
            boolValueOf = Boolean.TRUE;
        } else if (!pz2Var.n0(ge2VarW) || pz2Var.n0(ge2VarN)) {
            if (!pz2Var.s0(ge2VarW)) {
                ge2VarW = pz2Var.f(ge2VarW);
            }
            if (!pz2Var.s0(ge2VarN)) {
                ge2VarN = pz2Var.f(ge2VarN);
            }
            ge2VarW.getClass();
            ge2VarN.getClass();
            boolValueOf = Boolean.valueOf(vh3.C(pz2Var, ge2VarW, ge2VarN));
        } else {
            boolValueOf = Boolean.FALSE;
        }
        if (boolValueOf != null) {
            return boolValueOf.booleanValue();
        }
        ge2 ge2VarW2 = pz2Var.w(lf1VarF);
        ge2 ge2VarN2 = pz2Var.N(lf1VarF2);
        xx2 xx2Var = xx2.g;
        wx2 wx2Var = wx2.h;
        pz2 pz2Var2 = yx2Var.c;
        if (!pz2Var2.n0(ge2VarN2) && !pz2Var2.X(ge2VarW2) && !pz2Var2.F(ge2VarW2) && ((!(ge2VarW2 instanceof ft) || !pz2Var2.g0((ft) ge2VarW2)) && !gd3.v(yx2Var, ge2VarW2, wx2Var))) {
            if (pz2Var2.F(ge2VarN2) || gd3.v(yx2Var, ge2VarN2, wx2.i) || pz2Var2.t(ge2VarW2)) {
                return false;
            }
            by2 by2VarA7 = pz2Var2.A(ge2VarN2);
            by2VarA7.getClass();
            if (!gd3.x(yx2Var, ge2VarW2, by2VarA7)) {
                yx2Var.b();
                ArrayDeque arrayDeque3 = yx2Var.g;
                arrayDeque3.getClass();
                ul2 ul2Var3 = yx2Var.h;
                ul2Var3.getClass();
                arrayDeque3.push(ge2VarW2);
                loop0: while (true) {
                    if (arrayDeque3.isEmpty()) {
                        yx2Var.a();
                        return false;
                    }
                    ge2 ge2Var3 = (ge2) arrayDeque3.pop();
                    ge2Var3.getClass();
                    if (ul2Var3.add(ge2Var3)) {
                        w10 w10Var3 = pz2Var2.n0(ge2Var3) ? xx2Var : wx2Var;
                        if (w10Var3.equals(xx2Var)) {
                            w10Var3 = null;
                        }
                        if (w10Var3 == null) {
                            continue;
                        } else {
                            Iterator it5 = pz2Var2.y(pz2Var2.A(ge2Var3)).iterator();
                            while (it5.hasNext()) {
                                ge2 ge2VarQ2 = w10Var3.Q(yx2Var, (lf1) it5.next());
                                if (gd3.x(yx2Var, ge2VarQ2, by2VarA7)) {
                                    yx2Var.a();
                                    break loop0;
                                }
                                arrayDeque3.add(ge2VarQ2);
                            }
                        }
                    }
                }
            }
        }
        if (!pz2Var.G(ge2VarW2) && !pz2Var.G(ge2VarN2)) {
            bool = null;
        } else if (k(pz2Var, ge2VarW2) && k(pz2Var, ge2VarN2)) {
            bool = Boolean.TRUE;
        } else if (pz2Var.G(ge2VarW2)) {
            if (l(pz2Var, yx2Var, ge2VarW2, ge2VarN2, false)) {
                bool = Boolean.TRUE;
            } else {
                bool = null;
            }
        } else if (pz2Var.G(ge2VarN2)) {
            by2 by2VarA8 = pz2Var.A(ge2VarW2);
            if (by2VarA8 instanceof zy0) {
                Collection collectionY3 = pz2Var.y(by2VarA8);
                if (!(collectionY3 instanceof Collection) || !collectionY3.isEmpty()) {
                    Iterator it6 = collectionY3.iterator();
                    while (true) {
                        if (it6.hasNext()) {
                            lf1 lf1Var3 = (lf1) it6.next();
                            lf1Var3.getClass();
                            ge2 ge2VarD1 = pz2Var.d0(lf1Var3);
                            if (ge2VarD1 == null || !pz2Var.G(ge2VarD1)) {
                            }
                        } else if (!l(pz2Var, yx2Var, ge2VarN2, ge2VarW2, true)) {
                            bool = null;
                        }
                    }
                } else if (!l(pz2Var, yx2Var, ge2VarN2, ge2VarW2, true)) {
                    bool = null;
                }
            } else if (!l(pz2Var, yx2Var, ge2VarN2, ge2VarW2, true)) {
                bool = null;
            }
            bool = Boolean.TRUE;
        } else {
            bool = null;
        }
        if (bool != null) {
            return bool.booleanValue();
        }
        by2 by2VarA9 = pz2Var.A(ge2VarN2);
        if (pz2Var.V(pz2Var.A(ge2VarW2), by2VarA9)) {
            by2VarA9.getClass();
            if (pz2Var.M(by2VarA9) != 0) {
                by2VarA = pz2Var.A(ge2VarN2);
                by2VarA.getClass();
                if (!pz2Var.f0(by2VarA)) {
                    by2VarA9.getClass();
                    if (pz2Var2.t(ge2VarW2)) {
                        if (!pz2Var2.r(by2VarA9) || pz2Var2.b0(by2VarA9)) {
                            sl2Var = new sl2();
                            yx2Var.b();
                            arrayDeque = yx2Var.g;
                            arrayDeque.getClass();
                            ul2Var = yx2Var.h;
                            ul2Var.getClass();
                            arrayDeque.push(ge2VarW2);
                            while (!arrayDeque.isEmpty()) {
                                ge2Var = (ge2) arrayDeque.pop();
                                ge2Var.getClass();
                                if (!ul2Var.add(ge2Var)) {
                                    if (pz2Var2.t(ge2Var)) {
                                        sl2Var.add(ge2Var);
                                        w10Var = xx2Var;
                                    } else {
                                        w10Var = wx2Var;
                                    }
                                    if (w10Var.equals(xx2Var)) {
                                        w10Var = null;
                                    }
                                    if (w10Var == null) {
                                        it = pz2Var2.y(pz2Var2.A(ge2Var)).iterator();
                                        while (it.hasNext()) {
                                            arrayDeque.add(w10Var.Q(yx2Var, (lf1) it.next()));
                                            z3 = z3;
                                        }
                                    }
                                }
                            }
                            z = z3;
                            yx2Var.a();
                            arrayList = new ArrayList();
                            for (ge2 ge2Var4 : sl2Var) {
                                ge2Var4.getClass();
                                gy.I(arrayList, n(yx2Var, pz2Var2, ge2Var4, by2VarA9));
                            }
                            listM = arrayList;
                        } else {
                            listM = m(yx2Var, pz2Var2, ge2VarW2, by2VarA9);
                        }
                        listM.size();
                        List<ge2> list = listM;
                        arrayList2 = new ArrayList(cy.G(list));
                        for (ge2 ge2Var5 : list) {
                            ge2Var5.getClass();
                            lf1 lf1VarF3 = yx2Var.d.F(ge2Var5);
                            lf1VarF3.getClass();
                            ge2VarD0 = pz2Var.d0(lf1VarF3);
                            if (ge2VarD0 == null) {
                                ge2Var5 = ge2VarD0;
                            }
                            arrayList2.add(ge2Var5);
                        }
                        size = arrayList2.size();
                        if (size != 0) {
                            by2VarA2 = pz2Var.A(ge2VarW2);
                            by2VarA2.getClass();
                            if (pz2Var.r(by2VarA2)) {
                                return pz2Var.h0(by2VarA2);
                            }
                            by2VarA3 = pz2Var.A(ge2VarW2);
                            by2VarA3.getClass();
                            if (pz2Var.h0(by2VarA3)) {
                                return true;
                            }
                            yx2Var.b();
                            arrayDeque2 = yx2Var.g;
                            arrayDeque2.getClass();
                            ul2Var2 = yx2Var.h;
                            ul2Var2.getClass();
                            arrayDeque2.push(ge2VarW2);
                            while (!arrayDeque2.isEmpty()) {
                                ge2Var2 = (ge2) arrayDeque2.pop();
                                ge2Var2.getClass();
                                if (!ul2Var2.add(ge2Var2)) {
                                    if (pz2Var.t(ge2Var2)) {
                                        w10Var2 = xx2Var;
                                    } else {
                                        w10Var2 = wx2Var;
                                    }
                                    if (w10Var2.equals(xx2Var)) {
                                        w10Var2 = null;
                                    }
                                    if (w10Var2 == null) {
                                        continue;
                                    } else {
                                        it2 = pz2Var2.y(pz2Var2.A(ge2Var2)).iterator();
                                        while (it2.hasNext()) {
                                            ge2VarQ = w10Var2.Q(yx2Var, (lf1) it2.next());
                                            ge2VarQ.getClass();
                                            by2VarA4 = pz2Var.A(ge2VarQ);
                                            by2VarA4.getClass();
                                            if (pz2Var.h0(by2VarA4)) {
                                                yx2Var.a();
                                                return true;
                                            }
                                            arrayDeque2.add(ge2VarQ);
                                        }
                                    }
                                }
                            }
                            yx2Var.a();
                            return z;
                        }
                        if (size != 1) {
                            ge2 ge2Var6 = (ge2) zx.R(arrayList2);
                            ge2Var6.getClass();
                            return D(yx2Var, pz2Var, pz2Var.p0(ge2Var6), ge2VarN2);
                        }
                        mgVar = new mg(pz2Var.M(by2VarA9));
                        iM = pz2Var.M(by2VarA9);
                        r6 = z;
                        while (true) {
                            if (r6 < iM) {
                                zD = D(yx2Var, pz2Var, mgVar, ge2VarN2);
                                break;
                            }
                            if (pz2Var.v(pz2Var.U(by2VarA9, r6)) != vz2.OUT) {
                                zD = z;
                                break;
                            }
                            ArrayList arrayList3 = new ArrayList(cy.G(arrayList2));
                            for (ge2 ge2Var7 : arrayList2) {
                                ge2Var7.getClass();
                                ox2VarU = pz2Var.u(ge2Var7, r6);
                                if (ox2VarU != null) {
                                    boolean z5 = z4;
                                    if (pz2Var.l(ox2VarU) != vz2.INV) {
                                        ox2VarU = null;
                                    }
                                    if (ox2VarU == null && (lf1VarN = pz2Var.n(ox2VarU)) != null) {
                                        arrayList3.add(lf1VarN);
                                        z4 = z5;
                                    }
                                }
                                throw new IllegalStateException(("Incorrect type: " + ge2Var7 + ", subType: " + ge2VarW2 + ", superType: " + ge2VarN2).toString());
                            }
                            boolean z6 = z4;
                            lf1 lf1VarQ = pz2Var.Q(arrayList3);
                            lf1VarQ.getClass();
                            mgVar.add(pz2Var.R(lf1VarQ));
                            z4 = z6;
                            r6++;
                        }
                        if (zD) {
                            return z4;
                        }
                        zD2 = z;
                        for (ge2 ge2Var8 : arrayList2) {
                            if (zD2) {
                                ge2Var8.getClass();
                                zD2 = D(yx2Var, pz2Var, pz2Var.p0(ge2Var8), ge2VarN2);
                            }
                        }
                        return zD2;
                    }
                    listM = n(yx2Var, pz2Var2, ge2VarW2, by2VarA9);
                    z = false;
                    listM.size();
                    List<ge2> list2 = listM;
                    arrayList2 = new ArrayList(cy.G(list2));
                    while (r11.hasNext()) {
                        ge2Var5.getClass();
                        lf1 lf1VarF4 = yx2Var.d.F(ge2Var5);
                        lf1VarF4.getClass();
                        ge2VarD0 = pz2Var.d0(lf1VarF4);
                        if (ge2VarD0 == null) {
                            ge2Var5 = ge2VarD0;
                        }
                        arrayList2.add(ge2Var5);
                    }
                    size = arrayList2.size();
                    if (size != 0) {
                        by2VarA2 = pz2Var.A(ge2VarW2);
                        by2VarA2.getClass();
                        if (pz2Var.r(by2VarA2)) {
                            return pz2Var.h0(by2VarA2);
                        }
                        by2VarA3 = pz2Var.A(ge2VarW2);
                        by2VarA3.getClass();
                        if (pz2Var.h0(by2VarA3)) {
                            return true;
                        }
                        yx2Var.b();
                        arrayDeque2 = yx2Var.g;
                        arrayDeque2.getClass();
                        ul2Var2 = yx2Var.h;
                        ul2Var2.getClass();
                        arrayDeque2.push(ge2VarW2);
                        while (!arrayDeque2.isEmpty()) {
                            ge2Var2 = (ge2) arrayDeque2.pop();
                            ge2Var2.getClass();
                            if (!ul2Var2.add(ge2Var2)) {
                                if (pz2Var.t(ge2Var2)) {
                                    w10Var2 = xx2Var;
                                } else {
                                    w10Var2 = wx2Var;
                                }
                                if (w10Var2.equals(xx2Var)) {
                                    w10Var2 = null;
                                }
                                if (w10Var2 == null) {
                                    continue;
                                } else {
                                    it2 = pz2Var2.y(pz2Var2.A(ge2Var2)).iterator();
                                    while (it2.hasNext()) {
                                        ge2VarQ = w10Var2.Q(yx2Var, (lf1) it2.next());
                                        ge2VarQ.getClass();
                                        by2VarA4 = pz2Var.A(ge2VarQ);
                                        by2VarA4.getClass();
                                        if (pz2Var.h0(by2VarA4)) {
                                            yx2Var.a();
                                            return true;
                                        }
                                        arrayDeque2.add(ge2VarQ);
                                    }
                                }
                            }
                        }
                        yx2Var.a();
                        return z;
                    }
                    if (size != 1) {
                        ge2 ge2Var9 = (ge2) zx.R(arrayList2);
                        ge2Var9.getClass();
                        return D(yx2Var, pz2Var, pz2Var.p0(ge2Var9), ge2VarN2);
                    }
                    mgVar = new mg(pz2Var.M(by2VarA9));
                    iM = pz2Var.M(by2VarA9);
                    r6 = z;
                    while (true) {
                        if (r6 < iM) {
                            zD = D(yx2Var, pz2Var, mgVar, ge2VarN2);
                            break;
                        }
                        if (pz2Var.v(pz2Var.U(by2VarA9, r6)) != vz2.OUT) {
                            zD = z;
                            break;
                        }
                        ArrayList arrayList4 = new ArrayList(cy.G(arrayList2));
                        while (r12.hasNext()) {
                            ge2Var7.getClass();
                            ox2VarU = pz2Var.u(ge2Var7, r6);
                            if (ox2VarU != null) {
                                boolean z7 = z4;
                                if (pz2Var.l(ox2VarU) != vz2.INV) {
                                    ox2VarU = null;
                                }
                                if (ox2VarU == null) {
                                }
                            }
                            throw new IllegalStateException(("Incorrect type: " + ge2Var7 + ", subType: " + ge2VarW2 + ", superType: " + ge2VarN2).toString());
                        }
                        boolean z8 = z4;
                        lf1 lf1VarQ2 = pz2Var.Q(arrayList4);
                        lf1VarQ2.getClass();
                        mgVar.add(pz2Var.R(lf1VarQ2));
                        z4 = z8;
                        r6++;
                    }
                    if (zD) {
                        return z4;
                    }
                    zD2 = z;
                    while (r3.hasNext()) {
                        if (zD2) {
                            ge2Var8.getClass();
                            zD2 = D(yx2Var, pz2Var, pz2Var.p0(ge2Var8), ge2VarN2);
                        }
                    }
                    return zD2;
                }
            }
        } else {
            by2VarA = pz2Var.A(ge2VarN2);
            by2VarA.getClass();
            if (!pz2Var.f0(by2VarA)) {
                by2VarA9.getClass();
                if (pz2Var2.t(ge2VarW2)) {
                    if (pz2Var2.r(by2VarA9)) {
                    }
                    sl2Var = new sl2();
                    yx2Var.b();
                    arrayDeque = yx2Var.g;
                    arrayDeque.getClass();
                    ul2Var = yx2Var.h;
                    ul2Var.getClass();
                    arrayDeque.push(ge2VarW2);
                    while (!arrayDeque.isEmpty()) {
                        ge2Var = (ge2) arrayDeque.pop();
                        ge2Var.getClass();
                        if (!ul2Var.add(ge2Var)) {
                            if (pz2Var2.t(ge2Var)) {
                                sl2Var.add(ge2Var);
                                w10Var = xx2Var;
                            } else {
                                w10Var = wx2Var;
                            }
                            if (w10Var.equals(xx2Var)) {
                                w10Var = null;
                            }
                            if (w10Var == null) {
                                it = pz2Var2.y(pz2Var2.A(ge2Var)).iterator();
                                while (it.hasNext()) {
                                    arrayDeque.add(w10Var.Q(yx2Var, (lf1) it.next()));
                                    z3 = z3;
                                }
                            }
                        }
                    }
                    z = z3;
                    yx2Var.a();
                    arrayList = new ArrayList();
                    while (r11.hasNext()) {
                        ge2Var4.getClass();
                        gy.I(arrayList, n(yx2Var, pz2Var2, ge2Var4, by2VarA9));
                    }
                    listM = arrayList;
                    listM.size();
                    List<ge2> list3 = listM;
                    arrayList2 = new ArrayList(cy.G(list3));
                    while (r11.hasNext()) {
                        ge2Var5.getClass();
                        lf1 lf1VarF5 = yx2Var.d.F(ge2Var5);
                        lf1VarF5.getClass();
                        ge2VarD0 = pz2Var.d0(lf1VarF5);
                        if (ge2VarD0 == null) {
                            ge2Var5 = ge2VarD0;
                        }
                        arrayList2.add(ge2Var5);
                    }
                    size = arrayList2.size();
                    if (size != 0) {
                        by2VarA2 = pz2Var.A(ge2VarW2);
                        by2VarA2.getClass();
                        if (pz2Var.r(by2VarA2)) {
                            return pz2Var.h0(by2VarA2);
                        }
                        by2VarA3 = pz2Var.A(ge2VarW2);
                        by2VarA3.getClass();
                        if (pz2Var.h0(by2VarA3)) {
                            return true;
                        }
                        yx2Var.b();
                        arrayDeque2 = yx2Var.g;
                        arrayDeque2.getClass();
                        ul2Var2 = yx2Var.h;
                        ul2Var2.getClass();
                        arrayDeque2.push(ge2VarW2);
                        while (!arrayDeque2.isEmpty()) {
                            ge2Var2 = (ge2) arrayDeque2.pop();
                            ge2Var2.getClass();
                            if (!ul2Var2.add(ge2Var2)) {
                                if (pz2Var.t(ge2Var2)) {
                                    w10Var2 = xx2Var;
                                } else {
                                    w10Var2 = wx2Var;
                                }
                                if (w10Var2.equals(xx2Var)) {
                                    w10Var2 = null;
                                }
                                if (w10Var2 == null) {
                                    continue;
                                } else {
                                    it2 = pz2Var2.y(pz2Var2.A(ge2Var2)).iterator();
                                    while (it2.hasNext()) {
                                        ge2VarQ = w10Var2.Q(yx2Var, (lf1) it2.next());
                                        ge2VarQ.getClass();
                                        by2VarA4 = pz2Var.A(ge2VarQ);
                                        by2VarA4.getClass();
                                        if (pz2Var.h0(by2VarA4)) {
                                            yx2Var.a();
                                            return true;
                                        }
                                        arrayDeque2.add(ge2VarQ);
                                    }
                                }
                            }
                        }
                        yx2Var.a();
                        return z;
                    }
                    if (size != 1) {
                        ge2 ge2Var10 = (ge2) zx.R(arrayList2);
                        ge2Var10.getClass();
                        return D(yx2Var, pz2Var, pz2Var.p0(ge2Var10), ge2VarN2);
                    }
                    mgVar = new mg(pz2Var.M(by2VarA9));
                    iM = pz2Var.M(by2VarA9);
                    r6 = z;
                    while (true) {
                        if (r6 < iM) {
                            zD = D(yx2Var, pz2Var, mgVar, ge2VarN2);
                            break;
                        }
                        if (pz2Var.v(pz2Var.U(by2VarA9, r6)) != vz2.OUT) {
                            zD = z;
                            break;
                        }
                        ArrayList arrayList5 = new ArrayList(cy.G(arrayList2));
                        while (r12.hasNext()) {
                            ge2Var7.getClass();
                            ox2VarU = pz2Var.u(ge2Var7, r6);
                            if (ox2VarU != null) {
                                boolean z9 = z4;
                                if (pz2Var.l(ox2VarU) != vz2.INV) {
                                    ox2VarU = null;
                                }
                                if (ox2VarU == null) {
                                }
                            }
                            throw new IllegalStateException(("Incorrect type: " + ge2Var7 + ", subType: " + ge2VarW2 + ", superType: " + ge2VarN2).toString());
                        }
                        boolean z10 = z4;
                        lf1 lf1VarQ3 = pz2Var.Q(arrayList5);
                        lf1VarQ3.getClass();
                        mgVar.add(pz2Var.R(lf1VarQ3));
                        z4 = z10;
                        r6++;
                    }
                    if (zD) {
                        return z4;
                    }
                    zD2 = z;
                    while (r3.hasNext()) {
                        if (zD2) {
                            ge2Var8.getClass();
                            zD2 = D(yx2Var, pz2Var, pz2Var.p0(ge2Var8), ge2VarN2);
                        }
                    }
                    return zD2;
                }
                listM = n(yx2Var, pz2Var2, ge2VarW2, by2VarA9);
                z = false;
                listM.size();
                List<ge2> list4 = listM;
                arrayList2 = new ArrayList(cy.G(list4));
                while (r11.hasNext()) {
                    ge2Var5.getClass();
                    lf1 lf1VarF6 = yx2Var.d.F(ge2Var5);
                    lf1VarF6.getClass();
                    ge2VarD0 = pz2Var.d0(lf1VarF6);
                    if (ge2VarD0 == null) {
                        ge2Var5 = ge2VarD0;
                    }
                    arrayList2.add(ge2Var5);
                }
                size = arrayList2.size();
                if (size != 0) {
                    by2VarA2 = pz2Var.A(ge2VarW2);
                    by2VarA2.getClass();
                    if (pz2Var.r(by2VarA2)) {
                        return pz2Var.h0(by2VarA2);
                    }
                    by2VarA3 = pz2Var.A(ge2VarW2);
                    by2VarA3.getClass();
                    if (pz2Var.h0(by2VarA3)) {
                        return true;
                    }
                    yx2Var.b();
                    arrayDeque2 = yx2Var.g;
                    arrayDeque2.getClass();
                    ul2Var2 = yx2Var.h;
                    ul2Var2.getClass();
                    arrayDeque2.push(ge2VarW2);
                    while (!arrayDeque2.isEmpty()) {
                        ge2Var2 = (ge2) arrayDeque2.pop();
                        ge2Var2.getClass();
                        if (!ul2Var2.add(ge2Var2)) {
                            if (pz2Var.t(ge2Var2)) {
                                w10Var2 = xx2Var;
                            } else {
                                w10Var2 = wx2Var;
                            }
                            if (w10Var2.equals(xx2Var)) {
                                w10Var2 = null;
                            }
                            if (w10Var2 == null) {
                                continue;
                            } else {
                                it2 = pz2Var2.y(pz2Var2.A(ge2Var2)).iterator();
                                while (it2.hasNext()) {
                                    ge2VarQ = w10Var2.Q(yx2Var, (lf1) it2.next());
                                    ge2VarQ.getClass();
                                    by2VarA4 = pz2Var.A(ge2VarQ);
                                    by2VarA4.getClass();
                                    if (pz2Var.h0(by2VarA4)) {
                                        yx2Var.a();
                                        return true;
                                    }
                                    arrayDeque2.add(ge2VarQ);
                                }
                            }
                        }
                    }
                    yx2Var.a();
                    return z;
                }
                if (size != 1) {
                    ge2 ge2Var11 = (ge2) zx.R(arrayList2);
                    ge2Var11.getClass();
                    return D(yx2Var, pz2Var, pz2Var.p0(ge2Var11), ge2VarN2);
                }
                mgVar = new mg(pz2Var.M(by2VarA9));
                iM = pz2Var.M(by2VarA9);
                r6 = z;
                while (true) {
                    if (r6 < iM) {
                        zD = D(yx2Var, pz2Var, mgVar, ge2VarN2);
                        break;
                    }
                    if (pz2Var.v(pz2Var.U(by2VarA9, r6)) != vz2.OUT) {
                        zD = z;
                        break;
                    }
                    ArrayList arrayList6 = new ArrayList(cy.G(arrayList2));
                    while (r12.hasNext()) {
                        ge2Var7.getClass();
                        ox2VarU = pz2Var.u(ge2Var7, r6);
                        if (ox2VarU != null) {
                            boolean z11 = z4;
                            if (pz2Var.l(ox2VarU) != vz2.INV) {
                                ox2VarU = null;
                            }
                            if (ox2VarU == null) {
                            }
                        }
                        throw new IllegalStateException(("Incorrect type: " + ge2Var7 + ", subType: " + ge2VarW2 + ", superType: " + ge2VarN2).toString());
                    }
                    boolean z12 = z4;
                    lf1 lf1VarQ4 = pz2Var.Q(arrayList6);
                    lf1VarQ4.getClass();
                    mgVar.add(pz2Var.R(lf1VarQ4));
                    z4 = z12;
                    r6++;
                }
                if (zD) {
                    return z4;
                }
                zD2 = z;
                while (r3.hasNext()) {
                    if (zD2) {
                        ge2Var8.getClass();
                        zD2 = D(yx2Var, pz2Var, pz2Var.p0(ge2Var8), ge2VarN2);
                    }
                }
                return zD2;
            }
        }
        return true;
    }

    @Override // defpackage.q20
    public Object q(Object obj) {
        return obj.toString();
    }

    public Typeface r(Context context, List list, int i2) {
        ContentResolver contentResolver = context.getContentResolver();
        try {
            FontFamily fontFamilyV = v((kn0[]) list.get(0), contentResolver);
            if (fontFamilyV == null) {
                return null;
            }
            Typeface.CustomFallbackBuilder customFallbackBuilder = new Typeface.CustomFallbackBuilder(fontFamilyV);
            for (int i3 = 1; i3 < list.size(); i3++) {
                FontFamily fontFamilyV2 = v((kn0[]) list.get(i3), contentResolver);
                if (fontFamilyV2 != null) {
                    customFallbackBuilder.addCustomFallback(fontFamilyV2);
                }
            }
            return customFallbackBuilder.setStyle(u(fontFamilyV, i2).getStyle()).build();
        } catch (Exception e2) {
            Log.w("TypefaceCompatApi29Impl", "Font load failed", e2);
            return null;
        }
    }

    public Worker s(Context context, String str, WorkerParameters workerParameters) {
        context.getClass();
        str.getClass();
        try {
            Class<? extends U> clsAsSubclass = Class.forName(str).asSubclass(Worker.class);
            clsAsSubclass.getClass();
            try {
                Object objNewInstance = clsAsSubclass.getDeclaredConstructor(Context.class, WorkerParameters.class).newInstance(context, workerParameters);
                objNewInstance.getClass();
                Worker worker = (Worker) objNewInstance;
                if (!worker.d) {
                    return worker;
                }
                cw1.h("WorkerFactory (", getClass().getName(), ") returned an instance of a ListenableWorker (", str, ") which has already been invoked. createWorker() must always return a new instance of a ListenableWorker.");
                return null;
            } catch (Throwable th) {
                lk1.d().c(h93.a, "Could not instantiate ".concat(str), th);
                throw th;
            }
        } catch (Throwable th2) {
            lk1.d().c(h93.a, "Invalid class: ".concat(str), th2);
            throw th2;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
    @Override // defpackage.oq3
    public void f() {
    }

    @Override // defpackage.qq3
    /* JADX INFO: renamed from: b */
    public void mo43b() {
    }

    @Override // tmsdkobf.v5
    public void a(Runnable runnable, String str) {
        pb.f().b(runnable, str);
    }

    /* JADX DEBUG: Possible override for method qq3.b()V */
    @Override // defpackage.wk3
    public long b() {
        return 3540000L;
    }

    @Override // defpackage.qq3, defpackage.wk3
    public int a() {
        return 2;
    }

    @Override // defpackage.bo1
    public void b(hn1 hn1Var, boolean z) {
    }

    @Override // defpackage.qq3
    public void a(he3 he3Var) {
    }

    @Override // defpackage.oq3
    public void d() {
    }

    @Override // defpackage.qq3
    public void a(String str) {
    }

    @Override // defpackage.wk3
    public long a() {
        return 3540000L;
    }

    public ro(ek1 ek1Var) {
        this.b = 20;
        String str = ek1.d;
        new ConcurrentHashMap(3, 1.0f, 2);
    }

    /* JADX DEBUG: Marked for inline */
    /* JADX DEBUG: Method not inlined, still used in: [a03.<clinit>():void, androidx.appcompat.widget.ActionMenuView.getMenu():android.view.Menu, com.miui.guardprovider.GuardApplication.<init>():void, com.xiaomi.push.service.XMPushService.onCreate():void, fa1.o(p91, p91):boolean, kq3.d(int):qq3, ma0.<clinit>():void, ro.<clinit>():void, rz1.<clinit>():void, tmsdkobf.i3.b():tmsdkobf.v5, wl2.<init>():void, xu2.j(wp1, java.lang.String, java.util.concurrent.Executor, np0):ro] */
    public /* synthetic */ ro(int i2) {
        this.b = i2;
    }

    @Override // defpackage.oq3
    public void e() {
    }

    @Override // defpackage.qq3
    public void c() {
    }
}
