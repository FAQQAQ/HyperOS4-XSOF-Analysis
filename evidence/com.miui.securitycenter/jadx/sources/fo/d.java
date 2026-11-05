package fo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public abstract class d {
    public static boolean a(Context context, String str, String str2) {
        PackageInfo packageInfo;
        String[] strArr;
        try {
            if (TextUtils.isEmpty(str2) || (packageInfo = context.getPackageManager().getPackageInfo(str, 4096)) == null || (strArr = packageInfo.requestedPermissions) == null) {
                return false;
            }
            return Arrays.asList(strArr).contains(str2);
        } catch (Exception unused) {
            return false;
        }
    }
}
