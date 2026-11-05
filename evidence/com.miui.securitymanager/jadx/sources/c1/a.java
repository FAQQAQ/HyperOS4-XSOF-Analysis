package c1;

import a1.b;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import miuix.animation.R;
import miuix.core.util.SystemProperties;
import p2.d;

/* JADX INFO: compiled from: r8-map-id-439d64a6e73e6da60b62f1cd0d3775067af568959c6537234e5044e8e318ed73 */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    static {
        new HashSet(Collections.singletonList(""));
        new HashSet(Arrays.asList("com.miui.cleanmaster", "com.tencent.mm"));
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void a(Context context) {
        ShortcutInfo shortcutInfoB;
        ShortcutInfo shortcutInfoB2;
        ShortcutInfo shortcutInfoB3;
        if (h()) {
            try {
                ShortcutManager shortcutManager = (ShortcutManager) context.getSystemService(ShortcutManager.class);
                List<ShortcutInfo> dynamicShortcuts = shortcutManager.getDynamicShortcuts();
                ArrayList arrayList = new ArrayList();
                if (dynamicShortcuts != null) {
                    boolean z3 = v2.a.f3930a;
                    if (z3) {
                        f(context, dynamicShortcuts, arrayList);
                    } else if (g(dynamicShortcuts, "clean_master")) {
                        if (!b.x(context, "com.miui.cleanmaster")) {
                            d(context, "com.miui.cleanmaster");
                        }
                    } else if (b.x(context, "com.miui.cleanmaster") && (shortcutInfoB = b(context, "clean_master")) != null) {
                        arrayList.add(shortcutInfoB);
                    }
                    if (z3) {
                        if (!g(dynamicShortcuts, "security_scan") && (shortcutInfoB3 = b(context, "security_scan")) != null) {
                            arrayList.add(shortcutInfoB3);
                        }
                    } else if (!g(dynamicShortcuts, "anti_virus") && (shortcutInfoB2 = b(context, "anti_virus")) != null) {
                        arrayList.add(shortcutInfoB2);
                    }
                    if (b.x(context, "")) {
                        arrayList.addAll(e(context, dynamicShortcuts, ""));
                    } else if (b.x(context, "com.tencent.mm")) {
                        arrayList.addAll(e(context, dynamicShortcuts, "com.tencent.mm"));
                    }
                }
                if (arrayList.isEmpty()) {
                    return;
                }
                shortcutManager.addDynamicShortcuts(arrayList);
            } catch (Throwable th) {
                Log.e("ShortcutUtils", "add shortcuts error ", th);
            }
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static ShortcutInfo b(Context context, String str) {
        if ("clean_master".equals(str) || "cleaner".equals(str)) {
            Intent intent = new Intent("miui.intent.action.GARBAGE_CLEANUP");
            intent.setPackage(b1.a.a(context));
            return c(context, str, R.string.menu_text_garbage_cleanup, R.string.menu_text_garbage_cleanup, Icon.createWithResource(context, R.drawable.ic_shortcut_clean_master), intent);
        }
        if ("whatsapp_clean".equals(str)) {
            Intent intent2 = new Intent("miui.intent.action.GARBAGE_DEEPCLEAN_WHATSAPP");
            intent2.setPackage(b1.a.a(context));
            return c(context, "whatsapp_clean", R.string.shortcut_title_whatsapp_clean, R.string.shortcut_title_whatsapp_clean, Icon.createWithResource(context, R.drawable.ic_shortcut_whatsapp_clean), intent2);
        }
        if ("wechat_lean".equals(str)) {
            Intent intent3 = new Intent("miui.intent.action.GARBAGE_DEEPCLEAN_WECHAT");
            intent3.setPackage("com.miui.cleanmaster");
            return c(context, "wechat_lean", R.string.card_main_deepclean_wechat_title, R.string.card_main_deepclean_wechat_title, Icon.createWithResource(context, R.drawable.ic_shortcut_wechat_clean), intent3);
        }
        if ("security_scan".equals(str)) {
            Intent intent4 = new Intent("miui.intent.action.SECURITY_CENTER");
            intent4.setPackage("com.miui.securitycenter");
            intent4.putExtra("extra_auto_optimize", true);
            return c(context, "security_scan", R.string.shortcut_title_scan, R.string.shortcut_title_scan, Icon.createWithResource(context, R.drawable.ic_shortcut_securityscan), intent4);
        }
        if (!"anti_virus".equals(str)) {
            return null;
        }
        Intent intent5 = new Intent("miui.intent.action.ANTI_VIRUS");
        intent5.setPackage("com.miui.securitycenter");
        int i4 = "jp_kd".equals(b1.b.a()) ? R.string.menu_text_antivirus_kddi : R.string.menu_text_antivirus;
        return c(context, "anti_virus", i4, i4, Icon.createWithResource(context, R.drawable.ic_shortcut_antivirus), intent5);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static ShortcutInfo c(Context context, String str, int i4, int i5, Icon icon, Intent intent) {
        ShortcutInfo.Builder builder;
        try {
            Constructor<?> declaredConstructor = Class.forName("android.content.pm.ShortcutInfo$Builder").getDeclaredConstructor(Context.class, String.class);
            builder = declaredConstructor != null ? (ShortcutInfo.Builder) declaredConstructor.newInstance(context, str) : null;
        } catch (Exception unused) {
            Log.i("ShortcutUtils", "construct shortcutinfo builder error ");
        }
        if (builder == null) {
            return null;
        }
        try {
            d.a(builder, "setShortLabelResId", new Class[]{Integer.TYPE}, Integer.valueOf(i4));
            try {
                d.a(builder, "setLongLabelResId", new Class[]{String.class}, Integer.valueOf(i5));
            } catch (Exception unused2) {
                Log.i("ShortcutUtils", "shortcutInfo setLongLabel error");
            }
            builder.setIcon(icon);
            builder.setIntent(intent);
            return builder.build();
        } catch (Exception unused3) {
            Log.i("ShortcutUtils", "shortcutInfo setShortLabel error");
            return null;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void d(Context context, String str) {
        ShortcutManager shortcutManager;
        List<ShortcutInfo> dynamicShortcuts;
        if (h() && (dynamicShortcuts = (shortcutManager = (ShortcutManager) context.getSystemService(ShortcutManager.class)).getDynamicShortcuts()) != null && dynamicShortcuts.size() > 0) {
            if (v2.a.f3930a) {
                if ("".equals(str) && g(dynamicShortcuts, "whatsapp_clean")) {
                    shortcutManager.removeDynamicShortcuts(Arrays.asList("whatsapp_clean"));
                    return;
                }
                return;
            }
            if ("com.tencent.mm".equals(str) && g(dynamicShortcuts, "wechat_lean")) {
                shortcutManager.removeDynamicShortcuts(Arrays.asList("wechat_lean"));
            }
            if ("com.miui.cleanmaster".equals(str)) {
                if (g(dynamicShortcuts, "clean_master")) {
                    shortcutManager.removeDynamicShortcuts(Arrays.asList("clean_master"));
                }
                if (g(dynamicShortcuts, "wechat_lean")) {
                    shortcutManager.removeDynamicShortcuts(Arrays.asList("wechat_lean"));
                }
            }
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static ArrayList e(Context context, List list, String str) {
        ShortcutInfo shortcutInfoB;
        ShortcutInfo shortcutInfoB2;
        ShortcutInfo shortcutInfoB3;
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            if (!v2.a.f3930a) {
                if ("com.tencent.mm".equals(str) && b.x(context, "com.miui.cleanmaster") && !g(list, "wechat_lean") && (shortcutInfoB2 = b(context, "wechat_lean")) != null) {
                    arrayList.add(shortcutInfoB2);
                }
                if ("com.miui.cleanmaster".equals(str)) {
                    if (!g(list, "clean_master")) {
                        new Intent("miui.intent.action.GARBAGE_CLEANUP").setPackage("com.miui.cleanmaster");
                        ShortcutInfo shortcutInfoB4 = b(context, "clean_master");
                        if (shortcutInfoB4 != null) {
                            arrayList.add(shortcutInfoB4);
                        }
                    }
                    if (b.x(context, "com.tencent.mm") && !g(list, "wechat_lean") && (shortcutInfoB = b(context, "wechat_lean")) != null) {
                        arrayList.add(shortcutInfoB);
                    }
                }
            } else if ("".equals(str) && !g(list, "whatsapp_clean") && (shortcutInfoB3 = b(context, "whatsapp_clean")) != null) {
                arrayList.add(shortcutInfoB3);
                return arrayList;
            }
        }
        return arrayList;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void f(Context context, List list, ArrayList arrayList) {
        ShortcutInfo shortcutInfoB;
        ShortcutInfo shortcutInfoB2;
        if (context == null || !v2.a.f3930a) {
            return;
        }
        if (!TextUtils.equals(b1.a.a(context), "com.miui.cleaner")) {
            if (g(list, "clean_master") || !b.x(context, "com.miui.cleanmaster") || (shortcutInfoB = b(context, "clean_master")) == null) {
                return;
            }
            arrayList.add(shortcutInfoB);
            return;
        }
        if (g(list, "clean_master")) {
            d(context, "com.miui.cleanmaster");
        }
        if (g(list, "cleaner") || !b.x(context, "com.miui.cleaner") || (shortcutInfoB2 = b(context, "cleaner")) == null) {
            return;
        }
        arrayList.add(shortcutInfoB2);
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static boolean g(List list, String str) {
        if (list.isEmpty()) {
            return false;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (str.equals(((ShortcutInfo) it.next()).getId())) {
                return true;
            }
        }
        return false;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static boolean h() {
        int i4;
        String str = SystemProperties.get("ro.miui.ui.version.code");
        try {
            i1.a.a(str);
            i4 = Integer.parseInt(str);
        } catch (Exception unused) {
            i4 = 0;
        }
        return i4 > 8 || !a1.a.a();
    }
}
