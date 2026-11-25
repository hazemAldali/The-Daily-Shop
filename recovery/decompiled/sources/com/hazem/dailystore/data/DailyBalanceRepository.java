package com.hazem.dailystore.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import net.zetetic.database.sqlcipher.SQLiteDatabase;
import org.json.JSONObject;

/* JADX INFO: compiled from: DailyBalanceRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001b\u0018\u00002\u00020\u0001:\u0001OB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005JV\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0013\u001a\u00020\u000e2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0016\u001a\u00020\u0017J\b\u0010\u0018\u001a\u0004\u0018\u00010\fJ*\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000eJF\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u000e2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000eJF\u0010!\u001a\u00020\u001d2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u000e2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000eJ2\u0010\"\u001a\u00020\u001a2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u000e2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000eJF\u0010#\u001a\u00020\u001d2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u000e2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000eJ\u0010\u0010$\u001a\u00020%2\b\b\u0002\u0010&\u001a\u00020\u000eJ\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u000e0(J*\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00110(2\n\u0010*\u001a\u00060+j\u0002`,2\u0006\u0010-\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\u000eH\u0002J,\u0010.\u001a\u00020\u00112\n\u0010*\u001a\u00060+j\u0002`,2\u0006\u0010-\u001a\u00020\u000e2\u0006\u0010/\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\u000eH\u0002J0\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011012\n\u0010*\u001a\u00060+j\u0002`,2\u0006\u0010&\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020\u000eH\u0002J\u001e\u00102\u001a\u0004\u0018\u00010\f2\n\u0010*\u001a\u00060+j\u0002`,2\u0006\u0010&\u001a\u00020\u000eH\u0002J\u001e\u00103\u001a\u0004\u0018\u00010\f2\n\u0010*\u001a\u00060+j\u0002`,2\u0006\u0010-\u001a\u00020\u000eH\u0002J\u001c\u00104\u001a\u0002052\n\u0010*\u001a\u00060+j\u0002`,2\u0006\u0010-\u001a\u00020\u000eH\u0002J\u001c\u00106\u001a\u00020\u00112\n\u0010*\u001a\u00060+j\u0002`,2\u0006\u0010-\u001a\u00020\u000eH\u0002Jl\u00107\u001a\u00020\u001a2\n\u0010*\u001a\u00060+j\u0002`,2\u0006\u0010-\u001a\u00020\u000e2\u0006\u00108\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u00109\u001a\u00020\u000e2\u0006\u0010:\u001a\u00020\u000e2\u0006\u0010;\u001a\u00020\u000e2\u0006\u0010<\u001a\u00020\u000e2\u0006\u0010=\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010>\u001a\u00020\u000e2\u0006\u0010?\u001a\u00020\u000eH\u0002JN\u0010@\u001a\u00020\u001a2\n\u0010*\u001a\u00060+j\u0002`,2\u0006\u0010A\u001a\u00020\u000e2\u0006\u0010B\u001a\u00020\u000e2\u0006\u0010C\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020\u000e2\b\u0010D\u001a\u0004\u0018\u00010\u000e2\u0006\u0010E\u001a\u00020\u000e2\u0006\u0010?\u001a\u00020\u000eH\u0002J\u001c\u0010F\u001a\u00020\u001a2\n\u0010*\u001a\u00060+j\u0002`,2\u0006\u0010-\u001a\u00020\u000eH\u0002J\u0010\u0010G\u001a\u00020\u000e2\u0006\u0010H\u001a\u00020\u000eH\u0002J\u0010\u0010I\u001a\u00020\u000e2\u0006\u0010J\u001a\u00020\u000eH\u0002J\u0010\u0010K\u001a\u00020\u000e2\u0006\u0010J\u001a\u00020\u000eH\u0002J\b\u0010L\u001a\u00020\u000eH\u0002J\u0015\u0010M\u001a\n \u0007*\u0004\u0018\u00010\u000e0\u000eH\u0002\u00a2\u0006\u0002\u0010NR\u0018\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006P"}, d2 = {"Lcom/hazem/dailystore/data/DailyBalanceRepository;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "appContext", "kotlin.jvm.PlatformType", "Landroid/content/Context;", "database", "Lcom/hazem/dailystore/data/AppDatabase;", "setSupplierRollover", "Lcom/hazem/dailystore/data/SupplierRolloverSummary;", "actor", "", "accountId", "amount", "", "note", "operationId", "occurredAt", "operatingDate", "syncOrigin", "", "currentSupplierRollover", "setWalletOpening", "", "walletId", "transferCashBetweenBoxes", "Lcom/hazem/dailystore/data/AccountTransferResult;", "sourceAccountId", "destinationAccountId", "reference", "transferCashToWallet", "addPersonalWithdrawal", "transferWalletToDrawer", "dailyReport", "Lcom/hazem/dailystore/data/DailyCashReport;", "date", "todayTransactions", "", "totals", "db", "Lnet/zetetic/database/sqlcipher/SQLiteDatabase;", "Lcom/hazem/dailystore/data/AppSqliteDatabase;", "id", "openingBefore", "base", "usage", "Lkotlin/Pair;", "rolloverForDate", "rollover", "account", "Lcom/hazem/dailystore/data/DailyBalanceRepository$Account;", "balance", "movement", "direction", "currency", "type", "sourceType", "sourceId", "op", "description", "now", "audit", "user", "action", "kind", "before", "after", "manager", "typeName", "t", "esc", "s", "hash", "today", "stamp", "()Ljava/lang/String;", "Account", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class DailyBalanceRepository {
    private final Context appContext;
    private final AppDatabase database;

    public DailyBalanceRepository(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.appContext = context.getApplicationContext();
        this.database = AppDatabase.INSTANCE.getInstance(context);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v44, types: [org.json.JSONObject] */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.Object, net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r1v33, types: [android.content.ContentValues] */
    /* JADX WARN: Type inference failed for: r1v53, types: [com.hazem.dailystore.data.SyncProtocolRepository] */
    /* JADX WARN: Type inference failed for: r2v13, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v34 */
    /* JADX WARN: Type inference failed for: r2v43, types: [com.hazem.dailystore.data.PermissionPolicy] */
    /* JADX WARN: Type inference failed for: r2v53, types: [com.hazem.dailystore.data.SyncProtocolRepository] */
    /* JADX WARN: Type inference failed for: r39v0, types: [com.hazem.dailystore.data.DailyBalanceRepository] */
    /* JADX WARN: Type inference failed for: r4v38 */
    /* JADX WARN: Type inference failed for: r4v39 */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v18 */
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
    public final SupplierRolloverSummary setSupplierRollover(String actor, String accountId, long amount, String note, String operationId, String occurredAt, String operatingDate, boolean syncOrigin) throws Throwable {
        Throwable th;
        Pair pair;
        Throwable th2;
        Throwable th3;
        String string;
        String string2;
        String str;
        String str2;
        Intrinsics.checkNotNullParameter(actor, "actor");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        if (amount < 0) {
            throw new IllegalArgumentException("\u0645\u0628\u0644\u063a \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0633\u0627\u0644\u0628\u0627\u064b".toString());
        }
        if (StringsKt.isBlank(accountId) || StringsKt.isBlank(operationId)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        ?? writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", null);
        try {
            Cursor cursor = cursorRawQuery;
            String string3 = cursor.moveToFirst() ? cursor.getString(0) : null;
            CloseableKt.closeFinally(cursorRawQuery, null);
            String strStamp = occurredAt == null ? stamp() : occurredAt;
            String str3 = operatingDate == null ? today() : operatingDate;
            Intrinsics.checkNotNull(strStamp);
            if (new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(strStamp)) {
                if (new Regex("\\d{4}-\\d{2}-\\d{2}").matches(str3)) {
                    String str4 = str3;
                    String str5 = strStamp;
                    if (Intrinsics.areEqual(string3, "SECONDARY")) {
                        PermissionPolicy.INSTANCE.requireAssigned(writableDatabase, actor, PermissionPolicy.DAILY_CASH_MANAGE);
                        Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT name FROM cash_accounts WHERE id=? AND account_type='CASH_BOX' AND currency_code='SYP' AND is_active=1", new String[]{accountId});
                        try {
                            Cursor cursor2 = cursorRawQuery2;
                            if (cursor2.moveToFirst()) {
                                String string4 = cursor2.getString(0);
                                CloseableKt.closeFinally(cursorRawQuery2, null);
                                JSONObject jSONObjectPut = new JSONObject().put("business_operation", "SET_SUPPLIER_ROLLOVER").put("cash_account_id", accountId).put("cash_account_type", "CASH_BOX").put("cash_account_currency_code", "SYP").put("amount_minor", amount).put("note", note == null ? JSONObject.NULL : note).put("occurred_at", str5).put("operating_date", str4);
                                writableDatabase.beginTransaction();
                                try {
                                    try {
                                        Context appContext = this.appContext;
                                        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
                                        String string5 = jSONObjectPut.toString();
                                        Intrinsics.checkNotNullExpressionValue(string5, "toString(...)");
                                        new SyncProtocolRepository(appContext, null, 2, 0 == true ? 1 : 0).enqueueSecondaryCommand(writableDatabase, actor, operationId, "SET_SUPPLIER_ROLLOVER", "DAILY_SUPPLIER_ROLLOVER", "pending-rollover-" + operationId, string5);
                                        writableDatabase.setTransactionSuccessful();
                                        writableDatabase.endTransaction();
                                        Intrinsics.checkNotNull(string4);
                                        return new SupplierRolloverSummary(str4, accountId, string4, amount, 0L, 0L, true);
                                    } catch (Throwable th4) {
                                        th = th4;
                                        writableDatabase.endTransaction();
                                        throw th;
                                    }
                                } catch (Throwable th5) {
                                    th = th5;
                                }
                            } else {
                                try {
                                    throw new IllegalArgumentException("\u0627\u062e\u062a\u0631 \u0635\u0646\u062f\u0648\u0642\u0627\u064b \u0646\u0642\u062f\u064a\u0627\u064b \u0646\u0634\u0637\u0627\u064b \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629".toString());
                                } catch (Throwable th6) {
                                    th = th6;
                                }
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
                    }
                    writableDatabase = "operating_date";
                    manager(writableDatabase, actor);
                    writableDatabase.beginTransaction();
                    try {
                        try {
                            Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT name FROM cash_accounts WHERE id=? AND account_type='CASH_BOX' AND currency_code='SYP' AND is_active=1", new String[]{accountId});
                            try {
                                Cursor cursor3 = cursorRawQuery3;
                                if (cursor3.moveToFirst()) {
                                    String string6 = cursor3.getString(0);
                                    CloseableKt.closeFinally(cursorRawQuery3, null);
                                    Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT 1 FROM app_settings WHERE setting_key='service_recharge_cash_account_id' AND setting_value=?", new String[]{accountId});
                                    try {
                                        boolean zMoveToFirst = cursorRawQuery4.moveToFirst();
                                        CloseableKt.closeFinally(cursorRawQuery4, null);
                                        if (zMoveToFirst) {
                                            throw new IllegalArgumentException("\u0647\u0630\u0627 \u0627\u0644\u0635\u0646\u062f\u0648\u0642 \u0645\u062e\u0635\u0635 \u0644\u0634\u0631\u0627\u0621 \u0631\u0635\u064a\u062f \u0627\u0644\u062e\u062f\u0645\u0627\u062a \u0648\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u062e\u0635\u064a\u0635\u0647 \u0644\u0644\u0645\u0648\u0631\u062f\u064a\u0646".toString());
                                        }
                                        Pair<Long, Long> pairUsage = usage(writableDatabase, str4, accountId);
                                        Cursor cursorRawQuery5 = writableDatabase.rawQuery("SELECT COUNT(*) FROM cash_transactions WHERE account_id=? AND COALESCE(operating_date,substr(created_at,1,10))=? AND transaction_type NOT IN ('PURCHASE_PAYMENT','PERSONAL_WITHDRAWAL','ACCOUNT_TRANSFER_IN')", new String[]{accountId, str4});
                                        try {
                                            Cursor cursor4 = cursorRawQuery5;
                                            cursor4.moveToFirst();
                                            int i = cursor4.getInt(0);
                                            CloseableKt.closeFinally(cursorRawQuery5, null);
                                            if (i != 0) {
                                                throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u062e\u0635\u064a\u0635 \u0647\u0630\u0627 \u0627\u0644\u0635\u0646\u062f\u0648\u0642 \u0644\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0644\u0623\u0646\u0647 \u0627\u0633\u062a\u0642\u0628\u0644 \u062d\u0631\u0643\u0627\u062a \u0623\u062e\u0631\u0649 \u0627\u0644\u064a\u0648\u0645\u061b \u0627\u0633\u062a\u062e\u062f\u0645 \u0635\u0646\u062f\u0648\u0642\u0627\u064b \u0645\u0646\u0641\u0635\u0644\u0627\u064b".toString());
                                            }
                                            if (amount < pairUsage.getFirst().longValue() + pairUsage.getSecond().longValue()) {
                                                throw new IllegalArgumentException("\u0627\u0644\u0645\u0628\u0644\u063a \u0627\u0644\u062c\u062f\u064a\u062f \u0623\u0642\u0644 \u0645\u0646 \u062f\u0641\u0639\u0627\u062a \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0648\u0627\u0644\u0645\u0633\u062d\u0648\u0628\u0627\u062a \u0627\u0644\u0645\u0633\u062c\u0644\u0629 \u0627\u0644\u064a\u0648\u0645".toString());
                                            }
                                            Cursor cursorRawQuery6 = writableDatabase.rawQuery("SELECT account_id,allocated_minor FROM daily_supplier_rollovers WHERE operating_date=?", new String[]{str4});
                                            try {
                                                Cursor cursor5 = cursorRawQuery6;
                                                if (cursor5.moveToFirst()) {
                                                    try {
                                                        pair = new Pair(cursor5.getString(0), Long.valueOf(cursor5.getLong(1)));
                                                        th2 = null;
                                                    } catch (Throwable th10) {
                                                        th = th10;
                                                        try {
                                                            throw th;
                                                        } catch (Throwable th11) {
                                                            CloseableKt.closeFinally(cursorRawQuery6, th);
                                                            throw th11;
                                                        }
                                                    }
                                                } else {
                                                    th2 = null;
                                                    pair = null;
                                                }
                                                CloseableKt.closeFinally(cursorRawQuery6, th2);
                                                if (pair != null && !Intrinsics.areEqual(pair.getFirst(), accountId)) {
                                                    Object first = pair.getFirst();
                                                    Intrinsics.checkNotNullExpressionValue(first, "<get-first>(...)");
                                                    Pair<Long, Long> pairUsage2 = usage(writableDatabase, str4, (String) first);
                                                    if (pairUsage2.getFirst().longValue() != 0 || pairUsage2.getSecond().longValue() != 0) {
                                                        throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u0628\u062f\u064a\u0644 \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0628\u0639\u062f \u062a\u0633\u062c\u064a\u0644 \u062f\u0641\u0639\u0627\u062a \u0623\u0648 \u0645\u0633\u062d\u0648\u0628\u0627\u062a \u0639\u0644\u064a\u0647 \u0627\u0644\u064a\u0648\u0645".toString());
                                                    }
                                                }
                                                Cursor cursorRawQuery7 = writableDatabase.rawQuery("SELECT primary_cash_account_id FROM daily_cash_sessions WHERE operating_date=?", new String[]{str4});
                                                try {
                                                    Cursor cursor6 = cursorRawQuery7;
                                                    if (cursor6.moveToFirst()) {
                                                        try {
                                                            string = cursor6.getString(0);
                                                        } catch (Throwable th12) {
                                                            th3 = th12;
                                                            try {
                                                                throw th3;
                                                            } catch (Throwable th13) {
                                                                CloseableKt.closeFinally(cursorRawQuery7, th3);
                                                                throw th13;
                                                            }
                                                        }
                                                    } else {
                                                        string = null;
                                                    }
                                                    CloseableKt.closeFinally(cursorRawQuery7, null);
                                                    if (string != null && Intrinsics.areEqual(string, accountId)) {
                                                        throw new IllegalArgumentException("\u064a\u062c\u0628 \u0641\u0635\u0644 \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0627\u0644\u0645\u062f\u0648\u0631 \u0639\u0646 \u062f\u0631\u062c \u0627\u0644\u0643\u0627\u0634 \u0627\u0644\u0631\u0626\u064a\u0633\u064a \u0627\u0644\u064a\u0648\u0645\u064a".toString());
                                                    }
                                                    if (balance(writableDatabase, accountId) < amount) {
                                                        throw new IllegalArgumentException("\u0627\u0644\u0645\u0628\u0644\u063a \u0627\u0644\u0645\u062e\u0635\u0635 \u064a\u062a\u062c\u0627\u0648\u0632 \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0641\u0639\u0644\u064a \u0627\u0644\u0645\u0648\u062c\u0648\u062f \u0641\u064a \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646".toString());
                                                    }
                                                    ?? contentValues = new ContentValues();
                                                    contentValues.put(writableDatabase, str4);
                                                    contentValues.put("account_id", accountId);
                                                    contentValues.put("allocated_minor", Long.valueOf(amount));
                                                    if (note == null || (string2 = StringsKt.trim((CharSequence) note).toString()) == null || string2.length() <= 0) {
                                                        string2 = null;
                                                    }
                                                    contentValues.put("note", string2);
                                                    contentValues.put("updated_by", actor);
                                                    if (pair == null) {
                                                        contentValues.put("created_at", str5);
                                                    }
                                                    contentValues.put("updated_at", str5);
                                                    if (pair == null) {
                                                        Long.valueOf(writableDatabase.insertOrThrow("daily_supplier_rollovers", null, contentValues));
                                                    } else {
                                                        if (writableDatabase.update("daily_supplier_rollovers", contentValues, "operating_date=?", new String[]{str4}) != 1) {
                                                            throw new IllegalArgumentException("Failed requirement.".toString());
                                                        }
                                                        Unit unit = Unit.INSTANCE;
                                                    }
                                                    ContentValues contentValues2 = new ContentValues();
                                                    contentValues2.put("role_key", "SUPPLIER_ROLLOVER");
                                                    contentValues2.put("account_id", accountId);
                                                    contentValues2.put("linked_at", str5);
                                                    writableDatabase.insertWithOnConflict("system_cash_account_roles", null, contentValues2, 5);
                                                    String str6 = pair == null ? "CREATE_DAILY_SUPPLIER_ROLLOVER" : "UPDATE_DAILY_SUPPLIER_ROLLOVER";
                                                    if (pair != null) {
                                                        Object first2 = pair.getFirst();
                                                        Intrinsics.checkNotNullExpressionValue(first2, "<get-first>(...)");
                                                        str = "{\"account_id\":\"" + esc((String) first2) + "\",\"allocated_minor\":" + pair.getSecond() + "}";
                                                    } else {
                                                        str = null;
                                                    }
                                                    String strEsc = esc(accountId);
                                                    Intrinsics.checkNotNull(string6);
                                                    try {
                                                        audit(writableDatabase, actor, str6, "DAILY_SUPPLIER_ROLLOVER", str4, str, "{\"account_id\":\"" + strEsc + "\",\"account_name\":\"" + esc(string6) + "\",\"allocated_minor\":" + amount + ",\"note\":\"" + esc(note == null ? "" : note) + "\"}", str5);
                                                        if (syncOrigin) {
                                                            str2 = str4;
                                                        } else {
                                                            try {
                                                                str2 = str4;
                                                                JSONObject jSONObjectPut2 = new JSONObject().put("business_operation", "SET_SUPPLIER_ROLLOVER").put("cash_account_id", accountId).put("cash_account_type", "CASH_BOX").put("cash_account_currency_code", "SYP").put("amount_minor", amount).put("note", note == null ? JSONObject.NULL : note).put("occurred_at", str5).put(writableDatabase, str2);
                                                                Context appContext2 = this.appContext;
                                                                Intrinsics.checkNotNullExpressionValue(appContext2, "appContext");
                                                                ?? syncProtocolRepository = new SyncProtocolRepository(appContext2, null, 2, 0 == true ? 1 : 0);
                                                                String string7 = jSONObjectPut2.toString();
                                                                Intrinsics.checkNotNullExpressionValue(string7, "toString(...)");
                                                                syncProtocolRepository.enqueueLocalOperation(writableDatabase, actor, operationId, "SET_SUPPLIER_ROLLOVER", "DAILY_SUPPLIER_ROLLOVER", str2, "UPDATE", string7);
                                                            } catch (Throwable th14) {
                                                                th = th14;
                                                                writableDatabase = writableDatabase;
                                                                writableDatabase.endTransaction();
                                                                throw th;
                                                            }
                                                        }
                                                        writableDatabase.setTransactionSuccessful();
                                                        SupplierRolloverSummary supplierRolloverSummary = new SupplierRolloverSummary(str2, accountId, string6, amount, pairUsage.getFirst().longValue(), pairUsage.getSecond().longValue(), false, 64, null);
                                                        writableDatabase.endTransaction();
                                                        return supplierRolloverSummary;
                                                    } catch (Throwable th15) {
                                                        th = th15;
                                                    }
                                                } catch (Throwable th16) {
                                                    th3 = th16;
                                                }
                                            } catch (Throwable th17) {
                                                th = th17;
                                            }
                                        } catch (Throwable th18) {
                                            writableDatabase = writableDatabase;
                                            try {
                                                throw th18;
                                            } catch (Throwable th19) {
                                                CloseableKt.closeFinally(cursorRawQuery5, th18);
                                                throw th19;
                                            }
                                        }
                                    } catch (Throwable th20) {
                                        writableDatabase = writableDatabase;
                                        try {
                                            throw th20;
                                        } catch (Throwable th21) {
                                            CloseableKt.closeFinally(cursorRawQuery4, th20);
                                            throw th21;
                                        }
                                    }
                                } else {
                                    try {
                                        throw new IllegalArgumentException("\u0627\u062e\u062a\u0631 \u0635\u0646\u062f\u0648\u0642\u0627\u064b \u0646\u0642\u062f\u064a\u0627\u064b \u0646\u0634\u0637\u0627\u064b \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629".toString());
                                    } catch (Throwable th22) {
                                        th = th22;
                                    }
                                }
                            } catch (Throwable th23) {
                                th = th23;
                            }
                            Throwable th24 = th;
                            try {
                                throw th24;
                            } catch (Throwable th25) {
                                CloseableKt.closeFinally(cursorRawQuery3, th24);
                                throw th25;
                            }
                        } catch (Throwable th26) {
                            th = th26;
                            writableDatabase.endTransaction();
                            throw th;
                        }
                    } catch (Throwable th27) {
                        th = th27;
                        writableDatabase = writableDatabase;
                    }
                }
            }
            throw new IllegalArgumentException("\u062a\u0627\u0631\u064a\u062e \u062a\u062e\u0635\u064a\u0635 \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
        } catch (Throwable th28) {
            try {
                throw th28;
            } catch (Throwable th29) {
                CloseableKt.closeFinally(cursorRawQuery, th28);
                throw th29;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final SupplierRolloverSummary currentSupplierRollover() throws IOException {
        String str = today();
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery("SELECT r.account_id,a.name,r.allocated_minor FROM daily_supplier_rollovers r JOIN cash_accounts a ON a.id=r.account_id WHERE r.operating_date=?", new String[]{str});
        try {
            Cursor cursor = cursorRawQuery;
            SupplierRolloverSummary supplierRolloverSummary = null;
            if (cursor.moveToFirst()) {
                SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
                Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
                String string = cursor.getString(0);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                Pair<Long, Long> pairUsage = usage(readableDatabase, str, string);
                String string2 = cursor.getString(0);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                String string3 = cursor.getString(1);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                supplierRolloverSummary = new SupplierRolloverSummary(str, string2, string3, cursor.getLong(2), pairUsage.getFirst().longValue(), pairUsage.getSecond().longValue(), false, 64, null);
            }
            CloseableKt.closeFinally(cursorRawQuery, 0);
            return supplierRolloverSummary;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    public static /* synthetic */ void setWalletOpening$default(DailyBalanceRepository dailyBalanceRepository, String str, String str2, long j, String str3, int i, Object obj) throws Throwable {
        if ((i & 8) != 0) {
            str3 = null;
        }
        dailyBalanceRepository.setWalletOpening(str, str2, j, str3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v11, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v4, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.Object, net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r1v12, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r1v9, types: [android.content.ContentValues] */
    /* JADX WARN: Type inference failed for: r2v21, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r31v0 */
    /* JADX WARN: Type inference failed for: r31v1 */
    /* JADX WARN: Type inference failed for: r31v2 */
    /* JADX WARN: Type inference failed for: r31v3 */
    /* JADX WARN: Type inference failed for: r31v4 */
    /* JADX WARN: Type inference failed for: r31v5 */
    /* JADX WARN: Type inference failed for: r31v6 */
    /* JADX WARN: Type inference failed for: r32v0, types: [com.hazem.dailystore.data.DailyBalanceRepository] */
    /* JADX WARN: Type inference failed for: r4v11, types: [java.lang.StringBuilder] */
    public final void setWalletOpening(String actor, String walletId, long amount, String note) throws Throwable {
        Throwable th;
        Long lValueOf;
        String str;
        ?? r31;
        String str2;
        Intrinsics.checkNotNullParameter(actor, "actor");
        Intrinsics.checkNotNullParameter(walletId, "walletId");
        if (amount < 0) {
            throw new IllegalArgumentException("\u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0627\u0641\u062a\u062a\u0627\u062d\u064a \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0633\u0627\u0644\u0628\u0627\u064b".toString());
        }
        ?? writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        manager(writableDatabase, actor);
        writableDatabase.beginTransaction();
        try {
            Account account = account(writableDatabase, walletId);
            try {
                if (!Intrinsics.areEqual(account.getType(), "WALLET") || !account.getActive()) {
                    throw new IllegalArgumentException("\u0627\u062e\u062a\u0631 \u0645\u062d\u0641\u0638\u0629 \u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a\u0629 \u0646\u0634\u0637\u0629".toString());
                }
                writableDatabase = today();
                Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT opening_minor FROM wallet_daily_openings WHERE operating_date=? AND account_id=?", new String[]{writableDatabase, walletId});
                try {
                    Cursor cursor = cursorRawQuery;
                    if (cursor.moveToFirst()) {
                        try {
                            lValueOf = Long.valueOf(cursor.getLong(0));
                        } catch (Throwable th2) {
                            th = th2;
                            try {
                                throw th;
                            } catch (Throwable th3) {
                                CloseableKt.closeFinally(cursorRawQuery, th);
                                throw th3;
                            }
                        }
                    } else {
                        lValueOf = null;
                    }
                    CloseableKt.closeFinally(cursorRawQuery, null);
                    Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT COUNT(*) FROM cash_transactions WHERE account_id=? AND COALESCE(operating_date,substr(created_at,1,10))=? AND transaction_type<>'WALLET_DAILY_OPENING_ADJUSTMENT'", new String[]{walletId, writableDatabase});
                    try {
                        Cursor cursor2 = cursorRawQuery2;
                        cursor2.moveToFirst();
                        int i = cursor2.getInt(0);
                        CloseableKt.closeFinally(cursorRawQuery2, null);
                        if (i != 0) {
                            throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u0639\u062f\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0627\u0641\u062a\u062a\u0627\u062d\u064a \u0628\u0639\u062f \u0628\u062f\u0621 \u0627\u0644\u062d\u0631\u0643\u0627\u062a \u0627\u0644\u064a\u0648\u0645\u061b \u0627\u0633\u062a\u062e\u062f\u0645 \u0639\u0645\u0644\u064a\u0629 \u062a\u0633\u0648\u064a\u0629 \u0645\u0633\u062a\u0642\u0644\u0629 \u0645\u0648\u062b\u0642\u0629".toString());
                        }
                        long jBalance = balance(writableDatabase, walletId);
                        long jM = DailyBalanceRepository$$ExternalSyntheticBackport0.m(amount, jBalance);
                        String strStamp = stamp();
                        if (jM != 0) {
                            try {
                                String str3 = jM > 0 ? "IN" : "OUT";
                                long jAbs = Math.abs(jM);
                                String currency = account.getCurrency();
                                String str4 = writableDatabase + ":" + walletId;
                                String str5 = "WALLET_OPENING:" + writableDatabase + ":" + walletId + ":" + UUID.randomUUID();
                                Intrinsics.checkNotNull(strStamp);
                                str = ":";
                                r31 = writableDatabase;
                                try {
                                    movement(writableDatabase, walletId, str3, jAbs, currency, "WALLET_DAILY_OPENING_ADJUSTMENT", "WALLET_DAILY_OPENING", str4, str5, actor, "\u062a\u0633\u0648\u064a\u0629 \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0627\u0641\u062a\u062a\u0627\u062d\u064a \u0627\u0644\u064a\u0648\u0645\u064a", strStamp);
                                    r31 = r31;
                                } catch (Throwable th4) {
                                    th = th4;
                                    writableDatabase = r31;
                                    writableDatabase.endTransaction();
                                    throw th;
                                }
                            } catch (Throwable th5) {
                                th = th5;
                                r31 = writableDatabase;
                                writableDatabase = r31;
                                writableDatabase.endTransaction();
                                throw th;
                            }
                        } else {
                            str = ":";
                            r31 = writableDatabase;
                        }
                        ?? contentValues = new ContentValues();
                        contentValues.put("operating_date", writableDatabase);
                        contentValues.put("account_id", walletId);
                        contentValues.put("opening_minor", Long.valueOf(amount));
                        contentValues.put("note", note);
                        contentValues.put("updated_by", actor);
                        if (lValueOf == null) {
                            contentValues.put("created_at", strStamp);
                        }
                        contentValues.put("updated_at", strStamp);
                        ?? r10 = r31;
                        if (lValueOf == null) {
                            r10.insertOrThrow("wallet_daily_openings", null, contentValues);
                        } else {
                            r10.update("wallet_daily_openings", contentValues, "operating_date=? AND account_id=?", new String[]{writableDatabase, walletId});
                        }
                        String str6 = lValueOf == null ? "SET_WALLET_DAILY_OPENING" : "UPDATE_WALLET_DAILY_OPENING";
                        String str7 = writableDatabase + str + walletId;
                        if (lValueOf != null) {
                            str2 = "{\"opening_minor\":" + lValueOf.longValue() + "}";
                        } else {
                            str2 = null;
                        }
                        Intrinsics.checkNotNull(strStamp);
                        audit(r10, actor, str6, "WALLET_DAILY_OPENING", str7, str2, "{\"opening_minor\":" + amount + ",\"balance_before\":" + jBalance + ",\"adjustment_minor\":" + jM + "}", strStamp);
                        r10.setTransactionSuccessful();
                        r10.endTransaction();
                        return;
                    } catch (Throwable th6) {
                        writableDatabase = writableDatabase;
                        try {
                            throw th6;
                        } catch (Throwable th7) {
                            CloseableKt.closeFinally(cursorRawQuery2, th6);
                            throw th7;
                        }
                    }
                } catch (Throwable th8) {
                    th = th8;
                }
                writableDatabase.endTransaction();
                throw th;
            } catch (Throwable th9) {
                th = th9;
            }
        } catch (Throwable th10) {
            th = th10;
            writableDatabase = writableDatabase;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final AccountTransferResult transferCashBetweenBoxes(String actor, String sourceAccountId, String destinationAccountId, long amount, String operationId, String reference, String note) throws Throwable {
        Throwable th;
        String[] strArr;
        Throwable th2;
        String string;
        Throwable th3;
        String string2;
        Intrinsics.checkNotNullParameter(actor, "actor");
        Intrinsics.checkNotNullParameter(sourceAccountId, "sourceAccountId");
        Intrinsics.checkNotNullParameter(destinationAccountId, "destinationAccountId");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        if (amount <= 0 || StringsKt.isBlank(operationId)) {
            throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u063a\u064a\u0631 \u0645\u0643\u062a\u0645\u0644\u0629".toString());
        }
        if (StringsKt.isBlank(sourceAccountId) || StringsKt.isBlank(destinationAccountId) || Intrinsics.areEqual(sourceAccountId, destinationAccountId)) {
            throw new IllegalArgumentException("\u0627\u062e\u062a\u0631 \u0635\u0646\u062f\u0648\u0642\u064a\u0646 \u0645\u062e\u062a\u0644\u0641\u064a\u0646".toString());
        }
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        manager(writableDatabase, actor);
        writableDatabase.beginTransaction();
        try {
            try {
                String strHash = hash(sourceAccountId + "|" + destinationAccountId + "|" + amount + "|" + (reference != null ? reference : "") + "|" + (note == null ? "" : note));
                Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT id,source_account_id,destination_account_id,amount_minor,payload_hash FROM cash_account_transfers WHERE operation_id=?", new String[]{operationId});
                try {
                    Cursor cursor = cursorRawQuery;
                    if (cursor.moveToFirst()) {
                        try {
                            strArr = new String[]{cursor.getString(0), cursor.getString(1), cursor.getString(2), String.valueOf(cursor.getLong(3)), cursor.getString(4)};
                        } catch (Throwable th4) {
                            th = th4;
                            try {
                                throw th;
                            } catch (Throwable th5) {
                                CloseableKt.closeFinally(cursorRawQuery, th);
                                throw th5;
                            }
                        }
                    } else {
                        strArr = null;
                    }
                    CloseableKt.closeFinally(cursorRawQuery, null);
                    if (strArr != null) {
                        if (!Intrinsics.areEqual(strArr[1], sourceAccountId) || !Intrinsics.areEqual(strArr[2], destinationAccountId) || !Intrinsics.areEqual(strArr[3], String.valueOf(amount)) || !Intrinsics.areEqual(strArr[4], strHash)) {
                            throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u0645\u0633\u062a\u062e\u062f\u0645 \u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0645\u062e\u062a\u0644\u0641\u0629".toString());
                        }
                        writableDatabase.setTransactionSuccessful();
                        String str = strArr[0];
                        Intrinsics.checkNotNull(str);
                        AccountTransferResult accountTransferResult = new AccountTransferResult(str, true);
                        writableDatabase.endTransaction();
                        return accountTransferResult;
                    }
                    Account account = account(writableDatabase, sourceAccountId);
                    if (!Intrinsics.areEqual(account.getType(), "CASH_BOX") || !Intrinsics.areEqual(account.getCurrency(), "SYP") || !account.getActive()) {
                        throw new IllegalArgumentException("\u0645\u0635\u062f\u0631 \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u064a\u062c\u0628 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0635\u0646\u062f\u0648\u0642\u0627\u064b \u0646\u0642\u062f\u064a\u0627\u064b \u0646\u0634\u0637\u0627\u064b \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629".toString());
                    }
                    Account account2 = account(writableDatabase, destinationAccountId);
                    if (!Intrinsics.areEqual(account2.getType(), "CASH_BOX") || !Intrinsics.areEqual(account2.getCurrency(), "SYP") || !account2.getActive()) {
                        throw new IllegalArgumentException("\u0648\u062c\u0647\u0629 \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u064a\u062c\u0628 \u0623\u0646 \u062a\u0643\u0648\u0646 \u0635\u0646\u062f\u0648\u0642\u0627\u064b \u0646\u0642\u062f\u064a\u0627\u064b \u0646\u0634\u0637\u0627\u064b \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629".toString());
                    }
                    Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT setting_value FROM app_settings WHERE setting_key='service_recharge_cash_account_id'", (String[]) null);
                    try {
                        Cursor cursor2 = cursorRawQuery2;
                        if (cursor2.moveToFirst()) {
                            try {
                                string = cursor2.getString(0);
                            } catch (Throwable th6) {
                                th2 = th6;
                                try {
                                    throw th2;
                                } catch (Throwable th7) {
                                    CloseableKt.closeFinally(cursorRawQuery2, th2);
                                    throw th7;
                                }
                            }
                        } else {
                            string = null;
                        }
                        CloseableKt.closeFinally(cursorRawQuery2, null);
                        if (Intrinsics.areEqual(sourceAccountId, string)) {
                            throw new IllegalArgumentException("\u0635\u0646\u062f\u0648\u0642 \u0634\u0631\u0627\u0621 \u0631\u0635\u064a\u062f \u0627\u0644\u062e\u062f\u0645\u0627\u062a \u0645\u062e\u0635\u0635 \u0644\u062a\u0639\u0628\u0626\u0629 \u0631\u0635\u064a\u062f \u0627\u0644\u062e\u062f\u0645\u0627\u062a \u0641\u0642\u0637".toString());
                        }
                        Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT account_id FROM daily_supplier_rollovers WHERE operating_date=?", new String[]{today()});
                        try {
                            Cursor cursor3 = cursorRawQuery3;
                            if (cursor3.moveToFirst()) {
                                try {
                                    string2 = cursor3.getString(0);
                                } catch (Throwable th8) {
                                    th3 = th8;
                                    try {
                                        throw th3;
                                    } catch (Throwable th9) {
                                        CloseableKt.closeFinally(cursorRawQuery3, th3);
                                        throw th9;
                                    }
                                }
                            } else {
                                string2 = null;
                            }
                            CloseableKt.closeFinally(cursorRawQuery3, null);
                            if (Intrinsics.areEqual(sourceAccountId, string2)) {
                                throw new IllegalArgumentException("\u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0644\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0645\u062e\u0635\u0635 \u0644\u062f\u0641\u0639\u0627\u062a \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0648\u0627\u0644\u0645\u0633\u062d\u0648\u0628\u0627\u062a \u0627\u0644\u0634\u062e\u0635\u064a\u0629 \u0641\u0642\u0637".toString());
                            }
                            Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT 1 FROM system_cash_account_roles WHERE role_key='SUPPLIER_ROLLOVER' AND account_id=?", new String[]{sourceAccountId});
                            try {
                                boolean zMoveToFirst = cursorRawQuery4.moveToFirst();
                                CloseableKt.closeFinally(cursorRawQuery4, null);
                                if (zMoveToFirst) {
                                    throw new IllegalArgumentException("\u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0644\u0644\u0628\u0636\u0627\u0639\u0629 \u0645\u062e\u0635\u0635 \u0644\u062f\u0641\u0639\u0627\u062a \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0648\u0627\u0644\u0645\u0633\u062d\u0648\u0628\u0627\u062a \u0627\u0644\u0634\u062e\u0635\u064a\u0629 \u0641\u0642\u0637".toString());
                                }
                                if (balance(writableDatabase, sourceAccountId) < amount) {
                                    throw new IllegalArgumentException("\u0631\u0635\u064a\u062f \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0645\u0635\u062f\u0631 \u063a\u064a\u0631 \u0643\u0627\u0641\u064d".toString());
                                }
                                String string3 = UUID.randomUUID().toString();
                                Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
                                String strStamp = stamp();
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("id", string3);
                                contentValues.put("operation_id", operationId);
                                contentValues.put("source_account_id", sourceAccountId);
                                contentValues.put("destination_account_id", destinationAccountId);
                                contentValues.put("amount_minor", Long.valueOf(amount));
                                contentValues.put("currency_code", "SYP");
                                contentValues.put("reference", reference);
                                contentValues.put("note", note);
                                contentValues.put("payload_hash", strHash);
                                contentValues.put("created_by", actor);
                                contentValues.put("created_at", strStamp);
                                writableDatabase.insertOrThrow("cash_account_transfers", null, contentValues);
                                String str2 = operationId + ":OUT";
                                String str3 = "\u062a\u062d\u0648\u064a\u0644 \u0646\u0642\u062f\u064a\u0629 \u0625\u0644\u0649 " + account2.getName();
                                Intrinsics.checkNotNull(strStamp);
                                writableDatabase = writableDatabase;
                                try {
                                    movement(writableDatabase, sourceAccountId, "OUT", amount, "SYP", "ACCOUNT_TRANSFER_OUT", "ACCOUNT_TRANSFER", string3, str2, actor, str3, strStamp);
                                    movement(writableDatabase, destinationAccountId, "IN", amount, "SYP", "ACCOUNT_TRANSFER_IN", "ACCOUNT_TRANSFER", string3, operationId + ":IN", actor, "\u062a\u062d\u0648\u064a\u0644 \u0646\u0642\u062f\u064a\u0629 \u0645\u0646 " + account.getName(), strStamp);
                                    audit(writableDatabase, actor, "CASH_BOX_TO_CASH_BOX_TRANSFER", "CASH_ACCOUNT_TRANSFER", string3, null, "{\"source_account_id\":\"" + esc(sourceAccountId) + "\",\"destination_account_id\":\"" + esc(destinationAccountId) + "\",\"amount_minor\":" + amount + "}", strStamp);
                                    writableDatabase.setTransactionSuccessful();
                                    AccountTransferResult accountTransferResult2 = new AccountTransferResult(string3, false);
                                    writableDatabase.endTransaction();
                                    return accountTransferResult2;
                                } catch (Throwable th10) {
                                    th = th10;
                                }
                            } catch (Throwable th11) {
                                writableDatabase = writableDatabase;
                                try {
                                    throw th11;
                                } catch (Throwable th12) {
                                    CloseableKt.closeFinally(cursorRawQuery4, th11);
                                    throw th12;
                                }
                            }
                        } catch (Throwable th13) {
                            th3 = th13;
                        }
                    } catch (Throwable th14) {
                        th2 = th14;
                    }
                } catch (Throwable th15) {
                    th = th15;
                }
            } catch (Throwable th16) {
                th = th16;
            }
        } catch (Throwable th17) {
            th = th17;
            writableDatabase = writableDatabase;
        }
        writableDatabase.endTransaction();
        throw th;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final AccountTransferResult transferCashToWallet(String actor, String sourceAccountId, String walletId, long amount, String operationId, String reference, String note) throws Throwable {
        Throwable th;
        String[] strArr;
        Throwable th2;
        String string;
        Throwable th3;
        String string2;
        Intrinsics.checkNotNullParameter(actor, "actor");
        Intrinsics.checkNotNullParameter(sourceAccountId, "sourceAccountId");
        Intrinsics.checkNotNullParameter(walletId, "walletId");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        if (amount <= 0 || StringsKt.isBlank(operationId)) {
            throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u063a\u064a\u0631 \u0645\u0643\u062a\u0645\u0644\u0629".toString());
        }
        if (StringsKt.isBlank(sourceAccountId) || StringsKt.isBlank(walletId) || Intrinsics.areEqual(sourceAccountId, walletId)) {
            throw new IllegalArgumentException("\u0627\u062e\u062a\u0631 \u0635\u0646\u062f\u0648\u0642\u0627\u064b \u0648\u0645\u062d\u0641\u0638\u0629 \u0645\u062e\u062a\u0644\u0641\u064a\u0646".toString());
        }
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        manager(writableDatabase, actor);
        writableDatabase.beginTransaction();
        try {
            try {
                String strHash = hash(sourceAccountId + "|" + walletId + "|" + amount + "|" + (reference != null ? reference : "") + "|" + (note == null ? "" : note));
                Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT id,source_account_id,destination_account_id,amount_minor,payload_hash FROM cash_account_transfers WHERE operation_id=?", new String[]{operationId});
                try {
                    Cursor cursor = cursorRawQuery;
                    if (cursor.moveToFirst()) {
                        try {
                            strArr = new String[]{cursor.getString(0), cursor.getString(1), cursor.getString(2), String.valueOf(cursor.getLong(3)), cursor.getString(4)};
                        } catch (Throwable th4) {
                            th = th4;
                            try {
                                throw th;
                            } catch (Throwable th5) {
                                CloseableKt.closeFinally(cursorRawQuery, th);
                                throw th5;
                            }
                        }
                    } else {
                        strArr = null;
                    }
                    CloseableKt.closeFinally(cursorRawQuery, null);
                    if (strArr != null) {
                        if (!Intrinsics.areEqual(strArr[1], sourceAccountId) || !Intrinsics.areEqual(strArr[2], walletId) || !Intrinsics.areEqual(strArr[3], String.valueOf(amount)) || !Intrinsics.areEqual(strArr[4], strHash)) {
                            throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u0645\u0633\u062a\u062e\u062f\u0645 \u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0645\u062e\u062a\u0644\u0641\u0629".toString());
                        }
                        writableDatabase.setTransactionSuccessful();
                        String str = strArr[0];
                        Intrinsics.checkNotNull(str);
                        AccountTransferResult accountTransferResult = new AccountTransferResult(str, true);
                        writableDatabase.endTransaction();
                        return accountTransferResult;
                    }
                    Account account = account(writableDatabase, sourceAccountId);
                    if (!Intrinsics.areEqual(account.getType(), "CASH_BOX") || !Intrinsics.areEqual(account.getCurrency(), "SYP") || !account.getActive()) {
                        throw new IllegalArgumentException("\u0645\u0635\u062f\u0631 \u0627\u0644\u062a\u0645\u0648\u064a\u0644 \u064a\u062c\u0628 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0635\u0646\u062f\u0648\u0642\u0627\u064b \u0646\u0642\u062f\u064a\u0627\u064b \u0646\u0634\u0637\u0627\u064b \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629".toString());
                    }
                    Account account2 = account(writableDatabase, walletId);
                    if (!Intrinsics.areEqual(account2.getType(), "WALLET") || !Intrinsics.areEqual(account2.getCurrency(), "SYP") || !account2.getActive()) {
                        throw new IllegalArgumentException("\u0648\u062c\u0647\u0629 \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u064a\u062c\u0628 \u0623\u0646 \u062a\u0643\u0648\u0646 \u0645\u062d\u0641\u0638\u0629 \u0646\u0634\u0637\u0629 \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629".toString());
                    }
                    Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT 1 FROM service_wallet_account_links WHERE account_id=?", new String[]{walletId});
                    try {
                        boolean zMoveToFirst = cursorRawQuery2.moveToFirst();
                        CloseableKt.closeFinally(cursorRawQuery2, null);
                        if (zMoveToFirst) {
                            throw new IllegalArgumentException("\u0645\u062d\u0627\u0641\u0638 \u062a\u0634\u063a\u064a\u0644 \u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0648\u0634\u0627\u0645 \u0643\u0627\u0634 \u062a\u064f\u063a\u0630\u0649 \u062d\u0635\u0631\u0627\u064b \u0645\u0646 \u0635\u0646\u062f\u0648\u0642 \u0634\u0631\u0627\u0621 \u0631\u0635\u064a\u062f \u0627\u0644\u062e\u062f\u0645\u0627\u062a \u0639\u0628\u0631 \u0639\u0645\u0644\u064a\u0629 \u0627\u0644\u062a\u0639\u0628\u0626\u0629 \u0627\u0644\u0645\u062e\u0635\u0635\u0629".toString());
                        }
                        Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT setting_value FROM app_settings WHERE setting_key='service_recharge_cash_account_id'", (String[]) null);
                        try {
                            Cursor cursor2 = cursorRawQuery3;
                            if (cursor2.moveToFirst()) {
                                try {
                                    string = cursor2.getString(0);
                                } catch (Throwable th6) {
                                    th2 = th6;
                                    try {
                                        throw th2;
                                    } catch (Throwable th7) {
                                        CloseableKt.closeFinally(cursorRawQuery3, th2);
                                        throw th7;
                                    }
                                }
                            } else {
                                string = null;
                            }
                            CloseableKt.closeFinally(cursorRawQuery3, null);
                            if (Intrinsics.areEqual(sourceAccountId, string)) {
                                throw new IllegalArgumentException("\u0627\u0633\u062a\u062e\u062f\u0645 \u0639\u0645\u0644\u064a\u0629 \u0634\u0631\u0627\u0621 \u0631\u0635\u064a\u062f \u0627\u0644\u062e\u062f\u0645\u0627\u062a \u0627\u0644\u0645\u062e\u0635\u0635\u0629 \u0644\u062a\u0645\u0648\u064a\u0644 \u0645\u062d\u0641\u0638\u0629 \u0627\u0644\u062e\u062f\u0645\u0629".toString());
                            }
                            Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT account_id FROM daily_supplier_rollovers WHERE operating_date=?", new String[]{today()});
                            try {
                                Cursor cursor3 = cursorRawQuery4;
                                if (cursor3.moveToFirst()) {
                                    try {
                                        string2 = cursor3.getString(0);
                                    } catch (Throwable th8) {
                                        th3 = th8;
                                        try {
                                            throw th3;
                                        } catch (Throwable th9) {
                                            CloseableKt.closeFinally(cursorRawQuery4, th3);
                                            throw th9;
                                        }
                                    }
                                } else {
                                    string2 = null;
                                }
                                CloseableKt.closeFinally(cursorRawQuery4, null);
                                if (Intrinsics.areEqual(sourceAccountId, string2)) {
                                    throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u0645\u0648\u064a\u0644 \u0627\u0644\u0645\u062d\u0627\u0641\u0638 \u0645\u0646 \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0644\u0644\u0645\u0648\u0631\u062f\u064a\u0646".toString());
                                }
                                Cursor cursorRawQuery5 = writableDatabase.rawQuery("SELECT 1 FROM system_cash_account_roles WHERE role_key='SUPPLIER_ROLLOVER' AND account_id=?", new String[]{sourceAccountId});
                                try {
                                    boolean zMoveToFirst2 = cursorRawQuery5.moveToFirst();
                                    CloseableKt.closeFinally(cursorRawQuery5, null);
                                    if (zMoveToFirst2) {
                                        throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u0645\u0648\u064a\u0644 \u0627\u0644\u0645\u062d\u0627\u0641\u0638 \u0645\u0646 \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0644\u0644\u0628\u0636\u0627\u0639\u0629".toString());
                                    }
                                    if (balance(writableDatabase, sourceAccountId) < amount) {
                                        throw new IllegalArgumentException("\u0631\u0635\u064a\u062f \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0645\u0635\u062f\u0631 \u063a\u064a\u0631 \u0643\u0627\u0641\u064d".toString());
                                    }
                                    String string3 = UUID.randomUUID().toString();
                                    Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
                                    String strStamp = stamp();
                                    ContentValues contentValues = new ContentValues();
                                    contentValues.put("id", string3);
                                    contentValues.put("operation_id", operationId);
                                    contentValues.put("source_account_id", sourceAccountId);
                                    contentValues.put("destination_account_id", walletId);
                                    contentValues.put("amount_minor", Long.valueOf(amount));
                                    contentValues.put("currency_code", "SYP");
                                    contentValues.put("reference", reference);
                                    contentValues.put("note", note);
                                    contentValues.put("payload_hash", strHash);
                                    contentValues.put("created_by", actor);
                                    contentValues.put("created_at", strStamp);
                                    writableDatabase.insertOrThrow("cash_account_transfers", null, contentValues);
                                    String str2 = operationId + ":OUT";
                                    String str3 = "\u062a\u062d\u0648\u064a\u0644 \u0646\u0642\u062f\u064a\u0629 \u0625\u0644\u0649 " + account2.getName();
                                    Intrinsics.checkNotNull(strStamp);
                                    writableDatabase = writableDatabase;
                                    try {
                                        movement(writableDatabase, sourceAccountId, "OUT", amount, "SYP", "ACCOUNT_TRANSFER_OUT", "ACCOUNT_TRANSFER", string3, str2, actor, str3, strStamp);
                                        movement(writableDatabase, walletId, "IN", amount, "SYP", "ACCOUNT_TRANSFER_IN", "ACCOUNT_TRANSFER", string3, operationId + ":IN", actor, "\u062a\u0645\u0648\u064a\u0644 \u0645\u062d\u0641\u0638\u0629 \u0645\u0646 " + account.getName(), strStamp);
                                        audit(writableDatabase, actor, "CASH_TO_WALLET_TRANSFER", "CASH_ACCOUNT_TRANSFER", string3, null, "{\"source_account_id\":\"" + esc(sourceAccountId) + "\",\"destination_account_id\":\"" + esc(walletId) + "\",\"amount_minor\":" + amount + "}", strStamp);
                                        writableDatabase.setTransactionSuccessful();
                                        AccountTransferResult accountTransferResult2 = new AccountTransferResult(string3, false);
                                        writableDatabase.endTransaction();
                                        return accountTransferResult2;
                                    } catch (Throwable th10) {
                                        th = th10;
                                    }
                                } catch (Throwable th11) {
                                    writableDatabase = writableDatabase;
                                    try {
                                        throw th11;
                                    } catch (Throwable th12) {
                                        CloseableKt.closeFinally(cursorRawQuery5, th11);
                                        throw th12;
                                    }
                                }
                            } catch (Throwable th13) {
                                th3 = th13;
                            }
                        } catch (Throwable th14) {
                            th2 = th14;
                        }
                    } catch (Throwable th15) {
                        writableDatabase = writableDatabase;
                        try {
                            throw th15;
                        } catch (Throwable th16) {
                            CloseableKt.closeFinally(cursorRawQuery2, th15);
                            throw th16;
                        }
                    }
                } catch (Throwable th17) {
                    th = th17;
                }
            } catch (Throwable th18) {
                th = th18;
            }
        } catch (Throwable th19) {
            th = th19;
            writableDatabase = writableDatabase;
        }
        writableDatabase.endTransaction();
        throw th;
    }

    public static /* synthetic */ void addPersonalWithdrawal$default(DailyBalanceRepository dailyBalanceRepository, String str, String str2, long j, String str3, String str4, int i, Object obj) throws Throwable {
        if ((i & 16) != 0) {
            str4 = null;
        }
        dailyBalanceRepository.addPersonalWithdrawal(str, str2, j, str3, str4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void addPersonalWithdrawal(String actor, String accountId, long amount, String operationId, String note) throws Throwable {
        Throwable th;
        String[] strArr;
        Intrinsics.checkNotNullParameter(actor, "actor");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        if (amount <= 0 || StringsKt.isBlank(operationId)) {
            throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0645\u0633\u062d\u0648\u0628 \u0627\u0644\u0634\u062e\u0635\u064a \u063a\u064a\u0631 \u0645\u0643\u062a\u0645\u0644\u0629".toString());
        }
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        manager(writableDatabase, actor);
        writableDatabase.beginTransaction();
        try {
            SupplierRolloverSummary supplierRolloverSummaryRollover = rollover(writableDatabase, accountId);
            try {
                if (supplierRolloverSummaryRollover == null) {
                    throw new IllegalArgumentException("\u064a\u062c\u0628 \u062a\u062d\u062f\u064a\u062f \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0644\u0647\u0630\u0627 \u0627\u0644\u064a\u0648\u0645 \u0623\u0648\u0644\u0627\u064b");
                }
                Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT account_id,amount_minor,transaction_type,created_by,description FROM cash_transactions WHERE operation_id=?", new String[]{operationId});
                try {
                    Cursor cursor = cursorRawQuery;
                    if (cursor.moveToFirst()) {
                        try {
                            strArr = new String[]{cursor.getString(0), String.valueOf(cursor.getLong(1)), cursor.getString(2), cursor.getString(3), cursor.getString(4)};
                        } catch (Throwable th2) {
                            th = th2;
                            try {
                                throw th;
                            } catch (Throwable th3) {
                                CloseableKt.closeFinally(cursorRawQuery, th);
                                throw th3;
                            }
                        }
                    } else {
                        strArr = null;
                    }
                    CloseableKt.closeFinally(cursorRawQuery, null);
                    String str = "\u0645\u0633\u062d\u0648\u0628\u0627\u062a \u0634\u062e\u0635\u064a\u0629";
                    if (strArr != null) {
                        if (Intrinsics.areEqual(strArr[0], accountId) && Intrinsics.areEqual(strArr[1], String.valueOf(amount)) && Intrinsics.areEqual(strArr[2], "PERSONAL_WITHDRAWAL") && Intrinsics.areEqual(strArr[3], actor)) {
                            String str2 = strArr[4];
                            if (note != null) {
                                str = note;
                            }
                            if (Intrinsics.areEqual(str2, str)) {
                                writableDatabase.setTransactionSuccessful();
                                writableDatabase.endTransaction();
                                return;
                            }
                        }
                        throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0645\u0633\u062a\u062e\u062f\u0645 \u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0645\u062e\u062a\u0644\u0641\u0629".toString());
                    }
                    if (supplierRolloverSummaryRollover.getAvailableMinor() < amount) {
                        throw new IllegalArgumentException(("\u0627\u0644\u0645\u062a\u0627\u062d \u0645\u0646 \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0644\u0627 \u064a\u0643\u0641\u064a. \u0627\u0644\u0645\u062a\u0627\u062d: " + supplierRolloverSummaryRollover.getAvailableMinor() + " \u0644.\u0633").toString());
                    }
                    if (balance(writableDatabase, accountId) < amount) {
                        throw new IllegalArgumentException("\u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0641\u0639\u0644\u064a \u0641\u064a \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0644\u0627 \u064a\u0643\u0641\u064a \u0644\u0644\u0645\u0633\u062d\u0648\u0628 \u0627\u0644\u0634\u062e\u0635\u064a".toString());
                    }
                    String strStamp = stamp();
                    String str3 = note == null ? "\u0645\u0633\u062d\u0648\u0628\u0627\u062a \u0634\u062e\u0635\u064a\u0629" : note;
                    Intrinsics.checkNotNull(strStamp);
                    movement(writableDatabase, accountId, "OUT", amount, "SYP", "PERSONAL_WITHDRAWAL", "PERSONAL_WITHDRAWAL", operationId, operationId, actor, str3, strStamp);
                    audit(writableDatabase, actor, "PERSONAL_WITHDRAWAL", "CASH_ACCOUNT", accountId, null, "{\"amount_minor\":" + amount + ",\"note\":\"" + esc(note == null ? "" : note) + "\"}", strStamp);
                    writableDatabase.setTransactionSuccessful();
                    writableDatabase.endTransaction();
                } catch (Throwable th4) {
                    th = th4;
                }
            } catch (Throwable th5) {
                th = th5;
                writableDatabase.endTransaction();
                throw th;
            }
        } catch (Throwable th6) {
            th = th6;
            writableDatabase = writableDatabase;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final AccountTransferResult transferWalletToDrawer(String actor, String walletId, String destinationAccountId, long amount, String operationId, String reference, String note) throws Throwable {
        Throwable th;
        String[] strArr;
        Throwable th2;
        String string;
        Throwable th3;
        String string2;
        Intrinsics.checkNotNullParameter(actor, "actor");
        Intrinsics.checkNotNullParameter(walletId, "walletId");
        Intrinsics.checkNotNullParameter(destinationAccountId, "destinationAccountId");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        if (amount <= 0 || StringsKt.isBlank(operationId)) {
            throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u063a\u064a\u0631 \u0645\u0643\u062a\u0645\u0644\u0629".toString());
        }
        if (StringsKt.isBlank(destinationAccountId) || Intrinsics.areEqual(destinationAccountId, walletId)) {
            throw new IllegalArgumentException("\u0627\u062e\u062a\u0631 \u0635\u0646\u062f\u0648\u0642\u0627\u064b \u0646\u0642\u062f\u064a\u0627\u064b \u0645\u062e\u062a\u0644\u0641\u0627\u064b \u0639\u0646 \u0627\u0644\u0645\u062d\u0641\u0638\u0629".toString());
        }
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        manager(writableDatabase, actor);
        writableDatabase.beginTransaction();
        try {
            try {
                String strHash = hash(walletId + "|" + destinationAccountId + "|" + amount + "|" + (reference != null ? reference : "") + "|" + (note == null ? "" : note));
                Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT id,source_account_id,destination_account_id,amount_minor,payload_hash FROM cash_account_transfers WHERE operation_id=?", new String[]{operationId});
                try {
                    Cursor cursor = cursorRawQuery;
                    if (cursor.moveToFirst()) {
                        try {
                            strArr = new String[]{cursor.getString(0), cursor.getString(1), cursor.getString(2), String.valueOf(cursor.getLong(3)), cursor.getString(4)};
                        } catch (Throwable th4) {
                            th = th4;
                            try {
                                throw th;
                            } catch (Throwable th5) {
                                CloseableKt.closeFinally(cursorRawQuery, th);
                                throw th5;
                            }
                        }
                    } else {
                        strArr = null;
                    }
                    CloseableKt.closeFinally(cursorRawQuery, null);
                    if (strArr != null) {
                        if (!Intrinsics.areEqual(strArr[1], walletId) || !Intrinsics.areEqual(strArr[2], destinationAccountId) || !Intrinsics.areEqual(strArr[3], String.valueOf(amount)) || !Intrinsics.areEqual(strArr[4], strHash)) {
                            throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u0645\u0633\u062a\u062e\u062f\u0645 \u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0645\u062e\u062a\u0644\u0641\u0629".toString());
                        }
                        writableDatabase.setTransactionSuccessful();
                        String str = strArr[0];
                        Intrinsics.checkNotNull(str);
                        AccountTransferResult accountTransferResult = new AccountTransferResult(str, true);
                        writableDatabase.endTransaction();
                        return accountTransferResult;
                    }
                    Account account = account(writableDatabase, walletId);
                    if (!Intrinsics.areEqual(account.getType(), "WALLET") || !account.getActive() || !Intrinsics.areEqual(account.getCurrency(), "SYP")) {
                        throw new IllegalArgumentException("\u0645\u0635\u062f\u0631 \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u064a\u062c\u0628 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0645\u062d\u0641\u0638\u0629 \u0633\u0648\u0631\u064a\u0629 \u0646\u0634\u0637\u0629".toString());
                    }
                    Account account2 = account(writableDatabase, destinationAccountId);
                    if (!Intrinsics.areEqual(account2.getType(), "CASH_BOX") || !account2.getActive() || !Intrinsics.areEqual(account2.getCurrency(), "SYP")) {
                        throw new IllegalArgumentException("\u0648\u062c\u0647\u0629 \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u064a\u062c\u0628 \u0623\u0646 \u062a\u0643\u0648\u0646 \u0635\u0646\u062f\u0648\u0642\u0627\u064b \u0646\u0642\u062f\u064a\u0627\u064b \u0646\u0634\u0637\u0627\u064b \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629".toString());
                    }
                    Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT setting_value FROM app_settings WHERE setting_key='service_recharge_cash_account_id'", (String[]) null);
                    try {
                        Cursor cursor2 = cursorRawQuery2;
                        if (cursor2.moveToFirst()) {
                            try {
                                string = cursor2.getString(0);
                            } catch (Throwable th6) {
                                th2 = th6;
                                try {
                                    throw th2;
                                } catch (Throwable th7) {
                                    CloseableKt.closeFinally(cursorRawQuery2, th2);
                                    throw th7;
                                }
                            }
                        } else {
                            string = null;
                        }
                        CloseableKt.closeFinally(cursorRawQuery2, null);
                        if (Intrinsics.areEqual(destinationAccountId, string)) {
                            throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0645\u0642\u0628\u0648\u0636\u0627\u062a \u0625\u0644\u0649 \u0635\u0646\u062f\u0648\u0642 \u0634\u0631\u0627\u0621 \u0631\u0635\u064a\u062f \u0627\u0644\u062e\u062f\u0645\u0627\u062a".toString());
                        }
                        Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT account_id FROM daily_supplier_rollovers WHERE operating_date=?", new String[]{today()});
                        try {
                            Cursor cursor3 = cursorRawQuery3;
                            if (cursor3.moveToFirst()) {
                                try {
                                    string2 = cursor3.getString(0);
                                } catch (Throwable th8) {
                                    th3 = th8;
                                    try {
                                        throw th3;
                                    } catch (Throwable th9) {
                                        CloseableKt.closeFinally(cursorRawQuery3, th3);
                                        throw th9;
                                    }
                                }
                            } else {
                                string2 = null;
                            }
                            CloseableKt.closeFinally(cursorRawQuery3, null);
                            if (Intrinsics.areEqual(destinationAccountId, string2)) {
                                throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0645\u0642\u0628\u0648\u0636\u0627\u062a \u0625\u0644\u0649 \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0644\u0644\u0645\u0648\u0631\u062f\u064a\u0646".toString());
                            }
                            Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT 1 FROM system_cash_account_roles WHERE role_key='SUPPLIER_ROLLOVER' AND account_id=?", new String[]{destinationAccountId});
                            try {
                                boolean zMoveToFirst = cursorRawQuery4.moveToFirst();
                                CloseableKt.closeFinally(cursorRawQuery4, null);
                                if (zMoveToFirst) {
                                    throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0645\u062d\u0641\u0638\u0629 \u0625\u0644\u0649 \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0644\u0644\u0628\u0636\u0627\u0639\u0629".toString());
                                }
                                if (balance(writableDatabase, walletId) < amount) {
                                    throw new IllegalArgumentException("\u0631\u0635\u064a\u062f \u0627\u0644\u0645\u062d\u0641\u0638\u0629 \u063a\u064a\u0631 \u0643\u0627\u0641\u064d".toString());
                                }
                                String string3 = UUID.randomUUID().toString();
                                Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
                                String strStamp = stamp();
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("id", string3);
                                contentValues.put("operation_id", operationId);
                                contentValues.put("source_account_id", walletId);
                                contentValues.put("destination_account_id", destinationAccountId);
                                contentValues.put("amount_minor", Long.valueOf(amount));
                                contentValues.put("currency_code", "SYP");
                                contentValues.put("reference", reference);
                                contentValues.put("note", note);
                                contentValues.put("payload_hash", strHash);
                                contentValues.put("created_by", actor);
                                contentValues.put("created_at", strStamp);
                                writableDatabase.insertOrThrow("cash_account_transfers", null, contentValues);
                                String str2 = operationId + ":OUT";
                                String str3 = "\u062a\u062d\u0648\u064a\u0644 \u062f\u0627\u062e\u0644\u064a \u2013 \u062a\u0635\u0631\u064a\u0641 \u0645\u062d\u0641\u0638\u0629 \u0625\u0644\u0649 " + account2.getName();
                                Intrinsics.checkNotNull(strStamp);
                                writableDatabase = writableDatabase;
                                try {
                                    movement(writableDatabase, walletId, "OUT", amount, "SYP", "ACCOUNT_TRANSFER_OUT", "ACCOUNT_TRANSFER", string3, str2, actor, str3, strStamp);
                                    movement(writableDatabase, destinationAccountId, "IN", amount, "SYP", "ACCOUNT_TRANSFER_IN", "ACCOUNT_TRANSFER", string3, operationId + ":IN", actor, "\u062a\u062d\u0648\u064a\u0644 \u062f\u0627\u062e\u0644\u064a \u2013 \u062a\u0635\u0631\u064a\u0641 \u0645\u062d\u0641\u0638\u0629 \u0645\u0646 " + account.getName(), strStamp);
                                    audit(writableDatabase, actor, "WALLET_TO_CASH_TRANSFER", "CASH_ACCOUNT_TRANSFER", string3, null, "{\"source_account_id\":\"" + walletId + "\",\"destination_account_id\":\"" + destinationAccountId + "\",\"source_name\":\"" + esc(account.getName()) + "\",\"destination_name\":\"" + esc(account2.getName()) + "\",\"amount_minor\":" + amount + ",\"currency\":\"SYP\"}", strStamp);
                                    writableDatabase.setTransactionSuccessful();
                                    AccountTransferResult accountTransferResult2 = new AccountTransferResult(string3, false);
                                    writableDatabase.endTransaction();
                                    return accountTransferResult2;
                                } catch (Throwable th10) {
                                    th = th10;
                                    writableDatabase.endTransaction();
                                    throw th;
                                }
                            } catch (Throwable th11) {
                                writableDatabase = writableDatabase;
                                try {
                                    throw th11;
                                } catch (Throwable th12) {
                                    CloseableKt.closeFinally(cursorRawQuery4, th11);
                                    throw th12;
                                }
                            }
                        } catch (Throwable th13) {
                            th3 = th13;
                        }
                    } catch (Throwable th14) {
                        th2 = th14;
                    }
                } catch (Throwable th15) {
                    th = th15;
                }
            } catch (Throwable th16) {
                th = th16;
                writableDatabase.endTransaction();
                throw th;
            }
        } catch (Throwable th17) {
            th = th17;
            writableDatabase = writableDatabase;
        }
    }

    public static /* synthetic */ DailyCashReport dailyReport$default(DailyBalanceRepository dailyBalanceRepository, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = dailyBalanceRepository.today();
        }
        return dailyBalanceRepository.dailyReport(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final DailyCashReport dailyReport(String date) throws IOException {
        Cursor cursor;
        Throwable th;
        Cursor cursor2;
        Throwable th2;
        long jOpeningBefore;
        DailyBalanceRepository dailyBalanceRepository = this;
        String date2 = date;
        String str = "getString(...)";
        Intrinsics.checkNotNullParameter(date2, "date");
        if (!new Regex("\\d{4}-\\d{2}-\\d{2}").matches(date2)) {
            throw new IllegalArgumentException("\u062a\u0627\u0631\u064a\u062e \u0627\u0644\u062a\u0634\u063a\u064a\u0644 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
        }
        SQLiteDatabase readableDatabase = dailyBalanceRepository.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        ArrayList arrayList = new ArrayList();
        Throwable th3 = null;
        Cursor cursorRawQuery = readableDatabase.rawQuery("SELECT id,name,account_type,currency_code,opening_balance_minor FROM cash_accounts WHERE is_active=1 ORDER BY account_type,name", (String[]) null);
        try {
            Cursor cursor3 = cursorRawQuery;
            while (cursor3.moveToNext()) {
                try {
                    String string = cursor3.getString(0);
                    long j = cursor3.getLong(4);
                    Cursor cursorRawQuery2 = readableDatabase.rawQuery("SELECT opening_minor FROM wallet_daily_openings WHERE operating_date=? AND account_id=?", new String[]{date2, string});
                    try {
                        Cursor cursor4 = cursorRawQuery2;
                        if (cursor4.moveToFirst()) {
                            try {
                                jOpeningBefore = cursor4.getLong(0);
                                cursor2 = cursorRawQuery2;
                            } catch (Throwable th4) {
                                th2 = th4;
                                cursor2 = cursorRawQuery2;
                                try {
                                    throw th2;
                                } catch (Throwable th5) {
                                    CloseableKt.closeFinally(cursor2, th2);
                                    throw th5;
                                }
                            }
                        } else {
                            Intrinsics.checkNotNull(string);
                            cursor2 = cursorRawQuery2;
                            try {
                                jOpeningBefore = openingBefore(readableDatabase, string, j, date);
                            } catch (Throwable th6) {
                                th = th6;
                                th2 = th;
                                throw th2;
                            }
                        }
                        CloseableKt.closeFinally(cursor2, th3);
                        Intrinsics.checkNotNull(string);
                        List<Long> list = dailyBalanceRepository.totals(readableDatabase, string, date2);
                        cursorRawQuery = cursorRawQuery;
                        try {
                            long j2 = jOpeningBefore;
                            String str2 = str;
                            long jM = MainActivity$$ExternalSyntheticBackport0.m(j2, DailyBalanceRepository$$ExternalSyntheticBackport0.m(MainActivity$$ExternalSyntheticBackport0.m(MainActivity$$ExternalSyntheticBackport0.m(list.get(0).longValue(), list.get(1).longValue()), MainActivity$$ExternalSyntheticBackport0.m(list.get(2).longValue(), MainActivity$$ExternalSyntheticBackport0.m(list.get(5).longValue(), MainActivity$$ExternalSyntheticBackport0.m(list.get(8).longValue(), list.get(10).longValue())))), MainActivity$$ExternalSyntheticBackport0.m(MainActivity$$ExternalSyntheticBackport0.m(list.get(3).longValue(), list.get(4).longValue()), MainActivity$$ExternalSyntheticBackport0.m(list.get(6).longValue(), MainActivity$$ExternalSyntheticBackport0.m(list.get(7).longValue(), MainActivity$$ExternalSyntheticBackport0.m(list.get(9).longValue(), list.get(11).longValue()))))));
                            String string2 = cursor3.getString(1);
                            Intrinsics.checkNotNullExpressionValue(string2, str2);
                            String string3 = cursor3.getString(2);
                            Intrinsics.checkNotNullExpressionValue(string3, str2);
                            String string4 = cursor3.getString(3);
                            Intrinsics.checkNotNullExpressionValue(string4, str2);
                            arrayList.add(new DailyAccountSummary(string, string2, string3, string4, j2, list.get(0).longValue(), list.get(1).longValue(), list.get(8).longValue(), list.get(10).longValue(), list.get(2).longValue(), list.get(3).longValue(), list.get(4).longValue(), list.get(5).longValue(), list.get(6).longValue(), list.get(9).longValue(), list.get(11).longValue(), list.get(7).longValue(), jM));
                            dailyBalanceRepository = this;
                            date2 = date;
                            str = str2;
                            cursorRawQuery = cursorRawQuery;
                            th3 = null;
                        } catch (Throwable th7) {
                            th = th7;
                            th = th;
                            cursor = cursorRawQuery;
                            try {
                                throw th;
                            } catch (Throwable th8) {
                                CloseableKt.closeFinally(cursor, th);
                                throw th8;
                            }
                        }
                    } catch (Throwable th9) {
                        th = th9;
                        cursor2 = cursorRawQuery2;
                    }
                } catch (Throwable th10) {
                    th = th10;
                    cursorRawQuery = cursorRawQuery;
                }
            }
            Cursor cursor5 = cursorRawQuery;
            try {
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(cursor5, null);
                ArrayList arrayList2 = arrayList;
                ArrayList arrayList3 = new ArrayList();
                for (Object obj : arrayList2) {
                    if (Intrinsics.areEqual(((DailyAccountSummary) obj).getAccountType(), "WALLET")) {
                        arrayList3.add(obj);
                    }
                }
                ArrayList arrayList4 = arrayList3;
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (Object obj2 : arrayList4) {
                    String currencyCode = ((DailyAccountSummary) obj2).getCurrencyCode();
                    Object obj3 = linkedHashMap.get(currencyCode);
                    if (obj3 == null) {
                        obj3 = (List) new ArrayList();
                        linkedHashMap.put(currencyCode, obj3);
                    }
                    ((List) obj3).add(obj2);
                }
                LinkedHashMap linkedHashMap2 = new LinkedHashMap(MapsKt.mapCapacity(linkedHashMap.size()));
                for (Map.Entry entry : linkedHashMap.entrySet()) {
                    Object key = entry.getKey();
                    Iterator it = ((Iterable) entry.getValue()).iterator();
                    long electronicReceiptsMinor = 0;
                    while (it.hasNext()) {
                        electronicReceiptsMinor += ((DailyAccountSummary) it.next()).getElectronicReceiptsMinor();
                    }
                    linkedHashMap2.put(key, Long.valueOf(electronicReceiptsMinor));
                }
                LinkedHashMap linkedHashMap3 = new LinkedHashMap();
                for (Object obj4 : arrayList4) {
                    String currencyCode2 = ((DailyAccountSummary) obj4).getCurrencyCode();
                    Object obj5 = linkedHashMap3.get(currencyCode2);
                    if (obj5 == null) {
                        obj5 = (List) new ArrayList();
                        linkedHashMap3.put(currencyCode2, obj5);
                    }
                    ((List) obj5).add(obj4);
                }
                LinkedHashMap linkedHashMap4 = new LinkedHashMap(MapsKt.mapCapacity(linkedHashMap3.size()));
                for (Map.Entry entry2 : linkedHashMap3.entrySet()) {
                    Object key2 = entry2.getKey();
                    Iterator it2 = ((Iterable) entry2.getValue()).iterator();
                    long liquidatableElectronicReceiptsMinor = 0;
                    while (it2.hasNext()) {
                        liquidatableElectronicReceiptsMinor += ((DailyAccountSummary) it2.next()).getLiquidatableElectronicReceiptsMinor();
                    }
                    linkedHashMap4.put(key2, Long.valueOf(liquidatableElectronicReceiptsMinor));
                }
                ArrayList arrayList5 = new ArrayList();
                for (Object obj6 : arrayList2) {
                    if (Intrinsics.areEqual(((DailyAccountSummary) obj6).getAccountType(), "CASH_BOX")) {
                        arrayList5.add(obj6);
                    }
                }
                LinkedHashMap linkedHashMap5 = new LinkedHashMap();
                for (Object obj7 : arrayList5) {
                    String currencyCode3 = ((DailyAccountSummary) obj7).getCurrencyCode();
                    Object obj8 = linkedHashMap5.get(currencyCode3);
                    if (obj8 == null) {
                        obj8 = (List) new ArrayList();
                        linkedHashMap5.put(currencyCode3, obj8);
                    }
                    ((List) obj8).add(obj7);
                }
                LinkedHashMap linkedHashMap6 = new LinkedHashMap(MapsKt.mapCapacity(linkedHashMap5.size()));
                for (Map.Entry entry3 : linkedHashMap5.entrySet()) {
                    Object key3 = entry3.getKey();
                    long jM2 = 0;
                    for (DailyAccountSummary dailyAccountSummary : (Iterable) entry3.getValue()) {
                        jM2 += MainActivity$$ExternalSyntheticBackport0.m(MainActivity$$ExternalSyntheticBackport0.m(dailyAccountSummary.getSalesReceiptsMinor(), dailyAccountSummary.getDebtCollectionsMinor()), dailyAccountSummary.getServiceTransferReceiptsMinor());
                    }
                    linkedHashMap6.put(key3, Long.valueOf(jM2));
                }
                return new DailyCashReport(date, arrayList, linkedHashMap2, linkedHashMap4, linkedHashMap6, rolloverForDate(readableDatabase, date));
            } catch (Throwable th11) {
                th = th11;
                cursor = cursor5;
                th = th;
                throw th;
            }
        } catch (Throwable th12) {
            th = th12;
            cursor = cursorRawQuery;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final List<String> todayTransactions() throws IOException {
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery("SELECT t.created_at,a.name,t.transaction_type,t.direction,t.amount_minor,t.currency_code,COALESCE(t.description,'') FROM cash_transactions t JOIN cash_accounts a ON a.id=t.account_id WHERE COALESCE(t.operating_date,substr(t.created_at,1,10))=? ORDER BY t.created_at DESC", new String[]{today()});
        try {
            Cursor cursor = cursorRawQuery;
            while (cursor.moveToNext()) {
                StoreTime storeTime = StoreTime.INSTANCE;
                SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
                Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
                String string = cursor.getString(0);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String timestamp = storeTime.formatTimestamp(readableDatabase, string);
                String string2 = cursor.getString(1);
                String string3 = cursor.getString(2);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                String strTypeName = typeName(string3);
                String str = Intrinsics.areEqual(cursor.getString(3), "IN") ? "\u0648\u0627\u0631\u062f" : "\u0635\u0627\u062f\u0631";
                arrayList.add(timestamp + " \u2022 " + string2 + " \u2022 " + strTypeName + " \u2022 " + str + " \u2022 " + cursor.getLong(4) + " " + cursor.getString(5) + " \u2022 " + cursor.getString(6));
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

    private final List<Long> totals(SQLiteDatabase db, String id, String date) throws IOException {
        int i;
        Long lValueOf;
        int i2;
        Long lValueOf2;
        ArrayList arrayList = new ArrayList(12);
        for (int i3 = 0; i3 < 12; i3++) {
            arrayList.add(0L);
        }
        ArrayList arrayList2 = arrayList;
        Cursor cursorRawQuery = db.rawQuery("SELECT transaction_type,direction,SUM(amount_minor) FROM cash_transactions WHERE account_id=? AND COALESCE(operating_date,substr(created_at,1,10))=? AND transaction_type<>'WALLET_DAILY_OPENING_ADJUSTMENT' GROUP BY transaction_type,direction", new String[]{id, date});
        try {
            Cursor cursor = cursorRawQuery;
            while (cursor.moveToNext()) {
                String string = cursor.getString(0);
                String string2 = cursor.getString(1);
                long j = cursor.getLong(2);
                if (Intrinsics.areEqual(string, "SALE_RECEIPT") && Intrinsics.areEqual(string2, "IN")) {
                    arrayList2.set(0, Long.valueOf(((Number) arrayList2.get(0)).longValue() + j));
                } else if ((Intrinsics.areEqual(string, "CUSTOMER_CREDIT_COLLECTION") || Intrinsics.areEqual(string, "SERVICE_TRANSFER_DEBT_COLLECTION")) && Intrinsics.areEqual(string2, "IN")) {
                    arrayList2.set(1, Long.valueOf(((Number) arrayList2.get(1)).longValue() + j));
                } else if (Intrinsics.areEqual(string, "SERVICE_TRANSFER_RECEIPT") && Intrinsics.areEqual(string2, "IN")) {
                    arrayList2.set(8, Long.valueOf(((Number) arrayList2.get(8)).longValue() + j));
                } else {
                    if (Intrinsics.areEqual(string, "ACCOUNT_TRANSFER_IN") && Intrinsics.areEqual(string2, "IN")) {
                        i2 = 5;
                        lValueOf2 = Long.valueOf(((Number) arrayList2.get(5)).longValue() + j);
                    } else if (Intrinsics.areEqual(string, "PURCHASE_PAYMENT") && Intrinsics.areEqual(string2, "OUT")) {
                        i2 = 3;
                        lValueOf2 = Long.valueOf(((Number) arrayList2.get(3)).longValue() + j);
                    } else {
                        if (Intrinsics.areEqual(string, "PERSONAL_WITHDRAWAL") && Intrinsics.areEqual(string2, "OUT")) {
                            i = 4;
                            lValueOf = Long.valueOf(((Number) arrayList2.get(4)).longValue() + j);
                        } else if (Intrinsics.areEqual(string, "ACCOUNT_TRANSFER_OUT") && Intrinsics.areEqual(string2, "OUT")) {
                            i = 6;
                            lValueOf = Long.valueOf(((Number) arrayList2.get(6)).longValue() + j);
                        } else if (Intrinsics.areEqual(string, "OPERATING_EXPENSE") && Intrinsics.areEqual(string2, "OUT")) {
                            arrayList2.set(9, Long.valueOf(((Number) arrayList2.get(9)).longValue() + j));
                        } else if (Intrinsics.areEqual(string, "PURCHASE_RETURN_REFUND") && Intrinsics.areEqual(string2, "IN")) {
                            arrayList2.set(10, Long.valueOf(((Number) arrayList2.get(10)).longValue() + j));
                        } else if (Intrinsics.areEqual(string, "SALE_RETURN_REFUND") && Intrinsics.areEqual(string2, "OUT")) {
                            arrayList2.set(11, Long.valueOf(((Number) arrayList2.get(11)).longValue() + j));
                        } else if (Intrinsics.areEqual(string2, "IN")) {
                            arrayList2.set(2, Long.valueOf(((Number) arrayList2.get(2)).longValue() + j));
                        } else if (Intrinsics.areEqual(string2, "OUT")) {
                            arrayList2.set(7, Long.valueOf(((Number) arrayList2.get(7)).longValue() + j));
                        }
                        arrayList2.set(i, lValueOf);
                    }
                    arrayList2.set(i2, lValueOf2);
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            return arrayList2;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final long openingBefore(SQLiteDatabase db, String id, long base, String date) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT COALESCE(SUM(CASE WHEN direction='IN' THEN amount_minor ELSE -amount_minor END),0) FROM cash_transactions WHERE account_id=? AND COALESCE(operating_date,substr(created_at,1,10))<?", new String[]{id, date});
        try {
            Cursor cursor = cursorRawQuery;
            cursor.moveToFirst();
            long j = cursor.getLong(0);
            CloseableKt.closeFinally(cursorRawQuery, null);
            return MainActivity$$ExternalSyntheticBackport0.m(base, j);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final Pair<Long, Long> usage(SQLiteDatabase db, String date, String id) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT transaction_type,SUM(amount_minor) FROM cash_transactions WHERE account_id=? AND direction='OUT' AND COALESCE(operating_date,substr(created_at,1,10))=? AND transaction_type IN ('PURCHASE_PAYMENT','PERSONAL_WITHDRAWAL') GROUP BY transaction_type", new String[]{id, date});
        try {
            Cursor cursor = cursorRawQuery;
            long j = 0;
            long j2 = 0;
            while (cursor.moveToNext()) {
                if (Intrinsics.areEqual(cursor.getString(0), "PURCHASE_PAYMENT")) {
                    j = cursor.getLong(1);
                } else {
                    j2 = cursor.getLong(1);
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            return new Pair<>(Long.valueOf(j), Long.valueOf(j2));
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final SupplierRolloverSummary rolloverForDate(SQLiteDatabase db, String date) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT r.account_id,a.name,r.allocated_minor FROM daily_supplier_rollovers r JOIN cash_accounts a ON a.id=r.account_id WHERE r.operating_date=?", new String[]{date});
        try {
            Cursor cursor = cursorRawQuery;
            Triple triple = cursor.moveToFirst() ? new Triple(cursor.getString(0), cursor.getString(1), Long.valueOf(cursor.getLong(2))) : null;
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (triple == null) {
                return null;
            }
            Object first = triple.getFirst();
            Intrinsics.checkNotNullExpressionValue(first, "<get-first>(...)");
            Pair<Long, Long> pairUsage = usage(db, date, (String) first);
            Object first2 = triple.getFirst();
            Intrinsics.checkNotNullExpressionValue(first2, "<get-first>(...)");
            Object second = triple.getSecond();
            Intrinsics.checkNotNullExpressionValue(second, "<get-second>(...)");
            return new SupplierRolloverSummary(date, (String) first2, (String) second, ((Number) triple.getThird()).longValue(), pairUsage.getFirst().longValue(), pairUsage.getSecond().longValue(), false, 64, null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final SupplierRolloverSummary rollover(SQLiteDatabase db, String id) throws IOException {
        String str = today();
        Cursor cursorRawQuery = db.rawQuery("SELECT a.name,r.allocated_minor FROM daily_supplier_rollovers r JOIN cash_accounts a ON a.id=r.account_id WHERE r.operating_date=? AND r.account_id=?", new String[]{str, id});
        try {
            Cursor cursor = cursorRawQuery;
            Pair pair = cursor.moveToFirst() ? new Pair(cursor.getString(0), Long.valueOf(cursor.getLong(1))) : null;
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (pair == null) {
                return null;
            }
            Pair<Long, Long> pairUsage = usage(db, str, id);
            Object first = pair.getFirst();
            Intrinsics.checkNotNullExpressionValue(first, "<get-first>(...)");
            return new SupplierRolloverSummary(str, id, (String) first, ((Number) pair.getSecond()).longValue(), pairUsage.getFirst().longValue(), pairUsage.getSecond().longValue(), false, 64, null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    /* JADX INFO: compiled from: DailyBalanceRepository.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0007H\u00c6\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0015\u001a\u00020\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001a"}, d2 = {"Lcom/hazem/dailystore/data/DailyBalanceRepository$Account;", "", "name", "", "type", "currency", "active", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getName", "()Ljava/lang/String;", "getType", "getCurrency", "getActive", "()Z", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class Account {
        private final boolean active;
        private final String currency;
        private final String name;
        private final String type;

        public static /* synthetic */ Account copy$default(Account account, String str, String str2, String str3, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                str = account.name;
            }
            if ((i & 2) != 0) {
                str2 = account.type;
            }
            if ((i & 4) != 0) {
                str3 = account.currency;
            }
            if ((i & 8) != 0) {
                z = account.active;
            }
            return account.copy(str, str2, str3, z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getCurrency() {
            return this.currency;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getActive() {
            return this.active;
        }

        public final Account copy(String name, String type, String currency, boolean active) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(currency, "currency");
            return new Account(name, type, currency, active);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Account)) {
                return false;
            }
            Account account = (Account) other;
            return Intrinsics.areEqual(this.name, account.name) && Intrinsics.areEqual(this.type, account.type) && Intrinsics.areEqual(this.currency, account.currency) && this.active == account.active;
        }

        public int hashCode() {
            return (((((this.name.hashCode() * 31) + this.type.hashCode()) * 31) + this.currency.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.active);
        }

        public String toString() {
            return "Account(name=" + this.name + ", type=" + this.type + ", currency=" + this.currency + ", active=" + this.active + ")";
        }

        public Account(String name, String type, String currency, boolean z) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(currency, "currency");
            this.name = name;
            this.type = type;
            this.currency = currency;
            this.active = z;
        }

        public final boolean getActive() {
            return this.active;
        }

        public final String getCurrency() {
            return this.currency;
        }

        public final String getName() {
            return this.name;
        }

        public final String getType() {
            return this.type;
        }
    }

    private final Account account(SQLiteDatabase db, String id) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT name,account_type,currency_code,is_active FROM cash_accounts WHERE id=?", new String[]{id});
        try {
            Cursor cursor = cursorRawQuery;
            if (!cursor.moveToFirst()) {
                throw new IllegalArgumentException("\u0627\u0644\u062d\u0633\u0627\u0628 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
            }
            String string = cursor.getString(0);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String string2 = cursor.getString(1);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            String string3 = cursor.getString(2);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
            Account account = new Account(string, string2, string3, cursor.getInt(3) == 1);
            CloseableKt.closeFinally(cursorRawQuery, null);
            return account;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final long balance(SQLiteDatabase db, String id) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT opening_balance_minor FROM cash_accounts WHERE id=?", new String[]{id});
        try {
            Cursor cursor = cursorRawQuery;
            if (!cursor.moveToFirst()) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            long j = cursor.getLong(0);
            CloseableKt.closeFinally(cursorRawQuery, null);
            Cursor cursorRawQuery2 = db.rawQuery("SELECT COALESCE(SUM(CASE WHEN direction='IN' THEN amount_minor ELSE -amount_minor END),0) FROM cash_transactions WHERE account_id=?", new String[]{id});
            try {
                Cursor cursor2 = cursorRawQuery2;
                cursor2.moveToFirst();
                long j2 = cursor2.getLong(0);
                CloseableKt.closeFinally(cursorRawQuery2, null);
                return MainActivity$$ExternalSyntheticBackport0.m(j, j2);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(cursorRawQuery2, th);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            try {
                throw th3;
            } catch (Throwable th4) {
                CloseableKt.closeFinally(cursorRawQuery, th3);
                throw th4;
            }
        }
    }

    private final void movement(SQLiteDatabase db, String id, String direction, long amount, String currency, String type, String sourceType, String sourceId, String op, String actor, String description, String now) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", UUID.randomUUID().toString());
        contentValues.put("account_id", id);
        contentValues.put("direction", direction);
        contentValues.put("amount_minor", Long.valueOf(amount));
        contentValues.put("currency_code", currency);
        contentValues.put("transaction_type", type);
        contentValues.put("source_type", sourceType);
        contentValues.put("source_id", sourceId);
        contentValues.put("operation_id", op);
        contentValues.put("description", description);
        contentValues.put("created_by", actor);
        contentValues.put("operating_date", StoreTime.INSTANCE.operatingDate(db, now));
        contentValues.put("created_at", now);
        db.insertOrThrow("cash_transactions", null, contentValues);
    }

    private final void audit(SQLiteDatabase db, String user, String action, String kind, String id, String before, String after, String now) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", UUID.randomUUID().toString());
        contentValues.put("user_id", user);
        contentValues.put("action", action);
        contentValues.put("entity_type", kind);
        contentValues.put("entity_id", id);
        if (before == null) {
            contentValues.putNull("before_json");
        } else {
            contentValues.put("before_json", before);
        }
        contentValues.put("after_json", after);
        contentValues.put("created_at", now);
        db.insertOrThrow("audit_log", null, contentValues);
    }

    private final void manager(SQLiteDatabase db, String id) throws IOException {
        PermissionPolicy.INSTANCE.require(db, id, PermissionPolicy.DAILY_CASH_MANAGE);
        Cursor cursorRawQuery = db.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            String string = cursor.moveToFirst() ? cursor.getString(0) : null;
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (Intrinsics.areEqual(string, "SECONDARY")) {
                throw new IllegalArgumentException("\u0647\u0630\u0647 \u0627\u0644\u062d\u0631\u0643\u0629 \u0627\u0644\u0645\u0627\u0644\u064a\u0629 \u0644\u0645 \u064a\u0643\u062a\u0645\u0644 \u0645\u0633\u0627\u0631 \u0627\u0639\u062a\u0645\u0627\u062f\u0647\u0627 \u0648\u0645\u0632\u0627\u0645\u0646\u062a\u0647\u0627 \u0628\u0639\u062f\u061b \u0646\u0641\u0651\u0630\u0647\u0627 \u0645\u0646 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0644\u062a\u062c\u0646\u0628 \u0627\u062e\u062a\u0644\u0627\u0641 \u0627\u0644\u0623\u0631\u0635\u062f\u0629".toString());
            }
            if (string != null && !Intrinsics.areEqual(string, "PRIMARY")) {
                throw new IllegalArgumentException("\u062f\u0648\u0631 \u0627\u0644\u062c\u0647\u0627\u0632 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d \u0644\u062a\u0646\u0641\u064a\u0630 \u0627\u0644\u062d\u0631\u0643\u0629 \u0627\u0644\u0645\u0627\u0644\u064a\u0629".toString());
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:39:0x007b A[RETURN, SYNTHETIC] */
    private final String typeName(String t) {
        switch (t.hashCode()) {
            case -1707720684:
                return !t.equals("PERSONAL_WITHDRAWAL") ? t : "\u0645\u0633\u062d\u0648\u0628\u0627\u062a \u0634\u062e\u0635\u064a\u0629";
            case -1060690400:
                return !t.equals("SALE_RECEIPT") ? t : "\u0642\u0628\u0636 \u0645\u0628\u064a\u0639\u0627\u062a";
            case -1014642520:
                return !t.equals("PURCHASE_PAYMENT") ? t : "\u062f\u0641\u0639\u0629 \u0645\u0648\u0631\u062f";
            case -657890031:
                return !t.equals("SERVICE_RECHARGE") ? t : "\u0634\u0631\u0627\u0621 \u0631\u0635\u064a\u062f \u062e\u062f\u0645\u0629";
            case -362385972:
                if (t.equals("ACCOUNT_TRANSFER_OUT")) {
                    return "\u062a\u062d\u0648\u064a\u0644 \u062f\u0627\u062e\u0644\u064a \u2013 \u062a\u0635\u0631\u064a\u0641 \u0645\u062d\u0641\u0638\u0629";
                }
                return t;
            case -201303383:
                return !t.equals("PURCHASE_RETURN_REFUND") ? t : "\u0645\u0628\u0644\u063a \u0645\u0633\u062a\u0631\u062f \u0645\u0646 \u0627\u0644\u0645\u0648\u0631\u062f";
            case -45749393:
                return !t.equals("SALE_RETURN_REFUND") ? t : "\u0631\u062f \u0645\u0628\u0644\u063a \u0645\u0631\u062a\u062c\u0639 \u0644\u0644\u0639\u0645\u064a\u0644";
            case -34628373:
                return !t.equals("WALLET_TOP_UP") ? t : "\u062a\u063a\u0630\u064a\u0629 \u0645\u062d\u0641\u0638\u0629";
            case 254658947:
                return !t.equals("CUSTOMER_CREDIT_COLLECTION") ? t : "\u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0648\u0646 \u0641\u0648\u0627\u062a\u064a\u0631 \u0627\u0644\u0628\u064a\u0639";
            case 618339296:
                return !t.equals("SERVICE_TRANSFER_DEBT_COLLECTION") ? t : "\u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0646 \u062a\u062d\u0648\u064a\u0644 \u0631\u0635\u064a\u062f";
            case 625483310:
                return !t.equals("SERVICE_TRANSFER_RECEIPT") ? t : "\u0645\u0642\u0628\u0648\u0636 \u062a\u062d\u0648\u064a\u0644 \u0631\u0635\u064a\u062f";
            case 819593927:
                if (t.equals("ACCOUNT_TRANSFER_IN")) {
                    return "\u062a\u062d\u0648\u064a\u0644 \u062f\u0627\u062e\u0644\u064a \u2013 \u062a\u0635\u0631\u064a\u0641 \u0645\u062d\u0641\u0638\u0629";
                }
                return t;
            case 1956654874:
                return !t.equals("OPERATING_EXPENSE") ? t : "\u0645\u0635\u0631\u0648\u0641 \u062a\u0634\u063a\u064a\u0644\u064a";
            default:
                return t;
        }
    }

    private final String esc(String s) {
        return StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(s, "\\", "\\\\", false, 4, (Object) null), "\"", "\\\"", false, 4, (Object) null), "\n", "\\n", false, 4, (Object) null), "\r", "\\r", false, 4, (Object) null);
    }

    private final String hash(String s) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = s.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrDigest = messageDigest.digest(bytes);
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
        return ArraysKt.joinToString$default(bArrDigest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.hazem.dailystore.data.DailyBalanceRepository$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DailyBalanceRepository.hash$lambda$109(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence hash$lambda$109(byte b) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    private final String today() {
        StoreTime storeTime = StoreTime.INSTANCE;
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        return StoreTime.today$default(storeTime, readableDatabase, null, 2, null);
    }

    private final String stamp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.format(new Date());
    }
}
