package com.xiaomi.ai.vision.sdk;

import android.content.Context;
import android.util.Log;
import com.xiaomi.ai.vision.sdk.callback.CoreCallback;
import com.xiaomi.ai.vision.sdk.callback.CtaCallback;
import com.xiaomi.ai.vision.sdk.callback.DownloadModelCallback;
import com.xiaomi.ai.vision.sdk.callback.ErrorCallback;
import com.xiaomi.ai.vision.sdk.callback.SpeechRecognizeCallback;
import com.xiaomi.ai.vision.sdk.callback.TextTranslateCallback;
import com.xiaomi.ai.vision.sdk.callback.TtsCallback;
import com.xiaomi.ai.vision.sdk.utils.AiCapabilityUtils;
import com.xiaomi.ai.vision.sdk.utils.UiThreadProxy;
import com.xiaomi.onetrack.util.a;
import defpackage.c60;
import defpackage.g3;
import defpackage.h4;
import defpackage.op0;
import defpackage.pp0;
import defpackage.q6;
import defpackage.r6;
import defpackage.s6;
import defpackage.t6;
import defpackage.u6;
import defpackage.v6;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import org.json.JSONException;
import tmsdk.common.roach.nest.PowerNest;
import tmsdk.common.tcc.SmsCheckerContentTypes;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0004\u0018\u0000 `2\u00020\u0001:\u0001`BK\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u001c\b\u0002\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004\u0012\u001c\b\u0002\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004¢\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00020\u0007¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u0007¢\u0006\u0004\b\u000e\u0010\rJ!\u0010\u0012\u001a\u00020\u00072\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00070\u000f¢\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0016\u0010\u0017J?\u0010\u001f\u001a\u00020\u00072\b\b\u0002\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00062\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001c\u001a\u00020\u001b2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001d¢\u0006\u0004\b\u001f\u0010 Js\u0010'\u001a\u00020\u00072\b\b\u0002\u0010\u0018\u001a\u00020\u00052\b\b\u0002\u0010!\u001a\u00020\u00052\b\b\u0002\u0010\"\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00062\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010#\u001a\u00020\u00102\b\b\u0002\u0010$\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u001b2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001d¢\u0006\u0004\b'\u0010(J}\u0010*\u001a\u00020\u00072\b\b\u0002\u0010\u0018\u001a\u00020\u00052\b\b\u0002\u0010!\u001a\u00020\u00052\b\b\u0001\u0010)\u001a\u00020\u00052\b\b\u0002\u0010\"\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00062\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010#\u001a\u00020\u00102\b\b\u0002\u0010$\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u001b2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001d¢\u0006\u0004\b*\u0010+J'\u00100\u001a\u00020\u00102\u0006\u0010-\u001a\u00020,2\b\b\u0002\u0010.\u001a\u00020\u00102\u0006\u0010/\u001a\u00020\u0006¢\u0006\u0004\b0\u00101J\u0015\u00102\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u0006¢\u0006\u0004\b2\u00103J1\u00105\u001a\u00020\u00072\u0006\u00104\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010&\u001a\u00020%2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001d¢\u0006\u0004\b5\u00106J9\u00109\u001a\u00020\u00072\u0006\u00104\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u00108\u001a\u0002072\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001d¢\u0006\u0004\b9\u0010:J\u0017\u0010<\u001a\u00020\u00072\u0006\u0010;\u001a\u00020\u0010H\u0016¢\u0006\u0004\b<\u0010=J\u0017\u0010?\u001a\u00020\u00072\u0006\u0010>\u001a\u00020\u0006H\u0016¢\u0006\u0004\b?\u00103J\u001f\u0010\b\u001a\u00020\u00072\u0006\u0010@\u001a\u00020\u00052\u0006\u0010A\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\b\u0010BJ\u001f\u0010\t\u001a\u00020\u00072\u0006\u0010@\u001a\u00020\u00052\u0006\u0010A\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010BJ\u0017\u0010E\u001a\u00020\u00072\u0006\u0010D\u001a\u00020CH\u0002¢\u0006\u0004\bE\u0010FJ\u000f\u0010G\u001a\u00020\u0007H\u0002¢\u0006\u0004\bG\u0010\rJ#\u0010I\u001a\u00020\u00072\u0012\u0010H\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00070\u000fH\u0002¢\u0006\u0004\bI\u0010\u0013J#\u0010K\u001a\u00020\u00072\u0012\u0010J\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00070\u000fH\u0002¢\u0006\u0004\bK\u0010\u0013J+\u0010O\u001a\u00020\u00072\b\b\u0002\u0010L\u001a\u00020\u00102\u0006\u0010N\u001a\u00020M2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u0002¢\u0006\u0004\bO\u0010PJ\u000f\u0010Q\u001a\u00020\u0007H\u0002¢\u0006\u0004\bQ\u0010\rJ!\u0010T\u001a\u00028\u0000\"\b\b\u0000\u0010S*\u00020R2\u0006\u0010\u0015\u001a\u00028\u0000H\u0002¢\u0006\u0004\bT\u0010UR\u0014\u0010W\u001a\u00020V8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bW\u0010XR(\u0010Y\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bY\u0010ZR(\u0010\u001e\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010ZR\u0016\u0010[\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b[\u0010\\R\u001a\u0010^\u001a\b\u0012\u0004\u0012\u00020C0]8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b^\u0010_¨\u0006a"}, d2 = {"Lcom/xiaomi/ai/vision/sdk/AiCapability;", "Lcom/xiaomi/ai/vision/sdk/callback/CoreCallback;", "Landroid/content/Context;", "context", "Lkotlin/Function2;", a.c, a.c, "Lc13;", "onEvent", "onError", "<init>", "(Landroid/content/Context;Lpp0;Lpp0;)V", "start", "()V", "destroy", "Lkotlin/Function1;", a.c, "onAllowed", "checkIfCtaAllowed", "(Lop0;)V", "Lcom/xiaomi/ai/vision/sdk/callback/CtaCallback;", "callback", "requestCtaAccredit", "(Lcom/xiaomi/ai/vision/sdk/callback/CtaCallback;)V", "scenarioType", "srcLang", "destLang", "Lcom/xiaomi/ai/vision/sdk/callback/SpeechRecognizeCallback;", "recognizeCallback", "Lcom/xiaomi/ai/vision/sdk/callback/ErrorCallback;", "errorCallback", "startOfflineRecognize", "(ILjava/lang/String;Ljava/lang/String;Lcom/xiaomi/ai/vision/sdk/callback/SpeechRecognizeCallback;Lcom/xiaomi/ai/vision/sdk/callback/ErrorCallback;)V", "recognizeMode", "interrupt", "identifyLanguage", "vad", "Lcom/xiaomi/ai/vision/sdk/callback/TtsCallback;", "ttsCallback", "startRecognize", "(IIZLjava/lang/String;Ljava/lang/String;ZZLcom/xiaomi/ai/vision/sdk/callback/SpeechRecognizeCallback;Lcom/xiaomi/ai/vision/sdk/callback/TtsCallback;Lcom/xiaomi/ai/vision/sdk/callback/ErrorCallback;)V", "channel", "startMultichannelRecognize", "(IIIZLjava/lang/String;Ljava/lang/String;ZZLcom/xiaomi/ai/vision/sdk/callback/SpeechRecognizeCallback;Lcom/xiaomi/ai/vision/sdk/callback/TtsCallback;Lcom/xiaomi/ai/vision/sdk/callback/ErrorCallback;)V", a.c, "pcm", "isLastFrame", "dialogId", "postData", "([BZLjava/lang/String;)Z", "stopRecognize", "(Ljava/lang/String;)V", "text", "startTts", "(Ljava/lang/String;Ljava/lang/String;Lcom/xiaomi/ai/vision/sdk/callback/TtsCallback;Lcom/xiaomi/ai/vision/sdk/callback/ErrorCallback;)V", "Lcom/xiaomi/ai/vision/sdk/callback/TextTranslateCallback;", "textTranslateCallback", "startTextTranslate", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/xiaomi/ai/vision/sdk/callback/TextTranslateCallback;Lcom/xiaomi/ai/vision/sdk/callback/ErrorCallback;)V", "available", "onAvailableChanged", "(Z)V", com.xiaomi.onetrack.h.b.a.g, "onInterrupt", com.xiaomi.onetrack.g.a.d, com.xiaomi.onetrack.g.a.c, "(ILjava/lang/String;)V", "Ljava/lang/Runnable;", "task", "doTask", "(Ljava/lang/Runnable;)V", "doCacheTask", "onSupport", "isSupportOfflineRecognize", "onExist", "checkModelExist", "silentUpdate", "Lcom/xiaomi/ai/vision/sdk/callback/DownloadModelCallback;", "downloadModelCallback", "startDownloadModel", "(ZLcom/xiaomi/ai/vision/sdk/callback/DownloadModelCallback;Lcom/xiaomi/ai/vision/sdk/callback/ErrorCallback;)V", "cancelDownloadModel", a.c, "T", "getUiThreadCallback", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lcom/xiaomi/ai/vision/sdk/AiCore;", "core", "Lcom/xiaomi/ai/vision/sdk/AiCore;", "eventCallback", "Lpp0;", "mAvailable", "Z", a.c, "mCacheTaskList", "Ljava/util/List;", "Companion", "AiCapability_release"}, k = 1, mv = {1, 7, 1}, xi = SmsCheckerContentTypes.TYPE_TEL_NET_TEL)
public final class AiCapability implements CoreCallback {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String TAG = "AiCapability";
    private final AiCore core;
    private final pp0 errorCallback;
    private final pp0 eventCallback;
    private boolean mAvailable;
    private final List<Runnable> mCacheTaskList;

