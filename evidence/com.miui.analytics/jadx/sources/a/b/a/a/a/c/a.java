package a.b.a.a.a.c;

import a.b.a.c.f;
import a.b.a.c.v.h;
import a.b.a.c.v.i;
import a.b.a.c.v.n;
import a.b.a.e.l0.k;
import a.b.a.e.l0.l;
import a.b.a.e.l0.p;
import a.b.a.e.l0.r;
import a.b.a.e.l0.u;
import a.b.a.e.l0.v;
import a.b.a.e.w;
import a.b.a.e.y;
import a.b.a.f.h.b;
import a.b.a.f.h.c;
import a.b.a.f.h.d;
import a.b.a.f.h.e;
import a.b.a.f.h.g;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Looper;
import android.os.RemoteException;
import android.os.UserManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import b.c0;
import b.e0;
import com.miui.analytics.Analytics;
import com.miui.analytics.BuildConfig;
import com.miui.analytics.ICore;
import com.miui.analytics.R;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.log.internal.Level;
import com.miui.analytics.onetrack.runnable.TrackThreadManager;
import com.xiaomi.security.devicecredential.SecurityDeviceCredentialAbility;
import com.xiaomi.security.devicecredential.SecurityDeviceCredentialManager;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.zip.GZIPOutputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Context f2a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile String f3b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static volatile Boolean f4c;

    public static boolean A(long j, long j2) {
        return Math.abs(System.currentTimeMillis() - j) >= j2;
    }

    public static <T> T A0(Callable<T> callable, Boolean bool) {
        String str;
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Future<T> futureSubmit = executorServiceNewSingleThreadExecutor.submit(callable);
        try {
            return futureSubmit.get(30L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e = e;
            str = "safeCall throw InterruptedException,";
            u0(bool, str, e);
            executorServiceNewSingleThreadExecutor.shutdown();
            return null;
        } catch (ExecutionException e2) {
            e = e2;
            str = "safeCall throw ExecutionException,";
            u0(bool, str, e);
            executorServiceNewSingleThreadExecutor.shutdown();
            return null;
        } catch (TimeoutException e3) {
            futureSubmit.cancel(true);
            u0(bool, "safeCall throw TimeoutException,", e3);
            executorServiceNewSingleThreadExecutor.shutdown();
            return null;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static String B(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            if (str.startsWith("http")) {
                if (TextUtils.equals(new URL(str).getHost(), "tracking.miui.com")) {
                    z = true;
                }
            } else if (str.startsWith("tracking.miui.com")) {
                z = true;
            }
        } catch (Exception unused) {
        }
        if (str.startsWith("http")) {
            return z ? str.replaceFirst("http://", "https://") : str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(z ? "https://" : "http://");
        sb.append(str);
        return sb.toString();
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static <T> T B0(Callable<T> callable, boolean z) {
        String str;
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Future<T> futureSubmit = executorServiceNewSingleThreadExecutor.submit(callable);
        try {
            return futureSubmit.get(30L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e = e;
            str = "safeCallForNonce throw InterruptedException,";
            p.h("SecurityDeviceUtil", str, e);
            w(z, "SecurityDeviceUtil", str, e);
            executorServiceNewSingleThreadExecutor.shutdown();
            return null;
        } catch (ExecutionException e2) {
            e = e2;
            str = "safeCallForNonce throw ExecutionException,";
            p.h("SecurityDeviceUtil", str, e);
            w(z, "SecurityDeviceUtil", str, e);
            executorServiceNewSingleThreadExecutor.shutdown();
            return null;
        } catch (TimeoutException e3) {
            futureSubmit.cancel(true);
            p.h("SecurityDeviceUtil", "safeCallForNonce throw TimeoutException,", e3);
            w(z, "SecurityDeviceUtil", "safeCallForNonce throw TimeoutException,", e3);
            executorServiceNewSingleThreadExecutor.shutdown();
            return null;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static LogEvent C(Context context, JSONObject jSONObject) {
        LogEvent logEvent;
        LogEvent.LogType logType = LogEvent.LogType.TYPE_AD;
        try {
            String string = jSONObject.getString("configKey");
            String string2 = jSONObject.getString("appId");
            String strOptString = jSONObject.optString("content");
            LogEvent.LogType logType2 = LogEvent.LogType.TYPE_EVENT;
            if (jSONObject.optInt("logType", logType2.f1261a) == 1) {
                logType2 = logType;
            }
            if (logType2 == logType) {
                logEvent = new a.b.a.c.a(context, string2, string, strOptString);
                logEvent.m = jSONObject.optString("extra");
            } else {
                logEvent = new LogEvent(context, string2, string, strOptString);
            }
            logEvent.f1253d = jSONObject.optString("sessionId");
            long jOptLong = jSONObject.optLong("eventTime");
            if (jOptLong > 0) {
                logEvent.e = jOptLong;
            }
            return logEvent;
        } catch (Exception e) {
            Log.e(i.a("LogEventCreator"), "fromJsonV1 e", e);
            return null;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static byte[] C0(Object obj) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        ObjectOutputStream objectOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            if (obj == null) {
                return null;
            }
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                    try {
                        objectOutputStream.writeObject(obj);
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        g(byteArrayOutputStream);
                        g(objectOutputStream);
                        return byteArray;
                    } catch (IOException e) {
                        e = e;
                        p.g("DataConvertUtil", "serialize error:" + e.getMessage());
                        g(byteArrayOutputStream);
                        g(objectOutputStream);
                        return null;
                    }
                } catch (IOException e2) {
                    e = e2;
                    objectOutputStream = null;
                } catch (Throwable th) {
                    th = th;
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    g(byteArrayOutputStream2);
                    g(0);
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
                byteArrayOutputStream = null;
                objectOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                g(byteArrayOutputStream2);
                g(0);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static LogEvent D(Context context, JSONObject jSONObject) {
        LogEvent logEvent;
        try {
            String string = jSONObject.getString("configKey");
            String string2 = jSONObject.getString("appId");
            String strOptString = jSONObject.optString("content");
            int iOptInt = jSONObject.optInt("logType", LogEvent.LogType.TYPE_EVENT.f1261a);
            int iOptInt2 = jSONObject.optInt("idType", LogEvent.IdType.TYPE_DEFAULT.f1257a);
            boolean zOptBoolean = jSONObject.optBoolean("isBasicMode", false);
            if (iOptInt == LogEvent.LogType.TYPE_AD.f1261a) {
                logEvent = new a.b.a.c.a(context, string2, string, strOptString, iOptInt2, zOptBoolean);
                logEvent.m = jSONObject.optString("extra");
            } else {
                logEvent = new LogEvent(context, string2, string, strOptString, iOptInt, iOptInt2, zOptBoolean);
            }
            logEvent.f1253d = jSONObject.optString("sessionId");
            long jOptLong = jSONObject.optLong("eventTime");
            if (jOptLong > 0) {
                logEvent.e = jOptLong;
            }
            return logEvent;
        } catch (Exception e) {
            Log.e(i.a("LogEventCreator"), "fromJsonV2 e", e);
            return null;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void D0(Context context, String str) {
        int i;
        try {
            i = context.getPackageManager().getApplicationInfo(str, 1).uid;
        } catch (Exception unused) {
            Log.e(i.a("AndroidUtils"), "getUidByPackage exception: " + str);
            i = -1;
        }
        if (i >= 0) {
            i.b("AndroidUtils", "packageName:" + str + ", uid:" + i);
            try {
                if (h0(context, "android.permission.UPDATE_DEVICE_STATS")) {
                    i.b("AndroidUtils", "has setThreadStatsUid permission");
                    Class<?> cls = Class.forName("android.net.TrafficStats");
                    Class<?> cls2 = Integer.TYPE;
                    cls.getMethod("setThreadStatsUid", cls2).invoke(null, Integer.valueOf(i));
                    if (i == 1000) {
                        cls.getMethod("setThreadStatsPid", cls2).invoke(null, Integer.valueOf(Z(context, str)));
                    }
                } else {
                    i.b("AndroidUtils", "not has setThreadStatsUidAndPid permission");
                }
            } catch (Exception e) {
                Log.e(i.a("AndroidUtils"), "setThreadStatsUidAndPid e", e);
            }
        }
    }

    public static String E(String str) {
        return G(str, "");
    }

    public static String E0(String str, Boolean bool) {
        if (!TextUtils.isEmpty(str)) {
            return (String) A0(new d(str, bool), bool);
        }
        t0(bool, "content is null, return empty string");
        return "";
    }

    public static String F(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (Exception e) {
            Log.e(i.a("SystemProperties"), "get e", e);
            return str2;
        }
    }

    public static String F0(String str, boolean z) {
        return TextUtils.isEmpty(str) ? "" : (String) B0(new g(str, z), z);
    }

    public static String G(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (Exception e) {
            Log.e(p.a("SystemProperties"), "get e", e);
            return str2;
        }
    }

    public static boolean G0(Context context, Boolean bool) {
        if (f4c == null) {
            return Boolean.TRUE.equals(A0(new b(context, bool), bool));
        }
        StringBuilder sbC = a.a.a.a.a.c("signSupported, canSign get from memory : ");
        sbC.append(f4c);
        t0(bool, sbC.toString());
        return f4c.booleanValue();
    }

    public static String H(int i) {
        switch (i) {
            case 10:
                return "download";
            case 11:
                return "install";
            case 12:
                return "update";
            case 13:
                return "uninstall";
            case 14:
                return "first_launch";
            case 15:
                return "pi_install";
            default:
                return "";
        }
    }

    public static boolean H0(Context context, boolean z) {
        if (f4c == null) {
            return Boolean.TRUE.equals(B0(new e(context, z), z));
        }
        StringBuilder sbC = a.a.a.a.a.c("signSupportedForNonce, canSign get from memory : ");
        sbC.append(f4c);
        n(z, "SecurityDeviceUtil", sbC.toString(), true);
        return f4c.booleanValue();
    }

    public static Context I(Context context) {
        return (context == null || context.getApplicationContext() == null) ? context : context.getApplicationContext();
    }

    public static boolean I0(Context context) throws InterruptedException, SecurityDeviceCredentialManager.OperationFailedException, RemoteException {
        StringBuilder sb;
        String str;
        if (f4c != null) {
            sb = new StringBuilder();
            str = "signSupportedOrThrow, canSign get from memory : ";
        } else {
            f4c = Boolean.valueOf(e(context));
            sb = new StringBuilder();
            str = "canSign get from binder : ";
        }
        sb.append(str);
        sb.append(f4c);
        p.c("SecurityDeviceUtil", sb.toString());
        return f4c.booleanValue();
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static String J(Context context) {
        return context.getDir("analytics", 0).getAbsolutePath() + "/analytics_asset.apk";
    }

    public static byte[] J0(String str) throws InterruptedException, SecurityDeviceCredentialManager.OperationFailedException, RemoteException, UnsupportedEncodingException {
        if (Build.VERSION.SDK_INT >= 34) {
            try {
                a.b.a.h.d dVar = a.b.a.h.d.f894a;
                return a.b.a.h.d.f894a.c(1, str.getBytes("UTF-8"));
            } catch (SecurityDeviceCredentialManager.OperationFailedException e) {
                if (e.errorCode != -101) {
                    return null;
                }
            }
        }
        return K0(str);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static l K(Context context) {
        try {
            FutureTask futureTask = new FutureTask(new k(context));
            ThreadPoolExecutor threadPoolExecutor = a.b.a.e.l0.g.f580a;
            try {
                a.b.a.e.l0.g.f580a.execute(futureTask);
            } catch (Throwable unused) {
            }
            return (l) futureTask.get(3L, TimeUnit.SECONDS);
        } catch (Throwable th) {
            StringBuilder sbC = a.a.a.a.a.c("getClientIdInfo error: ");
            sbC.append(th.getMessage());
            p.g("GoogleSettingUtil", sbC.toString());
            return null;
        }
    }

    public static byte[] K0(String str) throws Throwable {
        SecurityDeviceCredentialAbility securityDeviceCredentialAbility = null;
        try {
            SecurityDeviceCredentialAbility securityDeviceCredentialAbility2 = new SecurityDeviceCredentialAbility(a.b.a.e.e.b());
            try {
                byte[] bArrSignWithDeviceCredential = securityDeviceCredentialAbility2.signWithDeviceCredential(str.getBytes("UTF-8"), true);
                securityDeviceCredentialAbility2.release();
                return bArrSignWithDeviceCredential;
            } catch (Throwable th) {
                th = th;
                securityDeviceCredentialAbility = securityDeviceCredentialAbility2;
                if (securityDeviceCredentialAbility != null) {
                    securityDeviceCredentialAbility.release();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static int L(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            if (BuildConfig.APPLICATION_ID.equals(context.getPackageName())) {
                try {
                    return context.getPackageManager().getPackageInfo(BuildConfig.APPLICATION_ID, 0).versionCode;
                } catch (Exception unused) {
                    return 0;
                }
            }
            int iQ0 = q0(U(context), context);
            if (iQ0 < 0) {
                iQ0 = q0(J(context), context);
            }
            if (iQ0 < 0) {
                try {
                    iQ0 = context.getPackageManager().getPackageInfo(BuildConfig.APPLICATION_ID, 0).versionCode;
                } catch (Exception unused2) {
                    iQ0 = 0;
                }
            }
            if (iQ0 >= 0) {
                return iQ0;
            }
        } catch (Exception e) {
            Log.e(i.a("Client"), "getCoreVersionCode exception", e);
        }
        return 0;
    }

    public static List<String> L0(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            String[] strArrSplit = str.split(str2);
            if (strArrSplit == null || strArrSplit.length <= 0) {
                return null;
            }
            return Arrays.asList(strArrSplit);
        } catch (Exception e) {
            a.a.a.a.a.g(e, a.a.a.a.a.c("StringToList error: "), "StringUtil");
            return null;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static String M(Context context) {
        String string;
        if (context == null) {
            return "0.0.0";
        }
        try {
            if (BuildConfig.APPLICATION_ID.equals(context.getPackageName())) {
                string = context.getString(R.string.analytics_version_name);
            } else {
                String strR0 = r0(U(context), context);
                if (TextUtils.isEmpty(strR0)) {
                    strR0 = r0(J(context), context);
                }
                if (TextUtils.isEmpty(strR0)) {
                    try {
                        string = context.getPackageManager().getPackageInfo(BuildConfig.APPLICATION_ID, 0).versionName;
                    } catch (Exception unused) {
                        string = null;
                    }
                } else {
                    string = strR0;
                }
            }
            return !TextUtils.isEmpty(string) ? string : "0.0.0";
        } catch (Exception e) {
            Log.e(i.a("Client"), "getCoreVersionName exception", e);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static byte[] M0(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            int i = inputStream.read(bArr);
            if (-1 == i) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
    }

    public static JSONObject N() {
        String strD = u.d();
        if (TextUtils.isEmpty(strD)) {
            return null;
        }
        try {
            int iB = v.b(0);
            JSONObject jSONObject = new JSONObject(strD);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                if (TextUtils.isEmpty(next) || Integer.parseInt(next) >= iB) {
                    itKeys.remove();
                }
            }
            return jSONObject;
        } catch (Exception e) {
            StringBuilder sbC = a.a.a.a.a.c("getCrashCountBeforeToday failed, e:");
            sbC.append(e.getMessage());
            p.s("CrashUtil", sbC.toString());
            return null;
        }
    }

    public static String N0(String str) {
        return str.endsWith("/") ? str : a.a.a.a.a.a(str, "/");
    }

    public static Map<String, String> O(Context context, int i) {
        HashMap map = new HashMap();
        try {
            Resources resources = context.getResources();
            if (resources == null) {
                Log.e("XmlParser", "Could not find the resources of the current context while trying to set defaults from an XML.");
                return map;
            }
            XmlResourceParser xml = resources.getXml(i);
            String name = null;
            String text = null;
            String text2 = null;
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 2) {
                    name = xml.getName();
                } else if (eventType == 3) {
                    if (xml.getName().equals("entry")) {
                        if (text == null || text2 == null) {
                            Log.w("XmlParser", "An entry in the defaults XML has an invalid key and/or value tag.");
                        } else {
                            map.put(text, text2);
                        }
                        text = null;
                        text2 = null;
                    }
                    name = null;
                } else if (eventType == 4 && name != null) {
                    byte b2 = -1;
                    int iHashCode = name.hashCode();
                    if (iHashCode != 106079) {
                        if (iHashCode == 111972721 && name.equals("value")) {
                            b2 = 1;
                        }
                    } else if (name.equals("key")) {
                        b2 = 0;
                    }
                    if (b2 == 0) {
                        text = xml.getText();
                    } else if (b2 != 1) {
                        Log.w("XmlParser", "Encountered an unexpected tag while parsing the defaults XML.");
                    } else {
                        text2 = xml.getText();
                    }
                }
            }
            return map;
        } catch (IOException | XmlPullParserException e) {
            Log.e("XmlParser", "Encountered an error while parsing the defaults XML file.", e);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void O0(Context context, String str, String str2, Throwable th) {
        String string;
        try {
            if (h.f()) {
                return;
            }
            if (th == null) {
                string = "";
            } else {
                StringWriter stringWriter = new StringWriter();
                th.printStackTrace(new PrintWriter(stringWriter));
                string = stringWriter.toString();
            }
            String strM = M(context);
            int iL = L(context);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("pkg", context.getPackageName());
            jSONObject.put("vc", iL);
            jSONObject.put("vn", strM);
            jSONObject.put("es", string);
            if (th != null) {
                jSONObject.put("msg", th.getMessage());
            }
            jSONObject.put("ds", str2);
            if (str2 == null) {
                str2 = "";
            }
            if (i.f154a) {
                Log.e(i.a(str), String.format("%s: %s", str2, string));
            }
            f.a(context).b(new LogEvent(context, BuildConfig.APPLICATION_ID, "analytics_exception", jSONObject.toString()));
        } catch (Throwable th2) {
            i.d(i.a("TrackUtils"), "trackException exception:", th2);
        }
    }

    public static String P(Context context, Boolean bool) {
        if (TextUtils.isEmpty(f3b)) {
            return (String) A0(new c(context, bool), bool);
        }
        StringBuilder sbC = a.a.a.a.a.c("getFid, fid get from memory ");
        sbC.append(f3b);
        t0(bool, sbC.toString());
        return f3b;
    }

    public static void P0(Context context, String str, String str2) {
        try {
            if (h.f()) {
                return;
            }
            int iL = L(context);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("pkg", context.getPackageName());
            jSONObject.put("vc", iL);
            jSONObject.put("ds", str);
            jSONObject.put("msg", str2);
            f.a(context).b(new LogEvent(context, BuildConfig.APPLICATION_ID, "analytics_exception", jSONObject.toString()));
        } catch (Exception e) {
            i.d(i.a("TrackUtils"), "trackMessage exception:", e);
        }
    }

    public static String Q(Context context, boolean z) {
        if (TextUtils.isEmpty(f3b)) {
            return (String) B0(new a.b.a.f.h.f(context, z), z);
        }
        p.o("SecurityDeviceUtil", "getFidForNonce, fid get from memory ");
        n(z, "SecurityDeviceUtil", "getFidForNonce, fid get from memory " + f3b, false);
        return f3b;
    }

    public static String Q0(byte[] bArr) {
        try {
            return new String(bArr, "UTF-8");
        } catch (Exception e) {
            p.g("StringUtil", e.getMessage());
            return "";
        }
    }

    public static String R(Context context) throws InterruptedException, SecurityDeviceCredentialManager.OperationFailedException, RemoteException {
        StringBuilder sb;
        String str;
        if (TextUtils.isEmpty(f3b)) {
            f3b = a0(context);
            sb = new StringBuilder();
            str = "getFidOrThrow, fid get from binder : ";
        } else {
            sb = new StringBuilder();
            str = "getFidOrThrow, fid get from memory ";
        }
        sb.append(str);
        sb.append(f3b);
        p.c("SecurityDeviceUtil", sb.toString());
        return f3b;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void R0(long j) {
        long jF = (v.f(0) * 100) + (j > 0 ? j - 1 : 0L);
        p.c("CaughtExceptionUtil", "updateTodayTicketCount->ticket: " + jF);
        u.a();
        u.l("report_caught_exception_ticket", jF);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static String S(PackageManager packageManager, String str) {
        if (packageManager == null || !w0(str)) {
            return null;
        }
        if (!r.q() || Build.VERSION.SDK_INT < 30) {
            try {
                return packageManager.getInstallerPackageName(str);
            } catch (Exception e) {
                if (!p.f602a) {
                    return null;
                }
                a.a.a.a.a.g(e, a.a.a.a.a.c("getInstallerPackageName error: "), "InstallSourceUtil");
                return null;
            }
        }
        try {
            String installerPackageName = packageManager.getInstallerPackageName(str);
            Object objInvoke = packageManager.getClass().getMethod("getInstallSourceInfo", String.class).invoke(packageManager, str);
            String str2 = (String) objInvoke.getClass().getMethod("getInitiatingPackageName", new Class[0]).invoke(objInvoke, new Object[0]);
            return (w0(installerPackageName) && w0(str2) && installerPackageName.equals("com.android.vending") && !installerPackageName.equals(str2)) ? str2 : installerPackageName;
        } catch (Exception e2) {
            if (!p.f602a) {
                return null;
            }
            a.a.a.a.a.f(e2, a.a.a.a.a.c("getInstallSource error: "), "InstallSourceUtil");
            return null;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void S0(long j) {
        long jF = (v.f(0) * 100) + (j > 0 ? j - 1 : 0L);
        p.c("CrashUtil", "updateTodayTicketCount->ticket: " + jF);
        u.a();
        u.f617b.putLong("report_crash_ticket", jF).commit();
    }

    public static int T(String str, Integer num) {
        try {
            return ((Integer) Class.forName("android.os.SystemProperties").getMethod("getInt", String.class, Integer.TYPE).invoke(null, str, num)).intValue();
        } catch (Exception e) {
            Log.e(p.a("SystemProperties"), "getInt e", e);
            return num.intValue();
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static boolean T0(a.b.a.f.f.b bVar, String str, String str2, Map<String, String> map) {
        boolean zBooleanValue = false;
        if (bVar == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || map == null) {
            p.g("PollingUploader", "uploadAddDbFailedMessage get null or empty params");
            return false;
        }
        if (r.w("PollingUploader")) {
            p.c("PollingUploader", "uploadAddDbFailedMessage, but the device is not provisioned, stop poll!");
            return false;
        }
        if (!m0()) {
            p.c("PollingUploader", "uploadAddDbFailedMessage, but network is unconnected, stop poll!");
            return false;
        }
        if (!a.b.a.f.a.b()) {
            return false;
        }
        FutureTask futureTask = new FutureTask(new a.b.a.f.g.a(bVar, str, str2, map));
        ThreadPoolExecutor threadPoolExecutor = a.b.a.e.l0.g.f580a;
        try {
            a.b.a.e.l0.g.f580a.execute(futureTask);
        } catch (Throwable unused) {
        }
        try {
            zBooleanValue = ((Boolean) futureTask.get(10L, TimeUnit.SECONDS)).booleanValue();
        } catch (Exception e) {
            p.h("PollingUploader", "uploadAddDbFailedMessage get Exception, ", e);
        }
        p.c("PollingUploader", "uploadAddDbFailedMessage end to upload data of project 【" + str + "】, result: " + zBooleanValue);
        return zBooleanValue;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static String U(Context context) {
        return context.getDir("analytics", 0).getAbsolutePath() + "/analytics.apk";
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static byte[] U0(String str) throws Throwable {
        GZIPOutputStream gZIPOutputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        byte[] byteArray = null;
        try {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream(str.getBytes("UTF-8").length);
                try {
                    gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                    try {
                        gZIPOutputStream.write(str.getBytes("UTF-8"));
                        gZIPOutputStream.finish();
                        byteArray = byteArrayOutputStream.toByteArray();
                    } catch (Exception e) {
                        e = e;
                        Log.e(p.a("IOUtil"), " zipData failed!, e: ", e);
                        y0("up", "s6", e);
                    }
                } catch (Exception e2) {
                    e = e2;
                    gZIPOutputStream = null;
                } catch (Throwable th) {
                    th = th;
                    gZIPOutputStream = null;
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    g(byteArrayOutputStream2);
                    g(gZIPOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e3) {
            e = e3;
            byteArrayOutputStream = null;
            gZIPOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            gZIPOutputStream = null;
            g(byteArrayOutputStream2);
            g(gZIPOutputStream);
            throw th;
        }
        g(byteArrayOutputStream);
        g(gZIPOutputStream);
        return byteArray;
    }

    public static a.b.a.d.b.d V(boolean z) {
        return z ? a.b.a.d.b.c(a.b.a.e.e.b()) : a.b.a.d.b.a(a.b.a.e.e.b());
    }

    public static String W(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                if (activeNetworkInfo.getType() == 1) {
                    return "WIFI";
                }
                if (activeNetworkInfo.getType() == 0) {
                    switch (activeNetworkInfo.getSubtype()) {
                        case 1:
                        case 2:
                        case 4:
                        case ICore.Stub.TRANSACTION_setDefaultPolicy /* 7 */:
                        case 11:
                        case 16:
                            return "2G";
                        case 3:
                        case ICore.Stub.TRANSACTION_trackEvent /* 5 */:
                        case ICore.Stub.TRANSACTION_trackEvents /* 6 */:
                        case ICore.Stub.TRANSACTION_isPolicyReady /* 8 */:
                        case ICore.Stub.TRANSACTION_deleteAllEvents /* 9 */:
                        case 10:
                        case 12:
                        case 14:
                        case 15:
                        case 17:
                            return "3G";
                        case 13:
                        case 18:
                        case 19:
                            return "4G";
                        case 20:
                            return "5G";
                        default:
                            return "UNKNOWN";
                    }
                }
                return "UNKNOWN";
            }
            return "NONE";
        } catch (Exception e) {
            p.h("NetworkUtil", "getNetworkState error", e);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static String X(String str, boolean z) {
        HashMap map = new HashMap();
        map.put("f", str);
        map.put("v", "1.0");
        try {
            a.b.a.e.i0.c cVarN = a.b.a.e.i0.c.n();
            ConcurrentHashMap<String, String> concurrentHashMap = y.f663c;
            y yVar = y.b.f667a;
            String strJ = cVarN.j(yVar.a("https://", yVar.d(), "/track/getNonce"), map, true);
            n(z, "NonceManager", "getNonce response : " + strJ, true);
            return x0(strJ);
        } catch (Exception e) {
            p.h("NonceManager", "getNonce failed, e: ", e);
            w(z, "NonceManager", "getNonce failed, e: ", e);
            y0("up", "s14", e);
            return null;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX WARN: Code duplicated, block: B:20:0x0071 A[Catch: Exception -> 0x00ae, PHI: r7
      0x0071: PHI (r7v3 java.lang.String) = (r7v2 java.lang.String), (r7v11 java.lang.String) binds: [B:22:0x007c, B:18:0x006e] A[DONT_GENERATE, DONT_INLINE], TryCatch #1 {Exception -> 0x00ae, blocks: (B:3:0x0002, B:14:0x0056, B:17:0x005f, B:24:0x007f, B:20:0x0071, B:21:0x0074, B:12:0x002f, B:5:0x0017, B:7:0x001f, B:9:0x0026), top: B:30:0x0002, inners: #0 }] */
    public static JSONObject Y(LogEvent logEvent, boolean z) {
        String str;
        String str2;
        try {
            i.b("ServiceHelper", "getPayloadHeader->isBasicMode: " + z);
            JSONObject jSONObject = null;
            try {
                jSONObject = TextUtils.isEmpty(logEvent.h) ? new JSONObject() : new JSONObject(logEvent.h);
            } catch (Exception e) {
                Log.e(i.a("ServiceHelper"), "event:" + logEvent.toString());
                Log.e(i.a("ServiceHelper"), "getPayloadHeader e", e);
            }
            if (jSONObject == null) {
                jSONObject = new JSONObject();
            }
            if (z) {
                str = "ts";
                if (l0(logEvent.f1252c)) {
                    str2 = "";
                } else {
                    str2 = logEvent.g;
                }
            } else {
                jSONObject.put("sid", logEvent.f1253d);
                str = "sn";
                if (l0(logEvent.f1252c)) {
                    str2 = "";
                } else {
                    str2 = logEvent.g;
                }
            }
            jSONObject.put(str, str2);
            jSONObject.put("pk", logEvent.f1252c);
            jSONObject.put("key", logEvent.f1251b);
            jSONObject.put("eventTime", logEvent.e + "");
            jSONObject.put("retryCnt", logEvent.j);
            return jSONObject;
        } catch (Exception e2) {
            Log.e(i.a("ServiceHelper"), "getPayloadHeader exception: ", e2);
            return new JSONObject();
        }
    }

    public static int Z(Context context, String str) {
        int i = -1;
        try {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.processName.equals(str)) {
                    i = runningAppProcessInfo.pid;
                    break;
                }
            }
        } catch (Exception e) {
            i.d("AndroidUtils", "getPidByPackage exception ", e);
        }
        i.e("AndroidUtils", "getPidByPackage process name " + str + ", pid=" + i);
        return i;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void a(a.b.a.f.f.b bVar, String str, String str2) {
        if ("miui-analytics".equals(str) && str2.contains("onetrack_usage")) {
            try {
                JSONObject jSONObject = bVar.f.getJSONObject("B");
                a.b.a.f.c.c cVarI = a.b.a.f.c.b.e().i();
                if (cVarI != null && !TextUtils.isEmpty(cVarI.g)) {
                    jSONObject.put("pb_ip", cVarI.g);
                }
                p.c("PollingUploader", "addParamsToMessage, message: " + bVar.f.toString());
            } catch (Exception e) {
                StringBuilder sbC = a.a.a.a.a.c("addParamsToMessage error : ");
                sbC.append(e.getMessage());
                p.g("PollingUploader", sbC.toString());
                y0("sa", "s7", e);
            }
        }
    }

    public static String a0(Context context) throws InterruptedException, SecurityDeviceCredentialManager.OperationFailedException, RemoteException {
        if (Build.VERSION.SDK_INT >= 34) {
            try {
                a.b.a.h.d dVar = a.b.a.h.d.f894a;
                return a.b.a.h.d.f894a.a();
            } catch (SecurityDeviceCredentialManager.OperationFailedException e) {
                if (e.errorCode != -101) {
                    throw e;
                }
            }
        }
        return b0(context);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static String b(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            int i2 = (bArr[i] >> 4) & 15;
            int i3 = bArr[i] & 15;
            sb.append(cArr[i2]);
            sb.append(cArr[i3]);
        }
        return sb.toString();
    }

    public static String b0(Context context) throws Throwable {
        SecurityDeviceCredentialAbility securityDeviceCredentialAbility = null;
        try {
            SecurityDeviceCredentialAbility securityDeviceCredentialAbility2 = new SecurityDeviceCredentialAbility(context);
            try {
                String securityDeviceId = securityDeviceCredentialAbility2.getSecurityDeviceId();
                securityDeviceCredentialAbility2.release();
                return securityDeviceId;
            } catch (Throwable th) {
                th = th;
                securityDeviceCredentialAbility = securityDeviceCredentialAbility2;
                if (securityDeviceCredentialAbility != null) {
                    securityDeviceCredentialAbility.release();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String c(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            while (hexString.length() < 2) {
                hexString = a.a.a.a.a.a("0", hexString);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static String c0(Context context, String str) {
        File file = new File(context.getFilesDir(), "shared");
        if (!file.exists()) {
            file.mkdirs();
        }
        file.setReadable(true, false);
        file.setExecutable(true, false);
        File file2 = new File(N0(file.getPath()), str);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        file2.setReadable(true, false);
        file2.setExecutable(true, false);
        return N0(file2.getPath());
    }

    public static Object d(Object obj, String str, Class<?>[] clsArr, Object... objArr) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        return obj.getClass().getDeclaredMethod(str, null).invoke(obj, objArr);
    }

    public static Signature[] d0(Context context, File file) {
        try {
            return context.getPackageManager().getPackageArchiveInfo(file.getPath(), 64).signatures;
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean e(Context context) throws Throwable {
        if (Build.VERSION.SDK_INT >= 34) {
            a.b.a.h.d dVar = a.b.a.h.d.f894a;
            return a.b.a.h.d.f894a.b();
        }
        SecurityDeviceCredentialAbility securityDeviceCredentialAbility = null;
        try {
            SecurityDeviceCredentialAbility securityDeviceCredentialAbility2 = new SecurityDeviceCredentialAbility(context);
            try {
                boolean zIsThisDeviceSupported = securityDeviceCredentialAbility2.isThisDeviceSupported();
                securityDeviceCredentialAbility2.release();
                return zIsThisDeviceSupported;
            } catch (Throwable th) {
                th = th;
                securityDeviceCredentialAbility = securityDeviceCredentialAbility2;
                if (securityDeviceCredentialAbility != null) {
                    securityDeviceCredentialAbility.release();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static long e0() {
        long jG = u.g("report_caught_exception_ticket", 0L);
        if (jG == 0) {
            p.c("CaughtExceptionUtil", "no ticket data found, return max count");
            return 20L;
        }
        long jF = v.f(0);
        if (jG / 100 != jF) {
            p.c("CaughtExceptionUtil", "no today's ticket, return max count");
            u.a();
            u.m("caught_exception_statistics", "");
            return 20L;
        }
        Long.signum(jF);
        long j = jG - (jF * 100);
        p.c("CaughtExceptionUtil", "today's remain ticket is " + j);
        return j;
    }

    public static void f(Context context) {
        try {
            if (h0(context, "android.permission.UPDATE_DEVICE_STATS")) {
                i.b("AndroidUtils", "has setThreadStatsUid permission");
                Class.forName("android.net.TrafficStats").getMethod("clearThreadStatsUid", new Class[0]).invoke(null, new Object[0]);
            } else {
                i.b("AndroidUtils", "not has setThreadStatsUid permission");
            }
        } catch (Exception e) {
            Log.e(i.a("AndroidUtils"), "clearThreadStatsUid e", e);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static long f0() {
        String str;
        long jG = u.g("report_crash_ticket", 0L);
        if (jG == 0) {
            str = "no ticket data found, return max count";
        } else {
            long jF = v.f(0);
            if (jG / 100 == jF) {
                Long.signum(jF);
                long j = jG - (jF * 100);
                p.c("CrashUtil", "today's remain ticket is " + j);
                return j;
            }
            str = "no today's ticket, return max count";
        }
        p.c("CrashUtil", str);
        return 10L;
    }

    public static void g(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
                Log.e(p.a("IOUtil"), "closeQuietly e: ", th);
            }
        }
    }

    public static int g0(String str) {
        if (str == null) {
            return 0;
        }
        switch (str) {
            case "pi_install":
                return 15;
            case "update":
                return 12;
            case "uninstall":
                return 13;
            case "first_launch":
                return 14;
            case "download":
                return 10;
            case "install":
                return 11;
            default:
                return 0;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void h(c0 c0Var) {
        if (c0Var == null) {
            return;
        }
        try {
            e0 e0Var = c0Var.g;
            if (e0Var != null) {
                e0Var.close();
            }
        } catch (Throwable th) {
            a.a.a.a.a.j(th, a.a.a.a.a.c("closeQuietly response Throwable:"), "IOUtil");
        }
    }

    public static boolean h0(Context context, String str) {
        try {
            return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        } catch (Exception unused) {
        }
    }

    public static void i(Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Throwable th) {
                Log.e(p.a("IOUtil"), "closeSafely e: ", th);
            }
        }
    }

    public static byte[] i0(String str) {
        if (str == null || str.length() < 1) {
            return null;
        }
        byte[] bArr = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            int i2 = i * 2;
            int i3 = i2 + 1;
            bArr[i] = (byte) ((Integer.parseInt(str.substring(i2, i3), 16) * 16) + Integer.parseInt(str.substring(i3, i2 + 2), 16));
        }
        return bArr;
    }

    public static void j(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                Log.e(i.a("IOUtils"), "closeSafely e", e);
            }
        }
    }

    public static boolean j0() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static JSONObject k(Context context, Boolean bool, int i, int i2, String str, boolean z) {
        String strA;
        String str2;
        List<String> list;
        String strD;
        LogEvent.LogType logType = LogEvent.LogType.TYPE_AD;
        JSONObject jSONObject = new JSONObject();
        try {
            try {
                jSONObject.put("model", Build.MODEL);
                jSONObject.put("android", Build.VERSION.RELEASE);
                jSONObject.put("miui", Build.VERSION.INCREMENTAL);
                jSONObject.put("bn", n.l());
                jSONObject.put("product", Build.PRODUCT);
                jSONObject.put("device", Build.DEVICE);
                jSONObject.put("cv", Analytics.getVersionName());
                jSONObject.put("lang", n.j());
                jSONObject.put("region", n.m());
                jSONObject.put("mi", h.f() ? "1" : "0");
                jSONObject.put("sender", context.getPackageName());
                jSONObject.put("isBasicMode", z);
                i.b("ServiceHelper", "composeTrackingHeaders->isBasicMode: " + z);
                if (!z) {
                    jSONObject.put("userid", n.o());
                    String strD2 = "";
                    jSONObject.put("oa", l0(str) ? "" : a.b.a.c.p.b.a.a(context));
                    jSONObject.put("n", String.valueOf(a.b.a.c.v.k.b(context)));
                    LogEvent.IdType idType = LogEvent.IdType.TYPE_DEFAULT;
                    switch (i2) {
                        case 1:
                            idType = LogEvent.IdType.TYPE_IMEI;
                            break;
                        case 2:
                            idType = LogEvent.IdType.TYPE_MAC;
                            break;
                        case 3:
                            idType = LogEvent.IdType.TYPE_ANDROID_ID;
                            break;
                        case 4:
                            idType = LogEvent.IdType.TYPE_AAID;
                            break;
                        case ICore.Stub.TRANSACTION_trackEvent /* 5 */:
                            idType = LogEvent.IdType.TYPE_GAID;
                            break;
                        case ICore.Stub.TRANSACTION_trackEvents /* 6 */:
                            idType = LogEvent.IdType.TYPE_GUID;
                            break;
                    }
                    switch (idType.ordinal()) {
                        case 0:
                            if (h.g() && (!h.f() || i != logType.f1261a)) {
                                if (!l0(str)) {
                                    strD2 = h.d(context);
                                }
                                jSONObject.put("aaid", strD2);
                            }
                            if (!h.f() && (bool.booleanValue() || TextUtils.isEmpty(strD2))) {
                                jSONObject.put("mac", n.h(context));
                                jSONObject.put("imei", n.g(context));
                            }
                            if (h.f() && i != logType.f1261a && TextUtils.isEmpty(strD2)) {
                                strD = n.d(context);
                                jSONObject.put("x", strD);
                                break;
                            }
                            return jSONObject;
                        case 1:
                            jSONObject.put("imei", n.g(context));
                            break;
                        case 2:
                            jSONObject.put("mac", n.h(context));
                            break;
                        case 3:
                            try {
                                strD2 = Settings.Secure.getString(context.getContentResolver(), "android_id");
                            } catch (Exception e) {
                                i.d("SysUtils", "getAndroidId Exception: ", e);
                            }
                            jSONObject.put("y", strD2);
                            break;
                        case 4:
                            jSONObject.put("aaid", h.d(context));
                            break;
                        case ICore.Stub.TRANSACTION_trackEvent /* 5 */:
                            strD = n.d(context);
                            jSONObject.put("x", strD);
                            break;
                        case ICore.Stub.TRANSACTION_trackEvents /* 6 */:
                            jSONObject.put("z", n.f(context, str));
                            break;
                    }
                }
                a.b.a.c.t.a aVarF = a.b.a.c.t.g.g(context).f(context.getPackageName());
                if (aVarF != null && (list = aVarF.f96b) != null) {
                    for (String str3 : list) {
                        if (jSONObject.has(str3)) {
                            jSONObject.remove(str3);
                        }
                    }
                }
            } catch (AssertionError e2) {
                e = e2;
                strA = i.a("ServiceHelper");
                str2 = "TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT) Error: ";
                Log.e(strA, str2, e);
            }
        } catch (Exception e3) {
            e = e3;
            strA = i.a("ServiceHelper");
            str2 = "composeTrackingHeaders exception: ";
            Log.e(strA, str2, e);
        }
        return jSONObject;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static byte[] k0(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                byteArrayOutputStream.close();
                inputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
    }

    public static LogEvent l(Context context, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("v");
            if (i == 1) {
                return C(context, jSONObject);
            }
            if (i == 2) {
                i.b("LogEventCreator", "json version is 2");
                return D(context, jSONObject);
            }
            Log.e(i.a("LogEventCreator"), "should not be reached.");
            return null;
        } catch (Exception e) {
            Log.e(i.a("LogEventCreator"), "createFromJson e", e);
            return null;
        }
    }

    public static boolean l0(String str) {
        return h.f() && TextUtils.equals(str, BuildConfig.APPLICATION_ID);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void m(boolean z, String str, String str2) {
        p.c(str, str2);
        a.b.a.d.b.d dVarV = V(z);
        if (dVarV != null) {
            dVarV.a(Level.DEBUG, "##XLogger##, TAG: ", a.a.a.a.a.b(str, ": ", str2), null);
        }
    }

    @SuppressLint({"MissingPermission"})
    public static boolean m0() {
        Context contextB = a.b.a.e.e.b();
        if (contextB == null) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) contextB.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnectedOrConnecting();
            }
            return false;
        } catch (Exception unused) {
            p.c("NetworkUtil", "isNetworkConnected exception");
            return false;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void n(boolean z, String str, String str2, boolean z2) {
        a.b.a.d.b.d dVarC;
        Level level = Level.DEBUG;
        if (z2) {
            p.c(str, str2);
        } else {
            a.b.a.d.b.d dVarB = a.b.a.d.b.b(a.b.a.e.e.b());
            if (dVarB != null) {
                dVarB.a(level, "##XLogger##, TAG: ", a.a.a.a.a.b(str, ": ", str2), null);
            }
        }
        a.b.a.d.b.d dVarA = a.b.a.d.b.a(a.b.a.e.e.b());
        if (dVarA != null) {
            dVarA.a(level, "##XLogger##, TAG: ", a.a.a.a.a.b(str, ": ", str2), null);
        }
        if (!z || (dVarC = a.b.a.d.b.c(a.b.a.e.e.b())) == null) {
            return;
        }
        dVarC.a(level, "##XLogger##, TAG: ", a.a.a.a.a.b(str, ": ", str2), null);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static boolean n0(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (Exception unused) {
            packageInfo = null;
        }
        try {
            return (packageInfo.applicationInfo.flags & 1) != 0;
        } catch (Exception unused2) {
            return false;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static Object o(byte[] bArr) throws Throwable {
        ByteArrayInputStream byteArrayInputStream;
        Throwable th;
        ObjectInputStream objectInputStream;
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                objectInputStream = new ObjectInputStream(byteArrayInputStream);
                try {
                    try {
                        Object object = objectInputStream.readObject();
                        g(byteArrayInputStream);
                        g(objectInputStream);
                        return object;
                    } catch (Exception e) {
                        e = e;
                        p.g("DataConvertUtil", "deserialize error:" + e.getMessage());
                        g(byteArrayInputStream);
                        g(objectInputStream);
                        return null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    g(byteArrayInputStream);
                    g(objectInputStream);
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                objectInputStream = null;
            } catch (Throwable th3) {
                th = th3;
                objectInputStream = null;
                g(byteArrayInputStream);
                g(objectInputStream);
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            objectInputStream = null;
            byteArrayInputStream = null;
        } catch (Throwable th4) {
            byteArrayInputStream = null;
            th = th4;
            objectInputStream = null;
        }
    }

    public static boolean o0(Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                UserManager userManager = (UserManager) context.getSystemService("user");
                return userManager != null && userManager.isUserUnlocked();
            } catch (Exception e) {
                StringBuilder sbC = a.a.a.a.a.c("isUserUnlocked Exception: ");
                sbC.append(e.getMessage());
                p.o("FbeUtil", sbC.toString());
            }
        }
        return false;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static a.b.a.g.e.b p(a.b.a.e.f0.h hVar) {
        byte[] bArrE;
        boolean z;
        a.b.a.g.e.b bVar = new a.b.a.g.e.b(false, false);
        try {
            ConcurrentHashMap<String, String> concurrentHashMap = y.f663c;
            y yVar = y.b.f667a;
            Objects.requireNonNull(yVar);
            String strA = yVar.a("https://", !r.q() ? "om.tracking.miui.com" : yVar.d(), "/track/mt");
            byte[] bArr = hVar.f335a;
            if (bArr == null) {
                p.g("EventMonitorUploader", "content is null");
                bArrE = null;
            } else {
                int i = a.b.a.e.g0.e.e;
                bArrE = a.b.a.e.g0.a.e(bArr, a.b.a.e.g0.c.a(a.b.a.e.g0.e.b.f436a.a(false)[0]));
            }
            if (bArrE != null && bArrE.length != 0) {
                p.c("EventMonitorUploader", "before zip and encrypt, len=" + hVar.f335a.length + ", after=" + bArrE.length);
                String strL = a.b.a.e.i0.c.n().l(strA, bArrE, false);
                StringBuilder sb = new StringBuilder();
                sb.append("sendDataToServer response: ");
                sb.append(strL);
                p.c("EventMonitorUploader", sb.toString());
                if (TextUtils.isEmpty(strL)) {
                    return bVar;
                }
                if (new JSONObject(strL).optInt("code") == 0) {
                    p.c("EventMonitorUploader", "成功发送数据到服务端");
                    z = true;
                } else {
                    z = false;
                }
                return new a.b.a.g.e.b(z, false);
            }
            return bVar;
        } catch (Exception e) {
            p.h("EventMonitorUploader", "Exception while uploading ", e);
            y0("up", "s33", e);
            return bVar;
        }
    }

    public static String p0(List<String> list) {
        return list.size() == 0 ? "" : String.format("[%s]", TextUtils.join(",", list));
    }

    public static byte[] q(String str) throws Throwable {
        GZIPOutputStream gZIPOutputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream2 = null;
        try {
            try {
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    gZIPOutputStream.write(str.getBytes());
                    j(gZIPOutputStream);
                } catch (Exception e) {
                    e = e;
                    gZIPOutputStream2 = gZIPOutputStream;
                    Log.e(i.a("IOUtils"), "doZip e", e);
                    j(gZIPOutputStream2);
                } catch (Throwable th) {
                    th = th;
                    j(gZIPOutputStream);
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th2) {
            th = th2;
            gZIPOutputStream = gZIPOutputStream2;
        }
    }

    public static int q0(String str, Context context) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return context.getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), 1).versionCode;
            }
            return -1;
        } catch (Exception e) {
            Log.e(i.a("Client"), "loadAnalyticsVersionCode exception", e);
            return -1;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void r(String str, String str2, Throwable th) {
        boolean z = w.i;
        p.h(str, str2, th);
        w(z, str, str2, th);
    }

    public static String r0(String str, Context context) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return context.getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), 1).versionName;
            }
            return null;
        } catch (Exception e) {
            Log.e(i.a("Client"), "loadAnalyticsVersionName exception", e);
            return null;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void s(boolean z, String str, String str2, Throwable th) {
        p.h(str, str2, th);
        a.b.a.d.b.d dVarV = V(z);
        if (dVarV != null) {
            dVarV.a(Level.ERROR, "##XLogger##, TAG: ", a.a.a.a.a.b(str, ": ", str2), th);
        }
    }

    public static RSAPublicKey s0(String str) throws Exception {
        return (RSAPublicKey) (Build.VERSION.SDK_INT >= 28 ? KeyFactory.getInstance("RSA") : KeyFactory.getInstance("RSA", "BC")).generatePublic(new X509EncodedKeySpec(a.b.a.e.g0.c.a(str)));
    }

    public static byte[] t(byte[] bArr) {
        byte[] byteArray = null;
        try {
            RSAPublicKey rSAPublicKeyS0 = s0("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCiH0r18h2G+lOzZz0mSZT9liZY\r6ibWUv/biAioduf0zuRbWUYGb3pHobyCOaw2LpVnlf8CeCYtbRJhxL9skOyoU1Qa\rwGtoJzvVR4GbCo1MBTmZ8XThMprr0unRfzsu9GNV4+twciOdS2cNJB7INcwAYBFQ\r9vKpgXFoEjWRhIgwMwIDAQAB\r");
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
            cipher.init(1, rSAPublicKeyS0);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int length = bArr.length;
            int i = 0;
            while (true) {
                int i2 = length - i;
                if (i2 <= 0) {
                    byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    return byteArray;
                }
                int iMin = Math.min(117, i2);
                byte[] bArrDoFinal = cipher.doFinal(bArr, i, iMin);
                byteArrayOutputStream.write(bArrDoFinal, 0, bArrDoFinal.length);
                i += iMin;
            }
        } catch (Exception e) {
            p.h("RsaUtils", "RsaUtils encrypt exception:", e);
            y0("up", "s4", e);
            return byteArray;
        }
    }

    public static void t0(Boolean bool, String str) {
        if (bool == null) {
            p.c("SecurityDeviceUtil", str);
        } else {
            m(bool.booleanValue(), "SecurityDeviceUtil", str);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x005b: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:20:0x005b */
    public static byte[] u(byte[] bArr) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        Closeable closeable;
        Closeable closeable2 = null;
        byte[] byteArray = null;
        try {
            try {
                RSAPublicKey rSAPublicKeyS0 = s0("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs5T44kbJUWimJegeGtwhFqB/vTQG5RgFQ1Jru4X3vQVLOG0INiAskKU6dtGUNciQw4ZCYpPXrGHDk+4QmWWbhZ4etNqvwpOSeHZVKvRD4C1xnZHF+s3XN6IDB5bm4WLrjLex/vbL06Cr+3T/z7Pp6DTAzaJCMZXU3y7zi7JKn2Unp5AFVSRpjMISgPlgx5Sp5LTOqltRQuJkvY75RG4BaSZyjs9rnH2INtCXhtKhNewTRrkChzMgmNBvy0cjilUBBns6j/89HxDB93o1zkTJHjzxVUqqy36RfZG0otfZyMYo8nJa73lWSMdo7e4fRx1TZUvlKi39szzhQNo4Vk1rrQIDAQAB");
                Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding", "BC");
                cipher.init(1, rSAPublicKeyS0, new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT));
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    int length = bArr.length;
                    int i = 0;
                    while (true) {
                        int i2 = length - i;
                        if (i2 <= 0) {
                            break;
                        }
                        int iMin = Math.min(117, i2);
                        byte[] bArrDoFinal = cipher.doFinal(bArr, i, iMin);
                        byteArrayOutputStream.write(bArrDoFinal, 0, bArrDoFinal.length);
                        i += iMin;
                    }
                    byteArray = byteArrayOutputStream.toByteArray();
                } catch (Exception e) {
                    e = e;
                    p.h("RsaUtils", "RsaUtil encryptForMonitor exception:", e);
                    y0("up", "s4", e);
                }
            } catch (Throwable th) {
                th = th;
                closeable2 = closeable;
                g(closeable2);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            byteArrayOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            g(closeable2);
            throw th;
        }
        g(byteArrayOutputStream);
        return byteArray;
    }

    public static void u0(Boolean bool, String str, Exception exc) {
        if (bool == null) {
            p.h("SecurityDeviceUtil", str, exc);
        } else {
            s(bool.booleanValue(), "SecurityDeviceUtil", str, exc);
        }
    }

    public static void v(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase != null) {
            try {
                sQLiteDatabase.endTransaction();
            } catch (Throwable th) {
                Log.e(p.a("IOUtil"), "endTransactionSafely e: ", th);
            }
        }
    }

    public static Set<String> v0(String str, String str2, String str3) {
        HashSet hashSet = new HashSet();
        try {
            String[] strArrSplit = null;
            String[] strArrSplit2 = TextUtils.isEmpty(str) ? null : str.split(str3);
            if (strArrSplit2 != null && strArrSplit2.length > 0) {
                hashSet.addAll(Arrays.asList(strArrSplit2));
            }
            if (!TextUtils.isEmpty(str2)) {
                strArrSplit = str2.split(str3);
            }
            if (strArrSplit != null && strArrSplit.length > 0) {
                hashSet.addAll(Arrays.asList(strArrSplit));
            }
        } catch (Exception e) {
            a.a.a.a.a.g(e, a.a.a.a.a.c("mergeParams error: "), "StringUtil");
        }
        return hashSet;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void w(boolean z, String str, String str2, Throwable th) {
        a.b.a.d.b.d dVarC;
        Level level = Level.ERROR;
        a.b.a.d.b.d dVarA = a.b.a.d.b.a(a.b.a.e.e.b());
        if (dVarA != null) {
            dVarA.a(level, "##XLogger##, TAG: ", a.a.a.a.a.b(str, ": ", str2), th);
        }
        if (!z || (dVarC = a.b.a.d.b.c(a.b.a.e.e.b())) == null) {
            return;
        }
        dVarC.a(level, "##XLogger##, TAG: ", a.a.a.a.a.b(str, ": ", str2), th);
    }

    public static boolean w0(String str) {
        return (str == null || str.length() == 0 || str.equals("") || str.equals("null")) ? false : true;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void x(Runnable runnable) {
        ThreadPoolExecutor threadPoolExecutor = a.b.a.e.l0.g.f580a;
        try {
            a.b.a.e.l0.g.f580a.execute(runnable);
        } catch (Throwable unused) {
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static String x0(String str) throws Exception {
        boolean z;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        int iOptInt = jSONObject.has("code") ? jSONObject.optInt("code") : -1;
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
        if (iOptInt != 0 || jSONObjectOptJSONObject == null || !jSONObjectOptJSONObject.has("t") || !jSONObjectOptJSONObject.has("n")) {
            return null;
        }
        long jOptLong = jSONObjectOptJSONObject.optLong("t");
        if (jOptLong <= 0) {
            return null;
        }
        long jG = u.g("first_get_nonce_server_time_v2", 0L);
        if (jG != 0) {
            z = jOptLong - jG <= 10368000000L;
            return jSONObjectOptJSONObject.optString("n");
        }
        u.l("first_get_nonce_server_time_v2", jOptLong);
        u.k("is_get_nonce_v2", z);
        return jSONObjectOptJSONObject.optString("n");
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void y(Runnable runnable) {
        ThreadPoolExecutor threadPoolExecutor = a.b.a.e.l0.g.f580a;
        try {
            a.b.a.e.l0.g.f580a.execute(runnable);
        } catch (Throwable unused) {
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void y0(String str, String str2, Throwable th) {
        p.c("MonitorDBExecutor", "addExceptionCount, phase: " + str + ", situation: " + str2 + ", count: 1");
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            p.c("MonitorDBExecutor", "addExceptionCount, phase is empty or situation is empty or count is non-positive. skip it");
        } else {
            a.b.a.g.c.c.h(new a.b.a.g.c.a(str, str2, 1));
        }
        TrackThreadManager.f().e(new a.b.a.e.i(str, str2, th));
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void z(Runnable runnable) {
        ThreadPoolExecutor threadPoolExecutor = a.b.a.e.l0.g.f580a;
        try {
            a.b.a.e.l0.g.f580a.execute(runnable);
        } catch (Throwable unused) {
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void z0(int i) {
        String strD = u.d();
        if (TextUtils.isEmpty(strD)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(strD);
            if (jSONObject.remove(String.valueOf(i)) != null) {
                u.m("daily_crash_count", jSONObject.length() == 0 ? "" : jSONObject.toString());
            }
        } catch (Exception e) {
            p.t("CrashUtil", "removeCrashCount failed,", e);
        }
    }
}
