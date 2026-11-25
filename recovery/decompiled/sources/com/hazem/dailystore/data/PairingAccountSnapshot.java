package com.hazem.dailystore.data;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuthRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u00a2\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003JE\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f\u00a8\u0006\u001f"}, d2 = {"Lcom/hazem/dailystore/data/PairingAccountSnapshot;", "", "id", "", "username", "displayName", "passwordHash", "passwordSalt", "roleKey", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getUsername", "getDisplayName", "getPasswordHash", "getPasswordSalt", "getRoleKey", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class PairingAccountSnapshot {
    private final String displayName;
    private final String id;
    private final String passwordHash;
    private final String passwordSalt;
    private final String roleKey;
    private final String username;

    public static /* synthetic */ PairingAccountSnapshot copy$default(PairingAccountSnapshot pairingAccountSnapshot, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = pairingAccountSnapshot.id;
        }
        if ((i & 2) != 0) {
            str2 = pairingAccountSnapshot.username;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            str3 = pairingAccountSnapshot.displayName;
        }
        String str8 = str3;
        if ((i & 8) != 0) {
            str4 = pairingAccountSnapshot.passwordHash;
        }
        String str9 = str4;
        if ((i & 16) != 0) {
            str5 = pairingAccountSnapshot.passwordSalt;
        }
        String str10 = str5;
        if ((i & 32) != 0) {
            str6 = pairingAccountSnapshot.roleKey;
        }
        return pairingAccountSnapshot.copy(str, str7, str8, str9, str10, str6);
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
    public final String getPasswordHash() {
        return this.passwordHash;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getPasswordSalt() {
        return this.passwordSalt;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getRoleKey() {
        return this.roleKey;
    }

    public final PairingAccountSnapshot copy(String id, String username, String displayName, String passwordHash, String passwordSalt, String roleKey) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        Intrinsics.checkNotNullParameter(passwordHash, "passwordHash");
        Intrinsics.checkNotNullParameter(passwordSalt, "passwordSalt");
        Intrinsics.checkNotNullParameter(roleKey, "roleKey");
        return new PairingAccountSnapshot(id, username, displayName, passwordHash, passwordSalt, roleKey);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PairingAccountSnapshot)) {
            return false;
        }
        PairingAccountSnapshot pairingAccountSnapshot = (PairingAccountSnapshot) other;
        return Intrinsics.areEqual(this.id, pairingAccountSnapshot.id) && Intrinsics.areEqual(this.username, pairingAccountSnapshot.username) && Intrinsics.areEqual(this.displayName, pairingAccountSnapshot.displayName) && Intrinsics.areEqual(this.passwordHash, pairingAccountSnapshot.passwordHash) && Intrinsics.areEqual(this.passwordSalt, pairingAccountSnapshot.passwordSalt) && Intrinsics.areEqual(this.roleKey, pairingAccountSnapshot.roleKey);
    }

    public int hashCode() {
        return (((((((((this.id.hashCode() * 31) + this.username.hashCode()) * 31) + this.displayName.hashCode()) * 31) + this.passwordHash.hashCode()) * 31) + this.passwordSalt.hashCode()) * 31) + this.roleKey.hashCode();
    }

    public String toString() {
        return "PairingAccountSnapshot(id=" + this.id + ", username=" + this.username + ", displayName=" + this.displayName + ", passwordHash=" + this.passwordHash + ", passwordSalt=" + this.passwordSalt + ", roleKey=" + this.roleKey + ")";
    }

    public PairingAccountSnapshot(String id, String username, String displayName, String passwordHash, String passwordSalt, String roleKey) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        Intrinsics.checkNotNullParameter(passwordHash, "passwordHash");
        Intrinsics.checkNotNullParameter(passwordSalt, "passwordSalt");
        Intrinsics.checkNotNullParameter(roleKey, "roleKey");
        this.id = id;
        this.username = username;
        this.displayName = displayName;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
        this.roleKey = roleKey;
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

    public final String getPasswordHash() {
        return this.passwordHash;
    }

    public final String getPasswordSalt() {
        return this.passwordSalt;
    }

    public final String getRoleKey() {
        return this.roleKey;
    }
}
