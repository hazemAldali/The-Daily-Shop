package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;

/* JADX INFO: compiled from: PurchaseReturnRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J1\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n\u00a8\u0006\u001a"}, d2 = {"Lcom/hazem/dailystore/data/PurchaseReturnPreview;", "", "totalUsdCents", "", "totalSypMinor", "supplierCreditReductionSypMinor", "supplierCashRefundSypMinor", "<init>", "(JJJJ)V", "getTotalUsdCents", "()J", "getTotalSypMinor", "getSupplierCreditReductionSypMinor", "getSupplierCashRefundSypMinor", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class PurchaseReturnPreview {
    private final long supplierCashRefundSypMinor;
    private final long supplierCreditReductionSypMinor;
    private final long totalSypMinor;
    private final long totalUsdCents;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getTotalUsdCents() {
        return this.totalUsdCents;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getTotalSypMinor() {
        return this.totalSypMinor;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getSupplierCreditReductionSypMinor() {
        return this.supplierCreditReductionSypMinor;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getSupplierCashRefundSypMinor() {
        return this.supplierCashRefundSypMinor;
    }

    public final PurchaseReturnPreview copy(long totalUsdCents, long totalSypMinor, long supplierCreditReductionSypMinor, long supplierCashRefundSypMinor) {
        return new PurchaseReturnPreview(totalUsdCents, totalSypMinor, supplierCreditReductionSypMinor, supplierCashRefundSypMinor);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PurchaseReturnPreview)) {
            return false;
        }
        PurchaseReturnPreview purchaseReturnPreview = (PurchaseReturnPreview) other;
        return this.totalUsdCents == purchaseReturnPreview.totalUsdCents && this.totalSypMinor == purchaseReturnPreview.totalSypMinor && this.supplierCreditReductionSypMinor == purchaseReturnPreview.supplierCreditReductionSypMinor && this.supplierCashRefundSypMinor == purchaseReturnPreview.supplierCashRefundSypMinor;
    }

    public int hashCode() {
        return (((((MainActivity$$ExternalSyntheticBackport0.m(this.totalUsdCents) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.totalSypMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.supplierCreditReductionSypMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.supplierCashRefundSypMinor);
    }

    public String toString() {
        return "PurchaseReturnPreview(totalUsdCents=" + this.totalUsdCents + ", totalSypMinor=" + this.totalSypMinor + ", supplierCreditReductionSypMinor=" + this.supplierCreditReductionSypMinor + ", supplierCashRefundSypMinor=" + this.supplierCashRefundSypMinor + ")";
    }

    public PurchaseReturnPreview(long j, long j2, long j3, long j4) {
        this.totalUsdCents = j;
        this.totalSypMinor = j2;
        this.supplierCreditReductionSypMinor = j3;
        this.supplierCashRefundSypMinor = j4;
    }

    public final long getTotalSypMinor() {
        return this.totalSypMinor;
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
}
