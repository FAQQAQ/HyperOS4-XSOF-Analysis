package com.xiaomi.trustservice.ota;

import E1.b;
import E1.c;
import W0.a;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.util.Slog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.HttpsURLConnection;

/* JADX INFO: loaded from: classes.dex */
public class SocidProvision extends Service {
    public static void a() {
        try {
            String str = SystemProperties.get("ro.boot.socid", "");
            if (str.equals("")) {
                return;
            }
            String strI = a.a().i();
            Slog.d("MiTrustService/SocidProvision", "check local socid status");
            String str2 = "https://deviceapi.micloud.xiaomi.net/mic/binding/v1/anonymous/device/security/socid?&cloudsp_fid=" + strI + "&cloudsp_socid=" + str;
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] bArrDigest = messageDigest.digest(("POST&/mic/binding/v1/anonymous/device/security/socid&cloudsp_fid=" + strI + "&cloudsp_socid=" + str).getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDigest) {
                sb.append(String.format("%02x", Byte.valueOf(b)));
            }
            String strG = a.a().g(sb.toString());
            if (strG.length() == 128) {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(str2 + "&cloudp_sign=" + ("30440220" + strG.substring(0, 64) + "0220" + strG.substring(64))).openConnection();
                Slog.d("MiTrustService/SocidProvision", "openConnection");
                httpsURLConnection.setReadTimeout(5000);
                httpsURLConnection.setRequestMethod("POST");
                int responseCode = httpsURLConnection.getResponseCode();
                if (responseCode != 200) {
                    Slog.e("MiTrustService/SocidProvision", "HTTP error: " + responseCode);
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
                c cVar = new c(stringBuffer.toString());
                String strH = cVar.h("code");
                String strH2 = cVar.h("result");
                String strH3 = cVar.h("description");
                if (strH2.equals("ok")) {
                    Slog.d("MiTrustService/SocidProvision", "save socid status");
                    byte[] bArrDigest2 = messageDigest.digest(("OtaSocidSuccess_" + strI + "_" + str).getBytes());
                    StringBuilder sb2 = new StringBuilder();
                    for (byte b2 : bArrDigest2) {
                        sb2.append(String.format("%02x", Byte.valueOf(b2)));
                    }
                    String string = sb2.toString();
                    if (a.a().m(string + "_" + a.a().g(string)) != 0) {
                        Slog.d("MiTrustService/SocidProvision", "save socid status failed");
                    }
                } else {
                    Slog.d("MiTrustService/SocidProvision", "Server error : " + strH + ", Description : " + strH3);
                }
            } else {
                Slog.d("MiTrustService/SocidProvision", "Sign Length Invalid");
            }
            Slog.d("MiTrustService/SocidProvision", "check done");
        } catch (b | RemoteException | IOException | NoSuchAlgorithmException unused) {
            Slog.d("MiTrustService/SocidProvision", "error");
        }
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public final void onCreate() {
        Slog.v("MiTrustService/SocidProvision", "OnCreate");
        super.onCreate();
    }

    @Override // android.app.Service
    public final void onDestroy() {
        Slog.d("MiTrustService/SocidProvision", "Task SocidProvision destroy");
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i2, int i3) {
        Slog.d("MiTrustService/SocidProvision", "Task SocidProvision start");
        new Thread(new M.b(this, intent.getAction())).start();
        stopSelf(i3);
        return 2;
    }
}
