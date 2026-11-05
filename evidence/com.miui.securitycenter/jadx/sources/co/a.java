package co;

import android.os.IBinder;
import android.util.Log;
import com.xiaomi.security.xsof.IMiSafetyDetectCallback;
import oo.d;

/* JADX INFO: loaded from: classes4.dex */
public class a implements IBinder.DeathRecipient {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f8917a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final go.a f8918b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private IMiSafetyDetectCallback f8919c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private IBinder f8920d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f8921e;

    public a(int i10, go.a aVar, IMiSafetyDetectCallback iMiSafetyDetectCallback) {
        this.f8917a = i10;
        this.f8918b = aVar;
        this.f8919c = iMiSafetyDetectCallback;
        try {
            IBinder iBinderAsBinder = iMiSafetyDetectCallback.asBinder();
            this.f8920d = iBinderAsBinder;
            if (iBinderAsBinder != null) {
                iBinderAsBinder.linkToDeath(this, 0);
            }
        } catch (Exception unused) {
        }
        this.f8921e = false;
    }

    public go.a a() {
        return this.f8918b;
    }

    public boolean b() {
        return this.f8921e;
    }

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        Log.i("ClientApiRequest", "binderDied: " + this.f8917a);
        IBinder iBinder = this.f8920d;
        if (iBinder != null) {
            iBinder.unlinkToDeath(this, 0);
        }
        this.f8921e = true;
    }

    public void c(String str, int i10) {
        this.f8921e = true;
        if (this.f8919c == null) {
            return;
        }
        d.g(d.a(this.f8917a), this.f8918b.d(), d.b(this.f8917a, str, i10));
        try {
            IBinder iBinder = this.f8920d;
            if (iBinder != null) {
                iBinder.unlinkToDeath(this, 0);
            }
            this.f8919c.H2(str, i10);
            this.f8919c = null;
        } catch (Exception unused) {
        }
    }

    public String toString() {
        return "[" + this.f8917a + "] ";
    }
}
