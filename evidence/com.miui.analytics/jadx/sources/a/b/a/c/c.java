package a.b.a.c;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import com.miui.analytics.internal.ApkReceiver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class c {
    public static int b(Context context, String str, File file) throws Exception {
        a.b.a.c.v.i.b("AppInstaller", "install pkg: " + str);
        PackageInstaller packageInstaller = context.getPackageManager().getPackageInstaller();
        PackageInstaller.Session sessionOpenSession = packageInstaller.openSession(packageInstaller.createSession(new PackageInstaller.SessionParams(1)));
        try {
            return c(context, file, sessionOpenSession);
        } finally {
            sessionOpenSession.close();
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:44:0x00ec */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 12, insn: 0x0043: MOVE (r10 I:??[OBJECT, ARRAY]) = (r12 I:??[OBJECT, ARRAY]), block:B:15:0x0042 */
    /* JADX WARN: Type inference failed for: r15v0, types: [android.content.pm.PackageInstaller$Session] */
    /* JADX WARN: Type inference failed for: r15v1, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r15v2 */
    /* JADX WARN: Type inference failed for: r15v5 */
    public static int c(Context context, File file, PackageInstaller.Session session) throws Exception {
        FileInputStream fileInputStream;
        OutputStream outputStream;
        OutputStream outputStream2;
        FileInputStream fileInputStream2;
        long length = file.length();
        if (length == 0) {
            a.b.a.c.v.i.c("AppInstaller", "apk size is 0");
            return -1;
        }
        FileInputStream fileInputStream3 = null;
        outputStreamOpenWrite = null;
        outputStreamOpenWrite = null;
        outputStreamOpenWrite = null;
        FileInputStream fileInputStream4 = null;
        FileInputStream fileInputStream5 = null;
        OutputStream outputStreamOpenWrite = null;
        fileInputStream3 = null;
        try {
            try {
                try {
                    fileInputStream = new FileInputStream(file);
                    try {
                        outputStreamOpenWrite = session.openWrite(file.getName(), 0L, length);
                        byte[] bArr = new byte[524288];
                        while (true) {
                            int i = fileInputStream.read(bArr);
                            if (i == -1) {
                                break;
                            }
                            outputStreamOpenWrite.write(bArr, 0, i);
                        }
                        session.fsync(outputStreamOpenWrite);
                    } catch (FileNotFoundException e) {
                        e = e;
                        outputStream2 = outputStreamOpenWrite;
                        fileInputStream4 = fileInputStream;
                        a.b.a.c.v.i.c("AppInstaller", "Apk copy: installation failed due to " + e + ". ");
                        a.b.a.a.a.c.a.O0(context, "AppInstaller", "installApkInternal file not found ", e);
                        fileInputStream4.close();
                        outputStream2.close();
                        return -1;
                    } catch (IOException e2) {
                        e = e2;
                        outputStream = outputStreamOpenWrite;
                        fileInputStream5 = fileInputStream;
                        a.b.a.c.v.i.c("AppInstaller", "Apk copy: installation failed due to " + e + ". ");
                        StringBuilder sb = new StringBuilder();
                        sb.append("installApkInternal IOEx ");
                        sb.append(e.toString());
                        a.b.a.a.a.c.a.O0(context, "AppInstaller", sb.toString(), e);
                        fileInputStream5.close();
                        outputStream.close();
                        return -1;
                    } catch (Exception e3) {
                        e = e3;
                        a.b.a.a.a.c.a.O0(context, "AppInstaller", "installApkInternal other ex " + e.toString(), e);
                    }
                } catch (Throwable th) {
                    th = th;
                    session = 0;
                    fileInputStream3 = fileInputStream2;
                    fileInputStream3.close();
                    session.close();
                    throw th;
                }
            } catch (FileNotFoundException e4) {
                e = e4;
                outputStream2 = null;
            } catch (IOException e5) {
                e = e5;
                outputStream = null;
            } catch (Exception e6) {
                e = e6;
                fileInputStream = null;
            } catch (Throwable th2) {
                th = th2;
                session = 0;
                fileInputStream3.close();
                session.close();
                throw th;
            }
            fileInputStream.close();
            outputStreamOpenWrite.close();
            session.commit(PendingIntent.getBroadcast(context, 0, new Intent(context, (Class<?>) ApkReceiver.class), Build.VERSION.SDK_INT >= 31 ? 167772160 : 134217728).getIntentSender());
            a.b.a.c.v.i.b("AppInstaller", "will exit installApkInternal");
            return 0;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public int a(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (Build.VERSION.SDK_INT >= 28) {
                a.b.a.c.v.i.b("AppInstaller", "SDK version >= Android P");
                return b(context, context.getPackageName(), new File(str));
            }
            a.b.a.c.v.i.b("AppInstaller", "SDK version < Android P");
            packageManager.getClass().getMethod("installPackage", Uri.class, Class.forName("android.content.pm.IPackageInstallObserver"), Integer.TYPE, String.class).invoke(packageManager, Uri.fromFile(new File(str)), null, packageManager.getClass().getField("INSTALL_REPLACE_EXISTING").get(null), null);
            return 0;
        } catch (Throwable th) {
            a.b.a.c.v.i.d("AppInstaller", "exception", th);
            a.b.a.a.a.c.a.O0(context, "AppInstaller", "install apk: path=" + str, th);
            return -1;
        }
    }
}
