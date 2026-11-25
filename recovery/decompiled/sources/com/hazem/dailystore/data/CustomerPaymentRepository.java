package com.hazem.dailystore.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import androidx.core.app.NotificationCompat;
import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import net.zetetic.database.sqlcipher.SQLiteDatabase;
import org.json.JSONObject;

/* JADX INFO: compiled from: CustomerPaymentRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001:\u000589:;<B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b2\u0006\u0010\u000f\u001a\u00020\u0010J\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000b2\u0006\u0010\u0013\u001a\u00020\u0010J\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u000b2\u0006\u0010\u0013\u001a\u00020\u0010J:\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0010J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002JD\u0010 \u001a\u00020\u001f2\n\u0010!\u001a\u00060\"j\u0002`#2\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020\u001c2\u0006\u0010&\u001a\u00020\u001c2\u0006\u0010'\u001a\u00020\u0010H\u0002J\u001c\u0010(\u001a\u00020\u001c2\n\u0010!\u001a\u00060\"j\u0002`#2\u0006\u0010\u0013\u001a\u00020\u0010H\u0002J$\u0010)\u001a\u00020\u001c2\n\u0010!\u001a\u00060\"j\u0002`#2\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010%\u001a\u00020\u001cH\u0002J\u001c\u0010*\u001a\u00020\u001c2\n\u0010!\u001a\u00060\"j\u0002`#2\u0006\u0010\u0013\u001a\u00020\u0010H\u0002J,\u0010+\u001a\u00020,2\n\u0010!\u001a\u00060\"j\u0002`#2\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020\u001cH\u0002J\u0014\u0010&\u001a\u00020\u001c2\n\u0010!\u001a\u00060\"j\u0002`#H\u0002J\b\u0010-\u001a\u00020\u0010H\u0002J\b\u0010.\u001a\u00020\u0010H\u0002J\u001e\u0010/\u001a\u0004\u0018\u0001002\n\u0010!\u001a\u00060\"j\u0002`#2\u0006\u00101\u001a\u00020\u0010H\u0002J<\u00102\u001a\u00020\u001f2\n\u0010!\u001a\u00060\"j\u0002`#2\u0006\u00101\u001a\u00020\u00102\u0006\u00103\u001a\u00020\u00102\u0006\u00104\u001a\u00020\u00102\u0006\u00105\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u0010H\u0002J\u0010\u00106\u001a\u00020\u00102\u0006\u00107\u001a\u00020\u0010H\u0002R\u0018\u0010\u0002\u001a\n \u0006*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006="}, d2 = {"Lcom/hazem/dailystore/data/CustomerPaymentRepository;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "kotlin.jvm.PlatformType", "Landroid/content/Context;", "database", "Lcom/hazem/dailystore/data/AppDatabase;", "listCustomerSummaries", "", "Lcom/hazem/dailystore/data/CustomerFinancialSummary;", "listInvoices", "Lcom/hazem/dailystore/data/CustomerInvoicePaymentSummary;", "customerId", "", "listPayments", "Lcom/hazem/dailystore/data/CustomerPaymentRecord;", "invoiceId", "listRevaluations", "Lcom/hazem/dailystore/data/CustomerCreditRevaluationRecord;", "addPayment", "Lcom/hazem/dailystore/data/CustomerPaymentResult;", "actorUserId", "operationId", "cashAccountId", "amountSypMinor", "", "note", "applyRevaluations", "", "recordRevaluation", "db", "Lnet/zetetic/database/sqlcipher/SQLiteDatabase;", "Lcom/hazem/dailystore/data/AppSqliteDatabase;", "totalUsdCents", "originalRate", "currentRate", "now", "creditReductionUsdCents", "paidSypMinor", "paidUsdCents", "invoicePaymentState", "Lcom/hazem/dailystore/data/CustomerPaymentRepository$InvoicePaymentState;", "operatingDate", "timestamp", "findOperation", "Lcom/hazem/dailystore/data/CustomerPaymentRepository$ExistingOperation;", "id", "registerOperation", "type", "payloadHash", "reference", "hash", "value", "InvoiceForRevaluation", "CustomerInvoiceForPayment", "CustomerAccountForPayment", "InvoicePaymentState", "ExistingOperation", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class CustomerPaymentRepository {
    private final Context context;
    private final AppDatabase database;

    public CustomerPaymentRepository(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context.getApplicationContext();
        this.database = AppDatabase.INSTANCE.getInstance(context);
    }

    public final List<CustomerFinancialSummary> listCustomerSummaries() {
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery("SELECT id,name,phone FROM customers WHERE is_active=1 ORDER BY name COLLATE NOCASE", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            while (cursor.moveToNext()) {
                int i = 0;
                String string = cursor.getString(0);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                List<CustomerInvoicePaymentSummary> listListInvoices = listInvoices(string);
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : listListInvoices) {
                    CustomerInvoicePaymentSummary customerInvoicePaymentSummary = (CustomerInvoicePaymentSummary) obj;
                    if (!Intrinsics.areEqual(customerInvoicePaymentSummary.getInvoiceStatus(), "DRAFT") && !Intrinsics.areEqual(customerInvoicePaymentSummary.getInvoiceStatus(), "CANCELLED")) {
                        arrayList2.add(obj);
                    }
                }
                ArrayList arrayList3 = arrayList2;
                String string2 = cursor.getString(0);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                String string3 = cursor.getString(1);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                String string4 = cursor.isNull(2) ? null : cursor.getString(2);
                Iterator it = arrayList3.iterator();
                long totalUsdCents = 0;
                while (it.hasNext()) {
                    totalUsdCents += ((CustomerInvoicePaymentSummary) it.next()).getTotalUsdCents();
                }
                Iterator it2 = arrayList3.iterator();
                long paidUsdCents = 0;
                while (it2.hasNext()) {
                    paidUsdCents += ((CustomerInvoicePaymentSummary) it2.next()).getPaidUsdCents();
                }
                Iterator it3 = arrayList3.iterator();
                long outstandingUsdCents = 0;
                while (it3.hasNext()) {
                    outstandingUsdCents += ((CustomerInvoicePaymentSummary) it3.next()).getOutstandingUsdCents();
                }
                ArrayList arrayList4 = arrayList3;
                if (!(arrayList4 instanceof Collection) || !arrayList4.isEmpty()) {
                    Iterator it4 = arrayList4.iterator();
                    while (it4.hasNext()) {
                        if (((CustomerInvoicePaymentSummary) it4.next()).getOutstandingUsdCents() > 0 && (i = i + 1) < 0) {
                            CollectionsKt.throwCountOverflow();
                        }
                    }
                }
                Iterator it5 = arrayList3.iterator();
                long outstandingSypMinor = 0;
                while (it5.hasNext()) {
                    outstandingSypMinor += ((CustomerInvoicePaymentSummary) it5.next()).getOutstandingSypMinor();
                }
                arrayList.add(new CustomerFinancialSummary(string2, string3, string4, totalUsdCents, paidUsdCents, outstandingUsdCents, i, outstandingSypMinor));
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

    /* JADX WARN: Multi-variable type inference failed */
    public final List<CustomerInvoicePaymentSummary> listInvoices(String customerId) {
        Cursor cursor;
        Throwable th;
        Cursor cursor2;
        String str;
        String str2;
        String str3;
        String str4 = "DRAFT";
        String str5 = "CANCELLED";
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        if (StringsKt.isBlank(customerId)) {
            throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0632\u0628\u0648\u0646 \u0645\u0637\u0644\u0648\u0628".toString());
        }
        applyRevaluations(customerId);
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        long jCurrentRate = currentRate(readableDatabase);
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = readableDatabase.rawQuery("SELECT id,invoice_number,invoice_date,status,total_usd_cents,\n                      exchange_rate_syp_per_usd_milli\n               FROM sales_invoices\n               WHERE customer_id=? AND sale_type='CREDIT'\n               ORDER BY invoice_date DESC,created_at DESC", new String[]{customerId});
        try {
            Cursor cursor3 = cursorRawQuery;
            while (cursor3.moveToNext()) {
                try {
                    String string = cursor3.getString(0);
                    long j = cursor3.getLong(4);
                    long j2 = cursor3.getLong(5);
                    Intrinsics.checkNotNull(string);
                    long jPaidUsdCents = paidUsdCents(readableDatabase, string);
                    long jCreditReductionUsdCents = creditReductionUsdCents(readableDatabase, string);
                    ArrayList arrayList2 = arrayList;
                    cursor2 = cursorRawQuery;
                    String str6 = str4;
                    String str7 = str5;
                    try {
                        long jCoerceAtLeast = RangesKt.coerceAtLeast((j - jPaidUsdCents) - jCreditReductionUsdCents, 0L);
                        long jPaidSypMinor = paidSypMinor(readableDatabase, string, j2);
                        long jMax = j2 > 0 ? Math.max(jCurrentRate, j2) : 0L;
                        long jUsdCentsToSyp = j2 > 0 ? CurrencyConverter.INSTANCE.usdCentsToSyp(j, j2) : 0L;
                        long jUsdCentsToSyp2 = jMax > 0 ? CurrencyConverter.INSTANCE.usdCentsToSyp(jCoerceAtLeast, jMax) : 0L;
                        if (Intrinsics.areEqual(cursor3.getString(3), str7)) {
                            str3 = str7;
                            str = str6;
                        } else {
                            str = str6;
                            if (Intrinsics.areEqual(cursor3.getString(3), str)) {
                                str3 = str;
                            } else {
                                if (jCoerceAtLeast == 0) {
                                    str2 = "PAID";
                                } else if (jPaidUsdCents > 0) {
                                    str2 = "PARTIALLY_PAID";
                                } else {
                                    str2 = "UNPAID";
                                }
                                str3 = str2;
                            }
                        }
                        String string2 = cursor3.getString(1);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                        String string3 = cursor3.getString(2);
                        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                        String string4 = cursor3.getString(3);
                        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                        CustomerInvoicePaymentSummary customerInvoicePaymentSummary = new CustomerInvoicePaymentSummary(string, string2, string3, string4, str3, j, jPaidUsdCents, jCoerceAtLeast, j2, jUsdCentsToSyp, jPaidSypMinor, jUsdCentsToSyp2, jCreditReductionUsdCents);
                        arrayList = arrayList2;
                        arrayList.add(customerInvoicePaymentSummary);
                        cursorRawQuery = cursor2;
                        str5 = str7;
                        str4 = str;
                        readableDatabase = readableDatabase;
                        jCurrentRate = jCurrentRate;
                    } catch (Throwable th2) {
                        th = th2;
                        cursor = cursor2;
                        th = th;
                        try {
                            throw th;
                        } catch (Throwable th3) {
                            CloseableKt.closeFinally(cursor, th);
                            throw th3;
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                    cursor2 = cursorRawQuery;
                    cursor = cursor2;
                    th = th;
                    throw th;
                }
            }
            cursor2 = cursorRawQuery;
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursor2, null);
            return arrayList;
        } catch (Throwable th5) {
            th = th5;
            cursor = cursorRawQuery;
        }
    }

    public final List<CustomerPaymentRecord> listPayments(String invoiceId) {
        String str = "getString(...)";
        Intrinsics.checkNotNullParameter(invoiceId, "invoiceId");
        if (StringsKt.isBlank(invoiceId)) {
            throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0645\u0637\u0644\u0648\u0628".toString());
        }
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery("SELECT p.id,p.amount_minor,p.currency_code,a.name,a.account_type,\n                      p.created_at,t.description,COALESCE(i.exchange_rate_syp_per_usd_milli,0),\n                      COALESCE(t.exchange_rate_syp_per_usd_milli,0),\n                      COALESCE(p.exchange_rate_syp_per_usd_milli,0)\n               FROM invoice_payments p\n               JOIN cash_transactions t ON t.id=p.cash_transaction_id\n               JOIN cash_accounts a ON a.id=t.account_id\n               JOIN sales_invoices i ON i.id=p.invoice_id\n               WHERE p.invoice_type='SALE' AND p.invoice_id=?\n               ORDER BY p.created_at DESC", new String[]{invoiceId});
        try {
            Cursor cursor = cursorRawQuery;
            while (cursor.moveToNext()) {
                long j = cursor.getLong(1);
                String string = cursor.getString(2);
                long j2 = cursor.getLong(9);
                long jSypToUsdCents = (!Intrinsics.areEqual(string, "SYP") || j2 <= 0) ? j : CurrencyConverter.INSTANCE.sypToUsdCents(j, j2);
                String string2 = cursor.getString(0);
                Intrinsics.checkNotNullExpressionValue(string2, str);
                Intrinsics.checkNotNull(string);
                String string3 = cursor.getString(3);
                Intrinsics.checkNotNullExpressionValue(string3, str);
                String string4 = cursor.getString(4);
                Intrinsics.checkNotNullExpressionValue(string4, str);
                String string5 = cursor.getString(5);
                Intrinsics.checkNotNullExpressionValue(string5, str);
                arrayList.add(new CustomerPaymentRecord(string2, j, string, string3, string4, string5, cursor.isNull(6) ? null : cursor.getString(6), cursor.getLong(7), cursor.getLong(8), j2, jSypToUsdCents));
                str = str;
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

    public final List<CustomerCreditRevaluationRecord> listRevaluations(String invoiceId) {
        Intrinsics.checkNotNullParameter(invoiceId, "invoiceId");
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery("SELECT previous_syp_minor,new_syp_minor,applied_rate_syp_per_usd_milli,\n                      outstanding_usd_cents,created_at\n               FROM customer_credit_revaluations WHERE invoice_id=? ORDER BY created_at DESC", new String[]{invoiceId});
        try {
            Cursor cursor = cursorRawQuery;
            while (cursor.moveToNext()) {
                long j = cursor.getLong(0);
                long j2 = cursor.getLong(1);
                long j3 = cursor.getLong(2);
                long j4 = cursor.getLong(3);
                String string = cursor.getString(4);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                arrayList.add(new CustomerCreditRevaluationRecord(j, j2, j3, j4, string));
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v13, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r11v15 */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v20 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v4, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r12v8, types: [android.content.ContentValues] */
    /* JADX WARN: Type inference failed for: r19v1, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r19v2 */
    /* JADX WARN: Type inference failed for: r19v3, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r1v32, types: [com.hazem.dailystore.data.SyncProtocolRepository] */
    /* JADX WARN: Type inference failed for: r1v41, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v59, types: [com.hazem.dailystore.data.SyncProtocolRepository] */
    /* JADX WARN: Type inference failed for: r3v16, types: [java.lang.Object, net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r4v38, types: [com.hazem.dailystore.data.PermissionPolicy] */
    /* JADX WARN: Type inference failed for: r4v7, types: [com.hazem.dailystore.data.PermissionPolicy] */
    /* JADX WARN: Type inference failed for: r67v0, types: [com.hazem.dailystore.data.CustomerPaymentRepository] */
    /* JADX WARN: Type inference failed for: r6v10, types: [com.hazem.dailystore.data.StoreTime] */
    /* JADX WARN: Type inference failed for: r6v16, types: [com.hazem.dailystore.data.StoreTime] */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v32 */
    /* JADX WARN: Type inference failed for: r9v21, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
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
    public final CustomerPaymentResult addPayment(String actorUserId, String invoiceId, String operationId, String cashAccountId, long amountSypMinor, String note) {
        Throwable th;
        String string;
        Throwable th2;
        Throwable th3;
        boolean z;
        String string2;
        ?? writableDatabase = "pending-payment-";
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(invoiceId, "invoiceId");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        Intrinsics.checkNotNullParameter(cashAccountId, "cashAccountId");
        if (StringsKt.isBlank(invoiceId) || StringsKt.isBlank(operationId) || StringsKt.isBlank(cashAccountId)) {
            throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u063a\u064a\u0631 \u0645\u0643\u062a\u0645\u0644\u0629".toString());
        }
        if (amountSypMinor <= 0) {
            throw new IllegalArgumentException("\u0645\u0628\u0644\u063a \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u064a\u062c\u0628 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0623\u0643\u0628\u0631 \u0645\u0646 \u0635\u0641\u0631".toString());
        }
        writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        Object obj = (note == null || (string2 = StringsKt.trim((CharSequence) note).toString()) == null || string2.length() <= 0) ? null : string2;
        String strHash = hash(invoiceId + "|" + cashAccountId + "|" + amountSypMinor + "|" + (obj == null ? "" : obj));
        writableDatabase.beginTransaction();
        try {
            try {
                Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", null);
                try {
                    Cursor cursor = cursorRawQuery;
                    if (cursor.moveToFirst()) {
                        try {
                            string = cursor.getString(0);
                            th2 = null;
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
                        th2 = null;
                        string = null;
                    }
                    CloseableKt.closeFinally(cursorRawQuery, th2);
                    if (Intrinsics.areEqual(string, "SECONDARY")) {
                        PermissionPolicy.INSTANCE.requireAssigned(writableDatabase, actorUserId, PermissionPolicy.CUSTOMERS_COLLECT);
                    } else {
                        PermissionPolicy.INSTANCE.require(writableDatabase, actorUserId, PermissionPolicy.CUSTOMERS_COLLECT);
                    }
                    Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT status,sale_type,total_usd_cents,customer_id,invoice_number,\n                          exchange_rate_syp_per_usd_milli\n                   FROM sales_invoices WHERE id=?", new String[]{invoiceId});
                    try {
                        Cursor cursor2 = cursorRawQuery2;
                        if (cursor2.moveToFirst()) {
                            String string3 = cursor2.getString(0);
                            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                            String string4 = cursor2.getString(1);
                            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                            long j = cursor2.getLong(2);
                            String string5 = cursor2.isNull(3) ? null : cursor2.getString(3);
                            String string6 = cursor2.getString(4);
                            Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                            CustomerInvoiceForPayment customerInvoiceForPayment = new CustomerInvoiceForPayment(string3, string4, j, string5, string6, cursor2.getLong(5));
                            CloseableKt.closeFinally(cursorRawQuery2, null);
                            if (!Intrinsics.areEqual(customerInvoiceForPayment.getSaleType(), "CREDIT") || customerInvoiceForPayment.getCustomerId() == null) {
                                throw new IllegalArgumentException("\u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u0645\u062a\u0627\u062d \u0644\u0641\u0648\u0627\u062a\u064a\u0631 \u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u0622\u062c\u0644 \u0627\u0644\u0645\u062d\u062f\u062f\u0629 \u0644\u0632\u0628\u0648\u0646 \u0641\u0642\u0637".toString());
                            }
                            ExistingOperation existingOperationFindOperation = findOperation(writableDatabase, operationId);
                            if (existingOperationFindOperation != null) {
                                if (!Intrinsics.areEqual(existingOperationFindOperation.getType(), "CUSTOMER_CREDIT_COLLECTION") || !Intrinsics.areEqual(existingOperationFindOperation.getPayloadHash(), strHash)) {
                                    throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0645\u0633\u062a\u062e\u062f\u0645 \u0644\u0639\u0645\u0644\u064a\u0629 \u0623\u0648 \u0628\u064a\u0627\u0646\u0627\u062a \u0645\u062e\u062a\u0644\u0641\u0629".toString());
                                }
                                InvoicePaymentState invoicePaymentState = invoicePaymentState(writableDatabase, invoiceId, customerInvoiceForPayment.getTotalUsdCents(), customerInvoiceForPayment.getOriginalRateMilli());
                                writableDatabase.setTransactionSuccessful();
                                String reference = existingOperationFindOperation.getReference();
                                if (reference == null) {
                                    throw new IllegalStateException("\u0633\u062c\u0644 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0644\u0627 \u064a\u062d\u062a\u0648\u064a \u0645\u0631\u062c\u0639 \u0627\u0644\u062a\u062d\u0635\u064a\u0644".toString());
                                }
                                CustomerPaymentResult customerPaymentResult = new CustomerPaymentResult(reference, invoicePaymentState.getPaidUsdCents(), invoicePaymentState.getOutstandingUsdCents(), invoicePaymentState.getStatus(), true, invoicePaymentState.getOutstandingSypMinor());
                                writableDatabase.endTransaction();
                                return customerPaymentResult;
                            }
                            writableDatabase = writableDatabase;
                            if (!Intrinsics.areEqual(customerInvoiceForPayment.getStatus(), "POSTED") && !Intrinsics.areEqual(customerInvoiceForPayment.getStatus(), "PARTIALLY_PAID")) {
                                throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u062d\u0635\u064a\u0644 \u0641\u0627\u062a\u0648\u0631\u0629 \u063a\u064a\u0631 \u0645\u0639\u062a\u0645\u062f\u0629 \u0623\u0648 \u0645\u062f\u0641\u0648\u0639\u0629 \u0623\u0648 \u0645\u0644\u063a\u0627\u0629".toString());
                            }
                            if (customerInvoiceForPayment.getOriginalRateMilli() <= 0) {
                                throw new IllegalArgumentException("\u0647\u0630\u0647 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0642\u062f\u064a\u0645\u0629 \u0648\u0644\u0627 \u062a\u062d\u062a\u0648\u064a \u0633\u0639\u0631 \u0635\u0631\u0641 \u0645\u0648\u062b\u0648\u0642\u0627\u064b\u061b \u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u062d\u0635\u064a\u0644\u0647\u0627 \u0622\u0644\u064a\u0627\u064b \u0642\u0628\u0644 \u0645\u0631\u0627\u062c\u0639\u062a\u0647\u0627".toString());
                            }
                            long jCurrentRate = currentRate(writableDatabase);
                            if (jCurrentRate <= 0) {
                                throw new IllegalArgumentException("\u064a\u062c\u0628 \u062a\u0633\u062c\u064a\u0644 \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0642\u0628\u0644 \u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0646 \u0627\u0644\u0632\u0628\u0648\u0646".toString());
                            }
                            long jMax = Math.max(jCurrentRate, customerInvoiceForPayment.getOriginalRateMilli());
                            Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT name,account_type,currency_code,is_active FROM cash_accounts WHERE id=?", new String[]{cashAccountId});
                            try {
                                Cursor cursor3 = cursorRawQuery3;
                                if (!cursor3.moveToFirst()) {
                                    throw new IllegalArgumentException("\u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
                                }
                                if (cursor3.getInt(3) != 1) {
                                    throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u0641\u064a \u062d\u0633\u0627\u0628 \u0645\u0648\u0642\u0648\u0641".toString());
                                }
                                String string7 = cursor3.getString(0);
                                Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
                                String string8 = cursor3.getString(1);
                                Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
                                String string9 = cursor3.getString(2);
                                Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
                                CustomerAccountForPayment customerAccountForPayment = new CustomerAccountForPayment(string7, string8, string9);
                                CloseableKt.closeFinally(cursorRawQuery3, null);
                                if (!Intrinsics.areEqual(customerAccountForPayment.getCurrencyCode(), "SYP")) {
                                    throw new IllegalArgumentException("\u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0648\u0646 \u0627\u0644\u0632\u0628\u0627\u0626\u0646 \u0645\u062a\u0627\u062d \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629 \u0641\u0642\u0637".toString());
                                }
                                String accountType = customerAccountForPayment.getAccountType();
                                if (Intrinsics.areEqual(accountType, "CASH_BOX")) {
                                    Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT primary_cash_account_id FROM daily_cash_sessions WHERE operating_date=?", new String[]{operatingDate()});
                                    try {
                                        Cursor cursor4 = cursorRawQuery4;
                                        String string10 = cursor4.moveToFirst() ? cursor4.getString(0) : null;
                                        CloseableKt.closeFinally(cursorRawQuery4, null);
                                        if (string10 == null) {
                                            throw new IllegalArgumentException("\u0644\u0645 \u064a\u062a\u0645 \u062a\u0639\u064a\u064a\u0646 \u0627\u0644\u062f\u0631\u062c \u0627\u0644\u0646\u0642\u062f\u064a \u0627\u0644\u0631\u0626\u064a\u0633\u064a \u0644\u0647\u0630\u0627 \u0627\u0644\u064a\u0648\u0645. \u0627\u0637\u0644\u0628 \u0645\u0646 \u0627\u0644\u0645\u062f\u064a\u0631 \u062a\u0639\u064a\u064a\u0646 \u062f\u0631\u062c \u0627\u0644\u064a\u0648\u0645 \u0645\u0646 \u0642\u0633\u0645 \u0627\u0644\u0635\u0646\u0627\u062f\u064a\u0642 \u0648\u0627\u0644\u0645\u062d\u0627\u0641\u0638.".toString());
                                        }
                                        if (!Intrinsics.areEqual(string10, cashAccountId)) {
                                            throw new IllegalArgumentException("\u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u0627\u0644\u0646\u0642\u062f\u064a \u064a\u062c\u0628 \u0623\u0646 \u064a\u0630\u0647\u0628 \u0625\u0644\u0649 \u062f\u0631\u062c \u0627\u0644\u064a\u0648\u0645 \u0627\u0644\u0631\u0626\u064a\u0633\u064a\u060c \u0648\u0644\u064a\u0633 \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0623\u0648 \u0627\u0644\u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0645\u062f\u0648\u0631.".toString());
                                        }
                                    } catch (Throwable th6) {
                                        try {
                                            throw th6;
                                        } catch (Throwable th7) {
                                            CloseableKt.closeFinally(cursorRawQuery4, th6);
                                            throw th7;
                                        }
                                    }
                                } else if (!Intrinsics.areEqual(accountType, "WALLET")) {
                                    throw new IllegalArgumentException("\u064a\u062c\u0628 \u0627\u062e\u062a\u064a\u0627\u0631 \u0627\u0644\u062f\u0631\u062c \u0627\u0644\u064a\u0648\u0645\u064a \u0627\u0644\u0631\u0626\u064a\u0633\u064a \u0623\u0648 \u0645\u062d\u0641\u0638\u0629 \u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a\u0629 \u0646\u0634\u0637\u0629");
                                }
                                if (Intrinsics.areEqual(string, "SECONDARY")) {
                                    try {
                                        InvoicePaymentState invoicePaymentState2 = invoicePaymentState(writableDatabase, invoiceId, customerInvoiceForPayment.getTotalUsdCents(), customerInvoiceForPayment.getOriginalRateMilli());
                                        if (invoicePaymentState2.getOutstandingUsdCents() <= 0) {
                                            throw new IllegalArgumentException("\u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0645\u0633\u062f\u062f\u0629 \u0628\u0627\u0644\u0643\u0627\u0645\u0644".toString());
                                        }
                                        if (amountSypMinor > invoicePaymentState2.getOutstandingSypMinor()) {
                                            throw new IllegalArgumentException("\u0645\u0628\u0644\u063a \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u064a\u062a\u062c\u0627\u0648\u0632 \u0627\u0644\u0645\u062a\u0628\u0642\u064a \u0627\u0644\u0645\u0633\u062a\u062d\u0642 \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629".toString());
                                        }
                                        long jSypToUsdCents = CurrencyConverter.INSTANCE.sypToUsdCents(amountSypMinor, jMax);
                                        if (jSypToUsdCents <= 0 || jSypToUsdCents > invoicePaymentState2.getOutstandingUsdCents()) {
                                            throw new IllegalArgumentException("\u0645\u0628\u0644\u063a \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u0644\u0627 \u064a\u0642\u0627\u0628\u0644 \u0631\u0635\u064a\u062f\u0627\u064b \u0645\u0631\u062c\u0639\u064a\u0627\u064b \u0635\u0627\u0644\u062d\u0627\u064b".toString());
                                        }
                                        String strTimestamp = timestamp();
                                        String strOperatingDate = StoreTime.INSTANCE.operatingDate(writableDatabase, strTimestamp);
                                        String str = "pending-payment-" + operationId;
                                        JSONObject jSONObjectPut = new JSONObject().put("business_operation", "COLLECT_CUSTOMER_PAYMENT").put("invoice_id", invoiceId).put("payment_id", str).put("cash_transaction_id", "pending-cash-" + operationId).put("cash_account_id", cashAccountId).put("cash_account_type", customerAccountForPayment.getAccountType()).put("cash_account_currency_code", customerAccountForPayment.getCurrencyCode()).put("amount_minor", amountSypMinor).put("currency_code", "SYP").put("collection_rate_milli", jCurrentRate).put("effective_rate_milli", jMax).put("usd_reference_reduction_cents", jSypToUsdCents);
                                        if (obj == null) {
                                            obj = JSONObject.NULL;
                                        }
                                        JSONObject jSONObjectPut2 = jSONObjectPut.put("note", obj).put("occurred_at", strTimestamp).put("operating_date", strOperatingDate);
                                        Context context = this.context;
                                        Intrinsics.checkNotNullExpressionValue(context, "context");
                                        ?? syncProtocolRepository = new SyncProtocolRepository(context, null, 2, null);
                                        String string11 = jSONObjectPut2.toString();
                                        Intrinsics.checkNotNullExpressionValue(string11, "toString(...)");
                                        syncProtocolRepository.enqueueSecondaryCommand(writableDatabase, actorUserId, operationId, "COLLECT_CUSTOMER_PAYMENT", "CUSTOMER_PAYMENT", str, string11);
                                        writableDatabase.setTransactionSuccessful();
                                        CustomerPaymentResult customerPaymentResult2 = new CustomerPaymentResult(str, invoicePaymentState2.getPaidUsdCents(), invoicePaymentState2.getOutstandingUsdCents(), invoicePaymentState2.getStatus(), false, invoicePaymentState2.getOutstandingSypMinor());
                                        writableDatabase.endTransaction();
                                        return customerPaymentResult2;
                                    } catch (Throwable th8) {
                                        th = th8;
                                    }
                                } else {
                                    if (string != null && !Intrinsics.areEqual(string, "PRIMARY")) {
                                        throw new IllegalArgumentException("\u062f\u0648\u0631 \u0627\u0644\u062c\u0647\u0627\u0632 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d \u0644\u062a\u0646\u0641\u064a\u0630 \u062a\u062d\u0635\u064a\u0644 \u0627\u0644\u062f\u064a\u0648\u0646".toString());
                                    }
                                    String strTimestamp2 = timestamp();
                                    ?? r19 = writableDatabase;
                                    try {
                                        recordRevaluation(writableDatabase, invoiceId, customerInvoiceForPayment.getCustomerId(), customerInvoiceForPayment.getTotalUsdCents(), customerInvoiceForPayment.getOriginalRateMilli(), jCurrentRate, strTimestamp2);
                                        InvoicePaymentState invoicePaymentState3 = invoicePaymentState(r19, invoiceId, customerInvoiceForPayment.getTotalUsdCents(), customerInvoiceForPayment.getOriginalRateMilli());
                                        if (invoicePaymentState3.getOutstandingUsdCents() <= 0) {
                                            throw new IllegalArgumentException("\u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0645\u0633\u062f\u062f\u0629 \u0628\u0627\u0644\u0643\u0627\u0645\u0644".toString());
                                        }
                                        if (amountSypMinor > invoicePaymentState3.getOutstandingSypMinor()) {
                                            throw new IllegalArgumentException("\u0645\u0628\u0644\u063a \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u064a\u062a\u062c\u0627\u0648\u0632 \u0627\u0644\u0645\u062a\u0628\u0642\u064a \u0627\u0644\u0645\u0633\u062a\u062d\u0642 \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629".toString());
                                        }
                                        long jSypToUsdCents2 = CurrencyConverter.INSTANCE.sypToUsdCents(amountSypMinor, jMax);
                                        if (jSypToUsdCents2 <= 0) {
                                            throw new IllegalArgumentException("\u0645\u0628\u0644\u063a \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u0635\u063a\u064a\u0631 \u062c\u062f\u0627\u064b \u0648\u0644\u0627 \u064a\u0642\u0627\u0628\u0644 \u0648\u062d\u062f\u0629 \u0645\u0631\u062c\u0639\u064a\u0629 \u0628\u0627\u0644\u062f\u0648\u0644\u0627\u0631".toString());
                                        }
                                        if (jSypToUsdCents2 > invoicePaymentState3.getOutstandingUsdCents()) {
                                            throw new IllegalArgumentException("\u0645\u0628\u0644\u063a \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u064a\u062a\u062c\u0627\u0648\u0632 \u0627\u0644\u0645\u062a\u0628\u0642\u064a \u0627\u0644\u0645\u0631\u062c\u0639\u064a \u0639\u0644\u0649 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629".toString());
                                        }
                                        String string12 = UUID.randomUUID().toString();
                                        Intrinsics.checkNotNullExpressionValue(string12, "toString(...)");
                                        String string13 = UUID.randomUUID().toString();
                                        Intrinsics.checkNotNullExpressionValue(string13, "toString(...)");
                                        String str2 = Intrinsics.areEqual(customerAccountForPayment.getAccountType(), "WALLET") ? "ELECTRONIC_WALLET" : "PHYSICAL_CASH";
                                        ?? contentValues = new ContentValues();
                                        contentValues.put("id", string13);
                                        contentValues.put("account_id", cashAccountId);
                                        contentValues.put("direction", "IN");
                                        contentValues.put("amount_minor", Long.valueOf(amountSypMinor));
                                        contentValues.put("currency_code", "SYP");
                                        contentValues.put("exchange_rate_syp_per_usd_milli", Long.valueOf(jCurrentRate));
                                        if (StringsKt.isBlank(actorUserId)) {
                                            contentValues.putNull("created_by");
                                        } else {
                                            contentValues.put("created_by", actorUserId);
                                        }
                                        contentValues.put("transaction_type", "CUSTOMER_CREDIT_COLLECTION");
                                        contentValues.put("source_type", "CUSTOMER_CREDIT_COLLECTION");
                                        contentValues.put("source_id", invoiceId);
                                        contentValues.put("operation_id", operationId);
                                        if (obj == null) {
                                            obj = "\u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0646 " + customerInvoiceForPayment.getCustomerId() + " \u0639\u0646 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 " + customerInvoiceForPayment.getInvoiceNumber() + " \u2022 " + str2;
                                        }
                                        contentValues.put("description", obj);
                                        writableDatabase = r19;
                                        try {
                                            contentValues.put("operating_date", StoreTime.INSTANCE.operatingDate(writableDatabase, strTimestamp2));
                                            contentValues.put("created_at", strTimestamp2);
                                            writableDatabase.insertOrThrow("cash_transactions", null, contentValues);
                                            ContentValues contentValues2 = new ContentValues();
                                            contentValues2.put("id", string12);
                                            contentValues2.put("invoice_type", "SALE");
                                            contentValues2.put("invoice_id", invoiceId);
                                            contentValues2.put("cash_transaction_id", string13);
                                            contentValues2.put("amount_minor", Long.valueOf(amountSypMinor));
                                            contentValues2.put("currency_code", "SYP");
                                            contentValues2.put("exchange_rate_syp_per_usd_milli", Long.valueOf(jMax));
                                            contentValues2.put("created_at", strTimestamp2);
                                            writableDatabase.insertOrThrow("invoice_payments", null, contentValues2);
                                            writableDatabase.execSQL("UPDATE sales_invoices SET status=?,updated_at=? WHERE id=? AND status IN ('POSTED','PARTIALLY_PAID')", new String[]{RangesKt.coerceAtLeast((customerInvoiceForPayment.getTotalUsdCents() - MainActivity$$ExternalSyntheticBackport0.m(invoicePaymentState3.getPaidUsdCents(), jSypToUsdCents2)) - creditReductionUsdCents(writableDatabase, invoiceId), 0L) == 0 ? "PAID" : "PARTIALLY_PAID", strTimestamp2, invoiceId});
                                            Cursor cursorRawQuery5 = writableDatabase.rawQuery("SELECT changes()", null);
                                            try {
                                                Cursor cursor5 = cursorRawQuery5;
                                                if (cursor5.moveToFirst()) {
                                                    z = false;
                                                    try {
                                                        if (cursor5.getInt(0) == 1) {
                                                            z = true;
                                                        }
                                                    } catch (Throwable th9) {
                                                        th3 = th9;
                                                        try {
                                                            throw th3;
                                                        } catch (Throwable th10) {
                                                            CloseableKt.closeFinally(cursorRawQuery5, th3);
                                                            throw th10;
                                                        }
                                                    }
                                                } else {
                                                    z = false;
                                                }
                                                CloseableKt.closeFinally(cursorRawQuery5, null);
                                                if (!z) {
                                                    throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062d\u062f\u064a\u062b \u062d\u0627\u0644\u0629 \u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0628\u064a\u0639".toString());
                                                }
                                                ContentValues contentValues3 = new ContentValues();
                                                contentValues3.put("id", UUID.randomUUID().toString());
                                                if (StringsKt.isBlank(actorUserId)) {
                                                    contentValues3.putNull("user_id");
                                                } else {
                                                    contentValues3.put("user_id", actorUserId);
                                                }
                                                contentValues3.put("action", "CUSTOMER_CREDIT_COLLECTION");
                                                contentValues3.put("entity_type", "CUSTOMER_CREDIT_INVOICE");
                                                contentValues3.put("entity_id", invoiceId);
                                                contentValues3.put("before_json", "{\"outstanding_usd_cents\":" + invoicePaymentState3.getOutstandingUsdCents() + ",\"outstanding_syp_minor\":" + invoicePaymentState3.getOutstandingSypMinor() + "}");
                                                contentValues3.put("after_json", "{\"payment_id\":\"" + string12 + "\",\"cash_transaction_id\":\"" + string13 + "\",\"amount_syp_minor\":" + amountSypMinor + ",\"channel\":\"" + str2 + "\",\"account_id\":\"" + cashAccountId + "\",\"collection_rate_milli\":" + jCurrentRate + ",\"invoice_rate_milli\":" + customerInvoiceForPayment.getOriginalRateMilli() + ",\"effective_rate_milli\":" + jMax + ",\"usd_reference_reduction_cents\":" + jSypToUsdCents2 + "}");
                                                contentValues3.put("created_at", strTimestamp2);
                                                writableDatabase.insertOrThrow("audit_log", null, contentValues3);
                                                r19 = writableDatabase;
                                                registerOperation(writableDatabase, operationId, "CUSTOMER_CREDIT_COLLECTION", strHash, string12, strTimestamp2);
                                                Context context2 = this.context;
                                                Intrinsics.checkNotNullExpressionValue(context2, "context");
                                                ?? syncProtocolRepository2 = new SyncProtocolRepository(context2, null, 2, null);
                                                String string14 = new JSONObject().put("invoice_id", invoiceId).put("payment_id", string12).put("cash_transaction_id", string13).put("cash_account_id", cashAccountId).put("amount_minor", amountSypMinor).put("currency_code", "SYP").put("collection_rate_milli", jCurrentRate).put("effective_rate_milli", jMax).put("usd_reference_reduction_cents", jSypToUsdCents2).put("created_at", strTimestamp2).toString();
                                                Intrinsics.checkNotNullExpressionValue(string14, "toString(...)");
                                                syncProtocolRepository2.enqueueLocalOperation(r19, actorUserId, operationId, "CUSTOMER_CREDIT_COLLECTION", "CUSTOMER_PAYMENT", string12, "CREATE", string14);
                                                InvoicePaymentState invoicePaymentState4 = invoicePaymentState(r19, invoiceId, customerInvoiceForPayment.getTotalUsdCents(), customerInvoiceForPayment.getOriginalRateMilli());
                                                r19.setTransactionSuccessful();
                                                CustomerPaymentResult customerPaymentResult3 = new CustomerPaymentResult(string12, invoicePaymentState4.getPaidUsdCents(), invoicePaymentState4.getOutstandingUsdCents(), invoicePaymentState4.getStatus(), false, invoicePaymentState4.getOutstandingSypMinor());
                                                r19.endTransaction();
                                                return customerPaymentResult3;
                                            } catch (Throwable th11) {
                                                th3 = th11;
                                            }
                                        } catch (Throwable th12) {
                                            th = th12;
                                            writableDatabase = writableDatabase;
                                        }
                                    } catch (Throwable th13) {
                                        th = th13;
                                        writableDatabase = r19;
                                    }
                                }
                            } catch (Throwable th14) {
                                try {
                                    throw th14;
                                } catch (Throwable th15) {
                                    CloseableKt.closeFinally(cursorRawQuery3, th14);
                                    throw th15;
                                }
                            }
                            writableDatabase.endTransaction();
                            throw th;
                        }
                        try {
                            throw new IllegalArgumentException("\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0628\u064a\u0639 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629".toString());
                        } catch (Throwable th16) {
                            th = th16;
                        }
                    } catch (Throwable th17) {
                        th = th17;
                    }
                    Throwable th18 = th;
                    try {
                        throw th18;
                    } catch (Throwable th19) {
                        CloseableKt.closeFinally(cursorRawQuery2, th18);
                        throw th19;
                    }
                } catch (Throwable th20) {
                    th = th20;
                }
            } catch (Throwable th21) {
                th = th21;
            }
        } catch (Throwable th22) {
            th = th22;
            writableDatabase = writableDatabase;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void applyRevaluations(String customerId) {
        Context context = this.context;
        Intrinsics.checkNotNullExpressionValue(context, "context");
        if (new SyncProtocolRepository(context, null, 2, null).isConfiguredSecondary()) {
            return;
        }
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        writableDatabase.beginTransaction();
        try {
            long jCurrentRate = currentRate(writableDatabase);
            if (jCurrentRate > 0) {
                ArrayList<InvoiceForRevaluation> arrayList = new ArrayList();
                Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT id,total_usd_cents,customer_id,exchange_rate_syp_per_usd_milli\n                       FROM sales_invoices WHERE customer_id=? AND sale_type='CREDIT'\n                         AND status IN ('POSTED','PARTIALLY_PAID')", new String[]{customerId});
                try {
                    Cursor cursor = cursorRawQuery;
                    while (cursor.moveToNext()) {
                        String string = cursor.getString(0);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                        long j = cursor.getLong(1);
                        String string2 = cursor.getString(2);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                        arrayList.add(new InvoiceForRevaluation(string, j, string2, cursor.getLong(3)));
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursorRawQuery, null);
                    String strTimestamp = timestamp();
                    for (InvoiceForRevaluation invoiceForRevaluation : arrayList) {
                        if (invoiceForRevaluation.getOriginalRateMilli() > 0) {
                            recordRevaluation(writableDatabase, invoiceForRevaluation.getId(), invoiceForRevaluation.getCustomerId(), invoiceForRevaluation.getTotalUsdCents(), invoiceForRevaluation.getOriginalRateMilli(), jCurrentRate, strTimestamp);
                        }
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
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
        } catch (Throwable th3) {
            writableDatabase.endTransaction();
            throw th3;
        }
    }

    private final void recordRevaluation(SQLiteDatabase db, String invoiceId, String customerId, long totalUsdCents, long originalRate, long currentRate, String now) throws IOException {
        if (originalRate <= 0 || currentRate <= 0) {
            return;
        }
        long jCoerceAtLeast = RangesKt.coerceAtLeast((totalUsdCents - paidUsdCents(db, invoiceId)) - creditReductionUsdCents(db, invoiceId), 0L);
        if (jCoerceAtLeast == 0) {
            return;
        }
        long jMax = Math.max(originalRate, currentRate);
        long jUsdCentsToSyp = CurrencyConverter.INSTANCE.usdCentsToSyp(jCoerceAtLeast, jMax);
        long jUsdCentsToSyp2 = CurrencyConverter.INSTANCE.usdCentsToSyp(jCoerceAtLeast, originalRate);
        Cursor cursorRawQuery = db.rawQuery("SELECT new_syp_minor FROM customer_credit_revaluations\n               WHERE invoice_id=? AND outstanding_usd_cents=? ORDER BY created_at DESC LIMIT 1", new String[]{invoiceId, String.valueOf(jCoerceAtLeast)});
        try {
            Cursor cursor = cursorRawQuery;
            if (cursor.moveToFirst()) {
                jUsdCentsToSyp2 = cursor.getLong(0);
            }
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (jUsdCentsToSyp == jUsdCentsToSyp2) {
                return;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", UUID.randomUUID().toString());
            contentValues.put("invoice_id", invoiceId);
            contentValues.put("customer_id", customerId);
            contentValues.put("outstanding_usd_cents", Long.valueOf(jCoerceAtLeast));
            contentValues.put("previous_syp_minor", Long.valueOf(jUsdCentsToSyp2));
            contentValues.put("new_syp_minor", Long.valueOf(jUsdCentsToSyp));
            contentValues.put("applied_rate_syp_per_usd_milli", Long.valueOf(jMax));
            contentValues.put("calculation_basis", "unpaid USD reference \u00d7 max(current rate, original invoice rate); paid allocations excluded");
            contentValues.put("operation_id", UUID.randomUUID().toString());
            contentValues.put("created_at", now);
            db.insertOrThrow("customer_credit_revaluations", null, contentValues);
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("id", UUID.randomUUID().toString());
            contentValues2.putNull("user_id");
            contentValues2.put("action", "CUSTOMER_CREDIT_FX_REVALUATION");
            contentValues2.put("entity_type", "CUSTOMER_CREDIT_INVOICE");
            contentValues2.put("entity_id", invoiceId);
            contentValues2.put("before_json", "{\"outstanding_usd_cents\":" + jCoerceAtLeast + ",\"syp_value\":" + jUsdCentsToSyp2 + "}");
            contentValues2.put("after_json", "{\"outstanding_usd_cents\":" + jCoerceAtLeast + ",\"syp_value\":" + jUsdCentsToSyp + ",\"applied_rate_milli\":" + jMax + "}");
            contentValues2.put("created_at", now);
            db.insertOrThrow("audit_log", null, contentValues2);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final long creditReductionUsdCents(SQLiteDatabase db, String invoiceId) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT COALESCE(SUM(credit_reduction_usd_cents),0)\n               FROM returns WHERE original_invoice_type='SALE'\n                 AND original_invoice_id=? AND status='POSTED'", new String[]{invoiceId});
        try {
            Cursor cursor = cursorRawQuery;
            cursor.moveToFirst();
            long j = cursor.getLong(0);
            CloseableKt.closeFinally(cursorRawQuery, null);
            return j;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final long paidSypMinor(SQLiteDatabase db, String invoiceId, long originalRate) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT amount_minor,currency_code,exchange_rate_syp_per_usd_milli\n               FROM invoice_payments WHERE invoice_type='SALE' AND invoice_id=?", new String[]{invoiceId});
        try {
            Cursor cursor = cursorRawQuery;
            long jM = 0;
            while (cursor.moveToNext()) {
                long jUsdCentsToSyp = cursor.getLong(0);
                long j = cursor.isNull(2) ? originalRate : cursor.getLong(2);
                String string = cursor.getString(1);
                if (!Intrinsics.areEqual(string, "SYP")) {
                    if (!Intrinsics.areEqual(string, "USD")) {
                        throw new IllegalStateException("\u0639\u0645\u0644\u0629 \u062f\u0641\u0639\u0629 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u0629".toString());
                    }
                    if (j <= 0) {
                        throw new IllegalArgumentException("\u062f\u0641\u0639\u0629 \u0642\u062f\u064a\u0645\u0629 \u0628\u0644\u0627 \u0633\u0639\u0631 \u0635\u0631\u0641 \u0645\u0648\u062b\u0648\u0642".toString());
                    }
                    jUsdCentsToSyp = CurrencyConverter.INSTANCE.usdCentsToSyp(jUsdCentsToSyp, j);
                }
                jM = MainActivity$$ExternalSyntheticBackport0.m(jM, jUsdCentsToSyp);
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

    private final long paidUsdCents(SQLiteDatabase db, String invoiceId) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT amount_minor,currency_code,exchange_rate_syp_per_usd_milli\n               FROM invoice_payments WHERE invoice_type='SALE' AND invoice_id=?", new String[]{invoiceId});
        try {
            Cursor cursor = cursorRawQuery;
            long jM = 0;
            while (cursor.moveToNext()) {
                long jSypToUsdCents = cursor.getLong(0);
                long j = cursor.isNull(2) ? 0L : cursor.getLong(2);
                String string = cursor.getString(1);
                if (!Intrinsics.areEqual(string, "USD")) {
                    if (!Intrinsics.areEqual(string, "SYP")) {
                        throw new IllegalStateException("\u0639\u0645\u0644\u0629 \u062f\u0641\u0639\u0629 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u0629".toString());
                    }
                    if (j <= 0) {
                        throw new IllegalArgumentException("\u062f\u0641\u0639\u0629 \u0644\u064a\u0631\u0629 \u0642\u062f\u064a\u0645\u0629 \u0628\u0644\u0627 \u0633\u0639\u0631 \u0635\u0631\u0641 \u0645\u062d\u0641\u0648\u0638".toString());
                    }
                    jSypToUsdCents = CurrencyConverter.INSTANCE.sypToUsdCents(jSypToUsdCents, j);
                }
                jM = MainActivity$$ExternalSyntheticBackport0.m(jM, jSypToUsdCents);
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

    private final InvoicePaymentState invoicePaymentState(SQLiteDatabase db, String invoiceId, long totalUsdCents, long originalRate) throws IOException {
        String str;
        long jPaidUsdCents = paidUsdCents(db, invoiceId);
        long jCoerceAtLeast = RangesKt.coerceAtLeast((totalUsdCents - jPaidUsdCents) - creditReductionUsdCents(db, invoiceId), 0L);
        long jMax = originalRate > 0 ? Math.max(currentRate(db), originalRate) : 0L;
        long jUsdCentsToSyp = jMax > 0 ? CurrencyConverter.INSTANCE.usdCentsToSyp(jCoerceAtLeast, jMax) : 0L;
        if (jCoerceAtLeast == 0) {
            str = "PAID";
        } else if (jPaidUsdCents > 0) {
            str = "PARTIALLY_PAID";
        } else {
            str = "UNPAID";
        }
        return new InvoicePaymentState(jPaidUsdCents, jCoerceAtLeast, jUsdCentsToSyp, str);
    }

    private final long currentRate(SQLiteDatabase db) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT rate_syp_per_usd_milli FROM exchange_rates ORDER BY effective_at DESC,created_at DESC LIMIT 1", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            long j = cursor.moveToFirst() ? cursor.getLong(0) : 0L;
            CloseableKt.closeFinally(cursorRawQuery, null);
            return j;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final String operatingDate() {
        StoreTime storeTime = StoreTime.INSTANCE;
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        return StoreTime.today$default(storeTime, readableDatabase, null, 2, null);
    }

    private final String timestamp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String str = simpleDateFormat.format(new Date());
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    /* JADX INFO: compiled from: CustomerPaymentRepository.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0005H\u00c6\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r\u00a8\u0006\u001b"}, d2 = {"Lcom/hazem/dailystore/data/CustomerPaymentRepository$InvoiceForRevaluation;", "", "id", "", "totalUsdCents", "", "customerId", "originalRateMilli", "<init>", "(Ljava/lang/String;JLjava/lang/String;J)V", "getId", "()Ljava/lang/String;", "getTotalUsdCents", "()J", "getCustomerId", "getOriginalRateMilli", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class InvoiceForRevaluation {
        private final String customerId;
        private final String id;
        private final long originalRateMilli;
        private final long totalUsdCents;

        public static /* synthetic */ InvoiceForRevaluation copy$default(InvoiceForRevaluation invoiceForRevaluation, String str, long j, String str2, long j2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = invoiceForRevaluation.id;
            }
            if ((i & 2) != 0) {
                j = invoiceForRevaluation.totalUsdCents;
            }
            long j3 = j;
            if ((i & 4) != 0) {
                str2 = invoiceForRevaluation.customerId;
            }
            String str3 = str2;
            if ((i & 8) != 0) {
                j2 = invoiceForRevaluation.originalRateMilli;
            }
            return invoiceForRevaluation.copy(str, j3, str3, j2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getTotalUsdCents() {
            return this.totalUsdCents;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getCustomerId() {
            return this.customerId;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final long getOriginalRateMilli() {
            return this.originalRateMilli;
        }

        public final InvoiceForRevaluation copy(String id, long totalUsdCents, String customerId, long originalRateMilli) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(customerId, "customerId");
            return new InvoiceForRevaluation(id, totalUsdCents, customerId, originalRateMilli);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof InvoiceForRevaluation)) {
                return false;
            }
            InvoiceForRevaluation invoiceForRevaluation = (InvoiceForRevaluation) other;
            return Intrinsics.areEqual(this.id, invoiceForRevaluation.id) && this.totalUsdCents == invoiceForRevaluation.totalUsdCents && Intrinsics.areEqual(this.customerId, invoiceForRevaluation.customerId) && this.originalRateMilli == invoiceForRevaluation.originalRateMilli;
        }

        public int hashCode() {
            return (((((this.id.hashCode() * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.totalUsdCents)) * 31) + this.customerId.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.originalRateMilli);
        }

        public String toString() {
            return "InvoiceForRevaluation(id=" + this.id + ", totalUsdCents=" + this.totalUsdCents + ", customerId=" + this.customerId + ", originalRateMilli=" + this.originalRateMilli + ")";
        }

        public InvoiceForRevaluation(String id, long j, String customerId, long j2) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(customerId, "customerId");
            this.id = id;
            this.totalUsdCents = j;
            this.customerId = customerId;
            this.originalRateMilli = j2;
        }

        public final String getCustomerId() {
            return this.customerId;
        }

        public final String getId() {
            return this.id;
        }

        public final long getOriginalRateMilli() {
            return this.originalRateMilli;
        }

        public final long getTotalUsdCents() {
            return this.totalUsdCents;
        }
    }

    /* JADX INFO: compiled from: CustomerPaymentRepository.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0006\u00a2\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0006H\u00c6\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0006H\u00c6\u0003JG\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001J\t\u0010 \u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010\u00a8\u0006!"}, d2 = {"Lcom/hazem/dailystore/data/CustomerPaymentRepository$CustomerInvoiceForPayment;", "", NotificationCompat.CATEGORY_STATUS, "", "saleType", "totalUsdCents", "", "customerId", "invoiceNumber", "originalRateMilli", "<init>", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;J)V", "getStatus", "()Ljava/lang/String;", "getSaleType", "getTotalUsdCents", "()J", "getCustomerId", "getInvoiceNumber", "getOriginalRateMilli", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class CustomerInvoiceForPayment {
        private final String customerId;
        private final String invoiceNumber;
        private final long originalRateMilli;
        private final String saleType;
        private final String status;
        private final long totalUsdCents;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getStatus() {
            return this.status;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSaleType() {
            return this.saleType;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final long getTotalUsdCents() {
            return this.totalUsdCents;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getCustomerId() {
            return this.customerId;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getInvoiceNumber() {
            return this.invoiceNumber;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final long getOriginalRateMilli() {
            return this.originalRateMilli;
        }

        public final CustomerInvoiceForPayment copy(String status, String saleType, long totalUsdCents, String customerId, String invoiceNumber, long originalRateMilli) {
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(saleType, "saleType");
            Intrinsics.checkNotNullParameter(invoiceNumber, "invoiceNumber");
            return new CustomerInvoiceForPayment(status, saleType, totalUsdCents, customerId, invoiceNumber, originalRateMilli);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CustomerInvoiceForPayment)) {
                return false;
            }
            CustomerInvoiceForPayment customerInvoiceForPayment = (CustomerInvoiceForPayment) other;
            return Intrinsics.areEqual(this.status, customerInvoiceForPayment.status) && Intrinsics.areEqual(this.saleType, customerInvoiceForPayment.saleType) && this.totalUsdCents == customerInvoiceForPayment.totalUsdCents && Intrinsics.areEqual(this.customerId, customerInvoiceForPayment.customerId) && Intrinsics.areEqual(this.invoiceNumber, customerInvoiceForPayment.invoiceNumber) && this.originalRateMilli == customerInvoiceForPayment.originalRateMilli;
        }

        public int hashCode() {
            int iHashCode = ((((this.status.hashCode() * 31) + this.saleType.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.totalUsdCents)) * 31;
            String str = this.customerId;
            return ((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.invoiceNumber.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.originalRateMilli);
        }

        public String toString() {
            return "CustomerInvoiceForPayment(status=" + this.status + ", saleType=" + this.saleType + ", totalUsdCents=" + this.totalUsdCents + ", customerId=" + this.customerId + ", invoiceNumber=" + this.invoiceNumber + ", originalRateMilli=" + this.originalRateMilli + ")";
        }

        public CustomerInvoiceForPayment(String status, String saleType, long j, String str, String invoiceNumber, long j2) {
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(saleType, "saleType");
            Intrinsics.checkNotNullParameter(invoiceNumber, "invoiceNumber");
            this.status = status;
            this.saleType = saleType;
            this.totalUsdCents = j;
            this.customerId = str;
            this.invoiceNumber = invoiceNumber;
            this.originalRateMilli = j2;
        }

        public final String getSaleType() {
            return this.saleType;
        }

        public final String getStatus() {
            return this.status;
        }

        public final long getTotalUsdCents() {
            return this.totalUsdCents;
        }

        public final String getCustomerId() {
            return this.customerId;
        }

        public final String getInvoiceNumber() {
            return this.invoiceNumber;
        }

        public final long getOriginalRateMilli() {
            return this.originalRateMilli;
        }
    }

    /* JADX INFO: compiled from: CustomerPaymentRepository.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t\u00a8\u0006\u0016"}, d2 = {"Lcom/hazem/dailystore/data/CustomerPaymentRepository$CustomerAccountForPayment;", "", "name", "", "accountType", "currencyCode", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getAccountType", "getCurrencyCode", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class CustomerAccountForPayment {
        private final String accountType;
        private final String currencyCode;
        private final String name;

        public static /* synthetic */ CustomerAccountForPayment copy$default(CustomerAccountForPayment customerAccountForPayment, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = customerAccountForPayment.name;
            }
            if ((i & 2) != 0) {
                str2 = customerAccountForPayment.accountType;
            }
            if ((i & 4) != 0) {
                str3 = customerAccountForPayment.currencyCode;
            }
            return customerAccountForPayment.copy(str, str2, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getAccountType() {
            return this.accountType;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getCurrencyCode() {
            return this.currencyCode;
        }

        public final CustomerAccountForPayment copy(String name, String accountType, String currencyCode) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(accountType, "accountType");
            Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
            return new CustomerAccountForPayment(name, accountType, currencyCode);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CustomerAccountForPayment)) {
                return false;
            }
            CustomerAccountForPayment customerAccountForPayment = (CustomerAccountForPayment) other;
            return Intrinsics.areEqual(this.name, customerAccountForPayment.name) && Intrinsics.areEqual(this.accountType, customerAccountForPayment.accountType) && Intrinsics.areEqual(this.currencyCode, customerAccountForPayment.currencyCode);
        }

        public int hashCode() {
            return (((this.name.hashCode() * 31) + this.accountType.hashCode()) * 31) + this.currencyCode.hashCode();
        }

        public String toString() {
            return "CustomerAccountForPayment(name=" + this.name + ", accountType=" + this.accountType + ", currencyCode=" + this.currencyCode + ")";
        }

        public CustomerAccountForPayment(String name, String accountType, String currencyCode) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(accountType, "accountType");
            Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
            this.name = name;
            this.accountType = accountType;
            this.currencyCode = currencyCode;
        }

        public final String getAccountType() {
            return this.accountType;
        }

        public final String getCurrencyCode() {
            return this.currencyCode;
        }

        public final String getName() {
            return this.name;
        }
    }

    /* JADX INFO: compiled from: CustomerPaymentRepository.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0007H\u00c6\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u0007H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001b"}, d2 = {"Lcom/hazem/dailystore/data/CustomerPaymentRepository$InvoicePaymentState;", "", "paidUsdCents", "", "outstandingUsdCents", "outstandingSypMinor", NotificationCompat.CATEGORY_STATUS, "", "<init>", "(JJJLjava/lang/String;)V", "getPaidUsdCents", "()J", "getOutstandingUsdCents", "getOutstandingSypMinor", "getStatus", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class InvoicePaymentState {
        private final long outstandingSypMinor;
        private final long outstandingUsdCents;
        private final long paidUsdCents;
        private final String status;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getPaidUsdCents() {
            return this.paidUsdCents;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getOutstandingUsdCents() {
            return this.outstandingUsdCents;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final long getOutstandingSypMinor() {
            return this.outstandingSypMinor;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getStatus() {
            return this.status;
        }

        public final InvoicePaymentState copy(long paidUsdCents, long outstandingUsdCents, long outstandingSypMinor, String status) {
            Intrinsics.checkNotNullParameter(status, "status");
            return new InvoicePaymentState(paidUsdCents, outstandingUsdCents, outstandingSypMinor, status);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof InvoicePaymentState)) {
                return false;
            }
            InvoicePaymentState invoicePaymentState = (InvoicePaymentState) other;
            return this.paidUsdCents == invoicePaymentState.paidUsdCents && this.outstandingUsdCents == invoicePaymentState.outstandingUsdCents && this.outstandingSypMinor == invoicePaymentState.outstandingSypMinor && Intrinsics.areEqual(this.status, invoicePaymentState.status);
        }

        public int hashCode() {
            return (((((MainActivity$$ExternalSyntheticBackport0.m(this.paidUsdCents) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.outstandingUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.outstandingSypMinor)) * 31) + this.status.hashCode();
        }

        public String toString() {
            return "InvoicePaymentState(paidUsdCents=" + this.paidUsdCents + ", outstandingUsdCents=" + this.outstandingUsdCents + ", outstandingSypMinor=" + this.outstandingSypMinor + ", status=" + this.status + ")";
        }

        public InvoicePaymentState(long j, long j2, long j3, String status) {
            Intrinsics.checkNotNullParameter(status, "status");
            this.paidUsdCents = j;
            this.outstandingUsdCents = j2;
            this.outstandingSypMinor = j3;
            this.status = status;
        }

        public final long getOutstandingSypMinor() {
            return this.outstandingSypMinor;
        }

        public final long getOutstandingUsdCents() {
            return this.outstandingUsdCents;
        }

        public final long getPaidUsdCents() {
            return this.paidUsdCents;
        }

        public final String getStatus() {
            return this.status;
        }
    }

    /* JADX INFO: compiled from: CustomerPaymentRepository.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J)\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t\u00a8\u0006\u0016"}, d2 = {"Lcom/hazem/dailystore/data/CustomerPaymentRepository$ExistingOperation;", "", "type", "", "payloadHash", "reference", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getType", "()Ljava/lang/String;", "getPayloadHash", "getReference", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class ExistingOperation {
        private final String payloadHash;
        private final String reference;
        private final String type;

        public static /* synthetic */ ExistingOperation copy$default(ExistingOperation existingOperation, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = existingOperation.type;
            }
            if ((i & 2) != 0) {
                str2 = existingOperation.payloadHash;
            }
            if ((i & 4) != 0) {
                str3 = existingOperation.reference;
            }
            return existingOperation.copy(str, str2, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getPayloadHash() {
            return this.payloadHash;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getReference() {
            return this.reference;
        }

        public final ExistingOperation copy(String type, String payloadHash, String reference) {
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(payloadHash, "payloadHash");
            return new ExistingOperation(type, payloadHash, reference);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ExistingOperation)) {
                return false;
            }
            ExistingOperation existingOperation = (ExistingOperation) other;
            return Intrinsics.areEqual(this.type, existingOperation.type) && Intrinsics.areEqual(this.payloadHash, existingOperation.payloadHash) && Intrinsics.areEqual(this.reference, existingOperation.reference);
        }

        public int hashCode() {
            int iHashCode = ((this.type.hashCode() * 31) + this.payloadHash.hashCode()) * 31;
            String str = this.reference;
            return iHashCode + (str == null ? 0 : str.hashCode());
        }

        public String toString() {
            return "ExistingOperation(type=" + this.type + ", payloadHash=" + this.payloadHash + ", reference=" + this.reference + ")";
        }

        public ExistingOperation(String type, String payloadHash, String str) {
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(payloadHash, "payloadHash");
            this.type = type;
            this.payloadHash = payloadHash;
            this.reference = str;
        }

        public final String getPayloadHash() {
            return this.payloadHash;
        }

        public final String getReference() {
            return this.reference;
        }

        public final String getType() {
            return this.type;
        }
    }

    private final ExistingOperation findOperation(SQLiteDatabase db, String id) throws IOException {
        ExistingOperation existingOperation;
        Cursor cursorRawQuery = db.rawQuery("SELECT operation_type,payload_hash,result_reference FROM operation_registry WHERE operation_id=?", new String[]{id});
        try {
            Cursor cursor = cursorRawQuery;
            if (cursor.moveToFirst()) {
                String string = cursor.getString(0);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String string2 = cursor.getString(1);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                existingOperation = new ExistingOperation(string, string2, cursor.isNull(2) ? null : cursor.getString(2));
            } else {
                existingOperation = null;
            }
            CloseableKt.closeFinally(cursorRawQuery, null);
            return existingOperation;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final void registerOperation(SQLiteDatabase db, String id, String type, String payloadHash, String reference, String now) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("operation_id", id);
        contentValues.put("operation_type", type);
        contentValues.put("payload_hash", payloadHash);
        contentValues.put(NotificationCompat.CATEGORY_STATUS, "COMPLETED");
        contentValues.put("result_reference", reference);
        contentValues.put("created_at", now);
        contentValues.put("completed_at", now);
        db.insertOrThrow("operation_registry", null, contentValues);
    }

    private final String hash(String value) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = value.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrDigest = messageDigest.digest(bytes);
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
        return ArraysKt.joinToString$default(bArrDigest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.hazem.dailystore.data.CustomerPaymentRepository$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CustomerPaymentRepository.hash$lambda$58(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence hash$lambda$58(byte b) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }
}
