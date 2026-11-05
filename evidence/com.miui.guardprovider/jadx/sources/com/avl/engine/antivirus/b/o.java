package com.avl.engine.antivirus.b;

import android.text.TextUtils;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import tmsdk.common.roach.nest.PowerNest;

/* JADX INFO: loaded from: classes.dex */
public final class o {
    private final d d;
    private final String e;
    private volatile boolean g;
    private volatile boolean h;
    private volatile boolean i;
    private volatile int j;
    private com.avl.engine.h.a k;
    private f l;
    private final Object a = new Object();
    private final Object b = new Object();
    private final Object c = new Object();
    private final AtomicBoolean f = new AtomicBoolean(false);

    public o(d dVar) {
        this.d = dVar;
        this.e = dVar.e();
    }

    private h a(b bVar, g gVar, g gVar2) throws com.avl.engine.antivirus.b.a.h {
        if (gVar == null || gVar2 == null) {
            com.avl.engine.n.b.a("Updater", "invalid params, engineConf = %s, libConf = %s", gVar, gVar2);
            return null;
        }
        if (gVar.isEmpty()) {
            com.avl.engine.antivirus.b.a.h hVar = new com.avl.engine.antivirus.b.a.h("engine conf is empty.");
            hVar.b();
            throw hVar;
        }
        if (gVar2.isEmpty()) {
            com.avl.engine.antivirus.b.a.h hVar2 = new com.avl.engine.antivirus.b.a.h("siglib conf is empty.");
            hVar2.c();
            throw hVar2;
        }
        h hVar3 = new h();
        boolean z = false;
        hVar3.a(false);
        hVar3.b(false);
        try {
            if (gVar.a()) {
                hVar3.a(false);
                hVar3.g(this.d.k());
            } else {
                hVar3.b(gVar.e());
                hVar3.a(gVar.c());
                hVar3.a(gVar.d());
                hVar3.a(true);
                hVar3.d(this.e + File.separator + System.currentTimeMillis() + com.avl.engine.n.p.a(hVar3.a(), "libavlm.so"));
                hVar3.c(gVar.b());
            }
            b(bVar, 10);
            try {
                if (gVar2.a()) {
                    hVar3.b(false);
                    hVar3.c(this.d.l());
                    return hVar3;
                }
                hVar3.f(gVar2.e());
                hVar3.e(gVar2.c());
                hVar3.b(gVar2.d());
                hVar3.b(true);
                hVar3.h(this.e + File.separator + System.currentTimeMillis() + com.avl.engine.n.p.a(hVar3.g(), "siglib.zip"));
                hVar3.g(gVar2.b());
                String property = gVar2.getProperty("file_type");
                if (TextUtils.isEmpty(property)) {
                    throw new com.avl.engine.antivirus.b.a.b("file_type can't be found");
                }
                hVar3.i(property.trim());
                return hVar3;
            } catch (com.avl.engine.antivirus.b.a.b e) {
                e = e;
                com.avl.engine.n.b.b(e);
                com.avl.engine.antivirus.b.a.h hVar4 = new com.avl.engine.antivirus.b.a.h(e);
                if (z) {
                    hVar4.b();
                } else {
                    hVar4.c();
                }
                com.avl.engine.n.b.b("parse conf fail", hVar4.getMessage());
                throw hVar4;
            }
        } catch (com.avl.engine.antivirus.b.a.b e2) {
            e = e2;
            z = true;
        }
    }

