package mi;

import aj.z;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import c5.k;
import com.miui.common.utils.m2;
import com.miui.common.utils.x1;
import com.miui.riskyapp.model.CloudScanPayload;
import com.miui.riskyapp.model.RiskApp;
import com.miui.riskyapp.model.RiskyAppDescriptionItem;
import com.miui.riskyapp.model.RiskyAppDescriptionList;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import miui.os.Build;
import miui.security.SecurityManager;

/* JADX INFO: loaded from: classes3.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile b f35367a;

    class a implements f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f35368a;

        a(Context context) {
            this.f35368a = context;
        }

        @Override // mi.b.f
        public void a(RiskyAppDescriptionList riskyAppDescriptionList) {
            new e(this.f35368a, riskyAppDescriptionList != null ? riskyAppDescriptionList.getDataVersion() : 0).execute(new String[0]);
        }
    }

    /* JADX INFO: renamed from: mi.b$b, reason: collision with other inner class name */
    private class AsyncTaskC0491b extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Context f35370a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String f35371b;

        AsyncTaskC0491b(Context context, String str) {
            this.f35370a = context.getApplicationContext();
            this.f35371b = str;
        }

        /* JADX DEBUG: Method merged with bridge method: doInBackground([Ljava/lang/Object;)Ljava/lang/Object; */
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean doInBackground(Void... voidArr) {
            return Boolean.valueOf(b.this.g(this.f35370a, this.f35371b) == null);
        }

        /* JADX DEBUG: Method merged with bridge method: onPostExecute(Ljava/lang/Object;)V */
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Boolean bool) {
            if (bool.booleanValue()) {
                Log.d("RiskyAppManager", "checkAppOnLaunch: no record for " + this.f35371b + ",  scan for it");
                b.this.b(this.f35370a, this.f35371b);
            }
        }
    }

    public static class c extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        f f35373a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Context f35374b;

        public c(Context context, f fVar) {
            this.f35374b = context;
            this.f35373a = fVar;
        }

        /* JADX WARN: Code duplicated, block: B:11:0x002c  */
        private RiskyAppDescriptionList c() {
            int i10;
            RiskyAppDescriptionList riskyAppDescriptionList = new RiskyAppDescriptionList();
            ArrayList arrayList = new ArrayList();
            Cursor cursorRawQuery = oi.b.a(this.f35374b).rawQuery("SELECT MAX(dataVersion) FROM risk_app_description_list", null);
            if (cursorRawQuery != null) {
                try {
                    if (cursorRawQuery.moveToFirst()) {
                        i10 = cursorRawQuery.getInt(0);
                    } else {
                        i10 = 0;
                    }
                } catch (Throwable th2) {
                    cursorRawQuery.close();
                    throw th2;
                }
            } else {
                i10 = 0;
            }
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            riskyAppDescriptionList.setDataVersion(i10);
            Cursor cursorQuery = oi.b.a(this.f35374b).query("risk_app_description_list", new String[]{"_id", "dataVersion", "riskType", RiskyAppDescriptionItem.Columns.RISK_LEVEL, RiskyAppDescriptionItem.Columns.TIP_CHS, RiskyAppDescriptionItem.Columns.TIP_CHT, RiskyAppDescriptionItem.Columns.TIP_EN, RiskyAppDescriptionItem.Columns.TIP_BO, RiskyAppDescriptionItem.Columns.TIP_UIG, RiskyAppDescriptionItem.Columns.TIP_KO, RiskyAppDescriptionItem.Columns.TIP_JA, RiskyAppDescriptionItem.Columns.TIP_FR, RiskyAppDescriptionItem.Columns.TIP_ES_ES, RiskyAppDescriptionItem.Columns.TIP_ES_US, RiskyAppDescriptionItem.Columns.TIP_DE, RiskyAppDescriptionItem.Columns.TIP_AR_E, RiskyAppDescriptionItem.Columns.TIP_AR_W, RiskyAppDescriptionItem.Columns.TIP_TH, RiskyAppDescriptionItem.Columns.TIP_PT_BR, RiskyAppDescriptionItem.Columns.TIP_PT_PT, RiskyAppDescriptionItem.Columns.OTHER}, null, null, null, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        do {
                            RiskyAppDescriptionItem riskyAppDescriptionItem = new RiskyAppDescriptionItem();
                            riskyAppDescriptionItem.setDataVersion(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("dataVersion")));
                            riskyAppDescriptionItem.setRiskLevel(cursorQuery.getString(cursorQuery.getColumnIndexOrThrow(RiskyAppDescriptionItem.Columns.RISK_LEVEL)));
                            riskyAppDescriptionItem.setRiskType(cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("riskType")));
                            for (String str : RiskyAppDescriptionItem.Columns.ALL_TIP_COLUMNS) {
                                riskyAppDescriptionItem.setTip(str, cursorQuery.getString(cursorQuery.getColumnIndexOrThrow(str)));
                            }
                            riskyAppDescriptionItem.setOtherInfo(cursorQuery.getString(cursorQuery.getColumnIndexOrThrow(RiskyAppDescriptionItem.Columns.OTHER)));
                            arrayList.add(riskyAppDescriptionItem);
                        } while (cursorQuery.moveToNext());
                    }
                } catch (Throwable th3) {
                    cursorQuery.close();
                    throw th3;
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            riskyAppDescriptionList.setBlockingApps(arrayList);
            Log.d("RiskyAppManager", "queryRiskyAppData maxDataVersion=" + i10 + ", itemCount=" + arrayList.size());
            return riskyAppDescriptionList;
        }

        /* JADX DEBUG: Method merged with bridge method: doInBackground([Ljava/lang/Object;)Ljava/lang/Object; */
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public RiskyAppDescriptionList doInBackground(String... strArr) {
            RiskyAppDescriptionList riskyAppDescriptionListC = c();
            Log.d("RiskyAppManager", "RiskyAppDescriptionList query finish");
            return riskyAppDescriptionListC;
        }

        /* JADX DEBUG: Method merged with bridge method: onPostExecute(Ljava/lang/Object;)V */
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(RiskyAppDescriptionList riskyAppDescriptionList) {
            f fVar = this.f35373a;
            if (fVar != null) {
                fVar.a(riskyAppDescriptionList);
            }
        }
    }

    private static class d extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Context f35375a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f35376b;

        private void b(List list, List list2) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                b.e(this.f35375a, (String) it.next());
            }
            Iterator it2 = list2.iterator();
            while (it2.hasNext()) {
                b.h(this.f35375a, (String) it2.next());
            }
            if (list.isEmpty() && list2.isEmpty()) {
                return;
            }
            Log.d("RiskyAppManager", "fwk operations: insertToFwk=" + list.size() + ", removeFromFwk=" + list2.size());
        }

        private int d(SQLiteDatabase sQLiteDatabase, String str) {
            Cursor cursorQuery = sQLiteDatabase.query("risk_app_app_name", new String[]{"riskType"}, "packageName=?", new String[]{str}, null, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        int i10 = cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("riskType"));
                        cursorQuery.close();
                        return i10;
                    }
                } catch (Throwable th2) {
                    cursorQuery.close();
                    throw th2;
                }
            }
            if (cursorQuery == null) {
                return -1;
            }
            cursorQuery.close();
            return -1;
        }

        private void e(List list, List list2, List list3) {
            SQLiteDatabase sQLiteDatabaseA = oi.b.a(this.f35375a);
            sQLiteDatabaseA.beginTransaction();
            try {
                Iterator it = list.iterator();
                int i10 = 0;
                int i11 = 0;
                while (it.hasNext()) {
                    CloudScanPayload cloudScanPayload = (CloudScanPayload) it.next();
                    String packageName = cloudScanPayload.getPackageName();
                    int riskType = cloudScanPayload.getRiskType();
                    boolean zContainsResultType = cloudScanPayload.containsResultType(CloudScanPayload.ResultType.Risks);
                    int iD = d(sQLiteDatabaseA, packageName);
                    if (zContainsResultType) {
                        list2.add(packageName);
                    } else if (iD > 0) {
                        list3.add(packageName);
                    }
                    if (iD < 0) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(RiskApp.Columns.RISK_PACKAGE_NAME, packageName);
                        contentValues.put("riskType", Integer.valueOf(riskType));
                        sQLiteDatabaseA.insert("risk_app_app_name", null, contentValues);
                        i10++;
                    } else if (iD != riskType) {
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("riskType", Integer.valueOf(riskType));
                        sQLiteDatabaseA.update("risk_app_app_name", contentValues2, "packageName=?", new String[]{packageName});
                        i11++;
                    }
                }
                sQLiteDatabaseA.setTransactionSuccessful();
                Log.d("RiskyAppManager", "processCloudScanResults done: total=" + list.size() + ", insert=" + i10 + ", update=" + i11);
            } catch (Exception e10) {
                Log.e("RiskyAppManager", "processCloudScanResults failed", e10);
            } finally {
                sQLiteDatabaseA.endTransaction();
            }
        }

        /* JADX DEBUG: Method merged with bridge method: doInBackground([Ljava/lang/Object;)Ljava/lang/Object; */
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public List doInBackground(String... strArr) {
            String str;
            if (!z.D()) {
                return null;
            }
            Log.d("RiskyAppManager", "start build all installed packageList  packageName =" + this.f35376b);
            List listB = qi.a.b(this.f35375a, this.f35376b);
            if (listB == null || listB.isEmpty()) {
                Log.w("RiskyAppManager", "RequestRiskyAppListTask: no installed packages to scan");
                return null;
            }
            Log.d("RiskyAppManager", "start request riskList, packageCount=" + listB.size());
            List listF = qi.c.f(this.f35375a, listB);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("RequestRiskyAppListTask finish ");
            if (listF == null) {
                str = "null";
            } else {
                str = "result.size=" + listF.size();
            }
            sb2.append(str);
            Log.d("RiskyAppManager", sb2.toString());
            if (listF != null && !listF.isEmpty()) {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                e(listF, arrayList, arrayList2);
                b(arrayList, arrayList2);
            }
            return listF;
        }

        /* JADX DEBUG: Method merged with bridge method: onPostExecute(Ljava/lang/Object;)V */
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List list) {
        }

        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
        private d(Context context, String str) {
            this.f35375a = context;
            this.f35376b = str;
        }
    }

    private static class e extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Context f35377a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f35378b;

        private ContentValues a(int i10, RiskyAppDescriptionItem riskyAppDescriptionItem) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("dataVersion", Integer.valueOf(i10));
            contentValues.put(RiskyAppDescriptionItem.Columns.STARTUP_TO_SEC_RISK_TIP, Integer.valueOf(riskyAppDescriptionItem.isStartupToSecRiskTip() ? 1 : 0));
            contentValues.put("riskType", riskyAppDescriptionItem.getRiskType());
            contentValues.put(RiskyAppDescriptionItem.Columns.RISK_LEVEL, riskyAppDescriptionItem.getRiskLevel());
            for (String str : RiskyAppDescriptionItem.Columns.ALL_TIP_COLUMNS) {
                contentValues.put(str, riskyAppDescriptionItem.getTip(str));
            }
            contentValues.put(RiskyAppDescriptionItem.Columns.OTHER, riskyAppDescriptionItem.getOtherInfo());
            return contentValues;
        }

        private void c(RiskyAppDescriptionList riskyAppDescriptionList) {
            List<RiskyAppDescriptionItem> blockingApps;
            Iterator<RiskyAppDescriptionItem> it;
            if (riskyAppDescriptionList == null || (blockingApps = riskyAppDescriptionList.getBlockingApps()) == null) {
                return;
            }
            SQLiteDatabase sQLiteDatabaseA = oi.b.a(this.f35377a);
            sQLiteDatabaseA.beginTransaction();
            try {
                Iterator<RiskyAppDescriptionItem> it2 = blockingApps.iterator();
                int i10 = 0;
                int i11 = 0;
                int i12 = 0;
                while (it2.hasNext()) {
                    RiskyAppDescriptionItem next = it2.next();
                    String riskType = next.getRiskType();
                    if (riskType != null) {
                        if (next.isStartupToSecRiskTip()) {
                            it = it2;
                            Cursor cursorQuery = sQLiteDatabaseA.query("risk_app_description_list", new String[]{"_id"}, "riskType=?", new String[]{riskType}, null, null, null);
                            boolean z10 = cursorQuery != null && cursorQuery.getCount() > 0;
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                            ContentValues contentValuesA = a(riskyAppDescriptionList.getDataVersion(), next);
                            if (z10) {
                                sQLiteDatabaseA.update("risk_app_description_list", contentValuesA, "riskType=?", new String[]{riskType});
                                i11++;
                            } else {
                                sQLiteDatabaseA.insert("risk_app_description_list", null, contentValuesA);
                                i10++;
                            }
                        } else {
                            it = it2;
                            sQLiteDatabaseA.delete("risk_app_description_list", "riskType=?", new String[]{riskType});
                            i12++;
                        }
                        it2 = it;
                    }
                }
                sQLiteDatabaseA.setTransactionSuccessful();
                Log.d("RiskyAppManager", "mergeRiskyAppData done: insert=" + i10 + ", update=" + i11 + ", delete=" + i12);
            } catch (Exception e10) {
                Log.e("RiskyAppManager", "mergeRiskyAppData failed, rolled back", e10);
            } finally {
                sQLiteDatabaseA.endTransaction();
            }
        }

        /* JADX DEBUG: Method merged with bridge method: doInBackground([Ljava/lang/Object;)Ljava/lang/Object; */
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public RiskyAppDescriptionList doInBackground(String... strArr) {
            String str;
            if (!z.D()) {
                return null;
            }
            Log.d("RiskyAppManager", "start request dataVersion=" + this.f35378b);
            RiskyAppDescriptionList riskyAppDescriptionListG = qi.c.g(this.f35377a, this.f35378b + "");
            StringBuilder sb2 = new StringBuilder();
            sb2.append("RequestRiskyNotifyTipTask finish ");
            String str2 = "null";
            if (riskyAppDescriptionListG == null) {
                str = "null";
            } else {
                str = "result.dataVersion=" + riskyAppDescriptionListG.getDataVersion() + "  dataVersion=" + this.f35378b;
            }
            sb2.append(str);
            Log.d("RiskyAppManager", sb2.toString());
            if (riskyAppDescriptionListG != null && riskyAppDescriptionListG.getDataVersion() > this.f35378b) {
                Log.d("RiskyAppManager", "dataVersion changed: " + this.f35378b + " -> " + riskyAppDescriptionListG.getDataVersion() + ", entering mergeRiskyAppData");
                c(riskyAppDescriptionListG);
                return riskyAppDescriptionListG;
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append("no merge needed, result=");
            if (riskyAppDescriptionListG != null) {
                str2 = "dataVersion=" + riskyAppDescriptionListG.getDataVersion();
            }
            sb3.append(str2);
            Log.d("RiskyAppManager", sb3.toString());
            return riskyAppDescriptionListG;
        }

        /* JADX DEBUG: Method merged with bridge method: onPostExecute(Ljava/lang/Object;)V */
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(RiskyAppDescriptionList riskyAppDescriptionList) {
        }

        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
        private e(Context context, int i10) {
            this.f35377a = context;
            this.f35378b = i10;
        }
    }

    public interface f {
        void a(RiskyAppDescriptionList riskyAppDescriptionList);
    }

    private b() {
    }

    public static synchronized b c() {
        try {
            if (f35367a == null) {
                f35367a = new b();
            }
        } catch (Throwable th2) {
            throw th2;
        }
        return f35367a;
    }

    public static void e(Context context, String str) {
        SecurityManager securityManager = (SecurityManager) context.getSystemService(SecurityManager.class);
        try {
            Method method = securityManager.getClass().getMethod("addStartupBlockedPackage", String.class, Integer.TYPE);
            int iF = m2.f();
            method.invoke(securityManager, str, Integer.valueOf(iF));
            Log.d("RiskyAppManager", "insertToFwk: added " + str + " for userId=" + iF);
            method.invoke(securityManager, str, 999);
            Log.d("RiskyAppManager", "insertToFwk: added " + str + " for userId=999");
        } catch (Throwable th2) {
            Log.e("RiskyAppManager", "insertToFwk: ", th2);
        }
    }

    public static void h(Context context, String str) {
        SecurityManager securityManager = (SecurityManager) context.getSystemService(SecurityManager.class);
        try {
            Method method = securityManager.getClass().getMethod("removeStartupBlockedPackage", String.class, Integer.TYPE);
            method.invoke(securityManager, str, Integer.valueOf(m2.f()));
            method.invoke(securityManager, str, 999);
            Log.d("RiskyAppManager", "removeFromFwk: removed " + str + " for userId=0 and userId=999");
        } catch (Throwable th2) {
            Log.e("RiskyAppManager", "removeFromFwk: ", th2);
        }
    }

    public void a(Context context, String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        new AsyncTaskC0491b(context, str).execute(new Void[0]);
    }

    public void b(Context context, String str) {
        if (!f()) {
            Log.d("RiskyAppManager", "isSupportRiskAppIntercept = false");
        } else if (!z.D() || !k.a(context)) {
            Log.d("RiskyAppManager", "isConnectNetworkAlow = false");
        } else {
            new c(context, new a(context)).execute(new String[0]);
            new d(context, str).execute(new String[0]);
        }
    }

    public boolean d(Context context) {
        return x1.c(context.getContentResolver(), "malware_startup_intercept", 1, m2.F()) == 1;
    }

    public boolean f() {
        if (Build.IS_TABLET || Build.IS_INTERNATIONAL_BUILD) {
            return false;
        }
        try {
            SecurityManager.class.getDeclaredMethod("addStartupBlockedPackage", String.class, Integer.TYPE);
            return true;
        } catch (Exception e10) {
            Log.e("RiskyAppManager", " isSupportRiskAppIntercept error：", e10);
            return false;
        }
    }

    public RiskApp g(Context context, String str) {
        Cursor cursorQuery = oi.b.a(context).query("risk_app_app_name", new String[]{"_id", RiskApp.Columns.RISK_PACKAGE_NAME, "riskType"}, "packageName=?", new String[]{str}, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    RiskApp riskApp = new RiskApp();
                    riskApp.setPackageName(cursorQuery.getString(cursorQuery.getColumnIndexOrThrow(RiskApp.Columns.RISK_PACKAGE_NAME)));
                    riskApp.setRiskType(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("riskType")));
                    cursorQuery.close();
                    return riskApp;
                }
            } catch (Throwable th2) {
                cursorQuery.close();
                throw th2;
            }
        }
        if (cursorQuery == null) {
            return null;
        }
        cursorQuery.close();
        return null;
    }

    public void i(Context context, boolean z10) {
        x1.e(context.getContentResolver(), "malware_startup_intercept", z10 ? 1 : 0, m2.F());
    }
}
