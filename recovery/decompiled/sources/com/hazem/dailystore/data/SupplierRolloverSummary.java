package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: DailyBalanceRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0007H\u00c6\u0003J\t\u0010 \u001a\u00020\u000bH\u00c6\u0003JO\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000bH\u00c6\u0001J\u0013\u0010\"\u001a\u00020\u000b2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010$\u001a\u00020%H\u00d6\u0001J\t\u0010&\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u00078F\u00a2\u0006\u0006\u001a\u0004\b\u0019\u0010\u0013\u00a8\u0006'"}, d2 = {"Lcom/hazem/dailystore/data/SupplierRolloverSummary;", "", "operatingDate", "", "accountId", "accountName", "allocatedMinor", "", "supplierPaymentsMinor", "personalWithdrawalsMinor", "pending", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJJZ)V", "getOperatingDate", "()Ljava/lang/String;", "getAccountId", "getAccountName", "getAllocatedMinor", "()J", "getSupplierPaymentsMinor", "getPersonalWithdrawalsMinor", "getPending", "()Z", "availableMinor", "getAvailableMinor", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class SupplierRolloverSummary {
    private final String accountId;
    private final String accountName;
    private final long allocatedMinor;
    private final String operatingDate;
    private final boolean pending;
    private final long personalWithdrawalsMinor;
    private final long supplierPaymentsMinor;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getOperatingDate() {
        return this.operatingDate;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getAccountId() {
        return this.accountId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getAccountName() {
        return this.accountName;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getAllocatedMinor() {
        return this.allocatedMinor;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getSupplierPaymentsMinor() {
        return this.supplierPaymentsMinor;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getPersonalWithdrawalsMinor() {
        return this.personalWithdrawalsMinor;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getPending() {
        return this.pending;
    }

    public final SupplierRolloverSummary copy(String operatingDate, String accountId, String accountName, long allocatedMinor, long supplierPaymentsMinor, long personalWithdrawalsMinor, boolean pending) {
        Intrinsics.checkNotNullParameter(operatingDate, "operatingDate");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Intrinsics.checkNotNullParameter(accountName, "accountName");
        return new SupplierRolloverSummary(operatingDate, accountId, accountName, allocatedMinor, supplierPaymentsMinor, personalWithdrawalsMinor, pending);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SupplierRolloverSummary)) {
            return false;
        }
        SupplierRolloverSummary supplierRolloverSummary = (SupplierRolloverSummary) other;
        return Intrinsics.areEqual(this.operatingDate, supplierRolloverSummary.operatingDate) && Intrinsics.areEqual(this.accountId, supplierRolloverSummary.accountId) && Intrinsics.areEqual(this.accountName, supplierRolloverSummary.accountName) && this.allocatedMinor == supplierRolloverSummary.allocatedMinor && this.supplierPaymentsMinor == supplierRolloverSummary.supplierPaymentsMinor && this.personalWithdrawalsMinor == supplierRolloverSummary.personalWithdrawalsMinor && this.pending == supplierRolloverSummary.pending;
    }

    public int hashCode() {
        return (((((((((((this.operatingDate.hashCode() * 31) + this.accountId.hashCode()) * 31) + this.accountName.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.allocatedMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.supplierPaymentsMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.personalWithdrawalsMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.pending);
    }

    public String toString() {
        return "SupplierRolloverSummary(operatingDate=" + this.operatingDate + ", accountId=" + this.accountId + ", accountName=" + this.accountName + ", allocatedMinor=" + this.allocatedMinor + ", supplierPaymentsMinor=" + this.supplierPaymentsMinor + ", personalWithdrawalsMinor=" + this.personalWithdrawalsMinor + ", pending=" + this.pending + ")";
    }

    public SupplierRolloverSummary(String operatingDate, String accountId, String accountName, long j, long j2, long j3, boolean z) {
        Intrinsics.checkNotNullParameter(operatingDate, "operatingDate");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Intrinsics.checkNotNullParameter(accountName, "accountName");
        this.operatingDate = operatingDate;
        this.accountId = accountId;
        this.accountName = accountName;
        this.allocatedMinor = j;
        this.supplierPaymentsMinor = j2;
        this.personalWithdrawalsMinor = j3;
        this.pending = z;
    }

    public /* synthetic */ SupplierRolloverSummary(String str, String str2, String str3, long j, long j2, long j3, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, j, j2, j3, (i & 64) != 0 ? false : z);
    }

    public final String getAccountId() {
        return this.accountId;
    }

    public final String getAccountName() {
        return this.accountName;
    }

    public final long getAllocatedMinor() {
        return this.allocatedMinor;
    }

    public final String getOperatingDate() {
        return this.operatingDate;
    }

    public final boolean getPending() {
        return this.pending;
    }

    public final long getPersonalWithdrawalsMinor() {
        return this.personalWithdrawalsMinor;
    }

    public final long getSupplierPaymentsMinor() {
        return this.supplierPaymentsMinor;
    }

    public final long getAvailableMinor() {
        return RangesKt.coerceAtLeast((this.allocatedMinor - this.supplierPaymentsMinor) - this.personalWithdrawalsMinor, 0L);
    }
}
