package defpackage;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.miui.guardprovider.GuardApplication;
import com.xiaomi.onetrack.api.al;
import com.xiaomi.onetrack.util.a;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class ls1 {
    public static final String a = bd3.v("ro.product.device", "unknown");
    public static final String b = bd3.v("ro.carrier.name", "unknown");
    public static final String c = bd3.v("ro.product.mod_device", "unknown");
    public static final String d = "MIUI-" + Build.VERSION.INCREMENTAL;
    public static final String e = String.valueOf(1);

    public static void a(HttpURLConnection httpURLConnection, String str) throws IOException {
        if (str != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("Content-Type", "application/json");
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.writeBytes(str);
            dataOutputStream.close();
        }
    }

    public static void b(HttpURLConnection httpURLConnection, HashMap map) throws IOException {
        byte[] bytes;
        if (map.size() > 0) {
            StringBuilder sb = new StringBuilder();
            try {
                boolean z = true;
                for (String str : map.keySet()) {
                    if (!z) {
                        sb.append('&');
                    }
                    sb.append(URLEncoder.encode(str, "UTF-8"));
                    sb.append('=');
                    sb.append(URLEncoder.encode((String) map.get(str), "UTF-8"));
                    z = false;
                }
                bytes = sb.toString().getBytes();
            } catch (UnsupportedEncodingException e2) {
                throw new RuntimeException("Encoding not supported: " + ((Object) sb), e2);
            }
        } else {
            bytes = null;
        }
        if (bytes != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(bytes);
            dataOutputStream.close();
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:37:0x00e3 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r4v12, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v17, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v22, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v26 */
    /* JADX WARN: Type inference failed for: r4v27 */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.io.ByteArrayOutputStream] */
    public static String c(Context context, String str, HashMap map) {
        String str2;
        String string;
        String strY;
        Throwable th;
        Exception e2;
        ?? r6;
        ?? r4;
        ByteArrayOutputStream byteArrayOutputStream;
        ?? r1 = 0;
        if (GuardApplication.isAllowConnectNetwork(context) && !TextUtils.isEmpty(str)) {
            HashMap map2 = new HashMap();
            map2.put("device", a);
            map2.put("carrier", b);
            map2.put("region", c);
            map2.put("miuiVersion", d);
            if (miui.os.Build.IS_STABLE_VERSION) {
                str2 = "stable";
            } else {
                str2 = miui.os.Build.IS_DEVELOPMENT_VERSION ? "development" : "alpha";
            }
            map2.put("versionType", str2);
            map2.put("appVersion", e);
            try {
                string = Settings.Secure.getString(context.getContentResolver(), al.D);
            } catch (Exception e3) {
                e3.printStackTrace();
                string = null;
            }
            if (TextUtils.isEmpty(string)) {
                strY = a.c;
            } else {
                try {
                    strY = uy2.y(cp3.i(new ByteArrayInputStream((string + "-94e6cec2-19dc-4bb6-823c-613f72d3ea16").toString().getBytes())));
                } catch (IOException e4) {
                    ik.n("IO exception happend in ByteArrayInputStream", e4);
                    return null;
                }
            }
            map2.put("mi", strY);
            map2.putAll(map);
            ?? inputStream = "sign";
            String strE = e("94e6cec2-19dc-4bb6-823c-613f72d3ea16", map2);
            map2.put("sign", strE);
            try {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                    httpURLConnection.setConnectTimeout(15000);
                    httpURLConnection.setReadTimeout(15000);
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.setDoInput(true);
                    try {
                        httpURLConnection.setRequestMethod("POST");
                        b(httpURLConnection, map2);
                        if (httpURLConnection.getResponseCode() == 200) {
                            inputStream = httpURLConnection.getInputStream();
                            try {
                                byteArrayOutputStream = new ByteArrayOutputStream();
                                try {
                                    byte[] bArr = new byte[4096];
                                    while (true) {
                                        int i = inputStream.read(bArr);
                                        if (i == -1) {
                                            String string2 = byteArrayOutputStream.toString();
                                            tv0.c(inputStream);
                                            tv0.a(byteArrayOutputStream);
                                            return string2;
                                        }
                                        byteArrayOutputStream.write(bArr, 0, i);
                                    }
                                } catch (Exception e5) {
                                    e2 = e5;
                                    r4 = inputStream;
                                }
                            } catch (Exception e6) {
                                e2 = e6;
                                byteArrayOutputStream = null;
                                r4 = inputStream;
                            } catch (Throwable th2) {
                                th = th2;
                                strE = null;
                                r1 = inputStream;
                                r6 = strE;
                                tv0.c(r1);
                                tv0.a(r6);
                                throw th;
                            }
                        } else {
                            int i2 = tv0.a;
                        }
                    } catch (Exception e7) {
                        e2 = e7;
                        r4 = 0;
                        byteArrayOutputStream = null;
                    } catch (Throwable th3) {
                        th = th3;
                        r6 = 0;
                        tv0.c(r1);
                        tv0.a(r6);
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            } catch (Exception e8) {
                e2 = e8;
            } catch (Throwable th5) {
                th = th5;
            }
            Log.e("NetworkApiHelper", "exception when do post request", e2);
            tv0.c(r4);
            tv0.a(byteArrayOutputStream);
        }
        return null;
    }

    public static String d(GuardApplication guardApplication, JSONObject jSONObject) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        InputStream inputStream;
        Exception e2;
        String string = null;
        if (!GuardApplication.isAllowConnectNetwork(guardApplication) || TextUtils.isEmpty("https://flash.sec.miui.com/detect/app")) {
            return null;
        }
        HashMap map = new HashMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (!next.equals("sign")) {
                if (next.equals("content")) {
                    map.put(next, jSONObject.optJSONArray(next).toString());
                } else {
                    map.put(next, jSONObject.optString(next));
                }
            }
        }
        String strE = e("6988567a-4220-4b51-bc2d-ccdec27a74a1", map);
        try {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("https://flash.sec.miui.com/detect/app").openConnection();
                httpURLConnection.setConnectTimeout(15000);
                httpURLConnection.setReadTimeout(15000);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setDoInput(true);
                try {
                    httpURLConnection.setRequestMethod("POST");
                    jSONObject.put("sign", strE);
                    a(httpURLConnection, jSONObject.toString());
                    if (httpURLConnection.getResponseCode() != 200) {
                        int i = tv0.a;
                        return null;
                    }
                    inputStream = httpURLConnection.getInputStream();
                    try {
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        try {
                            try {
                                byte[] bArr = new byte[4096];
                                while (true) {
                                    int i2 = inputStream.read(bArr);
                                    if (i2 == -1) {
                                        break;
                                    }
                                    byteArrayOutputStream.write(bArr, 0, i2);
                                }
                                string = byteArrayOutputStream.toString();
                            } catch (Exception e3) {
                                e2 = e3;
                                Log.e("NetworkApiHelper", "exception when do post request", e2);
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            tv0.c(inputStream);
                            tv0.a(byteArrayOutputStream);
                            throw th;
                        }
                    } catch (Exception e4) {
                        e2 = e4;
                        byteArrayOutputStream = null;
                    } catch (Throwable th3) {
                        th = th3;
                        byteArrayOutputStream = null;
                        tv0.c(inputStream);
                        tv0.a(byteArrayOutputStream);
                        throw th;
                    }
                    tv0.c(inputStream);
                    tv0.a(byteArrayOutputStream);
                    return string;
                } catch (Exception e5) {
                    e2 = e5;
                    inputStream = null;
                    byteArrayOutputStream = null;
                }
            } catch (Exception e6) {
                e2 = e6;
            }
            tv0.c(inputStream);
            tv0.a(byteArrayOutputStream);
            return string;
        } catch (Throwable th4) {
            byteArrayOutputStream = null;
            th = th4;
            inputStream = null;
        }
        Log.e("NetworkApiHelper", "exception when do post request", e2);
    }

    public static String e(String str, HashMap map) {
        byte[] bytes;
        byte[] bytes2;
        ArrayList<String> arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList, new ei(11));
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str2 : arrayList) {
            if (!z) {
                sb.append("&");
            }
            sb.append(str2);
            sb.append("=");
            sb.append((String) map.get(str2));
            z = false;
        }
        String strR = im2.r(sb, "&", str);
        try {
            bytes = strR.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            bytes = strR.getBytes();
        }
        String strEncodeToString = Base64.encodeToString(bytes, 2);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            try {
                bytes2 = strEncodeToString.getBytes("UTF-8");
            } catch (UnsupportedEncodingException unused2) {
                bytes2 = strEncodeToString.getBytes();
            }
            messageDigest.update(bytes2);
            return String.format("%1$032X", new BigInteger(1, messageDigest.digest()));
        } catch (NoSuchAlgorithmException e2) {
            ik.q(e2);
            return null;
        }
    }
}
