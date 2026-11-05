package a5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import c5.q;
import c5.t;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.miui.common.utils.q1;
import com.miui.riskyapp.model.RiskApp;
import com.miui.securitycenter.R;
import java.io.Closeable;
import jo.k;
import l7.e;
import miui.os.Build;
import vq.h;

/* JADX INFO: loaded from: classes2.dex */
public class b extends BroadcastReceiver {

    class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Intent f508a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f509b;

        a(Intent intent, Context context) {
            this.f508a = intent;
            this.f509b = context;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            String string;
            String strC;
            int i10 = -1;
            int intExtra = this.f508a.getIntExtra("virusLevel", -1);
            String stringExtra = this.f508a.getStringExtra(RiskApp.Columns.RISK_PACKAGE_NAME);
            String stringExtra2 = this.f508a.getStringExtra("virusDescription");
            String stringExtra3 = this.f508a.getStringExtra("engineName");
            String stringExtra4 = this.f508a.getStringExtra("virusName");
            String stringExtra5 = this.f508a.getStringExtra("versionName");
            String strB = t.b(intExtra);
            PackageManager packageManager = this.f509b.getPackageManager();
            PackageInfo packageInfoB = b.this.b(this.f509b, stringExtra);
            if (packageInfoB != null) {
                string = packageInfoB.applicationInfo.loadLabel(packageManager).toString();
                strC = c5.d.c(String.valueOf(packageInfoB.signatures[0].toChars()));
                i10 = packageInfoB.versionCode;
            } else {
                string = null;
                strC = null;
            }
            x4.a.c(stringExtra, string, com.miui.appmanager.b.f(this.f509b, stringExtra), q.x(this.f508a.getStringExtra("extras"), stringExtra3), strB, stringExtra2, strC, DateFormat.format("yyyy-MM-dd", q.z() ? e.j(this.f509b.getString(R.string.preference_key_database_auto_update_time, TtmlNode.COMBINE_ALL), 0L) : e.j(this.f509b.getString(R.string.preference_key_database_auto_update_time, stringExtra3), 0L)).toString(), "INSTALL_MONITOR", stringExtra4, stringExtra5);
            if (Build.IS_INTERNATIONAL_BUILD) {
                return;
            }
            String str = strC;
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            k.f33544c.a().k1(new io.b(stringExtra, string, Integer.valueOf(i10), str, jCurrentTimeMillis, -1L, Integer.valueOf(lo.b.EnumC0477b.RISK.b()), "installer"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0046: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:18:0x0045 */
    public PackageInfo b(Context context, String str) throws Throwable {
        Throwable th2;
        Exception exc;
        Cursor cursorQuery;
        Closeable closeable;
        Closeable closeable2 = null;
        try {
            try {
                cursorQuery = context.getContentResolver().query(MediaStore.Files.getContentUri("external"), new String[]{"_data", "date_modified"}, "_data LIKE '%.apk'", null, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            do {
                                PackageInfo packageInfoW = q1.w(context, cursorQuery.getString(0), 64);
                                if (packageInfoW != null && packageInfoW.packageName.equals(str)) {
                                    h.a(cursorQuery);
                                    return packageInfoW;
                                }
                            } while (cursorQuery.moveToNext());
                        }
                    } catch (Exception e10) {
                        exc = e10;
                        Log.e("TrackVirusReceiver", "Exception in get foreground scanning apks: ", exc);
                    }
                }
            } catch (Throwable th3) {
                th2 = th3;
                closeable2 = closeable;
                h.a(closeable2);
                throw th2;
            }
        } catch (Exception e11) {
            exc = e11;
            cursorQuery = null;
        } catch (Throwable th4) {
            th2 = th4;
            h.a(closeable2);
            throw th2;
        }
        h.a(cursorQuery);
        return null;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        b7.a.b(new a(intent, context));
    }
}
