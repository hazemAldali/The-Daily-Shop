package com.hazem.dailystore.data;

import androidx.core.app.NotificationCompat;
import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ServiceTransferReceivableRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\nH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\nH\u00c6\u0003JO\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\nH\u00c6\u0001J\u0013\u0010 \u001a\u00020\n2\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\"\u001a\u00020#H\u00d6\u0001J\t\u0010$\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016\u00a8\u0006%"}, d2 = {"Lcom/hazem/dailystore/data/ServiceTransferDebtPaymentResult;", "", "paymentId", "", "transferId", "paidMinor", "", "outstandingMinor", NotificationCompat.CATEGORY_STATUS, "replayed", "", "pending", "<init>", "(Ljava/lang/String;Ljava/lang/String;JJLjava/lang/String;ZZ)V", "getPaymentId", "()Ljava/lang/String;", "getTransferId", "getPaidMinor", "()J", "getOutstandingMinor", "getStatus", "getReplayed", "()Z", "getPending", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class ServiceTransferDebtPaymentResult {
    private final long outstandingMinor;
    private final long paidMinor;
    private final String paymentId;
    private final boolean pending;
    private final boolean replayed;
    private final String status;
    private final String transferId;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPaymentId() {
        return this.paymentId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTransferId() {
        return this.transferId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getPaidMinor() {
        return this.paidMinor;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getOutstandingMinor() {
        return this.outstandingMinor;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getReplayed() {
        return this.replayed;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getPending() {
        return this.pending;
    }

    public final ServiceTransferDebtPaymentResult copy(String paymentId, String transferId, long paidMinor, long outstandingMinor, String status, boolean replayed, boolean pending) {
        Intrinsics.checkNotNullParameter(paymentId, "paymentId");
        Intrinsics.checkNotNullParameter(transferId, "transferId");
        Intrinsics.checkNotNullParameter(status, "status");
        return new ServiceTransferDebtPaymentResult(paymentId, transferId, paidMinor, outstandingMinor, status, replayed, pending);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ServiceTransferDebtPaymentResult)) {
            return false;
        }
        ServiceTransferDebtPaymentResult serviceTransferDebtPaymentResult = (ServiceTransferDebtPaymentResult) other;
        return Intrinsics.areEqual(this.paymentId, serviceTransferDebtPaymentResult.paymentId) && Intrinsics.areEqual(this.transferId, serviceTransferDebtPaymentResult.transferId) && this.paidMinor == serviceTransferDebtPaymentResult.paidMinor && this.outstandingMinor == serviceTransferDebtPaymentResult.outstandingMinor && Intrinsics.areEqual(this.status, serviceTransferDebtPaymentResult.status) && this.replayed == serviceTransferDebtPaymentResult.replayed && this.pending == serviceTransferDebtPaymentResult.pending;
    }

    public int hashCode() {
        return (((((((((((this.paymentId.hashCode() * 31) + this.transferId.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.paidMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.outstandingMinor)) * 31) + this.status.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.replayed)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.pending);
    }

    public String toString() {
        return "ServiceTransferDebtPaymentResult(paymentId=" + this.paymentId + ", transferId=" + this.transferId + ", paidMinor=" + this.paidMinor + ", outstandingMinor=" + this.outstandingMinor + ", status=" + this.status + ", replayed=" + this.replayed + ", pending=" + this.pending + ")";
    }

    public ServiceTransferDebtPaymentResult(String paymentId, String transferId, long j, long j2, String status, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(paymentId, "paymentId");
        Intrinsics.checkNotNullParameter(transferId, "transferId");
        Intrinsics.checkNotNullParameter(status, "status");
        this.paymentId = paymentId;
        this.transferId = transferId;
        this.paidMinor = j;
        this.outstandingMinor = j2;
        this.status = status;
        this.replayed = z;
        this.pending = z2;
    }

    public /* synthetic */ ServiceTransferDebtPaymentResult(String str, String str2, long j, long j2, String str3, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, j, j2, str3, z, (i & 64) != 0 ? false : z2);
    }

    public final long getOutstandingMinor() {
        return this.outstandingMinor;
    }

    public final long getPaidMinor() {
        return this.paidMinor;
    }

    public final String getPaymentId() {
        return this.paymentId;
    }

    public final String getTransferId() {
        return this.transferId;
    }

    public final boolean getPending() {
        return this.pending;
    }

    public final boolean getReplayed() {
        return this.replayed;
    }

    public final String getStatus() {
        return this.status;
    }
}
