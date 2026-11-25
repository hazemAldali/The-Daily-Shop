package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SpreadsheetProductImporter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b&\b\u0086\b\u0018\u00002\u00020\u0001Bq\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\f\u001a\u0004\u0018\u00010\t\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0011\u0010\u0012J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\t\u0010&\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0010\u0010)\u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001aJ\u0010\u0010*\u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001aJ\u0010\u0010+\u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001aJ\u0010\u0010,\u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001aJ\u000b\u0010-\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010/\u001a\u00020\u0010H\u00c6\u0003J\u008c\u0001\u00100\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u00c6\u0001\u00a2\u0006\u0002\u00101J\u0013\u00102\u001a\u00020\u00102\b\u00103\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00104\u001a\u00020\u0003H\u00d6\u0001J\t\u00105\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0015\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001aR\u0015\u0010\n\u001a\u0004\u0018\u00010\t\u00a2\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u001c\u0010\u001aR\u0015\u0010\u000b\u001a\u0004\u0018\u00010\t\u00a2\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u001d\u0010\u001aR\u0015\u0010\f\u001a\u0004\u0018\u00010\t\u00a2\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u001e\u0010\u001aR\u0013\u0010\r\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0016R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0016R\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010#\u001a\u00020\u00108F\u00a2\u0006\u0006\u001a\u0004\b$\u0010\"\u00a8\u00066"}, d2 = {"Lcom/hazem/dailystore/data/ProductImportRow;", "", "rowNumber", "", "name", "", "category", "unit", "purchaseCostSyp", "", "salePriceSyp", "purchaseCostUsdCents", "salePriceUsdCents", "sku", "barcode", "tracksInventory", "", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Z)V", "getRowNumber", "()I", "getName", "()Ljava/lang/String;", "getCategory", "getUnit", "getPurchaseCostSyp", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getSalePriceSyp", "getPurchaseCostUsdCents", "getSalePriceUsdCents", "getSku", "getBarcode", "getTracksInventory", "()Z", "usesSypPrices", "getUsesSypPrices", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Z)Lcom/hazem/dailystore/data/ProductImportRow;", "equals", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class ProductImportRow {
    private final String barcode;
    private final String category;
    private final String name;
    private final Long purchaseCostSyp;
    private final Long purchaseCostUsdCents;
    private final int rowNumber;
    private final Long salePriceSyp;
    private final Long salePriceUsdCents;
    private final String sku;
    private final boolean tracksInventory;
    private final String unit;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getRowNumber() {
        return this.rowNumber;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getBarcode() {
        return this.barcode;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final boolean getTracksInventory() {
        return this.tracksInventory;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getCategory() {
        return this.category;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getUnit() {
        return this.unit;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Long getPurchaseCostSyp() {
        return this.purchaseCostSyp;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Long getSalePriceSyp() {
        return this.salePriceSyp;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Long getPurchaseCostUsdCents() {
        return this.purchaseCostUsdCents;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final Long getSalePriceUsdCents() {
        return this.salePriceUsdCents;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getSku() {
        return this.sku;
    }

    public final ProductImportRow copy(int rowNumber, String name, String category, String unit, Long purchaseCostSyp, Long salePriceSyp, Long purchaseCostUsdCents, Long salePriceUsdCents, String sku, String barcode, boolean tracksInventory) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new ProductImportRow(rowNumber, name, category, unit, purchaseCostSyp, salePriceSyp, purchaseCostUsdCents, salePriceUsdCents, sku, barcode, tracksInventory);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProductImportRow)) {
            return false;
        }
        ProductImportRow productImportRow = (ProductImportRow) other;
        return this.rowNumber == productImportRow.rowNumber && Intrinsics.areEqual(this.name, productImportRow.name) && Intrinsics.areEqual(this.category, productImportRow.category) && Intrinsics.areEqual(this.unit, productImportRow.unit) && Intrinsics.areEqual(this.purchaseCostSyp, productImportRow.purchaseCostSyp) && Intrinsics.areEqual(this.salePriceSyp, productImportRow.salePriceSyp) && Intrinsics.areEqual(this.purchaseCostUsdCents, productImportRow.purchaseCostUsdCents) && Intrinsics.areEqual(this.salePriceUsdCents, productImportRow.salePriceUsdCents) && Intrinsics.areEqual(this.sku, productImportRow.sku) && Intrinsics.areEqual(this.barcode, productImportRow.barcode) && this.tracksInventory == productImportRow.tracksInventory;
    }

    public int hashCode() {
        int iHashCode = ((this.rowNumber * 31) + this.name.hashCode()) * 31;
        String str = this.category;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.unit;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Long l = this.purchaseCostSyp;
        int iHashCode4 = (iHashCode3 + (l == null ? 0 : l.hashCode())) * 31;
        Long l2 = this.salePriceSyp;
        int iHashCode5 = (iHashCode4 + (l2 == null ? 0 : l2.hashCode())) * 31;
        Long l3 = this.purchaseCostUsdCents;
        int iHashCode6 = (iHashCode5 + (l3 == null ? 0 : l3.hashCode())) * 31;
        Long l4 = this.salePriceUsdCents;
        int iHashCode7 = (iHashCode6 + (l4 == null ? 0 : l4.hashCode())) * 31;
        String str3 = this.sku;
        int iHashCode8 = (iHashCode7 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.barcode;
        return ((iHashCode8 + (str4 != null ? str4.hashCode() : 0)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.tracksInventory);
    }

    public String toString() {
        return "ProductImportRow(rowNumber=" + this.rowNumber + ", name=" + this.name + ", category=" + this.category + ", unit=" + this.unit + ", purchaseCostSyp=" + this.purchaseCostSyp + ", salePriceSyp=" + this.salePriceSyp + ", purchaseCostUsdCents=" + this.purchaseCostUsdCents + ", salePriceUsdCents=" + this.salePriceUsdCents + ", sku=" + this.sku + ", barcode=" + this.barcode + ", tracksInventory=" + this.tracksInventory + ")";
    }

    public ProductImportRow(int i, String name, String str, String str2, Long l, Long l2, Long l3, Long l4, String str3, String str4, boolean z) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.rowNumber = i;
        this.name = name;
        this.category = str;
        this.unit = str2;
        this.purchaseCostSyp = l;
        this.salePriceSyp = l2;
        this.purchaseCostUsdCents = l3;
        this.salePriceUsdCents = l4;
        this.sku = str3;
        this.barcode = str4;
        this.tracksInventory = z;
    }

    public /* synthetic */ ProductImportRow(int i, String str, String str2, String str3, Long l, Long l2, Long l3, Long l4, String str4, String str5, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, str2, str3, l, l2, l3, l4, str4, str5, (i2 & 1024) != 0 ? true : z);
    }

    public final int getRowNumber() {
        return this.rowNumber;
    }

    public final String getName() {
        return this.name;
    }

    public final String getCategory() {
        return this.category;
    }

    public final String getUnit() {
        return this.unit;
    }

    public final Long getPurchaseCostSyp() {
        return this.purchaseCostSyp;
    }

    public final Long getSalePriceSyp() {
        return this.salePriceSyp;
    }

    public final Long getPurchaseCostUsdCents() {
        return this.purchaseCostUsdCents;
    }

    public final Long getSalePriceUsdCents() {
        return this.salePriceUsdCents;
    }

    public final String getSku() {
        return this.sku;
    }

    public final String getBarcode() {
        return this.barcode;
    }

    public final boolean getTracksInventory() {
        return this.tracksInventory;
    }

    public final boolean getUsesSypPrices() {
        return (this.purchaseCostSyp == null && this.salePriceSyp == null) ? false : true;
    }
}
