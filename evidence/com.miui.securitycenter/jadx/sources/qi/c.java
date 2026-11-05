package qi;

import aj.y;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.google.common.net.HttpHeaders;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.miui.common.utils.f2;
import com.miui.earthquakewarning.model.SaveAreaResult;
import com.miui.hybrid.accessory.utils.network.Network;
import com.miui.luckymoney.config.Constants;
import com.miui.maml.folme.AnimatedProperty;
import com.miui.networkassistant.vpn.miui.dedicatednet.DedicatedNetConstants;
import com.miui.riskyapp.model.CloudScanResponse;
import com.miui.riskyapp.model.RiskyAppDescriptionList;
import com.miui.warningcenter.caraccident.utils.NetworkUtils;
import gk.l;
import j7.i;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public abstract class c {
    private static Map a(Context context, int i10) {
        String str;
        HashMap map = new HashMap();
        map.put("androidVersion", String.valueOf(Build.VERSION.SDK_INT));
        map.put("miuiVersion", Build.VERSION.INCREMENTAL);
        map.put("timestamp", String.valueOf(System.currentTimeMillis()));
        map.put("uuid", y.a(context));
        if (miui.os.Build.IS_STABLE_VERSION) {
            str = "stable";
        } else {
            str = miui.os.Build.IS_DEVELOPMENT_VERSION ? "development" : AnimatedProperty.PROPERTY_NAME_ALPHA;
        }
        map.put(Constants.JSON_KEY_VERSION_TYPE, str);
        map.put("scanType", String.valueOf(i10));
        map.put(SaveAreaResult.Columns.source, String.valueOf(1));
        map.put("device", miui.os.Build.PRODUCT);
        return map;
    }

    private static byte[] b(List list) {
        byte[] bArrF = gf.a.f(new Gson().toJson(list).getBytes(StandardCharsets.UTF_8));
        if (bArrF == null) {
            Log.e("RiskyAppRequest", "buildEncryptedBody: gzip compress failed");
            return null;
        }
        byte[] bArrB = b.b(bArrF, "wcw1xbplx7aijwqr");
        if (bArrB == null) {
            Log.e("RiskyAppRequest", "buildEncryptedBody: encrypt failed");
        }
        return bArrB;
    }

    private static String c(String str, Map map) {
        StringBuilder sb2 = new StringBuilder(str);
        sb2.append("?");
        boolean z10 = true;
        for (Map.Entry entry : map.entrySet()) {
            if (!z10) {
                sb2.append("&");
            }
            sb2.append(URLEncoder.encode((String) entry.getKey(), C.UTF8_NAME));
            sb2.append("=");
            sb2.append(URLEncoder.encode((String) entry.getValue(), C.UTF8_NAME));
            z10 = false;
        }
        return sb2.toString();
    }

    /* JADX WARN: Code duplicated, block: B:49:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:58:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:66:0x00eb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:72:0x00f0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:76:0x00dd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:78:0x00d8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:88:? A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v9 */
    private static byte[] d(String str, byte[] bArr) throws Throwable {
        HttpURLConnection httpURLConnection;
        ?? r10;
        InputStream inputStream;
        ?? r11;
        InputStream inputStream2 = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection.setConnectTimeout(Network.READ_TIMEOUT);
                httpURLConnection.setReadTimeout(Network.READ_TIMEOUT);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod(NetworkUtils.REQUEST_METHOD_POST);
                httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
                httpURLConnection.setRequestProperty("EncryptVersion", DedicatedNetConstants.Report.ITEM_STATUS_CLOSE);
                DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                dataOutputStream.write(bArr);
                dataOutputStream.close();
                int responseCode = httpURLConnection.getResponseCode();
                Log.d("RiskyAppRequest", "doPostRequest responseCode=" + responseCode);
                String headerField = httpURLConnection.getHeaderField("EncryptVersion");
                if (responseCode != 200) {
                    httpURLConnection.disconnect();
                    return null;
                }
                if (!DedicatedNetConstants.Report.ITEM_STATUS_CLOSE.equals(headerField)) {
                    Log.e("RiskyAppRequest", "doPostRequest: EncryptVersion header mismatch or not found, got: " + headerField);
                    httpURLConnection.disconnect();
                    return null;
                }
                InputStream inputStream3 = httpURLConnection.getInputStream();
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        byte[] bArr2 = new byte[4096];
                        while (true) {
                            int i10 = inputStream3.read(bArr2);
                            if (i10 == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr2, 0, i10);
                        }
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        try {
                            inputStream3.close();
                        } catch (Exception unused) {
                        }
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception unused2) {
                        }
                        httpURLConnection.disconnect();
                        return byteArray;
                    } catch (Exception e10) {
                        inputStream = inputStream3;
                        e = e10;
                        r11 = byteArrayOutputStream;
                        try {
                            Log.e("RiskyAppRequest", "doPostRequest failed", e);
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Exception unused3) {
                                }
                            }
                            if (r11 != 0) {
                                try {
                                    r11.close();
                                } catch (Exception unused4) {
                                }
                            }
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            return null;
                        } catch (Throwable th2) {
                            th = th2;
                            inputStream2 = inputStream;
                            r10 = r11;
                            if (inputStream2 != null) {
                                try {
                                    inputStream2.close();
                                } catch (Exception unused5) {
                                }
                            }
                            if (r10 != 0) {
                                try {
                                    r10.close();
                                } catch (Exception unused6) {
                                }
                            }
                            if (httpURLConnection != null) {
                                throw th;
                            }
                            httpURLConnection.disconnect();
                            throw th;
                        }
                    } catch (Throwable th3) {
                        inputStream2 = inputStream3;
                        th = th3;
                        r10 = byteArrayOutputStream;
                        if (inputStream2 != null) {
                            inputStream2.close();
                        }
                        if (r10 != 0) {
                            r10.close();
                        }
                        if (httpURLConnection != null) {
                            throw th;
                        }
                        httpURLConnection.disconnect();
                        throw th;
                    }
                } catch (Exception e11) {
                    inputStream = inputStream3;
                    e = e11;
                    r11 = 0;
                } catch (Throwable th4) {
                    inputStream2 = inputStream3;
                    th = th4;
                    r10 = 0;
                }
            } catch (Exception e12) {
                e = e12;
                inputStream = null;
                r11 = inputStream;
                Log.e("RiskyAppRequest", "doPostRequest failed", e);
                if (inputStream != null) {
                    inputStream.close();
                }
                if (r11 != 0) {
                    r11.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return null;
            } catch (Throwable th5) {
                th = th5;
                r10 = 0;
            }
        } catch (Exception e13) {
            e = e13;
            httpURLConnection = null;
            inputStream = null;
        } catch (Throwable th6) {
            th = th6;
            httpURLConnection = null;
            r10 = 0;
        }
    }

    private static List e(byte[] bArr) {
        if (bArr == null) {
            Log.e("RiskyAppRequest", "parseResponse: server response is null");
            return null;
        }
        byte[] bArrA = b.a(bArr, "wcw1xbplx7aijwqr");
        if (bArrA == null) {
            Log.e("RiskyAppRequest", "parseResponse: decrypt failed");
            return null;
        }
        String strI = gf.a.i(bArrA);
        if (strI == null) {
            Log.e("RiskyAppRequest", "parseResponse: unzip failed");
            return null;
        }
        Log.d("RiskyAppRequest", "requestCloudScanV2 response=" + strI);
        CloudScanResponse cloudScanResponse = (CloudScanResponse) new Gson().fromJson(strI, CloudScanResponse.class);
        if (cloudScanResponse != null && cloudScanResponse.isSuccess()) {
            return cloudScanResponse.getData();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("parseResponse: code=");
        sb2.append(cloudScanResponse != null ? Integer.valueOf(cloudScanResponse.getCode()) : "null");
        sb2.append(", desc=");
        sb2.append(cloudScanResponse != null ? cloudScanResponse.getDesc() : "null");
        Log.e("RiskyAppRequest", sb2.toString());
        return null;
    }

    public static List f(Context context, List list) {
        if (list == null || list.isEmpty()) {
            Log.w("RiskyAppRequest", "requestCloudScanV2: apkInfoList is null or empty");
            return null;
        }
        try {
            Map mapA = a(context, 3);
            mapA.put("sign", l.r(mapA, "62b36629-ecb4-4725-9f33-ccb0fd158191"));
            String strC = c("https://virus-engine.sec.miui.com/risk/scan", mapA);
            byte[] bArrB = b(list);
            if (bArrB == null) {
                return null;
            }
            return e(d(strC, bArrB));
        } catch (Exception e10) {
            Log.e("RiskyAppRequest", "requestCloudScanV2 failed", e10);
            return null;
        }
    }

    public static RiskyAppDescriptionList g(Context context, String str) {
        String str2;
        HashMap map = new HashMap();
        map.put("timestamp", System.currentTimeMillis() + "");
        map.put("dataVersion", str);
        map.put("oaid", y.a(context));
        map.put("osVersion", f2.c("ro.mi.os.version.code", ""));
        map.put("device", miui.os.Build.PRODUCT);
        if (miui.os.Build.IS_STABLE_VERSION) {
            str2 = "stable";
        } else {
            str2 = miui.os.Build.IS_DEVELOPMENT_VERSION ? "development" : AnimatedProperty.PROPERTY_NAME_ALPHA;
        }
        map.put(Constants.JSON_KEY_VERSION_TYPE, str2);
        Log.d("RiskyAppRequest", "request dataVersion=" + str);
        try {
            byte[] bArrA = b.a(l.o(map, "https://virus-engine.sec.miui.com/risk/issueGrade", "62b36629-ecb4-4725-9f33-ccb0fd158191", new i("riskyapp_loadriskyappinfo")), "wcw1xbplx7aijwqr");
            if (bArrA == null) {
                Log.e("RiskyAppRequest", "decrypted is null");
                return null;
            }
            String strI = gf.a.i(bArrA);
            Log.d("RiskyAppRequest", "request success result=" + strI);
            JsonObject jsonObject = (JsonObject) new Gson().fromJson(strI, JsonObject.class);
            if (jsonObject.get("code").getAsInt() != 0) {
                return null;
            }
            return (RiskyAppDescriptionList) new Gson().fromJson(jsonObject.get("data"), RiskyAppDescriptionList.class);
        } catch (Exception e10) {
            Log.e("RiskyAppRequest", "requestRiskyApp failed", e10);
            return null;
        }
    }
}
