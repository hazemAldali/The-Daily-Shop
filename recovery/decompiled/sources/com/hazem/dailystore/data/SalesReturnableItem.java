package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SalesReturnRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\nH\u00c6\u0003J\t\u0010 \u001a\u00020\fH\u00c6\u0003JO\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u00c6\u0001J\u0013\u0010\"\u001a\u00020\f2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010$\u001a\u00020%H\u00d6\u0001J\t\u0010&\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006'"}, d2 = {"Lcom/hazem/dailystore/data/SalesReturnableItem;", "", "id", "", "productName", "soldQuantity", "Ljava/math/BigDecimal;", "returnedQuantity", "availableQuantity", "unitPriceUsdCents", "", "tracksInventory", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/math/BigDecimal;Ljava/math/BigDecimal;Ljava/math/BigDecimal;JZ)V", "getId", "()Ljava/lang/String;", "getProductName", "getSoldQuantity", "()Ljava/math/BigDecimal;", "getReturnedQuantity", "getAvailableQuantity", "getUnitPriceUsdCents", "()J", "getTracksInventory", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class SalesReturnableItem {
    private final BigDecimal availableQuantity;
    private final String id;
    private final String productName;
    private final BigDecimal returnedQuantity;
    private final BigDecimal soldQuantity;
    private final boolean tracksInventory;
    private final long unitPriceUsdCents;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getProductName() {
        return this.productName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final BigDecimal getSoldQuantity() {
        return this.soldQuantity;
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
    public final long getUnitPriceUsdCents() {
        return this.unitPriceUsdCents;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getTracksInventory() {
        return this.tracksInventory;
    }

    public final SalesReturnableItem copy(String id, String productName, BigDecimal soldQuantity, BigDecimal returnedQuantity, BigDecimal availableQuantity, long unitPriceUsdCents, boolean tracksInventory) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(productName, "productName");
        Intrinsics.checkNotNullParameter(soldQuantity, "soldQuantity");
        Intrinsics.checkNotNullParameter(returnedQuantity, "returnedQuantity");
        Intrinsics.checkNotNullParameter(availableQuantity, "availableQuantity");
        return new SalesReturnableItem(id, productName, soldQuantity, returnedQuantity, availableQuantity, unitPriceUsdCents, tracksInventory);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SalesReturnableItem)) {
            return false;
        }
        SalesReturnableItem salesReturnableItem = (SalesReturnableItem) other;
        return Intrinsics.areEqual(this.id, salesReturnableItem.id) && Intrinsics.areEqual(this.productName, salesReturnableItem.productName) && Intrinsics.areEqual(this.soldQuantity, salesReturnableItem.soldQuantity) && Intrinsics.areEqual(this.returnedQuantity, salesReturnableItem.returnedQuantity) && Intrinsics.areEqual(this.availableQuantity, salesReturnableItem.availableQuantity) && this.unitPriceUsdCents == salesReturnableItem.unitPriceUsdCents && this.tracksInventory == salesReturnableItem.tracksInventory;
    }

    public int hashCode() {
        return (((((((((((this.id.hashCode() * 31) + this.productName.hashCode()) * 31) + this.soldQuantity.hashCode()) * 31) + this.returnedQuantity.hashCode()) * 31) + this.availableQuantity.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.unitPriceUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.tracksInventory);
    }

    public String toString() {
        return "SalesReturnableItem(id=" + this.id + ", productName=" + this.productName + ", soldQuantity=" + this.soldQuantity + ", returnedQuantity=" + this.returnedQuantity + ", availableQuantity=" + this.availableQuantity + ", unitPriceUsdCents=" + this.unitPriceUsdCents + ", tracksInventory=" + this.tracksInventory + ")";
    }

    public SalesReturnableItem(String id, String productName, BigDecimal soldQuantity, BigDecimal returnedQuantity, BigDecimal availableQuantity, long j, boolean z) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(productName, "productName");
        Intrinsics.checkNotNullParameter(soldQuantity, "soldQuantity");
        Intrinsics.checkNotNullParameter(returnedQuantity, "returnedQuantity");
        Intrinsics.checkNotNullParameter(availableQuantity, "availableQuantity");
        this.id = id;
        this.productName = productName;
        this.soldQuantity = soldQuantity;
        this.returnedQuantity = returnedQuantity;
        this.availableQuantity = availableQuantity;
        this.unitPriceUsdCents = j;
        this.tracksInventory = z;
    }

    public final String getId() {
        return this.id;
    }

    public final String getProductName() {
        return this.productName;
    }

    public final BigDecimal getSoldQuantity() {
        return this.soldQuantity;
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

    public final long getUnitPriceUsdCents() {
        return this.unitPriceUsdCents;
    }
}
