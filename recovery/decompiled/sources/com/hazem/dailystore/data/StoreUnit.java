package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StoreRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\bH\u00c6\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\bH\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u001c"}, d2 = {"Lcom/hazem/dailystore/data/StoreUnit;", "", "id", "", "name", "", "symbol", "decimalPlaces", "", "<init>", "(JLjava/lang/String;Ljava/lang/String;I)V", "getId", "()J", "getName", "()Ljava/lang/String;", "getSymbol", "getDecimalPlaces", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class StoreUnit {
    private final int decimalPlaces;
    private final long id;
    private final String name;
    private final String symbol;

    public static /* synthetic */ StoreUnit copy$default(StoreUnit storeUnit, long j, String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j = storeUnit.id;
        }
        long j2 = j;
        if ((i2 & 2) != 0) {
            str = storeUnit.name;
        }
        String str3 = str;
        if ((i2 & 4) != 0) {
            str2 = storeUnit.symbol;
        }
        String str4 = str2;
        if ((i2 & 8) != 0) {
            i = storeUnit.decimalPlaces;
        }
        return storeUnit.copy(j2, str3, str4, i);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getSymbol() {
        return this.symbol;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getDecimalPlaces() {
        return this.decimalPlaces;
    }

    public final StoreUnit copy(long id, String name, String symbol, int decimalPlaces) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(symbol, "symbol");
        return new StoreUnit(id, name, symbol, decimalPlaces);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StoreUnit)) {
            return false;
        }
        StoreUnit storeUnit = (StoreUnit) other;
        return this.id == storeUnit.id && Intrinsics.areEqual(this.name, storeUnit.name) && Intrinsics.areEqual(this.symbol, storeUnit.symbol) && this.decimalPlaces == storeUnit.decimalPlaces;
    }

    public int hashCode() {
        return (((((MainActivity$$ExternalSyntheticBackport0.m(this.id) * 31) + this.name.hashCode()) * 31) + this.symbol.hashCode()) * 31) + this.decimalPlaces;
    }

    public String toString() {
        return "StoreUnit(id=" + this.id + ", name=" + this.name + ", symbol=" + this.symbol + ", decimalPlaces=" + this.decimalPlaces + ")";
    }

    public StoreUnit(long j, String name, String symbol, int i) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(symbol, "symbol");
        this.id = j;
        this.name = name;
        this.symbol = symbol;
        this.decimalPlaces = i;
    }

    public final int getDecimalPlaces() {
        return this.decimalPlaces;
    }

    public final long getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getSymbol() {
        return this.symbol;
    }
}
