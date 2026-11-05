package g1;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.l;
import b1.c;
import com.miui.securitymanager.Application;
import com.miui.securitymanager.ShortcutManageActivity;
import f2.d;
import java.util.HashSet;
import miui.util.FeatureParser;
import miuix.animation.R;

/* JADX INFO: compiled from: r8-map-id-439d64a6e73e6da60b62f1cd0d3775067af568959c6537234e5044e8e318ed73 */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1277a;
    public static final HashSet b;

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    static {
        int i4 = b1.b.f769a;
        String strA = c.a("ro.miui.product.home", "com.miui.home");
        i1.a.c(strA, "getString(...)");
        f1277a = l.d("content://", strA.concat(".launcher.settings"), "/favorites");
        HashSet hashSet = new HashSet();
        b = hashSet;
        hashSet.add(a.INVALID_TYPE);
        hashSet.add(a.QUICK_CLEANUP);
        hashSet.add(a.POWER_CLEANUP);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static Intent a(a aVar) {
        Intent intent = new Intent();
        switch (aVar.ordinal()) {
            case 1:
                intent.setAction("miui.intent.action.CREATE_QUICK_CLEANUP_SHORTCUT");
                intent.addCategory("android.intent.category.DEFAULT");
                break;
            case 2:
                String strA = b1.a.a(a1.b.f1a);
                intent.setAction("miui.intent.action.GARBAGE_CLEANUP");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setComponent(new ComponentName(strA, "com.miui.optimizecenter.MainActivity"));
                break;
            case 3:
                intent.setAction("miui.intent.action.POWER_MANAGER");
                intent.addCategory("android.intent.category.DEFAULT");
                break;
            case 4:
                intent.setAction("miui.intent.action.ANTI_VIRUS");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.antivirus.activity.MainActivity"));
                break;
            case 5:
                intent.setAction("miui.intent.action.NETWORKASSISTANT_ENTRANCE");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.networkassistant.ui.NetworkAssistantActivity"));
                break;
            case 6:
                intent.setAction("miui.intent.action.NETWORKASSISTANT_ENTRANCE");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setComponent(new ComponentName("com.miui.networkassistant", "com.miui.networkassistant.ui.NetworkAssistantActivity"));
                break;
            case 7:
                intent.setAction("miui.intent.action.SET_FIREWALL");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.putExtra(":miui:starting_window_label", "");
                intent.putExtra("extra_track_ref", "手机管家工具箱");
                break;
            case 8:
                intent.setAction("com.android.securitycenter.CREATE_DEEP_CLEAN_SHORTCUT");
                intent.addCategory("android.intent.category.DEFAULT");
                break;
            case 9:
                intent.setAction("miui.intent.action.NETWORKASSISTANT_NETWORK_SPEED_DETAIL");
                intent.addCategory("android.intent.category.DEFAULT");
                break;
            case 10:
                intent.setAction("miui.intent.action.HB_MAIN_ACTIVITY");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.luckymoney.ui.activity.LuckySettingActivity"));
                break;
            case 11:
                intent.setAction("miui.intent.action.NETWORK_DIAGNOSTICS");
                intent.addCategory("android.intent.category.DEFAULT");
                break;
            case 12:
                intent.setAction("miui.intent.action.AUTO_TASK");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.putExtra("openFrom", "open_from_short_cut");
                break;
            case 13:
                if (e()) {
                    intent.setAction("com.blackshark.action.SHARKSPACE");
                    intent.putExtra("gamebooster_entrance", "securityCenter");
                } else {
                    intent.setAction("miui.gamebooster.action.GAMEBOX");
                    intent.putExtra("enter_homepage_way", "home_shortcut");
                    intent.putExtra("launch_ref", "security_center");
                }
                intent.addFlags(268435456);
                break;
        }
        return intent;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void b(ShortcutManageActivity shortcutManageActivity, a aVar) {
        int identifier;
        if (aVar == a.NETWORK_DIAGNOSTICS) {
            boolean z3 = a1.c.f5a;
            if ("com.miui.securitycenter".equals(a1.b.f1a.getPackageName())) {
                boolean z4 = a1.c.f5a;
                a aVar2 = a.NETWORK_SPEED_DETAILS;
                if (!z4) {
                    Log.d("ShortcutHelper", "remove shortcut direct.");
                    b(shortcutManageActivity, aVar2);
                } else if (b.contains(aVar2)) {
                    b(shortcutManageActivity, aVar2);
                } else if (a1.b.x(a1.b.f1a, "com.miui.securitymanager")) {
                    Log.d("ShortcutHelper", "remove short cut by sm");
                    Intent intent = new Intent();
                    intent.setPackage("com.miui.securitymanager");
                    intent.setAction("com.miui.securitymanager.SHORTCUT_SETTINGS");
                    intent.putExtra("op", 1);
                    intent.putExtra("shortcut_id", 10);
                    try {
                        shortcutManageActivity.startActivity(intent);
                    } catch (Exception unused) {
                    }
                }
            } else {
                Log.e("ShortcutHelper", "removeShortcut: only sc can invoke this method!!!");
            }
        }
        if (a1.b.y(shortcutManageActivity, aVar)) {
            Log.d("ShortcutHelper", "unistall pinnedList shortcut : " + aVar);
            Intent intent2 = new Intent("com.miui.home.launcher.action.UNINSTALL_SHORTCUT");
            String strD = d(aVar);
            if (aVar == a.GAME_TURBO) {
                strD = "shortcut_com_miui_gamebooster";
            }
            intent2.putExtra("shortcut_id", strD);
            int i4 = b1.b.f769a;
            String strA = c.a("ro.miui.product.home", "com.miui.home");
            i1.a.c(strA, "getString(...)");
            intent2.setPackage(strA);
            shortcutManageActivity.sendBroadcast(intent2);
        }
        if (g(shortcutManageActivity, aVar)) {
            Log.d("ShortcutHelper", "remove default shortcut in database : " + aVar);
            Intent intent3 = new Intent("com.miui.home.launcher.action.UNINSTALL_SHORTCUT");
            int i5 = b1.b.f769a;
            String strA2 = c.a("ro.miui.product.home", "com.miui.home");
            i1.a.c(strA2, "getString(...)");
            intent3.setPackage(strA2);
            intent3.putExtra("duplicate", false);
            String strD2 = d(aVar);
            if (strD2 != null) {
                String str = shortcutManageActivity.getPackageName() + ":string/";
                if (strD2.startsWith(str) && (identifier = shortcutManageActivity.getResources().getIdentifier(strD2.substring(str.length()), "string", shortcutManageActivity.getPackageName())) != 0) {
                    strD2 = shortcutManageActivity.getString(identifier);
                }
            }
            intent3.putExtra("android.intent.extra.shortcut.NAME", strD2);
            intent3.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(shortcutManageActivity, c(aVar)));
            intent3.putExtra("android.intent.extra.shortcut.INTENT", a(aVar));
            shortcutManageActivity.sendBroadcast(intent3);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static int c(a aVar) {
        switch (aVar.ordinal()) {
            case 1:
                return f() ? R.drawable.ic_launcher_quick_clean_new : R.drawable.ic_launcher_quick_clean;
            case 2:
                return R.drawable.ic_launcher_rubbish_clean;
            case 3:
                return R.drawable.ic_launcher_power_optimize;
            case 4:
                return R.drawable.ic_launcher_virus_scan;
            case 5:
            case 6:
                return R.drawable.ic_launcher_network_assistant;
            case 7:
                return R.drawable.ic_launcher_anti_spam;
            case 8:
                return f() ? R.drawable.icon_power_cleanup_new : R.drawable.icon_power_cleanup;
            case 9:
                return R.drawable.ic_launcher_network_speed_detail;
            case 10:
                return R.drawable.hongbao_launcher;
            case 11:
                return R.drawable.ic_launcher_network_diagnostics;
            case 12:
                return R.drawable.ic_svg_auto_task;
            case 13:
                return R.drawable.game_booster_icon;
            default:
                return -1;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static String d(a aVar) {
        String packageName = a1.b.f1a.getPackageName();
        switch (aVar.ordinal()) {
            case 1:
                return l.c(packageName, ":string/btn_text_quick_cleanup");
            case 2:
                return l.c(packageName, ":string/activity_title_garbage_cleanup");
            case 3:
                return l.c(packageName, ":string/activity_title_power_manager");
            case 4:
                return l.c(packageName, ":string/activity_title_antivirus");
            case 5:
            case 6:
                return l.c(packageName, ":string/activity_title_networkassistants");
            case 7:
                return l.c(packageName, ":string/activity_title_antispam");
            case 8:
                return l.c(packageName, ":string/btn_text_power_cleanup");
            case 9:
                return l.c(packageName, ":string/btn_text_network_speed_detail");
            case 10:
                return l.c(packageName, ":string/hongbao_name");
            case 11:
                return l.c(packageName, ":string/network_diagnostics");
            case 12:
                return l.c(packageName, ":string/auto_task_main_title");
            case 13:
                return l.c(packageName, ":string/game_booster");
            default:
                return packageName;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static boolean e() {
        boolean z3 = false;
        try {
            z3 = FeatureParser.getBoolean("is_blackshark", false);
            Log.i("ShortcutHelper", "isBS: " + z3);
            return z3;
        } catch (Throwable th) {
            Log.e("ShortcutHelper", "getBlackSharkFeature Failed", th);
            return z3;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static boolean f() {
        int i4;
        Application application = a1.b.f1a;
        if (TextUtils.isEmpty("com.miui.home")) {
            i4 = 0;
        } else {
            try {
                i4 = application.getPackageManager().getPackageInfo("com.miui.home", 0).versionCode;
            } catch (PackageManager.NameNotFoundException e4) {
                Log.e("ShortcutHelper", "getPackageVersionName", e4);
                i4 = 0;
            }
        }
        return i4 > 41600000;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static boolean g(ShortcutManageActivity shortcutManageActivity, a aVar) {
        if (aVar == a.QUICK_CLEANUP) {
            return h(shortcutManageActivity, 12, aVar);
        }
        if (aVar == a.POWER_CLEANUP) {
            return h(shortcutManageActivity, 14, aVar);
        }
        if (aVar == a.NETWORK_DIAGNOSTICS && g(shortcutManageActivity, a.NETWORK_SPEED_DETAILS)) {
            return true;
        }
        String uri = a(aVar).toUri(0);
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = shortcutManageActivity.getContentResolver().query(Uri.parse(f1277a), new String[]{"_id"}, " intent = ? ", new String[]{uri}, null);
                if (cursorQuery != null && cursorQuery.getCount() != 0) {
                    d.a(cursorQuery);
                    return true;
                }
            } catch (Exception e4) {
                Log.e("ShortcutHelper", e4.toString());
            }
            d.a(cursorQuery);
            a1.b.y(shortcutManageActivity, aVar);
            return false;
        } catch (Throwable th) {
            d.a(cursorQuery);
            throw th;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static boolean h(ShortcutManageActivity shortcutManageActivity, int i4, a aVar) {
        Uri uri = Uri.parse(f1277a);
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = shortcutManageActivity.getContentResolver().query(uri, null, "itemType=5 AND appWidgetId= " + i4, null, null);
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    cursorQuery.close();
                    return true;
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                a1.b.y(shortcutManageActivity, aVar);
                return false;
            } catch (Exception e4) {
                e4.printStackTrace();
                if (cursorQuery != null) {
                }
            }
        } catch (Throwable th) {
            if (cursorQuery == null) {
                throw th;
            }
            cursorQuery.close();
            throw th;
        }
    }
}
