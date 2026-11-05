package com.xiaomi.trustservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public interface IMiTrustService extends IInterface {
    public static final String DESCRIPTOR = "com.xiaomi.trustservice.IMiTrustService";

    public static class Default implements IMiTrustService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaomi.trustservice.IMiTrustService
        public String getTrustStatus(String str, byte[] bArr, String str2) {
            return null;
        }

        @Override // com.xiaomi.trustservice.IMiTrustService
        public int getVersion() {
            return 0;
        }
    }

    public static abstract class Stub extends Binder implements IMiTrustService {
        static final int TRANSACTION_getTrustStatus = 2;
        static final int TRANSACTION_getVersion = 1;

        public Stub() {
            attachInterface(this, IMiTrustService.DESCRIPTOR);
        }

        public static IMiTrustService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(IMiTrustService.DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMiTrustService)) {
                return (IMiTrustService) iInterfaceQueryLocalInterface;
            }
            h hVar = new h();
            hVar.a = iBinder;
            return hVar;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IMiTrustService.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IMiTrustService.DESCRIPTOR);
                return true;
            }
            if (i2 == 1) {
                int version = getVersion();
                parcel2.writeNoException();
                parcel2.writeInt(version);
            } else {
                if (i2 != 2) {
                    return super.onTransact(i2, parcel, parcel2, i3);
                }
                String trustStatus = getTrustStatus(parcel.readString(), parcel.createByteArray(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(trustStatus);
            }
            return true;
        }
    }

    String getTrustStatus(String str, byte[] bArr, String str2);

    int getVersion();
}
