package com.miui.monthreport;

import aj.z;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.miui.common.utils.c0;
import j7.i;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import miui.os.Build;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final String f20242g = "c";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static c f20243h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final Executor f20244i = Executors.newSingleThreadExecutor();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f20246b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Exception f20247c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private List f20248d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private volatile boolean f20249e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Handler f20250f = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.miui.monthreport.a f20245a = com.miui.monthreport.a.f();

    class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Log.i(c.f20242g, "Handle task message : " + message.what);
            switch (message.what) {
                case 101:
                    Object obj = message.obj;
                    if (obj != null && (obj instanceof String)) {
                        c.this.n((String) obj, null);
                        break;
                    }
                    break;
                case 102:
                    Object obj2 = message.obj;
                    if (obj2 != null && (obj2 instanceof com.miui.monthreport.b)) {
                        com.miui.monthreport.b bVar = (com.miui.monthreport.b) obj2;
                        c.this.n(bVar.d(), bVar);
                        break;
                    }
                    break;
                case 103:
                    c.this.q();
                    break;
            }
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (c.this.f20249e) {
                return;
            }
            c.this.f20249e = true;
            new AsyncTaskC0274c().executeOnExecutor(c.f20244i, new Void[0]);
        }
    }

    /* JADX INFO: renamed from: com.miui.monthreport.c$c, reason: collision with other inner class name */
    private class AsyncTaskC0274c extends AsyncTask {
        /* JADX DEBUG: Method merged with bridge method: doInBackground([Ljava/lang/Object;)Ljava/lang/Object; */
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public List doInBackground(Void... voidArr) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(5, -30);
            int iD = c.this.f20245a.d(calendar.getTimeInMillis());
            Log.i(c.f20242g, "Old data cleaned : " + iD);
            return c.this.f20245a.g();
        }

        /* JADX DEBUG: Method merged with bridge method: onPostExecute(Ljava/lang/Object;)V */
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List list) {
            super.onPostExecute(list);
            c.this.f20248d = list;
            c.this.q();
        }

        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
        private AsyncTaskC0274c() {
        }
    }

    private class d extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private com.miui.monthreport.b f20254a;

        public d(com.miui.monthreport.b bVar) {
            this.f20254a = bVar;
        }

        private boolean a(String str) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("error_code")) {
                c cVar = c.this;
                cVar.f20247c = cVar.f20245a.c(this.f20254a.b());
                Log.i(c.f20242g, "Upload successfully");
                return true;
            }
            Log.i(c.f20242g, "Error : " + jSONObject.optString("error", "Unknown error"));
            return false;
        }

        private boolean b() {
            return z.D() && c0.b(c.this.f20246b) && td.c.a();
        }

        private boolean d(String str) throws JSONException {
            int iE;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("module", str);
            String strC = j7.c.c(c.this.f20246b, "https://api.sec.miui.com/data/check", jSONObject, "5fdd8678-cddf-4269-bb73-48187445bba7", new i("monthreport_taskmanager"));
            Log.d(c.f20242g, "Available : " + strC);
            if (TextUtils.isEmpty(strC)) {
                return false;
            }
            JSONObject jSONObject2 = new JSONObject(strC);
            if (jSONObject2.has("error_code") || jSONObject2.optInt("code", 0) != 1) {
                return false;
            }
            if (!c0.a(c.this.f20246b) || ((iE = c.this.f20245a.e(str)) > 0 && iE < 20000)) {
                return true;
            }
            Log.d(c.f20242g, "Rejected : many events upload in mobile network");
            return false;
        }

        /* JADX DEBUG: Method merged with bridge method: doInBackground([Ljava/lang/Object;)Ljava/lang/Object; */
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public Integer doInBackground(String... strArr) {
            if (strArr.length < 0) {
                return 103;
            }
            try {
                String str = strArr[0];
                if (this.f20254a == null) {
                    Log.i(c.f20242g, String.format("Module %s task is null.", str));
                    this.f20254a = com.miui.monthreport.b.e(str, c.this.f20247c);
                }
                String strI = this.f20254a.i();
                if (strI == null) {
                    Log.i(c.f20242g, String.format("Module %s has no data.", str));
                    return 103;
                }
                if (b()) {
                    if (!d(str)) {
                        return 103;
                    }
                    Log.i(c.f20242g, "Uploading " + this.f20254a.toString());
                    if (a(j7.c.b(c.this.f20246b, "https://data.sec.miui.com/data/upload", this.f20254a.j(), strI, "5fdd8678-cddf-4269-bb73-48187445bba7", new i("monthreport_taskmanager_uploadtask")))) {
                        return 101;
                    }
                }
                return 102;
            } catch (Exception e10) {
                e10.printStackTrace();
            }
        }

        /* JADX DEBUG: Method merged with bridge method: onPostExecute(Ljava/lang/Object;)V */
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Integer num) {
            super.onPostExecute(num);
            if (num != null) {
                switch (num.intValue()) {
                    case 101:
                        c.this.f20250f.sendMessageDelayed(c.this.f20250f.obtainMessage(101, this.f20254a.d()), 300000L);
                        break;
                    case 102:
                        com.miui.monthreport.b bVar = this.f20254a;
                        if (bVar != null) {
                            bVar.k();
                            if (this.f20254a.c() < 3) {
                                c.this.f20250f.sendMessageDelayed(c.this.f20250f.obtainMessage(102, this.f20254a), ((long) this.f20254a.c()) * 300000);
                            }
                        }
                        break;
                    case 103:
                        c.this.f20250f.sendEmptyMessage(103);
                        break;
                }
            }
        }
    }

    private c(Context context) {
        this.f20246b = context.getApplicationContext();
    }

    public static synchronized c m(Context context) {
        try {
            if (f20243h == null) {
                f20243h = new c(context);
            }
        } catch (Throwable th2) {
            throw th2;
        }
        return f20243h;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(String str, com.miui.monthreport.b bVar) {
        this.f20250f.removeMessages(102);
        this.f20250f.removeMessages(101);
        new d(bVar).executeOnExecutor(f20244i, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        List list = this.f20248d;
        if (list == null || list.isEmpty()) {
            Log.i(f20242g, "Module is null");
            this.f20249e = false;
        } else {
            String str = (String) this.f20248d.get(0);
            this.f20248d.remove(0);
            n(str, null);
        }
    }

    public void o() {
        this.f20250f.post(new b());
    }

    public void p() {
        if (Build.IS_INTERNATIONAL_BUILD) {
            return;
        }
        o();
    }
}
