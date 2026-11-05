package com.mobiletools.systemhelper.control;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import b1.c;
import b1.g;
import e1.d;
import f1.p;
import g1.h;
import g1.l;
import g1.m;
import g1.n;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetSignalInfoReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static volatile g f2189b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static b1.b f2190c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Handler f2191d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    n f2192a = new n();

    class a extends b1.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f2193a;

        a(Context context) {
            this.f2193a = context;
        }

        @Override // b1.b
        public void d(c cVar) {
            String str;
            String str2;
            if (cVar == null || cVar.v() == 167 || cVar.v() == 67 || cVar.v() == 62) {
                h.b("GetSignalInfoReceiver", "定位失败！");
            } else {
                h.a("GetSignalInfoReceiver", "定位成功！");
                GetSignalInfoReceiver.this.f2192a.f2687a = l.e();
                GetSignalInfoReceiver.this.f2192a.D = String.valueOf(cVar.x());
                GetSignalInfoReceiver.this.f2192a.E = String.valueOf(cVar.u());
                GetSignalInfoReceiver.this.f2192a.F = String.valueOf(cVar.e());
                GetSignalInfoReceiver.this.f2192a.L = String.valueOf(cVar.E());
                String strK = cVar.k();
                String strD = cVar.D();
                String strH = cVar.h();
                String strM = cVar.m();
                GetSignalInfoReceiver.this.f2192a.G = "" + strK + strD + strH + strM;
                int iJ = f1.a.j(0);
                if (iJ > 0 && (str2 = GetSignalInfoReceiver.this.f2192a.D) != null && str2 != "") {
                    h.a("GetSignalInfoReceiver", "signalInfo.longitude: " + GetSignalInfoReceiver.this.f2192a.D);
                    h.a("GetSignalInfoReceiver", "Receiver subIdSlot1: " + iJ);
                    new d().e(this.f2193a, 0, GetSignalInfoReceiver.this.f2192a);
                    JSONObject jSONObjectA = GetSignalInfoReceiver.this.f2192a.a();
                    h.a("GetSignalInfoReceiver", "siginfo: " + jSONObjectA);
                    if (jSONObjectA != null) {
                        try {
                            n nVar = GetSignalInfoReceiver.this.f2192a;
                            if (nVar.f2692f != null && GetSignalInfoReceiver.f(nVar).booleanValue()) {
                                m mVarB = m.b(this.f2193a, m.f2683c, m.f2682b.intValue());
                                mVarB.c(jSONObjectA.toString());
                                mVarB.close();
                            }
                        } catch (Exception e2) {
                            h.b("GetSignalInfoReceiver", "inSigChinaNetwork");
                            e2.printStackTrace();
                        }
                    }
                }
                int iJ2 = f1.a.j(1);
                if (iJ2 > 0 && (str = GetSignalInfoReceiver.this.f2192a.D) != null && str != "") {
                    h.a("GetSignalInfoReceiver", "signalInfo.longitude: " + GetSignalInfoReceiver.this.f2192a.D);
                    h.a("GetSignalInfoReceiver", "Receiver subIdSlot2: " + iJ2);
                    new d().e(this.f2193a, 1, GetSignalInfoReceiver.this.f2192a);
                    JSONObject jSONObjectA2 = GetSignalInfoReceiver.this.f2192a.a();
                    h.a("GetSignalInfoReceiver", "siginfo: " + jSONObjectA2);
                    if (jSONObjectA2 != null) {
                        try {
                            n nVar2 = GetSignalInfoReceiver.this.f2192a;
                            if (nVar2.f2692f != null && GetSignalInfoReceiver.f(nVar2).booleanValue()) {
                                m mVarB2 = m.b(this.f2193a, m.f2683c, m.f2682b.intValue());
                                mVarB2.c(jSONObjectA2.toString());
                                mVarB2.close();
                            }
                        } catch (Exception e3) {
                            h.b("GetSignalInfoReceiver", "inSigChinaNetwork");
                            e3.printStackTrace();
                        }
                    }
                }
                h.a("GetSignalInfoReceiver", "经纬度信息signalInfo.longitude： " + GetSignalInfoReceiver.this.f2192a.D + ", " + GetSignalInfoReceiver.this.f2192a.E + ", " + GetSignalInfoReceiver.this.f2192a.F);
            }
            GetSignalInfoReceiver.h();
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h.a("GetSignalInfoReceiver", "locate timeout, force stopLocation");
            GetSignalInfoReceiver.h();
        }
    }

    private boolean b(Context context) {
        boolean z2 = false;
        Boolean boolValueOf = Boolean.valueOf(Settings.Global.getInt(context.getContentResolver(), "device_provisioned", 0) == 1);
        h.a("GetSignalInfoReceiver", "isBootWizardCompleted: " + boolValueOf);
        if (boolValueOf.booleanValue()) {
            z2 = Settings.Secure.getInt(context.getContentResolver(), "operator_service_improve_plan", 0) == 1;
            h.a("GetSignalInfoReceiver", "checkUserPermission:" + z2);
        }
        return z2;
    }

    private boolean c(Context context) {
        Boolean boolValueOf = Boolean.valueOf(Settings.Global.getInt(context.getContentResolver(), "device_provisioned", 0) == 1);
        h.a("GetSignalInfoReceiver", "isBootWizardCompleted: " + boolValueOf);
        return boolValueOf.booleanValue();
    }

    private static synchronized boolean d(Context context) {
        if (f2189b != null) {
            return true;
        }
        try {
            f2189b = new g(context.getApplicationContext());
            return true;
        } catch (Exception e2) {
            h.b("GetSignalInfoReceiver", "create LocationClient failed: " + e2);
            return false;
        }
    }

    public static Boolean f(n nVar) {
        h.a("GetSignalInfoReceiver", "siginfo.mncCH: " + nVar.f2692f);
        String str = nVar.f2692f;
        if (str == null || str.isEmpty()) {
            return Boolean.FALSE;
        }
        return nVar.f2692f.startsWith("460") ? Boolean.TRUE : Boolean.FALSE;
    }

    private static void g() {
        if (f2191d == null) {
            f2191d = new Handler(Looper.getMainLooper());
        }
        f2191d.removeCallbacksAndMessages(null);
        f2191d.postDelayed(new b(), 60000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void h() {
        Handler handler = f2191d;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            f2191d = null;
        }
        if (f2189b == null) {
            return;
        }
        if (f2189b.e0()) {
            f2189b.v0();
            h.a("GetSignalInfoReceiver", "mLocationClient stop!");
        }
        if (f2190c != null) {
            h.a("GetSignalInfoReceiver", "unRegisterLocationListener!!!!");
            f2189b.y0(f2190c);
            f2190c = null;
        }
    }

    public void e(b1.b bVar) {
        g.s0(true);
        if (f2189b == null || bVar == null) {
            h.a("GetSignalInfoReceiver", "客户端或监听器为空，取消定位");
            return;
        }
        if (f2189b != null) {
            f2189b.o0(bVar);
            b1.h hVar = new b1.h();
            hVar.k("bd09ll");
            hVar.n(b1.h.b.Hight_Accuracy);
            hVar.p(true);
            hVar.q(0);
            hVar.m(true);
            hVar.l(true);
            f2189b.t0(hVar);
            f2189b.u0();
            h.a("GetSignalInfoReceiver", "mLocationClient start!");
            g();
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String strO = l.o("ro.product.manufacturer");
        Context applicationContext = context.getApplicationContext();
        g.s0(true);
        f1.a.p(applicationContext);
        h.a("GetSignalInfoReceiver", "enter GetSignalInfoReceiver --->");
        try {
            if (d(applicationContext)) {
                g1.a.c(applicationContext, l.m(applicationContext));
                if (strO != null && !strO.isEmpty()) {
                    g1.d.f2649e = strO.equalsIgnoreCase("vivo") ? b(applicationContext) : c(applicationContext);
                }
                if (!g1.d.f2649e) {
                    h();
                    return;
                }
                if (f2190c != null && f2189b != null) {
                    f2189b.y0(f2190c);
                    f2190c = null;
                }
                a aVar = new a(applicationContext);
                f2190c = aVar;
                e(aVar);
                p.f();
                p.e(applicationContext);
            }
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }
}
