package kl;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.miui.networkassistant.config.Constants;
import com.miui.warningcenter.policeassist.PaUtils;
import dl.c;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class a extends BroadcastReceiver {

    /* JADX INFO: renamed from: kl.a$a, reason: collision with other inner class name */
    class RunnableC0462a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f34183a;

        RunnableC0462a(Context context) {
            this.f34183a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (a.this.e(this.f34183a) && a.d(this.f34183a)) {
                Intent intent = new Intent();
                intent.setClassName("com.android.updater", "com.android.updater.UpdateService");
                intent.putExtra("extra_command", 23);
                this.f34183a.startService(intent);
            }
            com.miui.common.tools.b.F(this.f34183a);
        }
    }

    class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f34185a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f34186b;

        b(String str, Context context) {
            this.f34185a = str;
            this.f34186b = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (PaUtils.isEmergencyNumber(this.f34185a)) {
                PaUtils.setCallNumberAndStartService(this.f34186b, this.f34185a);
            }
        }
    }

    public static String b() {
        try {
            return c.a.d("miui.process.ProcessManager").c("getForegroundInfo", null, new Object[0]).l().g("mForegroundPackageName").m();
        } catch (Exception e10) {
            Log.e("home_receiver", "getForegroundPackageName exception: ", e10);
            return null;
        }
    }

    public static List c(Context context) {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 0)) {
            if (!"com.android.settings".equals(resolveInfo.activityInfo.packageName)) {
                arrayList.add(resolveInfo.activityInfo.packageName);
            }
        }
        return arrayList;
    }

    public static boolean d(Context context) {
        String strB = b();
        List listC = c(context);
        if (listC == null || listC.isEmpty()) {
            return false;
        }
        return listC.contains(strB);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(Context context) {
        String str = Build.VERSION.INCREMENTAL;
        ContentResolver contentResolver = context.getContentResolver();
        String string = Settings.Global.getString(contentResolver, "miui_new_version");
        return (TextUtils.isEmpty(string) || string.equals(str) || Settings.Global.getInt(contentResolver, "miui_update_ready", 0) != 1) ? false : true;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Log.d("home_receiver", "receive broadcast");
        if (Constants.System.ACTION_USER_PRESENT.equals(intent.getAction())) {
            b7.a.b(new RunnableC0462a(context));
        } else {
            if (!"android.intent.action.NEW_OUTGOING_CALL".equals(intent.getAction()) || miui.os.Build.IS_INTERNATIONAL_BUILD) {
                return;
            }
            b7.a.b(new b(intent.getStringExtra("android.intent.extra.PHONE_NUMBER"), context));
        }
    }
}
