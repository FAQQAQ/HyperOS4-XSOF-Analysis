package defpackage;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class qq {
    public long a;
    public a7 b;
    public ArrayList c;
    public long d;

    public final void a(String str) throws Exception {
        str.getClass();
        ArrayList arrayList = this.c;
        arrayList.add(str);
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.d > this.a) {
            a7 a7Var = this.b;
            String strLineSeparator = System.lineSeparator();
            strLineSeparator.getClass();
            a7Var.z(zx.Y(arrayList, strLineSeparator, null, null, null, 62));
            this.d = jCurrentTimeMillis;
        }
    }
}
