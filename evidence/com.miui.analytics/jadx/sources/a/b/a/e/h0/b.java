package a.b.a.e.h0;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.Signature;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import com.miui.analytics.log.internal.Level;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class b extends a.b.a.e.f {
    public static HashMap<String, Integer> g;
    public static final Uri h;
    public static final Uri i;
    public static final Uri j;

    static {
        Uri uri = Uri.parse("content://com.miui.micloud");
        h = uri;
        i = Uri.withAppendedPath(uri, "cloud_backup_info");
        Uri uri2 = Uri.parse("content://com.miui.securitycenter.provider");
        j = uri2;
        Uri.withAppendedPath(uri2, "hideapp");
        HashMap<String, Integer> map = new HashMap<>();
        g = map;
        map.put("com.android.contacts", 2);
        g.put("sms", 4);
        g.put("notes", 16);
        g.put("com.miui.gallery.cloud.provider", 32);
        g.put("wifi", 64);
        g.put("call_log", 128);
        g.put("records", 256);
        g.put("antispam", 512);
        g.put("com.android.calendar", 1024);
        g.put("com.miui.browser", 2048);
        g.put("com.miui.player", 4096);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public b(boolean z) throws Throwable {
        JSONObject jSONObject;
        this.f309a = "001";
        this.f310b = a.b.a.e.e.f264d;
        this.f312d = 0;
        this.f311c = "onetrack_active";
        try {
            JSONObject jSONObjectA = a();
            JSONObject jSONObjectF = f(z);
            jSONObject = new JSONObject();
            jSONObject.put("H", jSONObjectA);
            jSONObject.put("B", jSONObjectF);
        } catch (JSONException e) {
            a.b.a.a.a.c.a.s(z, "ActiveEvent", "create active event error", e);
            jSONObject = null;
        }
        this.e = jSONObject;
        this.f = System.currentTimeMillis();
    }

    public static void d(JSONObject jSONObject, String str, String str2, String str3, String str4, boolean z) {
        try {
            jSONObject.put("tz_content", str);
            jSONObject.put("tz_fid", str2);
            jSONObject.put("tz_nonce", str3);
            jSONObject.put("tz_sign", str4);
            jSONObject.put("tz_support", true);
            jSONObject.put("tz_cpuid", g(str2, z));
        } catch (Exception e) {
            a.b.a.a.a.c.a.s(z, "ActiveEvent", "addDeviceSignWithNonce e: ", e);
        }
    }

    public static void e(JSONObject jSONObject, String str, String str2, int i2, String str3, boolean z) {
        try {
            jSONObject.put("tz_content", str);
            jSONObject.put("tz_fid", str2);
            jSONObject.put("tz_sign", str3);
            jSONObject.put("tz_no_nonce_reason", i2);
            jSONObject.put("tz_support", true);
            jSONObject.put("tz_cpuid", g(str2, z));
        } catch (Exception e) {
            a.b.a.a.a.c.a.s(z, "ActiveEvent", "addDeviceSignWithoutNonce e: ", e);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:13:0x005d */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:15:0x005f */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:41:0x0047 */
    /* JADX WARN: Code duplicated, block: B:27:0x0086  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v10, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v9, types: [java.io.Closeable] */
    public static String g(String str, boolean z) throws Throwable {
        int iLastIndexOf;
        BufferedReader bufferedReader;
        Exception e;
        String line = "";
        try {
            String strG = a.b.a.a.a.c.a.G("ro.boot.cpuid", "");
            try {
                a.b.a.a.a.c.a.m(z, "ActiveEvent", "cpuId get from ro.boot.cpuid: " + strG);
                if (TextUtils.isEmpty(strG)) {
                    String strA = a.b.a.e.l0.h.a("vendor");
                    a.b.a.a.a.c.a.m(z, "ActiveEvent", "vendor: " + strG);
                    ?? r4 = "qcom";
                    if ("qcom".equals(strA)) {
                        ?? r3 = 0;
                        try {
                            try {
                                bufferedReader = new BufferedReader(new FileReader("/proc/serial_num"), 256);
                                try {
                                    line = bufferedReader.readLine();
                                    r4 = bufferedReader;
                                } catch (Exception e2) {
                                    e = e2;
                                    a.b.a.a.a.c.a.s(z, "ActiveEvent", "getSeriNumByQcom e: ", e);
                                    r4 = bufferedReader;
                                }
                            } catch (Throwable th) {
                                th = th;
                                r3 = r4;
                                a.b.a.a.a.c.a.g(r3);
                                throw th;
                            }
                        } catch (Exception e3) {
                            bufferedReader = null;
                            e = e3;
                        } catch (Throwable th2) {
                            th = th2;
                            a.b.a.a.a.c.a.g(r3);
                            throw th;
                        }
                        a.b.a.a.a.c.a.g(r4);
                        a.b.a.a.a.c.a.m(z, "ActiveEvent", "cpuId get from serial_num: " + line);
                    } else {
                        line = strG;
                    }
                } else {
                    line = strG;
                }
                if (!TextUtils.isEmpty(line) || TextUtils.isEmpty(str) || (iLastIndexOf = str.lastIndexOf("_")) == -1) {
                    return line;
                }
                return "0x" + str.substring(iLastIndexOf + 1);
            } catch (Exception e4) {
                e = e4;
                line = strG;
            }
        } catch (Exception e5) {
            e = e5;
        }
        a.b.a.a.a.c.a.s(z, "ActiveEvent", "getCpuId e: ", e);
        return line;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX WARN: Code duplicated, block: B:9:0x001f A[Catch: Exception -> 0x0022, TryCatch #0 {Exception -> 0x0022, blocks: (B:3:0x0005, B:7:0x0014, B:13:0x0025, B:15:0x0045, B:16:0x004f, B:18:0x0056, B:19:0x005f, B:21:0x0065, B:23:0x006b, B:24:0x006d, B:9:0x001f), top: B:31:0x0005 }] */
    public static String h(Context context, boolean z) {
        String str;
        JSONObject jSONObject = new JSONObject();
        try {
            String strW = a.b.a.e.l0.c.w(context);
            if (TextUtils.isEmpty(a.b.a.e.l0.c.f565b)) {
                a.b.a.e.l0.c.K(context);
                if (TextUtils.isEmpty(a.b.a.e.l0.c.f565b)) {
                    str = "";
                } else {
                    str = a.b.a.e.l0.c.f565b;
                }
            } else {
                str = a.b.a.e.l0.c.f565b;
            }
            jSONObject.put("imei1", strW);
            jSONObject.put("imei2", str);
            jSONObject.put("sn", a.b.a.e.l0.c.x());
            ArrayList arrayList = (ArrayList) a.b.a.e.l0.c.o(context);
            if (arrayList.size() >= 1) {
                jSONObject.put("i1", arrayList.get(0));
            }
            if (arrayList.size() >= 2) {
                jSONObject.put("i2", arrayList.get(1));
            }
            if (!a.b.a.e.l0.r.q()) {
                Account accountD = a.b.a.e.l0.c.D(context);
                jSONObject.put("mid", accountD != null ? accountD.name : "");
            }
        } catch (Exception e) {
            a.b.a.e.l0.p.d("ActiveEvent", "getTZContentJson e: ", e);
            a.b.a.d.b.d dVarV = a.b.a.a.a.c.a.V(z);
            if (dVarV != null) {
                dVarV.a(Level.DEBUG, "##XLogger##, TAG: ", "ActiveEvent: getTZContentJson e: ", e);
            }
        }
        return jSONObject.toString();
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:192:0x04b7 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:335:0x08d7 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:354:0x0454 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:55:0x01a8 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:59:0x01ad */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:73:0x01c9 */
    /* JADX WARN: Code duplicated, block: B:101:0x0294 A[Catch: Exception -> 0x026a, TRY_LEAVE, TryCatch #2 {Exception -> 0x026a, blocks: (B:88:0x022d, B:104:0x02b7, B:91:0x0246, B:93:0x025f, B:98:0x0270, B:100:0x0289, B:101:0x0294, B:103:0x02ad), top: B:352:0x0227 }] */
    /* JADX WARN: Code duplicated, block: B:112:0x02ca  */
    /* JADX WARN: Code duplicated, block: B:114:0x02d7  */
    /* JADX WARN: Code duplicated, block: B:115:0x02db  */
    /* JADX WARN: Code duplicated, block: B:117:0x02e4  */
    /* JADX WARN: Code duplicated, block: B:120:0x0309 A[PHI: r16 r26
      0x0309: PHI (r16v5 java.lang.String) = (r16v2 java.lang.String), (r16v6 java.lang.String) binds: [B:111:0x02c4, B:119:0x0307] A[DONT_GENERATE, DONT_INLINE]
      0x0309: PHI (r26v7 long) = (r26v1 long), (r26v8 long) binds: [B:111:0x02c4, B:119:0x0307] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:121:0x030b  */
    /* JADX WARN: Code duplicated, block: B:123:0x0311  */
    /* JADX WARN: Code duplicated, block: B:124:0x0315  */
    /* JADX WARN: Code duplicated, block: B:127:0x031b  */
    /* JADX WARN: Code duplicated, block: B:130:0x0323  */
    /* JADX WARN: Code duplicated, block: B:133:0x032b  */
    /* JADX WARN: Code duplicated, block: B:136:0x0334  */
    /* JADX WARN: Code duplicated, block: B:160:0x03f0  */
    /* JADX WARN: Code duplicated, block: B:162:0x03f6  */
    /* JADX WARN: Code duplicated, block: B:163:0x03fb  */
    /* JADX WARN: Code duplicated, block: B:166:0x0402  */
    /* JADX WARN: Code duplicated, block: B:167:0x0405  */
    /* JADX WARN: Code duplicated, block: B:170:0x040b  */
    /* JADX WARN: Code duplicated, block: B:171:0x0410  */
    /* JADX WARN: Code duplicated, block: B:174:0x0416  */
    /* JADX WARN: Code duplicated, block: B:175:0x0425  */
    /* JADX WARN: Code duplicated, block: B:178:0x042b  */
    /* JADX WARN: Code duplicated, block: B:179:0x042e  */
    /* JADX WARN: Code duplicated, block: B:181:0x044d  */
    /* JADX WARN: Code duplicated, block: B:183:0x0456 A[Catch: Exception -> 0x04b7, TRY_ENTER, TRY_LEAVE, TryCatch #3 {Exception -> 0x04b7, blocks: (B:183:0x0456, B:186:0x0489), top: B:354:0x0454 }] */
    /* JADX WARN: Code duplicated, block: B:186:0x0489 A[Catch: Exception -> 0x04b7, TRY_ENTER, TRY_LEAVE, TryCatch #3 {Exception -> 0x04b7, blocks: (B:183:0x0456, B:186:0x0489), top: B:354:0x0454 }] */
    /* JADX WARN: Code duplicated, block: B:196:0x04c5  */
    /* JADX WARN: Code duplicated, block: B:197:0x04c8  */
    /* JADX WARN: Code duplicated, block: B:199:0x04ce  */
    /* JADX WARN: Code duplicated, block: B:200:0x04d3  */
    /* JADX WARN: Code duplicated, block: B:203:0x04da  */
    /* JADX WARN: Code duplicated, block: B:204:0x04dd  */
    /* JADX WARN: Code duplicated, block: B:207:0x04e3  */
    /* JADX WARN: Code duplicated, block: B:210:0x04ec  */
    /* JADX WARN: Code duplicated, block: B:211:0x04fb  */
    /* JADX WARN: Code duplicated, block: B:214:0x0501  */
    /* JADX WARN: Code duplicated, block: B:219:0x057b A[Catch: all -> 0x0596, TryCatch #28 {all -> 0x0596, blocks: (B:217:0x0567, B:219:0x057b, B:221:0x058b), top: B:395:0x0567 }] */
    /* JADX WARN: Code duplicated, block: B:223:0x0593 A[LOOP:1: B:218:0x0579->B:223:0x0593, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:230:0x0643 A[Catch: Exception -> 0x0660, TRY_LEAVE, TryCatch #9 {Exception -> 0x0660, blocks: (B:228:0x05d4, B:230:0x0643), top: B:358:0x05d4 }] */
    /* JADX WARN: Code duplicated, block: B:236:0x0676 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:237:0x0678  */
    /* JADX WARN: Code duplicated, block: B:238:0x067b  */
    /* JADX WARN: Code duplicated, block: B:241:0x0682  */
    /* JADX WARN: Code duplicated, block: B:245:0x0691  */
    /* JADX WARN: Code duplicated, block: B:250:0x06a7 A[Catch: Exception -> 0x070a, TRY_LEAVE, TryCatch #18 {Exception -> 0x070a, blocks: (B:248:0x06a1, B:250:0x06a7), top: B:375:0x06a1 }] */
    /* JADX WARN: Code duplicated, block: B:257:0x06d1 A[Catch: Exception -> 0x0708, TryCatch #30 {Exception -> 0x0708, blocks: (B:252:0x06bf, B:254:0x06c5, B:255:0x06c7, B:257:0x06d1, B:260:0x06d8, B:262:0x06e0), top: B:398:0x06bf }] */
    /* JADX WARN: Code duplicated, block: B:259:0x06d7  */
    /* JADX WARN: Code duplicated, block: B:260:0x06d8 A[Catch: Exception -> 0x0708, TryCatch #30 {Exception -> 0x0708, blocks: (B:252:0x06bf, B:254:0x06c5, B:255:0x06c7, B:257:0x06d1, B:260:0x06d8, B:262:0x06e0), top: B:398:0x06bf }] */
    /* JADX WARN: Code duplicated, block: B:262:0x06e0 A[Catch: Exception -> 0x0708, TRY_LEAVE, TryCatch #30 {Exception -> 0x0708, blocks: (B:252:0x06bf, B:254:0x06c5, B:255:0x06c7, B:257:0x06d1, B:260:0x06d8, B:262:0x06e0), top: B:398:0x06bf }] */
    /* JADX WARN: Code duplicated, block: B:265:0x06f9  */
    /* JADX WARN: Code duplicated, block: B:268:0x0700  */
    /* JADX WARN: Code duplicated, block: B:269:0x0703  */
    /* JADX WARN: Code duplicated, block: B:287:0x077b  */
    /* JADX WARN: Code duplicated, block: B:289:0x0795  */
    /* JADX WARN: Code duplicated, block: B:290:0x07c5  */
    /* JADX WARN: Code duplicated, block: B:293:0x07f0  */
    /* JADX WARN: Code duplicated, block: B:323:0x0890  */
    /* JADX WARN: Code duplicated, block: B:325:0x08b5  */
    /* JADX WARN: Code duplicated, block: B:327:0x08bd  */
    /* JADX WARN: Code duplicated, block: B:328:0x08bf  */
    /* JADX WARN: Code duplicated, block: B:332:0x08cf  */
    /* JADX WARN: Code duplicated, block: B:362:0x01c0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:373:0x0696 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:377:0x0370 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:387:0x01f6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:393:0x072e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:415:0x059c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:416:0x058b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:418:0x06c5 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:420:0x06a1 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:86:0x0229  */
    /* JADX WARN: Code duplicated, block: B:89:0x023f  */
    /* JADX WARN: Code duplicated, block: B:91:0x0246 A[Catch: Exception -> 0x026a, TRY_LEAVE, TryCatch #2 {Exception -> 0x026a, blocks: (B:88:0x022d, B:104:0x02b7, B:91:0x0246, B:93:0x025f, B:98:0x0270, B:100:0x0289, B:101:0x0294, B:103:0x02ad), top: B:352:0x0227 }] */
    /* JADX WARN: Code duplicated, block: B:96:0x026c  */
    /* JADX WARN: Code duplicated, block: B:98:0x0270 A[Catch: Exception -> 0x026a, TRY_LEAVE, TryCatch #2 {Exception -> 0x026a, blocks: (B:88:0x022d, B:104:0x02b7, B:91:0x0246, B:93:0x025f, B:98:0x0270, B:100:0x0289, B:101:0x0294, B:103:0x02ad), top: B:352:0x0227 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:230:0x0643, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:287:0x077b, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v214, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r0v266 */
    /* JADX WARN: Type inference failed for: r0v267 */
    /* JADX WARN: Type inference failed for: r23v4 */
    /* JADX WARN: Type inference failed for: r23v5 */
    /* JADX WARN: Type inference failed for: r23v6 */
    /* JADX WARN: Type inference failed for: r3v39 */
    /* JADX WARN: Type inference failed for: r3v40 */
    /* JADX WARN: Type inference failed for: r3v42 */
    /* JADX WARN: Type inference failed for: r3v43, types: [long] */
    /* JADX WARN: Type inference failed for: r3v64, types: [long] */
    /* JADX WARN: Type inference failed for: r3v71 */
    /* JADX WARN: Type inference failed for: r3v72 */
    /* JADX WARN: Type inference failed for: r3v73 */
    /* JADX WARN: Type inference failed for: r5v0, types: [int] */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v34, types: [java.io.FileReader] */
    /* JADX WARN: Type inference failed for: r5v35, types: [java.io.FileReader, java.io.Reader] */
    /* JADX WARN: Type inference failed for: r5v4, types: [java.io.FileReader] */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.util.Locale] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v23 */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.io.BufferedReader] */
    public JSONObject f(boolean z) throws Throwable {
        String strV;
        String strG;
        ?? fileReader;
        ?? r6;
        String str;
        List<String> listO;
        int i2;
        BufferedReader bufferedReader;
        long j2;
        String str2;
        String str3;
        long j3;
        String str4;
        long j4;
        DecimalFormat decimalFormat;
        StringBuilder sb;
        String string;
        int i3;
        String str5;
        String str6;
        long jB;
        float fFloor;
        String str7;
        String str8;
        String str9;
        String str10;
        long jLongValue;
        String strSubstring;
        boolean zBooleanValue;
        Account accountD;
        int i4;
        int i5;
        int i6;
        String key;
        int iIntValue;
        String strA;
        String strG2;
        String strG3;
        String strG4;
        String strG5;
        a.b.a.e.l0.l lVarK;
        String str11;
        String string2;
        boolean zF;
        String str12;
        String str13;
        a.b.a.d.b.d dVarV;
        Signature[] signatureArr;
        int length;
        int i7;
        String strC;
        ?? r3;
        ?? r4;
        Locale locale;
        float fFloor2;
        String str14;
        String str15;
        String str16;
        ?? sb2;
        long j5;
        long j6;
        float f;
        String str17;
        Context contextB = a.b.a.e.e.b();
        JSONObject jSONObject = new JSONObject();
        try {
            strV = a.b.a.e.l0.c.v("ro.product.model.real");
            if (TextUtils.isEmpty(strV)) {
                strV = Build.MODEL;
            }
        } catch (Exception e) {
            a.b.a.e.l0.p.h("DeviceUtil", "getModelReal Exception: ", e);
            strV = "";
        }
        jSONObject.put("real_model", strV);
        jSONObject.put("product", Build.PRODUCT);
        try {
            strG = a.b.a.a.a.c.a.G("ro.product.mod_device", "");
            if (TextUtils.isEmpty(strG)) {
                strG = Build.DEVICE;
            }
            while (true) {
                try {
                    ArrayList arrayList = (ArrayList) listO;
                    if (i2 >= arrayList.size()) {
                        break;
                    }
                    arrayList.set(i2, a.b.a.e.g0.d.b((String) arrayList.get(i2)));
                    i2++;
                } catch (Throwable th) {
                    a.b.a.a.a.c.a.s(z, "ActiveChecker", "getIMSIMd5 failed：", th);
                    listO = new ArrayList<>();
                }
            }
        } catch (Exception e2) {
            a.b.a.e.l0.p.h("DeviceUtil", "getModDevice Exception: ", e2);
            strG = "";
        }
        jSONObject.put("device", strG);
        jSONObject.put("device_type", a.b.a.e.l0.c.f());
        if (contextB == null) {
            str = "0*0";
        } else {
            DisplayMetrics displayMetrics = contextB.getResources().getDisplayMetrics();
            fileReader = displayMetrics.widthPixels;
            int i8 = displayMetrics.heightPixels;
            r6 = Locale.US;
            str = String.format(r6, "%d*%d", Integer.valueOf(i8), Integer.valueOf((int) fileReader));
        }
        jSONObject.put("screen", str);
        if (a.b.a.e.l0.r.q()) {
            jSONObject.put("imei", a.b.a.e.l0.c.h(contextB));
        }
        jSONObject.put("sn", a.b.a.e.l0.c.y());
        jSONObject.put("android_id", a.b.a.e.l0.c.d(contextB));
        jSONObject.put("vaid", a.b.a.e.l0.m.c(contextB));
        jSONObject.put("udid", a.b.a.e.l0.m.b(contextB));
        jSONObject.put("mac", a.b.a.e.l0.c.r(contextB));
        jSONObject.put("imeis", k(a.b.a.e.l0.c.l(contextB)));
        jSONObject.put("meids", k(a.b.a.e.l0.c.u(contextB)));
        listO = a.b.a.e.l0.c.o(contextB);
        if (a.b(listO) && !a.b.a.e.l0.u.b("r_i", false)) {
            a.b.a.e.l0.u.k("r_i", true);
        }
        i2 = 0;
        jSONObject.put("imsis", k(listO));
        jSONObject.put("oaid_stat", a.b.a.e.l0.r.h(contextB));
        jSONObject.put("language", a.b.a.e.l0.r.e());
        try {
            try {
                try {
                    fileReader = new FileReader("/proc/meminfo");
                    try {
                        bufferedReader = new BufferedReader(fileReader, 2048);
                        try {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                a.b.a.e.l0.p.g("OSUtil", "getTotalRAM: memoryLine is null");
                                try {
                                    bufferedReader.close();
                                } catch (IOException e3) {
                                    a.b.a.e.l0.p.h("OSUtil", "close BufferedReader failed: ", e3);
                                }
                                try {
                                    fileReader.close();
                                } catch (IOException e4) {
                                    a.b.a.e.l0.p.h("OSUtil", "close FileReader failed: ", e4);
                                }
                                j2 = 0;
                            } else {
                                String strReplaceAll = line.substring(line.indexOf("MemTotal:")).replaceAll("\\D+", "");
                                a.b.a.e.l0.p.c("OSUtil", "getTotalRAM, memoryLine: " + line + ", format: " + strReplaceAll);
                                j2 = ((long) Integer.parseInt(strReplaceAll)) * 1024;
                                try {
                                    bufferedReader.close();
                                } catch (IOException e5) {
                                    a.b.a.e.l0.p.h("OSUtil", "close BufferedReader failed: ", e5);
                                }
                                try {
                                    fileReader.close();
                                } catch (IOException e6) {
                                    a.b.a.e.l0.p.h("OSUtil", "close FileReader failed: ", e6);
                                }
                            }
                            while (true) {
                                if (i7 >= length) {
                                    strSubstring = "";
                                    break;
                                }
                                strC = a.b.a.e.g0.d.c(signatureArr[i7].toByteArray());
                                if (!TextUtils.isEmpty(strC)) {
                                    strSubstring = strC.substring(0, 8);
                                    break;
                                }
                                i7++;
                                strSubstring = "";
                                break;
                            }
                        } catch (Exception e7) {
                            e = e7;
                            a.b.a.e.l0.p.h("OSUtil", "getTotalRAM Exception: ", e);
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e8) {
                                    a.b.a.e.l0.p.h("OSUtil", "close BufferedReader failed: ", e8);
                                }
                            }
                            if (fileReader != 0) {
                            }
                            j2 = 0;
                            str2 = "MB";
                            str3 = "PB";
                            j3 = -1;
                            ?? r23 = -1;
                            if (Build.VERSION.SDK_INT <= 25) {
                                try {
                                    a.b.a.e.l0.p.c("OSUtil", "formatFileSize, size: " + j2);
                                    DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.US);
                                    str4 = "DeviceUtil";
                                    try {
                                        decimalFormat = new DecimalFormat("#0", decimalFormatSymbols);
                                        new DecimalFormat("#0.#", decimalFormatSymbols);
                                        j4 = 0;
                                        try {
                                            if (j2 >= 1000) {
                                                if (j2 < 1000000) {
                                                    sb = new StringBuilder();
                                                    double d2 = j2;
                                                    Double.isNaN(d2);
                                                    Double.isNaN(d2);
                                                    Double.isNaN(d2);
                                                    Double.isNaN(d2);
                                                    sb.append(decimalFormat.format(d2 / 1000.0d));
                                                    sb.append("KB");
                                                } else if (j2 < 1000000000) {
                                                    sb = new StringBuilder();
                                                    double d3 = j2;
                                                    Double.isNaN(d3);
                                                    Double.isNaN(d3);
                                                    Double.isNaN(d3);
                                                    Double.isNaN(d3);
                                                    sb.append(decimalFormat.format(d3 / 1000000.0d));
                                                    sb.append("MB");
                                                } else {
                                                    sb = new StringBuilder();
                                                    double d4 = j2;
                                                    Double.isNaN(d4);
                                                    Double.isNaN(d4);
                                                    Double.isNaN(d4);
                                                    Double.isNaN(d4);
                                                    sb.append(decimalFormat.format(d4 / 1.0E9d));
                                                    sb.append("GB");
                                                }
                                            } else if (j2 < 1000000) {
                                                sb = new StringBuilder();
                                                double d5 = j2;
                                                Double.isNaN(d5);
                                                Double.isNaN(d5);
                                                Double.isNaN(d5);
                                                Double.isNaN(d5);
                                                sb.append(decimalFormat.format(d5 / 1000.0d));
                                                sb.append("KB");
                                            } else if (j2 < 1000000000) {
                                                sb = new StringBuilder();
                                                double d6 = j2;
                                                Double.isNaN(d6);
                                                Double.isNaN(d6);
                                                Double.isNaN(d6);
                                                Double.isNaN(d6);
                                                sb.append(decimalFormat.format(d6 / 1000000.0d));
                                                sb.append("MB");
                                            } else {
                                                sb = new StringBuilder();
                                                double d7 = j2;
                                                Double.isNaN(d7);
                                                Double.isNaN(d7);
                                                Double.isNaN(d7);
                                                Double.isNaN(d7);
                                                sb.append(decimalFormat.format(d7 / 1.0E9d));
                                                sb.append("GB");
                                            }
                                            string = sb.toString();
                                        } catch (Exception e9) {
                                            e = e9;
                                            a.b.a.e.l0.p.h("OSUtil", "formatFileSize Exception: ", e);
                                            string = "";
                                        }
                                    } catch (Exception e10) {
                                        e = e10;
                                        j4 = 0;
                                        a.b.a.e.l0.p.h("OSUtil", "formatFileSize Exception: ", e);
                                        string = "";
                                        jSONObject.put("ram", string);
                                        i3 = Build.VERSION.SDK_INT;
                                        if (i3 > 33) {
                                            try {
                                                jLongValue = ((Long) a.b.a.a.a.c.a.d((StorageManager) contextB.getSystemService("storage"), "getPrimaryStorageSize", null, new Object[0])).longValue();
                                                try {
                                                    a.b.a.e.l0.p.c("StorageUtil", "getTotalMemoryBytesForAboveU->mTotalMemoryByte is " + jLongValue);
                                                    str5 = "B";
                                                    str6 = "KB";
                                                    long jA = ((long) a.b.a.e.l0.s.a(contextB)) * 1000000000;
                                                    try {
                                                        a.b.a.e.l0.p.c("StorageUtil", "getTotalMemoryBytesForAboveU->EUASupportSizeByte is " + jA);
                                                        j3 = jLongValue - jA;
                                                        try {
                                                            jB = a.b.a.e.l0.s.b(j3);
                                                        } catch (Exception e11) {
                                                            e = e11;
                                                            Log.e(a.b.a.e.l0.p.a("StorageUtil"), "getTotalMemoryBytes error, ", e);
                                                            jB = j3;
                                                        }
                                                    } catch (Exception e12) {
                                                        e = e12;
                                                        j3 = jLongValue;
                                                        Log.e(a.b.a.e.l0.p.a("StorageUtil"), "getTotalMemoryBytes error, ", e);
                                                        jB = j3;
                                                        a.b.a.e.l0.p.c("StorageUtil", "formatUFSShortSize->number is " + jB);
                                                        if (contextB == null) {
                                                            str10 = "";
                                                        } else {
                                                            fFloor = jB;
                                                            if (fFloor > 900.0d) {
                                                                fFloor /= 1000.0f;
                                                                str7 = str6;
                                                            } else {
                                                                str7 = str5;
                                                            }
                                                            if (fFloor > 900.0d) {
                                                                fFloor /= 1000.0f;
                                                            } else {
                                                                str2 = str7;
                                                            }
                                                            if (fFloor > 900.0d) {
                                                                fFloor /= 1000.0f;
                                                                str8 = "GB";
                                                            } else {
                                                                str8 = str2;
                                                            }
                                                            if (fFloor > 921.6d) {
                                                                fFloor = ((float) Math.floor((fFloor / 1024.0f) * 10.0f)) / 10.0f;
                                                                str9 = "TB";
                                                            } else {
                                                                str9 = str8;
                                                            }
                                                            if (fFloor > 921.6d) {
                                                                fFloor /= 1000.0f;
                                                            } else {
                                                                str3 = str9;
                                                            }
                                                            Locale locale2 = Locale.US;
                                                            str10 = String.format(locale2, "%s%s", String.format(locale2, "%.0f", Float.valueOf(fFloor)), str3);
                                                        }
                                                        jSONObject.put("rom", str10);
                                                        jSONObject.put("ui_ver", a.b.a.a.a.c.a.F("ro.miui.ui.version.name", ""));
                                                        jSONObject.put("mi_os_ver", a.b.a.a.a.c.a.F("ro.mi.os.version.name", ""));
                                                        Class cls = a.b.a.e.l0.r.f609a;
                                                        jSONObject.put("android_ver_int", Build.VERSION.SDK_INT);
                                                        jSONObject.put("uep", a.b.a.e.l0.r.v(contextB));
                                                        signatureArr = contextB.getPackageManager().getPackageInfo(contextB.getPackageName(), 64).signatures;
                                                        length = signatureArr.length;
                                                        i7 = 0;
                                                        while (true) {
                                                            if (i7 >= length) {
                                                                strSubstring = "";
                                                                break;
                                                            }
                                                            strC = a.b.a.e.g0.d.c(signatureArr[i7].toByteArray());
                                                            if (!TextUtils.isEmpty(strC)) {
                                                                strSubstring = strC.substring(0, 8);
                                                                break;
                                                            }
                                                            i7++;
                                                            strSubstring = "";
                                                            break;
                                                        }
                                                        jSONObject.put("sign", strSubstring);
                                                        jSONObject.put("cust_variant", a.b.a.a.a.c.a.G("ro.miui.cust_variant", ""));
                                                        jSONObject.put("intl", a.b.a.e.l0.r.q());
                                                        jSONObject.put("desc", a.b.a.a.a.c.a.G("ro.build.description", ""));
                                                        jSONObject.put("radio", a.b.a.e.l0.c.z());
                                                        jSONObject.put("radio2", a.b.a.e.l0.c.A(1));
                                                        Object objInvoke = Class.forName("android.content.pm.IPackageManager$Stub").getDeclaredMethod("asInterface", IBinder.class).invoke(null, (IBinder) Class.forName("android.os.ServiceManager").getDeclaredMethod("getService", String.class).invoke(null, "package"));
                                                        zBooleanValue = ((Boolean) objInvoke.getClass().getDeclaredMethod("isFirstBoot", new Class[0]).invoke(objInvoke, new Object[0])).booleanValue();
                                                        str13 = "is first boot:" + zBooleanValue;
                                                        a.b.a.e.l0.p.s("ActiveEvent", str13);
                                                        dVarV = a.b.a.a.a.c.a.V(z);
                                                        if (dVarV != null) {
                                                            dVarV.a(Level.WARNING, "##XLogger##, TAG: ", "ActiveEvent: " + str13, null);
                                                        }
                                                        jSONObject.put("first_boot", zBooleanValue);
                                                        accountD = a.b.a.e.l0.c.D(contextB);
                                                        if (!a.b.a.e.l0.r.q()) {
                                                            if (accountD != null) {
                                                                str12 = accountD.name;
                                                            } else {
                                                                str12 = "";
                                                            }
                                                            if (!TextUtils.isEmpty(str12)) {
                                                                a.b.a.e.l0.u.k("r_m", true);
                                                            }
                                                        }
                                                        if (accountD == null) {
                                                            i5 = 0;
                                                        } else {
                                                            try {
                                                                i4 = 1;
                                                                for (Map.Entry<String, Integer> entry : g.entrySet()) {
                                                                    try {
                                                                        key = entry.getKey();
                                                                        iIntValue = entry.getValue().intValue();
                                                                        try {
                                                                            if (j(accountD, key, z)) {
                                                                                i4 |= iIntValue;
                                                                            }
                                                                        } catch (Exception e13) {
                                                                            e = e13;
                                                                            a.b.a.a.a.c.a.s(z, "ActiveEvent", "getXiaomiServiceType e: ", e);
                                                                            i5 = i4;
                                                                            jSONObject.put("bind_stats", String.valueOf(i5));
                                                                            strA = a.b.a.e.l0.h.a("release_time");
                                                                            if (!TextUtils.isEmpty(strA)) {
                                                                                try {
                                                                                    j4 = Long.parseLong(strA);
                                                                                } catch (NumberFormatException e14) {
                                                                                    a.b.a.a.a.c.a.s(z, "ActiveEvent", "getReleaseTime error, value=" + strA, e14);
                                                                                }
                                                                            }
                                                                            jSONObject.put("release_time", j4);
                                                                            jSONObject.put("first_conect_time", a.b.a.e.l0.u.e());
                                                                            jSONObject.put("lock_state", a.b.a.a.a.c.a.E("ro.secureboot.lockstate"));
                                                                            jSONObject.put("rootable", a.b.a.a.a.c.a.E("ro.debuggable"));
                                                                            if (a.b.a.a.a.c.a.G0(a.b.a.e.e.b(), Boolean.valueOf(z))) {
                                                                                zF = a.b.a.e.l0.u.f();
                                                                                a.b.a.a.a.c.a.m(z, "ActiveEvent", "isGetNonce: " + zF);
                                                                                if (!zF) {
                                                                                    String strC2 = a.b.a.e.g0.c.c(a.b.a.a.a.c.a.t(h(contextB, z).getBytes()));
                                                                                    e(jSONObject, strC2, a.b.a.a.a.c.a.P(a.b.a.e.e.b(), Boolean.valueOf(z)), 1, a.b.a.a.a.c.a.E0(a.b.a.e.g0.d.d(strC2), Boolean.valueOf(z)), z);
                                                                                }
                                                                            } else {
                                                                                jSONObject.put("tz_content", a.b.a.e.g0.c.c(a.b.a.a.a.c.a.t(h(contextB, z).getBytes())));
                                                                                jSONObject.put("tz_support", false);
                                                                                jSONObject.put("tz_cpuid", g(null, z));
                                                                            }
                                                                            if (!a.b.a.e.l0.r.q()) {
                                                                                a.b.a.c.v.j jVarD = a.b.a.c.v.j.d(contextB);
                                                                                Objects.requireNonNull(jVarD);
                                                                                try {
                                                                                    string2 = a.b.a.c.v.j.a.a(jVarD.f156a).toString();
                                                                                } catch (Exception e15) {
                                                                                    Log.e(a.b.a.c.v.i.a("MC"), "getCurrentCoarseMdsToActive exception:", e15);
                                                                                    string2 = "";
                                                                                }
                                                                                jSONObject.put("loc", string2);
                                                                            }
                                                                            jSONObject.put("allow_report_usage", a.b.a.e.l0.r.b(contextB));
                                                                            jSONObject.put("protocol_ver", 1);
                                                                            jSONObject.put("ot_activation", z);
                                                                            strG2 = a.b.a.a.a.c.a.G("ro.boot.hwlevel", "");
                                                                            jSONObject.put("hwlevel", strG2);
                                                                            strG3 = a.b.a.a.a.c.a.G("ro.boot.hwc", "");
                                                                            jSONObject.put("hwc", strG3);
                                                                            strG4 = a.b.a.a.a.c.a.G("ro.boot.vbmeta.device_state", "");
                                                                            jSONObject.put("vbmeta_state", strG4);
                                                                            strG5 = a.b.a.a.a.c.a.G("ro.boot.vbmeta.digest", "");
                                                                            jSONObject.put("vbmeta_digest", strG5);
                                                                            if (a.b.a.e.l0.r.q()) {
                                                                                jSONObject.put("custom_version", a.b.a.e.l0.c.e());
                                                                                jSONObject.put("pure_custom_carrier", a.b.a.a.a.c.a.E("ro.miui.customized.region"));
                                                                                jSONObject.put("cota_carrier", a.b.a.a.a.c.a.E("persist.sys.cota.carrier"));
                                                                                lVarK = a.b.a.a.a.c.a.K(contextB);
                                                                                if (lVarK != null) {
                                                                                    if (TextUtils.isEmpty(lVarK.f591a)) {
                                                                                        str11 = "";
                                                                                    } else {
                                                                                        str11 = lVarK.f591a;
                                                                                    }
                                                                                    jSONObject.put("g_c_i_ms", str11);
                                                                                    jSONObject.put("g_c_i_am", TextUtils.isEmpty(lVarK.f592b) ? "" : lVarK.f592b);
                                                                                }
                                                                            }
                                                                            return jSONObject;
                                                                        }
                                                                    } catch (Exception e16) {
                                                                        e = e16;
                                                                    }
                                                                }
                                                                if (a.b.a.e.l0.r.o(contextB, "miui.cloud.finddevice.AccessFindDevice")) {
                                                                    if (a.b.a.e.l0.r.o(contextB, "android.permission.AUTHENTICATE_ACCOUNTS")) {
                                                                    }
                                                                    i6 = i4;
                                                                } else if (a.b.a.c.v.g.a(contextB)) {
                                                                    i6 = i4 | 8;
                                                                } else {
                                                                    i6 = i4;
                                                                }
                                                                try {
                                                                    if (i(contextB, accountD, z)) {
                                                                        i5 = i6 | 8192;
                                                                    } else {
                                                                        i5 = i6;
                                                                    }
                                                                } catch (Exception e17) {
                                                                    e = e17;
                                                                    i4 = i6;
                                                                    a.b.a.a.a.c.a.s(z, "ActiveEvent", "getXiaomiServiceType e: ", e);
                                                                    i5 = i4;
                                                                }
                                                            } catch (Exception e18) {
                                                                e = e18;
                                                                i4 = 1;
                                                            }
                                                        }
                                                        jSONObject.put("bind_stats", String.valueOf(i5));
                                                        strA = a.b.a.e.l0.h.a("release_time");
                                                        if (!TextUtils.isEmpty(strA)) {
                                                            j4 = Long.parseLong(strA);
                                                        }
                                                        jSONObject.put("release_time", j4);
                                                        jSONObject.put("first_conect_time", a.b.a.e.l0.u.e());
                                                        jSONObject.put("lock_state", a.b.a.a.a.c.a.E("ro.secureboot.lockstate"));
                                                        jSONObject.put("rootable", a.b.a.a.a.c.a.E("ro.debuggable"));
                                                        if (a.b.a.a.a.c.a.G0(a.b.a.e.e.b(), Boolean.valueOf(z))) {
                                                            zF = a.b.a.e.l0.u.f();
                                                            a.b.a.a.a.c.a.m(z, "ActiveEvent", "isGetNonce: " + zF);
                                                            if (!zF) {
                                                                String strC3 = a.b.a.e.g0.c.c(a.b.a.a.a.c.a.t(h(contextB, z).getBytes()));
                                                                e(jSONObject, strC3, a.b.a.a.a.c.a.P(a.b.a.e.e.b(), Boolean.valueOf(z)), 1, a.b.a.a.a.c.a.E0(a.b.a.e.g0.d.d(strC3), Boolean.valueOf(z)), z);
                                                            }
                                                        } else {
                                                            jSONObject.put("tz_content", a.b.a.e.g0.c.c(a.b.a.a.a.c.a.t(h(contextB, z).getBytes())));
                                                            jSONObject.put("tz_support", false);
                                                            jSONObject.put("tz_cpuid", g(null, z));
                                                        }
                                                        if (!a.b.a.e.l0.r.q()) {
                                                            a.b.a.c.v.j jVarD2 = a.b.a.c.v.j.d(contextB);
                                                            Objects.requireNonNull(jVarD2);
                                                            string2 = a.b.a.c.v.j.a.a(jVarD2.f156a).toString();
                                                            jSONObject.put("loc", string2);
                                                        }
                                                        jSONObject.put("allow_report_usage", a.b.a.e.l0.r.b(contextB));
                                                        jSONObject.put("protocol_ver", 1);
                                                        jSONObject.put("ot_activation", z);
                                                        strG2 = a.b.a.a.a.c.a.G("ro.boot.hwlevel", "");
                                                        jSONObject.put("hwlevel", strG2);
                                                        strG3 = a.b.a.a.a.c.a.G("ro.boot.hwc", "");
                                                        jSONObject.put("hwc", strG3);
                                                        strG4 = a.b.a.a.a.c.a.G("ro.boot.vbmeta.device_state", "");
                                                        jSONObject.put("vbmeta_state", strG4);
                                                        strG5 = a.b.a.a.a.c.a.G("ro.boot.vbmeta.digest", "");
                                                        jSONObject.put("vbmeta_digest", strG5);
                                                        if (a.b.a.e.l0.r.q()) {
                                                            jSONObject.put("custom_version", a.b.a.e.l0.c.e());
                                                            jSONObject.put("pure_custom_carrier", a.b.a.a.a.c.a.E("ro.miui.customized.region"));
                                                            jSONObject.put("cota_carrier", a.b.a.a.a.c.a.E("persist.sys.cota.carrier"));
                                                            lVarK = a.b.a.a.a.c.a.K(contextB);
                                                            if (lVarK != null) {
                                                                if (TextUtils.isEmpty(lVarK.f591a)) {
                                                                    str11 = "";
                                                                } else {
                                                                    str11 = lVarK.f591a;
                                                                }
                                                                jSONObject.put("g_c_i_ms", str11);
                                                                jSONObject.put("g_c_i_am", TextUtils.isEmpty(lVarK.f592b) ? "" : lVarK.f592b);
                                                            }
                                                        }
                                                        return jSONObject;
                                                    }
                                                } catch (Exception e19) {
                                                    e = e19;
                                                    str5 = "B";
                                                    str6 = "KB";
                                                }
                                            } catch (Exception e20) {
                                                e = e20;
                                                str5 = "B";
                                                str6 = "KB";
                                            }
                                            a.b.a.e.l0.p.c("StorageUtil", "formatUFSShortSize->number is " + jB);
                                            if (contextB == null) {
                                                str10 = "";
                                            } else {
                                                fFloor = jB;
                                                if (fFloor > 900.0d) {
                                                    fFloor /= 1000.0f;
                                                    str7 = str6;
                                                } else {
                                                    str7 = str5;
                                                }
                                                if (fFloor > 900.0d) {
                                                    fFloor /= 1000.0f;
                                                } else {
                                                    str2 = str7;
                                                }
                                                if (fFloor > 900.0d) {
                                                    fFloor /= 1000.0f;
                                                    str8 = "GB";
                                                } else {
                                                    str8 = str2;
                                                }
                                                if (fFloor > 921.6d) {
                                                    fFloor = ((float) Math.floor((fFloor / 1024.0f) * 10.0f)) / 10.0f;
                                                    str9 = "TB";
                                                } else {
                                                    str9 = str8;
                                                }
                                                if (fFloor > 921.6d) {
                                                    fFloor /= 1000.0f;
                                                } else {
                                                    str3 = str9;
                                                }
                                                Locale locale3 = Locale.US;
                                                str10 = String.format(locale3, "%s%s", String.format(locale3, "%.0f", Float.valueOf(fFloor)), str3);
                                            }
                                        } else {
                                            r3 = 0;
                                            try {
                                                try {
                                                    if (i3 > 24) {
                                                        long jLongValue2 = ((Long) a.b.a.a.a.c.a.d((StorageManager) contextB.getSystemService("storage"), "getPrimaryStorageSize", null, new Object[0])).longValue();
                                                        a.b.a.e.l0.p.c("StorageUtil", "getTotalMemoryBytes->above android N, mTotalMemoryByte is " + jLongValue2);
                                                        long jB2 = a.b.a.e.l0.s.b(jLongValue2);
                                                        str16 = "getTotalMemoryBytes->after round size, mTotalMemoryByte is ";
                                                        sb2 = new StringBuilder();
                                                        r3 = jB2;
                                                    } else {
                                                        long jC = a.b.a.e.l0.s.c(Environment.getDataDirectory().getTotalSpace() + Environment.getRootDirectory().getTotalSpace());
                                                        str16 = "getTotalMemoryBytes->below android N, mTotalMemoryByte is ";
                                                        sb2 = new StringBuilder();
                                                        r3 = jC;
                                                    }
                                                    sb2.append(str16);
                                                    sb2.append(r3);
                                                    a.b.a.e.l0.p.c("StorageUtil", sb2.toString());
                                                    r4 = r3;
                                                } catch (Exception e21) {
                                                    e = e21;
                                                    Log.e(a.b.a.e.l0.p.a("StorageUtil"), "getTotalMemoryBytes error, ", e);
                                                    r4 = r23;
                                                }
                                            } catch (Exception e22) {
                                                e = e22;
                                                r23 = r3;
                                                Log.e(a.b.a.e.l0.p.a("StorageUtil"), "getTotalMemoryBytes error, ", e);
                                                r4 = r23;
                                            }
                                            locale = Locale.ENGLISH;
                                            if (contextB == null) {
                                                str10 = "";
                                            } else {
                                                fFloor2 = (float) r4;
                                                if (fFloor2 > 900.0d) {
                                                    fFloor2 /= 1000.0f;
                                                    str14 = "KB";
                                                } else {
                                                    str14 = "B";
                                                }
                                                if (fFloor2 > 900.0d) {
                                                    fFloor2 /= 1000.0f;
                                                } else {
                                                    str2 = str14;
                                                }
                                                if (fFloor2 > 900.0d) {
                                                    fFloor2 /= 1000.0f;
                                                    str2 = "GB";
                                                }
                                                if (fFloor2 > 921.6d) {
                                                    fFloor2 = ((float) Math.floor((fFloor2 / 1024.0f) * 10.0f)) / 10.0f;
                                                    str15 = "TB";
                                                } else {
                                                    str15 = str2;
                                                }
                                                if (fFloor2 > 921.6d) {
                                                    fFloor2 /= 1000.0f;
                                                    str15 = "PB";
                                                }
                                                a.b.a.e.l0.p.c("StorageUtil", "suffix: " + str15 + ", result: " + fFloor2);
                                                str10 = String.format(locale, "%s%s", String.format(locale, "%.0f", Float.valueOf(fFloor2)), str15);
                                            }
                                        }
                                        jSONObject.put("rom", str10);
                                        jSONObject.put("ui_ver", a.b.a.a.a.c.a.F("ro.miui.ui.version.name", ""));
                                        jSONObject.put("mi_os_ver", a.b.a.a.a.c.a.F("ro.mi.os.version.name", ""));
                                        Class cls2 = a.b.a.e.l0.r.f609a;
                                        jSONObject.put("android_ver_int", Build.VERSION.SDK_INT);
                                        jSONObject.put("uep", a.b.a.e.l0.r.v(contextB));
                                        signatureArr = contextB.getPackageManager().getPackageInfo(contextB.getPackageName(), 64).signatures;
                                        length = signatureArr.length;
                                        i7 = 0;
                                        while (true) {
                                            if (i7 >= length) {
                                                strSubstring = "";
                                                break;
                                            }
                                            strC = a.b.a.e.g0.d.c(signatureArr[i7].toByteArray());
                                            if (!TextUtils.isEmpty(strC)) {
                                                strSubstring = strC.substring(0, 8);
                                                break;
                                            }
                                            i7++;
                                            strSubstring = "";
                                            break;
                                        }
                                        jSONObject.put("sign", strSubstring);
                                        jSONObject.put("cust_variant", a.b.a.a.a.c.a.G("ro.miui.cust_variant", ""));
                                        jSONObject.put("intl", a.b.a.e.l0.r.q());
                                        jSONObject.put("desc", a.b.a.a.a.c.a.G("ro.build.description", ""));
                                        jSONObject.put("radio", a.b.a.e.l0.c.z());
                                        jSONObject.put("radio2", a.b.a.e.l0.c.A(1));
                                        Object objInvoke2 = Class.forName("android.content.pm.IPackageManager$Stub").getDeclaredMethod("asInterface", IBinder.class).invoke(null, (IBinder) Class.forName("android.os.ServiceManager").getDeclaredMethod("getService", String.class).invoke(null, "package"));
                                        zBooleanValue = ((Boolean) objInvoke2.getClass().getDeclaredMethod("isFirstBoot", new Class[0]).invoke(objInvoke2, new Object[0])).booleanValue();
                                        str13 = "is first boot:" + zBooleanValue;
                                        a.b.a.e.l0.p.s("ActiveEvent", str13);
                                        dVarV = a.b.a.a.a.c.a.V(z);
                                        if (dVarV != null) {
                                            dVarV.a(Level.WARNING, "##XLogger##, TAG: ", "ActiveEvent: " + str13, null);
                                        }
                                        jSONObject.put("first_boot", zBooleanValue);
                                        accountD = a.b.a.e.l0.c.D(contextB);
                                        if (!a.b.a.e.l0.r.q()) {
                                            if (accountD != null) {
                                                str12 = accountD.name;
                                            } else {
                                                str12 = "";
                                            }
                                            if (!TextUtils.isEmpty(str12)) {
                                                a.b.a.e.l0.u.k("r_m", true);
                                            }
                                        }
                                        if (accountD == null) {
                                            i5 = 0;
                                        } else {
                                            i4 = 1;
                                            while (r1.hasNext()) {
                                                key = entry.getKey();
                                                iIntValue = entry.getValue().intValue();
                                                if (j(accountD, key, z)) {
                                                    i4 |= iIntValue;
                                                }
                                            }
                                            if (a.b.a.e.l0.r.o(contextB, "miui.cloud.finddevice.AccessFindDevice")) {
                                                if (a.b.a.e.l0.r.o(contextB, "android.permission.AUTHENTICATE_ACCOUNTS")) {
                                                }
                                                i6 = i4;
                                            } else if (a.b.a.c.v.g.a(contextB)) {
                                                i6 = i4 | 8;
                                            } else {
                                                i6 = i4;
                                            }
                                            if (i(contextB, accountD, z)) {
                                                i5 = i6 | 8192;
                                            } else {
                                                i5 = i6;
                                            }
                                        }
                                        jSONObject.put("bind_stats", String.valueOf(i5));
                                        strA = a.b.a.e.l0.h.a("release_time");
                                        if (!TextUtils.isEmpty(strA)) {
                                            j4 = Long.parseLong(strA);
                                        }
                                        jSONObject.put("release_time", j4);
                                        jSONObject.put("first_conect_time", a.b.a.e.l0.u.e());
                                        jSONObject.put("lock_state", a.b.a.a.a.c.a.E("ro.secureboot.lockstate"));
                                        jSONObject.put("rootable", a.b.a.a.a.c.a.E("ro.debuggable"));
                                        if (a.b.a.a.a.c.a.G0(a.b.a.e.e.b(), Boolean.valueOf(z))) {
                                            zF = a.b.a.e.l0.u.f();
                                            a.b.a.a.a.c.a.m(z, "ActiveEvent", "isGetNonce: " + zF);
                                            if (!zF) {
                                                String strC4 = a.b.a.e.g0.c.c(a.b.a.a.a.c.a.t(h(contextB, z).getBytes()));
                                                e(jSONObject, strC4, a.b.a.a.a.c.a.P(a.b.a.e.e.b(), Boolean.valueOf(z)), 1, a.b.a.a.a.c.a.E0(a.b.a.e.g0.d.d(strC4), Boolean.valueOf(z)), z);
                                            }
                                        } else {
                                            jSONObject.put("tz_content", a.b.a.e.g0.c.c(a.b.a.a.a.c.a.t(h(contextB, z).getBytes())));
                                            jSONObject.put("tz_support", false);
                                            jSONObject.put("tz_cpuid", g(null, z));
                                        }
                                        if (!a.b.a.e.l0.r.q()) {
                                            a.b.a.c.v.j jVarD3 = a.b.a.c.v.j.d(contextB);
                                            Objects.requireNonNull(jVarD3);
                                            string2 = a.b.a.c.v.j.a.a(jVarD3.f156a).toString();
                                            jSONObject.put("loc", string2);
                                        }
                                        jSONObject.put("allow_report_usage", a.b.a.e.l0.r.b(contextB));
                                        jSONObject.put("protocol_ver", 1);
                                        jSONObject.put("ot_activation", z);
                                        strG2 = a.b.a.a.a.c.a.G("ro.boot.hwlevel", "");
                                        jSONObject.put("hwlevel", strG2);
                                        strG3 = a.b.a.a.a.c.a.G("ro.boot.hwc", "");
                                        jSONObject.put("hwc", strG3);
                                        strG4 = a.b.a.a.a.c.a.G("ro.boot.vbmeta.device_state", "");
                                        jSONObject.put("vbmeta_state", strG4);
                                        strG5 = a.b.a.a.a.c.a.G("ro.boot.vbmeta.digest", "");
                                        jSONObject.put("vbmeta_digest", strG5);
                                        if (a.b.a.e.l0.r.q()) {
                                            jSONObject.put("custom_version", a.b.a.e.l0.c.e());
                                            jSONObject.put("pure_custom_carrier", a.b.a.a.a.c.a.E("ro.miui.customized.region"));
                                            jSONObject.put("cota_carrier", a.b.a.a.a.c.a.E("persist.sys.cota.carrier"));
                                            lVarK = a.b.a.a.a.c.a.K(contextB);
                                            if (lVarK != null) {
                                                if (TextUtils.isEmpty(lVarK.f591a)) {
                                                    str11 = "";
                                                } else {
                                                    str11 = lVarK.f591a;
                                                }
                                                jSONObject.put("g_c_i_ms", str11);
                                                jSONObject.put("g_c_i_am", TextUtils.isEmpty(lVarK.f592b) ? "" : lVarK.f592b);
                                            }
                                        }
                                        return jSONObject;
                                    }
                                } catch (Exception e23) {
                                    e = e23;
                                    str4 = "DeviceUtil";
                                }
                            } else {
                                str4 = "DeviceUtil";
                                j4 = 0;
                                j5 = 4294967296L;
                                if (j2 <= 4294967296L) {
                                    j5 = 1073741824;
                                } else if (j2 <= 8589934592L) {
                                    j5 = 2147483648L;
                                }
                                j6 = ((j5 - 1) ^ (-1)) & ((j2 + j5) - 1);
                                a.b.a.e.l0.p.c("OSUtil", "getTotalPhysicalMemory, sTotalPhysicalMemory: " + j6);
                                if (contextB == null) {
                                    string = "";
                                } else {
                                    f = j6;
                                    if (f > 921.6d) {
                                        f /= 1024.0f;
                                        str17 = "KB";
                                    } else {
                                        str17 = "B";
                                    }
                                    if (f > 921.6d) {
                                        f /= 1024.0f;
                                        str17 = "MB";
                                    }
                                    if (f > 921.6d) {
                                        f /= 1024.0f;
                                        str17 = "GB";
                                    }
                                    if (f > 921.6d) {
                                        f /= 1024.0f;
                                        str17 = "TB";
                                    }
                                    if (f > 921.6d) {
                                        f /= 1024.0f;
                                        str17 = "PB";
                                    }
                                    string = ((int) ((float) Math.ceil(f))) + str17;
                                }
                            }
                            jSONObject.put("ram", string);
                            i3 = Build.VERSION.SDK_INT;
                            if (i3 > 33) {
                                jLongValue = ((Long) a.b.a.a.a.c.a.d((StorageManager) contextB.getSystemService("storage"), "getPrimaryStorageSize", null, new Object[0])).longValue();
                                a.b.a.e.l0.p.c("StorageUtil", "getTotalMemoryBytesForAboveU->mTotalMemoryByte is " + jLongValue);
                                str5 = "B";
                                str6 = "KB";
                                long jA2 = ((long) a.b.a.e.l0.s.a(contextB)) * 1000000000;
                                a.b.a.e.l0.p.c("StorageUtil", "getTotalMemoryBytesForAboveU->EUASupportSizeByte is " + jA2);
                                j3 = jLongValue - jA2;
                                jB = a.b.a.e.l0.s.b(j3);
                                a.b.a.e.l0.p.c("StorageUtil", "formatUFSShortSize->number is " + jB);
                                if (contextB == null) {
                                    str10 = "";
                                } else {
                                    fFloor = jB;
                                    if (fFloor > 900.0d) {
                                        fFloor /= 1000.0f;
                                        str7 = str6;
                                    } else {
                                        str7 = str5;
                                    }
                                    if (fFloor > 900.0d) {
                                        fFloor /= 1000.0f;
                                    } else {
                                        str2 = str7;
                                    }
                                    if (fFloor > 900.0d) {
                                        fFloor /= 1000.0f;
                                        str8 = "GB";
                                    } else {
                                        str8 = str2;
                                    }
                                    if (fFloor > 921.6d) {
                                        fFloor = ((float) Math.floor((fFloor / 1024.0f) * 10.0f)) / 10.0f;
                                        str9 = "TB";
                                    } else {
                                        str9 = str8;
                                    }
                                    if (fFloor > 921.6d) {
                                        fFloor /= 1000.0f;
                                    } else {
                                        str3 = str9;
                                    }
                                    Locale locale4 = Locale.US;
                                    str10 = String.format(locale4, "%s%s", String.format(locale4, "%.0f", Float.valueOf(fFloor)), str3);
                                }
                            } else {
                                r3 = 0;
                                if (i3 > 24) {
                                    long jLongValue3 = ((Long) a.b.a.a.a.c.a.d((StorageManager) contextB.getSystemService("storage"), "getPrimaryStorageSize", null, new Object[0])).longValue();
                                    a.b.a.e.l0.p.c("StorageUtil", "getTotalMemoryBytes->above android N, mTotalMemoryByte is " + jLongValue3);
                                    long jB3 = a.b.a.e.l0.s.b(jLongValue3);
                                    str16 = "getTotalMemoryBytes->after round size, mTotalMemoryByte is ";
                                    sb2 = new StringBuilder();
                                    r3 = jB3;
                                } else {
                                    long jC2 = a.b.a.e.l0.s.c(Environment.getDataDirectory().getTotalSpace() + Environment.getRootDirectory().getTotalSpace());
                                    str16 = "getTotalMemoryBytes->below android N, mTotalMemoryByte is ";
                                    sb2 = new StringBuilder();
                                    r3 = jC2;
                                }
                                sb2.append(str16);
                                sb2.append(r3);
                                a.b.a.e.l0.p.c("StorageUtil", sb2.toString());
                                r4 = r3;
                                locale = Locale.ENGLISH;
                                if (contextB == null) {
                                    str10 = "";
                                } else {
                                    fFloor2 = (float) r4;
                                    if (fFloor2 > 900.0d) {
                                        fFloor2 /= 1000.0f;
                                        str14 = "KB";
                                    } else {
                                        str14 = "B";
                                    }
                                    if (fFloor2 > 900.0d) {
                                        fFloor2 /= 1000.0f;
                                    } else {
                                        str2 = str14;
                                    }
                                    if (fFloor2 > 900.0d) {
                                        fFloor2 /= 1000.0f;
                                        str2 = "GB";
                                    }
                                    if (fFloor2 > 921.6d) {
                                        fFloor2 = ((float) Math.floor((fFloor2 / 1024.0f) * 10.0f)) / 10.0f;
                                        str15 = "TB";
                                    } else {
                                        str15 = str2;
                                    }
                                    if (fFloor2 > 921.6d) {
                                        fFloor2 /= 1000.0f;
                                        str15 = "PB";
                                    }
                                    a.b.a.e.l0.p.c("StorageUtil", "suffix: " + str15 + ", result: " + fFloor2);
                                    str10 = String.format(locale, "%s%s", String.format(locale, "%.0f", Float.valueOf(fFloor2)), str15);
                                }
                            }
                            jSONObject.put("rom", str10);
                            jSONObject.put("ui_ver", a.b.a.a.a.c.a.F("ro.miui.ui.version.name", ""));
                            jSONObject.put("mi_os_ver", a.b.a.a.a.c.a.F("ro.mi.os.version.name", ""));
                            Class cls3 = a.b.a.e.l0.r.f609a;
                            jSONObject.put("android_ver_int", Build.VERSION.SDK_INT);
                            jSONObject.put("uep", a.b.a.e.l0.r.v(contextB));
                            signatureArr = contextB.getPackageManager().getPackageInfo(contextB.getPackageName(), 64).signatures;
                            length = signatureArr.length;
                            i7 = 0;
                            while (true) {
                                if (i7 >= length) {
                                    strSubstring = "";
                                    break;
                                }
                                strC = a.b.a.e.g0.d.c(signatureArr[i7].toByteArray());
                                if (!TextUtils.isEmpty(strC)) {
                                    strSubstring = strC.substring(0, 8);
                                    break;
                                }
                                i7++;
                            }
                            jSONObject.put("sign", strSubstring);
                            jSONObject.put("cust_variant", a.b.a.a.a.c.a.G("ro.miui.cust_variant", ""));
                            jSONObject.put("intl", a.b.a.e.l0.r.q());
                            jSONObject.put("desc", a.b.a.a.a.c.a.G("ro.build.description", ""));
                            jSONObject.put("radio", a.b.a.e.l0.c.z());
                            jSONObject.put("radio2", a.b.a.e.l0.c.A(1));
                            Object objInvoke3 = Class.forName("android.content.pm.IPackageManager$Stub").getDeclaredMethod("asInterface", IBinder.class).invoke(null, (IBinder) Class.forName("android.os.ServiceManager").getDeclaredMethod("getService", String.class).invoke(null, "package"));
                            zBooleanValue = ((Boolean) objInvoke3.getClass().getDeclaredMethod("isFirstBoot", new Class[0]).invoke(objInvoke3, new Object[0])).booleanValue();
                            str13 = "is first boot:" + zBooleanValue;
                            a.b.a.e.l0.p.s("ActiveEvent", str13);
                            dVarV = a.b.a.a.a.c.a.V(z);
                            if (dVarV != null) {
                                dVarV.a(Level.WARNING, "##XLogger##, TAG: ", "ActiveEvent: " + str13, null);
                            }
                            jSONObject.put("first_boot", zBooleanValue);
                            accountD = a.b.a.e.l0.c.D(contextB);
                            if (!a.b.a.e.l0.r.q()) {
                                if (accountD != null) {
                                    str12 = accountD.name;
                                } else {
                                    str12 = "";
                                }
                                if (!TextUtils.isEmpty(str12)) {
                                    a.b.a.e.l0.u.k("r_m", true);
                                }
                            }
                            if (accountD == null) {
                                i5 = 0;
                            } else {
                                i4 = 1;
                                while (r1.hasNext()) {
                                    key = entry.getKey();
                                    iIntValue = entry.getValue().intValue();
                                    if (j(accountD, key, z)) {
                                        i4 |= iIntValue;
                                    }
                                }
                                if (a.b.a.e.l0.r.o(contextB, "miui.cloud.finddevice.AccessFindDevice")) {
                                    if (a.b.a.e.l0.r.o(contextB, "android.permission.AUTHENTICATE_ACCOUNTS")) {
                                    }
                                    i6 = i4;
                                } else if (a.b.a.c.v.g.a(contextB)) {
                                    i6 = i4 | 8;
                                } else {
                                    i6 = i4;
                                }
                                if (i(contextB, accountD, z)) {
                                    i5 = i6 | 8192;
                                } else {
                                    i5 = i6;
                                }
                            }
                            jSONObject.put("bind_stats", String.valueOf(i5));
                            strA = a.b.a.e.l0.h.a("release_time");
                            if (!TextUtils.isEmpty(strA)) {
                                j4 = Long.parseLong(strA);
                            }
                            jSONObject.put("release_time", j4);
                            jSONObject.put("first_conect_time", a.b.a.e.l0.u.e());
                            jSONObject.put("lock_state", a.b.a.a.a.c.a.E("ro.secureboot.lockstate"));
                            jSONObject.put("rootable", a.b.a.a.a.c.a.E("ro.debuggable"));
                            if (a.b.a.a.a.c.a.G0(a.b.a.e.e.b(), Boolean.valueOf(z))) {
                                zF = a.b.a.e.l0.u.f();
                                a.b.a.a.a.c.a.m(z, "ActiveEvent", "isGetNonce: " + zF);
                                if (!zF) {
                                    String strC5 = a.b.a.e.g0.c.c(a.b.a.a.a.c.a.t(h(contextB, z).getBytes()));
                                    e(jSONObject, strC5, a.b.a.a.a.c.a.P(a.b.a.e.e.b(), Boolean.valueOf(z)), 1, a.b.a.a.a.c.a.E0(a.b.a.e.g0.d.d(strC5), Boolean.valueOf(z)), z);
                                }
                            } else {
                                jSONObject.put("tz_content", a.b.a.e.g0.c.c(a.b.a.a.a.c.a.t(h(contextB, z).getBytes())));
                                jSONObject.put("tz_support", false);
                                jSONObject.put("tz_cpuid", g(null, z));
                            }
                            if (!a.b.a.e.l0.r.q()) {
                                a.b.a.c.v.j jVarD4 = a.b.a.c.v.j.d(contextB);
                                Objects.requireNonNull(jVarD4);
                                string2 = a.b.a.c.v.j.a.a(jVarD4.f156a).toString();
                                jSONObject.put("loc", string2);
                            }
                            jSONObject.put("allow_report_usage", a.b.a.e.l0.r.b(contextB));
                            jSONObject.put("protocol_ver", 1);
                            jSONObject.put("ot_activation", z);
                            strG2 = a.b.a.a.a.c.a.G("ro.boot.hwlevel", "");
                            jSONObject.put("hwlevel", strG2);
                            strG3 = a.b.a.a.a.c.a.G("ro.boot.hwc", "");
                            jSONObject.put("hwc", strG3);
                            strG4 = a.b.a.a.a.c.a.G("ro.boot.vbmeta.device_state", "");
                            jSONObject.put("vbmeta_state", strG4);
                            strG5 = a.b.a.a.a.c.a.G("ro.boot.vbmeta.digest", "");
                            jSONObject.put("vbmeta_digest", strG5);
                            if (a.b.a.e.l0.r.q()) {
                                jSONObject.put("custom_version", a.b.a.e.l0.c.e());
                                jSONObject.put("pure_custom_carrier", a.b.a.a.a.c.a.E("ro.miui.customized.region"));
                                jSONObject.put("cota_carrier", a.b.a.a.a.c.a.E("persist.sys.cota.carrier"));
                                lVarK = a.b.a.a.a.c.a.K(contextB);
                                if (lVarK != null) {
                                    if (TextUtils.isEmpty(lVarK.f591a)) {
                                        str11 = "";
                                    } else {
                                        str11 = lVarK.f591a;
                                    }
                                    jSONObject.put("g_c_i_ms", str11);
                                    jSONObject.put("g_c_i_am", TextUtils.isEmpty(lVarK.f592b) ? "" : lVarK.f592b);
                                }
                            }
                            return jSONObject;
                        }
                    } catch (Exception e24) {
                        e = e24;
                        bufferedReader = null;
                        a.b.a.e.l0.p.h("OSUtil", "getTotalRAM Exception: ", e);
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (fileReader != 0) {
                            fileReader.close();
                        }
                        j2 = 0;
                        str2 = "MB";
                        str3 = "PB";
                        j3 = -1;
                        ?? r24 = -1;
                        if (Build.VERSION.SDK_INT <= 25) {
                            a.b.a.e.l0.p.c("OSUtil", "formatFileSize, size: " + j2);
                            DecimalFormatSymbols decimalFormatSymbols2 = new DecimalFormatSymbols(Locale.US);
                            str4 = "DeviceUtil";
                            decimalFormat = new DecimalFormat("#0", decimalFormatSymbols2);
                            new DecimalFormat("#0.#", decimalFormatSymbols2);
                            j4 = 0;
                            if (j2 >= 1000) {
                                if (j2 < 1000000) {
                                    sb = new StringBuilder();
                                    double d8 = j2;
                                    Double.isNaN(d8);
                                    Double.isNaN(d8);
                                    Double.isNaN(d8);
                                    Double.isNaN(d8);
                                    sb.append(decimalFormat.format(d8 / 1000.0d));
                                    sb.append("KB");
                                } else if (j2 < 1000000000) {
                                    sb = new StringBuilder();
                                    double d9 = j2;
                                    Double.isNaN(d9);
                                    Double.isNaN(d9);
                                    Double.isNaN(d9);
                                    Double.isNaN(d9);
                                    sb.append(decimalFormat.format(d9 / 1000000.0d));
                                    sb.append("MB");
                                } else {
                                    sb = new StringBuilder();
                                    double d10 = j2;
                                    Double.isNaN(d10);
                                    Double.isNaN(d10);
                                    Double.isNaN(d10);
                                    Double.isNaN(d10);
                                    sb.append(decimalFormat.format(d10 / 1.0E9d));
                                    sb.append("GB");
                                }
                            } else if (j2 < 1000000) {
                                sb = new StringBuilder();
                                double d11 = j2;
                                Double.isNaN(d11);
                                Double.isNaN(d11);
                                Double.isNaN(d11);
                                Double.isNaN(d11);
                                sb.append(decimalFormat.format(d11 / 1000.0d));
                                sb.append("KB");
                            } else if (j2 < 1000000000) {
                                sb = new StringBuilder();
                                double d12 = j2;
                                Double.isNaN(d12);
                                Double.isNaN(d12);
                                Double.isNaN(d12);
                                Double.isNaN(d12);
                                sb.append(decimalFormat.format(d12 / 1000000.0d));
                                sb.append("MB");
                            } else {
                                sb = new StringBuilder();
                                double d13 = j2;
                                Double.isNaN(d13);
                                Double.isNaN(d13);
                                Double.isNaN(d13);
                                Double.isNaN(d13);
                                sb.append(decimalFormat.format(d13 / 1.0E9d));
                                sb.append("GB");
                            }
                            string = sb.toString();
                        } else {
                            str4 = "DeviceUtil";
                            j4 = 0;
                            j5 = 4294967296L;
                            if (j2 <= 4294967296L) {
                                j5 = 1073741824;
                            } else if (j2 <= 8589934592L) {
                                j5 = 2147483648L;
                            }
                            j6 = ((j5 - 1) ^ (-1)) & ((j2 + j5) - 1);
                            a.b.a.e.l0.p.c("OSUtil", "getTotalPhysicalMemory, sTotalPhysicalMemory: " + j6);
                            if (contextB == null) {
                                string = "";
                            } else {
                                f = j6;
                                if (f > 921.6d) {
                                    f /= 1024.0f;
                                    str17 = "KB";
                                } else {
                                    str17 = "B";
                                }
                                if (f > 921.6d) {
                                    f /= 1024.0f;
                                    str17 = "MB";
                                }
                                if (f > 921.6d) {
                                    f /= 1024.0f;
                                    str17 = "GB";
                                }
                                if (f > 921.6d) {
                                    f /= 1024.0f;
                                    str17 = "TB";
                                }
                                if (f > 921.6d) {
                                    f /= 1024.0f;
                                    str17 = "PB";
                                }
                                string = ((int) ((float) Math.ceil(f))) + str17;
                            }
                        }
                        jSONObject.put("ram", string);
                        i3 = Build.VERSION.SDK_INT;
                        if (i3 > 33) {
                            jLongValue = ((Long) a.b.a.a.a.c.a.d((StorageManager) contextB.getSystemService("storage"), "getPrimaryStorageSize", null, new Object[0])).longValue();
                            a.b.a.e.l0.p.c("StorageUtil", "getTotalMemoryBytesForAboveU->mTotalMemoryByte is " + jLongValue);
                            str5 = "B";
                            str6 = "KB";
                            long jA3 = ((long) a.b.a.e.l0.s.a(contextB)) * 1000000000;
                            a.b.a.e.l0.p.c("StorageUtil", "getTotalMemoryBytesForAboveU->EUASupportSizeByte is " + jA3);
                            j3 = jLongValue - jA3;
                            jB = a.b.a.e.l0.s.b(j3);
                            a.b.a.e.l0.p.c("StorageUtil", "formatUFSShortSize->number is " + jB);
                            if (contextB == null) {
                                str10 = "";
                            } else {
                                fFloor = jB;
                                if (fFloor > 900.0d) {
                                    fFloor /= 1000.0f;
                                    str7 = str6;
                                } else {
                                    str7 = str5;
                                }
                                if (fFloor > 900.0d) {
                                    fFloor /= 1000.0f;
                                } else {
                                    str2 = str7;
                                }
                                if (fFloor > 900.0d) {
                                    fFloor /= 1000.0f;
                                    str8 = "GB";
                                } else {
                                    str8 = str2;
                                }
                                if (fFloor > 921.6d) {
                                    fFloor = ((float) Math.floor((fFloor / 1024.0f) * 10.0f)) / 10.0f;
                                    str9 = "TB";
                                } else {
                                    str9 = str8;
                                }
                                if (fFloor > 921.6d) {
                                    fFloor /= 1000.0f;
                                } else {
                                    str3 = str9;
                                }
                                Locale locale5 = Locale.US;
                                str10 = String.format(locale5, "%s%s", String.format(locale5, "%.0f", Float.valueOf(fFloor)), str3);
                            }
                        } else {
                            r3 = 0;
                            if (i3 > 24) {
                                long jLongValue4 = ((Long) a.b.a.a.a.c.a.d((StorageManager) contextB.getSystemService("storage"), "getPrimaryStorageSize", null, new Object[0])).longValue();
                                a.b.a.e.l0.p.c("StorageUtil", "getTotalMemoryBytes->above android N, mTotalMemoryByte is " + jLongValue4);
                                long jB4 = a.b.a.e.l0.s.b(jLongValue4);
                                str16 = "getTotalMemoryBytes->after round size, mTotalMemoryByte is ";
                                sb2 = new StringBuilder();
                                r3 = jB4;
                            } else {
                                long jC3 = a.b.a.e.l0.s.c(Environment.getDataDirectory().getTotalSpace() + Environment.getRootDirectory().getTotalSpace());
                                str16 = "getTotalMemoryBytes->below android N, mTotalMemoryByte is ";
                                sb2 = new StringBuilder();
                                r3 = jC3;
                            }
                            sb2.append(str16);
                            sb2.append(r3);
                            a.b.a.e.l0.p.c("StorageUtil", sb2.toString());
                            r4 = r3;
                            locale = Locale.ENGLISH;
                            if (contextB == null) {
                                str10 = "";
                            } else {
                                fFloor2 = (float) r4;
                                if (fFloor2 > 900.0d) {
                                    fFloor2 /= 1000.0f;
                                    str14 = "KB";
                                } else {
                                    str14 = "B";
                                }
                                if (fFloor2 > 900.0d) {
                                    fFloor2 /= 1000.0f;
                                } else {
                                    str2 = str14;
                                }
                                if (fFloor2 > 900.0d) {
                                    fFloor2 /= 1000.0f;
                                    str2 = "GB";
                                }
                                if (fFloor2 > 921.6d) {
                                    fFloor2 = ((float) Math.floor((fFloor2 / 1024.0f) * 10.0f)) / 10.0f;
                                    str15 = "TB";
                                } else {
                                    str15 = str2;
                                }
                                if (fFloor2 > 921.6d) {
                                    fFloor2 /= 1000.0f;
                                    str15 = "PB";
                                }
                                a.b.a.e.l0.p.c("StorageUtil", "suffix: " + str15 + ", result: " + fFloor2);
                                str10 = String.format(locale, "%s%s", String.format(locale, "%.0f", Float.valueOf(fFloor2)), str15);
                            }
                        }
                        jSONObject.put("rom", str10);
                        jSONObject.put("ui_ver", a.b.a.a.a.c.a.F("ro.miui.ui.version.name", ""));
                        jSONObject.put("mi_os_ver", a.b.a.a.a.c.a.F("ro.mi.os.version.name", ""));
                        Class cls4 = a.b.a.e.l0.r.f609a;
                        jSONObject.put("android_ver_int", Build.VERSION.SDK_INT);
                        jSONObject.put("uep", a.b.a.e.l0.r.v(contextB));
                        signatureArr = contextB.getPackageManager().getPackageInfo(contextB.getPackageName(), 64).signatures;
                        length = signatureArr.length;
                        i7 = 0;
                        while (true) {
                            if (i7 >= length) {
                                strSubstring = "";
                                break;
                            }
                            strC = a.b.a.e.g0.d.c(signatureArr[i7].toByteArray());
                            if (!TextUtils.isEmpty(strC)) {
                                strSubstring = strC.substring(0, 8);
                                break;
                            }
                            i7++;
                            strSubstring = "";
                            break;
                        }
                        jSONObject.put("sign", strSubstring);
                        jSONObject.put("cust_variant", a.b.a.a.a.c.a.G("ro.miui.cust_variant", ""));
                        jSONObject.put("intl", a.b.a.e.l0.r.q());
                        jSONObject.put("desc", a.b.a.a.a.c.a.G("ro.build.description", ""));
                        jSONObject.put("radio", a.b.a.e.l0.c.z());
                        jSONObject.put("radio2", a.b.a.e.l0.c.A(1));
                        Object objInvoke4 = Class.forName("android.content.pm.IPackageManager$Stub").getDeclaredMethod("asInterface", IBinder.class).invoke(null, (IBinder) Class.forName("android.os.ServiceManager").getDeclaredMethod("getService", String.class).invoke(null, "package"));
                        zBooleanValue = ((Boolean) objInvoke4.getClass().getDeclaredMethod("isFirstBoot", new Class[0]).invoke(objInvoke4, new Object[0])).booleanValue();
                        str13 = "is first boot:" + zBooleanValue;
                        a.b.a.e.l0.p.s("ActiveEvent", str13);
                        dVarV = a.b.a.a.a.c.a.V(z);
                        if (dVarV != null) {
                            dVarV.a(Level.WARNING, "##XLogger##, TAG: ", "ActiveEvent: " + str13, null);
                        }
                        jSONObject.put("first_boot", zBooleanValue);
                        accountD = a.b.a.e.l0.c.D(contextB);
                        if (!a.b.a.e.l0.r.q()) {
                            if (accountD != null) {
                                str12 = accountD.name;
                            } else {
                                str12 = "";
                            }
                            if (!TextUtils.isEmpty(str12)) {
                                a.b.a.e.l0.u.k("r_m", true);
                            }
                        }
                        if (accountD == null) {
                            i5 = 0;
                        } else {
                            i4 = 1;
                            while (r1.hasNext()) {
                                key = entry.getKey();
                                iIntValue = entry.getValue().intValue();
                                if (j(accountD, key, z)) {
                                    i4 |= iIntValue;
                                }
                            }
                            if (a.b.a.e.l0.r.o(contextB, "miui.cloud.finddevice.AccessFindDevice")) {
                                if (a.b.a.e.l0.r.o(contextB, "android.permission.AUTHENTICATE_ACCOUNTS")) {
                                }
                                i6 = i4;
                            } else if (a.b.a.c.v.g.a(contextB)) {
                                i6 = i4 | 8;
                            } else {
                                i6 = i4;
                            }
                            if (i(contextB, accountD, z)) {
                                i5 = i6 | 8192;
                            } else {
                                i5 = i6;
                            }
                        }
                        jSONObject.put("bind_stats", String.valueOf(i5));
                        strA = a.b.a.e.l0.h.a("release_time");
                        if (!TextUtils.isEmpty(strA)) {
                            j4 = Long.parseLong(strA);
                        }
                        jSONObject.put("release_time", j4);
                        jSONObject.put("first_conect_time", a.b.a.e.l0.u.e());
                        jSONObject.put("lock_state", a.b.a.a.a.c.a.E("ro.secureboot.lockstate"));
                        jSONObject.put("rootable", a.b.a.a.a.c.a.E("ro.debuggable"));
                        if (a.b.a.a.a.c.a.G0(a.b.a.e.e.b(), Boolean.valueOf(z))) {
                            zF = a.b.a.e.l0.u.f();
                            a.b.a.a.a.c.a.m(z, "ActiveEvent", "isGetNonce: " + zF);
                            if (!zF) {
                                String strC6 = a.b.a.e.g0.c.c(a.b.a.a.a.c.a.t(h(contextB, z).getBytes()));
                                e(jSONObject, strC6, a.b.a.a.a.c.a.P(a.b.a.e.e.b(), Boolean.valueOf(z)), 1, a.b.a.a.a.c.a.E0(a.b.a.e.g0.d.d(strC6), Boolean.valueOf(z)), z);
                            }
                        } else {
                            jSONObject.put("tz_content", a.b.a.e.g0.c.c(a.b.a.a.a.c.a.t(h(contextB, z).getBytes())));
                            jSONObject.put("tz_support", false);
                            jSONObject.put("tz_cpuid", g(null, z));
                        }
                        if (!a.b.a.e.l0.r.q()) {
                            a.b.a.c.v.j jVarD5 = a.b.a.c.v.j.d(contextB);
                            Objects.requireNonNull(jVarD5);
                            string2 = a.b.a.c.v.j.a.a(jVarD5.f156a).toString();
                            jSONObject.put("loc", string2);
                        }
                        jSONObject.put("allow_report_usage", a.b.a.e.l0.r.b(contextB));
                        jSONObject.put("protocol_ver", 1);
                        jSONObject.put("ot_activation", z);
                        strG2 = a.b.a.a.a.c.a.G("ro.boot.hwlevel", "");
                        jSONObject.put("hwlevel", strG2);
                        strG3 = a.b.a.a.a.c.a.G("ro.boot.hwc", "");
                        jSONObject.put("hwc", strG3);
                        strG4 = a.b.a.a.a.c.a.G("ro.boot.vbmeta.device_state", "");
                        jSONObject.put("vbmeta_state", strG4);
                        strG5 = a.b.a.a.a.c.a.G("ro.boot.vbmeta.digest", "");
                        jSONObject.put("vbmeta_digest", strG5);
                        if (a.b.a.e.l0.r.q()) {
                            jSONObject.put("custom_version", a.b.a.e.l0.c.e());
                            jSONObject.put("pure_custom_carrier", a.b.a.a.a.c.a.E("ro.miui.customized.region"));
                            jSONObject.put("cota_carrier", a.b.a.a.a.c.a.E("persist.sys.cota.carrier"));
                            lVarK = a.b.a.a.a.c.a.K(contextB);
                            if (lVarK != null) {
                                if (TextUtils.isEmpty(lVarK.f591a)) {
                                    str11 = "";
                                } else {
                                    str11 = lVarK.f591a;
                                }
                                jSONObject.put("g_c_i_ms", str11);
                                jSONObject.put("g_c_i_am", TextUtils.isEmpty(lVarK.f592b) ? "" : lVarK.f592b);
                            }
                        }
                        return jSONObject;
                    } catch (Throwable th2) {
                        th = th2;
                        r6 = 0;
                        Throwable th3 = th;
                        if (r6 != 0) {
                            try {
                                r6.close();
                            } catch (IOException e25) {
                                a.b.a.e.l0.p.h("OSUtil", "close BufferedReader failed: ", e25);
                            }
                        }
                        if (fileReader == 0) {
                            throw th3;
                        }
                        try {
                            fileReader.close();
                            throw th3;
                        } catch (IOException e26) {
                            a.b.a.e.l0.p.h("OSUtil", "close FileReader failed: ", e26);
                            throw th3;
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            } catch (Exception e27) {
                e = e27;
                fileReader = 0;
            } catch (Throwable th5) {
                th = th5;
                fileReader = 0;
                r6 = 0;
            }
            signatureArr = contextB.getPackageManager().getPackageInfo(contextB.getPackageName(), 64).signatures;
            length = signatureArr.length;
            i7 = 0;
        } catch (Throwable th6) {
            a.b.a.a.a.c.a.s(z, "ActiveEvent", "getShortSignature e: ", th6);
        }
        str2 = "MB";
        str3 = "PB";
        j3 = -1;
        ?? r25 = -1;
        if (Build.VERSION.SDK_INT <= 25) {
            a.b.a.e.l0.p.c("OSUtil", "formatFileSize, size: " + j2);
            DecimalFormatSymbols decimalFormatSymbols3 = new DecimalFormatSymbols(Locale.US);
            str4 = "DeviceUtil";
            decimalFormat = new DecimalFormat("#0", decimalFormatSymbols3);
            new DecimalFormat("#0.#", decimalFormatSymbols3);
            j4 = 0;
            if (j2 >= 1000 && j2 > 0) {
                sb = new StringBuilder();
                sb.append(decimalFormat.format(j2));
                sb.append("B");
            } else if (j2 < 1000000) {
                sb = new StringBuilder();
                double d14 = j2;
                Double.isNaN(d14);
                Double.isNaN(d14);
                Double.isNaN(d14);
                Double.isNaN(d14);
                sb.append(decimalFormat.format(d14 / 1000.0d));
                sb.append("KB");
            } else if (j2 < 1000000000) {
                sb = new StringBuilder();
                double d15 = j2;
                Double.isNaN(d15);
                Double.isNaN(d15);
                Double.isNaN(d15);
                Double.isNaN(d15);
                sb.append(decimalFormat.format(d15 / 1000000.0d));
                sb.append("MB");
            } else {
                sb = new StringBuilder();
                double d16 = j2;
                Double.isNaN(d16);
                Double.isNaN(d16);
                Double.isNaN(d16);
                Double.isNaN(d16);
                sb.append(decimalFormat.format(d16 / 1.0E9d));
                sb.append("GB");
            }
            string = sb.toString();
        } else {
            str4 = "DeviceUtil";
            j4 = 0;
            j5 = 4294967296L;
            if (j2 <= 4294967296L) {
                j5 = 1073741824;
            } else if (j2 <= 8589934592L) {
                j5 = 2147483648L;
            }
            j6 = ((j5 - 1) ^ (-1)) & ((j2 + j5) - 1);
            a.b.a.e.l0.p.c("OSUtil", "getTotalPhysicalMemory, sTotalPhysicalMemory: " + j6);
            if (contextB == null) {
                string = "";
            } else {
                f = j6;
                if (f > 921.6d) {
                    f /= 1024.0f;
                    str17 = "KB";
                } else {
                    str17 = "B";
                }
                if (f > 921.6d) {
                    f /= 1024.0f;
                    str17 = "MB";
                }
                if (f > 921.6d) {
                    f /= 1024.0f;
                    str17 = "GB";
                }
                if (f > 921.6d) {
                    f /= 1024.0f;
                    str17 = "TB";
                }
                if (f > 921.6d) {
                    f /= 1024.0f;
                    str17 = "PB";
                }
                string = ((int) ((float) Math.ceil(f))) + str17;
            }
        }
        jSONObject.put("ram", string);
        i3 = Build.VERSION.SDK_INT;
        if (i3 > 33) {
            jLongValue = ((Long) a.b.a.a.a.c.a.d((StorageManager) contextB.getSystemService("storage"), "getPrimaryStorageSize", null, new Object[0])).longValue();
            a.b.a.e.l0.p.c("StorageUtil", "getTotalMemoryBytesForAboveU->mTotalMemoryByte is " + jLongValue);
            str5 = "B";
            str6 = "KB";
            long jA4 = ((long) a.b.a.e.l0.s.a(contextB)) * 1000000000;
            a.b.a.e.l0.p.c("StorageUtil", "getTotalMemoryBytesForAboveU->EUASupportSizeByte is " + jA4);
            j3 = jLongValue - jA4;
            jB = a.b.a.e.l0.s.b(j3);
            a.b.a.e.l0.p.c("StorageUtil", "formatUFSShortSize->number is " + jB);
            if (contextB == null) {
                str10 = "";
            } else {
                fFloor = jB;
                if (fFloor > 900.0d) {
                    fFloor /= 1000.0f;
                    str7 = str6;
                } else {
                    str7 = str5;
                }
                if (fFloor > 900.0d) {
                    fFloor /= 1000.0f;
                } else {
                    str2 = str7;
                }
                if (fFloor > 900.0d) {
                    fFloor /= 1000.0f;
                    str8 = "GB";
                } else {
                    str8 = str2;
                }
                if (fFloor > 921.6d) {
                    fFloor = ((float) Math.floor((fFloor / 1024.0f) * 10.0f)) / 10.0f;
                    str9 = "TB";
                } else {
                    str9 = str8;
                }
                if (fFloor > 921.6d) {
                    fFloor /= 1000.0f;
                } else {
                    str3 = str9;
                }
                Locale locale6 = Locale.US;
                str10 = String.format(locale6, "%s%s", String.format(locale6, "%.0f", Float.valueOf(fFloor)), str3);
            }
        } else {
            r3 = 0;
            if (i3 > 24) {
                long jLongValue5 = ((Long) a.b.a.a.a.c.a.d((StorageManager) contextB.getSystemService("storage"), "getPrimaryStorageSize", null, new Object[0])).longValue();
                a.b.a.e.l0.p.c("StorageUtil", "getTotalMemoryBytes->above android N, mTotalMemoryByte is " + jLongValue5);
                long jB5 = a.b.a.e.l0.s.b(jLongValue5);
                str16 = "getTotalMemoryBytes->after round size, mTotalMemoryByte is ";
                sb2 = new StringBuilder();
                r3 = jB5;
            } else {
                long jC4 = a.b.a.e.l0.s.c(Environment.getDataDirectory().getTotalSpace() + Environment.getRootDirectory().getTotalSpace());
                str16 = "getTotalMemoryBytes->below android N, mTotalMemoryByte is ";
                sb2 = new StringBuilder();
                r3 = jC4;
            }
            sb2.append(str16);
            sb2.append(r3);
            a.b.a.e.l0.p.c("StorageUtil", sb2.toString());
            r4 = r3;
            locale = Locale.ENGLISH;
            if (contextB == null) {
                str10 = "";
            } else {
                fFloor2 = (float) r4;
                if (fFloor2 > 900.0d) {
                    fFloor2 /= 1000.0f;
                    str14 = "KB";
                } else {
                    str14 = "B";
                }
                if (fFloor2 > 900.0d) {
                    fFloor2 /= 1000.0f;
                } else {
                    str2 = str14;
                }
                if (fFloor2 > 900.0d) {
                    fFloor2 /= 1000.0f;
                    str2 = "GB";
                }
                if (fFloor2 > 921.6d) {
                    fFloor2 = ((float) Math.floor((fFloor2 / 1024.0f) * 10.0f)) / 10.0f;
                    str15 = "TB";
                } else {
                    str15 = str2;
                }
                if (fFloor2 > 921.6d) {
                    fFloor2 /= 1000.0f;
                    str15 = "PB";
                }
                a.b.a.e.l0.p.c("StorageUtil", "suffix: " + str15 + ", result: " + fFloor2);
                str10 = String.format(locale, "%s%s", String.format(locale, "%.0f", Float.valueOf(fFloor2)), str15);
            }
        }
        jSONObject.put("rom", str10);
        jSONObject.put("ui_ver", a.b.a.a.a.c.a.F("ro.miui.ui.version.name", ""));
        jSONObject.put("mi_os_ver", a.b.a.a.a.c.a.F("ro.mi.os.version.name", ""));
        Class cls5 = a.b.a.e.l0.r.f609a;
        jSONObject.put("android_ver_int", Build.VERSION.SDK_INT);
        jSONObject.put("uep", a.b.a.e.l0.r.v(contextB));
        jSONObject.put("sign", strSubstring);
        jSONObject.put("cust_variant", a.b.a.a.a.c.a.G("ro.miui.cust_variant", ""));
        jSONObject.put("intl", a.b.a.e.l0.r.q());
        jSONObject.put("desc", a.b.a.a.a.c.a.G("ro.build.description", ""));
        jSONObject.put("radio", a.b.a.e.l0.c.z());
        jSONObject.put("radio2", a.b.a.e.l0.c.A(1));
        try {
            Object objInvoke5 = Class.forName("android.content.pm.IPackageManager$Stub").getDeclaredMethod("asInterface", IBinder.class).invoke(null, (IBinder) Class.forName("android.os.ServiceManager").getDeclaredMethod("getService", String.class).invoke(null, "package"));
            zBooleanValue = ((Boolean) objInvoke5.getClass().getDeclaredMethod("isFirstBoot", new Class[0]).invoke(objInvoke5, new Object[0])).booleanValue();
            str13 = "is first boot:" + zBooleanValue;
            a.b.a.e.l0.p.s("ActiveEvent", str13);
            dVarV = a.b.a.a.a.c.a.V(z);
            if (dVarV != null) {
                dVarV.a(Level.WARNING, "##XLogger##, TAG: ", "ActiveEvent: " + str13, null);
            }
        } catch (Exception e28) {
            a.b.a.a.a.c.a.s(z, "ActiveEvent", "isFirstBoot e: ", e28);
            zBooleanValue = false;
        }
        jSONObject.put("first_boot", zBooleanValue);
        accountD = a.b.a.e.l0.c.D(contextB);
        if (!a.b.a.e.l0.r.q()) {
            if (accountD != null) {
                str12 = accountD.name;
            } else {
                str12 = "";
            }
            if (!TextUtils.isEmpty(str12) && !a.b.a.e.l0.u.b("r_m", false)) {
                a.b.a.e.l0.u.k("r_m", true);
            }
        }
        if (accountD == null) {
            i5 = 0;
        } else {
            i4 = 1;
            while (r1.hasNext()) {
                key = entry.getKey();
                iIntValue = entry.getValue().intValue();
                if (j(accountD, key, z)) {
                    i4 |= iIntValue;
                }
            }
            if (a.b.a.e.l0.r.o(contextB, "miui.cloud.finddevice.AccessFindDevice")) {
                if (a.b.a.c.v.g.a(contextB)) {
                    i6 = i4 | 8;
                } else {
                    i6 = i4;
                }
            } else if (a.b.a.e.l0.r.o(contextB, "android.permission.AUTHENTICATE_ACCOUNTS") || !"true".equals(((AccountManager) contextB.getSystemService("account")).getUserData(accountD, "extra_find_device_enabled"))) {
                i6 = i4;
            } else {
                i6 = i4 | 8;
            }
            if (i(contextB, accountD, z)) {
                i5 = i6 | 8192;
            } else {
                i5 = i6;
            }
        }
        jSONObject.put("bind_stats", String.valueOf(i5));
        strA = a.b.a.e.l0.h.a("release_time");
        if (!TextUtils.isEmpty(strA)) {
            j4 = Long.parseLong(strA);
        }
        jSONObject.put("release_time", j4);
        jSONObject.put("first_conect_time", a.b.a.e.l0.u.e());
        jSONObject.put("lock_state", a.b.a.a.a.c.a.E("ro.secureboot.lockstate"));
        jSONObject.put("rootable", a.b.a.a.a.c.a.E("ro.debuggable"));
        if (a.b.a.a.a.c.a.G0(a.b.a.e.e.b(), Boolean.valueOf(z))) {
            zF = a.b.a.e.l0.u.f();
            a.b.a.a.a.c.a.m(z, "ActiveEvent", "isGetNonce: " + zF);
            if (!zF) {
                String strC7 = a.b.a.e.g0.c.c(a.b.a.a.a.c.a.t(h(contextB, z).getBytes()));
                e(jSONObject, strC7, a.b.a.a.a.c.a.P(a.b.a.e.e.b(), Boolean.valueOf(z)), 1, a.b.a.a.a.c.a.E0(a.b.a.e.g0.d.d(strC7), Boolean.valueOf(z)), z);
            }
        } else {
            jSONObject.put("tz_content", a.b.a.e.g0.c.c(a.b.a.a.a.c.a.t(h(contextB, z).getBytes())));
            jSONObject.put("tz_support", false);
            jSONObject.put("tz_cpuid", g(null, z));
        }
        if (!a.b.a.e.l0.r.q() && !a.b.a.e.l0.r.w("ActiveEvent")) {
            a.b.a.c.v.j jVarD6 = a.b.a.c.v.j.d(contextB);
            Objects.requireNonNull(jVarD6);
            string2 = a.b.a.c.v.j.a.a(jVarD6.f156a).toString();
            jSONObject.put("loc", string2);
        }
        jSONObject.put("allow_report_usage", a.b.a.e.l0.r.b(contextB));
        jSONObject.put("protocol_ver", 1);
        jSONObject.put("ot_activation", z);
        try {
            strG2 = a.b.a.a.a.c.a.G("ro.boot.hwlevel", "");
        } catch (Throwable th7) {
            a.b.a.e.l0.p.h(a.b.a.e.l0.p.a(str4), "getHWLevel e", th7);
            strG2 = "";
        }
        jSONObject.put("hwlevel", strG2);
        try {
            strG3 = a.b.a.a.a.c.a.G("ro.boot.hwc", "");
        } catch (Throwable th8) {
            a.b.a.e.l0.p.h(a.b.a.e.l0.p.a(str4), "getHWC e", th8);
            strG3 = "";
        }
        jSONObject.put("hwc", strG3);
        try {
            strG4 = a.b.a.a.a.c.a.G("ro.boot.vbmeta.device_state", "");
        } catch (Throwable th9) {
            a.b.a.e.l0.p.h(a.b.a.e.l0.p.a(str4), "getDeviceState e", th9);
            strG4 = "";
        }
        jSONObject.put("vbmeta_state", strG4);
        try {
            strG5 = a.b.a.a.a.c.a.G("ro.boot.vbmeta.digest", "");
        } catch (Throwable th10) {
            a.b.a.e.l0.p.h(a.b.a.e.l0.p.a(str4), "getDigest e", th10);
            strG5 = "";
        }
        jSONObject.put("vbmeta_digest", strG5);
        if (a.b.a.e.l0.r.q()) {
            jSONObject.put("custom_version", a.b.a.e.l0.c.e());
            jSONObject.put("pure_custom_carrier", a.b.a.a.a.c.a.E("ro.miui.customized.region"));
            jSONObject.put("cota_carrier", a.b.a.a.a.c.a.E("persist.sys.cota.carrier"));
            lVarK = a.b.a.a.a.c.a.K(contextB);
            if (lVarK != null) {
                if (TextUtils.isEmpty(lVarK.f591a)) {
                    str11 = "";
                } else {
                    str11 = lVarK.f591a;
                }
                jSONObject.put("g_c_i_ms", str11);
                jSONObject.put("g_c_i_am", TextUtils.isEmpty(lVarK.f592b) ? "" : lVarK.f592b);
            }
        }
        return jSONObject;
    }

    @SuppressLint({"Range"})
    public final boolean i(Context context, Account account, boolean z) {
        boolean z2;
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = context.getContentResolver().query(i, null, "account_name = ?", new String[]{account.name}, null);
                z2 = ((cursorQuery == null || !cursorQuery.moveToFirst()) ? 0 : cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("is_open"))) == 1;
            } catch (Exception e) {
                a.b.a.a.a.c.a.s(z, "ActiveEvent", "isMiCloudBackupOpened e: ", e);
            }
            return z2;
        } finally {
            a.b.a.a.a.c.a.i(cursorQuery);
        }
    }

    public final boolean j(Account account, String str, boolean z) {
        try {
            return ((Boolean) ContentResolver.class.getDeclaredMethod("getSyncAutomatically", Account.class, String.class).invoke(null, account, str)).booleanValue();
        } catch (Throwable th) {
            a.b.a.a.a.c.a.s(z, "ActiveEvent", "isSyncAutomatically e: ", th);
            return false;
        }
    }

    public final String k(List<String> list) {
        return list.size() == 0 ? "" : String.format("[%s]", TextUtils.join(",", list));
    }
}
