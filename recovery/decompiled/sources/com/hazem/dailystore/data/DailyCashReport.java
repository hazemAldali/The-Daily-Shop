package com.hazem.dailystore.data;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DailyBalanceRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bc\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\b\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\b\u0012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u0015\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\bH\u00c6\u0003J\u0015\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\bH\u00c6\u0003J\u0015\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\bH\u00c6\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\rH\u00c6\u0003Jq\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\b2\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\b2\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rH\u00c6\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010$\u001a\u00020%H\u00d6\u0001J\t\u0010&\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u001d\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0013\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006'"}, d2 = {"Lcom/hazem/dailystore/data/DailyCashReport;", "", "operatingDate", "", "accounts", "", "Lcom/hazem/dailystore/data/DailyAccountSummary;", "electronicReceiptsByCurrency", "", "", "liquidatableElectronicReceiptsByCurrency", "physicalCashReceiptsByCurrency", "supplierRollover", "Lcom/hazem/dailystore/data/SupplierRolloverSummary;", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Lcom/hazem/dailystore/data/SupplierRolloverSummary;)V", "getOperatingDate", "()Ljava/lang/String;", "getAccounts", "()Ljava/util/List;", "getElectronicReceiptsByCurrency", "()Ljava/util/Map;", "getLiquidatableElectronicReceiptsByCurrency", "getPhysicalCashReceiptsByCurrency", "getSupplierRollover", "()Lcom/hazem/dailystore/data/SupplierRolloverSummary;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class DailyCashReport {
    private final List<DailyAccountSummary> accounts;
    private final Map<String, Long> electronicReceiptsByCurrency;
    private final Map<String, Long> liquidatableElectronicReceiptsByCurrency;
    private final String operatingDate;
    private final Map<String, Long> physicalCashReceiptsByCurrency;
    private final SupplierRolloverSummary supplierRollover;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DailyCashReport copy$default(DailyCashReport dailyCashReport, String str, List list, Map map, Map map2, Map map3, SupplierRolloverSummary supplierRolloverSummary, int i, Object obj) {
        if ((i & 1) != 0) {
            str = dailyCashReport.operatingDate;
        }
        if ((i & 2) != 0) {
            list = dailyCashReport.accounts;
        }
        List list2 = list;
        if ((i & 4) != 0) {
            map = dailyCashReport.electronicReceiptsByCurrency;
        }
        Map map4 = map;
        if ((i & 8) != 0) {
            map2 = dailyCashReport.liquidatableElectronicReceiptsByCurrency;
        }
        Map map5 = map2;
        if ((i & 16) != 0) {
            map3 = dailyCashReport.physicalCashReceiptsByCurrency;
        }
        Map map6 = map3;
        if ((i & 32) != 0) {
            supplierRolloverSummary = dailyCashReport.supplierRollover;
        }
        return dailyCashReport.copy(str, list2, map4, map5, map6, supplierRolloverSummary);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getOperatingDate() {
        return this.operatingDate;
    }

    public final List<DailyAccountSummary> component2() {
        return this.accounts;
    }

    public final Map<String, Long> component3() {
        return this.electronicReceiptsByCurrency;
    }

    public final Map<String, Long> component4() {
        return this.liquidatableElectronicReceiptsByCurrency;
    }

    public final Map<String, Long> component5() {
        return this.physicalCashReceiptsByCurrency;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final SupplierRolloverSummary getSupplierRollover() {
        return this.supplierRollover;
    }

    public final DailyCashReport copy(String operatingDate, List<DailyAccountSummary> accounts, Map<String, Long> electronicReceiptsByCurrency, Map<String, Long> liquidatableElectronicReceiptsByCurrency, Map<String, Long> physicalCashReceiptsByCurrency, SupplierRolloverSummary supplierRollover) {
        Intrinsics.checkNotNullParameter(operatingDate, "operatingDate");
        Intrinsics.checkNotNullParameter(accounts, "accounts");
        Intrinsics.checkNotNullParameter(electronicReceiptsByCurrency, "electronicReceiptsByCurrency");
        Intrinsics.checkNotNullParameter(liquidatableElectronicReceiptsByCurrency, "liquidatableElectronicReceiptsByCurrency");
        Intrinsics.checkNotNullParameter(physicalCashReceiptsByCurrency, "physicalCashReceiptsByCurrency");
        return new DailyCashReport(operatingDate, accounts, electronicReceiptsByCurrency, liquidatableElectronicReceiptsByCurrency, physicalCashReceiptsByCurrency, supplierRollover);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DailyCashReport)) {
            return false;
        }
        DailyCashReport dailyCashReport = (DailyCashReport) other;
        return Intrinsics.areEqual(this.operatingDate, dailyCashReport.operatingDate) && Intrinsics.areEqual(this.accounts, dailyCashReport.accounts) && Intrinsics.areEqual(this.electronicReceiptsByCurrency, dailyCashReport.electronicReceiptsByCurrency) && Intrinsics.areEqual(this.liquidatableElectronicReceiptsByCurrency, dailyCashReport.liquidatableElectronicReceiptsByCurrency) && Intrinsics.areEqual(this.physicalCashReceiptsByCurrency, dailyCashReport.physicalCashReceiptsByCurrency) && Intrinsics.areEqual(this.supplierRollover, dailyCashReport.supplierRollover);
    }

    public int hashCode() {
        int iHashCode = ((((((((this.operatingDate.hashCode() * 31) + this.accounts.hashCode()) * 31) + this.electronicReceiptsByCurrency.hashCode()) * 31) + this.liquidatableElectronicReceiptsByCurrency.hashCode()) * 31) + this.physicalCashReceiptsByCurrency.hashCode()) * 31;
        SupplierRolloverSummary supplierRolloverSummary = this.supplierRollover;
        return iHashCode + (supplierRolloverSummary == null ? 0 : supplierRolloverSummary.hashCode());
    }

    public String toString() {
        return "DailyCashReport(operatingDate=" + this.operatingDate + ", accounts=" + this.accounts + ", electronicReceiptsByCurrency=" + this.electronicReceiptsByCurrency + ", liquidatableElectronicReceiptsByCurrency=" + this.liquidatableElectronicReceiptsByCurrency + ", physicalCashReceiptsByCurrency=" + this.physicalCashReceiptsByCurrency + ", supplierRollover=" + this.supplierRollover + ")";
    }

    public DailyCashReport(String operatingDate, List<DailyAccountSummary> accounts, Map<String, Long> electronicReceiptsByCurrency, Map<String, Long> liquidatableElectronicReceiptsByCurrency, Map<String, Long> physicalCashReceiptsByCurrency, SupplierRolloverSummary supplierRolloverSummary) {
        Intrinsics.checkNotNullParameter(operatingDate, "operatingDate");
        Intrinsics.checkNotNullParameter(accounts, "accounts");
        Intrinsics.checkNotNullParameter(electronicReceiptsByCurrency, "electronicReceiptsByCurrency");
        Intrinsics.checkNotNullParameter(liquidatableElectronicReceiptsByCurrency, "liquidatableElectronicReceiptsByCurrency");
        Intrinsics.checkNotNullParameter(physicalCashReceiptsByCurrency, "physicalCashReceiptsByCurrency");
        this.operatingDate = operatingDate;
        this.accounts = accounts;
        this.electronicReceiptsByCurrency = electronicReceiptsByCurrency;
        this.liquidatableElectronicReceiptsByCurrency = liquidatableElectronicReceiptsByCurrency;
        this.physicalCashReceiptsByCurrency = physicalCashReceiptsByCurrency;
        this.supplierRollover = supplierRolloverSummary;
    }

    public final List<DailyAccountSummary> getAccounts() {
        return this.accounts;
    }

    public final Map<String, Long> getElectronicReceiptsByCurrency() {
        return this.electronicReceiptsByCurrency;
    }

    public final Map<String, Long> getLiquidatableElectronicReceiptsByCurrency() {
        return this.liquidatableElectronicReceiptsByCurrency;
    }

    public final String getOperatingDate() {
        return this.operatingDate;
    }

    public final Map<String, Long> getPhysicalCashReceiptsByCurrency() {
        return this.physicalCashReceiptsByCurrency;
    }

    public final SupplierRolloverSummary getSupplierRollover() {
        return this.supplierRollover;
    }
}
