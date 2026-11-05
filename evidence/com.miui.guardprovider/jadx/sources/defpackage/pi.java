package defpackage;

import android.util.Log;
import com.xiaomi.ai.vision.sdk.AiCapability;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class pi implements Consumer {
    public /* synthetic */ si a;
    public /* synthetic */ String b;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        Object objM;
        si siVar = this.a;
        String str = this.b;
        ByteBuffer byteBuffer = (ByteBuffer) obj;
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr);
        Consumer consumer = (Consumer) siVar.f;
        if (consumer != null) {
            consumer.accept(bArr);
        }
        try {
            objM = Boolean.valueOf(((AiCapability) siVar.d).postData(bArr, false, str));
        } catch (Throwable th) {
            objM = kk3.m(th);
        }
        Throwable thA = qd2.a(objM);
        if (thA != null) {
            Log.w("Asr", "Asr service might be dead", thA);
        }
    }
}
