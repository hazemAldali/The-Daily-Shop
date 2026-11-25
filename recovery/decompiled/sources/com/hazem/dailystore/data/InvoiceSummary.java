package com.hazem.dailystore.data;

import androidx.core.app.NotificationCompat;
import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InvoiceRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\bH\u00c6\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003JG\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001J\t\u0010 \u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r\u00a8\u0006!"}, d2 = {"Lcom/hazem/dailystore/data/InvoiceSummary;", "", "id", "", "invoiceNumber", "date", NotificationCompat.CATEGORY_STATUS, "totalUsdCents", "", "partyName", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;)V", "getId", "()Ljava/lang/String;", "getInvoiceNumber", "getDate", "getStatus", "getTotalUsdCents", "()J", "getPartyName", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class InvoiceSummary {
    private final String date;
    private final String id;
    private final String invoiceNumber;
    private final String partyName;
    private final String status;
    private final long totalUsdCents;

    public static /* synthetic */ InvoiceSummary copy$default(InvoiceSummary invoiceSummary, String str, String str2, String str3, String str4, long j, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = invoiceSummary.id;
        }
        if ((i & 2) != 0) {
            str2 = invoiceSummary.invoiceNumber;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = invoiceSummary.date;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = invoiceSummary.status;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            j = invoiceSummary.totalUsdCents;
        }
        long j2 = j;
        if ((i & 32) != 0) {
            str5 = invoiceSummary.partyName;
        }
        return invoiceSummary.copy(str, str6, str7, str8, j2, str5);
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
    public final String getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getTotalUsdCents() {
        return this.totalUsdCents;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getPartyName() {
        return this.partyName;
    }

    public final InvoiceSummary copy(String id, String invoiceNumber, String date, String status, long totalUsdCents, String partyName) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(invoiceNumber, "invoiceNumber");
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(status, "status");
        return new InvoiceSummary(id, invoiceNumber, date, status, totalUsdCents, partyName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InvoiceSummary)) {
            return false;
        }
        InvoiceSummary invoiceSummary = (InvoiceSummary) other;
        return Intrinsics.areEqual(this.id, invoiceSummary.id) && Intrinsics.areEqual(this.invoiceNumber, invoiceSummary.invoiceNumber) && Intrinsics.areEqual(this.date, invoiceSummary.date) && Intrinsics.areEqual(this.status, invoiceSummary.status) && this.totalUsdCents == invoiceSummary.totalUsdCents && Intrinsics.areEqual(this.partyName, invoiceSummary.partyName);
    }

    public int hashCode() {
        int iHashCode = ((((((((this.id.hashCode() * 31) + this.invoiceNumber.hashCode()) * 31) + this.date.hashCode()) * 31) + this.status.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.totalUsdCents)) * 31;
        String str = this.partyName;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "InvoiceSummary(id=" + this.id + ", invoiceNumber=" + this.invoiceNumber + ", date=" + this.date + ", status=" + this.status + ", totalUsdCents=" + this.totalUsdCents + ", partyName=" + this.partyName + ")";
    }

    public InvoiceSummary(String id, String invoiceNumber, String date, String status, long j, String str) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(invoiceNumber, "invoiceNumber");
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(status, "status");
        this.id = id;
        this.invoiceNumber = invoiceNumber;
        this.date = date;
        this.status = status;
        this.totalUsdCents = j;
        this.partyName = str;
    }

    public final String getId() {
        return this.id;
    }

    public final String getInvoiceNumber() {
        return this.invoiceNumber;
    }

    public final String getDate() {
        return this.date;
    }

    public final String getStatus() {
        return this.status;
    }

    public final long getTotalUsdCents() {
        return this.totalUsdCents;
    }

    public final String getPartyName() {
        return this.partyName;
    }
}
