package com.hazem.dailystore.data;

import android.content.ContentValues;
import android.database.Cursor;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import net.zetetic.database.sqlcipher.SQLiteDatabase;

/* JADX INFO: compiled from: PermissionPolicy.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010 \u001a\u00020!2\n\u0010\"\u001a\u00060#j\u0002`$J\u0014\u0010'\u001a\u00020(2\n\u0010\"\u001a\u00060#j\u0002`$H\u0002J\"\u0010)\u001a\u00020!2\n\u0010\"\u001a\u00060#j\u0002`$2\u0006\u0010*\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u0005J\"\u0010,\u001a\u00020!2\n\u0010\"\u001a\u00060#j\u0002`$2\u0006\u0010*\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u0005J2\u0010-\u001a\u00020!2\n\u0010\"\u001a\u00060#j\u0002`$2\u0006\u0010.\u001a\u00020\u00052\u0006\u0010/\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u00052\u0006\u00100\u001a\u00020(J \u00101\u001a\b\u0012\u0004\u0012\u000203022\n\u0010\"\u001a\u00060#j\u0002`$2\u0006\u0010/\u001a\u00020\u0005J&\u00104\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020(052\n\u0010\"\u001a\u00060#j\u0002`$2\u0006\u0010/\u001a\u00020\u0005J\u0012\u00106\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000505J\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u00050\u001fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R*\u0010\u001b\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u001cj\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005`\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00050\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00050\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00068"}, d2 = {"Lcom/hazem/dailystore/data/PermissionPolicy;", "", "<init>", "()V", "USERS_MANAGE", "", "CATALOG_MANAGE", "EXCHANGE_RATE_MANAGE", "ACCOUNTS_MANAGE", "SUPPLIERS_MANAGE", "PURCHASES_MANAGE", "SUPPLIER_PAYMENTS_MANAGE", "SUPPLIER_PAYMENTS_CORRECT", "CUSTOMERS_READ", "CUSTOMERS_CREATE", "CUSTOMERS_MANAGE", "CUSTOMERS_COLLECT", "SALES_CHECKOUT", "REPORTS_READ", "DAILY_CASH_MANAGE", "SERVICE_TRANSFERS_MANAGE", "SERVICE_TRANSFERS_EXECUTE", "BACKUP_MANAGE", "INVENTORY_ADJUST", "RETURNS_PROCESS", "EXPENSES_MANAGE", "SYNC_MANAGE", "descriptions", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "cashierDefaults", "", "ensureDefaults", "", "db", "Lnet/zetetic/database/sqlcipher/SQLiteDatabase;", "Lcom/hazem/dailystore/data/AppSqliteDatabase;", "primaryOnlyPermissions", "secondaryAllowedPermissions", "isConfiguredSecondary", "", "requireAssigned", "userId", "permissionKey", "require", "setRolePermission", "actorUserId", "roleKey", "allowed", "listRolePermissions", "", "Lcom/hazem/dailystore/data/RolePermissionSetting;", "rolePermissions", "", "permissionDescriptions", "allPermissionKeys", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class PermissionPolicy {
    public static final PermissionPolicy INSTANCE = new PermissionPolicy();
    public static final String USERS_MANAGE = "users.manage";
    public static final String CATALOG_MANAGE = "catalog.manage";
    public static final String EXCHANGE_RATE_MANAGE = "exchange_rate.manage";
    public static final String ACCOUNTS_MANAGE = "accounts.manage";
    public static final String SUPPLIERS_MANAGE = "suppliers.manage";
    public static final String PURCHASES_MANAGE = "purchases.manage";
    public static final String SUPPLIER_PAYMENTS_MANAGE = "supplier_payments.manage";
    public static final String SUPPLIER_PAYMENTS_CORRECT = "supplier_payments.correct";
    public static final String CUSTOMERS_READ = "customers.read";
    public static final String CUSTOMERS_CREATE = "customers.create";
    public static final String CUSTOMERS_MANAGE = "customers.manage";
    public static final String CUSTOMERS_COLLECT = "customers.collect";
    public static final String SALES_CHECKOUT = "sales.checkout";
    public static final String REPORTS_READ = "reports.read";
    public static final String DAILY_CASH_MANAGE = "daily_cash.manage";
    public static final String SERVICE_TRANSFERS_MANAGE = "service_transfers.manage";
    public static final String SERVICE_TRANSFERS_EXECUTE = "service_transfers.execute";
    public static final String BACKUP_MANAGE = "backup.manage";
    public static final String INVENTORY_ADJUST = "inventory.adjust";
    public static final String RETURNS_PROCESS = "returns.process";
    public static final String EXPENSES_MANAGE = "expenses.manage";
    public static final String SYNC_MANAGE = "sync.manage";
    private static final LinkedHashMap<String, String> descriptions = MapsKt.linkedMapOf(TuplesKt.to(USERS_MANAGE, "\u0625\u062f\u0627\u0631\u0629 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645\u064a\u0646 \u0648\u0627\u0644\u0635\u0644\u0627\u062d\u064a\u0627\u062a"), TuplesKt.to(CATALOG_MANAGE, "\u0625\u062f\u0627\u0631\u0629 \u0627\u0644\u0623\u0635\u0646\u0627\u0641 \u0648\u0627\u0644\u062a\u0635\u0646\u064a\u0641\u0627\u062a \u0648\u0627\u0644\u0648\u062d\u062f\u0627\u062a"), TuplesKt.to(EXCHANGE_RATE_MANAGE, "\u062a\u063a\u064a\u064a\u0631 \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641"), TuplesKt.to(ACCOUNTS_MANAGE, "\u0625\u062f\u0627\u0631\u0629 \u0627\u0644\u0635\u0646\u0627\u062f\u064a\u0642 \u0648\u0627\u0644\u0645\u062d\u0627\u0641\u0638 \u0648\u0627\u0644\u062d\u0633\u0627\u0628\u0627\u062a"), TuplesKt.to(SUPPLIERS_MANAGE, "\u0625\u062f\u0627\u0631\u0629 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646"), TuplesKt.to(PURCHASES_MANAGE, "\u0625\u0646\u0634\u0627\u0621 \u0648\u0627\u0639\u062a\u0645\u0627\u062f \u0641\u0648\u0627\u062a\u064a\u0631 \u0627\u0644\u0634\u0631\u0627\u0621"), TuplesKt.to(SUPPLIER_PAYMENTS_MANAGE, "\u062a\u0633\u062c\u064a\u0644 \u062f\u0641\u0639\u0627\u062a \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646"), TuplesKt.to(SUPPLIER_PAYMENTS_CORRECT, "\u062a\u0635\u062d\u064a\u062d \u062f\u0641\u0639\u0627\u062a \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646"), TuplesKt.to(CUSTOMERS_READ, "\u0639\u0631\u0636 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0639\u0645\u0644\u0627\u0621"), TuplesKt.to(CUSTOMERS_CREATE, "\u0625\u0636\u0627\u0641\u0629 \u0639\u0645\u0644\u0627\u0621 \u062c\u062f\u062f"), TuplesKt.to(CUSTOMERS_MANAGE, "\u062a\u0639\u062f\u064a\u0644 \u0648\u0625\u064a\u0642\u0627\u0641 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0639\u0645\u0644\u0627\u0621"), TuplesKt.to(CUSTOMERS_COLLECT, "\u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0648\u0646 \u0627\u0644\u0639\u0645\u0644\u0627\u0621"), TuplesKt.to(SALES_CHECKOUT, "\u062a\u0646\u0641\u064a\u0630 \u0639\u0645\u0644\u064a\u0627\u062a \u0627\u0644\u0628\u064a\u0639"), TuplesKt.to(REPORTS_READ, "\u0639\u0631\u0636 \u0627\u0644\u062a\u0642\u0627\u0631\u064a\u0631"), TuplesKt.to(DAILY_CASH_MANAGE, "\u0625\u062f\u0627\u0631\u0629 \u0625\u0642\u0641\u0627\u0644 \u0648\u0641\u062a\u062d \u0627\u0644\u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u064a\u0648\u0645\u064a"), TuplesKt.to(SERVICE_TRANSFERS_MANAGE, "\u0625\u062f\u0627\u0631\u0629 \u0645\u062d\u0627\u0641\u0638 \u0627\u0644\u062e\u062f\u0645\u0627\u062a \u0648\u0625\u0639\u0627\u062f\u0629 \u0634\u062d\u0646\u0647\u0627"), TuplesKt.to(SERVICE_TRANSFERS_EXECUTE, "\u062a\u0646\u0641\u064a\u0630 \u062a\u062d\u0648\u064a\u0644\u0627\u062a \u0627\u0644\u0639\u0645\u0644\u0627\u0621 \u0644\u0644\u062e\u062f\u0645\u0627\u062a"), TuplesKt.to(BACKUP_MANAGE, "\u0627\u0644\u0646\u0633\u062e \u0627\u0644\u0627\u062d\u062a\u064a\u0627\u0637\u064a \u0648\u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629"), TuplesKt.to(INVENTORY_ADJUST, "\u062a\u0633\u0648\u064a\u0627\u062a \u0648\u062c\u0631\u062f \u0627\u0644\u0645\u062e\u0632\u0648\u0646"), TuplesKt.to(RETURNS_PROCESS, "\u062a\u0646\u0641\u064a\u0630 \u0627\u0644\u0645\u0631\u062a\u062c\u0639\u0627\u062a \u0648\u0631\u062f \u0627\u0644\u0623\u0645\u0648\u0627\u0644"), TuplesKt.to(EXPENSES_MANAGE, "\u0625\u062f\u0627\u0631\u0629 \u0627\u0644\u0645\u0635\u0627\u0631\u064a\u0641"), TuplesKt.to(SYNC_MANAGE, "\u0625\u062f\u0627\u0631\u0629 \u0627\u0644\u0623\u062c\u0647\u0632\u0629 \u0648\u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629"));
    private static final Set<String> cashierDefaults = SetsKt.setOf((Object[]) new String[]{SALES_CHECKOUT, CUSTOMERS_READ, CUSTOMERS_CREATE, CUSTOMERS_COLLECT});
    private static final Set<String> primaryOnlyPermissions = SetsKt.setOf((Object[]) new String[]{USERS_MANAGE, CATALOG_MANAGE, EXCHANGE_RATE_MANAGE, ACCOUNTS_MANAGE, SUPPLIERS_MANAGE, PURCHASES_MANAGE, SUPPLIER_PAYMENTS_MANAGE, SUPPLIER_PAYMENTS_CORRECT, CUSTOMERS_CREATE, CUSTOMERS_MANAGE, CUSTOMERS_COLLECT, SALES_CHECKOUT, DAILY_CASH_MANAGE, SERVICE_TRANSFERS_MANAGE, SERVICE_TRANSFERS_EXECUTE, BACKUP_MANAGE, INVENTORY_ADJUST, RETURNS_PROCESS, EXPENSES_MANAGE});
    private static final Set<String> secondaryAllowedPermissions = SetsKt.setOf((Object[]) new String[]{SALES_CHECKOUT, CUSTOMERS_READ, CUSTOMERS_COLLECT, SERVICE_TRANSFERS_EXECUTE, SYNC_MANAGE});

    private PermissionPolicy() {
    }

    public final void ensureDefaults(SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        for (Map.Entry<String, String> entry : descriptions.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            ContentValues contentValues = new ContentValues();
            contentValues.put("permission_key", key);
            contentValues.put("description", value);
            Unit unit = Unit.INSTANCE;
            db.insertWithOnConflict("permissions", null, contentValues, 4);
            for (String str : CollectionsKt.listOf((Object[]) new String[]{"ADMIN", "CASHIER"})) {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("role_key", str);
                contentValues2.put("permission_key", key);
                contentValues2.put("allowed", Integer.valueOf((Intrinsics.areEqual(str, "ADMIN") || cashierDefaults.contains(key)) ? 1 : 0));
                Unit unit2 = Unit.INSTANCE;
                db.insertWithOnConflict("role_permissions", null, contentValues2, 4);
            }
        }
    }

    private final boolean isConfiguredSecondary(SQLiteDatabase db) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            String string = cursor.moveToFirst() ? cursor.getString(0) : null;
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (Intrinsics.areEqual(string, "SECONDARY")) {
                return true;
            }
            Cursor cursorRawQuery2 = db.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_id'", (String[]) null);
            try {
                Cursor cursor2 = cursorRawQuery2;
                String string2 = cursor2.moveToFirst() ? cursor2.getString(0) : null;
                CloseableKt.closeFinally(cursorRawQuery2, null);
                if (string2 == null) {
                    return false;
                }
                Cursor cursorRawQuery3 = db.rawQuery("SELECT device_role FROM sync_devices WHERE device_id=?", new String[]{string2});
                try {
                    Cursor cursor3 = cursorRawQuery3;
                    String string3 = cursor3.moveToFirst() ? cursor3.getString(0) : null;
                    CloseableKt.closeFinally(cursorRawQuery3, null);
                    return Intrinsics.areEqual(string3, "SECONDARY");
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

    public final void requireAssigned(SQLiteDatabase db, String userId, String permissionKey) {
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(permissionKey, "permissionKey");
        if (StringsKt.isBlank(userId)) {
            throw new IllegalArgumentException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
        }
        if (!descriptions.containsKey(permissionKey)) {
            throw new IllegalArgumentException("\u0627\u0644\u0635\u0644\u0627\u062d\u064a\u0629 \u0627\u0644\u0645\u0637\u0644\u0648\u0628\u0629 \u063a\u064a\u0631 \u0645\u0639\u0631\u0648\u0641\u0629".toString());
        }
        if (isConfiguredSecondary(db) && !secondaryAllowedPermissions.contains(permissionKey)) {
            throw new IllegalArgumentException("\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u0645\u062e\u0635\u0635 \u0644\u0644\u0628\u064a\u0639 \u0648\u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u0641\u0642\u0637\u061b \u0647\u0630\u0647 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0645\u062a\u0627\u062d\u0629 \u0645\u0646 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a".toString());
        }
        Cursor cursorRawQuery = db.rawQuery("SELECT 1\n               FROM users u\n               JOIN role_permissions rp ON rp.role_key=u.role_key\n               JOIN permissions p ON p.permission_key=rp.permission_key\n               WHERE u.id=? AND u.is_active=1 AND u.must_change_password=0\n                 AND p.permission_key=? AND rp.allowed=1", new String[]{userId, permissionKey});
        try {
            boolean zMoveToFirst = cursorRawQuery.moveToFirst();
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (!zMoveToFirst) {
                throw new IllegalArgumentException("\u0644\u064a\u0633 \u0644\u062f\u064a\u0643 \u0635\u0644\u0627\u062d\u064a\u0629 \u062a\u0646\u0641\u064a\u0630 \u0647\u0630\u0627 \u0627\u0644\u0625\u062c\u0631\u0627\u0621".toString());
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

    public final void require(SQLiteDatabase db, String userId, String permissionKey) {
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(permissionKey, "permissionKey");
        if (StringsKt.isBlank(userId)) {
            throw new IllegalArgumentException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
        }
        if (!descriptions.containsKey(permissionKey)) {
            throw new IllegalArgumentException("\u0627\u0644\u0635\u0644\u0627\u062d\u064a\u0629 \u0627\u0644\u0645\u0637\u0644\u0648\u0628\u0629 \u063a\u064a\u0631 \u0645\u0639\u0631\u0648\u0641\u0629".toString());
        }
        if (primaryOnlyPermissions.contains(permissionKey) && isConfiguredSecondary(db)) {
            throw new IllegalArgumentException("\u0647\u0630\u0627 \u0627\u0644\u062c\u0647\u0627\u0632 \u062b\u0627\u0646\u0648\u064a\u061b \u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u0631\u062d\u064a\u0644 \u0639\u0645\u0644\u064a\u0629 \u0645\u0627\u0644\u064a\u0629 \u0623\u0648 \u0645\u062e\u0632\u0646\u064a\u0629 \u0645\u062d\u0644\u064a\u0627\u064b \u0642\u0628\u0644 \u0627\u0639\u062a\u0645\u0627\u062f\u0647\u0627 \u0645\u0646 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a".toString());
        }
        requireAssigned(db, userId, permissionKey);
    }

    public final void setRolePermission(SQLiteDatabase db, String actorUserId, String roleKey, String permissionKey, boolean allowed) {
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(roleKey, "roleKey");
        Intrinsics.checkNotNullParameter(permissionKey, "permissionKey");
        if (isConfiguredSecondary(db)) {
            throw new IllegalArgumentException("\u0625\u062f\u0627\u0631\u0629 \u0635\u0644\u0627\u062d\u064a\u0627\u062a \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645\u064a\u0646 \u0645\u062a\u0627\u062d\u0629 \u0645\u0646 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0641\u0642\u0637".toString());
        }
        Cursor cursorRawQuery = db.rawQuery("SELECT 1 FROM users\n               WHERE id=? AND role_key='ADMIN' AND is_active=1 AND must_change_password=0", new String[]{actorUserId});
        try {
            boolean zMoveToFirst = cursorRawQuery.moveToFirst();
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (!zMoveToFirst) {
                throw new IllegalArgumentException("\u064a\u062c\u0628 \u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062f\u062e\u0648\u0644 \u0628\u062d\u0633\u0627\u0628 \u0645\u062f\u064a\u0631 \u0646\u0634\u0637".toString());
            }
            if (!SetsKt.setOf((Object[]) new String[]{"ADMIN", "CASHIER"}).contains(roleKey)) {
                throw new IllegalArgumentException("\u0627\u0644\u062f\u0648\u0631 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
            }
            if (!descriptions.containsKey(permissionKey)) {
                throw new IllegalArgumentException("\u0627\u0644\u0635\u0644\u0627\u062d\u064a\u0629 \u063a\u064a\u0631 \u0645\u0639\u0631\u0648\u0641\u0629".toString());
            }
            if (Intrinsics.areEqual(roleKey, "ADMIN") && !allowed) {
                throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0633\u062d\u0628 \u0635\u0644\u0627\u062d\u064a\u0627\u062a \u0627\u0644\u0645\u062f\u064a\u0631 \u0627\u0644\u0623\u0633\u0627\u0633\u064a\u0629".toString());
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            String str = simpleDateFormat.format(new Date());
            db.beginTransaction();
            try {
                Cursor cursorRawQuery2 = db.rawQuery("SELECT allowed FROM role_permissions WHERE role_key=? AND permission_key=?", new String[]{roleKey, permissionKey});
                try {
                    Cursor cursor = cursorRawQuery2;
                    boolean z = cursor.moveToFirst() && cursor.getInt(0) == 1;
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("role_key", roleKey);
                    contentValues.put("permission_key", permissionKey);
                    contentValues.put("allowed", Integer.valueOf(allowed ? 1 : 0));
                    Unit unit = Unit.INSTANCE;
                    db.insertWithOnConflict("role_permissions", null, contentValues, 5);
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("id", UUID.randomUUID().toString());
                    contentValues2.put("user_id", actorUserId);
                    contentValues2.put("action", "ROLE_PERMISSION_CHANGE");
                    contentValues2.put("entity_type", "ROLE_PERMISSION");
                    contentValues2.put("entity_id", roleKey + ":" + permissionKey);
                    StringBuilder sb = new StringBuilder("{\"allowed\":");
                    sb.append(z);
                    sb.append("}");
                    contentValues2.put("before_json", sb.toString());
                    contentValues2.put("after_json", "{\"allowed\":" + allowed + "}");
                    contentValues2.put("created_at", str);
                    Unit unit2 = Unit.INSTANCE;
                    db.insertOrThrow("audit_log", null, contentValues2);
                    db.setTransactionSuccessful();
                    db.endTransaction();
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(cursorRawQuery2, th);
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                db.endTransaction();
                throw th3;
            }
        } catch (Throwable th4) {
            try {
                throw th4;
            } catch (Throwable th5) {
                CloseableKt.closeFinally(cursorRawQuery, th4);
                throw th5;
            }
        }
    }

    public final List<RolePermissionSetting> listRolePermissions(SQLiteDatabase db, String roleKey) throws IOException {
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(roleKey, "roleKey");
        if (!SetsKt.setOf((Object[]) new String[]{"ADMIN", "CASHIER"}).contains(roleKey)) {
            throw new IllegalArgumentException("\u0627\u0644\u062f\u0648\u0631 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
        }
        LinkedHashMap<String, String> linkedHashMap = descriptions;
        ArrayList arrayList = new ArrayList(linkedHashMap.size());
        for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            Cursor cursorRawQuery = db.rawQuery("SELECT allowed FROM role_permissions WHERE role_key=? AND permission_key=?", new String[]{roleKey, key});
            try {
                Cursor cursor = cursorRawQuery;
                boolean z = false;
                if (cursor.moveToFirst() && cursor.getInt(0) == 1) {
                    z = true;
                }
                CloseableKt.closeFinally(cursorRawQuery, null);
                arrayList.add(new RolePermissionSetting(key, value, z));
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

    public final Map<String, Boolean> rolePermissions(SQLiteDatabase db, String roleKey) throws IOException {
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(roleKey, "roleKey");
        List<RolePermissionSetting> listListRolePermissions = listRolePermissions(db, roleKey);
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(listListRolePermissions, 10)), 16));
        for (RolePermissionSetting rolePermissionSetting : listListRolePermissions) {
            Pair pair = TuplesKt.to(rolePermissionSetting.getKey(), Boolean.valueOf(rolePermissionSetting.getAllowed()));
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        return linkedHashMap;
    }

    public final Map<String, String> permissionDescriptions() {
        return MapsKt.toMap(descriptions);
    }

    public final Set<String> allPermissionKeys() {
        Set<String> setKeySet = descriptions.keySet();
        Intrinsics.checkNotNullExpressionValue(setKeySet, "<get-keys>(...)");
        return setKeySet;
    }
}
