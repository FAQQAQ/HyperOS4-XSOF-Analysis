package a.b.a.e.l0;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Process;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f564a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f565b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f566c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String f567d = null;
    public static String e = null;
    public static String f = null;
    public static String g = null;
    public static Boolean h = null;
    public static String i = null;
    public static String j = null;
    public static volatile boolean k = false;
    public static String l;
    public static String m;

    public class a implements Comparator<String> {
        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
        @Override // java.util.Comparator
        public int compare(String str, String str2) {
            return str.compareToIgnoreCase(str2);
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static Signature[] f568a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final Signature f569b = new Signature("3082033b30820223a003020102020900a07a328482f70d2a300d06092a864886f70d01010505003035310b30090603550406130255533113301106035504080c0a43616c69666f726e69613111300f06035504070c084d6f756e7461696e301e170d3133303430313033303831325a170d3430303831373033303831325a3035310b30090603550406130255533113301106035504080c0a43616c69666f726e69613111300f06035504070c084d6f756e7461696e30820120300d06092a864886f70d01010105000382010d00308201080282010100ac678c9234a0226edbeb75a43e8e18f632d8c8a094c087fffbbb0b5e4429d845e36bffbe2d7098e320855258aa777368c18c538f968063d5d61663dc946ab03acbb31d00a27d452e12e6d42865e27d6d0ad2d8b12cf3b3096a7ec66a21db2a6a697857fd4d29fb4cdf294b3371d7601f2e3f190c0164efa543897026c719b334808e4f612fe3a3da589115fc30f9ca89862feefdf31a9164ecb295dcf7767e673be2192dda64f88189fd6e6ebd62e572c7997c2385a0ea9292ec799dee8f87596fc73aa123fb6f577d09ac0c123179c3bdbc978c2fe6194eb9fa4ab3658bfe8b44040bb13fe7809409e622189379fbc63966ab36521793547b01673ecb5f15cf020103a350304e301d0603551d0e0416041447203684e562385ada79108c4c94c5055037592f301f0603551d2304183016801447203684e562385ada79108c4c94c5055037592f300c0603551d13040530030101ff300d06092a864886f70d010105050003820101008d530fe05c6fe694c7559ddb5dd2c556528dd3cad4f7580f439f9a90a4681d37ce246b9a6681bdd5a5437f0b8bba903e39bac309fc0e9ee5553681612e723e9ec4f6abab6b643b33013f09246a9b5db7703b96f838fb27b00612f5fcd431bea32f68350ae51a4a1d012c520c401db7cccc15a7b19c4310b0c3bfc625ce5744744d0b9eeb02b0a4e7d51ed59849ce580b9f7c3062c84b9a0b13cc211e1c916c289820266a610801e3316c915649804571b147beadbf88d3b517ee04121d40630853f2f2a506bb788620de9648faeacff568e5033a666316bc2046526674ed3de25ceefdc4ad3628f1a230fd41bf9ca9f6a078173850dba555768fe1c191483ad9");

        /* JADX DEBUG: Failed to insert an additional move for type inference into block B:15:0x0035 */
        /* JADX DEBUG: Failed to insert an additional move for type inference into block B:23:0x0003 */
        /* JADX DEBUG: Failed to insert an additional move for type inference into block B:9:0x002e */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v0 */
        /* JADX WARN: Type inference failed for: r1v1 */
        /* JADX WARN: Type inference failed for: r1v10 */
        /* JADX WARN: Type inference failed for: r1v11 */
        /* JADX WARN: Type inference failed for: r1v2, types: [java.io.Closeable] */
        /* JADX WARN: Type inference failed for: r1v3 */
        /* JADX WARN: Type inference failed for: r1v4 */
        /* JADX WARN: Type inference failed for: r1v7 */
        /* JADX WARN: Type inference failed for: r2v0 */
        /* JADX WARN: Type inference failed for: r2v1, types: [java.io.Closeable] */
        /* JADX WARN: Type inference failed for: r2v3, types: [java.io.BufferedReader, java.io.Closeable] */
        public static String a(String str) throws Throwable {
            ?? bufferedReader;
            String string = "";
            ?? r1 = 0;
            StringBuilder sb = null;
            ?? r2 = 0;
            try {
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(str)), 512);
                    try {
                        String line = bufferedReader.readLine();
                        if (line != null) {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("");
                            sb2.append(line);
                            string = sb2.toString();
                            sb = sb2;
                        }
                        a.b.a.a.a.c.a.g(bufferedReader);
                        r1 = sb;
                    } catch (Exception e) {
                        e = e;
                        r2 = bufferedReader;
                        p.h("DeviceUtil", "catEntry exception", e);
                        a.b.a.a.a.c.a.g(r2);
                        r1 = r2;
                    } catch (Throwable th) {
                        th = th;
                        a.b.a.a.a.c.a.g(bufferedReader);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = r1;
                }
            } catch (Exception e2) {
                e = e2;
            }
            return string;
        }

        public static String b() {
            try {
                Class<?> cls = Class.forName("mitv.common.ConfigurationManager");
                int i = Integer.parseInt(String.valueOf(cls.getMethod("getProductCategory", new Class[0]).invoke(cls.getMethod("getInstance", new Class[0]).invoke(cls, new Object[0]), new Object[0])));
                Class<?> cls2 = Class.forName("mitv.tv.TvContext");
                if (i == Integer.parseInt(String.valueOf((char[]) c(cls2, "PRODUCT_CATEGORY_MITV")))) {
                    return "MI_TV";
                }
                if (i == Integer.parseInt(String.valueOf((char[]) c(cls2, "PRODUCT_CATEGORY_MIBOX")))) {
                    return "MI_BOX";
                }
                if (i == Integer.parseInt(String.valueOf((char[]) c(cls2, "PRODUCT_CATEGORY_MITVBOX")))) {
                    return "MI_TVBOX";
                }
                return i == Integer.parseInt(String.valueOf((char[]) c(cls2, "PRODUCT_CATEGORY_MIPROJECTOR"))) ? "MI_PROJECTOR" : "";
            } catch (Throwable th) {
                StringBuilder sbC = a.a.a.a.a.c("getMiTvProductCategory exception: ");
                sbC.append(th.getMessage());
                p.s("DeviceUtil", sbC.toString());
                return "";
            }
        }

        public static <T> T c(Class<?> cls, String str) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                declaredField.setAccessible(true);
                return (T) declaredField.get(null);
            } catch (Exception e) {
                p.h("DeviceUtil", "getStaticVariableValue exception", e);
                return null;
            }
        }
    }

    public static String A(int i2) {
        try {
            Class<?> cls = Class.forName("miui.telephony.TelephonyManager");
            return (String) cls.getDeclaredMethod("getNetworkOperatorForSlot", Integer.TYPE).invoke(cls.getDeclaredMethod("getDefault", new Class[0]).invoke(null, new Object[0]), Integer.valueOf(i2));
        } catch (Throwable th) {
            p.h(p.a("DeviceUtil"), "getSimType e", th);
            return "";
        }
    }

    public static long B(File file) {
        if (file != null && file.exists()) {
            try {
                StatFs statFs = new StatFs(file.getPath());
                return Build.VERSION.SDK_INT >= 26 ? statFs.getTotalBytes() : statFs.getBlockSizeLong() * statFs.getBlockCountLong();
            } catch (Exception e2) {
                a.a.a.a.a.g(e2, a.a.a.a.a.c("获取总空间失败："), "DeviceUtil");
            }
        }
        return 0L;
    }

    public static String C() {
        try {
            if (m == null) {
                m = v("ro.mi.xms.version.incremental");
                p.c("DeviceUtil", "cachedXmsVersion:" + m);
                if (m == null) {
                    m = "";
                }
            }
            String strV = v("persist.sys.xms.version");
            if (strV == null) {
                strV = "";
            }
            int iA = a(m, strV);
            return (iA <= 0 && iA < 0) ? strV : m;
        } catch (Exception e2) {
            p.d("DeviceUtil", "getXMSVersion e", e2);
            return "";
        }
    }

    public static Account D(Context context) {
        String string;
        if (r.o(context, "android.permission.GET_ACCOUNTS") || r.o(context, "android.permission.GET_ACCOUNTS_PRIVILEGED")) {
            try {
                Account[] accountsByType = AccountManager.get(context).getAccountsByType("com.xiaomi");
                if (accountsByType.length > 0) {
                    return accountsByType[0];
                }
                return null;
            } catch (SecurityException e2) {
                string = e2.toString();
            } catch (Exception e3) {
                string = e3.toString();
            }
        } else {
            string = "can't grant GET_ACCOUNTS permission";
        }
        p.g("DeviceUtil", string);
        return null;
    }

    public static boolean E(String str) {
        return (str == null || str.length() != 15 || str.matches("^0*$")) ? false : true;
    }

    public static boolean F(String str) {
        return str != null && str.length() >= 6 && str.length() <= 15 && !str.matches("^0*$");
    }

    public static boolean G(String str) {
        return (str == null || str.length() != 14 || str.matches("^0*$")) ? false : true;
    }

    public static boolean H() {
        if ("dsds".equals(v("persist.radio.multisim.config"))) {
            return true;
        }
        String str = Build.DEVICE;
        return "lcsh92_wet_jb9".equals(str) || "lcsh92_wet_tdd".equals(str) || "HM2013022".equals(str) || "HM2013023".equals(str) || "armani".equals(str) || "HM2014011".equals(str) || "HM2014012".equals(str);
    }

    public static boolean I() {
        if (Build.VERSION.SDK_INT >= 21) {
            return false;
        }
        String str = Build.DEVICE;
        String strV = v("persist.radio.modem");
        if ("HM2014812".equals(str) || "HM2014821".equals(str)) {
            return true;
        }
        return ("gucci".equals(str) && "ct".equals(v("persist.sys.modem"))) || "CDMA".equals(strV) || "HM1AC".equals(strV) || "LTE-X5-ALL".equals(strV) || "LTE-CT".equals(strV) || "MI 3C".equals(Build.MODEL);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static boolean J() {
        try {
            return ((Boolean) Class.forName("miui.os.Build").getField("IS_TABLET").get(null)).booleanValue();
        } catch (Exception e2) {
            StringBuilder sbC = a.a.a.a.a.c("get IS_TABLET from miui.os.Build exception: ");
            sbC.append(e2.getMessage());
            p.s("DeviceUtil", sbC.toString());
            try {
                Method method = h.f584b;
                if (method == null) {
                    return false;
                }
                try {
                    return ((Boolean) method.invoke(null, "is_pad", Boolean.FALSE)).booleanValue();
                } catch (Throwable unused) {
                    return false;
                }
            } catch (Exception e3) {
                StringBuilder sbC2 = a.a.a.a.a.c("get is_pad from FeatureParser exception: ");
                sbC2.append(e3.getMessage());
                p.s("DeviceUtil", sbC2.toString());
                return false;
            }
        }
    }

    @SuppressLint({"MissingPermission"})
    public static List<String> K(Context context) {
        try {
            if (r.w("DeviceUtil") && !k) {
                return null;
            }
            List<String> listK = k();
            if (listK == null || listK.isEmpty()) {
                listK = Build.VERSION.SDK_INT >= 21 ? i(context) : j();
            }
            if (listK != null && !listK.isEmpty()) {
                Collections.sort(listK);
                f564a = listK.get(0);
                if (listK.size() >= 2) {
                    f565b = listK.get(1);
                }
            }
            return listK;
        } catch (Exception e2) {
            a.a.a.a.a.h("can not get imei list ", e2, "DeviceUtil");
            return null;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX WARN: Code duplicated, block: B:30:0x0093  */
    @SuppressLint({"MissingPermission"})
    public static List<String> L(Context context) {
        Object objInvoke;
        String str;
        List<String> list;
        boolean z;
        try {
            Class<?> cls = Class.forName("miui.telephony.TelephonyManagerEx");
            Object objInvoke2 = cls.getMethod("getDefault", new Class[0]).invoke(null, new Object[0]);
            Method method = cls.getMethod("getMeidList", new Class[0]);
            if (method != null && (list = (List) method.invoke(objInvoke2, new Object[0])) != null && list.size() > 0) {
                Iterator<String> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    if (!G(it.next())) {
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    Collections.sort(list);
                    return list;
                }
            }
        } catch (Throwable th) {
            a.a.a.a.a.j(th, a.a.a.a.a.c("queryMeidList failed ex: "), "DeviceUtil");
        }
        try {
            Class<?> cls2 = Class.forName("android.telephony.TelephonyManager");
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (Build.VERSION.SDK_INT >= 26) {
                Method method2 = cls2.getMethod("getMeid", new Class[0]);
                if (method2 != null) {
                    objInvoke = method2.invoke(telephonyManager, new Object[0]);
                    str = (String) objInvoke;
                } else {
                    str = null;
                }
            } else {
                Method method3 = cls2.getMethod("getDeviceId", new Class[0]);
                if (method3 != null) {
                    objInvoke = method3.invoke(telephonyManager, new Object[0]);
                    str = (String) objInvoke;
                } else {
                    str = null;
                }
            }
            if (G(str)) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(str);
                return arrayList;
            }
        } catch (Exception e2) {
            a.a.a.a.a.f(e2, a.a.a.a.a.c("queryMeidList->getMeid failed ex: "), "DeviceUtil");
        }
        return null;
    }

    public static int a(String str, String str2) {
        if (str.isEmpty() && str2.isEmpty()) {
            return 0;
        }
        if (str.isEmpty()) {
            return -1;
        }
        if (str2.isEmpty()) {
            return 1;
        }
        char cCharAt = str.charAt(0);
        char cCharAt2 = str2.charAt(0);
        char upperCase = Character.toUpperCase(cCharAt);
        char upperCase2 = Character.toUpperCase(cCharAt2);
        if (upperCase > upperCase2) {
            return 1;
        }
        if (upperCase < upperCase2) {
            return -1;
        }
        try {
            String strSubstring = str.substring(1);
            String strSubstring2 = str2.substring(1);
            long j2 = Long.parseLong(strSubstring);
            long j3 = Long.parseLong(strSubstring2);
            if (j2 > j3) {
                return 1;
            }
            return j2 < j3 ? -1 : 0;
        } catch (NumberFormatException unused) {
            return str.toUpperCase().compareTo(str2.toUpperCase());
        }
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            byte[] bArrI0 = a.b.a.a.a.c.a.i0(str);
            byte[] bArrB = a.b.a.e.g0.a.b(bArrI0, a.b.a.e.g0.c.f429d);
            if (bArrB == null || bArrB.length == 0) {
                bArrB = a.b.a.e.g0.a.b(bArrI0, a.b.a.e.g0.c.d().getBytes());
            }
            if (bArrB != null && bArrB.length != 0) {
                return new String(bArrB);
            }
        } catch (Exception e2) {
            p.h("DeviceUtil", "decodeID e", e2);
        }
        return "";
    }

    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return a.b.a.a.a.c.a.c(a.b.a.e.g0.a.e(str.getBytes(), a.b.a.e.g0.c.f429d));
        } catch (Exception e2) {
            p.h("DeviceUtil", "encodeID e", e2);
            return "";
        }
    }

    public static String d(Context context) {
        if (!TextUtils.isEmpty(f567d)) {
            return f567d;
        }
        String string = Settings.System.getString(context.getContentResolver(), "android_id");
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        f567d = string;
        return string;
    }

    public static String e() {
        try {
            return a.b.a.a.a.c.a.G("ro.miui.business.version", "");
        } catch (Throwable th) {
            p.h("DeviceUtil", "getCustomVersion e", th);
            return "";
        }
    }

    public static String f() {
        if (!TextUtils.isEmpty(i)) {
            return i;
        }
        if (!J()) {
            return "Phone";
        }
        i = "Pad";
        return "Pad";
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX WARN: Code duplicated, block: B:36:0x0083  */
    /* JADX WARN: Code duplicated, block: B:38:0x0086 A[RETURN] */
    public static String g(Context context) {
        boolean z;
        String string;
        if (!TextUtils.isEmpty(e)) {
            return e;
        }
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 16384);
            z = true;
        } catch (PackageManager.NameNotFoundException unused) {
            z = false;
        }
        if (z) {
            d dVar = new d(null);
            try {
                try {
                    Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
                    intent.setPackage("com.google.android.gms");
                    if (context.bindService(intent, dVar, 1)) {
                        IBinder iBinderA = dVar.a();
                        if (iBinderA == null) {
                            string = "";
                        } else {
                            Parcel parcelObtain = Parcel.obtain();
                            Parcel parcelObtain2 = Parcel.obtain();
                            try {
                                parcelObtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                                iBinderA.transact(1, parcelObtain, parcelObtain2, 0);
                                parcelObtain2.readException();
                                string = parcelObtain2.readString();
                                parcelObtain2.recycle();
                                parcelObtain.recycle();
                            } catch (Throwable th) {
                                parcelObtain2.recycle();
                                parcelObtain.recycle();
                                throw th;
                            }
                        }
                        try {
                            context.unbindService(dVar);
                        } catch (Throwable unused2) {
                        }
                    } else {
                        try {
                            context.unbindService(dVar);
                        } catch (Throwable unused3) {
                        }
                    }
                } catch (Throwable th2) {
                    try {
                        context.unbindService(dVar);
                    } catch (Throwable unused4) {
                    }
                    throw th2;
                }
            } catch (Exception e2) {
                p.d("GAIDClient", "Query Google ADID failed ", e2);
            }
            if (!TextUtils.isEmpty(string)) {
                return "";
            }
            e = string;
            return string;
        }
        p.c("GAIDClient", "Google play service is not available");
        string = "";
        if (!TextUtils.isEmpty(string)) {
            return "";
        }
        e = string;
        return string;
    }

    public static String h(Context context) {
        if (!TextUtils.isEmpty(f)) {
            return f;
        }
        String strW = w(context);
        if (TextUtils.isEmpty(strW)) {
            return "";
        }
        String strD = a.b.a.e.g0.d.d(strW);
        f = strD;
        return strD;
    }

    public static List<String> i(Context context) {
        Method method;
        try {
            if (Build.VERSION.SDK_INT < 21 || (method = Class.forName("android.telephony.TelephonyManager").getMethod("getImei", Integer.TYPE)) == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            String str = (String) method.invoke(telephonyManager, 0);
            if (E(str)) {
                arrayList.add(str);
            }
            if (H()) {
                String str2 = (String) method.invoke(telephonyManager, 1);
                if (E(str2)) {
                    arrayList.add(str2);
                }
            }
            return arrayList;
        } catch (Exception e2) {
            a.a.a.a.a.f(e2, a.a.a.a.a.c("getImeiListAboveLollipop failed ex: "), "DeviceUtil");
            return null;
        }
    }

    /* JADX DEBUG: Incorrect args count in method signature: (Landroid/content/Context;)Ljava/util/List<Ljava/lang/String;>; */
    @SuppressLint({"MissingPermission"})
    public static List j() {
        try {
            ArrayList arrayList = new ArrayList();
            Class<?> cls = Class.forName("android.telephony.TelephonyManager");
            if (!H()) {
                String deviceId = ((TelephonyManager) cls.getMethod("getDefault", new Class[0]).invoke(null, new Object[0])).getDeviceId();
                if (E(deviceId)) {
                    arrayList.add(deviceId);
                }
                return arrayList;
            }
            Class<?> cls2 = Integer.TYPE;
            String deviceId2 = ((TelephonyManager) cls.getMethod("getDefault", cls2).invoke(null, 0)).getDeviceId();
            String deviceId3 = ((TelephonyManager) cls.getMethod("getDefault", cls2).invoke(null, 1)).getDeviceId();
            if (E(deviceId2)) {
                arrayList.add(deviceId2);
            }
            if (E(deviceId3)) {
                arrayList.add(deviceId3);
            }
            return arrayList;
        } catch (Exception e2) {
            a.a.a.a.a.f(e2, a.a.a.a.a.c("getImeiListBelowLollipop failed ex: "), "DeviceUtil");
            return null;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static List<String> k() {
        List<String> list;
        try {
            Class<?> cls = Class.forName("miui.telephony.TelephonyManagerEx");
            boolean z = false;
            Object objInvoke = cls.getMethod("getDefault", new Class[0]).invoke(null, new Object[0]);
            Method method = cls.getMethod("getImeiList", new Class[0]);
            if (method != null && !I() && (list = (List) method.invoke(objInvoke, new Object[0])) != null && list.size() > 0) {
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    if (!E(it.next())) {
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    return list;
                }
            }
        } catch (Throwable th) {
            a.a.a.a.a.j(th, a.a.a.a.a.c("getImeiListFromMiui failed ex: "), "DeviceUtil");
        }
        return null;
    }

    public static List<String> l(Context context) {
        List<String> listK = K(context);
        ArrayList arrayList = new ArrayList();
        if (listK != null && !listK.isEmpty()) {
            for (int i2 = 0; i2 < listK.size(); i2++) {
                if (!TextUtils.isEmpty(listK.get(i2))) {
                    arrayList.add(i2, a.b.a.e.g0.d.d(listK.get(i2)));
                }
            }
        }
        return arrayList;
    }

    public static String[] m(Class<?> cls, Class<?> cls2, TelephonyManager telephonyManager, SubscriptionManager subscriptionManager) {
        String[] strArr = new String[2];
        try {
            Class<?> cls3 = Integer.TYPE;
            int[] iArr = (int[]) cls2.getMethod("getSubscriptionIds", cls3).invoke(subscriptionManager, 0);
            if (iArr != null) {
                strArr[0] = (String) cls.getMethod("getSubscriberId", cls3).invoke(telephonyManager, Integer.valueOf(iArr[0]));
            }
        } catch (Exception e2) {
            a.a.a.a.a.h("get imsi1 above Android Q exception:", e2, "DeviceUtil");
        }
        try {
            Class<?> cls4 = Integer.TYPE;
            int[] iArr2 = (int[]) cls2.getMethod("getSubscriptionIds", cls4).invoke(subscriptionManager, 1);
            if (iArr2 != null) {
                strArr[1] = (String) cls.getMethod("getSubscriberId", cls4).invoke(telephonyManager, Integer.valueOf(iArr2[0]));
            }
        } catch (Exception e3) {
            a.a.a.a.a.h("get imsi2 above Android Q exception:", e3, "DeviceUtil");
        }
        return strArr;
    }

    public static String[] n(Class<?> cls, Class<?> cls2, TelephonyManager telephonyManager, SubscriptionManager subscriptionManager) {
        Object objInvoke;
        String[] strArr = new String[2];
        try {
            if (Build.VERSION.SDK_INT == 21) {
                strArr[0] = (String) cls.getMethod("getSubscriberId", Long.TYPE).invoke(telephonyManager, Long.valueOf(((long[]) cls2.getMethod("getSubId", Integer.TYPE).invoke(subscriptionManager, 0))[0]));
            } else {
                Class<?> cls3 = Integer.TYPE;
                strArr[0] = (String) cls.getMethod("getSubscriberId", cls3).invoke(telephonyManager, Integer.valueOf(((int[]) cls2.getMethod("getSubId", cls3).invoke(subscriptionManager, 0))[0]));
            }
            Method method = null;
            try {
                Class<?> cls4 = Class.forName("miui.telephony.TelephonyManagerEx");
                objInvoke = cls4.getMethod("getDefault", new Class[0]).invoke(null, new Object[0]);
                try {
                    method = cls4.getMethod("getSubscriberIdForSlot", Integer.TYPE);
                } catch (Exception unused) {
                }
            } catch (Exception unused2) {
                objInvoke = null;
            }
            if (!F(strArr[0]) && method != null && objInvoke != null) {
                strArr[0] = (String) method.invoke(objInvoke, 0);
                strArr[1] = (String) method.invoke(objInvoke, 1);
            } else if (Build.VERSION.SDK_INT == 21) {
                strArr[1] = (String) cls.getMethod("getSubscriberId", Long.TYPE).invoke(telephonyManager, Long.valueOf(((long[]) cls2.getMethod("getSubId", Integer.TYPE).invoke(subscriptionManager, 1))[0]));
            } else {
                Class<?> cls5 = Integer.TYPE;
                strArr[1] = (String) cls.getMethod("getSubscriberId", cls5).invoke(telephonyManager, Integer.valueOf(((int[]) cls2.getMethod("getSubId", cls5).invoke(subscriptionManager, 1))[0]));
            }
        } catch (Exception e2) {
            p.c("DeviceUtil", "getImsiFromLToP: " + e2);
        }
        return strArr;
    }

    public static List<String> o(Context context) {
        String subscriberId;
        String subscriberId2;
        ArrayList arrayList = new ArrayList();
        try {
            if (!H()) {
                String subscriberId3 = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
                if (F(subscriberId3)) {
                    arrayList.add(subscriberId3);
                }
                return arrayList;
            }
            Class<?> cls = Class.forName("android.telephony.TelephonyManager");
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 22) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService("telephony_subscription_service");
                Class<?> cls2 = Class.forName("android.telephony.SubscriptionManager");
                if (i2 < 29) {
                    subscriberId = n(cls, cls2, telephonyManager, subscriptionManager)[0];
                    subscriberId2 = n(cls, cls2, telephonyManager, subscriptionManager)[1];
                } else {
                    subscriberId = m(cls, cls2, telephonyManager, subscriptionManager)[0];
                    subscriberId2 = m(cls, cls2, telephonyManager, subscriptionManager)[1];
                }
            } else {
                Class<?> cls3 = Integer.TYPE;
                subscriberId = ((TelephonyManager) cls.getMethod("getDefault", cls3).invoke(null, 0)).getSubscriberId();
                subscriberId2 = ((TelephonyManager) cls.getMethod("getDefault", cls3).invoke(null, 1)).getSubscriberId();
            }
            if (!F(subscriberId)) {
                subscriberId = "";
            }
            arrayList.add(subscriberId);
            if (!F(subscriberId2)) {
                subscriberId2 = "";
            }
            arrayList.add(subscriberId2);
            return arrayList;
        } catch (SecurityException unused) {
            p.g("DeviceUtil", "getImsiList for failed with no permission");
            return arrayList;
        } catch (Exception e2) {
            p.h("DeviceUtil", "getImsiList for multi sim failed!", e2);
            return arrayList;
        }
    }

    public static List<String> p(Context context) {
        try {
            List<String> listO = o(context);
            int i2 = 0;
            while (true) {
                ArrayList arrayList = (ArrayList) listO;
                if (i2 >= arrayList.size()) {
                    return listO;
                }
                arrayList.set(i2, a.b.a.e.g0.d.b((String) arrayList.get(i2)));
                i2++;
            }
        } catch (Throwable th) {
            p.h(p.a("DeviceUtil"), "getImeiListMd5 failed!", th);
            return new ArrayList();
        }
    }

    public static String q(Context context) {
        if (!TextUtils.isEmpty(j)) {
            return j;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("pref_id", 0);
        String string = sharedPreferences.getString("d11", "");
        if (!TextUtils.isEmpty(string)) {
            j = b(string);
        }
        if (TextUtils.isEmpty(j)) {
            j = UUID.randomUUID().toString();
            sharedPreferences.edit().putString("d11", c(j)).apply();
        }
        return j;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static String r(Context context) throws Throwable {
        String string;
        String macAddress;
        String strA;
        Signature signature;
        Signature[] signatureArr;
        if (!TextUtils.isEmpty(g)) {
            return g;
        }
        if (TextUtils.isEmpty(f566c)) {
            String string2 = null;
            boolean z = true;
            if (h == null) {
                if (b.f568a == null) {
                    Signature[] signatureArr2 = new Signature[1];
                    try {
                        PackageInfo packageInfo = context.getPackageManager().getPackageInfo("android", 64);
                        signature = (packageInfo == null || (signatureArr = packageInfo.signatures) == null || signatureArr.length <= 0) ? null : signatureArr[0];
                    } catch (Exception e2) {
                        p.t("DeviceUtil", "getSystemSignature exception", e2);
                    }
                    signatureArr2[0] = signature;
                    b.f568a = signatureArr2;
                }
                Signature[] signatureArr3 = b.f568a;
                h = Boolean.valueOf(signatureArr3[0] != null && signatureArr3[0].equals(b.f569b));
            }
            if (h.booleanValue()) {
                Signature[] signatureArr4 = b.f568a;
                try {
                    string = Settings.Global.getString(context.getContentResolver(), "mi_device_mac");
                    if (TextUtils.isEmpty(string)) {
                        String str = Build.PRODUCT;
                        String strV = v("ro.product.model");
                        if ((!TextUtils.equals("MI_TV", b.b()) || "batman".equals(str) || "conan".equals(str)) && !"augustrush".equals(str) && !"casablanca".equals(str)) {
                            z = false;
                        }
                        if (TextUtils.equals("me2", str)) {
                            strA = v("persist.service.bdroid.bdaddr");
                        } else {
                            strA = b.a(((TextUtils.equals("transformers", str) && TextUtils.equals("MiBOX4C", strV)) || TextUtils.equals("dolphin-zorro", str)) ? "/sys/class/net/wlan0/address" : z ? "/sys/class/net/eth0/address" : "ro.boot.btmac");
                        }
                        string = !TextUtils.isEmpty(strA) ? strA.trim() : "";
                    }
                } catch (Exception e3) {
                    p.t("DeviceUtil", "getMiTvMac exception", e3);
                }
            } else {
                if (context.checkPermission("android.permission.ACCESS_WIFI_STATE", Process.myPid(), Process.myUid()) == 0) {
                    if (Build.VERSION.SDK_INT < 23) {
                        try {
                            macAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
                        } catch (Exception e4) {
                            p.h("DeviceUtil", "getMAC exception: ", e4);
                            macAddress = null;
                        }
                    } else {
                        macAddress = null;
                    }
                    if (TextUtils.isEmpty(macAddress) || "02:00:00:00:00:00".equals(macAddress)) {
                        try {
                            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                            while (networkInterfaces.hasMoreElements()) {
                                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                                byte[] hardwareAddress = networkInterfaceNextElement.getHardwareAddress();
                                if (hardwareAddress != null && hardwareAddress.length != 0) {
                                    StringBuilder sb = new StringBuilder();
                                    for (byte b2 : hardwareAddress) {
                                        sb.append(String.format("%02x:", Byte.valueOf(b2)));
                                    }
                                    if (sb.length() > 0) {
                                        sb.deleteCharAt(sb.length() - 1);
                                    }
                                    if ("wlan0".equals(networkInterfaceNextElement.getName())) {
                                        string2 = sb.toString();
                                        break;
                                    }
                                }
                            }
                        } catch (Exception e5) {
                            a.a.a.a.a.f(e5, a.a.a.a.a.c("queryMac failed ex: "), "DeviceUtil");
                        }
                    }
                }
                string = string2;
            }
            if (TextUtils.isEmpty(string)) {
                string = "";
            } else {
                f566c = string;
            }
        } else {
            string = f566c;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("pref_id", 0);
        if (TextUtils.isEmpty(string)) {
            g = b(sharedPreferences.getString("d3", ""));
        } else {
            g = a.b.a.e.g0.d.d(string);
            if (!TextUtils.equals(g, b(sharedPreferences.getString("d3", "")))) {
                sharedPreferences.edit().putString("d3", c(g)).apply();
            }
        }
        return g;
    }

    public static String s() {
        return v("ro.product.marketname");
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static long t() {
        long j2;
        try {
            long jB = B(Environment.getDataDirectory());
            if (jB == 0) {
                p.s("DeviceUtil", "totalSpace return 0, just return 100MB!");
                return 104857600L;
            }
            long j3 = jB / 1000000000;
            long jPow = (jB >= 8000000000L ? (long) Math.pow(2.0d, Math.ceil(Math.log(j3) / Math.log(2.0d))) : j3 + 1) * 1000000000;
            if (jPow < jB) {
                long j4 = 1;
                long j5 = 1;
                while (true) {
                    j2 = j4 * j5;
                    if (j2 >= jB) {
                        break;
                    }
                    j4 <<= 1;
                    if (j4 > 512) {
                        j5 *= 1000;
                        j4 = 1;
                    }
                }
                jPow = j2;
            }
            long j6 = jPow / 1000000000;
            p.c("DeviceUtil", "totalSize: " + jB + ", formatGBSize: " + j6);
            if (j6 >= 64) {
                return 1073741824L;
            }
            if (j6 <= 4) {
                return 52428800L;
            }
            long j7 = ((jB / 1048576) / 64) / 100;
            if (j7 == 0) {
                return 104857600L;
            }
            if (j7 <= 5) {
                return j7 * 100 * 1048576;
            }
            return 524288000L;
        } catch (Throwable th) {
            StringBuilder sbC = a.a.a.a.a.c("getMaxDBSize e：");
            sbC.append(th.getMessage());
            p.g("DeviceUtil", sbC.toString());
            return 52428800L;
        }
    }

    public static List<String> u(Context context) {
        try {
            List<String> listL = L(context);
            if (listL != null) {
                Collections.sort(listL, new a());
                for (int i2 = 0; i2 < listL.size(); i2++) {
                    listL.set(i2, a.b.a.e.g0.d.b(listL.get(i2)));
                }
                return listL;
            }
        } catch (Throwable th) {
            p.h("DeviceUtil", "getMeidListMd5 e", th);
        }
        return new ArrayList();
    }

    public static String v(String str) {
        try {
            Method method = Class.forName("android.os.SystemProperties").getMethod("get", String.class);
            if (method != null) {
                return String.valueOf(method.invoke(null, str));
            }
        } catch (Exception e2) {
            a.a.a.a.a.f(e2, a.a.a.a.a.c("getProp failed ex: "), "DeviceUtil");
        }
        return null;
    }

    public static String w(Context context) {
        if (!TextUtils.isEmpty(f564a)) {
            return f564a;
        }
        K(context);
        return !TextUtils.isEmpty(f564a) ? f564a : "";
    }

    public static String x() {
        String strV = v("ro.ril.oem.psno");
        if (TextUtils.isEmpty(strV)) {
            strV = v("persist.radio.factory_phone_sn");
        }
        return !TextUtils.isEmpty(strV) ? strV : "";
    }

    public static String y() {
        String strX = x();
        return !TextUtils.isEmpty(strX) ? a.b.a.e.g0.d.d(strX) : "";
    }

    public static String z() {
        try {
            Object objInvoke = TelephonyManager.class.getDeclaredMethod("getDefault", new Class[0]).invoke(null, new Object[0]);
            return (String) objInvoke.getClass().getDeclaredMethod("getNetworkOperator", new Class[0]).invoke(objInvoke, new Object[0]);
        } catch (Throwable th) {
            p.h("DeviceUtil", "getSimType e", th);
            return "";
        }
    }
}
