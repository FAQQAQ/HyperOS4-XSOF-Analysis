package com.miui.earthquakewarning.service;

import aj.z;
import android.os.AsyncTask;
import android.util.Log;
import com.miui.earthquakewarning.Constants;
import com.miui.earthquakewarning.model.WarningModel;
import com.miui.earthquakewarning.model.WarningResult;
import gk.l;
import j7.i;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class RequestWarningListTask extends AsyncTask<String, Void, WarningResult> {
    private static final String TAG = "RequestWarningListTask";
    private Listener listener;

    public interface Listener {
        void onPost(WarningResult warningResult);
    }

    private WarningResult parse(String str) {
        WarningResult warningResult = new WarningResult();
        try {
            JSONObject jSONObject = new JSONObject(str);
            warningResult.setCode(jSONObject.optInt("code"));
            JSONArray jSONArray = jSONObject.getJSONArray("data");
            ArrayList arrayList = new ArrayList();
            for (int i10 = 0; i10 < jSONArray.length(); i10++) {
                WarningModel warningModel = new WarningModel();
                JSONObject jSONObject2 = jSONArray.getJSONObject(i10);
                warningModel.eventID = jSONObject2.optInt("event_id");
                warningModel.latitude = Double.parseDouble(jSONObject2.optString(WarningModel.Columns.LATITUDE));
                warningModel.longitude = Double.parseDouble(jSONObject2.optString(WarningModel.Columns.LONGITUDE));
                warningModel.magnitude = Float.parseFloat(jSONObject2.optString(WarningModel.Columns.MAGNITUDE));
                warningModel.depth = jSONObject2.optInt(WarningModel.Columns.DEPTH);
                warningModel.signature = jSONObject2.optString(WarningModel.Columns.SIGNATURE);
                warningModel.startTime = jSONObject2.optLong("startAt");
                warningModel.epicenter = jSONObject2.optString(WarningModel.Columns.EPICENTER);
                arrayList.add(warningModel);
            }
            warningResult.setData(arrayList);
            return warningResult;
        } catch (Exception unused) {
            Log.e(TAG, "parse json failed");
            return warningResult;
        }
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
    /* JADX DEBUG: Method merged with bridge method: doInBackground([Ljava/lang/Object;)Ljava/lang/Object; */
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public WarningResult doInBackground(String... strArr) {
        if (!z.D()) {
            return null;
        }
        HashMap map = new HashMap();
        map.put("version", "2");
        return parse(l.C(map, Constants.REQUEST_WARNLIST_URL, "7htr5238-a8cf-3k79-ec73-75382145ns5c", new i("ew_requestwarninglist")));
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
    /* JADX DEBUG: Method merged with bridge method: onPostExecute(Ljava/lang/Object;)V */
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(WarningResult warningResult) {
        Listener listener = this.listener;
        if (listener != null) {
            listener.onPost(warningResult);
            this.listener = null;
        }
    }
}
