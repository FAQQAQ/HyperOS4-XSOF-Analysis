package tm;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.util.Log;
import dalvik.system.DexClassLoader;
import rm.e;

/* JADX INFO: loaded from: classes3.dex */
public class b implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f43401a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ClassLoader f43402b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f43403c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f43404d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f43405e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f43406f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private volatile boolean f43407g;

    public b(Context context, String str, String str2) {
        this.f43404d = "";
        this.f43401a = sm.b.a(context);
        this.f43405e = str;
        this.f43406f = str2;
        PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 1);
        this.f43403c = packageArchiveInfo.versionCode;
        this.f43404d = packageArchiveInfo.versionName;
    }

    private void c() {
        try {
            this.f43402b.loadClass("com.miui.analytics.Analytics").getDeclaredMethod("initialize", Context.class, Integer.TYPE, String.class).invoke(null, this.f43401a, Integer.valueOf(this.f43403c), this.f43404d);
        } catch (Throwable th2) {
            Log.w(sm.a.a("DexAnalytics"), "initAnalytics exception", th2);
        }
    }

    @Override // tm.a
    public boolean a(String str) {
        try {
            init();
            return ((Boolean) this.f43402b.loadClass("com.miui.analytics.Analytics").getDeclaredMethod("isPolicyReady", String.class, String.class).invoke(null, this.f43401a.getPackageName(), str)).booleanValue();
        } catch (Throwable th2) {
            Log.w(sm.a.a("DexAnalytics"), "isPolicyReady exception", th2);
            return false;
        }
    }

    @Override // tm.a
    public String b(String str) {
        try {
            init();
            return (String) this.f43402b.loadClass("com.miui.analytics.Analytics").getDeclaredMethod("getClientExtra", String.class, String.class).invoke(null, this.f43401a.getPackageName(), str);
        } catch (Throwable th2) {
            Log.w(sm.a.a("DexAnalytics"), "getClientExtra exception", th2);
            return "";
        }
    }

    @Override // tm.a
    public void deleteAllEvents(String str) {
        try {
            init();
            this.f43402b.loadClass("com.miui.analytics.Analytics").getDeclaredMethod("deleteAllEvents", String.class).invoke(null, str);
        } catch (Throwable th2) {
            Log.w(sm.a.a("DexAnalytics"), "deleteAllEvents exception", th2);
        }
    }

    @Override // tm.a
    public e getVersion() {
        return new e(this.f43404d);
    }

    @Override // tm.a
    public void init() {
        try {
            if (this.f43407g) {
                return;
            }
            this.f43402b = new DexClassLoader(this.f43405e, this.f43401a.getDir("dex", 0).getAbsolutePath(), this.f43406f, ClassLoader.getSystemClassLoader());
            c();
            this.f43407g = true;
            sm.a.b("DexAnalytics", "initialized");
        } catch (Exception e10) {
            Log.e(sm.a.a("DexAnalytics"), "init e", e10);
        }
    }

    @Override // tm.a
    public void setDebugOn(boolean z10) {
        try {
            init();
            this.f43402b.loadClass("com.miui.analytics.Analytics").getDeclaredMethod("setDebugOn", Boolean.TYPE).invoke(null, Boolean.valueOf(z10));
        } catch (Throwable th2) {
            Log.w(sm.a.a("DexAnalytics"), "setDebugOn exception", th2);
        }
    }

    @Override // tm.a
    public void setDefaultPolicy(String str, String str2) {
        try {
            init();
            this.f43402b.loadClass("com.miui.analytics.Analytics").getDeclaredMethod("setDefaultPolicy", String.class, String.class).invoke(null, str, str2);
        } catch (Throwable th2) {
            Log.w(sm.a.a("DexAnalytics"), "setDefaultPolicy exception", th2);
        }
    }

    @Override // tm.a
    public void trackEvent(String str) {
        try {
            init();
            this.f43402b.loadClass("com.miui.analytics.Analytics").getDeclaredMethod("trackEvent", String.class).invoke(null, str);
        } catch (Throwable th2) {
            Log.w(sm.a.a("DexAnalytics"), "trackEvent exception", th2);
        }
    }

    @Override // tm.a
    public void trackEvents(String[] strArr) {
        try {
            init();
            this.f43402b.loadClass("com.miui.analytics.Analytics").getDeclaredMethod("trackEvents", String[].class).invoke(null, strArr);
        } catch (Throwable th2) {
            Log.w(sm.a.a("DexAnalytics"), "trackEvents exception", th2);
        }
    }
}
