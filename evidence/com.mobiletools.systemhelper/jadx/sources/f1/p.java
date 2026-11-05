package f1;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Map<String, String> f2553a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static c f2554b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static List<g1.b> f2555c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static e1.f.a f2556d = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static e1.c.a f2557e = new b();

    class a implements e1.f.a {
        a() {
        }

        @Override // e1.f.a
        public void a(Context context, e1.b bVar, List<g1.b> list) {
            List unused = p.f2555c = list;
            if (p.f2555c != null) {
                JSONArray jSONArray = new JSONArray();
                for (g1.b bVar2 : p.f2555c) {
                    try {
                        if (!bVar2.a().getString("pkgName").startsWith("com.android")) {
                            jSONArray.put(bVar2.a());
                        }
                    } catch (JSONException e2) {
                        throw new RuntimeException(e2);
                    }
                }
                if (bVar != null) {
                    bVar.f2444a0 = jSONArray;
                    g1.h.a("DeviceManager", "appInfolength:" + jSONArray.length());
                }
            }
            ((g1.d.f2647c && g1.d.f2649e) ? new e1.c(context, bVar, p.f2557e) : new e1.g(context, bVar)).start();
        }
    }

    class b implements e1.c.a {
        b() {
        }

        @Override // e1.c.a
        public void a(Context context, e1.b bVar, String str, String str2) {
            if (bVar != null) {
                bVar.S = str;
                bVar.T = str2;
            }
            String str3 = bVar.T;
            if (str3 == null || str3.equals("")) {
                bVar.T = "ex01";
            }
            new e1.g(context, bVar).start();
        }
    }

    public interface c {
        boolean a(Context context);

        void b(Context context, e1.b bVar);
    }

    public static synchronized e1.b d(Context context, String str) {
        Thread gVar;
        g1.h.a("DeviceManager", "enter getCurDeviceInfo--RegType:" + str);
        e1.b bVar = new e1.b();
        bVar.W = str;
        k(context, bVar);
        if (!str.trim().equals("T")) {
            i(context, bVar);
            j(context, bVar);
        } else if (!i(context, bVar) && !j(context, bVar)) {
            g1.h.a("DeviceManager", "SIM not changed，not post！");
            return bVar;
        }
        l(bVar);
        if (!str.trim().equals("A")) {
            if (str.trim().equals("R")) {
                g1.h.a("DeviceManager", "regtype R");
                g1.m mVarB = g1.m.b(context, g1.m.f2683c, g1.m.f2682b.intValue());
                JSONArray jSONArrayD = mVarB.d();
                g1.h.a("DeviceManager", "signalJSONArray.length() : " + jSONArrayD.length());
                if (jSONArrayD.length() > 0) {
                    bVar.f2446b0 = jSONArrayD;
                    mVarB.a();
                    mVarB.close();
                    g1.h.a("DeviceManager", "signalJSONArray>>>>>:" + jSONArrayD.toString());
                    if (g1.d.f2647c && g1.d.f2649e) {
                        gVar = new e1.c(context, bVar, f2557e);
                    } else {
                        g1.h.a("DeviceManager", "GlobalSetting.isUserPermissionOpen is false,type R-appinfo is not post");
                        gVar = new e1.g(context, bVar);
                    }
                } else {
                    g1.h.a("DeviceManager", "deviceinfo.siginfo 为空！ ");
                }
            } else if (str.trim().equals("B") || str.trim().equals("F") || str.trim().equals("S") || str.trim().equals("T")) {
                if (g1.d.f2647c && g1.d.f2649e) {
                    gVar = new e1.c(context, bVar, f2557e);
                } else {
                    g1.h.a("DeviceManager", "GlobalSetting.isUserPermissionOpen is false,B/F/S/Tdeviceinfo is not post");
                    gVar = new e1.g(context, bVar);
                }
            }
            return bVar;
        }
        if (g1.d.f2649e) {
            gVar = new e1.f(context, bVar, f2556d);
        } else {
            g1.h.a("DeviceManager", "GlobalSetting.isUserPermissionOpen is false,type A-appinfo is not post");
            gVar = new e1.g(context, bVar);
        }
        gVar.start();
        return bVar;
    }

    public static c e(Context context) {
        c aVar;
        f1.a.p(context);
        c cVar = f2554b;
        if (cVar != null) {
            return cVar;
        }
        Context context2 = g1.d.f2648d;
        StringBuilder sb = new StringBuilder();
        sb.append("model is ");
        String str = Build.MODEL;
        sb.append(str);
        g1.h.c(context2, sb.toString());
        String str2 = f2553a.get(str);
        if (str2 == null) {
            g1.h.b("DeviceManager", "getReader error!! manufacture==null! model=" + str);
            aVar = new f1.a();
        } else if (str2.equals("Xiaomi")) {
            aVar = new n();
        } else if (str2.equals("OPPO")) {
            aVar = new e();
        } else if (str2.equalsIgnoreCase("vivo")) {
            aVar = new l();
        } else if (str2.equalsIgnoreCase("samsung")) {
            aVar = new i();
        } else if (str2.equalsIgnoreCase("realme")) {
            aVar = new h();
        } else if (str2.equalsIgnoreCase("ZTE")) {
            aVar = new o();
        } else if (str2.equalsIgnoreCase("nubia")) {
            aVar = new d();
        } else if (str2.equalsIgnoreCase("OnePlus")) {
            aVar = new f();
        } else if (str2.equalsIgnoreCase("Liantong")) {
            aVar = new f1.b();
        } else if (str2.equalsIgnoreCase("TDTECH")) {
            aVar = new j();
        } else if (str2.equalsIgnoreCase("PTAC")) {
            aVar = new g();
        } else if (str2.equalsIgnoreCase("WIKO")) {
            aVar = new m();
        } else if (str2.equalsIgnoreCase("Tianyi")) {
            aVar = new k();
        } else {
            aVar = str2.equalsIgnoreCase("motorola") ? new f1.c() : new f1.a();
        }
        f2554b = aVar;
        return f2554b;
    }

    public static void f() {
        if (f2553a == null) {
            f2553a = new HashMap();
            String strO = g1.l.o("ro.product.manufacturer");
            if (strO == null || strO.isEmpty()) {
                return;
            }
            g1.h.a("DeviceManager", "manufacturer=" + strO);
            if (strO.trim().equalsIgnoreCase("vivo") || strO.trim().equalsIgnoreCase("samsung") || strO.trim().equalsIgnoreCase("realme") || strO.trim().equalsIgnoreCase("Xiaomi") || strO.trim().equalsIgnoreCase("OPPO") || strO.trim().equalsIgnoreCase("Liantong") || strO.trim().equalsIgnoreCase("OnePlus") || strO.trim().equalsIgnoreCase("TDTECH") || strO.trim().equalsIgnoreCase("nubia") || strO.trim().equalsIgnoreCase("PTAC") || strO.trim().equalsIgnoreCase("ZTE") || strO.trim().equalsIgnoreCase("WIKO") || strO.trim().equalsIgnoreCase("Tianyi") || strO.trim().equalsIgnoreCase("Lenovo") || strO.trim().equalsIgnoreCase("motorola")) {
                StringBuilder sb = new StringBuilder();
                sb.append("model: ");
                String str = Build.MODEL;
                sb.append(str);
                g1.h.a("DeviceManager", sb.toString());
                f2553a.put(str, strO);
            }
        }
    }

    public static boolean g(Context context) {
        c cVarE;
        if (context == null || (cVarE = e(context)) == null) {
            return false;
        }
        return cVarE.a(context);
    }

    public static Boolean h(Context context) {
        if (context == null) {
            return Boolean.FALSE;
        }
        f();
        return Boolean.valueOf(g(context));
    }

    private static synchronized boolean i(Context context, e1.b bVar) {
        String strN = g1.l.n(context, "iccid1", null);
        if (strN == null || strN.isEmpty()) {
            String str = bVar.f2453h;
            if (str != null && !str.isEmpty()) {
                g1.h.a("DeviceManager", "SIM1 Changed,old iccid:" + strN + ", new iccid:" + bVar.f2453h);
                g1.l.H(context, "iccid1", bVar.f2453h);
                return true;
            }
        } else if (!strN.equals(bVar.f2453h)) {
            g1.h.a("DeviceManager", "SIM1 Changed,old iccid:" + strN + ", new iccid:" + bVar.f2453h);
            g1.l.H(context, "iccid1", bVar.f2453h);
            return true;
        }
        return false;
    }

    private static synchronized boolean j(Context context, e1.b bVar) {
        String strN = g1.l.n(context, "iccid2", null);
        if (strN == null || strN.isEmpty()) {
            String str = bVar.f2466u;
            if (str != null && !str.isEmpty()) {
                g1.h.a("DeviceManager", "SIM2 Changed,old iccid:" + strN + ", new iccid:" + bVar.f2466u);
                g1.l.H(context, "iccid2", bVar.f2466u);
                return true;
            }
        } else if (!strN.equals(bVar.f2466u)) {
            g1.h.a("DeviceManager", "SIM2 Changed,old iccid:" + strN + ", new iccid:" + bVar.f2466u);
            g1.l.H(context, "iccid2", bVar.f2466u);
            return true;
        }
        return false;
    }

    private static synchronized void k(Context context, e1.b bVar) {
        String str;
        try {
            bVar.Y = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        }
        f();
        c cVarE = e(context);
        if (cVarE == null) {
            g1.h.b("DeviceManager", "manuf==null,model=" + Build.MODEL);
            return;
        }
        cVarE.b(context, bVar);
        if (y.a.a(context, "android.permission.ACCESS_NETWORK_STATE") == 0) {
            if (g1.l.t(context).booleanValue()) {
                str = !g1.l.w(context).booleanValue() ? "Mobile" : "WiFi";
            } else {
                str = "Disconnected";
            }
            bVar.R = str;
        }
        bVar.Z = g1.l.e();
        g1.h.a("DeviceManager", "getDeviceInfo:" + bVar.b());
    }

    private static synchronized void l(e1.b bVar) {
        String str;
        String str2;
        String str3;
        String str4 = bVar.f2452g;
        if (str4 != null && bVar.f2465t != null && !str4.isEmpty() && !bVar.f2465t.isEmpty() && bVar.f2452g.compareTo(bVar.f2465t) > 0) {
            e1.b bVar2 = new e1.b();
            bVar2.f2452g = bVar.f2452g;
            bVar2.f2453h = bVar.f2453h;
            bVar2.f2454i = bVar.f2454i;
            bVar2.f2456k = bVar.f2456k;
            bVar2.f2457l = bVar.f2457l;
            bVar2.f2458m = bVar.f2458m;
            bVar2.f2460o = bVar.f2460o;
            bVar2.f2461p = bVar.f2461p;
            bVar2.f2459n = bVar.f2459n;
            bVar2.f2462q = bVar.f2462q;
            bVar2.f2464s = bVar.f2464s;
            bVar2.f2463r = bVar.f2463r;
            String str5 = bVar.f2465t;
            bVar2.f2465t = str5;
            String str6 = bVar.f2466u;
            bVar2.f2466u = str6;
            String str7 = bVar.f2467v;
            bVar2.f2467v = str7;
            String str8 = bVar.f2469x;
            bVar2.f2469x = str8;
            String str9 = bVar.f2470y;
            bVar2.f2470y = str9;
            String str10 = bVar.f2471z;
            bVar2.f2471z = str10;
            String str11 = bVar.B;
            bVar2.B = str11;
            String str12 = bVar.C;
            bVar2.C = str12;
            String str13 = bVar.A;
            bVar2.A = str13;
            String str14 = bVar.D;
            bVar2.D = str14;
            String str15 = bVar.F;
            bVar2.F = str15;
            String str16 = bVar.E;
            bVar2.E = str16;
            bVar.f2452g = str5;
            bVar.f2453h = str6;
            bVar.f2454i = str7;
            bVar.f2456k = str8;
            bVar.f2457l = str9;
            bVar.f2458m = str10;
            bVar.f2460o = str11;
            bVar.f2461p = str12;
            bVar.f2459n = str13;
            bVar.f2462q = str14;
            bVar.f2464s = str15;
            bVar.f2463r = str16;
            bVar.f2465t = bVar2.f2452g;
            bVar.f2466u = bVar2.f2453h;
            bVar.f2467v = bVar2.f2454i;
            bVar.f2469x = bVar2.f2456k;
            bVar.f2470y = bVar2.f2457l;
            bVar.f2471z = bVar2.f2458m;
            bVar.B = bVar2.f2460o;
            bVar.C = bVar2.f2461p;
            bVar.A = bVar2.f2459n;
            bVar.D = bVar2.f2462q;
            bVar.F = bVar2.f2464s;
            bVar.E = bVar2.f2463r;
            String str17 = bVar.G;
            if (str17 != null) {
                if (!str17.equals("1")) {
                    str3 = bVar.G.equals("2") ? "1" : "2";
                }
                bVar.G = str3;
            }
            String str18 = bVar.L;
            if (str18 != null && str18.length() == 2) {
                char cCharAt = bVar.L.charAt(0);
                bVar.L = "" + bVar.L.charAt(1) + cCharAt;
                StringBuilder sb = new StringBuilder();
                sb.append("排序后更新eSIM状态: ");
                sb.append(bVar.L);
                g1.h.a("EsimStatus", sb.toString());
            }
        }
        try {
            String str19 = bVar.f2454i;
            if (str19 != null && bVar.f2453h != null && !str19.equals("") && bVar.f2454i.length() >= 5 && bVar.f2453h.length() >= 6 && !g1.l.v(bVar.f2454i).booleanValue()) {
                if (bVar.f2454i.length() < 11 || bVar.f2453h.length() < 11) {
                    if (bVar.f2453h.length() >= 11) {
                        bVar.f2453h = bVar.f2453h.substring(0, 10) + "**********";
                    } else if (bVar.f2454i.length() >= 11) {
                        str2 = bVar.f2454i.substring(0, 10) + "*****";
                    }
                    bVar.f2460o = g1.i.a(bVar.f2460o);
                    bVar.f2459n = g1.i.a(bVar.f2459n);
                } else {
                    bVar.f2453h = bVar.f2453h.substring(0, 10) + "**********";
                    str2 = bVar.f2454i.substring(0, 10) + "*****";
                }
                bVar.f2454i = str2;
                bVar.f2460o = g1.i.a(bVar.f2460o);
                bVar.f2459n = g1.i.a(bVar.f2459n);
            }
            String str20 = bVar.f2467v;
            if (str20 != null && bVar.f2466u != null && !str20.equals("") && bVar.f2467v.length() >= 5 && bVar.f2466u.length() >= 6 && !g1.l.v(bVar.f2467v).booleanValue()) {
                if (bVar.f2467v.length() < 11 || bVar.f2466u.length() < 11) {
                    if (bVar.f2466u.length() >= 11) {
                        bVar.f2466u = bVar.f2466u.substring(0, 10) + "**********";
                    } else if (bVar.f2467v.length() >= 11) {
                        str = bVar.f2467v.substring(0, 10) + "*****";
                    }
                    bVar.B = g1.i.a(bVar.B);
                    bVar.A = g1.i.a(bVar.A);
                } else {
                    bVar.f2466u = bVar.f2466u.substring(0, 10) + "**********";
                    str = bVar.f2467v.substring(0, 10) + "*****";
                }
                bVar.f2467v = str;
                bVar.B = g1.i.a(bVar.B);
                bVar.A = g1.i.a(bVar.A);
            }
        } catch (Exception e2) {
            g1.h.b("DeviceManager", "exception === sortAndAnonymousSubscriber");
            e2.printStackTrace();
        }
    }
}
