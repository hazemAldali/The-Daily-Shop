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
import java.util.Collection;
import java.util.Comparator;
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
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import net.zetetic.database.sqlcipher.SQLiteDatabase;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: PurchaseReturnRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001:\u0004PQRSB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b2\u0006\u0010\u000f\u001a\u00020\u0010J\u001c\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000bJl\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00102\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000b2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u000b2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0010J*\u0010!\u001a\u00020\u00122\n\u0010\"\u001a\u00060#j\u0002`$2\u0006\u0010%\u001a\u00020&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020(0\u000bH\u0002J,\u0010)\u001a\u00020(2\n\u0010\"\u001a\u00060#j\u0002`$2\u0006\u0010%\u001a\u00020&2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J2\u0010.\u001a\u00020/2\n\u0010\"\u001a\u00060#j\u0002`$2\u0006\u0010%\u001a\u00020&2\u0006\u00100\u001a\u0002012\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u001c0\u000bH\u0002J\\\u00103\u001a\u00020/2\n\u0010\"\u001a\u00060#j\u0002`$2\u0006\u00104\u001a\u00020\u00102\u0006\u00105\u001a\u00020\u00102\u0006\u00106\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u00107\u001a\u0002082\u0006\u0010%\u001a\u00020&2\u0006\u0010*\u001a\u00020+2\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020\u0010H\u0002JT\u0010<\u001a\u00020/2\n\u0010\"\u001a\u00060#j\u0002`$2\u0006\u00104\u001a\u00020\u00102\u0006\u00105\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u00107\u001a\u0002082\u0006\u0010=\u001a\u0002012\u0006\u0010>\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\u00102\u0006\u0010?\u001a\u00020\u001cH\u0002J\u001c\u0010@\u001a\u00020&2\n\u0010\"\u001a\u00060#j\u0002`$2\u0006\u0010A\u001a\u00020\u0010H\u0002J\"\u0010B\u001a\b\u0012\u0004\u0012\u00020+0\u000b2\n\u0010\"\u001a\u00060#j\u0002`$2\u0006\u0010%\u001a\u00020&H\u0002J\u001c\u0010C\u001a\u00020-2\n\u0010\"\u001a\u00060#j\u0002`$2\u0006\u0010A\u001a\u00020\u0010H\u0002J$\u0010D\u001a\u00020-2\n\u0010\"\u001a\u00060#j\u0002`$2\u0006\u0010%\u001a\u00020&2\u0006\u0010E\u001a\u00020\u0010H\u0002J$\u0010F\u001a\u0002012\n\u0010\"\u001a\u00060#j\u0002`$2\u0006\u0010A\u001a\u00020\u00102\u0006\u0010=\u001a\u000201H\u0002J\u001c\u0010G\u001a\u00020\u00102\n\u0010\"\u001a\u00060#j\u0002`$2\u0006\u0010A\u001a\u00020\u0010H\u0002J2\u0010H\u001a\u0018\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0018\u00010I2\n\u0010\"\u001a\u00060#j\u0002`$2\u0006\u0010A\u001a\u00020\u0010H\u0002J$\u0010J\u001a\u00020\u00162\n\u0010\"\u001a\u00060#j\u0002`$2\u0006\u0010A\u001a\u00020\u00102\u0006\u0010K\u001a\u00020\u001fH\u0002J\u001c\u0010L\u001a\u00020\u00102\n\u0010\"\u001a\u00060#j\u0002`$2\u0006\u0010>\u001a\u00020\u0010H\u0002J\u0010\u0010M\u001a\u00020\u00102\u0006\u0010N\u001a\u00020\u0010H\u0002J\b\u0010O\u001a\u00020\u0010H\u0002R\u0018\u0010\u0002\u001a\n \u0006*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006T"}, d2 = {"Lcom/hazem/dailystore/data/PurchaseReturnRepository;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "kotlin.jvm.PlatformType", "Landroid/content/Context;", "database", "Lcom/hazem/dailystore/data/AppDatabase;", "listReturnableInvoices", "", "Lcom/hazem/dailystore/data/PurchaseReturnInvoiceOption;", "listReturnableItems", "Lcom/hazem/dailystore/data/PurchaseReturnableItem;", "invoiceId", "", "previewPurchaseReturn", "Lcom/hazem/dailystore/data/PurchaseReturnPreview;", "items", "Lcom/hazem/dailystore/data/PurchaseReturnItemInput;", "postPurchaseReturn", "Lcom/hazem/dailystore/data/PurchaseReturnResult;", "actorUserId", "operationId", "returnDate", "reason", "refundAllocations", "Lcom/hazem/dailystore/data/SupplierRefundAllocationInput;", "occurredAt", "syncOrigin", "", "operatingDate", "calculate", "db", "Lnet/zetetic/database/sqlcipher/SQLiteDatabase;", "Lcom/hazem/dailystore/data/AppSqliteDatabase;", "invoice", "Lcom/hazem/dailystore/data/PurchaseReturnRepository$Invoice;", "plans", "Lcom/hazem/dailystore/data/PurchaseReturnRepository$Plan;", "planItem", "item", "Lcom/hazem/dailystore/data/PurchaseReturnRepository$Item;", "quantity", "Ljava/math/BigDecimal;", "validateRefunds", "", "target", "", "allocations", "postStockReturn", "actor", "returnId", "returnItemId", "index", "", "layer", "Lcom/hazem/dailystore/data/PurchaseReturnRepository$Layer;", "now", "postRefund", "rate", "date", "allocation", "loadInvoice", "id", "loadItems", "returnedQuantity", "sourceLayerQuantity", "itemId", "paidSyp", "productName", "findOperation", "Lkotlin/Triple;", "loadResult", "replayed", "nextNumber", "hash", "value", "timestamp", "Invoice", "Item", "Layer", "Plan", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class PurchaseReturnRepository {
    private final Context context;
    private final AppDatabase database;

    public PurchaseReturnRepository(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context.getApplicationContext();
        this.database = AppDatabase.INSTANCE.getInstance(context);
    }

    /* JADX INFO: compiled from: PurchaseReturnRepository.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\n\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\nH\u00c6\u0003J\t\u0010#\u001a\u00020\nH\u00c6\u0003J\t\u0010$\u001a\u00020\nH\u00c6\u0003J\t\u0010%\u001a\u00020\nH\u00c6\u0003Jm\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\nH\u00c6\u0001J\u0013\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010*\u001a\u00020+H\u00d6\u0001J\t\u0010,\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u000b\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\f\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0011\u0010\r\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018\u00a8\u0006-"}, d2 = {"Lcom/hazem/dailystore/data/PurchaseReturnRepository$Invoice;", "", "id", "", "number", "supplierId", "supplierName", "warehouseId", NotificationCompat.CATEGORY_STATUS, "total", "", "subtotal", "discount", "rate", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJJJ)V", "getId", "()Ljava/lang/String;", "getNumber", "getSupplierId", "getSupplierName", "getWarehouseId", "getStatus", "getTotal", "()J", "getSubtotal", "getDiscount", "getRate", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class Invoice {
        private final long discount;
        private final String id;
        private final String number;
        private final long rate;
        private final String status;
        private final long subtotal;
        private final String supplierId;
        private final String supplierName;
        private final long total;
        private final String warehouseId;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final long getRate() {
            return this.rate;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getNumber() {
            return this.number;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getSupplierId() {
            return this.supplierId;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getSupplierName() {
            return this.supplierName;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getWarehouseId() {
            return this.warehouseId;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getStatus() {
            return this.status;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final long getTotal() {
            return this.total;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final long getSubtotal() {
            return this.subtotal;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final long getDiscount() {
            return this.discount;
        }

        public final Invoice copy(String id, String number, String supplierId, String supplierName, String warehouseId, String status, long total, long subtotal, long discount, long rate) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(number, "number");
            Intrinsics.checkNotNullParameter(supplierId, "supplierId");
            Intrinsics.checkNotNullParameter(supplierName, "supplierName");
            Intrinsics.checkNotNullParameter(warehouseId, "warehouseId");
            Intrinsics.checkNotNullParameter(status, "status");
            return new Invoice(id, number, supplierId, supplierName, warehouseId, status, total, subtotal, discount, rate);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Invoice)) {
                return false;
            }
            Invoice invoice = (Invoice) other;
            return Intrinsics.areEqual(this.id, invoice.id) && Intrinsics.areEqual(this.number, invoice.number) && Intrinsics.areEqual(this.supplierId, invoice.supplierId) && Intrinsics.areEqual(this.supplierName, invoice.supplierName) && Intrinsics.areEqual(this.warehouseId, invoice.warehouseId) && Intrinsics.areEqual(this.status, invoice.status) && this.total == invoice.total && this.subtotal == invoice.subtotal && this.discount == invoice.discount && this.rate == invoice.rate;
        }

        public int hashCode() {
            return (((((((((((((((((this.id.hashCode() * 31) + this.number.hashCode()) * 31) + this.supplierId.hashCode()) * 31) + this.supplierName.hashCode()) * 31) + this.warehouseId.hashCode()) * 31) + this.status.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.total)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.subtotal)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.discount)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.rate);
        }

        public String toString() {
            return "Invoice(id=" + this.id + ", number=" + this.number + ", supplierId=" + this.supplierId + ", supplierName=" + this.supplierName + ", warehouseId=" + this.warehouseId + ", status=" + this.status + ", total=" + this.total + ", subtotal=" + this.subtotal + ", discount=" + this.discount + ", rate=" + this.rate + ")";
        }

        public Invoice(String id, String number, String supplierId, String supplierName, String warehouseId, String status, long j, long j2, long j3, long j4) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(number, "number");
            Intrinsics.checkNotNullParameter(supplierId, "supplierId");
            Intrinsics.checkNotNullParameter(supplierName, "supplierName");
            Intrinsics.checkNotNullParameter(warehouseId, "warehouseId");
            Intrinsics.checkNotNullParameter(status, "status");
            this.id = id;
            this.number = number;
            this.supplierId = supplierId;
            this.supplierName = supplierName;
            this.warehouseId = warehouseId;
            this.status = status;
            this.total = j;
            this.subtotal = j2;
            this.discount = j3;
            this.rate = j4;
        }

        public final String getId() {
            return this.id;
        }

        public final String getNumber() {
            return this.number;
        }

        public final String getSupplierId() {
            return this.supplierId;
        }

        public final String getSupplierName() {
            return this.supplierName;
        }

        public final String getStatus() {
            return this.status;
        }

        public final long getSubtotal() {
            return this.subtotal;
        }

        public final long getTotal() {
            return this.total;
        }

        public final String getWarehouseId() {
            return this.warehouseId;
        }

        public final long getDiscount() {
            return this.discount;
        }

        public final long getRate() {
            return this.rate;
        }
    }

    /* JADX INFO: compiled from: PurchaseReturnRepository.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u001b\b\u0082\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\bH\u00c6\u0003J\t\u0010 \u001a\u00020\bH\u00c6\u0003J\t\u0010!\u001a\u00020\u000bH\u00c6\u0003J\t\u0010\"\u001a\u00020\rH\u00c6\u0003JO\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u00c6\u0001J\u0013\u0010$\u001a\u00020\u000b2\b\u0010%\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010&\u001a\u00020\rH\u00d6\u0001J\t\u0010'\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\t\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b\u00a8\u0006("}, d2 = {"Lcom/hazem/dailystore/data/PurchaseReturnRepository$Item;", "", "id", "", "productId", "quantity", "Ljava/math/BigDecimal;", "unitCost", "", "lineTotal", "tracks", "", "decimals", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/math/BigDecimal;JJZI)V", "getId", "()Ljava/lang/String;", "getProductId", "getQuantity", "()Ljava/math/BigDecimal;", "getUnitCost", "()J", "getLineTotal", "getTracks", "()Z", "getDecimals", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class Item {
        private final int decimals;
        private final String id;
        private final long lineTotal;
        private final String productId;
        private final BigDecimal quantity;
        private final boolean tracks;
        private final long unitCost;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getProductId() {
            return this.productId;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final BigDecimal getQuantity() {
            return this.quantity;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final long getUnitCost() {
            return this.unitCost;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final long getLineTotal() {
            return this.lineTotal;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final boolean getTracks() {
            return this.tracks;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final int getDecimals() {
            return this.decimals;
        }

        public final Item copy(String id, String productId, BigDecimal quantity, long unitCost, long lineTotal, boolean tracks, int decimals) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(productId, "productId");
            Intrinsics.checkNotNullParameter(quantity, "quantity");
            return new Item(id, productId, quantity, unitCost, lineTotal, tracks, decimals);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Item)) {
                return false;
            }
            Item item = (Item) other;
            return Intrinsics.areEqual(this.id, item.id) && Intrinsics.areEqual(this.productId, item.productId) && Intrinsics.areEqual(this.quantity, item.quantity) && this.unitCost == item.unitCost && this.lineTotal == item.lineTotal && this.tracks == item.tracks && this.decimals == item.decimals;
        }

        public int hashCode() {
            return (((((((((((this.id.hashCode() * 31) + this.productId.hashCode()) * 31) + this.quantity.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.unitCost)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.lineTotal)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.tracks)) * 31) + this.decimals;
        }

        public String toString() {
            return "Item(id=" + this.id + ", productId=" + this.productId + ", quantity=" + this.quantity + ", unitCost=" + this.unitCost + ", lineTotal=" + this.lineTotal + ", tracks=" + this.tracks + ", decimals=" + this.decimals + ")";
        }

        public Item(String id, String productId, BigDecimal quantity, long j, long j2, boolean z, int i) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(productId, "productId");
            Intrinsics.checkNotNullParameter(quantity, "quantity");
            this.id = id;
            this.productId = productId;
            this.quantity = quantity;
            this.unitCost = j;
            this.lineTotal = j2;
            this.tracks = z;
            this.decimals = i;
        }

        public final String getId() {
            return this.id;
        }

        public final String getProductId() {
            return this.productId;
        }

        public final BigDecimal getQuantity() {
            return this.quantity;
        }

        public final long getUnitCost() {
            return this.unitCost;
        }

        public final int getDecimals() {
            return this.decimals;
        }

        public final long getLineTotal() {
            return this.lineTotal;
        }

        public final boolean getTracks() {
            return this.tracks;
        }
    }

    /* JADX INFO: compiled from: PurchaseReturnRepository.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0005H\u00c6\u0003J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000fJ.\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001c"}, d2 = {"Lcom/hazem/dailystore/data/PurchaseReturnRepository$Layer;", "", "id", "", "quantity", "Ljava/math/BigDecimal;", "unitCost", "", "<init>", "(Ljava/lang/String;Ljava/math/BigDecimal;Ljava/lang/Long;)V", "getId", "()Ljava/lang/String;", "getQuantity", "()Ljava/math/BigDecimal;", "getUnitCost", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/math/BigDecimal;Ljava/lang/Long;)Lcom/hazem/dailystore/data/PurchaseReturnRepository$Layer;", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class Layer {
        private final String id;
        private final BigDecimal quantity;
        private final Long unitCost;

        public static /* synthetic */ Layer copy$default(Layer layer, String str, BigDecimal bigDecimal, Long l, int i, Object obj) {
            if ((i & 1) != 0) {
                str = layer.id;
            }
            if ((i & 2) != 0) {
                bigDecimal = layer.quantity;
            }
            if ((i & 4) != 0) {
                l = layer.unitCost;
            }
            return layer.copy(str, bigDecimal, l);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final BigDecimal getQuantity() {
            return this.quantity;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Long getUnitCost() {
            return this.unitCost;
        }

        public final Layer copy(String id, BigDecimal quantity, Long unitCost) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(quantity, "quantity");
            return new Layer(id, quantity, unitCost);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Layer)) {
                return false;
            }
            Layer layer = (Layer) other;
            return Intrinsics.areEqual(this.id, layer.id) && Intrinsics.areEqual(this.quantity, layer.quantity) && Intrinsics.areEqual(this.unitCost, layer.unitCost);
        }

        public int hashCode() {
            int iHashCode = ((this.id.hashCode() * 31) + this.quantity.hashCode()) * 31;
            Long l = this.unitCost;
            return iHashCode + (l == null ? 0 : l.hashCode());
        }

        public String toString() {
            return "Layer(id=" + this.id + ", quantity=" + this.quantity + ", unitCost=" + this.unitCost + ")";
        }

        public Layer(String id, BigDecimal quantity, Long l) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(quantity, "quantity");
            this.id = id;
            this.quantity = quantity;
            this.unitCost = l;
        }

        public final String getId() {
            return this.id;
        }

        public final BigDecimal getQuantity() {
            return this.quantity;
        }

        public final Long getUnitCost() {
            return this.unitCost;
        }
    }

    /* JADX INFO: compiled from: PurchaseReturnRepository.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0007H\u00c6\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00c6\u0003J7\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00c6\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001d\u001a\u00020\u001eH\u00d6\u0001J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006!"}, d2 = {"Lcom/hazem/dailystore/data/PurchaseReturnRepository$Plan;", "", "item", "Lcom/hazem/dailystore/data/PurchaseReturnRepository$Item;", "quantity", "Ljava/math/BigDecimal;", "value", "", "layers", "", "Lcom/hazem/dailystore/data/PurchaseReturnRepository$Layer;", "<init>", "(Lcom/hazem/dailystore/data/PurchaseReturnRepository$Item;Ljava/math/BigDecimal;JLjava/util/List;)V", "getItem", "()Lcom/hazem/dailystore/data/PurchaseReturnRepository$Item;", "getQuantity", "()Ljava/math/BigDecimal;", "getValue", "()J", "getLayers", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class Plan {
        private final Item item;
        private final List<Layer> layers;
        private final BigDecimal quantity;
        private final long value;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Plan copy$default(Plan plan, Item item, BigDecimal bigDecimal, long j, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                item = plan.item;
            }
            if ((i & 2) != 0) {
                bigDecimal = plan.quantity;
            }
            BigDecimal bigDecimal2 = bigDecimal;
            if ((i & 4) != 0) {
                j = plan.value;
            }
            long j2 = j;
            if ((i & 8) != 0) {
                list = plan.layers;
            }
            return plan.copy(item, bigDecimal2, j2, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Item getItem() {
            return this.item;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final BigDecimal getQuantity() {
            return this.quantity;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final long getValue() {
            return this.value;
        }

        public final List<Layer> component4() {
            return this.layers;
        }

        public final Plan copy(Item item, BigDecimal quantity, long value, List<Layer> layers) {
            Intrinsics.checkNotNullParameter(item, "item");
            Intrinsics.checkNotNullParameter(quantity, "quantity");
            Intrinsics.checkNotNullParameter(layers, "layers");
            return new Plan(item, quantity, value, layers);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Plan)) {
                return false;
            }
            Plan plan = (Plan) other;
            return Intrinsics.areEqual(this.item, plan.item) && Intrinsics.areEqual(this.quantity, plan.quantity) && this.value == plan.value && Intrinsics.areEqual(this.layers, plan.layers);
        }

        public int hashCode() {
            return (((((this.item.hashCode() * 31) + this.quantity.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.value)) * 31) + this.layers.hashCode();
        }

        public String toString() {
            return "Plan(item=" + this.item + ", quantity=" + this.quantity + ", value=" + this.value + ", layers=" + this.layers + ")";
        }

        public Plan(Item item, BigDecimal quantity, long j, List<Layer> layers) {
            Intrinsics.checkNotNullParameter(item, "item");
            Intrinsics.checkNotNullParameter(quantity, "quantity");
            Intrinsics.checkNotNullParameter(layers, "layers");
            this.item = item;
            this.quantity = quantity;
            this.value = j;
            this.layers = layers;
        }

        public final Item getItem() {
            return this.item;
        }

        public final List<Layer> getLayers() {
            return this.layers;
        }

        public final BigDecimal getQuantity() {
            return this.quantity;
        }

        public final long getValue() {
            return this.value;
        }
    }

    public final List<PurchaseReturnInvoiceOption> listReturnableInvoices() throws IOException {
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery("SELECT i.id,i.invoice_number,i.invoice_date,s.name,i.total_usd_cents\n               FROM purchase_invoices i JOIN suppliers s ON s.id=i.supplier_id\n               WHERE i.status IN ('POSTED','PARTIALLY_PAID','PAID')\n               ORDER BY i.invoice_date DESC,i.created_at DESC", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            while (cursor.moveToNext()) {
                String string = cursor.getString(0);
                Intrinsics.checkNotNull(string);
                List<PurchaseReturnableItem> listListReturnableItems = listReturnableItems(string);
                if (!(listListReturnableItems instanceof Collection) || !listListReturnableItems.isEmpty()) {
                    Iterator<T> it = listListReturnableItems.iterator();
                    while (it.hasNext()) {
                        if (((PurchaseReturnableItem) it.next()).getAvailableQuantity().signum() > 0) {
                            String string2 = cursor.getString(1);
                            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                            String string3 = cursor.getString(2);
                            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                            String string4 = cursor.getString(3);
                            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                            arrayList.add(new PurchaseReturnInvoiceOption(string, string2, string3, string4, cursor.getLong(4)));
                            break;
                        }
                    }
                }
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
    public final List<PurchaseReturnableItem> listReturnableItems(String invoiceId) throws IOException {
        Intrinsics.checkNotNullParameter(invoiceId, "invoiceId");
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        Invoice invoiceLoadInvoice = loadInvoice(readableDatabase, invoiceId);
        List<Item> listLoadItems = loadItems(readableDatabase, invoiceLoadInvoice);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listLoadItems, 10));
        for (Item item : listLoadItems) {
            BigDecimal bigDecimalReturnedQuantity = returnedQuantity(readableDatabase, item.getId());
            BigDecimal bigDecimalSourceLayerQuantity = item.getTracks() ? sourceLayerQuantity(readableDatabase, invoiceLoadInvoice, item.getId()) : item.getQuantity();
            String id = item.getId();
            String strProductName = productName(readableDatabase, item.getProductId());
            BigDecimal quantity = item.getQuantity();
            BigDecimal bigDecimalMin = bigDecimalSourceLayerQuantity.min(item.getQuantity().subtract(bigDecimalReturnedQuantity).max(BigDecimal.ZERO));
            Intrinsics.checkNotNullExpressionValue(bigDecimalMin, "min(...)");
            arrayList.add(new PurchaseReturnableItem(id, strProductName, quantity, bigDecimalReturnedQuantity, bigDecimalMin, item.getUnitCost(), item.getTracks()));
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final PurchaseReturnPreview previewPurchaseReturn(String invoiceId, List<PurchaseReturnItemInput> items) throws IOException {
        Object next;
        Intrinsics.checkNotNullParameter(invoiceId, "invoiceId");
        Intrinsics.checkNotNullParameter(items, "items");
        if (items.isEmpty()) {
            throw new IllegalArgumentException("\u062d\u062f\u062f \u0627\u0644\u0623\u0635\u0646\u0627\u0641 \u0627\u0644\u0645\u0631\u062a\u062c\u0639\u0629".toString());
        }
        List<PurchaseReturnItemInput> list = items;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((PurchaseReturnItemInput) it.next()).getOriginalPurchaseItemId());
        }
        if (CollectionsKt.distinct(arrayList).size() != items.size()) {
            throw new IllegalArgumentException("\u0644\u0627 \u062a\u0643\u0631\u0631 \u0628\u0646\u062f \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629".toString());
        }
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        Invoice invoiceLoadInvoice = loadInvoice(readableDatabase, invoiceId);
        if (!SetsKt.setOf((Object[]) new String[]{"POSTED", "PARTIALLY_PAID", "PAID"}).contains(invoiceLoadInvoice.getStatus())) {
            throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0625\u0631\u062c\u0627\u0639 \u0641\u0627\u062a\u0648\u0631\u0629 \u063a\u064a\u0631 \u0645\u0639\u062a\u0645\u062f\u0629".toString());
        }
        if (invoiceLoadInvoice.getRate() <= 0) {
            throw new IllegalArgumentException("\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0634\u0631\u0627\u0621 \u0628\u0644\u0627 \u0633\u0639\u0631 \u0635\u0631\u0641 \u0645\u062d\u0641\u0648\u0638".toString());
        }
        List<Item> listLoadItems = loadItems(readableDatabase, invoiceLoadInvoice);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (PurchaseReturnItemInput purchaseReturnItemInput : list) {
            Iterator<T> it2 = listLoadItems.iterator();
            do {
                if (!it2.hasNext()) {
                    next = null;
                    break;
                }
                next = it2.next();
            } while (!Intrinsics.areEqual(((Item) next).getId(), purchaseReturnItemInput.getOriginalPurchaseItemId()));
            Item item = (Item) next;
            if (item == null) {
                throw new IllegalArgumentException("\u0627\u0644\u0635\u0646\u0641 \u0644\u0627 \u064a\u0646\u062a\u0645\u064a \u0625\u0644\u0649 \u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0634\u0631\u0627\u0621");
            }
            arrayList2.add(planItem(readableDatabase, invoiceLoadInvoice, item, purchaseReturnItemInput.getQuantity()));
        }
        return calculate(readableDatabase, invoiceLoadInvoice, arrayList2);
    }

    /* JADX WARN: Code duplicated, block: B:107:0x02ce  */
    /* JADX WARN: Code duplicated, block: B:94:0x02a5  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v81, types: [com.hazem.dailystore.data.StoreTime] */
    /* JADX WARN: Type inference failed for: r10v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v23 */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v30 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v5, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Type inference failed for: r11v9 */
    /* JADX WARN: Type inference failed for: r1v32, types: [com.hazem.dailystore.data.SyncProtocolRepository] */
    /* JADX WARN: Type inference failed for: r1v62, types: [com.hazem.dailystore.data.SyncProtocolRepository] */
    /* JADX WARN: Type inference failed for: r2v25, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r3v5, types: [com.hazem.dailystore.data.PermissionPolicy] */
    /* JADX WARN: Type inference failed for: r41v0, types: [com.hazem.dailystore.data.PurchaseReturnRepository] */
    /* JADX WARN: Type inference failed for: r49v1, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r49v2 */
    /* JADX WARN: Type inference failed for: r49v3 */
    /* JADX WARN: Type inference failed for: r49v4 */
    /* JADX WARN: Type inference failed for: r49v5 */
    /* JADX WARN: Type inference failed for: r4v99, types: [com.hazem.dailystore.data.PermissionPolicy] */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v11, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v20 */
    /* JADX WARN: Type inference failed for: r9v24 */
    /* JADX WARN: Type inference failed for: r9v25 */
    /* JADX WARN: Type inference failed for: r9v26 */
    /* JADX WARN: Type inference failed for: r9v9, types: [java.lang.Object, net.zetetic.database.sqlcipher.SQLiteDatabase] */
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
    public final PurchaseReturnResult postPurchaseReturn(String actorUserId, String invoiceId, String operationId, String returnDate, String reason, List<PurchaseReturnItemInput> items, List<SupplierRefundAllocationInput> refundAllocations, String occurredAt, boolean syncOrigin, String operatingDate) throws Throwable {
        Throwable th;
        ?? r10;
        Throwable th2;
        String string;
        ?? r49;
        String str;
        Iterator it;
        List<Item> list;
        Object next;
        String strTimestamp;
        String strOperatingDate;
        String strOptString;
        String strOptString2;
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(invoiceId, "invoiceId");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        Intrinsics.checkNotNullParameter(returnDate, "returnDate");
        Intrinsics.checkNotNullParameter(reason, "reason");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(refundAllocations, "refundAllocations");
        if (StringsKt.isBlank(actorUserId) || StringsKt.isBlank(invoiceId) || StringsKt.isBlank(operationId)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        ?? writableDatabase = "\\d{4}-\\d{2}-\\d{2}";
        if (!new Regex("\\d{4}-\\d{2}-\\d{2}").matches(returnDate)) {
            throw new IllegalArgumentException("\u062a\u0627\u0631\u064a\u062e \u0627\u0644\u0645\u0631\u062a\u062c\u0639 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
        }
        String str2 = reason;
        if (StringsKt.isBlank(str2) || items.isEmpty()) {
            throw new IllegalArgumentException("\u0633\u0628\u0628 \u0627\u0644\u0645\u0631\u062a\u062c\u0639 \u0648\u0627\u0644\u0623\u0635\u0646\u0627\u0641 \u0645\u0637\u0644\u0648\u0628\u0627\u0646".toString());
        }
        List<PurchaseReturnItemInput> list2 = items;
        if (!(list2 instanceof Collection) || !list2.isEmpty()) {
            for (PurchaseReturnItemInput purchaseReturnItemInput : list2) {
                if (StringsKt.isBlank(purchaseReturnItemInput.getOriginalPurchaseItemId()) || purchaseReturnItemInput.getQuantity().signum() <= 0) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
            }
        }
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            arrayList.add(((PurchaseReturnItemInput) it2.next()).getOriginalPurchaseItemId());
        }
        if (CollectionsKt.distinct(arrayList).size() != items.size()) {
            throw new IllegalArgumentException("\u0627\u062c\u0645\u0639 \u0643\u0644 \u0635\u0646\u0641 \u0641\u064a \u0633\u0637\u0631 \u0648\u0627\u062d\u062f".toString());
        }
        List<SupplierRefundAllocationInput> list3 = refundAllocations;
        if (!(list3 instanceof Collection) || !list3.isEmpty()) {
            for (SupplierRefundAllocationInput supplierRefundAllocationInput : list3) {
                if (StringsKt.isBlank(supplierRefundAllocationInput.getCashAccountId()) || supplierRefundAllocationInput.getAmountMinor() <= 0) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
        Iterator it3 = list3.iterator();
        while (it3.hasNext()) {
            arrayList2.add(((SupplierRefundAllocationInput) it3.next()).getCashAccountId());
        }
        if (CollectionsKt.distinct(arrayList2).size() != refundAllocations.size()) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        StringBuilder sb = new StringBuilder();
        sb.append(invoiceId);
        char c = '|';
        sb.append('|');
        sb.append(returnDate);
        sb.append('|');
        sb.append(StringsKt.trim((CharSequence) str2).toString());
        for (PurchaseReturnItemInput purchaseReturnItemInput2 : CollectionsKt.sortedWith(list2, new Comparator() { // from class: com.hazem.dailystore.data.PurchaseReturnRepository$postPurchaseReturn$lambda$21$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(((PurchaseReturnItemInput) t).getOriginalPurchaseItemId(), ((PurchaseReturnItemInput) t2).getOriginalPurchaseItemId());
            }
        })) {
            sb.append(c);
            sb.append(purchaseReturnItemInput2.getOriginalPurchaseItemId());
            sb.append(':');
            sb.append(MainActivity$$ExternalSyntheticBackport0.m(purchaseReturnItemInput2.getQuantity()).toPlainString());
            c = '|';
        }
        for (SupplierRefundAllocationInput supplierRefundAllocationInput2 : CollectionsKt.sortedWith(list3, new Comparator() { // from class: com.hazem.dailystore.data.PurchaseReturnRepository$postPurchaseReturn$lambda$21$$inlined$sortedBy$2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(((SupplierRefundAllocationInput) t).getCashAccountId(), ((SupplierRefundAllocationInput) t2).getCashAccountId());
            }
        })) {
            sb.append('|');
            sb.append(supplierRefundAllocationInput2.getCashAccountId());
            sb.append(':');
            sb.append(supplierRefundAllocationInput2.getAmountMinor());
        }
        String strHash = hash(sb.toString());
        writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        writableDatabase.beginTransaction();
        try {
            try {
                Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", null);
                try {
                    try {
                        Cursor cursor = cursorRawQuery;
                        if (cursor.moveToFirst()) {
                            try {
                                string = cursor.getString(0);
                            } catch (Throwable th3) {
                                th2 = th3;
                                try {
                                    throw th2;
                                } catch (Throwable th4) {
                                    CloseableKt.closeFinally(cursorRawQuery, th2);
                                    throw th4;
                                }
                            }
                        } else {
                            string = null;
                        }
                        CloseableKt.closeFinally(cursorRawQuery, null);
                        String str3 = "return_date";
                        String str4 = "original_invoice_id";
                        try {
                            if (!Intrinsics.areEqual(string, "SECONDARY")) {
                                String str5 = "toString(...)";
                                PermissionPolicy.INSTANCE.require(writableDatabase, actorUserId, PermissionPolicy.RETURNS_PROCESS);
                                Triple<String, String, String> tripleFindOperation = findOperation(writableDatabase, operationId);
                                if (tripleFindOperation != null) {
                                    if (!Intrinsics.areEqual(tripleFindOperation.getFirst(), "POST_PURCHASE_RETURN") || !Intrinsics.areEqual(tripleFindOperation.getSecond(), strHash)) {
                                        throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0645\u0633\u062a\u062e\u062f\u0645 \u0628\u0628\u064a\u0627\u0646\u0627\u062a \u0645\u062e\u062a\u0644\u0641\u0629".toString());
                                    }
                                    String third = tripleFindOperation.getThird();
                                    if (third == null) {
                                        throw new IllegalStateException("\u0645\u0631\u062c\u0639 \u0627\u0644\u0645\u0631\u062a\u062c\u0639 \u0645\u0641\u0642\u0648\u062f".toString());
                                    }
                                    PurchaseReturnResult purchaseReturnResultLoadResult = loadResult(writableDatabase, third, true);
                                    writableDatabase.setTransactionSuccessful();
                                    writableDatabase.endTransaction();
                                    Result.Companion companion = Result.INSTANCE;
                                    SyncRelaySession.INSTANCE.flushPending();
                                    Result.m381constructorimpl(Unit.INSTANCE);
                                    return purchaseReturnResultLoadResult;
                                }
                                Invoice invoiceLoadInvoice = loadInvoice(writableDatabase, invoiceId);
                                if (!SetsKt.setOf((Object[]) new String[]{"POSTED", "PARTIALLY_PAID", "PAID"}).contains(invoiceLoadInvoice.getStatus())) {
                                    throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0625\u0631\u062c\u0627\u0639 \u0641\u0627\u062a\u0648\u0631\u0629 \u063a\u064a\u0631 \u0645\u0639\u062a\u0645\u062f\u0629 \u0623\u0648 \u0645\u0644\u063a\u0627\u0629".toString());
                                }
                                if (invoiceLoadInvoice.getRate() <= 0) {
                                    throw new IllegalArgumentException("\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0634\u0631\u0627\u0621 \u0628\u0644\u0627 \u0633\u0639\u0631 \u0635\u0631\u0641 \u0645\u062d\u0641\u0648\u0638".toString());
                                }
                                List<Item> listLoadItems = loadItems(writableDatabase, invoiceLoadInvoice);
                                List<PurchaseReturnItemInput> list4 = items;
                                ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list4, 10));
                                Iterator it4 = list4.iterator();
                                while (it4.hasNext()) {
                                    PurchaseReturnItemInput purchaseReturnItemInput3 = (PurchaseReturnItemInput) it4.next();
                                    Iterator it5 = listLoadItems.iterator();
                                    while (true) {
                                        if (!it5.hasNext()) {
                                            it = it4;
                                            list = listLoadItems;
                                            next = null;
                                            break;
                                        }
                                        next = it5.next();
                                        it = it4;
                                        list = listLoadItems;
                                        if (Intrinsics.areEqual(((Item) next).getId(), purchaseReturnItemInput3.getOriginalPurchaseItemId())) {
                                            break;
                                        }
                                        it4 = it;
                                        listLoadItems = list;
                                    }
                                    Item item = (Item) next;
                                    if (item == null) {
                                        throw new IllegalArgumentException("\u0627\u0644\u0635\u0646\u0641 \u0644\u0627 \u064a\u0646\u062a\u0645\u064a \u0625\u0644\u0649 \u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0634\u0631\u0627\u0621");
                                    }
                                    arrayList3.add(planItem(writableDatabase, invoiceLoadInvoice, item, purchaseReturnItemInput3.getQuantity()));
                                    it4 = it;
                                    listLoadItems = list;
                                }
                                ArrayList<Plan> arrayList4 = arrayList3;
                                PurchaseReturnPreview purchaseReturnPreviewCalculate = calculate(writableDatabase, invoiceLoadInvoice, arrayList4);
                                String str6 = actorUserId;
                                String str7 = "reason";
                                validateRefunds(writableDatabase, invoiceLoadInvoice, purchaseReturnPreviewCalculate.getSupplierCashRefundSypMinor(), refundAllocations);
                                String strTimestamp2 = occurredAt == null ? timestamp() : occurredAt;
                                String string2 = UUID.randomUUID().toString();
                                Intrinsics.checkNotNullExpressionValue(string2, str5);
                                String strNextNumber = nextNumber(writableDatabase, returnDate);
                                ContentValues contentValues = new ContentValues();
                                String str8 = "id";
                                contentValues.put(str8, string2);
                                contentValues.put("return_number", strNextNumber);
                                String str9 = strNextNumber;
                                String str10 = "return_number";
                                contentValues.put("original_invoice_type", "PURCHASE");
                                contentValues.put("original_invoice_id", invoiceLoadInvoice.getId());
                                contentValues.put("party_type", "SUPPLIER");
                                contentValues.put("party_id", invoiceLoadInvoice.getSupplierId());
                                contentValues.put("return_date", returnDate);
                                contentValues.put("total_usd_cents", Long.valueOf(purchaseReturnPreviewCalculate.getTotalUsdCents()));
                                contentValues.put(str7, StringsKt.trim((CharSequence) reason).toString());
                                contentValues.put(NotificationCompat.CATEGORY_STATUS, "POSTED");
                                contentValues.put("created_by", str6);
                                String str11 = "created_at";
                                contentValues.put(str11, strTimestamp2);
                                contentValues.put("operation_id", operationId);
                                contentValues.put("posted_at", strTimestamp2);
                                String str12 = strTimestamp2;
                                contentValues.put("cash_refund_usd_cents", (Long) 0L);
                                contentValues.put("credit_reduction_usd_cents", (Long) 0L);
                                contentValues.putNull("returned_cogs_usd_cents");
                                contentValues.put("exchange_rate_syp_per_usd_milli", Long.valueOf(invoiceLoadInvoice.getRate()));
                                contentValues.put("supplier_credit_reduction_syp_minor", Long.valueOf(purchaseReturnPreviewCalculate.getSupplierCreditReductionSypMinor()));
                                contentValues.put("supplier_cash_refund_syp_minor", Long.valueOf(purchaseReturnPreviewCalculate.getSupplierCashRefundSypMinor()));
                                Unit unit = Unit.INSTANCE;
                                writableDatabase.insertOrThrow("returns", null, contentValues);
                                writableDatabase = writableDatabase;
                                for (Plan plan : arrayList4) {
                                    try {
                                        String string3 = UUID.randomUUID().toString();
                                        Intrinsics.checkNotNullExpressionValue(string3, str5);
                                        ContentValues contentValues2 = new ContentValues();
                                        contentValues2.put(str8, string3);
                                        contentValues2.put("return_id", string2);
                                        String str13 = str8;
                                        contentValues2.put("product_id", plan.getItem().getProductId());
                                        contentValues2.put("quantity", Double.valueOf(plan.getQuantity().doubleValue()));
                                        contentValues2.put("unit_price_usd_cents", Long.valueOf(plan.getItem().getUnitCost()));
                                        contentValues2.put("line_total_usd_cents", Long.valueOf(plan.getValue()));
                                        contentValues2.put("original_purchase_item_id", plan.getItem().getId());
                                        contentValues2.putNull("original_sales_item_id");
                                        contentValues2.putNull("returned_cogs_usd_cents");
                                        Unit unit2 = Unit.INSTANCE;
                                        Object obj = null;
                                        writableDatabase.insertOrThrow("return_items", null, contentValues2);
                                        int i = 0;
                                        writableDatabase = writableDatabase;
                                        for (Object obj2 : plan.getLayers()) {
                                            int i2 = i + 1;
                                            if (i < 0) {
                                                CollectionsKt.throwIndexOverflow();
                                            }
                                            String str14 = str5;
                                            String str15 = str9;
                                            String str16 = string2;
                                            String str17 = str12;
                                            String str18 = string3;
                                            String str19 = str7;
                                            String str20 = str3;
                                            String str21 = str13;
                                            String str22 = str4;
                                            String str23 = str11;
                                            String str24 = str6;
                                            String str25 = str10;
                                            r49 = writableDatabase;
                                            try {
                                                postStockReturn(writableDatabase, actorUserId, string2, string3, operationId, i, invoiceLoadInvoice, plan.getItem(), (Layer) obj2, str17);
                                                string2 = str16;
                                                writableDatabase = r49;
                                                str6 = str24;
                                                str4 = str22;
                                                str11 = str23;
                                                string3 = str18;
                                                str3 = str20;
                                                i = i2;
                                                str5 = str14;
                                                str7 = str19;
                                                str10 = str25;
                                                obj = null;
                                                str12 = str17;
                                                str13 = str21;
                                                str9 = str15;
                                            } catch (Throwable th5) {
                                                th = th5;
                                                writableDatabase = r49;
                                                th = th;
                                                r10 = writableDatabase;
                                                r10.endTransaction();
                                                Result.Companion companion2 = Result.INSTANCE;
                                                SyncRelaySession.INSTANCE.flushPending();
                                                Result.m381constructorimpl(Unit.INSTANCE);
                                                throw th;
                                            }
                                        }
                                        str8 = str13;
                                        writableDatabase = writableDatabase;
                                    } catch (Throwable th6) {
                                        th = th6;
                                        r49 = writableDatabase;
                                        writableDatabase = r49;
                                        th = th;
                                        r10 = writableDatabase;
                                        r10.endTransaction();
                                        Result.Companion companion3 = Result.INSTANCE;
                                        SyncRelaySession.INSTANCE.flushPending();
                                        Result.m381constructorimpl(Unit.INSTANCE);
                                        throw th;
                                    }
                                }
                                String str26 = str9;
                                String str27 = str5;
                                String str28 = str8;
                                String str29 = string2;
                                String str30 = str3;
                                r49 = writableDatabase;
                                String str31 = str6;
                                String str32 = str11;
                                String str33 = str7;
                                String str34 = str12;
                                String str35 = str10;
                                String str36 = str4;
                                int i3 = 0;
                                for (Object obj3 : refundAllocations) {
                                    int i4 = i3 + 1;
                                    if (i3 < 0) {
                                        CollectionsKt.throwIndexOverflow();
                                    }
                                    postRefund(r49, actorUserId, str29, operationId, i3, invoiceLoadInvoice.getRate(), operatingDate == null ? returnDate : operatingDate, str34, (SupplierRefundAllocationInput) obj3);
                                    i3 = i4;
                                }
                                ContentValues contentValues3 = new ContentValues();
                                contentValues3.put(str28, UUID.randomUUID().toString());
                                contentValues3.put("user_id", str31);
                                contentValues3.put("action", "PURCHASE_RETURN_POSTED");
                                contentValues3.put("entity_type", "PURCHASE_RETURN");
                                contentValues3.put("entity_id", str29);
                                contentValues3.put("after_json", "{\"original_invoice_id\":\"" + invoiceLoadInvoice.getId() + "\",\"total_usd_cents\":" + purchaseReturnPreviewCalculate.getTotalUsdCents() + ",\"supplier_credit_reduction_syp_minor\":" + purchaseReturnPreviewCalculate.getSupplierCreditReductionSypMinor() + ",\"supplier_cash_refund_syp_minor\":" + purchaseReturnPreviewCalculate.getSupplierCashRefundSypMinor() + "}");
                                contentValues3.put(str32, str34);
                                Unit unit3 = Unit.INSTANCE;
                                r49.insertOrThrow("audit_log", null, contentValues3);
                                ContentValues contentValues4 = new ContentValues();
                                contentValues4.put("operation_id", operationId);
                                contentValues4.put("operation_type", "POST_PURCHASE_RETURN");
                                contentValues4.put("payload_hash", strHash);
                                contentValues4.put(NotificationCompat.CATEGORY_STATUS, "COMPLETED");
                                contentValues4.put("result_reference", str29);
                                contentValues4.put(str32, str34);
                                contentValues4.put("completed_at", str34);
                                Unit unit4 = Unit.INSTANCE;
                                r49.insertOrThrow("operation_registry", null, contentValues4);
                                if (syncOrigin) {
                                    str = str26;
                                } else {
                                    Context context = this.context;
                                    Intrinsics.checkNotNullExpressionValue(context, "context");
                                    ?? syncProtocolRepository = new SyncProtocolRepository(context, null, 2, false ? 1 : 0);
                                    JSONObject jSONObjectPut = new JSONObject().put(str36, invoiceLoadInvoice.getId()).put("return_id", str29);
                                    str = str26;
                                    String string4 = jSONObjectPut.put(str35, str).put(str30, returnDate).put(str33, StringsKt.trim((CharSequence) reason).toString()).put("total_usd_cents", purchaseReturnPreviewCalculate.getTotalUsdCents()).put("supplier_credit_reduction_syp_minor", purchaseReturnPreviewCalculate.getSupplierCreditReductionSypMinor()).put("supplier_cash_refund_syp_minor", purchaseReturnPreviewCalculate.getSupplierCashRefundSypMinor()).toString();
                                    Intrinsics.checkNotNullExpressionValue(string4, str27);
                                    syncProtocolRepository.enqueueLocalOperation(r49, actorUserId, operationId, "POST_PURCHASE_RETURN", "PURCHASE_RETURN", str29, "CREATE", string4);
                                }
                                r49.setTransactionSuccessful();
                                PurchaseReturnResult purchaseReturnResult = new PurchaseReturnResult(str29, str, purchaseReturnPreviewCalculate.getTotalUsdCents(), purchaseReturnPreviewCalculate.getSupplierCreditReductionSypMinor(), purchaseReturnPreviewCalculate.getSupplierCashRefundSypMinor(), false);
                                r49.endTransaction();
                                try {
                                    Result.Companion companion4 = Result.INSTANCE;
                                    SyncRelaySession.INSTANCE.flushPending();
                                    Result.m381constructorimpl(Unit.INSTANCE);
                                } catch (Throwable th7) {
                                    Result.Companion companion5 = Result.INSTANCE;
                                    Result.m381constructorimpl(ResultKt.createFailure(th7));
                                }
                                return purchaseReturnResult;
                            }
                            PermissionPolicy.INSTANCE.requireAssigned(writableDatabase, actorUserId, PermissionPolicy.RETURNS_PROCESS);
                            PurchaseReturnPreview purchaseReturnPreviewPreviewPurchaseReturn = previewPurchaseReturn(invoiceId, items);
                            Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT payload_json FROM sync_operation_envelopes WHERE operation_id=?", new String[]{operationId});
                            try {
                                Cursor cursor2 = cursorRawQuery2;
                                JSONObject jSONObject = cursor2.moveToFirst() ? new JSONObject(cursor2.getString(0)) : null;
                                CloseableKt.closeFinally(cursorRawQuery2, null);
                                if (occurredAt == null) {
                                    if (jSONObject == null || (strOptString2 = jSONObject.optString("occurred_at")) == null) {
                                        strTimestamp = null;
                                    } else if (new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(strOptString2)) {
                                        strTimestamp = strOptString2;
                                    } else {
                                        strTimestamp = null;
                                    }
                                    if (strTimestamp == null) {
                                        strTimestamp = timestamp();
                                    }
                                } else {
                                    strTimestamp = occurredAt;
                                }
                                if (operatingDate == null) {
                                    if (jSONObject == null || (strOptString = jSONObject.optString("operating_date")) == null) {
                                        strOperatingDate = null;
                                    } else if (new Regex("\\d{4}-\\d{2}-\\d{2}").matches(strOptString)) {
                                        strOperatingDate = strOptString;
                                    } else {
                                        strOperatingDate = null;
                                    }
                                    if (strOperatingDate == null) {
                                        strOperatingDate = StoreTime.INSTANCE.operatingDate(writableDatabase, strTimestamp);
                                    }
                                } else {
                                    strOperatingDate = operatingDate;
                                }
                                try {
                                    JSONObject jSONObjectPut2 = new JSONObject().put("original_invoice_id", invoiceId).put("return_date", returnDate).put("reason", StringsKt.trim((CharSequence) reason).toString()).put("occurred_at", strTimestamp).put("operating_date", strOperatingDate);
                                    JSONArray jSONArray = new JSONArray();
                                    for (PurchaseReturnItemInput purchaseReturnItemInput4 : items) {
                                        jSONArray.put(new JSONObject().put("original_purchase_item_id", purchaseReturnItemInput4.getOriginalPurchaseItemId()).put("quantity", MainActivity$$ExternalSyntheticBackport0.m(purchaseReturnItemInput4.getQuantity()).toPlainString()));
                                    }
                                    Unit unit5 = Unit.INSTANCE;
                                    JSONObject jSONObjectPut3 = jSONObjectPut2.put("items", jSONArray);
                                    JSONArray jSONArray2 = new JSONArray();
                                    for (SupplierRefundAllocationInput supplierRefundAllocationInput3 : refundAllocations) {
                                        JSONObject jSONObjectPut4 = new JSONObject().put("cash_account_id", supplierRefundAllocationInput3.getCashAccountId()).put("amount_minor", supplierRefundAllocationInput3.getAmountMinor());
                                        Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT account_type FROM cash_accounts WHERE id=?", new String[]{supplierRefundAllocationInput3.getCashAccountId()});
                                        try {
                                            Cursor cursor3 = cursorRawQuery3;
                                            if (!cursor3.moveToFirst()) {
                                                throw new IllegalArgumentException("Failed requirement.".toString());
                                            }
                                            String string5 = cursor3.getString(0);
                                            CloseableKt.closeFinally(cursorRawQuery3, null);
                                            JSONObject jSONObjectPut5 = jSONObjectPut4.put("cash_account_type", string5);
                                            Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT currency_code FROM cash_accounts WHERE id=?", new String[]{supplierRefundAllocationInput3.getCashAccountId()});
                                            try {
                                                Cursor cursor4 = cursorRawQuery4;
                                                if (!cursor4.moveToFirst()) {
                                                    throw new IllegalArgumentException("Failed requirement.".toString());
                                                }
                                                String string6 = cursor4.getString(0);
                                                CloseableKt.closeFinally(cursorRawQuery4, null);
                                                jSONArray2.put(jSONObjectPut5.put("cash_account_currency_code", string6));
                                            } catch (Throwable th8) {
                                                try {
                                                    throw th8;
                                                } catch (Throwable th9) {
                                                    CloseableKt.closeFinally(cursorRawQuery4, th8);
                                                    throw th9;
                                                }
                                            }
                                        } catch (Throwable th10) {
                                            try {
                                                throw th10;
                                            } catch (Throwable th11) {
                                                CloseableKt.closeFinally(cursorRawQuery3, th10);
                                                throw th11;
                                            }
                                        }
                                        th = th;
                                        th = th;
                                        r10 = writableDatabase;
                                        r10.endTransaction();
                                        Result.Companion companion6 = Result.INSTANCE;
                                        SyncRelaySession.INSTANCE.flushPending();
                                        Result.m381constructorimpl(Unit.INSTANCE);
                                        throw th;
                                    }
                                    Unit unit6 = Unit.INSTANCE;
                                    JSONObject jSONObjectPut6 = jSONObjectPut3.put("refund_allocations", jSONArray2);
                                    Context context2 = this.context;
                                    Intrinsics.checkNotNullExpressionValue(context2, "context");
                                    String string7 = jSONObjectPut6.toString();
                                    Intrinsics.checkNotNullExpressionValue(string7, "toString(...)");
                                    new SyncProtocolRepository(context2, null, 2, false ? 1 : 0).enqueueSecondaryCommand(writableDatabase, actorUserId, operationId, "POST_PURCHASE_RETURN", "PURCHASE_RETURN", "pending-return-" + operationId, string7);
                                    writableDatabase.setTransactionSuccessful();
                                    PurchaseReturnResult purchaseReturnResult2 = new PurchaseReturnResult("pending-return-" + operationId, "\u0645\u0639\u0644\u0642", purchaseReturnPreviewPreviewPurchaseReturn.getTotalUsdCents(), purchaseReturnPreviewPreviewPurchaseReturn.getSupplierCreditReductionSypMinor(), purchaseReturnPreviewPreviewPurchaseReturn.getSupplierCashRefundSypMinor(), false);
                                    writableDatabase.endTransaction();
                                    try {
                                        Result.Companion companion7 = Result.INSTANCE;
                                        SyncRelaySession.INSTANCE.flushPending();
                                        Result.m381constructorimpl(Unit.INSTANCE);
                                    } catch (Throwable th12) {
                                        Result.Companion companion8 = Result.INSTANCE;
                                        Result.m381constructorimpl(ResultKt.createFailure(th12));
                                    }
                                    return purchaseReturnResult2;
                                } catch (Throwable th13) {
                                    th = th13;
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
                            th = th16;
                        }
                    } catch (Throwable th17) {
                        th2 = th17;
                    }
                    Result.Companion companion9 = Result.INSTANCE;
                    SyncRelaySession.INSTANCE.flushPending();
                    Result.m381constructorimpl(Unit.INSTANCE);
                    throw th;
                } catch (Throwable th18) {
                    Result.Companion companion10 = Result.INSTANCE;
                    Result.m381constructorimpl(ResultKt.createFailure(th18));
                    throw th;
                }
            } catch (Throwable th19) {
                th = th19;
            }
        } catch (Throwable th20) {
            th = th20;
            writableDatabase = writableDatabase;
        }
        th = th;
        r10 = writableDatabase;
        r10.endTransaction();
    }

    private final PurchaseReturnPreview calculate(SQLiteDatabase db, Invoice invoice, List<Plan> plans) throws IOException {
        Iterator<T> it = plans.iterator();
        long jM = 0;
        while (it.hasNext()) {
            jM = MainActivity$$ExternalSyntheticBackport0.m(jM, ((Plan) it.next()).getValue());
        }
        Cursor cursorRawQuery = db.rawQuery("SELECT COALESCE(SUM(total_usd_cents),0),\n                      COALESCE(SUM(supplier_credit_reduction_syp_minor),0),\n                      COALESCE(SUM(supplier_cash_refund_syp_minor),0)\n               FROM returns WHERE original_invoice_type='PURCHASE' AND original_invoice_id=? AND status='POSTED'", new String[]{invoice.getId()});
        try {
            Cursor cursor = cursorRawQuery;
            cursor.moveToFirst();
            Triple triple = new Triple(Long.valueOf(cursor.getLong(0)), Long.valueOf(cursor.getLong(1)), Long.valueOf(cursor.getLong(2)));
            CloseableKt.closeFinally(cursorRawQuery, null);
            long jM2 = DailyBalanceRepository$$ExternalSyntheticBackport0.m(CurrencyConverter.INSTANCE.usdCentsToSyp(MainActivity$$ExternalSyntheticBackport0.m(((Number) triple.getFirst()).longValue(), jM), invoice.getRate()), MainActivity$$ExternalSyntheticBackport0.m(((Number) triple.getSecond()).longValue(), ((Number) triple.getThird()).longValue()));
            if (jM2 < 0) {
                throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u0645\u0637\u0627\u0628\u0642\u0629 \u0627\u0644\u0645\u0631\u062a\u062c\u0639\u0627\u062a \u0627\u0644\u0633\u0627\u0628\u0642\u0629 \u0645\u0639 \u0633\u0639\u0631 \u0635\u0631\u0641 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629".toString());
            }
            long jMin = Math.min(jM2, RangesKt.coerceAtLeast((CurrencyConverter.INSTANCE.usdCentsToSyp(invoice.getTotal(), invoice.getRate()) - paidSyp(db, invoice.getId(), invoice.getRate())) - ((Number) triple.getSecond()).longValue(), 0L));
            return new PurchaseReturnPreview(jM, jM2, jMin, jM2 - jMin);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final Plan planItem(SQLiteDatabase db, Invoice invoice, Item item, BigDecimal quantity) throws IOException {
        long jLongValueExact;
        if (quantity.signum() <= 0 || RangesKt.coerceAtLeast(MainActivity$$ExternalSyntheticBackport0.m(quantity).scale(), 0) > item.getDecimals()) {
            throw new IllegalArgumentException("\u0643\u0645\u064a\u0629 \u0627\u0644\u0645\u0631\u062a\u062c\u0639 \u0623\u0648 \u062f\u0642\u062a\u0647\u0627 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
        }
        Cursor cursorRawQuery = db.rawQuery("SELECT COALESCE(SUM(ri.quantity),0),COALESCE(SUM(ri.line_total_usd_cents),0)\n               FROM return_items ri JOIN returns r ON r.id=ri.return_id\n               WHERE ri.original_purchase_item_id=? AND r.status='POSTED'", new String[]{item.getId()});
        try {
            Cursor cursor = cursorRawQuery;
            cursor.moveToFirst();
            Pair pair = TuplesKt.to(BigDecimal.valueOf(cursor.getDouble(0)), Long.valueOf(cursor.getLong(1)));
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (quantity.compareTo(item.getQuantity().subtract((BigDecimal) pair.getFirst())) > 0) {
                throw new IllegalArgumentException("\u0627\u0644\u0643\u0645\u064a\u0629 \u062a\u062a\u062c\u0627\u0648\u0632 \u0627\u0644\u0645\u062a\u0628\u0642\u064a \u0645\u0646 \u0628\u0646\u062f \u0627\u0644\u0634\u0631\u0627\u0621".toString());
            }
            ArrayList arrayList = new ArrayList();
            Cursor cursorRawQuery2 = db.rawQuery("SELECT id,line_total_usd_cents FROM purchase_items WHERE purchase_invoice_id=? ORDER BY rowid", new String[]{invoice.getId()});
            try {
                Cursor cursor2 = cursorRawQuery2;
                while (cursor2.moveToNext()) {
                    arrayList.add(TuplesKt.to(cursor2.getString(0), Long.valueOf(cursor2.getLong(1))));
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(cursorRawQuery2, null);
                ArrayList arrayList2 = arrayList;
                Iterator it = arrayList2.iterator();
                long j = 0;
                long jM = 0;
                while (it.hasNext()) {
                    jM = MainActivity$$ExternalSyntheticBackport0.m(jM, ((Number) ((Pair) it.next()).getSecond()).longValue());
                }
                if (jM != invoice.getSubtotal()) {
                    throw new IllegalArgumentException("\u0625\u062c\u0645\u0627\u0644\u064a \u0628\u0646\u0648\u062f \u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0634\u0631\u0627\u0621 \u063a\u064a\u0631 \u0645\u062a\u0637\u0627\u0628\u0642".toString());
                }
                int i = 0;
                long jM2 = 0;
                long j2 = 0;
                for (Object obj : arrayList2) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    Pair pair2 = (Pair) obj;
                    if (invoice.getDiscount() == j || jM == j) {
                        jLongValueExact = 0;
                    } else if (i == CollectionsKt.getLastIndex(arrayList)) {
                        jLongValueExact = invoice.getDiscount() - jM2;
                    } else {
                        jLongValueExact = BigDecimal.valueOf(invoice.getDiscount()).multiply(BigDecimal.valueOf(((Number) pair2.getSecond()).longValue())).divide(BigDecimal.valueOf(jM), 0, RoundingMode.DOWN).longValueExact();
                        jM2 = MainActivity$$ExternalSyntheticBackport0.m(jM2, jLongValueExact);
                    }
                    if (Intrinsics.areEqual(pair2.getFirst(), item.getId())) {
                        j2 = jLongValueExact;
                    }
                    i = i2;
                    j = 0;
                }
                if (j2 > item.getLineTotal()) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                long jM3 = DailyBalanceRepository$$ExternalSyntheticBackport0.m(BigDecimal.valueOf(item.getLineTotal() - j2).multiply(((BigDecimal) pair.getFirst()).add(quantity)).divide(item.getQuantity(), 0, RoundingMode.HALF_UP).longValueExact(), ((Number) pair.getSecond()).longValue());
                if (!item.getTracks()) {
                    return new Plan(item, quantity, jM3, CollectionsKt.emptyList());
                }
                ArrayList arrayList3 = new ArrayList();
                Cursor cursorRawQuery3 = db.rawQuery("SELECT id,remaining_quantity,unit_cost_usd_cents FROM inventory_cost_layers\n               WHERE source_item_id=? AND source_type='PURCHASE_INVOICE' AND source_id=?\n                 AND product_id=? AND warehouse_id=? AND remaining_quantity>0 ORDER BY received_at,rowid", new String[]{item.getId(), invoice.getId(), item.getProductId(), invoice.getWarehouseId()});
                try {
                    Cursor cursor3 = cursorRawQuery3;
                    while (cursor3.moveToNext()) {
                        String string = cursor3.getString(0);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(cursor3.getDouble(1));
                        Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "valueOf(...)");
                        arrayList3.add(new Layer(string, bigDecimalValueOf, cursor3.isNull(2) ? null : Long.valueOf(cursor3.getLong(2))));
                    }
                    Unit unit2 = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursorRawQuery3, null);
                    ArrayList<Layer> arrayList4 = arrayList3;
                    BigDecimal bigDecimalAdd = BigDecimal.ZERO;
                    Iterator it2 = arrayList4.iterator();
                    while (it2.hasNext()) {
                        bigDecimalAdd = bigDecimalAdd.add(((Layer) it2.next()).getQuantity());
                    }
                    if (bigDecimalAdd.compareTo(quantity) < 0) {
                        throw new IllegalArgumentException("\u0637\u0628\u0642\u0629 \u0634\u0631\u0627\u0621 \u0627\u0644\u0635\u0646\u0641 \u0627\u0644\u0623\u0635\u0644\u064a\u0629 \u0644\u0627 \u062a\u062d\u062a\u0648\u064a \u0643\u0645\u064a\u0629 \u0643\u0627\u0641\u064a\u0629\u061b \u0644\u0627 \u064a\u0645\u0643\u0646 \u062e\u0635\u0645 \u062f\u0641\u0639\u0627\u062a \u0634\u0631\u0627\u0621 \u0623\u062e\u0631\u0649".toString());
                    }
                    Cursor cursorRawQuery4 = db.rawQuery("SELECT quantity FROM inventory_balances WHERE product_id=? AND warehouse_id=?", new String[]{item.getProductId(), invoice.getWarehouseId()});
                    try {
                        Cursor cursor4 = cursorRawQuery4;
                        BigDecimal bigDecimalValueOf2 = cursor4.moveToFirst() ? BigDecimal.valueOf(cursor4.getDouble(0)) : BigDecimal.ZERO;
                        CloseableKt.closeFinally(cursorRawQuery4, null);
                        if (bigDecimalValueOf2.compareTo(quantity) < 0) {
                            throw new IllegalArgumentException("\u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u0627\u0644\u062d\u0627\u0644\u064a \u0644\u0627 \u064a\u063a\u0637\u064a \u0643\u0645\u064a\u0629 \u0627\u0644\u0645\u0631\u062a\u062c\u0639".toString());
                        }
                        ArrayList arrayList5 = new ArrayList();
                        BigDecimal bigDecimalSubtract = quantity;
                        for (Layer layer : arrayList4) {
                            if (bigDecimalSubtract.signum() > 0) {
                                BigDecimal bigDecimalMin = bigDecimalSubtract.min(layer.getQuantity());
                                Intrinsics.checkNotNull(bigDecimalMin);
                                arrayList5.add(Layer.copy$default(layer, null, bigDecimalMin, null, 5, null));
                                bigDecimalSubtract = bigDecimalSubtract.subtract(bigDecimalMin);
                            }
                        }
                        if (bigDecimalSubtract.signum() == 0) {
                            return new Plan(item, quantity, jM3, arrayList5);
                        }
                        throw new IllegalStateException("Check failed.");
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            CloseableKt.closeFinally(cursorRawQuery4, th);
                            throw th2;
                        }
                    }
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

    private final void validateRefunds(SQLiteDatabase db, Invoice invoice, long target, List<SupplierRefundAllocationInput> allocations) throws IOException {
        long jM = 0;
        if (target == 0) {
            if (!allocations.isEmpty()) {
                throw new IllegalArgumentException("\u0644\u0627 \u064a\u0648\u062c\u062f \u0645\u0628\u0644\u063a \u0646\u0642\u062f\u064a \u0645\u0633\u062a\u0631\u062f \u0645\u0646 \u0627\u0644\u0645\u0648\u0631\u062f".toString());
            }
            return;
        }
        if (allocations.isEmpty()) {
            throw new IllegalArgumentException("\u062d\u062f\u062f \u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0630\u064a \u0633\u064a\u0633\u062a\u0642\u0628\u0644 \u0627\u0644\u0645\u0628\u0644\u063a \u0627\u0644\u0645\u0633\u062a\u0631\u062f".toString());
        }
        for (SupplierRefundAllocationInput supplierRefundAllocationInput : allocations) {
            Cursor cursorRawQuery = db.rawQuery("SELECT currency_code,is_active FROM cash_accounts WHERE id=?", new String[]{supplierRefundAllocationInput.getCashAccountId()});
            try {
                Cursor cursor = cursorRawQuery;
                if (cursor.moveToFirst() && cursor.getInt(1) == 1) {
                    String string = cursor.getString(0);
                    CloseableKt.closeFinally(cursorRawQuery, null);
                    if (!Intrinsics.areEqual(string, "USD") && !Intrinsics.areEqual(string, "SYP")) {
                        throw new IllegalArgumentException("\u0639\u0645\u0644\u0629 \u0627\u0644\u062d\u0633\u0627\u0628 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u0629".toString());
                    }
                    jM = MainActivity$$ExternalSyntheticBackport0.m(jM, Intrinsics.areEqual(string, "SYP") ? supplierRefundAllocationInput.getAmountMinor() : CurrencyConverter.INSTANCE.usdCentsToSyp(supplierRefundAllocationInput.getAmountMinor(), invoice.getRate()));
                } else {
                    throw new IllegalArgumentException("\u062d\u0633\u0627\u0628 \u0627\u0644\u0627\u0633\u062a\u0631\u062f\u0627\u062f \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0623\u0648 \u0645\u0648\u0642\u0648\u0641".toString());
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
        if (jM != target) {
            throw new IllegalArgumentException("\u062a\u0648\u0632\u064a\u0639 \u0627\u0644\u0645\u0628\u0644\u063a \u0627\u0644\u0645\u0633\u062a\u0631\u062f \u0644\u0627 \u064a\u0633\u0627\u0648\u064a \u0627\u0644\u0642\u064a\u0645\u0629 \u0627\u0644\u0645\u0633\u062a\u062d\u0642\u0629 \u0648\u0641\u0642 \u0633\u0639\u0631 \u0635\u0631\u0641 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629".toString());
        }
    }

    private final void postStockReturn(SQLiteDatabase db, String actor, String returnId, String returnItemId, String operationId, int index, Invoice invoice, Item item, Layer layer, String now) throws IOException {
        db.execSQL("UPDATE inventory_cost_layers SET remaining_quantity=remaining_quantity-? WHERE id=? AND remaining_quantity>=?", new Object[]{Double.valueOf(layer.getQuantity().doubleValue()), layer.getId(), Double.valueOf(layer.getQuantity().doubleValue())});
        Cursor cursorRawQuery = db.rawQuery("SELECT changes()", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            boolean z = cursor.moveToFirst() && cursor.getInt(0) == 1;
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (!z) {
                throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062e\u0635\u0645 \u0627\u0644\u0643\u0645\u064a\u0629 \u0645\u0646 \u0637\u0628\u0642\u0629 \u0627\u0644\u0634\u0631\u0627\u0621 \u0627\u0644\u0623\u0635\u0644\u064a\u0629".toString());
            }
            db.execSQL("UPDATE inventory_balances SET quantity=quantity-?,updated_at=? WHERE product_id=? AND warehouse_id=? AND quantity>=?", new Object[]{Double.valueOf(layer.getQuantity().doubleValue()), now, item.getProductId(), invoice.getWarehouseId(), Double.valueOf(layer.getQuantity().doubleValue())});
            Cursor cursorRawQuery2 = db.rawQuery("SELECT changes()", (String[]) null);
            try {
                Cursor cursor2 = cursorRawQuery2;
                boolean z2 = cursor2.moveToFirst() && cursor2.getInt(0) == 1;
                CloseableKt.closeFinally(cursorRawQuery2, null);
                if (!z2) {
                    throw new IllegalArgumentException("\u0631\u0635\u064a\u062f \u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u063a\u064a\u0631 \u0643\u0627\u0641\u064d \u0644\u0625\u062a\u0645\u0627\u0645 \u0627\u0644\u0645\u0631\u062a\u062c\u0639".toString());
                }
                String string = UUID.randomUUID().toString();
                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                ContentValues contentValues = new ContentValues();
                contentValues.put("id", string);
                contentValues.put("product_id", item.getProductId());
                contentValues.put("warehouse_id", invoice.getWarehouseId());
                contentValues.put("movement_type", "PURCHASE_RETURN");
                contentValues.put("quantity_delta", Double.valueOf(-layer.getQuantity().doubleValue()));
                Long unitCost = layer.getUnitCost();
                contentValues.put("unit_cost_usd_cents", Long.valueOf(unitCost != null ? unitCost.longValue() : 0L));
                contentValues.put("source_type", "PURCHASE_RETURN");
                contentValues.put("source_id", returnId);
                contentValues.put("operation_id", operationId + ":STOCK:" + returnItemId + ":" + index);
                String id = item.getId();
                StringBuilder sb = new StringBuilder("\u0645\u0631\u062a\u062c\u0639 \u0645\u0646 \u0628\u0646\u062f \u0627\u0644\u0634\u0631\u0627\u0621 \u0627\u0644\u0623\u0635\u0644\u064a ");
                sb.append(id);
                contentValues.put("note", sb.toString());
                contentValues.put("created_by", actor);
                contentValues.put("created_at", now);
                Unit unit = Unit.INSTANCE;
                db.insertOrThrow("inventory_movements", null, contentValues);
                Long unitCost2 = layer.getUnitCost();
                Long lValueOf = unitCost2 != null ? Long.valueOf(layer.getQuantity().multiply(BigDecimal.valueOf(unitCost2.longValue())).setScale(0, RoundingMode.HALF_UP).longValueExact()) : null;
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("id", UUID.randomUUID().toString());
                contentValues2.put("return_item_id", returnItemId);
                contentValues2.put("source_cost_layer_id", layer.getId());
                contentValues2.put("inventory_movement_id", string);
                contentValues2.put("quantity", Double.valueOf(layer.getQuantity().doubleValue()));
                if (layer.getUnitCost() == null) {
                    contentValues2.putNull("unit_cost_usd_cents");
                } else {
                    contentValues2.put("unit_cost_usd_cents", layer.getUnitCost());
                }
                if (lValueOf == null) {
                    contentValues2.putNull("cost_usd_cents");
                } else {
                    contentValues2.put("cost_usd_cents", lValueOf);
                }
                contentValues2.put("created_at", now);
                Unit unit2 = Unit.INSTANCE;
                db.insertOrThrow("supplier_return_cost_allocations", null, contentValues2);
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

    private final void postRefund(SQLiteDatabase db, String actor, String returnId, String operationId, int index, long rate, String date, String now, SupplierRefundAllocationInput allocation) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT currency_code FROM cash_accounts WHERE id=? AND is_active=1", new String[]{allocation.getCashAccountId()});
        try {
            Cursor cursor = cursorRawQuery;
            if (!cursor.moveToFirst()) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            String string = cursor.getString(0);
            CloseableKt.closeFinally(cursorRawQuery, null);
            long amountMinor = Intrinsics.areEqual(string, "SYP") ? allocation.getAmountMinor() : CurrencyConverter.INSTANCE.usdCentsToSyp(allocation.getAmountMinor(), rate);
            if (amountMinor <= 0) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            String string2 = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
            String str = operationId + ":SUPPLIER_REFUND:" + index;
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", string2);
            contentValues.put("account_id", allocation.getCashAccountId());
            contentValues.put("direction", "IN");
            contentValues.put("amount_minor", Long.valueOf(allocation.getAmountMinor()));
            contentValues.put("currency_code", string);
            contentValues.put("exchange_rate_syp_per_usd_milli", Long.valueOf(rate));
            contentValues.put("transaction_type", "PURCHASE_RETURN_REFUND");
            contentValues.put("source_type", "PURCHASE_RETURN");
            contentValues.put("source_id", returnId);
            contentValues.put("operation_id", str);
            contentValues.put("description", "\u0645\u0628\u0644\u063a \u0645\u0633\u062a\u0631\u062f \u0645\u0646 \u0627\u0644\u0645\u0648\u0631\u062f \u0639\u0646 \u0627\u0644\u0645\u0631\u062a\u062c\u0639 " + returnId);
            contentValues.put("created_by", actor);
            contentValues.put("operating_date", date);
            contentValues.put("created_at", now);
            Unit unit = Unit.INSTANCE;
            db.insertOrThrow("cash_transactions", null, contentValues);
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("id", UUID.randomUUID().toString());
            contentValues2.put("return_id", returnId);
            contentValues2.put("cash_account_id", allocation.getCashAccountId());
            contentValues2.put("cash_transaction_id", string2);
            contentValues2.put("operation_id", str);
            contentValues2.put("amount_minor", Long.valueOf(allocation.getAmountMinor()));
            contentValues2.put("currency_code", string);
            contentValues2.put("exchange_rate_syp_per_usd_milli", Long.valueOf(rate));
            contentValues2.put("refund_equivalent_syp_minor", Long.valueOf(amountMinor));
            contentValues2.put("created_at", now);
            Unit unit2 = Unit.INSTANCE;
            db.insertOrThrow("supplier_return_refund_allocations", null, contentValues2);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final Invoice loadInvoice(SQLiteDatabase db, String id) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT i.id,i.invoice_number,i.supplier_id,s.name,i.warehouse_id,i.status,\n            i.total_usd_cents,i.subtotal_usd_cents,i.discount_usd_cents,i.exchange_rate_syp_per_usd_milli\n            FROM purchase_invoices i JOIN suppliers s ON s.id=i.supplier_id WHERE i.id=?", new String[]{id});
        try {
            Cursor cursor = cursorRawQuery;
            if (!cursor.moveToFirst()) {
                throw new IllegalArgumentException("\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0634\u0631\u0627\u0621 \u0623\u0648 \u0627\u0644\u0645\u0648\u0631\u062f \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
            }
            String string = cursor.getString(0);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String string2 = cursor.getString(1);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            String string3 = cursor.getString(2);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
            String string4 = cursor.getString(3);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
            String string5 = cursor.getString(4);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
            String string6 = cursor.getString(5);
            Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
            Invoice invoice = new Invoice(string, string2, string3, string4, string5, string6, cursor.getLong(6), cursor.getLong(7), cursor.getLong(8), cursor.getLong(9));
            CloseableKt.closeFinally(cursorRawQuery, null);
            return invoice;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final List<Item> loadItems(SQLiteDatabase db, Invoice invoice) throws IOException {
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = db.rawQuery("SELECT pi.id,pi.product_id,pi.quantity,pi.unit_cost_usd_cents,pi.line_total_usd_cents,\n            p.track_inventory,u.decimal_places FROM purchase_items pi JOIN products p ON p.id=pi.product_id\n            JOIN units u ON u.id=p.unit_id WHERE pi.purchase_invoice_id=? ORDER BY pi.rowid", new String[]{invoice.getId()});
        try {
            Cursor cursor = cursorRawQuery;
            while (cursor.moveToNext()) {
                String string = cursor.getString(0);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String string2 = cursor.getString(1);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                BigDecimal bigDecimalValueOf = BigDecimal.valueOf(cursor.getDouble(2));
                Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "valueOf(...)");
                arrayList.add(new Item(string, string2, bigDecimalValueOf, cursor.getLong(3), cursor.getLong(4), cursor.getInt(5) == 1, cursor.getInt(6)));
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (!arrayList.isEmpty()) {
                Iterator it = arrayList.iterator();
                long lineTotal = 0;
                while (it.hasNext()) {
                    lineTotal += ((Item) it.next()).getLineTotal();
                }
                if (lineTotal == invoice.getSubtotal()) {
                    return arrayList;
                }
            }
            throw new IllegalArgumentException("\u0628\u0646\u0648\u062f \u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0634\u0631\u0627\u0621 \u063a\u064a\u0631 \u0645\u062a\u0637\u0627\u0628\u0642\u0629".toString());
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final BigDecimal returnedQuantity(SQLiteDatabase db, String id) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT COALESCE(SUM(ri.quantity),0) FROM return_items ri JOIN returns r ON r.id=ri.return_id\n            WHERE ri.original_purchase_item_id=? AND r.status='POSTED'", new String[]{id});
        try {
            Cursor cursor = cursorRawQuery;
            cursor.moveToFirst();
            BigDecimal bigDecimalValueOf = BigDecimal.valueOf(cursor.getDouble(0));
            CloseableKt.closeFinally(cursorRawQuery, null);
            Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "use(...)");
            return bigDecimalValueOf;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final BigDecimal sourceLayerQuantity(SQLiteDatabase db, Invoice invoice, String itemId) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT COALESCE(SUM(remaining_quantity),0) FROM inventory_cost_layers\n            WHERE source_item_id=? AND source_id=? AND source_type='PURCHASE_INVOICE' AND warehouse_id=?", new String[]{itemId, invoice.getId(), invoice.getWarehouseId()});
        try {
            Cursor cursor = cursorRawQuery;
            cursor.moveToFirst();
            BigDecimal bigDecimalValueOf = BigDecimal.valueOf(cursor.getDouble(0));
            CloseableKt.closeFinally(cursorRawQuery, null);
            Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "use(...)");
            return bigDecimalValueOf;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final long paidSyp(SQLiteDatabase db, String id, long rate) throws IOException {
        long jUsdCentsToSyp;
        Cursor cursorRawQuery = db.rawQuery("SELECT amount_minor,currency_code FROM invoice_payments WHERE invoice_type='PURCHASE' AND invoice_id=?", new String[]{id});
        try {
            Cursor cursor = cursorRawQuery;
            long jM = 0;
            while (cursor.moveToNext()) {
                String string = cursor.getString(1);
                if (Intrinsics.areEqual(string, "SYP")) {
                    jUsdCentsToSyp = cursor.getLong(0);
                } else {
                    if (!Intrinsics.areEqual(string, "USD")) {
                        throw new IllegalStateException("\u0639\u0645\u0644\u0629 \u062f\u0641\u0639\u0629 \u0627\u0644\u0645\u0648\u0631\u062f \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u0629".toString());
                    }
                    jUsdCentsToSyp = CurrencyConverter.INSTANCE.usdCentsToSyp(cursor.getLong(0), rate);
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

    private final String productName(SQLiteDatabase db, String id) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT name FROM products WHERE id=?", new String[]{id});
        try {
            Cursor cursor = cursorRawQuery;
            if (!cursor.moveToFirst()) {
                throw new IllegalArgumentException("Failed requirement.".toString());
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

    private final Triple<String, String, String> findOperation(SQLiteDatabase db, String id) throws IOException {
        Triple<String, String, String> triple;
        Cursor cursorRawQuery = db.rawQuery("SELECT operation_type,payload_hash,result_reference FROM operation_registry WHERE operation_id=?", new String[]{id});
        try {
            Cursor cursor = cursorRawQuery;
            if (cursor.moveToFirst()) {
                triple = new Triple<>(cursor.getString(0), cursor.getString(1), cursor.isNull(2) ? null : cursor.getString(2));
            } else {
                triple = null;
            }
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

    private final PurchaseReturnResult loadResult(SQLiteDatabase db, String id, boolean replayed) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT return_number,total_usd_cents,supplier_credit_reduction_syp_minor,\n            supplier_cash_refund_syp_minor FROM returns WHERE id=? AND original_invoice_type='PURCHASE' AND status='POSTED'", new String[]{id});
        try {
            Cursor cursor = cursorRawQuery;
            if (!cursor.moveToFirst()) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            String string = cursor.getString(0);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            PurchaseReturnResult purchaseReturnResult = new PurchaseReturnResult(id, string, cursor.getLong(1), cursor.getLong(2), cursor.getLong(3), replayed);
            CloseableKt.closeFinally(cursorRawQuery, null);
            return purchaseReturnResult;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final String nextNumber(SQLiteDatabase db, String date) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT COUNT(*) FROM returns WHERE original_invoice_type='PURCHASE' AND return_date=?", new String[]{date});
        try {
            Cursor cursor = cursorRawQuery;
            cursor.moveToFirst();
            int i = cursor.getInt(0) + 1;
            CloseableKt.closeFinally(cursorRawQuery, null);
            return "PR-" + StringsKt.replace$default(date, "-", "", false, 4, (Object) null) + "-" + StringsKt.padStart(String.valueOf(i), 4, '0');
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
        return ArraysKt.joinToString$default(bArrDigest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.hazem.dailystore.data.PurchaseReturnRepository$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PurchaseReturnRepository.hash$lambda$92(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence hash$lambda$92(byte b) {
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
