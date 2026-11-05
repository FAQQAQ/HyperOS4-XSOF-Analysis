package defpackage;

import android.media.AudioFormat;
import android.media.AudioRecord;

/* JADX INFO: loaded from: classes.dex */
public interface uj {
    public static final AudioFormat a = new AudioFormat.Builder().setSampleRate(16000).setEncoding(2).setChannelMask(16).build();
    public static final int b = AudioRecord.getMinBufferSize(16000, 16, 2);

    void a(pi piVar);

    void stop();
}
