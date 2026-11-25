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
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import net.zetetic.database.sqlcipher.SQLiteDatabase;
import org.json.JSONObject;

/* JADX INFO: compiled from: PurchasePaymentRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001:\u0006>?@ABCB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eJ\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u000bJ\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000b2\u0006\u0010\u0013\u001a\u00020\u000eJ:\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u000eJ8\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u001a2\b\u0010!\u001a\u0004\u0018\u00010\u000eJ\u001c\u0010\"\u001a\u00020\u001a2\n\u0010#\u001a\u00060$j\u0002`%2\u0006\u0010&\u001a\u00020\u000eH\u0002J\u0010\u0010'\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u000eH\u0002J$\u0010)\u001a\u00020\u001a2\n\u0010#\u001a\u00060$j\u0002`%2\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020\u001aH\u0002J\u001c\u0010+\u001a\u00020\u001a2\n\u0010#\u001a\u00060$j\u0002`%2\u0006\u0010\u0013\u001a\u00020\u000eH\u0002J\u001c\u0010,\u001a\u00020\u001a2\n\u0010#\u001a\u00060$j\u0002`%2\u0006\u0010\u0013\u001a\u00020\u000eH\u0002J,\u0010-\u001a\u00020.2\n\u0010#\u001a\u00060$j\u0002`%2\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010/\u001a\u00020\u001a2\u0006\u00100\u001a\u00020\u001aH\u0002J$\u0010\"\u001a\u00020\u001a2\n\u0010#\u001a\u00060$j\u0002`%2\u0006\u00101\u001a\u00020\u000e2\u0006\u00102\u001a\u00020\u001aH\u0002J\u0014\u00103\u001a\u00020\u001a2\n\u0010#\u001a\u00060$j\u0002`%H\u0002J\u001e\u00104\u001a\u0004\u0018\u0001052\n\u0010#\u001a\u00060$j\u0002`%2\u0006\u00101\u001a\u00020\u000eH\u0002J<\u00106\u001a\u0002072\n\u0010#\u001a\u00060$j\u0002`%2\u0006\u00101\u001a\u00020\u000e2\u0006\u00108\u001a\u00020\u000e2\u0006\u00109\u001a\u00020\u000e2\u0006\u0010:\u001a\u00020\u000e2\u0006\u0010;\u001a\u00020\u000eH\u0002J\u0010\u0010<\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u000eH\u0002J\b\u0010=\u001a\u00020\u000eH\u0002R\u0018\u0010\u0002\u001a\n \u0006*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006D"}, d2 = {"Lcom/hazem/dailystore/data/PurchasePaymentRepository;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "kotlin.jvm.PlatformType", "Landroid/content/Context;", "database", "Lcom/hazem/dailystore/data/AppDatabase;", "listPurchaseInvoices", "", "Lcom/hazem/dailystore/data/PurchaseInvoicePaymentSummary;", "supplierId", "", "listSupplierSummaries", "Lcom/hazem/dailystore/data/SupplierFinancialSummary;", "listPayments", "Lcom/hazem/dailystore/data/PurchasePaymentRecord;", "invoiceId", "addPayment", "Lcom/hazem/dailystore/data/PurchasePaymentResult;", "actorUserId", "operationId", "cashAccountId", "amountMinor", "", "note", "editPayment", "actorRole", "paymentId", "newCashAccountId", "newAmountMinor", "newNote", "accountBalance", "db", "Lnet/zetetic/database/sqlcipher/SQLiteDatabase;", "Lcom/hazem/dailystore/data/AppSqliteDatabase;", "accountId", "jsonString", "value", "paidSypMinor", "invoiceRateMilli", "supplierReturnCreditSyp", "paidUsdCentsLegacy", "invoicePaymentStateSyp", "Lcom/hazem/dailystore/data/PurchasePaymentRepository$SypPaymentState;", "totalUsdCents", "rate", "id", "opening", "currentRate", "findOperation", "Lcom/hazem/dailystore/data/PurchasePaymentRepository$ExistingOperation;", "registerOperation", "", "type", "payloadHash", "reference", "now", "hash", "timestamp", "ExistingSupplierPayment", "SupplierPaymentAccount", "InvoiceForPayment", "SypPaymentState", "AccountForPayment", "ExistingOperation", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class PurchasePaymentRepository {
    private final Context context;
    private final AppDatabase database;

    public PurchasePaymentRepository(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context.getApplicationContext();
        this.database = AppDatabase.INSTANCE.getInstance(context);
    }

    public static /* synthetic */ List listPurchaseInvoices$default(PurchasePaymentRepository purchasePaymentRepository, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        return purchasePaymentRepository.listPurchaseInvoices(str);
    }

    /* JADX WARN: Code duplicated, block: B:63:0x012e  */
    /* JADX WARN: Multi-variable type inference failed */
    public final List<PurchaseInvoicePaymentSummary> listPurchaseInvoices(String supplierId) throws IOException {
        Throwable th;
        Cursor cursor;
        long jPaidSypMinor;
        long jSupplierReturnCreditSyp;
        long jPaidUsdCentsLegacy;
        long jCoerceAtLeast;
        String str;
        String str2;
        PurchasePaymentRepository purchasePaymentRepository = this;
        String str3 = "getString(...)";
        String str4 = "DRAFT";
        String str5 = "CANCELLED";
        ArrayList arrayList = new ArrayList();
        String str6 = supplierId == null ? "" : "WHERE i.supplier_id=?";
        int i = 0;
        String[] strArr = supplierId == null ? null : new String[]{supplierId};
        Cursor cursorRawQuery = purchasePaymentRepository.database.getReadableDatabase().rawQuery(StringsKt.trimIndent("SELECT i.id,i.invoice_number,i.invoice_date,i.status,i.total_usd_cents,\n                      i.supplier_id,s.name,i.exchange_rate_syp_per_usd_milli\n               FROM purchase_invoices i LEFT JOIN suppliers s ON s.id=i.supplier_id\n               " + str6 + " ORDER BY i.invoice_date DESC,i.created_at DESC"), strArr);
        try {
            Cursor cursor2 = cursorRawQuery;
            while (cursor2.moveToNext()) {
                try {
                    String string = cursor2.getString(i);
                    String string2 = cursor2.getString(3);
                    long j = cursor2.getLong(4);
                    long j2 = cursor2.getLong(7);
                    long jUsdCentsToSyp = j2 > 0 ? CurrencyConverter.INSTANCE.usdCentsToSyp(j, j2) : 0L;
                    if (j2 > 0) {
                        SQLiteDatabase readableDatabase = purchasePaymentRepository.database.getReadableDatabase();
                        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
                        Intrinsics.checkNotNull(string);
                        jPaidSypMinor = purchasePaymentRepository.paidSypMinor(readableDatabase, string, j2);
                    } else {
                        jPaidSypMinor = 0;
                    }
                    if (j2 > 0) {
                        cursor = cursorRawQuery;
                        try {
                            SQLiteDatabase readableDatabase2 = purchasePaymentRepository.database.getReadableDatabase();
                            Intrinsics.checkNotNullExpressionValue(readableDatabase2, "<get-readableDatabase>(...)");
                            Intrinsics.checkNotNull(string);
                            jSupplierReturnCreditSyp = purchasePaymentRepository.supplierReturnCreditSyp(readableDatabase2, string);
                        } catch (Throwable th2) {
                            th = th2;
                            cursorRawQuery = cursor;
                            th = th;
                            try {
                                throw th;
                            } catch (Throwable th3) {
                                CloseableKt.closeFinally(cursorRawQuery, th);
                                throw th3;
                            }
                        }
                    } else {
                        cursor = cursorRawQuery;
                        jSupplierReturnCreditSyp = 0;
                    }
                    ArrayList arrayList2 = arrayList;
                    String str7 = str4;
                    String str8 = str5;
                    long jCoerceAtLeast2 = RangesKt.coerceAtLeast((jUsdCentsToSyp - jPaidSypMinor) - jSupplierReturnCreditSyp, 0L);
                    if (j2 > 0) {
                        jPaidUsdCentsLegacy = CurrencyConverter.INSTANCE.sypToUsdCents(jPaidSypMinor, j2);
                    } else {
                        SQLiteDatabase readableDatabase3 = purchasePaymentRepository.database.getReadableDatabase();
                        Intrinsics.checkNotNullExpressionValue(readableDatabase3, "<get-readableDatabase>(...)");
                        Intrinsics.checkNotNull(string);
                        jPaidUsdCentsLegacy = purchasePaymentRepository.paidUsdCentsLegacy(readableDatabase3, string);
                    }
                    long j3 = jPaidUsdCentsLegacy;
                    if (j2 > 0) {
                        jCoerceAtLeast = CurrencyConverter.INSTANCE.sypToUsdCents(jCoerceAtLeast2, j2);
                    } else {
                        jCoerceAtLeast = RangesKt.coerceAtLeast(j - j3, 0L);
                    }
                    if (Intrinsics.areEqual(string2, str8)) {
                        str2 = str8;
                        str = str7;
                    } else {
                        str = str7;
                        if (Intrinsics.areEqual(string2, str)) {
                            str2 = str;
                        } else {
                            String str9 = "PARTIALLY_PAID";
                            if (j2 > 0) {
                                if (jCoerceAtLeast2 == 0 && jSupplierReturnCreditSyp > 0 && jPaidSypMinor < jUsdCentsToSyp) {
                                    str9 = "SETTLED_BY_RETURN";
                                } else if (jCoerceAtLeast2 == 0) {
                                    str2 = "PAID";
                                } else if (jPaidSypMinor <= 0) {
                                    str2 = "UNPAID";
                                }
                                str2 = str9;
                            } else if (j3 >= j) {
                                str2 = "PAID";
                            } else if (j3 > 0) {
                                str2 = str9;
                            } else {
                                str2 = "UNPAID";
                            }
                        }
                    }
                    Intrinsics.checkNotNull(string);
                    String string3 = cursor2.getString(1);
                    Intrinsics.checkNotNullExpressionValue(string3, str3);
                    String string4 = cursor2.getString(2);
                    Intrinsics.checkNotNullExpressionValue(string4, str3);
                    Intrinsics.checkNotNull(string2);
                    String str10 = str3;
                    new PurchaseInvoicePaymentSummary(string, string3, string4, string2, str2, j, j3, jCoerceAtLeast, cursor2.isNull(5) ? null : cursor2.getString(5), cursor2.isNull(6) ? null : cursor2.getString(6), jUsdCentsToSyp, jPaidSypMinor, jCoerceAtLeast2, j2, jSupplierReturnCreditSyp);
                    arrayList = arrayList2;
                    arrayList.add(r11);
                    purchasePaymentRepository = this;
                    cursorRawQuery = cursor;
                    str4 = str;
                    str3 = str10;
                    str5 = str8;
                    i = 0;
                } catch (Throwable th4) {
                    th = th4;
                    th = th;
                    throw th;
                }
            }
            cursor = cursorRawQuery;
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            return arrayList;
        } catch (Throwable th5) {
            th = th5;
        }
    }

    public final List<SupplierFinancialSummary> listSupplierSummaries() throws IOException {
        int i;
        long j;
        int i2;
        PurchasePaymentRepository purchasePaymentRepository = this;
        ArrayList arrayList = new ArrayList();
        String str = null;
        Cursor cursorRawQuery = purchasePaymentRepository.database.getReadableDatabase().rawQuery("SELECT id,name,phone,address FROM suppliers ORDER BY name COLLATE NOCASE", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            while (cursor.moveToNext()) {
                String string = cursor.getString(0);
                List<PurchaseInvoicePaymentSummary> listListPurchaseInvoices = purchasePaymentRepository.listPurchaseInvoices(string);
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : listListPurchaseInvoices) {
                    PurchaseInvoicePaymentSummary purchaseInvoicePaymentSummary = (PurchaseInvoicePaymentSummary) obj;
                    if (!Intrinsics.areEqual(purchaseInvoicePaymentSummary.getInvoiceStatus(), "DRAFT") && !Intrinsics.areEqual(purchaseInvoicePaymentSummary.getInvoiceStatus(), "CANCELLED")) {
                        arrayList2.add(obj);
                    }
                }
                ArrayList arrayList3 = arrayList2;
                Intrinsics.checkNotNull(string);
                String string2 = cursor.getString(1);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                String string3 = cursor.isNull(2) ? str : cursor.getString(2);
                String string4 = cursor.isNull(3) ? str : cursor.getString(3);
                Iterator it = arrayList3.iterator();
                long totalUsdCents = 0;
                while (it.hasNext()) {
                    totalUsdCents += ((PurchaseInvoicePaymentSummary) it.next()).getTotalUsdCents();
                }
                Iterator it2 = arrayList3.iterator();
                long paidUsdCents = 0;
                while (it2.hasNext()) {
                    paidUsdCents += ((PurchaseInvoicePaymentSummary) it2.next()).getPaidUsdCents();
                }
                Iterator it3 = arrayList3.iterator();
                long outstandingUsdCents = 0;
                while (it3.hasNext()) {
                    outstandingUsdCents += ((PurchaseInvoicePaymentSummary) it3.next()).getOutstandingUsdCents();
                }
                ArrayList<PurchaseInvoicePaymentSummary> arrayList4 = arrayList3;
                if ((arrayList4 instanceof Collection) && arrayList4.isEmpty()) {
                    i = 0;
                } else {
                    int i3 = 0;
                    for (PurchaseInvoicePaymentSummary purchaseInvoicePaymentSummary2 : arrayList4) {
                        if (Intrinsics.areEqual(purchaseInvoicePaymentSummary2.getPaymentStatus(), "UNPAID") || Intrinsics.areEqual(purchaseInvoicePaymentSummary2.getPaymentStatus(), "PARTIALLY_PAID")) {
                            i3++;
                            if (i3 < 0) {
                                CollectionsKt.throwCountOverflow();
                            }
                        }
                    }
                    i = i3;
                }
                Iterator it4 = arrayList3.iterator();
                long totalSypMinor = 0;
                while (it4.hasNext()) {
                    totalSypMinor += ((PurchaseInvoicePaymentSummary) it4.next()).getTotalSypMinor();
                }
                Iterator it5 = arrayList3.iterator();
                long paidSypMinor = 0;
                while (it5.hasNext()) {
                    paidSypMinor += ((PurchaseInvoicePaymentSummary) it5.next()).getPaidSypMinor();
                }
                Iterator it6 = arrayList3.iterator();
                long outstandingSypMinor = 0;
                while (it6.hasNext()) {
                    outstandingSypMinor += ((PurchaseInvoicePaymentSummary) it6.next()).getOutstandingSypMinor();
                }
                ArrayList<PurchaseInvoicePaymentSummary> arrayList5 = arrayList3;
                if ((arrayList5 instanceof Collection) && arrayList5.isEmpty()) {
                    i2 = 0;
                    j = 0;
                } else {
                    int i4 = 0;
                    for (PurchaseInvoicePaymentSummary purchaseInvoicePaymentSummary3 : arrayList5) {
                        if (purchaseInvoicePaymentSummary3.getInvoiceRateMilli() <= 0 && !Intrinsics.areEqual(purchaseInvoicePaymentSummary3.getPaymentStatus(), "PAID") && (i4 = i4 + 1) < 0) {
                            CollectionsKt.throwCountOverflow();
                        }
                    }
                    j = 0;
                    i2 = i4;
                }
                Iterator it7 = arrayList3.iterator();
                long supplierReturnCreditSypMinor = j;
                while (it7.hasNext()) {
                    supplierReturnCreditSypMinor += ((PurchaseInvoicePaymentSummary) it7.next()).getSupplierReturnCreditSypMinor();
                }
                arrayList.add(new SupplierFinancialSummary(string, string2, string3, string4, totalUsdCents, paidUsdCents, outstandingUsdCents, i, totalSypMinor, paidSypMinor, outstandingSypMinor, i2, supplierReturnCreditSypMinor));
                purchasePaymentRepository = this;
                str = null;
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

    public final List<PurchasePaymentRecord> listPayments(String invoiceId) throws IOException {
        Intrinsics.checkNotNullParameter(invoiceId, "invoiceId");
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery("SELECT p.id,p.amount_minor,p.currency_code,a.name,a.account_type,\n                      p.created_at,t.description,t.id,t.account_id\n               FROM invoice_payments p\n               JOIN cash_transactions t ON t.id=p.cash_transaction_id\n               JOIN cash_accounts a ON a.id=t.account_id\n               WHERE p.invoice_type='PURCHASE' AND p.invoice_id=?\n               ORDER BY p.created_at DESC", new String[]{invoiceId});
        try {
            Cursor cursor = cursorRawQuery;
            while (true) {
                String string = null;
                if (cursor.moveToNext()) {
                    String string2 = cursor.getString(0);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    long j = cursor.getLong(1);
                    String string3 = cursor.getString(2);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                    String string4 = cursor.getString(3);
                    Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                    String string5 = cursor.getString(4);
                    Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                    String string6 = cursor.getString(5);
                    Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                    if (!cursor.isNull(6)) {
                        string = cursor.getString(6);
                    }
                    String str = string;
                    String string7 = cursor.getString(7);
                    Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
                    String string8 = cursor.getString(8);
                    Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
                    arrayList.add(new PurchasePaymentRecord(string2, j, string3, string4, string5, string6, str, string7, string8));
                } else {
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursorRawQuery, null);
                    return arrayList;
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

    /* JADX WARN: Code duplicated, block: B:195:0x0441 A[Catch: all -> 0x0581, TRY_ENTER, TRY_LEAVE, TryCatch #14 {all -> 0x0581, blocks: (B:177:0x03e3, B:185:0x041b, B:216:0x0494, B:205:0x0466, B:195:0x0441), top: B:384:0x03e3 }] */
    /* JADX WARN: Code duplicated, block: B:205:0x0466 A[Catch: all -> 0x0581, TRY_ENTER, TRY_LEAVE, TryCatch #14 {all -> 0x0581, blocks: (B:177:0x03e3, B:185:0x041b, B:216:0x0494, B:205:0x0466, B:195:0x0441), top: B:384:0x03e3 }] */
    /* JADX WARN: Code duplicated, block: B:281:0x070c  */
    /* JADX WARN: Code duplicated, block: B:394:0x050b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v75, types: [com.hazem.dailystore.data.SyncProtocolRepository] */
    /* JADX WARN: Type inference failed for: r10v4, types: [android.content.ContentValues] */
    /* JADX WARN: Type inference failed for: r14v0, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r1v61, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v62, types: [com.hazem.dailystore.data.StoreTime] */
    /* JADX WARN: Type inference failed for: r1v72, types: [com.hazem.dailystore.data.SyncProtocolRepository] */
    /* JADX WARN: Type inference failed for: r4v11, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r71v0, types: [com.hazem.dailystore.data.PurchasePaymentRepository] */
    /* JADX WARN: Type inference failed for: r78v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r78v1 */
    /* JADX WARN: Type inference failed for: r78v10 */
    /* JADX WARN: Type inference failed for: r78v11 */
    /* JADX WARN: Type inference failed for: r78v17 */
    /* JADX WARN: Type inference failed for: r78v19 */
    /* JADX WARN: Type inference failed for: r78v2, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r78v21 */
    /* JADX WARN: Type inference failed for: r78v3 */
    /* JADX WARN: Type inference failed for: r78v4 */
    /* JADX WARN: Type inference failed for: r78v5 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v24 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.hazem.dailystore.data.PurchasePaymentRepository] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v17, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r8v18 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v7, types: [com.hazem.dailystore.data.StoreTime] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v10, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v36 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8 */
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
    public final PurchasePaymentResult addPayment(String actorUserId, String invoiceId, String operationId, String cashAccountId, long amountMinor, String note) throws Throwable {
        Throwable th;
        Throwable th2;
        char c;
        long jUsdCentsToSyp;
        ?? r8;
        Throwable th3;
        JSONObject jSONObject;
        Throwable th4;
        String strTimestamp;
        String strOperatingDate;
        long jLongValue;
        String strOptString;
        String strOptString2;
        String str;
        Throwable th5;
        Throwable th6;
        boolean z;
        Throwable th7;
        long j;
        String string;
        ?? r9 = this;
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(invoiceId, "invoiceId");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        Intrinsics.checkNotNullParameter(cashAccountId, "cashAccountId");
        if (StringsKt.isBlank(invoiceId) || StringsKt.isBlank(operationId) || StringsKt.isBlank(cashAccountId)) {
            throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u062f\u0641\u0639\u0629 \u063a\u064a\u0631 \u0645\u0643\u062a\u0645\u0644\u0629".toString());
        }
        if (amountMinor <= 0) {
            throw new IllegalArgumentException("\u0645\u0628\u0644\u063a \u0627\u0644\u062f\u0641\u0639\u0629 \u064a\u062c\u0628 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0623\u0643\u0628\u0631 \u0645\u0646 \u0635\u0641\u0631".toString());
        }
        SQLiteDatabase writableDatabase = r9.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        Object obj = (note == 0 || (string = StringsKt.trim((CharSequence) note).toString()) == null || string.length() <= 0) ? null : string;
        String strHash = r9.hash(invoiceId + "|" + cashAccountId + "|" + amountMinor + "|" + (obj == null ? "" : obj));
        Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            String string2 = cursor.moveToFirst() ? cursor.getString(0) : null;
            CloseableKt.closeFinally(cursorRawQuery, null);
            writableDatabase.beginTransaction();
            try {
                if (Intrinsics.areEqual(string2, "SECONDARY")) {
                    PermissionPolicy.INSTANCE.requireAssigned(writableDatabase, actorUserId, PermissionPolicy.SUPPLIER_PAYMENTS_MANAGE);
                } else {
                    PermissionPolicy.INSTANCE.require(writableDatabase, actorUserId, PermissionPolicy.SUPPLIER_PAYMENTS_MANAGE);
                }
                ExistingOperation existingOperationFindOperation = r9.findOperation(writableDatabase, operationId);
                ?? r10 = 1;
                r10 = 1;
                if (existingOperationFindOperation != null) {
                    try {
                        try {
                            if (!Intrinsics.areEqual(existingOperationFindOperation.getType(), "PURCHASE_PAYMENT") || !Intrinsics.areEqual(existingOperationFindOperation.getPayloadHash(), strHash)) {
                                throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0645\u0633\u062a\u062e\u062f\u0645 \u0644\u0639\u0645\u0644\u064a\u0629 \u0623\u0648 \u0628\u064a\u0627\u0646\u0627\u062a \u0645\u062e\u062a\u0644\u0641\u0629".toString());
                            }
                            String reference = existingOperationFindOperation.getReference();
                            if (reference == null) {
                                throw new IllegalStateException("\u0633\u062c\u0644 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0644\u0627 \u064a\u062d\u062a\u0648\u064a \u0645\u0631\u062c\u0639 \u0627\u0644\u062f\u0641\u0639\u0629".toString());
                            }
                            Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT total_usd_cents,exchange_rate_syp_per_usd_milli FROM purchase_invoices WHERE id=?", new String[]{invoiceId});
                            try {
                                Cursor cursor2 = cursorRawQuery2;
                                try {
                                    if (!cursor2.moveToFirst()) {
                                        throw new IllegalArgumentException("\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0634\u0631\u0627\u0621 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629".toString());
                                    }
                                    r10 = writableDatabase;
                                    SypPaymentState sypPaymentStateInvoicePaymentStateSyp = invoicePaymentStateSyp(writableDatabase, invoiceId, cursor2.getLong(0), cursor2.getLong(1));
                                    CloseableKt.closeFinally(cursorRawQuery2, null);
                                    Cursor cursorRawQuery3 = r10.rawQuery("SELECT exchange_rate_syp_per_usd_milli FROM purchase_invoices WHERE id=?", new String[]{invoiceId});
                                    try {
                                        Cursor cursor3 = cursorRawQuery3;
                                        cursor3.moveToFirst();
                                        long j2 = cursor3.getLong(0);
                                        CloseableKt.closeFinally(cursorRawQuery3, null);
                                        r10.setTransactionSuccessful();
                                        PurchasePaymentResult purchasePaymentResult = new PurchasePaymentResult(reference, CurrencyConverter.INSTANCE.sypToUsdCents(sypPaymentStateInvoicePaymentStateSyp.getFirst(), j2), CurrencyConverter.INSTANCE.sypToUsdCents(sypPaymentStateInvoicePaymentStateSyp.getSecond(), j2), sypPaymentStateInvoicePaymentStateSyp.getThird(), true, sypPaymentStateInvoicePaymentStateSyp.getFirst(), sypPaymentStateInvoicePaymentStateSyp.getSecond());
                                        r10.endTransaction();
                                        return purchasePaymentResult;
                                    } catch (Throwable th8) {
                                        try {
                                            throw th8;
                                        } catch (Throwable th9) {
                                            CloseableKt.closeFinally(cursorRawQuery3, th8);
                                            throw th9;
                                        }
                                    }
                                } catch (Throwable th10) {
                                    th = th10;
                                    th = th;
                                    throw th;
                                }
                            } catch (Throwable th11) {
                                th = th11;
                                r10 = writableDatabase;
                            }
                            th = th;
                            try {
                                throw th;
                            } catch (Throwable th12) {
                                CloseableKt.closeFinally(cursorRawQuery2, th);
                                throw th12;
                            }
                        } catch (Throwable th13) {
                            th = th13;
                            note = r10;
                            note.endTransaction();
                            throw th;
                        }
                    } catch (Throwable th14) {
                        th = th14;
                        r10 = writableDatabase;
                    }
                } else {
                    note = writableDatabase;
                    try {
                        try {
                            Cursor cursorRawQuery4 = note.rawQuery("SELECT status,total_usd_cents,supplier_id,invoice_number,exchange_rate_syp_per_usd_milli FROM purchase_invoices WHERE id=?", new String[]{invoiceId});
                            try {
                                Cursor cursor4 = cursorRawQuery4;
                                if (cursor4.moveToFirst()) {
                                    String string3 = cursor4.getString(0);
                                    Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                                    long j3 = cursor4.getLong(1);
                                    String string4 = cursor4.isNull(2) ? null : cursor4.getString(2);
                                    String string5 = cursor4.getString(3);
                                    Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                                    InvoiceForPayment invoiceForPayment = new InvoiceForPayment(string3, j3, string4, string5, cursor4.getLong(4));
                                    CloseableKt.closeFinally(cursorRawQuery4, null);
                                    if (!Intrinsics.areEqual(invoiceForPayment.getStatus(), "POSTED") && !Intrinsics.areEqual(invoiceForPayment.getStatus(), "PARTIALLY_PAID")) {
                                        throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u062f\u0641\u0639 \u0641\u0627\u062a\u0648\u0631\u0629 \u063a\u064a\u0631 \u0645\u0639\u062a\u0645\u062f\u0629 \u0623\u0648 \u0645\u0644\u063a\u0627\u0629".toString());
                                    }
                                    if (invoiceForPayment.getSupplierId() == null) {
                                        throw new IllegalArgumentException("\u064a\u062c\u0628 \u062a\u062d\u062f\u064a\u062f \u0627\u0644\u0645\u0648\u0631\u062f \u0641\u064a \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0642\u0628\u0644 \u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062f\u0641\u0639\u0629".toString());
                                    }
                                    Cursor cursorRawQuery5 = note.rawQuery("SELECT name,account_type,currency_code,opening_balance_minor,is_active FROM cash_accounts WHERE id=?", new String[]{cashAccountId});
                                    try {
                                        Cursor cursor5 = cursorRawQuery5;
                                        try {
                                            if (!cursor5.moveToFirst()) {
                                                throw new IllegalArgumentException("\u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
                                            }
                                            if (cursor5.getInt(4) != 1) {
                                                throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0627\u0644\u062f\u0641\u0639 \u0645\u0646 \u062d\u0633\u0627\u0628 \u0645\u0648\u0642\u0648\u0641".toString());
                                            }
                                            String string6 = cursor5.getString(0);
                                            Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                                            String string7 = cursor5.getString(1);
                                            Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
                                            String string8 = cursor5.getString(2);
                                            Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
                                            AccountForPayment accountForPayment = new AccountForPayment(string6, string7, string8, cursor5.getLong(3));
                                            CloseableKt.closeFinally(cursorRawQuery5, null);
                                            if (!Intrinsics.areEqual(accountForPayment.getCurrencyCode(), "USD") && !Intrinsics.areEqual(accountForPayment.getCurrencyCode(), "SYP")) {
                                                throw new IllegalArgumentException("\u0639\u0645\u0644\u0629 \u0627\u0644\u062d\u0633\u0627\u0628 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u0629".toString());
                                            }
                                            if (!Intrinsics.areEqual(string2, "SECONDARY") && Intrinsics.areEqual(accountForPayment.getAccountType(), "CASH_BOX") && Intrinsics.areEqual(accountForPayment.getCurrencyCode(), "SYP")) {
                                                c = 2;
                                                String str2 = StoreTime.today$default(StoreTime.INSTANCE, note, null, 2, null);
                                                Cursor cursorRawQuery6 = note.rawQuery("SELECT allocated_minor FROM daily_supplier_rollovers WHERE operating_date=? AND account_id=?", new String[]{str2, cashAccountId});
                                                try {
                                                    Cursor cursor6 = cursorRawQuery6;
                                                    Long lValueOf = cursor6.moveToFirst() ? Long.valueOf(cursor6.getLong(0)) : null;
                                                    CloseableKt.closeFinally(cursorRawQuery6, null);
                                                    if (lValueOf == null) {
                                                        throw new IllegalArgumentException("\u064a\u062c\u0628 \u0639\u0644\u0649 \u0627\u0644\u0645\u062f\u064a\u0631 \u062a\u062d\u062f\u064a\u062f \u0645\u0628\u0644\u063a \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0644\u0647\u0630\u0627 \u0627\u0644\u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u064a\u0648\u0645 \u0642\u0628\u0644 \u062f\u0641\u0639 \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0645\u0646\u0647".toString());
                                                    }
                                                    Cursor cursorRawQuery7 = note.rawQuery("SELECT COALESCE(SUM(amount_minor),0) FROM cash_transactions WHERE account_id=? AND direction='OUT' AND transaction_type IN ('PURCHASE_PAYMENT','PERSONAL_WITHDRAWAL') AND COALESCE(operating_date,substr(created_at,1,10))=?", new String[]{cashAccountId, str2});
                                                    try {
                                                        Cursor cursor7 = cursorRawQuery7;
                                                        if (cursor7.moveToFirst()) {
                                                            j = cursor7.getLong(0);
                                                            th7 = null;
                                                        } else {
                                                            th7 = null;
                                                            j = 0;
                                                        }
                                                        CloseableKt.closeFinally(cursorRawQuery7, th7);
                                                        if (lValueOf.longValue() - j < amountMinor) {
                                                            throw new IllegalArgumentException(("\u0627\u0644\u0645\u0628\u0644\u063a \u0627\u0644\u0645\u062a\u0627\u062d \u0645\u0646 \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0644\u0627 \u064a\u0643\u0641\u064a \u0644\u0647\u0630\u0647 \u0627\u0644\u062f\u0641\u0639\u0629. \u0627\u0644\u0645\u062a\u0627\u062d: " + RangesKt.coerceAtLeast(lValueOf.longValue() - j, 0L) + " \u0644.\u0633").toString());
                                                        }
                                                    } catch (Throwable th15) {
                                                        try {
                                                            throw th15;
                                                        } catch (Throwable th16) {
                                                            CloseableKt.closeFinally(cursorRawQuery7, th15);
                                                            throw th16;
                                                        }
                                                    }
                                                } catch (Throwable th17) {
                                                    try {
                                                        throw th17;
                                                    } catch (Throwable th18) {
                                                        CloseableKt.closeFinally(cursorRawQuery6, th17);
                                                        throw th18;
                                                    }
                                                }
                                            } else {
                                                c = 2;
                                            }
                                            long jCurrentRate = r9.currentRate(note);
                                            long exchangeRateMilli = invoiceForPayment.getExchangeRateMilli();
                                            if (exchangeRateMilli <= 0) {
                                                throw new IllegalArgumentException("\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0634\u0631\u0627\u0621 \u0644\u0627 \u062a\u062d\u062a\u0648\u064a \u0633\u0639\u0631 \u0635\u0631\u0641 \u0645\u062d\u0641\u0648\u0638\u0627\u064b\u061b \u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u062b\u0628\u064a\u062a \u0627\u0644\u0645\u062a\u0628\u0642\u064a \u0627\u0644\u0633\u0648\u0631\u064a \u0628\u0623\u0645\u0627\u0646".toString());
                                            }
                                            String currencyCode = accountForPayment.getCurrencyCode();
                                            if (Intrinsics.areEqual(currencyCode, "USD")) {
                                                jUsdCentsToSyp = CurrencyConverter.INSTANCE.usdCentsToSyp(amountMinor, exchangeRateMilli);
                                            } else {
                                                if (!Intrinsics.areEqual(currencyCode, "SYP")) {
                                                    throw new IllegalStateException("\u0639\u0645\u0644\u0629 \u0627\u0644\u062d\u0633\u0627\u0628 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u0629".toString());
                                                }
                                                jUsdCentsToSyp = amountMinor;
                                            }
                                            if (jUsdCentsToSyp <= 0) {
                                                throw new IllegalArgumentException("\u0645\u0628\u0644\u063a \u0627\u0644\u062f\u0641\u0639\u0629 \u0635\u063a\u064a\u0631 \u062c\u062f\u0627\u064b".toString());
                                            }
                                            r9 = note;
                                            String str3 = string2;
                                            try {
                                                SypPaymentState sypPaymentStateInvoicePaymentStateSyp2 = invoicePaymentStateSyp(note, invoiceId, invoiceForPayment.getTotalUsdCents(), exchangeRateMilli);
                                                if (jUsdCentsToSyp > sypPaymentStateInvoicePaymentStateSyp2.getSecond()) {
                                                    throw new IllegalArgumentException("\u0645\u0628\u0644\u063a \u0627\u0644\u062f\u0641\u0639\u0629 \u064a\u062a\u062c\u0627\u0648\u0632 \u0627\u0644\u0645\u062a\u0628\u0642\u064a \u0627\u0644\u0633\u0648\u0631\u064a \u0627\u0644\u062b\u0627\u0628\u062a \u0639\u0644\u0649 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629".toString());
                                                }
                                                try {
                                                    if (Intrinsics.areEqual(str3, "SECONDARY")) {
                                                        try {
                                                            Context context = r9.context;
                                                            Intrinsics.checkNotNullExpressionValue(context, "context");
                                                            long j4 = jUsdCentsToSyp;
                                                            ?? syncProtocolRepository = new SyncProtocolRepository(context, null, 2, false ? 1 : 0);
                                                            Cursor cursorRawQuery8 = r9.rawQuery("SELECT payload_json FROM sync_operation_envelopes WHERE operation_id=?", new String[]{operationId});
                                                            try {
                                                                Cursor cursor8 = cursorRawQuery8;
                                                                if (cursor8.moveToFirst()) {
                                                                    try {
                                                                        jSONObject = new JSONObject(cursor8.getString(0));
                                                                        th4 = null;
                                                                    } catch (Throwable th19) {
                                                                        th3 = th19;
                                                                        try {
                                                                            throw th3;
                                                                        } catch (Throwable th20) {
                                                                            CloseableKt.closeFinally(cursorRawQuery8, th3);
                                                                            throw th20;
                                                                        }
                                                                    }
                                                                } else {
                                                                    th4 = null;
                                                                    jSONObject = null;
                                                                }
                                                                CloseableKt.closeFinally(cursorRawQuery8, th4);
                                                                if (jSONObject == null || (strOptString2 = jSONObject.optString("occurred_at")) == null) {
                                                                    strTimestamp = timestamp();
                                                                } else {
                                                                    strTimestamp = new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(strOptString2) ? strOptString2 : null;
                                                                    if (strTimestamp == null) {
                                                                        strTimestamp = timestamp();
                                                                    }
                                                                }
                                                                if (jSONObject == null || (strOptString = jSONObject.optString("operating_date")) == null) {
                                                                    strOperatingDate = StoreTime.INSTANCE.operatingDate(r9, strTimestamp);
                                                                } else {
                                                                    strOperatingDate = new Regex("\\d{4}-\\d{2}-\\d{2}").matches(strOptString) ? strOptString : null;
                                                                    if (strOperatingDate == null) {
                                                                        strOperatingDate = StoreTime.INSTANCE.operatingDate(r9, strTimestamp);
                                                                    }
                                                                }
                                                                try {
                                                                    if (jSONObject != null) {
                                                                        Long lValueOf2 = Long.valueOf(jSONObject.optLong("payment_rate_milli", jCurrentRate));
                                                                        if (lValueOf2.longValue() <= 0) {
                                                                            lValueOf2 = null;
                                                                        }
                                                                        if (lValueOf2 != null) {
                                                                            jLongValue = lValueOf2.longValue();
                                                                        }
                                                                        String str4 = "pending-payment-" + operationId;
                                                                        JSONObject jSONObjectPut = new JSONObject().put("invoice_id", invoiceId).put("payment_id", str4).put("cash_transaction_id", "pending-cash-" + operationId).put("cash_account_id", cashAccountId).put("cash_account_type", accountForPayment.getAccountType()).put("cash_account_currency_code", accountForPayment.getCurrencyCode()).put("amount_minor", amountMinor).put("currency_code", accountForPayment.getCurrencyCode()).put("payment_rate_milli", jLongValue).put("amount_syp_equivalent", j4);
                                                                        if (obj == null) {
                                                                            try {
                                                                                obj = JSONObject.NULL;
                                                                            } catch (Throwable th21) {
                                                                                th = th21;
                                                                                note = r9;
                                                                            }
                                                                        }
                                                                        String string9 = jSONObjectPut.put("note", obj).put("occurred_at", strTimestamp).put("operating_date", strOperatingDate).toString();
                                                                        Intrinsics.checkNotNullExpressionValue(string9, "toString(...)");
                                                                        syncProtocolRepository.enqueueSecondaryCommand(r9, actorUserId, operationId, "POST_SUPPLIER_PAYMENT", "SUPPLIER_PAYMENT", str4, string9);
                                                                        r9.setTransactionSuccessful();
                                                                        PurchasePaymentResult purchasePaymentResult2 = new PurchasePaymentResult(str4, CurrencyConverter.INSTANCE.sypToUsdCents(sypPaymentStateInvoicePaymentStateSyp2.getFirst(), exchangeRateMilli), CurrencyConverter.INSTANCE.sypToUsdCents(sypPaymentStateInvoicePaymentStateSyp2.getSecond(), exchangeRateMilli), sypPaymentStateInvoicePaymentStateSyp2.getThird(), false, sypPaymentStateInvoicePaymentStateSyp2.getFirst(), sypPaymentStateInvoicePaymentStateSyp2.getSecond());
                                                                        r9.endTransaction();
                                                                        return purchasePaymentResult2;
                                                                    }
                                                                    JSONObject jSONObjectPut2 = new JSONObject().put("invoice_id", invoiceId).put("payment_id", str4).put("cash_transaction_id", "pending-cash-" + operationId).put("cash_account_id", cashAccountId).put("cash_account_type", accountForPayment.getAccountType()).put("cash_account_currency_code", accountForPayment.getCurrencyCode()).put("amount_minor", amountMinor).put("currency_code", accountForPayment.getCurrencyCode()).put("payment_rate_milli", jLongValue).put("amount_syp_equivalent", j4);
                                                                    if (obj == null) {
                                                                        obj = JSONObject.NULL;
                                                                    }
                                                                    String string10 = jSONObjectPut2.put("note", obj).put("occurred_at", strTimestamp).put("operating_date", strOperatingDate).toString();
                                                                    Intrinsics.checkNotNullExpressionValue(string10, "toString(...)");
                                                                    syncProtocolRepository.enqueueSecondaryCommand(r9, actorUserId, operationId, "POST_SUPPLIER_PAYMENT", "SUPPLIER_PAYMENT", str4, string10);
                                                                    r9.setTransactionSuccessful();
                                                                    PurchasePaymentResult purchasePaymentResult3 = new PurchasePaymentResult(str4, CurrencyConverter.INSTANCE.sypToUsdCents(sypPaymentStateInvoicePaymentStateSyp2.getFirst(), exchangeRateMilli), CurrencyConverter.INSTANCE.sypToUsdCents(sypPaymentStateInvoicePaymentStateSyp2.getSecond(), exchangeRateMilli), sypPaymentStateInvoicePaymentStateSyp2.getThird(), false, sypPaymentStateInvoicePaymentStateSyp2.getFirst(), sypPaymentStateInvoicePaymentStateSyp2.getSecond());
                                                                    r9.endTransaction();
                                                                    return purchasePaymentResult3;
                                                                } catch (Throwable th22) {
                                                                    th = th22;
                                                                    r8 = r9;
                                                                    note = r8;
                                                                }
                                                                jLongValue = jCurrentRate;
                                                                String str5 = "pending-payment-" + operationId;
                                                            } catch (Throwable th23) {
                                                                th3 = th23;
                                                            }
                                                        } catch (Throwable th24) {
                                                            th = th24;
                                                            r8 = r9;
                                                        }
                                                    } else {
                                                        note = r9;
                                                        long j5 = jUsdCentsToSyp;
                                                        if (str3 != null && !Intrinsics.areEqual(str3, "PRIMARY")) {
                                                            throw new IllegalArgumentException("\u062f\u0648\u0631 \u0627\u0644\u062c\u0647\u0627\u0632 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d \u0644\u062a\u0633\u062c\u064a\u0644 \u062f\u0641\u0639\u0627\u062a \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646".toString());
                                                        }
                                                        long jAccountBalance = accountBalance(note, cashAccountId, accountForPayment.getOpeningBalanceMinor());
                                                        if (jAccountBalance < amountMinor) {
                                                            throw new IllegalArgumentException(("\u0631\u0635\u064a\u062f \u0627\u0644\u062d\u0633\u0627\u0628 \u063a\u064a\u0631 \u0643\u0627\u0641\u064d. \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0645\u062a\u0627\u062d: " + jAccountBalance + " " + accountForPayment.getCurrencyCode()).toString());
                                                        }
                                                        String strTimestamp2 = timestamp();
                                                        String string11 = UUID.randomUUID().toString();
                                                        Intrinsics.checkNotNullExpressionValue(string11, "toString(...)");
                                                        String string12 = UUID.randomUUID().toString();
                                                        Intrinsics.checkNotNullExpressionValue(string12, "toString(...)");
                                                        ?? contentValues = new ContentValues();
                                                        contentValues.put("id", string12);
                                                        contentValues.put("account_id", cashAccountId);
                                                        contentValues.put("direction", "OUT");
                                                        contentValues.put("amount_minor", Long.valueOf(amountMinor));
                                                        contentValues.put("currency_code", accountForPayment.getCurrencyCode());
                                                        if (jCurrentRate > 0) {
                                                            contentValues.put("exchange_rate_syp_per_usd_milli", Long.valueOf(jCurrentRate));
                                                        } else {
                                                            contentValues.putNull("exchange_rate_syp_per_usd_milli");
                                                        }
                                                        contentValues.put("transaction_type", "PURCHASE_PAYMENT");
                                                        contentValues.put("source_type", "PURCHASE_INVOICE");
                                                        contentValues.put("source_id", invoiceId);
                                                        contentValues.put("operation_id", operationId);
                                                        if (obj == null) {
                                                            obj = "\u062f\u0641\u0639\u0629 \u0644\u0644\u0645\u0648\u0631\u062f \u0639\u0646 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 " + invoiceForPayment.getInvoiceNumber();
                                                        }
                                                        contentValues.put("description", obj);
                                                        contentValues.put("operating_date", StoreTime.INSTANCE.operatingDate(note, strTimestamp2));
                                                        contentValues.put("created_at", strTimestamp2);
                                                        note.insertOrThrow("cash_transactions", null, contentValues);
                                                        ContentValues contentValues2 = new ContentValues();
                                                        contentValues2.put("id", string11);
                                                        contentValues2.put("invoice_type", "PURCHASE");
                                                        contentValues2.put("invoice_id", invoiceId);
                                                        contentValues2.put("cash_transaction_id", string12);
                                                        contentValues2.put("amount_minor", Long.valueOf(amountMinor));
                                                        contentValues2.put("currency_code", accountForPayment.getCurrencyCode());
                                                        if (jCurrentRate > 0) {
                                                            str = "exchange_rate_syp_per_usd_milli";
                                                            contentValues2.put(str, Long.valueOf(jCurrentRate));
                                                        } else {
                                                            str = r11;
                                                            contentValues2.putNull(str);
                                                        }
                                                        contentValues2.put("created_at", strTimestamp2);
                                                        note.insertOrThrow("invoice_payments", null, contentValues2);
                                                        long jM = MainActivity$$ExternalSyntheticBackport0.m(sypPaymentStateInvoicePaymentStateSyp2.getFirst(), j5);
                                                        long jCoerceAtLeast = RangesKt.coerceAtLeast(sypPaymentStateInvoicePaymentStateSyp2.getSecond() - j5, 0L);
                                                        String str6 = str;
                                                        long jSypToUsdCents = CurrencyConverter.INSTANCE.sypToUsdCents(jM, exchangeRateMilli);
                                                        long jSypToUsdCents2 = CurrencyConverter.INSTANCE.sypToUsdCents(jCoerceAtLeast, exchangeRateMilli);
                                                        String str7 = jCoerceAtLeast == 0 ? "PAID" : "PARTIALLY_PAID";
                                                        note.execSQL("UPDATE purchase_invoices SET status=?,updated_at=? WHERE id=? AND status IN ('POSTED','PARTIALLY_PAID')", new String[]{str7, strTimestamp2, invoiceId});
                                                        Cursor cursorRawQuery9 = note.rawQuery("SELECT changes()", null);
                                                        try {
                                                            Cursor cursor9 = cursorRawQuery9;
                                                            if (cursor9.moveToFirst()) {
                                                                try {
                                                                    z = true;
                                                                    if (cursor9.getInt(0) == 1) {
                                                                        th6 = null;
                                                                    } else {
                                                                        th6 = null;
                                                                        z = false;
                                                                    }
                                                                } catch (Throwable th25) {
                                                                    th5 = th25;
                                                                    try {
                                                                        throw th5;
                                                                    } catch (Throwable th26) {
                                                                        CloseableKt.closeFinally(cursorRawQuery9, th5);
                                                                        throw th26;
                                                                    }
                                                                }
                                                            } else {
                                                                th6 = null;
                                                                z = false;
                                                            }
                                                            CloseableKt.closeFinally(cursorRawQuery9, th6);
                                                            if (!z) {
                                                                throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062d\u062f\u064a\u062b \u062d\u0627\u0644\u0629 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629".toString());
                                                            }
                                                            registerOperation(note, operationId, "PURCHASE_PAYMENT", strHash, string11, strTimestamp2);
                                                            Context context2 = this.context;
                                                            Intrinsics.checkNotNullExpressionValue(context2, "context");
                                                            ?? syncProtocolRepository2 = new SyncProtocolRepository(context2, null, 2, false ? 1 : 0);
                                                            String string13 = new JSONObject().put("invoice_id", invoiceId).put("payment_id", string11).put("cash_transaction_id", string12).put("cash_account_id", cashAccountId).put("amount_minor", amountMinor).put("currency_code", accountForPayment.getCurrencyCode()).put(str6, r1).put("amount_syp_equivalent", j5).put("created_at", strTimestamp2).toString();
                                                            Intrinsics.checkNotNullExpressionValue(string13, "toString(...)");
                                                            syncProtocolRepository2.enqueueLocalOperation(note, actorUserId, operationId, "POST_SUPPLIER_PAYMENT", "SUPPLIER_PAYMENT", string11, "CREATE", string13);
                                                            note.setTransactionSuccessful();
                                                            PurchasePaymentResult purchasePaymentResult4 = new PurchasePaymentResult(string11, jSypToUsdCents, jSypToUsdCents2, str7, false, jM, jCoerceAtLeast);
                                                            note.endTransaction();
                                                            return purchasePaymentResult4;
                                                        } catch (Throwable th27) {
                                                            th5 = th27;
                                                        }
                                                    }
                                                } catch (Throwable th28) {
                                                    th = th28;
                                                    r8 = r9;
                                                }
                                                note = r8;
                                            } catch (Throwable th29) {
                                                th = th29;
                                                note = r9;
                                            }
                                        } catch (Throwable th30) {
                                            th = th30;
                                            th2 = th;
                                            throw th2;
                                        }
                                    } catch (Throwable th31) {
                                        th = th31;
                                        note = note;
                                    }
                                    th2 = th;
                                    try {
                                        throw th2;
                                    } catch (Throwable th32) {
                                        CloseableKt.closeFinally(cursorRawQuery5, th2);
                                        throw th32;
                                    }
                                }
                                try {
                                    throw new IllegalArgumentException("\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0634\u0631\u0627\u0621 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629".toString());
                                } catch (Throwable th33) {
                                    th = th33;
                                }
                            } catch (Throwable th34) {
                                th = th34;
                            }
                            Throwable th35 = th;
                            try {
                                throw th35;
                            } catch (Throwable th36) {
                                CloseableKt.closeFinally(cursorRawQuery4, th35);
                                throw th36;
                            }
                        } catch (Throwable th37) {
                            th = th37;
                            note = note;
                        }
                    } catch (Throwable th38) {
                        th = th38;
                    }
                }
            } catch (Throwable th39) {
                th = th39;
                note = writableDatabase;
            }
            note.endTransaction();
            throw th;
        } catch (Throwable th40) {
            try {
                throw th40;
            } catch (Throwable th41) {
                CloseableKt.closeFinally(cursorRawQuery, th40);
                throw th41;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v2, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r14v3 */
    /* JADX WARN: Type inference failed for: r14v8 */
    /* JADX WARN: Type inference failed for: r14v9 */
    /* JADX WARN: Type inference failed for: r3v2, types: [com.hazem.dailystore.data.PermissionPolicy] */
    /* JADX WARN: Type inference failed for: r42v0, types: [com.hazem.dailystore.data.PurchasePaymentRepository] */
    /* JADX WARN: Type inference failed for: r9v8, types: [java.lang.Object, net.zetetic.database.sqlcipher.SQLiteDatabase] */
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
    public final PurchasePaymentResult editPayment(String actorRole, String actorUserId, String paymentId, String newCashAccountId, long newAmountMinor, String newNote) throws Throwable {
        String string;
        long j;
        long jUsdCentsToSyp;
        long amountMinor;
        String str;
        String str2;
        ?? writableDatabase = "PAID";
        Intrinsics.checkNotNullParameter(actorRole, "actorRole");
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(paymentId, "paymentId");
        Intrinsics.checkNotNullParameter(newCashAccountId, "newCashAccountId");
        if (!Intrinsics.areEqual(actorRole, "ADMIN")) {
            throw new IllegalArgumentException("\u062a\u0639\u062f\u064a\u0644 \u062f\u0641\u0639\u0627\u062a \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0645\u062a\u0627\u062d \u0644\u0644\u0645\u062f\u064a\u0631 \u0641\u0642\u0637".toString());
        }
        if (StringsKt.isBlank(actorUserId)) {
            throw new IllegalArgumentException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u062f\u064a\u0631 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
        }
        if (StringsKt.isBlank(paymentId) || StringsKt.isBlank(newCashAccountId)) {
            throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u062a\u0639\u062f\u064a\u0644 \u0627\u0644\u062f\u0641\u0639\u0629 \u063a\u064a\u0631 \u0645\u0643\u062a\u0645\u0644\u0629".toString());
        }
        if (newAmountMinor <= 0) {
            throw new IllegalArgumentException("\u0645\u0628\u0644\u063a \u0627\u0644\u062f\u0641\u0639\u0629 \u064a\u062c\u0628 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0623\u0643\u0628\u0631 \u0645\u0646 \u0635\u0641\u0631".toString());
        }
        if (newNote == null || (string = StringsKt.trim((CharSequence) newNote).toString()) == null || string.length() <= 0) {
            string = null;
        }
        writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        String str3 = string;
        PermissionPolicy.INSTANCE.require(writableDatabase, actorUserId, PermissionPolicy.SUPPLIER_PAYMENTS_CORRECT);
        writableDatabase.beginTransaction();
        try {
            try {
                Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT p.invoice_id,p.amount_minor,p.currency_code,p.exchange_rate_syp_per_usd_milli,\n                          p.cash_transaction_id,t.account_id,t.description,i.total_usd_cents,i.status,i.invoice_number,i.exchange_rate_syp_per_usd_milli,COALESCE(t.operating_date,substr(t.created_at,1,10))\n                   FROM invoice_payments p\n                   JOIN cash_transactions t ON t.id=p.cash_transaction_id\n                   JOIN purchase_invoices i ON i.id=p.invoice_id\n                   WHERE p.id=? AND p.invoice_type='PURCHASE'", new String[]{paymentId});
                try {
                    Cursor cursor = cursorRawQuery;
                    if (!cursor.moveToFirst()) {
                        try {
                            throw new IllegalArgumentException("\u062f\u0641\u0639\u0629 \u0627\u0644\u0645\u0648\u0631\u062f \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629".toString());
                        } catch (Throwable th) {
                            th = th;
                        }
                    } else {
                        String string2 = cursor.getString(0);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                        long j2 = cursor.getLong(1);
                        String string3 = cursor.getString(2);
                        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                        Long lValueOf = cursor.isNull(3) ? null : Long.valueOf(cursor.getLong(3));
                        String string4 = cursor.getString(4);
                        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                        String string5 = cursor.getString(5);
                        Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                        String string6 = cursor.isNull(6) ? null : cursor.getString(6);
                        long j3 = cursor.getLong(7);
                        String string7 = cursor.getString(8);
                        Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
                        String string8 = cursor.getString(9);
                        Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
                        long j4 = cursor.getLong(10);
                        String string9 = cursor.getString(11);
                        Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
                        ExistingSupplierPayment existingSupplierPayment = new ExistingSupplierPayment(string2, j2, string3, lValueOf, string4, string5, string6, j3, string7, string8, j4, string9);
                        CloseableKt.closeFinally(cursorRawQuery, null);
                        if (!SetsKt.setOf((Object[]) new String[]{"POSTED", "PARTIALLY_PAID", "PAID"}).contains(existingSupplierPayment.getInvoiceStatus())) {
                            throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u0639\u062f\u064a\u0644 \u062f\u0641\u0639\u0629 \u0641\u0627\u062a\u0648\u0631\u0629 \u063a\u064a\u0631 \u0645\u0639\u062a\u0645\u062f\u0629 \u0623\u0648 \u0645\u0644\u063a\u0627\u0629".toString());
                        }
                        Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT name,account_type,currency_code,is_active,opening_balance_minor FROM cash_accounts WHERE id=?", new String[]{newCashAccountId});
                        try {
                            Cursor cursor2 = cursorRawQuery2;
                            try {
                                if (!cursor2.moveToFirst()) {
                                    throw new IllegalArgumentException("\u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
                                }
                                if (cursor2.getInt(3) != 1) {
                                    throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0646\u0642\u0644 \u0627\u0644\u062f\u0641\u0639\u0629 \u0625\u0644\u0649 \u062d\u0633\u0627\u0628 \u0645\u0648\u0642\u0648\u0641".toString());
                                }
                                String string10 = cursor2.getString(0);
                                Intrinsics.checkNotNullExpressionValue(string10, "getString(...)");
                                String string11 = cursor2.getString(1);
                                Intrinsics.checkNotNullExpressionValue(string11, "getString(...)");
                                String string12 = cursor2.getString(2);
                                Intrinsics.checkNotNullExpressionValue(string12, "getString(...)");
                                AccountForPayment accountForPayment = new AccountForPayment(string10, string11, string12, cursor2.getLong(4));
                                CloseableKt.closeFinally(cursorRawQuery2, null);
                                if (!Intrinsics.areEqual(accountForPayment.getCurrencyCode(), existingSupplierPayment.getCurrencyCode())) {
                                    throw new IllegalArgumentException("\u064a\u062c\u0628 \u0627\u062e\u062a\u064a\u0627\u0631 \u062d\u0633\u0627\u0628 \u0628\u0646\u0641\u0633 \u0639\u0645\u0644\u0629 \u0627\u0644\u062f\u0641\u0639\u0629\u061b \u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u063a\u064a\u064a\u0631 \u0627\u0644\u0639\u0645\u0644\u0629 \u0627\u0644\u062a\u0627\u0631\u064a\u062e\u064a\u0629 \u0623\u062b\u0646\u0627\u0621 \u0627\u0644\u062a\u0635\u062d\u064a\u062d".toString());
                                }
                                if (Intrinsics.areEqual(accountForPayment.getAccountType(), "CASH_BOX") && Intrinsics.areEqual(accountForPayment.getCurrencyCode(), "SYP")) {
                                    Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT allocated_minor FROM daily_supplier_rollovers WHERE operating_date=? AND account_id=?", new String[]{existingSupplierPayment.getPaymentDate(), newCashAccountId});
                                    try {
                                        Cursor cursor3 = cursorRawQuery3;
                                        Long lValueOf2 = cursor3.moveToFirst() ? Long.valueOf(cursor3.getLong(0)) : null;
                                        CloseableKt.closeFinally(cursorRawQuery3, null);
                                        if (lValueOf2 == null) {
                                            if (!Intrinsics.areEqual(existingSupplierPayment.getCashAccountId(), newCashAccountId)) {
                                                throw new IllegalArgumentException("\u062a\u063a\u064a\u064a\u0631 \u062d\u0633\u0627\u0628 \u062f\u0641\u0639\u0629 \u0627\u0644\u0645\u0648\u0631\u062f \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u064a\u062a\u0637\u0644\u0628 \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0627\u0644\u0645\u062d\u062f\u062f \u0641\u064a \u064a\u0648\u0645 \u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062f\u0641\u0639\u0629".toString());
                                            }
                                            j = newAmountMinor;
                                        } else {
                                            Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT COALESCE(SUM(amount_minor),0) FROM cash_transactions WHERE account_id=? AND direction='OUT' AND transaction_type IN ('PURCHASE_PAYMENT','PERSONAL_WITHDRAWAL') AND COALESCE(operating_date,substr(created_at,1,10))=?", new String[]{newCashAccountId, existingSupplierPayment.getPaymentDate()});
                                            try {
                                                Cursor cursor4 = cursorRawQuery4;
                                                cursor4.moveToFirst();
                                                long jCoerceAtLeast = cursor4.getLong(0);
                                                CloseableKt.closeFinally(cursorRawQuery4, null);
                                                if (Intrinsics.areEqual(existingSupplierPayment.getCashAccountId(), newCashAccountId)) {
                                                    jCoerceAtLeast = RangesKt.coerceAtLeast(jCoerceAtLeast - existingSupplierPayment.getAmountMinor(), 0L);
                                                }
                                                long jLongValue = lValueOf2.longValue() - jCoerceAtLeast;
                                                j = newAmountMinor;
                                                if (jLongValue < j) {
                                                    throw new IllegalArgumentException("\u0627\u0644\u0645\u062a\u0627\u062d \u0645\u0646 \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0644\u0627 \u064a\u0643\u0641\u064a \u0628\u0639\u062f \u062a\u0635\u062d\u064a\u062d \u0627\u0644\u062f\u0641\u0639\u0629".toString());
                                                }
                                            } catch (Throwable th2) {
                                                try {
                                                    throw th2;
                                                } catch (Throwable th3) {
                                                    CloseableKt.closeFinally(cursorRawQuery4, th2);
                                                    throw th3;
                                                }
                                            }
                                        }
                                    } catch (Throwable th4) {
                                        try {
                                            throw th4;
                                        } catch (Throwable th5) {
                                            CloseableKt.closeFinally(cursorRawQuery3, th4);
                                            throw th5;
                                        }
                                    }
                                } else {
                                    j = newAmountMinor;
                                }
                                long invoiceRateMilli = existingSupplierPayment.getInvoiceRateMilli();
                                if (invoiceRateMilli <= 0) {
                                    throw new IllegalArgumentException("\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0634\u0631\u0627\u0621 \u0644\u0627 \u062a\u062d\u062a\u0648\u064a \u0633\u0639\u0631 \u0635\u0631\u0641 \u0645\u062d\u0641\u0648\u0638\u0627\u064b\u061b \u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u0635\u062d\u064a\u062d \u0627\u0644\u0645\u062a\u0628\u0642\u064a \u0627\u0644\u0633\u0648\u0631\u064a \u0628\u0623\u0645\u0627\u0646".toString());
                                }
                                String currencyCode = existingSupplierPayment.getCurrencyCode();
                                if (Intrinsics.areEqual(currencyCode, "USD")) {
                                    jUsdCentsToSyp = CurrencyConverter.INSTANCE.usdCentsToSyp(j, invoiceRateMilli);
                                } else {
                                    if (!Intrinsics.areEqual(currencyCode, "SYP")) {
                                        throw new IllegalStateException("\u0639\u0645\u0644\u0629 \u062f\u0641\u0639\u0629 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u0629".toString());
                                    }
                                    jUsdCentsToSyp = j;
                                }
                                String currencyCode2 = existingSupplierPayment.getCurrencyCode();
                                if (Intrinsics.areEqual(currencyCode2, "USD")) {
                                    amountMinor = CurrencyConverter.INSTANCE.usdCentsToSyp(existingSupplierPayment.getAmountMinor(), invoiceRateMilli);
                                } else {
                                    if (!Intrinsics.areEqual(currencyCode2, "SYP")) {
                                        throw new IllegalStateException("\u0639\u0645\u0644\u0629 \u062f\u0641\u0639\u0629 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u0629".toString());
                                    }
                                    amountMinor = existingSupplierPayment.getAmountMinor();
                                }
                                if (jUsdCentsToSyp <= 0) {
                                    throw new IllegalArgumentException("\u0627\u0644\u0645\u0628\u0644\u063a \u0627\u0644\u062c\u062f\u064a\u062f \u0635\u063a\u064a\u0631 \u062c\u062f\u0627\u064b".toString());
                                }
                                long jCoerceAtLeast2 = RangesKt.coerceAtLeast(paidSypMinor(writableDatabase, existingSupplierPayment.getInvoiceId(), invoiceRateMilli) - amountMinor, 0L);
                                long jUsdCentsToSyp2 = CurrencyConverter.INSTANCE.usdCentsToSyp(existingSupplierPayment.getInvoiceTotalUsdCents(), invoiceRateMilli) - supplierReturnCreditSyp(writableDatabase, existingSupplierPayment.getInvoiceId());
                                if (jUsdCentsToSyp > RangesKt.coerceAtLeast(jUsdCentsToSyp2 - jCoerceAtLeast2, 0L)) {
                                    throw new IllegalArgumentException("\u0627\u0644\u0645\u0628\u0644\u063a \u0627\u0644\u0645\u0639\u062f\u0644 \u064a\u062a\u062c\u0627\u0648\u0632 \u0627\u0644\u0645\u062a\u0628\u0642\u064a \u0627\u0644\u0633\u0648\u0631\u064a \u0627\u0644\u062b\u0627\u0628\u062a \u0628\u0639\u062f \u0627\u062d\u062a\u0633\u0627\u0628 \u0627\u0644\u062f\u0641\u0639\u0627\u062a \u0627\u0644\u0623\u062e\u0631\u0649".toString());
                                }
                                long jAccountBalance = accountBalance(writableDatabase, existingSupplierPayment.getCashAccountId());
                                if (Intrinsics.areEqual(existingSupplierPayment.getCashAccountId(), newCashAccountId)) {
                                    if (MainActivity$$ExternalSyntheticBackport0.m(jAccountBalance, existingSupplierPayment.getAmountMinor()) < j) {
                                        throw new IllegalArgumentException("\u0627\u0644\u0631\u0635\u064a\u062f \u063a\u064a\u0631 \u0643\u0627\u0641\u064d \u0628\u0639\u062f \u062a\u0639\u062f\u064a\u0644 \u0645\u0628\u0644\u063a \u0627\u0644\u062f\u0641\u0639\u0629".toString());
                                    }
                                } else if (accountBalance(writableDatabase, newCashAccountId) < j) {
                                    throw new IllegalArgumentException("\u0631\u0635\u064a\u062f \u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u062c\u062f\u064a\u062f \u063a\u064a\u0631 \u0643\u0627\u0641\u064d \u0644\u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062f\u0641\u0639\u0629 \u0627\u0644\u0645\u0635\u062d\u062d\u0629".toString());
                                }
                                long amountMinor2 = existingSupplierPayment.getAmountMinor();
                                String strJsonString = jsonString(existingSupplierPayment.getCurrencyCode());
                                String strJsonString2 = jsonString(existingSupplierPayment.getCashAccountId());
                                String note = existingSupplierPayment.getNote();
                                if (note == null) {
                                    note = "";
                                }
                                String str4 = "{\"amount_minor\":" + amountMinor2 + ",\"currency\":" + strJsonString + ",\"account_id\":" + strJsonString2 + ",\"note\":" + jsonString(note) + "}";
                                String strJsonString3 = jsonString(existingSupplierPayment.getCurrencyCode());
                                String strJsonString4 = jsonString(newCashAccountId);
                                String str5 = "{\"amount_minor\":" + j + ",\"currency\":" + strJsonString3 + ",\"account_id\":" + strJsonString4 + ",\"note\":" + jsonString(str3 == null ? "" : str3) + "}";
                                String strTimestamp = timestamp();
                                if (str3 == null) {
                                    str = "\u062f\u0641\u0639\u0629 \u0645\u0635\u062d\u062d\u0629 \u0644\u0644\u0645\u0648\u0631\u062f \u0639\u0646 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 " + existingSupplierPayment.getInvoiceNumber();
                                } else {
                                    str = str3;
                                }
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("account_id", newCashAccountId);
                                contentValues.put("amount_minor", Long.valueOf(newAmountMinor));
                                contentValues.put("description", str);
                                if (writableDatabase.update("cash_transactions", contentValues, "id=?", new String[]{existingSupplierPayment.getCashTransactionId()}) != 1) {
                                    throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u0639\u062f\u064a\u0644 \u062d\u0631\u0643\u0629 \u0627\u0644\u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0645\u0631\u062a\u0628\u0637\u0629 \u0628\u0627\u0644\u062f\u0641\u0639\u0629".toString());
                                }
                                ContentValues contentValues2 = new ContentValues();
                                contentValues2.put("amount_minor", Long.valueOf(newAmountMinor));
                                if (writableDatabase.update("invoice_payments", contentValues2, "id=?", new String[]{paymentId}) != 1) {
                                    throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u0639\u062f\u064a\u0644 \u0633\u062c\u0644 \u062f\u0641\u0639\u0629 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629".toString());
                                }
                                long jM = MainActivity$$ExternalSyntheticBackport0.m(jCoerceAtLeast2, jUsdCentsToSyp);
                                long jCoerceAtLeast3 = RangesKt.coerceAtLeast(jUsdCentsToSyp2 - jM, 0L);
                                long jSypToUsdCents = CurrencyConverter.INSTANCE.sypToUsdCents(jM, invoiceRateMilli);
                                long jSypToUsdCents2 = CurrencyConverter.INSTANCE.sypToUsdCents(jCoerceAtLeast3, invoiceRateMilli);
                                if (jCoerceAtLeast3 == 0) {
                                    str2 = "PAID";
                                } else {
                                    str2 = jM > 0 ? "PARTIALLY_PAID" : "POSTED";
                                }
                                writableDatabase.execSQL("UPDATE purchase_invoices SET status=?,updated_at=? WHERE id=?", new String[]{str2, strTimestamp, existingSupplierPayment.getInvoiceId()});
                                ContentValues contentValues3 = new ContentValues();
                                contentValues3.put("id", UUID.randomUUID().toString());
                                contentValues3.put("user_id", actorUserId);
                                contentValues3.put("action", "CORRECT");
                                contentValues3.put("entity_type", "PURCHASE_PAYMENT");
                                contentValues3.put("entity_id", paymentId);
                                contentValues3.put("before_json", str4);
                                contentValues3.put("after_json", str5);
                                contentValues3.put("created_at", strTimestamp);
                                writableDatabase.insertOrThrow("audit_log", null, contentValues3);
                                writableDatabase.setTransactionSuccessful();
                                PurchasePaymentResult purchasePaymentResult = new PurchasePaymentResult(paymentId, jSypToUsdCents, jSypToUsdCents2, jCoerceAtLeast3 == 0 ? "PAID" : "PARTIALLY_PAID", false, jM, jCoerceAtLeast3);
                                writableDatabase.endTransaction();
                                return purchasePaymentResult;
                            } catch (Throwable th6) {
                                th = th6;
                            }
                        } catch (Throwable th7) {
                            th = th7;
                            writableDatabase = writableDatabase;
                        }
                        Throwable th8 = th;
                        try {
                            throw th8;
                        } catch (Throwable th9) {
                            CloseableKt.closeFinally(cursorRawQuery2, th8);
                            throw th9;
                        }
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
                writableDatabase.endTransaction();
                throw th;
            }
        } catch (Throwable th14) {
            th = th14;
            writableDatabase = writableDatabase;
            writableDatabase.endTransaction();
            throw th;
        }
    }

    /* JADX INFO: compiled from: PurchasePaymentRepository.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b+\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001Bk\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0010\u0010\u0011J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\u0005H\u00c6\u0003J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010%\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0018J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003J\t\u0010'\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\u0005H\u00c6\u0003J\t\u0010*\u001a\u00020\u0003H\u00c6\u0003J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\u0005H\u00c6\u0003J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\u008a\u0001\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010/J\u0013\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00103\u001a\u000204H\u00d6\u0001J\t\u00105\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015R\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0013R\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0013R\u0011\u0010\u000e\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0015R\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0013\u00a8\u00066"}, d2 = {"Lcom/hazem/dailystore/data/PurchasePaymentRepository$ExistingSupplierPayment;", "", "invoiceId", "", "amountMinor", "", "currencyCode", "exchangeRateMilli", "cashTransactionId", "cashAccountId", "note", "invoiceTotalUsdCents", "invoiceStatus", "invoiceNumber", "invoiceRateMilli", "paymentDate", "<init>", "(Ljava/lang/String;JLjava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;JLjava/lang/String;)V", "getInvoiceId", "()Ljava/lang/String;", "getAmountMinor", "()J", "getCurrencyCode", "getExchangeRateMilli", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getCashTransactionId", "getCashAccountId", "getNote", "getInvoiceTotalUsdCents", "getInvoiceStatus", "getInvoiceNumber", "getInvoiceRateMilli", "getPaymentDate", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "(Ljava/lang/String;JLjava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;JLjava/lang/String;)Lcom/hazem/dailystore/data/PurchasePaymentRepository$ExistingSupplierPayment;", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class ExistingSupplierPayment {
        private final long amountMinor;
        private final String cashAccountId;
        private final String cashTransactionId;
        private final String currencyCode;
        private final Long exchangeRateMilli;
        private final String invoiceId;
        private final String invoiceNumber;
        private final long invoiceRateMilli;
        private final String invoiceStatus;
        private final long invoiceTotalUsdCents;
        private final String note;
        private final String paymentDate;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getInvoiceId() {
            return this.invoiceId;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final String getInvoiceNumber() {
            return this.invoiceNumber;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final long getInvoiceRateMilli() {
            return this.invoiceRateMilli;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final String getPaymentDate() {
            return this.paymentDate;
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
        public final Long getExchangeRateMilli() {
            return this.exchangeRateMilli;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getCashTransactionId() {
            return this.cashTransactionId;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getCashAccountId() {
            return this.cashAccountId;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getNote() {
            return this.note;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final long getInvoiceTotalUsdCents() {
            return this.invoiceTotalUsdCents;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getInvoiceStatus() {
            return this.invoiceStatus;
        }

        public final ExistingSupplierPayment copy(String invoiceId, long amountMinor, String currencyCode, Long exchangeRateMilli, String cashTransactionId, String cashAccountId, String note, long invoiceTotalUsdCents, String invoiceStatus, String invoiceNumber, long invoiceRateMilli, String paymentDate) {
            Intrinsics.checkNotNullParameter(invoiceId, "invoiceId");
            Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
            Intrinsics.checkNotNullParameter(cashTransactionId, "cashTransactionId");
            Intrinsics.checkNotNullParameter(cashAccountId, "cashAccountId");
            Intrinsics.checkNotNullParameter(invoiceStatus, "invoiceStatus");
            Intrinsics.checkNotNullParameter(invoiceNumber, "invoiceNumber");
            Intrinsics.checkNotNullParameter(paymentDate, "paymentDate");
            return new ExistingSupplierPayment(invoiceId, amountMinor, currencyCode, exchangeRateMilli, cashTransactionId, cashAccountId, note, invoiceTotalUsdCents, invoiceStatus, invoiceNumber, invoiceRateMilli, paymentDate);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ExistingSupplierPayment)) {
                return false;
            }
            ExistingSupplierPayment existingSupplierPayment = (ExistingSupplierPayment) other;
            return Intrinsics.areEqual(this.invoiceId, existingSupplierPayment.invoiceId) && this.amountMinor == existingSupplierPayment.amountMinor && Intrinsics.areEqual(this.currencyCode, existingSupplierPayment.currencyCode) && Intrinsics.areEqual(this.exchangeRateMilli, existingSupplierPayment.exchangeRateMilli) && Intrinsics.areEqual(this.cashTransactionId, existingSupplierPayment.cashTransactionId) && Intrinsics.areEqual(this.cashAccountId, existingSupplierPayment.cashAccountId) && Intrinsics.areEqual(this.note, existingSupplierPayment.note) && this.invoiceTotalUsdCents == existingSupplierPayment.invoiceTotalUsdCents && Intrinsics.areEqual(this.invoiceStatus, existingSupplierPayment.invoiceStatus) && Intrinsics.areEqual(this.invoiceNumber, existingSupplierPayment.invoiceNumber) && this.invoiceRateMilli == existingSupplierPayment.invoiceRateMilli && Intrinsics.areEqual(this.paymentDate, existingSupplierPayment.paymentDate);
        }

        public int hashCode() {
            int iHashCode = ((((this.invoiceId.hashCode() * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.amountMinor)) * 31) + this.currencyCode.hashCode()) * 31;
            Long l = this.exchangeRateMilli;
            int iHashCode2 = (((((iHashCode + (l == null ? 0 : l.hashCode())) * 31) + this.cashTransactionId.hashCode()) * 31) + this.cashAccountId.hashCode()) * 31;
            String str = this.note;
            return ((((((((((iHashCode2 + (str != null ? str.hashCode() : 0)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.invoiceTotalUsdCents)) * 31) + this.invoiceStatus.hashCode()) * 31) + this.invoiceNumber.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.invoiceRateMilli)) * 31) + this.paymentDate.hashCode();
        }

        public String toString() {
            return "ExistingSupplierPayment(invoiceId=" + this.invoiceId + ", amountMinor=" + this.amountMinor + ", currencyCode=" + this.currencyCode + ", exchangeRateMilli=" + this.exchangeRateMilli + ", cashTransactionId=" + this.cashTransactionId + ", cashAccountId=" + this.cashAccountId + ", note=" + this.note + ", invoiceTotalUsdCents=" + this.invoiceTotalUsdCents + ", invoiceStatus=" + this.invoiceStatus + ", invoiceNumber=" + this.invoiceNumber + ", invoiceRateMilli=" + this.invoiceRateMilli + ", paymentDate=" + this.paymentDate + ")";
        }

        public ExistingSupplierPayment(String invoiceId, long j, String currencyCode, Long l, String cashTransactionId, String cashAccountId, String str, long j2, String invoiceStatus, String invoiceNumber, long j3, String paymentDate) {
            Intrinsics.checkNotNullParameter(invoiceId, "invoiceId");
            Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
            Intrinsics.checkNotNullParameter(cashTransactionId, "cashTransactionId");
            Intrinsics.checkNotNullParameter(cashAccountId, "cashAccountId");
            Intrinsics.checkNotNullParameter(invoiceStatus, "invoiceStatus");
            Intrinsics.checkNotNullParameter(invoiceNumber, "invoiceNumber");
            Intrinsics.checkNotNullParameter(paymentDate, "paymentDate");
            this.invoiceId = invoiceId;
            this.amountMinor = j;
            this.currencyCode = currencyCode;
            this.exchangeRateMilli = l;
            this.cashTransactionId = cashTransactionId;
            this.cashAccountId = cashAccountId;
            this.note = str;
            this.invoiceTotalUsdCents = j2;
            this.invoiceStatus = invoiceStatus;
            this.invoiceNumber = invoiceNumber;
            this.invoiceRateMilli = j3;
            this.paymentDate = paymentDate;
        }

        public final long getAmountMinor() {
            return this.amountMinor;
        }

        public final String getCurrencyCode() {
            return this.currencyCode;
        }

        public final Long getExchangeRateMilli() {
            return this.exchangeRateMilli;
        }

        public final String getInvoiceId() {
            return this.invoiceId;
        }

        public final String getCashAccountId() {
            return this.cashAccountId;
        }

        public final String getCashTransactionId() {
            return this.cashTransactionId;
        }

        public final long getInvoiceTotalUsdCents() {
            return this.invoiceTotalUsdCents;
        }

        public final String getNote() {
            return this.note;
        }

        public final String getInvoiceNumber() {
            return this.invoiceNumber;
        }

        public final long getInvoiceRateMilli() {
            return this.invoiceRateMilli;
        }

        public final String getInvoiceStatus() {
            return this.invoiceStatus;
        }

        public final String getPaymentDate() {
            return this.paymentDate;
        }
    }

    /* JADX INFO: compiled from: PurchasePaymentRepository.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0006H\u00c6\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0018"}, d2 = {"Lcom/hazem/dailystore/data/PurchasePaymentRepository$SupplierPaymentAccount;", "", "name", "", "currencyCode", "openingBalanceMinor", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;J)V", "getName", "()Ljava/lang/String;", "getCurrencyCode", "getOpeningBalanceMinor", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class SupplierPaymentAccount {
        private final String currencyCode;
        private final String name;
        private final long openingBalanceMinor;

        public static /* synthetic */ SupplierPaymentAccount copy$default(SupplierPaymentAccount supplierPaymentAccount, String str, String str2, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                str = supplierPaymentAccount.name;
            }
            if ((i & 2) != 0) {
                str2 = supplierPaymentAccount.currencyCode;
            }
            if ((i & 4) != 0) {
                j = supplierPaymentAccount.openingBalanceMinor;
            }
            return supplierPaymentAccount.copy(str, str2, j);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getCurrencyCode() {
            return this.currencyCode;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final long getOpeningBalanceMinor() {
            return this.openingBalanceMinor;
        }

        public final SupplierPaymentAccount copy(String name, String currencyCode, long openingBalanceMinor) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
            return new SupplierPaymentAccount(name, currencyCode, openingBalanceMinor);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SupplierPaymentAccount)) {
                return false;
            }
            SupplierPaymentAccount supplierPaymentAccount = (SupplierPaymentAccount) other;
            return Intrinsics.areEqual(this.name, supplierPaymentAccount.name) && Intrinsics.areEqual(this.currencyCode, supplierPaymentAccount.currencyCode) && this.openingBalanceMinor == supplierPaymentAccount.openingBalanceMinor;
        }

        public int hashCode() {
            return (((this.name.hashCode() * 31) + this.currencyCode.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.openingBalanceMinor);
        }

        public String toString() {
            return "SupplierPaymentAccount(name=" + this.name + ", currencyCode=" + this.currencyCode + ", openingBalanceMinor=" + this.openingBalanceMinor + ")";
        }

        public SupplierPaymentAccount(String name, String currencyCode, long j) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
            this.name = name;
            this.currencyCode = currencyCode;
            this.openingBalanceMinor = j;
        }

        public final String getCurrencyCode() {
            return this.currencyCode;
        }

        public final String getName() {
            return this.name;
        }

        public final long getOpeningBalanceMinor() {
            return this.openingBalanceMinor;
        }
    }

    private final long accountBalance(SQLiteDatabase db, String accountId) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT opening_balance_minor FROM cash_accounts WHERE id=?", new String[]{accountId});
        try {
            Cursor cursor = cursorRawQuery;
            if (!cursor.moveToFirst()) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            long j = cursor.getLong(0);
            CloseableKt.closeFinally(cursorRawQuery, null);
            Cursor cursorRawQuery2 = db.rawQuery("SELECT COALESCE(SUM(CASE WHEN direction='IN' THEN amount_minor\n                       WHEN direction='OUT' THEN -amount_minor ELSE 0 END),0)\n               FROM cash_transactions WHERE account_id=?", new String[]{accountId});
            try {
                Cursor cursor2 = cursorRawQuery2;
                long j2 = cursor2.moveToFirst() ? cursor2.getLong(0) : 0L;
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

    private final String jsonString(String value) {
        return "\"" + StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(value, "\\", "\\\\", false, 4, (Object) null), "\"", "\\\"", false, 4, (Object) null), "\n", "\\n", false, 4, (Object) null), "\r", "\\r", false, 4, (Object) null) + "\"";
    }

    private final long paidSypMinor(SQLiteDatabase db, String invoiceId, long invoiceRateMilli) throws IOException {
        long jM = 0;
        if (invoiceRateMilli <= 0) {
            throw new IllegalArgumentException("\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0634\u0631\u0627\u0621 \u0644\u0627 \u062a\u062d\u062a\u0648\u064a \u0633\u0639\u0631 \u0635\u0631\u0641 \u0645\u062d\u0641\u0648\u0638\u0627\u064b".toString());
        }
        Cursor cursorRawQuery = db.rawQuery("SELECT amount_minor,currency_code FROM invoice_payments\n            WHERE invoice_type='PURCHASE' AND invoice_id=?", new String[]{invoiceId});
        try {
            Cursor cursor = cursorRawQuery;
            while (cursor.moveToNext()) {
                long jUsdCentsToSyp = cursor.getLong(0);
                String string = cursor.getString(1);
                if (Intrinsics.areEqual(string, "USD")) {
                    jUsdCentsToSyp = CurrencyConverter.INSTANCE.usdCentsToSyp(jUsdCentsToSyp, invoiceRateMilli);
                } else if (!Intrinsics.areEqual(string, "SYP")) {
                    throw new IllegalStateException("\u0639\u0645\u0644\u0629 \u062f\u0641\u0639\u0629 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u0629".toString());
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

    private final long supplierReturnCreditSyp(SQLiteDatabase db, String invoiceId) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT COALESCE(SUM(supplier_credit_reduction_syp_minor),0)\n               FROM returns WHERE original_invoice_type='PURCHASE'\n                 AND original_invoice_id=? AND status='POSTED'", new String[]{invoiceId});
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

    private final long paidUsdCentsLegacy(SQLiteDatabase db, String invoiceId) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT amount_minor,currency_code,exchange_rate_syp_per_usd_milli\n            FROM invoice_payments WHERE invoice_type='PURCHASE' AND invoice_id=?", new String[]{invoiceId});
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

    private final SypPaymentState invoicePaymentStateSyp(SQLiteDatabase db, String invoiceId, long totalUsdCents, long rate) throws IOException {
        String str;
        long jUsdCentsToSyp = CurrencyConverter.INSTANCE.usdCentsToSyp(totalUsdCents, rate);
        long jPaidSypMinor = paidSypMinor(db, invoiceId, rate);
        long jCoerceAtLeast = RangesKt.coerceAtLeast((jUsdCentsToSyp - jPaidSypMinor) - supplierReturnCreditSyp(db, invoiceId), 0L);
        if (jCoerceAtLeast == 0) {
            str = "PAID";
        } else {
            str = jPaidSypMinor > 0 ? "PARTIALLY_PAID" : "UNPAID";
        }
        return new SypPaymentState(jPaidSypMinor, jCoerceAtLeast, str);
    }

    private final long accountBalance(SQLiteDatabase db, String id, long opening) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT COALESCE(SUM(CASE WHEN direction='IN' THEN amount_minor\n                       WHEN direction='OUT' THEN -amount_minor ELSE 0 END),0)\n               FROM cash_transactions WHERE account_id=?", new String[]{id});
        try {
            Cursor cursor = cursorRawQuery;
            long j = cursor.moveToFirst() ? cursor.getLong(0) : 0L;
            CloseableKt.closeFinally(cursorRawQuery, null);
            return MainActivity$$ExternalSyntheticBackport0.m(opening, j);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
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

    /* JADX INFO: compiled from: PurchasePaymentRepository.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0005\u00a2\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0005H\u00c6\u0003J=\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001J\t\u0010\u001d\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e\u00a8\u0006\u001e"}, d2 = {"Lcom/hazem/dailystore/data/PurchasePaymentRepository$InvoiceForPayment;", "", NotificationCompat.CATEGORY_STATUS, "", "totalUsdCents", "", "supplierId", "invoiceNumber", "exchangeRateMilli", "<init>", "(Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;J)V", "getStatus", "()Ljava/lang/String;", "getTotalUsdCents", "()J", "getSupplierId", "getInvoiceNumber", "getExchangeRateMilli", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class InvoiceForPayment {
        private final long exchangeRateMilli;
        private final String invoiceNumber;
        private final String status;
        private final String supplierId;
        private final long totalUsdCents;

        public static /* synthetic */ InvoiceForPayment copy$default(InvoiceForPayment invoiceForPayment, String str, long j, String str2, String str3, long j2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = invoiceForPayment.status;
            }
            if ((i & 2) != 0) {
                j = invoiceForPayment.totalUsdCents;
            }
            long j3 = j;
            if ((i & 4) != 0) {
                str2 = invoiceForPayment.supplierId;
            }
            String str4 = str2;
            if ((i & 8) != 0) {
                str3 = invoiceForPayment.invoiceNumber;
            }
            String str5 = str3;
            if ((i & 16) != 0) {
                j2 = invoiceForPayment.exchangeRateMilli;
            }
            return invoiceForPayment.copy(str, j3, str4, str5, j2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getStatus() {
            return this.status;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getTotalUsdCents() {
            return this.totalUsdCents;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getSupplierId() {
            return this.supplierId;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getInvoiceNumber() {
            return this.invoiceNumber;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final long getExchangeRateMilli() {
            return this.exchangeRateMilli;
        }

        public final InvoiceForPayment copy(String status, long totalUsdCents, String supplierId, String invoiceNumber, long exchangeRateMilli) {
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(invoiceNumber, "invoiceNumber");
            return new InvoiceForPayment(status, totalUsdCents, supplierId, invoiceNumber, exchangeRateMilli);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof InvoiceForPayment)) {
                return false;
            }
            InvoiceForPayment invoiceForPayment = (InvoiceForPayment) other;
            return Intrinsics.areEqual(this.status, invoiceForPayment.status) && this.totalUsdCents == invoiceForPayment.totalUsdCents && Intrinsics.areEqual(this.supplierId, invoiceForPayment.supplierId) && Intrinsics.areEqual(this.invoiceNumber, invoiceForPayment.invoiceNumber) && this.exchangeRateMilli == invoiceForPayment.exchangeRateMilli;
        }

        public int hashCode() {
            int iHashCode = ((this.status.hashCode() * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.totalUsdCents)) * 31;
            String str = this.supplierId;
            return ((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.invoiceNumber.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.exchangeRateMilli);
        }

        public String toString() {
            return "InvoiceForPayment(status=" + this.status + ", totalUsdCents=" + this.totalUsdCents + ", supplierId=" + this.supplierId + ", invoiceNumber=" + this.invoiceNumber + ", exchangeRateMilli=" + this.exchangeRateMilli + ")";
        }

        public InvoiceForPayment(String status, long j, String str, String invoiceNumber, long j2) {
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(invoiceNumber, "invoiceNumber");
            this.status = status;
            this.totalUsdCents = j;
            this.supplierId = str;
            this.invoiceNumber = invoiceNumber;
            this.exchangeRateMilli = j2;
        }

        public final long getExchangeRateMilli() {
            return this.exchangeRateMilli;
        }

        public final String getInvoiceNumber() {
            return this.invoiceNumber;
        }

        public final String getStatus() {
            return this.status;
        }

        public final String getSupplierId() {
            return this.supplierId;
        }

        public final long getTotalUsdCents() {
            return this.totalUsdCents;
        }
    }

    /* JADX INFO: compiled from: PurchasePaymentRepository.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0006H\u00c6\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0006H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0018"}, d2 = {"Lcom/hazem/dailystore/data/PurchasePaymentRepository$SypPaymentState;", "", "first", "", "second", "third", "", "<init>", "(JJLjava/lang/String;)V", "getFirst", "()J", "getSecond", "getThird", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class SypPaymentState {
        private final long first;
        private final long second;
        private final String third;

        public static /* synthetic */ SypPaymentState copy$default(SypPaymentState sypPaymentState, long j, long j2, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                j = sypPaymentState.first;
            }
            long j3 = j;
            if ((i & 2) != 0) {
                j2 = sypPaymentState.second;
            }
            long j4 = j2;
            if ((i & 4) != 0) {
                str = sypPaymentState.third;
            }
            return sypPaymentState.copy(j3, j4, str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getFirst() {
            return this.first;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getSecond() {
            return this.second;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getThird() {
            return this.third;
        }

        public final SypPaymentState copy(long first, long second, String third) {
            Intrinsics.checkNotNullParameter(third, "third");
            return new SypPaymentState(first, second, third);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SypPaymentState)) {
                return false;
            }
            SypPaymentState sypPaymentState = (SypPaymentState) other;
            return this.first == sypPaymentState.first && this.second == sypPaymentState.second && Intrinsics.areEqual(this.third, sypPaymentState.third);
        }

        public int hashCode() {
            return (((MainActivity$$ExternalSyntheticBackport0.m(this.first) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.second)) * 31) + this.third.hashCode();
        }

        public String toString() {
            return "SypPaymentState(first=" + this.first + ", second=" + this.second + ", third=" + this.third + ")";
        }

        public SypPaymentState(long j, long j2, String third) {
            Intrinsics.checkNotNullParameter(third, "third");
            this.first = j;
            this.second = j2;
            this.third = third;
        }

        public final long getFirst() {
            return this.first;
        }

        public final long getSecond() {
            return this.second;
        }

        public final String getThird() {
            return this.third;
        }
    }

    /* JADX INFO: compiled from: PurchasePaymentRepository.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0007H\u00c6\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001b"}, d2 = {"Lcom/hazem/dailystore/data/PurchasePaymentRepository$AccountForPayment;", "", "name", "", "accountType", "currencyCode", "openingBalanceMinor", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getName", "()Ljava/lang/String;", "getAccountType", "getCurrencyCode", "getOpeningBalanceMinor", "()J", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class AccountForPayment {
        private final String accountType;
        private final String currencyCode;
        private final String name;
        private final long openingBalanceMinor;

        public static /* synthetic */ AccountForPayment copy$default(AccountForPayment accountForPayment, String str, String str2, String str3, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                str = accountForPayment.name;
            }
            if ((i & 2) != 0) {
                str2 = accountForPayment.accountType;
            }
            String str4 = str2;
            if ((i & 4) != 0) {
                str3 = accountForPayment.currencyCode;
            }
            String str5 = str3;
            if ((i & 8) != 0) {
                j = accountForPayment.openingBalanceMinor;
            }
            return accountForPayment.copy(str, str4, str5, j);
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

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final long getOpeningBalanceMinor() {
            return this.openingBalanceMinor;
        }

        public final AccountForPayment copy(String name, String accountType, String currencyCode, long openingBalanceMinor) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(accountType, "accountType");
            Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
            return new AccountForPayment(name, accountType, currencyCode, openingBalanceMinor);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AccountForPayment)) {
                return false;
            }
            AccountForPayment accountForPayment = (AccountForPayment) other;
            return Intrinsics.areEqual(this.name, accountForPayment.name) && Intrinsics.areEqual(this.accountType, accountForPayment.accountType) && Intrinsics.areEqual(this.currencyCode, accountForPayment.currencyCode) && this.openingBalanceMinor == accountForPayment.openingBalanceMinor;
        }

        public int hashCode() {
            return (((((this.name.hashCode() * 31) + this.accountType.hashCode()) * 31) + this.currencyCode.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.openingBalanceMinor);
        }

        public String toString() {
            return "AccountForPayment(name=" + this.name + ", accountType=" + this.accountType + ", currencyCode=" + this.currencyCode + ", openingBalanceMinor=" + this.openingBalanceMinor + ")";
        }

        public AccountForPayment(String name, String accountType, String currencyCode, long j) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(accountType, "accountType");
            Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
            this.name = name;
            this.accountType = accountType;
            this.currencyCode = currencyCode;
            this.openingBalanceMinor = j;
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

        public final long getOpeningBalanceMinor() {
            return this.openingBalanceMinor;
        }
    }

    /* JADX INFO: compiled from: PurchasePaymentRepository.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J)\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t\u00a8\u0006\u0016"}, d2 = {"Lcom/hazem/dailystore/data/PurchasePaymentRepository$ExistingOperation;", "", "type", "", "payloadHash", "reference", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getType", "()Ljava/lang/String;", "getPayloadHash", "getReference", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
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
        return ArraysKt.joinToString$default(bArrDigest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.hazem.dailystore.data.PurchasePaymentRepository$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PurchasePaymentRepository.hash$lambda$84(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence hash$lambda$84(byte b) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    private final String timestamp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String str = simpleDateFormat.format(new Date());
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }
}
