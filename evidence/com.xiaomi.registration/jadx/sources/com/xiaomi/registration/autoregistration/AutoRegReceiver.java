package com.xiaomi.registration.autoregistration;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.registration.autoregistration.AutoRegReceiver;
import f2.e;
import j1.c;
import miui.os.Build;
import miui.os.UserHandle;

/* JADX INFO: loaded from: classes.dex */
public class AutoRegReceiver extends BroadcastReceiver {
    public static final /* synthetic */ int a = 0;

    public static boolean a(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return false;
        }
        int phoneCount = telephonyManager.getPhoneCount();
        for (int i3 = 0; i3 < phoneCount; i3++) {
            if (telephonyManager.getSimState(i3) == 5) {
                return true;
            }
        }
        return false;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        boolean z2;
        String str;
        if (intent == null || intent.getAction() == null) {
            return;
        }
        String action = intent.getAction();
        Log.d("AutoReg_Receiver", "onReceive action=" + action);
        int i3 = c.a;
        if (e.c("ro.radio.noril") && TextUtils.isEmpty(e.g("ro.ril.oem.ctei", ""))) {
            return;
        }
        int i4 = c.a;
        if (i4 != 0) {
            if (i4 != 1) {
                boolean zEquals = "disabled".equals(e.g("ro.product.autosms", "enabled"));
                if (zEquals) {
                    str = "ro.product.autosms" + zEquals;
                } else {
                    if (Build.IS_CU_CUSTOMIZATION || Build.IS_CM_CUSTOMIZATION) {
                        str = "it cu, so not reg";
                    } else if (TextUtils.isEmpty(c.c(context))) {
                        Log.d("AutoReg_Utils", "not the correct device to reg1, the device is " + Build.DEVICE);
                    } else {
                        c.a = 1;
                    }
                    z2 = false;
                }
                Log.d("AutoReg_Utils", str);
                c.a = 0;
                z2 = false;
            }
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 && UserHandle.myUserId() == 0) {
            if ((Settings.Global.getInt(context.getContentResolver(), "device_provisioned", 0) == 1) || "android.provision.action.PROVISION_COMPLETE".equals(action)) {
                if ("android.intent.action.BOOT_COMPLETED".equals(action) || "android.intent.action.MY_PACKAGE_REPLACED".equals(action)) {
                    PreferenceManager.getDefaultSharedPreferences(context).edit().putInt("register_request_ims_count", 0).apply();
                    l1.c.T0(context, true);
                    l1.c.U0(context, false);
                    l1.c.P0(context, false);
                    if (PreferenceManager.getDefaultSharedPreferences(context).getBoolean("state_registering", false)) {
                        l1.c.V0(0L, context, false);
                        l1.c.R0(context, 0);
                    }
                    if (!a(context)) {
                        Log.d("AutoReg_Receiver", "no SIM ready, exit process");
                        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: i1.d
                            @Override // java.lang.Runnable
                            public final void run() {
                                int i5 = AutoRegReceiver.a;
                                Process.killProcess(Process.myPid());
                            }
                        });
                        return;
                    }
                    JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
                    if (jobScheduler == null) {
                        return;
                    }
                    jobScheduler.schedule(new JobInfo.Builder(2, new ComponentName(context, (Class<?>) RegistrationJobService.class)).setRequiredNetworkType(1).build());
                    Log.d("AutoReg_Receiver", "scheduled network available job");
                    return;
                }
                if ("android.intent.action.LOCKED_BOOT_COMPLETED".equals(action)) {
                    AutoRegIntentService.b(context, 63);
                    return;
                }
                if ("android.provision.action.PROVISION_COMPLETE".equals(action)) {
                    AutoRegIntentService.b(context, 61);
                    return;
                }
                if ("android.intent.action.SIM_STATE_CHANGED".equals(action)) {
                    String stringExtra = intent.getStringExtra("ss");
                    if ("READY".equals(stringExtra) || "LOADED".equals(stringExtra) || "IMSI".equals(stringExtra) || "ABSENT".equals(stringExtra)) {
                        AutoRegIntentService.c(context, 60, -1, intent);
                        return;
                    } else {
                        if (a(context)) {
                            return;
                        }
                        Log.d("AutoReg_Receiver", "SIM not ready and process is fresh, exit");
                        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: i1.d
                            @Override // java.lang.Runnable
                            public final void run() {
                                int i5 = AutoRegReceiver.a;
                                Process.killProcess(Process.myPid());
                            }
                        });
                        return;
                    }
                }
                if ("android.telephony.action.SECRET_CODE".equals(action)) {
                    AutoRegIntentService.b(context, 50);
                    return;
                }
                if ("com.qualcomm.qti.action.AUTO_REGISTRATION".equals(action)) {
                    AutoRegIntentService.c(context, 30, -1, intent);
                    return;
                }
                if ("com.qualcomm.qti.action.AUTO_REGISTRATION_RETRY".equals(action)) {
                    AutoRegIntentService.b(context, 10);
                    return;
                }
                if ("com.qualcomm.qti.action.AUTO_REGISTRATION_PERIOD_TRIGGER".equals(action)) {
                    AutoRegIntentService.b(context, 20);
                    return;
                }
                int i5 = 3;
                if ("miui.intent.action.ACTION_DEFAULT_DATA_SLOT_CHANGED".equals(action)) {
                    AutoRegIntentService.b(context, 3);
                    return;
                }
                if ("com.xiaomi.registration.action.AUTOREG_DELAYED_BOOT_FIRE".equals(action)) {
                    AutoRegIntentService.b(context, 2);
                    return;
                }
                if ("com.xiaomi.registration.action.AUTOREG_DELAYED_DDS_FIRE".equals(action)) {
                    AutoRegIntentService.b(context, 4);
                    return;
                }
                if ("com.xiaomi.registration.action.AUTOREG_GAME_RETRY_SMS_FIRE".equals(action)) {
                    AutoRegIntentService.c(context, 64, intent.getIntExtra("slotId", -1), null);
                    return;
                }
                if ("com.xiaomi.registration.action.AUTOREG_DELAYED_SECONDSLOT_FIRE".equals(action)) {
                    AutoRegIntentService.c(context, 67, intent.getIntExtra("slotId", -1), null);
                    return;
                }
                if ("com.qualcomm.qti.action.SMS_AUTO_REGISTRATION".equals(action)) {
                    AutoRegIntentService.c(context, c.h(context) ? 32 : 31, intent.getExtras() != null ? intent.getExtras().getInt("phone", 0) : 0, intent);
                    return;
                }
                if ("miui.intent.action.SMS_AUTO_REGISTRATION_RETRY".equals(action)) {
                    AutoRegIntentService.c(context, 11, intent.getIntExtra("slotId", -1), null);
                    return;
                }
                if ("miui.intent.action.SMS_AUTO_REGISTRATION_PERIOD_TRIGGER".equals(action)) {
                    AutoRegIntentService.c(context, 21, -1, null);
                    return;
                }
                if ("miui.intent.action.ACTION_SMS_AUTO_REGISTRATION_IMSSEAULTCHECK".equals(action)) {
                    AutoRegIntentService.c(context, 40, intent.getIntExtra("slotId", -1), null);
                    return;
                }
                if ("com.android.app.action.SATELLITE_STATE_CHANGE".equals(action)) {
                    AutoRegIntentService.c(context, 62, intent.getIntExtra("phone_id", 0), intent);
                    return;
                }
                if ("miui.intent.action.SATELLITE_AUTO_REGISTRATION_PERIOD_RETRY".equals(action)) {
                    AutoRegIntentService.b(context, 13);
                    return;
                }
                if ("miui.intent.action.ACTION_SMS_AUTO_REGISTRATION_SEAULTCHECK".equals(action)) {
                    AutoRegIntentService.c(context, 41, intent.getIntExtra("slotId", -1), null);
                    return;
                }
                if ("miui.intent.action.CTEI_AUTO_REGISTRATION".equals(action)) {
                    AutoRegIntentService.b(context, 22);
                    return;
                }
                if ("miui.intent.action.CTEI_AUTO_REGISTRATION_RETRY".equals(action)) {
                    AutoRegIntentService.b(context, 12);
                    return;
                }
                if (!"com.xiaomi.registration.action.KEEPALIVE_WAKEUP".equals(action)) {
                    Log.w("AutoReg_Receiver", "unhandled action=" + action);
                    return;
                }
                String stringExtra2 = intent.getStringExtra("reason");
                int intExtra = intent.getIntExtra("slotId", -1);
                if ("CONNECTIVITY".equals(stringExtra2)) {
                    i5 = 8;
                } else if ("SERVICE_STATE".equals(stringExtra2)) {
                    i5 = 9;
                } else if ("FIVEG_SWITCH".equals(stringExtra2)) {
                    i5 = 5;
                } else if ("SATELLITE_SS".equals(stringExtra2)) {
                    i5 = 7;
                } else if ("IMS_CAP".equals(stringExtra2)) {
                    i5 = 6;
                } else if (!"DDS".equals(stringExtra2)) {
                    Log.w("AutoReg_Receiver", "unknown KEEPALIVE_WAKEUP reason=" + stringExtra2);
                    return;
                }
                AutoRegIntentService.c(context, i5, intExtra, intent);
            }
        }
    }
}
