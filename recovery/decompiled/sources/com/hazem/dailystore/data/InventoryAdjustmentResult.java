package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InventoryAdjustmentRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b$\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bk\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\u0004\b\u0012\u0010\u0013J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003J\t\u0010'\u001a\u00020\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\u0003H\u00c6\u0003J\t\u0010*\u001a\u00020\tH\u00c6\u0003J\t\u0010+\u001a\u00020\tH\u00c6\u0003J\t\u0010,\u001a\u00020\tH\u00c6\u0003J\u0010\u0010-\u001a\u0004\u0018\u00010\rH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001fJ\u0010\u0010.\u001a\u0004\u0018\u00010\rH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001fJ\t\u0010/\u001a\u00020\u0003H\u00c6\u0003J\t\u00100\u001a\u00020\u0011H\u00c6\u0003J\u008a\u0001\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u00c6\u0001\u00a2\u0006\u0002\u00102J\u0013\u00103\u001a\u00020\u00112\b\u00104\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00105\u001a\u000206H\u00d6\u0001J\t\u00107\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\n\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0011\u0010\u000b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u0015\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\n\n\u0002\u0010 \u001a\u0004\b\u001e\u0010\u001fR\u0015\u0010\u000e\u001a\u0004\u0018\u00010\r\u00a2\u0006\n\n\u0002\u0010 \u001a\u0004\b!\u0010\u001fR\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0015R\u0011\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$\u00a8\u00068"}, d2 = {"Lcom/hazem/dailystore/data/InventoryAdjustmentResult;", "", "adjustmentId", "", "movementId", "productId", "warehouseId", "adjustmentType", "quantity", "Ljava/math/BigDecimal;", "quantityBefore", "quantityAfter", "unitCostUsdCents", "", "costUsdCents", "reason", "replayed", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/math/BigDecimal;Ljava/math/BigDecimal;Ljava/math/BigDecimal;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Z)V", "getAdjustmentId", "()Ljava/lang/String;", "getMovementId", "getProductId", "getWarehouseId", "getAdjustmentType", "getQuantity", "()Ljava/math/BigDecimal;", "getQuantityBefore", "getQuantityAfter", "getUnitCostUsdCents", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getCostUsdCents", "getReason", "getReplayed", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/math/BigDecimal;Ljava/math/BigDecimal;Ljava/math/BigDecimal;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Z)Lcom/hazem/dailystore/data/InventoryAdjustmentResult;", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class InventoryAdjustmentResult {
    private final String adjustmentId;
    private final String adjustmentType;
    private final Long costUsdCents;
    private final String movementId;
    private final String productId;
    private final BigDecimal quantity;
    private final BigDecimal quantityAfter;
    private final BigDecimal quantityBefore;
    private final String reason;
    private final boolean replayed;
    private final Long unitCostUsdCents;
    private final String warehouseId;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAdjustmentId() {
        return this.adjustmentId;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final Long getCostUsdCents() {
        return this.costUsdCents;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getReason() {
        return this.reason;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final boolean getReplayed() {
        return this.replayed;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getMovementId() {
        return this.movementId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getProductId() {
        return this.productId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getWarehouseId() {
        return this.warehouseId;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getAdjustmentType() {
        return this.adjustmentType;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final BigDecimal getQuantity() {
        return this.quantity;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final BigDecimal getQuantityBefore() {
        return this.quantityBefore;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final BigDecimal getQuantityAfter() {
        return this.quantityAfter;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final Long getUnitCostUsdCents() {
        return this.unitCostUsdCents;
    }

    public final InventoryAdjustmentResult copy(String adjustmentId, String movementId, String productId, String warehouseId, String adjustmentType, BigDecimal quantity, BigDecimal quantityBefore, BigDecimal quantityAfter, Long unitCostUsdCents, Long costUsdCents, String reason, boolean replayed) {
        Intrinsics.checkNotNullParameter(adjustmentId, "adjustmentId");
        Intrinsics.checkNotNullParameter(movementId, "movementId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(warehouseId, "warehouseId");
        Intrinsics.checkNotNullParameter(adjustmentType, "adjustmentType");
        Intrinsics.checkNotNullParameter(quantity, "quantity");
        Intrinsics.checkNotNullParameter(quantityBefore, "quantityBefore");
        Intrinsics.checkNotNullParameter(quantityAfter, "quantityAfter");
        Intrinsics.checkNotNullParameter(reason, "reason");
        return new InventoryAdjustmentResult(adjustmentId, movementId, productId, warehouseId, adjustmentType, quantity, quantityBefore, quantityAfter, unitCostUsdCents, costUsdCents, reason, replayed);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InventoryAdjustmentResult)) {
            return false;
        }
        InventoryAdjustmentResult inventoryAdjustmentResult = (InventoryAdjustmentResult) other;
        return Intrinsics.areEqual(this.adjustmentId, inventoryAdjustmentResult.adjustmentId) && Intrinsics.areEqual(this.movementId, inventoryAdjustmentResult.movementId) && Intrinsics.areEqual(this.productId, inventoryAdjustmentResult.productId) && Intrinsics.areEqual(this.warehouseId, inventoryAdjustmentResult.warehouseId) && Intrinsics.areEqual(this.adjustmentType, inventoryAdjustmentResult.adjustmentType) && Intrinsics.areEqual(this.quantity, inventoryAdjustmentResult.quantity) && Intrinsics.areEqual(this.quantityBefore, inventoryAdjustmentResult.quantityBefore) && Intrinsics.areEqual(this.quantityAfter, inventoryAdjustmentResult.quantityAfter) && Intrinsics.areEqual(this.unitCostUsdCents, inventoryAdjustmentResult.unitCostUsdCents) && Intrinsics.areEqual(this.costUsdCents, inventoryAdjustmentResult.costUsdCents) && Intrinsics.areEqual(this.reason, inventoryAdjustmentResult.reason) && this.replayed == inventoryAdjustmentResult.replayed;
    }

    public int hashCode() {
        int iHashCode = ((((((((((((((this.adjustmentId.hashCode() * 31) + this.movementId.hashCode()) * 31) + this.productId.hashCode()) * 31) + this.warehouseId.hashCode()) * 31) + this.adjustmentType.hashCode()) * 31) + this.quantity.hashCode()) * 31) + this.quantityBefore.hashCode()) * 31) + this.quantityAfter.hashCode()) * 31;
        Long l = this.unitCostUsdCents;
        int iHashCode2 = (iHashCode + (l == null ? 0 : l.hashCode())) * 31;
        Long l2 = this.costUsdCents;
        return ((((iHashCode2 + (l2 != null ? l2.hashCode() : 0)) * 31) + this.reason.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.replayed);
    }

    public String toString() {
        return "InventoryAdjustmentResult(adjustmentId=" + this.adjustmentId + ", movementId=" + this.movementId + ", productId=" + this.productId + ", warehouseId=" + this.warehouseId + ", adjustmentType=" + this.adjustmentType + ", quantity=" + this.quantity + ", quantityBefore=" + this.quantityBefore + ", quantityAfter=" + this.quantityAfter + ", unitCostUsdCents=" + this.unitCostUsdCents + ", costUsdCents=" + this.costUsdCents + ", reason=" + this.reason + ", replayed=" + this.replayed + ")";
    }

    public InventoryAdjustmentResult(String adjustmentId, String movementId, String productId, String warehouseId, String adjustmentType, BigDecimal quantity, BigDecimal quantityBefore, BigDecimal quantityAfter, Long l, Long l2, String reason, boolean z) {
        Intrinsics.checkNotNullParameter(adjustmentId, "adjustmentId");
        Intrinsics.checkNotNullParameter(movementId, "movementId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(warehouseId, "warehouseId");
        Intrinsics.checkNotNullParameter(adjustmentType, "adjustmentType");
        Intrinsics.checkNotNullParameter(quantity, "quantity");
        Intrinsics.checkNotNullParameter(quantityBefore, "quantityBefore");
        Intrinsics.checkNotNullParameter(quantityAfter, "quantityAfter");
        Intrinsics.checkNotNullParameter(reason, "reason");
        this.adjustmentId = adjustmentId;
        this.movementId = movementId;
        this.productId = productId;
        this.warehouseId = warehouseId;
        this.adjustmentType = adjustmentType;
        this.quantity = quantity;
        this.quantityBefore = quantityBefore;
        this.quantityAfter = quantityAfter;
        this.unitCostUsdCents = l;
        this.costUsdCents = l2;
        this.reason = reason;
        this.replayed = z;
    }

    public final String getAdjustmentId() {
        return this.adjustmentId;
    }

    public final String getMovementId() {
        return this.movementId;
    }

    public final String getProductId() {
        return this.productId;
    }

    public final String getWarehouseId() {
        return this.warehouseId;
    }

    public final String getAdjustmentType() {
        return this.adjustmentType;
    }

    public final BigDecimal getQuantity() {
        return this.quantity;
    }

    public final BigDecimal getQuantityBefore() {
        return this.quantityBefore;
    }

    public final BigDecimal getQuantityAfter() {
        return this.quantityAfter;
    }

    public final Long getUnitCostUsdCents() {
        return this.unitCostUsdCents;
    }

    public final Long getCostUsdCents() {
        return this.costUsdCents;
    }

    public final String getReason() {
        return this.reason;
    }

    public final boolean getReplayed() {
        return this.replayed;
    }
}
