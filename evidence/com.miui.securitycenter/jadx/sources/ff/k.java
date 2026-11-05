package ff;

import android.util.Base64;
import android.util.Log;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* JADX INFO: loaded from: classes3.dex */
public class k {
    private PublicKey b() {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/gT0vSqtsv7y4c7qLn1tvJF6Uur067h8pf7VXJNNqYxVWdpR5la5QuLIi90bZntPGsxM7IDSTL7Wia9ox+CbalDC3+ouPMVIjMK9tqWfb8F0XQJi8eHlZ4KpGZxqQDOcisxGQHClig/dD/CkqlX4eTyK7HDRVRS8Jj6SiaWDY2QIDAQAB", 2)));
        } catch (Exception e10) {
            Log.d("RSA_Encryption", "get public key error!");
            e10.printStackTrace();
            return null;
        }
    }

    public byte[] a(String str) {
        try {
            PublicKey publicKeyB = b();
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(1, publicKeyB);
            byte[] bytes = str.getBytes();
            int length = (bytes.length - 1) / 117;
            int i10 = length + 1;
            byte[] bArr = new byte[i10 * 128];
            byte[] bArr2 = new byte[117];
            for (int i11 = 0; i11 < i10; i11++) {
                if (i11 == length) {
                    int i12 = i11 * 117;
                    int length2 = bytes.length - i12;
                    if (length2 <= 0) {
                        break;
                    }
                    byte[] bArr3 = new byte[length2];
                    System.arraycopy(bytes, i12, bArr3, 0, length2);
                    bArr2 = bArr3;
                } else {
                    System.arraycopy(bytes, i11 * 117, bArr2, 0, 117);
                }
                System.arraycopy(cipher.doFinal(bArr2), 0, bArr, i11 * 128, 128);
            }
            return bArr;
        } catch (Exception e10) {
            Log.d("RSA_Encryption", "encrypt error!");
            e10.printStackTrace();
            return null;
        }
    }
}
