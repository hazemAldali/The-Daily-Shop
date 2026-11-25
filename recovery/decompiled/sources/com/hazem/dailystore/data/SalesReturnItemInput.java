package com.hazem.dailystore.data;

import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SalesReturnRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0015"}, d2 = {"Lcom/hazem/dailystore/data/SalesReturnItemInput;", "", "originalSalesItemId", "", "quantity", "Ljava/math/BigDecimal;", "<init>", "(Ljava/lang/String;Ljava/math/BigDecimal;)V", "getOriginalSalesItemId", "()Ljava/lang/String;", "getQuantity", "()Ljava/math/BigDecimal;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class SalesReturnItemInput {
    private final String originalSalesItemId;
    private final BigDecimal quantity;

    public static /* synthetic */ SalesReturnItemInput copy$default(SalesReturnItemInput salesReturnItemInput, String str, BigDecimal bigDecimal, int i, Object obj) {
        if ((i & 1) != 0) {
            str = salesReturnItemInput.originalSalesItemId;
        }
        if ((i & 2) != 0) {
            bigDecimal = salesReturnItemInput.quantity;
        }
        return salesReturnItemInput.copy(str, bigDecimal);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getOriginalSalesItemId() {
        return this.originalSalesItemId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final BigDecimal getQuantity() {
        return this.quantity;
    }

    public final SalesReturnItemInput copy(String originalSalesItemId, BigDecimal quantity) {
        Intrinsics.checkNotNullParameter(originalSalesItemId, "originalSalesItemId");
        Intrinsics.checkNotNullParameter(quantity, "quantity");
        return new SalesReturnItemInput(originalSalesItemId, quantity);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SalesReturnItemInput)) {
            return false;
        }
        SalesReturnItemInput salesReturnItemInput = (SalesReturnItemInput) other;
        return Intrinsics.areEqual(this.originalSalesItemId, salesReturnItemInput.originalSalesItemId) && Intrinsics.areEqual(this.quantity, salesReturnItemInput.quantity);
    }

    public int hashCode() {
        return (this.originalSalesItemId.hashCode() * 31) + this.quantity.hashCode();
    }

    public String toString() {
        return "SalesReturnItemInput(originalSalesItemId=" + this.originalSalesItemId + ", quantity=" + this.quantity + ")";
    }

    public SalesReturnItemInput(String originalSalesItemId, BigDecimal quantity) {
        Intrinsics.checkNotNullParameter(originalSalesItemId, "originalSalesItemId");
        Intrinsics.checkNotNullParameter(quantity, "quantity");
        this.originalSalesItemId = originalSalesItemId;
        this.quantity = quantity;
    }

    public final String getOriginalSalesItemId() {
        return this.originalSalesItemId;
    }

    public final BigDecimal getQuantity() {
        return this.quantity;
    }
}
