package com.miui.guardprovider.aiguard;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.text.Spanned;
import android.util.TypedValue;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.lifecycle.a;
import com.miui.guardprovider.R;
import com.xiaomi.ai.vision.sdk.AiCapability;
import defpackage.a02;
import defpackage.ai1;
import defpackage.b6;
import defpackage.c0;
import defpackage.c6;
import defpackage.c8;
import defpackage.mi;
import defpackage.nh1;
import defpackage.o8;
import defpackage.od;
import defpackage.tz;
import defpackage.w2;
import defpackage.x5;
import defpackage.xz1;
import defpackage.yz1;
import defpackage.z5;
import defpackage.zz1;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Locale;
import miuix.appcompat.app.d;
import miuix.appcompat.app.e;
import miuix.core.util.SystemProperties;
import tmsdk.common.roach.nest.PowerNest;

/* JADX INFO: loaded from: classes.dex */
public final class PermissionRequestActivity extends od {
    public static final /* synthetic */ int F = 0;
    public final c6 E;

    public PermissionRequestActivity() {
        x5 x5Var = new x5();
        w2 w2Var = new w2(5);
        w2Var.c = this;
        VarHandle.storeStoreFence();
        tz tzVar = this.j;
        tzVar.getClass();
        String str = "activity_rq#" + this.i.getAndIncrement();
        LinkedHashMap linkedHashMap = tzVar.c;
        a aVar = this.b;
        if (aVar.c.compareTo(nh1.e) >= 0) {
            StringBuilder sb = new StringBuilder("LifecycleOwner ");
            sb.append(this);
            nh1 nh1Var = aVar.c;
            sb.append(" is attempting to register while current state is ");
            sb.append(nh1Var);
            sb.append(". LifecycleOwners must call register before they are STARTED.");
            throw new IllegalStateException(sb.toString().toString());
        }
        tzVar.d(str);
        b6 b6Var = (b6) linkedHashMap.get(str);
        if (b6Var == null) {
            b6Var = new b6();
            b6Var.a = aVar;
            b6Var.b = new ArrayList();
            VarHandle.storeStoreFence();
        }
        z5 z5Var = new z5();
        z5Var.b = tzVar;
        z5Var.c = str;
        z5Var.d = w2Var;
        z5Var.e = x5Var;
        VarHandle.storeStoreFence();
        b6Var.a.a(z5Var);
        b6Var.b.add(z5Var);
        linkedHashMap.put(str, b6Var);
        c6 c6Var = new c6();
        c6Var.c = tzVar;
        c6Var.d = str;
        c6Var.e = x5Var;
        VarHandle.storeStoreFence();
        this.E = c6Var;
    }

    public final void E() {
        c0 c0Var = new c0(5);
        c0Var.c = this;
        VarHandle.storeStoreFence();
        AiCapability aiCapability = new AiCapability(this, null, null, 6, null);
        mi miVar = new mi(0);
        miVar.c = aiCapability;
        miVar.d = c0Var;
        VarHandle.storeStoreFence();
        aiCapability.checkIfCtaAllowed(miVar);
    }

    public final void F() {
        boolean z = SystemProperties.getBoolean("debug.config.media.video.voip.detect", false);
        SharedPreferences preferences = getPreferences(0);
        boolean z2 = preferences.getBoolean("ai_guard_projection_permission", false);
        if (z || z2) {
            E();
            return;
        }
        CharSequence charSequenceLoadLabel = getApplicationInfo().loadLabel(getPackageManager());
        charSequenceLoadLabel.getClass();
        d dVar = new d(this, R.style.Dimmed);
        dVar.p(getString(R.string.ai_guard_permission_request_projection_title, new Object[]{charSequenceLoadLabel}));
        dVar.i(R.string.ai_guard_permission_request_projection_message);
        a02 a02Var = new a02(1);
        a02Var.c = this;
        VarHandle.storeStoreFence();
        dVar.l(a02Var);
        dVar.d();
        dVar.f();
        int iApplyDimension = (int) TypedValue.applyDimension(1, 32.0f, dVar.b().getResources().getDisplayMetrics());
        dVar.h(iApplyDimension, iApplyDimension);
        zz1 zz1Var = new zz1(3);
        zz1Var.c = this;
        VarHandle.storeStoreFence();
        dVar.k(R.string.ai_guard_permission_request_projection_neg, zz1Var);
        c8 c8Var = new c8(3);
        c8Var.c = preferences;
        c8Var.d = this;
        VarHandle.storeStoreFence();
        dVar.n(R.string.ai_guard_permission_request_projection_pos, c8Var);
        e eVarA = dVar.a();
        eVarA.show();
        TextView textView = eVarA.h.T;
        o8 o8Var = new o8(1);
        o8Var.c = eVarA;
        VarHandle.storeStoreFence();
        textView.post(o8Var);
    }

    public final void G() {
        d dVar = new d(this, R.style.Dimmed);
        dVar.o(R.string.ai_guard_permission_request_title);
        dVar.i(R.string.ai_guard_permission_request_message);
        xz1 xz1Var = new xz1(0);
        xz1Var.c = this;
        VarHandle.storeStoreFence();
        dVar.l(xz1Var);
        zz1 zz1Var = new zz1(1);
        zz1Var.c = this;
        VarHandle.storeStoreFence();
        dVar.n(R.string.ai_guard_permission_request_pos_btn, zz1Var);
        yz1 yz1Var = new yz1(1);
        yz1Var.c = this;
        VarHandle.storeStoreFence();
        dVar.k(android.R.string.cancel, yz1Var);
        dVar.a().show();
    }

    @Override // defpackage.od, defpackage.uz, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().clearFlags(PowerNest.SHARK_TCP);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.layoutInDisplayCutoutMode = 1;
        window.setAttributes(attributes);
        boolean z = getPreferences(0).getBoolean("ai_guard_first_open_guiding", true);
        int i = Settings.Secure.getInt(getContentResolver(), "com.miui.guardprovider.AI_GUARD_PRIVACY_AGREEMENT", 0);
        if (!z && i != 0) {
            if (checkSelfPermission("android.permission.RECORD_AUDIO") == 0) {
                F();
                return;
            } else {
                G();
                return;
            }
        }
        d dVar = new d(this, R.style.Dimmed);
        dVar.o(R.string.ai_guard_privacy_policy_title);
        Locale locale = Locale.getDefault();
        String str = "https://privacy.mi.com/AIalert/" + locale.getLanguage() + "_" + locale.getCountry();
        Spanned spannedFromHtml = Html.fromHtml(getString((getIntent().getIntExtra("capabilities", 1) & 2) == 0 ? R.string.ai_guard_privacy_policy_message_1 : R.string.ai_guard_privacy_policy_message, new Object[]{str, str}), 63);
        spannedFromHtml.getClass();
        xz1 xz1Var = new xz1(1);
        xz1Var.c = this;
        VarHandle.storeStoreFence();
        dVar.l(xz1Var);
        dVar.j(spannedFromHtml);
        yz1 yz1Var = new yz1(2);
        yz1Var.c = this;
        VarHandle.storeStoreFence();
        dVar.n(R.string.ai_guard_privacy_policy_agree, yz1Var);
        zz1 zz1Var = new zz1(2);
        zz1Var.c = this;
        VarHandle.storeStoreFence();
        dVar.k(android.R.string.cancel, zz1Var);
        e eVarA = dVar.a();
        eVarA.show();
        TextView textView = eVarA.h.T;
        if (textView != null) {
            if (ai1.a == null) {
                ai1.a = new ai1();
            }
            textView.setMovementMethod(ai1.a);
        }
    }
}
