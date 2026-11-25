package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ServiceTransferRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b'\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Bo\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\b\u0012\b\b\u0002\u0010\r\u001a\u00020\b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\b\u0012\b\b\u0002\u0010\u000f\u001a\u00020\b\u0012\b\b\u0002\u0010\u0010\u001a\u00020\b\u00a2\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0006H\u00c6\u0003J\t\u0010%\u001a\u00020\bH\u00c6\u0003J\t\u0010&\u001a\u00020\bH\u00c6\u0003J\t\u0010'\u001a\u00020\bH\u00c6\u0003J\t\u0010(\u001a\u00020\bH\u00c6\u0003J\t\u0010)\u001a\u00020\bH\u00c6\u0003J\t\u0010*\u001a\u00020\bH\u00c6\u0003J\t\u0010+\u001a\u00020\bH\u00c6\u0003J\t\u0010,\u001a\u00020\bH\u00c6\u0003J\t\u0010-\u001a\u00020\bH\u00c6\u0003J\u0081\u0001\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\bH\u00c6\u0001J\u0013\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00102\u001a\u00020\u0006H\u00d6\u0001J\t\u00103\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\t\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\n\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\u000b\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0011\u0010\f\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u0011\u0010\r\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u0011\u0010\u000e\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0019R\u0011\u0010\u000f\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0019R\u0011\u0010\u0010\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0019\u00a8\u00064"}, d2 = {"Lcom/hazem/dailystore/data/ServiceDailySummary;", "", "serviceType", "", "serviceName", "operationCount", "", "deliveredMinor", "", "customerReceiptsMinor", "profitMinor", "providerFeeMinor", "rechargeFeePaidMinor", "rechargeReserveMinor", "creditChargesMinor", "deliveredMilliSyp", "profitMilliSyp", "<init>", "(Ljava/lang/String;Ljava/lang/String;IJJJJJJJJJ)V", "getServiceType", "()Ljava/lang/String;", "getServiceName", "getOperationCount", "()I", "getDeliveredMinor", "()J", "getCustomerReceiptsMinor", "getProfitMinor", "getProviderFeeMinor", "getRechargeFeePaidMinor", "getRechargeReserveMinor", "getCreditChargesMinor", "getDeliveredMilliSyp", "getProfitMilliSyp", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class ServiceDailySummary {
    private final long creditChargesMinor;
    private final long customerReceiptsMinor;
    private final long deliveredMilliSyp;
    private final long deliveredMinor;
    private final int operationCount;
    private final long profitMilliSyp;
    private final long profitMinor;
    private final long providerFeeMinor;
    private final long rechargeFeePaidMinor;
    private final long rechargeReserveMinor;
    private final String serviceName;
    private final String serviceType;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getServiceType() {
        return this.serviceType;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final long getCreditChargesMinor() {
        return this.creditChargesMinor;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final long getDeliveredMilliSyp() {
        return this.deliveredMilliSyp;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final long getProfitMilliSyp() {
        return this.profitMilliSyp;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getServiceName() {
        return this.serviceName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getOperationCount() {
        return this.operationCount;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getDeliveredMinor() {
        return this.deliveredMinor;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getCustomerReceiptsMinor() {
        return this.customerReceiptsMinor;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getProfitMinor() {
        return this.profitMinor;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final long getProviderFeeMinor() {
        return this.providerFeeMinor;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final long getRechargeFeePaidMinor() {
        return this.rechargeFeePaidMinor;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final long getRechargeReserveMinor() {
        return this.rechargeReserveMinor;
    }

    public final ServiceDailySummary copy(String serviceType, String serviceName, int operationCount, long deliveredMinor, long customerReceiptsMinor, long profitMinor, long providerFeeMinor, long rechargeFeePaidMinor, long rechargeReserveMinor, long creditChargesMinor, long deliveredMilliSyp, long profitMilliSyp) {
        Intrinsics.checkNotNullParameter(serviceType, "serviceType");
        Intrinsics.checkNotNullParameter(serviceName, "serviceName");
        return new ServiceDailySummary(serviceType, serviceName, operationCount, deliveredMinor, customerReceiptsMinor, profitMinor, providerFeeMinor, rechargeFeePaidMinor, rechargeReserveMinor, creditChargesMinor, deliveredMilliSyp, profitMilliSyp);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ServiceDailySummary)) {
            return false;
        }
        ServiceDailySummary serviceDailySummary = (ServiceDailySummary) other;
        return Intrinsics.areEqual(this.serviceType, serviceDailySummary.serviceType) && Intrinsics.areEqual(this.serviceName, serviceDailySummary.serviceName) && this.operationCount == serviceDailySummary.operationCount && this.deliveredMinor == serviceDailySummary.deliveredMinor && this.customerReceiptsMinor == serviceDailySummary.customerReceiptsMinor && this.profitMinor == serviceDailySummary.profitMinor && this.providerFeeMinor == serviceDailySummary.providerFeeMinor && this.rechargeFeePaidMinor == serviceDailySummary.rechargeFeePaidMinor && this.rechargeReserveMinor == serviceDailySummary.rechargeReserveMinor && this.creditChargesMinor == serviceDailySummary.creditChargesMinor && this.deliveredMilliSyp == serviceDailySummary.deliveredMilliSyp && this.profitMilliSyp == serviceDailySummary.profitMilliSyp;
    }

    public int hashCode() {
        return (((((((((((((((((((((this.serviceType.hashCode() * 31) + this.serviceName.hashCode()) * 31) + this.operationCount) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.deliveredMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.customerReceiptsMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.profitMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.providerFeeMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.rechargeFeePaidMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.rechargeReserveMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.creditChargesMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.deliveredMilliSyp)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.profitMilliSyp);
    }

    public String toString() {
        return "ServiceDailySummary(serviceType=" + this.serviceType + ", serviceName=" + this.serviceName + ", operationCount=" + this.operationCount + ", deliveredMinor=" + this.deliveredMinor + ", customerReceiptsMinor=" + this.customerReceiptsMinor + ", profitMinor=" + this.profitMinor + ", providerFeeMinor=" + this.providerFeeMinor + ", rechargeFeePaidMinor=" + this.rechargeFeePaidMinor + ", rechargeReserveMinor=" + this.rechargeReserveMinor + ", creditChargesMinor=" + this.creditChargesMinor + ", deliveredMilliSyp=" + this.deliveredMilliSyp + ", profitMilliSyp=" + this.profitMilliSyp + ")";
    }

    public ServiceDailySummary(String serviceType, String serviceName, int i, long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9) {
        Intrinsics.checkNotNullParameter(serviceType, "serviceType");
        Intrinsics.checkNotNullParameter(serviceName, "serviceName");
        this.serviceType = serviceType;
        this.serviceName = serviceName;
        this.operationCount = i;
        this.deliveredMinor = j;
        this.customerReceiptsMinor = j2;
        this.profitMinor = j3;
        this.providerFeeMinor = j4;
        this.rechargeFeePaidMinor = j5;
        this.rechargeReserveMinor = j6;
        this.creditChargesMinor = j7;
        this.deliveredMilliSyp = j8;
        this.profitMilliSyp = j9;
    }

    public /* synthetic */ ServiceDailySummary(String str, String str2, int i, long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, i, j, j2, j3, j4, j5, (i2 & 256) != 0 ? j : j6, (i2 & 512) != 0 ? 0L : j7, (i2 & 1024) != 0 ? j * 1000 : j8, (i2 & 2048) != 0 ? j3 * 1000 : j9);
    }

    public final long getDeliveredMinor() {
        return this.deliveredMinor;
    }

    public final int getOperationCount() {
        return this.operationCount;
    }

    public final String getServiceName() {
        return this.serviceName;
    }

    public final String getServiceType() {
        return this.serviceType;
    }

    public final long getCustomerReceiptsMinor() {
        return this.customerReceiptsMinor;
    }

    public final long getProfitMinor() {
        return this.profitMinor;
    }

    public final long getProviderFeeMinor() {
        return this.providerFeeMinor;
    }

    public final long getCreditChargesMinor() {
        return this.creditChargesMinor;
    }

    public final long getRechargeFeePaidMinor() {
        return this.rechargeFeePaidMinor;
    }

    public final long getRechargeReserveMinor() {
        return this.rechargeReserveMinor;
    }

    public final long getDeliveredMilliSyp() {
        return this.deliveredMilliSyp;
    }

    public final long getProfitMilliSyp() {
        return this.profitMilliSyp;
    }
}
