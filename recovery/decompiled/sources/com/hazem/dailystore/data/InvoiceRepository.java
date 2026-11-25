package com.hazem.dailystore.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import androidx.core.app.NotificationCompat;
import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import net.zetetic.database.sqlcipher.SQLiteDatabase;
import org.bouncycastle.asn1.BERTags;
import org.json.JSONObject;

/* JADX INFO: compiled from: InvoiceRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001:\u0002RSB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005JR\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\r2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016J\\\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\r2\b\u0010\u0019\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\r2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\b\b\u0002\u0010\u001a\u001a\u00020\rJf\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\r2\b\u0010\u001d\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\r2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\rH\u0002J6\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\rJ6\u0010\"\u001a\u00020\u001f2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\rJ>\u0010#\u001a\u00020\u001f2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010$\u001a\u00020\r2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\rJL\u0010%\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\rH\u0002J\f\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\u0016J\f\u0010(\u001a\b\u0012\u0004\u0012\u00020'0\u0016J\u0016\u0010)\u001a\b\u0012\u0004\u0012\u00020'0\u00162\u0006\u0010\u001c\u001a\u00020\rH\u0002J\"\u0010*\u001a\u00020+2\n\u0010,\u001a\u00060-j\u0002`.2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0002J\u0010\u0010/\u001a\u00020\u00132\u0006\u00100\u001a\u00020\u0017H\u0002J\u001c\u00101\u001a\u00020\u00132\n\u0010,\u001a\u00060-j\u0002`.2\u0006\u00102\u001a\u00020\rH\u0002J\u001c\u00103\u001a\u00020\u001f2\n\u0010,\u001a\u00060-j\u0002`.2\u0006\u00102\u001a\u00020\rH\u0002J\u001c\u00104\u001a\u00020\r2\n\u0010,\u001a\u00060-j\u0002`.2\u0006\u00102\u001a\u00020\rH\u0002J$\u00105\u001a\u0002062\n\u0010,\u001a\u00060-j\u0002`.2\u0006\u00102\u001a\u00020\r2\u0006\u00107\u001a\u00020\rH\u0002JT\u00108\u001a\u00020+2\n\u0010,\u001a\u00060-j\u0002`.2\u0006\u00102\u001a\u00020\r2\u0006\u00107\u001a\u00020\r2\u0006\u00109\u001a\u0002062\u0006\u0010:\u001a\u00020\u00132\u0006\u0010;\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010<\u001a\u00020\r2\u0006\u0010=\u001a\u00020\rH\u0002J\u0014\u0010>\u001a\u00020\r2\n\u0010,\u001a\u00060-j\u0002`.H\u0002J\u0014\u0010?\u001a\u00020\u00132\n\u0010,\u001a\u00060-j\u0002`.H\u0002J&\u0010@\u001a\u0004\u0018\u00010\r2\n\u0010,\u001a\u00060-j\u0002`.2\u0006\u0010A\u001a\u00020\r2\u0006\u0010B\u001a\u00020\rH\u0002J$\u0010C\u001a\u00020\r2\n\u0010,\u001a\u00060-j\u0002`.2\u0006\u0010A\u001a\u00020\r2\u0006\u0010B\u001a\u00020\rH\u0002J$\u0010D\u001a\u00020\r2\n\u0010,\u001a\u00060-j\u0002`.2\u0006\u0010A\u001a\u00020\r2\u0006\u0010B\u001a\u00020\rH\u0002J,\u0010E\u001a\u00020\r2\n\u0010,\u001a\u00060-j\u0002`.2\u0006\u0010A\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010F\u001a\u00020\rH\u0002J\u001e\u0010G\u001a\u0004\u0018\u00010H2\n\u0010,\u001a\u00060-j\u0002`.2\u0006\u0010B\u001a\u00020\rH\u0002J<\u0010I\u001a\u00020+2\n\u0010,\u001a\u00060-j\u0002`.2\u0006\u0010B\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010J\u001a\u00020\r2\u0006\u0010K\u001a\u00020\r2\u0006\u0010=\u001a\u00020\rH\u0002JV\u0010L\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\r2\b\u0010\u001d\u001a\u0004\u0018\u00010\r2\u0006\u0010F\u001a\u00020\r2\u0006\u0010M\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\r2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\rH\u0002J\u0010\u0010N\u001a\u00020\r2\u0006\u0010O\u001a\u00020\rH\u0002J\u0010\u0010P\u001a\u00020\r2\u0006\u0010O\u001a\u00020\rH\u0002J\b\u0010Q\u001a\u00020\rH\u0002R\u0018\u0010\u0002\u001a\n \u0006*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006T"}, d2 = {"Lcom/hazem/dailystore/data/InvoiceRepository;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "kotlin.jvm.PlatformType", "Landroid/content/Context;", "database", "Lcom/hazem/dailystore/data/AppDatabase;", "savePurchaseDraft", "Lcom/hazem/dailystore/data/InvoiceSaveResult;", "actorUserId", "", "operationId", "invoiceId", "supplierId", "invoiceDate", "discountUsdCents", "", "notes", "lines", "", "Lcom/hazem/dailystore/data/InvoiceLineInput;", "saveSalesDraft", "customerId", "saleType", "saveDraft", "type", "partyId", "postPurchase", "", "occurredAt", "operatingDate", "postSale", "postCashSale", "cashAccountId", "postInvoice", "listPurchaseInvoices", "Lcom/hazem/dailystore/data/InvoiceSummary;", "listSalesInvoices", "listInvoices", "validateLines", "", "db", "Lnet/zetetic/database/sqlcipher/SQLiteDatabase;", "Lcom/hazem/dailystore/data/AppSqliteDatabase;", "lineTotal", "line", "productCost", "productId", "productTracksInventory", "productName", "stockQuantity", "", "warehouseId", "applyStockMovement", "delta", "unitCost", "movementType", "movementOperationId", "now", "defaultWarehouseId", "currentRate", "invoiceStatus", "table", "id", "invoiceWarehouse", "invoiceNumber", "nextInvoiceNumber", "date", "findOperation", "Lcom/hazem/dailystore/data/InvoiceRepository$ExistingOperation;", "registerOperation", "payloadHash", "reference", "hashPayload", "discount", "hash", "value", "operatingDateForTimestamp", "timestamp", "CashSaleReceipt", "ExistingOperation", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class InvoiceRepository {
    private final Context context;
    private final AppDatabase database;

    public InvoiceRepository(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context.getApplicationContext();
        this.database = AppDatabase.INSTANCE.getInstance(context);
    }

    public final InvoiceSaveResult savePurchaseDraft(String actorUserId, String operationId, String invoiceId, String supplierId, String invoiceDate, long discountUsdCents, String notes, List<InvoiceLineInput> lines) {
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        Intrinsics.checkNotNullParameter(invoiceDate, "invoiceDate");
        Intrinsics.checkNotNullParameter(lines, "lines");
        return saveDraft("PURCHASE", actorUserId, operationId, invoiceId, supplierId, invoiceDate, discountUsdCents, notes, lines, null);
    }

    public final InvoiceSaveResult saveSalesDraft(String actorUserId, String operationId, String invoiceId, String customerId, String invoiceDate, long discountUsdCents, String notes, List<InvoiceLineInput> lines, String saleType) {
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        Intrinsics.checkNotNullParameter(invoiceDate, "invoiceDate");
        Intrinsics.checkNotNullParameter(lines, "lines");
        Intrinsics.checkNotNullParameter(saleType, "saleType");
        if (!Intrinsics.areEqual(saleType, "CASH") && !Intrinsics.areEqual(saleType, "CREDIT")) {
            throw new IllegalArgumentException("\u0646\u0648\u0639 \u0627\u0644\u0628\u064a\u0639 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
        }
        if (Intrinsics.areEqual(saleType, "CREDIT")) {
            String str = customerId;
            if (str == null || StringsKt.isBlank(str)) {
                throw new IllegalArgumentException("\u064a\u062c\u0628 \u062a\u062d\u062f\u064a\u062f \u0627\u0644\u0632\u0628\u0648\u0646 \u0644\u0644\u0628\u064a\u0639 \u0627\u0644\u0622\u062c\u0644".toString());
            }
        }
        return saveDraft("SALE", actorUserId, operationId, invoiceId, customerId, invoiceDate, discountUsdCents, notes, lines, saleType);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final InvoiceSaveResult saveDraft(String type, String actorUserId, String operationId, String invoiceId, String partyId, String invoiceDate, long discountUsdCents, String notes, List<InvoiceLineInput> lines, String saleType) throws Throwable {
        SQLiteDatabase sQLiteDatabase;
        String string;
        String str;
        if (StringsKt.isBlank(operationId)) {
            throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0645\u0637\u0644\u0648\u0628".toString());
        }
        if (StringsKt.isBlank(invoiceDate)) {
            throw new IllegalArgumentException("\u062a\u0627\u0631\u064a\u062e \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0645\u0637\u0644\u0648\u0628".toString());
        }
        if (discountUsdCents < 0) {
            throw new IllegalArgumentException("\u0627\u0644\u062e\u0635\u0645 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0633\u0627\u0644\u0628\u0627\u064b".toString());
        }
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        validateLines(writableDatabase, lines);
        String str2 = Intrinsics.areEqual(type, "PURCHASE") ? "purchase_invoices" : "sales_invoices";
        String str3 = Intrinsics.areEqual(type, "PURCHASE") ? "purchase_items" : "sales_items";
        String str4 = Intrinsics.areEqual(type, "PURCHASE") ? "supplier_id" : "customer_id";
        String strHashPayload = hashPayload(type, invoiceId, partyId, invoiceDate, discountUsdCents, notes, lines, saleType);
        writableDatabase.beginTransaction();
        try {
            SyncCredentialVault syncCredentialVault = null;
            Object[] objArr = 0;
            if (Intrinsics.areEqual(type, "PURCHASE")) {
                Context context = this.context;
                Intrinsics.checkNotNullExpressionValue(context, "context");
                if (new SyncProtocolRepository(context, syncCredentialVault, 2, objArr == true ? 1 : 0).isConfiguredSecondary()) {
                    throw new IllegalArgumentException("\u0625\u0646\u0634\u0627\u0621 \u0641\u0648\u0627\u062a\u064a\u0631 \u0627\u0644\u0645\u0634\u062a\u0631\u064a\u0627\u062a \u0645\u062a\u0627\u062d \u0645\u0646 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0631\u0626\u064a\u0633\u064a \u0641\u0642\u0637".toString());
                }
            }
            sQLiteDatabase = writableDatabase;
            try {
                PermissionPolicy.INSTANCE.requireAssigned(sQLiteDatabase, actorUserId, Intrinsics.areEqual(type, "PURCHASE") ? PermissionPolicy.PURCHASES_MANAGE : PermissionPolicy.SALES_CHECKOUT);
                ExistingOperation existingOperationFindOperation = findOperation(sQLiteDatabase, operationId);
                String str5 = "SAVE_";
                if (existingOperationFindOperation != null) {
                    if (!Intrinsics.areEqual(existingOperationFindOperation.getType(), "SAVE_" + type + "_DRAFT") || !Intrinsics.areEqual(existingOperationFindOperation.getPayloadHash(), strHashPayload)) {
                        throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0645\u0633\u062a\u062e\u062f\u0645 \u0644\u0646\u0648\u0639 \u0623\u0648 \u0628\u064a\u0627\u0646\u0627\u062a \u0645\u062e\u062a\u0644\u0641\u0629".toString());
                    }
                    String reference = existingOperationFindOperation.getReference();
                    if (reference == null) {
                        throw new IllegalStateException("\u0633\u062c\u0644 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0644\u0627 \u064a\u062d\u062a\u0648\u064a \u0645\u0631\u062c\u0639 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629".toString());
                    }
                    String strInvoiceNumber = invoiceNumber(sQLiteDatabase, str2, reference);
                    sQLiteDatabase.setTransactionSuccessful();
                    InvoiceSaveResult invoiceSaveResult = new InvoiceSaveResult(reference, strInvoiceNumber, true);
                    sQLiteDatabase.endTransaction();
                    return invoiceSaveResult;
                }
                String strDefaultWarehouseId = defaultWarehouseId(sQLiteDatabase);
                String strTimestamp = timestamp();
                if (invoiceId == null) {
                    string = UUID.randomUUID().toString();
                    Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                } else {
                    string = invoiceId;
                }
                String strInvoiceStatus = invoiceId != null ? invoiceStatus(sQLiteDatabase, str2, invoiceId) : null;
                if (strInvoiceStatus != null && !Intrinsics.areEqual(strInvoiceStatus, "DRAFT")) {
                    throw new IllegalArgumentException("\u064a\u0645\u0643\u0646 \u062a\u0639\u062f\u064a\u0644 \u0627\u0644\u0645\u0633\u0648\u062f\u0627\u062a \u0641\u0642\u0637".toString());
                }
                boolean z = strInvoiceStatus == null;
                Iterator<T> it = lines.iterator();
                long jM = 0;
                while (it.hasNext()) {
                    jM = MainActivity$$ExternalSyntheticBackport0.m(jM, lineTotal((InvoiceLineInput) it.next()));
                    str5 = str5;
                    strTimestamp = strTimestamp;
                }
                String str6 = str5;
                String str7 = strTimestamp;
                if (discountUsdCents > jM) {
                    throw new IllegalArgumentException("\u0627\u0644\u062e\u0635\u0645 \u0623\u0643\u0628\u0631 \u0645\u0646 \u0625\u062c\u0645\u0627\u0644\u064a \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629".toString());
                }
                long jM2 = DailyBalanceRepository$$ExternalSyntheticBackport0.m(jM, discountUsdCents);
                long jCurrentRate = currentRate(sQLiteDatabase);
                String strNextInvoiceNumber = z ? nextInvoiceNumber(sQLiteDatabase, str2, type, invoiceDate) : invoiceNumber(sQLiteDatabase, str2, string);
                ContentValues contentValues = new ContentValues();
                contentValues.put("id", string);
                contentValues.put("invoice_number", strNextInvoiceNumber);
                if (partyId == null) {
                    contentValues.putNull(str4);
                } else {
                    contentValues.put(str4, partyId);
                }
                contentValues.put("warehouse_id", strDefaultWarehouseId);
                contentValues.put("invoice_date", invoiceDate);
                contentValues.put(NotificationCompat.CATEGORY_STATUS, "DRAFT");
                if (Intrinsics.areEqual(type, "SALE")) {
                    contentValues.put("sale_type", saleType == null ? "CASH" : saleType);
                }
                contentValues.put("subtotal_usd_cents", Long.valueOf(jM));
                contentValues.put("discount_usd_cents", Long.valueOf(discountUsdCents));
                contentValues.put("total_usd_cents", Long.valueOf(jM2));
                contentValues.put("exchange_rate_syp_per_usd_milli", Long.valueOf(jCurrentRate));
                if (notes == null) {
                    contentValues.putNull("notes");
                } else {
                    contentValues.put("notes", notes);
                }
                contentValues.put("updated_at", str7);
                if (z) {
                    contentValues.put("created_at", str7);
                }
                if (z) {
                    sQLiteDatabase.insertOrThrow(str2, null, contentValues);
                    str = str3;
                } else {
                    if (sQLiteDatabase.update(str2, contentValues, "id=? AND status='DRAFT'", new String[]{string}) != 1) {
                        throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062d\u062f\u064a\u062b \u0645\u0633\u0648\u062f\u0629 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629".toString());
                    }
                    str = str3;
                    sQLiteDatabase.delete(str, Intrinsics.areEqual(type, "PURCHASE") ? "purchase_invoice_id=?" : "sales_invoice_id=?", new String[]{string});
                }
                for (InvoiceLineInput invoiceLineInput : lines) {
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("id", UUID.randomUUID().toString());
                    contentValues2.put(Intrinsics.areEqual(type, "PURCHASE") ? "purchase_invoice_id" : "sales_invoice_id", string);
                    contentValues2.put("product_id", invoiceLineInput.getProductId());
                    contentValues2.put("quantity", Double.valueOf(invoiceLineInput.getQuantity().doubleValue()));
                    if (Intrinsics.areEqual(type, "PURCHASE")) {
                        contentValues2.put("unit_cost_usd_cents", Long.valueOf(invoiceLineInput.getUnitPriceUsdCents()));
                    } else {
                        contentValues2.put("unit_price_usd_cents", Long.valueOf(invoiceLineInput.getUnitPriceUsdCents()));
                        contentValues2.put("unit_cost_usd_cents", Long.valueOf(productCost(sQLiteDatabase, invoiceLineInput.getProductId())));
                    }
                    contentValues2.put("line_total_usd_cents", Long.valueOf(lineTotal(invoiceLineInput)));
                    sQLiteDatabase.insertOrThrow(str, null, contentValues2);
                }
                registerOperation(sQLiteDatabase, operationId, str6 + type + "_DRAFT", strHashPayload, string, str7);
                sQLiteDatabase.setTransactionSuccessful();
                InvoiceSaveResult invoiceSaveResult2 = new InvoiceSaveResult(string, strNextInvoiceNumber, false);
                sQLiteDatabase.endTransaction();
                return invoiceSaveResult2;
            } catch (Throwable th) {
                th = th;
                sQLiteDatabase.endTransaction();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            sQLiteDatabase = writableDatabase;
        }
    }

    public final boolean postPurchase(String actorUserId, String invoiceId, String operationId, String occurredAt, String operatingDate) {
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(invoiceId, "invoiceId");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        return postInvoice("PURCHASE", actorUserId, invoiceId, operationId, null, occurredAt, operatingDate);
    }

    public final boolean postSale(String actorUserId, String invoiceId, String operationId, String occurredAt, String operatingDate) {
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(invoiceId, "invoiceId");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        return postInvoice("SALE", actorUserId, invoiceId, operationId, null, occurredAt, operatingDate);
    }

    public final boolean postCashSale(String actorUserId, String invoiceId, String operationId, String cashAccountId, String occurredAt, String operatingDate) {
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(invoiceId, "invoiceId");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        Intrinsics.checkNotNullParameter(cashAccountId, "cashAccountId");
        if (StringsKt.isBlank(cashAccountId)) {
            throw new IllegalArgumentException("\u064a\u062c\u0628 \u0627\u062e\u062a\u064a\u0627\u0631 \u0627\u0644\u0635\u0646\u062f\u0648\u0642 \u0623\u0648 \u0627\u0644\u0645\u062d\u0641\u0638\u0629 \u0627\u0644\u0645\u0633\u062a\u0644\u0645\u0629".toString());
        }
        return postInvoice("SALE", actorUserId, invoiceId, operationId, cashAccountId, occurredAt, operatingDate);
    }

    /* JADX INFO: compiled from: InvoiceRepository.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0006H\u00c6\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e\u00a8\u0006\u001b"}, d2 = {"Lcom/hazem/dailystore/data/InvoiceRepository$CashSaleReceipt;", "", "accountId", "", "currencyCode", "amountMinor", "", "rateMilli", "<init>", "(Ljava/lang/String;Ljava/lang/String;JJ)V", "getAccountId", "()Ljava/lang/String;", "getCurrencyCode", "getAmountMinor", "()J", "getRateMilli", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class CashSaleReceipt {
        private final String accountId;
        private final long amountMinor;
        private final String currencyCode;
        private final long rateMilli;

        public static /* synthetic */ CashSaleReceipt copy$default(CashSaleReceipt cashSaleReceipt, String str, String str2, long j, long j2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = cashSaleReceipt.accountId;
            }
            if ((i & 2) != 0) {
                str2 = cashSaleReceipt.currencyCode;
            }
            String str3 = str2;
            if ((i & 4) != 0) {
                j = cashSaleReceipt.amountMinor;
            }
            long j3 = j;
            if ((i & 8) != 0) {
                j2 = cashSaleReceipt.rateMilli;
            }
            return cashSaleReceipt.copy(str, str3, j3, j2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getAccountId() {
            return this.accountId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getCurrencyCode() {
            return this.currencyCode;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final long getAmountMinor() {
            return this.amountMinor;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final long getRateMilli() {
            return this.rateMilli;
        }

        public final CashSaleReceipt copy(String accountId, String currencyCode, long amountMinor, long rateMilli) {
            Intrinsics.checkNotNullParameter(accountId, "accountId");
            Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
            return new CashSaleReceipt(accountId, currencyCode, amountMinor, rateMilli);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CashSaleReceipt)) {
                return false;
            }
            CashSaleReceipt cashSaleReceipt = (CashSaleReceipt) other;
            return Intrinsics.areEqual(this.accountId, cashSaleReceipt.accountId) && Intrinsics.areEqual(this.currencyCode, cashSaleReceipt.currencyCode) && this.amountMinor == cashSaleReceipt.amountMinor && this.rateMilli == cashSaleReceipt.rateMilli;
        }

        public int hashCode() {
            return (((((this.accountId.hashCode() * 31) + this.currencyCode.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.amountMinor)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.rateMilli);
        }

        public String toString() {
            return "CashSaleReceipt(accountId=" + this.accountId + ", currencyCode=" + this.currencyCode + ", amountMinor=" + this.amountMinor + ", rateMilli=" + this.rateMilli + ")";
        }

        public CashSaleReceipt(String accountId, String currencyCode, long j, long j2) {
            Intrinsics.checkNotNullParameter(accountId, "accountId");
            Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
            this.accountId = accountId;
            this.currencyCode = currencyCode;
            this.amountMinor = j;
            this.rateMilli = j2;
        }

        public final String getAccountId() {
            return this.accountId;
        }

        public final long getAmountMinor() {
            return this.amountMinor;
        }

        public final String getCurrencyCode() {
            return this.currencyCode;
        }

        public final long getRateMilli() {
            return this.rateMilli;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:100:0x01d8 A[Catch: all -> 0x018c, TRY_ENTER, TRY_LEAVE, TryCatch #25 {all -> 0x018c, blocks: (B:92:0x01b8, B:100:0x01d8, B:105:0x01f5, B:124:0x0229, B:133:0x0272, B:137:0x02a4, B:139:0x02a8, B:293:0x0546, B:318:0x05fc, B:324:0x061d, B:326:0x0622, B:332:0x063a, B:338:0x065d, B:340:0x0662, B:342:0x066a, B:344:0x0673, B:351:0x0683, B:353:0x068b, B:359:0x06a1, B:372:0x06ed, B:373:0x06f0, B:329:0x062b, B:330:0x0637, B:379:0x06f6, B:380:0x06f9, B:388:0x070b, B:389:0x070e, B:148:0x02ca, B:149:0x02d6, B:150:0x02d7, B:151:0x02e2, B:152:0x02e3, B:153:0x02ef, B:154:0x02f0, B:157:0x02fc, B:168:0x033a, B:171:0x0353, B:174:0x035a, B:175:0x0365, B:176:0x0366, B:178:0x036c, B:186:0x03a0, B:189:0x03a6, B:190:0x03b2, B:196:0x03b8, B:197:0x03bb, B:198:0x03bc, B:200:0x03c4, B:202:0x03ca, B:204:0x03d0, B:211:0x03ed, B:213:0x03f6, B:216:0x040a, B:244:0x045a, B:257:0x047e, B:258:0x048a, B:259:0x048b, B:247:0x0465, B:251:0x046f, B:260:0x04ba, B:261:0x04c6, B:262:0x04c7, B:263:0x04d2, B:219:0x0410, B:220:0x041b, B:226:0x0421, B:227:0x0424, B:228:0x0425, B:229:0x0431, B:235:0x0437, B:236:0x043a, B:237:0x043b, B:239:0x0444, B:242:0x044d, B:243:0x0459, B:273:0x04f1, B:274:0x04f4, B:275:0x04f5, B:276:0x04fd, B:277:0x04fe, B:278:0x050a, B:286:0x051d, B:287:0x0520, B:108:0x01fd, B:109:0x0209, B:117:0x0219, B:118:0x021c, B:75:0x0181, B:333:0x0646, B:335:0x064f, B:369:0x06ea, B:158:0x0308, B:160:0x0311, B:162:0x031b, B:166:0x0332, B:264:0x04d3, B:265:0x04df, B:266:0x04e0, B:267:0x04eb, B:270:0x04ee, B:319:0x060b, B:321:0x0614, B:205:0x03dc, B:207:0x03e5, B:179:0x0386, B:181:0x038f, B:376:0x06f3, B:232:0x0434, B:193:0x03b5, B:294:0x056b, B:298:0x0579, B:300:0x057f, B:302:0x058e, B:304:0x059b, B:306:0x05a3, B:308:0x05ac, B:311:0x05bc, B:312:0x05e7, B:314:0x05ea, B:315:0x05f6, B:316:0x05f7, B:381:0x06fa, B:382:0x0705, B:385:0x0708, B:101:0x01e4, B:103:0x01ed, B:110:0x020a, B:111:0x0213, B:114:0x0216, B:125:0x0235, B:127:0x023e, B:131:0x025a, B:130:0x0251, B:279:0x050b, B:280:0x0517, B:283:0x051a, B:214:0x0402, B:223:0x041e), top: B:598:0x0145, inners: #0, #1, #2, #3, #4, #5, #8, #11, #12, #13, #22, #24, #27, #29, #30, #33, #37, #39 }] */
    /* JADX WARN: Code duplicated, block: B:103:0x01ed A[Catch: all -> 0x0214, TRY_LEAVE, TryCatch #27 {all -> 0x0214, blocks: (B:101:0x01e4, B:103:0x01ed, B:110:0x020a, B:111:0x0213), top: B:601:0x01e4, outer: #25 }] */
    /* JADX WARN: Code duplicated, block: B:107:0x01fc  */
    /* JADX WARN: Code duplicated, block: B:108:0x01fd A[Catch: all -> 0x018c, TryCatch #25 {all -> 0x018c, blocks: (B:92:0x01b8, B:100:0x01d8, B:105:0x01f5, B:124:0x0229, B:133:0x0272, B:137:0x02a4, B:139:0x02a8, B:293:0x0546, B:318:0x05fc, B:324:0x061d, B:326:0x0622, B:332:0x063a, B:338:0x065d, B:340:0x0662, B:342:0x066a, B:344:0x0673, B:351:0x0683, B:353:0x068b, B:359:0x06a1, B:372:0x06ed, B:373:0x06f0, B:329:0x062b, B:330:0x0637, B:379:0x06f6, B:380:0x06f9, B:388:0x070b, B:389:0x070e, B:148:0x02ca, B:149:0x02d6, B:150:0x02d7, B:151:0x02e2, B:152:0x02e3, B:153:0x02ef, B:154:0x02f0, B:157:0x02fc, B:168:0x033a, B:171:0x0353, B:174:0x035a, B:175:0x0365, B:176:0x0366, B:178:0x036c, B:186:0x03a0, B:189:0x03a6, B:190:0x03b2, B:196:0x03b8, B:197:0x03bb, B:198:0x03bc, B:200:0x03c4, B:202:0x03ca, B:204:0x03d0, B:211:0x03ed, B:213:0x03f6, B:216:0x040a, B:244:0x045a, B:257:0x047e, B:258:0x048a, B:259:0x048b, B:247:0x0465, B:251:0x046f, B:260:0x04ba, B:261:0x04c6, B:262:0x04c7, B:263:0x04d2, B:219:0x0410, B:220:0x041b, B:226:0x0421, B:227:0x0424, B:228:0x0425, B:229:0x0431, B:235:0x0437, B:236:0x043a, B:237:0x043b, B:239:0x0444, B:242:0x044d, B:243:0x0459, B:273:0x04f1, B:274:0x04f4, B:275:0x04f5, B:276:0x04fd, B:277:0x04fe, B:278:0x050a, B:286:0x051d, B:287:0x0520, B:108:0x01fd, B:109:0x0209, B:117:0x0219, B:118:0x021c, B:75:0x0181, B:333:0x0646, B:335:0x064f, B:369:0x06ea, B:158:0x0308, B:160:0x0311, B:162:0x031b, B:166:0x0332, B:264:0x04d3, B:265:0x04df, B:266:0x04e0, B:267:0x04eb, B:270:0x04ee, B:319:0x060b, B:321:0x0614, B:205:0x03dc, B:207:0x03e5, B:179:0x0386, B:181:0x038f, B:376:0x06f3, B:232:0x0434, B:193:0x03b5, B:294:0x056b, B:298:0x0579, B:300:0x057f, B:302:0x058e, B:304:0x059b, B:306:0x05a3, B:308:0x05ac, B:311:0x05bc, B:312:0x05e7, B:314:0x05ea, B:315:0x05f6, B:316:0x05f7, B:381:0x06fa, B:382:0x0705, B:385:0x0708, B:101:0x01e4, B:103:0x01ed, B:110:0x020a, B:111:0x0213, B:114:0x0216, B:125:0x0235, B:127:0x023e, B:131:0x025a, B:130:0x0251, B:279:0x050b, B:280:0x0517, B:283:0x051a, B:214:0x0402, B:223:0x041e), top: B:598:0x0145, inners: #0, #1, #2, #3, #4, #5, #8, #11, #12, #13, #22, #24, #27, #29, #30, #33, #37, #39 }] */
    /* JADX WARN: Code duplicated, block: B:110:0x020a A[Catch: all -> 0x0214, TRY_ENTER, TryCatch #27 {all -> 0x0214, blocks: (B:101:0x01e4, B:103:0x01ed, B:110:0x020a, B:111:0x0213), top: B:601:0x01e4, outer: #25 }] */
    /* JADX WARN: Code duplicated, block: B:119:0x021d  */
    /* JADX WARN: Code duplicated, block: B:124:0x0229 A[Catch: all -> 0x018c, TRY_ENTER, TRY_LEAVE, TryCatch #25 {all -> 0x018c, blocks: (B:92:0x01b8, B:100:0x01d8, B:105:0x01f5, B:124:0x0229, B:133:0x0272, B:137:0x02a4, B:139:0x02a8, B:293:0x0546, B:318:0x05fc, B:324:0x061d, B:326:0x0622, B:332:0x063a, B:338:0x065d, B:340:0x0662, B:342:0x066a, B:344:0x0673, B:351:0x0683, B:353:0x068b, B:359:0x06a1, B:372:0x06ed, B:373:0x06f0, B:329:0x062b, B:330:0x0637, B:379:0x06f6, B:380:0x06f9, B:388:0x070b, B:389:0x070e, B:148:0x02ca, B:149:0x02d6, B:150:0x02d7, B:151:0x02e2, B:152:0x02e3, B:153:0x02ef, B:154:0x02f0, B:157:0x02fc, B:168:0x033a, B:171:0x0353, B:174:0x035a, B:175:0x0365, B:176:0x0366, B:178:0x036c, B:186:0x03a0, B:189:0x03a6, B:190:0x03b2, B:196:0x03b8, B:197:0x03bb, B:198:0x03bc, B:200:0x03c4, B:202:0x03ca, B:204:0x03d0, B:211:0x03ed, B:213:0x03f6, B:216:0x040a, B:244:0x045a, B:257:0x047e, B:258:0x048a, B:259:0x048b, B:247:0x0465, B:251:0x046f, B:260:0x04ba, B:261:0x04c6, B:262:0x04c7, B:263:0x04d2, B:219:0x0410, B:220:0x041b, B:226:0x0421, B:227:0x0424, B:228:0x0425, B:229:0x0431, B:235:0x0437, B:236:0x043a, B:237:0x043b, B:239:0x0444, B:242:0x044d, B:243:0x0459, B:273:0x04f1, B:274:0x04f4, B:275:0x04f5, B:276:0x04fd, B:277:0x04fe, B:278:0x050a, B:286:0x051d, B:287:0x0520, B:108:0x01fd, B:109:0x0209, B:117:0x0219, B:118:0x021c, B:75:0x0181, B:333:0x0646, B:335:0x064f, B:369:0x06ea, B:158:0x0308, B:160:0x0311, B:162:0x031b, B:166:0x0332, B:264:0x04d3, B:265:0x04df, B:266:0x04e0, B:267:0x04eb, B:270:0x04ee, B:319:0x060b, B:321:0x0614, B:205:0x03dc, B:207:0x03e5, B:179:0x0386, B:181:0x038f, B:376:0x06f3, B:232:0x0434, B:193:0x03b5, B:294:0x056b, B:298:0x0579, B:300:0x057f, B:302:0x058e, B:304:0x059b, B:306:0x05a3, B:308:0x05ac, B:311:0x05bc, B:312:0x05e7, B:314:0x05ea, B:315:0x05f6, B:316:0x05f7, B:381:0x06fa, B:382:0x0705, B:385:0x0708, B:101:0x01e4, B:103:0x01ed, B:110:0x020a, B:111:0x0213, B:114:0x0216, B:125:0x0235, B:127:0x023e, B:131:0x025a, B:130:0x0251, B:279:0x050b, B:280:0x0517, B:283:0x051a, B:214:0x0402, B:223:0x041e), top: B:598:0x0145, inners: #0, #1, #2, #3, #4, #5, #8, #11, #12, #13, #22, #24, #27, #29, #30, #33, #37, #39 }] */
    /* JADX WARN: Code duplicated, block: B:127:0x023e A[Catch: all -> 0x0518, TryCatch #30 {all -> 0x0518, blocks: (B:125:0x0235, B:127:0x023e, B:131:0x025a, B:130:0x0251, B:279:0x050b, B:280:0x0517), top: B:605:0x0235, outer: #25 }] */
    /* JADX WARN: Code duplicated, block: B:129:0x024c  */
    /* JADX WARN: Code duplicated, block: B:130:0x0251 A[Catch: all -> 0x0518, TryCatch #30 {all -> 0x0518, blocks: (B:125:0x0235, B:127:0x023e, B:131:0x025a, B:130:0x0251, B:279:0x050b, B:280:0x0517), top: B:605:0x0235, outer: #25 }] */
    /* JADX WARN: Code duplicated, block: B:135:0x02a0  */
    /* JADX WARN: Code duplicated, block: B:137:0x02a4 A[Catch: all -> 0x018c, TryCatch #25 {all -> 0x018c, blocks: (B:92:0x01b8, B:100:0x01d8, B:105:0x01f5, B:124:0x0229, B:133:0x0272, B:137:0x02a4, B:139:0x02a8, B:293:0x0546, B:318:0x05fc, B:324:0x061d, B:326:0x0622, B:332:0x063a, B:338:0x065d, B:340:0x0662, B:342:0x066a, B:344:0x0673, B:351:0x0683, B:353:0x068b, B:359:0x06a1, B:372:0x06ed, B:373:0x06f0, B:329:0x062b, B:330:0x0637, B:379:0x06f6, B:380:0x06f9, B:388:0x070b, B:389:0x070e, B:148:0x02ca, B:149:0x02d6, B:150:0x02d7, B:151:0x02e2, B:152:0x02e3, B:153:0x02ef, B:154:0x02f0, B:157:0x02fc, B:168:0x033a, B:171:0x0353, B:174:0x035a, B:175:0x0365, B:176:0x0366, B:178:0x036c, B:186:0x03a0, B:189:0x03a6, B:190:0x03b2, B:196:0x03b8, B:197:0x03bb, B:198:0x03bc, B:200:0x03c4, B:202:0x03ca, B:204:0x03d0, B:211:0x03ed, B:213:0x03f6, B:216:0x040a, B:244:0x045a, B:257:0x047e, B:258:0x048a, B:259:0x048b, B:247:0x0465, B:251:0x046f, B:260:0x04ba, B:261:0x04c6, B:262:0x04c7, B:263:0x04d2, B:219:0x0410, B:220:0x041b, B:226:0x0421, B:227:0x0424, B:228:0x0425, B:229:0x0431, B:235:0x0437, B:236:0x043a, B:237:0x043b, B:239:0x0444, B:242:0x044d, B:243:0x0459, B:273:0x04f1, B:274:0x04f4, B:275:0x04f5, B:276:0x04fd, B:277:0x04fe, B:278:0x050a, B:286:0x051d, B:287:0x0520, B:108:0x01fd, B:109:0x0209, B:117:0x0219, B:118:0x021c, B:75:0x0181, B:333:0x0646, B:335:0x064f, B:369:0x06ea, B:158:0x0308, B:160:0x0311, B:162:0x031b, B:166:0x0332, B:264:0x04d3, B:265:0x04df, B:266:0x04e0, B:267:0x04eb, B:270:0x04ee, B:319:0x060b, B:321:0x0614, B:205:0x03dc, B:207:0x03e5, B:179:0x0386, B:181:0x038f, B:376:0x06f3, B:232:0x0434, B:193:0x03b5, B:294:0x056b, B:298:0x0579, B:300:0x057f, B:302:0x058e, B:304:0x059b, B:306:0x05a3, B:308:0x05ac, B:311:0x05bc, B:312:0x05e7, B:314:0x05ea, B:315:0x05f6, B:316:0x05f7, B:381:0x06fa, B:382:0x0705, B:385:0x0708, B:101:0x01e4, B:103:0x01ed, B:110:0x020a, B:111:0x0213, B:114:0x0216, B:125:0x0235, B:127:0x023e, B:131:0x025a, B:130:0x0251, B:279:0x050b, B:280:0x0517, B:283:0x051a, B:214:0x0402, B:223:0x041e), top: B:598:0x0145, inners: #0, #1, #2, #3, #4, #5, #8, #11, #12, #13, #22, #24, #27, #29, #30, #33, #37, #39 }] */
    /* JADX WARN: Code duplicated, block: B:143:0x02b1  */
    /* JADX WARN: Code duplicated, block: B:145:0x02b4  */
    /* JADX WARN: Code duplicated, block: B:147:0x02b8  */
    /* JADX WARN: Code duplicated, block: B:148:0x02ca A[Catch: all -> 0x018c, TryCatch #25 {all -> 0x018c, blocks: (B:92:0x01b8, B:100:0x01d8, B:105:0x01f5, B:124:0x0229, B:133:0x0272, B:137:0x02a4, B:139:0x02a8, B:293:0x0546, B:318:0x05fc, B:324:0x061d, B:326:0x0622, B:332:0x063a, B:338:0x065d, B:340:0x0662, B:342:0x066a, B:344:0x0673, B:351:0x0683, B:353:0x068b, B:359:0x06a1, B:372:0x06ed, B:373:0x06f0, B:329:0x062b, B:330:0x0637, B:379:0x06f6, B:380:0x06f9, B:388:0x070b, B:389:0x070e, B:148:0x02ca, B:149:0x02d6, B:150:0x02d7, B:151:0x02e2, B:152:0x02e3, B:153:0x02ef, B:154:0x02f0, B:157:0x02fc, B:168:0x033a, B:171:0x0353, B:174:0x035a, B:175:0x0365, B:176:0x0366, B:178:0x036c, B:186:0x03a0, B:189:0x03a6, B:190:0x03b2, B:196:0x03b8, B:197:0x03bb, B:198:0x03bc, B:200:0x03c4, B:202:0x03ca, B:204:0x03d0, B:211:0x03ed, B:213:0x03f6, B:216:0x040a, B:244:0x045a, B:257:0x047e, B:258:0x048a, B:259:0x048b, B:247:0x0465, B:251:0x046f, B:260:0x04ba, B:261:0x04c6, B:262:0x04c7, B:263:0x04d2, B:219:0x0410, B:220:0x041b, B:226:0x0421, B:227:0x0424, B:228:0x0425, B:229:0x0431, B:235:0x0437, B:236:0x043a, B:237:0x043b, B:239:0x0444, B:242:0x044d, B:243:0x0459, B:273:0x04f1, B:274:0x04f4, B:275:0x04f5, B:276:0x04fd, B:277:0x04fe, B:278:0x050a, B:286:0x051d, B:287:0x0520, B:108:0x01fd, B:109:0x0209, B:117:0x0219, B:118:0x021c, B:75:0x0181, B:333:0x0646, B:335:0x064f, B:369:0x06ea, B:158:0x0308, B:160:0x0311, B:162:0x031b, B:166:0x0332, B:264:0x04d3, B:265:0x04df, B:266:0x04e0, B:267:0x04eb, B:270:0x04ee, B:319:0x060b, B:321:0x0614, B:205:0x03dc, B:207:0x03e5, B:179:0x0386, B:181:0x038f, B:376:0x06f3, B:232:0x0434, B:193:0x03b5, B:294:0x056b, B:298:0x0579, B:300:0x057f, B:302:0x058e, B:304:0x059b, B:306:0x05a3, B:308:0x05ac, B:311:0x05bc, B:312:0x05e7, B:314:0x05ea, B:315:0x05f6, B:316:0x05f7, B:381:0x06fa, B:382:0x0705, B:385:0x0708, B:101:0x01e4, B:103:0x01ed, B:110:0x020a, B:111:0x0213, B:114:0x0216, B:125:0x0235, B:127:0x023e, B:131:0x025a, B:130:0x0251, B:279:0x050b, B:280:0x0517, B:283:0x051a, B:214:0x0402, B:223:0x041e), top: B:598:0x0145, inners: #0, #1, #2, #3, #4, #5, #8, #11, #12, #13, #22, #24, #27, #29, #30, #33, #37, #39 }] */
    /* JADX WARN: Code duplicated, block: B:150:0x02d7 A[Catch: all -> 0x018c, TryCatch #25 {all -> 0x018c, blocks: (B:92:0x01b8, B:100:0x01d8, B:105:0x01f5, B:124:0x0229, B:133:0x0272, B:137:0x02a4, B:139:0x02a8, B:293:0x0546, B:318:0x05fc, B:324:0x061d, B:326:0x0622, B:332:0x063a, B:338:0x065d, B:340:0x0662, B:342:0x066a, B:344:0x0673, B:351:0x0683, B:353:0x068b, B:359:0x06a1, B:372:0x06ed, B:373:0x06f0, B:329:0x062b, B:330:0x0637, B:379:0x06f6, B:380:0x06f9, B:388:0x070b, B:389:0x070e, B:148:0x02ca, B:149:0x02d6, B:150:0x02d7, B:151:0x02e2, B:152:0x02e3, B:153:0x02ef, B:154:0x02f0, B:157:0x02fc, B:168:0x033a, B:171:0x0353, B:174:0x035a, B:175:0x0365, B:176:0x0366, B:178:0x036c, B:186:0x03a0, B:189:0x03a6, B:190:0x03b2, B:196:0x03b8, B:197:0x03bb, B:198:0x03bc, B:200:0x03c4, B:202:0x03ca, B:204:0x03d0, B:211:0x03ed, B:213:0x03f6, B:216:0x040a, B:244:0x045a, B:257:0x047e, B:258:0x048a, B:259:0x048b, B:247:0x0465, B:251:0x046f, B:260:0x04ba, B:261:0x04c6, B:262:0x04c7, B:263:0x04d2, B:219:0x0410, B:220:0x041b, B:226:0x0421, B:227:0x0424, B:228:0x0425, B:229:0x0431, B:235:0x0437, B:236:0x043a, B:237:0x043b, B:239:0x0444, B:242:0x044d, B:243:0x0459, B:273:0x04f1, B:274:0x04f4, B:275:0x04f5, B:276:0x04fd, B:277:0x04fe, B:278:0x050a, B:286:0x051d, B:287:0x0520, B:108:0x01fd, B:109:0x0209, B:117:0x0219, B:118:0x021c, B:75:0x0181, B:333:0x0646, B:335:0x064f, B:369:0x06ea, B:158:0x0308, B:160:0x0311, B:162:0x031b, B:166:0x0332, B:264:0x04d3, B:265:0x04df, B:266:0x04e0, B:267:0x04eb, B:270:0x04ee, B:319:0x060b, B:321:0x0614, B:205:0x03dc, B:207:0x03e5, B:179:0x0386, B:181:0x038f, B:376:0x06f3, B:232:0x0434, B:193:0x03b5, B:294:0x056b, B:298:0x0579, B:300:0x057f, B:302:0x058e, B:304:0x059b, B:306:0x05a3, B:308:0x05ac, B:311:0x05bc, B:312:0x05e7, B:314:0x05ea, B:315:0x05f6, B:316:0x05f7, B:381:0x06fa, B:382:0x0705, B:385:0x0708, B:101:0x01e4, B:103:0x01ed, B:110:0x020a, B:111:0x0213, B:114:0x0216, B:125:0x0235, B:127:0x023e, B:131:0x025a, B:130:0x0251, B:279:0x050b, B:280:0x0517, B:283:0x051a, B:214:0x0402, B:223:0x041e), top: B:598:0x0145, inners: #0, #1, #2, #3, #4, #5, #8, #11, #12, #13, #22, #24, #27, #29, #30, #33, #37, #39 }] */
    /* JADX WARN: Code duplicated, block: B:152:0x02e3 A[Catch: all -> 0x018c, TryCatch #25 {all -> 0x018c, blocks: (B:92:0x01b8, B:100:0x01d8, B:105:0x01f5, B:124:0x0229, B:133:0x0272, B:137:0x02a4, B:139:0x02a8, B:293:0x0546, B:318:0x05fc, B:324:0x061d, B:326:0x0622, B:332:0x063a, B:338:0x065d, B:340:0x0662, B:342:0x066a, B:344:0x0673, B:351:0x0683, B:353:0x068b, B:359:0x06a1, B:372:0x06ed, B:373:0x06f0, B:329:0x062b, B:330:0x0637, B:379:0x06f6, B:380:0x06f9, B:388:0x070b, B:389:0x070e, B:148:0x02ca, B:149:0x02d6, B:150:0x02d7, B:151:0x02e2, B:152:0x02e3, B:153:0x02ef, B:154:0x02f0, B:157:0x02fc, B:168:0x033a, B:171:0x0353, B:174:0x035a, B:175:0x0365, B:176:0x0366, B:178:0x036c, B:186:0x03a0, B:189:0x03a6, B:190:0x03b2, B:196:0x03b8, B:197:0x03bb, B:198:0x03bc, B:200:0x03c4, B:202:0x03ca, B:204:0x03d0, B:211:0x03ed, B:213:0x03f6, B:216:0x040a, B:244:0x045a, B:257:0x047e, B:258:0x048a, B:259:0x048b, B:247:0x0465, B:251:0x046f, B:260:0x04ba, B:261:0x04c6, B:262:0x04c7, B:263:0x04d2, B:219:0x0410, B:220:0x041b, B:226:0x0421, B:227:0x0424, B:228:0x0425, B:229:0x0431, B:235:0x0437, B:236:0x043a, B:237:0x043b, B:239:0x0444, B:242:0x044d, B:243:0x0459, B:273:0x04f1, B:274:0x04f4, B:275:0x04f5, B:276:0x04fd, B:277:0x04fe, B:278:0x050a, B:286:0x051d, B:287:0x0520, B:108:0x01fd, B:109:0x0209, B:117:0x0219, B:118:0x021c, B:75:0x0181, B:333:0x0646, B:335:0x064f, B:369:0x06ea, B:158:0x0308, B:160:0x0311, B:162:0x031b, B:166:0x0332, B:264:0x04d3, B:265:0x04df, B:266:0x04e0, B:267:0x04eb, B:270:0x04ee, B:319:0x060b, B:321:0x0614, B:205:0x03dc, B:207:0x03e5, B:179:0x0386, B:181:0x038f, B:376:0x06f3, B:232:0x0434, B:193:0x03b5, B:294:0x056b, B:298:0x0579, B:300:0x057f, B:302:0x058e, B:304:0x059b, B:306:0x05a3, B:308:0x05ac, B:311:0x05bc, B:312:0x05e7, B:314:0x05ea, B:315:0x05f6, B:316:0x05f7, B:381:0x06fa, B:382:0x0705, B:385:0x0708, B:101:0x01e4, B:103:0x01ed, B:110:0x020a, B:111:0x0213, B:114:0x0216, B:125:0x0235, B:127:0x023e, B:131:0x025a, B:130:0x0251, B:279:0x050b, B:280:0x0517, B:283:0x051a, B:214:0x0402, B:223:0x041e), top: B:598:0x0145, inners: #0, #1, #2, #3, #4, #5, #8, #11, #12, #13, #22, #24, #27, #29, #30, #33, #37, #39 }] */
    /* JADX WARN: Code duplicated, block: B:154:0x02f0 A[Catch: all -> 0x018c, TryCatch #25 {all -> 0x018c, blocks: (B:92:0x01b8, B:100:0x01d8, B:105:0x01f5, B:124:0x0229, B:133:0x0272, B:137:0x02a4, B:139:0x02a8, B:293:0x0546, B:318:0x05fc, B:324:0x061d, B:326:0x0622, B:332:0x063a, B:338:0x065d, B:340:0x0662, B:342:0x066a, B:344:0x0673, B:351:0x0683, B:353:0x068b, B:359:0x06a1, B:372:0x06ed, B:373:0x06f0, B:329:0x062b, B:330:0x0637, B:379:0x06f6, B:380:0x06f9, B:388:0x070b, B:389:0x070e, B:148:0x02ca, B:149:0x02d6, B:150:0x02d7, B:151:0x02e2, B:152:0x02e3, B:153:0x02ef, B:154:0x02f0, B:157:0x02fc, B:168:0x033a, B:171:0x0353, B:174:0x035a, B:175:0x0365, B:176:0x0366, B:178:0x036c, B:186:0x03a0, B:189:0x03a6, B:190:0x03b2, B:196:0x03b8, B:197:0x03bb, B:198:0x03bc, B:200:0x03c4, B:202:0x03ca, B:204:0x03d0, B:211:0x03ed, B:213:0x03f6, B:216:0x040a, B:244:0x045a, B:257:0x047e, B:258:0x048a, B:259:0x048b, B:247:0x0465, B:251:0x046f, B:260:0x04ba, B:261:0x04c6, B:262:0x04c7, B:263:0x04d2, B:219:0x0410, B:220:0x041b, B:226:0x0421, B:227:0x0424, B:228:0x0425, B:229:0x0431, B:235:0x0437, B:236:0x043a, B:237:0x043b, B:239:0x0444, B:242:0x044d, B:243:0x0459, B:273:0x04f1, B:274:0x04f4, B:275:0x04f5, B:276:0x04fd, B:277:0x04fe, B:278:0x050a, B:286:0x051d, B:287:0x0520, B:108:0x01fd, B:109:0x0209, B:117:0x0219, B:118:0x021c, B:75:0x0181, B:333:0x0646, B:335:0x064f, B:369:0x06ea, B:158:0x0308, B:160:0x0311, B:162:0x031b, B:166:0x0332, B:264:0x04d3, B:265:0x04df, B:266:0x04e0, B:267:0x04eb, B:270:0x04ee, B:319:0x060b, B:321:0x0614, B:205:0x03dc, B:207:0x03e5, B:179:0x0386, B:181:0x038f, B:376:0x06f3, B:232:0x0434, B:193:0x03b5, B:294:0x056b, B:298:0x0579, B:300:0x057f, B:302:0x058e, B:304:0x059b, B:306:0x05a3, B:308:0x05ac, B:311:0x05bc, B:312:0x05e7, B:314:0x05ea, B:315:0x05f6, B:316:0x05f7, B:381:0x06fa, B:382:0x0705, B:385:0x0708, B:101:0x01e4, B:103:0x01ed, B:110:0x020a, B:111:0x0213, B:114:0x0216, B:125:0x0235, B:127:0x023e, B:131:0x025a, B:130:0x0251, B:279:0x050b, B:280:0x0517, B:283:0x051a, B:214:0x0402, B:223:0x041e), top: B:598:0x0145, inners: #0, #1, #2, #3, #4, #5, #8, #11, #12, #13, #22, #24, #27, #29, #30, #33, #37, #39 }] */
    /* JADX WARN: Code duplicated, block: B:156:0x02fa A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:157:0x02fc A[Catch: all -> 0x018c, TRY_LEAVE, TryCatch #25 {all -> 0x018c, blocks: (B:92:0x01b8, B:100:0x01d8, B:105:0x01f5, B:124:0x0229, B:133:0x0272, B:137:0x02a4, B:139:0x02a8, B:293:0x0546, B:318:0x05fc, B:324:0x061d, B:326:0x0622, B:332:0x063a, B:338:0x065d, B:340:0x0662, B:342:0x066a, B:344:0x0673, B:351:0x0683, B:353:0x068b, B:359:0x06a1, B:372:0x06ed, B:373:0x06f0, B:329:0x062b, B:330:0x0637, B:379:0x06f6, B:380:0x06f9, B:388:0x070b, B:389:0x070e, B:148:0x02ca, B:149:0x02d6, B:150:0x02d7, B:151:0x02e2, B:152:0x02e3, B:153:0x02ef, B:154:0x02f0, B:157:0x02fc, B:168:0x033a, B:171:0x0353, B:174:0x035a, B:175:0x0365, B:176:0x0366, B:178:0x036c, B:186:0x03a0, B:189:0x03a6, B:190:0x03b2, B:196:0x03b8, B:197:0x03bb, B:198:0x03bc, B:200:0x03c4, B:202:0x03ca, B:204:0x03d0, B:211:0x03ed, B:213:0x03f6, B:216:0x040a, B:244:0x045a, B:257:0x047e, B:258:0x048a, B:259:0x048b, B:247:0x0465, B:251:0x046f, B:260:0x04ba, B:261:0x04c6, B:262:0x04c7, B:263:0x04d2, B:219:0x0410, B:220:0x041b, B:226:0x0421, B:227:0x0424, B:228:0x0425, B:229:0x0431, B:235:0x0437, B:236:0x043a, B:237:0x043b, B:239:0x0444, B:242:0x044d, B:243:0x0459, B:273:0x04f1, B:274:0x04f4, B:275:0x04f5, B:276:0x04fd, B:277:0x04fe, B:278:0x050a, B:286:0x051d, B:287:0x0520, B:108:0x01fd, B:109:0x0209, B:117:0x0219, B:118:0x021c, B:75:0x0181, B:333:0x0646, B:335:0x064f, B:369:0x06ea, B:158:0x0308, B:160:0x0311, B:162:0x031b, B:166:0x0332, B:264:0x04d3, B:265:0x04df, B:266:0x04e0, B:267:0x04eb, B:270:0x04ee, B:319:0x060b, B:321:0x0614, B:205:0x03dc, B:207:0x03e5, B:179:0x0386, B:181:0x038f, B:376:0x06f3, B:232:0x0434, B:193:0x03b5, B:294:0x056b, B:298:0x0579, B:300:0x057f, B:302:0x058e, B:304:0x059b, B:306:0x05a3, B:308:0x05ac, B:311:0x05bc, B:312:0x05e7, B:314:0x05ea, B:315:0x05f6, B:316:0x05f7, B:381:0x06fa, B:382:0x0705, B:385:0x0708, B:101:0x01e4, B:103:0x01ed, B:110:0x020a, B:111:0x0213, B:114:0x0216, B:125:0x0235, B:127:0x023e, B:131:0x025a, B:130:0x0251, B:279:0x050b, B:280:0x0517, B:283:0x051a, B:214:0x0402, B:223:0x041e), top: B:598:0x0145, inners: #0, #1, #2, #3, #4, #5, #8, #11, #12, #13, #22, #24, #27, #29, #30, #33, #37, #39 }] */
    /* JADX WARN: Code duplicated, block: B:160:0x0311 A[Catch: all -> 0x04ec, TryCatch #2 {all -> 0x04ec, blocks: (B:158:0x0308, B:160:0x0311, B:162:0x031b, B:166:0x0332, B:264:0x04d3, B:265:0x04df, B:266:0x04e0, B:267:0x04eb), top: B:556:0x0308, outer: #25 }] */
    /* JADX WARN: Code duplicated, block: B:162:0x031b A[Catch: all -> 0x04ec, TryCatch #2 {all -> 0x04ec, blocks: (B:158:0x0308, B:160:0x0311, B:162:0x031b, B:166:0x0332, B:264:0x04d3, B:265:0x04df, B:266:0x04e0, B:267:0x04eb), top: B:556:0x0308, outer: #25 }] */
    /* JADX WARN: Code duplicated, block: B:164:0x032f  */
    /* JADX WARN: Code duplicated, block: B:165:0x0331  */
    /* JADX WARN: Code duplicated, block: B:178:0x036c A[Catch: all -> 0x018c, TRY_LEAVE, TryCatch #25 {all -> 0x018c, blocks: (B:92:0x01b8, B:100:0x01d8, B:105:0x01f5, B:124:0x0229, B:133:0x0272, B:137:0x02a4, B:139:0x02a8, B:293:0x0546, B:318:0x05fc, B:324:0x061d, B:326:0x0622, B:332:0x063a, B:338:0x065d, B:340:0x0662, B:342:0x066a, B:344:0x0673, B:351:0x0683, B:353:0x068b, B:359:0x06a1, B:372:0x06ed, B:373:0x06f0, B:329:0x062b, B:330:0x0637, B:379:0x06f6, B:380:0x06f9, B:388:0x070b, B:389:0x070e, B:148:0x02ca, B:149:0x02d6, B:150:0x02d7, B:151:0x02e2, B:152:0x02e3, B:153:0x02ef, B:154:0x02f0, B:157:0x02fc, B:168:0x033a, B:171:0x0353, B:174:0x035a, B:175:0x0365, B:176:0x0366, B:178:0x036c, B:186:0x03a0, B:189:0x03a6, B:190:0x03b2, B:196:0x03b8, B:197:0x03bb, B:198:0x03bc, B:200:0x03c4, B:202:0x03ca, B:204:0x03d0, B:211:0x03ed, B:213:0x03f6, B:216:0x040a, B:244:0x045a, B:257:0x047e, B:258:0x048a, B:259:0x048b, B:247:0x0465, B:251:0x046f, B:260:0x04ba, B:261:0x04c6, B:262:0x04c7, B:263:0x04d2, B:219:0x0410, B:220:0x041b, B:226:0x0421, B:227:0x0424, B:228:0x0425, B:229:0x0431, B:235:0x0437, B:236:0x043a, B:237:0x043b, B:239:0x0444, B:242:0x044d, B:243:0x0459, B:273:0x04f1, B:274:0x04f4, B:275:0x04f5, B:276:0x04fd, B:277:0x04fe, B:278:0x050a, B:286:0x051d, B:287:0x0520, B:108:0x01fd, B:109:0x0209, B:117:0x0219, B:118:0x021c, B:75:0x0181, B:333:0x0646, B:335:0x064f, B:369:0x06ea, B:158:0x0308, B:160:0x0311, B:162:0x031b, B:166:0x0332, B:264:0x04d3, B:265:0x04df, B:266:0x04e0, B:267:0x04eb, B:270:0x04ee, B:319:0x060b, B:321:0x0614, B:205:0x03dc, B:207:0x03e5, B:179:0x0386, B:181:0x038f, B:376:0x06f3, B:232:0x0434, B:193:0x03b5, B:294:0x056b, B:298:0x0579, B:300:0x057f, B:302:0x058e, B:304:0x059b, B:306:0x05a3, B:308:0x05ac, B:311:0x05bc, B:312:0x05e7, B:314:0x05ea, B:315:0x05f6, B:316:0x05f7, B:381:0x06fa, B:382:0x0705, B:385:0x0708, B:101:0x01e4, B:103:0x01ed, B:110:0x020a, B:111:0x0213, B:114:0x0216, B:125:0x0235, B:127:0x023e, B:131:0x025a, B:130:0x0251, B:279:0x050b, B:280:0x0517, B:283:0x051a, B:214:0x0402, B:223:0x041e), top: B:598:0x0145, inners: #0, #1, #2, #3, #4, #5, #8, #11, #12, #13, #22, #24, #27, #29, #30, #33, #37, #39 }] */
    /* JADX WARN: Code duplicated, block: B:184:0x039e  */
    /* JADX WARN: Code duplicated, block: B:188:0x03a5  */
    /* JADX WARN: Code duplicated, block: B:189:0x03a6 A[Catch: all -> 0x018c, TryCatch #25 {all -> 0x018c, blocks: (B:92:0x01b8, B:100:0x01d8, B:105:0x01f5, B:124:0x0229, B:133:0x0272, B:137:0x02a4, B:139:0x02a8, B:293:0x0546, B:318:0x05fc, B:324:0x061d, B:326:0x0622, B:332:0x063a, B:338:0x065d, B:340:0x0662, B:342:0x066a, B:344:0x0673, B:351:0x0683, B:353:0x068b, B:359:0x06a1, B:372:0x06ed, B:373:0x06f0, B:329:0x062b, B:330:0x0637, B:379:0x06f6, B:380:0x06f9, B:388:0x070b, B:389:0x070e, B:148:0x02ca, B:149:0x02d6, B:150:0x02d7, B:151:0x02e2, B:152:0x02e3, B:153:0x02ef, B:154:0x02f0, B:157:0x02fc, B:168:0x033a, B:171:0x0353, B:174:0x035a, B:175:0x0365, B:176:0x0366, B:178:0x036c, B:186:0x03a0, B:189:0x03a6, B:190:0x03b2, B:196:0x03b8, B:197:0x03bb, B:198:0x03bc, B:200:0x03c4, B:202:0x03ca, B:204:0x03d0, B:211:0x03ed, B:213:0x03f6, B:216:0x040a, B:244:0x045a, B:257:0x047e, B:258:0x048a, B:259:0x048b, B:247:0x0465, B:251:0x046f, B:260:0x04ba, B:261:0x04c6, B:262:0x04c7, B:263:0x04d2, B:219:0x0410, B:220:0x041b, B:226:0x0421, B:227:0x0424, B:228:0x0425, B:229:0x0431, B:235:0x0437, B:236:0x043a, B:237:0x043b, B:239:0x0444, B:242:0x044d, B:243:0x0459, B:273:0x04f1, B:274:0x04f4, B:275:0x04f5, B:276:0x04fd, B:277:0x04fe, B:278:0x050a, B:286:0x051d, B:287:0x0520, B:108:0x01fd, B:109:0x0209, B:117:0x0219, B:118:0x021c, B:75:0x0181, B:333:0x0646, B:335:0x064f, B:369:0x06ea, B:158:0x0308, B:160:0x0311, B:162:0x031b, B:166:0x0332, B:264:0x04d3, B:265:0x04df, B:266:0x04e0, B:267:0x04eb, B:270:0x04ee, B:319:0x060b, B:321:0x0614, B:205:0x03dc, B:207:0x03e5, B:179:0x0386, B:181:0x038f, B:376:0x06f3, B:232:0x0434, B:193:0x03b5, B:294:0x056b, B:298:0x0579, B:300:0x057f, B:302:0x058e, B:304:0x059b, B:306:0x05a3, B:308:0x05ac, B:311:0x05bc, B:312:0x05e7, B:314:0x05ea, B:315:0x05f6, B:316:0x05f7, B:381:0x06fa, B:382:0x0705, B:385:0x0708, B:101:0x01e4, B:103:0x01ed, B:110:0x020a, B:111:0x0213, B:114:0x0216, B:125:0x0235, B:127:0x023e, B:131:0x025a, B:130:0x0251, B:279:0x050b, B:280:0x0517, B:283:0x051a, B:214:0x0402, B:223:0x041e), top: B:598:0x0145, inners: #0, #1, #2, #3, #4, #5, #8, #11, #12, #13, #22, #24, #27, #29, #30, #33, #37, #39 }] */
    /* JADX WARN: Code duplicated, block: B:237:0x043b A[Catch: all -> 0x018c, TryCatch #25 {all -> 0x018c, blocks: (B:92:0x01b8, B:100:0x01d8, B:105:0x01f5, B:124:0x0229, B:133:0x0272, B:137:0x02a4, B:139:0x02a8, B:293:0x0546, B:318:0x05fc, B:324:0x061d, B:326:0x0622, B:332:0x063a, B:338:0x065d, B:340:0x0662, B:342:0x066a, B:344:0x0673, B:351:0x0683, B:353:0x068b, B:359:0x06a1, B:372:0x06ed, B:373:0x06f0, B:329:0x062b, B:330:0x0637, B:379:0x06f6, B:380:0x06f9, B:388:0x070b, B:389:0x070e, B:148:0x02ca, B:149:0x02d6, B:150:0x02d7, B:151:0x02e2, B:152:0x02e3, B:153:0x02ef, B:154:0x02f0, B:157:0x02fc, B:168:0x033a, B:171:0x0353, B:174:0x035a, B:175:0x0365, B:176:0x0366, B:178:0x036c, B:186:0x03a0, B:189:0x03a6, B:190:0x03b2, B:196:0x03b8, B:197:0x03bb, B:198:0x03bc, B:200:0x03c4, B:202:0x03ca, B:204:0x03d0, B:211:0x03ed, B:213:0x03f6, B:216:0x040a, B:244:0x045a, B:257:0x047e, B:258:0x048a, B:259:0x048b, B:247:0x0465, B:251:0x046f, B:260:0x04ba, B:261:0x04c6, B:262:0x04c7, B:263:0x04d2, B:219:0x0410, B:220:0x041b, B:226:0x0421, B:227:0x0424, B:228:0x0425, B:229:0x0431, B:235:0x0437, B:236:0x043a, B:237:0x043b, B:239:0x0444, B:242:0x044d, B:243:0x0459, B:273:0x04f1, B:274:0x04f4, B:275:0x04f5, B:276:0x04fd, B:277:0x04fe, B:278:0x050a, B:286:0x051d, B:287:0x0520, B:108:0x01fd, B:109:0x0209, B:117:0x0219, B:118:0x021c, B:75:0x0181, B:333:0x0646, B:335:0x064f, B:369:0x06ea, B:158:0x0308, B:160:0x0311, B:162:0x031b, B:166:0x0332, B:264:0x04d3, B:265:0x04df, B:266:0x04e0, B:267:0x04eb, B:270:0x04ee, B:319:0x060b, B:321:0x0614, B:205:0x03dc, B:207:0x03e5, B:179:0x0386, B:181:0x038f, B:376:0x06f3, B:232:0x0434, B:193:0x03b5, B:294:0x056b, B:298:0x0579, B:300:0x057f, B:302:0x058e, B:304:0x059b, B:306:0x05a3, B:308:0x05ac, B:311:0x05bc, B:312:0x05e7, B:314:0x05ea, B:315:0x05f6, B:316:0x05f7, B:381:0x06fa, B:382:0x0705, B:385:0x0708, B:101:0x01e4, B:103:0x01ed, B:110:0x020a, B:111:0x0213, B:114:0x0216, B:125:0x0235, B:127:0x023e, B:131:0x025a, B:130:0x0251, B:279:0x050b, B:280:0x0517, B:283:0x051a, B:214:0x0402, B:223:0x041e), top: B:598:0x0145, inners: #0, #1, #2, #3, #4, #5, #8, #11, #12, #13, #22, #24, #27, #29, #30, #33, #37, #39 }] */
    /* JADX WARN: Code duplicated, block: B:239:0x0444 A[Catch: all -> 0x018c, TryCatch #25 {all -> 0x018c, blocks: (B:92:0x01b8, B:100:0x01d8, B:105:0x01f5, B:124:0x0229, B:133:0x0272, B:137:0x02a4, B:139:0x02a8, B:293:0x0546, B:318:0x05fc, B:324:0x061d, B:326:0x0622, B:332:0x063a, B:338:0x065d, B:340:0x0662, B:342:0x066a, B:344:0x0673, B:351:0x0683, B:353:0x068b, B:359:0x06a1, B:372:0x06ed, B:373:0x06f0, B:329:0x062b, B:330:0x0637, B:379:0x06f6, B:380:0x06f9, B:388:0x070b, B:389:0x070e, B:148:0x02ca, B:149:0x02d6, B:150:0x02d7, B:151:0x02e2, B:152:0x02e3, B:153:0x02ef, B:154:0x02f0, B:157:0x02fc, B:168:0x033a, B:171:0x0353, B:174:0x035a, B:175:0x0365, B:176:0x0366, B:178:0x036c, B:186:0x03a0, B:189:0x03a6, B:190:0x03b2, B:196:0x03b8, B:197:0x03bb, B:198:0x03bc, B:200:0x03c4, B:202:0x03ca, B:204:0x03d0, B:211:0x03ed, B:213:0x03f6, B:216:0x040a, B:244:0x045a, B:257:0x047e, B:258:0x048a, B:259:0x048b, B:247:0x0465, B:251:0x046f, B:260:0x04ba, B:261:0x04c6, B:262:0x04c7, B:263:0x04d2, B:219:0x0410, B:220:0x041b, B:226:0x0421, B:227:0x0424, B:228:0x0425, B:229:0x0431, B:235:0x0437, B:236:0x043a, B:237:0x043b, B:239:0x0444, B:242:0x044d, B:243:0x0459, B:273:0x04f1, B:274:0x04f4, B:275:0x04f5, B:276:0x04fd, B:277:0x04fe, B:278:0x050a, B:286:0x051d, B:287:0x0520, B:108:0x01fd, B:109:0x0209, B:117:0x0219, B:118:0x021c, B:75:0x0181, B:333:0x0646, B:335:0x064f, B:369:0x06ea, B:158:0x0308, B:160:0x0311, B:162:0x031b, B:166:0x0332, B:264:0x04d3, B:265:0x04df, B:266:0x04e0, B:267:0x04eb, B:270:0x04ee, B:319:0x060b, B:321:0x0614, B:205:0x03dc, B:207:0x03e5, B:179:0x0386, B:181:0x038f, B:376:0x06f3, B:232:0x0434, B:193:0x03b5, B:294:0x056b, B:298:0x0579, B:300:0x057f, B:302:0x058e, B:304:0x059b, B:306:0x05a3, B:308:0x05ac, B:311:0x05bc, B:312:0x05e7, B:314:0x05ea, B:315:0x05f6, B:316:0x05f7, B:381:0x06fa, B:382:0x0705, B:385:0x0708, B:101:0x01e4, B:103:0x01ed, B:110:0x020a, B:111:0x0213, B:114:0x0216, B:125:0x0235, B:127:0x023e, B:131:0x025a, B:130:0x0251, B:279:0x050b, B:280:0x0517, B:283:0x051a, B:214:0x0402, B:223:0x041e), top: B:598:0x0145, inners: #0, #1, #2, #3, #4, #5, #8, #11, #12, #13, #22, #24, #27, #29, #30, #33, #37, #39 }] */
    /* JADX WARN: Code duplicated, block: B:246:0x0462  */
    /* JADX WARN: Code duplicated, block: B:247:0x0465 A[Catch: all -> 0x018c, TryCatch #25 {all -> 0x018c, blocks: (B:92:0x01b8, B:100:0x01d8, B:105:0x01f5, B:124:0x0229, B:133:0x0272, B:137:0x02a4, B:139:0x02a8, B:293:0x0546, B:318:0x05fc, B:324:0x061d, B:326:0x0622, B:332:0x063a, B:338:0x065d, B:340:0x0662, B:342:0x066a, B:344:0x0673, B:351:0x0683, B:353:0x068b, B:359:0x06a1, B:372:0x06ed, B:373:0x06f0, B:329:0x062b, B:330:0x0637, B:379:0x06f6, B:380:0x06f9, B:388:0x070b, B:389:0x070e, B:148:0x02ca, B:149:0x02d6, B:150:0x02d7, B:151:0x02e2, B:152:0x02e3, B:153:0x02ef, B:154:0x02f0, B:157:0x02fc, B:168:0x033a, B:171:0x0353, B:174:0x035a, B:175:0x0365, B:176:0x0366, B:178:0x036c, B:186:0x03a0, B:189:0x03a6, B:190:0x03b2, B:196:0x03b8, B:197:0x03bb, B:198:0x03bc, B:200:0x03c4, B:202:0x03ca, B:204:0x03d0, B:211:0x03ed, B:213:0x03f6, B:216:0x040a, B:244:0x045a, B:257:0x047e, B:258:0x048a, B:259:0x048b, B:247:0x0465, B:251:0x046f, B:260:0x04ba, B:261:0x04c6, B:262:0x04c7, B:263:0x04d2, B:219:0x0410, B:220:0x041b, B:226:0x0421, B:227:0x0424, B:228:0x0425, B:229:0x0431, B:235:0x0437, B:236:0x043a, B:237:0x043b, B:239:0x0444, B:242:0x044d, B:243:0x0459, B:273:0x04f1, B:274:0x04f4, B:275:0x04f5, B:276:0x04fd, B:277:0x04fe, B:278:0x050a, B:286:0x051d, B:287:0x0520, B:108:0x01fd, B:109:0x0209, B:117:0x0219, B:118:0x021c, B:75:0x0181, B:333:0x0646, B:335:0x064f, B:369:0x06ea, B:158:0x0308, B:160:0x0311, B:162:0x031b, B:166:0x0332, B:264:0x04d3, B:265:0x04df, B:266:0x04e0, B:267:0x04eb, B:270:0x04ee, B:319:0x060b, B:321:0x0614, B:205:0x03dc, B:207:0x03e5, B:179:0x0386, B:181:0x038f, B:376:0x06f3, B:232:0x0434, B:193:0x03b5, B:294:0x056b, B:298:0x0579, B:300:0x057f, B:302:0x058e, B:304:0x059b, B:306:0x05a3, B:308:0x05ac, B:311:0x05bc, B:312:0x05e7, B:314:0x05ea, B:315:0x05f6, B:316:0x05f7, B:381:0x06fa, B:382:0x0705, B:385:0x0708, B:101:0x01e4, B:103:0x01ed, B:110:0x020a, B:111:0x0213, B:114:0x0216, B:125:0x0235, B:127:0x023e, B:131:0x025a, B:130:0x0251, B:279:0x050b, B:280:0x0517, B:283:0x051a, B:214:0x0402, B:223:0x041e), top: B:598:0x0145, inners: #0, #1, #2, #3, #4, #5, #8, #11, #12, #13, #22, #24, #27, #29, #30, #33, #37, #39 }] */
    /* JADX WARN: Code duplicated, block: B:249:0x046b  */
    /* JADX WARN: Code duplicated, block: B:251:0x046f A[Catch: all -> 0x018c, TryCatch #25 {all -> 0x018c, blocks: (B:92:0x01b8, B:100:0x01d8, B:105:0x01f5, B:124:0x0229, B:133:0x0272, B:137:0x02a4, B:139:0x02a8, B:293:0x0546, B:318:0x05fc, B:324:0x061d, B:326:0x0622, B:332:0x063a, B:338:0x065d, B:340:0x0662, B:342:0x066a, B:344:0x0673, B:351:0x0683, B:353:0x068b, B:359:0x06a1, B:372:0x06ed, B:373:0x06f0, B:329:0x062b, B:330:0x0637, B:379:0x06f6, B:380:0x06f9, B:388:0x070b, B:389:0x070e, B:148:0x02ca, B:149:0x02d6, B:150:0x02d7, B:151:0x02e2, B:152:0x02e3, B:153:0x02ef, B:154:0x02f0, B:157:0x02fc, B:168:0x033a, B:171:0x0353, B:174:0x035a, B:175:0x0365, B:176:0x0366, B:178:0x036c, B:186:0x03a0, B:189:0x03a6, B:190:0x03b2, B:196:0x03b8, B:197:0x03bb, B:198:0x03bc, B:200:0x03c4, B:202:0x03ca, B:204:0x03d0, B:211:0x03ed, B:213:0x03f6, B:216:0x040a, B:244:0x045a, B:257:0x047e, B:258:0x048a, B:259:0x048b, B:247:0x0465, B:251:0x046f, B:260:0x04ba, B:261:0x04c6, B:262:0x04c7, B:263:0x04d2, B:219:0x0410, B:220:0x041b, B:226:0x0421, B:227:0x0424, B:228:0x0425, B:229:0x0431, B:235:0x0437, B:236:0x043a, B:237:0x043b, B:239:0x0444, B:242:0x044d, B:243:0x0459, B:273:0x04f1, B:274:0x04f4, B:275:0x04f5, B:276:0x04fd, B:277:0x04fe, B:278:0x050a, B:286:0x051d, B:287:0x0520, B:108:0x01fd, B:109:0x0209, B:117:0x0219, B:118:0x021c, B:75:0x0181, B:333:0x0646, B:335:0x064f, B:369:0x06ea, B:158:0x0308, B:160:0x0311, B:162:0x031b, B:166:0x0332, B:264:0x04d3, B:265:0x04df, B:266:0x04e0, B:267:0x04eb, B:270:0x04ee, B:319:0x060b, B:321:0x0614, B:205:0x03dc, B:207:0x03e5, B:179:0x0386, B:181:0x038f, B:376:0x06f3, B:232:0x0434, B:193:0x03b5, B:294:0x056b, B:298:0x0579, B:300:0x057f, B:302:0x058e, B:304:0x059b, B:306:0x05a3, B:308:0x05ac, B:311:0x05bc, B:312:0x05e7, B:314:0x05ea, B:315:0x05f6, B:316:0x05f7, B:381:0x06fa, B:382:0x0705, B:385:0x0708, B:101:0x01e4, B:103:0x01ed, B:110:0x020a, B:111:0x0213, B:114:0x0216, B:125:0x0235, B:127:0x023e, B:131:0x025a, B:130:0x0251, B:279:0x050b, B:280:0x0517, B:283:0x051a, B:214:0x0402, B:223:0x041e), top: B:598:0x0145, inners: #0, #1, #2, #3, #4, #5, #8, #11, #12, #13, #22, #24, #27, #29, #30, #33, #37, #39 }] */
    /* JADX WARN: Code duplicated, block: B:254:0x0479  */
    /* JADX WARN: Code duplicated, block: B:260:0x04ba A[Catch: all -> 0x018c, TryCatch #25 {all -> 0x018c, blocks: (B:92:0x01b8, B:100:0x01d8, B:105:0x01f5, B:124:0x0229, B:133:0x0272, B:137:0x02a4, B:139:0x02a8, B:293:0x0546, B:318:0x05fc, B:324:0x061d, B:326:0x0622, B:332:0x063a, B:338:0x065d, B:340:0x0662, B:342:0x066a, B:344:0x0673, B:351:0x0683, B:353:0x068b, B:359:0x06a1, B:372:0x06ed, B:373:0x06f0, B:329:0x062b, B:330:0x0637, B:379:0x06f6, B:380:0x06f9, B:388:0x070b, B:389:0x070e, B:148:0x02ca, B:149:0x02d6, B:150:0x02d7, B:151:0x02e2, B:152:0x02e3, B:153:0x02ef, B:154:0x02f0, B:157:0x02fc, B:168:0x033a, B:171:0x0353, B:174:0x035a, B:175:0x0365, B:176:0x0366, B:178:0x036c, B:186:0x03a0, B:189:0x03a6, B:190:0x03b2, B:196:0x03b8, B:197:0x03bb, B:198:0x03bc, B:200:0x03c4, B:202:0x03ca, B:204:0x03d0, B:211:0x03ed, B:213:0x03f6, B:216:0x040a, B:244:0x045a, B:257:0x047e, B:258:0x048a, B:259:0x048b, B:247:0x0465, B:251:0x046f, B:260:0x04ba, B:261:0x04c6, B:262:0x04c7, B:263:0x04d2, B:219:0x0410, B:220:0x041b, B:226:0x0421, B:227:0x0424, B:228:0x0425, B:229:0x0431, B:235:0x0437, B:236:0x043a, B:237:0x043b, B:239:0x0444, B:242:0x044d, B:243:0x0459, B:273:0x04f1, B:274:0x04f4, B:275:0x04f5, B:276:0x04fd, B:277:0x04fe, B:278:0x050a, B:286:0x051d, B:287:0x0520, B:108:0x01fd, B:109:0x0209, B:117:0x0219, B:118:0x021c, B:75:0x0181, B:333:0x0646, B:335:0x064f, B:369:0x06ea, B:158:0x0308, B:160:0x0311, B:162:0x031b, B:166:0x0332, B:264:0x04d3, B:265:0x04df, B:266:0x04e0, B:267:0x04eb, B:270:0x04ee, B:319:0x060b, B:321:0x0614, B:205:0x03dc, B:207:0x03e5, B:179:0x0386, B:181:0x038f, B:376:0x06f3, B:232:0x0434, B:193:0x03b5, B:294:0x056b, B:298:0x0579, B:300:0x057f, B:302:0x058e, B:304:0x059b, B:306:0x05a3, B:308:0x05ac, B:311:0x05bc, B:312:0x05e7, B:314:0x05ea, B:315:0x05f6, B:316:0x05f7, B:381:0x06fa, B:382:0x0705, B:385:0x0708, B:101:0x01e4, B:103:0x01ed, B:110:0x020a, B:111:0x0213, B:114:0x0216, B:125:0x0235, B:127:0x023e, B:131:0x025a, B:130:0x0251, B:279:0x050b, B:280:0x0517, B:283:0x051a, B:214:0x0402, B:223:0x041e), top: B:598:0x0145, inners: #0, #1, #2, #3, #4, #5, #8, #11, #12, #13, #22, #24, #27, #29, #30, #33, #37, #39 }] */
    /* JADX WARN: Code duplicated, block: B:262:0x04c7 A[Catch: all -> 0x018c, TryCatch #25 {all -> 0x018c, blocks: (B:92:0x01b8, B:100:0x01d8, B:105:0x01f5, B:124:0x0229, B:133:0x0272, B:137:0x02a4, B:139:0x02a8, B:293:0x0546, B:318:0x05fc, B:324:0x061d, B:326:0x0622, B:332:0x063a, B:338:0x065d, B:340:0x0662, B:342:0x066a, B:344:0x0673, B:351:0x0683, B:353:0x068b, B:359:0x06a1, B:372:0x06ed, B:373:0x06f0, B:329:0x062b, B:330:0x0637, B:379:0x06f6, B:380:0x06f9, B:388:0x070b, B:389:0x070e, B:148:0x02ca, B:149:0x02d6, B:150:0x02d7, B:151:0x02e2, B:152:0x02e3, B:153:0x02ef, B:154:0x02f0, B:157:0x02fc, B:168:0x033a, B:171:0x0353, B:174:0x035a, B:175:0x0365, B:176:0x0366, B:178:0x036c, B:186:0x03a0, B:189:0x03a6, B:190:0x03b2, B:196:0x03b8, B:197:0x03bb, B:198:0x03bc, B:200:0x03c4, B:202:0x03ca, B:204:0x03d0, B:211:0x03ed, B:213:0x03f6, B:216:0x040a, B:244:0x045a, B:257:0x047e, B:258:0x048a, B:259:0x048b, B:247:0x0465, B:251:0x046f, B:260:0x04ba, B:261:0x04c6, B:262:0x04c7, B:263:0x04d2, B:219:0x0410, B:220:0x041b, B:226:0x0421, B:227:0x0424, B:228:0x0425, B:229:0x0431, B:235:0x0437, B:236:0x043a, B:237:0x043b, B:239:0x0444, B:242:0x044d, B:243:0x0459, B:273:0x04f1, B:274:0x04f4, B:275:0x04f5, B:276:0x04fd, B:277:0x04fe, B:278:0x050a, B:286:0x051d, B:287:0x0520, B:108:0x01fd, B:109:0x0209, B:117:0x0219, B:118:0x021c, B:75:0x0181, B:333:0x0646, B:335:0x064f, B:369:0x06ea, B:158:0x0308, B:160:0x0311, B:162:0x031b, B:166:0x0332, B:264:0x04d3, B:265:0x04df, B:266:0x04e0, B:267:0x04eb, B:270:0x04ee, B:319:0x060b, B:321:0x0614, B:205:0x03dc, B:207:0x03e5, B:179:0x0386, B:181:0x038f, B:376:0x06f3, B:232:0x0434, B:193:0x03b5, B:294:0x056b, B:298:0x0579, B:300:0x057f, B:302:0x058e, B:304:0x059b, B:306:0x05a3, B:308:0x05ac, B:311:0x05bc, B:312:0x05e7, B:314:0x05ea, B:315:0x05f6, B:316:0x05f7, B:381:0x06fa, B:382:0x0705, B:385:0x0708, B:101:0x01e4, B:103:0x01ed, B:110:0x020a, B:111:0x0213, B:114:0x0216, B:125:0x0235, B:127:0x023e, B:131:0x025a, B:130:0x0251, B:279:0x050b, B:280:0x0517, B:283:0x051a, B:214:0x0402, B:223:0x041e), top: B:598:0x0145, inners: #0, #1, #2, #3, #4, #5, #8, #11, #12, #13, #22, #24, #27, #29, #30, #33, #37, #39 }] */
    /* JADX WARN: Code duplicated, block: B:264:0x04d3 A[Catch: all -> 0x04ec, TRY_ENTER, TryCatch #2 {all -> 0x04ec, blocks: (B:158:0x0308, B:160:0x0311, B:162:0x031b, B:166:0x0332, B:264:0x04d3, B:265:0x04df, B:266:0x04e0, B:267:0x04eb), top: B:556:0x0308, outer: #25 }] */
    /* JADX WARN: Code duplicated, block: B:266:0x04e0 A[Catch: all -> 0x04ec, TryCatch #2 {all -> 0x04ec, blocks: (B:158:0x0308, B:160:0x0311, B:162:0x031b, B:166:0x0332, B:264:0x04d3, B:265:0x04df, B:266:0x04e0, B:267:0x04eb), top: B:556:0x0308, outer: #25 }] */
    /* JADX WARN: Code duplicated, block: B:275:0x04f5 A[Catch: all -> 0x018c, TryCatch #25 {all -> 0x018c, blocks: (B:92:0x01b8, B:100:0x01d8, B:105:0x01f5, B:124:0x0229, B:133:0x0272, B:137:0x02a4, B:139:0x02a8, B:293:0x0546, B:318:0x05fc, B:324:0x061d, B:326:0x0622, B:332:0x063a, B:338:0x065d, B:340:0x0662, B:342:0x066a, B:344:0x0673, B:351:0x0683, B:353:0x068b, B:359:0x06a1, B:372:0x06ed, B:373:0x06f0, B:329:0x062b, B:330:0x0637, B:379:0x06f6, B:380:0x06f9, B:388:0x070b, B:389:0x070e, B:148:0x02ca, B:149:0x02d6, B:150:0x02d7, B:151:0x02e2, B:152:0x02e3, B:153:0x02ef, B:154:0x02f0, B:157:0x02fc, B:168:0x033a, B:171:0x0353, B:174:0x035a, B:175:0x0365, B:176:0x0366, B:178:0x036c, B:186:0x03a0, B:189:0x03a6, B:190:0x03b2, B:196:0x03b8, B:197:0x03bb, B:198:0x03bc, B:200:0x03c4, B:202:0x03ca, B:204:0x03d0, B:211:0x03ed, B:213:0x03f6, B:216:0x040a, B:244:0x045a, B:257:0x047e, B:258:0x048a, B:259:0x048b, B:247:0x0465, B:251:0x046f, B:260:0x04ba, B:261:0x04c6, B:262:0x04c7, B:263:0x04d2, B:219:0x0410, B:220:0x041b, B:226:0x0421, B:227:0x0424, B:228:0x0425, B:229:0x0431, B:235:0x0437, B:236:0x043a, B:237:0x043b, B:239:0x0444, B:242:0x044d, B:243:0x0459, B:273:0x04f1, B:274:0x04f4, B:275:0x04f5, B:276:0x04fd, B:277:0x04fe, B:278:0x050a, B:286:0x051d, B:287:0x0520, B:108:0x01fd, B:109:0x0209, B:117:0x0219, B:118:0x021c, B:75:0x0181, B:333:0x0646, B:335:0x064f, B:369:0x06ea, B:158:0x0308, B:160:0x0311, B:162:0x031b, B:166:0x0332, B:264:0x04d3, B:265:0x04df, B:266:0x04e0, B:267:0x04eb, B:270:0x04ee, B:319:0x060b, B:321:0x0614, B:205:0x03dc, B:207:0x03e5, B:179:0x0386, B:181:0x038f, B:376:0x06f3, B:232:0x0434, B:193:0x03b5, B:294:0x056b, B:298:0x0579, B:300:0x057f, B:302:0x058e, B:304:0x059b, B:306:0x05a3, B:308:0x05ac, B:311:0x05bc, B:312:0x05e7, B:314:0x05ea, B:315:0x05f6, B:316:0x05f7, B:381:0x06fa, B:382:0x0705, B:385:0x0708, B:101:0x01e4, B:103:0x01ed, B:110:0x020a, B:111:0x0213, B:114:0x0216, B:125:0x0235, B:127:0x023e, B:131:0x025a, B:130:0x0251, B:279:0x050b, B:280:0x0517, B:283:0x051a, B:214:0x0402, B:223:0x041e), top: B:598:0x0145, inners: #0, #1, #2, #3, #4, #5, #8, #11, #12, #13, #22, #24, #27, #29, #30, #33, #37, #39 }] */
    /* JADX WARN: Code duplicated, block: B:277:0x04fe A[Catch: all -> 0x018c, TryCatch #25 {all -> 0x018c, blocks: (B:92:0x01b8, B:100:0x01d8, B:105:0x01f5, B:124:0x0229, B:133:0x0272, B:137:0x02a4, B:139:0x02a8, B:293:0x0546, B:318:0x05fc, B:324:0x061d, B:326:0x0622, B:332:0x063a, B:338:0x065d, B:340:0x0662, B:342:0x066a, B:344:0x0673, B:351:0x0683, B:353:0x068b, B:359:0x06a1, B:372:0x06ed, B:373:0x06f0, B:329:0x062b, B:330:0x0637, B:379:0x06f6, B:380:0x06f9, B:388:0x070b, B:389:0x070e, B:148:0x02ca, B:149:0x02d6, B:150:0x02d7, B:151:0x02e2, B:152:0x02e3, B:153:0x02ef, B:154:0x02f0, B:157:0x02fc, B:168:0x033a, B:171:0x0353, B:174:0x035a, B:175:0x0365, B:176:0x0366, B:178:0x036c, B:186:0x03a0, B:189:0x03a6, B:190:0x03b2, B:196:0x03b8, B:197:0x03bb, B:198:0x03bc, B:200:0x03c4, B:202:0x03ca, B:204:0x03d0, B:211:0x03ed, B:213:0x03f6, B:216:0x040a, B:244:0x045a, B:257:0x047e, B:258:0x048a, B:259:0x048b, B:247:0x0465, B:251:0x046f, B:260:0x04ba, B:261:0x04c6, B:262:0x04c7, B:263:0x04d2, B:219:0x0410, B:220:0x041b, B:226:0x0421, B:227:0x0424, B:228:0x0425, B:229:0x0431, B:235:0x0437, B:236:0x043a, B:237:0x043b, B:239:0x0444, B:242:0x044d, B:243:0x0459, B:273:0x04f1, B:274:0x04f4, B:275:0x04f5, B:276:0x04fd, B:277:0x04fe, B:278:0x050a, B:286:0x051d, B:287:0x0520, B:108:0x01fd, B:109:0x0209, B:117:0x0219, B:118:0x021c, B:75:0x0181, B:333:0x0646, B:335:0x064f, B:369:0x06ea, B:158:0x0308, B:160:0x0311, B:162:0x031b, B:166:0x0332, B:264:0x04d3, B:265:0x04df, B:266:0x04e0, B:267:0x04eb, B:270:0x04ee, B:319:0x060b, B:321:0x0614, B:205:0x03dc, B:207:0x03e5, B:179:0x0386, B:181:0x038f, B:376:0x06f3, B:232:0x0434, B:193:0x03b5, B:294:0x056b, B:298:0x0579, B:300:0x057f, B:302:0x058e, B:304:0x059b, B:306:0x05a3, B:308:0x05ac, B:311:0x05bc, B:312:0x05e7, B:314:0x05ea, B:315:0x05f6, B:316:0x05f7, B:381:0x06fa, B:382:0x0705, B:385:0x0708, B:101:0x01e4, B:103:0x01ed, B:110:0x020a, B:111:0x0213, B:114:0x0216, B:125:0x0235, B:127:0x023e, B:131:0x025a, B:130:0x0251, B:279:0x050b, B:280:0x0517, B:283:0x051a, B:214:0x0402, B:223:0x041e), top: B:598:0x0145, inners: #0, #1, #2, #3, #4, #5, #8, #11, #12, #13, #22, #24, #27, #29, #30, #33, #37, #39 }] */
    /* JADX WARN: Code duplicated, block: B:279:0x050b A[Catch: all -> 0x0518, TRY_ENTER, TryCatch #30 {all -> 0x0518, blocks: (B:125:0x0235, B:127:0x023e, B:131:0x025a, B:130:0x0251, B:279:0x050b, B:280:0x0517), top: B:605:0x0235, outer: #25 }] */
    /* JADX WARN: Code duplicated, block: B:288:0x0521  */
    /* JADX WARN: Code duplicated, block: B:293:0x0546 A[Catch: all -> 0x018c, TRY_ENTER, TRY_LEAVE, TryCatch #25 {all -> 0x018c, blocks: (B:92:0x01b8, B:100:0x01d8, B:105:0x01f5, B:124:0x0229, B:133:0x0272, B:137:0x02a4, B:139:0x02a8, B:293:0x0546, B:318:0x05fc, B:324:0x061d, B:326:0x0622, B:332:0x063a, B:338:0x065d, B:340:0x0662, B:342:0x066a, B:344:0x0673, B:351:0x0683, B:353:0x068b, B:359:0x06a1, B:372:0x06ed, B:373:0x06f0, B:329:0x062b, B:330:0x0637, B:379:0x06f6, B:380:0x06f9, B:388:0x070b, B:389:0x070e, B:148:0x02ca, B:149:0x02d6, B:150:0x02d7, B:151:0x02e2, B:152:0x02e3, B:153:0x02ef, B:154:0x02f0, B:157:0x02fc, B:168:0x033a, B:171:0x0353, B:174:0x035a, B:175:0x0365, B:176:0x0366, B:178:0x036c, B:186:0x03a0, B:189:0x03a6, B:190:0x03b2, B:196:0x03b8, B:197:0x03bb, B:198:0x03bc, B:200:0x03c4, B:202:0x03ca, B:204:0x03d0, B:211:0x03ed, B:213:0x03f6, B:216:0x040a, B:244:0x045a, B:257:0x047e, B:258:0x048a, B:259:0x048b, B:247:0x0465, B:251:0x046f, B:260:0x04ba, B:261:0x04c6, B:262:0x04c7, B:263:0x04d2, B:219:0x0410, B:220:0x041b, B:226:0x0421, B:227:0x0424, B:228:0x0425, B:229:0x0431, B:235:0x0437, B:236:0x043a, B:237:0x043b, B:239:0x0444, B:242:0x044d, B:243:0x0459, B:273:0x04f1, B:274:0x04f4, B:275:0x04f5, B:276:0x04fd, B:277:0x04fe, B:278:0x050a, B:286:0x051d, B:287:0x0520, B:108:0x01fd, B:109:0x0209, B:117:0x0219, B:118:0x021c, B:75:0x0181, B:333:0x0646, B:335:0x064f, B:369:0x06ea, B:158:0x0308, B:160:0x0311, B:162:0x031b, B:166:0x0332, B:264:0x04d3, B:265:0x04df, B:266:0x04e0, B:267:0x04eb, B:270:0x04ee, B:319:0x060b, B:321:0x0614, B:205:0x03dc, B:207:0x03e5, B:179:0x0386, B:181:0x038f, B:376:0x06f3, B:232:0x0434, B:193:0x03b5, B:294:0x056b, B:298:0x0579, B:300:0x057f, B:302:0x058e, B:304:0x059b, B:306:0x05a3, B:308:0x05ac, B:311:0x05bc, B:312:0x05e7, B:314:0x05ea, B:315:0x05f6, B:316:0x05f7, B:381:0x06fa, B:382:0x0705, B:385:0x0708, B:101:0x01e4, B:103:0x01ed, B:110:0x020a, B:111:0x0213, B:114:0x0216, B:125:0x0235, B:127:0x023e, B:131:0x025a, B:130:0x0251, B:279:0x050b, B:280:0x0517, B:283:0x051a, B:214:0x0402, B:223:0x041e), top: B:598:0x0145, inners: #0, #1, #2, #3, #4, #5, #8, #11, #12, #13, #22, #24, #27, #29, #30, #33, #37, #39 }] */
    /* JADX WARN: Code duplicated, block: B:296:0x0575  */
    /* JADX WARN: Code duplicated, block: B:34:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:36:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:390:0x070f  */
    /* JADX WARN: Code duplicated, block: B:394:0x0749 A[Catch: all -> 0x0bb4, LOOP:1: B:394:0x0749->B:441:0x08a3, LOOP_START, PHI: r6 r7 r8 r9 r10 r11 r12 r13 r14 r15 r25 r43
      0x0749: PHI (r6v10 android.database.Cursor) = (r6v9 android.database.Cursor), (r6v36 android.database.Cursor) binds: [B:393:0x0747, B:441:0x08a3] A[DONT_GENERATE, DONT_INLINE]
      0x0749: PHI (r7v4 java.lang.String) = (r7v3 java.lang.String), (r7v25 java.lang.String) binds: [B:393:0x0747, B:441:0x08a3] A[DONT_GENERATE, DONT_INLINE]
      0x0749: PHI (r8v2 java.lang.String) = (r8v1 java.lang.String), (r8v15 java.lang.String) binds: [B:393:0x0747, B:441:0x08a3] A[DONT_GENERATE, DONT_INLINE]
      0x0749: PHI (r9v10 android.database.Cursor) = (r9v8 android.database.Cursor), (r9v37 android.database.Cursor) binds: [B:393:0x0747, B:441:0x08a3] A[DONT_GENERATE, DONT_INLINE]
      0x0749: PHI (r10v15 ??) = (r10v32 ??), (r10v33 ??) binds: [B:393:0x0747, B:441:0x08a3] A[DONT_GENERATE, DONT_INLINE]
      0x0749: PHI (r11v4 java.lang.String) = (r11v3 java.lang.String), (r11v19 java.lang.String) binds: [B:393:0x0747, B:441:0x08a3] A[DONT_GENERATE, DONT_INLINE]
      0x0749: PHI (r12v19 java.lang.String) = (r12v14 java.lang.String), (r12v29 java.lang.String) binds: [B:393:0x0747, B:441:0x08a3] A[DONT_GENERATE, DONT_INLINE]
      0x0749: PHI (r13v1 ??) = (r13v12 ??), (r13v10 ??) binds: [B:393:0x0747, B:441:0x08a3] A[DONT_GENERATE, DONT_INLINE]
      0x0749: PHI (r14v3 java.lang.String) = (r14v2 java.lang.String), (r14v9 java.lang.String) binds: [B:393:0x0747, B:441:0x08a3] A[DONT_GENERATE, DONT_INLINE]
      0x0749: PHI (r15v4 java.lang.String) = (r15v3 java.lang.String), (r15v10 java.lang.String) binds: [B:393:0x0747, B:441:0x08a3] A[DONT_GENERATE, DONT_INLINE]
      0x0749: PHI (r25v1 java.lang.String) = (r25v0 java.lang.String), (r25v2 java.lang.String) binds: [B:393:0x0747, B:441:0x08a3] A[DONT_GENERATE, DONT_INLINE]
      0x0749: PHI (r43v2 java.lang.String) = (r43v1 java.lang.String), (r43v3 java.lang.String) binds: [B:393:0x0747, B:441:0x08a3] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #10 {all -> 0x0bb4, blocks: (B:392:0x0740, B:394:0x0749), top: B:571:0x0740 }] */
    /* JADX WARN: Code duplicated, block: B:396:0x074f  */
    /* JADX WARN: Code duplicated, block: B:39:0x00f3 A[Catch: all -> 0x00dd, TRY_ENTER, TRY_LEAVE, TryCatch #38 {all -> 0x00dd, blocks: (B:25:0x00ca, B:28:0x00d1, B:29:0x00dc, B:39:0x00f3, B:45:0x0105, B:47:0x010f, B:49:0x0119, B:53:0x0127, B:54:0x0133, B:65:0x0157, B:68:0x015e), top: B:620:0x00ca }] */
    /* JADX WARN: Code duplicated, block: B:401:0x0774 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:404:0x0785  */
    /* JADX WARN: Code duplicated, block: B:412:0x07c1 A[Catch: all -> 0x085b, TRY_LEAVE, TryCatch #35 {all -> 0x085b, blocks: (B:410:0x07bb, B:412:0x07c1), top: B:614:0x07bb }] */
    /* JADX WARN: Code duplicated, block: B:415:0x07ff  */
    /* JADX WARN: Code duplicated, block: B:418:0x0822  */
    /* JADX WARN: Code duplicated, block: B:419:0x0824  */
    /* JADX WARN: Code duplicated, block: B:41:0x00f9 A[Catch: all -> 0x0c01, TRY_ENTER, TryCatch #19 {all -> 0x0c01, blocks: (B:23:0x00b4, B:32:0x00e2, B:37:0x00ed, B:42:0x00fe, B:55:0x0134, B:57:0x013a, B:71:0x0167, B:41:0x00f9), top: B:588:0x00b4 }] */
    /* JADX WARN: Code duplicated, block: B:422:0x082c  */
    /* JADX WARN: Code duplicated, block: B:423:0x082e  */
    /* JADX WARN: Code duplicated, block: B:427:0x0862  */
    /* JADX WARN: Code duplicated, block: B:430:0x0881 A[Catch: all -> 0x08ba, TRY_LEAVE, TryCatch #15 {all -> 0x08ba, blocks: (B:414:0x07fb, B:416:0x0818, B:420:0x0826, B:424:0x0830, B:428:0x087b, B:430:0x0881), top: B:581:0x07fb }] */
    /* JADX WARN: Code duplicated, block: B:440:0x08a1  */
    /* JADX WARN: Code duplicated, block: B:457:0x08ea A[Catch: all -> 0x0bff, TRY_ENTER, TryCatch #17 {all -> 0x0bff, blocks: (B:531:0x0bbb, B:532:0x0bbe, B:454:0x08e1, B:457:0x08ea, B:459:0x08f2, B:463:0x090b, B:466:0x0953, B:468:0x0962, B:470:0x09e3, B:472:0x09f2, B:484:0x0a16, B:489:0x0a23, B:491:0x0a29, B:493:0x0a40, B:497:0x0a6f, B:499:0x0a76, B:502:0x0a90, B:516:0x0b9b, B:517:0x0b9e, B:492:0x0a35, B:471:0x09ef, B:467:0x095f, B:481:0x0a0a, B:482:0x0a0d, B:533:0x0bbf, B:534:0x0bcb, B:535:0x0bcc, B:536:0x0bd9, B:537:0x0bda, B:538:0x0bf2, B:541:0x0bf6, B:542:0x0bfe, B:528:0x0bb8, B:460:0x08fe, B:462:0x0907, B:474:0x09fb, B:475:0x0a04, B:478:0x0a07, B:494:0x0a62, B:496:0x0a6b, B:509:0x0b8c, B:510:0x0b95, B:513:0x0b98), top: B:585:0x0138, inners: #14, #21, #23, #28, #31 }] */
    /* JADX WARN: Code duplicated, block: B:45:0x0105 A[Catch: all -> 0x00dd, TRY_ENTER, TryCatch #38 {all -> 0x00dd, blocks: (B:25:0x00ca, B:28:0x00d1, B:29:0x00dc, B:39:0x00f3, B:45:0x0105, B:47:0x010f, B:49:0x0119, B:53:0x0127, B:54:0x0133, B:65:0x0157, B:68:0x015e), top: B:620:0x00ca }] */
    /* JADX WARN: Code duplicated, block: B:483:0x0a0e  */
    /* JADX WARN: Code duplicated, block: B:486:0x0a1c A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:488:0x0a21  */
    /* JADX WARN: Code duplicated, block: B:491:0x0a29 A[Catch: all -> 0x0bff, TryCatch #17 {all -> 0x0bff, blocks: (B:531:0x0bbb, B:532:0x0bbe, B:454:0x08e1, B:457:0x08ea, B:459:0x08f2, B:463:0x090b, B:466:0x0953, B:468:0x0962, B:470:0x09e3, B:472:0x09f2, B:484:0x0a16, B:489:0x0a23, B:491:0x0a29, B:493:0x0a40, B:497:0x0a6f, B:499:0x0a76, B:502:0x0a90, B:516:0x0b9b, B:517:0x0b9e, B:492:0x0a35, B:471:0x09ef, B:467:0x095f, B:481:0x0a0a, B:482:0x0a0d, B:533:0x0bbf, B:534:0x0bcb, B:535:0x0bcc, B:536:0x0bd9, B:537:0x0bda, B:538:0x0bf2, B:541:0x0bf6, B:542:0x0bfe, B:528:0x0bb8, B:460:0x08fe, B:462:0x0907, B:474:0x09fb, B:475:0x0a04, B:478:0x0a07, B:494:0x0a62, B:496:0x0a6b, B:509:0x0b8c, B:510:0x0b95, B:513:0x0b98), top: B:585:0x0138, inners: #14, #21, #23, #28, #31 }] */
    /* JADX WARN: Code duplicated, block: B:492:0x0a35 A[Catch: all -> 0x0bff, TryCatch #17 {all -> 0x0bff, blocks: (B:531:0x0bbb, B:532:0x0bbe, B:454:0x08e1, B:457:0x08ea, B:459:0x08f2, B:463:0x090b, B:466:0x0953, B:468:0x0962, B:470:0x09e3, B:472:0x09f2, B:484:0x0a16, B:489:0x0a23, B:491:0x0a29, B:493:0x0a40, B:497:0x0a6f, B:499:0x0a76, B:502:0x0a90, B:516:0x0b9b, B:517:0x0b9e, B:492:0x0a35, B:471:0x09ef, B:467:0x095f, B:481:0x0a0a, B:482:0x0a0d, B:533:0x0bbf, B:534:0x0bcb, B:535:0x0bcc, B:536:0x0bd9, B:537:0x0bda, B:538:0x0bf2, B:541:0x0bf6, B:542:0x0bfe, B:528:0x0bb8, B:460:0x08fe, B:462:0x0907, B:474:0x09fb, B:475:0x0a04, B:478:0x0a07, B:494:0x0a62, B:496:0x0a6b, B:509:0x0b8c, B:510:0x0b95, B:513:0x0b98), top: B:585:0x0138, inners: #14, #21, #23, #28, #31 }] */
    /* JADX WARN: Code duplicated, block: B:496:0x0a6b A[Catch: all -> 0x0b96, TRY_LEAVE, TryCatch #28 {all -> 0x0b96, blocks: (B:494:0x0a62, B:496:0x0a6b, B:509:0x0b8c, B:510:0x0b95), top: B:602:0x0a62, outer: #17 }] */
    /* JADX WARN: Code duplicated, block: B:499:0x0a76 A[Catch: all -> 0x0bff, TryCatch #17 {all -> 0x0bff, blocks: (B:531:0x0bbb, B:532:0x0bbe, B:454:0x08e1, B:457:0x08ea, B:459:0x08f2, B:463:0x090b, B:466:0x0953, B:468:0x0962, B:470:0x09e3, B:472:0x09f2, B:484:0x0a16, B:489:0x0a23, B:491:0x0a29, B:493:0x0a40, B:497:0x0a6f, B:499:0x0a76, B:502:0x0a90, B:516:0x0b9b, B:517:0x0b9e, B:492:0x0a35, B:471:0x09ef, B:467:0x095f, B:481:0x0a0a, B:482:0x0a0d, B:533:0x0bbf, B:534:0x0bcb, B:535:0x0bcc, B:536:0x0bd9, B:537:0x0bda, B:538:0x0bf2, B:541:0x0bf6, B:542:0x0bfe, B:528:0x0bb8, B:460:0x08fe, B:462:0x0907, B:474:0x09fb, B:475:0x0a04, B:478:0x0a07, B:494:0x0a62, B:496:0x0a6b, B:509:0x0b8c, B:510:0x0b95, B:513:0x0b98), top: B:585:0x0138, inners: #14, #21, #23, #28, #31 }] */
    /* JADX WARN: Code duplicated, block: B:501:0x0a8e  */
    /* JADX WARN: Code duplicated, block: B:509:0x0b8c A[Catch: all -> 0x0b96, TRY_ENTER, TryCatch #28 {all -> 0x0b96, blocks: (B:494:0x0a62, B:496:0x0a6b, B:509:0x0b8c, B:510:0x0b95), top: B:602:0x0a62, outer: #17 }] */
    /* JADX WARN: Code duplicated, block: B:520:0x0ba3  */
    /* JADX WARN: Code duplicated, block: B:533:0x0bbf A[Catch: all -> 0x0bff, TryCatch #17 {all -> 0x0bff, blocks: (B:531:0x0bbb, B:532:0x0bbe, B:454:0x08e1, B:457:0x08ea, B:459:0x08f2, B:463:0x090b, B:466:0x0953, B:468:0x0962, B:470:0x09e3, B:472:0x09f2, B:484:0x0a16, B:489:0x0a23, B:491:0x0a29, B:493:0x0a40, B:497:0x0a6f, B:499:0x0a76, B:502:0x0a90, B:516:0x0b9b, B:517:0x0b9e, B:492:0x0a35, B:471:0x09ef, B:467:0x095f, B:481:0x0a0a, B:482:0x0a0d, B:533:0x0bbf, B:534:0x0bcb, B:535:0x0bcc, B:536:0x0bd9, B:537:0x0bda, B:538:0x0bf2, B:541:0x0bf6, B:542:0x0bfe, B:528:0x0bb8, B:460:0x08fe, B:462:0x0907, B:474:0x09fb, B:475:0x0a04, B:478:0x0a07, B:494:0x0a62, B:496:0x0a6b, B:509:0x0b8c, B:510:0x0b95, B:513:0x0b98), top: B:585:0x0138, inners: #14, #21, #23, #28, #31 }] */
    /* JADX WARN: Code duplicated, block: B:535:0x0bcc A[Catch: all -> 0x0bff, TryCatch #17 {all -> 0x0bff, blocks: (B:531:0x0bbb, B:532:0x0bbe, B:454:0x08e1, B:457:0x08ea, B:459:0x08f2, B:463:0x090b, B:466:0x0953, B:468:0x0962, B:470:0x09e3, B:472:0x09f2, B:484:0x0a16, B:489:0x0a23, B:491:0x0a29, B:493:0x0a40, B:497:0x0a6f, B:499:0x0a76, B:502:0x0a90, B:516:0x0b9b, B:517:0x0b9e, B:492:0x0a35, B:471:0x09ef, B:467:0x095f, B:481:0x0a0a, B:482:0x0a0d, B:533:0x0bbf, B:534:0x0bcb, B:535:0x0bcc, B:536:0x0bd9, B:537:0x0bda, B:538:0x0bf2, B:541:0x0bf6, B:542:0x0bfe, B:528:0x0bb8, B:460:0x08fe, B:462:0x0907, B:474:0x09fb, B:475:0x0a04, B:478:0x0a07, B:494:0x0a62, B:496:0x0a6b, B:509:0x0b8c, B:510:0x0b95, B:513:0x0b98), top: B:585:0x0138, inners: #14, #21, #23, #28, #31 }] */
    /* JADX WARN: Code duplicated, block: B:537:0x0bda A[Catch: all -> 0x0bff, TryCatch #17 {all -> 0x0bff, blocks: (B:531:0x0bbb, B:532:0x0bbe, B:454:0x08e1, B:457:0x08ea, B:459:0x08f2, B:463:0x090b, B:466:0x0953, B:468:0x0962, B:470:0x09e3, B:472:0x09f2, B:484:0x0a16, B:489:0x0a23, B:491:0x0a29, B:493:0x0a40, B:497:0x0a6f, B:499:0x0a76, B:502:0x0a90, B:516:0x0b9b, B:517:0x0b9e, B:492:0x0a35, B:471:0x09ef, B:467:0x095f, B:481:0x0a0a, B:482:0x0a0d, B:533:0x0bbf, B:534:0x0bcb, B:535:0x0bcc, B:536:0x0bd9, B:537:0x0bda, B:538:0x0bf2, B:541:0x0bf6, B:542:0x0bfe, B:528:0x0bb8, B:460:0x08fe, B:462:0x0907, B:474:0x09fb, B:475:0x0a04, B:478:0x0a07, B:494:0x0a62, B:496:0x0a6b, B:509:0x0b8c, B:510:0x0b95, B:513:0x0b98), top: B:585:0x0138, inners: #14, #21, #23, #28, #31 }] */
    /* JADX WARN: Code duplicated, block: B:541:0x0bf6 A[Catch: all -> 0x0bff, TryCatch #17 {all -> 0x0bff, blocks: (B:531:0x0bbb, B:532:0x0bbe, B:454:0x08e1, B:457:0x08ea, B:459:0x08f2, B:463:0x090b, B:466:0x0953, B:468:0x0962, B:470:0x09e3, B:472:0x09f2, B:484:0x0a16, B:489:0x0a23, B:491:0x0a29, B:493:0x0a40, B:497:0x0a6f, B:499:0x0a76, B:502:0x0a90, B:516:0x0b9b, B:517:0x0b9e, B:492:0x0a35, B:471:0x09ef, B:467:0x095f, B:481:0x0a0a, B:482:0x0a0d, B:533:0x0bbf, B:534:0x0bcb, B:535:0x0bcc, B:536:0x0bd9, B:537:0x0bda, B:538:0x0bf2, B:541:0x0bf6, B:542:0x0bfe, B:528:0x0bb8, B:460:0x08fe, B:462:0x0907, B:474:0x09fb, B:475:0x0a04, B:478:0x0a07, B:494:0x0a62, B:496:0x0a6b, B:509:0x0b8c, B:510:0x0b95, B:513:0x0b98), top: B:585:0x0138, inners: #14, #21, #23, #28, #31 }] */
    /* JADX WARN: Code duplicated, block: B:55:0x0134 A[Catch: all -> 0x0c01, TRY_ENTER, TryCatch #19 {all -> 0x0c01, blocks: (B:23:0x00b4, B:32:0x00e2, B:37:0x00ed, B:42:0x00fe, B:55:0x0134, B:57:0x013a, B:71:0x0167, B:41:0x00f9), top: B:588:0x00b4 }] */
    /* JADX WARN: Code duplicated, block: B:57:0x013a A[Catch: all -> 0x0c01, TRY_LEAVE, TryCatch #19 {all -> 0x0c01, blocks: (B:23:0x00b4, B:32:0x00e2, B:37:0x00ed, B:42:0x00fe, B:55:0x0134, B:57:0x013a, B:71:0x0167, B:41:0x00f9), top: B:588:0x00b4 }] */
    /* JADX WARN: Code duplicated, block: B:614:0x07bb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:633:0x0786 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:85:0x019c A[Catch: all -> 0x0bf3, TryCatch #9 {all -> 0x0bf3, blocks: (B:83:0x0194, B:85:0x019c, B:89:0x01a6, B:95:0x01c0, B:97:0x01ce, B:121:0x0221, B:290:0x053a, B:391:0x071c), top: B:569:0x0194 }] */
    /* JADX WARN: Code duplicated, block: B:87:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:88:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:91:0x01b6 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:92:0x01b8 A[Catch: all -> 0x018c, TRY_ENTER, TRY_LEAVE, TryCatch #25 {all -> 0x018c, blocks: (B:92:0x01b8, B:100:0x01d8, B:105:0x01f5, B:124:0x0229, B:133:0x0272, B:137:0x02a4, B:139:0x02a8, B:293:0x0546, B:318:0x05fc, B:324:0x061d, B:326:0x0622, B:332:0x063a, B:338:0x065d, B:340:0x0662, B:342:0x066a, B:344:0x0673, B:351:0x0683, B:353:0x068b, B:359:0x06a1, B:372:0x06ed, B:373:0x06f0, B:329:0x062b, B:330:0x0637, B:379:0x06f6, B:380:0x06f9, B:388:0x070b, B:389:0x070e, B:148:0x02ca, B:149:0x02d6, B:150:0x02d7, B:151:0x02e2, B:152:0x02e3, B:153:0x02ef, B:154:0x02f0, B:157:0x02fc, B:168:0x033a, B:171:0x0353, B:174:0x035a, B:175:0x0365, B:176:0x0366, B:178:0x036c, B:186:0x03a0, B:189:0x03a6, B:190:0x03b2, B:196:0x03b8, B:197:0x03bb, B:198:0x03bc, B:200:0x03c4, B:202:0x03ca, B:204:0x03d0, B:211:0x03ed, B:213:0x03f6, B:216:0x040a, B:244:0x045a, B:257:0x047e, B:258:0x048a, B:259:0x048b, B:247:0x0465, B:251:0x046f, B:260:0x04ba, B:261:0x04c6, B:262:0x04c7, B:263:0x04d2, B:219:0x0410, B:220:0x041b, B:226:0x0421, B:227:0x0424, B:228:0x0425, B:229:0x0431, B:235:0x0437, B:236:0x043a, B:237:0x043b, B:239:0x0444, B:242:0x044d, B:243:0x0459, B:273:0x04f1, B:274:0x04f4, B:275:0x04f5, B:276:0x04fd, B:277:0x04fe, B:278:0x050a, B:286:0x051d, B:287:0x0520, B:108:0x01fd, B:109:0x0209, B:117:0x0219, B:118:0x021c, B:75:0x0181, B:333:0x0646, B:335:0x064f, B:369:0x06ea, B:158:0x0308, B:160:0x0311, B:162:0x031b, B:166:0x0332, B:264:0x04d3, B:265:0x04df, B:266:0x04e0, B:267:0x04eb, B:270:0x04ee, B:319:0x060b, B:321:0x0614, B:205:0x03dc, B:207:0x03e5, B:179:0x0386, B:181:0x038f, B:376:0x06f3, B:232:0x0434, B:193:0x03b5, B:294:0x056b, B:298:0x0579, B:300:0x057f, B:302:0x058e, B:304:0x059b, B:306:0x05a3, B:308:0x05ac, B:311:0x05bc, B:312:0x05e7, B:314:0x05ea, B:315:0x05f6, B:316:0x05f7, B:381:0x06fa, B:382:0x0705, B:385:0x0708, B:101:0x01e4, B:103:0x01ed, B:110:0x020a, B:111:0x0213, B:114:0x0216, B:125:0x0235, B:127:0x023e, B:131:0x025a, B:130:0x0251, B:279:0x050b, B:280:0x0517, B:283:0x051a, B:214:0x0402, B:223:0x041e), top: B:598:0x0145, inners: #0, #1, #2, #3, #4, #5, #8, #11, #12, #13, #22, #24, #27, #29, #30, #33, #37, #39 }] */
    /* JADX WARN: Code duplicated, block: B:94:0x01be  */
    /* JADX WARN: Code duplicated, block: B:97:0x01ce A[Catch: all -> 0x0bf3, TRY_LEAVE, TryCatch #9 {all -> 0x0bf3, blocks: (B:83:0x0194, B:85:0x019c, B:89:0x01a6, B:95:0x01c0, B:97:0x01ce, B:121:0x0221, B:290:0x053a, B:391:0x071c), top: B:569:0x0194 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:178:0x036c, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:293:0x0546, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:499:0x0a76, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:537:0x0bda, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v11 */
    /* JADX WARN: Type inference failed for: r10v13, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r10v14 */
    /* JADX WARN: Type inference failed for: r10v15, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r10v26 */
    /* JADX WARN: Type inference failed for: r10v27 */
    /* JADX WARN: Type inference failed for: r10v28 */
    /* JADX WARN: Type inference failed for: r10v32 */
    /* JADX WARN: Type inference failed for: r10v33 */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v15 */
    /* JADX WARN: Type inference failed for: r12v16 */
    /* JADX WARN: Type inference failed for: r12v17 */
    /* JADX WARN: Type inference failed for: r12v18 */
    /* JADX WARN: Type inference failed for: r12v2, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r12v20, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r12v21 */
    /* JADX WARN: Type inference failed for: r12v22 */
    /* JADX WARN: Type inference failed for: r12v23 */
    /* JADX WARN: Type inference failed for: r12v24 */
    /* JADX WARN: Type inference failed for: r12v26, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v30 */
    /* JADX WARN: Type inference failed for: r12v31, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r12v32 */
    /* JADX WARN: Type inference failed for: r12v4 */
    /* JADX WARN: Type inference failed for: r12v40 */
    /* JADX WARN: Type inference failed for: r12v41 */
    /* JADX WARN: Type inference failed for: r12v42 */
    /* JADX WARN: Type inference failed for: r12v43 */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r12v7 */
    /* JADX WARN: Type inference failed for: r12v8 */
    /* JADX WARN: Type inference failed for: r13v0, types: [com.hazem.dailystore.data.InvoiceRepository] */
    /* JADX WARN: Type inference failed for: r13v1, types: [com.hazem.dailystore.data.InvoiceRepository] */
    /* JADX WARN: Type inference failed for: r13v10 */
    /* JADX WARN: Type inference failed for: r13v12 */
    /* JADX WARN: Type inference failed for: r1v151, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r1v152, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r1v6, types: [com.hazem.dailystore.data.SyncProtocolRepository] */
    /* JADX WARN: Type inference failed for: r2v54, types: [android.content.ContentValues] */
    /* JADX WARN: Type inference failed for: r2v63, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r2v65, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r51v0, types: [com.hazem.dailystore.data.InvoiceRepository] */
    /* JADX WARN: Type inference failed for: r58v10 */
    /* JADX WARN: Type inference failed for: r58v2 */
    /* JADX WARN: Type inference failed for: r58v3 */
    /* JADX WARN: Type inference failed for: r58v4 */
    /* JADX WARN: Type inference failed for: r58v5 */
    /* JADX WARN: Type inference failed for: r58v6 */
    /* JADX WARN: Type inference failed for: r58v7 */
    /* JADX WARN: Type inference failed for: r58v8 */
    /* JADX WARN: Type inference failed for: r58v9 */
    /* JADX WARN: Type inference failed for: r7v22, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v61 */
    /* JADX WARN: Type inference failed for: r8v17, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v7, types: [android.content.ContentValues] */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v12, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v30, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v36, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v38, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v42 */
    public final boolean postInvoice(String type, String actorUserId, String invoiceId, String operationId, String cashAccountId, String occurredAt, String operatingDate) throws Throwable {
        ?? r1;
        ?? r12;
        Throwable th;
        String str;
        ExistingOperation existingOperationFindOperation;
        String strInvoiceStatus;
        int iHashCode;
        ?? r10;
        String str2;
        String strTimestamp;
        String str3;
        String strOperatingDateForTimestamp;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        Object obj;
        Object obj2;
        CashSaleReceipt cashSaleReceipt;
        String strInvoiceWarehouse;
        String str9;
        String str10;
        String str11;
        Cursor cursorRawQuery;
        Cursor cursor;
        Throwable th2;
        Cursor cursor2;
        ?? r13;
        String str12;
        Cursor cursor3;
        String str13;
        String str14;
        String str15;
        String str16;
        String str17;
        String str18;
        ?? r9;
        ?? r7;
        String str19;
        String str20;
        Cursor cursorRawQuery2;
        Cursor cursor4;
        String str21;
        Cursor cursor5;
        ?? r14;
        ?? r15;
        String string;
        String string2;
        Cursor cursor6;
        String str22;
        double d;
        long j;
        boolean zProductTracksInventory;
        ?? r58;
        String str23;
        String str24;
        ?? r59;
        char c;
        String str25;
        String str26;
        double d2;
        String str27;
        String str28;
        String str29;
        ?? r510;
        ?? r11;
        double dStockQuantity;
        Cursor cursorRawQuery3;
        int count;
        Throwable th3;
        String string3;
        Throwable th4;
        JSONObject jSONObject;
        String strOptString;
        String strOptString2;
        Cursor cursorRawQuery4;
        Cursor cursor7;
        String string4;
        int i;
        String str30;
        String str31;
        long j2;
        long j3;
        Cursor cursorRawQuery5;
        Cursor cursor8;
        boolean z;
        String str32;
        String str33;
        long jUsdCentsToSyp;
        Cursor cursorRawQuery6;
        Cursor cursor9;
        boolean z2;
        String str34;
        boolean z3;
        Cursor cursorRawQuery7;
        Cursor cursor10;
        long j4;
        ?? r16 = this;
        ?? r17 = invoiceId;
        if (StringsKt.isBlank((CharSequence) r17) || StringsKt.isBlank(operationId)) {
            throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u063a\u064a\u0631 \u0645\u0643\u062a\u0645\u0644\u0629".toString());
        }
        SQLiteDatabase writableDatabase = r16.database.getWritableDatabase();
        String str35 = "SELECT id,product_id,quantity,unit_cost_usd_cents FROM ";
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        String str36 = "PURCHASE";
        String str37 = "SELECT product_id,quantity FROM ";
        String str38 = Intrinsics.areEqual(type, "PURCHASE") ? "purchase_invoices" : "sales_invoices";
        String str39 = Intrinsics.areEqual(type, "PURCHASE") ? "purchase_items" : "sales_items";
        String str40 = Intrinsics.areEqual(type, "PURCHASE") ? "purchase_invoice_id" : "sales_invoice_id";
        StringBuilder sb = new StringBuilder("POST_");
        sb.append(type);
        String str41 = "POST_";
        sb.append("_INVOICE");
        String string5 = sb.toString();
        String str42 = "_INVOICE";
        if (cashAccountId == null) {
            StringBuilder sb2 = new StringBuilder();
            r1.append(type);
            r1.append("|");
            r1.append(r17);
            r1 = sb2;
        } else {
            StringBuilder sb3 = new StringBuilder();
            r1.append(type);
            r1.append("|");
            r1.append(r17);
            r1.append("|");
            r1.append(cashAccountId);
            r1 = sb3;
        }
        String strHash = r16.hash(r1.toString());
        writableDatabase.beginTransaction();
        try {
            Context context = r16.context;
            Intrinsics.checkNotNullExpressionValue(context, "context");
            String str43 = "context";
            ?? syncProtocolRepository = new SyncProtocolRepository(context, null, 2, false ? 1 : 0);
            if (Intrinsics.areEqual(type, "PURCHASE")) {
                try {
                    if (syncProtocolRepository.isConfiguredSecondary()) {
                        throw new IllegalArgumentException("\u0627\u0639\u062a\u0645\u0627\u062f \u0641\u0648\u0627\u062a\u064a\u0631 \u0627\u0644\u0645\u0634\u062a\u0631\u064a\u0627\u062a \u0645\u062a\u0627\u062d \u0645\u0646 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0631\u0626\u064a\u0633\u064a \u0641\u0642\u0637".toString());
                    }
                    if (Intrinsics.areEqual(type, "PURCHASE")) {
                        str = PermissionPolicy.PURCHASES_MANAGE;
                    } else {
                        str = PermissionPolicy.SALES_CHECKOUT;
                    }
                    if (syncProtocolRepository.isConfiguredSecondary()) {
                        PermissionPolicy.INSTANCE.requireAssigned(writableDatabase, actorUserId, str);
                    } else {
                        PermissionPolicy.INSTANCE.require(writableDatabase, actorUserId, str);
                    }
                    existingOperationFindOperation = r16.findOperation(writableDatabase, operationId);
                    if (existingOperationFindOperation != null) {
                        if (Intrinsics.areEqual(existingOperationFindOperation.getType(), string5) || !Intrinsics.areEqual(existingOperationFindOperation.getPayloadHash(), strHash) || !Intrinsics.areEqual(existingOperationFindOperation.getReference(), (Object) r17)) {
                            throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0627\u0639\u062a\u0645\u0627\u062f \u0645\u0633\u062a\u062e\u062f\u0645 \u0644\u0639\u0645\u0644\u064a\u0629 \u0645\u062e\u062a\u0644\u0641\u0629".toString());
                        }
                        writableDatabase.endTransaction();
                        return false;
                    }
                    strInvoiceStatus = r16.invoiceStatus(writableDatabase, str38, r17);
                    try {
                        if (strInvoiceStatus != null) {
                            throw new IllegalArgumentException("\u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629");
                        }
                        iHashCode = strInvoiceStatus.hashCode();
                        r10 = -1929121473;
                        str2 = "PAID";
                        try {
                            if (iHashCode == -1929121473 ? strInvoiceStatus.equals("POSTED") : !(iHashCode == -313981507 ? !strInvoiceStatus.equals("PARTIALLY_PAID") : !(iHashCode == 2448076 && strInvoiceStatus.equals("PAID")))) {
                                try {
                                    registerOperation(writableDatabase, operationId, string5, strHash, invoiceId, timestamp());
                                    writableDatabase.setTransactionSuccessful();
                                    writableDatabase.endTransaction();
                                    return false;
                                } catch (Throwable th5) {
                                    th = th5;
                                    r10 = writableDatabase;
                                    th = th;
                                    r12 = r10;
                                    r12.endTransaction();
                                    throw th;
                                }
                            }
                            r10 = writableDatabase;
                            try {
                                if (Intrinsics.areEqual(strInvoiceStatus, "DRAFT")) {
                                    throw new IllegalArgumentException(("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0627\u0639\u062a\u0645\u0627\u062f \u0641\u0627\u062a\u0648\u0631\u0629 \u0628\u0647\u0630\u0647 \u0627\u0644\u062d\u0627\u0644\u0629: " + strInvoiceStatus).toString());
                                }
                                strTimestamp = timestamp();
                                if (occurredAt == null) {
                                    str3 = strTimestamp;
                                } else {
                                    str3 = occurredAt;
                                }
                                if (new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(str3)) {
                                    throw new IllegalArgumentException("\u0648\u0642\u062a \u0627\u0644\u062d\u0631\u0643\u0629 \u0627\u0644\u0623\u0635\u0644\u064a \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                }
                                if (operatingDate == null) {
                                    strOperatingDateForTimestamp = r16.operatingDateForTimestamp(str3);
                                } else {
                                    strOperatingDateForTimestamp = operatingDate;
                                }
                                if (new Regex("\\d{4}-\\d{2}-\\d{2}").matches(strOperatingDateForTimestamp)) {
                                    throw new IllegalArgumentException("\u062a\u0627\u0631\u064a\u062e \u0627\u0644\u062d\u0631\u0643\u0629 \u0627\u0644\u062a\u0634\u063a\u064a\u0644\u064a \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                }
                                if (Intrinsics.areEqual(type, "PURCHASE")) {
                                    cursorRawQuery7 = r10.rawQuery("SELECT exchange_rate_syp_per_usd_milli FROM purchase_invoices WHERE id=?", new String[]{invoiceId});
                                    try {
                                        cursor10 = cursorRawQuery7;
                                        if (cursor10.moveToFirst()) {
                                            throw new IllegalArgumentException("Failed requirement.".toString());
                                        }
                                        j4 = cursor10.getLong(0);
                                        CloseableKt.closeFinally(cursorRawQuery7, null);
                                        if (j4 > 0) {
                                            throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0627\u0639\u062a\u0645\u0627\u062f \u0641\u0627\u062a\u0648\u0631\u0629 \u0634\u0631\u0627\u0621 \u062f\u0648\u0646 \u0633\u0639\u0631 \u0635\u0631\u0641 \u0645\u062d\u0641\u0648\u0638\u061b \u064a\u062c\u0628 \u062a\u062b\u0628\u064a\u062a \u0633\u0639\u0631\u0647\u0627 \u0627\u0644\u0633\u0648\u0631\u064a \u0623\u0648\u0644\u0627\u064b".toString());
                                        }
                                    } catch (Throwable th6) {
                                        try {
                                            throw th6;
                                        } catch (Throwable th7) {
                                            CloseableKt.closeFinally(cursorRawQuery7, th6);
                                            throw th7;
                                        }
                                    }
                                }
                                if (Intrinsics.areEqual(type, "SALE")) {
                                    cursorRawQuery4 = r10.rawQuery("SELECT sale_type,customer_id,exchange_rate_syp_per_usd_milli,\n                              total_usd_cents FROM sales_invoices WHERE id=?", new String[]{invoiceId});
                                    try {
                                        cursor7 = cursorRawQuery4;
                                        if (cursor7.moveToFirst()) {
                                            throw new IllegalArgumentException("\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0628\u064a\u0639 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629".toString());
                                        }
                                        String string6 = cursor7.getString(0);
                                        if (cursor7.isNull(1)) {
                                            i = 2;
                                            string4 = null;
                                        } else {
                                            string4 = cursor7.getString(1);
                                            i = 2;
                                        }
                                        String[] strArr = {string6, string4, String.valueOf(cursor7.getLong(i)), String.valueOf(cursor7.getLong(3))};
                                        CloseableKt.closeFinally(cursorRawQuery4, null);
                                        str30 = strArr[0];
                                        Intrinsics.checkNotNull(str30, "null cannot be cast to non-null type kotlin.String");
                                        str31 = strArr[1];
                                        String str44 = strArr[2];
                                        Intrinsics.checkNotNull(str44, "null cannot be cast to non-null type kotlin.String");
                                        str7 = "\\d{4}-\\d{2}-\\d{2}";
                                        str8 = strTimestamp;
                                        j2 = Long.parseLong(str44);
                                        String str45 = strArr[3];
                                        Intrinsics.checkNotNull(str45, "null cannot be cast to non-null type kotlin.String");
                                        j3 = Long.parseLong(str45);
                                        if (Intrinsics.areEqual(str30, "CREDIT")) {
                                            if (Intrinsics.areEqual(str30, "CASH")) {
                                                throw new IllegalArgumentException("\u0646\u0648\u0639 \u0627\u0644\u0628\u064a\u0639 \u063a\u064a\u0631 \u0645\u0639\u0631\u0648\u0641".toString());
                                            }
                                            if (cashAccountId != null) {
                                                throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u0646\u0642\u062f\u064a \u062f\u0648\u0646 \u062a\u0633\u062c\u064a\u0644 \u0642\u0628\u0636 \u0643\u0627\u0645\u0644 \u0641\u064a \u0635\u0646\u062f\u0648\u0642 \u0623\u0648 \u0645\u062d\u0641\u0638\u0629");
                                            }
                                            cursorRawQuery5 = r10.rawQuery("SELECT account_type,currency_code,is_active FROM cash_accounts WHERE id=?", new String[]{cashAccountId});
                                            try {
                                                cursor8 = cursorRawQuery5;
                                                if (cursor8.moveToFirst()) {
                                                    throw new IllegalArgumentException("\u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
                                                }
                                                str2 = "PAID";
                                                if (cursor8.getInt(2) == 1) {
                                                    throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0642\u0628\u0636 \u0642\u064a\u0645\u0629 \u0627\u0644\u0628\u064a\u0639 \u0641\u064a \u062d\u0633\u0627\u0628 \u0645\u0648\u0642\u0648\u0641".toString());
                                                }
                                                String string7 = cursor8.getString(0);
                                                String string8 = cursor8.getString(1);
                                                if (cursor8.getInt(2) == 1) {
                                                    z = true;
                                                } else {
                                                    z = false;
                                                }
                                                Triple triple = new Triple(string7, string8, Boolean.valueOf(z));
                                                CloseableKt.closeFinally(cursorRawQuery5, null);
                                                str32 = (String) triple.getFirst();
                                                str33 = (String) triple.getSecond();
                                                if (!Intrinsics.areEqual(str33, "USD") && !Intrinsics.areEqual(str33, "SYP")) {
                                                    throw new IllegalArgumentException("\u0639\u0645\u0644\u0629 \u062d\u0633\u0627\u0628 \u0627\u0644\u0642\u0628\u0636 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u0629".toString());
                                                }
                                                if (syncProtocolRepository.isConfiguredSecondary()) {
                                                    cursorRawQuery6 = r10.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key=?", new String[]{"cash_account_sales_allowed:" + cashAccountId});
                                                    try {
                                                        cursor9 = cursorRawQuery6;
                                                        if (cursor9.moveToFirst() || !Intrinsics.areEqual(cursor9.getString(0), "1")) {
                                                            z2 = false;
                                                        } else {
                                                            z2 = true;
                                                        }
                                                        CloseableKt.closeFinally(cursorRawQuery6, null);
                                                        if (!z2) {
                                                            throw new IllegalArgumentException("\u0647\u0630\u0627 \u0627\u0644\u062d\u0633\u0627\u0628 \u063a\u064a\u0631 \u0645\u062e\u0635\u0635 \u0644\u0642\u0628\u0636 \u0645\u0628\u064a\u0639\u0627\u062a \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a".toString());
                                                        }
                                                    } catch (Throwable th8) {
                                                        try {
                                                            throw th8;
                                                        } catch (Throwable th9) {
                                                            CloseableKt.closeFinally(cursorRawQuery6, th8);
                                                            throw th9;
                                                        }
                                                    }
                                                }
                                                if (!Intrinsics.areEqual(str32, "CASH_BOX") && Intrinsics.areEqual(str33, "SYP") && !syncProtocolRepository.isConfiguredSecondary()) {
                                                    Cursor cursorRawQuery8 = r10.rawQuery("SELECT primary_cash_account_id FROM daily_cash_sessions WHERE operating_date=?", new String[]{strOperatingDateForTimestamp});
                                                    try {
                                                        Cursor cursor11 = cursorRawQuery8;
                                                        String string9 = cursor11.moveToFirst() ? cursor11.getString(0) : null;
                                                        CloseableKt.closeFinally(cursorRawQuery8, null);
                                                        if (!Intrinsics.areEqual(string9, cashAccountId)) {
                                                            throw new IllegalArgumentException("\u0642\u0628\u0636 \u0627\u0644\u0645\u0628\u064a\u0639\u0627\u062a \u0627\u0644\u0646\u0642\u062f\u064a \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u064a\u062c\u0628 \u0623\u0646 \u064a\u0630\u0647\u0628 \u0625\u0644\u0649 \u062f\u0631\u062c \u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u064a\u0648\u0645\u064a \u0627\u0644\u0631\u0626\u064a\u0633\u064a \u0641\u0642\u0637".toString());
                                                        }
                                                        Cursor cursorRawQuery9 = r10.rawQuery("SELECT 1 FROM system_cash_account_roles WHERE role_key='SUPPLIER_ROLLOVER' AND account_id=?", new String[]{cashAccountId});
                                                        try {
                                                            boolean zMoveToFirst = cursorRawQuery9.moveToFirst();
                                                            CloseableKt.closeFinally(cursorRawQuery9, null);
                                                            if (zMoveToFirst) {
                                                                throw new IllegalArgumentException("\u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0644\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0644\u0627 \u064a\u0633\u062a\u0642\u0628\u0644 \u0645\u0628\u064a\u0639\u0627\u062a".toString());
                                                            }
                                                        } catch (Throwable th10) {
                                                            try {
                                                                throw th10;
                                                            } catch (Throwable th11) {
                                                                CloseableKt.closeFinally(cursorRawQuery9, th10);
                                                                throw th11;
                                                            }
                                                        }
                                                    } catch (Throwable th12) {
                                                        try {
                                                            throw th12;
                                                        } catch (Throwable th13) {
                                                            CloseableKt.closeFinally(cursorRawQuery8, th12);
                                                            throw th13;
                                                        }
                                                    }
                                                } else if (!Intrinsics.areEqual(str32, "WALLET") && !Intrinsics.areEqual(str32, "CASH_BOX")) {
                                                    throw new IllegalArgumentException("\u064a\u062c\u0628 \u0627\u062e\u062a\u064a\u0627\u0631 \u0635\u0646\u062f\u0648\u0642 \u0646\u0642\u062f\u064a \u0623\u0648 \u0645\u062d\u0641\u0638\u0629 \u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a\u0629 \u0646\u0634\u0637\u0629 \u0644\u0642\u0628\u0636 \u0627\u0644\u0645\u0628\u064a\u0639\u0627\u062a".toString());
                                                }
                                                if (Intrinsics.areEqual(str33, "USD")) {
                                                    jUsdCentsToSyp = j3;
                                                } else {
                                                    if (!Intrinsics.areEqual(str33, "SYP")) {
                                                        throw new IllegalStateException("\u0639\u0645\u0644\u0629 \u062d\u0633\u0627\u0628 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u0629".toString());
                                                    }
                                                    if (j2 <= 0) {
                                                        throw new IllegalArgumentException("\u064a\u062c\u0628 \u062a\u0633\u062c\u064a\u0644 \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0642\u0628\u0644 \u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u0646\u0642\u062f\u064a \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629".toString());
                                                    }
                                                    jUsdCentsToSyp = CurrencyConverter.INSTANCE.usdCentsToSyp(j3, j2);
                                                }
                                                if (j3 > 0 && jUsdCentsToSyp <= 0) {
                                                    throw new IllegalArgumentException("\u0642\u064a\u0645\u0629 \u0627\u0644\u0628\u064a\u0639 \u0623\u0635\u063a\u0631 \u0645\u0646 \u0623\u0635\u063a\u0631 \u0648\u062d\u062f\u0629 \u0646\u0642\u062f\u064a\u0629".toString());
                                                }
                                                str42 = str42;
                                                str36 = str36;
                                                obj = strOperatingDateForTimestamp;
                                                str41 = str41;
                                                cashSaleReceipt = cashSaleReceipt;
                                                str37 = str37;
                                                str6 = str38;
                                                str35 = str35;
                                                str4 = "SALE";
                                                CashSaleReceipt cashSaleReceipt2 = new CashSaleReceipt(cashAccountId, str33, jUsdCentsToSyp, j2);
                                                obj2 = str3;
                                                strInvoiceWarehouse = r16.invoiceWarehouse(r10, str6, invoiceId);
                                                if (syncProtocolRepository.isConfiguredSecondary()) {
                                                    cursorRawQuery3 = r10.rawQuery(str37 + str39 + " WHERE " + str40 + "=?", new String[]{invoiceId});
                                                    try {
                                                        Cursor cursor12 = cursorRawQuery3;
                                                        count = cursor12.getCount();
                                                        int i2 = 1;
                                                        if (1 <= count || count >= 501) {
                                                            throw new IllegalArgumentException("\u0639\u062f\u062f \u0623\u0635\u0646\u0627\u0641 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                                        }
                                                        while (cursor12.moveToNext()) {
                                                            String string10 = cursor12.getString(0);
                                                            double d3 = cursor12.getDouble(i2);
                                                            if (d3 <= 0.0d || Math.abs(d3) > Double.MAX_VALUE) {
                                                                throw new IllegalArgumentException("\u0643\u0645\u064a\u0629 \u0635\u0646\u0641 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
                                                            }
                                                            if (Intrinsics.areEqual(type, str4)) {
                                                                Intrinsics.checkNotNull(string10);
                                                                if (r16.productTracksInventory(r10, string10)) {
                                                                    double dStockQuantity2 = r16.stockQuantity(r10, string10, strInvoiceWarehouse);
                                                                    if (d3 > dStockQuantity2 + 1.0E-7d) {
                                                                        throw new IllegalArgumentException(("\u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u0627\u0644\u0645\u062d\u0644\u064a \u063a\u064a\u0631 \u0643\u0627\u0641\u064d \u0644\u0644\u0645\u0646\u062a\u062c: " + r16.productName(r10, string10) + " (\u0627\u0644\u0645\u062a\u0648\u0641\u0631: " + dStockQuantity2 + ")").toString());
                                                                    }
                                                                } else {
                                                                    continue;
                                                                }
                                                            }
                                                            i2 = 1;
                                                        }
                                                        Unit unit = Unit.INSTANCE;
                                                        CloseableKt.closeFinally(cursorRawQuery3, null);
                                                        Cursor cursorRawQuery10 = r10.rawQuery("SELECT operation_id FROM sync_operation_envelopes\n                       WHERE entity_type='SALES_INVOICE' AND entity_id=?\n                       ORDER BY sequence_number LIMIT 1", new String[]{invoiceId});
                                                        try {
                                                            Cursor cursor13 = cursorRawQuery10;
                                                            if (cursor13.moveToFirst()) {
                                                                string3 = cursor13.getString(0);
                                                                th3 = null;
                                                            } else {
                                                                th3 = null;
                                                                string3 = null;
                                                            }
                                                            CloseableKt.closeFinally(cursorRawQuery10, th3);
                                                            if (string3 != null && !Intrinsics.areEqual(string3, operationId)) {
                                                                throw new IllegalArgumentException("\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0628\u064a\u0639 \u0644\u062f\u064a\u0647\u0627 \u0623\u0645\u0631 \u0645\u0632\u0627\u0645\u0646\u0629 \u0633\u0627\u0628\u0642\u061b \u0644\u0646 \u064a\u064f\u0646\u0634\u0623 \u0623\u0645\u0631 \u0645\u0643\u0631\u0631 \u0628\u0645\u0639\u0631\u0651\u0641 \u0645\u062e\u062a\u0644\u0641".toString());
                                                            }
                                                            Cursor cursorRawQuery11 = r10.rawQuery("SELECT payload_json FROM sync_operation_envelopes WHERE operation_id=?", new String[]{operationId});
                                                            try {
                                                                Cursor cursor14 = cursorRawQuery11;
                                                                if (cursor14.moveToFirst()) {
                                                                    jSONObject = new JSONObject(cursor14.getString(0));
                                                                    th4 = null;
                                                                } else {
                                                                    th4 = null;
                                                                    jSONObject = null;
                                                                }
                                                                CloseableKt.closeFinally(cursorRawQuery11, th4);
                                                                if (jSONObject != null && (strOptString2 = jSONObject.optString("posted_at")) != null) {
                                                                    Object obj3 = (StringsKt.isBlank(strOptString2) || Intrinsics.areEqual(strOptString2, "null")) ? th4 : strOptString2;
                                                                    if (obj3 != null) {
                                                                        obj2 = obj3;
                                                                    }
                                                                }
                                                                if (jSONObject != null && (strOptString = jSONObject.optString("posted_operating_date")) != null) {
                                                                    Object obj4 = new Regex(str7).matches(strOptString) ? strOptString : th4;
                                                                    if (obj4 != null) {
                                                                        obj = obj4;
                                                                    }
                                                                }
                                                                syncProtocolRepository.enqueueInvoicePosting(r10, actorUserId, operationId, type, invoiceId, cashAccountId, obj2, obj);
                                                                registerOperation(r10, operationId, string5, strHash, invoiceId, str8);
                                                                r10.setTransactionSuccessful();
                                                                r10.endTransaction();
                                                                try {
                                                                    Result.Companion companion = Result.INSTANCE;
                                                                    SyncRelaySession.INSTANCE.flushPending();
                                                                    Result.m381constructorimpl(Unit.INSTANCE);
                                                                    return true;
                                                                } catch (Throwable th14) {
                                                                    Result.Companion companion2 = Result.INSTANCE;
                                                                    Result.m381constructorimpl(ResultKt.createFailure(th14));
                                                                    return true;
                                                                }
                                                            } catch (Throwable th15) {
                                                                try {
                                                                    throw th15;
                                                                } catch (Throwable th16) {
                                                                    CloseableKt.closeFinally(cursorRawQuery11, th15);
                                                                    throw th16;
                                                                }
                                                            }
                                                        } catch (Throwable th17) {
                                                            try {
                                                                throw th17;
                                                            } catch (Throwable th18) {
                                                                CloseableKt.closeFinally(cursorRawQuery10, th17);
                                                                throw th18;
                                                            }
                                                        }
                                                    } catch (Throwable th19) {
                                                        try {
                                                            throw th19;
                                                        } catch (Throwable th20) {
                                                            CloseableKt.closeFinally(cursorRawQuery3, th19);
                                                            throw th20;
                                                        }
                                                    }
                                                }
                                                str9 = type;
                                                str10 = operationId;
                                                str11 = str8;
                                                cursorRawQuery = r10.rawQuery(str35 + str39 + " WHERE " + str40 + "=?", new String[]{invoiceId});
                                                try {
                                                    cursor2 = cursorRawQuery;
                                                    r10 = r10;
                                                    r13 = r16;
                                                    if (cursor2.getCount() > 0) {
                                                        while (cursor2.moveToNext()) {
                                                            try {
                                                                string = cursor2.getString(0);
                                                                string2 = cursor2.getString(1);
                                                                cursor6 = cursorRawQuery;
                                                                str22 = str6;
                                                                try {
                                                                    d = cursor2.getDouble(2);
                                                                    j = cursor2.getLong(3);
                                                                    Intrinsics.checkNotNull(string2);
                                                                    zProductTracksInventory = r13.productTracksInventory(r10, string2);
                                                                    if (Intrinsics.areEqual(str9, str4) && zProductTracksInventory) {
                                                                        try {
                                                                            dStockQuantity = r13.stockQuantity(r10, string2, strInvoiceWarehouse);
                                                                            if (d > dStockQuantity + 1.0E-7d) {
                                                                                throw new IllegalArgumentException(("\u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u063a\u064a\u0631 \u0643\u0627\u0641\u064d \u0644\u0644\u0645\u0646\u062a\u062c: " + r13.productName(r10, string2) + " (\u0627\u0644\u0645\u062a\u0648\u0641\u0631: " + dStockQuantity + ")").toString());
                                                                            }
                                                                        } catch (Throwable th21) {
                                                                            cursor = cursor6;
                                                                            th2 = th21;
                                                                            r17 = r10;
                                                                        }
                                                                    }
                                                                    if (zProductTracksInventory) {
                                                                        try {
                                                                            if (Intrinsics.areEqual(str9, str4)) {
                                                                                str23 = string2;
                                                                                str24 = string;
                                                                                cursor5 = cursor6;
                                                                                r58 = r10;
                                                                                c = 1;
                                                                                str25 = operationId;
                                                                                try {
                                                                                    FifoCosting.INSTANCE.consumeForSale(r10, invoiceId, string, str23, strInvoiceWarehouse, d, obj2, (128 & 128) != 0);
                                                                                    r59 = r58;
                                                                                } catch (Throwable th22) {
                                                                                    th = th22;
                                                                                    r14 = r58;
                                                                                    th2 = th;
                                                                                    r15 = r14;
                                                                                    cursor = cursor5;
                                                                                    r17 = r15;
                                                                                    throw th2;
                                                                                }
                                                                            } else {
                                                                                cursor5 = cursor6;
                                                                                str23 = string2;
                                                                                str24 = string;
                                                                                r59 = r10;
                                                                                c = 1;
                                                                                str25 = operationId;
                                                                            }
                                                                            str26 = str36;
                                                                            if (Intrinsics.areEqual(type, str26)) {
                                                                                d2 = d;
                                                                            } else {
                                                                                d2 = -d;
                                                                            }
                                                                            if (Intrinsics.areEqual(type, str26)) {
                                                                                str27 = str26;
                                                                            } else {
                                                                                str27 = str4;
                                                                            }
                                                                            str28 = str26;
                                                                            str29 = type;
                                                                            applyStockMovement(r59, str23, strInvoiceWarehouse, d2, j, str27, invoiceId, str25 + ":" + str24, obj2);
                                                                            r510 = r59;
                                                                        } catch (Throwable th23) {
                                                                            th = th23;
                                                                            cursor5 = cursor6;
                                                                            r58 = r10;
                                                                        }
                                                                    } else {
                                                                        cursor5 = cursor6;
                                                                        str23 = string2;
                                                                        cursor2 = cursor2;
                                                                        strInvoiceWarehouse = strInvoiceWarehouse;
                                                                        str29 = str9;
                                                                        r510 = r10;
                                                                        str4 = str4;
                                                                        str11 = str11;
                                                                        str43 = str43;
                                                                        str22 = str22;
                                                                        str28 = str36;
                                                                        c = 1;
                                                                        str25 = operationId;
                                                                    }
                                                                    if (Intrinsics.areEqual(str29, str28)) {
                                                                        try {
                                                                            Object[] objArr = new Object[3];
                                                                            objArr[0] = Long.valueOf(j);
                                                                            objArr[c] = str11;
                                                                            objArr[2] = str23;
                                                                            r14 = r510;
                                                                            try {
                                                                                r14.execSQL("UPDATE products SET purchase_cost_usd_cents=?,updated_at=? WHERE id=?", objArr);
                                                                                r11 = r14;
                                                                            } catch (Throwable th24) {
                                                                                th = th24;
                                                                                th2 = th;
                                                                                r15 = r14;
                                                                                cursor = cursor5;
                                                                                r17 = r15;
                                                                                throw th2;
                                                                            }
                                                                        } catch (Throwable th25) {
                                                                            r15 = r510;
                                                                            th2 = th25;
                                                                            cursor = cursor5;
                                                                            r17 = r15;
                                                                            throw th2;
                                                                        }
                                                                    } else {
                                                                        r11 = r510;
                                                                    }
                                                                    str9 = str29;
                                                                    str36 = str28;
                                                                    str10 = str25;
                                                                    str4 = str4;
                                                                    cursor2 = cursor2;
                                                                    strInvoiceWarehouse = strInvoiceWarehouse;
                                                                    str11 = str11;
                                                                    cursorRawQuery = cursor5;
                                                                    str43 = str43;
                                                                    str6 = str22;
                                                                    r13 = this;
                                                                    r10 = r11;
                                                                } catch (Throwable th26) {
                                                                    th = th26;
                                                                    cursor5 = cursor6;
                                                                    r14 = r10;
                                                                    th2 = th;
                                                                    r15 = r14;
                                                                    cursor = cursor5;
                                                                    r17 = r15;
                                                                    throw th2;
                                                                }
                                                            } catch (Throwable th27) {
                                                                th = th27;
                                                                cursor5 = cursorRawQuery;
                                                            }
                                                        }
                                                        str12 = str9;
                                                        cursor3 = cursorRawQuery;
                                                        String str46 = str6;
                                                        str13 = str4;
                                                        str14 = str11;
                                                        str15 = str43;
                                                        str16 = str10;
                                                        r17 = r10;
                                                        try {
                                                            Unit unit2 = Unit.INSTANCE;
                                                            CloseableKt.closeFinally(cursor3, null);
                                                            if (cashSaleReceipt != null || cashSaleReceipt.getAmountMinor() <= 0) {
                                                                str17 = invoiceId;
                                                                str18 = str13;
                                                                r9 = obj2;
                                                                r7 = obj;
                                                            } else {
                                                                Cursor cursorRawQuery12 = r17.rawQuery("SELECT invoice_number FROM sales_invoices WHERE id=?", new String[]{invoiceId});
                                                                try {
                                                                    Cursor cursor15 = cursorRawQuery12;
                                                                    if (!cursor15.moveToFirst()) {
                                                                        throw new IllegalArgumentException("Failed requirement.".toString());
                                                                    }
                                                                    String string11 = cursor15.getString(0);
                                                                    CloseableKt.closeFinally(cursorRawQuery12, null);
                                                                    String string12 = UUID.randomUUID().toString();
                                                                    Intrinsics.checkNotNullExpressionValue(string12, "toString(...)");
                                                                    ?? contentValues = new ContentValues();
                                                                    contentValues.put("id", string12);
                                                                    contentValues.put("account_id", cashSaleReceipt.getAccountId());
                                                                    contentValues.put("direction", "IN");
                                                                    contentValues.put("amount_minor", Long.valueOf(cashSaleReceipt.getAmountMinor()));
                                                                    contentValues.put("currency_code", cashSaleReceipt.getCurrencyCode());
                                                                    if (cashSaleReceipt.getRateMilli() > 0) {
                                                                        contentValues.put("exchange_rate_syp_per_usd_milli", Long.valueOf(cashSaleReceipt.getRateMilli()));
                                                                    } else {
                                                                        contentValues.putNull("exchange_rate_syp_per_usd_milli");
                                                                    }
                                                                    contentValues.put("transaction_type", "SALE_RECEIPT");
                                                                    contentValues.put("source_type", "SALES_INVOICE");
                                                                    str17 = invoiceId;
                                                                    contentValues.put("source_id", str17);
                                                                    contentValues.put("operation_id", str16);
                                                                    contentValues.put("description", "\u0642\u0628\u0636 \u0642\u064a\u0645\u0629 \u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0628\u064a\u0639 " + string11);
                                                                    ?? r8 = obj;
                                                                    contentValues.put("operating_date", r8);
                                                                    ?? r18 = obj2;
                                                                    contentValues.put("created_at", r18);
                                                                    r17.insertOrThrow("cash_transactions", null, contentValues);
                                                                    ?? contentValues2 = new ContentValues();
                                                                    contentValues2.put("id", UUID.randomUUID().toString());
                                                                    str18 = str13;
                                                                    contentValues2.put("invoice_type", str18);
                                                                    contentValues2.put("invoice_id", str17);
                                                                    contentValues2.put("cash_transaction_id", string12);
                                                                    contentValues2.put("amount_minor", Long.valueOf(cashSaleReceipt.getAmountMinor()));
                                                                    contentValues2.put("currency_code", cashSaleReceipt.getCurrencyCode());
                                                                    if (cashSaleReceipt.getRateMilli() > 0) {
                                                                        contentValues2.put("exchange_rate_syp_per_usd_milli", Long.valueOf(cashSaleReceipt.getRateMilli()));
                                                                    } else {
                                                                        contentValues2.putNull("exchange_rate_syp_per_usd_milli");
                                                                    }
                                                                    contentValues2.put("created_at", r18);
                                                                    r17.insertOrThrow("invoice_payments", null, contentValues2);
                                                                    r7 = r8;
                                                                    r9 = r18;
                                                                } catch (Throwable th28) {
                                                                    try {
                                                                        throw th28;
                                                                    } catch (Throwable th29) {
                                                                        CloseableKt.closeFinally(cursorRawQuery12, th28);
                                                                        throw th29;
                                                                    }
                                                                }
                                                            }
                                                            if (Intrinsics.areEqual(str12, str18) || cashSaleReceipt == null) {
                                                                str19 = "POSTED";
                                                            } else {
                                                                str19 = str2;
                                                            }
                                                            if (Intrinsics.areEqual(str12, str18)) {
                                                                str20 = str14;
                                                                r17.execSQL("UPDATE sales_invoices SET status=?,updated_at=?,posted_at=?,posted_operating_date=? WHERE id=? AND status='DRAFT'", new String[]{str19, str20, r9, r7, str17});
                                                            } else {
                                                                str20 = str14;
                                                                r17.execSQL("UPDATE purchase_invoices SET status=?,updated_at=? WHERE id=? AND status='DRAFT'", new String[]{str19, str20, str17});
                                                            }
                                                            cursorRawQuery2 = r17.rawQuery("SELECT total_usd_cents FROM " + str46 + " WHERE id=?", new String[]{invoiceId});
                                                            try {
                                                                cursor4 = cursorRawQuery2;
                                                                if (!cursor4.moveToFirst()) {
                                                                    throw new IllegalArgumentException("Failed requirement.".toString());
                                                                }
                                                                long j5 = cursor4.getLong(0);
                                                                CloseableKt.closeFinally(cursorRawQuery2, null);
                                                                if (cashAccountId != null) {
                                                                    str21 = "\"" + cashAccountId + "\"";
                                                                    if (str21 == null) {
                                                                        str21 = "null";
                                                                    }
                                                                } else {
                                                                    str21 = "null";
                                                                }
                                                                ContentValues contentValues3 = new ContentValues();
                                                                contentValues3.put("id", UUID.randomUUID().toString());
                                                                contentValues3.put("user_id", actorUserId);
                                                                StringBuilder sb4 = new StringBuilder();
                                                                sb4.append(str41);
                                                                sb4.append(str12);
                                                                String str47 = str42;
                                                                sb4.append(str47);
                                                                contentValues3.put("action", sb4.toString());
                                                                contentValues3.put("entity_type", str12 + str47);
                                                                contentValues3.put("entity_id", str17);
                                                                contentValues3.put("before_json", "{\"status\":\"DRAFT\"}");
                                                                contentValues3.put("after_json", "{\"status\":\"" + str19 + "\",\"total_usd_cents\":" + j5 + ",\"operation_id\":\"" + str16 + "\",\"cash_account_id\":" + str21 + "}");
                                                                SyncDeviceIdentity.Companion companion3 = SyncDeviceIdentity.INSTANCE;
                                                                Context context2 = this.context;
                                                                Intrinsics.checkNotNullExpressionValue(context2, str15);
                                                                contentValues3.put("device_id", companion3.getOrCreate(context2));
                                                                contentValues3.put("created_at", str20);
                                                                Unit unit3 = Unit.INSTANCE;
                                                                r17.insertOrThrow("audit_log", null, contentValues3);
                                                                Context context3 = this.context;
                                                                Intrinsics.checkNotNullExpressionValue(context3, str15);
                                                                new SyncProtocolRepository(context3, null, 2, false ? 1 : 0).enqueueInvoicePosting(r17, actorUserId, operationId, type, invoiceId, (BERTags.PRIVATE & 32) != 0 ? null : cashAccountId, (BERTags.PRIVATE & 64) != 0 ? null : null, (BERTags.PRIVATE & 128) != 0 ? null : null);
                                                                registerOperation(r17, operationId, string5, strHash, invoiceId, str20);
                                                                r17.setTransactionSuccessful();
                                                                r17.endTransaction();
                                                                try {
                                                                    Result.Companion companion4 = Result.INSTANCE;
                                                                    SyncRelaySession.INSTANCE.flushPending();
                                                                    Result.m381constructorimpl(Unit.INSTANCE);
                                                                } catch (Throwable th30) {
                                                                    Result.Companion companion5 = Result.INSTANCE;
                                                                    Result.m381constructorimpl(ResultKt.createFailure(th30));
                                                                }
                                                                return true;
                                                            } catch (Throwable th31) {
                                                                try {
                                                                    throw th31;
                                                                } catch (Throwable th32) {
                                                                    CloseableKt.closeFinally(cursorRawQuery2, th31);
                                                                    throw th32;
                                                                }
                                                            }
                                                        } catch (Throwable th33) {
                                                            th = th33;
                                                            cursor = cursor3;
                                                            th2 = th;
                                                            r17 = r17;
                                                            throw th2;
                                                        }
                                                    }
                                                    cursor = cursorRawQuery;
                                                    r17 = r10;
                                                    try {
                                                        throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0627\u0639\u062a\u0645\u0627\u062f \u0641\u0627\u062a\u0648\u0631\u0629 \u0641\u0627\u0631\u063a\u0629".toString());
                                                    } catch (Throwable th34) {
                                                        th = th34;
                                                    }
                                                } catch (Throwable th35) {
                                                    th = th35;
                                                    cursor = cursorRawQuery;
                                                    r17 = r10;
                                                }
                                                th2 = th;
                                                r17 = r17;
                                                try {
                                                    throw th2;
                                                } catch (Throwable th36) {
                                                    CloseableKt.closeFinally(cursor, th2);
                                                    throw th36;
                                                }
                                            } catch (Throwable th37) {
                                                try {
                                                    throw th37;
                                                } catch (Throwable th38) {
                                                    CloseableKt.closeFinally(cursorRawQuery5, th37);
                                                    throw th38;
                                                }
                                            }
                                        }
                                        if (cashAccountId == null) {
                                            throw new IllegalArgumentException("\u0644\u0627 \u064a\u062c\u0648\u0632 \u0631\u0628\u0637 \u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u0622\u062c\u0644 \u0628\u062a\u062d\u0635\u064a\u0644 \u0646\u0642\u062f\u064a \u0639\u0646\u062f \u0627\u0644\u0627\u0639\u062a\u0645\u0627\u062f".toString());
                                        }
                                        str34 = str31;
                                        if (str34 != null || StringsKt.isBlank(str34)) {
                                            z3 = true;
                                        } else {
                                            z3 = false;
                                        }
                                        if (!z3) {
                                            throw new IllegalArgumentException("\u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u0622\u062c\u0644 \u064a\u062a\u0637\u0644\u0628 \u0632\u0628\u0648\u0646\u0627\u064b \u0645\u062d\u062f\u062f\u0627\u064b".toString());
                                        }
                                        if (j2 > 0) {
                                            throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0627\u0639\u062a\u0645\u0627\u062f \u0628\u064a\u0639 \u0622\u062c\u0644 \u062f\u0648\u0646 \u0633\u0639\u0631 \u0635\u0631\u0641 \u0645\u062d\u0641\u0648\u0638".toString());
                                        }
                                        str4 = "SALE";
                                        str6 = str38;
                                        str5 = str3;
                                    } catch (Throwable th39) {
                                        try {
                                            throw th39;
                                        } catch (Throwable th40) {
                                            CloseableKt.closeFinally(cursorRawQuery4, th39);
                                            throw th40;
                                        }
                                    }
                                } else {
                                    str4 = "SALE";
                                    str5 = str3;
                                    str6 = str38;
                                    str7 = "\\d{4}-\\d{2}-\\d{2}";
                                    str8 = strTimestamp;
                                }
                                cashSaleReceipt = null;
                                obj2 = str5;
                                obj = strOperatingDateForTimestamp;
                                strInvoiceWarehouse = r16.invoiceWarehouse(r10, str6, invoiceId);
                                if (syncProtocolRepository.isConfiguredSecondary()) {
                                    cursorRawQuery3 = r10.rawQuery(str37 + str39 + " WHERE " + str40 + "=?", new String[]{invoiceId});
                                    Cursor cursor16 = cursorRawQuery3;
                                    count = cursor16.getCount();
                                    int i3 = 1;
                                    if (1 <= count) {
                                    }
                                    throw new IllegalArgumentException("\u0639\u062f\u062f \u0623\u0635\u0646\u0627\u0641 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                }
                                str9 = type;
                                str10 = operationId;
                                str11 = str8;
                                cursorRawQuery = r10.rawQuery(str35 + str39 + " WHERE " + str40 + "=?", new String[]{invoiceId});
                                cursor2 = cursorRawQuery;
                                r10 = r10;
                                r13 = r16;
                                if (cursor2.getCount() > 0) {
                                    cursor = cursorRawQuery;
                                    r17 = r10;
                                    throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0627\u0639\u062a\u0645\u0627\u062f \u0641\u0627\u062a\u0648\u0631\u0629 \u0641\u0627\u0631\u063a\u0629".toString());
                                }
                                while (cursor2.moveToNext()) {
                                    string = cursor2.getString(0);
                                    string2 = cursor2.getString(1);
                                    cursor6 = cursorRawQuery;
                                    str22 = str6;
                                    d = cursor2.getDouble(2);
                                    j = cursor2.getLong(3);
                                    Intrinsics.checkNotNull(string2);
                                    zProductTracksInventory = r13.productTracksInventory(r10, string2);
                                    if (Intrinsics.areEqual(str9, str4)) {
                                        dStockQuantity = r13.stockQuantity(r10, string2, strInvoiceWarehouse);
                                        if (d > dStockQuantity + 1.0E-7d) {
                                            throw new IllegalArgumentException(("\u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u063a\u064a\u0631 \u0643\u0627\u0641\u064d \u0644\u0644\u0645\u0646\u062a\u062c: " + r13.productName(r10, string2) + " (\u0627\u0644\u0645\u062a\u0648\u0641\u0631: " + dStockQuantity + ")").toString());
                                        }
                                    }
                                    if (zProductTracksInventory) {
                                        if (Intrinsics.areEqual(str9, str4)) {
                                            str23 = string2;
                                            str24 = string;
                                            cursor5 = cursor6;
                                            r58 = r10;
                                            c = 1;
                                            str25 = operationId;
                                            FifoCosting.INSTANCE.consumeForSale(r10, invoiceId, string, str23, strInvoiceWarehouse, d, obj2, (128 & 128) != 0);
                                            r59 = r58;
                                        } else {
                                            cursor5 = cursor6;
                                            str23 = string2;
                                            str24 = string;
                                            r59 = r10;
                                            c = 1;
                                            str25 = operationId;
                                        }
                                        str26 = str36;
                                        if (Intrinsics.areEqual(type, str26)) {
                                            d2 = d;
                                        } else {
                                            d2 = -d;
                                        }
                                        if (Intrinsics.areEqual(type, str26)) {
                                            str27 = str26;
                                        } else {
                                            str27 = str4;
                                        }
                                        str28 = str26;
                                        str29 = type;
                                        applyStockMovement(r59, str23, strInvoiceWarehouse, d2, j, str27, invoiceId, str25 + ":" + str24, obj2);
                                        r510 = r59;
                                    } else {
                                        cursor5 = cursor6;
                                        str23 = string2;
                                        cursor2 = cursor2;
                                        strInvoiceWarehouse = strInvoiceWarehouse;
                                        str29 = str9;
                                        r510 = r10;
                                        str4 = str4;
                                        str11 = str11;
                                        str43 = str43;
                                        str22 = str22;
                                        str28 = str36;
                                        c = 1;
                                        str25 = operationId;
                                    }
                                    if (Intrinsics.areEqual(str29, str28)) {
                                        Object[] objArr2 = new Object[3];
                                        objArr2[0] = Long.valueOf(j);
                                        objArr2[c] = str11;
                                        objArr2[2] = str23;
                                        r14 = r510;
                                        r14.execSQL("UPDATE products SET purchase_cost_usd_cents=?,updated_at=? WHERE id=?", objArr2);
                                        r11 = r14;
                                    } else {
                                        r11 = r510;
                                    }
                                    str9 = str29;
                                    str36 = str28;
                                    str10 = str25;
                                    str4 = str4;
                                    cursor2 = cursor2;
                                    strInvoiceWarehouse = strInvoiceWarehouse;
                                    str11 = str11;
                                    cursorRawQuery = cursor5;
                                    str43 = str43;
                                    str6 = str22;
                                    r13 = this;
                                    r10 = r11;
                                }
                                str12 = str9;
                                cursor3 = cursorRawQuery;
                                String str48 = str6;
                                str13 = str4;
                                str14 = str11;
                                str15 = str43;
                                str16 = str10;
                                r17 = r10;
                                Unit unit4 = Unit.INSTANCE;
                                CloseableKt.closeFinally(cursor3, null);
                                if (cashSaleReceipt != null) {
                                    str17 = invoiceId;
                                    str18 = str13;
                                    r9 = obj2;
                                    r7 = obj;
                                } else {
                                    str17 = invoiceId;
                                    str18 = str13;
                                    r9 = obj2;
                                    r7 = obj;
                                }
                                if (Intrinsics.areEqual(str12, str18)) {
                                    str19 = "POSTED";
                                } else {
                                    str19 = "POSTED";
                                }
                                if (Intrinsics.areEqual(str12, str18)) {
                                    str20 = str14;
                                    r17.execSQL("UPDATE sales_invoices SET status=?,updated_at=?,posted_at=?,posted_operating_date=? WHERE id=? AND status='DRAFT'", new String[]{str19, str20, r9, r7, str17});
                                } else {
                                    str20 = str14;
                                    r17.execSQL("UPDATE purchase_invoices SET status=?,updated_at=? WHERE id=? AND status='DRAFT'", new String[]{str19, str20, str17});
                                }
                                cursorRawQuery2 = r17.rawQuery("SELECT total_usd_cents FROM " + str48 + " WHERE id=?", new String[]{invoiceId});
                                cursor4 = cursorRawQuery2;
                                if (!cursor4.moveToFirst()) {
                                    throw new IllegalArgumentException("Failed requirement.".toString());
                                }
                                long j6 = cursor4.getLong(0);
                                CloseableKt.closeFinally(cursorRawQuery2, null);
                                if (cashAccountId != null) {
                                    str21 = "\"" + cashAccountId + "\"";
                                    if (str21 == null) {
                                        str21 = "null";
                                    }
                                } else {
                                    str21 = "null";
                                }
                                ContentValues contentValues4 = new ContentValues();
                                contentValues4.put("id", UUID.randomUUID().toString());
                                contentValues4.put("user_id", actorUserId);
                                StringBuilder sb5 = new StringBuilder();
                                sb5.append(str41);
                                sb5.append(str12);
                                String str49 = str42;
                                sb5.append(str49);
                                contentValues4.put("action", sb5.toString());
                                contentValues4.put("entity_type", str12 + str49);
                                contentValues4.put("entity_id", str17);
                                contentValues4.put("before_json", "{\"status\":\"DRAFT\"}");
                                contentValues4.put("after_json", "{\"status\":\"" + str19 + "\",\"total_usd_cents\":" + j6 + ",\"operation_id\":\"" + str16 + "\",\"cash_account_id\":" + str21 + "}");
                                SyncDeviceIdentity.Companion companion6 = SyncDeviceIdentity.INSTANCE;
                                Context context4 = this.context;
                                Intrinsics.checkNotNullExpressionValue(context4, str15);
                                contentValues4.put("device_id", companion6.getOrCreate(context4));
                                contentValues4.put("created_at", str20);
                                Unit unit5 = Unit.INSTANCE;
                                r17.insertOrThrow("audit_log", null, contentValues4);
                                Context context5 = this.context;
                                Intrinsics.checkNotNullExpressionValue(context5, str15);
                                new SyncProtocolRepository(context5, null, 2, false ? 1 : 0).enqueueInvoicePosting(r17, actorUserId, operationId, type, invoiceId, (BERTags.PRIVATE & 32) != 0 ? null : cashAccountId, (BERTags.PRIVATE & 64) != 0 ? null : null, (BERTags.PRIVATE & 128) != 0 ? null : null);
                                registerOperation(r17, operationId, string5, strHash, invoiceId, str20);
                                r17.setTransactionSuccessful();
                                r17.endTransaction();
                                Result.Companion companion7 = Result.INSTANCE;
                                SyncRelaySession.INSTANCE.flushPending();
                                Result.m381constructorimpl(Unit.INSTANCE);
                                return true;
                                th2 = th;
                                r17 = r17;
                                throw th2;
                            } catch (Throwable th41) {
                                th = th41;
                                r17 = r10;
                            }
                        } catch (Throwable th42) {
                            th = th42;
                            th = th;
                            r12 = r10;
                            r12.endTransaction();
                            throw th;
                        }
                    } catch (Throwable th43) {
                        th = th43;
                    }
                    th = th;
                    r12 = r17;
                } catch (Throwable th44) {
                    th = th44;
                    r12 = writableDatabase;
                }
            } else {
                if (Intrinsics.areEqual(type, "PURCHASE")) {
                    str = PermissionPolicy.PURCHASES_MANAGE;
                } else {
                    str = PermissionPolicy.SALES_CHECKOUT;
                }
                if (syncProtocolRepository.isConfiguredSecondary()) {
                    PermissionPolicy.INSTANCE.requireAssigned(writableDatabase, actorUserId, str);
                } else {
                    PermissionPolicy.INSTANCE.require(writableDatabase, actorUserId, str);
                }
                existingOperationFindOperation = r16.findOperation(writableDatabase, operationId);
                if (existingOperationFindOperation != null) {
                    if (Intrinsics.areEqual(existingOperationFindOperation.getType(), string5)) {
                    }
                    throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0627\u0639\u062a\u0645\u0627\u062f \u0645\u0633\u062a\u062e\u062f\u0645 \u0644\u0639\u0645\u0644\u064a\u0629 \u0645\u062e\u062a\u0644\u0641\u0629".toString());
                }
                strInvoiceStatus = r16.invoiceStatus(writableDatabase, str38, r17);
                if (strInvoiceStatus != null) {
                    throw new IllegalArgumentException("\u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629");
                }
                iHashCode = strInvoiceStatus.hashCode();
                r10 = -1929121473;
                str2 = "PAID";
                if (iHashCode == -1929121473) {
                    registerOperation(writableDatabase, operationId, string5, strHash, invoiceId, timestamp());
                    writableDatabase.setTransactionSuccessful();
                    writableDatabase.endTransaction();
                    return false;
                }
                r10 = writableDatabase;
                if (Intrinsics.areEqual(strInvoiceStatus, "DRAFT")) {
                    throw new IllegalArgumentException(("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0627\u0639\u062a\u0645\u0627\u062f \u0641\u0627\u062a\u0648\u0631\u0629 \u0628\u0647\u0630\u0647 \u0627\u0644\u062d\u0627\u0644\u0629: " + strInvoiceStatus).toString());
                }
                strTimestamp = timestamp();
                if (occurredAt == null) {
                    str3 = strTimestamp;
                } else {
                    str3 = occurredAt;
                }
                if (new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(str3)) {
                    throw new IllegalArgumentException("\u0648\u0642\u062a \u0627\u0644\u062d\u0631\u0643\u0629 \u0627\u0644\u0623\u0635\u0644\u064a \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                }
                if (operatingDate == null) {
                    strOperatingDateForTimestamp = r16.operatingDateForTimestamp(str3);
                } else {
                    strOperatingDateForTimestamp = operatingDate;
                }
                if (new Regex("\\d{4}-\\d{2}-\\d{2}").matches(strOperatingDateForTimestamp)) {
                    throw new IllegalArgumentException("\u062a\u0627\u0631\u064a\u062e \u0627\u0644\u062d\u0631\u0643\u0629 \u0627\u0644\u062a\u0634\u063a\u064a\u0644\u064a \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                }
                if (Intrinsics.areEqual(type, "PURCHASE")) {
                    cursorRawQuery7 = r10.rawQuery("SELECT exchange_rate_syp_per_usd_milli FROM purchase_invoices WHERE id=?", new String[]{invoiceId});
                    cursor10 = cursorRawQuery7;
                    if (cursor10.moveToFirst()) {
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                    j4 = cursor10.getLong(0);
                    CloseableKt.closeFinally(cursorRawQuery7, null);
                    if (j4 > 0) {
                        throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0627\u0639\u062a\u0645\u0627\u062f \u0641\u0627\u062a\u0648\u0631\u0629 \u0634\u0631\u0627\u0621 \u062f\u0648\u0646 \u0633\u0639\u0631 \u0635\u0631\u0641 \u0645\u062d\u0641\u0648\u0638\u061b \u064a\u062c\u0628 \u062a\u062b\u0628\u064a\u062a \u0633\u0639\u0631\u0647\u0627 \u0627\u0644\u0633\u0648\u0631\u064a \u0623\u0648\u0644\u0627\u064b".toString());
                    }
                }
                if (Intrinsics.areEqual(type, "SALE")) {
                    cursorRawQuery4 = r10.rawQuery("SELECT sale_type,customer_id,exchange_rate_syp_per_usd_milli,\n                              total_usd_cents FROM sales_invoices WHERE id=?", new String[]{invoiceId});
                    cursor7 = cursorRawQuery4;
                    if (cursor7.moveToFirst()) {
                        throw new IllegalArgumentException("\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0628\u064a\u0639 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629".toString());
                    }
                    String string13 = cursor7.getString(0);
                    if (cursor7.isNull(1)) {
                        i = 2;
                        string4 = null;
                    } else {
                        string4 = cursor7.getString(1);
                        i = 2;
                    }
                    String[] strArr2 = {string13, string4, String.valueOf(cursor7.getLong(i)), String.valueOf(cursor7.getLong(3))};
                    CloseableKt.closeFinally(cursorRawQuery4, null);
                    str30 = strArr2[0];
                    Intrinsics.checkNotNull(str30, "null cannot be cast to non-null type kotlin.String");
                    str31 = strArr2[1];
                    String str410 = strArr2[2];
                    Intrinsics.checkNotNull(str410, "null cannot be cast to non-null type kotlin.String");
                    str7 = "\\d{4}-\\d{2}-\\d{2}";
                    str8 = strTimestamp;
                    j2 = Long.parseLong(str410);
                    String str411 = strArr2[3];
                    Intrinsics.checkNotNull(str411, "null cannot be cast to non-null type kotlin.String");
                    j3 = Long.parseLong(str411);
                    if (Intrinsics.areEqual(str30, "CREDIT")) {
                        if (Intrinsics.areEqual(str30, "CASH")) {
                            throw new IllegalArgumentException("\u0646\u0648\u0639 \u0627\u0644\u0628\u064a\u0639 \u063a\u064a\u0631 \u0645\u0639\u0631\u0648\u0641".toString());
                        }
                        if (cashAccountId != null) {
                            throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u0646\u0642\u062f\u064a \u062f\u0648\u0646 \u062a\u0633\u062c\u064a\u0644 \u0642\u0628\u0636 \u0643\u0627\u0645\u0644 \u0641\u064a \u0635\u0646\u062f\u0648\u0642 \u0623\u0648 \u0645\u062d\u0641\u0638\u0629");
                        }
                        cursorRawQuery5 = r10.rawQuery("SELECT account_type,currency_code,is_active FROM cash_accounts WHERE id=?", new String[]{cashAccountId});
                        cursor8 = cursorRawQuery5;
                        if (cursor8.moveToFirst()) {
                            throw new IllegalArgumentException("\u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
                        }
                        str2 = "PAID";
                        if (cursor8.getInt(2) == 1) {
                            throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0642\u0628\u0636 \u0642\u064a\u0645\u0629 \u0627\u0644\u0628\u064a\u0639 \u0641\u064a \u062d\u0633\u0627\u0628 \u0645\u0648\u0642\u0648\u0641".toString());
                        }
                        String string14 = cursor8.getString(0);
                        String string15 = cursor8.getString(1);
                        if (cursor8.getInt(2) == 1) {
                            z = true;
                        } else {
                            z = false;
                        }
                        Triple triple2 = new Triple(string14, string15, Boolean.valueOf(z));
                        CloseableKt.closeFinally(cursorRawQuery5, null);
                        str32 = (String) triple2.getFirst();
                        str33 = (String) triple2.getSecond();
                        if (!Intrinsics.areEqual(str33, "USD")) {
                            throw new IllegalArgumentException("\u0639\u0645\u0644\u0629 \u062d\u0633\u0627\u0628 \u0627\u0644\u0642\u0628\u0636 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u0629".toString());
                        }
                        if (syncProtocolRepository.isConfiguredSecondary()) {
                            cursorRawQuery6 = r10.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key=?", new String[]{"cash_account_sales_allowed:" + cashAccountId});
                            cursor9 = cursorRawQuery6;
                            if (cursor9.moveToFirst()) {
                                z2 = false;
                                CloseableKt.closeFinally(cursorRawQuery6, null);
                                if (!z2) {
                                    throw new IllegalArgumentException("\u0647\u0630\u0627 \u0627\u0644\u062d\u0633\u0627\u0628 \u063a\u064a\u0631 \u0645\u062e\u0635\u0635 \u0644\u0642\u0628\u0636 \u0645\u0628\u064a\u0639\u0627\u062a \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a".toString());
                                }
                            } else {
                                z2 = false;
                                CloseableKt.closeFinally(cursorRawQuery6, null);
                                if (!z2) {
                                    throw new IllegalArgumentException("\u0647\u0630\u0627 \u0627\u0644\u062d\u0633\u0627\u0628 \u063a\u064a\u0631 \u0645\u062e\u0635\u0635 \u0644\u0642\u0628\u0636 \u0645\u0628\u064a\u0639\u0627\u062a \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a".toString());
                                }
                            }
                            strInvoiceWarehouse = r16.invoiceWarehouse(r10, str6, invoiceId);
                            if (syncProtocolRepository.isConfiguredSecondary()) {
                                cursorRawQuery3 = r10.rawQuery(str37 + str39 + " WHERE " + str40 + "=?", new String[]{invoiceId});
                                Cursor cursor17 = cursorRawQuery3;
                                count = cursor17.getCount();
                                int i4 = 1;
                                if (1 <= count) {
                                }
                                throw new IllegalArgumentException("\u0639\u062f\u062f \u0623\u0635\u0646\u0627\u0641 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                            }
                            str9 = type;
                            str10 = operationId;
                            str11 = str8;
                            cursorRawQuery = r10.rawQuery(str35 + str39 + " WHERE " + str40 + "=?", new String[]{invoiceId});
                            cursor2 = cursorRawQuery;
                            r10 = r10;
                            r13 = r16;
                            if (cursor2.getCount() > 0) {
                                cursor = cursorRawQuery;
                                r17 = r10;
                                throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0627\u0639\u062a\u0645\u0627\u062f \u0641\u0627\u062a\u0648\u0631\u0629 \u0641\u0627\u0631\u063a\u0629".toString());
                            }
                            while (cursor2.moveToNext()) {
                                string = cursor2.getString(0);
                                string2 = cursor2.getString(1);
                                cursor6 = cursorRawQuery;
                                str22 = str6;
                                d = cursor2.getDouble(2);
                                j = cursor2.getLong(3);
                                Intrinsics.checkNotNull(string2);
                                zProductTracksInventory = r13.productTracksInventory(r10, string2);
                                if (Intrinsics.areEqual(str9, str4)) {
                                    dStockQuantity = r13.stockQuantity(r10, string2, strInvoiceWarehouse);
                                    if (d > dStockQuantity + 1.0E-7d) {
                                        throw new IllegalArgumentException(("\u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u063a\u064a\u0631 \u0643\u0627\u0641\u064d \u0644\u0644\u0645\u0646\u062a\u062c: " + r13.productName(r10, string2) + " (\u0627\u0644\u0645\u062a\u0648\u0641\u0631: " + dStockQuantity + ")").toString());
                                    }
                                }
                                if (zProductTracksInventory) {
                                    if (Intrinsics.areEqual(str9, str4)) {
                                        str23 = string2;
                                        str24 = string;
                                        cursor5 = cursor6;
                                        r58 = r10;
                                        c = 1;
                                        str25 = operationId;
                                        FifoCosting.INSTANCE.consumeForSale(r10, invoiceId, string, str23, strInvoiceWarehouse, d, obj2, (128 & 128) != 0);
                                        r59 = r58;
                                    } else {
                                        cursor5 = cursor6;
                                        str23 = string2;
                                        str24 = string;
                                        r59 = r10;
                                        c = 1;
                                        str25 = operationId;
                                    }
                                    str26 = str36;
                                    if (Intrinsics.areEqual(type, str26)) {
                                        d2 = d;
                                    } else {
                                        d2 = -d;
                                    }
                                    if (Intrinsics.areEqual(type, str26)) {
                                        str27 = str26;
                                    } else {
                                        str27 = str4;
                                    }
                                    str28 = str26;
                                    str29 = type;
                                    applyStockMovement(r59, str23, strInvoiceWarehouse, d2, j, str27, invoiceId, str25 + ":" + str24, obj2);
                                    r510 = r59;
                                } else {
                                    cursor5 = cursor6;
                                    str23 = string2;
                                    cursor2 = cursor2;
                                    strInvoiceWarehouse = strInvoiceWarehouse;
                                    str29 = str9;
                                    r510 = r10;
                                    str4 = str4;
                                    str11 = str11;
                                    str43 = str43;
                                    str22 = str22;
                                    str28 = str36;
                                    c = 1;
                                    str25 = operationId;
                                }
                                if (Intrinsics.areEqual(str29, str28)) {
                                    Object[] objArr3 = new Object[3];
                                    objArr3[0] = Long.valueOf(j);
                                    objArr3[c] = str11;
                                    objArr3[2] = str23;
                                    r14 = r510;
                                    r14.execSQL("UPDATE products SET purchase_cost_usd_cents=?,updated_at=? WHERE id=?", objArr3);
                                    r11 = r14;
                                } else {
                                    r11 = r510;
                                }
                                str9 = str29;
                                str36 = str28;
                                str10 = str25;
                                str4 = str4;
                                cursor2 = cursor2;
                                strInvoiceWarehouse = strInvoiceWarehouse;
                                str11 = str11;
                                cursorRawQuery = cursor5;
                                str43 = str43;
                                str6 = str22;
                                r13 = this;
                                r10 = r11;
                            }
                            str12 = str9;
                            cursor3 = cursorRawQuery;
                            String str412 = str6;
                            str13 = str4;
                            str14 = str11;
                            str15 = str43;
                            str16 = str10;
                            r17 = r10;
                            Unit unit6 = Unit.INSTANCE;
                            CloseableKt.closeFinally(cursor3, null);
                            if (cashSaleReceipt != null) {
                                str17 = invoiceId;
                                str18 = str13;
                                r9 = obj2;
                                r7 = obj;
                            } else {
                                str17 = invoiceId;
                                str18 = str13;
                                r9 = obj2;
                                r7 = obj;
                            }
                            if (Intrinsics.areEqual(str12, str18)) {
                                str19 = "POSTED";
                            } else {
                                str19 = "POSTED";
                            }
                            if (Intrinsics.areEqual(str12, str18)) {
                                str20 = str14;
                                r17.execSQL("UPDATE sales_invoices SET status=?,updated_at=?,posted_at=?,posted_operating_date=? WHERE id=? AND status='DRAFT'", new String[]{str19, str20, r9, r7, str17});
                            } else {
                                str20 = str14;
                                r17.execSQL("UPDATE purchase_invoices SET status=?,updated_at=? WHERE id=? AND status='DRAFT'", new String[]{str19, str20, str17});
                            }
                            cursorRawQuery2 = r17.rawQuery("SELECT total_usd_cents FROM " + str412 + " WHERE id=?", new String[]{invoiceId});
                            cursor4 = cursorRawQuery2;
                            if (!cursor4.moveToFirst()) {
                                throw new IllegalArgumentException("Failed requirement.".toString());
                            }
                            long j7 = cursor4.getLong(0);
                            CloseableKt.closeFinally(cursorRawQuery2, null);
                            if (cashAccountId != null) {
                                str21 = "\"" + cashAccountId + "\"";
                                if (str21 == null) {
                                    str21 = "null";
                                }
                            } else {
                                str21 = "null";
                            }
                            ContentValues contentValues5 = new ContentValues();
                            contentValues5.put("id", UUID.randomUUID().toString());
                            contentValues5.put("user_id", actorUserId);
                            StringBuilder sb6 = new StringBuilder();
                            sb6.append(str41);
                            sb6.append(str12);
                            String str413 = str42;
                            sb6.append(str413);
                            contentValues5.put("action", sb6.toString());
                            contentValues5.put("entity_type", str12 + str413);
                            contentValues5.put("entity_id", str17);
                            contentValues5.put("before_json", "{\"status\":\"DRAFT\"}");
                            contentValues5.put("after_json", "{\"status\":\"" + str19 + "\",\"total_usd_cents\":" + j7 + ",\"operation_id\":\"" + str16 + "\",\"cash_account_id\":" + str21 + "}");
                            SyncDeviceIdentity.Companion companion8 = SyncDeviceIdentity.INSTANCE;
                            Context context6 = this.context;
                            Intrinsics.checkNotNullExpressionValue(context6, str15);
                            contentValues5.put("device_id", companion8.getOrCreate(context6));
                            contentValues5.put("created_at", str20);
                            Unit unit7 = Unit.INSTANCE;
                            r17.insertOrThrow("audit_log", null, contentValues5);
                            Context context7 = this.context;
                            Intrinsics.checkNotNullExpressionValue(context7, str15);
                            new SyncProtocolRepository(context7, null, 2, false ? 1 : 0).enqueueInvoicePosting(r17, actorUserId, operationId, type, invoiceId, (BERTags.PRIVATE & 32) != 0 ? null : cashAccountId, (BERTags.PRIVATE & 64) != 0 ? null : null, (BERTags.PRIVATE & 128) != 0 ? null : null);
                            registerOperation(r17, operationId, string5, strHash, invoiceId, str20);
                            r17.setTransactionSuccessful();
                            r17.endTransaction();
                            Result.Companion companion9 = Result.INSTANCE;
                            SyncRelaySession.INSTANCE.flushPending();
                            Result.m381constructorimpl(Unit.INSTANCE);
                            return true;
                            th2 = th;
                            r17 = r17;
                            throw th2;
                        }
                        if (!Intrinsics.areEqual(str32, "CASH_BOX")) {
                            if (!Intrinsics.areEqual(str32, "WALLET")) {
                                throw new IllegalArgumentException("\u064a\u062c\u0628 \u0627\u062e\u062a\u064a\u0627\u0631 \u0635\u0646\u062f\u0648\u0642 \u0646\u0642\u062f\u064a \u0623\u0648 \u0645\u062d\u0641\u0638\u0629 \u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a\u0629 \u0646\u0634\u0637\u0629 \u0644\u0642\u0628\u0636 \u0627\u0644\u0645\u0628\u064a\u0639\u0627\u062a".toString());
                            }
                            if (Intrinsics.areEqual(str33, "USD")) {
                                jUsdCentsToSyp = j3;
                            } else {
                                if (!Intrinsics.areEqual(str33, "SYP")) {
                                    throw new IllegalStateException("\u0639\u0645\u0644\u0629 \u062d\u0633\u0627\u0628 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u0629".toString());
                                }
                                if (j2 <= 0) {
                                    throw new IllegalArgumentException("\u064a\u062c\u0628 \u062a\u0633\u062c\u064a\u0644 \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0642\u0628\u0644 \u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u0646\u0642\u062f\u064a \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629".toString());
                                }
                                jUsdCentsToSyp = CurrencyConverter.INSTANCE.usdCentsToSyp(j3, j2);
                            }
                            if (j3 > 0) {
                                throw new IllegalArgumentException("\u0642\u064a\u0645\u0629 \u0627\u0644\u0628\u064a\u0639 \u0623\u0635\u063a\u0631 \u0645\u0646 \u0623\u0635\u063a\u0631 \u0648\u062d\u062f\u0629 \u0646\u0642\u062f\u064a\u0629".toString());
                            }
                            str42 = str42;
                            str36 = str36;
                            obj = strOperatingDateForTimestamp;
                            str41 = str41;
                            cashSaleReceipt = cashSaleReceipt2;
                            str37 = str37;
                            str6 = str38;
                            str35 = str35;
                            str4 = "SALE";
                            CashSaleReceipt cashSaleReceipt3 = new CashSaleReceipt(cashAccountId, str33, jUsdCentsToSyp, j2);
                            obj2 = str3;
                        } else {
                            if (!Intrinsics.areEqual(str32, "WALLET")) {
                                throw new IllegalArgumentException("\u064a\u062c\u0628 \u0627\u062e\u062a\u064a\u0627\u0631 \u0635\u0646\u062f\u0648\u0642 \u0646\u0642\u062f\u064a \u0623\u0648 \u0645\u062d\u0641\u0638\u0629 \u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a\u0629 \u0646\u0634\u0637\u0629 \u0644\u0642\u0628\u0636 \u0627\u0644\u0645\u0628\u064a\u0639\u0627\u062a".toString());
                            }
                            if (Intrinsics.areEqual(str33, "USD")) {
                                jUsdCentsToSyp = j3;
                            } else {
                                if (!Intrinsics.areEqual(str33, "SYP")) {
                                    throw new IllegalStateException("\u0639\u0645\u0644\u0629 \u062d\u0633\u0627\u0628 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u0629".toString());
                                }
                                if (j2 <= 0) {
                                    throw new IllegalArgumentException("\u064a\u062c\u0628 \u062a\u0633\u062c\u064a\u0644 \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0642\u0628\u0644 \u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u0646\u0642\u062f\u064a \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629".toString());
                                }
                                jUsdCentsToSyp = CurrencyConverter.INSTANCE.usdCentsToSyp(j3, j2);
                            }
                            if (j3 > 0) {
                                throw new IllegalArgumentException("\u0642\u064a\u0645\u0629 \u0627\u0644\u0628\u064a\u0639 \u0623\u0635\u063a\u0631 \u0645\u0646 \u0623\u0635\u063a\u0631 \u0648\u062d\u062f\u0629 \u0646\u0642\u062f\u064a\u0629".toString());
                            }
                            str42 = str42;
                            str36 = str36;
                            obj = strOperatingDateForTimestamp;
                            str41 = str41;
                            cashSaleReceipt = cashSaleReceipt3;
                            str37 = str37;
                            str6 = str38;
                            str35 = str35;
                            str4 = "SALE";
                            CashSaleReceipt cashSaleReceipt4 = new CashSaleReceipt(cashAccountId, str33, jUsdCentsToSyp, j2);
                            obj2 = str3;
                        }
                        strInvoiceWarehouse = r16.invoiceWarehouse(r10, str6, invoiceId);
                        if (syncProtocolRepository.isConfiguredSecondary()) {
                            cursorRawQuery3 = r10.rawQuery(str37 + str39 + " WHERE " + str40 + "=?", new String[]{invoiceId});
                            Cursor cursor18 = cursorRawQuery3;
                            count = cursor18.getCount();
                            int i5 = 1;
                            if (1 <= count) {
                            }
                            throw new IllegalArgumentException("\u0639\u062f\u062f \u0623\u0635\u0646\u0627\u0641 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                        }
                        str9 = type;
                        str10 = operationId;
                        str11 = str8;
                        cursorRawQuery = r10.rawQuery(str35 + str39 + " WHERE " + str40 + "=?", new String[]{invoiceId});
                        cursor2 = cursorRawQuery;
                        r10 = r10;
                        r13 = r16;
                        if (cursor2.getCount() > 0) {
                            cursor = cursorRawQuery;
                            r17 = r10;
                            throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0627\u0639\u062a\u0645\u0627\u062f \u0641\u0627\u062a\u0648\u0631\u0629 \u0641\u0627\u0631\u063a\u0629".toString());
                        }
                        while (cursor2.moveToNext()) {
                            string = cursor2.getString(0);
                            string2 = cursor2.getString(1);
                            cursor6 = cursorRawQuery;
                            str22 = str6;
                            d = cursor2.getDouble(2);
                            j = cursor2.getLong(3);
                            Intrinsics.checkNotNull(string2);
                            zProductTracksInventory = r13.productTracksInventory(r10, string2);
                            if (Intrinsics.areEqual(str9, str4)) {
                                dStockQuantity = r13.stockQuantity(r10, string2, strInvoiceWarehouse);
                                if (d > dStockQuantity + 1.0E-7d) {
                                    throw new IllegalArgumentException(("\u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u063a\u064a\u0631 \u0643\u0627\u0641\u064d \u0644\u0644\u0645\u0646\u062a\u062c: " + r13.productName(r10, string2) + " (\u0627\u0644\u0645\u062a\u0648\u0641\u0631: " + dStockQuantity + ")").toString());
                                }
                            }
                            if (zProductTracksInventory) {
                                if (Intrinsics.areEqual(str9, str4)) {
                                    str23 = string2;
                                    str24 = string;
                                    cursor5 = cursor6;
                                    r58 = r10;
                                    c = 1;
                                    str25 = operationId;
                                    FifoCosting.INSTANCE.consumeForSale(r10, invoiceId, string, str23, strInvoiceWarehouse, d, obj2, (128 & 128) != 0);
                                    r59 = r58;
                                } else {
                                    cursor5 = cursor6;
                                    str23 = string2;
                                    str24 = string;
                                    r59 = r10;
                                    c = 1;
                                    str25 = operationId;
                                }
                                str26 = str36;
                                if (Intrinsics.areEqual(type, str26)) {
                                    d2 = d;
                                } else {
                                    d2 = -d;
                                }
                                if (Intrinsics.areEqual(type, str26)) {
                                    str27 = str26;
                                } else {
                                    str27 = str4;
                                }
                                str28 = str26;
                                str29 = type;
                                applyStockMovement(r59, str23, strInvoiceWarehouse, d2, j, str27, invoiceId, str25 + ":" + str24, obj2);
                                r510 = r59;
                            } else {
                                cursor5 = cursor6;
                                str23 = string2;
                                cursor2 = cursor2;
                                strInvoiceWarehouse = strInvoiceWarehouse;
                                str29 = str9;
                                r510 = r10;
                                str4 = str4;
                                str11 = str11;
                                str43 = str43;
                                str22 = str22;
                                str28 = str36;
                                c = 1;
                                str25 = operationId;
                            }
                            if (Intrinsics.areEqual(str29, str28)) {
                                Object[] objArr4 = new Object[3];
                                objArr4[0] = Long.valueOf(j);
                                objArr4[c] = str11;
                                objArr4[2] = str23;
                                r14 = r510;
                                r14.execSQL("UPDATE products SET purchase_cost_usd_cents=?,updated_at=? WHERE id=?", objArr4);
                                r11 = r14;
                            } else {
                                r11 = r510;
                            }
                            str9 = str29;
                            str36 = str28;
                            str10 = str25;
                            str4 = str4;
                            cursor2 = cursor2;
                            strInvoiceWarehouse = strInvoiceWarehouse;
                            str11 = str11;
                            cursorRawQuery = cursor5;
                            str43 = str43;
                            str6 = str22;
                            r13 = this;
                            r10 = r11;
                        }
                        str12 = str9;
                        cursor3 = cursorRawQuery;
                        String str414 = str6;
                        str13 = str4;
                        str14 = str11;
                        str15 = str43;
                        str16 = str10;
                        r17 = r10;
                        Unit unit8 = Unit.INSTANCE;
                        CloseableKt.closeFinally(cursor3, null);
                        if (cashSaleReceipt != null) {
                            str17 = invoiceId;
                            str18 = str13;
                            r9 = obj2;
                            r7 = obj;
                        } else {
                            str17 = invoiceId;
                            str18 = str13;
                            r9 = obj2;
                            r7 = obj;
                        }
                        if (Intrinsics.areEqual(str12, str18)) {
                            str19 = "POSTED";
                        } else {
                            str19 = "POSTED";
                        }
                        if (Intrinsics.areEqual(str12, str18)) {
                            str20 = str14;
                            r17.execSQL("UPDATE sales_invoices SET status=?,updated_at=?,posted_at=?,posted_operating_date=? WHERE id=? AND status='DRAFT'", new String[]{str19, str20, r9, r7, str17});
                        } else {
                            str20 = str14;
                            r17.execSQL("UPDATE purchase_invoices SET status=?,updated_at=? WHERE id=? AND status='DRAFT'", new String[]{str19, str20, str17});
                        }
                        cursorRawQuery2 = r17.rawQuery("SELECT total_usd_cents FROM " + str414 + " WHERE id=?", new String[]{invoiceId});
                        cursor4 = cursorRawQuery2;
                        if (!cursor4.moveToFirst()) {
                            throw new IllegalArgumentException("Failed requirement.".toString());
                        }
                        long j8 = cursor4.getLong(0);
                        CloseableKt.closeFinally(cursorRawQuery2, null);
                        if (cashAccountId != null) {
                            str21 = "\"" + cashAccountId + "\"";
                            if (str21 == null) {
                                str21 = "null";
                            }
                        } else {
                            str21 = "null";
                        }
                        ContentValues contentValues6 = new ContentValues();
                        contentValues6.put("id", UUID.randomUUID().toString());
                        contentValues6.put("user_id", actorUserId);
                        StringBuilder sb7 = new StringBuilder();
                        sb7.append(str41);
                        sb7.append(str12);
                        String str415 = str42;
                        sb7.append(str415);
                        contentValues6.put("action", sb7.toString());
                        contentValues6.put("entity_type", str12 + str415);
                        contentValues6.put("entity_id", str17);
                        contentValues6.put("before_json", "{\"status\":\"DRAFT\"}");
                        contentValues6.put("after_json", "{\"status\":\"" + str19 + "\",\"total_usd_cents\":" + j8 + ",\"operation_id\":\"" + str16 + "\",\"cash_account_id\":" + str21 + "}");
                        SyncDeviceIdentity.Companion companion10 = SyncDeviceIdentity.INSTANCE;
                        Context context8 = this.context;
                        Intrinsics.checkNotNullExpressionValue(context8, str15);
                        contentValues6.put("device_id", companion10.getOrCreate(context8));
                        contentValues6.put("created_at", str20);
                        Unit unit9 = Unit.INSTANCE;
                        r17.insertOrThrow("audit_log", null, contentValues6);
                        Context context9 = this.context;
                        Intrinsics.checkNotNullExpressionValue(context9, str15);
                        new SyncProtocolRepository(context9, null, 2, false ? 1 : 0).enqueueInvoicePosting(r17, actorUserId, operationId, type, invoiceId, (BERTags.PRIVATE & 32) != 0 ? null : cashAccountId, (BERTags.PRIVATE & 64) != 0 ? null : null, (BERTags.PRIVATE & 128) != 0 ? null : null);
                        registerOperation(r17, operationId, string5, strHash, invoiceId, str20);
                        r17.setTransactionSuccessful();
                        r17.endTransaction();
                        Result.Companion companion11 = Result.INSTANCE;
                        SyncRelaySession.INSTANCE.flushPending();
                        Result.m381constructorimpl(Unit.INSTANCE);
                        return true;
                        th2 = th;
                        r17 = r17;
                        throw th2;
                    }
                    if (cashAccountId == null) {
                        throw new IllegalArgumentException("\u0644\u0627 \u064a\u062c\u0648\u0632 \u0631\u0628\u0637 \u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u0622\u062c\u0644 \u0628\u062a\u062d\u0635\u064a\u0644 \u0646\u0642\u062f\u064a \u0639\u0646\u062f \u0627\u0644\u0627\u0639\u062a\u0645\u0627\u062f".toString());
                    }
                    str34 = str31;
                    if (str34 != null) {
                        z3 = true;
                    } else {
                        z3 = true;
                    }
                    if (!z3) {
                        throw new IllegalArgumentException("\u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u0622\u062c\u0644 \u064a\u062a\u0637\u0644\u0628 \u0632\u0628\u0648\u0646\u0627\u064b \u0645\u062d\u062f\u062f\u0627\u064b".toString());
                    }
                    if (j2 > 0) {
                        throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0627\u0639\u062a\u0645\u0627\u062f \u0628\u064a\u0639 \u0622\u062c\u0644 \u062f\u0648\u0646 \u0633\u0639\u0631 \u0635\u0631\u0641 \u0645\u062d\u0641\u0648\u0638".toString());
                    }
                    str4 = "SALE";
                    str6 = str38;
                    str5 = str3;
                } else {
                    str4 = "SALE";
                    str5 = str3;
                    str6 = str38;
                    str7 = "\\d{4}-\\d{2}-\\d{2}";
                    str8 = strTimestamp;
                }
                cashSaleReceipt = null;
                obj2 = str5;
                obj = strOperatingDateForTimestamp;
                strInvoiceWarehouse = r16.invoiceWarehouse(r10, str6, invoiceId);
                if (syncProtocolRepository.isConfiguredSecondary()) {
                    cursorRawQuery3 = r10.rawQuery(str37 + str39 + " WHERE " + str40 + "=?", new String[]{invoiceId});
                    Cursor cursor19 = cursorRawQuery3;
                    count = cursor19.getCount();
                    int i6 = 1;
                    if (1 <= count) {
                    }
                    throw new IllegalArgumentException("\u0639\u062f\u062f \u0623\u0635\u0646\u0627\u0641 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                }
                str9 = type;
                str10 = operationId;
                str11 = str8;
                cursorRawQuery = r10.rawQuery(str35 + str39 + " WHERE " + str40 + "=?", new String[]{invoiceId});
                cursor2 = cursorRawQuery;
                r10 = r10;
                r13 = r16;
                if (cursor2.getCount() > 0) {
                    cursor = cursorRawQuery;
                    r17 = r10;
                    throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0627\u0639\u062a\u0645\u0627\u062f \u0641\u0627\u062a\u0648\u0631\u0629 \u0641\u0627\u0631\u063a\u0629".toString());
                }
                while (cursor2.moveToNext()) {
                    string = cursor2.getString(0);
                    string2 = cursor2.getString(1);
                    cursor6 = cursorRawQuery;
                    str22 = str6;
                    d = cursor2.getDouble(2);
                    j = cursor2.getLong(3);
                    Intrinsics.checkNotNull(string2);
                    zProductTracksInventory = r13.productTracksInventory(r10, string2);
                    if (Intrinsics.areEqual(str9, str4)) {
                        dStockQuantity = r13.stockQuantity(r10, string2, strInvoiceWarehouse);
                        if (d > dStockQuantity + 1.0E-7d) {
                            throw new IllegalArgumentException(("\u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u063a\u064a\u0631 \u0643\u0627\u0641\u064d \u0644\u0644\u0645\u0646\u062a\u062c: " + r13.productName(r10, string2) + " (\u0627\u0644\u0645\u062a\u0648\u0641\u0631: " + dStockQuantity + ")").toString());
                        }
                    }
                    if (zProductTracksInventory) {
                        if (Intrinsics.areEqual(str9, str4)) {
                            str23 = string2;
                            str24 = string;
                            cursor5 = cursor6;
                            r58 = r10;
                            c = 1;
                            str25 = operationId;
                            FifoCosting.INSTANCE.consumeForSale(r10, invoiceId, string, str23, strInvoiceWarehouse, d, obj2, (128 & 128) != 0);
                            r59 = r58;
                        } else {
                            cursor5 = cursor6;
                            str23 = string2;
                            str24 = string;
                            r59 = r10;
                            c = 1;
                            str25 = operationId;
                        }
                        str26 = str36;
                        if (Intrinsics.areEqual(type, str26)) {
                            d2 = d;
                        } else {
                            d2 = -d;
                        }
                        if (Intrinsics.areEqual(type, str26)) {
                            str27 = str26;
                        } else {
                            str27 = str4;
                        }
                        str28 = str26;
                        str29 = type;
                        applyStockMovement(r59, str23, strInvoiceWarehouse, d2, j, str27, invoiceId, str25 + ":" + str24, obj2);
                        r510 = r59;
                    } else {
                        cursor5 = cursor6;
                        str23 = string2;
                        cursor2 = cursor2;
                        strInvoiceWarehouse = strInvoiceWarehouse;
                        str29 = str9;
                        r510 = r10;
                        str4 = str4;
                        str11 = str11;
                        str43 = str43;
                        str22 = str22;
                        str28 = str36;
                        c = 1;
                        str25 = operationId;
                    }
                    if (Intrinsics.areEqual(str29, str28)) {
                        Object[] objArr5 = new Object[3];
                        objArr5[0] = Long.valueOf(j);
                        objArr5[c] = str11;
                        objArr5[2] = str23;
                        r14 = r510;
                        r14.execSQL("UPDATE products SET purchase_cost_usd_cents=?,updated_at=? WHERE id=?", objArr5);
                        r11 = r14;
                    } else {
                        r11 = r510;
                    }
                    str9 = str29;
                    str36 = str28;
                    str10 = str25;
                    str4 = str4;
                    cursor2 = cursor2;
                    strInvoiceWarehouse = strInvoiceWarehouse;
                    str11 = str11;
                    cursorRawQuery = cursor5;
                    str43 = str43;
                    str6 = str22;
                    r13 = this;
                    r10 = r11;
                }
                str12 = str9;
                cursor3 = cursorRawQuery;
                String str416 = str6;
                str13 = str4;
                str14 = str11;
                str15 = str43;
                str16 = str10;
                r17 = r10;
                Unit unit10 = Unit.INSTANCE;
                CloseableKt.closeFinally(cursor3, null);
                if (cashSaleReceipt != null) {
                    str17 = invoiceId;
                    str18 = str13;
                    r9 = obj2;
                    r7 = obj;
                } else {
                    str17 = invoiceId;
                    str18 = str13;
                    r9 = obj2;
                    r7 = obj;
                }
                if (Intrinsics.areEqual(str12, str18)) {
                    str19 = "POSTED";
                } else {
                    str19 = "POSTED";
                }
                if (Intrinsics.areEqual(str12, str18)) {
                    str20 = str14;
                    r17.execSQL("UPDATE sales_invoices SET status=?,updated_at=?,posted_at=?,posted_operating_date=? WHERE id=? AND status='DRAFT'", new String[]{str19, str20, r9, r7, str17});
                } else {
                    str20 = str14;
                    r17.execSQL("UPDATE purchase_invoices SET status=?,updated_at=? WHERE id=? AND status='DRAFT'", new String[]{str19, str20, str17});
                }
                cursorRawQuery2 = r17.rawQuery("SELECT total_usd_cents FROM " + str416 + " WHERE id=?", new String[]{invoiceId});
                cursor4 = cursorRawQuery2;
                if (!cursor4.moveToFirst()) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                long j9 = cursor4.getLong(0);
                CloseableKt.closeFinally(cursorRawQuery2, null);
                if (cashAccountId != null) {
                    str21 = "\"" + cashAccountId + "\"";
                    if (str21 == null) {
                        str21 = "null";
                    }
                } else {
                    str21 = "null";
                }
                ContentValues contentValues7 = new ContentValues();
                contentValues7.put("id", UUID.randomUUID().toString());
                contentValues7.put("user_id", actorUserId);
                StringBuilder sb8 = new StringBuilder();
                sb8.append(str41);
                sb8.append(str12);
                String str417 = str42;
                sb8.append(str417);
                contentValues7.put("action", sb8.toString());
                contentValues7.put("entity_type", str12 + str417);
                contentValues7.put("entity_id", str17);
                contentValues7.put("before_json", "{\"status\":\"DRAFT\"}");
                contentValues7.put("after_json", "{\"status\":\"" + str19 + "\",\"total_usd_cents\":" + j9 + ",\"operation_id\":\"" + str16 + "\",\"cash_account_id\":" + str21 + "}");
                SyncDeviceIdentity.Companion companion12 = SyncDeviceIdentity.INSTANCE;
                Context context10 = this.context;
                Intrinsics.checkNotNullExpressionValue(context10, str15);
                contentValues7.put("device_id", companion12.getOrCreate(context10));
                contentValues7.put("created_at", str20);
                Unit unit11 = Unit.INSTANCE;
                r17.insertOrThrow("audit_log", null, contentValues7);
                Context context11 = this.context;
                Intrinsics.checkNotNullExpressionValue(context11, str15);
                new SyncProtocolRepository(context11, null, 2, false ? 1 : 0).enqueueInvoicePosting(r17, actorUserId, operationId, type, invoiceId, (BERTags.PRIVATE & 32) != 0 ? null : cashAccountId, (BERTags.PRIVATE & 64) != 0 ? null : null, (BERTags.PRIVATE & 128) != 0 ? null : null);
                registerOperation(r17, operationId, string5, strHash, invoiceId, str20);
                r17.setTransactionSuccessful();
                r17.endTransaction();
                Result.Companion companion13 = Result.INSTANCE;
                SyncRelaySession.INSTANCE.flushPending();
                Result.m381constructorimpl(Unit.INSTANCE);
                return true;
                th2 = th;
                r17 = r17;
                throw th2;
                th = th;
                r12 = r17;
            }
        } catch (Throwable th45) {
            th = th45;
            r17 = writableDatabase;
        }
        r12.endTransaction();
        throw th;
    }

    public final List<InvoiceSummary> listPurchaseInvoices() {
        return listInvoices("PURCHASE");
    }

    public final List<InvoiceSummary> listSalesInvoices() {
        return listInvoices("SALE");
    }

    private final List<InvoiceSummary> listInvoices(String type) throws IOException {
        String str = Intrinsics.areEqual(type, "PURCHASE") ? "purchase_invoices" : "sales_invoices";
        String str2 = Intrinsics.areEqual(type, "PURCHASE") ? "suppliers" : "customers";
        String str3 = Intrinsics.areEqual(type, "PURCHASE") ? "supplier_id" : "customer_id";
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery(StringsKt.trimIndent("SELECT i.id,i.invoice_number,i.invoice_date,i.status,i.total_usd_cents,p.name\n               FROM " + str + " i LEFT JOIN " + str2 + " p ON p.id=i." + str3 + "\n               ORDER BY i.created_at DESC"), (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            while (cursor.moveToNext()) {
                String string = cursor.getString(0);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String string2 = cursor.getString(1);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                String string3 = cursor.getString(2);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                String string4 = cursor.getString(3);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                arrayList.add(new InvoiceSummary(string, string2, string3, string4, cursor.getLong(4), cursor.isNull(5) ? null : cursor.getString(5)));
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

    private final void validateLines(SQLiteDatabase db, List<InvoiceLineInput> lines) throws IOException {
        if (lines.isEmpty()) {
            throw new IllegalArgumentException("\u0623\u0636\u0641 \u0635\u0646\u0641\u0627\u064b \u0648\u0627\u062d\u062f\u0627\u064b \u0639\u0644\u0649 \u0627\u0644\u0623\u0642\u0644 \u0625\u0644\u0649 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629".toString());
        }
        for (InvoiceLineInput invoiceLineInput : lines) {
            if (StringsKt.isBlank(invoiceLineInput.getProductId())) {
                throw new IllegalArgumentException("\u064a\u0648\u062c\u062f \u0635\u0646\u0641 \u063a\u064a\u0631 \u0645\u062d\u062f\u062f".toString());
            }
            if (invoiceLineInput.getQuantity().signum() <= 0) {
                throw new IllegalArgumentException("\u0627\u0644\u0643\u0645\u064a\u0629 \u064a\u062c\u0628 \u0623\u0646 \u062a\u0643\u0648\u0646 \u0623\u0643\u0628\u0631 \u0645\u0646 \u0635\u0641\u0631".toString());
            }
            if (invoiceLineInput.getUnitPriceUsdCents() < 0) {
                throw new IllegalArgumentException("\u0627\u0644\u0633\u0639\u0631 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0633\u0627\u0644\u0628\u0627\u064b".toString());
            }
            Cursor cursorRawQuery = db.rawQuery("SELECT u.decimal_places FROM products p JOIN units u ON u.id=p.unit_id\n                   WHERE p.id=? AND p.is_active=1", new String[]{invoiceLineInput.getProductId()});
            try {
                Cursor cursor = cursorRawQuery;
                if (!cursor.moveToFirst()) {
                    throw new IllegalArgumentException(("\u0627\u0644\u0645\u0646\u062a\u062c \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0623\u0648 \u0645\u0648\u0642\u0648\u0641: " + invoiceLineInput.getProductId()).toString());
                }
                int i = cursor.getInt(0);
                CloseableKt.closeFinally(cursorRawQuery, null);
                if (RangesKt.coerceAtLeast(MainActivity$$ExternalSyntheticBackport0.m(invoiceLineInput.getQuantity()).scale(), 0) > i) {
                    throw new IllegalArgumentException(("\u062f\u0642\u0629 \u0627\u0644\u0643\u0645\u064a\u0629 \u0644\u0627 \u062a\u062a\u0648\u0627\u0641\u0642 \u0645\u0639 \u0648\u062d\u062f\u0629 \u0642\u064a\u0627\u0633 \u0627\u0644\u0645\u0646\u062a\u062c (\u0627\u0644\u0645\u0646\u0627\u0632\u0644 \u0627\u0644\u0639\u0634\u0631\u064a\u0629 \u0627\u0644\u0645\u0633\u0645\u0648\u062d\u0629: " + i + ")").toString());
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
    }

    private final long lineTotal(InvoiceLineInput line) {
        return line.getQuantity().multiply(BigDecimal.valueOf(line.getUnitPriceUsdCents())).setScale(0, RoundingMode.HALF_UP).longValueExact();
    }

    private final long productCost(SQLiteDatabase db, String productId) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT purchase_cost_usd_cents FROM products WHERE id=?", new String[]{productId});
        try {
            Cursor cursor = cursorRawQuery;
            if (cursor.moveToFirst()) {
                long j = cursor.getLong(0);
                CloseableKt.closeFinally(cursorRawQuery, null);
                return j;
            }
            throw new IllegalArgumentException(("\u0627\u0644\u0645\u0646\u062a\u062c \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f: " + productId).toString());
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final boolean productTracksInventory(SQLiteDatabase db, String productId) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT track_inventory FROM products WHERE id=?", new String[]{productId});
        try {
            Cursor cursor = cursorRawQuery;
            if (cursor.moveToFirst()) {
                boolean z = cursor.getInt(0) == 1;
                CloseableKt.closeFinally(cursorRawQuery, null);
                return z;
            }
            throw new IllegalArgumentException(("\u0627\u0644\u0645\u0646\u062a\u062c \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f: " + productId).toString());
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final String productName(SQLiteDatabase db, String productId) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT name FROM products WHERE id=?", new String[]{productId});
        try {
            Cursor cursor = cursorRawQuery;
            if (cursor.moveToFirst()) {
                productId = cursor.getString(0);
            }
            CloseableKt.closeFinally(cursorRawQuery, null);
            Intrinsics.checkNotNullExpressionValue(productId, "use(...)");
            return productId;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final double stockQuantity(SQLiteDatabase db, String productId, String warehouseId) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT quantity FROM inventory_balances WHERE product_id=? AND warehouse_id=?", new String[]{productId, warehouseId});
        try {
            Cursor cursor = cursorRawQuery;
            double d = cursor.moveToFirst() ? cursor.getDouble(0) : 0.0d;
            CloseableKt.closeFinally(cursorRawQuery, null);
            return d;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final void applyStockMovement(SQLiteDatabase db, String productId, String warehouseId, double delta, long unitCost, String movementType, String invoiceId, String movementOperationId, String now) throws IOException {
        db.execSQL("UPDATE inventory_balances SET quantity=quantity+?,updated_at=? WHERE product_id=? AND warehouse_id=?", new Object[]{Double.valueOf(delta), now, productId, warehouseId});
        Cursor cursorRawQuery = db.rawQuery("SELECT 1 FROM inventory_balances WHERE product_id=? AND warehouse_id=?", new String[]{productId, warehouseId});
        try {
            boolean zMoveToFirst = cursorRawQuery.moveToFirst();
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (!zMoveToFirst) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("product_id", productId);
                contentValues.put("warehouse_id", warehouseId);
                contentValues.put("quantity", Double.valueOf(delta));
                contentValues.put("updated_at", now);
                db.insertOrThrow("inventory_balances", null, contentValues);
            }
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            String str = Intrinsics.areEqual(movementType, "PURCHASE") ? "PURCHASE_INVOICE" : "SALES_INVOICE";
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("id", string);
            contentValues2.put("product_id", productId);
            contentValues2.put("warehouse_id", warehouseId);
            contentValues2.put("movement_type", movementType);
            contentValues2.put("quantity_delta", Double.valueOf(delta));
            contentValues2.put("unit_cost_usd_cents", Long.valueOf(unitCost));
            contentValues2.put("source_type", str);
            contentValues2.put("source_id", invoiceId);
            contentValues2.put("operation_id", movementOperationId);
            contentValues2.put("created_at", now);
            db.insertOrThrow("inventory_movements", null, contentValues2);
            if (delta > 0.0d) {
                FifoCosting.INSTANCE.addLayer(db, string, productId, warehouseId, delta, unitCost, str, invoiceId, now, StringsKt.substringAfterLast$default(movementOperationId, ':', (String) null, 2, (Object) null));
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

    private final String defaultWarehouseId(SQLiteDatabase db) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT id FROM warehouses WHERE is_default=1 AND is_active=1 LIMIT 1", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            if (cursor.moveToFirst()) {
                String string = cursor.getString(0);
                CloseableKt.closeFinally(cursorRawQuery, null);
                Intrinsics.checkNotNullExpressionValue(string, "use(...)");
                return string;
            }
            throw new IllegalStateException("\u0627\u0644\u0645\u0633\u062a\u0648\u062f\u0639 \u0627\u0644\u0631\u0626\u064a\u0633\u064a \u063a\u064a\u0631 \u0645\u0647\u064a\u0623".toString());
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

    private final String invoiceStatus(SQLiteDatabase db, String table, String id) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT status FROM " + table + " WHERE id=?", new String[]{id});
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

    private final String invoiceWarehouse(SQLiteDatabase db, String table, String id) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT warehouse_id FROM " + table + " WHERE id=?", new String[]{id});
        try {
            Cursor cursor = cursorRawQuery;
            if (cursor.moveToFirst()) {
                String string = cursor.getString(0);
                CloseableKt.closeFinally(cursorRawQuery, null);
                Intrinsics.checkNotNullExpressionValue(string, "use(...)");
                return string;
            }
            throw new IllegalArgumentException("\u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629".toString());
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final String invoiceNumber(SQLiteDatabase db, String table, String id) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT invoice_number FROM " + table + " WHERE id=?", new String[]{id});
        try {
            Cursor cursor = cursorRawQuery;
            if (cursor.moveToFirst()) {
                String string = cursor.getString(0);
                CloseableKt.closeFinally(cursorRawQuery, null);
                Intrinsics.checkNotNullExpressionValue(string, "use(...)");
                return string;
            }
            throw new IllegalArgumentException("\u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629".toString());
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final String nextInvoiceNumber(SQLiteDatabase db, String table, String type, String date) throws IOException {
        String str = Intrinsics.areEqual(type, "PURCHASE") ? "PUR" : "SAL";
        String str2 = str + "-" + StringsKt.take(StringsKt.replace$default(date, "-", "", false, 4, (Object) null), 8) + "-";
        Cursor cursorRawQuery = db.rawQuery("SELECT COUNT(*) FROM " + table + " WHERE invoice_number LIKE ?", new String[]{str2 + "%"});
        try {
            Cursor cursor = cursorRawQuery;
            int i = cursor.moveToFirst() ? 1 + cursor.getInt(0) : 1;
            CloseableKt.closeFinally(cursorRawQuery, null);
            return str2 + StringsKt.padStart(String.valueOf(i), 4, '0');
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    /* JADX INFO: compiled from: InvoiceRepository.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J)\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t\u00a8\u0006\u0016"}, d2 = {"Lcom/hazem/dailystore/data/InvoiceRepository$ExistingOperation;", "", "type", "", "payloadHash", "reference", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getType", "()Ljava/lang/String;", "getPayloadHash", "getReference", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
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

    private final String hashPayload(String type, String invoiceId, String partyId, String date, long discount, String notes, List<InvoiceLineInput> lines, String saleType) {
        StringBuilder sb = new StringBuilder();
        sb.append(type);
        sb.append('|');
        if (invoiceId == null) {
            invoiceId = "";
        }
        sb.append(invoiceId);
        sb.append('|');
        if (partyId == null) {
            partyId = "";
        }
        sb.append(partyId);
        sb.append('|');
        sb.append(date);
        sb.append('|');
        sb.append(discount);
        sb.append('|');
        if (notes == null) {
            notes = "";
        }
        sb.append(notes);
        sb.append('|');
        if (saleType == null) {
            saleType = "";
        }
        sb.append(saleType);
        for (InvoiceLineInput invoiceLineInput : lines) {
            sb.append('|');
            sb.append(invoiceLineInput.getProductId());
            sb.append(':');
            sb.append(MainActivity$$ExternalSyntheticBackport0.m(invoiceLineInput.getQuantity()).toPlainString());
            sb.append(':');
            sb.append(invoiceLineInput.getUnitPriceUsdCents());
        }
        return hash(sb.toString());
    }

    private final String hash(String value) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = value.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrDigest = messageDigest.digest(bytes);
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
        return ArraysKt.joinToString$default(bArrDigest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.hazem.dailystore.data.InvoiceRepository$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return InvoiceRepository.hash$lambda$95(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence hash$lambda$95(byte b) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
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
