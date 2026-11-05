package a.b.a.e.f0;

import android.os.Handler;
import android.os.Message;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class y implements a.b.a.e.i0.b.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static int f385c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f386a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Handler f387b = a.b.a.e.k0.g.b().c(new a());

    public class a implements Handler.Callback {

        /* JADX INFO: renamed from: a.b.a.e.f0.y$a$a, reason: collision with other inner class name */
        public class RunnableC0010a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Message f389a;

            public RunnableC0010a(Message message) {
                this.f389a = message;
            }

            /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
            @Override // java.lang.Runnable
            public void run() {
                String string = this.f389a.getData().getString("app_id");
                Objects.requireNonNull(y.this);
                try {
                    a.b.a.e.e0.a.d().c(string);
                } catch (Exception e) {
                    a.b.a.e.l0.p.c("UploadTimer", "forceRefreshAppConfig: " + e);
                }
            }
        }

        public class b implements Runnable {
            public b() {
            }

            /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
            @Override // java.lang.Runnable
            public void run() {
                String str;
                y yVar = y.this;
                Objects.requireNonNull(yVar);
                if (!a.b.a.a.a.c.a.m0()) {
                    str = "doRetry 不用处理消息, 当前未连接网络";
                } else {
                    if (!z.e().l(4).f402a) {
                        yVar.f387b.removeMessages(1000);
                        int i = y.f385c + 1;
                        y.f385c = i;
                        yVar.f386a = ((long) a.b.a.e.l0.o.c("UploadTimer", i)) * 1000;
                        StringBuilder sbC = a.a.a.a.a.c("will restart retry msg after,failedCount ");
                        sbC.append(y.f385c);
                        a.b.a.e.l0.p.c("UploadTimer", sbC.toString());
                        yVar.f387b.sendEmptyMessageDelayed(1000, yVar.f386a);
                        return;
                    }
                    y.f385c = 0;
                    yVar.f386a = 0L;
                    yVar.f387b.removeMessages(1000);
                    str = "retry success";
                }
                a.b.a.e.l0.p.c("UploadTimer", str);
            }
        }

        public class c implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f392a;

            public c(int i) {
                this.f392a = i;
            }

            /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
            /* JADX WARN: Code duplicated, block: B:12:0x0036  */
            /* JADX WARN: Code duplicated, block: B:13:0x003a A[Catch: Exception -> 0x00c0, TryCatch #0 {Exception -> 0x00c0, blocks: (B:3:0x000f, B:5:0x0021, B:10:0x0030, B:26:0x00bc, B:13:0x003a, B:16:0x0042, B:19:0x0049, B:21:0x0057, B:23:0x0064, B:24:0x008d, B:25:0x0097, B:6:0x0025), top: B:31:0x000f }] */
            /* JADX WARN: Code duplicated, block: B:25:0x0097 A[Catch: Exception -> 0x00c0, TryCatch #0 {Exception -> 0x00c0, blocks: (B:3:0x000f, B:5:0x0021, B:10:0x0030, B:26:0x00bc, B:13:0x003a, B:16:0x0042, B:19:0x0049, B:21:0x0057, B:23:0x0064, B:24:0x008d, B:25:0x0097, B:6:0x0025), top: B:31:0x000f }] */
            /* JADX WARN: Instruction removed from duplicated block: B:25:0x0097, please report this as an issue */
            @Override // java.lang.Runnable
            public void run() {
                boolean z;
                boolean zA;
                String str;
                y yVar = y.this;
                int i = this.f392a;
                Objects.requireNonNull(yVar);
                try {
                    long jG = a.b.a.e.l0.u.g("forbidden_upload_until", 0L);
                    if (jG <= System.currentTimeMillis() + 172800000) {
                        z = jG > System.currentTimeMillis();
                        if (a.b.a.a.a.c.a.m0()) {
                            zA = a.b.a.e.e0.k.a(false);
                            if (zA || z || a.b.a.e.e0.k.i()) {
                                str = "不用处理消息, available=" + zA + ", forbidden now = " + z + ", 数据库是否为空=" + a.b.a.e.e0.k.i();
                            } else {
                                if (z.e().l(i).f402a) {
                                    y.f385c = 0;
                                    yVar.f386a = 0L;
                                    yVar.f387b.removeMessages(1000);
                                    return;
                                }
                                a.b.a.e.l0.p.c("UploadTimer", "handleCheckUpload failed, will check if need to send retry msg");
                                if (yVar.f387b.hasMessages(1000)) {
                                    return;
                                }
                                int i2 = y.f385c + 1;
                                y.f385c = i2;
                                long jC = ((long) a.b.a.e.l0.o.c("UploadTimer", i2)) * 1000;
                                yVar.f386a = jC;
                                yVar.f387b.sendEmptyMessageDelayed(1000, jC);
                                str = "fire retry timer after " + yVar.f386a;
                            }
                        } else {
                            str = "不用处理消息, 当前未连接网络";
                        }
                        a.b.a.e.l0.p.c("UploadTimer", str);
                    }
                    a.b.a.e.l0.u.l("forbidden_upload_until", 0L);
                    if (a.b.a.a.a.c.a.m0()) {
                        str = "不用处理消息, 当前未连接网络";
                    } else {
                        zA = a.b.a.e.e0.k.a(false);
                        if (zA) {
                            str = "不用处理消息, available=" + zA + ", forbidden now = " + z + ", 数据库是否为空=" + a.b.a.e.e0.k.i();
                        } else {
                            str = "不用处理消息, available=" + zA + ", forbidden now = " + z + ", 数据库是否为空=" + a.b.a.e.e0.k.i();
                        }
                    }
                    a.b.a.e.l0.p.c("UploadTimer", str);
                } catch (Exception e) {
                    a.b.a.e.l0.p.h("UploadTimer", "handleMessage failed,", e);
                    a.b.a.a.a.c.a.y0("up", "s1", e);
                }
            }
        }

        public a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            StringBuilder sbC = a.a.a.a.a.c("handleMessage, msg.what=");
            sbC.append(message.what);
            a.b.a.e.l0.p.c("UploadTimer", sbC.toString());
            int i = message.what;
            if (i == 1000) {
                a.b.a.e.k0.g.b().a("CommonUploadPool", new b());
                return true;
            }
            if (i != 1001) {
                a.b.a.e.k0.g.b().a("CommonUploadPool", new c(i));
                return true;
            }
            a.b.a.e.k0.g.b().a("CommonUploadPool", new RunnableC0010a(message));
            return true;
        }
    }

    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f394a;

        public b(int i) {
            this.f394a = i;
        }

        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
        @Override // java.lang.Runnable
        public void run() {
            long jH = a.b.a.e.e0.k.h(this.f394a);
            StringBuilder sbC = a.a.a.a.a.c("will check prio=");
            sbC.append(this.f394a);
            sbC.append(", delay=");
            sbC.append(jH);
            a.b.a.e.l0.p.c("UploadTimer", sbC.toString());
            y yVar = y.this;
            int i = this.f394a;
            yVar.f387b.removeMessages(i);
            yVar.f387b.sendEmptyMessageDelayed(i, jH);
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            y.this.b(0);
            y.this.b(2);
            y.this.b(4);
        }
    }

    public y() {
        a.b.a.e.i0.b.c().a(this);
    }

    @Override // a.b.a.e.i0.b.a
    public void a(boolean z) {
        a.b.a.e.l0.p.c("UploadTimer", "netReceiver, 网络是否可用=" + z);
        if (z) {
            a.b.a.e.k0.g.b().a("CommonUploadPool", new c());
        }
    }

    public void b(int i) {
        if (a.b.a.a.a.c.a.m0()) {
            if (this.f387b.hasMessages(1000)) {
                a.b.a.e.l0.p.c("UploadTimer", "in retry mode, return, prio=" + i);
                return;
            }
            if (!this.f387b.hasMessages(i)) {
                a.b.a.e.k0.g.b().a("CommonUploadPool", new b(i));
                return;
            }
            a.b.a.e.l0.p.c("UploadTimer", "has message\u3000prio=" + i);
        }
    }
}
