package com.hazem.dailystore.data;

import android.content.ContentValues;
import android.database.Cursor;
import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.zetetic.database.sqlcipher.SQLiteDatabase;

/* JADX INFO: compiled from: StoreTime.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005J\u0012\u0010\r\u001a\u00020\u00052\n\u0010\u000e\u001a\u00060\u000fj\u0002`\u0010J\"\u0010\u0011\u001a\u00020\u00122\n\u0010\u000e\u001a\u00060\u000fj\u0002`\u00102\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0005J\u001c\u0010\u0014\u001a\u00020\u00052\n\u0010\u000e\u001a\u00060\u000fj\u0002`\u00102\b\b\u0002\u0010\u0015\u001a\u00020\u0016J\u001a\u0010\u0017\u001a\u00020\u00052\n\u0010\u000e\u001a\u00060\u000fj\u0002`\u00102\u0006\u0010\u0018\u001a\u00020\u0005J\u001a\u0010\u0019\u001a\u00020\u00052\n\u0010\u000e\u001a\u00060\u000fj\u0002`\u00102\u0006\u0010\u0018\u001a\u00020\u0005J\u0010\u0010\u001a\u001a\u00020\u00162\u0006\u0010\f\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/hazem/dailystore/data/StoreTime;", "", "<init>", "()V", "DEFAULT_ZONE_ID", "", "SETTING_KEY", "UTC_ID", "availableZoneIds", "", "isValidZoneId", "", "value", "zoneId", "db", "Lnet/zetetic/database/sqlcipher/SQLiteDatabase;", "Lcom/hazem/dailystore/data/AppSqliteDatabase;", "setZoneId", "", "updatedAt", "today", "now", "Ljava/util/Date;", "operatingDate", "utcTimestamp", "formatTimestamp", "parseUtcTimestamp", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class StoreTime {
    public static final String DEFAULT_ZONE_ID = "Asia/Damascus";
    public static final StoreTime INSTANCE = new StoreTime();
    private static final String SETTING_KEY = "store_time_zone";
    private static final String UTC_ID = "UTC";

    private StoreTime() {
    }

    public final List<String> availableZoneIds() {
        String[] availableIDs = TimeZone.getAvailableIDs();
        Intrinsics.checkNotNullExpressionValue(availableIDs, "getAvailableIDs(...)");
        ArrayList arrayList = new ArrayList();
        for (String str : availableIDs) {
            String str2 = str;
            Intrinsics.checkNotNull(str2);
            if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "/", false, 2, (Object) null)) {
                arrayList.add(str);
            }
        }
        return CollectionsKt.sorted(CollectionsKt.distinct(arrayList));
    }

    public final boolean isValidZoneId(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (!Intrinsics.areEqual(value, UTC_ID)) {
            String[] availableIDs = TimeZone.getAvailableIDs();
            Intrinsics.checkNotNullExpressionValue(availableIDs, "getAvailableIDs(...)");
            if (!ArraysKt.contains(availableIDs, value)) {
                return false;
            }
        }
        return true;
    }

    public final String zoneId(SQLiteDatabase db) throws IOException {
        Intrinsics.checkNotNullParameter(db, "db");
        Cursor cursorRawQuery = db.rawQuery("SELECT setting_value FROM app_settings WHERE setting_key=?", new String[]{SETTING_KEY});
        try {
            Cursor cursor = cursorRawQuery;
            String string = cursor.moveToFirst() ? cursor.getString(0) : null;
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (string != null) {
                String str = isValidZoneId(string) ? string : null;
                if (str != null) {
                    return str;
                }
            }
            return DEFAULT_ZONE_ID;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    public final void setZoneId(SQLiteDatabase db, String value, String updatedAt) {
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(updatedAt, "updatedAt");
        String string = StringsKt.trim((CharSequence) value).toString();
        if (!isValidZoneId(string)) {
            throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0645\u0646\u0637\u0642\u0629 \u0627\u0644\u0632\u0645\u0646\u064a\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d. \u0627\u062e\u062a\u0631 \u0645\u0646\u0637\u0642\u0629 \u0645\u0646 \u0627\u0644\u0642\u0627\u0626\u0645\u0629.".toString());
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("setting_key", SETTING_KEY);
        contentValues.put("setting_value", string);
        contentValues.put("updated_at", updatedAt);
        Unit unit = Unit.INSTANCE;
        db.insertWithOnConflict("app_settings", null, contentValues, 5);
    }

    public static /* synthetic */ String today$default(StoreTime storeTime, SQLiteDatabase sQLiteDatabase, Date date, int i, Object obj) {
        if ((i & 2) != 0) {
            date = new Date();
        }
        return storeTime.today(sQLiteDatabase, date);
    }

    public final String today(SQLiteDatabase db, Date now) {
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(now, "now");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(INSTANCE.zoneId(db)));
        String str = simpleDateFormat.format(now);
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    public final String operatingDate(SQLiteDatabase db, String utcTimestamp) {
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(utcTimestamp, "utcTimestamp");
        return today(db, parseUtcTimestamp(utcTimestamp));
    }

    public final String formatTimestamp(SQLiteDatabase db, String utcTimestamp) {
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(utcTimestamp, "utcTimestamp");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(INSTANCE.zoneId(db)));
        String str = simpleDateFormat.format(parseUtcTimestamp(utcTimestamp));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    private final Date parseUtcTimestamp(String value) {
        Iterator it = CollectionsKt.listOf((Object[]) new String[]{"yyyy-MM-dd'T'HH:mm:ss.SSSX", "yyyy-MM-dd'T'HH:mm:ssX"}).iterator();
        while (it.hasNext()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat((String) it.next(), Locale.US);
            simpleDateFormat.setLenient(false);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone(UTC_ID));
            ParsePosition parsePosition = new ParsePosition(0);
            Date date = simpleDateFormat.parse(value, parsePosition);
            if (date != null && parsePosition.getIndex() == value.length()) {
                return date;
            }
        }
        throw new IllegalArgumentException("\u0637\u0627\u0628\u0639 \u0627\u0644\u062d\u0631\u0643\u0629 \u0627\u0644\u0632\u0645\u0646\u064a \u063a\u064a\u0631 \u0635\u0627\u0644\u062d: " + value);
    }
}
