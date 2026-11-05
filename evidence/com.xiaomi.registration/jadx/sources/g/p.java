package g;

import android.app.AlarmManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import miui.os.Build;
import miui.telephony.PhoneDebug;

/* JADX INFO: loaded from: classes.dex */
public final class p {
    public final /* synthetic */ int a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1438b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f1439c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f1440d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f1441e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f1442f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f1443g;

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public p(View view) {
        this.a = 0;
        this.f1438b = -1;
        this.f1439c = view;
        this.f1440d = u.a();
    }

    public final void a() {
        View view = (View) this.f1439c;
        Drawable background = view.getBackground();
        if (background != null) {
            boolean z2 = false;
            if (((z1) this.f1441e) != null) {
                if (((z1) this.f1443g) == null) {
                    this.f1443g = new z1(0);
                }
                z1 z1Var = (z1) this.f1443g;
                z1Var.f1558c = null;
                z1Var.f1557b = false;
                z1Var.f1559d = null;
                z1Var.a = false;
                int[] iArr = b0.b0.a;
                ColorStateList colorStateListG = b0.t.g(view);
                if (colorStateListG != null) {
                    z1Var.f1557b = true;
                    z1Var.f1558c = colorStateListG;
                }
                PorterDuff.Mode modeH = b0.t.h(view);
                if (modeH != null) {
                    z1Var.a = true;
                    z1Var.f1559d = modeH;
                }
                if (z1Var.f1557b || z1Var.a) {
                    u.e(background, z1Var, view.getDrawableState());
                    z2 = true;
                }
                if (z2) {
                    return;
                }
            }
            z1 z1Var2 = (z1) this.f1442f;
            if (z1Var2 != null) {
                u.e(background, z1Var2, view.getDrawableState());
                return;
            }
            z1 z1Var3 = (z1) this.f1441e;
            if (z1Var3 != null) {
                u.e(background, z1Var3, view.getDrawableState());
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0042  */
    public final void b(int i3) {
        boolean z2;
        String string = ((SharedPreferences) this.f1441e).getString("sms_auto_register_regtime" + i3, "");
        if (string.isEmpty()) {
            z2 = false;
        } else {
            try {
                if ((System.currentTimeMillis() - new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).parse(string).getTime()) / 1000 > 1296000) {
                    z2 = true;
                } else {
                    z2 = false;
                }
            } catch (Exception unused) {
            }
        }
        if (z2) {
            PreferenceManager.getDefaultSharedPreferences((Context) this.f1440d).edit().putBoolean(l1.c.m0(i3, "state_sms_period_trigger"), true).apply();
            t(i3, "period");
        }
    }

    public final ColorStateList c() {
        Object obj = this.f1442f;
        if (((z1) obj) != null) {
            return (ColorStateList) ((z1) obj).f1558c;
        }
        return null;
    }

    public final PorterDuff.Mode d() {
        Object obj = this.f1442f;
        if (((z1) obj) != null) {
            return (PorterDuff.Mode) ((z1) obj).f1559d;
        }
        return null;
    }

    public final boolean e(int i3) {
        int activeDataSubscriptionId = SubscriptionManager.getActiveDataSubscriptionId();
        SubscriptionInfo activeSubscriptionInfoForSimSlotIndex = ((SubscriptionManager) this.f1443g).getActiveSubscriptionInfoForSimSlotIndex(i3);
        if (activeSubscriptionInfoForSimSlotIndex != null) {
            return activeSubscriptionInfoForSimSlotIndex.getSubscriptionId() == activeDataSubscriptionId;
        }
        return !TextUtils.isEmpty(((SharedPreferences) this.f1441e).getString("lte_imsi" + i3, ""));
    }

    public final boolean f(int i3) {
        int iP = j1.b.l((Context) this.f1440d).p(i3);
        if (iP == -1) {
            return false;
        }
        return ((TelephonyManager) this.f1442f).createForSubscriptionId(iP).isNetworkRoaming();
    }

    public final boolean g(int i3, int i4) {
        String strB = j1.c.b((Context) this.f1440d, i4);
        String string = ((SharedPreferences) this.f1441e).getString(l1.c.m0(i3, "second_slot_imsi"), "");
        if (TextUtils.isEmpty(strB) && !TextUtils.isEmpty(string)) {
            return true;
        }
        if (!TextUtils.isEmpty(strB) && TextUtils.isEmpty(string)) {
            return true;
        }
        if (TextUtils.isEmpty(strB)) {
            return false;
        }
        return !strB.equals(string);
    }

    public final boolean h(int i3) {
        int activeDataSubscriptionId = SubscriptionManager.getActiveDataSubscriptionId();
        SubscriptionInfo activeSubscriptionInfoForSimSlotIndex = ((SubscriptionManager) this.f1443g).getActiveSubscriptionInfoForSimSlotIndex(i3);
        return activeDataSubscriptionId != (activeSubscriptionInfoForSimSlotIndex != null ? activeSubscriptionInfoForSimSlotIndex.getSubscriptionId() : activeDataSubscriptionId);
    }

    public final void i(AttributeSet attributeSet, int i3) {
        ColorStateList colorStateListH;
        Object obj = this.f1439c;
        View view = (View) obj;
        Context context = view.getContext();
        int[] iArr = b.a.f502u;
        r1 r1VarM = r1.m(context, attributeSet, iArr, i3);
        Context context2 = view.getContext();
        TypedArray typedArray = (TypedArray) r1VarM.f1460b;
        int[] iArr2 = b0.b0.a;
        b0.y.c(view, context2, iArr, attributeSet, typedArray, i3, 0);
        try {
            if (r1VarM.k(0)) {
                this.f1438b = r1VarM.h(0, -1);
                u uVar = (u) this.f1440d;
                Context context3 = ((View) obj).getContext();
                int i4 = this.f1438b;
                synchronized (uVar) {
                    colorStateListH = uVar.a.h(context3, i4);
                }
                if (colorStateListH != null) {
                    p(colorStateListH);
                }
            }
            if (r1VarM.k(1)) {
                b0.t.q((View) obj, r1VarM.b(1));
            }
            if (r1VarM.k(2)) {
                b0.t.r((View) obj, l0.b(r1VarM.g(2, -1), null));
            }
            r1VarM.n();
        } catch (Throwable th) {
            r1VarM.n();
            throw th;
        }
    }

    public final void j(int i3) {
        Log.d("AutoReg_SmsWork", "[" + i3 + "] noResultRetry");
        l1.c.F((Context) this.f1440d, i3);
        int i4 = ((SharedPreferences) this.f1441e).getInt(l1.c.m0(i3, "register_request_ims_count"), 0) + 1;
        ((SharedPreferences) this.f1441e).edit().putInt(l1.c.m0(i3, "register_request_ims_count"), i4).apply();
        if (i4 < 4) {
            t(i3, "no_result_retry");
            return;
        }
        Log.d("AutoReg_SmsWork", "[" + i3 + "] sms auto registration achieved max request");
    }

    public final void k() {
        this.f1438b = -1;
        p(null);
        a();
    }

    public final void l(int i3) {
        ColorStateList colorStateListH;
        this.f1438b = i3;
        u uVar = (u) this.f1440d;
        if (uVar != null) {
            Context context = ((View) this.f1439c).getContext();
            synchronized (uVar) {
                colorStateListH = uVar.a.h(context, i3);
            }
        } else {
            colorStateListH = null;
        }
        p(colorStateListH);
        a();
    }

    public final String m(int i3) {
        Object obj = this.f1440d;
        String string = PreferenceManager.getDefaultSharedPreferences((Context) obj).getString(l1.c.m0(i3, "last_registration_id"), "");
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        long j3 = PreferenceManager.getDefaultSharedPreferences((Context) obj).getLong(l1.c.m0(i3, "last_registration_id_ts"), 0L);
        long jCurrentTimeMillis = System.currentTimeMillis() - j3;
        if (j3 <= 0 || jCurrentTimeMillis <= 1800000) {
            return string;
        }
        Log.w("AutoReg_SmsWork", "[" + i3 + "] stale regId aged " + jCurrentTimeMillis + "ms, discard");
        l1.c.F((Context) obj, i3);
        return "";
    }

    public final void n(int i3, int i4) {
        ((SharedPreferences) this.f1441e).edit().putString(l1.c.m0(i3, "second_slot_imsi"), j1.c.b((Context) this.f1440d, i4)).apply();
    }

    public final void o(int i3) {
        Object obj = this.f1439c;
        l1.c.F((Context) this.f1440d, i3);
        int i4 = ((SharedPreferences) this.f1441e).getInt(l1.c.m0(i3, "register_request_ims_count"), 0) + 1;
        ((SharedPreferences) this.f1441e).edit().putInt(l1.c.m0(i3, "register_request_ims_count"), i4).apply();
        if (i4 < 4) {
            try {
                ((AlarmManager) ((i1.b) obj).f1700e).setExactAndAllowWhileIdle(2, SystemClock.elapsedRealtime() + 600000, ((i1.b) obj).a("miui.intent.action.SMS_AUTO_REGISTRATION_RETRY"));
            } catch (Exception e3) {
                Log.e("AutoReg_SmsWork", "scheduleNextIfNeed", e3);
            }
        }
    }

    public final void p(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (((z1) this.f1441e) == null) {
                this.f1441e = new z1(0);
            }
            Object obj = this.f1441e;
            ((z1) obj).f1558c = colorStateList;
            ((z1) obj).f1557b = true;
        } else {
            this.f1441e = null;
        }
        a();
    }

    public final void q(ColorStateList colorStateList) {
        if (((z1) this.f1442f) == null) {
            this.f1442f = new z1(0);
        }
        z1 z1Var = (z1) this.f1442f;
        z1Var.f1558c = colorStateList;
        z1Var.f1557b = true;
        a();
    }

    public final void r(PorterDuff.Mode mode) {
        if (((z1) this.f1442f) == null) {
            this.f1442f = new z1(0);
        }
        z1 z1Var = (z1) this.f1442f;
        z1Var.f1559d = mode;
        z1Var.a = true;
        a();
    }

    public final void s(int i3) {
        String str;
        String string;
        int i4 = i3 == 1 ? 0 : 1;
        if (((TelephonyManager) this.f1442f).getSimState(i4) != 1 && TextUtils.isEmpty(miui.telephony.TelephonyManager.getDefault().getSimOperatorForSlot(i4))) {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
        String str2 = System.currentTimeMillis() + "_" + i3 + "_" + new Random().nextInt(10000);
        Context context = (Context) this.f1440d;
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(l1.c.m0(i3, "last_registration_id"), str2).putLong(l1.c.m0(i3, "last_registration_id_ts"), System.currentTimeMillis()).apply();
        Log.d("AutoReg_SmsWork", "[" + i3 + "] generated registration ID=" + str2);
        String strC = j1.c.c(context);
        String imeiForSlot = miui.telephony.TelephonyManager.getDefault().getImeiForSlot(i3);
        if (imeiForSlot == null) {
            imeiForSlot = "";
        }
        String imeiForSlot2 = miui.telephony.TelephonyManager.getDefault().getImeiForSlot(1 - i3);
        String str3 = imeiForSlot2 != null ? imeiForSlot2 : "";
        String strB = j1.c.b(context, i3);
        String strA = j1.c.a(context);
        if (this.f1438b == 1) {
            string = "01";
        } else {
            int i5 = i3 == 1 ? 0 : 1;
            StringBuilder sb = new StringBuilder();
            String simOperatorForSlot = miui.telephony.TelephonyManager.getDefault().getSimOperatorForSlot(i5);
            if (TextUtils.isEmpty(simOperatorForSlot)) {
                sb.append("0");
            } else if (f2.e.h(simOperatorForSlot)) {
                sb.append("1");
            } else if (f2.e.j(simOperatorForSlot)) {
                sb.append("2");
            } else {
                if (f2.e.i(simOperatorForSlot)) {
                    str = "3";
                } else {
                    str = ("46013".equals(simOperatorForSlot) || "46015".equals(simOperatorForSlot)) ? "5" : "4";
                }
                sb.append(str);
            }
            SubscriptionInfo activeSubscriptionInfoForSimSlotIndex = ((SubscriptionManager) this.f1443g).getActiveSubscriptionInfoForSimSlotIndex(i3);
            int defaultDataSlotId = miui.telephony.SubscriptionManager.getDefault().getDefaultDataSlotId();
            if (activeSubscriptionInfoForSimSlotIndex != null) {
                sb.append(defaultDataSlotId == i3 ? "1" : "2");
            } else {
                sb.append("0");
            }
            string = sb.toString();
        }
        String str4 = String.format("<a><b>%s<c>%s<d>%s<e>%s<f>%s<g>%s</a>", strC, imeiForSlot, str3, strB, strA, string);
        byte[] bArr = {4, 3, (byte) str4.length(), 0};
        byte[] bytes = str4.getBytes();
        byte[] bArr2 = new byte[bytes.length + 4];
        System.arraycopy(bArr, 0, bArr2, 0, 4);
        System.arraycopy(bytes, 0, bArr2, 4, bytes.length);
        if (PhoneDebug.VDBG || Build.IS_CT_CUSTOMIZATION_TEST) {
            Log.d("AutoReg_SmsWork", "[" + i3 + "] full msg=" + new String(bArr2));
        }
        String str5 = new String(bArr2);
        int iP = j1.b.l(context).p(i3);
        if (iP != -1) {
            new i1.m(context, i3, iP, false).a(i3, str5);
        }
        Object obj = this.f1439c;
        i1.b bVar = (i1.b) obj;
        bVar.c(i3, "miui.intent.action.ACTION_SMS_AUTO_REGISTRATION_IMSSEAULTCHECK");
        bVar.d("miui.intent.action.ACTION_SMS_AUTO_REGISTRATION_IMSSEAULTCHECK");
        ((i1.b) obj).p("miui.intent.action.ACTION_SMS_AUTO_REGISTRATION_IMSSEAULTCHECK", i3, 600000L);
    }

    /* JADX WARN: Code duplicated, block: B:43:0x0110 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:44:0x0112  */
    /* JADX WARN: Code duplicated, block: B:45:0x0116  */
    /* JADX WARN: Code duplicated, block: B:51:0x0120  */
    /* JADX WARN: Code duplicated, block: B:53:0x0124 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:54:0x0126  */
    /* JADX WARN: Code duplicated, block: B:56:0x013b  */
    /* JADX WARN: Code duplicated, block: B:58:0x013f  */
    /* JADX WARN: Code duplicated, block: B:66:0x0174  */
    /* JADX WARN: Code duplicated, block: B:73:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:54:0x0126, please report this as an issue */
    public final void t(int i3, String str) {
        boolean zG;
        boolean z2;
        boolean z3;
        boolean z4;
        Log.d("AutoReg_SmsWork", "[" + i3 + "] tryToRegister reason=" + str);
        if (Build.IS_TABLET) {
            return;
        }
        Context context = (Context) this.f1440d;
        if (j1.c.h(context)) {
            return;
        }
        if (!PreferenceManager.getDefaultSharedPreferences(context).getBoolean(l1.c.m0(i3, "state_sms_cap"), false)) {
            Log.d("AutoReg_SmsWork", "[" + i3 + "] SMS capability not yet available, skip");
            return;
        }
        String strM = m(i3);
        if (!TextUtils.isEmpty(strM)) {
            Log.d("AutoReg_SmsWork", "[" + i3 + "] registration in progress " + strM);
            return;
        }
        boolean zC = f2.e.c("persist.radio.game.status");
        boolean zF = f(i3);
        if (!j1.c.f(i3) || zF) {
            ((SharedPreferences) this.f1441e).edit().putBoolean(l1.c.m0(i3, "second_slot_trigger"), false).apply();
            return;
        }
        boolean zG2 = j1.c.g(context, (SharedPreferences) this.f1441e, "lte_imsi", i3);
        boolean z5 = ((SharedPreferences) this.f1441e).getBoolean(l1.c.m0(i3, "second_slot_trigger"), false);
        if (!zG2) {
            if (z5) {
                zG = true;
            } else if (e(i3)) {
                int i4 = i3 == 1 ? 0 : 1;
                zG = g(i3, i4);
                if (zG) {
                    ((SharedPreferences) this.f1441e).edit().putBoolean(l1.c.m0(i3, "second_slot_trigger"), true).apply();
                    n(i3, i4);
                }
            }
            z2 = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(l1.c.m0(i3, "state_sms_dds_changed"), false);
            z3 = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(l1.c.m0(i3, "state_sms_period_trigger"), false);
            if (zG2) {
                if (!z3 && !zG) {
                    z4 = false;
                }
                if (z2) {
                    s(i3);
                    return;
                }
                if (!z4) {
                    Log.d("AutoReg_SmsWork", "[" + i3 + "] no trigger, skip");
                    return;
                }
                if (!zC && h(i3)) {
                    if (PreferenceManager.getDefaultSharedPreferences(context).getBoolean(l1.c.m0(i3, "state_sms_blocked_by_game"), false)) {
                        return;
                    }
                    l1.c.W0(context, i3, true);
                    ((i1.b) this.f1439c).p("com.xiaomi.registration.action.AUTOREG_GAME_RETRY_SMS_FIRE", i3, 300000L);
                    return;
                }
                s(i3);
                if (PreferenceManager.getDefaultSharedPreferences(context).getBoolean(l1.c.m0(i3, "state_sms_blocked_by_game"), false) || !h(i3)) {
                }
                l1.c.W0(context, i3, false);
                return;
            }
            if (z2) {
                l1.c.X0(context, i3, false);
            }
            z4 = true;
            if (z2) {
                s(i3);
                return;
            }
            if (!z4) {
                Log.d("AutoReg_SmsWork", "[" + i3 + "] no trigger, skip");
                return;
            }
            if (!zC) {
            }
            s(i3);
            if (PreferenceManager.getDefaultSharedPreferences(context).getBoolean(l1.c.m0(i3, "state_sms_blocked_by_game"), false)) {
            }
        }
        if (z5) {
            ((SharedPreferences) this.f1441e).edit().putBoolean(l1.c.m0(i3, "second_slot_trigger"), false).apply();
        }
        if (e(i3)) {
            n(i3, i3 == 1 ? 0 : 1);
        }
        zG = false;
        z2 = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(l1.c.m0(i3, "state_sms_dds_changed"), false);
        z3 = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(l1.c.m0(i3, "state_sms_period_trigger"), false);
        if (zG2) {
            if (!z3) {
                z4 = false;
            }
            if (z2) {
                s(i3);
                return;
            }
            if (!z4) {
                Log.d("AutoReg_SmsWork", "[" + i3 + "] no trigger, skip");
                return;
            }
            if (!zC) {
            }
            s(i3);
            if (PreferenceManager.getDefaultSharedPreferences(context).getBoolean(l1.c.m0(i3, "state_sms_blocked_by_game"), false)) {
            }
        }
        if (z2) {
            l1.c.X0(context, i3, false);
        }
        z4 = true;
        if (z2) {
            s(i3);
            return;
        }
        if (!z4) {
            Log.d("AutoReg_SmsWork", "[" + i3 + "] no trigger, skip");
            return;
        }
        if (!zC) {
        }
        s(i3);
        if (PreferenceManager.getDefaultSharedPreferences(context).getBoolean(l1.c.m0(i3, "state_sms_blocked_by_game"), false)) {
        }
    }

    public final String toString() {
        switch (this.a) {
            case 1:
                StringBuilder sb = new StringBuilder();
                sb.append("FontRequest {mProviderAuthority: " + ((String) this.f1439c) + ", mProviderPackage: " + ((String) this.f1440d) + ", mQuery: " + ((String) this.f1441e) + ", mCertificates:");
                for (int i3 = 0; i3 < ((List) this.f1442f).size(); i3++) {
                    sb.append(" [");
                    List list = (List) ((List) this.f1442f).get(i3);
                    for (int i4 = 0; i4 < list.size(); i4++) {
                        sb.append(" \"");
                        sb.append(Base64.encodeToString((byte[]) list.get(i4), 0));
                        sb.append("\"");
                    }
                    sb.append(" ]");
                }
                sb.append("}");
                sb.append("mCertificatesArray: " + this.f1438b);
                return sb.toString();
            default:
                return super.toString();
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
    public p(i1.b bVar) {
        this.a = 3;
        this.f1439c = bVar;
        this.f1440d = bVar.f1697b;
        this.f1441e = (SharedPreferences) bVar.a;
        TelephonyManager telephonyManager = bVar.f1698c;
        this.f1442f = telephonyManager;
        this.f1443g = (SubscriptionManager) bVar.f1699d;
        this.f1438b = telephonyManager.getPhoneCount();
    }

    public p(String str, String str2, String str3, List list) {
        this.a = 1;
        str.getClass();
        this.f1439c = str;
        str2.getClass();
        this.f1440d = str2;
        this.f1441e = str3;
        list.getClass();
        this.f1442f = list;
        this.f1438b = 0;
        this.f1443g = str + "-" + str2 + "-" + str3;
    }
}
