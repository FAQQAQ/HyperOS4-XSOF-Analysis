package com.miui.earthquakewarning.service;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import com.miui.earthquakewarning.log.EarthquakePushLog;
import com.miui.earthquakewarning.model.SaveAreaResult;
import com.miui.earthquakewarning.utils.Utils;
import com.miui.securitycenter.Application;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ManageAreaDataTask extends AsyncTask<String, Void, List<SaveAreaResult>> {
    private static final String AUTHORITY = "com.miui.earthquakewarning.EarthquakeContentProvider2";
    private static final Uri EARTHQUAKE_URI = Uri.parse("content://com.miui.earthquakewarning.EarthquakeContentProvider2/area");
    private static final String TAG = "ManageAreaDataTask";
    public static final int TYPE_DELETE = 2;
    public static final int TYPE_INSERT = 1;
    public static final int TYPE_QUERY = 3;
    public static final int TYPE_UPDATE = 4;
    private CallBack callBack;
    private List<SaveAreaResult> mAreaResult;
    private String mOpearteString;
    private int mType;

    public interface CallBack {
        void onSuccess(List<SaveAreaResult> list);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public ManageAreaDataTask(int i10) {
        this.mType = i10;
    }

    private void deleteData() {
        Application.B().getContentResolver().delete(EARTHQUAKE_URI, null, null);
    }

    private void insertArea() {
        if (this.mAreaResult != null) {
            for (int i10 = 0; i10 < this.mAreaResult.size(); i10++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("province", this.mAreaResult.get(i10).getProvince());
                contentValues.put("code", this.mAreaResult.get(i10).getCode());
                contentValues.put("city", this.mAreaResult.get(i10).getCity());
                contentValues.put(SaveAreaResult.Columns.support, Integer.valueOf(this.mAreaResult.get(i10).getSupport()));
                contentValues.put(SaveAreaResult.Columns.counties, this.mAreaResult.get(i10).getCounties());
                Application.B().getContentResolver().insert(EARTHQUAKE_URI, contentValues);
            }
        }
    }

    private List<SaveAreaResult> queryData(String str) {
        String str2;
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            str2 = null;
        } else {
            str2 = "province LIKE '%" + str + "%' OR city LIKE '%" + str + "%' OR counties LIKE '%" + str + "%'";
        }
        Cursor cursorQuery = Application.B().getContentResolver().query(EARTHQUAKE_URI, new String[]{"_id", "province", "code", "city", SaveAreaResult.Columns.support, SaveAreaResult.Columns.counties}, str2, null, null);
        if (cursorQuery != null) {
            cursorQuery.moveToFirst();
            do {
                SaveAreaResult saveAreaResult = new SaveAreaResult();
                saveAreaResult.setProvince(cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("province")));
                saveAreaResult.setCode(cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("code")));
                saveAreaResult.setCity(cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("city")));
                saveAreaResult.setSupport(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow(SaveAreaResult.Columns.support)));
                saveAreaResult.setCounties(cursorQuery.getString(cursorQuery.getColumnIndexOrThrow(SaveAreaResult.Columns.counties)));
                arrayList.add(saveAreaResult);
            } while (cursorQuery.moveToNext());
            cursorQuery.close();
        }
        return arrayList;
    }

    public void setAreaList(List<SaveAreaResult> list) {
        this.mAreaResult = list;
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
            if (i10 != 1) {
                return i10 == 3 ? queryData(this.mOpearteString) : arrayList;
            }
            deleteData();
            insertArea();
            Utils.setNeedToForceUpdateArea(false);
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
            EarthquakePushLog.logWarningResult(false, 17, "ManageAreaDataTask callback is null");
        }
    }

    public ManageAreaDataTask(int i10, String str) {
        this.mType = i10;
        this.mOpearteString = str;
    }
}
