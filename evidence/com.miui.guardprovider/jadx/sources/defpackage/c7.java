package defpackage;

import com.miui.guardprovider.aiguard.AiGuardService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c7 implements Consumer {
    public final /* synthetic */ int a;
    public /* synthetic */ AiGuardService b;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) throws IOException {
        int i = this.a;
        AiGuardService aiGuardService = this.b;
        byte[] bArr = (byte[]) obj;
        switch (i) {
            case 0:
                int i2 = AiGuardService.o;
                bArr.getClass();
                pj0 pj0VarF = aiGuardService.f();
                pj0VarF.getClass();
                Files.write(pj0VarF.a, bArr, (OpenOption[]) Arrays.copyOf(new OpenOption[]{StandardOpenOption.WRITE, StandardOpenOption.CREATE}, 2));
                break;
            default:
                int i3 = AiGuardService.o;
                pj0 pj0VarF2 = aiGuardService.f();
                bArr.getClass();
                pj0VarF2.getClass();
                Files.write(pj0VarF2.a, bArr, (OpenOption[]) Arrays.copyOf(new OpenOption[]{StandardOpenOption.WRITE, StandardOpenOption.CREATE}, 2));
                break;
        }
    }
}
