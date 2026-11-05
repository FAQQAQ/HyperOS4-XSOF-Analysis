package oj;

import android.text.TextUtils;
import com.miui.common.utils.f2;
import com.miui.securitycenter.utils.LoadSeriNum;
import java.io.BufferedReader;
import java.io.FileReader;
import miui.util.FeatureParser;

/* JADX INFO: loaded from: classes3.dex */
public abstract class h {
    public static String a() {
        return f2.c("ro.secureboot.lockstate", "");
    }

    public static boolean b() {
        return f2.b("ro.debuggable", 0) == 1;
    }

    public static String c() {
        String strG = g();
        if (!TextUtils.isEmpty(strG)) {
            return strG;
        }
        String string = FeatureParser.getString("vendor");
        if ("mediatek".equals(string)) {
            return d();
        }
        if ("qcom".equals(string)) {
            return f();
        }
        return "pinecone".equals(string) ? e() : strG;
    }

    private static String d() {
        StringBuilder sb2 = new StringBuilder();
        for (byte b10 : LoadSeriNum.readOTP()) {
            sb2.append((char) b10);
        }
        return sb2.toString();
    }

    private static String e() {
        return g();
    }

    private static String f() throws Throwable {
        BufferedReader bufferedReader;
        Throwable th2;
        Exception e10;
        String line;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/serial_num"), 256);
            try {
                try {
                    line = bufferedReader.readLine();
                    vq.h.d(bufferedReader);
                } catch (Exception e11) {
                    e10 = e11;
                    e10.printStackTrace();
                    vq.h.d(bufferedReader);
                    line = "";
                }
            } catch (Throwable th3) {
                th2 = th3;
                vq.h.d(bufferedReader);
                throw th2;
            }
        } catch (Exception e12) {
            bufferedReader = null;
            e10 = e12;
        } catch (Throwable th4) {
            bufferedReader = null;
            th2 = th4;
            vq.h.d(bufferedReader);
            throw th2;
        }
        return TextUtils.isEmpty(line) ? g() : line;
    }

    private static String g() {
        return f2.c("ro.boot.cpuid", "");
    }

    public static boolean h() {
        return f2.b("ro.secureboot.devicelock", 0) == 1;
    }

    public static boolean i() {
        if (FeatureParser.hasFeature("is_patchrom", 1)) {
            return FeatureParser.getBoolean("is_patchrom", false);
        }
        return false;
    }
}
