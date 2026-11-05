package defpackage;

import android.content.ContentResolver;
import android.os.Build;
import android.util.Log;
import com.miui.guardprovider.aiguard.AiGuardService;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class z6 implements np0 {
    public final /* synthetic */ int b;
    public /* synthetic */ AiGuardService c;

    /* JADX DEBUG: Marked for inline */
    /* JADX DEBUG: Method not inlined, still used in: [com.miui.guardprovider.aiguard.AiGuardService.<init>():void] */
    public /* synthetic */ z6(int i) {
        this.b = i;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0236  */
    /* JADX WARN: Code duplicated, block: B:102:0x0239 A[Catch: Exception -> 0x026e, TryCatch #1 {Exception -> 0x026e, blocks: (B:93:0x020b, B:105:0x0245, B:108:0x0252, B:110:0x0265, B:111:0x0268, B:112:0x026d, B:96:0x021f, B:99:0x022c, B:102:0x0239), top: B:148:0x020b }] */
    /* JADX WARN: Code duplicated, block: B:104:0x0243  */
    /* JADX WARN: Code duplicated, block: B:107:0x0251  */
    /* JADX WARN: Code duplicated, block: B:110:0x0265 A[Catch: Exception -> 0x026e, TryCatch #1 {Exception -> 0x026e, blocks: (B:93:0x020b, B:105:0x0245, B:108:0x0252, B:110:0x0265, B:111:0x0268, B:112:0x026d, B:96:0x021f, B:99:0x022c, B:102:0x0239), top: B:148:0x020b }] */
    /* JADX WARN: Code duplicated, block: B:111:0x0268 A[Catch: Exception -> 0x026e, TryCatch #1 {Exception -> 0x026e, blocks: (B:93:0x020b, B:105:0x0245, B:108:0x0252, B:110:0x0265, B:111:0x0268, B:112:0x026d, B:96:0x021f, B:99:0x022c, B:102:0x0239), top: B:148:0x020b }] */
    /* JADX WARN: Code duplicated, block: B:113:0x026e  */
    /* JADX WARN: Code duplicated, block: B:117:0x0290  */
    /* JADX WARN: Code duplicated, block: B:118:0x0291 A[Catch: Exception -> 0x02e2, TryCatch #8 {Exception -> 0x02e2, blocks: (B:115:0x027e, B:127:0x02b7, B:131:0x02c5, B:133:0x02d8, B:134:0x02dc, B:135:0x02e1, B:118:0x0291, B:121:0x029e, B:124:0x02ab), top: B:158:0x027e }] */
    /* JADX WARN: Code duplicated, block: B:120:0x029b  */
    /* JADX WARN: Code duplicated, block: B:121:0x029e A[Catch: Exception -> 0x02e2, TryCatch #8 {Exception -> 0x02e2, blocks: (B:115:0x027e, B:127:0x02b7, B:131:0x02c5, B:133:0x02d8, B:134:0x02dc, B:135:0x02e1, B:118:0x0291, B:121:0x029e, B:124:0x02ab), top: B:158:0x027e }] */
    /* JADX WARN: Code duplicated, block: B:123:0x02a8  */
    /* JADX WARN: Code duplicated, block: B:124:0x02ab A[Catch: Exception -> 0x02e2, TryCatch #8 {Exception -> 0x02e2, blocks: (B:115:0x027e, B:127:0x02b7, B:131:0x02c5, B:133:0x02d8, B:134:0x02dc, B:135:0x02e1, B:118:0x0291, B:121:0x029e, B:124:0x02ab), top: B:158:0x027e }] */
    /* JADX WARN: Code duplicated, block: B:126:0x02b5  */
    /* JADX WARN: Code duplicated, block: B:129:0x02c3  */
    /* JADX WARN: Code duplicated, block: B:130:0x02c4  */
    /* JADX WARN: Code duplicated, block: B:133:0x02d8 A[Catch: Exception -> 0x02e2, TryCatch #8 {Exception -> 0x02e2, blocks: (B:115:0x027e, B:127:0x02b7, B:131:0x02c5, B:133:0x02d8, B:134:0x02dc, B:135:0x02e1, B:118:0x0291, B:121:0x029e, B:124:0x02ab), top: B:158:0x027e }] */
    /* JADX WARN: Code duplicated, block: B:134:0x02dc A[Catch: Exception -> 0x02e2, TryCatch #8 {Exception -> 0x02e2, blocks: (B:115:0x027e, B:127:0x02b7, B:131:0x02c5, B:133:0x02d8, B:134:0x02dc, B:135:0x02e1, B:118:0x0291, B:121:0x029e, B:124:0x02ab), top: B:158:0x027e }] */
    /* JADX WARN: Code duplicated, block: B:35:0x00c8 A[Catch: c51 -> 0x00d6, TRY_LEAVE, TryCatch #3 {c51 -> 0x00d6, blocks: (B:32:0x00ab, B:33:0x00c2, B:35:0x00c8), top: B:150:0x00ab }] */
    /* JADX WARN: Code duplicated, block: B:43:0x010f  */
    /* JADX WARN: Code duplicated, block: B:48:0x012c  */
    /* JADX WARN: Code duplicated, block: B:49:0x012e A[Catch: Exception -> 0x017d, TryCatch #2 {Exception -> 0x017d, blocks: (B:46:0x0122, B:58:0x0154, B:61:0x0161, B:63:0x0174, B:64:0x0177, B:65:0x017c, B:49:0x012e, B:52:0x013b, B:55:0x0148), top: B:149:0x0122 }] */
    /* JADX WARN: Code duplicated, block: B:51:0x0138  */
    /* JADX WARN: Code duplicated, block: B:52:0x013b A[Catch: Exception -> 0x017d, TryCatch #2 {Exception -> 0x017d, blocks: (B:46:0x0122, B:58:0x0154, B:61:0x0161, B:63:0x0174, B:64:0x0177, B:65:0x017c, B:49:0x012e, B:52:0x013b, B:55:0x0148), top: B:149:0x0122 }] */
    /* JADX WARN: Code duplicated, block: B:54:0x0145  */
    /* JADX WARN: Code duplicated, block: B:55:0x0148 A[Catch: Exception -> 0x017d, TryCatch #2 {Exception -> 0x017d, blocks: (B:46:0x0122, B:58:0x0154, B:61:0x0161, B:63:0x0174, B:64:0x0177, B:65:0x017c, B:49:0x012e, B:52:0x013b, B:55:0x0148), top: B:149:0x0122 }] */
    /* JADX WARN: Code duplicated, block: B:57:0x0152  */
    /* JADX WARN: Code duplicated, block: B:60:0x0160  */
    /* JADX WARN: Code duplicated, block: B:63:0x0174 A[Catch: Exception -> 0x017d, TryCatch #2 {Exception -> 0x017d, blocks: (B:46:0x0122, B:58:0x0154, B:61:0x0161, B:63:0x0174, B:64:0x0177, B:65:0x017c, B:49:0x012e, B:52:0x013b, B:55:0x0148), top: B:149:0x0122 }] */
    /* JADX WARN: Code duplicated, block: B:64:0x0177 A[Catch: Exception -> 0x017d, TryCatch #2 {Exception -> 0x017d, blocks: (B:46:0x0122, B:58:0x0154, B:61:0x0161, B:63:0x0174, B:64:0x0177, B:65:0x017c, B:49:0x012e, B:52:0x013b, B:55:0x0148), top: B:149:0x0122 }] */
    /* JADX WARN: Code duplicated, block: B:66:0x017d A[PHI: r19
      0x017d: PHI (r19v3 java.lang.String) = (r19v2 java.lang.String), (r19v5 java.lang.String), (r19v5 java.lang.String) binds: [B:67:0x017f, B:142:0x017d, B:56:0x0150] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:69:0x018c  */
    /* JADX WARN: Code duplicated, block: B:72:0x01a9  */
    /* JADX WARN: Code duplicated, block: B:73:0x01ab A[Catch: Exception -> 0x01fa, TryCatch #4 {Exception -> 0x01fa, blocks: (B:70:0x0197, B:82:0x01d1, B:85:0x01de, B:87:0x01f1, B:88:0x01f4, B:89:0x01f9, B:73:0x01ab, B:76:0x01b8, B:79:0x01c5), top: B:152:0x0197 }] */
    /* JADX WARN: Code duplicated, block: B:75:0x01b5  */
    /* JADX WARN: Code duplicated, block: B:76:0x01b8 A[Catch: Exception -> 0x01fa, TryCatch #4 {Exception -> 0x01fa, blocks: (B:70:0x0197, B:82:0x01d1, B:85:0x01de, B:87:0x01f1, B:88:0x01f4, B:89:0x01f9, B:73:0x01ab, B:76:0x01b8, B:79:0x01c5), top: B:152:0x0197 }] */
    /* JADX WARN: Code duplicated, block: B:78:0x01c2  */
    /* JADX WARN: Code duplicated, block: B:79:0x01c5 A[Catch: Exception -> 0x01fa, TryCatch #4 {Exception -> 0x01fa, blocks: (B:70:0x0197, B:82:0x01d1, B:85:0x01de, B:87:0x01f1, B:88:0x01f4, B:89:0x01f9, B:73:0x01ab, B:76:0x01b8, B:79:0x01c5), top: B:152:0x0197 }] */
    /* JADX WARN: Code duplicated, block: B:81:0x01cf  */
    /* JADX WARN: Code duplicated, block: B:84:0x01dd  */
    /* JADX WARN: Code duplicated, block: B:87:0x01f1 A[Catch: Exception -> 0x01fa, TryCatch #4 {Exception -> 0x01fa, blocks: (B:70:0x0197, B:82:0x01d1, B:85:0x01de, B:87:0x01f1, B:88:0x01f4, B:89:0x01f9, B:73:0x01ab, B:76:0x01b8, B:79:0x01c5), top: B:152:0x0197 }] */
    /* JADX WARN: Code duplicated, block: B:88:0x01f4 A[Catch: Exception -> 0x01fa, TryCatch #4 {Exception -> 0x01fa, blocks: (B:70:0x0197, B:82:0x01d1, B:85:0x01de, B:87:0x01f1, B:88:0x01f4, B:89:0x01f9, B:73:0x01ab, B:76:0x01b8, B:79:0x01c5), top: B:152:0x0197 }] */
    /* JADX WARN: Code duplicated, block: B:90:0x01fa  */
    /* JADX WARN: Code duplicated, block: B:95:0x021d  */
    /* JADX WARN: Code duplicated, block: B:96:0x021f A[Catch: Exception -> 0x026e, TryCatch #1 {Exception -> 0x026e, blocks: (B:93:0x020b, B:105:0x0245, B:108:0x0252, B:110:0x0265, B:111:0x0268, B:112:0x026d, B:96:0x021f, B:99:0x022c, B:102:0x0239), top: B:148:0x020b }] */
    /* JADX WARN: Code duplicated, block: B:98:0x0229  */
    /* JADX WARN: Code duplicated, block: B:99:0x022c A[Catch: Exception -> 0x026e, TryCatch #1 {Exception -> 0x026e, blocks: (B:93:0x020b, B:105:0x0245, B:108:0x0252, B:110:0x0265, B:111:0x0268, B:112:0x026d, B:96:0x021f, B:99:0x022c, B:102:0x0239), top: B:148:0x020b }] */
    @Override // defpackage.np0
    public final Object e() {
        String str;
        Class cls;
        String str2;
        ArrayList arrayList;
        boolean zContains;
        Class<Integer> cls2;
        String str3;
        Integer num;
        float fIntValue;
        fb1 fb1VarA;
        String str4;
        Class<Integer> clsY;
        Object objInvoke;
        Integer num2;
        fb1 fb1VarA2;
        Class<Integer> clsY2;
        Object objInvoke2;
        fb1 fb1VarA3;
        String str5;
        Class<Integer> clsY3;
        Object objInvoke3;
        Integer num3;
        fb1 fb1VarA4;
        String str6;
        Class<Integer> clsY4;
        Object objInvoke4;
        Iterator it;
        String str7;
        int i = this.b;
        AiGuardService aiGuardService = this.c;
        switch (i) {
            case 0:
                int i2 = AiGuardService.o;
                return n53.p(aiGuardService);
            default:
                int i3 = AiGuardService.o;
                Class cls3 = Long.TYPE;
                Class cls4 = Boolean.TYPE;
                String str8 = "getCloudDataInt";
                Class cls5 = Integer.TYPE;
                hx hxVar = new hx();
                Integer num4 = 10;
                ContentResolver contentResolver = aiGuardService.getContentResolver();
                contentResolver.getClass();
                try {
                    Class<?> cls6 = Class.forName("android.provider.MiuiSettings$SettingsCloudData");
                    str = "getCloudDataLong";
                    try {
                        fb1 fb1VarA5 = va2.a(String.class);
                        cls = cls3;
                        try {
                            if (az0.j(fb1VarA5, va2.a(cls5))) {
                                str7 = "getCloudDataInt";
                            } else if (az0.j(fb1VarA5, va2.a(cls4))) {
                                str7 = "getCloudDataBoolean";
                            } else if (az0.j(fb1VarA5, va2.a(cls))) {
                                str7 = str;
                            } else {
                                if (az0.j(fb1VarA5, va2.a(String.class))) {
                                    str7 = "getCloudDataString";
                                } else {
                                    str2 = "[\n                                                            \"dada\",//O3\n                                                            \"haotian\",//O2\n                                                            \"xuanyuan\",//O1\n                                                            \"pudding\",//P3\n                                                            \"popsicle\",//P2\n                                                            \"miro\"//O11U\n                                                    ]";
                                }
                                try {
                                    i31 i31VarF = vh3.y(str2).f();
                                    arrayList = new ArrayList(cy.G(i31VarF));
                                    it = i31VarF.b.iterator();
                                    while (it.hasNext()) {
                                        arrayList.add(((t31) it.next()).l());
                                    }
                                } catch (c51 unused) {
                                    i31 i31VarF2 = vh3.y("[\n                                                            \"dada\",//O3\n                                                            \"haotian\",//O2\n                                                            \"xuanyuan\",//O1\n                                                            \"pudding\",//P3\n                                                            \"popsicle\",//P2\n                                                            \"miro\"//O11U\n                                                    ]").f();
                                    arrayList = new ArrayList(cy.G(i31VarF2));
                                    Iterator it2 = i31VarF2.b.iterator();
                                    while (it2.hasNext()) {
                                        arrayList.add(((t31) it2.next()).l());
                                    }
                                }
                                zContains = arrayList.contains(Build.DEVICE);
                                hxVar.a = zContains;
                                cls2 = Integer.class;
                                if (zContains) {
                                    ContentResolver contentResolver2 = aiGuardService.getContentResolver();
                                    contentResolver2.getClass();
                                    try {
                                        Class<?> cls7 = Class.forName("android.provider.MiuiSettings$SettingsCloudData");
                                        fb1VarA4 = va2.a(cls5);
                                        str3 = "getCloudDataBoolean";
                                        try {
                                            if (az0.j(fb1VarA4, va2.a(cls5))) {
                                                str6 = "getCloudDataInt";
                                            } else if (az0.j(fb1VarA4, va2.a(cls4))) {
                                                str6 = str3;
                                            } else if (az0.j(fb1VarA4, va2.a(cls))) {
                                                str6 = str;
                                            } else {
                                                if (az0.j(fb1VarA4, va2.a(String.class))) {
                                                    str6 = "getCloudDataString";
                                                } else {
                                                    num3 = num4;
                                                }
                                                fIntValue = 1.0f / num3.intValue();
                                            }
                                            clsY4 = bi3.y(va2.a.b(cls2));
                                            if (clsY4 == null) {
                                                clsY4 = cls2;
                                            }
                                            objInvoke4 = cls7.getMethod(str6, ContentResolver.class, String.class, String.class, clsY4).invoke(null, contentResolver2, "AiGuardServiceConfigs", "videoCaptureGap4OnDevice", num4);
                                            if (objInvoke4 != null) {
                                                throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
                                            }
                                            num3 = (Integer) objInvoke4;
                                            fIntValue = 1.0f / num3.intValue();
                                        } catch (Exception unused2) {
                                        }
                                    } catch (Exception unused3) {
                                        str3 = "getCloudDataBoolean";
                                    }
                                } else {
                                    str3 = "getCloudDataBoolean";
                                    ContentResolver contentResolver3 = aiGuardService.getContentResolver();
                                    contentResolver3.getClass();
                                    try {
                                        Class<?> cls8 = Class.forName("android.provider.MiuiSettings$SettingsCloudData");
                                        fb1VarA = va2.a(cls5);
                                        if (az0.j(fb1VarA, va2.a(cls5))) {
                                            str4 = "getCloudDataInt";
                                        } else if (az0.j(fb1VarA, va2.a(cls4))) {
                                            str4 = str3;
                                        } else if (az0.j(fb1VarA, va2.a(cls))) {
                                            str4 = str;
                                        } else {
                                            if (az0.j(fb1VarA, va2.a(String.class))) {
                                                str4 = "getCloudDataString";
                                            } else {
                                                num = num4;
                                            }
                                            fIntValue = num.intValue();
                                        }
                                        clsY = bi3.y(va2.a.b(cls2));
                                        if (clsY == null) {
                                            clsY = cls2;
                                        }
                                        objInvoke = cls8.getMethod(str4, ContentResolver.class, String.class, String.class, clsY).invoke(null, contentResolver3, "AiGuardServiceConfigs", "videoCaptureVelocity4CloudSide", num4);
                                        if (objInvoke != null) {
                                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
                                        }
                                        num = (Integer) objInvoke;
                                        fIntValue = num.intValue();
                                    } catch (Exception unused4) {
                                    }
                                }
                                hxVar.b = fIntValue;
                                ContentResolver contentResolver4 = aiGuardService.getContentResolver();
                                contentResolver4.getClass();
                                try {
                                    Class<?> cls9 = Class.forName("android.provider.MiuiSettings$SettingsCloudData");
                                    fb1VarA3 = va2.a(cls5);
                                    if (az0.j(fb1VarA3, va2.a(cls5))) {
                                        str5 = "getCloudDataInt";
                                    } else if (az0.j(fb1VarA3, va2.a(cls4))) {
                                        str5 = str3;
                                    } else if (!az0.j(fb1VarA3, va2.a(cls))) {
                                        str5 = str;
                                    } else {
                                        if (az0.j(fb1VarA3, va2.a(String.class))) {
                                            str5 = "getCloudDataString";
                                        } else {
                                            num2 = num4;
                                        }
                                        hxVar.d = num2.intValue();
                                        ContentResolver contentResolver5 = aiGuardService.getContentResolver();
                                        contentResolver5.getClass();
                                        try {
                                            Class<?> cls10 = Class.forName("android.provider.MiuiSettings$SettingsCloudData");
                                            fb1VarA2 = va2.a(cls5);
                                            if (!az0.j(fb1VarA2, va2.a(cls5))) {
                                                if (az0.j(fb1VarA2, va2.a(cls4))) {
                                                    str8 = str3;
                                                } else {
                                                    if (az0.j(fb1VarA2, va2.a(cls))) {
                                                        if (az0.j(fb1VarA2, va2.a(String.class))) {
                                                            str8 = "getCloudDataString";
                                                        }
                                                        hxVar.c = num4.intValue();
                                                        Log.i("CloudSettingConfigs", hxVar.toString());
                                                        VarHandle.storeStoreFence();
                                                        return hxVar;
                                                    }
                                                    str8 = str;
                                                }
                                            }
                                            clsY2 = bi3.y(va2.a.b(cls2));
                                            if (clsY2 == null) {
                                                cls2 = clsY2;
                                            }
                                            objInvoke2 = cls10.getMethod(str8, ContentResolver.class, String.class, String.class, cls2).invoke(null, contentResolver5, "AiGuardServiceConfigs", "voiceDetectionGapInSeconds", num4);
                                            if (objInvoke2 != null) {
                                                throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
                                            }
                                            num4 = (Integer) objInvoke2;
                                            hxVar.c = num4.intValue();
                                            Log.i("CloudSettingConfigs", hxVar.toString());
                                            VarHandle.storeStoreFence();
                                            return hxVar;
                                        } catch (Exception unused5) {
                                        }
                                    }
                                    clsY3 = bi3.y(va2.a.b(cls2));
                                    if (clsY3 == null) {
                                        clsY3 = cls2;
                                    }
                                    objInvoke3 = cls9.getMethod(str5, ContentResolver.class, String.class, String.class, clsY3).invoke(null, contentResolver4, "AiGuardServiceConfigs", "detectionServiceLifetimeThresholdInMinutes", num4);
                                    if (objInvoke3 != null) {
                                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
                                    }
                                    num2 = (Integer) objInvoke3;
                                    hxVar.d = num2.intValue();
                                    ContentResolver contentResolver6 = aiGuardService.getContentResolver();
                                    contentResolver6.getClass();
                                    Class<?> cls11 = Class.forName("android.provider.MiuiSettings$SettingsCloudData");
                                    fb1VarA2 = va2.a(cls5);
                                    if (!az0.j(fb1VarA2, va2.a(cls5))) {
                                        if (az0.j(fb1VarA2, va2.a(cls4))) {
                                            str8 = str3;
                                        } else {
                                            if (az0.j(fb1VarA2, va2.a(cls))) {
                                                if (az0.j(fb1VarA2, va2.a(String.class))) {
                                                    str8 = "getCloudDataString";
                                                }
                                                hxVar.c = num4.intValue();
                                                Log.i("CloudSettingConfigs", hxVar.toString());
                                                VarHandle.storeStoreFence();
                                                return hxVar;
                                            }
                                            str8 = str;
                                        }
                                    }
                                    clsY2 = bi3.y(va2.a.b(cls2));
                                    if (clsY2 == null) {
                                        cls2 = clsY2;
                                    }
                                    objInvoke2 = cls11.getMethod(str8, ContentResolver.class, String.class, String.class, cls2).invoke(null, contentResolver6, "AiGuardServiceConfigs", "voiceDetectionGapInSeconds", num4);
                                    if (objInvoke2 != null) {
                                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
                                    }
                                    num4 = (Integer) objInvoke2;
                                    hxVar.c = num4.intValue();
                                    Log.i("CloudSettingConfigs", hxVar.toString());
                                    VarHandle.storeStoreFence();
                                    return hxVar;
                                } catch (Exception unused6) {
                                }
                            }
                            Class clsY5 = bi3.y(va2.a.b(String.class));
                            if (clsY5 == null) {
                                clsY5 = String.class;
                            }
                            Object objInvoke5 = cls6.getMethod(str7, ContentResolver.class, String.class, String.class, clsY5).invoke(null, contentResolver, "AiGuardServiceConfigs", "onDevice", "[\n                                                            \"dada\",//O3\n                                                            \"haotian\",//O2\n                                                            \"xuanyuan\",//O1\n                                                            \"pudding\",//P3\n                                                            \"popsicle\",//P2\n                                                            \"miro\"//O11U\n                                                    ]");
                            if (objInvoke5 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                            }
                            str2 = (String) objInvoke5;
                            i31 i31VarF3 = vh3.y(str2).f();
                            arrayList = new ArrayList(cy.G(i31VarF3));
                            it = i31VarF3.b.iterator();
                            while (it.hasNext()) {
                                arrayList.add(((t31) it.next()).l());
                            }
                            zContains = arrayList.contains(Build.DEVICE);
                            hxVar.a = zContains;
                            cls2 = Integer.class;
                            if (zContains) {
                                ContentResolver contentResolver7 = aiGuardService.getContentResolver();
                                contentResolver7.getClass();
                                Class<?> cls12 = Class.forName("android.provider.MiuiSettings$SettingsCloudData");
                                fb1VarA4 = va2.a(cls5);
                                str3 = "getCloudDataBoolean";
                                if (az0.j(fb1VarA4, va2.a(cls5))) {
                                    str6 = "getCloudDataInt";
                                } else if (az0.j(fb1VarA4, va2.a(cls4))) {
                                    str6 = str3;
                                } else if (az0.j(fb1VarA4, va2.a(cls))) {
                                    str6 = str;
                                } else {
                                    if (az0.j(fb1VarA4, va2.a(String.class))) {
                                        str6 = "getCloudDataString";
                                    } else {
                                        num3 = num4;
                                    }
                                    fIntValue = 1.0f / num3.intValue();
                                }
                                clsY4 = bi3.y(va2.a.b(cls2));
                                if (clsY4 == null) {
                                    clsY4 = cls2;
                                }
                                objInvoke4 = cls12.getMethod(str6, ContentResolver.class, String.class, String.class, clsY4).invoke(null, contentResolver7, "AiGuardServiceConfigs", "videoCaptureGap4OnDevice", num4);
                                if (objInvoke4 != null) {
                                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
                                }
                                num3 = (Integer) objInvoke4;
                                fIntValue = 1.0f / num3.intValue();
                            } else {
                                str3 = "getCloudDataBoolean";
                                ContentResolver contentResolver8 = aiGuardService.getContentResolver();
                                contentResolver8.getClass();
                                Class<?> cls13 = Class.forName("android.provider.MiuiSettings$SettingsCloudData");
                                fb1VarA = va2.a(cls5);
                                if (az0.j(fb1VarA, va2.a(cls5))) {
                                    str4 = "getCloudDataInt";
                                } else if (az0.j(fb1VarA, va2.a(cls4))) {
                                    str4 = str3;
                                } else if (az0.j(fb1VarA, va2.a(cls))) {
                                    str4 = str;
                                } else {
                                    if (az0.j(fb1VarA, va2.a(String.class))) {
                                        str4 = "getCloudDataString";
                                    } else {
                                        num = num4;
                                    }
                                    fIntValue = num.intValue();
                                }
                                clsY = bi3.y(va2.a.b(cls2));
                                if (clsY == null) {
                                    clsY = cls2;
                                }
                                objInvoke = cls13.getMethod(str4, ContentResolver.class, String.class, String.class, clsY).invoke(null, contentResolver8, "AiGuardServiceConfigs", "videoCaptureVelocity4CloudSide", num4);
                                if (objInvoke != null) {
                                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
                                }
                                num = (Integer) objInvoke;
                                fIntValue = num.intValue();
                            }
                            hxVar.b = fIntValue;
                            ContentResolver contentResolver9 = aiGuardService.getContentResolver();
                            contentResolver9.getClass();
                            Class<?> cls14 = Class.forName("android.provider.MiuiSettings$SettingsCloudData");
                            fb1VarA3 = va2.a(cls5);
                            if (az0.j(fb1VarA3, va2.a(cls5))) {
                                str5 = "getCloudDataInt";
                            } else if (az0.j(fb1VarA3, va2.a(cls4))) {
                                str5 = str3;
                            } else {
                                if (!az0.j(fb1VarA3, va2.a(cls))) {
                                    if (az0.j(fb1VarA3, va2.a(String.class))) {
                                        str5 = "getCloudDataString";
                                    } else {
                                        num2 = num4;
                                    }
                                    hxVar.d = num2.intValue();
                                    ContentResolver contentResolver10 = aiGuardService.getContentResolver();
                                    contentResolver10.getClass();
                                    Class<?> cls15 = Class.forName("android.provider.MiuiSettings$SettingsCloudData");
                                    fb1VarA2 = va2.a(cls5);
                                    if (!az0.j(fb1VarA2, va2.a(cls5))) {
                                        if (az0.j(fb1VarA2, va2.a(cls4))) {
                                            str8 = str3;
                                        } else {
                                            if (az0.j(fb1VarA2, va2.a(cls))) {
                                                if (az0.j(fb1VarA2, va2.a(String.class))) {
                                                    str8 = "getCloudDataString";
                                                }
                                                hxVar.c = num4.intValue();
                                                Log.i("CloudSettingConfigs", hxVar.toString());
                                                VarHandle.storeStoreFence();
                                                return hxVar;
                                            }
                                            str8 = str;
                                        }
                                    }
                                    clsY2 = bi3.y(va2.a.b(cls2));
                                    if (clsY2 == null) {
                                        cls2 = clsY2;
                                    }
                                    objInvoke2 = cls15.getMethod(str8, ContentResolver.class, String.class, String.class, cls2).invoke(null, contentResolver10, "AiGuardServiceConfigs", "voiceDetectionGapInSeconds", num4);
                                    if (objInvoke2 != null) {
                                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
                                    }
                                    num4 = (Integer) objInvoke2;
                                    hxVar.c = num4.intValue();
                                    Log.i("CloudSettingConfigs", hxVar.toString());
                                    VarHandle.storeStoreFence();
                                    return hxVar;
                                }
                                str5 = str;
                            }
                            clsY3 = bi3.y(va2.a.b(cls2));
                            if (clsY3 == null) {
                                clsY3 = cls2;
                            }
                            objInvoke3 = cls14.getMethod(str5, ContentResolver.class, String.class, String.class, clsY3).invoke(null, contentResolver9, "AiGuardServiceConfigs", "detectionServiceLifetimeThresholdInMinutes", num4);
                            if (objInvoke3 != null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
                            }
                            num2 = (Integer) objInvoke3;
                            hxVar.d = num2.intValue();
                            ContentResolver contentResolver11 = aiGuardService.getContentResolver();
                            contentResolver11.getClass();
                            Class<?> cls16 = Class.forName("android.provider.MiuiSettings$SettingsCloudData");
                            fb1VarA2 = va2.a(cls5);
                            if (!az0.j(fb1VarA2, va2.a(cls5))) {
                                if (az0.j(fb1VarA2, va2.a(cls4))) {
                                    str8 = str3;
                                } else {
                                    if (az0.j(fb1VarA2, va2.a(cls))) {
                                        if (az0.j(fb1VarA2, va2.a(String.class))) {
                                            str8 = "getCloudDataString";
                                        }
                                        hxVar.c = num4.intValue();
                                        Log.i("CloudSettingConfigs", hxVar.toString());
                                        VarHandle.storeStoreFence();
                                        return hxVar;
                                    }
                                    str8 = str;
                                }
                            }
                            clsY2 = bi3.y(va2.a.b(cls2));
                            if (clsY2 == null) {
                                cls2 = clsY2;
                            }
                            objInvoke2 = cls16.getMethod(str8, ContentResolver.class, String.class, String.class, cls2).invoke(null, contentResolver11, "AiGuardServiceConfigs", "voiceDetectionGapInSeconds", num4);
                            if (objInvoke2 != null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
                            }
                            num4 = (Integer) objInvoke2;
                            hxVar.c = num4.intValue();
                            Log.i("CloudSettingConfigs", hxVar.toString());
                            VarHandle.storeStoreFence();
                            return hxVar;
                        } catch (Exception unused7) {
                        }
                    } catch (Exception unused8) {
                        cls = cls3;
                    }
                } catch (Exception unused9) {
                    str = "getCloudDataLong";
                }
                break;
        }
    }
}
