package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: DailyBalanceRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b;\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0097\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\b\u0012\u0006\u0010\r\u001a\u00020\b\u0012\u0006\u0010\u000e\u001a\u00020\b\u0012\u0006\u0010\u000f\u001a\u00020\b\u0012\u0006\u0010\u0010\u001a\u00020\b\u0012\u0006\u0010\u0011\u001a\u00020\b\u0012\u0006\u0010\u0012\u001a\u00020\b\u0012\u0006\u0010\u0013\u001a\u00020\b\u0012\u0006\u0010\u0014\u001a\u00020\b\u0012\u0006\u0010\u0015\u001a\u00020\b\u00a2\u0006\u0004\b\u0016\u0010\u0017J\t\u00100\u001a\u00020\u0003H\u00c6\u0003J\t\u00101\u001a\u00020\u0003H\u00c6\u0003J\t\u00102\u001a\u00020\u0003H\u00c6\u0003J\t\u00103\u001a\u00020\u0003H\u00c6\u0003J\t\u00104\u001a\u00020\bH\u00c6\u0003J\t\u00105\u001a\u00020\bH\u00c6\u0003J\t\u00106\u001a\u00020\bH\u00c6\u0003J\t\u00107\u001a\u00020\bH\u00c6\u0003J\t\u00108\u001a\u00020\bH\u00c6\u0003J\t\u00109\u001a\u00020\bH\u00c6\u0003J\t\u0010:\u001a\u00020\bH\u00c6\u0003J\t\u0010;\u001a\u00020\bH\u00c6\u0003J\t\u0010<\u001a\u00020\bH\u00c6\u0003J\t\u0010=\u001a\u00020\bH\u00c6\u0003J\t\u0010>\u001a\u00020\bH\u00c6\u0003J\t\u0010?\u001a\u00020\bH\u00c6\u0003J\t\u0010@\u001a\u00020\bH\u00c6\u0003J\t\u0010A\u001a\u00020\bH\u00c6\u0003J\u00bd\u0001\u0010B\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\b2\b\b\u0002\u0010\u0012\u001a\u00020\b2\b\b\u0002\u0010\u0013\u001a\u00020\b2\b\b\u0002\u0010\u0014\u001a\u00020\b2\b\b\u0002\u0010\u0015\u001a\u00020\bH\u00c6\u0001J\u0013\u0010C\u001a\u00020D2\b\u0010E\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010F\u001a\u00020GH\u00d6\u0001J\t\u0010H\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\t\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR\u0011\u0010\n\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001eR\u0011\u0010\u000b\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001eR\u0011\u0010\f\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001eR\u0011\u0010\r\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001eR\u0011\u0010\u000e\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001eR\u0011\u0010\u000f\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001eR\u0011\u0010\u0010\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001eR\u0011\u0010\u0011\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001eR\u0011\u0010\u0012\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001eR\u0011\u0010\u0013\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001eR\u0011\u0010\u0014\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001eR\u0011\u0010\u0015\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001eR\u0011\u0010,\u001a\u00020\b8F\u00a2\u0006\u0006\u001a\u0004\b-\u0010\u001eR\u0011\u0010.\u001a\u00020\b8F\u00a2\u0006\u0006\u001a\u0004\b/\u0010\u001e\u00a8\u0006I"}, d2 = {"Lcom/hazem/dailystore/data/DailyAccountSummary;", "", "accountId", "", "accountName", "accountType", "currencyCode", "openingMinor", "", "salesReceiptsMinor", "debtCollectionsMinor", "serviceTransferReceiptsMinor", "supplierReturnRefundsMinor", "otherInflowsMinor", "supplierPaymentsMinor", "personalWithdrawalsMinor", "transferInMinor", "transferOutMinor", "operatingExpensesMinor", "salesReturnRefundsMinor", "otherOutflowsMinor", "closingMinor", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJJJJJJJJJJJJJ)V", "getAccountId", "()Ljava/lang/String;", "getAccountName", "getAccountType", "getCurrencyCode", "getOpeningMinor", "()J", "getSalesReceiptsMinor", "getDebtCollectionsMinor", "getServiceTransferReceiptsMinor", "getSupplierReturnRefundsMinor", "getOtherInflowsMinor", "getSupplierPaymentsMinor", "getPersonalWithdrawalsMinor", "getTransferInMinor", "getTransferOutMinor", "getOperatingExpensesMinor", "getSalesReturnRefundsMinor", "getOtherOutflowsMinor", "getClosingMinor", "electronicReceiptsMinor", "getElectronicReceiptsMinor", "liquidatableElectronicReceiptsMinor", "getLiquidatableElectronicReceiptsMinor", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class DailyAccountSummary {
    private final String accountId;
    private final String accountName;
    private final String accountType;
    private final long closingMinor;
    private final String currencyCode;
    private final long debtCollectionsMinor;
    private final long openingMinor;
    private final long operatingExpensesMinor;
    private final long otherInflowsMinor;
    private final long otherOutflowsMinor;
    private final long personalWithdrawalsMinor;
    private final long salesReceiptsMinor;
    private final long salesReturnRefundsMinor;
    private final long serviceTransferReceiptsMinor;
    private final long supplierPaymentsMinor;
    private final long supplierReturnRefundsMinor;
    private final long transferInMinor;
    private final long transferOutMinor;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAccountId() {
        return this.accountId;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final long getOtherInflowsMinor() {
        return this.otherInflowsMinor;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final long getSupplierPaymentsMinor() {
        return this.supplierPaymentsMinor;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final long getPersonalWithdrawalsMinor() {
        return this.personalWithdrawalsMinor;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final long getTransferInMinor() {
        return this.transferInMinor;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final long getTransferOutMinor() {
        return this.transferOutMinor;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final long getOperatingExpensesMinor() {
        return this.operatingExpensesMinor;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final long getSalesReturnRefundsMinor() {
        return this.salesReturnRefundsMinor;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final long getOtherOutflowsMinor() {
        return this.otherOutflowsMinor;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final long getClosingMinor() {
        return this.closingMinor;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getAccountName() {
        return this.accountName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getAccountType() {
        return this.accountType;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getCurrencyCode() {
        return this.currencyCode;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getOpeningMinor() {
        return this.openingMinor;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getSalesReceiptsMinor() {
        return this.salesReceiptsMinor;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final long getDebtCollectionsMinor() {
        return this.debtCollectionsMinor;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final long getServiceTransferReceiptsMinor() {
        return this.serviceTransferReceiptsMinor;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final long getSupplierReturnRefundsMinor() {
        return this.supplierReturnRefundsMinor;
    }

    public final DailyAccountSummary copy(String accountId, String accountName, String accountType, String currencyCode, long openingMinor, long salesReceiptsMinor, long debtCollectionsMinor, long serviceTransferReceiptsMinor, long supplierReturnRefundsMinor, long otherInflowsMinor, long supplierPaymentsMinor, long personalWithdrawalsMinor, long transferInMinor, long transferOutMinor, long operatingExpensesMinor, long salesReturnRefundsMinor, long otherOutflowsMinor, long closingMinor) {
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Intrinsics.checkNotNullParameter(accountName, "accountName");
        Intrinsics.checkNotNullParameter(accountType, "accountType");
        Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
        return new DailyAccountSummary(accountId, accountName, accountType, currencyCode, openingMinor, salesReceiptsMinor, debtCollectionsMinor, serviceTransferReceiptsMinor, supplierReturnRefundsMinor, otherInflowsMinor, supplierPaymentsMinor, personalWithdrawalsMinor, transferInMinor, transferOutMinor, operatingExpensesMinor, salesReturnRefundsMinor, otherOutflowsMinor, closingMinor);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DailyAccountSummary)) {
            return false;
        }
        DailyAccountSummary dailyAccountSummary = (DailyAccountSummary) other;
        return Intrinsics.areEqual(this.accountId, dailyAccountSummary.accountId) && Intrinsics.areEqual(this.accountName, dailyAccountSummary.accountName) && Intrinsics.areEqual(this.accountType, dailyAccountSummary.accountType) && Intrinsics.areEqual(this.currencyCode, dailyAccountSummary.currencyCode) && this.openingMinor == dailyAccountSummary.openingMinor && this.salesReceiptsMinor == dailyAccountSummary.salesReceiptsMinor && this.debtCollectionsMinor == dailyAccountSummary.debtCollectionsMinor && this.serviceTransferReceiptsMinor == dailyAccountSummary.serviceTransferReceiptsMinor && this.supplierReturnRefundsMinor == dailyAccountSummary.supplierReturnRefundsMinor && this.otherInflowsMinor == dailyAccountSummary.otherInflowsMinor && this.supplierPaymentsMinor == dailyAccountSummary.supplierPaymentsMinor && this.personalWithdrawalsMinor == dailyAccountSummary.personalWithdrawalsMinor && this.transferInMinor == dailyAccountSummary.transferInMinor && this.transferOutMinor == dailyAccountSummary.transferOutMinor && this.operatingExpensesMinor == dailyAccountSummary.operatingExpensesMinor && this.salesReturnRefundsMinor == dailyAccountSummary.salesReturnRefundsMinor && this.otherOutflowsMinor == dailyAccountSummary.otherOutflowsMinor && this.closingMinor == dailyAccountSummary.closingMinor;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((this.accountId.hashCode() * 31) + this.accountName.hashCode()) * 31) + this.accountType.hashCode()) * 31) + this.currencyCode.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.openingMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.salesReceiptsMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.debtCollectionsMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.serviceTransferReceiptsMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.supplierReturnRefundsMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.otherInflowsMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.supplierPaymentsMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.personalWithdrawalsMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.transferInMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.transferOutMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.operatingExpensesMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.salesReturnRefundsMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.otherOutflowsMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.closingMinor);
    }

    public String toString() {
        return "DailyAccountSummary(accountId=" + this.accountId + ", accountName=" + this.accountName + ", accountType=" + this.accountType + ", currencyCode=" + this.currencyCode + ", openingMinor=" + this.openingMinor + ", salesReceiptsMinor=" + this.salesReceiptsMinor + ", debtCollectionsMinor=" + this.debtCollectionsMinor + ", serviceTransferReceiptsMinor=" + this.serviceTransferReceiptsMinor + ", supplierReturnRefundsMinor=" + this.supplierReturnRefundsMinor + ", otherInflowsMinor=" + this.otherInflowsMinor + ", supplierPaymentsMinor=" + this.supplierPaymentsMinor + ", personalWithdrawalsMinor=" + this.personalWithdrawalsMinor + ", transferInMinor=" + this.transferInMinor + ", transferOutMinor=" + this.transferOutMinor + ", operatingExpensesMinor=" + this.operatingExpensesMinor + ", salesReturnRefundsMinor=" + this.salesReturnRefundsMinor + ", otherOutflowsMinor=" + this.otherOutflowsMinor + ", closingMinor=" + this.closingMinor + ")";
    }

    public DailyAccountSummary(String accountId, String accountName, String accountType, String currencyCode, long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14) {
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Intrinsics.checkNotNullParameter(accountName, "accountName");
        Intrinsics.checkNotNullParameter(accountType, "accountType");
        Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountType = accountType;
        this.currencyCode = currencyCode;
        this.openingMinor = j;
        this.salesReceiptsMinor = j2;
        this.debtCollectionsMinor = j3;
        this.serviceTransferReceiptsMinor = j4;
        this.supplierReturnRefundsMinor = j5;
        this.otherInflowsMinor = j6;
        this.supplierPaymentsMinor = j7;
        this.personalWithdrawalsMinor = j8;
        this.transferInMinor = j9;
        this.transferOutMinor = j10;
        this.operatingExpensesMinor = j11;
        this.salesReturnRefundsMinor = j12;
        this.otherOutflowsMinor = j13;
        this.closingMinor = j14;
    }

    public final String getAccountId() {
        return this.accountId;
    }

    public final String getAccountName() {
        return this.accountName;
    }

    public final String getAccountType() {
        return this.accountType;
    }

    public final String getCurrencyCode() {
        return this.currencyCode;
    }

    public final long getDebtCollectionsMinor() {
        return this.debtCollectionsMinor;
    }

    public final long getOpeningMinor() {
        return this.openingMinor;
    }

    public final long getOtherInflowsMinor() {
        return this.otherInflowsMinor;
    }

    public final long getSalesReceiptsMinor() {
        return this.salesReceiptsMinor;
    }

    public final long getServiceTransferReceiptsMinor() {
        return this.serviceTransferReceiptsMinor;
    }

    public final long getSupplierReturnRefundsMinor() {
        return this.supplierReturnRefundsMinor;
    }

    public final long getPersonalWithdrawalsMinor() {
        return this.personalWithdrawalsMinor;
    }

    public final long getSupplierPaymentsMinor() {
        return this.supplierPaymentsMinor;
    }

    public final long getTransferInMinor() {
        return this.transferInMinor;
    }

    public final long getTransferOutMinor() {
        return this.transferOutMinor;
    }

    public final long getClosingMinor() {
        return this.closingMinor;
    }

    public final long getOperatingExpensesMinor() {
        return this.operatingExpensesMinor;
    }

    public final long getOtherOutflowsMinor() {
        return this.otherOutflowsMinor;
    }

    public final long getSalesReturnRefundsMinor() {
        return this.salesReturnRefundsMinor;
    }

    public final long getElectronicReceiptsMinor() {
        return MainActivity$$ExternalSyntheticBackport0.m(MainActivity$$ExternalSyntheticBackport0.m(this.salesReceiptsMinor, this.debtCollectionsMinor), this.serviceTransferReceiptsMinor);
    }

    public final long getLiquidatableElectronicReceiptsMinor() {
        return RangesKt.coerceAtLeast(getElectronicReceiptsMinor() - this.transferOutMinor, 0L);
    }
}
