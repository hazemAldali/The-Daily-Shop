package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SalesReturnRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\tH\u00c6\u0003JG\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\tH\u00c6\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001J\t\u0010 \u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006!"}, d2 = {"Lcom/hazem/dailystore/data/SalesReturnInvoiceOption;", "", "id", "", "number", "date", "saleType", "customerName", "totalUsdCents", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getId", "()Ljava/lang/String;", "getNumber", "getDate", "getSaleType", "getCustomerName", "getTotalUsdCents", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class SalesReturnInvoiceOption {
    private final String customerName;
    private final String date;
    private final String id;
    private final String number;
    private final String saleType;
    private final long totalUsdCents;

    public static /* synthetic */ SalesReturnInvoiceOption copy$default(SalesReturnInvoiceOption salesReturnInvoiceOption, String str, String str2, String str3, String str4, String str5, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = salesReturnInvoiceOption.id;
        }
        if ((i & 2) != 0) {
            str2 = salesReturnInvoiceOption.number;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = salesReturnInvoiceOption.date;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = salesReturnInvoiceOption.saleType;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            str5 = salesReturnInvoiceOption.customerName;
        }
        String str9 = str5;
        if ((i & 32) != 0) {
            j = salesReturnInvoiceOption.totalUsdCents;
        }
        return salesReturnInvoiceOption.copy(str, str6, str7, str8, str9, j);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getNumber() {
        return this.number;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDate() {
        return this.date;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getSaleType() {
        return this.saleType;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getCustomerName() {
        return this.customerName;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getTotalUsdCents() {
        return this.totalUsdCents;
    }

    public final SalesReturnInvoiceOption copy(String id, String number, String date, String saleType, String customerName, long totalUsdCents) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(number, "number");
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(saleType, "saleType");
        return new SalesReturnInvoiceOption(id, number, date, saleType, customerName, totalUsdCents);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SalesReturnInvoiceOption)) {
            return false;
        }
        SalesReturnInvoiceOption salesReturnInvoiceOption = (SalesReturnInvoiceOption) other;
        return Intrinsics.areEqual(this.id, salesReturnInvoiceOption.id) && Intrinsics.areEqual(this.number, salesReturnInvoiceOption.number) && Intrinsics.areEqual(this.date, salesReturnInvoiceOption.date) && Intrinsics.areEqual(this.saleType, salesReturnInvoiceOption.saleType) && Intrinsics.areEqual(this.customerName, salesReturnInvoiceOption.customerName) && this.totalUsdCents == salesReturnInvoiceOption.totalUsdCents;
    }

    public int hashCode() {
        int iHashCode = ((((((this.id.hashCode() * 31) + this.number.hashCode()) * 31) + this.date.hashCode()) * 31) + this.saleType.hashCode()) * 31;
        String str = this.customerName;
        return ((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.totalUsdCents);
    }

    public String toString() {
        return "SalesReturnInvoiceOption(id=" + this.id + ", number=" + this.number + ", date=" + this.date + ", saleType=" + this.saleType + ", customerName=" + this.customerName + ", totalUsdCents=" + this.totalUsdCents + ")";
    }

    public SalesReturnInvoiceOption(String id, String number, String date, String saleType, String str, long j) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(number, "number");
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(saleType, "saleType");
        this.id = id;
        this.number = number;
        this.date = date;
        this.saleType = saleType;
        this.customerName = str;
        this.totalUsdCents = j;
    }

    public final String getDate() {
        return this.date;
    }

    public final String getId() {
        return this.id;
    }

    public final String getNumber() {
        return this.number;
    }

    public final String getSaleType() {
        return this.saleType;
    }

    public final String getCustomerName() {
        return this.customerName;
    }

    public final long getTotalUsdCents() {
        return this.totalUsdCents;
    }
}
