package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.work.impl.background.systemalarm.RescheduleReceiver;
import com.miui.guardprovider.aiguard.AiGuardService;
import com.xiaomi.onetrack.api.al;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public final class h7 extends lr2 implements pp0 {
    public final /* synthetic */ int f = 1;
    public /* synthetic */ boolean g;
    public final /* synthetic */ Context h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h7(AiGuardService aiGuardService, boolean z, j20 j20Var) {
        super(2, j20Var);
        this.h = aiGuardService;
        this.g = z;
    }

    @Override // defpackage.pp0
    public final Object H(Object obj, Object obj2) throws IllegalAccessException, InvocationTargetException {
        int i = this.f;
        c13 c13Var = c13.a;
        switch (i) {
            case 0:
                ((h7) k((j20) obj2, (g30) obj)).o(c13Var);
                break;
            default:
                Boolean bool = (Boolean) obj;
                bool.booleanValue();
                ((h7) k((j20) obj2, bool)).o(c13Var);
                break;
        }
        return c13Var;
    }

    @Override // defpackage.sk
    public final j20 k(j20 j20Var, Object obj) {
        int i = this.f;
        Context context = this.h;
        switch (i) {
            case 0:
                return new h7((AiGuardService) context, this.g, j20Var);
            default:
                h7 h7Var = new h7(context, j20Var);
                h7Var.g = ((Boolean) obj).booleanValue();
                return h7Var;
        }
    }

    @Override // defpackage.sk
    public final Object o(Object obj) throws IllegalAccessException, InvocationTargetException {
        int i = this.f;
        c13 c13Var = c13.a;
        Context context = this.h;
        switch (i) {
            case 0:
                h30 h30Var = h30.b;
                kk3.y(obj);
                Log.i("AiGuardService", "Reporting daily statistics...");
                AiGuardService aiGuardService = (AiGuardService) context;
                Context applicationContext = aiGuardService.getApplicationContext();
                applicationContext.getClass();
                ro.I(applicationContext).a.track("daily_report", xl1.G(new qy1[]{new qy1(al.ae, "1127.0.0.0.37700"), new qy1("enable_status", Boolean.valueOf(this.g))}));
                SharedPreferences sharedPreferences = aiGuardService.getSharedPreferences("ai_guard_daily_report", 0);
                ii1 ii1VarN = kk3.n();
                ii1VarN.add(xl1.C(new qy1("trigger_scenario", "电话"), new qy1("detect_number", new Integer(sharedPreferences.getInt("phoneVoiceDetectionTimes", 0)))));
                ii1VarN.add(xl1.C(new qy1("trigger_scenario", "QQ语音通话"), new qy1("detect_number", new Integer(sharedPreferences.getInt("qqVoiceDetectionTimes", 0)))));
                ii1VarN.add(xl1.C(new qy1("trigger_scenario", "QQ视频通话"), new qy1("detect_number", new Integer(sharedPreferences.getInt("qqVideoDetectionTimes", 0)))));
                ii1VarN.add(xl1.C(new qy1("trigger_scenario", "微信语音通话"), new qy1("detect_number", new Integer(sharedPreferences.getInt("wechatVoiceDetectionTimes", 0)))));
                ii1VarN.add(xl1.C(new qy1("trigger_scenario", "微信视频通话"), new qy1("detect_number", new Integer(sharedPreferences.getInt("wechatVideoDetectionTimes", 0)))));
                ii1 ii1VarJ = kk3.j(ii1VarN);
                Context applicationContext2 = aiGuardService.getApplicationContext();
                applicationContext2.getClass();
                ro.I(applicationContext2).a.track("daily_report", xl1.G(new qy1[]{new qy1(al.ae, "1127.0.0.0.37704"), new qy1("detect_status", ii1VarJ)}));
                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                editorEdit.getClass();
                editorEdit.clear();
                editorEdit.apply();
                break;
            default:
                h30 h30Var2 = h30.b;
                kk3.y(obj);
                hy1.a(context, RescheduleReceiver.class, this.g);
                break;
        }
        return c13Var;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h7(Context context, j20 j20Var) {
        super(2, j20Var);
        this.h = context;
    }
}
