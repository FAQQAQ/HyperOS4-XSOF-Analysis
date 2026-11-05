package com.xiaomi.trustservice.common;

import A0.o;
import E0.c;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.ArrayMap;
import android.util.Slog;
import com.xiaomi.continuity.ServiceName;
import com.xiaomi.continuity.channel.Channel;
import com.xiaomi.continuity.channel.ChannelListener;
import com.xiaomi.continuity.channel.ConfirmInfo;
import com.xiaomi.continuity.channel.Packet;
import com.xiaomi.continuity.channel.ServerChannelOptions;
import com.xiaomi.continuity.channel.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class CommService extends Service implements ChannelListener {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static int f1558f = -1;
    public f b;
    public final ArrayList a = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f1559c = new ArrayList();
    public final ChannelBinder d = new ChannelBinder(this);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayMap f1560e = new ArrayMap();

    public static class ChannelBinder extends Binder {
        private final CommService mService;

        public ChannelBinder(CommService commService) {
            this.mService = commService;
        }

        public CommService getService() {
            return this.mService;
        }
    }

    public final void a(int i2) {
        Slog.d("MiTrustService/CommService", "confirmChannel:" + i2 + ",0");
        this.b.a(i2);
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.d;
    }

    @Override // com.xiaomi.continuity.channel.ChannelListener
    public final void onChannelConfirm(String str, ServiceName serviceName, int i2, ConfirmInfo confirmInfo) {
        Slog.d("MiTrustService/CommService", "onChannelConfirm");
        synchronized (this.a) {
            try {
                f1558f = i2;
                Iterator it = this.a.iterator();
                while (it.hasNext()) {
                    ((ChannelListener) it.next()).onChannelConfirm(str, serviceName, i2, confirmInfo);
                    f1558f = -1;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.xiaomi.continuity.channel.ChannelListener
    public final void onChannelCreateFailed(String str, ServiceName serviceName, int i2, int i3) {
        synchronized (this.a) {
            try {
                Iterator it = this.a.iterator();
                while (it.hasNext()) {
                    ((ChannelListener) it.next()).onChannelCreateFailed(str, serviceName, i2, i3);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.xiaomi.continuity.channel.ChannelListener
    public final void onChannelCreateSuccess(Channel channel) {
        Slog.d("MiTrustService/CommService", "onChannelCreateSuccess");
        this.f1559c.add(channel);
        synchronized (this.a) {
            try {
                Iterator it = this.a.iterator();
                while (it.hasNext()) {
                    ((ChannelListener) it.next()).onChannelCreateSuccess(channel);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.xiaomi.continuity.channel.ChannelListener
    public final void onChannelReceive(Channel channel, Packet packet) {
        synchronized (this.a) {
            try {
                Iterator it = this.a.iterator();
                while (it.hasNext()) {
                    ((ChannelListener) it.next()).onChannelReceive(channel, packet);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.xiaomi.continuity.channel.ChannelListener
    public final void onChannelRelease(Channel channel, int i2) {
        this.f1559c.remove(channel);
        synchronized (this.a) {
            try {
                Iterator it = this.a.iterator();
                while (it.hasNext()) {
                    ((ChannelListener) it.next()).onChannelRelease(channel, i2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        Slog.d("MiTrustService/CommService", "onCreate");
        ServiceName serviceName = new ServiceName(getPackageName(), "mitrustservice");
        f fVarD = f.d(this);
        Objects.requireNonNull(fVarD);
        this.b = fVarD;
        ServerChannelOptions serverChannelOptions = new ServerChannelOptions(48);
        this.b.e(serviceName, serverChannelOptions, this, getMainExecutor());
        f fVar = this.b;
        c cVar = new c(this, serviceName, serverChannelOptions, 1);
        synchronized (fVar) {
            try {
                if (!fVar.f1476c.contains(cVar)) {
                    fVar.f1476c.add(cVar);
                }
                o.A("ContinuityChannel.Manager", "registerDeathCallback.size=" + fVar.f1476c.size(), new Object[0]);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.app.Service
    public final void onDestroy() {
        Slog.d("MiTrustService/CommService", "onDestroy");
        this.b.f(new ServiceName(getPackageName(), "mitrustservice"));
        super.onDestroy();
    }
}
