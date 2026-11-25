package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PermissionPolicy.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0006H\u00c6\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0017"}, d2 = {"Lcom/hazem/dailystore/data/RolePermissionSetting;", "", "key", "", "description", "allowed", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getKey", "()Ljava/lang/String;", "getDescription", "getAllowed", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class RolePermissionSetting {
    private final boolean allowed;
    private final String description;
    private final String key;

    public static /* synthetic */ RolePermissionSetting copy$default(RolePermissionSetting rolePermissionSetting, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = rolePermissionSetting.key;
        }
        if ((i & 2) != 0) {
            str2 = rolePermissionSetting.description;
        }
        if ((i & 4) != 0) {
            z = rolePermissionSetting.allowed;
        }
        return rolePermissionSetting.copy(str, str2, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getKey() {
        return this.key;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getAllowed() {
        return this.allowed;
    }

    public final RolePermissionSetting copy(String key, String description, boolean allowed) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(description, "description");
        return new RolePermissionSetting(key, description, allowed);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RolePermissionSetting)) {
            return false;
        }
        RolePermissionSetting rolePermissionSetting = (RolePermissionSetting) other;
        return Intrinsics.areEqual(this.key, rolePermissionSetting.key) && Intrinsics.areEqual(this.description, rolePermissionSetting.description) && this.allowed == rolePermissionSetting.allowed;
    }

    public int hashCode() {
        return (((this.key.hashCode() * 31) + this.description.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.allowed);
    }

    public String toString() {
        return "RolePermissionSetting(key=" + this.key + ", description=" + this.description + ", allowed=" + this.allowed + ")";
    }

    public RolePermissionSetting(String key, String description, boolean z) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(description, "description");
        this.key = key;
        this.description = description;
        this.allowed = z;
    }

    public final boolean getAllowed() {
        return this.allowed;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getKey() {
        return this.key;
    }
}
