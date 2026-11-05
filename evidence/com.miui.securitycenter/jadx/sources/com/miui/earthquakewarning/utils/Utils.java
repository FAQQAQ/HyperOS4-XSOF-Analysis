package com.miui.earthquakewarning.utils;

import aj.y;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import com.miui.common.utils.j0;
import com.miui.common.utils.q1;
import com.miui.earthquakewarning.Constants;
import com.miui.securitycenter.Application;
import com.miui.securitycenter.R;
import com.miui.warningcenter.disasterwarning.DisasterConstants;
import com.xiaomi.onetrack.api.ay;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import miui.os.Build;
import miuix.appcompat.app.AlertDialog;

/* JADX INFO: loaded from: classes2.dex */
public class Utils {
    private static final String ACCOUNT_TYPE_STR = "com.xiaomi";
    public static final String CARACCIDENT_DETECT_POLICE_NAME = "CrashDetection";
    public static final String EARTHQUAKE_MONITOR_POLICE_NAME = "earthquake_monitor";
    public static final String EARTHQUAKE_WARNING_POLICE_NAME = "earthquake";
    private static final String EMPTY_STR = "";
    public static final List<String> EQM_SENSOR_SUPPORT_DEVICE;
    public static final String HIDE_GESTURE_LINE = "hide_gesture_line";
    private static final String KEY_MIUI_SOS_EMERGENCY_CONTACTNAMES = "key_miui_sos_emergency_contacts_names";
    private static final String KEY_MIUI_SOS_EMERGENCY_CONTACTS = "key_miui_sos_emergency_contacts";
    public static final String POLICE_ASSIST_POLICE_NAME = "emergency_location";

    public interface Listener {
        default void onAgreePrivacyChange() {
        }

        void onRefusePrivacyChange();
    }

    static {
        ArrayList arrayList = new ArrayList();
        EQM_SENSOR_SUPPORT_DEVICE = arrayList;
        arrayList.add("zeus");
        arrayList.add("cupid");
    }

    private Utils() {
    }

    public static /* synthetic */ void a(final Context context, final String str, Listener listener, DialogInterface dialogInterface, int i10) {
        b7.a.b(new Runnable() { // from class: com.miui.earthquakewarning.utils.e
            @Override // java.lang.Runnable
            public final void run() {
                Context context2 = context;
                hi.e.b(context2, str, y.a(context2));
            }
        });
        dialogInterface.dismiss();
        if (listener != null) {
            listener.onAgreePrivacyChange();
        }
    }

    public static /* synthetic */ void d(Context context, String str, String str2, String str3, Listener listener, DialogInterface dialogInterface, int i10) {
        dialogInterface.dismiss();
        showPrivacyRevokeTipDialog(context, str, str2, str3, listener);
    }

    public static /* synthetic */ void e(Listener listener, DialogInterface dialogInterface, int i10) {
        dialogInterface.dismiss();
        if (listener != null) {
            listener.onRefusePrivacyChange();
        }
    }

    public static /* synthetic */ void f(final Context context, final String str, DialogInterface dialogInterface, int i10) {
        b7.a.b(new Runnable() { // from class: com.miui.earthquakewarning.utils.d
            @Override // java.lang.Runnable
            public final void run() {
                Context context2 = context;
                hi.e.b(context2, str, y.a(context2));
            }
        });
        dialogInterface.dismiss();
    }

    public static String getAccountId(Context context) {
        Account[] accountsByType;
        AccountManager accountManager = AccountManager.get(context);
        if (accountManager == null || (accountsByType = accountManager.getAccountsByType("com.xiaomi")) == null || accountsByType.length == 0) {
            return "";
        }
        String str = accountsByType[0].name;
        return TextUtils.isEmpty(str) ? "" : str;
    }

    public static String getContact() {
        return l7.e.l(Constants.PREFERENCE_KEY_EARTHQUAKE_WARNING_CONTACT, "");
    }

    public static String getContactName() {
        return l7.e.l(Constants.PREFERENCE_KEY_EARTHQUAKE_WARNING_CONTACT_NAME, "");
    }

    public static String getEarthquakeMonitorOrder() {
        return l7.e.l(Constants.PREFERENCE_KEY_EARTHQUAKE_WARNING_MONITOR_ORDER, "");
    }

