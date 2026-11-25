package com.hazem.dailystore.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import androidx.core.app.NotificationCompat;
import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import net.zetetic.database.sqlcipher.SQLiteDatabase;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: SyncDomainApplier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 72\u00020\u0001:\u0003567B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u0013\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u0014\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u0015\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u0016\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u0017\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u0018\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u0019\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u001a\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ \u0010\u001b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u000eH\u0002J\u000e\u0010\u001e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u001f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010 \u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010!\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\"\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010#\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010$\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010%\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010&\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010'\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ&\u0010(\u001a\u0004\u0018\u00010\u000e2\n\u0010)\u001a\u00060*j\u0002`+2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u000eH\u0002J\u0016\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.2\u0006\u00100\u001a\u000201H\u0002J\u0010\u00102\u001a\u00020\u000e2\u0006\u00103\u001a\u00020\u000eH\u0002J\b\u00104\u001a\u00020\u000eH\u0002R\u0018\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00068"}, d2 = {"Lcom/hazem/dailystore/data/SyncDomainApplier;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "appContext", "kotlin.jvm.PlatformType", "Landroid/content/Context;", "database", "Lcom/hazem/dailystore/data/AppDatabase;", "applyReceivedOperation", "Lcom/hazem/dailystore/data/SyncDomainApplier$ApplyResult;", "operationId", "", "applyReceivedStoreTimeZone", "applyReceivedExchangeRate", "applyReceivedInvoice", "applyReceivedOperatingExpense", "applyReceivedSupplierPayment", "applyReceivedCustomerCollection", "applyReceivedSupplierRollover", "applyReceivedServiceTransfer", "applyReceivedServiceTransferReceivable", "applyReceivedServiceTransferDebtCollection", "applyReceivedSalesReturn", "applyReceivedPurchaseReturn", "applyReceivedReturn", "businessOperation", "entityType", "applyReceivedInventoryAdjustment", "applyReceivedCashAccount", "applyReceivedCustomer", "applyReceivedCustomerActive", "applyReceivedProduct", "applyReceivedProductActive", "applyReceivedProductOpeningStock", "applyReceivedUnit", "applyReceivedCategory", "applyReceivedTransportProbe", "resultReference", "db", "Lnet/zetetic/database/sqlcipher/SQLiteDatabase;", "Lcom/hazem/dailystore/data/AppSqliteDatabase;", "type", "parseLines", "", "Lcom/hazem/dailystore/data/InvoiceLineInput;", "items", "Lorg/json/JSONArray;", "operatingDateForTimestamp", "value", "timestamp", "ApplyResult", "InboxCommand", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SyncDomainApplier {
    private final Context appContext;
    private final AppDatabase database;
    private static final Object APPLY_LOCK = new Object();

    public SyncDomainApplier(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Context appContext = context.getApplicationContext();
        this.appContext = appContext;
        AppDatabase.Companion companion = AppDatabase.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        this.database = companion.getInstance(appContext);
    }

    /* JADX INFO: compiled from: SyncDomainApplier.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J)\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n\u00a8\u0006\u0017"}, d2 = {"Lcom/hazem/dailystore/data/SyncDomainApplier$ApplyResult;", "", "operationId", "", "applied", "", "reason", "<init>", "(Ljava/lang/String;ZLjava/lang/String;)V", "getOperationId", "()Ljava/lang/String;", "getApplied", "()Z", "getReason", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class ApplyResult {
        private final boolean applied;
        private final String operationId;
        private final String reason;

        public static /* synthetic */ ApplyResult copy$default(ApplyResult applyResult, String str, boolean z, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = applyResult.operationId;
            }
            if ((i & 2) != 0) {
                z = applyResult.applied;
            }
            if ((i & 4) != 0) {
                str2 = applyResult.reason;
            }
            return applyResult.copy(str, z, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getOperationId() {
            return this.operationId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getApplied() {
            return this.applied;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getReason() {
            return this.reason;
        }

        public final ApplyResult copy(String operationId, boolean applied, String reason) {
            Intrinsics.checkNotNullParameter(operationId, "operationId");
            return new ApplyResult(operationId, applied, reason);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ApplyResult)) {
                return false;
            }
            ApplyResult applyResult = (ApplyResult) other;
            return Intrinsics.areEqual(this.operationId, applyResult.operationId) && this.applied == applyResult.applied && Intrinsics.areEqual(this.reason, applyResult.reason);
        }

        public int hashCode() {
            int iHashCode = ((this.operationId.hashCode() * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.applied)) * 31;
            String str = this.reason;
            return iHashCode + (str == null ? 0 : str.hashCode());
        }

        public String toString() {
            return "ApplyResult(operationId=" + this.operationId + ", applied=" + this.applied + ", reason=" + this.reason + ")";
        }

        public ApplyResult(String operationId, boolean z, String str) {
            Intrinsics.checkNotNullParameter(operationId, "operationId");
            this.operationId = operationId;
            this.applied = z;
            this.reason = str;
        }

        public /* synthetic */ ApplyResult(String str, boolean z, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, z, (i & 4) != 0 ? null : str2);
        }

        public final String getOperationId() {
            return this.operationId;
        }

        public final boolean getApplied() {
            return this.applied;
        }

        public final String getReason() {
            return this.reason;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x0112, code lost:
    
        if (r0.equals("POST_PURCHASE_INVOICE") != false) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0176, code lost:
    
        if (r0.equals("POST_SALE_INVOICE") != false) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x017c, code lost:
    
        return applyReceivedInvoice(r4);
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final ApplyResult applyReceivedOperation(String operationId) throws IOException {
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        if (StringsKt.isBlank(operationId)) {
            throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0623\u0645\u0631 \u0645\u0637\u0644\u0648\u0628".toString());
        }
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery("SELECT payload_json FROM sync_inbox WHERE operation_id=?", new String[]{operationId});
        try {
            Cursor cursor = cursorRawQuery;
            if (!cursor.moveToFirst()) {
                throw new IllegalArgumentException("\u0627\u0644\u0623\u0645\u0631 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0641\u064a \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0648\u0627\u0631\u062f".toString());
            }
            String string = cursor.getString(0);
            CloseableKt.closeFinally(cursorRawQuery, null);
            String strOptString = new JSONObject(string).optString("business_operation");
            if (strOptString != null) {
                switch (strOptString.hashCode()) {
                    case -2017668012:
                        break;
                    case -1683422950:
                        if (strOptString.equals("COLLECT_CUSTOMER_PAYMENT")) {
                            return applyReceivedCustomerCollection(operationId);
                        }
                        break;
                    case -1476903623:
                        if (strOptString.equals("EXECUTE_SERVICE_TRANSFER_CREDIT")) {
                            return applyReceivedServiceTransfer(operationId);
                        }
                        break;
                    case -1296667201:
                        if (strOptString.equals("UPSERT_PRODUCT")) {
                            return applyReceivedProduct(operationId);
                        }
                        break;
                    case -1296441034:
                        if (strOptString.equals("SYNC_TRANSPORT_PROBE")) {
                            return applyReceivedTransportProbe(operationId);
                        }
                        break;
                    case -845573758:
                        if (strOptString.equals("SET_PRODUCT_OPENING_STOCK")) {
                            return applyReceivedProductOpeningStock(operationId);
                        }
                        break;
                    case -778583533:
                        if (strOptString.equals("SET_PRODUCT_ACTIVE")) {
                            return applyReceivedProductActive(operationId);
                        }
                        break;
                    case -383663813:
                        if (strOptString.equals("POST_OPERATING_EXPENSE")) {
                            return applyReceivedOperatingExpense(operationId);
                        }
                        break;
                    case -282919026:
                        break;
                    case -239936374:
                        if (strOptString.equals("SET_CUSTOMER_ACTIVE")) {
                            return applyReceivedCustomerActive(operationId);
                        }
                        break;
                    case 67959022:
                        if (strOptString.equals("UPSERT_CUSTOMER")) {
                            return applyReceivedCustomer(operationId);
                        }
                        break;
                    case 80159969:
                        if (strOptString.equals("CREATE_CATEGORY")) {
                            return applyReceivedCategory(operationId);
                        }
                        break;
                    case 258855018:
                        if (strOptString.equals("UPDATE_STORE_TIME_ZONE")) {
                            return applyReceivedStoreTimeZone(operationId);
                        }
                        break;
                    case 271463222:
                        if (strOptString.equals("UPSERT_SERVICE_TRANSFER_RECEIVABLE")) {
                            return applyReceivedServiceTransferReceivable(operationId);
                        }
                        break;
                    case 787616039:
                        if (strOptString.equals("SET_SUPPLIER_ROLLOVER")) {
                            return applyReceivedSupplierRollover(operationId);
                        }
                        break;
                    case 980150322:
                        if (strOptString.equals("POST_SUPPLIER_PAYMENT")) {
                            return applyReceivedSupplierPayment(operationId);
                        }
                        break;
                    case 987633954:
                        if (strOptString.equals("POST_SALES_RETURN")) {
                            return applyReceivedSalesReturn(operationId);
                        }
                        break;
                    case 1007144465:
                        if (strOptString.equals("UPSERT_CASH_ACCOUNT")) {
                            return applyReceivedCashAccount(operationId);
                        }
                        break;
                    case 1080252047:
                        if (strOptString.equals("POST_INVENTORY_ADJUSTMENT")) {
                            return applyReceivedInventoryAdjustment(operationId);
                        }
                        break;
                    case 1317688383:
                        if (strOptString.equals("SET_EXCHANGE_RATE")) {
                            return applyReceivedExchangeRate(operationId);
                        }
                        break;
                    case 1318921928:
                        if (strOptString.equals("COLLECT_SERVICE_TRANSFER_DEBT")) {
                            return applyReceivedServiceTransferDebtCollection(operationId);
                        }
                        break;
                    case 1597792916:
                        if (strOptString.equals("UPSERT_UNIT")) {
                            return applyReceivedUnit(operationId);
                        }
                        break;
                    case 1764191375:
                        if (strOptString.equals("POST_PURCHASE_RETURN")) {
                            return applyReceivedPurchaseReturn(operationId);
                        }
                        break;
                }
            }
            throw new IllegalArgumentException("\u0646\u0648\u0639 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0627\u0644\u0648\u0627\u0631\u062f\u0629 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645 \u0628\u0639\u062f\u061b \u0628\u0642\u064a \u0627\u0644\u0623\u0645\u0631 \u0645\u062d\u0641\u0648\u0638\u0627\u064b \u062f\u0648\u0646 \u062a\u0637\u0628\u064a\u0642");
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    public final ApplyResult applyReceivedStoreTimeZone(String operationId) {
        String str;
        ApplyResult applyResult;
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        synchronized (APPLY_LOCK) {
            if (StringsKt.isBlank(operationId)) {
                throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0623\u0645\u0631 \u0645\u0637\u0644\u0648\u0628".toString());
            }
            SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
            Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
            SyncDeviceIdentity.Companion companion = SyncDeviceIdentity.INSTANCE;
            Context appContext = this.appContext;
            Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
            String orCreate = companion.getOrCreate(appContext);
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", (String[]) null);
            try {
                Cursor cursor = cursorRawQuery;
                String string = cursor.moveToFirst() ? cursor.getString(0) : null;
                CloseableKt.closeFinally(cursorRawQuery, null);
                Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='authority_device_id'", (String[]) null);
                try {
                    Cursor cursor2 = cursorRawQuery2;
                    String string2 = cursor2.moveToFirst() ? cursor2.getString(0) : null;
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    if (!Intrinsics.areEqual(string, "SECONDARY") || (str = string2) == null || StringsKt.isBlank(str) || Intrinsics.areEqual(string2, orCreate)) {
                        throw new IllegalArgumentException("\u062a\u0637\u0628\u064a\u0642 \u0625\u0639\u062f\u0627\u062f \u0627\u0644\u0645\u0646\u0637\u0642\u0629 \u0627\u0644\u0632\u0645\u0646\u064a\u0629 \u0627\u0644\u0648\u0627\u0631\u062f \u0645\u0633\u0645\u0648\u062d \u0639\u0644\u0649 \u062c\u0647\u0627\u0632 \u062b\u0627\u0646\u0648\u064a \u0645\u0631\u062a\u0628\u0637 \u0641\u0642\u0637".toString());
                    }
                    writableDatabase.beginTransaction();
                    try {
                        Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT origin_device_id,operation_type,entity_type,entity_id,payload_json,status\n                   FROM sync_inbox WHERE operation_id=?", new String[]{operationId});
                        try {
                            Cursor cursor3 = cursorRawQuery3;
                            if (!cursor3.moveToFirst()) {
                                throw new IllegalArgumentException("\u062a\u062d\u062f\u064a\u062b \u0627\u0644\u0645\u0646\u0637\u0642\u0629 \u0627\u0644\u0632\u0645\u0646\u064a\u0629 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0641\u064a \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0648\u0627\u0631\u062f".toString());
                            }
                            String[] strArr = {cursor3.getString(0), cursor3.getString(1), cursor3.getString(2), cursor3.getString(3), cursor3.getString(4), cursor3.getString(5)};
                            CloseableKt.closeFinally(cursorRawQuery3, null);
                            if (!Intrinsics.areEqual(strArr[0], string2) || !Intrinsics.areEqual(strArr[1], "UPDATE") || !Intrinsics.areEqual(strArr[2], "STORE_SETTINGS") || !Intrinsics.areEqual(strArr[3], "store_time_zone")) {
                                throw new IllegalArgumentException("\u0645\u0635\u062f\u0631 \u0623\u0648 \u0646\u0648\u0639 \u062a\u062d\u062f\u064a\u062b \u0627\u0644\u0645\u0646\u0637\u0642\u0629 \u0627\u0644\u0632\u0645\u0646\u064a\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                            }
                            if (Intrinsics.areEqual(strArr[5], "APPLIED")) {
                                writableDatabase.setTransactionSuccessful();
                                applyResult = new ApplyResult(operationId, true, null, 4, null);
                            } else {
                                if (!Intrinsics.areEqual(strArr[5], "RECEIVED")) {
                                    throw new IllegalArgumentException("\u062d\u0627\u0644\u0629 \u062a\u062d\u062f\u064a\u062b \u0627\u0644\u0645\u0646\u0637\u0642\u0629 \u0627\u0644\u0632\u0645\u0646\u064a\u0629 \u0644\u0627 \u062a\u0633\u0645\u062d \u0628\u0627\u0644\u062a\u0637\u0628\u064a\u0642".toString());
                                }
                                Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT sequence_number FROM sync_inbox WHERE operation_id=?", new String[]{operationId});
                                try {
                                    Cursor cursor4 = cursorRawQuery4;
                                    if (!cursor4.moveToFirst()) {
                                        throw new IllegalArgumentException("Failed requirement.".toString());
                                    }
                                    long j = cursor4.getLong(0);
                                    CloseableKt.closeFinally(cursorRawQuery4, null);
                                    Cursor cursorRawQuery5 = writableDatabase.rawQuery("SELECT COUNT(*) FROM sync_inbox\n                   WHERE entity_type='STORE_SETTINGS' AND entity_id='store_time_zone'\n                     AND status='RECEIVED' AND sequence_number<?", new String[]{String.valueOf(j)});
                                    try {
                                        Cursor cursor5 = cursorRawQuery5;
                                        cursor5.moveToFirst();
                                        int i = cursor5.getInt(0);
                                        CloseableKt.closeFinally(cursorRawQuery5, null);
                                        if (i != 0) {
                                            throw new IllegalArgumentException("\u064a\u062c\u0628 \u062a\u0637\u0628\u064a\u0642 \u062a\u062d\u062f\u064a\u062b\u0627\u062a \u0627\u0644\u0645\u0646\u0637\u0642\u0629 \u0627\u0644\u0632\u0645\u0646\u064a\u0629 \u062d\u0633\u0628 \u062a\u0631\u062a\u064a\u0628 \u0648\u0635\u0648\u0644\u0647\u0627".toString());
                                        }
                                        JSONObject jSONObject = new JSONObject(strArr[4]);
                                        if (!Intrinsics.areEqual(jSONObject.optString("business_operation"), "UPDATE_STORE_TIME_ZONE")) {
                                            throw new IllegalArgumentException("\u0646\u0648\u0639 \u0639\u0645\u0644\u064a\u0629 \u0625\u0639\u062f\u0627\u062f \u0627\u0644\u0645\u062a\u062c\u0631 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                        }
                                        String strOptString = jSONObject.optString("time_zone");
                                        StoreTime storeTime = StoreTime.INSTANCE;
                                        Intrinsics.checkNotNull(strOptString);
                                        if (!storeTime.isValidZoneId(strOptString)) {
                                            throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0645\u0646\u0637\u0642\u0629 \u0627\u0644\u0632\u0645\u0646\u064a\u0629 \u0627\u0644\u0648\u0627\u0631\u062f \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                        }
                                        StoreTime.INSTANCE.setZoneId(writableDatabase, strOptString, timestamp());
                                        ContentValues contentValues = new ContentValues();
                                        contentValues.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                        contentValues.put("applied_at", timestamp());
                                        contentValues.putNull("rejection_reason");
                                        Unit unit = Unit.INSTANCE;
                                        if (writableDatabase.update("sync_inbox", contentValues, "operation_id=? AND status='RECEIVED'", new String[]{operationId}) != 1) {
                                            throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062b\u0628\u064a\u062a \u062a\u0637\u0628\u064a\u0642 \u0625\u0639\u062f\u0627\u062f \u0627\u0644\u0645\u0646\u0637\u0642\u0629 \u0627\u0644\u0632\u0645\u0646\u064a\u0629".toString());
                                        }
                                        writableDatabase.setTransactionSuccessful();
                                        applyResult = new ApplyResult(operationId, true, null, 4, null);
                                    } catch (Throwable th) {
                                        try {
                                            throw th;
                                        } catch (Throwable th2) {
                                            CloseableKt.closeFinally(cursorRawQuery5, th);
                                            throw th2;
                                        }
                                    }
                                } catch (Throwable th3) {
                                    try {
                                        throw th3;
                                    } catch (Throwable th4) {
                                        CloseableKt.closeFinally(cursorRawQuery4, th3);
                                        throw th4;
                                    }
                                }
                            }
                            writableDatabase.endTransaction();
                        } catch (Throwable th5) {
                            try {
                                throw th5;
                            } catch (Throwable th6) {
                                CloseableKt.closeFinally(cursorRawQuery3, th5);
                                throw th6;
                            }
                        }
                    } catch (Throwable th7) {
                        writableDatabase.endTransaction();
                        throw th7;
                    }
                } catch (Throwable th8) {
                    try {
                        throw th8;
                    } catch (Throwable th9) {
                        CloseableKt.closeFinally(cursorRawQuery2, th8);
                        throw th9;
                    }
                }
            } catch (Throwable th10) {
                try {
                    throw th10;
                } catch (Throwable th11) {
                    CloseableKt.closeFinally(cursorRawQuery, th10);
                    throw th11;
                }
            }
        }
        return applyResult;
    }

    public final ApplyResult applyReceivedExchangeRate(String operationId) {
        String str;
        ApplyResult applyResult;
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        synchronized (APPLY_LOCK) {
            if (StringsKt.isBlank(operationId)) {
                throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0623\u0645\u0631 \u0645\u0637\u0644\u0648\u0628".toString());
            }
            SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
            Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
            SyncDeviceIdentity.Companion companion = SyncDeviceIdentity.INSTANCE;
            Context appContext = this.appContext;
            Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
            String orCreate = companion.getOrCreate(appContext);
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", (String[]) null);
            try {
                Cursor cursor = cursorRawQuery;
                String string = cursor.moveToFirst() ? cursor.getString(0) : null;
                CloseableKt.closeFinally(cursorRawQuery, null);
                Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='authority_device_id'", (String[]) null);
                try {
                    Cursor cursor2 = cursorRawQuery2;
                    String string2 = cursor2.moveToFirst() ? cursor2.getString(0) : null;
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    if (!Intrinsics.areEqual(string, "SECONDARY") || (str = string2) == null || StringsKt.isBlank(str) || Intrinsics.areEqual(string2, orCreate)) {
                        throw new IllegalArgumentException("\u062a\u0637\u0628\u064a\u0642 \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0627\u0644\u0648\u0627\u0631\u062f \u0645\u0633\u0645\u0648\u062d \u0639\u0644\u0649 \u062c\u0647\u0627\u0632 \u062b\u0627\u0646\u0648\u064a \u0645\u0631\u062a\u0628\u0637 \u0641\u0642\u0637".toString());
                    }
                    writableDatabase.beginTransaction();
                    try {
                        Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT origin_device_id,operation_type,entity_type,entity_id,payload_json,status,\n                          sequence_number,origin_created_at\n                   FROM sync_inbox WHERE operation_id=?", new String[]{operationId});
                        try {
                            Cursor cursor3 = cursorRawQuery3;
                            if (!cursor3.moveToFirst()) {
                                throw new IllegalArgumentException("\u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0641\u064a \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0648\u0627\u0631\u062f".toString());
                            }
                            String[] strArr = new String[8];
                            for (int i = 0; i < 8; i++) {
                                strArr[i] = cursor3.getString(i);
                            }
                            CloseableKt.closeFinally(cursorRawQuery3, null);
                            if (!Intrinsics.areEqual(strArr[0], string2) || !Intrinsics.areEqual(strArr[1], "CREATE") || !Intrinsics.areEqual(strArr[2], "EXCHANGE_RATE")) {
                                throw new IllegalArgumentException("\u0645\u0635\u062f\u0631 \u0623\u0648 \u0646\u0648\u0639 \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0627\u0644\u0648\u0627\u0631\u062f \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                            }
                            if (Intrinsics.areEqual(strArr[5], "APPLIED")) {
                                writableDatabase.setTransactionSuccessful();
                                applyResult = new ApplyResult(operationId, true, null, 4, null);
                            } else {
                                if (!Intrinsics.areEqual(strArr[5], "RECEIVED")) {
                                    throw new IllegalArgumentException("\u062d\u0627\u0644\u0629 \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0644\u0627 \u062a\u0633\u0645\u062d \u0628\u0627\u0644\u062a\u0637\u0628\u064a\u0642".toString());
                                }
                                String str2 = strArr[6];
                                Intrinsics.checkNotNullExpressionValue(str2, "get(...)");
                                Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT COUNT(*) FROM sync_inbox\n                   WHERE entity_type='EXCHANGE_RATE' AND status='RECEIVED' AND sequence_number<?", new String[]{String.valueOf(Long.parseLong(str2))});
                                try {
                                    Cursor cursor4 = cursorRawQuery4;
                                    cursor4.moveToFirst();
                                    int i2 = cursor4.getInt(0);
                                    CloseableKt.closeFinally(cursorRawQuery4, null);
                                    if (i2 != 0) {
                                        throw new IllegalArgumentException("\u064a\u062c\u0628 \u062a\u0637\u0628\u064a\u0642 \u0623\u0633\u0639\u0627\u0631 \u0627\u0644\u0635\u0631\u0641 \u062d\u0633\u0628 \u062a\u0631\u062a\u064a\u0628 \u0635\u062f\u0648\u0631\u0647\u0627 \u0645\u0646 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a".toString());
                                    }
                                    JSONObject jSONObject = new JSONObject(strArr[4]);
                                    if (!Intrinsics.areEqual(jSONObject.optString("business_operation"), "SET_EXCHANGE_RATE") || !Intrinsics.areEqual(jSONObject.optString("id"), strArr[3]) || !Intrinsics.areEqual(jSONObject.optString("currency_code"), "USD")) {
                                        throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0627\u0644\u0648\u0627\u0631\u062f\u0629 \u0644\u0627 \u062a\u0637\u0627\u0628\u0642 \u0627\u0644\u0643\u064a\u0627\u0646".toString());
                                    }
                                    long jOptLong = jSONObject.optLong("rate_syp_per_usd_milli", 0L);
                                    String strOptString = jSONObject.optString("effective_at");
                                    String strOptString2 = jSONObject.optString("created_at");
                                    Intrinsics.checkNotNull(strOptString2);
                                    if (!new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(strOptString2)) {
                                        strOptString2 = null;
                                    }
                                    if (strOptString2 == null) {
                                        strOptString2 = strArr[7];
                                    }
                                    if (jOptLong > 0) {
                                        Intrinsics.checkNotNull(strOptString);
                                        if (new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(strOptString)) {
                                            Intrinsics.checkNotNull(strOptString2);
                                            if (new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(strOptString2)) {
                                                Cursor cursorRawQuery5 = writableDatabase.rawQuery("SELECT currency_code,rate_syp_per_usd_milli,effective_at,created_at FROM exchange_rates WHERE id=?", new String[]{strArr[3]});
                                                try {
                                                    Cursor cursor5 = cursorRawQuery5;
                                                    String[] strArr2 = !cursor5.moveToFirst() ? null : new String[]{cursor5.getString(0), String.valueOf(cursor5.getLong(1)), cursor5.getString(2), cursor5.getString(3)};
                                                    CloseableKt.closeFinally(cursorRawQuery5, null);
                                                    if (strArr2 == null) {
                                                        ContentValues contentValues = new ContentValues();
                                                        contentValues.put("id", strArr[3]);
                                                        contentValues.put("currency_code", "USD");
                                                        contentValues.put("rate_syp_per_usd_milli", Long.valueOf(jOptLong));
                                                        contentValues.put("effective_at", strOptString);
                                                        contentValues.putNull("created_by");
                                                        contentValues.put("created_at", strOptString2);
                                                        Unit unit = Unit.INSTANCE;
                                                        writableDatabase.insertOrThrow("exchange_rates", null, contentValues);
                                                    } else if (!Intrinsics.areEqual(strArr2[0], "USD") || !Intrinsics.areEqual(strArr2[1], String.valueOf(jOptLong)) || !Intrinsics.areEqual(strArr2[2], strOptString) || !Intrinsics.areEqual(strArr2[3], strOptString2)) {
                                                        throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0645\u0648\u062c\u0648\u062f \u0645\u0633\u0628\u0642\u0627\u064b \u0628\u0628\u064a\u0627\u0646\u0627\u062a \u0645\u062e\u062a\u0644\u0641\u0629".toString());
                                                    }
                                                    ContentValues contentValues2 = new ContentValues();
                                                    contentValues2.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                                    contentValues2.put("applied_at", timestamp());
                                                    contentValues2.putNull("rejection_reason");
                                                    Unit unit2 = Unit.INSTANCE;
                                                    if (writableDatabase.update("sync_inbox", contentValues2, "operation_id=? AND status='RECEIVED'", new String[]{operationId}) != 1) {
                                                        throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062b\u0628\u064a\u062a \u062a\u0637\u0628\u064a\u0642 \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641".toString());
                                                    }
                                                    writableDatabase.setTransactionSuccessful();
                                                    applyResult = new ApplyResult(operationId, true, null, 4, null);
                                                } catch (Throwable th) {
                                                    try {
                                                        throw th;
                                                    } catch (Throwable th2) {
                                                        CloseableKt.closeFinally(cursorRawQuery5, th);
                                                        throw th2;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    throw new IllegalArgumentException("\u0642\u064a\u0645\u0629 \u0623\u0648 \u062a\u0627\u0631\u064a\u062e \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0627\u0644\u0648\u0627\u0631\u062f \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                } catch (Throwable th3) {
                                    try {
                                        throw th3;
                                    } catch (Throwable th4) {
                                        CloseableKt.closeFinally(cursorRawQuery4, th3);
                                        throw th4;
                                    }
                                }
                            }
                            writableDatabase.endTransaction();
                        } catch (Throwable th5) {
                            try {
                                throw th5;
                            } catch (Throwable th6) {
                                CloseableKt.closeFinally(cursorRawQuery3, th5);
                                throw th6;
                            }
                        }
                    } catch (Throwable th7) {
                        writableDatabase.endTransaction();
                        throw th7;
                    }
                } catch (Throwable th8) {
                    try {
                        throw th8;
                    } catch (Throwable th9) {
                        CloseableKt.closeFinally(cursorRawQuery2, th8);
                        throw th9;
                    }
                }
            } catch (Throwable th10) {
                try {
                    throw th10;
                } catch (Throwable th11) {
                    CloseableKt.closeFinally(cursorRawQuery, th10);
                    throw th11;
                }
            }
        }
        return applyResult;
    }

    public final ApplyResult applyReceivedInvoice(String operationId) {
        String str;
        String string;
        InvoiceSaveResult invoiceSaveResultSaveSalesDraft;
        SQLiteDatabase sQLiteDatabase;
        ApplyResult applyResult;
        long jUsdCentsToSyp;
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        synchronized (APPLY_LOCK) {
            if (StringsKt.isBlank(operationId)) {
                throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0623\u0645\u0631 \u0645\u0637\u0644\u0648\u0628".toString());
            }
            SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
            Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
            SyncDeviceIdentity.Companion companion = SyncDeviceIdentity.INSTANCE;
            Context appContext = this.appContext;
            Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
            String orCreate = companion.getOrCreate(appContext);
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='authority_device_id'", (String[]) null);
            try {
                Cursor cursor = cursorRawQuery;
                String string2 = cursor.moveToFirst() ? cursor.getString(0) : null;
                CloseableKt.closeFinally(cursorRawQuery, null);
                Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", (String[]) null);
                try {
                    Cursor cursor2 = cursorRawQuery2;
                    String string3 = cursor2.moveToFirst() ? cursor2.getString(0) : null;
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    if (!Intrinsics.areEqual(string3, "PRIMARY") || !Intrinsics.areEqual(string2, orCreate)) {
                        throw new IllegalArgumentException("\u062a\u0637\u0628\u064a\u0642 \u0627\u0644\u0623\u0648\u0627\u0645\u0631 \u0627\u0644\u0648\u0627\u0631\u062f\u0629 \u0645\u0633\u0645\u0648\u062d \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0627\u0644\u0645\u0639\u062a\u0645\u062f \u0641\u0642\u0637".toString());
                    }
                    Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT i.origin_device_id,i.entity_type,i.entity_id,i.payload_json,i.status,\n                      m.primary_user_id,i.origin_created_at\n               FROM sync_inbox i\n               JOIN sync_actor_mappings m\n                 ON m.device_id=i.origin_device_id\n                AND m.remote_actor_user_id=i.actor_user_id AND m.is_active=1\n               JOIN users u ON u.id=m.primary_user_id\n               JOIN sync_devices d ON d.device_id=i.origin_device_id\n               WHERE i.operation_id=? AND d.device_role='SECONDARY'\n                 AND d.status='ACTIVE' AND u.is_active=1 AND u.must_change_password=0\n               LIMIT 1", new String[]{operationId});
                    try {
                        Cursor cursor3 = cursorRawQuery3;
                        if (!cursor3.moveToFirst()) {
                            throw new IllegalArgumentException("\u0627\u0644\u0623\u0645\u0631 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0623\u0648 \u0627\u0644\u062c\u0647\u0627\u0632/\u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0645\u0641\u0639\u0644 \u0623\u0648 \u0644\u0645 \u064a\u062a\u0645 \u0631\u0628\u0637 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645".toString());
                        }
                        String string4 = cursor3.getString(0);
                        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                        String string5 = cursor3.getString(1);
                        Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                        String string6 = cursor3.getString(2);
                        Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                        String string7 = cursor3.getString(3);
                        Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
                        String string8 = cursor3.getString(4);
                        Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
                        String string9 = cursor3.getString(5);
                        Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
                        String string10 = cursor3.getString(6);
                        Intrinsics.checkNotNullExpressionValue(string10, "getString(...)");
                        InboxCommand inboxCommand = new InboxCommand(string4, string5, string6, string7, string8, string9, string10);
                        CloseableKt.closeFinally(cursorRawQuery3, null);
                        if (Intrinsics.areEqual(inboxCommand.getStatus(), "APPLIED")) {
                            applyResult = new ApplyResult(operationId, true, null, 4, null);
                        } else {
                            if (!Intrinsics.areEqual(inboxCommand.getStatus(), "RECEIVED")) {
                                throw new IllegalArgumentException(("\u062d\u0627\u0644\u0629 \u0627\u0644\u0623\u0645\u0631 \u0644\u0627 \u062a\u0633\u0645\u062d \u0628\u0627\u0644\u062a\u0637\u0628\u064a\u0642: " + inboxCommand.getStatus()).toString());
                            }
                            JSONObject jSONObject = new JSONObject(inboxCommand.getPayloadJson());
                            String strOptString = jSONObject.optString("business_operation");
                            if (Intrinsics.areEqual(strOptString, "POST_PURCHASE_INVOICE")) {
                                str = PermissionPolicy.PURCHASES_MANAGE;
                            } else {
                                if (!Intrinsics.areEqual(strOptString, "POST_SALE_INVOICE")) {
                                    throw new IllegalArgumentException("\u0646\u0648\u0639 \u0627\u0644\u0623\u0645\u0631 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645 \u0641\u064a \u0645\u0637\u0628\u0651\u0642 \u0627\u0644\u0641\u0648\u0627\u062a\u064a\u0631: " + strOptString);
                                }
                                str = PermissionPolicy.SALES_CHECKOUT;
                            }
                            PermissionPolicy.INSTANCE.require(writableDatabase, inboxCommand.getPrimaryUserId(), str);
                            String string11 = jSONObject.getString("id");
                            if (!Intrinsics.areEqual(string11, inboxCommand.getEntityId())) {
                                throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0643\u064a\u0627\u0646 \u0641\u064a \u0627\u0644\u0638\u0631\u0641".toString());
                            }
                            String str2 = Intrinsics.areEqual(strOptString, "POST_PURCHASE_INVOICE") ? "PURCHASE" : "SALE";
                            String strOptString2 = jSONObject.optString("sale_type", "CASH");
                            if (Intrinsics.areEqual(str2, "SALE") && !Intrinsics.areEqual(strOptString2, "CREDIT") && !Intrinsics.areEqual(strOptString2, "CASH")) {
                                throw new IllegalArgumentException("\u0646\u0648\u0639 \u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u0648\u0627\u0631\u062f \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645".toString());
                            }
                            JSONArray jSONArray = jSONObject.getJSONArray("items");
                            int length = jSONArray.length();
                            if (1 > length || length >= 501) {
                                throw new IllegalArgumentException("\u0639\u062f\u062f \u0623\u0635\u0646\u0627\u0641 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                            }
                            Intrinsics.checkNotNull(jSONArray);
                            List<InvoiceLineInput> lines = parseLines(jSONArray);
                            String string12 = jSONObject.isNull("party_id") ? null : jSONObject.getString("party_id");
                            String string13 = jSONObject.getString("invoice_date");
                            long j = jSONObject.getLong("discount_usd_cents");
                            String string14 = jSONObject.isNull("notes") ? null : jSONObject.getString("notes");
                            long j2 = jSONObject.getLong("exchange_rate_syp_per_usd_milli");
                            if (j2 <= 0) {
                                throw new IllegalArgumentException("\u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0648\u0627\u0631\u062f\u0629 \u0644\u0627 \u062a\u062d\u062a\u0648\u064a \u0633\u0639\u0631 \u0635\u0631\u0641 \u0635\u0627\u0644\u062d\u0627\u064b".toString());
                            }
                            String strOptString3 = jSONObject.optString("posted_at");
                            Intrinsics.checkNotNull(strOptString3);
                            if (StringsKt.isBlank(strOptString3) || Intrinsics.areEqual(strOptString3, "null")) {
                                strOptString3 = null;
                            }
                            if (strOptString3 == null) {
                                strOptString3 = inboxCommand.getOriginCreatedAt();
                            }
                            if (!new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(strOptString3)) {
                                throw new IllegalArgumentException("\u0627\u0644\u0623\u0645\u0631 \u0627\u0644\u0648\u0627\u0631\u062f \u0644\u0627 \u064a\u062d\u062a\u0648\u064a \u0648\u0642\u062a \u0648\u0642\u0648\u0639 \u0623\u0635\u0644\u064a\u0627\u064b \u0635\u0627\u0644\u062d\u0627\u064b".toString());
                            }
                            String strOptString4 = jSONObject.optString("posted_operating_date");
                            Intrinsics.checkNotNull(strOptString4);
                            String str3 = strOptString4;
                            if (!new Regex("\\d{4}-\\d{2}-\\d{2}").matches(strOptString4)) {
                                str3 = null;
                            }
                            String strOperatingDateForTimestamp = str3 == null ? operatingDateForTimestamp(strOptString3) : str3;
                            if (Intrinsics.areEqual(str2, "SALE") && Intrinsics.areEqual(strOptString2, "CASH")) {
                                String strOptString5 = jSONObject.optString("cash_account_id");
                                Intrinsics.checkNotNull(strOptString5);
                                if (StringsKt.isBlank(strOptString5) || Intrinsics.areEqual(strOptString5, "null")) {
                                    strOptString5 = null;
                                }
                                if (strOptString5 == null) {
                                    throw new IllegalArgumentException("\u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u0646\u0642\u062f\u064a \u0627\u0644\u0648\u0627\u0631\u062f \u0644\u0627 \u064a\u062d\u062f\u062f \u062d\u0633\u0627\u0628 \u0627\u0644\u0642\u0628\u0636 \u0627\u0644\u0623\u0635\u0644\u064a");
                                }
                                String strOptString6 = jSONObject.optString("cash_account_type");
                                String strOptString7 = jSONObject.optString("cash_account_currency_code");
                                if (!SetsKt.setOf((Object[]) new String[]{"CASH_BOX", "WALLET"}).contains(strOptString6) || !SetsKt.setOf((Object[]) new String[]{"USD", "SYP"}).contains(strOptString7)) {
                                    throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u062d\u0633\u0627\u0628 \u0627\u0644\u0642\u0628\u0636 \u0627\u0644\u0623\u0635\u0644\u064a \u063a\u064a\u0631 \u0645\u0643\u062a\u0645\u0644\u0629 \u0623\u0648 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u0629".toString());
                                }
                                Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT m.primary_cash_account_id,a.account_type,a.currency_code\n                   FROM sync_cash_account_mappings m\n                   JOIN cash_accounts a ON a.id=m.primary_cash_account_id\n                   WHERE m.device_id=? AND m.remote_cash_account_id=? AND m.is_active=1\n                     AND a.is_active=1", new String[]{inboxCommand.getOriginDeviceId(), strOptString5});
                                try {
                                    Cursor cursor4 = cursorRawQuery4;
                                    if (!cursor4.moveToFirst()) {
                                        throw new IllegalArgumentException("\u0644\u0645 \u064a\u062a\u0645 \u0631\u0628\u0637 \u062d\u0633\u0627\u0628 \u0642\u0628\u0636 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u0628\u062d\u0633\u0627\u0628 \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a".toString());
                                    }
                                    if (!Intrinsics.areEqual(cursor4.getString(1), strOptString6) || !Intrinsics.areEqual(cursor4.getString(2), strOptString7)) {
                                        throw new IllegalArgumentException("\u0646\u0648\u0639 \u0623\u0648 \u0639\u0645\u0644\u0629 \u062d\u0633\u0627\u0628 \u0627\u0644\u0642\u0628\u0636 \u0627\u0644\u0645\u0631\u062a\u0628\u0637 \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0623\u0635\u0644\u064a".toString());
                                    }
                                    string = cursor4.getString(0);
                                    CloseableKt.closeFinally(cursorRawQuery4, null);
                                    JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("cash_receipts");
                                    if (jSONArrayOptJSONArray == null) {
                                        throw new IllegalArgumentException("\u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u0646\u0642\u062f\u064a \u0627\u0644\u0648\u0627\u0631\u062f \u0644\u0627 \u064a\u062d\u062a\u0648\u064a \u062a\u0641\u0627\u0635\u064a\u0644 \u0627\u0644\u0642\u0628\u0636");
                                    }
                                    if (jSONArrayOptJSONArray.length() != 1) {
                                        throw new IllegalArgumentException("\u064a\u062c\u0628 \u0623\u0646 \u064a\u062d\u062a\u0648\u064a \u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u0646\u0642\u062f\u064a \u0639\u0644\u0649 \u0642\u064a\u062f \u0642\u0628\u0636 \u0648\u0627\u062d\u062f \u0645\u0637\u0627\u0628\u0642".toString());
                                    }
                                    JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(0);
                                    String strOptString8 = jSONObject2.optString("cash_transaction_id");
                                    Intrinsics.checkNotNullExpressionValue(strOptString8, "optString(...)");
                                    if (StringsKt.isBlank(strOptString8)) {
                                        throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0642\u064a\u062f \u0627\u0644\u0642\u0628\u0636 \u0627\u0644\u0623\u0635\u0644\u064a \u0645\u0641\u0642\u0648\u062f".toString());
                                    }
                                    if (!Intrinsics.areEqual(jSONObject2.optString("currency_code"), strOptString7)) {
                                        throw new IllegalArgumentException("\u0639\u0645\u0644\u0629 \u0642\u064a\u062f \u0627\u0644\u0642\u0628\u0636 \u0644\u0627 \u062a\u0637\u0627\u0628\u0642 \u0639\u0645\u0644\u0629 \u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0623\u0635\u0644\u064a".toString());
                                    }
                                    if (Intrinsics.areEqual(strOptString7, "USD")) {
                                        jUsdCentsToSyp = jSONObject.getLong("total_usd_cents");
                                    } else if (Intrinsics.areEqual(strOptString7, "SYP")) {
                                        jUsdCentsToSyp = CurrencyConverter.INSTANCE.usdCentsToSyp(jSONObject.getLong("total_usd_cents"), j2);
                                    } else {
                                        throw new IllegalStateException("\u0639\u0645\u0644\u0629 \u0642\u0628\u0636 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u0629".toString());
                                    }
                                    if (jSONObject2.getLong("amount_minor") != jUsdCentsToSyp) {
                                        throw new IllegalArgumentException("\u0642\u064a\u0645\u0629 \u0642\u064a\u062f \u0627\u0644\u0642\u0628\u0636 \u0627\u0644\u0623\u0635\u0644\u064a \u0644\u0627 \u062a\u0637\u0627\u0628\u0642 \u0625\u062c\u0645\u0627\u0644\u064a \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0648\u0633\u0639\u0631 \u0635\u0631\u0641\u0647\u0627".toString());
                                    }
                                    if ((jSONObject2.isNull("exchange_rate_syp_per_usd_milli") ? 0L : jSONObject2.getLong("exchange_rate_syp_per_usd_milli")) != j2) {
                                        throw new IllegalArgumentException("\u0633\u0639\u0631 \u0635\u0631\u0641 \u0642\u064a\u062f \u0627\u0644\u0642\u0628\u0636 \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u0633\u0639\u0631 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629".toString());
                                    }
                                } catch (Throwable th) {
                                    try {
                                        throw th;
                                    } catch (Throwable th2) {
                                        CloseableKt.closeFinally(cursorRawQuery4, th);
                                        throw th2;
                                    }
                                }
                            } else {
                                string = null;
                            }
                            Context appContext2 = this.appContext;
                            Intrinsics.checkNotNullExpressionValue(appContext2, "appContext");
                            InvoiceRepository invoiceRepository = new InvoiceRepository(appContext2);
                            String str4 = operationId + ":IMPORT_DRAFT";
                            String str5 = operationId + ":IMPORT_POST";
                            if (Intrinsics.areEqual(str2, "PURCHASE")) {
                                String primaryUserId = inboxCommand.getPrimaryUserId();
                                Intrinsics.checkNotNull(string13);
                                invoiceSaveResultSaveSalesDraft = invoiceRepository.savePurchaseDraft(primaryUserId, str4, string11, string12, string13, j, string14, lines);
                            } else {
                                String primaryUserId2 = inboxCommand.getPrimaryUserId();
                                Intrinsics.checkNotNull(string13);
                                Intrinsics.checkNotNull(strOptString2);
                                invoiceSaveResultSaveSalesDraft = invoiceRepository.saveSalesDraft(primaryUserId2, str4, string11, string12, string13, j, string14, lines, strOptString2);
                            }
                            if (!Intrinsics.areEqual(invoiceSaveResultSaveSalesDraft.getInvoiceId(), string11)) {
                                throw new IllegalArgumentException("\u0644\u0645 \u064a\u062a\u0645 \u062d\u0641\u0638 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0628\u0627\u0644\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0648\u0627\u0631\u062f".toString());
                            }
                            String str6 = Intrinsics.areEqual(str2, "PURCHASE") ? "purchase_invoices" : "sales_invoices";
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("exchange_rate_syp_per_usd_milli", Long.valueOf(j2));
                            Unit unit = Unit.INSTANCE;
                            if (writableDatabase.update(str6, contentValues, "id=? AND status='DRAFT'", new String[]{string11}) == 0) {
                                Cursor cursorRawQuery5 = writableDatabase.rawQuery("SELECT exchange_rate_syp_per_usd_milli FROM " + str6 + " WHERE id=?", new String[]{string11});
                                try {
                                    Cursor cursor5 = cursorRawQuery5;
                                    if (!cursor5.moveToFirst()) {
                                        throw new IllegalArgumentException("Failed requirement.".toString());
                                    }
                                    long j3 = cursor5.getLong(0);
                                    CloseableKt.closeFinally(cursorRawQuery5, null);
                                    if (j3 != j2) {
                                        throw new IllegalArgumentException("\u0633\u0639\u0631 \u0635\u0631\u0641 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0645\u062d\u0644\u064a\u0629 \u064a\u062e\u062a\u0644\u0641 \u0639\u0646 \u0627\u0644\u0633\u0639\u0631 \u0627\u0644\u0648\u0627\u0631\u062f".toString());
                                    }
                                } catch (Throwable th3) {
                                    try {
                                        throw th3;
                                    } catch (Throwable th4) {
                                        CloseableKt.closeFinally(cursorRawQuery5, th3);
                                        throw th4;
                                    }
                                }
                            }
                            if (Intrinsics.areEqual(str2, "PURCHASE")) {
                                sQLiteDatabase = writableDatabase;
                                invoiceRepository.postPurchase(inboxCommand.getPrimaryUserId(), string11, str5, strOptString3, strOperatingDateForTimestamp);
                            } else {
                                sQLiteDatabase = writableDatabase;
                                if (Intrinsics.areEqual(strOptString2, "CASH")) {
                                    String primaryUserId3 = inboxCommand.getPrimaryUserId();
                                    if (string != null) {
                                        invoiceRepository.postCashSale(primaryUserId3, string11, str5, string, strOptString3, strOperatingDateForTimestamp);
                                    } else {
                                        throw new IllegalArgumentException("Required value was null.".toString());
                                    }
                                } else {
                                    invoiceRepository.postSale(inboxCommand.getPrimaryUserId(), string11, str5, strOptString3, strOperatingDateForTimestamp);
                                }
                            }
                            sQLiteDatabase.beginTransaction();
                            try {
                                Cursor cursorRawQuery6 = sQLiteDatabase.rawQuery("SELECT status,payload_sha256 FROM sync_inbox WHERE operation_id=?", new String[]{operationId});
                                try {
                                    Cursor cursor6 = cursorRawQuery6;
                                    if (!cursor6.moveToFirst()) {
                                        throw new IllegalArgumentException("\u0627\u062e\u062a\u0641\u0649 \u0623\u0645\u0631 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u0623\u062b\u0646\u0627\u0621 \u0627\u0644\u062a\u0637\u0628\u064a\u0642".toString());
                                    }
                                    Pair pair = TuplesKt.to(cursor6.getString(0), cursor6.getString(1));
                                    CloseableKt.closeFinally(cursorRawQuery6, null);
                                    if (!Intrinsics.areEqual(pair.getFirst(), "RECEIVED")) {
                                        throw new IllegalArgumentException("\u062a\u063a\u064a\u0631\u062a \u062d\u0627\u0644\u0629 \u0627\u0644\u0623\u0645\u0631 \u0623\u062b\u0646\u0627\u0621 \u0627\u0644\u062a\u0637\u0628\u064a\u0642".toString());
                                    }
                                    ContentValues contentValues2 = new ContentValues();
                                    contentValues2.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                    contentValues2.put("applied_at", timestamp());
                                    contentValues2.putNull("rejection_reason");
                                    Unit unit2 = Unit.INSTANCE;
                                    if (sQLiteDatabase.update("sync_inbox", contentValues2, "operation_id=? AND status='RECEIVED'", new String[]{operationId}) != 1) {
                                        throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062b\u0628\u064a\u062a \u062d\u0627\u0644\u0629 \u062a\u0637\u0628\u064a\u0642 \u0627\u0644\u0623\u0645\u0631".toString());
                                    }
                                    sQLiteDatabase.setTransactionSuccessful();
                                    sQLiteDatabase.endTransaction();
                                    applyResult = new ApplyResult(operationId, true, null, 4, null);
                                } catch (Throwable th5) {
                                    try {
                                        throw th5;
                                    } catch (Throwable th6) {
                                        CloseableKt.closeFinally(cursorRawQuery6, th5);
                                        throw th6;
                                    }
                                }
                            } catch (Throwable th7) {
                                sQLiteDatabase.endTransaction();
                                throw th7;
                            }
                        }
                    } catch (Throwable th8) {
                        try {
                            throw th8;
                        } catch (Throwable th9) {
                            CloseableKt.closeFinally(cursorRawQuery3, th8);
                            throw th9;
                        }
                    }
                } catch (Throwable th10) {
                    try {
                        throw th10;
                    } catch (Throwable th11) {
                        CloseableKt.closeFinally(cursorRawQuery2, th10);
                        throw th11;
                    }
                }
            } catch (Throwable th12) {
                try {
                    throw th12;
                } catch (Throwable th13) {
                    CloseableKt.closeFinally(cursorRawQuery, th12);
                    throw th13;
                }
            }
        }
        return applyResult;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final ApplyResult applyReceivedOperatingExpense(String operationId) {
        String[] strArr;
        String strTake;
        String strTake2;
        String str;
        String str2;
        String str3;
        ApplyResult applyResult;
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        synchronized (APPLY_LOCK) {
            if (StringsKt.isBlank(operationId)) {
                throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0623\u0645\u0631 \u0645\u0637\u0644\u0648\u0628".toString());
            }
            SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
            Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
            SyncDeviceIdentity.Companion companion = SyncDeviceIdentity.INSTANCE;
            Context appContext = this.appContext;
            Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
            String orCreate = companion.getOrCreate(appContext);
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", (String[]) null);
            try {
                Cursor cursor = cursorRawQuery;
                String string = cursor.moveToFirst() ? cursor.getString(0) : null;
                CloseableKt.closeFinally(cursorRawQuery, null);
                Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='authority_device_id'", (String[]) null);
                try {
                    Cursor cursor2 = cursorRawQuery2;
                    String string2 = cursor2.moveToFirst() ? cursor2.getString(0) : null;
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    if (!Intrinsics.areEqual(string, "PRIMARY") || !Intrinsics.areEqual(string2, orCreate)) {
                        throw new IllegalArgumentException("\u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u0645\u0635\u0631\u0648\u0641\u0627\u062a \u0627\u0644\u0648\u0627\u0631\u062f\u0629 \u0645\u0633\u0645\u0648\u062d \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0627\u0644\u0645\u0639\u062a\u0645\u062f \u0641\u0642\u0637".toString());
                    }
                    writableDatabase.beginTransaction();
                    try {
                        Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT i.origin_device_id,i.entity_type,i.entity_id,i.payload_json,i.status,\n                          m.primary_user_id,i.payload_sha256,i.origin_created_at\n                   FROM sync_inbox i\n                   JOIN sync_actor_mappings m\n                     ON m.device_id=i.origin_device_id\n                    AND m.remote_actor_user_id=i.actor_user_id AND m.is_active=1\n                   JOIN users u ON u.id=m.primary_user_id\n                   JOIN sync_devices d ON d.device_id=i.origin_device_id\n                   WHERE i.operation_id=? AND d.device_role='SECONDARY'\n                     AND d.status='ACTIVE' AND u.is_active=1 AND u.must_change_password=0\n                   LIMIT 1", new String[]{operationId});
                        try {
                            Cursor cursor3 = cursorRawQuery3;
                            if (!cursor3.moveToFirst()) {
                                throw new IllegalArgumentException("\u0623\u0645\u0631 \u0627\u0644\u0645\u0635\u0631\u0648\u0641 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0623\u0648 \u0627\u0644\u062c\u0647\u0627\u0632/\u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0645\u0641\u0639\u0644 \u0623\u0648 \u063a\u064a\u0631 \u0645\u0631\u0628\u0648\u0637".toString());
                            }
                            String[] strArr2 = {cursor3.getString(0), cursor3.getString(1), cursor3.getString(2), cursor3.getString(3), cursor3.getString(4), cursor3.getString(5), cursor3.getString(6), cursor3.getString(7)};
                            CloseableKt.closeFinally(cursorRawQuery3, null);
                            if (Intrinsics.areEqual(strArr2[4], "APPLIED")) {
                                writableDatabase.setTransactionSuccessful();
                                applyResult = new ApplyResult(operationId, true, null, 4, null);
                            } else {
                                if (!Intrinsics.areEqual(strArr2[4], "RECEIVED")) {
                                    throw new IllegalArgumentException("\u062d\u0627\u0644\u0629 \u0623\u0645\u0631 \u0627\u0644\u0645\u0635\u0631\u0648\u0641 \u0644\u0627 \u062a\u0633\u0645\u062d \u0628\u0627\u0644\u062a\u0637\u0628\u064a\u0642".toString());
                                }
                                if (!Intrinsics.areEqual(strArr2[1], "OPERATING_EXPENSE")) {
                                    throw new IllegalArgumentException("\u0646\u0648\u0639 \u0643\u064a\u0627\u0646 \u0627\u0644\u0645\u0635\u0631\u0648\u0641 \u0627\u0644\u0648\u0627\u0631\u062f \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                }
                                JSONObject jSONObject = new JSONObject(strArr2[3]);
                                if (!Intrinsics.areEqual(jSONObject.optString("business_operation"), "POST_OPERATING_EXPENSE")) {
                                    throw new IllegalArgumentException("\u0646\u0648\u0639 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0627\u0644\u0648\u0627\u0631\u062f\u0629 \u0644\u064a\u0633 \u0645\u0635\u0631\u0648\u0641\u0627\u064b \u062a\u0634\u063a\u064a\u0644\u064a\u0627\u064b".toString());
                                }
                                String str4 = strArr2[5];
                                PermissionPolicy permissionPolicy = PermissionPolicy.INSTANCE;
                                Intrinsics.checkNotNull(str4);
                                permissionPolicy.require(writableDatabase, str4, PermissionPolicy.EXPENSES_MANAGE);
                                String string3 = jSONObject.getString("expense_id");
                                Intrinsics.checkNotNull(string3);
                                if (StringsKt.isBlank(string3) || !Intrinsics.areEqual(strArr2[2], string3)) {
                                    throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0645\u0635\u0631\u0648\u0641 \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u0643\u064a\u0627\u0646 \u0627\u0644\u0638\u0631\u0641".toString());
                                }
                                Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT operation_type,payload_hash,result_reference FROM operation_registry WHERE operation_id=?", new String[]{operationId});
                                try {
                                    Cursor cursor4 = cursorRawQuery4;
                                    if (cursor4.moveToFirst()) {
                                        strArr = new String[]{cursor4.getString(0), cursor4.getString(1), cursor4.isNull(2) ? "" : cursor4.getString(2)};
                                    } else {
                                        strArr = null;
                                    }
                                    CloseableKt.closeFinally(cursorRawQuery4, null);
                                    if (strArr != null) {
                                        if (Intrinsics.areEqual(strArr[0], "POST_OPERATING_EXPENSE") && Intrinsics.areEqual(strArr[1], strArr2[6])) {
                                            String str5 = strArr[2];
                                            Intrinsics.checkNotNullExpressionValue(str5, "get(...)");
                                            if (!StringsKt.isBlank(str5)) {
                                                ContentValues contentValues = new ContentValues();
                                                contentValues.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                                contentValues.put("applied_at", timestamp());
                                                contentValues.putNull("rejection_reason");
                                                Unit unit = Unit.INSTANCE;
                                                writableDatabase.update("sync_inbox", contentValues, "operation_id=? AND status='RECEIVED'", new String[]{operationId});
                                                writableDatabase.setTransactionSuccessful();
                                                applyResult = new ApplyResult(operationId, true, null, 4, null);
                                            }
                                        }
                                        throw new IllegalArgumentException("\u062a\u0639\u0627\u0631\u0636 \u0641\u064a \u0633\u062c\u0644 \u0627\u0644\u0645\u0635\u0631\u0648\u0641 \u0623\u0648 \u0625\u0639\u0627\u062f\u0629 \u0627\u0633\u062a\u062e\u062f\u0627\u0645 \u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0639\u0645\u0644\u064a\u0629".toString());
                                    }
                                    String string4 = jSONObject.getString("category_id");
                                    Cursor cursorRawQuery5 = writableDatabase.rawQuery("SELECT 1 FROM expense_categories WHERE id=? AND is_active=1", new String[]{string4});
                                    try {
                                        boolean zMoveToFirst = cursorRawQuery5.moveToFirst();
                                        CloseableKt.closeFinally(cursorRawQuery5, null);
                                        if (!zMoveToFirst) {
                                            throw new IllegalArgumentException("\u062a\u0635\u0646\u064a\u0641 \u0627\u0644\u0645\u0635\u0631\u0648\u0641 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0623\u0648 \u0645\u0648\u0642\u0648\u0641 \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a".toString());
                                        }
                                        String string5 = jSONObject.getString("cash_account_id");
                                        String string6 = jSONObject.getString("cash_account_type");
                                        String string7 = jSONObject.getString("cash_account_currency_code");
                                        String string8 = jSONObject.getString("currency_code");
                                        if (!SetsKt.setOf((Object[]) new String[]{"CASH_BOX", "WALLET"}).contains(string6) || !SetsKt.setOf((Object[]) new String[]{"USD", "SYP"}).contains(string7) || !Intrinsics.areEqual(string8, string7)) {
                                            throw new IllegalArgumentException("\u0646\u0648\u0639 \u0623\u0648 \u0639\u0645\u0644\u0629 \u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0635\u0631\u0648\u0641 \u0627\u0644\u0648\u0627\u0631\u062f \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u064a\u0646".toString());
                                        }
                                        Cursor cursorRawQuery6 = writableDatabase.rawQuery("SELECT m.primary_cash_account_id,a.account_type,a.currency_code,a.opening_balance_minor\n                   FROM sync_cash_account_mappings m JOIN cash_accounts a\n                     ON a.id=m.primary_cash_account_id\n                   WHERE m.device_id=? AND m.remote_cash_account_id=? AND m.is_active=1\n                     AND a.is_active=1", new String[]{strArr2[0], string5});
                                        try {
                                            Cursor cursor5 = cursorRawQuery6;
                                            if (!cursor5.moveToFirst()) {
                                                throw new IllegalArgumentException("\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0635\u0631\u0648\u0641 \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u063a\u064a\u0631 \u0645\u0631\u0628\u0648\u0637 \u0623\u0648 \u0645\u0648\u0642\u0648\u0641".toString());
                                            }
                                            if (!Intrinsics.areEqual(cursor5.getString(1), string6) || !Intrinsics.areEqual(cursor5.getString(2), string7)) {
                                                throw new IllegalArgumentException("\u0646\u0648\u0639 \u0623\u0648 \u0639\u0645\u0644\u0629 \u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0635\u0631\u0648\u0641 \u0627\u0644\u0645\u0631\u062a\u0628\u0637 \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0623\u0635\u0644\u064a".toString());
                                            }
                                            Pair pair = TuplesKt.to(cursor5.getString(0), Long.valueOf(cursor5.getLong(3)));
                                            CloseableKt.closeFinally(cursorRawQuery6, null);
                                            Cursor cursorRawQuery7 = writableDatabase.rawQuery("SELECT 1 FROM system_cash_account_roles WHERE role_key='SUPPLIER_ROLLOVER' AND account_id=?", new String[]{pair.getFirst()});
                                            try {
                                                boolean zMoveToFirst2 = cursorRawQuery7.moveToFirst();
                                                CloseableKt.closeFinally(cursorRawQuery7, null);
                                                if (zMoveToFirst2) {
                                                    throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u0633\u062c\u064a\u0644 \u0645\u0635\u0631\u0648\u0641 \u0645\u0646 \u0645\u062e\u0635\u0635 \u062f\u0641\u0639\u0627\u062a \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0627\u0644\u064a\u0648\u0645\u064a".toString());
                                                }
                                                long j = jSONObject.getLong("amount_minor");
                                                long jOptLong = jSONObject.optLong("exchange_rate_syp_per_usd_milli", 0L);
                                                long j2 = jSONObject.getLong("amount_usd_cents");
                                                if (j <= 0 || jOptLong < 0 || j2 < 0) {
                                                    throw new IllegalArgumentException("\u0645\u0628\u0644\u063a \u0623\u0648 \u0633\u0639\u0631 \u0635\u0631\u0641 \u0627\u0644\u0645\u0635\u0631\u0648\u0641 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                                }
                                                if (Intrinsics.areEqual(string8, "SYP") && jOptLong <= 0) {
                                                    throw new IllegalArgumentException("\u0645\u0635\u0631\u0648\u0641 \u0627\u0644\u0644\u064a\u0631\u0629 \u0644\u0627 \u064a\u062d\u062a\u0648\u064a \u0633\u0639\u0631 \u0635\u0631\u0641 \u0645\u062d\u0641\u0648\u0638\u0627\u064b \u0635\u0627\u0644\u062d\u0627\u064b".toString());
                                                }
                                                if ((Intrinsics.areEqual(string8, "USD") ? j : CurrencyConverter.INSTANCE.sypToUsdCents(j, jOptLong)) != j2) {
                                                    throw new IllegalArgumentException("\u0627\u0644\u0642\u064a\u0645\u0629 \u0627\u0644\u062f\u0648\u0644\u0627\u0631\u064a\u0629 \u0644\u0644\u0645\u0635\u0631\u0648\u0641 \u0644\u0627 \u062a\u0637\u0627\u0628\u0642 \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0627\u0644\u0645\u0633\u062c\u0644".toString());
                                                }
                                                String string9 = jSONObject.getString("expense_date");
                                                String string10 = jSONObject.getString("operating_date");
                                                String strOptString = jSONObject.optString("occurred_at");
                                                if (StringsKt.isBlank(strOptString)) {
                                                    strOptString = strArr2[7];
                                                }
                                                String str6 = strOptString;
                                                Intrinsics.checkNotNull(string9);
                                                if (new Regex("\\d{4}-\\d{2}-\\d{2}").matches(string9)) {
                                                    Intrinsics.checkNotNull(string10);
                                                    if (new Regex("\\d{4}-\\d{2}-\\d{2}").matches(string10)) {
                                                        Intrinsics.checkNotNull(str6);
                                                        if (new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(str6)) {
                                                            if (jSONObject.isNull("reference")) {
                                                                strTake = null;
                                                            } else {
                                                                String string11 = jSONObject.getString("reference");
                                                                Intrinsics.checkNotNullExpressionValue(string11, "getString(...)");
                                                                strTake = StringsKt.take(string11, 200);
                                                            }
                                                            if (jSONObject.isNull("notes")) {
                                                                strTake2 = null;
                                                            } else {
                                                                String string12 = jSONObject.getString("notes");
                                                                Intrinsics.checkNotNullExpressionValue(string12, "getString(...)");
                                                                strTake2 = StringsKt.take(string12, 1000);
                                                            }
                                                            long jLongValue = ((Number) pair.getSecond()).longValue();
                                                            Cursor cursorRawQuery8 = writableDatabase.rawQuery("SELECT COALESCE(SUM(CASE WHEN direction='IN' THEN amount_minor\n                           WHEN direction='OUT' THEN -amount_minor ELSE 0 END),0)\n                   FROM cash_transactions WHERE account_id=?", new String[]{pair.getFirst()});
                                                            try {
                                                                Cursor cursor6 = cursorRawQuery8;
                                                                cursor6.moveToFirst();
                                                                long j3 = cursor6.getLong(0);
                                                                CloseableKt.closeFinally(cursorRawQuery8, null);
                                                                if (MainActivity$$ExternalSyntheticBackport0.m(jLongValue, j3) < j) {
                                                                    throw new IllegalArgumentException("\u0631\u0635\u064a\u062f \u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u063a\u064a\u0631 \u0643\u0627\u0641\u064d \u0644\u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u0645\u0635\u0631\u0648\u0641 \u0627\u0644\u0648\u0627\u0631\u062f".toString());
                                                                }
                                                                String str7 = "sync-expense-" + operationId;
                                                                String str8 = "sync-cash-" + operationId;
                                                                if (strTake == null) {
                                                                    str = strTake2 == null ? string4 : strTake2;
                                                                } else {
                                                                    str = strTake;
                                                                }
                                                                String str9 = "\u0645\u0635\u0631\u0648\u0641 \u062a\u0634\u063a\u064a\u0644\u064a: " + str;
                                                                ContentValues contentValues2 = new ContentValues();
                                                                contentValues2.put("id", str8);
                                                                contentValues2.put("account_id", (String) pair.getFirst());
                                                                contentValues2.put("direction", "OUT");
                                                                contentValues2.put("amount_minor", Long.valueOf(j));
                                                                contentValues2.put("currency_code", string8);
                                                                if (jOptLong > 0) {
                                                                    contentValues2.put("exchange_rate_syp_per_usd_milli", Long.valueOf(jOptLong));
                                                                } else {
                                                                    contentValues2.putNull("exchange_rate_syp_per_usd_milli");
                                                                }
                                                                contentValues2.put("transaction_type", "OPERATING_EXPENSE");
                                                                contentValues2.put("source_type", "OPERATING_EXPENSE");
                                                                contentValues2.put("source_id", str7);
                                                                contentValues2.put("operation_id", operationId + ":CASH");
                                                                contentValues2.put("description", str9);
                                                                contentValues2.put("created_by", str4);
                                                                contentValues2.put("created_at", str6);
                                                                contentValues2.put("operating_date", string10);
                                                                Unit unit2 = Unit.INSTANCE;
                                                                writableDatabase.insertOrThrow("cash_transactions", null, contentValues2);
                                                                ContentValues contentValues3 = new ContentValues();
                                                                contentValues3.put("id", str7);
                                                                contentValues3.put("category_id", string4);
                                                                contentValues3.put("cash_account_id", (String) pair.getFirst());
                                                                contentValues3.put("cash_transaction_id", str8);
                                                                contentValues3.put("amount_minor", Long.valueOf(j));
                                                                contentValues3.put("currency_code", string8);
                                                                if (jOptLong > 0) {
                                                                    contentValues3.put("exchange_rate_syp_per_usd_milli", Long.valueOf(jOptLong));
                                                                } else {
                                                                    contentValues3.putNull("exchange_rate_syp_per_usd_milli");
                                                                }
                                                                contentValues3.put("amount_usd_cents", Long.valueOf(j2));
                                                                contentValues3.put("expense_date", string9);
                                                                if (strTake == 0) {
                                                                    contentValues3.putNull("reference");
                                                                    str2 = strTake;
                                                                } else {
                                                                    str2 = strTake;
                                                                    contentValues3.put("reference", str2);
                                                                }
                                                                if (strTake2 == 0) {
                                                                    contentValues3.putNull("notes");
                                                                    str3 = strTake2;
                                                                } else {
                                                                    str3 = strTake2;
                                                                    contentValues3.put("notes", str3);
                                                                }
                                                                contentValues3.put("created_by", str4);
                                                                contentValues3.put("operation_id", operationId);
                                                                contentValues3.put("created_at", str6);
                                                                Unit unit3 = Unit.INSTANCE;
                                                                writableDatabase.insertOrThrow("operating_expenses", null, contentValues3);
                                                                ContentValues contentValues4 = new ContentValues();
                                                                contentValues4.put("operation_id", operationId);
                                                                contentValues4.put("operation_type", "POST_OPERATING_EXPENSE");
                                                                contentValues4.put("payload_hash", strArr2[6]);
                                                                contentValues4.put(NotificationCompat.CATEGORY_STATUS, "COMPLETED");
                                                                contentValues4.put("result_reference", str7);
                                                                contentValues4.put("created_at", timestamp());
                                                                contentValues4.put("completed_at", timestamp());
                                                                Unit unit4 = Unit.INSTANCE;
                                                                writableDatabase.insertOrThrow("operation_registry", null, contentValues4);
                                                                ContentValues contentValues5 = new ContentValues();
                                                                contentValues5.put("id", UUID.randomUUID().toString());
                                                                contentValues5.put("user_id", str4);
                                                                contentValues5.put("action", "OPERATING_EXPENSE_POSTED");
                                                                contentValues5.put("entity_type", "OPERATING_EXPENSE");
                                                                contentValues5.put("entity_id", str7);
                                                                contentValues5.put("after_json", new JSONObject().put("category_id", string4).put("cash_account_id", pair.getFirst()).put("amount_minor", j).put("currency_code", string8).put("amount_usd_cents", j2).put("expense_date", string9).put("reference", str2).put("notes", str3).toString());
                                                                contentValues5.put("created_at", timestamp());
                                                                Unit unit5 = Unit.INSTANCE;
                                                                writableDatabase.insertOrThrow("audit_log", null, contentValues5);
                                                                ContentValues contentValues6 = new ContentValues();
                                                                contentValues6.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                                                contentValues6.put("applied_at", timestamp());
                                                                contentValues6.putNull("rejection_reason");
                                                                Unit unit6 = Unit.INSTANCE;
                                                                if (writableDatabase.update("sync_inbox", contentValues6, "operation_id=? AND status='RECEIVED'", new String[]{operationId}) != 1) {
                                                                    throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062b\u0628\u064a\u062a \u062d\u0627\u0644\u0629 \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u0645\u0635\u0631\u0648\u0641 \u0627\u0644\u0648\u0627\u0631\u062f".toString());
                                                                }
                                                                writableDatabase.setTransactionSuccessful();
                                                                applyResult = new ApplyResult(operationId, true, null, 4, null);
                                                            } catch (Throwable th) {
                                                                try {
                                                                    throw th;
                                                                } catch (Throwable th2) {
                                                                    CloseableKt.closeFinally(cursorRawQuery8, th);
                                                                    throw th2;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                                throw new IllegalArgumentException("\u062a\u0627\u0631\u064a\u062e \u0623\u0648 \u0648\u0642\u062a \u0627\u0644\u0645\u0635\u0631\u0648\u0641 \u0627\u0644\u0623\u0635\u0644\u064a \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                            } catch (Throwable th3) {
                                                try {
                                                    throw th3;
                                                } catch (Throwable th4) {
                                                    CloseableKt.closeFinally(cursorRawQuery7, th3);
                                                    throw th4;
                                                }
                                            }
                                        } catch (Throwable th5) {
                                            try {
                                                throw th5;
                                            } catch (Throwable th6) {
                                                CloseableKt.closeFinally(cursorRawQuery6, th5);
                                                throw th6;
                                            }
                                        }
                                    } catch (Throwable th7) {
                                        try {
                                            throw th7;
                                        } catch (Throwable th8) {
                                            CloseableKt.closeFinally(cursorRawQuery5, th7);
                                            throw th8;
                                        }
                                    }
                                } catch (Throwable th9) {
                                    try {
                                        throw th9;
                                    } catch (Throwable th10) {
                                        CloseableKt.closeFinally(cursorRawQuery4, th9);
                                        throw th10;
                                    }
                                }
                            }
                            writableDatabase.endTransaction();
                        } catch (Throwable th11) {
                            try {
                                throw th11;
                            } catch (Throwable th12) {
                                CloseableKt.closeFinally(cursorRawQuery3, th11);
                                throw th12;
                            }
                        }
                    } catch (Throwable th13) {
                        writableDatabase.endTransaction();
                        throw th13;
                    }
                } catch (Throwable th14) {
                    try {
                        throw th14;
                    } catch (Throwable th15) {
                        CloseableKt.closeFinally(cursorRawQuery2, th14);
                        throw th15;
                    }
                }
            } catch (Throwable th16) {
                try {
                    throw th16;
                } catch (Throwable th17) {
                    CloseableKt.closeFinally(cursorRawQuery, th16);
                    throw th17;
                }
            }
        }
        return applyResult;
    }

    /* JADX WARN: Code duplicated, block: B:197:0x052f A[Catch: all -> 0x07fa, TryCatch #16 {all -> 0x07fa, blocks: (B:23:0x0074, B:27:0x00bd, B:29:0x00ca, B:32:0x00de, B:34:0x00e8, B:36:0x00f2, B:38:0x0107, B:40:0x012c, B:42:0x0138, B:44:0x0141, B:54:0x0173, B:56:0x0178, B:58:0x0183, B:60:0x018e, B:62:0x019e, B:63:0x01d6, B:64:0x01e1, B:65:0x01e2, B:74:0x0227, B:76:0x0235, B:79:0x0240, B:80:0x024c, B:81:0x024d, B:83:0x025d, B:85:0x026f, B:87:0x029b, B:89:0x02ad, B:91:0x02b3, B:100:0x02e7, B:106:0x030c, B:108:0x0314, B:114:0x0328, B:116:0x0336, B:117:0x033a, B:119:0x035d, B:121:0x0370, B:125:0x038b, B:139:0x0401, B:142:0x041c, B:146:0x0430, B:151:0x044b, B:154:0x0466, B:156:0x0471, B:158:0x047b, B:160:0x0485, B:167:0x04aa, B:169:0x04af, B:172:0x04c9, B:195:0x0509, B:197:0x052f, B:199:0x0546, B:201:0x0574, B:203:0x0583, B:205:0x05e8, B:207:0x05f7, B:211:0x061a, B:213:0x063e, B:215:0x06a8, B:219:0x06bc, B:220:0x06c7, B:221:0x06c8, B:222:0x06d3, B:206:0x05f2, B:202:0x057e, B:175:0x04d6, B:176:0x04e1, B:182:0x04e7, B:183:0x04ea, B:184:0x04eb, B:185:0x04f7, B:191:0x04fd, B:192:0x0500, B:223:0x06d4, B:224:0x06df, B:230:0x06e5, B:231:0x06e8, B:239:0x06fa, B:240:0x06fd, B:241:0x06fe, B:242:0x070a, B:248:0x0710, B:249:0x0713, B:255:0x0719, B:256:0x071c, B:124:0x037a, B:257:0x071d, B:258:0x0728, B:259:0x0729, B:260:0x0734, B:109:0x031b, B:261:0x0735, B:262:0x073c, B:263:0x073d, B:264:0x0749, B:274:0x0768, B:275:0x076b, B:276:0x076c, B:277:0x0778, B:278:0x0779, B:279:0x0785, B:280:0x0786, B:281:0x0792, B:289:0x07a5, B:290:0x07a8, B:296:0x07ae, B:297:0x07b1, B:298:0x07b2, B:299:0x07be, B:300:0x07bf, B:301:0x07cb, B:302:0x07cc, B:303:0x07d8, B:304:0x07d9, B:305:0x07e4, B:313:0x07f6, B:314:0x07f9, B:152:0x045a, B:66:0x01ee, B:68:0x01f7, B:72:0x0212, B:71:0x020e, B:282:0x0793, B:283:0x079f, B:170:0x04bb, B:286:0x07a2, B:227:0x06e2, B:92:0x02c2, B:94:0x02cb, B:96:0x02d6, B:98:0x02e1, B:265:0x074a, B:266:0x0756, B:267:0x0757, B:268:0x0762, B:179:0x04e4, B:271:0x0765, B:45:0x014d, B:47:0x0156, B:51:0x016d, B:50:0x0169, B:293:0x07ab, B:24:0x0080, B:26:0x0089, B:306:0x07e5, B:307:0x07f0, B:310:0x07f3, B:147:0x043c, B:149:0x0445, B:232:0x06e9, B:233:0x06f4, B:236:0x06f7, B:161:0x0495, B:163:0x049e, B:188:0x04fa, B:140:0x0410, B:245:0x070d, B:126:0x03ab, B:127:0x03b2, B:129:0x03b8, B:131:0x03d2, B:134:0x03e1, B:132:0x03d9, B:135:0x03ee, B:136:0x03f5, B:137:0x03f6, B:252:0x0716), top: B:368:0x0074, outer: #9, inners: #0, #1, #2, #3, #4, #5, #6, #7, #8, #10, #11, #12, #13, #14, #15, #17, #18, #20, #22, #24 }] */
    /* JADX WARN: Code duplicated, block: B:198:0x0544  */
    /* JADX WARN: Code duplicated, block: B:201:0x0574 A[Catch: all -> 0x07fa, TryCatch #16 {all -> 0x07fa, blocks: (B:23:0x0074, B:27:0x00bd, B:29:0x00ca, B:32:0x00de, B:34:0x00e8, B:36:0x00f2, B:38:0x0107, B:40:0x012c, B:42:0x0138, B:44:0x0141, B:54:0x0173, B:56:0x0178, B:58:0x0183, B:60:0x018e, B:62:0x019e, B:63:0x01d6, B:64:0x01e1, B:65:0x01e2, B:74:0x0227, B:76:0x0235, B:79:0x0240, B:80:0x024c, B:81:0x024d, B:83:0x025d, B:85:0x026f, B:87:0x029b, B:89:0x02ad, B:91:0x02b3, B:100:0x02e7, B:106:0x030c, B:108:0x0314, B:114:0x0328, B:116:0x0336, B:117:0x033a, B:119:0x035d, B:121:0x0370, B:125:0x038b, B:139:0x0401, B:142:0x041c, B:146:0x0430, B:151:0x044b, B:154:0x0466, B:156:0x0471, B:158:0x047b, B:160:0x0485, B:167:0x04aa, B:169:0x04af, B:172:0x04c9, B:195:0x0509, B:197:0x052f, B:199:0x0546, B:201:0x0574, B:203:0x0583, B:205:0x05e8, B:207:0x05f7, B:211:0x061a, B:213:0x063e, B:215:0x06a8, B:219:0x06bc, B:220:0x06c7, B:221:0x06c8, B:222:0x06d3, B:206:0x05f2, B:202:0x057e, B:175:0x04d6, B:176:0x04e1, B:182:0x04e7, B:183:0x04ea, B:184:0x04eb, B:185:0x04f7, B:191:0x04fd, B:192:0x0500, B:223:0x06d4, B:224:0x06df, B:230:0x06e5, B:231:0x06e8, B:239:0x06fa, B:240:0x06fd, B:241:0x06fe, B:242:0x070a, B:248:0x0710, B:249:0x0713, B:255:0x0719, B:256:0x071c, B:124:0x037a, B:257:0x071d, B:258:0x0728, B:259:0x0729, B:260:0x0734, B:109:0x031b, B:261:0x0735, B:262:0x073c, B:263:0x073d, B:264:0x0749, B:274:0x0768, B:275:0x076b, B:276:0x076c, B:277:0x0778, B:278:0x0779, B:279:0x0785, B:280:0x0786, B:281:0x0792, B:289:0x07a5, B:290:0x07a8, B:296:0x07ae, B:297:0x07b1, B:298:0x07b2, B:299:0x07be, B:300:0x07bf, B:301:0x07cb, B:302:0x07cc, B:303:0x07d8, B:304:0x07d9, B:305:0x07e4, B:313:0x07f6, B:314:0x07f9, B:152:0x045a, B:66:0x01ee, B:68:0x01f7, B:72:0x0212, B:71:0x020e, B:282:0x0793, B:283:0x079f, B:170:0x04bb, B:286:0x07a2, B:227:0x06e2, B:92:0x02c2, B:94:0x02cb, B:96:0x02d6, B:98:0x02e1, B:265:0x074a, B:266:0x0756, B:267:0x0757, B:268:0x0762, B:179:0x04e4, B:271:0x0765, B:45:0x014d, B:47:0x0156, B:51:0x016d, B:50:0x0169, B:293:0x07ab, B:24:0x0080, B:26:0x0089, B:306:0x07e5, B:307:0x07f0, B:310:0x07f3, B:147:0x043c, B:149:0x0445, B:232:0x06e9, B:233:0x06f4, B:236:0x06f7, B:161:0x0495, B:163:0x049e, B:188:0x04fa, B:140:0x0410, B:245:0x070d, B:126:0x03ab, B:127:0x03b2, B:129:0x03b8, B:131:0x03d2, B:134:0x03e1, B:132:0x03d9, B:135:0x03ee, B:136:0x03f5, B:137:0x03f6, B:252:0x0716), top: B:368:0x0074, outer: #9, inners: #0, #1, #2, #3, #4, #5, #6, #7, #8, #10, #11, #12, #13, #14, #15, #17, #18, #20, #22, #24 }] */
    /* JADX WARN: Code duplicated, block: B:202:0x057e A[Catch: all -> 0x07fa, TryCatch #16 {all -> 0x07fa, blocks: (B:23:0x0074, B:27:0x00bd, B:29:0x00ca, B:32:0x00de, B:34:0x00e8, B:36:0x00f2, B:38:0x0107, B:40:0x012c, B:42:0x0138, B:44:0x0141, B:54:0x0173, B:56:0x0178, B:58:0x0183, B:60:0x018e, B:62:0x019e, B:63:0x01d6, B:64:0x01e1, B:65:0x01e2, B:74:0x0227, B:76:0x0235, B:79:0x0240, B:80:0x024c, B:81:0x024d, B:83:0x025d, B:85:0x026f, B:87:0x029b, B:89:0x02ad, B:91:0x02b3, B:100:0x02e7, B:106:0x030c, B:108:0x0314, B:114:0x0328, B:116:0x0336, B:117:0x033a, B:119:0x035d, B:121:0x0370, B:125:0x038b, B:139:0x0401, B:142:0x041c, B:146:0x0430, B:151:0x044b, B:154:0x0466, B:156:0x0471, B:158:0x047b, B:160:0x0485, B:167:0x04aa, B:169:0x04af, B:172:0x04c9, B:195:0x0509, B:197:0x052f, B:199:0x0546, B:201:0x0574, B:203:0x0583, B:205:0x05e8, B:207:0x05f7, B:211:0x061a, B:213:0x063e, B:215:0x06a8, B:219:0x06bc, B:220:0x06c7, B:221:0x06c8, B:222:0x06d3, B:206:0x05f2, B:202:0x057e, B:175:0x04d6, B:176:0x04e1, B:182:0x04e7, B:183:0x04ea, B:184:0x04eb, B:185:0x04f7, B:191:0x04fd, B:192:0x0500, B:223:0x06d4, B:224:0x06df, B:230:0x06e5, B:231:0x06e8, B:239:0x06fa, B:240:0x06fd, B:241:0x06fe, B:242:0x070a, B:248:0x0710, B:249:0x0713, B:255:0x0719, B:256:0x071c, B:124:0x037a, B:257:0x071d, B:258:0x0728, B:259:0x0729, B:260:0x0734, B:109:0x031b, B:261:0x0735, B:262:0x073c, B:263:0x073d, B:264:0x0749, B:274:0x0768, B:275:0x076b, B:276:0x076c, B:277:0x0778, B:278:0x0779, B:279:0x0785, B:280:0x0786, B:281:0x0792, B:289:0x07a5, B:290:0x07a8, B:296:0x07ae, B:297:0x07b1, B:298:0x07b2, B:299:0x07be, B:300:0x07bf, B:301:0x07cb, B:302:0x07cc, B:303:0x07d8, B:304:0x07d9, B:305:0x07e4, B:313:0x07f6, B:314:0x07f9, B:152:0x045a, B:66:0x01ee, B:68:0x01f7, B:72:0x0212, B:71:0x020e, B:282:0x0793, B:283:0x079f, B:170:0x04bb, B:286:0x07a2, B:227:0x06e2, B:92:0x02c2, B:94:0x02cb, B:96:0x02d6, B:98:0x02e1, B:265:0x074a, B:266:0x0756, B:267:0x0757, B:268:0x0762, B:179:0x04e4, B:271:0x0765, B:45:0x014d, B:47:0x0156, B:51:0x016d, B:50:0x0169, B:293:0x07ab, B:24:0x0080, B:26:0x0089, B:306:0x07e5, B:307:0x07f0, B:310:0x07f3, B:147:0x043c, B:149:0x0445, B:232:0x06e9, B:233:0x06f4, B:236:0x06f7, B:161:0x0495, B:163:0x049e, B:188:0x04fa, B:140:0x0410, B:245:0x070d, B:126:0x03ab, B:127:0x03b2, B:129:0x03b8, B:131:0x03d2, B:134:0x03e1, B:132:0x03d9, B:135:0x03ee, B:136:0x03f5, B:137:0x03f6, B:252:0x0716), top: B:368:0x0074, outer: #9, inners: #0, #1, #2, #3, #4, #5, #6, #7, #8, #10, #11, #12, #13, #14, #15, #17, #18, #20, #22, #24 }] */
    /* JADX WARN: Code duplicated, block: B:205:0x05e8 A[Catch: all -> 0x07fa, TryCatch #16 {all -> 0x07fa, blocks: (B:23:0x0074, B:27:0x00bd, B:29:0x00ca, B:32:0x00de, B:34:0x00e8, B:36:0x00f2, B:38:0x0107, B:40:0x012c, B:42:0x0138, B:44:0x0141, B:54:0x0173, B:56:0x0178, B:58:0x0183, B:60:0x018e, B:62:0x019e, B:63:0x01d6, B:64:0x01e1, B:65:0x01e2, B:74:0x0227, B:76:0x0235, B:79:0x0240, B:80:0x024c, B:81:0x024d, B:83:0x025d, B:85:0x026f, B:87:0x029b, B:89:0x02ad, B:91:0x02b3, B:100:0x02e7, B:106:0x030c, B:108:0x0314, B:114:0x0328, B:116:0x0336, B:117:0x033a, B:119:0x035d, B:121:0x0370, B:125:0x038b, B:139:0x0401, B:142:0x041c, B:146:0x0430, B:151:0x044b, B:154:0x0466, B:156:0x0471, B:158:0x047b, B:160:0x0485, B:167:0x04aa, B:169:0x04af, B:172:0x04c9, B:195:0x0509, B:197:0x052f, B:199:0x0546, B:201:0x0574, B:203:0x0583, B:205:0x05e8, B:207:0x05f7, B:211:0x061a, B:213:0x063e, B:215:0x06a8, B:219:0x06bc, B:220:0x06c7, B:221:0x06c8, B:222:0x06d3, B:206:0x05f2, B:202:0x057e, B:175:0x04d6, B:176:0x04e1, B:182:0x04e7, B:183:0x04ea, B:184:0x04eb, B:185:0x04f7, B:191:0x04fd, B:192:0x0500, B:223:0x06d4, B:224:0x06df, B:230:0x06e5, B:231:0x06e8, B:239:0x06fa, B:240:0x06fd, B:241:0x06fe, B:242:0x070a, B:248:0x0710, B:249:0x0713, B:255:0x0719, B:256:0x071c, B:124:0x037a, B:257:0x071d, B:258:0x0728, B:259:0x0729, B:260:0x0734, B:109:0x031b, B:261:0x0735, B:262:0x073c, B:263:0x073d, B:264:0x0749, B:274:0x0768, B:275:0x076b, B:276:0x076c, B:277:0x0778, B:278:0x0779, B:279:0x0785, B:280:0x0786, B:281:0x0792, B:289:0x07a5, B:290:0x07a8, B:296:0x07ae, B:297:0x07b1, B:298:0x07b2, B:299:0x07be, B:300:0x07bf, B:301:0x07cb, B:302:0x07cc, B:303:0x07d8, B:304:0x07d9, B:305:0x07e4, B:313:0x07f6, B:314:0x07f9, B:152:0x045a, B:66:0x01ee, B:68:0x01f7, B:72:0x0212, B:71:0x020e, B:282:0x0793, B:283:0x079f, B:170:0x04bb, B:286:0x07a2, B:227:0x06e2, B:92:0x02c2, B:94:0x02cb, B:96:0x02d6, B:98:0x02e1, B:265:0x074a, B:266:0x0756, B:267:0x0757, B:268:0x0762, B:179:0x04e4, B:271:0x0765, B:45:0x014d, B:47:0x0156, B:51:0x016d, B:50:0x0169, B:293:0x07ab, B:24:0x0080, B:26:0x0089, B:306:0x07e5, B:307:0x07f0, B:310:0x07f3, B:147:0x043c, B:149:0x0445, B:232:0x06e9, B:233:0x06f4, B:236:0x06f7, B:161:0x0495, B:163:0x049e, B:188:0x04fa, B:140:0x0410, B:245:0x070d, B:126:0x03ab, B:127:0x03b2, B:129:0x03b8, B:131:0x03d2, B:134:0x03e1, B:132:0x03d9, B:135:0x03ee, B:136:0x03f5, B:137:0x03f6, B:252:0x0716), top: B:368:0x0074, outer: #9, inners: #0, #1, #2, #3, #4, #5, #6, #7, #8, #10, #11, #12, #13, #14, #15, #17, #18, #20, #22, #24 }] */
    /* JADX WARN: Code duplicated, block: B:206:0x05f2 A[Catch: all -> 0x07fa, TryCatch #16 {all -> 0x07fa, blocks: (B:23:0x0074, B:27:0x00bd, B:29:0x00ca, B:32:0x00de, B:34:0x00e8, B:36:0x00f2, B:38:0x0107, B:40:0x012c, B:42:0x0138, B:44:0x0141, B:54:0x0173, B:56:0x0178, B:58:0x0183, B:60:0x018e, B:62:0x019e, B:63:0x01d6, B:64:0x01e1, B:65:0x01e2, B:74:0x0227, B:76:0x0235, B:79:0x0240, B:80:0x024c, B:81:0x024d, B:83:0x025d, B:85:0x026f, B:87:0x029b, B:89:0x02ad, B:91:0x02b3, B:100:0x02e7, B:106:0x030c, B:108:0x0314, B:114:0x0328, B:116:0x0336, B:117:0x033a, B:119:0x035d, B:121:0x0370, B:125:0x038b, B:139:0x0401, B:142:0x041c, B:146:0x0430, B:151:0x044b, B:154:0x0466, B:156:0x0471, B:158:0x047b, B:160:0x0485, B:167:0x04aa, B:169:0x04af, B:172:0x04c9, B:195:0x0509, B:197:0x052f, B:199:0x0546, B:201:0x0574, B:203:0x0583, B:205:0x05e8, B:207:0x05f7, B:211:0x061a, B:213:0x063e, B:215:0x06a8, B:219:0x06bc, B:220:0x06c7, B:221:0x06c8, B:222:0x06d3, B:206:0x05f2, B:202:0x057e, B:175:0x04d6, B:176:0x04e1, B:182:0x04e7, B:183:0x04ea, B:184:0x04eb, B:185:0x04f7, B:191:0x04fd, B:192:0x0500, B:223:0x06d4, B:224:0x06df, B:230:0x06e5, B:231:0x06e8, B:239:0x06fa, B:240:0x06fd, B:241:0x06fe, B:242:0x070a, B:248:0x0710, B:249:0x0713, B:255:0x0719, B:256:0x071c, B:124:0x037a, B:257:0x071d, B:258:0x0728, B:259:0x0729, B:260:0x0734, B:109:0x031b, B:261:0x0735, B:262:0x073c, B:263:0x073d, B:264:0x0749, B:274:0x0768, B:275:0x076b, B:276:0x076c, B:277:0x0778, B:278:0x0779, B:279:0x0785, B:280:0x0786, B:281:0x0792, B:289:0x07a5, B:290:0x07a8, B:296:0x07ae, B:297:0x07b1, B:298:0x07b2, B:299:0x07be, B:300:0x07bf, B:301:0x07cb, B:302:0x07cc, B:303:0x07d8, B:304:0x07d9, B:305:0x07e4, B:313:0x07f6, B:314:0x07f9, B:152:0x045a, B:66:0x01ee, B:68:0x01f7, B:72:0x0212, B:71:0x020e, B:282:0x0793, B:283:0x079f, B:170:0x04bb, B:286:0x07a2, B:227:0x06e2, B:92:0x02c2, B:94:0x02cb, B:96:0x02d6, B:98:0x02e1, B:265:0x074a, B:266:0x0756, B:267:0x0757, B:268:0x0762, B:179:0x04e4, B:271:0x0765, B:45:0x014d, B:47:0x0156, B:51:0x016d, B:50:0x0169, B:293:0x07ab, B:24:0x0080, B:26:0x0089, B:306:0x07e5, B:307:0x07f0, B:310:0x07f3, B:147:0x043c, B:149:0x0445, B:232:0x06e9, B:233:0x06f4, B:236:0x06f7, B:161:0x0495, B:163:0x049e, B:188:0x04fa, B:140:0x0410, B:245:0x070d, B:126:0x03ab, B:127:0x03b2, B:129:0x03b8, B:131:0x03d2, B:134:0x03e1, B:132:0x03d9, B:135:0x03ee, B:136:0x03f5, B:137:0x03f6, B:252:0x0716), top: B:368:0x0074, outer: #9, inners: #0, #1, #2, #3, #4, #5, #6, #7, #8, #10, #11, #12, #13, #14, #15, #17, #18, #20, #22, #24 }] */
    /* JADX WARN: Code duplicated, block: B:209:0x0615  */
    /* JADX WARN: Code duplicated, block: B:210:0x0618  */
    /* JADX WARN: Code duplicated, block: B:213:0x063e A[Catch: all -> 0x07fa, TryCatch #16 {all -> 0x07fa, blocks: (B:23:0x0074, B:27:0x00bd, B:29:0x00ca, B:32:0x00de, B:34:0x00e8, B:36:0x00f2, B:38:0x0107, B:40:0x012c, B:42:0x0138, B:44:0x0141, B:54:0x0173, B:56:0x0178, B:58:0x0183, B:60:0x018e, B:62:0x019e, B:63:0x01d6, B:64:0x01e1, B:65:0x01e2, B:74:0x0227, B:76:0x0235, B:79:0x0240, B:80:0x024c, B:81:0x024d, B:83:0x025d, B:85:0x026f, B:87:0x029b, B:89:0x02ad, B:91:0x02b3, B:100:0x02e7, B:106:0x030c, B:108:0x0314, B:114:0x0328, B:116:0x0336, B:117:0x033a, B:119:0x035d, B:121:0x0370, B:125:0x038b, B:139:0x0401, B:142:0x041c, B:146:0x0430, B:151:0x044b, B:154:0x0466, B:156:0x0471, B:158:0x047b, B:160:0x0485, B:167:0x04aa, B:169:0x04af, B:172:0x04c9, B:195:0x0509, B:197:0x052f, B:199:0x0546, B:201:0x0574, B:203:0x0583, B:205:0x05e8, B:207:0x05f7, B:211:0x061a, B:213:0x063e, B:215:0x06a8, B:219:0x06bc, B:220:0x06c7, B:221:0x06c8, B:222:0x06d3, B:206:0x05f2, B:202:0x057e, B:175:0x04d6, B:176:0x04e1, B:182:0x04e7, B:183:0x04ea, B:184:0x04eb, B:185:0x04f7, B:191:0x04fd, B:192:0x0500, B:223:0x06d4, B:224:0x06df, B:230:0x06e5, B:231:0x06e8, B:239:0x06fa, B:240:0x06fd, B:241:0x06fe, B:242:0x070a, B:248:0x0710, B:249:0x0713, B:255:0x0719, B:256:0x071c, B:124:0x037a, B:257:0x071d, B:258:0x0728, B:259:0x0729, B:260:0x0734, B:109:0x031b, B:261:0x0735, B:262:0x073c, B:263:0x073d, B:264:0x0749, B:274:0x0768, B:275:0x076b, B:276:0x076c, B:277:0x0778, B:278:0x0779, B:279:0x0785, B:280:0x0786, B:281:0x0792, B:289:0x07a5, B:290:0x07a8, B:296:0x07ae, B:297:0x07b1, B:298:0x07b2, B:299:0x07be, B:300:0x07bf, B:301:0x07cb, B:302:0x07cc, B:303:0x07d8, B:304:0x07d9, B:305:0x07e4, B:313:0x07f6, B:314:0x07f9, B:152:0x045a, B:66:0x01ee, B:68:0x01f7, B:72:0x0212, B:71:0x020e, B:282:0x0793, B:283:0x079f, B:170:0x04bb, B:286:0x07a2, B:227:0x06e2, B:92:0x02c2, B:94:0x02cb, B:96:0x02d6, B:98:0x02e1, B:265:0x074a, B:266:0x0756, B:267:0x0757, B:268:0x0762, B:179:0x04e4, B:271:0x0765, B:45:0x014d, B:47:0x0156, B:51:0x016d, B:50:0x0169, B:293:0x07ab, B:24:0x0080, B:26:0x0089, B:306:0x07e5, B:307:0x07f0, B:310:0x07f3, B:147:0x043c, B:149:0x0445, B:232:0x06e9, B:233:0x06f4, B:236:0x06f7, B:161:0x0495, B:163:0x049e, B:188:0x04fa, B:140:0x0410, B:245:0x070d, B:126:0x03ab, B:127:0x03b2, B:129:0x03b8, B:131:0x03d2, B:134:0x03e1, B:132:0x03d9, B:135:0x03ee, B:136:0x03f5, B:137:0x03f6, B:252:0x0716), top: B:368:0x0074, outer: #9, inners: #0, #1, #2, #3, #4, #5, #6, #7, #8, #10, #11, #12, #13, #14, #15, #17, #18, #20, #22, #24 }] */
    /* JADX WARN: Code duplicated, block: B:215:0x06a8 A[Catch: all -> 0x07fa, TRY_LEAVE, TryCatch #16 {all -> 0x07fa, blocks: (B:23:0x0074, B:27:0x00bd, B:29:0x00ca, B:32:0x00de, B:34:0x00e8, B:36:0x00f2, B:38:0x0107, B:40:0x012c, B:42:0x0138, B:44:0x0141, B:54:0x0173, B:56:0x0178, B:58:0x0183, B:60:0x018e, B:62:0x019e, B:63:0x01d6, B:64:0x01e1, B:65:0x01e2, B:74:0x0227, B:76:0x0235, B:79:0x0240, B:80:0x024c, B:81:0x024d, B:83:0x025d, B:85:0x026f, B:87:0x029b, B:89:0x02ad, B:91:0x02b3, B:100:0x02e7, B:106:0x030c, B:108:0x0314, B:114:0x0328, B:116:0x0336, B:117:0x033a, B:119:0x035d, B:121:0x0370, B:125:0x038b, B:139:0x0401, B:142:0x041c, B:146:0x0430, B:151:0x044b, B:154:0x0466, B:156:0x0471, B:158:0x047b, B:160:0x0485, B:167:0x04aa, B:169:0x04af, B:172:0x04c9, B:195:0x0509, B:197:0x052f, B:199:0x0546, B:201:0x0574, B:203:0x0583, B:205:0x05e8, B:207:0x05f7, B:211:0x061a, B:213:0x063e, B:215:0x06a8, B:219:0x06bc, B:220:0x06c7, B:221:0x06c8, B:222:0x06d3, B:206:0x05f2, B:202:0x057e, B:175:0x04d6, B:176:0x04e1, B:182:0x04e7, B:183:0x04ea, B:184:0x04eb, B:185:0x04f7, B:191:0x04fd, B:192:0x0500, B:223:0x06d4, B:224:0x06df, B:230:0x06e5, B:231:0x06e8, B:239:0x06fa, B:240:0x06fd, B:241:0x06fe, B:242:0x070a, B:248:0x0710, B:249:0x0713, B:255:0x0719, B:256:0x071c, B:124:0x037a, B:257:0x071d, B:258:0x0728, B:259:0x0729, B:260:0x0734, B:109:0x031b, B:261:0x0735, B:262:0x073c, B:263:0x073d, B:264:0x0749, B:274:0x0768, B:275:0x076b, B:276:0x076c, B:277:0x0778, B:278:0x0779, B:279:0x0785, B:280:0x0786, B:281:0x0792, B:289:0x07a5, B:290:0x07a8, B:296:0x07ae, B:297:0x07b1, B:298:0x07b2, B:299:0x07be, B:300:0x07bf, B:301:0x07cb, B:302:0x07cc, B:303:0x07d8, B:304:0x07d9, B:305:0x07e4, B:313:0x07f6, B:314:0x07f9, B:152:0x045a, B:66:0x01ee, B:68:0x01f7, B:72:0x0212, B:71:0x020e, B:282:0x0793, B:283:0x079f, B:170:0x04bb, B:286:0x07a2, B:227:0x06e2, B:92:0x02c2, B:94:0x02cb, B:96:0x02d6, B:98:0x02e1, B:265:0x074a, B:266:0x0756, B:267:0x0757, B:268:0x0762, B:179:0x04e4, B:271:0x0765, B:45:0x014d, B:47:0x0156, B:51:0x016d, B:50:0x0169, B:293:0x07ab, B:24:0x0080, B:26:0x0089, B:306:0x07e5, B:307:0x07f0, B:310:0x07f3, B:147:0x043c, B:149:0x0445, B:232:0x06e9, B:233:0x06f4, B:236:0x06f7, B:161:0x0495, B:163:0x049e, B:188:0x04fa, B:140:0x0410, B:245:0x070d, B:126:0x03ab, B:127:0x03b2, B:129:0x03b8, B:131:0x03d2, B:134:0x03e1, B:132:0x03d9, B:135:0x03ee, B:136:0x03f5, B:137:0x03f6, B:252:0x0716), top: B:368:0x0074, outer: #9, inners: #0, #1, #2, #3, #4, #5, #6, #7, #8, #10, #11, #12, #13, #14, #15, #17, #18, #20, #22, #24 }] */
    /* JADX WARN: Code duplicated, block: B:219:0x06bc A[Catch: all -> 0x07fa, TRY_ENTER, TryCatch #16 {all -> 0x07fa, blocks: (B:23:0x0074, B:27:0x00bd, B:29:0x00ca, B:32:0x00de, B:34:0x00e8, B:36:0x00f2, B:38:0x0107, B:40:0x012c, B:42:0x0138, B:44:0x0141, B:54:0x0173, B:56:0x0178, B:58:0x0183, B:60:0x018e, B:62:0x019e, B:63:0x01d6, B:64:0x01e1, B:65:0x01e2, B:74:0x0227, B:76:0x0235, B:79:0x0240, B:80:0x024c, B:81:0x024d, B:83:0x025d, B:85:0x026f, B:87:0x029b, B:89:0x02ad, B:91:0x02b3, B:100:0x02e7, B:106:0x030c, B:108:0x0314, B:114:0x0328, B:116:0x0336, B:117:0x033a, B:119:0x035d, B:121:0x0370, B:125:0x038b, B:139:0x0401, B:142:0x041c, B:146:0x0430, B:151:0x044b, B:154:0x0466, B:156:0x0471, B:158:0x047b, B:160:0x0485, B:167:0x04aa, B:169:0x04af, B:172:0x04c9, B:195:0x0509, B:197:0x052f, B:199:0x0546, B:201:0x0574, B:203:0x0583, B:205:0x05e8, B:207:0x05f7, B:211:0x061a, B:213:0x063e, B:215:0x06a8, B:219:0x06bc, B:220:0x06c7, B:221:0x06c8, B:222:0x06d3, B:206:0x05f2, B:202:0x057e, B:175:0x04d6, B:176:0x04e1, B:182:0x04e7, B:183:0x04ea, B:184:0x04eb, B:185:0x04f7, B:191:0x04fd, B:192:0x0500, B:223:0x06d4, B:224:0x06df, B:230:0x06e5, B:231:0x06e8, B:239:0x06fa, B:240:0x06fd, B:241:0x06fe, B:242:0x070a, B:248:0x0710, B:249:0x0713, B:255:0x0719, B:256:0x071c, B:124:0x037a, B:257:0x071d, B:258:0x0728, B:259:0x0729, B:260:0x0734, B:109:0x031b, B:261:0x0735, B:262:0x073c, B:263:0x073d, B:264:0x0749, B:274:0x0768, B:275:0x076b, B:276:0x076c, B:277:0x0778, B:278:0x0779, B:279:0x0785, B:280:0x0786, B:281:0x0792, B:289:0x07a5, B:290:0x07a8, B:296:0x07ae, B:297:0x07b1, B:298:0x07b2, B:299:0x07be, B:300:0x07bf, B:301:0x07cb, B:302:0x07cc, B:303:0x07d8, B:304:0x07d9, B:305:0x07e4, B:313:0x07f6, B:314:0x07f9, B:152:0x045a, B:66:0x01ee, B:68:0x01f7, B:72:0x0212, B:71:0x020e, B:282:0x0793, B:283:0x079f, B:170:0x04bb, B:286:0x07a2, B:227:0x06e2, B:92:0x02c2, B:94:0x02cb, B:96:0x02d6, B:98:0x02e1, B:265:0x074a, B:266:0x0756, B:267:0x0757, B:268:0x0762, B:179:0x04e4, B:271:0x0765, B:45:0x014d, B:47:0x0156, B:51:0x016d, B:50:0x0169, B:293:0x07ab, B:24:0x0080, B:26:0x0089, B:306:0x07e5, B:307:0x07f0, B:310:0x07f3, B:147:0x043c, B:149:0x0445, B:232:0x06e9, B:233:0x06f4, B:236:0x06f7, B:161:0x0495, B:163:0x049e, B:188:0x04fa, B:140:0x0410, B:245:0x070d, B:126:0x03ab, B:127:0x03b2, B:129:0x03b8, B:131:0x03d2, B:134:0x03e1, B:132:0x03d9, B:135:0x03ee, B:136:0x03f5, B:137:0x03f6, B:252:0x0716), top: B:368:0x0074, outer: #9, inners: #0, #1, #2, #3, #4, #5, #6, #7, #8, #10, #11, #12, #13, #14, #15, #17, #18, #20, #22, #24 }] */
    /* JADX WARN: Code duplicated, block: B:221:0x06c8 A[Catch: all -> 0x07fa, TryCatch #16 {all -> 0x07fa, blocks: (B:23:0x0074, B:27:0x00bd, B:29:0x00ca, B:32:0x00de, B:34:0x00e8, B:36:0x00f2, B:38:0x0107, B:40:0x012c, B:42:0x0138, B:44:0x0141, B:54:0x0173, B:56:0x0178, B:58:0x0183, B:60:0x018e, B:62:0x019e, B:63:0x01d6, B:64:0x01e1, B:65:0x01e2, B:74:0x0227, B:76:0x0235, B:79:0x0240, B:80:0x024c, B:81:0x024d, B:83:0x025d, B:85:0x026f, B:87:0x029b, B:89:0x02ad, B:91:0x02b3, B:100:0x02e7, B:106:0x030c, B:108:0x0314, B:114:0x0328, B:116:0x0336, B:117:0x033a, B:119:0x035d, B:121:0x0370, B:125:0x038b, B:139:0x0401, B:142:0x041c, B:146:0x0430, B:151:0x044b, B:154:0x0466, B:156:0x0471, B:158:0x047b, B:160:0x0485, B:167:0x04aa, B:169:0x04af, B:172:0x04c9, B:195:0x0509, B:197:0x052f, B:199:0x0546, B:201:0x0574, B:203:0x0583, B:205:0x05e8, B:207:0x05f7, B:211:0x061a, B:213:0x063e, B:215:0x06a8, B:219:0x06bc, B:220:0x06c7, B:221:0x06c8, B:222:0x06d3, B:206:0x05f2, B:202:0x057e, B:175:0x04d6, B:176:0x04e1, B:182:0x04e7, B:183:0x04ea, B:184:0x04eb, B:185:0x04f7, B:191:0x04fd, B:192:0x0500, B:223:0x06d4, B:224:0x06df, B:230:0x06e5, B:231:0x06e8, B:239:0x06fa, B:240:0x06fd, B:241:0x06fe, B:242:0x070a, B:248:0x0710, B:249:0x0713, B:255:0x0719, B:256:0x071c, B:124:0x037a, B:257:0x071d, B:258:0x0728, B:259:0x0729, B:260:0x0734, B:109:0x031b, B:261:0x0735, B:262:0x073c, B:263:0x073d, B:264:0x0749, B:274:0x0768, B:275:0x076b, B:276:0x076c, B:277:0x0778, B:278:0x0779, B:279:0x0785, B:280:0x0786, B:281:0x0792, B:289:0x07a5, B:290:0x07a8, B:296:0x07ae, B:297:0x07b1, B:298:0x07b2, B:299:0x07be, B:300:0x07bf, B:301:0x07cb, B:302:0x07cc, B:303:0x07d8, B:304:0x07d9, B:305:0x07e4, B:313:0x07f6, B:314:0x07f9, B:152:0x045a, B:66:0x01ee, B:68:0x01f7, B:72:0x0212, B:71:0x020e, B:282:0x0793, B:283:0x079f, B:170:0x04bb, B:286:0x07a2, B:227:0x06e2, B:92:0x02c2, B:94:0x02cb, B:96:0x02d6, B:98:0x02e1, B:265:0x074a, B:266:0x0756, B:267:0x0757, B:268:0x0762, B:179:0x04e4, B:271:0x0765, B:45:0x014d, B:47:0x0156, B:51:0x016d, B:50:0x0169, B:293:0x07ab, B:24:0x0080, B:26:0x0089, B:306:0x07e5, B:307:0x07f0, B:310:0x07f3, B:147:0x043c, B:149:0x0445, B:232:0x06e9, B:233:0x06f4, B:236:0x06f7, B:161:0x0495, B:163:0x049e, B:188:0x04fa, B:140:0x0410, B:245:0x070d, B:126:0x03ab, B:127:0x03b2, B:129:0x03b8, B:131:0x03d2, B:134:0x03e1, B:132:0x03d9, B:135:0x03ee, B:136:0x03f5, B:137:0x03f6, B:252:0x0716), top: B:368:0x0074, outer: #9, inners: #0, #1, #2, #3, #4, #5, #6, #7, #8, #10, #11, #12, #13, #14, #15, #17, #18, #20, #22, #24 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:197:0x052f, please report this as an issue */
    public final ApplyResult applyReceivedSupplierPayment(String operationId) {
        String[] strArr;
        long jUsdCentsToSyp;
        String strTake;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        ContentValues contentValues;
        ContentValues contentValues2;
        String str6;
        ContentValues contentValues3;
        ContentValues contentValues4;
        ApplyResult applyResult;
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        synchronized (APPLY_LOCK) {
            if (StringsKt.isBlank(operationId)) {
                throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0623\u0645\u0631 \u0645\u0637\u0644\u0648\u0628".toString());
            }
            SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
            Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
            SyncDeviceIdentity.Companion companion = SyncDeviceIdentity.INSTANCE;
            Context appContext = this.appContext;
            Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
            String orCreate = companion.getOrCreate(appContext);
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", (String[]) null);
            try {
                Cursor cursor = cursorRawQuery;
                String string = cursor.moveToFirst() ? cursor.getString(0) : null;
                CloseableKt.closeFinally(cursorRawQuery, null);
                Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='authority_device_id'", (String[]) null);
                try {
                    Cursor cursor2 = cursorRawQuery2;
                    String string2 = cursor2.moveToFirst() ? cursor2.getString(0) : null;
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    if (!Intrinsics.areEqual(string, "PRIMARY") || !Intrinsics.areEqual(string2, orCreate)) {
                        throw new IllegalArgumentException("\u0627\u0639\u062a\u0645\u0627\u062f \u062f\u0641\u0639\u0627\u062a \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0627\u0644\u0648\u0627\u0631\u062f\u0629 \u0645\u0633\u0645\u0648\u062d \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0627\u0644\u0645\u0639\u062a\u0645\u062f \u0641\u0642\u0637".toString());
                    }
                    writableDatabase.beginTransaction();
                    try {
                        Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT i.origin_device_id,i.entity_type,i.entity_id,i.payload_json,i.status,\n                          m.primary_user_id,i.payload_sha256,i.origin_created_at\n                   FROM sync_inbox i\n                   JOIN sync_actor_mappings m\n                     ON m.device_id=i.origin_device_id\n                    AND m.remote_actor_user_id=i.actor_user_id AND m.is_active=1\n                   JOIN users u ON u.id=m.primary_user_id\n                   JOIN sync_devices d ON d.device_id=i.origin_device_id\n                   WHERE i.operation_id=? AND d.device_role='SECONDARY'\n                     AND d.status='ACTIVE' AND u.is_active=1 AND u.must_change_password=0\n                   LIMIT 1", new String[]{operationId});
                        try {
                            Cursor cursor3 = cursorRawQuery3;
                            if (!cursor3.moveToFirst()) {
                                throw new IllegalArgumentException("\u0623\u0645\u0631 \u062f\u0641\u0639\u0629 \u0627\u0644\u0645\u0648\u0631\u062f \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0623\u0648 \u0627\u0644\u062c\u0647\u0627\u0632/\u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0645\u0641\u0639\u0644 \u0623\u0648 \u063a\u064a\u0631 \u0645\u0631\u0628\u0648\u0637".toString());
                            }
                            String[] strArr2 = {cursor3.getString(0), cursor3.getString(1), cursor3.getString(2), cursor3.getString(3), cursor3.getString(4), cursor3.getString(5), cursor3.getString(6), cursor3.getString(7)};
                            CloseableKt.closeFinally(cursorRawQuery3, null);
                            if (Intrinsics.areEqual(strArr2[4], "APPLIED")) {
                                writableDatabase.setTransactionSuccessful();
                                applyResult = new ApplyResult(operationId, true, null, 4, null);
                            } else {
                                if (!Intrinsics.areEqual(strArr2[4], "RECEIVED")) {
                                    throw new IllegalArgumentException("\u062d\u0627\u0644\u0629 \u0623\u0645\u0631 \u062f\u0641\u0639\u0629 \u0627\u0644\u0645\u0648\u0631\u062f \u0644\u0627 \u062a\u0633\u0645\u062d \u0628\u0627\u0644\u062a\u0637\u0628\u064a\u0642".toString());
                                }
                                if (!Intrinsics.areEqual(strArr2[1], "SUPPLIER_PAYMENT")) {
                                    throw new IllegalArgumentException("\u0646\u0648\u0639 \u0643\u064a\u0627\u0646 \u062f\u0641\u0639\u0629 \u0627\u0644\u0645\u0648\u0631\u062f \u0627\u0644\u0648\u0627\u0631\u062f \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                }
                                JSONObject jSONObject = new JSONObject(strArr2[3]);
                                if (!Intrinsics.areEqual(jSONObject.optString("business_operation"), "POST_SUPPLIER_PAYMENT")) {
                                    throw new IllegalArgumentException("\u0646\u0648\u0639 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0627\u0644\u0648\u0627\u0631\u062f\u0629 \u0644\u064a\u0633 \u062f\u0641\u0639\u0629 \u0645\u0648\u0631\u062f".toString());
                                }
                                String str7 = strArr2[5];
                                PermissionPolicy permissionPolicy = PermissionPolicy.INSTANCE;
                                Intrinsics.checkNotNull(str7);
                                permissionPolicy.require(writableDatabase, str7, PermissionPolicy.SUPPLIER_PAYMENTS_MANAGE);
                                String string3 = jSONObject.getString("invoice_id");
                                String string4 = jSONObject.getString("payment_id");
                                Intrinsics.checkNotNull(string3);
                                if (!StringsKt.isBlank(string3)) {
                                    Intrinsics.checkNotNull(string4);
                                    if (!StringsKt.isBlank(string4) && Intrinsics.areEqual(strArr2[2], string4)) {
                                        Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT operation_type,payload_hash,result_reference FROM operation_registry WHERE operation_id=?", new String[]{operationId});
                                        try {
                                            Cursor cursor4 = cursorRawQuery4;
                                            if (cursor4.moveToFirst()) {
                                                strArr = new String[]{cursor4.getString(0), cursor4.getString(1), cursor4.isNull(2) ? "" : cursor4.getString(2)};
                                            } else {
                                                strArr = null;
                                            }
                                            CloseableKt.closeFinally(cursorRawQuery4, null);
                                            if (strArr != null) {
                                                if (Intrinsics.areEqual(strArr[0], "PURCHASE_PAYMENT") && Intrinsics.areEqual(strArr[1], strArr2[6])) {
                                                    String str8 = strArr[2];
                                                    Intrinsics.checkNotNullExpressionValue(str8, "get(...)");
                                                    if (!StringsKt.isBlank(str8)) {
                                                        ContentValues contentValues5 = new ContentValues();
                                                        contentValues5.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                                        contentValues5.put("applied_at", timestamp());
                                                        contentValues5.putNull("rejection_reason");
                                                        Unit unit = Unit.INSTANCE;
                                                        writableDatabase.update("sync_inbox", contentValues5, "operation_id=? AND status='RECEIVED'", new String[]{operationId});
                                                        writableDatabase.setTransactionSuccessful();
                                                        applyResult = new ApplyResult(operationId, true, null, 4, null);
                                                    }
                                                }
                                                throw new IllegalArgumentException("\u062a\u0639\u0627\u0631\u0636 \u0641\u064a \u0633\u062c\u0644 \u062f\u0641\u0639\u0629 \u0627\u0644\u0645\u0648\u0631\u062f \u0623\u0648 \u0625\u0639\u0627\u062f\u0629 \u0627\u0633\u062a\u062e\u062f\u0627\u0645 \u0645\u0639\u0631\u0651\u0641\u0647\u0627".toString());
                                            }
                                            Cursor cursorRawQuery5 = writableDatabase.rawQuery("SELECT status,total_usd_cents,supplier_id,invoice_number,\n                          exchange_rate_syp_per_usd_milli\n                   FROM purchase_invoices WHERE id=?", new String[]{string3});
                                            try {
                                                Cursor cursor5 = cursorRawQuery5;
                                                if (!cursor5.moveToFirst()) {
                                                    throw new IllegalArgumentException("\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0634\u0631\u0627\u0621 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629 \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a".toString());
                                                }
                                                String[] strArr3 = {cursor5.getString(0), String.valueOf(cursor5.getLong(1)), cursor5.isNull(2) ? "" : cursor5.getString(2), cursor5.getString(3), String.valueOf(cursor5.getLong(4))};
                                                CloseableKt.closeFinally(cursorRawQuery5, null);
                                                if (!Intrinsics.areEqual(strArr3[0], "POSTED") && !Intrinsics.areEqual(strArr3[0], "PARTIALLY_PAID")) {
                                                    throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u062f\u0641\u0639 \u0641\u0627\u062a\u0648\u0631\u0629 \u0634\u0631\u0627\u0621 \u063a\u064a\u0631 \u0645\u0639\u062a\u0645\u062f\u0629 \u0623\u0648 \u0645\u0644\u063a\u0627\u0629".toString());
                                                }
                                                String str9 = strArr3[2];
                                                Intrinsics.checkNotNullExpressionValue(str9, "get(...)");
                                                if (StringsKt.isBlank(str9)) {
                                                    throw new IllegalArgumentException("\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0634\u0631\u0627\u0621 \u0644\u0627 \u062a\u062d\u062a\u0648\u064a \u0645\u0648\u0631\u062f\u0627\u064b \u0645\u062d\u062f\u062f\u0627\u064b".toString());
                                                }
                                                String str10 = strArr3[4];
                                                Intrinsics.checkNotNullExpressionValue(str10, "get(...)");
                                                long j = Long.parseLong(str10);
                                                if (j <= 0) {
                                                    throw new IllegalArgumentException("\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0634\u0631\u0627\u0621 \u0644\u0627 \u062a\u062d\u062a\u0648\u064a \u0633\u0639\u0631 \u0635\u0631\u0641 \u0645\u062d\u0641\u0648\u0638\u0627\u064b \u0635\u0627\u0644\u062d\u0627\u064b".toString());
                                                }
                                                String string5 = jSONObject.getString("cash_account_id");
                                                String string6 = jSONObject.getString("cash_account_type");
                                                String string7 = jSONObject.getString("cash_account_currency_code");
                                                String string8 = jSONObject.getString("currency_code");
                                                if (!SetsKt.setOf((Object[]) new String[]{"CASH_BOX", "WALLET"}).contains(string6) || !SetsKt.setOf((Object[]) new String[]{"USD", "SYP"}).contains(string7) || !Intrinsics.areEqual(string8, string7)) {
                                                    throw new IllegalArgumentException("\u0646\u0648\u0639 \u0623\u0648 \u0639\u0645\u0644\u0629 \u062d\u0633\u0627\u0628 \u062f\u0641\u0639 \u0627\u0644\u0645\u0648\u0631\u062f \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u064a\u0646".toString());
                                                }
                                                Cursor cursorRawQuery6 = writableDatabase.rawQuery("SELECT m.primary_cash_account_id,a.account_type,a.currency_code,a.opening_balance_minor\n                   FROM sync_cash_account_mappings m\n                   JOIN cash_accounts a ON a.id=m.primary_cash_account_id\n                   WHERE m.device_id=? AND m.remote_cash_account_id=? AND m.is_active=1\n                     AND a.is_active=1", new String[]{strArr2[0], string5});
                                                try {
                                                    Cursor cursor6 = cursorRawQuery6;
                                                    if (!cursor6.moveToFirst()) {
                                                        throw new IllegalArgumentException("\u062d\u0633\u0627\u0628 \u062f\u0641\u0639 \u0627\u0644\u0645\u0648\u0631\u062f \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u063a\u064a\u0631 \u0645\u0631\u0628\u0648\u0637 \u0623\u0648 \u0645\u0648\u0642\u0648\u0641".toString());
                                                    }
                                                    if (!Intrinsics.areEqual(cursor6.getString(1), string6) || !Intrinsics.areEqual(cursor6.getString(2), string7)) {
                                                        throw new IllegalArgumentException("\u0646\u0648\u0639 \u0623\u0648 \u0639\u0645\u0644\u0629 \u062d\u0633\u0627\u0628 \u062f\u0641\u0639 \u0627\u0644\u0645\u0648\u0631\u062f \u0627\u0644\u0645\u0631\u062a\u0628\u0637 \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0623\u0635\u0644\u064a".toString());
                                                    }
                                                    String string9 = cursor6.getString(0);
                                                    CloseableKt.closeFinally(cursorRawQuery6, null);
                                                    long j2 = jSONObject.getLong("amount_minor");
                                                    long jOptLong = jSONObject.optLong("payment_rate_milli", 0L);
                                                    long j3 = jSONObject.getLong("amount_syp_equivalent");
                                                    if (j2 <= 0 || jOptLong < 0 || j3 <= 0) {
                                                        throw new IllegalArgumentException("\u0645\u0628\u0644\u063a \u0623\u0648 \u0633\u0639\u0631 \u0635\u0631\u0641 \u062f\u0641\u0639\u0629 \u0627\u0644\u0645\u0648\u0631\u062f \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                                    }
                                                    if (Intrinsics.areEqual(string8, "USD")) {
                                                        jUsdCentsToSyp = CurrencyConverter.INSTANCE.usdCentsToSyp(j2, j);
                                                    } else {
                                                        if (!Intrinsics.areEqual(string8, "SYP")) {
                                                            throw new IllegalArgumentException("\u0639\u0645\u0644\u0629 \u062f\u0641\u0639\u0629 \u0627\u0644\u0645\u0648\u0631\u062f \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u0629");
                                                        }
                                                        jUsdCentsToSyp = j2;
                                                    }
                                                    if (jUsdCentsToSyp != j3) {
                                                        throw new IllegalArgumentException("\u0627\u0644\u0645\u0642\u0627\u0628\u0644 \u0627\u0644\u0633\u0648\u0631\u064a \u0644\u062f\u0641\u0639\u0629 \u0627\u0644\u0645\u0648\u0631\u062f \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u0633\u0639\u0631 \u0635\u0631\u0641 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0623\u0635\u0644\u064a".toString());
                                                    }
                                                    String strOptString = jSONObject.optString("occurred_at");
                                                    if (StringsKt.isBlank(strOptString)) {
                                                        strOptString = strArr2[7];
                                                    }
                                                    String str11 = strOptString;
                                                    String strOptString2 = jSONObject.optString("operating_date");
                                                    Intrinsics.checkNotNull(str11);
                                                    if (new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(str11)) {
                                                        Intrinsics.checkNotNull(strOptString2);
                                                        if (new Regex("\\d{4}-\\d{2}-\\d{2}").matches(strOptString2)) {
                                                            if (jSONObject.isNull("note")) {
                                                                strTake = null;
                                                            } else {
                                                                String string10 = jSONObject.getString("note");
                                                                Intrinsics.checkNotNullExpressionValue(string10, "getString(...)");
                                                                strTake = StringsKt.take(string10, 500);
                                                            }
                                                            CurrencyConverter currencyConverter = CurrencyConverter.INSTANCE;
                                                            String str12 = strArr3[1];
                                                            Intrinsics.checkNotNullExpressionValue(str12, "get(...)");
                                                            long jUsdCentsToSyp2 = currencyConverter.usdCentsToSyp(Long.parseLong(str12), j);
                                                            Cursor cursorRawQuery7 = writableDatabase.rawQuery("SELECT amount_minor,currency_code FROM invoice_payments\n                   WHERE invoice_type='PURCHASE' AND invoice_id=?", new String[]{string3});
                                                            try {
                                                                Cursor cursor7 = cursorRawQuery7;
                                                                long jM = 0;
                                                                while (cursor7.moveToNext()) {
                                                                    String str13 = strTake;
                                                                    String str14 = string9;
                                                                    String str15 = strOptString2;
                                                                    long jUsdCentsToSyp3 = cursor7.getLong(0);
                                                                    long j4 = jUsdCentsToSyp;
                                                                    String string11 = cursor7.getString(1);
                                                                    if (Intrinsics.areEqual(string11, "USD")) {
                                                                        jUsdCentsToSyp3 = CurrencyConverter.INSTANCE.usdCentsToSyp(jUsdCentsToSyp3, j);
                                                                    } else if (!Intrinsics.areEqual(string11, "SYP")) {
                                                                        throw new IllegalArgumentException("\u0639\u0645\u0644\u0629 \u062f\u0641\u0639\u0629 \u0633\u0627\u0628\u0642\u0629 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u0629");
                                                                    }
                                                                    jM = MainActivity$$ExternalSyntheticBackport0.m(jM, jUsdCentsToSyp3);
                                                                    strTake = str13;
                                                                    string9 = str14;
                                                                    strOptString2 = str15;
                                                                    jUsdCentsToSyp = j4;
                                                                }
                                                                String str16 = strTake;
                                                                String str17 = string9;
                                                                String str18 = strOptString2;
                                                                long j5 = jUsdCentsToSyp;
                                                                Unit unit2 = Unit.INSTANCE;
                                                                CloseableKt.closeFinally(cursorRawQuery7, null);
                                                                Cursor cursorRawQuery8 = writableDatabase.rawQuery("SELECT COALESCE(SUM(supplier_credit_reduction_syp_minor),0)\n                   FROM returns WHERE original_invoice_type='PURCHASE'\n                     AND original_invoice_id=? AND status='POSTED'", new String[]{string3});
                                                                try {
                                                                    Cursor cursor8 = cursorRawQuery8;
                                                                    cursor8.moveToFirst();
                                                                    long j6 = cursor8.getLong(0);
                                                                    CloseableKt.closeFinally(cursorRawQuery8, null);
                                                                    long jCoerceAtLeast = RangesKt.coerceAtLeast((jUsdCentsToSyp2 - jM) - j6, 0L);
                                                                    if (jCoerceAtLeast <= 0 || j5 > jCoerceAtLeast) {
                                                                        throw new IllegalArgumentException("\u0645\u0628\u0644\u063a \u062f\u0641\u0639\u0629 \u0627\u0644\u0645\u0648\u0631\u062f \u064a\u062a\u062c\u0627\u0648\u0632 \u0627\u0644\u0645\u062a\u0628\u0642\u064a \u0627\u0644\u0633\u0648\u0631\u064a \u0627\u0644\u062b\u0627\u0628\u062a \u0639\u0644\u0649 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629".toString());
                                                                    }
                                                                    Cursor cursorRawQuery9 = writableDatabase.rawQuery("SELECT opening_balance_minor FROM cash_accounts WHERE id=? AND is_active=1", new String[]{str17});
                                                                    try {
                                                                        Cursor cursor9 = cursorRawQuery9;
                                                                        if (!cursor9.moveToFirst()) {
                                                                            throw new IllegalArgumentException("Failed requirement.".toString());
                                                                        }
                                                                        long j7 = cursor9.getLong(0);
                                                                        CloseableKt.closeFinally(cursorRawQuery9, null);
                                                                        Cursor cursorRawQuery10 = writableDatabase.rawQuery("SELECT COALESCE(SUM(CASE WHEN direction='IN' THEN amount_minor\n                           WHEN direction='OUT' THEN -amount_minor ELSE 0 END),0)\n                   FROM cash_transactions WHERE account_id=?", new String[]{str17});
                                                                        try {
                                                                            Cursor cursor10 = cursorRawQuery10;
                                                                            cursor10.moveToFirst();
                                                                            long j8 = cursor10.getLong(0);
                                                                            CloseableKt.closeFinally(cursorRawQuery10, null);
                                                                            if (MainActivity$$ExternalSyntheticBackport0.m(j7, j8) < j2) {
                                                                                throw new IllegalArgumentException("\u0631\u0635\u064a\u062f \u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u063a\u064a\u0631 \u0643\u0627\u0641\u064d \u0644\u0627\u0639\u062a\u0645\u0627\u062f \u062f\u0641\u0639\u0629 \u0627\u0644\u0645\u0648\u0631\u062f".toString());
                                                                            }
                                                                            if (Intrinsics.areEqual(string6, "CASH_BOX")) {
                                                                                str = string8;
                                                                                if (Intrinsics.areEqual(str, "SYP")) {
                                                                                    str2 = str17;
                                                                                    str3 = str18;
                                                                                    Cursor cursorRawQuery11 = writableDatabase.rawQuery("SELECT allocated_minor FROM daily_supplier_rollovers WHERE operating_date=? AND account_id=?", new String[]{str3, str2});
                                                                                    try {
                                                                                        Cursor cursor11 = cursorRawQuery11;
                                                                                        Long lValueOf = cursor11.moveToFirst() ? Long.valueOf(cursor11.getLong(0)) : null;
                                                                                        CloseableKt.closeFinally(cursorRawQuery11, null);
                                                                                        if (lValueOf == null) {
                                                                                            throw new IllegalArgumentException("\u0644\u0627 \u064a\u0648\u062c\u062f \u062a\u062e\u0635\u064a\u0635 \u0646\u0642\u062f \u0645\u062f\u0648\u0631 \u0644\u0647\u0630\u0627 \u0627\u0644\u0635\u0646\u062f\u0648\u0642 \u0641\u064a \u062a\u0627\u0631\u064a\u062e \u062f\u0641\u0639\u0629 \u0627\u0644\u0645\u0648\u0631\u062f \u0627\u0644\u0623\u0635\u0644\u064a\u0629".toString());
                                                                                        }
                                                                                        Cursor cursorRawQuery12 = writableDatabase.rawQuery("SELECT COALESCE(SUM(amount_minor),0) FROM cash_transactions\n                       WHERE account_id=? AND direction='OUT'\n                         AND transaction_type IN ('PURCHASE_PAYMENT','PERSONAL_WITHDRAWAL')\n                         AND COALESCE(operating_date,substr(created_at,1,10))=?", new String[]{str2, str3});
                                                                                        try {
                                                                                            Cursor cursor12 = cursorRawQuery12;
                                                                                            cursor12.moveToFirst();
                                                                                            long j9 = cursor12.getLong(0);
                                                                                            CloseableKt.closeFinally(cursorRawQuery12, null);
                                                                                            if (lValueOf.longValue() - j9 < j2) {
                                                                                                throw new IllegalArgumentException("\u0627\u0644\u0645\u0628\u0644\u063a \u0627\u0644\u0645\u062a\u0627\u062d \u0645\u0646 \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0644\u0627 \u064a\u0643\u0641\u064a \u0644\u0627\u0639\u062a\u0645\u0627\u062f \u062f\u0641\u0639\u0629 \u0627\u0644\u0645\u0648\u0631\u062f \u0627\u0644\u0648\u0627\u0631\u062f\u0629".toString());
                                                                                            }
                                                                                        } catch (Throwable th) {
                                                                                            try {
                                                                                                throw th;
                                                                                            } catch (Throwable th2) {
                                                                                                CloseableKt.closeFinally(cursorRawQuery12, th);
                                                                                                throw th2;
                                                                                            }
                                                                                        }
                                                                                    } catch (Throwable th3) {
                                                                                        try {
                                                                                            throw th3;
                                                                                        } catch (Throwable th4) {
                                                                                            CloseableKt.closeFinally(cursorRawQuery11, th3);
                                                                                            throw th4;
                                                                                        }
                                                                                    }
                                                                                }
                                                                                str4 = "sync-payment-" + operationId;
                                                                                String str19 = "sync-cash-" + operationId;
                                                                                if (str16 == null) {
                                                                                    str5 = "\u062f\u0641\u0639\u0629 \u0644\u0644\u0645\u0648\u0631\u062f \u0639\u0646 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 " + strArr3[3];
                                                                                } else {
                                                                                    str5 = str16;
                                                                                }
                                                                                contentValues = new ContentValues();
                                                                                contentValues.put("id", str19);
                                                                                contentValues.put("account_id", str2);
                                                                                contentValues.put("direction", "OUT");
                                                                                contentValues.put("amount_minor", Long.valueOf(j2));
                                                                                contentValues.put("currency_code", str);
                                                                                if (jOptLong > 0) {
                                                                                    contentValues.put("exchange_rate_syp_per_usd_milli", Long.valueOf(jOptLong));
                                                                                } else {
                                                                                    contentValues.putNull("exchange_rate_syp_per_usd_milli");
                                                                                }
                                                                                contentValues.put("transaction_type", "PURCHASE_PAYMENT");
                                                                                contentValues.put("source_type", "PURCHASE_INVOICE");
                                                                                contentValues.put("source_id", string3);
                                                                                contentValues.put("operation_id", operationId);
                                                                                contentValues.put("description", str5);
                                                                                contentValues.put("created_by", str7);
                                                                                contentValues.put("operating_date", str3);
                                                                                contentValues.put("created_at", str11);
                                                                                Unit unit3 = Unit.INSTANCE;
                                                                                writableDatabase.insertOrThrow("cash_transactions", null, contentValues);
                                                                                contentValues2 = new ContentValues();
                                                                                contentValues2.put("id", str4);
                                                                                contentValues2.put("invoice_type", "PURCHASE");
                                                                                contentValues2.put("invoice_id", string3);
                                                                                contentValues2.put("cash_transaction_id", str19);
                                                                                contentValues2.put("amount_minor", Long.valueOf(j2));
                                                                                contentValues2.put("currency_code", str);
                                                                                if (jOptLong > 0) {
                                                                                    contentValues2.put("exchange_rate_syp_per_usd_milli", Long.valueOf(jOptLong));
                                                                                } else {
                                                                                    contentValues2.putNull("exchange_rate_syp_per_usd_milli");
                                                                                }
                                                                                contentValues2.put("created_at", str11);
                                                                                Unit unit4 = Unit.INSTANCE;
                                                                                writableDatabase.insertOrThrow("invoice_payments", null, contentValues2);
                                                                                if (RangesKt.coerceAtLeast((jUsdCentsToSyp2 - j6) - MainActivity$$ExternalSyntheticBackport0.m(jM, j5), 0L) == 0) {
                                                                                    str6 = "PAID";
                                                                                } else {
                                                                                    str6 = "PARTIALLY_PAID";
                                                                                }
                                                                                contentValues3 = new ContentValues();
                                                                                contentValues3.put(NotificationCompat.CATEGORY_STATUS, str6);
                                                                                contentValues3.put("updated_at", timestamp());
                                                                                Unit unit5 = Unit.INSTANCE;
                                                                                if (writableDatabase.update("purchase_invoices", contentValues3, "id=? AND status IN ('POSTED','PARTIALLY_PAID')", new String[]{string3}) == 1) {
                                                                                    throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062d\u062f\u064a\u062b \u062d\u0627\u0644\u0629 \u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0634\u0631\u0627\u0621 \u0628\u0639\u062f \u0627\u0639\u062a\u0645\u0627\u062f \u062f\u0641\u0639\u0629 \u0627\u0644\u0645\u0648\u0631\u062f".toString());
                                                                                }
                                                                                ContentValues contentValues6 = new ContentValues();
                                                                                contentValues6.put("operation_id", operationId);
                                                                                contentValues6.put("operation_type", "PURCHASE_PAYMENT");
                                                                                contentValues6.put("payload_hash", strArr2[6]);
                                                                                contentValues6.put(NotificationCompat.CATEGORY_STATUS, "COMPLETED");
                                                                                contentValues6.put("result_reference", str4);
                                                                                contentValues6.put("created_at", timestamp());
                                                                                contentValues6.put("completed_at", timestamp());
                                                                                Unit unit6 = Unit.INSTANCE;
                                                                                writableDatabase.insertOrThrow("operation_registry", null, contentValues6);
                                                                                contentValues4 = new ContentValues();
                                                                                contentValues4.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                                                                contentValues4.put("applied_at", timestamp());
                                                                                contentValues4.putNull("rejection_reason");
                                                                                Unit unit7 = Unit.INSTANCE;
                                                                                if (writableDatabase.update("sync_inbox", contentValues4, "operation_id=? AND status='RECEIVED'", new String[]{operationId}) == 1) {
                                                                                    throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062b\u0628\u064a\u062a \u062d\u0627\u0644\u0629 \u0627\u0639\u062a\u0645\u0627\u062f \u062f\u0641\u0639\u0629 \u0627\u0644\u0645\u0648\u0631\u062f".toString());
                                                                                }
                                                                                writableDatabase.setTransactionSuccessful();
                                                                                applyResult = new ApplyResult(operationId, true, null, 4, null);
                                                                            } else {
                                                                                str = string8;
                                                                            }
                                                                            str2 = str17;
                                                                            str3 = str18;
                                                                            str4 = "sync-payment-" + operationId;
                                                                            String str110 = "sync-cash-" + operationId;
                                                                            if (str16 == null) {
                                                                                str5 = "\u062f\u0641\u0639\u0629 \u0644\u0644\u0645\u0648\u0631\u062f \u0639\u0646 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 " + strArr3[3];
                                                                            } else {
                                                                                str5 = str16;
                                                                            }
                                                                            contentValues = new ContentValues();
                                                                            contentValues.put("id", str110);
                                                                            contentValues.put("account_id", str2);
                                                                            contentValues.put("direction", "OUT");
                                                                            contentValues.put("amount_minor", Long.valueOf(j2));
                                                                            contentValues.put("currency_code", str);
                                                                            if (jOptLong > 0) {
                                                                                contentValues.put("exchange_rate_syp_per_usd_milli", Long.valueOf(jOptLong));
                                                                            } else {
                                                                                contentValues.putNull("exchange_rate_syp_per_usd_milli");
                                                                            }
                                                                            contentValues.put("transaction_type", "PURCHASE_PAYMENT");
                                                                            contentValues.put("source_type", "PURCHASE_INVOICE");
                                                                            contentValues.put("source_id", string3);
                                                                            contentValues.put("operation_id", operationId);
                                                                            contentValues.put("description", str5);
                                                                            contentValues.put("created_by", str7);
                                                                            contentValues.put("operating_date", str3);
                                                                            contentValues.put("created_at", str11);
                                                                            Unit unit8 = Unit.INSTANCE;
                                                                            writableDatabase.insertOrThrow("cash_transactions", null, contentValues);
                                                                            contentValues2 = new ContentValues();
                                                                            contentValues2.put("id", str4);
                                                                            contentValues2.put("invoice_type", "PURCHASE");
                                                                            contentValues2.put("invoice_id", string3);
                                                                            contentValues2.put("cash_transaction_id", str110);
                                                                            contentValues2.put("amount_minor", Long.valueOf(j2));
                                                                            contentValues2.put("currency_code", str);
                                                                            if (jOptLong > 0) {
                                                                                contentValues2.put("exchange_rate_syp_per_usd_milli", Long.valueOf(jOptLong));
                                                                            } else {
                                                                                contentValues2.putNull("exchange_rate_syp_per_usd_milli");
                                                                            }
                                                                            contentValues2.put("created_at", str11);
                                                                            Unit unit9 = Unit.INSTANCE;
                                                                            writableDatabase.insertOrThrow("invoice_payments", null, contentValues2);
                                                                            if (RangesKt.coerceAtLeast((jUsdCentsToSyp2 - j6) - MainActivity$$ExternalSyntheticBackport0.m(jM, j5), 0L) == 0) {
                                                                                str6 = "PAID";
                                                                            } else {
                                                                                str6 = "PARTIALLY_PAID";
                                                                            }
                                                                            contentValues3 = new ContentValues();
                                                                            contentValues3.put(NotificationCompat.CATEGORY_STATUS, str6);
                                                                            contentValues3.put("updated_at", timestamp());
                                                                            Unit unit10 = Unit.INSTANCE;
                                                                            if (writableDatabase.update("purchase_invoices", contentValues3, "id=? AND status IN ('POSTED','PARTIALLY_PAID')", new String[]{string3}) == 1) {
                                                                                throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062d\u062f\u064a\u062b \u062d\u0627\u0644\u0629 \u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0634\u0631\u0627\u0621 \u0628\u0639\u062f \u0627\u0639\u062a\u0645\u0627\u062f \u062f\u0641\u0639\u0629 \u0627\u0644\u0645\u0648\u0631\u062f".toString());
                                                                            }
                                                                            ContentValues contentValues7 = new ContentValues();
                                                                            contentValues7.put("operation_id", operationId);
                                                                            contentValues7.put("operation_type", "PURCHASE_PAYMENT");
                                                                            contentValues7.put("payload_hash", strArr2[6]);
                                                                            contentValues7.put(NotificationCompat.CATEGORY_STATUS, "COMPLETED");
                                                                            contentValues7.put("result_reference", str4);
                                                                            contentValues7.put("created_at", timestamp());
                                                                            contentValues7.put("completed_at", timestamp());
                                                                            Unit unit11 = Unit.INSTANCE;
                                                                            writableDatabase.insertOrThrow("operation_registry", null, contentValues7);
                                                                            contentValues4 = new ContentValues();
                                                                            contentValues4.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                                                            contentValues4.put("applied_at", timestamp());
                                                                            contentValues4.putNull("rejection_reason");
                                                                            Unit unit12 = Unit.INSTANCE;
                                                                            if (writableDatabase.update("sync_inbox", contentValues4, "operation_id=? AND status='RECEIVED'", new String[]{operationId}) == 1) {
                                                                                throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062b\u0628\u064a\u062a \u062d\u0627\u0644\u0629 \u0627\u0639\u062a\u0645\u0627\u062f \u062f\u0641\u0639\u0629 \u0627\u0644\u0645\u0648\u0631\u062f".toString());
                                                                            }
                                                                            writableDatabase.setTransactionSuccessful();
                                                                            applyResult = new ApplyResult(operationId, true, null, 4, null);
                                                                        } catch (Throwable th5) {
                                                                            try {
                                                                                throw th5;
                                                                            } catch (Throwable th6) {
                                                                                CloseableKt.closeFinally(cursorRawQuery10, th5);
                                                                                throw th6;
                                                                            }
                                                                        }
                                                                    } catch (Throwable th7) {
                                                                        try {
                                                                            throw th7;
                                                                        } catch (Throwable th8) {
                                                                            CloseableKt.closeFinally(cursorRawQuery9, th7);
                                                                            throw th8;
                                                                        }
                                                                    }
                                                                } catch (Throwable th9) {
                                                                    try {
                                                                        throw th9;
                                                                    } catch (Throwable th10) {
                                                                        CloseableKt.closeFinally(cursorRawQuery8, th9);
                                                                        throw th10;
                                                                    }
                                                                }
                                                            } catch (Throwable th11) {
                                                                try {
                                                                    throw th11;
                                                                } catch (Throwable th12) {
                                                                    CloseableKt.closeFinally(cursorRawQuery7, th11);
                                                                    throw th12;
                                                                }
                                                            }
                                                        }
                                                    }
                                                    throw new IllegalArgumentException("\u062a\u0627\u0631\u064a\u062e \u0623\u0648 \u0648\u0642\u062a \u062f\u0641\u0639\u0629 \u0627\u0644\u0645\u0648\u0631\u062f \u0627\u0644\u0623\u0635\u0644\u064a \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                                } catch (Throwable th13) {
                                                    try {
                                                        throw th13;
                                                    } catch (Throwable th14) {
                                                        CloseableKt.closeFinally(cursorRawQuery6, th13);
                                                        throw th14;
                                                    }
                                                }
                                            } catch (Throwable th15) {
                                                try {
                                                    throw th15;
                                                } catch (Throwable th16) {
                                                    CloseableKt.closeFinally(cursorRawQuery5, th15);
                                                    throw th16;
                                                }
                                            }
                                        } catch (Throwable th17) {
                                            try {
                                                throw th17;
                                            } catch (Throwable th18) {
                                                CloseableKt.closeFinally(cursorRawQuery4, th17);
                                                throw th18;
                                            }
                                        }
                                    }
                                }
                                throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u062f\u0641\u0639\u0629 \u0627\u0644\u0645\u0648\u0631\u062f \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0643\u064a\u0627\u0646 \u0641\u064a \u0627\u0644\u0638\u0631\u0641".toString());
                            }
                            writableDatabase.endTransaction();
                        } catch (Throwable th19) {
                            try {
                                throw th19;
                            } catch (Throwable th20) {
                                CloseableKt.closeFinally(cursorRawQuery3, th19);
                                throw th20;
                            }
                        }
                    } catch (Throwable th21) {
                        writableDatabase.endTransaction();
                        throw th21;
                    }
                } catch (Throwable th22) {
                    try {
                        throw th22;
                    } catch (Throwable th23) {
                        CloseableKt.closeFinally(cursorRawQuery2, th22);
                        throw th23;
                    }
                }
            } catch (Throwable th24) {
                try {
                    throw th24;
                } catch (Throwable th25) {
                    CloseableKt.closeFinally(cursorRawQuery, th24);
                    throw th25;
                }
            }
        }
        return applyResult;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v11 */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v26, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v30 */
    /* JADX WARN: Type inference failed for: r12v31 */
    /* JADX WARN: Type inference failed for: r12v35, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v39 */
    /* JADX WARN: Type inference failed for: r12v4, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r12v53 */
    /* JADX WARN: Type inference failed for: r1v108, types: [android.content.ContentValues] */
    /* JADX WARN: Type inference failed for: r6v10, types: [com.hazem.dailystore.data.PermissionPolicy] */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.lang.Object, net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 4 */
    public final ApplyResult applyReceivedCustomerCollection(String operationId) throws Throwable {
        Throwable th;
        Throwable th2;
        String string;
        Throwable th3;
        String string2;
        Throwable th4;
        ?? r12;
        Throwable th5;
        Triple triple;
        Throwable th6;
        Throwable th7;
        String strTake;
        Throwable th8;
        String string3;
        Throwable th9;
        String str;
        ApplyResult applyResult;
        long j;
        int i;
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        Object obj = APPLY_LOCK;
        synchronized (obj) {
            try {
                try {
                    if (StringsKt.isBlank(operationId)) {
                        throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0623\u0645\u0631 \u0645\u0637\u0644\u0648\u0628".toString());
                    }
                    ?? writableDatabase = this.database.getWritableDatabase();
                    Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
                    SyncDeviceIdentity.Companion companion = SyncDeviceIdentity.INSTANCE;
                    Context appContext = this.appContext;
                    Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
                    String orCreate = companion.getOrCreate(appContext);
                    Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='authority_device_id'", null);
                    try {
                        Cursor cursor = cursorRawQuery;
                        if (cursor.moveToFirst()) {
                            try {
                                string = cursor.getString(0);
                            } catch (Throwable th10) {
                                th2 = th10;
                                try {
                                    throw th2;
                                } catch (Throwable th11) {
                                    CloseableKt.closeFinally(cursorRawQuery, th2);
                                    throw th11;
                                }
                            }
                        } else {
                            string = null;
                        }
                        CloseableKt.closeFinally(cursorRawQuery, null);
                        Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", null);
                        try {
                            Cursor cursor2 = cursorRawQuery2;
                            writableDatabase = cursor2.moveToFirst();
                            if (writableDatabase != 0) {
                                try {
                                    string2 = cursor2.getString(0);
                                } catch (Throwable th12) {
                                    th3 = th12;
                                    try {
                                        throw th3;
                                    } catch (Throwable th13) {
                                        CloseableKt.closeFinally(cursorRawQuery2, th3);
                                        throw th13;
                                    }
                                }
                            } else {
                                string2 = null;
                            }
                            CloseableKt.closeFinally(cursorRawQuery2, null);
                            if (!Intrinsics.areEqual(string2, "PRIMARY") || !Intrinsics.areEqual(string, orCreate)) {
                                throw new IllegalArgumentException("\u062a\u0637\u0628\u064a\u0642 \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u0627\u0644\u0648\u0627\u0631\u062f \u0645\u0633\u0645\u0648\u062d \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0627\u0644\u0645\u0639\u062a\u0645\u062f \u0641\u0642\u0637".toString());
                            }
                            writableDatabase.beginTransaction();
                            try {
                                try {
                                    Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT i.origin_device_id,i.entity_type,i.entity_id,i.payload_json,i.status,\n                          m.primary_user_id,i.origin_created_at,i.payload_sha256\n                   FROM sync_inbox i\n                   JOIN sync_actor_mappings m\n                     ON m.device_id=i.origin_device_id\n                    AND m.remote_actor_user_id=i.actor_user_id AND m.is_active=1\n                   JOIN users u ON u.id=m.primary_user_id\n                   JOIN sync_devices d ON d.device_id=i.origin_device_id\n                   WHERE i.operation_id=? AND d.device_role='SECONDARY'\n                     AND d.status='ACTIVE' AND u.is_active=1 AND u.must_change_password=0\n                   LIMIT 1", new String[]{operationId});
                                    try {
                                        Cursor cursor3 = cursorRawQuery3;
                                        if (!cursor3.moveToFirst()) {
                                            try {
                                                throw new IllegalArgumentException("\u0623\u0645\u0631 \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0623\u0648 \u0627\u0644\u062c\u0647\u0627\u0632/\u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0645\u0641\u0639\u0644 \u0623\u0648 \u063a\u064a\u0631 \u0645\u0631\u0628\u0648\u0637".toString());
                                            } catch (Throwable th14) {
                                                th = th14;
                                            }
                                        } else {
                                            String string4 = cursor3.getString(0);
                                            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                                            String string5 = cursor3.getString(1);
                                            Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                                            String string6 = cursor3.getString(2);
                                            Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                                            String string7 = cursor3.getString(3);
                                            Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
                                            String string8 = cursor3.getString(4);
                                            Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
                                            String string9 = cursor3.getString(5);
                                            Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
                                            String string10 = cursor3.getString(6);
                                            Intrinsics.checkNotNullExpressionValue(string10, "getString(...)");
                                            Pair pair = TuplesKt.to(new InboxCommand(string4, string5, string6, string7, string8, string9, string10), cursor3.getString(7));
                                            CloseableKt.closeFinally(cursorRawQuery3, null);
                                            InboxCommand inboxCommand = (InboxCommand) pair.getFirst();
                                            String str2 = (String) pair.getSecond();
                                            try {
                                                try {
                                                    if (Intrinsics.areEqual(inboxCommand.getStatus(), "APPLIED")) {
                                                        writableDatabase.setTransactionSuccessful();
                                                        applyResult = new ApplyResult(operationId, true, null, 4, null);
                                                    } else {
                                                        if (!Intrinsics.areEqual(inboxCommand.getStatus(), "RECEIVED")) {
                                                            throw new IllegalArgumentException(("\u062d\u0627\u0644\u0629 \u0623\u0645\u0631 \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u0644\u0627 \u062a\u0633\u0645\u062d \u0628\u0627\u0644\u062a\u0637\u0628\u064a\u0642: " + inboxCommand.getStatus()).toString());
                                                        }
                                                        if (!Intrinsics.areEqual(inboxCommand.getEntityType(), "CUSTOMER_PAYMENT")) {
                                                            throw new IllegalArgumentException("\u0646\u0648\u0639 \u0643\u064a\u0627\u0646 \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u0627\u0644\u0648\u0627\u0631\u062f \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                                        }
                                                        JSONObject jSONObject = new JSONObject(inboxCommand.getPayloadJson());
                                                        if (!Intrinsics.areEqual(jSONObject.optString("business_operation"), "COLLECT_CUSTOMER_PAYMENT")) {
                                                            throw new IllegalArgumentException("\u0646\u0648\u0639 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0627\u0644\u0648\u0627\u0631\u062f\u0629 \u0644\u064a\u0633 \u062a\u062d\u0635\u064a\u0644\u0627\u064b \u0644\u0630\u0645\u0645 \u0627\u0644\u0632\u0628\u0627\u0626\u0646".toString());
                                                        }
                                                        PermissionPolicy.INSTANCE.require(writableDatabase, inboxCommand.getPrimaryUserId(), PermissionPolicy.CUSTOMERS_COLLECT);
                                                        String string11 = jSONObject.getString("invoice_id");
                                                        String string12 = jSONObject.getString("payment_id");
                                                        Intrinsics.checkNotNull(string11);
                                                        if (!StringsKt.isBlank(string11)) {
                                                            Intrinsics.checkNotNull(string12);
                                                            if (!StringsKt.isBlank(string12) && Intrinsics.areEqual(inboxCommand.getEntityId(), string12)) {
                                                                Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT operation_type,payload_hash,result_reference FROM operation_registry WHERE operation_id=?", new String[]{operationId});
                                                                try {
                                                                    Cursor cursor4 = cursorRawQuery4;
                                                                    if (cursor4.moveToFirst()) {
                                                                        try {
                                                                            triple = new Triple(cursor4.getString(0), cursor4.getString(1), cursor4.isNull(2) ? null : cursor4.getString(2));
                                                                            th6 = null;
                                                                        } catch (Throwable th15) {
                                                                            th5 = th15;
                                                                            try {
                                                                                throw th5;
                                                                            } catch (Throwable th16) {
                                                                                CloseableKt.closeFinally(cursorRawQuery4, th5);
                                                                                throw th16;
                                                                            }
                                                                        }
                                                                    } else {
                                                                        th6 = null;
                                                                        triple = null;
                                                                    }
                                                                    CloseableKt.closeFinally(cursorRawQuery4, th6);
                                                                    if (triple != null) {
                                                                        if (!Intrinsics.areEqual(triple.getFirst(), "CUSTOMER_CREDIT_COLLECTION") || !Intrinsics.areEqual(triple.getSecond(), str2) || triple.getThird() == null) {
                                                                            throw new IllegalArgumentException("\u062a\u0639\u0627\u0631\u0636 \u0641\u064a \u0633\u062c\u0644 \u0639\u0645\u0644\u064a\u0629 \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u0623\u0648 \u0625\u0639\u0627\u062f\u0629 \u0627\u0633\u062a\u062e\u062f\u0627\u0645 \u0645\u0639\u0631\u0651\u0641\u0647\u0627".toString());
                                                                        }
                                                                        ContentValues contentValues = new ContentValues();
                                                                        contentValues.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                                                        contentValues.put("applied_at", timestamp());
                                                                        contentValues.putNull("rejection_reason");
                                                                        Unit unit = Unit.INSTANCE;
                                                                        writableDatabase.update("sync_inbox", contentValues, "operation_id=? AND status='RECEIVED'", new String[]{operationId});
                                                                        writableDatabase.setTransactionSuccessful();
                                                                        applyResult = new ApplyResult(operationId, true, null, 4, null);
                                                                    } else {
                                                                        Cursor cursorRawQuery5 = writableDatabase.rawQuery("SELECT status,sale_type,total_usd_cents,customer_id,invoice_number,\n                          exchange_rate_syp_per_usd_milli\n                   FROM sales_invoices WHERE id=?", new String[]{string11});
                                                                        try {
                                                                            Cursor cursor5 = cursorRawQuery5;
                                                                            if (!cursor5.moveToFirst()) {
                                                                                try {
                                                                                    throw new IllegalArgumentException("\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u062f\u064a\u0646 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629 \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a".toString());
                                                                                } catch (Throwable th17) {
                                                                                    th = th17;
                                                                                }
                                                                            } else {
                                                                                String[] strArr = {cursor5.getString(0), cursor5.getString(1), String.valueOf(cursor5.getLong(2)), cursor5.isNull(3) ? "" : cursor5.getString(3), cursor5.getString(4), String.valueOf(cursor5.getLong(5))};
                                                                                CloseableKt.closeFinally(cursorRawQuery5, null);
                                                                                writableDatabase = "POSTED";
                                                                                if (!Intrinsics.areEqual(strArr[0], "POSTED") && !Intrinsics.areEqual(strArr[0], "PARTIALLY_PAID")) {
                                                                                    throw new IllegalArgumentException("\u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0644\u064a\u0633\u062a \u062f\u064a\u0646\u0627\u064b \u0645\u0639\u062a\u0645\u062f\u0627\u064b \u0642\u0627\u0628\u0644\u0627\u064b \u0644\u0644\u062a\u062d\u0635\u064a\u0644".toString());
                                                                                }
                                                                                if (Intrinsics.areEqual(strArr[1], "CREDIT")) {
                                                                                    String str3 = strArr[3];
                                                                                    Intrinsics.checkNotNullExpressionValue(str3, "get(...)");
                                                                                    if (!StringsKt.isBlank(str3)) {
                                                                                        String str4 = strArr[2];
                                                                                        Intrinsics.checkNotNullExpressionValue(str4, "get(...)");
                                                                                        long j2 = Long.parseLong(str4);
                                                                                        String str5 = strArr[5];
                                                                                        Intrinsics.checkNotNullExpressionValue(str5, "get(...)");
                                                                                        long j3 = Long.parseLong(str5);
                                                                                        if (j3 <= 0) {
                                                                                            throw new IllegalArgumentException("\u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0623\u0635\u0644\u064a\u0629 \u0644\u0627 \u062a\u062d\u062a\u0648\u064a \u0633\u0639\u0631 \u0635\u0631\u0641 \u0645\u0648\u062b\u0648\u0642\u0627\u064b".toString());
                                                                                        }
                                                                                        String string13 = jSONObject.getString("cash_account_id");
                                                                                        String string14 = jSONObject.getString("cash_account_type");
                                                                                        String string15 = jSONObject.getString("cash_account_currency_code");
                                                                                        if (!SetsKt.setOf((Object[]) new String[]{"CASH_BOX", "WALLET"}).contains(string14) || !Intrinsics.areEqual(string15, "SYP") || !Intrinsics.areEqual(jSONObject.optString("currency_code"), "SYP")) {
                                                                                            throw new IllegalArgumentException("\u0646\u0648\u0639 \u0623\u0648 \u0639\u0645\u0644\u0629 \u062d\u0633\u0627\u0628 \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u0627\u0644\u0648\u0627\u0631\u062f \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u064a\u0646".toString());
                                                                                        }
                                                                                        Cursor cursorRawQuery6 = writableDatabase.rawQuery("SELECT m.primary_cash_account_id,a.account_type,a.currency_code\n                   FROM sync_cash_account_mappings m\n                   JOIN cash_accounts a ON a.id=m.primary_cash_account_id\n                   WHERE m.device_id=? AND m.remote_cash_account_id=? AND m.is_active=1\n                     AND a.is_active=1", new String[]{inboxCommand.getOriginDeviceId(), string13});
                                                                                        try {
                                                                                            Cursor cursor6 = cursorRawQuery6;
                                                                                            try {
                                                                                                if (!cursor6.moveToFirst()) {
                                                                                                    throw new IllegalArgumentException("\u062d\u0633\u0627\u0628 \u0642\u0628\u0636 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u063a\u064a\u0631 \u0645\u0631\u0628\u0648\u0637 \u0623\u0648 \u0645\u0648\u0642\u0648\u0641".toString());
                                                                                                }
                                                                                                if (!Intrinsics.areEqual(cursor6.getString(1), string14) || !Intrinsics.areEqual(cursor6.getString(2), string15)) {
                                                                                                    throw new IllegalArgumentException("\u0646\u0648\u0639 \u0623\u0648 \u0639\u0645\u0644\u0629 \u062d\u0633\u0627\u0628 \u0627\u0644\u0642\u0628\u0636 \u0627\u0644\u0645\u0631\u062a\u0628\u0637 \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0623\u0635\u0644\u064a".toString());
                                                                                                }
                                                                                                String string16 = cursor6.getString(0);
                                                                                                CloseableKt.closeFinally(cursorRawQuery6, null);
                                                                                                String string17 = jSONObject.getString("occurred_at");
                                                                                                String string18 = jSONObject.getString("operating_date");
                                                                                                Intrinsics.checkNotNull(string17);
                                                                                                if (new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(string17)) {
                                                                                                    Intrinsics.checkNotNull(string18);
                                                                                                    if (new Regex("\\d{4}-\\d{2}-\\d{2}").matches(string18)) {
                                                                                                        try {
                                                                                                            long j4 = jSONObject.getLong("amount_minor");
                                                                                                            long j5 = jSONObject.getLong("collection_rate_milli");
                                                                                                            writableDatabase = string16;
                                                                                                            long j6 = jSONObject.getLong("effective_rate_milli");
                                                                                                            long j7 = jSONObject.getLong("usd_reference_reduction_cents");
                                                                                                            if (j4 <= 0 || j5 <= 0 || j6 != Math.max(j5, j3) || j7 <= 0) {
                                                                                                                throw new IllegalArgumentException("\u0645\u0628\u0644\u063a \u0623\u0648 \u0633\u0639\u0631 \u0635\u0631\u0641 \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                                                                                            }
                                                                                                            if (jSONObject.isNull("note")) {
                                                                                                                strTake = null;
                                                                                                            } else {
                                                                                                                String string19 = jSONObject.getString("note");
                                                                                                                Intrinsics.checkNotNullExpressionValue(string19, "getString(...)");
                                                                                                                strTake = StringsKt.take(string19, 500);
                                                                                                            }
                                                                                                            if (Intrinsics.areEqual(string14, "CASH_BOX")) {
                                                                                                                try {
                                                                                                                    Cursor cursorRawQuery7 = writableDatabase.rawQuery("SELECT primary_cash_account_id FROM daily_cash_sessions WHERE operating_date=?", new String[]{string18});
                                                                                                                    try {
                                                                                                                        Cursor cursor7 = cursorRawQuery7;
                                                                                                                        if (cursor7.moveToFirst()) {
                                                                                                                            string3 = cursor7.getString(0);
                                                                                                                            th8 = null;
                                                                                                                        } else {
                                                                                                                            th8 = null;
                                                                                                                            string3 = null;
                                                                                                                        }
                                                                                                                        CloseableKt.closeFinally(cursorRawQuery7, th8);
                                                                                                                        if (!Intrinsics.areEqual(string3, (Object) writableDatabase)) {
                                                                                                                            throw new IllegalArgumentException("\u062d\u0633\u0627\u0628 \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u0627\u0644\u0646\u0642\u062f\u064a \u0627\u0644\u0645\u0631\u062a\u0628\u0637 \u0644\u064a\u0633 \u062f\u0631\u062c \u0627\u0644\u064a\u0648\u0645 \u0627\u0644\u0631\u0626\u064a\u0633\u064a \u0644\u062a\u0627\u0631\u064a\u062e \u0627\u0644\u062d\u0631\u0643\u0629".toString());
                                                                                                                        }
                                                                                                                    } catch (Throwable th18) {
                                                                                                                        try {
                                                                                                                            throw th18;
                                                                                                                        } catch (Throwable th19) {
                                                                                                                            CloseableKt.closeFinally(cursorRawQuery7, th18);
                                                                                                                            throw th19;
                                                                                                                        }
                                                                                                                    }
                                                                                                                } catch (Throwable th20) {
                                                                                                                    th4 = th20;
                                                                                                                    r12 = writableDatabase;
                                                                                                                    r12.endTransaction();
                                                                                                                    throw th4;
                                                                                                                }
                                                                                                            } else {
                                                                                                                j3 = j3;
                                                                                                            }
                                                                                                            Cursor cursorRawQuery8 = writableDatabase.rawQuery("SELECT amount_minor,currency_code,exchange_rate_syp_per_usd_milli\n                   FROM invoice_payments WHERE invoice_type='SALE' AND invoice_id=?", new String[]{string11});
                                                                                                            try {
                                                                                                                Cursor cursor8 = cursorRawQuery8;
                                                                                                                long jM = 0;
                                                                                                                while (cursor8.moveToNext()) {
                                                                                                                    String str6 = string18;
                                                                                                                    long j8 = j5;
                                                                                                                    try {
                                                                                                                        long jSypToUsdCents = cursor8.getLong(0);
                                                                                                                        if (cursor8.isNull(2)) {
                                                                                                                            j = j3;
                                                                                                                            i = 1;
                                                                                                                        } else {
                                                                                                                            j = cursor8.getLong(2);
                                                                                                                            i = 1;
                                                                                                                        }
                                                                                                                        String string20 = cursor8.getString(i);
                                                                                                                        if (!Intrinsics.areEqual(string20, "USD")) {
                                                                                                                            if (!Intrinsics.areEqual(string20, "SYP")) {
                                                                                                                                throw new IllegalArgumentException("\u0639\u0645\u0644\u0629 \u062f\u0641\u0639\u0629 \u0633\u0627\u0628\u0642\u0629 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u0629");
                                                                                                                            }
                                                                                                                            if (j <= 0) {
                                                                                                                                throw new IllegalArgumentException("\u062f\u0641\u0639\u0629 \u0633\u0627\u0628\u0642\u0629 \u0628\u0644\u0627 \u0633\u0639\u0631 \u0635\u0631\u0641 \u0645\u0648\u062b\u0648\u0642".toString());
                                                                                                                            }
                                                                                                                            jSypToUsdCents = CurrencyConverter.INSTANCE.sypToUsdCents(jSypToUsdCents, j);
                                                                                                                        }
                                                                                                                        jM = MainActivity$$ExternalSyntheticBackport0.m(jM, jSypToUsdCents);
                                                                                                                        string18 = str6;
                                                                                                                        j5 = j8;
                                                                                                                        strTake = strTake;
                                                                                                                        j6 = j6;
                                                                                                                    } catch (Throwable th21) {
                                                                                                                        th9 = th21;
                                                                                                                        try {
                                                                                                                            throw th9;
                                                                                                                        } catch (Throwable th22) {
                                                                                                                            CloseableKt.closeFinally(cursorRawQuery8, th9);
                                                                                                                            throw th22;
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                                String str7 = strTake;
                                                                                                                String str8 = string18;
                                                                                                                long j9 = j6;
                                                                                                                long j10 = j5;
                                                                                                                Unit unit2 = Unit.INSTANCE;
                                                                                                                CloseableKt.closeFinally(cursorRawQuery8, null);
                                                                                                                Cursor cursorRawQuery9 = writableDatabase.rawQuery("SELECT COALESCE(SUM(credit_reduction_usd_cents),0) FROM returns\n                   WHERE original_invoice_type='SALE' AND original_invoice_id=? AND status='POSTED'", new String[]{string11});
                                                                                                                try {
                                                                                                                    Cursor cursor9 = cursorRawQuery9;
                                                                                                                    cursor9.moveToFirst();
                                                                                                                    long j11 = cursor9.getLong(0);
                                                                                                                    CloseableKt.closeFinally(cursorRawQuery9, null);
                                                                                                                    long j12 = j2 - jM;
                                                                                                                    long jCoerceAtLeast = RangesKt.coerceAtLeast(j12 - j11, 0L);
                                                                                                                    if (jCoerceAtLeast <= 0) {
                                                                                                                        throw new IllegalArgumentException("\u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0645\u0633\u062f\u062f\u0629 \u0628\u0627\u0644\u0643\u0627\u0645\u0644".toString());
                                                                                                                    }
                                                                                                                    if (j4 > CurrencyConverter.INSTANCE.usdCentsToSyp(jCoerceAtLeast, j9)) {
                                                                                                                        throw new IllegalArgumentException("\u0645\u0628\u0644\u063a \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u064a\u062a\u062c\u0627\u0648\u0632 \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0645\u062a\u0628\u0642\u064a".toString());
                                                                                                                    }
                                                                                                                    long jSypToUsdCents2 = CurrencyConverter.INSTANCE.sypToUsdCents(j4, j9);
                                                                                                                    if (jSypToUsdCents2 != j7 || jSypToUsdCents2 > jCoerceAtLeast) {
                                                                                                                        throw new IllegalArgumentException("\u062a\u062e\u0641\u064a\u0636 \u0627\u0644\u062f\u064a\u0646 \u0627\u0644\u0645\u0631\u062c\u0639\u064a \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u0627\u0644\u0645\u0628\u0644\u063a \u0648\u0633\u0639\u0631 \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u0627\u0644\u0623\u0635\u0644\u064a".toString());
                                                                                                                    }
                                                                                                                    String str9 = "sync-payment-" + operationId;
                                                                                                                    String str10 = "sync-cash-" + operationId;
                                                                                                                    ?? contentValues2 = new ContentValues();
                                                                                                                    contentValues2.put("id", str10);
                                                                                                                    contentValues2.put("account_id", writableDatabase);
                                                                                                                    contentValues2.put("direction", "IN");
                                                                                                                    contentValues2.put("amount_minor", Long.valueOf(j4));
                                                                                                                    contentValues2.put("currency_code", "SYP");
                                                                                                                    contentValues2.put("exchange_rate_syp_per_usd_milli", Long.valueOf(j10));
                                                                                                                    contentValues2.put("transaction_type", "CUSTOMER_CREDIT_COLLECTION");
                                                                                                                    contentValues2.put("source_type", "CUSTOMER_CREDIT_COLLECTION");
                                                                                                                    contentValues2.put("source_id", string11);
                                                                                                                    contentValues2.put("operation_id", operationId);
                                                                                                                    if (str7 == null) {
                                                                                                                        str = "\u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0646 \u0627\u0644\u0632\u0628\u0648\u0646 \u0639\u0646 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 " + strArr[4];
                                                                                                                    } else {
                                                                                                                        str = str7;
                                                                                                                    }
                                                                                                                    contentValues2.put("description", str);
                                                                                                                    contentValues2.put("created_by", inboxCommand.getPrimaryUserId());
                                                                                                                    contentValues2.put("created_at", string17);
                                                                                                                    contentValues2.put("operating_date", str8);
                                                                                                                    Unit unit3 = Unit.INSTANCE;
                                                                                                                    writableDatabase.insertOrThrow("cash_transactions", null, contentValues2);
                                                                                                                    ContentValues contentValues3 = new ContentValues();
                                                                                                                    contentValues3.put("id", str9);
                                                                                                                    contentValues3.put("invoice_type", "SALE");
                                                                                                                    contentValues3.put("invoice_id", string11);
                                                                                                                    contentValues3.put("cash_transaction_id", str10);
                                                                                                                    contentValues3.put("amount_minor", Long.valueOf(j4));
                                                                                                                    contentValues3.put("currency_code", "SYP");
                                                                                                                    contentValues3.put("exchange_rate_syp_per_usd_milli", Long.valueOf(j9));
                                                                                                                    contentValues3.put("created_at", string17);
                                                                                                                    Unit unit4 = Unit.INSTANCE;
                                                                                                                    writableDatabase.insertOrThrow("invoice_payments", null, contentValues3);
                                                                                                                    String str11 = RangesKt.coerceAtLeast((j12 - jSypToUsdCents2) - j11, 0L) == 0 ? "PAID" : "PARTIALLY_PAID";
                                                                                                                    ContentValues contentValues4 = new ContentValues();
                                                                                                                    contentValues4.put(NotificationCompat.CATEGORY_STATUS, str11);
                                                                                                                    contentValues4.put("updated_at", timestamp());
                                                                                                                    Unit unit5 = Unit.INSTANCE;
                                                                                                                    if (writableDatabase.update("sales_invoices", contentValues4, "id=? AND status IN ('POSTED','PARTIALLY_PAID')", new String[]{string11}) != 1) {
                                                                                                                        throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062d\u062f\u064a\u062b \u062d\u0627\u0644\u0629 \u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u062f\u064a\u0646 \u0628\u0639\u062f \u0627\u0644\u062a\u062d\u0635\u064a\u0644".toString());
                                                                                                                    }
                                                                                                                    ContentValues contentValues5 = new ContentValues();
                                                                                                                    contentValues5.put("id", UUID.randomUUID().toString());
                                                                                                                    contentValues5.put("user_id", inboxCommand.getPrimaryUserId());
                                                                                                                    contentValues5.put("action", "CUSTOMER_CREDIT_COLLECTION");
                                                                                                                    contentValues5.put("entity_type", "CUSTOMER_CREDIT_INVOICE");
                                                                                                                    contentValues5.put("entity_id", string11);
                                                                                                                    contentValues5.put("before_json", new JSONObject().put("outstanding_usd_cents", jCoerceAtLeast).toString());
                                                                                                                    contentValues5.put("after_json", new JSONObject().put("payment_id", str9).put("cash_transaction_id", str10).put("amount_syp_minor", j4).put("collection_rate_milli", j10).put("effective_rate_milli", j9).put("usd_reference_reduction_cents", jSypToUsdCents2).toString());
                                                                                                                    contentValues5.put("created_at", timestamp());
                                                                                                                    Unit unit6 = Unit.INSTANCE;
                                                                                                                    writableDatabase.insertOrThrow("audit_log", null, contentValues5);
                                                                                                                    ContentValues contentValues6 = new ContentValues();
                                                                                                                    contentValues6.put("operation_id", operationId);
                                                                                                                    contentValues6.put("operation_type", "CUSTOMER_CREDIT_COLLECTION");
                                                                                                                    contentValues6.put("payload_hash", str2);
                                                                                                                    contentValues6.put(NotificationCompat.CATEGORY_STATUS, "COMPLETED");
                                                                                                                    contentValues6.put("result_reference", str9);
                                                                                                                    contentValues6.put("created_at", timestamp());
                                                                                                                    contentValues6.put("completed_at", timestamp());
                                                                                                                    Unit unit7 = Unit.INSTANCE;
                                                                                                                    writableDatabase.insertOrThrow("operation_registry", null, contentValues6);
                                                                                                                    ContentValues contentValues7 = new ContentValues();
                                                                                                                    contentValues7.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                                                                                                    contentValues7.put("applied_at", timestamp());
                                                                                                                    contentValues7.putNull("rejection_reason");
                                                                                                                    Unit unit8 = Unit.INSTANCE;
                                                                                                                    if (writableDatabase.update("sync_inbox", contentValues7, "operation_id=? AND status='RECEIVED'", new String[]{operationId}) != 1) {
                                                                                                                        throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062b\u0628\u064a\u062a \u062d\u0627\u0644\u0629 \u062a\u0637\u0628\u064a\u0642 \u0627\u0644\u062a\u062d\u0635\u064a\u0644".toString());
                                                                                                                    }
                                                                                                                    writableDatabase.setTransactionSuccessful();
                                                                                                                    applyResult = new ApplyResult(operationId, true, null, 4, null);
                                                                                                                    writableDatabase.endTransaction();
                                                                                                                } catch (Throwable th23) {
                                                                                                                    writableDatabase = writableDatabase;
                                                                                                                    try {
                                                                                                                        throw th23;
                                                                                                                    } catch (Throwable th24) {
                                                                                                                        CloseableKt.closeFinally(cursorRawQuery9, th23);
                                                                                                                        throw th24;
                                                                                                                    }
                                                                                                                }
                                                                                                            } catch (Throwable th25) {
                                                                                                                th9 = th25;
                                                                                                            }
                                                                                                        } catch (Throwable th26) {
                                                                                                            th = th26;
                                                                                                            writableDatabase = writableDatabase;
                                                                                                            th4 = th;
                                                                                                            r12 = writableDatabase;
                                                                                                            r12.endTransaction();
                                                                                                            throw th4;
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                                throw new IllegalArgumentException("\u062a\u0627\u0631\u064a\u062e \u0623\u0648 \u0648\u0642\u062a \u062d\u0631\u0643\u0629 \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u0627\u0644\u0623\u0635\u0644\u064a \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                                                                            } catch (Throwable th27) {
                                                                                                th = th27;
                                                                                                th7 = th;
                                                                                                throw th7;
                                                                                            }
                                                                                        } catch (Throwable th28) {
                                                                                            th = th28;
                                                                                            writableDatabase = writableDatabase;
                                                                                        }
                                                                                        th7 = th;
                                                                                        try {
                                                                                            throw th7;
                                                                                        } catch (Throwable th29) {
                                                                                            CloseableKt.closeFinally(cursorRawQuery6, th7);
                                                                                            throw th29;
                                                                                        }
                                                                                    }
                                                                                }
                                                                                throw new IllegalArgumentException("\u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u0627\u0644\u0648\u0627\u0631\u062f \u0644\u0627 \u064a\u0631\u062a\u0628\u0637 \u0628\u0641\u0627\u062a\u0648\u0631\u0629 \u0622\u062c\u0644\u0629 \u0648\u0632\u0628\u0648\u0646 \u0635\u0627\u0644\u062d".toString());
                                                                            }
                                                                        } catch (Throwable th30) {
                                                                            th = th30;
                                                                        }
                                                                        Throwable th31 = th;
                                                                        try {
                                                                            throw th31;
                                                                        } catch (Throwable th32) {
                                                                            CloseableKt.closeFinally(cursorRawQuery5, th31);
                                                                            throw th32;
                                                                        }
                                                                    }
                                                                } catch (Throwable th33) {
                                                                    th5 = th33;
                                                                }
                                                            }
                                                        }
                                                        throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u062f\u0641\u0639\u0629 \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u0643\u064a\u0627\u0646 \u0627\u0644\u0638\u0631\u0641".toString());
                                                    }
                                                    writableDatabase.endTransaction();
                                                } catch (Throwable th34) {
                                                    th = th34;
                                                    obj = obj;
                                                }
                                            } catch (Throwable th35) {
                                                th4 = th35;
                                            }
                                        }
                                    } catch (Throwable th36) {
                                        th = th36;
                                    }
                                    Throwable th37 = th;
                                    try {
                                        throw th37;
                                    } catch (Throwable th38) {
                                        CloseableKt.closeFinally(cursorRawQuery3, th37);
                                        throw th38;
                                    }
                                } catch (Throwable th39) {
                                    th = th39;
                                    th4 = th;
                                    r12 = writableDatabase;
                                    r12.endTransaction();
                                    throw th4;
                                }
                            } catch (Throwable th40) {
                                th = th40;
                            }
                        } catch (Throwable th41) {
                            th3 = th41;
                        }
                    } catch (Throwable th42) {
                        th2 = th42;
                    }
                    th = th;
                } catch (Throwable th43) {
                    th = th43;
                    th = th;
                }
            } catch (Throwable th44) {
                th = th44;
                obj = obj;
            }
            throw th;
        }
        return applyResult;
    }

    public final ApplyResult applyReceivedSupplierRollover(String operationId) {
        String strTake;
        ApplyResult applyResult;
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        synchronized (APPLY_LOCK) {
            if (StringsKt.isBlank(operationId)) {
                throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0645\u0637\u0644\u0648\u0628".toString());
            }
            SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
            Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
            SyncDeviceIdentity.Companion companion = SyncDeviceIdentity.INSTANCE;
            Context appContext = this.appContext;
            Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
            String orCreate = companion.getOrCreate(appContext);
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", (String[]) null);
            try {
                Cursor cursor = cursorRawQuery;
                String string = cursor.moveToFirst() ? cursor.getString(0) : null;
                CloseableKt.closeFinally(cursorRawQuery, null);
                Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='authority_device_id'", (String[]) null);
                try {
                    Cursor cursor2 = cursorRawQuery2;
                    String string2 = cursor2.moveToFirst() ? cursor2.getString(0) : null;
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    if (!Intrinsics.areEqual(string, "PRIMARY") || !Intrinsics.areEqual(string2, orCreate)) {
                        throw new IllegalArgumentException("\u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0627\u0644\u0648\u0627\u0631\u062f \u0645\u0633\u0645\u0648\u062d \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0641\u0642\u0637".toString());
                    }
                    writableDatabase.beginTransaction();
                    try {
                        Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT i.origin_device_id,i.entity_type,i.entity_id,i.payload_json,i.status,m.primary_user_id,i.payload_sha256\n                   FROM sync_inbox i JOIN sync_actor_mappings m\n                     ON m.device_id=i.origin_device_id\n                    AND m.remote_actor_user_id=i.actor_user_id AND m.is_active=1\n                   JOIN users u ON u.id=m.primary_user_id JOIN sync_devices d ON d.device_id=i.origin_device_id\n                   WHERE i.operation_id=? AND d.device_role='SECONDARY' AND d.status='ACTIVE'\n                     AND u.is_active=1 AND u.must_change_password=0 LIMIT 1", new String[]{operationId});
                        try {
                            Cursor cursor3 = cursorRawQuery3;
                            if (!cursor3.moveToFirst()) {
                                throw new IllegalArgumentException("\u0623\u0645\u0631 \u062a\u062e\u0635\u064a\u0635 \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0623\u0648 \u0627\u0644\u062c\u0647\u0627\u0632/\u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0645\u0641\u0639\u0644 \u0623\u0648 \u063a\u064a\u0631 \u0645\u0631\u0628\u0648\u0637".toString());
                            }
                            String[] strArr = new String[7];
                            for (int i = 0; i < 7; i++) {
                                strArr[i] = cursor3.getString(i);
                            }
                            CloseableKt.closeFinally(cursorRawQuery3, null);
                            if (!Intrinsics.areEqual(strArr[4], "APPLIED")) {
                                if (!Intrinsics.areEqual(strArr[4], "RECEIVED") || !Intrinsics.areEqual(strArr[1], "DAILY_SUPPLIER_ROLLOVER")) {
                                    throw new IllegalArgumentException("\u0646\u0648\u0639 \u0623\u0648 \u062d\u0627\u0644\u0629 \u0623\u0645\u0631 \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                }
                                String str = strArr[3];
                                Intrinsics.checkNotNull(str);
                                JSONObject jSONObject = new JSONObject(str);
                                if (!Intrinsics.areEqual(jSONObject.optString("business_operation"), "SET_SUPPLIER_ROLLOVER")) {
                                    throw new IllegalArgumentException("\u0646\u0648\u0639 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0627\u0644\u0648\u0627\u0631\u062f\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                }
                                String str2 = strArr[5];
                                Intrinsics.checkNotNull(str2);
                                PermissionPolicy.INSTANCE.require(writableDatabase, str2, PermissionPolicy.DAILY_CASH_MANAGE);
                                Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT operation_type,payload_hash,result_reference FROM operation_registry WHERE operation_id=?", new String[]{operationId});
                                try {
                                    Cursor cursor4 = cursorRawQuery4;
                                    String[] strArr2 = cursor4.moveToFirst() ? new String[]{cursor4.getString(0), cursor4.getString(1), cursor4.isNull(2) ? "" : cursor4.getString(2)} : null;
                                    CloseableKt.closeFinally(cursorRawQuery4, null);
                                    if (strArr2 != null) {
                                        if (Intrinsics.areEqual(strArr2[0], "SET_SUPPLIER_ROLLOVER") && Intrinsics.areEqual(strArr2[1], strArr[6])) {
                                            String str3 = strArr2[2];
                                            Intrinsics.checkNotNullExpressionValue(str3, "get(...)");
                                            if (!StringsKt.isBlank(str3)) {
                                                ContentValues contentValues = new ContentValues();
                                                contentValues.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                                contentValues.put("applied_at", timestamp());
                                                contentValues.putNull("rejection_reason");
                                                Unit unit = Unit.INSTANCE;
                                                writableDatabase.update("sync_inbox", contentValues, "operation_id=? AND status='RECEIVED'", new String[]{operationId});
                                                writableDatabase.setTransactionSuccessful();
                                                applyResult = new ApplyResult(operationId, true, null, 4, null);
                                            }
                                        }
                                        throw new IllegalArgumentException("\u062a\u0639\u0627\u0631\u0636 \u0641\u064a \u0633\u062c\u0644 \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631".toString());
                                    }
                                    String string3 = jSONObject.getString("cash_account_id");
                                    if (!Intrinsics.areEqual(jSONObject.getString("cash_account_type"), "CASH_BOX") || !Intrinsics.areEqual(jSONObject.getString("cash_account_currency_code"), "SYP")) {
                                        throw new IllegalArgumentException("\u062d\u0633\u0627\u0628 \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u064a\u062c\u0628 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0635\u0646\u062f\u0648\u0642\u0627\u064b \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629".toString());
                                    }
                                    Cursor cursorRawQuery5 = writableDatabase.rawQuery("SELECT m.primary_cash_account_id,a.account_type,a.currency_code,a.is_active\n                   FROM sync_cash_account_mappings m JOIN cash_accounts a ON a.id=m.primary_cash_account_id\n                   WHERE m.device_id=? AND m.remote_cash_account_id=? AND m.is_active=1", new String[]{strArr[0], string3});
                                    try {
                                        Cursor cursor5 = cursorRawQuery5;
                                        if (!cursor5.moveToFirst() || cursor5.getInt(3) != 1) {
                                            throw new IllegalArgumentException("\u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u063a\u064a\u0631 \u0645\u0631\u0628\u0648\u0637 \u0623\u0648 \u0645\u0648\u0642\u0648\u0641".toString());
                                        }
                                        if (!Intrinsics.areEqual(cursor5.getString(1), "CASH_BOX") || !Intrinsics.areEqual(cursor5.getString(2), "SYP")) {
                                            throw new IllegalArgumentException("\u0627\u0644\u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0645\u0631\u062a\u0628\u0637 \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u0646\u0648\u0639 \u0623\u0648 \u0639\u0645\u0644\u0629 \u0627\u0644\u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0623\u0635\u0644\u064a".toString());
                                        }
                                        String string4 = cursor5.getString(0);
                                        CloseableKt.closeFinally(cursorRawQuery5, null);
                                        String string5 = jSONObject.getString("occurred_at");
                                        String string6 = jSONObject.getString("operating_date");
                                        Intrinsics.checkNotNull(string5);
                                        if (new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(string5)) {
                                            Intrinsics.checkNotNull(string6);
                                            if (new Regex("\\d{4}-\\d{2}-\\d{2}").matches(string6)) {
                                                long j = jSONObject.getLong("amount_minor");
                                                if (j < 0) {
                                                    throw new IllegalArgumentException("\u0645\u0628\u0644\u063a \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                                }
                                                if (jSONObject.isNull("note")) {
                                                    strTake = null;
                                                } else {
                                                    String string7 = jSONObject.getString("note");
                                                    Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
                                                    strTake = StringsKt.take(string7, 500);
                                                }
                                                Context appContext2 = this.appContext;
                                                Intrinsics.checkNotNullExpressionValue(appContext2, "appContext");
                                                DailyBalanceRepository dailyBalanceRepository = new DailyBalanceRepository(appContext2);
                                                Intrinsics.checkNotNull(string4);
                                                dailyBalanceRepository.setSupplierRollover(str2, string4, j, strTake, operationId, string5, string6, true);
                                                ContentValues contentValues2 = new ContentValues();
                                                contentValues2.put("operation_id", operationId);
                                                contentValues2.put("operation_type", "SET_SUPPLIER_ROLLOVER");
                                                contentValues2.put("payload_hash", strArr[6]);
                                                contentValues2.put(NotificationCompat.CATEGORY_STATUS, "COMPLETED");
                                                contentValues2.put("result_reference", string6);
                                                contentValues2.put("created_at", timestamp());
                                                contentValues2.put("completed_at", timestamp());
                                                Unit unit2 = Unit.INSTANCE;
                                                writableDatabase.insertOrThrow("operation_registry", null, contentValues2);
                                                ContentValues contentValues3 = new ContentValues();
                                                contentValues3.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                                contentValues3.put("applied_at", timestamp());
                                                contentValues3.putNull("rejection_reason");
                                                Unit unit3 = Unit.INSTANCE;
                                                if (writableDatabase.update("sync_inbox", contentValues3, "operation_id=? AND status='RECEIVED'", new String[]{operationId}) != 1) {
                                                    throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062b\u0628\u064a\u062a \u062d\u0627\u0644\u0629 \u062a\u062e\u0635\u064a\u0635 \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631".toString());
                                                }
                                                writableDatabase.setTransactionSuccessful();
                                                applyResult = new ApplyResult(operationId, true, null, 4, null);
                                            }
                                        }
                                        throw new IllegalArgumentException("\u062a\u0627\u0631\u064a\u062e \u062a\u062e\u0635\u064a\u0635 \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                    } catch (Throwable th) {
                                        try {
                                            throw th;
                                        } catch (Throwable th2) {
                                            CloseableKt.closeFinally(cursorRawQuery5, th);
                                            throw th2;
                                        }
                                    }
                                } catch (Throwable th3) {
                                    try {
                                        throw th3;
                                    } catch (Throwable th4) {
                                        CloseableKt.closeFinally(cursorRawQuery4, th3);
                                        throw th4;
                                    }
                                }
                            }
                            writableDatabase.setTransactionSuccessful();
                            applyResult = new ApplyResult(operationId, true, null, 4, null);
                            writableDatabase.endTransaction();
                        } catch (Throwable th5) {
                            try {
                                throw th5;
                            } catch (Throwable th6) {
                                CloseableKt.closeFinally(cursorRawQuery3, th5);
                                throw th6;
                            }
                        }
                    } catch (Throwable th7) {
                        writableDatabase.endTransaction();
                        throw th7;
                    }
                } catch (Throwable th8) {
                    try {
                        throw th8;
                    } catch (Throwable th9) {
                        CloseableKt.closeFinally(cursorRawQuery2, th8);
                        throw th9;
                    }
                }
            } catch (Throwable th10) {
                try {
                    throw th10;
                } catch (Throwable th11) {
                    CloseableKt.closeFinally(cursorRawQuery, th10);
                    throw th11;
                }
            }
        }
        return applyResult;
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 14741. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
        */
    public final com.hazem.dailystore.data.SyncDomainApplier.ApplyResult applyReceivedServiceTransfer(java.lang.String r32) {
        /*
            Method dump skipped, instruction units count: 1474
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hazem.dailystore.data.SyncDomainApplier.applyReceivedServiceTransfer(java.lang.String):com.hazem.dailystore.data.SyncDomainApplier$ApplyResult");
    }

    /* JADX WARN: Code restructure failed: missing block: B:89:0x01e8, code lost:
    
        if (r19 == (r7 - r17)) goto L90;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final ApplyResult applyReceivedServiceTransferReceivable(String operationId) throws Throwable {
        Throwable th;
        String string;
        Throwable th2;
        String string2;
        String str;
        Throwable th3;
        String str2;
        Throwable th4;
        Pair pair;
        long j;
        String str3;
        String str4;
        String str5;
        ApplyResult applyResult;
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        Object obj = APPLY_LOCK;
        synchronized (obj) {
            try {
                try {
                    if (StringsKt.isBlank(operationId)) {
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                    SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
                    Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
                    SyncDeviceIdentity.Companion companion = SyncDeviceIdentity.INSTANCE;
                    Context appContext = this.appContext;
                    Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
                    String orCreate = companion.getOrCreate(appContext);
                    Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", (String[]) null);
                    try {
                        Cursor cursor = cursorRawQuery;
                        if (cursor.moveToFirst()) {
                            try {
                                string = cursor.getString(0);
                            } catch (Throwable th5) {
                                th = th5;
                                try {
                                    throw th;
                                } catch (Throwable th6) {
                                    CloseableKt.closeFinally(cursorRawQuery, th);
                                    throw th6;
                                }
                            }
                        } else {
                            string = null;
                        }
                        CloseableKt.closeFinally(cursorRawQuery, null);
                        Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='authority_device_id'", (String[]) null);
                        try {
                            Cursor cursor2 = cursorRawQuery2;
                            if (cursor2.moveToFirst()) {
                                try {
                                    string2 = cursor2.getString(0);
                                } catch (Throwable th7) {
                                    th2 = th7;
                                    try {
                                        throw th2;
                                    } catch (Throwable th8) {
                                        CloseableKt.closeFinally(cursorRawQuery2, th2);
                                        throw th8;
                                    }
                                }
                            } else {
                                string2 = null;
                            }
                            CloseableKt.closeFinally(cursorRawQuery2, null);
                            if (!Intrinsics.areEqual(string, "SECONDARY") || (str = string2) == null || StringsKt.isBlank(str) || Intrinsics.areEqual(string2, orCreate)) {
                                throw new IllegalArgumentException("\u0645\u0632\u0627\u0645\u0646\u0629 \u0630\u0645\u0645 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u0645\u062a\u0627\u062d\u0629 \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u0627\u0644\u0645\u0631\u062a\u0628\u0637 \u0641\u0642\u0637".toString());
                            }
                            Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT 1 FROM sync_devices WHERE device_id=? AND device_role='PRIMARY' AND status='ACTIVE'", new String[]{string2});
                            try {
                                boolean zMoveToFirst = cursorRawQuery3.moveToFirst();
                                CloseableKt.closeFinally(cursorRawQuery3, null);
                                if (!zMoveToFirst) {
                                    throw new IllegalArgumentException("\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0627\u0644\u0645\u0639\u062a\u0645\u062f \u063a\u064a\u0631 \u0646\u0634\u0637".toString());
                                }
                                writableDatabase.beginTransaction();
                                try {
                                    try {
                                        Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT i.origin_device_id,i.operation_type,i.entity_type,i.entity_id,\n                          i.payload_json,i.status,i.payload_sha256\n                   FROM sync_inbox i WHERE i.operation_id=?", new String[]{operationId});
                                        try {
                                            Cursor cursor3 = cursorRawQuery4;
                                            if (!cursor3.moveToFirst()) {
                                                try {
                                                    throw new IllegalArgumentException("\u062a\u062d\u062f\u064a\u062b \u0630\u0645\u0629 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
                                                } catch (Throwable th9) {
                                                    th = th9;
                                                }
                                            } else {
                                                String[] strArr = new String[7];
                                                for (int i = 0; i < 7; i++) {
                                                    try {
                                                        strArr[i] = cursor3.getString(i);
                                                    } catch (Throwable th10) {
                                                        th3 = th10;
                                                    }
                                                }
                                                CloseableKt.closeFinally(cursorRawQuery4, null);
                                                if (!Intrinsics.areEqual(strArr[0], string2) || !SetsKt.setOf((Object[]) new String[]{"CREATE", "UPDATE"}).contains(strArr[1]) || !Intrinsics.areEqual(strArr[2], "SERVICE_TRANSFER_RECEIVABLE")) {
                                                    throw new IllegalArgumentException("\u0645\u0635\u062f\u0631 \u0623\u0648 \u0646\u0648\u0639 \u062a\u062d\u062f\u064a\u062b \u0630\u0645\u0629 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                                }
                                                if (Intrinsics.areEqual(strArr[5], "APPLIED")) {
                                                    writableDatabase.setTransactionSuccessful();
                                                    applyResult = new ApplyResult(operationId, true, null, 4, null);
                                                    writableDatabase.endTransaction();
                                                } else {
                                                    if (!Intrinsics.areEqual(strArr[5], "RECEIVED")) {
                                                        throw new IllegalArgumentException("\u062d\u0627\u0644\u0629 \u062a\u062d\u062f\u064a\u062b \u0630\u0645\u0629 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u0644\u0627 \u062a\u0633\u0645\u062d \u0628\u0627\u0644\u062a\u0637\u0628\u064a\u0642".toString());
                                                    }
                                                    String str6 = strArr[4];
                                                    Intrinsics.checkNotNull(str6);
                                                    JSONObject jSONObject = new JSONObject(str6);
                                                    if (Intrinsics.areEqual(jSONObject.optString("business_operation"), "UPSERT_SERVICE_TRANSFER_RECEIVABLE") && Intrinsics.areEqual(jSONObject.optString("transfer_id"), strArr[3])) {
                                                        String strOptString = jSONObject.optString("customer_id");
                                                        Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
                                                        if (!StringsKt.isBlank(strOptString)) {
                                                            String strOptString2 = jSONObject.optString("customer_name");
                                                            Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
                                                            if (!StringsKt.isBlank(strOptString2) && SetsKt.setOf((Object[]) new String[]{ServiceTransferRepository.SYRIATEL_BALANCE, ServiceTransferRepository.SYRIATEL_CASH, "SHAM_CASH"}).contains(jSONObject.optString("service_type"))) {
                                                                long j2 = jSONObject.getLong("delivered_minor");
                                                                long jOptLong = jSONObject.optLong("delivered_milli_syp", DailyBalanceRepository$$ExternalSyntheticBackport0.m$1(j2, 1000L));
                                                                long j3 = jSONObject.getLong("charge_minor");
                                                                long j4 = jSONObject.getLong("paid_minor");
                                                                long j5 = jSONObject.getLong("outstanding_minor");
                                                                boolean zAreEqual = Intrinsics.areEqual(jSONObject.optString(NotificationCompat.CATEGORY_STATUS), "REVERSED");
                                                                if (j2 > 0 && jOptLong > 0 && jOptLong <= DailyBalanceRepository$$ExternalSyntheticBackport0.m$1(j3, 1000L) && j3 >= j2 && j4 >= 0 && j4 <= j3) {
                                                                    if (zAreEqual) {
                                                                        if (j4 == 0 && j5 == 0) {
                                                                            if (zAreEqual) {
                                                                                str2 = "REVERSED";
                                                                            } else if (j5 == 0) {
                                                                                str2 = "PAID";
                                                                            } else {
                                                                                str2 = j4 > 0 ? "PARTIALLY_PAID" : "OPEN";
                                                                            }
                                                                            if (!Intrinsics.areEqual(jSONObject.optString(NotificationCompat.CATEGORY_STATUS), str2)) {
                                                                                throw new IllegalArgumentException("\u062d\u0627\u0644\u0629 \u0630\u0645\u0629 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u0644\u0627 \u062a\u0637\u0627\u0628\u0642 \u0631\u0635\u064a\u062f\u0647\u0627".toString());
                                                                            }
                                                                            String strOptString3 = jSONObject.optString("created_at");
                                                                            String strOptString4 = jSONObject.optString("operating_date");
                                                                            Intrinsics.checkNotNull(strOptString3);
                                                                            if (new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(strOptString3)) {
                                                                                Intrinsics.checkNotNull(strOptString4);
                                                                                if (new Regex("\\d{4}-\\d{2}-\\d{2}").matches(strOptString4)) {
                                                                                    Cursor cursorRawQuery5 = writableDatabase.rawQuery("SELECT 1 FROM customers WHERE id=? AND is_active IN (0,1)", new String[]{jSONObject.getString("customer_id")});
                                                                                    try {
                                                                                        boolean zMoveToFirst2 = cursorRawQuery5.moveToFirst();
                                                                                        CloseableKt.closeFinally(cursorRawQuery5, null);
                                                                                        if (!zMoveToFirst2) {
                                                                                            throw new IllegalArgumentException("\u064a\u062c\u0628 \u0645\u0632\u0627\u0645\u0646\u0629 \u0645\u0644\u0641 \u0627\u0644\u0632\u0628\u0648\u0646 \u0642\u0628\u0644 \u0645\u0632\u0627\u0645\u0646\u0629 \u0630\u0645\u062a\u0647".toString());
                                                                                        }
                                                                                        Cursor cursorRawQuery6 = writableDatabase.rawQuery("SELECT paid_minor,charge_minor FROM service_transfer_receivable_mirrors WHERE transfer_id=?", new String[]{strArr[3]});
                                                                                        try {
                                                                                            Cursor cursor4 = cursorRawQuery6;
                                                                                            if (cursor4.moveToFirst()) {
                                                                                                pair = TuplesKt.to(Long.valueOf(cursor4.getLong(0)), Long.valueOf(cursor4.getLong(1)));
                                                                                                th4 = null;
                                                                                            } else {
                                                                                                th4 = null;
                                                                                                pair = null;
                                                                                            }
                                                                                            CloseableKt.closeFinally(cursorRawQuery6, th4);
                                                                                            if (jSONObject.has("payment_id") && !jSONObject.isNull("payment_id")) {
                                                                                                if (pair == null) {
                                                                                                    throw new IllegalArgumentException("\u0648\u0635\u0644 \u062a\u062d\u0635\u064a\u0644 \u0630\u0645\u0629 \u0642\u0628\u0644 \u0625\u0646\u0634\u0627\u0621 \u0633\u062c\u0644 \u0627\u0644\u0630\u0645\u0629".toString());
                                                                                                }
                                                                                                String string3 = jSONObject.getString("payment_id");
                                                                                                String string4 = jSONObject.getString("payment_operation_id");
                                                                                                long j6 = jSONObject.getLong("payment_amount_minor");
                                                                                                String string5 = jSONObject.getString("payment_cash_account_id");
                                                                                                str5 = strOptString4;
                                                                                                String string6 = jSONObject.getString("payment_operating_date");
                                                                                                str4 = strOptString3;
                                                                                                String string7 = jSONObject.getString("payment_created_at");
                                                                                                Intrinsics.checkNotNull(string3);
                                                                                                if (!StringsKt.isBlank(string3)) {
                                                                                                    Intrinsics.checkNotNull(string4);
                                                                                                    if (!StringsKt.isBlank(string4) && j6 > 0) {
                                                                                                        Intrinsics.checkNotNull(string5);
                                                                                                        if (!StringsKt.isBlank(string5)) {
                                                                                                            Intrinsics.checkNotNull(string6);
                                                                                                            str3 = str2;
                                                                                                            j = jOptLong;
                                                                                                            if (new Regex("\\d{4}-\\d{2}-\\d{2}").matches(string6)) {
                                                                                                                Intrinsics.checkNotNull(string7);
                                                                                                                if (new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(string7) && jSONObject.getLong("paid_minor") == ((Number) pair.getFirst()).longValue() + j6 && jSONObject.getLong("charge_minor") == ((Number) pair.getSecond()).longValue()) {
                                                                                                                    Cursor cursorRawQuery7 = writableDatabase.rawQuery("SELECT transfer_id,amount_minor,operation_id,cash_account_id FROM service_transfer_receivable_payment_mirrors WHERE payment_id=? OR operation_id=?", new String[]{string3, string4});
                                                                                                                    try {
                                                                                                                        Cursor cursor5 = cursorRawQuery7;
                                                                                                                        String[] strArr2 = cursor5.moveToFirst() ? new String[]{cursor5.getString(0), String.valueOf(cursor5.getLong(1)), cursor5.getString(2), cursor5.getString(3)} : null;
                                                                                                                        CloseableKt.closeFinally(cursorRawQuery7, null);
                                                                                                                        if (strArr2 != null) {
                                                                                                                            if (!Intrinsics.areEqual(strArr2[0], strArr[3]) || !Intrinsics.areEqual(strArr2[1], String.valueOf(j6)) || !Intrinsics.areEqual(strArr2[2], string4) || !Intrinsics.areEqual(strArr2[3], string5)) {
                                                                                                                                throw new IllegalArgumentException("\u062a\u0639\u0627\u0631\u0636 \u0641\u064a \u062f\u0641\u0639\u0629 \u0630\u0645\u0629 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0645\u0643\u0631\u0631\u0629".toString());
                                                                                                                            }
                                                                                                                            Unit unit = Unit.INSTANCE;
                                                                                                                        } else {
                                                                                                                            ContentValues contentValues = new ContentValues();
                                                                                                                            contentValues.put("payment_id", string3);
                                                                                                                            contentValues.put("transfer_id", strArr[3]);
                                                                                                                            contentValues.put("amount_minor", Long.valueOf(j6));
                                                                                                                            contentValues.put("operation_id", string4);
                                                                                                                            contentValues.put("cash_account_id", string5);
                                                                                                                            contentValues.put("operating_date", string6);
                                                                                                                            contentValues.put("created_at", string7);
                                                                                                                            if (jSONObject.isNull("payment_note")) {
                                                                                                                                contentValues.putNull("note");
                                                                                                                            } else {
                                                                                                                                contentValues.put("note", jSONObject.getString("payment_note"));
                                                                                                                            }
                                                                                                                            Unit unit2 = Unit.INSTANCE;
                                                                                                                            Long.valueOf(writableDatabase.insertOrThrow("service_transfer_receivable_payment_mirrors", null, contentValues));
                                                                                                                        }
                                                                                                                    } catch (Throwable th11) {
                                                                                                                        try {
                                                                                                                            throw th11;
                                                                                                                        } catch (Throwable th12) {
                                                                                                                            CloseableKt.closeFinally(cursorRawQuery7, th11);
                                                                                                                            throw th12;
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                                throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u062f\u0641\u0639\u0629 \u0630\u0645\u0629 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u0644\u0627 \u062a\u0637\u0627\u0628\u0642 \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0633\u0627\u0628\u0642".toString());
                                                                                            }
                                                                                            j = jOptLong;
                                                                                            str3 = str2;
                                                                                            j3 = j3;
                                                                                            str4 = strOptString3;
                                                                                            j2 = j2;
                                                                                            str5 = strOptString4;
                                                                                            if (pair != null) {
                                                                                                if (((Number) pair.getFirst()).longValue() != j4 || ((Number) pair.getSecond()).longValue() != j3) {
                                                                                                    throw new IllegalArgumentException("\u062a\u062d\u062f\u064a\u062b \u0627\u0644\u0630\u0645\u0629 \u0644\u0627 \u064a\u062a\u0636\u0645\u0646 \u062d\u0631\u0643\u0629 \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u0627\u0644\u0644\u0627\u0632\u0645\u0629 \u0644\u062a\u0641\u0633\u064a\u0631 \u062a\u063a\u064a\u0631 \u0627\u0644\u0631\u0635\u064a\u062f".toString());
                                                                                                }
                                                                                                Unit unit3 = Unit.INSTANCE;
                                                                                            } else if (j4 == 0) {
                                                                                                Unit unit4 = Unit.INSTANCE;
                                                                                            } else {
                                                                                                throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0627\u0633\u062a\u064a\u0631\u0627\u062f \u0630\u0645\u0629 \u0628\u0631\u0635\u064a\u062f \u0645\u0633\u062f\u062f \u062f\u0648\u0646 \u0633\u062c\u0644 \u062a\u062d\u0635\u064a\u0644 \u0633\u0627\u0628\u0642".toString());
                                                                                            }
                                                                                            ContentValues contentValues2 = new ContentValues();
                                                                                            contentValues2.put("transfer_id", strArr[3]);
                                                                                            contentValues2.put("customer_id", jSONObject.getString("customer_id"));
                                                                                            contentValues2.put("customer_name", jSONObject.getString("customer_name"));
                                                                                            contentValues2.put("service_type", jSONObject.getString("service_type"));
                                                                                            contentValues2.put("delivered_minor", Long.valueOf(j2));
                                                                                            contentValues2.put("delivered_milli_syp", Long.valueOf(j));
                                                                                            contentValues2.put("charge_minor", Long.valueOf(j3));
                                                                                            contentValues2.put("paid_minor", Long.valueOf(j4));
                                                                                            contentValues2.put("outstanding_minor", Long.valueOf(j5));
                                                                                            contentValues2.put(NotificationCompat.CATEGORY_STATUS, str3);
                                                                                            contentValues2.put("created_at", str4);
                                                                                            contentValues2.put("operating_date", str5);
                                                                                            if (jSONObject.isNull("reference")) {
                                                                                                contentValues2.putNull("reference");
                                                                                            } else {
                                                                                                contentValues2.put("reference", jSONObject.getString("reference"));
                                                                                            }
                                                                                            if (jSONObject.isNull("note")) {
                                                                                                contentValues2.putNull("note");
                                                                                            } else {
                                                                                                contentValues2.put("note", jSONObject.getString("note"));
                                                                                            }
                                                                                            contentValues2.put("updated_at", timestamp());
                                                                                            if (pair == null) {
                                                                                                Long.valueOf(writableDatabase.insertOrThrow("service_transfer_receivable_mirrors", null, contentValues2));
                                                                                            } else if (Integer.valueOf(writableDatabase.update("service_transfer_receivable_mirrors", contentValues2, "transfer_id=?", new String[]{strArr[3]})).intValue() != 1) {
                                                                                                throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062d\u062f\u064a\u062b \u0646\u0633\u062e\u0629 \u0630\u0645\u0629 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f".toString());
                                                                                            }
                                                                                            ContentValues contentValues3 = new ContentValues();
                                                                                            contentValues3.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                                                                            contentValues3.put("applied_at", timestamp());
                                                                                            contentValues3.putNull("rejection_reason");
                                                                                            Unit unit5 = Unit.INSTANCE;
                                                                                            if (writableDatabase.update("sync_inbox", contentValues3, "operation_id=? AND status='RECEIVED'", new String[]{operationId}) != 1) {
                                                                                                throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062b\u0628\u064a\u062a \u062a\u062d\u062f\u064a\u062b \u0630\u0645\u0629 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f".toString());
                                                                                            }
                                                                                            writableDatabase.setTransactionSuccessful();
                                                                                            applyResult = new ApplyResult(operationId, true, null, 4, null);
                                                                                            writableDatabase.endTransaction();
                                                                                        } catch (Throwable th13) {
                                                                                            try {
                                                                                                throw th13;
                                                                                            } catch (Throwable th14) {
                                                                                                CloseableKt.closeFinally(cursorRawQuery6, th13);
                                                                                                throw th14;
                                                                                            }
                                                                                        }
                                                                                    } catch (Throwable th15) {
                                                                                        try {
                                                                                            throw th15;
                                                                                        } catch (Throwable th16) {
                                                                                            CloseableKt.closeFinally(cursorRawQuery5, th15);
                                                                                            throw th16;
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                            throw new IllegalArgumentException("\u062a\u0627\u0631\u064a\u062e \u0630\u0645\u0629 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                                                        }
                                                                    }
                                                                }
                                                                throw new IllegalArgumentException("\u0645\u0628\u0627\u0644\u063a \u0630\u0645\u0629 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u063a\u064a\u0631 \u0645\u062a\u0648\u0627\u0632\u0646\u0629".toString());
                                                            }
                                                        }
                                                    }
                                                    throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u062a\u0639\u0631\u064a\u0641 \u0630\u0645\u0629 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
                                                }
                                            }
                                        } catch (Throwable th17) {
                                            th = th17;
                                        }
                                        th3 = th;
                                        try {
                                            throw th3;
                                        } catch (Throwable th18) {
                                            CloseableKt.closeFinally(cursorRawQuery4, th3);
                                            throw th18;
                                        }
                                    } catch (Throwable th19) {
                                        th = th19;
                                        writableDatabase.endTransaction();
                                        throw th;
                                    }
                                } catch (Throwable th20) {
                                    th = th20;
                                    writableDatabase.endTransaction();
                                    throw th;
                                }
                            } catch (Throwable th21) {
                                obj = obj;
                                try {
                                    throw th21;
                                } catch (Throwable th22) {
                                    CloseableKt.closeFinally(cursorRawQuery3, th21);
                                    throw th22;
                                }
                            }
                            throw th;
                        } catch (Throwable th23) {
                            th2 = th23;
                        }
                    } catch (Throwable th24) {
                        th = th24;
                    }
                } catch (Throwable th25) {
                    th = th25;
                }
            } catch (Throwable th26) {
                th = th26;
                obj = obj;
            }
        }
        return applyResult;
    }

    public final ApplyResult applyReceivedServiceTransferDebtCollection(String operationId) {
        String strTake;
        ApplyResult applyResult;
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        synchronized (APPLY_LOCK) {
            if (StringsKt.isBlank(operationId)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
            Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
            SyncDeviceIdentity.Companion companion = SyncDeviceIdentity.INSTANCE;
            Context appContext = this.appContext;
            Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
            String orCreate = companion.getOrCreate(appContext);
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", (String[]) null);
            try {
                Cursor cursor = cursorRawQuery;
                String string = cursor.moveToFirst() ? cursor.getString(0) : null;
                CloseableKt.closeFinally(cursorRawQuery, null);
                Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='authority_device_id'", (String[]) null);
                try {
                    Cursor cursor2 = cursorRawQuery2;
                    String string2 = cursor2.moveToFirst() ? cursor2.getString(0) : null;
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    if (!Intrinsics.areEqual(string, "PRIMARY") || !Intrinsics.areEqual(string2, orCreate)) {
                        throw new IllegalArgumentException("\u0627\u0639\u062a\u0645\u0627\u062f \u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0646 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u0645\u062a\u0627\u062d \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0641\u0642\u0637".toString());
                    }
                    writableDatabase.beginTransaction();
                    try {
                        Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT i.origin_device_id,i.entity_type,i.entity_id,i.payload_json,i.status,\n                          m.primary_user_id,i.payload_sha256,i.operation_type\n                   FROM sync_inbox i\n                   JOIN sync_actor_mappings m ON m.device_id=i.origin_device_id\n                    AND m.remote_actor_user_id=i.actor_user_id AND m.is_active=1\n                   JOIN users u ON u.id=m.primary_user_id\n                   JOIN sync_devices d ON d.device_id=i.origin_device_id\n                   WHERE i.operation_id=? AND d.device_role='SECONDARY' AND d.status='ACTIVE'\n                     AND u.is_active=1 AND u.must_change_password=0 LIMIT 1", new String[]{operationId});
                        try {
                            Cursor cursor3 = cursorRawQuery3;
                            if (!cursor3.moveToFirst()) {
                                throw new IllegalArgumentException("\u0637\u0644\u0628 \u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0646 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0623\u0648 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645/\u0627\u0644\u062c\u0647\u0627\u0632 \u063a\u064a\u0631 \u0645\u062e\u0648\u0644".toString());
                            }
                            String[] strArr = new String[8];
                            for (int i = 0; i < 8; i++) {
                                strArr[i] = cursor3.getString(i);
                            }
                            CloseableKt.closeFinally(cursorRawQuery3, null);
                            if (Intrinsics.areEqual(strArr[4], "APPLIED")) {
                                writableDatabase.setTransactionSuccessful();
                                applyResult = new ApplyResult(operationId, true, null, 4, null);
                            } else {
                                if (!Intrinsics.areEqual(strArr[4], "RECEIVED") || !Intrinsics.areEqual(strArr[1], "SERVICE_TRANSFER_DEBT_PAYMENT") || !Intrinsics.areEqual(strArr[7], "CREATE")) {
                                    throw new IllegalArgumentException("\u062d\u0627\u0644\u0629 \u0623\u0648 \u0646\u0648\u0639 \u0637\u0644\u0628 \u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0646 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                }
                                String str = strArr[3];
                                Intrinsics.checkNotNull(str);
                                JSONObject jSONObject = new JSONObject(str);
                                String string3 = jSONObject.getString("transfer_id");
                                String string4 = jSONObject.getString("payment_id");
                                String string5 = jSONObject.getString("customer_id");
                                long j = jSONObject.getLong("amount_minor");
                                if (Intrinsics.areEqual(jSONObject.optString("business_operation"), "COLLECT_SERVICE_TRANSFER_DEBT") && Intrinsics.areEqual(string4, strArr[2])) {
                                    Intrinsics.checkNotNull(string3);
                                    if (!StringsKt.isBlank(string3)) {
                                        Intrinsics.checkNotNull(string5);
                                        if (!StringsKt.isBlank(string5) && j > 0) {
                                            PermissionPolicy permissionPolicy = PermissionPolicy.INSTANCE;
                                            String str2 = strArr[5];
                                            Intrinsics.checkNotNull(str2);
                                            permissionPolicy.require(writableDatabase, str2, PermissionPolicy.CUSTOMERS_COLLECT);
                                            Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT operation_type,payload_hash,result_reference FROM operation_registry WHERE operation_id=?", new String[]{operationId});
                                            try {
                                                Cursor cursor4 = cursorRawQuery4;
                                                String[] strArr2 = cursor4.moveToFirst() ? new String[]{cursor4.getString(0), cursor4.getString(1), cursor4.isNull(2) ? "" : cursor4.getString(2)} : null;
                                                CloseableKt.closeFinally(cursorRawQuery4, null);
                                                if (strArr2 != null) {
                                                    if (Intrinsics.areEqual(strArr2[0], "COLLECT_SERVICE_TRANSFER_DEBT") && Intrinsics.areEqual(strArr2[1], strArr[6])) {
                                                        String str3 = strArr2[2];
                                                        Intrinsics.checkNotNullExpressionValue(str3, "get(...)");
                                                        if (!StringsKt.isBlank(str3)) {
                                                            ContentValues contentValues = new ContentValues();
                                                            contentValues.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                                            contentValues.put("applied_at", timestamp());
                                                            contentValues.putNull("rejection_reason");
                                                            Unit unit = Unit.INSTANCE;
                                                            writableDatabase.update("sync_inbox", contentValues, "operation_id=? AND status='RECEIVED'", new String[]{operationId});
                                                            writableDatabase.setTransactionSuccessful();
                                                            applyResult = new ApplyResult(operationId, true, null, 4, null);
                                                        }
                                                    }
                                                    throw new IllegalArgumentException("\u062a\u0639\u0627\u0631\u0636 \u0641\u064a \u0633\u062c\u0644 \u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0646 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u0623\u0648 \u0625\u0639\u0627\u062f\u0629 \u0627\u0633\u062a\u062e\u062f\u0627\u0645 \u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0639\u0645\u0644\u064a\u0629".toString());
                                                }
                                                Cursor cursorRawQuery5 = writableDatabase.rawQuery("SELECT t.customer_id,t.status,r.original_amount_minor,t.customer_charge_minor\n                   FROM service_transfers t JOIN service_transfer_receivables r ON r.transfer_id=t.id\n                   WHERE t.id=?", new String[]{string3});
                                                try {
                                                    Cursor cursor5 = cursorRawQuery5;
                                                    if (!cursor5.moveToFirst()) {
                                                        throw new IllegalArgumentException("\u0630\u0645\u0629 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629 \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a".toString());
                                                    }
                                                    String[] strArr3 = {cursor5.getString(0), cursor5.getString(1), String.valueOf(cursor5.getLong(2)), String.valueOf(cursor5.getLong(3))};
                                                    CloseableKt.closeFinally(cursorRawQuery5, null);
                                                    if (!Intrinsics.areEqual(strArr3[0], string5) || !Intrinsics.areEqual(strArr3[1], "POSTED") || !Intrinsics.areEqual(strArr3[2], strArr3[3])) {
                                                        throw new IllegalArgumentException("\u0627\u0644\u0632\u0628\u0648\u0646 \u0623\u0648 \u062d\u0627\u0644\u0629 \u0623\u0648 \u0623\u0635\u0644 \u0630\u0645\u0629 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u0627\u0644\u0633\u062c\u0644 \u0627\u0644\u0623\u0633\u0627\u0633\u064a".toString());
                                                    }
                                                    String string6 = jSONObject.getString("cash_account_id");
                                                    String string7 = jSONObject.getString("cash_account_type");
                                                    String string8 = jSONObject.getString("cash_account_currency_code");
                                                    if (!SetsKt.setOf((Object[]) new String[]{"CASH_BOX", "WALLET"}).contains(string7) || !Intrinsics.areEqual(string8, "SYP")) {
                                                        throw new IllegalArgumentException("\u0646\u0648\u0639 \u0623\u0648 \u0639\u0645\u0644\u0629 \u062d\u0633\u0627\u0628 \u0642\u0628\u0636 \u062a\u062d\u0635\u064a\u0644 \u0627\u0644\u062f\u064a\u0646 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u064a\u0646".toString());
                                                    }
                                                    Cursor cursorRawQuery6 = writableDatabase.rawQuery("SELECT m.primary_cash_account_id,a.account_type,a.currency_code,a.is_active\n                   FROM sync_cash_account_mappings m\n                   JOIN cash_accounts a ON a.id=m.primary_cash_account_id\n                   WHERE m.device_id=? AND m.remote_cash_account_id=? AND m.is_active=1", new String[]{strArr[0], string6});
                                                    try {
                                                        Cursor cursor6 = cursorRawQuery6;
                                                        if (!cursor6.moveToFirst() || cursor6.getInt(3) != 1) {
                                                            throw new IllegalArgumentException("\u062d\u0633\u0627\u0628 \u0642\u0628\u0636 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u063a\u064a\u0631 \u0645\u0631\u0628\u0648\u0637 \u0623\u0648 \u0645\u0648\u0642\u0648\u0641".toString());
                                                        }
                                                        if (!Intrinsics.areEqual(cursor6.getString(1), string7) || !Intrinsics.areEqual(cursor6.getString(2), string8)) {
                                                            throw new IllegalArgumentException("\u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a \u0627\u0644\u0645\u0631\u062a\u0628\u0637 \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u0646\u0648\u0639 \u0623\u0648 \u0639\u0645\u0644\u0629 \u062d\u0633\u0627\u0628 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a".toString());
                                                        }
                                                        String string9 = cursor6.getString(0);
                                                        CloseableKt.closeFinally(cursorRawQuery6, null);
                                                        String string10 = jSONObject.getString("occurred_at");
                                                        String string11 = jSONObject.getString("operating_date");
                                                        Intrinsics.checkNotNull(string10);
                                                        if (new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(string10)) {
                                                            Intrinsics.checkNotNull(string11);
                                                            if (new Regex("\\d{4}-\\d{2}-\\d{2}").matches(string11)) {
                                                                if (jSONObject.isNull("note")) {
                                                                    strTake = null;
                                                                } else {
                                                                    String string12 = jSONObject.getString("note");
                                                                    Intrinsics.checkNotNullExpressionValue(string12, "getString(...)");
                                                                    strTake = StringsKt.take(string12, 500);
                                                                }
                                                                Context appContext2 = this.appContext;
                                                                Intrinsics.checkNotNullExpressionValue(appContext2, "appContext");
                                                                ServiceTransferReceivableRepository serviceTransferReceivableRepository = new ServiceTransferReceivableRepository(appContext2);
                                                                String str4 = strArr[5];
                                                                Intrinsics.checkNotNull(str4);
                                                                Intrinsics.checkNotNull(string3);
                                                                Intrinsics.checkNotNull(string9);
                                                                ServiceTransferDebtPaymentResult serviceTransferDebtPaymentResultCollect = serviceTransferReceivableRepository.collect(str4, string3, operationId, string9, j, strTake, string10, string11);
                                                                ContentValues contentValues2 = new ContentValues();
                                                                contentValues2.put("operation_id", operationId);
                                                                contentValues2.put("operation_type", "COLLECT_SERVICE_TRANSFER_DEBT");
                                                                contentValues2.put("payload_hash", strArr[6]);
                                                                contentValues2.put(NotificationCompat.CATEGORY_STATUS, "COMPLETED");
                                                                contentValues2.put("result_reference", serviceTransferDebtPaymentResultCollect.getPaymentId());
                                                                contentValues2.put("created_at", timestamp());
                                                                contentValues2.put("completed_at", timestamp());
                                                                Unit unit2 = Unit.INSTANCE;
                                                                writableDatabase.insertOrThrow("operation_registry", null, contentValues2);
                                                                ContentValues contentValues3 = new ContentValues();
                                                                contentValues3.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                                                contentValues3.put("applied_at", timestamp());
                                                                contentValues3.putNull("rejection_reason");
                                                                Unit unit3 = Unit.INSTANCE;
                                                                if (writableDatabase.update("sync_inbox", contentValues3, "operation_id=? AND status='RECEIVED'", new String[]{operationId}) != 1) {
                                                                    throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062b\u0628\u064a\u062a \u0646\u062a\u064a\u062c\u0629 \u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0646 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f".toString());
                                                                }
                                                                writableDatabase.setTransactionSuccessful();
                                                                applyResult = new ApplyResult(operationId, true, null, 4, null);
                                                            }
                                                        }
                                                        throw new IllegalArgumentException("\u062a\u0627\u0631\u064a\u062e \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u0627\u0644\u0648\u0627\u0631\u062f \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                                    } catch (Throwable th) {
                                                        try {
                                                            throw th;
                                                        } catch (Throwable th2) {
                                                            CloseableKt.closeFinally(cursorRawQuery6, th);
                                                            throw th2;
                                                        }
                                                    }
                                                } catch (Throwable th3) {
                                                    try {
                                                        throw th3;
                                                    } catch (Throwable th4) {
                                                        CloseableKt.closeFinally(cursorRawQuery5, th3);
                                                        throw th4;
                                                    }
                                                }
                                            } catch (Throwable th5) {
                                                try {
                                                    throw th5;
                                                } catch (Throwable th6) {
                                                    CloseableKt.closeFinally(cursorRawQuery4, th5);
                                                    throw th6;
                                                }
                                            }
                                        }
                                    }
                                }
                                throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0637\u0644\u0628 \u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0646 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u063a\u064a\u0631 \u0645\u062a\u0637\u0627\u0628\u0642\u0629".toString());
                            }
                            writableDatabase.endTransaction();
                        } catch (Throwable th7) {
                            try {
                                throw th7;
                            } catch (Throwable th8) {
                                CloseableKt.closeFinally(cursorRawQuery3, th7);
                                throw th8;
                            }
                        }
                    } catch (Throwable th9) {
                        writableDatabase.endTransaction();
                        throw th9;
                    }
                } catch (Throwable th10) {
                    try {
                        throw th10;
                    } catch (Throwable th11) {
                        CloseableKt.closeFinally(cursorRawQuery2, th10);
                        throw th11;
                    }
                }
            } catch (Throwable th12) {
                try {
                    throw th12;
                } catch (Throwable th13) {
                    CloseableKt.closeFinally(cursorRawQuery, th12);
                    throw th13;
                }
            }
        }
        return applyResult;
    }

    public final ApplyResult applyReceivedSalesReturn(String operationId) {
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        return applyReceivedReturn(operationId, "POST_SALES_RETURN", "SALES_RETURN");
    }

    public final ApplyResult applyReceivedPurchaseReturn(String operationId) {
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        return applyReceivedReturn(operationId, "POST_PURCHASE_RETURN", "PURCHASE_RETURN");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final ApplyResult applyReceivedReturn(String operationId, String businessOperation, String entityType) {
        boolean z;
        String strResultReference;
        ApplyResult applyResult;
        synchronized (APPLY_LOCK) {
            if (StringsKt.isBlank(operationId)) {
                throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0623\u0645\u0631 \u0645\u0637\u0644\u0648\u0628".toString());
            }
            SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
            Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
            SyncDeviceIdentity.Companion companion = SyncDeviceIdentity.INSTANCE;
            Context appContext = this.appContext;
            Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
            String orCreate = companion.getOrCreate(appContext);
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", (String[]) null);
            try {
                Cursor cursor = cursorRawQuery;
                String string = cursor.moveToFirst() ? cursor.getString(0) : null;
                CloseableKt.closeFinally(cursorRawQuery, null);
                Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='authority_device_id'", (String[]) null);
                try {
                    Cursor cursor2 = cursorRawQuery2;
                    String string2 = cursor2.moveToFirst() ? cursor2.getString(0) : null;
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    if (!Intrinsics.areEqual(string, "PRIMARY") || !Intrinsics.areEqual(string2, orCreate)) {
                        throw new IllegalArgumentException("\u062a\u0637\u0628\u064a\u0642 \u0627\u0644\u0645\u0631\u062a\u062c\u0639\u0627\u062a \u0627\u0644\u0648\u0627\u0631\u062f\u0629 \u0645\u0633\u0645\u0648\u062d \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0641\u0642\u0637".toString());
                    }
                    writableDatabase.beginTransaction();
                    try {
                        Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT i.origin_device_id,i.entity_type,i.entity_id,i.payload_json,i.status,m.primary_user_id\n                       FROM sync_inbox i JOIN sync_actor_mappings m\n                     ON m.device_id=i.origin_device_id\n                    AND m.remote_actor_user_id=i.actor_user_id AND m.is_active=1\n                       JOIN users u ON u.id=m.primary_user_id JOIN sync_devices d ON d.device_id=i.origin_device_id\n                       WHERE i.operation_id=? AND d.device_role='SECONDARY' AND d.status='ACTIVE'\n                         AND u.is_active=1 AND u.must_change_password=0\n                       LIMIT 1", new String[]{operationId});
                        try {
                            Cursor cursor3 = cursorRawQuery3;
                            if (!cursor3.moveToFirst()) {
                                throw new IllegalArgumentException("\u0623\u0645\u0631 \u0627\u0644\u0645\u0631\u062a\u062c\u0639 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0623\u0648 \u0627\u0644\u062c\u0647\u0627\u0632/\u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0645\u0631\u0628\u0648\u0637 \u0623\u0648 \u0645\u0648\u0642\u0648\u0641".toString());
                            }
                            String[] strArr = {cursor3.getString(0), cursor3.getString(1), cursor3.getString(2), cursor3.getString(3), cursor3.getString(4), cursor3.getString(5)};
                            CloseableKt.closeFinally(cursorRawQuery3, null);
                            if (!Intrinsics.areEqual(strArr[4], "APPLIED")) {
                                if (Intrinsics.areEqual(strArr[4], "RECEIVED") && Intrinsics.areEqual(strArr[1], entityType)) {
                                    if (Intrinsics.areEqual(strArr[2], "pending-return-" + operationId)) {
                                        JSONObject jSONObject = new JSONObject(strArr[3]);
                                        if (!Intrinsics.areEqual(jSONObject.optString("business_operation"), businessOperation)) {
                                            throw new IllegalArgumentException("\u0646\u0648\u0639 \u0639\u0645\u0644\u064a\u0629 \u0627\u0644\u0645\u0631\u062a\u062c\u0639 \u0627\u0644\u0648\u0627\u0631\u062f \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                        }
                                        PermissionPolicy permissionPolicy = PermissionPolicy.INSTANCE;
                                        String str = strArr[5];
                                        Intrinsics.checkNotNullExpressionValue(str, "get(...)");
                                        permissionPolicy.require(writableDatabase, str, PermissionPolicy.RETURNS_PROCESS);
                                        String string3 = jSONObject.getString("original_invoice_id");
                                        String string4 = jSONObject.getString("return_date");
                                        String string5 = jSONObject.getString("reason");
                                        String string6 = jSONObject.getString("occurred_at");
                                        String string7 = jSONObject.getString("operating_date");
                                        Intrinsics.checkNotNull(string3);
                                        if (!StringsKt.isBlank(string3)) {
                                            Intrinsics.checkNotNull(string4);
                                            if (new Regex("\\d{4}-\\d{2}-\\d{2}").matches(string4)) {
                                                Intrinsics.checkNotNull(string6);
                                                if (new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(string6)) {
                                                    Intrinsics.checkNotNull(string7);
                                                    if (new Regex("\\d{4}-\\d{2}-\\d{2}").matches(string7)) {
                                                        JSONArray jSONArray = jSONObject.getJSONArray("items");
                                                        if (jSONArray.length() <= 0) {
                                                            throw new IllegalArgumentException("\u0627\u0644\u0645\u0631\u062a\u062c\u0639 \u0627\u0644\u0648\u0627\u0631\u062f \u0628\u0644\u0627 \u0623\u0635\u0646\u0627\u0641".toString());
                                                        }
                                                        JSONArray jSONArray2 = jSONObject.getJSONArray("refund_allocations");
                                                        ArrayList arrayList = new ArrayList();
                                                        int length = jSONArray2.length();
                                                        int i = 0;
                                                        while (i < length) {
                                                            JSONObject jSONObject2 = jSONArray2.getJSONObject(i);
                                                            JSONArray jSONArray3 = jSONArray2;
                                                            String string8 = jSONObject2.getString("cash_account_id");
                                                            int i2 = length;
                                                            String string9 = jSONObject2.getString("cash_account_type");
                                                            String string10 = jSONObject2.getString("cash_account_currency_code");
                                                            String str2 = string6;
                                                            long j = jSONObject2.getLong("amount_minor");
                                                            Intrinsics.checkNotNull(string8);
                                                            if (StringsKt.isBlank(string8) || !SetsKt.setOf((Object[]) new String[]{"CASH_BOX", "WALLET"}).contains(string9) || !SetsKt.setOf((Object[]) new String[]{"USD", "SYP"}).contains(string10) || j <= 0) {
                                                                throw new IllegalArgumentException("\u062d\u0633\u0627\u0628 \u0631\u062f \u0627\u0644\u0645\u0628\u0644\u063a \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                                            }
                                                            String str3 = string3;
                                                            Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT m.primary_cash_account_id,a.account_type,a.currency_code\n                           FROM sync_cash_account_mappings m JOIN cash_accounts a ON a.id=m.primary_cash_account_id\n                           WHERE m.device_id=? AND m.remote_cash_account_id=? AND m.is_active=1 AND a.is_active=1", new String[]{strArr[0], string8});
                                                            try {
                                                                Cursor cursor4 = cursorRawQuery4;
                                                                if (!cursor4.moveToFirst()) {
                                                                    throw new IllegalArgumentException("\u062d\u0633\u0627\u0628 \u0631\u062f \u0627\u0644\u0645\u0628\u0644\u063a \u063a\u064a\u0631 \u0645\u0631\u0628\u0648\u0637 \u0623\u0648 \u0645\u0648\u0642\u0648\u0641".toString());
                                                                }
                                                                if (!Intrinsics.areEqual(cursor4.getString(1), string9) || !Intrinsics.areEqual(cursor4.getString(2), string10)) {
                                                                    throw new IllegalArgumentException("\u0646\u0648\u0639 \u0623\u0648 \u0639\u0645\u0644\u0629 \u062d\u0633\u0627\u0628 \u0631\u062f \u0627\u0644\u0645\u0628\u0644\u063a \u0627\u0644\u0645\u0631\u062a\u0628\u0637 \u063a\u064a\u0631 \u0645\u062a\u0637\u0627\u0628\u0642\u064a\u0646".toString());
                                                                }
                                                                String string11 = cursor4.getString(0);
                                                                CloseableKt.closeFinally(cursorRawQuery4, null);
                                                                arrayList.add(TuplesKt.to(string11, Long.valueOf(j)));
                                                                i++;
                                                                jSONArray2 = jSONArray3;
                                                                length = i2;
                                                                string6 = str2;
                                                                string3 = str3;
                                                            } catch (Throwable th) {
                                                                try {
                                                                    throw th;
                                                                } catch (Throwable th2) {
                                                                    CloseableKt.closeFinally(cursorRawQuery4, th);
                                                                    throw th2;
                                                                }
                                                            }
                                                        }
                                                        String str4 = string3;
                                                        String str5 = string6;
                                                        if (Intrinsics.areEqual(businessOperation, "POST_SALES_RETURN")) {
                                                            IntRange intRangeUntil = RangesKt.until(0, jSONArray.length());
                                                            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10));
                                                            Iterator<Integer> it = intRangeUntil.iterator();
                                                            while (it.hasNext()) {
                                                                JSONObject jSONObject3 = jSONArray.getJSONObject(((IntIterator) it).nextInt());
                                                                String string12 = jSONObject3.getString("original_sales_item_id");
                                                                Intrinsics.checkNotNullExpressionValue(string12, "getString(...)");
                                                                arrayList2.add(new SalesReturnItemInput(string12, new BigDecimal(jSONObject3.getString("quantity"))));
                                                            }
                                                            ArrayList arrayList3 = arrayList2;
                                                            Context appContext2 = this.appContext;
                                                            Intrinsics.checkNotNullExpressionValue(appContext2, "appContext");
                                                            SalesReturnRepository salesReturnRepository = new SalesReturnRepository(appContext2);
                                                            String str6 = strArr[5];
                                                            Intrinsics.checkNotNullExpressionValue(str6, "get(...)");
                                                            Intrinsics.checkNotNull(string4);
                                                            Intrinsics.checkNotNull(string5);
                                                            ArrayList<Pair> arrayList4 = arrayList;
                                                            ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList4, 10));
                                                            for (Pair pair : arrayList4) {
                                                                arrayList5.add(new SalesRefundAllocationInput((String) pair.getFirst(), ((Number) pair.getSecond()).longValue()));
                                                            }
                                                            salesReturnRepository.postSalesReturn(str6, str4, operationId, string4, string5, arrayList3, arrayList5, str5, true, string7);
                                                            strResultReference = resultReference(writableDatabase, operationId, "POST_SALES_RETURN");
                                                            z = false;
                                                        } else {
                                                            IntRange intRangeUntil2 = RangesKt.until(0, jSONArray.length());
                                                            ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil2, 10));
                                                            Iterator<Integer> it2 = intRangeUntil2.iterator();
                                                            while (it2.hasNext()) {
                                                                JSONObject jSONObject4 = jSONArray.getJSONObject(((IntIterator) it2).nextInt());
                                                                String string13 = jSONObject4.getString("original_purchase_item_id");
                                                                Intrinsics.checkNotNullExpressionValue(string13, "getString(...)");
                                                                arrayList6.add(new PurchaseReturnItemInput(string13, new BigDecimal(jSONObject4.getString("quantity"))));
                                                            }
                                                            ArrayList arrayList7 = arrayList6;
                                                            Context appContext3 = this.appContext;
                                                            Intrinsics.checkNotNullExpressionValue(appContext3, "appContext");
                                                            PurchaseReturnRepository purchaseReturnRepository = new PurchaseReturnRepository(appContext3);
                                                            String str7 = strArr[5];
                                                            Intrinsics.checkNotNullExpressionValue(str7, "get(...)");
                                                            Intrinsics.checkNotNull(string4);
                                                            Intrinsics.checkNotNull(string5);
                                                            ArrayList<Pair> arrayList8 = arrayList;
                                                            ArrayList arrayList9 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList8, 10));
                                                            for (Pair pair2 : arrayList8) {
                                                                arrayList9.add(new SupplierRefundAllocationInput((String) pair2.getFirst(), ((Number) pair2.getSecond()).longValue()));
                                                            }
                                                            z = false;
                                                            purchaseReturnRepository.postPurchaseReturn(str7, str4, operationId, string4, string5, arrayList7, arrayList9, str5, true, string7);
                                                            strResultReference = resultReference(writableDatabase, operationId, "POST_PURCHASE_RETURN");
                                                        }
                                                        String str8 = strResultReference;
                                                        if (str8 == null || StringsKt.isBlank(str8)) {
                                                            z = true;
                                                        }
                                                        if (z) {
                                                            throw new IllegalArgumentException("\u0644\u0645 \u064a\u064f\u0646\u0634\u0623 \u0633\u062c\u0644 \u0627\u0644\u0645\u0631\u062a\u062c\u0639 \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a".toString());
                                                        }
                                                        ContentValues contentValues = new ContentValues();
                                                        contentValues.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                                        contentValues.put("applied_at", timestamp());
                                                        contentValues.putNull("rejection_reason");
                                                        Unit unit = Unit.INSTANCE;
                                                        if (writableDatabase.update("sync_inbox", contentValues, "operation_id=? AND status='RECEIVED'", new String[]{operationId}) != 1) {
                                                            throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062b\u0628\u064a\u062a \u062a\u0637\u0628\u064a\u0642 \u0627\u0644\u0645\u0631\u062a\u062c\u0639 \u0627\u0644\u0648\u0627\u0631\u062f".toString());
                                                        }
                                                        writableDatabase.setTransactionSuccessful();
                                                        applyResult = new ApplyResult(operationId, true, null, 4, null);
                                                    }
                                                }
                                            }
                                        }
                                        throw new IllegalArgumentException("\u062a\u0627\u0631\u064a\u062e \u0623\u0648 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0645\u0631\u062a\u062c\u0639 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
                                    }
                                }
                                throw new IllegalArgumentException("\u0646\u0648\u0639 \u0623\u0648 \u062d\u0627\u0644\u0629 \u0643\u064a\u0627\u0646 \u0627\u0644\u0645\u0631\u062a\u062c\u0639 \u0627\u0644\u0648\u0627\u0631\u062f \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
                            }
                            writableDatabase.setTransactionSuccessful();
                            applyResult = new ApplyResult(operationId, true, null, 4, null);
                            writableDatabase.endTransaction();
                        } catch (Throwable th3) {
                            try {
                                throw th3;
                            } catch (Throwable th4) {
                                CloseableKt.closeFinally(cursorRawQuery3, th3);
                                throw th4;
                            }
                        }
                    } catch (Throwable th5) {
                        writableDatabase.endTransaction();
                        throw th5;
                    }
                } catch (Throwable th6) {
                    try {
                        throw th6;
                    } catch (Throwable th7) {
                        CloseableKt.closeFinally(cursorRawQuery2, th6);
                        throw th7;
                    }
                }
            } catch (Throwable th8) {
                try {
                    throw th8;
                } catch (Throwable th9) {
                    CloseableKt.closeFinally(cursorRawQuery, th8);
                    throw th9;
                }
            }
        }
        return applyResult;
    }

    public final ApplyResult applyReceivedInventoryAdjustment(String operationId) {
        ApplyResult applyResult;
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        synchronized (APPLY_LOCK) {
            if (StringsKt.isBlank(operationId)) {
                throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0623\u0645\u0631 \u0645\u0637\u0644\u0648\u0628".toString());
            }
            SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
            Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
            SyncDeviceIdentity.Companion companion = SyncDeviceIdentity.INSTANCE;
            Context appContext = this.appContext;
            Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
            String orCreate = companion.getOrCreate(appContext);
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", (String[]) null);
            try {
                Cursor cursor = cursorRawQuery;
                String string = cursor.moveToFirst() ? cursor.getString(0) : null;
                CloseableKt.closeFinally(cursorRawQuery, null);
                Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='authority_device_id'", (String[]) null);
                try {
                    Cursor cursor2 = cursorRawQuery2;
                    String string2 = cursor2.moveToFirst() ? cursor2.getString(0) : null;
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    if (!Intrinsics.areEqual(string, "PRIMARY") || !Intrinsics.areEqual(string2, orCreate)) {
                        throw new IllegalArgumentException("\u062a\u0637\u0628\u064a\u0642 \u062a\u0633\u0648\u064a\u0629 \u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u0627\u0644\u0648\u0627\u0631\u062f\u0629 \u0645\u0633\u0645\u0648\u062d \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0641\u0642\u0637".toString());
                    }
                    writableDatabase.beginTransaction();
                    try {
                        Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT i.origin_device_id,i.entity_type,i.entity_id,i.payload_json,i.status,m.primary_user_id\n                   FROM sync_inbox i JOIN sync_actor_mappings m\n                     ON m.device_id=i.origin_device_id\n                    AND m.remote_actor_user_id=i.actor_user_id AND m.is_active=1\n                   JOIN users u ON u.id=m.primary_user_id JOIN sync_devices d ON d.device_id=i.origin_device_id\n                   WHERE i.operation_id=? AND d.device_role='SECONDARY' AND d.status='ACTIVE'\n                     AND u.is_active=1 AND u.must_change_password=0\n                   LIMIT 1", new String[]{operationId});
                        try {
                            Cursor cursor3 = cursorRawQuery3;
                            if (!cursor3.moveToFirst()) {
                                throw new IllegalArgumentException("\u0623\u0645\u0631 \u062a\u0633\u0648\u064a\u0629 \u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0623\u0648 \u0627\u0644\u062c\u0647\u0627\u0632/\u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0645\u0631\u0628\u0648\u0637".toString());
                            }
                            String[] strArr = {cursor3.getString(0), cursor3.getString(1), cursor3.getString(2), cursor3.getString(3), cursor3.getString(4), cursor3.getString(5)};
                            CloseableKt.closeFinally(cursorRawQuery3, null);
                            if (!Intrinsics.areEqual(strArr[4], "APPLIED")) {
                                if (Intrinsics.areEqual(strArr[4], "RECEIVED") && Intrinsics.areEqual(strArr[1], "INVENTORY_ADJUSTMENT")) {
                                    if (Intrinsics.areEqual(strArr[2], "pending-adjustment-" + operationId)) {
                                        JSONObject jSONObject = new JSONObject(strArr[3]);
                                        if (!Intrinsics.areEqual(jSONObject.optString("business_operation"), "POST_INVENTORY_ADJUSTMENT")) {
                                            throw new IllegalArgumentException("\u0646\u0648\u0639 \u0639\u0645\u0644\u064a\u0629 \u062a\u0633\u0648\u064a\u0629 \u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u0627\u0644\u0648\u0627\u0631\u062f\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                        }
                                        PermissionPolicy permissionPolicy = PermissionPolicy.INSTANCE;
                                        String str = strArr[5];
                                        Intrinsics.checkNotNullExpressionValue(str, "get(...)");
                                        permissionPolicy.require(writableDatabase, str, PermissionPolicy.INVENTORY_ADJUST);
                                        String string3 = jSONObject.getString("occurred_at");
                                        String string4 = jSONObject.getString("operating_date");
                                        Intrinsics.checkNotNull(string3);
                                        if (new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(string3)) {
                                            Intrinsics.checkNotNull(string4);
                                            if (new Regex("\\d{4}-\\d{2}-\\d{2}").matches(string4)) {
                                                BigDecimal bigDecimal = new BigDecimal(jSONObject.getString("quantity_delta"));
                                                if (bigDecimal.signum() == 0) {
                                                    throw new IllegalArgumentException("\u0641\u0631\u0642 \u0643\u0645\u064a\u0629 \u0627\u0644\u062a\u0633\u0648\u064a\u0629 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0635\u0641\u0631\u0627\u064b".toString());
                                                }
                                                Long lValueOf = jSONObject.isNull("unit_cost_usd_cents") ? null : Long.valueOf(jSONObject.getLong("unit_cost_usd_cents"));
                                                Context appContext2 = this.appContext;
                                                Intrinsics.checkNotNullExpressionValue(appContext2, "appContext");
                                                InventoryAdjustmentRepository inventoryAdjustmentRepository = new InventoryAdjustmentRepository(appContext2);
                                                String str2 = strArr[5];
                                                Intrinsics.checkNotNullExpressionValue(str2, "get(...)");
                                                String string5 = jSONObject.getString("product_id");
                                                Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                                                String string6 = jSONObject.getString("reason");
                                                Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                                                inventoryAdjustmentRepository.postAdjustment(str2, string5, bigDecimal, string6, operationId, lValueOf, jSONObject.getString("warehouse_id"), string3, true);
                                                ContentValues contentValues = new ContentValues();
                                                contentValues.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                                contentValues.put("applied_at", timestamp());
                                                contentValues.putNull("rejection_reason");
                                                Unit unit = Unit.INSTANCE;
                                                if (writableDatabase.update("sync_inbox", contentValues, "operation_id=? AND status='RECEIVED'", new String[]{operationId}) != 1) {
                                                    throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062b\u0628\u064a\u062a \u062a\u0637\u0628\u064a\u0642 \u062a\u0633\u0648\u064a\u0629 \u0627\u0644\u0645\u062e\u0632\u0648\u0646".toString());
                                                }
                                                writableDatabase.setTransactionSuccessful();
                                                applyResult = new ApplyResult(operationId, true, null, 4, null);
                                            }
                                        }
                                        throw new IllegalArgumentException("\u062a\u0627\u0631\u064a\u062e \u062d\u0631\u0643\u0629 \u0627\u0644\u062a\u0633\u0648\u064a\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                    }
                                }
                                throw new IllegalArgumentException("\u0646\u0648\u0639 \u0623\u0648 \u062d\u0627\u0644\u0629 \u062a\u0633\u0648\u064a\u0629 \u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u0627\u0644\u0648\u0627\u0631\u062f\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
                            }
                            writableDatabase.setTransactionSuccessful();
                            applyResult = new ApplyResult(operationId, true, null, 4, null);
                            writableDatabase.endTransaction();
                        } catch (Throwable th) {
                            try {
                                throw th;
                            } catch (Throwable th2) {
                                CloseableKt.closeFinally(cursorRawQuery3, th);
                                throw th2;
                            }
                        }
                    } catch (Throwable th3) {
                        writableDatabase.endTransaction();
                        throw th3;
                    }
                } catch (Throwable th4) {
                    try {
                        throw th4;
                    } catch (Throwable th5) {
                        CloseableKt.closeFinally(cursorRawQuery2, th4);
                        throw th5;
                    }
                }
            } catch (Throwable th6) {
                try {
                    throw th6;
                } catch (Throwable th7) {
                    CloseableKt.closeFinally(cursorRawQuery, th6);
                    throw th7;
                }
            }
        }
        return applyResult;
    }

    public final ApplyResult applyReceivedCashAccount(String operationId) {
        String str;
        ApplyResult applyResult;
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        synchronized (APPLY_LOCK) {
            if (StringsKt.isBlank(operationId)) {
                throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u062d\u062f\u062b \u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a \u0645\u0637\u0644\u0648\u0628".toString());
            }
            SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
            Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
            SyncDeviceIdentity.Companion companion = SyncDeviceIdentity.INSTANCE;
            Context appContext = this.appContext;
            Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
            String orCreate = companion.getOrCreate(appContext);
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", (String[]) null);
            try {
                Cursor cursor = cursorRawQuery;
                String string = cursor.moveToFirst() ? cursor.getString(0) : null;
                CloseableKt.closeFinally(cursorRawQuery, null);
                Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='authority_device_id'", (String[]) null);
                try {
                    Cursor cursor2 = cursorRawQuery2;
                    String string2 = cursor2.moveToFirst() ? cursor2.getString(0) : null;
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    if (!Intrinsics.areEqual(string, "SECONDARY") || (str = string2) == null || StringsKt.isBlank(str) || Intrinsics.areEqual(string2, orCreate)) {
                        throw new IllegalArgumentException("\u062a\u0637\u0628\u064a\u0642 \u062a\u0639\u0631\u064a\u0641\u0627\u062a \u0627\u0644\u062d\u0633\u0627\u0628\u0627\u062a \u0627\u0644\u0645\u0627\u0644\u064a\u0629 \u0627\u0644\u0648\u0627\u0631\u062f\u0629 \u0645\u0633\u0645\u0648\u062d \u0639\u0644\u0649 \u062c\u0647\u0627\u0632 \u062b\u0627\u0646\u0648\u064a \u0645\u0631\u062a\u0628\u0637 \u0641\u0642\u0637".toString());
                    }
                    writableDatabase.beginTransaction();
                    try {
                        Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT origin_device_id,operation_type,entity_type,entity_id,payload_json,status,origin_created_at\n                   FROM sync_inbox WHERE operation_id=?", new String[]{operationId});
                        try {
                            Cursor cursor3 = cursorRawQuery3;
                            if (!cursor3.moveToFirst()) {
                                throw new IllegalArgumentException("\u062d\u062f\u062b \u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0641\u064a \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0648\u0627\u0631\u062f".toString());
                            }
                            String[] strArr = new String[7];
                            for (int i = 0; i < 7; i++) {
                                strArr[i] = cursor3.getString(i);
                            }
                            CloseableKt.closeFinally(cursorRawQuery3, null);
                            if (!Intrinsics.areEqual(strArr[0], string2) || !Intrinsics.areEqual(strArr[2], "CASH_ACCOUNT") || !SetsKt.setOf((Object[]) new String[]{"CREATE", "UPDATE"}).contains(strArr[1])) {
                                throw new IllegalArgumentException("\u0645\u0635\u062f\u0631 \u0623\u0648 \u0646\u0648\u0639 \u062d\u062f\u062b \u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                            }
                            if (Intrinsics.areEqual(strArr[5], "APPLIED")) {
                                writableDatabase.setTransactionSuccessful();
                                applyResult = new ApplyResult(operationId, true, null, 4, null);
                            } else {
                                if (!Intrinsics.areEqual(strArr[5], "RECEIVED")) {
                                    throw new IllegalArgumentException("\u062d\u0627\u0644\u0629 \u062d\u062f\u062b \u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a \u0644\u0627 \u062a\u0633\u0645\u062d \u0628\u0627\u0644\u062a\u0637\u0628\u064a\u0642".toString());
                                }
                                JSONObject jSONObject = new JSONObject(strArr[4]);
                                String strOptString = jSONObject.optString("cash_account_id");
                                String strOptString2 = jSONObject.optString("name");
                                Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
                                String string3 = StringsKt.trim((CharSequence) strOptString2).toString();
                                String strOptString3 = jSONObject.optString("account_type");
                                String strOptString4 = jSONObject.optString("currency_code");
                                boolean zOptBoolean = jSONObject.optBoolean("is_active", false);
                                String strOptString5 = jSONObject.optString("created_at");
                                Intrinsics.checkNotNull(strOptString5);
                                if (!new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(strOptString5)) {
                                    strOptString5 = null;
                                }
                                if (strOptString5 == null) {
                                    strOptString5 = strArr[6];
                                }
                                String strOptString6 = jSONObject.optString("service_wallet_key");
                                Intrinsics.checkNotNull(strOptString6);
                                if (StringsKt.isBlank(strOptString6) || Intrinsics.areEqual(strOptString6, "null")) {
                                    strOptString6 = null;
                                }
                                if (Intrinsics.areEqual(jSONObject.optString("business_operation"), "UPSERT_CASH_ACCOUNT")) {
                                    Intrinsics.checkNotNull(strOptString);
                                    if (!StringsKt.isBlank(strOptString) && Intrinsics.areEqual(strOptString, strArr[3]) && string3.length() > 0 && string3.length() <= 200 && SetsKt.setOf((Object[]) new String[]{"CASH_BOX", "WALLET", "BANK", "OTHER"}).contains(strOptString3) && SetsKt.setOf((Object[]) new String[]{"USD", "SYP"}).contains(strOptString4) && jSONObject.has("is_active") && jSONObject.has("sales_receipt_allowed") && ((!jSONObject.optBoolean("sales_receipt_allowed") || (zOptBoolean && SetsKt.setOf((Object[]) new String[]{"CASH_BOX", "WALLET"}).contains(strOptString3))) && (strOptString6 == null || (SetsKt.setOf((Object[]) new String[]{ServiceTransferRepository.SYRIATEL_SHARED, "SHAM_CASH"}).contains(strOptString6) && Intrinsics.areEqual(strOptString3, "WALLET") && Intrinsics.areEqual(strOptString4, "SYP") && zOptBoolean)))) {
                                        Intrinsics.checkNotNull(strOptString5);
                                        if (new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(strOptString5)) {
                                            Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT opening_balance_minor FROM cash_accounts WHERE id=?", new String[]{strOptString});
                                            try {
                                                Cursor cursor4 = cursorRawQuery4;
                                                Long lValueOf = cursor4.moveToFirst() ? Long.valueOf(cursor4.getLong(0)) : null;
                                                CloseableKt.closeFinally(cursorRawQuery4, null);
                                                if (lValueOf != null) {
                                                    Cursor cursorRawQuery5 = writableDatabase.rawQuery("SELECT 1 FROM cash_transactions WHERE account_id=? LIMIT 1", new String[]{strOptString});
                                                    try {
                                                        boolean zMoveToFirst = cursorRawQuery5.moveToFirst();
                                                        CloseableKt.closeFinally(cursorRawQuery5, null);
                                                        if (lValueOf.longValue() != 0 || zMoveToFirst) {
                                                            throw new IllegalArgumentException("\u062a\u0639\u0627\u0631\u0636 \u0645\u0639 \u062d\u0633\u0627\u0628 \u0645\u062d\u0644\u064a \u064a\u062d\u062a\u0648\u064a \u0631\u0635\u064a\u062f\u0627\u064b \u0623\u0648 \u062d\u0631\u0643\u0627\u062a \u0645\u0627\u0644\u064a\u0629\u061b \u0644\u0645 \u064a\u062a\u0645 \u0627\u0633\u062a\u0628\u062f\u0627\u0644\u0647".toString());
                                                        }
                                                        ContentValues contentValues = new ContentValues();
                                                        contentValues.put("name", string3);
                                                        contentValues.put("account_type", strOptString3);
                                                        contentValues.put("currency_code", strOptString4);
                                                        contentValues.put("opening_balance_minor", (Long) 0L);
                                                        contentValues.put("is_active", Integer.valueOf(zOptBoolean ? 1 : 0));
                                                        Unit unit = Unit.INSTANCE;
                                                        if (writableDatabase.update("cash_accounts", contentValues, "id=?", new String[]{strOptString}) != 1) {
                                                            throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062d\u062f\u064a\u062b \u062a\u0639\u0631\u064a\u0641 \u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a".toString());
                                                        }
                                                        Unit unit2 = Unit.INSTANCE;
                                                    } catch (Throwable th) {
                                                        try {
                                                            throw th;
                                                        } catch (Throwable th2) {
                                                            CloseableKt.closeFinally(cursorRawQuery5, th);
                                                            throw th2;
                                                        }
                                                    }
                                                } else {
                                                    ContentValues contentValues2 = new ContentValues();
                                                    contentValues2.put("id", strOptString);
                                                    contentValues2.put("name", string3);
                                                    contentValues2.put("account_type", strOptString3);
                                                    contentValues2.put("currency_code", strOptString4);
                                                    contentValues2.put("opening_balance_minor", (Long) 0L);
                                                    contentValues2.put("is_active", Integer.valueOf(zOptBoolean ? 1 : 0));
                                                    contentValues2.put("created_at", strOptString5);
                                                    Unit unit3 = Unit.INSTANCE;
                                                    Long.valueOf(writableDatabase.insertOrThrow("cash_accounts", null, contentValues2));
                                                }
                                                if (strOptString6 != null) {
                                                    String str2 = Intrinsics.areEqual(strOptString6, ServiceTransferRepository.SYRIATEL_SHARED) ? "\u0631\u0635\u064a\u062f \u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0627\u0644\u0645\u0634\u062a\u0631\u0643" : "\u0631\u0635\u064a\u062f \u0634\u0627\u0645 \u0643\u0627\u0634";
                                                    ContentValues contentValues3 = new ContentValues();
                                                    contentValues3.put("service_key", strOptString6);
                                                    contentValues3.put("name", str2);
                                                    contentValues3.put("created_at", strOptString5);
                                                    Unit unit4 = Unit.INSTANCE;
                                                    writableDatabase.insertWithOnConflict("service_wallets", null, contentValues3, 4);
                                                    Cursor cursorRawQuery6 = writableDatabase.rawQuery("SELECT account_id FROM service_wallet_account_links WHERE service_key=?", new String[]{strOptString6});
                                                    try {
                                                        Cursor cursor5 = cursorRawQuery6;
                                                        String string4 = cursor5.moveToFirst() ? cursor5.getString(0) : null;
                                                        CloseableKt.closeFinally(cursorRawQuery6, null);
                                                        Cursor cursorRawQuery7 = writableDatabase.rawQuery("SELECT service_key FROM service_wallet_account_links WHERE account_id=?", new String[]{strOptString});
                                                        try {
                                                            Cursor cursor6 = cursorRawQuery7;
                                                            String string5 = cursor6.moveToFirst() ? cursor6.getString(0) : null;
                                                            CloseableKt.closeFinally(cursorRawQuery7, null);
                                                            if (string4 != null && !Intrinsics.areEqual(string4, strOptString)) {
                                                                throw new IllegalArgumentException("\u0645\u062d\u0641\u0638\u0629 \u0627\u0644\u062e\u062f\u0645\u0629 \u0645\u0631\u062a\u0628\u0637\u0629 \u0645\u0633\u0628\u0642\u0627\u064b \u0628\u062d\u0633\u0627\u0628 \u0645\u0627\u0644\u064a \u0645\u062e\u062a\u0644\u0641".toString());
                                                            }
                                                            if (string5 != null && !Intrinsics.areEqual(string5, strOptString6)) {
                                                                throw new IllegalArgumentException("\u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a \u0645\u0631\u062a\u0628\u0637 \u0645\u0633\u0628\u0642\u0627\u064b \u0628\u0645\u062d\u0641\u0638\u0629 \u062e\u062f\u0645\u0629 \u0645\u062e\u062a\u0644\u0641\u0629".toString());
                                                            }
                                                            if (string4 == null) {
                                                                ContentValues contentValues4 = new ContentValues();
                                                                contentValues4.put("service_key", strOptString6);
                                                                contentValues4.put("account_id", strOptString);
                                                                contentValues4.put("linked_at", timestamp());
                                                                Unit unit5 = Unit.INSTANCE;
                                                                writableDatabase.insertOrThrow("service_wallet_account_links", null, contentValues4);
                                                            }
                                                        } catch (Throwable th3) {
                                                            try {
                                                                throw th3;
                                                            } catch (Throwable th4) {
                                                                CloseableKt.closeFinally(cursorRawQuery7, th3);
                                                                throw th4;
                                                            }
                                                        }
                                                    } catch (Throwable th5) {
                                                        try {
                                                            throw th5;
                                                        } catch (Throwable th6) {
                                                            CloseableKt.closeFinally(cursorRawQuery6, th5);
                                                            throw th6;
                                                        }
                                                    }
                                                }
                                                ContentValues contentValues5 = new ContentValues();
                                                contentValues5.put("state_key", "cash_account_sales_allowed:" + strOptString);
                                                contentValues5.put("state_value", jSONObject.optBoolean("sales_receipt_allowed") ? "1" : "0");
                                                contentValues5.put("updated_at", timestamp());
                                                Unit unit6 = Unit.INSTANCE;
                                                writableDatabase.insertWithOnConflict("sync_protocol_state", null, contentValues5, 5);
                                                ContentValues contentValues6 = new ContentValues();
                                                contentValues6.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                                contentValues6.put("applied_at", timestamp());
                                                contentValues6.putNull("rejection_reason");
                                                Unit unit7 = Unit.INSTANCE;
                                                if (writableDatabase.update("sync_inbox", contentValues6, "operation_id=? AND status='RECEIVED'", new String[]{operationId}) != 1) {
                                                    throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062b\u0628\u064a\u062a \u062a\u0637\u0628\u064a\u0642 \u062a\u0639\u0631\u064a\u0641 \u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a".toString());
                                                }
                                                writableDatabase.setTransactionSuccessful();
                                                applyResult = new ApplyResult(operationId, true, null, 4, null);
                                            } catch (Throwable th7) {
                                                try {
                                                    throw th7;
                                                } catch (Throwable th8) {
                                                    CloseableKt.closeFinally(cursorRawQuery4, th7);
                                                    throw th8;
                                                }
                                            }
                                        }
                                    }
                                }
                                throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u062a\u0639\u0631\u064a\u0641 \u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a \u0627\u0644\u0648\u0627\u0631\u062f\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
                            }
                            writableDatabase.endTransaction();
                        } catch (Throwable th9) {
                            try {
                                throw th9;
                            } catch (Throwable th10) {
                                CloseableKt.closeFinally(cursorRawQuery3, th9);
                                throw th10;
                            }
                        }
                    } catch (Throwable th11) {
                        writableDatabase.endTransaction();
                        throw th11;
                    }
                } catch (Throwable th12) {
                    try {
                        throw th12;
                    } catch (Throwable th13) {
                        CloseableKt.closeFinally(cursorRawQuery2, th12);
                        throw th13;
                    }
                }
            } catch (Throwable th14) {
                try {
                    throw th14;
                } catch (Throwable th15) {
                    CloseableKt.closeFinally(cursorRawQuery, th14);
                    throw th15;
                }
            }
        }
        return applyResult;
    }

    public final ApplyResult applyReceivedCustomer(String operationId) {
        String str;
        ApplyResult applyResult;
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        synchronized (APPLY_LOCK) {
            if (StringsKt.isBlank(operationId)) {
                throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0623\u0645\u0631 \u0627\u0644\u0632\u0628\u0648\u0646 \u0645\u0637\u0644\u0648\u0628".toString());
            }
            SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
            Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
            SyncDeviceIdentity.Companion companion = SyncDeviceIdentity.INSTANCE;
            Context appContext = this.appContext;
            Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
            String orCreate = companion.getOrCreate(appContext);
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", (String[]) null);
            try {
                Cursor cursor = cursorRawQuery;
                String string = cursor.moveToFirst() ? cursor.getString(0) : null;
                CloseableKt.closeFinally(cursorRawQuery, null);
                Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='authority_device_id'", (String[]) null);
                try {
                    Cursor cursor2 = cursorRawQuery2;
                    String string2 = cursor2.moveToFirst() ? cursor2.getString(0) : null;
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    if (!Intrinsics.areEqual(string, "SECONDARY") || (str = string2) == null || StringsKt.isBlank(str) || Intrinsics.areEqual(string2, orCreate)) {
                        throw new IllegalArgumentException("\u062a\u0637\u0628\u064a\u0642 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0632\u0628\u0627\u0626\u0646 \u0627\u0644\u0648\u0627\u0631\u062f\u0629 \u0645\u0633\u0645\u0648\u062d \u0639\u0644\u0649 \u062c\u0647\u0627\u0632 \u062b\u0627\u0646\u0648\u064a \u0645\u0631\u062a\u0628\u0637 \u0641\u0642\u0637".toString());
                    }
                    writableDatabase.beginTransaction();
                    try {
                        Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT origin_device_id,operation_type,entity_type,entity_id,payload_json,status,origin_created_at\n                   FROM sync_inbox WHERE operation_id=?", new String[]{operationId});
                        try {
                            Cursor cursor3 = cursorRawQuery3;
                            if (!cursor3.moveToFirst()) {
                                throw new IllegalArgumentException("\u0623\u0645\u0631 \u0627\u0644\u0632\u0628\u0648\u0646 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0641\u064a \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0648\u0627\u0631\u062f".toString());
                            }
                            String[] strArr = new String[7];
                            for (int i = 0; i < 7; i++) {
                                strArr[i] = cursor3.getString(i);
                            }
                            CloseableKt.closeFinally(cursorRawQuery3, null);
                            if (!Intrinsics.areEqual(strArr[0], string2) || !Intrinsics.areEqual(strArr[2], "CUSTOMER") || !SetsKt.setOf((Object[]) new String[]{"CREATE", "UPDATE"}).contains(strArr[1])) {
                                throw new IllegalArgumentException("\u0645\u0635\u062f\u0631 \u0623\u0648 \u0646\u0648\u0639 \u062d\u062f\u062b \u0627\u0644\u0632\u0628\u0648\u0646 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                            }
                            if (Intrinsics.areEqual(strArr[5], "APPLIED")) {
                                writableDatabase.setTransactionSuccessful();
                                applyResult = new ApplyResult(operationId, true, null, 4, null);
                            } else {
                                if (!Intrinsics.areEqual(strArr[5], "RECEIVED")) {
                                    throw new IllegalArgumentException("\u062d\u0627\u0644\u0629 \u062d\u062f\u062b \u0627\u0644\u0632\u0628\u0648\u0646 \u0644\u0627 \u062a\u0633\u0645\u062d \u0628\u0627\u0644\u062a\u0637\u0628\u064a\u0642".toString());
                                }
                                JSONObject jSONObject = new JSONObject(strArr[4]);
                                String strOptString = jSONObject.optString("customer_id");
                                String strOptString2 = jSONObject.optString("name");
                                Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
                                String string3 = StringsKt.trim((CharSequence) strOptString2).toString();
                                String strOptString3 = jSONObject.optString("created_at");
                                Intrinsics.checkNotNull(strOptString3);
                                if (!new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(strOptString3)) {
                                    strOptString3 = null;
                                }
                                if (strOptString3 == null) {
                                    strOptString3 = strArr[6];
                                }
                                String strOptString4 = jSONObject.optString("updated_at");
                                Intrinsics.checkNotNull(strOptString4);
                                if (!new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(strOptString4)) {
                                    strOptString4 = null;
                                }
                                if (strOptString4 == null) {
                                    strOptString4 = strOptString3;
                                }
                                String strOptString5 = jSONObject.optString("phone");
                                if (jSONObject.isNull("phone")) {
                                    strOptString5 = null;
                                }
                                String strOptString6 = jSONObject.optString("address");
                                if (jSONObject.isNull("address")) {
                                    strOptString6 = null;
                                }
                                String strOptString7 = jSONObject.optString("notes");
                                if (jSONObject.isNull("notes")) {
                                    strOptString7 = null;
                                }
                                Intrinsics.checkNotNull(strOptString);
                                if (!StringsKt.isBlank(strOptString) && Intrinsics.areEqual(strOptString, strArr[3]) && string3.length() > 0 && string3.length() <= 200) {
                                    if ((strOptString5 != null ? strOptString5.length() : 0) <= 80) {
                                        if ((strOptString6 != null ? strOptString6.length() : 0) <= 500) {
                                            if ((strOptString7 != null ? strOptString7.length() : 0) <= 2000) {
                                                ContentValues contentValues = new ContentValues();
                                                contentValues.put("name", string3);
                                                if (strOptString5 == null) {
                                                    contentValues.putNull("phone");
                                                } else {
                                                    contentValues.put("phone", strOptString5);
                                                }
                                                if (strOptString6 == null) {
                                                    contentValues.putNull("address");
                                                } else {
                                                    contentValues.put("address", strOptString6);
                                                }
                                                if (strOptString7 == null) {
                                                    contentValues.putNull("notes");
                                                } else {
                                                    contentValues.put("notes", strOptString7);
                                                }
                                                contentValues.put("is_active", Integer.valueOf(jSONObject.optBoolean("is_active", true) ? 1 : 0));
                                                contentValues.put("updated_at", strOptString4);
                                                Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT 1 FROM customers WHERE id=?", new String[]{strOptString});
                                                try {
                                                    boolean zMoveToFirst = cursorRawQuery4.moveToFirst();
                                                    CloseableKt.closeFinally(cursorRawQuery4, null);
                                                    if (zMoveToFirst) {
                                                        if (!(writableDatabase.update("customers", contentValues, "id=?", new String[]{strOptString}) == 1)) {
                                                            throw new IllegalArgumentException("Failed requirement.".toString());
                                                        }
                                                    } else {
                                                        contentValues.put("id", strOptString);
                                                        contentValues.put("opening_balance_minor", (Long) 0L);
                                                        contentValues.put("opening_balance_currency", "SYP");
                                                        contentValues.put("created_at", strOptString3);
                                                        writableDatabase.insertOrThrow("customers", null, contentValues);
                                                    }
                                                    ContentValues contentValues2 = new ContentValues();
                                                    contentValues2.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                                    contentValues2.put("applied_at", timestamp());
                                                    contentValues2.putNull("rejection_reason");
                                                    Unit unit = Unit.INSTANCE;
                                                    if (!(writableDatabase.update("sync_inbox", contentValues2, "operation_id=? AND status='RECEIVED'", new String[]{operationId}) == 1)) {
                                                        throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062b\u0628\u064a\u062a \u062a\u0637\u0628\u064a\u0642 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0632\u0628\u0648\u0646".toString());
                                                    }
                                                    writableDatabase.setTransactionSuccessful();
                                                    applyResult = new ApplyResult(operationId, true, null, 4, null);
                                                } catch (Throwable th) {
                                                    try {
                                                        throw th;
                                                    } catch (Throwable th2) {
                                                        CloseableKt.closeFinally(cursorRawQuery4, th);
                                                        throw th2;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0632\u0628\u0648\u0646 \u0627\u0644\u0648\u0627\u0631\u062f\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
                            }
                            writableDatabase.endTransaction();
                        } catch (Throwable th3) {
                            try {
                                throw th3;
                            } catch (Throwable th4) {
                                CloseableKt.closeFinally(cursorRawQuery3, th3);
                                throw th4;
                            }
                        }
                    } catch (Throwable th5) {
                        writableDatabase.endTransaction();
                        throw th5;
                    }
                } catch (Throwable th6) {
                    try {
                        throw th6;
                    } catch (Throwable th7) {
                        CloseableKt.closeFinally(cursorRawQuery2, th6);
                        throw th7;
                    }
                }
            } catch (Throwable th8) {
                try {
                    throw th8;
                } catch (Throwable th9) {
                    CloseableKt.closeFinally(cursorRawQuery, th8);
                    throw th9;
                }
            }
        }
        return applyResult;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final ApplyResult applyReceivedCustomerActive(String operationId) {
        ApplyResult applyResult;
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        synchronized (APPLY_LOCK) {
            if (StringsKt.isBlank(operationId)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
            Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='authority_device_id'", (String[]) null);
            try {
                Cursor cursor = cursorRawQuery;
                String string = cursor.moveToFirst() ? cursor.getString(0) : null;
                CloseableKt.closeFinally(cursorRawQuery, null);
                String str = string;
                if (str != null && !StringsKt.isBlank(str)) {
                    Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", (String[]) null);
                    try {
                        Cursor cursor2 = cursorRawQuery2;
                        Object[] objArr = cursor2.moveToFirst() && Intrinsics.areEqual(cursor2.getString(0), "SECONDARY");
                        CloseableKt.closeFinally(cursorRawQuery2, null);
                        if (objArr != false) {
                            writableDatabase.beginTransaction();
                            try {
                                Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT origin_device_id,entity_type,entity_id,payload_json,status FROM sync_inbox WHERE operation_id=?", new String[]{operationId});
                                try {
                                    Cursor cursor3 = cursorRawQuery3;
                                    if (!cursor3.moveToFirst()) {
                                        throw new IllegalArgumentException("\u062a\u062d\u062f\u064a\u062b \u062d\u0627\u0644\u0629 \u0627\u0644\u0632\u0628\u0648\u0646 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
                                    }
                                    String[] strArr = new String[5];
                                    for (int i = 0; i < 5; i++) {
                                        strArr[i] = cursor3.getString(i);
                                    }
                                    CloseableKt.closeFinally(cursorRawQuery3, null);
                                    if (!Intrinsics.areEqual(strArr[0], string) || !Intrinsics.areEqual(strArr[1], "CUSTOMER")) {
                                        throw new IllegalArgumentException("\u0645\u0635\u062f\u0631 \u062a\u062d\u062f\u064a\u062b \u062d\u0627\u0644\u0629 \u0627\u0644\u0632\u0628\u0648\u0646 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                    }
                                    if (Intrinsics.areEqual(strArr[4], "APPLIED")) {
                                        writableDatabase.setTransactionSuccessful();
                                        applyResult = new ApplyResult(operationId, true, null, 4, null);
                                    } else {
                                        if (!Intrinsics.areEqual(strArr[4], "RECEIVED")) {
                                            throw new IllegalArgumentException("Failed requirement.".toString());
                                        }
                                        JSONObject jSONObject = new JSONObject(strArr[3]);
                                        if (!Intrinsics.areEqual(jSONObject.optString("business_operation"), "SET_CUSTOMER_ACTIVE") || !Intrinsics.areEqual(jSONObject.optString("customer_id"), strArr[2])) {
                                            throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u062a\u062d\u062f\u064a\u062b \u062d\u0627\u0644\u0629 \u0627\u0644\u0632\u0628\u0648\u0646 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
                                        }
                                        ContentValues contentValues = new ContentValues();
                                        contentValues.put("is_active", Integer.valueOf(jSONObject.optBoolean("is_active") ? 1 : 0));
                                        contentValues.put("updated_at", jSONObject.optString("occurred_at", timestamp()));
                                        Unit unit = Unit.INSTANCE;
                                        if (writableDatabase.update("customers", contentValues, "id=?", new String[]{strArr[2]}) != 1) {
                                            throw new IllegalArgumentException("\u0627\u0644\u0632\u0628\u0648\u0646 \u0627\u0644\u0645\u0637\u0644\u0648\u0628 \u062a\u062d\u062f\u064a\u062b\u0647 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a".toString());
                                        }
                                        ContentValues contentValues2 = new ContentValues();
                                        contentValues2.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                        contentValues2.put("applied_at", timestamp());
                                        contentValues2.putNull("rejection_reason");
                                        Unit unit2 = Unit.INSTANCE;
                                        if (writableDatabase.update("sync_inbox", contentValues2, "operation_id=? AND status='RECEIVED'", new String[]{operationId}) != 1) {
                                            throw new IllegalArgumentException("Failed requirement.".toString());
                                        }
                                        writableDatabase.setTransactionSuccessful();
                                        applyResult = new ApplyResult(operationId, true, null, 4, null);
                                    }
                                    writableDatabase.endTransaction();
                                } catch (Throwable th) {
                                    try {
                                        throw th;
                                    } catch (Throwable th2) {
                                        CloseableKt.closeFinally(cursorRawQuery3, th);
                                        throw th2;
                                    }
                                }
                            } catch (Throwable th3) {
                                writableDatabase.endTransaction();
                                throw th3;
                            }
                        }
                    } catch (Throwable th4) {
                        try {
                            throw th4;
                        } catch (Throwable th5) {
                            CloseableKt.closeFinally(cursorRawQuery2, th4);
                            throw th5;
                        }
                    }
                }
                throw new IllegalArgumentException("\u062a\u062d\u062f\u064a\u062b \u062d\u0627\u0644\u0629 \u0627\u0644\u0632\u0628\u0648\u0646 \u0627\u0644\u0648\u0627\u0631\u062f \u0645\u0633\u0645\u0648\u062d \u0639\u0644\u0649 \u062c\u0647\u0627\u0632 \u062b\u0627\u0646\u0648\u064a \u0645\u0631\u062a\u0628\u0637 \u0641\u0642\u0637".toString());
            } catch (Throwable th6) {
                try {
                    throw th6;
                } catch (Throwable th7) {
                    CloseableKt.closeFinally(cursorRawQuery, th6);
                    throw th7;
                }
            }
        }
        return applyResult;
    }

    /* JADX WARN: Code duplicated, block: B:333:0x088a A[Catch: all -> 0x08da, TRY_LEAVE, TryCatch #38 {all -> 0x08da, blocks: (B:331:0x085f, B:333:0x088a, B:337:0x089e, B:338:0x08a9, B:315:0x0841, B:328:0x085a, B:329:0x085d, B:344:0x08b0, B:345:0x08b3, B:346:0x08b4, B:347:0x08c0, B:359:0x08d6, B:360:0x08d9, B:366:0x08e2, B:367:0x08ee, B:373:0x08f5, B:374:0x08f8, B:341:0x08ad, B:370:0x08f2, B:356:0x08d3, B:325:0x0857), top: B:510:0x0252, inners: #8, #11, #33, #36 }] */
    /* JADX WARN: Code duplicated, block: B:337:0x089e A[Catch: all -> 0x08da, TRY_ENTER, TryCatch #38 {all -> 0x08da, blocks: (B:331:0x085f, B:333:0x088a, B:337:0x089e, B:338:0x08a9, B:315:0x0841, B:328:0x085a, B:329:0x085d, B:344:0x08b0, B:345:0x08b3, B:346:0x08b4, B:347:0x08c0, B:359:0x08d6, B:360:0x08d9, B:366:0x08e2, B:367:0x08ee, B:373:0x08f5, B:374:0x08f8, B:341:0x08ad, B:370:0x08f2, B:356:0x08d3, B:325:0x0857), top: B:510:0x0252, inners: #8, #11, #33, #36 }] */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x024a, code lost:
    
        if (r13 != false) goto L110;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v0, types: [java.lang.Object, net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r14v1, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.hazem.dailystore.data.SyncDomainApplier] */
    /* JADX WARN: Type inference failed for: r1v125 */
    /* JADX WARN: Type inference failed for: r1v126 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v23 */
    /* JADX WARN: Type inference failed for: r1v35 */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v42 */
    /* JADX WARN: Type inference failed for: r8v91 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final ApplyResult applyReceivedProduct(String operationId) throws Throwable {
        Throwable th;
        Throwable th2;
        String string;
        Throwable th3;
        String string2;
        String str;
        Throwable th4;
        ?? r1;
        Throwable th5;
        ApplyResult applyResult;
        ?? r8;
        String str2;
        Throwable th6;
        Pair pair;
        Throwable th7;
        Pair pair2;
        long jLongValue;
        long jLongValue2;
        Long lValueOf;
        Triple triple;
        Throwable th8;
        long jLongValue3;
        Throwable th9;
        Pair pair3;
        ContentValues contentValues;
        ?? writableDatabase = this;
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        Object obj = APPLY_LOCK;
        synchronized (obj) {
            try {
                try {
                    if (StringsKt.isBlank(operationId)) {
                        throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0645\u0637\u0644\u0648\u0628".toString());
                    }
                    writableDatabase = writableDatabase.database.getWritableDatabase();
                    Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
                    SyncDeviceIdentity.Companion companion = SyncDeviceIdentity.INSTANCE;
                    Context appContext = writableDatabase.appContext;
                    Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
                    String orCreate = companion.getOrCreate(appContext);
                    Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", null);
                    try {
                        Cursor cursor = cursorRawQuery;
                        if (cursor.moveToFirst()) {
                            try {
                                string = cursor.getString(0);
                            } catch (Throwable th10) {
                                th2 = th10;
                                try {
                                    throw th2;
                                } catch (Throwable th11) {
                                    CloseableKt.closeFinally(cursorRawQuery, th2);
                                    throw th11;
                                }
                            }
                        } else {
                            string = null;
                        }
                        CloseableKt.closeFinally(cursorRawQuery, null);
                        Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='authority_device_id'", null);
                        try {
                            Cursor cursor2 = cursorRawQuery2;
                            if (cursor2.moveToFirst()) {
                                try {
                                    string2 = cursor2.getString(0);
                                } catch (Throwable th12) {
                                    th3 = th12;
                                    try {
                                        throw th3;
                                    } catch (Throwable th13) {
                                        CloseableKt.closeFinally(cursorRawQuery2, th3);
                                        throw th13;
                                    }
                                }
                            } else {
                                string2 = null;
                            }
                            CloseableKt.closeFinally(cursorRawQuery2, null);
                            if (!Intrinsics.areEqual(string, "SECONDARY") || (str = string2) == null || StringsKt.isBlank(str) || Intrinsics.areEqual(string2, orCreate)) {
                                throw new IllegalArgumentException("\u062a\u0637\u0628\u064a\u0642 \u062f\u0644\u064a\u0644 \u0627\u0644\u0645\u0646\u062a\u062c\u0627\u062a \u0627\u0644\u0648\u0627\u0631\u062f \u0645\u0633\u0645\u0648\u062d \u0639\u0644\u0649 \u062c\u0647\u0627\u0632 \u062b\u0627\u0646\u0648\u064a \u0645\u0631\u062a\u0628\u0637 \u0641\u0642\u0637".toString());
                            }
                            writableDatabase.beginTransaction();
                            try {
                                try {
                                    Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT origin_device_id,operation_type,entity_type,entity_id,payload_json,status,origin_created_at\n                   FROM sync_inbox WHERE operation_id=?", new String[]{operationId});
                                    try {
                                        Cursor cursor3 = cursorRawQuery3;
                                        if (!cursor3.moveToFirst()) {
                                            try {
                                                throw new IllegalArgumentException("\u0639\u0645\u0644\u064a\u0629 \u0627\u0644\u0645\u0646\u062a\u062c \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629 \u0641\u064a \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0648\u0627\u0631\u062f".toString());
                                            } catch (Throwable th14) {
                                                th = th14;
                                            }
                                        } else {
                                            String[] strArr = new String[7];
                                            for (int i = 0; i < 7; i++) {
                                                try {
                                                    strArr[i] = cursor3.getString(i);
                                                } catch (Throwable th15) {
                                                    th5 = th15;
                                                    writableDatabase = writableDatabase;
                                                }
                                            }
                                            CloseableKt.closeFinally(cursorRawQuery3, null);
                                            if (!Intrinsics.areEqual(strArr[0], string2) || !Intrinsics.areEqual(strArr[2], "PRODUCT") || !SetsKt.setOf((Object[]) new String[]{"CREATE", "UPDATE"}).contains(strArr[1])) {
                                                throw new IllegalArgumentException("\u0645\u0635\u062f\u0631 \u0623\u0648 \u0646\u0648\u0639 \u062d\u062f\u062b \u0627\u0644\u0645\u0646\u062a\u062c \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                            }
                                            if (Intrinsics.areEqual(strArr[5], "APPLIED")) {
                                                try {
                                                    writableDatabase.setTransactionSuccessful();
                                                    applyResult = new ApplyResult(operationId, true, null, 4, null);
                                                    try {
                                                        writableDatabase.endTransaction();
                                                    } catch (Throwable th16) {
                                                        th = th16;
                                                        obj = obj;
                                                    }
                                                } catch (Throwable th17) {
                                                    th4 = th17;
                                                    r1 = writableDatabase;
                                                    r1.endTransaction();
                                                    throw th4;
                                                }
                                            } else {
                                                if (!Intrinsics.areEqual(strArr[5], "RECEIVED")) {
                                                    throw new IllegalArgumentException("\u062d\u0627\u0644\u0629 \u062d\u062f\u062b \u0627\u0644\u0645\u0646\u062a\u062c \u0644\u0627 \u062a\u0633\u0645\u062d \u0628\u0627\u0644\u062a\u0637\u0628\u064a\u0642".toString());
                                                }
                                                JSONObject jSONObject = new JSONObject(strArr[4]);
                                                if (!Intrinsics.areEqual(jSONObject.optString("business_operation"), "UPSERT_PRODUCT")) {
                                                    throw new IllegalArgumentException("\u0646\u0648\u0639 \u062d\u062f\u062b \u0627\u0644\u0645\u0646\u062a\u062c \u0627\u0644\u0648\u0627\u0631\u062f \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                                }
                                                String strOptString = jSONObject.optString("product_id");
                                                String strOptString2 = jSONObject.optString("name");
                                                Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
                                                String string3 = StringsKt.trim((CharSequence) strOptString2).toString();
                                                String strOptString3 = jSONObject.optString("unit_name");
                                                Intrinsics.checkNotNullExpressionValue(strOptString3, "optString(...)");
                                                String string4 = StringsKt.trim((CharSequence) strOptString3).toString();
                                                String strOptString4 = jSONObject.optString("unit_symbol");
                                                Intrinsics.checkNotNullExpressionValue(strOptString4, "optString(...)");
                                                String string5 = StringsKt.trim((CharSequence) strOptString4).toString();
                                                int iOptInt = jSONObject.optInt("unit_decimal_places", -1);
                                                long jOptLong = jSONObject.optLong("purchase_cost_usd_cents", -1L);
                                                long jOptLong2 = jSONObject.optLong("sale_price_usd_cents", -1L);
                                                boolean zOptBoolean = jSONObject.optBoolean("tracks_inventory", false);
                                                String str3 = string2;
                                                try {
                                                    double dOptDouble = jSONObject.optDouble("opening_quantity", 0.0d);
                                                    String strOptString5 = jSONObject.optString("created_at");
                                                    Intrinsics.checkNotNull(strOptString5);
                                                    if (!new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(strOptString5)) {
                                                        strOptString5 = null;
                                                    }
                                                    if (strOptString5 == null) {
                                                        try {
                                                            strOptString5 = strArr[6];
                                                        } catch (Throwable th18) {
                                                            th4 = th18;
                                                            r1 = writableDatabase;
                                                            r1.endTransaction();
                                                            throw th4;
                                                        }
                                                    }
                                                    String strOptString6 = jSONObject.optString("updated_at");
                                                    Intrinsics.checkNotNull(strOptString6);
                                                    String str4 = strOptString6;
                                                    if (!new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(strOptString6)) {
                                                        str4 = null;
                                                    }
                                                    String str5 = str4 == null ? strOptString5 : str4;
                                                    Intrinsics.checkNotNull(strOptString);
                                                    if (!StringsKt.isBlank(strOptString) && Intrinsics.areEqual(strOptString, strArr[3]) && string3.length() > 0 && string3.length() <= 200 && string4.length() > 0 && string5.length() > 0 && iOptInt >= 0) {
                                                        writableDatabase = 4;
                                                        if (iOptInt < 4) {
                                                            if (jOptLong >= 0) {
                                                                if (jOptLong2 > 0) {
                                                                    if (zOptBoolean) {
                                                                        if (jOptLong > 0) {
                                                                        }
                                                                    }
                                                                    if (Math.abs(dOptDouble) <= Double.MAX_VALUE) {
                                                                        if (dOptDouble >= 0.0d) {
                                                                            if (dOptDouble != 0.0d) {
                                                                            }
                                                                            try {
                                                                                if (jSONObject.isNull("primary_category_id")) {
                                                                                    if (!jSONObject.isNull("category_name")) {
                                                                                        throw new IllegalArgumentException("\u0627\u0633\u0645 \u0627\u0644\u062a\u0635\u0646\u064a\u0641 \u0645\u0648\u062c\u0648\u062f \u062f\u0648\u0646 \u0645\u0639\u0631\u0651\u0641 \u0623\u0635\u0644\u064a".toString());
                                                                                    }
                                                                                    strOptString5 = strOptString5;
                                                                                    jOptLong2 = jOptLong2;
                                                                                    str2 = str3;
                                                                                    str5 = str5;
                                                                                    lValueOf = null;
                                                                                } else {
                                                                                    long jOptLong3 = jSONObject.optLong("primary_category_id", 0L);
                                                                                    String strOptString7 = jSONObject.optString("category_name");
                                                                                    Intrinsics.checkNotNullExpressionValue(strOptString7, "optString(...)");
                                                                                    String string6 = StringsKt.trim((CharSequence) strOptString7).toString();
                                                                                    if (jOptLong3 <= 0 || string6.length() <= 0) {
                                                                                        throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u062a\u0635\u0646\u064a\u0641 \u0627\u0644\u0645\u0646\u062a\u062c \u063a\u064a\u0631 \u0645\u0643\u062a\u0645\u0644\u0629".toString());
                                                                                    }
                                                                                    str2 = str3;
                                                                                    Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT secondary_category_id,category_name FROM sync_category_mappings\n                       WHERE primary_device_id=? AND primary_category_id=?", new String[]{str2, String.valueOf(jOptLong3)});
                                                                                    try {
                                                                                        Cursor cursor4 = cursorRawQuery4;
                                                                                        if (cursor4.moveToFirst()) {
                                                                                            try {
                                                                                                pair = TuplesKt.to(Long.valueOf(cursor4.getLong(0)), cursor4.getString(1));
                                                                                            } catch (Throwable th19) {
                                                                                                th6 = th19;
                                                                                                try {
                                                                                                    throw th6;
                                                                                                } catch (Throwable th20) {
                                                                                                    CloseableKt.closeFinally(cursorRawQuery4, th6);
                                                                                                    throw th20;
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            pair = null;
                                                                                        }
                                                                                        try {
                                                                                            CloseableKt.closeFinally(cursorRawQuery4, null);
                                                                                            if (pair != null) {
                                                                                                if (!StringsKt.equals((String) pair.getSecond(), string6, true)) {
                                                                                                    throw new IllegalArgumentException("\u0631\u0628\u0637 \u062a\u0635\u0646\u064a\u0641 \u0627\u0644\u0645\u0646\u062a\u062c \u064a\u062a\u0639\u0627\u0631\u0636 \u0645\u0639 \u0627\u0644\u0627\u0633\u0645 \u0627\u0644\u0623\u0635\u0644\u064a".toString());
                                                                                                }
                                                                                                jLongValue2 = ((Number) pair.getFirst()).longValue();
                                                                                            } else {
                                                                                                Locale ROOT = Locale.ROOT;
                                                                                                Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
                                                                                                String lowerCase = string6.toLowerCase(ROOT);
                                                                                                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                                                                                                Cursor cursorRawQuery5 = writableDatabase.rawQuery("SELECT id,name FROM categories WHERE lower(trim(name))=? LIMIT 1", new String[]{lowerCase});
                                                                                                try {
                                                                                                    Cursor cursor5 = cursorRawQuery5;
                                                                                                    if (cursor5.moveToFirst()) {
                                                                                                        try {
                                                                                                            pair2 = TuplesKt.to(Long.valueOf(cursor5.getLong(0)), cursor5.getString(1));
                                                                                                        } catch (Throwable th21) {
                                                                                                            th7 = th21;
                                                                                                            try {
                                                                                                                throw th7;
                                                                                                            } catch (Throwable th22) {
                                                                                                                CloseableKt.closeFinally(cursorRawQuery5, th7);
                                                                                                                throw th22;
                                                                                                            }
                                                                                                        }
                                                                                                    } else {
                                                                                                        pair2 = null;
                                                                                                    }
                                                                                                    CloseableKt.closeFinally(cursorRawQuery5, null);
                                                                                                    if (pair2 == null) {
                                                                                                        ContentValues contentValues2 = new ContentValues();
                                                                                                        contentValues2.put("name", string6);
                                                                                                        contentValues2.put("created_at", strOptString5);
                                                                                                        Unit unit = Unit.INSTANCE;
                                                                                                        jLongValue = writableDatabase.insertOrThrow("categories", null, contentValues2);
                                                                                                    } else {
                                                                                                        if (!StringsKt.equals((String) pair2.getSecond(), string6, true)) {
                                                                                                            throw new IllegalArgumentException("\u0627\u0633\u0645 \u062a\u0635\u0646\u064a\u0641 \u0627\u0644\u0645\u0646\u062a\u062c \u064a\u062a\u0639\u0627\u0631\u0636 \u0645\u0639 \u062a\u0635\u0646\u064a\u0641 \u0645\u062d\u0644\u064a".toString());
                                                                                                        }
                                                                                                        jLongValue = ((Number) pair2.getFirst()).longValue();
                                                                                                    }
                                                                                                    ContentValues contentValues3 = new ContentValues();
                                                                                                    contentValues3.put("primary_device_id", str2);
                                                                                                    contentValues3.put("primary_category_id", Long.valueOf(jOptLong3));
                                                                                                    contentValues3.put("secondary_category_id", Long.valueOf(jLongValue));
                                                                                                    contentValues3.put("category_name", string6);
                                                                                                    contentValues3.put("mapped_at", timestamp());
                                                                                                    Unit unit2 = Unit.INSTANCE;
                                                                                                    writableDatabase.insertOrThrow("sync_category_mappings", null, contentValues3);
                                                                                                    jLongValue2 = jLongValue;
                                                                                                } catch (Throwable th23) {
                                                                                                    th7 = th23;
                                                                                                }
                                                                                            }
                                                                                            lValueOf = Long.valueOf(jLongValue2);
                                                                                        } catch (Throwable th24) {
                                                                                            th = th24;
                                                                                            r8 = writableDatabase;
                                                                                            th4 = th;
                                                                                            r1 = r8;
                                                                                            r1.endTransaction();
                                                                                            throw th4;
                                                                                        }
                                                                                    } catch (Throwable th25) {
                                                                                        th6 = th25;
                                                                                    }
                                                                                }
                                                                                boolean zHas = jSONObject.has("primary_unit_id");
                                                                                long jOptLong4 = jSONObject.optLong("primary_unit_id", 0L);
                                                                                if (zHas && (jSONObject.isNull("primary_unit_id") || jOptLong4 <= 0)) {
                                                                                    throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0648\u062d\u062f\u0629 \u0627\u0644\u0623\u0635\u0644\u064a \u0641\u064a \u0627\u0644\u0645\u0646\u062a\u062c \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                                                                }
                                                                                if (zHas) {
                                                                                    Cursor cursorRawQuery6 = writableDatabase.rawQuery("SELECT secondary_unit_id,unit_name,unit_symbol,decimal_places\n                       FROM sync_unit_mappings WHERE primary_device_id=? AND primary_unit_id=?", new String[]{str2, String.valueOf(jOptLong4)});
                                                                                    try {
                                                                                        Cursor cursor6 = cursorRawQuery6;
                                                                                        if (!cursor6.moveToFirst()) {
                                                                                            throw new IllegalArgumentException("\u0644\u0645 \u062a\u0635\u0644 \u0627\u0644\u0648\u062d\u062f\u0629 \u0627\u0644\u0623\u0635\u0644\u064a\u0629 \u0642\u0628\u0644 \u0627\u0644\u0645\u0646\u062a\u062c\u061b \u0623\u0648\u0642\u0641 \u0627\u0644\u062a\u0637\u0628\u064a\u0642 \u0648\u0627\u0646\u062a\u0638\u0631 \u0645\u0632\u0627\u0645\u0646\u0629 \u0627\u0644\u0648\u062d\u062f\u0627\u062a".toString());
                                                                                        }
                                                                                        String[] strArr2 = {String.valueOf(cursor6.getLong(0)), cursor6.getString(1), cursor6.getString(2), String.valueOf(cursor6.getInt(3))};
                                                                                        CloseableKt.closeFinally(cursorRawQuery6, null);
                                                                                        if (!Intrinsics.areEqual(strArr2[1], string4) || !Intrinsics.areEqual(strArr2[2], string5) || !Intrinsics.areEqual(strArr2[3], String.valueOf(iOptInt))) {
                                                                                            throw new IllegalArgumentException("\u062a\u0639\u0631\u064a\u0641 \u0648\u062d\u062f\u0629 \u0627\u0644\u0645\u0646\u062a\u062c \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u0631\u0628\u0637 \u0627\u0644\u0648\u062d\u062f\u0629 \u0627\u0644\u0623\u0635\u0644\u064a".toString());
                                                                                        }
                                                                                        Cursor cursorRawQuery7 = writableDatabase.rawQuery("SELECT name,symbol,decimal_places FROM units WHERE id=?", new String[]{strArr2[0]});
                                                                                        try {
                                                                                            Cursor cursor7 = cursorRawQuery7;
                                                                                            if (!cursor7.moveToFirst()) {
                                                                                                throw new IllegalArgumentException("\u0627\u0644\u0648\u062d\u062f\u0629 \u0627\u0644\u0645\u062d\u0644\u064a\u0629 \u0627\u0644\u0645\u0631\u062a\u0628\u0637\u0629 \u0628\u0627\u0644\u0645\u0646\u062a\u062c \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629".toString());
                                                                                            }
                                                                                            String[] strArr3 = {cursor7.getString(0), cursor7.getString(1), String.valueOf(cursor7.getInt(2))};
                                                                                            CloseableKt.closeFinally(cursorRawQuery7, null);
                                                                                            if (!Intrinsics.areEqual(strArr3[0], string4) || !Intrinsics.areEqual(strArr3[1], string5) || !Intrinsics.areEqual(strArr3[2], String.valueOf(iOptInt))) {
                                                                                                throw new IllegalArgumentException("\u062a\u0639\u0631\u064a\u0641 \u0627\u0644\u0648\u062d\u062f\u0629 \u0627\u0644\u0645\u062d\u0644\u064a\u0629 \u0627\u0644\u0645\u0631\u062a\u0628\u0637 \u0628\u0627\u0644\u0645\u0646\u062a\u062c \u062a\u063a\u064a\u0631".toString());
                                                                                            }
                                                                                            String str6 = strArr2[0];
                                                                                            Intrinsics.checkNotNullExpressionValue(str6, "get(...)");
                                                                                            jLongValue3 = Long.parseLong(str6);
                                                                                        } catch (Throwable th26) {
                                                                                            try {
                                                                                                throw th26;
                                                                                            } catch (Throwable th27) {
                                                                                                CloseableKt.closeFinally(cursorRawQuery7, th26);
                                                                                                throw th27;
                                                                                            }
                                                                                        }
                                                                                    } catch (Throwable th28) {
                                                                                        try {
                                                                                            throw th28;
                                                                                        } catch (Throwable th29) {
                                                                                            CloseableKt.closeFinally(cursorRawQuery6, th28);
                                                                                            throw th29;
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    Locale ROOT2 = Locale.ROOT;
                                                                                    Intrinsics.checkNotNullExpressionValue(ROOT2, "ROOT");
                                                                                    String lowerCase2 = string4.toLowerCase(ROOT2);
                                                                                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                                                                                    Cursor cursorRawQuery8 = writableDatabase.rawQuery("SELECT id,symbol,decimal_places FROM units WHERE lower(trim(name))=? LIMIT 2", new String[]{lowerCase2});
                                                                                    try {
                                                                                        Cursor cursor8 = cursorRawQuery8;
                                                                                        if (cursor8.moveToFirst()) {
                                                                                            triple = new Triple(Long.valueOf(cursor8.getLong(0)), cursor8.getString(1), Integer.valueOf(cursor8.getInt(2)));
                                                                                            if (cursor8.moveToNext()) {
                                                                                                try {
                                                                                                    throw new IllegalArgumentException("\u0627\u0633\u0645 \u0648\u062d\u062f\u0629 \u0627\u0644\u0645\u0646\u062a\u062c \u0645\u0643\u0631\u0631 \u0645\u062d\u0644\u064a\u0627\u064b".toString());
                                                                                                } catch (Throwable th30) {
                                                                                                    th = th30;
                                                                                                    Throwable th31 = th;
                                                                                                    try {
                                                                                                        throw th31;
                                                                                                    } catch (Throwable th32) {
                                                                                                        CloseableKt.closeFinally(cursorRawQuery8, th31);
                                                                                                        throw th32;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                            th8 = null;
                                                                                        } else {
                                                                                            th8 = null;
                                                                                            triple = null;
                                                                                        }
                                                                                        CloseableKt.closeFinally(cursorRawQuery8, th8);
                                                                                        if (triple == null) {
                                                                                            ContentValues contentValues4 = new ContentValues();
                                                                                            contentValues4.put("name", string4);
                                                                                            contentValues4.put("symbol", string5);
                                                                                            contentValues4.put("decimal_places", Integer.valueOf(iOptInt));
                                                                                            Unit unit3 = Unit.INSTANCE;
                                                                                            jLongValue3 = writableDatabase.insertOrThrow("units", null, contentValues4);
                                                                                        } else {
                                                                                            if (!Intrinsics.areEqual(triple.getSecond(), string5) || ((Number) triple.getThird()).intValue() != iOptInt) {
                                                                                                throw new IllegalArgumentException("\u062a\u0639\u0631\u064a\u0641 \u0648\u062d\u062f\u0629 \u0627\u0644\u0645\u0646\u062a\u062c \u064a\u062e\u062a\u0644\u0641 \u0639\u0646 \u0627\u0644\u0648\u062d\u062f\u0629 \u0627\u0644\u0645\u062d\u0644\u064a\u0629\u061b \u0623\u0648\u0642\u0641 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u0644\u0644\u0645\u0631\u0627\u062c\u0639\u0629".toString());
                                                                                            }
                                                                                            jLongValue3 = ((Number) triple.getFirst()).longValue();
                                                                                        }
                                                                                    } catch (Throwable th33) {
                                                                                        th = th33;
                                                                                    }
                                                                                }
                                                                                Cursor cursorRawQuery9 = writableDatabase.rawQuery("SELECT unit_id,track_inventory FROM products WHERE id=?", new String[]{strOptString});
                                                                                try {
                                                                                    Cursor cursor9 = cursorRawQuery9;
                                                                                    if (cursor9.moveToFirst()) {
                                                                                        try {
                                                                                            pair3 = TuplesKt.to(Long.valueOf(cursor9.getLong(0)), Boolean.valueOf(cursor9.getInt(1) == 1));
                                                                                        } catch (Throwable th34) {
                                                                                            th9 = th34;
                                                                                            try {
                                                                                                throw th9;
                                                                                            } catch (Throwable th35) {
                                                                                                CloseableKt.closeFinally(cursorRawQuery9, th9);
                                                                                                throw th35;
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        pair3 = null;
                                                                                    }
                                                                                    CloseableKt.closeFinally(cursorRawQuery9, null);
                                                                                    if (pair3 != null) {
                                                                                        Cursor cursorRawQuery10 = writableDatabase.rawQuery("SELECT 1 FROM inventory_movements WHERE product_id=? LIMIT 1", new String[]{strOptString});
                                                                                        try {
                                                                                            boolean zMoveToFirst = cursorRawQuery10.moveToFirst();
                                                                                            CloseableKt.closeFinally(cursorRawQuery10, null);
                                                                                            Cursor cursorRawQuery11 = writableDatabase.rawQuery("SELECT COALESCE(SUM(quantity),0) FROM inventory_balances WHERE product_id=?", new String[]{strOptString});
                                                                                            try {
                                                                                                Cursor cursor10 = cursorRawQuery11;
                                                                                                cursor10.moveToFirst();
                                                                                                double d = cursor10.getDouble(0);
                                                                                                CloseableKt.closeFinally(cursorRawQuery11, null);
                                                                                                boolean z = Math.abs(d) > 1.0E-7d;
                                                                                                if ((zMoveToFirst || z) && (((Number) pair3.getFirst()).longValue() != jLongValue3 || ((Boolean) pair3.getSecond()).booleanValue() != zOptBoolean)) {
                                                                                                    throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u063a\u064a\u064a\u0631 \u0648\u062d\u062f\u0629 \u0623\u0648 \u062a\u062a\u0628\u0639 \u0645\u062e\u0632\u0648\u0646 \u0645\u0646\u062a\u062c \u0644\u0647 \u062d\u0631\u0643\u0627\u062a \u0645\u062d\u0644\u064a\u0629".toString());
                                                                                                }
                                                                                                ContentValues contentValues5 = new ContentValues();
                                                                                                contentValues5.put("name", string3);
                                                                                                if (jSONObject.isNull("barcode")) {
                                                                                                    contentValues5.putNull("barcode");
                                                                                                } else {
                                                                                                    contentValues5.put("barcode", jSONObject.getString("barcode"));
                                                                                                }
                                                                                                if (jSONObject.isNull("sku")) {
                                                                                                    contentValues5.putNull("sku");
                                                                                                } else {
                                                                                                    contentValues5.put("sku", jSONObject.getString("sku"));
                                                                                                }
                                                                                                if (lValueOf == null) {
                                                                                                    contentValues5.putNull("category_id");
                                                                                                } else {
                                                                                                    contentValues5.put("category_id", lValueOf);
                                                                                                }
                                                                                                contentValues5.put("unit_id", Long.valueOf(jLongValue3));
                                                                                                contentValues5.put("purchase_cost_usd_cents", Long.valueOf(jOptLong));
                                                                                                contentValues5.put("sale_price_usd_cents", Long.valueOf(jOptLong2));
                                                                                                contentValues5.put("track_inventory", Integer.valueOf(zOptBoolean ? 1 : 0));
                                                                                                contentValues5.put("is_active", Integer.valueOf(jSONObject.optBoolean("is_active", true) ? 1 : 0));
                                                                                                contentValues5.put("updated_at", str5);
                                                                                                if (writableDatabase.update("products", contentValues5, "id=?", new String[]{strOptString}) != 1) {
                                                                                                    throw new IllegalArgumentException("Failed requirement.".toString());
                                                                                                }
                                                                                            } catch (Throwable th36) {
                                                                                                try {
                                                                                                    throw th36;
                                                                                                } catch (Throwable th37) {
                                                                                                    CloseableKt.closeFinally(cursorRawQuery11, th36);
                                                                                                    throw th37;
                                                                                                }
                                                                                            }
                                                                                        } catch (Throwable th38) {
                                                                                            try {
                                                                                                throw th38;
                                                                                            } catch (Throwable th39) {
                                                                                                CloseableKt.closeFinally(cursorRawQuery10, th38);
                                                                                                throw th39;
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        String str7 = str5;
                                                                                        ContentValues contentValues6 = new ContentValues();
                                                                                        contentValues6.put("id", strOptString);
                                                                                        contentValues6.put("name", string3);
                                                                                        if (jSONObject.isNull("barcode")) {
                                                                                            contentValues6.putNull("barcode");
                                                                                        } else {
                                                                                            contentValues6.put("barcode", jSONObject.getString("barcode"));
                                                                                        }
                                                                                        if (jSONObject.isNull("sku")) {
                                                                                            contentValues6.putNull("sku");
                                                                                        } else {
                                                                                            contentValues6.put("sku", jSONObject.getString("sku"));
                                                                                        }
                                                                                        if (lValueOf == null) {
                                                                                            contentValues6.putNull("category_id");
                                                                                        } else {
                                                                                            contentValues6.put("category_id", lValueOf);
                                                                                        }
                                                                                        contentValues6.put("unit_id", Long.valueOf(jLongValue3));
                                                                                        contentValues6.put("purchase_cost_usd_cents", Long.valueOf(jOptLong));
                                                                                        contentValues6.put("sale_price_usd_cents", Long.valueOf(jOptLong2));
                                                                                        contentValues6.put("track_inventory", Integer.valueOf(zOptBoolean ? 1 : 0));
                                                                                        contentValues6.put("is_active", Integer.valueOf(jSONObject.optBoolean("is_active", true) ? 1 : 0));
                                                                                        contentValues6.put("created_at", strOptString5);
                                                                                        contentValues6.put("updated_at", str7);
                                                                                        Unit unit4 = Unit.INSTANCE;
                                                                                        writableDatabase.insertOrThrow("products", null, contentValues6);
                                                                                        if (dOptDouble > 0.0d) {
                                                                                            Cursor cursorRawQuery12 = writableDatabase.rawQuery("SELECT id FROM warehouses WHERE is_default=1 AND is_active=1 LIMIT 1", null);
                                                                                            try {
                                                                                                Cursor cursor11 = cursorRawQuery12;
                                                                                                if (!cursor11.moveToFirst()) {
                                                                                                    try {
                                                                                                        throw new IllegalArgumentException("\u0627\u0644\u0645\u0633\u062a\u0648\u062f\u0639 \u0627\u0644\u0627\u0641\u062a\u0631\u0627\u0636\u064a \u063a\u064a\u0631 \u0645\u0647\u064a\u0623 \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a".toString());
                                                                                                    } catch (Throwable th40) {
                                                                                                        th = th40;
                                                                                                    }
                                                                                                } else {
                                                                                                    String string7 = cursor11.getString(0);
                                                                                                    CloseableKt.closeFinally(cursorRawQuery12, null);
                                                                                                    String string8 = UUID.randomUUID().toString();
                                                                                                    Intrinsics.checkNotNullExpressionValue(string8, "toString(...)");
                                                                                                    ContentValues contentValues7 = new ContentValues();
                                                                                                    contentValues7.put("product_id", strOptString);
                                                                                                    contentValues7.put("warehouse_id", string7);
                                                                                                    contentValues7.put("quantity", Double.valueOf(dOptDouble));
                                                                                                    contentValues7.put("updated_at", str7);
                                                                                                    Unit unit5 = Unit.INSTANCE;
                                                                                                    writableDatabase.insertOrThrow("inventory_balances", null, contentValues7);
                                                                                                    ContentValues contentValues8 = new ContentValues();
                                                                                                    contentValues8.put("id", string8);
                                                                                                    contentValues8.put("product_id", strOptString);
                                                                                                    contentValues8.put("warehouse_id", string7);
                                                                                                    contentValues8.put("movement_type", "OPENING");
                                                                                                    contentValues8.put("quantity_delta", Double.valueOf(dOptDouble));
                                                                                                    contentValues8.put("unit_cost_usd_cents", Long.valueOf(jOptLong));
                                                                                                    contentValues8.put("source_type", "SYNC_CATALOG_OPENING");
                                                                                                    contentValues8.put("source_id", strOptString);
                                                                                                    contentValues8.put("operation_id", "SYNC_CATALOG_OPENING:" + strOptString);
                                                                                                    contentValues8.putNull("created_by");
                                                                                                    contentValues8.put("note", "\u0631\u0635\u064a\u062f \u0627\u0641\u062a\u062a\u0627\u062d\u064a \u0648\u0627\u0631\u062f \u0645\u0646 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a");
                                                                                                    contentValues8.put("created_at", str7);
                                                                                                    Unit unit6 = Unit.INSTANCE;
                                                                                                    writableDatabase.insertOrThrow("inventory_movements", null, contentValues8);
                                                                                                    FifoCosting fifoCosting = FifoCosting.INSTANCE;
                                                                                                    Intrinsics.checkNotNull(string7);
                                                                                                    Intrinsics.checkNotNull(str7);
                                                                                                    fifoCosting.addLayer(writableDatabase, string8, strOptString, string7, dOptDouble, jOptLong, "SYNC_CATALOG_OPENING", strOptString, str7, (512 & 512) != 0 ? null : null);
                                                                                                }
                                                                                            } catch (Throwable th41) {
                                                                                                th = th41;
                                                                                            }
                                                                                            Throwable th42 = th;
                                                                                            try {
                                                                                                throw th42;
                                                                                            } catch (Throwable th43) {
                                                                                                CloseableKt.closeFinally(cursorRawQuery12, th42);
                                                                                                throw th43;
                                                                                            }
                                                                                        }
                                                                                        contentValues = new ContentValues();
                                                                                        contentValues.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                                                                        contentValues.put("applied_at", timestamp());
                                                                                        contentValues.putNull("rejection_reason");
                                                                                        Unit unit7 = Unit.INSTANCE;
                                                                                        if (writableDatabase.update("sync_inbox", contentValues, "operation_id=? AND status='RECEIVED'", new String[]{operationId}) == 1) {
                                                                                            throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062b\u0628\u064a\u062a \u062a\u0637\u0628\u064a\u0642 \u0627\u0644\u0645\u0646\u062a\u062c".toString());
                                                                                        }
                                                                                        writableDatabase.setTransactionSuccessful();
                                                                                        applyResult = new ApplyResult(operationId, true, null, 4, null);
                                                                                        writableDatabase.endTransaction();
                                                                                    }
                                                                                    contentValues = new ContentValues();
                                                                                    contentValues.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                                                                    contentValues.put("applied_at", timestamp());
                                                                                    contentValues.putNull("rejection_reason");
                                                                                    Unit unit8 = Unit.INSTANCE;
                                                                                    if (writableDatabase.update("sync_inbox", contentValues, "operation_id=? AND status='RECEIVED'", new String[]{operationId}) == 1) {
                                                                                        throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062b\u0628\u064a\u062a \u062a\u0637\u0628\u064a\u0642 \u0627\u0644\u0645\u0646\u062a\u062c".toString());
                                                                                    }
                                                                                    writableDatabase.setTransactionSuccessful();
                                                                                    applyResult = new ApplyResult(operationId, true, null, 4, null);
                                                                                    writableDatabase.endTransaction();
                                                                                } catch (Throwable th44) {
                                                                                    th9 = th44;
                                                                                }
                                                                            } catch (Throwable th45) {
                                                                                th = th45;
                                                                                r8 = writableDatabase;
                                                                                th4 = th;
                                                                                r1 = r8;
                                                                                r1.endTransaction();
                                                                                throw th4;
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                    throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0645\u0646\u062a\u062c \u0623\u0648 \u0627\u0644\u0648\u062d\u062f\u0629 \u0623\u0648 \u0627\u0644\u0623\u0633\u0639\u0627\u0631 \u0623\u0648 \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0627\u0641\u062a\u062a\u0627\u062d\u064a \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
                                                } catch (Throwable th46) {
                                                    th = th46;
                                                    writableDatabase = writableDatabase;
                                                    th4 = th;
                                                    r1 = writableDatabase;
                                                    r1.endTransaction();
                                                    throw th4;
                                                }
                                            }
                                        }
                                    } catch (Throwable th47) {
                                        th = th47;
                                    }
                                    th5 = th;
                                    writableDatabase = writableDatabase;
                                    try {
                                        throw th5;
                                    } catch (Throwable th48) {
                                        CloseableKt.closeFinally(cursorRawQuery3, th5);
                                        throw th48;
                                    }
                                } catch (Throwable th49) {
                                    th = th49;
                                    th4 = th;
                                    r1 = writableDatabase;
                                    r1.endTransaction();
                                    throw th4;
                                }
                            } catch (Throwable th50) {
                                th = th50;
                            }
                        } catch (Throwable th51) {
                            th3 = th51;
                        }
                    } catch (Throwable th52) {
                        th2 = th52;
                    }
                    th = th;
                } catch (Throwable th53) {
                    th = th53;
                    th = th;
                }
            } catch (Throwable th54) {
                th = th54;
                obj = obj;
            }
            throw th;
        }
        return applyResult;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final ApplyResult applyReceivedProductActive(String operationId) {
        ApplyResult applyResult;
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        synchronized (APPLY_LOCK) {
            if (StringsKt.isBlank(operationId)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
            Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='authority_device_id'", (String[]) null);
            try {
                Cursor cursor = cursorRawQuery;
                String string = cursor.moveToFirst() ? cursor.getString(0) : null;
                CloseableKt.closeFinally(cursorRawQuery, null);
                String str = string;
                if (str != null && !StringsKt.isBlank(str)) {
                    Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", (String[]) null);
                    try {
                        Cursor cursor2 = cursorRawQuery2;
                        Object[] objArr = cursor2.moveToFirst() && Intrinsics.areEqual(cursor2.getString(0), "SECONDARY");
                        CloseableKt.closeFinally(cursorRawQuery2, null);
                        if (objArr != false) {
                            writableDatabase.beginTransaction();
                            try {
                                Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT origin_device_id,entity_type,entity_id,payload_json,status FROM sync_inbox WHERE operation_id=?", new String[]{operationId});
                                try {
                                    Cursor cursor3 = cursorRawQuery3;
                                    if (!cursor3.moveToFirst()) {
                                        throw new IllegalArgumentException("\u062a\u062d\u062f\u064a\u062b \u0627\u0644\u0645\u0646\u062a\u062c \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
                                    }
                                    String[] strArr = new String[5];
                                    for (int i = 0; i < 5; i++) {
                                        strArr[i] = cursor3.getString(i);
                                    }
                                    CloseableKt.closeFinally(cursorRawQuery3, null);
                                    if (!Intrinsics.areEqual(strArr[0], string) || !Intrinsics.areEqual(strArr[1], "PRODUCT")) {
                                        throw new IllegalArgumentException("\u0645\u0635\u062f\u0631 \u062a\u062d\u062f\u064a\u062b \u0627\u0644\u0645\u0646\u062a\u062c \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                    }
                                    if (Intrinsics.areEqual(strArr[4], "APPLIED")) {
                                        writableDatabase.setTransactionSuccessful();
                                        applyResult = new ApplyResult(operationId, true, null, 4, null);
                                    } else {
                                        if (!Intrinsics.areEqual(strArr[4], "RECEIVED")) {
                                            throw new IllegalArgumentException("Failed requirement.".toString());
                                        }
                                        JSONObject jSONObject = new JSONObject(strArr[3]);
                                        if (!Intrinsics.areEqual(jSONObject.optString("business_operation"), "SET_PRODUCT_ACTIVE") || !Intrinsics.areEqual(jSONObject.optString("product_id"), strArr[2])) {
                                            throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u062a\u062d\u062f\u064a\u062b \u062d\u0627\u0644\u0629 \u0627\u0644\u0645\u0646\u062a\u062c \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
                                        }
                                        ContentValues contentValues = new ContentValues();
                                        contentValues.put("is_active", Integer.valueOf(jSONObject.optBoolean("is_active") ? 1 : 0));
                                        contentValues.put("updated_at", jSONObject.optString("occurred_at", timestamp()));
                                        Unit unit = Unit.INSTANCE;
                                        if (writableDatabase.update("products", contentValues, "id=?", new String[]{strArr[2]}) != 1) {
                                            throw new IllegalArgumentException("\u0627\u0644\u0645\u0646\u062a\u062c \u0627\u0644\u0645\u0637\u0644\u0648\u0628 \u062a\u062d\u062f\u064a\u062b\u0647 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a".toString());
                                        }
                                        ContentValues contentValues2 = new ContentValues();
                                        contentValues2.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                        contentValues2.put("applied_at", timestamp());
                                        contentValues2.putNull("rejection_reason");
                                        Unit unit2 = Unit.INSTANCE;
                                        writableDatabase.update("sync_inbox", contentValues2, "operation_id=? AND status='RECEIVED'", new String[]{operationId});
                                        writableDatabase.setTransactionSuccessful();
                                        applyResult = new ApplyResult(operationId, true, null, 4, null);
                                    }
                                    writableDatabase.endTransaction();
                                } catch (Throwable th) {
                                    try {
                                        throw th;
                                    } catch (Throwable th2) {
                                        CloseableKt.closeFinally(cursorRawQuery3, th);
                                        throw th2;
                                    }
                                }
                            } catch (Throwable th3) {
                                writableDatabase.endTransaction();
                                throw th3;
                            }
                        }
                    } catch (Throwable th4) {
                        try {
                            throw th4;
                        } catch (Throwable th5) {
                            CloseableKt.closeFinally(cursorRawQuery2, th4);
                            throw th5;
                        }
                    }
                }
                throw new IllegalArgumentException("\u062a\u062d\u062f\u064a\u062b \u062d\u0627\u0644\u0629 \u0627\u0644\u0645\u0646\u062a\u062c \u0627\u0644\u0648\u0627\u0631\u062f \u0645\u0633\u0645\u0648\u062d \u0639\u0644\u0649 \u062c\u0647\u0627\u0632 \u062b\u0627\u0646\u0648\u064a \u0645\u0631\u062a\u0628\u0637 \u0641\u0642\u0637".toString());
            } catch (Throwable th6) {
                try {
                    throw th6;
                } catch (Throwable th7) {
                    CloseableKt.closeFinally(cursorRawQuery, th6);
                    throw th7;
                }
            }
        }
        return applyResult;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0 */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v2, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r11v22, types: [java.lang.Boolean, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r11v28 */
    /* JADX WARN: Type inference failed for: r11v29 */
    /* JADX WARN: Type inference failed for: r11v32 */
    /* JADX WARN: Type inference failed for: r11v35 */
    /* JADX WARN: Type inference failed for: r11v38 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r14v0, types: [java.lang.Object, net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r14v1, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    public final ApplyResult applyReceivedProductOpeningStock(String operationId) throws Throwable {
        Throwable th;
        ApplyResult applyResult;
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        synchronized (APPLY_LOCK) {
            try {
                try {
                    if (StringsKt.isBlank(operationId)) {
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                    ?? writableDatabase = this.database.getWritableDatabase();
                    Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
                    Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='authority_device_id'", null);
                    try {
                        Cursor cursor = cursorRawQuery;
                        String string = cursor.moveToFirst() ? cursor.getString(0) : null;
                        CloseableKt.closeFinally(cursorRawQuery, null);
                        String str = string;
                        if (str != null && !StringsKt.isBlank(str)) {
                            Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", null);
                            try {
                                Cursor cursor2 = cursorRawQuery2;
                                boolean z = cursor2.moveToFirst() && Intrinsics.areEqual(cursor2.getString(0), "SECONDARY");
                                CloseableKt.closeFinally(cursorRawQuery2, null);
                                if (z) {
                                    writableDatabase.beginTransaction();
                                    try {
                                        try {
                                            Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT origin_device_id,entity_type,entity_id,payload_json,status FROM sync_inbox WHERE operation_id=?", new String[]{operationId});
                                            try {
                                                Cursor cursor3 = cursorRawQuery3;
                                                if (!cursor3.moveToFirst()) {
                                                    try {
                                                        throw new IllegalArgumentException("\u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0627\u0641\u062a\u062a\u0627\u062d\u064a \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
                                                    } catch (Throwable th2) {
                                                        th = th2;
                                                    }
                                                } else {
                                                    String[] strArr = new String[5];
                                                    for (int i = 0; i < 5; i++) {
                                                        try {
                                                            strArr[i] = cursor3.getString(i);
                                                        } catch (Throwable th3) {
                                                            th = th3;
                                                            writableDatabase = writableDatabase;
                                                        }
                                                    }
                                                    CloseableKt.closeFinally(cursorRawQuery3, null);
                                                    if (!Intrinsics.areEqual(strArr[0], string) || !Intrinsics.areEqual(strArr[1], "PRODUCT_OPENING_STOCK")) {
                                                        throw new IllegalArgumentException("\u0645\u0635\u062f\u0631 \u0623\u0648 \u0646\u0648\u0639 \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0627\u0641\u062a\u062a\u0627\u062d\u064a \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                                    }
                                                    if (!Intrinsics.areEqual(strArr[4], "APPLIED")) {
                                                        if (!Intrinsics.areEqual(strArr[4], "RECEIVED")) {
                                                            throw new IllegalArgumentException("Failed requirement.".toString());
                                                        }
                                                        JSONObject jSONObject = new JSONObject(strArr[3]);
                                                        String strOptString = jSONObject.optString("product_id");
                                                        double dOptDouble = jSONObject.optDouble("quantity", -1.0d);
                                                        long jOptLong = jSONObject.optLong("unit_cost_usd_cents", 0L);
                                                        String strOptString2 = jSONObject.optString("occurred_at");
                                                        if (Intrinsics.areEqual(jSONObject.optString("business_operation"), "SET_PRODUCT_OPENING_STOCK") && Intrinsics.areEqual(strOptString, strArr[2]) && dOptDouble > 0.0d && Math.abs(dOptDouble) <= Double.MAX_VALUE && jOptLong > 0) {
                                                            Intrinsics.checkNotNull(strOptString2);
                                                            if (new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(strOptString2)) {
                                                                Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT track_inventory,unit_id FROM products WHERE id=?", new String[]{strOptString});
                                                                try {
                                                                    Cursor cursor4 = cursorRawQuery4;
                                                                    if (!cursor4.moveToFirst()) {
                                                                        try {
                                                                            throw new IllegalArgumentException("\u0627\u0644\u0645\u0646\u062a\u062c \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a".toString());
                                                                        } catch (Throwable th4) {
                                                                            th = th4;
                                                                        }
                                                                    } else {
                                                                        writableDatabase = Boolean.valueOf(cursor4.getInt(0) == 1);
                                                                        Pair pair = TuplesKt.to(writableDatabase, Long.valueOf(cursor4.getLong(1)));
                                                                        CloseableKt.closeFinally(cursorRawQuery4, null);
                                                                        if (!((Boolean) pair.getFirst()).booleanValue()) {
                                                                            throw new IllegalArgumentException("\u0627\u0644\u0645\u0646\u062a\u062c \u0644\u0627 \u064a\u062a\u062a\u0628\u0639 \u0627\u0644\u0645\u062e\u0632\u0648\u0646".toString());
                                                                        }
                                                                        Cursor cursorRawQuery5 = writableDatabase.rawQuery("SELECT decimal_places FROM units WHERE id=?", new String[]{String.valueOf(((Number) pair.getSecond()).longValue())});
                                                                        try {
                                                                            Cursor cursor5 = cursorRawQuery5;
                                                                            if (!cursor5.moveToFirst()) {
                                                                                try {
                                                                                    throw new IllegalArgumentException("Failed requirement.".toString());
                                                                                } catch (Throwable th5) {
                                                                                    th = th5;
                                                                                }
                                                                            } else {
                                                                                int i2 = cursor5.getInt(0);
                                                                                CloseableKt.closeFinally(cursorRawQuery5, null);
                                                                                if (RangesKt.coerceAtLeast(MainActivity$$ExternalSyntheticBackport0.m(BigDecimal.valueOf(dOptDouble)).scale(), 0) > i2) {
                                                                                    throw new IllegalArgumentException("\u062f\u0642\u0629 \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0627\u0641\u062a\u062a\u0627\u062d\u064a \u062a\u062a\u062c\u0627\u0648\u0632 \u062f\u0642\u0629 \u0627\u0644\u0648\u062d\u062f\u0629".toString());
                                                                                }
                                                                                Cursor cursorRawQuery6 = writableDatabase.rawQuery("SELECT 1 FROM inventory_movements WHERE product_id=? LIMIT 1", new String[]{strOptString});
                                                                                try {
                                                                                    boolean zMoveToFirst = cursorRawQuery6.moveToFirst();
                                                                                    CloseableKt.closeFinally(cursorRawQuery6, null);
                                                                                    Cursor cursorRawQuery7 = writableDatabase.rawQuery("SELECT COALESCE(SUM(quantity),0) FROM inventory_balances WHERE product_id=?", new String[]{strOptString});
                                                                                    try {
                                                                                        Cursor cursor6 = cursorRawQuery7;
                                                                                        cursor6.moveToFirst();
                                                                                        double d = cursor6.getDouble(0);
                                                                                        CloseableKt.closeFinally(cursorRawQuery7, null);
                                                                                        if (zMoveToFirst || Math.abs(d) > 1.0E-7d) {
                                                                                            throw new IllegalArgumentException("\u064a\u0648\u062c\u062f \u0631\u0635\u064a\u062f \u0623\u0648 \u062d\u0631\u0643\u0629 \u0633\u0627\u0628\u0642\u0629\u061b \u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u0637\u0628\u064a\u0642 \u0631\u0635\u064a\u062f \u0627\u0641\u062a\u062a\u0627\u062d\u064a \u062b\u0627\u0646\u064d".toString());
                                                                                        }
                                                                                        String strOptString3 = jSONObject.optString("warehouse_id");
                                                                                        Intrinsics.checkNotNull(strOptString3);
                                                                                        String str2 = !StringsKt.isBlank(strOptString3) ? strOptString3 : null;
                                                                                        if (str2 == null) {
                                                                                            throw new IllegalArgumentException("\u0627\u0644\u0645\u0633\u062a\u0648\u062f\u0639 \u0627\u0644\u0623\u0635\u0644\u064a \u063a\u064a\u0631 \u0645\u062d\u062f\u062f");
                                                                                        }
                                                                                        Cursor cursorRawQuery8 = writableDatabase.rawQuery("SELECT 1 FROM warehouses WHERE id=? AND is_default=1 AND is_active=1", new String[]{str2});
                                                                                        try {
                                                                                            boolean zMoveToFirst2 = cursorRawQuery8.moveToFirst();
                                                                                            CloseableKt.closeFinally(cursorRawQuery8, null);
                                                                                            if (!zMoveToFirst2) {
                                                                                                throw new IllegalArgumentException("\u0627\u0644\u0645\u0633\u062a\u0648\u062f\u0639 \u0627\u0644\u0627\u0641\u062a\u0631\u0627\u0636\u064a \u063a\u064a\u0631 \u0645\u0637\u0627\u0628\u0642 \u0628\u064a\u0646 \u0627\u0644\u062c\u0647\u0627\u0632\u064a\u0646".toString());
                                                                                            }
                                                                                            String string2 = UUID.randomUUID().toString();
                                                                                            Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                                                                                            ContentValues contentValues = new ContentValues();
                                                                                            contentValues.put("product_id", strOptString);
                                                                                            contentValues.put("warehouse_id", str2);
                                                                                            contentValues.put("quantity", Double.valueOf(dOptDouble));
                                                                                            contentValues.put("updated_at", strOptString2);
                                                                                            Unit unit = Unit.INSTANCE;
                                                                                            writableDatabase.insertOrThrow("inventory_balances", null, contentValues);
                                                                                            ContentValues contentValues2 = new ContentValues();
                                                                                            contentValues2.put("id", string2);
                                                                                            contentValues2.put("product_id", strOptString);
                                                                                            contentValues2.put("warehouse_id", str2);
                                                                                            contentValues2.put("movement_type", "OPENING");
                                                                                            contentValues2.put("quantity_delta", Double.valueOf(dOptDouble));
                                                                                            contentValues2.put("unit_cost_usd_cents", Long.valueOf(jOptLong));
                                                                                            contentValues2.put("source_type", "SYNC_STOCKTAKE_OPENING");
                                                                                            contentValues2.put("source_id", strOptString);
                                                                                            contentValues2.put("operation_id", "SYNC_STOCKTAKE_OPENING:" + strOptString);
                                                                                            contentValues2.putNull("created_by");
                                                                                            contentValues2.put("note", jSONObject.isNull("note") ? "\u0631\u0635\u064a\u062f \u0627\u0641\u062a\u062a\u0627\u062d\u064a \u0648\u0627\u0631\u062f \u0645\u0646 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a" : jSONObject.getString("note"));
                                                                                            contentValues2.put("created_at", strOptString2);
                                                                                            Unit unit2 = Unit.INSTANCE;
                                                                                            writableDatabase.insertOrThrow("inventory_movements", null, contentValues2);
                                                                                            FifoCosting fifoCosting = FifoCosting.INSTANCE;
                                                                                            Intrinsics.checkNotNull(strOptString);
                                                                                            Intrinsics.checkNotNull(strOptString2);
                                                                                            try {
                                                                                                fifoCosting.addLayer(writableDatabase, string2, strOptString, str2, dOptDouble, jOptLong, "SYNC_STOCKTAKE_OPENING", strOptString, strOptString2, (512 & 512) != 0 ? null : null);
                                                                                                ContentValues contentValues3 = new ContentValues();
                                                                                                contentValues3.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                                                                                contentValues3.put("applied_at", timestamp());
                                                                                                contentValues3.putNull("rejection_reason");
                                                                                                Unit unit3 = Unit.INSTANCE;
                                                                                                writableDatabase.update("sync_inbox", contentValues3, "operation_id=? AND status='RECEIVED'", new String[]{operationId});
                                                                                                writableDatabase.setTransactionSuccessful();
                                                                                                applyResult = new ApplyResult(operationId, true, null, 4, null);
                                                                                                writableDatabase.endTransaction();
                                                                                            } catch (Throwable th6) {
                                                                                                th = th6;
                                                                                                writableDatabase = writableDatabase;
                                                                                            }
                                                                                        } catch (Throwable th7) {
                                                                                            writableDatabase = writableDatabase;
                                                                                            try {
                                                                                                throw th7;
                                                                                            } catch (Throwable th8) {
                                                                                                CloseableKt.closeFinally(cursorRawQuery8, th7);
                                                                                                throw th8;
                                                                                            }
                                                                                        }
                                                                                    } catch (Throwable th9) {
                                                                                        writableDatabase = writableDatabase;
                                                                                        try {
                                                                                            throw th9;
                                                                                        } catch (Throwable th10) {
                                                                                            CloseableKt.closeFinally(cursorRawQuery7, th9);
                                                                                            throw th10;
                                                                                        }
                                                                                    }
                                                                                } catch (Throwable th11) {
                                                                                    writableDatabase = writableDatabase;
                                                                                    try {
                                                                                        throw th11;
                                                                                    } catch (Throwable th12) {
                                                                                        CloseableKt.closeFinally(cursorRawQuery6, th11);
                                                                                        throw th12;
                                                                                    }
                                                                                }
                                                                            }
                                                                        } catch (Throwable th13) {
                                                                            th = th13;
                                                                        }
                                                                        Throwable th14 = th;
                                                                        try {
                                                                            throw th14;
                                                                        } catch (Throwable th15) {
                                                                            CloseableKt.closeFinally(cursorRawQuery5, th14);
                                                                            throw th15;
                                                                        }
                                                                    }
                                                                } catch (Throwable th16) {
                                                                    th = th16;
                                                                }
                                                                Throwable th17 = th;
                                                                try {
                                                                    throw th17;
                                                                } catch (Throwable th18) {
                                                                    CloseableKt.closeFinally(cursorRawQuery4, th17);
                                                                    throw th18;
                                                                }
                                                            }
                                                        }
                                                        throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0627\u0641\u062a\u062a\u0627\u062d\u064a \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
                                                    }
                                                    writableDatabase.setTransactionSuccessful();
                                                    applyResult = new ApplyResult(operationId, true, null, 4, null);
                                                    writableDatabase.endTransaction();
                                                    return applyResult;
                                                    writableDatabase.endTransaction();
                                                    throw th;
                                                }
                                            } catch (Throwable th19) {
                                                th = th19;
                                            }
                                            th = th;
                                            writableDatabase = writableDatabase;
                                            try {
                                                throw th;
                                            } catch (Throwable th20) {
                                                CloseableKt.closeFinally(cursorRawQuery3, th);
                                                throw th20;
                                            }
                                        } catch (Throwable th21) {
                                            th = th21;
                                        }
                                    } catch (Throwable th22) {
                                        th = th22;
                                        writableDatabase = writableDatabase;
                                    }
                                }
                            } catch (Throwable th23) {
                                try {
                                    throw th23;
                                } catch (Throwable th24) {
                                    CloseableKt.closeFinally(cursorRawQuery2, th23);
                                    throw th24;
                                }
                            }
                        }
                        throw new IllegalArgumentException("\u062a\u0637\u0628\u064a\u0642 \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0627\u0641\u062a\u062a\u0627\u062d\u064a \u0627\u0644\u0648\u0627\u0631\u062f \u0645\u0633\u0645\u0648\u062d \u0639\u0644\u0649 \u062c\u0647\u0627\u0632 \u062b\u0627\u0646\u0648\u064a \u0645\u0631\u062a\u0628\u0637 \u0641\u0642\u0637".toString());
                    } catch (Throwable th25) {
                        try {
                            throw th25;
                        } catch (Throwable th26) {
                            CloseableKt.closeFinally(cursorRawQuery, th25);
                            throw th26;
                        }
                    }
                } catch (Throwable th27) {
                    th = th27;
                    throw th;
                }
            } catch (Throwable th28) {
                th = th28;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:123:0x0363 A[Catch: all -> 0x03e6, TRY_LEAVE, TryCatch #6 {all -> 0x03e6, blocks: (B:27:0x007f, B:34:0x00a3, B:36:0x00ae, B:38:0x00b9, B:40:0x00c4, B:42:0x00cf, B:45:0x00e3, B:47:0x00ed, B:51:0x0140, B:53:0x014d, B:55:0x0156, B:57:0x015e, B:59:0x0167, B:63:0x0173, B:70:0x01ae, B:72:0x01b3, B:74:0x01bc, B:76:0x01c5, B:78:0x01d1, B:83:0x0201, B:85:0x020d, B:87:0x0216, B:89:0x0223, B:121:0x0338, B:123:0x0363, B:127:0x0376, B:128:0x0381, B:90:0x0227, B:91:0x0232, B:99:0x0244, B:100:0x0247, B:101:0x0248, B:102:0x0254, B:103:0x0255, B:110:0x02a3, B:112:0x02a8, B:120:0x02f4, B:113:0x02ca, B:115:0x02d3, B:117:0x02dc, B:119:0x02e8, B:129:0x0382, B:130:0x038d, B:138:0x039f, B:139:0x03a2, B:145:0x03a8, B:146:0x03ab, B:147:0x03ac, B:148:0x03b7, B:149:0x03b8, B:150:0x03c3, B:151:0x03c4, B:152:0x03d0, B:160:0x03e2, B:161:0x03e5, B:157:0x03df, B:96:0x0241, B:64:0x0183, B:68:0x018f, B:142:0x03a5, B:104:0x0271, B:107:0x027d, B:131:0x038e, B:132:0x0399, B:135:0x039c, B:28:0x008b, B:30:0x0094, B:32:0x009a, B:153:0x03d1, B:154:0x03dc, B:79:0x01e0, B:81:0x01e9, B:92:0x0233, B:93:0x023e), top: B:198:0x007f, outer: #7, inners: #0, #2, #4, #5, #10, #11, #12, #13 }] */
    /* JADX WARN: Code duplicated, block: B:127:0x0376 A[Catch: all -> 0x03e6, TRY_ENTER, TryCatch #6 {all -> 0x03e6, blocks: (B:27:0x007f, B:34:0x00a3, B:36:0x00ae, B:38:0x00b9, B:40:0x00c4, B:42:0x00cf, B:45:0x00e3, B:47:0x00ed, B:51:0x0140, B:53:0x014d, B:55:0x0156, B:57:0x015e, B:59:0x0167, B:63:0x0173, B:70:0x01ae, B:72:0x01b3, B:74:0x01bc, B:76:0x01c5, B:78:0x01d1, B:83:0x0201, B:85:0x020d, B:87:0x0216, B:89:0x0223, B:121:0x0338, B:123:0x0363, B:127:0x0376, B:128:0x0381, B:90:0x0227, B:91:0x0232, B:99:0x0244, B:100:0x0247, B:101:0x0248, B:102:0x0254, B:103:0x0255, B:110:0x02a3, B:112:0x02a8, B:120:0x02f4, B:113:0x02ca, B:115:0x02d3, B:117:0x02dc, B:119:0x02e8, B:129:0x0382, B:130:0x038d, B:138:0x039f, B:139:0x03a2, B:145:0x03a8, B:146:0x03ab, B:147:0x03ac, B:148:0x03b7, B:149:0x03b8, B:150:0x03c3, B:151:0x03c4, B:152:0x03d0, B:160:0x03e2, B:161:0x03e5, B:157:0x03df, B:96:0x0241, B:64:0x0183, B:68:0x018f, B:142:0x03a5, B:104:0x0271, B:107:0x027d, B:131:0x038e, B:132:0x0399, B:135:0x039c, B:28:0x008b, B:30:0x0094, B:32:0x009a, B:153:0x03d1, B:154:0x03dc, B:79:0x01e0, B:81:0x01e9, B:92:0x0233, B:93:0x023e), top: B:198:0x007f, outer: #7, inners: #0, #2, #4, #5, #10, #11, #12, #13 }] */
    public final ApplyResult applyReceivedUnit(String operationId) {
        String str;
        String[] strArr;
        Throwable th;
        long jInsertOrThrow;
        ContentValues contentValues;
        ApplyResult applyResult;
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        synchronized (APPLY_LOCK) {
            if (StringsKt.isBlank(operationId)) {
                throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0623\u0645\u0631 \u0645\u0637\u0644\u0648\u0628".toString());
            }
            SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
            Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
            SyncDeviceIdentity.Companion companion = SyncDeviceIdentity.INSTANCE;
            Context appContext = this.appContext;
            Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
            String orCreate = companion.getOrCreate(appContext);
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='authority_device_id'", (String[]) null);
            try {
                Cursor cursor = cursorRawQuery;
                String string = cursor.moveToFirst() ? cursor.getString(0) : null;
                CloseableKt.closeFinally(cursorRawQuery, null);
                Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", (String[]) null);
                try {
                    Cursor cursor2 = cursorRawQuery2;
                    String string2 = cursor2.moveToFirst() ? cursor2.getString(0) : null;
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    if (!Intrinsics.areEqual(string2, "SECONDARY") || (str = string) == null || StringsKt.isBlank(str) || Intrinsics.areEqual(string, orCreate)) {
                        throw new IllegalArgumentException("\u062a\u0637\u0628\u064a\u0642 \u0627\u0644\u0648\u062d\u062f\u0627\u062a \u0627\u0644\u0648\u0627\u0631\u062f\u0629 \u0645\u0633\u0645\u0648\u062d \u0639\u0644\u0649 \u062c\u0647\u0627\u0632 \u062b\u0627\u0646\u0648\u064a \u0645\u0631\u062a\u0628\u0637 \u0641\u0642\u0637".toString());
                    }
                    writableDatabase.beginTransaction();
                    try {
                        Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT origin_device_id,operation_type,entity_type,entity_id,payload_json,status\n                   FROM sync_inbox WHERE operation_id=?", new String[]{operationId});
                        try {
                            Cursor cursor3 = cursorRawQuery3;
                            if (!cursor3.moveToFirst()) {
                                throw new IllegalArgumentException("\u0627\u0644\u0648\u062d\u062f\u0629 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629 \u0641\u064a \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0648\u0627\u0631\u062f".toString());
                            }
                            String[] strArr2 = new String[6];
                            for (int i = 0; i < 6; i++) {
                                strArr2[i] = cursor3.getString(i);
                            }
                            CloseableKt.closeFinally(cursorRawQuery3, null);
                            if (!Intrinsics.areEqual(strArr2[0], string) || !Intrinsics.areEqual(strArr2[1], "CREATE") || !Intrinsics.areEqual(strArr2[2], "UNIT")) {
                                throw new IllegalArgumentException("\u0645\u0635\u062f\u0631 \u0623\u0648 \u0646\u0648\u0639 \u0627\u0644\u0648\u062d\u062f\u0629 \u0627\u0644\u0648\u0627\u0631\u062f\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                            }
                            if (Intrinsics.areEqual(strArr2[5], "APPLIED")) {
                                writableDatabase.setTransactionSuccessful();
                                applyResult = new ApplyResult(operationId, true, null, 4, null);
                            } else {
                                if (!Intrinsics.areEqual(strArr2[5], "RECEIVED")) {
                                    throw new IllegalArgumentException("\u062d\u0627\u0644\u0629 \u0627\u0644\u0648\u062d\u062f\u0629 \u0644\u0627 \u062a\u0633\u0645\u062d \u0628\u0627\u0644\u062a\u0637\u0628\u064a\u0642".toString());
                                }
                                JSONObject jSONObject = new JSONObject(strArr2[4]);
                                long jOptLong = jSONObject.optLong("primary_unit_id", 0L);
                                String strOptString = jSONObject.optString("unit_name");
                                Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
                                String string3 = StringsKt.trim((CharSequence) strOptString).toString();
                                String strOptString2 = jSONObject.optString("unit_symbol");
                                Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
                                String string4 = StringsKt.trim((CharSequence) strOptString2).toString();
                                int iOptInt = jSONObject.optInt("decimal_places", -1);
                                if (!Intrinsics.areEqual(jSONObject.optString("business_operation"), "UPSERT_UNIT") || jOptLong <= 0 || !Intrinsics.areEqual(strArr2[3], String.valueOf(jOptLong)) || string3.length() <= 0 || string3.length() > 80 || string4.length() <= 0 || string4.length() > 16 || iOptInt < 0 || iOptInt >= 4) {
                                    throw new IllegalArgumentException("\u062a\u0639\u0631\u064a\u0641 \u0627\u0644\u0648\u062d\u062f\u0629 \u0627\u0644\u0648\u0627\u0631\u062f \u0623\u0648 \u0645\u0639\u0631\u0651\u0641\u0647\u0627 \u0627\u0644\u0623\u0635\u0644\u064a \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                }
                                Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT secondary_unit_id,unit_name,unit_symbol,decimal_places\n                   FROM sync_unit_mappings WHERE primary_device_id=? AND primary_unit_id=?", new String[]{string, String.valueOf(jOptLong)});
                                try {
                                    Cursor cursor4 = cursorRawQuery4;
                                    String[] strArr3 = !cursor4.moveToFirst() ? null : new String[]{String.valueOf(cursor4.getLong(0)), cursor4.getString(1), cursor4.getString(2), String.valueOf(cursor4.getInt(3))};
                                    CloseableKt.closeFinally(cursorRawQuery4, null);
                                    if (strArr3 != null) {
                                        if (!Intrinsics.areEqual(strArr3[1], string3) || !Intrinsics.areEqual(strArr3[2], string4) || !Intrinsics.areEqual(strArr3[3], String.valueOf(iOptInt))) {
                                            throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0648\u062d\u062f\u0629 \u0627\u0644\u0623\u0635\u0644\u064a \u0645\u0631\u062a\u0628\u0637 \u0645\u0633\u0628\u0642\u0627\u064b \u0628\u062a\u0639\u0631\u064a\u0641 \u0648\u062d\u062f\u0629 \u0645\u062e\u062a\u0644\u0641".toString());
                                        }
                                        Cursor cursorRawQuery5 = writableDatabase.rawQuery("SELECT name,symbol,decimal_places FROM units WHERE id=?", new String[]{strArr3[0]});
                                        try {
                                            Cursor cursor5 = cursorRawQuery5;
                                            if (!cursor5.moveToFirst()) {
                                                throw new IllegalArgumentException("\u0627\u0644\u0648\u062d\u062f\u0629 \u0627\u0644\u0645\u062d\u0644\u064a\u0629 \u0627\u0644\u0645\u0631\u062a\u0628\u0637\u0629 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629".toString());
                                            }
                                            String[] strArr4 = {cursor5.getString(0), cursor5.getString(1), String.valueOf(cursor5.getInt(2))};
                                            CloseableKt.closeFinally(cursorRawQuery5, null);
                                            if (!Intrinsics.areEqual(strArr4[0], string3) || !Intrinsics.areEqual(strArr4[1], string4) || !Intrinsics.areEqual(strArr4[2], String.valueOf(iOptInt))) {
                                                throw new IllegalArgumentException("\u062a\u0639\u0631\u064a\u0641 \u0627\u0644\u0648\u062d\u062f\u0629 \u0627\u0644\u0645\u062d\u0644\u064a\u0629 \u062a\u063a\u064a\u0631 \u0628\u0639\u062f \u0631\u0628\u0637\u0647 \u0628\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a".toString());
                                            }
                                            Unit unit = Unit.INSTANCE;
                                            contentValues = new ContentValues();
                                            contentValues.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                            contentValues.put("applied_at", timestamp());
                                            contentValues.putNull("rejection_reason");
                                            Unit unit2 = Unit.INSTANCE;
                                            if (writableDatabase.update("sync_inbox", contentValues, "operation_id=? AND status='RECEIVED'", new String[]{operationId}) == 1) {
                                                throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062b\u0628\u064a\u062a \u062a\u0637\u0628\u064a\u0642 \u0627\u0644\u0648\u062d\u062f\u0629".toString());
                                            }
                                            writableDatabase.setTransactionSuccessful();
                                            applyResult = new ApplyResult(operationId, true, null, 4, null);
                                        } catch (Throwable th2) {
                                            try {
                                                throw th2;
                                            } catch (Throwable th3) {
                                                CloseableKt.closeFinally(cursorRawQuery5, th2);
                                                throw th3;
                                            }
                                        }
                                    } else {
                                        Locale ROOT = Locale.ROOT;
                                        Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
                                        String lowerCase = string3.toLowerCase(ROOT);
                                        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                                        Cursor cursorRawQuery6 = writableDatabase.rawQuery("SELECT id,name,symbol,decimal_places FROM units WHERE lower(trim(name))=? LIMIT 2", new String[]{lowerCase});
                                        try {
                                            Cursor cursor6 = cursorRawQuery6;
                                            if (cursor6.moveToFirst()) {
                                                strArr = new String[]{String.valueOf(cursor6.getLong(0)), cursor6.getString(1), cursor6.getString(2), String.valueOf(cursor6.getInt(3))};
                                                if (cursor6.moveToNext()) {
                                                    throw new IllegalArgumentException("\u0627\u0633\u0645 \u0627\u0644\u0648\u062d\u062f\u0629 \u0645\u0648\u062c\u0648\u062f \u0623\u0643\u062b\u0631 \u0645\u0646 \u0645\u0631\u0629 \u0645\u062d\u0644\u064a\u0627\u064b".toString());
                                                }
                                                th = null;
                                                writableDatabase.endTransaction();
                                                throw th;
                                            }
                                            th = null;
                                            strArr = null;
                                            CloseableKt.closeFinally(cursorRawQuery6, th);
                                            if (strArr == null) {
                                                ContentValues contentValues2 = new ContentValues();
                                                contentValues2.put("name", string3);
                                                contentValues2.put("symbol", string4);
                                                contentValues2.put("decimal_places", Integer.valueOf(iOptInt));
                                                Unit unit3 = Unit.INSTANCE;
                                                jInsertOrThrow = writableDatabase.insertOrThrow("units", null, contentValues2);
                                            } else {
                                                if (!Intrinsics.areEqual(strArr[1], string3) || !Intrinsics.areEqual(strArr[2], string4) || !Intrinsics.areEqual(strArr[3], String.valueOf(iOptInt))) {
                                                    throw new IllegalArgumentException("\u062a\u0639\u0631\u064a\u0641 \u0627\u0644\u0648\u062d\u062f\u0629 \u0627\u0644\u0645\u0648\u062c\u0648\u062f\u0629 \u0645\u062d\u0644\u064a\u0627\u064b \u064a\u062a\u0639\u0627\u0631\u0636 \u0645\u0639 \u062a\u0639\u0631\u064a\u0641 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a".toString());
                                                }
                                                String str2 = strArr[0];
                                                Intrinsics.checkNotNullExpressionValue(str2, "get(...)");
                                                jInsertOrThrow = Long.parseLong(str2);
                                            }
                                            ContentValues contentValues3 = new ContentValues();
                                            contentValues3.put("primary_device_id", string);
                                            contentValues3.put("primary_unit_id", Long.valueOf(jOptLong));
                                            contentValues3.put("secondary_unit_id", Long.valueOf(jInsertOrThrow));
                                            contentValues3.put("unit_name", string3);
                                            contentValues3.put("unit_symbol", string4);
                                            contentValues3.put("decimal_places", Integer.valueOf(iOptInt));
                                            contentValues3.put("mapped_at", timestamp());
                                            Unit unit4 = Unit.INSTANCE;
                                            Long.valueOf(writableDatabase.insertOrThrow("sync_unit_mappings", null, contentValues3));
                                            contentValues = new ContentValues();
                                            contentValues.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                            contentValues.put("applied_at", timestamp());
                                            contentValues.putNull("rejection_reason");
                                            Unit unit5 = Unit.INSTANCE;
                                            if (writableDatabase.update("sync_inbox", contentValues, "operation_id=? AND status='RECEIVED'", new String[]{operationId}) == 1) {
                                                throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062b\u0628\u064a\u062a \u062a\u0637\u0628\u064a\u0642 \u0627\u0644\u0648\u062d\u062f\u0629".toString());
                                            }
                                            writableDatabase.setTransactionSuccessful();
                                            applyResult = new ApplyResult(operationId, true, null, 4, null);
                                        } catch (Throwable th4) {
                                            try {
                                                throw th4;
                                            } catch (Throwable th5) {
                                                CloseableKt.closeFinally(cursorRawQuery6, th4);
                                                throw th5;
                                            }
                                        }
                                    }
                                } catch (Throwable th6) {
                                    try {
                                        throw th6;
                                    } catch (Throwable th7) {
                                        CloseableKt.closeFinally(cursorRawQuery4, th6);
                                        throw th7;
                                    }
                                }
                            }
                            writableDatabase.endTransaction();
                        } catch (Throwable th8) {
                            try {
                                throw th8;
                            } catch (Throwable th9) {
                                CloseableKt.closeFinally(cursorRawQuery3, th8);
                                throw th9;
                            }
                        }
                    } catch (Throwable th10) {
                        writableDatabase.endTransaction();
                        throw th10;
                    }
                } catch (Throwable th11) {
                    try {
                        throw th11;
                    } catch (Throwable th12) {
                        CloseableKt.closeFinally(cursorRawQuery2, th11);
                        throw th12;
                    }
                }
            } catch (Throwable th13) {
                try {
                    throw th13;
                } catch (Throwable th14) {
                    CloseableKt.closeFinally(cursorRawQuery, th13);
                    throw th14;
                }
            }
        }
        return applyResult;
    }

    public final ApplyResult applyReceivedCategory(String operationId) {
        String str;
        long jLongValue;
        ApplyResult applyResult;
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        synchronized (APPLY_LOCK) {
            if (StringsKt.isBlank(operationId)) {
                throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0623\u0645\u0631 \u0645\u0637\u0644\u0648\u0628".toString());
            }
            SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
            Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
            SyncDeviceIdentity.Companion companion = SyncDeviceIdentity.INSTANCE;
            Context appContext = this.appContext;
            Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
            String orCreate = companion.getOrCreate(appContext);
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", (String[]) null);
            try {
                Cursor cursor = cursorRawQuery;
                String string = cursor.moveToFirst() ? cursor.getString(0) : null;
                CloseableKt.closeFinally(cursorRawQuery, null);
                Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='authority_device_id'", (String[]) null);
                try {
                    Cursor cursor2 = cursorRawQuery2;
                    String string2 = cursor2.moveToFirst() ? cursor2.getString(0) : null;
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    if (!Intrinsics.areEqual(string, "SECONDARY") || (str = string2) == null || StringsKt.isBlank(str) || Intrinsics.areEqual(string2, orCreate)) {
                        throw new IllegalArgumentException("\u062a\u0637\u0628\u064a\u0642 \u0627\u0644\u062a\u0635\u0646\u064a\u0641\u0627\u062a \u0627\u0644\u0648\u0627\u0631\u062f\u0629 \u0645\u0633\u0645\u0648\u062d \u0639\u0644\u0649 \u062c\u0647\u0627\u0632 \u062b\u0627\u0646\u0648\u064a \u0645\u0631\u062a\u0628\u0637 \u0641\u0642\u0637".toString());
                    }
                    writableDatabase.beginTransaction();
                    try {
                        Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT origin_device_id,operation_type,entity_type,entity_id,payload_json,status,\n                          origin_created_at\n                   FROM sync_inbox WHERE operation_id=?", new String[]{operationId});
                        try {
                            Cursor cursor3 = cursorRawQuery3;
                            if (!cursor3.moveToFirst()) {
                                throw new IllegalArgumentException("\u0627\u0644\u062a\u0635\u0646\u064a\u0641 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0641\u064a \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0648\u0627\u0631\u062f".toString());
                            }
                            String[] strArr = new String[7];
                            for (int i = 0; i < 7; i++) {
                                strArr[i] = cursor3.getString(i);
                            }
                            CloseableKt.closeFinally(cursorRawQuery3, null);
                            if (!Intrinsics.areEqual(strArr[0], string2) || !Intrinsics.areEqual(strArr[1], "CREATE") || !Intrinsics.areEqual(strArr[2], "CATEGORY")) {
                                throw new IllegalArgumentException("\u0645\u0635\u062f\u0631 \u0623\u0648 \u0646\u0648\u0639 \u0627\u0644\u062a\u0635\u0646\u064a\u0641 \u0627\u0644\u0648\u0627\u0631\u062f \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                            }
                            if (Intrinsics.areEqual(strArr[5], "APPLIED")) {
                                writableDatabase.setTransactionSuccessful();
                                applyResult = new ApplyResult(operationId, true, null, 4, null);
                            } else {
                                if (!Intrinsics.areEqual(strArr[5], "RECEIVED")) {
                                    throw new IllegalArgumentException("\u062d\u0627\u0644\u0629 \u0627\u0644\u062a\u0635\u0646\u064a\u0641 \u0644\u0627 \u062a\u0633\u0645\u062d \u0628\u0627\u0644\u062a\u0637\u0628\u064a\u0642".toString());
                                }
                                JSONObject jSONObject = new JSONObject(strArr[4]);
                                if (!Intrinsics.areEqual(jSONObject.optString("business_operation"), "CREATE_CATEGORY")) {
                                    throw new IllegalArgumentException("\u0646\u0648\u0639 \u0639\u0645\u0644\u064a\u0629 \u0627\u0644\u062a\u0635\u0646\u064a\u0641 \u0627\u0644\u0648\u0627\u0631\u062f\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                }
                                String strOptString = jSONObject.optString("name");
                                Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
                                String string3 = StringsKt.trim((CharSequence) strOptString).toString();
                                long jOptLong = jSONObject.optLong("primary_category_id", 0L);
                                if (jOptLong <= 0) {
                                    throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u062a\u0635\u0646\u064a\u0641 \u0627\u0644\u0623\u0635\u0644\u064a \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                }
                                String strOptString2 = jSONObject.optString("created_at");
                                Intrinsics.checkNotNull(strOptString2);
                                if (!new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(strOptString2)) {
                                    strOptString2 = null;
                                }
                                if (strOptString2 == null) {
                                    strOptString2 = strArr[6];
                                }
                                if (string3.length() > 0 && string3.length() <= 80) {
                                    String str2 = strArr[3];
                                    Locale ROOT = Locale.ROOT;
                                    Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
                                    String lowerCase = string3.toLowerCase(ROOT);
                                    Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                                    if (Intrinsics.areEqual(str2, lowerCase)) {
                                        Intrinsics.checkNotNull(strOptString2);
                                        if (new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(strOptString2)) {
                                            Locale ROOT2 = Locale.ROOT;
                                            Intrinsics.checkNotNullExpressionValue(ROOT2, "ROOT");
                                            String lowerCase2 = string3.toLowerCase(ROOT2);
                                            Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                                            Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT id,name FROM categories WHERE lower(trim(name))=? LIMIT 1", new String[]{lowerCase2});
                                            try {
                                                Cursor cursor4 = cursorRawQuery4;
                                                Pair pair = cursor4.moveToFirst() ? new Pair(Long.valueOf(cursor4.getLong(0)), cursor4.getString(1)) : null;
                                                CloseableKt.closeFinally(cursorRawQuery4, null);
                                                if (pair == null) {
                                                    ContentValues contentValues = new ContentValues();
                                                    contentValues.put("name", string3);
                                                    contentValues.put("created_at", strOptString2);
                                                    Unit unit = Unit.INSTANCE;
                                                    jLongValue = writableDatabase.insertOrThrow("categories", null, contentValues);
                                                } else {
                                                    if (!StringsKt.equals((String) pair.getSecond(), string3, true)) {
                                                        throw new IllegalArgumentException("\u0627\u0633\u0645 \u0627\u0644\u062a\u0635\u0646\u064a\u0641 \u0645\u0648\u062c\u0648\u062f \u0645\u062d\u0644\u064a\u0627\u064b \u0628\u0635\u064a\u063a\u0629 \u0645\u062a\u0639\u0627\u0631\u0636\u0629".toString());
                                                    }
                                                    jLongValue = ((Number) pair.getFirst()).longValue();
                                                }
                                                Cursor cursorRawQuery5 = writableDatabase.rawQuery("SELECT secondary_category_id,category_name FROM sync_category_mappings\n                   WHERE primary_device_id=? AND primary_category_id=?", new String[]{string2, String.valueOf(jOptLong)});
                                                try {
                                                    Cursor cursor5 = cursorRawQuery5;
                                                    Pair pair2 = cursor5.moveToFirst() ? new Pair(Long.valueOf(cursor5.getLong(0)), cursor5.getString(1)) : null;
                                                    CloseableKt.closeFinally(cursorRawQuery5, null);
                                                    if (pair2 != null) {
                                                        if (((Number) pair2.getFirst()).longValue() != jLongValue || !StringsKt.equals((String) pair2.getSecond(), string3, true)) {
                                                            throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u062a\u0635\u0646\u064a\u0641 \u0627\u0644\u0623\u0635\u0644\u064a \u0645\u0631\u062a\u0628\u0637 \u0645\u0633\u0628\u0642\u0627\u064b \u0628\u062a\u0635\u0646\u064a\u0641 \u0645\u062d\u0644\u064a \u0645\u062e\u062a\u0644\u0641".toString());
                                                        }
                                                        Unit unit2 = Unit.INSTANCE;
                                                    } else {
                                                        ContentValues contentValues2 = new ContentValues();
                                                        contentValues2.put("primary_device_id", string2);
                                                        contentValues2.put("primary_category_id", Long.valueOf(jOptLong));
                                                        contentValues2.put("secondary_category_id", Long.valueOf(jLongValue));
                                                        contentValues2.put("category_name", string3);
                                                        contentValues2.put("mapped_at", timestamp());
                                                        Unit unit3 = Unit.INSTANCE;
                                                        Long.valueOf(writableDatabase.insertOrThrow("sync_category_mappings", null, contentValues2));
                                                    }
                                                    ContentValues contentValues3 = new ContentValues();
                                                    contentValues3.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                                    contentValues3.put("applied_at", timestamp());
                                                    contentValues3.putNull("rejection_reason");
                                                    Unit unit4 = Unit.INSTANCE;
                                                    if (writableDatabase.update("sync_inbox", contentValues3, "operation_id=? AND status='RECEIVED'", new String[]{operationId}) != 1) {
                                                        throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062b\u0628\u064a\u062a \u062a\u0637\u0628\u064a\u0642 \u0627\u0644\u062a\u0635\u0646\u064a\u0641".toString());
                                                    }
                                                    writableDatabase.setTransactionSuccessful();
                                                    applyResult = new ApplyResult(operationId, true, null, 4, null);
                                                } catch (Throwable th) {
                                                    try {
                                                        throw th;
                                                    } catch (Throwable th2) {
                                                        CloseableKt.closeFinally(cursorRawQuery5, th);
                                                        throw th2;
                                                    }
                                                }
                                            } catch (Throwable th3) {
                                                try {
                                                    throw th3;
                                                } catch (Throwable th4) {
                                                    CloseableKt.closeFinally(cursorRawQuery4, th3);
                                                    throw th4;
                                                }
                                            }
                                        }
                                    }
                                }
                                throw new IllegalArgumentException("\u0627\u0633\u0645 \u0627\u0644\u062a\u0635\u0646\u064a\u0641 \u0623\u0648 \u0645\u0639\u0631\u0651\u0641\u0647 \u0623\u0648 \u0648\u0642\u062a \u0625\u0646\u0634\u0627\u0626\u0647 \u0627\u0644\u0623\u0635\u0644\u064a \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                            }
                            writableDatabase.endTransaction();
                        } catch (Throwable th5) {
                            try {
                                throw th5;
                            } catch (Throwable th6) {
                                CloseableKt.closeFinally(cursorRawQuery3, th5);
                                throw th6;
                            }
                        }
                    } catch (Throwable th7) {
                        writableDatabase.endTransaction();
                        throw th7;
                    }
                } catch (Throwable th8) {
                    try {
                        throw th8;
                    } catch (Throwable th9) {
                        CloseableKt.closeFinally(cursorRawQuery2, th8);
                        throw th9;
                    }
                }
            } catch (Throwable th10) {
                try {
                    throw th10;
                } catch (Throwable th11) {
                    CloseableKt.closeFinally(cursorRawQuery, th10);
                    throw th11;
                }
            }
        }
        return applyResult;
    }

    public final ApplyResult applyReceivedTransportProbe(String operationId) {
        Object objM381constructorimpl;
        ApplyResult applyResult;
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        synchronized (APPLY_LOCK) {
            if (StringsKt.isBlank(operationId)) {
                throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u062e\u062a\u0628\u0627\u0631 \u0627\u0644\u0646\u0642\u0644 \u0645\u0637\u0644\u0648\u0628".toString());
            }
            SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
            Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
            SyncDeviceIdentity.Companion companion = SyncDeviceIdentity.INSTANCE;
            Context appContext = this.appContext;
            Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
            String orCreate = companion.getOrCreate(appContext);
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", (String[]) null);
            try {
                Cursor cursor = cursorRawQuery;
                String string = cursor.moveToFirst() ? cursor.getString(0) : null;
                CloseableKt.closeFinally(cursorRawQuery, null);
                Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='authority_device_id'", (String[]) null);
                try {
                    Cursor cursor2 = cursorRawQuery2;
                    String string2 = cursor2.moveToFirst() ? cursor2.getString(0) : null;
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    if (!Intrinsics.areEqual(string, "PRIMARY") || !Intrinsics.areEqual(string2, orCreate)) {
                        throw new IllegalArgumentException("\u0627\u062e\u062a\u0628\u0627\u0631 \u0627\u0644\u0646\u0642\u0644 \u0627\u0644\u0648\u0627\u0631\u062f \u064a\u064f\u0639\u062a\u0645\u062f \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0641\u0642\u0637".toString());
                    }
                    writableDatabase.beginTransaction();
                    try {
                        Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT i.origin_device_id,i.operation_type,i.entity_type,i.entity_id,\n                          i.payload_json,i.status,d.status\n                   FROM sync_inbox i JOIN sync_devices d ON d.device_id=i.origin_device_id\n                   WHERE i.operation_id=?", new String[]{operationId});
                        try {
                            Cursor cursor3 = cursorRawQuery3;
                            if (!cursor3.moveToFirst()) {
                                throw new IllegalArgumentException("\u0627\u062e\u062a\u0628\u0627\u0631 \u0627\u0644\u0646\u0642\u0644 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0641\u064a \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0648\u0627\u0631\u062f".toString());
                            }
                            String[] strArr = new String[7];
                            for (int i = 0; i < 7; i++) {
                                strArr[i] = cursor3.getString(i);
                            }
                            CloseableKt.closeFinally(cursorRawQuery3, null);
                            if (!Intrinsics.areEqual(strArr[6], "ACTIVE") || !Intrinsics.areEqual(strArr[1], "CREATE") || !Intrinsics.areEqual(strArr[2], "SYNC_TRANSPORT_PROBE") || !Intrinsics.areEqual(strArr[3], operationId)) {
                                throw new IllegalArgumentException("\u0645\u0635\u062f\u0631 \u0623\u0648 \u0646\u0648\u0639 \u0627\u062e\u062a\u0628\u0627\u0631 \u0627\u0644\u0646\u0642\u0644 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                            }
                            if (Intrinsics.areEqual(strArr[5], "APPLIED")) {
                                writableDatabase.setTransactionSuccessful();
                                applyResult = new ApplyResult(operationId, true, null, 4, null);
                            } else {
                                if (!Intrinsics.areEqual(strArr[5], "RECEIVED")) {
                                    throw new IllegalArgumentException("\u062d\u0627\u0644\u0629 \u0627\u062e\u062a\u0628\u0627\u0631 \u0627\u0644\u0646\u0642\u0644 \u0644\u0627 \u062a\u0633\u0645\u062d \u0628\u0627\u0644\u062a\u0637\u0628\u064a\u0642".toString());
                                }
                                JSONObject jSONObject = new JSONObject(strArr[4]);
                                if (Intrinsics.areEqual(jSONObject.optString("business_operation"), "SYNC_TRANSPORT_PROBE") && Intrinsics.areEqual(jSONObject.optString("probe_id"), operationId)) {
                                    try {
                                        Result.Companion companion2 = Result.INSTANCE;
                                        SyncDomainApplier syncDomainApplier = this;
                                        objM381constructorimpl = Result.m381constructorimpl(UUID.fromString(jSONObject.optString("nonce")));
                                    } catch (Throwable th) {
                                        Result.Companion companion3 = Result.INSTANCE;
                                        objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
                                    }
                                    if (Result.m388isSuccessimpl(objM381constructorimpl)) {
                                        String strOptString = jSONObject.optString("created_at");
                                        Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
                                        if (new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(strOptString)) {
                                            ContentValues contentValues = new ContentValues();
                                            contentValues.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                            contentValues.put("applied_at", timestamp());
                                            contentValues.putNull("rejection_reason");
                                            Unit unit = Unit.INSTANCE;
                                            if (writableDatabase.update("sync_inbox", contentValues, "operation_id=? AND status='RECEIVED'", new String[]{operationId}) != 1) {
                                                throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062b\u0628\u064a\u062a \u0646\u062a\u064a\u062c\u0629 \u0627\u062e\u062a\u0628\u0627\u0631 \u0627\u0644\u0646\u0642\u0644".toString());
                                            }
                                            writableDatabase.setTransactionSuccessful();
                                            applyResult = new ApplyResult(operationId, true, null, 4, null);
                                        }
                                    }
                                }
                                throw new IllegalArgumentException("\u0645\u062d\u062a\u0648\u0649 \u0627\u062e\u062a\u0628\u0627\u0631 \u0627\u0644\u0646\u0642\u0644 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                            }
                            writableDatabase.endTransaction();
                        } catch (Throwable th2) {
                            try {
                                throw th2;
                            } catch (Throwable th3) {
                                CloseableKt.closeFinally(cursorRawQuery3, th2);
                                throw th3;
                            }
                        }
                    } catch (Throwable th4) {
                        writableDatabase.endTransaction();
                        throw th4;
                    }
                } catch (Throwable th5) {
                    try {
                        throw th5;
                    } catch (Throwable th6) {
                        CloseableKt.closeFinally(cursorRawQuery2, th5);
                        throw th6;
                    }
                }
            } catch (Throwable th7) {
                try {
                    throw th7;
                } catch (Throwable th8) {
                    CloseableKt.closeFinally(cursorRawQuery, th7);
                    throw th8;
                }
            }
        }
        return applyResult;
    }

    private final String resultReference(SQLiteDatabase db, String operationId, String type) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT result_reference FROM operation_registry WHERE operation_id=? AND operation_type=?", new String[]{operationId, type});
        try {
            Cursor cursor = cursorRawQuery;
            String string = (!cursor.moveToFirst() || cursor.isNull(0)) ? null : cursor.getString(0);
            CloseableKt.closeFinally(cursorRawQuery, null);
            return string;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final List<InvoiceLineInput> parseLines(JSONArray items) throws JSONException {
        IntRange intRangeUntil = RangesKt.until(0, items.length());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10));
        Iterator<Integer> it = intRangeUntil.iterator();
        while (it.hasNext()) {
            JSONObject jSONObject = items.getJSONObject(((IntIterator) it).nextInt());
            BigDecimal bigDecimal = new BigDecimal(jSONObject.getString("quantity"));
            long j = jSONObject.getLong("unit_price_or_cost_usd_cents");
            String string = jSONObject.getString("product_id");
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            if (StringsKt.isBlank(string) || bigDecimal.compareTo(BigDecimal.ZERO) <= 0 || j < 0) {
                throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0623\u062d\u062f \u0623\u0635\u0646\u0627\u0641 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
            }
            String string2 = jSONObject.getString("product_id");
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            arrayList.add(new InvoiceLineInput(string2, bigDecimal, j));
        }
        return arrayList;
    }

    /* JADX INFO: compiled from: SyncDomainApplier.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003JO\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001J\t\u0010!\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r\u00a8\u0006\""}, d2 = {"Lcom/hazem/dailystore/data/SyncDomainApplier$InboxCommand;", "", "originDeviceId", "", "entityType", "entityId", "payloadJson", NotificationCompat.CATEGORY_STATUS, "primaryUserId", "originCreatedAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getOriginDeviceId", "()Ljava/lang/String;", "getEntityType", "getEntityId", "getPayloadJson", "getStatus", "getPrimaryUserId", "getOriginCreatedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class InboxCommand {
        private final String entityId;
        private final String entityType;
        private final String originCreatedAt;
        private final String originDeviceId;
        private final String payloadJson;
        private final String primaryUserId;
        private final String status;

        public static /* synthetic */ InboxCommand copy$default(InboxCommand inboxCommand, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, Object obj) {
            if ((i & 1) != 0) {
                str = inboxCommand.originDeviceId;
            }
            if ((i & 2) != 0) {
                str2 = inboxCommand.entityType;
            }
            String str8 = str2;
            if ((i & 4) != 0) {
                str3 = inboxCommand.entityId;
            }
            String str9 = str3;
            if ((i & 8) != 0) {
                str4 = inboxCommand.payloadJson;
            }
            String str10 = str4;
            if ((i & 16) != 0) {
                str5 = inboxCommand.status;
            }
            String str11 = str5;
            if ((i & 32) != 0) {
                str6 = inboxCommand.primaryUserId;
            }
            String str12 = str6;
            if ((i & 64) != 0) {
                str7 = inboxCommand.originCreatedAt;
            }
            return inboxCommand.copy(str, str8, str9, str10, str11, str12, str7);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getOriginDeviceId() {
            return this.originDeviceId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getEntityType() {
            return this.entityType;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getEntityId() {
            return this.entityId;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getPayloadJson() {
            return this.payloadJson;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getStatus() {
            return this.status;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getPrimaryUserId() {
            return this.primaryUserId;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getOriginCreatedAt() {
            return this.originCreatedAt;
        }

        public final InboxCommand copy(String originDeviceId, String entityType, String entityId, String payloadJson, String status, String primaryUserId, String originCreatedAt) {
            Intrinsics.checkNotNullParameter(originDeviceId, "originDeviceId");
            Intrinsics.checkNotNullParameter(entityType, "entityType");
            Intrinsics.checkNotNullParameter(entityId, "entityId");
            Intrinsics.checkNotNullParameter(payloadJson, "payloadJson");
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(primaryUserId, "primaryUserId");
            Intrinsics.checkNotNullParameter(originCreatedAt, "originCreatedAt");
            return new InboxCommand(originDeviceId, entityType, entityId, payloadJson, status, primaryUserId, originCreatedAt);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof InboxCommand)) {
                return false;
            }
            InboxCommand inboxCommand = (InboxCommand) other;
            return Intrinsics.areEqual(this.originDeviceId, inboxCommand.originDeviceId) && Intrinsics.areEqual(this.entityType, inboxCommand.entityType) && Intrinsics.areEqual(this.entityId, inboxCommand.entityId) && Intrinsics.areEqual(this.payloadJson, inboxCommand.payloadJson) && Intrinsics.areEqual(this.status, inboxCommand.status) && Intrinsics.areEqual(this.primaryUserId, inboxCommand.primaryUserId) && Intrinsics.areEqual(this.originCreatedAt, inboxCommand.originCreatedAt);
        }

        public int hashCode() {
            return (((((((((((this.originDeviceId.hashCode() * 31) + this.entityType.hashCode()) * 31) + this.entityId.hashCode()) * 31) + this.payloadJson.hashCode()) * 31) + this.status.hashCode()) * 31) + this.primaryUserId.hashCode()) * 31) + this.originCreatedAt.hashCode();
        }

        public String toString() {
            return "InboxCommand(originDeviceId=" + this.originDeviceId + ", entityType=" + this.entityType + ", entityId=" + this.entityId + ", payloadJson=" + this.payloadJson + ", status=" + this.status + ", primaryUserId=" + this.primaryUserId + ", originCreatedAt=" + this.originCreatedAt + ")";
        }

        public InboxCommand(String originDeviceId, String entityType, String entityId, String payloadJson, String status, String primaryUserId, String originCreatedAt) {
            Intrinsics.checkNotNullParameter(originDeviceId, "originDeviceId");
            Intrinsics.checkNotNullParameter(entityType, "entityType");
            Intrinsics.checkNotNullParameter(entityId, "entityId");
            Intrinsics.checkNotNullParameter(payloadJson, "payloadJson");
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(primaryUserId, "primaryUserId");
            Intrinsics.checkNotNullParameter(originCreatedAt, "originCreatedAt");
            this.originDeviceId = originDeviceId;
            this.entityType = entityType;
            this.entityId = entityId;
            this.payloadJson = payloadJson;
            this.status = status;
            this.primaryUserId = primaryUserId;
            this.originCreatedAt = originCreatedAt;
        }

        public final String getOriginDeviceId() {
            return this.originDeviceId;
        }

        public final String getEntityType() {
            return this.entityType;
        }

        public final String getEntityId() {
            return this.entityId;
        }

        public final String getPayloadJson() {
            return this.payloadJson;
        }

        public final String getStatus() {
            return this.status;
        }

        public final String getPrimaryUserId() {
            return this.primaryUserId;
        }

        public final String getOriginCreatedAt() {
            return this.originCreatedAt;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final String operatingDateForTimestamp(String value) {
        StoreTime storeTime = StoreTime.INSTANCE;
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        return storeTime.operatingDate(readableDatabase, value);
    }

    private final String timestamp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String str = simpleDateFormat.format(new Date());
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }
}
