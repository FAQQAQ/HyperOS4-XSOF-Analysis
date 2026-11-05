package defpackage;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.provider.Settings;
import com.miui.guardprovider.R;
import com.miui.guardprovider.action.impl.AppListAlertActivity;
import com.miui.guardprovider.aiguard.PermissionRequestActivity;
import java.lang.invoke.VarHandle;
import miuix.appcompat.app.d;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class yz1 implements DialogInterface.OnClickListener {
    public final /* synthetic */ int b;
    public /* synthetic */ Object c;

    /* JADX DEBUG: Marked for inline */
    /* JADX DEBUG: Method not inlined, still used in: [com.miui.guardprovider.action.impl.AppListAlertActivity.a.w(android.os.Bundle):android.app.Dialog, yz1.onClick(android.content.DialogInterface, int):void] */
    public /* synthetic */ yz1(int i) {
        this.b = i;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        int i2 = this.b;
        int i3 = 0;
        Object obj = this.c;
        switch (i2) {
            case 0:
                int i4 = PermissionRequestActivity.F;
                ((PermissionRequestActivity) obj).finish();
                break;
            case 1:
                int i5 = PermissionRequestActivity.F;
                ((PermissionRequestActivity) obj).finish();
                break;
            case 2:
                PermissionRequestActivity permissionRequestActivity = (PermissionRequestActivity) obj;
                int i6 = PermissionRequestActivity.F;
                if (permissionRequestActivity.checkSelfPermission("android.permission.RECORD_AUDIO") == 0) {
                    permissionRequestActivity.F();
                } else {
                    d dVar = new d(permissionRequestActivity, R.style.Dimmed);
                    dVar.o(R.string.ai_guard_permission_tips_title);
                    dVar.i(R.string.ai_guard_permission_tips_message);
                    yz1 yz1Var = new yz1(i3);
                    yz1Var.c = permissionRequestActivity;
                    VarHandle.storeStoreFence();
                    dVar.k(android.R.string.cancel, yz1Var);
                    zz1 zz1Var = new zz1(0);
                    zz1Var.c = permissionRequestActivity;
                    VarHandle.storeStoreFence();
                    dVar.n(R.string.ai_guard_permission_request_pos_btn, zz1Var);
                    a02 a02Var = new a02(0);
                    a02Var.c = permissionRequestActivity;
                    VarHandle.storeStoreFence();
                    dVar.l(a02Var);
                    dVar.a().show();
                }
                Settings.Secure.putInt(permissionRequestActivity.getContentResolver(), "com.miui.guardprovider.AI_GUARD_PRIVACY_AGREEMENT", 1);
                SharedPreferences preferences = permissionRequestActivity.getPreferences(0);
                preferences.getClass();
                SharedPreferences.Editor editorEdit = preferences.edit();
                editorEdit.getClass();
                editorEdit.putBoolean("ai_guard_first_open_guiding", false);
                editorEdit.apply();
                break;
            default:
                ((AppListAlertActivity.a) obj).v(false, false);
                break;
        }
    }
}
