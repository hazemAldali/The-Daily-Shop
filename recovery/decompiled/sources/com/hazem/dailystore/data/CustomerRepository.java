package com.hazem.dailystore.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.zetetic.database.sqlcipher.SQLiteDatabase;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: CustomerRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\u0014J:\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00142\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0014JB\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00142\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0014J\u001e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u0011J\b\u0010\u001f\u001a\u00020\u001dH\u0002J,\u0010 \u001a\u00020\u001d2\n\u0010!\u001a\u00060\"j\u0002`#2\u0006\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010$\u001a\u00020\u0014H\u0002J4\u0010%\u001a\u00020\u001d2\n\u0010!\u001a\u00060\"j\u0002`#2\u0006\u0010&\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010(\u001a\u00020\u0014H\u0002J\u001e\u0010)\u001a\u00020\u001d*\u00020*2\u0006\u0010+\u001a\u00020\u00142\b\u0010,\u001a\u0004\u0018\u00010\u0014H\u0002J\b\u0010-\u001a\u00020\u0014H\u0002R\u0018\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006."}, d2 = {"Lcom/hazem/dailystore/data/CustomerRepository;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "appContext", "kotlin.jvm.PlatformType", "Landroid/content/Context;", "database", "Lcom/hazem/dailystore/data/AppDatabase;", "listActive", "", "Lcom/hazem/dailystore/data/CustomerSummary;", "listAll", "list", "includeInactive", "", "get", "customerId", "", "create", "actorUserId", "name", "phone", "address", "notes", "update", "setActive", "", "active", "requirePrimaryMasterDataDevice", "enqueueCustomerSync", "db", "Lnet/zetetic/database/sqlcipher/SQLiteDatabase;", "Lcom/hazem/dailystore/data/AppSqliteDatabase;", "changeKind", "insertAudit", "actor", "action", "now", "putNullable", "Landroid/content/ContentValues;", "key", "value", "timestamp", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class CustomerRepository {
    private final Context appContext;
    private final AppDatabase database;

    public CustomerRepository(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Context appContext = context.getApplicationContext();
        this.appContext = appContext;
        AppDatabase.Companion companion = AppDatabase.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        this.database = companion.getInstance(appContext);
    }

    public final List<CustomerSummary> listActive() {
        return list(false);
    }

    public final List<CustomerSummary> listAll() {
        return list(true);
    }

    private final List<CustomerSummary> list(boolean includeInactive) throws IOException {
        ArrayList arrayList = new ArrayList();
        String str = includeInactive ? "" : "WHERE is_active=1";
        String str2 = null;
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery(StringsKt.trimIndent("SELECT id,name,phone,address,notes,opening_balance_minor,\n                      opening_balance_currency,is_active\n               FROM customers " + str + " ORDER BY name COLLATE NOCASE"), (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            while (cursor.moveToNext()) {
                String string = cursor.getString(0);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String string2 = cursor.getString(1);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                String string3 = cursor.isNull(2) ? str2 : cursor.getString(2);
                String string4 = cursor.isNull(3) ? str2 : cursor.getString(3);
                String string5 = cursor.isNull(4) ? str2 : cursor.getString(4);
                long j = cursor.getLong(5);
                String string6 = cursor.getString(6);
                Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                arrayList.add(new CustomerSummary(string, string2, string3, string4, string5, j, string6, cursor.getInt(7) == 1));
                str2 = null;
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

    public final CustomerSummary get(String customerId) {
        Object next;
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Iterator<T> it = list(true).iterator();
        while (it.hasNext()) {
            next = it.next();
            if (Intrinsics.areEqual(((CustomerSummary) next).getId(), customerId)) {
                return (CustomerSummary) next;
            }
        }
        next = null;
        return (CustomerSummary) next;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final CustomerSummary create(String actorUserId, String name, String phone, String address, String notes) {
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(name, "name");
        String string = StringsKt.trim((CharSequence) name).toString();
        if (string.length() <= 0) {
            throw new IllegalArgumentException("\u0627\u0633\u0645 \u0627\u0644\u0632\u0628\u0648\u0646 \u0645\u0637\u0644\u0648\u0628".toString());
        }
        requirePrimaryMasterDataDevice();
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        PermissionPolicy.INSTANCE.require(writableDatabase, actorUserId, PermissionPolicy.CUSTOMERS_CREATE);
        String string2 = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
        String strTimestamp = timestamp();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", string2);
        contentValues.put("name", string);
        putNullable(contentValues, "phone", phone);
        putNullable(contentValues, "address", address);
        putNullable(contentValues, "notes", notes);
        contentValues.put("opening_balance_minor", (Long) 0L);
        contentValues.put("opening_balance_currency", "SYP");
        contentValues.put("is_active", (Integer) 1);
        contentValues.put("created_at", strTimestamp);
        contentValues.put("updated_at", strTimestamp);
        writableDatabase.beginTransaction();
        try {
            writableDatabase.insertOrThrow("customers", null, contentValues);
            insertAudit(writableDatabase, actorUserId, "CREATE", string2, strTimestamp);
            enqueueCustomerSync(writableDatabase, actorUserId, string2, "CREATE");
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            try {
                Result.Companion companion = Result.INSTANCE;
                CustomerRepository customerRepository = this;
                SyncRelaySession.INSTANCE.flushPending();
                Result.m381constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m381constructorimpl(ResultKt.createFailure(th));
            }
            CustomerSummary customerSummary = get(string2);
            if (customerSummary != null) {
                return customerSummary;
            }
            throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0642\u0631\u0627\u0621\u0629 \u0627\u0644\u0632\u0628\u0648\u0646 \u0628\u0639\u062f \u0627\u0644\u062d\u0641\u0638".toString());
        } catch (Throwable th2) {
            writableDatabase.endTransaction();
            try {
                Result.Companion companion3 = Result.INSTANCE;
                SyncRelaySession.INSTANCE.flushPending();
                Result.m381constructorimpl(Unit.INSTANCE);
            } catch (Throwable th3) {
                Result.Companion companion4 = Result.INSTANCE;
                Result.m381constructorimpl(ResultKt.createFailure(th3));
            }
            throw th2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final CustomerSummary update(String actorUserId, String customerId, String name, String phone, String address, String notes) {
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(name, "name");
        if (StringsKt.isBlank(customerId)) {
            throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0632\u0628\u0648\u0646 \u0645\u0637\u0644\u0648\u0628".toString());
        }
        String string = StringsKt.trim((CharSequence) name).toString();
        if (string.length() <= 0) {
            throw new IllegalArgumentException("\u0627\u0633\u0645 \u0627\u0644\u0632\u0628\u0648\u0646 \u0645\u0637\u0644\u0648\u0628".toString());
        }
        requirePrimaryMasterDataDevice();
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        PermissionPolicy.INSTANCE.require(writableDatabase, actorUserId, PermissionPolicy.CUSTOMERS_MANAGE);
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", string);
        putNullable(contentValues, "phone", phone);
        putNullable(contentValues, "address", address);
        putNullable(contentValues, "notes", notes);
        contentValues.put("updated_at", timestamp());
        String strTimestamp = timestamp();
        contentValues.put("updated_at", strTimestamp);
        writableDatabase.beginTransaction();
        try {
            if (writableDatabase.update("customers", contentValues, "id=? AND is_active=1", new String[]{customerId}) != 1) {
                throw new IllegalArgumentException("\u0627\u0644\u0632\u0628\u0648\u0646 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0623\u0648 \u0645\u0648\u0642\u0648\u0641".toString());
            }
            insertAudit(writableDatabase, actorUserId, "UPDATE", customerId, strTimestamp);
            enqueueCustomerSync(writableDatabase, actorUserId, customerId, "UPDATE");
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            try {
                Result.Companion companion = Result.INSTANCE;
                CustomerRepository customerRepository = this;
                SyncRelaySession.INSTANCE.flushPending();
                Result.m381constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m381constructorimpl(ResultKt.createFailure(th));
            }
            CustomerSummary customerSummary = get(customerId);
            if (customerSummary != null) {
                return customerSummary;
            }
            throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0642\u0631\u0627\u0621\u0629 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0632\u0628\u0648\u0646".toString());
        } catch (Throwable th2) {
            writableDatabase.endTransaction();
            try {
                Result.Companion companion3 = Result.INSTANCE;
                SyncRelaySession.INSTANCE.flushPending();
                Result.m381constructorimpl(Unit.INSTANCE);
            } catch (Throwable th3) {
                Result.Companion companion4 = Result.INSTANCE;
                Result.m381constructorimpl(ResultKt.createFailure(th3));
            }
            throw th2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setActive(String actorUserId, String customerId, boolean active) {
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        if (StringsKt.isBlank(customerId)) {
            throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0632\u0628\u0648\u0646 \u0645\u0637\u0644\u0648\u0628".toString());
        }
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        PermissionPolicy.INSTANCE.require(writableDatabase, actorUserId, PermissionPolicy.CUSTOMERS_MANAGE);
        ContentValues contentValues = new ContentValues();
        contentValues.put("is_active", Integer.valueOf(active ? 1 : 0));
        contentValues.put("updated_at", timestamp());
        String strTimestamp = timestamp();
        contentValues.put("updated_at", strTimestamp);
        writableDatabase.beginTransaction();
        try {
            if (writableDatabase.update("customers", contentValues, "id=?", new String[]{customerId}) != 1) {
                throw new IllegalArgumentException("\u0627\u0644\u0632\u0628\u0648\u0646 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
            }
            insertAudit(writableDatabase, actorUserId, active ? "ACTIVATE" : "DEACTIVATE", customerId, strTimestamp);
            JSONObject jSONObjectPut = new JSONObject().put("business_operation", "SET_CUSTOMER_ACTIVE").put("customer_id", customerId).put("is_active", active).put("occurred_at", strTimestamp);
            Context appContext = this.appContext;
            Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
            SyncProtocolRepository syncProtocolRepository = new SyncProtocolRepository(appContext, null, 2, null);
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            String string2 = jSONObjectPut.toString();
            Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
            syncProtocolRepository.enqueueLocalOperation(writableDatabase, actorUserId, string, "SET_CUSTOMER_ACTIVE", "CUSTOMER", customerId, "UPDATE", string2);
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            try {
                Result.Companion companion = Result.INSTANCE;
                CustomerRepository customerRepository = this;
                SyncRelaySession.INSTANCE.flushPending();
                Result.m381constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m381constructorimpl(ResultKt.createFailure(th));
            }
        } catch (Throwable th2) {
            writableDatabase.endTransaction();
            try {
                Result.Companion companion3 = Result.INSTANCE;
                SyncRelaySession.INSTANCE.flushPending();
                Result.m381constructorimpl(Unit.INSTANCE);
            } catch (Throwable th3) {
                Result.Companion companion4 = Result.INSTANCE;
                Result.m381constructorimpl(ResultKt.createFailure(th3));
            }
            throw th2;
        }
    }

    private final void requirePrimaryMasterDataDevice() {
        Context appContext = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        if (new SyncProtocolRepository(appContext, null, 2, null).isConfiguredSecondary()) {
            throw new IllegalArgumentException("\u0625\u0646\u0634\u0627\u0621 \u0648\u062a\u0639\u062f\u064a\u0644 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0632\u0628\u0627\u0626\u0646 \u0645\u062a\u0627\u062d \u0645\u0646 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0641\u0642\u0637".toString());
        }
    }

    private final void enqueueCustomerSync(SQLiteDatabase db, String actorUserId, String customerId, String changeKind) throws JSONException, NoSuchAlgorithmException, IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT name,phone,address,notes,is_active,created_at,updated_at FROM customers WHERE id=?", new String[]{customerId});
        try {
            Cursor cursor = cursorRawQuery;
            if (!cursor.moveToFirst()) {
                try {
                    throw new IllegalArgumentException("\u0627\u0644\u0632\u0628\u0648\u0646 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0639\u0646\u062f \u062a\u062c\u0647\u064a\u0632 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629".toString());
                } catch (Throwable th) {
                    th = th;
                }
            } else {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("business_operation", "UPSERT_CUSTOMER");
                jSONObject.put("customer_id", customerId);
                jSONObject.put("name", cursor.getString(0));
                jSONObject.put("phone", cursor.isNull(1) ? JSONObject.NULL : cursor.getString(1));
                jSONObject.put("address", cursor.isNull(2) ? JSONObject.NULL : cursor.getString(2));
                jSONObject.put("notes", cursor.isNull(3) ? JSONObject.NULL : cursor.getString(3));
                jSONObject.put("is_active", cursor.getInt(4) == 1);
                jSONObject.put("created_at", cursor.getString(5));
                jSONObject.put("updated_at", cursor.getString(6));
                CloseableKt.closeFinally(cursorRawQuery, null);
                Context appContext = this.appContext;
                Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
                SyncProtocolRepository syncProtocolRepository = new SyncProtocolRepository(appContext, null, 2, null);
                String string = UUID.randomUUID().toString();
                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                String string2 = jSONObject.toString();
                Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                syncProtocolRepository.enqueueLocalOperation(db, actorUserId, string, "UPSERT_CUSTOMER", "CUSTOMER", customerId, changeKind, string2);
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

    private final void insertAudit(SQLiteDatabase db, String actor, String action, String customerId, String now) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", UUID.randomUUID().toString());
        contentValues.put("user_id", actor);
        contentValues.put("action", action);
        contentValues.put("entity_type", "CUSTOMER");
        contentValues.put("entity_id", customerId);
        contentValues.put("created_at", now);
        Unit unit = Unit.INSTANCE;
        db.insertOrThrow("audit_log", null, contentValues);
    }

    private final void putNullable(ContentValues contentValues, String str, String str2) {
        String string;
        String str3 = null;
        if (str2 != null && (string = StringsKt.trim((CharSequence) str2).toString()) != null && string.length() > 0) {
            str3 = string;
        }
        if (str3 == null) {
            contentValues.putNull(str);
        } else {
            contentValues.put(str, str3);
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
