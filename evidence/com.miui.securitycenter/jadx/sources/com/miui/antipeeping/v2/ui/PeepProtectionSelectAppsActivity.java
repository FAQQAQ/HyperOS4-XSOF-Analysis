package com.miui.antipeeping.v2.ui;

import aj.c0;
import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.j0;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.miui.antipeeping.v2.ui.PeepProtectionSelectAppsActivity;
import com.miui.common.base.BaseActivity;
import com.miui.common.utils.m2;
import com.miui.common.utils.q1;
import com.miui.maml.folme.AnimatedProperty;
import com.miui.networkassistant.utils.VirtualSimUtil;
import com.miui.securitycenter.R;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;
import miuix.appcompat.app.AlertDialog;
import miuix.appcompat.app.LayoutUiModeHelper;
import miuix.recyclerview.widget.RecyclerView;
import z3.e0;
import z3.g0;
import z3.i0;
import z3.k0;

/* JADX INFO: loaded from: classes2.dex */
public class PeepProtectionSelectAppsActivity extends BaseActivity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private t f10382a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private RecyclerView f10383b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private View f10384c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private View f10385d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private TextView f10386e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f10387f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f10388g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private miuix.view.u f10389h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f10390i = false;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private List f10391j = new ArrayList();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final TextWatcher f10392k = new a();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final miuix.view.u.b f10393l = new b();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f10394m = false;

    class a implements TextWatcher {
        a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            PeepProtectionSelectAppsActivity.this.updateSearchResult(editable.toString().trim());
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i10, int i11, int i12) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i10, int i11, int i12) {
        }
    }

    class b implements miuix.view.u.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final miuix.view.a f10396a = new a();

        class a implements miuix.view.a {
            a() {
            }

            @Override // miuix.view.a
            public void onStart(boolean z10) {
                Log.i("ANTI.V2SelectApps", "anim onStart: toVisible=" + z10 + " contentInsetTop=" + PeepProtectionSelectAppsActivity.this.f10388g);
                PeepProtectionSelectAppsActivity.this.f10383b.stopScroll();
                View viewFindViewById = PeepProtectionSelectAppsActivity.this.findViewById(R.id.search_panel);
                if (z10) {
                    if (viewFindViewById != null) {
                        viewFindViewById.setVisibility(0);
                    }
                    PeepProtectionSelectAppsActivity.this.f10383b.setVisibility(4);
                } else {
                    if (viewFindViewById != null) {
                        viewFindViewById.setVisibility(8);
                    }
                    PeepProtectionSelectAppsActivity.this.f10383b.setVisibility(0);
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append("anim onStart: searchPanelExists=");
                sb2.append(viewFindViewById != null);
                Log.i("ANTI.V2SelectApps", sb2.toString());
            }

            @Override // miuix.view.a
            public void onStop(boolean z10) {
                Log.i("ANTI.V2SelectApps", "anim onStop: toVisible=" + z10 + " contentInsetTop=" + PeepProtectionSelectAppsActivity.this.f10388g + " rvPaddingTop=" + PeepProtectionSelectAppsActivity.this.f10383b.getPaddingTop());
                PeepProtectionSelectAppsActivity.this.f10383b.setVisibility(0);
            }
        }

        b() {
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return true;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: android.view.ActionMode */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            miuix.view.u uVar = (miuix.view.u) actionMode;
            uVar.setAnchorView(PeepProtectionSelectAppsActivity.this.f10385d);
            uVar.setResultView(PeepProtectionSelectAppsActivity.this.f10383b);
            uVar.getSearchInput().setHint(R.string.peep_protection_v2_search_hint);
            uVar.getSearchInput().addTextChangedListener(PeepProtectionSelectAppsActivity.this.f10392k);
            uVar.addAnimationListener(this.f10396a);
            return true;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: android.view.ActionMode */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode actionMode) {
            miuix.view.u uVar = (miuix.view.u) actionMode;
            uVar.getSearchInput().removeTextChangedListener(PeepProtectionSelectAppsActivity.this.f10392k);
            uVar.removeAnimationListener(this.f10396a);
            Log.i("ANTI.V2SelectApps", "onDestroyActionMode: contentInsetTop=" + PeepProtectionSelectAppsActivity.this.f10388g);
            PeepProtectionSelectAppsActivity.this.exitSearchMode();
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return true;
        }
    }

    public static /* synthetic */ void A0(DialogInterface dialogInterface, int i10) {
        l7.e.n("peep_category_tip_dismissed", true);
        dialogInterface.dismiss();
    }

    public static /* synthetic */ int J0(Map map, Map map2, t.d dVar, t.d dVar2) {
        int iIntValue = ((Integer) map.getOrDefault(Integer.valueOf(dVar.f10475e), 0)).intValue();
        int iIntValue2 = ((Integer) map.getOrDefault(Integer.valueOf(dVar2.f10475e), 0)).intValue();
        if (iIntValue != iIntValue2) {
            return iIntValue2 - iIntValue;
        }
        String str = dVar.f10472b;
        if (str == null) {
            str = "";
        }
        String str2 = (String) map2.getOrDefault(str, "");
        String str3 = dVar2.f10472b;
        if (str3 == null) {
            str3 = "";
        }
        return str2.compareTo((String) map2.getOrDefault(str3, ""));
    }

    private void S0(List list) {
        ApplicationInfo applicationInfoP;
        HashSet hashSet = new HashSet();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = ((ApplicationInfo) it.next()).packageName;
            if (str != null) {
                hashSet.add(str);
            }
        }
        for (String str2 : z3.k.f49257a) {
            if (!hashSet.contains(str2) && (applicationInfoP = q1.p(this, str2)) != null) {
                list.add(applicationInfoP);
            }
        }
    }

    private void T0() {
        if (this.f10383b == null) {
            return;
        }
        int i10 = this.f10387f + ((int) (getResources().getDisplayMetrics().density * 28.0f));
        RecyclerView recyclerView = this.f10383b;
        recyclerView.setPadding(recyclerView.getPaddingLeft(), this.f10383b.getPaddingTop(), this.f10383b.getPaddingRight(), i10);
    }

    private static String U0(String str) {
        StringBuffer stringBuffer;
        if (str == null || str.isEmpty()) {
            return "";
        }
        try {
            com.miui.appmanager.n nVarA = ig.o.a(str);
            if (nVarA != null && (stringBuffer = nVarA.f13108a) != null && stringBuffer.length() > 0) {
                return nVarA.f13108a.toString().toLowerCase(Locale.ENGLISH);
            }
        } catch (Exception e10) {
            Log.w("ANTI.V2SelectApps", "buildPinyinSortKey failed for: " + str, e10);
        }
        return str.toLowerCase(Locale.ENGLISH);
    }

    private void V0() {
        try {
            PackageManager packageManager = getPackageManager();
            StringBuilder sb2 = new StringBuilder();
            int i10 = 0;
            for (String str : z3.k.f49257a) {
                if (i10 >= 5) {
                    break;
                }
                ApplicationInfo applicationInfoP = q1.p(this, str);
                if (applicationInfoP != null) {
                    if (sb2.length() > 0) {
                        sb2.append("、");
                    }
                    sb2.append(packageManager.getApplicationLabel(applicationInfoP).toString());
                    i10++;
                }
            }
            if (sb2.length() == 0) {
                return;
            }
            String string = getString(R.string.peep_protection_v2_category_tip_prompt, sb2.toString());
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService("clipboard");
            if (clipboardManager != null) {
                clipboardManager.setPrimaryClip(ClipData.newPlainText("category_tip_prompt", string));
                Toast.makeText(this, R.string.peep_protection_v2_smart_enable_prompt_copied, 0).show();
            }
        } catch (Exception e10) {
            Log.e("ANTI.V2SelectApps", "copyCategoryPromptToClipboard failed", e10);
        }
    }

    private void W0() {
        TextView textView = this.f10386e;
        if (textView != null) {
            textView.setVisibility(8);
        }
    }

    private void X0() {
        Intent intent;
        if (this.f10394m || (intent = getIntent()) == null) {
            return;
        }
        if (intent.getBooleanExtra("highlight_notification", false) || VirtualSimUtil.LAUNCH_FROM_NOTIFICATION.equals(intent.getStringExtra("target_key"))) {
            this.f10394m = true;
            this.f10383b.postDelayed(new Runnable() { // from class: g4.t0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f30856a.Z0();
                }
            }, 300L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void Y0() {
        androidx.recyclerview.widget.RecyclerView.c0 c0VarFindViewHolderForAdapterPosition = this.f10383b.findViewHolderForAdapterPosition(1);
        if (c0VarFindViewHolderForAdapterPosition != null) {
            ObjectAnimator.ofFloat(c0VarFindViewHolderForAdapterPosition.itemView, AnimatedProperty.PROPERTY_NAME_ALPHA, 1.0f, 0.3f, 1.0f, 0.3f, 1.0f).setDuration(1200L).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void Z0() {
        this.f10383b.smoothScrollToPosition(1);
        this.f10383b.postDelayed(new Runnable() { // from class: g4.u0
            @Override // java.lang.Runnable
            public final void run() {
                this.f30860a.Y0();
            }
        }, 400L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a1(List list, List list2) {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        Log.i("ANTI.V2SelectApps", "loadApps callback: mIsInSearchMode=" + this.f10390i + " size=" + list.size());
        this.f10391j = list;
        this.f10382a.S(list2);
        if (!this.f10390i) {
            this.f10382a.updateData(list);
        }
        X0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:36:0x0129  */
    /* JADX WARN: Code duplicated, block: B:50:0x0161  */
    /* JADX WARN: Code duplicated, block: B:53:0x018d  */
    /* JADX WARN: Code duplicated, block: B:55:0x0192  */
    public /* synthetic */ void b1() {
        int i10;
        int iM1;
        int i11;
        c4.c.a aVar;
        String str;
        int iJ1;
        Map mapC = z3.k.c();
        c4.d dVarA = c4.d.A(this);
        List listC = t3.g.c();
        S0(listC);
        PackageManager packageManager = getPackageManager();
        List<c4.b> listM0 = dVarA.m0();
        HashMap map = new HashMap();
        for (c4.b bVar : listM0) {
            map.put(bVar.c() + "@" + bVar.h(), bVar);
        }
        final List<c4.c.a> listN0 = dVarA.n0();
        HashMap map2 = new HashMap();
        for (c4.c.a aVar2 : listN0) {
            map2.put(Integer.valueOf(aVar2.f8222a), aVar2);
        }
        final ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        HashSet hashSet = new HashSet(g0.e());
        Iterator it = listC.iterator();
        while (it.hasNext()) {
            ApplicationInfo applicationInfo = (ApplicationInfo) it.next();
            String str2 = applicationInfo.packageName;
            if (str2 != null) {
                if (hashSet.contains(str2)) {
                    int iO = m2.o(applicationInfo.uid);
                    StringBuilder sb2 = new StringBuilder();
                    Iterator it2 = it;
                    sb2.append(applicationInfo.packageName);
                    sb2.append("@");
                    sb2.append(iO);
                    c4.b bVar2 = (c4.b) map.get(sb2.toString());
                    if (bVar2 != null && bVar2.f() == 1) {
                        dVarA.M0(applicationInfo.packageName, iO, 0);
                    }
                    it = it2;
                } else {
                    Iterator it3 = it;
                    String string = applicationInfo.loadLabel(packageManager).toString();
                    int iO2 = m2.o(applicationInfo.uid);
                    Integer num = (Integer) mapC.get(applicationInfo.packageName);
                    int iIntValue = num != null ? num.intValue() : -1;
                    StringBuilder sb3 = new StringBuilder();
                    Map map3 = mapC;
                    sb3.append(applicationInfo.packageName);
                    sb3.append("@");
                    sb3.append(iO2);
                    c4.b bVar3 = (c4.b) map.get(sb3.toString());
                    if (bVar3 != null) {
                        i10 = iO2;
                        boolean z10 = bVar3.f() == 1;
                        if (bVar3 != null) {
                            iM1 = bVar3.b();
                            if ((TtmlNode.TEXT_EMPHASIS_AUTO.equals(bVar3.e()) || "recommend".equals(bVar3.e())) && iM1 == 3 && iIntValue != -1 && (iJ1 = j1(iIntValue)) != 3 && iJ1 != iM1) {
                                bVar3.j(iJ1);
                                arrayList3.add(bVar3);
                                iM1 = iJ1;
                            }
                            i11 = iIntValue;
                        } else {
                            iM1 = m1(applicationInfo.packageName, iIntValue);
                            i11 = iIntValue;
                            c4.b bVar4 = new c4.b(applicationInfo.packageName, string, i10, i11, TtmlNode.TEXT_EMPHASIS_AUTO, 0);
                            bVar4.j(iM1);
                            arrayList2.add(bVar4);
                        }
                        int i12 = iM1;
                        aVar = (c4.c.a) map2.get(Integer.valueOf(i12));
                        if (aVar != null) {
                            str = aVar.f8223b;
                        } else {
                            str = null;
                        }
                        arrayList.add(new t.d(applicationInfo.packageName, string, i10, i11, i12, str, z10));
                        packageManager = packageManager;
                        it = it3;
                        mapC = map3;
                    } else {
                        i10 = iO2;
                    }
                    if (bVar3 != null) {
                        iM1 = bVar3.b();
                        if (TtmlNode.TEXT_EMPHASIS_AUTO.equals(bVar3.e())) {
                            bVar3.j(iJ1);
                            arrayList3.add(bVar3);
                            iM1 = iJ1;
                        } else {
                            bVar3.j(iJ1);
                            arrayList3.add(bVar3);
                            iM1 = iJ1;
                        }
                        i11 = iIntValue;
                    } else {
                        iM1 = m1(applicationInfo.packageName, iIntValue);
                        i11 = iIntValue;
                        c4.b bVar5 = new c4.b(applicationInfo.packageName, string, i10, i11, TtmlNode.TEXT_EMPHASIS_AUTO, 0);
                        bVar5.j(iM1);
                        arrayList2.add(bVar5);
                    }
                    int i13 = iM1;
                    aVar = (c4.c.a) map2.get(Integer.valueOf(i13));
                    if (aVar != null) {
                        str = aVar.f8223b;
                    } else {
                        str = null;
                    }
                    arrayList.add(new t.d(applicationInfo.packageName, string, i10, i11, i13, str, z10));
                    packageManager = packageManager;
                    it = it3;
                    mapC = map3;
                }
            }
        }
        if (!arrayList2.isEmpty()) {
            dVarA.N(arrayList2);
        }
        int size = arrayList3.size();
        int i14 = 0;
        while (i14 < size) {
            Object obj = arrayList3.get(i14);
            i14++;
            c4.b bVar6 = (c4.b) obj;
            dVarA.L0(bVar6.c(), bVar6.h(), bVar6.b());
        }
        final HashMap map4 = new HashMap();
        for (c4.c.a aVar3 : listN0) {
            map4.put(Integer.valueOf(aVar3.f8222a), Integer.valueOf(aVar3.f8224c));
        }
        final HashMap map5 = new HashMap();
        int size2 = arrayList.size();
        int i15 = 0;
        while (i15 < size2) {
            Object obj2 = arrayList.get(i15);
            i15++;
            String str3 = ((t.d) obj2).f10472b;
            if (str3 == null) {
                str3 = "";
            }
            if (!map5.containsKey(str3)) {
                map5.put(str3, U0(str3));
            }
        }
        arrayList.sort(new Comparator() { // from class: com.miui.antipeeping.v2.ui.h
            @Override // java.util.Comparator
            public final int compare(Object obj3, Object obj4) {
                return PeepProtectionSelectAppsActivity.J0(map4, map5, (t.d) obj3, (t.d) obj4);
            }
        });
        runOnUiThread(new Runnable() { // from class: g4.y0
            @Override // java.lang.Runnable
            public final void run() {
                this.f30875a.a1(arrayList, listN0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c1(t.d dVar) {
        c4.d dVarA = c4.d.A(this);
        if (!dVar.f10477g) {
            dVarA.M0(dVar.f10471a, dVar.f10473c, 0);
            b4.d.z(this, dVar.f10471a);
        } else {
            c4.b bVar = new c4.b(dVar.f10471a, dVar.f10472b, dVar.f10473c, dVar.f10474d, "manual", 1);
            bVar.j(dVar.f10475e);
            dVarA.O(bVar);
            b4.d.A(this, dVar.f10471a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ WindowInsetsCompat d1(View view, WindowInsetsCompat windowInsetsCompat) {
        this.f10387f = windowInsetsCompat.f(WindowInsetsCompat.Type.f()).f3829d;
        T0();
        return windowInsetsCompat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e1(DialogInterface dialogInterface, int i10) {
        l7.e.n("peep_category_tip_dismissed", true);
        dialogInterface.dismiss();
        V0();
        e0.a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void exitSearchMode() {
        Log.i("ANTI.V2SelectApps", "exitSearchMode: mIsInSearchMode was " + this.f10390i);
        this.f10389h = null;
        this.f10390i = false;
        W0();
        RecyclerView recyclerView = this.f10383b;
        if (recyclerView != null) {
            recyclerView.setPadding(recyclerView.getPaddingLeft(), 0, this.f10383b.getPaddingRight(), this.f10383b.getPaddingBottom());
            T0();
        }
        View view = this.f10384c;
        if (view != null) {
            registerCoordinateScrollView(view);
        }
        RecyclerView recyclerView2 = this.f10383b;
        if (recyclerView2 != null) {
            recyclerView2.scrollToPosition(0);
        }
        t tVar = this.f10382a;
        if (tVar != null) {
            tVar.V(false);
            this.f10382a.updateData(this.f10391j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f1(t.d dVar, int i10, List list, int i11) {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        dVar.f10475e = i10;
        dVar.f10476f = ((c4.c.a) list.get(i11)).f8223b;
        i1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g1(final t.d dVar, c4.d dVar2, final int i10, final List list, final int i11) {
        c4.b bVar = new c4.b(dVar.f10471a, dVar.f10472b, dVar.f10473c, dVar.f10474d, "manual", dVar.f10477g ? 1 : 0);
        bVar.j(dVar.f10475e);
        dVar2.O(bVar);
        dVar2.a0(dVar.f10471a, dVar.f10473c, i10);
        runOnUiThread(new Runnable() { // from class: com.miui.antipeeping.v2.ui.d
            @Override // java.lang.Runnable
            public final void run() {
                this.f10429a.f1(dVar, i10, list, i11);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h1(int[] iArr, int i10, final t.d dVar, final c4.d dVar2, final List list, DialogInterface dialogInterface, final int i11) {
        final int i12 = iArr[i11];
        if (i12 == i10) {
            dialogInterface.dismiss();
        } else {
            dialogInterface.dismiss();
            c0.e().a(new Runnable() { // from class: com.miui.antipeeping.v2.ui.c
                @Override // java.lang.Runnable
                public final void run() {
                    this.f10423a.g1(dVar, dVar2, i12, list, i11);
                }
            });
        }
    }

    private void i1() {
        c0.e().a(new Runnable() { // from class: g4.v0
            @Override // java.lang.Runnable
            public final void run() {
                this.f30867a.b1();
            }
        });
    }

    private int j1(int i10) {
        return c4.d.V(i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k1(final t.d dVar) {
        c0.e().a(new Runnable() { // from class: com.miui.antipeeping.v2.ui.i
            @Override // java.lang.Runnable
            public final void run() {
                this.f10439a.c1(dVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l1(boolean z10) {
        k0.G(this, z10);
    }

    private int m1(String str, int i10) {
        return j1(i10);
    }

    private void n1() {
        if (pr.a.f40580a || l7.e.e("peep_category_tip_dismissed", false)) {
            return;
        }
        String string = getString(R.string.peep_protection_v2_category_tip_message);
        String string2 = getString(R.string.peep_protection_v2_expert_mode_hint);
        String str = string + "\n\n" + string2;
        SpannableString spannableString = new SpannableString(str);
        int length = str.length() - string2.length();
        spannableString.setSpan(new AbsoluteSizeSpan(13, true), length, str.length(), 33);
        spannableString.setSpan(new ForegroundColorSpan(getColor(R.color.peep_protection_hint_text_color)), length, str.length(), 33);
        new AlertDialog.Builder(this).setTitle(R.string.peep_protection_v2_category_tip_title).setMessage(spannableString).setPositiveButton(R.string.peep_protection_v2_go_miclaw, new DialogInterface.OnClickListener() { // from class: g4.w0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i10) {
                this.f30869a.e1(dialogInterface, i10);
            }
        }).setNegativeButton(R.string.button_text_let_me_select, new DialogInterface.OnClickListener() { // from class: g4.x0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i10) {
                PeepProtectionSelectAppsActivity.A0(dialogInterface, i10);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o1(final t.d dVar) {
        if (pr.a.f40580a) {
            return;
        }
        Vibrator vibrator = (Vibrator) getSystemService("vibrator");
        int i10 = -1;
        if (vibrator != null && vibrator.hasVibrator()) {
            vibrator.vibrate(VibrationEffect.createOneShot(30L, -1));
        }
        final c4.d dVarA = c4.d.A(this);
        final List listN0 = dVarA.n0();
        if (listN0 == null || listN0.isEmpty()) {
            return;
        }
        final int i11 = dVar.f10475e;
        String[] strArr = new String[listN0.size()];
        final int[] iArr = new int[listN0.size()];
        for (int i12 = 0; i12 < listN0.size(); i12++) {
            c4.c.a aVar = (c4.c.a) listN0.get(i12);
            int i13 = aVar.f8222a;
            iArr[i12] = i13;
            strArr[i12] = c4.d.t(this, i13, aVar.f8223b);
            if (aVar.f8222a == i11) {
                i10 = i12;
            }
        }
        new AlertDialog.Builder(this).setTitle(getString(R.string.peep_protection_v2_move_app_title_v2, dVar.f10472b)).setSingleChoiceItems(strArr, i10, new DialogInterface.OnClickListener() { // from class: com.miui.antipeeping.v2.ui.b
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i14) {
                this.f10417a.h1(iArr, i11, dVar, dVarA, listN0, dialogInterface, i14);
            }
        }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).show();
    }

    private void p1(int i10) {
        TextView textView = this.f10386e;
        if (textView == null) {
            Log.w("ANTI.V2SelectApps", "showSearchEmpty: mSearchEmptyView is null!");
            return;
        }
        textView.setText(i10);
        this.f10386e.setVisibility(0);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("showSearchEmpty: visible, resId=");
        sb2.append(i10);
        sb2.append(" parent=");
        sb2.append(this.f10386e.getParent() != null);
        sb2.append(" width=");
        sb2.append(this.f10386e.getWidth());
        sb2.append(" height=");
        sb2.append(this.f10386e.getHeight());
        Log.i("ANTI.V2SelectApps", sb2.toString());
    }

    private void q1() {
        Log.i("ANTI.V2SelectApps", "startSearchMode: mIsInSearchMode was " + this.f10390i);
        this.f10390i = true;
        View view = this.f10384c;
        if (view != null) {
            unregisterCoordinateScrollView(view);
        }
        miuix.view.u uVar = (miuix.view.u) startActionMode(this.f10393l);
        this.f10389h = uVar;
        if (uVar != null) {
            uVar.setAnchorApplyExtraPaddingByUser(true);
            this.f10389h.getSearchInput().setImeOptions(6);
        }
        t tVar = this.f10382a;
        if (tVar != null) {
            tVar.V(true);
            this.f10382a.updateData(new ArrayList());
        }
        p1(R.string.peep_protection_v2_search_empty_hint);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSearchResult(String str) {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            String lowerCase = str.toLowerCase();
            for (t.d dVar : this.f10391j) {
                if (dVar.f10472b.toLowerCase().contains(lowerCase)) {
                    arrayList.add(dVar);
                }
            }
        }
        this.f10382a.updateData(arrayList);
        if (TextUtils.isEmpty(str)) {
            p1(R.string.peep_protection_v2_search_empty_hint);
        } else if (arrayList.isEmpty()) {
            p1(R.string.peep_protection_v2_search_no_result);
        } else {
            W0();
        }
    }

    @Override // miuix.appcompat.app.AppCompatActivity, miuix.appcompat.app.IContentInsetState
    public void onContentInsetChanged(Rect rect) {
        super.onContentInsetChanged(rect);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("onContentInsetChanged: top=");
        sb2.append(rect != null ? rect.top : -1);
        sb2.append(" inSearch=");
        sb2.append(this.f10390i);
        sb2.append(" rvPaddingTop=");
        sb2.append(this.f10383b.getPaddingTop());
        Log.i("ANTI.V2SelectApps", sb2.toString());
        if (rect != null) {
            this.f10388g = rect.top;
            T0();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.miui.common.base.BaseActivity, miuix.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        com.miui.common.ui.e.h(this);
        super.onCreate(bundle);
        if (!k0.m(this)) {
            i0.a(this);
            finish();
            return;
        }
        if (k0.o(this)) {
            i0.d(this, getIntent());
            finish();
            return;
        }
        setExtraHorizontalPaddingEnable(true);
        setExtraPaddingApplyToContentEnable(true);
        LayoutUiModeHelper.autoSetLayoutUiMode(this);
        setContentView(R.layout.peep_protection_activity_select_apps);
        this.f10383b = (RecyclerView) findViewById(R.id.rv_apps);
        this.f10384c = findViewById(R.id.spring_back_root);
        View viewFindViewById = findViewById(R.id.am_search_view);
        this.f10385d = viewFindViewById;
        viewFindViewById.setVisibility(8);
        TextView textView = (TextView) findViewById(R.id.tv_search_empty);
        this.f10386e = textView;
        if (textView != null && (textView.getLayoutParams() instanceof FrameLayout.LayoutParams)) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.f10386e.getLayoutParams();
            layoutParams.topMargin = getResources().getDisplayMetrics().heightPixels / 3;
            this.f10386e.setLayoutParams(layoutParams);
        }
        this.f10383b.setHasFixedSize(true);
        this.f10383b.setItemViewCacheSize(20);
        ViewCompat.D0(this.f10383b, new j0() { // from class: g4.s0
            @Override // androidx.core.view.j0
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return this.f30850a.d1(view, windowInsetsCompat);
            }
        });
        t tVar = new t(this, new Consumer() { // from class: com.miui.antipeeping.v2.ui.e
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f10434a.k1((t.d) obj);
            }
        }, new t.k() { // from class: com.miui.antipeeping.v2.ui.f
            @Override // com.miui.antipeeping.v2.ui.t.k
            public final void a(boolean z10) {
                this.f10435a.l1(z10);
            }
        });
        this.f10382a = tVar;
        tVar.T(k0.q(this));
        this.f10382a.U(new t.j() { // from class: com.miui.antipeeping.v2.ui.g
            @Override // com.miui.antipeeping.v2.ui.t.j
            public final void a(t.d dVar) {
                this.f10436a.o1(dVar);
            }
        });
        this.f10383b.setLayoutManager(new LinearLayoutManager(this));
        this.f10383b.setAdapter(this.f10382a);
        this.f10383b.addItemDecoration(new u(this, (int) (getResources().getDisplayMetrics().density * 12.0f)));
        this.f10383b.setClipToPadding(false);
        this.f10383b.setSpringEnabled(false);
        i1();
        n1();
        new com.miui.common.ui.e(this).p(R.string.peep_protection_v2_select_apps).k(this.f10384c).m();
        f4.a.i();
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItemB = com.miui.common.ui.e.b(menu, 1001, R.drawable.miuix_appcompat_action_icon_search_light, R.string.miuix_appcompat_search_input_description);
        if (menuItemB.getIcon() != null) {
            Drawable drawableMutate = menuItemB.getIcon().mutate();
            drawableMutate.setTint(getColor(R.color.color_list_title));
            menuItemB.setIcon(drawableMutate);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override // com.miui.common.base.BaseActivity, miuix.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // com.miui.common.base.BaseActivity, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 1001) {
            return super.onOptionsItemSelected(menuItem);
        }
        q1();
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.f10390i || this.f10382a == null || this.f10391j.isEmpty()) {
            return;
        }
        i1();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z10) {
        super.onWindowFocusChanged(z10);
    }
}
