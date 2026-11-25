package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InventoryValuationRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0018\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\nH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\fH\u00c6\u0003JE\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u00c6\u0001J\u0013\u0010 \u001a\u00020\f2\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\"\u001a\u00020\nH\u00d6\u0001J\t\u0010#\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0018\u00a8\u0006$"}, d2 = {"Lcom/hazem/dailystore/data/InventoryValuation;", "", "asOfLabel", "", "totalCostUsdCents", "", "valuedQuantity", "", "unvaluedQuantity", "unvaluedProductCount", "", "isComplete", "", "<init>", "(Ljava/lang/String;JDDIZ)V", "getAsOfLabel", "()Ljava/lang/String;", "getTotalCostUsdCents", "()J", "getValuedQuantity", "()D", "getUnvaluedQuantity", "getUnvaluedProductCount", "()I", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class InventoryValuation {
    private final String asOfLabel;
    private final boolean isComplete;
    private final long totalCostUsdCents;
    private final int unvaluedProductCount;
    private final double unvaluedQuantity;
    private final double valuedQuantity;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAsOfLabel() {
        return this.asOfLabel;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getTotalCostUsdCents() {
        return this.totalCostUsdCents;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final double getValuedQuantity() {
        return this.valuedQuantity;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final double getUnvaluedQuantity() {
        return this.unvaluedQuantity;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getUnvaluedProductCount() {
        return this.unvaluedProductCount;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getIsComplete() {
        return this.isComplete;
    }

    public final InventoryValuation copy(String asOfLabel, long totalCostUsdCents, double valuedQuantity, double unvaluedQuantity, int unvaluedProductCount, boolean isComplete) {
        Intrinsics.checkNotNullParameter(asOfLabel, "asOfLabel");
        return new InventoryValuation(asOfLabel, totalCostUsdCents, valuedQuantity, unvaluedQuantity, unvaluedProductCount, isComplete);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InventoryValuation)) {
            return false;
        }
        InventoryValuation inventoryValuation = (InventoryValuation) other;
        return Intrinsics.areEqual(this.asOfLabel, inventoryValuation.asOfLabel) && this.totalCostUsdCents == inventoryValuation.totalCostUsdCents && Double.compare(this.valuedQuantity, inventoryValuation.valuedQuantity) == 0 && Double.compare(this.unvaluedQuantity, inventoryValuation.unvaluedQuantity) == 0 && this.unvaluedProductCount == inventoryValuation.unvaluedProductCount && this.isComplete == inventoryValuation.isComplete;
    }

    public int hashCode() {
        return (((((((((this.asOfLabel.hashCode() * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.totalCostUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.valuedQuantity)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.unvaluedQuantity)) * 31) + this.unvaluedProductCount) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.isComplete);
    }

    public String toString() {
        return "InventoryValuation(asOfLabel=" + this.asOfLabel + ", totalCostUsdCents=" + this.totalCostUsdCents + ", valuedQuantity=" + this.valuedQuantity + ", unvaluedQuantity=" + this.unvaluedQuantity + ", unvaluedProductCount=" + this.unvaluedProductCount + ", isComplete=" + this.isComplete + ")";
    }

    public InventoryValuation(String asOfLabel, long j, double d, double d2, int i, boolean z) {
        Intrinsics.checkNotNullParameter(asOfLabel, "asOfLabel");
        this.asOfLabel = asOfLabel;
        this.totalCostUsdCents = j;
        this.valuedQuantity = d;
        this.unvaluedQuantity = d2;
        this.unvaluedProductCount = i;
        this.isComplete = z;
    }

    public final String getAsOfLabel() {
        return this.asOfLabel;
    }

    public final long getTotalCostUsdCents() {
        return this.totalCostUsdCents;
    }

    public final double getValuedQuantity() {
        return this.valuedQuantity;
    }

    public final double getUnvaluedQuantity() {
        return this.unvaluedQuantity;
    }

    public final int getUnvaluedProductCount() {
        return this.unvaluedProductCount;
    }

    public final boolean isComplete() {
        return this.isComplete;
    }
}
