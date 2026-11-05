package com.miui.earthquakewarning.service;

import aj.z;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import com.miui.earthquakewarning.Constants;
import com.miui.earthquakewarning.log.EarthquakePushLog;
import com.miui.earthquakewarning.model.AreaCodeResult;
import com.miui.earthquakewarning.model.SaveAreaResult;
import com.miui.earthquakewarning.model.WarningModel;
import com.miui.earthquakewarning.utils.Utils;
import com.miui.securitycenter.Application;
import gk.l;
import j7.i;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class RequestAreaCodeTask extends AsyncTask<String, Void, AreaCodeResult> {
    private static final String TAG = "RequestAreaCodeTask";
    private String latitude;
    private Listener listener;
    private String longitude;

    public interface Listener {
        void onPost(AreaCodeResult areaCodeResult);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDistrictSupport(String str, String str2) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i10 = 0; i10 < jSONArray.length(); i10++) {
                JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i10);
                String strOptString = jSONObjectOptJSONObject.optString("geocode", "");
                int iOptInt = jSONObjectOptJSONObject.optInt(SaveAreaResult.Columns.support, 0);
                if (strOptString.equals(str2) && iOptInt == 1) {
                    return true;
                }
            }
        } catch (Exception e10) {
            e10.printStackTrace();
        }
        return false;
    }

    private void matchMyPosition(Context context, final AreaCodeResult areaCodeResult) {
        ManageAreaDataTask manageAreaDataTask = new ManageAreaDataTask(3, null);
        manageAreaDataTask.setCallBack(new ManageAreaDataTask.CallBack() { // from class: com.miui.earthquakewarning.service.RequestAreaCodeTask.1
            @Override // com.miui.earthquakewarning.service.ManageAreaDataTask.CallBack
            public void onSuccess(List<SaveAreaResult> list) {
                int cityId = areaCodeResult.getData().getCityId();
                int districtId = areaCodeResult.getData().getDistrictId();
                for (int i10 = 0; i10 < list.size(); i10++) {
                    String code = list.get(i10).getCode();
                    if (String.valueOf(cityId).equals(code)) {
                        String counties = list.get(i10).getCounties();
                        if (String.valueOf(districtId).equals(code) || (!TextUtils.isEmpty(counties) && RequestAreaCodeTask.this.isDistrictSupport(counties, String.valueOf(districtId)))) {
                            Utils.setPreviousAreaCode(cityId);
                            Utils.setPreviousAreaCityName(areaCodeResult.getData().getCity());
                            Utils.setPreviousAreaDistrictCode(areaCodeResult.getData().getDistrictId());
                            Intent intent = new Intent(Application.B(), (Class<?>) EarthquakeWarningService.class);
                            intent.setAction("action_subscribe_area");
                            intent.putExtra("extra_area_code", String.valueOf(cityId));
                            intent.putExtra("extra_area_region", areaCodeResult.getData().getCity());
                            Application.B().startService(intent);
                            return;
                        }
                    }
                }
            }
        });
        manageAreaDataTask.execute(new String[0]);
    }

    private AreaCodeResult parse(String str) {
        AreaCodeResult areaCodeResult = new AreaCodeResult();
        try {
            JSONObject jSONObject = new JSONObject(str);
            areaCodeResult.setErr(jSONObject.optBoolean("err"));
            JSONObject jSONObject2 = new JSONObject(jSONObject.optString("data"));
            AreaCodeResult.DataBean dataBean = new AreaCodeResult.DataBean();
            dataBean.setCityId(jSONObject2.optInt("cityId"));
            dataBean.setCity(jSONObject2.optString("city"));
            dataBean.setDistrictId(jSONObject2.optInt("districtId"));
            dataBean.setDistrict(jSONObject2.optString("district"));
            areaCodeResult.setData(dataBean);
            return areaCodeResult;
        } catch (Exception unused) {
            Log.e(TAG, "parse json failed");
            return areaCodeResult;
        }
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
    /* JADX DEBUG: Method merged with bridge method: doInBackground([Ljava/lang/Object;)Ljava/lang/Object; */
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public AreaCodeResult doInBackground(String... strArr) {
        if (!z.D()) {
            return null;
        }
        HashMap map = new HashMap();
        String str = strArr[0];
        this.longitude = str;
        this.latitude = strArr[1];
        map.put(WarningModel.Columns.LONGITUDE, str);
        map.put(WarningModel.Columns.LATITUDE, this.latitude);
        return parse(l.C(map, Constants.REQUEST_AREA_CODE_URL, "7htr5238-a8cf-3k79-ec73-75382145ns5c", new i("ew_requestareacode")));
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
    /* JADX DEBUG: Method merged with bridge method: onPostExecute(Ljava/lang/Object;)V */
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(AreaCodeResult areaCodeResult) {
        if (areaCodeResult == null || areaCodeResult.isErr() || areaCodeResult.getData() == null) {
            return;
        }
        Listener listener = this.listener;
        if (listener != null) {
            listener.onPost(areaCodeResult);
        } else {
            int cityId = areaCodeResult.getData().getCityId() > 0 ? areaCodeResult.getData().getCityId() : 0;
            if (cityId > 0) {
                int previousAreaCode = Utils.getPreviousAreaCode();
                if (previousAreaCode > 0 && previousAreaCode != cityId) {
                    Intent intent = new Intent(Application.B(), (Class<?>) EarthquakeWarningService.class);
                    intent.setAction(EarthquakeWarningService.ACTION_UNSUBSCRIBE_AREA);
                    intent.putExtra("extra_area_code", String.valueOf(previousAreaCode));
                    intent.putExtra(EarthquakeWarningService.EXTRA_USER_SUBSCRIBE, false);
                    Application.B().startService(intent);
                    EarthquakePushLog.logFlow("RequestAreaCodeTask: location changed " + previousAreaCode + " → " + cityId + ", delegate unsub to Service");
                }
                matchMyPosition(Application.B(), areaCodeResult);
            }
        }
        new RequestAreaCodeTaskForNoisyLocation().execute(this.longitude, this.latitude, areaCodeResult.getData().getCity() + areaCodeResult.getData().getDistrict());
    }
}
