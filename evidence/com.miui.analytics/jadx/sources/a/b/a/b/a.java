package a.b.a.b;

import a.b.a.b.b.b;
import a.b.a.e.e;
import a.b.a.e.l0.r;
import a.b.a.g.c.c;
import android.text.TextUtils;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class a {
    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static boolean a(String str, String str2, boolean z) {
        String str3;
        if (z) {
            try {
                b.b().d();
                if (b(str)) {
                    b bVarB = b.b();
                    Objects.requireNonNull(bVarB);
                    return !((TextUtils.isEmpty(str) || bVarB.a(str) == null) ? false : bVarB.a(str).f6b);
                }
                if (a.b.a.g.f.b.f892b.contains(str)) {
                    if (!a.b.a.g.f.a.n(str2)) {
                        str2.hashCode();
                        str2.hashCode();
                        str2.hashCode();
                        str2.hashCode();
                        byte b2 = -1;
                        switch (str2.hashCode()) {
                            case 100707:
                                if (str2.equals("et2")) {
                                    b2 = 0;
                                }
                                break;
                            case 100709:
                                if (str2.equals("et4")) {
                                    b2 = 1;
                                }
                                break;
                            case 100711:
                                if (str2.equals("et6")) {
                                    b2 = 2;
                                }
                                break;
                        }
                        switch (b2) {
                            case 0:
                            case 1:
                                str3 = "r3";
                                break;
                            case 2:
                                str3 = "r4";
                                break;
                            default:
                                str3 = "";
                                break;
                        }
                    } else {
                        str3 = "r9";
                    }
                    c.f(true, str2, 1, str3);
                }
                return false;
            } catch (Exception e) {
                a.a.a.a.a.g(e, a.a.a.a.a.c("check failed: "), "FireBaseChecker");
            }
        }
        return false;
    }

    public static boolean b(String str) {
        try {
            return "onetrack_active".equals(str) || r.b(e.b()) == 1;
        } catch (Exception e) {
            a.a.a.a.a.g(e, a.a.a.a.a.c("isAllowReportAppUsageOpen failed: "), "FireBaseChecker");
            return false;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static boolean c(String str) {
        try {
            if (!r.q()) {
                return true;
            }
            String strJ = r.j();
            a.b.a.e.e0.a.d().k(strJ);
            return !r.t(strJ) || b(str);
        } catch (Exception e) {
            a.a.a.a.a.g(e, a.a.a.a.a.c("isAllowTrackForRussia failed: "), "FireBaseChecker");
            return true;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static boolean d() {
        try {
            if (g()) {
                b bVarB = b.b();
                Objects.requireNonNull(bVarB);
                if ((TextUtils.isEmpty("onetrack_app_update") || bVarB.a("onetrack_app_update") == null) ? false : bVarB.a("onetrack_app_update").f5a) {
                    return true;
                }
            }
        } catch (Exception e) {
            a.a.a.a.a.g(e, a.a.a.a.a.c("isCloseAnalyticsUpdateApp failed: "), "FireBaseChecker");
        }
        return false;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static boolean e(String str) {
        try {
            if (g()) {
                b bVarB = b.b();
                Objects.requireNonNull(bVarB);
                if ((TextUtils.isEmpty(str) || bVarB.a(str) == null) ? false : bVarB.a(str).f5a) {
                    return true;
                }
            }
        } catch (Exception e) {
            a.a.a.a.a.g(e, a.a.a.a.a.c("isCloseOneTrackOverseasChannel failed: "), "FireBaseChecker");
        }
        return false;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static boolean f() {
        try {
            if (g()) {
                b bVarB = b.b();
                Objects.requireNonNull(bVarB);
                if ((TextUtils.isEmpty("onetrack_pull_config") || bVarB.a("onetrack_pull_config") == null) ? false : bVarB.a("onetrack_pull_config").f5a) {
                    return true;
                }
            }
        } catch (Exception e) {
            a.a.a.a.a.g(e, a.a.a.a.a.c("isCloseOneTrackPullConfig failed: "), "FireBaseChecker");
        }
        return false;
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static boolean g() {
        try {
            if (!r.q()) {
                return false;
            }
            String strJ = r.j();
            a.b.a.e.e0.a.d().k(strJ);
            return !r.t(strJ);
        } catch (Exception e) {
            a.a.a.a.a.g(e, a.a.a.a.a.c("isUseFirebaseTrack failed: "), "FireBaseChecker");
            return false;
        }
    }
}
