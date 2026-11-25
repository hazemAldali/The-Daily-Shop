package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SyncEnvelopeSecurity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b-\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Bw\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\b\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u0005\u0012\u0006\u0010\u0011\u001a\u00020\u0005\u0012\u0006\u0010\u0012\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0013\u0010\u0014J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003J\t\u0010'\u001a\u00020\u0005H\u00c6\u0003J\t\u0010(\u001a\u00020\u0005H\u00c6\u0003J\t\u0010)\u001a\u00020\bH\u00c6\u0003J\t\u0010*\u001a\u00020\u0005H\u00c6\u0003J\t\u0010+\u001a\u00020\u0005H\u00c6\u0003J\t\u0010,\u001a\u00020\u0005H\u00c6\u0003J\t\u0010-\u001a\u00020\u0005H\u00c6\u0003J\t\u0010.\u001a\u00020\u0005H\u00c6\u0003J\t\u0010/\u001a\u00020\bH\u00c6\u0003J\t\u00100\u001a\u00020\u0005H\u00c6\u0003J\t\u00101\u001a\u00020\u0005H\u00c6\u0003J\t\u00102\u001a\u00020\u0005H\u00c6\u0003J\t\u00103\u001a\u00020\u0005H\u00c6\u0003J\u0095\u0001\u00104\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u0005H\u00c6\u0001J\u0013\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00108\u001a\u00020\u0003H\u00d6\u0001J\t\u00109\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\t\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0011\u0010\n\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0011\u0010\u000b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u0011\u0010\f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u0011\u0010\r\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0018R\u0011\u0010\u000e\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001bR\u0011\u0010\u000f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0018R\u0011\u0010\u0010\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0018R\u0011\u0010\u0011\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u0011\u0010\u0012\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0018\u00a8\u0006:"}, d2 = {"Lcom/hazem/dailystore/data/AuthenticatedSyncEnvelope;", "", "protocolVersion", "", "storeId", "", "operationId", "sequenceNumber", "", "originDeviceId", "actorUserId", "operationType", "entityType", "entityId", "baseRevision", "payloadJson", "payloadSha256", "createdAt", "authenticationTagHex", "<init>", "(ILjava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getProtocolVersion", "()I", "getStoreId", "()Ljava/lang/String;", "getOperationId", "getSequenceNumber", "()J", "getOriginDeviceId", "getActorUserId", "getOperationType", "getEntityType", "getEntityId", "getBaseRevision", "getPayloadJson", "getPayloadSha256", "getCreatedAt", "getAuthenticationTagHex", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class AuthenticatedSyncEnvelope {
    private final String actorUserId;
    private final String authenticationTagHex;
    private final long baseRevision;
    private final String createdAt;
    private final String entityId;
    private final String entityType;
    private final String operationId;
    private final String operationType;
    private final String originDeviceId;
    private final String payloadJson;
    private final String payloadSha256;
    private final int protocolVersion;
    private final long sequenceNumber;
    private final String storeId;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getProtocolVersion() {
        return this.protocolVersion;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final long getBaseRevision() {
        return this.baseRevision;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getPayloadJson() {
        return this.payloadJson;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getPayloadSha256() {
        return this.payloadSha256;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getAuthenticationTagHex() {
        return this.authenticationTagHex;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getStoreId() {
        return this.storeId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getOperationId() {
        return this.operationId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getSequenceNumber() {
        return this.sequenceNumber;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getOriginDeviceId() {
        return this.originDeviceId;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getActorUserId() {
        return this.actorUserId;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getOperationType() {
        return this.operationType;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getEntityType() {
        return this.entityType;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getEntityId() {
        return this.entityId;
    }

    public final AuthenticatedSyncEnvelope copy(int protocolVersion, String storeId, String operationId, long sequenceNumber, String originDeviceId, String actorUserId, String operationType, String entityType, String entityId, long baseRevision, String payloadJson, String payloadSha256, String createdAt, String authenticationTagHex) {
        Intrinsics.checkNotNullParameter(storeId, "storeId");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        Intrinsics.checkNotNullParameter(originDeviceId, "originDeviceId");
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(operationType, "operationType");
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        Intrinsics.checkNotNullParameter(entityId, "entityId");
        Intrinsics.checkNotNullParameter(payloadJson, "payloadJson");
        Intrinsics.checkNotNullParameter(payloadSha256, "payloadSha256");
        Intrinsics.checkNotNullParameter(createdAt, "createdAt");
        Intrinsics.checkNotNullParameter(authenticationTagHex, "authenticationTagHex");
        return new AuthenticatedSyncEnvelope(protocolVersion, storeId, operationId, sequenceNumber, originDeviceId, actorUserId, operationType, entityType, entityId, baseRevision, payloadJson, payloadSha256, createdAt, authenticationTagHex);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AuthenticatedSyncEnvelope)) {
            return false;
        }
        AuthenticatedSyncEnvelope authenticatedSyncEnvelope = (AuthenticatedSyncEnvelope) other;
        return this.protocolVersion == authenticatedSyncEnvelope.protocolVersion && Intrinsics.areEqual(this.storeId, authenticatedSyncEnvelope.storeId) && Intrinsics.areEqual(this.operationId, authenticatedSyncEnvelope.operationId) && this.sequenceNumber == authenticatedSyncEnvelope.sequenceNumber && Intrinsics.areEqual(this.originDeviceId, authenticatedSyncEnvelope.originDeviceId) && Intrinsics.areEqual(this.actorUserId, authenticatedSyncEnvelope.actorUserId) && Intrinsics.areEqual(this.operationType, authenticatedSyncEnvelope.operationType) && Intrinsics.areEqual(this.entityType, authenticatedSyncEnvelope.entityType) && Intrinsics.areEqual(this.entityId, authenticatedSyncEnvelope.entityId) && this.baseRevision == authenticatedSyncEnvelope.baseRevision && Intrinsics.areEqual(this.payloadJson, authenticatedSyncEnvelope.payloadJson) && Intrinsics.areEqual(this.payloadSha256, authenticatedSyncEnvelope.payloadSha256) && Intrinsics.areEqual(this.createdAt, authenticatedSyncEnvelope.createdAt) && Intrinsics.areEqual(this.authenticationTagHex, authenticatedSyncEnvelope.authenticationTagHex);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((this.protocolVersion * 31) + this.storeId.hashCode()) * 31) + this.operationId.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.sequenceNumber)) * 31) + this.originDeviceId.hashCode()) * 31) + this.actorUserId.hashCode()) * 31) + this.operationType.hashCode()) * 31) + this.entityType.hashCode()) * 31) + this.entityId.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.baseRevision)) * 31) + this.payloadJson.hashCode()) * 31) + this.payloadSha256.hashCode()) * 31) + this.createdAt.hashCode()) * 31) + this.authenticationTagHex.hashCode();
    }

    public String toString() {
        return "AuthenticatedSyncEnvelope(protocolVersion=" + this.protocolVersion + ", storeId=" + this.storeId + ", operationId=" + this.operationId + ", sequenceNumber=" + this.sequenceNumber + ", originDeviceId=" + this.originDeviceId + ", actorUserId=" + this.actorUserId + ", operationType=" + this.operationType + ", entityType=" + this.entityType + ", entityId=" + this.entityId + ", baseRevision=" + this.baseRevision + ", payloadJson=" + this.payloadJson + ", payloadSha256=" + this.payloadSha256 + ", createdAt=" + this.createdAt + ", authenticationTagHex=" + this.authenticationTagHex + ")";
    }

    public AuthenticatedSyncEnvelope(int i, String storeId, String operationId, long j, String originDeviceId, String actorUserId, String operationType, String entityType, String entityId, long j2, String payloadJson, String payloadSha256, String createdAt, String authenticationTagHex) {
        Intrinsics.checkNotNullParameter(storeId, "storeId");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        Intrinsics.checkNotNullParameter(originDeviceId, "originDeviceId");
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(operationType, "operationType");
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        Intrinsics.checkNotNullParameter(entityId, "entityId");
        Intrinsics.checkNotNullParameter(payloadJson, "payloadJson");
        Intrinsics.checkNotNullParameter(payloadSha256, "payloadSha256");
        Intrinsics.checkNotNullParameter(createdAt, "createdAt");
        Intrinsics.checkNotNullParameter(authenticationTagHex, "authenticationTagHex");
        this.protocolVersion = i;
        this.storeId = storeId;
        this.operationId = operationId;
        this.sequenceNumber = j;
        this.originDeviceId = originDeviceId;
        this.actorUserId = actorUserId;
        this.operationType = operationType;
        this.entityType = entityType;
        this.entityId = entityId;
        this.baseRevision = j2;
        this.payloadJson = payloadJson;
        this.payloadSha256 = payloadSha256;
        this.createdAt = createdAt;
        this.authenticationTagHex = authenticationTagHex;
    }

    public final int getProtocolVersion() {
        return this.protocolVersion;
    }

    public final String getStoreId() {
        return this.storeId;
    }

    public final String getOperationId() {
        return this.operationId;
    }

    public final long getSequenceNumber() {
        return this.sequenceNumber;
    }

    public final String getOriginDeviceId() {
        return this.originDeviceId;
    }

    public final String getActorUserId() {
        return this.actorUserId;
    }

    public final String getOperationType() {
        return this.operationType;
    }

    public final String getEntityType() {
        return this.entityType;
    }

    public final String getEntityId() {
        return this.entityId;
    }

    public final long getBaseRevision() {
        return this.baseRevision;
    }

    public final String getPayloadJson() {
        return this.payloadJson;
    }

    public final String getPayloadSha256() {
        return this.payloadSha256;
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final String getAuthenticationTagHex() {
        return this.authenticationTagHex;
    }
}
