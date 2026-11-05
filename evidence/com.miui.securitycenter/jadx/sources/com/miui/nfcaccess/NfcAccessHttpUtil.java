package com.miui.nfcaccess;

import android.os.Build;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import wo.t;
import yo.f0;

/* JADX INFO: loaded from: classes3.dex */
public final class NfcAccessHttpUtil {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final NfcAccessHttpUtil f20633a = new NfcAccessHttpUtil();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final a f20634b = new a("NfcAccessHttpUtil");

    private NfcAccessHttpUtil() {
    }

    public static final Response a(int i10) {
        String strM = gk.l.m(f0.l(t.a("deviceModel", Build.DEVICE), t.a("appDataVersion", String.valueOf(i10))), "https://fraud-detection.engine.miui.com/nfc/antifraud/config", new j7.i(""));
        Object objFromJson = null;
        if (strM == null) {
            return null;
        }
        a.d(f20634b, "getConfig result = " + strM, null, 2, null);
        try {
            objFromJson = new Gson().fromJson(strM, new TypeToken<Response<ConfigData>>() { // from class: com.miui.nfcaccess.NfcAccessHttpUtil$getConfig$lambda$0$$inlined$fromJsonTypeToken$1
            }.getType());
        } catch (Exception e10) {
            f20634b.c("fromJsonTypeToken error", e10);
        }
        return (Response) objFromJson;
    }
}
