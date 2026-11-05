package M;

import J.AbstractC0026s;
import J.G;
import Y.C0038e;
import Y.C0041h;
import Y.C0042i;
import Y.C0043j;
import Y.C0046m;
import Y.H;
import Y.RunnableC0037d;
import Y.Y;
import android.animation.ValueAnimator;
import android.os.Looper;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Slog;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.z;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.xiaomi.continuity.channel.SendResultCallback;
import com.xiaomi.trustservice.lockscreenui.gesture.GestureDrawingView;
import com.xiaomi.trustservice.lockscreenui.numeric.NumericKeypadView;
import com.xiaomi.trustservice.ota.SocidProvision;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;
import p021l.C0123j;
import p021l.C0130m0;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public /* synthetic */ b(int i2, Object obj) {
        this.a = i2;
        this.b = obj;
    }

    /* JADX DEBUG: Class process forced to load method for inline: androidx.activity.ComponentActivity.f(androidx.activity.ComponentActivity):void */
    @Override // java.lang.Runnable
    public final void run() {
        int i2;
        boolean z2;
        C0123j c0123j;
        Object obj = this.b;
        switch (this.a) {
            case 0:
                h hVar = (h) obj;
                if (hVar.f197o) {
                    boolean z3 = hVar.f195m;
                    a aVar = hVar.a;
                    if (z3) {
                        hVar.f195m = false;
                        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                        aVar.f182e = jCurrentAnimationTimeMillis;
                        aVar.f184g = -1L;
                        aVar.f183f = jCurrentAnimationTimeMillis;
                        aVar.h = 0.5f;
                    }
                    if ((aVar.f184g > 0 && AnimationUtils.currentAnimationTimeMillis() > aVar.f184g + ((long) aVar.f185i)) || !hVar.e()) {
                        hVar.f197o = false;
                        return;
                    }
                    boolean z4 = hVar.f196n;
                    ListView listView = hVar.f187c;
                    if (z4) {
                        hVar.f196n = false;
                        long jUptimeMillis = SystemClock.uptimeMillis();
                        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                        listView.onTouchEvent(motionEventObtain);
                        motionEventObtain.recycle();
                    }
                    if (aVar.f183f == 0) {
                        throw new RuntimeException("Cannot compute scroll delta before calling start()");
                    }
                    long jCurrentAnimationTimeMillis2 = AnimationUtils.currentAnimationTimeMillis();
                    float fA = aVar.a(jCurrentAnimationTimeMillis2);
                    long j2 = jCurrentAnimationTimeMillis2 - aVar.f183f;
                    aVar.f183f = jCurrentAnimationTimeMillis2;
                    i.b(hVar.f199q, (int) (j2 * ((fA * 4.0f) + ((-4.0f) * fA * fA)) * aVar.d));
                    WeakHashMap weakHashMap = G.a;
                    AbstractC0026s.m(listView, this);
                    return;
                }
                return;
            case 1:
                ((P.e) obj).n(0);
                return;
            case 2:
                ((GestureDrawingView) obj).d.g();
                return;
            case 3:
                A0.l lVar = (A0.l) obj;
                lVar.getClass();
                int i3 = NumericKeypadView.f1656n;
                ((NumericKeypadView) lVar.b).setEnabled(true);
                return;
            case 4:
                Looper.prepare();
                try {
                    String str = (String) obj;
                    if (str == null) {
                        Slog.d("MiTrustService/SocidProvision", "action error");
                    } else if (str.equals("com.xiaomi.trustservice.ota.SocidProvision")) {
                        SocidProvision.a();
                    }
                    break;
                } catch (E1.b | RemoteException | IOException | NoSuchAlgorithmException unused) {
                    Slog.d("MiTrustService/SocidProvision", "error");
                }
                Looper.loop();
                return;
            case 5:
                C0046m c0046m = (C0046m) obj;
                int i4 = c0046m.f387A;
                ValueAnimator valueAnimator = c0046m.f410z;
                if (i4 != 1) {
                    i2 = 2;
                    if (i4 != 2) {
                        return;
                    }
                } else {
                    i2 = 2;
                    valueAnimator.cancel();
                }
                c0046m.f387A = 3;
                float[] fArr = new float[i2];
                fArr[0] = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                fArr[1] = 0.0f;
                valueAnimator.setFloatValues(fArr);
                valueAnimator.setDuration(500);
                valueAnimator.start();
                return;
            case SendResultCallback.SEND_FAILED_READ_FILE_EXCEPTION /* 6 */:
                RecyclerView recyclerView = (RecyclerView) obj;
                H h = recyclerView.f995H;
                if (h != null) {
                    C0043j c0043j = (C0043j) h;
                    ArrayList arrayList = c0043j.h;
                    boolean zIsEmpty = arrayList.isEmpty();
                    ArrayList arrayList2 = c0043j.f375j;
                    boolean zIsEmpty2 = arrayList2.isEmpty();
                    ArrayList arrayList3 = c0043j.f376k;
                    boolean zIsEmpty3 = arrayList3.isEmpty();
                    ArrayList arrayList4 = c0043j.f374i;
                    boolean zIsEmpty4 = arrayList4.isEmpty();
                    if (!zIsEmpty || !zIsEmpty2 || !zIsEmpty4 || !zIsEmpty3) {
                        Iterator it = arrayList.iterator();
                        while (true) {
                            boolean zHasNext = it.hasNext();
                            long j3 = c0043j.d;
                            if (zHasNext) {
                                Y y2 = (Y) it.next();
                                View view = y2.a;
                                ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
                                c0043j.f382q.add(y2);
                                viewPropertyAnimatorAnimate.setDuration(j3).alpha(0.0f).setListener(new C0038e(c0043j, y2, viewPropertyAnimatorAnimate, view)).start();
                            } else {
                                arrayList.clear();
                                if (!zIsEmpty2) {
                                    ArrayList arrayList5 = new ArrayList();
                                    arrayList5.addAll(arrayList2);
                                    c0043j.f378m.add(arrayList5);
                                    arrayList2.clear();
                                    RunnableC0037d runnableC0037d = new RunnableC0037d(c0043j, arrayList5, 0);
                                    if (zIsEmpty) {
                                        runnableC0037d.run();
                                    } else {
                                        View view2 = ((C0042i) arrayList5.get(0)).a.a;
                                        WeakHashMap weakHashMap2 = G.a;
                                        AbstractC0026s.n(view2, runnableC0037d, j3);
                                    }
                                }
                                if (!zIsEmpty3) {
                                    ArrayList arrayList6 = new ArrayList();
                                    arrayList6.addAll(arrayList3);
                                    c0043j.f379n.add(arrayList6);
                                    arrayList3.clear();
                                    RunnableC0037d runnableC0037d2 = new RunnableC0037d(c0043j, arrayList6, 1);
                                    if (zIsEmpty) {
                                        runnableC0037d2.run();
                                    } else {
                                        View view3 = ((C0041h) arrayList6.get(0)).a.a;
                                        WeakHashMap weakHashMap3 = G.a;
                                        AbstractC0026s.n(view3, runnableC0037d2, j3);
                                    }
                                }
                                if (!zIsEmpty4) {
                                    ArrayList arrayList7 = new ArrayList();
                                    arrayList7.addAll(arrayList4);
                                    c0043j.f377l.add(arrayList7);
                                    arrayList4.clear();
                                    RunnableC0037d runnableC0037d3 = new RunnableC0037d(c0043j, arrayList7, 2);
                                    if (zIsEmpty && zIsEmpty2 && zIsEmpty3) {
                                        runnableC0037d3.run();
                                    } else {
                                        if (zIsEmpty) {
                                            j3 = 0;
                                        }
                                        long jMax = Math.max(!zIsEmpty2 ? c0043j.f290e : 0L, !zIsEmpty3 ? c0043j.f291f : 0L) + j3;
                                        View view4 = ((Y) arrayList7.get(0)).a;
                                        WeakHashMap weakHashMap4 = G.a;
                                        AbstractC0026s.n(view4, runnableC0037d3, jMax);
                                    }
                                }
                            }
                        }
                    }
                    z2 = false;
                } else {
                    z2 = false;
                }
                recyclerView.f1023i0 = z2;
                return;
            case SendResultCallback.SEND_END_CANCELED /* 7 */:
                ((StaggeredGridLayoutManager) obj).w0();
                return;
            case 8:
                try {
                    super/*android.app.Activity*/.onBackPressed();
                    return;
                } catch (IllegalStateException e2) {
                    if (!TextUtils.equals(e2.getMessage(), "Can not perform this action after onSaveInstanceState")) {
                        throw e2;
                    }
                    return;
                }
            case 9:
                z zVar = (z) obj;
                int i5 = zVar.b;
                androidx.lifecycle.s sVar = zVar.f962f;
                if (i5 == 0) {
                    zVar.f960c = true;
                    sVar.d(androidx.lifecycle.k.ON_PAUSE);
                }
                if (zVar.a == 0 && zVar.f960c) {
                    sVar.d(androidx.lifecycle.k.ON_STOP);
                    zVar.d = true;
                    return;
                }
                return;
            case 10:
                p020k0.c cVar = (p020k0.c) obj;
                cVar.f2150c = false;
                BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) cVar.f2151e;
                P.e eVar = bottomSheetBehavior.f1173M;
                if (eVar != null && eVar.f()) {
                    cVar.a(cVar.b);
                    return;
                } else {
                    if (bottomSheetBehavior.f1172L == 2) {
                        bottomSheetBehavior.A(cVar.b);
                        return;
                    }
                    return;
                }
            case 11:
                C0130m0 c0130m0 = (C0130m0) obj;
                c0130m0.f2289l = null;
                c0130m0.drawableStateChanged();
                return;
            case 12:
                ActionMenuView actionMenuView = ((Toolbar) obj).a;
                if (actionMenuView == null || (c0123j = actionMenuView.f591t) == null) {
                    return;
                }
                c0123j.l();
                return;
            default:
                View view5 = (View) obj;
                ((InputMethodManager) view5.getContext().getSystemService("input_method")).showSoftInput(view5, 1);
                return;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
    public b(SocidProvision socidProvision, String str) {
        this.a = 4;
        this.b = str;
    }
}
