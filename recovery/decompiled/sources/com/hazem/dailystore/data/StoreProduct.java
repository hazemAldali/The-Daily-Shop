package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StoreRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b \n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Be\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003J\t\u0010'\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\u000bH\u00c6\u0003J\t\u0010*\u001a\u00020\u000bH\u00c6\u0003J\t\u0010+\u001a\u00020\u000eH\u00c6\u0003J\t\u0010,\u001a\u00020\u0010H\u00c6\u0003J}\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u00c6\u0001J\u0013\u0010.\u001a\u00020\u000e2\b\u0010/\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00100\u001a\u000201H\u00d6\u0001J\t\u00102\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\f\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!\u00a8\u00063"}, d2 = {"Lcom/hazem/dailystore/data/StoreProduct;", "", "id", "", "name", "barcode", "sku", "unitName", "unitSymbol", "categoryName", "purchaseCostUsdCents", "", "salePriceUsdCents", "tracksInventory", "", "stock", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJZD)V", "getId", "()Ljava/lang/String;", "getName", "getBarcode", "getSku", "getUnitName", "getUnitSymbol", "getCategoryName", "getPurchaseCostUsdCents", "()J", "getSalePriceUsdCents", "getTracksInventory", "()Z", "getStock", "()D", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class StoreProduct {
    private final String barcode;
    private final String categoryName;
    private final String id;
    private final String name;
    private final long purchaseCostUsdCents;
    private final long salePriceUsdCents;
    private final String sku;
    private final double stock;
    private final boolean tracksInventory;
    private final String unitName;
    private final String unitSymbol;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final boolean getTracksInventory() {
        return this.tracksInventory;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final double getStock() {
        return this.stock;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getBarcode() {
        return this.barcode;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getSku() {
        return this.sku;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getUnitName() {
        return this.unitName;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getUnitSymbol() {
        return this.unitSymbol;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getCategoryName() {
        return this.categoryName;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final long getPurchaseCostUsdCents() {
        return this.purchaseCostUsdCents;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final long getSalePriceUsdCents() {
        return this.salePriceUsdCents;
    }

    public final StoreProduct copy(String id, String name, String barcode, String sku, String unitName, String unitSymbol, String categoryName, long purchaseCostUsdCents, long salePriceUsdCents, boolean tracksInventory, double stock) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(unitName, "unitName");
        Intrinsics.checkNotNullParameter(unitSymbol, "unitSymbol");
        return new StoreProduct(id, name, barcode, sku, unitName, unitSymbol, categoryName, purchaseCostUsdCents, salePriceUsdCents, tracksInventory, stock);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StoreProduct)) {
            return false;
        }
        StoreProduct storeProduct = (StoreProduct) other;
        return Intrinsics.areEqual(this.id, storeProduct.id) && Intrinsics.areEqual(this.name, storeProduct.name) && Intrinsics.areEqual(this.barcode, storeProduct.barcode) && Intrinsics.areEqual(this.sku, storeProduct.sku) && Intrinsics.areEqual(this.unitName, storeProduct.unitName) && Intrinsics.areEqual(this.unitSymbol, storeProduct.unitSymbol) && Intrinsics.areEqual(this.categoryName, storeProduct.categoryName) && this.purchaseCostUsdCents == storeProduct.purchaseCostUsdCents && this.salePriceUsdCents == storeProduct.salePriceUsdCents && this.tracksInventory == storeProduct.tracksInventory && Double.compare(this.stock, storeProduct.stock) == 0;
    }

    public int hashCode() {
        int iHashCode = ((this.id.hashCode() * 31) + this.name.hashCode()) * 31;
        String str = this.barcode;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.sku;
        int iHashCode3 = (((((iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31) + this.unitName.hashCode()) * 31) + this.unitSymbol.hashCode()) * 31;
        String str3 = this.categoryName;
        return ((((((((iHashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.purchaseCostUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.salePriceUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.tracksInventory)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.stock);
    }

    public String toString() {
        return "StoreProduct(id=" + this.id + ", name=" + this.name + ", barcode=" + this.barcode + ", sku=" + this.sku + ", unitName=" + this.unitName + ", unitSymbol=" + this.unitSymbol + ", categoryName=" + this.categoryName + ", purchaseCostUsdCents=" + this.purchaseCostUsdCents + ", salePriceUsdCents=" + this.salePriceUsdCents + ", tracksInventory=" + this.tracksInventory + ", stock=" + this.stock + ")";
    }

    public StoreProduct(String id, String name, String str, String str2, String unitName, String unitSymbol, String str3, long j, long j2, boolean z, double d) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(unitName, "unitName");
        Intrinsics.checkNotNullParameter(unitSymbol, "unitSymbol");
        this.id = id;
        this.name = name;
        this.barcode = str;
        this.sku = str2;
        this.unitName = unitName;
        this.unitSymbol = unitSymbol;
        this.categoryName = str3;
        this.purchaseCostUsdCents = j;
        this.salePriceUsdCents = j2;
        this.tracksInventory = z;
        this.stock = d;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getBarcode() {
        return this.barcode;
    }

    public final String getSku() {
        return this.sku;
    }

    public final String getUnitName() {
        return this.unitName;
    }

    public final String getUnitSymbol() {
        return this.unitSymbol;
    }

    public final String getCategoryName() {
        return this.categoryName;
    }

    public final long getPurchaseCostUsdCents() {
        return this.purchaseCostUsdCents;
    }

    public final long getSalePriceUsdCents() {
        return this.salePriceUsdCents;
    }

    public final boolean getTracksInventory() {
        return this.tracksInventory;
    }

    public final double getStock() {
        return this.stock;
    }
}
