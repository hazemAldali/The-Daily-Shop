package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomerPaymentRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b%\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Ba\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u00a2\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0005H\u00c6\u0003J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\u0005H\u00c6\u0003J\t\u0010&\u001a\u00020\u0005H\u00c6\u0003J\t\u0010'\u001a\u00020\u0005H\u00c6\u0003J\t\u0010(\u001a\u00020\u0005H\u00c6\u0003Jy\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010-\u001a\u00020.H\u00d6\u0001J\t\u0010/\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012R\u0011\u0010\u000b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0011\u0010\f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0011\u0010\r\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0011\u0010\u000e\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0014\u00a8\u00060"}, d2 = {"Lcom/hazem/dailystore/data/CustomerPaymentRecord;", "", "id", "", "amountMinor", "", "currencyCode", "accountName", "accountType", "createdAt", "note", "originalInvoiceRateMilli", "collectionRateMilli", "effectiveRateMilli", "usdReferenceReductionCents", "<init>", "(Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJJJ)V", "getId", "()Ljava/lang/String;", "getAmountMinor", "()J", "getCurrencyCode", "getAccountName", "getAccountType", "getCreatedAt", "getNote", "getOriginalInvoiceRateMilli", "getCollectionRateMilli", "getEffectiveRateMilli", "getUsdReferenceReductionCents", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class CustomerPaymentRecord {
    private final String accountName;
    private final String accountType;
    private final long amountMinor;
    private final long collectionRateMilli;
    private final String createdAt;
    private final String currencyCode;
    private final long effectiveRateMilli;
    private final String id;
    private final String note;
    private final long originalInvoiceRateMilli;
    private final long usdReferenceReductionCents;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final long getEffectiveRateMilli() {
        return this.effectiveRateMilli;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final long getUsdReferenceReductionCents() {
        return this.usdReferenceReductionCents;
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
    public final long getOriginalInvoiceRateMilli() {
        return this.originalInvoiceRateMilli;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final long getCollectionRateMilli() {
        return this.collectionRateMilli;
    }

    public final CustomerPaymentRecord copy(String id, long amountMinor, String currencyCode, String accountName, String accountType, String createdAt, String note, long originalInvoiceRateMilli, long collectionRateMilli, long effectiveRateMilli, long usdReferenceReductionCents) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
        Intrinsics.checkNotNullParameter(accountName, "accountName");
        Intrinsics.checkNotNullParameter(accountType, "accountType");
        Intrinsics.checkNotNullParameter(createdAt, "createdAt");
        return new CustomerPaymentRecord(id, amountMinor, currencyCode, accountName, accountType, createdAt, note, originalInvoiceRateMilli, collectionRateMilli, effectiveRateMilli, usdReferenceReductionCents);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomerPaymentRecord)) {
            return false;
        }
        CustomerPaymentRecord customerPaymentRecord = (CustomerPaymentRecord) other;
        return Intrinsics.areEqual(this.id, customerPaymentRecord.id) && this.amountMinor == customerPaymentRecord.amountMinor && Intrinsics.areEqual(this.currencyCode, customerPaymentRecord.currencyCode) && Intrinsics.areEqual(this.accountName, customerPaymentRecord.accountName) && Intrinsics.areEqual(this.accountType, customerPaymentRecord.accountType) && Intrinsics.areEqual(this.createdAt, customerPaymentRecord.createdAt) && Intrinsics.areEqual(this.note, customerPaymentRecord.note) && this.originalInvoiceRateMilli == customerPaymentRecord.originalInvoiceRateMilli && this.collectionRateMilli == customerPaymentRecord.collectionRateMilli && this.effectiveRateMilli == customerPaymentRecord.effectiveRateMilli && this.usdReferenceReductionCents == customerPaymentRecord.usdReferenceReductionCents;
    }

    public int hashCode() {
        int iHashCode = ((((((((((this.id.hashCode() * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.amountMinor)) * 31) + this.currencyCode.hashCode()) * 31) + this.accountName.hashCode()) * 31) + this.accountType.hashCode()) * 31) + this.createdAt.hashCode()) * 31;
        String str = this.note;
        return ((((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.originalInvoiceRateMilli)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.collectionRateMilli)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.effectiveRateMilli)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.usdReferenceReductionCents);
    }

    public String toString() {
        return "CustomerPaymentRecord(id=" + this.id + ", amountMinor=" + this.amountMinor + ", currencyCode=" + this.currencyCode + ", accountName=" + this.accountName + ", accountType=" + this.accountType + ", createdAt=" + this.createdAt + ", note=" + this.note + ", originalInvoiceRateMilli=" + this.originalInvoiceRateMilli + ", collectionRateMilli=" + this.collectionRateMilli + ", effectiveRateMilli=" + this.effectiveRateMilli + ", usdReferenceReductionCents=" + this.usdReferenceReductionCents + ")";
    }

    public CustomerPaymentRecord(String id, long j, String currencyCode, String accountName, String accountType, String createdAt, String str, long j2, long j3, long j4, long j5) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
        Intrinsics.checkNotNullParameter(accountName, "accountName");
        Intrinsics.checkNotNullParameter(accountType, "accountType");
        Intrinsics.checkNotNullParameter(createdAt, "createdAt");
        this.id = id;
        this.amountMinor = j;
        this.currencyCode = currencyCode;
        this.accountName = accountName;
        this.accountType = accountType;
        this.createdAt = createdAt;
        this.note = str;
        this.originalInvoiceRateMilli = j2;
        this.collectionRateMilli = j3;
        this.effectiveRateMilli = j4;
        this.usdReferenceReductionCents = j5;
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

    public final String getAccountName() {
        return this.accountName;
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

    public final long getOriginalInvoiceRateMilli() {
        return this.originalInvoiceRateMilli;
    }

    public final long getCollectionRateMilli() {
        return this.collectionRateMilli;
    }

    public final long getEffectiveRateMilli() {
        return this.effectiveRateMilli;
    }

    public final long getUsdReferenceReductionCents() {
        return this.usdReferenceReductionCents;
    }
}
