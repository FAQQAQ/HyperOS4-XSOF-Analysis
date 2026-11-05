package com.miui.guardprovider.aiguard;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Icon;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.miui.guardprovider.R;
import com.xiaomi.ai.vision.sdk.AiCapability;
import defpackage.a7;
import defpackage.az0;
import defpackage.b7;
import defpackage.c13;
import defpackage.c7;
import defpackage.cb3;
import defpackage.d61;
import defpackage.es;
import defpackage.f20;
import defpackage.g40;
import defpackage.g7;
import defpackage.gs0;
import defpackage.h30;
import defpackage.hn;
import defpackage.hx;
import defpackage.i6;
import defpackage.i7;
import defpackage.ik;
import defpackage.j02;
import defpackage.j20;
import defpackage.j7;
import defpackage.ja2;
import defpackage.k00;
import defpackage.k20;
import defpackage.k43;
import defpackage.k63;
import defpackage.k7;
import defpackage.kk3;
import defpackage.kn2;
import defpackage.l00;
import defpackage.l7;
import defpackage.mr2;
import defpackage.mu;
import defpackage.n7;
import defpackage.o60;
import defpackage.o7;
import defpackage.p7;
import defpackage.pj0;
import defpackage.q7;
import defpackage.qi;
import defpackage.qq;
import defpackage.qq2;
import defpackage.qy1;
import defpackage.r7;
import defpackage.ri3;
import defpackage.rk1;
import defpackage.ro;
import defpackage.si;
import defpackage.t7;
import defpackage.u83;
import defpackage.uj;
import defpackage.uk3;
import defpackage.v7;
import defpackage.w10;
import defpackage.w60;
import defpackage.x7;
import defpackage.xc0;
import defpackage.y6;
import defpackage.y7;
import defpackage.yp2;
import defpackage.z6;
import java.io.IOException;
import java.lang.invoke.VarHandle;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.UUID;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class AiGuardService extends Service {
    public static final /* synthetic */ int o = 0;
    public final f20 b;
    public final f20 c;
    public final mr2 d;
    public si e;
    public Integer f;
    public kn2 g;
    public boolean h;
    public final mr2 i;
    public final mr2 j;
    public final mr2 k;
    public ro l;
    public final g7 m;
    public int n;

    public AiGuardService() {
        w60 w60Var = xc0.a;
        qq2 qq2VarC = cb3.c();
        w60Var.getClass();
        this.b = az0.a(w10.B(w60Var, qq2VarC));
        this.c = az0.a(w10.B(o60.d, cb3.c()));
        int i = 0;
        this.d = new mr2(new y6(i));
        z6 z6Var = new z6(i);
        z6Var.c = this;
        VarHandle.storeStoreFence();
        this.i = new mr2(z6Var);
        int i2 = 1;
        i6 i6Var = new i6(i2);
        i6Var.c = this;
        VarHandle.storeStoreFence();
        this.j = new mr2(i6Var);
        z6 z6Var2 = new z6(i2);
        z6Var2.c = this;
        VarHandle.storeStoreFence();
        this.k = new mr2(z6Var2);
        this.m = new g7();
    }

    public static final void a(AiGuardService aiGuardService, int i) {
        aiGuardService.getClass();
        aiGuardService.startActivity(new Intent(aiGuardService.getApplicationContext(), (Class<?>) AlertActivity.class).setFlags(268435456).putExtra("alert_cause", i));
        aiGuardService.h = true;
    }

    public static final int b(AiGuardService aiGuardService, int i) {
        g7 g7Var = aiGuardService.m;
        if (i == 1) {
            g7Var.a++;
            return 268435457;
        }
        if (i == 2) {
            g7Var.c++;
            return 268435458;
        }
        Log.w("AiGuardService", "Video capture scene missing:" + i);
        return 268435456;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public static final Object c(AiGuardService aiGuardService, int i, k20 k20Var) {
        v7 v7Var;
        if (k20Var instanceof v7) {
            v7Var = (v7) k20Var;
            int i2 = v7Var.g;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                v7Var.g = i2 - Integer.MIN_VALUE;
            } else {
                v7Var = new v7(aiGuardService, k20Var);
            }
        } else {
            v7Var = new v7(aiGuardService, k20Var);
        }
        Object obj = v7Var.e;
        h30 h30Var = h30.b;
        int i3 = v7Var.g;
        if (i3 == 0) {
            kk3.y(obj);
            w60 w60Var = xc0.a;
            gs0 gs0Var = rk1.a;
            x7 x7Var = new x7(i, null, aiGuardService);
            v7Var.g = 1;
            if (uk3.E(gs0Var, x7Var, v7Var) == h30Var) {
                return h30Var;
            }
        } else {
            if (i3 != 1) {
                ik.j("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            kk3.y(obj);
        }
        return c13.a;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    public final Object d(String str, k20 k20Var) throws k00 {
        i7 i7Var;
        l00 l00Var;
        Throwable th;
        if (k20Var instanceof i7) {
            i7Var = (i7) k20Var;
            int i = i7Var.h;
            if ((i & Integer.MIN_VALUE) != 0) {
                i7Var.h = i - Integer.MIN_VALUE;
            } else {
                i7Var = new i7(this, k20Var);
            }
        } else {
            i7Var = new i7(this, k20Var);
        }
        Object obj = i7Var.f;
        h30 h30Var = h30.b;
        int i2 = i7Var.h;
        if (i2 == 0) {
            kk3.y(obj);
            j02 j02Var = (j02) ((g40) this.d.getValue()).b.get();
            if (this.l == null) {
                ik.j("Required value was null.");
                return null;
            }
            l00 l00VarJ = ro.J();
            try {
                JSONObject jSONObject = new JSONObject();
                String string = UUID.randomUUID().toString();
                string.getClass();
                jSONObject.put("requestId", string);
                jSONObject.put("text", str);
                String string2 = jSONObject.toString();
                string2.getClass();
                byte[] bytes = string2.getBytes(mu.a);
                bytes.getClass();
                j7 j7Var = new j7(2, j02Var, j02.class, "detectV2", "detectV2(Lcom/miui/securitycenter/confidentialcomputing/EncryptedData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0, 0, 0);
                i7Var.e = l00VarJ;
                i7Var.h = 1;
                Object objO = kk3.o(bytes, l00VarJ, j7Var, i7Var);
                if (objO == h30Var) {
                    return h30Var;
                }
                l00Var = l00VarJ;
                obj = objO;
            } catch (Throwable th2) {
                th = th2;
                l00Var = l00VarJ;
                th = th;
                throw th;
            }
        } else {
            if (i2 != 1) {
                ik.j("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            l00Var = i7Var.e;
            try {
                kk3.y(obj);
            } catch (Throwable th3) {
                th = th3;
                th = th;
                try {
                    throw th;
                } catch (Throwable th4) {
                    u83.l(l00Var, th);
                    throw th4;
                }
            }
        }
        Boolean boolValueOf = Boolean.valueOf(((JSONObject) obj).getBoolean("checkRes"));
        u83.l(l00Var, null);
        return boolValueOf;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    public final Object e(Path path, k20 k20Var) throws k00 {
        k7 k7Var;
        l00 l00Var;
        Throwable th;
        if (k20Var instanceof k7) {
            k7Var = (k7) k20Var;
            int i = k7Var.h;
            if ((i & Integer.MIN_VALUE) != 0) {
                k7Var.h = i - Integer.MIN_VALUE;
            } else {
                k7Var = new k7(this, k20Var);
            }
        } else {
            k7Var = new k7(this, k20Var);
        }
        Object obj = k7Var.f;
        h30 h30Var = h30.b;
        int i2 = k7Var.h;
        if (i2 == 0) {
            kk3.y(obj);
            k43 k43Var = (k43) ((g40) this.d.getValue()).c.get();
            if (this.l == null) {
                ik.j("Required value was null.");
                return null;
            }
            l00 l00VarJ = ro.J();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("streamName", "clip.mp4");
                Base64.Encoder encoder = Base64.getEncoder();
                byte[] allBytes = Files.readAllBytes(path);
                allBytes.getClass();
                String strEncodeToString = encoder.encodeToString(allBytes);
                strEncodeToString.getClass();
                jSONObject.put("fileBase64", strEncodeToString);
                String string = UUID.randomUUID().toString();
                string.getClass();
                jSONObject.put("requestId", string);
                String string2 = jSONObject.toString();
                string2.getClass();
                byte[] bytes = string2.getBytes(mu.a);
                bytes.getClass();
                l7 l7Var = new l7(2, k43Var, k43.class, "detectV2", "detectV2(Lcom/miui/securitycenter/confidentialcomputing/EncryptedData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0, 0, 0);
                k7Var.e = l00VarJ;
                k7Var.h = 1;
                Object objO = kk3.o(bytes, l00VarJ, l7Var, k7Var);
                if (objO == h30Var) {
                    return h30Var;
                }
                l00Var = l00VarJ;
                obj = objO;
            } catch (Throwable th2) {
                th = th2;
                l00Var = l00VarJ;
                th = th;
                throw th;
            }
        } else {
            if (i2 != 1) {
                ik.j("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            l00Var = k7Var.e;
            try {
                kk3.y(obj);
            } catch (Throwable th3) {
                th = th3;
                th = th;
                try {
                    throw th;
                } catch (Throwable th4) {
                    u83.l(l00Var, th);
                    throw th4;
                }
            }
        }
        Boolean boolValueOf = Boolean.valueOf(((JSONObject) obj).getBoolean("checkRes"));
        u83.l(l00Var, null);
        return boolValueOf;
    }

    public final pj0 f() {
        return (pj0) this.i.getValue();
    }

    public final synchronized void g() {
        try {
            kn2 kn2Var = this.g;
            if (kn2Var != null) {
                kn2Var.b(null);
            }
            this.g = null;
            si siVar = this.e;
            if (siVar == null) {
                return;
            }
            this.e = null;
            this.f = null;
            siVar.b();
            uk3.z(this.c, null, null, new y7(siVar, (j20) null), 3);
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        uk3.z(this.b, null, null, new n7(1, null, this), 3);
    }

    @Override // android.app.Service
    public final void onDestroy() {
        super.onDestroy();
        this.l = null;
        g();
        if (!this.h) {
            uk3.z(this.c, null, null, new p7(this, null), 3);
        }
        g7 g7Var = this.m;
        int i = g7Var.a;
        int i2 = g7Var.b;
        int i3 = g7Var.c;
        int i4 = g7Var.d;
        int i5 = g7Var.e;
        SharedPreferences sharedPreferences = getSharedPreferences("ai_guard_daily_report", 0);
        sharedPreferences.getClass();
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.getClass();
        editorEdit.putInt("qqVideoDetectionTimes", sharedPreferences.getInt("qqVideoDetectionTimes", 0) + i);
        editorEdit.putInt("qqVoiceDetectionTimes", sharedPreferences.getInt("qqVoiceDetectionTimes", 0) + i2);
        editorEdit.putInt("wechatVideoDetectionTimes", sharedPreferences.getInt("wechatVideoDetectionTimes", 0) + i3);
        editorEdit.putInt("wechatVoiceDetectionTimes", sharedPreferences.getInt("wechatVoiceDetectionTimes", 0) + i4);
        editorEdit.putInt("phoneVoiceDetectionTimes", sharedPreferences.getInt("phoneVoiceDetectionTimes", 0) + i5);
        editorEdit.apply();
        az0.o(this.b);
        Log.i("AiGuardService", "Destroyed.");
    }

    /* JADX WARN: Code duplicated, block: B:103:0x03ea A[Catch: all -> 0x036a, TryCatch #0 {, blocks: (B:80:0x0342, B:82:0x0346, B:92:0x036d, B:85:0x034b, B:87:0x0351, B:93:0x0390, B:100:0x03d0, B:101:0x03e3, B:102:0x03e4, B:104:0x03f2, B:110:0x0436, B:111:0x0446, B:108:0x0430, B:103:0x03ea, B:105:0x042a), top: B:123:0x0342, inners: #2 }] */
    /* JADX WARN: Code duplicated, block: B:110:0x0436 A[Catch: all -> 0x036a, TryCatch #0 {, blocks: (B:80:0x0342, B:82:0x0346, B:92:0x036d, B:85:0x034b, B:87:0x0351, B:93:0x0390, B:100:0x03d0, B:101:0x03e3, B:102:0x03e4, B:104:0x03f2, B:110:0x0436, B:111:0x0446, B:108:0x0430, B:103:0x03ea, B:105:0x042a), top: B:123:0x0342, inners: #2 }] */
    /* JADX WARN: Code duplicated, block: B:111:0x0446 A[Catch: all -> 0x036a, TRY_LEAVE, TryCatch #0 {, blocks: (B:80:0x0342, B:82:0x0346, B:92:0x036d, B:85:0x034b, B:87:0x0351, B:93:0x0390, B:100:0x03d0, B:101:0x03e3, B:102:0x03e4, B:104:0x03f2, B:110:0x0436, B:111:0x0446, B:108:0x0430, B:103:0x03ea, B:105:0x042a), top: B:123:0x0342, inners: #2 }] */
    /* JADX WARN: Code duplicated, block: B:95:0x03c9  */
    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) throws IOException {
        qq qqVar;
        Object esVar;
        si siVar;
        intent.getClass();
        int intExtra = intent.getIntExtra("COMMAND", 0);
        this.n = intent.getIntExtra("CAPABILITIES", 0);
        if (intExtra == 3) {
            uk3.z(this.b, null, null, new q7(this, intent.getBooleanExtra("ai_guard_function_switch", false), null), 3);
            return super.onStartCommand(intent, i, i2);
        }
        Uri uri = Settings.System.DEFAULT_NOTIFICATION_URI;
        AudioAttributes audioAttributes = Notification.AUDIO_ATTRIBUTES_DEFAULT;
        String string = getString(R.string.ai_guard_settings_preference_title);
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        NotificationChannel notificationChannel = new NotificationChannel("AiGuardService", string, 3);
        notificationChannel.setDescription(null);
        notificationChannel.setGroup(null);
        notificationChannel.setShowBadge(true);
        notificationChannel.setSound(uri, audioAttributes);
        notificationChannel.enableLights(false);
        notificationChannel.setLightColor(0);
        notificationChannel.setVibrationPattern(null);
        notificationChannel.enableVibration(false);
        notificationManager.createNotificationChannel(notificationChannel);
        PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent().setComponent(ComponentName.unflattenFromString("com.miui.securitycenter/com.miui.electricrisk.ElectricProtectPhoneActivity")).addFlags(268435456), 201326592);
        Bundle bundle = new Bundle();
        String string2 = getString(R.string.ai_guard_notification_title);
        string2.getClass();
        String string3 = (this.n & 2) == 0 ? getString(R.string.ai_guard_notification_message_1) : getString(R.string.ai_guard_notification_message);
        string3.getClass();
        StringBuilder sb = new StringBuilder("\n            {\n              \"scene\": \"templateRevertProgressScene\",\n              \"param_v2\": {\n                \"protocol\": 1,\n                \"business\": \"AI Fraud Prevention\",\n                \"ticker\": \"");
        sb.append(string2);
        sb.append("\", \n                \"tickerPic\": \"miui.focus.pic_landPic\",\n                \"tickerPicDark\": \"miui.focus.pic_landPic\",\n                \"updatable\": true,\n                \"showSmallIcon\": true,\n                \"picInfo\": {\n                  \"type\": 2,\n                  \"pic\": \"miui.focus.pic_landPic\"\n                },\n                \"baseInfo\": {\n                  \"title\": \"");
        sb.append(string2);
        d61.B(sb, "\", \n                  \"content\": \"", string3, "\", \n                  \"type\": 2\n                },\n                \"aodPic\": \"miui.focus.pic_landPic\",\n                \"islandFirstFloat\": false,\n                \"param_island\": {\n                  \"islandProperty\": 1,\n                  \"islandPriority\": 1,\n                  \"islandOrder\": true,\n                  \"dismissIsland\": false,\n                  \"bigIslandArea\": {\n                    \"textInfo\": {\n                      \"title\": \"", string2);
        sb.append("\"\n                    },\n                    \"imageTextInfoLeft\": {\n                      \"type\": 1,\n                      \"picInfo\": {\n                        \"type\": 1,\n                        \"pic\": \"miui.focus.pic_landPic\"\n                      }\n                    }\n                  },\n                  \"smallIslandArea\": {\n                    \"picInfo\": {\n                      \"type\": 1,\n                      \"pic\": \"miui.focus.pic_landPic\"\n                    }\n                  }\n                }\n              }\n            }\n        ");
        String strD = yp2.D(sb.toString());
        Icon iconCreateWithResource = Icon.createWithResource(this, R.drawable.ai_guard_notification_icon);
        iconCreateWithResource.getClass();
        bundle.putParcelable("miui.focus.pics", hn.c(new qy1("miui.appIcon", iconCreateWithResource), new qy1("miui.focus.pic_landPic", iconCreateWithResource)));
        bundle.putString("miui.focus.param", strD);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Notification notification = new Notification();
        notification.when = System.currentTimeMillis();
        notification.audioStreamType = -1;
        ArrayList arrayList4 = new ArrayList();
        String string4 = getString(R.string.ai_guard_notification_title);
        String strSubSequence = string4;
        if (string4 != null && string4.length() > 5120) {
            strSubSequence = string4;
            strSubSequence = string4.subSequence(0, 5120);
        }
        strSubSequence = string4;
        int length = string3.length();
        String strSubSequence2 = string3;
        if (length > 5120) {
            strSubSequence2 = string3.subSequence(0, 5120);
        }
        notification.icon = R.drawable.ai_guard_notification_icon;
        Bundle bundle2 = new Bundle(bundle);
        Bundle bundle3 = new Bundle();
        Notification.Builder builder = new Notification.Builder(this, "AiGuardService");
        builder.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, null).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(strSubSequence).setContentText(strSubSequence2).setContentInfo(null).setContentIntent(activity).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(null, (notification.flags & 128) != 0).setNumber(0).setProgress(0, 0, false);
        builder.setLargeIcon((Icon) null);
        builder.setSubText(null).setUsesChronometer(false).setPriority(0);
        Iterator it = arrayList.iterator();
        if (it.hasNext()) {
            it.next().getClass();
            ik.b();
            return 0;
        }
        bundle3.putAll(bundle2);
        builder.setShowWhen(true);
        builder.setLocalOnly(false);
        builder.setGroup(null);
        builder.setSortKey(null);
        builder.setGroupSummary(false);
        builder.setCategory(null);
        builder.setColor(0);
        builder.setVisibility(0);
        builder.setPublicVersion(null);
        builder.setSound(notification.sound, notification.audioAttributes);
        if (!arrayList4.isEmpty()) {
            Iterator it2 = arrayList4.iterator();
            while (it2.hasNext()) {
                builder.addPerson((String) it2.next());
            }
        }
        if (arrayList3.size() > 0) {
            Bundle bundle4 = bundle2.getBundle("android.car.EXTENSIONS");
            if (bundle4 == null) {
                bundle4 = new Bundle();
            }
            Bundle bundle5 = new Bundle(bundle4);
            Bundle bundle6 = new Bundle();
            if (arrayList3.size() > 0) {
                Integer.toString(0);
                if (arrayList3.get(0) != null) {
                    ik.b();
                    return 0;
                }
                new Bundle();
                throw null;
            }
            bundle4.putBundle("invisible_actions", bundle6);
            bundle5.putBundle("invisible_actions", bundle6);
            bundle2.putBundle("android.car.EXTENSIONS", bundle4);
            bundle3.putBundle("android.car.EXTENSIONS", bundle5);
        }
        builder.setExtras(bundle2);
        builder.setRemoteInputHistory(null);
        builder.setBadgeIconType(0);
        builder.setSettingsText(null);
        builder.setShortcutId(null);
        builder.setTimeoutAfter(0L);
        builder.setGroupAlertBehavior(0);
        if (!TextUtils.isEmpty("AiGuardService")) {
            builder.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
        }
        Iterator it3 = arrayList2.iterator();
        if (it3.hasNext()) {
            it3.next().getClass();
            ik.b();
            return 0;
        }
        builder.setAllowSystemGeneratedContextualActions(true);
        builder.setBubbleMetadata(null);
        if (Build.VERSION.SDK_INT >= 36) {
            ja2.h0(builder);
        }
        Notification notificationBuild = builder.build();
        notificationBuild.getClass();
        startForeground(1, notificationBuild, 160);
        uk3.z(this.b, null, null, new o7(this, null, 0), 3);
        try {
            this.l = new ro(this);
        } catch (Exception e) {
            Log.e("AiGuardService", "Failed to init pcc:", e);
        }
        if (intExtra == 1) {
            int intExtra2 = intent.getIntExtra("PHONE_SCAM_SCENE", 0);
            if (checkSelfPermission("android.permission.RECORD_AUDIO") == 0) {
                synchronized (this) {
                    if (this.e == null) {
                        long j = ((long) ((hx) this.k.getValue()).c) * 1000;
                        a7 a7Var = new a7(0);
                        a7Var.d = this;
                        a7Var.c = intExtra2;
                        VarHandle.storeStoreFence();
                        qqVar = new qq();
                        qqVar.a = j;
                        qqVar.b = a7Var;
                        qqVar.c = new ArrayList(16);
                        qqVar.d = System.currentTimeMillis();
                        VarHandle.storeStoreFence();
                        AudioFormat audioFormat = uj.a;
                        if (intExtra2 != 2) {
                            if (intExtra2 != 3 && intExtra2 != 4) {
                                throw new IllegalArgumentException("Unexpected value: " + intExtra2);
                            }
                            esVar = new k63();
                        } else {
                            esVar = new es();
                            VarHandle.storeStoreFence();
                        }
                        Context applicationContext = getApplicationContext();
                        applicationContext.getClass();
                        siVar = new si();
                        siVar.c = esVar;
                        AiCapability aiCapability = new AiCapability(applicationContext, null, null, 6, null);
                        siVar.d = aiCapability;
                        qi qiVar = new qi();
                        qiVar.b = siVar;
                        qiVar.a = new ri3(1);
                        VarHandle.storeStoreFence();
                        siVar.g = qiVar;
                        VarHandle.storeStoreFence();
                        this.e = siVar;
                        this.f = Integer.valueOf(intExtra2);
                        try {
                            aiCapability.start();
                        } catch (Throwable th) {
                            kk3.m(th);
                        }
                        if (intExtra2 == 2) {
                            this.g = uk3.z(this.b, null, null, new t7(this, siVar, qqVar, (j20) null), 3);
                        } else {
                            b7 b7Var = new b7(0);
                            b7Var.c = qqVar;
                            b7Var.d = this;
                            VarHandle.storeStoreFence();
                            c7 c7Var = new c7(0);
                            c7Var.b = this;
                            VarHandle.storeStoreFence();
                            siVar.d(b7Var, c7Var);
                        }
                    } else {
                        Integer num = this.f;
                        if (num != null && num.intValue() == intExtra2) {
                            Log.i("AiGuardService", "Audio recording is already running for scene:" + intExtra2 + ", skip.");
                        } else {
                            Log.i("AiGuardService", "Audio recording scene changed:" + this.f + " -> " + intExtra2 + ", restart it.");
                            g();
                            long j2 = ((long) ((hx) this.k.getValue()).c) * 1000;
                            a7 a7Var2 = new a7(0);
                            a7Var2.d = this;
                            a7Var2.c = intExtra2;
                            VarHandle.storeStoreFence();
                            qqVar = new qq();
                            qqVar.a = j2;
                            qqVar.b = a7Var2;
                            qqVar.c = new ArrayList(16);
                            qqVar.d = System.currentTimeMillis();
                            VarHandle.storeStoreFence();
                            AudioFormat audioFormat2 = uj.a;
                            if (intExtra2 != 2) {
                                if (intExtra2 != 3) {
                                    throw new IllegalArgumentException("Unexpected value: " + intExtra2);
                                }
                                esVar = new k63();
                            } else {
                                esVar = new es();
                                VarHandle.storeStoreFence();
                            }
                            Context applicationContext2 = getApplicationContext();
                            applicationContext2.getClass();
                            siVar = new si();
                            siVar.c = esVar;
                            AiCapability aiCapability2 = new AiCapability(applicationContext2, null, null, 6, null);
                            siVar.d = aiCapability2;
                            qi qiVar2 = new qi();
                            qiVar2.b = siVar;
                            qiVar2.a = new ri3(1);
                            VarHandle.storeStoreFence();
                            siVar.g = qiVar2;
                            VarHandle.storeStoreFence();
                            this.e = siVar;
                            this.f = Integer.valueOf(intExtra2);
                            aiCapability2.start();
                            if (intExtra2 == 2) {
                                this.g = uk3.z(this.b, null, null, new t7(this, siVar, qqVar, (j20) null), 3);
                            } else {
                                b7 b7Var2 = new b7(0);
                                b7Var2.c = qqVar;
                                b7Var2.d = this;
                                VarHandle.storeStoreFence();
                                c7 c7Var2 = new c7(0);
                                c7Var2.b = this;
                                VarHandle.storeStoreFence();
                                siVar.d(b7Var2, c7Var2);
                            }
                        }
                    }
                }
                Log.i("AiGuardService", "Phone scam detection started,scene:" + intExtra2);
            } else {
                Log.w("AiGuardService", "Required permissions have been revoked,phone scam detection is unavailable.");
            }
        } else if (intExtra != 2) {
            stopSelf();
            Log.w("AiGuardService", "Unknown command:" + intExtra + ".");
        } else {
            uk3.z(this.b, null, null, new r7(intent.getIntExtra("VIDEO_SCAM_SCENE", 0), null, this), 3);
            Log.i("AiGuardService", "Video scam detection started.");
        }
        return super.onStartCommand(intent, i, i2);
    }
}
