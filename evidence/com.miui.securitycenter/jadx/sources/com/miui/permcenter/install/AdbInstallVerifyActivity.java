package com.miui.permcenter.install;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.exifinterface.media.ExifInterface;
import com.miui.common.base.BaseActivity;
import com.miui.common.utils.j;
import com.miui.maml.data.VariableNames;
import com.miui.permcenter.compact.SystemPropertiesCompat;
import com.miui.securitycenter.R;
import dl.e;
import ff.c;
import ff.f;
import ff.k;
import ff.m;
import j7.i;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import miui.cloud.Constants;
import miui.os.Build;
import miuix.appcompat.app.AlertDialog;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class AdbInstallVerifyActivity extends BaseActivity implements DialogInterface.OnDismissListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected String f21739a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f21740b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected String f21741c = "https://srv.sec.miui.com/data/adb";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected b f21742d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private AlertDialog f21743e;

    static class a implements AccountManagerCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference f21744a;

        public a(AdbInstallVerifyActivity adbInstallVerifyActivity) {
            this.f21744a = new WeakReference(adbInstallVerifyActivity);
        }

        @Override // android.accounts.AccountManagerCallback
        public void run(AccountManagerFuture accountManagerFuture) {
            if (accountManagerFuture == null) {
                return;
            }
            try {
                AdbInstallVerifyActivity adbInstallVerifyActivity = (AdbInstallVerifyActivity) this.f21744a.get();
                Intent intent = (Intent) ((Bundle) accountManagerFuture.getResult()).getParcelable("intent");
                if (intent != null) {
                    if (j.f(adbInstallVerifyActivity)) {
                        e.d(intent, "addMiuiFlags", new Class[]{Integer.TYPE}, 4);
                        intent.putExtra("miui.extra.splitmode", 8);
                    }
                    adbInstallVerifyActivity.startActivity(intent);
                    adbInstallVerifyActivity.finish();
                    Log.d("AdbInstallActivity", "start login and finish this");
                }
            } catch (Exception e10) {
                e10.printStackTrace();
            }
        }
    }

    static class b extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference f21745a;

        b(AdbInstallVerifyActivity adbInstallVerifyActivity) {
            this.f21745a = new WeakReference(adbInstallVerifyActivity);
        }

        /* JADX DEBUG: Method merged with bridge method: doInBackground([Ljava/lang/Object;)Ljava/lang/Object; */
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public String doInBackground(Void... voidArr) throws Throwable {
            AdbInstallVerifyActivity adbInstallVerifyActivity;
            if (isCancelled() || (adbInstallVerifyActivity = (AdbInstallVerifyActivity) this.f21745a.get()) == null) {
                return null;
            }
            Log.w("AdbInstallActivity", "start request for adb install!");
            try {
                HashMap map = new HashMap();
                if (TextUtils.isEmpty(adbInstallVerifyActivity.f21739a)) {
                    map.put("xiaomi_id", "");
                } else {
                    map.put("xiaomi_id", adbInstallVerifyActivity.f21739a);
                }
                map.put(VariableNames.VAR_TIME, String.valueOf(System.currentTimeMillis()));
                JSONObject jSONObject = new JSONObject(f.a(adbInstallVerifyActivity.getApplicationContext(), map));
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, jSONObject);
                String strD = f.d(adbInstallVerifyActivity.f21741c, new k().a(jSONObject2.toString()), new i("permcenter_adbinstallverifyactivity"));
                if (TextUtils.isEmpty(strD)) {
                    Log.e("AdbInstallActivity", "result is empty");
                    return adbInstallVerifyActivity.getString(R.string.adb_install_open_error);
                }
                JSONObject jSONObject3 = new JSONObject(strD);
                Log.w("AdbInstallActivity", "response: " + jSONObject3);
                if (jSONObject3.optInt("status", -1) == 0) {
                    return null;
                }
                return jSONObject3.optString("message");
            } catch (Exception e10) {
                Log.e("AdbInstallActivity", "request error", e10);
            }
        }

        /* JADX DEBUG: Method merged with bridge method: onPostExecute(Ljava/lang/Object;)V */
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(String str) {
            AdbInstallVerifyActivity adbInstallVerifyActivity = (AdbInstallVerifyActivity) this.f21745a.get();
            if (adbInstallVerifyActivity == null) {
                return;
            }
            Log.w("AdbInstallActivity", "server result: " + str);
            if (str == null) {
                adbInstallVerifyActivity.y0();
            } else if (!TextUtils.isEmpty(str)) {
                Toast.makeText(adbInstallVerifyActivity.getApplicationContext(), str, 1).show();
            }
            adbInstallVerifyActivity.finish();
        }
    }

    private void A0(boolean z10) {
        c.m(this).D(z10);
    }

    private void x0(Bundle bundle) {
        AccountManager.get(getApplicationContext()).addAccount(Constants.XIAOMI_ACCOUNT_TYPE, "passportapi", null, bundle, null, new a(this), null);
    }

    private void z0() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            Toast.makeText(getApplicationContext(), R.string.adb_install_no_network, 1).show();
            finish();
        } else {
            b bVar = new b(this);
            this.f21742d = bVar;
            bVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    protected void B0() {
        AlertDialog alertDialogShow = new AlertDialog.Builder(this, 2131951665).setView(View.inflate(this, R.layout.adb_install_progress, null)).setOnDismissListener(this).show();
        this.f21743e = alertDialogShow;
        ensureDialogCenterOnFoldable(alertDialogShow);
        z0();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.miui.common.base.BaseActivity, miuix.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f21740b = getIntent().getBooleanExtra("is_input", false);
        if (Build.DEVICE.equals("houji") && "eea".equals(SystemPropertiesCompat.getString("ro.miui.build.region", "CN"))) {
            y0();
            finish();
            return;
        }
        String strA = m.a(getApplicationContext());
        this.f21739a = strA;
        if (this.f21740b || !TextUtils.isEmpty(strA)) {
            B0();
        } else {
            Toast.makeText(this, R.string.adb_login_xiaomi_account, 1).show();
            x0(new Bundle());
        }
    }

    @Override // com.miui.common.base.BaseActivity, miuix.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        AlertDialog alertDialog = this.f21743e;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.f21743e.dismiss();
            this.f21743e = null;
        }
        b bVar = this.f21742d;
        if (bVar != null) {
            bVar.cancel(true);
        }
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        if (isFinishing()) {
            return;
        }
        finish();
    }

    protected void y0() {
        if (this.f21740b) {
            AdbInputApplyActivity.G0(true);
        } else {
            A0(true);
        }
    }
}
