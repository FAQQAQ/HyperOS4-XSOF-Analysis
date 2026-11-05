package eo;

import aj.c0;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

/* JADX INFO: loaded from: classes4.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f30043a;

    public b(Context context) {
        this.f30043a = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(co.a aVar) {
        Bundle bundle = new Bundle();
        try {
            bundle = this.f30043a.getContentResolver().call(Uri.parse("content://guard"), "getRiskAppData", "", (Bundle) null);
        } catch (Exception e10) {
            Log.e("RiskAppManager", "Risk app scan Failed", e10);
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        aVar.c(bundle.getString("riskData", ""), 0);
    }

    public void b(final co.a aVar) {
        c0.e().b(new Runnable() { // from class: eo.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f30041a.c(aVar);
            }
        });
    }
}
