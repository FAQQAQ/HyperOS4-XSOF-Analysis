package com.xiaomi.ai.vision.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.util.Log;
import com.xiaomi.ai.vision.sdk.callback.CoreCallback;
import com.xiaomi.ai.vision.sdk.callback.CtaCallback;
import com.xiaomi.ai.vision.sdk.callback.DownloadModelCallback;
import com.xiaomi.ai.vision.sdk.callback.ErrorCallback;
import com.xiaomi.ai.vision.sdk.callback.SpeechRecognizeCallback;
import com.xiaomi.ai.vision.sdk.callback.TextTranslateCallback;
import com.xiaomi.ai.vision.sdk.callback.TtsCallback;
import com.xiaomi.ai.vision.sdk.model.RecognizeResult;
import com.xiaomi.ai.vision.sdk.model.TranslateResult;
import com.xiaomi.ai.vision.sdk.model.WordTimestamp;
import com.xiaomi.onetrack.util.a;
import defpackage.b8;
import defpackage.c60;
import defpackage.cw1;
import defpackage.dw0;
import defpackage.fw0;
import defpackage.gw0;
import defpackage.hn;
import defpackage.hw0;
import defpackage.ir0;
import defpackage.nf0;
import defpackage.qz2;
import defpackage.w6;
import defpackage.x6;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import org.json.JSONException;
import org.json.JSONObject;
import tmsdk.common.tcc.SmsCheckerContentTypes;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0005*\u0002kq\b\u0000\u0018\u0000 t2\u00020\u0001:\u0001tB\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\r\u001a\u00020\f2\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\f¢\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\n¢\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0019\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u0019\u0010\u001aJq\u0010*\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u000f2\b\b\u0002\u0010\u001d\u001a\u00020\u000f2\b\b\u0002\u0010\u001e\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u001f2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\"\u001a\u00020\n2\u0006\u0010#\u001a\u00020\n2\u0006\u0010%\u001a\u00020$2\b\u0010'\u001a\u0004\u0018\u00010&2\b\u0010)\u001a\u0004\u0018\u00010(¢\u0006\u0004\b*\u0010+J%\u00100\u001a\u00020\n2\u0006\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020\n2\u0006\u0010/\u001a\u00020\u001f¢\u0006\u0004\b0\u00101J\u0015\u00102\u001a\u00020\f2\u0006\u0010/\u001a\u00020\u001f¢\u0006\u0004\b2\u00103J9\u00105\u001a\u00020\f2\b\b\u0002\u0010\u001d\u001a\u00020\u000f2\u0006\u00104\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010'\u001a\u00020&2\b\u0010)\u001a\u0004\u0018\u00010(¢\u0006\u0004\b5\u00106JA\u00109\u001a\u00020\f2\b\b\u0002\u0010\u001d\u001a\u00020\u000f2\u0006\u00104\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u001f2\u0006\u00108\u001a\u0002072\b\u0010)\u001a\u0004\u0018\u00010(¢\u0006\u0004\b9\u0010:J\u0015\u0010<\u001a\u00020\f2\u0006\u0010;\u001a\u00020\u001f¢\u0006\u0004\b<\u00103J\r\u0010=\u001a\u00020\n¢\u0006\u0004\b=\u0010\u0016J!\u0010>\u001a\u00020\n2\u0006\u0010 \u001a\u00020\u001f2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u001f¢\u0006\u0004\b>\u0010?J9\u0010C\u001a\u00020\f2\u0006\u0010@\u001a\u00020\n2\u0006\u0010 \u001a\u00020\u001f2\b\u0010!\u001a\u0004\u0018\u00010\u001f2\u0006\u0010B\u001a\u00020A2\b\u0010)\u001a\u0004\u0018\u00010(¢\u0006\u0004\bC\u0010DJ!\u0010E\u001a\u00020\n2\u0006\u0010 \u001a\u00020\u001f2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u001f¢\u0006\u0004\bE\u0010?J\u0019\u0010F\u001a\u00020\f2\b\b\u0002\u0010;\u001a\u00020\u001fH\u0002¢\u0006\u0004\bF\u00103J\"\u0010I\u001a\u0004\u0018\u00018\u0000\"\u0006\b\u0000\u0010G\u0018\u00012\u0006\u0010H\u001a\u00020\u001fH\u0082\b¢\u0006\u0004\bI\u0010JJ*\u0010K\u001a\u00020\f\"\u0006\b\u0000\u0010G\u0018\u00012\u0006\u0010H\u001a\u00020\u001f2\b\u0010\u0018\u001a\u0004\u0018\u00018\u0000H\u0082\b¢\u0006\u0004\bK\u0010LJ6\u0010O\u001a\u0004\u0018\u00018\u0000\"\u0006\b\u0000\u0010G\u0018\u00012\u0006\u0010M\u001a\u00020\u000f2\u0006\u0010N\u001a\u00020\u001f2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010(H\u0082\b¢\u0006\u0004\bO\u0010PJ)\u0010S\u001a\u00020\f2\b\u0010\u0018\u001a\u0004\u0018\u00010(2\u0006\u0010Q\u001a\u00020\u000f2\u0006\u0010R\u001a\u00020\u001fH\u0002¢\u0006\u0004\bS\u0010TR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010UR\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010V\u001a\u0004\bW\u0010XR\u0018\u0010\t\u001a\u0004\u0018\u00010Y8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010ZR\u0014\u0010\\\u001a\u00020[8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\\\u0010]R\u0014\u0010_\u001a\u00020^8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b_\u0010`R\u0018\u0010a\u001a\u0004\u0018\u00010\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\ba\u0010bR \u0010d\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020$0c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bd\u0010eR \u0010f\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020&0c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bf\u0010eR \u0010g\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u0002070c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bg\u0010eR&\u0010i\u001a\u0014\u0012\u0004\u0012\u00020\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020A0h0c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bi\u0010eR \u0010j\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020(0c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bj\u0010eR\u0014\u0010l\u001a\u00020k8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bl\u0010mR\u0014\u0010o\u001a\u00020n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bo\u0010pR\u0014\u0010r\u001a\u00020q8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\br\u0010s¨\u0006u"}, d2 = {"Lcom/xiaomi/ai/vision/sdk/AiCore;", a.c, "Landroid/content/Context;", "context", "Lcom/xiaomi/ai/vision/sdk/callback/CoreCallback;", "coreCallback", "<init>", "(Landroid/content/Context;Lcom/xiaomi/ai/vision/sdk/callback/CoreCallback;)V", "Landroid/os/IInterface;", "service", a.c, com.xiaomi.onetrack.a.a.C0018a.b, "Lc13;", "monitorServerState", "(Landroid/os/IInterface;Z)V", a.c, "retryCount", "connect", "(I)V", "attachToServer", "()V", "checkIfCtaAllowed", "()Z", "Lcom/xiaomi/ai/vision/sdk/callback/CtaCallback;", "callback", "requestCtaAccredit", "(Lcom/xiaomi/ai/vision/sdk/callback/CtaCallback;)V", "scenarioType", "recognizeMode", "startMode", "channel", a.c, "srcLang", "destLang", "identifyLanguage", "vad", "Lcom/xiaomi/ai/vision/sdk/callback/SpeechRecognizeCallback;", "recognizeCallback", "Lcom/xiaomi/ai/vision/sdk/callback/TtsCallback;", "ttsCallback", "Lcom/xiaomi/ai/vision/sdk/callback/ErrorCallback;", "errorCallback", "startRecognize", "(IIIILjava/lang/String;Ljava/lang/String;ZZLcom/xiaomi/ai/vision/sdk/callback/SpeechRecognizeCallback;Lcom/xiaomi/ai/vision/sdk/callback/TtsCallback;Lcom/xiaomi/ai/vision/sdk/callback/ErrorCallback;)V", a.c, "pcm", "isLastFrame", "dialogId", "postData", "([BZLjava/lang/String;)Z", "stopRecognize", "(Ljava/lang/String;)V", "text", "startTts", "(ILjava/lang/String;Ljava/lang/String;Lcom/xiaomi/ai/vision/sdk/callback/TtsCallback;Lcom/xiaomi/ai/vision/sdk/callback/ErrorCallback;)V", "Lcom/xiaomi/ai/vision/sdk/callback/TextTranslateCallback;", "textTranslateCallback", "startTextTranslate", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/xiaomi/ai/vision/sdk/callback/TextTranslateCallback;Lcom/xiaomi/ai/vision/sdk/callback/ErrorCallback;)V", com.xiaomi.onetrack.h.b.a.g, "release", "isSupportOfflineRecognize", "checkModelExist", "(Ljava/lang/String;Ljava/lang/String;)Z", "silentUpdate", "Lcom/xiaomi/ai/vision/sdk/callback/DownloadModelCallback;", "downloadModeCallback", "startDownloadModel", "(ZLjava/lang/String;Ljava/lang/String;Lcom/xiaomi/ai/vision/sdk/callback/DownloadModelCallback;Lcom/xiaomi/ai/vision/sdk/callback/ErrorCallback;)V", "cancelDownloadModel", "disconnect", "T", "key", "getCallback", "(Ljava/lang/String;)Ljava/lang/Object;", "setCallback", "(Ljava/lang/String;Ljava/lang/Object;)V", "type", "params", "doFunc", "(ILjava/lang/String;Lcom/xiaomi/ai/vision/sdk/callback/ErrorCallback;)Ljava/lang/Object;", com.xiaomi.onetrack.g.a.d, com.xiaomi.onetrack.g.a.c, "onError", "(Lcom/xiaomi/ai/vision/sdk/callback/ErrorCallback;ILjava/lang/String;)V", "Landroid/content/Context;", "Lcom/xiaomi/ai/vision/sdk/callback/CoreCallback;", "getCoreCallback", "()Lcom/xiaomi/ai/vision/sdk/callback/CoreCallback;", "Lhw0;", "Lhw0;", "Landroid/os/Handler;", "uiHandler", "Landroid/os/Handler;", "Lir0;", "gson", "Lir0;", "mCtaCallback", "Lcom/xiaomi/ai/vision/sdk/callback/CtaCallback;", a.c, "mRecognizeCallbackMap", "Ljava/util/Map;", "mTtsCallbackMap", "mTextTranslateCallbackMap", a.c, "mDownloadModelCallbackMap", "mErrorCallbackMap", "com/xiaomi/ai/vision/sdk/AiCore$sdkCallback$1", "sdkCallback", "Lcom/xiaomi/ai/vision/sdk/AiCore$sdkCallback$1;", "Landroid/os/IBinder$DeathRecipient;", "deathRecipient", "Landroid/os/IBinder$DeathRecipient;", "com/xiaomi/ai/vision/sdk/AiCore$serviceConnection$1", "serviceConnection", "Lcom/xiaomi/ai/vision/sdk/AiCore$serviceConnection$1;", "Companion", "AiCapability_release"}, k = 1, mv = {1, 7, 1}, xi = SmsCheckerContentTypes.TYPE_TEL_NET_TEL)
public final class AiCore {
    public static final String ACTION_TRANSLATE_SDK_SERVICE = "com.xiaomi.aiasst.vision.ACTION_TRANSLATE_SDK_SERVICE";
    public static final int MAX_CONNECT_RETRY_COUNT = 3;
    public static final String PKG_AI_ASST_VISION = "com.xiaomi.aiasst.vision";
    public static final String TAG = "AiCore";
    private final Context context;
    private final CoreCallback coreCallback;
    private final IBinder.DeathRecipient deathRecipient;
    private final ir0 gson;
    private CtaCallback mCtaCallback;
    private final Map<String, List<DownloadModelCallback>> mDownloadModelCallbackMap;
    private final Map<String, ErrorCallback> mErrorCallbackMap;
    private final Map<String, SpeechRecognizeCallback> mRecognizeCallbackMap;
    private final Map<String, TextTranslateCallback> mTextTranslateCallbackMap;
    private final Map<String, TtsCallback> mTtsCallbackMap;
    private final AiCore$sdkCallback$1 sdkCallback;
    private hw0 service;
    private final AiCore$serviceConnection$1 serviceConnection;
    private final Handler uiHandler;

