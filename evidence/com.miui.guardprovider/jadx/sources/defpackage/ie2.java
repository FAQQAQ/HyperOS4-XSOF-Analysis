package defpackage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.miui.guardprovider.GuardApplication;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import tmsdk.common.tcc.DeepCleanSAFEngine;

/* JADX INFO: loaded from: classes.dex */
public abstract class ie2 {
    public static final String a = "MIUI-" + Build.VERSION.INCREMENTAL;

    public static String a(ArrayList arrayList) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
            jSONObject.put("os", a);
            jSONObject.put("biz_id", "virus_scan");
            jSONObject.put("uuid", n61.A());
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < arrayList.size(); i++) {
                JSONObject jSONObject2 = new JSONObject();
                he2 he2Var = (he2) arrayList.get(i);
                jSONObject2.put("pkg", he2Var.b);
                jSONObject2.put("version", he2Var.c);
                jSONObject2.put("signature", he2Var.d);
                jSONObject2.put("appname", he2Var.a);
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("content", jSONArray);
            return ls1.d(GuardApplication.application, jSONObject);
        } catch (Exception e) {
            Log.e("AntiDefraudAppManager", "getAllUnSystemAppsStatus error, ", e);
            return null;
        }
    }

    public static String b(String str) {
        boolean zG;
        vo1 vo1VarD = vo1.d(GuardApplication.application);
        vo1VarD.getClass();
        try {
            zG = vo1VarD.g(str, c(GuardApplication.application, str));
        } catch (Exception e) {
            e.printStackTrace();
            zG = false;
        }
        if (zG) {
            return str;
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0018  */
    public static String c(Context context, String str) {
        Signature[] signatureArr;
        String str2 = null;
        if (str == null || str.length() == 0) {
            signatureArr = null;
        } else {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
                if (packageInfo == null) {
                    signatureArr = null;
                } else {
                    signatureArr = packageInfo.signatures;
                }
            } catch (PackageManager.NameNotFoundException e) {
                Log.e("AntiDefraudAppManager", "getRawSignature error, ", e);
            }
        }
        if (signatureArr == null || signatureArr.length == 0) {
            return null;
        }
        byte[] byteArray = signatureArr[0].toByteArray();
        char[] cArr = {DeepCleanSAFEngine.DetailPath.KEY, DeepCleanSAFEngine.DetailPath.DETAIL_PATH, DeepCleanSAFEngine.DetailPath.FILENAME, DeepCleanSAFEngine.DetailPath.FILESIZE, DeepCleanSAFEngine.DetailPath.CTIME, DeepCleanSAFEngine.DetailPath.MTIME, DeepCleanSAFEngine.DetailPath.ATIME, DeepCleanSAFEngine.DetailPath.IMPORTANT, '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(byteArray);
            byte[] bArrDigest = messageDigest.digest();
            char[] cArr2 = new char[bArrDigest.length * 2];
            int i = 0;
            for (byte b : bArrDigest) {
                int i2 = i + 1;
                cArr2[i] = cArr[(b >>> 4) & 15];
                i += 2;
                cArr2[i2] = cArr[b & 15];
            }
            str2 = new String(cArr2);
            return str2;
        } catch (Exception e2) {
            Log.e("AntiDefraudAppManager", "getMessageDigest error, ", e2);
            return str2;
        }
    }

    public static ArrayList d(GuardApplication guardApplication) {
        PackageManager packageManager = guardApplication.getPackageManager();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
        ArrayList arrayList = new ArrayList();
        for (PackageInfo packageInfo : installedPackages) {
            if ((packageInfo.applicationInfo.flags & 1) != 1) {
                String str = packageInfo.packageName;
                he2 he2Var = new he2();
                he2Var.b = str;
                he2Var.c = String.valueOf(packageInfo.versionCode);
                he2Var.d = c(guardApplication, str);
                try {
                    he2Var.a = URLEncoder.encode(packageInfo.applicationInfo.loadLabel(packageManager).toString(), "UTF-8");
                } catch (Exception e) {
                    Log.e("AntiDefraudAppManager", "getUnSystemAppList error, ", e);
                }
                arrayList.add(he2Var);
            }
        }
        return arrayList;
    }

    public static void e(Context context) {
        try {
            String strA = a(d(GuardApplication.application));
            if (TextUtils.isEmpty(strA)) {
                return;
            }
            vo1.d(context).f(strA);
        } catch (Exception e) {
            Log.e("AntiDefraudAppManager", "updateAllDetectApps error, ", e);
        }
    }
}
