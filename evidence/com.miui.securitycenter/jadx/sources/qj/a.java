package qj;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import com.miui.analytics.StatManager;
import com.miui.common.card.models.AdvCardModel;
import com.miui.common.utils.a1;
import com.miui.earthquakewarning.model.SaveAreaResult;
import com.miui.securitycenter.Application;
import com.xiaomi.analytics.Actions;
import com.xiaomi.analytics.AdAction;
import com.xiaomi.analytics.Analytics;
import com.xiaomi.analytics.Tracker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import miui.os.Build;

/* JADX INFO: loaded from: classes3.dex */
public abstract class a {

    /* JADX INFO: renamed from: qj.a$a, reason: collision with other inner class name */
    class AsyncTaskC0561a extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ArrayList f41011a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f41012b;

        AsyncTaskC0561a(ArrayList arrayList, Context context) {
            this.f41011a = arrayList;
            this.f41012b = context;
        }

        private void a(String str, String str2, String str3, String str4, String[] strArr, String[] strArr2) {
            String string = UUID.randomUUID().toString();
            Log.i("AdAnalytics", "trackingStrategy : " + str4);
            if (TextUtils.isEmpty(str4)) {
                c(str, str2, str3, strArr, strArr2, string);
            } else if (TextUtils.equals(str4, "useonetrack")) {
                d(str, str3, strArr, strArr2, string, false);
            } else {
                c(str, str2, str3, strArr, strArr2, string);
                d(str, str3, strArr, strArr2, string, true);
            }
        }

        private void c(String str, String str2, String str3, String[] strArr, String[] strArr2, String str4) {
            Analytics analytics = Analytics.getInstance(this.f41012b);
            analytics.setDebugOn(false);
            Tracker tracker = analytics.getTracker(str2);
            AdAction adActionNewAdAction = Actions.newAdAction(str);
            adActionNewAdAction.addParam("e", str);
            adActionNewAdAction.addParam("ex", str3);
            adActionNewAdAction.addParam("uniqueId", str4);
            adActionNewAdAction.addParam("t", String.valueOf(System.currentTimeMillis()));
            if ("VIEW".equals(str)) {
                a.d(adActionNewAdAction, strArr, str4);
            } else if ("CLICK".equals(str)) {
                a.d(adActionNewAdAction, strArr2, str4);
            }
            tracker.track(adActionNewAdAction);
        }

