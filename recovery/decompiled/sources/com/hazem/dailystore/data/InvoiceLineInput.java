package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InvoiceRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0007H\u00c6\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001a"}, d2 = {"Lcom/hazem/dailystore/data/InvoiceLineInput;", "", "productId", "", "quantity", "Ljava/math/BigDecimal;", "unitPriceUsdCents", "", "<init>", "(Ljava/lang/String;Ljava/math/BigDecimal;J)V", "getProductId", "()Ljava/lang/String;", "getQuantity", "()Ljava/math/BigDecimal;", "getUnitPriceUsdCents", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class InvoiceLineInput {
    private final String productId;
    private final BigDecimal quantity;
    private final long unitPriceUsdCents;

    public static /* synthetic */ InvoiceLineInput copy$default(InvoiceLineInput invoiceLineInput, String str, BigDecimal bigDecimal, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = invoiceLineInput.productId;
        }
        if ((i & 2) != 0) {
            bigDecimal = invoiceLineInput.quantity;
        }
        if ((i & 4) != 0) {
            j = invoiceLineInput.unitPriceUsdCents;
        }
        return invoiceLineInput.copy(str, bigDecimal, j);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getProductId() {
        return this.productId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final BigDecimal getQuantity() {
        return this.quantity;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getUnitPriceUsdCents() {
        return this.unitPriceUsdCents;
    }

    public final InvoiceLineInput copy(String productId, BigDecimal quantity, long unitPriceUsdCents) {
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(quantity, "quantity");
        return new InvoiceLineInput(productId, quantity, unitPriceUsdCents);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InvoiceLineInput)) {
            return false;
        }
        InvoiceLineInput invoiceLineInput = (InvoiceLineInput) other;
        return Intrinsics.areEqual(this.productId, invoiceLineInput.productId) && Intrinsics.areEqual(this.quantity, invoiceLineInput.quantity) && this.unitPriceUsdCents == invoiceLineInput.unitPriceUsdCents;
    }

    public int hashCode() {
        return (((this.productId.hashCode() * 31) + this.quantity.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.unitPriceUsdCents);
    }

    public String toString() {
        return "InvoiceLineInput(productId=" + this.productId + ", quantity=" + this.quantity + ", unitPriceUsdCents=" + this.unitPriceUsdCents + ")";
    }

    public InvoiceLineInput(String productId, BigDecimal quantity, long j) {
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(quantity, "quantity");
        this.productId = productId;
        this.quantity = quantity;
        this.unitPriceUsdCents = j;
    }

    public final String getProductId() {
        return this.productId;
    }

    public final BigDecimal getQuantity() {
        return this.quantity;
    }

    public final long getUnitPriceUsdCents() {
        return this.unitPriceUsdCents;
    }
}
