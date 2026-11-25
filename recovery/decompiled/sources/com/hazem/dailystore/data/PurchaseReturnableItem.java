package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PurchaseReturnRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\nH\u00c6\u0003J\t\u0010 \u001a\u00020\fH\u00c6\u0003JO\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u00c6\u0001J\u0013\u0010\"\u001a\u00020\f2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010$\u001a\u00020%H\u00d6\u0001J\t\u0010&\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006'"}, d2 = {"Lcom/hazem/dailystore/data/PurchaseReturnableItem;", "", "id", "", "productName", "purchasedQuantity", "Ljava/math/BigDecimal;", "returnedQuantity", "availableQuantity", "unitCostUsdCents", "", "tracksInventory", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/math/BigDecimal;Ljava/math/BigDecimal;Ljava/math/BigDecimal;JZ)V", "getId", "()Ljava/lang/String;", "getProductName", "getPurchasedQuantity", "()Ljava/math/BigDecimal;", "getReturnedQuantity", "getAvailableQuantity", "getUnitCostUsdCents", "()J", "getTracksInventory", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class PurchaseReturnableItem {
    private final BigDecimal availableQuantity;
    private final String id;
    private final String productName;
    private final BigDecimal purchasedQuantity;
    private final BigDecimal returnedQuantity;
    private final boolean tracksInventory;
    private final long unitCostUsdCents;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getProductName() {
        return this.productName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final BigDecimal getPurchasedQuantity() {
        return this.purchasedQuantity;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final BigDecimal getReturnedQuantity() {
        return this.returnedQuantity;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final BigDecimal getAvailableQuantity() {
        return this.availableQuantity;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getUnitCostUsdCents() {
        return this.unitCostUsdCents;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getTracksInventory() {
        return this.tracksInventory;
    }

    public final PurchaseReturnableItem copy(String id, String productName, BigDecimal purchasedQuantity, BigDecimal returnedQuantity, BigDecimal availableQuantity, long unitCostUsdCents, boolean tracksInventory) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(productName, "productName");
        Intrinsics.checkNotNullParameter(purchasedQuantity, "purchasedQuantity");
        Intrinsics.checkNotNullParameter(returnedQuantity, "returnedQuantity");
        Intrinsics.checkNotNullParameter(availableQuantity, "availableQuantity");
        return new PurchaseReturnableItem(id, productName, purchasedQuantity, returnedQuantity, availableQuantity, unitCostUsdCents, tracksInventory);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PurchaseReturnableItem)) {
            return false;
        }
        PurchaseReturnableItem purchaseReturnableItem = (PurchaseReturnableItem) other;
        return Intrinsics.areEqual(this.id, purchaseReturnableItem.id) && Intrinsics.areEqual(this.productName, purchaseReturnableItem.productName) && Intrinsics.areEqual(this.purchasedQuantity, purchaseReturnableItem.purchasedQuantity) && Intrinsics.areEqual(this.returnedQuantity, purchaseReturnableItem.returnedQuantity) && Intrinsics.areEqual(this.availableQuantity, purchaseReturnableItem.availableQuantity) && this.unitCostUsdCents == purchaseReturnableItem.unitCostUsdCents && this.tracksInventory == purchaseReturnableItem.tracksInventory;
    }

    public int hashCode() {
        return (((((((((((this.id.hashCode() * 31) + this.productName.hashCode()) * 31) + this.purchasedQuantity.hashCode()) * 31) + this.returnedQuantity.hashCode()) * 31) + this.availableQuantity.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.unitCostUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.tracksInventory);
    }

    public String toString() {
        return "PurchaseReturnableItem(id=" + this.id + ", productName=" + this.productName + ", purchasedQuantity=" + this.purchasedQuantity + ", returnedQuantity=" + this.returnedQuantity + ", availableQuantity=" + this.availableQuantity + ", unitCostUsdCents=" + this.unitCostUsdCents + ", tracksInventory=" + this.tracksInventory + ")";
    }

    public PurchaseReturnableItem(String id, String productName, BigDecimal purchasedQuantity, BigDecimal returnedQuantity, BigDecimal availableQuantity, long j, boolean z) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(productName, "productName");
        Intrinsics.checkNotNullParameter(purchasedQuantity, "purchasedQuantity");
        Intrinsics.checkNotNullParameter(returnedQuantity, "returnedQuantity");
        Intrinsics.checkNotNullParameter(availableQuantity, "availableQuantity");
        this.id = id;
        this.productName = productName;
        this.purchasedQuantity = purchasedQuantity;
        this.returnedQuantity = returnedQuantity;
        this.availableQuantity = availableQuantity;
        this.unitCostUsdCents = j;
        this.tracksInventory = z;
    }

    public final String getId() {
        return this.id;
    }

    public final String getProductName() {
        return this.productName;
    }

    public final BigDecimal getPurchasedQuantity() {
        return this.purchasedQuantity;
    }

    public final BigDecimal getAvailableQuantity() {
        return this.availableQuantity;
    }

    public final BigDecimal getReturnedQuantity() {
        return this.returnedQuantity;
    }

    public final boolean getTracksInventory() {
        return this.tracksInventory;
    }

    public final long getUnitCostUsdCents() {
        return this.unitCostUsdCents;
    }
}
