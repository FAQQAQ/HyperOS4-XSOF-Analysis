package com.miui.riskcontrol;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class SensitiveWordDetectProvider extends ContentProvider {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ArrayList f24283a;

    static {
        Uri.withAppendedPath(Uri.parse("content://com.miui.riskcontrol"), "censorResult");
        ArrayList arrayList = new ArrayList();
        f24283a = arrayList;
        System.loadLibrary("lib-riskcontrol");
        arrayList.add("com.android.camera");
        arrayList.add("com.miui.mediaeditor");
    }

    private native boolean hasSensitiveWords(String str);

    private native void setAssetManager(AssetManager assetManager);

    @Override // android.content.ContentProvider
    public final Bundle call(String str, String str2, Bundle bundle) {
        Log.i("SWDP", "call method = " + str + " arg = " + str2);
        if (!f24283a.contains(getCallingPackage())) {
            Log.i("SWDP", getCallingPackage() + "not in whiteList!");
            return null;
        }
        if (!"METHOD_SENSITIVE_WORD_DETECT".equals(str)) {
            return null;
        }
        setAssetManager(getContext().getAssets());
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean("hasSensitiveWords", hasSensitiveWords(str2));
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
        new UriMatcher(-1).addURI("com.miui.riskcontrol", "censorResult", 1);
        Log.i("SWDP", "onCreate");
        return true;
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
