package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SalesReturnRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0006H\u00c6\u0003J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0016J\t\u0010 \u001a\u00020\u000bH\u00c6\u0003JV\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\n\u001a\u00020\u000bH\u00c6\u0001\u00a2\u0006\u0002\u0010\"J\u0013\u0010#\u001a\u00020\u000b2\b\u0010$\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010%\u001a\u00020&H\u00d6\u0001J\t\u0010'\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0015\u0010\t\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006("}, d2 = {"Lcom/hazem/dailystore/data/SalesReturnResult;", "", "returnId", "", "returnNumber", "totalUsdCents", "", "creditReductionUsdCents", "cashRefundUsdCents", "returnedCogsUsdCents", "replayed", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;JJJLjava/lang/Long;Z)V", "getReturnId", "()Ljava/lang/String;", "getReturnNumber", "getTotalUsdCents", "()J", "getCreditReductionUsdCents", "getCashRefundUsdCents", "getReturnedCogsUsdCents", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getReplayed", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;Ljava/lang/String;JJJLjava/lang/Long;Z)Lcom/hazem/dailystore/data/SalesReturnResult;", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class SalesReturnResult {
    private final long cashRefundUsdCents;
    private final long creditReductionUsdCents;
    private final boolean replayed;
    private final String returnId;
    private final String returnNumber;
    private final Long returnedCogsUsdCents;
    private final long totalUsdCents;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getReturnId() {
        return this.returnId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getReturnNumber() {
        return this.returnNumber;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getTotalUsdCents() {
        return this.totalUsdCents;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getCreditReductionUsdCents() {
        return this.creditReductionUsdCents;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getCashRefundUsdCents() {
        return this.cashRefundUsdCents;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Long getReturnedCogsUsdCents() {
        return this.returnedCogsUsdCents;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getReplayed() {
        return this.replayed;
    }

    public final SalesReturnResult copy(String returnId, String returnNumber, long totalUsdCents, long creditReductionUsdCents, long cashRefundUsdCents, Long returnedCogsUsdCents, boolean replayed) {
        Intrinsics.checkNotNullParameter(returnId, "returnId");
        Intrinsics.checkNotNullParameter(returnNumber, "returnNumber");
        return new SalesReturnResult(returnId, returnNumber, totalUsdCents, creditReductionUsdCents, cashRefundUsdCents, returnedCogsUsdCents, replayed);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SalesReturnResult)) {
            return false;
        }
        SalesReturnResult salesReturnResult = (SalesReturnResult) other;
        return Intrinsics.areEqual(this.returnId, salesReturnResult.returnId) && Intrinsics.areEqual(this.returnNumber, salesReturnResult.returnNumber) && this.totalUsdCents == salesReturnResult.totalUsdCents && this.creditReductionUsdCents == salesReturnResult.creditReductionUsdCents && this.cashRefundUsdCents == salesReturnResult.cashRefundUsdCents && Intrinsics.areEqual(this.returnedCogsUsdCents, salesReturnResult.returnedCogsUsdCents) && this.replayed == salesReturnResult.replayed;
    }

    public int hashCode() {
        int iHashCode = ((((((((this.returnId.hashCode() * 31) + this.returnNumber.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.totalUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.creditReductionUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.cashRefundUsdCents)) * 31;
        Long l = this.returnedCogsUsdCents;
        return ((iHashCode + (l == null ? 0 : l.hashCode())) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.replayed);
    }

    public String toString() {
        return "SalesReturnResult(returnId=" + this.returnId + ", returnNumber=" + this.returnNumber + ", totalUsdCents=" + this.totalUsdCents + ", creditReductionUsdCents=" + this.creditReductionUsdCents + ", cashRefundUsdCents=" + this.cashRefundUsdCents + ", returnedCogsUsdCents=" + this.returnedCogsUsdCents + ", replayed=" + this.replayed + ")";
    }

    public SalesReturnResult(String returnId, String returnNumber, long j, long j2, long j3, Long l, boolean z) {
        Intrinsics.checkNotNullParameter(returnId, "returnId");
        Intrinsics.checkNotNullParameter(returnNumber, "returnNumber");
        this.returnId = returnId;
        this.returnNumber = returnNumber;
        this.totalUsdCents = j;
        this.creditReductionUsdCents = j2;
        this.cashRefundUsdCents = j3;
        this.returnedCogsUsdCents = l;
        this.replayed = z;
    }

    public final String getReturnId() {
        return this.returnId;
    }

    public final String getReturnNumber() {
        return this.returnNumber;
    }

    public final long getTotalUsdCents() {
        return this.totalUsdCents;
    }

    public final long getCashRefundUsdCents() {
        return this.cashRefundUsdCents;
    }

    public final long getCreditReductionUsdCents() {
        return this.creditReductionUsdCents;
    }

    public final boolean getReplayed() {
        return this.replayed;
    }

    public final Long getReturnedCogsUsdCents() {
        return this.returnedCogsUsdCents;
    }
}
