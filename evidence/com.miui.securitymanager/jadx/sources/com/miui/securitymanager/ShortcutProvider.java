package com.miui.securitymanager;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import f2.d;
import g1.a;
import g1.b;

/* JADX INFO: compiled from: r8-map-id-439d64a6e73e6da60b62f1cd0d3775067af568959c6537234e5044e8e318ed73 */
/* JADX INFO: loaded from: classes.dex */
public class ShortcutProvider extends ContentProvider {
    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static boolean a(Context context, a aVar) {
        boolean z3 = false;
        Cursor cursorQuery = null;
        try {
            cursorQuery = context.getContentResolver().query(Uri.parse(b.f1277a), new String[]{"_id"}, "intent = ?", new String[]{b.a(aVar).toUri(0)}, null);
            if (cursorQuery != null && cursorQuery.getCount() > 0) {
                z3 = true;
            }
            return z3;
        } catch (Exception unused) {
            return false;
        } finally {
            d.a(cursorQuery);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static MatrixCursor b() {
        return new MatrixCursor(new String[]{"_id"});
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    @Override // android.content.ContentProvider
    public final int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    @Override // android.content.ContentProvider
    public final String getType(Uri uri) {
        return null;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    @Override // android.content.ContentProvider
    public final Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    @Override // android.content.ContentProvider
    public final boolean onCreate() {
        return true;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    /* JADX WARN: Code duplicated, block: B:31:0x0048  */
    @Override // android.content.ContentProvider
    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        a aVar;
        boolean zY;
        boolean z3;
        try {
            try {
                Context context = getContext();
                if (context == null) {
                    return b();
                }
                int id = (int) ContentUris.parseId(uri);
                if (id == -1) {
                    return b();
                }
                try {
                    String str3 = b.f1277a;
                    a[] aVarArrValues = a.values();
                    int length = aVarArrValues.length;
                    int i4 = 0;
                    while (true) {
                        if (i4 >= length) {
                            aVar = a.INVALID_TYPE;
                            break;
                        }
                        aVar = aVarArrValues[i4];
                        if (aVar.f1276a == id) {
                            break;
                        }
                        i4++;
                    }
                } catch (Exception | ExceptionInInitializerError unused) {
                    aVar = null;
                }
                if (aVar == null) {
                    return b();
                }
                if (!a(context, aVar)) {
                    try {
                        zY = a1.b.y(context, aVar);
                    } catch (Exception unused2) {
                        zY = false;
                    }
                    z3 = zY;
                }
                MatrixCursor matrixCursor = new MatrixCursor(new String[]{"_id"});
                if (z3) {
                    matrixCursor.addRow(new Integer[]{Integer.valueOf(id)});
                }
                return matrixCursor;
            } catch (ExceptionInInitializerError e4) {
                if (e4.getException() != null) {
                    e4.getException().printStackTrace();
                }
                return b();
            }
        } catch (Throwable unused3) {
            return b();
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    @Override // android.content.ContentProvider
    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
