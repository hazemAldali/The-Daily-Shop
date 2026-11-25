package com.hazem.dailystore.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import androidx.core.app.NotificationCompat;
import androidx.core.view.PointerIconCompat;
import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import net.zetetic.database.sqlcipher.SQLiteDatabase;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ServiceTransferReceivableRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\"\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011JR\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u000fJw\u0010\u001d\u001a\u00020\u001e2\n\u0010\u001f\u001a\u00060 j\u0002`!2\u0006\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u000f2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\u0002\u0010&J6\u0010'\u001a\u0014\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u000f0(2\n\u0010\u001f\u001a\u00060 j\u0002`!2\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020\u0019H\u0002J\u0010\u0010*\u001a\u00020\u000f2\u0006\u0010+\u001a\u00020\u000fH\u0002J\u0015\u0010,\u001a\n \u0007*\u0004\u0018\u00010\u000f0\u000fH\u0002\u00a2\u0006\u0002\u0010-R\u0018\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006."}, d2 = {"Lcom/hazem/dailystore/data/ServiceTransferReceivableRepository;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "appContext", "kotlin.jvm.PlatformType", "Landroid/content/Context;", "database", "Lcom/hazem/dailystore/data/AppDatabase;", "listOpenReceivables", "", "Lcom/hazem/dailystore/data/ServiceTransferReceivableSummary;", "customerId", "", "limit", "", "collect", "Lcom/hazem/dailystore/data/ServiceTransferDebtPaymentResult;", "actorUserId", "transferId", "operationId", "cashAccountId", "amountSypMinor", "", "note", "occurredAt", "operatingDate", "enqueueMirrorSnapshot", "", "db", "Lnet/zetetic/database/sqlcipher/SQLiteDatabase;", "Lcom/hazem/dailystore/data/AppSqliteDatabase;", "sourceOperationId", "paymentId", "paymentAmount", "paymentNote", "(Lnet/zetetic/database/sqlcipher/SQLiteDatabase;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "receivableState", "Lkotlin/Triple;", "charge", "hash", "value", "timestamp", "()Ljava/lang/String;", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ServiceTransferReceivableRepository {
    private final Context appContext;
    private final AppDatabase database;

    public ServiceTransferReceivableRepository(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Context appContext = context.getApplicationContext();
        this.appContext = appContext;
        AppDatabase.Companion companion = AppDatabase.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        this.database = companion.getInstance(appContext);
    }

    public static /* synthetic */ List listOpenReceivables$default(ServiceTransferReceivableRepository serviceTransferReceivableRepository, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = null;
        }
        if ((i2 & 2) != 0) {
            i = 100;
        }
        return serviceTransferReceivableRepository.listOpenReceivables(str, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final List<ServiceTransferReceivableSummary> listOpenReceivables(String customerId, int limit) throws IOException {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        Context appContext = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        int i = 2;
        boolean zIsConfiguredSecondary = new SyncProtocolRepository(appContext, null, i, 0 == true ? 1 : 0).isConfiguredSecondary();
        int i2 = 7;
        int i3 = 4;
        int i4 = 3;
        int i5 = 0;
        int i6 = 6;
        int i7 = 5;
        if (zIsConfiguredSecondary) {
            Cursor cursorRawQuery = readableDatabase.rawQuery("SELECT transfer_id,customer_id,customer_name,service_type,delivered_minor,\n                          charge_minor,paid_minor,outstanding_minor,created_at,note\n                   FROM service_transfer_receivable_mirrors\n                   WHERE status IN ('OPEN','PARTIALLY_PAID') AND outstanding_minor>0\n                     AND (? IS NULL OR customer_id=?)\n                   ORDER BY created_at DESC LIMIT ?", new String[]{customerId, customerId, String.valueOf(RangesKt.coerceIn(limit, 1, 500))});
            try {
                Cursor cursor = cursorRawQuery;
                while (cursor.moveToNext()) {
                    String string = cursor.getString(i5);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    String string2 = cursor.getString(1);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    String string3 = cursor.getString(i);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                    String string4 = cursor.getString(3);
                    Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                    long j = cursor.getLong(i3);
                    long j2 = cursor.getLong(5);
                    long j3 = cursor.getLong(6);
                    long j4 = cursor.getLong(i2);
                    String string5 = cursor.getString(8);
                    Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                    arrayList.add(new ServiceTransferReceivableSummary(string, string2, string3, string4, j, j2, j3, j4, string5, cursor.isNull(9) ? null : cursor.getString(9)));
                    i2 = 7;
                    i = 2;
                    i3 = 4;
                    i5 = 0;
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(cursorRawQuery, null);
                return arrayList;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(cursorRawQuery, th);
                    throw th2;
                }
            }
        }
        Cursor cursorRawQuery2 = readableDatabase.rawQuery("SELECT t.id,r.customer_id,c.name,t.service_type,t.amount_delivered_minor,\n                      t.customer_charge_minor,COALESCE(SUM(p.amount_minor),0),t.created_at,r.note\n               FROM service_transfer_receivables r\n               JOIN service_transfers t ON t.id=r.transfer_id\n               JOIN customers c ON c.id=r.customer_id\n               LEFT JOIN service_transfer_receivable_payments p ON p.transfer_id=t.id\n               WHERE t.status='POSTED' AND r.status IN ('OPEN','PARTIALLY_PAID')\n                 AND (? IS NULL OR r.customer_id=?)\n               GROUP BY t.id\n               HAVING t.customer_charge_minor-COALESCE(SUM(p.amount_minor),0)>0\n               ORDER BY t.created_at DESC LIMIT ?", new String[]{customerId, customerId, String.valueOf(RangesKt.coerceIn(limit, 1, 500))});
        try {
            Cursor cursor2 = cursorRawQuery2;
            while (cursor2.moveToNext()) {
                long j5 = cursor2.getLong(i7);
                long j6 = cursor2.getLong(i6);
                String string6 = cursor2.getString(0);
                Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                String string7 = cursor2.getString(1);
                Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
                String string8 = cursor2.getString(2);
                Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
                String string9 = cursor2.getString(i4);
                Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
                long j7 = cursor2.getLong(4);
                long jCoerceAtLeast = RangesKt.coerceAtLeast(j5 - j6, 0L);
                String string10 = cursor2.getString(7);
                Intrinsics.checkNotNullExpressionValue(string10, "getString(...)");
                arrayList.add(new ServiceTransferReceivableSummary(string6, string7, string8, string9, j7, j5, j6, jCoerceAtLeast, string10, cursor2.isNull(8) ? null : cursor2.getString(8)));
                i4 = 3;
                i6 = 6;
                i7 = 5;
            }
            Unit unit2 = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery2, null);
            return arrayList;
        } catch (Throwable th3) {
            try {
                throw th3;
            } catch (Throwable th4) {
                CloseableKt.closeFinally(cursorRawQuery2, th3);
                throw th4;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final ServiceTransferDebtPaymentResult collect(String actorUserId, String transferId, String operationId, String cashAccountId, long amountSypMinor, String note, String occurredAt, String operatingDate) throws Throwable {
        Throwable th;
        String string;
        String strOperatingDate;
        String str;
        String str2;
        String string2;
        String strTake;
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(transferId, "transferId");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        Intrinsics.checkNotNullParameter(cashAccountId, "cashAccountId");
        if (StringsKt.isBlank(actorUserId) || StringsKt.isBlank(transferId) || StringsKt.isBlank(operationId) || StringsKt.isBlank(cashAccountId)) {
            throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0646 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u063a\u064a\u0631 \u0645\u0643\u062a\u0645\u0644\u0629".toString());
        }
        if (amountSypMinor <= 0) {
            throw new IllegalArgumentException("\u0645\u0628\u0644\u063a \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u064a\u062c\u0628 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0623\u0643\u0628\u0631 \u0645\u0646 \u0635\u0641\u0631".toString());
        }
        String str3 = (note == null || (string2 = StringsKt.trim((CharSequence) note).toString()) == null || (strTake = StringsKt.take(string2, 500)) == null || strTake.length() <= 0) ? null : strTake;
        String strHash = hash(transferId + "|" + cashAccountId + "|" + amountSypMinor + "|" + (str3 == null ? "" : str3));
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        writableDatabase.beginTransaction();
        try {
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", (String[]) null);
            try {
                Cursor cursor = cursorRawQuery;
                if (cursor.moveToFirst()) {
                    string = cursor.getString(0);
                    th = null;
                } else {
                    th = null;
                    string = null;
                }
                CloseableKt.closeFinally(cursorRawQuery, th);
                String str4 = string;
                String str5 = str3;
                if (Intrinsics.areEqual(string, "SECONDARY")) {
                    try {
                        PermissionPolicy.INSTANCE.requireAssigned(writableDatabase, actorUserId, PermissionPolicy.CUSTOMERS_COLLECT);
                        Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT customer_id,charge_minor,paid_minor,outstanding_minor,status FROM service_transfer_receivable_mirrors WHERE transfer_id=?", new String[]{transferId});
                        try {
                            Cursor cursor2 = cursorRawQuery2;
                            if (cursor2.moveToFirst()) {
                                String string3 = cursor2.getString(0);
                                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                                long j = cursor2.getLong(1);
                                long j2 = cursor2.getLong(2);
                                long j3 = cursor2.getLong(3);
                                String string4 = cursor2.getString(4);
                                Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                                MirrorDebt mirrorDebt = new MirrorDebt(string3, j, j2, j3, string4);
                                CloseableKt.closeFinally(cursorRawQuery2, null);
                                if (!SetsKt.setOf((Object[]) new String[]{"OPEN", "PARTIALLY_PAID"}).contains(mirrorDebt.getStatus()) || mirrorDebt.getOutstanding() <= 0) {
                                    throw new IllegalArgumentException("\u062a\u0645 \u062a\u0633\u062f\u064a\u062f \u0647\u0630\u0627 \u0627\u0644\u062f\u064a\u0646 \u0623\u0648 \u0625\u063a\u0644\u0627\u0642\u0647".toString());
                                }
                                if (amountSypMinor > mirrorDebt.getOutstanding()) {
                                    throw new IllegalArgumentException("\u0645\u0628\u0644\u063a \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u064a\u062a\u062c\u0627\u0648\u0632 \u0627\u0644\u0645\u062a\u0628\u0642\u064a \u0627\u0644\u0645\u0633\u062a\u062d\u0642".toString());
                                }
                                Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT account_type,currency_code,is_active FROM cash_accounts WHERE id=?", new String[]{cashAccountId});
                                try {
                                    Cursor cursor3 = cursorRawQuery3;
                                    if (cursor3.moveToFirst() && cursor3.getInt(2) == 1) {
                                        Triple triple = new Triple(cursor3.getString(0), cursor3.getString(1), true);
                                        CloseableKt.closeFinally(cursorRawQuery3, null);
                                        if (!Intrinsics.areEqual(triple.getSecond(), "SYP") || !SetsKt.setOf((Object[]) new String[]{"CASH_BOX", "WALLET"}).contains(triple.getFirst())) {
                                            throw new IllegalArgumentException("\u0627\u062e\u062a\u0631 \u062d\u0633\u0627\u0628 \u0642\u0628\u0636 \u0646\u0634\u0637\u0627\u064b \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629".toString());
                                        }
                                        String strTimestamp = timestamp();
                                        StoreTime storeTime = StoreTime.INSTANCE;
                                        Intrinsics.checkNotNull(strTimestamp);
                                        String strOperatingDate2 = storeTime.operatingDate(writableDatabase, strTimestamp);
                                        String string5 = UUID.randomUUID().toString();
                                        Intrinsics.checkNotNullExpressionValue(string5, "toString(...)");
                                        JSONObject jSONObjectPut = new JSONObject().put("business_operation", "COLLECT_SERVICE_TRANSFER_DEBT").put("transfer_id", transferId).put("payment_id", string5).put("customer_id", mirrorDebt.getCustomerId()).put("amount_minor", amountSypMinor).put("cash_account_id", cashAccountId).put("cash_account_type", triple.getFirst()).put("cash_account_currency_code", triple.getSecond()).put("note", str5 == null ? JSONObject.NULL : str5).put("occurred_at", strTimestamp).put("operating_date", strOperatingDate2);
                                        Context appContext = this.appContext;
                                        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
                                        SyncProtocolRepository syncProtocolRepository = new SyncProtocolRepository(appContext, null, 2, null == true ? 1 : 0);
                                        String string6 = jSONObjectPut.toString();
                                        Intrinsics.checkNotNullExpressionValue(string6, "toString(...)");
                                        syncProtocolRepository.enqueueSecondaryCommand(writableDatabase, actorUserId, operationId, "COLLECT_SERVICE_TRANSFER_DEBT", "SERVICE_TRANSFER_DEBT_PAYMENT", string5, string6);
                                        writableDatabase.setTransactionSuccessful();
                                        ServiceTransferDebtPaymentResult serviceTransferDebtPaymentResult = new ServiceTransferDebtPaymentResult(string5, transferId, mirrorDebt.getPaid(), mirrorDebt.getOutstanding(), mirrorDebt.getStatus(), false, true);
                                        writableDatabase.endTransaction();
                                        return serviceTransferDebtPaymentResult;
                                    }
                                    try {
                                        throw new IllegalArgumentException("\u062d\u0633\u0627\u0628 \u0627\u0644\u0642\u0628\u0636 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0623\u0648 \u0645\u0648\u0642\u0648\u0641".toString());
                                    } catch (Throwable th2) {
                                        th = th2;
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                }
                                Throwable th4 = th;
                                try {
                                    throw th4;
                                } catch (Throwable th5) {
                                    CloseableKt.closeFinally(cursorRawQuery3, th4);
                                    throw th5;
                                }
                            }
                            try {
                                throw new IllegalArgumentException("\u062f\u064a\u0646 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u063a\u064a\u0631 \u0645\u062a\u0627\u062d \u0639\u0644\u0649 \u0647\u0630\u0627 \u0627\u0644\u062c\u0647\u0627\u0632\u061b \u0627\u0646\u062a\u0638\u0631 \u0645\u0632\u0627\u0645\u0646\u0629 \u0627\u0644\u0623\u0633\u0627\u0633\u064a".toString());
                            } catch (Throwable th6) {
                                th = th6;
                            }
                        } catch (Throwable th7) {
                            th = th7;
                        }
                        Throwable th8 = th;
                        try {
                            throw th8;
                        } catch (Throwable th9) {
                            CloseableKt.closeFinally(cursorRawQuery2, th8);
                            throw th9;
                        }
                    } catch (Throwable th10) {
                        th = th10;
                    }
                } else {
                    if (str4 != null && !Intrinsics.areEqual(str4, "PRIMARY")) {
                        throw new IllegalArgumentException("\u062f\u0648\u0631 \u0627\u0644\u062c\u0647\u0627\u0632 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d \u0644\u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0646 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f".toString());
                    }
                    PermissionPolicy.INSTANCE.require(writableDatabase, actorUserId, PermissionPolicy.CUSTOMERS_COLLECT);
                    Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT t.customer_charge_minor,t.status,r.status,r.original_amount_minor\n                   FROM service_transfers t JOIN service_transfer_receivables r ON r.transfer_id=t.id\n                   WHERE t.id=?", new String[]{transferId});
                    try {
                        Cursor cursor4 = cursorRawQuery4;
                        if (!cursor4.moveToFirst()) {
                            throw new IllegalArgumentException("\u062f\u064a\u0646 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
                        }
                        String[] strArr = {String.valueOf(cursor4.getLong(0)), cursor4.getString(1), cursor4.getString(2), String.valueOf(cursor4.getLong(3))};
                        CloseableKt.closeFinally(cursorRawQuery4, null);
                        if (!Intrinsics.areEqual(strArr[0], strArr[3])) {
                            throw new IllegalArgumentException("\u0645\u0628\u0644\u063a \u0627\u0644\u0630\u0645\u0629 \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u0645\u0628\u0644\u063a \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0645\u0633\u062c\u0644\u061b \u0623\u0648\u0642\u0641 \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u0644\u062d\u064a\u0646 \u0627\u0644\u062a\u062f\u0642\u064a\u0642".toString());
                        }
                        Cursor cursorRawQuery5 = writableDatabase.rawQuery("SELECT id,transfer_id,amount_minor,payload_hash FROM service_transfer_receivable_payments WHERE operation_id=?", new String[]{operationId});
                        try {
                            Cursor cursor5 = cursorRawQuery5;
                            String[] strArr2 = cursor5.moveToFirst() ? new String[]{cursor5.getString(0), cursor5.getString(1), String.valueOf(cursor5.getLong(2)), cursor5.getString(3)} : null;
                            CloseableKt.closeFinally(cursorRawQuery5, null);
                            if (strArr2 == null) {
                                if (!Intrinsics.areEqual(strArr[1], "POSTED") || !SetsKt.setOf((Object[]) new String[]{"OPEN", "PARTIALLY_PAID"}).contains(strArr[2])) {
                                    throw new IllegalArgumentException("\u0647\u0630\u0627 \u0627\u0644\u062f\u064a\u0646 \u063a\u064a\u0631 \u0642\u0627\u0628\u0644 \u0644\u0644\u062a\u062d\u0635\u064a\u0644 \u0623\u0648 \u0623\u064f\u063a\u0644\u0642 \u0633\u0627\u0628\u0642\u0627\u064b".toString());
                                }
                                Cursor cursorRawQuery6 = writableDatabase.rawQuery("SELECT account_type,currency_code,is_active FROM cash_accounts WHERE id=?", new String[]{cashAccountId});
                                try {
                                    Cursor cursor6 = cursorRawQuery6;
                                    if (!cursor6.moveToFirst() || cursor6.getInt(2) != 1) {
                                        throw new IllegalArgumentException("\u062d\u0633\u0627\u0628 \u0627\u0644\u0642\u0628\u0636 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0623\u0648 \u0645\u0648\u0642\u0648\u0641".toString());
                                    }
                                    Triple triple2 = new Triple(cursor6.getString(0), cursor6.getString(1), true);
                                    CloseableKt.closeFinally(cursorRawQuery6, null);
                                    if (!Intrinsics.areEqual(triple2.getSecond(), "SYP")) {
                                        throw new IllegalArgumentException("\u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0648\u0646 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u0645\u062a\u0627\u062d \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629 \u0641\u0642\u0637".toString());
                                    }
                                    String str6 = (String) triple2.getFirst();
                                    if (Intrinsics.areEqual(str6, "CASH_BOX")) {
                                        String str7 = operatingDate == null ? StoreTime.today$default(StoreTime.INSTANCE, writableDatabase, null, 2, null) : operatingDate;
                                        if (!new Regex("\\d{4}-\\d{2}-\\d{2}").matches(str7)) {
                                            throw new IllegalArgumentException("\u062a\u0627\u0631\u064a\u062e \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                        }
                                        Cursor cursorRawQuery7 = writableDatabase.rawQuery("SELECT primary_cash_account_id FROM daily_cash_sessions WHERE operating_date=?", new String[]{str7});
                                        try {
                                            Cursor cursor7 = cursorRawQuery7;
                                            String string7 = cursor7.moveToFirst() ? cursor7.getString(0) : null;
                                            CloseableKt.closeFinally(cursorRawQuery7, null);
                                            if (!Intrinsics.areEqual(string7, cashAccountId)) {
                                                throw new IllegalArgumentException("\u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u0627\u0644\u0646\u0642\u062f\u064a \u064a\u062c\u0628 \u0623\u0646 \u064a\u0630\u0647\u0628 \u0625\u0644\u0649 \u062f\u0631\u062c \u0627\u0644\u064a\u0648\u0645 \u0627\u0644\u0631\u0626\u064a\u0633\u064a".toString());
                                            }
                                        } catch (Throwable th11) {
                                            try {
                                                throw th11;
                                            } catch (Throwable th12) {
                                                CloseableKt.closeFinally(cursorRawQuery7, th11);
                                                throw th12;
                                            }
                                        }
                                    } else if (!Intrinsics.areEqual(str6, "WALLET")) {
                                        throw new IllegalArgumentException("\u0627\u062e\u062a\u0631 \u0627\u0644\u062f\u0631\u062c \u0627\u0644\u064a\u0648\u0645\u064a \u0627\u0644\u0631\u0626\u064a\u0633\u064a \u0623\u0648 \u0645\u062d\u0641\u0638\u0629 \u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a\u0629 \u0646\u0634\u0637\u0629");
                                    }
                                    String str8 = strArr[0];
                                    Intrinsics.checkNotNull(str8);
                                    Triple<Long, Long, String> tripleReceivableState = receivableState(writableDatabase, transferId, Long.parseLong(str8));
                                    long jLongValue = tripleReceivableState.getFirst().longValue();
                                    String str9 = strArr[0];
                                    Intrinsics.checkNotNull(str9);
                                    if (jLongValue > Long.parseLong(str9)) {
                                        throw new IllegalArgumentException("\u062f\u0641\u062a\u0631 \u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0646 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u063a\u064a\u0631 \u0645\u062a\u0648\u0627\u0632\u0646\u061b \u064a\u0644\u0632\u0645 \u0627\u0644\u062a\u062f\u0642\u064a\u0642 \u0642\u0628\u0644 \u0627\u0644\u062a\u062d\u0635\u064a\u0644".toString());
                                    }
                                    if (tripleReceivableState.getSecond().longValue() <= 0) {
                                        throw new IllegalArgumentException("\u062a\u0645 \u062a\u0633\u062f\u064a\u062f \u0627\u0644\u062f\u064a\u0646 \u0628\u0627\u0644\u0643\u0627\u0645\u0644".toString());
                                    }
                                    if (amountSypMinor > tripleReceivableState.getSecond().longValue()) {
                                        throw new IllegalArgumentException("\u0645\u0628\u0644\u063a \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u064a\u062a\u062c\u0627\u0648\u0632 \u0627\u0644\u0645\u062a\u0628\u0642\u064a \u0627\u0644\u0645\u0633\u062a\u062d\u0642".toString());
                                    }
                                    String strTimestamp2 = occurredAt == null ? timestamp() : occurredAt;
                                    if (operatingDate == null) {
                                        StoreTime storeTime2 = StoreTime.INSTANCE;
                                        Intrinsics.checkNotNull(strTimestamp2);
                                        strOperatingDate = storeTime2.operatingDate(writableDatabase, strTimestamp2);
                                    } else {
                                        strOperatingDate = operatingDate;
                                    }
                                    Intrinsics.checkNotNull(strTimestamp2);
                                    if (new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(strTimestamp2)) {
                                        if (new Regex("\\d{4}-\\d{2}-\\d{2}").matches(strOperatingDate)) {
                                            String string8 = UUID.randomUUID().toString();
                                            Intrinsics.checkNotNullExpressionValue(string8, "toString(...)");
                                            String string9 = UUID.randomUUID().toString();
                                            Intrinsics.checkNotNullExpressionValue(string9, "toString(...)");
                                            ContentValues contentValues = new ContentValues();
                                            contentValues.put("id", string9);
                                            contentValues.put("account_id", cashAccountId);
                                            contentValues.put("direction", "IN");
                                            contentValues.put("amount_minor", Long.valueOf(amountSypMinor));
                                            contentValues.put("currency_code", "SYP");
                                            contentValues.put("transaction_type", "SERVICE_TRANSFER_DEBT_COLLECTION");
                                            contentValues.put("source_type", "SERVICE_TRANSFER_DEBT_COLLECTION");
                                            contentValues.put("source_id", transferId);
                                            contentValues.put("operation_id", operationId);
                                            if (str5 == null) {
                                                str = "\u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0646 \u062a\u062d\u0648\u064a\u0644 \u0631\u0635\u064a\u062f \u2022 " + transferId;
                                            } else {
                                                str = str5;
                                            }
                                            contentValues.put("description", str);
                                            contentValues.put("created_by", actorUserId);
                                            contentValues.put("created_at", strTimestamp2);
                                            contentValues.put("operating_date", strOperatingDate);
                                            Unit unit = Unit.INSTANCE;
                                            writableDatabase.insertOrThrow("cash_transactions", null, contentValues);
                                            ContentValues contentValues2 = new ContentValues();
                                            contentValues2.put("id", string8);
                                            contentValues2.put("transfer_id", transferId);
                                            contentValues2.put("amount_minor", Long.valueOf(amountSypMinor));
                                            contentValues2.put("cash_transaction_id", string9);
                                            contentValues2.put("operation_id", operationId);
                                            contentValues2.put("payload_hash", strHash);
                                            contentValues2.put("created_by", actorUserId);
                                            contentValues2.put("created_at", strTimestamp2);
                                            contentValues2.put("operating_date", strOperatingDate);
                                            if (str5 == null) {
                                                contentValues2.putNull("note");
                                                str2 = str5;
                                            } else {
                                                str2 = str5;
                                                contentValues2.put("note", str2);
                                            }
                                            Unit unit2 = Unit.INSTANCE;
                                            writableDatabase.insertOrThrow("service_transfer_receivable_payments", null, contentValues2);
                                            long jM = MainActivity$$ExternalSyntheticBackport0.m(tripleReceivableState.getFirst().longValue(), amountSypMinor);
                                            String str10 = strArr[0];
                                            Intrinsics.checkNotNull(str10);
                                            String str11 = jM == Long.parseLong(str10) ? "PAID" : "PARTIALLY_PAID";
                                            ContentValues contentValues3 = new ContentValues();
                                            contentValues3.put(NotificationCompat.CATEGORY_STATUS, str11);
                                            Unit unit3 = Unit.INSTANCE;
                                            if (writableDatabase.update("service_transfer_receivables", contentValues3, "transfer_id=? AND status IN ('OPEN','PARTIALLY_PAID')", new String[]{transferId}) != 1) {
                                                throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062d\u062f\u064a\u062b \u062d\u0627\u0644\u0629 \u062f\u064a\u0646 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f".toString());
                                            }
                                            ContentValues contentValues4 = new ContentValues();
                                            contentValues4.put("id", UUID.randomUUID().toString());
                                            contentValues4.put("user_id", actorUserId);
                                            contentValues4.put("action", "SERVICE_TRANSFER_DEBT_COLLECTED");
                                            contentValues4.put("entity_type", "SERVICE_TRANSFER_RECEIVABLE");
                                            contentValues4.put("entity_id", transferId);
                                            contentValues4.put("after_json", "{\"payment_id\":\"" + string8 + "\",\"amount_minor\":" + amountSypMinor + ",\"status\":\"" + str11 + "\"}");
                                            contentValues4.put("created_at", strTimestamp2);
                                            Unit unit4 = Unit.INSTANCE;
                                            writableDatabase.insertOrThrow("audit_log", null, contentValues4);
                                            enqueueMirrorSnapshot(writableDatabase, actorUserId, transferId, operationId, string8, Long.valueOf(amountSypMinor), cashAccountId, strOperatingDate, strTimestamp2, str2);
                                            writableDatabase.setTransactionSuccessful();
                                            String str12 = strArr[0];
                                            Intrinsics.checkNotNull(str12);
                                            ServiceTransferDebtPaymentResult serviceTransferDebtPaymentResult2 = new ServiceTransferDebtPaymentResult(string8, transferId, jM, Long.parseLong(str12) - jM, str11, false, false, 64, null);
                                            writableDatabase.endTransaction();
                                            return serviceTransferDebtPaymentResult2;
                                        }
                                    }
                                    throw new IllegalArgumentException("\u062a\u0627\u0631\u064a\u062e \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u0623\u0648 \u064a\u0648\u0645 \u0627\u0644\u0639\u0645\u0644 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                } catch (Throwable th13) {
                                    try {
                                        throw th13;
                                    } catch (Throwable th14) {
                                        CloseableKt.closeFinally(cursorRawQuery6, th13);
                                        throw th14;
                                    }
                                }
                            }
                            try {
                                if (!Intrinsics.areEqual(strArr2[1], transferId) || !Intrinsics.areEqual(strArr2[2], String.valueOf(amountSypMinor)) || !Intrinsics.areEqual(strArr2[3], strHash)) {
                                    throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u0645\u0633\u062a\u062e\u062f\u0645 \u0644\u0639\u0645\u0644\u064a\u0629 \u0623\u0648 \u0628\u064a\u0627\u0646\u0627\u062a \u0645\u062e\u062a\u0644\u0641\u0629".toString());
                                }
                                String str13 = strArr[0];
                                Intrinsics.checkNotNull(str13);
                                Triple<Long, Long, String> tripleReceivableState2 = receivableState(writableDatabase, transferId, Long.parseLong(str13));
                                writableDatabase.setTransactionSuccessful();
                                String str14 = strArr2[0];
                                Intrinsics.checkNotNull(str14);
                                ServiceTransferDebtPaymentResult serviceTransferDebtPaymentResult3 = new ServiceTransferDebtPaymentResult(str14, transferId, tripleReceivableState2.getFirst().longValue(), tripleReceivableState2.getSecond().longValue(), tripleReceivableState2.getThird(), true, false, 64, null);
                                writableDatabase.endTransaction();
                                return serviceTransferDebtPaymentResult3;
                            } catch (Throwable th15) {
                                th = th15;
                            }
                        } catch (Throwable th16) {
                            try {
                                throw th16;
                            } catch (Throwable th17) {
                                CloseableKt.closeFinally(cursorRawQuery5, th16);
                                throw th17;
                            }
                        }
                    } catch (Throwable th18) {
                        try {
                            throw th18;
                        } catch (Throwable th19) {
                            CloseableKt.closeFinally(cursorRawQuery4, th18);
                            throw th19;
                        }
                    }
                }
            } catch (Throwable th20) {
                try {
                    throw th20;
                } catch (Throwable th21) {
                    CloseableKt.closeFinally(cursorRawQuery, th20);
                    throw th21;
                }
            }
        } catch (Throwable th22) {
            th = th22;
        }
        th = th22;
        writableDatabase.endTransaction();
        throw th;
    }

    /* JADX INFO: compiled from: ServiceTransferReceivableRepository.kt */
    @Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\u008a\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0003\u00a2\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J@\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f\u00a8\u0006\u001f"}, d2 = {"com/hazem/dailystore/data/ServiceTransferReceivableRepository$collect$MirrorDebt", "", "customerId", "", "charge", "", "paid", "outstanding", NotificationCompat.CATEGORY_STATUS, "<init>", "(Ljava/lang/String;JJJLjava/lang/String;)V", "getCustomerId", "()Ljava/lang/String;", "getCharge", "()J", "getPaid", "getOutstanding", "getStatus", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;JJJLjava/lang/String;)Lcom/hazem/dailystore/data/ServiceTransferReceivableRepository$collect$MirrorDebt;", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class MirrorDebt {
        private final long charge;
        private final String customerId;
        private final long outstanding;
        private final long paid;
        private final String status;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getCustomerId() {
            return this.customerId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getCharge() {
            return this.charge;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final long getPaid() {
            return this.paid;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final long getOutstanding() {
            return this.outstanding;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getStatus() {
            return this.status;
        }

        public final MirrorDebt copy(String customerId, long charge, long paid, long outstanding, String status) {
            Intrinsics.checkNotNullParameter(customerId, "customerId");
            Intrinsics.checkNotNullParameter(status, "status");
            return new MirrorDebt(customerId, charge, paid, outstanding, status);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MirrorDebt)) {
                return false;
            }
            MirrorDebt mirrorDebt = (MirrorDebt) other;
            return Intrinsics.areEqual(this.customerId, mirrorDebt.customerId) && this.charge == mirrorDebt.charge && this.paid == mirrorDebt.paid && this.outstanding == mirrorDebt.outstanding && Intrinsics.areEqual(this.status, mirrorDebt.status);
        }

        public int hashCode() {
            return (((((((this.customerId.hashCode() * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.charge)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.paid)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.outstanding)) * 31) + this.status.hashCode();
        }

        public String toString() {
            return "MirrorDebt(customerId=" + this.customerId + ", charge=" + this.charge + ", paid=" + this.paid + ", outstanding=" + this.outstanding + ", status=" + this.status + ")";
        }

        public MirrorDebt(String customerId, long j, long j2, long j3, String status) {
            Intrinsics.checkNotNullParameter(customerId, "customerId");
            Intrinsics.checkNotNullParameter(status, "status");
            this.customerId = customerId;
            this.charge = j;
            this.paid = j2;
            this.outstanding = j3;
            this.status = status;
        }

        public final long getCharge() {
            return this.charge;
        }

        public final String getCustomerId() {
            return this.customerId;
        }

        public final long getOutstanding() {
            return this.outstanding;
        }

        public final long getPaid() {
            return this.paid;
        }

        public final String getStatus() {
            return this.status;
        }
    }

    public static /* synthetic */ void enqueueMirrorSnapshot$default(ServiceTransferReceivableRepository serviceTransferReceivableRepository, SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String str4, Long l, String str5, String str6, String str7, String str8, int i, Object obj) throws JSONException, NoSuchAlgorithmException, IOException {
        serviceTransferReceivableRepository.enqueueMirrorSnapshot(sQLiteDatabase, str, str2, str3, (PointerIconCompat.TYPE_TEXT & 16) != 0 ? null : str4, (PointerIconCompat.TYPE_TEXT & 32) != 0 ? null : l, (PointerIconCompat.TYPE_TEXT & 64) != 0 ? null : str5, (PointerIconCompat.TYPE_TEXT & 128) != 0 ? null : str6, (PointerIconCompat.TYPE_TEXT & 256) != 0 ? null : str7, (PointerIconCompat.TYPE_TEXT & 512) != 0 ? null : str8);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void enqueueMirrorSnapshot(SQLiteDatabase db, String actorUserId, String transferId, String sourceOperationId, String paymentId, Long paymentAmount, String cashAccountId, String operatingDate, String occurredAt, String paymentNote) throws JSONException, NoSuchAlgorithmException, IOException {
        Throwable th;
        long jCoerceAtLeast;
        String string;
        Object string2;
        String str;
        String str2;
        String str3;
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(transferId, "transferId");
        Intrinsics.checkNotNullParameter(sourceOperationId, "sourceOperationId");
        if (!db.inTransaction()) {
            throw new IllegalArgumentException("\u064a\u062c\u0628 \u062d\u0641\u0638 \u0645\u0632\u0627\u0645\u0646\u0629 \u0630\u0645\u0629 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u0636\u0645\u0646 \u0645\u0639\u0627\u0645\u0644\u0629 \u0627\u0644\u0639\u0645\u0644\u064a\u0629".toString());
        }
        Cursor cursorRawQuery = db.rawQuery("SELECT t.customer_id,c.name,t.service_type,t.amount_delivered_minor,\n                      t.customer_charge_minor,t.created_at,COALESCE(t.operating_date,substr(t.created_at,1,10)),\n                      t.reference,r.note,r.status,COALESCE(SUM(p.amount_minor),0),t.amount_delivered_milli_syp\n               FROM service_transfers t\n               JOIN service_transfer_receivables r ON r.transfer_id=t.id\n               JOIN customers c ON c.id=r.customer_id\n               LEFT JOIN service_transfer_receivable_payments p ON p.transfer_id=t.id\n               WHERE t.id=? AND t.status IN ('POSTED','REVERSED')\n               GROUP BY t.id", new String[]{transferId});
        try {
            Cursor cursor = cursorRawQuery;
            if (!cursor.moveToFirst()) {
                try {
                    throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062c\u0647\u064a\u0632 \u0646\u0633\u062e\u0629 \u0645\u0632\u0627\u0645\u0646\u0629 \u0630\u0645\u0629 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f".toString());
                } catch (Throwable th2) {
                    th = th2;
                }
            } else {
                long j = cursor.getLong(4);
                long j2 = cursor.getLong(10);
                boolean zAreEqual = Intrinsics.areEqual(cursor.getString(9), "REVERSED");
                if (zAreEqual) {
                    jCoerceAtLeast = 0;
                } else {
                    try {
                        jCoerceAtLeast = RangesKt.coerceAtLeast(j - j2, 0L);
                    } catch (Throwable th3) {
                        th = th3;
                        cursorRawQuery = cursorRawQuery;
                        th = th;
                    }
                }
                int i = 2;
                JSONObject jSONObjectPut = new JSONObject().put("business_operation", "UPSERT_SERVICE_TRANSFER_RECEIVABLE").put("transfer_id", transferId).put("customer_id", cursor.getString(0)).put("customer_name", cursor.getString(1)).put("service_type", cursor.getString(2)).put("delivered_minor", cursor.getLong(3)).put("delivered_milli_syp", cursor.getLong(11)).put("charge_minor", j).put("paid_minor", j2).put("outstanding_minor", jCoerceAtLeast);
                if (zAreEqual) {
                    string = "REVERSED";
                } else if (j2 >= j) {
                    string = "PAID";
                } else {
                    string = j2 > 0 ? "PARTIALLY_PAID" : cursor.getString(9);
                }
                JSONObject jSONObjectPut2 = jSONObjectPut.put(NotificationCompat.CATEGORY_STATUS, string).put("created_at", cursor.getString(5)).put("operating_date", cursor.getString(6));
                if (cursor.isNull(7)) {
                    try {
                        string2 = JSONObject.NULL;
                    } catch (Throwable th4) {
                        th = th4;
                        cursorRawQuery = cursorRawQuery;
                    }
                } else {
                    string2 = cursor.getString(7);
                }
                JSONObject jSONObjectPut3 = jSONObjectPut2.put("reference", string2).put("note", cursor.isNull(8) ? JSONObject.NULL : cursor.getString(8));
                if (paymentId != null) {
                    if (paymentAmount == null || paymentAmount.longValue() <= 0 || (str = cashAccountId) == null || StringsKt.isBlank(str) || (str2 = operatingDate) == null || StringsKt.isBlank(str2) || (str3 = occurredAt) == null || StringsKt.isBlank(str3)) {
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                    jSONObjectPut3.put("payment_id", paymentId).put("payment_amount_minor", paymentAmount.longValue());
                    jSONObjectPut3.put("payment_operation_id", sourceOperationId).put("payment_cash_account_id", cashAccountId);
                    jSONObjectPut3.put("payment_operating_date", operatingDate).put("payment_created_at", occurredAt);
                    jSONObjectPut3.put("payment_note", paymentNote == null ? JSONObject.NULL : paymentNote);
                }
                CloseableKt.closeFinally(cursorRawQuery, null);
                byte[] bytes = ("service-transfer-receivable|" + transferId + "|" + sourceOperationId).getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                String string3 = UUID.nameUUIDFromBytes(bytes).toString();
                Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
                Context appContext = this.appContext;
                Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
                SyncProtocolRepository syncProtocolRepository = new SyncProtocolRepository(appContext, null, i, 0 == true ? 1 : 0);
                String str4 = paymentId == null ? "CREATE" : "UPDATE";
                String string4 = jSONObjectPut3.toString();
                Intrinsics.checkNotNullExpressionValue(string4, "toString(...)");
                syncProtocolRepository.enqueueLocalOperation(db, actorUserId, string3, "UPSERT_SERVICE_TRANSFER_RECEIVABLE", "SERVICE_TRANSFER_RECEIVABLE", transferId, str4, string4);
                return;
            }
        } catch (Throwable th5) {
            th = th5;
        }
        th = th;
        try {
            throw th;
        } catch (Throwable th6) {
            CloseableKt.closeFinally(cursorRawQuery, th);
            throw th6;
        }
    }

    private final Triple<Long, Long, String> receivableState(SQLiteDatabase db, String transferId, long charge) throws IOException {
        String str;
        Cursor cursorRawQuery = db.rawQuery("SELECT COALESCE(SUM(amount_minor),0) FROM service_transfer_receivable_payments WHERE transfer_id=?", new String[]{transferId});
        try {
            Cursor cursor = cursorRawQuery;
            cursor.moveToFirst();
            long j = cursor.getLong(0);
            CloseableKt.closeFinally(cursorRawQuery, null);
            long jCoerceAtLeast = RangesKt.coerceAtLeast(charge - j, 0L);
            Long lValueOf = Long.valueOf(j);
            Long lValueOf2 = Long.valueOf(jCoerceAtLeast);
            if (jCoerceAtLeast == 0) {
                str = "PAID";
            } else {
                str = j > 0 ? "PARTIALLY_PAID" : "OPEN";
            }
            return new Triple<>(lValueOf, lValueOf2, str);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final String hash(String value) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = value.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrDigest = messageDigest.digest(bytes);
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
        return ArraysKt.joinToString$default(bArrDigest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.hazem.dailystore.data.ServiceTransferReceivableRepository$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ServiceTransferReceivableRepository.hash$lambda$41(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence hash$lambda$41(byte b) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    private final String timestamp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.format(new Date());
    }
}
