package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OperatingExpenseRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b&\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bs\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\n\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003J\t\u0010'\u001a\u00020\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\nH\u00c6\u0003J\t\u0010)\u001a\u00020\u0003H\u00c6\u0003J\t\u0010*\u001a\u00020\nH\u00c6\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\t\u0010.\u001a\u00020\u0003H\u00c6\u0003J\u008f\u0001\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u0003H\u00c6\u0001J\u0013\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00103\u001a\u000204H\u00d6\u0001J\t\u00105\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0011\u0010\f\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0014R\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0014R\u0011\u0010\u0010\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0014\u00a8\u00066"}, d2 = {"Lcom/hazem/dailystore/data/OperatingExpense;", "", "id", "", "date", "categoryId", "categoryName", "accountId", "accountName", "amountMinor", "", "currencyCode", "amountUsdCents", "reference", "notes", "createdBy", "createdAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getDate", "getCategoryId", "getCategoryName", "getAccountId", "getAccountName", "getAmountMinor", "()J", "getCurrencyCode", "getAmountUsdCents", "getReference", "getNotes", "getCreatedBy", "getCreatedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class OperatingExpense {
    private final String accountId;
    private final String accountName;
    private final long amountMinor;
    private final long amountUsdCents;
    private final String categoryId;
    private final String categoryName;
    private final String createdAt;
    private final String createdBy;
    private final String currencyCode;
    private final String date;
    private final String id;
    private final String notes;
    private final String reference;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getReference() {
        return this.reference;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getNotes() {
        return this.notes;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getCreatedBy() {
        return this.createdBy;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDate() {
        return this.date;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getCategoryId() {
        return this.categoryId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getCategoryName() {
        return this.categoryName;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getAccountId() {
        return this.accountId;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getAccountName() {
        return this.accountName;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final long getAmountMinor() {
        return this.amountMinor;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getCurrencyCode() {
        return this.currencyCode;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final long getAmountUsdCents() {
        return this.amountUsdCents;
    }

    public final OperatingExpense copy(String id, String date, String categoryId, String categoryName, String accountId, String accountName, long amountMinor, String currencyCode, long amountUsdCents, String reference, String notes, String createdBy, String createdAt) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(categoryId, "categoryId");
        Intrinsics.checkNotNullParameter(categoryName, "categoryName");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Intrinsics.checkNotNullParameter(accountName, "accountName");
        Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
        Intrinsics.checkNotNullParameter(createdBy, "createdBy");
        Intrinsics.checkNotNullParameter(createdAt, "createdAt");
        return new OperatingExpense(id, date, categoryId, categoryName, accountId, accountName, amountMinor, currencyCode, amountUsdCents, reference, notes, createdBy, createdAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OperatingExpense)) {
            return false;
        }
        OperatingExpense operatingExpense = (OperatingExpense) other;
        return Intrinsics.areEqual(this.id, operatingExpense.id) && Intrinsics.areEqual(this.date, operatingExpense.date) && Intrinsics.areEqual(this.categoryId, operatingExpense.categoryId) && Intrinsics.areEqual(this.categoryName, operatingExpense.categoryName) && Intrinsics.areEqual(this.accountId, operatingExpense.accountId) && Intrinsics.areEqual(this.accountName, operatingExpense.accountName) && this.amountMinor == operatingExpense.amountMinor && Intrinsics.areEqual(this.currencyCode, operatingExpense.currencyCode) && this.amountUsdCents == operatingExpense.amountUsdCents && Intrinsics.areEqual(this.reference, operatingExpense.reference) && Intrinsics.areEqual(this.notes, operatingExpense.notes) && Intrinsics.areEqual(this.createdBy, operatingExpense.createdBy) && Intrinsics.areEqual(this.createdAt, operatingExpense.createdAt);
    }

    public int hashCode() {
        int iHashCode = ((((((((((((((((this.id.hashCode() * 31) + this.date.hashCode()) * 31) + this.categoryId.hashCode()) * 31) + this.categoryName.hashCode()) * 31) + this.accountId.hashCode()) * 31) + this.accountName.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.amountMinor)) * 31) + this.currencyCode.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.amountUsdCents)) * 31;
        String str = this.reference;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.notes;
        return ((((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + this.createdBy.hashCode()) * 31) + this.createdAt.hashCode();
    }

    public String toString() {
        return "OperatingExpense(id=" + this.id + ", date=" + this.date + ", categoryId=" + this.categoryId + ", categoryName=" + this.categoryName + ", accountId=" + this.accountId + ", accountName=" + this.accountName + ", amountMinor=" + this.amountMinor + ", currencyCode=" + this.currencyCode + ", amountUsdCents=" + this.amountUsdCents + ", reference=" + this.reference + ", notes=" + this.notes + ", createdBy=" + this.createdBy + ", createdAt=" + this.createdAt + ")";
    }

    public OperatingExpense(String id, String date, String categoryId, String categoryName, String accountId, String accountName, long j, String currencyCode, long j2, String str, String str2, String createdBy, String createdAt) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(categoryId, "categoryId");
        Intrinsics.checkNotNullParameter(categoryName, "categoryName");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Intrinsics.checkNotNullParameter(accountName, "accountName");
        Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
        Intrinsics.checkNotNullParameter(createdBy, "createdBy");
        Intrinsics.checkNotNullParameter(createdAt, "createdAt");
        this.id = id;
        this.date = date;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.accountId = accountId;
        this.accountName = accountName;
        this.amountMinor = j;
        this.currencyCode = currencyCode;
        this.amountUsdCents = j2;
        this.reference = str;
        this.notes = str2;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public final String getCategoryId() {
        return this.categoryId;
    }

    public final String getCategoryName() {
        return this.categoryName;
    }

    public final String getDate() {
        return this.date;
    }

    public final String getId() {
        return this.id;
    }

    public final String getAccountId() {
        return this.accountId;
    }

    public final String getAccountName() {
        return this.accountName;
    }

    public final long getAmountMinor() {
        return this.amountMinor;
    }

    public final long getAmountUsdCents() {
        return this.amountUsdCents;
    }

    public final String getCurrencyCode() {
        return this.currencyCode;
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final String getCreatedBy() {
        return this.createdBy;
    }

    public final String getNotes() {
        return this.notes;
    }

    public final String getReference() {
        return this.reference;
    }
}
