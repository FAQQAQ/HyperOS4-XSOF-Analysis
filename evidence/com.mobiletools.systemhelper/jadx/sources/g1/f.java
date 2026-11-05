package g1;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private HttpURLConnection f2675a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private HttpsURLConnection f2676b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f2677c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f2678d = false;

    private static class b implements HostnameVerifier {
        private b() {
        }

        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return HttpsURLConnection.getDefaultHostnameVerifier().verify(str, sSLSession);
        }
    }

    private static class c implements X509TrustManager {
        private c() {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            if (x509CertificateArr == null || x509CertificateArr.length == 0) {
                throw new CertificateException("服务端证书链为空");
            }
            for (X509Certificate x509Certificate : x509CertificateArr) {
                x509Certificate.checkValidity();
            }
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private void a(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Exception e2) {
                h.b("HttpHelper", "close stream error：" + e2.getMessage());
            }
        }
    }

    private void b(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    private SSLSocketFactory d() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sSLContext = SSLContext.getInstance("TLSv1.2");
        sSLContext.init(null, new TrustManager[]{new c()}, new SecureRandom());
        return sSLContext.getSocketFactory();
    }

    public synchronized int c() {
        return this.f2677c;
    }

    public synchronized boolean e() {
        return this.f2678d;
    }

    public synchronized boolean f(String str, byte[] bArr) {
        boolean z2;
        h.a("HttpHelper", "sendHttpFallbackRequest() httpPath = " + d.f2657m);
        h.a("HttpHelper", "sendHttpRequest() path = " + str);
        OutputStream outputStream = null;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            this.f2675a = httpURLConnection;
            httpURLConnection.setConnectTimeout(10000);
            this.f2675a.setReadTimeout(15000);
            this.f2675a.setRequestMethod("POST");
            z2 = true;
            this.f2675a.setDoOutput(true);
            this.f2675a.setDoInput(true);
            this.f2675a.setRequestProperty("Content-Type", "application/encrypted-json");
            this.f2675a.setRequestProperty("Content-Length", String.valueOf(bArr.length));
            outputStream = this.f2675a.getOutputStream();
            outputStream.write(bArr);
            outputStream.flush();
            outputStream.close();
            this.f2677c = this.f2675a.getResponseCode();
            h.a("HttpHelper", "HTTP fallback responseCode-->" + this.f2677c);
            if (this.f2677c != 200) {
                z2 = false;
            }
            h.a("HttpHelper", z2 ? "HTTP fallback success!" : "HTTP fallback fail");
            a(outputStream);
            b(this.f2675a);
        } catch (Throwable th) {
            a(outputStream);
            b(this.f2675a);
            throw th;
        }
        return z2;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:48:0x002a */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [g1.f$a] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r6v0, types: [g1.f] */
    public synchronized boolean g(String str, byte[] bArr, byte[] bArr2) {
        boolean z2;
        z2 = false;
        this.f2678d = false;
        h.a("HttpHelper", "sendHttpRequest() path = " + str);
        ?? outputStream = 0;
        outputStream = null;
        OutputStream outputStream2 = null;
        if (str.startsWith("http://")) {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                this.f2675a = httpURLConnection;
                httpURLConnection.setConnectTimeout(10000);
                this.f2675a.setReadTimeout(15000);
                this.f2675a.setRequestMethod("POST");
                this.f2675a.setDoOutput(true);
                this.f2675a.setDoInput(true);
                this.f2675a.setRequestProperty("Content-Type", "application/encrypted-json");
                this.f2675a.setRequestProperty("Content-Length", String.valueOf(bArr.length));
                outputStream = this.f2675a.getOutputStream();
                outputStream.write(bArr);
                outputStream.flush();
                outputStream.close();
                this.f2677c = this.f2675a.getResponseCode();
                h.a("HttpHelper", "HTTP server responseCode-->" + this.f2677c);
                z2 = this.f2677c == 200;
                a(outputStream);
                b(this.f2675a);
            } catch (Throwable th) {
                a(outputStream);
                b(this.f2675a);
                throw th;
            }
        } else {
            try {
                if (str.startsWith("https://")) {
                    try {
                        try {
                            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection();
                            this.f2676b = httpsURLConnection;
                            httpsURLConnection.setSSLSocketFactory(d());
                            this.f2676b.setHostnameVerifier(new b());
                            this.f2676b.setConnectTimeout(10000);
                            this.f2676b.setReadTimeout(15000);
                            this.f2676b.setRequestMethod("POST");
                            this.f2676b.setDoOutput(true);
                            this.f2676b.setDoInput(true);
                            this.f2676b.setRequestProperty("Content-Type", "application/encrypted-json");
                            this.f2676b.setRequestProperty("Content-Length", String.valueOf(bArr.length));
                            outputStream2 = this.f2676b.getOutputStream();
                            outputStream2.write(bArr);
                            outputStream2.flush();
                            outputStream2.close();
                            this.f2677c = this.f2676b.getResponseCode();
                            h.a("HttpHelper", "HTTPS server responseCode-->" + this.f2677c);
                            boolean z3 = this.f2677c == 200;
                            h.a("HttpHelper", z3 ? "HTTPS register success!" : "HTTPS register fail (server reject)");
                            if (this.f2676b != null) {
                                a(outputStream2);
                                b(this.f2676b);
                            }
                            z2 = z3;
                        } catch (SSLException e2) {
                            this.f2678d = true;
                            h.b("HttpHelper", "HTTPS register fail (cert invalid)：" + e2.getClass().getSimpleName() + " - " + e2.getMessage());
                            throw e2;
                        }
                    } catch (Exception e3) {
                        this.f2678d = false;
                        h.b("HttpHelper", "HTTPS register fail (network/other error)：" + e3.getMessage());
                        throw e3;
                    }
                }
            } catch (Throwable th2) {
                if (this.f2676b != null) {
                    a(outputStream2);
                    b(this.f2676b);
                }
                throw th2;
            }
        }
        return z2;
    }
}