    private boolean b(h hVar, b bVar) throws Throwable {
        String strA = a(hVar, 2);
        if (TextUtils.isEmpty(strA)) {
            a(bVar, 32, 99);
            if (!hVar.m()) {
                return true;
            }
            com.avl.engine.n.b.a("Updater", "siglib need update but libDownloadUrl is empty");
            return false;
        }
        com.avl.engine.h.h.c cVarA = f().a(new com.avl.engine.h.e.a(strA));
        try {
            int iA = cVarA.a();
            if (iA != 200) {
                com.avl.engine.antivirus.b.a.i iVar = new com.avl.engine.antivirus.b.a.i(String.valueOf(iA));
                iVar.c();
                throw iVar;
            }
            InputStream inputStreamA = cVarA.c().a();
            j();
            if (a(bVar, inputStreamA, hVar.k(), 32, 95, hVar.j()) == 1) {
                com.avl.engine.n.e.a(hVar.k());
                com.avl.engine.antivirus.b.a.e eVar = new com.avl.engine.antivirus.b.a.e("write siglib file failed.");
                eVar.c();
                throw eVar;
            }
            cVarA.d();
            j();
            boolean zA = a(hVar.h(), hVar.k());
            com.avl.engine.n.b.a("Updater", "update siglib checksum: %s", Boolean.valueOf(zA));
            b(bVar, 97);
            l();
            if (!zA) {
                com.avl.engine.n.e.a(hVar.k());
                com.avl.engine.antivirus.b.a.d dVar = new com.avl.engine.antivirus.b.a.d("checksum siglib failed.");
                dVar.c();
                throw dVar;
            }
            int iA2 = this.d.a(hVar.k(), hVar.i(), hVar.l());
            com.avl.engine.n.e.a(hVar.k());
            if (iA2 == 1) {
                b(bVar, 99);
                return true;
            }
            com.avl.engine.antivirus.b.a.g gVar = new com.avl.engine.antivirus.b.a.g("native install siglib failed.");
            gVar.c();
            throw gVar;
        } catch (Throwable th) {
            if (cVarA == null) {
                throw th;
            }
            cVarA.d();
            throw th;
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:136:0x0032 */
    /* JADX WARN: Code duplicated, block: B:109:0x02e7 A[Catch: a -> 0x0150, IOException -> 0x0154, all -> 0x02f5, TRY_ENTER, TRY_LEAVE, TryCatch #1 {all -> 0x02f5, blocks: (B:109:0x02e7, B:69:0x01cc, B:73:0x01e2, B:78:0x0203, B:101:0x02c7, B:62:0x01ad), top: B:134:0x01ad }] */
    /* JADX WARN: Code duplicated, block: B:129:0x0335  */
    /* JADX WARN: Code duplicated, block: B:131:0x033d A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:140:0x02f9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [com.avl.engine.antivirus.b.d, com.avl.engine.antivirus.b.j] */
    /* JADX WARN: Type inference failed for: r0v63, types: [com.avl.engine.antivirus.b.d, com.avl.engine.antivirus.b.j] */
    /* JADX WARN: Type inference failed for: r0v64, types: [com.avl.engine.antivirus.b.d, com.avl.engine.antivirus.b.j] */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.avl.engine.antivirus.b.d, com.avl.engine.antivirus.b.j] */
    /* JADX WARN: Type inference failed for: r21v0, types: [com.avl.engine.antivirus.b.o] */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2, types: [int] */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.avl.engine.j.b.c, com.avl.engine.j.b.f] */
    /* JADX WARN: Type inference failed for: r7v0, types: [long] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v22 */
    /* JADX WARN: Type inference failed for: r7v23 */
    /* JADX WARN: Type inference failed for: r7v24 */
    /* JADX WARN: Type inference failed for: r7v25 */
    /* JADX WARN: Type inference failed for: r7v5, types: [int] */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private int c(b bVar) throws Throwable {
        byte b;
        byte b2;
        int iD;
        ?? r7;
        boolean zA;
        boolean zB;
        com.avl.engine.n.b.a("Updater", "start update");
        ?? r4 = 1;
        a(bVar, 1);
        int i = 0;
        this.j = 0;
        b(bVar, 1);
        ?? fVar = new com.avl.engine.j.b.f();
        ?? CurrentTimeMillis = System.currentTimeMillis();
        fVar.a(CurrentTimeMillis);
        fVar.a(this.d.a().a().a());
        try {
            try {
                int iD2 = this.d.d();
                fVar.b(iD2);
                j();
                try {
                    if (a(iD2)) {
                        com.avl.engine.h.a.b bVarF = f();
                        g gVarA = a(bVarF, fVar);
                        Objects.toString(gVarA);
                        b(bVar, 4);
                        j();
                        if (a(iD2)) {
                            g gVarB = b(bVarF, fVar);
                            Objects.toString(gVarB);
                            b(bVar, 8);
                            h hVarA = a(bVar, gVarA, gVarB);
                            if (hVarA == null) {
                                com.avl.engine.n.b.a("Updater", "updateInfo == null");
                            } else {
                                new a(hVarA);
                                a(bVar, (hVarA.m() || hVarA.f()) ? 3 : 2);
                                b(bVar, 12);
                                iD = (int) (hVarA.d() + hVarA.j());
                                j();
                                if (a(iD2)) {
                                    long jCurrentTimeMillis = System.currentTimeMillis();
                                    fVar.a(fVar.a() + 4);
                                    fVar.h(hVarA.a());
                                    if (d.f()) {
                                        String strA = a(hVarA, 1);
                                        if (hVarA.f()) {
                                            try {
                                                if (TextUtils.isEmpty(strA)) {
                                                    zA = false;
                                                } else {
                                                    j();
                                                    boolean zA2 = k().a(strA, new File(hVarA.e()), new p(this, bVar), this.f);
                                                    if (!this.f.get() && !zA2) {
                                                        com.avl.engine.n.e.a(hVarA.e());
                                                        com.avl.engine.antivirus.b.a.e eVar = new com.avl.engine.antivirus.b.a.e("write engine file failed.");
                                                        eVar.b();
                                                        throw eVar;
                                                    }
                                                    j();
                                                    boolean zA3 = a(hVarA.b(), hVarA.e());
                                                    b(bVar, 29);
                                                    if (!zA3) {
                                                        com.avl.engine.n.e.a(hVarA.e());
                                                        com.avl.engine.antivirus.b.a.d dVar = new com.avl.engine.antivirus.b.a.d("checksum engine failed.");
                                                        dVar.b();
                                                        throw dVar;
                                                    }
                                                    int iA = this.d.a(hVarA.e(), hVarA.c());
                                                    com.avl.engine.n.e.a(hVarA.e());
                                                    if (iA != 1) {
                                                        com.avl.engine.antivirus.b.a.g gVar = new com.avl.engine.antivirus.b.a.g("native install engine failed.");
                                                        gVar.b();
                                                        throw gVar;
                                                    }
                                                    b(bVar, 31);
                                                }
                                            } catch (Throwable th) {
                                                th = th;
                                                r4 = -1;
                                                i = 0;
                                                b(bVar, r4, i);
                                                this.d.a(fVar);
                                                throw th;
                                            }
                                        } else {
                                            try {
                                                a(bVar, 13, 31);
                                            } catch (Throwable th2) {
                                                th = th2;
                                                i = 0;
                                                r4 = -1;
                                                b(bVar, r4, i);
                                                this.d.a(fVar);
                                                throw th;
                                            }
                                        }
                                        zA = true;
                                    } else {
                                        zA = a(hVarA, bVar);
                                    }
                                    if (zA) {
                                        fVar.d(hVarA.c());
                                        fVar.d(System.currentTimeMillis() - jCurrentTimeMillis);
                                    }
                                    if (zA) {
                                        j();
                                        if (a(iD2)) {
                                            long jCurrentTimeMillis2 = System.currentTimeMillis();
                                            fVar.a(fVar.a() + 8);
                                            fVar.h(hVarA.g());
                                            if (d.f()) {
                                                String strA2 = a(hVarA, 2);
                                                if (!hVarA.m()) {
                                                    a(bVar, 32, 99);
                                                } else if (TextUtils.isEmpty(strA2)) {
                                                    com.avl.engine.n.b.a("Updater", "siglib need update but libDownloadUrl is empty");
                                                    zB = false;
                                                } else {
                                                    j();
                                                    com.avl.engine.n.b.a("Updater", "siglib targetFilePath %s", hVarA.k());
                                                    boolean zA4 = k().a(strA2, new File(hVarA.k()), new q(this, bVar), this.f);
                                                    if (!this.f.get() && !zA4) {
                                                        com.avl.engine.n.e.a(hVarA.k());
                                                        com.avl.engine.antivirus.b.a.e eVar2 = new com.avl.engine.antivirus.b.a.e("write siglib file failed.");
                                                        eVar2.c();
                                                        throw eVar2;
                                                    }
                                                    j();
                                                    boolean zA5 = a(hVarA.h(), hVarA.k());
                                                    com.avl.engine.n.b.a("Updater", "update siglib checksum: %s", Boolean.valueOf(zA5));
                                                    b(bVar, 97);
                                                    l();
                                                    if (!zA5) {
                                                        com.avl.engine.n.e.a(hVarA.k());
                                                        com.avl.engine.antivirus.b.a.d dVar2 = new com.avl.engine.antivirus.b.a.d("checksum siglib failed.");
                                                        dVar2.c();
                                                        throw dVar2;
                                                    }
                                                    int iA2 = this.d.a(hVarA.k(), hVarA.i(), hVarA.l());
                                                    com.avl.engine.n.e.a(hVarA.k());
                                                    if (iA2 != 1) {
                                                        com.avl.engine.antivirus.b.a.g gVar2 = new com.avl.engine.antivirus.b.a.g("native install siglib failed.");
                                                        gVar2.c();
                                                        throw gVar2;
                                                    }
                                                    b(bVar, 99);
                                                }
                                                zB = true;
                                            } else {
                                                zB = b(hVarA, bVar);
                                            }
                                            if (zB) {
                                                fVar.f(hVarA.i());
                                                fVar.d(System.currentTimeMillis() - jCurrentTimeMillis2);
                                            }
                                            if (!zB) {
                                                com.avl.engine.n.b.a("Updater", "updateLib fail");
                                            }
                                            if (iD < 0) {
                                                d(bVar);
                                                b(bVar, -1, 0);
                                                this.d.a(fVar);
                                                return -1;
                                            }
                                            try {
                                                fVar.b("00000");
                                                b(bVar, 1, iD);
                                                this.d.a(fVar);
                                                r7 = 1;
                                            } catch (com.avl.engine.antivirus.b.a.a e) {
                                                e = e;
                                                b2 = 1;
                                                i = iD;
                                                e.printStackTrace();
                                                a(e, (com.avl.engine.j.b.f) fVar);
                                                a(bVar, e);
                                                CurrentTimeMillis = b2;
                                                b(bVar, CurrentTimeMillis, i);
                                                this.d.a(fVar);
                                                iD = i;
                                                r7 = CurrentTimeMillis;
                                            } catch (IOException e2) {
                                                e = e2;
                                                b = 1;
                                                i = iD;
                                                com.avl.engine.n.b.b(e);
                                                a(e, (com.avl.engine.j.b.f) fVar);
                                                a(bVar, e);
                                                CurrentTimeMillis = b;
                                                b(bVar, CurrentTimeMillis, i);
                                                this.d.a(fVar);
                                                iD = i;
                                                r7 = CurrentTimeMillis;
                                            } catch (Throwable th3) {
                                                th = th3;
                                                i = iD;
                                                b(bVar, r4, i);
                                                this.d.a(fVar);
                                                throw th;
                                            }
                                            if (r7 != 1) {
                                                return iD;
                                            }
                                            com.avl.engine.n.b.a("Updater", "update fail");
                                            return -1;
                                        }
                                        com.avl.engine.n.b.a("Updater", "can't continue update, networkType = %s", Integer.valueOf(iD2));
                                    } else {
                                        com.avl.engine.n.b.a("Updater", "updateEngine fail");
                                    }
                                } else {
                                    com.avl.engine.n.b.a("Updater", "can't continue update, networkType = %s", Integer.valueOf(iD2));
                                }
                            }
                        } else {
                            com.avl.engine.n.b.a("Updater", "can't continue update, networkType = %s", Integer.valueOf(iD2));
                        }
                    } else {
                        com.avl.engine.n.b.a("Updater", "can't update, networkType = %s", Integer.valueOf(iD2));
                    }
                    iD = -1;
                    if (iD < 0) {
                        d(bVar);
                        b(bVar, -1, 0);
                        this.d.a(fVar);
                        return -1;
                    }
                    fVar.b("00000");
                    b(bVar, 1, iD);
                    this.d.a(fVar);
                    r7 = 1;
                    if (r7 != 1) {
                        return iD;
                    }
                    com.avl.engine.n.b.a("Updater", "update fail");
                    return -1;
                } catch (com.avl.engine.antivirus.b.a.a e3) {
                    e = e3;
                    i = 0;
                    b2 = -1;
                    e.printStackTrace();
                    a(e, (com.avl.engine.j.b.f) fVar);
                    a(bVar, e);
                    CurrentTimeMillis = b2;
                    b(bVar, CurrentTimeMillis, i);
                    this.d.a(fVar);
                    iD = i;
                    r7 = CurrentTimeMillis;
                    if (r7 != 1) {
                        return iD;
                    }
                    com.avl.engine.n.b.a("Updater", "update fail");
                    return -1;
                } catch (IOException e4) {
                    e = e4;
                    i = 0;
                    b = -1;
                    com.avl.engine.n.b.b(e);
                    a(e, (com.avl.engine.j.b.f) fVar);
                    a(bVar, e);
                    CurrentTimeMillis = b;
                    b(bVar, CurrentTimeMillis, i);
                    this.d.a(fVar);
                    iD = i;
                    r7 = CurrentTimeMillis;
                    if (r7 != 1) {
                        return iD;
                    }
                    com.avl.engine.n.b.a("Updater", "update fail");
                    return -1;
                }
            } catch (Throwable th4) {
                th = th4;
                r4 = CurrentTimeMillis;
            }
        } catch (com.avl.engine.antivirus.b.a.a e5) {
            e = e5;
        } catch (IOException e6) {
            e = e6;
        } catch (Throwable th5) {
            th = th5;
        }
    }

