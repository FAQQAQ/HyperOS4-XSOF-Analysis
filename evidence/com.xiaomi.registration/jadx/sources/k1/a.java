package k1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String[] f1792d = {"VER", "CTEI", "MAC", "IP", "UPLINKMAC", "LINK", "FWVER", "DATE"};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static a f1793e;
    public final ConnectivityManager a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final WifiManager f1794b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Context f1795c;

    public a(Context context) {
        this.f1795c = context;
        this.a = (ConnectivityManager) context.getSystemService("connectivity");
        this.f1794b = (WifiManager) context.getSystemService("wifi");
    }
}
