package com.miui.earthquakewarning.service;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import com.miui.earthquakewarning.log.EarthquakePushLog;
import com.miui.earthquakewarning.model.SaveAreaResult;
import com.xiaomi.onetrack.util.aa;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ManageSubscribeDataTask extends AsyncTask<String, Void, List<SaveAreaResult>> {
    private static final Uri EARTHQUAKE_URI = ji.c.f33434a;
    private static final String TAG = "ManageAreaDataTask";
    public static final int TYPE_DELETE = 2;
    public static final int TYPE_INSERT = 1;
    public static final int TYPE_QUERY = 3;
    public static final int TYPE_QUERY_VISIBLE = 5;
    public static final int TYPE_UPDATE = 4;
    private CallBack callBack;
    private Context mContext;
    private SaveAreaResult mSaveAreaModel;
    private int mType;

    public interface CallBack {
        void onSuccess(List<SaveAreaResult> list);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public ManageSubscribeDataTask(int i10) {
        this.mType = i10;
    }

    private void deleteData(SaveAreaResult saveAreaResult) {
        this.mContext.getContentResolver().delete(EARTHQUAKE_URI, "code = ?", new String[]{String.valueOf(saveAreaResult.getCode())});
    }

    private void insertData(SaveAreaResult saveAreaResult) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("code", saveAreaResult.getCode());
        contentValues.put("city", saveAreaResult.getCity());
        contentValues.put(SaveAreaResult.Columns.support, Integer.valueOf(saveAreaResult.getSupport()));
        contentValues.put(SaveAreaResult.Columns.counties, saveAreaResult.getCounties());
        contentValues.put(SaveAreaResult.Columns.subscribeTime, Long.valueOf(System.currentTimeMillis()));
        contentValues.put(SaveAreaResult.Columns.topic, saveAreaResult.getTopic());
        contentValues.put("state", saveAreaResult.getState());
        contentValues.put(SaveAreaResult.Columns.source, saveAreaResult.getSource());
        contentValues.put(SaveAreaResult.Columns.pushTime, Long.valueOf(saveAreaResult.getPushTime()));
        contentValues.put(SaveAreaResult.Columns.retryCount, Integer.valueOf(saveAreaResult.getRetryCount()));
        this.mContext.getContentResolver().insert(EARTHQUAKE_URI, contentValues);
    }

    private List<SaveAreaResult> queryData() {
        return ji.c.r();
    }

    private List<SaveAreaResult> queryDataVisible() {
        return ji.c.u();
    }

    private void updateData(SaveAreaResult saveAreaResult) {
        for (String str : saveAreaResult.getCityCode().split(aa.f28090b)) {
            if (!TextUtils.isEmpty(str)) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("updateTime", Long.valueOf(saveAreaResult.getUpdateTime()));
                this.mContext.getContentResolver().update(EARTHQUAKE_URI, contentValues, "code = ?", new String[]{str});
            }
        }
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
    /* JADX DEBUG: Method merged with bridge method: doInBackground([Ljava/lang/Object;)Ljava/lang/Object; */
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public List<SaveAreaResult> doInBackground(String... strArr) {
        ArrayList arrayList = new ArrayList();
        try {
            int i10 = this.mType;
            if (i10 == 1) {
                insertData(this.mSaveAreaModel);
                return arrayList;
            }
            if (i10 == 3) {
                return queryData();
            }
            if (i10 == 5) {
                return queryDataVisible();
            }
            if (i10 == 2) {
                deleteData(this.mSaveAreaModel);
                return arrayList;
            }
            if (i10 == 4) {
                updateData(this.mSaveAreaModel);
            }
            return arrayList;
        } catch (Exception e10) {
            Log.e(TAG, "insert data error", e10);
            return arrayList;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
    /* JADX DEBUG: Method merged with bridge method: onPostExecute(Ljava/lang/Object;)V */
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(List<SaveAreaResult> list) {
        super.onPostExecute(list);
        CallBack callBack = this.callBack;
        if (callBack != null) {
            callBack.onSuccess(list);
        } else if (this.mType == 3) {
            EarthquakePushLog.logWarningResult(false, 17, "ManageSubscribeDataTask callback is null");
        }
    }

    public ManageSubscribeDataTask(Context context, int i10, SaveAreaResult saveAreaResult) {
        this.mContext = context;
        this.mType = i10;
        this.mSaveAreaModel = saveAreaResult;
    }
}
