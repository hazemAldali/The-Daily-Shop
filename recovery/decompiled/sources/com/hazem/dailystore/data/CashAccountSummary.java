package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CashAccountRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001a\u001a\u00020\nH\u00c6\u0003JE\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u00c6\u0001J\u0013\u0010\u001c\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001J\t\u0010 \u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0014\u00a8\u0006!"}, d2 = {"Lcom/hazem/dailystore/data/CashAccountSummary;", "", "id", "", "name", "accountType", "currencyCode", "balanceMinor", "", "isActive", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JZ)V", "getId", "()Ljava/lang/String;", "getName", "getAccountType", "getCurrencyCode", "getBalanceMinor", "()J", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class CashAccountSummary {
    private final String accountType;
    private final long balanceMinor;
    private final String currencyCode;
    private final String id;
    private final boolean isActive;
    private final String name;

    public static /* synthetic */ CashAccountSummary copy$default(CashAccountSummary cashAccountSummary, String str, String str2, String str3, String str4, long j, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = cashAccountSummary.id;
        }
        if ((i & 2) != 0) {
            str2 = cashAccountSummary.name;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = cashAccountSummary.accountType;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            str4 = cashAccountSummary.currencyCode;
        }
        String str7 = str4;
        if ((i & 16) != 0) {
            j = cashAccountSummary.balanceMinor;
        }
        long j2 = j;
        if ((i & 32) != 0) {
            z = cashAccountSummary.isActive;
        }
        return cashAccountSummary.copy(str, str5, str6, str7, j2, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getAccountType() {
        return this.accountType;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getCurrencyCode() {
        return this.currencyCode;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getBalanceMinor() {
        return this.balanceMinor;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getIsActive() {
        return this.isActive;
    }

    public final CashAccountSummary copy(String id, String name, String accountType, String currencyCode, long balanceMinor, boolean isActive) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(accountType, "accountType");
        Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
        return new CashAccountSummary(id, name, accountType, currencyCode, balanceMinor, isActive);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CashAccountSummary)) {
            return false;
        }
        CashAccountSummary cashAccountSummary = (CashAccountSummary) other;
        return Intrinsics.areEqual(this.id, cashAccountSummary.id) && Intrinsics.areEqual(this.name, cashAccountSummary.name) && Intrinsics.areEqual(this.accountType, cashAccountSummary.accountType) && Intrinsics.areEqual(this.currencyCode, cashAccountSummary.currencyCode) && this.balanceMinor == cashAccountSummary.balanceMinor && this.isActive == cashAccountSummary.isActive;
    }

    public int hashCode() {
        return (((((((((this.id.hashCode() * 31) + this.name.hashCode()) * 31) + this.accountType.hashCode()) * 31) + this.currencyCode.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.balanceMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.isActive);
    }

    public String toString() {
        return "CashAccountSummary(id=" + this.id + ", name=" + this.name + ", accountType=" + this.accountType + ", currencyCode=" + this.currencyCode + ", balanceMinor=" + this.balanceMinor + ", isActive=" + this.isActive + ")";
    }

    public CashAccountSummary(String id, String name, String accountType, String currencyCode, long j, boolean z) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(accountType, "accountType");
        Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
        this.id = id;
        this.name = name;
        this.accountType = accountType;
        this.currencyCode = currencyCode;
        this.balanceMinor = j;
        this.isActive = z;
    }

    public final String getAccountType() {
        return this.accountType;
    }

    public final String getCurrencyCode() {
        return this.currencyCode;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final long getBalanceMinor() {
        return this.balanceMinor;
    }

    public final boolean isActive() {
        return this.isActive;
    }
}
