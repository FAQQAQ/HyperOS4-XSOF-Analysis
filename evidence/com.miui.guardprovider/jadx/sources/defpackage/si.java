package defpackage;

import android.content.res.AssetManager;
import android.util.Log;
import com.xiaomi.ai.vision.sdk.AiCapability;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.invoke.VarHandle;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public final class si {
    public String a;
    public boolean b;
    public Object c;
    public Object d;
    public Object e;
    public Object f;
    public Object g;

    public FileInputStream a(AssetManager assetManager, String str) {
        try {
            return assetManager.openFd(str).createInputStream();
        } catch (FileNotFoundException e) {
            String message = e.getMessage();
            if (message == null || !message.contains("compressed")) {
                return null;
            }
            ((n22) this.d).h();
            return null;
        }
    }

    public void b() {
        Object objM;
        try {
            ((uj) this.c).stop();
            objM = c13.a;
        } catch (Throwable th) {
            objM = kk3.m(th);
        }
        Throwable thA = qd2.a(objM);
        if (thA != null) {
            Log.w("Asr", "Release audio source failed", thA);
        }
    }

    public void c(int i, IOException iOException) {
        Executor executor = (Executor) this.c;
        sz szVar = new sz(1);
        szVar.d = this;
        szVar.c = i;
        szVar.e = iOException;
        VarHandle.storeStoreFence();
        executor.execute(szVar);
    }

    public void d(op0 op0Var, Consumer consumer) {
        if (this.b) {
            return;
        }
        this.e = op0Var;
        this.f = consumer;
        try {
            AiCapability aiCapability = (AiCapability) this.d;
            qi qiVar = (qi) this.g;
            ri riVar = new ri();
            riVar.a = this;
            VarHandle.storeStoreFence();
            AiCapability.startRecognize$default(aiCapability, 4, 0, false, "zh-CN", "zh-CN", false, false, qiVar, null, riVar, 256, null);
        } catch (Throwable th) {
            kk3.m(th);
        }
        this.b = true;
    }

    public void e() {
        Object objM;
        if (!this.b) {
            b();
            return;
        }
        try {
            try {
                String str = this.a;
                if (str != null) {
                    ((AiCapability) this.d).stopRecognize(str);
                    objM = c13.a;
                } else {
                    objM = null;
                }
            } catch (Throwable th) {
                objM = kk3.m(th);
            }
            Throwable thA = qd2.a(objM);
            if (thA != null) {
                Log.w("Asr", "stopRecognize failed", thA);
            }
        } finally {
            b();
            this.a = null;
            this.b = false;
        }
    }
}
