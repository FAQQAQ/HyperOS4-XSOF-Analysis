package com.miui.securitymanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutManager;
import android.text.TextUtils;
import android.util.Log;
import c1.a;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-439d64a6e73e6da60b62f1cd0d3775067af568959c6537234e5044e8e318ed73 */
/* JADX INFO: loaded from: classes.dex */
public class ShortcutReceiver extends BroadcastReceiver {
    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        int intExtra = intent.getIntExtra("shortcutType", -1);
        String stringExtra = intent.getStringExtra("pkg");
        Log.d("ShortcutReceiver", "type = " + intExtra + ", pkg = " + stringExtra);
        if (intExtra == 0 && !TextUtils.isEmpty(stringExtra)) {
            a.d(context, stringExtra);
        }
        if (intExtra == 1) {
            if (TextUtils.equals(context.getPackageName(), stringExtra)) {
                a.a(context);
                return;
            }
            if (a.h()) {
                ShortcutManager shortcutManager = (ShortcutManager) context.getSystemService(ShortcutManager.class);
                ArrayList arrayListE = a.e(context, shortcutManager.getDynamicShortcuts(), stringExtra);
                if (arrayListE.isEmpty()) {
                    return;
                }
                shortcutManager.addDynamicShortcuts(arrayListE);
            }
        }
    }
}