    public AiCapability(Context context, pp0 pp0Var, pp0 pp0Var2) {
        context.getClass();
        this.core = new AiCore(context, this);
        this.eventCallback = pp0Var == null ? null : (pp0) getUiThreadCallback(pp0Var);
        this.errorCallback = pp0Var2 != null ? (pp0) getUiThreadCallback(pp0Var2) : null;
        this.mCacheTaskList = new ArrayList();
    }

    private final void cancelDownloadModel() {
        g3 g3Var = new g3(2);
        g3Var.c = this;
        VarHandle.storeStoreFence();
        doTask(g3Var);
    }

    /* JADX DEBUG: Class process forced to load method for inline: com.xiaomi.ai.vision.sdk.AiCore.cancelDownloadModel$default(com.xiaomi.ai.vision.sdk.AiCore, java.lang.String, java.lang.String, int, java.lang.Object):boolean */
    /* JADX INFO: Access modifiers changed from: private */
    public static final void cancelDownloadModel$lambda$10(AiCapability aiCapability) {
        aiCapability.getClass();
        AiCore.cancelDownloadModel$default(aiCapability.core, "zh-CN", null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkIfCtaAllowed$lambda$2(op0 op0Var, AiCapability aiCapability) {
        op0Var.getClass();
        aiCapability.getClass();
        op0Var.z(Boolean.valueOf(aiCapability.core.checkIfCtaAllowed()));
    }

    private final void checkModelExist(op0 onExist) {
        s6 s6Var = new s6(1);
        s6Var.c = onExist;
        s6Var.d = this;
        VarHandle.storeStoreFence();
        doTask(s6Var);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkModelExist$lambda$8(op0 op0Var, AiCapability aiCapability) {
        op0Var.getClass();
        aiCapability.getClass();
        op0Var.z(Boolean.valueOf(AiCore.checkModelExist$default(aiCapability.core, "zh-CN", null, 2, null)));
    }

    private final void doCacheTask() {
        synchronized (this) {
            try {
                Iterator<Runnable> it = this.mCacheTaskList.iterator();
                while (it.hasNext()) {
                    it.next().run();
                }
                this.mCacheTaskList.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private final void doTask(Runnable task) {
        if (this.mAvailable) {
            task.run();
            return;
        }
        synchronized (this) {
            this.mCacheTaskList.add(task);
        }
        start();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    private final <T> T getUiThreadCallback(T callback) {
        return (T) new UiThreadProxy(callback, null, 2, 0 == true ? 1 : 0).getProxy();
    }

    private final void isSupportOfflineRecognize(op0 onSupport) {
        h4 h4Var = new h4(1);
        h4Var.c = onSupport;
        h4Var.d = this;
        VarHandle.storeStoreFence();
        doTask(h4Var);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void isSupportOfflineRecognize$lambda$7(op0 op0Var, AiCapability aiCapability) {
        op0Var.getClass();
        aiCapability.getClass();
        op0Var.z(Boolean.valueOf(aiCapability.core.isSupportOfflineRecognize()));
    }

    public static /* synthetic */ boolean postData$default(AiCapability aiCapability, byte[] bArr, boolean z, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return aiCapability.postData(bArr, z, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestCtaAccredit$lambda$3(AiCapability aiCapability, CtaCallback ctaCallback) {
        aiCapability.getClass();
        ctaCallback.getClass();
        aiCapability.core.requestCtaAccredit((CtaCallback) aiCapability.getUiThreadCallback(ctaCallback));
    }

    private final void startDownloadModel(boolean silentUpdate, DownloadModelCallback downloadModelCallback, ErrorCallback errorCallback) {
        r6 r6Var = new r6();
        r6Var.b = this;
        r6Var.c = silentUpdate;
        r6Var.d = downloadModelCallback;
        r6Var.e = errorCallback;
        VarHandle.storeStoreFence();
        doTask(r6Var);
    }

    public static /* synthetic */ void startDownloadModel$default(AiCapability aiCapability, boolean z, DownloadModelCallback downloadModelCallback, ErrorCallback errorCallback, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        aiCapability.startDownloadModel(z, downloadModelCallback, errorCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startDownloadModel$lambda$9(AiCapability aiCapability, boolean z, DownloadModelCallback downloadModelCallback, ErrorCallback errorCallback) throws JSONException {
        aiCapability.getClass();
        downloadModelCallback.getClass();
        aiCapability.core.startDownloadModel(z, "zh-CN", null, (DownloadModelCallback) aiCapability.getUiThreadCallback(downloadModelCallback), errorCallback == null ? null : (ErrorCallback) aiCapability.getUiThreadCallback(errorCallback));
    }

    public static /* synthetic */ void startMultichannelRecognize$default(AiCapability aiCapability, int i, int i2, int i3, boolean z, String str, String str2, boolean z2, boolean z3, SpeechRecognizeCallback speechRecognizeCallback, TtsCallback ttsCallback, ErrorCallback errorCallback, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = -1;
        }
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            z = false;
        }
        if ((i4 & 32) != 0) {
            str2 = null;
        }
        if ((i4 & 64) != 0) {
            z2 = false;
        }
        if ((i4 & 128) != 0) {
            z3 = false;
        }
        if ((i4 & PowerNest.SHARK_HTTP) != 0) {
            ttsCallback = null;
        }
        aiCapability.startMultichannelRecognize(i, i2, i3, z, str, str2, z2, z3, speechRecognizeCallback, ttsCallback, (i4 & PowerNest.SHARK_TCP) != 0 ? null : errorCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startMultichannelRecognize$lambda$4(AiCapability aiCapability, int i, int i2, boolean z, int i3, String str, String str2, boolean z2, boolean z3, SpeechRecognizeCallback speechRecognizeCallback, TtsCallback ttsCallback, ErrorCallback errorCallback) {
        aiCapability.getClass();
        str.getClass();
        speechRecognizeCallback.getClass();
        aiCapability.core.startRecognize(i, i2, z ? 1 : 0, i3, str, str2, z2, z3, (SpeechRecognizeCallback) aiCapability.getUiThreadCallback(speechRecognizeCallback), ttsCallback == null ? null : (TtsCallback) aiCapability.getUiThreadCallback(ttsCallback), errorCallback != null ? (ErrorCallback) aiCapability.getUiThreadCallback(errorCallback) : null);
    }

    public static /* synthetic */ void startOfflineRecognize$default(AiCapability aiCapability, int i, String str, String str2, SpeechRecognizeCallback speechRecognizeCallback, ErrorCallback errorCallback, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = -1;
        }
        if ((i2 & 4) != 0) {
            str2 = null;
        }
        aiCapability.startOfflineRecognize(i, str, str2, speechRecognizeCallback, (i2 & 16) != 0 ? null : errorCallback);
    }

    public static /* synthetic */ void startRecognize$default(AiCapability aiCapability, int i, int i2, boolean z, String str, String str2, boolean z2, boolean z3, SpeechRecognizeCallback speechRecognizeCallback, TtsCallback ttsCallback, ErrorCallback errorCallback, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = -1;
        }
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        if ((i3 & 16) != 0) {
            str2 = null;
        }
        if ((i3 & 32) != 0) {
            z2 = false;
        }
        if ((i3 & 64) != 0) {
            z3 = false;
        }
        if ((i3 & 256) != 0) {
            ttsCallback = null;
        }
        aiCapability.startRecognize(i, i2, z, str, str2, z2, z3, speechRecognizeCallback, ttsCallback, (i3 & PowerNest.SHARK_HTTP) != 0 ? null : errorCallback);
    }

    public static /* synthetic */ void startTextTranslate$default(AiCapability aiCapability, String str, String str2, String str3, TextTranslateCallback textTranslateCallback, ErrorCallback errorCallback, int i, Object obj) {
        if ((i & 16) != 0) {
            errorCallback = null;
        }
        aiCapability.startTextTranslate(str, str2, str3, textTranslateCallback, errorCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startTextTranslate$lambda$6(AiCapability aiCapability, String str, String str2, String str3, TextTranslateCallback textTranslateCallback, ErrorCallback errorCallback) {
        aiCapability.getClass();
        str.getClass();
        str2.getClass();
        str3.getClass();
        textTranslateCallback.getClass();
        aiCapability.core.startTextTranslate(0, str, str2, str3, (TextTranslateCallback) aiCapability.getUiThreadCallback(textTranslateCallback), errorCallback == null ? null : (ErrorCallback) aiCapability.getUiThreadCallback(errorCallback));
    }

    public static /* synthetic */ void startTts$default(AiCapability aiCapability, String str, String str2, TtsCallback ttsCallback, ErrorCallback errorCallback, int i, Object obj) {
        if ((i & 8) != 0) {
            errorCallback = null;
        }
        aiCapability.startTts(str, str2, ttsCallback, errorCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startTts$lambda$5(AiCapability aiCapability, String str, String str2, TtsCallback ttsCallback, ErrorCallback errorCallback) {
        aiCapability.getClass();
        str.getClass();
        str2.getClass();
        ttsCallback.getClass();
        aiCapability.core.startTts(0, str, str2, (TtsCallback) aiCapability.getUiThreadCallback(ttsCallback), errorCallback == null ? null : (ErrorCallback) aiCapability.getUiThreadCallback(errorCallback));
    }

    public final void checkIfCtaAllowed(op0 onAllowed) {
        onAllowed.getClass();
        s6 s6Var = new s6(0);
        s6Var.c = onAllowed;
        s6Var.d = this;
        VarHandle.storeStoreFence();
        doTask(s6Var);
    }

    public final void destroy() {
        this.core.release("destroy");
        this.mCacheTaskList.clear();
    }

    @Override // com.xiaomi.ai.vision.sdk.callback.CoreCallback
    public void onAvailableChanged(boolean available) {
        Log.i(TAG, "onAvailableChanged: " + available);
        this.mAvailable = available;
        if (!available) {
            pp0 pp0Var = this.eventCallback;
            if (pp0Var != null) {
                pp0Var.H(1002, "server disconnected!");
                return;
            }
            return;
        }
        doCacheTask();
        pp0 pp0Var2 = this.eventCallback;
        if (pp0Var2 != null) {
            pp0Var2.H(1001, "server connected!");
        }
    }

    @Override // com.xiaomi.ai.vision.sdk.callback.CoreCallback
    public void onError(int code, String msg) {
        msg.getClass();
        Log.i(TAG, "onError, code: " + code + ", msg: " + msg);
        pp0 pp0Var = this.errorCallback;
        if (pp0Var != null) {
            pp0Var.H(Integer.valueOf(code), msg);
        }
    }

    @Override // com.xiaomi.ai.vision.sdk.callback.CoreCallback
    public void onEvent(int code, String msg) {
        msg.getClass();
        pp0 pp0Var = this.eventCallback;
        if (pp0Var != null) {
            pp0Var.H(Integer.valueOf(code), msg);
        }
    }

    @Override // com.xiaomi.ai.vision.sdk.callback.CoreCallback
    public void onInterrupt(String reason) {
        reason.getClass();
        Log.i(TAG, "onInterrupt: ".concat(reason));
        this.core.release("onInterrupt: ".concat(reason));
        pp0 pp0Var = this.eventCallback;
        if (pp0Var != null) {
            pp0Var.H(1000, reason);
        }
    }

    public final boolean postData(byte[] pcm, boolean isLastFrame, String dialogId) {
        pcm.getClass();
        dialogId.getClass();
        return this.core.postData(pcm, isLastFrame, dialogId);
    }

    public final void requestCtaAccredit(CtaCallback callback) {
        callback.getClass();
        u6 u6Var = new u6(0);
        u6Var.c = this;
        u6Var.d = callback;
        VarHandle.storeStoreFence();
        doTask(u6Var);
    }

    public final void start() {
        AiCore.connect$default(this.core, 0, 1, null);
    }

    public final void startMultichannelRecognize(int scenarioType, int recognizeMode, int channel, boolean interrupt, String srcLang, String destLang, boolean identifyLanguage, boolean vad, SpeechRecognizeCallback recognizeCallback, TtsCallback ttsCallback, ErrorCallback errorCallback) {
        srcLang.getClass();
        recognizeCallback.getClass();
        t6 t6Var = new t6();
        t6Var.b = this;
        t6Var.c = scenarioType;
        t6Var.d = recognizeMode;
        t6Var.e = interrupt;
        t6Var.f = channel;
        t6Var.g = srcLang;
        t6Var.h = destLang;
        t6Var.i = identifyLanguage;
        t6Var.j = vad;
        t6Var.k = recognizeCallback;
        t6Var.l = ttsCallback;
        t6Var.m = errorCallback;
        VarHandle.storeStoreFence();
        doTask(t6Var);
    }

    public final void startOfflineRecognize(int scenarioType, String srcLang, String destLang, SpeechRecognizeCallback recognizeCallback, ErrorCallback errorCallback) {
        srcLang.getClass();
        recognizeCallback.getClass();
        startRecognize$default(this, scenarioType, 1, false, srcLang, destLang, false, false, recognizeCallback, null, errorCallback, 356, null);
    }

    public final void startRecognize(int scenarioType, int recognizeMode, boolean interrupt, String srcLang, String destLang, boolean identifyLanguage, boolean vad, SpeechRecognizeCallback recognizeCallback, TtsCallback ttsCallback, ErrorCallback errorCallback) {
        srcLang.getClass();
        recognizeCallback.getClass();
        startMultichannelRecognize(scenarioType, recognizeMode, 0, interrupt, srcLang, destLang, identifyLanguage, vad, recognizeCallback, ttsCallback, errorCallback);
    }

    public final void startTextTranslate(String text, String srcLang, String destLang, TextTranslateCallback textTranslateCallback, ErrorCallback errorCallback) {
        text.getClass();
        srcLang.getClass();
        destLang.getClass();
        textTranslateCallback.getClass();
        v6 v6Var = new v6();
        v6Var.b = this;
        v6Var.c = text;
        v6Var.d = srcLang;
        v6Var.e = destLang;
        v6Var.f = textTranslateCallback;
        v6Var.g = errorCallback;
        VarHandle.storeStoreFence();
        doTask(v6Var);
    }

    public final void startTts(String text, String srcLang, TtsCallback ttsCallback, ErrorCallback errorCallback) {
        text.getClass();
        srcLang.getClass();
        ttsCallback.getClass();
        q6 q6Var = new q6();
        q6Var.b = this;
        q6Var.c = text;
        q6Var.d = srcLang;
        q6Var.e = ttsCallback;
        q6Var.f = errorCallback;
        VarHandle.storeStoreFence();
        doTask(q6Var);
    }

    public final void stopRecognize(String dialogId) {
        dialogId.getClass();
        this.core.stopRecognize(dialogId);
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/xiaomi/ai/vision/sdk/AiCapability$Companion;", a.c, "()V", "TAG", a.c, "isEnable", a.c, "context", "Landroid/content/Context;", "AiCapability_release"}, k = 1, mv = {1, 7, 1}, xi = SmsCheckerContentTypes.TYPE_TEL_NET_TEL)
    public static final class Companion {
        /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x0000: CONSTRUCTOR  A[MD:():void (m)] call: com.xiaomi.ai.vision.sdk.AiCapability.Companion.<init>():void type: THIS */
        public /* synthetic */ Companion(c60 c60Var) {
            this();
        }

        public final boolean isEnable(Context context) {
            context.getClass();
            return AiCapabilityUtils.INSTANCE.isEnable(context);
        }

        private Companion() {
        }
    }

    /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x000b: CONSTRUCTOR 
      (r2v0 android.content.Context)
      (wrap pp0:?: TERNARY null = ((wrap int:0x0000: ARITH (r5v0 int) & (2 int) A[WRAPPED]) != (0 int)) ? (null pp0) : (r3v0 pp0))
      (wrap pp0:?: TERNARY null = ((wrap int:0x0006: ARITH (r5v0 int) & (4 int) A[WRAPPED]) != (0 int)) ? (null pp0) : (r4v0 pp0))
     A[MD:(android.content.Context, pp0, pp0):void (m)] (LINE:45) call: com.xiaomi.ai.vision.sdk.AiCapability.<init>(android.content.Context, pp0, pp0):void type: THIS */
    public /* synthetic */ AiCapability(Context context, pp0 pp0Var, pp0 pp0Var2, int i, c60 c60Var) {
        this(context, (i & 2) != 0 ? null : pp0Var, (i & 4) != 0 ? null : pp0Var2);
    }
}
