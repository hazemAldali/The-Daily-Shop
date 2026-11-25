package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PartyStatementRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b*\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0089\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t\u0012\b\b\u0002\u0010\f\u001a\u00020\t\u0012\b\b\u0002\u0010\r\u001a\u00020\t\u0012\b\b\u0002\u0010\u000e\u001a\u00020\t\u0012\b\b\u0002\u0010\u000f\u001a\u00020\t\u0012\b\b\u0002\u0010\u0010\u001a\u00020\t\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0013J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003J\t\u0010'\u001a\u00020\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\tH\u00c6\u0003J\t\u0010*\u001a\u00020\tH\u00c6\u0003J\t\u0010+\u001a\u00020\tH\u00c6\u0003J\t\u0010,\u001a\u00020\tH\u00c6\u0003J\t\u0010-\u001a\u00020\tH\u00c6\u0003J\t\u0010.\u001a\u00020\tH\u00c6\u0003J\t\u0010/\u001a\u00020\tH\u00c6\u0003J\t\u00100\u001a\u00020\tH\u00c6\u0003J\t\u00101\u001a\u00020\u0003H\u00c6\u0003J\u0095\u0001\u00102\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\t2\b\b\u0002\u0010\u0011\u001a\u00020\u0003H\u00c6\u0001J\u0013\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00106\u001a\u000207H\u00d6\u0001J\t\u00108\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\n\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0011\u0010\u000b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u0011\u0010\f\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001bR\u0011\u0010\r\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001bR\u0011\u0010\u000e\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001bR\u0011\u0010\u000f\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001bR\u0011\u0010\u0010\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001bR\u0011\u0010\u0011\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0015\u00a8\u00069"}, d2 = {"Lcom/hazem/dailystore/data/PartyStatementEntry;", "", "id", "", "date", "type", "reference", "description", "debitUsdCents", "", "creditUsdCents", "debitSypMinor", "creditSypMinor", "cashRefundUsdCents", "cashRefundSypMinor", "balanceUsdCents", "balanceSypMinor", "sortAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJJJJJJJLjava/lang/String;)V", "getId", "()Ljava/lang/String;", "getDate", "getType", "getReference", "getDescription", "getDebitUsdCents", "()J", "getCreditUsdCents", "getDebitSypMinor", "getCreditSypMinor", "getCashRefundUsdCents", "getCashRefundSypMinor", "getBalanceUsdCents", "getBalanceSypMinor", "getSortAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class PartyStatementEntry {
    private final long balanceSypMinor;
    private final long balanceUsdCents;
    private final long cashRefundSypMinor;
    private final long cashRefundUsdCents;
    private final long creditSypMinor;
    private final long creditUsdCents;
    private final String date;
    private final long debitSypMinor;
    private final long debitUsdCents;
    private final String description;
    private final String id;
    private final String reference;
    private final String sortAt;
    private final String type;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final long getCashRefundUsdCents() {
        return this.cashRefundUsdCents;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final long getCashRefundSypMinor() {
        return this.cashRefundSypMinor;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final long getBalanceUsdCents() {
        return this.balanceUsdCents;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final long getBalanceSypMinor() {
        return this.balanceSypMinor;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getSortAt() {
        return this.sortAt;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDate() {
        return this.date;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getReference() {
        return this.reference;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getDebitUsdCents() {
        return this.debitUsdCents;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final long getCreditUsdCents() {
        return this.creditUsdCents;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final long getDebitSypMinor() {
        return this.debitSypMinor;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final long getCreditSypMinor() {
        return this.creditSypMinor;
    }

    public final PartyStatementEntry copy(String id, String date, String type, String reference, String description, long debitUsdCents, long creditUsdCents, long debitSypMinor, long creditSypMinor, long cashRefundUsdCents, long cashRefundSypMinor, long balanceUsdCents, long balanceSypMinor, String sortAt) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(reference, "reference");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(sortAt, "sortAt");
        return new PartyStatementEntry(id, date, type, reference, description, debitUsdCents, creditUsdCents, debitSypMinor, creditSypMinor, cashRefundUsdCents, cashRefundSypMinor, balanceUsdCents, balanceSypMinor, sortAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PartyStatementEntry)) {
            return false;
        }
        PartyStatementEntry partyStatementEntry = (PartyStatementEntry) other;
        return Intrinsics.areEqual(this.id, partyStatementEntry.id) && Intrinsics.areEqual(this.date, partyStatementEntry.date) && Intrinsics.areEqual(this.type, partyStatementEntry.type) && Intrinsics.areEqual(this.reference, partyStatementEntry.reference) && Intrinsics.areEqual(this.description, partyStatementEntry.description) && this.debitUsdCents == partyStatementEntry.debitUsdCents && this.creditUsdCents == partyStatementEntry.creditUsdCents && this.debitSypMinor == partyStatementEntry.debitSypMinor && this.creditSypMinor == partyStatementEntry.creditSypMinor && this.cashRefundUsdCents == partyStatementEntry.cashRefundUsdCents && this.cashRefundSypMinor == partyStatementEntry.cashRefundSypMinor && this.balanceUsdCents == partyStatementEntry.balanceUsdCents && this.balanceSypMinor == partyStatementEntry.balanceSypMinor && Intrinsics.areEqual(this.sortAt, partyStatementEntry.sortAt);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((this.id.hashCode() * 31) + this.date.hashCode()) * 31) + this.type.hashCode()) * 31) + this.reference.hashCode()) * 31) + this.description.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.debitUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.creditUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.debitSypMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.creditSypMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.cashRefundUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.cashRefundSypMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.balanceUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.balanceSypMinor)) * 31) + this.sortAt.hashCode();
    }

    public String toString() {
        return "PartyStatementEntry(id=" + this.id + ", date=" + this.date + ", type=" + this.type + ", reference=" + this.reference + ", description=" + this.description + ", debitUsdCents=" + this.debitUsdCents + ", creditUsdCents=" + this.creditUsdCents + ", debitSypMinor=" + this.debitSypMinor + ", creditSypMinor=" + this.creditSypMinor + ", cashRefundUsdCents=" + this.cashRefundUsdCents + ", cashRefundSypMinor=" + this.cashRefundSypMinor + ", balanceUsdCents=" + this.balanceUsdCents + ", balanceSypMinor=" + this.balanceSypMinor + ", sortAt=" + this.sortAt + ")";
    }

    public PartyStatementEntry(String id, String date, String type, String reference, String description, long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, String sortAt) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(reference, "reference");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(sortAt, "sortAt");
        this.id = id;
        this.date = date;
        this.type = type;
        this.reference = reference;
        this.description = description;
        this.debitUsdCents = j;
        this.creditUsdCents = j2;
        this.debitSypMinor = j3;
        this.creditSypMinor = j4;
        this.cashRefundUsdCents = j5;
        this.cashRefundSypMinor = j6;
        this.balanceUsdCents = j7;
        this.balanceSypMinor = j8;
        this.sortAt = sortAt;
    }

    public /* synthetic */ PartyStatementEntry(String str, String str2, String str3, String str4, String str5, long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, String str6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, (i & 32) != 0 ? 0L : j, (i & 64) != 0 ? 0L : j2, (i & 128) != 0 ? 0L : j3, (i & 256) != 0 ? 0L : j4, (i & 512) != 0 ? 0L : j5, (i & 1024) != 0 ? 0L : j6, (i & 2048) != 0 ? 0L : j7, (i & 4096) != 0 ? 0L : j8, (i & 8192) != 0 ? str2 : str6);
    }

    public final String getId() {
        return this.id;
    }

    public final String getDate() {
        return this.date;
    }

    public final String getType() {
        return this.type;
    }

    public final String getReference() {
        return this.reference;
    }

    public final String getDescription() {
        return this.description;
    }

    public final long getDebitUsdCents() {
        return this.debitUsdCents;
    }

    public final long getCreditUsdCents() {
        return this.creditUsdCents;
    }

    public final long getDebitSypMinor() {
        return this.debitSypMinor;
    }

    public final long getCreditSypMinor() {
        return this.creditSypMinor;
    }

    public final long getCashRefundUsdCents() {
        return this.cashRefundUsdCents;
    }

    public final long getCashRefundSypMinor() {
        return this.cashRefundSypMinor;
    }

    public final long getBalanceUsdCents() {
        return this.balanceUsdCents;
    }

    public final long getBalanceSypMinor() {
        return this.balanceSypMinor;
    }

    public final String getSortAt() {
        return this.sortAt;
    }
}
