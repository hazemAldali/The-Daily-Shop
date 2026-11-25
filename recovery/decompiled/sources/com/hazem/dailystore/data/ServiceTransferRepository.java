package com.hazem.dailystore.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import androidx.core.app.NotificationCompat;
import androidx.core.view.PointerIconCompat;
import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import java.io.IOException;
import java.math.BigInteger;
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
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import net.zetetic.database.sqlcipher.SQLiteDatabase;
import org.json.JSONObject;

/* JADX INFO: compiled from: ServiceTransferRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0018\u0018\u0000 j2\u00020\u0001:\u0001jB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eJ\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011J\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00112\b\b\u0002\u0010\u0015\u001a\u00020\u000eJ\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u00112\b\b\u0002\u0010\u0018\u001a\u00020\u0019J*\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u000eJ\b\u0010\u001f\u001a\u0004\u0018\u00010\u000eJ\u0016\u0010 \u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u000eJF\u0010\"\u001a\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020\u001d2\u0006\u0010%\u001a\u00020\u000e2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u000eJ\u0090\u0001\u0010'\u001a\u00020(2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020\u001d2\u0006\u0010+\u001a\u00020\u001d2\u0006\u0010,\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\u000e2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010/\u001a\u0002002\b\b\u0002\u00101\u001a\u00020\u000e2\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u00103\u001a\u000200J&\u00104\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u00105\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\u000e2\u0006\u00106\u001a\u00020\u000eJ\u0014\u00107\u001a\u00020\f2\n\u00108\u001a\u000609j\u0002`:H\u0002J\u0014\u0010\u000b\u001a\u00020\f2\n\u00108\u001a\u000609j\u0002`:H\u0002J:\u0010;\u001a\u00020\f2\n\u00108\u001a\u000609j\u0002`:2\u0006\u0010<\u001a\u00020\u000e2\u0006\u0010=\u001a\u00020\u000e2\u0006\u0010>\u001a\u00020\u000e2\f\u0010?\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0011H\u0002J\u001c\u0010\r\u001a\u00020\u000e2\n\u00108\u001a\u000609j\u0002`:2\u0006\u0010<\u001a\u00020\u000eH\u0002J\u001c\u0010@\u001a\u00020\f2\n\u00108\u001a\u000609j\u0002`:2\u0006\u0010<\u001a\u00020\u000eH\u0002J$\u0010A\u001a\u00020\u001d2\n\u00108\u001a\u000609j\u0002`:2\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020\u000eH\u0002J \u0010B\u001a\u00020\u001d2\u0006\u0010C\u001a\u00020\u001d2\u0006\u0010D\u001a\u00020\u001d2\u0006\u0010E\u001a\u00020\u001dH\u0002J\u001c\u0010F\u001a\u00020\u001d2\n\u00108\u001a\u000609j\u0002`:2\u0006\u0010<\u001a\u00020\u000eH\u0002J\u0080\u0001\u0010G\u001a\u00020\f2\n\u00108\u001a\u000609j\u0002`:2\u0006\u0010<\u001a\u00020\u000e2\u0006\u0010H\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010I\u001a\u00020\u000e2\b\u0010J\u001a\u0004\u0018\u00010\u000e2\u0006\u0010%\u001a\u00020\u000e2\u0006\u0010K\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010L\u001a\u00020\u000e2\n\b\u0002\u0010M\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010N\u001a\u0004\u0018\u00010\u000eH\u0002Jp\u0010O\u001a\u00020\f2\n\u00108\u001a\u000609j\u0002`:2\u0006\u0010!\u001a\u00020\u000e2\u0006\u0010H\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010I\u001a\u00020\u000e2\u0006\u0010P\u001a\u00020\u000e2\u0006\u0010J\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010Q\u001a\u00020\u000e2\u0006\u0010L\u001a\u00020\u000e2\n\b\u0002\u0010N\u001a\u0004\u0018\u00010\u000eH\u0002J.\u0010R\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u0002000S2\n\u00108\u001a\u000609j\u0002`:2\u0006\u0010T\u001a\u00020\u000eH\u0002J\u001c\u0010U\u001a\u00020\u001d2\n\u00108\u001a\u000609j\u0002`:2\u0006\u0010T\u001a\u00020\u000eH\u0002J\u001c\u0010V\u001a\u00020\f2\n\u00108\u001a\u000609j\u0002`:2\u0006\u0010\u001b\u001a\u00020\u000eH\u0002J\u001c\u0010W\u001a\u00020\f2\n\u00108\u001a\u000609j\u0002`:2\u0006\u0010\u001b\u001a\u00020\u000eH\u0002JF\u0010X\u001a\u00020\f2\n\u00108\u001a\u000609j\u0002`:2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010Y\u001a\u00020\u000e2\u0006\u0010T\u001a\u00020\u000e2\b\u0010Z\u001a\u0004\u0018\u00010\u000e2\u0006\u0010[\u001a\u00020\u000e2\u0006\u0010L\u001a\u00020\u000eH\u0002J\u0010\u0010\\\u001a\u00020\u000e2\u0006\u0010<\u001a\u00020\u000eH\u0002J\u0010\u0010]\u001a\u00020\u000e2\u0006\u0010I\u001a\u00020\u000eH\u0002J\u0018\u0010^\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010_\u001a\u00020\u001dH\u0002J \u0010`\u001a\u00020\u001d2\u0006\u0010a\u001a\u00020\u001d2\u0006\u0010b\u001a\u00020\u001d2\u0006\u0010c\u001a\u00020\u001dH\u0002J\u0010\u0010d\u001a\u00020\u001d2\u0006\u0010a\u001a\u00020\u001dH\u0002J\u0010\u0010e\u001a\u00020\u000e2\u0006\u0010a\u001a\u00020\u000eH\u0002J\u0010\u0010f\u001a\u00020\u000e2\u0006\u0010a\u001a\u00020\u000eH\u0002J\b\u0010g\u001a\u00020\u000eH\u0002J\u0015\u0010h\u001a\n \t*\u0004\u0018\u00010\u000e0\u000eH\u0002\u00a2\u0006\u0002\u0010iR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0018\u0010\b\u001a\n \t*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\n\u00a8\u0006k"}, d2 = {"Lcom/hazem/dailystore/data/ServiceTransferRepository;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "database", "Lcom/hazem/dailystore/data/AppDatabase;", "appContext", "kotlin.jvm.PlatformType", "Landroid/content/Context;", "ensureSystemAccounts", "", "serviceAccountId", "", "serviceKey", "listWallets", "", "Lcom/hazem/dailystore/data/ServiceWalletSummary;", "dailySummary", "Lcom/hazem/dailystore/data/ServiceDailySummary;", "date", "listTransfers", "Lcom/hazem/dailystore/data/ServiceTransferRecord;", "limit", "", "setOpeningBalance", "actor", "amount", "", "note", "rechargeAccountId", "setRechargeAccount", "accountId", "recharge", "sourceAccountId", "floatAmount", "operationId", "reference", "executeTransfer", "Lcom/hazem/dailystore/data/ServiceTransferResult;", "serviceType", "deliveredMinor", "providerFeeMinor", "receiptAccountId", "occurredAt", "operatingDate", "syncOrigin", "", "paymentMode", "customerId", "legacyBalanceDelivered", "reverseTransfer", "transferId", "reason", "ensureWallets", "db", "Lnet/zetetic/database/sqlcipher/SQLiteDatabase;", "Lcom/hazem/dailystore/data/AppSqliteDatabase;", "ensureServiceAccountLink", "key", "systemId", "defaultName", "aliases", "migrateLegacyWalletMovements", "realizedCreditProfitMilliForDate", "realizedProfitMilliSyp", "profitMilliSyp", "chargeMinor", "paidMinor", "walletBalance", "insertWalletMovement", "direction", "type", "sourceId", "fee", "now", "payloadHash", "eventDate", "insertCashMovement", "sourceType", "description", "receiptAccount", "Lkotlin/Triple;", "id", "cashBalance", "requireManager", "requireActiveUser", "audit", "action", "before", "after", "serviceName", "serviceTypeName", "percent", "rate", "roundRatioMilli", "value", "numerator", "denominator", "roundMilliToWhole", "hash", "esc", "today", "stamp", "()Ljava/lang/String;", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ServiceTransferRepository {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String SHAM_CASH = "SHAM_CASH";
    public static final String SHAM_CASH_TRANSFER = "SHAM_CASH";
    public static final String SYRIATEL_BALANCE = "SYRIATEL_BALANCE";
    public static final String SYRIATEL_CASH = "SYRIATEL_CASH";
    public static final long SYRIATEL_CASH_FEE_THRESHOLD_MINOR = 200;
    public static final String SYRIATEL_SHARED = "SYRIATEL_SHARED";
    private final Context appContext;
    private final AppDatabase database;

    public ServiceTransferRepository(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.database = AppDatabase.INSTANCE.getInstance(context);
        this.appContext = context.getApplicationContext();
    }

    /* JADX INFO: compiled from: ServiceTransferRepository.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/hazem/dailystore/data/ServiceTransferRepository$Companion;", "", "<init>", "()V", ServiceTransferRepository.SYRIATEL_SHARED, "", "SHAM_CASH", ServiceTransferRepository.SYRIATEL_BALANCE, ServiceTransferRepository.SYRIATEL_CASH, "SHAM_CASH_TRANSFER", "SYRIATEL_CASH_FEE_THRESHOLD_MINOR", "", "expectedProviderFeeMinor", "serviceType", "deliveredMinor", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final long expectedProviderFeeMinor(String serviceType, long deliveredMinor) {
            Intrinsics.checkNotNullParameter(serviceType, "serviceType");
            if (deliveredMinor <= 0) {
                throw new IllegalArgumentException("\u0645\u0628\u0644\u063a \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u064a\u062c\u0628 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0623\u0643\u0628\u0631 \u0645\u0646 \u0635\u0641\u0631".toString());
            }
            if (Intrinsics.areEqual(serviceType, ServiceTransferRepository.SYRIATEL_CASH)) {
                return 2L;
            }
            return Intrinsics.areEqual(serviceType, "SHAM_CASH") ? 0L : -1L;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void ensureSystemAccounts() {
        Context appContext = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        if (new SyncProtocolRepository(appContext, null, 2, 0 == true ? 1 : 0).isConfiguredSecondary()) {
            return;
        }
        Context appContext2 = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext2, "appContext");
        new CashAccountRepository(appContext2).ensureSystemCashAccounts();
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        writableDatabase.beginTransaction();
        try {
            ensureSystemAccounts(writableDatabase);
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String serviceAccountId(String serviceKey) {
        Intrinsics.checkNotNullParameter(serviceKey, "serviceKey");
        if (!Intrinsics.areEqual(serviceKey, SYRIATEL_SHARED) && !Intrinsics.areEqual(serviceKey, "SHAM_CASH")) {
            throw new IllegalArgumentException("\u0645\u062d\u0641\u0638\u0629 \u0627\u0644\u062e\u062f\u0645\u0629 \u063a\u064a\u0631 \u0645\u0639\u0631\u0648\u0641\u0629".toString());
        }
        ensureSystemAccounts();
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        return serviceAccountId(readableDatabase, serviceKey);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final List<ServiceWalletSummary> listWallets() throws IOException {
        ensureSystemAccounts();
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        List<String> listListOf = CollectionsKt.listOf((Object[]) new String[]{SYRIATEL_SHARED, "SHAM_CASH"});
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listListOf, 10));
        for (String str : listListOf) {
            String strServiceAccountId = serviceAccountId(readableDatabase, str);
            Cursor cursorRawQuery = readableDatabase.rawQuery("SELECT name FROM cash_accounts WHERE id=?", new String[]{strServiceAccountId});
            try {
                Cursor cursor = cursorRawQuery;
                cursor.moveToFirst();
                String string = cursor.getString(0);
                CloseableKt.closeFinally(cursorRawQuery, null);
                Intrinsics.checkNotNull(string);
                arrayList.add(new ServiceWalletSummary(str, string, walletBalance(readableDatabase, str), strServiceAccountId));
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(cursorRawQuery, th);
                    throw th2;
                }
            }
        }
        return arrayList;
    }

    public static /* synthetic */ List dailySummary$default(ServiceTransferRepository serviceTransferRepository, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = serviceTransferRepository.today();
        }
        return serviceTransferRepository.dailySummary(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final List<ServiceDailySummary> dailySummary(String date) throws IOException {
        String str;
        long j;
        Intrinsics.checkNotNullParameter(date, "date");
        if (!new Regex("\\d{4}-\\d{2}-\\d{2}").matches(date)) {
            throw new IllegalArgumentException("\u062a\u0627\u0631\u064a\u062e \u0627\u0644\u0643\u0634\u0641 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
        }
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery("SELECT service_type,SUM(operation_count),SUM(delivered_minor),SUM(customer_receipts_minor),SUM(profit_minor),SUM(provider_fee_minor),SUM(credit_charges_minor),SUM(delivered_milli_syp),SUM(profit_milli_syp),SUM(debited_minor)\n               FROM (\n                 SELECT service_type,COUNT(*) operation_count,COALESCE(SUM(amount_delivered_minor),0) delivered_minor,\n                        COALESCE(SUM(CASE WHEN payment_mode='CASH' THEN customer_charge_minor ELSE 0 END),0) customer_receipts_minor,\n                        COALESCE(SUM(CASE WHEN payment_mode='CASH' THEN profit_minor ELSE 0 END),0) profit_minor,COALESCE(SUM(provider_fee_minor),0) provider_fee_minor,\n                        COALESCE(SUM(CASE WHEN payment_mode='CREDIT' THEN customer_charge_minor ELSE 0 END),0) credit_charges_minor,\n                        COALESCE(SUM(amount_delivered_milli_syp),0) delivered_milli_syp,\n                        COALESCE(SUM(CASE WHEN payment_mode='CASH' THEN profit_milli_syp ELSE 0 END),0) profit_milli_syp,\n                        COALESCE(SUM(amount_debited_minor),0) debited_minor\n                 FROM service_transfers WHERE operating_date=? AND status='POSTED' GROUP BY service_type\n                 UNION ALL\n                 SELECT CASE service_key WHEN 'SYRIATEL_SHARED' THEN 'SYRIATEL_BALANCE' ELSE 'SHAM_CASH' END,\n                        0,0,0,0,0,0,0,0,0 FROM service_wallet_transactions\n                 WHERE transaction_type='RECHARGE' AND operating_date=?\n                 GROUP BY service_key\n                 UNION ALL\n                 -- Collections are real customer receipts on the collection date,\n                 -- but remain collections (not new transfers or sales).\n                 SELECT t.service_type,0,0,COALESCE(SUM(p.amount_minor),0),0,0,0,0,0,0\n                 FROM service_transfer_receivable_payments p\n                 JOIN service_transfers t ON t.id=p.transfer_id\n                 WHERE p.operating_date=? AND t.status='POSTED'\n                 GROUP BY t.service_type\n               ) GROUP BY service_type ORDER BY service_type", new String[]{date, date, date});
        try {
            Cursor cursor = cursorRawQuery;
            while (cursor.moveToNext()) {
                String string = cursor.getString(0);
                long j2 = cursor.getLong(2);
                if (Intrinsics.areEqual(string, SYRIATEL_BALANCE)) {
                    str = SYRIATEL_SHARED;
                } else {
                    str = Intrinsics.areEqual(string, "SHAM_CASH") ? "SHAM_CASH" : null;
                }
                if (str != null) {
                    Cursor cursorRawQuery2 = this.database.getReadableDatabase().rawQuery("SELECT COALESCE(SUM(fee_minor),0) FROM service_wallet_transactions WHERE service_key=? AND transaction_type='RECHARGE' AND operating_date=?", new String[]{str, date});
                    try {
                        Cursor cursor2 = cursorRawQuery2;
                        cursor2.moveToFirst();
                        long j3 = cursor2.getLong(0);
                        CloseableKt.closeFinally(cursorRawQuery2, null);
                        j = j3;
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            CloseableKt.closeFinally(cursorRawQuery2, th);
                            throw th2;
                        }
                    }
                } else {
                    j = 0;
                }
                SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
                Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
                Intrinsics.checkNotNull(string);
                long jRealizedCreditProfitMilliForDate = realizedCreditProfitMilliForDate(readableDatabase, date, string);
                arrayList.add(new ServiceDailySummary(string, serviceTypeName(string), cursor.getInt(1), j2, cursor.getLong(3), MainActivity$$ExternalSyntheticBackport0.m(cursor.getLong(4), roundMilliToWhole(jRealizedCreditProfitMilliForDate)), cursor.getLong(5), j, cursor.getLong(9), cursor.getLong(6), cursor.getLong(7), MainActivity$$ExternalSyntheticBackport0.m(cursor.getLong(8), jRealizedCreditProfitMilliForDate)));
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            return arrayList;
        } catch (Throwable th3) {
            try {
                throw th3;
            } catch (Throwable th4) {
                CloseableKt.closeFinally(cursorRawQuery, th3);
                throw th4;
            }
        }
    }

    public static /* synthetic */ List listTransfers$default(ServiceTransferRepository serviceTransferRepository, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 50;
        }
        return serviceTransferRepository.listTransfers(i);
    }

    public final List<ServiceTransferRecord> listTransfers(int limit) throws IOException {
        ServiceTransferRepository serviceTransferRepository = this;
        ArrayList arrayList = new ArrayList();
        int i = 1;
        Cursor cursorRawQuery = serviceTransferRepository.database.getReadableDatabase().rawQuery("SELECT t.id,t.service_type,t.amount_delivered_minor,t.profit_minor,t.provider_fee_minor,\n                      t.customer_charge_minor,a.name,t.receipt_channel,t.created_at,t.status,t.payment_mode,c.name,\n                      COALESCE(SUM(p.amount_minor),0),t.amount_debited_minor,t.amount_delivered_milli_syp,t.profit_milli_syp\n               FROM service_transfers t JOIN cash_accounts a ON a.id=t.receipt_account_id\n               LEFT JOIN customers c ON c.id=t.customer_id\n               LEFT JOIN service_transfer_receivable_payments p ON p.transfer_id=t.id\n               GROUP BY t.id\n               ORDER BY t.created_at DESC LIMIT ?", new String[]{String.valueOf(RangesKt.coerceIn(limit, 1, 200))});
        try {
            Cursor cursor = cursorRawQuery;
            while (cursor.moveToNext()) {
                String string = cursor.getString(i);
                long j = cursor.getLong(5);
                long j2 = cursor.getLong(12);
                long j3 = cursor.getLong(15);
                long jRealizedProfitMilliSyp = Intrinsics.areEqual(cursor.getString(10), "CREDIT") ? realizedProfitMilliSyp(j3, j, j2) : j3;
                String string2 = cursor.getString(0);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                Intrinsics.checkNotNull(string);
                String strServiceTypeName = serviceTransferRepository.serviceTypeName(string);
                long j4 = cursor.getLong(2);
                long jRoundMilliToWhole = serviceTransferRepository.roundMilliToWhole(jRealizedProfitMilliSyp);
                long j5 = cursor.getLong(4);
                String string3 = cursor.getString(6);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                String string4 = cursor.getString(7);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                String string5 = cursor.getString(8);
                Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                String string6 = cursor.getString(9);
                Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                String string7 = cursor.getString(10);
                Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
                ArrayList arrayList2 = arrayList;
                arrayList2.add(new ServiceTransferRecord(string2, string, strServiceTypeName, j4, jRoundMilliToWhole, j5, j, string3, string4, string5, string6, string7, cursor.isNull(11) ? null : cursor.getString(11), j2, RangesKt.coerceAtLeast(j - j2, 0L), cursor.getLong(13), cursor.getLong(14), jRealizedProfitMilliSyp));
                serviceTransferRepository = this;
                arrayList = arrayList2;
                i = 1;
            }
            ArrayList arrayList3 = arrayList;
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            return arrayList3;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    public static /* synthetic */ void setOpeningBalance$default(ServiceTransferRepository serviceTransferRepository, String str, String str2, long j, String str3, int i, Object obj) throws Throwable {
        if ((i & 8) != 0) {
            str3 = null;
        }
        serviceTransferRepository.setOpeningBalance(str, str2, j, str3);
    }

    public final void setOpeningBalance(String actor, String serviceKey, long amount, String note) throws Throwable {
        Intrinsics.checkNotNullParameter(actor, "actor");
        Intrinsics.checkNotNullParameter(serviceKey, "serviceKey");
        if (amount < 0) {
            throw new IllegalArgumentException("\u0627\u0644\u0631\u0635\u064a\u062f \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0633\u0627\u0644\u0628\u0627\u064b".toString());
        }
        String strServiceAccountId = serviceAccountId(serviceKey);
        Context appContext = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        new DailyBalanceRepository(appContext).setWalletOpening(actor, strServiceAccountId, amount, note);
    }

    public final String rechargeAccountId() throws IOException {
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery("SELECT setting_value FROM app_settings WHERE setting_key='service_recharge_cash_account_id'", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            String string = cursor.moveToFirst() ? cursor.getString(0) : null;
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

    /* JADX WARN: Multi-variable type inference failed */
    public final void setRechargeAccount(String actor, String accountId) throws IOException {
        String str;
        Intrinsics.checkNotNullParameter(actor, "actor");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Context appContext = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        if (new SyncProtocolRepository(appContext, null, 2, 0 == true ? 1 : 0).isConfiguredSecondary()) {
            throw new IllegalArgumentException("\u062a\u063a\u064a\u064a\u0631 \u062d\u0633\u0627\u0628 \u0634\u062d\u0646 \u0627\u0644\u062e\u062f\u0645\u0627\u062a \u0645\u062a\u0627\u062d \u0645\u0646 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0641\u0642\u0637 \u062d\u062a\u0649 \u062a\u0643\u062a\u0645\u0644 \u0645\u0632\u0627\u0645\u0646\u062a\u0647".toString());
        }
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        requireManager(writableDatabase, actor);
        Context appContext2 = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext2, "appContext");
        new CashAccountRepository(appContext2).ensureSystemCashAccounts();
        writableDatabase.beginTransaction();
        try {
            Triple<String, String, Boolean> tripleReceiptAccount = receiptAccount(writableDatabase, accountId);
            if (!Intrinsics.areEqual(tripleReceiptAccount.getFirst(), "CASH_BOX") || !Intrinsics.areEqual(tripleReceiptAccount.getSecond(), "SYP") || !tripleReceiptAccount.getThird().booleanValue()) {
                throw new IllegalArgumentException("\u0627\u062e\u062a\u0631 \u0635\u0646\u062f\u0648\u0642\u0627\u064b \u0646\u0642\u062f\u064a\u0627\u064b \u0646\u0634\u0637\u0627\u064b \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629".toString());
            }
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT primary_cash_account_id FROM daily_cash_sessions WHERE operating_date=?", new String[]{today()});
            try {
                Cursor cursor = cursorRawQuery;
                String string = cursor.moveToFirst() ? cursor.getString(0) : null;
                CloseableKt.closeFinally(cursorRawQuery, null);
                if (Intrinsics.areEqual(string, accountId)) {
                    throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0627\u0633\u062a\u062e\u062f\u0627\u0645 \u062f\u0631\u062c \u0627\u0644\u064a\u0648\u0645 \u0627\u0644\u0631\u0626\u064a\u0633\u064a \u0644\u0634\u0631\u0627\u0621 \u0631\u0635\u064a\u062f \u0627\u0644\u062e\u062f\u0645\u0627\u062a".toString());
                }
                Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT account_id FROM daily_supplier_rollovers WHERE operating_date=?", new String[]{today()});
                try {
                    Cursor cursor2 = cursorRawQuery2;
                    String string2 = cursor2.moveToFirst() ? cursor2.getString(0) : null;
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    if (Intrinsics.areEqual(string2, accountId)) {
                        throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0627\u0633\u062a\u062e\u062f\u0627\u0645 \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0644\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0644\u0634\u0631\u0627\u0621 \u0631\u0635\u064a\u062f \u0627\u0644\u062e\u062f\u0645\u0627\u062a".toString());
                    }
                    Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT 1 FROM system_cash_account_roles WHERE role_key='SUPPLIER_ROLLOVER' AND account_id=?", new String[]{accountId});
                    try {
                        boolean zMoveToFirst = cursorRawQuery3.moveToFirst();
                        CloseableKt.closeFinally(cursorRawQuery3, null);
                        if (zMoveToFirst) {
                            throw new IllegalArgumentException("\u0647\u0630\u0627 \u0627\u0644\u0635\u0646\u062f\u0648\u0642 \u0645\u062e\u0635\u0635 \u0644\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0644\u0644\u0628\u0636\u0627\u0639\u0629".toString());
                        }
                        String strRechargeAccountId = rechargeAccountId();
                        String strStamp = stamp();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("setting_key", "service_recharge_cash_account_id");
                        contentValues.put("setting_value", accountId);
                        contentValues.put("updated_at", strStamp);
                        if (strRechargeAccountId == null) {
                            writableDatabase.insertOrThrow("app_settings", null, contentValues);
                        } else {
                            writableDatabase.update("app_settings", contentValues, "setting_key='service_recharge_cash_account_id'", null);
                        }
                        if (strRechargeAccountId != null) {
                            str = "{\"account_id\":\"" + strRechargeAccountId + "\"}";
                        } else {
                            str = null;
                        }
                        Intrinsics.checkNotNull(strStamp);
                        audit(writableDatabase, actor, "SET_SERVICE_RECHARGE_CASH_ACCOUNT", accountId, str, "{\"account_id\":\"" + accountId + "\"}", strStamp);
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("role_key", "SERVICE_RECHARGE");
                        contentValues2.put("account_id", accountId);
                        contentValues2.put("linked_at", strStamp);
                        writableDatabase.insertWithOnConflict("system_cash_account_roles", null, contentValues2, 5);
                        writableDatabase.setTransactionSuccessful();
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
                    try {
                        throw th3;
                    } catch (Throwable th4) {
                        CloseableKt.closeFinally(cursorRawQuery2, th3);
                        throw th4;
                    }
                }
            } catch (Throwable th5) {
                try {
                    throw th5;
                } catch (Throwable th6) {
                    CloseableKt.closeFinally(cursorRawQuery, th5);
                    throw th6;
                }
            }
        } catch (Throwable th7) {
            writableDatabase.endTransaction();
            throw th7;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final long recharge(String actor, String serviceKey, String sourceAccountId, long floatAmount, String operationId, String reference, String note) throws Throwable {
        Throwable th;
        Triple triple;
        Throwable th2;
        String str;
        Intrinsics.checkNotNullParameter(actor, "actor");
        Intrinsics.checkNotNullParameter(serviceKey, "serviceKey");
        Intrinsics.checkNotNullParameter(sourceAccountId, "sourceAccountId");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        Context appContext = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        if (new SyncProtocolRepository(appContext, null, 2, 0 == true ? 1 : 0).isConfiguredSecondary()) {
            throw new IllegalArgumentException("\u0634\u062d\u0646 \u0645\u062d\u0627\u0641\u0638 \u0627\u0644\u062e\u062f\u0645\u0627\u062a \u0645\u062a\u0627\u062d \u0645\u0646 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0641\u0642\u0637 \u062d\u062a\u0649 \u064a\u0643\u062a\u0645\u0644 \u0645\u0633\u0627\u0631 \u0645\u0632\u0627\u0645\u0646\u062a\u0647 \u0627\u0644\u0645\u0627\u0644\u064a\u0629".toString());
        }
        if (floatAmount <= 0 || StringsKt.isBlank(operationId)) {
            throw new IllegalArgumentException("\u0623\u062f\u062e\u0644 \u0645\u0628\u0644\u063a \u0634\u062d\u0646 \u0648\u0645\u0639\u0631\u0651\u0641 \u0639\u0645\u0644\u064a\u0629 \u0635\u062d\u064a\u062d\u064a\u0646".toString());
        }
        if (!Intrinsics.areEqual(serviceKey, SYRIATEL_SHARED) && !Intrinsics.areEqual(serviceKey, "SHAM_CASH")) {
            throw new IllegalArgumentException("\u0645\u062d\u0641\u0638\u0629 \u0627\u0644\u062e\u062f\u0645\u0629 \u063a\u064a\u0631 \u0645\u0639\u0631\u0648\u0641\u0629".toString());
        }
        long jPercent = percent(floatAmount, 2L);
        long jM = MainActivity$$ExternalSyntheticBackport0.m(floatAmount, jPercent);
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        writableDatabase.beginTransaction();
        try {
            try {
                requireManager(writableDatabase, actor);
                ensureSystemAccounts(writableDatabase);
                String strHash = hash(serviceKey + "|" + sourceAccountId + "|" + floatAmount + "|" + reference + "|" + note);
                Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT service_key,amount_minor,payload_hash FROM service_wallet_transactions WHERE operation_id=?", new String[]{operationId});
                try {
                    Cursor cursor = cursorRawQuery;
                    if (cursor.moveToFirst()) {
                        try {
                            triple = new Triple(cursor.getString(0), Long.valueOf(cursor.getLong(1)), cursor.getString(2));
                            th2 = null;
                        } catch (Throwable th3) {
                            th = th3;
                            try {
                                throw th;
                            } catch (Throwable th4) {
                                CloseableKt.closeFinally(cursorRawQuery, th);
                                throw th4;
                            }
                        }
                    } else {
                        th2 = null;
                        triple = null;
                    }
                    CloseableKt.closeFinally(cursorRawQuery, th2);
                    if (triple != null) {
                        if (!Intrinsics.areEqual(triple.getFirst(), serviceKey) || ((Number) triple.getSecond()).longValue() != floatAmount || !Intrinsics.areEqual(triple.getThird(), strHash)) {
                            throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0634\u062d\u0646 \u0645\u0633\u062a\u062e\u062f\u0645 \u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0645\u062e\u062a\u0644\u0641\u0629".toString());
                        }
                        writableDatabase.setTransactionSuccessful();
                        writableDatabase.endTransaction();
                        return jPercent;
                    }
                    Triple<String, String, Boolean> tripleReceiptAccount = receiptAccount(writableDatabase, sourceAccountId);
                    if (!Intrinsics.areEqual(tripleReceiptAccount.getFirst(), "CASH_BOX") || !Intrinsics.areEqual(tripleReceiptAccount.getSecond(), "SYP") || !tripleReceiptAccount.getThird().booleanValue() || !Intrinsics.areEqual(rechargeAccountId(), sourceAccountId)) {
                        throw new IllegalArgumentException("\u064a\u062c\u0628 \u0627\u062e\u062a\u064a\u0627\u0631 \u0635\u0646\u062f\u0648\u0642 \u0634\u0631\u0627\u0621 \u0631\u0635\u064a\u062f \u0627\u0644\u062e\u062f\u0645\u0627\u062a \u0627\u0644\u0645\u062e\u0635\u0635 \u0645\u0646 \u0634\u0627\u0634\u0629 \u0627\u0644\u0635\u0646\u0627\u062f\u064a\u0642 \u0648\u0627\u0644\u0645\u062d\u0627\u0641\u0638".toString());
                    }
                    if (cashBalance(writableDatabase, sourceAccountId) < jM) {
                        throw new IllegalArgumentException("\u0631\u0635\u064a\u062f \u0635\u0646\u062f\u0648\u0642 \u0634\u0631\u0627\u0621 \u0627\u0644\u0631\u0635\u064a\u062f \u0644\u0627 \u064a\u0643\u0641\u064a \u0644\u0642\u064a\u0645\u0629 \u0627\u0644\u0634\u062d\u0646 \u0648\u0627\u0644\u0639\u0645\u0648\u0644\u0629".toString());
                    }
                    String strStamp = stamp();
                    String str2 = operationId + ":SOURCE";
                    String strServiceName = serviceName(serviceKey);
                    if (jPercent > 0) {
                        str = " \u2022 \u0639\u0645\u0648\u0644\u0629 \u0634\u062d\u0646 2%: " + jPercent + " \u0644.\u0633";
                    } else {
                        str = "";
                    }
                    String str3 = "\u0634\u0631\u0627\u0621 \u0631\u0635\u064a\u062f " + strServiceName + str;
                    Intrinsics.checkNotNull(strStamp);
                    writableDatabase = writableDatabase;
                    try {
                        insertCashMovement$default(this, writableDatabase, sourceAccountId, "OUT", jM, "SERVICE_RECHARGE", "SERVICE_RECHARGE", operationId, str2, actor, str3, strStamp, null, 2048, null);
                        insertWalletMovement$default(this, writableDatabase, serviceKey, "IN", floatAmount, "RECHARGE", sourceAccountId, operationId, jPercent, note == null ? reference : note, actor, strStamp, strHash, null, 4096, null);
                        String strEsc = esc(sourceAccountId);
                        audit(writableDatabase, actor, "SERVICE_WALLET_RECHARGE", serviceKey, null, "{\"float_minor\":" + floatAmount + ",\"recharge_fee_minor\":" + jPercent + ",\"source_account_id\":\"" + strEsc + "\",\"reference\":\"" + esc(reference == null ? "" : reference) + "\"}", strStamp);
                        writableDatabase.setTransactionSuccessful();
                        writableDatabase.endTransaction();
                        return jPercent;
                    } catch (Throwable th5) {
                        th = th5;
                        writableDatabase.endTransaction();
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                }
            } catch (Throwable th7) {
                th = th7;
                writableDatabase.endTransaction();
                throw th;
            }
        } catch (Throwable th8) {
            th = th8;
            writableDatabase = writableDatabase;
        }
    }

    /* JADX WARN: Code duplicated, block: B:166:0x0319 A[Catch: all -> 0x01ec, TRY_LEAVE, TryCatch #1 {all -> 0x01ec, blocks: (B:86:0x01df, B:95:0x020a, B:98:0x021b, B:99:0x0226, B:100:0x0227, B:102:0x022d, B:106:0x0238, B:116:0x026b, B:119:0x0276, B:120:0x0281, B:123:0x0288, B:130:0x02b2, B:143:0x02ca, B:145:0x02d0, B:147:0x02d9, B:152:0x02e5, B:158:0x02f3, B:160:0x02f9, B:166:0x0319, B:175:0x0357, B:177:0x0363, B:184:0x0384, B:206:0x03ca, B:187:0x038e, B:188:0x0399, B:194:0x039f, B:195:0x03a2, B:196:0x03a3, B:199:0x03b0, B:200:0x03bb, B:137:0x02bb, B:138:0x02be, B:124:0x0294, B:126:0x029d, B:134:0x02b8, B:178:0x036f, B:180:0x0378, B:191:0x039c), top: B:466:0x01df, inners: #3, #5, #29, #30 }] */
    /* JADX WARN: Code duplicated, block: B:360:0x0948 A[Catch: all -> 0x08d0, TRY_ENTER, TryCatch #26 {all -> 0x08d0, blocks: (B:351:0x08ea, B:354:0x08f1, B:360:0x0948, B:362:0x097d), top: B:510:0x08ea }] */
    /* JADX WARN: Code duplicated, block: B:362:0x097d A[Catch: all -> 0x08d0, TRY_LEAVE, TryCatch #26 {all -> 0x08d0, blocks: (B:351:0x08ea, B:354:0x08f1, B:360:0x0948, B:362:0x097d), top: B:510:0x08ea }] */
    /* JADX WARN: Code duplicated, block: B:364:0x09e0  */
    /* JADX WARN: Code duplicated, block: B:365:0x0a02  */
    /* JADX WARN: Code duplicated, block: B:368:0x0a29  */
    /* JADX WARN: Code duplicated, block: B:373:0x0a36  */
    /* JADX WARN: Code duplicated, block: B:377:0x0a62 A[Catch: all -> 0x0806, TRY_LEAVE, TryCatch #20 {all -> 0x0806, blocks: (B:303:0x06ea, B:305:0x06f0, B:307:0x077a, B:309:0x077e, B:311:0x0786, B:312:0x0788, B:314:0x0790, B:315:0x0792, B:375:0x0a55, B:377:0x0a62, B:383:0x0b44, B:389:0x0b53, B:394:0x0b5e, B:411:0x0bce), top: B:500:0x06e8 }] */
    /* JADX WARN: Code duplicated, block: B:379:0x0ab9  */
    /* JADX WARN: Code duplicated, block: B:383:0x0b44 A[Catch: all -> 0x0806, TRY_ENTER, TRY_LEAVE, TryCatch #20 {all -> 0x0806, blocks: (B:303:0x06ea, B:305:0x06f0, B:307:0x077a, B:309:0x077e, B:311:0x0786, B:312:0x0788, B:314:0x0790, B:315:0x0792, B:375:0x0a55, B:377:0x0a62, B:383:0x0b44, B:389:0x0b53, B:394:0x0b5e, B:411:0x0bce), top: B:500:0x06e8 }] */
    /* JADX WARN: Code duplicated, block: B:385:0x0b47  */
    /* JADX WARN: Code duplicated, block: B:389:0x0b53 A[Catch: all -> 0x0806, TRY_ENTER, TRY_LEAVE, TryCatch #20 {all -> 0x0806, blocks: (B:303:0x06ea, B:305:0x06f0, B:307:0x077a, B:309:0x077e, B:311:0x0786, B:312:0x0788, B:314:0x0790, B:315:0x0792, B:375:0x0a55, B:377:0x0a62, B:383:0x0b44, B:389:0x0b53, B:394:0x0b5e, B:411:0x0bce), top: B:500:0x06e8 }] */
    /* JADX WARN: Code duplicated, block: B:391:0x0b56  */
    /* JADX WARN: Code duplicated, block: B:394:0x0b5e A[Catch: all -> 0x0806, TRY_ENTER, TRY_LEAVE, TryCatch #20 {all -> 0x0806, blocks: (B:303:0x06ea, B:305:0x06f0, B:307:0x077a, B:309:0x077e, B:311:0x0786, B:312:0x0788, B:314:0x0790, B:315:0x0792, B:375:0x0a55, B:377:0x0a62, B:383:0x0b44, B:389:0x0b53, B:394:0x0b5e, B:411:0x0bce), top: B:500:0x06e8 }] */
    /* JADX WARN: Code duplicated, block: B:396:0x0b61  */
    /* JADX WARN: Code duplicated, block: B:410:0x0bc3  */
    /* JADX WARN: Code duplicated, block: B:483:0x0abf A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:377:0x0a62, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: TypeSearchVarInfo not found in map for var: r22v1 ??
    	at jadx.core.dex.visitors.typeinference.TypeSearchState.getVarInfo(TypeSearchState.java:34)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.lambda$resolveIndependentVariables$1(TypeSearch.java:173)
    	at java.base/java.util.stream.MatchOps$1MatchSink.accept(MatchOps.java:90)
    	at java.base/java.util.ArrayList$ArrayListSpliterator.tryAdvance(ArrayList.java:1602)
    	at java.base/java.util.stream.ReferencePipeline$7$1.accept(ReferencePipeline.java:280)
    	at java.base/java.util.ArrayList$ArrayListSpliterator.tryAdvance(ArrayList.java:1602)
    	at java.base/java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
    	at java.base/java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
    	at java.base/java.util.stream.MatchOps$MatchOp.evaluateSequential(MatchOps.java:230)
    	at java.base/java.util.stream.MatchOps$MatchOp.evaluateSequential(MatchOps.java:196)
    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
    	at java.base/java.util.stream.ReferencePipeline.allMatch(ReferencePipeline.java:637)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.resolveIndependentVariables(TypeSearch.java:173)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:63)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.runMultiVariableSearch(FixTypesVisitor.java:119)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX WARN: Type inference failed for: r11v36, types: [com.hazem.dailystore.data.PermissionPolicy] */
    /* JADX WARN: Type inference failed for: r12v2, types: [com.hazem.dailystore.data.PermissionPolicy] */
    /* JADX WARN: Type inference failed for: r12v4, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r14v3, types: [java.lang.Object, net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r15v1, types: [com.hazem.dailystore.data.ServiceTransferRepository] */
    /* JADX WARN: Type inference failed for: r15v12 */
    /* JADX WARN: Type inference failed for: r15v2 */
    /* JADX WARN: Type inference failed for: r15v20 */
    /* JADX WARN: Type inference failed for: r15v22, types: [com.hazem.dailystore.data.ServiceTransferRepository] */
    /* JADX WARN: Type inference failed for: r15v23 */
    /* JADX WARN: Type inference failed for: r15v3 */
    /* JADX WARN: Type inference failed for: r15v31 */
    /* JADX WARN: Type inference failed for: r1v194, types: [com.hazem.dailystore.data.StoreTime] */
    /* JADX WARN: Type inference failed for: r1v90, types: [org.json.JSONObject] */
    /* JADX WARN: Type inference failed for: r22v5 */
    /* JADX WARN: Type inference failed for: r2v50, types: [com.hazem.dailystore.data.SyncProtocolRepository] */
    /* JADX WARN: Type inference failed for: r2v77, types: [com.hazem.dailystore.data.SyncProtocolRepository] */
    /* JADX WARN: Type inference failed for: r34v1 */
    /* JADX WARN: Type inference failed for: r34v10 */
    /* JADX WARN: Type inference failed for: r34v11 */
    /* JADX WARN: Type inference failed for: r34v12 */
    /* JADX WARN: Type inference failed for: r34v13, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r34v15, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r34v16 */
    /* JADX WARN: Type inference failed for: r34v17 */
    /* JADX WARN: Type inference failed for: r34v18 */
    /* JADX WARN: Type inference failed for: r34v19 */
    /* JADX WARN: Type inference failed for: r34v2 */
    /* JADX WARN: Type inference failed for: r34v20 */
    /* JADX WARN: Type inference failed for: r34v21 */
    /* JADX WARN: Type inference failed for: r34v3 */
    /* JADX WARN: Type inference failed for: r34v4 */
    /* JADX WARN: Type inference failed for: r34v5 */
    /* JADX WARN: Type inference failed for: r34v6 */
    /* JADX WARN: Type inference failed for: r34v7 */
    /* JADX WARN: Type inference failed for: r34v8 */
    /* JADX WARN: Type inference failed for: r34v9, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r3v32 */
    /* JADX WARN: Type inference failed for: r3v60, types: [long] */
    /* JADX WARN: Type inference failed for: r3v70, types: [org.json.JSONObject] */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v2, types: [long] */
    /* JADX WARN: Type inference failed for: r4v20 */
    /* JADX WARN: Type inference failed for: r4v22 */
    /* JADX WARN: Type inference failed for: r4v25 */
    /* JADX WARN: Type inference failed for: r4v26 */
    /* JADX WARN: Type inference failed for: r4v27 */
    /* JADX WARN: Type inference failed for: r4v28 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v61 */
    /* JADX WARN: Type inference failed for: r4v7, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r65v2 */
    /* JADX WARN: Type inference failed for: r65v3 */
    /* JADX WARN: Type inference failed for: r80v0, types: [com.hazem.dailystore.data.ServiceTransferRepository] */
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
    public final ServiceTransferResult executeTransfer(String actor, String serviceType, long deliveredMinor, long providerFeeMinor, String receiptAccountId, String operationId, String reference, String note, String occurredAt, String operatingDate, boolean syncOrigin, String paymentMode, String customerId, boolean legacyBalanceDelivered) throws Throwable {
        long j;
        String str;
        long jM$1;
        Throwable th;
        ?? r4;
        Throwable th2;
        boolean z;
        String string;
        String str2;
        String strServiceAccountId;
        JSONObject jSONObject;
        String str3;
        String str4;
        String str5;
        String str6;
        ?? r34;
        ?? r35;
        Object obj;
        ?? r36;
        String str7;
        Object obj2;
        long j2;
        String strHash;
        String str8;
        String str9;
        long j3;
        String str10;
        Throwable th3;
        String[] strArr;
        String str11;
        long j4;
        String str12;
        String str13;
        String str14;
        long j5;
        String str15;
        long j6;
        String str16;
        String str17;
        String strServiceTypeName;
        String str18;
        long j7;
        ?? r65;
        long j8;
        Object obj3;
        Object obj4;
        Object obj5;
        long j9;
        long j10;
        long j11;
        ?? r15;
        String str19;
        String strOperatingDate;
        String strOptString;
        String strStamp;
        Intrinsics.checkNotNullParameter(actor, "actor");
        Intrinsics.checkNotNullParameter(serviceType, "serviceType");
        Intrinsics.checkNotNullParameter(receiptAccountId, "receiptAccountId");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        Intrinsics.checkNotNullParameter(paymentMode, "paymentMode");
        if (deliveredMinor <= 0 || providerFeeMinor < 0 || StringsKt.isBlank(operationId)) {
            throw new IllegalArgumentException("\u062a\u062d\u0642\u0642 \u0645\u0646 \u0645\u0628\u0644\u063a \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u0648\u0631\u0633\u0648\u0645 \u0627\u0644\u0634\u0631\u0643\u0629".toString());
        }
        if (!SetsKt.setOf((Object[]) new String[]{"CASH", "CREDIT"}).contains(paymentMode)) {
            throw new IllegalArgumentException("\u0637\u0631\u064a\u0642\u0629 \u062f\u0641\u0639 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
        }
        if (Intrinsics.areEqual(paymentMode, "CREDIT")) {
            String str20 = customerId;
            if (str20 == null || StringsKt.isBlank(str20)) {
                throw new IllegalArgumentException("\u0627\u062e\u062a\u0631 \u0627\u0644\u0632\u0628\u0648\u0646 \u0644\u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0622\u062c\u0644".toString());
            }
        } else {
            String str21 = customerId;
            if (str21 != null && !StringsKt.isBlank(str21)) {
                throw new IllegalArgumentException("\u0644\u0627 \u062a\u0631\u0628\u0637 \u0632\u0628\u0648\u0646\u0627\u064b \u0628\u0639\u0645\u0644\u064a\u0629 \u062a\u062d\u0648\u064a\u0644 \u0646\u0642\u062f\u064a\u0629".toString());
            }
        }
        if (!CollectionsKt.listOf((Object[]) new String[]{SYRIATEL_BALANCE, SYRIATEL_CASH, "SHAM_CASH"}).contains(serviceType)) {
            throw new IllegalArgumentException("\u0646\u0648\u0639 \u062e\u062f\u0645\u0629 \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u063a\u064a\u0631 \u0645\u0639\u0631\u0648\u0641".toString());
        }
        if (Intrinsics.areEqual(serviceType, "SHAM_CASH") && providerFeeMinor != 0) {
            throw new IllegalArgumentException("\u0644\u0627 \u062a\u0648\u062c\u062f \u0631\u0633\u0648\u0645 \u0634\u0631\u0643\u0629 \u0639\u0644\u0649 \u062a\u062d\u0648\u064a\u0644 \u0634\u0627\u0645 \u0643\u0627\u0634 \u0648\u0641\u0642 \u0627\u0644\u0633\u064a\u0627\u0633\u0629 \u0627\u0644\u0645\u0639\u062a\u0645\u062f\u0629".toString());
        }
        if (Intrinsics.areEqual(serviceType, SYRIATEL_CASH) && providerFeeMinor != INSTANCE.expectedProviderFeeMinor(serviceType, deliveredMinor)) {
            throw new IllegalArgumentException("\u0631\u0633\u0645 \u0634\u0631\u0643\u0629 \u0633\u064a\u0631\u064a\u062a\u0644 \u0643\u0627\u0634 \u064a\u064f\u062d\u062a\u0633\u0628 \u062a\u0644\u0642\u0627\u0626\u064a\u0627\u064b \u0648\u0641\u0642 \u0627\u0644\u0633\u064a\u0627\u0633\u0629 \u0627\u0644\u0645\u0639\u062a\u0645\u062f\u0629".toString());
        }
        String str22 = Intrinsics.areEqual(serviceType, "SHAM_CASH") ? "SHAM_CASH" : SYRIATEL_SHARED;
        if (Intrinsics.areEqual(serviceType, SYRIATEL_BALANCE)) {
            j = 20;
        } else {
            j = Intrinsics.areEqual(serviceType, SYRIATEL_CASH) ? 10L : 5L;
        }
        long jM = (!Intrinsics.areEqual(serviceType, SYRIATEL_BALANCE) && Intrinsics.areEqual(serviceType, SYRIATEL_CASH)) ? MainActivity$$ExternalSyntheticBackport0.m(deliveredMinor, providerFeeMinor) : deliveredMinor;
        if (!Intrinsics.areEqual(serviceType, SYRIATEL_BALANCE) || legacyBalanceDelivered) {
            str = "CASH";
            jM$1 = DailyBalanceRepository$$ExternalSyntheticBackport0.m$1(deliveredMinor, 1000L);
        } else {
            str = "CASH";
            jM$1 = roundRatioMilli(jM, 41340L, 43L);
        }
        long j12 = jM$1;
        long jRoundRatioMilli = roundRatioMilli(j12, j, 100L);
        ?? RoundMilliToWhole = roundMilliToWhole(j12);
        long jRoundMilliToWhole = roundMilliToWhole(jRoundRatioMilli);
        ?? r16 = this;
        long jRoundMilliToWhole2 = r16.roundMilliToWhole(MainActivity$$ExternalSyntheticBackport0.m(MainActivity$$ExternalSyntheticBackport0.m(j12, jRoundRatioMilli), Intrinsics.areEqual(serviceType, SYRIATEL_CASH) ? 0L : DailyBalanceRepository$$ExternalSyntheticBackport0.m$1(providerFeeMinor, 1000L)));
        RoundMilliToWhole = r16.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(RoundMilliToWhole, "<get-writableDatabase>(...)");
        RoundMilliToWhole.beginTransaction();
        try {
            try {
                Cursor cursorRawQuery = RoundMilliToWhole.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", null);
                try {
                    Cursor cursor = cursorRawQuery;
                    if (cursor.moveToFirst()) {
                        z = false;
                        try {
                            string = cursor.getString(0);
                        } catch (Throwable th4) {
                            th2 = th4;
                            try {
                                throw th2;
                            } catch (Throwable th5) {
                                CloseableKt.closeFinally(cursorRawQuery, th2);
                                throw th5;
                            }
                        }
                    } else {
                        z = false;
                        string = null;
                    }
                    CloseableKt.closeFinally(cursorRawQuery, null);
                    String str23 = "SECONDARY";
                    if (Intrinsics.areEqual(string, str23)) {
                        try {
                            PermissionPolicy.INSTANCE.requireAssigned(RoundMilliToWhole, actor, PermissionPolicy.SERVICE_TRANSFERS_EXECUTE);
                        } catch (Throwable th6) {
                            th = th6;
                            r4 = RoundMilliToWhole;
                        }
                    } else {
                        PermissionPolicy.INSTANCE.require(RoundMilliToWhole, actor, PermissionPolicy.SERVICE_TRANSFERS_EXECUTE);
                    }
                    if (Intrinsics.areEqual(paymentMode, "CREDIT")) {
                        if (string != null && !SetsKt.setOf((Object[]) new String[]{"PRIMARY", str23}).contains(string)) {
                            throw new IllegalArgumentException("\u062f\u0648\u0631 \u0627\u0644\u062c\u0647\u0627\u0632 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d \u0644\u0625\u0646\u0634\u0627\u0621 \u0630\u0645\u0645 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f".toString());
                        }
                        if (!Intrinsics.areEqual(string, str23)) {
                            r16.ensureSystemAccounts(RoundMilliToWhole);
                        }
                    }
                    if (Intrinsics.areEqual(paymentMode, "CREDIT")) {
                        str2 = str22;
                        strServiceAccountId = r16.serviceAccountId(RoundMilliToWhole, str2);
                    } else {
                        str2 = str22;
                        strServiceAccountId = receiptAccountId;
                    }
                    Triple<String, String, Boolean> tripleReceiptAccount = r16.receiptAccount(RoundMilliToWhole, strServiceAccountId);
                    String str24 = str2;
                    if (!Intrinsics.areEqual(tripleReceiptAccount.getSecond(), "SYP") || !tripleReceiptAccount.getThird().booleanValue()) {
                        throw new IllegalArgumentException("\u0627\u062e\u062a\u0631 \u062d\u0633\u0627\u0628 \u0642\u0628\u0636 \u0646\u0634\u0637\u0627\u064b \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629".toString());
                    }
                    if (Intrinsics.areEqual(paymentMode, "CREDIT") && !Intrinsics.areEqual(tripleReceiptAccount.getFirst(), "WALLET")) {
                        throw new IllegalArgumentException("\u062d\u0633\u0627\u0628 \u062d\u0641\u0638 \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0622\u062c\u0644 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                    }
                    if (Intrinsics.areEqual(string, str23)) {
                        Cursor cursorRawQuery2 = RoundMilliToWhole.rawQuery("SELECT payload_json FROM sync_operation_envelopes WHERE operation_id=?", new String[]{operationId});
                        try {
                            Cursor cursor2 = cursorRawQuery2;
                            jSONObject = cursor2.moveToFirst() ? new JSONObject(cursor2.getString(0)) : null;
                            CloseableKt.closeFinally(cursorRawQuery2, null);
                        } catch (Throwable th7) {
                            try {
                                throw th7;
                            } catch (Throwable th8) {
                                CloseableKt.closeFinally(cursorRawQuery2, th7);
                                throw th8;
                            }
                        }
                    } else {
                        str23 = str23;
                        string = string;
                        jSONObject = null;
                    }
                    if (occurredAt == null) {
                        if (jSONObject == null || (strStamp = jSONObject.optString("occurred_at")) == null || StringsKt.isBlank(strStamp) || Intrinsics.areEqual(strStamp, "null")) {
                            strStamp = null;
                        }
                        if (strStamp == null) {
                            strStamp = stamp();
                        }
                        str3 = strStamp;
                    } else {
                        str3 = occurredAt;
                    }
                    if (operatingDate == null) {
                        if (jSONObject == null || (strOptString = jSONObject.optString("operating_date")) == null) {
                            str4 = "operating_date";
                            str5 = "occurred_at";
                        } else {
                            str4 = "operating_date";
                            str5 = "occurred_at";
                            strOperatingDate = new Regex("\\d{4}-\\d{2}-\\d{2}").matches(strOptString) ? strOptString : null;
                            if (strOperatingDate == null) {
                                ?? r1 = StoreTime.INSTANCE;
                                Intrinsics.checkNotNull(str3);
                                strOperatingDate = r1.operatingDate(RoundMilliToWhole, str3);
                            }
                            str6 = strOperatingDate;
                        }
                        if (strOperatingDate == null) {
                            ?? r2 = StoreTime.INSTANCE;
                            Intrinsics.checkNotNull(str3);
                            strOperatingDate = r2.operatingDate(RoundMilliToWhole, str3);
                        }
                        str6 = strOperatingDate;
                    } else {
                        str4 = "operating_date";
                        str5 = "occurred_at";
                        str6 = operatingDate;
                    }
                    Intrinsics.checkNotNull(str3);
                    String str25 = str3;
                    if (new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(str3)) {
                        if (new Regex("\\d{4}-\\d{2}-\\d{2}").matches(str6)) {
                            String str26 = str;
                            if (!Intrinsics.areEqual(paymentMode, str26)) {
                                str6 = str6;
                            } else if (Intrinsics.areEqual(tripleReceiptAccount.getFirst(), "CASH_BOX")) {
                                Cursor cursorRawQuery3 = RoundMilliToWhole.rawQuery("SELECT primary_cash_account_id FROM daily_cash_sessions WHERE operating_date=?", new String[]{str6});
                                try {
                                    Cursor cursor3 = cursorRawQuery3;
                                    String string2 = cursor3.moveToFirst() ? cursor3.getString(0) : null;
                                    CloseableKt.closeFinally(cursorRawQuery3, null);
                                    if (!Intrinsics.areEqual(string2, strServiceAccountId)) {
                                        throw new IllegalArgumentException("\u0627\u0644\u0642\u0628\u0636 \u0627\u0644\u0646\u0642\u062f\u064a \u064a\u062c\u0628 \u0623\u0646 \u064a\u0630\u0647\u0628 \u0625\u0644\u0649 \u062f\u0631\u062c \u0627\u0644\u064a\u0648\u0645 \u0627\u0644\u0631\u0626\u064a\u0633\u064a".toString());
                                    }
                                } catch (Throwable th9) {
                                    try {
                                        throw th9;
                                    } catch (Throwable th10) {
                                        CloseableKt.closeFinally(cursorRawQuery3, th9);
                                        throw th10;
                                    }
                                }
                            } else {
                                str6 = str6;
                                if (!Intrinsics.areEqual(tripleReceiptAccount.getFirst(), "WALLET")) {
                                    throw new IllegalArgumentException("\u062d\u0633\u0627\u0628 \u0627\u0644\u0627\u0633\u062a\u0644\u0627\u0645 \u064a\u062c\u0628 \u0623\u0646 \u064a\u0643\u0648\u0646 \u062f\u0631\u062c\u0627\u064b \u0646\u0642\u062f\u064a\u0627\u064b \u0623\u0648 \u0645\u062d\u0641\u0638\u0629 \u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a\u0629".toString());
                                }
                            }
                            try {
                                if (!legacyBalanceDelivered) {
                                    obj = "CREDIT";
                                    r36 = RoundMilliToWhole;
                                    str7 = "";
                                    obj2 = "WALLET";
                                    String str27 = reference;
                                    try {
                                        if (Intrinsics.areEqual(paymentMode, str26)) {
                                            StringBuilder sb = new StringBuilder();
                                            sb.append(serviceType);
                                            sb.append("|");
                                            long j13 = jM;
                                            sb.append(j13);
                                            sb.append("|");
                                            sb.append(j12);
                                            sb.append("|");
                                            j2 = j12;
                                            j3 = jRoundRatioMilli;
                                            sb.append(j3);
                                            sb.append("|");
                                            sb.append(providerFeeMinor);
                                            sb.append("|");
                                            sb.append(strServiceAccountId);
                                            sb.append("|");
                                            sb.append(str27);
                                            sb.append("|");
                                            sb.append(note);
                                            strHash = r16.hash(sb.toString());
                                            str8 = customerId;
                                            jM = j13;
                                            str9 = paymentMode;
                                            str10 = str27;
                                            r36 = r36;
                                        } else {
                                            str8 = customerId;
                                            long j14 = jM;
                                            j3 = jRoundRatioMilli;
                                            String str28 = str8 == null ? str7 : str8;
                                            StringBuilder sb2 = new StringBuilder();
                                            sb2.append(serviceType);
                                            sb2.append("|");
                                            sb2.append(j14);
                                            sb2.append("|");
                                            jM = j14;
                                            sb2.append(j12);
                                            sb2.append("|");
                                            sb2.append(j3);
                                            sb2.append("|");
                                            sb2.append(providerFeeMinor);
                                            sb2.append("|");
                                            sb2.append(strServiceAccountId);
                                            sb2.append("|");
                                            j2 = j12;
                                            str9 = paymentMode;
                                            sb2.append(str9);
                                            sb2.append("|");
                                            sb2.append(str28);
                                            sb2.append("|");
                                            sb2.append(str27);
                                            sb2.append("|");
                                            sb2.append(note);
                                            strHash = r16.hash(sb2.toString());
                                            str10 = str27;
                                            r36 = r36;
                                        }
                                    } catch (Throwable th11) {
                                        th = th11;
                                        RoundMilliToWhole = r36;
                                        th = th;
                                        r4 = RoundMilliToWhole;
                                        r4.endTransaction();
                                        throw th;
                                    }
                                } else {
                                    try {
                                        if (Intrinsics.areEqual(paymentMode, str26)) {
                                            StringBuilder sb3 = new StringBuilder();
                                            sb3.append(serviceType);
                                            sb3.append("|");
                                            obj = "CREDIT";
                                            sb3.append(deliveredMinor);
                                            sb3.append("|");
                                            sb3.append(providerFeeMinor);
                                            sb3.append("|");
                                            sb3.append(strServiceAccountId);
                                            sb3.append("|");
                                            str7 = "";
                                            String str29 = reference;
                                            sb3.append(str29);
                                            sb3.append("|");
                                            obj2 = "WALLET";
                                            sb3.append(note);
                                            str9 = paymentMode;
                                            j2 = j12;
                                            r36 = RoundMilliToWhole;
                                            j3 = jRoundRatioMilli;
                                            str8 = customerId;
                                            strHash = r16.hash(sb3.toString());
                                            str10 = str29;
                                        } else {
                                            obj = "CREDIT";
                                            r36 = RoundMilliToWhole;
                                            String str30 = customerId;
                                            str7 = "";
                                            obj2 = "WALLET";
                                            String str31 = reference;
                                            j2 = j12;
                                            if (str30 == null) {
                                                str30 = str7;
                                            }
                                            strHash = r16.hash(serviceType + "|" + deliveredMinor + "|" + providerFeeMinor + "|" + strServiceAccountId + "|" + paymentMode + "|" + str30 + "|" + str31 + "|" + note);
                                            str8 = customerId;
                                            str9 = paymentMode;
                                            j3 = jRoundRatioMilli;
                                            str10 = str31;
                                        }
                                    } catch (Throwable th12) {
                                        th = th12;
                                        r34 = RoundMilliToWhole;
                                    }
                                }
                                RoundMilliToWhole = r36;
                                try {
                                    Cursor cursorRawQuery4 = RoundMilliToWhole.rawQuery("SELECT id,service_type,amount_delivered_minor,profit_minor,provider_fee_minor,customer_charge_minor,payload_hash,payment_mode,customer_id,amount_debited_minor,amount_delivered_milli_syp,profit_milli_syp FROM service_transfers WHERE operation_id=?", new String[]{operationId});
                                    try {
                                        Cursor cursor4 = cursorRawQuery4;
                                        if (cursor4.moveToFirst()) {
                                            try {
                                                strArr = new String[]{cursor4.getString(0), cursor4.getString(1), String.valueOf(cursor4.getLong(2)), String.valueOf(cursor4.getLong(3)), String.valueOf(cursor4.getLong(4)), String.valueOf(cursor4.getLong(5)), cursor4.getString(6), cursor4.getString(7), cursor4.isNull(8) ? str7 : cursor4.getString(8), String.valueOf(cursor4.getLong(9)), String.valueOf(cursor4.getLong(10)), String.valueOf(cursor4.getLong(11))};
                                            } catch (Throwable th13) {
                                                th3 = th13;
                                                try {
                                                    throw th3;
                                                } catch (Throwable th14) {
                                                    CloseableKt.closeFinally(cursorRawQuery4, th3);
                                                    throw th14;
                                                }
                                            }
                                        } else {
                                            strArr = null;
                                        }
                                        try {
                                            CloseableKt.closeFinally(cursorRawQuery4, null);
                                            try {
                                                if (strArr != null) {
                                                    if (Intrinsics.areEqual(strArr[1], serviceType) && Intrinsics.areEqual(strArr[2], String.valueOf((long) RoundMilliToWhole)) && Intrinsics.areEqual(strArr[3], String.valueOf(jRoundMilliToWhole)) && Intrinsics.areEqual(strArr[4], String.valueOf(providerFeeMinor)) && Intrinsics.areEqual(strArr[5], String.valueOf(jRoundMilliToWhole2)) && Intrinsics.areEqual(strArr[6], strHash) && Intrinsics.areEqual(strArr[7], str9)) {
                                                        String str32 = strArr[8];
                                                        if (str8 == null) {
                                                            str8 = str7;
                                                        }
                                                        if (Intrinsics.areEqual(str32, str8) && Intrinsics.areEqual(strArr[9], String.valueOf(jM)) && Intrinsics.areEqual(strArr[10], String.valueOf(j2)) && Intrinsics.areEqual(strArr[11], String.valueOf(j3))) {
                                                            RoundMilliToWhole.setTransactionSuccessful();
                                                            String str33 = strArr[0];
                                                            Intrinsics.checkNotNull(str33);
                                                            ServiceTransferResult serviceTransferResult = new ServiceTransferResult(str33, RoundMilliToWhole, jRoundMilliToWhole, providerFeeMinor, jRoundMilliToWhole2, true, jM, j2, j3);
                                                            RoundMilliToWhole.endTransaction();
                                                            return serviceTransferResult;
                                                        }
                                                    }
                                                    throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u0645\u0633\u062a\u062e\u062f\u0645 \u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0645\u062e\u062a\u0644\u0641\u0629".toString());
                                                }
                                                Object obj6 = obj;
                                                if (Intrinsics.areEqual(str9, obj6)) {
                                                    Cursor cursorRawQuery5 = RoundMilliToWhole.rawQuery("SELECT 1 FROM customers WHERE id=? AND is_active=1", new String[]{customerId});
                                                    try {
                                                        boolean zMoveToFirst = cursorRawQuery5.moveToFirst();
                                                        CloseableKt.closeFinally(cursorRawQuery5, null);
                                                        if (!zMoveToFirst) {
                                                            throw new IllegalArgumentException("\u0627\u0644\u0632\u0628\u0648\u0646 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0623\u0648 \u0645\u0648\u0642\u0648\u0641".toString());
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
                                                String str34 = string;
                                                r34 = RoundMilliToWhole;
                                                try {
                                                    if (Intrinsics.areEqual(str34, str23)) {
                                                        try {
                                                            if (!Intrinsics.areEqual(str9, obj6)) {
                                                                throw new IllegalArgumentException("\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u064a\u0633\u062a\u0637\u064a\u0639 \u062a\u0633\u062c\u064a\u0644 \u062a\u062d\u0648\u064a\u0644 \u0631\u0635\u064a\u062f \u0622\u062c\u0644 \u0641\u0642\u0637\u061b \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0646\u0642\u062f\u064a \u0648\u0625\u062f\u0627\u0631\u0629 \u0627\u0644\u0645\u062d\u0627\u0641\u0638 \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a".toString());
                                                            }
                                                            String str35 = "pending-service-transfer-" + operationId;
                                                            long j15 = jM;
                                                            JSONObject jSONObjectPut = new JSONObject().put("business_operation", "EXECUTE_SERVICE_TRANSFER_CREDIT").put("service_type", serviceType).put("service_key", str24).put("schema_version", 2).put("amount_debited_minor", j15).put("delivered_minor", RoundMilliToWhole);
                                                            long j16 = j2;
                                                            JSONObject jSONObjectPut2 = jSONObjectPut.put("delivered_milli_syp", j16).put("profit_minor", jRoundMilliToWhole).put("profit_milli_syp", j3).put("provider_fee_minor", providerFeeMinor).put("customer_charge_minor", jRoundMilliToWhole2).put("cash_account_id", strServiceAccountId).put("cash_account_type", tripleReceiptAccount.getFirst()).put("cash_account_currency_code", tripleReceiptAccount.getSecond()).put("payment_mode", str9).put("customer_id", str8 == null ? JSONObject.NULL : str8);
                                                            Object obj7 = str10;
                                                            if (str10 == null) {
                                                                obj7 = JSONObject.NULL;
                                                            }
                                                            JSONObject jSONObjectPut3 = jSONObjectPut2.put("reference", obj7);
                                                            Object obj8 = note;
                                                            if (obj8 == null) {
                                                                obj8 = JSONObject.NULL;
                                                            }
                                                            JSONObject jSONObjectPut4 = jSONObjectPut3.put("note", obj8).put(str5, str25).put(str4, str6);
                                                            Context appContext = this.appContext;
                                                            Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
                                                            ?? syncProtocolRepository = new SyncProtocolRepository(appContext, null, 2, false ? 1 : 0);
                                                            String string3 = jSONObjectPut4.toString();
                                                            Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
                                                            syncProtocolRepository.enqueueSecondaryCommand(r34, actor, operationId, "EXECUTE_SERVICE_TRANSFER_CREDIT", "SERVICE_TRANSFER", str35, string3);
                                                            r34.setTransactionSuccessful();
                                                            ServiceTransferResult serviceTransferResult2 = new ServiceTransferResult(str35, RoundMilliToWhole, jRoundMilliToWhole, providerFeeMinor, jRoundMilliToWhole2, false, j15, j16, j3);
                                                            r34.endTransaction();
                                                            return serviceTransferResult2;
                                                        } catch (Throwable th17) {
                                                            th = th17;
                                                        }
                                                    } else {
                                                        String str36 = str4;
                                                        String str37 = str6;
                                                        String str38 = str5;
                                                        if (str34 != null) {
                                                            try {
                                                                if (!Intrinsics.areEqual(str34, "PRIMARY")) {
                                                                    throw new IllegalArgumentException("\u062f\u0648\u0631 \u0627\u0644\u062c\u0647\u0627\u0632 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d \u0644\u062a\u0646\u0641\u064a\u0630 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f".toString());
                                                                }
                                                            } catch (Throwable th18) {
                                                                th = th18;
                                                                r35 = r34;
                                                                r4 = r35;
                                                            }
                                                        }
                                                        try {
                                                            ensureSystemAccounts(r34);
                                                            if (walletBalance(r34, str24) < jM) {
                                                                r36 = r34;
                                                                try {
                                                                    throw new IllegalArgumentException(("\u0631\u0635\u064a\u062f " + serviceName(str24) + " \u063a\u064a\u0631 \u0643\u0627\u0641\u064d. \u0627\u0644\u0645\u062a\u0627\u062d: " + walletBalance(r36, str24) + " \u0644.\u0633").toString());
                                                                } catch (Throwable th19) {
                                                                    th = th19;
                                                                    RoundMilliToWhole = r36;
                                                                    th = th;
                                                                    r4 = RoundMilliToWhole;
                                                                    r4.endTransaction();
                                                                    throw th;
                                                                }
                                                            }
                                                            try {
                                                                String string4 = UUID.randomUUID().toString();
                                                                Intrinsics.checkNotNullExpressionValue(string4, "toString(...)");
                                                                ContentValues contentValues = new ContentValues();
                                                                contentValues.put("id", string4);
                                                                contentValues.put("operation_id", operationId);
                                                                contentValues.put("service_type", serviceType);
                                                                contentValues.put("service_key", str24);
                                                                contentValues.put("amount_delivered_minor", Long.valueOf((long) RoundMilliToWhole));
                                                                contentValues.put("profit_minor", Long.valueOf(jRoundMilliToWhole));
                                                                contentValues.put("provider_fee_minor", Long.valueOf(providerFeeMinor));
                                                                contentValues.put("amount_debited_minor", Long.valueOf(jM));
                                                                contentValues.put("amount_delivered_milli_syp", Long.valueOf(j2));
                                                                contentValues.put("profit_milli_syp", Long.valueOf(j3));
                                                                contentValues.put("customer_charge_minor", Long.valueOf(jRoundMilliToWhole2));
                                                                contentValues.put("receipt_account_id", strServiceAccountId);
                                                                String str39 = strServiceAccountId;
                                                                contentValues.put("receipt_channel", Intrinsics.areEqual(tripleReceiptAccount.getFirst(), obj2) ? "ELECTRONIC" : str26);
                                                                contentValues.put("payment_mode", paymentMode);
                                                                String str40 = customerId;
                                                                if (str40 != null) {
                                                                    try {
                                                                        if (StringsKt.isBlank(str40)) {
                                                                            str11 = "customer_id";
                                                                            contentValues.putNull(str11);
                                                                        } else {
                                                                            str11 = "customer_id";
                                                                            contentValues.put(str11, customerId);
                                                                        }
                                                                        j4 = j3;
                                                                        contentValues.put("reference", reference);
                                                                        contentValues.put("note", note);
                                                                        contentValues.put("payload_hash", strHash);
                                                                        contentValues.put("created_by", actor);
                                                                        str12 = str25;
                                                                        contentValues.put("created_at", str12);
                                                                        contentValues.put(str36, str37);
                                                                        contentValues.put(NotificationCompat.CATEGORY_STATUS, "POSTED");
                                                                        r34.insertOrThrow("service_transfers", null, contentValues);
                                                                        if (Intrinsics.areEqual(paymentMode, obj6)) {
                                                                            ContentValues contentValues2 = new ContentValues();
                                                                            contentValues2.put("transfer_id", string4);
                                                                            contentValues2.put(str11, customerId);
                                                                            str19 = str11;
                                                                            contentValues2.put("original_amount_minor", Long.valueOf(jRoundMilliToWhole2));
                                                                            contentValues2.put(NotificationCompat.CATEGORY_STATUS, "OPEN");
                                                                            contentValues2.put("note", note);
                                                                            contentValues2.put("created_at", str12);
                                                                            Unit unit = Unit.INSTANCE;
                                                                            r34.insertOrThrow("service_transfer_receivables", null, contentValues2);
                                                                            if (syncOrigin) {
                                                                                j5 = jRoundMilliToWhole2;
                                                                                str15 = "note";
                                                                                str13 = str19;
                                                                                j6 = j4;
                                                                                str17 = str37;
                                                                                str14 = str36;
                                                                                str16 = string4;
                                                                            } else {
                                                                                Context appContext2 = this.appContext;
                                                                                Intrinsics.checkNotNullExpressionValue(appContext2, "appContext");
                                                                                str14 = str36;
                                                                                j6 = j4;
                                                                                str17 = str37;
                                                                                j5 = jRoundMilliToWhole2;
                                                                                str16 = string4;
                                                                                str15 = "note";
                                                                                str13 = str19;
                                                                                new ServiceTransferReceivableRepository(appContext2).enqueueMirrorSnapshot(r34, actor, string4, operationId, (PointerIconCompat.TYPE_TEXT & 16) != 0 ? null : null, (PointerIconCompat.TYPE_TEXT & 32) != 0 ? null : null, (PointerIconCompat.TYPE_TEXT & 64) != 0 ? null : null, (PointerIconCompat.TYPE_TEXT & 128) != 0 ? null : null, (PointerIconCompat.TYPE_TEXT & 256) != 0 ? null : null, (PointerIconCompat.TYPE_TEXT & 512) != 0 ? null : null);
                                                                            }
                                                                        } else {
                                                                            str13 = str11;
                                                                            str14 = str36;
                                                                            j5 = jRoundMilliToWhole2;
                                                                            str15 = "note";
                                                                            j6 = j4;
                                                                            str16 = string4;
                                                                            str17 = str37;
                                                                        }
                                                                        if (note == null) {
                                                                            try {
                                                                                strServiceTypeName = serviceTypeName(serviceType);
                                                                            } catch (Throwable th20) {
                                                                                th = th20;
                                                                                r4 = r34;
                                                                                r4.endTransaction();
                                                                                throw th;
                                                                            }
                                                                        } else {
                                                                            strServiceTypeName = note;
                                                                        }
                                                                        r34 = r34;
                                                                        insertWalletMovement(r34, str24, "OUT", jM, "TRANSFER_OUT", str16, operationId, 0L, strServiceTypeName, actor, str12, null, str17);
                                                                        if (Intrinsics.areEqual(paymentMode, str26)) {
                                                                            j7 = jRoundMilliToWhole;
                                                                            str18 = serviceType;
                                                                            insertCashMovement(r34, str39, "IN", j5, "SERVICE_TRANSFER_RECEIPT", "SERVICE_TRANSFER", str16, operationId + ":RECEIPT", actor, "\u0645\u0642\u0628\u0648\u0636 \u062a\u062d\u0648\u064a\u0644 \u0631\u0635\u064a\u062f \u2022 " + serviceTypeName(serviceType) + " \u2022 \u0631\u0628\u062d " + jRoundMilliToWhole + " \u0644.\u0633", str12, str17);
                                                                        } else {
                                                                            str18 = serviceType;
                                                                            j7 = r16;
                                                                        }
                                                                        if (syncOrigin) {
                                                                            j8 = providerFeeMinor;
                                                                            j11 = j2;
                                                                            j9 = j6;
                                                                            j10 = j7;
                                                                            r15 = this;
                                                                            r65 = r22;
                                                                        } else {
                                                                            try {
                                                                                long j17 = jM;
                                                                                long j18 = j2;
                                                                                long j19 = j7;
                                                                                JSONObject jSONObjectPut5 = new JSONObject().put("business_operation", "EXECUTE_SERVICE_TRANSFER").put("service_type", str18).put("service_key", str24).put("schema_version", 2).put("amount_debited_minor", j17).put("delivered_minor", RoundMilliToWhole).put("delivered_milli_syp", j18).put("profit_minor", j19);
                                                                                long j20 = j6;
                                                                                r65 = RoundMilliToWhole;
                                                                                jM = j17;
                                                                                j8 = providerFeeMinor;
                                                                                long j21 = j5;
                                                                                JSONObject jSONObjectPut6 = jSONObjectPut5.put("profit_milli_syp", j20).put("provider_fee_minor", j8).put("customer_charge_minor", j21).put("cash_account_id", str39).put("cash_account_type", tripleReceiptAccount.getFirst()).put("cash_account_currency_code", tripleReceiptAccount.getSecond()).put("payment_mode", paymentMode);
                                                                                if (customerId == null) {
                                                                                    obj3 = JSONObject.NULL;
                                                                                } else {
                                                                                    obj3 = customerId;
                                                                                }
                                                                                JSONObject jSONObjectPut7 = jSONObjectPut6.put(str13, obj3);
                                                                                if (reference == null) {
                                                                                    obj4 = JSONObject.NULL;
                                                                                } else {
                                                                                    obj4 = reference;
                                                                                }
                                                                                JSONObject jSONObjectPut8 = jSONObjectPut7.put("reference", obj4);
                                                                                if (note == null) {
                                                                                    obj5 = JSONObject.NULL;
                                                                                } else {
                                                                                    obj5 = note;
                                                                                }
                                                                                JSONObject jSONObjectPut9 = jSONObjectPut8.put(str15, obj5).put(str38, str12).put(str14, str17);
                                                                                try {
                                                                                    Context appContext3 = this.appContext;
                                                                                    Intrinsics.checkNotNullExpressionValue(appContext3, "appContext");
                                                                                    ?? syncProtocolRepository2 = new SyncProtocolRepository(appContext3, null, 2, false ? 1 : 0);
                                                                                    String string5 = jSONObjectPut9.toString();
                                                                                    Intrinsics.checkNotNullExpressionValue(string5, "toString(...)");
                                                                                    str12 = str12;
                                                                                    r16 = this;
                                                                                    j9 = j20;
                                                                                    j5 = j21;
                                                                                    j10 = j19;
                                                                                    j11 = j18;
                                                                                    syncProtocolRepository2.enqueueLocalOperation(r34, actor, operationId, "EXECUTE_SERVICE_TRANSFER", "SERVICE_TRANSFER", str16, "CREATE", string5);
                                                                                    r15 = r16;
                                                                                } catch (Throwable th21) {
                                                                                    th = th21;
                                                                                }
                                                                            } catch (Throwable th22) {
                                                                                th = th22;
                                                                                r16 = this;
                                                                            }
                                                                        }
                                                                        String strEsc = r15.esc(receiptAccountId);
                                                                        StringBuilder sb4 = new StringBuilder("{\"service_type\":\"");
                                                                        sb4.append(str18);
                                                                        sb4.append("\",\"delivered_minor\":");
                                                                        sb4.append(deliveredMinor);
                                                                        sb4.append(",\"profit_minor\":");
                                                                        sb4.append(j10);
                                                                        sb4.append(",\"provider_fee_minor\":");
                                                                        sb4.append(j8);
                                                                        sb4.append(",\"customer_charge_minor\":");
                                                                        long j22 = j5;
                                                                        sb4.append(j22);
                                                                        sb4.append(",\"receipt_account_id\":\"");
                                                                        sb4.append(strEsc);
                                                                        sb4.append("\"}");
                                                                        audit(r34, actor, "SERVICE_TRANSFER_POSTED", str16, null, sb4.toString(), str12);
                                                                        r34.setTransactionSuccessful();
                                                                        ServiceTransferResult serviceTransferResult3 = new ServiceTransferResult(str16, r65, j10, providerFeeMinor, j22, false, jM, j11, j9);
                                                                        r34.endTransaction();
                                                                        return serviceTransferResult3;
                                                                    } catch (Throwable th23) {
                                                                        th = th23;
                                                                        r4 = r34;
                                                                        r4.endTransaction();
                                                                        throw th;
                                                                    }
                                                                }
                                                                str11 = "customer_id";
                                                                contentValues.putNull(str11);
                                                                j4 = j3;
                                                                contentValues.put("reference", reference);
                                                                contentValues.put("note", note);
                                                                contentValues.put("payload_hash", strHash);
                                                                contentValues.put("created_by", actor);
                                                                str12 = str25;
                                                                contentValues.put("created_at", str12);
                                                                contentValues.put(str36, str37);
                                                                contentValues.put(NotificationCompat.CATEGORY_STATUS, "POSTED");
                                                                r34.insertOrThrow("service_transfers", null, contentValues);
                                                                if (Intrinsics.areEqual(paymentMode, obj6)) {
                                                                    ContentValues contentValues3 = new ContentValues();
                                                                    contentValues3.put("transfer_id", string4);
                                                                    contentValues3.put(str11, customerId);
                                                                    str19 = str11;
                                                                    contentValues3.put("original_amount_minor", Long.valueOf(jRoundMilliToWhole2));
                                                                    contentValues3.put(NotificationCompat.CATEGORY_STATUS, "OPEN");
                                                                    contentValues3.put("note", note);
                                                                    contentValues3.put("created_at", str12);
                                                                    Unit unit2 = Unit.INSTANCE;
                                                                    r34.insertOrThrow("service_transfer_receivables", null, contentValues3);
                                                                    if (syncOrigin) {
                                                                        Context appContext4 = this.appContext;
                                                                        Intrinsics.checkNotNullExpressionValue(appContext4, "appContext");
                                                                        str14 = str36;
                                                                        j6 = j4;
                                                                        str17 = str37;
                                                                        j5 = jRoundMilliToWhole2;
                                                                        str16 = string4;
                                                                        str15 = "note";
                                                                        str13 = str19;
                                                                        new ServiceTransferReceivableRepository(appContext4).enqueueMirrorSnapshot(r34, actor, string4, operationId, (PointerIconCompat.TYPE_TEXT & 16) != 0 ? null : null, (PointerIconCompat.TYPE_TEXT & 32) != 0 ? null : null, (PointerIconCompat.TYPE_TEXT & 64) != 0 ? null : null, (PointerIconCompat.TYPE_TEXT & 128) != 0 ? null : null, (PointerIconCompat.TYPE_TEXT & 256) != 0 ? null : null, (PointerIconCompat.TYPE_TEXT & 512) != 0 ? null : null);
                                                                    } else {
                                                                        j5 = jRoundMilliToWhole2;
                                                                        str15 = "note";
                                                                        str13 = str19;
                                                                        j6 = j4;
                                                                        str17 = str37;
                                                                        str14 = str36;
                                                                        str16 = string4;
                                                                    }
                                                                } else {
                                                                    str13 = str11;
                                                                    str14 = str36;
                                                                    j5 = jRoundMilliToWhole2;
                                                                    str15 = "note";
                                                                    j6 = j4;
                                                                    str16 = string4;
                                                                    str17 = str37;
                                                                }
                                                                if (note == null) {
                                                                    strServiceTypeName = serviceTypeName(serviceType);
                                                                } else {
                                                                    strServiceTypeName = note;
                                                                }
                                                                r34 = r34;
                                                                insertWalletMovement(r34, str24, "OUT", jM, "TRANSFER_OUT", str16, operationId, 0L, strServiceTypeName, actor, str12, null, str17);
                                                                if (Intrinsics.areEqual(paymentMode, str26)) {
                                                                    j7 = jRoundMilliToWhole;
                                                                    str18 = serviceType;
                                                                    insertCashMovement(r34, str39, "IN", j5, "SERVICE_TRANSFER_RECEIPT", "SERVICE_TRANSFER", str16, operationId + ":RECEIPT", actor, "\u0645\u0642\u0628\u0648\u0636 \u062a\u062d\u0648\u064a\u0644 \u0631\u0635\u064a\u062f \u2022 " + serviceTypeName(serviceType) + " \u2022 \u0631\u0628\u062d " + jRoundMilliToWhole + " \u0644.\u0633", str12, str17);
                                                                } else {
                                                                    str18 = serviceType;
                                                                    j7 = r16;
                                                                }
                                                                if (syncOrigin) {
                                                                    long j110 = jM;
                                                                    long j111 = j2;
                                                                    long j112 = j7;
                                                                    JSONObject jSONObjectPut10 = new JSONObject().put("business_operation", "EXECUTE_SERVICE_TRANSFER").put("service_type", str18).put("service_key", str24).put("schema_version", 2).put("amount_debited_minor", j110).put("delivered_minor", RoundMilliToWhole).put("delivered_milli_syp", j111).put("profit_minor", j112);
                                                                    long j23 = j6;
                                                                    r65 = RoundMilliToWhole;
                                                                    jM = j110;
                                                                    j8 = providerFeeMinor;
                                                                    long j24 = j5;
                                                                    JSONObject jSONObjectPut11 = jSONObjectPut10.put("profit_milli_syp", j23).put("provider_fee_minor", j8).put("customer_charge_minor", j24).put("cash_account_id", str39).put("cash_account_type", tripleReceiptAccount.getFirst()).put("cash_account_currency_code", tripleReceiptAccount.getSecond()).put("payment_mode", paymentMode);
                                                                    if (customerId == null) {
                                                                        obj3 = JSONObject.NULL;
                                                                    } else {
                                                                        obj3 = customerId;
                                                                    }
                                                                    JSONObject jSONObjectPut12 = jSONObjectPut11.put(str13, obj3);
                                                                    if (reference == null) {
                                                                        obj4 = JSONObject.NULL;
                                                                    } else {
                                                                        obj4 = reference;
                                                                    }
                                                                    JSONObject jSONObjectPut13 = jSONObjectPut12.put("reference", obj4);
                                                                    if (note == null) {
                                                                        obj5 = JSONObject.NULL;
                                                                    } else {
                                                                        obj5 = note;
                                                                    }
                                                                    JSONObject jSONObjectPut14 = jSONObjectPut13.put(str15, obj5).put(str38, str12).put(str14, str17);
                                                                    Context appContext5 = this.appContext;
                                                                    Intrinsics.checkNotNullExpressionValue(appContext5, "appContext");
                                                                    ?? syncProtocolRepository3 = new SyncProtocolRepository(appContext5, null, 2, false ? 1 : 0);
                                                                    String string6 = jSONObjectPut14.toString();
                                                                    Intrinsics.checkNotNullExpressionValue(string6, "toString(...)");
                                                                    str12 = str12;
                                                                    r16 = this;
                                                                    j9 = j23;
                                                                    j5 = j24;
                                                                    j10 = j112;
                                                                    j11 = j111;
                                                                    syncProtocolRepository3.enqueueLocalOperation(r34, actor, operationId, "EXECUTE_SERVICE_TRANSFER", "SERVICE_TRANSFER", str16, "CREATE", string6);
                                                                    r15 = r16;
                                                                } else {
                                                                    j8 = providerFeeMinor;
                                                                    j11 = j2;
                                                                    j9 = j6;
                                                                    j10 = j7;
                                                                    r15 = this;
                                                                    r65 = r22;
                                                                }
                                                                String strEsc2 = r15.esc(receiptAccountId);
                                                                StringBuilder sb5 = new StringBuilder("{\"service_type\":\"");
                                                                sb5.append(str18);
                                                                sb5.append("\",\"delivered_minor\":");
                                                                sb5.append(deliveredMinor);
                                                                sb5.append(",\"profit_minor\":");
                                                                sb5.append(j10);
                                                                sb5.append(",\"provider_fee_minor\":");
                                                                sb5.append(j8);
                                                                sb5.append(",\"customer_charge_minor\":");
                                                                long j25 = j5;
                                                                sb5.append(j25);
                                                                sb5.append(",\"receipt_account_id\":\"");
                                                                sb5.append(strEsc2);
                                                                sb5.append("\"}");
                                                                audit(r34, actor, "SERVICE_TRANSFER_POSTED", str16, null, sb5.toString(), str12);
                                                                r34.setTransactionSuccessful();
                                                                ServiceTransferResult serviceTransferResult4 = new ServiceTransferResult(str16, r65, j10, providerFeeMinor, j25, false, jM, j11, j9);
                                                                r34.endTransaction();
                                                                return serviceTransferResult4;
                                                            } catch (Throwable th24) {
                                                                th = th24;
                                                                r34 = r34;
                                                            }
                                                        } catch (Throwable th25) {
                                                            th = th25;
                                                            RoundMilliToWhole = r34;
                                                            th = th;
                                                            r4 = RoundMilliToWhole;
                                                        }
                                                        th = th;
                                                        r35 = r34;
                                                        r4 = r35;
                                                    }
                                                } catch (Throwable th26) {
                                                    th = th26;
                                                }
                                                th = th;
                                                r35 = r34;
                                                r4 = r35;
                                            } catch (Throwable th27) {
                                                th = th27;
                                                r4 = RoundMilliToWhole;
                                            }
                                        } catch (Throwable th28) {
                                            th = th28;
                                            RoundMilliToWhole = RoundMilliToWhole;
                                        }
                                    } catch (Throwable th29) {
                                        th3 = th29;
                                    }
                                } catch (Throwable th30) {
                                    th = th30;
                                    RoundMilliToWhole = RoundMilliToWhole;
                                    th = th;
                                    r4 = RoundMilliToWhole;
                                    r4.endTransaction();
                                    throw th;
                                }
                            } catch (Throwable th31) {
                                th = th31;
                            }
                            r4.endTransaction();
                            throw th;
                        }
                    }
                    throw new IllegalArgumentException("\u062a\u0627\u0631\u064a\u062e \u0623\u0648 \u0648\u0642\u062a \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                } catch (Throwable th32) {
                    th2 = th32;
                }
            } catch (Throwable th33) {
                th = th33;
                th = th;
                r4 = RoundMilliToWhole;
                r4.endTransaction();
                throw th;
            }
        } catch (Throwable th34) {
            th = th34;
            RoundMilliToWhole = RoundMilliToWhole;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v0, types: [com.hazem.dailystore.data.ServiceTransferRepository] */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v10 */
    /* JADX WARN: Type inference failed for: r14v11, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r14v12 */
    /* JADX WARN: Type inference failed for: r14v13, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r14v16 */
    /* JADX WARN: Type inference failed for: r14v17 */
    /* JADX WARN: Type inference failed for: r14v2 */
    /* JADX WARN: Type inference failed for: r14v3, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r14v7 */
    /* JADX WARN: Type inference failed for: r2v17, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r2v19, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r35v0, types: [com.hazem.dailystore.data.ServiceTransferRepository] */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.Object, net.zetetic.database.sqlcipher.SQLiteDatabase] */
    public final void reverseTransfer(String actor, String transferId, String operationId, String reason) throws Throwable {
        String[] strArr;
        String str;
        String str2;
        ?? writableDatabase = this;
        Intrinsics.checkNotNullParameter(actor, "actor");
        Intrinsics.checkNotNullParameter(transferId, "transferId");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        Intrinsics.checkNotNullParameter(reason, "reason");
        if (StringsKt.isBlank(operationId) || StringsKt.isBlank(reason)) {
            throw new IllegalArgumentException("\u0633\u0628\u0628 \u0627\u0644\u0639\u0643\u0633 \u0645\u0637\u0644\u0648\u0628".toString());
        }
        writableDatabase = writableDatabase.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        writableDatabase.beginTransaction();
        try {
            try {
                writableDatabase.requireManager(writableDatabase, actor);
                writableDatabase.ensureSystemAccounts(writableDatabase);
                Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT service_key,amount_delivered_minor,customer_charge_minor,receipt_account_id,status,payment_mode,amount_debited_minor FROM service_transfers WHERE id=?", new String[]{transferId});
                try {
                    Cursor cursor = cursorRawQuery;
                    if (!cursor.moveToFirst()) {
                        try {
                            throw new IllegalArgumentException("\u0639\u0645\u0644\u064a\u0629 \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629".toString());
                        } catch (Throwable th) {
                            th = th;
                        }
                    } else {
                        String[] strArr2 = {cursor.getString(0), String.valueOf(cursor.getLong(1)), String.valueOf(cursor.getLong(2)), cursor.getString(3), cursor.getString(4), cursor.getString(5), String.valueOf(cursor.getLong(6))};
                        CloseableKt.closeFinally(cursorRawQuery, null);
                        boolean zAreEqual = Intrinsics.areEqual(strArr2[5], "CREDIT");
                        if (zAreEqual) {
                            Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT COALESCE(SUM(amount_minor),0) FROM service_transfer_receivable_payments WHERE transfer_id=?", new String[]{transferId});
                            try {
                                Cursor cursor2 = cursorRawQuery2;
                                cursor2.moveToFirst();
                                long j = cursor2.getLong(0);
                                CloseableKt.closeFinally(cursorRawQuery2, null);
                                if (j != 0) {
                                    throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0639\u0643\u0633 \u062a\u062d\u0648\u064a\u0644 \u0622\u062c\u0644 \u0628\u0639\u062f \u062a\u0633\u062c\u064a\u0644 \u0623\u064a \u062a\u062d\u0635\u064a\u0644 \u0639\u0644\u064a\u0647".toString());
                                }
                            } catch (Throwable th2) {
                                try {
                                    throw th2;
                                } catch (Throwable th3) {
                                    CloseableKt.closeFinally(cursorRawQuery2, th2);
                                    throw th3;
                                }
                            }
                        }
                        String str3 = strArr2[3];
                        Intrinsics.checkNotNull(str3);
                        String str4 = strArr2[2];
                        Intrinsics.checkNotNull(str4);
                        long j2 = Long.parseLong(str4);
                        Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT service_key,direction,amount_minor,transaction_type,source_id,note\n                   FROM service_wallet_transactions WHERE operation_id=?", new String[]{operationId});
                        try {
                            Cursor cursor3 = cursorRawQuery3;
                            if (cursor3.moveToFirst()) {
                                strArr = new String[]{cursor3.getString(0), cursor3.getString(1), String.valueOf(cursor3.getLong(2)), cursor3.getString(3), cursor3.getString(4), cursor3.isNull(5) ? null : cursor3.getString(5)};
                            } else {
                                strArr = null;
                            }
                            CloseableKt.closeFinally(cursorRawQuery3, null);
                            if (strArr != null) {
                                if (!Intrinsics.areEqual(strArr[0], strArr2[0]) || !Intrinsics.areEqual(strArr[1], "IN") || !Intrinsics.areEqual(strArr[2], strArr2[6]) || !Intrinsics.areEqual(strArr[3], "TRANSFER_REVERSAL") || !Intrinsics.areEqual(strArr[4], transferId) || !Intrinsics.areEqual(strArr[5], reason)) {
                                    throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0639\u0643\u0633 \u0645\u0633\u062a\u062e\u062f\u0645 \u0645\u0633\u0628\u0642\u0627\u064b \u0644\u0639\u0645\u0644\u064a\u0629 \u0623\u0648 \u0628\u064a\u0627\u0646\u0627\u062a \u0645\u062e\u062a\u0644\u0641\u0629".toString());
                                }
                                if (!Intrinsics.areEqual(strArr2[4], "REVERSED")) {
                                    throw new IllegalArgumentException("\u064a\u0648\u062c\u062f \u0633\u062c\u0644 \u0639\u0643\u0633 \u062f\u0648\u0646 \u0627\u0643\u062a\u0645\u0627\u0644 \u062d\u0627\u0644\u0629 \u0627\u0644\u0639\u0645\u0644\u064a\u0629\u061b \u064a\u0644\u0632\u0645 \u0641\u062d\u0635 \u0633\u0644\u0627\u0645\u0629 \u0627\u0644\u062f\u0641\u062a\u0631".toString());
                                }
                                if (!zAreEqual) {
                                    Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT 1 FROM cash_transactions WHERE operation_id=? AND account_id=?\n                           AND direction='OUT' AND amount_minor=? AND transaction_type='SERVICE_TRANSFER_REVERSAL'", new String[]{operationId + ":RECEIPT", str3, String.valueOf(j2)});
                                    try {
                                        boolean zMoveToFirst = cursorRawQuery4.moveToFirst();
                                        CloseableKt.closeFinally(cursorRawQuery4, null);
                                        if (!zMoveToFirst) {
                                            throw new IllegalArgumentException("\u0633\u062c\u0644 \u0639\u0643\u0633 \u0627\u0644\u0645\u062d\u0641\u0638\u0629 \u0645\u0648\u062c\u0648\u062f \u0644\u0643\u0646 \u062d\u0631\u0643\u0629 \u062d\u0633\u0627\u0628 \u0627\u0644\u0642\u0628\u0636 \u0645\u0641\u0642\u0648\u062f\u0629".toString());
                                        }
                                    } catch (Throwable th4) {
                                        try {
                                            throw th4;
                                        } catch (Throwable th5) {
                                            CloseableKt.closeFinally(cursorRawQuery4, th4);
                                            throw th5;
                                        }
                                    }
                                }
                                writableDatabase.setTransactionSuccessful();
                                writableDatabase.endTransaction();
                                return;
                            }
                            if (!Intrinsics.areEqual(strArr2[4], "POSTED")) {
                                throw new IllegalArgumentException("\u0639\u0645\u0644\u064a\u0629 \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u0645\u0639\u0643\u0648\u0633\u0629 \u0645\u0633\u0628\u0642\u0627\u064b \u0623\u0648 \u063a\u064a\u0631 \u0642\u0627\u0628\u0644\u0629 \u0644\u0644\u0639\u0643\u0633".toString());
                            }
                            if (!zAreEqual && writableDatabase.cashBalance(writableDatabase, str3) < j2) {
                                throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0639\u0643\u0633 \u0627\u0644\u0639\u0645\u0644\u064a\u0629: \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0645\u062a\u0628\u0642\u064a \u0641\u064a \u062d\u0633\u0627\u0628 \u0627\u0644\u0642\u0628\u0636 \u0623\u0642\u0644 \u0645\u0646 \u0627\u0644\u0645\u0628\u0644\u063a \u0627\u0644\u0645\u0642\u0628\u0648\u0636".toString());
                            }
                            String strStamp = stamp();
                            String str5 = strArr2[0];
                            Intrinsics.checkNotNull(str5);
                            String str6 = strArr2[6];
                            Intrinsics.checkNotNull(str6);
                            long j3 = Long.parseLong(str6);
                            Intrinsics.checkNotNull(strStamp);
                            try {
                                insertWalletMovement$default(this, writableDatabase, str5, "IN", j3, "TRANSFER_REVERSAL", transferId, operationId, 0L, reason, actor, strStamp, null, null, 6144, null);
                                if (!zAreEqual) {
                                    insertCashMovement$default(this, writableDatabase, str3, "OUT", j2, "SERVICE_TRANSFER_REVERSAL", "SERVICE_TRANSFER_REVERSAL", transferId, operationId + ":RECEIPT", actor, "\u0639\u0643\u0633 \u062a\u062d\u0648\u064a\u0644 \u0631\u0635\u064a\u062f: " + reason, strStamp, null, 2048, null);
                                    writableDatabase = writableDatabase;
                                    str = "REVERSED";
                                    str2 = NotificationCompat.CATEGORY_STATUS;
                                } else {
                                    ContentValues contentValues = new ContentValues();
                                    str = "REVERSED";
                                    str2 = NotificationCompat.CATEGORY_STATUS;
                                    contentValues.put(str2, str);
                                    Unit unit = Unit.INSTANCE;
                                    writableDatabase = writableDatabase;
                                    try {
                                        writableDatabase.update("service_transfer_receivables", contentValues, "transfer_id=? AND status='OPEN'", new String[]{transferId});
                                        writableDatabase = writableDatabase;
                                    } catch (Throwable th6) {
                                        th = th6;
                                    }
                                }
                                ContentValues contentValues2 = new ContentValues();
                                contentValues2.put(str2, str);
                                contentValues2.put("reversed_at", strStamp);
                                contentValues2.put("reversed_by", actor);
                                contentValues2.put("reversal_reason", reason);
                                writableDatabase.update("service_transfers", contentValues2, "id=? AND status='POSTED'", new String[]{transferId});
                                audit(writableDatabase, actor, "SERVICE_TRANSFER_REVERSED", transferId, "{\"status\":\"POSTED\"}", "{\"status\":\"REVERSED\",\"reason\":\"" + esc(reason) + "\"}", strStamp);
                                if (zAreEqual) {
                                    Context appContext = this.appContext;
                                    Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
                                    new ServiceTransferReceivableRepository(appContext).enqueueMirrorSnapshot(writableDatabase, actor, transferId, operationId, (PointerIconCompat.TYPE_TEXT & 16) != 0 ? null : null, (PointerIconCompat.TYPE_TEXT & 32) != 0 ? null : null, (PointerIconCompat.TYPE_TEXT & 64) != 0 ? null : null, (PointerIconCompat.TYPE_TEXT & 128) != 0 ? null : null, (PointerIconCompat.TYPE_TEXT & 256) != 0 ? null : null, (PointerIconCompat.TYPE_TEXT & 512) != 0 ? null : null);
                                }
                                writableDatabase.setTransactionSuccessful();
                                writableDatabase.endTransaction();
                                return;
                            } catch (Throwable th7) {
                                th = th7;
                                writableDatabase = writableDatabase;
                            }
                        } catch (Throwable th8) {
                            writableDatabase = writableDatabase;
                            try {
                                throw th8;
                            } catch (Throwable th9) {
                                CloseableKt.closeFinally(cursorRawQuery3, th8);
                                throw th9;
                            }
                        }
                        writableDatabase.endTransaction();
                        throw th;
                    }
                } catch (Throwable th10) {
                    th = th10;
                }
                Throwable th11 = th;
                try {
                    throw th11;
                } catch (Throwable th12) {
                    CloseableKt.closeFinally(cursorRawQuery, th11);
                    throw th12;
                }
            } catch (Throwable th13) {
                th = th13;
            }
        } catch (Throwable th14) {
            th = th14;
            writableDatabase = writableDatabase;
        }
    }

    private final void ensureWallets(SQLiteDatabase db) {
        db.execSQL("INSERT OR IGNORE INTO service_wallets(service_key,name,created_at) VALUES(?,?,?)", new String[]{SYRIATEL_SHARED, "\u0631\u0635\u064a\u062f \u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0627\u0644\u0645\u0634\u062a\u0631\u0643", stamp()});
        db.execSQL("INSERT OR IGNORE INTO service_wallets(service_key,name,created_at) VALUES(?,?,?)", new String[]{"SHAM_CASH", "\u0631\u0635\u064a\u062f \u0634\u0627\u0645 \u0643\u0627\u0634", stamp()});
    }

    private final void ensureSystemAccounts(SQLiteDatabase db) throws IOException {
        ensureWallets(db);
        ensureServiceAccountLink(db, SYRIATEL_SHARED, "system_service_syriatel", "\u0645\u062d\u0641\u0638\u0629 \u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0643\u0627\u0634", CollectionsKt.listOf((Object[]) new String[]{"\u0645\u062d\u0641\u0638\u0629 \u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0643\u0627\u0634", "\u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0643\u0627\u0634", "\u0645\u062d\u0641\u0638\u0629 \u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644", "\u0631\u0635\u064a\u062f \u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0627\u0644\u0645\u0634\u062a\u0631\u0643", "\u0631\u0635\u064a\u062f \u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644", "\u0645\u062d\u0641\u0638\u0629 \u0631\u0635\u064a\u062f \u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644"}));
        ensureServiceAccountLink(db, "SHAM_CASH", "system_service_sham_cash", "\u0645\u062d\u0641\u0638\u0629 \u0634\u0627\u0645 \u0643\u0627\u0634", CollectionsKt.listOf((Object[]) new String[]{"\u0645\u062d\u0641\u0638\u0629 \u0634\u0627\u0645 \u0643\u0627\u0634", "\u0634\u0627\u0645 \u0643\u0627\u0634", "\u0631\u0635\u064a\u062f \u0634\u0627\u0645 \u0643\u0627\u0634", "\u0645\u062d\u0641\u0638\u0629 \u0634\u0627\u0645"}));
        migrateLegacyWalletMovements(db, SYRIATEL_SHARED);
        migrateLegacyWalletMovements(db, "SHAM_CASH");
    }

    private final void ensureServiceAccountLink(SQLiteDatabase db, String key, String systemId, String defaultName, List<String> aliases) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT account_id FROM service_wallet_account_links WHERE service_key=?", new String[]{key});
        try {
            Cursor cursor = cursorRawQuery;
            String string = cursor.moveToFirst() ? cursor.getString(0) : null;
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (string != null) {
                Cursor cursorRawQuery2 = db.rawQuery("SELECT 1 FROM cash_accounts WHERE id=? AND account_type='WALLET' AND currency_code='SYP' AND is_active=1", new String[]{string});
                try {
                    boolean zMoveToFirst = cursorRawQuery2.moveToFirst();
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    if (!zMoveToFirst) {
                        throw new IllegalArgumentException("\u0645\u062d\u0641\u0638\u0629 \u0627\u0644\u062e\u062f\u0645\u0629 \u0627\u0644\u0645\u0631\u062a\u0628\u0637\u0629 \u063a\u064a\u0631 \u0646\u0634\u0637\u0629 \u0623\u0648 \u062a\u063a\u064a\u0631 \u0646\u0648\u0639\u0647\u0627\u061b \u0631\u0627\u062c\u0639 \u0627\u0644\u062d\u0633\u0627\u0628\u0627\u062a \u0627\u0644\u0645\u0627\u0644\u064a\u0629".toString());
                    }
                    return;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(cursorRawQuery2, th);
                        throw th2;
                    }
                }
            }
            List<String> list = aliases;
            String str = "SELECT id FROM cash_accounts WHERE account_type='WALLET' AND currency_code='SYP' AND is_active=1 AND lower(trim(name)) IN (" + CollectionsKt.joinToString$default(list, ",", null, null, 0, null, new Function1() { // from class: com.hazem.dailystore.data.ServiceTransferRepository$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ServiceTransferRepository.ensureServiceAccountLink$lambda$72((String) obj);
                }
            }, 30, null) + ") ORDER BY created_at,id LIMIT 1";
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (String str2 : list) {
                Locale ROOT = Locale.ROOT;
                Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
                String lowerCase = str2.toLowerCase(ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                arrayList.add(lowerCase);
            }
            Cursor cursorRawQuery3 = db.rawQuery(str, (String[]) arrayList.toArray(new String[0]));
            try {
                Cursor cursor2 = cursorRawQuery3;
                String string2 = cursor2.moveToFirst() ? cursor2.getString(0) : null;
                CloseableKt.closeFinally(cursorRawQuery3, null);
                if (string2 == null) {
                    Cursor cursorRawQuery4 = db.rawQuery("SELECT 1 FROM cash_accounts WHERE id=?", new String[]{systemId});
                    try {
                        boolean zMoveToFirst2 = cursorRawQuery4.moveToFirst();
                        CloseableKt.closeFinally(cursorRawQuery4, null);
                        String string3 = zMoveToFirst2 ? UUID.randomUUID().toString() : systemId;
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("id", string3);
                        contentValues.put("name", defaultName);
                        contentValues.put("account_type", "WALLET");
                        contentValues.put("currency_code", "SYP");
                        contentValues.put("opening_balance_minor", (Long) 0L);
                        contentValues.put("is_active", (Integer) 1);
                        contentValues.put("created_at", stamp());
                        db.insertOrThrow("cash_accounts", null, contentValues);
                        string2 = string3;
                    } catch (Throwable th3) {
                        try {
                            throw th3;
                        } catch (Throwable th4) {
                            CloseableKt.closeFinally(cursorRawQuery4, th3);
                            throw th4;
                        }
                    }
                }
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("service_key", key);
                contentValues2.put("account_id", string2);
                contentValues2.put("linked_at", stamp());
                db.insertOrThrow("service_wallet_account_links", null, contentValues2);
            } catch (Throwable th5) {
                try {
                    throw th5;
                } catch (Throwable th6) {
                    CloseableKt.closeFinally(cursorRawQuery3, th5);
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

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence ensureServiceAccountLink$lambda$72(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return "?";
    }

    private final String serviceAccountId(SQLiteDatabase db, String key) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT account_id FROM service_wallet_account_links WHERE service_key=?", new String[]{key});
        try {
            Cursor cursor = cursorRawQuery;
            if (!cursor.moveToFirst()) {
                throw new IllegalArgumentException("\u0645\u062d\u0641\u0638\u0629 \u0627\u0644\u062e\u062f\u0645\u0629 \u063a\u064a\u0631 \u0645\u0631\u0628\u0648\u0637\u0629 \u0628\u062d\u0633\u0627\u0628 \u0645\u0627\u0644\u064a".toString());
            }
            String string = cursor.getString(0);
            CloseableKt.closeFinally(cursorRawQuery, null);
            Intrinsics.checkNotNullExpressionValue(string, "use(...)");
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

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final void migrateLegacyWalletMovements(SQLiteDatabase db, String key) throws IOException {
        String str;
        String strServiceAccountId = serviceAccountId(db, key);
        Cursor cursorRawQuery = db.rawQuery("SELECT opening_balance_minor FROM cash_accounts WHERE id=?", new String[]{strServiceAccountId});
        try {
            Cursor cursor = cursorRawQuery;
            cursor.moveToFirst();
            int i = 0;
            long j = cursor.getLong(0);
            CloseableKt.closeFinally(cursorRawQuery, null);
            Cursor cursorRawQuery2 = db.rawQuery("SELECT 1 FROM cash_transactions WHERE account_id=? AND transaction_type IN ('WALLET_DAILY_OPENING_ADJUSTMENT','WALLET_TOP_UP') LIMIT 1", new String[]{strServiceAccountId});
            try {
                boolean zMoveToFirst = cursorRawQuery2.moveToFirst();
                CloseableKt.closeFinally(cursorRawQuery2, null);
                Cursor cursorRawQuery3 = db.rawQuery("SELECT id,direction,amount_minor,transaction_type,note,created_by,created_at FROM service_wallet_transactions WHERE service_key=? ORDER BY created_at,id", new String[]{key});
                try {
                    Cursor cursor2 = cursorRawQuery3;
                    while (cursor2.moveToNext()) {
                        String string = cursor2.getString(i);
                        Cursor cursorRawQuery4 = db.rawQuery("SELECT 1 FROM cash_transactions WHERE source_type='SERVICE_WALLET_LEDGER' AND source_id=? LIMIT 1", new String[]{string});
                        try {
                            boolean zMoveToFirst2 = cursorRawQuery4.moveToFirst();
                            CloseableKt.closeFinally(cursorRawQuery4, null);
                            if (!zMoveToFirst2) {
                                String string2 = cursor2.getString(3);
                                if (!Intrinsics.areEqual(string2, "OPENING") || (j <= 0 && !zMoveToFirst)) {
                                    if (string2 != null) {
                                        switch (string2.hashCode()) {
                                            case -545201864:
                                                str = !string2.equals("OPENING") ? "SERVICE_FLOAT_" + string2 : "SERVICE_FLOAT_OPENING";
                                                break;
                                            case -23564633:
                                                if (string2.equals("RECHARGE")) {
                                                    str = "SERVICE_RECHARGE_IN";
                                                }
                                                break;
                                            case 175054042:
                                                if (string2.equals("TRANSFER_OUT")) {
                                                    str = "SERVICE_TRANSFER_OUT";
                                                }
                                                break;
                                            case 1329543970:
                                                if (string2.equals("TRANSFER_REVERSAL")) {
                                                    str = "SERVICE_TRANSFER_REVERSAL_IN";
                                                }
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                    String string3 = cursor2.getString(6);
                                    ContentValues contentValues = new ContentValues();
                                    contentValues.put("id", UUID.randomUUID().toString());
                                    contentValues.put("account_id", strServiceAccountId);
                                    contentValues.put("direction", cursor2.getString(1));
                                    contentValues.put("amount_minor", Long.valueOf(cursor2.getLong(2)));
                                    contentValues.put("currency_code", "SYP");
                                    contentValues.put("transaction_type", str);
                                    contentValues.put("source_type", "SERVICE_WALLET_LEDGER");
                                    contentValues.put("source_id", string);
                                    contentValues.put("operation_id", "SERVICE_FLOAT_LEDGER:" + string);
                                    String string4 = cursor2.getString(4);
                                    if (string4 != null) {
                                        string2 = string4;
                                    }
                                    contentValues.put("description", "\u062a\u0631\u062d\u064a\u0644 \u062d\u0631\u0643\u0629 \u0631\u0635\u064a\u062f \u062e\u062f\u0645\u0629 \u0642\u062f\u064a\u0645\u0629 \u2022 " + string2);
                                    contentValues.putNull("created_by");
                                    contentValues.put("created_at", string3);
                                    StoreTime storeTime = StoreTime.INSTANCE;
                                    Intrinsics.checkNotNull(string3);
                                    contentValues.put("operating_date", storeTime.operatingDate(db, string3));
                                    db.insertOrThrow("cash_transactions", null, contentValues);
                                    i = 0;
                                }
                            }
                        } catch (Throwable th) {
                            try {
                                throw th;
                            } catch (Throwable th2) {
                                CloseableKt.closeFinally(cursorRawQuery4, th);
                                throw th2;
                            }
                        }
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursorRawQuery3, null);
                } catch (Throwable th3) {
                    try {
                        throw th3;
                    } catch (Throwable th4) {
                        CloseableKt.closeFinally(cursorRawQuery3, th3);
                        throw th4;
                    }
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

    private final long realizedCreditProfitMilliForDate(SQLiteDatabase db, String date, String serviceType) throws IOException {
        long j;
        long j2;
        long j3;
        Cursor cursorRawQuery = db.rawQuery("SELECT t.id,t.customer_charge_minor,t.profit_milli_syp,p.amount_minor,p.operating_date\n               FROM service_transfer_receivable_payments p\n               JOIN service_transfers t ON t.id=p.transfer_id\n               WHERE p.operating_date<=? AND t.service_type=? AND t.payment_mode='CREDIT' AND t.status='POSTED'\n               ORDER BY t.id,p.created_at,p.id", new String[]{date, serviceType});
        try {
            Cursor cursor = cursorRawQuery;
            long jM = 0;
            long j4 = 0;
            long j5 = 0;
            long j6 = 0;
            long j7 = 0;
            String str = null;
            while (cursor.moveToNext()) {
                String string = cursor.getString(0);
                if (Intrinsics.areEqual(str, string)) {
                    j = j6;
                    j2 = j7;
                    j3 = j4;
                } else {
                    str = string;
                    j5 = 0;
                    j2 = 0;
                    j3 = cursor.getLong(1);
                    j = cursor.getLong(2);
                }
                if (j3 <= 0) {
                    throw new IllegalArgumentException("\u0645\u0628\u0644\u063a \u0630\u0645\u0629 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u063a\u064a\u0631 \u0635\u0627\u0644\u062d \u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0631\u0628\u062d \u0627\u0644\u0645\u062d\u0635\u0644".toString());
                }
                long jM2 = MainActivity$$ExternalSyntheticBackport0.m(j5, cursor.getLong(3));
                long jRealizedProfitMilliSyp = realizedProfitMilliSyp(j, j3, jM2);
                long j8 = jRealizedProfitMilliSyp - j2;
                if (Intrinsics.areEqual(cursor.getString(4), date)) {
                    jM = MainActivity$$ExternalSyntheticBackport0.m(jM, j8);
                }
                j6 = j;
                j5 = jM2;
                j4 = j3;
                j7 = jRealizedProfitMilliSyp;
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            return jM;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final long realizedProfitMilliSyp(long profitMilliSyp, long chargeMinor, long paidMinor) {
        if (profitMilliSyp <= 0 || paidMinor <= 0) {
            return 0L;
        }
        if (chargeMinor <= 0 || paidMinor > chargeMinor) {
            throw new IllegalArgumentException("\u062a\u062d\u0635\u064a\u0644 \u0630\u0645\u0629 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u064a\u062a\u062c\u0627\u0648\u0632 \u0623\u0635\u0644 \u0627\u0644\u062f\u064a\u0646".toString());
        }
        return roundRatioMilli(profitMilliSyp, paidMinor, chargeMinor);
    }

    private final long walletBalance(SQLiteDatabase db, String key) {
        return cashBalance(db, serviceAccountId(db, key));
    }

    static /* synthetic */ void insertWalletMovement$default(ServiceTransferRepository serviceTransferRepository, SQLiteDatabase sQLiteDatabase, String str, String str2, long j, String str3, String str4, String str5, long j2, String str6, String str7, String str8, String str9, String str10, int i, Object obj) throws IOException {
        serviceTransferRepository.insertWalletMovement(sQLiteDatabase, str, str2, j, str3, str4, str5, j2, str6, str7, str8, (i & 2048) != 0 ? null : str9, (i & 4096) != 0 ? null : str10);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:28:0x00ba  */
    /* JADX WARN: Instruction removed from duplicated block: B:28:0x00ba, please report this as an issue */
    private final void insertWalletMovement(SQLiteDatabase db, String key, String direction, long amount, String type, String sourceId, String operationId, long fee, String note, String actor, String now, String payloadHash, String eventDate) throws IOException {
        String str;
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", string);
        contentValues.put("service_key", key);
        contentValues.put("direction", direction);
        contentValues.put("amount_minor", Long.valueOf(amount));
        contentValues.put("transaction_type", type);
        contentValues.put("source_id", sourceId);
        contentValues.put("operation_id", operationId);
        if (payloadHash != null) {
            contentValues.put("payload_hash", payloadHash);
        }
        contentValues.put("fee_minor", Long.valueOf(fee));
        contentValues.put("note", note);
        contentValues.put("created_by", actor);
        contentValues.put("created_at", now);
        contentValues.put("operating_date", eventDate == null ? StoreTime.INSTANCE.operatingDate(db, now) : eventDate);
        db.insertOrThrow("service_wallet_transactions", null, contentValues);
        switch (type) {
            case "OPENING":
                str = "SERVICE_FLOAT_OPENING";
                break;
            case "RECHARGE":
                str = "SERVICE_RECHARGE_IN";
                break;
            case "TRANSFER_OUT":
                str = "SERVICE_TRANSFER_OUT";
                break;
            case "TRANSFER_REVERSAL":
                str = "SERVICE_TRANSFER_REVERSAL_IN";
                break;
            default:
                str = "SERVICE_FLOAT_" + type;
                break;
        }
        String str2 = str;
        insertCashMovement(db, serviceAccountId(db, key), direction, amount, str2, "SERVICE_WALLET_LEDGER", string, "SERVICE_FLOAT_LEDGER:" + string, actor, note == null ? type : note, now, eventDate);
    }

    static /* synthetic */ void insertCashMovement$default(ServiceTransferRepository serviceTransferRepository, SQLiteDatabase sQLiteDatabase, String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, int i, Object obj) {
        serviceTransferRepository.insertCashMovement(sQLiteDatabase, str, str2, j, str3, str4, str5, str6, str7, str8, str9, (i & 2048) != 0 ? null : str10);
    }

    private final void insertCashMovement(SQLiteDatabase db, String accountId, String direction, long amount, String type, String sourceType, String sourceId, String operationId, String actor, String description, String now, String eventDate) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", UUID.randomUUID().toString());
        contentValues.put("account_id", accountId);
        contentValues.put("direction", direction);
        contentValues.put("amount_minor", Long.valueOf(amount));
        contentValues.put("currency_code", "SYP");
        contentValues.put("transaction_type", type);
        contentValues.put("source_type", sourceType);
        contentValues.put("source_id", sourceId);
        contentValues.put("operation_id", operationId);
        contentValues.put("description", description);
        contentValues.put("created_by", actor);
        contentValues.put("operating_date", eventDate == null ? StoreTime.INSTANCE.operatingDate(db, now) : eventDate);
        contentValues.put("created_at", now);
        db.insertOrThrow("cash_transactions", null, contentValues);
    }

    private final Triple<String, String, Boolean> receiptAccount(SQLiteDatabase db, String id) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT account_type,currency_code,is_active FROM cash_accounts WHERE id=?", new String[]{id});
        try {
            Cursor cursor = cursorRawQuery;
            if (!cursor.moveToFirst()) {
                throw new IllegalArgumentException("\u062d\u0633\u0627\u0628 \u0627\u0644\u0642\u0628\u0636 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
            }
            Triple<String, String, Boolean> triple = new Triple<>(cursor.getString(0), cursor.getString(1), Boolean.valueOf(cursor.getInt(2) == 1));
            CloseableKt.closeFinally(cursorRawQuery, null);
            return triple;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final long cashBalance(SQLiteDatabase db, String id) throws IOException {
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

    private final void requireManager(SQLiteDatabase db, String actor) throws IOException {
        PermissionPolicy.INSTANCE.require(db, actor, PermissionPolicy.SERVICE_TRANSFERS_MANAGE);
        Cursor cursorRawQuery = db.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            String string = cursor.moveToFirst() ? cursor.getString(0) : null;
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (Intrinsics.areEqual(string, "SECONDARY")) {
                throw new IllegalArgumentException("\u0625\u062f\u0627\u0631\u0629 \u0623\u0631\u0635\u062f\u0629 \u0627\u0644\u062e\u062f\u0645\u0627\u062a \u0648\u062a\u0639\u0628\u0626\u062a\u0647\u0627 \u0644\u0645 \u064a\u0643\u062a\u0645\u0644 \u0645\u0633\u0627\u0631 \u0645\u0632\u0627\u0645\u0646\u062a\u0647\u0627 \u0628\u0639\u062f\u061b \u0646\u0641\u0651\u0630\u0647\u0627 \u0645\u0646 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a".toString());
            }
            if (string != null && !Intrinsics.areEqual(string, "PRIMARY")) {
                throw new IllegalArgumentException("\u062f\u0648\u0631 \u0627\u0644\u062c\u0647\u0627\u0632 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d \u0644\u0625\u062f\u0627\u0631\u0629 \u0623\u0631\u0635\u062f\u0629 \u0627\u0644\u062e\u062f\u0645\u0627\u062a".toString());
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

    private final void requireActiveUser(SQLiteDatabase db, String actor) throws IOException {
        if (StringsKt.isBlank(actor)) {
            throw new IllegalArgumentException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
        }
        Cursor cursorRawQuery = db.rawQuery("SELECT 1 FROM users WHERE id=? AND is_active=1", new String[]{actor});
        try {
            boolean zMoveToFirst = cursorRawQuery.moveToFirst();
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (!zMoveToFirst) {
                throw new IllegalArgumentException("\u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0646\u0634\u0637 \u0623\u0648 \u0627\u0646\u062a\u0647\u062a \u0627\u0644\u062c\u0644\u0633\u0629".toString());
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

    private final void audit(SQLiteDatabase db, String actor, String action, String id, String before, String after, String now) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", UUID.randomUUID().toString());
        contentValues.put("user_id", actor);
        contentValues.put("action", action);
        contentValues.put("entity_type", "SERVICE_TRANSFER");
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

    private final String serviceName(String key) {
        return Intrinsics.areEqual(key, SYRIATEL_SHARED) ? "\u0631\u0635\u064a\u062f \u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0627\u0644\u0645\u0634\u062a\u0631\u0643" : "\u0631\u0635\u064a\u062f \u0634\u0627\u0645 \u0643\u0627\u0634";
    }

    private final String serviceTypeName(String type) {
        if (Intrinsics.areEqual(type, SYRIATEL_BALANCE)) {
            return "\u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0627\u0644\u0639\u0627\u062f\u064a";
        }
        return Intrinsics.areEqual(type, SYRIATEL_CASH) ? "\u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0643\u0627\u0634" : "\u0634\u0627\u0645 \u0643\u0627\u0634";
    }

    private final long percent(long amount, long rate) {
        return MainActivity$$ExternalSyntheticBackport0.m(DailyBalanceRepository$$ExternalSyntheticBackport0.m$1(amount, rate), 50L) / 100;
    }

    private final long roundRatioMilli(long value, long numerator, long denominator) {
        if (value < 0 || numerator < 0 || denominator <= 0) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        BigInteger bigIntegerMultiply = BigInteger.valueOf(value).multiply(BigInteger.valueOf(numerator));
        BigInteger bigIntegerValueOf = BigInteger.valueOf(denominator);
        return bigIntegerMultiply.add(bigIntegerValueOf.divide(BigInteger.valueOf(2L))).divide(bigIntegerValueOf).longValueExact();
    }

    private final long roundMilliToWhole(long value) {
        if (value >= 0) {
            return MainActivity$$ExternalSyntheticBackport0.m(value, 500L) / 1000;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    private final String hash(String value) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = value.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrDigest = messageDigest.digest(bytes);
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
        return ArraysKt.joinToString$default(bArrDigest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.hazem.dailystore.data.ServiceTransferRepository$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ServiceTransferRepository.hash$lambda$101(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence hash$lambda$101(byte b) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    private final String esc(String value) {
        return StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(value, "\\", "\\\\", false, 4, (Object) null), "\"", "\\\"", false, 4, (Object) null), "\n", "\\n", false, 4, (Object) null), "\r", "\\r", false, 4, (Object) null);
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
