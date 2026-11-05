package i2;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.l;
import dalvik.system.PathClassLoader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-439d64a6e73e6da60b62f1cd0d3775067af568959c6537234e5044e8e318ed73 */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {
    public static int A;
    public static int B;
    public static int C;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final Class f1843l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final PathClassLoader f1844m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final Constructor f1845n;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static Method f1849r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final Method f1850s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static Context f1851t;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Pattern f1834a = Pattern.compile("Inc ([A-Z]+)([\\d]+)");
    public static final Pattern b = Pattern.compile("MT([\\d]{2})([\\d]+)");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Boolean f1835c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static int f1836d = -2;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static int f1837e = -2;
    public static Boolean f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static Boolean f1838g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static int f1839h = -1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static int f1840i = -1;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static int f1841j = -1;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static int f1842k = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static Object f1846o = null;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static Method f1847p = null;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static Method f1848q = null;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final int f1852u = 1;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static int f1853v = 1;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static boolean f1854w = false;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static int f1855x = 1;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static int f1856y = 2;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static int f1857z = 3;

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    static {
        f1845n = null;
        f1849r = null;
        f1850s = null;
        try {
            if (Build.VERSION.SDK_INT > 33) {
                PathClassLoader pathClassLoader = new PathClassLoader("/system_ext/framework/MiuiBooster.jar", ClassLoader.getSystemClassLoader());
                f1844m = pathClassLoader;
                f1843l = pathClassLoader.loadClass("com.miui.performance.DeviceLevelUtils");
            } else {
                PathClassLoader pathClassLoader2 = new PathClassLoader("/system/framework/MiuiBooster.jar", ClassLoader.getSystemClassLoader());
                f1844m = pathClassLoader2;
                f1843l = pathClassLoader2.loadClass("com.miui.performance.DeviceLevelUtils");
            }
            Class cls = f1843l;
            f1845n = cls.getConstructor(Context.class);
            f1849r = cls.getDeclaredMethod("getMiuiLiteVersion", null);
        } catch (Exception e4) {
            Log.e("DeviceUtils", "static init(): Load Class Exception:" + e4);
        }
        PathClassLoader pathClassLoader3 = f1844m;
        if (pathClassLoader3 != null) {
            try {
                Class<?> clsLoadClass = pathClassLoader3.loadClass("com.miui.performance.ComputilityLevelUtils");
                clsLoadClass.getConstructor(null);
                clsLoadClass.getDeclaredMethod("initComputilityLevel", null);
                clsLoadClass.getDeclaredMethod("getComputilityVersion", null);
                clsLoadClass.getDeclaredMethod("getComputilityLevel", null);
                clsLoadClass.getDeclaredMethod("getComputilityVersionV2", null);
                clsLoadClass.getDeclaredMethod("getComputilityLevelV2", null);
            } catch (Exception e5) {
                Log.e("DeviceUtils", "static init: ComputilityLevel Load Class Exception:" + e5);
            }
        }
        try {
            f1850s = f1843l.getDeclaredMethod("getMiuiMiddleVersion", null);
        } catch (Exception e6) {
            Log.e("DeviceUtils", "static init(): Load MiuiMiddle Class Exception:" + e6);
        }
        if (f1843l == null) {
            Log.e("DeviceUtils", "static init(): MiuiBooster is not in this rom");
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static Context a() {
        if (f1851t == null) {
            try {
                Application application = (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", null).invoke(null, null);
                if (application != null) {
                    f1851t = application.getApplicationContext();
                }
            } catch (Exception e4) {
                Log.e("DeviceUtils", "android.app.ActivityThread Exception:" + e4);
            }
        }
        if (f1851t == null) {
            try {
                Application application2 = (Application) Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", null).invoke(null, null);
                if (application2 != null) {
                    f1851t = application2.getApplicationContext();
                }
            } catch (Exception e5) {
                Log.e("DeviceUtils", "android.app.AppGlobals Exception:" + e5);
            }
        }
        return f1851t;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX WARN: Code duplicated, block: B:49:0x009c  */
    /* JADX WARN: Code duplicated, block: B:77:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:9:0x001d  */
    public static int b() {
        int iIntValue;
        int i4;
        int i5;
        int iIntValue2;
        int i6;
        int i7 = f1853v;
        int i8 = f1852u;
        if (i7 == i8 && (i6 = f1839h) != -1) {
            return i6;
        }
        f1853v = i8;
        int iC = 2;
        if (h()) {
            try {
                Object objE = e();
                if (objE == null) {
                    throw new Exception("perf is null!");
                }
                iIntValue = ((Integer) f1848q.invoke(objE, Integer.valueOf(i8))).intValue();
                if (iIntValue == A) {
                    i4 = 0;
                } else if (iIntValue == B) {
                    i4 = 1;
                } else if (iIntValue == C) {
                    i4 = 2;
                } else {
                    i4 = -1;
                }
            } catch (Exception e4) {
                l.h(e4, new StringBuilder("getDeviceLevel failed , e:"), "DeviceUtils");
                iIntValue = -1;
            }
        } else {
            i4 = -1;
        }
        f1839h = i4;
        if (i4 != -1 || i4 != -1) {
            return i4;
        }
        if (j()) {
            f1839h = 0;
        } else {
            int iC2 = c(f1856y);
            int iC3 = c(f1855x);
            int i9 = f1857z;
            if (i9 != f1856y ? f1853v != i8 || (i5 = f1841j) == -1 : f1853v != i8 || (i5 = f1840i) == -1) {
                if (h()) {
                    try {
                        Object objE2 = e();
                        if (objE2 == null) {
                            throw new Exception("perf is null!");
                        }
                        iIntValue2 = ((Integer) f1847p.invoke(objE2, Integer.valueOf(i8), Integer.valueOf(i9))).intValue();
                        if (iIntValue2 == A) {
                            iC = 0;
                        } else if (iIntValue2 == B) {
                            iC = 1;
                        } else if (iIntValue2 != C) {
                            iC = -1;
                        }
                    } catch (Exception e5) {
                        l.h(e5, new StringBuilder("getDeviceLevel failed , e:"), "DeviceUtils");
                        iIntValue2 = -1;
                    }
                } else {
                    iC = -1;
                }
                if (iC != -1) {
                    f1853v = i8;
                    if (i9 == f1856y) {
                        f1840i = iC;
                    } else if (i9 == f1857z) {
                        f1841j = iC;
                    } else if (i9 != f1855x) {
                        i5 = -1;
                    }
                    i5 = iC;
                } else {
                    iC = c(i9);
                    f1853v = i8;
                    if (i9 == f1856y) {
                        f1840i = iC;
                    } else if (i9 == f1857z) {
                        f1841j = iC;
                    } else if (i9 != f1855x) {
                        i5 = -1;
                    }
                    i5 = iC;
                }
            }
            int[] iArr = {iC2, iC3, i5};
            int i10 = iArr[0];
            for (int i11 = 0; i11 < 3; i11++) {
                int i12 = iArr[i11];
                if (i12 > -1 && i12 < i10) {
                    i10 = i12;
                }
            }
            f1839h = i10;
        }
        return f1839h;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX WARN: Code duplicated, block: B:119:0x0169  */
    /* JADX WARN: Code duplicated, block: B:52:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:53:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:73:0x00ee  */
    public static int c(int i4) {
        String str;
        int i5;
        String strGroup;
        String strGroup2;
        String strGroup3;
        String strGroup4;
        int i6 = 0;
        if (i4 == f1855x) {
            int iG = g();
            if (iG > 6) {
                return 2;
            }
            if (iG > 4) {
                return 1;
            }
            if (iG > 0) {
                return 0;
            }
        } else if (i4 == f1856y) {
            try {
                Scanner scanner = new Scanner(new File("/proc/cpuinfo"));
                while (true) {
                    try {
                        if (!scanner.hasNextLine()) {
                            scanner.close();
                            str = "";
                            break;
                        }
                        String strNextLine = scanner.nextLine();
                        if (!scanner.hasNextLine()) {
                            String[] strArrSplit = strNextLine.split(": ");
                            if (strArrSplit.length > 1) {
                                str = strArrSplit[1];
                                scanner.close();
                                break;
                            }
                            Log.e("DeviceUtils", "getChipSetFromCpuInfo failed", e);
                            str = "";
                            break;
                        }
                    } catch (Throwable th) {
                        try {
                            scanner.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                }
            } catch (Exception e4) {
                Log.e("DeviceUtils", "getChipSetFromCpuInfo failed", e4);
            }
            if (str.length() <= 0) {
                i5 = -1;
            } else if (str.contains("Qualcomm")) {
                Matcher matcher = f1834a.matcher(str);
                if (!matcher.find() || (strGroup3 = matcher.group(1)) == null || (strGroup4 = matcher.group(2)) == null) {
                    i5 = -1;
                } else {
                    String lowerCase = strGroup3.toLowerCase(Locale.ENGLISH);
                    if (lowerCase.equals("sm")) {
                        int i7 = Integer.parseInt(strGroup4.substring(0, 1));
                        if (i7 >= 8) {
                            i5 = 2;
                        } else if (i7 >= 7) {
                            i5 = 1;
                        } else {
                            i5 = 0;
                        }
                    } else {
                        if (lowerCase.equals("sdm")) {
                            if (Integer.parseInt(strGroup4.substring(0, 1)) >= 7) {
                                i5 = 1;
                            }
                        } else if (!lowerCase.equals("msm")) {
                            i5 = -1;
                        }
                        i5 = 0;
                    }
                }
            } else {
                Matcher matcher2 = b.matcher(str);
                if (!matcher2.find() || (strGroup = matcher2.group(1)) == null || (strGroup2 = matcher2.group(2)) == null) {
                    i5 = -1;
                } else {
                    int i8 = Integer.parseInt(strGroup);
                    int i9 = Integer.parseInt(strGroup2);
                    if (i8 != 68 || i9 < 73) {
                        i5 = 0;
                    } else {
                        i5 = 1;
                    }
                }
            }
            if (i5 != -1) {
                return i5;
            }
            ArrayList arrayList = new ArrayList();
            try {
                Scanner scanner2 = new Scanner(new File("/proc/cpuinfo"));
                a aVarM = null;
                while (scanner2.hasNextLine()) {
                    try {
                        String[] strArrSplit2 = scanner2.nextLine().split(": ");
                        if (strArrSplit2.length > 1) {
                            aVarM = m(strArrSplit2, arrayList, aVarM);
                        }
                    } catch (Throwable th3) {
                        try {
                            scanner2.close();
                        } catch (Throwable th4) {
                            th3.addSuppressed(th4);
                        }
                        throw th3;
                    }
                }
                scanner2.close();
            } catch (Exception e5) {
                Log.e("DeviceUtils", "getChipSetFromCpuInfo failed", e5);
            }
            int i10 = arrayList.size() < 8 ? 0 : -1;
            int size = arrayList.size();
            int i11 = 0;
            int i12 = 0;
            int i13 = 0;
            while (i11 < size) {
                Object obj = arrayList.get(i11);
                i11++;
                a aVar = (a) obj;
                if (aVar.f1831c < 8) {
                    i10 = 0;
                }
                int i14 = aVar.f1833e;
                if (i14 > i12) {
                    i12 = i14;
                }
                if (i14 >= 2000000) {
                    i13++;
                }
            }
            if (i10 != -1) {
                i6 = i10;
            } else if (i13 >= 4) {
                if (i12 > 2700000) {
                    i6 = 2;
                } else if (i12 > 2300000) {
                    i6 = 1;
                }
            } else if (i12 > 2300000) {
                i6 = 1;
            }
            return i6;
        }
        return -1;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static int d() {
        int iIntValue;
        if (!j()) {
            f1836d = 0;
            return 0;
        }
        int i4 = f1836d;
        if (i4 != -2) {
            return i4;
        }
        try {
            Object objE = e();
            if (objE == null) {
                throw new Exception("perf is null!");
            }
            if (f1849r == null) {
                f1849r = f1843l.getDeclaredMethod("getMiuiLiteVersion", null);
            }
            iIntValue = ((Integer) f1849r.invoke(objE, null)).intValue();
            if (iIntValue >= 2) {
                f1836d = iIntValue;
            } else {
                f1836d = 1;
            }
            return f1836d;
        } catch (Exception e4) {
            l.h(e4, new StringBuilder("getMiuiLiteVersion failed , e:"), "DeviceUtils");
            iIntValue = -1;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static Object e() {
        Constructor constructor;
        if (f1846o == null) {
            try {
                Context contextA = a();
                if (contextA == null || (constructor = f1845n) == null) {
                    throw new Exception("getAppContext fail");
                }
                f1846o = constructor.newInstance(contextA);
            } catch (Exception e4) {
                Log.e("DeviceUtils", "getPerf DeviceUtils(): newInstance Exception:" + e4);
                e4.printStackTrace();
            }
        }
        return f1846o;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static Object f(String str, Class cls) throws NoSuchFieldException {
        Field declaredField = cls.getDeclaredField(str);
        declaredField.setAccessible(true);
        return declaredField.get(null);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static int g() {
        if (f1842k == Integer.MAX_VALUE) {
            try {
                f1842k = (int) (((((Long) Class.forName("miui.util.HardwareInfo").getMethod("getTotalPhysicalMemory", null).invoke(null, null)).longValue() / 1024) / 1024) / 1024);
            } catch (Throwable th) {
                Log.e("DeviceUtils", th.getMessage());
                f1842k = 0;
            }
        }
        return f1842k;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static boolean h() {
        boolean z3 = f1854w;
        if (!z3) {
            try {
                Class cls = f1843l;
                if (cls != null) {
                    Class cls2 = Integer.TYPE;
                    f1847p = cls.getDeclaredMethod("getDeviceLevel", cls2, cls2);
                    f1848q = cls.getDeclaredMethod("getDeviceLevel", cls2);
                    f1855x = ((Integer) f("DEVICE_LEVEL_FOR_RAM", cls)).intValue();
                    f1856y = ((Integer) f("DEVICE_LEVEL_FOR_CPU", cls)).intValue();
                    f1857z = ((Integer) f("DEVICE_LEVEL_FOR_GPU", cls)).intValue();
                    A = ((Integer) f("LOW_DEVICE", cls)).intValue();
                    B = ((Integer) f("MIDDLE_DEVICE", cls)).intValue();
                    C = ((Integer) f("HIGH_DEVICE", cls)).intValue();
                    ((Integer) f("DEVICE_LEVEL_UNKNOWN", cls)).getClass();
                    f1854w = true;
                    return f1854w;
                }
            } catch (Exception e4) {
                Log.e("DeviceUtils", "initDeviceLevelInfo Fail: Exception:" + e4);
            }
        }
        return z3;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static boolean i() {
        if (f == null) {
            f = Boolean.valueOf("true".contentEquals(c.a("ro.config.low_ram.support_miuilite_plus", "false")));
        }
        return f.booleanValue();
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static boolean j() {
        if (f1835c == null) {
            try {
                Boolean bool = (Boolean) Class.forName("miui.os.Build").getDeclaredField("IS_MIUI_LITE_VERSION").get(null);
                bool.booleanValue();
                f1835c = bool;
            } catch (Throwable th) {
                Log.i("DeviceUtils", "isMiuiLiteRom failed", th);
                f1835c = null;
            }
        }
        return Boolean.TRUE.equals(f1835c);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static boolean k() {
        return j() && d() >= 2;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static boolean l() {
        if (f1837e == -2) {
            try {
                Object objInvoke = f1850s.invoke(e(), null);
                if (objInvoke != null) {
                    f1837e = ((Integer) objInvoke).intValue();
                } else {
                    f1837e = -1;
                }
            } catch (Exception e4) {
                f1837e = -1;
                l.h(e4, new StringBuilder("getMiuiMiddleVersion failed , e:"), "DeviceUtils");
            }
        }
        return f1837e >= 1;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX WARN: Code duplicated, block: B:31:0x0076  */
    public static a m(String[] strArr, ArrayList arrayList, a aVar) throws Throwable {
        BufferedReader bufferedReader;
        FileInputStream fileInputStream;
        String strTrim = strArr[1].trim();
        if (!strArr[0].contains("processor") || !TextUtils.isDigitsOnly(strTrim)) {
            if (aVar != null) {
                String str = strArr[0];
                if (str.contains("CPU implementer")) {
                    aVar.b = n(strTrim);
                    return aVar;
                }
                if (str.contains("CPU architecture")) {
                    aVar.f1831c = n(strTrim);
                    return aVar;
                }
                if (str.contains("CPU part")) {
                    aVar.f1832d = n(strTrim);
                }
            }
            return aVar;
        }
        a aVar2 = new a();
        int i4 = Integer.parseInt(strTrim);
        aVar2.f1830a = i4;
        Locale locale = Locale.ENGLISH;
        FileInputStream fileInputStream2 = null;
        line = null;
        String line = null;
        try {
            fileInputStream = new FileInputStream("/sys/devices/system/cpu/cpu" + i4 + "/cpufreq/cpuinfo_max_freq");
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                try {
                    line = bufferedReader.readLine();
                    try {
                        fileInputStream.close();
                    } catch (IOException unused) {
                    }
                } catch (IOException unused2) {
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException unused3) {
                        }
                    }
                    if (bufferedReader != null) {
                    }
                    if (line != null) {
                        aVar2.f1833e = Integer.parseInt(line);
                    }
                    arrayList.add(aVar2);
                    return aVar2;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream2 = fileInputStream;
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException unused4) {
                        }
                    }
                    if (bufferedReader == null) {
                        throw th;
                    }
                    try {
                        bufferedReader.close();
                        throw th;
                    } catch (IOException unused5) {
                        throw th;
                    }
                }
            } catch (IOException unused6) {
                bufferedReader = null;
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
            }
        } catch (IOException unused7) {
            bufferedReader = null;
            fileInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
        }
        try {
            bufferedReader.close();
        } catch (IOException unused8) {
        }
        if (line != null) {
            aVar2.f1833e = Integer.parseInt(line);
        }
        arrayList.add(aVar2);
        return aVar2;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static int n(String str) {
        return str.startsWith("0x") ? Integer.parseInt(str.substring(2), 16) : Integer.parseInt(str);
    }
}
