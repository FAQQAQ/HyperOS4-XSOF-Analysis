package com.xiaomi.trustservice.rootpub;

import A0.o;
import E1.c;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Slog;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.ProtocolException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import vendor.xiaomi.hardware.mrm.IMiCertificate;

/* JADX INFO: loaded from: classes.dex */
public class PubCertRevise extends Service {
    public volatile IMiCertificate a = null;

    public final void a() throws ProtocolException {
        IMiCertificate iMiCertificate;
        Slog.d("MiTrustService/PubCertRevise", "Begin revise pub cert");
        if (c() && (iMiCertificate = this.a) != null) {
            try {
                String[] strArrSplit = new String(o.t0(iMiCertificate.certificatePrepare()).b).split("\\$");
                if (strArrSplit.length != 2) {
                    return;
                }
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL("https://find.api.micloud.xiaomi.net/mic/find/v4/anonymous/device/externalkey?keyType=cert&fid=" + strArrSplit[0] + "&clientData=" + strArrSplit[1]).openConnection();
                Slog.d("MiTrustService/PubCertRevise", "openConnection");
                httpsURLConnection.setReadTimeout(5000);
                httpsURLConnection.setConnectTimeout(5000);
                httpsURLConnection.setRequestMethod("GET");
                try {
                    int responseCode = httpsURLConnection.getResponseCode();
                    if (responseCode != 200) {
                        Slog.e("MiTrustService/PubCertRevise", "HTTP error: " + responseCode);
                        return;
                    }
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
                    StringBuffer stringBuffer = new StringBuffer();
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        } else {
                            stringBuffer.append(line);
                        }
                    }
                    String string = stringBuffer.toString();
                    Slog.d("MiTrustService/PubCertRevise", "Response: " + string);
                    c cVar = new c(string);
                    if (!"error".equals(cVar.m("result", ""))) {
                        byte[] bytes = cVar.g("data").h("encryptedPrivateKey").getBytes();
                        Slog.d("MiTrustService/PubCertRevise", String.valueOf(bytes.length));
                        try {
                            iMiCertificate.certificateLoad(bytes);
                        } catch (RemoteException unused) {
                            Slog.d("MiTrustService/PubCertRevise", "mrm service may crash");
                            this.a = null;
                        }
                        return;
                    }
                    Slog.e("MiTrustService/PubCertRevise", "Server error: code=" + cVar.l() + ", reason=" + cVar.m("reason", "") + ", description=" + cVar.m("description", "") + ", retriable=" + cVar.k());
                } finally {
                    httpsURLConnection.disconnect();
                }
            } catch (RemoteException unused2) {
                Slog.d("MiTrustService/PubCertRevise", "mrm service may crash");
                this.a = null;
            }
        }
    }

    public final void b() throws ProtocolException {
        IMiCertificate iMiCertificate;
        Slog.d("MiTrustService/PubCertRevise", "Begin revise rotate key");
        if (c() && (iMiCertificate = this.a) != null) {
            try {
                String[] strArrSplit = new String(o.t0(iMiCertificate.rotateKeyPrepare()).b).split("\\$");
                if (strArrSplit.length != 2) {
                    return;
                }
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL("https://find.api.micloud.xiaomi.net/mic/find/v4/anonymous/device/externalkey?keyType=misec&fid=" + strArrSplit[0] + "&clientData=" + strArrSplit[1]).openConnection();
                Slog.d("MiTrustService/PubCertRevise", "openConnection");
                httpsURLConnection.setReadTimeout(5000);
                httpsURLConnection.setConnectTimeout(5000);
                httpsURLConnection.setRequestMethod("GET");
                try {
                    int responseCode = httpsURLConnection.getResponseCode();
                    if (responseCode != 200) {
                        Slog.e("MiTrustService/PubCertRevise", "HTTP error: " + responseCode);
                        httpsURLConnection.disconnect();
                        return;
                    }
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
                    StringBuffer stringBuffer = new StringBuffer();
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        } else {
                            stringBuffer.append(line);
                        }
                    }
                    String string = stringBuffer.toString();
                    Slog.d("MiTrustService/PubCertRevise", "Response: " + string);
                    c cVar = new c(string);
                    if ("error".equals(cVar.m("result", ""))) {
                        Slog.e("MiTrustService/PubCertRevise", "Server error: code=" + cVar.l() + ", reason=" + cVar.m("reason", "") + ", description=" + cVar.m("description", "") + ", retriable=" + cVar.k());
                        httpsURLConnection.disconnect();
                        return;
                    }
                    byte[] bytes = (cVar.g("data").h("encryptedPrivateKey") + cVar.g("data").h("sign")).getBytes();
                    Slog.d("MiTrustService/PubCertRevise", String.valueOf(bytes.length));
                    try {
                        iMiCertificate.rotateKeyLoad(bytes);
                    } catch (RemoteException unused) {
                        Slog.d("MiTrustService/PubCertRevise", "mrm service may crash");
                        this.a = null;
                    }
                    httpsURLConnection.disconnect();
                } catch (Throwable th) {
                    httpsURLConnection.disconnect();
                    throw th;
                }
            } catch (RemoteException unused2) {
                Slog.d("MiTrustService/PubCertRevise", "mrm service may crash");
                this.a = null;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:15:0x004b A[Catch: all -> 0x0033, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0007, B:7:0x000e, B:12:0x0036, B:13:0x0047, B:15:0x004b), top: B:24:0x0003, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:19:0x0055 A[DONT_GENERATE] */
    /* JADX WARN: Instruction removed from duplicated block: B:19:0x0055, please report this as an issue */
    public final synchronized boolean c() {
        if (this.a != null) {
            if (this.a == null) {
                return true;
            }
            Slog.d("MiTrustService/PubCertRevise", "failed to get mrmd service");
            return false;
        }
        Slog.d("MiTrustService/PubCertRevise", "try init mrmd Service");
        try {
            Class<?> cls = Class.forName("android.os.ServiceManager");
            this.a = IMiCertificate.Stub.asInterface((IBinder) cls.getDeclaredMethod("waitForService", String.class).invoke(cls, "vendor.xiaomi.hardware.mrm.IMiCertificate/default"));
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            Slog.e("MiTrustService/PubCertRevise", "initService error" + e2);
        }
        if (this.a == null) {
            return true;
        }
        Slog.d("MiTrustService/PubCertRevise", "failed to get mrmd service");
        return false;
        throw th;
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public final void onCreate() {
        Slog.v("MiTrustService/PubCertRevise", "OnCreate");
        super.onCreate();
    }

    @Override // android.app.Service
    public final void onDestroy() {
        Slog.d("MiTrustService/PubCertRevise", "Task PubCertRevise destroy");
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i2, int i3) {
        Slog.d("MiTrustService/PubCertRevise", "Task PubCertRevise start");
        new Thread(new G.a(this, intent.getAction())).start();
        stopSelf(i3);
        return 2;
    }
}
