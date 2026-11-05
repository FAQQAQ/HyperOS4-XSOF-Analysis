package ff;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.common.net.HttpHeaders;
import com.miui.common.utils.j0;
import com.miui.hybrid.accessory.utils.network.Network;
import com.miui.permcenter.compact.SystemPropertiesCompat;
import com.miui.warningcenter.caraccident.utils.NetworkUtils;
import com.xiaomi.onetrack.api.ay;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import miui.os.Build;

/* JADX INFO: loaded from: classes3.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f30574a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f30575b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f30576c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f30577d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final HashMap f30578e;

    static {
        String string = SystemPropertiesCompat.getString("ro.product.device", "unknown");
        f30574a = string;
        String string2 = SystemPropertiesCompat.getString("ro.carrier.name", "unknown");
        f30575b = string2;
        String region = Build.getRegion();
        f30576c = region;
        String str = android.os.Build.VERSION.INCREMENTAL;
        f30577d = str;
        HashMap map = new HashMap();
        f30578e = map;
        map.put("device", string);
        map.put("carrier", string2);
        map.put("region", region);
        map.put("miui_version", str);
        map.put("version_type", gk.d.d());
        map.put("miui_version_name", SystemPropertiesCompat.getString("ro.miui.ui.version.name", "unknown"));
    }

    public static Map a(Context context, Map map) {
        if (map == null) {
            map = new HashMap();
        }
        map.putAll(f30578e);
        map.put("language", Locale.getDefault().toString());
        map.put(Network.PARAM_NETWORK_TYPE, gk.d.e(context));
        String strA = gk.d.a(context);
        map.put("mi", os.a.d(js.a.b(strA + "2dcd9s0c-ad3f-2fas-0l3a-abzo301jd0s9", "MD5")));
        map.put("android_id", strA);
        String strI = gk.d.i();
        if (TextUtils.isEmpty(strI)) {
            strI = gk.d.a(context);
        }
        map.put("imei", os.a.d(js.a.b(strI, "MD5")));
        map.put("sim", b(context));
        try {
            map.put("app_version", String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode));
            return map;
        } catch (PackageManager.NameNotFoundException e10) {
            Log.e("NetConnectUtil", "NameNotFoundException", e10);
            return map;
        }
    }

    private static String b(Context context) {
        if (j0.M()) {
            return gk.d.a(context);
        }
        try {
            String subscriberId = ((TelephonyManager) context.getSystemService(ay.f27518d)).getSubscriberId();
            return !TextUtils.isEmpty(subscriberId) ? subscriberId : "off";
        } catch (Exception e10) {
            Log.e("NetConnectUtil", "getImsi", e10);
            return "off";
        }
    }

    public static boolean c(Context context) {
        if (context == null) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isAvailable();
            }
            return false;
        } catch (Exception e10) {
            Log.d("NetConnectUtil", "isNetworkConnected:" + e10.toString());
            return false;
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:46:0x00b1 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v18, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r6v19 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v7, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r6v8, types: [java.io.InputStream] */
    public static String d(String str, byte[] bArr, j7.i iVar) throws Throwable {
        OutputStream outputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th2;
        ?? inputStream;
        Exception e10;
        int i10 = -1;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setRequestMethod(NetworkUtils.REQUEST_METHOD_POST);
            httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_LENGTH, String.valueOf(bArr.length));
            httpURLConnection.setDoOutput(true);
            outputStream = httpURLConnection.getOutputStream();
            try {
                outputStream.write(bArr);
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode != 200) {
                    j7.h.a(iVar, responseCode, 0);
                    vq.h.b(null);
                    vq.h.c(outputStream);
                    vq.h.c(null);
                    return "";
                }
                try {
                    inputStream = httpURLConnection.getInputStream();
                    try {
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        try {
                            byte[] bArr2 = new byte[4096];
                            while (true) {
                                int i11 = inputStream.read(bArr2);
                                if (i11 == -1) {
                                    String string = byteArrayOutputStream.toString();
                                    j7.h.a(iVar, responseCode, 0);
                                    vq.h.b(inputStream);
                                    vq.h.c(outputStream);
                                    vq.h.c(byteArrayOutputStream);
                                    return string;
                                }
                                byteArrayOutputStream.write(bArr2, 0, i11);
                            }
                        } catch (Exception e11) {
                            e10 = e11;
                            i10 = responseCode;
                            inputStream = inputStream;
                            try {
                                Log.e("NetConnectUtil", "post error!", e10);
                                j7.h.a(iVar, i10, 0);
                                vq.h.b(inputStream);
                                vq.h.c(outputStream);
                                vq.h.c(byteArrayOutputStream);
                                return "";
                            } catch (Throwable th3) {
                                th2 = th3;
                                j7.h.a(iVar, i10, 0);
                                vq.h.b(inputStream);
                                vq.h.c(outputStream);
                                vq.h.c(byteArrayOutputStream);
                                throw th2;
                            }
                        } catch (Throwable th4) {
                            th2 = th4;
                            i10 = responseCode;
                            j7.h.a(iVar, i10, 0);
                            vq.h.b(inputStream);
                            vq.h.c(outputStream);
                            vq.h.c(byteArrayOutputStream);
                            throw th2;
                        }
                    } catch (Exception e12) {
                        byteArrayOutputStream = null;
                        e10 = e12;
                    } catch (Throwable th5) {
                        byteArrayOutputStream = null;
                        th2 = th5;
                    }
                } catch (Exception e13) {
                    e = e13;
                    i10 = responseCode;
                    byteArrayOutputStream = null;
                    e10 = e;
                    inputStream = byteArrayOutputStream;
                    Log.e("NetConnectUtil", "post error!", e10);
                    j7.h.a(iVar, i10, 0);
                    vq.h.b(inputStream);
                    vq.h.c(outputStream);
                    vq.h.c(byteArrayOutputStream);
                    return "";
                } catch (Throwable th6) {
                    th = th6;
                    i10 = responseCode;
                    byteArrayOutputStream = null;
                    th2 = th;
                    inputStream = byteArrayOutputStream;
                    j7.h.a(iVar, i10, 0);
                    vq.h.b(inputStream);
                    vq.h.c(outputStream);
                    vq.h.c(byteArrayOutputStream);
                    throw th2;
                }
            } catch (Exception e14) {
                e = e14;
            } catch (Throwable th7) {
                th = th7;
            }
        } catch (Exception e15) {
            e = e15;
            outputStream = null;
            byteArrayOutputStream = null;
        } catch (Throwable th8) {
            th = th8;
            outputStream = null;
            byteArrayOutputStream = null;
        }
    }
}
