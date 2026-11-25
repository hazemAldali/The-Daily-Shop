package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ServiceTransferReceivableRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\bH\u00c6\u0003J\t\u0010!\u001a\u00020\bH\u00c6\u0003J\t\u0010\"\u001a\u00020\bH\u00c6\u0003J\t\u0010#\u001a\u00020\bH\u00c6\u0003J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003Jo\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010*\u001a\u00020+H\u00d6\u0001J\t\u0010,\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\t\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\n\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0011\u00a8\u0006-"}, d2 = {"Lcom/hazem/dailystore/data/ServiceTransferReceivableSummary;", "", "transferId", "", "customerId", "customerName", "serviceType", "deliveredMinor", "", "chargeMinor", "paidMinor", "outstandingMinor", "createdAt", "note", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJJJLjava/lang/String;Ljava/lang/String;)V", "getTransferId", "()Ljava/lang/String;", "getCustomerId", "getCustomerName", "getServiceType", "getDeliveredMinor", "()J", "getChargeMinor", "getPaidMinor", "getOutstandingMinor", "getCreatedAt", "getNote", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class ServiceTransferReceivableSummary {
    private final long chargeMinor;
    private final String createdAt;
    private final String customerId;
    private final String customerName;
    private final long deliveredMinor;
    private final String note;
    private final long outstandingMinor;
    private final long paidMinor;
    private final String serviceType;
    private final String transferId;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getTransferId() {
        return this.transferId;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getNote() {
        return this.note;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getCustomerId() {
        return this.customerId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getCustomerName() {
        return this.customerName;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getServiceType() {
        return this.serviceType;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getDeliveredMinor() {
        return this.deliveredMinor;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getChargeMinor() {
        return this.chargeMinor;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final long getPaidMinor() {
        return this.paidMinor;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final long getOutstandingMinor() {
        return this.outstandingMinor;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final ServiceTransferReceivableSummary copy(String transferId, String customerId, String customerName, String serviceType, long deliveredMinor, long chargeMinor, long paidMinor, long outstandingMinor, String createdAt, String note) {
        Intrinsics.checkNotNullParameter(transferId, "transferId");
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(customerName, "customerName");
        Intrinsics.checkNotNullParameter(serviceType, "serviceType");
        Intrinsics.checkNotNullParameter(createdAt, "createdAt");
        return new ServiceTransferReceivableSummary(transferId, customerId, customerName, serviceType, deliveredMinor, chargeMinor, paidMinor, outstandingMinor, createdAt, note);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ServiceTransferReceivableSummary)) {
            return false;
        }
        ServiceTransferReceivableSummary serviceTransferReceivableSummary = (ServiceTransferReceivableSummary) other;
        return Intrinsics.areEqual(this.transferId, serviceTransferReceivableSummary.transferId) && Intrinsics.areEqual(this.customerId, serviceTransferReceivableSummary.customerId) && Intrinsics.areEqual(this.customerName, serviceTransferReceivableSummary.customerName) && Intrinsics.areEqual(this.serviceType, serviceTransferReceivableSummary.serviceType) && this.deliveredMinor == serviceTransferReceivableSummary.deliveredMinor && this.chargeMinor == serviceTransferReceivableSummary.chargeMinor && this.paidMinor == serviceTransferReceivableSummary.paidMinor && this.outstandingMinor == serviceTransferReceivableSummary.outstandingMinor && Intrinsics.areEqual(this.createdAt, serviceTransferReceivableSummary.createdAt) && Intrinsics.areEqual(this.note, serviceTransferReceivableSummary.note);
    }

    public int hashCode() {
        int iHashCode = ((((((((((((((((this.transferId.hashCode() * 31) + this.customerId.hashCode()) * 31) + this.customerName.hashCode()) * 31) + this.serviceType.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.deliveredMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.chargeMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.paidMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.outstandingMinor)) * 31) + this.createdAt.hashCode()) * 31;
        String str = this.note;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "ServiceTransferReceivableSummary(transferId=" + this.transferId + ", customerId=" + this.customerId + ", customerName=" + this.customerName + ", serviceType=" + this.serviceType + ", deliveredMinor=" + this.deliveredMinor + ", chargeMinor=" + this.chargeMinor + ", paidMinor=" + this.paidMinor + ", outstandingMinor=" + this.outstandingMinor + ", createdAt=" + this.createdAt + ", note=" + this.note + ")";
    }

    public ServiceTransferReceivableSummary(String transferId, String customerId, String customerName, String serviceType, long j, long j2, long j3, long j4, String createdAt, String str) {
        Intrinsics.checkNotNullParameter(transferId, "transferId");
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(customerName, "customerName");
        Intrinsics.checkNotNullParameter(serviceType, "serviceType");
        Intrinsics.checkNotNullParameter(createdAt, "createdAt");
        this.transferId = transferId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.serviceType = serviceType;
        this.deliveredMinor = j;
        this.chargeMinor = j2;
        this.paidMinor = j3;
        this.outstandingMinor = j4;
        this.createdAt = createdAt;
        this.note = str;
    }

    public final String getCustomerId() {
        return this.customerId;
    }

    public final String getCustomerName() {
        return this.customerName;
    }

    public final String getServiceType() {
        return this.serviceType;
    }

    public final String getTransferId() {
        return this.transferId;
    }

    public final long getChargeMinor() {
        return this.chargeMinor;
    }

    public final long getDeliveredMinor() {
        return this.deliveredMinor;
    }

    public final long getOutstandingMinor() {
        return this.outstandingMinor;
    }

    public final long getPaidMinor() {
        return this.paidMinor;
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final String getNote() {
        return this.note;
    }
}
