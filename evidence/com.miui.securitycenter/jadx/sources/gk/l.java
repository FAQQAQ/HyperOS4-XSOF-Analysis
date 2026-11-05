package gk;

import aj.y;
import android.app.Application;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.google.common.net.HttpHeaders;
import com.miui.common.utils.GsonUtils;
import com.miui.common.utils.f2;
import com.miui.common.utils.h0;
import com.miui.common.utils.v1;
import com.miui.hybrid.accessory.utils.network.Network;
import com.miui.luckymoney.model.message.Impl.QQMessage;
import com.miui.maml.folme.AnimatedProperty;
import com.miui.networkassistant.vpn.miui.dedicatednet.DedicatedNetConstants;
import com.miui.securitykey.ConfigManager;
import com.miui.warningcenter.caraccident.utils.NetworkUtils;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import miui.cloud.CloudPushConstants;
import miui.os.Build;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public abstract class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final boolean f31400a = uj.a.f43927a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f31401b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f31402c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f31403d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f31404e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final String f31405f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final String f31406g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final String f31407h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final HashMap f31408i;

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f31409a;

        static {
            int[] iArr = new int[b.values().length];
            f31409a = iArr;
            try {
                iArr[b.GET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f31409a[b.DELETE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f31409a[b.POST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f31409a[b.PUT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public enum b {
        GET,
        POST,
        PUT,
        DELETE
    }

    private static class c implements Comparable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f31415a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f31416b;

        public c(String str, String str2) {
            this.f31415a = str;
            this.f31416b = str2;
        }

        /* JADX DEBUG: Method merged with bridge method: compareTo(Ljava/lang/Object;)I */
        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public int compareTo(c cVar) {
            return this.f31415a.compareTo(cVar.f31415a);
        }
    }

    static {
        String strC = f2.c("ro.product.device", "unknown");
        f31401b = strC;
        String strC2 = f2.c("ro.carrier.name", "unknown");
        f31402c = strC2;
        String region = Build.getRegion();
        f31403d = region;
        String str = android.os.Build.VERSION.INCREMENTAL;
        f31404e = str;
        String strC3 = f2.c("ro.miui.ui.version.name", "unknown");
        f31405f = strC3;
        String str2 = android.os.Build.VERSION.RELEASE;
        f31406g = str2;
        String str3 = android.os.Build.MODEL;
        f31407h = str3;
        HashMap map = new HashMap();
        f31408i = map;
        map.put("d", strC);
        map.put("c", strC2);
        map.put("r", region);
        map.put("v", str);
        map.put("vn", strC3);
        map.put(com.xiaomi.onetrack.b.l.f27684g, str2);
        map.put("t", d.d());
        map.put("si", String.valueOf(android.os.Build.VERSION.SDK_INT));
        map.put("mo", str3);
        Application applicationD = com.miui.common.e.d();
        if (!Build.IS_INTERNATIONAL_BUILD) {
            boolean zV = v();
            map.put("m", !zV ? os.a.d(js.a.b(d.c(applicationD), "MD5")) : "");
            map.put("ri", String.valueOf(zV));
        }
        map.put("o", d.h(applicationD));
        try {
            map.put("e", String.valueOf(applicationD.getPackageManager().getPackageInfo(applicationD.getPackageName(), 0).versionCode));
        } catch (PackageManager.NameNotFoundException e10) {
            e10.printStackTrace();
        }
    }

    public static String A(String str, j7.i iVar) {
        return B(null, str, iVar);
    }

    public static String B(Map map, String str, j7.i iVar) {
        return N(map, str, b.POST, "2dcd9s0c-ad3f-2fas-0l3a-abzo301jd0s9", false, true, iVar);
    }

    public static String C(Map map, String str, String str2, j7.i iVar) {
        return N(map, str, b.POST, str2, false, true, iVar);
    }

    public static String D(Map map, String str, String str2, boolean z10, boolean z11, j7.i iVar) {
        return N(map, str, b.POST, str2, z10, z11, iVar);
    }

    public static String E(String str) {
        return N(s(str), "https://api.sec.miui.com/mnntrain/taskdata", b.POST, "5cdd8678-cddf-4269-ab73-48017445bba3", true, false, new j7.i("ai_request_task"));
    }

    public static String F(String str, JSONObject jSONObject) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        DataOutputStream dataOutputStream;
        InputStream inputStream;
        ByteArrayOutputStream byteArrayOutputStream2;
        if (!v1.b()) {
            return "";
        }
        InputStream inputStream2 = null;
        try {
            try {
                boolean z10 = f31400a;
                if (z10) {
                    Log.d("NetUtil", "request start : " + str);
                }
                HttpURLConnection httpURLConnectionZ = z(new URL(str));
                httpURLConnectionZ.setRequestMethod(NetworkUtils.REQUEST_METHOD_POST);
                String string = jSONObject.toString();
                if (z10) {
                    Log.d("NetUtil", "body : " + string);
                }
                byte[] bytes = !TextUtils.isEmpty(string) ? string.getBytes() : null;
                if (bytes != null) {
                    httpURLConnectionZ.addRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
                }
                dataOutputStream = new DataOutputStream(httpURLConnectionZ.getOutputStream());
                try {
                    dataOutputStream.write(bytes);
                    int responseCode = httpURLConnectionZ.getResponseCode();
                    if (z10) {
                        Log.d("NetUtil", "responseCode : " + responseCode);
                    }
                    if (responseCode != 200) {
                        e(null);
                        e(dataOutputStream);
                        e(null);
                        return null;
                    }
                    InputStream inputStream3 = httpURLConnectionZ.getInputStream();
                    try {
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        try {
                            byte[] bArr = new byte[4096];
                            while (true) {
                                int i10 = inputStream3.read(bArr);
                                if (i10 == -1) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr, 0, i10);
                            }
                            String string2 = byteArrayOutputStream.toString();
                            if (f31400a) {
                                Log.d("NetUtil", "request result  : " + string2);
                            }
                            e(inputStream3);
                            e(dataOutputStream);
                            e(byteArrayOutputStream);
                            return string2;
                        } catch (Exception e10) {
                            inputStream = inputStream3;
                            e = e10;
                            byteArrayOutputStream2 = byteArrayOutputStream;
                        } catch (Throwable th2) {
                            inputStream2 = inputStream3;
                            th = th2;
                            e(inputStream2);
                            e(dataOutputStream);
                            e(byteArrayOutputStream);
                            throw th;
                        }
                    } catch (Exception e11) {
                        inputStream = inputStream3;
                        e = e11;
                        byteArrayOutputStream2 = null;
                    } catch (Throwable th3) {
                        inputStream2 = inputStream3;
                        th = th3;
                        byteArrayOutputStream = null;
                    }
                } catch (Exception e12) {
                    e = e12;
                    inputStream = null;
                    byteArrayOutputStream2 = null;
                } catch (Throwable th4) {
                    th = th4;
                    byteArrayOutputStream = null;
                }
            } catch (Exception e13) {
                e = e13;
                inputStream = null;
                byteArrayOutputStream2 = null;
                dataOutputStream = null;
            } catch (Throwable th5) {
                th = th5;
                byteArrayOutputStream = null;
                dataOutputStream = null;
            }
            Log.e("NetUtil", "postAdvTailData failed ", e);
            e(inputStream);
            e(dataOutputStream);
            e(byteArrayOutputStream2);
            return null;
        } catch (Throwable th6) {
            th = th6;
            inputStream2 = inputStream;
            byteArrayOutputStream = byteArrayOutputStream2;
            e(inputStream2);
            e(dataOutputStream);
            e(byteArrayOutputStream);
            throw th;
        }
    }

    public static String G(Map map, String str) {
        return H(map, str, true);
    }

    public static String H(Map map, String str, boolean z10) {
        return I(map, str, z10, false);
    }

    public static String I(Map map, String str, boolean z10, boolean z11) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        InputStream inputStream;
        ByteArrayOutputStream byteArrayOutputStream2;
        if (!v1.b()) {
            return "";
        }
        InputStream inputStream2 = null;
        try {
            try {
                boolean z12 = f31400a;
                if (z12) {
                    Log.d("NetUtil", "request start : " + str);
                }
                HttpURLConnection httpURLConnectionZ = z(new URL(str));
                String strA = z11 ? m.a(map, "2dcd9s0c-ad3f-2fas-0l3a-abzo301jd0s9") : q(map, "2dcd9s0c-ad3f-2fas-0l3a-abzo301jd0s9");
                map.put("sign", strA);
                String strB = GsonUtils.b(map);
                if (z12) {
                    Log.d("NetUtil", "jsonBodyStr : " + strB);
                }
                if (z10) {
                    httpURLConnectionZ.setRequestProperty("sign", strA);
                }
                c(httpURLConnectionZ, strB);
                int responseCode = httpURLConnectionZ.getResponseCode();
                if (z12) {
                    Log.d("NetUtil", " responseCode :  " + responseCode);
                }
                if (responseCode != 200) {
                    e(null);
                    e(null);
                    return null;
                }
                InputStream inputStream3 = httpURLConnectionZ.getInputStream();
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        byte[] bArr = new byte[4096];
                        while (true) {
                            int i10 = inputStream3.read(bArr);
                            if (i10 == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, i10);
                        }
                        String string = byteArrayOutputStream.toString();
                        if (f31400a) {
                            Log.d("NetUtil", "request result  : " + string);
                        }
                        e(inputStream3);
                        e(byteArrayOutputStream);
                        return string;
                    } catch (Exception e10) {
                        inputStream = inputStream3;
                        e = e10;
                        byteArrayOutputStream2 = byteArrayOutputStream;
                    } catch (Throwable th2) {
                        inputStream2 = inputStream3;
                        th = th2;
                        e(inputStream2);
                        e(byteArrayOutputStream);
                        throw th;
                    }
                } catch (Exception e11) {
                    inputStream = inputStream3;
                    e = e11;
                    byteArrayOutputStream2 = null;
                } catch (Throwable th3) {
                    inputStream2 = inputStream3;
                    th = th3;
                    byteArrayOutputStream = null;
                }
            } catch (Exception e12) {
                e = e12;
                inputStream = null;
                byteArrayOutputStream2 = null;
            } catch (Throwable th4) {
                th = th4;
                byteArrayOutputStream = null;
            }
            e.printStackTrace();
            e(inputStream);
            e(byteArrayOutputStream2);
            return null;
        } catch (Throwable th5) {
            th = th5;
            inputStream2 = inputStream;
            byteArrayOutputStream = byteArrayOutputStream2;
            e(inputStream2);
            e(byteArrayOutputStream);
            throw th;
        }
    }

    public static String J(String str, j7.i iVar) {
        return L(str, b.POST, "2dcd9s0c-ad3f-2fas-0l3a-abzo301jd0s9", iVar);
    }

    private static void K(InputStream inputStream, ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        byte[] bArr = new byte[4096];
        while (true) {
            int i10 = inputStream.read(bArr);
            if (i10 == -1) {
                return;
            } else {
                byteArrayOutputStream.write(bArr, 0, i10);
            }
        }
    }

    public static String L(String str, b bVar, String str2, j7.i iVar) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        if (!v1.b()) {
            return "";
        }
        int i10 = -1;
        InputStream inputStream = null;
        try {
            HashMap map = new HashMap();
            map.put("u", UUID.randomUUID().toString().replace("-", ""));
            map.put("sign", q(map, str2));
            if (bVar == b.GET) {
                String strK = k(map);
                str = str.contains("?") ? str.concat(strK) : str.concat("?").concat(strK);
            }
            boolean z10 = f31400a;
            if (z10) {
                Log.d("NetUtil", "request start : " + str);
            }
            HttpURLConnection httpURLConnectionZ = z(new URL(str));
            Q(httpURLConnectionZ, bVar, map);
            int responseCode = httpURLConnectionZ.getResponseCode();
            if (z10) {
                try {
                    Log.d("NetUtil", " responseCode :  " + responseCode);
                } catch (Exception e10) {
                    e = e10;
                    i10 = responseCode;
                    byteArrayOutputStream = null;
                    try {
                        e.printStackTrace();
                        j7.h.a(iVar, i10, 0);
                        e(inputStream);
                        e(byteArrayOutputStream);
                        return "";
                    } catch (Throwable th2) {
                        th = th2;
                        j7.h.a(iVar, i10, 0);
                        e(inputStream);
                        e(byteArrayOutputStream);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    i10 = responseCode;
                    byteArrayOutputStream = null;
                    j7.h.a(iVar, i10, 0);
                    e(inputStream);
                    e(byteArrayOutputStream);
                    throw th;
                }
            }
            if (responseCode != 200) {
                j7.h.a(iVar, responseCode, 0);
                e(null);
                e(null);
                return "";
            }
            InputStream inputStream2 = httpURLConnectionZ.getInputStream();
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int i11 = inputStream2.read(bArr);
                        if (i11 == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, i11);
                    }
                    String string = byteArrayOutputStream.toString();
                    if (f31400a) {
                        Log.d("NetUtil", "request result  : " + string);
                    }
                    j7.h.a(iVar, responseCode, 0);
                    e(inputStream2);
                    e(byteArrayOutputStream);
                    return string;
                } catch (Exception e11) {
                    inputStream = inputStream2;
                    i10 = responseCode;
                    e = e11;
                    e.printStackTrace();
                    j7.h.a(iVar, i10, 0);
                    e(inputStream);
                    e(byteArrayOutputStream);
                    return "";
                } catch (Throwable th4) {
                    inputStream = inputStream2;
                    i10 = responseCode;
                    th = th4;
                    j7.h.a(iVar, i10, 0);
                    e(inputStream);
                    e(byteArrayOutputStream);
                    throw th;
                }
            } catch (Exception e12) {
                inputStream = inputStream2;
                e = e12;
                byteArrayOutputStream = null;
                i10 = responseCode;
            } catch (Throwable th5) {
                inputStream = inputStream2;
                th = th5;
                byteArrayOutputStream = null;
                i10 = responseCode;
            }
        } catch (Exception e13) {
            e = e13;
        } catch (Throwable th6) {
            th = th6;
        }
    }

    public static String M(Map map, String str, b bVar, String str2, j7.i iVar) {
        return N(map, str, bVar, str2, false, true, iVar);
    }

    private static String N(Map map, String str, b bVar, String str2, boolean z10, boolean z11, j7.i iVar) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        if (!v1.b() && !z10) {
            return "";
        }
        int i10 = -1;
        InputStream inputStream = null;
        try {
            if (z11) {
                map = b(map, str2);
            } else {
                if (map == null) {
                    map = new HashMap();
                }
                map.put("sign", q(map, str2));
            }
            if (bVar == b.GET && map != null) {
                String strK = k(map);
                str = str.contains("?") ? str.concat(strK) : str.concat("?").concat(strK);
            }
            boolean z12 = f31400a;
            if (z12) {
                Log.d("NetUtil", "request start : " + str);
            }
            HttpURLConnection httpURLConnectionZ = z(new URL(str));
            Q(httpURLConnectionZ, bVar, map);
            int responseCode = httpURLConnectionZ.getResponseCode();
            if (z12) {
                try {
                    Log.d("NetUtil", " responseCode :  " + responseCode);
                } catch (Exception e10) {
                    i10 = responseCode;
                    e = e10;
                    byteArrayOutputStream = null;
                    try {
                        Log.e("NetUtil", "request: " + e);
                        j7.h.a(iVar, i10, 0);
                        e(inputStream);
                        e(byteArrayOutputStream);
                        return "";
                    } catch (Throwable th2) {
                        th = th2;
                        j7.h.a(iVar, i10, 0);
                        e(inputStream);
                        e(byteArrayOutputStream);
                        throw th;
                    }
                } catch (Throwable th3) {
                    i10 = responseCode;
                    th = th3;
                    byteArrayOutputStream = null;
                    j7.h.a(iVar, i10, 0);
                    e(inputStream);
                    e(byteArrayOutputStream);
                    throw th;
                }
            }
            if (responseCode != 200) {
                j7.h.a(iVar, responseCode, 0);
                e(null);
                e(null);
                return "";
            }
            InputStream inputStream2 = httpURLConnectionZ.getInputStream();
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int i11 = inputStream2.read(bArr);
                        if (i11 == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, i11);
                    }
                    String string = byteArrayOutputStream.toString();
                    if (f31400a) {
                        Log.d("NetUtil", "request result  : " + string);
                    }
                    j7.h.a(iVar, responseCode, 0);
                    e(inputStream2);
                    e(byteArrayOutputStream);
                    return string;
                } catch (Exception e11) {
                    i10 = responseCode;
                    inputStream = inputStream2;
                    e = e11;
                    Log.e("NetUtil", "request: " + e);
                    j7.h.a(iVar, i10, 0);
                    e(inputStream);
                    e(byteArrayOutputStream);
                    return "";
                } catch (Throwable th4) {
                    i10 = responseCode;
                    inputStream = inputStream2;
                    th = th4;
                    j7.h.a(iVar, i10, 0);
                    e(inputStream);
                    e(byteArrayOutputStream);
                    throw th;
                }
            } catch (Exception e12) {
                i10 = responseCode;
                e = e12;
                byteArrayOutputStream = null;
                inputStream = inputStream2;
            } catch (Throwable th5) {
                i10 = responseCode;
                th = th5;
                byteArrayOutputStream = null;
                inputStream = inputStream2;
            }
        } catch (Exception e13) {
            e = e13;
        } catch (Throwable th6) {
            th = th6;
        }
    }

    public static String O(String str, String str2) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        InputStream inputStream;
        ByteArrayOutputStream byteArrayOutputStream2;
        if (!v1.b()) {
            return "";
        }
        InputStream inputStream2 = null;
        try {
            try {
                boolean z10 = f31400a;
                if (z10) {
                    Log.d("NetUtil", "request post json start : " + str2);
                }
                HttpURLConnection httpURLConnectionZ = z(new URL(str2));
                httpURLConnectionZ.setConnectTimeout(QQMessage.TYPE_DISCUSS_GROUP);
                httpURLConnectionZ.setRequestMethod(NetworkUtils.REQUEST_METHOD_POST);
                httpURLConnectionZ.addRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json");
                httpURLConnectionZ.setDoOutput(true);
                DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnectionZ.getOutputStream());
                dataOutputStream.write(str.getBytes());
                dataOutputStream.close();
                int responseCode = httpURLConnectionZ.getResponseCode();
                if (z10) {
                    Log.d("NetUtil", " responseCode :  " + responseCode);
                }
                if (responseCode != 200) {
                    e(null);
                    e(null);
                    return null;
                }
                InputStream inputStream3 = httpURLConnectionZ.getInputStream();
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        byte[] bArr = new byte[4096];
                        while (true) {
                            int i10 = inputStream3.read(bArr);
                            if (i10 == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, i10);
                        }
                        String string = byteArrayOutputStream.toString();
                        if (f31400a) {
                            Log.d("NetUtil", "request post json result : " + string);
                        }
                        e(inputStream3);
                        e(byteArrayOutputStream);
                        return string;
                    } catch (Exception e10) {
                        inputStream = inputStream3;
                        e = e10;
                        byteArrayOutputStream2 = byteArrayOutputStream;
                    } catch (Throwable th2) {
                        inputStream2 = inputStream3;
                        th = th2;
                        e(inputStream2);
                        e(byteArrayOutputStream);
                        throw th;
                    }
                } catch (Exception e11) {
                    inputStream = inputStream3;
                    e = e11;
                    byteArrayOutputStream2 = null;
                } catch (Throwable th3) {
                    inputStream2 = inputStream3;
                    th = th3;
                    byteArrayOutputStream = null;
                }
            } catch (Exception e12) {
                e = e12;
                inputStream = null;
                byteArrayOutputStream2 = null;
            } catch (Throwable th4) {
                th = th4;
                byteArrayOutputStream = null;
            }
            e.printStackTrace();
            e(inputStream);
            e(byteArrayOutputStream2);
            return null;
        } catch (Throwable th5) {
            th = th5;
            inputStream2 = inputStream;
            byteArrayOutputStream = byteArrayOutputStream2;
            e(inputStream2);
            e(byteArrayOutputStream);
            throw th;
        }
    }

    private static byte[] P(Map map, String str, b bVar, String str2, boolean z10, boolean z11, j7.i iVar) {
        if (!v1.b() && !z10) {
            return null;
        }
        try {
            map.put("sign", r(map, str2));
            if (bVar == b.GET) {
                str = d(str, map);
            }
            w(str, map);
            return t(j(str, bVar, map));
        } catch (Exception e10) {
            Log.e("NetUtil", "requestRiskyApp exception : " + e10.getMessage());
            return null;
        }
    }

    private static void Q(HttpURLConnection httpURLConnection, b bVar, Map map) throws IOException {
        int i10 = a.f31409a[bVar.ordinal()];
        if (i10 == 1) {
            httpURLConnection.setRequestMethod(NetworkUtils.REQUEST_METHOD_GET);
            return;
        }
        if (i10 == 2) {
            httpURLConnection.setRequestMethod("DELETE");
            return;
        }
        if (i10 == 3) {
            httpURLConnection.setRequestMethod(NetworkUtils.REQUEST_METHOD_POST);
            a(httpURLConnection, map);
        } else {
            if (i10 != 4) {
                throw new IllegalStateException("Unknown method type.");
            }
            httpURLConnection.setRequestMethod("PUT");
        }
    }

    private static void a(HttpURLConnection httpURLConnection, Map map) throws IOException {
        byte[] bytes;
        if (map == null || map.size() <= 0) {
            bytes = null;
        } else {
            String strK = k(map);
            if (f31400a) {
                Log.d("NetUtil", " post body : " + strK);
            }
            bytes = strK.getBytes();
        }
        if (bytes != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8");
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(bytes);
            dataOutputStream.close();
        }
    }

    public static Map b(Map map, String str) {
        if (map == null) {
            map = new HashMap();
        }
        map.putAll(f31408i);
        Application applicationD = com.miui.common.e.d();
        map.put(com.xiaomi.onetrack.b.e.f27645a, Locale.getDefault().toString());
        map.put("n", d.e(applicationD));
        if (Build.IS_INTERNATIONAL_BUILD) {
            try {
                String strB = aj.x.b();
                if (!TextUtils.isEmpty(strB)) {
                    ConfigManager.UuidAesKey uuidAesKey = ConfigManager.getUuidAesKey(applicationD);
                    String strB2 = "";
                    if (uuidAesKey != null) {
                        String key = uuidAesKey.getKey();
                        String iv = uuidAesKey.getIv();
                        if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(iv)) {
                            strB2 = gk.a.b(strB, key, iv);
                        }
                    }
                    map.put(CloudPushConstants.WATERMARK_TYPE.GLOBAL, strB2);
                }
                map.put("cr", h0.e());
            } catch (Exception e10) {
                Log.e("NetUtil", "getAdvertisingId error", e10);
            }
        } else {
            map.put(AnimatedProperty.PROPERTY_NAME_X, d.a(applicationD));
            map.put(com.xiaomi.onetrack.b.l.f27678a, y.a(applicationD));
        }
        map.put("sign", q(map, str));
        return map;
    }

    public static void c(HttpURLConnection httpURLConnection, String str) throws IOException {
        byte[] bytes;
        if (TextUtils.isEmpty(str)) {
            bytes = null;
        } else {
            if (f31400a) {
                Log.d("NetUtil", " post body : " + str);
            }
            bytes = str.getBytes();
        }
        if (bytes != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(bytes);
            dataOutputStream.close();
        }
    }

    private static String d(String str, Map map) {
        String strK = k(map);
        return str.contains("?") ? str.concat(strK) : str.concat("?").concat(strK);
    }

    private static void e(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    private static void f(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e10) {
                Log.e("NetUtil", "Error closing resource", e10);
            }
        }
    }

    public static String g(String str, Map map, j7.i iVar) {
        return i(str, map, b.GET, iVar);
    }

    public static String h(String str, Map map, j7.i iVar) {
        return i(str, map, b.POST, iVar);
    }

    private static String i(String str, Map map, b bVar, j7.i iVar) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        if (!v1.b()) {
            return "";
        }
        int i10 = -1;
        InputStream inputStream = null;
        try {
            if (bVar == b.GET && map != null) {
                String strK = k(map);
                str = str.contains("?") ? str.concat(strK) : str.concat("?").concat(strK);
            }
            boolean z10 = f31400a;
            if (z10) {
                Log.d("NetUtil", "request start : " + str);
            }
            HttpURLConnection httpURLConnectionZ = z(new URL(str));
            Q(httpURLConnectionZ, bVar, map);
            int responseCode = httpURLConnectionZ.getResponseCode();
            if (z10) {
                try {
                    Log.d("NetUtil", " responseCode :  " + responseCode);
                } catch (Exception e10) {
                    e = e10;
                    i10 = responseCode;
                    byteArrayOutputStream = null;
                    try {
                        e.printStackTrace();
                        Log.e("NetExp", "Comment request exp", e);
                        j7.h.a(iVar, i10, 0);
                        e(inputStream);
                        e(byteArrayOutputStream);
                        return "";
                    } catch (Throwable th2) {
                        th = th2;
                        j7.h.a(iVar, i10, 0);
                        e(inputStream);
                        e(byteArrayOutputStream);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    i10 = responseCode;
                    byteArrayOutputStream = null;
                    j7.h.a(iVar, i10, 0);
                    e(inputStream);
                    e(byteArrayOutputStream);
                    throw th;
                }
            }
            if (responseCode != 200) {
                j7.h.a(iVar, responseCode, 0);
                e(null);
                e(null);
                return "";
            }
            InputStream inputStream2 = httpURLConnectionZ.getInputStream();
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int i11 = inputStream2.read(bArr);
                        if (i11 == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, i11);
                    }
                    String string = byteArrayOutputStream.toString();
                    if (f31400a) {
                        Log.d("NetUtil", "request result  : " + string);
                    }
                    j7.h.a(iVar, responseCode, 0);
                    e(inputStream2);
                    e(byteArrayOutputStream);
                    return string;
                } catch (Exception e11) {
                    inputStream = inputStream2;
                    i10 = responseCode;
                    e = e11;
                    e.printStackTrace();
                    Log.e("NetExp", "Comment request exp", e);
                    j7.h.a(iVar, i10, 0);
                    e(inputStream);
                    e(byteArrayOutputStream);
                    return "";
                } catch (Throwable th4) {
                    inputStream = inputStream2;
                    i10 = responseCode;
                    th = th4;
                    j7.h.a(iVar, i10, 0);
                    e(inputStream);
                    e(byteArrayOutputStream);
                    throw th;
                }
            } catch (Exception e12) {
                inputStream = inputStream2;
                e = e12;
                byteArrayOutputStream = null;
                i10 = responseCode;
            } catch (Throwable th5) {
                inputStream = inputStream2;
                th = th5;
                byteArrayOutputStream = null;
                i10 = responseCode;
            }
        } catch (Exception e13) {
            e = e13;
        } catch (Throwable th6) {
            th = th6;
        }
    }

    private static HttpURLConnection j(String str, b bVar, Map map) throws IOException {
        HttpURLConnection httpURLConnectionZ = z(new URL(str));
        Q(httpURLConnectionZ, bVar, map);
        httpURLConnectionZ.setRequestProperty("EncryptVersion", DedicatedNetConstants.Report.ITEM_STATUS_CLOSE);
        return httpURLConnectionZ;
    }

    public static String k(Map map) {
        StringBuilder sb2 = new StringBuilder();
        try {
            boolean z10 = true;
            for (Map.Entry entry : map.entrySet()) {
                if (!z10) {
                    sb2.append('&');
                }
                sb2.append(URLEncoder.encode((String) entry.getKey(), C.UTF8_NAME));
                sb2.append('=');
                sb2.append(URLEncoder.encode((String) entry.getValue(), C.UTF8_NAME));
                z10 = false;
            }
            return sb2.toString();
        } catch (UnsupportedEncodingException e10) {
            throw new RuntimeException("Encoding not supported: " + ((Object) sb2), e10);
        }
    }

    public static String l(Map map, String str, j7.i iVar) {
        return N(map, str, b.POST, "2dcd9s0c-ad3f-2fas-0l3a-abzo301jd0s9", false, false, iVar);
    }

    public static String m(Map map, String str, j7.i iVar) {
        return N(map, str, b.GET, "2dcd9s0c-ad3f-2fas-0l3a-abzo301jd0s9", false, true, iVar);
    }

    private static byte[] n(String str) {
        try {
            return str.getBytes(C.UTF8_NAME);
        } catch (UnsupportedEncodingException unused) {
            return str.getBytes();
        }
    }

    public static byte[] o(Map map, String str, String str2, j7.i iVar) {
        return P(map, str, b.GET, str2, false, true, iVar);
    }

    public static String p(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(n(str));
            return String.format("%1$032X", new BigInteger(1, messageDigest.digest()));
        } catch (NoSuchAlgorithmException e10) {
            throw new RuntimeException(e10);
        }
    }

    public static String q(Map map, String str) {
        StringBuilder sb2 = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : map.entrySet()) {
            arrayList.add(new c((String) entry.getKey(), (String) entry.getValue()));
        }
        Collections.sort(arrayList);
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            if (i10 > 0) {
                sb2.append("&");
            }
            c cVar = (c) arrayList.get(i10);
            sb2.append(cVar.f31415a);
            sb2.append("=");
            sb2.append(cVar.f31416b);
        }
        sb2.append("&");
        sb2.append(str);
        return p(new String(Base64.encodeToString(n(sb2.toString()), 2)));
    }

    public static String r(Map map, String str) {
        StringBuilder sb2 = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : map.entrySet()) {
            arrayList.add(new c((String) entry.getKey(), (String) entry.getValue()));
        }
        Collections.sort(arrayList);
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            if (i10 > 0) {
                sb2.append("&");
            }
            c cVar = (c) arrayList.get(i10);
            sb2.append(cVar.f31415a);
            sb2.append("=");
            sb2.append(cVar.f31416b);
        }
        sb2.append("&");
        sb2.append(str);
        try {
            return u(sb2.toString().getBytes(StandardCharsets.UTF_8), "MD5", true);
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:20:0x00af  */
    public static HashMap s(String str) {
        String strB;
        HashMap map = new HashMap();
        map.put("d", f31401b);
        map.put("r", f31403d);
        map.put("v", f31404e);
        map.put("androidVersion", String.valueOf(android.os.Build.VERSION.SDK_INT));
        map.put("t", d.d());
        map.put("t_app", d.g());
        Application applicationD = com.miui.common.e.d();
        map.put("n", d.e(applicationD));
        String strValueOf = "";
        if (Build.IS_INTERNATIONAL_BUILD) {
            try {
                String strB2 = aj.x.b();
                ConfigManager.getUuidAesKey(com.miui.common.e.d());
                ConfigManager.UuidAesKey uuidAesKey = ConfigManager.getUuidAesKey(com.miui.common.e.d());
                if (TextUtils.isEmpty(strB2) || uuidAesKey == null) {
                    strB = "";
                } else {
                    String key = uuidAesKey.getKey();
                    String iv = uuidAesKey.getIv();
                    if (TextUtils.isEmpty(key) || TextUtils.isEmpty(iv)) {
                        strB = "";
                    } else {
                        strB = gk.a.b(strB2, key, iv);
                    }
                }
            } catch (Exception e10) {
                Log.e("TrainRequestBean", "getAdvertisingId error", e10);
            }
            map.put(CloudPushConstants.WATERMARK_TYPE.GLOBAL, strB);
        } else {
            map.put("m", !v() ? os.a.d(js.a.b(d.c(applicationD), "MD5")) : "");
            map.put(com.xiaomi.onetrack.b.l.f27678a, y.a(com.miui.common.e.d()));
        }
        try {
            strValueOf = String.valueOf(applicationD.getPackageManager().getPackageInfo(applicationD.getPackageName(), 0).versionCode);
        } catch (Exception e11) {
            e11.printStackTrace();
        }
        map.put("e", strValueOf);
        map.put("model_version", str);
        return map;
    }

    private static byte[] t(HttpURLConnection httpURLConnection) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th2;
        InputStream inputStream;
        try {
            int responseCode = httpURLConnection.getResponseCode();
            x(responseCode);
            if (responseCode != 200) {
                f(null);
                f(null);
                return null;
            }
            inputStream = httpURLConnection.getInputStream();
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    K(inputStream, byteArrayOutputStream);
                    y(byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    f(byteArrayOutputStream);
                    f(inputStream);
                    return byteArray;
                } catch (Throwable th3) {
                    th2 = th3;
                }
            } catch (Throwable th4) {
                byteArrayOutputStream = null;
                th2 = th4;
            }
        } catch (Throwable th5) {
            byteArrayOutputStream = null;
            th2 = th5;
            inputStream = null;
        }
        f(byteArrayOutputStream);
        f(inputStream);
        throw th2;
    }

    private static String u(byte[] bArr, String str, boolean z10) {
        byte[] bArrDigest = MessageDigest.getInstance(str).digest(bArr);
        StringBuilder sb2 = new StringBuilder();
        for (byte b10 : bArrDigest) {
            String hexString = Integer.toHexString(b10 & 255);
            if (hexString.length() == 1) {
                sb2.append('0');
            }
            sb2.append(hexString);
        }
        String string = sb2.toString();
        return z10 ? string.toUpperCase() : string.toLowerCase();
    }

    public static boolean v() {
        return d.l();
    }

    private static void w(String str, Map map) {
        if (f31400a) {
            Log.d("NetUtil", "request start : " + str);
            Log.d("NetUtil", "request params : " + map.toString());
        }
    }

    private static void x(int i10) {
        if (f31400a) {
            Log.d("NetUtil", " responseCode :  " + i10);
        }
    }

    private static void y(ByteArrayOutputStream byteArrayOutputStream) {
        if (f31400a) {
            Log.d("NetUtil", "request result  : " + byteArrayOutputStream.toString());
        }
    }

    private static HttpURLConnection z(URL url) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(Network.READ_TIMEOUT);
        httpURLConnection.setReadTimeout(Network.READ_TIMEOUT);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        return httpURLConnection;
    }
}
