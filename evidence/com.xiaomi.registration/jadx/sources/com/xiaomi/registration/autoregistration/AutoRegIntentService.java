package com.xiaomi.registration.autoregistration;

import android.app.AlarmManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import f2.e;
import g.p;
import i1.a;
import i1.b;
import i1.c;
import java.text.SimpleDateFormat;
import java.util.Locale;
import l.d;
import miui.telephony.SubscriptionManager;

/* JADX INFO: loaded from: classes.dex */
public class AutoRegIntentService extends Service {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static volatile boolean f843c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static SmsFilterReceiver f844d;
    public volatile Looper a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile c f845b;

    /* JADX WARN: Code duplicated, block: B:270:0x093d  */
    /* JADX WARN: Code duplicated, block: B:332:0x0ab6  */
    public static void a(AutoRegIntentService autoRegIntentService, Intent intent) {
        boolean z2;
        String str;
        TelephonyManager telephonyManager;
        boolean z3;
        String str2;
        boolean z4;
        String str3;
        String str4;
        String str5;
        autoRegIntentService.getClass();
        int intExtra = intent.getIntExtra("workType", -1);
        int intExtra2 = intent.getIntExtra("slotId", -1);
        Log.d("AutoReg_IntentSvc", "onHandleIntent workType=" + intExtra + " slotId=" + intExtra2);
        Context applicationContext = autoRegIntentService.getApplicationContext();
        b bVar = new b(applicationContext);
        int i3 = j1.c.a;
        if (e.c("ro.radio.noril") && TextUtils.isEmpty(e.g("ro.ril.oem.ctei", ""))) {
            Log.d("AutoReg_Work", "AutoReg not supported, skip workType=" + intExtra);
            return;
        }
        bVar.e();
        if ((e.c("ro.radio.noril") && !TextUtils.isEmpty(e.g("ro.ril.oem.ctei", ""))) || j1.c.e()) {
            d dVar = new d(bVar);
            if (intExtra != 1 && intExtra != 2 && intExtra != 8 && intExtra != 12 && intExtra != 22) {
                if (intExtra != 60) {
                    Log.d("Ctei_Work", "ignore workType=" + intExtra);
                    return;
                }
                return;
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) dVar.f1851d;
            NetworkInfo activeNetworkInfo = connectivityManager == null ? null : connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                Log.d("Ctei_Work", "ctei tryToRegister");
                new a(dVar, (Context) dVar.f1849b);
                return;
            } else {
                Log.d("Ctei_Work", "no network, skip");
                bVar.d("miui.intent.action.CTEI_AUTO_REGISTRATION");
                bVar.d("miui.intent.action.CTEI_AUTO_REGISTRATION_RETRY");
                return;
            }
        }
        if (intExtra == 13) {
            b bVar2 = new b(bVar, 1);
            if (intExtra2 >= 0) {
                bVar2.t(intExtra2);
            }
            int phoneCount = bVar2.f1698c.getPhoneCount();
            for (int i4 = 0; i4 < phoneCount; i4++) {
                bVar2.t(i4);
            }
            return;
        }
        if (intExtra == 50) {
            new b(bVar, 0).f();
            return;
        }
        if (intExtra == 20) {
            b bVar3 = new b(bVar, 0);
            String string = ((SharedPreferences) ((b) bVar3.a).a).getString("register_regtime", "");
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
                Context context = bVar3.f1697b;
                PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("state_period_trigger", true).apply();
                l1.c.T0(context, false);
                bVar3.u("period");
                return;
            }
            return;
        }
        if (intExtra == 21) {
            p pVar = new p(bVar);
            if (intExtra2 >= 0) {
                pVar.b(intExtra2);
                return;
            }
            for (int i5 = 0; i5 < pVar.f1438b; i5++) {
                pVar.b(i5);
            }
            return;
        }
        if (intExtra == 40) {
            p pVar2 = new p(bVar);
            if (intExtra2 >= 0) {
                pVar2.j(intExtra2);
                return;
            }
            for (int i6 = 0; i6 < pVar2.f1438b; i6++) {
                pVar2.j(i6);
            }
            return;
        }
        if (intExtra == 41) {
            b bVar4 = new b(bVar, 1);
            if (intExtra2 >= 0) {
                bVar4.o(intExtra2);
                return;
            }
            int phoneCount2 = bVar4.f1698c.getPhoneCount();
            for (int i7 = 0; i7 < phoneCount2; i7++) {
                bVar4.o(i7);
            }
            return;
        }
        Context context2 = bVar.f1697b;
        TelephonyManager telephonyManager2 = bVar.f1698c;
        Object obj = bVar.a;
        TelephonyManager telephonyManager3 = telephonyManager2;
        String str6 = "second_slot_trigger";
        switch (intExtra) {
            case 1:
                b bVar5 = new b(bVar, 0);
                Object obj2 = bVar5.a;
                try {
                    ((AlarmManager) ((b) obj2).f1700e).setRepeating(0, System.currentTimeMillis() + 43200000, 43200000L, ((b) obj2).a("com.qualcomm.qti.action.AUTO_REGISTRATION_PERIOD_TRIGGER"));
                    ((AlarmManager) ((b) obj2).f1700e).setRepeating(0, System.currentTimeMillis() + 43200000, 43200000L, ((b) obj2).a("miui.intent.action.SMS_AUTO_REGISTRATION_PERIOD_TRIGGER"));
                } catch (Exception e3) {
                    Log.e("AutoReg_DataWork", "ensurePeriodAlarms", e3);
                }
                Context context3 = bVar5.f1697b;
                if (PreferenceManager.getDefaultSharedPreferences(context3).getBoolean("state_power_on", true)) {
                    if (!l1.c.k0(context3)) {
                        b bVar6 = (b) obj2;
                        boolean z5 = bVar6.b("com.xiaomi.registration.action.AUTOREG_DELAYED_BOOT_FIRE") != null;
                        if (PreferenceManager.getDefaultSharedPreferences(context3).getBoolean("state_power_on_alarm_pending", false) && z5) {
                            str = "boot delay alarm already pending";
                        } else {
                            l1.c.U0(context3, true);
                            bVar6.q("com.xiaomi.registration.action.AUTOREG_DELAYED_BOOT_FIRE", 30000L);
                            str = "scheduled 30s boot delay alarm";
                        }
                        Log.d("AutoReg_DataWork", str);
                    } else {
                        Log.d("AutoReg_DataWork", "power-on barrier stale at boot bootstrap, self-clearing");
                        l1.c.T0(context3, false);
                        l1.c.U0(context3, false);
                        ((b) obj2).d("com.xiaomi.registration.action.AUTOREG_DELAYED_BOOT_FIRE");
                    }
                }
                bVar5.u("boot");
                break;
            case 2:
                b bVar7 = new b(bVar, 0);
                Context context4 = bVar7.f1697b;
                l1.c.T0(context4, false);
                l1.c.U0(context4, false);
                bVar7.u("delayed_boot");
                break;
            case 3:
                if (!j1.c.a(context2).equals(((SharedPreferences) obj).getString("register_sw_version", ""))) {
                    l1.c.Y0(context2, true);
                }
                String string2 = ((SharedPreferences) obj).getString("build_fingerprint", null);
                String str7 = Build.FINGERPRINT;
                if (!str7.equals(string2)) {
                    SharedPreferences.Editor editorEdit = ((SharedPreferences) obj).edit();
                    editorEdit.putString("build_fingerprint", str7);
                    editorEdit.putString("sim_iccid", "");
                    int phoneCount3 = telephonyManager3.getPhoneCount();
                    for (int i8 = 0; i8 < phoneCount3; i8++) {
                        editorEdit.putString("lte_imsi" + i8, "");
                        editorEdit.putString("satellite_imsi" + i8, "");
                    }
                    editorEdit.apply();
                }
                int phoneCount4 = telephonyManager3.getPhoneCount();
                int i9 = 0;
                while (true) {
                    telephonyManager = telephonyManager3;
                    if (i9 >= phoneCount4) {
                        z3 = false;
                    } else if (telephonyManager.getSimState(i9) == 5) {
                        z3 = true;
                    } else {
                        i9++;
                        telephonyManager3 = telephonyManager;
                    }
                }
                if (!z3) {
                    str2 = "scheduleDdsDelay: SIM not ready, skip";
                } else if (SubscriptionManager.getDefault().getDefaultDataSlotId() == ((SharedPreferences) obj).getInt("register_dds_slot", 0)) {
                    str2 = "scheduleDdsDelay: already registered for this DDS, skip";
                } else {
                    l1.c.Q0(context2, true);
                    ((SharedPreferences) bVar.a).edit().putInt("register_request_count", 0).apply();
                    bVar.d("com.qualcomm.qti.action.AUTO_REGISTRATION_RETRY");
                    bVar.d("com.xiaomi.registration.action.AUTOREG_DELAYED_DDS_FIRE");
                    l1.c.P0(context2, true);
                    bVar.q("com.xiaomi.registration.action.AUTOREG_DELAYED_DDS_FIRE", 60000L);
                    str2 = "scheduled 60s DDS delay alarm";
                }
                Log.d("AutoReg_DataWork", str2);
                SharedPreferences sharedPreferences = (SharedPreferences) obj;
                int phoneCount5 = telephonyManager.getPhoneCount();
                int i10 = 0;
                while (i10 < phoneCount5) {
                    int i11 = 0;
                    while (true) {
                        if (i11 >= phoneCount5) {
                            z4 = false;
                        } else if (telephonyManager.getSimState(i11) == 5) {
                            z4 = true;
                        } else {
                            i11++;
                        }
                    }
                    if (z4) {
                        str3 = str6;
                        if (!sharedPreferences.getBoolean(l1.c.m0(i10, str3), false) && !j1.c.g(applicationContext, sharedPreferences, "lte_imsi", i10)) {
                            l1.c.X0(applicationContext, i10, true);
                        }
                    } else {
                        str3 = str6;
                    }
                    i10++;
                    str6 = str3;
                }
                break;
            case 4:
                b bVar8 = new b(bVar, 0);
                l1.c.P0(bVar8.f1697b, false);
                bVar8.u("delayed_dds");
                p pVar3 = new p(bVar);
                for (int i12 = 0; i12 < pVar3.f1438b; i12++) {
                    if (PreferenceManager.getDefaultSharedPreferences((Context) pVar3.f1440d).getBoolean(l1.c.m0(i12, "state_sms_dds_changed"), false)) {
                        ((SharedPreferences) pVar3.f1441e).edit().putInt(l1.c.m0(i12, "register_request_ims_count"), 0).apply();
                        pVar3.t(i12, "dds");
                    }
                }
                break;
            case 5:
                b bVar9 = new b(bVar, 0);
                Context context5 = bVar9.f1697b;
                int i13 = Settings.Global.getInt(context5.getContentResolver(), "fiveg_user_enable", 1);
                int i14 = Settings.Global.getInt(context5.getContentResolver(), "dual_nr_enabled", 1);
                int i15 = PreferenceManager.getDefaultSharedPreferences(context5).getInt("state_5g_master_last", 1);
                int i16 = PreferenceManager.getDefaultSharedPreferences(context5).getInt("state_5g_vice_last", 1);
                Log.d("AutoReg_DataWork", "5G: master " + i15 + "->" + i13 + " vice " + i16 + "->" + i14);
                if (i13 == i15 && i14 == i16) {
                    Log.d("AutoReg_DataWork", "5G unchanged on re-check; skip");
                } else {
                    PreferenceManager.getDefaultSharedPreferences(context5).edit().putInt("state_5g_master_last", i13).putInt("state_5g_vice_last", i14).apply();
                    PreferenceManager.getDefaultSharedPreferences(context5).edit().putBoolean("state_5g_changed", true).apply();
                    ((SharedPreferences) ((b) bVar9.a).a).edit().putInt("register_request_count", 0).apply();
                    ((b) bVar9.a).d("com.qualcomm.qti.action.AUTO_REGISTRATION_RETRY");
                    bVar9.u("fiveg_switch");
                }
                break;
            case 6:
                if (intExtra2 >= 0 && intent.hasExtra("smsCap")) {
                    boolean booleanExtra = intent.getBooleanExtra("smsCap", false);
                    PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putBoolean(l1.c.m0(intExtra2, "state_sms_cap"), booleanExtra).apply();
                    Log.d("AutoReg_Work", "IMS_CAP slot=" + intExtra2 + " smsCap=" + booleanExtra);
                }
                p pVar4 = new p(bVar);
                if (intExtra2 >= 0) {
                    pVar4.t(intExtra2, "ims_cap");
                    break;
                }
                break;
            case 7:
                new b(bVar, 1).t(intExtra2);
                break;
            case 8:
                new b(bVar, 0).u("connectivity");
                break;
            case 9:
                b bVar10 = new b(bVar, 0);
                if (!PreferenceManager.getDefaultSharedPreferences(bVar10.f1697b).getBoolean("state_5g_changed", false)) {
                    bVar10.u("service_state");
                    break;
                }
                break;
            case 10:
                new b(bVar, 0).u("retry");
                break;
            case 11:
                p pVar5 = new p(bVar);
                if (intExtra2 < 0) {
                    for (int i17 = 0; i17 < pVar5.f1438b; i17++) {
                        pVar5.t(i17, "retry");
                    }
                } else {
                    pVar5.t(intExtra2, "retry");
                }
                break;
            default:
                String str8 = "AutoReg_SmsWork";
                switch (intExtra) {
                    case 30:
                        b bVar11 = new b(bVar, 0);
                        i1.e eVar = (i1.e) intent.getSerializableExtra("REG_ENTITY");
                        Log.d("AutoReg_DataWork", "persistResult entity=" + eVar);
                        if (eVar != null) {
                            boolean z6 = eVar.f1704d;
                            Object obj3 = bVar11.a;
                            Context context6 = bVar11.f1697b;
                            if (z6) {
                                b bVar12 = (b) obj3;
                                ((SharedPreferences) bVar12.a).edit().putString("sim_iccid", bVar11.j()).putInt("register_dds_slot", SubscriptionManager.getDefault().getDefaultDataSlotId()).putString("register_sw_version", j1.c.a(bVar11.f1697b)).putString("register_regtime", j1.c.d()).apply();
                                l1.c.Q0(context6, false);
                                PreferenceManager.getDefaultSharedPreferences(context6).edit().putBoolean("state_5g_changed", false).apply();
                                l1.c.Y0(context6, false);
                                PreferenceManager.getDefaultSharedPreferences(context6).edit().putBoolean("state_period_trigger", false).apply();
                                Log.d("AutoReg_DataWork", "auto registration success");
                                bVar12.d("com.qualcomm.qti.action.AUTO_REGISTRATION_PERIOD_TRIGGER");
                                try {
                                    ((AlarmManager) ((b) obj3).f1700e).setRepeating(0, System.currentTimeMillis() + 43200000, 43200000L, ((b) obj3).a("com.qualcomm.qti.action.AUTO_REGISTRATION_PERIOD_TRIGGER"));
                                } catch (Exception e4) {
                                    Log.e("AutoReg_DataWork", "reStartPeriodRegTimer", e4);
                                }
                                break;
                            } else if (!eVar.a) {
                                b bVar13 = (b) obj3;
                                int i18 = ((SharedPreferences) bVar13.a).getInt("register_request_count", 0) + 1;
                                ((SharedPreferences) bVar13.a).edit().putInt("register_request_count", i18).apply();
                                if (i18 >= 10) {
                                    Log.d("AutoReg_DataWork", "data registration achieved max retry");
                                } else {
                                    ((b) bVar11.a).d("com.qualcomm.qti.action.AUTO_REGISTRATION_RETRY");
                                    Log.d("AutoReg_DataWork", "schedule data retry #" + i18);
                                    bVar13.q("com.qualcomm.qti.action.AUTO_REGISTRATION_RETRY", 3600000L);
                                }
                            }
                            l1.c.V0(0L, context6, false);
                            break;
                        }
                        break;
                    case 31:
                        p pVar6 = new p(bVar);
                        bVar.c(intExtra2, "miui.intent.action.ACTION_SMS_AUTO_REGISTRATION_IMSSEAULTCHECK");
                        bVar.d("miui.intent.action.ACTION_SMS_AUTO_REGISTRATION_IMSSEAULTCHECK");
                        if (TextUtils.isEmpty(pVar6.m(intExtra2))) {
                            Log.d("AutoReg_SmsWork", "[" + intExtra2 + "] persistResult: no in-flight registration ID, ignore (likely stale ghost)");
                        } else {
                            Log.d("AutoReg_SmsWork", "[" + intExtra2 + "] persistResult");
                            boolean z7 = intent.getExtras() != null ? intent.getExtras().getBoolean("result", false) : false;
                            Context context7 = (Context) pVar6.f1440d;
                            if (!z7) {
                                l1.c.F(context7, intExtra2);
                                pVar6.o(intExtra2);
                            } else {
                                j1.c.j(context7, (SharedPreferences) pVar6.f1441e, "lte_imsi", intExtra2);
                                ((SharedPreferences) pVar6.f1441e).edit().putString(androidx.activity.result.a.b("sms_auto_register_regtime", intExtra2), j1.c.d()).apply();
                                ((SharedPreferences) pVar6.f1441e).edit().putBoolean(l1.c.m0(intExtra2, str6), false).apply();
                                l1.c.F(context7, intExtra2);
                                l1.c.X0(context7, intExtra2, false);
                                PreferenceManager.getDefaultSharedPreferences(context7).edit().putBoolean(l1.c.m0(intExtra2, "state_sms_period_trigger"), false).apply();
                                ((SharedPreferences) pVar6.f1441e).edit().putInt(l1.c.m0(intExtra2, "register_request_ims_count"), 0).apply();
                                bVar.d("miui.intent.action.SMS_AUTO_REGISTRATION_PERIOD_TRIGGER");
                                try {
                                    ((AlarmManager) bVar.f1700e).setRepeating(0, System.currentTimeMillis() + 43200000, 43200000L, bVar.a("miui.intent.action.SMS_AUTO_REGISTRATION_PERIOD_TRIGGER"));
                                } catch (Exception e5) {
                                    Log.e("AutoReg_SmsWork", "resetPeriodRegTimer", e5);
                                    return;
                                }
                            }
                        }
                        break;
                    case 32:
                        b bVar14 = new b(bVar, 1);
                        b bVar15 = (b) bVar14.a;
                        bVar15.c(intExtra2, "miui.intent.action.ACTION_SMS_AUTO_REGISTRATION_SEAULTCHECK");
                        bVar15.d("miui.intent.action.ACTION_SMS_AUTO_REGISTRATION_SEAULTCHECK");
                        boolean z8 = intent.getExtras() != null ? intent.getExtras().getBoolean("result", false) : false;
                        if (z8) {
                            j1.c.j(bVar14.f1697b, (SharedPreferences) bVar14.f1699d, "satellite_imsi", intExtra2);
                        } else {
                            bVar14.r(intExtra2);
                        }
                        Log.d("AutoReg_SatWork", "[" + intExtra2 + "] satellite persistResult=" + z8);
                        break;
                    default:
                        switch (intExtra) {
                            case 60:
                                b bVar16 = new b(bVar, 0);
                                String stringExtra = intent.getStringExtra("ss");
                                if ("READY".equals(stringExtra) || "LOADED".equals(stringExtra) || "IMSI".equals(stringExtra)) {
                                    Context context8 = bVar16.f1697b;
                                    if (!PreferenceManager.getDefaultSharedPreferences(context8).getBoolean("state_power_on", true) && !PreferenceManager.getDefaultSharedPreferences(context8).getBoolean("state_power_on_alarm_pending", false)) {
                                        Log.d("AutoReg_DataWork", "hot SIM insert; rearm 30s boot-style delay");
                                        l1.c.T0(context8, true);
                                    }
                                    bVar16.u("sim_state");
                                }
                                p pVar7 = new p(bVar);
                                String stringExtra2 = intent.getStringExtra("ss");
                                int i19 = intent.getExtras() != null ? intent.getExtras().getInt("phone", 0) : 0;
                                if ("ABSENT".equals(stringExtra2)) {
                                    l1.c.F((Context) pVar7.f1440d, i19);
                                }
                                int i20 = 0;
                                while (i20 < pVar7.f1438b) {
                                    if (i19 == i20 || !pVar7.e(i20)) {
                                        str5 = str8;
                                    } else if (!("LOADED".equals(stringExtra2) || "READY".equals(stringExtra2) || "IMSI".equals(stringExtra2))) {
                                        if ("ABSENT".equals(stringExtra2) && j1.c.f(i20) && !pVar7.f(i20)) {
                                            boolean zIsEmpty = TextUtils.isEmpty(((SharedPreferences) pVar7.f1441e).getString(l1.c.m0(i20, "second_slot_imsi"), ""));
                                            pVar7.n(i20, i19);
                                            if (!zIsEmpty) {
                                                ((SharedPreferences) pVar7.f1441e).edit().putBoolean(l1.c.m0(i20, str6), true).apply();
                                                ((SharedPreferences) pVar7.f1441e).edit().putInt(l1.c.m0(i20, "register_request_ims_count"), 0).apply();
                                                str4 = "second_slot_removed";
                                                str5 = str8;
                                                Log.d(str5, "[" + i20 + "] " + str4 + ", schedule 30s delay before register");
                                                ((b) pVar7.f1439c).p("com.xiaomi.registration.action.AUTOREG_DELAYED_SECONDSLOT_FIRE", i20, 30000L);
                                            }
                                        }
                                        str5 = str8;
                                    } else if (j1.c.f(i20) && !pVar7.f(i20) && pVar7.g(i20, i19)) {
                                        pVar7.n(i20, i19);
                                        ((SharedPreferences) pVar7.f1441e).edit().putBoolean(l1.c.m0(i20, str6), true).apply();
                                        ((SharedPreferences) pVar7.f1441e).edit().putInt(l1.c.m0(i20, "register_request_ims_count"), 0).apply();
                                        str4 = "second_slot_loaded";
                                        str5 = str8;
                                        Log.d(str5, "[" + i20 + "] " + str4 + ", schedule 30s delay before register");
                                        ((b) pVar7.f1439c).p("com.xiaomi.registration.action.AUTOREG_DELAYED_SECONDSLOT_FIRE", i20, 30000L);
                                    } else {
                                        str5 = str8;
                                    }
                                    i20++;
                                    pVar7 = pVar7;
                                    str8 = str5;
                                    i19 = i19;
                                }
                                break;
                            case 61:
                                b bVar17 = new b(bVar, 0);
                                ((SharedPreferences) ((b) bVar17.a).a).edit().putString("autosms_provision", "true").apply();
                                bVar17.u("provision");
                                break;
                            case 62:
                                SharedPreferences sharedPreferences2 = (SharedPreferences) obj;
                                e.g("ro.product.device", "");
                                if (intent.getExtras() != null) {
                                    int i21 = intent.getExtras().getInt("phone_id", 0);
                                    if (intent.getExtras().getBoolean("is_enable", false)) {
                                        sharedPreferences2.edit().putInt(l1.c.m0(i21, "register_request_satellite_count"), 0).apply();
                                    }
                                    break;
                                }
                                break;
                            case 63:
                                if (!j1.c.a(context2).equals(((SharedPreferences) obj).getString("register_sw_version", ""))) {
                                    l1.c.Y0(context2, true);
                                }
                                String string3 = ((SharedPreferences) obj).getString("build_fingerprint", null);
                                String str9 = Build.FINGERPRINT;
                                if (!str9.equals(string3)) {
                                    SharedPreferences.Editor editorEdit2 = ((SharedPreferences) obj).edit();
                                    editorEdit2.putString("build_fingerprint", str9);
                                    editorEdit2.putString("sim_iccid", "");
                                    int phoneCount6 = telephonyManager3.getPhoneCount();
                                    for (int i22 = 0; i22 < phoneCount6; i22++) {
                                        editorEdit2.putString("lte_imsi" + i22, "");
                                        editorEdit2.putString("satellite_imsi" + i22, "");
                                    }
                                    editorEdit2.apply();
                                }
                                break;
                            case 64:
                                p pVar8 = new p(bVar);
                                if (intExtra2 >= 0) {
                                    if (e.c("persist.radio.game.status") && pVar8.h(intExtra2)) {
                                        bVar.p("com.xiaomi.registration.action.AUTOREG_GAME_RETRY_SMS_FIRE", intExtra2, 300000L);
                                    } else {
                                        l1.c.W0((Context) pVar8.f1440d, intExtra2, false);
                                        pVar8.t(intExtra2, "game_retry");
                                    }
                                    break;
                                }
                                break;
                            case 65:
                                p pVar9 = new p(bVar);
                                if (intExtra2 >= 0) {
                                    Log.d("AutoReg_SmsWork", "[" + intExtra2 + "] onSendFailed, schedule 10-min retry");
                                    bVar.c(intExtra2, "miui.intent.action.ACTION_SMS_AUTO_REGISTRATION_IMSSEAULTCHECK");
                                    bVar.d("miui.intent.action.ACTION_SMS_AUTO_REGISTRATION_IMSSEAULTCHECK");
                                    pVar9.o(intExtra2);
                                    break;
                                }
                                break;
                            case 66:
                                b bVar18 = new b(bVar, 1);
                                if (intExtra2 >= 0) {
                                    Log.d("AutoReg_SatWork", "[" + intExtra2 + "] satellite onSendFailed, schedule 10-min retry");
                                    b bVar19 = (b) bVar18.a;
                                    bVar19.c(intExtra2, "miui.intent.action.ACTION_SMS_AUTO_REGISTRATION_SEAULTCHECK");
                                    bVar19.d("miui.intent.action.ACTION_SMS_AUTO_REGISTRATION_SEAULTCHECK");
                                    bVar18.r(intExtra2);
                                    break;
                                }
                                break;
                            case 67:
                                p pVar10 = new p(bVar);
                                if (intExtra2 >= 0) {
                                    Log.d("AutoReg_SmsWork", "[" + intExtra2 + "] onDelayedSecondSlot");
                                    pVar10.t(intExtra2, "second_slot_delayed");
                                    break;
                                }
                                break;
                            default:
                                Log.w("AutoReg_Work", "Unknown workType=" + intExtra);
                                break;
                        }
                        break;
                }
                break;
        }
    }

    public static void b(Context context, int i3) {
        c(context, i3, -1, null);
    }

    public static void c(Context context, int i3, int i4, Intent intent) {
        Intent intentPutExtra = new Intent(context, (Class<?>) AutoRegIntentService.class).putExtra("workType", i3).putExtra("slotId", i4);
        if (intent != null) {
            if (intent.getAction() != null) {
                intentPutExtra.putExtra("origAction", intent.getAction());
            }
            if (intent.getExtras() != null) {
                intentPutExtra.putExtras(intent.getExtras());
            }
        }
        try {
            context.startService(intentPutExtra);
        } catch (Exception e3) {
            Log.e("AutoReg_IntentSvc", "startService failed for workType=" + i3, e3);
        }
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        Log.d("AutoReg_IntentSvc", "onCreate");
        HandlerThread handlerThread = new HandlerThread("AutoRegIntentService");
        handlerThread.start();
        this.a = handlerThread.getLooper();
        this.f845b = new c(this, this.a, 0);
        Context applicationContext = getApplicationContext();
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        if (!defaultSharedPreferences.getBoolean("state_first_use_init_done", false)) {
            int i3 = Settings.Global.getInt(applicationContext.getContentResolver(), "fiveg_user_enable", 1);
            int i4 = Settings.Global.getInt(applicationContext.getContentResolver(), "dual_nr_enabled", 1);
            defaultSharedPreferences.edit().putInt("state_5g_master_last", i3).putInt("state_5g_vice_last", i4).putBoolean("state_first_use_init_done", true).apply();
            Log.d("AutoReg_StateStore", "firstUseInit: master=" + i3 + " vice=" + i4);
        }
        synchronized (AutoRegIntentService.class) {
            if (!f843c) {
                f844d = new SmsFilterReceiver();
                applicationContext.registerReceiver(f844d, new IntentFilter("com.xiaomi.autoreg.receivesms"), 2);
                f843c = true;
                Log.d("AutoReg_IntentSvc", "SmsFilterReceiver registered");
            }
        }
    }

    @Override // android.app.Service
    public final void onDestroy() {
        Log.d("AutoReg_IntentSvc", "onDestroy");
        if (this.a != null) {
            this.a.quit();
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i3, int i4) {
        if (intent == null) {
            stopSelf(i4);
            return 2;
        }
        Log.d("AutoReg_IntentSvc", "onStartCommand workType=" + intent.getIntExtra("workType", -1) + " slotId=" + intent.getIntExtra("slotId", -1));
        Message messageObtainMessage = this.f845b.obtainMessage();
        messageObtainMessage.arg1 = i4;
        messageObtainMessage.obj = intent;
        this.f845b.sendMessage(messageObtainMessage);
        return 2;
    }
}
