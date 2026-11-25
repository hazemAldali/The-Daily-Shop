package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomerPaymentRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\bH\u00c6\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001J\t\u0010\u001d\u001a\u00020\bH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u001e"}, d2 = {"Lcom/hazem/dailystore/data/CustomerCreditRevaluationRecord;", "", "previousSypMinor", "", "newSypMinor", "appliedRateMilli", "outstandingUsdCents", "createdAt", "", "<init>", "(JJJJLjava/lang/String;)V", "getPreviousSypMinor", "()J", "getNewSypMinor", "getAppliedRateMilli", "getOutstandingUsdCents", "getCreatedAt", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class CustomerCreditRevaluationRecord {
    private final long appliedRateMilli;
    private final String createdAt;
    private final long newSypMinor;
    private final long outstandingUsdCents;
    private final long previousSypMinor;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getPreviousSypMinor() {
        return this.previousSypMinor;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getNewSypMinor() {
        return this.newSypMinor;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getAppliedRateMilli() {
        return this.appliedRateMilli;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getOutstandingUsdCents() {
        return this.outstandingUsdCents;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final CustomerCreditRevaluationRecord copy(long previousSypMinor, long newSypMinor, long appliedRateMilli, long outstandingUsdCents, String createdAt) {
        Intrinsics.checkNotNullParameter(createdAt, "createdAt");
        return new CustomerCreditRevaluationRecord(previousSypMinor, newSypMinor, appliedRateMilli, outstandingUsdCents, createdAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomerCreditRevaluationRecord)) {
            return false;
        }
        CustomerCreditRevaluationRecord customerCreditRevaluationRecord = (CustomerCreditRevaluationRecord) other;
        return this.previousSypMinor == customerCreditRevaluationRecord.previousSypMinor && this.newSypMinor == customerCreditRevaluationRecord.newSypMinor && this.appliedRateMilli == customerCreditRevaluationRecord.appliedRateMilli && this.outstandingUsdCents == customerCreditRevaluationRecord.outstandingUsdCents && Intrinsics.areEqual(this.createdAt, customerCreditRevaluationRecord.createdAt);
    }

    public int hashCode() {
        return (((((((MainActivity$$ExternalSyntheticBackport0.m(this.previousSypMinor) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.newSypMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.appliedRateMilli)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.outstandingUsdCents)) * 31) + this.createdAt.hashCode();
    }

    public String toString() {
        return "CustomerCreditRevaluationRecord(previousSypMinor=" + this.previousSypMinor + ", newSypMinor=" + this.newSypMinor + ", appliedRateMilli=" + this.appliedRateMilli + ", outstandingUsdCents=" + this.outstandingUsdCents + ", createdAt=" + this.createdAt + ")";
    }

    public CustomerCreditRevaluationRecord(long j, long j2, long j3, long j4, String createdAt) {
        Intrinsics.checkNotNullParameter(createdAt, "createdAt");
        this.previousSypMinor = j;
        this.newSypMinor = j2;
        this.appliedRateMilli = j3;
        this.outstandingUsdCents = j4;
        this.createdAt = createdAt;
    }

    public final long getNewSypMinor() {
        return this.newSypMinor;
    }

    public final long getPreviousSypMinor() {
        return this.previousSypMinor;
    }

    public final long getAppliedRateMilli() {
        return this.appliedRateMilli;
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final long getOutstandingUsdCents() {
        return this.outstandingUsdCents;
    }
}
