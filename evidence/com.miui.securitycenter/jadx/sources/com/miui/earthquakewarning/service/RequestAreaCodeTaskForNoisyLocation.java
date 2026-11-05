package com.miui.earthquakewarning.service;

import aj.z;
import android.os.AsyncTask;
import android.util.Log;
import com.google.privacy.differentialprivacy.LaplaceNoise;
import com.miui.earthquakewarning.Constants;
import com.miui.earthquakewarning.analytics.AnalyticHelper;
import com.miui.earthquakewarning.model.AreaCodeResult;
import com.miui.earthquakewarning.model.WarningModel;
import gk.l;
import j7.i;
import java.util.HashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class RequestAreaCodeTaskForNoisyLocation extends AsyncTask<String, Void, String[]> {
    private static final String TAG = "RequestAreaCodeTaskForNoisyLocation";

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

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
    /* JADX DEBUG: Method merged with bridge method: doInBackground([Ljava/lang/Object;)Ljava/lang/Object; */
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String[] doInBackground(String... strArr) {
        if (!z.D()) {
            return null;
        }
        HashMap map = new HashMap();
        double d10 = Double.parseDouble(strArr[0]);
        double d11 = Double.parseDouble(strArr[1]);
        String str = strArr[2];
        double dCos = 1.0d / (Math.cos(Math.toRadians(d11)) * 111.0d);
        LaplaceNoise laplaceNoise = new LaplaceNoise();
        double dAddNoise = laplaceNoise.addNoise(d11, 1, 0.009009009009009009d, 1.0d, (Double) null);
        double dAddNoise2 = laplaceNoise.addNoise(d10, 1, dCos, 1.0d, (Double) null);
        double dMax = Math.max(-90.0d, Math.min(90.0d, dAddNoise));
        map.put(WarningModel.Columns.LONGITUDE, String.valueOf(((dAddNoise2 + 180.0d) % 360.0d) - 180.0d));
        map.put(WarningModel.Columns.LATITUDE, String.valueOf(dMax));
        AreaCodeResult areaCodeResult = parse(l.C(map, Constants.REQUEST_AREA_CODE_URL, "7htr5238-a8cf-3k79-ec73-75382145ns5c", new i("ew_requestareacode")));
        if (areaCodeResult == null || areaCodeResult.isErr() || areaCodeResult.getData() == null) {
            return null;
        }
        return new String[]{str, areaCodeResult.getData().getCity() + areaCodeResult.getData().getDistrict()};
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
    /* JADX DEBUG: Method merged with bridge method: onPostExecute(Ljava/lang/Object;)V */
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String[] strArr) {
        if (strArr == null || strArr.length != 2) {
            return;
        }
        AnalyticHelper.trackSubscribeCityForNoisy(strArr[0], strArr[1]);
    }
}
