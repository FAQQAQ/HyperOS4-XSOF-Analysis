package defpackage;

import com.miui.guardprovider.aiguard.AiGuardService;

/* JADX INFO: loaded from: classes.dex */
public final class x7 extends lr2 implements pp0 {
    public re3 f;
    public int g;
    public /* synthetic */ Object h;
    public final /* synthetic */ AiGuardService i;
    public final /* synthetic */ int j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x7(int i, j20 j20Var, AiGuardService aiGuardService) {
        super(2, j20Var);
        this.i = aiGuardService;
        this.j = i;
    }

    @Override // defpackage.pp0
    public final Object H(Object obj, Object obj2) {
        return ((x7) k((j20) obj2, (g30) obj)).o(c13.a);
    }

    @Override // defpackage.sk
    public final j20 k(j20 j20Var, Object obj) {
        x7 x7Var = new x7(this.j, j20Var, this.i);
        x7Var.h = obj;
        return x7Var;
    }

    /* JADX DEBUG: Duplicate block (B:44:0x00df) to fix multi-entry loop: BACK_EDGE: B:44:0x00df -> B:23:0x0054 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:29:0x0079 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:53:0x00f5 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:54:0x0012 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:56:0x0054 */
    /* JADX DEBUG: Multi-variable search result rejected for r2v1, resolved type: re3 */
    /* JADX DEBUG: Multi-variable search result rejected for r2v16, resolved type: re3 */
    /* JADX DEBUG: Multi-variable search result rejected for r2v17, resolved type: re3 */
    /* JADX DEBUG: Multi-variable search result rejected for r2v2, resolved type: re3 */
    /* JADX DEBUG: Multi-variable search result rejected for r2v3, resolved type: re3 */
    /* JADX DEBUG: Multi-variable search result rejected for r2v4, resolved type: re3 */
    /* JADX DEBUG: Multi-variable search result rejected for r2v6, resolved type: re3 */
    /* JADX DEBUG: Multi-variable search result rejected for r2v7, resolved type: re3 */
    /* JADX WARN: Code duplicated, block: B:25:0x005a A[Catch: all -> 0x0020, Exception -> 0x0023, CancellationException -> 0x00f5, Merged into TryCatch #0 {all -> 0x0020, Exception -> 0x0023, CancellationException -> 0x00f5, Exception -> 0x0023, blocks: (B:8:0x001c, B:23:0x0054, B:25:0x005a, B:31:0x007c, B:34:0x0083, B:37:0x009a, B:39:0x00bc, B:40:0x00c3, B:48:0x00ea, B:17:0x002e, B:20:0x0034), top: B:54:0x0012 }] */
    /* JADX WARN: Code duplicated, block: B:27:0x0077  */
    /* JADX WARN: Code duplicated, block: B:28:0x0078  */
    /* JADX WARN: Code duplicated, block: B:30:0x007b  */
    /* JADX WARN: Code duplicated, block: B:31:0x007c A[Catch: all -> 0x0020, Exception -> 0x0023, CancellationException -> 0x00f5, Merged into TryCatch #0 {all -> 0x0020, Exception -> 0x0023, CancellationException -> 0x00f5, Exception -> 0x0023, blocks: (B:8:0x001c, B:23:0x0054, B:25:0x005a, B:31:0x007c, B:34:0x0083, B:37:0x009a, B:39:0x00bc, B:40:0x00c3, B:48:0x00ea, B:17:0x002e, B:20:0x0034), top: B:54:0x0012 }, PHI: r2
      0x007c: PHI (r2v9 ??) = (r2v10 ??), (r2v11 ??) binds: [B:29:0x0079, B:20:0x0034] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:33:0x0082  */
    /* JADX WARN: Code duplicated, block: B:39:0x00bc A[Catch: all -> 0x0020, Exception -> 0x0023, CancellationException -> 0x00f5, Merged into TryCatch #0 {all -> 0x0020, Exception -> 0x0023, CancellationException -> 0x00f5, Exception -> 0x0023, blocks: (B:8:0x001c, B:23:0x0054, B:25:0x005a, B:31:0x007c, B:34:0x0083, B:37:0x009a, B:39:0x00bc, B:40:0x00c3, B:48:0x00ea, B:17:0x002e, B:20:0x0034), top: B:54:0x0012 }] */
    /* JADX WARN: Code duplicated, block: B:40:0x00c3 A[Catch: all -> 0x0020, Exception -> 0x0023, CancellationException -> 0x00f5, Merged into TryCatch #0 {all -> 0x0020, Exception -> 0x0023, CancellationException -> 0x00f5, Exception -> 0x0023, blocks: (B:8:0x001c, B:23:0x0054, B:25:0x005a, B:31:0x007c, B:34:0x0083, B:37:0x009a, B:39:0x00bc, B:40:0x00c3, B:48:0x00ea, B:17:0x002e, B:20:0x0034), top: B:54:0x0012 }, TRY_LEAVE] */
    /* JADX WARN: Code duplicated, block: B:43:0x00de  */
    /* JADX WARN: Code duplicated, block: B:44:0x00df A[DONT_INVERT] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v10, types: [re3] */
    /* JADX WARN: Type inference failed for: r2v11, types: [re3] */
    /* JADX WARN: Type inference failed for: r2v13, types: [re3] */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v8, types: [re3] */
    /* JADX WARN: Type inference failed for: r2v9, types: [re3] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x0082 -> B:23:0x0054). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // defpackage.sk
    public final java.lang.Object o(java.lang.Object r14) {
        /*
            r13 = this;
            java.lang.Object r0 = r13.h
            g30 r0 = (defpackage.g30) r0
            h30 r1 = defpackage.h30.b
            int r2 = r13.g
            c13 r3 = defpackage.c13.a
            java.lang.String r4 = "AiGuardService"
            r5 = 1
            r6 = 3
            r7 = 2
            r8 = 0
            com.miui.guardprovider.aiguard.AiGuardService r9 = r13.i
            if (r2 == 0) goto L38
            if (r2 == r5) goto L32
            if (r2 == r7) goto L2c
            if (r2 != r6) goto L26
            re3 r2 = r13.f
            defpackage.kk3.y(r14)     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            goto L54
        L20:
            r13 = move-exception
            goto Lfd
        L23:
            r13 = move-exception
            goto Lea
        L26:
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            defpackage.ik.j(r13)
            return r8
        L2c:
            re3 r2 = r13.f
            defpackage.kk3.y(r14)     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            goto L9a
        L32:
            re3 r2 = r13.f
            defpackage.kk3.y(r14)     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            goto L7c
        L38:
            defpackage.kk3.y(r14)
            re3 r14 = new re3
            android.content.Context r2 = r9.getApplicationContext()
            r2.getClass()
            r14.<init>()
            r14.b = r2
            android.media.MediaRecorder r10 = new android.media.MediaRecorder
            r10.<init>(r2)
            r14.c = r10
            java.lang.invoke.VarHandle.storeStoreFence()
            r2 = r14
        L54:
            boolean r14 = defpackage.az0.D(r0)     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            if (r14 == 0) goto Le2
            r2.x()     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            vg3 r14 = defpackage.vd0.b     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            xd0 r14 = defpackage.xd0.SECONDS     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            long r10 = defpackage.tn.D(r6, r14)     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            r13.h = r0     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            r13.f = r2     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            r13.g = r5     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            long r10 = defpackage.f03.L(r10)     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            java.lang.Object r14 = defpackage.f03.t(r10, r13)     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            h30 r10 = defpackage.h30.b     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            if (r14 != r10) goto L78
            goto L79
        L78:
            r14 = r3
        L79:
            if (r14 != r1) goto L7c
            goto Le1
        L7c:
            java.nio.file.Path r14 = r2.y()     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            if (r14 != 0) goto L83
            goto L54
        L83:
            w60 r10 = defpackage.xc0.a     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            o60 r10 = defpackage.o60.d     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            w7 r11 = new w7     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            r12 = 0
            r11.<init>(r9, r14, r8, r12)     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            r13.h = r0     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            r13.f = r2     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            r13.g = r7     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            java.lang.Object r14 = defpackage.uk3.E(r10, r11, r13)     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            if (r14 != r1) goto L9a
            goto Le1
        L9a:
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            boolean r14 = r14.booleanValue()     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            int r10 = r13.j     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            int r10 = com.miui.guardprovider.aiguard.AiGuardService.b(r9, r10)     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            r11.<init>()     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            java.lang.String r12 = "video scam detection result:"
            r11.append(r12)     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            r11.append(r14)     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            java.lang.String r11 = r11.toString()     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            android.util.Log.i(r4, r11)     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            if (r14 == 0) goto Lc3
            com.miui.guardprovider.aiguard.AiGuardService.a(r9, r10)     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            r9.stopSelf()     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            goto Le2
        Lc3:
            vg3 r14 = defpackage.vd0.b     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            xd0 r14 = defpackage.xd0.SECONDS     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            long r10 = defpackage.tn.D(r7, r14)     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            r13.h = r0     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            r13.f = r2     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            r13.g = r6     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            long r10 = defpackage.f03.L(r10)     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            java.lang.Object r14 = defpackage.f03.t(r10, r13)     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            h30 r10 = defpackage.h30.b     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23 java.util.concurrent.CancellationException -> Lf5
            if (r14 != r10) goto Lde
            goto Ldf
        Lde:
            r14 = r3
        Ldf:
            if (r14 != r1) goto L54
        Le1:
            return r1
        Le2:
            java.lang.Object r13 = r2.c
            android.media.MediaRecorder r13 = (android.media.MediaRecorder) r13
            r13.release()
            return r3
        Lea:
            java.lang.String r14 = "Detection loop quit unexpectedly"
            int r13 = android.util.Log.e(r4, r14, r13)     // Catch: java.lang.Throwable -> L20
            java.lang.Integer r3 = new java.lang.Integer     // Catch: java.lang.Throwable -> L20
            r3.<init>(r13)     // Catch: java.lang.Throwable -> L20
        Lf5:
            java.lang.Object r13 = r2.c
            android.media.MediaRecorder r13 = (android.media.MediaRecorder) r13
            r13.release()
            goto L105
        Lfd:
            java.lang.Object r14 = r2.c
            android.media.MediaRecorder r14 = (android.media.MediaRecorder) r14
            r14.release()
            throw r13
        L105:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.x7.o(java.lang.Object):java.lang.Object");
    }
}