    private com.avl.engine.h.a.b f() {
        com.avl.engine.h.g.d dVar = new com.avl.engine.h.g.d();
        com.avl.engine.b.k kVarA = this.d.a();
        dVar.b(com.xiaomi.onetrack.g.b.h, kVarA == null ? false : com.avl.engine.h.b.a.a(kVarA.c().a("sdk.http.connection"), 2) ? "Close" : "Keep-Alive");
        dVar.a("CHUNKED_STREAMING_MODE", 8192);
        dVar.a("CONNECT_TIMEOUT", 10000);
        dVar.a("READ_TIMEOUT", 10000);
        dVar.a("INSTANCE_FOLLOW_REDIRECTS", true);
        dVar.b("DNS_TIMEOUT", 6000L);
        return new com.avl.engine.h.a.a(dVar);
    }

    private a g() {
        synchronized (this.b) {
            try {
                if (this.g) {
                    return null;
                }
                this.g = true;
                a aVarH = h();
                synchronized (this.b) {
                    this.g = false;
                }
                return aVarH;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private a h() {
        com.avl.engine.n.b.a("Updater", "checkUpdate start");
        a aVar = new a();
        com.avl.engine.j.b.f fVar = new com.avl.engine.j.b.f();
        fVar.a(System.currentTimeMillis());
        fVar.b(this.d.d());
        com.avl.engine.h.a.b bVarF = f();
        try {
            j();
            fVar.a(this.d.a().a().a());
            j();
            g gVarA = a(bVarF, fVar);
            j();
            aVar = a(gVarA, b(bVarF, fVar));
            fVar.b("00000");
            return aVar;
        } catch (MalformedURLException e) {
            com.avl.engine.n.b.b(e);
            a(e, fVar);
            return aVar;
        } catch (com.avl.engine.antivirus.b.a.a e2) {
            com.avl.engine.n.b.b(e2);
            a(e2, fVar);
            return aVar;
        } catch (IOException e3) {
            com.avl.engine.n.b.b(e3);
            a(e3, fVar);
            return aVar;
        } finally {
            this.d.a(fVar);
        }
    }

    private boolean i() {
        synchronized (this.c) {
            try {
                if (!this.i) {
                    return false;
                }
                this.i = false;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void j() throws com.avl.engine.antivirus.b.a.c {
        if (i()) {
            throw new com.avl.engine.antivirus.b.a.c();
        }
    }

    private com.avl.engine.h.a k() {
        if (this.k == null) {
            com.avl.engine.h.a.C0008a c0008a = new com.avl.engine.h.a.C0008a();
            c0008a.a(new File(this.d.a().j())).a(f()).a();
            this.k = c0008a.b();
        }
        return this.k;
    }

    private static void l() {
        com.avl.engine.b.l.a(com.xiaomi.onetrack.b.l.g);
    }

    public final a d() {
        if (com.avl.engine.b.d.c() >= 0) {
            com.avl.engine.g.b.b.a.a().a(6);
        }
        return g();
    }

    public final int e() {
        if (!this.g && !this.h) {
            return -1;
        }
        synchronized (this.c) {
            this.i = true;
            this.f.set(true);
        }
        return 0;
    }

    private void d(b bVar) {
    }

    private int b(b bVar) throws Throwable {
        synchronized (this.a) {
            try {
                if (this.h) {
                    return -1;
                }
                this.h = true;
                this.f.set(false);
                int iC = c(bVar);
                synchronized (this.a) {
                    this.h = false;
                }
                return iC;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private g b(com.avl.engine.h.a.b bVar, com.avl.engine.j.b.f fVar) throws com.avl.engine.antivirus.b.a.f {
        String str;
        com.avl.engine.n.b.a("Updater", "checkSLib start");
        long jCurrentTimeMillis = System.currentTimeMillis();
        fVar.a(fVar.a() + 2);
        String strL = this.d.l();
        fVar.e(strL);
        String strA = a(strL, 2);
        fVar.h(strA);
        if (TextUtils.isEmpty(strA)) {
            if (TextUtils.isEmpty(strL)) {
                str = "get siglib version failed.";
            } else {
                str = "get siglib conf url failed.";
            }
            com.avl.engine.antivirus.b.a.f fVar2 = new com.avl.engine.antivirus.b.a.f(str);
            fVar2.c();
            throw fVar2;
        }
        g gVarA = a(bVar, strA, 1);
        fVar.c(System.currentTimeMillis() - jCurrentTimeMillis);
        return gVarA;
    }

    public final boolean b() {
        return this.h;
    }

    private void b(b bVar, int i, int i2) {
        this.j = 100;
        if (bVar != null) {
            if (i == 1) {
                bVar.c(100);
                bVar.b(i2);
            } else {
                bVar.b(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(b bVar, int i) {
        this.j = i;
        if (bVar != null) {
            bVar.c(i);
        }
    }

    private static void a(com.avl.engine.antivirus.b.a.a aVar, com.avl.engine.j.b.f fVar) {
        fVar.b(aVar.a());
        fVar.g(aVar.getMessage());
    }

    public final boolean a() {
        return this.g;
    }

    private g a(com.avl.engine.h.a.b bVar, com.avl.engine.j.b.f fVar) throws com.avl.engine.antivirus.b.a.f {
        String str;
        long jCurrentTimeMillis = System.currentTimeMillis();
        fVar.a(fVar.a() + 1);
        String strK = this.d.k();
        fVar.c(strK);
        String strA = a(strK, 1);
        fVar.h(strA);
        if (TextUtils.isEmpty(strA)) {
            if (TextUtils.isEmpty(strK)) {
                str = "get engine version failed.";
            } else {
                str = "get engine conf url failed.";
            }
            com.avl.engine.antivirus.b.a.f fVar2 = new com.avl.engine.antivirus.b.a.f(str);
            fVar2.b();
            throw fVar2;
        }
        g gVarA = a(bVar, strA, 0);
        fVar.b(System.currentTimeMillis() - jCurrentTimeMillis);
        return gVarA;
    }

    private String a(String str, int i) {
        f fVar = this.l;
        if (fVar == null || !fVar.b()) {
            if (i == 1) {
                return this.d.a(str);
            }
            if (i == 2) {
                return this.d.b(str);
            }
            return com.xiaomi.onetrack.util.a.c;
        }
        if (i == 1) {
            return this.d.a(str, this.l);
        }
        if (i == 2) {
            return this.d.b(str, this.l);
        }
        return com.xiaomi.onetrack.util.a.c;
    }

    private String a(h hVar, int i) {
        f fVar = this.l;
        if (fVar == null || !fVar.b()) {
            if (i == 1) {
                return hVar.a();
            }
            if (i == 2) {
                return hVar.g();
            }
            return null;
        }
        if (i == 1) {
            return a(hVar.a());
        }
        if (i == 2) {
            return a(hVar.g());
        }
        return null;
    }

    private boolean a(h hVar, b bVar) throws Throwable {
        String strA = a(hVar, 1);
        if (strA == null) {
            a(bVar, 13, 31);
            return !hVar.f();
        }
        com.avl.engine.h.h.c cVarA = f().a(new com.avl.engine.h.e.a(strA));
        try {
            int iA = cVarA.a();
            if (iA == 200) {
                InputStream inputStreamA = cVarA.c().a();
                j();
                if (a(bVar, inputStreamA, hVar.e(), 13, 27, hVar.d()) != 1) {
                    cVarA.d();
                    j();
                    boolean zA = a(hVar.b(), hVar.e());
                    b(bVar, 29);
                    if (zA) {
                        int iA2 = this.d.a(hVar.e(), hVar.c());
                        com.avl.engine.n.e.a(hVar.e());
                        if (iA2 == 1) {
                            b(bVar, 31);
                            return true;
                        }
                        com.avl.engine.antivirus.b.a.g gVar = new com.avl.engine.antivirus.b.a.g("native install engine failed.");
                        gVar.b();
                        throw gVar;
                    }
                    com.avl.engine.n.e.a(hVar.e());
                    com.avl.engine.antivirus.b.a.d dVar = new com.avl.engine.antivirus.b.a.d("checksum engine failed.");
                    dVar.b();
                    throw dVar;
                }
                com.avl.engine.n.e.a(hVar.e());
                com.avl.engine.antivirus.b.a.e eVar = new com.avl.engine.antivirus.b.a.e("write engine file failed.");
                eVar.b();
                throw eVar;
            }
            com.avl.engine.antivirus.b.a.i iVar = new com.avl.engine.antivirus.b.a.i(String.valueOf(iA));
            iVar.b();
            cVarA.d();
            throw iVar;
        } catch (Throwable th) {
            if (cVarA != null) {
                cVarA.d();
                throw th;
            }
            throw th;
        }
    }

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String strA = this.l.a();
        return Pattern.compile("^http(s)?://[^/]+/").matcher(str).replaceFirst(strA + "/");
    }

    private static a a(g gVar, g gVar2) throws com.avl.engine.antivirus.b.a.h {
        if (gVar == null || gVar2 == null) {
            com.avl.engine.n.b.a("Updater", "engineConf = %s, libConf = %s", gVar, gVar2);
            return null;
        }
        if (!gVar.isEmpty()) {
            if (!gVar2.isEmpty()) {
                a aVar = new a();
                boolean z = true;
                try {
                    if (!gVar.a()) {
                        aVar.b = gVar.d();
                        aVar.a = 1;
                        aVar.c = gVar.b();
                    } else {
                        aVar.a = 0;
                    }
                    try {
                        if (!gVar2.a()) {
                            aVar.e = gVar2.d();
                            aVar.d = 1;
                            aVar.f = gVar2.b();
                        } else {
                            aVar.d = 0;
                        }
                        com.avl.engine.n.b.a("Updater", "VLibUpdate: %d", Integer.valueOf(aVar.d));
                        return aVar;
                    } catch (com.avl.engine.antivirus.b.a.b e) {
                        e = e;
                        z = false;
                        com.avl.engine.n.b.b(e);
                        com.avl.engine.antivirus.b.a.h hVar = new com.avl.engine.antivirus.b.a.h(e);
                        if (z) {
                            hVar.b();
                            throw hVar;
                        }
                        hVar.c();
                        throw hVar;
                    }
                } catch (com.avl.engine.antivirus.b.a.b e2) {
                    e = e2;
                }
            } else {
                com.avl.engine.antivirus.b.a.h hVar2 = new com.avl.engine.antivirus.b.a.h("siglib conf is empty.");
                hVar2.c();
                throw hVar2;
            }
        } else {
            com.avl.engine.antivirus.b.a.h hVar3 = new com.avl.engine.antivirus.b.a.h("engine conf is empty.");
            hVar3.b();
            throw hVar3;
        }
    }

    private boolean a(int i) {
        int iD = this.d.d();
        return iD == i || iD != 0;
    }

    private int a(b bVar, InputStream inputStream, String str, int i, int i2, long j) throws Throwable {
        int i3;
        if (inputStream == null) {
            com.avl.engine.n.b.a("Updater", "inputStream == null");
            return 1;
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                File file = new File(str);
                File parentFile = file.getParentFile();
                if (parentFile != null && !parentFile.exists()) {
                    parentFile.mkdirs();
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                int i4 = ((int) ((j / 512.0f) / (i2 - i))) + 1;
                try {
                    byte[] bArr = new byte[PowerNest.SHARK_HTTP];
                    int i5 = 0;
                    while (true) {
                        int i6 = inputStream.read(bArr);
                        if (i6 == -1) {
                            break;
                        }
                        j();
                        fileOutputStream2.write(bArr, 0, i6);
                        i5++;
                        if (i5 % i4 == 0 && (i3 = (i5 / i4) + i) < i2) {
                            b(bVar, i3);
                        }
                    }
                    fileOutputStream2.flush();
                    if (i5 < i2 - 1) {
                        a(bVar, i5, i2);
                    }
                    b(bVar, i2);
                    com.avl.engine.n.i.a(fileOutputStream2);
                    com.avl.engine.n.i.a((Closeable) inputStream);
                    return 0;
                } catch (FileNotFoundException e) {
                    e = e;
                    fileOutputStream = fileOutputStream2;
                    com.avl.engine.n.b.b(e);
                    com.avl.engine.n.i.a(fileOutputStream);
                    com.avl.engine.n.i.a((Closeable) inputStream);
                    return 1;
                } catch (IOException e2) {
                    e = e2;
                    fileOutputStream = fileOutputStream2;
                    com.avl.engine.n.b.b(e);
                    com.avl.engine.n.i.a(fileOutputStream);
                    com.avl.engine.n.i.a((Closeable) inputStream);
                    return 1;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    com.avl.engine.n.i.a(fileOutputStream);
                    com.avl.engine.n.i.a((Closeable) inputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (FileNotFoundException e3) {
            e = e3;
        } catch (IOException e4) {
            e = e4;
        }
    }

    private void a(b bVar, int i, int i2) {
        while (i < i2) {
            i++;
            b(bVar, i);
        }
    }

    private static g a(com.avl.engine.h.a.b bVar, String str, int i) {
        com.avl.engine.n.b.a("Updater", "Get start");
        com.avl.engine.h.h.c cVarA = bVar.a(new com.avl.engine.h.e.b(str));
        try {
            int iA = cVarA.a();
            if (iA != 200) {
                com.avl.engine.antivirus.b.a.i iVar = new com.avl.engine.antivirus.b.a.i(String.valueOf(iA));
                if (i == 0) {
                    iVar.b();
                } else {
                    iVar.c();
                }
                throw iVar;
            }
            return a(cVarA);
        } finally {
            if (cVarA != null) {
                cVarA.d();
            }
        }
    }

    private static g a(com.avl.engine.h.h.c cVar) throws Throwable {
        com.avl.engine.n.b.a("Updater", "get Uf start");
        com.avl.engine.h.h.b bVarC = cVar.c();
        InputStream inputStreamA = bVarC.a();
        if ("gzip".equals(bVarC.c().a())) {
            inputStreamA = new GZIPInputStream(inputStreamA);
        }
        g gVar = new g();
        InputStreamReader inputStreamReader = null;
        try {
            Matcher matcher = Pattern.compile("^(.+?)charset=(.+?)$").matcher(bVarC.b().a());
            String str = "utf-8";
            if (matcher.find()) {
                String strTrim = matcher.group(2).trim();
                if (!TextUtils.isEmpty(strTrim)) {
                    str = strTrim;
                }
            }
            InputStreamReader inputStreamReader2 = new InputStreamReader(inputStreamA, str);
            try {
                gVar.load(inputStreamReader2);
                com.avl.engine.n.i.a(inputStreamReader2);
                com.avl.engine.n.i.a((Closeable) inputStreamA);
                return gVar;
            } catch (Throwable th) {
                inputStreamReader = inputStreamReader2;
                th = th;
                com.avl.engine.n.i.a(inputStreamReader);
                com.avl.engine.n.i.a((Closeable) inputStreamA);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static boolean a(String str, String str2) throws Throwable {
        String strD = com.avl.engine.n.c.b.d(str2);
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(strD) || !str.equals(strD)) ? false : true;
    }

    private static void a(Exception exc, com.avl.engine.j.b.f fVar) {
        String str;
        String str2;
        if (exc instanceof MalformedURLException) {
            str = "0202";
        } else {
            str = "0201";
        }
        if ((fVar.a() & 2) != 0) {
            str2 = "2";
        } else {
            str2 = "1";
        }
        fVar.b(com.avl.engine.j.b.f.a.a(str2, str));
        String strB = fVar.b();
        com.avl.engine.j.b.d dVar = new com.avl.engine.j.b.d();
        dVar.a(System.currentTimeMillis());
        dVar.a(exc);
        dVar.a(strB);
    }

    private void a(b bVar, Exception exc) {
        if (exc instanceof com.avl.engine.antivirus.b.a.c) {
            d(bVar);
            return;
        }
        if (exc instanceof com.avl.engine.antivirus.b.a.d) {
            d(bVar);
            return;
        }
        if (exc instanceof com.avl.engine.antivirus.b.a.e) {
            d(bVar);
            return;
        }
        if (exc instanceof com.avl.engine.antivirus.b.a.f) {
            d(bVar);
            return;
        }
        if (exc instanceof com.avl.engine.antivirus.b.a.g) {
            d(bVar);
            return;
        }
        if (exc instanceof com.avl.engine.antivirus.b.a.h) {
            d(bVar);
            return;
        }
        if (exc instanceof com.avl.engine.antivirus.b.a.i) {
            d(bVar);
        } else if (exc instanceof SocketTimeoutException) {
            d(bVar);
        } else {
            d(bVar);
        }
    }

    private static void a(b bVar, int i) {
        if (bVar != null) {
            bVar.a(i);
        }
    }

    public final int a(b bVar) {
        if (com.avl.engine.b.d.c() >= 0) {
            com.avl.engine.g.b.b.a.a().a(7);
        }
        return b(bVar);
    }

    public final int c() {
        if (this.h) {
            return this.j;
        }
        return -1;
    }
}
