package defpackage;

import com.xiaomi.ai.vision.sdk.callback.SpeechRecognizeCallback;
import com.xiaomi.ai.vision.sdk.model.RecognizeResult;
import java.lang.invoke.VarHandle;

/* JADX INFO: loaded from: classes.dex */
public final class qi implements SpeechRecognizeCallback {
    public ri3 a;
    public /* synthetic */ si b;

    @Override // com.xiaomi.ai.vision.sdk.callback.SpeechRecognizeCallback
    public final void onRecognizeResult(String str, RecognizeResult recognizeResult) {
        String str2;
        str.getClass();
        recognizeResult.getClass();
        ri3 ri3Var = this.a;
        String srcStr = recognizeResult.getSrcStr();
        int segId = recognizeResult.getSegId();
        srcStr.getClass();
        oi oiVar = new oi();
        oiVar.a = srcStr;
        oiVar.b = segId;
        VarHandle.storeStoreFence();
        ri3Var.getClass();
        oi oiVar2 = (oi) ri3Var.c;
        if (oiVar2 == null || oiVar2.b == segId) {
            ri3Var.c = oiVar;
            str2 = null;
        } else {
            ri3Var.c = oiVar;
            str2 = oiVar2.a;
        }
        if (str2 != null) {
            op0 op0Var = (op0) this.b.e;
            if (op0Var != null) {
                op0Var.z(str2);
            } else {
                az0.J("sentenceCallback");
                throw null;
            }
        }
    }

    @Override // com.xiaomi.ai.vision.sdk.callback.SpeechRecognizeCallback
    public final void onRecognizeStart(String str) {
        str.getClass();
        si siVar = this.b;
        siVar.a = str;
        uj ujVar = (uj) siVar.c;
        pi piVar = new pi();
        piVar.a = siVar;
        piVar.b = str;
        VarHandle.storeStoreFence();
        ujVar.a(piVar);
    }

    @Override // com.xiaomi.ai.vision.sdk.callback.SpeechRecognizeCallback
    public final void onRecognizeStop(String str) {
        str.getClass();
    }
}
