package com.miui.guardprovider.aiguard;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.DynamicEffect;
import android.os.HapticPlayer;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.miui.guardprovider.R;
import com.xiaomi.onetrack.api.al;
import defpackage.c8;
import defpackage.cw1;
import defpackage.d8;
import defpackage.im2;
import defpackage.jc0;
import defpackage.k00;
import defpackage.l00;
import defpackage.od;
import defpackage.qy1;
import defpackage.ro;
import defpackage.wu2;
import defpackage.xl1;
import defpackage.yu2;
import java.lang.invoke.VarHandle;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import miuix.appcompat.app.d;
import org.json.JSONObject;
import tmsdk.common.roach.nest.PowerNest;

/* JADX INFO: loaded from: classes.dex */
public class AlertActivity extends od {
    public int E;
    public String F;
    public wu2 G;

    public static final class a extends jc0 {
        public HapticPlayer j0;
        public yu2 k0;

        @Override // defpackage.jc0, android.content.DialogInterface.OnDismissListener
        public final void onDismiss(DialogInterface dialogInterface) {
            HapticPlayer hapticPlayer = this.j0;
            if (hapticPlayer != null) {
                hapticPlayer.stop();
                this.j0 = null;
            }
        }

        @Override // defpackage.jc0
        public final Dialog w(Bundle bundle) {
            AlertActivity alertActivity = (AlertActivity) q();
            int i = alertActivity.E;
            this.k0 = alertActivity.G;
            if (bundle == null) {
                HapticPlayer hapticPlayer = new HapticPlayer(DynamicEffect.create("{\n  \"Metadata\": {\n    \"Version\": 1,\n    \"Created\": \"2024-09-10\",\n    \"Description\": \"alert haptic\"\n  },\n  \"Pattern\": [\n    {\n      \"Event\": {\n        \"Type\": \"continuous\",\n        \"RelativeTime\": 0,\n        \"Duration\": 3000,\n        \"Parameters\": {\n          \"Intensity\": 100,\n          \"Frequency\": 50,\n          \"Curve\": [\n            {\n              \"Time\": 0,\n              \"Intensity\": 0,\n              \"Frequency\": 0\n            },\n            {\n              \"Time\": 1,\n              \"Intensity\": 1.0,\n              \"Frequency\": 0\n            },\n            {\n              \"Time\": 2999,\n              \"Intensity\": 1.0,\n              \"Frequency\": 0\n            },\n            {\n              \"Time\": 3000,\n              \"Intensity\": 0,\n              \"Frequency\": 0\n            }\n          ]\n        }\n      }\n    }\n  ]\n}"));
                this.j0 = hapticPlayer;
                hapticPlayer.start();
                ((wu2) this.k0).a.track("expose", xl1.G(new qy1[]{new qy1(al.ae, "1127.1.0.1.37701"), new qy1("trigger_scenario", alertActivity.F)}));
            }
            int i2 = 0;
            this.Z = false;
            Dialog dialog = this.e0;
            if (dialog != null) {
                dialog.setCancelable(false);
            }
            d dVar = new d(alertActivity, R.style.AlertDialog_Theme_DayNight);
            c8 c8Var = new c8(i2);
            c8Var.c = this;
            c8Var.d = alertActivity;
            VarHandle.storeStoreFence();
            dVar.k(R.string.ai_guard_alert_neg_btn, c8Var);
            if (i == 1073741824) {
                c8 c8Var2 = new c8(1);
                c8Var2.c = this;
                c8Var2.d = alertActivity;
                VarHandle.storeStoreFence();
                dVar.n(R.string.ai_guard_alert_pos_btn, c8Var2);
            }
            LayoutInflater layoutInflaterP = this.J;
            if (layoutInflaterP == null) {
                layoutInflaterP = p(null);
            }
            View viewInflate = layoutInflaterP.inflate(R.layout.scam_alert_dialog, (ViewGroup) null, false);
            TextView textView = (TextView) viewInflate.findViewById(R.id.spannable);
            TextView textView2 = (TextView) viewInflate.findViewById(R.id.message);
            if ((i & 268435456) != 0) {
                textView2.setText(R.string.ai_guard_alert_message_3);
                c8 c8Var3 = new c8(2);
                c8Var3.c = this;
                c8Var3.d = alertActivity;
                VarHandle.storeStoreFence();
                dVar.k(R.string.ai_guard_alert_neu_btn, c8Var3);
            }
            textView.setText(Html.fromHtml(s().getResources().getString(R.string.ai_guard_alert_info), 63));
            dVar.q(viewInflate);
            return dVar.a();
        }

        public final void y(boolean z) {
            v(false, false);
            Bundle bundle = new Bundle();
            bundle.putBoolean("AlertActivity_Feedback_voiceCall", z);
            b bVar = new b();
            bVar.u(bundle);
            bVar.x(f(), "AlertActivity_Feedback");
        }
    }

    public static final class b extends jc0 {
        public yu2 j0;
        public ro k0;
        public boolean l0;
        public final d8 m0;

        public b() {
            d8 d8Var = new d8(2);
            d8Var.c = this;
            VarHandle.storeStoreFence();
            this.m0 = d8Var;
        }

