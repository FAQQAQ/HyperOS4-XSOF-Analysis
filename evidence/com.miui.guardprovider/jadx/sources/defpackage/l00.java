package defpackage;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.miui.securitycenter.confidentialcomputing.EncryptedData;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public final class l00 implements AutoCloseable {
    public /* synthetic */ String b;

    public static byte[] a(String str, byte[] bArr) {
        vp1 vp1Var = new vp1();
        vp1Var.b = true;
        File fileCreateTempFile = File.createTempFile("pipe", null);
        Files.write(fileCreateTempFile.toPath(), bArr, StandardOpenOption.DELETE_ON_CLOSE, StandardOpenOption.WRITE);
        vp1Var.d = ParcelFileDescriptor.open(fileCreateTempFile, 268435456);
        q23 q23VarN = wp1.n(str, vp1Var);
        if (((ParcelFileDescriptor) q23VarN.b) == null) {
            ik.u("Returned value is null.");
            return null;
        }
        ParcelFileDescriptor.AutoCloseInputStream autoCloseInputStream = new ParcelFileDescriptor.AutoCloseInputStream((ParcelFileDescriptor) q23VarN.b);
        try {
            byte[] allBytes = autoCloseInputStream.readAllBytes();
            autoCloseInputStream.close();
            return allBytes;
        } catch (Throwable th) {
            try {
                autoCloseInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static EncryptedData g(String str, byte[] bArr) throws IOException {
        vp1 vp1Var = new vp1();
        vp1Var.b = true;
        File fileCreateTempFile = File.createTempFile("pipe", null);
        Files.write(fileCreateTempFile.toPath(), bArr, StandardOpenOption.DELETE_ON_CLOSE, StandardOpenOption.WRITE);
        vp1Var.d = ParcelFileDescriptor.open(fileCreateTempFile, 268435456);
        q23 q23VarO = wp1.o(str, vp1Var);
        if (((ParcelFileDescriptor) q23VarO.b) == null) {
            ik.u("Returned value is null.");
            return null;
        }
        try {
            ParcelFileDescriptor.AutoCloseInputStream autoCloseInputStream = new ParcelFileDescriptor.AutoCloseInputStream((ParcelFileDescriptor) q23VarO.b);
            try {
                EncryptedData encryptedDataCreateInstanceFrom = EncryptedData.createInstanceFrom(autoCloseInputStream.readAllBytes());
                autoCloseInputStream.close();
                return encryptedDataCreateInstanceFrom;
            } catch (Throwable th) {
                try {
                    autoCloseInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (JSONException e) {
            throw new IOException(e);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: byte[] */
    /* JADX WARN: Multi-variable type inference failed */
    public final EncryptedData b(byte[] bArr) {
        Objects.requireNonNull(bArr);
        try {
            int length = bArr.length;
            String str = this.b;
            if (length >= 1048576) {
                return g(str, bArr);
            }
            vp1 vp1Var = new vp1();
            vp1Var.b = false;
            vp1Var.c = bArr;
            byte[] bArr2 = (byte[]) wp1.o(str, vp1Var).a;
            if (bArr2 == null) {
                throw new IOException("Returned value is null.");
            }
            try {
                return EncryptedData.createInstanceFrom(bArr2);
            } catch (JSONException e) {
                throw new IOException(e);
            }
        } catch (Exception e2) {
            throw new k00(e2);
        }
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        int i;
        String str = this.b;
        ConcurrentHashMap concurrentHashMap = ub3.a;
        ob3 ob3Var = (ob3) concurrentHashMap.get(str);
        if (ob3Var != null) {
            synchronized (ob3Var.b) {
                ob3Var.a();
                concurrentHashMap.remove(str);
                ub3.d(ob3Var.a);
                Log.d("MipccService", "Session closed and cleaned: " + str);
            }
            i = 0;
        } else {
            i = -1;
        }
        if (i != 0) {
            Log.w("CCL", "Fail to pcc close,error code:" + i);
        }
    }
}
