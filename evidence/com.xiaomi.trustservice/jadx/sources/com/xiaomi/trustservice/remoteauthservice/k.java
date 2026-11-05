package com.xiaomi.trustservice.remoteauthservice;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.fingerprint.FingerprintManager;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Slog;
import com.android.keyguard.crossendsunlock.IMiSystemUIRemoteAuth;
import com.xiaomi.trustservice.remoteservice.locksettings.AuthUIActivity;
import com.xiaomi.trustservice.remoteservice.locksettings.InternalAuthUIActivity;
import com.xiaomi.trustservice.remoteservice.locksettings.LockScreenUIActivity;
import com.xiaomi.trustservice.t;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidator;
import java.security.cert.CertPathValidatorException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXParameters;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Timer;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import vendor.xiaomi.hardware.misauth.SharedAuthResponse;

/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public static boolean f1681J = false;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public static String f1682K = null;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public static int f1683L = 544;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public static int f1684M = 561;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public static k f1685N;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public IMiRemoteAuthUnlockUICallback f1702m;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Z0.b f1711v;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f1713x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f1714y;
    public IMiSystemUIRemoteAuth a = null;
    public long b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f1694c = 0;
    public String d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f1695e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f1696f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f1697g = null;
    public String h = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f1698i = null;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public IMiRemoteAuthServiceCallback f1699j = null;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public IMiRemoteAuthServiceCallback f1700k = null;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public IMiRemoteAuthServiceUICallback f1701l = null;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f1703n = false;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f1704o = false;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f1705p = false;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f1706q = false;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1707r = false;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f1708s = false;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Object f1709t = new Object();

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final Object f1710u = new Object();

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public SharedAuthResponse f1712w = new SharedAuthResponse();

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public String f1715z = null;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f1686A = false;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public String f1687B = null;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public String f1688C = null;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public String f1689D = null;
    public String E = null;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public String f1690F = null;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public boolean f1691G = false;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final h f1692H = new h(this);

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final i f1693I = new i(this);

    public static void A(int i2, int i3, String str) {
        Slog.d("MiTrustService/quickAuthEventHandle", "send shared auth error " + i3 + " and reset all, stage=" + Integer.toHexString(i2));
        E1.c cVar = new E1.c();
        if (str == null) {
            Slog.d("MiTrustService/quickAuthEventHandle", "device id is null when send error message");
            return;
        }
        try {
            cVar.p(Integer.valueOf((i2 < 544 || i2 > 553) ? 555 : 538), "event_name");
            cVar.p(Integer.valueOf(i3), "error_code");
        } catch (E1.b unused) {
            Slog.d("MiTrustService/quickAuthEventHandle", "send shared auth error json error");
        }
        P0.c.f234v.d(str, cVar.toString());
    }

    public static void a(E1.c cVar, P0.c cVar2, String str) {
        Slog.d("MiTrustService/quickAuthEventHandle", "TLS1");
        if (cVar.a.containsKey("client_hello")) {
            E1.c cVar3 = new E1.c();
            cVar3.p(593, "event_name");
            cVar3.p("1234567890123456123456789012345612345678901234561234567890123456", "sessionkey");
            cVar3.p("server_done", "server_done");
            cVar2.d(str, cVar3.toString());
        }
    }

    public static String k(String str) {
        int iIndexOf = str.indexOf("-----BEGIN CERTIFICATE-----");
        int iIndexOf2 = str.indexOf("-----END CERTIFICATE-----");
        if (iIndexOf == -1 || iIndexOf2 == -1) {
            return null;
        }
        return str.substring(iIndexOf + 27, iIndexOf2).replaceAll("\\s+", "");
    }

    public static boolean r(String str) {
        if (str == null) {
            return false;
        }
        SharedPreferences sharedPreferences = P0.c.f234v.a.getSharedPreferences("remoteauth_bind", 0);
        if (!sharedPreferences.contains("bioSig_".concat(str))) {
            Slog.d("MiTrustService/quickAuthEventHandle", "no biometric baseline for " + str + ", skip change detection");
            return false;
        }
        String string = sharedPreferences.getString("bioSig_".concat(str), "");
        String strK = A0.o.K(P0.c.f234v.a);
        if (strK == null) {
            Slog.d("MiTrustService/quickAuthEventHandle", "current biometric signature unavailable, treat as changed");
            return true;
        }
        boolean zEquals = string.equals(strK);
        boolean z2 = !zEquals;
        if (!zEquals) {
            StringBuilder sb = new StringBuilder("biometric enrollment changed[");
            sb.append(str);
            sb.append("]: saved=");
            sb.append(string);
            sb.append(" current=");
            B.b.i(sb, strK, "MiTrustService/quickAuthEventHandle");
        }
        return z2;
    }

    public static boolean s() {
        String str = SystemProperties.get("sys.locksettings.protectorid", "");
        if (str.isEmpty()) {
            return false;
        }
        String string = P0.c.f234v.a.getSharedPreferences("remoteauth_bind", 0).getString("protectorId", "");
        if (string.isEmpty()) {
            return false;
        }
        boolean zEquals = str.equals(string);
        boolean z2 = !zEquals;
        if (!zEquals) {
            Slog.d("MiTrustService/quickAuthEventHandle", "protectorId changed: saved=" + string + " current=" + str);
        }
        return z2;
    }

    public static void y(String str) {
        if (str == null) {
            Slog.d("MiTrustService/quickAuthEventHandle", "saveBiometricBaseline: deviceId is null");
            return;
        }
        String strK = A0.o.K(P0.c.f234v.a);
        P0.c.f234v.a.getSharedPreferences("remoteauth_bind", 0).edit().putString("bioSig_".concat(str), strK == null ? "" : strK).apply();
        Slog.d("MiTrustService/quickAuthEventHandle", "saveBiometricBaseline[" + str + "]: " + strK);
    }

    public static void z() {
        String str = SystemProperties.get("sys.locksettings.protectorid", "");
        if (str.isEmpty()) {
            return;
        }
        P0.c.f234v.a.getSharedPreferences("remoteauth_bind", 0).edit().putString("protectorId", str).apply();
        Slog.d("MiTrustService/quickAuthEventHandle", "saveProtectorId: ".concat(str));
    }

    public final void B(P0.c cVar, int i2, int i3) {
        int i4;
        boolean z2;
        boolean z3;
        Slog.d("MiTrustService/quickAuthEventHandle", "startAuth");
        if (a.a().c()) {
            A(f1684M, 1792, this.f1698i);
            u();
            return;
        }
        Account[] accountsByType = AccountManager.get(cVar.a.getApplicationContext()).getAccountsByType("com.xiaomi");
        if (accountsByType.length == 0) {
            Slog.d("MiTrustService/quickAuthEventHandle", "mi user account is not logged in");
            A(f1684M, 1794, this.f1698i);
            u();
            return;
        }
        this.f1695e = accountsByType[0].name;
        Slog.d("MiTrustService/quickAuthEventHandle", "current mi user account is: " + this.f1695e);
        this.f1696f = cVar.a();
        this.f1697g = this.f1695e + this.f1696f;
        try {
            SharedAuthResponse authHATChallengeV2 = a.a().a.getAuthHATChallengeV2();
            this.f1712w = authHATChallengeV2;
            if (authHATChallengeV2.b == 0 && authHATChallengeV2.a != null) {
                E1.c cVar2 = new E1.c();
                cVar2.p(563, "event_name");
                cVar2.q("flash_timestamp", true);
                cVar.d(this.f1698i, cVar2.toString());
                Slog.d("MiTrustService/quickAuthEventHandle", "auth type is " + i3 + ", authLevel is :" + i2);
                if (i3 == 0) {
                    if (i2 == 2) {
                        if (P0.c.f234v.a.getPackageManager().hasSystemFeature("android.hardware.fingerprint") ? ((FingerprintManager) P0.c.f234v.a.getSystemService("fingerprint")).hasEnrolledFingerprints() : false) {
                            boolean zHasEnrolledFingerprints = ((FingerprintManager) P0.c.f234v.a.getSystemService("fingerprint")).hasEnrolledFingerprints();
                            if (zHasEnrolledFingerprints) {
                                int i5 = Settings.Secure.getInt(P0.c.f234v.a.getContentResolver(), "miui_keyguard", 1);
                                Slog.d("MiTrustService/quickAuthEventHandle", "miui_keyguard is :" + i5);
                                zHasEnrolledFingerprints = i5 == 2;
                            }
                            if (zHasEnrolledFingerprints) {
                                i4 = 5;
                            }
                        }
                    } else if (i2 != 1) {
                        Slog.d("MiTrustService/quickAuthEventHandle", "not support this strength: " + i2);
                        A(f1684M, 2566, this.f1698i);
                        u();
                        return;
                    }
                    i4 = 3;
                } else {
                    i4 = i3;
                }
                if (i4 == 1 || i4 == 3 || i4 == 4) {
                    Slog.d("MiTrustService/quickAuthEventHandle", "start locksetting activity");
                    Z0.b bVarI = Z0.b.i(cVar.a);
                    this.f1711v = bVarI;
                    if (!bVarI.n().booleanValue()) {
                        Slog.d("MiTrustService/quickAuthEventHandle", "locksetting absence");
                        A(f1684M, 5, this.f1698i);
                        u();
                        return;
                    }
                    AuthUIActivity.f1739g = 5;
                    AuthUIActivity.f1737e = Z0.b.e(this.f1712w.a);
                    AuthUIActivity.f1740i = this.f1715z;
                    AuthUIActivity.h = this.f1686A;
                    InternalAuthUIActivity.f1744g = 5;
                    InternalAuthUIActivity.f1742e = Z0.b.e(this.f1712w.a);
                    InternalAuthUIActivity.f1745i = this.f1715z;
                    InternalAuthUIActivity.h = this.f1686A;
                    this.f1686A = false;
                    if (this.f1707r) {
                        Slog.d("MiTrustService/quickAuthEventHandle", "need call by external");
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (z2 && this.f1701l == null) {
                        Slog.d("MiTrustService/quickAuthEventHandle", "cant be called by external");
                        z2 = false;
                    }
                    if (z2) {
                        E1.c cVar3 = new E1.c();
                        cVar3.p(0, "error_code");
                        this.f1701l.onShowAuthInputUI(cVar3.toString());
                        return;
                    } else {
                        Intent intent = new Intent();
                        intent.setClass(P0.c.f234v.a, InternalAuthUIActivity.class);
                        intent.addFlags(268468224);
                        P0.c.f234v.a.startActivity(intent);
                        return;
                    }
                }
                if (i4 != 5) {
                    if (i4 == 6) {
                        Slog.d("MiTrustService/quickAuthEventHandle", "not support face auth");
                        A(f1684M, 2566, this.f1698i);
                        u();
                        return;
                    } else {
                        Slog.d("MiTrustService/quickAuthEventHandle", "unkown type :" + i4);
                        A(f1684M, 2566, this.f1698i);
                        u();
                        return;
                    }
                }
                Slog.d("MiTrustService/quickAuthEventHandle", "start fingerprint activity");
                if (!((FingerprintManager) P0.c.f234v.a.getSystemService("fingerprint")).hasEnrolledFingerprints()) {
                    Slog.d("MiTrustService/quickAuthEventHandle", "fingerprint absence");
                    A(f1684M, 5, this.f1698i);
                    u();
                    return;
                }
                int i6 = Settings.Secure.getInt(P0.c.f234v.a.getContentResolver(), "auth_wrong_fingerprint_attempt_num", 0);
                if (i6 >= 3) {
                    Slog.d("MiTrustService/quickAuthEventHandle", "fingerprint lockout, error count: " + i6);
                    A(f1684M, 24, this.f1698i);
                    u();
                    return;
                }
                AuthUIActivity.f1739g = 4;
                AuthUIActivity.f1737e = Z0.b.e(this.f1712w.a);
                AuthUIActivity.f1740i = this.f1715z;
                AuthUIActivity.h = this.f1686A;
                InternalAuthUIActivity.f1744g = 4;
                InternalAuthUIActivity.f1742e = Z0.b.e(this.f1712w.a);
                InternalAuthUIActivity.f1745i = this.f1715z;
                InternalAuthUIActivity.h = this.f1686A;
                this.f1686A = false;
                if (this.f1707r) {
                    Slog.d("MiTrustService/quickAuthEventHandle", "need call by external");
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z3 && this.f1701l == null) {
                    Slog.d("MiTrustService/quickAuthEventHandle", "cant be called by external");
                    z3 = false;
                }
                if (z3) {
                    E1.c cVar4 = new E1.c();
                    cVar4.p(0, "error_code");
                    this.f1701l.onShowAuthInputUI(cVar4.toString());
                    return;
                } else {
                    Intent intent2 = new Intent();
                    intent2.setClass(P0.c.f234v.a, InternalAuthUIActivity.class);
                    intent2.addFlags(268468224);
                    P0.c.f234v.a.startActivity(intent2);
                    return;
                }
            }
            Slog.d("MiTrustService/quickAuthEventHandle", "get hardware auth token challenge A failed");
            A(f1684M, 1792, this.f1698i);
            u();
        } catch (E1.b | RemoteException unused) {
            Slog.d("MiTrustService/quickAuthEventHandle", "call shared auth service failed");
            A(f1684M, 1792, this.f1698i);
            u();
            a.a().a = null;
        }
    }

    public final void C(String str, String str2, boolean z2, P0.c cVar, IMiRemoteAuthServiceCallback iMiRemoteAuthServiceCallback) {
        IMiSystemUIRemoteAuth iMiSystemUIRemoteAuth;
        Slog.d("MiTrustService/quickAuthEventHandle", "startSharedAuthBinding");
        if (f1683L != 544) {
            Slog.d("MiTrustService/quickAuthEventHandle", "binding already in progress, stage=" + Integer.toHexString(f1683L) + ", reset and retry");
            v();
            i();
        }
        synchronized (this.f1710u) {
            this.f1699j = iMiRemoteAuthServiceCallback;
        }
        if (str == null) {
            w(2306, 0);
            Slog.d("MiTrustService/quickAuthEventHandle", "input package or devcieid name is null");
            return;
        }
        Account[] accountsByType = AccountManager.get(cVar.a.getApplicationContext()).getAccountsByType("com.xiaomi");
        if (accountsByType.length == 0) {
            w(1794, 0);
            Slog.d("MiTrustService/quickAuthEventHandle", "mi user account is not logged in");
            return;
        }
        this.f1695e = accountsByType[0].name;
        B.b.i(new StringBuilder("current mi user account is: "), this.f1695e, "MiTrustService/quickAuthEventHandle");
        this.h = str2;
        this.f1696f = cVar.a();
        this.f1697g = this.f1695e + this.f1696f;
        if (!z2 || (iMiSystemUIRemoteAuth = this.a) == null) {
            e(cVar);
            return;
        }
        this.f1704o = false;
        this.f1705p = true;
        this.f1703n = z2;
        iMiSystemUIRemoteAuth.remoteAuthUnlock(str2, 0, 1, this.f1693I);
    }

    public final void D(IMiRemoteAuthServiceCallback iMiRemoteAuthServiceCallback, P0.c cVar, boolean z2) {
        IMiSystemUIRemoteAuth iMiSystemUIRemoteAuth;
        Slog.d("MiTrustService/quickAuthEventHandle", "[Risk 1]:startRiskAuth");
        synchronized (this.f1710u) {
            this.f1700k = iMiRemoteAuthServiceCallback;
        }
        Z0.b bVarI = Z0.b.i(cVar.a);
        this.f1711v = bVarI;
        if (bVarI.j() == -1) {
            Slog.d("MiTrustService/quickAuthEventHandle", "credential type mode");
            w(4, 1);
        } else {
            if (!z2 || (iMiSystemUIRemoteAuth = this.a) == null) {
                f();
                return;
            }
            this.f1703n = z2;
            this.f1704o = true;
            iMiSystemUIRemoteAuth.remoteAuthUnlock(cVar.a(), 0, 1, this.f1693I);
        }
    }

    public final void E(int i2, int i3, P0.c cVar, IMiRemoteAuthUnlockUICallback iMiRemoteAuthUnlockUICallback) {
        Slog.d("MiTrustService/quickAuthEventHandle", "startSharedAuthForSystemUIUsing");
        this.f1702m = iMiRemoteAuthUnlockUICallback;
        if (this.f1706q) {
            Slog.d("MiTrustService/quickAuthEventHandle", "remote password unlock mode, only save callback");
            this.f1706q = false;
        } else if (this.f1704o) {
            this.f1704o = false;
            f();
        } else if (!this.f1705p) {
            m(cVar, i2, i3);
        } else {
            this.f1705p = false;
            e(cVar);
        }
    }

    public final void F(String str, int i2, int i3, boolean z2, boolean z3, P0.c cVar, IMiRemoteAuthServiceCallback iMiRemoteAuthServiceCallback) {
        boolean zR;
        IMiSystemUIRemoteAuth iMiSystemUIRemoteAuth;
        Slog.d("MiTrustService/quickAuthEventHandle", "startSharedAuthUsing");
        synchronized (this.f1710u) {
            this.f1700k = iMiRemoteAuthServiceCallback;
        }
        f1681J = true;
        this.f1691G = false;
        Account[] accountsByType = AccountManager.get(cVar.a.getApplicationContext()).getAccountsByType("com.xiaomi");
        if (accountsByType.length == 0) {
            Slog.d("MiTrustService/quickAuthEventHandle", "mi user account is not logged in");
            w(1794, 1);
            return;
        }
        this.f1695e = accountsByType[0].name;
        B.b.i(new StringBuilder("current mi user account is: "), this.f1695e, "MiTrustService/quickAuthEventHandle");
        Z0.b bVarI = Z0.b.i(cVar.a);
        this.f1711v = bVarI;
        if (bVarI.j() == -1) {
            Slog.d("MiTrustService/quickAuthEventHandle", "credential type mode");
            w(4, 1);
            u();
            return;
        }
        String str2 = SystemProperties.get("sys.locksettings.remoteauth.enable", "false");
        Slog.d("MiTrustService/quickAuthEventHandle", "locksetting usable status: " + str2);
        if (!str2.equals("true")) {
            Slog.d("MiTrustService/quickAuthEventHandle", "device is locked, cant bind");
            w(6, 1);
            u();
            return;
        }
        int iM = this.f1711v.m();
        Slog.d("MiTrustService/quickAuthEventHandle", "strongAuth res is: " + iM);
        if (iM == 16) {
            Slog.d("MiTrustService/quickAuthEventHandle", "locksetting 72h hasnot used,need use it on phone");
            w(6, 1);
            u();
            return;
        }
        if (s()) {
            Slog.d("MiTrustService/quickAuthEventHandle", "B-side protectorId changed, need re-bindinding");
            w(10, 1);
            u();
            return;
        }
        String strConcat = "shareAuthBind".concat(str);
        if (A0.o.T(strConcat)) {
            Context context = P0.c.f234v.a;
            zR = A0.o.X(strConcat) || r(str);
        } else {
            zR = r(str);
        }
        if (zR) {
            Slog.d("MiTrustService/quickAuthEventHandle", "B-side biometric changed, need risk auth");
            f1682K = str;
            w(8, 1);
            u();
            return;
        }
        this.f1698i = str;
        this.f1696f = cVar.a();
        this.f1697g = this.f1695e + this.f1696f;
        this.f1713x = i3;
        this.f1714y = i2;
        this.f1707r = z3;
        Slog.d("MiTrustService/quickAuthEventHandle", "unlockUI is: " + z2);
        this.f1703n = z2;
        if (!z2 || (iMiSystemUIRemoteAuth = this.a) == null) {
            m(cVar, i2, this.f1713x);
            return;
        }
        int i4 = this.f1713x;
        if (i4 <= 2) {
            this.f1704o = false;
            iMiSystemUIRemoteAuth.remoteAuthUnlock(str, i2, i4, this.f1693I);
        } else {
            Slog.d("MiTrustService/quickAuthEventHandle", "authlevel too weak,cant unlock ui");
            u();
            w(2565, 1);
        }
    }

    public final void G(String str) {
        Slog.d("MiTrustService/quickAuthEventHandle", "[Authing 3]:verify auth token A");
        if (a.a().c()) {
            u();
            w(1792, 1);
            return;
        }
        try {
            SharedAuthResponse sharedAuthResponseCheckAuthTokenAV2 = a.a().a.checkAuthTokenAV2(A0.o.A0(str));
            if (sharedAuthResponseCheckAuthTokenAV2.b == 0 && sharedAuthResponseCheckAuthTokenAV2.a != null) {
                u();
                this.f1691G = true;
                if (!this.f1703n) {
                    w(0, 1);
                    Slog.d("MiTrustService/quickAuthEventHandle", "auth success");
                    return;
                } else {
                    Slog.d("MiTrustService/quickAuthEventHandle", "auth success, call UI to unlock");
                    this.f1703n = false;
                    this.f1702m.onResult(0);
                    return;
                }
            }
            Slog.d("MiTrustService/quickAuthEventHandle", "verify auth token A failed");
            u();
            int i2 = sharedAuthResponseCheckAuthTokenAV2.b;
            if (i2 == 7) {
                w(14, 1);
            } else if (i2 == 8) {
                w(13, 1);
            } else {
                w(1792, 1);
            }
        } catch (RemoteException unused) {
            Slog.d("MiTrustService/quickAuthEventHandle", "call shared auth service failed");
            u();
            w(1792, 1);
            a.a().a = null;
        }
    }

    public final void H() {
        Slog.d("MiTrustService/quickAuthEventHandle", "user input wrong password at A");
        u();
        i();
        P0.c.c(P0.c.f234v.a);
        if (this.f1698i != null) {
            B.b.i(new StringBuilder("mSharedDeviceId :"), this.f1698i, "MiTrustService/quickAuthEventHandle");
            A(f1684M, 2, this.f1698i);
        } else {
            Slog.d("MiTrustService/quickAuthEventHandle", "mSharedDeviceId is null");
        }
        u();
    }

    public final void b(E1.c cVar, P0.c cVar2, String str) {
        Slog.d("MiTrustService/quickAuthEventHandle", "TLS2");
        this.E = cVar.h("sessionkey");
        E1.c cVar3 = new E1.c();
        cVar3.p(594, "event_name");
        cVar3.p(this.E, "sessionkey");
        cVar3.p("client_key_exchange", "client_key_exchange");
        cVar2.d(str, cVar3.toString());
    }

    public final void c(E1.c cVar, P0.c cVar2, String str) {
        Slog.d("MiTrustService/quickAuthEventHandle", "TLS3");
        this.E = cVar.h("sessionkey");
        if (this.f1688C != null) {
            E1.c cVar3 = new E1.c();
            cVar3.p(this.f1689D, "shared_auth_id_A");
            cVar3.p(this.f1688C, "identityPubkeyA");
            cVar3.p("xiaomi", "pubkey_signer");
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(A0.o.A0(this.E), "AES");
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                cipher.init(1, secretKeySpec);
                String strB0 = A0.o.B0(cipher.doFinal());
                SecretKeySpec secretKeySpec2 = new SecretKeySpec(A0.o.A0(this.E), "HmacSHA256");
                Mac mac = Mac.getInstance("HmacSHA256");
                mac.init(secretKeySpec2);
                String strB1 = A0.o.B0(mac.doFinal());
                E1.c cVar4 = new E1.c();
                cVar4.p(547, "event_name");
                cVar4.p(strB0, "ciphertext");
                cVar4.p(strB1, "hmac");
                cVar2.d(str, cVar4.toString());
                v();
                this.f1688C = null;
                this.f1689D = null;
                this.E = null;
            } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e2) {
                Slog.d("MiTrustService/quickAuthEventHandle", "cipher error:" + e2);
                this.f1688C = null;
                this.f1689D = null;
                this.E = null;
                A(f1683L, 1793, str);
                v();
            }
        }
    }

    public final void d(E1.c cVar, P0.c cVar2) {
        Slog.d("MiTrustService/quickAuthEventHandle", "[Binding 2]:get shared auth id for bind A");
        if (a.a().c()) {
            A(f1683L, 1792, this.h);
            v();
            return;
        }
        Account[] accountsByType = AccountManager.get(cVar2.a.getApplicationContext()).getAccountsByType("com.xiaomi");
        if (accountsByType.length == 0) {
            Slog.d("MiTrustService/quickAuthEventHandle", "mi user account is not logged in");
            A(f1683L, 1794, this.h);
            v();
            return;
        }
        this.f1695e = accountsByType[0].name;
        Slog.d("MiTrustService/quickAuthEventHandle", "current mi user account is: " + this.f1695e);
        this.f1696f = cVar2.a();
        this.f1697g = this.f1695e + this.f1696f;
        this.d = cVar.h("shared_auth_id_B");
        try {
            Z0.b bVarI = Z0.b.i(cVar2.a);
            this.f1711v = bVarI;
            if (bVarI.j() == -1) {
                A(f1683L, 5, this.h);
                v();
                return;
            }
            SharedAuthResponse bindHATChallengeV2 = a.a().a.getBindHATChallengeV2();
            if (bindHATChallengeV2.b == 0 && bindHATChallengeV2.a != null) {
                byte[] bArr = new byte[69];
                for (int i2 = 1; i2 < 69; i2++) {
                    byte[] bArr2 = bindHATChallengeV2.a;
                    if (i2 > bArr2.length) {
                        break;
                    }
                    bArr[i2] = bArr2[i2 - 1];
                }
                n(bArr);
                return;
            }
            Slog.d("MiTrustService/quickAuthEventHandle", "get bind challenge A failed");
            A(f1683L, 1792, this.h);
            v();
        } catch (RemoteException unused) {
            Slog.d("MiTrustService/quickAuthEventHandle", "call shared auth service failed");
            A(f1683L, 1792, this.h);
            v();
            a.a().a = null;
        }
    }

    public final void e(P0.c cVar) {
        byte[] bArr;
        Slog.d("MiTrustService/quickAuthEventHandle", "[Binding 1]:get quick auth id for bind B");
        if (a.a().c()) {
            Slog.d("MiTrustService/quickAuthEventHandle", "failed to get misa service");
            w(1792, 0);
            return;
        }
        E1.c cVar2 = new E1.c();
        cVar2.p(595, "event_name");
        cVar2.p("client_hello", "client_hello");
        cVar.d(this.h, cVar2.toString());
        try {
            Z0.b bVarI = Z0.b.i(cVar.a);
            this.f1711v = bVarI;
            if (bVarI.j() == -1) {
                Slog.d("MiTrustService/quickAuthEventHandle", "credential type mode");
                w(4, 0);
                v();
                return;
            }
            SharedAuthResponse bindHATChallengeV2 = a.a().a.getBindHATChallengeV2();
            if (bindHATChallengeV2.b == 0 && (bArr = bindHATChallengeV2.a) != null) {
                this.f1711v.h(this, bArr, this.h, "Bind_B");
                return;
            }
            Slog.d("MiTrustService/quickAuthEventHandle", "get bind challenge A failed");
            w(1792, 0);
            v();
        } catch (RemoteException unused) {
            Slog.d("MiTrustService/quickAuthEventHandle", "call shared auth service failed");
            v();
            w(1792, 0);
            a.a().a = null;
        }
    }

    public final void f() {
        byte[] bArr;
        Slog.d("MiTrustService/quickAuthEventHandle", "authForRisk");
        if (a.a().c()) {
            w(1792, 1);
            return;
        }
        SharedAuthResponse riskHATChallengeV2 = a.a().a.getRiskHATChallengeV2();
        if (riskHATChallengeV2.b == 0 && (bArr = riskHATChallengeV2.a) != null) {
            this.f1711v.h(this, bArr, P0.c.f234v.a(), "Risk_B");
        } else {
            Slog.d("MiTrustService/quickAuthEventHandle", "get risk challenge A failed");
            w(1792, 1);
        }
    }

    public final void g(P0.c cVar, String str, String str2) {
        int i2;
        LockScreenUIActivity lockScreenUIActivity;
        InternalAuthUIActivity internalAuthUIActivity;
        AuthUIActivity authUIActivity;
        Slog.d("MiTrustService/quickAuthEventHandle", "cancelQuickAuth");
        E1.c cVar2 = new E1.c();
        if (AuthUIActivity.f1738f && (authUIActivity = AuthUIActivity.d) != null) {
            authUIActivity.a();
        }
        if (InternalAuthUIActivity.f1743f && (internalAuthUIActivity = InternalAuthUIActivity.d) != null) {
            internalAuthUIActivity.a();
        }
        if (LockScreenUIActivity.E && (lockScreenUIActivity = LockScreenUIActivity.f1753J) != null) {
            lockScreenUIActivity.n();
        }
        if (str2.equals("cancelBindRemote")) {
            v();
            cVar2.p(537, "event_name");
            i2 = 0;
        } else {
            if (str2.equals("cancelRemoteAuth")) {
                u();
                cVar2.p(560, "event_name");
            } else if (str2.equals("cancelRiskAuth")) {
                w(3, 1);
                return;
            }
            i2 = 1;
        }
        cVar.d(str, cVar2.toString());
        Slog.d("MiTrustService/quickAuthEventHandle", "authType is " + this.f1714y + " mode is " + i2);
        if (this.f1714y == 5 && i2 == 1) {
            w(20, i2);
        } else {
            w(3, i2);
        }
    }

    public final boolean h(String str, String str2, String str3) {
        String strK;
        Slog.d("MiTrustService/quickAuthEventHandle", "checkCertificate");
        InputStream inputStreamOpenRawResource = P0.c.f234v.a.getResources().openRawResource(t.car_chain);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamOpenRawResource));
            StringBuilder sb = new StringBuilder();
            Slog.d("MiTrustService/quickAuthEventHandle", "get local certificate");
            loop0: while (true) {
                boolean z2 = false;
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break loop0;
                    }
                    String strTrim = line.trim();
                    if (strTrim.startsWith("-----BEGIN CERTIFICATE-----")) {
                        sb.setLength(0);
                        z2 = true;
                    } else if (!strTrim.startsWith("-----END CERTIFICATE-----") || !z2) {
                        if (z2) {
                            sb.append(strTrim);
                        }
                    }
                }
                arrayList.add((X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(Base64.getDecoder().decode(sb.toString()))));
            }
            bufferedReader.close();
            Slog.d("MiTrustService/quickAuthEventHandle", "get remote certificate");
            for (String str4 : str.split("(?=-----BEGIN CERTIFICATE-----)")) {
                if (!str4.trim().isEmpty() && (strK = k(str4)) != null) {
                    arrayList2.add((X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(Base64.getDecoder().decode(strK))));
                }
            }
            try {
                Slog.d("MiTrustService/quickAuthEventHandle", "check certificate");
                KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                keyStore.load(null, null);
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    keyStore.setCertificateEntry("ca_" + i2, (Certificate) arrayList.get(i2));
                }
                CertPath certPathGenerateCertPath = CertificateFactory.getInstance("X.509").generateCertPath(arrayList2);
                CertPathValidator certPathValidator = CertPathValidator.getInstance("PKIX");
                PKIXParameters pKIXParameters = new PKIXParameters(keyStore);
                pKIXParameters.setRevocationEnabled(false);
                certPathValidator.validate(certPathGenerateCertPath, pKIXParameters);
                try {
                    Slog.d("MiTrustService/quickAuthEventHandle", "check signature");
                    PublicKey publicKey = ((X509Certificate) arrayList2.get(0)).getPublicKey();
                    Signature signature = Signature.getInstance(((X509Certificate) arrayList2.get(0)).getSigAlgName());
                    Slog.d("MiTrustService/quickAuthEventHandle", "signame:" + ((X509Certificate) arrayList2.get(0)).getSigAlgName());
                    signature.initVerify(publicKey);
                    signature.update((this.f1690F + str3).getBytes());
                    return signature.verify(A0.o.A0(str2));
                } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException e2) {
                    Slog.d("MiTrustService/quickAuthEventHandle", "error:" + e2);
                    return false;
                }
            } catch (IOException | InvalidAlgorithmParameterException | KeyStoreException | NoSuchAlgorithmException | CertPathValidatorException | CertificateException e3) {
                Slog.d("MiTrustService/quickAuthEventHandle", "error:" + e3);
                return false;
            }
        } catch (IOException | CertificateException e4) {
            Slog.d("MiTrustService/quickAuthEventHandle", "error:" + e4);
            return false;
        }
    }

    public final void i() {
        this.E = null;
        this.f1688C = null;
        this.f1689D = null;
        this.f1690F = null;
    }

    public final void j(byte[] bArr) {
        Slog.d("MiTrustService/quickAuthEventHandle", "enableBindB");
        g.h(false);
        g.g();
        if (this.f1703n) {
            Slog.d("MiTrustService/quickAuthEventHandle", "bind auth success, call UI to unlock");
            this.f1703n = false;
            this.f1708s = true;
            this.f1702m.onResult(0);
        }
        P0.c cVarC = P0.c.c(P0.c.f234v.a);
        if (this.f1697g == null) {
            Slog.d("MiTrustService/quickAuthEventHandle", "can't get mix local device id at B");
            v();
            w(2563, 0);
            return;
        }
        SharedAuthResponse sharedAuthResponse = new SharedAuthResponse();
        try {
            SharedAuthResponse sharedAuthResponseCheckHATV2 = a.a().a.checkHATV2(bArr);
            if (sharedAuthResponseCheckHATV2.b == 0 && sharedAuthResponseCheckHATV2.a != null) {
                sharedAuthResponse = a.a().a.getDeviceSAID(this.f1697g.getBytes());
                if (sharedAuthResponse.b != 0 || sharedAuthResponse.a == null) {
                    Slog.d("MiTrustService/quickAuthEventHandle", "get device shared auth id B failed");
                    v();
                    w(1792, 0);
                    return;
                }
                byte[] bArr2 = new byte[32];
                new SecureRandom().nextBytes(bArr2);
                this.f1690F = A0.o.B0(bArr2);
                long jCurrentTimeMillis = System.currentTimeMillis();
                String str = this.h;
                f1683L = 546;
                this.b = jCurrentTimeMillis;
                this.h = str;
                E1.c cVar = new E1.c();
                cVar.p(546, "event_name");
                cVar.p(A0.o.B0(sharedAuthResponse.a), "shared_auth_id_B");
                cVar.p(this.f1690F, "bind_challenge");
                cVarC.d(this.h, cVar.toString());
                new Timer().schedule(new j(this, jCurrentTimeMillis, 0), 10000L);
                return;
            }
            Slog.d("MiTrustService/quickAuthEventHandle", "get bind pub failed");
            v();
            w(1792, 0);
        } catch (RemoteException unused) {
            Slog.d("MiTrustService/quickAuthEventHandle", "call shared auth service failed");
            v();
            w(1792, 0);
            a.a().a = null;
        }
    }

    public final void l(P0.c cVar, byte[] bArr) {
        Slog.d("MiTrustService/quickAuthEventHandle", "[Authing 2]:generate auth token A");
        if (a.a().c()) {
            A(f1684M, 1792, this.f1698i);
            u();
            return;
        }
        if (this.f1697g == null) {
            Slog.d("MiTrustService/quickAuthEventHandle", "can't get mix local device id at B");
            A(f1684M, 2563, this.f1698i);
            u();
            return;
        }
        for (int i2 = 1; i2 < bArr.length; i2++) {
            try {
                byte[] bArr2 = this.f1712w.a;
                if (i2 > bArr2.length) {
                    break;
                }
                bArr[i2] = bArr2[i2 - 1];
            } catch (RemoteException unused) {
                Slog.d("MiTrustService/quickAuthEventHandle", "call shared auth service failed");
                A(f1684M, 1792, this.f1698i);
                u();
                a.a().a = null;
                return;
            }
        }
        Slog.d("MiTrustService/quickAuthEventHandle", "mAuthTokenB:" + this.f1687B);
        SharedAuthResponse authTokenAV2 = a.a().a.getAuthTokenAV2(this.f1697g.getBytes(), bArr, A0.o.A0(this.f1687B), new byte[]{(byte) this.f1713x});
        if (authTokenAV2.b != 0 || authTokenAV2.a == null) {
            Slog.d("MiTrustService/quickAuthEventHandle", "generate auth token A failed");
            A(f1684M, 1792, this.f1698i);
            u();
        } else {
            E1.c cVar2 = new E1.c();
            cVar2.p(563, "event_name");
            cVar2.p(A0.o.B0(authTokenAV2.a), "auth_token_A");
            cVar.d(this.f1698i, cVar2.toString());
            u();
        }
    }

    public final void m(P0.c cVar, int i2, int i3) {
        Slog.d("MiTrustService/quickAuthEventHandle", "[Authing 1]:get shared auth id and challenge for token B");
        if (a.a().c()) {
            w(1792, 1);
            return;
        }
        if (this.f1697g == null) {
            Slog.d("MiTrustService/quickAuthEventHandle", "can't get mix local device id at B");
            w(2563, 1);
            return;
        }
        String str = SystemProperties.get("sys.locksettings.protectorid", (String) null);
        if (str == null) {
            Slog.d("MiTrustService/quickAuthEventHandle", "can not get protect id");
            w(2564, 1);
            return;
        }
        try {
            SharedAuthResponse deviceSAID = a.a().a.getDeviceSAID(this.f1697g.getBytes());
            if (deviceSAID.b == 0 && deviceSAID.a != null) {
                SharedAuthResponse authTokenBV2 = a.a().a.getAuthTokenBV2(this.f1697g.getBytes(), deviceSAID.a, A0.o.A0(A0.o.m0(str)), new byte[]{-128});
                int i4 = authTokenBV2.b;
                if (i4 != 0 || authTokenBV2.a == null) {
                    if (i4 != 3) {
                        Slog.d("MiTrustService/quickAuthEventHandle", "get auth_token failed");
                        w(1792, 1);
                        return;
                    }
                    if (SystemProperties.getBoolean("vendor.security.miriskmanager.ignore_reboot_risk_supported", false)) {
                        Slog.d("MiTrustService/quickAuthEventHandle", "unexpected reboot risk from HAL (statusCode=3)");
                        w(1792, 1);
                        return;
                    } else if (g.e()) {
                        Slog.d("MiTrustService/quickAuthEventHandle", "TA reports reboot but risk auth already done this boot, treat as internal error");
                        w(1792, 1);
                        return;
                    } else {
                        Slog.d("MiTrustService/quickAuthEventHandle", "device reboot , need risk auth");
                        g.h(true);
                        w(11, 1);
                        return;
                    }
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                String str2 = this.f1698i;
                f1684M = 562;
                this.f1694c = jCurrentTimeMillis;
                this.f1698i = str2;
                Slog.d("MiTrustService/quickAuthEventHandle", "auth type is " + i2 + ", authLevel is :" + i3);
                E1.c cVar2 = new E1.c();
                cVar2.p(562, "event_name");
                cVar2.p(A0.o.B0(deviceSAID.a), "shared_auth_id_B");
                cVar2.p(A0.o.B0(authTokenBV2.a), "auth_token_B");
                cVar2.p(Integer.valueOf(i2), "auth_type");
                cVar2.p(Integer.valueOf(i3), "auth_level");
                cVar2.q("external_call", this.f1707r);
                cVar2.q("cancel_is_timeout", this.f1686A);
                this.f1686A = false;
                String str3 = this.f1715z;
                if (str3 != null) {
                    cVar2.p(str3, "remote_side_title");
                    B.b.i(new StringBuilder("remote_side_title:"), this.f1715z, "MiTrustService/quickAuthEventHandle");
                }
                cVar.d(this.f1698i, cVar2.toString());
                new Timer().schedule(new j(this, jCurrentTimeMillis, 1), 10000L);
                return;
            }
            Slog.d("MiTrustService/quickAuthEventHandle", "get device shared auth id B failed");
            w(1792, 1);
        } catch (RemoteException unused) {
            Slog.d("MiTrustService/quickAuthEventHandle", "call shared auth service failed");
            w(1792, 1);
            a.a().a = null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:34:0x00e5  */
    public final void n(byte[] bArr) {
        byte[] bArr2;
        byte[] bArr3;
        Slog.d("MiTrustService/quickAuthEventHandle", "genBindPubA");
        if (bArr.length == 0) {
            Slog.d("MiTrustService/quickAuthEventHandle", "HAT is null");
            A(f1683L, 2309, this.h);
            v();
            return;
        }
        if (this.d == null) {
            Slog.d("MiTrustService/quickAuthEventHandle", "shared SA id is null");
            A(f1683L, 2310, this.h);
            v();
            return;
        }
        try {
            SharedAuthResponse bindPubV2 = a.a().a.getBindPubV2(bArr, A0.o.A0(this.d));
            if (bindPubV2.b == 0 && (bArr2 = bindPubV2.a) != null) {
                this.f1688C = A0.o.B0(bArr2);
                SharedAuthResponse deviceSAID = a.a().a.getDeviceSAID(this.f1697g.getBytes());
                if (deviceSAID.b == 0 && (bArr3 = deviceSAID.a) != null) {
                    this.f1689D = A0.o.B0(bArr3);
                    boolean z2 = false;
                    if (P0.c.f234v.a.getPackageManager().hasSystemFeature("android.hardware.fingerprint")) {
                        boolean zHasEnrolledFingerprints = ((FingerprintManager) P0.c.f234v.a.getSystemService("fingerprint")).hasEnrolledFingerprints();
                        if (zHasEnrolledFingerprints) {
                            int i2 = Settings.Secure.getInt(P0.c.f234v.a.getContentResolver(), "miui_keyguard", 1);
                            Slog.d("MiTrustService/quickAuthEventHandle", "miui_keyguard is :" + i2);
                            zHasEnrolledFingerprints = i2 == 2;
                        }
                        if (zHasEnrolledFingerprints) {
                            int i3 = Settings.Secure.getInt(P0.c.f234v.a.getContentResolver(), "auth_wrong_fingerprint_attempt_num", 0);
                            Slog.d("MiTrustService/quickAuthEventHandle", "finger error count is: " + i3);
                            if (i3 < 3) {
                                z2 = zHasEnrolledFingerprints;
                            }
                        } else {
                            z2 = zHasEnrolledFingerprints;
                        }
                    }
                    if (z2) {
                        B.b.i(new StringBuilder("create biometric keystore key for "), this.h, "MiTrustService/quickAuthEventHandle");
                        Context context = P0.c.f234v.a;
                        if (!A0.o.y("shareAuthBind" + this.h)) {
                            Slog.d("MiTrustService/quickAuthEventHandle", "create biometric keystore key failed");
                            A(f1683L, 1793, this.h);
                            v();
                            return;
                        }
                    }
                    if (this.E != null) {
                        E1.c cVar = new E1.c();
                        cVar.p(this.f1689D, "shared_auth_id_A");
                        cVar.p(this.f1688C, "identityPubkeyA");
                        cVar.p("xiaomi", "pubkey_signer");
                        try {
                            SecretKeySpec secretKeySpec = new SecretKeySpec(A0.o.A0(this.E), "AES");
                            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                            cipher.init(1, secretKeySpec);
                            String strB0 = A0.o.B0(cipher.doFinal(cVar.toString().getBytes()));
                            SecretKeySpec secretKeySpec2 = new SecretKeySpec(A0.o.A0(this.E), "HmacSHA256");
                            Mac mac = Mac.getInstance("HmacSHA256");
                            mac.init(secretKeySpec2);
                            String strB1 = A0.o.B0(mac.doFinal(strB0.getBytes()));
                            E1.c cVar2 = new E1.c();
                            cVar2.p(547, "event_name");
                            cVar2.p(strB0, "ciphertext");
                            cVar2.p(strB1, "hmac");
                            P0.c.f234v.d(this.h, cVar2.toString());
                            z();
                            y(this.h);
                            v();
                            this.f1688C = null;
                            this.f1689D = null;
                            this.E = null;
                            return;
                        } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e2) {
                            Slog.d("MiTrustService/quickAuthEventHandle", "cipher error:" + e2);
                            this.f1688C = null;
                            this.f1689D = null;
                            this.E = null;
                            A(f1683L, 1793, this.h);
                            v();
                            return;
                        }
                    }
                    return;
                }
                Slog.d("MiTrustService/quickAuthEventHandle", "get device shared auth id A failed");
                A(f1683L, 1792, this.h);
                v();
                return;
            }
            Slog.d("MiTrustService/quickAuthEventHandle", "get bind pub A failed");
            A(f1683L, 1792, this.h);
            v();
        } catch (RemoteException unused) {
            Slog.d("MiTrustService/quickAuthEventHandle", "call shared auth service failed");
            A(f1683L, 1792, this.h);
            v();
            a.a().a = null;
        }
    }

    public final void o(E1.c cVar, P0.c cVar2, String str) {
        LockScreenUIActivity lockScreenUIActivity;
        InternalAuthUIActivity internalAuthUIActivity;
        AuthUIActivity authUIActivity;
        Slog.d("MiTrustService/quickAuthEventHandle", "handle quick auth binding");
        synchronized (this.f1709t) {
            try {
                if (cVar.a.containsKey("error_code")) {
                    Slog.d("MiTrustService/quickAuthEventHandle", "remote device error: " + cVar.e("error_code"));
                    w(cVar.e("error_code"), 0);
                    v();
                    return;
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                int iE = cVar.e("event_name");
                if (f1683L == 547 && iE == 546) {
                    v();
                } else if (iE == 537) {
                    f1683L = 537;
                    this.b = jCurrentTimeMillis;
                    this.h = str;
                }
                Slog.d("MiTrustService/quickAuthEventHandle", "local stage is " + Integer.toHexString(f1683L));
                int i2 = f1683L;
                if (i2 == 537) {
                    Slog.d("MiTrustService/quickAuthEventHandle", "eventName.QUICK_CANCEL_BINDING");
                    v();
                    if (AuthUIActivity.f1738f && (authUIActivity = AuthUIActivity.d) != null) {
                        authUIActivity.a();
                    }
                    if (InternalAuthUIActivity.f1743f && (internalAuthUIActivity = InternalAuthUIActivity.d) != null) {
                        internalAuthUIActivity.a();
                    }
                    if (LockScreenUIActivity.E && (lockScreenUIActivity = LockScreenUIActivity.f1753J) != null) {
                        lockScreenUIActivity.n();
                    }
                } else if (i2 == 544) {
                    Slog.d("MiTrustService/quickAuthEventHandle", "eventName.QUICK_INITIAL_BIND_STATE");
                    if (iE == 546) {
                        f1683L = 547;
                        this.b = jCurrentTimeMillis;
                        this.h = str;
                        d(cVar, cVar2);
                    } else {
                        Slog.d("MiTrustService/quickAuthEventHandle", "illegal remote stage, break now");
                        A(f1683L, 24, str);
                        v();
                    }
                } else if (i2 != 546) {
                    Slog.d("MiTrustService/quickAuthEventHandle", "illegal local stage, break now");
                    w(1793, 0);
                    A(f1683L, 1793, str);
                    v();
                } else {
                    Slog.d("MiTrustService/quickAuthEventHandle", "eventName.QUICK_START_SHARED_AUTH_B");
                    if (iE == 547) {
                        f1683L = 548;
                        this.b = jCurrentTimeMillis;
                        this.h = str;
                        t(cVar);
                    } else {
                        Slog.d("MiTrustService/quickAuthEventHandle", "illegal remote stage, break now");
                        w(24, 0);
                        v();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void p(E1.c cVar, P0.c cVar2, String str) {
        LockScreenUIActivity lockScreenUIActivity;
        InternalAuthUIActivity internalAuthUIActivity;
        AuthUIActivity authUIActivity;
        Slog.d("MiTrustService/quickAuthEventHandle", "handle shared auth using");
        synchronized (this.f1709t) {
            try {
                if (cVar.a.containsKey("error_code")) {
                    Slog.d("MiTrustService/quickAuthEventHandle", "remote device error: " + cVar.e("error_code"));
                    u();
                    w(cVar.e("error_code"), 1);
                    return;
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                int iE = cVar.e("event_name");
                if (iE == 562 && f1684M == 563) {
                    u();
                } else if (iE == 560) {
                    f1684M = 560;
                    this.f1694c = jCurrentTimeMillis;
                    this.f1698i = str;
                }
                Slog.d("MiTrustService/quickAuthEventHandle", "local stage is " + Integer.toHexString(f1684M));
                switch (f1684M) {
                    case 560:
                        Slog.d("MiTrustService/quickAuthEventHandle", "eventName.QUICK_CANCEL_USING");
                        u();
                        if (AuthUIActivity.f1738f && (authUIActivity = AuthUIActivity.d) != null) {
                            authUIActivity.a();
                        }
                        if (InternalAuthUIActivity.f1743f && (internalAuthUIActivity = InternalAuthUIActivity.d) != null) {
                            internalAuthUIActivity.a();
                        }
                        if (LockScreenUIActivity.E && (lockScreenUIActivity = LockScreenUIActivity.f1753J) != null) {
                            lockScreenUIActivity.n();
                        }
                        break;
                    case 561:
                        Slog.d("MiTrustService/quickAuthEventHandle", "eventName.QUICK_INITIAL_AUTH_STATE");
                        if (iE != 562) {
                            Slog.d("MiTrustService/quickAuthEventHandle", "illegal stage, break now");
                            A(f1684M, 1793, str);
                            u();
                        } else {
                            this.f1713x = cVar.e("auth_level");
                            this.f1714y = cVar.e("auth_type");
                            this.d = cVar.h("shared_auth_id_B");
                            this.f1687B = cVar.h("auth_token_B");
                            this.f1707r = cVar.d("external_call");
                            Slog.d("MiTrustService/quickAuthEventHandle", "external call is :" + this.f1707r);
                            this.f1715z = null;
                            if (cVar.a.containsKey("remote_side_title")) {
                                this.f1715z = cVar.h("remote_side_title");
                                Slog.d("MiTrustService/quickAuthEventHandle", "remote_side_title:" + this.f1715z);
                            }
                            if (cVar.a.containsKey("cancel_is_timeout")) {
                                this.f1686A = cVar.d("cancel_is_timeout");
                            }
                            f1684M = 563;
                            this.f1694c = jCurrentTimeMillis;
                            this.f1698i = str;
                            B(cVar2, this.f1713x, this.f1714y);
                        }
                        break;
                    case 562:
                        Slog.d("MiTrustService/quickAuthEventHandle", "eventName.QUICK_GEN_AUTHTOKEN_B");
                        if (iE != 563) {
                            Slog.d("MiTrustService/quickAuthEventHandle", "illegal remote stage, break now");
                            u();
                            w(1793, 1);
                        } else if (cVar.a.containsKey("flash_timestamp")) {
                            f1684M = 562;
                            this.f1694c = jCurrentTimeMillis;
                            this.f1698i = str;
                            return;
                        } else {
                            f1684M = 565;
                            this.f1694c = jCurrentTimeMillis;
                            this.f1698i = str;
                            G(cVar.h("auth_token_A"));
                        }
                        break;
                    default:
                        Slog.d("MiTrustService/quickAuthEventHandle", "illegal local stage, break now");
                        A(f1684M, 1793, str);
                        u();
                        w(1793, 1);
                        break;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void q(E1.c cVar, P0.c cVar2, String str) {
        synchronized (this.f1709t) {
            try {
                switch (cVar.e("event_name")) {
                    case 593:
                        b(cVar, cVar2, str);
                        break;
                    case 594:
                        c(cVar, cVar2, str);
                        break;
                    case 595:
                        a(cVar, cVar2, str);
                        break;
                    default:
                        Slog.d("MiTrustService/quickAuthEventHandle", "unknow command");
                        break;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void t(E1.c cVar) {
        Slog.d("MiTrustService/quickAuthEventHandle", "loadBindPubA");
        if (a.a().c()) {
            w(1792, 0);
            v();
            return;
        }
        String strH = cVar.h("ciphertext");
        String strH2 = cVar.h("hmac");
        if (this.E == null) {
            Slog.d("MiTrustService/quickAuthEventHandle", "session key is null");
            v();
            w(2311, 0);
            return;
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(A0.o.A0(this.E), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);
            String strB0 = A0.o.B0(mac.doFinal(strH.getBytes()));
            if (!strB0.equals(strH2)) {
                Slog.d("MiTrustService/quickAuthEventHandle", "hmac not match,A:" + strH2 + " B:" + strB0);
                return;
            }
            SecretKeySpec secretKeySpec2 = new SecretKeySpec(A0.o.A0(this.E), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(2, secretKeySpec2);
            E1.c cVar2 = new E1.c(new String(cipher.doFinal(A0.o.A0(strH))));
            String strH3 = cVar2.h("shared_auth_id_A");
            String strH4 = cVar2.h("identityPubkeyA");
            if (cVar2.h("pubkey_signer").equals("xiaomi_car")) {
                if (!h(cVar2.h("certificate_car"), cVar2.h("signature_car"), strH4)) {
                    Slog.d("MiTrustService/quickAuthEventHandle", "certificateCar check failed");
                    w(2312, 0);
                    v();
                    return;
                }
                Slog.d("MiTrustService/quickAuthEventHandle", "certificate check success!");
            }
            if (a.a().a.loadBindPubAV2(A0.o.A0(strH3), A0.o.A0(strH4)).b != 0) {
                Slog.d("MiTrustService/quickAuthEventHandle", "load pubkey A failed");
                v();
                w(1792, 0);
                return;
            }
            Context context = P0.c.f234v.a;
            A0.o.y("shareAuthBind" + this.h);
            y(this.h);
            v();
            z();
            w(0, 0);
        } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e2) {
            Slog.d("MiTrustService/quickAuthEventHandle", "cipher error:" + e2);
            this.E = null;
            v();
            w(1793, 0);
        }
    }

    public final void u() {
        f1684M = 561;
        this.f1694c = 0L;
        this.f1698i = null;
    }

    public final void v() {
        f1683L = 544;
        this.b = 0L;
        this.h = null;
    }

    public final void w(int i2, int i3) {
        synchronized (this.f1710u) {
            Slog.d("MiTrustService/quickAuthEventHandle", "returnErrorCode errorCode is: " + i2 + "mode is: " + i3);
            E1.c cVar = new E1.c();
            try {
                cVar.p(Integer.valueOf(i2), "errorCode");
            } catch (E1.b e2) {
                Slog.d("MiTrustService/quickAuthEventHandle", "json error: " + e2);
            }
            if (i3 == 0) {
                IMiRemoteAuthServiceCallback iMiRemoteAuthServiceCallback = this.f1699j;
                if (iMiRemoteAuthServiceCallback == null) {
                    Slog.d("MiTrustService/quickAuthEventHandle", "mBindCallback is null");
                } else {
                    iMiRemoteAuthServiceCallback.onResult(cVar.toString());
                    this.f1699j = null;
                }
            } else {
                if (this.f1700k == null) {
                    Slog.d("MiTrustService/quickAuthEventHandle", "mAuthCallback is null");
                    return;
                }
                f1681J = false;
                if (i2 == 0) {
                    try {
                        cVar.q("remoteAuthResult", this.f1691G);
                    } catch (E1.b e3) {
                        Slog.d("MiTrustService/quickAuthEventHandle", "json error: " + e3);
                    }
                }
                this.f1700k.onResult(cVar.toString());
                this.f1700k = null;
            }
        }
    }

    public final void x(byte[] bArr) {
        Slog.d("MiTrustService/quickAuthEventHandle", "riskAuthSuccess");
        g.h(false);
        g.g();
        String str = f1682K;
        if (str != null) {
            y(str);
            Context context = P0.c.f234v.a;
            A0.o.y("shareAuthBind" + f1682K);
            B.b.i(new StringBuilder("biometric baseline updated for "), f1682K, "MiTrustService/quickAuthEventHandle");
            f1682K = null;
        }
        if (a.a().c()) {
            w(1792, 1);
            return;
        }
        SharedAuthResponse riskHATV2 = a.a().a.setRiskHATV2(bArr);
        Slog.d("MiTrustService/quickAuthEventHandle", "riskResponse is: " + riskHATV2.b);
        if (riskHATV2.b != 0) {
            w(1792, 1);
            return;
        }
        if (!this.f1703n) {
            w(0, 1);
            Slog.d("MiTrustService/quickAuthEventHandle", "riskAuth success");
        } else {
            Slog.d("MiTrustService/quickAuthEventHandle", "auth success, call UI to unlock");
            this.f1703n = false;
            this.f1702m.onResult(0);
        }
    }
}
