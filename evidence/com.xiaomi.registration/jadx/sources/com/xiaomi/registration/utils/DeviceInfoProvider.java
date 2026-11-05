package com.xiaomi.registration.utils;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import androidx.activity.result.a;
import j1.b;
import miui.os.UserHandle;

/* JADX INFO: loaded from: classes.dex */
public class DeviceInfoProvider extends ContentProvider {
    public b a = null;

    @Override // android.content.ContentProvider
    public final Bundle call(String str, String str2, Bundle bundle) {
        int iG;
        boolean z2;
        Bundle bundle2 = new Bundle();
        Message message = (Message) bundle.getParcelable("callback");
        b bVar = this.a;
        bVar.getClass();
        try {
            iG = "5GRCSSW".equals(str2) ? 36 : a.g(str2);
        } catch (Exception unused) {
            iG = 32;
        }
        if (iG != 32) {
            if (message != null) {
                bVar.a = message.arg1;
                bVar.f1784b = message.arg2;
            }
            Handler handler = (Handler) bVar.f1789g;
            j1.a aVar = new j1.a(message, str2);
            z2 = true;
            handler.obtainMessage(1, aVar).sendToTarget();
        } else {
            z2 = false;
        }
        bundle2.putBoolean("result", z2);
        return bundle2;
    }

    @Override // android.content.ContentProvider
    public final int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public final String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public final Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public final boolean onCreate() {
        Log.d("AutoReg_DeviceInfoProvider", "DeviceInfoProvider onCreate");
        if (UserHandle.myUserId() != 0) {
            Log.d("AutoReg_DeviceInfoProvider", "onCreate UserHandle.myUserId() != 0 return");
            return false;
        }
        this.a = b.l(getContext());
        return false;
    }

    @Override // android.content.ContentProvider
    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
