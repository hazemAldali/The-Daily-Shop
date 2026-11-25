package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;

/* JADX INFO: compiled from: SalesReturnRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J1\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n\u00a8\u0006\u001a"}, d2 = {"Lcom/hazem/dailystore/data/SalesReturnPreview;", "", "totalUsdCents", "", "creditReductionUsdCents", "cashRefundUsdCents", "cashRefundSypMinor", "<init>", "(JJJJ)V", "getTotalUsdCents", "()J", "getCreditReductionUsdCents", "getCashRefundUsdCents", "getCashRefundSypMinor", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class SalesReturnPreview {
    private final long cashRefundSypMinor;
    private final long cashRefundUsdCents;
    private final long creditReductionUsdCents;
    private final long totalUsdCents;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getTotalUsdCents() {
        return this.totalUsdCents;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getCreditReductionUsdCents() {
        return this.creditReductionUsdCents;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getCashRefundUsdCents() {
        return this.cashRefundUsdCents;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getCashRefundSypMinor() {
        return this.cashRefundSypMinor;
    }

    public final SalesReturnPreview copy(long totalUsdCents, long creditReductionUsdCents, long cashRefundUsdCents, long cashRefundSypMinor) {
        return new SalesReturnPreview(totalUsdCents, creditReductionUsdCents, cashRefundUsdCents, cashRefundSypMinor);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SalesReturnPreview)) {
            return false;
        }
        SalesReturnPreview salesReturnPreview = (SalesReturnPreview) other;
        return this.totalUsdCents == salesReturnPreview.totalUsdCents && this.creditReductionUsdCents == salesReturnPreview.creditReductionUsdCents && this.cashRefundUsdCents == salesReturnPreview.cashRefundUsdCents && this.cashRefundSypMinor == salesReturnPreview.cashRefundSypMinor;
    }

    public int hashCode() {
        return (((((MainActivity$$ExternalSyntheticBackport0.m(this.totalUsdCents) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.creditReductionUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.cashRefundUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.cashRefundSypMinor);
    }

    public String toString() {
        return "SalesReturnPreview(totalUsdCents=" + this.totalUsdCents + ", creditReductionUsdCents=" + this.creditReductionUsdCents + ", cashRefundUsdCents=" + this.cashRefundUsdCents + ", cashRefundSypMinor=" + this.cashRefundSypMinor + ")";
    }

    public SalesReturnPreview(long j, long j2, long j3, long j4) {
        this.totalUsdCents = j;
        this.creditReductionUsdCents = j2;
        this.cashRefundUsdCents = j3;
        this.cashRefundSypMinor = j4;
    }

    public final long getCreditReductionUsdCents() {
        return this.creditReductionUsdCents;
    }

    public final long getTotalUsdCents() {
        return this.totalUsdCents;
    }

    public final long getCashRefundSypMinor() {
        return this.cashRefundSypMinor;
    }

    public final long getCashRefundUsdCents() {
        return this.cashRefundUsdCents;
    }
}
