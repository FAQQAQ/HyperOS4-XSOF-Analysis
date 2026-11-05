package com.miui.earthquakewarning.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import com.miui.earthquakewarning.model.SaveAreaResult;
import com.miui.earthquakewarning.model.UserQuakeItem;
import com.miui.earthquakewarning.model.WarningModel;
import com.xiaomi.onetrack.util.aa;

/* JADX INFO: loaded from: classes2.dex */
public class ManageDataTask extends AsyncTask<String, Void, Boolean> {
    private static final String AUTHORITY = "com.miui.earthquakewarning.EarthquakeContentProvider2";
    private static final Uri EARTHQUAKE_URI = Uri.parse("content://com.miui.earthquakewarning.EarthquakeContentProvider2/earthquake");
    private static final String TAG = "ManageDataTask";
    private Context context;
    private UserQuakeItem userQuakeItem;

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public ManageDataTask(Context context, UserQuakeItem userQuakeItem) {
        this.context = context;
        this.userQuakeItem = userQuakeItem;
    }

    private void insertEarthquake(Context context, UserQuakeItem userQuakeItem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(WarningModel.Columns.EVENTID, Long.valueOf(userQuakeItem.getEventID()));
        contentValues.put(WarningModel.Columns.INDEX_EW, Integer.valueOf(userQuakeItem.getIndex()));
        contentValues.put(WarningModel.Columns.MAGNITUDE, Float.valueOf(userQuakeItem.getMagnitude()));
        contentValues.put(WarningModel.Columns.LONGITUDE, Double.valueOf(userQuakeItem.getEpiLocation().getLongitude()));
        contentValues.put(WarningModel.Columns.LATITUDE, Double.valueOf(userQuakeItem.getEpiLocation().getLatitude()));
        contentValues.put(WarningModel.Columns.MYLONGITUDE, Double.valueOf(userQuakeItem.getLocation().getLongitude()));
        contentValues.put(WarningModel.Columns.MYLATITUDE, Double.valueOf(userQuakeItem.getLocation().getLatitude()));
        contentValues.put(WarningModel.Columns.DISTANCE, String.valueOf(userQuakeItem.getDistance()));
        contentValues.put(WarningModel.Columns.INTENSITY, Float.valueOf(userQuakeItem.getIntensity()));
        contentValues.put(WarningModel.Columns.EPICENTER, userQuakeItem.getEpiLocation().getPlace());
        contentValues.put("startTime", Long.valueOf(userQuakeItem.getStartTime()));
        contentValues.put(WarningModel.Columns.SIGNATURE, userQuakeItem.getSignatureText());
        contentValues.put("updateTime", Long.valueOf(userQuakeItem.getUpdateTime()));
        contentValues.put("type", Integer.valueOf(userQuakeItem.getType()));
        contentValues.put(WarningModel.Columns.WARNTIME, Integer.valueOf(userQuakeItem.getCountdown()));
        contentValues.put(WarningModel.Columns.CITYCODE, userQuakeItem.getCityCode());
        contentValues.put("subscribe", Integer.valueOf(userQuakeItem.getSubscribe()));
        contentValues.put(WarningModel.Columns.IS_MY_CITY, Integer.valueOf(userQuakeItem.getIsMyCity()));
        context.getContentResolver().insert(EARTHQUAKE_URI, contentValues);
    }

    private boolean isSameEarthQuake(UserQuakeItem userQuakeItem, WarningModel warningModel) {
        return userQuakeItem != null && warningModel != null && userQuakeItem.getEventID() == warningModel.eventID && userQuakeItem.getIndex() == warningModel.index_ew;
    }

    private WarningModel queryData(Context context, long j10) {
        WarningModel warningModel = new WarningModel();
        Cursor cursorQuery = context.getContentResolver().query(EARTHQUAKE_URI, new String[]{"_id", WarningModel.Columns.EVENTID, WarningModel.Columns.INDEX_EW, WarningModel.Columns.MAGNITUDE, WarningModel.Columns.LONGITUDE, WarningModel.Columns.LATITUDE, WarningModel.Columns.MYLONGITUDE, WarningModel.Columns.MYLATITUDE, WarningModel.Columns.EPICENTER, "startTime", WarningModel.Columns.SIGNATURE, WarningModel.Columns.DISTANCE, WarningModel.Columns.INTENSITY, WarningModel.Columns.WARNTIME, "subscribe", WarningModel.Columns.CITYCODE, WarningModel.Columns.IS_MY_CITY}, null, null, "startTime desc");
        while (cursorQuery.moveToNext()) {
            if (cursorQuery.getLong(cursorQuery.getColumnIndex(WarningModel.Columns.EVENTID)) == j10) {
                warningModel.eventID = cursorQuery.getLong(cursorQuery.getColumnIndex(WarningModel.Columns.EVENTID));
                warningModel.index_ew = cursorQuery.getInt(cursorQuery.getColumnIndex(WarningModel.Columns.INDEX_EW));
                warningModel.magnitude = cursorQuery.getFloat(cursorQuery.getColumnIndex(WarningModel.Columns.MAGNITUDE));
                warningModel.longitude = cursorQuery.getDouble(cursorQuery.getColumnIndex(WarningModel.Columns.LONGITUDE));
                warningModel.latitude = cursorQuery.getDouble(cursorQuery.getColumnIndex(WarningModel.Columns.LATITUDE));
                warningModel.myLongitude = cursorQuery.getDouble(cursorQuery.getColumnIndex(WarningModel.Columns.MYLONGITUDE));
                warningModel.myLatitude = cursorQuery.getDouble(cursorQuery.getColumnIndex(WarningModel.Columns.MYLATITUDE));
                warningModel.epicenter = cursorQuery.getString(cursorQuery.getColumnIndex(WarningModel.Columns.EPICENTER));
                warningModel.startTime = cursorQuery.getLong(cursorQuery.getColumnIndex("startTime"));
                warningModel.signature = cursorQuery.getString(cursorQuery.getColumnIndex(WarningModel.Columns.SIGNATURE));
                warningModel.distance = cursorQuery.getDouble(cursorQuery.getColumnIndex(WarningModel.Columns.DISTANCE));
                warningModel.intensity = cursorQuery.getFloat(cursorQuery.getColumnIndex(WarningModel.Columns.INTENSITY));
                warningModel.warnTime = cursorQuery.getInt(cursorQuery.getColumnIndex(WarningModel.Columns.WARNTIME));
                warningModel.subscribe = cursorQuery.getInt(cursorQuery.getColumnIndex("subscribe"));
                warningModel.cityCode = cursorQuery.getString(cursorQuery.getColumnIndex(WarningModel.Columns.CITYCODE));
                warningModel.isMyCity = cursorQuery.getInt(cursorQuery.getColumnIndex(WarningModel.Columns.IS_MY_CITY));
                return warningModel;
            }
        }
        return null;
    }

