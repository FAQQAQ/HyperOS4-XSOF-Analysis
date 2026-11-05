package A0;

import J.AbstractC0026s;
import J.N;
import Y.AbstractC0057y;
import Y.K;
import Y.V;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.hardware.fingerprint.FingerprintManager;
import android.media.ReflectUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.HwBinder;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.UserHandle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.util.Slog;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;
import com.xiaomi.continuity.messagecenter.MessageDataV2;
import com.xiaomi.continuity.messagecenter.MessageOptionsV3;
import com.xiaomi.continuity.netbus.StartAdvertisingOptionsV2;
import com.xiaomi.continuity.netbus.StartDiscoveryOptionsV2;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.zip.CRC32;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/* JADX INFO: loaded from: classes.dex */
public abstract class o implements N {
    public static volatile boolean a = false;
    public static Application b;

    public static void A(String str, String str2, Object... objArr) {
        q0(3, str, null, str2, objArr);
    }

    public static byte[] A0(String str) {
        if (str == null) {
            Slog.d("MiTrustService/toolClass", "hexString null");
            return null;
        }
        String lowerCase = str.toLowerCase();
        byte[] bArr = new byte[lowerCase.length() >> 1];
        int i2 = 0;
        for (int i3 = 0; i3 < lowerCase.length() && i2 <= lowerCase.length() - 1; i3++) {
            bArr[i3] = (byte) ((((byte) (Character.digit(lowerCase.charAt(i2), 16) & 255)) << 4) | ((byte) (Character.digit(lowerCase.charAt(i2 + 1), 16) & 255)));
            i2 += 2;
        }
        return bArr;
    }

    public static B.g[] B(B.g[] gVarArr) {
        if (gVarArr == null) {
            return null;
        }
        B.g[] gVarArr2 = new B.g[gVarArr.length];
        for (int i2 = 0; i2 < gVarArr.length; i2++) {
            B.g gVar = gVarArr[i2];
            B.g gVar2 = new B.g();
            gVar2.a = gVar.a;
            float[] fArr = gVar.b;
            gVar2.b = w(fArr, fArr.length);
            gVarArr2[i2] = gVar2;
        }
        return gVarArr2;
    }

