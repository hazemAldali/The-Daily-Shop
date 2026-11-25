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

/* JADX INFO: compiled from: SalesReturnRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u001a\u0018\u00002\u00020\u0001:\u0004TUVWB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b2\u0006\u0010\u000f\u001a\u00020\u0010J\u001c\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000bJl\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u00102\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u000b2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u001f\u001a\u00020 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0010J\u001c\u0010\"\u001a\u00020#2\n\u0010$\u001a\u00060%j\u0002`&2\u0006\u0010'\u001a\u00020\u0010H\u0002J\"\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u000b2\n\u0010$\u001a\u00060%j\u0002`&2\u0006\u0010*\u001a\u00020#H\u0002J,\u0010+\u001a\u00020,2\n\u0010$\u001a\u00060%j\u0002`&2\u0006\u0010-\u001a\u00020)2\u0006\u0010.\u001a\u00020/2\u0006\u0010*\u001a\u00020#H\u0002J*\u00100\u001a\b\u0012\u0004\u0012\u0002010\u000b2\n\u0010$\u001a\u00060%j\u0002`&2\u0006\u00102\u001a\u00020\u00102\u0006\u00103\u001a\u00020/H\u0002J2\u00104\u001a\u0002052\n\u0010$\u001a\u00060%j\u0002`&2\u0006\u0010*\u001a\u00020#2\u0006\u00106\u001a\u0002072\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u001d0\u000bH\u0002Jk\u00109\u001a\u00020\u00102\n\u0010$\u001a\u00060%j\u0002`&2\u0006\u0010:\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\u00102\u0006\u0010<\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020\u00102\u0006\u0010@\u001a\u00020\u00102\u0006\u0010.\u001a\u00020/2\b\u0010A\u001a\u0004\u0018\u0001072\u0006\u0010B\u001a\u00020\u0010H\u0002\u00a2\u0006\u0002\u0010CJT\u0010D\u001a\u0002052\n\u0010$\u001a\u00060%j\u0002`&2\u0006\u0010:\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010E\u001a\u00020>2\u0006\u0010F\u001a\u0002072\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010B\u001a\u00020\u00102\u0006\u0010G\u001a\u00020\u001dH\u0002J$\u0010H\u001a\u0002072\n\u0010$\u001a\u00060%j\u0002`&2\u0006\u0010I\u001a\u00020\u00102\u0006\u0010J\u001a\u000207H\u0002J\u001c\u0010K\u001a\u0002072\n\u0010$\u001a\u00060%j\u0002`&2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J$\u0010L\u001a\u00020\u00162\n\u0010$\u001a\u00060%j\u0002`&2\u0006\u0010'\u001a\u00020\u00102\u0006\u0010M\u001a\u00020 H\u0002J\u001c\u0010N\u001a\u00020\u00102\n\u0010$\u001a\u00060%j\u0002`&2\u0006\u0010O\u001a\u00020\u0010H\u0002J\u001c\u0010P\u001a\u00020\u00102\n\u0010$\u001a\u00060%j\u0002`&2\u0006\u0010?\u001a\u00020\u0010H\u0002J\u0010\u0010Q\u001a\u00020\u00102\u0006\u0010R\u001a\u00020\u0010H\u0002J\b\u0010S\u001a\u00020\u0010H\u0002R\u0018\u0010\u0002\u001a\n \u0006*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006X"}, d2 = {"Lcom/hazem/dailystore/data/SalesReturnRepository;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "kotlin.jvm.PlatformType", "Landroid/content/Context;", "database", "Lcom/hazem/dailystore/data/AppDatabase;", "listReturnableInvoices", "", "Lcom/hazem/dailystore/data/SalesReturnInvoiceOption;", "listReturnableItems", "Lcom/hazem/dailystore/data/SalesReturnableItem;", "invoiceId", "", "previewSalesReturn", "Lcom/hazem/dailystore/data/SalesReturnPreview;", "items", "Lcom/hazem/dailystore/data/SalesReturnItemInput;", "postSalesReturn", "Lcom/hazem/dailystore/data/SalesReturnResult;", "actorUserId", "originalInvoiceId", "operationId", "returnDate", "reason", "refundAllocations", "Lcom/hazem/dailystore/data/SalesRefundAllocationInput;", "occurredAt", "syncOrigin", "", "operatingDate", "loadOriginalInvoice", "Lcom/hazem/dailystore/data/SalesReturnRepository$OriginalInvoice;", "db", "Lnet/zetetic/database/sqlcipher/SQLiteDatabase;", "Lcom/hazem/dailystore/data/AppSqliteDatabase;", "id", "loadOriginalItems", "Lcom/hazem/dailystore/data/SalesReturnRepository$OriginalItem;", "invoice", "buildItemPlan", "Lcom/hazem/dailystore/data/SalesReturnRepository$ItemPlan;", "original", "quantity", "Ljava/math/BigDecimal;", "planReturnedCosts", "Lcom/hazem/dailystore/data/SalesReturnRepository$CostAllocationPlan;", "salesItemId", "requested", "validateRefundAllocations", "", "refundUsd", "", "allocations", "addReturnedStockMovement", "actor", "returnId", "returnItemId", "movementIndex", "", "productId", "warehouseId", "unitCost", "now", "(Lnet/zetetic/database/sqlcipher/SQLiteDatabase;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/math/BigDecimal;Ljava/lang/Long;Ljava/lang/String;)Ljava/lang/String;", "postRefundAllocation", "index", "rate", "allocation", "accountBalance", "accountId", "opening", "paidUsdCents", "loadResult", "replayed", "nextReturnNumber", "date", "productName", "hash", "value", "timestamp", "OriginalInvoice", "OriginalItem", "CostAllocationPlan", "ItemPlan", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SalesReturnRepository {
    private final Context context;
    private final AppDatabase database;

    public SalesReturnRepository(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context.getApplicationContext();
        this.database = AppDatabase.INSTANCE.getInstance(context);
    }

    /* JADX INFO: compiled from: SalesReturnRepository.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001BY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\n\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\nH\u00c6\u0003J\t\u0010#\u001a\u00020\nH\u00c6\u0003J\t\u0010$\u001a\u00020\nH\u00c6\u0003J\t\u0010%\u001a\u00020\nH\u00c6\u0003Jo\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\nH\u00c6\u0001J\u0013\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010*\u001a\u00020+H\u00d6\u0001J\t\u0010,\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u000b\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\f\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0011\u0010\r\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018\u00a8\u0006-"}, d2 = {"Lcom/hazem/dailystore/data/SalesReturnRepository$OriginalInvoice;", "", "id", "", "number", "saleType", "customerId", "warehouseId", NotificationCompat.CATEGORY_STATUS, "totalUsdCents", "", "subtotalUsdCents", "discountUsdCents", "rateMilli", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJJJ)V", "getId", "()Ljava/lang/String;", "getNumber", "getSaleType", "getCustomerId", "getWarehouseId", "getStatus", "getTotalUsdCents", "()J", "getSubtotalUsdCents", "getDiscountUsdCents", "getRateMilli", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class OriginalInvoice {
        private final String customerId;
        private final long discountUsdCents;
        private final String id;
        private final String number;
        private final long rateMilli;
        private final String saleType;
        private final String status;
        private final long subtotalUsdCents;
        private final long totalUsdCents;
        private final String warehouseId;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final long getRateMilli() {
            return this.rateMilli;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getNumber() {
            return this.number;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getSaleType() {
            return this.saleType;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getCustomerId() {
            return this.customerId;
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
        public final long getTotalUsdCents() {
            return this.totalUsdCents;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final long getSubtotalUsdCents() {
            return this.subtotalUsdCents;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final long getDiscountUsdCents() {
            return this.discountUsdCents;
        }

        public final OriginalInvoice copy(String id, String number, String saleType, String customerId, String warehouseId, String status, long totalUsdCents, long subtotalUsdCents, long discountUsdCents, long rateMilli) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(number, "number");
            Intrinsics.checkNotNullParameter(saleType, "saleType");
            Intrinsics.checkNotNullParameter(warehouseId, "warehouseId");
            Intrinsics.checkNotNullParameter(status, "status");
            return new OriginalInvoice(id, number, saleType, customerId, warehouseId, status, totalUsdCents, subtotalUsdCents, discountUsdCents, rateMilli);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof OriginalInvoice)) {
                return false;
            }
            OriginalInvoice originalInvoice = (OriginalInvoice) other;
            return Intrinsics.areEqual(this.id, originalInvoice.id) && Intrinsics.areEqual(this.number, originalInvoice.number) && Intrinsics.areEqual(this.saleType, originalInvoice.saleType) && Intrinsics.areEqual(this.customerId, originalInvoice.customerId) && Intrinsics.areEqual(this.warehouseId, originalInvoice.warehouseId) && Intrinsics.areEqual(this.status, originalInvoice.status) && this.totalUsdCents == originalInvoice.totalUsdCents && this.subtotalUsdCents == originalInvoice.subtotalUsdCents && this.discountUsdCents == originalInvoice.discountUsdCents && this.rateMilli == originalInvoice.rateMilli;
        }

        public int hashCode() {
            int iHashCode = ((((this.id.hashCode() * 31) + this.number.hashCode()) * 31) + this.saleType.hashCode()) * 31;
            String str = this.customerId;
            return ((((((((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.warehouseId.hashCode()) * 31) + this.status.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.totalUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.subtotalUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.discountUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.rateMilli);
        }

        public String toString() {
            return "OriginalInvoice(id=" + this.id + ", number=" + this.number + ", saleType=" + this.saleType + ", customerId=" + this.customerId + ", warehouseId=" + this.warehouseId + ", status=" + this.status + ", totalUsdCents=" + this.totalUsdCents + ", subtotalUsdCents=" + this.subtotalUsdCents + ", discountUsdCents=" + this.discountUsdCents + ", rateMilli=" + this.rateMilli + ")";
        }

        public OriginalInvoice(String id, String number, String saleType, String str, String warehouseId, String status, long j, long j2, long j3, long j4) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(number, "number");
            Intrinsics.checkNotNullParameter(saleType, "saleType");
            Intrinsics.checkNotNullParameter(warehouseId, "warehouseId");
            Intrinsics.checkNotNullParameter(status, "status");
            this.id = id;
            this.number = number;
            this.saleType = saleType;
            this.customerId = str;
            this.warehouseId = warehouseId;
            this.status = status;
            this.totalUsdCents = j;
            this.subtotalUsdCents = j2;
            this.discountUsdCents = j3;
            this.rateMilli = j4;
        }

        public final String getCustomerId() {
            return this.customerId;
        }

        public final String getId() {
            return this.id;
        }

        public final String getNumber() {
            return this.number;
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

        public final String getWarehouseId() {
            return this.warehouseId;
        }

        public final long getDiscountUsdCents() {
            return this.discountUsdCents;
        }

        public final long getRateMilli() {
            return this.rateMilli;
        }

        public final long getSubtotalUsdCents() {
            return this.subtotalUsdCents;
        }
    }

    /* JADX INFO: compiled from: SalesReturnRepository.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u001b\b\u0082\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\bH\u00c6\u0003J\t\u0010 \u001a\u00020\bH\u00c6\u0003J\t\u0010!\u001a\u00020\u000bH\u00c6\u0003J\t\u0010\"\u001a\u00020\rH\u00c6\u0003JO\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u00c6\u0001J\u0013\u0010$\u001a\u00020\u000b2\b\u0010%\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010&\u001a\u00020\rH\u00d6\u0001J\t\u0010'\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\t\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b\u00a8\u0006("}, d2 = {"Lcom/hazem/dailystore/data/SalesReturnRepository$OriginalItem;", "", "id", "", "productId", "quantity", "Ljava/math/BigDecimal;", "unitPriceUsdCents", "", "lineTotalUsdCents", "tracksInventory", "", "decimalPlaces", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/math/BigDecimal;JJZI)V", "getId", "()Ljava/lang/String;", "getProductId", "getQuantity", "()Ljava/math/BigDecimal;", "getUnitPriceUsdCents", "()J", "getLineTotalUsdCents", "getTracksInventory", "()Z", "getDecimalPlaces", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class OriginalItem {
        private final int decimalPlaces;
        private final String id;
        private final long lineTotalUsdCents;
        private final String productId;
        private final BigDecimal quantity;
        private final boolean tracksInventory;
        private final long unitPriceUsdCents;

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
        public final long getUnitPriceUsdCents() {
            return this.unitPriceUsdCents;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final long getLineTotalUsdCents() {
            return this.lineTotalUsdCents;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final boolean getTracksInventory() {
            return this.tracksInventory;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final int getDecimalPlaces() {
            return this.decimalPlaces;
        }

        public final OriginalItem copy(String id, String productId, BigDecimal quantity, long unitPriceUsdCents, long lineTotalUsdCents, boolean tracksInventory, int decimalPlaces) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(productId, "productId");
            Intrinsics.checkNotNullParameter(quantity, "quantity");
            return new OriginalItem(id, productId, quantity, unitPriceUsdCents, lineTotalUsdCents, tracksInventory, decimalPlaces);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof OriginalItem)) {
                return false;
            }
            OriginalItem originalItem = (OriginalItem) other;
            return Intrinsics.areEqual(this.id, originalItem.id) && Intrinsics.areEqual(this.productId, originalItem.productId) && Intrinsics.areEqual(this.quantity, originalItem.quantity) && this.unitPriceUsdCents == originalItem.unitPriceUsdCents && this.lineTotalUsdCents == originalItem.lineTotalUsdCents && this.tracksInventory == originalItem.tracksInventory && this.decimalPlaces == originalItem.decimalPlaces;
        }

        public int hashCode() {
            return (((((((((((this.id.hashCode() * 31) + this.productId.hashCode()) * 31) + this.quantity.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.unitPriceUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.lineTotalUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.tracksInventory)) * 31) + this.decimalPlaces;
        }

        public String toString() {
            return "OriginalItem(id=" + this.id + ", productId=" + this.productId + ", quantity=" + this.quantity + ", unitPriceUsdCents=" + this.unitPriceUsdCents + ", lineTotalUsdCents=" + this.lineTotalUsdCents + ", tracksInventory=" + this.tracksInventory + ", decimalPlaces=" + this.decimalPlaces + ")";
        }

        public OriginalItem(String id, String productId, BigDecimal quantity, long j, long j2, boolean z, int i) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(productId, "productId");
            Intrinsics.checkNotNullParameter(quantity, "quantity");
            this.id = id;
            this.productId = productId;
            this.quantity = quantity;
            this.unitPriceUsdCents = j;
            this.lineTotalUsdCents = j2;
            this.tracksInventory = z;
            this.decimalPlaces = i;
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

        public final long getLineTotalUsdCents() {
            return this.lineTotalUsdCents;
        }

        public final long getUnitPriceUsdCents() {
            return this.unitPriceUsdCents;
        }

        public final int getDecimalPlaces() {
            return this.decimalPlaces;
        }

        public final boolean getTracksInventory() {
            return this.tracksInventory;
        }
    }

    /* JADX INFO: compiled from: SalesReturnRepository.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B-\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\b\t\u0010\nJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00c6\u0003J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0010J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0010J<\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0012\u0010\u0010\u00a8\u0006\u001f"}, d2 = {"Lcom/hazem/dailystore/data/SalesReturnRepository$CostAllocationPlan;", "", "sourceAllocationId", "", "quantity", "Ljava/math/BigDecimal;", "unitCostUsdCents", "", "costUsdCents", "<init>", "(Ljava/lang/String;Ljava/math/BigDecimal;Ljava/lang/Long;Ljava/lang/Long;)V", "getSourceAllocationId", "()Ljava/lang/String;", "getQuantity", "()Ljava/math/BigDecimal;", "getUnitCostUsdCents", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getCostUsdCents", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/math/BigDecimal;Ljava/lang/Long;Ljava/lang/Long;)Lcom/hazem/dailystore/data/SalesReturnRepository$CostAllocationPlan;", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class CostAllocationPlan {
        private final Long costUsdCents;
        private final BigDecimal quantity;
        private final String sourceAllocationId;
        private final Long unitCostUsdCents;

        public static /* synthetic */ CostAllocationPlan copy$default(CostAllocationPlan costAllocationPlan, String str, BigDecimal bigDecimal, Long l, Long l2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = costAllocationPlan.sourceAllocationId;
            }
            if ((i & 2) != 0) {
                bigDecimal = costAllocationPlan.quantity;
            }
            if ((i & 4) != 0) {
                l = costAllocationPlan.unitCostUsdCents;
            }
            if ((i & 8) != 0) {
                l2 = costAllocationPlan.costUsdCents;
            }
            return costAllocationPlan.copy(str, bigDecimal, l, l2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getSourceAllocationId() {
            return this.sourceAllocationId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final BigDecimal getQuantity() {
            return this.quantity;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Long getUnitCostUsdCents() {
            return this.unitCostUsdCents;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Long getCostUsdCents() {
            return this.costUsdCents;
        }

        public final CostAllocationPlan copy(String sourceAllocationId, BigDecimal quantity, Long unitCostUsdCents, Long costUsdCents) {
            Intrinsics.checkNotNullParameter(quantity, "quantity");
            return new CostAllocationPlan(sourceAllocationId, quantity, unitCostUsdCents, costUsdCents);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CostAllocationPlan)) {
                return false;
            }
            CostAllocationPlan costAllocationPlan = (CostAllocationPlan) other;
            return Intrinsics.areEqual(this.sourceAllocationId, costAllocationPlan.sourceAllocationId) && Intrinsics.areEqual(this.quantity, costAllocationPlan.quantity) && Intrinsics.areEqual(this.unitCostUsdCents, costAllocationPlan.unitCostUsdCents) && Intrinsics.areEqual(this.costUsdCents, costAllocationPlan.costUsdCents);
        }

        public int hashCode() {
            String str = this.sourceAllocationId;
            int iHashCode = (((str == null ? 0 : str.hashCode()) * 31) + this.quantity.hashCode()) * 31;
            Long l = this.unitCostUsdCents;
            int iHashCode2 = (iHashCode + (l == null ? 0 : l.hashCode())) * 31;
            Long l2 = this.costUsdCents;
            return iHashCode2 + (l2 != null ? l2.hashCode() : 0);
        }

        public String toString() {
            return "CostAllocationPlan(sourceAllocationId=" + this.sourceAllocationId + ", quantity=" + this.quantity + ", unitCostUsdCents=" + this.unitCostUsdCents + ", costUsdCents=" + this.costUsdCents + ")";
        }

        public CostAllocationPlan(String str, BigDecimal quantity, Long l, Long l2) {
            Intrinsics.checkNotNullParameter(quantity, "quantity");
            this.sourceAllocationId = str;
            this.quantity = quantity;
            this.unitCostUsdCents = l;
            this.costUsdCents = l2;
        }

        public final BigDecimal getQuantity() {
            return this.quantity;
        }

        public final String getSourceAllocationId() {
            return this.sourceAllocationId;
        }

        public final Long getCostUsdCents() {
            return this.costUsdCents;
        }

        public final Long getUnitCostUsdCents() {
            return this.unitCostUsdCents;
        }
    }

    /* JADX INFO: compiled from: SalesReturnRepository.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\u0004\b\f\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0007H\u00c6\u0003J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0015J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00c6\u0003JH\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00c6\u0001\u00a2\u0006\u0002\u0010\u001fJ\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010#\u001a\u00020$H\u00d6\u0001J\t\u0010%\u001a\u00020&H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006'"}, d2 = {"Lcom/hazem/dailystore/data/SalesReturnRepository$ItemPlan;", "", "original", "Lcom/hazem/dailystore/data/SalesReturnRepository$OriginalItem;", "quantity", "Ljava/math/BigDecimal;", "returnValueUsdCents", "", "returnedCogsUsdCents", "costAllocations", "", "Lcom/hazem/dailystore/data/SalesReturnRepository$CostAllocationPlan;", "<init>", "(Lcom/hazem/dailystore/data/SalesReturnRepository$OriginalItem;Ljava/math/BigDecimal;JLjava/lang/Long;Ljava/util/List;)V", "getOriginal", "()Lcom/hazem/dailystore/data/SalesReturnRepository$OriginalItem;", "getQuantity", "()Ljava/math/BigDecimal;", "getReturnValueUsdCents", "()J", "getReturnedCogsUsdCents", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getCostAllocations", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "copy", "(Lcom/hazem/dailystore/data/SalesReturnRepository$OriginalItem;Ljava/math/BigDecimal;JLjava/lang/Long;Ljava/util/List;)Lcom/hazem/dailystore/data/SalesReturnRepository$ItemPlan;", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class ItemPlan {
        private final List<CostAllocationPlan> costAllocations;
        private final OriginalItem original;
        private final BigDecimal quantity;
        private final long returnValueUsdCents;
        private final Long returnedCogsUsdCents;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ItemPlan copy$default(ItemPlan itemPlan, OriginalItem originalItem, BigDecimal bigDecimal, long j, Long l, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                originalItem = itemPlan.original;
            }
            if ((i & 2) != 0) {
                bigDecimal = itemPlan.quantity;
            }
            BigDecimal bigDecimal2 = bigDecimal;
            if ((i & 4) != 0) {
                j = itemPlan.returnValueUsdCents;
            }
            long j2 = j;
            if ((i & 8) != 0) {
                l = itemPlan.returnedCogsUsdCents;
            }
            Long l2 = l;
            if ((i & 16) != 0) {
                list = itemPlan.costAllocations;
            }
            return itemPlan.copy(originalItem, bigDecimal2, j2, l2, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final OriginalItem getOriginal() {
            return this.original;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final BigDecimal getQuantity() {
            return this.quantity;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final long getReturnValueUsdCents() {
            return this.returnValueUsdCents;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Long getReturnedCogsUsdCents() {
            return this.returnedCogsUsdCents;
        }

        public final List<CostAllocationPlan> component5() {
            return this.costAllocations;
        }

        public final ItemPlan copy(OriginalItem original, BigDecimal quantity, long returnValueUsdCents, Long returnedCogsUsdCents, List<CostAllocationPlan> costAllocations) {
            Intrinsics.checkNotNullParameter(original, "original");
            Intrinsics.checkNotNullParameter(quantity, "quantity");
            Intrinsics.checkNotNullParameter(costAllocations, "costAllocations");
            return new ItemPlan(original, quantity, returnValueUsdCents, returnedCogsUsdCents, costAllocations);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ItemPlan)) {
                return false;
            }
            ItemPlan itemPlan = (ItemPlan) other;
            return Intrinsics.areEqual(this.original, itemPlan.original) && Intrinsics.areEqual(this.quantity, itemPlan.quantity) && this.returnValueUsdCents == itemPlan.returnValueUsdCents && Intrinsics.areEqual(this.returnedCogsUsdCents, itemPlan.returnedCogsUsdCents) && Intrinsics.areEqual(this.costAllocations, itemPlan.costAllocations);
        }

        public int hashCode() {
            int iHashCode = ((((this.original.hashCode() * 31) + this.quantity.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.returnValueUsdCents)) * 31;
            Long l = this.returnedCogsUsdCents;
            return ((iHashCode + (l == null ? 0 : l.hashCode())) * 31) + this.costAllocations.hashCode();
        }

        public String toString() {
            return "ItemPlan(original=" + this.original + ", quantity=" + this.quantity + ", returnValueUsdCents=" + this.returnValueUsdCents + ", returnedCogsUsdCents=" + this.returnedCogsUsdCents + ", costAllocations=" + this.costAllocations + ")";
        }

        public ItemPlan(OriginalItem original, BigDecimal quantity, long j, Long l, List<CostAllocationPlan> costAllocations) {
            Intrinsics.checkNotNullParameter(original, "original");
            Intrinsics.checkNotNullParameter(quantity, "quantity");
            Intrinsics.checkNotNullParameter(costAllocations, "costAllocations");
            this.original = original;
            this.quantity = quantity;
            this.returnValueUsdCents = j;
            this.returnedCogsUsdCents = l;
            this.costAllocations = costAllocations;
        }

        public final OriginalItem getOriginal() {
            return this.original;
        }

        public final BigDecimal getQuantity() {
            return this.quantity;
        }

        public final long getReturnValueUsdCents() {
            return this.returnValueUsdCents;
        }

        public final Long getReturnedCogsUsdCents() {
            return this.returnedCogsUsdCents;
        }

        public final List<CostAllocationPlan> getCostAllocations() {
            return this.costAllocations;
        }
    }

    public final List<SalesReturnInvoiceOption> listReturnableInvoices() throws IOException {
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery("SELECT i.id,i.invoice_number,i.invoice_date,i.sale_type,c.name,i.total_usd_cents\n               FROM sales_invoices i LEFT JOIN customers c ON c.id=i.customer_id\n               WHERE i.status IN ('POSTED','PARTIALLY_PAID','PAID')\n               ORDER BY i.invoice_date DESC,i.created_at DESC", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            while (cursor.moveToNext()) {
                String string = cursor.getString(0);
                Intrinsics.checkNotNull(string);
                List<SalesReturnableItem> listListReturnableItems = listReturnableItems(string);
                if (!(listListReturnableItems instanceof Collection) || !listListReturnableItems.isEmpty()) {
                    Iterator<T> it = listListReturnableItems.iterator();
                    while (it.hasNext()) {
                        if (((SalesReturnableItem) it.next()).getAvailableQuantity().signum() > 0) {
                            String string2 = cursor.getString(1);
                            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                            String string3 = cursor.getString(2);
                            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                            String string4 = cursor.getString(3);
                            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                            arrayList.add(new SalesReturnInvoiceOption(string, string2, string3, string4, cursor.isNull(4) ? null : cursor.getString(4), cursor.getLong(5)));
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
    public final List<SalesReturnableItem> listReturnableItems(String invoiceId) throws IOException {
        Intrinsics.checkNotNullParameter(invoiceId, "invoiceId");
        if (StringsKt.isBlank(invoiceId)) {
            throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0645\u0637\u0644\u0648\u0628".toString());
        }
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        List<OriginalItem> listLoadOriginalItems = loadOriginalItems(readableDatabase, loadOriginalInvoice(readableDatabase, invoiceId));
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listLoadOriginalItems, 10));
        for (OriginalItem originalItem : listLoadOriginalItems) {
            Cursor cursorRawQuery = readableDatabase.rawQuery("SELECT COALESCE(SUM(ri.quantity),0)\n                   FROM return_items ri JOIN returns r ON r.id=ri.return_id\n                   WHERE ri.original_sales_item_id=? AND r.status='POSTED'", new String[]{originalItem.getId()});
            try {
                Cursor cursor = cursorRawQuery;
                cursor.moveToFirst();
                BigDecimal bigDecimalValueOf = BigDecimal.valueOf(cursor.getDouble(0));
                CloseableKt.closeFinally(cursorRawQuery, null);
                String id = originalItem.getId();
                String strProductName = productName(readableDatabase, originalItem.getProductId());
                BigDecimal quantity = originalItem.getQuantity();
                Intrinsics.checkNotNull(bigDecimalValueOf);
                BigDecimal bigDecimalMax = originalItem.getQuantity().subtract(bigDecimalValueOf).max(BigDecimal.ZERO);
                Intrinsics.checkNotNullExpressionValue(bigDecimalMax, "max(...)");
                arrayList.add(new SalesReturnableItem(id, strProductName, quantity, bigDecimalValueOf, bigDecimalMax, originalItem.getUnitPriceUsdCents(), originalItem.getTracksInventory()));
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

    /* JADX WARN: Multi-variable type inference failed */
    public final SalesReturnPreview previewSalesReturn(String invoiceId, List<SalesReturnItemInput> items) throws IOException {
        Intrinsics.checkNotNullParameter(invoiceId, "invoiceId");
        Intrinsics.checkNotNullParameter(items, "items");
        if (StringsKt.isBlank(invoiceId) || items.isEmpty()) {
            throw new IllegalArgumentException("\u062d\u062f\u062f \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0648\u0627\u0644\u0623\u0635\u0646\u0627\u0641 \u0627\u0644\u0645\u0631\u062a\u062c\u0639\u0629".toString());
        }
        List<SalesReturnItemInput> list = items;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((SalesReturnItemInput) it.next()).getOriginalSalesItemId());
        }
        if (CollectionsKt.distinct(arrayList).size() != items.size()) {
            throw new IllegalArgumentException("\u0644\u0627 \u062a\u0643\u0631\u0631 \u0628\u0646\u062f \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0646\u0641\u0633\u0647".toString());
        }
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        OriginalInvoice originalInvoiceLoadOriginalInvoice = loadOriginalInvoice(readableDatabase, invoiceId);
        if (!SetsKt.setOf((Object[]) new String[]{"POSTED", "PARTIALLY_PAID", "PAID"}).contains(originalInvoiceLoadOriginalInvoice.getStatus())) {
            throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0645\u0639\u0627\u064a\u0646\u0629 \u0645\u0631\u062a\u062c\u0639 \u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u063a\u064a\u0631 \u0645\u0639\u062a\u0645\u062f\u0629".toString());
        }
        if (originalInvoiceLoadOriginalInvoice.getRateMilli() <= 0) {
            throw new IllegalArgumentException("\u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0623\u0635\u0644\u064a\u0629 \u0628\u0644\u0627 \u0633\u0639\u0631 \u0635\u0631\u0641 \u0645\u062d\u0641\u0648\u0638".toString());
        }
        List<OriginalItem> listLoadOriginalItems = loadOriginalItems(readableDatabase, originalInvoiceLoadOriginalInvoice);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it2 = list.iterator();
        while (true) {
            Object obj = null;
            if (!it2.hasNext()) {
                Iterator it3 = arrayList2.iterator();
                long jM = 0;
                while (it3.hasNext()) {
                    jM = MainActivity$$ExternalSyntheticBackport0.m(jM, ((ItemPlan) it3.next()).getReturnValueUsdCents());
                }
                long jPaidUsdCents = paidUsdCents(readableDatabase, invoiceId);
                Cursor cursorRawQuery = readableDatabase.rawQuery("SELECT COALESCE(SUM(credit_reduction_usd_cents),0)\n               FROM returns WHERE original_invoice_type='SALE'\n                 AND original_invoice_id=? AND status='POSTED'", new String[]{invoiceId});
                try {
                    Cursor cursor = cursorRawQuery;
                    cursor.moveToFirst();
                    long j = cursor.getLong(0);
                    CloseableKt.closeFinally(cursorRawQuery, null);
                    long jMin = Math.min(jM, RangesKt.coerceAtLeast((originalInvoiceLoadOriginalInvoice.getTotalUsdCents() - jPaidUsdCents) - j, 0L));
                    long jM2 = DailyBalanceRepository$$ExternalSyntheticBackport0.m(jM, jMin);
                    return new SalesReturnPreview(jM, jMin, jM2, CurrencyConverter.INSTANCE.usdCentsToSyp(jM2, originalInvoiceLoadOriginalInvoice.getRateMilli()));
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(cursorRawQuery, th);
                        throw th2;
                    }
                }
            }
            SalesReturnItemInput salesReturnItemInput = (SalesReturnItemInput) it2.next();
            for (Object obj2 : listLoadOriginalItems) {
                if (Intrinsics.areEqual(((OriginalItem) obj2).getId(), salesReturnItemInput.getOriginalSalesItemId())) {
                    obj = obj2;
                    break;
                }
            }
            OriginalItem originalItem = (OriginalItem) obj;
            if (originalItem == null) {
                throw new IllegalArgumentException("\u0623\u062d\u062f \u0627\u0644\u0623\u0635\u0646\u0627\u0641 \u0644\u0627 \u064a\u0646\u062a\u0645\u064a \u0625\u0644\u0649 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0623\u0635\u0644\u064a\u0629");
            }
            arrayList2.add(buildItemPlan(readableDatabase, originalItem, salesReturnItemInput.getQuantity(), originalInvoiceLoadOriginalInvoice));
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x02c0  */
    /* JADX WARN: Code duplicated, block: B:248:0x06b3 A[Catch: all -> 0x0480, TRY_ENTER, TryCatch #19 {all -> 0x0480, blocks: (B:75:0x024b, B:81:0x0277, B:145:0x03f1, B:159:0x047c, B:160:0x047f, B:179:0x04de, B:181:0x04e8, B:183:0x04f4, B:185:0x04fc, B:187:0x0503, B:194:0x0525, B:195:0x0530, B:196:0x0531, B:197:0x053d, B:206:0x0585, B:207:0x0593, B:209:0x0599, B:214:0x05bf, B:216:0x05c5, B:217:0x05d5, B:218:0x05dc, B:222:0x05f1, B:230:0x066a, B:235:0x067e, B:248:0x06b3, B:250:0x06c7, B:252:0x06ce, B:257:0x06fd, B:263:0x0771, B:272:0x07a2, B:275:0x0818, B:241:0x0692, B:76:0x0260, B:78:0x0269, B:156:0x0479), top: B:458:0x0249, inners: #16, #18 }] */
    /* JADX WARN: Code duplicated, block: B:250:0x06c7 A[Catch: all -> 0x0480, TryCatch #19 {all -> 0x0480, blocks: (B:75:0x024b, B:81:0x0277, B:145:0x03f1, B:159:0x047c, B:160:0x047f, B:179:0x04de, B:181:0x04e8, B:183:0x04f4, B:185:0x04fc, B:187:0x0503, B:194:0x0525, B:195:0x0530, B:196:0x0531, B:197:0x053d, B:206:0x0585, B:207:0x0593, B:209:0x0599, B:214:0x05bf, B:216:0x05c5, B:217:0x05d5, B:218:0x05dc, B:222:0x05f1, B:230:0x066a, B:235:0x067e, B:248:0x06b3, B:250:0x06c7, B:252:0x06ce, B:257:0x06fd, B:263:0x0771, B:272:0x07a2, B:275:0x0818, B:241:0x0692, B:76:0x0260, B:78:0x0269, B:156:0x0479), top: B:458:0x0249, inners: #16, #18 }] */
    /* JADX WARN: Code duplicated, block: B:251:0x06cc  */
    /* JADX WARN: Code duplicated, block: B:90:0x029b  */
    /* JADX WARN: Code duplicated, block: B:92:0x029e A[Catch: all -> 0x0473, TryCatch #13 {all -> 0x0473, blocks: (B:85:0x0280, B:87:0x0286, B:92:0x029e, B:96:0x02a9, B:98:0x02b1, B:103:0x02c3, B:106:0x02ce, B:107:0x0300, B:109:0x0306, B:110:0x032f, B:111:0x0344, B:113:0x034a, B:118:0x038a, B:123:0x03b2, B:132:0x03ce, B:133:0x03d1, B:141:0x03e3, B:142:0x03e6, B:143:0x03e7, B:138:0x03e0, B:119:0x03a3, B:121:0x03ac, B:125:0x03bd, B:126:0x03c8, B:129:0x03cb, B:114:0x037b, B:116:0x0384, B:134:0x03d2, B:135:0x03dd), top: B:448:0x0280, inners: #1, #21, #23, #32 }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v11 */
    /* JADX WARN: Type inference failed for: r10v12 */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v14 */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r10v17 */
    /* JADX WARN: Type inference failed for: r10v19 */
    /* JADX WARN: Type inference failed for: r10v20 */
    /* JADX WARN: Type inference failed for: r10v21, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v26 */
    /* JADX WARN: Type inference failed for: r10v4, types: [java.lang.Object, net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r13v11 */
    /* JADX WARN: Type inference failed for: r13v25 */
    /* JADX WARN: Type inference failed for: r13v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* JADX WARN: Type inference failed for: r13v7 */
    /* JADX WARN: Type inference failed for: r13v8, types: [com.hazem.dailystore.data.SalesReturnRepository] */
    /* JADX WARN: Type inference failed for: r17v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r17v1 */
    /* JADX WARN: Type inference failed for: r17v14 */
    /* JADX WARN: Type inference failed for: r17v15 */
    /* JADX WARN: Type inference failed for: r17v16 */
    /* JADX WARN: Type inference failed for: r17v18 */
    /* JADX WARN: Type inference failed for: r17v19 */
    /* JADX WARN: Type inference failed for: r17v2 */
    /* JADX WARN: Type inference failed for: r17v20 */
    /* JADX WARN: Type inference failed for: r17v24 */
    /* JADX WARN: Type inference failed for: r17v26 */
    /* JADX WARN: Type inference failed for: r17v28 */
    /* JADX WARN: Type inference failed for: r17v29 */
    /* JADX WARN: Type inference failed for: r17v3 */
    /* JADX WARN: Type inference failed for: r17v33 */
    /* JADX WARN: Type inference failed for: r17v4, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r17v8 */
    /* JADX WARN: Type inference failed for: r19v12 */
    /* JADX WARN: Type inference failed for: r19v13 */
    /* JADX WARN: Type inference failed for: r19v15 */
    /* JADX WARN: Type inference failed for: r19v16 */
    /* JADX WARN: Type inference failed for: r19v17 */
    /* JADX WARN: Type inference failed for: r19v18 */
    /* JADX WARN: Type inference failed for: r19v22 */
    /* JADX WARN: Type inference failed for: r1v44, types: [com.hazem.dailystore.data.SyncProtocolRepository] */
    /* JADX WARN: Type inference failed for: r2v102, types: [com.hazem.dailystore.data.SyncProtocolRepository] */
    /* JADX WARN: Type inference failed for: r2v47, types: [android.content.ContentValues] */
    /* JADX WARN: Type inference failed for: r2v48, types: [android.content.ContentValues] */
    /* JADX WARN: Type inference failed for: r2v49, types: [android.content.ContentValues] */
    /* JADX WARN: Type inference failed for: r2v53 */
    /* JADX WARN: Type inference failed for: r2v70, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r2v72, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r2v73, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r3v108, types: [android.content.ContentValues] */
    /* JADX WARN: Type inference failed for: r3v123, types: [com.hazem.dailystore.data.PermissionPolicy] */
    /* JADX WARN: Type inference failed for: r49v0 */
    /* JADX WARN: Type inference failed for: r49v1 */
    /* JADX WARN: Type inference failed for: r49v2 */
    /* JADX WARN: Type inference failed for: r49v3 */
    /* JADX WARN: Type inference failed for: r54v0, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r54v1 */
    /* JADX WARN: Type inference failed for: r54v2 */
    /* JADX WARN: Type inference failed for: r54v3 */
    /* JADX WARN: Type inference failed for: r56v0, types: [com.hazem.dailystore.data.SalesReturnRepository] */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.hazem.dailystore.data.PermissionPolicy] */
    /* JADX WARN: Type inference failed for: r6v37, types: [com.hazem.dailystore.data.StoreTime] */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v19 */
    /* JADX WARN: Type inference failed for: r9v20 */
    /* JADX WARN: Type inference failed for: r9v23 */
    /* JADX WARN: Type inference failed for: r9v24 */
    /* JADX WARN: Type inference failed for: r9v25 */
    /* JADX WARN: Type inference failed for: r9v27 */
    /* JADX WARN: Type inference failed for: r9v28 */
    /* JADX WARN: Type inference failed for: r9v29, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r9v45 */
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
    public final SalesReturnResult postSalesReturn(String actorUserId, String originalInvoiceId, String operationId, String returnDate, String reason, List<SalesReturnItemInput> items, List<SalesRefundAllocationInput> refundAllocations, String occurredAt, boolean syncOrigin, String operatingDate) throws Throwable {
        char c;
        Throwable th;
        ?? r17;
        Throwable th2;
        String string;
        Throwable th3;
        Triple triple;
        Throwable th4;
        Long l;
        ?? r9;
        ?? r13;
        long j;
        Long l2;
        String str;
        long j2;
        ?? r54;
        Throwable th5;
        String str2;
        String str3;
        ?? r10;
        String str4;
        String str5;
        ?? r11;
        String str6;
        String str7;
        String str8;
        ?? r19;
        String str9;
        String str10;
        String str11;
        String str12;
        ?? r12;
        long jValueOf;
        Iterator it;
        Long returnedCogsUsdCents;
        long jLongValue;
        Iterator it2;
        List<OriginalItem> list;
        Object next;
        SalesReturnResult salesReturnResultLoadResult;
        Throwable th6;
        JSONObject jSONObject;
        String strTimestamp;
        String strOperatingDate;
        String strOptString;
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(originalInvoiceId, "originalInvoiceId");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        Intrinsics.checkNotNullParameter(returnDate, "returnDate");
        Intrinsics.checkNotNullParameter(reason, "reason");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(refundAllocations, "refundAllocations");
        if (StringsKt.isBlank(originalInvoiceId) || StringsKt.isBlank(operationId)) {
            throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0645\u0631\u062a\u062c\u0639 \u063a\u064a\u0631 \u0645\u0643\u062a\u0645\u0644\u0629".toString());
        }
        if (!new Regex("\\d{4}-\\d{2}-\\d{2}").matches(returnDate)) {
            throw new IllegalArgumentException("\u062a\u0627\u0631\u064a\u062e \u0627\u0644\u0645\u0631\u062a\u062c\u0639 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
        }
        String str13 = reason;
        if (StringsKt.isBlank(str13)) {
            throw new IllegalArgumentException("\u0633\u0628\u0628 \u0627\u0644\u0645\u0631\u062a\u062c\u0639 \u0645\u0637\u0644\u0648\u0628".toString());
        }
        if (items.isEmpty()) {
            throw new IllegalArgumentException("\u0623\u0636\u0641 \u0635\u0646\u0641\u0627\u064b \u0648\u0627\u062d\u062f\u0627\u064b \u0639\u0644\u0649 \u0627\u0644\u0623\u0642\u0644 \u0625\u0644\u0649 \u0627\u0644\u0645\u0631\u062a\u062c\u0639".toString());
        }
        List<SalesReturnItemInput> list2 = items;
        ?? writableDatabase = "created_at";
        if (!(list2 instanceof Collection) || !list2.isEmpty()) {
            for (SalesReturnItemInput salesReturnItemInput : list2) {
                if (StringsKt.isBlank(salesReturnItemInput.getOriginalSalesItemId()) || salesReturnItemInput.getQuantity().signum() <= 0) {
                    throw new IllegalArgumentException("\u062a\u062d\u0642\u0642 \u0645\u0646 \u0623\u0635\u0646\u0627\u0641 \u0648\u0643\u0645\u064a\u0627\u062a \u0627\u0644\u0645\u0631\u062a\u062c\u0639".toString());
                }
            }
        }
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it3 = list2.iterator();
        while (it3.hasNext()) {
            arrayList.add(((SalesReturnItemInput) it3.next()).getOriginalSalesItemId());
        }
        if (CollectionsKt.distinct(arrayList).size() != items.size()) {
            throw new IllegalArgumentException("\u0644\u0627 \u062a\u0643\u0631\u0631 \u0628\u0646\u062f \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0646\u0641\u0633\u0647\u061b \u0627\u062c\u0645\u0639 \u0643\u0645\u064a\u062a\u0647 \u0641\u064a \u0633\u0637\u0631 \u0648\u0627\u062d\u062f".toString());
        }
        List<SalesRefundAllocationInput> list3 = refundAllocations;
        if (!(list3 instanceof Collection) || !list3.isEmpty()) {
            for (SalesRefundAllocationInput salesRefundAllocationInput : list3) {
                if (StringsKt.isBlank(salesRefundAllocationInput.getCashAccountId()) || salesRefundAllocationInput.getAmountMinor() <= 0) {
                    throw new IllegalArgumentException("\u062a\u0648\u0632\u064a\u0639 \u0631\u062f \u0627\u0644\u0645\u0628\u0644\u063a \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                }
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
        Iterator it4 = list3.iterator();
        while (it4.hasNext()) {
            arrayList2.add(((SalesRefundAllocationInput) it4.next()).getCashAccountId());
        }
        if (CollectionsKt.distinct(arrayList2).size() != refundAllocations.size()) {
            throw new IllegalArgumentException("\u0627\u062c\u0645\u0639 \u0627\u0644\u0645\u0628\u0627\u0644\u063a \u0627\u0644\u0645\u062e\u0635\u0635\u0629 \u0644\u0644\u062d\u0633\u0627\u0628 \u0646\u0641\u0633\u0647 \u0641\u064a \u0633\u0637\u0631 \u0648\u0627\u062d\u062f".toString());
        }
        StringBuilder sb = new StringBuilder();
        sb.append(originalInvoiceId);
        char c2 = '|';
        sb.append('|');
        sb.append(returnDate);
        sb.append('|');
        sb.append(StringsKt.trim((CharSequence) str13).toString());
        Iterator it5 = CollectionsKt.sortedWith(list2, new Comparator() { // from class: com.hazem.dailystore.data.SalesReturnRepository$postSalesReturn$lambda$30$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(((SalesReturnItemInput) t).getOriginalSalesItemId(), ((SalesReturnItemInput) t2).getOriginalSalesItemId());
            }
        }).iterator();
        while (true) {
            c = ':';
            if (!it5.hasNext()) {
                break;
            }
            SalesReturnItemInput salesReturnItemInput2 = (SalesReturnItemInput) it5.next();
            sb.append('|');
            sb.append(salesReturnItemInput2.getOriginalSalesItemId());
            sb.append(':');
            sb.append(MainActivity$$ExternalSyntheticBackport0.m(salesReturnItemInput2.getQuantity()).toPlainString());
        }
        for (SalesRefundAllocationInput salesRefundAllocationInput2 : CollectionsKt.sortedWith(list3, new Comparator() { // from class: com.hazem.dailystore.data.SalesReturnRepository$postSalesReturn$lambda$30$$inlined$sortedBy$2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(((SalesRefundAllocationInput) t).getCashAccountId(), ((SalesRefundAllocationInput) t2).getCashAccountId());
            }
        })) {
            sb.append(c2);
            sb.append(salesRefundAllocationInput2.getCashAccountId());
            sb.append(c);
            sb.append(salesRefundAllocationInput2.getAmountMinor());
            c2 = '|';
            c = ':';
        }
        Unit unit = Unit.INSTANCE;
        String strHash = hash(sb.toString());
        writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        writableDatabase.beginTransaction();
        try {
            try {
                Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", null);
                try {
                    Cursor cursor = cursorRawQuery;
                    if (cursor.moveToFirst()) {
                        try {
                            string = cursor.getString(0);
                        } catch (Throwable th7) {
                            th2 = th7;
                            try {
                                throw th2;
                            } catch (Throwable th8) {
                                CloseableKt.closeFinally(cursorRawQuery, th2);
                                throw th8;
                            }
                        }
                    } else {
                        string = null;
                    }
                    CloseableKt.closeFinally(cursorRawQuery, null);
                    String str14 = "quantity";
                    String str15 = "return_date";
                    try {
                        if (Intrinsics.areEqual(string, "SECONDARY")) {
                            PermissionPolicy.INSTANCE.requireAssigned(writableDatabase, actorUserId, PermissionPolicy.RETURNS_PROCESS);
                            SalesReturnPreview salesReturnPreviewPreviewSalesReturn = previewSalesReturn(originalInvoiceId, items);
                            Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT payload_json FROM sync_operation_envelopes WHERE operation_id=?", new String[]{operationId});
                            try {
                                Cursor cursor2 = cursorRawQuery2;
                                if (cursor2.moveToFirst()) {
                                    jSONObject = new JSONObject(cursor2.getString(0));
                                    th6 = null;
                                } else {
                                    th6 = null;
                                    jSONObject = null;
                                }
                                CloseableKt.closeFinally(cursorRawQuery2, th6);
                                if (occurredAt != null) {
                                    strTimestamp = occurredAt;
                                } else if (jSONObject != null) {
                                    try {
                                        String strOptString2 = jSONObject.optString("occurred_at");
                                        if (strOptString2 == null) {
                                            strTimestamp = null;
                                        } else if (new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(strOptString2)) {
                                            strTimestamp = strOptString2;
                                        } else {
                                            strTimestamp = null;
                                        }
                                        if (strTimestamp == null) {
                                            strTimestamp = timestamp();
                                        }
                                    } catch (Throwable th9) {
                                        th = th9;
                                    }
                                } else {
                                    strTimestamp = null;
                                    if (strTimestamp == null) {
                                        strTimestamp = timestamp();
                                    }
                                }
                                if (operatingDate == null) {
                                    if (jSONObject == null || (strOptString = jSONObject.optString("operating_date")) == null) {
                                        strOptString = null;
                                    } else if (!new Regex("\\d{4}-\\d{2}-\\d{2}").matches(strOptString)) {
                                        strOptString = null;
                                    }
                                    strOperatingDate = strOptString == null ? StoreTime.INSTANCE.operatingDate(writableDatabase, strTimestamp) : strOptString;
                                } else {
                                    strOperatingDate = operatingDate;
                                }
                                JSONObject jSONObjectPut = new JSONObject().put("original_invoice_id", originalInvoiceId).put("return_date", returnDate).put("reason", StringsKt.trim((CharSequence) reason).toString()).put("occurred_at", strTimestamp).put("operating_date", strOperatingDate);
                                JSONArray jSONArray = new JSONArray();
                                for (SalesReturnItemInput salesReturnItemInput3 : items) {
                                    jSONArray.put(new JSONObject().put("original_sales_item_id", salesReturnItemInput3.getOriginalSalesItemId()).put("quantity", MainActivity$$ExternalSyntheticBackport0.m(salesReturnItemInput3.getQuantity()).toPlainString()));
                                }
                                Unit unit2 = Unit.INSTANCE;
                                JSONObject jSONObjectPut2 = jSONObjectPut.put("items", jSONArray);
                                JSONArray jSONArray2 = new JSONArray();
                                for (SalesRefundAllocationInput salesRefundAllocationInput3 : refundAllocations) {
                                    JSONObject jSONObjectPut3 = new JSONObject().put("cash_account_id", salesRefundAllocationInput3.getCashAccountId()).put("amount_minor", salesRefundAllocationInput3.getAmountMinor());
                                    Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT account_type FROM cash_accounts WHERE id=?", new String[]{salesRefundAllocationInput3.getCashAccountId()});
                                    try {
                                        Cursor cursor3 = cursorRawQuery3;
                                        if (!cursor3.moveToFirst()) {
                                            throw new IllegalArgumentException("Failed requirement.".toString());
                                        }
                                        String string2 = cursor3.getString(0);
                                        CloseableKt.closeFinally(cursorRawQuery3, null);
                                        JSONObject jSONObjectPut4 = jSONObjectPut3.put("cash_account_type", string2);
                                        Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT currency_code FROM cash_accounts WHERE id=?", new String[]{salesRefundAllocationInput3.getCashAccountId()});
                                        try {
                                            Cursor cursor4 = cursorRawQuery4;
                                            if (!cursor4.moveToFirst()) {
                                                throw new IllegalArgumentException("Failed requirement.".toString());
                                            }
                                            String string3 = cursor4.getString(0);
                                            CloseableKt.closeFinally(cursorRawQuery4, null);
                                            jSONArray2.put(jSONObjectPut4.put("cash_account_currency_code", string3));
                                        } catch (Throwable th10) {
                                            try {
                                                throw th10;
                                            } catch (Throwable th11) {
                                                CloseableKt.closeFinally(cursorRawQuery4, th10);
                                                throw th11;
                                            }
                                        }
                                    } catch (Throwable th12) {
                                        try {
                                            throw th12;
                                        } catch (Throwable th13) {
                                            CloseableKt.closeFinally(cursorRawQuery3, th12);
                                            throw th13;
                                        }
                                    }
                                    th = th9;
                                }
                                Unit unit3 = Unit.INSTANCE;
                                JSONObject jSONObjectPut5 = jSONObjectPut2.put("refund_allocations", jSONArray2);
                                Context context = this.context;
                                Intrinsics.checkNotNullExpressionValue(context, "context");
                                String string4 = jSONObjectPut5.toString();
                                Intrinsics.checkNotNullExpressionValue(string4, "toString(...)");
                                new SyncProtocolRepository(context, null, 2, false ? 1 : 0).enqueueSecondaryCommand(writableDatabase, actorUserId, operationId, "POST_SALES_RETURN", "SALES_RETURN", "pending-return-" + operationId, string4);
                                writableDatabase.setTransactionSuccessful();
                                SalesReturnResult salesReturnResult = new SalesReturnResult("pending-return-" + operationId, "\u0645\u0639\u0644\u0642", salesReturnPreviewPreviewSalesReturn.getTotalUsdCents(), salesReturnPreviewPreviewSalesReturn.getCreditReductionUsdCents(), salesReturnPreviewPreviewSalesReturn.getCashRefundUsdCents(), null, false);
                                writableDatabase.endTransaction();
                                try {
                                    Result.Companion companion = Result.INSTANCE;
                                    SyncRelaySession.INSTANCE.flushPending();
                                    Result.m381constructorimpl(Unit.INSTANCE);
                                } catch (Throwable th14) {
                                    Result.Companion companion2 = Result.INSTANCE;
                                    Result.m381constructorimpl(ResultKt.createFailure(th14));
                                }
                                return salesReturnResult;
                            } catch (Throwable th15) {
                                try {
                                    throw th15;
                                } catch (Throwable th16) {
                                    CloseableKt.closeFinally(cursorRawQuery2, th15);
                                    throw th16;
                                }
                            }
                        }
                        String str16 = "toString(...)";
                        PermissionPolicy.INSTANCE.require(writableDatabase, actorUserId, PermissionPolicy.RETURNS_PROCESS);
                        Cursor cursorRawQuery5 = writableDatabase.rawQuery("SELECT operation_type,payload_hash,result_reference FROM operation_registry WHERE operation_id=?", new String[]{operationId});
                        try {
                            Cursor cursor5 = cursorRawQuery5;
                            if (cursor5.moveToFirst()) {
                                try {
                                    triple = new Triple(cursor5.getString(0), cursor5.getString(1), cursor5.isNull(2) ? null : cursor5.getString(2));
                                    th4 = null;
                                } catch (Throwable th17) {
                                    th3 = th17;
                                    try {
                                        throw th3;
                                    } catch (Throwable th18) {
                                        CloseableKt.closeFinally(cursorRawQuery5, th3);
                                        throw th18;
                                    }
                                }
                            } else {
                                th4 = null;
                                triple = null;
                            }
                            CloseableKt.closeFinally(cursorRawQuery5, th4);
                            if (triple != null) {
                                if (!Intrinsics.areEqual(triple.getFirst(), "POST_SALES_RETURN") || !Intrinsics.areEqual(triple.getSecond(), strHash)) {
                                    throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0645\u0633\u062a\u062e\u062f\u0645 \u0644\u0645\u0631\u062a\u062c\u0639 \u0623\u0648 \u0628\u064a\u0627\u0646\u0627\u062a \u0645\u062e\u062a\u0644\u0641\u0629".toString());
                                }
                                String str17 = (String) triple.getThird();
                                if (str17 == null || (salesReturnResultLoadResult = loadResult(writableDatabase, str17, true)) == null) {
                                    throw new IllegalStateException("\u0633\u062c\u0644 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0644\u0627 \u064a\u062d\u062a\u0648\u064a \u0645\u0631\u062c\u0639 \u0627\u0644\u0645\u0631\u062a\u062c\u0639".toString());
                                }
                                writableDatabase.setTransactionSuccessful();
                                writableDatabase.endTransaction();
                                try {
                                    Result.Companion companion3 = Result.INSTANCE;
                                    SyncRelaySession.INSTANCE.flushPending();
                                    Result.m381constructorimpl(Unit.INSTANCE);
                                } catch (Throwable th19) {
                                    Result.Companion companion4 = Result.INSTANCE;
                                    Result.m381constructorimpl(ResultKt.createFailure(th19));
                                }
                                return salesReturnResultLoadResult;
                            }
                            String str18 = strHash;
                            OriginalInvoice originalInvoiceLoadOriginalInvoice = loadOriginalInvoice(writableDatabase, originalInvoiceId);
                            String str19 = "POST_SALES_RETURN";
                            if (!SetsKt.setOf((Object[]) new String[]{"POSTED", "PARTIALLY_PAID", "PAID"}).contains(originalInvoiceLoadOriginalInvoice.getStatus())) {
                                throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0625\u0631\u062c\u0627\u0639 \u0623\u0635\u0646\u0627\u0641 \u0645\u0646 \u0641\u0627\u062a\u0648\u0631\u0629 \u063a\u064a\u0631 \u0645\u0639\u062a\u0645\u062f\u0629 \u0623\u0648 \u0645\u0644\u063a\u0627\u0629".toString());
                            }
                            if (originalInvoiceLoadOriginalInvoice.getRateMilli() <= 0) {
                                throw new IllegalArgumentException("\u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0623\u0635\u0644\u064a\u0629 \u0628\u0644\u0627 \u0633\u0639\u0631 \u0635\u0631\u0641 \u0645\u062d\u0641\u0648\u0638\u061b \u0644\u0627 \u064a\u0645\u0643\u0646 \u0631\u062f \u0642\u064a\u0645\u062a\u0647\u0627 \u0628\u0623\u0645\u0627\u0646".toString());
                            }
                            List<OriginalItem> listLoadOriginalItems = loadOriginalItems(writableDatabase, originalInvoiceLoadOriginalInvoice);
                            List<SalesReturnItemInput> list4 = items;
                            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list4, 10));
                            Iterator it6 = list4.iterator();
                            while (it6.hasNext()) {
                                SalesReturnItemInput salesReturnItemInput4 = (SalesReturnItemInput) it6.next();
                                Iterator it7 = listLoadOriginalItems.iterator();
                                while (true) {
                                    if (!it7.hasNext()) {
                                        it2 = it6;
                                        list = listLoadOriginalItems;
                                        next = null;
                                        break;
                                    }
                                    next = it7.next();
                                    it2 = it6;
                                    list = listLoadOriginalItems;
                                    if (Intrinsics.areEqual(((OriginalItem) next).getId(), salesReturnItemInput4.getOriginalSalesItemId())) {
                                        break;
                                    }
                                    it6 = it2;
                                    listLoadOriginalItems = list;
                                }
                                OriginalItem originalItem = (OriginalItem) next;
                                if (originalItem == null) {
                                    throw new IllegalArgumentException("\u0623\u062d\u062f \u0627\u0644\u0623\u0635\u0646\u0627\u0641 \u0644\u0627 \u064a\u0646\u062a\u0645\u064a \u0625\u0644\u0649 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0623\u0635\u0644\u064a\u0629");
                                }
                                arrayList3.add(buildItemPlan(writableDatabase, originalItem, salesReturnItemInput4.getQuantity(), originalInvoiceLoadOriginalInvoice));
                                it6 = it2;
                                listLoadOriginalItems = list;
                            }
                            ArrayList arrayList4 = arrayList3;
                            Iterator it8 = arrayList4.iterator();
                            long jM = 0;
                            while (it8.hasNext()) {
                                String str20 = str18;
                                jM = MainActivity$$ExternalSyntheticBackport0.m(jM, ((ItemPlan) it8.next()).getReturnValueUsdCents());
                                str18 = str20;
                            }
                            String str21 = str18;
                            long jPaidUsdCents = paidUsdCents(writableDatabase, originalInvoiceId);
                            Cursor cursorRawQuery6 = writableDatabase.rawQuery("SELECT COALESCE(SUM(credit_reduction_usd_cents),0)\n                   FROM returns WHERE original_invoice_type='SALE'\n                     AND original_invoice_id=? AND status='POSTED'", new String[]{originalInvoiceId});
                            try {
                                Cursor cursor6 = cursorRawQuery6;
                                cursor6.moveToFirst();
                                long j3 = cursor6.getLong(0);
                                CloseableKt.closeFinally(cursorRawQuery6, null);
                                long jMin = Math.min(jM, RangesKt.coerceAtLeast((originalInvoiceLoadOriginalInvoice.getTotalUsdCents() - jPaidUsdCents) - j3, 0L));
                                long jM2 = DailyBalanceRepository$$ExternalSyntheticBackport0.m(jM, jMin);
                                long j4 = jM;
                                long j5 = jM2;
                                String str22 = "id";
                                validateRefundAllocations(writableDatabase, originalInvoiceLoadOriginalInvoice, jM2, refundAllocations);
                                String string5 = UUID.randomUUID().toString();
                                Intrinsics.checkNotNullExpressionValue(string5, str16);
                                String strTimestamp2 = occurredAt == null ? timestamp() : occurredAt;
                                String strNextReturnNumber = nextReturnNumber(writableDatabase, returnDate);
                                ArrayList arrayList5 = arrayList4;
                                if ((arrayList5 instanceof Collection) && arrayList5.isEmpty()) {
                                    jValueOf = 0L;
                                    it = arrayList4.iterator();
                                    while (it.hasNext()) {
                                        ItemPlan itemPlan = (ItemPlan) it.next();
                                        Iterator it9 = it;
                                        long jLongValue2 = jValueOf.longValue();
                                        returnedCogsUsdCents = itemPlan.getReturnedCogsUsdCents();
                                        if (returnedCogsUsdCents != null) {
                                            jLongValue = returnedCogsUsdCents.longValue();
                                        } else {
                                            jLongValue = 0;
                                        }
                                        jValueOf = Long.valueOf(MainActivity$$ExternalSyntheticBackport0.m(jLongValue2, jLongValue));
                                        it = it9;
                                    }
                                    l = jValueOf;
                                } else {
                                    Iterator it10 = arrayList5.iterator();
                                    while (true) {
                                        if (!it10.hasNext()) {
                                            jValueOf = 0L;
                                            it = arrayList4.iterator();
                                            while (it.hasNext()) {
                                                ItemPlan itemPlan2 = (ItemPlan) it.next();
                                                Iterator it11 = it;
                                                long jLongValue3 = jValueOf.longValue();
                                                returnedCogsUsdCents = itemPlan2.getReturnedCogsUsdCents();
                                                if (returnedCogsUsdCents != null) {
                                                    jLongValue = returnedCogsUsdCents.longValue();
                                                } else {
                                                    jLongValue = 0;
                                                }
                                                jValueOf = Long.valueOf(MainActivity$$ExternalSyntheticBackport0.m(jLongValue3, jLongValue));
                                                it = it11;
                                            }
                                            l = jValueOf;
                                        } else if (((ItemPlan) it10.next()).getReturnedCogsUsdCents() == null) {
                                            l = null;
                                        }
                                    }
                                }
                                ?? contentValues = new ContentValues();
                                contentValues.put(str22, string5);
                                contentValues.put("return_number", strNextReturnNumber);
                                String str23 = strNextReturnNumber;
                                contentValues.put("original_invoice_type", "SALE");
                                String str24 = "original_invoice_id";
                                contentValues.put(str24, originalInvoiceId);
                                if (originalInvoiceLoadOriginalInvoice.getCustomerId() == null) {
                                    contentValues.putNull("party_type");
                                    contentValues.putNull("party_id");
                                } else {
                                    contentValues.put("party_type", "CUSTOMER");
                                    contentValues.put("party_id", originalInvoiceLoadOriginalInvoice.getCustomerId());
                                }
                                contentValues.put("return_date", returnDate);
                                contentValues.put("total_usd_cents", Long.valueOf(j4));
                                contentValues.put("reason", StringsKt.trim((CharSequence) reason).toString());
                                contentValues.put(NotificationCompat.CATEGORY_STATUS, "POSTED");
                                String str25 = actorUserId;
                                contentValues.put("created_by", str25);
                                contentValues.put(writableDatabase, strTimestamp2);
                                ?? r18 = writableDatabase;
                                String str26 = operationId;
                                contentValues.put("operation_id", str26);
                                contentValues.put("posted_at", strTimestamp2);
                                String str27 = "reason";
                                contentValues.put("cash_refund_usd_cents", Long.valueOf(j5));
                                contentValues.put("credit_reduction_usd_cents", Long.valueOf(jMin));
                                String str28 = "returned_cogs_usd_cents";
                                if (l == null) {
                                    contentValues.putNull("returned_cogs_usd_cents");
                                } else {
                                    contentValues.put("returned_cogs_usd_cents", l);
                                }
                                Long l3 = l;
                                contentValues.put("exchange_rate_syp_per_usd_milli", Long.valueOf(originalInvoiceLoadOriginalInvoice.getRateMilli()));
                                Unit unit4 = Unit.INSTANCE;
                                writableDatabase.insertOrThrow("returns", null, contentValues);
                                int i = 0;
                                writableDatabase = writableDatabase;
                                for (Object obj : arrayList4) {
                                    try {
                                        int i2 = i + 1;
                                        if (i < 0) {
                                            CollectionsKt.throwIndexOverflow();
                                        }
                                        ItemPlan itemPlan3 = (ItemPlan) obj;
                                        String string6 = UUID.randomUUID().toString();
                                        Intrinsics.checkNotNullExpressionValue(string6, str16);
                                        ContentValues contentValues2 = new ContentValues();
                                        contentValues2.put(str22, string6);
                                        contentValues2.put("return_id", string5);
                                        int i3 = i;
                                        contentValues2.put("product_id", itemPlan3.getOriginal().getProductId());
                                        String str29 = str14;
                                        contentValues2.put(str29, Double.valueOf(itemPlan3.getQuantity().doubleValue()));
                                        contentValues2.put("unit_price_usd_cents", Long.valueOf(itemPlan3.getOriginal().getUnitPriceUsdCents()));
                                        contentValues2.put("line_total_usd_cents", Long.valueOf(itemPlan3.getReturnValueUsdCents()));
                                        contentValues2.put("original_sales_item_id", itemPlan3.getOriginal().getId());
                                        if (itemPlan3.getReturnedCogsUsdCents() == null) {
                                            contentValues2.putNull(str28);
                                        } else {
                                            contentValues2.put(str28, itemPlan3.getReturnedCogsUsdCents());
                                        }
                                        Unit unit5 = Unit.INSTANCE;
                                        writableDatabase.insertOrThrow("return_items", null, contentValues2);
                                        if (!itemPlan3.getOriginal().getTracksInventory()) {
                                            str2 = str24;
                                            str3 = string5;
                                            r10 = writableDatabase;
                                            str4 = str26;
                                            str5 = str16;
                                            r11 = r18;
                                            str6 = strTimestamp2;
                                            str7 = str22;
                                            str8 = str29;
                                        } else if (itemPlan3.getCostAllocations().isEmpty()) {
                                            try {
                                                String str30 = str24;
                                                String str31 = strTimestamp2;
                                                String str32 = str22;
                                                str3 = string5;
                                                ?? r49 = r18;
                                                str2 = str30;
                                                r19 = writableDatabase;
                                                str4 = operationId;
                                                str5 = str16;
                                                try {
                                                    addReturnedStockMovement(writableDatabase, actorUserId, string5, string6, operationId, i3, itemPlan3.getOriginal().getProductId(), originalInvoiceLoadOriginalInvoice.getWarehouseId(), itemPlan3.getQuantity(), null, str31);
                                                    str6 = str31;
                                                    r10 = r19;
                                                    str7 = str32;
                                                    str8 = str29;
                                                    r11 = r49;
                                                } catch (Throwable th20) {
                                                    th = th20;
                                                    th = th;
                                                    r17 = r19;
                                                    r17.endTransaction();
                                                    Result.Companion companion5 = Result.INSTANCE;
                                                    SyncRelaySession.INSTANCE.flushPending();
                                                    Result.m381constructorimpl(Unit.INSTANCE);
                                                    throw th;
                                                }
                                            } catch (Throwable th21) {
                                                th = th21;
                                                r19 = writableDatabase;
                                            }
                                        } else {
                                            str2 = str24;
                                            String str33 = strTimestamp2;
                                            String str34 = str22;
                                            str3 = string5;
                                            ?? r110 = writableDatabase;
                                            str4 = str26;
                                            str5 = str16;
                                            ?? r410 = r18;
                                            String str35 = str29;
                                            String str36 = string6;
                                            try {
                                                int i4 = 0;
                                                ?? r111 = r110;
                                                for (Object obj2 : itemPlan3.getCostAllocations()) {
                                                    int i5 = i4 + 1;
                                                    if (i4 < 0) {
                                                        CollectionsKt.throwIndexOverflow();
                                                    }
                                                    CostAllocationPlan costAllocationPlan = (CostAllocationPlan) obj2;
                                                    int i6 = i3;
                                                    String strAddReturnedStockMovement = addReturnedStockMovement(r111, actorUserId, str3, str36, operationId, (i6 * 1000) + i4, itemPlan3.getOriginal().getProductId(), originalInvoiceLoadOriginalInvoice.getWarehouseId(), costAllocationPlan.getQuantity(), costAllocationPlan.getUnitCostUsdCents(), str33);
                                                    if (costAllocationPlan.getSourceAllocationId() != null) {
                                                        ?? contentValues3 = new ContentValues();
                                                        str11 = str34;
                                                        contentValues3.put(str11, UUID.randomUUID().toString());
                                                        str10 = str36;
                                                        contentValues3.put("return_item_id", str10);
                                                        contentValues3.put("source_sale_allocation_id", costAllocationPlan.getSourceAllocationId());
                                                        contentValues3.put("inventory_movement_id", strAddReturnedStockMovement);
                                                        str12 = str35;
                                                        contentValues3.put(str12, Double.valueOf(costAllocationPlan.getQuantity().doubleValue()));
                                                        if (costAllocationPlan.getUnitCostUsdCents() == null) {
                                                            contentValues3.putNull("unit_cost_usd_cents");
                                                        } else {
                                                            contentValues3.put("unit_cost_usd_cents", costAllocationPlan.getUnitCostUsdCents());
                                                        }
                                                        if (costAllocationPlan.getCostUsdCents() == null) {
                                                            contentValues3.putNull("cost_usd_cents");
                                                        } else {
                                                            contentValues3.put("cost_usd_cents", costAllocationPlan.getCostUsdCents());
                                                        }
                                                        str9 = str33;
                                                        r12 = r410;
                                                        contentValues3.put(r12, str9);
                                                        Unit unit6 = Unit.INSTANCE;
                                                        r9 = r111;
                                                        try {
                                                            r9.insertOrThrow("return_cost_allocations", null, contentValues3);
                                                        } catch (Throwable th22) {
                                                            th5 = th22;
                                                            th = th5;
                                                            r17 = r9;
                                                            r17.endTransaction();
                                                            Result.Companion companion6 = Result.INSTANCE;
                                                            SyncRelaySession.INSTANCE.flushPending();
                                                            Result.m381constructorimpl(Unit.INSTANCE);
                                                            throw th;
                                                        }
                                                    } else {
                                                        str9 = str33;
                                                        r9 = r111;
                                                        str10 = str36;
                                                        str11 = str34;
                                                        str12 = str35;
                                                        r12 = r410;
                                                    }
                                                    str35 = str12;
                                                    str36 = str10;
                                                    r111 = r9;
                                                    r410 = r12;
                                                    str33 = str9;
                                                    str34 = str11;
                                                    i4 = i5;
                                                    i3 = i6;
                                                }
                                                str6 = str33;
                                                r10 = r111;
                                                str7 = str34;
                                                str8 = str35;
                                                r11 = r410;
                                                Unit unit7 = Unit.INSTANCE;
                                            } catch (Throwable th23) {
                                                th5 = th23;
                                                r9 = r110;
                                                th = th5;
                                                r17 = r9;
                                                r17.endTransaction();
                                                Result.Companion companion7 = Result.INSTANCE;
                                                SyncRelaySession.INSTANCE.flushPending();
                                                Result.m381constructorimpl(Unit.INSTANCE);
                                                throw th;
                                            }
                                        }
                                        string5 = str3;
                                        str14 = str8;
                                        r18 = r11;
                                        strTimestamp2 = str6;
                                        str22 = str7;
                                        str26 = str4;
                                        str25 = str25;
                                        i = i2;
                                        l3 = l3;
                                        str23 = str23;
                                        str28 = str28;
                                        str27 = str27;
                                        j5 = j5;
                                        str19 = str19;
                                        str24 = str2;
                                        str21 = str21;
                                        str16 = str5;
                                        writableDatabase = r10;
                                        str15 = str15;
                                    } catch (Throwable th24) {
                                        th5 = th24;
                                        r9 = writableDatabase;
                                    }
                                }
                                String str37 = str24;
                                String str38 = str23;
                                String str39 = str28;
                                String str40 = string5;
                                String str41 = str15;
                                r9 = writableDatabase;
                                String str42 = str16;
                                ?? r14 = r18;
                                String str43 = str19;
                                Long l4 = l3;
                                String str44 = str21;
                                String str45 = str27;
                                long j6 = j5;
                                String str46 = strTimestamp2;
                                String str47 = str25;
                                String str48 = str22;
                                Object obj3 = null;
                                try {
                                    int i7 = 0;
                                    r9 = r9;
                                    for (Object obj4 : refundAllocations) {
                                        try {
                                            int i8 = i7 + 1;
                                            if (i7 < 0) {
                                                CollectionsKt.throwIndexOverflow();
                                            }
                                            r54 = r9;
                                            ?? r15 = r14;
                                            String str49 = str46;
                                            try {
                                                postRefundAllocation(r9, actorUserId, str40, operationId, i7, originalInvoiceLoadOriginalInvoice.getRateMilli(), operatingDate == null ? returnDate : operatingDate, str46, (SalesRefundAllocationInput) obj4);
                                                r14 = r15;
                                                i7 = i8;
                                                r9 = r54;
                                                str46 = str49;
                                                obj3 = null;
                                            } catch (Throwable th25) {
                                                th = th25;
                                                th = th;
                                                r17 = r54;
                                                r17.endTransaction();
                                                Result.Companion companion8 = Result.INSTANCE;
                                                SyncRelaySession.INSTANCE.flushPending();
                                                Result.m381constructorimpl(Unit.INSTANCE);
                                                throw th;
                                            }
                                        } catch (Throwable th26) {
                                            th = th26;
                                            r54 = r9;
                                        }
                                    }
                                    ?? r55 = r9;
                                    ?? r16 = r14;
                                    String str50 = str46;
                                    try {
                                        ?? contentValues4 = new ContentValues();
                                        contentValues4.put(str48, UUID.randomUUID().toString());
                                        contentValues4.put("user_id", str47);
                                        contentValues4.put("action", "SALE_RETURN_POSTED");
                                        contentValues4.put("entity_type", "RETURN");
                                        contentValues4.put("entity_id", str40);
                                        contentValues4.put("after_json", "{\"original_invoice_id\":\"" + originalInvoiceId + "\",\"total_usd_cents\":" + j4 + ",\"credit_reduction_usd_cents\":" + jMin + ",\"cash_refund_usd_cents\":" + j6 + "}");
                                        contentValues4.put(r16, str50);
                                        Unit unit8 = Unit.INSTANCE;
                                        try {
                                            r55.insertOrThrow("audit_log", null, contentValues4);
                                            ?? contentValues5 = new ContentValues();
                                            SyncCredentialVault syncCredentialVault = null;
                                            contentValues5.put("operation_id", operationId);
                                            contentValues5.put("operation_type", str43);
                                            contentValues5.put("payload_hash", str44);
                                            contentValues5.put(NotificationCompat.CATEGORY_STATUS, "COMPLETED");
                                            contentValues5.put("result_reference", str40);
                                            contentValues5.put(r16, str50);
                                            contentValues5.put("completed_at", str50);
                                            Unit unit9 = Unit.INSTANCE;
                                            r55.insertOrThrow("operation_registry", null, contentValues5);
                                            if (syncOrigin) {
                                                r13 = this;
                                                j = jMin;
                                                l2 = l4;
                                                str = str38;
                                                j2 = j6;
                                            } else {
                                                ?? r112 = this;
                                                try {
                                                    Context context2 = r112.context;
                                                    Intrinsics.checkNotNullExpressionValue(context2, "context");
                                                    ?? syncProtocolRepository = new SyncProtocolRepository(context2, syncCredentialVault, 2, false ? 1 : 0);
                                                    str = str38;
                                                    String string7 = new JSONObject().put(str37, originalInvoiceId).put("return_id", str40).put("return_number", str).put(str41, returnDate).put(str45, StringsKt.trim((CharSequence) reason).toString()).put("total_usd_cents", j4).put("credit_reduction_usd_cents", jMin).put("cash_refund_usd_cents", j6).put(str39, l4).toString();
                                                    Intrinsics.checkNotNullExpressionValue(string7, str42);
                                                    l2 = l4;
                                                    j2 = j6;
                                                    j = jMin;
                                                    syncProtocolRepository.enqueueLocalOperation(r55, actorUserId, operationId, "POST_SALES_RETURN", "SALES_RETURN", str40, "CREATE", string7);
                                                    r13 = r112;
                                                } catch (Throwable th27) {
                                                    th = th27;
                                                    writableDatabase = r55;
                                                    th = th;
                                                    r17 = writableDatabase;
                                                    r17.endTransaction();
                                                    Result.Companion companion9 = Result.INSTANCE;
                                                    SyncRelaySession.INSTANCE.flushPending();
                                                    Result.m381constructorimpl(Unit.INSTANCE);
                                                    throw th;
                                                }
                                            }
                                            r5.setTransactionSuccessful();
                                            SalesReturnResult salesReturnResult2 = new SalesReturnResult(str40, str, j4, j, j2, l2, false);
                                            r55.endTransaction();
                                            try {
                                                Result.Companion companion10 = Result.INSTANCE;
                                                SyncRelaySession.INSTANCE.flushPending();
                                                Result.m381constructorimpl(Unit.INSTANCE);
                                            } catch (Throwable th28) {
                                                Result.Companion companion11 = Result.INSTANCE;
                                                Result.m381constructorimpl(ResultKt.createFailure(th28));
                                            }
                                            return salesReturnResult2;
                                        } catch (Throwable th29) {
                                            th = th29;
                                        }
                                    } catch (Throwable th30) {
                                        th = th30;
                                        writableDatabase = r55;
                                    }
                                } catch (Throwable th31) {
                                    th = th31;
                                    writableDatabase = r9;
                                }
                            } catch (Throwable th32) {
                                writableDatabase = writableDatabase;
                                try {
                                    throw th32;
                                } catch (Throwable th33) {
                                    CloseableKt.closeFinally(cursorRawQuery6, th32);
                                    throw th33;
                                }
                            }
                        } catch (Throwable th34) {
                            th3 = th34;
                        }
                    } catch (Throwable th35) {
                        th = th35;
                    }
                    th = th;
                    r17 = writableDatabase;
                } catch (Throwable th36) {
                    th2 = th36;
                }
            } catch (Throwable th37) {
                th = th37;
                th = th;
                r17 = writableDatabase;
                r17.endTransaction();
                Result.Companion companion12 = Result.INSTANCE;
                SyncRelaySession.INSTANCE.flushPending();
                Result.m381constructorimpl(Unit.INSTANCE);
                throw th;
            }
        } catch (Throwable th38) {
            th = th38;
            writableDatabase = writableDatabase;
        }
        r17.endTransaction();
        try {
            Result.Companion companion13 = Result.INSTANCE;
            SyncRelaySession.INSTANCE.flushPending();
            Result.m381constructorimpl(Unit.INSTANCE);
            throw th;
        } catch (Throwable th39) {
            Result.Companion companion14 = Result.INSTANCE;
            Result.m381constructorimpl(ResultKt.createFailure(th39));
            throw th;
        }
    }

    private final OriginalInvoice loadOriginalInvoice(SQLiteDatabase db, String id) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT id,invoice_number,sale_type,customer_id,warehouse_id,status,total_usd_cents,\n                      subtotal_usd_cents,discount_usd_cents,exchange_rate_syp_per_usd_milli\n               FROM sales_invoices WHERE id=?", new String[]{id});
        try {
            Cursor cursor = cursorRawQuery;
            if (cursor.moveToFirst()) {
                String string = cursor.getString(0);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String string2 = cursor.getString(1);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                String string3 = cursor.getString(2);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                String string4 = cursor.isNull(3) ? null : cursor.getString(3);
                String string5 = cursor.getString(4);
                Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                String string6 = cursor.getString(5);
                Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                OriginalInvoice originalInvoice = new OriginalInvoice(string, string2, string3, string4, string5, string6, cursor.getLong(6), cursor.getLong(7), cursor.getLong(8), cursor.getLong(9));
                CloseableKt.closeFinally(cursorRawQuery, null);
                return originalInvoice;
            }
            throw new IllegalArgumentException("\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u0623\u0635\u0644\u064a\u0629 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629".toString());
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final List<OriginalItem> loadOriginalItems(SQLiteDatabase db, OriginalInvoice invoice) throws IOException {
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = db.rawQuery("SELECT si.id,si.product_id,si.quantity,si.unit_price_usd_cents,si.line_total_usd_cents,\n                      p.track_inventory,u.decimal_places\n               FROM sales_items si JOIN products p ON p.id=si.product_id\n               JOIN units u ON u.id=p.unit_id\n               WHERE si.sales_invoice_id=? ORDER BY si.rowid", new String[]{invoice.getId()});
        try {
            Cursor cursor = cursorRawQuery;
            while (cursor.moveToNext()) {
                String string = cursor.getString(0);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String string2 = cursor.getString(1);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                BigDecimal bigDecimalValueOf = BigDecimal.valueOf(cursor.getDouble(2));
                Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "valueOf(...)");
                arrayList.add(new OriginalItem(string, string2, bigDecimalValueOf, cursor.getLong(3), cursor.getLong(4), cursor.getInt(5) == 1, cursor.getInt(6)));
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (arrayList.isEmpty()) {
                throw new IllegalArgumentException("\u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0623\u0635\u0644\u064a\u0629 \u0644\u0627 \u062a\u062d\u062a\u0648\u064a \u0628\u0646\u0648\u062f\u0627\u064b".toString());
            }
            Iterator it = arrayList.iterator();
            long jM = 0;
            while (it.hasNext()) {
                jM = MainActivity$$ExternalSyntheticBackport0.m(jM, ((OriginalItem) it.next()).getLineTotalUsdCents());
            }
            if (jM == invoice.getSubtotalUsdCents()) {
                return arrayList;
            }
            throw new IllegalArgumentException("\u0625\u062c\u0645\u0627\u0644\u064a \u0628\u0646\u0648\u062f \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u0631\u0623\u0633 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629\u061b \u0623\u0648\u0642\u0641 \u0627\u0644\u0645\u0631\u062a\u062c\u0639 \u0644\u0644\u0645\u0631\u0627\u062c\u0639\u0629".toString());
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final ItemPlan buildItemPlan(SQLiteDatabase db, OriginalItem original, BigDecimal quantity, OriginalInvoice invoice) throws IOException {
        Long l;
        long jLongValueExact;
        if (quantity.signum() <= 0) {
            throw new IllegalArgumentException("\u0643\u0645\u064a\u0629 \u0627\u0644\u0645\u0631\u062a\u062c\u0639 \u064a\u062c\u0628 \u0623\u0646 \u062a\u0643\u0648\u0646 \u0645\u0648\u062c\u0628\u0629".toString());
        }
        if (RangesKt.coerceAtLeast(MainActivity$$ExternalSyntheticBackport0.m(quantity).scale(), 0) > original.getDecimalPlaces()) {
            throw new IllegalArgumentException("\u062f\u0642\u0629 \u0643\u0645\u064a\u0629 \u0627\u0644\u0645\u0631\u062a\u062c\u0639 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
        }
        Cursor cursorRawQuery = db.rawQuery("SELECT COALESCE(SUM(ri.quantity),0),COALESCE(SUM(ri.line_total_usd_cents),0)\n               FROM return_items ri JOIN returns r ON r.id=ri.return_id\n               WHERE ri.original_sales_item_id=? AND r.status='POSTED'", new String[]{original.getId()});
        try {
            Cursor cursor = cursorRawQuery;
            cursor.moveToFirst();
            Pair pair = TuplesKt.to(BigDecimal.valueOf(cursor.getDouble(0)), Long.valueOf(cursor.getLong(1)));
            CloseableKt.closeFinally(cursorRawQuery, null);
            BigDecimal bigDecimalSubtract = original.getQuantity().subtract((BigDecimal) pair.getFirst());
            if (bigDecimalSubtract.signum() <= 0 || quantity.compareTo(bigDecimalSubtract) > 0) {
                throw new IllegalArgumentException("\u0627\u0644\u0643\u0645\u064a\u0629 \u0627\u0644\u0645\u0637\u0644\u0648\u0628\u0629 \u062a\u062a\u062c\u0627\u0648\u0632 \u0627\u0644\u0643\u0645\u064a\u0629 \u0627\u0644\u0645\u062a\u0628\u0642\u064a\u0629 \u0627\u0644\u0642\u0627\u0628\u0644\u0629 \u0644\u0644\u0625\u0631\u062c\u0627\u0639 \u0644\u0647\u0630\u0627 \u0627\u0644\u0635\u0646\u0641".toString());
            }
            ArrayList arrayList = new ArrayList();
            Cursor cursorRawQuery2 = db.rawQuery("SELECT id,line_total_usd_cents FROM sales_items WHERE sales_invoice_id=? ORDER BY rowid", new String[]{invoice.getId()});
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
                int i = 0;
                long j2 = 0;
                long jM2 = 0;
                for (Object obj : arrayList2) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    Pair pair2 = (Pair) obj;
                    if (invoice.getDiscountUsdCents() == j || jM == j) {
                        jLongValueExact = 0;
                    } else if (i == CollectionsKt.getLastIndex(arrayList)) {
                        jLongValueExact = invoice.getDiscountUsdCents() - jM2;
                    } else {
                        jLongValueExact = BigDecimal.valueOf(invoice.getDiscountUsdCents()).multiply(BigDecimal.valueOf(((Number) pair2.getSecond()).longValue())).divide(BigDecimal.valueOf(jM), 0, RoundingMode.DOWN).longValueExact();
                        jM2 = MainActivity$$ExternalSyntheticBackport0.m(jM2, jLongValueExact);
                    }
                    if (Intrinsics.areEqual(pair2.getFirst(), original.getId())) {
                        j2 = jLongValueExact;
                    }
                    i = i2;
                    j = 0;
                }
                if (j2 > original.getLineTotalUsdCents()) {
                    throw new IllegalArgumentException("\u062e\u0635\u0645 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u063a\u064a\u0631 \u0642\u0627\u0628\u0644 \u0644\u0644\u062a\u0648\u0632\u064a\u0639 \u0628\u0623\u0645\u0627\u0646".toString());
                }
                long jM3 = DailyBalanceRepository$$ExternalSyntheticBackport0.m(BigDecimal.valueOf(original.getLineTotalUsdCents() - j2).multiply(((BigDecimal) pair.getFirst()).add(quantity)).divide(original.getQuantity(), 0, RoundingMode.HALF_UP).longValueExact(), ((Number) pair.getSecond()).longValue());
                if (jM3 < 0) {
                    throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062d\u0633\u0627\u0628 \u0642\u064a\u0645\u0629 \u0627\u0644\u0645\u0631\u062a\u062c\u0639".toString());
                }
                if (!original.getTracksInventory()) {
                    return new ItemPlan(original, quantity, jM3, 0L, CollectionsKt.emptyList());
                }
                List<CostAllocationPlan> listPlanReturnedCosts = planReturnedCosts(db, original.getId(), quantity);
                if (listPlanReturnedCosts.isEmpty()) {
                    l = null;
                } else {
                    List<CostAllocationPlan> list = listPlanReturnedCosts;
                    if (!(list instanceof Collection) || !list.isEmpty()) {
                        Iterator<T> it2 = list.iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                if (((CostAllocationPlan) it2.next()).getCostUsdCents() == null) {
                                    l = null;
                                }
                            }
                        }
                    }
                    long jValueOf = 0L;
                    for (CostAllocationPlan costAllocationPlan : listPlanReturnedCosts) {
                        long jLongValue = jValueOf.longValue();
                        Long costUsdCents = costAllocationPlan.getCostUsdCents();
                        Intrinsics.checkNotNull(costUsdCents);
                        jValueOf = Long.valueOf(MainActivity$$ExternalSyntheticBackport0.m(jLongValue, costUsdCents.longValue()));
                    }
                    l = jValueOf;
                }
                return new ItemPlan(original, quantity, jM3, l, listPlanReturnedCosts);
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

    /* JADX INFO: compiled from: SalesReturnRepository.kt */
    @Metadata(d1 = {"\u0000/\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\u008a\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0007\u00a2\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0005H\u00c6\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0012J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0012J\t\u0010\u001c\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0007H\u00c6\u0003JN\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u0007H\u00c6\u0001\u00a2\u0006\u0002\u0010\u001fJ\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010#\u001a\u00020$H\u00d6\u0001J\t\u0010%\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0011\u0010\n\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006&"}, d2 = {"com/hazem/dailystore/data/SalesReturnRepository$planReturnedCosts$Source", "", "id", "", "quantity", "Ljava/math/BigDecimal;", "unitCost", "", "totalCost", "returnedQuantity", "returnedCost", "<init>", "(Ljava/lang/String;Ljava/math/BigDecimal;Ljava/lang/Long;Ljava/lang/Long;Ljava/math/BigDecimal;J)V", "getId", "()Ljava/lang/String;", "getQuantity", "()Ljava/math/BigDecimal;", "getUnitCost", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getTotalCost", "getReturnedQuantity", "getReturnedCost", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Ljava/math/BigDecimal;Ljava/lang/Long;Ljava/lang/Long;Ljava/math/BigDecimal;J)Lcom/hazem/dailystore/data/SalesReturnRepository$planReturnedCosts$Source;", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class Source {
        private final String id;
        private final BigDecimal quantity;
        private final long returnedCost;
        private final BigDecimal returnedQuantity;
        private final Long totalCost;
        private final Long unitCost;

        public static /* synthetic */ Source copy$default(Source source, String str, BigDecimal bigDecimal, Long l, Long l2, BigDecimal bigDecimal2, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                str = source.id;
            }
            if ((i & 2) != 0) {
                bigDecimal = source.quantity;
            }
            BigDecimal bigDecimal3 = bigDecimal;
            if ((i & 4) != 0) {
                l = source.unitCost;
            }
            Long l3 = l;
            if ((i & 8) != 0) {
                l2 = source.totalCost;
            }
            Long l4 = l2;
            if ((i & 16) != 0) {
                bigDecimal2 = source.returnedQuantity;
            }
            BigDecimal bigDecimal4 = bigDecimal2;
            if ((i & 32) != 0) {
                j = source.returnedCost;
            }
            return source.copy(str, bigDecimal3, l3, l4, bigDecimal4, j);
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

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Long getTotalCost() {
            return this.totalCost;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final BigDecimal getReturnedQuantity() {
            return this.returnedQuantity;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final long getReturnedCost() {
            return this.returnedCost;
        }

        public final Source copy(String id, BigDecimal quantity, Long unitCost, Long totalCost, BigDecimal returnedQuantity, long returnedCost) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(quantity, "quantity");
            Intrinsics.checkNotNullParameter(returnedQuantity, "returnedQuantity");
            return new Source(id, quantity, unitCost, totalCost, returnedQuantity, returnedCost);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Source)) {
                return false;
            }
            Source source = (Source) other;
            return Intrinsics.areEqual(this.id, source.id) && Intrinsics.areEqual(this.quantity, source.quantity) && Intrinsics.areEqual(this.unitCost, source.unitCost) && Intrinsics.areEqual(this.totalCost, source.totalCost) && Intrinsics.areEqual(this.returnedQuantity, source.returnedQuantity) && this.returnedCost == source.returnedCost;
        }

        public int hashCode() {
            int iHashCode = ((this.id.hashCode() * 31) + this.quantity.hashCode()) * 31;
            Long l = this.unitCost;
            int iHashCode2 = (iHashCode + (l == null ? 0 : l.hashCode())) * 31;
            Long l2 = this.totalCost;
            return ((((iHashCode2 + (l2 != null ? l2.hashCode() : 0)) * 31) + this.returnedQuantity.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.returnedCost);
        }

        public String toString() {
            return "Source(id=" + this.id + ", quantity=" + this.quantity + ", unitCost=" + this.unitCost + ", totalCost=" + this.totalCost + ", returnedQuantity=" + this.returnedQuantity + ", returnedCost=" + this.returnedCost + ")";
        }

        public Source(String id, BigDecimal quantity, Long l, Long l2, BigDecimal returnedQuantity, long j) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(quantity, "quantity");
            Intrinsics.checkNotNullParameter(returnedQuantity, "returnedQuantity");
            this.id = id;
            this.quantity = quantity;
            this.unitCost = l;
            this.totalCost = l2;
            this.returnedQuantity = returnedQuantity;
            this.returnedCost = j;
        }

        public final String getId() {
            return this.id;
        }

        public final BigDecimal getQuantity() {
            return this.quantity;
        }

        public final Long getTotalCost() {
            return this.totalCost;
        }

        public final Long getUnitCost() {
            return this.unitCost;
        }

        public final long getReturnedCost() {
            return this.returnedCost;
        }

        public final BigDecimal getReturnedQuantity() {
            return this.returnedQuantity;
        }
    }

    private final List<CostAllocationPlan> planReturnedCosts(SQLiteDatabase db, String salesItemId, BigDecimal requested) throws IOException {
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = db.rawQuery("SELECT a.id,a.quantity,a.unit_cost_usd_cents,a.cost_usd_cents,\n                      COALESCE(SUM(r.quantity),0),COALESCE(SUM(r.cost_usd_cents),0)\n               FROM sales_item_cost_allocations a\n               LEFT JOIN return_cost_allocations r ON r.source_sale_allocation_id=a.id\n               WHERE a.sales_item_id=? GROUP BY a.id ORDER BY a.rowid", new String[]{salesItemId});
        try {
            Cursor cursor = cursorRawQuery;
            while (true) {
                if (!cursor.moveToNext()) {
                    break;
                }
                String string = cursor.getString(0);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                BigDecimal bigDecimalValueOf = BigDecimal.valueOf(cursor.getDouble(1));
                Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "valueOf(...)");
                Long lValueOf = cursor.isNull(2) ? null : Long.valueOf(cursor.getLong(2));
                Long lValueOf2 = cursor.isNull(3) ? null : Long.valueOf(cursor.getLong(3));
                BigDecimal bigDecimalValueOf2 = BigDecimal.valueOf(cursor.getDouble(4));
                Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf2, "valueOf(...)");
                arrayList.add(new Source(string, bigDecimalValueOf, lValueOf, lValueOf2, bigDecimalValueOf2, cursor.getLong(5)));
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (arrayList.isEmpty()) {
                return CollectionsKt.emptyList();
            }
            ArrayList<Source> arrayList2 = arrayList;
            BigDecimal bigDecimalAdd = BigDecimal.ZERO;
            for (Source source : arrayList2) {
                bigDecimalAdd = bigDecimalAdd.add(source.getQuantity().subtract(source.getReturnedQuantity()));
            }
            BigDecimal bigDecimalSubtract = requested;
            if (bigDecimalAdd.compareTo(bigDecimalSubtract) < 0) {
                throw new IllegalArgumentException("\u062a\u062e\u0635\u064a\u0635\u0627\u062a FIFO \u0627\u0644\u0623\u0635\u0644\u064a\u0629 \u0644\u0627 \u062a\u063a\u0637\u064a \u0643\u0645\u064a\u0629 \u0627\u0644\u0645\u0631\u062a\u062c\u0639\u061b \u0623\u0648\u0642\u0641 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0644\u0644\u0645\u0637\u0627\u0628\u0642\u0629".toString());
            }
            ArrayList arrayList3 = new ArrayList();
            for (Source source2 : arrayList2) {
                if (bigDecimalSubtract.signum() > 0) {
                    BigDecimal bigDecimalSubtract2 = source2.getQuantity().subtract(source2.getReturnedQuantity());
                    if (bigDecimalSubtract2.signum() > 0) {
                        BigDecimal bigDecimalMin = bigDecimalSubtract.min(bigDecimalSubtract2);
                        BigDecimal bigDecimalAdd2 = source2.getReturnedQuantity().add(bigDecimalMin);
                        Long totalCost = source2.getTotalCost();
                        Long lValueOf3 = totalCost != null ? Long.valueOf(DailyBalanceRepository$$ExternalSyntheticBackport0.m(BigDecimal.valueOf(totalCost.longValue()).multiply(bigDecimalAdd2).divide(source2.getQuantity(), 0, RoundingMode.HALF_UP).longValueExact(), source2.getReturnedCost())) : null;
                        String id = source2.getId();
                        Intrinsics.checkNotNull(bigDecimalMin);
                        arrayList3.add(new CostAllocationPlan(id, bigDecimalMin, source2.getUnitCost(), lValueOf3));
                        bigDecimalSubtract = bigDecimalSubtract.subtract(bigDecimalMin);
                    }
                }
            }
            if (bigDecimalSubtract.signum() == 0) {
                return arrayList3;
            }
            throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u062a\u0648\u0632\u064a\u0639 \u062a\u0643\u0644\u0641\u0629 \u0627\u0644\u0645\u0631\u062a\u062c\u0639 \u0639\u0644\u0649 \u062a\u062e\u0635\u064a\u0635\u0627\u062a FIFO \u0627\u0644\u0623\u0635\u0644\u064a\u0629".toString());
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final void validateRefundAllocations(SQLiteDatabase db, OriginalInvoice invoice, long refundUsd, List<SalesRefundAllocationInput> allocations) throws IOException {
        long jUsdCentsToSyp = CurrencyConverter.INSTANCE.usdCentsToSyp(refundUsd, invoice.getRateMilli());
        long jM = 0;
        if (refundUsd == 0) {
            if (!allocations.isEmpty()) {
                throw new IllegalArgumentException("\u0644\u0627 \u064a\u0648\u062c\u062f \u0645\u0628\u0644\u063a \u0646\u0642\u062f\u064a \u0644\u0631\u062f\u0647\u061b \u0623\u0632\u0644 \u062a\u0648\u0632\u064a\u0639\u0627\u062a \u0631\u062f \u0627\u0644\u0645\u0628\u0644\u063a".toString());
            }
            return;
        }
        if (allocations.isEmpty()) {
            throw new IllegalArgumentException("\u062d\u062f\u062f \u0627\u0644\u062d\u0633\u0627\u0628\u0627\u062a \u0627\u0644\u062a\u064a \u0633\u064a\u064f\u0631\u062f \u0645\u0646\u0647\u0627 \u0627\u0644\u0645\u0628\u0644\u063a \u0644\u0644\u0632\u0628\u0648\u0646".toString());
        }
        for (SalesRefundAllocationInput salesRefundAllocationInput : allocations) {
            Cursor cursorRawQuery = db.rawQuery("SELECT currency_code,opening_balance_minor,is_active FROM cash_accounts WHERE id=?", new String[]{salesRefundAllocationInput.getCashAccountId()});
            try {
                Cursor cursor = cursorRawQuery;
                if (!cursor.moveToFirst()) {
                    throw new IllegalArgumentException("\u062d\u0633\u0627\u0628 \u0631\u062f \u0627\u0644\u0645\u0628\u0644\u063a \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
                }
                if (cursor.getInt(2) != 1) {
                    throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0631\u062f \u0627\u0644\u0645\u0628\u0644\u063a \u0645\u0646 \u062d\u0633\u0627\u0628 \u0645\u0648\u0642\u0648\u0641".toString());
                }
                Pair pair = TuplesKt.to(cursor.getString(0), Long.valueOf(cursor.getLong(1)));
                CloseableKt.closeFinally(cursorRawQuery, null);
                if (!Intrinsics.areEqual(pair.getFirst(), "USD") && !Intrinsics.areEqual(pair.getFirst(), "SYP")) {
                    throw new IllegalArgumentException("\u0639\u0645\u0644\u0629 \u062d\u0633\u0627\u0628 \u0631\u062f \u0627\u0644\u0645\u0628\u0644\u063a \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u0629".toString());
                }
                Cursor cursorRawQuery2 = db.rawQuery("SELECT role_key FROM system_cash_account_roles WHERE account_id=?", new String[]{salesRefundAllocationInput.getCashAccountId()});
                try {
                    Cursor cursor2 = cursorRawQuery2;
                    String string = cursor2.moveToFirst() ? cursor2.getString(0) : null;
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    if (Intrinsics.areEqual(string, "SUPPLIER_ROLLOVER")) {
                        throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0627\u0633\u062a\u062e\u062f\u0627\u0645 \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0644\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0644\u0631\u062f \u0645\u0628\u0627\u0644\u063a \u0627\u0644\u0639\u0645\u0644\u0627\u0621".toString());
                    }
                    if (accountBalance(db, salesRefundAllocationInput.getCashAccountId(), ((Number) pair.getSecond()).longValue()) < salesRefundAllocationInput.getAmountMinor()) {
                        throw new IllegalArgumentException(("\u0627\u0644\u0631\u0635\u064a\u062f \u063a\u064a\u0631 \u0643\u0627\u0641\u064d \u0641\u064a \u062d\u0633\u0627\u0628 " + salesRefundAllocationInput.getCashAccountId() + " \u0644\u0631\u062f \u0627\u0644\u0645\u0628\u0644\u063a").toString());
                    }
                    jM = MainActivity$$ExternalSyntheticBackport0.m(jM, Intrinsics.areEqual(pair.getFirst(), "SYP") ? salesRefundAllocationInput.getAmountMinor() : CurrencyConverter.INSTANCE.usdCentsToSyp(salesRefundAllocationInput.getAmountMinor(), invoice.getRateMilli()));
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
        if (jM != jUsdCentsToSyp) {
            throw new IllegalArgumentException("\u0645\u062c\u0645\u0648\u0639 \u062a\u0648\u0632\u064a\u0639\u0627\u062a \u0631\u062f \u0627\u0644\u0645\u0628\u0644\u063a \u0644\u0627 \u064a\u0633\u0627\u0648\u064a \u0627\u0644\u0645\u0628\u0644\u063a \u0627\u0644\u0645\u0633\u062a\u062d\u0642 \u0648\u0641\u0642 \u0633\u0639\u0631 \u0635\u0631\u0641 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0623\u0635\u0644\u064a\u0629".toString());
        }
    }

    private final String addReturnedStockMovement(SQLiteDatabase db, String actor, String returnId, String returnItemId, String operationId, int movementIndex, String productId, String warehouseId, BigDecimal quantity, Long unitCost, String now) throws IOException {
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        String str = operationId + ":STOCK:" + returnItemId + ":" + movementIndex;
        db.execSQL("UPDATE inventory_balances SET quantity=quantity+?,updated_at=? WHERE product_id=? AND warehouse_id=?", new Object[]{Double.valueOf(quantity.doubleValue()), now, productId, warehouseId});
        Cursor cursorRawQuery = db.rawQuery("SELECT 1 FROM inventory_balances WHERE product_id=? AND warehouse_id=?", new String[]{productId, warehouseId});
        try {
            boolean zMoveToFirst = cursorRawQuery.moveToFirst();
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (!zMoveToFirst) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("product_id", productId);
                contentValues.put("warehouse_id", warehouseId);
                contentValues.put("quantity", Double.valueOf(quantity.doubleValue()));
                contentValues.put("updated_at", now);
                Unit unit = Unit.INSTANCE;
                db.insertOrThrow("inventory_balances", null, contentValues);
            }
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("id", string);
            contentValues2.put("product_id", productId);
            contentValues2.put("warehouse_id", warehouseId);
            contentValues2.put("movement_type", "SALE_RETURN");
            contentValues2.put("quantity_delta", Double.valueOf(quantity.doubleValue()));
            contentValues2.put("unit_cost_usd_cents", Long.valueOf(unitCost != null ? unitCost.longValue() : 0L));
            contentValues2.put("source_type", "SALE_RETURN");
            contentValues2.put("source_id", returnId);
            contentValues2.put("operation_id", str);
            contentValues2.put("note", "\u0625\u0631\u062c\u0627\u0639 \u0635\u0646\u0641 \u0645\u0646 \u0627\u0644\u0645\u0631\u062a\u062c\u0639 " + returnId);
            contentValues2.put("created_by", actor);
            contentValues2.put("created_at", now);
            Unit unit2 = Unit.INSTANCE;
            db.insertOrThrow("inventory_movements", null, contentValues2);
            FifoCosting.INSTANCE.addLayer(db, string, productId, warehouseId, quantity.doubleValue(), unitCost != null ? unitCost.longValue() : 0L, "SALE_RETURN", returnId, now, (512 & 512) != 0 ? null : null);
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

    private final void postRefundAllocation(SQLiteDatabase db, String actor, String returnId, String operationId, int index, long rate, String returnDate, String now, SalesRefundAllocationInput allocation) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT currency_code FROM cash_accounts WHERE id=? AND is_active=1", new String[]{allocation.getCashAccountId()});
        try {
            Cursor cursor = cursorRawQuery;
            if (!cursor.moveToFirst()) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            String string = cursor.getString(0);
            CloseableKt.closeFinally(cursorRawQuery, null);
            long amountMinor = Intrinsics.areEqual(string, "SYP") ? allocation.getAmountMinor() : CurrencyConverter.INSTANCE.usdCentsToSyp(allocation.getAmountMinor(), rate);
            String string2 = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
            String str = operationId + ":REFUND:" + index;
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", string2);
            contentValues.put("account_id", allocation.getCashAccountId());
            contentValues.put("direction", "OUT");
            contentValues.put("amount_minor", Long.valueOf(allocation.getAmountMinor()));
            contentValues.put("currency_code", string);
            contentValues.put("exchange_rate_syp_per_usd_milli", Long.valueOf(rate));
            contentValues.put("transaction_type", "SALE_RETURN_REFUND");
            contentValues.put("source_type", "RETURN");
            contentValues.put("source_id", returnId);
            contentValues.put("operation_id", str);
            contentValues.put("description", "\u0631\u062f \u0645\u0628\u0644\u063a \u0645\u0631\u062a\u062c\u0639 \u0645\u0628\u064a\u0639\u0627\u062a " + returnId);
            contentValues.put("created_by", actor);
            contentValues.put("operating_date", returnDate);
            contentValues.put("created_at", now);
            Unit unit = Unit.INSTANCE;
            long j = amountMinor;
            db.insertOrThrow("cash_transactions", null, contentValues);
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("id", UUID.randomUUID().toString());
            contentValues2.put("return_id", returnId);
            contentValues2.put("cash_account_id", allocation.getCashAccountId());
            contentValues2.put("amount_minor", Long.valueOf(allocation.getAmountMinor()));
            contentValues2.put("currency_code", string);
            contentValues2.put("company_fee_minor", (Long) 0L);
            contentValues2.put("exchange_rate_syp_per_usd_milli", Long.valueOf(rate));
            contentValues2.put("created_at", now);
            contentValues2.put("operation_id", str);
            contentValues2.put("cash_transaction_id", string2);
            contentValues2.put("refund_equivalent_syp_minor", Long.valueOf(j));
            Unit unit2 = Unit.INSTANCE;
            db.insertOrThrow("return_refund_allocations", null, contentValues2);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final long accountBalance(SQLiteDatabase db, String accountId, long opening) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT COALESCE(SUM(CASE WHEN direction='IN' THEN amount_minor\n                       WHEN direction='OUT' THEN -amount_minor ELSE 0 END),0)\n               FROM cash_transactions WHERE account_id=?", new String[]{accountId});
        try {
            Cursor cursor = cursorRawQuery;
            cursor.moveToFirst();
            long j = cursor.getLong(0);
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

    private final SalesReturnResult loadResult(SQLiteDatabase db, String id, boolean replayed) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT return_number,total_usd_cents,credit_reduction_usd_cents,\n                      cash_refund_usd_cents,returned_cogs_usd_cents\n               FROM returns WHERE id=? AND status='POSTED'", new String[]{id});
        try {
            Cursor cursor = cursorRawQuery;
            if (!cursor.moveToFirst()) {
                throw new IllegalArgumentException("\u0627\u0644\u0645\u0631\u062a\u062c\u0639 \u0627\u0644\u0633\u0627\u0628\u0642 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
            }
            String string = cursor.getString(0);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            SalesReturnResult salesReturnResult = new SalesReturnResult(id, string, cursor.getLong(1), cursor.getLong(2), cursor.getLong(3), cursor.isNull(4) ? null : Long.valueOf(cursor.getLong(4)), replayed);
            CloseableKt.closeFinally(cursorRawQuery, null);
            return salesReturnResult;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final String nextReturnNumber(SQLiteDatabase db, String date) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT COUNT(*) FROM returns WHERE return_date=?", new String[]{date});
        try {
            Cursor cursor = cursorRawQuery;
            cursor.moveToFirst();
            int i = cursor.getInt(0) + 1;
            CloseableKt.closeFinally(cursorRawQuery, null);
            return "SR-" + StringsKt.replace$default(date, "-", "", false, 4, (Object) null) + "-" + StringsKt.padStart(String.valueOf(i), 4, '0');
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
            if (!cursor.moveToFirst()) {
                throw new IllegalArgumentException("\u0627\u0644\u0635\u0646\u0641 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
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

    private final String hash(String value) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = value.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrDigest = messageDigest.digest(bytes);
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
        return ArraysKt.joinToString$default(bArrDigest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.hazem.dailystore.data.SalesReturnRepository$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SalesReturnRepository.hash$lambda$110(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence hash$lambda$110(byte b) {
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
