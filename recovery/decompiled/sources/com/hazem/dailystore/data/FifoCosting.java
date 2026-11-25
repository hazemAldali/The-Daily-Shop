package com.hazem.dailystore.data;

import android.content.ContentValues;
import android.database.Cursor;
import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import net.zetetic.database.sqlcipher.SQLiteDatabase;

/* JADX INFO: compiled from: FifoCosting.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u00c0\u0002\u0018\u00002\u00020\u0001:\u0001\u001cB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003Jb\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0013\u001a\u00020\n2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\nJU\u0010\u0015\u001a\u0004\u0018\u00010\u00102\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b2\u0006\u0010\u0016\u001a\u00020\n2\b\u0010\u0017\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\n2\b\b\u0002\u0010\u0019\u001a\u00020\u001a\u00a2\u0006\u0002\u0010\u001b\u00a8\u0006\u001d"}, d2 = {"Lcom/hazem/dailystore/data/FifoCosting;", "", "<init>", "()V", "addLayer", "", "db", "Lnet/zetetic/database/sqlcipher/SQLiteDatabase;", "Lcom/hazem/dailystore/data/AppSqliteDatabase;", "movementId", "", "productId", "warehouseId", "quantity", "", "unitCostUsdCents", "", "sourceType", "sourceId", "receivedAt", "sourceItemId", "consumeForSale", "invoiceId", "salesItemId", "createdAt", "recordSaleAllocation", "", "(Lnet/zetetic/database/sqlcipher/SQLiteDatabase;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DLjava/lang/String;Z)Ljava/lang/Long;", "Layer", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class FifoCosting {
    public static final FifoCosting INSTANCE = new FifoCosting();

    /* JADX INFO: compiled from: FifoCosting.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0005H\u00c6\u0003J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000fJ.\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001c"}, d2 = {"Lcom/hazem/dailystore/data/FifoCosting$Layer;", "", "id", "", "remaining", "Ljava/math/BigDecimal;", "unitCost", "", "<init>", "(Ljava/lang/String;Ljava/math/BigDecimal;Ljava/lang/Long;)V", "getId", "()Ljava/lang/String;", "getRemaining", "()Ljava/math/BigDecimal;", "getUnitCost", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/math/BigDecimal;Ljava/lang/Long;)Lcom/hazem/dailystore/data/FifoCosting$Layer;", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class Layer {
        private final String id;
        private final BigDecimal remaining;
        private final Long unitCost;

        public static /* synthetic */ Layer copy$default(Layer layer, String str, BigDecimal bigDecimal, Long l, int i, Object obj) {
            if ((i & 1) != 0) {
                str = layer.id;
            }
            if ((i & 2) != 0) {
                bigDecimal = layer.remaining;
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
        public final BigDecimal getRemaining() {
            return this.remaining;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Long getUnitCost() {
            return this.unitCost;
        }

        public final Layer copy(String id, BigDecimal remaining, Long unitCost) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(remaining, "remaining");
            return new Layer(id, remaining, unitCost);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Layer)) {
                return false;
            }
            Layer layer = (Layer) other;
            return Intrinsics.areEqual(this.id, layer.id) && Intrinsics.areEqual(this.remaining, layer.remaining) && Intrinsics.areEqual(this.unitCost, layer.unitCost);
        }

        public int hashCode() {
            int iHashCode = ((this.id.hashCode() * 31) + this.remaining.hashCode()) * 31;
            Long l = this.unitCost;
            return iHashCode + (l == null ? 0 : l.hashCode());
        }

        public String toString() {
            return "Layer(id=" + this.id + ", remaining=" + this.remaining + ", unitCost=" + this.unitCost + ")";
        }

        public Layer(String id, BigDecimal remaining, Long l) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(remaining, "remaining");
            this.id = id;
            this.remaining = remaining;
            this.unitCost = l;
        }

        public final String getId() {
            return this.id;
        }

        public final BigDecimal getRemaining() {
            return this.remaining;
        }

        public final Long getUnitCost() {
            return this.unitCost;
        }
    }

    private FifoCosting() {
    }

    public final void addLayer(SQLiteDatabase db, String movementId, String productId, String warehouseId, double quantity, long unitCostUsdCents, String sourceType, String sourceId, String receivedAt, String sourceItemId) throws IOException {
        Long lValueOf;
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(movementId, "movementId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(warehouseId, "warehouseId");
        Intrinsics.checkNotNullParameter(receivedAt, "receivedAt");
        if (quantity <= 0.0d || Math.abs(quantity) > Double.MAX_VALUE) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!Intrinsics.areEqual(sourceType, "PURCHASE_INVOICE")) {
            Cursor cursorRawQuery = db.rawQuery("SELECT rate_syp_per_usd_milli FROM exchange_rates ORDER BY effective_at DESC,created_at DESC LIMIT 1", (String[]) null);
            try {
                Cursor cursor = cursorRawQuery;
                lValueOf = cursor.moveToFirst() ? Long.valueOf(cursor.getLong(0)) : null;
                CloseableKt.closeFinally(cursorRawQuery, null);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(cursorRawQuery, th);
                    throw th2;
                }
            }
        } else if (sourceId != null) {
            Cursor cursorRawQuery2 = db.rawQuery("SELECT exchange_rate_syp_per_usd_milli FROM purchase_invoices WHERE id=?", new String[]{sourceId});
            try {
                Cursor cursor2 = cursorRawQuery2;
                lValueOf = cursor2.moveToFirst() ? Long.valueOf(cursor2.getLong(0)) : null;
                CloseableKt.closeFinally(cursorRawQuery2, null);
            } catch (Throwable th3) {
                try {
                    throw th3;
                } catch (Throwable th4) {
                    CloseableKt.closeFinally(cursorRawQuery2, th3);
                    throw th4;
                }
            }
        } else {
            lValueOf = null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", UUID.randomUUID().toString());
        contentValues.put("product_id", productId);
        contentValues.put("warehouse_id", warehouseId);
        contentValues.put("source_movement_id", movementId);
        contentValues.put("source_type", sourceType);
        contentValues.put("source_id", sourceId);
        if (sourceItemId == null) {
            contentValues.putNull("source_item_id");
        } else {
            contentValues.put("source_item_id", sourceItemId);
        }
        contentValues.put("received_quantity", Double.valueOf(quantity));
        contentValues.put("remaining_quantity", Double.valueOf(quantity));
        if (unitCostUsdCents > 0) {
            contentValues.put("unit_cost_usd_cents", Long.valueOf(unitCostUsdCents));
        } else {
            contentValues.putNull("unit_cost_usd_cents");
        }
        if (lValueOf != null && lValueOf.longValue() > 0) {
            contentValues.put("purchase_exchange_rate_syp_per_usd_milli", lValueOf);
        } else {
            contentValues.putNull("purchase_exchange_rate_syp_per_usd_milli");
        }
        contentValues.put("received_at", receivedAt);
        db.insertOrThrow("inventory_cost_layers", null, contentValues);
    }

    /* JADX WARN: Code duplicated, block: B:49:0x0147 A[LOOP:3: B:47:0x0141->B:49:0x0147, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:75:0x0213  */
    /* JADX WARN: Code duplicated, block: B:77:0x022e  */
    /* JADX WARN: Code duplicated, block: B:78:0x0232  */
    /* JADX WARN: Code duplicated, block: B:81:0x025f  */
    /* JADX WARN: Code duplicated, block: B:82:0x0263  */
    /* JADX WARN: Code duplicated, block: B:85:0x0272  */
    /* JADX WARN: Code duplicated, block: B:86:0x0276  */
    /* JADX WARN: Code duplicated, block: B:88:0x0285  */
    public final Long consumeForSale(SQLiteDatabase db, String invoiceId, String salesItemId, String productId, String warehouseId, double quantity, String createdAt, boolean recordSaleAllocation) throws IOException {
        int i;
        BigDecimal bigDecimalAdd;
        boolean z;
        Long lValueOf;
        Object obj;
        ContentValues contentValues;
        BigDecimal scale;
        Iterator it;
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(invoiceId, "invoiceId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(warehouseId, "warehouseId");
        Intrinsics.checkNotNullParameter(createdAt, "createdAt");
        if (quantity <= 0.0d || Math.abs(quantity) > Double.MAX_VALUE) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(quantity);
        ArrayList<Layer> arrayList = new ArrayList();
        Cursor cursorRawQuery = db.rawQuery("SELECT id,remaining_quantity,unit_cost_usd_cents\n               FROM inventory_cost_layers\n               WHERE product_id=? AND warehouse_id=? AND remaining_quantity>0\n               ORDER BY received_at ASC,rowid ASC", new String[]{productId, warehouseId});
        try {
            Cursor cursor = cursorRawQuery;
            while (true) {
                i = 1;
                Long lValueOf2 = null;
                if (!cursor.moveToNext()) {
                    break;
                }
                String string = cursor.getString(0);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                BigDecimal bigDecimalValueOf2 = BigDecimal.valueOf(cursor.getDouble(1));
                Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf2, "valueOf(...)");
                if (!cursor.isNull(2)) {
                    lValueOf2 = Long.valueOf(cursor.getLong(2));
                }
                arrayList.add(new Layer(string, bigDecimalValueOf2, lValueOf2));
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            BigDecimal bigDecimalAdd2 = BigDecimal.ZERO;
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                bigDecimalAdd2 = bigDecimalAdd2.add(((Layer) it2.next()).getRemaining());
            }
            if (bigDecimalAdd2.compareTo(bigDecimalValueOf) < 0) {
                throw new IllegalArgumentException("\u0637\u0628\u0642\u0627\u062a \u062a\u0643\u0644\u0641\u0629 FIFO \u0644\u0627 \u062a\u063a\u0637\u064a \u0627\u0644\u0643\u0645\u064a\u0629 \u0627\u0644\u0645\u0637\u0644\u0648\u0628\u0629\u061b \u0623\u0648\u0642\u0641 \u0627\u0644\u0627\u0639\u062a\u0645\u0627\u062f \u062d\u062a\u0649 \u062a\u062a\u0645 \u0645\u0637\u0627\u0628\u0642\u0629 \u0627\u0644\u0645\u062e\u0632\u0648\u0646".toString());
            }
            ArrayList arrayList2 = new ArrayList();
            for (Layer layer : arrayList) {
                if (bigDecimalValueOf.signum() <= 0) {
                    break;
                }
                BigDecimal bigDecimalMin = bigDecimalValueOf.min(layer.getRemaining());
                Intrinsics.checkNotNull(bigDecimalMin);
                BigDecimal bigDecimalSubtract = layer.getRemaining().subtract(bigDecimalMin);
                Intrinsics.checkNotNullExpressionValue(bigDecimalSubtract, "subtract(...)");
                Long unitCost = layer.getUnitCost();
                arrayList2.add(new PlannedAllocation(layer, bigDecimalMin, bigDecimalSubtract, unitCost != null ? bigDecimalMin.multiply(BigDecimal.valueOf(unitCost.longValue())) : null));
                bigDecimalValueOf = bigDecimalValueOf.subtract(bigDecimalMin);
            }
            if (bigDecimalValueOf.signum() != 0) {
                throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0627\u0633\u062a\u0647\u0644\u0627\u0643 \u0643\u0627\u0645\u0644 \u0627\u0644\u0643\u0645\u064a\u0629 \u0645\u0646 \u0637\u0628\u0642\u0627\u062a FIFO".toString());
            }
            ArrayList arrayList3 = arrayList2;
            if ((arrayList3 instanceof Collection) && arrayList3.isEmpty()) {
                bigDecimalAdd = BigDecimal.ZERO;
                it = arrayList3.iterator();
                while (it.hasNext()) {
                    BigDecimal exactCost = ((PlannedAllocation) it.next()).getExactCost();
                    Intrinsics.checkNotNull(exactCost);
                    bigDecimalAdd = bigDecimalAdd.add(exactCost);
                }
            } else {
                Iterator it3 = arrayList3.iterator();
                while (true) {
                    if (it3.hasNext()) {
                        if (((PlannedAllocation) it3.next()).getExactCost() == null) {
                            bigDecimalAdd = null;
                        }
                    } else {
                        bigDecimalAdd = BigDecimal.ZERO;
                        it = arrayList3.iterator();
                        while (it.hasNext()) {
                            BigDecimal exactCost2 = ((PlannedAllocation) it.next()).getExactCost();
                            Intrinsics.checkNotNull(exactCost2);
                            bigDecimalAdd = bigDecimalAdd.add(exactCost2);
                        }
                    }
                }
            }
            Long lValueOf3 = (bigDecimalAdd == null || (scale = bigDecimalAdd.setScale(0, RoundingMode.HALF_UP)) == null) ? null : Long.valueOf(scale.longValueExact());
            Iterator it4 = arrayList3.iterator();
            long jM = 0;
            int i2 = 0;
            while (it4.hasNext()) {
                Object next = it4.next();
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                PlannedAllocation plannedAllocation = (PlannedAllocation) next;
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("remaining_quantity", Double.valueOf(plannedAllocation.getRemaining().doubleValue()));
                Unit unit2 = Unit.INSTANCE;
                Iterator it5 = it4;
                if (db.update("inventory_cost_layers", contentValues2, "id=? AND remaining_quantity>=?", new String[]{plannedAllocation.getLayer().getId(), plannedAllocation.getConsumed().toString()}) != i) {
                    throw new IllegalStateException("\u062a\u063a\u064a\u0631\u062a \u0637\u0628\u0642\u0629 \u0627\u0644\u062a\u0643\u0644\u0641\u0629 \u0623\u062b\u0646\u0627\u0621 \u0627\u0644\u0627\u0639\u062a\u0645\u0627\u062f\u061b \u0623\u0639\u062f \u0627\u0644\u0645\u062d\u0627\u0648\u0644\u0629".toString());
                }
                if (plannedAllocation.getExactCost() == null) {
                    lValueOf = null;
                } else {
                    if (lValueOf3 != null && i2 == CollectionsKt.getLastIndex(arrayList2)) {
                        lValueOf = Long.valueOf(DailyBalanceRepository$$ExternalSyntheticBackport0.m(lValueOf3.longValue(), jM));
                    } else if (lValueOf3 != null) {
                        z = false;
                        lValueOf = Long.valueOf(plannedAllocation.getExactCost().setScale(0, RoundingMode.DOWN).longValueExact());
                        jM = MainActivity$$ExternalSyntheticBackport0.m(jM, lValueOf.longValue());
                    } else {
                        z = false;
                        lValueOf = Long.valueOf(plannedAllocation.getExactCost().setScale(0, RoundingMode.HALF_UP).longValueExact());
                    }
                    if (recordSaleAllocation) {
                        contentValues = new ContentValues();
                        contentValues.put("id", UUID.randomUUID().toString());
                        contentValues.put("sales_invoice_id", invoiceId);
                        if (salesItemId == null) {
                            contentValues.putNull("sales_item_id");
                        } else {
                            contentValues.put("sales_item_id", salesItemId);
                        }
                        contentValues.put("cost_layer_id", plannedAllocation.getLayer().getId());
                        contentValues.put("quantity", Double.valueOf(plannedAllocation.getConsumed().doubleValue()));
                        if (plannedAllocation.getLayer().getUnitCost() == null) {
                            contentValues.putNull("unit_cost_usd_cents");
                        } else {
                            contentValues.put("unit_cost_usd_cents", plannedAllocation.getLayer().getUnitCost());
                        }
                        if (lValueOf == null) {
                            contentValues.putNull("cost_usd_cents");
                        } else {
                            contentValues.put("cost_usd_cents", lValueOf);
                        }
                        contentValues.put("created_at", createdAt);
                        obj = null;
                        db.insertOrThrow("sales_item_cost_allocations", null, contentValues);
                    } else {
                        obj = null;
                    }
                    it4 = it5;
                    i2 = i3;
                    i = 1;
                }
                z = false;
                if (recordSaleAllocation) {
                    contentValues = new ContentValues();
                    contentValues.put("id", UUID.randomUUID().toString());
                    contentValues.put("sales_invoice_id", invoiceId);
                    if (salesItemId == null) {
                        contentValues.putNull("sales_item_id");
                    } else {
                        contentValues.put("sales_item_id", salesItemId);
                    }
                    contentValues.put("cost_layer_id", plannedAllocation.getLayer().getId());
                    contentValues.put("quantity", Double.valueOf(plannedAllocation.getConsumed().doubleValue()));
                    if (plannedAllocation.getLayer().getUnitCost() == null) {
                        contentValues.putNull("unit_cost_usd_cents");
                    } else {
                        contentValues.put("unit_cost_usd_cents", plannedAllocation.getLayer().getUnitCost());
                    }
                    if (lValueOf == null) {
                        contentValues.putNull("cost_usd_cents");
                    } else {
                        contentValues.put("cost_usd_cents", lValueOf);
                    }
                    contentValues.put("created_at", createdAt);
                    obj = null;
                    db.insertOrThrow("sales_item_cost_allocations", null, contentValues);
                } else {
                    obj = null;
                }
                it4 = it5;
                i2 = i3;
                i = 1;
            }
            if (salesItemId != null) {
                ContentValues contentValues3 = new ContentValues();
                if (lValueOf3 == null) {
                    contentValues3.putNull("cogs_usd_cents");
                } else {
                    contentValues3.put("cogs_usd_cents", lValueOf3);
                }
                Unit unit3 = Unit.INSTANCE;
                if (db.update("sales_items", contentValues3, "id=? AND sales_invoice_id=?", new String[]{salesItemId, invoiceId}) != 1) {
                    throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u062d\u0641\u0638 \u062a\u0643\u0644\u0641\u0629 \u0627\u0644\u0628\u0636\u0627\u0639\u0629 \u0627\u0644\u0645\u0628\u0627\u0639\u0629 \u0639\u0644\u0649 \u0628\u0646\u062f \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629".toString());
                }
            }
            return lValueOf3;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    /* JADX INFO: compiled from: FifoCosting.kt */
    @Metadata(d1 = {"\u0000-\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\u008a\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J8\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r\u00a8\u0006\u001d"}, d2 = {"com/hazem/dailystore/data/FifoCosting$consumeForSale$PlannedAllocation", "", "layer", "Lcom/hazem/dailystore/data/FifoCosting$Layer;", "consumed", "Ljava/math/BigDecimal;", "remaining", "exactCost", "<init>", "(Lcom/hazem/dailystore/data/FifoCosting$Layer;Ljava/math/BigDecimal;Ljava/math/BigDecimal;Ljava/math/BigDecimal;)V", "getLayer", "()Lcom/hazem/dailystore/data/FifoCosting$Layer;", "getConsumed", "()Ljava/math/BigDecimal;", "getRemaining", "getExactCost", "component1", "component2", "component3", "component4", "copy", "(Lcom/hazem/dailystore/data/FifoCosting$Layer;Ljava/math/BigDecimal;Ljava/math/BigDecimal;Ljava/math/BigDecimal;)Lcom/hazem/dailystore/data/FifoCosting$consumeForSale$PlannedAllocation;", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class PlannedAllocation {
        private final BigDecimal consumed;
        private final BigDecimal exactCost;
        private final Layer layer;
        private final BigDecimal remaining;

        public static /* synthetic */ PlannedAllocation copy$default(PlannedAllocation plannedAllocation, Layer layer, BigDecimal bigDecimal, BigDecimal bigDecimal2, BigDecimal bigDecimal3, int i, Object obj) {
            if ((i & 1) != 0) {
                layer = plannedAllocation.layer;
            }
            if ((i & 2) != 0) {
                bigDecimal = plannedAllocation.consumed;
            }
            if ((i & 4) != 0) {
                bigDecimal2 = plannedAllocation.remaining;
            }
            if ((i & 8) != 0) {
                bigDecimal3 = plannedAllocation.exactCost;
            }
            return plannedAllocation.copy(layer, bigDecimal, bigDecimal2, bigDecimal3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Layer getLayer() {
            return this.layer;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final BigDecimal getConsumed() {
            return this.consumed;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final BigDecimal getRemaining() {
            return this.remaining;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final BigDecimal getExactCost() {
            return this.exactCost;
        }

        public final PlannedAllocation copy(Layer layer, BigDecimal consumed, BigDecimal remaining, BigDecimal exactCost) {
            Intrinsics.checkNotNullParameter(layer, "layer");
            Intrinsics.checkNotNullParameter(consumed, "consumed");
            Intrinsics.checkNotNullParameter(remaining, "remaining");
            return new PlannedAllocation(layer, consumed, remaining, exactCost);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PlannedAllocation)) {
                return false;
            }
            PlannedAllocation plannedAllocation = (PlannedAllocation) other;
            return Intrinsics.areEqual(this.layer, plannedAllocation.layer) && Intrinsics.areEqual(this.consumed, plannedAllocation.consumed) && Intrinsics.areEqual(this.remaining, plannedAllocation.remaining) && Intrinsics.areEqual(this.exactCost, plannedAllocation.exactCost);
        }

        public int hashCode() {
            int iHashCode = ((((this.layer.hashCode() * 31) + this.consumed.hashCode()) * 31) + this.remaining.hashCode()) * 31;
            BigDecimal bigDecimal = this.exactCost;
            return iHashCode + (bigDecimal == null ? 0 : bigDecimal.hashCode());
        }

        public String toString() {
            return "PlannedAllocation(layer=" + this.layer + ", consumed=" + this.consumed + ", remaining=" + this.remaining + ", exactCost=" + this.exactCost + ")";
        }

        public PlannedAllocation(Layer layer, BigDecimal consumed, BigDecimal remaining, BigDecimal bigDecimal) {
            Intrinsics.checkNotNullParameter(layer, "layer");
            Intrinsics.checkNotNullParameter(consumed, "consumed");
            Intrinsics.checkNotNullParameter(remaining, "remaining");
            this.layer = layer;
            this.consumed = consumed;
            this.remaining = remaining;
            this.exactCost = bigDecimal;
        }

        public final Layer getLayer() {
            return this.layer;
        }

        public final BigDecimal getConsumed() {
            return this.consumed;
        }

        public final BigDecimal getRemaining() {
            return this.remaining;
        }

        public final BigDecimal getExactCost() {
            return this.exactCost;
        }
    }
}
