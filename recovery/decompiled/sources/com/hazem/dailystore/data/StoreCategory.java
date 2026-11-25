package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StoreRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0015"}, d2 = {"Lcom/hazem/dailystore/data/StoreCategory;", "", "id", "", "name", "", "<init>", "(JLjava/lang/String;)V", "getId", "()J", "getName", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class StoreCategory {
    private final long id;
    private final String name;

    public static /* synthetic */ StoreCategory copy$default(StoreCategory storeCategory, long j, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            j = storeCategory.id;
        }
        if ((i & 2) != 0) {
            str = storeCategory.name;
        }
        return storeCategory.copy(j, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final StoreCategory copy(long id, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new StoreCategory(id, name);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StoreCategory)) {
            return false;
        }
        StoreCategory storeCategory = (StoreCategory) other;
        return this.id == storeCategory.id && Intrinsics.areEqual(this.name, storeCategory.name);
    }

    public int hashCode() {
        return (MainActivity$$ExternalSyntheticBackport0.m(this.id) * 31) + this.name.hashCode();
    }

    public String toString() {
        return "StoreCategory(id=" + this.id + ", name=" + this.name + ")";
    }

    public StoreCategory(long j, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.id = j;
        this.name = name;
    }

    public final long getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }
}
