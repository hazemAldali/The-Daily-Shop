package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuthRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\bH\u00c6\u0003J\t\u0010\u0018\u001a\u00020\bH\u00c6\u0003JE\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u001a\u001a\u00020\b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0011R\u0011\u0010\t\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011\u00a8\u0006\u001f"}, d2 = {"Lcom/hazem/dailystore/data/ManagedUserSummary;", "", "id", "", "username", "displayName", "roleKey", "isActive", "", "mustChangePassword", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)V", "getId", "()Ljava/lang/String;", "getUsername", "getDisplayName", "getRoleKey", "()Z", "getMustChangePassword", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class ManagedUserSummary {
    private final String displayName;
    private final String id;
    private final boolean isActive;
    private final boolean mustChangePassword;
    private final String roleKey;
    private final String username;

    public static /* synthetic */ ManagedUserSummary copy$default(ManagedUserSummary managedUserSummary, String str, String str2, String str3, String str4, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = managedUserSummary.id;
        }
        if ((i & 2) != 0) {
            str2 = managedUserSummary.username;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = managedUserSummary.displayName;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            str4 = managedUserSummary.roleKey;
        }
        String str7 = str4;
        if ((i & 16) != 0) {
            z = managedUserSummary.isActive;
        }
        boolean z3 = z;
        if ((i & 32) != 0) {
            z2 = managedUserSummary.mustChangePassword;
        }
        return managedUserSummary.copy(str, str5, str6, str7, z3, z2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getUsername() {
        return this.username;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDisplayName() {
        return this.displayName;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getRoleKey() {
        return this.roleKey;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getIsActive() {
        return this.isActive;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getMustChangePassword() {
        return this.mustChangePassword;
    }

    public final ManagedUserSummary copy(String id, String username, String displayName, String roleKey, boolean isActive, boolean mustChangePassword) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        Intrinsics.checkNotNullParameter(roleKey, "roleKey");
        return new ManagedUserSummary(id, username, displayName, roleKey, isActive, mustChangePassword);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ManagedUserSummary)) {
            return false;
        }
        ManagedUserSummary managedUserSummary = (ManagedUserSummary) other;
        return Intrinsics.areEqual(this.id, managedUserSummary.id) && Intrinsics.areEqual(this.username, managedUserSummary.username) && Intrinsics.areEqual(this.displayName, managedUserSummary.displayName) && Intrinsics.areEqual(this.roleKey, managedUserSummary.roleKey) && this.isActive == managedUserSummary.isActive && this.mustChangePassword == managedUserSummary.mustChangePassword;
    }

    public int hashCode() {
        return (((((((((this.id.hashCode() * 31) + this.username.hashCode()) * 31) + this.displayName.hashCode()) * 31) + this.roleKey.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.isActive)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.mustChangePassword);
    }

    public String toString() {
        return "ManagedUserSummary(id=" + this.id + ", username=" + this.username + ", displayName=" + this.displayName + ", roleKey=" + this.roleKey + ", isActive=" + this.isActive + ", mustChangePassword=" + this.mustChangePassword + ")";
    }

    public ManagedUserSummary(String id, String username, String displayName, String roleKey, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        Intrinsics.checkNotNullParameter(roleKey, "roleKey");
        this.id = id;
        this.username = username;
        this.displayName = displayName;
        this.roleKey = roleKey;
        this.isActive = z;
        this.mustChangePassword = z2;
    }

    public final String getDisplayName() {
        return this.displayName;
    }

    public final String getId() {
        return this.id;
    }

    public final String getUsername() {
        return this.username;
    }

    public final boolean getMustChangePassword() {
        return this.mustChangePassword;
    }

    public final String getRoleKey() {
        return this.roleKey;
    }

    public final boolean isActive() {
        return this.isActive;
    }
}
