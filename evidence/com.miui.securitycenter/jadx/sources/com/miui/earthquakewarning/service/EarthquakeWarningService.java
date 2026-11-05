package com.miui.earthquakewarning.service;

import aj.y;
import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.miui.common.utils.b1;
import com.miui.earthquakewarning.Constants;
import com.miui.earthquakewarning.EarthquakeWarningManager;
import com.miui.earthquakewarning.log.EarthquakePushLog;
import com.miui.earthquakewarning.model.EwTranData;
import com.miui.earthquakewarning.model.SaveAreaResult;
import com.miui.earthquakewarning.utils.LocationRecordManager;
import com.miui.earthquakewarning.utils.NotificationUtil;
import com.miui.earthquakewarning.utils.Utils;
import com.miui.gamebooster.utils.u2;
import com.xiaomi.onetrack.api.ay;
import ff.f;
import java.util.Arrays;
import l7.e;
import oh.g;

/* JADX INFO: loaded from: classes2.dex */
public class EarthquakeWarningService extends Service implements SensorEventListener {
    public static final String ACTION_CLOSE_WARNING = "action_close_warning";
    public static final String ACTION_EXIT_EARTHQUAKEWARNING = "action_exit_earthquakewarning";
    public static final String ACTION_EXIT_VOLUNTEER = "action_exit_volunteer";
    public static final String ACTION_JOIN_VOLUNTEER = "action_join_volunteer";
    public static final String ACTION_OPEN_WARNING = "action_open_warning";
    public static final String ACTION_RESTORE_DATA = "action_restore_data";
    public static final String ACTION_SUBSCRIBE_AREA = "action_subscribe_area";
    public static final String ACTION_SUBSCRIBE_LOCATION_TOPIC = "action_subscribe_location_topic";
    public static final String ACTION_UNSUBSCRIBE_AREA = "action_unsubscribe_area";
    public static final String EXTRA_AREA_CITY = "extra_area_city";
    public static final String EXTRA_AREA_CODE = "extra_area_code";
    public static final String EXTRA_AREA_COUNTRIES = "extra_area_countries";
    public static final String EXTRA_AREA_PROVINCE = "extra_area_province";
    public static final String EXTRA_AREA_REGION = "extra_area_region";
    public static final String EXTRA_AREA_SUBSCRIBELEVEL = "extra_area_subscribe_level";
    public static final String EXTRA_AREA_SUPPORT = "extra_area_support";
    public static final String EXTRA_LOCATION_CITY_CODE = "extra_location_city_code";
    public static final String EXTRA_USER_SUBSCRIBE = "extra_user_subscribe";
    public static final String KEY_SUBSCRIBE_TYPE_AUTO = "AUTO";
    public static final String KEY_SUBSCRIBE_TYPE_USER = "FROM_USER";
    private static final String TAG = "EwService";
    private int mCurrentCallState;
    private boolean mFlag = true;
    private PhoneStateListener mPhoneStateListener = new PhoneStateListener() { // from class: com.miui.earthquakewarning.service.EarthquakeWarningService.1
        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int i10, String str) {
            int callState = ((TelephonyManager) EarthquakeWarningService.this.getSystemService(ay.f27518d)).getCallState();
            if (EarthquakeWarningService.this.mCurrentCallState == callState) {
                return;
            }
            if (EarthquakeWarningService.this.mPlaying) {
                if (callState != 0) {
                    NotificationUtil.muteVolume(EarthquakeWarningService.this);
                } else {
                    NotificationUtil.remuteVolume(EarthquakeWarningService.this);
                }
            }
            EarthquakeWarningService.this.mCurrentCallState = callState;
        }
    };
    private boolean mPlaying;
    private SensorManager mSensorManager;
    private TelephonyManager mTelephonyManager;

    /* JADX INFO: Access modifiers changed from: private */
    public void addLogData(String str) {
    }

    private void closeEarthWarning() {
        int previousAreaCode = Utils.getPreviousAreaCode();
        if (previousAreaCode != 0) {
            EarthquakePushLog.logFlow("closeEarthWarning unsetTopicForPush code=" + previousAreaCode);
            EarthquakeWarningManager.getInstance().unsetTopicForPush(this, String.valueOf(previousAreaCode), "MANUAL_CLOSE_SWITCH", 0);
        }
    }

    private void exitEarthquakeWarningService() {
        closeEarthWarning();
        if (Utils.isEarthquakeMonitorOpen()) {
            this.mTelephonyManager.listen(this.mPhoneStateListener, 0);
        } else {
            stopSelf();
        }
    }

    private void getLocation(float[] fArr) {
        EwTranData ewTranData = new EwTranData();
        float fG = e.g(Constants.PREFERENCE_KEY_EARTHQUAKE_WARNING_MONITOR_LOCATION_LAT, 0.0f);
        float fG2 = e.g(Constants.PREFERENCE_KEY_EARTHQUAKE_WARNING_MONITOR_LOCATION_LNG, 0.0f);
        ewTranData.lat = fG;
        ewTranData.lng = fG2;
        ewTranData.time = System.currentTimeMillis();
        ewTranData.f15834id = y.a(this);
        ewTranData.arrays = fArr;
        EarthquakeMonitorDataManager.getInstance().sendData(ewTranData);
        long j10 = e.j("LOCATION_TIME", 0L);
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (this.mFlag) {
            if (jCurrentTimeMillis - j10 > 600000 || Float.compare(fG, 0.0f) == 0) {
                this.mFlag = false;
                addLogData("getLocation start = " + System.currentTimeMillis());
                addLogData("is network connect = " + f.c(this) + " ,time = " + System.currentTimeMillis());
                e.q("LOCATION_TIME", System.currentTimeMillis());
                startLocation();
            }
        }
    }

    private void initSensor() {
        SensorManager sensorManager = (SensorManager) getSystemService("sensor");
        this.mSensorManager = sensorManager;
        Sensor defaultSensor = sensorManager.getDefaultSensor(Constants.EQM_SENSOR_ID, true);
        if (defaultSensor != null) {
            this.mSensorManager.registerListener(this, defaultSensor, 0);
        }
        startLocation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onStartCommand$0(String str, String str2, int i10, String str3) {
        String strF = b1.f(str + Constants.UUID_NEW_CITY_CODE);
        SaveAreaResult saveAreaResultCreatePending = SaveAreaResult.createPending(str, str2, i10, str3, strF, "MANUAL");
        ji.c.y(strF);
        ji.c.j(saveAreaResultCreatePending);
        EarthquakeWarningManager.getInstance().setTopicForPush(this, str, str2, "MANUAL", 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onStartCommand$1(String str, String str2) {
        ji.c.x(b1.f(str + Constants.UUID_NEW_CITY_CODE));
        EarthquakeWarningManager.getInstance().setTopicForPush(this, str, str2, "AUTO_LOCATION", 0);
    }

    private void startLocation() {
        EarthquakeMonitorDataManager.getInstance().startLocationSingleUpdate(new LocationRecordManager.CallBack() { // from class: com.miui.earthquakewarning.service.EarthquakeWarningService.2
            @Override // com.miui.earthquakewarning.utils.LocationRecordManager.CallBack
            public void onLocationFailed(String str) {
                EarthquakeWarningService.this.addLogData("getLocation time = " + System.currentTimeMillis() + ", location failed");
            }

            /* JADX WARN: Code duplicated, block: B:10:0x002a A[Catch: Exception -> 0x0028, TRY_LEAVE, TryCatch #0 {Exception -> 0x0028, blocks: (B:3:0x000d, B:5:0x001a, B:7:0x0021, B:10:0x002a), top: B:16:0x000d }] */
            @Override // com.miui.earthquakewarning.utils.LocationRecordManager.CallBack
            public void onLocationSuccess(Location location) {
                EwTranData ewTranData = new EwTranData();
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                try {
                    double[] dArrB = un.a.b(latitude, longitude);
                    double d10 = dArrB[0];
                    if (d10 != 0.0d) {
                        double d11 = dArrB[1];
                        if (d11 != 0.0d) {
                            ewTranData.lat = (float) d10;
                            ewTranData.lng = (float) d11;
                        } else {
                            ewTranData.lat = (float) latitude;
                            ewTranData.lng = (float) longitude;
                        }
                    } else {
                        ewTranData.lat = (float) latitude;
                        ewTranData.lng = (float) longitude;
                    }
                } catch (Exception e10) {
                    Log.e(EarthquakeWarningService.TAG, "get diff privacy error", e10);
                    ewTranData.lat = (float) latitude;
                    ewTranData.lng = (float) longitude;
                }
                e.o(Constants.PREFERENCE_KEY_EARTHQUAKE_WARNING_MONITOR_LOCATION_LAT, ewTranData.lat);
                e.o(Constants.PREFERENCE_KEY_EARTHQUAKE_WARNING_MONITOR_LOCATION_LNG, ewTranData.lng);
                EarthquakeMonitorDataManager.getInstance().stopLocation();
                EarthquakeWarningService.this.addLogData("getLocation time = " + System.currentTimeMillis() + ", get location already,Latitude =" + location.getLatitude());
            }
        });
        new CountDownTimer(10000L, 1000L) { // from class: com.miui.earthquakewarning.service.EarthquakeWarningService.3
            @Override // android.os.CountDownTimer
            public void onFinish() {
                EarthquakeWarningService.this.mFlag = true;
                EarthquakeWarningService.this.addLogData("getLocation CountDownTimer finish = " + System.currentTimeMillis());
                EarthquakeMonitorDataManager.getInstance().stopLocation();
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j10) {
            }
        }.start();
    }

    public void exitVolunteer() {
        SensorManager sensorManager = this.mSensorManager;
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
        EarthquakeMonitorDataManager.getInstance().release();
        if (Utils.isEarthquakeWarningOpen()) {
            return;
        }
        stopSelf();
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i10) {
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(ay.f27518d);
        this.mTelephonyManager = telephonyManager;
        telephonyManager.listen(this.mPhoneStateListener, 32);
        EarthquakeWarningJobService.schedule(this);
        EarthquakePushLog.init(this);
        Log.i(TAG, "EarthquakeWarningService created");
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        SensorManager sensorManager = this.mSensorManager;
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
        this.mTelephonyManager.listen(this.mPhoneStateListener, 0);
        EarthquakeMonitorDataManager.getInstance().release();
        EarthquakeWarningJobService.cancel(this);
        Log.w(TAG, "EarthquakeWarningService destroyed");
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] fArr = (float[]) sensorEvent.values.clone();
        if (fArr.length <= 0) {
            addLogData("eqw_onSensorChanged time = " + System.currentTimeMillis() + ", values = null");
            return;
        }
        Log.d(TAG, "receive data: " + Arrays.toString(fArr));
        addLogData("eqw_onSensorChanged time = " + System.currentTimeMillis() + ", values = " + Arrays.toString(fArr));
        if (pr.a.f40580a || !TextUtils.equals(this.mTelephonyManager.getNetworkCountryIso(), "cn")) {
            Log.i(TAG, "onSensorChanged: no execute!!!");
            return;
        }
        if (g.z(this) && g.p(this)) {
            u2.a();
        }
        getLocation(fArr);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i10, int i11) {
        final EarthquakeWarningService earthquakeWarningService;
        Log.i(TAG, "EarthquakeWarningService onStartCommand");
        if (intent == null || TextUtils.isEmpty(intent.getAction())) {
            if (Utils.isNeedRestore()) {
                RestoreHelper.restore(this);
                Utils.needRestore(false);
            }
            return super.onStartCommand(intent, i10, i11);
        }
        if ("updatePlayingStatus".equals(intent.getAction())) {
            setPlaying(intent.getBooleanExtra("playing", false));
        }
        if ("action_subscribe_area".equals(intent.getAction())) {
            final String stringExtra = intent.getStringExtra("extra_area_code");
            final String stringExtra2 = intent.getStringExtra("extra_area_region");
            final int intExtra = intent.getIntExtra(EXTRA_AREA_SUPPORT, 1);
            final String stringExtra3 = intent.getStringExtra(EXTRA_AREA_COUNTRIES);
            if (intent.getBooleanExtra(EXTRA_USER_SUBSCRIBE, false)) {
                earthquakeWarningService = this;
                b7.a.b(new Runnable() { // from class: com.miui.earthquakewarning.service.c
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f15837a.lambda$onStartCommand$0(stringExtra, stringExtra2, intExtra, stringExtra3);
                    }
                });
            } else {
                earthquakeWarningService = this;
                b7.a.b(new Runnable() { // from class: com.miui.earthquakewarning.service.d
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f15842a.lambda$onStartCommand$1(stringExtra, stringExtra2);
                    }
                });
            }
        } else {
            earthquakeWarningService = this;
        }
        if (ACTION_UNSUBSCRIBE_AREA.equals(intent.getAction())) {
            String stringExtra4 = intent.getStringExtra("extra_area_code");
            boolean booleanExtra = intent.getBooleanExtra(EXTRA_USER_SUBSCRIBE, true);
            String strF = b1.f(stringExtra4 + Constants.UUID_NEW_CITY_CODE);
            if (booleanExtra) {
                if (ji.c.c(strF)) {
                    EarthquakeWarningManager.getInstance().unsetTopicForPush(this, stringExtra4, "MANUAL_DELETE_CITY", 1);
                } else {
                    EarthquakePushLog.logFlow("unsubscribe: TYPE_BOTH→TYPE_AUTO, keep SDK sub. code=" + stringExtra4);
                }
            } else if (ji.c.b(strF)) {
                EarthquakeWarningManager.getInstance().unsetTopicForPush(this, stringExtra4, "AUTO_LOCATION_CHANGE", 0);
            } else {
                EarthquakePushLog.logFlow("unsubscribe: downgradeAuto skipped real unsub. code=" + stringExtra4);
            }
        }
        if (ACTION_RESTORE_DATA.equals(intent.getAction()) && Utils.isNeedRestore()) {
            RestoreHelper.restore(this);
            Utils.needRestore(false);
        }
        if (ACTION_JOIN_VOLUNTEER.equals(intent.getAction())) {
            initSensor();
        }
        if (ACTION_EXIT_VOLUNTEER.equals(intent.getAction())) {
            exitVolunteer();
        }
        if (ACTION_EXIT_EARTHQUAKEWARNING.equals(intent.getAction())) {
            exitEarthquakeWarningService();
        }
        if (ACTION_CLOSE_WARNING.equals(intent.getAction())) {
            closeEarthWarning();
            EarthquakeWarningManager.getInstance().closeEarthquakeWarning(this);
        }
        if (ACTION_OPEN_WARNING.equals(intent.getAction())) {
            UpdateAreaCodeManager.getInstance().uploadSettings(this);
        }
        earthquakeWarningService.mCurrentCallState = earthquakeWarningService.mTelephonyManager.getCallState();
        return super.onStartCommand(intent, i10, i11);
    }

    public void setPlaying(boolean z10) {
        this.mPlaying = z10;
    }
}
