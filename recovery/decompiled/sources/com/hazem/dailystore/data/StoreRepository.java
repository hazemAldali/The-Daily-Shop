package com.hazem.dailystore.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
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
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import net.zetetic.database.sqlcipher.SQLiteDatabase;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: StoreRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u000b\u001a\u00020\fJ\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eJ\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000eJ\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0015J\u0016\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u0015J\u0016\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0015J\u001d\u0010\u001e\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u0013\u00a2\u0006\u0002\u0010!Jk\u0010\"\u001a\u00020\u00152\b\u0010#\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u001d\u001a\u00020\u00152\b\u0010$\u001a\u0004\u0018\u00010\u00152\b\u0010%\u001a\u0004\u0018\u00010\u00152\b\u0010&\u001a\u0004\u0018\u00010\u00132\u0006\u0010'\u001a\u00020\u00132\u0006\u0010(\u001a\u00020\u00132\u0006\u0010)\u001a\u00020\u00132\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0018\u001a\u00020\u0015\u00a2\u0006\u0002\u0010.J$\u0010/\u001a\u0002002\f\u00101\u001a\b\u0012\u0004\u0012\u0002020\u000e2\u0006\u00103\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0015J\u001c\u00104\u001a\u00020\u00132\n\u00105\u001a\u000606j\u0002`72\u0006\u00108\u001a\u00020\u0015H\u0002J(\u00109\u001a\u00020\f2\u0006\u0010:\u001a\u00020\u00152\u0006\u0010;\u001a\u00020-2\u0006\u0010\u0018\u001a\u00020\u00152\b\u0010<\u001a\u0004\u0018\u00010\u0015J\u000e\u0010=\u001a\u00020+2\u0006\u0010:\u001a\u00020\u0015J\u001e\u0010>\u001a\u00020\f2\u0006\u0010#\u001a\u00020\u00152\u0006\u0010?\u001a\u00020+2\u0006\u0010\u0018\u001a\u00020\u0015J\f\u0010@\u001a\b\u0012\u0004\u0012\u00020A0\u000eJ\u001c\u0010B\u001a\u00020\f2\n\u00105\u001a\u000606j\u0002`72\u0006\u0010\u0018\u001a\u00020\u0015H\u0002JN\u0010C\u001a\u00020\f2\n\u00105\u001a\u000606j\u0002`72\u0006\u0010D\u001a\u00020\u00152\u0006\u0010E\u001a\u00020\u00152\u0006\u0010F\u001a\u00020\u00152\u0006\u0010G\u001a\u00020\u00152\b\u0010H\u001a\u0004\u0018\u00010\u00152\u0006\u0010I\u001a\u00020\u00152\u0006\u0010J\u001a\u00020\u0015H\u0002J\u0010\u0010K\u001a\u00020\u00152\u0006\u0010L\u001a\u00020\u0015H\u0002J\u001e\u0010M\u001a\u00020\f*\u00020N2\u0006\u0010O\u001a\u00020\u00152\b\u0010L\u001a\u0004\u0018\u00010\u0015H\u0002J4\u0010P\u001a\u00020\f2\n\u00105\u001a\u000606j\u0002`72\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010:\u001a\u00020\u00152\u0006\u0010Q\u001a\u00020\u00152\u0006\u0010R\u001a\u00020+H\u0002J\b\u0010S\u001a\u00020\fH\u0002J\b\u0010T\u001a\u00020\u0015H\u0002R\u0018\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006U"}, d2 = {"Lcom/hazem/dailystore/data/StoreRepository;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "appContext", "kotlin.jvm.PlatformType", "Landroid/content/Context;", "helper", "Lcom/hazem/dailystore/data/AppDatabase;", "ensureBaseSetup", "", "getUnits", "", "Lcom/hazem/dailystore/data/StoreUnit;", "getCategories", "Lcom/hazem/dailystore/data/StoreCategory;", "getCurrentExchangeRateMilli", "", "getStoreTimeZoneId", "", "saveStoreTimeZoneId", "zoneId", "actorUserId", "saveExchangeRate", "rateSypPerUsd", "Ljava/math/BigDecimal;", "addCategory", "name", "resolveSecondaryCategoryId", "primaryDeviceId", "primaryCategoryId", "(Ljava/lang/String;J)Ljava/lang/Long;", "saveProduct", "id", "barcode", "sku", "categoryId", "unitId", "purchaseCostUsdCents", "salePriceUsdCents", "tracksInventory", "", "openingQuantity", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;JJJZDLjava/lang/String;)Ljava/lang/String;", "importProducts", "", "rows", "Lcom/hazem/dailystore/data/ProductImportRow;", "exchangeRateMilli", "resolveImportUnit", "db", "Lnet/zetetic/database/sqlcipher/SQLiteDatabase;", "Lcom/hazem/dailystore/data/AppSqliteDatabase;", "rawName", "recordOpeningStock", "productId", "quantity", "note", "canRecordOpeningStock", "setProductActive", "active", "listProducts", "Lcom/hazem/dailystore/data/StoreProduct;", "requireManager", "insertAudit", "actor", "action", "entityType", "entityId", "before", "after", "now", "jsonEscape", "value", "putNullable", "Landroid/content/ContentValues;", "key", "enqueueProductSync", "changeKind", "includeOpeningStock", "requirePrimaryCatalogAuthority", "timestamp", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class StoreRepository {
    private final Context appContext;
    private final AppDatabase helper;

    public StoreRepository(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Context appContext = context.getApplicationContext();
        this.appContext = appContext;
        AppDatabase.Companion companion = AppDatabase.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        this.helper = companion.getInstance(appContext);
    }

    public final void ensureBaseSetup() {
        SQLiteDatabase writableDatabase = this.helper.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        writableDatabase.beginTransaction();
        try {
            String strTimestamp = timestamp();
            for (Triple triple : CollectionsKt.listOf((Object[]) new Triple[]{new Triple("\u0642\u0637\u0639\u0629", "\u0642\u0637\u0639\u0629", 0), new Triple("\u0643\u064a\u0644\u0648\u063a\u0631\u0627\u0645", "\u0643\u063a", 3), new Triple("\u063a\u0631\u0627\u0645", "\u063a", 0), new Triple("\u0644\u062a\u0631", "\u0644", 3), new Triple("\u0639\u0644\u0628\u0629", "\u0639\u0644\u0628\u0629", 0), new Triple("\u0631\u0628\u0637\u0629", "\u0631\u0628\u0637\u0629", 0), new Triple("\u0643\u0631\u062a\u0648\u0646\u0629", "\u0643\u0631\u062a\u0648\u0646\u0629", 0), new Triple("\u0637\u0631\u062f", "\u0637\u0631\u062f", 0)})) {
                writableDatabase.execSQL("INSERT OR IGNORE INTO units(name,symbol,decimal_places) VALUES(?,?,?)", new Object[]{(String) triple.component1(), (String) triple.component2(), Integer.valueOf(((Number) triple.component3()).intValue())});
            }
            Iterator it = CollectionsKt.listOf((Object[]) new String[]{"\u0645\u0648\u0627\u062f \u063a\u0630\u0627\u0626\u064a\u0629", "\u0645\u0634\u0631\u0648\u0628\u0627\u062a", "\u0645\u0646\u0638\u0641\u0627\u062a", "\u0639\u0646\u0627\u064a\u0629 \u0634\u062e\u0635\u064a\u0629", "\u0623\u062e\u0631\u0649"}).iterator();
            while (it.hasNext()) {
                writableDatabase.execSQL("INSERT OR IGNORE INTO categories(name,created_at) VALUES(?,?)", new String[]{(String) it.next(), strTimestamp});
            }
            writableDatabase.execSQL("INSERT OR IGNORE INTO warehouses(id,name,is_default,is_active,created_at)\n                   VALUES('default-warehouse','\u0627\u0644\u0645\u0633\u062a\u0648\u062f\u0639 \u0627\u0644\u0631\u0626\u064a\u0633\u064a',1,1,?)", new String[]{strTimestamp});
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            try {
                Result.Companion companion = Result.INSTANCE;
                StoreRepository storeRepository = this;
            } catch (Throwable th) {
            }
        } finally {
            writableDatabase.endTransaction();
            try {
                Result.Companion companion2 = Result.INSTANCE;
                SyncRelaySession.INSTANCE.flushPending();
                Result.m381constructorimpl(Unit.INSTANCE);
            } catch (Throwable th2) {
                Result.Companion companion3 = Result.INSTANCE;
                Result.m381constructorimpl(ResultKt.createFailure(th2));
            }
        }
    }

    public final List<StoreUnit> getUnits() {
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = this.helper.getReadableDatabase().rawQuery("SELECT id,name,symbol,decimal_places FROM units ORDER BY id", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            while (cursor.moveToNext()) {
                long j = cursor.getLong(0);
                String string = cursor.getString(1);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String string2 = cursor.getString(2);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                arrayList.add(new StoreUnit(j, string, string2, cursor.getInt(3)));
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

    public final List<StoreCategory> getCategories() {
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = this.helper.getReadableDatabase().rawQuery("SELECT id,name FROM categories WHERE is_active=1 ORDER BY name COLLATE NOCASE", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            while (cursor.moveToNext()) {
                long j = cursor.getLong(0);
                String string = cursor.getString(1);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                arrayList.add(new StoreCategory(j, string));
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

    public final long getCurrentExchangeRateMilli() {
        Cursor cursorRawQuery = this.helper.getReadableDatabase().rawQuery("SELECT rate_syp_per_usd_milli FROM exchange_rates ORDER BY effective_at DESC, created_at DESC LIMIT 1", (String[]) null);
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

    /* JADX WARN: Multi-variable type inference failed */
    public final String getStoreTimeZoneId() {
        StoreTime storeTime = StoreTime.INSTANCE;
        SQLiteDatabase readableDatabase = this.helper.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        return storeTime.zoneId(readableDatabase);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String saveStoreTimeZoneId(String zoneId, String actorUserId) {
        SyncRelaySession syncRelaySession;
        Unit unit;
        Intrinsics.checkNotNullParameter(zoneId, "zoneId");
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        String string = StringsKt.trim((CharSequence) zoneId).toString();
        if (!StoreTime.INSTANCE.isValidZoneId(string)) {
            throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0645\u0646\u0637\u0642\u0629 \u0627\u0644\u0632\u0645\u0646\u064a\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
        }
        SQLiteDatabase writableDatabase = this.helper.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        requireManager(writableDatabase, actorUserId);
        Context appContext = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        SyncProtocolRepository syncProtocolRepository = new SyncProtocolRepository(appContext, null, 2, null);
        if (syncProtocolRepository.isConfiguredSecondary()) {
            throw new IllegalArgumentException("\u062a\u063a\u064a\u064a\u0631 \u0627\u0644\u0645\u0646\u0637\u0642\u0629 \u0627\u0644\u0632\u0645\u0646\u064a\u0629 \u0645\u0633\u0645\u0648\u062d \u0645\u0646 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0641\u0642\u0637".toString());
        }
        writableDatabase.beginTransaction();
        try {
            String strZoneId = StoreTime.INSTANCE.zoneId(writableDatabase);
            if (Intrinsics.areEqual(strZoneId, string)) {
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
                try {
                    Result.Companion companion = Result.INSTANCE;
                    StoreRepository storeRepository = this;
                } catch (Throwable th) {
                }
                return string;
            }
            String strTimestamp = timestamp();
            StoreTime.INSTANCE.setZoneId(writableDatabase, string, strTimestamp);
            insertAudit(writableDatabase, actorUserId, "UPDATE_STORE_TIME_ZONE", "STORE_SETTINGS", "store_time_zone", "{\"time_zone\":\"" + jsonEscape(strZoneId) + "\"}", "{\"time_zone\":\"" + jsonEscape(string) + "\"}", strTimestamp);
            String string2 = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
            syncProtocolRepository.enqueueLocalOperation(writableDatabase, actorUserId, string2, "UPDATE_STORE_TIME_ZONE", "STORE_SETTINGS", "store_time_zone", "UPDATE", "{\"time_zone\":\"" + jsonEscape(string) + "\"}");
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            try {
                Result.Companion companion2 = Result.INSTANCE;
                StoreRepository storeRepository2 = this;
            } catch (Throwable th2) {
            }
            return string;
        } finally {
            writableDatabase.endTransaction();
            try {
                Result.Companion companion3 = Result.INSTANCE;
                SyncRelaySession.INSTANCE.flushPending();
                Result.m381constructorimpl(Unit.INSTANCE);
            } catch (Throwable th3) {
                Result.Companion companion4 = Result.INSTANCE;
                Result.m381constructorimpl(ResultKt.createFailure(th3));
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final long saveExchangeRate(BigDecimal rateSypPerUsd, String actorUserId) {
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        String str;
        Intrinsics.checkNotNullParameter(rateSypPerUsd, "rateSypPerUsd");
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        if (rateSypPerUsd.signum() <= 0) {
            throw new IllegalArgumentException("\u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u064a\u062c\u0628 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0623\u0643\u0628\u0631 \u0645\u0646 \u0635\u0641\u0631".toString());
        }
        long jLongValueExact = rateSypPerUsd.multiply(BigDecimal.valueOf(1000L)).setScale(0, RoundingMode.HALF_UP).longValueExact();
        if (jLongValueExact <= 0) {
            throw new IllegalArgumentException("\u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
        }
        SQLiteDatabase writableDatabase = this.helper.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        Context appContext = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        SyncProtocolRepository syncProtocolRepository = new SyncProtocolRepository(appContext, null, 2, null);
        if (syncProtocolRepository.isConfiguredSecondary()) {
            throw new IllegalArgumentException("\u062a\u063a\u064a\u064a\u0631 \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0645\u062a\u0627\u062d \u0645\u0646 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0641\u0642\u0637".toString());
        }
        PermissionPolicy.INSTANCE.require(writableDatabase, actorUserId, PermissionPolicy.EXCHANGE_RATE_MANAGE);
        String strTimestamp = timestamp();
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        writableDatabase.beginTransaction();
        try {
            PermissionPolicy.INSTANCE.require(writableDatabase, actorUserId, PermissionPolicy.EXCHANGE_RATE_MANAGE);
            long currentExchangeRateMilli = getCurrentExchangeRateMilli();
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", string);
            contentValues.put("currency_code", "USD");
            contentValues.put("rate_syp_per_usd_milli", Long.valueOf(jLongValueExact));
            contentValues.put("effective_at", strTimestamp);
            contentValues.put("created_at", strTimestamp);
            contentValues.put("created_by", actorUserId);
            writableDatabase.insertOrThrow("exchange_rates", null, contentValues);
            String strValueOf = String.valueOf(jLongValueExact);
            if (currentExchangeRateMilli > 0) {
                try {
                    str = "{\"rate_syp_per_usd_milli\":" + currentExchangeRateMilli + "}";
                } catch (Throwable th2) {
                    th = th2;
                    sQLiteDatabase = writableDatabase;
                    sQLiteDatabase.endTransaction();
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        SyncRelaySession.INSTANCE.flushPending();
                        Result.m381constructorimpl(Unit.INSTANCE);
                        throw th;
                    } catch (Throwable th3) {
                        Result.Companion companion2 = Result.INSTANCE;
                        Result.m381constructorimpl(ResultKt.createFailure(th3));
                        throw th;
                    }
                }
            } else {
                str = null;
            }
            sQLiteDatabase = writableDatabase;
            try {
                insertAudit(writableDatabase, actorUserId, "UPDATE_EXCHANGE_RATE", "EXCHANGE_RATE", strValueOf, str, "{\"rate_syp_per_usd_milli\":" + jLongValueExact + "}", strTimestamp);
                String string2 = new JSONObject().put("id", string).put("currency_code", "USD").put("rate_syp_per_usd_milli", jLongValueExact).put("effective_at", strTimestamp).put("created_at", strTimestamp).toString();
                Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                syncProtocolRepository.enqueueLocalOperation(sQLiteDatabase, actorUserId, "exchange-rate-" + string, "SET_EXCHANGE_RATE", "EXCHANGE_RATE", string, "CREATE", string2);
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
                try {
                    Result.Companion companion3 = Result.INSTANCE;
                    StoreRepository storeRepository = this;
                    SyncRelaySession.INSTANCE.flushPending();
                    Result.m381constructorimpl(Unit.INSTANCE);
                } catch (Throwable th4) {
                    Result.Companion companion4 = Result.INSTANCE;
                    Result.m381constructorimpl(ResultKt.createFailure(th4));
                }
                return jLongValueExact;
            } catch (Throwable th5) {
                th = th5;
                th = th;
                sQLiteDatabase.endTransaction();
                Result.Companion companion5 = Result.INSTANCE;
                SyncRelaySession.INSTANCE.flushPending();
                Result.m381constructorimpl(Unit.INSTANCE);
                throw th;
            }
        } catch (Throwable th6) {
            th = th6;
            sQLiteDatabase = writableDatabase;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final long addCategory(String name, String actorUserId) {
        SQLiteDatabase sQLiteDatabase;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        requirePrimaryCatalogAuthority();
        String string = StringsKt.trim((CharSequence) name).toString();
        if (string.length() <= 0) {
            throw new IllegalArgumentException("\u0627\u0633\u0645 \u0627\u0644\u062a\u0635\u0646\u064a\u0641 \u0645\u0637\u0644\u0648\u0628".toString());
        }
        SQLiteDatabase writableDatabase = this.helper.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        requireManager(writableDatabase, actorUserId);
        String strTimestamp = timestamp();
        writableDatabase.beginTransaction();
        try {
            requireManager(writableDatabase, actorUserId);
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", string);
            contentValues.put("created_at", strTimestamp);
            long jInsertOrThrow = writableDatabase.insertOrThrow("categories", null, contentValues);
            sQLiteDatabase = writableDatabase;
            try {
                insertAudit(writableDatabase, actorUserId, "CREATE_CATEGORY", "CATEGORY", String.valueOf(jInsertOrThrow), null, "{\"name\":\"" + jsonEscape(string) + "\"}", strTimestamp);
                Context appContext = this.appContext;
                Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
                SyncProtocolRepository syncProtocolRepository = new SyncProtocolRepository(appContext, null, 2, null);
                String string2 = UUID.randomUUID().toString();
                Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                Locale ROOT = Locale.ROOT;
                Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
                String lowerCase = string.toLowerCase(ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                syncProtocolRepository.enqueueLocalOperation(sQLiteDatabase, actorUserId, string2, "CREATE_CATEGORY", "CATEGORY", lowerCase, "CREATE", "{\"name\":\"" + jsonEscape(string) + "\",\"primary_category_id\":" + jInsertOrThrow + ",\"created_at\":\"" + strTimestamp + "\"}");
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
                try {
                    Result.Companion companion = Result.INSTANCE;
                    StoreRepository storeRepository = this;
                    SyncRelaySession.INSTANCE.flushPending();
                    Result.m381constructorimpl(Unit.INSTANCE);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    Result.m381constructorimpl(ResultKt.createFailure(th));
                }
                return jInsertOrThrow;
            } catch (Throwable th2) {
                th = th2;
                Throwable th3 = th;
                sQLiteDatabase.endTransaction();
                try {
                    Result.Companion companion3 = Result.INSTANCE;
                    SyncRelaySession.INSTANCE.flushPending();
                    Result.m381constructorimpl(Unit.INSTANCE);
                    throw th3;
                } catch (Throwable th4) {
                    Result.Companion companion4 = Result.INSTANCE;
                    Result.m381constructorimpl(ResultKt.createFailure(th4));
                    throw th3;
                }
            }
        } catch (Throwable th5) {
            th = th5;
            sQLiteDatabase = writableDatabase;
        }
    }

    public final Long resolveSecondaryCategoryId(String primaryDeviceId, long primaryCategoryId) throws IOException {
        Intrinsics.checkNotNullParameter(primaryDeviceId, "primaryDeviceId");
        if (StringsKt.isBlank(primaryDeviceId) || primaryCategoryId <= 0) {
            throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u062c\u0647\u0627\u0632 \u0623\u0648 \u0627\u0644\u062a\u0635\u0646\u064a\u0641 \u0627\u0644\u0623\u0635\u0644\u064a \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
        }
        SQLiteDatabase readableDatabase = this.helper.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        SyncDeviceIdentity.Companion companion = SyncDeviceIdentity.INSTANCE;
        Context appContext = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        String orCreate = companion.getOrCreate(appContext);
        Cursor cursorRawQuery = readableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            String string = cursor.moveToFirst() ? cursor.getString(0) : null;
            CloseableKt.closeFinally(cursorRawQuery, null);
            Cursor cursorRawQuery2 = readableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='authority_device_id'", (String[]) null);
            try {
                Cursor cursor2 = cursorRawQuery2;
                String string2 = cursor2.moveToFirst() ? cursor2.getString(0) : null;
                CloseableKt.closeFinally(cursorRawQuery2, null);
                if (!Intrinsics.areEqual(string, "SECONDARY") || !Intrinsics.areEqual(string2, primaryDeviceId) || Intrinsics.areEqual(string2, orCreate)) {
                    throw new IllegalArgumentException("\u0631\u0628\u0637 \u0627\u0644\u062a\u0635\u0646\u064a\u0641\u0627\u062a \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0627\u0644\u0645\u0639\u062a\u0645\u062f".toString());
                }
                Cursor cursorRawQuery3 = readableDatabase.rawQuery("SELECT secondary_category_id FROM sync_category_mappings\n               WHERE primary_device_id=? AND primary_category_id=?", new String[]{primaryDeviceId, String.valueOf(primaryCategoryId)});
                try {
                    Cursor cursor3 = cursorRawQuery3;
                    Long lValueOf = cursor3.moveToFirst() ? Long.valueOf(cursor3.getLong(0)) : null;
                    CloseableKt.closeFinally(cursorRawQuery3, null);
                    return lValueOf;
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
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 14311. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
        */
    public final java.lang.String saveProduct(java.lang.String r39, java.lang.String r40, java.lang.String r41, java.lang.String r42, java.lang.Long r43, long r44, long r46, long r48, boolean r50, double r51, java.lang.String r53) throws android.database.sqlite.SQLiteConstraintException, java.lang.IllegalArgumentException {
        /*
            Method dump skipped, instruction units count: 1431
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hazem.dailystore.data.StoreRepository.saveProduct(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, long, long, long, boolean, double, java.lang.String):java.lang.String");
    }

    /* JADX WARN: Code duplicated, block: B:100:0x02c7  */
    /* JADX WARN: Code duplicated, block: B:102:0x02ca A[Catch: all -> 0x02c2, TRY_LEAVE, TryCatch #2 {all -> 0x02c2, blocks: (B:93:0x02ab, B:95:0x02b7, B:102:0x02ca, B:105:0x02de, B:108:0x02e4, B:109:0x02fe, B:115:0x0304, B:116:0x0307, B:119:0x030e, B:121:0x031a, B:126:0x0327, B:129:0x033d, B:144:0x036f, B:146:0x037b, B:165:0x03b8, B:170:0x0454, B:172:0x0460, B:180:0x047a, B:132:0x0343, B:133:0x035d, B:139:0x0363, B:140:0x0366, B:136:0x0360, B:112:0x0301, B:127:0x0333, B:103:0x02d6), top: B:277:0x02ab, inners: #0, #1, #14, #16 }] */
    /* JADX WARN: Code duplicated, block: B:107:0x02e3  */
    /* JADX WARN: Code duplicated, block: B:124:0x0324  */
    /* JADX WARN: Code duplicated, block: B:126:0x0327 A[Catch: all -> 0x02c2, TRY_LEAVE, TryCatch #2 {all -> 0x02c2, blocks: (B:93:0x02ab, B:95:0x02b7, B:102:0x02ca, B:105:0x02de, B:108:0x02e4, B:109:0x02fe, B:115:0x0304, B:116:0x0307, B:119:0x030e, B:121:0x031a, B:126:0x0327, B:129:0x033d, B:144:0x036f, B:146:0x037b, B:165:0x03b8, B:170:0x0454, B:172:0x0460, B:180:0x047a, B:132:0x0343, B:133:0x035d, B:139:0x0363, B:140:0x0366, B:136:0x0360, B:112:0x0301, B:127:0x0333, B:103:0x02d6), top: B:277:0x02ab, inners: #0, #1, #14, #16 }] */
    /* JADX WARN: Code duplicated, block: B:131:0x0342  */
    /* JADX WARN: Code duplicated, block: B:141:0x0367  */
    /* JADX WARN: Code duplicated, block: B:151:0x0388  */
    /* JADX WARN: Code duplicated, block: B:160:0x03af  */
    /* JADX WARN: Code duplicated, block: B:165:0x03b8 A[Catch: all -> 0x02c2, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x02c2, blocks: (B:93:0x02ab, B:95:0x02b7, B:102:0x02ca, B:105:0x02de, B:108:0x02e4, B:109:0x02fe, B:115:0x0304, B:116:0x0307, B:119:0x030e, B:121:0x031a, B:126:0x0327, B:129:0x033d, B:144:0x036f, B:146:0x037b, B:165:0x03b8, B:170:0x0454, B:172:0x0460, B:180:0x047a, B:132:0x0343, B:133:0x035d, B:139:0x0363, B:140:0x0366, B:136:0x0360, B:112:0x0301, B:127:0x0333, B:103:0x02d6), top: B:277:0x02ab, inners: #0, #1, #14, #16 }] */
    /* JADX WARN: Code duplicated, block: B:167:0x03cb A[Catch: all -> 0x06bc, TRY_ENTER, TryCatch #13 {all -> 0x06bc, blocks: (B:82:0x0260, B:83:0x026a, B:86:0x0276, B:89:0x02a0, B:91:0x02a5, B:117:0x0308, B:142:0x0369, B:152:0x038b, B:162:0x03b1, B:168:0x044e, B:178:0x0470, B:211:0x0564, B:182:0x048f, B:167:0x03cb), top: B:297:0x0260 }] */
    /* JADX WARN: Code duplicated, block: B:177:0x046d  */
    /* JADX WARN: Code duplicated, block: B:180:0x047a A[Catch: all -> 0x02c2, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x02c2, blocks: (B:93:0x02ab, B:95:0x02b7, B:102:0x02ca, B:105:0x02de, B:108:0x02e4, B:109:0x02fe, B:115:0x0304, B:116:0x0307, B:119:0x030e, B:121:0x031a, B:126:0x0327, B:129:0x033d, B:144:0x036f, B:146:0x037b, B:165:0x03b8, B:170:0x0454, B:172:0x0460, B:180:0x047a, B:132:0x0343, B:133:0x035d, B:139:0x0363, B:140:0x0366, B:136:0x0360, B:112:0x0301, B:127:0x0333, B:103:0x02d6), top: B:277:0x02ab, inners: #0, #1, #14, #16 }] */
    /* JADX WARN: Code duplicated, block: B:182:0x048f A[Catch: all -> 0x06bc, TRY_ENTER, TRY_LEAVE, TryCatch #13 {all -> 0x06bc, blocks: (B:82:0x0260, B:83:0x026a, B:86:0x0276, B:89:0x02a0, B:91:0x02a5, B:117:0x0308, B:142:0x0369, B:152:0x038b, B:162:0x03b1, B:168:0x044e, B:178:0x0470, B:211:0x0564, B:182:0x048f, B:167:0x03cb), top: B:297:0x0260 }] */
    /* JADX WARN: Code duplicated, block: B:193:0x04b6  */
    /* JADX WARN: Code duplicated, block: B:198:0x04d5 A[Catch: all -> 0x05ba, TRY_ENTER, TRY_LEAVE, TryCatch #12 {all -> 0x05ba, blocks: (B:194:0x04c0, B:203:0x04f0, B:209:0x04fe, B:198:0x04d5), top: B:295:0x04c0 }] */
    /* JADX WARN: Code duplicated, block: B:200:0x04db A[Catch: all -> 0x04ac, TRY_ENTER, TRY_LEAVE, TryCatch #6 {all -> 0x04ac, blocks: (B:196:0x04c6, B:200:0x04db, B:186:0x049f), top: B:284:0x04c6 }] */
    /* JADX WARN: Code duplicated, block: B:202:0x04ec  */
    /* JADX WARN: Code duplicated, block: B:280:0x03a1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:284:0x04c6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:288:0x0495 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:320:0x02e4 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:321:0x0343 A[SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:167:0x03cb, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v1, types: [com.hazem.dailystore.data.SyncProtocolRepository] */
    /* JADX WARN: Type inference failed for: r12v13, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r13v1, types: [long] */
    /* JADX WARN: Type inference failed for: r16v2 */
    /* JADX WARN: Type inference failed for: r16v5 */
    /* JADX WARN: Type inference failed for: r31v0, types: [com.hazem.dailystore.data.StoreRepository] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.Object, net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r5v10, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r5v9, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v19 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v20 */
    /* JADX WARN: Type inference failed for: r9v21 */
    /* JADX WARN: Type inference failed for: r9v3, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final int importProducts(List<ProductImportRow> rows, long exchangeRateMilli, String actorUserId) {
        Throwable th;
        ?? r9;
        String str;
        ProductImportRow productImportRow;
        String string;
        String str2;
        String barcode;
        String string2;
        String category;
        String string3;
        Cursor cursorRawQuery;
        Throwable th2;
        Cursor cursor;
        Long lValueOf;
        long jLongValue;
        String unit;
        String string4;
        long jResolveImportUnit;
        long j;
        long jSypToUsdCents;
        long jLongValue2;
        long j2;
        String str3;
        Cursor cursorRawQuery2;
        boolean zMoveToFirst;
        Cursor cursorRawQuery3;
        boolean zMoveToFirst2;
        String string5;
        String string6;
        long j3 = exchangeRateMilli;
        Intrinsics.checkNotNullParameter(rows, "rows");
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        requirePrimaryCatalogAuthority();
        if (StringsKt.isBlank(actorUserId)) {
            throw new IllegalArgumentException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629 \u0644\u062a\u0633\u062c\u064a\u0644 \u0639\u0645\u0644\u064a\u0629 \u0627\u0644\u0627\u0633\u062a\u064a\u0631\u0627\u062f".toString());
        }
        SQLiteDatabase readableDatabase = this.helper.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        requireManager(readableDatabase, actorUserId);
        if (rows.isEmpty()) {
            throw new IllegalArgumentException("\u0644\u0627 \u062a\u0648\u062c\u062f \u0623\u0635\u0646\u0627\u0641 \u0644\u0644\u0627\u0633\u062a\u064a\u0631\u0627\u062f".toString());
        }
        List<ProductImportRow> list = rows;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (((ProductImportRow) it.next()).getUsesSypPrices()) {
                    if (j3 > 0) {
                        break;
                    }
                    throw new IllegalArgumentException("\u0627\u0644\u0645\u0644\u0641 \u064a\u062d\u062a\u0648\u064a \u0623\u0633\u0639\u0627\u0631\u0627\u064b \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629\u061b \u0633\u062c\u0651\u0644 \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0623\u0648\u0644\u0627\u064b \u0648\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u062e\u0645\u064a\u0646 \u0627\u0644\u062a\u062d\u0648\u064a\u0644".toString());
                }
            }
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        LinkedHashSet linkedHashSet3 = new LinkedHashSet();
        Iterator it2 = list.iterator();
        while (true) {
            String str4 = "toLowerCase(...)";
            String str5 = "ROOT";
            if (it2.hasNext()) {
                ProductImportRow productImportRow2 = (ProductImportRow) it2.next();
                String string7 = StringsKt.trim((CharSequence) productImportRow2.getName()).toString();
                Locale ROOT = Locale.ROOT;
                Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
                String lowerCase = string7.toLowerCase(ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                if (!linkedHashSet.add(lowerCase)) {
                    throw new IllegalArgumentException(("\u0627\u0633\u0645 \u0635\u0646\u0641 \u0645\u0643\u0631\u0631 \u062f\u0627\u062e\u0644 \u0627\u0644\u0645\u0644\u0641: " + productImportRow2.getName() + " (\u0627\u0644\u0635\u0641 " + productImportRow2.getRowNumber() + ")").toString());
                }
                String sku = productImportRow2.getSku();
                if (sku != null && (string6 = StringsKt.trim((CharSequence) sku).toString()) != null) {
                    if (string6.length() <= 0) {
                        string6 = null;
                    }
                    if (string6 != null) {
                        Locale ROOT2 = Locale.ROOT;
                        Intrinsics.checkNotNullExpressionValue(ROOT2, "ROOT");
                        String lowerCase2 = string6.toLowerCase(ROOT2);
                        Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                        if (!linkedHashSet2.add(lowerCase2)) {
                            throw new IllegalArgumentException(("\u0631\u0645\u0632 SKU \u0645\u0643\u0631\u0631 \u062f\u0627\u062e\u0644 \u0627\u0644\u0645\u0644\u0641: " + string6).toString());
                        }
                        Unit unit2 = Unit.INSTANCE;
                        Unit unit3 = Unit.INSTANCE;
                    }
                }
                String barcode2 = productImportRow2.getBarcode();
                if (barcode2 != null && (string5 = StringsKt.trim((CharSequence) barcode2).toString()) != null) {
                    String str6 = string5.length() > 0 ? string5 : null;
                    if (str6 != null) {
                        if (!linkedHashSet3.add(str6)) {
                            throw new IllegalArgumentException(("\u0628\u0627\u0631\u0643\u0648\u062f \u0645\u0643\u0631\u0631 \u062f\u0627\u062e\u0644 \u0627\u0644\u0645\u0644\u0641: " + str6).toString());
                        }
                        Unit unit4 = Unit.INSTANCE;
                        Unit unit5 = Unit.INSTANCE;
                    }
                }
                if (productImportRow2.getSalePriceSyp() == null && productImportRow2.getSalePriceUsdCents() == null) {
                    throw new IllegalArgumentException(("\u0633\u0639\u0631 \u0627\u0644\u0628\u064a\u0639 \u0645\u0641\u0642\u0648\u062f \u0641\u064a \u0627\u0644\u0635\u0641 " + productImportRow2.getRowNumber()).toString());
                }
                if (productImportRow2.getPurchaseCostSyp() != null && productImportRow2.getPurchaseCostUsdCents() != null && BigDecimal.valueOf(CurrencyConverter.INSTANCE.sypToUsdCents(productImportRow2.getPurchaseCostSyp().longValue(), j3)).subtract(BigDecimal.valueOf(productImportRow2.getPurchaseCostUsdCents().longValue())).abs().compareTo(BigDecimal.ONE) > 0) {
                    throw new IllegalArgumentException(("\u062a\u0643\u0644\u0641\u0629 \u0627\u0644\u0634\u0631\u0627\u0621 \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0648\u0627\u0644\u062f\u0648\u0644\u0627\u0631 \u063a\u064a\u0631 \u0645\u062a\u0637\u0627\u0628\u0642\u0629 \u0641\u064a \u0627\u0644\u0635\u0641 " + productImportRow2.getRowNumber()).toString());
                }
                if (productImportRow2.getSalePriceSyp() != null && productImportRow2.getSalePriceUsdCents() != null && BigDecimal.valueOf(CurrencyConverter.INSTANCE.sypToUsdCents(productImportRow2.getSalePriceSyp().longValue(), j3)).subtract(BigDecimal.valueOf(productImportRow2.getSalePriceUsdCents().longValue())).abs().compareTo(BigDecimal.ONE) > 0) {
                    throw new IllegalArgumentException(("\u0633\u0639\u0631 \u0627\u0644\u0628\u064a\u0639 \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0648\u0627\u0644\u062f\u0648\u0644\u0627\u0631 \u063a\u064a\u0631 \u0645\u062a\u0637\u0627\u0628\u0642 \u0641\u064a \u0627\u0644\u0635\u0641 " + productImportRow2.getRowNumber()).toString());
                }
            } else {
                ?? writableDatabase = this.helper.getWritableDatabase();
                Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
                String strTimestamp = timestamp();
                writableDatabase.beginTransaction();
                try {
                    requireManager(writableDatabase, actorUserId);
                    Iterator it3 = rows.iterator();
                    writableDatabase = writableDatabase;
                    writableDatabase = j3;
                    while (true) {
                        if (it3.hasNext()) {
                            productImportRow = (ProductImportRow) it3.next();
                            string = StringsKt.trim((CharSequence) productImportRow.getName()).toString();
                            Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT 1 FROM products WHERE name=? COLLATE NOCASE LIMIT 1", new String[]{string});
                            try {
                                boolean zMoveToFirst3 = cursorRawQuery4.moveToFirst();
                                CloseableKt.closeFinally(cursorRawQuery4, null);
                                if (zMoveToFirst3) {
                                    throw new IllegalArgumentException(("\u0627\u0644\u0635\u0646\u0641 \u0645\u0648\u062c\u0648\u062f \u0645\u0633\u0628\u0642\u0627\u064b: " + string + "\u061b \u0623\u0648\u0642\u0641 \u0627\u0644\u0627\u0633\u062a\u064a\u0631\u0627\u062f \u0644\u062a\u062c\u0646\u0628 \u0625\u0646\u0634\u0627\u0621 \u062a\u0643\u0631\u0627\u0631").toString());
                                }
                                String sku2 = productImportRow.getSku();
                                if (sku2 != null) {
                                    try {
                                        String string8 = StringsKt.trim((CharSequence) sku2).toString();
                                        if (string8 == null || string8.length() <= 0) {
                                            str2 = null;
                                        } else {
                                            str2 = string8;
                                        }
                                        if (str2 != null) {
                                            cursorRawQuery3 = writableDatabase.rawQuery("SELECT 1 FROM products WHERE sku=? LIMIT 1", new String[]{str2});
                                            try {
                                                zMoveToFirst2 = cursorRawQuery3.moveToFirst();
                                                CloseableKt.closeFinally(cursorRawQuery3, null);
                                                if (!zMoveToFirst2) {
                                                    throw new IllegalArgumentException(("\u0631\u0645\u0632 SKU \u0645\u0633\u062a\u062e\u062f\u0645 \u0645\u0633\u0628\u0642\u0627\u064b: " + str2).toString());
                                                }
                                            } catch (Throwable th3) {
                                                try {
                                                    throw th3;
                                                } catch (Throwable th4) {
                                                    CloseableKt.closeFinally(cursorRawQuery3, th3);
                                                    throw th4;
                                                }
                                            }
                                        }
                                        barcode = productImportRow.getBarcode();
                                        if (barcode != null || (string2 = StringsKt.trim((CharSequence) barcode).toString()) == null || string2.length() <= 0) {
                                            string2 = null;
                                        }
                                        if (string2 != null) {
                                            cursorRawQuery2 = writableDatabase.rawQuery("SELECT 1 FROM products WHERE barcode=? LIMIT 1", new String[]{string2});
                                            try {
                                                zMoveToFirst = cursorRawQuery2.moveToFirst();
                                                CloseableKt.closeFinally(cursorRawQuery2, null);
                                                if (!zMoveToFirst) {
                                                    throw new IllegalArgumentException(("\u0627\u0644\u0628\u0627\u0631\u0643\u0648\u062f \u0645\u0633\u062a\u062e\u062f\u0645 \u0645\u0633\u0628\u0642\u0627\u064b: " + string2).toString());
                                                }
                                            } catch (Throwable th5) {
                                                try {
                                                    throw th5;
                                                } catch (Throwable th6) {
                                                    CloseableKt.closeFinally(cursorRawQuery2, th5);
                                                    throw th6;
                                                }
                                            }
                                        }
                                        category = productImportRow.getCategory();
                                        if (category != null || (string3 = StringsKt.trim((CharSequence) category).toString()) == null) {
                                            string3 = "\u063a\u064a\u0631 \u0645\u0635\u0646\u0641";
                                        } else {
                                            if (string3.length() <= 0) {
                                                string3 = null;
                                            }
                                            if (string3 == null) {
                                                string3 = "\u063a\u064a\u0631 \u0645\u0635\u0646\u0641";
                                            }
                                        }
                                        cursorRawQuery = writableDatabase.rawQuery("SELECT id FROM categories WHERE name=? COLLATE NOCASE LIMIT 1", new String[]{string3});
                                        try {
                                            cursor = cursorRawQuery;
                                            if (cursor.moveToFirst()) {
                                                try {
                                                    lValueOf = Long.valueOf(cursor.getLong(0));
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
                                                lValueOf = null;
                                            }
                                            CloseableKt.closeFinally(cursorRawQuery, null);
                                            if (lValueOf != null) {
                                                jLongValue = lValueOf.longValue();
                                            } else {
                                                ContentValues contentValues = new ContentValues();
                                                contentValues.put("name", string3);
                                                contentValues.put("created_at", strTimestamp);
                                                long jInsertOrThrow = writableDatabase.insertOrThrow("categories", null, contentValues);
                                                Context appContext = this.appContext;
                                                Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
                                                Locale locale = Locale.ROOT;
                                                Intrinsics.checkNotNullExpressionValue(locale, str5);
                                                String lowerCase3 = string3.toLowerCase(locale);
                                                Intrinsics.checkNotNullExpressionValue(lowerCase3, str4);
                                                String string9 = new JSONObject().put("name", string3).put("primary_category_id", jInsertOrThrow).put("created_at", strTimestamp).toString();
                                                Intrinsics.checkNotNullExpressionValue(string9, "toString(...)");
                                                new SyncProtocolRepository(appContext, null, 2, null).enqueueLocalOperation(writableDatabase, actorUserId, "category-import-" + jInsertOrThrow, "CREATE_CATEGORY", "CATEGORY", lowerCase3, "CREATE", string9);
                                                jLongValue = jInsertOrThrow;
                                            }
                                            unit = productImportRow.getUnit();
                                            if (unit != null || (string4 = StringsKt.trim((CharSequence) unit).toString()) == null) {
                                                string4 = "\u0642\u0637\u0639\u0629";
                                            } else {
                                                if (string4.length() <= 0) {
                                                    string4 = null;
                                                }
                                                if (string4 == null) {
                                                    string4 = "\u0642\u0637\u0639\u0629";
                                                }
                                            }
                                            jResolveImportUnit = resolveImportUnit(writableDatabase, string4);
                                            if (productImportRow.getPurchaseCostUsdCents() != null) {
                                                j = exchangeRateMilli;
                                                jSypToUsdCents = productImportRow.getPurchaseCostUsdCents().longValue();
                                            } else if (productImportRow.getPurchaseCostSyp() != null) {
                                                try {
                                                    j = exchangeRateMilli;
                                                    jSypToUsdCents = CurrencyConverter.INSTANCE.sypToUsdCents(productImportRow.getPurchaseCostSyp().longValue(), j);
                                                } catch (Throwable th9) {
                                                    th = th9;
                                                    writableDatabase = writableDatabase;
                                                    th = th;
                                                    r9 = writableDatabase;
                                                    r9.endTransaction();
                                                    Result.Companion companion = Result.INSTANCE;
                                                    SyncRelaySession.INSTANCE.flushPending();
                                                    Result.m381constructorimpl(Unit.INSTANCE);
                                                    throw th;
                                                }
                                            } else {
                                                j = exchangeRateMilli;
                                                jSypToUsdCents = 0;
                                            }
                                            try {
                                                if (productImportRow.getSalePriceUsdCents() == null) {
                                                    if (productImportRow.getSalePriceSyp() != null) {
                                                        j2 = jLongValue;
                                                        jLongValue2 = CurrencyConverter.INSTANCE.sypToUsdCents(productImportRow.getSalePriceSyp().longValue(), j);
                                                    } else {
                                                        j2 = jLongValue;
                                                        jLongValue2 = 0;
                                                    }
                                                } else {
                                                    try {
                                                        long j4 = jLongValue;
                                                        jLongValue2 = productImportRow.getSalePriceUsdCents().longValue();
                                                        j2 = j4;
                                                    } catch (Throwable th10) {
                                                        th = th10;
                                                        th = th;
                                                        r9 = writableDatabase;
                                                        r9.endTransaction();
                                                        Result.Companion companion2 = Result.INSTANCE;
                                                        SyncRelaySession.INSTANCE.flushPending();
                                                        Result.m381constructorimpl(Unit.INSTANCE);
                                                        throw th;
                                                    }
                                                }
                                                if ((!productImportRow.getTracksInventory() && jSypToUsdCents <= 0) || jLongValue2 <= 0) {
                                                    break;
                                                }
                                                String string10 = UUID.randomUUID().toString();
                                                Intrinsics.checkNotNullExpressionValue(string10, "toString(...)");
                                                ContentValues contentValues2 = new ContentValues();
                                                contentValues2.put("id", string10);
                                                String str7 = str5;
                                                putNullable(contentValues2, "barcode", string2);
                                                putNullable(contentValues2, "sku", str2);
                                                contentValues2.put("name", r17);
                                                contentValues2.put("category_id", Long.valueOf(j2));
                                                contentValues2.put("unit_id", Long.valueOf(jResolveImportUnit));
                                                contentValues2.put("purchase_cost_usd_cents", Long.valueOf(jSypToUsdCents));
                                                contentValues2.put("sale_price_usd_cents", Long.valueOf(jLongValue2));
                                                contentValues2.put("track_inventory", Integer.valueOf(productImportRow.getTracksInventory() ? 1 : 0));
                                                String str8 = strTimestamp;
                                                contentValues2.put("created_at", str8);
                                                contentValues2.put("updated_at", str8);
                                                ?? r5 = writableDatabase;
                                                r5.insertOrThrow("products", null, contentValues2);
                                                writableDatabase = r5;
                                                String str9 = str4;
                                                try {
                                                    enqueueProductSync(r5, actorUserId, string10, "CREATE", false);
                                                    strTimestamp = str8;
                                                    writableDatabase = writableDatabase;
                                                    str4 = str9;
                                                    writableDatabase = j;
                                                    str5 = str7;
                                                } catch (Throwable th11) {
                                                    th = th11;
                                                    th = th;
                                                    r9 = writableDatabase;
                                                    r9.endTransaction();
                                                    Result.Companion companion3 = Result.INSTANCE;
                                                    SyncRelaySession.INSTANCE.flushPending();
                                                    Result.m381constructorimpl(Unit.INSTANCE);
                                                    throw th;
                                                }
                                            } catch (Throwable th12) {
                                                th = th12;
                                                writableDatabase = writableDatabase;
                                                th = th;
                                                r9 = writableDatabase;
                                                r9.endTransaction();
                                                Result.Companion companion4 = Result.INSTANCE;
                                                SyncRelaySession.INSTANCE.flushPending();
                                                Result.m381constructorimpl(Unit.INSTANCE);
                                                throw th;
                                            }
                                        } catch (Throwable th13) {
                                            th2 = th13;
                                        }
                                    } catch (Throwable th14) {
                                        th = th14;
                                        r9 = writableDatabase;
                                    }
                                } else {
                                    str2 = null;
                                    if (str2 != null) {
                                        cursorRawQuery3 = writableDatabase.rawQuery("SELECT 1 FROM products WHERE sku=? LIMIT 1", new String[]{str2});
                                        zMoveToFirst2 = cursorRawQuery3.moveToFirst();
                                        CloseableKt.closeFinally(cursorRawQuery3, null);
                                        if (!zMoveToFirst2) {
                                            throw new IllegalArgumentException(("\u0631\u0645\u0632 SKU \u0645\u0633\u062a\u062e\u062f\u0645 \u0645\u0633\u0628\u0642\u0627\u064b: " + str2).toString());
                                        }
                                    }
                                    barcode = productImportRow.getBarcode();
                                    if (barcode != null) {
                                        string2 = null;
                                    } else {
                                        string2 = null;
                                    }
                                    if (string2 != null) {
                                        cursorRawQuery2 = writableDatabase.rawQuery("SELECT 1 FROM products WHERE barcode=? LIMIT 1", new String[]{string2});
                                        zMoveToFirst = cursorRawQuery2.moveToFirst();
                                        CloseableKt.closeFinally(cursorRawQuery2, null);
                                        if (!zMoveToFirst) {
                                            throw new IllegalArgumentException(("\u0627\u0644\u0628\u0627\u0631\u0643\u0648\u062f \u0645\u0633\u062a\u062e\u062f\u0645 \u0645\u0633\u0628\u0642\u0627\u064b: " + string2).toString());
                                        }
                                    }
                                    category = productImportRow.getCategory();
                                    if (category != null) {
                                        string3 = "\u063a\u064a\u0631 \u0645\u0635\u0646\u0641";
                                    } else {
                                        string3 = "\u063a\u064a\u0631 \u0645\u0635\u0646\u0641";
                                    }
                                    cursorRawQuery = writableDatabase.rawQuery("SELECT id FROM categories WHERE name=? COLLATE NOCASE LIMIT 1", new String[]{string3});
                                    cursor = cursorRawQuery;
                                    if (cursor.moveToFirst()) {
                                        lValueOf = Long.valueOf(cursor.getLong(0));
                                    } else {
                                        lValueOf = null;
                                    }
                                    CloseableKt.closeFinally(cursorRawQuery, null);
                                    if (lValueOf != null) {
                                        jLongValue = lValueOf.longValue();
                                    } else {
                                        ContentValues contentValues3 = new ContentValues();
                                        contentValues3.put("name", string3);
                                        contentValues3.put("created_at", strTimestamp);
                                        long jInsertOrThrow2 = writableDatabase.insertOrThrow("categories", null, contentValues3);
                                        Context appContext2 = this.appContext;
                                        Intrinsics.checkNotNullExpressionValue(appContext2, "appContext");
                                        Locale locale2 = Locale.ROOT;
                                        Intrinsics.checkNotNullExpressionValue(locale2, str5);
                                        String lowerCase4 = string3.toLowerCase(locale2);
                                        Intrinsics.checkNotNullExpressionValue(lowerCase4, str4);
                                        String string11 = new JSONObject().put("name", string3).put("primary_category_id", jInsertOrThrow2).put("created_at", strTimestamp).toString();
                                        Intrinsics.checkNotNullExpressionValue(string11, "toString(...)");
                                        new SyncProtocolRepository(appContext2, null, 2, null).enqueueLocalOperation(writableDatabase, actorUserId, "category-import-" + jInsertOrThrow2, "CREATE_CATEGORY", "CATEGORY", lowerCase4, "CREATE", string11);
                                        jLongValue = jInsertOrThrow2;
                                    }
                                    unit = productImportRow.getUnit();
                                    if (unit != null) {
                                        string4 = "\u0642\u0637\u0639\u0629";
                                    } else {
                                        string4 = "\u0642\u0637\u0639\u0629";
                                    }
                                    jResolveImportUnit = resolveImportUnit(writableDatabase, string4);
                                    if (productImportRow.getPurchaseCostUsdCents() != null) {
                                        j = exchangeRateMilli;
                                        jSypToUsdCents = productImportRow.getPurchaseCostUsdCents().longValue();
                                    } else if (productImportRow.getPurchaseCostSyp() != null) {
                                        j = exchangeRateMilli;
                                        jSypToUsdCents = CurrencyConverter.INSTANCE.sypToUsdCents(productImportRow.getPurchaseCostSyp().longValue(), j);
                                    } else {
                                        j = exchangeRateMilli;
                                        jSypToUsdCents = 0;
                                    }
                                    if (productImportRow.getSalePriceUsdCents() == null) {
                                        long j5 = jLongValue;
                                        jLongValue2 = productImportRow.getSalePriceUsdCents().longValue();
                                        j2 = j5;
                                    } else if (productImportRow.getSalePriceSyp() != null) {
                                        j2 = jLongValue;
                                        jLongValue2 = CurrencyConverter.INSTANCE.sypToUsdCents(productImportRow.getSalePriceSyp().longValue(), j);
                                    } else {
                                        j2 = jLongValue;
                                        jLongValue2 = 0;
                                    }
                                    if (!productImportRow.getTracksInventory()) {
                                        String string12 = UUID.randomUUID().toString();
                                        Intrinsics.checkNotNullExpressionValue(string12, "toString(...)");
                                        ContentValues contentValues4 = new ContentValues();
                                        contentValues4.put("id", string12);
                                        String str10 = str5;
                                        putNullable(contentValues4, "barcode", string2);
                                        putNullable(contentValues4, "sku", str2);
                                        contentValues4.put("name", r17);
                                        contentValues4.put("category_id", Long.valueOf(j2));
                                        contentValues4.put("unit_id", Long.valueOf(jResolveImportUnit));
                                        contentValues4.put("purchase_cost_usd_cents", Long.valueOf(jSypToUsdCents));
                                        contentValues4.put("sale_price_usd_cents", Long.valueOf(jLongValue2));
                                        contentValues4.put("track_inventory", Integer.valueOf(productImportRow.getTracksInventory() ? 1 : 0));
                                        String str11 = strTimestamp;
                                        contentValues4.put("created_at", str11);
                                        contentValues4.put("updated_at", str11);
                                        ?? r6 = writableDatabase;
                                        r6.insertOrThrow("products", null, contentValues4);
                                        writableDatabase = r6;
                                        String str12 = str4;
                                        enqueueProductSync(r6, actorUserId, string12, "CREATE", false);
                                        strTimestamp = str11;
                                        writableDatabase = writableDatabase;
                                        str4 = str12;
                                        writableDatabase = j;
                                        str5 = str10;
                                    } else {
                                        String string13 = UUID.randomUUID().toString();
                                        Intrinsics.checkNotNullExpressionValue(string13, "toString(...)");
                                        ContentValues contentValues5 = new ContentValues();
                                        contentValues5.put("id", string13);
                                        String str13 = str5;
                                        putNullable(contentValues5, "barcode", string2);
                                        putNullable(contentValues5, "sku", str2);
                                        contentValues5.put("name", r17);
                                        contentValues5.put("category_id", Long.valueOf(j2));
                                        contentValues5.put("unit_id", Long.valueOf(jResolveImportUnit));
                                        contentValues5.put("purchase_cost_usd_cents", Long.valueOf(jSypToUsdCents));
                                        contentValues5.put("sale_price_usd_cents", Long.valueOf(jLongValue2));
                                        contentValues5.put("track_inventory", Integer.valueOf(productImportRow.getTracksInventory() ? 1 : 0));
                                        String str14 = strTimestamp;
                                        contentValues5.put("created_at", str14);
                                        contentValues5.put("updated_at", str14);
                                        ?? r7 = writableDatabase;
                                        r7.insertOrThrow("products", null, contentValues5);
                                        writableDatabase = r7;
                                        String str15 = str4;
                                        enqueueProductSync(r7, actorUserId, string13, "CREATE", false);
                                        strTimestamp = str14;
                                        writableDatabase = writableDatabase;
                                        str4 = str15;
                                        writableDatabase = j;
                                        str5 = str13;
                                    }
                                }
                                r9.endTransaction();
                                try {
                                    Result.Companion companion5 = Result.INSTANCE;
                                    SyncRelaySession.INSTANCE.flushPending();
                                    Result.m381constructorimpl(Unit.INSTANCE);
                                    throw th;
                                } catch (Throwable th15) {
                                    Result.Companion companion6 = Result.INSTANCE;
                                    Result.m381constructorimpl(ResultKt.createFailure(th15));
                                    throw th;
                                }
                            } catch (Throwable th16) {
                                writableDatabase = writableDatabase;
                                try {
                                    throw th16;
                                } catch (Throwable th17) {
                                    CloseableKt.closeFinally(cursorRawQuery4, th16);
                                    throw th17;
                                }
                            }
                        }
                        String str16 = strTimestamp;
                        ?? r13 = writableDatabase;
                        ?? r10 = writableDatabase;
                        String string14 = UUID.randomUUID().toString();
                        Intrinsics.checkNotNullExpressionValue(string14, "toString(...)");
                        List<ProductImportRow> list2 = rows;
                        try {
                            if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                                Iterator it4 = list2.iterator();
                                while (true) {
                                    if (it4.hasNext()) {
                                        if (((ProductImportRow) it4.next()).getUsesSypPrices()) {
                                            str = "SYP_CONVERTED_TO_USD";
                                            break;
                                        }
                                    }
                                }
                                ContentValues contentValues6 = new ContentValues();
                                contentValues6.put("id", UUID.randomUUID().toString());
                                contentValues6.put("user_id", actorUserId);
                                contentValues6.put("action", "PRODUCTS_IMPORTED");
                                contentValues6.put("entity_type", "PRODUCT_IMPORT_BATCH");
                                contentValues6.put("entity_id", string14);
                                contentValues6.put("after_json", "{\"count\":" + rows.size() + ",\"source_currency\":\"" + str + "\",\"exchange_rate_syp_per_usd_milli\":" + r13 + ",\"opening_stock\":\"ZERO\"}");
                                contentValues6.put("created_at", str16);
                                r10.insertOrThrow("audit_log", null, contentValues6);
                                r10.setTransactionSuccessful();
                                r10.endTransaction();
                                Result.Companion companion7 = Result.INSTANCE;
                                SyncRelaySession.INSTANCE.flushPending();
                                Result.m381constructorimpl(Unit.INSTANCE);
                                return rows.size();
                            }
                            Result.Companion companion8 = Result.INSTANCE;
                            SyncRelaySession.INSTANCE.flushPending();
                            Result.m381constructorimpl(Unit.INSTANCE);
                        } catch (Throwable th18) {
                            Result.Companion companion9 = Result.INSTANCE;
                            Result.m381constructorimpl(ResultKt.createFailure(th18));
                        }
                        str = "USD_REFERENCE";
                        ContentValues contentValues7 = new ContentValues();
                        contentValues7.put("id", UUID.randomUUID().toString());
                        contentValues7.put("user_id", actorUserId);
                        contentValues7.put("action", "PRODUCTS_IMPORTED");
                        contentValues7.put("entity_type", "PRODUCT_IMPORT_BATCH");
                        contentValues7.put("entity_id", string14);
                        contentValues7.put("after_json", "{\"count\":" + rows.size() + ",\"source_currency\":\"" + str + "\",\"exchange_rate_syp_per_usd_milli\":" + r13 + ",\"opening_stock\":\"ZERO\"}");
                        contentValues7.put("created_at", str16);
                        r10.insertOrThrow("audit_log", null, contentValues7);
                        r10.setTransactionSuccessful();
                        r10.endTransaction();
                        return rows.size();
                    }
                    if (productImportRow.getTracksInventory()) {
                        str3 = "\u064a\u062c\u0628 \u062a\u0633\u062c\u064a\u0644 \u062a\u0643\u0644\u0641\u0629 \u0634\u0631\u0627\u0621 \u0645\u0648\u062c\u0628\u0629 \u0644\u0644\u0635\u0646\u0641 \u0627\u0644\u0630\u064a \u064a\u062a\u062a\u0628\u0639 \u0627\u0644\u0645\u062e\u0632\u0648\u0646: " + string;
                    } else {
                        str3 = "\u0633\u0639\u0631 \u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u0645\u062d\u0648\u0651\u0644 \u0625\u0644\u0649 \u0627\u0644\u062f\u0648\u0644\u0627\u0631 \u0623\u0642\u0644 \u0645\u0646 \u0633\u0646\u062a \u0648\u0627\u062d\u062f \u0623\u0648 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d \u0644\u0644\u0635\u0646\u0641: " + r17;
                    }
                    throw new IllegalArgumentException(str3.toString());
                } catch (Throwable th19) {
                    th = th19;
                    writableDatabase = writableDatabase;
                }
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:50:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:57:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:64:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:68:0x010d  */
    /* JADX WARN: Code duplicated, block: B:78:0x012d  */
    /* JADX WARN: Code duplicated, block: B:82:0x013f  */
    /* JADX WARN: Code duplicated, block: B:86:0x0151  */
    /* JADX WARN: Code duplicated, block: B:90:0x015f  */
    /* JADX WARN: Code duplicated, block: B:91:0x0164  */
    private final long resolveImportUnit(SQLiteDatabase db, String rawName) throws IOException {
        List<String> listListOf;
        String str = rawName;
        String string = StringsKt.trim((CharSequence) str).toString();
        Locale ROOT = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
        String lowerCase = string.toLowerCase(ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        switch (lowerCase) {
            case "bundle":
                listListOf = CollectionsKt.listOf("\u0631\u0628\u0637\u0629");
                break;
            case "carton":
                listListOf = CollectionsKt.listOf("\u0643\u0631\u062a\u0648\u0646\u0629");
                break;
            case "g":
                listListOf = CollectionsKt.listOf((Object[]) new String[]{"\u063a\u0631\u0627\u0645", "\u063a"});
                break;
            case "l":
                listListOf = CollectionsKt.listOf((Object[]) new String[]{"\u0644\u062a\u0631", "\u0644"});
                break;
            case "غ":
                listListOf = CollectionsKt.listOf((Object[]) new String[]{"\u063a\u0631\u0627\u0645", "\u063a"});
                break;
            case "ل":
                listListOf = CollectionsKt.listOf((Object[]) new String[]{"\u0644\u062a\u0631", "\u0644"});
                break;
            case "kg":
                listListOf = CollectionsKt.listOf((Object[]) new String[]{"\u0643\u064a\u0644\u0648\u063a\u0631\u0627\u0645", "\u0643\u063a"});
                break;
            case "pc":
                listListOf = CollectionsKt.listOf("\u0642\u0637\u0639\u0629");
                break;
            case "كغ":
                listListOf = CollectionsKt.listOf((Object[]) new String[]{"\u0643\u064a\u0644\u0648\u063a\u0631\u0627\u0645", "\u0643\u063a"});
                break;
            case "box":
                listListOf = CollectionsKt.listOf("\u0639\u0644\u0628\u0629");
                break;
            case "pcs":
                listListOf = CollectionsKt.listOf("\u0642\u0637\u0639\u0629");
                break;
            case "طرد":
                listListOf = CollectionsKt.listOf("\u0637\u0631\u062f");
                break;
            case "لتر":
                listListOf = CollectionsKt.listOf((Object[]) new String[]{"\u0644\u062a\u0631", "\u0644"});
                break;
            case "pack":
                listListOf = CollectionsKt.listOf("\u0637\u0631\u062f");
                break;
            case "جرام":
                listListOf = CollectionsKt.listOf((Object[]) new String[]{"\u063a\u0631\u0627\u0645", "\u063a"});
                break;
            case "ربطة":
                listListOf = CollectionsKt.listOf("\u0631\u0628\u0637\u0629");
                break;
            case "علبة":
                listListOf = CollectionsKt.listOf("\u0639\u0644\u0628\u0629");
                break;
            case "غرام":
                listListOf = CollectionsKt.listOf((Object[]) new String[]{"\u063a\u0631\u0627\u0645", "\u063a"});
                break;
            case "قطعة":
                listListOf = CollectionsKt.listOf("\u0642\u0637\u0639\u0629");
                break;
            case "كيلو":
                listListOf = CollectionsKt.listOf((Object[]) new String[]{"\u0643\u064a\u0644\u0648\u063a\u0631\u0627\u0645", "\u0643\u063a"});
                break;
            case "liter":
            case "litre":
                listListOf = CollectionsKt.listOf((Object[]) new String[]{"\u0644\u062a\u0631", "\u0644"});
                break;
            case "piece":
                listListOf = CollectionsKt.listOf("\u0642\u0637\u0639\u0629");
                break;
            case "كرتونة":
                listListOf = CollectionsKt.listOf("\u0643\u0631\u062a\u0648\u0646\u0629");
                break;
            case "كيلوجرام":
            case "كيلوغرام":
                listListOf = CollectionsKt.listOf((Object[]) new String[]{"\u0643\u064a\u0644\u0648\u063a\u0631\u0627\u0645", "\u0643\u063a"});
                break;
            default:
                listListOf = CollectionsKt.listOf(StringsKt.trim((CharSequence) str).toString());
                break;
        }
        for (String str2 : listListOf) {
            Cursor cursorRawQuery = db.rawQuery("SELECT id FROM units WHERE name=? COLLATE NOCASE OR symbol=? COLLATE NOCASE LIMIT 1", new String[]{str2, str2});
            try {
                Cursor cursor = cursorRawQuery;
                if (cursor.moveToFirst()) {
                    long j = cursor.getLong(0);
                    CloseableKt.closeFinally(cursorRawQuery, null);
                    return j;
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(cursorRawQuery, null);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(cursorRawQuery, th);
                    throw th2;
                }
            }
        }
        throw new IllegalStateException(("\u0648\u062d\u062f\u0629 \u0627\u0644\u0642\u064a\u0627\u0633 \u063a\u064a\u0631 \u0645\u0639\u0631\u0648\u0641\u0629: " + rawName + "\u061b \u0623\u0636\u0641\u0647\u0627 \u0645\u0646 \u0625\u0639\u062f\u0627\u062f\u0627\u062a \u0627\u0644\u0648\u062d\u062f\u0627\u062a \u062b\u0645 \u0623\u0639\u062f \u0627\u0644\u0627\u0633\u062a\u064a\u0631\u0627\u062f").toString());
    }

    /* JADX WARN: Code duplicated, block: B:60:0x0209  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v11 */
    /* JADX WARN: Type inference failed for: r11v13 */
    /* JADX WARN: Type inference failed for: r11v18 */
    /* JADX WARN: Type inference failed for: r11v19 */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v21 */
    /* JADX WARN: Type inference failed for: r11v22, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r11v23 */
    /* JADX WARN: Type inference failed for: r11v3, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r38v0, types: [com.hazem.dailystore.data.StoreRepository] */
    /* JADX WARN: Type inference failed for: r3v54, types: [com.hazem.dailystore.data.SyncProtocolRepository] */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.Object, net.zetetic.database.sqlcipher.SQLiteDatabase] */
    public final void recordOpeningStock(String productId, double quantity, String actorUserId, String note) {
        ?? r11;
        Throwable th;
        Throwable th2;
        int i;
        String string;
        ?? writableDatabase = "note";
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        requirePrimaryCatalogAuthority();
        if (StringsKt.isBlank(productId) || StringsKt.isBlank(actorUserId)) {
            throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u063a\u064a\u0631 \u0645\u0643\u062a\u0645\u0644\u0629".toString());
        }
        if (quantity <= 0.0d || Math.abs(quantity) > Double.MAX_VALUE) {
            throw new IllegalArgumentException("\u0643\u0645\u064a\u0629 \u0627\u0644\u062c\u0631\u062f \u064a\u062c\u0628 \u0623\u0646 \u062a\u0643\u0648\u0646 \u0623\u0643\u0628\u0631 \u0645\u0646 \u0635\u0641\u0631".toString());
        }
        writableDatabase = this.helper.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        String strTimestamp = timestamp();
        writableDatabase.beginTransaction();
        try {
            try {
                requireManager(writableDatabase, actorUserId);
                Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT p.track_inventory,p.purchase_cost_usd_cents,u.decimal_places\n                   FROM products p JOIN units u ON u.id=p.unit_id WHERE p.id=?", new String[]{productId});
                try {
                    Cursor cursor = cursorRawQuery;
                    if (!cursor.moveToFirst()) {
                        try {
                            throw new IllegalArgumentException("\u0627\u0644\u0645\u0646\u062a\u062c \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } else {
                        try {
                            Triple triple = new Triple(Boolean.valueOf(cursor.getInt(0) == 1), Long.valueOf(cursor.getLong(1)), Integer.valueOf(cursor.getInt(2)));
                            try {
                                CloseableKt.closeFinally(cursorRawQuery, null);
                                if (!((Boolean) triple.getFirst()).booleanValue()) {
                                    throw new IllegalArgumentException("\u0647\u0630\u0627 \u0627\u0644\u0645\u0646\u062a\u062c \u0644\u0627 \u064a\u062a\u062a\u0628\u0639 \u0627\u0644\u0645\u062e\u0632\u0648\u0646".toString());
                                }
                                if (((Number) triple.getSecond()).longValue() <= 0) {
                                    throw new IllegalArgumentException("\u062a\u0643\u0644\u0641\u0629 \u0634\u0631\u0627\u0621 \u0627\u0644\u0645\u0646\u062a\u062c \u063a\u064a\u0631 \u0645\u0633\u062c\u0644\u0629\u061b \u062d\u062f\u0651\u062b \u062a\u0643\u0644\u0641\u0629 \u0627\u0644\u0634\u0631\u0627\u0621 \u0623\u0648\u0644\u0627\u064b \u062d\u062a\u0649 \u0644\u0627 \u064a\u062f\u062e\u0644 \u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u0641\u064a \u0631\u0623\u0633 \u0627\u0644\u0645\u0627\u0644 \u0628\u0642\u064a\u0645\u0629 \u0645\u062c\u0647\u0648\u0644\u0629".toString());
                                }
                                if (RangesKt.coerceAtLeast(MainActivity$$ExternalSyntheticBackport0.m(BigDecimal.valueOf(quantity)).scale(), 0) > ((Number) triple.getThird()).intValue()) {
                                    throw new IllegalArgumentException("\u062f\u0642\u0629 \u0627\u0644\u0643\u0645\u064a\u0629 \u062a\u062a\u062c\u0627\u0648\u0632 \u0627\u0644\u0645\u0646\u0627\u0632\u0644 \u0627\u0644\u0639\u0634\u0631\u064a\u0629 \u0627\u0644\u0645\u0633\u0645\u0648\u062d\u0629 \u0644\u0648\u062d\u062f\u0629 \u0627\u0644\u0642\u064a\u0627\u0633".toString());
                                }
                                Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT 1 FROM inventory_movements WHERE product_id=? LIMIT 1", new String[]{productId});
                                try {
                                    boolean zMoveToFirst = cursorRawQuery2.moveToFirst();
                                    CloseableKt.closeFinally(cursorRawQuery2, null);
                                    if (zMoveToFirst) {
                                        throw new IllegalArgumentException("\u064a\u0648\u062c\u062f \u0633\u062c\u0644 \u062d\u0631\u0643\u0629 \u0633\u0627\u0628\u0642 \u0644\u0647\u0630\u0627 \u0627\u0644\u0645\u0646\u062a\u062c\u061b \u0644\u0627 \u064a\u062c\u0648\u0632 \u0625\u0646\u0634\u0627\u0621 \u0631\u0635\u064a\u062f \u0627\u0641\u062a\u062a\u0627\u062d\u064a \u062c\u062f\u064a\u062f \u0641\u0648\u0642 \u0633\u062c\u0644 \u0642\u0627\u0626\u0645. \u0627\u0633\u062a\u062e\u062f\u0645 \u062a\u0633\u0648\u064a\u0629 \u062c\u0631\u062f \u0645\u0633\u062a\u0642\u0644\u0629.".toString());
                                    }
                                    Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT COALESCE(SUM(quantity),0) FROM inventory_balances WHERE product_id=?", new String[]{productId});
                                    try {
                                        Cursor cursor2 = cursorRawQuery3;
                                        cursor2.moveToFirst();
                                        double d = cursor2.getDouble(0);
                                        CloseableKt.closeFinally(cursorRawQuery3, null);
                                        if (Math.abs(d) > 1.0E-7d) {
                                            throw new IllegalArgumentException("\u064a\u0648\u062c\u062f \u0631\u0635\u064a\u062f \u0645\u062e\u0632\u0648\u0646 \u0645\u0633\u062c\u0644 \u0628\u0627\u0644\u0641\u0639\u0644\u061b \u0644\u0627 \u064a\u062c\u0648\u0632 \u0627\u0633\u062a\u0628\u062f\u0627\u0644\u0647 \u0628\u0631\u0635\u064a\u062f \u0627\u0641\u062a\u062a\u0627\u062d\u064a".toString());
                                        }
                                        Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT id FROM warehouses WHERE is_default=1 AND is_active=1 LIMIT 1", null);
                                        try {
                                            Cursor cursor3 = cursorRawQuery4;
                                            if (!cursor3.moveToFirst()) {
                                                try {
                                                    throw new IllegalArgumentException("\u0627\u0644\u0645\u0633\u062a\u0648\u062f\u0639 \u0627\u0644\u0631\u0626\u064a\u0633\u064a \u063a\u064a\u0631 \u0645\u0647\u064a\u0623".toString());
                                                } catch (Throwable th4) {
                                                    th = th4;
                                                }
                                            } else {
                                                String string2 = cursor3.getString(0);
                                                CloseableKt.closeFinally(cursorRawQuery4, null);
                                                Cursor cursorRawQuery5 = writableDatabase.rawQuery("SELECT 1 FROM inventory_balances WHERE product_id=? AND warehouse_id=?", new String[]{productId, string2});
                                                try {
                                                    boolean zMoveToFirst2 = cursorRawQuery5.moveToFirst();
                                                    CloseableKt.closeFinally(cursorRawQuery5, null);
                                                    if (zMoveToFirst2) {
                                                        try {
                                                            i = 2;
                                                            writableDatabase.execSQL("UPDATE inventory_balances SET quantity=?,updated_at=? WHERE product_id=? AND warehouse_id=?", new Object[]{Double.valueOf(quantity), strTimestamp, productId, string2});
                                                        } catch (Throwable th5) {
                                                            th = th5;
                                                            r11 = writableDatabase;
                                                        }
                                                    } else {
                                                        i = 2;
                                                        ContentValues contentValues = new ContentValues();
                                                        contentValues.put("product_id", productId);
                                                        contentValues.put("warehouse_id", string2);
                                                        contentValues.put("quantity", Double.valueOf(quantity));
                                                        contentValues.put("updated_at", strTimestamp);
                                                        writableDatabase.insertOrThrow("inventory_balances", null, contentValues);
                                                    }
                                                    String string3 = UUID.randomUUID().toString();
                                                    Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
                                                    ContentValues contentValues2 = new ContentValues();
                                                    contentValues2.put("id", string3);
                                                    contentValues2.put("product_id", productId);
                                                    contentValues2.put("warehouse_id", string2);
                                                    contentValues2.put("movement_type", "OPENING");
                                                    contentValues2.put("quantity_delta", Double.valueOf(quantity));
                                                    contentValues2.put("unit_cost_usd_cents", (Long) triple.getSecond());
                                                    contentValues2.put("source_type", "STOCKTAKE_OPENING");
                                                    contentValues2.put("source_id", productId);
                                                    contentValues2.put("operation_id", "OPENING_STOCK:" + productId);
                                                    if (note == null || (string = StringsKt.trim((CharSequence) note).toString()) == null) {
                                                        string = "\u0631\u0635\u064a\u062f \u0627\u0641\u062a\u062a\u0627\u062d\u064a \u0646\u0627\u062a\u062c \u0639\u0646 \u062c\u0631\u062f \u0641\u0639\u0644\u064a";
                                                    } else {
                                                        if (string.length() <= 0) {
                                                            string = null;
                                                        }
                                                        if (string == null) {
                                                            string = "\u0631\u0635\u064a\u062f \u0627\u0641\u062a\u062a\u0627\u062d\u064a \u0646\u0627\u062a\u062c \u0639\u0646 \u062c\u0631\u062f \u0641\u0639\u0644\u064a";
                                                        }
                                                    }
                                                    contentValues2.put("note", string);
                                                    contentValues2.put("created_by", actorUserId);
                                                    contentValues2.put("created_at", strTimestamp);
                                                    writableDatabase.insertOrThrow("inventory_movements", null, contentValues2);
                                                    FifoCosting fifoCosting = FifoCosting.INSTANCE;
                                                    Intrinsics.checkNotNull(string2);
                                                    try {
                                                        fifoCosting.addLayer(writableDatabase, string3, productId, string2, quantity, ((Number) triple.getSecond()).longValue(), "STOCKTAKE_OPENING", productId, strTimestamp, (512 & 512) != 0 ? null : null);
                                                        ContentValues contentValues3 = new ContentValues();
                                                        contentValues3.put("id", UUID.randomUUID().toString());
                                                        contentValues3.put("user_id", actorUserId);
                                                        contentValues3.put("action", "OPENING_STOCK_RECORDED");
                                                        contentValues3.put("entity_type", "PRODUCT");
                                                        contentValues3.put("entity_id", productId);
                                                        contentValues3.put("after_json", "{\"quantity\":" + quantity + ",\"unit_cost_usd_cents\":" + triple.getSecond() + ",\"valuation\":\"FIFO\"}");
                                                        contentValues3.put("created_at", strTimestamp);
                                                        writableDatabase = writableDatabase;
                                                        try {
                                                            writableDatabase.insertOrThrow("audit_log", null, contentValues3);
                                                            JSONObject jSONObjectPut = new JSONObject().put("business_operation", "SET_PRODUCT_OPENING_STOCK").put("product_id", productId).put("quantity", quantity).put("unit_cost_usd_cents", ((Number) triple.getSecond()).longValue()).put("warehouse_id", string2).put("note", note == null ? JSONObject.NULL : note).put("occurred_at", strTimestamp);
                                                            int i2 = i;
                                                            Context appContext = this.appContext;
                                                            Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
                                                            String string4 = jSONObjectPut.toString();
                                                            Intrinsics.checkNotNullExpressionValue(string4, "toString(...)");
                                                            new SyncProtocolRepository(appContext, null, i2, null).enqueueLocalOperation(writableDatabase, actorUserId, "opening-stock-sync-" + productId, "SET_PRODUCT_OPENING_STOCK", "PRODUCT_OPENING_STOCK", productId, "CREATE", string4);
                                                            writableDatabase.setTransactionSuccessful();
                                                            writableDatabase.endTransaction();
                                                            try {
                                                                Result.Companion companion = Result.INSTANCE;
                                                                SyncRelaySession.INSTANCE.flushPending();
                                                                Result.m381constructorimpl(Unit.INSTANCE);
                                                                return;
                                                            } catch (Throwable th6) {
                                                                Result.Companion companion2 = Result.INSTANCE;
                                                                Result.m381constructorimpl(ResultKt.createFailure(th6));
                                                                return;
                                                            }
                                                        } catch (Throwable th7) {
                                                            th = th7;
                                                        }
                                                    } catch (Throwable th8) {
                                                        th = th8;
                                                        writableDatabase = writableDatabase;
                                                    }
                                                } catch (Throwable th9) {
                                                    writableDatabase = writableDatabase;
                                                    try {
                                                        throw th9;
                                                    } catch (Throwable th10) {
                                                        CloseableKt.closeFinally(cursorRawQuery5, th9);
                                                        throw th10;
                                                    }
                                                }
                                            }
                                        } catch (Throwable th11) {
                                            th = th11;
                                        }
                                        Throwable th12 = th;
                                        try {
                                            throw th12;
                                        } catch (Throwable th13) {
                                            CloseableKt.closeFinally(cursorRawQuery4, th12);
                                            throw th13;
                                        }
                                    } catch (Throwable th14) {
                                        writableDatabase = writableDatabase;
                                        try {
                                            throw th14;
                                        } catch (Throwable th15) {
                                            CloseableKt.closeFinally(cursorRawQuery3, th14);
                                            throw th15;
                                        }
                                    }
                                } catch (Throwable th16) {
                                    writableDatabase = writableDatabase;
                                    try {
                                        throw th16;
                                    } catch (Throwable th17) {
                                        CloseableKt.closeFinally(cursorRawQuery2, th16);
                                        throw th17;
                                    }
                                }
                            } catch (Throwable th18) {
                                th = th18;
                                writableDatabase = writableDatabase;
                            }
                        } catch (Throwable th19) {
                            th = th19;
                            th2 = th;
                            throw th2;
                        }
                        th = th;
                        r11 = writableDatabase;
                        r11.endTransaction();
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            SyncRelaySession.INSTANCE.flushPending();
                            Result.m381constructorimpl(Unit.INSTANCE);
                            throw th;
                        } catch (Throwable th20) {
                            Result.Companion companion4 = Result.INSTANCE;
                            Result.m381constructorimpl(ResultKt.createFailure(th20));
                            throw th;
                        }
                    }
                } catch (Throwable th21) {
                    th = th21;
                }
                th2 = th;
                try {
                    throw th2;
                } catch (Throwable th22) {
                    CloseableKt.closeFinally(cursorRawQuery, th2);
                    throw th22;
                }
            } catch (Throwable th23) {
                th = th23;
            }
        } catch (Throwable th24) {
            th = th24;
        }
    }

    public final boolean canRecordOpeningStock(String productId) throws IOException {
        Intrinsics.checkNotNullParameter(productId, "productId");
        Cursor cursorRawQuery = this.helper.getReadableDatabase().rawQuery("SELECT 1 FROM products p\n               WHERE p.id=? AND p.track_inventory=1\n                 AND NOT EXISTS (SELECT 1 FROM inventory_movements m WHERE m.product_id=p.id)", new String[]{productId});
        try {
            boolean zMoveToFirst = cursorRawQuery.moveToFirst();
            CloseableKt.closeFinally(cursorRawQuery, null);
            return zMoveToFirst;
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
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 3 */
    public final void setProductActive(String id, boolean active, String actorUserId) {
        Throwable th;
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        requirePrimaryCatalogAuthority();
        SQLiteDatabase writableDatabase = this.helper.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        requireManager(writableDatabase, actorUserId);
        writableDatabase.beginTransaction();
        try {
            try {
                Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT name,is_active FROM products WHERE id=?", new String[]{id});
                try {
                    Cursor cursor = cursorRawQuery;
                    if (!cursor.moveToFirst()) {
                        try {
                            throw new IllegalArgumentException("\u0627\u0644\u0645\u0646\u062a\u062c \u0627\u0644\u0645\u0637\u0644\u0648\u0628 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } else {
                        Pair pair = TuplesKt.to(cursor.getString(0), Integer.valueOf(cursor.getInt(1)));
                        CloseableKt.closeFinally(cursorRawQuery, null);
                        String strTimestamp = timestamp();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("is_active", Integer.valueOf(active ? 1 : 0));
                        contentValues.put("updated_at", strTimestamp);
                        if (writableDatabase.update("products", contentValues, "id=?", new String[]{id}) != 1) {
                            throw new IllegalArgumentException("\u0627\u0644\u0645\u0646\u062a\u062c \u0627\u0644\u0645\u0637\u0644\u0648\u0628 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
                        }
                        String str = active ? "ACTIVATE_PRODUCT" : "DEACTIVATE_PRODUCT";
                        Object first = pair.getFirst();
                        Intrinsics.checkNotNullExpressionValue(first, "<get-first>(...)");
                        String str2 = "{\"name\":\"" + jsonEscape((String) first) + "\",\"is_active\":" + pair.getSecond() + "}";
                        Object first2 = pair.getFirst();
                        Intrinsics.checkNotNullExpressionValue(first2, "<get-first>(...)");
                        insertAudit(writableDatabase, actorUserId, str, "PRODUCT", id, str2, "{\"name\":\"" + jsonEscape((String) first2) + "\",\"is_active\":" + (active ? 1 : 0) + "}", strTimestamp);
                        JSONObject jSONObjectPut = new JSONObject().put("business_operation", "SET_PRODUCT_ACTIVE").put("product_id", id).put("is_active", active).put("occurred_at", strTimestamp);
                        Context appContext = this.appContext;
                        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
                        SyncProtocolRepository syncProtocolRepository = new SyncProtocolRepository(appContext, null, 2, null);
                        String string = UUID.randomUUID().toString();
                        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                        String string2 = jSONObjectPut.toString();
                        Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                        syncProtocolRepository.enqueueLocalOperation(writableDatabase, actorUserId, string, "SET_PRODUCT_ACTIVE", "PRODUCT", id, "UPDATE", string2);
                        writableDatabase.setTransactionSuccessful();
                        writableDatabase.endTransaction();
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            StoreRepository storeRepository = this;
                            SyncRelaySession.INSTANCE.flushPending();
                            Result.m381constructorimpl(Unit.INSTANCE);
                            return;
                        } catch (Throwable th3) {
                            Result.Companion companion2 = Result.INSTANCE;
                            Result.m381constructorimpl(ResultKt.createFailure(th3));
                            return;
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
                Throwable th5 = th;
                try {
                    throw th5;
                } catch (Throwable th6) {
                    CloseableKt.closeFinally(cursorRawQuery, th5);
                    throw th6;
                }
            } catch (Throwable th7) {
                th = th7;
                th = th;
                writableDatabase.endTransaction();
                try {
                    Result.Companion companion3 = Result.INSTANCE;
                    SyncRelaySession.INSTANCE.flushPending();
                    Result.m381constructorimpl(Unit.INSTANCE);
                    throw th;
                } catch (Throwable th8) {
                    Result.Companion companion4 = Result.INSTANCE;
                    Result.m381constructorimpl(ResultKt.createFailure(th8));
                    throw th;
                }
            }
        } catch (Throwable th9) {
            th = th9;
            writableDatabase = writableDatabase;
            th = th;
            writableDatabase.endTransaction();
            Result.Companion companion5 = Result.INSTANCE;
            SyncRelaySession.INSTANCE.flushPending();
            Result.m381constructorimpl(Unit.INSTANCE);
            throw th;
        }
    }

    public final List<StoreProduct> listProducts() {
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = this.helper.getReadableDatabase().rawQuery("SELECT p.id,p.name,p.barcode,p.sku,u.name,u.symbol,c.name,\n                      p.purchase_cost_usd_cents,p.sale_price_usd_cents,p.track_inventory,\n                      COALESCE((SELECT SUM(ib.quantity) FROM inventory_balances ib\n                                WHERE ib.product_id=p.id),0)\n               FROM products p JOIN units u ON u.id=p.unit_id\n               LEFT JOIN categories c ON c.id=p.category_id\n               WHERE p.is_active=1 ORDER BY p.name COLLATE NOCASE", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            while (cursor.moveToNext()) {
                String string = cursor.getString(0);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String string2 = cursor.getString(1);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                String string3 = cursor.isNull(2) ? null : cursor.getString(2);
                String string4 = cursor.isNull(3) ? null : cursor.getString(3);
                String string5 = cursor.getString(4);
                Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                String string6 = cursor.getString(5);
                Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                arrayList.add(new StoreProduct(string, string2, string3, string4, string5, string6, cursor.isNull(6) ? null : cursor.getString(6), cursor.getLong(7), cursor.getLong(8), cursor.getInt(9) == 1, cursor.getDouble(10)));
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

    private final void requireManager(SQLiteDatabase db, String actorUserId) {
        PermissionPolicy.INSTANCE.require(db, actorUserId, PermissionPolicy.CATALOG_MANAGE);
    }

    private final void insertAudit(SQLiteDatabase db, String actor, String action, String entityType, String entityId, String before, String after, String now) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", UUID.randomUUID().toString());
        contentValues.put("user_id", actor);
        contentValues.put("action", action);
        contentValues.put("entity_type", entityType);
        contentValues.put("entity_id", entityId);
        if (before == null) {
            contentValues.putNull("before_json");
        } else {
            contentValues.put("before_json", before);
        }
        contentValues.put("after_json", after);
        contentValues.put("created_at", now);
        db.insertOrThrow("audit_log", null, contentValues);
    }

    private final String jsonEscape(String value) {
        return StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(value, "\\", "\\\\", false, 4, (Object) null), "\"", "\\\"", false, 4, (Object) null), "\n", "\\n", false, 4, (Object) null), "\r", "\\r", false, 4, (Object) null);
    }

    private final void putNullable(ContentValues contentValues, String str, String str2) {
        if (str2 == null) {
            contentValues.putNull(str);
        } else {
            contentValues.put(str, str2);
        }
    }

    private final void enqueueProductSync(SQLiteDatabase db, String actorUserId, String productId, String changeKind, boolean includeOpeningStock) throws JSONException, NoSuchAlgorithmException, IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT p.name,p.barcode,p.sku,p.category_id,c.name,u.name,u.symbol,u.decimal_places,\n                      p.purchase_cost_usd_cents,p.sale_price_usd_cents,p.track_inventory,p.is_active,\n                      p.created_at,p.updated_at,\n                      COALESCE((SELECT SUM(ib.quantity) FROM inventory_balances ib WHERE ib.product_id=p.id),0),\n                      p.unit_id\n               FROM products p JOIN units u ON u.id=p.unit_id\n               LEFT JOIN categories c ON c.id=p.category_id WHERE p.id=?", new String[]{productId});
        try {
            Cursor cursor = cursorRawQuery;
            if (!cursor.moveToFirst()) {
                try {
                    throw new IllegalArgumentException("\u0627\u0644\u0645\u0646\u062a\u062c \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0639\u0646\u062f \u062a\u062c\u0647\u064a\u0632 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629".toString());
                } catch (Throwable th) {
                    th = th;
                }
            } else {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("business_operation", "UPSERT_PRODUCT");
                jSONObject.put("product_id", productId);
                jSONObject.put("name", cursor.getString(0));
                jSONObject.put("barcode", cursor.isNull(1) ? JSONObject.NULL : cursor.getString(1));
                jSONObject.put("sku", cursor.isNull(2) ? JSONObject.NULL : cursor.getString(2));
                jSONObject.put("primary_category_id", cursor.isNull(3) ? JSONObject.NULL : Long.valueOf(cursor.getLong(3)));
                jSONObject.put("category_name", cursor.isNull(4) ? JSONObject.NULL : cursor.getString(4));
                jSONObject.put("unit_name", cursor.getString(5));
                jSONObject.put("unit_symbol", cursor.getString(6));
                jSONObject.put("unit_decimal_places", cursor.getInt(7));
                jSONObject.put("primary_unit_id", cursor.getLong(15));
                jSONObject.put("purchase_cost_usd_cents", cursor.getLong(8));
                jSONObject.put("sale_price_usd_cents", cursor.getLong(9));
                jSONObject.put("tracks_inventory", cursor.getInt(10) == 1);
                jSONObject.put("is_active", cursor.getInt(11) == 1);
                jSONObject.put("created_at", cursor.getString(12));
                jSONObject.put("updated_at", cursor.getString(13));
                jSONObject.put("opening_quantity", includeOpeningStock ? cursor.getDouble(14) : 0.0d);
                CloseableKt.closeFinally(cursorRawQuery, null);
                Context appContext = this.appContext;
                Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
                SyncProtocolRepository syncProtocolRepository = new SyncProtocolRepository(appContext, null, 2, null);
                String string = UUID.randomUUID().toString();
                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                String string2 = jSONObject.toString();
                Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                syncProtocolRepository.enqueueLocalOperation(db, actorUserId, string, "UPSERT_PRODUCT", "PRODUCT", productId, changeKind, string2);
                return;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        Throwable th3 = th;
        try {
            throw th3;
        } catch (Throwable th4) {
            CloseableKt.closeFinally(cursorRawQuery, th3);
            throw th4;
        }
    }

    private final void requirePrimaryCatalogAuthority() {
        Context appContext = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        if (new SyncProtocolRepository(appContext, null, 2, null).isConfiguredSecondary()) {
            throw new IllegalArgumentException("\u062a\u0639\u062f\u064a\u0644 \u0627\u0644\u0645\u0646\u062a\u062c\u0627\u062a \u0648\u0627\u0644\u062a\u0635\u0646\u064a\u0641\u0627\u062a \u0648\u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u0627\u0644\u0627\u0641\u062a\u062a\u0627\u062d\u064a \u0645\u062a\u0627\u062d \u0645\u0646 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0641\u0642\u0637 \u062d\u062a\u0649 \u064a\u0643\u062a\u0645\u0644 \u0645\u0633\u0627\u0631 \u0645\u0632\u0627\u0645\u0646\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0623\u0633\u0627\u0633\u064a\u0629".toString());
        }
    }

    private final String timestamp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String str = simpleDateFormat.format(new Date());
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }
}
