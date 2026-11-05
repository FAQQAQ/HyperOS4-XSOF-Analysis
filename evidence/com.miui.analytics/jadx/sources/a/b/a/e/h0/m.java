package a.b.a.e.h0;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import com.miui.analytics.BuildConfig;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class m extends a.b.a.e.f {
    public static final List<String> g;

    static {
        ArrayList arrayList = new ArrayList();
        g = arrayList;
        arrayList.add("com.sohu.inputmethod.sogou.xiaomi");
        arrayList.add("com.baidu.input_mi");
        arrayList.add("com.miui.securitycore");
        arrayList.add("com.xiaomi.finddevice");
        arrayList.add("com.android.phone");
        arrayList.add("com.android.bluetooth");
        arrayList.add("com.android.systemui");
        arrayList.add("com.miui.home");
        arrayList.add("com.mi.android.globallauncher");
        arrayList.add("com.miui.tsmclient");
        arrayList.add("com.miui.hybrid");
        arrayList.add("com.xiaomi.payment");
        arrayList.add("com.miui.virtualsim");
        arrayList.add("com.miui.yellowpage");
        arrayList.add("com.android.quicksearchbox");
        arrayList.add("com.xiaomi.miplay");
        arrayList.add("com.xiaomi.xmsf");
        arrayList.add("com.xiaomi.midrop");
        arrayList.add("com.xiaomi.account");
        arrayList.add("com.android.midrive");
        arrayList.add("com.miui.cloudbackup");
        arrayList.add("com.miui.cloudservice");
        arrayList.add("com.xiaomi.simactivate.service");
        arrayList.add("com.miui.personalassistant");
        arrayList.add("com.miui.newhome");
        arrayList.add(BuildConfig.APPLICATION_ID);
        arrayList.add("com.miui.personalassistant");
        arrayList.add("com.xiaomi.aiasst.service");
        arrayList.add("com.mi.android.globalminusscreen");
        arrayList.add("com.milink.service");
        arrayList.add("com.miui.packageinstaller");
        arrayList.add("com.miui.mishare.connectivity");
        arrayList.add("com.xiaomi.mirror");
        arrayList.add("miui.systemui.plugin");
        arrayList.add("com.mi.globalminusscreen");
        arrayList.add("com.miui.guardprovider");
        arrayList.add("com.xiaomi.mi_connect_service");
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public m() {
        JSONObject jSONObject;
        this.f309a = "001";
        this.f310b = a.b.a.e.e.f264d;
        this.f312d = 0;
        this.f311c = "onetrack_installed_app";
        try {
            JSONObject jSONObjectA = a();
            JSONObject jSONObjectD = d();
            jSONObject = new JSONObject();
            jSONObject.put("H", jSONObjectA);
            jSONObject.put("B", jSONObjectD);
        } catch (JSONException e) {
            a.b.a.e.l0.p.h("InstalledAppEvent", "create installed_app event error", e);
            jSONObject = null;
        }
        this.e = jSONObject;
        this.f = System.currentTimeMillis();
    }

    public static List<String> e() {
        ArrayList arrayList = new ArrayList();
        try {
            try {
                List<InputMethodInfo> enabledInputMethodList = ((InputMethodManager) a.b.a.e.e.b().getSystemService("input_method")).getEnabledInputMethodList();
                int size = enabledInputMethodList.size();
                for (int i = 0; i < size; i++) {
                    arrayList.add(enabledInputMethodList.get(i).getPackageName());
                }
            } catch (Throwable th) {
                throw th;
            }
        } catch (NullPointerException unused) {
            a.b.a.e.l0.p.g("InstalledAppEvent", "null pointer while getting ime list");
        } catch (Throwable th2) {
            a.b.a.e.l0.p.h("InstalledAppEvent", "getImeList error", th2);
        }
        return arrayList;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public final JSONObject d() throws JSONException {
        String str;
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        Context contextB = a.b.a.e.e.b();
        PackageManager packageManager = contextB.getPackageManager();
        List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        HashSet hashSet = new HashSet();
        Iterator<ResolveInfo> it = listQueryIntentActivities.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().activityInfo.packageName);
        }
        List<String> listE = e();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(16384);
        Iterator<PackageInfo> it2 = installedPackages.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            PackageInfo next = it2.next();
            if (next != null && (str = next.packageName) != null) {
                boolean z = (next.applicationInfo.flags & 1) != 0;
                if (!hashSet.contains(str) && !g.contains(next.packageName)) {
                    if (((ArrayList) listE).contains(next.packageName) || !z) {
                    }
                }
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("pkg", next.packageName);
                String str2 = next.versionName;
                if (str2 == null) {
                    str2 = "";
                }
                jSONObject2.put("vn", str2);
                jSONObject2.put("vc", next.versionCode);
                String strS = a.b.a.a.a.c.a.S(packageManager, next.packageName);
                jSONObject2.put("installer", strS != null ? strS : "");
                jSONObject2.put("fit", next.firstInstallTime);
                jSONObject2.put("lut", next.lastUpdateTime);
                jSONObject2.put("status", a.b.a.e.l0.r.d(contextB, next.packageName));
                jSONObject2.put("ot_enable", a.b.a.e.l0.r.c(contextB, next.packageName));
                jSONArray.put(jSONObject2);
            }
        }
        listQueryIntentActivities.clear();
        hashSet.clear();
        ((ArrayList) listE).clear();
        installedPackages.clear();
        a.b.a.e.l0.p.c("InstalledAppEvent", "getInstalledApps size: " + jSONArray.length());
        jSONObject.put("apps", jSONArray);
        jSONObject.put("radio", a.b.a.e.l0.c.z());
        jSONObject.put("radio2", a.b.a.e.l0.c.A(1));
        if (a.b.a.e.l0.r.q()) {
            jSONObject.put("custom_version", a.b.a.e.l0.c.e());
        }
        return jSONObject;
    }
}
