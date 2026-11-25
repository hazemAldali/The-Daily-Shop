package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InventoryValuationRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0006H\u00c6\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0019"}, d2 = {"Lcom/hazem/dailystore/data/DailyInventoryCapital;", "", "current", "Lcom/hazem/dailystore/data/InventoryValuation;", "previousClose", "changeUsdCents", "", "<init>", "(Lcom/hazem/dailystore/data/InventoryValuation;Lcom/hazem/dailystore/data/InventoryValuation;J)V", "getCurrent", "()Lcom/hazem/dailystore/data/InventoryValuation;", "getPreviousClose", "getChangeUsdCents", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class DailyInventoryCapital {
    private final long changeUsdCents;
    private final InventoryValuation current;
    private final InventoryValuation previousClose;

    public static /* synthetic */ DailyInventoryCapital copy$default(DailyInventoryCapital dailyInventoryCapital, InventoryValuation inventoryValuation, InventoryValuation inventoryValuation2, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            inventoryValuation = dailyInventoryCapital.current;
        }
        if ((i & 2) != 0) {
            inventoryValuation2 = dailyInventoryCapital.previousClose;
        }
        if ((i & 4) != 0) {
            j = dailyInventoryCapital.changeUsdCents;
        }
        return dailyInventoryCapital.copy(inventoryValuation, inventoryValuation2, j);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final InventoryValuation getCurrent() {
        return this.current;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final InventoryValuation getPreviousClose() {
        return this.previousClose;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getChangeUsdCents() {
        return this.changeUsdCents;
    }

    public final DailyInventoryCapital copy(InventoryValuation current, InventoryValuation previousClose, long changeUsdCents) {
        Intrinsics.checkNotNullParameter(current, "current");
        Intrinsics.checkNotNullParameter(previousClose, "previousClose");
        return new DailyInventoryCapital(current, previousClose, changeUsdCents);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DailyInventoryCapital)) {
            return false;
        }
        DailyInventoryCapital dailyInventoryCapital = (DailyInventoryCapital) other;
        return Intrinsics.areEqual(this.current, dailyInventoryCapital.current) && Intrinsics.areEqual(this.previousClose, dailyInventoryCapital.previousClose) && this.changeUsdCents == dailyInventoryCapital.changeUsdCents;
    }

    public int hashCode() {
        return (((this.current.hashCode() * 31) + this.previousClose.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.changeUsdCents);
    }

    public String toString() {
        return "DailyInventoryCapital(current=" + this.current + ", previousClose=" + this.previousClose + ", changeUsdCents=" + this.changeUsdCents + ")";
    }

    public DailyInventoryCapital(InventoryValuation current, InventoryValuation previousClose, long j) {
        Intrinsics.checkNotNullParameter(current, "current");
        Intrinsics.checkNotNullParameter(previousClose, "previousClose");
        this.current = current;
        this.previousClose = previousClose;
        this.changeUsdCents = j;
    }

    public final InventoryValuation getCurrent() {
        return this.current;
    }

    public final InventoryValuation getPreviousClose() {
        return this.previousClose;
    }

    public final long getChangeUsdCents() {
        return this.changeUsdCents;
    }
}
