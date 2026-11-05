package com.miui.earthquakewarning.service;

import aj.z;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import androidx.annotation.Nullable;
import com.miui.earthquakewarning.Constants;
import com.miui.earthquakewarning.model.SignatureReuslt;
import com.miui.earthquakewarning.utils.FileUtils;
import com.miui.securitycenter.Application;
import gk.l;
import j7.i;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class RequestSignatureTask extends AsyncTask<String, Void, SignatureReuslt> {
    private static final String TAG = "RequestSignatureTask";
    private Listener listener;
    private Context mContext;

    public interface Listener {
        void onPost(SignatureReuslt signatureReuslt);
    }

    public RequestSignatureTask(Context context) {
        this.mContext = context;
    }

    @Nullable
    public static String download(String str) {
        if (!z.D()) {
            return null;
        }
        HashMap map = new HashMap();
        map.put("channel", str);
        return l.C(map, Constants.REQUEST_SIGNATURE_URL, "7htr5238-a8cf-3k79-ec73-75382145ns5c", new i("ew_requestsignature"));
    }

    private SignatureReuslt parse(String str) {
        SignatureReuslt signatureReuslt = new SignatureReuslt();
        try {
            JSONObject jSONObject = new JSONObject(str);
            signatureReuslt.setCode(jSONObject.optInt("code", -1));
            JSONArray jSONArray = jSONObject.getJSONArray("datas");
            ArrayList arrayList = new ArrayList();
            for (int i10 = 0; i10 < jSONArray.length(); i10++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i10);
                SignatureReuslt.DatasBean datasBean = new SignatureReuslt.DatasBean();
                datasBean.setChannel(jSONObject2.optString("channel"));
                JSONArray jSONArray2 = jSONObject2.getJSONArray("data");
                ArrayList arrayList2 = new ArrayList();
                for (int i11 = 0; i11 < jSONArray2.length(); i11++) {
                    JSONObject jSONObject3 = jSONArray2.getJSONObject(i11);
                    SignatureReuslt.DataBean dataBean = new SignatureReuslt.DataBean();
                    dataBean.setCode(jSONObject3.optInt("code"));
                    dataBean.setDistrict(jSONObject3.optString("district"));
                    JSONArray jSONArrayOptJSONArray = jSONObject3.optJSONArray("signs");
                    ArrayList arrayList3 = new ArrayList();
                    for (int i12 = 0; i12 < jSONArrayOptJSONArray.length(); i12++) {
                        arrayList3.add(jSONArrayOptJSONArray.getString(i12));
                    }
                    dataBean.setSigns(arrayList3);
                    arrayList2.add(dataBean);
                }
                datasBean.setData(arrayList2);
                arrayList.add(datasBean);
            }
            signatureReuslt.setDatas(arrayList);
            return signatureReuslt;
        } catch (Exception unused) {
            Log.e(TAG, "parse json failed");
            return signatureReuslt;
        }
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
    /* JADX DEBUG: Method merged with bridge method: doInBackground([Ljava/lang/Object;)Ljava/lang/Object; */
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public SignatureReuslt doInBackground(String... strArr) throws Throwable {
        String strDownload = download(strArr[0]);
        FileUtils.saveSignatureToData(strDownload, Application.B());
        return parse(strDownload);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
    /* JADX DEBUG: Method merged with bridge method: onPostExecute(Ljava/lang/Object;)V */
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(SignatureReuslt signatureReuslt) {
        Listener listener = this.listener;
        if (listener != null) {
            listener.onPost(signatureReuslt);
        }
        new ManageAreaDataTask(1, FileUtils.getSignatureFromData(Application.B())).execute(new String[0]);
    }
}
