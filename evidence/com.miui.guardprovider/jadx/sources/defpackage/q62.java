package defpackage;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import com.miui.guardprovider.GuardApplication;
import com.xiaomi.onetrack.util.a;
import com.xiaomi.onetrack.util.aa;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.invoke.VarHandle;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import miui.os.Build;
import tmsdk.common.tcc.DeepCleanSAFEngine;

/* JADX INFO: loaded from: classes.dex */
public abstract class q62 {
    public static volatile ob2 a;
    public static final char[] b = {DeepCleanSAFEngine.DetailPath.KEY, DeepCleanSAFEngine.DetailPath.DETAIL_PATH, DeepCleanSAFEngine.DetailPath.FILENAME, DeepCleanSAFEngine.DetailPath.FILESIZE, DeepCleanSAFEngine.DetailPath.CTIME, DeepCleanSAFEngine.DetailPath.MTIME, DeepCleanSAFEngine.DetailPath.ATIME, DeepCleanSAFEngine.DetailPath.IMPORTANT, '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final t20 c = new t20(2);

    public static int a(fo3 fo3Var, String str, ArrayList arrayList, String str2) {
        int length = str.length();
        int length2 = 0;
        if (!TextUtils.isEmpty(str2)) {
            try {
                length2 = str2.getBytes("UTF-8").length;
            } catch (UnsupportedEncodingException unused) {
            }
        }
        return (((length2 + 243) / 1448) * 132) + 1080 + length + length2;
    }

    public static String b(Context context) {
        fo3 fo3Var = new fo3();
        if (!zd3.h()) {
            return null;
        }
        try {
            new ArrayList();
            bh3 bh3VarB = bh3.b();
            bh3VarB.getClass();
            if (TextUtils.isEmpty("https://resolver.msg.xiaomi.net/psc/?t=a")) {
                throw new IllegalArgumentException("the url is empty");
            }
            xg3 xg3VarA = bh3VarB.a(new URL("https://resolver.msg.xiaomi.net/psc/?t=a").getHost(), true);
            ArrayList<String> arrayListB = xg3VarA.b("https://resolver.msg.xiaomi.net/psc/?t=a");
            if (!arrayListB.contains("https://resolver.msg.xiaomi.net/psc/?t=a")) {
                arrayListB.add("https://resolver.msg.xiaomi.net/psc/?t=a");
            }
            String strT0 = null;
            for (String str : arrayListB) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                try {
                    strT0 = fo3.t0(context, str, null);
                    if (TextUtils.isEmpty(strT0)) {
                        try {
                            xg3VarA.g(new URL(str).getHost(), -1, System.currentTimeMillis() - jCurrentTimeMillis, a(fo3Var, str, null, strT0), null);
                        } catch (MalformedURLException unused) {
                        }
                    } else {
                        try {
                            xg3VarA.g(new URL(str).getHost(), 0, System.currentTimeMillis() - jCurrentTimeMillis, a(fo3Var, str, null, strT0), null);
                            break;
                        } catch (MalformedURLException unused2) {
                        }
                    }
                } catch (IOException e) {
                    try {
                        xg3VarA.g(new URL(str).getHost(), -1, System.currentTimeMillis() - jCurrentTimeMillis, a(fo3Var, str, null, strT0), e);
                    } catch (MalformedURLException unused3) {
                    }
                    e.printStackTrace();
                }
            }
            return strT0;
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String c(ArrayList arrayList) {
        String strC = bi3.c(i(arrayList));
        return (TextUtils.isEmpty(strC) || strC.length() <= 4) ? a.c : strC.substring(0, 4).toLowerCase();
    }

    public static void d(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        long j = sharedPreferences.getLong("last_sync_info", -1L);
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        long jA = md3.c(context).a(28, 1209600);
        if (j == -1) {
            sharedPreferences.edit().putLong("last_sync_info", jCurrentTimeMillis).commit();
            return;
        }
        if (Math.abs(jCurrentTimeMillis - j) > jA) {
            sc3 sc3VarE = sc3.e(context);
            wj3 wj3Var = new wj3(2);
            wj3Var.d = context;
            wj3Var.c = true;
            VarHandle.storeStoreFence();
            sc3VarE.i(wj3Var, 0);
            sharedPreferences.edit().putLong("last_sync_info", jCurrentTimeMillis).commit();
        }
    }

    public static void e(Context context, ao3 ao3Var) {
        kg3.e("need to update local info with: " + ao3Var.i);
        String str = (String) ao3Var.i.get("accept_time");
        if (str != null) {
            fp1.q(context);
            String[] strArrSplit = str.split("-");
            if (strArrSplit.length == 2) {
                fp1.a(context, strArrSplit[0], strArrSplit[1]);
                if ("00:00".equals(strArrSplit[0]) && "00:00".equals(strArrSplit[1])) {
                    re3.d(context).h(true);
                } else {
                    re3.d(context).h(false);
                }
            }
        }
        String str2 = (String) ao3Var.i.get("aliases");
        if (str2 != null) {
            Context context2 = fp1.a;
            synchronized (fp1.class) {
                Iterator it = fp1.k(context).iterator();
                while (it.hasNext()) {
                    fp1.s(context, (String) it.next());
                }
            }
            if (!a.c.equals(str2)) {
                for (String str3 : str2.split(aa.b)) {
                    fp1.c(context, str3);
                }
            }
        }
        String str4 = (String) ao3Var.i.get("topics");
        if (str4 != null) {
            Context context3 = fp1.a;
            synchronized (fp1.class) {
                Iterator it2 = fp1.l(context).iterator();
                while (it2.hasNext()) {
                    fp1.u(context, (String) it2.next());
                }
            }
            if (!a.c.equals(str4)) {
                for (String str5 : str4.split(aa.b)) {
                    fp1.d(context, str5);
                }
            }
        }
        String str6 = (String) ao3Var.i.get("user_accounts");
        if (str6 != null) {
            Context context4 = fp1.a;
            synchronized (fp1.class) {
                Iterator it3 = fp1.m(context).iterator();
                while (it3.hasNext()) {
                    fp1.r(context, (String) it3.next());
                }
            }
            if (a.c.equals(str6)) {
                return;
            }
            for (String str7 : str6.split(aa.b)) {
                fp1.b(context, str7);
            }
        }
    }

    public static final vn0 f(vn0 vn0Var, String str) {
        return vn0Var.a(gr1.e(str));
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public static final Object g(m22 m22Var, np0 np0Var, k20 k20Var) {
        l22 l22Var;
        if (k20Var instanceof l22) {
            l22Var = (l22) k20Var;
            int i = l22Var.g;
            if ((i & Integer.MIN_VALUE) != 0) {
                l22Var.g = i - Integer.MIN_VALUE;
            } else {
                l22Var = new l22(k20Var);
            }
        } else {
            l22Var = new l22(k20Var);
        }
        Object obj = l22Var.f;
        h30 h30Var = h30.b;
        int i2 = l22Var.g;
        try {
            if (i2 == 0) {
                kk3.y(obj);
                x20 x20Var = l22Var.c;
                x20Var.getClass();
                if (x20Var.B(f70.g) != m22Var) {
                    ik.j("awaitClose() can only be invoked from the producer context");
                    return null;
                }
                l22Var.e = np0Var;
                l22Var.g = 1;
                ms msVar = new ms(1, kq2.n(l22Var));
                msVar.x();
                g0 g0Var = new g0(17);
                g0Var.c = msVar;
                VarHandle.storeStoreFence();
                m22Var.c(g0Var);
                if (msVar.v() == h30Var) {
                    return h30Var;
                }
            } else {
                if (i2 != 1) {
                    ik.j("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                np0Var = l22Var.e;
                kk3.y(obj);
            }
            np0Var.e();
            return c13.a;
        } catch (Throwable th) {
            np0Var.e();
            throw th;
        }
    }

    public static ob2 h() {
        v vVar = new v();
        vVar.p("https://push-service.sec.miui.com");
        dw1 dw1Var = new dw1();
        dw1Var.a(15L);
        dw1Var.b(15L);
        dw1Var.c.add(new v62());
        vVar.c = new ew1(dw1Var);
        vVar.m(kr0.c());
        return (ob2) vVar.r().b(ob2.class);
    }

    public static String i(ArrayList arrayList) {
        boolean zB = bt1.b(arrayList);
        String strA = a.c;
        if (zB) {
            return a.c;
        }
        ArrayList<String> arrayList2 = new ArrayList(arrayList);
        Collections.sort(arrayList2, Collator.getInstance(Locale.CHINA));
        for (String str : arrayList2) {
            if (!TextUtils.isEmpty(strA)) {
                strA = strA.concat(aa.b);
            }
            strA = im2.A(strA, str);
        }
        return strA;
    }

    public static void j(View view) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "alpha", mp3.t(view.getContext()) ? 0.6f : 0.3f, 0.0f);
        objectAnimatorOfFloat.setInterpolator(new DecelerateInterpolator(1.5f));
        objectAnimatorOfFloat.setDuration(250L);
        objectAnimatorOfFloat.start();
    }

    public static int k(AccessibilityNodeInfo accessibilityNodeInfo) {
        return accessibilityNodeInfo.getChecked();
    }

    public static int l(AccessibilityNodeInfo accessibilityNodeInfo) {
        return accessibilityNodeInfo.getExpandedState();
    }

    public static int m(ViewConfiguration viewConfiguration, int i, int i2, int i3) {
        return viewConfiguration.getScaledMaximumFlingVelocity(i, i2, i3);
    }

    public static int n(ViewConfiguration viewConfiguration, int i, int i2, int i3) {
        return viewConfiguration.getScaledMinimumFlingVelocity(i, i2, i3);
    }

    public static CharSequence o(AccessibilityNodeInfo accessibilityNodeInfo) {
        return accessibilityNodeInfo.getSupplementalDescription();
    }

    public static boolean p(AccessibilityNodeInfo accessibilityNodeInfo) {
        return accessibilityNodeInfo.isFieldRequired();
    }

    public static boolean q() {
        if (Build.IS_INTERNATIONAL_BUILD) {
            return false;
        }
        try {
            return GuardApplication.application.getPackageManager().getApplicationInfo("com.miui.securitycenter", 128).metaData.getBoolean("securitycenter.support.mix.scan");
        } catch (Exception unused) {
            Log.e("GuardProvider", "not support mix scan in securitycenter");
            return false;
        }
    }

    public static ro1 r(MappedByteBuffer mappedByteBuffer) throws IOException {
        long j;
        ByteBuffer byteBufferDuplicate = mappedByteBuffer.duplicate();
        byteBufferDuplicate.order(ByteOrder.BIG_ENDIAN);
        byteBufferDuplicate.position(byteBufferDuplicate.position() + 4);
        int i = byteBufferDuplicate.getShort() & 65535;
        if (i > 100) {
            ik.u("Cannot read metadata.");
            return null;
        }
        byteBufferDuplicate.position(byteBufferDuplicate.position() + 6);
        int i2 = 0;
        while (true) {
            if (i2 >= i) {
                j = -1;
                break;
            }
            int i3 = byteBufferDuplicate.getInt();
            byteBufferDuplicate.position(byteBufferDuplicate.position() + 4);
            j = ((long) byteBufferDuplicate.getInt()) & 4294967295L;
            byteBufferDuplicate.position(byteBufferDuplicate.position() + 4);
            if (1835365473 == i3) {
                break;
            }
            i2++;
        }
        if (j != -1) {
            byteBufferDuplicate.position(byteBufferDuplicate.position() + ((int) (j - ((long) byteBufferDuplicate.position()))));
            byteBufferDuplicate.position(byteBufferDuplicate.position() + 12);
            long j2 = ((long) byteBufferDuplicate.getInt()) & 4294967295L;
            for (int i4 = 0; i4 < j2; i4++) {
                int i5 = byteBufferDuplicate.getInt();
                long j3 = ((long) byteBufferDuplicate.getInt()) & 4294967295L;
                byteBufferDuplicate.getInt();
                if (1164798569 == i5 || 1701669481 == i5) {
                    byteBufferDuplicate.position((int) (j3 + j));
                    ro1 ro1Var = new ro1();
                    byteBufferDuplicate.order(ByteOrder.LITTLE_ENDIAN);
                    int iPosition = byteBufferDuplicate.position() + byteBufferDuplicate.getInt(byteBufferDuplicate.position());
                    ro1Var.e = byteBufferDuplicate;
                    ro1Var.b = iPosition;
                    int i6 = iPosition - byteBufferDuplicate.getInt(iPosition);
                    ro1Var.c = i6;
                    ro1Var.d = ((ByteBuffer) ro1Var.e).getShort(i6);
                    return ro1Var;
                }
            }
        }
        ik.u("Cannot read metadata.");
        return null;
    }

    public static final Object s(pp0 pp0Var) {
        Thread.interrupted();
        return uk3.C(lf0.b, new b40(pp0Var, (j20) null));
    }

    public static void t(TextView textView, int i) {
        if (i < 0) {
            cw1.a();
            return;
        }
        int fontMetricsInt = textView.getPaint().getFontMetricsInt(null);
        if (i != fontMetricsInt) {
            textView.setLineSpacing(i - fontMetricsInt, 1.0f);
        }
    }

    public static void u(View view) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "alpha", 0.0f, mp3.t(view.getContext()) ? 0.6f : 0.3f);
        objectAnimatorOfFloat.setInterpolator(new DecelerateInterpolator(1.5f));
        objectAnimatorOfFloat.setDuration(300L);
        objectAnimatorOfFloat.start();
    }

    public static final vn0 v(vn0 vn0Var, vn0 vn0Var2) {
        vn0Var.getClass();
        wn0 wn0Var = vn0Var.a;
        vn0Var2.getClass();
        wn0 wn0Var2 = vn0Var2.a;
        if (!vn0Var.equals(vn0Var2) && !wn0Var2.c()) {
            String str = wn0Var.a;
            String str2 = wn0Var2.a;
            if (!fq2.M(str, str2) || str.charAt(str2.length()) != '.') {
                return vn0Var;
            }
        }
        if (wn0Var2.c()) {
            return vn0Var;
        }
        return vn0Var.equals(vn0Var2) ? vn0.c : new vn0(wn0Var.a.substring(wn0Var2.a.length() + 1));
    }
}
