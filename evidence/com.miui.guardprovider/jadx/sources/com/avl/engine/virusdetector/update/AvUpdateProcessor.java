package com.avl.engine.virusdetector.update;

import com.avl.engine.b.k;
import com.avl.engine.b.l;
import com.avl.engine.b.o;
import com.avl.engine.j.f;
import com.avl.engine.security.AVLA;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class AvUpdateProcessor extends com.avl.engine.antivirus.b.d {
    private final List b;
    private final d c;

    public AvUpdateProcessor(k kVar) {
        super(kVar);
        this.b = new CopyOnWriteArrayList();
        this.c = c.a(kVar);
    }

    @Override // com.avl.engine.antivirus.b.i
    public final int a(String str, String str2, String str3) {
        k kVarA = l.a(com.xiaomi.onetrack.b.l.g);
        com.avl.engine.virusdetector.b bVar = kVarA instanceof com.avl.engine.virusdetector.b ? (com.avl.engine.virusdetector.b) kVarA : null;
        if (bVar == null) {
            return -5;
        }
        int iA = bVar.a(str, str2, str3);
        com.avl.engine.n.b.a("AvUpdate", "installSig ret:%d", Integer.valueOf(iA));
        if (iA == -5) {
            return -5;
        }
        this.c.a(iA, str2, str3);
        if (iA != 1) {
            return -1;
        }
        com.avl.engine.b.a.c cVarC = this.a.c();
        cVarC.d();
        cVarC.a(2, null);
        if (!this.b.isEmpty()) {
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                ((o) it.next()).a();
            }
        }
        return 1;
    }

    @Override // com.avl.engine.antivirus.b.d
    public final String g() {
        return "av.updateUrl.siglib";
    }

    @Override // com.avl.engine.antivirus.b.d
    public final String h() {
        return "av.updateUrl.engine";
    }

    @Override // com.avl.engine.antivirus.b.d
    public final String i() {
        return "av.channel";
    }

    @Override // com.avl.engine.antivirus.b.d
    public final String j() {
        String cpuPlatform = AVLA.a().getCpuPlatform();
        if ("x86".equals(cpuPlatform)) {
            return "x86";
        }
        if ("x86_64".equals(cpuPlatform)) {
            return "x86/64";
        }
        if ("mips".equals(cpuPlatform)) {
            return "mips";
        }
        if ("mips64".equals(cpuPlatform)) {
            return "mips/64";
        }
        if (cpuPlatform.startsWith("arm64")) {
            return "64";
        }
        if (cpuPlatform.startsWith("armeabi-v7a")) {
            return "v7a";
        }
        return cpuPlatform.startsWith("arm") ? com.xiaomi.onetrack.util.a.c : "unknown";
    }

    @Override // com.avl.engine.antivirus.b.i
    public final String k() {
        return AVLA.a().getEngineVersion();
    }

    @Override // com.avl.engine.antivirus.b.d, com.avl.engine.antivirus.b.i
    public final String l() {
        return this.c.c() == d.a.TOTAL ? "total" : AVLA.a().getSigLibVersion();
    }

    @Override // com.avl.engine.antivirus.b.j
    public final void m() {
        f.b();
    }

    @Override // com.avl.engine.antivirus.b.d, com.avl.engine.antivirus.b.i
    public final void a(o oVar) {
        this.b.add(oVar);
    }

    @Override // com.avl.engine.antivirus.b.j
    public final void a(com.avl.engine.j.b.c cVar) {
        f.a(cVar);
    }

    @Override // com.avl.engine.antivirus.b.i
    public final int a(String str, String str2) {
        if (AVLA.a().installPackage(str, 0, str2, null) != 1) {
            return -1;
        }
        if (!this.b.isEmpty()) {
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                ((o) it.next()).a(str2);
            }
        }
        return 1;
    }
}
