package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SalesProfitRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b3\b\u0086\b\u0018\u00002\u00020\u0001B\u0087\u0001\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u000f\u001a\u00020\b\u0012\b\b\u0002\u0010\u0010\u001a\u00020\b\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0013\u001a\u00020\b\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u000b\u0010-\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010/\u001a\u00020\u0006H\u00c6\u0003J\t\u00100\u001a\u00020\bH\u00c6\u0003J\t\u00101\u001a\u00020\bH\u00c6\u0003J\t\u00102\u001a\u00020\bH\u00c6\u0003J\t\u00103\u001a\u00020\u0006H\u00c6\u0003J\t\u00104\u001a\u00020\rH\u00c6\u0003J\t\u00105\u001a\u00020\u0006H\u00c6\u0003J\t\u00106\u001a\u00020\bH\u00c6\u0003J\t\u00107\u001a\u00020\bH\u00c6\u0003J\t\u00108\u001a\u00020\u0006H\u00c6\u0003J\t\u00109\u001a\u00020\u0006H\u00c6\u0003J\t\u0010:\u001a\u00020\bH\u00c6\u0003J\u0099\u0001\u0010;\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\u00062\b\b\u0002\u0010\u0013\u001a\u00020\bH\u00c6\u0001J\u0013\u0010<\u001a\u00020\r2\b\u0010=\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010>\u001a\u00020\u0006H\u00d6\u0001J\t\u0010?\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\t\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0011\u0010\n\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR\u0011\u0010\u000b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001aR\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010 R\u0011\u0010\u000e\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u0011\u0010\u000f\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001cR\u0011\u0010\u0010\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001cR\u0011\u0010\u0011\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001aR\u0011\u0010\u0012\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001aR\u0011\u0010\u0013\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001cR\u0011\u0010'\u001a\u00020\b8F\u00a2\u0006\u0006\u001a\u0004\b(\u0010\u001cR\u0011\u0010)\u001a\u00020\b8F\u00a2\u0006\u0006\u001a\u0004\b*\u0010\u001cR\u0011\u0010+\u001a\u00020\b8F\u00a2\u0006\u0006\u001a\u0004\b,\u0010\u001c\u00a8\u0006@"}, d2 = {"Lcom/hazem/dailystore/data/SalesProfitSummary;", "", "fromDate", "", "throughDate", "invoiceCount", "", "revenueUsdCents", "", "cogsUsdCents", "grossProfitUsdCents", "uncostedInvoiceCount", "isComplete", "", "returnCount", "returnUsdCents", "returnedCogsUsdCents", "uncostedReturnCount", "operatingExpenseCount", "operatingExpensesUsdCents", "<init>", "(Ljava/lang/String;Ljava/lang/String;IJJJIZIJJIIJ)V", "getFromDate", "()Ljava/lang/String;", "getThroughDate", "getInvoiceCount", "()I", "getRevenueUsdCents", "()J", "getCogsUsdCents", "getGrossProfitUsdCents", "getUncostedInvoiceCount", "()Z", "getReturnCount", "getReturnUsdCents", "getReturnedCogsUsdCents", "getUncostedReturnCount", "getOperatingExpenseCount", "getOperatingExpensesUsdCents", "netRevenueUsdCents", "getNetRevenueUsdCents", "netCogsUsdCents", "getNetCogsUsdCents", "netOperatingProfitUsdCents", "getNetOperatingProfitUsdCents", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "equals", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class SalesProfitSummary {
    private final long cogsUsdCents;
    private final String fromDate;
    private final long grossProfitUsdCents;
    private final int invoiceCount;
    private final boolean isComplete;
    private final int operatingExpenseCount;
    private final long operatingExpensesUsdCents;
    private final int returnCount;
    private final long returnUsdCents;
    private final long returnedCogsUsdCents;
    private final long revenueUsdCents;
    private final String throughDate;
    private final int uncostedInvoiceCount;
    private final int uncostedReturnCount;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getFromDate() {
        return this.fromDate;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final long getReturnUsdCents() {
        return this.returnUsdCents;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final long getReturnedCogsUsdCents() {
        return this.returnedCogsUsdCents;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final int getUncostedReturnCount() {
        return this.uncostedReturnCount;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final int getOperatingExpenseCount() {
        return this.operatingExpenseCount;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final long getOperatingExpensesUsdCents() {
        return this.operatingExpensesUsdCents;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getThroughDate() {
        return this.throughDate;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getInvoiceCount() {
        return this.invoiceCount;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getRevenueUsdCents() {
        return this.revenueUsdCents;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getCogsUsdCents() {
        return this.cogsUsdCents;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getGrossProfitUsdCents() {
        return this.grossProfitUsdCents;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getUncostedInvoiceCount() {
        return this.uncostedInvoiceCount;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final boolean getIsComplete() {
        return this.isComplete;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getReturnCount() {
        return this.returnCount;
    }

    public final SalesProfitSummary copy(String fromDate, String throughDate, int invoiceCount, long revenueUsdCents, long cogsUsdCents, long grossProfitUsdCents, int uncostedInvoiceCount, boolean isComplete, int returnCount, long returnUsdCents, long returnedCogsUsdCents, int uncostedReturnCount, int operatingExpenseCount, long operatingExpensesUsdCents) {
        return new SalesProfitSummary(fromDate, throughDate, invoiceCount, revenueUsdCents, cogsUsdCents, grossProfitUsdCents, uncostedInvoiceCount, isComplete, returnCount, returnUsdCents, returnedCogsUsdCents, uncostedReturnCount, operatingExpenseCount, operatingExpensesUsdCents);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SalesProfitSummary)) {
            return false;
        }
        SalesProfitSummary salesProfitSummary = (SalesProfitSummary) other;
        return Intrinsics.areEqual(this.fromDate, salesProfitSummary.fromDate) && Intrinsics.areEqual(this.throughDate, salesProfitSummary.throughDate) && this.invoiceCount == salesProfitSummary.invoiceCount && this.revenueUsdCents == salesProfitSummary.revenueUsdCents && this.cogsUsdCents == salesProfitSummary.cogsUsdCents && this.grossProfitUsdCents == salesProfitSummary.grossProfitUsdCents && this.uncostedInvoiceCount == salesProfitSummary.uncostedInvoiceCount && this.isComplete == salesProfitSummary.isComplete && this.returnCount == salesProfitSummary.returnCount && this.returnUsdCents == salesProfitSummary.returnUsdCents && this.returnedCogsUsdCents == salesProfitSummary.returnedCogsUsdCents && this.uncostedReturnCount == salesProfitSummary.uncostedReturnCount && this.operatingExpenseCount == salesProfitSummary.operatingExpenseCount && this.operatingExpensesUsdCents == salesProfitSummary.operatingExpensesUsdCents;
    }

    public int hashCode() {
        String str = this.fromDate;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.throughDate;
        return ((((((((((((((((((((((((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + this.invoiceCount) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.revenueUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.cogsUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.grossProfitUsdCents)) * 31) + this.uncostedInvoiceCount) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.isComplete)) * 31) + this.returnCount) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.returnUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.returnedCogsUsdCents)) * 31) + this.uncostedReturnCount) * 31) + this.operatingExpenseCount) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.operatingExpensesUsdCents);
    }

    public String toString() {
        return "SalesProfitSummary(fromDate=" + this.fromDate + ", throughDate=" + this.throughDate + ", invoiceCount=" + this.invoiceCount + ", revenueUsdCents=" + this.revenueUsdCents + ", cogsUsdCents=" + this.cogsUsdCents + ", grossProfitUsdCents=" + this.grossProfitUsdCents + ", uncostedInvoiceCount=" + this.uncostedInvoiceCount + ", isComplete=" + this.isComplete + ", returnCount=" + this.returnCount + ", returnUsdCents=" + this.returnUsdCents + ", returnedCogsUsdCents=" + this.returnedCogsUsdCents + ", uncostedReturnCount=" + this.uncostedReturnCount + ", operatingExpenseCount=" + this.operatingExpenseCount + ", operatingExpensesUsdCents=" + this.operatingExpensesUsdCents + ")";
    }

    public SalesProfitSummary(String str, String str2, int i, long j, long j2, long j3, int i2, boolean z, int i3, long j4, long j5, int i4, int i5, long j6) {
        this.fromDate = str;
        this.throughDate = str2;
        this.invoiceCount = i;
        this.revenueUsdCents = j;
        this.cogsUsdCents = j2;
        this.grossProfitUsdCents = j3;
        this.uncostedInvoiceCount = i2;
        this.isComplete = z;
        this.returnCount = i3;
        this.returnUsdCents = j4;
        this.returnedCogsUsdCents = j5;
        this.uncostedReturnCount = i4;
        this.operatingExpenseCount = i5;
        this.operatingExpensesUsdCents = j6;
    }

    public /* synthetic */ SalesProfitSummary(String str, String str2, int i, long j, long j2, long j3, int i2, boolean z, int i3, long j4, long j5, int i4, int i5, long j6, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, i, j, j2, j3, i2, z, (i6 & 256) != 0 ? 0 : i3, (i6 & 512) != 0 ? 0L : j4, (i6 & 1024) != 0 ? 0L : j5, (i6 & 2048) != 0 ? 0 : i4, (i6 & 4096) != 0 ? 0 : i5, (i6 & 8192) != 0 ? 0L : j6);
    }

    public final String getFromDate() {
        return this.fromDate;
    }

    public final String getThroughDate() {
        return this.throughDate;
    }

    public final int getInvoiceCount() {
        return this.invoiceCount;
    }

    public final long getRevenueUsdCents() {
        return this.revenueUsdCents;
    }

    public final long getCogsUsdCents() {
        return this.cogsUsdCents;
    }

    public final long getGrossProfitUsdCents() {
        return this.grossProfitUsdCents;
    }

    public final int getUncostedInvoiceCount() {
        return this.uncostedInvoiceCount;
    }

    public final boolean isComplete() {
        return this.isComplete;
    }

    public final int getReturnCount() {
        return this.returnCount;
    }

    public final long getReturnUsdCents() {
        return this.returnUsdCents;
    }

    public final long getReturnedCogsUsdCents() {
        return this.returnedCogsUsdCents;
    }

    public final int getUncostedReturnCount() {
        return this.uncostedReturnCount;
    }

    public final int getOperatingExpenseCount() {
        return this.operatingExpenseCount;
    }

    public final long getOperatingExpensesUsdCents() {
        return this.operatingExpensesUsdCents;
    }

    public final long getNetRevenueUsdCents() {
        return DailyBalanceRepository$$ExternalSyntheticBackport0.m(this.revenueUsdCents, this.returnUsdCents);
    }

    public final long getNetCogsUsdCents() {
        return DailyBalanceRepository$$ExternalSyntheticBackport0.m(this.cogsUsdCents, this.returnedCogsUsdCents);
    }

    public final long getNetOperatingProfitUsdCents() {
        if (this.isComplete) {
            return DailyBalanceRepository$$ExternalSyntheticBackport0.m(this.grossProfitUsdCents, this.operatingExpensesUsdCents);
        }
        return 0L;
    }
}
