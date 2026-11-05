package defpackage;

import android.content.Context;
import android.util.Log;
import com.miui.guardprovider.aiguard.AlertActivity;
import com.miui.guardprovider.aiguard.service.FeedbackResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e8 implements Runnable {
    public /* synthetic */ AlertActivity.b b;
    public /* synthetic */ boolean c;
    public /* synthetic */ pj0 d;
    public /* synthetic */ oj0 e;
    public /* synthetic */ Context f;

    @Override // java.lang.Runnable
    public final void run() {
        AlertActivity.b bVar = this.b;
        boolean z = this.c;
        pj0 pj0Var = this.d;
        oj0 oj0Var = this.e;
        Context context = this.f;
        try {
            if (z) {
                Log.i("AlertActivity_Feedback", "submitFeedback: " + ((FeedbackResponse) oj0Var.b(bVar.y(pj0Var.a, pj0Var.b.toString())).execute().b));
            } else {
                Path path = pj0Var.c;
                if (Files.exists(path, new LinkOption[0])) {
                    Log.i("AlertActivity_Feedback", "submitFeedback: " + ((FeedbackResponse) oj0Var.a(bVar.z(path)).execute().b));
                }
            }
            pj0 pj0VarP = n53.p(context);
            try {
                Files.deleteIfExists(pj0VarP.a);
                Files.deleteIfExists(pj0VarP.c);
            } catch (IOException unused) {
            }
        } catch (Exception e) {
            Log.e("AlertActivity_Feedback", "submitFeedback: ", e);
        }
    }
}