    public static String getEarthquakeVolunteerToken() {
        return l7.e.l(Constants.PREFERENCE_KEY_EARTHQUAKE_WARNING_MONITOR_TOKEN, "");
    }

    public static List<String> getEmergencyContacts(Context context) {
        ArrayList arrayList = new ArrayList();
        String string = Settings.Secure.getString(context.getContentResolver(), KEY_MIUI_SOS_EMERGENCY_CONTACTS);
        return !TextUtils.isEmpty(string) ? new ArrayList(Arrays.asList(string.split(";"))) : arrayList;
    }

    public static List<String> getEmergencyNames(Context context) {
        ArrayList arrayList = new ArrayList();
        String string = Settings.Secure.getString(context.getContentResolver(), KEY_MIUI_SOS_EMERGENCY_CONTACTNAMES);
        return !TextUtils.isEmpty(string) ? new ArrayList(Arrays.asList(string.split(";"))) : arrayList;
    }

    public static String getPreviousAreaCityName() {
        return l7.e.l(Constants.PREFERENCE_KEY_PREVIOUS_AREA_CITY_NAME, "");
    }

    public static int getPreviousAreaCode() {
        return l7.e.h(Constants.PREFERENCE_KEY_PREVIOUS_AREA_CODE, 0);
    }

    public static int getPreviousAreaDistricCode() {
        return l7.e.h(Constants.PREFERENCE_KEY_PREVIOUS_AREA_DISTRICT_CODE, 0);
    }

    public static int getPreviousBrightness() {
        return l7.e.h(Constants.PREFERENCE_KEY_PREVIOUS_BRIGHTNESS, 0);
    }

    public static int getPreviousBrightnessMode() {
        return l7.e.h(Constants.PREFERENCE_KEY_PREVIOUS_BRIGHTNESS_MODE, 0);
    }

    public static String getPreviousDistrict() {
        return l7.e.l(Constants.PREFERENCE_KEY_PREVIOUS_DISTRICT, "");
    }

    public static int getPreviousGPS() {
        return l7.e.h(Constants.PREFERENCE_KEY_PREVIOUS_GPS, 0);
    }

    public static String getPreviousGeoHashCode() {
        return l7.e.l(Constants.PREFERENCE_KEY_PREVIOUS_GEO_HASH_CODE, "");
    }

    public static int getPreviousVolume() {
        return l7.e.h(Constants.PREFERENCE_KEY_PREVIOUS_VOLUME, 0);
    }

    public static float getSelectIntensity() {
        return l7.e.g(Constants.PREFERENCE_KEY_SELECT_PUSH_INTENSITY, Build.IS_INTERNATIONAL_BUILD ? 4.0f : 2.0f);
    }

    public static boolean getStrongPushToggle() {
        return l7.e.e(DisasterConstants.PREFERENCE_KEY_DISASTER_STRONG_TOOGLE, false);
    }

    public static boolean getSystemPushToggle() {
        return l7.e.e(DisasterConstants.PREFERENCE_KEY_DISASTER_SYSTEM_TOOGLE, false);
    }

    public static long getUploadTopicTime() {
        return l7.e.j(Constants.PREFERENCE_KEY_SET_TOPIC_TIME, 0L);
    }

    public static void initEarthquakeWarningInSetting(Context context) {
        boolean z10 = l7.e.h(Constants.IS_FIRST_TIME_TO_CHECK_SLEEP_MODE, 1) != 2;
        if (z10) {
            Settings.Secure.putInt(context.getContentResolver(), Constants.PREFERENCE_KEY_OPEN_EARTHQUAKE_WARNING, 0);
        }
        if (isPowerKeeperSupportSleepModeSwitch(context) && isEarthquakeWarningOpen() && z10) {
            Settings.Secure.putInt(context.getContentResolver(), Constants.PREFERENCE_KEY_OPEN_EARTHQUAKE_WARNING, 1);
            l7.e.p(Constants.IS_FIRST_TIME_TO_CHECK_SLEEP_MODE, 2);
        }
    }

    public static boolean isEarthquakeMonitorOpen() {
        return l7.e.e(Constants.PREFERENCE_KEY_EARTHQUAKE_WARNING_OPEN_MONITOR, false);
    }

    public static boolean isEarthquakeWarningOpen() {
        return l7.e.e(Constants.PREFERENCE_KEY_OPEN_EARTHQUAKE_WARNING, false);
    }

