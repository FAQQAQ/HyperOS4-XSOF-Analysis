package i1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.telephony.ServiceState;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.registration.autoregistration.RegistrationJobService;
import java.util.Iterator;
import java.util.List;
import miui.os.Build;
import miui.telephony.PhoneDebug;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public final Object a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f1697b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final TelephonyManager f1698c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f1699d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f1700e;

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public b(Context context) {
        this.f1697b = context;
        this.a = PreferenceManager.getDefaultSharedPreferences(context);
        this.f1700e = (AlarmManager) context.getSystemService("alarm");
        this.f1698c = (TelephonyManager) context.getSystemService("phone");
        this.f1699d = (SubscriptionManager) context.getSystemService("telephony_subscription_service");
    }

    public static int g(Context context) {
        List<SubscriptionInfo> activeSubscriptionInfoList;
        SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService("telephony_subscription_service");
        if (subscriptionManager == null || (activeSubscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList()) == null) {
            return -1;
        }
        for (SubscriptionInfo subscriptionInfo : activeSubscriptionInfoList) {
            String iccId = subscriptionInfo.getIccId();
            if (iccId != null) {
                for (String str : "898603,898611,898612".split(",")) {
                    if (iccId.startsWith(str)) {
                        return subscriptionInfo.getSimSlotIndex();
                    }
                }
            }
        }
        return -1;
    }

    public final PendingIntent a(String str) {
        Intent intent = new Intent(str);
        Context context = this.f1697b;
        intent.setPackage(context.getPackageName());
        intent.addFlags(268435456);
        return PendingIntent.getBroadcast(context, 0, intent, 67108864);
    }

    public final PendingIntent b(String str) {
        Intent intent = new Intent(str);
        Context context = this.f1697b;
        intent.setPackage(context.getPackageName());
        intent.addFlags(268435456);
        return PendingIntent.getBroadcast(context, 0, intent, 603979776);
    }

    public final void c(int i3, String str) {
        Intent intent = new Intent(str);
        Context context = this.f1697b;
        intent.setPackage(context.getPackageName());
        intent.addFlags(268435456);
        intent.putExtra("slotId", i3);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, i3, intent, 603979776);
        if (broadcast != null) {
            ((AlarmManager) this.f1700e).cancel(broadcast);
        }
    }

    public final void d(String str) {
        PendingIntent pendingIntentB = b(str);
        if (pendingIntentB != null) {
            ((AlarmManager) this.f1700e).cancel(pendingIntentB);
        }
    }

    public final void e() {
        Context context = this.f1697b;
        if (PreferenceManager.getDefaultSharedPreferences(context).getBoolean("state_registering", false)) {
            long j3 = PreferenceManager.getDefaultSharedPreferences(context).getLong("state_registering_ts", 0L);
            if (j3 <= 0) {
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis() - j3;
            if (jCurrentTimeMillis < 300000) {
                return;
            }
            int i3 = PreferenceManager.getDefaultSharedPreferences(context).getInt("state_http_job_id", 0);
            JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
            boolean z2 = (i3 <= 0 || jobScheduler == null || jobScheduler.getPendingJob(i3) == null) ? false : true;
            if (jCurrentTimeMillis >= 600000) {
                Log.w("AutoReg_Work", "state_registering aged " + jCurrentTimeMillis + "ms (hard), force clear stillPending=" + z2);
                if (z2) {
                    jobScheduler.cancel(i3);
                }
            } else {
                if (z2) {
                    return;
                }
                Log.w("AutoReg_Work", "state_registering aged " + jCurrentTimeMillis + "ms (no job), clear");
            }
            l1.c.V0(0L, context, false);
            l1.c.R0(context, 0);
        }
    }

    public final void f() {
        JobScheduler jobScheduler = (JobScheduler) this.f1697b.getSystemService("jobscheduler");
        if (jobScheduler != null && jobScheduler.getPendingJob(1) != null) {
            jobScheduler.cancel(1);
        }
        int iH = h();
        if (iH < 0) {
            iH = SubscriptionManager.getSlotIndex(SubscriptionManager.getDefaultDataSubscriptionId());
        }
        if (n()) {
            s(new e(iH, SubscriptionManager.getSlotIndex(SubscriptionManager.getDefaultDataSubscriptionId()), 0));
        }
    }

    public final int h() {
        List<SubscriptionInfo> activeSubscriptionInfoList = ((SubscriptionManager) this.f1699d).getActiveSubscriptionInfoList();
        if (activeSubscriptionInfoList == null) {
            return -1;
        }
        for (SubscriptionInfo subscriptionInfo : activeSubscriptionInfoList) {
            String iccId = subscriptionInfo.getIccId();
            if (iccId != null) {
                for (String str : "898603,898611,898612".split(",")) {
                    if (iccId.startsWith(str)) {
                        return subscriptionInfo.getSimSlotIndex();
                    }
                }
            }
        }
        return -1;
    }

    public final String i(int i3) {
        Context context = this.f1697b;
        String strC = j1.c.c(context);
        String strG = f2.e.g("ro.vendor.ril.imeitt", "null");
        j1.c.i("imei3: " + strG);
        if (TextUtils.isEmpty(strG)) {
            strG = "000000000000000";
        }
        String imeiForSlot = miui.telephony.TelephonyManager.getDefault().getImeiForSlot(1 - i3);
        String str = String.format("<a1><b1>%s<b2>%s<b3>%s<b4>%s<b5>%s</a1>", strC, strG, TextUtils.isEmpty(imeiForSlot) ? "000000000000000" : imeiForSlot, j1.c.b(context, i3), j1.c.a(context));
        byte[] bArr = {9, 3, (byte) str.length(), 0};
        byte[] bytes = str.getBytes();
        byte[] bArr2 = new byte[bytes.length + 4];
        System.arraycopy(bArr, 0, bArr2, 0, 4);
        System.arraycopy(bytes, 0, bArr2, 4, bytes.length);
        if (PhoneDebug.VDBG || Build.IS_CT_CUSTOMIZATION_TEST) {
            Log.d("AutoReg_SatWork", "[" + i3 + "] full msg=" + new String(bArr2));
        }
        return new String(bArr2);
    }

    public final String j() {
        String str;
        TelephonyManager telephonyManager = this.f1698c;
        int phoneCount = telephonyManager.getPhoneCount();
        if (telephonyManager.getActiveModemCount() > 1) {
            str = null;
            for (int i3 = 0; i3 < phoneCount; i3++) {
                int iP = j1.b.l(this.f1697b).p(i3);
                String simSerialNumber = iP != -1 ? telephonyManager.createForSubscriptionId(iP).getSimSerialNumber() : "";
                if (simSerialNumber == null) {
                    simSerialNumber = " ";
                }
                str = str == null ? simSerialNumber : str + "," + simSerialNumber;
            }
        } else {
            String simSerialNumber2 = telephonyManager.getSimSerialNumber();
            str = simSerialNumber2 != null ? simSerialNumber2 : " ";
        }
        return str == null ? "" : str;
    }

    public final boolean k() {
        TelephonyManager telephonyManager = this.f1698c;
        int phoneCount = telephonyManager.getPhoneCount();
        for (int i3 = 0; i3 < phoneCount; i3++) {
            int iP = j1.b.l(this.f1697b).p(i3);
            if (iP != -1 && telephonyManager.createForSubscriptionId(iP).isNetworkRoaming()) {
                return true;
            }
        }
        return false;
    }

    public final boolean l() {
        List<SubscriptionInfo> activeSubscriptionInfoList = ((SubscriptionManager) this.f1699d).getActiveSubscriptionInfoList();
        if (activeSubscriptionInfoList == null) {
            return false;
        }
        Iterator<SubscriptionInfo> it = activeSubscriptionInfoList.iterator();
        while (it.hasNext()) {
            ServiceState serviceState = this.f1698c.createForSubscriptionId(it.next().getSubscriptionId()).getServiceState();
            if (serviceState != null && serviceState.getState() == 0) {
                return true;
            }
        }
        return false;
    }

    public final boolean m() {
        TelephonyManager telephonyManager = this.f1698c;
        int phoneCount = telephonyManager.getPhoneCount();
        for (int i3 = 0; i3 < phoneCount; i3++) {
            if (telephonyManager.getSimState(i3) == 5) {
                return true;
            }
        }
        return false;
    }

    public final boolean n() {
        String iccId;
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.f1700e).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected() || this.f1698c.getActiveModemCount() <= 1 || activeNetworkInfo.getType() == 1) {
            return true;
        }
        SubscriptionInfo activeSubscriptionInfo = ((SubscriptionManager) this.f1699d).getActiveSubscriptionInfo(SubscriptionManager.getDefaultDataSubscriptionId());
        if (activeSubscriptionInfo == null || (iccId = activeSubscriptionInfo.getIccId()) == null) {
            return false;
        }
        for (String str : "898603,898611,898612".split(",")) {
            if (iccId.startsWith(str)) {
                return true;
            }
        }
        return false;
    }

    public final void o(int i3) {
        Log.d("AutoReg_SatWork", "[" + i3 + "] satellite noResultRetry");
        Object obj = this.f1699d;
        int i4 = ((SharedPreferences) obj).getInt(l1.c.m0(i3, "register_request_satellite_count"), 0) + 1;
        ((SharedPreferences) obj).edit().putInt(l1.c.m0(i3, "register_request_satellite_count"), i4).apply();
        if (i4 < 4) {
            t(i3);
            return;
        }
        Log.d("AutoReg_SatWork", "[" + i3 + "] satellite achieved max request");
    }

    public final void p(String str, int i3, long j3) {
        try {
            AlarmManager alarmManager = (AlarmManager) this.f1700e;
            long jElapsedRealtime = SystemClock.elapsedRealtime() + j3;
            Intent intent = new Intent(str);
            Context context = this.f1697b;
            intent.setPackage(context.getPackageName());
            intent.addFlags(268435456);
            intent.putExtra("slotId", i3);
            alarmManager.setExactAndAllowWhileIdle(2, jElapsedRealtime, PendingIntent.getBroadcast(context, i3, intent, 201326592));
        } catch (Exception e3) {
            Log.e("AutoReg_Work", "scheduleAlarm " + str + "[" + i3 + "] failed: " + e3);
        }
    }

    public final void q(String str, long j3) {
        try {
            ((AlarmManager) this.f1700e).setExactAndAllowWhileIdle(2, SystemClock.elapsedRealtime() + j3, a(str));
        } catch (Exception e3) {
            Log.e("AutoReg_Work", "scheduleAlarm " + str + " failed: " + e3);
        }
    }

    public final void r(int i3) {
        Object obj = this.a;
        Object obj2 = this.f1699d;
        int i4 = ((SharedPreferences) obj2).getInt(l1.c.m0(i3, "register_request_satellite_count"), 0) + 1;
        ((SharedPreferences) obj2).edit().putInt(l1.c.m0(i3, "register_request_satellite_count"), i4).apply();
        if (!j1.c.h(this.f1697b) || i4 >= 4) {
            return;
        }
        try {
            ((AlarmManager) ((b) obj).f1700e).setExactAndAllowWhileIdle(2, SystemClock.elapsedRealtime() + 600000, ((b) obj).a("miui.intent.action.SATELLITE_AUTO_REGISTRATION_PERIOD_RETRY"));
        } catch (Exception e3) {
            Log.e("AutoReg_SatWork", "scheduleNextIfNeed", e3);
        }
    }

    public final void s(e eVar) {
        Log.d("AutoReg_DataWork", "scheduleRegistrationJob " + eVar);
        this.f1698c.getAllCellInfo();
        Context context = this.f1697b;
        l1.c.R0(context, 1);
        JobInfo.Builder builder = new JobInfo.Builder(1, new ComponentName(context, (Class<?>) RegistrationJobService.class));
        builder.setOverrideDeadline(1000L);
        builder.setRequiredNetworkType(1);
        builder.setPersisted(false);
        Bundle bundle = new Bundle();
        bundle.putSerializable("REG_ENTITY", eVar);
        builder.setTransientExtras(bundle);
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        if (jobScheduler != null) {
            try {
                jobScheduler.schedule(builder.build());
            } catch (IllegalStateException unused) {
                Log.d("AutoReg_DataWork", "scheduleRegistrationJob exception");
            }
        }
    }

    public final void t(int i3) {
        Object obj = this.a;
        if (i3 < 0) {
            return;
        }
        Log.d("AutoReg_SatWork", "[" + i3 + "] tryToRegister");
        if (j1.c.f(i3)) {
            Context context = this.f1697b;
            if (j1.c.h(context) && j1.c.g(context, (SharedPreferences) this.f1699d, "satellite_imsi", i3)) {
                try {
                    String strI = i(i3);
                    int iP = j1.b.l(context).p(i3);
                    if (iP != -1) {
                        new m(context, i3, iP, true).a(i3, strI);
                    }
                    ((b) obj).c(i3, "miui.intent.action.ACTION_SMS_AUTO_REGISTRATION_SEAULTCHECK");
                    ((b) obj).d("miui.intent.action.ACTION_SMS_AUTO_REGISTRATION_SEAULTCHECK");
                    ((b) obj).p("miui.intent.action.ACTION_SMS_AUTO_REGISTRATION_SEAULTCHECK", i3, 600000L);
                } catch (Exception e3) {
                    Log.e("AutoReg_SatWork", "satellite send error", e3);
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:76:0x01f2  */
    /* JADX WARN: Code duplicated, block: B:78:0x01fc  */
    public final void u(String str) {
        String str2;
        boolean zIsEmpty;
        Log.d("AutoReg_DataWork", "tryToRegister reason=".concat(str));
        ConnectivityManager connectivityManager = (ConnectivityManager) this.f1700e;
        if (connectivityManager == null) {
            return;
        }
        Context context = this.f1697b;
        boolean z2 = PreferenceManager.getDefaultSharedPreferences(context).getBoolean("state_dds_changed", false);
        Object obj = this.a;
        if (z2 && miui.telephony.SubscriptionManager.getDefault().getDefaultDataSlotId() == ((SharedPreferences) ((b) obj).a).getInt("register_dds_slot", 0)) {
            Log.d("AutoReg_DataWork", "DDS reverted to registered slot, clear flag");
            l1.c.Q0(context, false);
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean z3 = PreferenceManager.getDefaultSharedPreferences(context).getBoolean("state_dds_changed", false);
        boolean z4 = PreferenceManager.getDefaultSharedPreferences(context).getBoolean("state_5g_changed", false);
        boolean z5 = PreferenceManager.getDefaultSharedPreferences(context).getBoolean("state_sw_changed", false);
        boolean z6 = PreferenceManager.getDefaultSharedPreferences(context).getBoolean("state_period_trigger", false);
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            if (z3 || z5 || z6 || z4) {
                Log.d("AutoReg_DataWork", "no network, will retry on connectivity wake");
                return;
            }
            return;
        }
        b bVar = (b) obj;
        if (!j().equals(((SharedPreferences) bVar.a).getString("sim_iccid", null)) || z3 || z5 || z6 || z4) {
            ((b) obj).d("com.qualcomm.qti.action.AUTO_REGISTRATION_RETRY");
            boolean z7 = activeNetworkInfo.getType() == 1;
            Log.d("AutoReg_DataWork", "tryToRegister underWifi=" + z7 + " ddsChanged=" + z3 + " fiveGChanged=" + z4 + " swChanged=" + z5 + " period=" + z6 + " hasService=" + l());
            if ("service_state".equals(str) || !z7 || l()) {
                Log.d("AutoReg_DataWork", "onRegistrationRequest reason=".concat(str));
                boolean z8 = PreferenceManager.getDefaultSharedPreferences(context).getBoolean("state_5g_changed", false);
                if ((!PreferenceManager.getDefaultSharedPreferences(context).getBoolean("state_registering", false) || z8) && m() && !k()) {
                    if (!(Settings.Global.getInt(context.getContentResolver(), "device_provisioned", 0) == 1)) {
                        str2 = "provision not complete, abort";
                    } else if (!PreferenceManager.getDefaultSharedPreferences(context).getBoolean("state_power_on", true)) {
                        String strJ = j();
                        String string = ((SharedPreferences) bVar.a).getString("sim_iccid", null);
                        boolean z9 = PreferenceManager.getDefaultSharedPreferences(context).getBoolean("state_dds_changed", false);
                        boolean z10 = PreferenceManager.getDefaultSharedPreferences(context).getBoolean("state_sw_changed", false);
                        if (!PreferenceManager.getDefaultSharedPreferences(context).getBoolean("state_period_trigger", false) || z10 || z8 || z9) {
                            int iH = h();
                            if (!n() && !PreferenceManager.getDefaultSharedPreferences(context).getBoolean("state_dds_alarm_pending", false)) {
                                l1.c.V0(System.currentTimeMillis(), context, true);
                                s(new e(iH, SubscriptionManager.getSlotIndex(SubscriptionManager.getDefaultDataSubscriptionId())));
                                return;
                            }
                            str2 = "non CT DDS / DDS delay pending, abort";
                        } else {
                            if (strJ.contains(",")) {
                                String[] strArrSplit = TextUtils.split(strJ, ",");
                                int length = strArrSplit.length;
                                int i3 = 0;
                                while (true) {
                                    if (i3 >= length) {
                                        zIsEmpty = true;
                                        break;
                                    } else {
                                        if (!TextUtils.isEmpty(strArrSplit[i3].trim())) {
                                            zIsEmpty = false;
                                            break;
                                        }
                                        i3++;
                                    }
                                }
                            } else {
                                zIsEmpty = TextUtils.isEmpty(strJ.trim());
                            }
                            if (zIsEmpty || strJ.equals(string)) {
                                str2 = "Already registered for this SIM and no other trigger";
                            } else {
                                int iH2 = h();
                                if (!n()) {
                                }
                                str2 = "non CT DDS / DDS delay pending, abort";
                            }
                        }
                    } else if (l1.c.k0(context)) {
                        Log.d("AutoReg_DataWork", "power-on barrier stale, self-clearing");
                        l1.c.T0(context, false);
                        l1.c.U0(context, false);
                        bVar.d("com.xiaomi.registration.action.AUTOREG_DELAYED_BOOT_FIRE");
                        String strJ2 = j();
                        String string2 = ((SharedPreferences) bVar.a).getString("sim_iccid", null);
                        boolean z11 = PreferenceManager.getDefaultSharedPreferences(context).getBoolean("state_dds_changed", false);
                        boolean z12 = PreferenceManager.getDefaultSharedPreferences(context).getBoolean("state_sw_changed", false);
                        if (PreferenceManager.getDefaultSharedPreferences(context).getBoolean("state_period_trigger", false)) {
                            int iH3 = h();
                            if (!n()) {
                            }
                            str2 = "non CT DDS / DDS delay pending, abort";
                        } else {
                            int iH4 = h();
                            if (!n()) {
                            }
                            str2 = "non CT DDS / DDS delay pending, abort";
                        }
                    } else {
                        boolean z13 = bVar.b("com.xiaomi.registration.action.AUTOREG_DELAYED_BOOT_FIRE") != null;
                        if (PreferenceManager.getDefaultSharedPreferences(context).getBoolean("state_power_on_alarm_pending", false) && z13) {
                            return;
                        }
                        l1.c.U0(context, true);
                        bVar.q("com.xiaomi.registration.action.AUTOREG_DELAYED_BOOT_FIRE", 30000L);
                        str2 = "power-on, scheduling 30s alarm and bailing";
                    }
                } else {
                    str2 = "registering=" + PreferenceManager.getDefaultSharedPreferences(context).getBoolean("state_registering", false) + " fiveG=" + z8 + " simReady=" + m() + " roaming=" + k() + ", abort";
                }
                Log.d("AutoReg_DataWork", str2);
            }
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
    public b(b bVar, int i3) {
        if (i3 == 1) {
            this.a = bVar;
            this.f1697b = bVar.f1697b;
            this.f1699d = (SharedPreferences) bVar.a;
            this.f1698c = bVar.f1698c;
            this.f1700e = f2.e.g("ro.product.device", "");
            return;
        }
        this.a = bVar;
        Context context = bVar.f1697b;
        this.f1697b = context;
        TelephonyManager telephonyManager = bVar.f1698c;
        this.f1698c = telephonyManager;
        this.f1699d = (SubscriptionManager) bVar.f1699d;
        this.f1700e = (ConnectivityManager) context.getSystemService("connectivity");
        if (!j1.c.a(context).equals(((SharedPreferences) bVar.a).getString("register_sw_version", ""))) {
            l1.c.Y0(context, true);
        }
        String string = ((SharedPreferences) bVar.a).getString("build_fingerprint", null);
        String str = android.os.Build.FINGERPRINT;
        if (str.equals(string)) {
            return;
        }
        SharedPreferences.Editor editorEdit = ((SharedPreferences) bVar.a).edit();
        editorEdit.putString("build_fingerprint", str);
        editorEdit.putString("sim_iccid", "");
        int phoneCount = telephonyManager.getPhoneCount();
        for (int i4 = 0; i4 < phoneCount; i4++) {
            editorEdit.putString("lte_imsi" + i4, "");
            editorEdit.putString("satellite_imsi" + i4, "");
        }
        editorEdit.apply();
    }
}
