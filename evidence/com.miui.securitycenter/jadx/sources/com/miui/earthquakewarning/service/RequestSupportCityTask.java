package com.miui.earthquakewarning.service;

import aj.z;
import android.os.AsyncTask;
import android.util.Log;
import com.miui.earthquakewarning.Constants;
import com.miui.earthquakewarning.model.SaveAreaResult;
import gk.l;
import j7.i;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class RequestSupportCityTask extends AsyncTask<String, Void, List<SaveAreaResult>> {
    private static final String TAG = "RequestSupportCityTask";
    private Listener listener;

    public interface Listener {
        void onPost(List<SaveAreaResult> list);
    }

    private List<SaveAreaResult> parse(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt("code", -1) == 0) {
                JSONArray jSONArray = jSONObject.getJSONArray("data");
                for (int i10 = 0; i10 < jSONArray.length(); i10++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i10);
                    String strOptString = jSONObject2.optString("province");
                    JSONArray jSONArray2 = jSONObject2.getJSONArray("cityList");
                    for (int i11 = 0; i11 < jSONArray2.length(); i11++) {
                        JSONObject jSONObject3 = jSONArray2.getJSONObject(i11);
                        SaveAreaResult saveAreaResult = new SaveAreaResult();
                        saveAreaResult.setProvince(strOptString);
                        saveAreaResult.setCity(jSONObject3.optString("name"));
                        saveAreaResult.setCode(String.valueOf(jSONObject3.optInt("topicCode")));
                        saveAreaResult.setSupport(jSONObject3.optInt(SaveAreaResult.Columns.support));
                        saveAreaResult.setCounties(jSONObject3.optString(SaveAreaResult.Columns.counties));
                        arrayList.add(saveAreaResult);
                    }
                }
            }
            return arrayList;
        } catch (Exception unused) {
            Log.e(TAG, "parse json failed");
            return arrayList;
        }
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
    /* JADX DEBUG: Method merged with bridge method: doInBackground([Ljava/lang/Object;)Ljava/lang/Object; */
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public List<SaveAreaResult> doInBackground(String... strArr) {
        if (!z.D()) {
            return new ArrayList();
        }
        HashMap map = new HashMap();
        map.put("version", "2");
        return parse(l.C(map, Constants.REQUEST_SUPPORT_CITY_URL, "7htr5238-a8cf-3k79-ec73-75382145ns5c", new i("ew_requestsupportcity")));
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
    /* JADX DEBUG: Method merged with bridge method: onPostExecute(Ljava/lang/Object;)V */
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(List<SaveAreaResult> list) {
        ManageAreaDataTask manageAreaDataTask = new ManageAreaDataTask(1);
        manageAreaDataTask.setAreaList(list);
        manageAreaDataTask.execute(new String[0]);
        Listener listener = this.listener;
        if (listener != null) {
            listener.onPost(list);
        }
    }
}
