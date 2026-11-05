package rm;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.miui.warningcenter.caraccident.utils.NetworkUtils;
import com.xiaomi.analytics.Analytics;
import com.xiaomi.onetrack.b.l;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import miuix.appcompat.app.floatingactivity.multiapp.MethodCodeHelper;
import org.json.JSONObject;
import sm.g;
import sm.h;
import sm.j;
import sm.k;
import sm.m;
import sm.n;
import sm.o;

/* JADX INFO: loaded from: classes3.dex */
class d {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final long f42096i = n.f42746a;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static volatile d f42097j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f42098a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f42101d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f42102e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private c f42103f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f42099b = "";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f42100c = "";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Runnable f42104g = new a();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Runnable f42105h = new b();

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a aVar;
            String str;
            String str2;
            int i10;
            a aVar2 = this;
            String str3 = "v";
            String str4 = "UpdateManager";
            e eVar = rm.a.f42059a;
            e eVarL = rm.c.F(d.this.f42098a).L();
            long jCurrentTimeMillis = System.currentTimeMillis();
            int i11 = 0;
            while (true) {
                int i12 = i11 + 1;
                if (i11 >= 2) {
                    return;
                }
                try {
                    String strB = k.b();
                    String strF = k.f();
                    String strD = k.d(d.this.f42098a);
                    String strC = k.c();
                    int iC = j.c(d.this.f42098a);
                    String strP = d.this.p();
                    String packageName = d.this.f42098a.getPackageName();
                    String strH = k.h();
                    i10 = i12;
                    try {
                        String strG = k.g();
                        try {
                            String strA = o.a(packageName + Settings.Secure.getString(d.this.f42098a.getContentResolver(), "android_id"));
                            StringBuilder sb2 = new StringBuilder();
                            String str5 = str3;
                            try {
                                sb2.append("i=");
                                sb2.append(strA);
                                sb2.append(", orig=");
                                sb2.append(strD);
                                sm.a.b(str4, sb2.toString());
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append(l.f27684g + eVar);
                                sb3.append("cv" + eVarL);
                                sb3.append("d" + strB);
                                sb3.append("f" + strF);
                                if (!h.d()) {
                                    sb3.append("i" + strA);
                                }
                                sb3.append("m" + strC);
                                sb3.append("n" + iC);
                                sb3.append("nonce" + strP);
                                sb3.append("p" + packageName);
                                sb3.append("r" + strH);
                                sb3.append("ts" + jCurrentTimeMillis);
                                StringBuilder sb4 = new StringBuilder();
                                str = str5;
                                try {
                                    sb4.append(str);
                                    sb4.append(strG);
                                    sb3.append(sb4.toString());
                                    sb3.append("miui_sdkconfig_jafej!@#)(*e@!#");
                                    String strA2 = o.a(sb3.toString());
                                    String str6 = str4;
                                    try {
                                        StringBuilder sb5 = new StringBuilder(h.d() ? "https://sdkconfig.ad.intl.xiaomi.com/api/checkupdate/lastusefulversion2?" : "https://sdkconfig.ad.xiaomi.com/api/checkupdate/lastusefulversion2?");
                                        sb5.append("av=" + eVar);
                                        sb5.append("&cv=" + eVarL);
                                        sb5.append("&d=" + strB);
                                        sb5.append("&f=" + strF);
                                        if (!h.d()) {
                                            sb5.append("&i=" + strA);
                                        }
                                        sb5.append("&m=" + strC);
                                        sb5.append("&n=" + iC);
                                        sb5.append("&nonce=" + strP);
                                        sb5.append("&p=" + packageName);
                                        sb5.append("&r=" + strH);
                                        sb5.append("&ts=" + jCurrentTimeMillis);
                                        sb5.append("&v=" + strG);
                                        sb5.append("&sign=" + strA2);
                                        str2 = str6;
                                        try {
                                            sm.a.b(str2, sb5.toString());
                                            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(sb5.toString()).openConnection();
                                            httpURLConnection.setRequestMethod(NetworkUtils.REQUEST_METHOD_GET);
                                            httpURLConnection.setConnectTimeout(rm.a.f42061c);
                                            httpURLConnection.connect();
                                            String str7 = new String(g.b(httpURLConnection.getInputStream()));
                                            sm.a.b(str2, "result " + str7);
                                            JSONObject jSONObject = new JSONObject(str7);
                                            String strOptString = jSONObject.optString("url");
                                            try {
                                                int iOptInt = jSONObject.optInt("code", 0);
                                                str = str;
                                                try {
                                                    String strOptString2 = jSONObject.optString(str);
                                                    aVar = this;
                                                    try {
                                                        d.this.f42102e = jSONObject.optInt("force", 0);
                                                        if (!TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2)) {
                                                            e eVar2 = new e(strOptString2);
                                                            if (!h.a() && eVar2.f42110c != 0) {
                                                                return;
                                                            }
                                                            d.this.f42100c = jSONObject.optString("md5");
                                                            d.this.f42099b = strOptString;
                                                            m.a(d.this.f42105h);
                                                            return;
                                                        }
                                                        if (iOptInt != -8) {
                                                            return;
                                                        } else {
                                                            jCurrentTimeMillis = d.this.s(jSONObject.optString("failMsg"));
                                                        }
                                                    } catch (Exception e10) {
                                                        e = e10;
                                                        d.this.t(0L);
                                                        sm.a.d(str2, "exception ", e);
                                                    }
                                                } catch (Exception e11) {
                                                    e = e11;
                                                    aVar = this;
                                                }
                                            } catch (Exception e12) {
                                                e = e12;
                                                aVar = this;
                                                str = str;
                                                d.this.t(0L);
                                                sm.a.d(str2, "exception ", e);
                                                str4 = str2;
                                                aVar2 = aVar;
                                                str3 = str;
                                                i11 = i10;
                                            }
                                        } catch (Exception e13) {
                                            e = e13;
                                        }
                                    } catch (Exception e14) {
                                        e = e14;
                                        aVar = this;
                                        str = str;
                                        str2 = str6;
                                        d.this.t(0L);
                                        sm.a.d(str2, "exception ", e);
                                        str4 = str2;
                                        aVar2 = aVar;
                                        str3 = str;
                                        i11 = i10;
                                    }
                                } catch (Exception e15) {
                                    e = e15;
                                    aVar = this;
                                    str2 = str4;
                                    d.this.t(0L);
                                    sm.a.d(str2, "exception ", e);
                                    str4 = str2;
                                    aVar2 = aVar;
                                    str3 = str;
                                    i11 = i10;
                                }
                            } catch (Exception e16) {
                                e = e16;
                                aVar = this;
                                str2 = str4;
                                str = str5;
                            }
                        } catch (Exception e17) {
                            e = e17;
                            aVar = this;
                            str = str3;
                            str2 = str4;
                            d.this.t(0L);
                            sm.a.d(str2, "exception ", e);
                            str4 = str2;
                            aVar2 = aVar;
                            str3 = str;
                            i11 = i10;
                        }
                    } catch (Exception e18) {
                        e = e18;
                        aVar = aVar2;
                    }
                } catch (Exception e19) {
                    e = e19;
                    aVar = aVar2;
                    str = str3;
                    str2 = str4;
                    i10 = i12;
                }
                str4 = str2;
                aVar2 = aVar;
                str3 = str;
                i11 = i10;
            }
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(d.this.f42099b).openConnection();
                httpURLConnection.setRequestMethod(NetworkUtils.REQUEST_METHOD_GET);
                httpURLConnection.setConnectTimeout(rm.a.f42061c);
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() == 200) {
                    byte[] bArrB = g.b(httpURLConnection.getInputStream());
                    FileOutputStream fileOutputStream = null;
                    if (!TextUtils.isEmpty(d.this.f42100c)) {
                        if (!d.this.f42100c.equalsIgnoreCase(o.b(bArrB))) {
                            bArrB = null;
                        }
                    }
                    if (bArrB != null) {
                        Log.d(sm.a.a("UpdateManager"), "download apk success.");
                        File file = new File(d.this.f42101d + ".tmp");
                        try {
                            try {
                                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                                try {
                                    fileOutputStream2.write(bArrB);
                                    fileOutputStream2.flush();
                                    fileOutputStream2.close();
                                    if (sm.e.b(sm.b.b(d.this.f42098a, file))) {
                                        Log.d(sm.a.a("UpdateManager"), "verify signature success");
                                        file.renameTo(new File(d.this.f42101d));
                                        d.this.r();
                                    } else {
                                        Log.e(sm.a.a("UpdateManager"), "verify signature failed");
                                    }
                                } catch (Exception e10) {
                                    e = e10;
                                    fileOutputStream = fileOutputStream2;
                                    Log.e(sm.a.a("UpdateManager"), "mDownloader e", e);
                                } catch (Throwable th2) {
                                    th = th2;
                                    fileOutputStream = fileOutputStream2;
                                    g.a(fileOutputStream);
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                            }
                        } catch (Exception e11) {
                            e = e11;
                        }
                        g.a(fileOutputStream);
                    }
                }
            } catch (Exception e12) {
                Log.w(sm.a.a("UpdateManager"), "mDownloader exception", e12);
            }
        }
    }

    interface c {
        void a(String str, boolean z10);
    }

    private d(Context context) {
        this.f42098a = sm.b.a(context);
    }

    public static synchronized d n(Context context) {
        try {
            if (f42097j == null) {
                f42097j = new d(context);
            }
        } catch (Throwable th2) {
            throw th2;
        }
        return f42097j;
    }

    private synchronized long o() {
        return this.f42098a.getSharedPreferences("analytics_updater", 0).getLong("updateTime", 0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String p() {
        Random random = new Random(System.nanoTime());
        try {
            return o.a(this.f42098a.getPackageName() + MethodCodeHelper.IDENTITY_INFO_SEPARATOR + random.nextLong());
        } catch (Exception unused) {
            return o.a(random.nextLong() + "");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        c cVar = this.f42103f;
        if (cVar != null) {
            cVar.a(this.f42101d, this.f42102e == 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long s(String str) {
        try {
            return Long.parseLong(str.split("-")[1]);
        } catch (Exception unused) {
            return System.currentTimeMillis();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void t(long j10) {
        SharedPreferences.Editor editorEdit = this.f42098a.getSharedPreferences("analytics_updater", 0).edit();
        editorEdit.putLong("updateTime", j10);
        editorEdit.apply();
    }

    public void m(String str) {
        if (h.f(this.f42098a, "UpdateManager")) {
            return;
        }
        sm.a.b("UpdateManager", "checkUpdate ");
        this.f42101d = str;
        m.a(this.f42104g);
        t(System.currentTimeMillis());
    }

    public boolean q() {
        if (h.f(this.f42098a, "UpdateManager")) {
            return false;
        }
        if (Analytics.isUpdateEnable()) {
            return System.currentTimeMillis() - o() >= f42096i;
        }
        sm.a.b("UpdateManager", "Updating is disabled.");
        return false;
    }

    public void u(c cVar) {
        this.f42103f = cVar;
    }
}
