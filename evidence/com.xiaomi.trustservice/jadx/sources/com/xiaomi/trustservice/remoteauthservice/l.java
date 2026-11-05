package com.xiaomi.trustservice.remoteauthservice;

import android.os.Binder;
import android.util.Slog;
import com.xiaomi.trustservice.remoteservice.locksettings.LockScreenUIActivity;

/* JADX INFO: loaded from: classes.dex */
public final class l extends IMiRemoteAuthService.Stub {
    public final /* synthetic */ remoteAuthService a;

    public l(remoteAuthService remoteauthservice) {
        this.a = remoteauthservice;
    }

    @Override // com.xiaomi.trustservice.remoteauthservice.IMiRemoteAuthService
    public final void bindRemoteDevice(String str, IMiRemoteAuthServiceCallback iMiRemoteAuthServiceCallback) {
        Slog.d("MiTrustService/remoteAuthService", "bindRemoteDevice");
        remoteAuthService.a();
        if (iMiRemoteAuthServiceCallback == null) {
            Slog.d("MiTrustService/remoteAuthService", "callback is null");
            return;
        }
        synchronized (this.a.f1734e) {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (Math.abs(jCurrentTimeMillis - this.a.f1733c) < 200) {
                    Slog.d("MiTrustService/remoteAuthService", "Invoke callBindRemoteDevice too frequently");
                    return;
                }
                this.a.f1733c = jCurrentTimeMillis;
                try {
                    E1.c cVar = new E1.c(str);
                    String strH = cVar.h("deviceId");
                    String strO = A0.o.O(this.a, Binder.getCallingPid());
                    if (cVar.a.containsKey("mainTitle") && cVar.a.containsKey("sideTitle")) {
                        LockScreenUIActivity.f1749F = true;
                        LockScreenUIActivity.f1751H = cVar.h("mainTitle");
                        LockScreenUIActivity.f1752I = cVar.h("sideTitle");
                    }
                    boolean zD = cVar.d("unlockUI");
                    P0.c cVar2 = this.a.a;
                    cVar2.f246p.C(strO, strH, zD, cVar2, iMiRemoteAuthServiceCallback);
                } catch (E1.b e2) {
                    Slog.d("MiTrustService/remoteAuthService", "prase JSONdata error:" + e2);
                } catch (InterruptedException e3) {
                    Slog.d("MiTrustService/remoteAuthService", "InterruptedException error:" + e3);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.xiaomi.trustservice.remoteauthservice.IMiRemoteAuthService
    public final void callQuickAuth(String str, IMiRemoteAuthServiceCallback iMiRemoteAuthServiceCallback) {
        Slog.d("MiTrustService/remoteAuthService", "callQuickAuth");
        remoteAuthService.a();
        if (iMiRemoteAuthServiceCallback == null) {
            Slog.d("MiTrustService/remoteAuthService", "callback is null");
            return;
        }
        synchronized (this.a.f1734e) {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                remoteAuthService remoteauthservice = this.a;
                if (jCurrentTimeMillis - remoteauthservice.b < 200 && k.f1681J) {
                    Slog.d("MiTrustService/remoteAuthService", "Invoke callQuickAuth too frequently");
                    return;
                }
                if (!k.f1681J) {
                    k.f1681J = true;
                }
                remoteauthservice.b = jCurrentTimeMillis;
                try {
                    E1.c cVar = new E1.c(str);
                    String strH = cVar.h("deviceId");
                    int iE = cVar.e("authType");
                    int iE2 = cVar.e("minStrength");
                    boolean zD = cVar.d("unlockUI");
                    this.a.a.f246p.f1715z = null;
                    boolean zD2 = false;
                    if (cVar.a.containsKey("additionalData")) {
                        E1.c cVar2 = new E1.c(cVar.h("additionalData"));
                        zD2 = cVar2.a.containsKey("externalCall") ? cVar2.d("externalCall") : false;
                        if (cVar2.a.containsKey("remoteSideTitle")) {
                            this.a.a.f246p.f1715z = cVar2.h("remoteSideTitle");
                        }
                        if (cVar2.a.containsKey("cancelIsTimeout")) {
                            this.a.a.f246p.f1686A = cVar2.d("cancelIsTimeout");
                        }
                    }
                    A0.o.O(this.a, Binder.getCallingPid());
                    P0.c cVar3 = this.a.a;
                    cVar3.f246p.F(strH, iE, iE2, zD, zD2, cVar3, iMiRemoteAuthServiceCallback);
                } catch (E1.b e2) {
                    Slog.d("MiTrustService/remoteAuthService", "prase JSONdata error:" + e2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.xiaomi.trustservice.remoteauthservice.IMiRemoteAuthService
    public final void callRemoteAuth(String str, IMiRemoteAuthServiceCallback iMiRemoteAuthServiceCallback) {
        Slog.d("MiTrustService/remoteAuthService", "callRemoteAuth");
        remoteAuthService.a();
        if (iMiRemoteAuthServiceCallback == null) {
            Slog.d("MiTrustService/remoteAuthService", "callbcak is null");
            return;
        }
        remoteAuthService remoteauthservice = this.a;
        if (remoteauthservice.a == null) {
            Slog.d("MiTrustService/remoteAuthService", "commHandle is null");
            return;
        }
        synchronized (remoteauthservice.f1734e) {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                remoteAuthService remoteauthservice2 = this.a;
                if (jCurrentTimeMillis - remoteauthservice2.b < 1000) {
                    Slog.d("MiTrustService/remoteAuthService", "Invoke callRemoteAuth too frequently");
                    return;
                }
                remoteauthservice2.b = jCurrentTimeMillis;
                try {
                    E1.c cVar = new E1.c(str);
                    int iE = cVar.e("version");
                    String nameForUid = this.a.getPackageManager().getNameForUid(Binder.getCallingUid());
                    boolean z2 = true;
                    if (iE == 1) {
                        String strH = cVar.h("deviceId");
                        cVar.e("type");
                        int iE2 = cVar.e("mode");
                        P0.c cVar2 = this.a.a;
                        cVar2.f243m.m(nameForUid, strH, cVar2, iMiRemoteAuthServiceCallback.getRemoteAuthCallback(), iE2);
                    } else if (iE == 2) {
                        String strH2 = cVar.h("deviceId");
                        int iE3 = cVar.e("mode");
                        P0.c cVar3 = this.a.a;
                        o oVar = cVar3.f248r;
                        if (iE3 != 1) {
                            z2 = false;
                        }
                        oVar.v(cVar3, iMiRemoteAuthServiceCallback, strH2, z2);
                    }
                } catch (E1.b e2) {
                    Slog.d("MiTrustService/remoteAuthService", "prase JSONdata error:" + e2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.xiaomi.trustservice.remoteauthservice.IMiRemoteAuthService
    public final void cancel(String str) {
        Slog.d("MiTrustService/remoteAuthService", "cancel");
        remoteAuthService.a();
        try {
            E1.c cVar = new E1.c(str);
            String strH = cVar.h("cancelType");
            String strH2 = cVar.h("deviceId");
            if (strH.equals("cancelRemoteAuth") || strH.equals("cancelBindRemote") || strH.equals("cancelRiskAuth")) {
                P0.c cVar2 = this.a.a;
                cVar2.f246p.g(cVar2, strH2, strH);
            } else if (strH.equals("cancelRemotePassword")) {
                if (this.a.a.f248r.f1731r) {
                    P0.c cVar3 = this.a.a;
                    cVar3.f248r.b(cVar3, strH2);
                } else {
                    P0.c cVar4 = this.a.a;
                    cVar4.f243m.a(cVar4, strH2);
                }
            }
        } catch (E1.b e2) {
            Slog.d("MiTrustService/remoteAuthService", "error:" + e2);
        }
    }

    @Override // com.xiaomi.trustservice.remoteauthservice.IMiRemoteAuthService
    public final void checkPassword(String str, IMiRemoteAuthServiceCallback iMiRemoteAuthServiceCallback) {
        Slog.d("MiTrustService/remoteAuthService", "checkPassword");
        remoteAuthService.a();
        if (this.a.a == null) {
            Slog.d("MiTrustService/remoteAuthService", "commHandle is null");
            return;
        }
        try {
            E1.c cVar = new E1.c(str);
            String strH = cVar.h("checkPasswordType");
            if (strH.equals("remoteAuthRisk")) {
                if (cVar.a.containsKey("mainTitle") && cVar.a.containsKey("sideTitle")) {
                    LockScreenUIActivity.f1749F = true;
                    LockScreenUIActivity.f1751H = cVar.h("mainTitle");
                    LockScreenUIActivity.f1752I = cVar.h("sideTitle");
                }
                P0.c cVar2 = this.a.a;
                cVar2.f246p.D(iMiRemoteAuthServiceCallback, cVar2, cVar.d("unlockUI"));
                return;
            }
            if (strH.equals("remotePassword")) {
                String strH2 = cVar.h("password");
                String nameForUid = this.a.getPackageManager().getNameForUid(Binder.getCallingUid());
                if (this.a.a.f248r.f1731r) {
                    Slog.d("MiTrustService/remoteAuthService", "remotePassword is V2");
                    this.a.a.f248r.c(this.a.a, strH2.getBytes());
                    return;
                }
                Slog.d("MiTrustService/remoteAuthService", "remotePassword is V1");
                this.a.a.f243m.b(this.a.a, nameForUid, strH2.getBytes());
            }
        } catch (E1.b e2) {
            Slog.d("MiTrustService/remoteAuthService", "error:" + e2);
        }
    }

    @Override // com.xiaomi.trustservice.remoteauthservice.IMiRemoteAuthService
    public final void clearBindStatus(String str, IMiRemoteAuthServiceCallback iMiRemoteAuthServiceCallback) {
        Slog.d("MiTrustService/remoteAuthService", "clearBindStatus");
    }

    @Override // com.xiaomi.trustservice.remoteauthservice.IMiRemoteAuthService
    public final void getRemoteAuthStatus(String str, IMiRemoteAuthServiceCallback iMiRemoteAuthServiceCallback) {
        Slog.d("MiTrustService/remoteAuthService", "getQuickAuthStatus");
        remoteAuthService.a();
        if (iMiRemoteAuthServiceCallback == null) {
            Slog.d("MiTrustService/remoteAuthService", "callback is null");
            return;
        }
        synchronized (this.a.f1734e) {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                remoteAuthService remoteauthservice = this.a;
                if (jCurrentTimeMillis - remoteauthservice.d < 200 && g.f1672l) {
                    Slog.d("MiTrustService/remoteAuthService", "Invoke callgetRemoteAuthStatus too frequently");
                    return;
                }
                if (!g.f1672l) {
                    g.f1672l = true;
                }
                remoteauthservice.d = jCurrentTimeMillis;
                try {
                    E1.c cVar = new E1.c(str);
                    String strH = cVar.h("deviceId");
                    A0.o.O(this.a, Binder.getCallingPid());
                    boolean zContainsKey = cVar.a.containsKey("quickMode");
                    P0.c cVar2 = this.a.a;
                    cVar2.f247q.b(cVar2, iMiRemoteAuthServiceCallback, strH, zContainsKey);
                } catch (E1.b e2) {
                    Slog.d("MiTrustService/remoteAuthService", "prase JSONdata error:" + e2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.xiaomi.trustservice.remoteauthservice.IMiRemoteAuthService
    public final void getVersion(String str, IMiRemoteAuthServiceCallback iMiRemoteAuthServiceCallback) {
        Slog.d("MiTrustService/remoteAuthService", "getVersion");
    }

    @Override // com.xiaomi.trustservice.remoteauthservice.IMiRemoteAuthService
    public final void registerUICallback(String str, IMiRemoteAuthServiceUICallback iMiRemoteAuthServiceUICallback) {
        Slog.d("MiTrustService/remoteAuthService", "registerUICallback" + str);
        remoteAuthService remoteauthservice = this.a;
        if (remoteauthservice.a == null) {
            Slog.d("MiTrustService/remoteAuthService", "commHandle is null");
            return;
        }
        try {
            E1.c cVar = new E1.c(str);
            cVar.e("version");
            if (cVar.a.containsKey("UIType") && cVar.h("UIType").equals("quickAuth")) {
                k kVar = remoteauthservice.a.f246p;
                kVar.getClass();
                Slog.d("MiTrustService/quickAuthEventHandle", "registerUICallback");
                if (iMiRemoteAuthServiceUICallback == null) {
                    Slog.d("MiTrustService/quickAuthEventHandle", "callback is null");
                    return;
                } else {
                    kVar.f1701l = iMiRemoteAuthServiceUICallback;
                    return;
                }
            }
            String nameForUid = remoteauthservice.getPackageManager().getNameForUid(Binder.getCallingUid());
            remoteauthservice.a.f243m.f460n.put(nameForUid, iMiRemoteAuthServiceUICallback.getRemoteAuthUICallback());
            o oVar = remoteauthservice.a.f248r;
            oVar.getClass();
            Slog.d("MiTrustService/remotePassword", "registerUICallback V2:" + nameForUid);
            oVar.f1725l = iMiRemoteAuthServiceUICallback;
        } catch (E1.b e2) {
            Slog.d("MiTrustService/remoteAuthService", "prase JSONdata error:" + e2);
        }
    }
}
