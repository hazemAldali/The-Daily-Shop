package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PurchaseReturnRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\nH\u00c6\u0003JE\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\nH\u00c6\u0001J\u0013\u0010\u001d\u001a\u00020\n2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001J\t\u0010!\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006\""}, d2 = {"Lcom/hazem/dailystore/data/PurchaseReturnResult;", "", "returnId", "", "returnNumber", "totalUsdCents", "", "supplierCreditReductionSypMinor", "supplierCashRefundSypMinor", "replayed", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;JJJZ)V", "getReturnId", "()Ljava/lang/String;", "getReturnNumber", "getTotalUsdCents", "()J", "getSupplierCreditReductionSypMinor", "getSupplierCashRefundSypMinor", "getReplayed", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class PurchaseReturnResult {
    private final boolean replayed;
    private final String returnId;
    private final String returnNumber;
    private final long supplierCashRefundSypMinor;
    private final long supplierCreditReductionSypMinor;
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
    public final long getSupplierCreditReductionSypMinor() {
        return this.supplierCreditReductionSypMinor;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getSupplierCashRefundSypMinor() {
        return this.supplierCashRefundSypMinor;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getReplayed() {
        return this.replayed;
    }

    public final PurchaseReturnResult copy(String returnId, String returnNumber, long totalUsdCents, long supplierCreditReductionSypMinor, long supplierCashRefundSypMinor, boolean replayed) {
        Intrinsics.checkNotNullParameter(returnId, "returnId");
        Intrinsics.checkNotNullParameter(returnNumber, "returnNumber");
        return new PurchaseReturnResult(returnId, returnNumber, totalUsdCents, supplierCreditReductionSypMinor, supplierCashRefundSypMinor, replayed);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PurchaseReturnResult)) {
            return false;
        }
        PurchaseReturnResult purchaseReturnResult = (PurchaseReturnResult) other;
        return Intrinsics.areEqual(this.returnId, purchaseReturnResult.returnId) && Intrinsics.areEqual(this.returnNumber, purchaseReturnResult.returnNumber) && this.totalUsdCents == purchaseReturnResult.totalUsdCents && this.supplierCreditReductionSypMinor == purchaseReturnResult.supplierCreditReductionSypMinor && this.supplierCashRefundSypMinor == purchaseReturnResult.supplierCashRefundSypMinor && this.replayed == purchaseReturnResult.replayed;
    }

    public int hashCode() {
        return (((((((((this.returnId.hashCode() * 31) + this.returnNumber.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.totalUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.supplierCreditReductionSypMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.supplierCashRefundSypMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.replayed);
    }

    public String toString() {
        return "PurchaseReturnResult(returnId=" + this.returnId + ", returnNumber=" + this.returnNumber + ", totalUsdCents=" + this.totalUsdCents + ", supplierCreditReductionSypMinor=" + this.supplierCreditReductionSypMinor + ", supplierCashRefundSypMinor=" + this.supplierCashRefundSypMinor + ", replayed=" + this.replayed + ")";
    }

    public PurchaseReturnResult(String returnId, String returnNumber, long j, long j2, long j3, boolean z) {
        Intrinsics.checkNotNullParameter(returnId, "returnId");
        Intrinsics.checkNotNullParameter(returnNumber, "returnNumber");
        this.returnId = returnId;
        this.returnNumber = returnNumber;
        this.totalUsdCents = j;
        this.supplierCreditReductionSypMinor = j2;
        this.supplierCashRefundSypMinor = j3;
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

    public final long getSupplierCashRefundSypMinor() {
        return this.supplierCashRefundSypMinor;
    }

    public final long getSupplierCreditReductionSypMinor() {
        return this.supplierCreditReductionSypMinor;
    }

    public final boolean getReplayed() {
        return this.replayed;
    }
}
