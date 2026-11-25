package com.hazem.dailystore.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.zetetic.database.sqlcipher.SQLiteDatabase;

/* JADX INFO: compiled from: SupplierRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fJ.\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0010J\u001e\u0010\u0014\u001a\u00020\u0015*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00102\b\u0010\u0018\u001a\u0004\u0018\u00010\u0010H\u0002J\b\u0010\u0019\u001a\u00020\u0010H\u0002R\u0018\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/hazem/dailystore/data/SupplierRepository;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "appContext", "kotlin.jvm.PlatformType", "Landroid/content/Context;", "database", "Lcom/hazem/dailystore/data/AppDatabase;", "listActive", "", "Lcom/hazem/dailystore/data/SupplierSummary;", "create", "actorUserId", "", "name", "phone", "address", "putNullable", "", "Landroid/content/ContentValues;", "key", "value", "timestamp", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SupplierRepository {
    private final Context appContext;
    private final AppDatabase database;

    public SupplierRepository(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Context appContext = context.getApplicationContext();
        this.appContext = appContext;
        AppDatabase.Companion companion = AppDatabase.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        this.database = companion.getInstance(appContext);
    }

    public final List<SupplierSummary> listActive() throws IOException {
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery("SELECT id,name,phone,address FROM suppliers WHERE is_active=1 ORDER BY name COLLATE NOCASE", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            while (cursor.moveToNext()) {
                String string = cursor.getString(0);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String string2 = cursor.getString(1);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                arrayList.add(new SupplierSummary(string, string2, cursor.isNull(2) ? null : cursor.getString(2), cursor.isNull(3) ? null : cursor.getString(3)));
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

    public static /* synthetic */ SupplierSummary create$default(SupplierRepository supplierRepository, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = null;
        }
        if ((i & 8) != 0) {
            str4 = null;
        }
        return supplierRepository.create(str, str2, str3, str4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [com.hazem.dailystore.data.SyncCredentialVault, kotlin.jvm.internal.DefaultConstructorMarker] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    public final SupplierSummary create(String actorUserId, String name, String phone, String address) {
        String string;
        String string2;
        String string3;
        String string4;
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(name, "name");
        Context appContext = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        String str = 0;
        str = 0;
        str = 0;
        if (new SyncProtocolRepository(appContext, str, 2, str).isConfiguredSecondary()) {
            throw new IllegalArgumentException("\u0625\u0646\u0634\u0627\u0621 \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0645\u062a\u0627\u062d \u0645\u0646 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0641\u0642\u0637 \u062d\u062a\u0649 \u062a\u0643\u062a\u0645\u0644 \u0645\u0632\u0627\u0645\u0646\u0629 \u0628\u064a\u0627\u0646\u0627\u062a\u0647\u0645".toString());
        }
        String string5 = StringsKt.trim((CharSequence) name).toString();
        if (string5.length() <= 0) {
            throw new IllegalArgumentException("\u0627\u0633\u0645 \u0627\u0644\u0645\u0648\u0631\u062f \u0645\u0637\u0644\u0648\u0628".toString());
        }
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        PermissionPolicy.INSTANCE.require(writableDatabase, actorUserId, PermissionPolicy.SUPPLIERS_MANAGE);
        String strTimestamp = timestamp();
        String string6 = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string6, "toString(...)");
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", string6);
        contentValues.put("name", string5);
        if (phone == null || (string = StringsKt.trim((CharSequence) phone).toString()) == null || string.length() <= 0) {
            string = null;
        }
        putNullable(contentValues, "phone", string);
        if (address == null || (string2 = StringsKt.trim((CharSequence) address).toString()) == null || string2.length() <= 0) {
            string2 = null;
        }
        putNullable(contentValues, "address", string2);
        contentValues.put("created_at", strTimestamp);
        contentValues.put("updated_at", strTimestamp);
        writableDatabase.beginTransaction();
        try {
            writableDatabase.insertOrThrow("suppliers", null, contentValues);
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("id", UUID.randomUUID().toString());
            contentValues2.put("user_id", actorUserId);
            contentValues2.put("action", "CREATE");
            contentValues2.put("entity_type", "SUPPLIER");
            contentValues2.put("entity_id", string6);
            contentValues2.put("created_at", strTimestamp);
            Unit unit = Unit.INSTANCE;
            writableDatabase.insertOrThrow("audit_log", null, contentValues2);
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            if (phone == null || (string3 = StringsKt.trim((CharSequence) phone).toString()) == null || string3.length() <= 0) {
                string3 = null;
            }
            if (address != null && (string4 = StringsKt.trim((CharSequence) address).toString()) != null && string4.length() > 0) {
                str = string4;
            }
            return new SupplierSummary(string6, string5, string3, str);
        } catch (Throwable th) {
            writableDatabase.endTransaction();
            throw th;
        }
    }

    private final void putNullable(ContentValues contentValues, String str, String str2) {
        if (str2 == null) {
            contentValues.putNull(str);
        } else {
            contentValues.put(str, str2);
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
