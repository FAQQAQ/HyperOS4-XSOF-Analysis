package qi;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.miui.riskyapp.model.CloudScanApkInfo;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/* JADX INFO: loaded from: classes3.dex */
public abstract class a {
    private static CloudScanApkInfo a(PackageManager packageManager, PackageInfo packageInfo) throws Throwable {
        String strD;
        String strD2;
        Signature[] apkContentsSigners;
        String str = packageInfo.packageName;
        String strValueOf = String.valueOf(packageInfo.versionCode);
        CharSequence charSequenceLoadLabel = packageInfo.applicationInfo.loadLabel(packageManager);
        String string = charSequenceLoadLabel != null ? charSequenceLoadLabel.toString() : str;
        if (packageInfo.signingInfo == null || (apkContentsSigners = packageInfo.signingInfo.getApkContentsSigners()) == null || apkContentsSigners.length <= 0) {
            strD = "";
            strD2 = strD;
        } else {
            strD = d(apkContentsSigners, "MD5", true);
            strD2 = d(apkContentsSigners, "SHA-256", true);
        }
        String strG = g(packageInfo.applicationInfo.sourceDir);
        return new CloudScanApkInfo(str, strValueOf, strD, strD2, strG != null ? strG : "", string);
    }

    public static List b(Context context, String str) {
        List<PackageInfo> installedPackages;
        ArrayList arrayList = new ArrayList();
        try {
            PackageManager packageManager = context.getPackageManager();
            if (TextUtils.isEmpty(str)) {
                installedPackages = packageManager.getInstalledPackages(C.BUFFER_FLAG_FIRST_SAMPLE);
            } else {
                PackageInfo packageInfo = packageManager.getPackageInfo(str, C.BUFFER_FLAG_FIRST_SAMPLE);
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(packageInfo);
                installedPackages = arrayList2;
            }
            if (installedPackages != null) {
                for (PackageInfo packageInfo2 : installedPackages) {
                    ApplicationInfo applicationInfo = packageInfo2.applicationInfo;
                    if (applicationInfo != null && (applicationInfo.flags & 1) == 0) {
                        arrayList.add(a(packageManager, packageInfo2));
                    }
                }
            }
            return arrayList;
        } catch (Exception e10) {
            Log.e("ApkInfoBuilder", "buildInstalledPackageList failed", e10);
            return arrayList;
        }
    }

    static String c(byte[] bArr, boolean z10) {
        StringBuilder sb2 = new StringBuilder(bArr.length * 2);
        String str = z10 ? "%02X" : "%02x";
        for (byte b10 : bArr) {
            sb2.append(String.format(str, Integer.valueOf(b10 & 255)));
        }
        return sb2.toString();
    }

    static String d(Signature[] signatureArr, String str, boolean z10) {
        StringBuilder sb2 = new StringBuilder();
        for (int i10 = 0; i10 < signatureArr.length; i10++) {
            if (i10 > 0) {
                sb2.append("|");
            }
            sb2.append(e(signatureArr[i10].toByteArray(), str, z10));
        }
        return sb2.toString();
    }

    static String e(byte[] bArr, String str, boolean z10) {
        try {
            return c(MessageDigest.getInstance(str).digest(bArr), z10);
        } catch (Exception e10) {
            Log.e("ApkInfoBuilder", "hashBytes failed for " + str, e10);
            return "";
        }
    }

    static String f(InputStream inputStream, String str, boolean z10) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            byte[] bArr = new byte[4096];
            while (true) {
                int i10 = inputStream.read(bArr);
                if (i10 <= 0) {
                    return c(messageDigest.digest(), z10);
                }
                messageDigest.update(bArr, 0, i10);
            }
        } catch (Exception unused) {
            return null;
        }
    }

    static String g(String str) throws Throwable {
        JarFile jarFile;
        JarFile jarFile2 = null;
        if (str == null) {
            return null;
        }
        try {
            jarFile = new JarFile(str, false);
            try {
                JarEntry jarEntry = jarFile.getJarEntry("META-INF/MANIFEST.MF");
                if (jarEntry == null) {
                    try {
                        jarFile.close();
                    } catch (Exception unused) {
                    }
                    return null;
                }
                InputStream inputStream = jarFile.getInputStream(jarEntry);
                try {
                    String strF = f(inputStream, "SHA-256", false);
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    try {
                        jarFile.close();
                    } catch (Exception unused2) {
                    }
                    return strF;
                } catch (Throwable th2) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th3) {
                            th2.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            } catch (Exception unused3) {
                if (jarFile != null) {
                    try {
                        jarFile.close();
                    } catch (Exception unused4) {
                    }
                }
                return null;
            } catch (Throwable th4) {
                th = th4;
                jarFile2 = jarFile;
                if (jarFile2 != null) {
                    try {
                        jarFile2.close();
                    } catch (Exception unused5) {
                    }
                }
                throw th;
            }
        } catch (Exception unused6) {
            jarFile = null;
        } catch (Throwable th5) {
            th = th5;
        }
    }
}
