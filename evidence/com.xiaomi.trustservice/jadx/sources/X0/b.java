package X0;

import A0.o;
import P0.c;
import Y0.d;
import Y0.e;
import Y0.h;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.Slog;
import com.google.android.collect.Lists;
import com.xiaomi.trustservice.IMiDeviceStatusCallback;
import com.xiaomi.trustservice.IMiDeviceStatusService;
import com.xiaomi.trustservice.remoteservice.remoteService;
import java.util.Timer;

/* JADX INFO: loaded from: classes.dex */
public final class b extends IMiDeviceStatusService.Stub {
    public final /* synthetic */ remoteService a;

    public b(remoteService remoteservice) {
        this.a = remoteservice;
    }

    @Override // com.xiaomi.trustservice.IMiDeviceStatusService
    public final void getDeviceStatus(String str, IMiDeviceStatusCallback iMiDeviceStatusCallback, int i2, boolean z2) {
        Slog.d("MiTrustService/remoteService", "getDeviceStatus");
        remoteService.a("miui.permission.TRUST_SERVICE_INTERNAL");
        if (iMiDeviceStatusCallback == null) {
            return;
        }
        c cVar = this.a.a;
        if (cVar == null) {
            iMiDeviceStatusCallback.onResult(str, 4, "cant connect to remote device");
            return;
        }
        if (str == null) {
            iMiDeviceStatusCallback.onResult("", 4, "device ID is null");
            return;
        }
        h hVar = cVar.f245o;
        if (!hVar.b()) {
            try {
                iMiDeviceStatusCallback.onResult(str, 3, "mrm service not found");
                return;
            } catch (RemoteException unused) {
                Slog.d("MiTrustService/statusEventHandle", "callbcak function error");
                return;
            }
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        synchronized (hVar.b) {
            if (hVar.f481c.containsKey(str)) {
                ((d) hVar.f481c.get(str)).f463c.add(iMiDeviceStatusCallback);
                d dVar = (d) hVar.f481c.get(str);
                if (dVar.a < i2) {
                    dVar.a = i2;
                }
                if (z2) {
                    dVar.b = true;
                }
                return;
            }
            ArrayMap arrayMap = hVar.f481c;
            d dVar2 = new d();
            dVar2.a = 1;
            dVar2.b = false;
            dVar2.f463c = Lists.newArrayList();
            arrayMap.put(str, dVar2);
            ((d) hVar.f481c.get(str)).f463c.add(iMiDeviceStatusCallback);
            d dVar3 = (d) hVar.f481c.get(str);
            if (dVar3.a < i2) {
                dVar3.a = i2;
            }
            if (z2) {
                dVar3.b = true;
            }
            hVar.d.put(str, Long.valueOf(jCurrentTimeMillis));
            try {
                p001a1.a aVarT0 = o.t0(hVar.a.getChallengeByCert());
                if (aVarT0.a != 0) {
                    Slog.d("MiTrustService/statusEventHandle", "getChallengeByCert failed");
                    return;
                }
                E1.c cVar2 = new E1.c();
                try {
                    cVar2.p(16, "event_name");
                    cVar2.p(new String(aVarT0.b), "challenge");
                } catch (E1.b unused2) {
                    Slog.d("MiTrustService/statusEventHandle", "JSON error");
                }
                cVar.d(str, cVar2.toString());
                new Timer().schedule(new e(hVar, str, jCurrentTimeMillis, 2), 5000L);
            } catch (RemoteException unused3) {
                Slog.d("MiTrustService/statusEventHandle", "mrm service may crash");
                hVar.a = null;
            }
        }
    }
}
