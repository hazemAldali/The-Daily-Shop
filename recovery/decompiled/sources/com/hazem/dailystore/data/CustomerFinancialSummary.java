package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomerPaymentRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0007\u00a2\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0007H\u00c6\u0003J\t\u0010 \u001a\u00020\u000bH\u00c6\u0003J\t\u0010!\u001a\u00020\u0007H\u00c6\u0003J[\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010&\u001a\u00020\u000bH\u00d6\u0001J\t\u0010'\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\t\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\f\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014\u00a8\u0006("}, d2 = {"Lcom/hazem/dailystore/data/CustomerFinancialSummary;", "", "id", "", "name", "phone", "totalCreditUsdCents", "", "paidUsdCents", "outstandingUsdCents", "unpaidInvoiceCount", "", "outstandingSypMinor", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJJIJ)V", "getId", "()Ljava/lang/String;", "getName", "getPhone", "getTotalCreditUsdCents", "()J", "getPaidUsdCents", "getOutstandingUsdCents", "getUnpaidInvoiceCount", "()I", "getOutstandingSypMinor", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class CustomerFinancialSummary {
    private final String id;
    private final String name;
    private final long outstandingSypMinor;
    private final long outstandingUsdCents;
    private final long paidUsdCents;
    private final String phone;
    private final long totalCreditUsdCents;
    private final int unpaidInvoiceCount;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
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
    public final long getTotalCreditUsdCents() {
        return this.totalCreditUsdCents;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getPaidUsdCents() {
        return this.paidUsdCents;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getOutstandingUsdCents() {
        return this.outstandingUsdCents;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getUnpaidInvoiceCount() {
        return this.unpaidInvoiceCount;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final long getOutstandingSypMinor() {
        return this.outstandingSypMinor;
    }

    public final CustomerFinancialSummary copy(String id, String name, String phone, long totalCreditUsdCents, long paidUsdCents, long outstandingUsdCents, int unpaidInvoiceCount, long outstandingSypMinor) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        return new CustomerFinancialSummary(id, name, phone, totalCreditUsdCents, paidUsdCents, outstandingUsdCents, unpaidInvoiceCount, outstandingSypMinor);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomerFinancialSummary)) {
            return false;
        }
        CustomerFinancialSummary customerFinancialSummary = (CustomerFinancialSummary) other;
        return Intrinsics.areEqual(this.id, customerFinancialSummary.id) && Intrinsics.areEqual(this.name, customerFinancialSummary.name) && Intrinsics.areEqual(this.phone, customerFinancialSummary.phone) && this.totalCreditUsdCents == customerFinancialSummary.totalCreditUsdCents && this.paidUsdCents == customerFinancialSummary.paidUsdCents && this.outstandingUsdCents == customerFinancialSummary.outstandingUsdCents && this.unpaidInvoiceCount == customerFinancialSummary.unpaidInvoiceCount && this.outstandingSypMinor == customerFinancialSummary.outstandingSypMinor;
    }

    public int hashCode() {
        int iHashCode = ((this.id.hashCode() * 31) + this.name.hashCode()) * 31;
        String str = this.phone;
        return ((((((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.totalCreditUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.paidUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.outstandingUsdCents)) * 31) + this.unpaidInvoiceCount) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.outstandingSypMinor);
    }

    public String toString() {
        return "CustomerFinancialSummary(id=" + this.id + ", name=" + this.name + ", phone=" + this.phone + ", totalCreditUsdCents=" + this.totalCreditUsdCents + ", paidUsdCents=" + this.paidUsdCents + ", outstandingUsdCents=" + this.outstandingUsdCents + ", unpaidInvoiceCount=" + this.unpaidInvoiceCount + ", outstandingSypMinor=" + this.outstandingSypMinor + ")";
    }

    public CustomerFinancialSummary(String id, String name, String str, long j, long j2, long j3, int i, long j4) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        this.id = id;
        this.name = name;
        this.phone = str;
        this.totalCreditUsdCents = j;
        this.paidUsdCents = j2;
        this.outstandingUsdCents = j3;
        this.unpaidInvoiceCount = i;
        this.outstandingSypMinor = j4;
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

    public final long getTotalCreditUsdCents() {
        return this.totalCreditUsdCents;
    }

    public final long getOutstandingSypMinor() {
        return this.outstandingSypMinor;
    }

    public final long getOutstandingUsdCents() {
        return this.outstandingUsdCents;
    }

    public final int getUnpaidInvoiceCount() {
        return this.unpaidInvoiceCount;
    }
}
