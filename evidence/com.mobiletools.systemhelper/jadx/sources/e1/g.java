package e1;

import android.content.Context;
import f1.p;
import g1.h;
import g1.l;
import java.net.URLEncoder;
import java.util.Base64;

/* JADX INFO: loaded from: classes.dex */
public class g extends Thread {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f2487b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private b f2488c;

    public g(Context context, b bVar) {
        this.f2487b = context;
        this.f2488c = bVar;
    }

    /* JADX WARN: Code duplicated, block: B:15:0x00d1 A[PHI: r13
      0x00d1: PHI (r13v13 boolean) = (r13v12 boolean), (r13v12 boolean), (r13v17 boolean), (r13v17 boolean) binds: [B:21:0x012a, B:23:0x0130, B:11:0x00c6, B:13:0x00cc] A[DONT_GENERATE, DONT_INLINE]] */
    private synchronized boolean a(b bVar) {
        boolean zF;
        boolean z2 = false;
        if (!g1.d.f2649e) {
            h.a("RegisterThread", "isUserPermissionOpen is false!");
            return false;
        }
        String strB = bVar.b();
        h.a("RegisterThread", "register postString：" + strB);
        String strEncode = "";
        String strEncode2 = "";
        byte[] bArrB = new byte[0];
        g1.f fVar = new g1.f();
        byte[] bArrA = null;
        try {
            byte[] bArrC = g1.c.c(strB);
            bArrB = g1.c.b(this.f2487b, bArrC);
            strEncode = URLEncoder.encode(bVar.f2445b, "utf-8");
            strEncode2 = URLEncoder.encode(bVar.f2443a, "utf-8");
            String str = l.k(this.f2487b) + "?ver=6.0&model=" + strEncode + "&manuf=" + strEncode2 + "&sign=" + h1.a.e(bArrB);
            bArrA = g1.c.a(strB, bArrC);
            h.a("RegisterThread", "RSA加密密文： " + str);
            h.a("RegisterThread", "AES加密密文： " + Base64.getEncoder().encodeToString(bArrA));
            zF = fVar.g(str, bArrA, bArrC);
            if (zF || fVar.c() != 416) {
                z2 = zF;
            } else {
                z2 = true;
            }
        } catch (Exception e2) {
            h.b("RegisterThread", "HTTPS request exception--->" + e2.toString());
            if (fVar.e()) {
                h.a("RegisterThread", "HTTPS fail by cert invalid, trigger HTTP fallback!");
                try {
                    zF = fVar.f(g1.d.f2657m + "?ver=6.0&model=" + strEncode + "&manuf=" + strEncode2 + "&sign=" + h1.a.e(bArrB), bArrA);
                    if (zF || fVar.c() != 416) {
                        z2 = zF;
                    }
                } catch (Exception e3) {
                    h.b("RegisterThread", "HTTP fallback request exception--->" + e3.toString());
                }
            }
        }
        return z2;
    }

    private void b(boolean z2) {
        if (z2) {
            h.a("RegisterThread", "register success!");
        } else {
            h.b("RegisterThread", "register false!");
        }
    }

    private synchronized boolean c(b bVar) {
        if (!g1.d.f2649e) {
            h.a("RegisterThread", "isUserPermissionOpen is false!");
            return false;
        }
        if (bVar == null) {
            h.b("RegisterThread", "deviceInfo is null! ");
            return false;
        }
        if (!l.s(bVar).booleanValue()) {
            h.a("RegisterThread", "mnc is not in china!");
            return false;
        }
        if (l.t(this.f2487b).booleanValue()) {
            h.a("RegisterThread", "network connected!");
            if ((l.w(this.f2487b).booleanValue() && l.s(bVar).booleanValue()) || (!p.h(this.f2487b).booleanValue() && l.u(bVar).booleanValue())) {
                h.a("RegisterThread", "is unicomnetwork || is wifi connected!");
                if (a(bVar)) {
                    new e(this.f2487b).start();
                    return true;
                }
            }
        }
        g1.e eVarA = g1.e.a(this.f2487b, g1.e.f2670c, g1.e.f2669b.intValue());
        eVarA.b(bVar.b());
        eVarA.close();
        h.a("RegisterThread", "save to database!");
        h.a("RegisterThread", "Not post!");
        return false;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        if (g1.d.f2649e) {
            b(c(this.f2488c));
            return;
        }
        h.a("RegisterThread", "GlobalSetting.isUserPermissionOpen is " + g1.d.f2649e);
    }
}
