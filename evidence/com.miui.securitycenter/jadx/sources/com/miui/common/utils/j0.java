package com.miui.common.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.os.PowerManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.miui.networkassistant.config.Constants;
import com.miui.networkassistant.utils.TelephonyUtil;
import com.miui.permcenter.compact.SystemPropertiesCompat;
import com.miui.securitycenter.Application;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import miui.os.Build;
import miui.util.MiuiMultiDisplayTypeInfo;
import miuix.core.util.SystemProperties;

/* JADX INFO: loaded from: classes2.dex */
public abstract class j0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f15064a = gk.l.v();

    public static boolean A() {
        return ar.a.K() || ar.a.I() || z();
    }

    public static boolean B() {
        return h0.A();
    }

    public static boolean C() {
        return f2.a("ro.miui.google.csp", false);
    }

    public static boolean D() {
        return Build.DEVICE.equals("lhasa");
    }

    public static boolean E(Configuration configuration) {
        return k.b(configuration);
    }

    public static boolean F(Context context) {
        return ((PowerManager) context.getSystemService("power")).isScreenOn();
    }

    public static boolean G() {
        try {
            Class<?> cls = Class.forName("miui.os.DeviceFeature");
            Object objInvoke = cls.getMethod("isSupportRearSmartAssistant", null).invoke(cls, null);
            if (objInvoke instanceof Boolean) {
                return ((Boolean) objInvoke).booleanValue();
            }
            return true;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e10) {
            e10.printStackTrace();
            return true;
        }
    }

    public static boolean H(Context context) {
        String string = Settings.Global.getString(context.getContentResolver(), "miui_new_version");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("curVersion : ");
        String str = android.os.Build.VERSION.INCREMENTAL;
        sb2.append(str);
        sb2.append(", newVersion : ");
        sb2.append(string);
        Log.d("SecurityCenterDebug", sb2.toString());
        if (!TextUtils.isEmpty(string) && !str.equals(string)) {
            if (!string.startsWith(str + ".")) {
                String strM = pj.n.m();
                pj.n.K(string);
                if (TextUtils.isEmpty(strM) || strM.equals(string)) {
                    return true;
                }
                gk.s.c("MIUI_UPDATE");
                return true;
            }
        }
        return false;
    }

    public static boolean I() {
        return android.os.Build.VERSION.SDK_INT < 28 || Settings.Global.getInt(Application.B().getContentResolver(), "device_provisioning_mobile_data", 0) == 1 || Settings.Global.getInt(Application.B().getContentResolver(), Constants.System.DEVICE_PROVISIONED, 0) == 1;
    }

    public static boolean J() {
        return ar.a.J() || y() || z();
    }

    public static boolean K(Context context) {
        return dl.c.a.d("miui.telephony.TelephonyManager").c("getDefault", null, new Object[0]).l().b("isVoiceCapable", null, new Object[0]).a();
    }

    public static boolean L() {
        return SystemProperties.getInt("persist.sys.muiltdisplay_type", 0) == 2;
    }

    public static boolean M() {
        return SystemPropertiesCompat.getBoolean("ro.radio.noril", false);
    }

    public static int a(Context context, float f10) {
        return h7.a.a(context, f10);
    }

    public static String b(Context context) {
        return dl.c.a.d("miui.telephony.TelephonyManager").c("getDefault", null, new Object[0]).l().b("getSubscriberIdForSlot", new Class[]{Integer.TYPE}, Integer.valueOf(m())).m();
    }

    public static String c(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        return string == null ? "" : string;
    }

    public static String d(Context context) {
        String string = SystemPropertiesCompat.getString("ro.product.marketname", "");
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        String strG = g(context.getApplicationContext());
        if (!TextUtils.isEmpty(strG)) {
            return strG;
        }
        String str = Build.MODEL;
        return !TextUtils.isEmpty(str) ? str : Build.DEVICE;
    }

    public static int e() {
        int iN = (int) (((b0.n() / 1024) / 1024) / 1024);
        if (iN <= 4) {
            return 1;
        }
        return iN <= 6 ? 2 : 4;
    }

    public static String f(Context context) {
        if (f15064a) {
            return "";
        }
        String strM = null;
        try {
            strM = dl.c.a.d("miui.telephony.TelephonyManager").c("getDefault", null, new Object[0]).l().b("getDeviceId", null, new Object[0]).m();
        } catch (Exception unused) {
        }
        return strM == null ? "" : strM;
    }

    public static String g(Context context) {
        try {
            Class<?> cls = Class.forName("android.provider.MiuiSettings$System");
            return (String) cls.getMethod("getDeviceName", Context.class).invoke(cls, context);
        } catch (Exception e10) {
            Log.e("DeviceUtils", "Get Phone name error:", e10);
            return null;
        }
    }

    public static String h() {
        return h0.d();
    }

    public static final String i() {
        return com.miui.common.a.b();
    }

    public static String j(Context context) {
        String strB = b(context);
        return TextUtils.isEmpty(strB) ? "OTHER" : k(context, strB);
    }

    private static String k(Context context, String str) {
        if (str.startsWith("46001")) {
            return TelephonyUtil.UNICOM;
        }
        if (str.startsWith("46003") || str.startsWith("460003")) {
            return TelephonyUtil.TELECOM;
        }
        return (str.startsWith("46000") || str.startsWith("46002") || str.startsWith("46007")) ? TelephonyUtil.CMCC : "OTHER";
    }

    public static String l(Context context) {
        String strB = b(context);
        if (TextUtils.isEmpty(strB)) {
            return "OTHER";
        }
        String strK = k(context, strB);
        return "OTHER".equals(strK) ? strB.substring(0, 8) : strK;
    }

    public static int m() {
        int i10 = dl.c.a.d("miui.telephony.SubscriptionManager").c("getDefault", null, new Object[0]).l().b("getDefaultDataSlotId", null, new Object[0]).i();
        if (i10 < 0 || i10 > 1) {
            return 0;
        }
        return i10;
    }

    public static String n() {
        return SystemProperties.get("ro.com.miui.rsa");
    }

    public static int o() {
        return i0.a();
    }

    public static String p() {
        return SystemProperties.get("ro.product.name");
    }

    public static int q(Context context) {
        return h0.g(context);
    }

    public static String r() {
        String property = System.getProperty("http.agent");
        return TextUtils.isEmpty(property) ? "" : property;
    }

    private static boolean s(Context context, int i10) {
        String strM = dl.c.a.d("miui.telephony.TelephonyManager").c("getDefault", null, new Object[0]).l().b("getSimOperatorForSlot", new Class[]{Integer.TYPE}, Integer.valueOf(i10)).m();
        return TextUtils.isEmpty(strM) || strM.startsWith("460");
    }

    public static boolean t(Context context) {
        return s(context, m());
    }

    public static boolean u(List list) {
        String str = android.os.Build.DEVICE;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (TextUtils.equals(str, (String) it.next())) {
                return true;
            }
        }
        return false;
    }

    public static boolean v(String... strArr) {
        String str = android.os.Build.DEVICE;
        for (String str2 : strArr) {
            if (TextUtils.equals(str, str2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean w() {
        return h0.p();
    }

    public static boolean x() {
        try {
            Object objInvoke = MiuiMultiDisplayTypeInfo.class.getMethod("isIndependentRearDevice", null).invoke(MiuiMultiDisplayTypeInfo.class, null);
            if (objInvoke instanceof Boolean) {
                return ((Boolean) objInvoke).booleanValue();
            }
            return false;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e10) {
            e10.printStackTrace();
            return false;
        }
    }

    public static boolean y() {
        return i0.c();
    }

    public static boolean z() {
        return ar.a.L();
    }
}
