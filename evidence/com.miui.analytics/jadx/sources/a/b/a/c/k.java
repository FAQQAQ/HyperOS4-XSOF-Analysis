package a.b.a.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.Analytics;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class k {
    public static volatile k h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f34a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f37d;
    public int e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f35b = "";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f36c = "";
    public Runnable f = new a();
    public Runnable g = new b();

    public class a implements Runnable {
        public a() {
        }

        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
        @Override // java.lang.Runnable
        public void run() {
            n nVar = new n("1.9.7");
            n nVar2 = new n(Analytics.getVersionName());
            long jCurrentTimeMillis = System.currentTimeMillis();
            try {
                String strB = a.b.a.c.v.n.b();
                String strL = a.b.a.c.v.n.l();
                String strG = a.b.a.c.v.n.g(k.this.f34a);
                String strC = a.b.a.c.v.n.c();
                int iB = a.b.a.c.v.k.b(k.this.f34a);
                String strA = k.a(k.this);
                String packageName = k.this.f34a.getPackageName();
                String strM = a.b.a.c.v.n.m();
                String str = Build.VERSION.INCREMENTAL;
                StringBuilder sb = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                try {
                    sb2.append("av");
                    sb2.append(nVar);
                    sb.append(sb2.toString());
                    sb.append("cv" + nVar2);
                    sb.append("d" + strB);
                    sb.append("f" + strL);
                    if (!a.b.a.c.v.h.f()) {
                        sb.append("i" + strG);
                    }
                    sb.append("m" + strC);
                    sb.append("n" + iB);
                    sb.append("nonce" + strA);
                    sb.append("p" + packageName);
                    sb.append("r" + strM);
                    sb.append("ts" + jCurrentTimeMillis);
                    sb.append("v" + str);
                    sb.append("miui_sdkconfig_jafej!@#)(*e@!#");
                    String strB2 = a.b.a.c.v.c.b(sb.toString());
                    StringBuilder sb3 = new StringBuilder(a.b.a.c.v.h.f() ? "https://sdkconfig.intl.xiaomi.com/api/checkupdate/lastusefulversion2?" : "https://sdkconfig.xiaomi.com/api/checkupdate/lastusefulversion2?");
                    sb3.append("av=" + nVar);
                    sb3.append("&cv=" + nVar2);
                    sb3.append("&d=" + strB);
                    sb3.append("&f=" + strL);
                    if (!a.b.a.c.v.h.f()) {
                        sb3.append("&i=" + strG);
                    }
                    sb3.append("&m=" + strC);
                    sb3.append("&n=" + iB);
                    sb3.append("&nonce=" + strA);
                    sb3.append("&p=" + packageName);
                    sb3.append("&r=" + strM);
                    sb3.append("&ts=" + jCurrentTimeMillis);
                    sb3.append("&v=" + str);
                    sb3.append("&sign=" + strB2);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(sb3.toString()).openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(30000);
                    httpURLConnection.connect();
                    String str2 = new String(a.b.a.a.a.c.a.k0(httpURLConnection.getInputStream()));
                    Log.d("UpdateManagerForApi", "result " + str2);
                    JSONObject jSONObject = new JSONObject(str2);
                    String strOptString = jSONObject.optString("url");
                    jSONObject.optInt("code", 0);
                    String strOptString2 = jSONObject.optString("v");
                    try {
                        k.this.e = jSONObject.optInt("force", 0);
                        long jOptInt = jSONObject.optInt("delay", 0);
                        long j = 2592000000L;
                        if (jOptInt == 0) {
                            j = 43200000;
                        } else {
                            long j2 = jOptInt * 1000;
                            if (j2 < 43200000) {
                                j = 43200000;
                            } else if (j2 <= 2592000000L) {
                                j = j2;
                            }
                        }
                        long jCurrentTimeMillis2 = System.currentTimeMillis();
                        k kVar = k.this;
                        long j3 = jCurrentTimeMillis2 + j;
                        synchronized (kVar) {
                            SharedPreferences.Editor editorEdit = kVar.f34a.getSharedPreferences("analytics_updater", 0).edit();
                            editorEdit.putLong("updateTimeForThird", j3);
                            editorEdit.apply();
                        }
                        Log.d("UpdateManagerForApi", "saved, now=" + jCurrentTimeMillis2 + ", nextCheckTimeDelay=" + j);
                        if (TextUtils.isEmpty(strOptString) || TextUtils.isEmpty(strOptString2)) {
                            return;
                        }
                        n nVar3 = new n(strOptString2);
                        if (a.b.a.c.v.h.e() || nVar3.f49c == 0) {
                            k.this.f36c = jSONObject.optString("md5");
                            k kVar2 = k.this;
                            kVar2.f35b = strOptString;
                            a.b.a.a.a.c.a.x(kVar2.g);
                        }
                    } catch (Exception e) {
                        e = e;
                        Log.e("UpdateManagerForApi", "exception ", e);
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        }
    }

    public class b implements Runnable {
        public b() {
        }

        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
        @Override // java.lang.Runnable
        public void run() throws Throwable {
            FileOutputStream fileOutputStream;
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(k.this.f35b).openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(30000);
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() != 200) {
                    return;
                }
                byte[] bArrK0 = a.b.a.a.a.c.a.k0(httpURLConnection.getInputStream());
                FileOutputStream fileOutputStream2 = null;
                if (!TextUtils.isEmpty(k.this.f36c)) {
                    if (!k.this.f36c.equalsIgnoreCase(a.b.a.c.v.c.c(bArrK0))) {
                        bArrK0 = null;
                    }
                }
                if (bArrK0 == null) {
                    return;
                }
                Log.d("UpdateManagerForApi", "download apk success.");
                File file = new File(k.this.f37d + ".temp");
                try {
                    try {
                        fileOutputStream = new FileOutputStream(file);
                        try {
                            fileOutputStream.write(bArrK0);
                            fileOutputStream.flush();
                            fileOutputStream.close();
                            if (a.b.a.c.v.c.g(a.b.a.a.a.c.a.d0(k.this.f34a, file))) {
                                Log.d("UpdateManagerForApi", "verify signature success");
                                file.renameTo(new File(k.this.f37d));
                                if (k.this.e != 0) {
                                    Process.killProcess(Process.myPid());
                                }
                            } else {
                                Log.e("UpdateManagerForApi", "verify signature failed");
                            }
                        } catch (Exception e) {
                            e = e;
                            fileOutputStream2 = fileOutputStream;
                            Log.e("UpdateManagerForApi", "mDownloader e", e);
                        } catch (Throwable th) {
                            th = th;
                            a.b.a.a.a.c.a.j(fileOutputStream);
                            throw th;
                        }
                    } catch (Exception e2) {
                        e = e2;
                    }
                    a.b.a.a.a.c.a.j(fileOutputStream2);
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = fileOutputStream2;
                }
            } catch (Exception e3) {
                Log.w("UpdateManagerForApi", "mDownloader exception", e3);
            }
        }
    }

    public k(Context context) {
        this.f34a = a.b.a.a.a.c.a.I(context);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static String a(k kVar) {
        Objects.requireNonNull(kVar);
        Random random = new Random(System.nanoTime());
        try {
            return a.b.a.c.v.c.b(kVar.f34a.getPackageName() + ":" + random.nextLong());
        } catch (Exception unused) {
            return a.b.a.c.v.c.b(random.nextLong() + "");
        }
    }

    public static synchronized k b(Context context) {
        if (h == null) {
            h = new k(context);
        }
        return h;
    }
}
