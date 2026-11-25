package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PurchasePaymentRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u00a2\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003Je\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010'\u001a\u00020(H\u00d6\u0001J\t\u0010)\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010\u00a8\u0006*"}, d2 = {"Lcom/hazem/dailystore/data/PurchasePaymentRecord;", "", "id", "", "amountMinor", "", "currencyCode", "accountName", "accountType", "createdAt", "note", "cashTransactionId", "cashAccountId", "<init>", "(Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getAmountMinor", "()J", "getCurrencyCode", "getAccountName", "getAccountType", "getCreatedAt", "getNote", "getCashTransactionId", "getCashAccountId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class PurchasePaymentRecord {
    private final String accountName;
    private final String accountType;
    private final long amountMinor;
    private final String cashAccountId;
    private final String cashTransactionId;
    private final String createdAt;
    private final String currencyCode;
    private final String id;
    private final String note;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getAmountMinor() {
        return this.amountMinor;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getCurrencyCode() {
        return this.currencyCode;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getAccountName() {
        return this.accountName;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getAccountType() {
        return this.accountType;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getNote() {
        return this.note;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getCashTransactionId() {
        return this.cashTransactionId;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getCashAccountId() {
        return this.cashAccountId;
    }

    public final PurchasePaymentRecord copy(String id, long amountMinor, String currencyCode, String accountName, String accountType, String createdAt, String note, String cashTransactionId, String cashAccountId) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
        Intrinsics.checkNotNullParameter(accountName, "accountName");
        Intrinsics.checkNotNullParameter(accountType, "accountType");
        Intrinsics.checkNotNullParameter(createdAt, "createdAt");
        Intrinsics.checkNotNullParameter(cashTransactionId, "cashTransactionId");
        Intrinsics.checkNotNullParameter(cashAccountId, "cashAccountId");
        return new PurchasePaymentRecord(id, amountMinor, currencyCode, accountName, accountType, createdAt, note, cashTransactionId, cashAccountId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PurchasePaymentRecord)) {
            return false;
        }
        PurchasePaymentRecord purchasePaymentRecord = (PurchasePaymentRecord) other;
        return Intrinsics.areEqual(this.id, purchasePaymentRecord.id) && this.amountMinor == purchasePaymentRecord.amountMinor && Intrinsics.areEqual(this.currencyCode, purchasePaymentRecord.currencyCode) && Intrinsics.areEqual(this.accountName, purchasePaymentRecord.accountName) && Intrinsics.areEqual(this.accountType, purchasePaymentRecord.accountType) && Intrinsics.areEqual(this.createdAt, purchasePaymentRecord.createdAt) && Intrinsics.areEqual(this.note, purchasePaymentRecord.note) && Intrinsics.areEqual(this.cashTransactionId, purchasePaymentRecord.cashTransactionId) && Intrinsics.areEqual(this.cashAccountId, purchasePaymentRecord.cashAccountId);
    }

    public int hashCode() {
        int iHashCode = ((((((((((this.id.hashCode() * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.amountMinor)) * 31) + this.currencyCode.hashCode()) * 31) + this.accountName.hashCode()) * 31) + this.accountType.hashCode()) * 31) + this.createdAt.hashCode()) * 31;
        String str = this.note;
        return ((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.cashTransactionId.hashCode()) * 31) + this.cashAccountId.hashCode();
    }

    public String toString() {
        return "PurchasePaymentRecord(id=" + this.id + ", amountMinor=" + this.amountMinor + ", currencyCode=" + this.currencyCode + ", accountName=" + this.accountName + ", accountType=" + this.accountType + ", createdAt=" + this.createdAt + ", note=" + this.note + ", cashTransactionId=" + this.cashTransactionId + ", cashAccountId=" + this.cashAccountId + ")";
    }

    public PurchasePaymentRecord(String id, long j, String currencyCode, String accountName, String accountType, String createdAt, String str, String cashTransactionId, String cashAccountId) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
        Intrinsics.checkNotNullParameter(accountName, "accountName");
        Intrinsics.checkNotNullParameter(accountType, "accountType");
        Intrinsics.checkNotNullParameter(createdAt, "createdAt");
        Intrinsics.checkNotNullParameter(cashTransactionId, "cashTransactionId");
        Intrinsics.checkNotNullParameter(cashAccountId, "cashAccountId");
        this.id = id;
        this.amountMinor = j;
        this.currencyCode = currencyCode;
        this.accountName = accountName;
        this.accountType = accountType;
        this.createdAt = createdAt;
        this.note = str;
        this.cashTransactionId = cashTransactionId;
        this.cashAccountId = cashAccountId;
    }

    public final String getAccountName() {
        return this.accountName;
    }

    public final long getAmountMinor() {
        return this.amountMinor;
    }

    public final String getCurrencyCode() {
        return this.currencyCode;
    }

    public final String getId() {
        return this.id;
    }

    public final String getAccountType() {
        return this.accountType;
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final String getNote() {
        return this.note;
    }

    public final String getCashAccountId() {
        return this.cashAccountId;
    }

    public final String getCashTransactionId() {
        return this.cashTransactionId;
    }
}
