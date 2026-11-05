package i1;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.util.Log;
import miui.os.Build;
import miui.telephony.PhoneDebug;

/* JADX INFO: loaded from: classes.dex */
public final class m {
    public final Context a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final SmsManager f1726b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f1727c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final l f1728d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final l f1729e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1730f;

    public m(Context context, int i3, int i4, boolean z2) {
        this.a = context;
        this.f1726b = ((SmsManager) context.getSystemService(SmsManager.class)).createForSubscriptionId(i4);
        this.f1730f = i3;
        this.f1727c = z2;
        l lVar = new l(this, (Object) null);
        this.f1728d = lVar;
        context.registerReceiver(lVar, new IntentFilter("AUTO_REG_SMS_SEND"), 2);
        l lVar2 = new l(this);
        this.f1729e = lVar2;
        context.registerReceiver(lVar2, new IntentFilter("AUTO_REG_SMS_DELIVERED"), 2);
    }

    public final void a(int i3, final String str) {
        this.f1730f = i3;
        Intent intent = new Intent("AUTO_REG_SMS_SEND");
        Context context = this.a;
        final PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, 67108864);
        final PendingIntent broadcast2 = PendingIntent.getBroadcast(context, 0, new Intent("AUTO_REG_SMS_DELIVERED"), 67108864);
        new Thread(new Runnable() { // from class: i1.k

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ String f1721b = "10659401";

            @Override // java.lang.Runnable
            public final void run() {
                String str2 = this.f1721b;
                String str3 = str;
                PendingIntent pendingIntent = broadcast;
                PendingIntent pendingIntent2 = broadcast2;
                m mVar = this.a;
                mVar.getClass();
                try {
                    Log.d("AutoReg_SmsSender", "start sent message, thread creation");
                    mVar.f1726b.sendDataMessage(str2, null, (short) 0, str3.getBytes(), pendingIntent, pendingIntent2);
                } catch (Exception e3) {
                    Log.e("AutoReg_SmsSender", "Send SMS Error", e3);
                }
            }
        }).start();
        if (Build.IS_CT_CUSTOMIZATION_TEST || PhoneDebug.VDBG) {
            Log.d("AutoReg_SmsSender", "start sent message".concat(str));
        }
    }
}
