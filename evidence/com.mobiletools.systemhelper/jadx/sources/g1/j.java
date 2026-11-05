package g1;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.crypto.Cipher;

/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static char[] f2680a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public byte[] a(Key key, byte[] bArr) {
        byte[] bArrDoFinal;
        if (key == null) {
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(1, key);
            int length = bArr.length;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i2 = 0;
            int i3 = 0;
            while (true) {
                int i4 = length - i2;
                if (i4 <= 0) {
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    return byteArray;
                }
                if (i4 > 245) {
                    try {
                        bArrDoFinal = cipher.doFinal(bArr, i2, 245);
                    } catch (Throwable th) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                } else {
                    bArrDoFinal = cipher.doFinal(bArr, i2, i4);
                }
                byteArrayOutputStream.write(bArrDoFinal, 0, bArrDoFinal.length);
                i3++;
                i2 = i3 * 245;
            }
        } catch (Exception e2) {
            h.b("RSAHelper", e2.getMessage());
            return null;
        }
    }

    public Key b(InputStream inputStream) {
        try {
            return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(inputStream)).getPublicKey();
        } catch (CertificateException e2) {
            e2.printStackTrace();
            h.b("RSAHelper", e2.getMessage());
            return null;
        }
    }
}
