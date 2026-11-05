package defpackage;

import android.content.Context;
import android.os.IBinder;
import android.os.Parcel;
import android.view.View;
import android.view.ViewGroup;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class n53 {
    public static volatile rb a;

    public static void A(View view, float f) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.leftMargin = (int) (marginLayoutParams.leftMargin * f);
            marginLayoutParams.topMargin = (int) (marginLayoutParams.topMargin * f);
            marginLayoutParams.rightMargin = (int) (marginLayoutParams.rightMargin * f);
            marginLayoutParams.bottomMargin = (int) (marginLayoutParams.bottomMargin * f);
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public static void B(View view, float f) {
        view.setPadding((int) (view.getPaddingLeft() * f), (int) (view.getPaddingTop() * f), (int) (view.getPaddingRight() * f), (int) (view.getPaddingBottom() * f));
    }

    public static String b(IBinder iBinder, String str, String str2) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.heytap.openid.IOpenID");
            parcelObtain.writeString(str);
            parcelObtain.writeString(str2);
            parcelObtain.writeString("OUID");
            iBinder.transact(1, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            return parcelObtain2.readString();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    public static void j(Appendable appendable, Object obj, op0 op0Var) {
        appendable.getClass();
        if (op0Var != null) {
            appendable.append((CharSequence) op0Var.z(obj));
            return;
        }
        if (obj == null ? true : obj instanceof CharSequence) {
            appendable.append((CharSequence) obj);
        } else if (obj instanceof Character) {
            appendable.append(((Character) obj).charValue());
        } else {
            appendable.append(obj.toString());
        }
    }

    public static boolean m(sq sqVar, sq sqVar2) {
        sqVar.getClass();
        sqVar2.getClass();
        if (!(sqVar2 instanceof o11) || !(sqVar instanceof aq0)) {
            return false;
        }
        o11 o11Var = (o11) sqVar2;
        o11Var.M().size();
        aq0 aq0Var = (aq0) sqVar;
        aq0Var.M().size();
        List listM = o11Var.v0().M();
        listM.getClass();
        List listM2 = aq0Var.v0().M();
        listM2.getClass();
        for (qy1 qy1Var : zx.v0(listM, listM2)) {
            m33 m33Var = (m33) qy1Var.b;
            m33 m33Var2 = (m33) qy1Var.c;
            m33Var.getClass();
            boolean z = x((aq0) sqVar2, m33Var) instanceof f91;
            m33Var2.getClass();
            if (z != (x(aq0Var, m33Var2) instanceof f91)) {
                return true;
            }
        }
        return false;
    }

    public static pj0 p(Context context) {
        context.getClass();
        if (pj0.d == null) {
            synchronized (pj0.class) {
                if (pj0.d == null) {
                    Context applicationContext = context.getApplicationContext();
                    applicationContext.getClass();
                    pj0 pj0Var = new pj0();
                    Path path = Paths.get(applicationContext.getFilesDir().toString(), "feedback01.tmp");
                    path.getClass();
                    pj0Var.a = path;
                    pj0Var.b = new StringBuffer();
                    Path path2 = Paths.get(applicationContext.getFilesDir().toString(), "clip01.tmp");
                    path2.getClass();
                    pj0Var.c = path2;
                    VarHandle.storeStoreFence();
                    pj0.d = pj0Var;
                }
            }
        }
        pj0 pj0Var2 = pj0.d;
        pj0Var2.getClass();
        return pj0Var2;
    }

    /* JADX DEBUG: Class process forced to load method for inline: r71.a(int):void */
    /* JADX WARN: Code duplicated, block: B:53:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:59:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:62:? A[RETURN, SYNTHETIC] */
    public static na2 r(f62 f62Var, boolean z, boolean z2, Boolean bool, boolean z3, vo1 vo1Var, to1 to1Var) {
        i81 i81Var;
        na2 na2Var;
        d62 d62Var;
        e42 e42Var;
        f62Var.getClass();
        dm2 dm2Var = f62Var.c;
        vo1Var.getClass();
        to1Var.getClass();
        e42 e42Var2 = e42.INTERFACE;
        if (z) {
            if (bool == null) {
                throw new IllegalStateException(("isConst should not be null for property (container=" + f62Var + ')').toString());
            }
            if (f62Var instanceof d62) {
                d62 d62Var2 = (d62) f62Var;
                if (d62Var2.g == e42Var2) {
                    return fa1.k(vo1Var, d62Var2.f.d(gr1.e("DefaultImpls")), to1Var);
                }
            }
            if (bool.booleanValue() && (f62Var instanceof e62)) {
                i81 i81Var2 = dm2Var instanceof i81 ? (i81) dm2Var : null;
                r71 r71Var = i81Var2 != null ? i81Var2.c : null;
                if (r71Var != null) {
                    String str = r71Var.a;
                    if (str == null) {
                        r71.a(10);
                        throw null;
                    }
                    String strReplace = str.replace('/', '.');
                    strReplace.getClass();
                    vn0 vn0Var = new vn0(strReplace);
                    return fa1.k(vo1Var, new rv(vn0Var.b(), vn0Var.a.g()), to1Var);
                }
            }
        }
        if (z2 && (f62Var instanceof d62)) {
            d62 d62Var3 = (d62) f62Var;
            if (d62Var3.g == e42.COMPANION_OBJECT && (d62Var = d62Var3.e) != null && ((e42Var = d62Var.g) == e42.CLASS || e42Var == e42.ENUM_CLASS || (z3 && (e42Var == e42Var2 || e42Var == e42.ANNOTATION_CLASS)))) {
                dm2 dm2Var2 = d62Var.c;
                de1 de1Var = dm2Var2 instanceof de1 ? (de1) dm2Var2 : null;
                if (de1Var != null) {
                    return de1Var.b;
                }
            } else if (f62Var instanceof e62) {
                i81Var = (i81) dm2Var;
                na2Var = i81Var.d;
                if (na2Var == null) {
                    return fa1.k(vo1Var, i81Var.a(), to1Var);
                }
                return na2Var;
            }
        } else if ((f62Var instanceof e62) && (dm2Var instanceof i81)) {
            i81Var = (i81) dm2Var;
            na2Var = i81Var.d;
            if (na2Var == null) {
                return fa1.k(vo1Var, i81Var.a(), to1Var);
            }
            return na2Var;
        }
        return null;
    }

    public static int s(int i, int i2) {
        if (i > -12 || i2 > -65) {
            return -1;
        }
        return i ^ (i2 << 8);
    }

    public static int t(int i, byte[] bArr, int i2) {
        byte b = bArr[i - 1];
        int i3 = i2 - i;
        if (i3 == 0) {
            if (b > -12) {
                return -1;
            }
            return b;
        }
        if (i3 == 1) {
            return s(b, bArr[i]);
        }
        if (i3 != 2) {
            throw new AssertionError();
        }
        byte b2 = bArr[i];
        byte b3 = bArr[i + 1];
        if (b > -12 || b2 > -65 || b3 > -65) {
            return -1;
        }
        return (b3 << 16) ^ ((b2 << 8) ^ b);
    }

    public static final boolean u(gy1 gy1Var, vn0 vn0Var) {
        gy1Var.getClass();
        vn0Var.getClass();
        return gy1Var.a(vn0Var);
    }

    public static void w(String str, boolean z) {
        if (z) {
            return;
        }
        cw1.f(str);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: aq0 */
    /* JADX WARN: Code duplicated, block: B:31:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:34:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:36:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:37:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:40:0x00d3  */
    /* JADX WARN: Code duplicated, block: B:42:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:45:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:47:0x00fc  */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0108, code lost:
    
        if (defpackage.ja0.g(r0).equals(defpackage.ja0.g(r2)) != false) goto L49;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static g91 x(aq0 aq0Var, m33 m33Var) {
        i50 i50VarO;
        fq1 fq1Var;
        fq1 fq1Var2;
        aq0 aq0VarA;
        aq0Var.getClass();
        boolean zJ = az0.j(((j50) aq0Var).getName().b(), "remove");
        nq0 nq0Var = nq0.b;
        if (!zJ || aq0Var.M().size() != 1 || (ja0.i(aq0Var).o() instanceof yf1) || od1.A(aq0Var)) {
            if (aq0Var.M().size() == 1) {
                i50VarO = aq0Var.o();
                if (i50VarO instanceof fq1) {
                    fq1Var = (fq1) i50VarO;
                } else {
                    fq1Var = null;
                }
                if (fq1Var != null) {
                    List listM = aq0Var.M();
                    listM.getClass();
                    ow owVarW = ((m33) zx.h0(listM)).getType().o0().w();
                    fq1Var2 = owVarW instanceof fq1 ? (fq1) owVarW : null;
                    if (fq1Var2 != null) {
                        if (od1.u(fq1Var) != null) {
                        }
                    }
                }
            }
            if1 type = m33Var.getType();
            type.getClass();
            return (g91) u83.A(type, wy2.i, nq0Var);
        }
        List listM2 = aq0Var.v0().M();
        listM2.getClass();
        if1 type2 = ((m33) zx.h0(listM2)).getType();
        type2.getClass();
        wy2 wy2Var = wy2.i;
        g91 g91Var = (g91) u83.A(type2, wy2Var, nq0Var);
        f91 f91Var = g91Var instanceof f91 ? (f91) g91Var : null;
        if ((f91Var != null ? f91Var.i : null) != k81.INT || (aq0VarA = hp.a(aq0Var)) == null) {
            if (aq0Var.M().size() == 1) {
                i50VarO = aq0Var.o();
                if (i50VarO instanceof fq1) {
                    fq1Var = (fq1) i50VarO;
                } else {
                    fq1Var = null;
                }
                if (fq1Var != null) {
                    List listM3 = aq0Var.M();
                    listM3.getClass();
                    ow owVarW2 = ((m33) zx.h0(listM3)).getType().o0().w();
                    if (owVarW2 instanceof fq1) {
                    }
                    if (fq1Var2 != null) {
                        if (od1.u(fq1Var) != null) {
                        }
                    }
                }
            }
            if1 type3 = m33Var.getType();
            type3.getClass();
            return (g91) u83.A(type3, wy2.i, nq0Var);
        }
        List listM4 = aq0VarA.v0().M();
        listM4.getClass();
        if1 type4 = ((m33) zx.h0(listM4)).getType();
        type4.getClass();
        g91 g91Var2 = (g91) u83.A(type4, wy2Var, nq0Var);
        i50 i50VarO2 = aq0VarA.o();
        i50VarO2.getClass();
        wn0 wn0VarF = ga0.f(i50VarO2);
        wn0VarF.getClass();
        if (!wn0VarF.equals(on2.K.a) || !(g91Var2 instanceof e91) || !((e91) g91Var2).i.equals("java/lang/Object")) {
            if (aq0Var.M().size() == 1) {
                i50VarO = aq0Var.o();
                if (i50VarO instanceof fq1) {
                    fq1Var = (fq1) i50VarO;
                } else {
                    fq1Var = null;
                }
                if (fq1Var != null) {
                    List listM5 = aq0Var.M();
                    listM5.getClass();
                    ow owVarW3 = ((m33) zx.h0(listM5)).getType().o0().w();
                    if (owVarW3 instanceof fq1) {
                    }
                    if (fq1Var2 != null) {
                        if (od1.u(fq1Var) != null) {
                        }
                    }
                }
            }
            if1 type5 = m33Var.getType();
            type5.getClass();
            return (g91) u83.A(type5, wy2.i, nq0Var);
        }
        if1 type6 = m33Var.getType();
        type6.getClass();
        return (g91) u83.A(ja2.d0(type6), wy2.i, nq0Var);
    }

    public static void y(Object obj, String str) {
        if (obj != null) {
            return;
        }
        cw1.f(str);
    }

    public static int z(int i, byte[] bArr, int i2) {
        while (i < i2 && bArr[i] >= 0) {
            i++;
        }
        if (i >= i2) {
            return 0;
        }
        while (i < i2) {
            int i3 = i + 1;
            byte b = bArr[i];
            if (b >= 0) {
                i = i3;
            } else if (b < -32) {
                if (i3 >= i2) {
                    return b;
                }
                if (b < -62) {
                    return -1;
                }
                i += 2;
                if (bArr[i3] > -65) {
                    return -1;
                }
            } else if (b < -16) {
                if (i3 >= i2 - 1) {
                    return t(i3, bArr, i2);
                }
                int i4 = i + 2;
                byte b2 = bArr[i3];
                if (b2 > -65) {
                    return -1;
                }
                if (b == -32 && b2 < -96) {
                    return -1;
                }
                if (b == -19 && b2 >= -96) {
                    return -1;
                }
                i += 3;
                if (bArr[i4] > -65) {
                    return -1;
                }
            } else {
                if (i3 >= i2 - 2) {
                    return t(i3, bArr, i2);
                }
                int i5 = i + 2;
                byte b3 = bArr[i3];
                if (b3 > -65) {
                    return -1;
                }
                if ((((b3 + 112) + (b << 28)) >> 30) != 0) {
                    return -1;
                }
                int i6 = i + 3;
                if (bArr[i5] > -65) {
                    return -1;
                }
                i += 4;
                if (bArr[i6] > -65) {
                    return -1;
                }
            }
        }
        return 0;
    }

    public abstract int a(int i, byte[] bArr, int i2);

    public void c(int i) {
    }

    public abstract void d(int i, byte[] bArr, int i2);

    public byte[] g() {
        return null;
    }

    public int i() {
        return 0;
    }

    public int k() {
        return -1;
    }

    public int l(int i, byte[] bArr) throws vo3 {
        int i2 = 0;
        while (i2 < i) {
            int iA = a(i2, bArr, i - i2);
            if (iA <= 0) {
                throw new vo3("Cannot read. Remote side has closed. Tried to read " + i + " bytes, but only got " + i2 + " bytes.");
            }
            i2 += iA;
        }
        return i2;
    }

    public abstract Method n(Class cls, Field field);

    public abstract Constructor o(Class cls);

    public abstract String[] q(Class cls);

    public abstract boolean v(Class cls);
}
