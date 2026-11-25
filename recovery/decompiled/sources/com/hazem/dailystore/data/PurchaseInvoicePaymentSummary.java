package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PurchasePaymentRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b-\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0085\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000e\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\t\u0012\u0006\u0010\u0010\u001a\u00020\t\u0012\u0006\u0010\u0011\u001a\u00020\t\u0012\b\b\u0002\u0010\u0012\u001a\u00020\t\u00a2\u0006\u0004\b\u0013\u0010\u0014J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003J\t\u0010'\u001a\u00020\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\u0003H\u00c6\u0003J\t\u0010*\u001a\u00020\u0003H\u00c6\u0003J\t\u0010+\u001a\u00020\tH\u00c6\u0003J\t\u0010,\u001a\u00020\tH\u00c6\u0003J\t\u0010-\u001a\u00020\tH\u00c6\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u00100\u001a\u00020\tH\u00c6\u0003J\t\u00101\u001a\u00020\tH\u00c6\u0003J\t\u00102\u001a\u00020\tH\u00c6\u0003J\t\u00103\u001a\u00020\tH\u00c6\u0003J\t\u00104\u001a\u00020\tH\u00c6\u0003J\u00a3\u0001\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\t2\b\b\u0002\u0010\u0011\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\tH\u00c6\u0001J\u0013\u00106\u001a\u0002072\b\u00108\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00109\u001a\u00020:H\u00d6\u0001J\t\u0010;\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\n\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0011\u0010\u000b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0016R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0016R\u0011\u0010\u000e\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001cR\u0011\u0010\u000f\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001cR\u0011\u0010\u0010\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001cR\u0011\u0010\u0011\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001cR\u0011\u0010\u0012\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001c\u00a8\u0006<"}, d2 = {"Lcom/hazem/dailystore/data/PurchaseInvoicePaymentSummary;", "", "id", "", "invoiceNumber", "date", "invoiceStatus", "paymentStatus", "totalUsdCents", "", "paidUsdCents", "outstandingUsdCents", "supplierId", "supplierName", "totalSypMinor", "paidSypMinor", "outstandingSypMinor", "invoiceRateMilli", "supplierReturnCreditSypMinor", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJJLjava/lang/String;Ljava/lang/String;JJJJJ)V", "getId", "()Ljava/lang/String;", "getInvoiceNumber", "getDate", "getInvoiceStatus", "getPaymentStatus", "getTotalUsdCents", "()J", "getPaidUsdCents", "getOutstandingUsdCents", "getSupplierId", "getSupplierName", "getTotalSypMinor", "getPaidSypMinor", "getOutstandingSypMinor", "getInvoiceRateMilli", "getSupplierReturnCreditSypMinor", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class PurchaseInvoicePaymentSummary {
    private final String date;
    private final String id;
    private final String invoiceNumber;
    private final long invoiceRateMilli;
    private final String invoiceStatus;
    private final long outstandingSypMinor;
    private final long outstandingUsdCents;
    private final long paidSypMinor;
    private final long paidUsdCents;
    private final String paymentStatus;
    private final String supplierId;
    private final String supplierName;
    private final long supplierReturnCreditSypMinor;
    private final long totalSypMinor;
    private final long totalUsdCents;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getSupplierName() {
        return this.supplierName;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final long getTotalSypMinor() {
        return this.totalSypMinor;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final long getPaidSypMinor() {
        return this.paidSypMinor;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final long getOutstandingSypMinor() {
        return this.outstandingSypMinor;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final long getInvoiceRateMilli() {
        return this.invoiceRateMilli;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final long getSupplierReturnCreditSypMinor() {
        return this.supplierReturnCreditSypMinor;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getInvoiceNumber() {
        return this.invoiceNumber;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDate() {
        return this.date;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getInvoiceStatus() {
        return this.invoiceStatus;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getPaymentStatus() {
        return this.paymentStatus;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getTotalUsdCents() {
        return this.totalUsdCents;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final long getPaidUsdCents() {
        return this.paidUsdCents;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final long getOutstandingUsdCents() {
        return this.outstandingUsdCents;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getSupplierId() {
        return this.supplierId;
    }

    public final PurchaseInvoicePaymentSummary copy(String id, String invoiceNumber, String date, String invoiceStatus, String paymentStatus, long totalUsdCents, long paidUsdCents, long outstandingUsdCents, String supplierId, String supplierName, long totalSypMinor, long paidSypMinor, long outstandingSypMinor, long invoiceRateMilli, long supplierReturnCreditSypMinor) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(invoiceNumber, "invoiceNumber");
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(invoiceStatus, "invoiceStatus");
        Intrinsics.checkNotNullParameter(paymentStatus, "paymentStatus");
        return new PurchaseInvoicePaymentSummary(id, invoiceNumber, date, invoiceStatus, paymentStatus, totalUsdCents, paidUsdCents, outstandingUsdCents, supplierId, supplierName, totalSypMinor, paidSypMinor, outstandingSypMinor, invoiceRateMilli, supplierReturnCreditSypMinor);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PurchaseInvoicePaymentSummary)) {
            return false;
        }
        PurchaseInvoicePaymentSummary purchaseInvoicePaymentSummary = (PurchaseInvoicePaymentSummary) other;
        return Intrinsics.areEqual(this.id, purchaseInvoicePaymentSummary.id) && Intrinsics.areEqual(this.invoiceNumber, purchaseInvoicePaymentSummary.invoiceNumber) && Intrinsics.areEqual(this.date, purchaseInvoicePaymentSummary.date) && Intrinsics.areEqual(this.invoiceStatus, purchaseInvoicePaymentSummary.invoiceStatus) && Intrinsics.areEqual(this.paymentStatus, purchaseInvoicePaymentSummary.paymentStatus) && this.totalUsdCents == purchaseInvoicePaymentSummary.totalUsdCents && this.paidUsdCents == purchaseInvoicePaymentSummary.paidUsdCents && this.outstandingUsdCents == purchaseInvoicePaymentSummary.outstandingUsdCents && Intrinsics.areEqual(this.supplierId, purchaseInvoicePaymentSummary.supplierId) && Intrinsics.areEqual(this.supplierName, purchaseInvoicePaymentSummary.supplierName) && this.totalSypMinor == purchaseInvoicePaymentSummary.totalSypMinor && this.paidSypMinor == purchaseInvoicePaymentSummary.paidSypMinor && this.outstandingSypMinor == purchaseInvoicePaymentSummary.outstandingSypMinor && this.invoiceRateMilli == purchaseInvoicePaymentSummary.invoiceRateMilli && this.supplierReturnCreditSypMinor == purchaseInvoicePaymentSummary.supplierReturnCreditSypMinor;
    }

    public int hashCode() {
        int iHashCode = ((((((((((((((this.id.hashCode() * 31) + this.invoiceNumber.hashCode()) * 31) + this.date.hashCode()) * 31) + this.invoiceStatus.hashCode()) * 31) + this.paymentStatus.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.totalUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.paidUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.outstandingUsdCents)) * 31;
        String str = this.supplierId;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.supplierName;
        return ((((((((((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.totalSypMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.paidSypMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.outstandingSypMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.invoiceRateMilli)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.supplierReturnCreditSypMinor);
    }

    public String toString() {
        return "PurchaseInvoicePaymentSummary(id=" + this.id + ", invoiceNumber=" + this.invoiceNumber + ", date=" + this.date + ", invoiceStatus=" + this.invoiceStatus + ", paymentStatus=" + this.paymentStatus + ", totalUsdCents=" + this.totalUsdCents + ", paidUsdCents=" + this.paidUsdCents + ", outstandingUsdCents=" + this.outstandingUsdCents + ", supplierId=" + this.supplierId + ", supplierName=" + this.supplierName + ", totalSypMinor=" + this.totalSypMinor + ", paidSypMinor=" + this.paidSypMinor + ", outstandingSypMinor=" + this.outstandingSypMinor + ", invoiceRateMilli=" + this.invoiceRateMilli + ", supplierReturnCreditSypMinor=" + this.supplierReturnCreditSypMinor + ")";
    }

    public PurchaseInvoicePaymentSummary(String id, String invoiceNumber, String date, String invoiceStatus, String paymentStatus, long j, long j2, long j3, String str, String str2, long j4, long j5, long j6, long j7, long j8) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(invoiceNumber, "invoiceNumber");
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(invoiceStatus, "invoiceStatus");
        Intrinsics.checkNotNullParameter(paymentStatus, "paymentStatus");
        this.id = id;
        this.invoiceNumber = invoiceNumber;
        this.date = date;
        this.invoiceStatus = invoiceStatus;
        this.paymentStatus = paymentStatus;
        this.totalUsdCents = j;
        this.paidUsdCents = j2;
        this.outstandingUsdCents = j3;
        this.supplierId = str;
        this.supplierName = str2;
        this.totalSypMinor = j4;
        this.paidSypMinor = j5;
        this.outstandingSypMinor = j6;
        this.invoiceRateMilli = j7;
        this.supplierReturnCreditSypMinor = j8;
    }

    public /* synthetic */ PurchaseInvoicePaymentSummary(String str, String str2, String str3, String str4, String str5, long j, long j2, long j3, String str6, String str7, long j4, long j5, long j6, long j7, long j8, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, j, j2, j3, str6, str7, j4, j5, j6, j7, (i & 16384) != 0 ? 0L : j8);
    }

    public final String getDate() {
        return this.date;
    }

    public final String getId() {
        return this.id;
    }

    public final String getInvoiceNumber() {
        return this.invoiceNumber;
    }

    public final String getInvoiceStatus() {
        return this.invoiceStatus;
    }

    public final long getPaidUsdCents() {
        return this.paidUsdCents;
    }

    public final String getPaymentStatus() {
        return this.paymentStatus;
    }

    public final long getTotalUsdCents() {
        return this.totalUsdCents;
    }

    public final long getOutstandingUsdCents() {
        return this.outstandingUsdCents;
    }

    public final String getSupplierId() {
        return this.supplierId;
    }

    public final String getSupplierName() {
        return this.supplierName;
    }

    public final long getInvoiceRateMilli() {
        return this.invoiceRateMilli;
    }

    public final long getOutstandingSypMinor() {
        return this.outstandingSypMinor;
    }

    public final long getPaidSypMinor() {
        return this.paidSypMinor;
    }

    public final long getTotalSypMinor() {
        return this.totalSypMinor;
    }

    public final long getSupplierReturnCreditSypMinor() {
        return this.supplierReturnCreditSypMinor;
    }
}
