package g1;

import android.content.Context;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public class c {
    public static byte[] a(String str, byte[] bArr) {
        try {
            try {
                try {
                    try {
                        try {
                            try {
                                try {
                                    byte[] bArr2 = new byte[16];
                                    System.arraycopy(bArr, 0, bArr2, 0, 8);
                                    System.arraycopy(bArr, 24, bArr2, 8, 8);
                                    GCMParameterSpec gCMParameterSpec = new GCMParameterSpec(128, bArr2);
                                    SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
                                    Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
                                    cipher.init(1, secretKeySpec, gCMParameterSpec);
                                    return cipher.doFinal(str.getBytes("utf-8"));
                                } catch (BadPaddingException e2) {
                                    e2.printStackTrace();
                                    return null;
                                }
                            } catch (NoSuchPaddingException e3) {
                                e3.printStackTrace();
                                return null;
                            }
                        } catch (InvalidKeyException e4) {
                            e4.printStackTrace();
                            return null;
                        }
                    } catch (NoSuchAlgorithmException e5) {
                        e5.printStackTrace();
                        return null;
                    }
                } catch (IllegalBlockSizeException e6) {
                    e6.printStackTrace();
                    return null;
                }
            } catch (UnsupportedEncodingException e7) {
                e7.printStackTrace();
                return null;
            }
        } catch (Throwable unused) {
            return null;
        }
    }

    public static byte[] b(Context context, byte[] bArr) {
        j jVar = new j();
        return jVar.a(jVar.b(context.getResources().getAssets().open("cudms_main_pub.cer")), bArr);
    }

    public static byte[] c(String str) {
        try {
            return MessageDigest.getInstance("SHA-256").digest(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new RuntimeException("Huh, MD5 should be supported?", e3);
        }
    }
}
