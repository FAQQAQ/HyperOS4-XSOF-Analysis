package a.b.a.e.i0.j;

import a.b.a.e.i0.i.f;
import a.b.a.e.l0.p;
import android.content.Context;
import android.text.TextUtils;
import b.b0;
import b.c0;
import b.e0;
import b.g0.e;
import b.l;
import b.q;
import b.r;
import b.u;
import b.w;
import b.x;
import b.y;
import b.z;
import com.miui.analytics.BuildConfig;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import okhttp3.Protocol;

/* JADX INFO: loaded from: classes.dex */
public class b {
    public static b h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public x f521a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public x f522b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public f f523c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public x f524d;
    public x e;
    public x f;
    public x g;

    public class a implements q {
        public a(b bVar) {
        }

        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
        @Override // b.q
        public List<InetAddress> a(String str) throws UnknownHostException {
            List<InetAddress> listA;
            a.b.a.e.i0.i.d dVarA = a.b.a.e.i0.i.d.a();
            Objects.requireNonNull(dVarA);
            try {
                if (dVarA.c(str)) {
                    try {
                        listA = b.l().k().a(str);
                    } catch (Exception e) {
                        p.c("DnsInfoControl", "lookup exception : " + e.getMessage());
                        listA = null;
                    }
                } else {
                    listA = null;
                }
                HashMap<String, a.b.a.e.i0.i.b> mapB = a.b.a.e.i0.i.c.c().b(str);
                dVarA.b(str, mapB, listA);
                return d.a(mapB);
            } catch (Exception e2) {
                a.a.a.a.a.f(e2, a.a.a.a.a.c("getHostNameAllDns exception: "), "DnsInfoControl");
                return null;
            }
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public b() {
        x.b bVar = new x.b();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        bVar.r = new l(2, 60L, timeUnit);
        bVar.a(30L, timeUnit);
        bVar.b(30L, timeUnit);
        bVar.z = e.b("timeout", 30L, timeUnit);
        r.b bVar2 = a.b.a.e.i0.j.e.a.e;
        Objects.requireNonNull(bVar2, "eventListenerFactory == null");
        bVar.g = bVar2;
        this.f521a = new x(bVar);
    }

    public static b l() {
        if (h == null) {
            synchronized (b.class) {
                if (h == null) {
                    h = new b();
                }
            }
        }
        return h;
    }

    public static String p(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        for (String str : arrayList) {
            if (!TextUtils.isEmpty(str)) {
                sb.append(str);
                sb.append(map.get(str));
            }
        }
        sb.append("miui_sdkconfig_jafej!@#)(*e@!#");
        return a.b.a.e.g0.d.d(sb.toString());
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final a.b.a.e.i0.j.a a(String str, String str2, String str3, boolean z) {
        z zVarB;
        a.b.a.e.i0.j.a aVar = new a.b.a.e.i0.j.a();
        c0 c0VarA = null;
        try {
            try {
                c cVar = new c();
                cVar.f526b = z ? 1 : 0;
                cVar.f525a = false;
                cVar.f528d = "callRequest";
                cVar.f = str2;
                cVar.e = i(str2);
                if ("GET".equals(str)) {
                    z.a aVar2 = new z.a();
                    aVar2.c();
                    aVar2.g(str2);
                    aVar2.f(cVar);
                    zVarB = aVar2.b();
                } else if (!"POST".equals(str) || str3 == null) {
                    zVarB = null;
                } else {
                    b0 b0VarC = b0.c(w.b("application/x-www-form-urlencoded"), str3.getBytes("UTF-8"));
                    z.a aVar3 = new z.a();
                    aVar3.e("POST", b0VarC);
                    aVar3.a("Content-Type", "application/x-www-form-urlencoded");
                    aVar3.g(str2);
                    aVar3.f(cVar);
                    zVarB = aVar3.b();
                }
                c0VarA = ((y) (z ? j().a(zVarB) : this.f521a.a(zVarB))).a();
                cVar.f527c = c0VarA.f903b.compareTo(Protocol.HTTP_2) == 0 ? 1 : 0;
                p.c("OkHttpUtil", String.format("OkHttpUtil doPost 上传成功 url: %s, code: %s", str2, Integer.valueOf(c0VarA.f904c)));
                aVar.f519c = c0VarA.f904c;
                aVar.f520d = z;
                e0 e0Var = c0VarA.g;
                if (e0Var != null) {
                    aVar.f517a = e0Var.I();
                    String strI = i(str2);
                    int i = aVar.f519c;
                    String str4 = aVar.f517a;
                    a.b.a.g.f.a.f(false, strI, 1, i, str4, str4, "OkHttpUtil");
                }
            } catch (IOException e) {
                aVar.f518b = e;
                p.c("OkHttpUtil", "callRequestSync IOException:" + e.getMessage());
                a.b.a.g.f.a.d(false, i(str2), 1, e, "OkHttpUtil");
                if (c0VarA != null) {
                }
                return aVar;
            }
            a.b.a.a.a.c.a.h(c0VarA);
            return aVar;
        } catch (Throwable th) {
            if (c0VarA != null) {
                a.b.a.a.a.c.a.h(c0VarA);
            }
            throw th;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public a.b.a.e.i0.j.a b(boolean z, String str, boolean z2) {
        Context contextB;
        String str2;
        a.b.a.e.i0.j.a aVar = new a.b.a.e.i0.j.a();
        c0 c0VarA = null;
        try {
            try {
                c cVar = new c();
                cVar.f526b = z2 ? 1 : 0;
                cVar.f525a = z;
                cVar.f528d = "doCheckUpdateRequest";
                cVar.f = str;
                cVar.e = i("U5");
                z.a aVar2 = new z.a();
                aVar2.c();
                aVar2.g(str);
                aVar2.f(cVar);
                z zVarB = aVar2.b();
                c0VarA = (z2 ? (y) j().a(zVarB) : (y) this.f521a.a(zVarB)).a();
                cVar.f527c = c0VarA.f903b.compareTo(Protocol.HTTP_2) == 0 ? 1 : 0;
                aVar.f519c = c0VarA.f904c;
                aVar.f520d = z2;
                e0 e0Var = c0VarA.g;
                if (e0Var != null) {
                    aVar.f517a = e0Var.I();
                    p.c("OkHttpUtil", "OkHttpUtil doCheckUpdateRequest response：" + aVar.f517a);
                    int i = aVar.f519c;
                    String str3 = aVar.f517a;
                    a.b.a.g.f.a.f(false, "U5", 1, i, str3, str3, "OkHttpUtil");
                }
            } catch (IOException e) {
                if (e instanceof UnknownHostException) {
                    if (new Random().nextInt() % 10 == 0) {
                        contextB = a.b.a.e.e.b();
                        str2 = "mUpdater.run: UnknownHostException";
                    } else {
                        aVar.f518b = e;
                        a.b.a.g.f.a.d(true, i("U5"), 1, e, "OkHttpUtil");
                        if (c0VarA != null) {
                        }
                    }
                    return aVar;
                }
                contextB = a.b.a.e.e.b();
                str2 = "mUpdater.run: IOException=" + e.toString();
                a.b.a.a.a.c.a.P0(contextB, str2, "");
                aVar.f518b = e;
                a.b.a.g.f.a.d(true, i("U5"), 1, e, "OkHttpUtil");
                if (c0VarA != null) {
                }
                return aVar;
            }
            a.b.a.a.a.c.a.h(c0VarA);
            return aVar;
        } catch (Throwable th) {
            if (c0VarA != null) {
                a.b.a.a.a.c.a.h(c0VarA);
            }
            throw th;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:16:0x005e */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:22:0x0066 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:37:0x0001 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2, types: [b.c0] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r5v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v4, types: [b.c0] */
    public byte[] c(boolean z, String str) throws Throwable {
        c0 c0VarA;
        e0 e0Var;
        ?? r0 = 0;
        bArrK0 = null;
        bArrK0 = null;
        r0 = 0;
        bArrK0 = null;
        byte[] bArrK0 = null;
        try {
            try {
                c cVar = new c();
                cVar.f525a = z;
                cVar.f528d = "doDownloadRequest";
                cVar.f = str;
                cVar.e = i("U6");
                z.a aVar = new z.a();
                aVar.c();
                aVar.g(str);
                aVar.f(cVar);
                try {
                    c0VarA = ((y) this.f521a.a(aVar.b())).a();
                    try {
                        cVar.f527c = c0VarA.f903b.compareTo(Protocol.HTTP_2) == 0 ? 1 : 0;
                        int i = c0VarA.f904c;
                        z = c0VarA;
                        if (i == 200 && (e0Var = c0VarA.g) != null) {
                            z = c0VarA;
                            bArrK0 = a.b.a.a.a.c.a.k0(e0Var.H().w());
                            z = c0VarA;
                        }
                    } catch (IOException e) {
                        e = e;
                        a.b.a.a.a.c.a.P0(a.b.a.e.e.b(), "mDownloader.run: IOException=" + e.toString(), "");
                        z = c0VarA;
                        if (c0VarA != null) {
                        }
                        return bArrK0;
                    }
                } catch (IOException e2) {
                    e = e2;
                    c0VarA = null;
                    a.b.a.a.a.c.a.P0(a.b.a.e.e.b(), "mDownloader.run: IOException=" + e.toString(), "");
                    z = c0VarA;
                    if (c0VarA != null) {
                        z = c0VarA;
                        a.b.a.a.a.c.a.h(z);
                    }
                    return bArrK0;
                } catch (Throwable th) {
                    th = th;
                    if (r0 != 0) {
                        a.b.a.a.a.c.a.h(r0);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                r0 = z;
            }
        } catch (IOException e3) {
            e = e3;
        } catch (Throwable th3) {
            th = th3;
        }
        z = c0VarA;
        a.b.a.a.a.c.a.h(z);
        return bArrK0;
    }

    public a.b.a.e.i0.j.a d(String str, Map<String, String> map, boolean z, boolean z2) throws IOException {
        String strH = map == null ? null : h(map, z);
        if (strH != null) {
            str = a.a.a.a.a.b(str, "?", strH);
        }
        return a("GET", str, strH, z2);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public boolean e(a.b.a.e.c0.e.a aVar) throws Throwable {
        int i;
        c0 c0VarA;
        String strC = aVar.f240b;
        if (TextUtils.isEmpty(strC)) {
            p.c("OkHttpUtil", "doGetAdMonitor url is null");
            return true;
        }
        p.c("OkHttpUtil", "doGetAdMonitor url = " + strC);
        String strI = "";
        c0 c0Var = null;
        try {
            try {
                c cVar = new c();
                cVar.f525a = false;
                cVar.f526b = 0;
                cVar.f528d = "doGetAdMonitor";
                cVar.f = strC;
                cVar.g = a.b.a.e.c0.a.a(aVar.e);
                int i2 = 0;
                i = 0;
                c0VarA = null;
                while (i2 < 3) {
                    try {
                        if (i / 100 == 3) {
                            i2++;
                            strC = c0VarA.f.c("Location");
                            if (strC == null) {
                                strC = null;
                            }
                            p.c("OkHttpUtil", "redirect url is:" + strC);
                        }
                        z.a aVar2 = new z.a();
                        aVar2.c();
                        aVar2.g(strC);
                        aVar2.f(cVar);
                        if (a.b.a.a.a.c.a.w0(aVar.k)) {
                            aVar2.a("User-Agent", aVar.k);
                        }
                        c0VarA = ((y) m().a(aVar2.b())).a();
                        i = c0VarA.f904c;
                        p.c("OkHttpUtil", "AdMonitor get 请求 ResponseCode：" + i);
                        if (i / 100 != 5 && i / 100 != 4 && i / 100 != 3) {
                            if (c0VarA.g != null) {
                                if (p.f602a) {
                                    p.c("OkHttpUtil", "AdMonitor url: " + strC + " ,Response:" + c0VarA.g.I());
                                }
                                c0VarA.g.close();
                            }
                            a.b.a.a.a.c.a.h(c0VarA);
                            return true;
                        }
                        try {
                            e0 e0Var = c0VarA.g;
                            if (e0Var != null) {
                                strI = e0Var.I();
                                c0VarA.g.close();
                            }
                        } catch (Throwable unused) {
                        }
                        a.b.a.e.c0.b.d().h(aVar, i, strI, null);
                        if (i / 100 != 3) {
                            a.b.a.a.a.c.a.h(c0VarA);
                            return false;
                        }
                    } catch (ProtocolException e) {
                        e = e;
                        c0Var = c0VarA;
                        a.b.a.e.c0.b.d().h(aVar, i, strI, e);
                        if (!TextUtils.isEmpty(e.getMessage()) && e.getMessage().contains("200 OK")) {
                            p.c("OkHttpUtil", "response code is 200, bug status line is invalid.");
                            if (c0Var != null) {
                                a.b.a.a.a.c.a.h(c0Var);
                            }
                            return true;
                        }
                        if (c0Var != null) {
                            a.b.a.a.a.c.a.h(c0Var);
                        }
                    } catch (Exception e2) {
                        e = e2;
                        c0Var = c0VarA;
                        p.g("OkHttpUtil", "OkHttpUtil doGetAdMonitor 上传异常:" + e.getMessage());
                        a.b.a.e.c0.b.d().h(aVar, i, strI, e);
                        if (c0Var != null) {
                            a.b.a.a.a.c.a.h(c0Var);
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (c0VarA != null) {
                            a.b.a.a.a.c.a.h(c0VarA);
                        }
                        throw th;
                    }
                }
                p.c("OkHttpUtil", "redirectCount >= 3, return true");
                if (c0VarA != null) {
                    a.b.a.a.a.c.a.h(c0VarA);
                }
                return true;
            } catch (Throwable th2) {
                th = th2;
                c0VarA = null;
            }
        } catch (ProtocolException e3) {
            e = e3;
            i = 0;
        } catch (Exception e4) {
            e = e4;
            i = 0;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public a.b.a.f.g.c f(String str, byte[] bArr, String str2) throws Throwable {
        c0 c0VarA;
        a.b.a.f.g.c cVar;
        c0 c0Var = null;
        a.b.a.f.g.c cVar2 = null;
        c0Var = null;
        try {
            try {
                b0 b0VarC = b0.c(w.b("application/json"), bArr);
                c cVar3 = new c();
                cVar3.f525a = true;
                cVar3.f528d = "doPublish";
                cVar3.f = str;
                cVar3.e = i("U1");
                z.a aVar = new z.a();
                aVar.e("POST", b0VarC);
                aVar.a("accept", "*/*");
                aVar.a("connection", "Keep-Alive");
                aVar.a("Content-Type", "application/json");
                aVar.a("Authorization", str2);
                aVar.g(str);
                aVar.f(cVar3);
                c0VarA = ((y) n().a(aVar.b())).a();
                try {
                    try {
                        cVar3.f527c = c0VarA.f903b.compareTo(Protocol.HTTP_2) == 0 ? 1 : 0;
                        int i = c0VarA.f904c;
                        a.b.a.f.g.c cVar4 = new a.b.a.f.g.c();
                        try {
                            cVar4.f723a = i;
                            e0 e0Var = c0VarA.g;
                            if (e0Var != null) {
                                cVar4.a(e0Var.I());
                            }
                            if (cVar4.f723a == 200) {
                                p.c("OkHttpUtil", "POST 成功 publishResponse:" + cVar4.toString());
                            } else {
                                p.g("OkHttpUtil", "POST 失败 publishResponse:" + cVar4.toString());
                                int i2 = cVar4.f723a;
                                String str3 = cVar4.f725c;
                                a.b.a.g.f.a.f(true, "U1", 1, i2, str3, str3, "OkHttpUtil");
                            }
                            if (a.b.a.f.a.c()) {
                                a.b.a.f.a.f670c = 0;
                            }
                            a.b.a.a.a.c.a.h(c0VarA);
                            return cVar4;
                        } catch (IOException e) {
                            e = e;
                            cVar2 = cVar4;
                            a.b.a.f.g.c cVar5 = cVar2;
                            c0Var = c0VarA;
                            cVar = cVar5;
                            if (e instanceof SocketTimeoutException) {
                                a.b.a.f.g.b.b().c(false);
                                a.b.a.f.a.a();
                            }
                            a.b.a.g.f.a.d(true, i("U1"), 1, e, "OkHttpUtil");
                            if (c0Var != null) {
                                a.b.a.a.a.c.a.h(c0Var);
                            }
                            return cVar;
                        }
                    } catch (IOException e2) {
                        e = e2;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (c0VarA != null) {
                        a.b.a.a.a.c.a.h(c0VarA);
                    }
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
                cVar = null;
            }
        } catch (Throwable th2) {
            th = th2;
            c0VarA = c0Var;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX WARN: Code duplicated, block: B:37:0x0186 A[PHI: r2 r3
      0x0186: PHI (r2v7 a.b.a.e.i0.j.a) = (r2v5 a.b.a.e.i0.j.a), (r2v10 a.b.a.e.i0.j.a), (r2v10 a.b.a.e.i0.j.a) binds: [B:36:0x0184, B:26:0x0141, B:27:0x0143] A[DONT_GENERATE, DONT_INLINE]
      0x0186: PHI (r3v10 b.c0) = (r3v9 b.c0), (r3v11 b.c0), (r3v11 b.c0) binds: [B:36:0x0184, B:26:0x0141, B:27:0x0143] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:40:0x018c  */
    public a.b.a.e.i0.j.a g(String str, byte[] bArr, boolean z, boolean z2) throws Throwable {
        z.a aVar;
        z.a aVar2;
        String str2;
        c0 c0VarA;
        a.b.a.e.i0.j.a aVar3;
        a.b.a.e.i0.j.a aVar4 = new a.b.a.e.i0.j.a();
        int i = a.b.a.e.g0.e.e;
        String str3 = a.b.a.e.g0.e.b.f436a.a(false)[1];
        b0 b0VarC = b0.c(w.b("application/octet-stream"), bArr);
        c cVar = new c();
        cVar.f526b = z ? 1 : 0;
        cVar.f525a = false;
        cVar.f528d = "doPost";
        cVar.f = str;
        cVar.e = i(str);
        String property = System.getProperty("http.agent");
        if (!a.b.a.a.a.c.a.w0(property)) {
            property = "okhttp/3.14.9";
        }
        if (z2) {
            aVar = new z.a();
            aVar.e("POST", b0VarC);
            aVar.a("Content-Type", "application/octet-stream");
            aVar.a("User-Agent", property);
            aVar.a("OT_SID", str3);
            aVar.a("OT_ts", Long.toString(System.currentTimeMillis()));
            aVar.a("OT_net", a.b.a.a.a.c.a.W(a.b.a.e.e.b()));
            aVar.a("OT_sender", BuildConfig.APPLICATION_ID);
            aVar.a("OT_bi_v", a.b.a.e.e.f263c);
            aVar.a("OT_protocol", "4.0");
            aVar.a("OT_ri_f", "1");
            aVar.g(str);
            aVar2 = aVar;
            str2 = str;
        } else {
            aVar = new z.a();
            aVar.e("POST", b0VarC);
            aVar.a("Content-Type", "application/octet-stream");
            aVar.a("User-Agent", property);
            aVar.a("OT_SID", str3);
            aVar.a("OT_ts", Long.toString(System.currentTimeMillis()));
            aVar.a("OT_net", a.b.a.a.a.c.a.W(a.b.a.e.e.b()));
            aVar.a("OT_sender", BuildConfig.APPLICATION_ID);
            aVar.a("OT_bi_v", a.b.a.e.e.f263c);
            aVar.a("OT_protocol", "4.0");
            aVar2 = aVar;
            str2 = str;
            aVar2.g(str2);
        }
        aVar2.f(cVar);
        z zVarB = aVar2.b();
        c0 c0Var = null;
        if (z) {
            try {
                c0VarA = ((y) j().a(zVarB)).a();
            } catch (IOException e) {
                e = e;
                aVar3 = aVar4;
                aVar3.f518b = e;
                p.c("OkHttpUtil", "doUpload IOException:" + e.getMessage());
                a.b.a.g.f.a.d(false, i(str), 1, e, "OkHttpUtil");
                if (c0Var != null) {
                    a.b.a.a.a.c.a.h(c0Var);
                }
                return aVar3;
            } catch (Throwable th) {
                th = th;
                if (c0Var != null) {
                    a.b.a.a.a.c.a.h(c0Var);
                }
                throw th;
            }
        } else {
            try {
                try {
                    c0VarA = ((y) this.f521a.a(zVarB)).a();
                } catch (Throwable th2) {
                    th = th2;
                    if (c0Var != null) {
                        a.b.a.a.a.c.a.h(c0Var);
                    }
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
                aVar3 = aVar4;
                aVar3.f518b = e;
                p.c("OkHttpUtil", "doUpload IOException:" + e.getMessage());
                a.b.a.g.f.a.d(false, i(str), 1, e, "OkHttpUtil");
                if (c0Var != null) {
                    a.b.a.a.a.c.a.h(c0Var);
                }
                return aVar3;
            }
        }
        c0Var = c0VarA;
        cVar.f527c = c0Var.f903b.compareTo(Protocol.HTTP_2) == 0 ? 1 : 0;
        p.c("OkHttpUtil", String.format("OkHttpUtil doPost 上传成功 url: %s, code: %s", str2, Integer.valueOf(c0Var.f904c)));
        aVar3 = aVar4;
        try {
            aVar3.f519c = c0Var.f904c;
            aVar3.f520d = z;
            e0 e0Var = c0Var.g;
            if (e0Var != null) {
                aVar3.f517a = e0Var.I();
                String strI = i(str);
                int i2 = aVar3.f519c;
                String str4 = aVar3.f517a;
                a.b.a.g.f.a.f(false, strI, 1, i2, str4, str4, "OkHttpUtil");
            }
        } catch (IOException e3) {
            e = e3;
            aVar3.f518b = e;
            p.c("OkHttpUtil", "doUpload IOException:" + e.getMessage());
            a.b.a.g.f.a.d(false, i(str), 1, e, "OkHttpUtil");
            if (c0Var != null) {
            }
            return aVar3;
        }
        a.b.a.a.a.c.a.h(c0Var);
        return aVar3;
    }

    public String h(Map<String, String> map, boolean z) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            try {
                if (!TextUtils.isEmpty(entry.getKey())) {
                    if (sb.length() > 0) {
                        sb.append("&");
                    }
                    sb.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                    sb.append("=");
                    sb.append(URLEncoder.encode(entry.getValue() == null ? "null" : entry.getValue(), "UTF-8"));
                }
            } catch (UnsupportedEncodingException unused) {
                p.g("OkHttpUtil", "format params failed");
            }
        }
        if (z) {
            String strP = p(map);
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(URLEncoder.encode("sign", "UTF-8"));
            sb.append("=");
            sb.append(URLEncoder.encode(strP, "UTF-8"));
        }
        return sb.toString();
    }

    public String i(String str) {
        if (!str.endsWith("/track/v4")) {
            if (!str.endsWith("/api/v4/detail/config")) {
                if (str.endsWith("/track/key_get")) {
                    return "U3";
                }
                if (str.endsWith("/api/v4/detail/config_common")) {
                    return "U4";
                }
                if ("U5".equals(str)) {
                    return "U5";
                }
                if ("U6".equals(str)) {
                    return "U6";
                }
                if (str.endsWith("/api/v4/detail/d")) {
                    return "U7";
                }
                if (str.endsWith("/track/getNonce")) {
                    return "U8";
                }
                if (str.endsWith("/track/mt")) {
                    return "U9";
                }
                if (str.endsWith("/api/ex/lastUseful")) {
                    return "U10";
                }
                if (!"U1".equals(str)) {
                    if (!str.endsWith("/api/v1/token")) {
                        return "";
                    }
                }
            }
            return "U2";
        }
        return "U1";
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public x j() {
        if (this.f522b == null) {
            x xVar = this.f521a;
            Objects.requireNonNull(xVar);
            x.b bVar = new x.b(xVar);
            bVar.s = new a(this);
            this.f522b = new x(bVar);
        }
        return this.f522b;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public f k() {
        if (this.f523c == null) {
            try {
                x xVar = l().f521a;
                Objects.requireNonNull(xVar);
                x xVar2 = new x(new x.b(xVar));
                f.a aVar = new f.a();
                aVar.f512a = xVar2;
                u.a aVar2 = new u.a();
                aVar2.c(null, "https://dns.google/dns-query");
                aVar.f513b = aVar2.a();
                aVar.f515d = Arrays.asList(InetAddress.getByName("8.8.4.4"), InetAddress.getByName("8.8.8.8"));
                this.f523c = new f(aVar);
            } catch (Throwable th) {
                StringBuilder sbC = a.a.a.a.a.c("GoogleDns init Throwable:");
                sbC.append(th.getMessage());
                p.g("OkHttpUtil", sbC.toString());
            }
        }
        return this.f523c;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final x m() {
        if (this.f524d == null) {
            x xVar = this.f521a;
            Objects.requireNonNull(xVar);
            x.b bVar = new x.b(xVar);
            bVar.u = false;
            this.f524d = new x(bVar);
        }
        return this.f524d;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final x n() {
        int i = a.b.a.f.g.b.b().f722a;
        if (i == 100) {
            if (this.e == null) {
                x xVar = this.f521a;
                Objects.requireNonNull(xVar);
                x.b bVar = new x.b(xVar);
                long j = i;
                TimeUnit timeUnit = TimeUnit.SECONDS;
                bVar.a(j, timeUnit);
                bVar.b(j, timeUnit);
                this.e = new x(bVar);
            }
            return this.e;
        }
        if (i == 200) {
            if (this.f == null) {
                x xVar2 = this.f521a;
                Objects.requireNonNull(xVar2);
                x.b bVar2 = new x.b(xVar2);
                long j2 = i;
                TimeUnit timeUnit2 = TimeUnit.SECONDS;
                bVar2.a(j2, timeUnit2);
                bVar2.b(j2, timeUnit2);
                this.f = new x(bVar2);
            }
            return this.f;
        }
        if (this.g == null) {
            x xVar3 = this.f521a;
            Objects.requireNonNull(xVar3);
            x.b bVar3 = new x.b(xVar3);
            long j3 = i;
            TimeUnit timeUnit3 = TimeUnit.SECONDS;
            bVar3.a(j3, timeUnit3);
            bVar3.b(j3, timeUnit3);
            this.g = new x(bVar3);
        }
        return this.g;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public a.b.a.e.i0.j.a o(String str, String str2, Map<String, String> map, boolean z) {
        c0 c0VarA = null;
        String strH = map == null ? null : h(map, true);
        a.b.a.e.i0.j.a aVar = new a.b.a.e.i0.j.a();
        try {
            try {
                c cVar = new c();
                cVar.f525a = true;
                cVar.f526b = z ? 1 : 0;
                cVar.f528d = "doGetToken";
                cVar.f = str2;
                cVar.e = i(str2);
                b0 b0VarC = b0.c(w.b("application/x-www-form-urlencoded"), strH.getBytes("UTF-8"));
                z.a aVar2 = new z.a();
                aVar2.e("POST", b0VarC);
                aVar2.a("Content-Type", "application/x-www-form-urlencoded");
                aVar2.a("Authorization", "Bearer " + str);
                aVar2.g(str2);
                aVar2.f(cVar);
                z zVarB = aVar2.b();
                c0VarA = ((y) (z ? j().a(zVarB) : this.f521a.a(zVarB))).a();
                cVar.f527c = c0VarA.f903b.compareTo(Protocol.HTTP_2) == 0 ? 1 : 0;
                p.c("OkHttpUtil", "postGetToken isDns:" + z);
                p.c("OkHttpUtil", "postGetToken responseData statusCode:" + c0VarA.f904c);
                aVar.f519c = c0VarA.f904c;
                aVar.f520d = z;
                e0 e0Var = c0VarA.g;
                if (e0Var != null) {
                    aVar.f517a = e0Var.I();
                    String strI = i(str2);
                    int i = aVar.f519c;
                    String str3 = aVar.f517a;
                    a.b.a.g.f.a.f(true, strI, 1, i, str3, str3, "OkHttpUtil");
                }
            } catch (Exception e) {
                aVar.f518b = e;
                a.b.a.g.f.a.d(true, i(str2), 1, e, "OkHttpUtil");
                if (c0VarA != null) {
                }
                return aVar;
            }
            a.b.a.a.a.c.a.h(c0VarA);
            return aVar;
        } catch (Throwable th) {
            if (c0VarA != null) {
                a.b.a.a.a.c.a.h(c0VarA);
            }
            throw th;
        }
    }
}