    private void updateEarthquake(Context context, UserQuakeItem userQuakeItem, WarningModel warningModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(WarningModel.Columns.INDEX_EW, Integer.valueOf(userQuakeItem.getIndex()));
        contentValues.put(WarningModel.Columns.MAGNITUDE, Float.valueOf(userQuakeItem.getMagnitude()));
        contentValues.put(WarningModel.Columns.LONGITUDE, Double.valueOf(userQuakeItem.getEpiLocation().getLongitude()));
        contentValues.put(WarningModel.Columns.LATITUDE, Double.valueOf(userQuakeItem.getEpiLocation().getLatitude()));
        if (warningModel.myLongitude <= 0.0d || userQuakeItem.getLocation().getLongitude() > 0.0d) {
            contentValues.put(WarningModel.Columns.MYLONGITUDE, Double.valueOf(userQuakeItem.getLocation().getLongitude()));
            contentValues.put(WarningModel.Columns.MYLATITUDE, Double.valueOf(userQuakeItem.getLocation().getLatitude()));
            contentValues.put(WarningModel.Columns.DISTANCE, String.valueOf(userQuakeItem.getDistance()));
            contentValues.put(WarningModel.Columns.INTENSITY, Float.valueOf(userQuakeItem.getIntensity()));
        }
        contentValues.put(WarningModel.Columns.EPICENTER, userQuakeItem.getEpiLocation().getPlace());
        contentValues.put("startTime", Long.valueOf(userQuakeItem.getStartTime()));
        contentValues.put(WarningModel.Columns.SIGNATURE, userQuakeItem.getSignatureText());
        contentValues.put("updateTime", Long.valueOf(userQuakeItem.getUpdateTime()));
        contentValues.put("type", Integer.valueOf(userQuakeItem.getType()));
        if (warningModel.warnTime < 1) {
            contentValues.put(WarningModel.Columns.WARNTIME, Integer.valueOf(userQuakeItem.getCountdown()));
        }
        contentValues.put(WarningModel.Columns.CITYCODE, warningModel.cityCode + aa.f28090b + userQuakeItem.getCityCode());
        if (warningModel.subscribe == 0) {
            contentValues.put("subscribe", Integer.valueOf(userQuakeItem.getSubscribe()));
        }
        if (warningModel.isMyCity == 0) {
            contentValues.put(WarningModel.Columns.IS_MY_CITY, Integer.valueOf(userQuakeItem.getIsMyCity()));
        }
        context.getContentResolver().update(EARTHQUAKE_URI, contentValues, "eventID = ? AND startTime = ?", new String[]{String.valueOf(userQuakeItem.getEventID()), String.valueOf(userQuakeItem.getStartTime())});
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
    /* JADX DEBUG: Method merged with bridge method: doInBackground([Ljava/lang/Object;)Ljava/lang/Object; */
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Boolean doInBackground(String... strArr) {
        try {
            WarningModel warningModelQueryData = queryData(this.context, this.userQuakeItem.getEventID());
            if (isSameEarthQuake(this.userQuakeItem, warningModelQueryData)) {
                updateEarthquake(this.context, this.userQuakeItem, warningModelQueryData);
            } else {
                insertEarthquake(this.context, this.userQuakeItem);
            }
        } catch (Exception e10) {
            Log.e(TAG, "insert data error", e10);
        }
        return Boolean.TRUE;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
    /* JADX DEBUG: Method merged with bridge method: onPostExecute(Ljava/lang/Object;)V */
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Boolean bool) {
        super.onPostExecute(bool);
        if (TextUtils.isEmpty(this.userQuakeItem.getCityCode())) {
            return;
        }
        SaveAreaResult saveAreaResult = new SaveAreaResult();
        saveAreaResult.setUpdateTime(this.userQuakeItem.getStartTime());
        saveAreaResult.setCityCode(this.userQuakeItem.getCityCode());
        new ManageSubscribeDataTask(this.context, 4, saveAreaResult).execute(new String[0]);
    }

    public ManageDataTask(Context context, UserQuakeItem userQuakeItem, boolean z10) {
        this.context = context;
        this.userQuakeItem = userQuakeItem;
    }
}
