package defpackage;

import android.media.AudioRecord;
import android.util.Log;
import java.lang.invoke.VarHandle;

/* JADX INFO: loaded from: classes.dex */
public final class k63 implements uj {
    public volatile AudioRecord c;
    public volatile AudioRecord d;

    public static void b(AudioRecord audioRecord, String str) {
        if (audioRecord == null) {
            return;
        }
        try {
            audioRecord.stop();
        } catch (Exception e) {
            Log.w("AudioCapture", "Stop " + str + " AudioRecord failed: ", e);
        }
        try {
            audioRecord.release();
        } catch (Exception e2) {
            Log.w("AudioCapture", "Release " + str + " AudioRecord failed: ", e2);
        }
    }

    @Override // defpackage.uj
    public final void a(pi piVar) {
        int i = uj.b * 25;
        this.c = new AudioRecord.Builder().setAudioSource(7).setAudioFormat(uj.a).setBufferSizeInBytes(i).build();
        try {
            this.d = es.b();
            sz szVar = new sz(4);
            szVar.d = this;
            szVar.c = i;
            szVar.e = piVar;
            VarHandle.storeStoreFence();
            new Thread(szVar).start();
        } catch (Exception e) {
            Log.e("AudioCapture", "Create voip downlink AudioRecord failed: ", e);
            stop();
        }
    }

    @Override // defpackage.uj
    public final void stop() {
        boolean z;
        synchronized (this) {
            AudioRecord audioRecord = this.c;
            AudioRecord audioRecord2 = this.d;
            z = (audioRecord == null && audioRecord2 == null) ? false : true;
            this.c = null;
            this.d = null;
            b(audioRecord, "uplink");
            b(audioRecord2, "downlink");
        }
        if (z) {
            Log.i("AudioCapture", "Recording stopped.");
        }
    }
}