        @Override // defpackage.jc0, android.content.DialogInterface.OnCancelListener
        public final void onCancel(DialogInterface dialogInterface) {
            this.m0.onClick(dialogInterface, -2);
        }

        @Override // defpackage.jc0
        public final Dialog w(Bundle bundle) {
            AlertActivity alertActivity = (AlertActivity) q();
            this.l0 = r().getBoolean("AlertActivity_Feedback_voiceCall", true);
            wu2 wu2Var = alertActivity.G;
            this.j0 = wu2Var;
            if (bundle == null) {
                wu2Var.a.track("expose", xl1.G(new qy1[]{new qy1(al.ae, "1127.1.0.1.45357"), new qy1("trigger_scenario", alertActivity.F)}));
            }
            int i = this.l0 ? R.string.ai_guard_false_positive_feedback_message : R.string.ai_guard_false_positive_feedback_message_1;
            d dVar = new d(alertActivity, R.style.AlertDialog_Theme_DayNight);
            dVar.o(R.string.ai_guard_false_positive_feedback_title);
            dVar.i(i);
            d8 d8Var = this.m0;
            dVar.k(android.R.string.cancel, d8Var);
            dVar.n(R.string.ai_guard_false_positive_feedback_pos_btn, d8Var);
            return dVar.a();
        }

        /* JADX DEBUG: Multi-variable search result rejected for r4v2, resolved type: l00 */
        /* JADX WARN: Multi-variable type inference failed */
        public final String y(Path path, String str) throws k00 {
            boolean zIsTerminated;
            this.k0.getClass();
            l00 l00VarJ = ro.J();
            try {
                String strEncodeToString = Base64.getEncoder().encodeToString(Files.readAllBytes(path));
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("requestId", UUID.randomUUID().toString());
                jSONObject.put("audioPCM", strEncodeToString);
                jSONObject.put("audioText", str);
                String json = l00VarJ.b(jSONObject.toString().getBytes()).toJson();
                if (l00VarJ instanceof AutoCloseable) {
                    l00VarJ.close();
                    return json;
                }
                if (!(l00VarJ instanceof ExecutorService)) {
                    cw1.a();
                    return null;
                }
                ExecutorService executorService = (ExecutorService) l00VarJ;
                if (executorService != ForkJoinPool.commonPool() && !(zIsTerminated = executorService.isTerminated())) {
                    executorService.shutdown();
                    boolean z = false;
                    while (!zIsTerminated) {
                        try {
                            zIsTerminated = executorService.awaitTermination(1L, TimeUnit.DAYS);
                        } catch (InterruptedException unused) {
                            if (!z) {
                                executorService.shutdownNow();
                                z = true;
                            }
                        }
                    }
                    if (z) {
                        Thread.currentThread().interrupt();
                    }
                }
                return json;
            } catch (Throwable th) {
                try {
                    im2.w(l00VarJ);
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r5v2, resolved type: l00 */
        /* JADX WARN: Multi-variable type inference failed */
        public final String z(Path path) throws k00 {
            boolean zIsTerminated;
            this.k0.getClass();
            l00 l00VarJ = ro.J();
            try {
                String strEncodeToString = Base64.getEncoder().encodeToString(Files.readAllBytes(path));
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("fileType", "MP4");
                jSONObject.put("requestId", UUID.randomUUID().toString());
                jSONObject.put("fileBase64", strEncodeToString);
                String json = l00VarJ.b(jSONObject.toString().getBytes()).toJson();
                if (l00VarJ instanceof AutoCloseable) {
                    l00VarJ.close();
                    return json;
                }
                if (!(l00VarJ instanceof ExecutorService)) {
                    cw1.a();
                    return null;
                }
                ExecutorService executorService = (ExecutorService) l00VarJ;
                if (executorService != ForkJoinPool.commonPool() && !(zIsTerminated = executorService.isTerminated())) {
                    executorService.shutdown();
                    boolean z = false;
                    while (!zIsTerminated) {
                        try {
                            zIsTerminated = executorService.awaitTermination(1L, TimeUnit.DAYS);
                        } catch (InterruptedException unused) {
                            if (!z) {
                                executorService.shutdownNow();
                                z = true;
                            }
                        }
                    }
                    if (z) {
                        Thread.currentThread().interrupt();
                    }
                }
                return json;
            } catch (Throwable th) {
                try {
                    im2.w(l00VarJ);
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }

    @Override // defpackage.od, defpackage.uz, android.app.Activity
    public final void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        Window window = getWindow();
        window.clearFlags(PowerNest.SHARK_TCP);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.layoutInDisplayCutoutMode = 1;
        window.setAttributes(attributes);
        this.G = ro.I(this);
        int intExtra = getIntent().getIntExtra("alert_cause", 268435456);
        this.E = intExtra;
        if (intExtra == 536870913) {
            str = "QQ语音通话";
        } else if (intExtra == 268435457) {
            str = "QQ视频通话";
        } else if (intExtra == 536870914) {
            str = "微信语音通话";
        } else {
            str = intExtra == 268435458 ? "微信视频通话" : "电话";
        }
        this.F = str;
        Log.i("AlertActivity", "alert cause: " + this.E);
        if (bundle == null) {
            new a().x(w(), "AlertActivity_Alert");
        }
    }
}
