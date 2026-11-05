package com.mobiletools.systemhelper.Activity;

import android.os.Bundle;
import android.widget.TextView;
import com.mobiletools.systemhelper.R;
import e1.b;
import f1.p;
import g1.d;
import g1.l;
import i0.a;

/* JADX INFO: loaded from: classes.dex */
public class TestActivity extends a {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private TextView f2186r;

    private void B() {
        this.f2186r = (TextView) findViewById(R.id.tv_device_info);
        p.f();
        this.f2186r.setText(p.e(this) != null ? b.a(getApplicationContext(), "").b() : "");
    }

    @Override // i0.a, android.support.v4.app.f, android.support.v4.app.x, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_test);
        if (!d.f2646b) {
            d.f2645a = l.d(this, "preference_enable_log", Boolean.FALSE).booleanValue();
        }
        f1.a.p(this);
        if (y.a.a(this, "android.permission.READ_PHONE_STATE") == 0 && y.a.a(this, "android.permission.ACCESS_COARSE_LOCATION") == 0 && y.a.a(this, "android.permission.ACCESS_FINE_LOCATION") == 0 && y.a.a(this, "android.permission.ACCESS_BACKGROUND_LOCATION") == 0 && y.a.a(this, "android.permission.ACCESS_NETWORK_STATE") == 0) {
            B();
        } else {
            android.support.v4.app.a.f(this, new String[]{"android.permission.READ_PHONE_STATE", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_BACKGROUND_LOCATION", "android.permission.ACCESS_NETWORK_STATE"}, 1);
        }
    }

    @Override // android.support.v4.app.f, android.app.Activity
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        if (i2 != 1) {
            return;
        }
        if (iArr.length == 3 && iArr[0] == 0 && iArr[1] == 0 && iArr[2] == 0) {
            B();
        } else {
            finish();
        }
    }
}
