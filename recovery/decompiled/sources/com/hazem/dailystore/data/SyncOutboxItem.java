package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SyncProtocolRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\rH\u00c6\u0003Jc\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\rH\u00c6\u0001J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010)\u001a\u00020\rH\u00d6\u0001J\t\u0010*\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b\u00a8\u0006+"}, d2 = {"Lcom/hazem/dailystore/data/SyncOutboxItem;", "", "operationId", "", "sequence", "", "operationType", "entityType", "entityId", "payloadJson", "payloadSha256", "createdAt", "retryCount", "", "<init>", "(Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getOperationId", "()Ljava/lang/String;", "getSequence", "()J", "getOperationType", "getEntityType", "getEntityId", "getPayloadJson", "getPayloadSha256", "getCreatedAt", "getRetryCount", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class SyncOutboxItem {
    private final String createdAt;
    private final String entityId;
    private final String entityType;
    private final String operationId;
    private final String operationType;
    private final String payloadJson;
    private final String payloadSha256;
    private final int retryCount;
    private final long sequence;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getOperationId() {
        return this.operationId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getSequence() {
        return this.sequence;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getOperationType() {
        return this.operationType;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getEntityType() {
        return this.entityType;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getEntityId() {
        return this.entityId;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getPayloadJson() {
        return this.payloadJson;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getPayloadSha256() {
        return this.payloadSha256;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getRetryCount() {
        return this.retryCount;
    }

    public final SyncOutboxItem copy(String operationId, long sequence, String operationType, String entityType, String entityId, String payloadJson, String payloadSha256, String createdAt, int retryCount) {
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        Intrinsics.checkNotNullParameter(operationType, "operationType");
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        Intrinsics.checkNotNullParameter(entityId, "entityId");
        Intrinsics.checkNotNullParameter(payloadJson, "payloadJson");
        Intrinsics.checkNotNullParameter(payloadSha256, "payloadSha256");
        Intrinsics.checkNotNullParameter(createdAt, "createdAt");
        return new SyncOutboxItem(operationId, sequence, operationType, entityType, entityId, payloadJson, payloadSha256, createdAt, retryCount);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SyncOutboxItem)) {
            return false;
        }
        SyncOutboxItem syncOutboxItem = (SyncOutboxItem) other;
        return Intrinsics.areEqual(this.operationId, syncOutboxItem.operationId) && this.sequence == syncOutboxItem.sequence && Intrinsics.areEqual(this.operationType, syncOutboxItem.operationType) && Intrinsics.areEqual(this.entityType, syncOutboxItem.entityType) && Intrinsics.areEqual(this.entityId, syncOutboxItem.entityId) && Intrinsics.areEqual(this.payloadJson, syncOutboxItem.payloadJson) && Intrinsics.areEqual(this.payloadSha256, syncOutboxItem.payloadSha256) && Intrinsics.areEqual(this.createdAt, syncOutboxItem.createdAt) && this.retryCount == syncOutboxItem.retryCount;
    }

    public int hashCode() {
        return (((((((((((((((this.operationId.hashCode() * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.sequence)) * 31) + this.operationType.hashCode()) * 31) + this.entityType.hashCode()) * 31) + this.entityId.hashCode()) * 31) + this.payloadJson.hashCode()) * 31) + this.payloadSha256.hashCode()) * 31) + this.createdAt.hashCode()) * 31) + this.retryCount;
    }

    public String toString() {
        return "SyncOutboxItem(operationId=" + this.operationId + ", sequence=" + this.sequence + ", operationType=" + this.operationType + ", entityType=" + this.entityType + ", entityId=" + this.entityId + ", payloadJson=" + this.payloadJson + ", payloadSha256=" + this.payloadSha256 + ", createdAt=" + this.createdAt + ", retryCount=" + this.retryCount + ")";
    }

    public SyncOutboxItem(String operationId, long j, String operationType, String entityType, String entityId, String payloadJson, String payloadSha256, String createdAt, int i) {
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        Intrinsics.checkNotNullParameter(operationType, "operationType");
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        Intrinsics.checkNotNullParameter(entityId, "entityId");
        Intrinsics.checkNotNullParameter(payloadJson, "payloadJson");
        Intrinsics.checkNotNullParameter(payloadSha256, "payloadSha256");
        Intrinsics.checkNotNullParameter(createdAt, "createdAt");
        this.operationId = operationId;
        this.sequence = j;
        this.operationType = operationType;
        this.entityType = entityType;
        this.entityId = entityId;
        this.payloadJson = payloadJson;
        this.payloadSha256 = payloadSha256;
        this.createdAt = createdAt;
        this.retryCount = i;
    }

    public final String getOperationId() {
        return this.operationId;
    }

    public final String getOperationType() {
        return this.operationType;
    }

    public final long getSequence() {
        return this.sequence;
    }

    public final String getEntityId() {
        return this.entityId;
    }

    public final String getEntityType() {
        return this.entityType;
    }

    public final String getPayloadJson() {
        return this.payloadJson;
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final String getPayloadSha256() {
        return this.payloadSha256;
    }

    public final int getRetryCount() {
        return this.retryCount;
    }
}
