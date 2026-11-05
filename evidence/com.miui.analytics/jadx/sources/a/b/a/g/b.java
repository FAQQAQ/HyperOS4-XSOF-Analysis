package a.b.a.g;

import a.b.a.e.e;
import a.b.a.e.i0.d;
import a.b.a.e.l0.m;
import a.b.a.e.l0.o;
import a.b.a.e.l0.p;
import a.b.a.e.l0.r;
import a.b.a.e.l0.u;
import a.b.a.e.l0.v;
import a.b.a.e.y;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class b {
    public static b i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ScheduledExecutorService f747a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AtomicBoolean f748b = new AtomicBoolean(false);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f749c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f750d = 0;
    public volatile boolean e = false;
    public volatile boolean f;
    public String g;
    public String h;

    public class a implements ThreadFactory {
        public a(b bVar) {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "ot_upload_log_file");
            thread.setDaemon(false);
            thread.setPriority(5);
            return thread;
        }
    }

    /* JADX INFO: renamed from: a.b.a.g.b$b, reason: collision with other inner class name */
    public class RunnableC0019b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ JSONObject f751a;

        public RunnableC0019b(JSONObject jSONObject) {
            this.f751a = jSONObject;
        }

        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
        @Override // java.lang.Runnable
        public void run() throws Throwable {
            u.m("u_l_t_f", this.f751a.toString());
            b.a(b.this);
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            b.a(b.this);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public b() {
        this.f = false;
        this.g = "";
        try {
            String strI = u.i();
            if (TextUtils.isEmpty(strI)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(strI);
            this.g = jSONObject.optString("utid");
            this.h = jSONObject.optString("dl");
            this.f = jSONObject.optBoolean("re");
        } catch (Exception e) {
            p.h("UploadLogTaskManager", "loadConfigFromSp error: ", e);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX WARN: Code duplicated, block: B:8:0x0021 A[Catch: Exception -> 0x0036, TryCatch #1 {Exception -> 0x0036, blocks: (B:3:0x0005, B:5:0x0009, B:7:0x001b, B:8:0x0021, B:10:0x0025, B:12:0x002d), top: B:23:0x0005 }] */
    public static void a(b bVar) throws Throwable {
        Objects.requireNonNull(bVar);
        try {
            if (!p.f605d) {
                if (v.e(bVar.h) - System.currentTimeMillis() > 86400000) {
                    p.c("UploadLogTaskManager", "距离截止时间超过1天，暂不上报");
                } else if (!bVar.e && !bVar.f748b.get()) {
                    bVar.i();
                    bVar.j(0L);
                }
            } else if (!bVar.e) {
                bVar.i();
                bVar.j(0L);
            }
        } catch (Exception e) {
            try {
                p.h("UploadLogTaskManager", "uploadLog error: ", e);
            } catch (Exception e2) {
                p.h("UploadLogTaskManager", "triggerUploadLogTask error: ", e2);
            }
        }
    }

    public static b f() {
        if (i == null) {
            synchronized (b.class) {
                if (i == null) {
                    i = new b();
                }
            }
        }
        return i;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX WARN: Code duplicated, block: B:30:0x008c  */
    /* JADX WARN: Code duplicated, block: B:34:? A[RETURN, SYNTHETIC] */
    public void b(JSONObject jSONObject) {
        String str;
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("ul");
        if (jSONObjectOptJSONObject != null) {
            boolean z = false;
            try {
                String strOptString = jSONObjectOptJSONObject.optString("utid");
                String strOptString2 = jSONObjectOptJSONObject.optString("dl");
                if (TextUtils.isEmpty(strOptString) || TextUtils.isEmpty(strOptString2)) {
                    str = "isNeedUpload, but uploadTaskId or deadline is empty";
                } else {
                    if (v.e(strOptString2) >= System.currentTimeMillis()) {
                        boolean zOptBoolean = jSONObjectOptJSONObject.optBoolean("re");
                        if (!strOptString.equals(this.g)) {
                            this.g = strOptString;
                            this.h = strOptString2;
                            this.f = zOptBoolean;
                        }
                        if (this.e || this.f) {
                            str = "isNeedUpload, but task is running or finished";
                        } else {
                            p.r(true);
                            p.c("UploadLogTaskManager", "isNeedUpload, taskId: " + strOptString + ", deadline: " + strOptString2 + ", isUploadTaskFinished: " + zOptBoolean);
                            z = true;
                        }
                        if (z) {
                            e().execute(new RunnableC0019b(jSONObjectOptJSONObject));
                        }
                        return;
                    }
                    str = "isNeedUpload, but task is out of date";
                }
                p.c("UploadLogTaskManager", str);
            } catch (Exception e) {
                p.h("UploadLogTaskManager", "isNeedUpload error: ", e);
            }
            if (z) {
                return;
            }
            e().execute(new RunnableC0019b(jSONObjectOptJSONObject));
        }
    }

    public void c() {
        try {
            if (!TextUtils.isEmpty(this.g) && v.e(this.h) >= System.currentTimeMillis()) {
                if (!this.e && !this.f) {
                    if (this.f748b.get()) {
                        p.c("UploadLogTaskManager", "checkUploadLogTask, but task already scheduled");
                        return;
                    } else {
                        e().execute(new c());
                        return;
                    }
                }
                p.c("UploadLogTaskManager", "checkUploadLogTask, but task is running or finished");
                return;
            }
            p.c("UploadLogTaskManager", "checkUploadLogTask, but task is empty or out of date");
        } catch (Exception e) {
            p.h("UploadLogTaskManager", "checkUploadLogTask error: ", e);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final void d() throws Throwable {
        String strA;
        String str;
        if (a.b.a.a.a.c.a.m0()) {
            String strG = g();
            if (TextUtils.isEmpty(strG)) {
                str = "doUpload, but uploadDirPath is empty";
            } else {
                File file = new File(strG);
                if (file.exists()) {
                    this.e = true;
                    try {
                        File[] fileArrListFiles = file.listFiles();
                        if (fileArrListFiles != null && fileArrListFiles.length != 0) {
                            for (File file2 : fileArrListFiles) {
                                ConcurrentHashMap<String, String> concurrentHashMap = y.f663c;
                                y yVar = y.b.f667a;
                                String strA2 = yVar.a("https://", yVar.d(), "/track/upload");
                                HashMap map = new HashMap();
                                map.put("a", "001");
                                if (r.q()) {
                                    strA = a.b.a.e.l0.c.g(e.b());
                                    if (TextUtils.isEmpty(strA)) {
                                        strA = a.b.a.e.l0.c.d(e.b());
                                    }
                                    if (TextUtils.isEmpty(strA)) {
                                        strA = a.b.a.e.l0.c.q(e.b());
                                    }
                                } else {
                                    strA = m.a(e.b());
                                }
                                map.put("d", strA);
                                map.put("t", this.g);
                                map.put("f", r.m() == 999 ? "999_" + file2.getName() : file2.getName());
                                String strG2 = d.g(strA2, map, file2);
                                JSONObject jSONObject = TextUtils.isEmpty(strG2) ? null : new JSONObject(strG2);
                                if (jSONObject == null || jSONObject.optInt("code") != 0) {
                                    int i2 = this.f749c + 1;
                                    this.f749c = i2;
                                    this.f750d = ((long) o.c("UploadLogTaskManager", i2)) * 1000;
                                    p.c("UploadLogTaskManager", "doUpload, but upload file failed, failed count: " + this.f749c + ", retry delay: " + this.f750d + "ms");
                                    j(this.f750d);
                                    return;
                                }
                                file2.delete();
                                this.f749c = 0;
                                this.f750d = 0L;
                            }
                            if (file.listFiles() == null || file.listFiles().length == 0) {
                                file.delete();
                                this.f = true;
                                String strI = u.i();
                                JSONObject jSONObject2 = new JSONObject();
                                if (TextUtils.isEmpty(strI)) {
                                    jSONObject2.put("utid", this.g);
                                    jSONObject2.put("dl", this.h);
                                } else {
                                    jSONObject2 = new JSONObject(strI);
                                }
                                jSONObject2.put("re", true);
                                u.m("u_l_t_f", jSONObject2.toString());
                                p.r(false);
                            }
                            this.e = false;
                            return;
                        }
                        return;
                    } catch (Exception e) {
                        p.h("UploadLogTaskManager", "doUpload error: ", e);
                    }
                } else {
                    str = "doUpload, but uploadDir not exist";
                }
            }
        } else {
            str = "network is not connected";
        }
        p.c("UploadLogTaskManager", str);
    }

    public final ScheduledExecutorService e() {
        ScheduledExecutorService scheduledExecutorService = this.f747a;
        if (scheduledExecutorService == null || scheduledExecutorService.isShutdown() || this.f747a.isTerminated()) {
            synchronized (b.class) {
                ScheduledExecutorService scheduledExecutorService2 = this.f747a;
                if (scheduledExecutorService2 == null || scheduledExecutorService2.isShutdown() || this.f747a.isTerminated()) {
                    ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, new a(this));
                    this.f747a = scheduledThreadPoolExecutor;
                    scheduledThreadPoolExecutor.setKeepAliveTime(30L, TimeUnit.SECONDS);
                    ((ScheduledThreadPoolExecutor) this.f747a).allowCoreThreadTimeOut(true);
                    if (Build.VERSION.SDK_INT >= 21) {
                        ((ScheduledThreadPoolExecutor) this.f747a).setRemoveOnCancelPolicy(true);
                    }
                }
            }
        }
        return this.f747a;
    }

    public final String g() {
        String strH = h();
        if (TextUtils.isEmpty(strH)) {
            return null;
        }
        StringBuilder sbC = a.a.a.a.a.c(strH);
        sbC.append(this.g);
        sbC.append("/");
        return sbC.toString();
    }

    public final String h() {
        File cacheDir = e.b().getCacheDir();
        if (cacheDir == null) {
            Log.e(p.a("UploadLogTaskManager"), "Fail to getCacheDir");
            return null;
        }
        return cacheDir.getPath() + "/u/";
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final void i() throws Throwable {
        String str;
        String str2;
        String strH = h();
        String strG = g();
        if (!TextUtils.isEmpty(strH) && !TextUtils.isEmpty(strG)) {
            File file = new File(strH);
            File file2 = new File(strG);
            if (file.exists()) {
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles != null) {
                    for (File file3 : fileArrListFiles) {
                        if (!this.g.equals(file3.getName())) {
                            a.b.a.d.d.a.a(file3);
                        }
                    }
                }
                if (file2.exists()) {
                    str = file2.listFiles().length > 0 ? "prepareUploadLog, but uploadDir has files" : "prepareUploadLog, but uploadRootDir or uploadDir path is empty";
                } else if (!file2.mkdirs()) {
                    p.c("UploadLogTaskManager", "prepareUploadLog, but create uploadDir failed");
                    return;
                }
            } else if (!file2.mkdirs()) {
                p.c("UploadLogTaskManager", "prepareUploadLog, but create uploadDir failed");
            }
            String strB = a.b.a.d.c.f.a.b(e.b());
            if (TextUtils.isEmpty(strB)) {
                str2 = "moveLogs, but debugLogPath is empty";
            } else {
                File file4 = new File(strB);
                if (file4.exists()) {
                    File[] fileArrListFiles2 = file4.listFiles();
                    if (fileArrListFiles2 != null && fileArrListFiles2.length != 0) {
                        String strG2 = g();
                        try {
                            for (File file5 : fileArrListFiles2) {
                                if (file5.isFile()) {
                                    String str3 = strG2 + file5.getName();
                                    if (file5.getName().endsWith(".log")) {
                                        a.b.a.d.d.a.d(file5.getPath(), file5.getPath() + ".0.zip");
                                        str3 = str3 + ".0.zip";
                                        file5 = new File(file5.getPath() + ".0.zip");
                                    }
                                    file5.renameTo(new File(str3));
                                }
                            }
                            return;
                        } catch (Exception e) {
                            p.h("UploadLogTaskManager", "moveLogs error: ", e);
                            return;
                        }
                    }
                    str2 = "moveLogs, but debugLogDir is empty";
                } else {
                    str2 = "moveLogs, but debugLogDir not exist";
                }
            }
            p.c("UploadLogTaskManager", str2);
            return;
        }
        p.c("UploadLogTaskManager", str);
    }

    public final void j(long j) {
        if (this.f748b.compareAndSet(false, true)) {
            e().schedule(new Runnable() { // from class: a.b.a.g.a
                /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
                @Override // java.lang.Runnable
                public final void run() {
                    b bVar = this.f746a;
                    try {
                        bVar.d();
                    } finally {
                        bVar.f748b.set(false);
                    }
                }
            }, j, TimeUnit.MILLISECONDS);
        }
    }
}
