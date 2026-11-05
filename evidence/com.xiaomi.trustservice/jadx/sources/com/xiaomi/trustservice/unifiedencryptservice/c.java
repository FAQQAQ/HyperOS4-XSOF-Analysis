package com.xiaomi.trustservice.unifiedencryptservice;

import A0.o;
import android.os.Binder;
import android.util.Slog;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* JADX INFO: loaded from: classes.dex */
public final class c extends IMiUnifiedEncryptService.Stub {
    public final /* synthetic */ unifiedEncryptService a;

    public c(unifiedEncryptService unifiedencryptservice) {
        this.a = unifiedencryptservice;
    }

    @Override // com.xiaomi.trustservice.unifiedencryptservice.IMiUnifiedEncryptService
    public final int deleteAesKeyBag(String str) {
        if (str == null || str.isEmpty()) {
            Slog.e("unifiedEncryptService", "alias is null, cannot delete AesKeyBag");
            return -1000;
        }
        int callingPid = Binder.getCallingPid();
        unifiedEncryptService unifiedencryptservice = this.a;
        String strO = o.O(unifiedencryptservice, callingPid);
        if (strO == null) {
            Slog.e("unifiedEncryptService", "packageName is null, cannot get AesKeyBag");
            return -1000;
        }
        if (!unifiedEncryptService.a(strO)) {
            return -16;
        }
        String str2 = "/data/system/unifiedencrypt_" + strO + "_";
        unifiedencryptservice.getClass();
        if (str2 == null) {
            Slog.e("unifiedEncryptService", "invalid parameter, cannot delete app key");
            return -3;
        }
        String strE = unifiedencryptservice.e(str2, str);
        if (strE != null && !strE.isEmpty()) {
            return unifiedencryptservice.f(str2, str, "") ? 0 : -1000;
        }
        Slog.d("unifiedEncryptService", "app key " + str + " is not exists");
        return -1;
    }

    @Override // com.xiaomi.trustservice.unifiedencryptservice.IMiUnifiedEncryptService
    public final int genAesKeyBag(AesKeyBag aesKeyBag) {
        unifiedEncryptService unifiedencryptservice = this.a;
        try {
            if (aesKeyBag == null) {
                Slog.e("unifiedEncryptService", "aesKeyBag is null, cannot gen AesKeyBag");
                return -1000;
            }
            String strO = o.O(unifiedencryptservice, Binder.getCallingPid());
            if (strO == null) {
                Slog.e("unifiedEncryptService", "packageName is null, cannot gen AesKeyBag");
                return -1000;
            }
            if (!unifiedEncryptService.a(strO)) {
                return -16;
            }
            unifiedEncryptService.b(strO);
            return unifiedEncryptService.c(unifiedencryptservice, "/data/system/unifiedencrypt_" + strO + "_", aesKeyBag, strO);
        } catch (IOException | InvalidAlgorithmParameterException | InvalidKeyException | KeyStoreException | NoSuchAlgorithmException | NoSuchProviderException | UnrecoverableKeyException | CertificateException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e2) {
            Slog.e("unifiedEncryptService", "genAesKeyBag error: " + e2.toString());
            return -1000;
        }
    }

    @Override // com.xiaomi.trustservice.unifiedencryptservice.IMiUnifiedEncryptService
    public final int getAesKeyBag(String str, AesKeyBag[] aesKeyBagArr) {
        unifiedEncryptService unifiedencryptservice = this.a;
        if (aesKeyBagArr != null) {
            try {
                if (aesKeyBagArr.length > 0) {
                    String strO = o.O(unifiedencryptservice, Binder.getCallingPid());
                    if (strO == null) {
                        Slog.e("unifiedEncryptService", "packageName is null, cannot get AesKeyBag");
                        return -1000;
                    }
                    if (!unifiedEncryptService.a(strO)) {
                        return -16;
                    }
                    return unifiedEncryptService.d(unifiedencryptservice, "/data/system/unifiedencrypt_" + strO + "_", str, strO, aesKeyBagArr);
                }
            } catch (IOException | InvalidAlgorithmParameterException | InvalidKeyException | KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException | CertificateException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e2) {
                Slog.e("unifiedEncryptService", "getAesKeyBag error: " + e2.toString());
                return -1000;
            }
        }
        Slog.e("unifiedEncryptService", "key is null, cannot get AesKeyBag");
        return -1000;
    }

    @Override // com.xiaomi.trustservice.unifiedencryptservice.IMiUnifiedEncryptService
    public final String getVersion() {
        return "1.0.3.2025.06.30";
    }
}
