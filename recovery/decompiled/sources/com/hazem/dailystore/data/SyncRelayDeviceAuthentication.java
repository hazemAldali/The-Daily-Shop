package com.hazem.dailystore.data;

import java.security.PrivateKey;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SyncRelayWebSocketClient.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0015"}, d2 = {"Lcom/hazem/dailystore/data/SyncRelayDeviceAuthentication;", "", "serial", "", "privateKey", "Ljava/security/PrivateKey;", "<init>", "(Ljava/lang/String;Ljava/security/PrivateKey;)V", "getSerial", "()Ljava/lang/String;", "getPrivateKey", "()Ljava/security/PrivateKey;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class SyncRelayDeviceAuthentication {
    private final PrivateKey privateKey;
    private final String serial;

    public static /* synthetic */ SyncRelayDeviceAuthentication copy$default(SyncRelayDeviceAuthentication syncRelayDeviceAuthentication, String str, PrivateKey privateKey, int i, Object obj) {
        if ((i & 1) != 0) {
            str = syncRelayDeviceAuthentication.serial;
        }
        if ((i & 2) != 0) {
            privateKey = syncRelayDeviceAuthentication.privateKey;
        }
        return syncRelayDeviceAuthentication.copy(str, privateKey);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getSerial() {
        return this.serial;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    public final SyncRelayDeviceAuthentication copy(String serial, PrivateKey privateKey) {
        Intrinsics.checkNotNullParameter(serial, "serial");
        Intrinsics.checkNotNullParameter(privateKey, "privateKey");
        return new SyncRelayDeviceAuthentication(serial, privateKey);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SyncRelayDeviceAuthentication)) {
            return false;
        }
        SyncRelayDeviceAuthentication syncRelayDeviceAuthentication = (SyncRelayDeviceAuthentication) other;
        return Intrinsics.areEqual(this.serial, syncRelayDeviceAuthentication.serial) && Intrinsics.areEqual(this.privateKey, syncRelayDeviceAuthentication.privateKey);
    }

    public int hashCode() {
        return (this.serial.hashCode() * 31) + this.privateKey.hashCode();
    }

    public String toString() {
        return "SyncRelayDeviceAuthentication(serial=" + this.serial + ", privateKey=" + this.privateKey + ")";
    }

    public SyncRelayDeviceAuthentication(String serial, PrivateKey privateKey) {
        Intrinsics.checkNotNullParameter(serial, "serial");
        Intrinsics.checkNotNullParameter(privateKey, "privateKey");
        this.serial = serial;
        this.privateKey = privateKey;
    }

    public final PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    public final String getSerial() {
        return this.serial;
    }
}
