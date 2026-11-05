package defpackage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageParser;
import android.content.pm.Signature;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.miui.guardprovider.GuardApplication;
import com.miui.guardprovider.manager.SecurityService;
import com.xiaomi.onetrack.util.a;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import miuix.animation.utils.EaseManager;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class ni2 extends Binder implements IInterface {
    public final /* synthetic */ int a = 0;
    public final /* synthetic */ Object b;

    public ni2(hp1 hp1Var) {
        this.b = hp1Var;
        attachInterface(this, "android.telephony.tcpka.IModemTcpKeepAliveIndCallback");
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        int i = this.a;
        return this;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:120:0x02c4 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:408:0x074e */
    /* JADX WARN: Code duplicated, block: B:143:0x033b A[Catch: all -> 0x027f, Exception -> 0x0283, TryCatch #12 {Exception -> 0x0283, all -> 0x027f, blocks: (B:95:0x025f, B:97:0x0265, B:99:0x0272, B:106:0x0287, B:108:0x0291, B:110:0x0298, B:112:0x02a4, B:144:0x0340, B:115:0x02aa, B:121:0x02c6, B:124:0x02ce, B:127:0x02e0, B:130:0x02e9, B:131:0x02fe, B:135:0x0306, B:140:0x0326, B:143:0x033b, B:118:0x02be), top: B:366:0x025f }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v30 */
    /* JADX WARN: Type inference failed for: r14v31 */
    /* JADX WARN: Type inference failed for: r14v43 */
    /* JADX WARN: Type inference failed for: r14v6, types: [int] */
    /* JADX WARN: Type inference failed for: r14v7, types: [int] */
    /* JADX WARN: Type inference failed for: r14v8, types: [boolean] */
    /* JADX WARN: Type inference failed for: r27v0, types: [android.os.Parcel] */
    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws Throwable {
        int i3;
        String[] strArr;
        int i4;
        PackageInfo packageInfo;
        SQLiteDatabase readableDatabase;
        int i5;
        int i6 = this.a;
        int i7 = 3;
        Object obj = this.b;
        switch (i6) {
            case 0:
                SecurityService securityService = (SecurityService) obj;
                if (i >= 1 && i <= 16777215) {
                    parcel.enforceInterface("com.miui.guardprovider.aidl.IAntiVirusServer");
                }
                if (i == 1598968902) {
                    parcel2.writeString("com.miui.guardprovider.aidl.IAntiVirusServer");
                    return true;
                }
                int i8 = 4;
                int i9 = -1;
                String str = null;
                cursor = null;
                cursor = null;
                cursor = null;
                Cursor cursor = null;
                String strB = null;
                String string = null;
                int iJ = 0;
                iJ = 0;
                iJ = 0;
                int iJ2 = 0;
                int iG = 0;
                int iG2 = 0;
                int iG3 = 0;
                int iG4 = 0;
                int i10 = 0;
                ?? r14 = 0;
                ?? E = 0;
                iJ = 0;
                iJ = 0;
                switch (i) {
                    case 1:
                        String[] strArrCreateStringArray = parcel.createStringArray();
                        mw0 mw0VarQ = q53.q(parcel.readStrongBinder());
                        boolean z = parcel.readInt() != 0;
                        int i11 = SecurityService.d;
                        if (!f03.E(securityService)) {
                            SecurityService.a("startScan on Server, but user is locked, skip.");
                            if (mw0VarQ != null) {
                                try {
                                    mw0VarQ.n(-1, null);
                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                }
                            }
                            break;
                        } else {
                            iJ = gc.f().j(strArrCreateStringArray, mw0VarQ, z, 0);
                        }
                        parcel2.writeNoException();
                        parcel2.writeInt(iJ);
                        return true;
                    case 2:
                        String string2 = parcel.readString();
                        boolean z2 = parcel.readInt() != 0;
                        int i12 = SecurityService.d;
                        if (f03.E(securityService)) {
                            gc.f().getClass();
                            for (bg0 bg0Var : bg0.values()) {
                                if ((z2 || !TextUtils.equals(bg0.MI.b, bg0Var.b)) && bg0Var.b.equals(string2)) {
                                    kk2.b().e(bg0Var.d, Boolean.valueOf(z2));
                                }
                            }
                        } else {
                            SecurityService.a("setEngineEnable on Server, but user is locked, skip.");
                        }
                        parcel2.writeNoException();
                        return true;
                    case 3:
                        String string3 = parcel.readString();
                        int i13 = SecurityService.d;
                        if (f03.E(securityService)) {
                            gc.f().getClass();
                            E = gc.e(string3);
                        } else {
                            SecurityService.a("getEngineEnable on Server, but user is locked, skip.");
                        }
                        parcel2.writeNoException();
                        parcel2.writeInt(E);
                        return true;
                    case 4:
                        String string4 = parcel.readString();
                        gc.f().getClass();
                        for (bg0 bg0Var2 : bg0.values()) {
                            if (bg0Var2.b.equals(string4)) {
                                r14 = bg0Var2.e;
                                parcel2.writeNoException();
                                parcel2.writeInt(r14);
                                return true;
                            }
                        }
                        parcel2.writeNoException();
                        parcel2.writeInt(r14);
                        return true;
                    case 5:
                        int i14 = parcel.readInt();
                        int i15 = SecurityService.d;
                        if (f03.E(securityService)) {
                            gc gcVarF = gc.f();
                            ConcurrentHashMap concurrentHashMap = gcVarF.b;
                            Log.i("AntivirusManager", "cancelScan id " + i14);
                            cc ccVarG = gc.g(i14);
                            mw0 mw0Var = ccVarG != null ? ccVarG.a : null;
                            if (mw0Var != null) {
                                List list = (List) concurrentHashMap.get(mw0Var);
                                if (list != null) {
                                    Iterator it = list.iterator();
                                    while (it.hasNext()) {
                                        ((cc) it.next()).a();
                                    }
                                }
                                concurrentHashMap.remove(mw0Var);
                                gcVarF.c.remove(mw0Var);
                                gcVarF.a.remove(mw0Var);
                                gcVarF.d.remove(mw0Var);
                                gcVarF.e.remove(mw0Var);
                                gcVarF.f.remove(mw0Var);
                            } else {
                                cc ccVarG2 = gc.g(i14);
                                if (ccVarG2 != null) {
                                    ccVarG2.a();
                                }
                            }
                        } else {
                            SecurityService.a("cancelScan on Server, but user is locked, skip.");
                        }
                        parcel2.writeNoException();
                        return true;
                    case 6:
                        q53.q(parcel.readStrongBinder());
                        int i16 = SecurityService.d;
                        if (f03.E(securityService)) {
                            gc.f().getClass();
                        } else {
                            SecurityService.a("checkUpdate on Server, but user is locked, skip.");
                        }
                        parcel2.writeNoException();
                        parcel2.writeInt(-1);
                        return true;
                    case 7:
                        mw0 mw0VarQ2 = q53.q(parcel.readStrongBinder());
                        int i17 = SecurityService.d;
                        if (!f03.E(securityService)) {
                            SecurityService.a("updateScan on Server, but user is locked, skip.");
                            f23 f23Var = new f23();
                            f23Var.b = a.c;
                            f23Var.c = 2;
                            if (mw0VarQ2 != null) {
                                try {
                                    mw0VarQ2.j(f23Var);
                                    mw0VarQ2.e(-1);
                                } catch (RemoteException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            i3 = 0;
                            break;
                        } else {
                            gc gcVarF2 = gc.f();
                            gcVarF2.getClass();
                            if (gc.g >= 14) {
                                if (mw0VarQ2 != null) {
                                    try {
                                        mw0VarQ2.e(-1);
                                        Log.d("AntivirusManager", "task number overflows !");
                                    } catch (RemoteException e3) {
                                        e3.printStackTrace();
                                    }
                                }
                                i3 = -1;
                            } else {
                                cc ccVar = new cc(gcVarF2, mw0VarQ2, null);
                                ccVar.c.a = gc.d(xr0.m());
                                qb qbVarD = qb.D();
                                g gVar = new g(i8);
                                gVar.c = ccVar;
                                VarHandle.storeStoreFence();
                                ((ExecutorService) qbVarD.b).execute(gVar);
                                i3 = ccVar.b;
                            }
                            break;
                        }
                        parcel2.writeNoException();
                        parcel2.writeInt(i3);
                        return true;
                    case 8:
                        int i18 = parcel.readInt();
                        int i19 = SecurityService.d;
                        if (f03.E(securityService)) {
                            gc.f().getClass();
                            cc ccVarG3 = gc.g(i18);
                            if (ccVarG3 != null) {
                                ccVarG3.b();
                            }
                        } else {
                            SecurityService.a("cancelUpdate on Server, but user is locked, skip.");
                        }
                        parcel2.writeNoException();
                        return true;
                    case 9:
                        parcel2.writeNoException();
                        parcel2.writeString("1");
                        return true;
                    case 10:
                        String string5 = parcel.readString();
                        gc.f().getClass();
                        for (bg0 bg0Var3 : bg0.values()) {
                            if (bg0Var3.b.equals(string5)) {
                                string = GuardApplication.application.getString(bg0Var3.f);
                                parcel2.writeNoException();
                                parcel2.writeString(string);
                                return true;
                            }
                        }
                        parcel2.writeNoException();
                        parcel2.writeString(string);
                        return true;
                    case 11:
                        gc.f().getClass();
                        String[] strArr2 = new String[4];
                        bg0[] bg0VarArrValues = bg0.values();
                        int length = bg0VarArrValues.length;
                        int i20 = 0;
                        while (i10 < length) {
                            strArr2[i20] = bg0VarArrValues[i10].b;
                            i10++;
                            i20++;
                        }
                        parcel2.writeNoException();
                        parcel2.writeStringArray(strArr2);
                        return true;
                    case 12:
                        String string6 = parcel.readString();
                        sw0 sw0VarQ = rw0.q(parcel.readStrongBinder());
                        int i21 = SecurityService.d;
                        if (f03.E(securityService)) {
                            iG4 = w63.f().g(string6, 2, sw0VarQ, null);
                        } else {
                            SecurityService.a("wifiDetectNetworkState on Server, but user is locked, skip.");
                        }
                        parcel2.writeNoException();
                        parcel2.writeInt(iG4);
                        return true;
                    case 13:
                        String string7 = parcel.readString();
                        sw0 sw0VarQ2 = rw0.q(parcel.readStrongBinder());
                        int i22 = SecurityService.d;
                        if (f03.E(securityService)) {
                            iG3 = w63.f().g(string7, 3, sw0VarQ2, null);
                        } else {
                            SecurityService.a("wifiDetectDnsAndPhishing on Server, but user is locked, skip.");
                        }
                        parcel2.writeNoException();
                        parcel2.writeInt(iG3);
                        return true;
                    case 14:
                        String string8 = parcel.readString();
                        sw0 sw0VarQ3 = rw0.q(parcel.readStrongBinder());
                        String string9 = parcel.readString();
                        int i23 = SecurityService.d;
                        if (f03.E(securityService)) {
                            iG2 = w63.f().g(string8, 4, sw0VarQ3, string9);
                        } else {
                            SecurityService.a("wifiDetectArp on Server, but user is locked, skip.");
                        }
                        parcel2.writeNoException();
                        parcel2.writeInt(iG2);
                        return true;
                    case 15:
                        String string10 = parcel.readString();
                        sw0 sw0VarQ4 = rw0.q(parcel.readStrongBinder());
                        int i24 = SecurityService.d;
                        if (f03.E(securityService)) {
                            iG = w63.f().g(string10, 5, sw0VarQ4, null);
                        } else {
                            SecurityService.a("wifiDetectSecurity on Server, but user is locked, skip.");
                        }
                        parcel2.writeNoException();
                        parcel2.writeInt(iG);
                        return true;
                    case 16:
                        int i25 = SecurityService.d;
                        if (f03.E(securityService)) {
                            String str2 = ie2.a;
                            try {
                                String strA = ie2.a(ie2.d(GuardApplication.application));
                                if (TextUtils.isEmpty(strA)) {
                                    strArr = new String[0];
                                } else {
                                    vo1.d(GuardApplication.application).f(strA);
                                    JSONObject jSONObject = new JSONObject(strA);
                                    if (jSONObject.has("error_code")) {
                                        strArr = new String[0];
                                    } else {
                                        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("data");
                                        ArrayList arrayList = new ArrayList();
                                        if (jSONArrayOptJSONArray != null) {
                                            for (int i26 = 0; i26 < jSONArrayOptJSONArray.length(); i26++) {
                                                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i26);
                                                if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.optInt("basisOf") == 2) {
                                                    arrayList.add(jSONObjectOptJSONObject.optString("pkg"));
                                                }
                                            }
                                        }
                                        strArr = new String[arrayList.size()];
                                        for (int i27 = 0; i27 < arrayList.size(); i27++) {
                                            strArr[i27] = (String) arrayList.get(i27);
                                        }
                                    }
                                }
                            } catch (Exception e4) {
                                Log.e("AntiDefraudAppManager", "getAllDetectUnsafeApps error, ", e4);
                                strArr = new String[0];
                            }
                        } else {
                            SecurityService.a("detectAppsScan on Server, but user is locked, skip.");
                            strArr = new String[0];
                        }
                        parcel2.writeNoException();
                        parcel2.writeStringArray(strArr);
                        return true;
                    case 17:
                        String string11 = parcel.readString();
                        int i28 = SecurityService.d;
                        if (f03.E(securityService)) {
                            strB = ie2.b(string11);
                        } else {
                            SecurityService.a("detectAppScan on Server, but user is locked, skip.");
                        }
                        parcel2.writeNoException();
                        parcel2.writeString(strB);
                        return true;
                    case 18:
                        int i29 = SecurityService.d;
                        if (f03.E(securityService)) {
                            ie2.e(GuardApplication.application);
                            i4 = 0;
                        } else {
                            SecurityService.a("detectAppsScan on Server, but user is locked, skip.");
                            i4 = -1;
                        }
                        parcel2.writeNoException();
                        parcel2.writeInt(i4);
                        return true;
                    case 19:
                        int i30 = u83.y(GuardApplication.application) ? !ch2.l(GuardApplication.application).k().isEmpty() ? 1 : 0 : 0;
                        parcel2.writeNoException();
                        parcel2.writeInt(i30);
                        return true;
                    case EaseManager.EaseStyleDef.DECELERATE /* 20 */:
                        gc.f().h(q53.q(parcel.readStrongBinder()), parcel.readInt() != 0);
                        parcel2.writeNoException();
                        return true;
                    case 21:
                        ArrayList<String> arrayListCreateStringArrayList = parcel.createStringArrayList();
                        gc.f().getClass();
                        ArrayList arrayList2 = new ArrayList(arrayListCreateStringArrayList);
                        GuardApplication guardApplication = GuardApplication.application;
                        sb.t(guardApplication);
                        for (String str3 : arrayListCreateStringArrayList) {
                            PackageInfo packageInfoR = u83.r(GuardApplication.application, str3);
                            if (packageInfoR != null) {
                                boolean zI = pp1.e(guardApplication).i(packageInfoR);
                                if (!zI) {
                                    zI = pp1.j(packageInfoR.applicationInfo.sourceDir);
                                }
                                if (zI) {
                                    arrayList2.remove(str3);
                                }
                            }
                        }
                        parcel2.writeNoException();
                        parcel2.writeStringList(arrayList2);
                        return true;
                    case 22:
                        String string12 = parcel.readString();
                        int i31 = parcel.readInt();
                        if (i31 != 2 && i31 != 3) {
                            if (i31 == 1) {
                                sb sbVarP = sb.p();
                                sbVarP.s(securityService);
                                Context context = (Context) sbVarP.c;
                                rb rbVar = (rb) sbVarP.d;
                                if (TextUtils.isEmpty(string12)) {
                                    Log.i("MiEngine", "return when packageName is null");
                                } else {
                                    try {
                                        packageInfo = securityService.getPackageManager().getPackageInfo(string12, 64);
                                    } catch (PackageManager.NameNotFoundException e5) {
                                        Log.e("MiEngine", "exception when getPackageInfo :", e5);
                                        packageInfo = null;
                                    }
                                    if (packageInfo != null) {
                                        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                                        if ((applicationInfo.flags & 1) == 0) {
                                            try {
                                                readableDatabase = rbVar.getReadableDatabase();
                                                try {
                                                    try {
                                                        Cursor cursorQuery = readableDatabase.query("antivirus_db", null, "package=?", new String[]{string12}, null, null, null, null);
                                                        if (cursorQuery != null) {
                                                            try {
                                                                if (cursorQuery.moveToFirst()) {
                                                                    new PackageParser.Package(string12).baseCodePath = applicationInfo.sourceDir;
                                                                    Signature[] signatureArr = packageInfo.signatures;
                                                                    if (signatureArr == null) {
                                                                        Log.i("MiEngine", "return when signatures null");
                                                                    } else {
                                                                        String strQ = u83.q(signatureArr);
                                                                        if (TextUtils.isEmpty(strQ)) {
                                                                            Log.i("MiEngine", "return when certificatesMd5 null");
                                                                        } else {
                                                                            boolean z3 = false;
                                                                            while (true) {
                                                                                int i32 = cursorQuery.getInt(cursorQuery.getColumnIndex("version"));
                                                                                if (i32 <= 0 || i32 == packageInfo.versionCode) {
                                                                                    String string13 = cursorQuery.getString(cursorQuery.getColumnIndex("signatures"));
                                                                                    int columnIndex = cursorQuery.getColumnIndex("strategy");
                                                                                    if ((columnIndex == i9 ? 0 : cursorQuery.getInt(columnIndex)) != 0 || (string13 != null && string13.equals(strQ))) {
                                                                                        String string14 = cursorQuery.getString(cursorQuery.getColumnIndex("md5"));
                                                                                        z3 = z3;
                                                                                        if (TextUtils.isEmpty(string14)) {
                                                                                            pt2 pt2Var = new pt2();
                                                                                            pt2Var.c = packageInfo.packageName;
                                                                                            pt2Var.b = packageInfo.versionCode;
                                                                                            i5 = cursorQuery.getInt(cursorQuery.getColumnIndex("type"));
                                                                                            pt2Var.a = i5;
                                                                                            if (i5 != 0 || i5 == 1) {
                                                                                                Log.d("MiEngine", "unknown or safe apk");
                                                                                            } else if (i5 == 5) {
                                                                                                pt2Var.f = strQ;
                                                                                                Log.i("MiEngine", "update riskgraydb");
                                                                                                pm.h(context).k(pt2Var);
                                                                                                pt2Var.a = 0;
                                                                                            } else if (z3) {
                                                                                            }
                                                                                        } else {
                                                                                            if (str == null) {
                                                                                                String strP = ys.p(applicationInfo.sourceDir);
                                                                                                if (strP != null) {
                                                                                                    Log.d("MiEngine", "file md5 = " + strP);
                                                                                                    str = strP;
                                                                                                }
                                                                                            }
                                                                                            if (string14.equals(str)) {
                                                                                                z3 = true;
                                                                                                pt2 pt2Var2 = new pt2();
                                                                                                pt2Var2.c = packageInfo.packageName;
                                                                                                pt2Var2.b = packageInfo.versionCode;
                                                                                                i5 = cursorQuery.getInt(cursorQuery.getColumnIndex("type"));
                                                                                                pt2Var2.a = i5;
                                                                                                if (i5 != 0) {
                                                                                                    Log.d("MiEngine", "unknown or safe apk");
                                                                                                } else {
                                                                                                    Log.d("MiEngine", "unknown or safe apk");
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                                if (cursorQuery.moveToNext()) {
                                                                                    i9 = -1;
                                                                                    z3 = z3;
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            } catch (Exception e6) {
                                                                e = e6;
                                                                cursor = cursorQuery;
                                                                Log.e("MiEngine", "exception happens when findInBWList:", e);
                                                                tv0.b(cursor);
                                                            } catch (Throwable th) {
                                                                th = th;
                                                                cursor = cursorQuery;
                                                                tv0.b(cursor);
                                                                tv0.b(readableDatabase);
                                                                throw th;
                                                            }
                                                        }
                                                        tv0.b(cursorQuery);
                                                    } catch (Exception e7) {
                                                        e = e7;
                                                    }
                                                } catch (Throwable th2) {
                                                    th = th2;
                                                }
                                            } catch (Exception e8) {
                                                e = e8;
                                                readableDatabase = null;
                                            } catch (Throwable th3) {
                                                th = th3;
                                                readableDatabase = null;
                                            }
                                            tv0.b(readableDatabase);
                                        }
                                    }
                                }
                            }
                            break;
                        } else {
                            qb qbVarD2 = qb.D();
                            h4 h4Var = new h4(4);
                            h4Var.c = this;
                            h4Var.d = string12;
                            VarHandle.storeStoreFence();
                            ((ExecutorService) qbVarD2.b).execute(h4Var);
                        }
                        parcel2.writeNoException();
                        return true;
                    case 23:
                        ArrayList<String> arrayListCreateStringArrayList2 = parcel.createStringArrayList();
                        gc.f().getClass();
                        ArrayList arrayList3 = new ArrayList(arrayListCreateStringArrayList2);
                        GuardApplication guardApplication2 = GuardApplication.application;
                        sb.t(guardApplication2);
                        for (String str4 : arrayListCreateStringArrayList2) {
                            boolean zI2 = pp1.e(guardApplication2).i(u83.s(GuardApplication.application, str4));
                            if (!zI2) {
                                zI2 = pp1.j(str4);
                            }
                            if (zI2) {
                                arrayList3.remove(str4);
                            }
                        }
                        parcel2.writeNoException();
                        parcel2.writeStringList(arrayList3);
                        return true;
                    case 24:
                        String[] strArrCreateStringArray2 = parcel.createStringArray();
                        mw0 mw0VarQ3 = q53.q(parcel.readStrongBinder());
                        boolean z4 = parcel.readInt() != 0;
                        int i33 = parcel.readInt();
                        int i34 = SecurityService.d;
                        if (!f03.E(securityService)) {
                            SecurityService.a("startScanAsScene on Server, but user is locked, skip.");
                            if (mw0VarQ3 != null) {
                                try {
                                    mw0VarQ3.n(-1, null);
                                } catch (RemoteException e9) {
                                    e9.printStackTrace();
                                }
                            }
                            break;
                        } else {
                            iJ2 = gc.f().j(strArrCreateStringArray2, mw0VarQ3, z4, i33);
                        }
                        parcel2.writeNoException();
                        parcel2.writeInt(iJ2);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            default:
                if (i != 1) {
                    if (i != 1598968902) {
                        return super.onTransact(i, parcel, parcel2, i2);
                    }
                    parcel2.writeString("android.telephony.tcpka.IModemTcpKeepAliveIndCallback");
                    return true;
                }
                parcel.enforceInterface("android.telephony.tcpka.IModemTcpKeepAliveIndCallback");
                String string15 = parcel.readString();
                Iterator it2 = ((CopyOnWriteArraySet) ((hp1) obj).f).iterator();
                while (it2.hasNext()) {
                    ((oj3) it2.next()).getClass();
                    kg3.f("HwKaMgr", "[Modem Callback] onTcpKaInd, result=" + string15);
                    if ("KEEP_ALIVE_RESULT_OK".equals(string15)) {
                        zj3 zj3Var = yj3.a;
                        Handler handler = zj3Var.c;
                        if (handler != null) {
                            vj3 vj3Var = new vj3(1);
                            vj3Var.c = zj3Var;
                            VarHandle.storeStoreFence();
                            handler.post(vj3Var);
                        }
                        hq3 hq3Var = (hq3) sj3.a.b;
                        AtomicLong atomicLong = (AtomicLong) hq3Var.e;
                        if (atomicLong.get() <= 0) {
                            atomicLong.set(SystemClock.elapsedRealtime());
                        }
                        ((AtomicInteger) hq3Var.c).incrementAndGet();
                    } else {
                        zj3 zj3Var2 = yj3.a;
                        String strA2 = ((bk3) zj3Var2.a.get()).a();
                        Handler handler2 = zj3Var2.c;
                        if (handler2 != null) {
                            uj3 uj3Var = new uj3(i7);
                            uj3Var.c = zj3Var2;
                            VarHandle.storeStoreFence();
                            handler2.post(uj3Var);
                        }
                        sc3 sc3Var = sj3.a;
                        sc3Var.getClass();
                        kg3.f("HwKaMgr", "[stat] onMdKaFail,result= " + string15 + " state=" + strA2);
                        HashMap map = new HashMap();
                        map.put("type_str", "md_ka_fail");
                        map.put("state_str", string15);
                        map.put("current_state", strA2);
                        sc3.w(map);
                        sc3.o(map);
                        sc3Var.n(strA2, "modemKeepAliveCallbackError");
                        sc3Var.x();
                    }
                    ti tiVar = nj3.a;
                    tiVar.getClass();
                    if ("KEEP_ALIVE_RESULT_OK".equals(string15)) {
                        tiVar.o("MODEM_CALLBACK_SUCCESS", 1L);
                    } else if ("KEEP_ALIVE_NETWORK_DOWN_ERROR".equals(string15)) {
                        tiVar.o("MODEM_CALLBACK_NETWORK_DOWN_ERROR", 1L);
                    } else if ("KEEP_ALIVE_RESULT_FAIL".equals(string15)) {
                        tiVar.o("MODEM_CALLBACK_RESULT_FAIL", 1L);
                    } else if ("KEEP_ALIVE_RESULT_ERROR".equals(string15)) {
                        tiVar.o("MODEM_CALLBACK_RESULT_ERROR", 1L);
                    } else {
                        tiVar.o("MODEM_CALLBACK_OTHER", 1L);
                    }
                }
                parcel2.writeNoException();
                return true;
        }
    }

    public ni2(SecurityService securityService) {
        this.b = securityService;
        attachInterface(this, "com.miui.guardprovider.aidl.IAntiVirusServer");
    }
}
