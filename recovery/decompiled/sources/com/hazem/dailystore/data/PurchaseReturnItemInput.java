package com.hazem.dailystore.data;

import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PurchaseReturnRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0015"}, d2 = {"Lcom/hazem/dailystore/data/PurchaseReturnItemInput;", "", "originalPurchaseItemId", "", "quantity", "Ljava/math/BigDecimal;", "<init>", "(Ljava/lang/String;Ljava/math/BigDecimal;)V", "getOriginalPurchaseItemId", "()Ljava/lang/String;", "getQuantity", "()Ljava/math/BigDecimal;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class PurchaseReturnItemInput {
    private final String originalPurchaseItemId;
    private final BigDecimal quantity;

    public static /* synthetic */ PurchaseReturnItemInput copy$default(PurchaseReturnItemInput purchaseReturnItemInput, String str, BigDecimal bigDecimal, int i, Object obj) {
        if ((i & 1) != 0) {
            str = purchaseReturnItemInput.originalPurchaseItemId;
        }
        if ((i & 2) != 0) {
            bigDecimal = purchaseReturnItemInput.quantity;
        }
        return purchaseReturnItemInput.copy(str, bigDecimal);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getOriginalPurchaseItemId() {
        return this.originalPurchaseItemId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final BigDecimal getQuantity() {
        return this.quantity;
    }

    public final PurchaseReturnItemInput copy(String originalPurchaseItemId, BigDecimal quantity) {
        Intrinsics.checkNotNullParameter(originalPurchaseItemId, "originalPurchaseItemId");
        Intrinsics.checkNotNullParameter(quantity, "quantity");
        return new PurchaseReturnItemInput(originalPurchaseItemId, quantity);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PurchaseReturnItemInput)) {
            return false;
        }
        PurchaseReturnItemInput purchaseReturnItemInput = (PurchaseReturnItemInput) other;
        return Intrinsics.areEqual(this.originalPurchaseItemId, purchaseReturnItemInput.originalPurchaseItemId) && Intrinsics.areEqual(this.quantity, purchaseReturnItemInput.quantity);
    }

    public int hashCode() {
        return (this.originalPurchaseItemId.hashCode() * 31) + this.quantity.hashCode();
    }

    public String toString() {
        return "PurchaseReturnItemInput(originalPurchaseItemId=" + this.originalPurchaseItemId + ", quantity=" + this.quantity + ")";
    }

    public PurchaseReturnItemInput(String originalPurchaseItemId, BigDecimal quantity) {
        Intrinsics.checkNotNullParameter(originalPurchaseItemId, "originalPurchaseItemId");
        Intrinsics.checkNotNullParameter(quantity, "quantity");
        this.originalPurchaseItemId = originalPurchaseItemId;
        this.quantity = quantity;
    }

    public final String getOriginalPurchaseItemId() {
        return this.originalPurchaseItemId;
    }

    public final BigDecimal getQuantity() {
        return this.quantity;
    }
}