    public static boolean isEmergencyInfoEmpty() {
        return l7.e.e(Constants.PREF_KEY_ALL_INFO_DELETE, true);
    }

    public static boolean isEnableGestureLine(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), HIDE_GESTURE_LINE, 0) == 0;
    }

    private static boolean isEqmSensorSupportDevice() {
        return j0.u(EQM_SENSOR_SUPPORT_DEVICE);
    }

    public static boolean isFirstGuide() {
        return l7.e.e(Constants.PREFERENCE_KEY_EARTHQUAKE_WARNING_FIRST_GUIDE, true);
    }

    public static boolean isLowEarthquakeWarningOpen() {
        return l7.e.e(Constants.PREFERENCE_KEY_OPEN_LOW_EARTHQUAKE_WARNING, true);
    }

    public static boolean isNeedRestore() {
        return l7.e.e(Constants.PREFERENCE_KEY_EARTHQUAKE_WARNING_NEED_RESTORE, true);
    }

    public static boolean isNeedToForceUpdateArea() {
        return l7.e.e(Constants.PREFERENCE_KEY_AREA_DB_UPDATE, true);
    }

    public static boolean isPowerKeeperSupportSleepModeSwitch(Context context) {
        return !Build.IS_INTERNATIONAL_BUILD && Settings.System.getInt(context.getContentResolver(), "powerkeep_earthquake_warning_version", 0) == 1;
    }

    public static boolean isVoiceCapable() {
        try {
            return ((TelephonyManager) Application.B().getSystemService(ay.f27518d)).isVoiceCapable();
        } catch (Exception e10) {
            Log.e("EarthquakeManager", "isVoiceCapable error", e10);
            return false;
        }
    }

    public static void needRestore(boolean z10) {
        l7.e.n(Constants.PREFERENCE_KEY_EARTHQUAKE_WARNING_NEED_RESTORE, z10);
    }

    public static void openEarthquakeMonitor(boolean z10) {
        l7.e.n(Constants.PREFERENCE_KEY_EARTHQUAKE_WARNING_OPEN_MONITOR, z10);
    }

    public static void setContact(String str) {
        l7.e.r(Constants.PREFERENCE_KEY_EARTHQUAKE_WARNING_CONTACT, str);
    }

    public static void setContactName(String str) {
        l7.e.r(Constants.PREFERENCE_KEY_EARTHQUAKE_WARNING_CONTACT_NAME, str);
    }

    public static void setEarthquakeMonitorOrder(String str) {
        l7.e.r(Constants.PREFERENCE_KEY_EARTHQUAKE_WARNING_MONITOR_ORDER, str);
    }

    public static void setEarthquakeVolunteerToken(String str) {
        l7.e.r(Constants.PREFERENCE_KEY_EARTHQUAKE_WARNING_MONITOR_TOKEN, str);
    }

    public static void setEarthquakeWarningOpen(boolean z10) {
        l7.e.n(Constants.PREFERENCE_KEY_OPEN_EARTHQUAKE_WARNING, z10);
        Settings.Secure.putInt(Application.B().getContentResolver(), Constants.PREFERENCE_KEY_OPEN_EARTHQUAKE_WARNING, z10 ? 1 : 0);
    }

    public static void setEmergencyDeleteAll(boolean z10) {
        l7.e.n(Constants.PREF_KEY_ALL_INFO_DELETE, z10);
    }

    public static void setFirstGuide(boolean z10) {
        l7.e.n(Constants.PREFERENCE_KEY_EARTHQUAKE_WARNING_FIRST_GUIDE, z10);
    }

    public static void setLowEarthquakeWarningOpen(boolean z10) {
        l7.e.n(Constants.PREFERENCE_KEY_OPEN_LOW_EARTHQUAKE_WARNING, z10);
    }

    public static void setNeedToForceUpdateArea(boolean z10) {
        l7.e.n(Constants.PREFERENCE_KEY_AREA_DB_UPDATE, z10);
    }

    public static void setPreviousAreaCityName(String str) {
        if (str == null) {
            str = "";
        }
        l7.e.r(Constants.PREFERENCE_KEY_PREVIOUS_AREA_CITY_NAME, str);
    }

    public static void setPreviousAreaCode(int i10) {
        l7.e.p(Constants.PREFERENCE_KEY_PREVIOUS_AREA_CODE, i10);
    }

    public static void setPreviousAreaDistrictCode(int i10) {
        l7.e.p(Constants.PREFERENCE_KEY_PREVIOUS_AREA_DISTRICT_CODE, i10);
    }

    public static void setPreviousBrightness(int i10) {
        l7.e.p(Constants.PREFERENCE_KEY_PREVIOUS_BRIGHTNESS, i10);
    }

    public static void setPreviousBrightnessMode(int i10) {
        l7.e.p(Constants.PREFERENCE_KEY_PREVIOUS_BRIGHTNESS_MODE, i10);
    }

    public static void setPreviousDistrict(String str) {
        l7.e.r(Constants.PREFERENCE_KEY_PREVIOUS_DISTRICT, str);
    }

    public static void setPreviousGPS(int i10) {
        l7.e.p(Constants.PREFERENCE_KEY_PREVIOUS_GPS, i10);
    }

    public static void setPreviousGeoHashCode(String str) {
        l7.e.r(Constants.PREFERENCE_KEY_PREVIOUS_GEO_HASH_CODE, str);
    }

    public static void setPreviousVolume(int i10) {
        l7.e.p(Constants.PREFERENCE_KEY_PREVIOUS_VOLUME, i10);
    }

    public static void setSelectIntensity(float f10) {
        l7.e.o(Constants.PREFERENCE_KEY_SELECT_PUSH_INTENSITY, f10);
    }

    public static void setUploadTopicTime(long j10) {
        l7.e.q(Constants.PREFERENCE_KEY_SET_TOPIC_TIME, j10);
    }

    public static boolean showEqmEntry(Context context) {
        Sensor defaultSensor = ((SensorManager) context.getSystemService(SensorManager.class)).getDefaultSensor(Constants.EQM_SENSOR_ID, true);
        if (!Build.IS_DEVELOPMENT_VERSION) {
            if (defaultSensor == null) {
                return false;
            }
            if (defaultSensor.getVersion() < 2 && !isEqmSensorSupportDevice()) {
                return false;
            }
        }
        return true;
    }

    public static void showPrivacyRevokeTipDialog(final Context context, String str, final String str2, String str3, final Listener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.privacy_revoke_tip_dialog_title).setMessage(Html.fromHtml(context.getString(R.string.privacy_revoke_tip_dialog_content, str, str, str3))).setCancelable(false).setPositiveButton(R.string.privacy_dialog_positive_button_text, new DialogInterface.OnClickListener() { // from class: com.miui.earthquakewarning.utils.f
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i10) {
                Utils.f(context, str2, dialogInterface, i10);
            }
        }).setNegativeButton(R.string.privacy_dialog_negative_button_text, new DialogInterface.OnClickListener() { // from class: com.miui.earthquakewarning.utils.g
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i10) {
                Utils.e(listener, dialogInterface, i10);
            }
        });
        AlertDialog alertDialogCreate = builder.create();
        alertDialogCreate.show();
        alertDialogCreate.getMessageView().setMovementMethod(LinkMovementMethod.getInstance());
    }

    public static void showPrivacyUpdateDialog(final Context context, String str, final String str2, final String str3, final String str4, final Listener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.privacy_update_dialog_title).setMessage(Html.fromHtml(context.getResources().getString(R.string.privacy_update_dialog_content_1, str3) + "<br>" + str + "<br>" + context.getResources().getString(R.string.privacy_update_dialog_content_2, str4))).setCancelable(false).setPositiveButton(R.string.privacy_dialog_positive_button_text, new DialogInterface.OnClickListener() { // from class: com.miui.earthquakewarning.utils.b
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i10) {
                Utils.a(context, str2, listener, dialogInterface, i10);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.miui.earthquakewarning.utils.c
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i10) {
                Utils.d(context, str3, str2, str4, listener, dialogInterface, i10);
            }
        });
        AlertDialog alertDialogCreate = builder.create();
        alertDialogCreate.show();
        alertDialogCreate.getMessageView().setMovementMethod(LinkMovementMethod.getInstance());
    }

    public static boolean supportMap(Context context) {
        return q1.x(context, Constants.SECURITY_ADD_PACKAGE) >= 91032;
    }
}
