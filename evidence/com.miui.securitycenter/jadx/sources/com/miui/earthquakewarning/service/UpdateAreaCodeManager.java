package com.miui.earthquakewarning.service;

import android.content.Context;
import android.location.Address;
import android.util.Log;
import com.miui.earthquakewarning.log.EarthquakePushLog;
import com.miui.earthquakewarning.utils.LocationUtils;
import com.miui.earthquakewarning.utils.Utils;

/* JADX INFO: loaded from: classes2.dex */
public class UpdateAreaCodeManager {
    public static final String TAG = "UpdateAreaCodeManager";
    public static final long UPLOAD_TOPIC_INTERVAL = 21600000;
    private static volatile UpdateAreaCodeManager instance;

    private UpdateAreaCodeManager() {
    }

    public static UpdateAreaCodeManager getInstance() {
        if (instance == null) {
            synchronized (UpdateAreaCodeManager.class) {
                try {
                    if (instance == null) {
                        instance = new UpdateAreaCodeManager();
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        }
        return instance;
    }

    public void uploadSettings(Context context) {
        try {
            if (Utils.isEarthquakeWarningOpen()) {
                LocationUtils.getAdminAreaLocation3(context, new LocationUtils.AreaResultListener() { // from class: com.miui.earthquakewarning.service.UpdateAreaCodeManager.1
                    @Override // com.miui.earthquakewarning.utils.LocationUtils.AreaResultListener
                    public void areaFail() {
                        EarthquakePushLog.logFlow("uploadSettings.Failed");
                    }

                    @Override // com.miui.earthquakewarning.utils.LocationUtils.AreaResultListener
                    public void areaSuccess(Address address) {
                        EarthquakePushLog.logFlow("uploadSettings.locationSuccess");
                        address.getLocality();
                        Utils.setPreviousDistrict(address.getLocality());
                        new RequestAreaCodeTask().execute(String.valueOf(address.getLongitude()), String.valueOf(address.getLatitude()));
                    }
                });
            }
        } catch (Exception e10) {
            Log.d(TAG, e10.toString());
        }
    }
}