    /* JADX WARN: Type inference failed for: r1v10, types: [com.xiaomi.ai.vision.sdk.AiCore$serviceConnection$1] */
    /* JADX WARN: Type inference failed for: r1v8, types: [com.xiaomi.ai.vision.sdk.AiCore$sdkCallback$1] */
    public AiCore(Context context, CoreCallback coreCallback) {
        context.getClass();
        this.context = context;
        this.coreCallback = coreCallback;
        this.uiHandler = new Handler(Looper.getMainLooper());
        this.gson = new ir0();
        this.mRecognizeCallbackMap = new LinkedHashMap();
        this.mTtsCallbackMap = new LinkedHashMap();
        this.mTextTranslateCallbackMap = new LinkedHashMap();
        this.mDownloadModelCallbackMap = new LinkedHashMap();
        this.mErrorCallbackMap = new LinkedHashMap();
        this.sdkCallback = new dw0() { // from class: com.xiaomi.ai.vision.sdk.AiCore$sdkCallback$1
            {
                attachInterface(this, "com.xiaomi.aiasst.vision.sdk.ITranslateSdkCallback");
            }

            @Override // defpackage.ew0
            public void onCtaResult(boolean result) {
                Log.i(AiCore.TAG, "onCTAResult, result: " + result);
                CtaCallback ctaCallback = this.this$0.mCtaCallback;
                if (ctaCallback != null) {
                    ctaCallback.onResult(result);
                }
                this.this$0.mCtaCallback = null;
            }

            @Override // defpackage.ew0
            public void onError(String dialogId, int code, String msg) {
                ErrorCallback errorCallback;
                dialogId.getClass();
                msg.getClass();
                Log.i(AiCore.TAG, "onError, dialogId: " + dialogId + ", code: " + code + ", msg: " + msg);
                AiCore aiCore = this.this$0;
                ErrorCallback errorCallback2 = null;
                if (dialogId.length() == 0) {
                    Log.w(AiCore.TAG, "getCallback, key is empty");
                } else if (ErrorCallback.class.equals(TtsCallback.class)) {
                    TtsCallback ttsCallback = (TtsCallback) aiCore.mTtsCallbackMap.get(dialogId);
                    if (ttsCallback != null) {
                        errorCallback2 = (ErrorCallback) ttsCallback;
                    }
                } else if (ErrorCallback.class.equals(SpeechRecognizeCallback.class)) {
                    SpeechRecognizeCallback speechRecognizeCallback = (SpeechRecognizeCallback) aiCore.mRecognizeCallbackMap.get(dialogId);
                    if (speechRecognizeCallback != null) {
                        errorCallback2 = (ErrorCallback) speechRecognizeCallback;
                    }
                } else if (ErrorCallback.class.equals(TextTranslateCallback.class)) {
                    TextTranslateCallback textTranslateCallback = (TextTranslateCallback) aiCore.mTextTranslateCallbackMap.get(dialogId);
                    if (textTranslateCallback != null) {
                        errorCallback2 = (ErrorCallback) textTranslateCallback;
                    }
                } else if (ErrorCallback.class.equals(ErrorCallback.class) && (errorCallback = (ErrorCallback) aiCore.mErrorCallbackMap.get(dialogId)) != null) {
                    errorCallback2 = errorCallback;
                }
                aiCore.onError(errorCallback2, code, msg);
            }

            @Override // defpackage.ew0
            public void onEvent(String dialogId, int code, String msg) {
                dialogId.getClass();
                msg.getClass();
                Log.i(AiCore.TAG, "onEvent, dialogId: " + dialogId + ", code: " + code + ", msg: " + msg);
                CoreCallback coreCallback2 = this.this$0.getCoreCallback();
                if (coreCallback2 != null) {
                    coreCallback2.onEvent(code, msg);
                }
            }

            @Override // defpackage.ew0
            public void onInterrupt(String reason) {
                reason.getClass();
                Log.i(AiCore.TAG, "onInterrupt, reason: ".concat(reason));
                CoreCallback coreCallback2 = this.this$0.getCoreCallback();
                if (coreCallback2 != null) {
                    coreCallback2.onInterrupt(reason);
                }
            }

            @Override // defpackage.ew0
            public void onRecognizeResult(b8 aiTranslateResult) {
                SpeechRecognizeCallback speechRecognizeCallback;
                List list;
                aiTranslateResult.getClass();
                Log.i(AiCore.TAG, "onRecognizeResult, " + aiTranslateResult);
                String str = aiTranslateResult.b;
                if (str == null || str.length() == 0) {
                    return;
                }
                if (this.this$0.mRecognizeCallbackMap.containsKey(str) && (speechRecognizeCallback = (SpeechRecognizeCallback) this.this$0.mRecognizeCallbackMap.get(str)) != null) {
                    str.getClass();
                    int i = aiTranslateResult.c;
                    String str2 = aiTranslateResult.d;
                    String str3 = str2 == null ? a.c : str2;
                    String str4 = aiTranslateResult.e;
                    String str5 = str4 == null ? a.c : str4;
                    String str6 = aiTranslateResult.f;
                    String str7 = str6 == null ? a.c : str6;
                    String str8 = aiTranslateResult.g;
                    String str9 = str8 == null ? a.c : str8;
                    int i2 = aiTranslateResult.h;
                    String str10 = aiTranslateResult.i;
                    AiCore aiCore = this.this$0;
                    if (str10 == null || str10.length() == 0) {
                        list = nf0.b;
                    } else {
                        ir0 ir0Var = aiCore.gson;
                        Type type = new qz2<List<? extends WordTimestamp>>() { // from class: com.xiaomi.ai.vision.sdk.AiCore$sdkCallback$1$onRecognizeResult$1$1
                        }.getType();
                        ir0Var.getClass();
                        Object objC = ir0Var.c(str10, qz2.get(type));
                        objC.getClass();
                        list = (List) objC;
                    }
                    speechRecognizeCallback.onRecognizeResult(str, new RecognizeResult(i, str3, str5, str7, str9, i2, list, aiTranslateResult.j, aiTranslateResult.k));
                }
                if (this.this$0.mTextTranslateCallbackMap.containsKey(str)) {
                    TextTranslateCallback textTranslateCallback = (TextTranslateCallback) this.this$0.mTextTranslateCallbackMap.get(str);
                    if (textTranslateCallback != null) {
                        String str11 = aiTranslateResult.b;
                        String str12 = aiTranslateResult.d;
                        String str13 = str12 == null ? a.c : str12;
                        String str14 = aiTranslateResult.e;
                        String str15 = str14 == null ? a.c : str14;
                        String str16 = aiTranslateResult.f;
                        String str17 = str16 == null ? a.c : str16;
                        String str18 = aiTranslateResult.g;
                        String str19 = str18 == null ? a.c : str18;
                        str11.getClass();
                        textTranslateCallback.onResult(new TranslateResult(str11, str17, str19, str13, str15));
                    }
                    AiCore aiCore2 = this.this$0;
                    str.getClass();
                    if (TextTranslateCallback.class.equals(TtsCallback.class)) {
                        aiCore2.mTtsCallbackMap.remove(str);
                        return;
                    }
                    if (TextTranslateCallback.class.equals(SpeechRecognizeCallback.class)) {
                        aiCore2.mRecognizeCallbackMap.remove(str);
                    } else if (TextTranslateCallback.class.equals(TextTranslateCallback.class)) {
                        aiCore2.mTextTranslateCallbackMap.remove(str);
                    } else if (TextTranslateCallback.class.equals(ErrorCallback.class)) {
                        aiCore2.mErrorCallbackMap.remove(str);
                    }
                }
            }

            @Override // defpackage.ew0
            public void onRecognizeStart(String dialogId) {
                dialogId.getClass();
            }

            @Override // defpackage.ew0
            public void onRecognizeStop(String dialogId) {
                ErrorCallback errorCallback;
                dialogId.getClass();
                Log.i(AiCore.TAG, "onRecognizeStop: ".concat(dialogId));
                AiCore aiCore = this.this$0;
                SpeechRecognizeCallback speechRecognizeCallback = null;
                if (dialogId.length() == 0) {
                    Log.w(AiCore.TAG, "getCallback, key is empty");
                } else if (SpeechRecognizeCallback.class.equals(TtsCallback.class)) {
                    TtsCallback ttsCallback = (TtsCallback) aiCore.mTtsCallbackMap.get(dialogId);
                    if (ttsCallback != null) {
                        speechRecognizeCallback = (SpeechRecognizeCallback) ttsCallback;
                    }
                } else if (SpeechRecognizeCallback.class.equals(SpeechRecognizeCallback.class)) {
                    SpeechRecognizeCallback speechRecognizeCallback2 = (SpeechRecognizeCallback) aiCore.mRecognizeCallbackMap.get(dialogId);
                    if (speechRecognizeCallback2 != null) {
                        speechRecognizeCallback = speechRecognizeCallback2;
                    }
                } else if (SpeechRecognizeCallback.class.equals(TextTranslateCallback.class)) {
                    TextTranslateCallback textTranslateCallback = (TextTranslateCallback) aiCore.mTextTranslateCallbackMap.get(dialogId);
                    if (textTranslateCallback != null) {
                        speechRecognizeCallback = (SpeechRecognizeCallback) textTranslateCallback;
                    }
                } else if (SpeechRecognizeCallback.class.equals(ErrorCallback.class) && (errorCallback = (ErrorCallback) aiCore.mErrorCallbackMap.get(dialogId)) != null) {
                    speechRecognizeCallback = (SpeechRecognizeCallback) errorCallback;
                }
                if (speechRecognizeCallback != null) {
                    speechRecognizeCallback.onRecognizeStop(dialogId);
                }
            }

            @Override // defpackage.ew0
            public void onShowCtaDialog() {
                Log.i(AiCore.TAG, "onShowCtaDialog");
                CtaCallback ctaCallback = this.this$0.mCtaCallback;
                if (ctaCallback != null) {
                    ctaCallback.onShow();
                }
            }

            @Override // defpackage.ew0
            public void onTextTranslateResult(b8 aiTranslateResult) {
                ErrorCallback errorCallback;
                aiTranslateResult.getClass();
                Log.i(AiCore.TAG, "onTextTranslateResult, result: " + aiTranslateResult);
                String str = aiTranslateResult.b;
                if (str == null || str.length() == 0) {
                    return;
                }
                AiCore aiCore = this.this$0;
                str.getClass();
                TextTranslateCallback textTranslateCallback = null;
                if (str.length() == 0) {
                    Log.w(AiCore.TAG, "getCallback, key is empty");
                } else if (TextTranslateCallback.class.equals(TtsCallback.class)) {
                    TtsCallback ttsCallback = (TtsCallback) aiCore.mTtsCallbackMap.get(str);
                    if (ttsCallback != null) {
                        textTranslateCallback = (TextTranslateCallback) ttsCallback;
                    }
                } else if (TextTranslateCallback.class.equals(SpeechRecognizeCallback.class)) {
                    SpeechRecognizeCallback speechRecognizeCallback = (SpeechRecognizeCallback) aiCore.mRecognizeCallbackMap.get(str);
                    if (speechRecognizeCallback != null) {
                        textTranslateCallback = (TextTranslateCallback) speechRecognizeCallback;
                    }
                } else if (TextTranslateCallback.class.equals(TextTranslateCallback.class)) {
                    TextTranslateCallback textTranslateCallback2 = (TextTranslateCallback) aiCore.mTextTranslateCallbackMap.get(str);
                    if (textTranslateCallback2 != null) {
                        textTranslateCallback = textTranslateCallback2;
                    }
                } else if (TextTranslateCallback.class.equals(ErrorCallback.class) && (errorCallback = (ErrorCallback) aiCore.mErrorCallbackMap.get(str)) != null) {
                    textTranslateCallback = (TextTranslateCallback) errorCallback;
                }
                if (textTranslateCallback != null) {
                    String str2 = aiTranslateResult.b;
                    String str3 = aiTranslateResult.d;
                    String str4 = str3 == null ? a.c : str3;
                    String str5 = aiTranslateResult.e;
                    String str6 = str5 == null ? a.c : str5;
                    String str7 = aiTranslateResult.f;
                    String str8 = str7 == null ? a.c : str7;
                    String str9 = aiTranslateResult.g;
                    String str10 = str9 == null ? a.c : str9;
                    str2.getClass();
                    textTranslateCallback.onResult(new TranslateResult(str2, str8, str10, str4, str6));
                }
                AiCore aiCore2 = this.this$0;
                if (TextTranslateCallback.class.equals(TtsCallback.class)) {
                    aiCore2.mTtsCallbackMap.remove(str);
                    return;
                }
                if (TextTranslateCallback.class.equals(SpeechRecognizeCallback.class)) {
                    aiCore2.mRecognizeCallbackMap.remove(str);
                } else if (TextTranslateCallback.class.equals(TextTranslateCallback.class)) {
                    aiCore2.mTextTranslateCallbackMap.remove(str);
                } else if (TextTranslateCallback.class.equals(ErrorCallback.class)) {
                    aiCore2.mErrorCallbackMap.remove(str);
                }
            }

            @Override // defpackage.ew0
            public void onTtsData(byte[] data, String dialogId) {
                ErrorCallback errorCallback;
                data.getClass();
                dialogId.getClass();
                Log.i(AiCore.TAG, "onTtsData, dialogId: ".concat(dialogId));
                AiCore aiCore = this.this$0;
                TtsCallback ttsCallback = null;
                if (dialogId.length() == 0) {
                    Log.w(AiCore.TAG, "getCallback, key is empty");
                } else if (TtsCallback.class.equals(TtsCallback.class)) {
                    TtsCallback ttsCallback2 = (TtsCallback) aiCore.mTtsCallbackMap.get(dialogId);
                    if (ttsCallback2 != null) {
                        ttsCallback = ttsCallback2;
                    }
                } else if (TtsCallback.class.equals(SpeechRecognizeCallback.class)) {
                    SpeechRecognizeCallback speechRecognizeCallback = (SpeechRecognizeCallback) aiCore.mRecognizeCallbackMap.get(dialogId);
                    if (speechRecognizeCallback != null) {
                        ttsCallback = (TtsCallback) speechRecognizeCallback;
                    }
                } else if (TtsCallback.class.equals(TextTranslateCallback.class)) {
                    TextTranslateCallback textTranslateCallback = (TextTranslateCallback) aiCore.mTextTranslateCallbackMap.get(dialogId);
                    if (textTranslateCallback != null) {
                        ttsCallback = (TtsCallback) textTranslateCallback;
                    }
                } else if (TtsCallback.class.equals(ErrorCallback.class) && (errorCallback = (ErrorCallback) aiCore.mErrorCallbackMap.get(dialogId)) != null) {
                    ttsCallback = (TtsCallback) errorCallback;
                }
                if (ttsCallback != null) {
                    ttsCallback.onReceiveData(dialogId, data);
                }
            }

            @Override // defpackage.ew0
            public void onTtsFinish(String dialogId) {
                ErrorCallback errorCallback;
                dialogId.getClass();
                Log.i(AiCore.TAG, "onTtsFinish, dialogId: ".concat(dialogId));
                AiCore aiCore = this.this$0;
                TtsCallback ttsCallback = null;
                if (dialogId.length() == 0) {
                    Log.w(AiCore.TAG, "getCallback, key is empty");
                } else if (TtsCallback.class.equals(TtsCallback.class)) {
                    TtsCallback ttsCallback2 = (TtsCallback) aiCore.mTtsCallbackMap.get(dialogId);
                    if (ttsCallback2 != null) {
                        ttsCallback = ttsCallback2;
                    }
                } else if (TtsCallback.class.equals(SpeechRecognizeCallback.class)) {
                    SpeechRecognizeCallback speechRecognizeCallback = (SpeechRecognizeCallback) aiCore.mRecognizeCallbackMap.get(dialogId);
                    if (speechRecognizeCallback != null) {
                        ttsCallback = (TtsCallback) speechRecognizeCallback;
                    }
                } else if (TtsCallback.class.equals(TextTranslateCallback.class)) {
                    TextTranslateCallback textTranslateCallback = (TextTranslateCallback) aiCore.mTextTranslateCallbackMap.get(dialogId);
                    if (textTranslateCallback != null) {
                        ttsCallback = (TtsCallback) textTranslateCallback;
                    }
                } else if (TtsCallback.class.equals(ErrorCallback.class) && (errorCallback = (ErrorCallback) aiCore.mErrorCallbackMap.get(dialogId)) != null) {
                    ttsCallback = (TtsCallback) errorCallback;
                }
                if (ttsCallback != null) {
                    ttsCallback.onPlayFinish(dialogId);
                }
            }

            @Override // defpackage.ew0
            public void onTtsSpeak(String text, String lang, String dialogId) {
                ErrorCallback errorCallback;
                text.getClass();
                lang.getClass();
                dialogId.getClass();
                Log.i(AiCore.TAG, "onTtsSpeak, text: " + text + ", lang: " + lang + ", dialogId: " + dialogId);
                AiCore aiCore = this.this$0;
                TtsCallback ttsCallback = null;
                if (dialogId.length() == 0) {
                    Log.w(AiCore.TAG, "getCallback, key is empty");
                } else if (TtsCallback.class.equals(TtsCallback.class)) {
                    TtsCallback ttsCallback2 = (TtsCallback) aiCore.mTtsCallbackMap.get(dialogId);
                    if (ttsCallback2 != null) {
                        ttsCallback = ttsCallback2;
                    }
                } else if (TtsCallback.class.equals(SpeechRecognizeCallback.class)) {
                    SpeechRecognizeCallback speechRecognizeCallback = (SpeechRecognizeCallback) aiCore.mRecognizeCallbackMap.get(dialogId);
                    if (speechRecognizeCallback != null) {
                        ttsCallback = (TtsCallback) speechRecognizeCallback;
                    }
                } else if (TtsCallback.class.equals(TextTranslateCallback.class)) {
                    TextTranslateCallback textTranslateCallback = (TextTranslateCallback) aiCore.mTextTranslateCallbackMap.get(dialogId);
                    if (textTranslateCallback != null) {
                        ttsCallback = (TtsCallback) textTranslateCallback;
                    }
                } else if (TtsCallback.class.equals(ErrorCallback.class) && (errorCallback = (ErrorCallback) aiCore.mErrorCallbackMap.get(dialogId)) != null) {
                    ttsCallback = (TtsCallback) errorCallback;
                }
                if (ttsCallback != null) {
                    ttsCallback.onSpeak(dialogId, text, lang);
                }
            }

            @Override // defpackage.ew0
            public void onTtsStart(int sampleRate, String dialogId) {
                ErrorCallback errorCallback;
                dialogId.getClass();
                Log.i(AiCore.TAG, "onTtsStart, sampleRate: " + sampleRate + ", dialogId: " + dialogId);
                AiCore aiCore = this.this$0;
                TtsCallback ttsCallback = null;
                if (dialogId.length() == 0) {
                    Log.w(AiCore.TAG, "getCallback, key is empty");
                } else if (TtsCallback.class.equals(TtsCallback.class)) {
                    TtsCallback ttsCallback2 = (TtsCallback) aiCore.mTtsCallbackMap.get(dialogId);
                    if (ttsCallback2 != null) {
                        ttsCallback = ttsCallback2;
                    }
                } else if (TtsCallback.class.equals(SpeechRecognizeCallback.class)) {
                    SpeechRecognizeCallback speechRecognizeCallback = (SpeechRecognizeCallback) aiCore.mRecognizeCallbackMap.get(dialogId);
                    if (speechRecognizeCallback != null) {
                        ttsCallback = (TtsCallback) speechRecognizeCallback;
                    }
                } else if (TtsCallback.class.equals(TextTranslateCallback.class)) {
                    TextTranslateCallback textTranslateCallback = (TextTranslateCallback) aiCore.mTextTranslateCallbackMap.get(dialogId);
                    if (textTranslateCallback != null) {
                        ttsCallback = (TtsCallback) textTranslateCallback;
                    }
                } else if (TtsCallback.class.equals(ErrorCallback.class) && (errorCallback = (ErrorCallback) aiCore.mErrorCallbackMap.get(dialogId)) != null) {
                    ttsCallback = (TtsCallback) errorCallback;
                }
                if (ttsCallback != null) {
                    ttsCallback.onPlayStart(dialogId, sampleRate);
                }
            }
        };
        this.deathRecipient = new w6();
        this.serviceConnection = new ServiceConnection() { // from class: com.xiaomi.ai.vision.sdk.AiCore$serviceConnection$1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder binder) {
                hw0 hw0Var;
                String string;
                name.getClass();
                binder.getClass();
                AiCore aiCore = this.this$0;
                int i = gw0.a;
                IInterface iInterfaceQueryLocalInterface = binder.queryLocalInterface("com.xiaomi.aiasst.vision.sdk.ITranslateSdkInterface");
                if (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof hw0)) {
                    fw0 fw0Var = new fw0();
                    fw0Var.a = binder;
                    hw0Var = fw0Var;
                } else {
                    hw0Var = (hw0) iInterfaceQueryLocalInterface;
                }
                aiCore.service = hw0Var;
                StringBuilder sb = new StringBuilder("onServiceConnected, name: ");
                sb.append(name);
                sb.append(", protocol version: ");
                hw0 hw0Var2 = this.this$0.service;
                if (hw0Var2 != null) {
                    fw0 fw0Var2 = (fw0) hw0Var2;
                    Parcel parcelObtain = Parcel.obtain();
                    Parcel parcelObtain2 = Parcel.obtain();
                    try {
                        parcelObtain.writeInterfaceToken("com.xiaomi.aiasst.vision.sdk.ITranslateSdkInterface");
                        fw0Var2.a.transact(3, parcelObtain, parcelObtain2, 0);
                        parcelObtain2.readException();
                        string = parcelObtain2.readString();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } catch (Throwable th) {
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        throw th;
                    }
                } else {
                    string = null;
                }
                sb.append(string);
                Log.d(AiCore.TAG, sb.toString());
                AiCore aiCore2 = this.this$0;
                aiCore2.monitorServerState(aiCore2.service, true);
                this.this$0.attachToServer();
                hw0 hw0Var3 = this.this$0.service;
                if (hw0Var3 != null) {
                    ((fw0) hw0Var3).s(this.this$0.sdkCallback);
                }
                CoreCallback coreCallback2 = this.this$0.getCoreCallback();
                if (coreCallback2 != null) {
                    coreCallback2.onAvailableChanged(true);
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                name.getClass();
                Log.d(AiCore.TAG, "onServiceDisconnected, name: " + name);
                AiCore aiCore = this.this$0;
                aiCore.monitorServerState(aiCore.service, false);
                this.this$0.service = null;
                CoreCallback coreCallback2 = this.this$0.getCoreCallback();
                if (coreCallback2 != null) {
                    coreCallback2.onAvailableChanged(false);
                }
            }
        };
    }

    public static /* synthetic */ boolean cancelDownloadModel$default(AiCore aiCore, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        return aiCore.cancelDownloadModel(str, str2);
    }

    public static /* synthetic */ boolean checkModelExist$default(AiCore aiCore, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        return aiCore.checkModelExist(str, str2);
    }

    public static /* synthetic */ void connect$default(AiCore aiCore, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 1;
        }
        aiCore.connect(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void connect$lambda$2(AiCore aiCore, int i) {
        aiCore.getClass();
        aiCore.connect(i + 1);
    }

    private final void disconnect(String reason) {
        Log.i(TAG, "unbindService, reason: " + reason);
        if (this.service == null) {
            return;
        }
        this.context.unbindService(this.serviceConnection);
        this.service = null;
        CoreCallback coreCallback = this.coreCallback;
        if (coreCallback != null) {
            coreCallback.onAvailableChanged(false);
        }
    }

    public static /* synthetic */ void disconnect$default(AiCore aiCore, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = a.c;
        }
        aiCore.disconnect(str);
    }

    private final <T> T doFunc(int type, String params, ErrorCallback errorCallback) {
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            hw0 hw0Var = this.service;
            if (!(hw0Var != null ? ((fw0) hw0Var).r(type) : false)) {
                Log.i(TAG, "doFunc: " + hn.j(type) + ", func disabled !");
                return null;
            }
            hw0 hw0Var2 = this.service;
            String strQ = hw0Var2 != null ? ((fw0) hw0Var2).q(type, params) : null;
            Log.i(TAG, "doFunc: " + hn.j(type) + ", params: " + params + ", result: " + strQ + ", span: " + (System.currentTimeMillis() - jCurrentTimeMillis));
            if (strQ == null) {
                return null;
            }
            throw new UnsupportedOperationException("This function has a reified type parameter and thus can only be inlined at compilation time, not called directly.");
        } catch (Exception e) {
            onError(errorCallback, 2002, "doFunc: " + hn.j(type) + " exception: " + e);
            return null;
        }
    }

    public static Object doFunc$default(AiCore aiCore, int i, String str, ErrorCallback errorCallback, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            errorCallback = null;
        }
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            hw0 hw0Var = aiCore.service;
            if (!(hw0Var != null ? ((fw0) hw0Var).r(i) : false)) {
                Log.i(TAG, "doFunc: " + hn.j(i) + ", func disabled !");
                return null;
            }
            hw0 hw0Var2 = aiCore.service;
            String strQ = hw0Var2 != null ? ((fw0) hw0Var2).q(i, str) : null;
            Log.i(TAG, "doFunc: " + hn.j(i) + ", params: " + str + ", result: " + strQ + ", span: " + (System.currentTimeMillis() - jCurrentTimeMillis));
            if (strQ == null) {
                return null;
            }
            throw new UnsupportedOperationException("This function has a reified type parameter and thus can only be inlined at compilation time, not called directly.");
        } catch (Exception e) {
            aiCore.onError(errorCallback, 2002, "doFunc: " + hn.j(i) + " exception: " + e);
            return null;
        }
    }

    private final <T> T getCallback(String key) {
        if (key.length() != 0) {
            throw new UnsupportedOperationException("This function has a reified type parameter and thus can only be inlined at compilation time, not called directly.");
        }
        Log.w(TAG, "getCallback, key is empty");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onError(ErrorCallback callback, int code, String msg) {
        if (callback != null) {
            callback.onError(code, msg);
            return;
        }
        CoreCallback coreCallback = this.coreCallback;
        if (coreCallback != null) {
            coreCallback.onError(code, msg);
        }
    }

    private final <T> void setCallback(String key, T callback) {
        throw new UnsupportedOperationException("This function has a reified type parameter and thus can only be inlined at compilation time, not called directly.");
    }

    public static /* synthetic */ void startRecognize$default(AiCore aiCore, int i, int i2, int i3, int i4, String str, String str2, boolean z, boolean z2, SpeechRecognizeCallback speechRecognizeCallback, TtsCallback ttsCallback, ErrorCallback errorCallback, int i5, Object obj) {
        if ((i5 & 4) != 0) {
            i3 = 0;
        }
        if ((i5 & 8) != 0) {
            i4 = 0;
        }
        if ((i5 & 32) != 0) {
            str2 = null;
        }
        aiCore.startRecognize(i, i2, i3, i4, str, str2, z, z2, speechRecognizeCallback, ttsCallback, errorCallback);
    }

    public static /* synthetic */ void startTextTranslate$default(AiCore aiCore, int i, String str, String str2, String str3, TextTranslateCallback textTranslateCallback, ErrorCallback errorCallback, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        aiCore.startTextTranslate(i, str, str2, str3, textTranslateCallback, errorCallback);
    }

    public static /* synthetic */ void startTts$default(AiCore aiCore, int i, String str, String str2, TtsCallback ttsCallback, ErrorCallback errorCallback, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        aiCore.startTts(i, str, str2, ttsCallback, errorCallback);
    }

    public final void attachToServer() {
        Object objValueOf;
        String packageName = this.context.getPackageName();
        packageName.getClass();
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            hw0 hw0Var = this.service;
            if (!(hw0Var != null ? ((fw0) hw0Var).r(0) : false)) {
                Log.i(TAG, "doFunc: " + hn.j(0) + ", func disabled !");
                return;
            }
            hw0 hw0Var2 = this.service;
            String strQ = hw0Var2 != null ? ((fw0) hw0Var2).q(0, packageName) : null;
            Log.i(TAG, "doFunc: " + hn.j(0) + ", params: " + packageName + ", result: " + strQ + ", span: " + (System.currentTimeMillis() - jCurrentTimeMillis));
            if (strQ != null) {
                if (Boolean.class.equals(Boolean.class)) {
                    objValueOf = Boolean.valueOf(Boolean.parseBoolean(strQ));
                } else if (Boolean.class.equals(Integer.class)) {
                    objValueOf = Integer.valueOf(Integer.parseInt(strQ));
                } else if (Boolean.class.equals(Long.class)) {
                    objValueOf = Long.valueOf(Long.parseLong(strQ));
                } else if (Boolean.class.equals(Float.class)) {
                    objValueOf = Float.valueOf(Float.parseFloat(strQ));
                } else if (Boolean.class.equals(Double.class)) {
                    objValueOf = strQ;
                    objValueOf = Double.valueOf(Double.parseDouble(strQ));
                }
                objValueOf = strQ;
            }
        } catch (Exception e) {
            onError(null, 2002, "doFunc: " + hn.j(0) + " exception: " + e);
        }
    }

    public final boolean cancelDownloadModel(String srcLang, String destLang) throws JSONException {
        JSONObject jSONObject;
        Object objValueOf;
        srcLang.getClass();
        Boolean bool = null;
        String str = true & true ? a.c : null;
        try {
            if (str == null) {
                str = a.c;
            }
            jSONObject = new JSONObject(str);
        } catch (Exception unused) {
            jSONObject = new JSONObject();
        }
        jSONObject.put("srcLang", srcLang);
        if (destLang == null) {
            destLang = a.c;
        }
        jSONObject.put("destLang", destLang);
        String string = jSONObject.toString();
        string.getClass();
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            hw0 hw0Var = this.service;
            if (hw0Var != null ? ((fw0) hw0Var).r(4) : false) {
                hw0 hw0Var2 = this.service;
                String strQ = hw0Var2 != null ? ((fw0) hw0Var2).q(4, string) : null;
                Log.i(TAG, "doFunc: " + hn.j(4) + ", params: " + string + ", result: " + strQ + ", span: " + (System.currentTimeMillis() - jCurrentTimeMillis));
                if (strQ != null) {
                    if (Boolean.class.equals(Boolean.class)) {
                        objValueOf = Boolean.valueOf(Boolean.parseBoolean(strQ));
                    } else if (Boolean.class.equals(Integer.class)) {
                        objValueOf = Integer.valueOf(Integer.parseInt(strQ));
                    } else if (Boolean.class.equals(Long.class)) {
                        objValueOf = Long.valueOf(Long.parseLong(strQ));
                    } else if (Boolean.class.equals(Float.class)) {
                        objValueOf = Float.valueOf(Float.parseFloat(strQ));
                    } else if (Boolean.class.equals(Double.class)) {
                        objValueOf = strQ;
                        objValueOf = Double.valueOf(Double.parseDouble(strQ));
                    }
                    objValueOf = strQ;
                    bool = (Boolean) objValueOf;
                }
            } else {
                Log.i(TAG, "doFunc: " + hn.j(4) + ", func disabled !");
            }
        } catch (Exception e) {
            onError(null, 2002, "doFunc: " + hn.j(4) + " exception: " + e);
        }
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public final boolean checkIfCtaAllowed() {
        hw0 hw0Var = this.service;
        boolean z = false;
        if (hw0Var != null) {
            fw0 fw0Var = (fw0) hw0Var;
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken("com.xiaomi.aiasst.vision.sdk.ITranslateSdkInterface");
                fw0Var.a.transact(6, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                z = parcelObtain2.readInt() != 0;
                parcelObtain2.recycle();
                parcelObtain.recycle();
            } catch (Throwable th) {
                parcelObtain2.recycle();
                parcelObtain.recycle();
                throw th;
            }
        }
        Log.d(TAG, "checkIfCtaAllowed: " + z);
        return z;
    }

    public final boolean checkModelExist(String srcLang, String destLang) throws JSONException {
        JSONObject jSONObject;
        Object objValueOf;
        srcLang.getClass();
        Boolean bool = null;
        String str = true & true ? a.c : null;
        try {
            if (str == null) {
                str = a.c;
            }
            jSONObject = new JSONObject(str);
        } catch (Exception unused) {
            jSONObject = new JSONObject();
        }
        jSONObject.put("srcLang", srcLang);
        if (destLang == null) {
            destLang = a.c;
        }
        jSONObject.put("destLang", destLang);
        String string = jSONObject.toString();
        string.getClass();
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            hw0 hw0Var = this.service;
            if (hw0Var != null ? ((fw0) hw0Var).r(2) : false) {
                hw0 hw0Var2 = this.service;
                String strQ = hw0Var2 != null ? ((fw0) hw0Var2).q(2, string) : null;
                Log.i(TAG, "doFunc: " + hn.j(2) + ", params: " + string + ", result: " + strQ + ", span: " + (System.currentTimeMillis() - jCurrentTimeMillis));
                if (strQ != null) {
                    if (Boolean.class.equals(Boolean.class)) {
                        objValueOf = Boolean.valueOf(Boolean.parseBoolean(strQ));
                    } else if (Boolean.class.equals(Integer.class)) {
                        objValueOf = Integer.valueOf(Integer.parseInt(strQ));
                    } else if (Boolean.class.equals(Long.class)) {
                        objValueOf = Long.valueOf(Long.parseLong(strQ));
                    } else if (Boolean.class.equals(Float.class)) {
                        objValueOf = Float.valueOf(Float.parseFloat(strQ));
                    } else if (Boolean.class.equals(Double.class)) {
                        objValueOf = strQ;
                        objValueOf = Double.valueOf(Double.parseDouble(strQ));
                    }
                    objValueOf = strQ;
                    bool = (Boolean) objValueOf;
                }
            } else {
                Log.i(TAG, "doFunc: " + hn.j(2) + ", func disabled !");
            }
        } catch (Exception e) {
            onError(null, 2002, "doFunc: " + hn.j(2) + " exception: " + e);
        }
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public final void connect(int retryCount) {
        try {
            Context context = this.context;
            Intent intent = new Intent(ACTION_TRANSLATE_SDK_SERVICE);
            intent.setPackage(PKG_AI_ASST_VISION);
            intent.putExtra("from", this.context.getPackageName());
            boolean zBindService = context.bindService(intent, this.serviceConnection, 1);
            if (!zBindService) {
                if (retryCount >= 3) {
                    CoreCallback coreCallback = this.coreCallback;
                    if (coreCallback != null) {
                        coreCallback.onError(1000, "connect ai asst vision service fail！");
                    }
                } else {
                    Handler handler = this.uiHandler;
                    x6 x6Var = new x6(0);
                    x6Var.d = this;
                    x6Var.c = retryCount;
                    VarHandle.storeStoreFence();
                    long j = ((long) retryCount) * 1000;
                    if (j > 3000) {
                        j = 3000;
                    }
                    handler.postDelayed(x6Var, j);
                }
            }
            StringBuilder sb = new StringBuilder("bindService ");
            sb.append(zBindService ? "success" : "fail");
            sb.append("!, retryCount: ");
            sb.append(retryCount);
            Log.d(TAG, sb.toString());
        } catch (Exception e) {
            CoreCallback coreCallback2 = this.coreCallback;
            if (coreCallback2 != null) {
                coreCallback2.onError(1000, "connect ai asst vision service exception: " + e + " !");
            }
        }
    }

    public final CoreCallback getCoreCallback() {
        return this.coreCallback;
    }

    public final boolean isSupportOfflineRecognize() {
        Object objValueOf;
        Boolean bool = null;
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            hw0 hw0Var = this.service;
            if (hw0Var != null ? ((fw0) hw0Var).r(1) : false) {
                hw0 hw0Var2 = this.service;
                String strQ = hw0Var2 != null ? ((fw0) hw0Var2).q(1, a.c) : null;
                Log.i(TAG, "doFunc: " + hn.j(1) + ", params: , result: " + strQ + ", span: " + (System.currentTimeMillis() - jCurrentTimeMillis));
                if (strQ != null) {
                    if (Boolean.class.equals(Boolean.class)) {
                        objValueOf = Boolean.valueOf(Boolean.parseBoolean(strQ));
                    } else if (Boolean.class.equals(Integer.class)) {
                        objValueOf = Integer.valueOf(Integer.parseInt(strQ));
                    } else if (Boolean.class.equals(Long.class)) {
                        objValueOf = Long.valueOf(Long.parseLong(strQ));
                    } else if (Boolean.class.equals(Float.class)) {
                        objValueOf = Float.valueOf(Float.parseFloat(strQ));
                    } else if (Boolean.class.equals(Double.class)) {
                        objValueOf = strQ;
                        objValueOf = Double.valueOf(Double.parseDouble(strQ));
                    }
                    objValueOf = strQ;
                    bool = (Boolean) objValueOf;
                }
            } else {
                Log.i(TAG, "doFunc: " + hn.j(1) + ", func disabled !");
            }
        } catch (Exception e) {
            onError(null, 2002, "doFunc: " + hn.j(1) + " exception: " + e);
        }
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public final void monitorServerState(IInterface service, boolean monitor) {
        if (service == null) {
            return;
        }
        try {
            IBinder iBinderAsBinder = service.asBinder();
            if (monitor) {
                if (iBinderAsBinder != null) {
                    iBinderAsBinder.linkToDeath(this.deathRecipient, 0);
                }
            } else if (iBinderAsBinder != null) {
                iBinderAsBinder.unlinkToDeath(this.deathRecipient, 0);
            }
        } catch (Exception e) {
            Log.e(TAG, "monitorServerState, e: " + e);
        }
    }

    public final boolean postData(byte[] pcm, boolean isLastFrame, String dialogId) {
        pcm.getClass();
        dialogId.getClass();
        hw0 hw0Var = this.service;
        if (hw0Var == null) {
            return false;
        }
        fw0 fw0Var = (fw0) hw0Var;
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.xiaomi.aiasst.vision.sdk.ITranslateSdkInterface");
            parcelObtain.writeByteArray(pcm);
            parcelObtain.writeInt(isLastFrame ? 1 : 0);
            parcelObtain.writeString(dialogId);
            fw0Var.a.transact(11, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            return parcelObtain2.readInt() != 0;
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    public final void release(String reason) {
        reason.getClass();
        this.uiHandler.removeCallbacksAndMessages(null);
        hw0 hw0Var = this.service;
        if (hw0Var != null) {
            fw0 fw0Var = (fw0) hw0Var;
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken("com.xiaomi.aiasst.vision.sdk.ITranslateSdkInterface");
                fw0Var.a.transact(13, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                parcelObtain2.recycle();
                parcelObtain.recycle();
            } catch (Throwable th) {
                parcelObtain2.recycle();
                parcelObtain.recycle();
                throw th;
            }
        }
        hw0 hw0Var2 = this.service;
        if (hw0Var2 != null) {
            ((fw0) hw0Var2).s(null);
        }
        disconnect(reason);
        this.mCtaCallback = null;
        this.mRecognizeCallbackMap.clear();
        this.mTtsCallbackMap.clear();
        this.mTextTranslateCallbackMap.clear();
        this.mErrorCallbackMap.clear();
        this.service = null;
    }

    public final void requestCtaAccredit(CtaCallback callback) {
        callback.getClass();
        hw0 hw0Var = this.service;
        if (hw0Var != null) {
            fw0 fw0Var = (fw0) hw0Var;
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken("com.xiaomi.aiasst.vision.sdk.ITranslateSdkInterface");
                fw0Var.a.transact(7, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                parcelObtain2.readInt();
                parcelObtain2.recycle();
                parcelObtain.recycle();
            } catch (Throwable th) {
                parcelObtain2.recycle();
                parcelObtain.recycle();
                throw th;
            }
        }
        this.mCtaCallback = callback;
    }

    /* JADX WARN: Code duplicated, block: B:53:0x0134  */
    /* JADX WARN: Code duplicated, block: B:55:0x013a  */
    /* JADX WARN: Code duplicated, block: B:57:0x015e  */
    /* JADX WARN: Code duplicated, block: B:62:0x017c  */
    /* JADX WARN: Code duplicated, block: B:65:0x0187  */
    /* JADX WARN: Code duplicated, block: B:68:0x0198  */
    /* JADX WARN: Code duplicated, block: B:70:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:71:0x01ad  */
    /* JADX WARN: Code duplicated, block: B:73:0x01b5  */
    /* JADX WARN: Code duplicated, block: B:74:0x01c0  */
    /* JADX WARN: Code duplicated, block: B:76:0x01c8  */
    /* JADX WARN: Code duplicated, block: B:77:0x01d3  */
    /* JADX WARN: Code duplicated, block: B:79:0x01d9  */
    /* JADX WARN: Code duplicated, block: B:84:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:88:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:55:0x013a, please report this as an issue */
    public final void startDownloadModel(boolean silentUpdate, String srcLang, String destLang, DownloadModelCallback downloadModeCallback, ErrorCallback errorCallback) throws JSONException {
        JSONObject jSONObject;
        Boolean bool;
        String str;
        List<DownloadModelCallback> arrayList;
        Object objValueOf;
        srcLang.getClass();
        downloadModeCallback.getClass();
        boolean z = true & true;
        String str2 = a.c;
        String str3 = z ? a.c : null;
        try {
            if (str3 == null) {
                str3 = a.c;
            }
            jSONObject = new JSONObject(str3);
        } catch (Exception unused) {
            jSONObject = new JSONObject();
        }
        jSONObject.put("srcLang", srcLang);
        if (destLang != null) {
            str2 = destLang;
        }
        jSONObject.put("destLang", str2);
        jSONObject.put("silentUpdate", Boolean.valueOf(silentUpdate));
        String string = jSONObject.toString();
        string.getClass();
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            hw0 hw0Var = this.service;
            if (hw0Var != null ? ((fw0) hw0Var).r(3) : false) {
                hw0 hw0Var2 = this.service;
                String strQ = hw0Var2 != null ? ((fw0) hw0Var2).q(3, string) : null;
                Log.i(TAG, "doFunc: " + hn.j(3) + ", params: " + string + ", result: " + strQ + ", span: " + (System.currentTimeMillis() - jCurrentTimeMillis));
                if (strQ != null) {
                    if (Boolean.class.equals(Boolean.class)) {
                        objValueOf = Boolean.valueOf(Boolean.parseBoolean(strQ));
                    } else if (Boolean.class.equals(Integer.class)) {
                        objValueOf = Integer.valueOf(Integer.parseInt(strQ));
                    } else if (Boolean.class.equals(Long.class)) {
                        objValueOf = Long.valueOf(Long.parseLong(strQ));
                    } else if (Boolean.class.equals(Float.class)) {
                        objValueOf = Float.valueOf(Float.parseFloat(strQ));
                    } else if (Boolean.class.equals(Double.class)) {
                        objValueOf = strQ;
                        objValueOf = Double.valueOf(Double.parseDouble(strQ));
                    }
                    objValueOf = strQ;
                    bool = (Boolean) objValueOf;
                }
                if (!(bool != null ? bool.booleanValue() : false)) {
                    onError(errorCallback, 2002, "startDownloadModel fail, silentUpdate: " + silentUpdate + ", srcLang: " + srcLang + ", destLang: " + destLang);
                    return;
                }
                if (destLang != null || destLang.length() == 0) {
                    str = srcLang;
                } else {
                    str = srcLang + ',' + destLang;
                }
                arrayList = this.mDownloadModelCallbackMap.get(str);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                    this.mDownloadModelCallbackMap.put(str, arrayList);
                }
                arrayList.add(downloadModeCallback);
                if (errorCallback != null) {
                    if (ErrorCallback.class.equals(TtsCallback.class)) {
                        this.mTtsCallbackMap.put(str, (TtsCallback) errorCallback);
                        return;
                    }
                    if (ErrorCallback.class.equals(SpeechRecognizeCallback.class)) {
                        this.mRecognizeCallbackMap.put(str, (SpeechRecognizeCallback) errorCallback);
                    } else if (ErrorCallback.class.equals(TextTranslateCallback.class)) {
                        this.mTextTranslateCallbackMap.put(str, (TextTranslateCallback) errorCallback);
                    } else if (ErrorCallback.class.equals(ErrorCallback.class)) {
                        this.mErrorCallbackMap.put(str, errorCallback);
                    }
                }
            }
            Log.i(TAG, "doFunc: " + hn.j(3) + ", func disabled !");
        } catch (Exception e) {
            onError(errorCallback, 2002, "doFunc: " + hn.j(3) + " exception: " + e);
        }
        bool = null;
        if (!(bool != null ? bool.booleanValue() : false)) {
            onError(errorCallback, 2002, "startDownloadModel fail, silentUpdate: " + silentUpdate + ", srcLang: " + srcLang + ", destLang: " + destLang);
            return;
        }
        if (destLang != null) {
            str = srcLang;
        } else {
            str = srcLang;
        }
        arrayList = this.mDownloadModelCallbackMap.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.mDownloadModelCallbackMap.put(str, arrayList);
        }
        arrayList.add(downloadModeCallback);
        if (errorCallback != null) {
            if (ErrorCallback.class.equals(TtsCallback.class)) {
                this.mTtsCallbackMap.put(str, (TtsCallback) errorCallback);
                return;
            }
            if (ErrorCallback.class.equals(SpeechRecognizeCallback.class)) {
                this.mRecognizeCallbackMap.put(str, (SpeechRecognizeCallback) errorCallback);
            } else if (ErrorCallback.class.equals(TextTranslateCallback.class)) {
                this.mTextTranslateCallbackMap.put(str, (TextTranslateCallback) errorCallback);
            } else if (ErrorCallback.class.equals(ErrorCallback.class)) {
                this.mErrorCallbackMap.put(str, errorCallback);
            }
        }
    }

    public final void startRecognize(int scenarioType, int recognizeMode, int startMode, int channel, String srcLang, String destLang, boolean identifyLanguage, boolean vad, SpeechRecognizeCallback recognizeCallback, TtsCallback ttsCallback, ErrorCallback errorCallback) {
        srcLang.getClass();
        recognizeCallback.getClass();
        if (recognizeMode == 0 && (channel < 0 || channel >= 2)) {
            cw1.f("channel must be 0 or 1.");
            return;
        }
        boolean z = ttsCallback != null;
        try {
            hw0 hw0Var = this.service;
            String strT = hw0Var != null ? ((fw0) hw0Var).t(scenarioType, recognizeMode, startMode, channel, srcLang, destLang, z, identifyLanguage, vad) : null;
            if (strT == null || strT.length() == 0) {
                onError(errorCallback, 2002, "start recognize fail, dialogId is empty");
                return;
            }
            if (SpeechRecognizeCallback.class.equals(TtsCallback.class)) {
                this.mTtsCallbackMap.put(strT, (TtsCallback) recognizeCallback);
            } else if (SpeechRecognizeCallback.class.equals(SpeechRecognizeCallback.class)) {
                this.mRecognizeCallbackMap.put(strT, recognizeCallback);
            } else if (SpeechRecognizeCallback.class.equals(TextTranslateCallback.class)) {
                this.mTextTranslateCallbackMap.put(strT, (TextTranslateCallback) recognizeCallback);
            } else if (SpeechRecognizeCallback.class.equals(ErrorCallback.class)) {
                this.mErrorCallbackMap.put(strT, (ErrorCallback) recognizeCallback);
            }
            if (z) {
                if (TtsCallback.class.equals(TtsCallback.class)) {
                    if (ttsCallback != null) {
                        this.mTtsCallbackMap.put(strT, ttsCallback);
                    } else {
                        this.mTtsCallbackMap.remove(strT);
                    }
                } else if (TtsCallback.class.equals(SpeechRecognizeCallback.class)) {
                    if (ttsCallback != null) {
                        this.mRecognizeCallbackMap.put(strT, (SpeechRecognizeCallback) ttsCallback);
                    } else {
                        this.mRecognizeCallbackMap.remove(strT);
                    }
                } else if (TtsCallback.class.equals(TextTranslateCallback.class)) {
                    if (ttsCallback != null) {
                        this.mTextTranslateCallbackMap.put(strT, (TextTranslateCallback) ttsCallback);
                    } else {
                        this.mTextTranslateCallbackMap.remove(strT);
                    }
                } else if (TtsCallback.class.equals(ErrorCallback.class)) {
                    if (ttsCallback != null) {
                        this.mErrorCallbackMap.put(strT, (ErrorCallback) ttsCallback);
                    } else {
                        this.mErrorCallbackMap.remove(strT);
                    }
                }
            }
            if (errorCallback != null) {
                if (ErrorCallback.class.equals(TtsCallback.class)) {
                    this.mTtsCallbackMap.put(strT, (TtsCallback) errorCallback);
                } else if (ErrorCallback.class.equals(SpeechRecognizeCallback.class)) {
                    this.mRecognizeCallbackMap.put(strT, (SpeechRecognizeCallback) errorCallback);
                } else if (ErrorCallback.class.equals(TextTranslateCallback.class)) {
                    this.mTextTranslateCallbackMap.put(strT, (TextTranslateCallback) errorCallback);
                } else if (ErrorCallback.class.equals(ErrorCallback.class)) {
                    this.mErrorCallbackMap.put(strT, errorCallback);
                }
            }
            recognizeCallback.onRecognizeStart(strT);
        } catch (Exception e) {
            onError(errorCallback, 2002, "start recognize exception: " + e);
        }
    }

    public final void startTextTranslate(int startMode, String text, String srcLang, String destLang, TextTranslateCallback textTranslateCallback, ErrorCallback errorCallback) {
        text.getClass();
        srcLang.getClass();
        destLang.getClass();
        textTranslateCallback.getClass();
        try {
            hw0 hw0Var = this.service;
            String strU = hw0Var != null ? ((fw0) hw0Var).u(startMode, text, srcLang, destLang) : null;
            if (strU == null || strU.length() == 0) {
                onError(errorCallback, 2002, "start text translate fail, dialogId is empty");
                return;
            }
            if (TextTranslateCallback.class.equals(TtsCallback.class)) {
                this.mTtsCallbackMap.put(strU, (TtsCallback) textTranslateCallback);
            } else if (TextTranslateCallback.class.equals(SpeechRecognizeCallback.class)) {
                this.mRecognizeCallbackMap.put(strU, (SpeechRecognizeCallback) textTranslateCallback);
            } else if (TextTranslateCallback.class.equals(TextTranslateCallback.class)) {
                this.mTextTranslateCallbackMap.put(strU, textTranslateCallback);
            } else if (TextTranslateCallback.class.equals(ErrorCallback.class)) {
                this.mErrorCallbackMap.put(strU, (ErrorCallback) textTranslateCallback);
            }
            if (errorCallback != null) {
                if (ErrorCallback.class.equals(TtsCallback.class)) {
                    this.mTtsCallbackMap.put(strU, (TtsCallback) errorCallback);
                    return;
                }
                if (ErrorCallback.class.equals(SpeechRecognizeCallback.class)) {
                    this.mRecognizeCallbackMap.put(strU, (SpeechRecognizeCallback) errorCallback);
                } else if (ErrorCallback.class.equals(TextTranslateCallback.class)) {
                    this.mTextTranslateCallbackMap.put(strU, (TextTranslateCallback) errorCallback);
                } else if (ErrorCallback.class.equals(ErrorCallback.class)) {
                    this.mErrorCallbackMap.put(strU, errorCallback);
                }
            }
        } catch (Exception e) {
            onError(errorCallback, 2002, "start text translate exception: " + e);
        }
    }

    public final void startTts(int startMode, String text, String srcLang, TtsCallback ttsCallback, ErrorCallback errorCallback) {
        text.getClass();
        srcLang.getClass();
        ttsCallback.getClass();
        try {
            hw0 hw0Var = this.service;
            String strV = hw0Var != null ? ((fw0) hw0Var).v(startMode, text, srcLang) : null;
            if (strV == null || strV.length() == 0) {
                onError(errorCallback, 2002, "start tts fail, dialogId is empty");
                return;
            }
            if (TtsCallback.class.equals(TtsCallback.class)) {
                this.mTtsCallbackMap.put(strV, ttsCallback);
            } else if (TtsCallback.class.equals(SpeechRecognizeCallback.class)) {
                this.mRecognizeCallbackMap.put(strV, (SpeechRecognizeCallback) ttsCallback);
            } else if (TtsCallback.class.equals(TextTranslateCallback.class)) {
                this.mTextTranslateCallbackMap.put(strV, (TextTranslateCallback) ttsCallback);
            } else if (TtsCallback.class.equals(ErrorCallback.class)) {
                this.mErrorCallbackMap.put(strV, (ErrorCallback) ttsCallback);
            }
            if (errorCallback != null) {
                if (ErrorCallback.class.equals(TtsCallback.class)) {
                    this.mTtsCallbackMap.put(strV, (TtsCallback) errorCallback);
                    return;
                }
                if (ErrorCallback.class.equals(SpeechRecognizeCallback.class)) {
                    this.mRecognizeCallbackMap.put(strV, (SpeechRecognizeCallback) errorCallback);
                } else if (ErrorCallback.class.equals(TextTranslateCallback.class)) {
                    this.mTextTranslateCallbackMap.put(strV, (TextTranslateCallback) errorCallback);
                } else if (ErrorCallback.class.equals(ErrorCallback.class)) {
                    this.mErrorCallbackMap.put(strV, errorCallback);
                }
            }
        } catch (Exception e) {
            onError(errorCallback, 2002, "start tts exception: " + e);
        }
    }

    public final void stopRecognize(String dialogId) {
        dialogId.getClass();
        hw0 hw0Var = this.service;
        if (hw0Var != null) {
            fw0 fw0Var = (fw0) hw0Var;
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken("com.xiaomi.aiasst.vision.sdk.ITranslateSdkInterface");
                parcelObtain.writeString(dialogId);
                fw0Var.a.transact(12, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }
    }

    /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x0005: CONSTRUCTOR 
      (r1v0 android.content.Context)
      (wrap com.xiaomi.ai.vision.sdk.callback.CoreCallback:?: TERNARY null = ((wrap int:0x0000: ARITH (r3v0 int) & (2 int) A[WRAPPED]) != (0 int)) ? (null com.xiaomi.ai.vision.sdk.callback.CoreCallback) : (r2v0 com.xiaomi.ai.vision.sdk.callback.CoreCallback))
     A[MD:(android.content.Context, com.xiaomi.ai.vision.sdk.callback.CoreCallback):void (m)] (LINE:85) call: com.xiaomi.ai.vision.sdk.AiCore.<init>(android.content.Context, com.xiaomi.ai.vision.sdk.callback.CoreCallback):void type: THIS */
    public /* synthetic */ AiCore(Context context, CoreCallback coreCallback, int i, c60 c60Var) {
        this(context, (i & 2) != 0 ? null : coreCallback);
    }
}
