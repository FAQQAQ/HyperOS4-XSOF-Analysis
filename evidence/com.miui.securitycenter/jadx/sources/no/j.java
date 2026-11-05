package no;

import aj.c0;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.common.primitives.UnsignedBytes;
import com.miui.luckymoney.model.message.Impl.QQMessage;
import com.xiaomi.trustservice.IMiTrustService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kp.l;
import org.json.JSONException;
import org.json.JSONObject;
import wo.w;

/* JADX INFO: loaded from: classes4.dex */
public class j {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f39307c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private IMiTrustService f39308d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f39309e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Intent f39310f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List f39305a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List f39306b = new ArrayList();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Map f39311g = new HashMap();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final ServiceConnection f39312h = new a();

    class a implements ServiceConnection {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d() {
            Log.w("MiTrustManager", "binderDied!");
            j.this.f39309e = false;
            j.this.f39308d = null;
            c0.e().a(new Runnable() { // from class: no.i
                @Override // java.lang.Runnable
                public final void run() {
                    this.f39304a.f();
                }
            });
        }

        private void e(boolean z10, int i10) {
            synchronized (j.class) {
                try {
                    if (z10) {
                        Iterator it = j.this.f39305a.iterator();
                        while (it.hasNext()) {
                            j.this.w((co.a) it.next());
                        }
                    } else {
                        Iterator it2 = j.this.f39305a.iterator();
                        while (it2.hasNext()) {
                            ((co.a) it2.next()).c(null, i10);
                        }
                    }
                    j.this.f39305a.clear();
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void f() {
            j.this.n();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void g() {
            e(true, 0);
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i("MiTrustManager", "onServiceConnected: " + componentName);
            j.this.f39309e = true;
            j.this.f39308d = IMiTrustService.Stub.asInterface(iBinder);
            c0.e().a(new Runnable() { // from class: no.g
                @Override // java.lang.Runnable
                public final void run() {
                    this.f39302a.g();
                }
            });
            try {
                iBinder.linkToDeath(new IBinder.DeathRecipient() { // from class: no.h
                    @Override // android.os.IBinder.DeathRecipient
                    public final void binderDied() {
                        this.f39303a.d();
                    }
                }, 0);
            } catch (RemoteException e10) {
                Log.e("MiTrustManager", "onServiceConnected: " + e10);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            j.this.f39309e = false;
            j.this.f39308d = null;
            Log.i("MiTrustManager", "onServiceDisconnected: " + componentName);
        }
    }

    public j(Context context) {
        this.f39307c = context;
    }

    private static boolean A(Context context, Intent intent) {
        List<ResolveInfo> listQueryIntentServices;
        return (intent == null || (listQueryIntentServices = context.getPackageManager().queryIntentServices(intent, 4)) == null || listQueryIntentServices.isEmpty()) ? false : true;
    }

    private boolean B() {
        return A(this.f39307c, s());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ w C(co.a aVar, byte[] bArr, go.a aVar2, String str) {
        v(aVar, str, bArr, (go.i) aVar2);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ w D(co.a aVar, byte[] bArr, go.a aVar2, String str) {
        v(aVar, str, bArr, (go.i) aVar2);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ w E(co.a aVar, byte[] bArr, go.a aVar2, String str) {
        v(aVar, str, bArr, (go.i) aVar2);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ w F(co.a aVar, byte[] bArr, go.a aVar2, String str) {
        v(aVar, str, bArr, (go.i) aVar2);
        return null;
    }

    private void l(co.a aVar) {
        Log.i("MiTrustManager", "addSuspendingTask: " + aVar);
        synchronized (j.class) {
            this.f39305a.add(aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        if (this.f39306b.isEmpty()) {
            return;
        }
        Log.w("MiTrustManager", "endHandlingTasks");
        synchronized (j.class) {
            try {
                Iterator it = this.f39306b.iterator();
                while (it.hasNext()) {
                    ((co.a) it.next()).c(null, 1004);
                }
                this.f39306b.clear();
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    private String p(String str) {
        PackageManager packageManager = this.f39307c.getPackageManager();
        JSONObject jSONObject = new JSONObject();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 64);
            jSONObject.put("package_name", str);
            jSONObject.put("version_code", packageInfo.versionCode);
            jSONObject.put("certificate_SHA256", oo.c.a(this.f39307c, str));
            return jSONObject.toString();
        } catch (Exception e10) {
            Log.e("MiTrustManager", "getAppStatus: " + e10);
            return "";
        }
    }

    private byte[] r() {
        return new byte[]{-1, UnsignedBytes.MAX_POWER_OF_TWO};
    }

    private Intent s() {
        if (this.f39310f == null) {
            Intent intent = new Intent("com.xiaomi.trustservice.IMiTrustService");
            this.f39310f = intent;
            intent.setPackage("com.xiaomi.trustservice");
        }
        return this.f39310f;
    }

    /* JADX DEBUG: Method merged with bridge method: G(Lco/a;)V */
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: u, reason: merged with bridge method [inline-methods] */
    public void G(final co.a aVar) {
        if (aVar.b()) {
            synchronized (j.class) {
                this.f39306b.remove(aVar);
            }
            return;
        }
        try {
            final go.a aVarA = aVar.a();
            if (!(aVarA instanceof go.i)) {
                synchronized (j.class) {
                    aVar.c(null, 1001);
                    this.f39306b.remove(aVar);
                }
                return;
            }
            if (aVarA instanceof go.c) {
                final byte[] bArrG = ho.b.f31788a.f().g();
                ho.b.e(new l() { // from class: no.c
                    @Override // kp.l
                    public final Object invoke(Object obj) {
                        return this.f39286a.C(aVar, bArrG, aVarA, (String) obj);
                    }
                });
                return;
            }
            if (aVarA instanceof go.e) {
                int[] iArrH = ((go.e) aVarA).h();
                final byte[] bArrG2 = ho.b.f31788a.f().g();
                ho.b.h(iArrH, new l() { // from class: no.d
                    @Override // kp.l
                    public final Object invoke(Object obj) {
                        return this.f39290a.D(aVar, bArrG2, aVarA, (String) obj);
                    }
                });
            } else {
                if (aVarA instanceof go.d) {
                    int[] iArrI = ((go.d) aVarA).i();
                    int[] iArrH2 = ((go.d) aVarA).h();
                    final byte[] bArrG3 = ho.b.f31788a.f().g();
                    ho.b.f(iArrI, iArrH2, new l() { // from class: no.e
                        @Override // kp.l
                        public final Object invoke(Object obj) {
                            return this.f39294a.E(aVar, bArrG3, aVarA, (String) obj);
                        }
                    });
                    return;
                }
                if (!(aVarA instanceof go.f)) {
                    v(aVar, p(aVarA.d()), r(), (go.i) aVarA);
                } else {
                    final byte[] bArrG4 = ho.b.f31788a.f().g();
                    ho.b.k(this.f39307c, new l() { // from class: no.f
                        @Override // kp.l
                        public final Object invoke(Object obj) {
                            return this.f39298a.F(aVar, bArrG4, aVarA, (String) obj);
                        }
                    });
                }
            }
        } catch (Exception e10) {
            Log.e("MiTrustManager", "handleReqTask: " + e10);
        }
    }

    private void v(co.a aVar, String str, byte[] bArr, go.i iVar) {
        String strN8;
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            synchronized (j.class) {
                try {
                    IMiTrustService iMiTrustService = this.f39308d;
                    strN8 = (iMiTrustService == null || !iMiTrustService.asBinder().isBinderAlive()) ? null : this.f39308d.n8(str, bArr, iVar.e());
                } catch (Throwable th2) {
                    throw th2;
                }
            }
            oo.b.a("MiTrustManager", "get trustStatus success, cost time = " + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms, result: " + strN8);
            if (TextUtils.isEmpty(strN8)) {
                aVar.c(null, 1001);
            } else if (strN8.contains("error_code")) {
                x(aVar, strN8);
            } else {
                aVar.c(strN8, 0);
                if (y(aVar.f8917a)) {
                    o().put(new Pair(iVar.d(), Integer.valueOf(aVar.f8917a)), Long.valueOf(jCurrentTimeMillis));
                }
            }
            synchronized (j.class) {
                this.f39306b.remove(aVar);
            }
        } catch (Exception e10) {
            oo.b.b("MiTrustManager", "getTrustStatusSignatureLocked: " + e10);
            aVar.c(null, 1001);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w(final co.a aVar) {
        synchronized (j.class) {
            this.f39306b.add(aVar);
        }
        c0.e().a(new Runnable() { // from class: no.b
            @Override // java.lang.Runnable
            public final void run() {
                this.f39284a.G(aVar);
            }
        });
    }

    private void x(co.a aVar, String str) {
        int i10;
        try {
            i10 = new JSONObject(str).getInt("error_code");
        } catch (JSONException e10) {
            Log.e("MiTrustManager", "handleServiceError: ", e10);
            i10 = 1001;
        }
        if (i10 != 1001) {
            i10 += QQMessage.TYPE_DISCUSS_GROUP;
        }
        aVar.c(null, i10);
    }

    public void H() {
        if (this.f39309e) {
            this.f39309e = false;
            this.f39308d = null;
            try {
                this.f39307c.unbindService(this.f39312h);
            } catch (RuntimeException e10) {
                Log.e("MiTrustManager", "unbindService: " + e10);
            }
            c0.e().a(new Runnable() { // from class: no.a
                @Override // java.lang.Runnable
                public final void run() {
                    this.f39283a.n();
                }
            });
        }
    }

    public void m() {
        if (this.f39309e) {
            Log.i("MiTrustManager", "Already bound.");
            return;
        }
        try {
            Log.i("MiTrustManager", "bindMiTrustService!");
            Intent intent = new Intent("com.xiaomi.trustservice.IMiTrustService");
            intent.setPackage("com.xiaomi.trustservice");
            this.f39309e = this.f39307c.bindService(intent, this.f39312h, 1);
        } catch (Exception e10) {
            Log.e("MiTrustManager", "bindMiTrustService: " + e10);
            this.f39309e = false;
            this.f39308d = null;
        }
        if (this.f39309e) {
            return;
        }
        synchronized (j.class) {
            try {
                Iterator it = this.f39305a.iterator();
                while (it.hasNext()) {
                    ((co.a) it.next()).c(null, AnalyticsListener.EVENT_AUDIO_SINK_ERROR);
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    public Map o() {
        return this.f39311g;
    }

    public long q(Pair pair) {
        if (this.f39311g.isEmpty()) {
            return 0L;
        }
        return ((Long) this.f39311g.get(pair)).longValue();
    }

    public void t(co.a aVar) {
        if (aVar == null) {
            return;
        }
        if (!B()) {
            aVar.c(null, 1013);
        }
        if (this.f39308d == null) {
            l(aVar);
        } else {
            w(aVar);
        }
    }

    public boolean y(int i10) {
        return i10 == 13 || i10 == 14 || i10 == 15 || i10 == 16;
    }

    public boolean z(Pair pair) {
        return !this.f39311g.isEmpty() && this.f39311g.containsKey(pair);
    }
}
