package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PurchasePaymentRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b&\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Bu\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\b\u0012\u0006\u0010\u000e\u001a\u00020\b\u0012\u0006\u0010\u000f\u001a\u00020\b\u0012\u0006\u0010\u0010\u001a\u00020\f\u0012\b\b\u0002\u0010\u0011\u001a\u00020\b\u00a2\u0006\u0004\b\u0012\u0010\u0013J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\bH\u00c6\u0003J\t\u0010)\u001a\u00020\bH\u00c6\u0003J\t\u0010*\u001a\u00020\bH\u00c6\u0003J\t\u0010+\u001a\u00020\fH\u00c6\u0003J\t\u0010,\u001a\u00020\bH\u00c6\u0003J\t\u0010-\u001a\u00020\bH\u00c6\u0003J\t\u0010.\u001a\u00020\bH\u00c6\u0003J\t\u0010/\u001a\u00020\fH\u00c6\u0003J\t\u00100\u001a\u00020\bH\u00c6\u0003J\u008f\u0001\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\f2\b\b\u0002\u0010\u0011\u001a\u00020\bH\u00c6\u0001J\u0013\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00105\u001a\u00020\fH\u00d6\u0001J\t\u00106\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\t\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\n\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\r\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001aR\u0011\u0010\u000e\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u0011\u0010\u000f\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u0011\u0010\u0010\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001eR\u0011\u0010\u0011\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001a\u00a8\u00067"}, d2 = {"Lcom/hazem/dailystore/data/SupplierFinancialSummary;", "", "id", "", "name", "phone", "address", "totalPurchaseUsdCents", "", "paidUsdCents", "outstandingUsdCents", "unpaidInvoiceCount", "", "totalPurchaseSypMinor", "paidSypMinor", "outstandingSypMinor", "unpricedInvoiceCount", "supplierReturnCreditsSypMinor", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJJIJJJIJ)V", "getId", "()Ljava/lang/String;", "getName", "getPhone", "getAddress", "getTotalPurchaseUsdCents", "()J", "getPaidUsdCents", "getOutstandingUsdCents", "getUnpaidInvoiceCount", "()I", "getTotalPurchaseSypMinor", "getPaidSypMinor", "getOutstandingSypMinor", "getUnpricedInvoiceCount", "getSupplierReturnCreditsSypMinor", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class SupplierFinancialSummary {
    private final String address;
    private final String id;
    private final String name;
    private final long outstandingSypMinor;
    private final long outstandingUsdCents;
    private final long paidSypMinor;
    private final long paidUsdCents;
    private final String phone;
    private final long supplierReturnCreditsSypMinor;
    private final long totalPurchaseSypMinor;
    private final long totalPurchaseUsdCents;
    private final int unpaidInvoiceCount;
    private final int unpricedInvoiceCount;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final long getPaidSypMinor() {
        return this.paidSypMinor;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final long getOutstandingSypMinor() {
        return this.outstandingSypMinor;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final int getUnpricedInvoiceCount() {
        return this.unpricedInvoiceCount;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final long getSupplierReturnCreditsSypMinor() {
        return this.supplierReturnCreditsSypMinor;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPhone() {
        return this.phone;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getAddress() {
        return this.address;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getTotalPurchaseUsdCents() {
        return this.totalPurchaseUsdCents;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getPaidUsdCents() {
        return this.paidUsdCents;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final long getOutstandingUsdCents() {
        return this.outstandingUsdCents;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getUnpaidInvoiceCount() {
        return this.unpaidInvoiceCount;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final long getTotalPurchaseSypMinor() {
        return this.totalPurchaseSypMinor;
    }

    public final SupplierFinancialSummary copy(String id, String name, String phone, String address, long totalPurchaseUsdCents, long paidUsdCents, long outstandingUsdCents, int unpaidInvoiceCount, long totalPurchaseSypMinor, long paidSypMinor, long outstandingSypMinor, int unpricedInvoiceCount, long supplierReturnCreditsSypMinor) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        return new SupplierFinancialSummary(id, name, phone, address, totalPurchaseUsdCents, paidUsdCents, outstandingUsdCents, unpaidInvoiceCount, totalPurchaseSypMinor, paidSypMinor, outstandingSypMinor, unpricedInvoiceCount, supplierReturnCreditsSypMinor);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SupplierFinancialSummary)) {
            return false;
        }
        SupplierFinancialSummary supplierFinancialSummary = (SupplierFinancialSummary) other;
        return Intrinsics.areEqual(this.id, supplierFinancialSummary.id) && Intrinsics.areEqual(this.name, supplierFinancialSummary.name) && Intrinsics.areEqual(this.phone, supplierFinancialSummary.phone) && Intrinsics.areEqual(this.address, supplierFinancialSummary.address) && this.totalPurchaseUsdCents == supplierFinancialSummary.totalPurchaseUsdCents && this.paidUsdCents == supplierFinancialSummary.paidUsdCents && this.outstandingUsdCents == supplierFinancialSummary.outstandingUsdCents && this.unpaidInvoiceCount == supplierFinancialSummary.unpaidInvoiceCount && this.totalPurchaseSypMinor == supplierFinancialSummary.totalPurchaseSypMinor && this.paidSypMinor == supplierFinancialSummary.paidSypMinor && this.outstandingSypMinor == supplierFinancialSummary.outstandingSypMinor && this.unpricedInvoiceCount == supplierFinancialSummary.unpricedInvoiceCount && this.supplierReturnCreditsSypMinor == supplierFinancialSummary.supplierReturnCreditsSypMinor;
    }

    public int hashCode() {
        int iHashCode = ((this.id.hashCode() * 31) + this.name.hashCode()) * 31;
        String str = this.phone;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.address;
        return ((((((((((((((((((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.totalPurchaseUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.paidUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.outstandingUsdCents)) * 31) + this.unpaidInvoiceCount) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.totalPurchaseSypMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.paidSypMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.outstandingSypMinor)) * 31) + this.unpricedInvoiceCount) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.supplierReturnCreditsSypMinor);
    }

    public String toString() {
        return "SupplierFinancialSummary(id=" + this.id + ", name=" + this.name + ", phone=" + this.phone + ", address=" + this.address + ", totalPurchaseUsdCents=" + this.totalPurchaseUsdCents + ", paidUsdCents=" + this.paidUsdCents + ", outstandingUsdCents=" + this.outstandingUsdCents + ", unpaidInvoiceCount=" + this.unpaidInvoiceCount + ", totalPurchaseSypMinor=" + this.totalPurchaseSypMinor + ", paidSypMinor=" + this.paidSypMinor + ", outstandingSypMinor=" + this.outstandingSypMinor + ", unpricedInvoiceCount=" + this.unpricedInvoiceCount + ", supplierReturnCreditsSypMinor=" + this.supplierReturnCreditsSypMinor + ")";
    }

    public SupplierFinancialSummary(String id, String name, String str, String str2, long j, long j2, long j3, int i, long j4, long j5, long j6, int i2, long j7) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        this.id = id;
        this.name = name;
        this.phone = str;
        this.address = str2;
        this.totalPurchaseUsdCents = j;
        this.paidUsdCents = j2;
        this.outstandingUsdCents = j3;
        this.unpaidInvoiceCount = i;
        this.totalPurchaseSypMinor = j4;
        this.paidSypMinor = j5;
        this.outstandingSypMinor = j6;
        this.unpricedInvoiceCount = i2;
        this.supplierReturnCreditsSypMinor = j7;
    }

    public /* synthetic */ SupplierFinancialSummary(String str, String str2, String str3, String str4, long j, long j2, long j3, int i, long j4, long j5, long j6, int i2, long j7, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, j, j2, j3, i, j4, j5, j6, i2, (i3 & 4096) != 0 ? 0L : j7);
    }

    public final String getAddress() {
        return this.address;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPhone() {
        return this.phone;
    }

    public final long getPaidUsdCents() {
        return this.paidUsdCents;
    }

    public final long getTotalPurchaseUsdCents() {
        return this.totalPurchaseUsdCents;
    }

    public final long getOutstandingUsdCents() {
        return this.outstandingUsdCents;
    }

    public final int getUnpaidInvoiceCount() {
        return this.unpaidInvoiceCount;
    }

    public final long getOutstandingSypMinor() {
        return this.outstandingSypMinor;
    }

    public final long getPaidSypMinor() {
        return this.paidSypMinor;
    }

    public final long getTotalPurchaseSypMinor() {
        return this.totalPurchaseSypMinor;
    }

    public final long getSupplierReturnCreditsSypMinor() {
        return this.supplierReturnCreditsSypMinor;
    }

    public final int getUnpricedInvoiceCount() {
        return this.unpricedInvoiceCount;
    }
}
