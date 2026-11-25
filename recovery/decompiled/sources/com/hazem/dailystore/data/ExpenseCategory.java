package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OperatingExpenseRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0006H\u00c6\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\f\u00a8\u0006\u0016"}, d2 = {"Lcom/hazem/dailystore/data/ExpenseCategory;", "", "id", "", "name", "isActive", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getId", "()Ljava/lang/String;", "getName", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class ExpenseCategory {
    private final String id;
    private final boolean isActive;
    private final String name;

    public static /* synthetic */ ExpenseCategory copy$default(ExpenseCategory expenseCategory, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = expenseCategory.id;
        }
        if ((i & 2) != 0) {
            str2 = expenseCategory.name;
        }
        if ((i & 4) != 0) {
            z = expenseCategory.isActive;
        }
        return expenseCategory.copy(str, str2, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsActive() {
        return this.isActive;
    }

    public final ExpenseCategory copy(String id, String name, boolean isActive) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        return new ExpenseCategory(id, name, isActive);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ExpenseCategory)) {
            return false;
        }
        ExpenseCategory expenseCategory = (ExpenseCategory) other;
        return Intrinsics.areEqual(this.id, expenseCategory.id) && Intrinsics.areEqual(this.name, expenseCategory.name) && this.isActive == expenseCategory.isActive;
    }

    public int hashCode() {
        return (((this.id.hashCode() * 31) + this.name.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.isActive);
    }

    public String toString() {
        return "ExpenseCategory(id=" + this.id + ", name=" + this.name + ", isActive=" + this.isActive + ")";
    }

    public ExpenseCategory(String id, String name, boolean z) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        this.id = id;
        this.name = name;
        this.isActive = z;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final boolean isActive() {
        return this.isActive;
    }
}
