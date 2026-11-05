package com.mobiletools.systemhelper.control;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import e1.b;
import f1.a;
import f1.p;
import g1.d;
import g1.h;
import g1.l;

/* JADX INFO: loaded from: classes.dex */
public class OnBootReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Context applicationContext = context.getApplicationContext();
        if (!d.f2646b) {
            d.f2645a = l.d(applicationContext, "preference_enable_log", Boolean.FALSE).booleanValue();
        }
        h.a("OnBootReceiver", "onReceive-->intent action: " + intent.getAction());
        a.p(applicationContext);
        String action = intent.getAction();
        if (action != null && (action.equals("android.intent.action.BOOT_COMPLETED") || action.equals("android.intent.action.QUICKBOOT_POWERON"))) {
            h.a("OnBootReceiver", " boot delayed for 4 minutes-->");
            g1.a.b(applicationContext, 240000L);
            g1.a.c(applicationContext, l.m(applicationContext));
            g1.a.f(applicationContext, l.h(applicationContext), l.l(applicationContext));
            g1.a.e(applicationContext, l.g(applicationContext), l.j(applicationContext));
            g1.a.a(applicationContext, l.f(applicationContext), l.c(applicationContext));
            return;
        }
        h.a("OnBootReceiver", "SIM_STATE_CHANGED, state:" + ((TelephonyManager) applicationContext.getSystemService("phone")).getSimState());
        new SIMStateReceiver().b(applicationContext);
        new ConnectStateReceiver().b(applicationContext);
        p.f();
        if (p.e(applicationContext) != null) {
            String strN = l.n(applicationContext, "firstPowerOn", "");
            if (strN != null && !strN.isEmpty()) {
                b.a(applicationContext, "B");
            } else {
                b.a(applicationContext, "F");
                l.H(applicationContext, "firstPowerOn", "firstPowerOn");
            }
        }
    }
}
