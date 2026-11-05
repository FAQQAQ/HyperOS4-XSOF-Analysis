package defpackage;

import com.miui.guardprovider.aiguard.service.FeedbackResponse;

/* JADX INFO: loaded from: classes.dex */
public interface oj0 {
    @by1("/aiVideo/pcc/rbi")
    @qn0
    lq<FeedbackResponse> a(@qj0("data") String str);

    @by1("/ai/audio/text/pcc/rbi")
    @qn0
    lq<FeedbackResponse> b(@qj0("data") String str);
}
