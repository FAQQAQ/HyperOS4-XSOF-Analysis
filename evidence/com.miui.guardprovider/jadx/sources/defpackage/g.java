package defpackage;

import android.R;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Process;
import android.os.SystemClock;
import android.os.Trace;
import android.text.TextUtils;
import android.util.Log;
import androidx.appcompat.widget.Toolbar;
import com.miui.guardprovider.GuardApplication;
import com.miui.guardprovider.action.impl.c;
import com.xiaomi.onetrack.api.al;
import com.xiaomi.onetrack.b.l;
import com.xiaomi.onetrack.util.a;
import com.xiaomi.onetrack.util.aa;
import java.lang.invoke.VarHandle;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import miui.os.Build;
import miuix.appcompat.app.e;
import miuix.appcompat.internal.app.widget.ActionBarContextView;
import miuix.appcompat.internal.app.widget.ActionBarOverlayLayout;
import miuix.appcompat.internal.app.widget.ActionBarView;
import org.json.JSONArray;
import org.json.JSONObject;
import tmsdkobf.j4;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ int b;
    public /* synthetic */ Object c;

    /* JADX DEBUG: Marked for inline */
    /* JADX DEBUG: Method not inlined, still used in: [androidx.appcompat.widget.Toolbar.<init>(android.content.Context, android.util.AttributeSet, int):void, com.miui.guardprovider.GuardApplication.onCreate():void, j6.e():java.lang.Object, mh2.a():ws2, miuix.appcompat.app.e.show():void, miuix.appcompat.internal.app.widget.ActionBarContextView.onRestoreInstanceState(android.os.Parcelable):void, miuix.appcompat.internal.app.widget.ActionBarOverlayLayout.onConfigurationChanged(android.content.res.Configuration):void, miuix.appcompat.internal.app.widget.ActionBarView.onRestoreInstanceState(android.os.Parcelable):void, miuix.appcompat.internal.app.widget.ActionBarView.w(boolean):void, ni2.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean, o30.g():void, oq1.a(oq1):void, p22.run():void, tmsdkobf.j4.a(tmsdkobf.j4$a):void, y53.c(int, java.lang.String):void] */
    public /* synthetic */ g(int i) {
        this.b = i;
    }

    /* JADX DEBUG: Class process forced to load method for inline: tmsdkobf.j4.c(tmsdkobf.j4$a):void */
    /* JADX WARN: Code duplicated, block: B:161:0x0359 A[PHI: r10 r11
      0x0359: PHI (r10v29 android.database.sqlite.SQLiteDatabase) = 
      (r10v5 android.database.sqlite.SQLiteDatabase)
      (r10v30 android.database.sqlite.SQLiteDatabase)
      (r10v30 android.database.sqlite.SQLiteDatabase)
     binds: [B:170:0x036f, B:152:0x032e, B:154:0x0334] A[DONT_GENERATE, DONT_INLINE]
      0x0359: PHI (r11v13 android.database.Cursor) = (r11v2 android.database.Cursor), (r11v15 android.database.Cursor), (r11v15 android.database.Cursor) binds: [B:170:0x036f, B:152:0x032e, B:154:0x0334] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:174:0x0376  */
    /* JADX WARN: Code duplicated, block: B:175:0x0379  */
    /* JADX WARN: Code duplicated, block: B:178:0x037e  */
    /* JADX WARN: Code duplicated, block: B:179:0x0383  */
    /* JADX WARN: Code duplicated, block: B:185:0x0399  */
    /* JADX WARN: Code duplicated, block: B:189:0x03d0  */
    /* JADX WARN: Code duplicated, block: B:192:0x03f1  */
    /* JADX WARN: Code duplicated, block: B:193:0x03f4 A[Catch: Exception -> 0x0526, TRY_LEAVE, TryCatch #13 {Exception -> 0x0526, blocks: (B:190:0x03e4, B:193:0x03f4, B:229:0x051e, B:232:0x0528, B:233:0x052e, B:195:0x0401, B:197:0x0431, B:201:0x043a, B:202:0x0441, B:204:0x0447, B:206:0x0473, B:209:0x0481, B:211:0x049d, B:219:0x04bb, B:221:0x04d3, B:222:0x04d6, B:224:0x04f2, B:226:0x0505, B:227:0x050c, B:225:0x04fe, B:214:0x04a8, B:216:0x04b0, B:208:0x047c, B:228:0x051b), top: B:281:0x03e4, inners: #10 }] */
    /* JADX WARN: Code duplicated, block: B:197:0x0431 A[Catch: all -> 0x0436, TryCatch #10 {all -> 0x0436, blocks: (B:195:0x0401, B:197:0x0431, B:201:0x043a, B:202:0x0441, B:204:0x0447, B:206:0x0473, B:209:0x0481, B:211:0x049d, B:219:0x04bb, B:221:0x04d3, B:222:0x04d6, B:224:0x04f2, B:226:0x0505, B:227:0x050c, B:225:0x04fe, B:214:0x04a8, B:216:0x04b0, B:208:0x047c, B:228:0x051b), top: B:276:0x0401, outer: #13 }] */
    /* JADX WARN: Code duplicated, block: B:200:0x0439  */
    /* JADX WARN: Code duplicated, block: B:204:0x0447 A[Catch: all -> 0x0436, TryCatch #10 {all -> 0x0436, blocks: (B:195:0x0401, B:197:0x0431, B:201:0x043a, B:202:0x0441, B:204:0x0447, B:206:0x0473, B:209:0x0481, B:211:0x049d, B:219:0x04bb, B:221:0x04d3, B:222:0x04d6, B:224:0x04f2, B:226:0x0505, B:227:0x050c, B:225:0x04fe, B:214:0x04a8, B:216:0x04b0, B:208:0x047c, B:228:0x051b), top: B:276:0x0401, outer: #13 }] */
    /* JADX WARN: Code duplicated, block: B:206:0x0473 A[Catch: all -> 0x0436, TryCatch #10 {all -> 0x0436, blocks: (B:195:0x0401, B:197:0x0431, B:201:0x043a, B:202:0x0441, B:204:0x0447, B:206:0x0473, B:209:0x0481, B:211:0x049d, B:219:0x04bb, B:221:0x04d3, B:222:0x04d6, B:224:0x04f2, B:226:0x0505, B:227:0x050c, B:225:0x04fe, B:214:0x04a8, B:216:0x04b0, B:208:0x047c, B:228:0x051b), top: B:276:0x0401, outer: #13 }] */
    /* JADX WARN: Code duplicated, block: B:208:0x047c A[Catch: all -> 0x0436, TryCatch #10 {all -> 0x0436, blocks: (B:195:0x0401, B:197:0x0431, B:201:0x043a, B:202:0x0441, B:204:0x0447, B:206:0x0473, B:209:0x0481, B:211:0x049d, B:219:0x04bb, B:221:0x04d3, B:222:0x04d6, B:224:0x04f2, B:226:0x0505, B:227:0x050c, B:225:0x04fe, B:214:0x04a8, B:216:0x04b0, B:208:0x047c, B:228:0x051b), top: B:276:0x0401, outer: #13 }] */
    /* JADX WARN: Code duplicated, block: B:211:0x049d A[Catch: all -> 0x0436, TryCatch #10 {all -> 0x0436, blocks: (B:195:0x0401, B:197:0x0431, B:201:0x043a, B:202:0x0441, B:204:0x0447, B:206:0x0473, B:209:0x0481, B:211:0x049d, B:219:0x04bb, B:221:0x04d3, B:222:0x04d6, B:224:0x04f2, B:226:0x0505, B:227:0x050c, B:225:0x04fe, B:214:0x04a8, B:216:0x04b0, B:208:0x047c, B:228:0x051b), top: B:276:0x0401, outer: #13 }] */
    /* JADX WARN: Code duplicated, block: B:213:0x04a7  */
    /* JADX WARN: Code duplicated, block: B:214:0x04a8 A[Catch: all -> 0x0436, TryCatch #10 {all -> 0x0436, blocks: (B:195:0x0401, B:197:0x0431, B:201:0x043a, B:202:0x0441, B:204:0x0447, B:206:0x0473, B:209:0x0481, B:211:0x049d, B:219:0x04bb, B:221:0x04d3, B:222:0x04d6, B:224:0x04f2, B:226:0x0505, B:227:0x050c, B:225:0x04fe, B:214:0x04a8, B:216:0x04b0, B:208:0x047c, B:228:0x051b), top: B:276:0x0401, outer: #13 }] */
    /* JADX WARN: Code duplicated, block: B:216:0x04b0 A[Catch: all -> 0x0436, TryCatch #10 {all -> 0x0436, blocks: (B:195:0x0401, B:197:0x0431, B:201:0x043a, B:202:0x0441, B:204:0x0447, B:206:0x0473, B:209:0x0481, B:211:0x049d, B:219:0x04bb, B:221:0x04d3, B:222:0x04d6, B:224:0x04f2, B:226:0x0505, B:227:0x050c, B:225:0x04fe, B:214:0x04a8, B:216:0x04b0, B:208:0x047c, B:228:0x051b), top: B:276:0x0401, outer: #13 }] */
    /* JADX WARN: Code duplicated, block: B:218:0x04b9  */
    /* JADX WARN: Code duplicated, block: B:221:0x04d3 A[Catch: all -> 0x0436, TryCatch #10 {all -> 0x0436, blocks: (B:195:0x0401, B:197:0x0431, B:201:0x043a, B:202:0x0441, B:204:0x0447, B:206:0x0473, B:209:0x0481, B:211:0x049d, B:219:0x04bb, B:221:0x04d3, B:222:0x04d6, B:224:0x04f2, B:226:0x0505, B:227:0x050c, B:225:0x04fe, B:214:0x04a8, B:216:0x04b0, B:208:0x047c, B:228:0x051b), top: B:276:0x0401, outer: #13 }] */
    /* JADX WARN: Code duplicated, block: B:224:0x04f2 A[Catch: all -> 0x0436, TryCatch #10 {all -> 0x0436, blocks: (B:195:0x0401, B:197:0x0431, B:201:0x043a, B:202:0x0441, B:204:0x0447, B:206:0x0473, B:209:0x0481, B:211:0x049d, B:219:0x04bb, B:221:0x04d3, B:222:0x04d6, B:224:0x04f2, B:226:0x0505, B:227:0x050c, B:225:0x04fe, B:214:0x04a8, B:216:0x04b0, B:208:0x047c, B:228:0x051b), top: B:276:0x0401, outer: #13 }] */
    /* JADX WARN: Code duplicated, block: B:225:0x04fe A[Catch: all -> 0x0436, TryCatch #10 {all -> 0x0436, blocks: (B:195:0x0401, B:197:0x0431, B:201:0x043a, B:202:0x0441, B:204:0x0447, B:206:0x0473, B:209:0x0481, B:211:0x049d, B:219:0x04bb, B:221:0x04d3, B:222:0x04d6, B:224:0x04f2, B:226:0x0505, B:227:0x050c, B:225:0x04fe, B:214:0x04a8, B:216:0x04b0, B:208:0x047c, B:228:0x051b), top: B:276:0x0401, outer: #13 }] */
    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        Cursor cursor;
        SQLiteDatabase readableDatabase;
        Cursor cursorQuery;
        lk1 lk1Var;
        int i;
        String strA;
        String strC;
        String str;
        String str2;
        boolean z;
        JSONObject jSONObject;
        SQLiteDatabase writableDatabase;
        String str3;
        boolean z2;
        JSONArray jSONArray;
        int i2;
        JSONObject jSONObject2;
        boolean z3;
        String strOptString;
        String strOptString2;
        String str4;
        JSONObject jSONObjectOptJSONObject;
        String[] strArr;
        String str5;
        String str6;
        String str7;
        ContentValues contentValues;
        boolean z4 = true;
        boolean z5 = false;
        switch (this.b) {
            case 0:
                ((l) this.c).o();
                return;
            case 1:
                n3 n3Var = ((l3) this.c).b;
                n3Var.w(n3Var.l, n3Var.m);
                return;
            case 2:
                ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) this.c;
                int i3 = ActionBarOverlayLayout.k0;
                if (actionBarOverlayLayout.isAttachedToWindow()) {
                    ActionBarContextView actionBarContextView = actionBarOverlayLayout.h;
                    if (actionBarContextView != null) {
                        actionBarContextView.m();
                    }
                    ActionBarView actionBarView = actionBarOverlayLayout.b;
                    if (actionBarView != null) {
                        actionBarView.m();
                    }
                    if (actionBarOverlayLayout.I != null) {
                        sh1 sh1Var = actionBarOverlayLayout.l;
                        if (sh1Var != null ? sh1Var.l().c.equals(nh1.f) : true) {
                            return;
                        }
                        actionBarOverlayLayout.I.close();
                        return;
                    }
                    return;
                }
                return;
            case 3:
                e eVar = (e) this.c;
                if (eVar.h.H0) {
                    eVar.dismiss();
                    return;
                }
                return;
            case 4:
                cc ccVar = (cc) this.c;
                zs2 zs2Var = ccVar.c;
                zs2Var.c = true;
                zs2Var.d = zs2Var.a.size();
                for (o oVar : zs2Var.a) {
                    q63 q63Var = new q63();
                    q63Var.c = ccVar;
                    q63Var.b = oVar;
                    VarHandle.storeStoreFence();
                    if (oVar.c()) {
                        ge3 ge3Var = new ge3();
                        ge3Var.b = q63Var;
                        ge3Var.c = oVar;
                        VarHandle.storeStoreFence();
                        oVar.b(ge3Var);
                    } else {
                        oVar.g(q63Var);
                    }
                }
                if (GuardApplication.isAllowConnectNetwork(GuardApplication.application)) {
                    sb.p().s(GuardApplication.application);
                    sb sbVarP = sb.p();
                    sbVarP.getClass();
                    try {
                        readableDatabase = ((rb) sbVarP.d).getReadableDatabase();
                        try {
                            cursorQuery = readableDatabase.query("antivirus_db_version", new String[]{"version", "release_time"}, null, null, null, null, null);
                            if (cursorQuery != null) {
                                try {
                                    try {
                                        if (cursorQuery.moveToFirst()) {
                                            int i4 = cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("version"));
                                            cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("release_time"));
                                            lk1Var = new lk1();
                                            lk1Var.a = i4;
                                            tv0.b(cursorQuery);
                                            tv0.b(readableDatabase);
                                        } else {
                                            tv0.b(cursorQuery);
                                            tv0.b(readableDatabase);
                                            lk1Var = null;
                                        }
                                    } catch (Exception e) {
                                        e = e;
                                        e.printStackTrace();
                                        break;
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    cursor = cursorQuery;
                                    tv0.b(cursor);
                                    tv0.b(readableDatabase);
                                    throw th;
                                }
                            } else {
                                tv0.b(cursorQuery);
                                tv0.b(readableDatabase);
                                lk1Var = null;
                            }
                        } catch (Exception e2) {
                            e = e2;
                            cursorQuery = null;
                            e.printStackTrace();
                            tv0.b(cursorQuery);
                            tv0.b(readableDatabase);
                            lk1Var = null;
                            if (lk1Var != null) {
                                i = lk1Var.a;
                            } else {
                                i = 0;
                            }
                            if (Build.IS_INTERNATIONAL_BUILD) {
                                strA = n61.A();
                            } else {
                                Context context = (Context) sbVarP.c;
                                if (vw0.b != null) {
                                    strA = a.c;
                                } else {
                                    strA = a.c;
                                }
                            }
                            HashMap map = new HashMap();
                            map.put("version", String.valueOf(i));
                            map.put("updateType", "incremental");
                            map.put(l.a, strA);
                            strC = ls1.c((Context) sbVarP.c, "https://api.sec.miui.com/antivirus/viruses", map);
                            sb.t((Context) sbVarP.c);
                            ie2.e((Context) sbVarP.c);
                            if (!TextUtils.isEmpty(strC)) {
                                str = "new";
                                str2 = a.c;
                                try {
                                    jSONObject = new JSONObject(strC);
                                    if (jSONObject.has("error_code")) {
                                        z = false;
                                    } else {
                                        writableDatabase = ((rb) sbVarP.d).getWritableDatabase();
                                        writableDatabase.beginTransaction();
                                        try {
                                            writableDatabase.delete("antivirus_db_version", null, null);
                                            int i5 = jSONObject.getInt("version");
                                            str3 = "description";
                                            long j = jSONObject.getLong("releaseTime");
                                            ContentValues contentValues2 = new ContentValues();
                                            contentValues2.put("version", Integer.valueOf(i5));
                                            contentValues2.put("release_time", Long.valueOf(j));
                                            writableDatabase.insert("antivirus_db_version", null, contentValues2);
                                            if ("global".equals(jSONObject.getString("updateType"))) {
                                                writableDatabase.delete("antivirus_db", null, null);
                                                z2 = false;
                                            } else {
                                                z2 = true;
                                            }
                                            jSONArray = jSONObject.getJSONArray("viruses");
                                            i2 = 0;
                                            while (i2 < jSONArray.length()) {
                                                jSONObject2 = jSONArray.getJSONObject(i2);
                                                String string = jSONObject2.getString("packageName");
                                                int iOptInt = jSONObject2.optInt("version");
                                                String strOptString3 = jSONObject2.optString("signature");
                                                z3 = z2;
                                                strOptString = jSONObject2.optString("md5");
                                                int i6 = jSONObject2.getInt("type");
                                                JSONArray jSONArray2 = jSONArray;
                                                int iOptInt2 = jSONObject2.optInt("strategy", 0);
                                                int iOptInt3 = jSONObject2.optInt("descType", 1);
                                                if (z3) {
                                                    strOptString2 = jSONObject2.getString(al.L);
                                                } else {
                                                    strOptString2 = jSONObject2.optString(al.L, str);
                                                }
                                                str4 = strOptString2;
                                                int i7 = i2;
                                                String strOptString4 = jSONObject2.optString("virusName");
                                                jSONObjectOptJSONObject = jSONObject2.optJSONObject("i18n");
                                                strArr = new String[]{string, strOptString3, strOptString};
                                                if (str.equals(str4)) {
                                                    str5 = str;
                                                } else {
                                                    str5 = str;
                                                    if ("updated".equals(str4)) {
                                                        if ("deleted".equals(str4)) {
                                                            writableDatabase.delete("antivirus_db", "package =? and signatures =? and md5 =? ", strArr);
                                                        }
                                                        str6 = str2;
                                                        str7 = str3;
                                                    }
                                                    str3 = str7;
                                                    str2 = str6;
                                                    jSONArray = jSONArray2;
                                                    str = str5;
                                                    i2 = i7 + 1;
                                                    z2 = z3;
                                                }
                                                contentValues = new ContentValues();
                                                contentValues.put("package", string);
                                                contentValues.put("version", Integer.valueOf(iOptInt));
                                                contentValues.put("signatures", strOptString3);
                                                if (strOptString != null) {
                                                    contentValues.put("md5", strOptString);
                                                }
                                                contentValues.put("type", Integer.valueOf(i6));
                                                contentValues.put("virus_name", strOptString4);
                                                contentValues.put("strategy", Integer.valueOf(iOptInt2));
                                                contentValues.put("descType", Integer.valueOf(iOptInt3));
                                                if (jSONObjectOptJSONObject != null) {
                                                    str7 = str3;
                                                    contentValues.put(str7, jSONObjectOptJSONObject.toString());
                                                    str6 = str2;
                                                } else {
                                                    str6 = str2;
                                                    str7 = str3;
                                                    contentValues.put(str7, str6);
                                                }
                                                writableDatabase.delete("antivirus_db", "package =? and signatures =? and md5 =? ", strArr);
                                                writableDatabase.insert("antivirus_db", null, contentValues);
                                                str3 = str7;
                                                str2 = str6;
                                                jSONArray = jSONArray2;
                                                str = str5;
                                                i2 = i7 + 1;
                                                z2 = z3;
                                            }
                                            writableDatabase.setTransactionSuccessful();
                                            writableDatabase.endTransaction();
                                            tv0.b(writableDatabase);
                                            z = true;
                                        } catch (Throwable th2) {
                                            writableDatabase.endTransaction();
                                            tv0.b(writableDatabase);
                                            throw th2;
                                        }
                                    }
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                    z = false;
                                }
                                z5 = z;
                            }
                            Log.i("AntivirusManager", "update whitelist: " + z5);
                            return;
                        } catch (Throwable th3) {
                            th = th3;
                            cursor = null;
                            tv0.b(cursor);
                            tv0.b(readableDatabase);
                            throw th;
                        }
                    } catch (Exception e4) {
                        e = e4;
                        readableDatabase = null;
                    } catch (Throwable th4) {
                        th = th4;
                        cursor = null;
                        readableDatabase = null;
                    }
                    if (lk1Var != null) {
                        i = lk1Var.a;
                    } else {
                        i = 0;
                    }
                    if (Build.IS_INTERNATIONAL_BUILD) {
                        strA = n61.A();
                    } else {
                        Context context2 = (Context) sbVarP.c;
                        if (vw0.b != null || vw0.a == null) {
                            strA = a.c;
                        } else {
                            strA = vw0.a(context2);
                            if (TextUtils.isEmpty(strA)) {
                                strA = a.c;
                            }
                        }
                    }
                    HashMap map2 = new HashMap();
                    map2.put("version", String.valueOf(i));
                    map2.put("updateType", "incremental");
                    map2.put(l.a, strA);
                    strC = ls1.c((Context) sbVarP.c, "https://api.sec.miui.com/antivirus/viruses", map2);
                    sb.t((Context) sbVarP.c);
                    ie2.e((Context) sbVarP.c);
                    if (!TextUtils.isEmpty(strC)) {
                        str = "new";
                        str2 = a.c;
                        jSONObject = new JSONObject(strC);
                        if (jSONObject.has("error_code")) {
                            z = false;
                        } else {
                            writableDatabase = ((rb) sbVarP.d).getWritableDatabase();
                            writableDatabase.beginTransaction();
                            writableDatabase.delete("antivirus_db_version", null, null);
                            int i8 = jSONObject.getInt("version");
                            str3 = "description";
                            long j2 = jSONObject.getLong("releaseTime");
                            ContentValues contentValues3 = new ContentValues();
                            contentValues3.put("version", Integer.valueOf(i8));
                            contentValues3.put("release_time", Long.valueOf(j2));
                            writableDatabase.insert("antivirus_db_version", null, contentValues3);
                            if ("global".equals(jSONObject.getString("updateType"))) {
                                writableDatabase.delete("antivirus_db", null, null);
                                z2 = false;
                            } else {
                                z2 = true;
                            }
                            jSONArray = jSONObject.getJSONArray("viruses");
                            i2 = 0;
                            while (i2 < jSONArray.length()) {
                                jSONObject2 = jSONArray.getJSONObject(i2);
                                String string2 = jSONObject2.getString("packageName");
                                int iOptInt4 = jSONObject2.optInt("version");
                                String strOptString5 = jSONObject2.optString("signature");
                                z3 = z2;
                                strOptString = jSONObject2.optString("md5");
                                int i9 = jSONObject2.getInt("type");
                                JSONArray jSONArray3 = jSONArray;
                                int iOptInt5 = jSONObject2.optInt("strategy", 0);
                                int iOptInt6 = jSONObject2.optInt("descType", 1);
                                if (z3) {
                                    strOptString2 = jSONObject2.getString(al.L);
                                } else {
                                    strOptString2 = jSONObject2.optString(al.L, str);
                                }
                                str4 = strOptString2;
                                int i10 = i2;
                                String strOptString6 = jSONObject2.optString("virusName");
                                jSONObjectOptJSONObject = jSONObject2.optJSONObject("i18n");
                                strArr = new String[]{string2, strOptString5, strOptString};
                                if (str.equals(str4)) {
                                    str5 = str;
                                    if ("updated".equals(str4)) {
                                        if ("deleted".equals(str4)) {
                                            writableDatabase.delete("antivirus_db", "package =? and signatures =? and md5 =? ", strArr);
                                        }
                                        str6 = str2;
                                        str7 = str3;
                                    }
                                    str3 = str7;
                                    str2 = str6;
                                    jSONArray = jSONArray3;
                                    str = str5;
                                    i2 = i10 + 1;
                                    z2 = z3;
                                } else {
                                    str5 = str;
                                }
                                contentValues = new ContentValues();
                                contentValues.put("package", string2);
                                contentValues.put("version", Integer.valueOf(iOptInt4));
                                contentValues.put("signatures", strOptString5);
                                if (strOptString != null) {
                                    contentValues.put("md5", strOptString);
                                }
                                contentValues.put("type", Integer.valueOf(i9));
                                contentValues.put("virus_name", strOptString6);
                                contentValues.put("strategy", Integer.valueOf(iOptInt5));
                                contentValues.put("descType", Integer.valueOf(iOptInt6));
                                if (jSONObjectOptJSONObject != null) {
                                    str7 = str3;
                                    contentValues.put(str7, jSONObjectOptJSONObject.toString());
                                    str6 = str2;
                                } else {
                                    str6 = str2;
                                    str7 = str3;
                                    contentValues.put(str7, str6);
                                }
                                writableDatabase.delete("antivirus_db", "package =? and signatures =? and md5 =? ", strArr);
                                writableDatabase.insert("antivirus_db", null, contentValues);
                                str3 = str7;
                                str2 = str6;
                                jSONArray = jSONArray3;
                                str = str5;
                                i2 = i10 + 1;
                                z2 = z3;
                            }
                            writableDatabase.setTransactionSuccessful();
                            writableDatabase.endTransaction();
                            tv0.b(writableDatabase);
                            z = true;
                        }
                        z5 = z;
                    }
                    Log.i("AntivirusManager", "update whitelist: " + z5);
                    return;
                }
                return;
            case 5:
                px pxVar = (px) this.c;
                pxVar.b.setBackground(bj.d(pxVar.a, R.attr.actionBarItemBackground));
                return;
            case 6:
                vz.a((vz) this.c);
                return;
            case 7:
                ((o30) this.c).a();
                return;
            case 8:
                GuardApplication guardApplication = (GuardApplication) this.c;
                Process.setThreadPriority(10);
                try {
                    boolean z6 = oe2.G() && !oe2.C(guardApplication);
                    int iY = oe2.y(guardApplication, "com.android.updater");
                    String strZ = oe2.z();
                    ArrayList<tb0> arrayListA = oe2.A(guardApplication);
                    int iY2 = oe2.y(guardApplication, "com.miui.bugreport");
                    ArrayList arrayList = new ArrayList();
                    boolean z7 = z6 && iY != 0;
                    if (z6 && !"enforcing".equals(strZ)) {
                        z7 = true;
                    }
                    if (!arrayListA.isEmpty()) {
                        z7 = true;
                    }
                    if (iY2 == 0) {
                        z4 = z7;
                    }
                    if (!z4) {
                        Log.i("DeviceStateReporter", "no anomaly");
                        return;
                    }
                    arrayList.add(new qy1("bl_locked", Boolean.valueOf(z6)));
                    arrayList.add(new qy1("updater_state", oe2.R(iY)));
                    arrayList.add(new qy1("selinux_state", strZ));
                    ArrayList arrayList2 = new ArrayList(arrayListA.size());
                    for (tb0 tb0Var : arrayListA) {
                        arrayList2.add(tb0Var.b + ":" + tb0Var.c);
                    }
                    arrayList.add(new qy1("accessibility_apps", TextUtils.join(aa.b, arrayList2)));
                    arrayList.add(new qy1("feedback_state", oe2.R(iY2)));
                    arrayList.add(new qy1("filtered_packages", oe2.x(guardApplication)));
                    qy1[] qy1VarArr = (qy1[]) arrayList.toArray(new qy1[0]);
                    eo2.y0.a("device_state", qy1VarArr);
                    Log.i("DeviceStateReporter", "Reported device_state anomaly");
                    StringBuilder sb = new StringBuilder();
                    for (qy1 qy1Var : qy1VarArr) {
                        sb.append((String) qy1Var.b);
                        sb.append(":");
                        sb.append(qy1Var.c);
                        sb.append(" ");
                    }
                    Log.i("DeviceStateReporter", sb.toString());
                    return;
                } catch (Exception e5) {
                    Log.e("DeviceStateReporter", "doCheck failed", e5);
                    return;
                }
            case 9:
                zm0 zm0Var = (zm0) this.c;
                synchronized (zm0Var.d) {
                    try {
                        if (zm0Var.h == null) {
                            return;
                        }
                        try {
                            kn0 kn0VarC = zm0Var.c();
                            int i11 = kn0VarC.f;
                            if (i11 == 2) {
                                synchronized (zm0Var.d) {
                                }
                            }
                            if (i11 != 0) {
                                throw new RuntimeException("fetchFonts result is not OK. (" + i11 + ")");
                            }
                            try {
                                Trace.beginSection("EmojiCompat.FontRequestEmojiCompatConfig.buildTypeface");
                                vg3 vg3Var = zm0Var.c;
                                Context context3 = zm0Var.a;
                                vg3Var.getClass();
                                Typeface typefaceA = a03.a(context3, new kn0[]{kn0VarC}, 0);
                                MappedByteBuffer mappedByteBufferU = al2.u(zm0Var.a, kn0VarC.a);
                                if (mappedByteBufferU == null || typefaceA == null) {
                                    throw new RuntimeException("Unable to open file.");
                                }
                                try {
                                    Trace.beginSection("EmojiCompat.MetadataRepo.create");
                                    sc3 sc3Var = new sc3(typefaceA, q62.r(mappedByteBufferU));
                                    Trace.endSection();
                                    Trace.endSection();
                                    synchronized (zm0Var.d) {
                                        try {
                                            ys ysVar = zm0Var.h;
                                            if (ysVar != null) {
                                                ysVar.z(sc3Var);
                                            }
                                        } catch (Throwable th5) {
                                            throw th5;
                                        }
                                        break;
                                    }
                                    zm0Var.b();
                                    return;
                                } catch (Throwable th6) {
                                    Trace.endSection();
                                    throw th6;
                                }
                            } catch (Throwable th7) {
                                Trace.endSection();
                                throw th7;
                            }
                            break;
                        } catch (Throwable th8) {
                            synchronized (zm0Var.d) {
                                try {
                                    ys ysVar2 = zm0Var.h;
                                    if (ysVar2 != null) {
                                        ysVar2.y(th8);
                                    }
                                    zm0Var.b();
                                    return;
                                } catch (Throwable th9) {
                                    throw th9;
                                }
                            }
                        }
                    } catch (Throwable th10) {
                        throw th10;
                    }
                }
            case 10:
                cb3 cb3Var = ((od) this.c).C.F;
                if (cb3Var != null) {
                    cb3Var.G();
                    return;
                }
                return;
            case 11:
                g22 g22Var = (g22) this.c;
                androidx.lifecycle.a aVar = g22Var.g;
                if (g22Var.c == 0) {
                    g22Var.d = true;
                    aVar.d(mh1.ON_PAUSE);
                }
                if (g22Var.b == 0 && g22Var.d) {
                    aVar.d(mh1.ON_STOP);
                    g22Var.e = true;
                    return;
                }
                return;
            case 12:
                uk3.F((Context) this.c, new kg(2), uk3.b, false);
                return;
            case 13:
                qh2 qh2Var = (qh2) this.c;
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                for (oh2 oh2Var : qh2Var.c.keySet()) {
                    if (jElapsedRealtime - oh2Var.f > 3000 && oh2Var.d.compareAndSet(ph2.b, ph2.c)) {
                        qh2Var.d.add(oh2Var);
                        Log.w("ScanWatchdog", "A long time commit was observed:" + oh2Var);
                    }
                }
                return;
            case 14:
                ((Toolbar) this.c).m();
                return;
            case 15:
                ((c) this.c).d.countDown();
                return;
            default:
                j4.b((j4.a) this.c);
                return;
        }
    }
}
