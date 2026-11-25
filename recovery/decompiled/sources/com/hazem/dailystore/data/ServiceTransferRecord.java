package com.hazem.dailystore.data;

import androidx.core.app.NotificationCompat;
import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ServiceTransferRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b8\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u00a7\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0016\u0010\u0017J\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\t\u0010.\u001a\u00020\u0003H\u00c6\u0003J\t\u0010/\u001a\u00020\u0007H\u00c6\u0003J\t\u00100\u001a\u00020\u0007H\u00c6\u0003J\t\u00101\u001a\u00020\u0007H\u00c6\u0003J\t\u00102\u001a\u00020\u0007H\u00c6\u0003J\t\u00103\u001a\u00020\u0003H\u00c6\u0003J\t\u00104\u001a\u00020\u0003H\u00c6\u0003J\t\u00105\u001a\u00020\u0003H\u00c6\u0003J\t\u00106\u001a\u00020\u0003H\u00c6\u0003J\t\u00107\u001a\u00020\u0003H\u00c6\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u00109\u001a\u00020\u0007H\u00c6\u0003J\t\u0010:\u001a\u00020\u0007H\u00c6\u0003J\t\u0010;\u001a\u00020\u0007H\u00c6\u0003J\t\u0010<\u001a\u00020\u0007H\u00c6\u0003J\t\u0010=\u001a\u00020\u0007H\u00c6\u0003J\u00bf\u0001\u0010>\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00072\b\b\u0002\u0010\u0012\u001a\u00020\u00072\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u00072\b\b\u0002\u0010\u0015\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010?\u001a\u00020@2\b\u0010A\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010B\u001a\u00020CH\u00d6\u0001J\t\u0010D\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001dR\u0011\u0010\t\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001dR\u0011\u0010\n\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001dR\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0019R\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0019R\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0019R\u0011\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0019R\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0019R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0019R\u0011\u0010\u0011\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001dR\u0011\u0010\u0012\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001dR\u0011\u0010\u0013\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001dR\u0011\u0010\u0014\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001dR\u0011\u0010\u0015\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001d\u00a8\u0006E"}, d2 = {"Lcom/hazem/dailystore/data/ServiceTransferRecord;", "", "id", "", "serviceType", "serviceName", "deliveredMinor", "", "profitMinor", "providerFeeMinor", "customerChargeMinor", "receiptAccountName", "receiptChannel", "createdAt", NotificationCompat.CATEGORY_STATUS, "paymentMode", "customerName", "paidMinor", "outstandingMinor", "amountDebitedMinor", "deliveredMilliSyp", "profitMilliSyp", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJJJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJJJJ)V", "getId", "()Ljava/lang/String;", "getServiceType", "getServiceName", "getDeliveredMinor", "()J", "getProfitMinor", "getProviderFeeMinor", "getCustomerChargeMinor", "getReceiptAccountName", "getReceiptChannel", "getCreatedAt", "getStatus", "getPaymentMode", "getCustomerName", "getPaidMinor", "getOutstandingMinor", "getAmountDebitedMinor", "getDeliveredMilliSyp", "getProfitMilliSyp", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class ServiceTransferRecord {
    private final long amountDebitedMinor;
    private final String createdAt;
    private final long customerChargeMinor;
    private final String customerName;
    private final long deliveredMilliSyp;
    private final long deliveredMinor;
    private final String id;
    private final long outstandingMinor;
    private final long paidMinor;
    private final String paymentMode;
    private final long profitMilliSyp;
    private final long profitMinor;
    private final long providerFeeMinor;
    private final String receiptAccountName;
    private final String receiptChannel;
    private final String serviceName;
    private final String serviceType;
    private final String status;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getPaymentMode() {
        return this.paymentMode;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getCustomerName() {
        return this.customerName;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final long getPaidMinor() {
        return this.paidMinor;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final long getOutstandingMinor() {
        return this.outstandingMinor;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final long getAmountDebitedMinor() {
        return this.amountDebitedMinor;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final long getDeliveredMilliSyp() {
        return this.deliveredMilliSyp;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final long getProfitMilliSyp() {
        return this.profitMilliSyp;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getServiceType() {
        return this.serviceType;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getServiceName() {
        return this.serviceName;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getDeliveredMinor() {
        return this.deliveredMinor;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getProfitMinor() {
        return this.profitMinor;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getProviderFeeMinor() {
        return this.providerFeeMinor;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final long getCustomerChargeMinor() {
        return this.customerChargeMinor;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getReceiptAccountName() {
        return this.receiptAccountName;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getReceiptChannel() {
        return this.receiptChannel;
    }

    public final ServiceTransferRecord copy(String id, String serviceType, String serviceName, long deliveredMinor, long profitMinor, long providerFeeMinor, long customerChargeMinor, String receiptAccountName, String receiptChannel, String createdAt, String status, String paymentMode, String customerName, long paidMinor, long outstandingMinor, long amountDebitedMinor, long deliveredMilliSyp, long profitMilliSyp) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(serviceType, "serviceType");
        Intrinsics.checkNotNullParameter(serviceName, "serviceName");
        Intrinsics.checkNotNullParameter(receiptAccountName, "receiptAccountName");
        Intrinsics.checkNotNullParameter(receiptChannel, "receiptChannel");
        Intrinsics.checkNotNullParameter(createdAt, "createdAt");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(paymentMode, "paymentMode");
        return new ServiceTransferRecord(id, serviceType, serviceName, deliveredMinor, profitMinor, providerFeeMinor, customerChargeMinor, receiptAccountName, receiptChannel, createdAt, status, paymentMode, customerName, paidMinor, outstandingMinor, amountDebitedMinor, deliveredMilliSyp, profitMilliSyp);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ServiceTransferRecord)) {
            return false;
        }
        ServiceTransferRecord serviceTransferRecord = (ServiceTransferRecord) other;
        return Intrinsics.areEqual(this.id, serviceTransferRecord.id) && Intrinsics.areEqual(this.serviceType, serviceTransferRecord.serviceType) && Intrinsics.areEqual(this.serviceName, serviceTransferRecord.serviceName) && this.deliveredMinor == serviceTransferRecord.deliveredMinor && this.profitMinor == serviceTransferRecord.profitMinor && this.providerFeeMinor == serviceTransferRecord.providerFeeMinor && this.customerChargeMinor == serviceTransferRecord.customerChargeMinor && Intrinsics.areEqual(this.receiptAccountName, serviceTransferRecord.receiptAccountName) && Intrinsics.areEqual(this.receiptChannel, serviceTransferRecord.receiptChannel) && Intrinsics.areEqual(this.createdAt, serviceTransferRecord.createdAt) && Intrinsics.areEqual(this.status, serviceTransferRecord.status) && Intrinsics.areEqual(this.paymentMode, serviceTransferRecord.paymentMode) && Intrinsics.areEqual(this.customerName, serviceTransferRecord.customerName) && this.paidMinor == serviceTransferRecord.paidMinor && this.outstandingMinor == serviceTransferRecord.outstandingMinor && this.amountDebitedMinor == serviceTransferRecord.amountDebitedMinor && this.deliveredMilliSyp == serviceTransferRecord.deliveredMilliSyp && this.profitMilliSyp == serviceTransferRecord.profitMilliSyp;
    }

    public int hashCode() {
        int iHashCode = ((((((((((((((((((((((this.id.hashCode() * 31) + this.serviceType.hashCode()) * 31) + this.serviceName.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.deliveredMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.profitMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.providerFeeMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.customerChargeMinor)) * 31) + this.receiptAccountName.hashCode()) * 31) + this.receiptChannel.hashCode()) * 31) + this.createdAt.hashCode()) * 31) + this.status.hashCode()) * 31) + this.paymentMode.hashCode()) * 31;
        String str = this.customerName;
        return ((((((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.paidMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.outstandingMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.amountDebitedMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.deliveredMilliSyp)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.profitMilliSyp);
    }

    public String toString() {
        return "ServiceTransferRecord(id=" + this.id + ", serviceType=" + this.serviceType + ", serviceName=" + this.serviceName + ", deliveredMinor=" + this.deliveredMinor + ", profitMinor=" + this.profitMinor + ", providerFeeMinor=" + this.providerFeeMinor + ", customerChargeMinor=" + this.customerChargeMinor + ", receiptAccountName=" + this.receiptAccountName + ", receiptChannel=" + this.receiptChannel + ", createdAt=" + this.createdAt + ", status=" + this.status + ", paymentMode=" + this.paymentMode + ", customerName=" + this.customerName + ", paidMinor=" + this.paidMinor + ", outstandingMinor=" + this.outstandingMinor + ", amountDebitedMinor=" + this.amountDebitedMinor + ", deliveredMilliSyp=" + this.deliveredMilliSyp + ", profitMilliSyp=" + this.profitMilliSyp + ")";
    }

    public ServiceTransferRecord(String id, String serviceType, String serviceName, long j, long j2, long j3, long j4, String receiptAccountName, String receiptChannel, String createdAt, String status, String paymentMode, String str, long j5, long j6, long j7, long j8, long j9) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(serviceType, "serviceType");
        Intrinsics.checkNotNullParameter(serviceName, "serviceName");
        Intrinsics.checkNotNullParameter(receiptAccountName, "receiptAccountName");
        Intrinsics.checkNotNullParameter(receiptChannel, "receiptChannel");
        Intrinsics.checkNotNullParameter(createdAt, "createdAt");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(paymentMode, "paymentMode");
        this.id = id;
        this.serviceType = serviceType;
        this.serviceName = serviceName;
        this.deliveredMinor = j;
        this.profitMinor = j2;
        this.providerFeeMinor = j3;
        this.customerChargeMinor = j4;
        this.receiptAccountName = receiptAccountName;
        this.receiptChannel = receiptChannel;
        this.createdAt = createdAt;
        this.status = status;
        this.paymentMode = paymentMode;
        this.customerName = str;
        this.paidMinor = j5;
        this.outstandingMinor = j6;
        this.amountDebitedMinor = j7;
        this.deliveredMilliSyp = j8;
        this.profitMilliSyp = j9;
    }

    public final long getDeliveredMinor() {
        return this.deliveredMinor;
    }

    public final String getId() {
        return this.id;
    }

    public final long getProfitMinor() {
        return this.profitMinor;
    }

    public final String getServiceName() {
        return this.serviceName;
    }

    public final String getServiceType() {
        return this.serviceType;
    }

    public final long getCustomerChargeMinor() {
        return this.customerChargeMinor;
    }

    public final long getProviderFeeMinor() {
        return this.providerFeeMinor;
    }

    public final String getReceiptAccountName() {
        return this.receiptAccountName;
    }

    public final String getReceiptChannel() {
        return this.receiptChannel;
    }

    public /* synthetic */ ServiceTransferRecord(String str, String str2, String str3, long j, long j2, long j3, long j4, String str4, String str5, String str6, String str7, String str8, String str9, long j5, long j6, long j7, long j8, long j9, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, j, j2, j3, j4, str4, str5, str6, str7, (i & 2048) != 0 ? "CASH" : str8, (i & 4096) != 0 ? null : str9, (i & 8192) != 0 ? 0L : j5, (i & 16384) != 0 ? 0L : j6, (32768 & i) != 0 ? j : j7, (65536 & i) != 0 ? j * 1000 : j8, (i & 131072) != 0 ? j2 * 1000 : j9);
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final String getCustomerName() {
        return this.customerName;
    }

    public final String getPaymentMode() {
        return this.paymentMode;
    }

    public final String getStatus() {
        return this.status;
    }

    public final long getOutstandingMinor() {
        return this.outstandingMinor;
    }

    public final long getPaidMinor() {
        return this.paidMinor;
    }

    public final long getAmountDebitedMinor() {
        return this.amountDebitedMinor;
    }

    public final long getDeliveredMilliSyp() {
        return this.deliveredMilliSyp;
    }

    public final long getProfitMilliSyp() {
        return this.profitMilliSyp;
    }
}
