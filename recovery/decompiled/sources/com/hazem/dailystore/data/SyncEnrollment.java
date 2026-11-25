package com.hazem.dailystore.data;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SyncProtocolRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003JS\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001J\t\u0010!\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r\u00a8\u0006\""}, d2 = {"Lcom/hazem/dailystore/data/SyncEnrollment;", "", "deviceId", "", "credential", "storeId", "primaryDeviceId", "storeTimeZoneId", "primaryPublicKeyBase64", "deviceCertificateJson", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDeviceId", "()Ljava/lang/String;", "getCredential", "getStoreId", "getPrimaryDeviceId", "getStoreTimeZoneId", "getPrimaryPublicKeyBase64", "getDeviceCertificateJson", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class SyncEnrollment {
    private final String credential;
    private final String deviceCertificateJson;
    private final String deviceId;
    private final String primaryDeviceId;
    private final String primaryPublicKeyBase64;
    private final String storeId;
    private final String storeTimeZoneId;

    public static /* synthetic */ SyncEnrollment copy$default(SyncEnrollment syncEnrollment, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, Object obj) {
        if ((i & 1) != 0) {
            str = syncEnrollment.deviceId;
        }
        if ((i & 2) != 0) {
            str2 = syncEnrollment.credential;
        }
        String str8 = str2;
        if ((i & 4) != 0) {
            str3 = syncEnrollment.storeId;
        }
        String str9 = str3;
        if ((i & 8) != 0) {
            str4 = syncEnrollment.primaryDeviceId;
        }
        String str10 = str4;
        if ((i & 16) != 0) {
            str5 = syncEnrollment.storeTimeZoneId;
        }
        String str11 = str5;
        if ((i & 32) != 0) {
            str6 = syncEnrollment.primaryPublicKeyBase64;
        }
        String str12 = str6;
        if ((i & 64) != 0) {
            str7 = syncEnrollment.deviceCertificateJson;
        }
        return syncEnrollment.copy(str, str8, str9, str10, str11, str12, str7);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getDeviceId() {
        return this.deviceId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getCredential() {
        return this.credential;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getStoreId() {
        return this.storeId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getPrimaryDeviceId() {
        return this.primaryDeviceId;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getStoreTimeZoneId() {
        return this.storeTimeZoneId;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getPrimaryPublicKeyBase64() {
        return this.primaryPublicKeyBase64;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getDeviceCertificateJson() {
        return this.deviceCertificateJson;
    }

    public final SyncEnrollment copy(String deviceId, String credential, String storeId, String primaryDeviceId, String storeTimeZoneId, String primaryPublicKeyBase64, String deviceCertificateJson) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(credential, "credential");
        Intrinsics.checkNotNullParameter(storeId, "storeId");
        Intrinsics.checkNotNullParameter(primaryDeviceId, "primaryDeviceId");
        Intrinsics.checkNotNullParameter(storeTimeZoneId, "storeTimeZoneId");
        return new SyncEnrollment(deviceId, credential, storeId, primaryDeviceId, storeTimeZoneId, primaryPublicKeyBase64, deviceCertificateJson);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SyncEnrollment)) {
            return false;
        }
        SyncEnrollment syncEnrollment = (SyncEnrollment) other;
        return Intrinsics.areEqual(this.deviceId, syncEnrollment.deviceId) && Intrinsics.areEqual(this.credential, syncEnrollment.credential) && Intrinsics.areEqual(this.storeId, syncEnrollment.storeId) && Intrinsics.areEqual(this.primaryDeviceId, syncEnrollment.primaryDeviceId) && Intrinsics.areEqual(this.storeTimeZoneId, syncEnrollment.storeTimeZoneId) && Intrinsics.areEqual(this.primaryPublicKeyBase64, syncEnrollment.primaryPublicKeyBase64) && Intrinsics.areEqual(this.deviceCertificateJson, syncEnrollment.deviceCertificateJson);
    }

    public int hashCode() {
        int iHashCode = ((((((((this.deviceId.hashCode() * 31) + this.credential.hashCode()) * 31) + this.storeId.hashCode()) * 31) + this.primaryDeviceId.hashCode()) * 31) + this.storeTimeZoneId.hashCode()) * 31;
        String str = this.primaryPublicKeyBase64;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.deviceCertificateJson;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "SyncEnrollment(deviceId=" + this.deviceId + ", credential=" + this.credential + ", storeId=" + this.storeId + ", primaryDeviceId=" + this.primaryDeviceId + ", storeTimeZoneId=" + this.storeTimeZoneId + ", primaryPublicKeyBase64=" + this.primaryPublicKeyBase64 + ", deviceCertificateJson=" + this.deviceCertificateJson + ")";
    }

    public SyncEnrollment(String deviceId, String credential, String storeId, String primaryDeviceId, String storeTimeZoneId, String str, String str2) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(credential, "credential");
        Intrinsics.checkNotNullParameter(storeId, "storeId");
        Intrinsics.checkNotNullParameter(primaryDeviceId, "primaryDeviceId");
        Intrinsics.checkNotNullParameter(storeTimeZoneId, "storeTimeZoneId");
        this.deviceId = deviceId;
        this.credential = credential;
        this.storeId = storeId;
        this.primaryDeviceId = primaryDeviceId;
        this.storeTimeZoneId = storeTimeZoneId;
        this.primaryPublicKeyBase64 = str;
        this.deviceCertificateJson = str2;
    }

    public /* synthetic */ SyncEnrollment(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, (i & 32) != 0 ? null : str6, (i & 64) != 0 ? null : str7);
    }

    public final String getCredential() {
        return this.credential;
    }

    public final String getDeviceCertificateJson() {
        return this.deviceCertificateJson;
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final String getPrimaryDeviceId() {
        return this.primaryDeviceId;
    }

    public final String getPrimaryPublicKeyBase64() {
        return this.primaryPublicKeyBase64;
    }

    public final String getStoreId() {
        return this.storeId;
    }

    public final String getStoreTimeZoneId() {
        return this.storeTimeZoneId;
    }
}
