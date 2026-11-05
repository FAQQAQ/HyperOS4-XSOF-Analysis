package com.xiaomi.registration.autoregistration;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;
import androidx.activity.result.d;
import j1.c;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class SmsFilterReceiver extends BroadcastReceiver {
    public static final Object a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static byte[] f848b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static long f849c;

    /* JADX WARN: Code duplicated, block: B:74:0x019b  */
    /* JADX WARN: Code duplicated, block: B:76:0x01a7  */
    /* JADX WARN: Code duplicated, block: B:79:0x01af  */
    /* JADX WARN: Code duplicated, block: B:81:0x01bb  */
    /* JADX WARN: Code duplicated, block: B:82:0x01be  */
    /* JADX WARN: Code duplicated, block: B:95:? A[RETURN, SYNTHETIC] */
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        boolean z2;
        boolean zAreSamePhoneNumber;
        boolean z3;
        int i3;
        String str;
        String str2;
        if (intent == null || intent.getAction() == null || !intent.getAction().equals("com.xiaomi.autoreg.receivesms")) {
            return;
        }
        byte[] byteArrayExtra = intent.getByteArrayExtra("pdu");
        if (byteArrayExtra == null) {
            z2 = false;
        } else {
            synchronized (a) {
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                byte[] bArr = f848b;
                z2 = bArr != null && jElapsedRealtime - f849c < 3000 && Arrays.equals(byteArrayExtra, bArr);
                if (!z2) {
                    f848b = byteArrayExtra;
                    f849c = jElapsedRealtime;
                }
            }
        }
        if (z2) {
            str = "AutoReg_SmsFilterService";
            str2 = "duplicate autoRegSms broadcast within 3000ms, ignore";
        } else {
            Log.d("AutoReg_SmsFilterService", "on receive autoRegSms");
            d dVar = new d(0);
            StringBuilder sb = new StringBuilder();
            CharsetDecoder charsetDecoderNewDecoder = StandardCharsets.UTF_8.newDecoder();
            if (byteArrayExtra != null) {
                SmsMessage smsMessageCreateFromPdu = SmsMessage.createFromPdu(byteArrayExtra, "3gpp");
                String str3 = null;
                if (smsMessageCreateFromPdu == null) {
                    dVar = null;
                } else {
                    if (((SmsMessage) dVar.f70e) == null) {
                        dVar.f70e = smsMessageCreateFromPdu;
                    }
                    Log.d("AutoReg_SmsFilterService", "getUserData is " + ((int) smsMessageCreateFromPdu.getUserData()[1]));
                    if (smsMessageCreateFromPdu.getUserData() != null) {
                        ByteBuffer byteBufferWrap = ByteBuffer.wrap(smsMessageCreateFromPdu.getUserData());
                        Log.d("AutoReg_SmsFilterService", "The byteBuffer is " + byteBufferWrap);
                        try {
                            String string = charsetDecoderNewDecoder.decode(byteBufferWrap).toString();
                            Log.d("AutoReg_SmsFilterService", "The byteBuffer body is " + string);
                            str3 = string;
                        } catch (CharacterCodingException unused) {
                            dVar = null;
                        }
                    }
                    if (str3 != null) {
                        sb.append(str3);
                    }
                    dVar.f71f = sb.toString();
                    Log.d("AutoReg_SmsFilterService", "The byteBuffer fullMsg.fullMessageBody is " + ((String) dVar.f71f));
                }
            }
            if (dVar == null) {
                return;
            }
            SmsMessage smsMessage = (SmsMessage) dVar.f70e;
            String str4 = (String) dVar.f71f;
            if (smsMessage == null) {
                Log.e("AutoReg_SmsFilterService", "Unable to create SmsMessage from PDU, cannot determine originating number");
            } else {
                String originatingAddress = smsMessage.getOriginatingAddress();
                String networkCountryIso = ((TelephonyManager) context.getSystemService("phone")).getNetworkCountryIso();
                if ("".equals(networkCountryIso)) {
                    zAreSamePhoneNumber = "10659401".equals(originatingAddress) || "+8610659401".equals(originatingAddress);
                } else {
                    zAreSamePhoneNumber = PhoneNumberUtils.areSamePhoneNumber("10659401", originatingAddress, networkCountryIso);
                }
                Log.i("AutoReg_SmsFilterService", "networkCountryIso is " + networkCountryIso + ", orgAddress = " + originatingAddress + ", areSamePhoneNumber = " + zAreSamePhoneNumber);
                if (zAreSamePhoneNumber && (!c.h(context) ? !(str4.getBytes()[0] != 3 || str4.getBytes()[1] != 4) : !(str4.getBytes()[0] != 9 || str4.getBytes()[1] != 4))) {
                    z3 = true;
                }
                Log.d("AutoReg_SmsFilterService", "The compare result is " + z3);
                if (z3) {
                    return;
                }
                if (Settings.Global.getInt(context.getContentResolver(), "device_provisioned", 0) != 1) {
                    int intExtra = intent.getIntExtra("phoneId", 0);
                    if (c.h(context)) {
                        i3 = 32;
                    } else {
                        i3 = 31;
                    }
                    Intent intent2 = new Intent();
                    intent2.putExtra("result", true);
                    intent2.putExtra("phone", intExtra);
                    AutoRegIntentService.c(context, i3, intExtra, intent2);
                    return;
                }
                str = "AutoReg_SmsFilterService";
                str2 = "device not provisioned, skip result dispatch";
            }
            z3 = false;
            Log.d("AutoReg_SmsFilterService", "The compare result is " + z3);
            if (z3) {
                return;
            }
            if (Settings.Global.getInt(context.getContentResolver(), "device_provisioned", 0) != 1) {
                int intExtra2 = intent.getIntExtra("phoneId", 0);
                if (c.h(context)) {
                    i3 = 32;
                } else {
                    i3 = 31;
                }
                Intent intent3 = new Intent();
                intent3.putExtra("result", true);
                intent3.putExtra("phone", intExtra2);
                AutoRegIntentService.c(context, i3, intExtra2, intent3);
                return;
            }
            str = "AutoReg_SmsFilterService";
            str2 = "device not provisioned, skip result dispatch";
        }
        Log.d(str, str2);
    }
}
