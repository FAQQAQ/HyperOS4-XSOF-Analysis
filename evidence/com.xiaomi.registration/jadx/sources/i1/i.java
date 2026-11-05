package i1;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.xiaomi.registration.R;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import miui.os.Build;
import miui.telephony.TelephonyManager;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class i extends Handler {
    public final int a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1715b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final HashMap f1716c = new HashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Context f1717d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ z.f f1718e;

    public i(z.f fVar, Context context, int i3, int i4) {
        int i5;
        this.f1718e = fVar;
        this.a = 0;
        this.f1715b = 0;
        this.f1717d = context;
        this.a = i3;
        this.f1715b = i4;
        Log.d("AutoReg_Pairs", "ctSlotId id = " + i3 + " primary sim = " + i4);
        Resources resources = context.getResources();
        int i6 = j1.c.a;
        String[] stringArray = context.getResources().getStringArray(R.array.config_support_modem_pad);
        String str = Build.DEVICE;
        if (stringArray == null) {
            i5 = -1;
            break;
        }
        if (str != null) {
            int i7 = 0;
            while (true) {
                if (i7 >= stringArray.length) {
                    i5 = -1;
                    break;
                } else {
                    if (str.equals(stringArray[i7])) {
                        i5 = i7;
                        break;
                    }
                    i7++;
                }
            }
        } else {
            i5 = 0;
            while (true) {
                if (i5 >= stringArray.length) {
                    i5 = -1;
                    break;
                } else if (stringArray[i5] == null) {
                    break;
                } else {
                    i5++;
                }
            }
        }
        XmlResourceParser xml = resources.getXml((!Build.IS_TABLET || (i5 != -1)) ? f2.e.l() ? R.xml.post_params_satellite : R.xml.post_params : R.xml.post_params_pad);
        try {
            f2.e.b(xml);
            while (true) {
                f2.e.n(xml);
                if (xml.getEventType() == 1) {
                    break;
                }
                h hVar = new h();
                hVar.a = xml.getAttributeValue(null, "key");
                hVar.f1712b = xml.getAttributeValue(null, "post_key");
                if (TelephonyManager.getDefault().isFiveGCapable() || !"NETWORKTYPE".equals(hVar.a)) {
                    this.f1716c.put(hVar.a, hVar);
                }
            }
        } catch (Exception e3) {
            Log.w("AutoReg_Pairs", "failed to load post_params", e3);
        } finally {
            xml.close();
        }
    }

    public final Bundle a(String str) {
        int i3;
        Bundle bundle = new Bundle();
        HashMap map = this.f1716c;
        Iterator it = map.keySet().iterator();
        loop0: while (true) {
            i3 = 0;
            do {
                if (!it.hasNext()) {
                    break loop0;
                }
                String str2 = (String) it.next();
                i3++;
                if (str != null && str.equals(str2)) {
                    break loop0;
                }
            } while (i3 != map.size());
        }
        Message messageObtainMessage = obtainMessage(i3, this.a, this.f1715b);
        messageObtainMessage.replyTo = new Messenger(this);
        bundle.putParcelable("callback", messageObtainMessage);
        return bundle;
    }

    /* JADX DEBUG: Method merged with bridge method: handleMessage(Landroid/os/Message;)V */
    @Override // android.os.Handler
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final void handleMessage(Message message) {
        String str;
        int i3 = message.what;
        HashMap map = this.f1716c;
        Iterator it = map.keySet().iterator();
        int i4 = 0;
        do {
            if (!it.hasNext()) {
                str = null;
                break;
            } else {
                str = (String) it.next();
                i4++;
            }
        } while (i3 != i4);
        if (str == null) {
            return;
        }
        h hVar = (h) map.get(str);
        if (message.obj instanceof Bundle) {
            Objects.requireNonNull(hVar);
            hVar.f1713c = ((Bundle) message.obj).getString("result");
        }
        Objects.requireNonNull(hVar);
        hVar.f1714d = true;
        c();
    }

    public final void c() {
        JSONObject jSONObject = new JSONObject();
        HashMap map = this.f1716c;
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            h hVar = (h) map.get((String) it.next());
            if (!hVar.f1714d) {
                return;
            }
            try {
                String str = hVar.f1712b;
                String str2 = hVar.f1713c;
                if (str2 == null) {
                    str2 = "";
                }
                jSONObject.put(str, str2);
            } catch (JSONException e3) {
                Log.w("AutoReg_Pairs", "failed to get params", e3);
            }
        }
        j1.c.i("Params: " + jSONObject);
        String string = jSONObject.toString();
        z.f fVar = this.f1718e;
        fVar.getClass();
        new j(0, fVar, string).start();
    }
}
