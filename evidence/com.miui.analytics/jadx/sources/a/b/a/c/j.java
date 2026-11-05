package a.b.a.c;

import a.b.a.e.l0.p;
import a.b.a.e.l0.r;
import a.b.a.e.l0.u;
import a.b.a.e.y;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.BuildConfig;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class j {
    public static volatile j i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f27a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a.b.a.c.v.m f28b;
    public String e;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f29c = "";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f30d = "";
    public int f = 0;
    public Runnable g = new b();
    public Runnable h = new c();

    public class a implements Runnable {
        public a() {
        }

        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
        @Override // java.lang.Runnable
        public void run() {
            if (a.b.a.a.a.c.a.m0()) {
                j jVar = j.this;
                jVar.e = jVar.h();
                a.b.a.a.a.c.a.x(j.this.g);
                j.this.j(System.currentTimeMillis() + ((long) (new Random().nextDouble() * 4.32E7d)) + 64800000);
            }
        }
    }

    public class b implements Runnable {
        public b() {
        }

        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
        /* JADX WARN: Code duplicated, block: B:110:0x0492 A[Catch: Exception -> 0x050c, TRY_LEAVE, TryCatch #19 {Exception -> 0x050c, blocks: (B:95:0x0443, B:97:0x044f, B:99:0x0455, B:101:0x0467, B:103:0x046b, B:105:0x0482, B:107:0x0488, B:110:0x0492, B:113:0x04aa, B:111:0x049d), top: B:244:0x0443, inners: #5 }] */
        /* JADX WARN: Code duplicated, block: B:123:0x04dc  */
        /* JADX WARN: Unreachable blocks removed: 1, instructions: 1 */
        @Override // java.lang.Runnable
        public void run() {
            int i;
            String str;
            String str2;
            String str3;
            StringBuilder sb;
            StringBuilder sb2;
            boolean z;
            JSONObject jSONObject;
            String strOptString;
            int iOptInt;
            long jCurrentTimeMillis;
            b bVar = this;
            String str4 = "ts";
            String str5 = "r";
            String str6 = "p";
            String str7 = "nonce";
            String str8 = "n";
            String str9 = "m";
            String str10 = "f";
            String str11 = "v";
            String str12 = "=";
            String str13 = "";
            String str14 = "UTF-8";
            int i2 = 0;
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            String str15 = "UpdateManager";
            String str16 = "&";
            while (true) {
                long j = jCurrentTimeMillis2;
                while (true) {
                    i = i2 + 1;
                    str = str12;
                    if (i2 >= 2) {
                        return;
                    }
                    try {
                        String strM = a.b.a.a.a.c.a.M(j.this.f27a);
                        String strB = a.b.a.c.v.n.b();
                        str2 = str14;
                        try {
                            String strL = a.b.a.c.v.n.l();
                            str3 = str11;
                            try {
                                String strG = a.b.a.c.v.n.g(j.this.f27a);
                                j = j;
                                try {
                                    String strF = a.b.a.c.v.n.f(j.this.f27a, BuildConfig.APPLICATION_ID);
                                    String str17 = Build.MODEL;
                                    str4 = str4;
                                    try {
                                        int iB = a.b.a.c.v.k.b(j.this.f27a);
                                        str5 = str5;
                                        try {
                                            String strA = j.a(j.this);
                                            String str18 = str6;
                                            try {
                                                String packageName = j.this.f27a.getPackageName();
                                                try {
                                                    String strM2 = a.b.a.c.v.n.m();
                                                    String str19 = Build.VERSION.INCREMENTAL;
                                                    StringBuilder sb3 = new StringBuilder();
                                                    sb3.append("av0.0.0");
                                                    sb3.append("cv" + strM);
                                                    sb3.append("d" + strB);
                                                    sb3.append(str10 + strL);
                                                    if (a.b.a.c.v.h.f()) {
                                                        sb = new StringBuilder();
                                                        sb.append("i");
                                                        sb.append(strF);
                                                    } else {
                                                        sb = new StringBuilder();
                                                        sb.append("i");
                                                        sb.append(strG);
                                                    }
                                                    sb3.append(sb.toString());
                                                    sb3.append(str9 + str17);
                                                    sb3.append(str8 + iB);
                                                    sb3.append(str7 + strA);
                                                    StringBuilder sb4 = new StringBuilder();
                                                    try {
                                                        sb4.append(str18);
                                                        str6 = str18;
                                                        sb4.append(packageName);
                                                        sb3.append(sb4.toString());
                                                        StringBuilder sb5 = new StringBuilder();
                                                        try {
                                                            sb5.append(str5);
                                                            str5 = str5;
                                                            sb5.append(strM2);
                                                            sb3.append(sb5.toString());
                                                            StringBuilder sb6 = new StringBuilder();
                                                            try {
                                                                sb6.append(str4);
                                                                try {
                                                                    sb6.append(j);
                                                                    sb3.append(sb6.toString());
                                                                    StringBuilder sb7 = new StringBuilder();
                                                                    j = j;
                                                                    try {
                                                                        sb7.append(str3);
                                                                        sb7.append(str19);
                                                                        sb3.append(sb7.toString());
                                                                        sb3.append("miui_sdkconfig_jafej!@#)(*e@!#");
                                                                        String lowerCase = a.b.a.c.v.c.b(sb3.toString()).toLowerCase(Locale.getDefault());
                                                                        ConcurrentHashMap<String, String> concurrentHashMap = y.f663c;
                                                                        y yVar = y.b.f667a;
                                                                        str3 = str3;
                                                                        try {
                                                                            StringBuilder sb8 = new StringBuilder(yVar.a("https://", yVar.c(), "/api/checkupdate/lastusefulversion2"));
                                                                            StringBuilder sb9 = new StringBuilder();
                                                                            sb9.append("?");
                                                                            str2 = str2;
                                                                            try {
                                                                                sb9.append(URLEncoder.encode("av", str2));
                                                                                str = str;
                                                                                try {
                                                                                    sb9.append(str);
                                                                                    str7 = str7;
                                                                                    try {
                                                                                        sb9.append(URLEncoder.encode("0.0.0", str2));
                                                                                        sb8.append(sb9.toString());
                                                                                        StringBuilder sb10 = new StringBuilder();
                                                                                        str16 = str16;
                                                                                        try {
                                                                                            sb10.append(str16);
                                                                                            String str20 = str8;
                                                                                            try {
                                                                                                sb10.append(URLEncoder.encode("cv", str2));
                                                                                                sb10.append(str);
                                                                                                sb10.append(URLEncoder.encode(strM, str2));
                                                                                                sb8.append(sb10.toString());
                                                                                                sb8.append(str16 + URLEncoder.encode("d", str2) + str + URLEncoder.encode(strB, str2));
                                                                                                sb8.append(str16 + URLEncoder.encode(str10, str2) + str + URLEncoder.encode(strL, str2));
                                                                                                if (a.b.a.c.v.h.f()) {
                                                                                                    sb2 = new StringBuilder();
                                                                                                    sb2.append(str16);
                                                                                                    sb2.append(URLEncoder.encode("i", str2));
                                                                                                    sb2.append(str);
                                                                                                    sb2.append(URLEncoder.encode(strF, str2));
                                                                                                } else {
                                                                                                    sb2 = new StringBuilder();
                                                                                                    sb2.append(str16);
                                                                                                    sb2.append(URLEncoder.encode("i", str2));
                                                                                                    sb2.append(str);
                                                                                                    sb2.append(URLEncoder.encode(strG, str2));
                                                                                                }
                                                                                                sb8.append(sb2.toString());
                                                                                                sb8.append(str16 + URLEncoder.encode(str9, str2) + str + URLEncoder.encode(str17, str2));
                                                                                                StringBuilder sb11 = new StringBuilder();
                                                                                                sb11.append(str16);
                                                                                                str8 = str20;
                                                                                                sb11.append(URLEncoder.encode(str8, str2));
                                                                                                sb11.append(str);
                                                                                                sb11.append(URLEncoder.encode(String.valueOf(iB), str2));
                                                                                                sb8.append(sb11.toString());
                                                                                                StringBuilder sb12 = new StringBuilder();
                                                                                                sb12.append(str16);
                                                                                                str7 = str7;
                                                                                                try {
                                                                                                    sb12.append(URLEncoder.encode(str7, str2));
                                                                                                    sb12.append(str);
                                                                                                    sb12.append(URLEncoder.encode(strA, str2));
                                                                                                    sb8.append(sb12.toString());
                                                                                                    StringBuilder sb13 = new StringBuilder();
                                                                                                    sb13.append(str16);
                                                                                                    str6 = str6;
                                                                                                    try {
                                                                                                        sb13.append(URLEncoder.encode(str6, str2));
                                                                                                        sb13.append(str);
                                                                                                        sb13.append(URLEncoder.encode(packageName, str2));
                                                                                                        sb8.append(sb13.toString());
                                                                                                        StringBuilder sb14 = new StringBuilder();
                                                                                                        sb14.append(str16);
                                                                                                        str5 = str5;
                                                                                                        try {
                                                                                                            sb14.append(URLEncoder.encode(str5, str2));
                                                                                                            sb14.append(str);
                                                                                                            sb14.append(URLEncoder.encode(strM2, str2));
                                                                                                            sb8.append(sb14.toString());
                                                                                                            StringBuilder sb15 = new StringBuilder();
                                                                                                            sb15.append(str16);
                                                                                                            str4 = str4;
                                                                                                            try {
                                                                                                                sb15.append(URLEncoder.encode(str4, str2));
                                                                                                                sb15.append(str);
                                                                                                                sb15.append(URLEncoder.encode(String.valueOf(j), str2));
                                                                                                                sb8.append(sb15.toString());
                                                                                                                StringBuilder sb16 = new StringBuilder();
                                                                                                                sb16.append(str16);
                                                                                                                str3 = str3;
                                                                                                                try {
                                                                                                                    sb16.append(URLEncoder.encode(str3, str2));
                                                                                                                    sb16.append(str);
                                                                                                                    sb16.append(URLEncoder.encode(str19, str2));
                                                                                                                    sb8.append(sb16.toString());
                                                                                                                    sb8.append(str16 + URLEncoder.encode("sign", str2) + str + URLEncoder.encode(lowerCase, str2));
                                                                                                                    str15 = str15;
                                                                                                                    try {
                                                                                                                        a.b.a.c.v.i.b(str15, "url:" + sb8.toString());
                                                                                                                        try {
                                                                                                                            try {
                                                                                                                                try {
                                                                                                                                    try {
                                                                                                                                        if (r.q()) {
                                                                                                                                            String strJ = r.j();
                                                                                                                                            str = str;
                                                                                                                                            try {
                                                                                                                                                a.b.a.e.e0.a.d().k(strJ);
                                                                                                                                                if (!r.t(strJ)) {
                                                                                                                                                    z = true;
                                                                                                                                                    str2 = str2;
                                                                                                                                                }
                                                                                                                                            } catch (Exception e) {
                                                                                                                                                e = e;
                                                                                                                                                try {
                                                                                                                                                    StringBuilder sb17 = new StringBuilder();
                                                                                                                                                    sb17.append("isUseFirebaseTrack failed: ");
                                                                                                                                                    sb17.append(e.getMessage());
                                                                                                                                                    p.g("FireBaseChecker", sb17.toString());
                                                                                                                                                } catch (Exception e2) {
                                                                                                                                                    e = e2;
                                                                                                                                                    str2 = str2;
                                                                                                                                                    str16 = str16;
                                                                                                                                                    str8 = str8;
                                                                                                                                                    str9 = str9;
                                                                                                                                                    str10 = str10;
                                                                                                                                                    str13 = str13;
                                                                                                                                                    this = this;
                                                                                                                                                    str15 = str15;
                                                                                                                                                    bVar = this;
                                                                                                                                                    Context context = j.this.f27a;
                                                                                                                                                    StringBuilder sbC = a.a.a.a.a.c("mUpdater.run e=");
                                                                                                                                                    sbC.append(e.toString());
                                                                                                                                                    a.b.a.a.a.c.a.P0(context, sbC.toString(), str13);
                                                                                                                                                    Log.e(a.b.a.c.v.i.a(str15), "mUpdater task exception ", e);
                                                                                                                                                    str15 = str15;
                                                                                                                                                    str13 = str13;
                                                                                                                                                    str7 = str7;
                                                                                                                                                    str6 = str6;
                                                                                                                                                    str5 = str5;
                                                                                                                                                    str4 = str4;
                                                                                                                                                    str11 = str3;
                                                                                                                                                    i2 = i;
                                                                                                                                                    str12 = str;
                                                                                                                                                    str14 = str2;
                                                                                                                                                    str8 = str8;
                                                                                                                                                    j = j;
                                                                                                                                                    str9 = str9;
                                                                                                                                                    str10 = str10;
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                            jSONObject = new JSONObject(a.b.a.e.i0.c.n().f(z, sb8.toString()));
                                                                                                                                            strOptString = jSONObject.optString("url");
                                                                                                                                            iOptInt = jSONObject.optInt("code", 0);
                                                                                                                                            String strOptString2 = jSONObject.optString(str3);
                                                                                                                                            str16 = str16;
                                                                                                                                            str8 = str8;
                                                                                                                                            this = this;
                                                                                                                                            str9 = str9;
                                                                                                                                            str10 = str10;
                                                                                                                                            j.this.f = jSONObject.optInt("force", 0);
                                                                                                                                            if (TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2)) {
                                                                                                                                                a.b.a.g.c.c.c(System.currentTimeMillis(), strOptString2);
                                                                                                                                                n nVar = new n(strOptString2);
                                                                                                                                                if (a.b.a.c.v.h.e() || nVar.f49c == 0) {
                                                                                                                                                    j.this.f30d = jSONObject.optString("md5");
                                                                                                                                                    j jVar = j.this;
                                                                                                                                                    jVar.f29c = strOptString;
                                                                                                                                                    a.b.a.a.a.c.a.x(jVar.h);
                                                                                                                                                    return;
                                                                                                                                                }
                                                                                                                                                return;
                                                                                                                                            }
                                                                                                                                            if (iOptInt != 0 && TextUtils.isEmpty(strOptString)) {
                                                                                                                                                a.b.a.c.v.i.b(str15, "latest version, don't need to update");
                                                                                                                                                return;
                                                                                                                                            }
                                                                                                                                            if (iOptInt == -8) {
                                                                                                                                                j jVar2 = j.this;
                                                                                                                                                String strOptString3 = jSONObject.optString("failMsg");
                                                                                                                                                Objects.requireNonNull(jVar2);
                                                                                                                                                try {
                                                                                                                                                    jCurrentTimeMillis = Long.parseLong(strOptString3.split("-")[1]);
                                                                                                                                                } catch (Exception unused) {
                                                                                                                                                    jCurrentTimeMillis = System.currentTimeMillis();
                                                                                                                                                }
                                                                                                                                                try {
                                                                                                                                                    str13 = str13;
                                                                                                                                                    try {
                                                                                                                                                        a.b.a.a.a.c.a.P0(j.this.f27a, "mUpdate err -8", str13);
                                                                                                                                                        break;
                                                                                                                                                    } catch (Exception e3) {
                                                                                                                                                        e = e3;
                                                                                                                                                        j = jCurrentTimeMillis;
                                                                                                                                                        str15 = str15;
                                                                                                                                                        bVar = this;
                                                                                                                                                        Context context2 = j.this.f27a;
                                                                                                                                                        StringBuilder sbC2 = a.a.a.a.a.c("mUpdater.run e=");
                                                                                                                                                        sbC2.append(e.toString());
                                                                                                                                                        a.b.a.a.a.c.a.P0(context2, sbC2.toString(), str13);
                                                                                                                                                        Log.e(a.b.a.c.v.i.a(str15), "mUpdater task exception ", e);
                                                                                                                                                        str15 = str15;
                                                                                                                                                        str13 = str13;
                                                                                                                                                        str7 = str7;
                                                                                                                                                        str6 = str6;
                                                                                                                                                        str5 = str5;
                                                                                                                                                        str4 = str4;
                                                                                                                                                        str11 = str3;
                                                                                                                                                        i2 = i;
                                                                                                                                                        str12 = str;
                                                                                                                                                        str14 = str2;
                                                                                                                                                        str8 = str8;
                                                                                                                                                        j = j;
                                                                                                                                                        str9 = str9;
                                                                                                                                                        str10 = str10;
                                                                                                                                                    }
                                                                                                                                                } catch (Exception e4) {
                                                                                                                                                    e = e4;
                                                                                                                                                    str13 = str13;
                                                                                                                                                }
                                                                                                                                            } else {
                                                                                                                                                str13 = str13;
                                                                                                                                                try {
                                                                                                                                                    a.b.a.a.a.c.a.P0(j.this.f27a, "mUpdate err " + iOptInt, "url=" + strOptString);
                                                                                                                                                    return;
                                                                                                                                                } catch (Exception e5) {
                                                                                                                                                    e = e5;
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                            str15 = str15;
                                                                                                                                            bVar = this;
                                                                                                                                            Context context3 = j.this.f27a;
                                                                                                                                            StringBuilder sbC3 = a.a.a.a.a.c("mUpdater.run e=");
                                                                                                                                            sbC3.append(e.toString());
                                                                                                                                            a.b.a.a.a.c.a.P0(context3, sbC3.toString(), str13);
                                                                                                                                            Log.e(a.b.a.c.v.i.a(str15), "mUpdater task exception ", e);
                                                                                                                                            str15 = str15;
                                                                                                                                            str13 = str13;
                                                                                                                                            str7 = str7;
                                                                                                                                            str6 = str6;
                                                                                                                                            str5 = str5;
                                                                                                                                            str4 = str4;
                                                                                                                                            str11 = str3;
                                                                                                                                            i2 = i;
                                                                                                                                            str12 = str;
                                                                                                                                            str14 = str2;
                                                                                                                                            str8 = str8;
                                                                                                                                            j = j;
                                                                                                                                            str9 = str9;
                                                                                                                                            str10 = str10;
                                                                                                                                        } else {
                                                                                                                                            str = str;
                                                                                                                                        }
                                                                                                                                        j.this.f = jSONObject.optInt("force", 0);
                                                                                                                                        if (TextUtils.isEmpty(strOptString)) {
                                                                                                                                        }
                                                                                                                                        if (iOptInt != 0) {
                                                                                                                                        }
                                                                                                                                        if (iOptInt == -8) {
                                                                                                                                            j jVar3 = j.this;
                                                                                                                                            String strOptString4 = jSONObject.optString("failMsg");
                                                                                                                                            Objects.requireNonNull(jVar3);
                                                                                                                                            jCurrentTimeMillis = Long.parseLong(strOptString4.split("-")[1]);
                                                                                                                                            str13 = str13;
                                                                                                                                            a.b.a.a.a.c.a.P0(j.this.f27a, "mUpdate err -8", str13);
                                                                                                                                            break;
                                                                                                                                        }
                                                                                                                                        str13 = str13;
                                                                                                                                        a.b.a.a.a.c.a.P0(j.this.f27a, "mUpdate err " + iOptInt, "url=" + strOptString);
                                                                                                                                        return;
                                                                                                                                    } catch (Exception e6) {
                                                                                                                                        e = e6;
                                                                                                                                        str13 = str13;
                                                                                                                                    }
                                                                                                                                    str9 = str9;
                                                                                                                                    str10 = str10;
                                                                                                                                } catch (Exception e7) {
                                                                                                                                    e = e7;
                                                                                                                                    str9 = str9;
                                                                                                                                    str10 = str10;
                                                                                                                                    str13 = str13;
                                                                                                                                    str15 = str15;
                                                                                                                                    bVar = this;
                                                                                                                                    Context context4 = j.this.f27a;
                                                                                                                                    StringBuilder sbC4 = a.a.a.a.a.c("mUpdater.run e=");
                                                                                                                                    sbC4.append(e.toString());
                                                                                                                                    a.b.a.a.a.c.a.P0(context4, sbC4.toString(), str13);
                                                                                                                                    Log.e(a.b.a.c.v.i.a(str15), "mUpdater task exception ", e);
                                                                                                                                    str15 = str15;
                                                                                                                                    str13 = str13;
                                                                                                                                    str7 = str7;
                                                                                                                                    str6 = str6;
                                                                                                                                    str5 = str5;
                                                                                                                                    str4 = str4;
                                                                                                                                    str11 = str3;
                                                                                                                                    i2 = i;
                                                                                                                                    str12 = str;
                                                                                                                                    str14 = str2;
                                                                                                                                    str8 = str8;
                                                                                                                                    j = j;
                                                                                                                                    str9 = str9;
                                                                                                                                    str10 = str10;
                                                                                                                                }
                                                                                                                                jSONObject = new JSONObject(a.b.a.e.i0.c.n().f(z, sb8.toString()));
                                                                                                                                strOptString = jSONObject.optString("url");
                                                                                                                                iOptInt = jSONObject.optInt("code", 0);
                                                                                                                                String strOptString5 = jSONObject.optString(str3);
                                                                                                                                str16 = str16;
                                                                                                                                str8 = str8;
                                                                                                                                this = this;
                                                                                                                            } catch (Exception e8) {
                                                                                                                                e = e8;
                                                                                                                                str16 = str16;
                                                                                                                                str8 = str8;
                                                                                                                                str9 = str9;
                                                                                                                                str10 = str10;
                                                                                                                                str13 = str13;
                                                                                                                                this = this;
                                                                                                                            }
                                                                                                                        } catch (Exception e9) {
                                                                                                                            e = e9;
                                                                                                                            str = str;
                                                                                                                        }
                                                                                                                        z = false;
                                                                                                                    } catch (Exception e10) {
                                                                                                                        e = e10;
                                                                                                                        str = str;
                                                                                                                    }
                                                                                                                } catch (Exception e11) {
                                                                                                                    e = e11;
                                                                                                                    str = str;
                                                                                                                    str2 = str2;
                                                                                                                    str16 = str16;
                                                                                                                    str8 = str8;
                                                                                                                    str9 = str9;
                                                                                                                    str10 = str10;
                                                                                                                    str13 = str13;
                                                                                                                    str15 = str15;
                                                                                                                }
                                                                                                            } catch (Exception e12) {
                                                                                                                e = e12;
                                                                                                                str3 = str3;
                                                                                                                this = this;
                                                                                                                str15 = str15;
                                                                                                                bVar = this;
                                                                                                                Context context5 = j.this.f27a;
                                                                                                                StringBuilder sbC5 = a.a.a.a.a.c("mUpdater.run e=");
                                                                                                                sbC5.append(e.toString());
                                                                                                                a.b.a.a.a.c.a.P0(context5, sbC5.toString(), str13);
                                                                                                                Log.e(a.b.a.c.v.i.a(str15), "mUpdater task exception ", e);
                                                                                                                str15 = str15;
                                                                                                                str13 = str13;
                                                                                                                str7 = str7;
                                                                                                                str6 = str6;
                                                                                                                str5 = str5;
                                                                                                                str4 = str4;
                                                                                                                str11 = str3;
                                                                                                                i2 = i;
                                                                                                                str12 = str;
                                                                                                                str14 = str2;
                                                                                                                str8 = str8;
                                                                                                                j = j;
                                                                                                                str9 = str9;
                                                                                                                str10 = str10;
                                                                                                            }
                                                                                                        } catch (Exception e13) {
                                                                                                            e = e13;
                                                                                                            str4 = str4;
                                                                                                        }
                                                                                                    } catch (Exception e14) {
                                                                                                        e = e14;
                                                                                                        str = str;
                                                                                                        str2 = str2;
                                                                                                        str16 = str16;
                                                                                                        str8 = str8;
                                                                                                        str3 = str3;
                                                                                                        this = this;
                                                                                                        str10 = str10;
                                                                                                    }
                                                                                                } catch (Exception e15) {
                                                                                                    e = e15;
                                                                                                    str = str;
                                                                                                    str2 = str2;
                                                                                                    str16 = str16;
                                                                                                    str8 = str8;
                                                                                                    str6 = str6;
                                                                                                    str3 = str3;
                                                                                                    this = this;
                                                                                                    str10 = str10;
                                                                                                    str15 = str15;
                                                                                                    bVar = this;
                                                                                                    Context context6 = j.this.f27a;
                                                                                                    StringBuilder sbC6 = a.a.a.a.a.c("mUpdater.run e=");
                                                                                                    sbC6.append(e.toString());
                                                                                                    a.b.a.a.a.c.a.P0(context6, sbC6.toString(), str13);
                                                                                                    Log.e(a.b.a.c.v.i.a(str15), "mUpdater task exception ", e);
                                                                                                    str15 = str15;
                                                                                                    str13 = str13;
                                                                                                    str7 = str7;
                                                                                                    str6 = str6;
                                                                                                    str5 = str5;
                                                                                                    str4 = str4;
                                                                                                    str11 = str3;
                                                                                                    i2 = i;
                                                                                                    str12 = str;
                                                                                                    str14 = str2;
                                                                                                    str8 = str8;
                                                                                                    j = j;
                                                                                                    str9 = str9;
                                                                                                    str10 = str10;
                                                                                                }
                                                                                            } catch (Exception e16) {
                                                                                                e = e16;
                                                                                                str2 = str2;
                                                                                                str9 = str9;
                                                                                                str13 = str13;
                                                                                                str15 = str15;
                                                                                                str8 = str20;
                                                                                                str7 = str7;
                                                                                                str5 = str5;
                                                                                                str6 = str6;
                                                                                                str4 = str4;
                                                                                                str3 = str3;
                                                                                                str = str;
                                                                                                str16 = str16;
                                                                                                str10 = str10;
                                                                                            }
                                                                                        } catch (Exception e17) {
                                                                                            e = e17;
                                                                                            str16 = str16;
                                                                                            str8 = str8;
                                                                                            str = str;
                                                                                            str10 = str10;
                                                                                        }
                                                                                    } catch (Exception e18) {
                                                                                        e = e18;
                                                                                        str8 = str8;
                                                                                    }
                                                                                } catch (Exception e19) {
                                                                                    e = e19;
                                                                                    str = str;
                                                                                    str2 = str2;
                                                                                    str7 = str7;
                                                                                    str8 = str8;
                                                                                    str6 = str6;
                                                                                    str3 = str3;
                                                                                    this = this;
                                                                                    str10 = str10;
                                                                                    str15 = str15;
                                                                                    bVar = this;
                                                                                    Context context7 = j.this.f27a;
                                                                                    StringBuilder sbC7 = a.a.a.a.a.c("mUpdater.run e=");
                                                                                    sbC7.append(e.toString());
                                                                                    a.b.a.a.a.c.a.P0(context7, sbC7.toString(), str13);
                                                                                    Log.e(a.b.a.c.v.i.a(str15), "mUpdater task exception ", e);
                                                                                    str15 = str15;
                                                                                    str13 = str13;
                                                                                    str7 = str7;
                                                                                    str6 = str6;
                                                                                    str5 = str5;
                                                                                    str4 = str4;
                                                                                    str11 = str3;
                                                                                    i2 = i;
                                                                                    str12 = str;
                                                                                    str14 = str2;
                                                                                    str8 = str8;
                                                                                    j = j;
                                                                                    str9 = str9;
                                                                                    str10 = str10;
                                                                                }
                                                                            } catch (Exception e20) {
                                                                                e = e20;
                                                                            }
                                                                        } catch (Exception e21) {
                                                                            e = e21;
                                                                        }
                                                                    } catch (Exception e22) {
                                                                        e = e22;
                                                                        str3 = str3;
                                                                        str7 = str7;
                                                                        str8 = str8;
                                                                        str9 = str9;
                                                                        str13 = str13;
                                                                        str15 = str15;
                                                                        str5 = str5;
                                                                        str6 = str6;
                                                                    }
                                                                } catch (Exception e23) {
                                                                    e = e23;
                                                                    j = j;
                                                                    str4 = str4;
                                                                    this = this;
                                                                    str8 = str8;
                                                                    str10 = str10;
                                                                    str13 = str13;
                                                                    str15 = str15;
                                                                    bVar = this;
                                                                    Context context8 = j.this.f27a;
                                                                    StringBuilder sbC8 = a.a.a.a.a.c("mUpdater.run e=");
                                                                    sbC8.append(e.toString());
                                                                    a.b.a.a.a.c.a.P0(context8, sbC8.toString(), str13);
                                                                    Log.e(a.b.a.c.v.i.a(str15), "mUpdater task exception ", e);
                                                                    str15 = str15;
                                                                    str13 = str13;
                                                                    str7 = str7;
                                                                    str6 = str6;
                                                                    str5 = str5;
                                                                    str4 = str4;
                                                                    str11 = str3;
                                                                    i2 = i;
                                                                    str12 = str;
                                                                    str14 = str2;
                                                                    str8 = str8;
                                                                    j = j;
                                                                    str9 = str9;
                                                                    str10 = str10;
                                                                }
                                                            } catch (Exception e24) {
                                                                e = e24;
                                                                str4 = str4;
                                                            }
                                                        } catch (Exception e25) {
                                                            e = e25;
                                                            str5 = str5;
                                                            str7 = str7;
                                                            str10 = str10;
                                                            str15 = str15;
                                                            str3 = str3;
                                                            str4 = str4;
                                                            str6 = str6;
                                                            this = this;
                                                            str8 = str8;
                                                            str9 = str9;
                                                        }
                                                    } catch (Exception e26) {
                                                        e = e26;
                                                        str6 = str18;
                                                        this = this;
                                                        str8 = str8;
                                                        str9 = str9;
                                                        str10 = str10;
                                                        str13 = str13;
                                                        str15 = str15;
                                                        bVar = this;
                                                        Context context9 = j.this.f27a;
                                                        StringBuilder sbC9 = a.a.a.a.a.c("mUpdater.run e=");
                                                        sbC9.append(e.toString());
                                                        a.b.a.a.a.c.a.P0(context9, sbC9.toString(), str13);
                                                        Log.e(a.b.a.c.v.i.a(str15), "mUpdater task exception ", e);
                                                        str15 = str15;
                                                        str13 = str13;
                                                        str7 = str7;
                                                        str6 = str6;
                                                        str5 = str5;
                                                        str4 = str4;
                                                        str11 = str3;
                                                        i2 = i;
                                                        str12 = str;
                                                        str14 = str2;
                                                        str8 = str8;
                                                        j = j;
                                                        str9 = str9;
                                                        str10 = str10;
                                                    }
                                                } catch (Exception e27) {
                                                    e = e27;
                                                    str6 = str18;
                                                }
                                                str15 = str15;
                                                bVar = this;
                                            } catch (Exception e28) {
                                                e = e28;
                                                str6 = str18;
                                                str13 = str13;
                                            }
                                        } catch (Exception e29) {
                                            e = e29;
                                            str6 = str6;
                                        }
                                    } catch (Exception e30) {
                                        e = e30;
                                        str5 = str5;
                                        str6 = str6;
                                        str7 = str7;
                                        str15 = str15;
                                        str3 = str3;
                                        str4 = str4;
                                    }
                                } catch (Exception e31) {
                                    e = e31;
                                    str4 = str4;
                                    str5 = str5;
                                    str6 = str6;
                                    str7 = str7;
                                    str15 = str15;
                                    str3 = str3;
                                }
                            } catch (Exception e32) {
                                e = e32;
                                str3 = str3;
                                str15 = str15;
                            }
                        } catch (Exception e33) {
                            e = e33;
                            str3 = str11;
                        }
                    } catch (Exception e34) {
                        e = e34;
                        str8 = str8;
                        str9 = str9;
                        str10 = str10;
                        str2 = str14;
                        str3 = str11;
                        j = j;
                        str13 = str13;
                        str4 = str4;
                        str5 = str5;
                        str6 = str6;
                        str7 = str7;
                        str15 = str15;
                    }
                    Context context10 = j.this.f27a;
                    StringBuilder sbC10 = a.a.a.a.a.c("mUpdater.run e=");
                    sbC10.append(e.toString());
                    a.b.a.a.a.c.a.P0(context10, sbC10.toString(), str13);
                    Log.e(a.b.a.c.v.i.a(str15), "mUpdater task exception ", e);
                    str15 = str15;
                    str13 = str13;
                    str7 = str7;
                    str6 = str6;
                    str5 = str5;
                    str4 = str4;
                    str11 = str3;
                    i2 = i;
                    str12 = str;
                    str14 = str2;
                    str8 = str8;
                    j = j;
                    str9 = str9;
                    str10 = str10;
                }
                str15 = str15;
                bVar = this;
                str13 = str13;
                str7 = str7;
                str6 = str6;
                str11 = str3;
                i2 = i;
                str12 = str;
                str14 = str2;
                str8 = str8;
                str9 = str9;
                str10 = str10;
                jCurrentTimeMillis2 = jCurrentTimeMillis;
                str5 = str5;
                str4 = str4;
            }
        }
    }

    public class c implements Runnable {
        public c() {
        }

        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
        /* JADX WARN: Code duplicated, block: B:12:0x003e  */
        @Override // java.lang.Runnable
        public void run() throws Throwable {
            FileOutputStream fileOutputStream;
            boolean z;
            FileOutputStream fileOutputStream2 = null;
            try {
                try {
                    if (r.q()) {
                        String strJ = r.j();
                        a.b.a.e.e0.a.d().k(strJ);
                        if (r.t(strJ)) {
                            z = false;
                        } else {
                            z = true;
                        }
                    } else {
                        z = false;
                    }
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = null;
                    a.b.a.a.a.c.a.j(fileOutputStream);
                    throw th;
                }
            } catch (Exception e) {
                try {
                    p.g("FireBaseChecker", "isUseFirebaseTrack failed: " + e.getMessage());
                } catch (Exception e2) {
                    e = e2;
                    a.b.a.a.a.c.a.P0(j.this.f27a, "mDownloader e=" + e.toString(), "");
                    Log.e(a.b.a.c.v.i.a("UpdateManager"), "mDownloader e", e);
                    a.b.a.a.a.c.a.j(fileOutputStream2);
                }
            }
            byte[] bArrG = a.b.a.e.i0.c.n().g(z, j.this.f29c);
            if (!TextUtils.isEmpty(j.this.f30d)) {
                if (!j.this.f30d.equalsIgnoreCase(a.b.a.c.v.c.c(bArrG))) {
                    bArrG = null;
                }
            }
            if (bArrG != null) {
                Log.d(a.b.a.c.v.i.a("UpdateManager"), "download apk success.");
                File file = new File(j.this.e + ".tmp");
                fileOutputStream = new FileOutputStream(file);
                try {
                    fileOutputStream.write(bArrG);
                    if (a.b.a.c.v.c.g(a.b.a.a.a.c.a.d0(j.this.f27a, file))) {
                        Log.d(a.b.a.c.v.i.a("UpdateManager"), "verify signature success");
                        File file2 = new File(j.this.e);
                        file.renameTo(file2);
                        file2.setReadable(true, false);
                        j.b(j.this);
                    } else {
                        Log.e(a.b.a.c.v.i.a("UpdateManager"), "verify signature failed");
                        a.b.a.a.a.c.a.P0(j.this.f27a, "update app signature not matched", "downloadUrl: " + j.this.f29c);
                    }
                    fileOutputStream2 = fileOutputStream;
                } catch (Exception e3) {
                    e = e3;
                    fileOutputStream2 = fileOutputStream;
                    a.b.a.a.a.c.a.P0(j.this.f27a, "mDownloader e=" + e.toString(), "");
                    Log.e(a.b.a.c.v.i.a("UpdateManager"), "mDownloader e", e);
                } catch (Throwable th2) {
                    th = th2;
                    a.b.a.a.a.c.a.j(fileOutputStream);
                    throw th;
                }
            } else {
                a.b.a.a.a.c.a.P0(j.this.f27a, "mDownloader data is null", "");
            }
            a.b.a.a.a.c.a.j(fileOutputStream2);
        }
    }

    public j(Context context) {
        Context contextI = a.b.a.a.a.c.a.I(context);
        this.f27a = contextI;
        this.f28b = new a.b.a.c.v.m(contextI, "co", "updater");
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static String a(j jVar) {
        Objects.requireNonNull(jVar);
        Random random = new Random(System.nanoTime());
        try {
            return a.b.a.c.v.c.b(jVar.f27a.getPackageName() + ":" + random.nextLong());
        } catch (Exception unused) {
            return a.b.a.c.v.c.b(random.nextLong() + "");
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public static void b(j jVar) {
        Objects.requireNonNull(jVar);
        try {
            if (jVar.f == 1) {
                new a.b.a.c.c().a(jVar.f27a, jVar.e);
            }
        } catch (Exception e) {
            Context context = jVar.f27a;
            StringBuilder sbC = a.a.a.a.a.c("notifyApkUpdated e=");
            sbC.append(e.toString());
            a.b.a.a.a.c.a.P0(context, sbC.toString(), "");
        }
    }

    public static j i(Context context) {
        if (i == null) {
            synchronized (j.class) {
                if (i == null) {
                    i = new j(context);
                }
            }
        }
        return i;
    }

    public final void c(boolean z, String str, String str2, IOException iOException, String str3) {
        a.b.a.g.f.a.c(z, str, str2, 1, iOException, "UpdateManager", str3);
        a.b.a.g.f.a.d(z, str, 1, iOException, "UpdateManager");
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public synchronized void d() {
        try {
            if (a.b.a.c.v.h.j(this.f27a, "UpdateManager")) {
                return;
            }
            if (a.b.a.b.a.d()) {
                return;
            }
            boolean z = false;
            if (!a.b.a.c.v.h.j(this.f27a, "UpdateManager")) {
                long jD = this.f28b.d("updateTime", 0L);
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis > jD || jD - jCurrentTimeMillis > 172800000) {
                    z = true;
                }
            }
            if (z && a.b.a.a.a.c.a.m0()) {
                Log.d(a.b.a.c.v.i.a("UpdateManager"), "checkUpdate ");
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                if (a.b.a.e.l0.e.a().b(jCurrentTimeMillis2, u.e()) && a.b.a.e.l0.e.a().c(jCurrentTimeMillis2)) {
                    a.b.a.e.l0.e.a().e("checkUpdate", 3, new a());
                } else if (!a.b.a.e.l0.e.a().f574b.contains("checkUpdate")) {
                    this.e = h();
                    a.b.a.a.a.c.a.x(this.g);
                    j(System.currentTimeMillis() + ((long) (new Random().nextDouble() * 4.32E7d)) + 64800000);
                }
            }
        } catch (Throwable th) {
            a.b.a.c.v.i.d("UpdateManager", "checkUpdate:", th);
        }
    }

    public void e() {
        try {
            File file = new File(a.b.a.a.a.c.a.c0(this.f27a, "modules"));
            if (file.exists() && file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    if (file2.exists() && file2.isFile()) {
                        String absolutePath = file2.getAbsolutePath();
                        boolean zDelete = file2.delete();
                        String strA = a.b.a.c.v.i.a("UpdateManager");
                        Object[] objArr = new Object[2];
                        objArr[0] = zDelete ? "success" : "failed";
                        objArr[1] = absolutePath;
                        Log.d(strA, String.format("clean modules %s, %s ", objArr));
                    }
                }
            }
        } catch (Exception e) {
            Log.e(a.b.a.c.v.i.a("UpdateManager"), "cleanCacheFiles exception:", e);
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public String f(boolean z, String str) {
        String host;
        IOException iOException;
        String str2;
        Context context;
        String string;
        if (TextUtils.isEmpty(str)) {
            host = "";
        } else {
            try {
                host = new URI(str).getHost();
            } catch (Exception unused) {
                host = "";
            }
        }
        a.b.a.g.c.c.d(z, "U5", host, 1, "request_count");
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.connect();
            a.b.a.g.f.a.e(z, "U5", host, 1, httpURLConnection.getResponseCode(), "UpdateManager", "doCheckUpdateRequest");
            String str3 = new String(a.b.a.a.a.c.a.k0(httpURLConnection.getInputStream()));
            try {
                a.b.a.c.v.i.b("UpdateManager", "result " + str3);
                a.b.a.g.c.c.e(z, "U5", host, SystemClock.elapsedRealtime() - jElapsedRealtime);
                a.b.a.g.f.a.f(false, "U5", 1, httpURLConnection.getResponseCode(), httpURLConnection.getResponseMessage(), str3, "UpdateManager");
                return str3;
            } catch (IOException e) {
                iOException = e;
                str2 = str3;
                a.b.a.g.c.c.e(z, "U5", host, SystemClock.elapsedRealtime() - jElapsedRealtime);
                if (iOException instanceof UnknownHostException) {
                    if (new Random().nextInt() % 10 == 0) {
                        context = this.f27a;
                        string = "mUpdater.run: UnknownHostException";
                    }
                    c(z, "U5", host, iOException, "doCheckUpdateRequest");
                    return str2;
                }
                context = this.f27a;
                StringBuilder sbC = a.a.a.a.a.c("mUpdater.run: IOException=");
                sbC.append(iOException.toString());
                string = sbC.toString();
                a.b.a.a.a.c.a.P0(context, string, "");
                c(z, "U5", host, iOException, "doCheckUpdateRequest");
                return str2;
            }
        } catch (IOException e2) {
            iOException = e2;
            str2 = null;
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public byte[] g(boolean z, String str) {
        a.b.a.g.c.c.d(z, "U6", "", 1, "request_count");
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        byte[] bArrK0 = null;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.connect();
            int responseCode = httpURLConnection.getResponseCode();
            a.b.a.g.f.a.e(z, "U6", "", 1, responseCode, "UpdateManager", "doDownloadRequest");
            bArrK0 = responseCode == 200 ? a.b.a.a.a.c.a.k0(httpURLConnection.getInputStream()) : null;
            a.b.a.g.c.c.e(z, "U6", "", SystemClock.elapsedRealtime() - jElapsedRealtime);
        } catch (IOException e) {
            a.b.a.g.c.c.e(z, "U6", "", SystemClock.elapsedRealtime() - jElapsedRealtime);
            Context context = this.f27a;
            StringBuilder sbC = a.a.a.a.a.c("mDownloader.run: IOException=");
            sbC.append(e.toString());
            a.b.a.a.a.c.a.P0(context, sbC.toString(), "");
            c(z, "U6", "", e, "doDownloadRequest");
        }
        return bArrK0;
    }

    public final String h() {
        try {
            String str = new File(a.b.a.a.a.c.a.c0(this.f27a, "modules")).getAbsolutePath() + "/m.apk";
            a.b.a.c.v.i.b("UpdateManager", "apk path:" + str);
            return str;
        } catch (Exception e) {
            Log.e(a.b.a.c.v.i.a("UpdateManager"), "getApkPath exception:", e);
            Context context = this.f27a;
            StringBuilder sbC = a.a.a.a.a.c("getApkPath e=");
            sbC.append(e.toString());
            a.b.a.a.a.c.a.P0(context, sbC.toString(), "");
            return "";
        }
    }

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public synchronized void j(long j) {
        SharedPreferences.Editor editorEdit = this.f28b.f162a.edit();
        editorEdit.putLong("updateTime", j);
        editorEdit.apply();
    }
}
