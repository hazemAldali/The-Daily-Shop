package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PurchaseReturnRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0015"}, d2 = {"Lcom/hazem/dailystore/data/SupplierRefundAllocationInput;", "", "cashAccountId", "", "amountMinor", "", "<init>", "(Ljava/lang/String;J)V", "getCashAccountId", "()Ljava/lang/String;", "getAmountMinor", "()J", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class SupplierRefundAllocationInput {
    private final long amountMinor;
    private final String cashAccountId;

    public static /* synthetic */ SupplierRefundAllocationInput copy$default(SupplierRefundAllocationInput supplierRefundAllocationInput, String str, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = supplierRefundAllocationInput.cashAccountId;
        }
        if ((i & 2) != 0) {
            j = supplierRefundAllocationInput.amountMinor;
        }
        return supplierRefundAllocationInput.copy(str, j);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getCashAccountId() {
        return this.cashAccountId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getAmountMinor() {
        return this.amountMinor;
    }

    public final SupplierRefundAllocationInput copy(String cashAccountId, long amountMinor) {
        Intrinsics.checkNotNullParameter(cashAccountId, "cashAccountId");
        return new SupplierRefundAllocationInput(cashAccountId, amountMinor);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SupplierRefundAllocationInput)) {
            return false;
        }
        SupplierRefundAllocationInput supplierRefundAllocationInput = (SupplierRefundAllocationInput) other;
        return Intrinsics.areEqual(this.cashAccountId, supplierRefundAllocationInput.cashAccountId) && this.amountMinor == supplierRefundAllocationInput.amountMinor;
    }

    public int hashCode() {
        return (this.cashAccountId.hashCode() * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.amountMinor);
    }

    public String toString() {
        return "SupplierRefundAllocationInput(cashAccountId=" + this.cashAccountId + ", amountMinor=" + this.amountMinor + ")";
    }

    public SupplierRefundAllocationInput(String cashAccountId, long j) {
        Intrinsics.checkNotNullParameter(cashAccountId, "cashAccountId");
        this.cashAccountId = cashAccountId;
        this.amountMinor = j;
    }

    public final long getAmountMinor() {
        return this.amountMinor;
    }

    public final String getCashAccountId() {
        return this.cashAccountId;
    }
}
