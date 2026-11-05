package defpackage;

import android.content.Context;
import com.xiaomi.onetrack.Configuration;
import com.xiaomi.onetrack.OneTrack;

/* JADX INFO: loaded from: classes.dex */
public final class wu2 implements yu2 {
    public final OneTrack a;

    public wu2(Context context) {
        OneTrack oneTrackCreateInstance = OneTrack.createInstance(context, new Configuration.Builder().setAppId("31000401489").setChannel(context.getPackageName()).setMode(OneTrack.Mode.APP).setAutoTrackActivityAction(true).build());
        oneTrackCreateInstance.getClass();
        this.a = oneTrackCreateInstance;
    }
}
