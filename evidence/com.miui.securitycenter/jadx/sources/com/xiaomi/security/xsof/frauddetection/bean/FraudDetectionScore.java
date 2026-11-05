package com.xiaomi.security.xsof.frauddetection.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.miui.riskyapp.model.RiskyAppDescriptionItem;
import kotlin.Metadata;
import kotlinx.android.parcel.Parcelize;
import lp.n;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes4.dex */
@Parcelize
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\b¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0010J\u0010\u0010\u0014\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\u0014\u0010\u000eJ\u001a\u0010\u0018\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015HÖ\u0003¢\u0006\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001a\u001a\u0004\b\u001b\u0010\u0010¨\u0006\u001c"}, d2 = {"Lcom/xiaomi/security/xsof/frauddetection/bean/FraudDetectionScore;", "Landroid/os/Parcelable;", "", "score", "<init>", "(Ljava/lang/String;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lwo/w;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "copy", "(Ljava/lang/String;)Lcom/xiaomi/security/xsof/frauddetection/bean/FraudDetectionScore;", "toString", "hashCode", "", RiskyAppDescriptionItem.Columns.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getScore", "core-service_cnRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class FraudDetectionScore implements Parcelable {

    @NotNull
    public static final Parcelable.Creator<FraudDetectionScore> CREATOR = new a();

    @NotNull
    private final String score;

    public static final class a implements Parcelable.Creator {
        /* JADX DEBUG: Method merged with bridge method: createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object; */
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final FraudDetectionScore createFromParcel(Parcel parcel) {
            n.f(parcel, "parcel");
            return new FraudDetectionScore(parcel.readString());
        }

        /* JADX DEBUG: Method merged with bridge method: newArray(I)[Ljava/lang/Object; */
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final FraudDetectionScore[] newArray(int i10) {
            return new FraudDetectionScore[i10];
        }
    }

    public FraudDetectionScore(@NotNull String str) {
        n.f(str, "score");
        this.score = str;
    }

    public static /* synthetic */ FraudDetectionScore copy$default(FraudDetectionScore fraudDetectionScore, String str, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = fraudDetectionScore.score;
        }
        return fraudDetectionScore.copy(str);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getScore() {
        return this.score;
    }

    @NotNull
    public final FraudDetectionScore copy(@NotNull String score) {
        n.f(score, "score");
        return new FraudDetectionScore(score);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof FraudDetectionScore) && n.b(this.score, ((FraudDetectionScore) other).score);
    }

    @NotNull
    public final String getScore() {
        return this.score;
    }

    public int hashCode() {
        return this.score.hashCode();
    }

    @NotNull
    public String toString() {
        return "FraudDetectionScore(score=" + this.score + ")";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NotNull Parcel dest, int flags) {
        n.f(dest, "dest");
        dest.writeString(this.score);
    }
}
