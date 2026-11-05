package com.miui.securityscan.scanner;

import aj.z;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.miui.common.utils.b0;
import com.miui.luckymoney.config.CommonPerConstants;
import com.miui.securitycenter.Application;
import com.miui.securitycenter.memory.MemoryModel;
import com.miui.securityscan.model.AbsModel;
import com.miui.securityscan.model.GroupModel;
import com.miui.securityscan.model.system.VirusScanModel;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import pj.n;

/* JADX INFO: loaded from: classes3.dex */
public class ScoreManager {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private static ScoreManager f24831p;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f24838g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private long f24839h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private long f24840i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private long f24841j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private long f24842k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f24843l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f24844m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private List f24845n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List f24832a = Collections.synchronizedList(new ArrayList());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private List f24833b = Collections.synchronizedList(new ArrayList());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Map f24834c = new ConcurrentHashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Map f24835d = new ConcurrentHashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Map f24836e = new ConcurrentHashMap();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f24837f = false;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private int f24846o = 0;

    private ScoreManager() {
    }

    private int f() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j10 = this.f24842k;
        if (j10 == 0) {
            return 0;
        }
        long j11 = jCurrentTimeMillis - j10;
        if (j11 > 259200000) {
            return 15;
        }
        if (j11 > CommonPerConstants.DEFAULT.DEFAULT_UPDATE_FREQUENCY_DEFAULT) {
            return 10;
        }
        if (j11 > 86400000) {
            return 8;
        }
        if (j11 > 43200000) {
            return 5;
        }
        return j11 > 21600000 ? 3 : 0;
    }

    public static synchronized ScoreManager k() {
        try {
            if (f24831p == null) {
                f24831p = new ScoreManager();
            }
        } catch (Throwable th2) {
            throw th2;
        }
        return f24831p;
    }

    private int p(long j10) {
        if (System.currentTimeMillis() - this.f24842k < 300000) {
            return 0;
        }
        try {
            long jN = b0.n();
            Log.d("ScoreManager", "tm = " + jN);
            if (jN > 0) {
                int i10 = jN == 0 ? 0 : (int) ((j10 * 100) / jN);
                if (i10 >= 50) {
                    return 15;
                }
                if (i10 >= 40) {
                    return 10;
                }
                if (i10 >= 30) {
                    return 8;
                }
                return i10 >= 20 ? 5 : 0;
            }
        } catch (Exception e10) {
            Log.e("ScoreManager", "getMemoryMinusPredictScore", e10);
        }
        return 0;
    }

    private int q() {
        Log.d("ScoreManager", "getMinusPredictScore------------------------------------------------ ");
        Iterator it = this.f24832a.iterator();
        int score = 0;
        int score2 = 0;
        while (it.hasNext()) {
            for (AbsModel absModel : ((GroupModel) it.next()).getModelList()) {
                if (absModel.isSafe() == AbsModel.State.DANGER) {
                    score2 += absModel.getScore();
                    Log.d("ScoreManager", "SystemModel Minus ItemKey ==> " + absModel.getItemKey());
                }
            }
        }
        Log.d("ScoreManager", "SystemModel Minus Score = " + score2);
        int iP = p(h());
        Log.d("ScoreManager", "Memory Minus Score = " + iP);
        int i10 = score2 + iP;
        int iF = f();
        Log.d("ScoreManager", "Cache Minus Score = " + iF);
        int i11 = i10 + iF;
        this.f24837f = i11 == 0;
        Iterator it2 = this.f24833b.iterator();
        while (it2.hasNext()) {
            for (AbsModel absModel2 : ((GroupModel) it2.next()).getModelList()) {
                if (absModel2.isSafe() == AbsModel.State.DANGER) {
                    score += absModel2.getScore();
                    Log.d("ScoreManager", "ManualModel Minus ItemKey ==> " + absModel2.getItemKey());
                }
            }
        }
        Log.d("ScoreManager", "Manual Minus Score = " + score);
        int i12 = i11 + score;
        this.f24846o = i12;
        z.i0(Application.B(), this.f24846o);
        if (C() || i12 >= 41) {
            return i12;
        }
        return 41;
    }

    public boolean A() {
        return !this.f24834c.isEmpty();
    }

    public void B() {
        this.f24842k = n.i(0L);
        this.f24844m = w4.l.g();
        this.f24843l = w4.l.h();
        Log.i("ScoreManager", "initLastScore ==mLastVirusCount>" + this.f24843l + "mLastRiskCount>" + this.f24844m);
        this.f24846o = z.p(Application.B());
    }

    public boolean C() {
        return z() <= 0 && this.f24834c.isEmpty();
    }

    public boolean D() {
        long j10 = Settings.Secure.getLong(Application.B().getContentResolver(), "key_latest_virus_scan_date", 0L);
        long jCurrentTimeMillis = System.currentTimeMillis() - j10;
        if (j10 <= 0 || jCurrentTimeMillis <= 0) {
            return z() <= 0 && y() <= 0;
        }
        return z() <= 0 && y() <= 0 && jCurrentTimeMillis <= 259200000;
    }

    protected void E(String str) {
        Log.d("removeAppInfo", "size before " + this.f24835d.size());
        this.f24835d.remove(str);
        Log.d("removeAppInfo", "size before " + this.f24835d.size());
    }

    public void F(String str) {
        this.f24834c.remove(str);
    }

    public void G(int i10) {
        List list = this.f24833b;
        if (list == null || list.isEmpty()) {
            return;
        }
        Iterator it = this.f24833b.iterator();
        boolean z10 = false;
        while (it.hasNext()) {
            for (AbsModel absModel : ((GroupModel) it.next()).getModelList()) {
                if (absModel.getIndex() == i10) {
                    absModel.scan();
                    z10 = true;
                    break;
                }
            }
            if (z10) {
                return;
            }
        }
    }

    protected void H() {
        this.f24832a.clear();
        this.f24833b.clear();
        this.f24835d.clear();
        this.f24836e.clear();
    }

    public void I() {
        long memorySize = 0;
        long memorySize2 = 0;
        for (ResultModel resultModel : this.f24836e.values()) {
            if (resultModel.isChecked()) {
                memorySize2 += resultModel.getMemorySize();
            }
        }
        this.f24840i = memorySize2;
        Iterator it = this.f24836e.values().iterator();
        while (it.hasNext()) {
            memorySize += ((ResultModel) it.next()).getMemorySize();
        }
        this.f24841j = memorySize;
    }

    public void J() {
        long jB = 0;
        for (ij.c cVar : this.f24835d.values()) {
            if (cVar.f()) {
                jB += cVar.b();
            }
        }
        this.f24839h = jB;
    }

    public void K(long j10) {
        this.f24842k = j10;
    }

    public void L(int i10) {
        this.f24844m = i10;
    }

    protected void M(List list) {
        this.f24833b = Collections.synchronizedList(list);
    }

    protected void N(List list) {
        this.f24832a = Collections.synchronizedList(list);
    }

    public void O(int i10) {
        this.f24838g = i10;
    }

    public void P() {
        this.f24845n = new ArrayList(this.f24834c.values());
    }

    public void Q(int i10) {
        this.f24843l = i10;
    }

    public void R(long j10) {
        this.f24841j = j10;
    }

    public void S(AbsModel absModel, AbsModel.State state) {
        List list = this.f24833b;
        if (list == null || absModel == null) {
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            for (AbsModel absModel2 : ((GroupModel) it.next()).getModelList()) {
                String itemKey = absModel2.getItemKey();
                if (!TextUtils.isEmpty(itemKey) && itemKey.equals(absModel.getItemKey())) {
                    absModel2.setSafe(state);
                }
            }
        }
    }

    public void T() {
        z.i0(Application.B(), this.f24846o);
    }

    public void U() {
        List listX = x();
        int i10 = 0;
        if (listX == null || listX.isEmpty()) {
            this.f24844m = 0;
            this.f24843l = 0;
            w4.l.C(0);
            w4.l.D(0);
            Log.d("ScoreManager", "[updateRiskAndVirusCount] mVirusList empty, reset to 0");
            return;
        }
        Iterator it = listX.iterator();
        while (it.hasNext()) {
            if (((y4.g) it.next()).d() == w4.e.d.RISK_APP) {
                i10++;
            }
        }
        this.f24844m = i10;
        int size = listX.size() - i10;
        this.f24843l = size;
        w4.l.D(size);
        w4.l.C(this.f24844m);
        Log.d("ScoreManager", "[updateRiskAndVirusCount] from mVirusList: size=" + listX.size() + " virus=" + this.f24843l + " risk=" + i10);
    }

    public void V(Context context) {
        int i10;
        ApplicationInfo applicationInfo;
        if (this.f24834c.isEmpty()) {
            return;
        }
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f24834c.keySet().iterator();
        while (true) {
            i10 = 0;
            if (!it.hasNext()) {
                break;
            }
            String str = (String) it.next();
            y4.g gVar = (y4.g) this.f24834c.get(str);
            if (gVar != null) {
                if (gVar.c() == w4.e.c.INSTALLED_APP) {
                    try {
                        applicationInfo = packageManager.getApplicationInfo(gVar.b(), 0);
                    } catch (PackageManager.NameNotFoundException unused) {
                        applicationInfo = null;
                    }
                    if (applicationInfo == null) {
                        arrayList.add(str);
                    }
                } else if (gVar.c() == w4.e.c.UNINSTALLED_APK) {
                    String strE = gVar.e();
                    if (TextUtils.isEmpty(strE) || !new File(strE).exists()) {
                        arrayList.add(str);
                    }
                }
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        int size = arrayList.size();
        while (i10 < size) {
            Object obj = arrayList.get(i10);
            i10++;
            F((String) obj);
        }
        U();
    }

    public void W() {
        for (GroupModel groupModel : this.f24832a) {
            Iterator<AbsModel> it = groupModel.getModelList().iterator();
            while (it.hasNext()) {
                if (it.next() instanceof VirusScanModel) {
                    groupModel.scan();
                    return;
                }
            }
        }
    }

    protected void a(ResultModel resultModel) {
        this.f24836e.put(resultModel.getPackageName(), resultModel);
    }

    protected void b(ij.c cVar) {
        this.f24835d.put(cVar.d(), cVar);
    }

    public void c(y4.g gVar) {
        if (TextUtils.isEmpty(gVar.e())) {
            return;
        }
        this.f24834c.put(gVar.e(), gVar);
    }

    public void d() {
        this.f24834c.clear();
    }

    public long e() {
        return this.f24841j;
    }

    public long g() {
        long memorySize = 0;
        for (ResultModel resultModel : this.f24836e.values()) {
            if (resultModel.isChecked()) {
                memorySize += resultModel.getMemorySize();
            }
        }
        return memorySize;
    }

    public long h() {
        long jB = 0;
        for (ij.c cVar : this.f24835d.values()) {
            if (cVar.f()) {
                jB += cVar.b();
            }
        }
        return jB;
    }

    public long i() {
        return this.f24839h;
    }

    public int j() {
        int iR = r();
        if (!C() && iR < 41) {
            iR = 41;
        }
        if (iR > 100) {
            iR = 100;
        } else if (iR < 0) {
            iR = 0;
        }
        return 100 - iR;
    }

    public int l() {
        Iterator it = this.f24833b.iterator();
        int score = 0;
        while (it.hasNext()) {
            for (AbsModel absModel : ((GroupModel) it.next()).getModelList()) {
                if (absModel.isSafe() == AbsModel.State.DANGER) {
                    score += absModel.getScore();
                }
            }
        }
        return score;
    }

    public List m() {
        return this.f24833b;
    }

    public List n() {
        ArrayList arrayList = new ArrayList();
        for (GroupModel groupModel : this.f24833b) {
            Iterator<AbsModel> it = groupModel.getModelList().iterator();
            while (it.hasNext()) {
                if (it.next().isSafe() != AbsModel.State.SAFE) {
                    arrayList.add(groupModel);
                    break;
                }
            }
        }
        return arrayList;
    }

    public List o() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f24835d.keySet().iterator();
        while (it.hasNext()) {
            ij.c cVar = (ij.c) this.f24835d.get((String) it.next());
            if (cVar.f()) {
                arrayList.add(cVar);
            }
        }
        return arrayList;
    }

    public int r() {
        return z.p(Application.B());
    }

    public int s() {
        int iQ = q();
        if (iQ > 100) {
            iQ = 100;
        } else if (iQ < 0) {
            iQ = 0;
        }
        return 100 - iQ;
    }

    public List t() {
        return this.f24832a;
    }

    public List u() {
        ArrayList arrayList = new ArrayList();
        for (GroupModel groupModel : this.f24832a) {
            Iterator<AbsModel> it = groupModel.getModelList().iterator();
            while (it.hasNext()) {
                if (it.next().isSafe() != AbsModel.State.SAFE) {
                    arrayList.add(groupModel);
                    break;
                }
            }
        }
        return arrayList;
    }

    public List v() {
        ArrayList arrayList = new ArrayList();
        for (GroupModel groupModel : this.f24832a) {
            for (AbsModel absModel : groupModel.getModelList()) {
                if (absModel.isSafe() == AbsModel.State.SAFE && !absModel.isScanHide()) {
                    arrayList.add(groupModel);
                    break;
                }
            }
        }
        return arrayList;
    }

    public int w() {
        return this.f24838g;
    }

    public List x() {
        return new ArrayList(this.f24834c.values());
    }

    public int y() {
        Log.i("ScoreManager", "mVirusList>>" + this.f24834c.size());
        return this.f24834c.size();
    }

    public int z() {
        Log.i("ScoreManager", "mLastVirusCount:" + this.f24843l + " ,mLastRiskCount:" + this.f24844m);
        return this.f24843l + this.f24844m;
    }

    public static class ResultModel extends MemoryModel {
        private List<String> infoList = new ArrayList();
        private boolean isChecked;

        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
        public ResultModel() {
        }

        public void addInfo(String str) {
            this.infoList.add(str);
        }

        public List<String> getInfoList() {
            return this.infoList;
        }

        public boolean isChecked() {
            return this.isChecked;
        }

        public void setChecked(boolean z10) {
            this.isChecked = z10;
        }

        public ResultModel(MemoryModel memoryModel) {
            setAppName(memoryModel.getAppName());
            setLockState(memoryModel.getLockState());
            setMemorySize(memoryModel.getMemorySize());
            setPackageName(memoryModel.getPackageName());
        }
    }
}
