package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PurchaseReturnRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\bH\u00c6\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001J\t\u0010\u001d\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u001e"}, d2 = {"Lcom/hazem/dailystore/data/PurchaseReturnInvoiceOption;", "", "id", "", "invoiceNumber", "date", "supplierName", "totalUsdCents", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getId", "()Ljava/lang/String;", "getInvoiceNumber", "getDate", "getSupplierName", "getTotalUsdCents", "()J", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class PurchaseReturnInvoiceOption {
    private final String date;
    private final String id;
    private final String invoiceNumber;
    private final String supplierName;
    private final long totalUsdCents;

    public static /* synthetic */ PurchaseReturnInvoiceOption copy$default(PurchaseReturnInvoiceOption purchaseReturnInvoiceOption, String str, String str2, String str3, String str4, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = purchaseReturnInvoiceOption.id;
        }
        if ((i & 2) != 0) {
            str2 = purchaseReturnInvoiceOption.invoiceNumber;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = purchaseReturnInvoiceOption.date;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            str4 = purchaseReturnInvoiceOption.supplierName;
        }
        String str7 = str4;
        if ((i & 16) != 0) {
            j = purchaseReturnInvoiceOption.totalUsdCents;
        }
        return purchaseReturnInvoiceOption.copy(str, str5, str6, str7, j);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
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
    public final String getSupplierName() {
        return this.supplierName;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getTotalUsdCents() {
        return this.totalUsdCents;
    }

    public final PurchaseReturnInvoiceOption copy(String id, String invoiceNumber, String date, String supplierName, long totalUsdCents) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(invoiceNumber, "invoiceNumber");
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(supplierName, "supplierName");
        return new PurchaseReturnInvoiceOption(id, invoiceNumber, date, supplierName, totalUsdCents);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PurchaseReturnInvoiceOption)) {
            return false;
        }
        PurchaseReturnInvoiceOption purchaseReturnInvoiceOption = (PurchaseReturnInvoiceOption) other;
        return Intrinsics.areEqual(this.id, purchaseReturnInvoiceOption.id) && Intrinsics.areEqual(this.invoiceNumber, purchaseReturnInvoiceOption.invoiceNumber) && Intrinsics.areEqual(this.date, purchaseReturnInvoiceOption.date) && Intrinsics.areEqual(this.supplierName, purchaseReturnInvoiceOption.supplierName) && this.totalUsdCents == purchaseReturnInvoiceOption.totalUsdCents;
    }

    public int hashCode() {
        return (((((((this.id.hashCode() * 31) + this.invoiceNumber.hashCode()) * 31) + this.date.hashCode()) * 31) + this.supplierName.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.totalUsdCents);
    }

    public String toString() {
        return "PurchaseReturnInvoiceOption(id=" + this.id + ", invoiceNumber=" + this.invoiceNumber + ", date=" + this.date + ", supplierName=" + this.supplierName + ", totalUsdCents=" + this.totalUsdCents + ")";
    }

    public PurchaseReturnInvoiceOption(String id, String invoiceNumber, String date, String supplierName, long j) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(invoiceNumber, "invoiceNumber");
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(supplierName, "supplierName");
        this.id = id;
        this.invoiceNumber = invoiceNumber;
        this.date = date;
        this.supplierName = supplierName;
        this.totalUsdCents = j;
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

    public final String getSupplierName() {
        return this.supplierName;
    }

    public final long getTotalUsdCents() {
        return this.totalUsdCents;
    }
}
