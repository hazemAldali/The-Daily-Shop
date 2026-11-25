package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OperatingExpenseRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\nH\u00c6\u0003JE\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\nH\u00c6\u0001J\u0013\u0010\u001d\u001a\u00020\n2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001J\t\u0010!\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006\""}, d2 = {"Lcom/hazem/dailystore/data/OperatingExpenseResult;", "", "expenseId", "", "cashTransactionId", "amountMinor", "", "currencyCode", "amountUsdCents", "replayed", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;JZ)V", "getExpenseId", "()Ljava/lang/String;", "getCashTransactionId", "getAmountMinor", "()J", "getCurrencyCode", "getAmountUsdCents", "getReplayed", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class OperatingExpenseResult {
    private final long amountMinor;
    private final long amountUsdCents;
    private final String cashTransactionId;
    private final String currencyCode;
    private final String expenseId;
    private final boolean replayed;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getExpenseId() {
        return this.expenseId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getCashTransactionId() {
        return this.cashTransactionId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getAmountMinor() {
        return this.amountMinor;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getCurrencyCode() {
        return this.currencyCode;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getAmountUsdCents() {
        return this.amountUsdCents;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getReplayed() {
        return this.replayed;
    }

    public final OperatingExpenseResult copy(String expenseId, String cashTransactionId, long amountMinor, String currencyCode, long amountUsdCents, boolean replayed) {
        Intrinsics.checkNotNullParameter(expenseId, "expenseId");
        Intrinsics.checkNotNullParameter(cashTransactionId, "cashTransactionId");
        Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
        return new OperatingExpenseResult(expenseId, cashTransactionId, amountMinor, currencyCode, amountUsdCents, replayed);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OperatingExpenseResult)) {
            return false;
        }
        OperatingExpenseResult operatingExpenseResult = (OperatingExpenseResult) other;
        return Intrinsics.areEqual(this.expenseId, operatingExpenseResult.expenseId) && Intrinsics.areEqual(this.cashTransactionId, operatingExpenseResult.cashTransactionId) && this.amountMinor == operatingExpenseResult.amountMinor && Intrinsics.areEqual(this.currencyCode, operatingExpenseResult.currencyCode) && this.amountUsdCents == operatingExpenseResult.amountUsdCents && this.replayed == operatingExpenseResult.replayed;
    }

    public int hashCode() {
        return (((((((((this.expenseId.hashCode() * 31) + this.cashTransactionId.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.amountMinor)) * 31) + this.currencyCode.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.amountUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.replayed);
    }

    public String toString() {
        return "OperatingExpenseResult(expenseId=" + this.expenseId + ", cashTransactionId=" + this.cashTransactionId + ", amountMinor=" + this.amountMinor + ", currencyCode=" + this.currencyCode + ", amountUsdCents=" + this.amountUsdCents + ", replayed=" + this.replayed + ")";
    }

    public OperatingExpenseResult(String expenseId, String cashTransactionId, long j, String currencyCode, long j2, boolean z) {
        Intrinsics.checkNotNullParameter(expenseId, "expenseId");
        Intrinsics.checkNotNullParameter(cashTransactionId, "cashTransactionId");
        Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
        this.expenseId = expenseId;
        this.cashTransactionId = cashTransactionId;
        this.amountMinor = j;
        this.currencyCode = currencyCode;
        this.amountUsdCents = j2;
        this.replayed = z;
    }

    public final long getAmountMinor() {
        return this.amountMinor;
    }

    public final String getCashTransactionId() {
        return this.cashTransactionId;
    }

    public final String getExpenseId() {
        return this.expenseId;
    }

    public final long getAmountUsdCents() {
        return this.amountUsdCents;
    }

    public final String getCurrencyCode() {
        return this.currencyCode;
    }

    public final boolean getReplayed() {
        return this.replayed;
    }
}
