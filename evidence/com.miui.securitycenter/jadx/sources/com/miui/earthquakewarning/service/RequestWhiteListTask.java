package com.miui.earthquakewarning.service;

import aj.z;
import android.os.AsyncTask;
import android.util.Log;
import com.miui.earthquakewarning.Constants;
import com.miui.earthquakewarning.model.WhiteListResult;
import j7.i;
import java.util.ArrayList;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class RequestWhiteListTask extends AsyncTask<String, Void, WhiteListResult> {
    private static final String TAG = "RequestWhiteListTask";
    private Listener listener;

    public interface Listener {
        void onPost(WhiteListResult whiteListResult);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
    /* JADX DEBUG: Method merged with bridge method: doInBackground([Ljava/lang/Object;)Ljava/lang/Object; */
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public WhiteListResult doInBackground(String... strArr) throws Throwable {
        if (!z.D()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new com.miui.common.net.b("userId", strArr[0]));
        boolean z10 = true;
        arrayList.add(new com.miui.common.net.b("group", strArr[1]));
        arrayList.add(new com.miui.common.net.b("biz", "earthquake"));
        arrayList.add(new com.miui.common.net.b("sign", com.miui.common.net.d.b(arrayList, Constants.UUID_WHITELIST_CONFIG)));
        StringBuilder sb2 = new StringBuilder();
        int size = arrayList.size();
        int i10 = 0;
        while (i10 < size) {
            Object obj = arrayList.get(i10);
            i10++;
            com.miui.common.net.b bVar = (com.miui.common.net.b) obj;
            if (z10) {
                sb2.append("?");
            } else {
                sb2.append("&");
            }
            sb2.append(bVar.a());
            sb2.append("=");
            sb2.append(bVar.b());
            z10 = false;
        }
        String strA = com.miui.common.net.a.a(Constants.REQUEST_WHITELIST_URL + sb2.toString(), new i("ew_requestwhitelist"));
        WhiteListResult whiteListResult = new WhiteListResult();
        try {
            JSONObject jSONObject = new JSONObject(strA);
            whiteListResult.setCode(jSONObject.optInt("code"));
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
            WhiteListResult.DataBean dataBean = new WhiteListResult.DataBean();
            dataBean.setCheckResult(jSONObjectOptJSONObject.optBoolean("checkResult"));
            whiteListResult.setData(dataBean);
            return whiteListResult;
        } catch (Exception unused) {
            Log.e(TAG, "parse json failed");
            return whiteListResult;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
    /* JADX DEBUG: Method merged with bridge method: onPostExecute(Ljava/lang/Object;)V */
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(WhiteListResult whiteListResult) {
        Listener listener = this.listener;
        if (listener != null) {
            listener.onPost(whiteListResult);
        }
    }
}
