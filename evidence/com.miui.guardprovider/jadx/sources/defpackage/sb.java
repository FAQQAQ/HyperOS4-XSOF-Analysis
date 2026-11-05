package defpackage;

import android.R;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.AbsSeekBar;
import com.xiaomi.onetrack.b.l;
import com.xiaomi.onetrack.util.a;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import miui.os.Build;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class sb implements la, bb, nq, q20 {
    public static volatile sb e;
    public static final int[] f = {R.attr.indeterminateDrawable, R.attr.progressDrawable};
    public final /* synthetic */ int b;
    public Object c;
    public Object d;

    public sb(hq1 hq1Var, v vVar, wo woVar) {
        this.b = 2;
        hq1Var.getClass();
        woVar.getClass();
        this.c = woVar;
        this.d = new rg2(hq1Var, vVar);
    }

    public static synchronized sb p() {
        try {
            if (e == null) {
                e = new sb(0);
            }
        } catch (Throwable th) {
            throw th;
        }
        return e;
    }

    public static sb r(sh1 sh1Var) {
        z43 z43VarE = ((a53) sh1Var).e();
        sb sbVar = new sb(6);
        sbVar.c = sh1Var;
        hq3 hq3Var = new hq3(z43VarE, nj1.d);
        String canonicalName = nj1.class.getCanonicalName();
        if (canonicalName == null) {
            cw1.f("Local and anonymous classes can not be ViewModels");
            return null;
        }
        sbVar.d = (nj1) hq3Var.j(nj1.class, "androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(canonicalName));
        VarHandle.storeStoreFence();
        return sbVar;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x001d  */
    public static void t(Context context) {
        String strA;
        if (Build.IS_INTERNATIONAL_BUILD) {
            strA = n61.A();
        } else if (vw0.b == null || vw0.a == null) {
            strA = a.c;
        } else {
            strA = vw0.a(context);
            if (TextUtils.isEmpty(strA)) {
                strA = a.c;
            }
        }
        HashMap map = new HashMap();
        map.put(l.a, strA);
        String strC = ls1.c(context, "https://api.sec.miui.com/antivirus/whitelist", map);
        if (TextUtils.isEmpty(strC)) {
            return;
        }
        SQLiteDatabase writableDatabase = ((yb3) pp1.e(context).c).getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            JSONObject jSONObject = new JSONObject(strC);
            if (jSONObject.has("error_code")) {
                return;
            }
            writableDatabase.delete("mi_virus_white_db", null, null);
            ContentValues contentValues = new ContentValues();
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("whitelist");
            if (jSONArrayOptJSONArray != null) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                    if (jSONObjectOptJSONObject != null) {
                        String strOptString = jSONObjectOptJSONObject.optString("packageName");
                        String strOptString2 = jSONObjectOptJSONObject.optString("signature");
                        contentValues.put("package", strOptString);
                        contentValues.put("signatures", strOptString2);
                        writableDatabase.insert("mi_virus_white_db", null, contentValues);
                    }
                }
            }
            writableDatabase.setTransactionSuccessful();
        } catch (Exception e2) {
            e2.printStackTrace();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    @Override // defpackage.bb
    public List a(f62 f62Var, q0 q0Var, int i) {
        wo woVar = (wo) this.c;
        hr1 hr1Var = f62Var.a;
        q0Var.getClass();
        if (i == 0) {
            throw null;
        }
        if (q0Var instanceof i42) {
            i42 i42Var = (i42) q0Var;
            List list = i42Var.i;
            list.getClass();
            return u(list, (List) i42Var.k(woVar.b), hr1Var);
        }
        if (q0Var instanceof w42) {
            w42 w42Var = (w42) q0Var;
            List list2 = w42Var.v;
            list2.getClass();
            return u(list2, (List) w42Var.k(woVar.d), hr1Var);
        }
        if (!(q0Var instanceof d52)) {
            ik.h(q0Var, "Unknown message: ");
            return null;
        }
        int iC = im2.C(i);
        if (iC == 1) {
            d52 d52Var = (d52) q0Var;
            List list3 = d52Var.v;
            list3.getClass();
            return u(list3, (List) d52Var.k(woVar.e), hr1Var);
        }
        if (iC == 2) {
            d52 d52Var2 = (d52) q0Var;
            List list4 = d52Var2.w;
            list4.getClass();
            return u(list4, (List) d52Var2.k(woVar.f), hr1Var);
        }
        if (iC != 3) {
            ik.j("Unsupported callable kind with property proto");
            return null;
        }
        d52 d52Var3 = (d52) q0Var;
        List list5 = d52Var3.x;
        list5.getClass();
        return u(list5, (List) d52Var3.k(woVar.g), hr1Var);
    }

    @Override // defpackage.la
    public Object b(f62 f62Var, d52 d52Var, if1 if1Var) {
        d52Var.getClass();
        return null;
    }

    @Override // defpackage.bb
    public List c(f62 f62Var, q0 q0Var, int i, int i2, w52 w52Var) {
        q0Var.getClass();
        if (i == 0) {
            throw null;
        }
        List listF = w52Var != null ? f(f62Var, q0Var, i, i2, w52Var) : null;
        return listF == null ? nf0.b : listF;
    }

    @Override // defpackage.bb
    public List d(f62 f62Var, d52 d52Var) {
        d52Var.getClass();
        List list = d52Var.A;
        list.getClass();
        ((wo) this.c).getClass();
        return u(list, null, f62Var.a);
    }

    @Override // defpackage.bb
    public List e(f62 f62Var, q0 q0Var, int i) {
        String str;
        wo woVar = (wo) this.c;
        hr1 hr1Var = f62Var.a;
        q0Var.getClass();
        if (i == 0) {
            throw null;
        }
        if (q0Var instanceof w42) {
            List list = ((w42) q0Var).w;
            list.getClass();
            woVar.getClass();
            return u(list, null, hr1Var);
        }
        if (!(q0Var instanceof d52)) {
            ik.h(q0Var, "Unknown message: ");
            return null;
        }
        int iC = im2.C(i);
        if (iC == 1 || iC == 2 || iC == 3) {
            List list2 = ((d52) q0Var).y;
            list2.getClass();
            woVar.getClass();
            return u(list2, null, hr1Var);
        }
        if (i == 1) {
            str = "FUNCTION";
        } else if (i == 2) {
            str = "PROPERTY";
        } else if (i != 3) {
            str = i != 4 ? "null" : "PROPERTY_SETTER";
        } else {
            str = "PROPERTY_GETTER";
        }
        throw new IllegalStateException("Unsupported callable kind with property proto for receiver annotations: ".concat(str).toString());
    }

    @Override // defpackage.bb
    public List f(f62 f62Var, q0 q0Var, int i, int i2, w52 w52Var) {
        q0Var.getClass();
        if (i == 0) {
            throw null;
        }
        w52Var.getClass();
        List list = w52Var.k;
        list.getClass();
        return u(list, (List) w52Var.k(((wo) this.c).j), f62Var.a);
    }

    @Override // defpackage.nq
    public Type g() {
        return (Type) this.c;
    }

    @Override // defpackage.nq
    public Object h(bw1 bw1Var) {
        Executor executor = (Executor) this.d;
        return executor == null ? bw1Var : new z50(executor, bw1Var);
    }

    @Override // defpackage.bb
    public ArrayList i(o52 o52Var, hr1 hr1Var) {
        o52Var.getClass();
        hr1Var.getClass();
        List list = o52Var.s;
        list.getClass();
        return u(list, (List) o52Var.k(((wo) this.c).k), hr1Var);
    }

    @Override // defpackage.la
    public Object j(f62 f62Var, d52 d52Var, if1 if1Var) {
        d52Var.getClass();
        z32 z32Var = (z32) ys.n(d52Var, ((wo) this.c).i);
        if (z32Var == null) {
            return null;
        }
        return ((rg2) this.d).y(if1Var, z32Var, f62Var.a);
    }

    @Override // defpackage.bb
    public ArrayList k(t52 t52Var, hr1 hr1Var) {
        t52Var.getClass();
        hr1Var.getClass();
        List list = t52Var.l;
        list.getClass();
        return u(list, (List) t52Var.k(((wo) this.c).l), hr1Var);
    }

    @Override // defpackage.bb
    public List l(f62 f62Var, r42 r42Var) {
        f62Var.getClass();
        List list = r42Var.f;
        list.getClass();
        return u(list, (List) r42Var.k(((wo) this.c).h), f62Var.a);
    }

    @Override // defpackage.bb
    public List m(f62 f62Var, d52 d52Var) {
        d52Var.getClass();
        List list = d52Var.z;
        list.getClass();
        ((wo) this.c).getClass();
        return u(list, null, f62Var.a);
    }

    @Override // defpackage.bb
    public List n(d62 d62Var) {
        d62Var.getClass();
        f42 f42Var = d62Var.d;
        List list = f42Var.A;
        list.getClass();
        return u(list, (List) f42Var.k(((wo) this.c).c), d62Var.a);
    }

    public void o(String str, PrintWriter printWriter) {
        gm2 gm2Var = ((nj1) this.d).c;
        if (gm2Var.d > 0) {
            printWriter.print(str);
            printWriter.println("Loaders:");
            if (gm2Var.d <= 0) {
                return;
            }
            if (gm2Var.b(0) != null) {
                ik.b();
                return;
            }
            printWriter.print(str);
            printWriter.print("  #");
            printWriter.print(gm2Var.b[0]);
            printWriter.print(": ");
            throw null;
        }
    }

    @Override // defpackage.q20
    public Object q(Object obj) {
        Charset charsetA;
        hd2 hd2Var = (hd2) obj;
        ir0 ir0Var = (ir0) this.c;
        fd2 fd2Var = hd2Var.b;
        if (fd2Var == null) {
            mo moVarK = hd2Var.k();
            im1 im1VarG = hd2Var.g();
            if (im1VarG == null || (charsetA = im1.a(im1VarG)) == null) {
                charsetA = mu.a;
            }
            fd2Var = new fd2(moVarK, charsetA);
            hd2Var.b = fd2Var;
        }
        ir0Var.getClass();
        p51 p51Var = new p51(fd2Var);
        p51Var.U(2);
        try {
            Object objB = ((bw2) this.d).b(p51Var);
            if (p51Var.R() != 10) {
                throw new f41("JSON document was not fully consumed.");
            }
            hd2Var.close();
            return objB;
        } catch (Throwable th) {
            hd2Var.close();
            throw th;
        }
    }

    public void s(Context context) {
        this.c = context.getApplicationContext();
        this.d = new rb((Context) this.c, "miui_antivirus.db", null, 2, 0);
    }

    public String toString() {
        int iLastIndexOf;
        switch (this.b) {
            case 6:
                StringBuilder sb = new StringBuilder(128);
                sb.append("LoaderManager{");
                sb.append(Integer.toHexString(System.identityHashCode(this)));
                sb.append(" in ");
                sh1 sh1Var = (sh1) this.c;
                if (sh1Var == null) {
                    sb.append("null");
                } else {
                    String simpleName = sh1Var.getClass().getSimpleName();
                    if (simpleName.length() <= 0 && (iLastIndexOf = (simpleName = sh1Var.getClass().getName()).lastIndexOf(46)) > 0) {
                        simpleName = simpleName.substring(iLastIndexOf + 1);
                    }
                    sb.append(simpleName);
                    sb.append('{');
                    sb.append(Integer.toHexString(System.identityHashCode(sh1Var)));
                }
                sb.append("}}");
                return sb.toString();
            default:
                return super.toString();
        }
    }

    public ArrayList u(List list, List list2, hr1 hr1Var) {
        List list3 = list;
        if (list3.isEmpty()) {
            if (list2 == null) {
                list2 = nf0.b;
            }
            list3 = list2;
        }
        List<c42> list4 = list3;
        ArrayList arrayList = new ArrayList(cy.G(list4));
        for (c42 c42Var : list4) {
            c42Var.getClass();
            hr1Var.getClass();
            arrayList.add(((rg2) this.d).e(c42Var, hr1Var));
        }
        return arrayList;
    }

    public void v(AttributeSet attributeSet, int i) {
        AbsSeekBar absSeekBar = (AbsSeekBar) this.c;
        u63 u63VarM = u63.m(absSeekBar.getContext(), attributeSet, f, i);
        Drawable drawableF = u63VarM.f(0);
        if (drawableF != null) {
            if (drawableF instanceof AnimationDrawable) {
                AnimationDrawable animationDrawable = (AnimationDrawable) drawableF;
                int numberOfFrames = animationDrawable.getNumberOfFrames();
                AnimationDrawable animationDrawable2 = new AnimationDrawable();
                animationDrawable2.setOneShot(animationDrawable.isOneShot());
                for (int i2 = 0; i2 < numberOfFrames; i2++) {
                    Drawable drawableW = w(animationDrawable.getFrame(i2), true);
                    drawableW.setLevel(10000);
                    animationDrawable2.addFrame(drawableW, animationDrawable.getDuration(i2));
                }
                animationDrawable2.setLevel(10000);
                drawableF = animationDrawable2;
            }
            absSeekBar.setIndeterminateDrawable(drawableF);
        }
        Drawable drawableF2 = u63VarM.f(1);
        if (drawableF2 != null) {
            absSeekBar.setProgressDrawable(w(drawableF2, false));
        }
        u63VarM.q();
    }

    public Drawable w(Drawable drawable, boolean z) {
        if (!(drawable instanceof LayerDrawable)) {
            if (!(drawable instanceof BitmapDrawable)) {
                return drawable;
            }
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            if (((Bitmap) this.d) == null) {
                this.d = bitmap;
            }
            ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null));
            shapeDrawable.getPaint().setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP));
            shapeDrawable.getPaint().setColorFilter(bitmapDrawable.getPaint().getColorFilter());
            return z ? new ClipDrawable(shapeDrawable, 3, 1) : shapeDrawable;
        }
        LayerDrawable layerDrawable = (LayerDrawable) drawable;
        int numberOfLayers = layerDrawable.getNumberOfLayers();
        Drawable[] drawableArr = new Drawable[numberOfLayers];
        for (int i = 0; i < numberOfLayers; i++) {
            int id = layerDrawable.getId(i);
            drawableArr[i] = w(layerDrawable.getDrawable(i), id == 16908301 || id == 16908303);
        }
        LayerDrawable layerDrawable2 = new LayerDrawable(drawableArr);
        for (int i2 = 0; i2 < numberOfLayers; i2++) {
            layerDrawable2.setId(i2, layerDrawable.getId(i2));
            layerDrawable2.setLayerGravity(i2, layerDrawable.getLayerGravity(i2));
            layerDrawable2.setLayerWidth(i2, layerDrawable.getLayerWidth(i2));
            layerDrawable2.setLayerHeight(i2, layerDrawable.getLayerHeight(i2));
            layerDrawable2.setLayerInsetLeft(i2, layerDrawable.getLayerInsetLeft(i2));
            layerDrawable2.setLayerInsetRight(i2, layerDrawable.getLayerInsetRight(i2));
            layerDrawable2.setLayerInsetTop(i2, layerDrawable.getLayerInsetTop(i2));
            layerDrawable2.setLayerInsetBottom(i2, layerDrawable.getLayerInsetBottom(i2));
            layerDrawable2.setLayerInsetStart(i2, layerDrawable.getLayerInsetStart(i2));
            layerDrawable2.setLayerInsetEnd(i2, layerDrawable.getLayerInsetEnd(i2));
        }
        return layerDrawable2;
    }

    /* JADX DEBUG: Marked for inline */
    /* JADX DEBUG: Method not inlined, still used in: [com.xiaomi.push.service.XMPushService.c():void, kr0.b(java.lang.reflect.Type, java.lang.annotation.Annotation[], vd2):q20, sb.p():sb, sb.r(sh1):sb] */
    public /* synthetic */ sb(int i) {
        this.b = i;
    }

    public sb(ti tiVar, cd2 cd2Var) {
        this.b = 3;
        this.c = tiVar;
        this.d = cd2Var;
    }

    public sb(AbsSeekBar absSeekBar) {
        this.b = 1;
        this.c = absSeekBar;
    }
}