    public static String B0(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            sb.append(String.format("%02x", Byte.valueOf(b2)));
        }
        return sb.toString();
    }

    public static void C(String str, String str2, Throwable th, Object... objArr) {
        q0(6, str, th, str2, objArr);
    }

    public static byte[] C0(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if ((bArr[0] & 128) != 0) {
            byteArrayOutputStream.write(2);
            byteArrayOutputStream.write(bArr.length + 1);
            byteArrayOutputStream.write(0);
            byteArrayOutputStream.write(bArr, 0, bArr.length);
        } else {
            byte[] byteArray = new BigInteger(1, bArr).toByteArray();
            byteArrayOutputStream.write(2);
            byteArrayOutputStream.write(byteArray.length);
            byteArrayOutputStream.write(byteArray, 0, byteArray.length);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static void D(int i2, int[] iArr, int[] iArr2) {
        int i3 = 0;
        long j2 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i2 > 0) {
            if (i3 < Math.min(30, i2)) {
                j2 |= (((long) iArr[i4]) & 4294967295L) << i3;
                i3 += 32;
                i4++;
            }
            iArr2[i5] = ((int) j2) & 1073741823;
            j2 >>>= 30;
            i3 -= 30;
            i2 -= 30;
            i5++;
        }
    }

    public static String E(String str) {
        if (a || str == null || str.isEmpty()) {
            return str;
        }
        int i2 = str.contains(".") ? 46 : 58;
        int iIndexOf = str.indexOf(i2);
        int iLastIndexOf = str.lastIndexOf(i2);
        if (iIndexOf == -1 || iLastIndexOf == -1 || iIndexOf == iLastIndexOf) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        while (true) {
            iIndexOf++;
            if (iIndexOf >= iLastIndexOf) {
                return sb.toString();
            }
            char cCharAt = sb.charAt(iIndexOf);
            if (cCharAt >= '0' && cCharAt <= '9') {
                sb.setCharAt(iIndexOf, (char) (cCharAt - '\r'));
            }
        }
    }

    public static void E0(String str, String str2, Object... objArr) {
        q0(5, str, null, str2, objArr);
    }

    public static String F(String str) {
        String str2;
        if (a || str == null || str.isEmpty()) {
            return str;
        }
        String[] strArrSplit = str.split(":");
        if (strArrSplit.length != 6) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < strArrSplit.length; i2++) {
            if (i2 == 0) {
                str2 = strArrSplit[i2];
            } else if (i2 == strArrSplit.length - 1) {
                sb.append(":");
                str2 = strArrSplit[i2];
            } else {
                str2 = ":**";
            }
            sb.append(str2);
        }
        return sb.toString();
    }

    public static void F0(Parcel parcel, int i2, com.xiaomi.continuity.messagecenter.d dVar) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        int iDataPosition2 = parcel.dataPosition();
        switch (dVar.a) {
            case 0:
                MessageDataV2 messageDataV2 = (MessageDataV2) dVar.f1487c;
                messageDataV2.getClass();
                parcel.writeInt(0);
                byte[] bArr = messageDataV2.a;
                if (bArr == null || bArr.length >= 1048576) {
                    messageDataV2.a = new byte[0];
                }
                int length = messageDataV2.a.length;
                Parcel parcel2 = (Parcel) dVar.b;
                parcel2.writeInt(length);
                parcel2.writeByteArray(messageDataV2.a);
                byte[] bArr2 = messageDataV2.b;
                if (bArr2 == null || bArr2.length >= 1048576) {
                    messageDataV2.b = new byte[0];
                }
                parcel2.writeInt(messageDataV2.b.length);
                parcel2.writeByteArray(messageDataV2.b);
                parcel2.writeInt(messageDataV2.f1482c);
                break;
            case 1:
                MessageOptionsV3 messageOptionsV3 = (MessageOptionsV3) dVar.f1487c;
                messageOptionsV3.getClass();
                Parcel parcel3 = (Parcel) dVar.b;
                parcel3.writeInt(0);
                parcel3.writeInt(messageOptionsV3.a);
                parcel3.writeInt(messageOptionsV3.b);
                parcel3.writeSerializable((Serializable) messageOptionsV3.f1485c);
                parcel3.writeSerializable((Serializable) messageOptionsV3.d);
                parcel3.writeInt(messageOptionsV3.f1486e);
                break;
            case 2:
            default:
                StartDiscoveryOptionsV2 startDiscoveryOptionsV2 = (StartDiscoveryOptionsV2) dVar.f1487c;
                startDiscoveryOptionsV2.getClass();
                Parcel parcel4 = (Parcel) dVar.b;
                parcel4.writeInt(2);
                parcel4.writeInt(startDiscoveryOptionsV2.a);
                parcel4.writeInt(startDiscoveryOptionsV2.b);
                parcel4.writeInt(startDiscoveryOptionsV2.f1517c);
                parcel4.writeByte(startDiscoveryOptionsV2.d ? (byte) 1 : (byte) 0);
                parcel4.writeByte(startDiscoveryOptionsV2.f1518e ? (byte) 1 : (byte) 0);
                parcel4.writeByte(startDiscoveryOptionsV2.f1519f ? (byte) 1 : (byte) 0);
                parcel4.writeByte(startDiscoveryOptionsV2.f1520g ? (byte) 1 : (byte) 0);
                parcel4.writeByte(startDiscoveryOptionsV2.h ? (byte) 1 : (byte) 0);
                parcel4.writeByte(startDiscoveryOptionsV2.f1521i ? (byte) 1 : (byte) 0);
                parcel4.writeInt(startDiscoveryOptionsV2.f1522j);
                parcel4.writeInt(startDiscoveryOptionsV2.f1523k);
                parcel4.writeInt(startDiscoveryOptionsV2.f1524l);
                parcel4.writeInt(startDiscoveryOptionsV2.f1525m);
                parcel4.writeInt(startDiscoveryOptionsV2.f1526n);
                break;
            case 3:
                StartAdvertisingOptionsV2 startAdvertisingOptionsV2 = (StartAdvertisingOptionsV2) dVar.f1487c;
                startAdvertisingOptionsV2.getClass();
                Parcel parcel5 = (Parcel) dVar.b;
                parcel5.writeInt(1);
                parcel5.writeInt(startAdvertisingOptionsV2.a);
                parcel5.writeInt(startAdvertisingOptionsV2.b);
                parcel5.writeInt(startAdvertisingOptionsV2.f1510c);
                parcel5.writeByte(startAdvertisingOptionsV2.d ? (byte) 1 : (byte) 0);
                parcel5.writeByte(startAdvertisingOptionsV2.f1511e ? (byte) 1 : (byte) 0);
                parcel5.writeInt(startAdvertisingOptionsV2.f1512f);
                break;
        }
        int iDataPosition3 = parcel.dataPosition();
        parcel.setDataPosition(iDataPosition);
        parcel.writeInt(iDataPosition3 - iDataPosition2);
        parcel.setDataPosition(iDataPosition3);
    }

    public static String G(String str) {
        StringBuilder sb;
        String strSubstring;
        if (a || str == null || str.isEmpty()) {
            return str;
        }
        if (str.length() >= 4) {
            sb = new StringBuilder();
            sb.append(str.charAt(0));
            sb.append("**");
            strSubstring = str.substring(str.length() - 2);
        } else {
            sb = new StringBuilder();
            sb.append(str.charAt(0));
            strSubstring = "*";
        }
        sb.append(strSubstring);
        return sb.toString();
    }

    public static String H(byte[] bArr) {
        if (a) {
            return Arrays.toString(bArr);
        }
        StringBuilder sb = new StringBuilder("{hash=");
        long j2 = 0;
        if (bArr != null && bArr.length != 0) {
            for (byte b2 : bArr) {
                j2 = (j2 * 131) + ((long) (b2 & 255));
            }
            j2 &= 2147483647L;
        }
        sb.append(j2);
        sb.append(", len=");
        sb.append(bArr != null ? bArr.length : 0);
        sb.append("}");
        return sb.toString();
    }

    public static String I(byte b2) {
        E1.c cVar = new E1.c();
        try {
            cVar.p(Integer.valueOf(b2), "error_code");
            return cVar.toString();
        } catch (E1.b unused) {
            return "{}";
        }
    }

    public static byte[] J(byte[] bArr) {
        if (bArr.length < 72) {
            Log.w("SEProtector", "extractStrongboxData: invalid payload");
            return null;
        }
        byte[] bArr2 = new byte[60];
        System.arraycopy(bArr, 8, bArr2, 0, 12);
        System.arraycopy(bArr, 36, bArr2, 12, 32);
        System.arraycopy(bArr, 20, bArr2, 44, 16);
        return bArr2;
    }

    public static String K(Context context) {
        Object systemService;
        if (context == null) {
            Slog.d("MiTrustService/toolClass", "getBiometricEnrollmentSignature: context is null");
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("fp:");
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null && packageManager.hasSystemFeature("android.hardware.fingerprint")) {
                ArrayList arrayListM = M((FingerprintManager) context.getSystemService("fingerprint"), "getEnrolledFingerprints", "android.hardware.fingerprint.Fingerprint");
                if (arrayListM == null) {
                    return null;
                }
                Collections.sort(arrayListM);
                Iterator it = arrayListM.iterator();
                while (it.hasNext()) {
                    sb.append((Integer) it.next());
                    sb.append(',');
                }
            }
            sb.append(";face:");
            try {
                systemService = context.getSystemService((String) Class.forName("android.hardware.face.BaseMiuiFaceManager").getField("MIUI_FACE").get(null));
            } catch (Throwable th) {
                Slog.d("MiTrustService/toolClass", "getMiuiFaceManager failed: " + th);
                systemService = null;
            }
            if (systemService != null) {
                ArrayList arrayListM2 = M(systemService, "getEnrolledFaces", "android.hardware.face.Face");
                if (arrayListM2 == null) {
                    return null;
                }
                Collections.sort(arrayListM2);
                Iterator it2 = arrayListM2.iterator();
                while (it2.hasNext()) {
                    sb.append((Integer) it2.next());
                    sb.append(',');
                }
            }
            return sb.toString();
        } catch (Throwable th2) {
            Slog.d("MiTrustService/toolClass", "getBiometricEnrollmentSignature failed: " + th2);
            return null;
        }
    }

    public static Context L() {
        Application application;
        Application application2 = b;
        if (application2 != null) {
            return application2;
        }
        try {
            Method declaredMethod = Class.forName("android.app.ActivityThread").getDeclaredMethod("currentApplication", null);
            declaredMethod.setAccessible(true);
            application = (Application) declaredMethod.invoke(null, null);
        } catch (Exception e2) {
            C("UriUtils", "getCurApplication reflect ActivityThread exception : " + e2.getMessage(), null, new Object[0]);
            application = null;
        }
        if (application != null) {
            b = application;
            U("UriUtils", "application from ActivityThread", new Object[0]);
            return application;
        }
        try {
            Method declaredMethod2 = Class.forName("android.app.AppGlobals").getDeclaredMethod("getInitialApplication", null);
            declaredMethod2.setAccessible(true);
            application = (Application) declaredMethod2.invoke(null, null);
        } catch (Exception e3) {
            C("UriUtils", "getCurApplication reflect AppGlobals exception : " + e3.getMessage(), null, new Object[0]);
        }
        b = application;
        U("UriUtils", "application from AppGlobals", new Object[0]);
        return application;
    }

    public static ArrayList M(Object obj, String str, String str2) {
        if (obj == null) {
            return new ArrayList();
        }
        try {
            Object objInvoke = obj.getClass().getMethod(str, null).invoke(obj, null);
            ArrayList arrayList = new ArrayList();
            if (objInvoke == null) {
                return arrayList;
            }
            if (!(objInvoke instanceof List)) {
                Slog.d("MiTrustService/toolClass", str.concat(" returned unexpected type"));
                return null;
            }
            Class<?> cls = Class.forName(str2);
            Method method = cls.getMethod("getBiometricId", null);
            for (Object obj2 : (List) objInvoke) {
                if (cls.isInstance(obj2)) {
                    arrayList.add((Integer) method.invoke(obj2, null));
                }
            }
            return arrayList;
        } catch (Throwable th) {
            Slog.d("MiTrustService/toolClass", "getEnrolledBiometricIds(" + str + ") failed: " + th);
            return null;
        }
    }

    public static String O(Context context, int i2) {
        try {
            return (String) ReflectUtil.callObjectMethod(context.getSystemService("security"), "getPackageNameByPid", new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(i2)});
        } catch (Exception e2) {
            Slog.e("MiTrustService", "", e2);
            return "";
        }
    }

    public static String P(PackageInfo packageInfo) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA256");
            SigningInfo signingInfo = packageInfo.signingInfo;
            Objects.requireNonNull(signingInfo);
            messageDigest.update(signingInfo.getApkContentsSigners()[0].toByteArray());
            final byte[] bArrDigest = messageDigest.digest();
            return (String) IntStream.range(0, bArrDigest.length).mapToObj(new IntFunction() { // from class: a1.b
                @Override // java.util.function.IntFunction
                public final Object apply(int i2) {
                    return String.format("%02x", Integer.valueOf(bArrDigest[i2] & 255));
                }
            }).collect(Collectors.joining());
        } catch (Exception unused) {
            return "";
        }
    }

    public static G1.a Q() {
        IHwBinder service = HwBinder.getService("vendor.xiaomi.hardware.mtdservice@1.3::IMTService", "default");
        if (service == null) {
            return null;
        }
        IHwInterface iHwInterfaceQueryLocalInterface = service.queryLocalInterface("vendor.xiaomi.hardware.mtdservice@1.3::IMTService");
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof G1.a)) {
            return (G1.a) iHwInterfaceQueryLocalInterface;
        }
        G1.a aVar = new G1.a();
        aVar.a = service;
        try {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel hwParcel2 = new HwParcel();
            try {
                service.transact(256067662, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                ArrayList stringVector = hwParcel2.readStringVector();
                hwParcel2.release();
                Iterator it = stringVector.iterator();
                while (it.hasNext()) {
                    if (((String) it.next()).equals("vendor.xiaomi.hardware.mtdservice@1.3::IMTService")) {
                        return aVar;
                    }
                }
                return null;
            } catch (Throwable th) {
                hwParcel2.release();
                throw th;
            }
        } catch (RemoteException unused) {
            return null;
        }
    }

    public static boolean T(String str) {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            return keyStore.containsAlias(str);
        } catch (Exception e2) {
            Slog.d("MiTrustService/toolClass", "check key existence failed: " + e2.getMessage());
            return false;
        }
    }

    public static void U(String str, String str2, Object... objArr) {
        q0(4, str, null, str2, objArr);
    }

    public static void V(int i2, byte[] bArr, int i3) {
        bArr[i3] = (byte) (i2 >>> 24);
        bArr[i3 + 1] = (byte) (i2 >>> 16);
        bArr[i3 + 2] = (byte) (i2 >>> 8);
        bArr[i3 + 3] = (byte) i2;
    }

    public static boolean W() {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 33) {
            if (i2 >= 32) {
                String str = Build.VERSION.CODENAME;
                if (!"REL".equals(str)) {
                    Locale locale = Locale.ROOT;
                    if (str.toUpperCase(locale).compareTo("Tiramisu".toUpperCase(locale)) >= 0) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    public static boolean X(String str) {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            SecretKey secretKey = (SecretKey) keyStore.getKey(str, null);
            if (secretKey == null) {
                Slog.d("MiTrustService/toolClass", "密钥已失效，生物信息已变化");
                return true;
            }
            Cipher.getInstance("AES/CBC/PKCS7Padding").init(1, secretKey);
            Slog.d("MiTrustService/toolClass", "密钥可用，生物信息未变化");
            return false;
        } catch (KeyPermanentlyInvalidatedException e2) {
            Slog.d("MiTrustService/toolClass", "密钥验证失败，生物信息已变化: " + e2.getMessage());
            return true;
        } catch (Exception e3) {
            Slog.d("MiTrustService/toolClass", "密钥验证失败: " + e3.getMessage());
            return true;
        }
    }

    public static boolean Y(int i2, Rect rect, Rect rect2) {
        if (i2 == 17) {
            int i3 = rect.right;
            int i4 = rect2.right;
            return (i3 > i4 || rect.left >= i4) && rect.left > rect2.left;
        }
        if (i2 == 33) {
            int i5 = rect.bottom;
            int i6 = rect2.bottom;
            return (i5 > i6 || rect.top >= i6) && rect.top > rect2.top;
        }
        if (i2 == 66) {
            int i7 = rect.left;
            int i8 = rect2.left;
            return (i7 < i8 || rect.right <= i8) && rect.right < rect2.right;
        }
        if (i2 != 130) {
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        int i9 = rect.top;
        int i10 = rect2.top;
        return (i9 < i10 || rect.bottom <= i10) && rect.bottom < rect2.bottom;
    }

    public static boolean Z(EditText editText) {
        return editText.getInputType() != 0;
    }

    public static boolean a0(MotionEvent motionEvent, int i2) {
        return (motionEvent.getSource() & i2) == i2;
    }

    public static int b0(byte[] bArr, int i2) {
        return (bArr[i2 + 3] << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16);
    }

    public static int c0(int i2, Rect rect, Rect rect2) {
        int i3;
        int i4;
        if (i2 == 17) {
            i3 = rect.left;
            i4 = rect2.right;
        } else if (i2 == 33) {
            i3 = rect.top;
            i4 = rect2.bottom;
        } else if (i2 == 66) {
            i3 = rect2.left;
            i4 = rect.right;
        } else {
            if (i2 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            i3 = rect2.top;
            i4 = rect.bottom;
        }
        return Math.max(0, i3 - i4);
    }

    public static void d(TextInputLayout textInputLayout, CheckableImageButton checkableImageButton, ColorStateList colorStateList, PorterDuff.Mode mode) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (drawable != null) {
            drawable = drawable.mutate();
            if (colorStateList == null || !colorStateList.isStateful()) {
                C.b.h(drawable, colorStateList);
            } else {
                int[] drawableState = textInputLayout.getDrawableState();
                int[] drawableState2 = checkableImageButton.getDrawableState();
                int length = drawableState.length;
                int[] iArrCopyOf = Arrays.copyOf(drawableState, drawableState.length + drawableState2.length);
                System.arraycopy(drawableState2, 0, iArrCopyOf, length, drawableState2.length);
                C.b.h(drawable, ColorStateList.valueOf(colorStateList.getColorForState(iArrCopyOf, colorStateList.getDefaultColor())));
            }
            if (mode != null) {
                C.b.i(drawable, mode);
            }
        }
        if (checkableImageButton.getDrawable() != drawable) {
            checkableImageButton.setImageDrawable(drawable);
        }
    }

    public static int d0(int i2, Rect rect, Rect rect2) {
        if (i2 != 17) {
            if (i2 != 33) {
                if (i2 != 66) {
                    if (i2 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                }
            }
            return Math.abs(((rect.width() / 2) + rect.left) - ((rect2.width() / 2) + rect2.left));
        }
        return Math.abs(((rect.height() / 2) + rect.top) - ((rect2.height() / 2) + rect2.top));
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0043  */
    /* JADX WARN: Code duplicated, block: B:25:0x0045 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:29:0x004e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:30:0x0050 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:31:0x0052 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:32:0x0054  */
    /* JADX WARN: Code duplicated, block: B:34:0x005a  */
    /* JADX WARN: Code duplicated, block: B:36:0x0060  */
    /* JADX WARN: Code duplicated, block: B:37:0x0065  */
    /* JADX WARN: Code duplicated, block: B:38:0x006a  */
    /* JADX WARN: Code duplicated, block: B:41:0x0075  */
    /* JADX WARN: Code duplicated, block: B:45:? A[RETURN, SYNTHETIC] */
    public static boolean e(int i2, Rect rect, Rect rect2, Rect rect3) {
        int iC0;
        int i3;
        int i4;
        boolean zF = f(i2, rect, rect2);
        if (f(i2, rect, rect3) || !zF) {
            return false;
        }
        if (i2 != 17) {
            if (i2 != 33) {
                if (i2 != 66) {
                    if (i2 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                    if (rect.bottom <= rect3.top) {
                        if (i2 != 17 && i2 != 66) {
                            iC0 = c0(i2, rect, rect2);
                            if (i2 != 17) {
                                i3 = rect.left;
                                i4 = rect3.left;
                            } else if (i2 != 33) {
                                i3 = rect.top;
                                i4 = rect3.top;
                            } else if (i2 != 66) {
                                i3 = rect3.right;
                                i4 = rect.right;
                            } else {
                                if (i2 == 130) {
                                    throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                                }
                                i3 = rect3.bottom;
                                i4 = rect.bottom;
                            }
                            if (iC0 < Math.max(1, i3 - i4)) {
                                return true;
                            }
                            return false;
                        }
                    }
                } else if (rect.right <= rect3.left) {
                    if (i2 != 17) {
                        iC0 = c0(i2, rect, rect2);
                        if (i2 != 17) {
                            i3 = rect.left;
                            i4 = rect3.left;
                        } else if (i2 != 33) {
                            i3 = rect.top;
                            i4 = rect3.top;
                        } else if (i2 != 66) {
                            i3 = rect3.right;
                            i4 = rect.right;
                        } else {
                            if (i2 == 130) {
                                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                            }
                            i3 = rect3.bottom;
                            i4 = rect.bottom;
                        }
                        if (iC0 < Math.max(1, i3 - i4)) {
                            return true;
                        }
                        return false;
                    }
                }
            } else if (rect.top >= rect3.bottom) {
                if (i2 != 17) {
                    iC0 = c0(i2, rect, rect2);
                    if (i2 != 17) {
                        i3 = rect.left;
                        i4 = rect3.left;
                    } else if (i2 != 33) {
                        i3 = rect.top;
                        i4 = rect3.top;
                    } else if (i2 != 66) {
                        i3 = rect3.right;
                        i4 = rect.right;
                    } else {
                        if (i2 == 130) {
                            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                        }
                        i3 = rect3.bottom;
                        i4 = rect.bottom;
                    }
                    if (iC0 < Math.max(1, i3 - i4)) {
                        return true;
                    }
                    return false;
                }
            }
        } else if (rect.left >= rect3.right) {
            if (i2 != 17) {
                iC0 = c0(i2, rect, rect2);
                if (i2 != 17) {
                    i3 = rect.left;
                    i4 = rect3.left;
                } else if (i2 != 33) {
                    i3 = rect.top;
                    i4 = rect3.top;
                } else if (i2 != 66) {
                    i3 = rect3.right;
                    i4 = rect.right;
                } else {
                    if (i2 == 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                    i3 = rect3.bottom;
                    i4 = rect.bottom;
                }
                if (iC0 < Math.max(1, i3 - i4)) {
                    return true;
                }
                return false;
            }
        }
        return true;
    }

    public static MappedByteBuffer e0(Context context, Uri uri) {
        try {
            ParcelFileDescriptor parcelFileDescriptorA = B.j.a(context.getContentResolver(), uri, "r", null);
            if (parcelFileDescriptorA == null) {
                if (parcelFileDescriptorA != null) {
                    parcelFileDescriptorA.close();
                }
                return null;
            }
            try {
                FileInputStream fileInputStream = new FileInputStream(parcelFileDescriptorA.getFileDescriptor());
                try {
                    FileChannel channel = fileInputStream.getChannel();
                    MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size());
                    fileInputStream.close();
                    parcelFileDescriptorA.close();
                    return map;
                } catch (Throwable th) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                try {
                    parcelFileDescriptorA.close();
                } catch (Throwable th4) {
                    th3.addSuppressed(th4);
                }
                throw th3;
            }
        } catch (IOException unused) {
            return null;
        }
    }

    public static boolean f(int i2, Rect rect, Rect rect2) {
        if (i2 != 17) {
            if (i2 != 33) {
                if (i2 != 66) {
                    if (i2 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                }
            }
            return rect2.right >= rect.left && rect2.left <= rect.right;
        }
        return rect2.bottom >= rect.top && rect2.top <= rect.bottom;
    }

    public static boolean g(Context context, Intent intent, ServiceConnection serviceConnection, int i2, Handler handler, UserHandle userHandle) {
        try {
            Object objInvoke = context.getClass().getMethod("bindServiceAsUser", Intent.class, ServiceConnection.class, Integer.TYPE, Handler.class, UserHandle.class).invoke(context, intent, serviceConnection, Integer.valueOf(i2), handler, userHandle);
            return objInvoke != null && ((Boolean) objInvoke).booleanValue();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            Log.e("Lyra-BindService", "bindServiceAsUser error", e2);
            return false;
        }
    }

    public static byte[] h(byte[] bArr, byte b2, byte b3, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[72];
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr4);
        byteBufferWrap.order(ByteOrder.BIG_ENDIAN);
        byteBufferWrap.put(K0.a.a);
        byteBufferWrap.put((byte) 1);
        byteBufferWrap.put(b2);
        byteBufferWrap.put(b3);
        byteBufferWrap.put((byte) 0);
        if (bArr2 == null || bArr2.length < 12) {
            byteBufferWrap.put(new byte[12]);
        } else {
            byteBufferWrap.put(bArr2, 0, 12);
        }
        if (bArr3 == null || bArr3.length < 16) {
            byteBufferWrap.put(new byte[16]);
        } else {
            byteBufferWrap.put(bArr3, 0, 16);
        }
        if (bArr.length >= 32) {
            byteBufferWrap.put(bArr, 0, 32);
        } else {
            byteBufferWrap.put(bArr);
            byteBufferWrap.put(new byte[32 - bArr.length]);
        }
        CRC32 crc32 = new CRC32();
        crc32.update(bArr4, 0, 68);
        int value = (int) crc32.getValue();
        bArr4[68] = (byte) ((value >> 24) & 255);
        bArr4[69] = (byte) ((value >> 16) & 255);
        bArr4[70] = (byte) ((value >> 8) & 255);
        bArr4[71] = (byte) (value & 255);
        return bArr4;
    }

    public static Object i(Object obj, Object... objArr) throws NoSuchMethodException {
        if (obj == null) {
            C("NetBusReflectUtil", "callObjectMethod: call method getIdentifier on a null target", null, new Object[0]);
            return null;
        }
        Method declaredMethod = obj.getClass().getDeclaredMethod("getIdentifier", null);
        declaredMethod.setAccessible(true);
        return declaredMethod.invoke(obj, null);
    }

    public static void j(String str, boolean z2) {
        if (!z2) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void k(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException();
        }
    }

    public static void l(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }

    public static byte[] l0(byte[] bArr, int i2) {
        if (bArr == null) {
            return new byte[i2];
        }
        if (bArr.length >= i2) {
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, 0, bArr2, 0, i2);
            return bArr2;
        }
        byte[] bArr3 = new byte[i2];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        return bArr3;
    }

    public static int m(int i2, int i3, int i4) {
        if (i2 < i3) {
            return i3;
        }
        return i2 > i4 ? i4 : i2;
    }

    public static String m0(String str) {
        for (int length = str.length(); length < 16; length++) {
            str = "0" + str;
        }
        Slog.d("toolClass", "protectorId is " + str);
        return str;
    }

    public static String n0(Uri uri) {
        String lastPathSegment = "";
        if ("content".equals(uri.getScheme())) {
            Cursor cursorQuery = null;
            try {
                cursorQuery = L().getContentResolver().query(uri, new String[]{"_display_name"}, null, null, null);
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    lastPathSegment = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_display_name"));
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                throw th;
            }
        }
        if (TextUtils.isEmpty(lastPathSegment)) {
            lastPathSegment = uri.getLastPathSegment();
        }
        U("UriUtils", "parseFileNameByUri:scheme = " + uri.getScheme() + " ,result = " + lastPathSegment, new Object[0]);
        return lastPathSegment;
    }

    public static long o0(Uri uri) {
        try {
            ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = L().getContentResolver().openFileDescriptor(uri, "r");
            if (parcelFileDescriptorOpenFileDescriptor == null) {
                if (parcelFileDescriptorOpenFileDescriptor == null) {
                    return -1L;
                }
                parcelFileDescriptorOpenFileDescriptor.close();
                return -1L;
            }
            try {
                long statSize = parcelFileDescriptorOpenFileDescriptor.getStatSize();
                parcelFileDescriptorOpenFileDescriptor.close();
                return statSize;
            } catch (Throwable th) {
                try {
                    parcelFileDescriptorOpenFileDescriptor.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException e2) {
            U("UriUtils", "parseFileSizeByUri size failed " + e2, new Object[0]);
            return -1L;
        }
    }

    public static byte[] p(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0048  */
    public static String p0(Uri uri) {
        String mimeTypeFromExtension;
        if ("content".equals(uri.getScheme())) {
            mimeTypeFromExtension = L().getContentResolver().getType(uri);
        } else {
            String lastPathSegment = uri.getLastPathSegment();
            if (TextUtils.isEmpty(lastPathSegment)) {
                mimeTypeFromExtension = "";
            } else {
                String[] strArrSplit = lastPathSegment.split("\\.");
                if (strArrSplit.length < 2 || TextUtils.isEmpty(strArrSplit[strArrSplit.length - 1])) {
                    mimeTypeFromExtension = "";
                } else {
                    mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(strArrSplit[strArrSplit.length - 1]);
                }
            }
        }
        U("UriUtils", "parseMimeTypeByUri:scheme = " + uri.getScheme() + " ,mimeType = " + mimeTypeFromExtension, new Object[0]);
        return !TextUtils.isEmpty(mimeTypeFromExtension) ? mimeTypeFromExtension : "";
    }

    public static int q(V v2, AbstractC0057y abstractC0057y, View view, View view2, K k2, boolean z2) {
        if (k2.v() == 0 || v2.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z2) {
            return Math.abs(K.D(view) - K.D(view2)) + 1;
        }
        return Math.min(abstractC0057y.l(), abstractC0057y.b(view2) - abstractC0057y.e(view));
    }

    public static void q0(int i2, String str, Throwable th, String str2, Object... objArr) {
        int i3;
        if (i2 < 4) {
            return;
        }
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        } else if (objArr.length != 0) {
            StringBuilder sb = new StringBuilder((objArr.length * 12) + str2.length());
            int length = str2.length();
            int i4 = 0;
            int i5 = 0;
            while (true) {
                if (i4 < length) {
                    int iIndexOf = str2.indexOf(37, i4);
                    if (iIndexOf < 0 || (i3 = iIndexOf + 1) >= length) {
                        sb.append((CharSequence) str2, i4, length);
                    } else {
                        sb.append((CharSequence) str2, i4, iIndexOf);
                        char cCharAt = str2.charAt(i3);
                        if ((cCharAt == 's' || cCharAt == 'd') && i5 < objArr.length) {
                            sb.append(objArr[i5]);
                            i5++;
                            i4 = iIndexOf + 2;
                        } else {
                            try {
                                str2 = String.format(str2, objArr);
                            } catch (IllegalFormatException unused) {
                            }
                        }
                    }
                }
                str2 = sb.toString();
            }
        }
        if (i2 == 2) {
            if (th == null) {
                Log.v(str, str2);
                return;
            } else {
                Log.v(str, str2, th);
                return;
            }
        }
        if (i2 == 3) {
            if (th == null) {
                Log.d(str, str2);
                return;
            } else {
                Log.d(str, str2, th);
                return;
            }
        }
        if (i2 == 4) {
            if (th == null) {
                Log.i(str, str2);
                return;
            } else {
                Log.i(str, str2, th);
                return;
            }
        }
        if (i2 == 5) {
            if (th == null) {
                Log.w(str, str2);
                return;
            } else {
                Log.w(str, str2, th);
                return;
            }
        }
        if (i2 != 6) {
            return;
        }
        if (th == null) {
            Log.e(str, str2);
        } else {
            Log.e(str, str2, th);
        }
    }

    public static int r(V v2, AbstractC0057y abstractC0057y, View view, View view2, K k2, boolean z2, boolean z3) {
        if (k2.v() == 0 || v2.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        int iMax = z3 ? Math.max(0, (v2.b() - Math.max(K.D(view), K.D(view2))) - 1) : Math.max(0, Math.min(K.D(view), K.D(view2)));
        if (z2) {
            return Math.round((iMax * (Math.abs(abstractC0057y.b(view2) - abstractC0057y.e(view)) / (Math.abs(K.D(view) - K.D(view2)) + 1))) + (abstractC0057y.k() - abstractC0057y.e(view)));
        }
        return iMax;
    }

    public static byte[] r0(byte[] bArr, byte b2, byte b3) {
        if (bArr.length < 72) {
            Log.w("SEProtector", "refillKeyType: invalid payload");
            return bArr;
        }
        byte[] bArr2 = new byte[72];
        System.arraycopy(bArr, 0, bArr2, 0, 72);
        bArr2[5] = b2;
        bArr2[6] = b3;
        CRC32 crc32 = new CRC32();
        crc32.update(bArr2, 0, 68);
        int value = (int) crc32.getValue();
        bArr2[68] = (byte) ((value >> 24) & 255);
        bArr2[69] = (byte) ((value >> 16) & 255);
        bArr2[70] = (byte) ((value >> 8) & 255);
        bArr2[71] = (byte) (value & 255);
        return bArr2;
    }

    public static int s(V v2, AbstractC0057y abstractC0057y, View view, View view2, K k2, boolean z2) {
        if (k2.v() == 0 || v2.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z2) {
            return v2.b();
        }
        return (int) (((abstractC0057y.b(view2) - abstractC0057y.e(view)) / (Math.abs(K.D(view) - K.D(view2)) + 1)) * v2.b());
    }

    public static void s0(TextInputLayout textInputLayout, CheckableImageButton checkableImageButton, ColorStateList colorStateList) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (checkableImageButton.getDrawable() == null || colorStateList == null || !colorStateList.isStateful()) {
            return;
        }
        int[] drawableState = textInputLayout.getDrawableState();
        int[] drawableState2 = checkableImageButton.getDrawableState();
        int length = drawableState.length;
        int[] iArrCopyOf = Arrays.copyOf(drawableState, drawableState.length + drawableState2.length);
        System.arraycopy(drawableState2, 0, iArrCopyOf, length, drawableState2.length);
        int colorForState = colorStateList.getColorForState(iArrCopyOf, colorStateList.getDefaultColor());
        Drawable drawableMutate = drawable.mutate();
        C.b.h(drawableMutate, ColorStateList.valueOf(colorForState));
        checkableImageButton.setImageDrawable(drawableMutate);
    }

    public static byte[] t(byte[] bArr) {
        if (bArr.length != 64) {
            throw new IllegalArgumentException("P1363签名必须是64字节");
        }
        byte[] bArr2 = new byte[32];
        byte[] bArr3 = new byte[32];
        System.arraycopy(bArr, 0, bArr2, 0, 32);
        System.arraycopy(bArr, 32, bArr3, 0, 32);
        byte[] bArrC0 = C0(bArr2);
        byte[] bArrC1 = C0(bArr3);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(48);
        byteArrayOutputStream.write(bArrC0.length + bArrC1.length);
        byteArrayOutputStream.write(bArrC0, 0, bArrC0.length);
        byteArrayOutputStream.write(bArrC1, 0, bArrC1.length);
        return byteArrayOutputStream.toByteArray();
    }

    public static p001a1.a t0(byte[] bArr) {
        p001a1.a aVar = new p001a1.a();
        if (bArr == null) {
            aVar.a = (byte) -128;
            return aVar;
        }
        aVar.a = bArr[0];
        aVar.b = Arrays.copyOfRange(bArr, 1, bArr.length);
        return aVar;
    }

    public static ImageView.ScaleType u(int i2) {
        if (i2 == 0) {
            return ImageView.ScaleType.FIT_XY;
        }
        if (i2 == 1) {
            return ImageView.ScaleType.FIT_START;
        }
        if (i2 == 2) {
            return ImageView.ScaleType.FIT_CENTER;
        }
        if (i2 == 3) {
            return ImageView.ScaleType.FIT_END;
        }
        if (i2 != 5) {
            return i2 != 6 ? ImageView.ScaleType.CENTER : ImageView.ScaleType.CENTER_INSIDE;
        }
        return ImageView.ScaleType.CENTER_CROP;
    }

    public static String v(String str) {
        if (str == null) {
            return null;
        }
        byte[] bytes = str.getBytes();
        char[] cArr = H0.f.a;
        if (bytes == null || bytes.length == 0) {
            return "";
        }
        char[] cArr2 = new char[bytes.length * 2];
        int i2 = 0;
        for (byte b2 : bytes) {
            int i3 = i2 + 1;
            char[] cArr3 = H0.f.a;
            cArr2[i2] = cArr3[(b2 & 255) >>> 4];
            i2 += 2;
            cArr2[i3] = cArr3[b2 & 15];
        }
        return new String(cArr2);
    }

    public static float[] w(float[] fArr, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException();
        }
        int length = fArr.length;
        if (length < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int iMin = Math.min(i2, length);
        float[] fArr2 = new float[i2];
        System.arraycopy(fArr, 0, fArr2, 0, iMin);
        return fArr2;
    }

    public static void w0(CheckableImageButton checkableImageButton, View.OnLongClickListener onLongClickListener) {
        WeakHashMap weakHashMap = J.G.a;
        boolean zA = J.r.a(checkableImageButton);
        boolean z2 = onLongClickListener != null;
        boolean z3 = zA || z2;
        checkableImageButton.setFocusable(z3);
        checkableImageButton.setClickable(zA);
        checkableImageButton.setPressable(zA);
        checkableImageButton.setLongClickable(z2);
        AbstractC0026s.s(checkableImageButton, z3 ? 1 : 2);
    }

    public static androidx.emoji2.text.n x(Context context) {
        ProviderInfo providerInfo;
        G.f fVar;
        ApplicationInfo applicationInfo;
        PackageManager packageManager = context.getPackageManager();
        l(packageManager, "Package manager required to locate emoji font provider");
        Iterator<ResolveInfo> it = packageManager.queryIntentContentProviders(new Intent("androidx.content.action.LOAD_EMOJI_FONT"), 0).iterator();
        while (true) {
            if (!it.hasNext()) {
                providerInfo = null;
                break;
            }
            providerInfo = it.next().providerInfo;
            if (providerInfo != null && (applicationInfo = providerInfo.applicationInfo) != null && (applicationInfo.flags & 1) == 1) {
                break;
            }
        }
        if (providerInfo == null) {
            fVar = null;
        } else {
            try {
                String str = providerInfo.authority;
                String str2 = providerInfo.packageName;
                Signature[] signatureArr = packageManager.getPackageInfo(str2, 64).signatures;
                ArrayList arrayList = new ArrayList();
                for (Signature signature : signatureArr) {
                    arrayList.add(signature.toByteArray());
                }
                fVar = new G.f(str, str2, "emojicompat-emoji-font", Collections.singletonList(arrayList));
            } catch (PackageManager.NameNotFoundException e2) {
                Log.wtf("emoji2.text.DefaultEmojiConfig", e2);
                fVar = null;
            }
        }
        if (fVar == null) {
            return null;
        }
        return new androidx.emoji2.text.n(new androidx.emoji2.text.m(context, fVar));
    }

    public static void x0(Drawable drawable, int i2) {
        C.b.g(drawable, i2);
    }

    public static boolean y(String str) {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            if (keyStore.containsAlias(str)) {
                keyStore.deleteEntry(str);
            }
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore");
            keyGenerator.init(new KeyGenParameterSpec.Builder(str, 3).setBlockModes("CBC").setEncryptionPaddings("PKCS7Padding").setUserAuthenticationRequired(true).setInvalidatedByBiometricEnrollment(true).build());
            keyGenerator.generateKey();
            Slog.d("MiTrustService/toolClass", "绑定生物识别的密钥创建成功");
            return true;
        } catch (Exception e2) {
            Slog.e("MiTrustService/toolClass", "创建密钥失败: " + e2.getMessage(), e2);
            return false;
        }
    }

    public static void y0(Drawable drawable, ColorStateList colorStateList) {
        C.b.h(drawable, colorStateList);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0030  */
    /* JADX WARN: Code duplicated, block: B:21:0x0046  */
    /* JADX WARN: Code duplicated, block: B:25:0x0056  */
    /* JADX WARN: Code duplicated, block: B:45:0x0096  */
    /* JADX WARN: Code duplicated, block: B:50:0x00a1 A[Catch: NumberFormatException -> 0x00af, TryCatch #0 {NumberFormatException -> 0x00af, blocks: (B:26:0x0059, B:29:0x006d, B:31:0x0073, B:35:0x007f, B:48:0x009b, B:50:0x00a1, B:56:0x00b6, B:57:0x00b9), top: B:71:0x0059 }] */
    /* JADX WARN: Code duplicated, block: B:54:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:56:0x00b6 A[Catch: NumberFormatException -> 0x00af, TryCatch #0 {NumberFormatException -> 0x00af, blocks: (B:26:0x0059, B:29:0x006d, B:31:0x0073, B:35:0x007f, B:48:0x009b, B:50:0x00a1, B:56:0x00b6, B:57:0x00b9), top: B:71:0x0059 }] */
    /* JADX WARN: Code duplicated, block: B:75:0x00e0 A[SYNTHETIC] */
    public static B.g[] z(String str) {
        String strTrim;
        int i2;
        float[] fArrW;
        if (str == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        int i4 = 0;
        int i5 = 1;
        while (i5 < str.length()) {
            while (i5 < str.length()) {
                char cCharAt = str.charAt(i5);
                if ((cCharAt - 'Z') * (cCharAt - 'A') > 0) {
                    if ((cCharAt - 'z') * (cCharAt - 'a') > 0) {
                        continue;
                    } else if (cCharAt != 'e' && cCharAt != 'E') {
                        strTrim = str.substring(i4, i5).trim();
                        if (strTrim.length() <= 0) {
                            if (strTrim.charAt(i3) != 'z' || strTrim.charAt(i3) == 'Z') {
                                i2 = i3;
                                fArrW = new float[i2];
                            } else {
                                try {
                                    float[] fArr = new float[strTrim.length()];
                                    int length = strTrim.length();
                                    int i6 = i3;
                                    int i7 = 1;
                                    while (i7 < length) {
                                        int i8 = i3;
                                        int i9 = i8;
                                        int i10 = i9;
                                        int i11 = i10;
                                        for (int i12 = i7; i12 < strTrim.length(); i12++) {
                                            char cCharAt2 = strTrim.charAt(i12);
                                            if (cCharAt2 == ' ') {
                                                i8 = 0;
                                                i10 = 1;
                                            } else if (cCharAt2 != 'E' && cCharAt2 != 'e') {
                                                switch (cCharAt2) {
                                                    case ',':
                                                        i8 = 0;
                                                        i10 = 1;
                                                        break;
                                                    case '-':
                                                        if (i12 == i7 || i8 != 0) {
                                                            i8 = 0;
                                                        } else {
                                                            i8 = 0;
                                                            i10 = 1;
                                                            i11 = 1;
                                                        }
                                                        break;
                                                    case '.':
                                                        if (i9 == 0) {
                                                            i8 = 0;
                                                            i9 = 1;
                                                        } else {
                                                            i8 = 0;
                                                            i10 = 1;
                                                            i11 = 1;
                                                        }
                                                        break;
                                                    default:
                                                        i8 = 0;
                                                        break;
                                                }
                                            } else {
                                                i8 = 1;
                                            }
                                            if (i10 != 0) {
                                                if (i7 < i12) {
                                                    fArr[i6] = Float.parseFloat(strTrim.substring(i7, i12));
                                                    i6++;
                                                }
                                                if (i11 != 0) {
                                                    i7 = i12;
                                                } else {
                                                    i7 = i12 + 1;
                                                }
                                                i3 = 0;
                                            }
                                        }
                                        if (i7 < i12) {
                                            fArr[i6] = Float.parseFloat(strTrim.substring(i7, i12));
                                            i6++;
                                        }
                                        if (i11 != 0) {
                                            i7 = i12;
                                        } else {
                                            i7 = i12 + 1;
                                        }
                                        i3 = 0;
                                    }
                                    fArrW = w(fArr, i6);
                                    i2 = 0;
                                } catch (NumberFormatException e2) {
                                    throw new RuntimeException(B.b.f("error in parsing \"", strTrim, "\""), e2);
                                }
                            }
                            char cCharAt3 = strTrim.charAt(i2);
                            B.g gVar = new B.g();
                            gVar.a = cCharAt3;
                            gVar.b = fArrW;
                            arrayList.add(gVar);
                        }
                        i4 = i5;
                        i3 = 0;
                        i5++;
                    }
                } else if (cCharAt != 'e') {
                    continue;
                }
                i5++;
            }
            strTrim = str.substring(i4, i5).trim();
            if (strTrim.length() <= 0) {
                if (strTrim.charAt(i3) != 'z') {
                    i2 = i3;
                    fArrW = new float[i2];
                } else {
                    i2 = i3;
                    fArrW = new float[i2];
                }
                char cCharAt4 = strTrim.charAt(i2);
                B.g gVar2 = new B.g();
                gVar2.a = cCharAt4;
                gVar2.b = fArrW;
                arrayList.add(gVar2);
            }
            i4 = i5;
            i3 = 0;
            i5++;
        }
        if (i5 - i4 == 1 && i4 < str.length()) {
            char cCharAt5 = str.charAt(i4);
            B.g gVar3 = new B.g();
            gVar3.a = cCharAt5;
            gVar3.b = new float[0];
            arrayList.add(gVar3);
        }
        return (B.g[]) arrayList.toArray(new B.g[arrayList.size()]);
    }

    public static void z0(Drawable drawable, PorterDuff.Mode mode) {
        C.b.i(drawable, mode);
    }

    public abstract boolean D0(View view, int i2);

    public abstract InputFilter[] N(InputFilter[] inputFilterArr);

    public int R(View view) {
        return 0;
    }

    public int S() {
        return 0;
    }

    @Override // J.N
    public void b(View view) {
    }

    @Override // J.N
    public void c() {
    }

    public abstract void f0(Throwable th);

    public abstract void g0(com.xiaomi.continuity.d dVar);

    public void h0(View view, int i2) {
    }

    public abstract void i0(int i2);

    public abstract void j0(View view, int i2, int i3);

    public abstract void k0(View view, float f2, float f3);

    public abstract int n(View view, int i2);

    public abstract int o(View view, int i2);

    public abstract void u0(boolean z2);

    public abstract void v0(boolean z2);
}
