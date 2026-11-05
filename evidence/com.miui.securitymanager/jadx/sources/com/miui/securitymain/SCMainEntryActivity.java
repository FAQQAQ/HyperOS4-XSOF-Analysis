package com.miui.securitymain;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import e.j;
import e1.a;

/* JADX INFO: compiled from: r8-map-id-439d64a6e73e6da60b62f1cd0d3775067af568959c6537234e5044e8e318ed73 */
/* JADX INFO: loaded from: classes.dex */
public class SCMainEntryActivity extends j {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ int f852n = 0;

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    @Override // androidx.fragment.app.i, androidx.activity.f, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i("SCMainEntryActivity", "onCreate: SCMainEntryActivity");
        new Handler(Looper.getMainLooper()).post(new a(this, getApplicationContext(), 0));
    }
}
