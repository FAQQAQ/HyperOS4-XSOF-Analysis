package com.miui.guardprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageParser;
import android.content.pm.PackageUserState;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import defpackage.bg0;
import defpackage.ch2;
import defpackage.gc;
import defpackage.j3;
import defpackage.kk2;
import defpackage.n53;
import defpackage.p53;
import defpackage.pm;
import defpackage.pp1;
import defpackage.pt2;
import defpackage.rb;
import defpackage.sb;
import defpackage.sr0;
import defpackage.u83;
import defpackage.xr0;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import miui.securitycenter.utils.SecurityCenterHelper;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GuardProvider extends ContentProvider {
    public static final UriMatcher d;
    public rb b;
    public Context c;

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        d = uriMatcher;
        uriMatcher.addURI("guard", "engine_info_list", 1005);
        uriMatcher.addURI("guard", "network_set", 1010);
        uriMatcher.addURI("guard", "sync_scan", 1006);
        uriMatcher.addURI("guard", "virus", 1007);
        uriMatcher.addURI("guard", "install_scan", 1008);
        uriMatcher.addURI("guard", "support_incremental", 1009);
    }

    /* JADX WARN: Code duplicated, block: B:32:0x00ed A[Catch: IOException -> 0x00f1, TRY_ENTER, TRY_LEAVE, TryCatch #5 {IOException -> 0x00f1, blocks: (B:32:0x00ed, B:38:0x00fb), top: B:53:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:47:0x0101 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:54:? A[SYNTHETIC] */
    public final MatrixCursor a(String str, String str2, boolean z) throws Throwable {
        Path path;
        Throwable th;
        int i;
        String string = str;
        try {
            try {
                try {
                    if (!string.startsWith("file://")) {
                        if (string.startsWith("content://")) {
                            Context context = getContext();
                            path = Paths.get(context.getCacheDir().getAbsolutePath(), "copy.apk");
                            try {
                                Files.copy(context.getContentResolver().openInputStream(Uri.parse(string)), path, StandardCopyOption.REPLACE_EXISTING);
                                string = path.toAbsolutePath().toString();
                            } catch (IOException | InterruptedException e) {
                                e = e;
                                Log.e(" GuardProvider", "syncScan: ", e);
                                if (path != null) {
                                    Files.deleteIfExists(path);
                                }
                            }
                        }
                        CountDownLatch countDownLatch = new CountDownLatch(1);
                        j3 j3Var = new j3(4);
                        j3Var.c = null;
                        i = gc.f().i(new String[]{string}, new sr0(this, j3Var, countDownLatch), z, 2, str2);
                        countDownLatch.await(2L, TimeUnit.MINUTES);
                        if (i >= 0 || ((p53) j3Var.c) == null) {
                            if (path != null) {
                                Files.deleteIfExists(path);
                                return null;
                            }
                            return null;
                        }
                        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"Type", "VirusName", "Description", "EngineName", "AdType", "AdName", "AdDescription", "Forbidden"});
                        Integer numValueOf = Integer.valueOf(((p53) j3Var.c).j);
                        p53 p53Var = (p53) j3Var.c;
                        String str3 = p53Var.d;
                        String str4 = p53Var.e;
                        String str5 = p53Var.f;
                        p53 p53Var2 = (p53) j3Var.c;
                        p53Var2.getClass();
                        matrixCursor.addRow(new Object[]{numValueOf, str3, str4, str5, 0, null, null, Integer.valueOf(p53Var2.m)});
                        if (path != null) {
                            try {
                                Files.deleteIfExists(path);
                                return matrixCursor;
                            } catch (IOException e2) {
                                Log.e(" GuardProvider", "syncScan: ", e2);
                            }
                        }
                        return matrixCursor;
                    }
                    string = Uri.parse(string).getPath();
                    path = null;
                    CountDownLatch countDownLatch2 = new CountDownLatch(1);
                    j3 j3Var2 = new j3(4);
                    j3Var2.c = null;
                    i = gc.f().i(new String[]{string}, new sr0(this, j3Var2, countDownLatch2), z, 2, str2);
                    countDownLatch2.await(2L, TimeUnit.MINUTES);
                    if (i >= 0) {
                    }
                    if (path != null) {
                        Files.deleteIfExists(path);
                        return null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    th = th;
                    if (0 != 0) {
                        throw th;
                    }
                    try {
                        Files.deleteIfExists(null);
                        throw th;
                    } catch (IOException e3) {
                        Log.e(" GuardProvider", "syncScan: ", e3);
                        throw th;
                    }
                }
            } catch (IOException | InterruptedException e4) {
                e = e4;
                path = null;
            } catch (Throwable th3) {
                th = th3;
                th = th;
                if (0 != 0) {
                    throw th;
                }
                Files.deleteIfExists(null);
                throw th;
            }
        } catch (IOException e5) {
            Log.e(" GuardProvider", "syncScan: ", e5);
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:109:0x0256  */
    /* JADX WARN: Code duplicated, block: B:117:0x029e  */
    /* JADX WARN: Code duplicated, block: B:146:0x0205 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:147:0x0254 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:148:0x028d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:149:0x026d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:151:0x01f1 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:152:0x01f1 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:155:0x0251 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:89:0x01f7  */
    /* JADX WARN: Code duplicated, block: B:94:0x020f  */
    @Override // android.content.ContentProvider
    public final Bundle call(String str, String str2, Bundle bundle) throws Throwable {
        SQLiteDatabase sQLiteDatabase;
        SQLiteDatabase writableDatabase;
        Cursor cursor;
        Cursor cursorQuery;
        JSONArray jSONArray;
        Iterator it;
        JSONObject jSONObject;
        pt2 pt2Var;
        File file;
        boolean z;
        PackageInfo packageInfoGeneratePackageInfo;
        if ("parseApk".equals(str)) {
            Log.d(" GuardProvider", "start parse apk : " + str2);
            Context context = getContext();
            Environment.getDataDirectory();
            PackageManager packageManager = context.getPackageManager();
            int i = bundle != null ? bundle.getInt("flags", 0) : 0;
            int i2 = (i & 64) != 0 ? 64 : 0;
            File file2 = new File(str2);
            if (file2.isDirectory()) {
                File[] fileArrListFiles = file2.listFiles();
                if (fileArrListFiles != null) {
                    int i3 = 0;
                    File file3 = null;
                    for (File file4 : fileArrListFiles) {
                        if (file4.getName().endsWith("apk")) {
                            i3++;
                            file3 = file4;
                        }
                    }
                    if (i3 == 1) {
                        file2 = file3;
                    }
                    file = file2;
                    z = false;
                } else {
                    file = file2;
                    z = true;
                }
            } else {
                file = file2;
                z = false;
            }
            if (z) {
                try {
                    PackageParser.Package r9 = new PackageParser().parsePackage(file, 0);
                    if ((i2 & 64) != 0) {
                        PackageParser.collectCertificates(r9, false);
                    }
                    packageInfoGeneratePackageInfo = PackageParser.generatePackageInfo(r9, (int[]) null, i2, 0L, 0L, (Set) null, new PackageUserState());
                } catch (Exception e) {
                    Log.e("PackageUtils", "packageInfo error " + str2, e);
                    packageInfoGeneratePackageInfo = null;
                }
            } else {
                packageInfoGeneratePackageInfo = context.getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), i2);
            }
            if (packageInfoGeneratePackageInfo != null) {
                packageInfoGeneratePackageInfo.applicationInfo.publicSourceDir = file.getAbsolutePath();
            }
            if (packageInfoGeneratePackageInfo == null) {
                Log.d(" GuardProvider", "parse apk failed !");
                return null;
            }
            Bundle bundle2 = new Bundle();
            ApplicationInfo applicationInfo = packageInfoGeneratePackageInfo.applicationInfo;
            if ((i & 128) != 0) {
                String label = SecurityCenterHelper.getLabel(context, applicationInfo.publicSourceDir, applicationInfo);
                Drawable applicationIcon = packageManager.getApplicationIcon(applicationInfo);
                if (applicationIcon instanceof BitmapDrawable) {
                    bundle2.putParcelable("icon", ((BitmapDrawable) applicationIcon).getBitmap());
                } else {
                    try {
                        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(applicationIcon.getIntrinsicWidth(), applicationIcon.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                        Canvas canvas = new Canvas(bitmapCreateBitmap);
                        applicationIcon.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                        applicationIcon.draw(canvas);
                        bundle2.putParcelable("icon", bitmapCreateBitmap);
                    } catch (Exception unused) {
                    }
                }
                bundle2.putString("label", label);
            }
            bundle2.putParcelable("pkgInfo", packageInfoGeneratePackageInfo);
            return bundle2;
        }
        if (!"getRiskAppData".equals(str)) {
            return super.call(str, str2, bundle);
        }
        Bundle bundle3 = new Bundle();
        boolean z2 = false;
        List<PackageInfo> installedPackages = getContext().getPackageManager().getInstalledPackages(0);
        rb rbVar = (rb) pm.h(getContext()).b;
        Log.i("GrayResultDBManager", "getAllGrayData");
        try {
            writableDatabase = rbVar.getWritableDatabase();
            try {
                try {
                    writableDatabase.beginTransaction();
                    writableDatabase.delete("risk_gray_result_db", "time <? ", new String[]{String.valueOf(System.currentTimeMillis() - (((long) ((SharedPreferences) kk2.b().b).getInt("virus_cache_validty_perod", 3)) * 86400000))});
                    writableDatabase.setTransactionSuccessful();
                } catch (Exception e2) {
                    e = e2;
                    Log.e("GrayResultDBManager", "deleteExpiredCacheInfos failed", e);
                }
            } catch (Throwable th) {
                th = th;
                sQLiteDatabase = writableDatabase;
                pm.e(sQLiteDatabase);
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            writableDatabase = null;
        } catch (Throwable th2) {
            th = th2;
            sQLiteDatabase = null;
            pm.e(sQLiteDatabase);
            throw th;
        }
        pm.e(writableDatabase);
        ArrayList arrayList = new ArrayList();
        try {
            cursorQuery = rbVar.getReadableDatabase().query("risk_gray_result_db", null, null, null, null, null, null);
            if (cursorQuery != null) {
                try {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            do {
                                arrayList.add(pm.g(cursorQuery));
                            } while (cursorQuery.moveToNext());
                        }
                    } catch (Exception e4) {
                        e = e4;
                        Log.e("GrayResultDBManager", "getAllGrayData failed", e);
                        if (cursorQuery != null) {
                        }
                        jSONArray = new JSONArray();
                        while (r3.hasNext()) {
                            if ((packageInfo.applicationInfo.flags & 1) != 1) {
                                it = arrayList.iterator();
                                while (true) {
                                    if (it.hasNext()) {
                                        jSONObject = null;
                                        break;
                                    }
                                    pt2Var = (pt2) it.next();
                                    if (!packageInfo.packageName.equals(pt2Var.c)) {
                                    }
                                }
                                if (jSONObject == null) {
                                    if (!z2) {
                                        Log.i(" GuardProvider", "updatewhite when has gray app");
                                        sb.t(this.c);
                                        z2 = true;
                                    }
                                    if (pp1.e(this.c).i(packageInfo)) {
                                        Log.i(" GuardProvider", "white package delete from resultdb " + packageInfo.packageName);
                                        pm.h(this.c).c(packageInfo.packageName);
                                    } else {
                                        jSONArray.put(jSONObject);
                                    }
                                }
                            }
                        }
                        bundle3.putString("riskData", jSONArray.toString());
                        return bundle3;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    cursor = cursorQuery;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Exception e5) {
            e = e5;
            cursorQuery = null;
        } catch (Throwable th4) {
            th = th4;
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        jSONArray = new JSONArray();
        for (PackageInfo packageInfo : installedPackages) {
            if ((packageInfo.applicationInfo.flags & 1) != 1) {
                it = arrayList.iterator();
                while (true) {
                    if (it.hasNext()) {
                        jSONObject = null;
                        break;
                    }
                    pt2Var = (pt2) it.next();
                    if (!packageInfo.packageName.equals(pt2Var.c) && packageInfo.versionCode == pt2Var.b) {
                        try {
                            jSONObject = new JSONObject();
                            try {
                                jSONObject.put("packageName", pt2Var.c);
                                jSONObject.put("versionCode", pt2Var.b);
                                jSONObject.put("type", pt2Var.a);
                                jSONObject.put("signaturesMd5", pt2Var.f);
                                break;
                            } catch (Exception e6) {
                                e = e6;
                                Log.e(" GuardProvider", "getRiskData json failed", e);
                            }
                        } catch (Exception e7) {
                            e = e7;
                            jSONObject = null;
                        }
                    }
                }
                if (jSONObject == null) {
                    if (!z2) {
                        Log.i(" GuardProvider", "updatewhite when has gray app");
                        sb.t(this.c);
                        z2 = true;
                    }
                    if (pp1.e(this.c).i(packageInfo)) {
                        Log.i(" GuardProvider", "white package delete from resultdb " + packageInfo.packageName);
                        pm.h(this.c).c(packageInfo.packageName);
                    } else {
                        jSONArray.put(jSONObject);
                    }
                }
            }
        }
        bundle3.putString("riskData", jSONArray.toString());
        return bundle3;
    }

    @Override // android.content.ContentProvider
    public final int delete(Uri uri, String str, String[] strArr) {
        SQLiteDatabase writableDatabase = this.b.getWritableDatabase();
        if (d.match(uri) != 1007) {
            return 0;
        }
        int iDelete = writableDatabase.delete("t_virus_white_list", str, strArr);
        if (iDelete > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
            ch2.l(getContext()).e(strArr);
        }
        return iDelete;
    }

    @Override // android.content.ContentProvider
    public final String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public final Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase writableDatabase = this.b.getWritableDatabase();
        int iMatch = d.match(uri);
        if (iMatch == 1007) {
            long jInsert = writableDatabase.insert("t_virus_white_list", "_id", contentValues);
            if (jInsert > 0) {
                Uri uriWithAppendedPath = Uri.withAppendedPath(uri, String.valueOf(jInsert));
                getContext().getContentResolver().notifyChange(uri, null);
                ch2.l(getContext()).e(new String[]{(String) contentValues.get("dir_path")});
                return uriWithAppendedPath;
            }
        } else if (iMatch == 1010) {
            kk2 kk2VarB = kk2.b();
            int iIntValue = contentValues.getAsInteger("network_status").intValue();
            SharedPreferences.Editor editorEdit = ((SharedPreferences) kk2VarB.b).edit();
            editorEdit.putInt("network_status", iIntValue);
            editorEdit.commit();
            return null;
        }
        return null;
    }

    @Override // android.content.ContentProvider
    public final boolean onCreate() {
        Context context = getContext();
        this.c = context;
        if (n53.a == null) {
            synchronized (n53.class) {
                try {
                    if (n53.a == null) {
                        n53.a = new rb(context.getApplicationContext(), "miui_virus.db", null, 5, 2);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        this.b = n53.a;
        return true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.content.ContentProvider
    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        String[] strArr3;
        Cursor cursorQuery = null;
        switch (d.match(uri)) {
            case 1005:
                MatrixCursor matrixCursor = new MatrixCursor(new String[]{"Name", "NameString", "DescriptionName", "DialogTitle", "DialogHeader", "DialogPolicy", "DialogCloudScan", "DialogSummary", "DialogUrl", "Enable", "SupportCloud"});
                for (bg0 bg0Var : (List) xr0.u().stream().collect(Collectors.toUnmodifiableList())) {
                    Object[] objArr = new Object[11];
                    objArr[0] = bg0Var.b;
                    System.arraycopy(this.c.getResources().getStringArray(bg0Var.g), 0, objArr, 1, 8);
                    gc gcVarF = gc.f();
                    String str3 = bg0Var.b;
                    gcVarF.getClass();
                    objArr[9] = Integer.valueOf(gc.e(str3) ? 1 : 0);
                    objArr[10] = Integer.valueOf(bg0Var.e ? 1 : 0);
                    matrixCursor.addRow(objArr);
                }
                return matrixCursor;
            case 1006:
                if (strArr2 != null && strArr2.length == 2) {
                    return a(strArr2[0], null, Boolean.parseBoolean(strArr2[1]));
                }
                return null;
            case 1007:
                strArr3 = strArr2;
                cursorQuery = this.b.getWritableDatabase().query("t_virus_white_list", strArr, str, strArr3, null, null, str2);
                if (strArr3 != null || strArr3.length < 3) {
                    return cursorQuery;
                }
                return a(strArr3[0], strArr3[2], Boolean.parseBoolean(strArr3[1]));
            case 1008:
                strArr3 = strArr2;
                if (strArr3 != null) {
                    break;
                }
                return cursorQuery;
            case 1009:
                if (u83.y(getContext())) {
                    MatrixCursor matrixCursor2 = new MatrixCursor(new String[]{"support"});
                    matrixCursor2.addRow(new Object[]{1});
                    return matrixCursor2;
                }
                return null;
            case 1010:
                MatrixCursor matrixCursor3 = new MatrixCursor(new String[]{"network_status"});
                matrixCursor3.addRow(new Integer[]{Integer.valueOf(((SharedPreferences) kk2.b().b).getInt("network_status", 0))});
                return matrixCursor3;
            default:
                return null;
        }
    }

    @Override // android.content.ContentProvider
    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
