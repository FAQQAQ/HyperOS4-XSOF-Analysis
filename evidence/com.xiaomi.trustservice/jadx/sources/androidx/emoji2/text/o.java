package androidx.emoji2.text;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Trace;
import android.os.UserHandle;
import android.util.Log;
import android.view.View;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.security.MessageDigest;
import java.util.List;
import org.json.JSONStringer;

/* JADX INFO: loaded from: classes.dex */
public abstract class o implements M.l {
    public static long a;
    public static Method b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Application f782c;

    public static void A(Parcel parcel, Parcelable parcelable, int i2) {
        if (parcelable == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, i2);
        }
    }

    public static Object B(Parcel parcel, Parcelable.Creator creator) {
        if (parcel.readInt() != 0) {
            return creator.createFromParcel(parcel);
        }
        return null;
    }

    public static void C(Throwable th, Throwable th2) {
        l1.c.c(th, "<this>");
        l1.c.c(th2, "exception");
        if (th != th2) {
            p013g1.c.a.a(th, th2);
        }
    }

    public static void D(String str, String str2, Exception exc) {
        Log.w(str, "[XMS][SDK_BASE_VERSION: 1.0.6] " + str2, exc);
    }

    public static String E(String str) {
        try {
            JSONStringer jSONStringer = new JSONStringer();
            jSONStringer.object();
            jSONStringer.key("status").value("error");
            jSONStringer.key("error_code").value(str);
            jSONStringer.endObject();
            return jSONStringer.toString();
        } catch (E1.b unused) {
            return "{\"status\":\"error\",\"error_code\":\"UNKNOWN\"}";
        }
    }

    public static String F(int i2, String str, String str2, String str3, E1.c cVar, String str4, String[] strArr) {
        try {
            JSONStringer jSONStringer = new JSONStringer();
            jSONStringer.object();
            jSONStringer.key("status").value("success");
            jSONStringer.key("version").value(i2);
            jSONStringer.key("nonce").value(str);
            jSONStringer.key("fid").value(str2);
            jSONStringer.key("package_name").value(str3);
            jSONStringer.key("extra").object();
            if (cVar != null) {
                jSONStringer.key("bl_lock").value(cVar.m("bl_lock", ""));
                jSONStringer.key("hwc").value(cVar.m("hwc", ""));
                jSONStringer.key("hwlevel").value(cVar.m("hwlevel", ""));
            } else {
                jSONStringer.key("bl_lock").value("");
                jSONStringer.key("hwc").value("");
                jSONStringer.key("hwlevel").value("");
            }
            jSONStringer.endObject();
            jSONStringer.key("signature").value(str4);
            jSONStringer.key("cert_chain").array();
            for (String str5 : strArr) {
                jSONStringer.value(str5);
            }
            jSONStringer.endArray();
            jSONStringer.endObject();
            return jSONStringer.toString();
        } catch (E1.b e2) {
            Log.e("SignatureBuilder", "JSON construction failed", e2);
            return E("RESULT_BUILD_FAILURE");
        }
    }

    public static int G(int i2, String str) {
        try {
            Method declaredMethod = ActivityManager.class.getDeclaredMethod("checkUidPermission", String.class, Integer.TYPE);
            declaredMethod.setAccessible(true);
            return ((Integer) declaredMethod.invoke(null, str, Integer.valueOf(i2))).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static void H(String str, String str2) {
        Log.d(str, "[XMS][SDK_AUTH_VERSION: 1.0.2] " + str2);
    }

    public static Application I() {
        Application application = f782c;
        if (application != null) {
            return application;
        }
        throw new IllegalStateException("Need call XmsBase.init() first!");
    }

    public static int J(int i2) {
        try {
            Method declaredMethod = UserHandle.class.getDeclaredMethod("getAppId", Integer.TYPE);
            declaredMethod.setAccessible(true);
            return ((Integer) declaredMethod.invoke(null, Integer.valueOf(i2))).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static String K(PackageInfo packageInfo) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA256");
            messageDigest.update(packageInfo.signingInfo.getApkContentsSigners()[0].toByteArray());
            StringBuilder sb = new StringBuilder();
            byte[] bArrDigest = messageDigest.digest();
            int length = bArrDigest.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (i2 > 0) {
                    sb.append(":");
                }
                sb.append(Integer.toString((bArrDigest[i2] & 255) + 256, 16).substring(1));
            }
            return sb.toString().toUpperCase();
        } catch (Exception unused) {
            return "";
        }
    }

    public static void L(String str) {
        Log.i("AuthConnectManager", "[XMS][SDK_AUTH_VERSION: 1.0.2] " + str);
    }

    public static boolean M() {
        try {
            if (b == null) {
                return Trace.isEnabled();
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        try {
            if (b == null) {
                a = Trace.class.getField("TRACE_TAG_APP").getLong(null);
                b = Trace.class.getMethod("isTagEnabled", Long.TYPE);
            }
            return ((Boolean) b.invoke(null, Long.valueOf(a))).booleanValue();
        } catch (Exception e2) {
            if (!(e2 instanceof InvocationTargetException)) {
                Log.v("Trace", "Unable to call isTagEnabled via reflection", e2);
                return false;
            }
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            throw new RuntimeException(cause);
        }
    }

    public static Q.b Q(MappedByteBuffer mappedByteBuffer) throws IOException {
        long j2;
        ByteBuffer byteBufferDuplicate = mappedByteBuffer.duplicate();
        byteBufferDuplicate.order(ByteOrder.BIG_ENDIAN);
        byteBufferDuplicate.position(byteBufferDuplicate.position() + 4);
        int i2 = byteBufferDuplicate.getShort() & 65535;
        if (i2 > 100) {
            throw new IOException("Cannot read metadata.");
        }
        byteBufferDuplicate.position(byteBufferDuplicate.position() + 6);
        int i3 = 0;
        while (true) {
            if (i3 >= i2) {
                j2 = -1;
                break;
            }
            int i4 = byteBufferDuplicate.getInt();
            byteBufferDuplicate.position(byteBufferDuplicate.position() + 4);
            j2 = ((long) byteBufferDuplicate.getInt()) & 4294967295L;
            byteBufferDuplicate.position(byteBufferDuplicate.position() + 4);
            if (1835365473 == i4) {
                break;
            }
            i3++;
        }
        if (j2 != -1) {
            byteBufferDuplicate.position(byteBufferDuplicate.position() + ((int) (j2 - ((long) byteBufferDuplicate.position()))));
            byteBufferDuplicate.position(byteBufferDuplicate.position() + 12);
            long j3 = ((long) byteBufferDuplicate.getInt()) & 4294967295L;
            for (int i5 = 0; i5 < j3; i5++) {
                int i6 = byteBufferDuplicate.getInt();
                long j4 = ((long) byteBufferDuplicate.getInt()) & 4294967295L;
                byteBufferDuplicate.getInt();
                if (1164798569 == i6 || 1701669481 == i6) {
                    byteBufferDuplicate.position((int) (j4 + j2));
                    Q.b bVar = new Q.b();
                    byteBufferDuplicate.order(ByteOrder.LITTLE_ENDIAN);
                    int iPosition = byteBufferDuplicate.position() + byteBufferDuplicate.getInt(byteBufferDuplicate.position());
                    bVar.d = byteBufferDuplicate;
                    bVar.a = iPosition;
                    int i7 = iPosition - byteBufferDuplicate.getInt(iPosition);
                    bVar.b = i7;
                    bVar.f170c = ((ByteBuffer) bVar.d).getShort(i7);
                    return bVar;
                }
            }
        }
        throw new IOException("Cannot read metadata.");
    }

    public static void R(Parcel parcel, Parcelable parcelable, int i2) {
        if (parcelable == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, i2);
        }
    }

    public static Object e(Parcel parcel, Parcelable.Creator creator) {
        if (parcel.readInt() != 0) {
            return creator.createFromParcel(parcel);
        }
        return null;
    }

    public static Object f(Parcel parcel, Parcelable.Creator creator) {
        if (parcel.readInt() != 0) {
            return creator.createFromParcel(parcel);
        }
        return null;
    }

    public static Object g(Parcel parcel, Parcelable.Creator creator) {
        if (parcel.readInt() != 0) {
            return creator.createFromParcel(parcel);
        }
        return null;
    }

    public static Object h(Parcel parcel, Parcelable.Creator creator) {
        if (parcel.readInt() != 0) {
            return creator.createFromParcel(parcel);
        }
        return null;
    }

    public static Object i(Parcel parcel, Parcelable.Creator creator) {
        if (parcel.readInt() != 0) {
            return creator.createFromParcel(parcel);
        }
        return null;
    }

    public static Object j(Parcel parcel, Parcelable.Creator creator) {
        if (parcel.readInt() != 0) {
            return creator.createFromParcel(parcel);
        }
        return null;
    }

    public static Object k(Parcel parcel, Parcelable.Creator creator) {
        if (parcel.readInt() != 0) {
            return creator.createFromParcel(parcel);
        }
        return null;
    }

    public static Object l(Parcel parcel, Parcelable.Creator creator) {
        if (parcel.readInt() != 0) {
            return creator.createFromParcel(parcel);
        }
        return null;
    }

    public static void m(Parcel parcel, List list) {
        if (list == null) {
            parcel.writeInt(-1);
            return;
        }
        int size = list.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            R(parcel, (Parcelable) list.get(i2), 1);
        }
    }

    public static void n(Parcel parcel, Parcelable parcelable) {
        if (parcelable == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, 0);
        }
    }

    public static void o(Parcel parcel, Parcelable parcelable, int i2) {
        if (parcelable == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, i2);
        }
    }

    public static void p(Parcel parcel, Parcelable parcelable) {
        if (parcelable == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, 0);
        }
    }

    public static void q(Parcel parcel, Parcelable parcelable) {
        if (parcelable == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, 0);
        }
    }

    public static void r(Parcel parcel, Parcelable parcelable) {
        if (parcelable == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, 0);
        }
    }

    public static void s(Parcel parcel, Parcelable parcelable) {
        if (parcelable == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, 0);
        }
    }

    public static void t(Parcel parcel, Parcelable parcelable) {
        if (parcelable == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, 0);
        }
    }

    public static void u(Parcel parcel, Parcelable parcelable) {
        if (parcelable == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, 0);
        }
    }

    public static void v(Parcel parcel, Parcelable parcelable) {
        if (parcelable == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, 0);
        }
    }

    public static void w(Parcel parcel, Parcelable parcelable) {
        if (parcelable == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, 0);
        }
    }

    public static Object x(Object obj, p004c1.o oVar, Class... clsArr) {
        if (obj != null) {
            try {
                Class<?> cls = obj.getClass();
                p004c1.p pVar = new p004c1.p(obj, oVar);
                if (clsArr.length == 0) {
                    clsArr = cls.getInterfaces();
                }
                return Proxy.newProxyInstance(cls.getClassLoader(), clsArr, pVar);
            } catch (Exception e2) {
                z("ObjectProxy", "create error", e2);
            }
        }
        return null;
    }

    public static void y(String str, String str2) {
        Log.i(str, "[XMS][SDK_BASE_VERSION: 1.0.6] " + str2);
    }

    public static void z(String str, String str2, Throwable th) {
        Log.e(str, "[XMS][SDK_BASE_VERSION: 1.0.6] " + str2, th);
    }

    public abstract View N(int i2);

    public abstract boolean O();

    public abstract Object P(int i2, Intent intent);
}
