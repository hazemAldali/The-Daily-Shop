package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomerPaymentRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0005\u00a2\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0005H\u00c6\u0003JE\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u001d\u001a\u00020\t2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001J\t\u0010!\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010\u00a8\u0006\""}, d2 = {"Lcom/hazem/dailystore/data/CustomerPaymentResult;", "", "paymentId", "", "paidUsdCents", "", "outstandingUsdCents", "paymentStatus", "replayed", "", "outstandingSypMinor", "<init>", "(Ljava/lang/String;JJLjava/lang/String;ZJ)V", "getPaymentId", "()Ljava/lang/String;", "getPaidUsdCents", "()J", "getOutstandingUsdCents", "getPaymentStatus", "getReplayed", "()Z", "getOutstandingSypMinor", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class CustomerPaymentResult {
    private final long outstandingSypMinor;
    private final long outstandingUsdCents;
    private final long paidUsdCents;
    private final String paymentId;
    private final String paymentStatus;
    private final boolean replayed;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPaymentId() {
        return this.paymentId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getPaidUsdCents() {
        return this.paidUsdCents;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getOutstandingUsdCents() {
        return this.outstandingUsdCents;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getPaymentStatus() {
        return this.paymentStatus;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getReplayed() {
        return this.replayed;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getOutstandingSypMinor() {
        return this.outstandingSypMinor;
    }

    public final CustomerPaymentResult copy(String paymentId, long paidUsdCents, long outstandingUsdCents, String paymentStatus, boolean replayed, long outstandingSypMinor) {
        Intrinsics.checkNotNullParameter(paymentId, "paymentId");
        Intrinsics.checkNotNullParameter(paymentStatus, "paymentStatus");
        return new CustomerPaymentResult(paymentId, paidUsdCents, outstandingUsdCents, paymentStatus, replayed, outstandingSypMinor);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomerPaymentResult)) {
            return false;
        }
        CustomerPaymentResult customerPaymentResult = (CustomerPaymentResult) other;
        return Intrinsics.areEqual(this.paymentId, customerPaymentResult.paymentId) && this.paidUsdCents == customerPaymentResult.paidUsdCents && this.outstandingUsdCents == customerPaymentResult.outstandingUsdCents && Intrinsics.areEqual(this.paymentStatus, customerPaymentResult.paymentStatus) && this.replayed == customerPaymentResult.replayed && this.outstandingSypMinor == customerPaymentResult.outstandingSypMinor;
    }

    public int hashCode() {
        return (((((((((this.paymentId.hashCode() * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.paidUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.outstandingUsdCents)) * 31) + this.paymentStatus.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.replayed)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.outstandingSypMinor);
    }

    public String toString() {
        return "CustomerPaymentResult(paymentId=" + this.paymentId + ", paidUsdCents=" + this.paidUsdCents + ", outstandingUsdCents=" + this.outstandingUsdCents + ", paymentStatus=" + this.paymentStatus + ", replayed=" + this.replayed + ", outstandingSypMinor=" + this.outstandingSypMinor + ")";
    }

    public CustomerPaymentResult(String paymentId, long j, long j2, String paymentStatus, boolean z, long j3) {
        Intrinsics.checkNotNullParameter(paymentId, "paymentId");
        Intrinsics.checkNotNullParameter(paymentStatus, "paymentStatus");
        this.paymentId = paymentId;
        this.paidUsdCents = j;
        this.outstandingUsdCents = j2;
        this.paymentStatus = paymentStatus;
        this.replayed = z;
        this.outstandingSypMinor = j3;
    }

    public final long getOutstandingUsdCents() {
        return this.outstandingUsdCents;
    }

    public final long getPaidUsdCents() {
        return this.paidUsdCents;
    }

    public final String getPaymentId() {
        return this.paymentId;
    }

    public final long getOutstandingSypMinor() {
        return this.outstandingSypMinor;
    }

    public final String getPaymentStatus() {
        return this.paymentStatus;
    }

    public final boolean getReplayed() {
        return this.replayed;
    }
}
