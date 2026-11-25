package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuthRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\bH\u00c6\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\u001bH\u00d6\u0001J\t\u0010\u001c\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u001d"}, d2 = {"Lcom/hazem/dailystore/data/AuthenticatedUser;", "", "id", "", "username", "displayName", "roleKey", "mustChangePassword", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getId", "()Ljava/lang/String;", "getUsername", "getDisplayName", "getRoleKey", "getMustChangePassword", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class AuthenticatedUser {
    private final String displayName;
    private final String id;
    private final boolean mustChangePassword;
    private final String roleKey;
    private final String username;

    public static /* synthetic */ AuthenticatedUser copy$default(AuthenticatedUser authenticatedUser, String str, String str2, String str3, String str4, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = authenticatedUser.id;
        }
        if ((i & 2) != 0) {
            str2 = authenticatedUser.username;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = authenticatedUser.displayName;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            str4 = authenticatedUser.roleKey;
        }
        String str7 = str4;
        if ((i & 16) != 0) {
            z = authenticatedUser.mustChangePassword;
        }
        return authenticatedUser.copy(str, str5, str6, str7, z);
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
    public final boolean getMustChangePassword() {
        return this.mustChangePassword;
    }

    public final AuthenticatedUser copy(String id, String username, String displayName, String roleKey, boolean mustChangePassword) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        Intrinsics.checkNotNullParameter(roleKey, "roleKey");
        return new AuthenticatedUser(id, username, displayName, roleKey, mustChangePassword);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AuthenticatedUser)) {
            return false;
        }
        AuthenticatedUser authenticatedUser = (AuthenticatedUser) other;
        return Intrinsics.areEqual(this.id, authenticatedUser.id) && Intrinsics.areEqual(this.username, authenticatedUser.username) && Intrinsics.areEqual(this.displayName, authenticatedUser.displayName) && Intrinsics.areEqual(this.roleKey, authenticatedUser.roleKey) && this.mustChangePassword == authenticatedUser.mustChangePassword;
    }

    public int hashCode() {
        return (((((((this.id.hashCode() * 31) + this.username.hashCode()) * 31) + this.displayName.hashCode()) * 31) + this.roleKey.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.mustChangePassword);
    }

    public String toString() {
        return "AuthenticatedUser(id=" + this.id + ", username=" + this.username + ", displayName=" + this.displayName + ", roleKey=" + this.roleKey + ", mustChangePassword=" + this.mustChangePassword + ")";
    }

    public AuthenticatedUser(String id, String username, String displayName, String roleKey, boolean z) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        Intrinsics.checkNotNullParameter(roleKey, "roleKey");
        this.id = id;
        this.username = username;
        this.displayName = displayName;
        this.roleKey = roleKey;
        this.mustChangePassword = z;
    }

    public /* synthetic */ AuthenticatedUser(String str, String str2, String str3, String str4, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, (i & 16) != 0 ? false : z);
    }

    public final String getDisplayName() {
        return this.displayName;
    }

    public final String getId() {
        return this.id;
    }

    public final boolean getMustChangePassword() {
        return this.mustChangePassword;
    }

    public final String getRoleKey() {
        return this.roleKey;
    }

    public final String getUsername() {
        return this.username;
    }
}
