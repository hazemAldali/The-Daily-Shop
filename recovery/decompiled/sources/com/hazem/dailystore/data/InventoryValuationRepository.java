package com.hazem.dailystore.data;

import android.content.Context;
import android.database.Cursor;
import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.zetetic.database.sqlcipher.SQLiteDatabase;

/* JADX INFO: compiled from: InventoryValuationRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0014B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bJ\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000bH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/hazem/dailystore/data/InventoryValuationRepository;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "db", "Lnet/zetetic/database/sqlcipher/SQLiteDatabase;", "dailyCapital", "Lcom/hazem/dailystore/data/DailyInventoryCapital;", "now", "Ljava/util/Date;", "valueAt", "Lcom/hazem/dailystore/data/InventoryValuation;", "cutoff", "reconcileWithCurrentBalances", "", "utcStamp", "", "date", "Lot", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class InventoryValuationRepository {
    private final SQLiteDatabase db;

    public InventoryValuationRepository(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SQLiteDatabase readableDatabase = AppDatabase.INSTANCE.getInstance(context).getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        this.db = readableDatabase;
    }

    /* JADX INFO: compiled from: InventoryValuationRepository.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00c6\u0003J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0011J.\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0017J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001J\t\u0010\u001d\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u001e"}, d2 = {"Lcom/hazem/dailystore/data/InventoryValuationRepository$Lot;", "", "layerId", "", "quantity", "", "unitCostUsdCents", "", "<init>", "(Ljava/lang/String;DLjava/lang/Long;)V", "getLayerId", "()Ljava/lang/String;", "getQuantity", "()D", "setQuantity", "(D)V", "getUnitCostUsdCents", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;DLjava/lang/Long;)Lcom/hazem/dailystore/data/InventoryValuationRepository$Lot;", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class Lot {
        private final String layerId;
        private double quantity;
        private final Long unitCostUsdCents;

        public static /* synthetic */ Lot copy$default(Lot lot, String str, double d, Long l, int i, Object obj) {
            if ((i & 1) != 0) {
                str = lot.layerId;
            }
            if ((i & 2) != 0) {
                d = lot.quantity;
            }
            if ((i & 4) != 0) {
                l = lot.unitCostUsdCents;
            }
            return lot.copy(str, d, l);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLayerId() {
            return this.layerId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final double getQuantity() {
            return this.quantity;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Long getUnitCostUsdCents() {
            return this.unitCostUsdCents;
        }

        public final Lot copy(String layerId, double quantity, Long unitCostUsdCents) {
            Intrinsics.checkNotNullParameter(layerId, "layerId");
            return new Lot(layerId, quantity, unitCostUsdCents);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Lot)) {
                return false;
            }
            Lot lot = (Lot) other;
            return Intrinsics.areEqual(this.layerId, lot.layerId) && Double.compare(this.quantity, lot.quantity) == 0 && Intrinsics.areEqual(this.unitCostUsdCents, lot.unitCostUsdCents);
        }

        public int hashCode() {
            int iHashCode = ((this.layerId.hashCode() * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.quantity)) * 31;
            Long l = this.unitCostUsdCents;
            return iHashCode + (l == null ? 0 : l.hashCode());
        }

        public String toString() {
            return "Lot(layerId=" + this.layerId + ", quantity=" + this.quantity + ", unitCostUsdCents=" + this.unitCostUsdCents + ")";
        }

        public Lot(String layerId, double d, Long l) {
            Intrinsics.checkNotNullParameter(layerId, "layerId");
            this.layerId = layerId;
            this.quantity = d;
            this.unitCostUsdCents = l;
        }

        public final String getLayerId() {
            return this.layerId;
        }

        public final double getQuantity() {
            return this.quantity;
        }

        public final Long getUnitCostUsdCents() {
            return this.unitCostUsdCents;
        }

        public final void setQuantity(double d) {
            this.quantity = d;
        }
    }

    public static /* synthetic */ DailyInventoryCapital dailyCapital$default(InventoryValuationRepository inventoryValuationRepository, Date date, int i, Object obj) {
        if ((i & 1) != 0) {
            date = new Date();
        }
        return inventoryValuationRepository.dailyCapital(date);
    }

    public final DailyInventoryCapital dailyCapital(Date now) throws IOException {
        Intrinsics.checkNotNullParameter(now, "now");
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(StoreTime.INSTANCE.zoneId(this.db)));
        calendar.setTime(now);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        InventoryValuation inventoryValuationValueAt = valueAt(new Date(calendar.getTimeInMillis() - 1), false);
        InventoryValuation inventoryValuationValueAt2 = valueAt(now, true);
        return new DailyInventoryCapital(inventoryValuationValueAt2, inventoryValuationValueAt, DailyBalanceRepository$$ExternalSyntheticBackport0.m(inventoryValuationValueAt2.getTotalCostUsdCents(), inventoryValuationValueAt.getTotalCostUsdCents()));
    }

    private final InventoryValuation valueAt(Date cutoff, boolean reconcileWithCurrentBalances) throws IOException {
        int i;
        int i2;
        int i3;
        Long unitCostUsdCents;
        double quantity;
        LinkedHashMap linkedHashMap;
        LinkedHashMap linkedHashMap2;
        LinkedHashMap linkedHashMap3;
        Iterator it;
        Object next;
        LinkedHashMap linkedHashMap4;
        LinkedHashMap linkedHashMap5;
        this = this;
        String strUtcStamp = utcStamp(cutoff);
        LinkedHashMap linkedHashMap6 = new LinkedHashMap();
        LinkedHashMap linkedHashMap7 = new LinkedHashMap();
        LinkedHashMap linkedHashMap8 = new LinkedHashMap();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        LinkedHashMap linkedHashMap9 = new LinkedHashMap();
        Cursor cursorRawQuery = this.db.rawQuery("SELECT id,source_movement_id FROM inventory_cost_layers", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            while (true) {
                i = 1;
                i2 = 0;
                if (!cursor.moveToNext()) {
                    break;
                }
                linkedHashMap9.put(cursor.getString(1), cursor.getString(0));
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            LinkedHashMap linkedHashMap10 = new LinkedHashMap();
            Cursor cursorRawQuery2 = this.db.rawQuery("SELECT inventory_movement_id,source_cost_layer_id,quantity FROM supplier_return_cost_allocations", (String[]) null);
            try {
                Cursor cursor2 = cursorRawQuery2;
                while (true) {
                    i3 = 2;
                    if (!cursor2.moveToNext()) {
                        break;
                    }
                    String string = cursor2.getString(0);
                    Object obj = linkedHashMap10.get(string);
                    if (obj == null) {
                        obj = (List) new ArrayList();
                        linkedHashMap10.put(string, obj);
                    }
                    ((List) obj).add(TuplesKt.to(cursor2.getString(1), Double.valueOf(cursor2.getDouble(2))));
                }
                Unit unit2 = Unit.INSTANCE;
                CloseableKt.closeFinally(cursorRawQuery2, null);
                Cursor cursorRawQuery3 = this.db.rawQuery("SELECT p.id,ib.warehouse_id,COALESCE(SUM(ib.quantity),0)\n               FROM products p LEFT JOIN inventory_balances ib ON ib.product_id=p.id\n               WHERE p.track_inventory=1 GROUP BY p.id,ib.warehouse_id", (String[]) null);
                try {
                    Cursor cursor3 = cursorRawQuery3;
                    while (cursor3.moveToNext()) {
                        linkedHashMap8.put(cursor3.getString(i2) + "|" + (cursor3.isNull(i) ? "NO_WAREHOUSE" : cursor3.getString(i)), Double.valueOf(cursor3.getDouble(2)));
                        i = 1;
                        i2 = 0;
                    }
                    Unit unit3 = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursorRawQuery3, null);
                    Cursor cursorRawQuery4 = this.db.rawQuery("SELECT m.id,m.product_id,m.warehouse_id,m.quantity_delta,m.unit_cost_usd_cents,m.movement_type\n               FROM inventory_movements m JOIN products p ON p.id=m.product_id\n               WHERE p.track_inventory=1 AND m.created_at<=?\n               ORDER BY m.created_at ASC,m.rowid ASC", new String[]{strUtcStamp});
                    try {
                        Cursor cursor4 = cursorRawQuery4;
                        while (true) {
                            if (!cursor4.moveToNext()) {
                                break;
                            }
                            try {
                                String string2 = cursor4.getString(0);
                                String str = cursor4.getString(1) + "|" + cursor4.getString(i3);
                                double d = cursor4.getDouble(3);
                                long j = cursor4.getLong(4);
                                String string3 = cursor4.getString(5);
                                LinkedHashMap linkedHashMap11 = linkedHashMap6;
                                Object obj2 = linkedHashMap11.get(str);
                                if (obj2 == null) {
                                    ArrayList arrayList = new ArrayList();
                                    linkedHashMap11.put(str, arrayList);
                                    obj2 = arrayList;
                                }
                                List list = (List) obj2;
                                if (d > 0.0d) {
                                    Double d2 = (Double) linkedHashMap7.get(str);
                                    linkedHashMap = linkedHashMap6;
                                    double dDoubleValue = d2 != null ? d2.doubleValue() : 0.0d;
                                    if (dDoubleValue > 1.0E-7d) {
                                        double dMin = Math.min(d, dDoubleValue);
                                        linkedHashMap7.put(str, Double.valueOf(dDoubleValue - dMin));
                                        d -= dMin;
                                        Object obj3 = linkedHashMap7.get(str);
                                        Intrinsics.checkNotNull(obj3);
                                        if (((Number) obj3).doubleValue() <= 1.0E-7d) {
                                            linkedHashMap7.remove(str);
                                        }
                                    }
                                    if (d > 1.0E-7d) {
                                        String str2 = (String) linkedHashMap9.get(string2);
                                        if (str2 != null) {
                                            string2 = str2;
                                        }
                                        Intrinsics.checkNotNull(string2);
                                        Long lValueOf = Long.valueOf(j);
                                        if (lValueOf.longValue() <= 0) {
                                            lValueOf = null;
                                        }
                                        list.add(new Lot(string2, d, lValueOf));
                                    }
                                } else {
                                    linkedHashMap = linkedHashMap6;
                                    if (d >= 0.0d || !Intrinsics.areEqual(string3, "PURCHASE_RETURN")) {
                                        linkedHashMap2 = linkedHashMap9;
                                        linkedHashMap3 = linkedHashMap10;
                                        if (d < 0.0d) {
                                            double d3 = -d;
                                            while (d3 > 1.0E-7d && !list.isEmpty()) {
                                                Lot lot = (Lot) CollectionsKt.first(list);
                                                double dMin2 = Math.min(d3, lot.getQuantity());
                                                LinkedHashMap linkedHashMap12 = linkedHashMap3;
                                                LinkedHashMap linkedHashMap13 = linkedHashMap;
                                                lot.setQuantity(lot.getQuantity() - dMin2);
                                                d3 -= dMin2;
                                                if (lot.getQuantity() <= 1.0E-7d) {
                                                    list.remove(0);
                                                }
                                                linkedHashMap = linkedHashMap13;
                                                linkedHashMap3 = linkedHashMap12;
                                            }
                                            LinkedHashMap linkedHashMap14 = linkedHashMap3;
                                            LinkedHashMap linkedHashMap15 = linkedHashMap;
                                            if (d3 > 1.0E-7d) {
                                                Double d4 = (Double) linkedHashMap7.get(str);
                                                linkedHashMap7.put(str, Double.valueOf((d4 != null ? d4.doubleValue() : 0.0d) + d3));
                                            }
                                            i3 = 2;
                                            this = this;
                                            linkedHashMap6 = linkedHashMap15;
                                            linkedHashMap9 = linkedHashMap2;
                                            linkedHashMap10 = linkedHashMap14;
                                        }
                                    } else {
                                        List listEmptyList = (List) linkedHashMap10.get(string2);
                                        if (listEmptyList == null) {
                                            listEmptyList = CollectionsKt.emptyList();
                                        }
                                        Iterator it2 = listEmptyList.iterator();
                                        double d5 = 0.0d;
                                        while (it2.hasNext()) {
                                            Pair pair = (Pair) it2.next();
                                            String str3 = (String) pair.component1();
                                            double dDoubleValue2 = ((Number) pair.component2()).doubleValue();
                                            Iterator it3 = list.iterator();
                                            while (true) {
                                                if (!it3.hasNext()) {
                                                    it = it2;
                                                    next = null;
                                                    break;
                                                }
                                                next = it3.next();
                                                it = it2;
                                                if (Intrinsics.areEqual(((Lot) next).getLayerId(), str3)) {
                                                    break;
                                                }
                                                it2 = it;
                                            }
                                            Lot lot2 = (Lot) next;
                                            if (lot2 == null || lot2.getQuantity() + 1.0E-7d < dDoubleValue2) {
                                                linkedHashMap4 = linkedHashMap9;
                                                linkedHashMap5 = linkedHashMap10;
                                                d5 += dDoubleValue2;
                                            } else {
                                                linkedHashMap4 = linkedHashMap9;
                                                linkedHashMap5 = linkedHashMap10;
                                                lot2.setQuantity(lot2.getQuantity() - dDoubleValue2);
                                                if (lot2.getQuantity() <= 1.0E-7d) {
                                                    list.remove(lot2);
                                                }
                                            }
                                            linkedHashMap9 = linkedHashMap4;
                                            linkedHashMap10 = linkedHashMap5;
                                            it2 = it;
                                        }
                                        linkedHashMap2 = linkedHashMap9;
                                        linkedHashMap3 = linkedHashMap10;
                                        if (listEmptyList.isEmpty()) {
                                            d5 = -d;
                                        }
                                        if (d5 > 1.0E-7d) {
                                            Double d6 = (Double) linkedHashMap7.get(str);
                                            linkedHashMap7.put(str, Double.valueOf((d6 != null ? d6.doubleValue() : 0.0d) + d5));
                                        }
                                    }
                                    linkedHashMap9 = linkedHashMap2;
                                    linkedHashMap10 = linkedHashMap3;
                                }
                                linkedHashMap6 = linkedHashMap;
                                i3 = 2;
                            } catch (Throwable th) {
                                th = th;
                            }
                            th = th;
                            Throwable th2 = th;
                            try {
                                throw th2;
                            } catch (Throwable th3) {
                                CloseableKt.closeFinally(cursorRawQuery4, th2);
                                throw th3;
                            }
                        }
                        LinkedHashMap linkedHashMap16 = linkedHashMap6;
                        Unit unit4 = Unit.INSTANCE;
                        String str4 = null;
                        CloseableKt.closeFinally(cursorRawQuery4, null);
                        Iterator it4 = linkedHashMap7.keySet().iterator();
                        while (it4.hasNext()) {
                            linkedHashSet.add(StringsKt.substringBefore$default((String) it4.next(), "|", str4, 2, str4));
                            str4 = null;
                        }
                        double dSumOfDouble = CollectionsKt.sumOfDouble(linkedHashMap7.values());
                        Set setKeySet = linkedHashMap16.keySet();
                        Intrinsics.checkNotNullExpressionValue(setKeySet, "<get-keys>(...)");
                        Set<String> set = CollectionsKt.toSet(SetsKt.plus(SetsKt.plus(setKeySet, (Iterable) linkedHashMap8.keySet()), (Iterable) linkedHashMap7.keySet()));
                        for (String str5 : set) {
                            List listEmptyList2 = (List) linkedHashMap16.get(str5);
                            if (listEmptyList2 == null) {
                                listEmptyList2 = CollectionsKt.emptyList();
                            }
                            ArrayList arrayList2 = new ArrayList();
                            for (Object obj4 : listEmptyList2) {
                                if (((Lot) obj4).getUnitCostUsdCents() == null) {
                                    arrayList2.add(obj4);
                                }
                            }
                            Iterator it5 = arrayList2.iterator();
                            double quantity2 = 0.0d;
                            while (it5.hasNext()) {
                                quantity2 += ((Lot) it5.next()).getQuantity();
                            }
                            if (quantity2 > 1.0E-7d) {
                                linkedHashSet.add(StringsKt.substringBefore$default(str5, "|", (String) null, 2, (Object) null));
                                dSumOfDouble += quantity2;
                            }
                        }
                        if (reconcileWithCurrentBalances) {
                            for (String str6 : set) {
                                List list2 = (List) linkedHashMap16.get(str6);
                                if (list2 != null) {
                                    Iterator it6 = list2.iterator();
                                    quantity = 0.0d;
                                    while (it6.hasNext()) {
                                        quantity += ((Lot) it6.next()).getQuantity();
                                    }
                                } else {
                                    quantity = 0.0d;
                                }
                                Double d7 = (Double) linkedHashMap7.get(str6);
                                double dDoubleValue3 = quantity - (d7 != null ? d7.doubleValue() : 0.0d);
                                Double d8 = (Double) linkedHashMap8.get(str6);
                                double dDoubleValue4 = (d8 != null ? d8.doubleValue() : 0.0d) - dDoubleValue3;
                                if (Math.abs(dDoubleValue4) > 1.0E-7d) {
                                    linkedHashSet.add(StringsKt.substringBefore$default(str6, "|", (String) null, 2, (Object) null));
                                    dSumOfDouble += Math.abs(dDoubleValue4);
                                }
                            }
                        }
                        double d9 = dSumOfDouble;
                        BigDecimal bigDecimalAdd = BigDecimal.ZERO;
                        double quantity3 = 0.0d;
                        for (String str7 : set) {
                            if (!linkedHashSet.contains(StringsKt.substringBefore$default(str7, "|", (String) null, 2, (Object) null))) {
                                List<Lot> listEmptyList3 = (List) linkedHashMap16.get(str7);
                                if (listEmptyList3 == null) {
                                    listEmptyList3 = CollectionsKt.emptyList();
                                }
                                for (Lot lot3 : listEmptyList3) {
                                    if (lot3.getQuantity() > 1.0E-7d && (unitCostUsdCents = lot3.getUnitCostUsdCents()) != null) {
                                        bigDecimalAdd = bigDecimalAdd.add(BigDecimal.valueOf(unitCostUsdCents.longValue()).multiply(BigDecimal.valueOf(lot3.getQuantity())));
                                        quantity3 += lot3.getQuantity();
                                    }
                                }
                            }
                        }
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
                        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(StoreTime.INSTANCE.zoneId(this.db)));
                        String str8 = simpleDateFormat.format(cutoff);
                        Intrinsics.checkNotNullExpressionValue(str8, "format(...)");
                        return new InventoryValuation(str8, bigDecimalAdd.setScale(0, RoundingMode.HALF_UP).longValueExact(), quantity3, d9, linkedHashSet.size(), linkedHashSet.isEmpty());
                    } catch (Throwable th4) {
                        th = th4;
                    }
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
                    CloseableKt.closeFinally(cursorRawQuery2, th7);
                    throw th8;
                }
            }
        } catch (Throwable th9) {
            try {
                throw th9;
            } catch (Throwable th10) {
                CloseableKt.closeFinally(cursorRawQuery, th9);
                throw th10;
            }
        }
    }

    private final String utcStamp(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String str = simpleDateFormat.format(date);
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }
}