        /* JADX DEBUG: Method merged with bridge method: doInBackground([Ljava/lang/Object;)Ljava/lang/Object; */
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Code duplicated, block: B:29:0x004d  */
        /* JADX WARN: Code duplicated, block: B:46:0x0064 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:48:0x007d A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:49:0x00a0 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:50:0x0057 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:51:0x0081 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:52:0x00c3 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:53:0x00a4 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:54:0x00c7 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:56:0x004b A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:60:0x004b A[SYNTHETIC] */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            String str;
            String str2;
            ArrayList arrayList;
            int size;
            Object obj;
            b bVar;
            AdvCardModel advCardModel;
            int i10 = 0;
            if (Build.IS_INTERNATIONAL_BUILD) {
                str2 = "com.miui.securitycenter_globaladevent";
            } else {
                Object obj2 = this.f41011a.get(0);
                if (!(obj2 instanceof b)) {
                    if (obj2 instanceof c) {
                        str2 = "com.miui.securitycenter_datamodel";
                    } else {
                        str = ((obj2 instanceof d) || (obj2 instanceof e)) ? "com.miui.securitycenter_appmanager" : "";
                    }
                    arrayList = this.f41011a;
                    size = arrayList.size();
                    while (i10 < size) {
                        obj = arrayList.get(i10);
                        i10++;
                        if (obj instanceof b) {
                            bVar = (b) obj;
                            advCardModel = bVar.f41014b;
                            if (advCardModel.isLocal()) {
                                a(bVar.f41013a, str, advCardModel.getEx(), advCardModel.getTrackingStrategy(), advCardModel.getViewMonitorUrls(), advCardModel.getClickMonitorUrls());
                            }
                        } else if (obj instanceof c) {
                            c cVar = (c) obj;
                            f7.a aVar = cVar.f41016b;
                            a(cVar.f41015a, str, aVar.o(), aVar.x(), aVar.y(), aVar.m());
                        } else if (obj instanceof d) {
                            d dVar = (d) obj;
                            n5.c cVar2 = dVar.f41018b;
                            a(dVar.f41017a, str, cVar2.v(), cVar2.x(), cVar2.y(), cVar2.u());
                        } else if (obj instanceof e) {
                            e eVar = (e) obj;
                            com.miui.optimizemanage.optimizeresult.b bVar2 = eVar.f41020b;
                            a(eVar.f41019a, str, bVar2.l(), bVar2.o(), bVar2.p(), bVar2.j());
                        }
                    }
                    return null;
                }
                int usePosition = ((b) obj2).f41014b.getUsePosition();
                if (usePosition != 1) {
                    str2 = (usePosition == 2 || usePosition == 3) ? "com.miui.securitycenter_scanresult" : "";
                } else {
                    str2 = "com.miui.securitycenter_homepage";
                }
            }
            str = str2;
            arrayList = this.f41011a;
            size = arrayList.size();
            while (i10 < size) {
                obj = arrayList.get(i10);
                i10++;
                if (obj instanceof b) {
                    bVar = (b) obj;
                    advCardModel = bVar.f41014b;
                    if (advCardModel.isLocal()) {
                        a(bVar.f41013a, str, advCardModel.getEx(), advCardModel.getTrackingStrategy(), advCardModel.getViewMonitorUrls(), advCardModel.getClickMonitorUrls());
                    }
                } else if (obj instanceof c) {
                    c cVar3 = (c) obj;
                    f7.a aVar2 = cVar3.f41016b;
                    a(cVar3.f41015a, str, aVar2.o(), aVar2.x(), aVar2.y(), aVar2.m());
                } else if (obj instanceof d) {
                    d dVar2 = (d) obj;
                    n5.c cVar4 = dVar2.f41018b;
                    a(dVar2.f41017a, str, cVar4.v(), cVar4.x(), cVar4.y(), cVar4.u());
                } else if (obj instanceof e) {
                    e eVar2 = (e) obj;
                    com.miui.optimizemanage.optimizeresult.b bVar3 = eVar2.f41020b;
                    a(eVar2.f41019a, str, bVar3.l(), bVar3.o(), bVar3.p(), bVar3.j());
                }
            }
            return null;
        }

        public void d(String str, String str2, String[] strArr, String[] strArr2, String str3, boolean z10) {
            ArrayMap arrayMap = new ArrayMap(2);
            ArrayList arrayList = new ArrayList();
            HashMap map = new HashMap(1);
            map.put("ex", str2);
            arrayList.add(map);
            arrayMap.put("adTrackInfo", arrayList);
            if (z10) {
                arrayMap.put("uniqueId", str3);
            }
            List<String> listF = a.f(str, strArr, strArr2, str3, z10);
            String strE = a.e(str);
            if (strE == null || strE.isEmpty()) {
                return;
            }
            StatManager.getInstance().adTrack(strE, arrayMap, listF);
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f41013a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private AdvCardModel f41014b;

        public b(String str, AdvCardModel advCardModel) {
            this.f41013a = str;
            this.f41014b = advCardModel;
        }
    }

    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f41015a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private f7.a f41016b;

        public c(String str, f7.a aVar) {
            this.f41015a = str;
            this.f41016b = aVar;
        }
    }

    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f41017a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private n5.c f41018b;

        public d(String str, n5.c cVar) {
            this.f41017a = str;
            this.f41018b = cVar;
        }

        public String toString() {
            return this.f41017a + " " + this.f41018b.w();
        }
    }

    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f41019a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private com.miui.optimizemanage.optimizeresult.b f41020b;

        public e(String str, com.miui.optimizemanage.optimizeresult.b bVar) {
            this.f41019a = str;
            this.f41020b = bVar;
        }
    }

    static {
        Analytics.setUseSystemAnalyticsOnly(Application.B());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(AdAction adAction, String[] strArr, String str) {
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String strG : strArr) {
            if (strG != null && strG.contains("https://monitor-check.ad.xiaomi.com")) {
                strG = g(g(strG, SaveAreaResult.Columns.source, "sdk"), "uniqueId", str);
            }
            arrayList.add(strG);
        }
        a1.g("AdAnalytics", "urlList sdk : " + arrayList);
        adAction.addAdMonitor(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String e(String str) {
        if ("VIEW".equals(str)) {
            return "view";
        }
        return "CLICK".equals(str) ? "click" : str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List f(String str, String[] strArr, String[] strArr2, String str2, boolean z10) {
        ArrayList arrayList;
        ArrayList arrayList2 = null;
        if (!"VIEW".equals(str)) {
            strArr = "CLICK".equals(str) ? strArr2 : null;
        }
        if (strArr == null || strArr.length <= 0) {
            arrayList = null;
        } else {
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            for (String strG : strArr) {
                if (!TextUtils.isEmpty(strG)) {
                    if (strG.contains("https://monitor-check.ad.xiaomi.com")) {
                        strG = g(g(strG, SaveAreaResult.Columns.source, com.xiaomi.onetrack.c.d.a.f27722a), "uniqueId", str2);
                    }
                    if (z10 && strG.contains("https://monitor-check.ad.xiaomi.com")) {
                        arrayList3.add(strG);
                    } else {
                        arrayList4.add(strG);
                    }
                }
            }
            a1.g("AdAnalytics", "urlList one track doubleReport： " + z10 + "  ， checkUrlList: " + arrayList3 + "  ， realUrlList： " + arrayList4);
            arrayList2 = arrayList4;
            arrayList = arrayList3;
        }
        return z10 ? arrayList : arrayList2;
    }

    public static String g(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return str;
        }
        return str.replaceAll("(" + str2 + "=[^&]*)", str2 + "=" + str3);
    }

    public static void h(Context context, List list) {
        ArrayList arrayList = new ArrayList(list);
        if (arrayList.size() == 0) {
            return;
        }
        new AsyncTaskC0561a(arrayList, context).execute(new Void[0]);
    }
}
