package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ServiceTransferRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b\u00a8\u0006\u001b"}, d2 = {"Lcom/hazem/dailystore/data/ServiceWalletSummary;", "", "serviceKey", "", "name", "balanceMinor", "", "accountId", "<init>", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;)V", "getServiceKey", "()Ljava/lang/String;", "getName", "getBalanceMinor", "()J", "getAccountId", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class ServiceWalletSummary {
    private final String accountId;
    private final long balanceMinor;
    private final String name;
    private final String serviceKey;

    public static /* synthetic */ ServiceWalletSummary copy$default(ServiceWalletSummary serviceWalletSummary, String str, String str2, long j, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = serviceWalletSummary.serviceKey;
        }
        if ((i & 2) != 0) {
            str2 = serviceWalletSummary.name;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            j = serviceWalletSummary.balanceMinor;
        }
        long j2 = j;
        if ((i & 8) != 0) {
            str3 = serviceWalletSummary.accountId;
        }
        return serviceWalletSummary.copy(str, str4, j2, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getServiceKey() {
        return this.serviceKey;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getBalanceMinor() {
        return this.balanceMinor;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getAccountId() {
        return this.accountId;
    }

    public final ServiceWalletSummary copy(String serviceKey, String name, long balanceMinor, String accountId) {
        Intrinsics.checkNotNullParameter(serviceKey, "serviceKey");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        return new ServiceWalletSummary(serviceKey, name, balanceMinor, accountId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ServiceWalletSummary)) {
            return false;
        }
        ServiceWalletSummary serviceWalletSummary = (ServiceWalletSummary) other;
        return Intrinsics.areEqual(this.serviceKey, serviceWalletSummary.serviceKey) && Intrinsics.areEqual(this.name, serviceWalletSummary.name) && this.balanceMinor == serviceWalletSummary.balanceMinor && Intrinsics.areEqual(this.accountId, serviceWalletSummary.accountId);
    }

    public int hashCode() {
        return (((((this.serviceKey.hashCode() * 31) + this.name.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.balanceMinor)) * 31) + this.accountId.hashCode();
    }

    public String toString() {
        return "ServiceWalletSummary(serviceKey=" + this.serviceKey + ", name=" + this.name + ", balanceMinor=" + this.balanceMinor + ", accountId=" + this.accountId + ")";
    }

    public ServiceWalletSummary(String serviceKey, String name, long j, String accountId) {
        Intrinsics.checkNotNullParameter(serviceKey, "serviceKey");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        this.serviceKey = serviceKey;
        this.name = name;
        this.balanceMinor = j;
        this.accountId = accountId;
    }

    public final String getAccountId() {
        return this.accountId;
    }

    public final long getBalanceMinor() {
        return this.balanceMinor;
    }

    public final String getName() {
        return this.name;
    }

    public final String getServiceKey() {
        return this.serviceKey;
    }
}
