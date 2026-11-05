package z3;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.common.net.HttpHeaders;
import com.miui.antipeeping.v2.provider.PeepProtectionPageProvider;
import com.miui.common.utils.f2;
import com.miui.luckymoney.model.message.Impl.QQMessage;
import com.miui.securitycenter.Application;
import com.miui.securitykey.ConfigManager;
import com.miui.warningcenter.caraccident.utils.NetworkUtils;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import miui.security.SecurityManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public abstract class k {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static volatile Map f49258b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final List f49257a = Collections.unmodifiableList(Arrays.asList(SecurityManager.SKIP_INTERCEPT_PACKAGE, "com.miui.notes", "com.android.fileexplorer", "com.android.mms", "com.android.contacts", "com.android.browser", "com.miui.passwords", "com.google.android.gm", "com.google.android.apps.messaging", "com.google.android.apps.photos", "com.google.android.contacts", "com.google.android.dialer"));

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final AtomicBoolean f49259c = new AtomicBoolean(false);

    public static /* synthetic */ void a(Context context) {
        try {
            try {
                Map mapF = f(context);
                if (mapF.isEmpty()) {
                    mapF = d(context);
                }
                if (mapF.isEmpty()) {
                    Log.i("ANTI.V2CloudManager", "refreshFromCloudAsync: cloud empty, keep last snapshot");
                } else {
                    u(context, mapF);
                    f49258b = null;
                    Log.i("ANTI.V2CloudManager", "refreshFromCloudAsync: refreshed " + mapF.size() + " entries");
                    s(context);
                }
            } catch (Exception e10) {
                Log.e("ANTI.V2CloudManager", "refreshFromCloudAsync failed", e10);
            }
        } finally {
            f49259c.set(false);
        }
    }

    private static void b(Map map) {
        if (map == null) {
            return;
        }
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (entry.getValue() != null && ((Integer) entry.getValue()).intValue() == 3) {
                it.remove();
            }
        }
        Iterator it2 = f49257a.iterator();
        while (it2.hasNext()) {
            map.put((String) it2.next(), 3);
        }
    }

    public static Map c() {
        HashMap map;
        if (f49258b != null) {
            return f49258b;
        }
        Application applicationB = Application.B();
        Map mapO = o(applicationB);
        Map mapP = p(applicationB);
        if (mapP.isEmpty()) {
            map = new HashMap(mapO);
            Log.i("ANTI.V2CloudManager", "fetchAppTags: DB empty, persist asset(" + mapO.size() + ") to DB and trigger async refresh");
            u(applicationB, mapO);
            t(applicationB);
        } else {
            map = new HashMap(mapO);
            map.putAll(mapP);
            Log.i("ANTI.V2CloudManager", "fetchAppTags: merged asset(" + mapO.size() + ") + DB(" + mapP.size() + ") = " + map.size() + " entries");
            if (System.currentTimeMillis() - i(applicationB) > 86400000) {
                Log.i("ANTI.V2CloudManager", "fetchAppTags: DB stale (>24h), trigger async refresh");
                t(applicationB);
            }
        }
        b(map);
        f49258b = Collections.unmodifiableMap(map);
        return f49258b;
    }

    private static Map d(Context context) {
        return e(context, j(context), 10000);
    }

    private static Map e(Context context, List list, int i10) {
        String strK;
        HashMap map = new HashMap();
        if (list != null) {
            try {
                if (!list.isEmpty()) {
                    JSONObject jSONObject = new JSONObject();
                    JSONArray jSONArray = new JSONArray();
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        jSONArray.put((String) it.next());
                    }
                    jSONObject.put("apps", jSONArray);
                    jSONObject.put("type", com.miui.common.utils.j0.i());
                    jSONObject.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
                    jSONObject.put("os", "MIUI_" + Build.VERSION.INCREMENTAL);
                    jSONObject.put("device", f2.c("ro.product.device", "unknown"));
                    jSONObject.put("biz_id", "rivacy_risk_check");
                    String string = jSONObject.toString();
                    String securityHttpKey = ConfigManager.getSecurityHttpKey();
                    byte[] bArrE = gf.a.e(gf.a.d(string, securityHttpKey));
                    if (bArrE != null && (strK = k("https://flash.sec.miui.com/user/cat", bArrE, securityHttpKey, i10)) != null && !strK.isEmpty()) {
                        Log.i("ANTI.V2CloudManager", "fetchFromCategoryApi response: " + strK);
                        JSONObject jSONObject2 = new JSONObject(strK);
                        Iterator<String> itKeys = jSONObject2.keys();
                        while (itKeys.hasNext()) {
                            String next = itKeys.next();
                            int iQ = q(jSONObject2.optString(next, ""));
                            if (iQ != -1) {
                                map.put(next, Integer.valueOf(iQ));
                            }
                        }
                    }
                }
            } catch (Exception e10) {
                Log.e("ANTI.V2CloudManager", "fetchFromCategoryApi failed", e10);
            }
        }
        return map;
    }

    private static Map f(Context context) {
        HashMap map = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(com.miui.common.utils.s.d(context, "PeepProtectionV2", "app_category_tags", "{}"));
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                map.put(next, Integer.valueOf(jSONObject.optInt(next, -1)));
            }
            return map;
        } catch (Exception e10) {
            Log.e("ANTI.V2CloudManager", "fetchAppTags from cloud failed", e10);
            return map;
        }
    }

    public static int g(Context context, String str) {
        if (context != null && str != null && !str.isEmpty()) {
            try {
                Integer num = (Integer) e(context, Collections.singletonList(str), QQMessage.TYPE_DISCUSS_GROUP).get(str);
                if (num == null) {
                    return -1;
                }
                if (num.intValue() == 3 && !f49257a.contains(str)) {
                    num = -1;
                }
                r(context, str, num.intValue());
                Log.i("ANTI.V2CloudManager", "fetchSingleAppTag: " + str + " -> " + num);
                return num.intValue();
            } catch (Exception e10) {
                Log.e("ANTI.V2CloudManager", "fetchSingleAppTag failed: " + str, e10);
            }
        }
        return -1;
    }

    public static int h(String str) {
        Integer num = (Integer) c().get(str);
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }

    private static long i(Context context) {
        try {
            Cursor cursorRawQuery = c4.d.A(context).getReadableDatabase().rawQuery("SELECT MAX(update_time) FROM peep_protection_app_tags", null);
            try {
                if (!cursorRawQuery.moveToFirst()) {
                    cursorRawQuery.close();
                    return 0L;
                }
                long j10 = cursorRawQuery.getLong(0);
                cursorRawQuery.close();
                return j10;
            } catch (Throwable th2) {
                if (cursorRawQuery != null) {
                    try {
                        cursorRawQuery.close();
                    } catch (Throwable th3) {
                        th2.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        } catch (Exception e10) {
            Log.e("ANTI.V2CloudManager", "getDbUpdateTime failed", e10);
            return 0L;
        }
        Log.e("ANTI.V2CloudManager", "getDbUpdateTime failed", e10);
        return 0L;
    }

    private static List j(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            for (ApplicationInfo applicationInfo : context.getPackageManager().getInstalledApplications(0)) {
                if ((applicationInfo.flags & 1) == 0) {
                    arrayList.add(applicationInfo.packageName);
                }
            }
            return arrayList;
        } catch (Exception e10) {
            Log.e("ANTI.V2CloudManager", "getInstalledPackages failed", e10);
            return arrayList;
        }
    }

    /* JADX WARN: Code duplicated, block: B:53:0x00e8 A[PHI: r2
      0x00e8: PHI (r2v3 java.net.HttpURLConnection) = (r2v2 java.net.HttpURLConnection), (r2v6 java.net.HttpURLConnection) binds: [B:69:0x0113, B:7:0x0057] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:73:0x0119  */
    /* JADX WARN: Code duplicated, block: B:75:0x00df A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x009c: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:25:0x009c */
    private static String k(String str, byte[] bArr, String str2, int i10) throws Throwable {
        HttpURLConnection httpURLConnection;
        HttpURLConnection httpURLConnection2;
        HttpURLConnection httpURLConnection3 = null;
        try {
            try {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                try {
                    httpURLConnection.setConnectTimeout(i10);
                    httpURLConnection.setReadTimeout(i10);
                    httpURLConnection.setRequestMethod(NetworkUtils.REQUEST_METHOD_POST);
                    httpURLConnection.setRequestProperty("Charset", C.UTF8_NAME);
                    httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "text/xml");
                    httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_LENGTH, String.valueOf(bArr.length));
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.connect();
                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    try {
                        dataOutputStream.write(bArr);
                        dataOutputStream.flush();
                        dataOutputStream.close();
                        if (httpURLConnection.getResponseCode() == 200) {
                            InputStream inputStream = httpURLConnection.getInputStream();
                            try {
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                try {
                                    byte[] bArr2 = new byte[4096];
                                    while (true) {
                                        int i11 = inputStream.read(bArr2);
                                        if (i11 == -1) {
                                            break;
                                        }
                                        byteArrayOutputStream.write(bArr2, 0, i11);
                                        if (inputStream != null) {
                                            try {
                                                inputStream.close();
                                            } catch (Throwable th2) {
                                                th.addSuppressed(th2);
                                            }
                                        }
                                        throw th;
                                    }
                                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                                    byteArrayOutputStream.close();
                                    inputStream.close();
                                    String strI = gf.a.i(byteArray);
                                    if (strI == null) {
                                        Log.e("ANTI.V2CloudManager", "httpPost: uncompress returned null, url=" + str);
                                        httpURLConnection.disconnect();
                                        return null;
                                    }
                                    String strC = gf.a.c(strI, str2);
                                    if (strC != null) {
                                        String strOptString = new JSONObject(strC).optString("data");
                                        httpURLConnection.disconnect();
                                        return strOptString;
                                    }
                                    Log.e("ANTI.V2CloudManager", "httpPost: decrypt returned null, url=" + str);
                                    httpURLConnection.disconnect();
                                    return null;
                                } catch (Throwable th3) {
                                    try {
                                        byteArrayOutputStream.close();
                                    } catch (Throwable th4) {
                                        th3.addSuppressed(th4);
                                    }
                                    throw th3;
                                }
                            } catch (Throwable th5) {
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                throw th5;
                            }
                        }
                    } catch (Throwable th6) {
                        try {
                            dataOutputStream.close();
                        } catch (Throwable th7) {
                            th6.addSuppressed(th7);
                        }
                        throw th6;
                    }
                } catch (IOException e10) {
                    e = e10;
                    Log.e("ANTI.V2CloudManager", "httpPost failed: " + str, e);
                    if (httpURLConnection != null) {
                    }
                    return null;
                } catch (RuntimeException e11) {
                    e = e11;
                    Log.e("ANTI.V2CloudManager", "httpPost failed: " + str, e);
                    if (httpURLConnection != null) {
                    }
                    return null;
                } catch (JSONException e12) {
                    e = e12;
                    Log.e("ANTI.V2CloudManager", "httpPost failed: " + str, e);
                    if (httpURLConnection != null) {
                    }
                    return null;
                }
            } catch (Throwable th8) {
                th = th8;
                httpURLConnection3 = httpURLConnection2;
                if (httpURLConnection3 != null) {
                    httpURLConnection3.disconnect();
                }
                throw th;
            }
        } catch (IOException e13) {
            e = e13;
            httpURLConnection = null;
            Log.e("ANTI.V2CloudManager", "httpPost failed: " + str, e);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return null;
        } catch (RuntimeException e14) {
            e = e14;
            httpURLConnection = null;
            Log.e("ANTI.V2CloudManager", "httpPost failed: " + str, e);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return null;
        } catch (JSONException e15) {
            e = e15;
            httpURLConnection = null;
            Log.e("ANTI.V2CloudManager", "httpPost failed: " + str, e);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return null;
        } catch (Throwable th9) {
            th = th9;
            if (httpURLConnection3 != null) {
                httpURLConnection3.disconnect();
            }
            throw th;
        }
        httpURLConnection.disconnect();
        return null;
    }

    public static void l() {
        f49258b = null;
    }

    public static void m(Context context) {
        f49258b = null;
        try {
            c4.d.A(context).getWritableDatabase().delete("peep_protection_app_tags", null, null);
            Log.i("ANTI.V2CloudManager", "invalidateCacheAndDb: cleared");
        } catch (Exception e10) {
            Log.e("ANTI.V2CloudManager", "invalidateCacheAndDb failed", e10);
        }
    }

    public static boolean n(String str) {
        int iH = h(str);
        return iH == 1 || iH == 2 || iH == 3;
    }

    /* JADX WARN: Code duplicated, block: B:41:0x0073 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    private static Map o(Context context) {
        HashMap map = new HashMap();
        try {
            InputStream inputStreamOpen = context.getAssets().open("peep_protection_default_app_tags.json");
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamOpen, StandardCharsets.UTF_8));
                try {
                    StringBuilder sb2 = new StringBuilder();
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        sb2.append(line);
                        if (inputStreamOpen != null) {
                            try {
                                inputStreamOpen.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        }
                        throw th;
                    }
                    JSONObject jSONObjectOptJSONObject = new JSONObject(sb2.toString()).optJSONObject("tags");
                    if (jSONObjectOptJSONObject != null) {
                        Iterator<String> itKeys = jSONObjectOptJSONObject.keys();
                        while (itKeys.hasNext()) {
                            String next = itKeys.next();
                            map.put(next, Integer.valueOf(jSONObjectOptJSONObject.optInt(next, -1)));
                        }
                    }
                    bufferedReader.close();
                    if (inputStreamOpen == null) {
                        return map;
                    }
                    inputStreamOpen.close();
                    return map;
                } catch (Throwable th3) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable th4) {
                        th3.addSuppressed(th4);
                    }
                    throw th3;
                }
            } catch (Throwable th5) {
                if (inputStreamOpen != null) {
                    inputStreamOpen.close();
                }
                throw th5;
            }
        } catch (Exception e10) {
            Log.e("ANTI.V2CloudManager", "loadDefaultAppTagsFromAssets failed", e10);
            return map;
        }
    }

    private static Map p(Context context) {
        HashMap map = new HashMap();
        try {
            Cursor cursorQuery = c4.d.A(context).getReadableDatabase().query("peep_protection_app_tags", new String[]{"package_name", "scene_tag"}, null, null, null, null, null);
            while (cursorQuery.moveToNext()) {
                try {
                    map.put(cursorQuery.getString(0), Integer.valueOf(cursorQuery.getInt(1)));
                } catch (Throwable th2) {
                    if (cursorQuery == null) {
                        throw th2;
                    }
                    try {
                        cursorQuery.close();
                        throw th2;
                    } catch (Throwable th3) {
                        th2.addSuppressed(th3);
                        throw th2;
                    }
                }
            }
            cursorQuery.close();
            return map;
        } catch (Exception e10) {
            Log.e("ANTI.V2CloudManager", "loadFromDb failed", e10);
            return map;
        }
    }

    private static int q(String str) {
        str.getClass();
        switch (str) {
            case "1":
                return 1;
            case "2":
                return 2;
            case "5":
            case "6":
            case "7":
            case "9":
            case "14":
            case "27":
                return 3;
            default:
                return -1;
        }
    }

    private static void r(Context context, String str, int i10) {
        try {
            SQLiteDatabase writableDatabase = c4.d.A(context).getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("package_name", str);
            contentValues.put("scene_tag", Integer.valueOf(i10));
            contentValues.put("update_time", Long.valueOf(System.currentTimeMillis()));
            writableDatabase.insertWithOnConflict("peep_protection_app_tags", null, contentValues, 5);
        } catch (Exception e10) {
            Log.e("ANTI.V2CloudManager", "mergeSingleTagToDbAndCache DB write failed: " + str, e10);
        }
        f49258b = null;
    }

    private static void s(Context context) {
        int iV;
        try {
            c4.d dVarA = c4.d.A(context);
            List<c4.b> listM0 = dVarA.m0();
            if (listM0 != null && !listM0.isEmpty()) {
                Map mapC = c();
                int i10 = 0;
                for (c4.b bVar : listM0) {
                    if (TtmlNode.TEXT_EMPHASIS_AUTO.equals(bVar.e()) && bVar.b() == 3) {
                        Integer num = (Integer) mapC.get(bVar.c());
                        int iIntValue = num != null ? num.intValue() : -1;
                        if (iIntValue != -1 && (iV = c4.d.V(iIntValue)) != 3) {
                            dVarA.L0(bVar.c(), bVar.h(), iV);
                            i10++;
                        }
                    }
                }
                if (i10 > 0) {
                    Log.i("ANTI.V2CloudManager", "reEvaluateOtherCategoryApps: re-categorized " + i10 + " apps");
                    PeepProtectionPageProvider.notifyAppsChanged(context);
                }
            }
        } catch (Exception e10) {
            Log.e("ANTI.V2CloudManager", "reEvaluateOtherCategoryApps failed", e10);
        }
    }

    public static void t(final Context context) {
        if (context != null && f49259c.compareAndSet(false, true)) {
            aj.c0.e().a(new Runnable() { // from class: z3.j
                @Override // java.lang.Runnable
                public final void run() {
                    k.a(context);
                }
            });
        }
    }

    private static void u(Context context, Map map) {
        try {
            HashMap map2 = new HashMap(o(context));
            if (map != null) {
                map2.putAll(map);
            }
            SQLiteDatabase writableDatabase = c4.d.A(context).getWritableDatabase();
            writableDatabase.beginTransaction();
            try {
                writableDatabase.delete("peep_protection_app_tags", null, null);
                long jCurrentTimeMillis = System.currentTimeMillis();
                for (Map.Entry entry : map2.entrySet()) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("package_name", (String) entry.getKey());
                    contentValues.put("scene_tag", (Integer) entry.getValue());
                    contentValues.put("update_time", Long.valueOf(jCurrentTimeMillis));
                    writableDatabase.insertWithOnConflict("peep_protection_app_tags", null, contentValues, 5);
                }
                writableDatabase.setTransactionSuccessful();
                Log.i("ANTI.V2CloudManager", "saveToDb: saved " + map2.size() + " app tags (asset merged)");
            } finally {
                writableDatabase.endTransaction();
            }
        } catch (Exception e10) {
            Log.e("ANTI.V2CloudManager", "saveToDb failed", e10);
        }
    }
}
