package a.b.a.c.v;

import android.content.Context;
import android.content.pm.Signature;
import android.text.TextUtils;
import android.util.Log;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class c {
    public static void a(Context context) {
        try {
            a.b.a.a.a.c.a.x(new p(context));
        } catch (Throwable th) {
            Log.e(i.a("Utils"), "fix DB bug exception: ", th);
        }
    }

    public static String b(String str) {
        return TextUtils.isEmpty(str) ? "" : c(str.getBytes());
    }

    public static String c(byte[] bArr) {
        String str;
        if (bArr != null) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(bArr);
                str = String.format("%1$032X", new BigInteger(1, messageDigest.digest()));
            } catch (Exception unused) {
                str = "";
            }
        } else {
            str = "";
        }
        return str.toLowerCase(Locale.US);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static boolean d(Context context, String str, String str2) {
        Signature[] signatureArr;
        try {
            signatureArr = context.getPackageManager().getPackageInfo(str, 64).signatures;
        } catch (Exception unused) {
            signatureArr = null;
        }
        return e(signatureArr, str2);
    }

    public static boolean e(Signature[] signatureArr, String str) {
        StringBuilder sbC = a.a.a.a.a.c("sigs: ");
        sbC.append(signatureArr == null ? 0 : signatureArr.length);
        i.e("CertUtils", sbC.toString());
        i.e("CertUtils", "sigMd5String: " + str);
        if (signatureArr == null || signatureArr.length <= 0 || signatureArr[0] == null || TextUtils.isEmpty(str)) {
            return false;
        }
        StringBuilder sbC2 = a.a.a.a.a.c("sigs md5: ");
        sbC2.append(c(signatureArr[0].toByteArray()));
        i.e("CertUtils", sbC2.toString());
        return str.equalsIgnoreCase(c(signatureArr[0].toByteArray()));
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static boolean f(Context context, String str) {
        Signature[] signatureArr;
        try {
            signatureArr = context.getPackageManager().getPackageInfo(str, 64).signatures;
        } catch (Exception unused) {
            signatureArr = null;
        }
        return g(signatureArr);
    }

    public static boolean g(Signature[] signatureArr) {
        return e(signatureArr, "701478a1e3b4b7e3978ea69469410f13");
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0034: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:17:0x0034 */
    public static Object h(String str) throws Throwable {
        ObjectInputStream objectInputStream;
        Closeable closeable;
        Closeable closeable2 = null;
        try {
            try {
                objectInputStream = new ObjectInputStream(new FileInputStream(str));
                try {
                    Object object = objectInputStream.readObject();
                    a.b.a.a.a.c.a.j(objectInputStream);
                    return object;
                } catch (Exception e) {
                    e = e;
                    i.g("ObjectStore", "failed to read object, ex: " + e);
                    a.b.a.a.a.c.a.j(objectInputStream);
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                closeable2 = closeable;
                a.b.a.a.a.c.a.j(closeable2);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            objectInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            a.b.a.a.a.c.a.j(closeable2);
            throw th;
        }
    }
}
