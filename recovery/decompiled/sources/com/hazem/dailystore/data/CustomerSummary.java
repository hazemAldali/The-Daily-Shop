package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomerRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\fH\u00c6\u0003J_\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\fH\u00c6\u0001J\u0013\u0010\"\u001a\u00020\f2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010$\u001a\u00020%H\u00d6\u0001J\t\u0010&\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0018\u00a8\u0006'"}, d2 = {"Lcom/hazem/dailystore/data/CustomerSummary;", "", "id", "", "name", "phone", "address", "notes", "openingBalanceMinor", "", "openingBalanceCurrency", "isActive", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Z)V", "getId", "()Ljava/lang/String;", "getName", "getPhone", "getAddress", "getNotes", "getOpeningBalanceMinor", "()J", "getOpeningBalanceCurrency", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class CustomerSummary {
    private final String address;
    private final String id;
    private final boolean isActive;
    private final String name;
    private final String notes;
    private final String openingBalanceCurrency;
    private final long openingBalanceMinor;
    private final String phone;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPhone() {
        return this.phone;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getAddress() {
        return this.address;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getNotes() {
        return this.notes;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getOpeningBalanceMinor() {
        return this.openingBalanceMinor;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getOpeningBalanceCurrency() {
        return this.openingBalanceCurrency;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final boolean getIsActive() {
        return this.isActive;
    }

    public final CustomerSummary copy(String id, String name, String phone, String address, String notes, long openingBalanceMinor, String openingBalanceCurrency, boolean isActive) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(openingBalanceCurrency, "openingBalanceCurrency");
        return new CustomerSummary(id, name, phone, address, notes, openingBalanceMinor, openingBalanceCurrency, isActive);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomerSummary)) {
            return false;
        }
        CustomerSummary customerSummary = (CustomerSummary) other;
        return Intrinsics.areEqual(this.id, customerSummary.id) && Intrinsics.areEqual(this.name, customerSummary.name) && Intrinsics.areEqual(this.phone, customerSummary.phone) && Intrinsics.areEqual(this.address, customerSummary.address) && Intrinsics.areEqual(this.notes, customerSummary.notes) && this.openingBalanceMinor == customerSummary.openingBalanceMinor && Intrinsics.areEqual(this.openingBalanceCurrency, customerSummary.openingBalanceCurrency) && this.isActive == customerSummary.isActive;
    }

    public int hashCode() {
        int iHashCode = ((this.id.hashCode() * 31) + this.name.hashCode()) * 31;
        String str = this.phone;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.address;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.notes;
        return ((((((iHashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.openingBalanceMinor)) * 31) + this.openingBalanceCurrency.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.isActive);
    }

    public String toString() {
        return "CustomerSummary(id=" + this.id + ", name=" + this.name + ", phone=" + this.phone + ", address=" + this.address + ", notes=" + this.notes + ", openingBalanceMinor=" + this.openingBalanceMinor + ", openingBalanceCurrency=" + this.openingBalanceCurrency + ", isActive=" + this.isActive + ")";
    }

    public CustomerSummary(String id, String name, String str, String str2, String str3, long j, String openingBalanceCurrency, boolean z) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(openingBalanceCurrency, "openingBalanceCurrency");
        this.id = id;
        this.name = name;
        this.phone = str;
        this.address = str2;
        this.notes = str3;
        this.openingBalanceMinor = j;
        this.openingBalanceCurrency = openingBalanceCurrency;
        this.isActive = z;
    }

    public final String getAddress() {
        return this.address;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPhone() {
        return this.phone;
    }

    public final String getNotes() {
        return this.notes;
    }

    public final String getOpeningBalanceCurrency() {
        return this.openingBalanceCurrency;
    }

    public final long getOpeningBalanceMinor() {
        return this.openingBalanceMinor;
    }

    public final boolean isActive() {
        return this.isActive;
    }
}
