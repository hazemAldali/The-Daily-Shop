package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PartyStatementRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\nH\u00c6\u0003J\t\u0010 \u001a\u00020\nH\u00c6\u0003J\t\u0010!\u001a\u00020\rH\u00c6\u0003JU\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\rH\u00c6\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010&\u001a\u00020\rH\u00d6\u0001J\t\u0010'\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a\u00a8\u0006("}, d2 = {"Lcom/hazem/dailystore/data/PartyStatement;", "", "partyId", "", "partyName", "partyType", "entries", "", "Lcom/hazem/dailystore/data/PartyStatementEntry;", "closingBalanceUsdCents", "", "closingBalanceSypMinor", "unpricedEntryCount", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;JJI)V", "getPartyId", "()Ljava/lang/String;", "getPartyName", "getPartyType", "getEntries", "()Ljava/util/List;", "getClosingBalanceUsdCents", "()J", "getClosingBalanceSypMinor", "getUnpricedEntryCount", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class PartyStatement {
    private final long closingBalanceSypMinor;
    private final long closingBalanceUsdCents;
    private final List<PartyStatementEntry> entries;
    private final String partyId;
    private final String partyName;
    private final String partyType;
    private final int unpricedEntryCount;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPartyId() {
        return this.partyId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPartyName() {
        return this.partyName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPartyType() {
        return this.partyType;
    }

    public final List<PartyStatementEntry> component4() {
        return this.entries;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getClosingBalanceUsdCents() {
        return this.closingBalanceUsdCents;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getClosingBalanceSypMinor() {
        return this.closingBalanceSypMinor;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getUnpricedEntryCount() {
        return this.unpricedEntryCount;
    }

    public final PartyStatement copy(String partyId, String partyName, String partyType, List<PartyStatementEntry> entries, long closingBalanceUsdCents, long closingBalanceSypMinor, int unpricedEntryCount) {
        Intrinsics.checkNotNullParameter(partyId, "partyId");
        Intrinsics.checkNotNullParameter(partyName, "partyName");
        Intrinsics.checkNotNullParameter(partyType, "partyType");
        Intrinsics.checkNotNullParameter(entries, "entries");
        return new PartyStatement(partyId, partyName, partyType, entries, closingBalanceUsdCents, closingBalanceSypMinor, unpricedEntryCount);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PartyStatement)) {
            return false;
        }
        PartyStatement partyStatement = (PartyStatement) other;
        return Intrinsics.areEqual(this.partyId, partyStatement.partyId) && Intrinsics.areEqual(this.partyName, partyStatement.partyName) && Intrinsics.areEqual(this.partyType, partyStatement.partyType) && Intrinsics.areEqual(this.entries, partyStatement.entries) && this.closingBalanceUsdCents == partyStatement.closingBalanceUsdCents && this.closingBalanceSypMinor == partyStatement.closingBalanceSypMinor && this.unpricedEntryCount == partyStatement.unpricedEntryCount;
    }

    public int hashCode() {
        return (((((((((((this.partyId.hashCode() * 31) + this.partyName.hashCode()) * 31) + this.partyType.hashCode()) * 31) + this.entries.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.closingBalanceUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.closingBalanceSypMinor)) * 31) + this.unpricedEntryCount;
    }

    public String toString() {
        return "PartyStatement(partyId=" + this.partyId + ", partyName=" + this.partyName + ", partyType=" + this.partyType + ", entries=" + this.entries + ", closingBalanceUsdCents=" + this.closingBalanceUsdCents + ", closingBalanceSypMinor=" + this.closingBalanceSypMinor + ", unpricedEntryCount=" + this.unpricedEntryCount + ")";
    }

    public PartyStatement(String partyId, String partyName, String partyType, List<PartyStatementEntry> entries, long j, long j2, int i) {
        Intrinsics.checkNotNullParameter(partyId, "partyId");
        Intrinsics.checkNotNullParameter(partyName, "partyName");
        Intrinsics.checkNotNullParameter(partyType, "partyType");
        Intrinsics.checkNotNullParameter(entries, "entries");
        this.partyId = partyId;
        this.partyName = partyName;
        this.partyType = partyType;
        this.entries = entries;
        this.closingBalanceUsdCents = j;
        this.closingBalanceSypMinor = j2;
        this.unpricedEntryCount = i;
    }

    public final String getPartyId() {
        return this.partyId;
    }

    public final String getPartyName() {
        return this.partyName;
    }

    public final String getPartyType() {
        return this.partyType;
    }

    public final List<PartyStatementEntry> getEntries() {
        return this.entries;
    }

    public final long getClosingBalanceUsdCents() {
        return this.closingBalanceUsdCents;
    }

    public final long getClosingBalanceSypMinor() {
        return this.closingBalanceSypMinor;
    }

    public final int getUnpricedEntryCount() {
        return this.unpricedEntryCount;
    }
}
