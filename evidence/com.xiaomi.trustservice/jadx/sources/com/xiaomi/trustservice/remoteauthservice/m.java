package com.xiaomi.trustservice.remoteauthservice;

import android.util.Slog;

/* JADX INFO: loaded from: classes.dex */
public final class m extends IMiRemoteAuthUnlockUI.Stub {
    public final /* synthetic */ remoteAuthService a;

    public m(remoteAuthService remoteauthservice) {
        this.a = remoteauthservice;
    }

    @Override // com.xiaomi.trustservice.remoteauthservice.IMiRemoteAuthUnlockUI
    public final void systemUIcallRemoteAuth(String str, int i2, int i3, IMiRemoteAuthUnlockUICallback iMiRemoteAuthUnlockUICallback) {
        Slog.d("MiTrustService/remoteAuthService", "systemUIcallRemoteAuth");
        if (iMiRemoteAuthUnlockUICallback == null) {
            Slog.d("MiTrustService/remoteAuthService", "IMiRemoteAuthUnlockUICallback is null");
            return;
        }
        try {
            P0.c cVar = this.a.a;
            cVar.f246p.E(i2, i3, cVar, iMiRemoteAuthUnlockUICallback);
        } catch (E1.b e2) {
            Slog.d("MiTrustService/remoteAuthService", "error:" + e2);
        }
    }
}
