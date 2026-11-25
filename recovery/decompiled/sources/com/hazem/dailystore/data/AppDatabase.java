package com.hazem.dailystore.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import com.hazem.dailystore.SyncDeviceManagementActivity;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import net.zetetic.database.sqlcipher.SQLiteDatabase;

/* JADX INFO: compiled from: AppDatabase.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0016\b\u0000\u0018\u0000 )2\u00020\u0001:\u0001)B'\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0014\u0010\n\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\u0016J\r\u0010\u000f\u001a\u00020\u000bH\u0000\u00a2\u0006\u0002\b\u0010J\u0014\u0010\u0011\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\u0016J$\u0010\u0012\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\rj\u0002`\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0016J\u0014\u0010\u0016\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\u0002J\u0014\u0010\u0017\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\u0002J\u0014\u0010\u0018\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\u0002J\u0014\u0010\u0019\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\u0002J\u0014\u0010\u001a\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\u0002J\u0014\u0010\u001b\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\u0002J\u0014\u0010\u001c\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\u0002J\u0014\u0010\u001d\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\u0002J\u0014\u0010\u001e\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\u0002J\u0014\u0010\u001f\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\u0002J\u0014\u0010 \u001a\u00020\u000b2\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\u0002J\u0014\u0010!\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\u0002J\u0014\u0010\"\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\u0002J\u0014\u0010#\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\u0002J\u0014\u0010$\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\u0002J\u0014\u0010%\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\u0002J\u0014\u0010&\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\u0002J\u0014\u0010'\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\u0002J\u0014\u0010(\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\u0002\u00a8\u0006*"}, d2 = {"Lcom/hazem/dailystore/data/AppDatabase;", "Lcom/hazem/dailystore/data/SqlCipherOpenHelperBase;", "context", "Landroid/content/Context;", "databaseName", "", "databaseKey", "", "<init>", "(Landroid/content/Context;Ljava/lang/String;[B)V", "onConfigure", "", "db", "Lnet/zetetic/database/sqlcipher/SQLiteDatabase;", "Lcom/hazem/dailystore/data/AppSqliteDatabase;", "checkpointAndCloseForBackup", "checkpointAndCloseForBackup$app_release", "onCreate", "onUpgrade", "oldVersion", "", "newVersion", "ensureServiceTransferPrecisionSchema", "ensureServiceTransferReceivableSyncSchema", "ensureServiceTransferReceivableSchema", "ensureSyncCategoryMappingSchema", "ensureSyncAckDeliverySchema", "ensureSyncUnitMappingSchema", "ensureSyncCashAccountMappingSchema", "ensureSyncInboxOriginTimestampColumn", "ensureSyncInboxActorColumn", "ensureSyncDeviceIdentitySchema", "ensureSyncDeviceRevocationSchema", "ensureSyncActorMappingSchema", "ensureSalesReturnSchema", "ensureInventoryAdjustmentSchema", "ensureSyncProtocolSchema", "ensureOperatingExpenseSchema", "ensureSupplierReturnSchema", "ensureFifoSchema", "backfillFifoCostLayers", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AppDatabase extends SqlCipherOpenHelperBase {
    public static final String DATABASE_NAME = "the_daily_shop.db";
    public static final int DATABASE_VERSION = 33;
    private static volatile AppDatabase INSTANCE;
    private static volatile boolean plaintextBackendForTests;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String[] SCHEMA = {"CREATE TABLE app_settings (\n    setting_key TEXT PRIMARY KEY NOT NULL,\n    setting_value TEXT NOT NULL,\n    updated_at TEXT NOT NULL\n)", "CREATE TABLE users (\n    id TEXT PRIMARY KEY NOT NULL,\n    username TEXT NOT NULL COLLATE NOCASE UNIQUE,\n    display_name TEXT NOT NULL,\n    password_hash TEXT NOT NULL,\n    password_salt TEXT NOT NULL,\n    role_key TEXT NOT NULL,\n    is_active INTEGER NOT NULL DEFAULT 1 CHECK(is_active IN (0,1)),\n    must_change_password INTEGER NOT NULL DEFAULT 1 CHECK(must_change_password IN (0,1)),\n    created_at TEXT NOT NULL,\n    updated_at TEXT NOT NULL,\n    last_login_at TEXT\n)", "CREATE TABLE permissions (\n    permission_key TEXT PRIMARY KEY NOT NULL,\n    description TEXT NOT NULL\n)", "CREATE TABLE role_permissions (\n    role_key TEXT NOT NULL,\n    permission_key TEXT NOT NULL,\n    allowed INTEGER NOT NULL CHECK(allowed IN (0,1)),\n    PRIMARY KEY(role_key, permission_key),\n    FOREIGN KEY(permission_key) REFERENCES permissions(permission_key)\n        ON UPDATE CASCADE ON DELETE RESTRICT\n)", "CREATE TABLE categories (\n    id INTEGER PRIMARY KEY AUTOINCREMENT,\n    name TEXT NOT NULL COLLATE NOCASE UNIQUE,\n    parent_id INTEGER,\n    is_active INTEGER NOT NULL DEFAULT 1 CHECK(is_active IN (0,1)),\n    created_at TEXT NOT NULL,\n    FOREIGN KEY(parent_id) REFERENCES categories(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)", "CREATE TABLE units (\n    id INTEGER PRIMARY KEY AUTOINCREMENT,\n    name TEXT NOT NULL COLLATE NOCASE UNIQUE,\n    symbol TEXT NOT NULL,\n    decimal_places INTEGER NOT NULL DEFAULT 0\n        CHECK(decimal_places BETWEEN 0 AND 3)\n)", "CREATE TABLE products (\n    id TEXT PRIMARY KEY NOT NULL,\n    barcode TEXT UNIQUE,\n    sku TEXT UNIQUE,\n    name TEXT NOT NULL COLLATE NOCASE,\n    category_id INTEGER,\n    unit_id INTEGER NOT NULL,\n    purchase_cost_usd_cents INTEGER NOT NULL DEFAULT 0\n        CHECK(purchase_cost_usd_cents >= 0),\n    sale_price_usd_cents INTEGER NOT NULL DEFAULT 0\n        CHECK(sale_price_usd_cents >= 0),\n    track_inventory INTEGER NOT NULL DEFAULT 1\n        CHECK(track_inventory IN (0,1)),\n    minimum_stock REAL NOT NULL DEFAULT 0 CHECK(minimum_stock >= 0),\n    is_active INTEGER NOT NULL DEFAULT 1 CHECK(is_active IN (0,1)),\n    created_at TEXT NOT NULL,\n    updated_at TEXT NOT NULL,\n    FOREIGN KEY(category_id) REFERENCES categories(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(unit_id) REFERENCES units(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)", "CREATE TABLE warehouses (\n    id TEXT PRIMARY KEY NOT NULL,\n    name TEXT NOT NULL COLLATE NOCASE UNIQUE,\n    is_default INTEGER NOT NULL DEFAULT 0 CHECK(is_default IN (0,1)),\n    is_active INTEGER NOT NULL DEFAULT 1 CHECK(is_active IN (0,1)),\n    created_at TEXT NOT NULL\n)", "CREATE TABLE inventory_balances (\n    product_id TEXT NOT NULL,\n    warehouse_id TEXT NOT NULL,\n    quantity REAL NOT NULL DEFAULT 0,\n    updated_at TEXT NOT NULL,\n    PRIMARY KEY(product_id, warehouse_id),\n    FOREIGN KEY(product_id) REFERENCES products(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(warehouse_id) REFERENCES warehouses(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)", "CREATE TABLE inventory_movements (\n    id TEXT PRIMARY KEY NOT NULL,\n    product_id TEXT NOT NULL,\n    warehouse_id TEXT NOT NULL,\n    movement_type TEXT NOT NULL CHECK(movement_type IN\n        ('OPENING','PURCHASE','SALE','SALE_RETURN','PURCHASE_RETURN',\n         'ADJUSTMENT_IN','ADJUSTMENT_OUT','TRANSFER_IN','TRANSFER_OUT')),\n    quantity_delta REAL NOT NULL CHECK(quantity_delta <> 0),\n    unit_cost_usd_cents INTEGER NOT NULL DEFAULT 0\n        CHECK(unit_cost_usd_cents >= 0),\n    source_type TEXT,\n    source_id TEXT,\n    operation_id TEXT UNIQUE,\n    note TEXT,\n    created_by TEXT,\n    created_at TEXT NOT NULL,\n    FOREIGN KEY(product_id) REFERENCES products(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(warehouse_id) REFERENCES warehouses(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(created_by) REFERENCES users(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)", "CREATE TABLE exchange_rates (\n    id TEXT PRIMARY KEY NOT NULL,\n    currency_code TEXT NOT NULL,\n    rate_syp_per_usd_milli INTEGER NOT NULL\n        CHECK(rate_syp_per_usd_milli > 0),\n    effective_at TEXT NOT NULL,\n    created_by TEXT,\n    created_at TEXT NOT NULL,\n    FOREIGN KEY(created_by) REFERENCES users(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)", "CREATE TABLE customers (\n    id TEXT PRIMARY KEY NOT NULL,\n    name TEXT NOT NULL,\n    phone TEXT,\n    address TEXT,\n    notes TEXT,\n    opening_balance_minor INTEGER NOT NULL DEFAULT 0,\n    opening_balance_currency TEXT NOT NULL DEFAULT 'SYP',\n    is_active INTEGER NOT NULL DEFAULT 1 CHECK(is_active IN (0,1)),\n    created_at TEXT NOT NULL,\n    updated_at TEXT NOT NULL\n)", "CREATE TABLE suppliers (\n    id TEXT PRIMARY KEY NOT NULL,\n    name TEXT NOT NULL,\n    phone TEXT,\n    address TEXT,\n    notes TEXT,\n    opening_balance_minor INTEGER NOT NULL DEFAULT 0,\n    opening_balance_currency TEXT NOT NULL DEFAULT 'SYP',\n    is_active INTEGER NOT NULL DEFAULT 1 CHECK(is_active IN (0,1)),\n    created_at TEXT NOT NULL,\n    updated_at TEXT NOT NULL\n)", "CREATE TABLE purchase_invoices (\n    id TEXT PRIMARY KEY NOT NULL,\n    invoice_number TEXT NOT NULL UNIQUE,\n    supplier_id TEXT,\n    warehouse_id TEXT NOT NULL,\n    invoice_date TEXT NOT NULL,\n    status TEXT NOT NULL CHECK(status IN\n        ('DRAFT','POSTED','PARTIALLY_PAID','PAID','CANCELLED')),\n    subtotal_usd_cents INTEGER NOT NULL DEFAULT 0,\n    discount_usd_cents INTEGER NOT NULL DEFAULT 0,\n    total_usd_cents INTEGER NOT NULL DEFAULT 0,\n    exchange_rate_syp_per_usd_milli INTEGER NOT NULL DEFAULT 0,\n    notes TEXT,\n    created_by TEXT,\n    created_at TEXT NOT NULL,\n    updated_at TEXT NOT NULL,\n    FOREIGN KEY(supplier_id) REFERENCES suppliers(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(warehouse_id) REFERENCES warehouses(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(created_by) REFERENCES users(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    CHECK(subtotal_usd_cents >= 0 AND discount_usd_cents >= 0\n        AND total_usd_cents >= 0)\n)", "CREATE TABLE purchase_items (\n    id TEXT PRIMARY KEY NOT NULL,\n    purchase_invoice_id TEXT NOT NULL,\n    product_id TEXT NOT NULL,\n    quantity REAL NOT NULL CHECK(quantity > 0),\n    unit_cost_usd_cents INTEGER NOT NULL CHECK(unit_cost_usd_cents >= 0),\n    line_total_usd_cents INTEGER NOT NULL CHECK(line_total_usd_cents >= 0),\n    FOREIGN KEY(purchase_invoice_id) REFERENCES purchase_invoices(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(product_id) REFERENCES products(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)", "CREATE TABLE sales_invoices (\n    id TEXT PRIMARY KEY NOT NULL,\n    invoice_number TEXT NOT NULL UNIQUE,\n    customer_id TEXT,\n    sale_type TEXT NOT NULL DEFAULT 'CASH' CHECK(sale_type IN ('CASH','CREDIT')),\n    warehouse_id TEXT NOT NULL,\n    invoice_date TEXT NOT NULL,\n    status TEXT NOT NULL CHECK(status IN\n        ('DRAFT','POSTED','PARTIALLY_PAID','PAID','CANCELLED')),\n    subtotal_usd_cents INTEGER NOT NULL DEFAULT 0,\n    discount_usd_cents INTEGER NOT NULL DEFAULT 0,\n    total_usd_cents INTEGER NOT NULL DEFAULT 0,\n    exchange_rate_syp_per_usd_milli INTEGER NOT NULL DEFAULT 0,\n    notes TEXT,\n    created_by TEXT,\n    created_at TEXT NOT NULL,\n    updated_at TEXT NOT NULL,\n    posted_at TEXT,\n    posted_operating_date TEXT,\n    FOREIGN KEY(customer_id) REFERENCES customers(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(warehouse_id) REFERENCES warehouses(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(created_by) REFERENCES users(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    CHECK(subtotal_usd_cents >= 0 AND discount_usd_cents >= 0\n        AND total_usd_cents >= 0)\n)", "CREATE TABLE sales_items (\n    id TEXT PRIMARY KEY NOT NULL,\n    sales_invoice_id TEXT NOT NULL,\n    product_id TEXT NOT NULL,\n    quantity REAL NOT NULL CHECK(quantity > 0),\n    unit_price_usd_cents INTEGER NOT NULL CHECK(unit_price_usd_cents >= 0),\n    unit_cost_usd_cents INTEGER NOT NULL DEFAULT 0\n        CHECK(unit_cost_usd_cents >= 0),\n    line_total_usd_cents INTEGER NOT NULL CHECK(line_total_usd_cents >= 0),\n    FOREIGN KEY(sales_invoice_id) REFERENCES sales_invoices(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(product_id) REFERENCES products(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)", "CREATE TABLE cash_accounts (\n    id TEXT PRIMARY KEY NOT NULL,\n    name TEXT NOT NULL,\n    account_type TEXT NOT NULL CHECK(account_type IN\n        ('CASH_BOX','WALLET','BANK','OTHER')),\n    currency_code TEXT NOT NULL,\n    opening_balance_minor INTEGER NOT NULL DEFAULT 0,\n    is_active INTEGER NOT NULL DEFAULT 1 CHECK(is_active IN (0,1)),\n    created_at TEXT NOT NULL\n)", "CREATE TABLE system_cash_account_roles (\n    role_key TEXT PRIMARY KEY NOT NULL,\n    account_id TEXT NOT NULL,\n    linked_at TEXT NOT NULL,\n    FOREIGN KEY(account_id) REFERENCES cash_accounts(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)", "CREATE TABLE daily_cash_sessions (\n    operating_date TEXT PRIMARY KEY NOT NULL,\n    primary_cash_account_id TEXT NOT NULL,\n    created_by TEXT,\n    created_at TEXT NOT NULL,\n    FOREIGN KEY(primary_cash_account_id) REFERENCES cash_accounts(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(created_by) REFERENCES users(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)", "CREATE TABLE daily_supplier_rollovers (\n    operating_date TEXT PRIMARY KEY NOT NULL,\n    account_id TEXT NOT NULL,\n    allocated_minor INTEGER NOT NULL CHECK(allocated_minor >= 0),\n    note TEXT,\n    updated_by TEXT NOT NULL,\n    created_at TEXT NOT NULL,\n    updated_at TEXT NOT NULL,\n    FOREIGN KEY(account_id) REFERENCES cash_accounts(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(updated_by) REFERENCES users(id) ON UPDATE RESTRICT ON DELETE RESTRICT\n)", "CREATE TABLE wallet_daily_openings (\n    operating_date TEXT NOT NULL,\n    account_id TEXT NOT NULL,\n    opening_minor INTEGER NOT NULL CHECK(opening_minor >= 0),\n    note TEXT,\n    updated_by TEXT NOT NULL,\n    created_at TEXT NOT NULL,\n    updated_at TEXT NOT NULL,\n    PRIMARY KEY(operating_date, account_id),\n    FOREIGN KEY(account_id) REFERENCES cash_accounts(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(updated_by) REFERENCES users(id) ON UPDATE RESTRICT ON DELETE RESTRICT\n)", "CREATE TABLE cash_account_transfers (\n    id TEXT PRIMARY KEY NOT NULL,\n    operation_id TEXT NOT NULL UNIQUE,\n    source_account_id TEXT NOT NULL,\n    destination_account_id TEXT NOT NULL,\n    amount_minor INTEGER NOT NULL CHECK(amount_minor > 0),\n    currency_code TEXT NOT NULL,\n    reference TEXT,\n    note TEXT,\n    payload_hash TEXT NOT NULL,\n    created_by TEXT NOT NULL,\n    created_at TEXT NOT NULL,\n    FOREIGN KEY(source_account_id) REFERENCES cash_accounts(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(destination_account_id) REFERENCES cash_accounts(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(created_by) REFERENCES users(id) ON UPDATE RESTRICT ON DELETE RESTRICT\n)", "CREATE TABLE service_wallets (\n    service_key TEXT PRIMARY KEY NOT NULL,\n    name TEXT NOT NULL,\n    created_at TEXT NOT NULL\n)", "CREATE TABLE service_wallet_account_links (\n    service_key TEXT PRIMARY KEY NOT NULL,\n    account_id TEXT NOT NULL UNIQUE,\n    linked_at TEXT NOT NULL,\n    FOREIGN KEY(service_key) REFERENCES service_wallets(service_key)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(account_id) REFERENCES cash_accounts(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)", "CREATE TABLE service_wallet_transactions (\n    id TEXT PRIMARY KEY NOT NULL,\n    service_key TEXT NOT NULL,\n    direction TEXT NOT NULL CHECK(direction IN ('IN','OUT')),\n    amount_minor INTEGER NOT NULL CHECK(amount_minor > 0),\n    transaction_type TEXT NOT NULL,\n    source_id TEXT,\n    operation_id TEXT NOT NULL UNIQUE,\n    payload_hash TEXT,\n    fee_minor INTEGER NOT NULL DEFAULT 0 CHECK(fee_minor >= 0),\n    note TEXT,\n    created_by TEXT NOT NULL,\n    created_at TEXT NOT NULL,\n    operating_date TEXT,\n    FOREIGN KEY(service_key) REFERENCES service_wallets(service_key) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(created_by) REFERENCES users(id) ON UPDATE RESTRICT ON DELETE RESTRICT\n)", "CREATE TABLE service_transfers (\n    id TEXT PRIMARY KEY NOT NULL,\n    operation_id TEXT NOT NULL UNIQUE,\n    service_type TEXT NOT NULL CHECK(service_type IN ('SYRIATEL_BALANCE','SYRIATEL_CASH','SHAM_CASH')),\n    service_key TEXT NOT NULL,\n    amount_delivered_minor INTEGER NOT NULL CHECK(amount_delivered_minor > 0),\n    profit_minor INTEGER NOT NULL CHECK(profit_minor >= 0),\n    provider_fee_minor INTEGER NOT NULL CHECK(provider_fee_minor >= 0),\n    customer_charge_minor INTEGER NOT NULL CHECK(customer_charge_minor > 0),\n    receipt_account_id TEXT NOT NULL,\n    receipt_channel TEXT NOT NULL CHECK(receipt_channel IN ('CASH','ELECTRONIC')),\n    reference TEXT,\n    note TEXT,\n    payload_hash TEXT NOT NULL,\n    created_by TEXT NOT NULL,\n    created_at TEXT NOT NULL,\n    operating_date TEXT,\n    status TEXT NOT NULL DEFAULT 'POSTED' CHECK(status IN ('POSTED','REVERSED')),\n    reversed_at TEXT,\n    reversed_by TEXT,\n    reversal_reason TEXT,\n    FOREIGN KEY(service_key) REFERENCES service_wallets(service_key) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(receipt_account_id) REFERENCES cash_accounts(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(created_by) REFERENCES users(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(reversed_by) REFERENCES users(id) ON UPDATE RESTRICT ON DELETE RESTRICT\n)", "CREATE TABLE customer_credit_revaluations (\n    id TEXT PRIMARY KEY NOT NULL,\n    invoice_id TEXT NOT NULL,\n    customer_id TEXT NOT NULL,\n    outstanding_usd_cents INTEGER NOT NULL CHECK(outstanding_usd_cents > 0),\n    previous_syp_minor INTEGER NOT NULL CHECK(previous_syp_minor >= 0),\n    new_syp_minor INTEGER NOT NULL CHECK(new_syp_minor >= 0),\n    applied_rate_syp_per_usd_milli INTEGER NOT NULL CHECK(applied_rate_syp_per_usd_milli > 0),\n    calculation_basis TEXT NOT NULL,\n    operation_id TEXT NOT NULL UNIQUE,\n    created_at TEXT NOT NULL,\n    FOREIGN KEY(invoice_id) REFERENCES sales_invoices(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(customer_id) REFERENCES customers(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)", "CREATE TABLE cash_transactions (\n    id TEXT PRIMARY KEY NOT NULL,\n    account_id TEXT NOT NULL,\n    direction TEXT NOT NULL CHECK(direction IN ('IN','OUT')),\n    amount_minor INTEGER NOT NULL CHECK(amount_minor > 0),\n    currency_code TEXT NOT NULL,\n    exchange_rate_syp_per_usd_milli INTEGER,\n    transaction_type TEXT NOT NULL,\n    source_type TEXT,\n    source_id TEXT,\n    operation_id TEXT UNIQUE,\n    description TEXT,\n    created_by TEXT,\n    created_at TEXT NOT NULL,\n    operating_date TEXT,\n    FOREIGN KEY(account_id) REFERENCES cash_accounts(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(created_by) REFERENCES users(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)", "CREATE TABLE invoice_payments (\n    id TEXT PRIMARY KEY NOT NULL,\n    invoice_type TEXT NOT NULL CHECK(invoice_type IN ('SALE','PURCHASE')),\n    invoice_id TEXT NOT NULL,\n    cash_transaction_id TEXT NOT NULL UNIQUE,\n    amount_minor INTEGER NOT NULL CHECK(amount_minor > 0),\n    currency_code TEXT NOT NULL,\n    exchange_rate_syp_per_usd_milli INTEGER,\n    created_at TEXT NOT NULL,\n    FOREIGN KEY(cash_transaction_id) REFERENCES cash_transactions(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)", "CREATE TABLE debts (\n    id TEXT PRIMARY KEY NOT NULL,\n    party_type TEXT NOT NULL CHECK(party_type IN ('CUSTOMER','SUPPLIER')),\n    party_id TEXT NOT NULL,\n    direction TEXT NOT NULL CHECK(direction IN ('RECEIVABLE','PAYABLE')),\n    amount_minor INTEGER NOT NULL CHECK(amount_minor > 0),\n    currency_code TEXT NOT NULL,\n    source_type TEXT,\n    source_id TEXT,\n    operation_id TEXT UNIQUE,\n    description TEXT,\n    created_by TEXT,\n    created_at TEXT NOT NULL,\n    FOREIGN KEY(created_by) REFERENCES users(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)", "CREATE TABLE returns (\n    id TEXT PRIMARY KEY NOT NULL,\n    return_number TEXT NOT NULL UNIQUE,\n    original_invoice_type TEXT NOT NULL CHECK(original_invoice_type IN\n        ('SALE','PURCHASE')),\n    original_invoice_id TEXT NOT NULL,\n    party_type TEXT CHECK(party_type IN ('CUSTOMER','SUPPLIER')),\n    party_id TEXT,\n    return_date TEXT NOT NULL,\n    total_usd_cents INTEGER NOT NULL DEFAULT 0 CHECK(total_usd_cents >= 0),\n    reason TEXT,\n    status TEXT NOT NULL CHECK(status IN ('DRAFT','POSTED','CANCELLED')),\n    created_by TEXT,\n    created_at TEXT NOT NULL,\n    FOREIGN KEY(created_by) REFERENCES users(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)", "CREATE TABLE return_items (\n    id TEXT PRIMARY KEY NOT NULL,\n    return_id TEXT NOT NULL,\n    product_id TEXT NOT NULL,\n    quantity REAL NOT NULL CHECK(quantity > 0),\n    unit_price_usd_cents INTEGER NOT NULL CHECK(unit_price_usd_cents >= 0),\n    line_total_usd_cents INTEGER NOT NULL CHECK(line_total_usd_cents >= 0),\n    FOREIGN KEY(return_id) REFERENCES returns(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(product_id) REFERENCES products(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)", "CREATE TABLE return_refund_allocations (\n    id TEXT PRIMARY KEY NOT NULL,\n    return_id TEXT NOT NULL,\n    cash_account_id TEXT NOT NULL,\n    amount_minor INTEGER NOT NULL CHECK(amount_minor > 0),\n    currency_code TEXT NOT NULL,\n    company_fee_minor INTEGER NOT NULL DEFAULT 0\n        CHECK(company_fee_minor >= 0),\n    exchange_rate_syp_per_usd_milli INTEGER,\n    created_at TEXT NOT NULL,\n    FOREIGN KEY(return_id) REFERENCES returns(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(cash_account_id) REFERENCES cash_accounts(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)", "CREATE TABLE operation_registry (\n    operation_id TEXT PRIMARY KEY NOT NULL,\n    operation_type TEXT NOT NULL,\n    payload_hash TEXT NOT NULL,\n    status TEXT NOT NULL CHECK(status IN\n        ('PENDING','COMPLETED','FAILED','CONFLICT')),\n    result_reference TEXT,\n    created_at TEXT NOT NULL,\n    completed_at TEXT\n)", "CREATE TABLE sync_queue (\n    id TEXT PRIMARY KEY NOT NULL,\n    operation_id TEXT NOT NULL UNIQUE,\n    entity_type TEXT NOT NULL,\n    entity_id TEXT NOT NULL,\n    operation_type TEXT NOT NULL CHECK(operation_type IN\n        ('CREATE','UPDATE','DELETE')),\n    payload_json TEXT NOT NULL,\n    status TEXT NOT NULL CHECK(status IN\n        ('PENDING','IN_PROGRESS','SYNCED','FAILED','CONFLICT')),\n    retry_count INTEGER NOT NULL DEFAULT 0 CHECK(retry_count >= 0),\n    last_error TEXT,\n    created_at TEXT NOT NULL,\n    updated_at TEXT NOT NULL\n)", "CREATE TABLE sync_conflicts (\n    id TEXT PRIMARY KEY NOT NULL,\n    operation_id TEXT NOT NULL,\n    entity_type TEXT NOT NULL,\n    entity_id TEXT NOT NULL,\n    local_payload_json TEXT NOT NULL,\n    remote_payload_json TEXT,\n    resolution TEXT CHECK(resolution IN\n        ('KEEP_LOCAL','KEEP_REMOTE','MERGED','DISMISSED')),\n    resolved_by TEXT,\n    resolved_at TEXT,\n    created_at TEXT NOT NULL,\n    FOREIGN KEY(resolved_by) REFERENCES users(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)", "CREATE TABLE audit_log (\n    id TEXT PRIMARY KEY NOT NULL,\n    user_id TEXT,\n    action TEXT NOT NULL,\n    entity_type TEXT NOT NULL,\n    entity_id TEXT,\n    before_json TEXT,\n    after_json TEXT,\n    device_id TEXT,\n    created_at TEXT NOT NULL,\n    FOREIGN KEY(user_id) REFERENCES users(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)", "CREATE INDEX idx_products_name ON products(name)", "CREATE INDEX idx_products_category ON products(category_id)", "CREATE INDEX idx_products_barcode ON products(barcode)", "CREATE INDEX idx_inventory_movements_product_date ON inventory_movements(product_id, created_at)", "CREATE INDEX idx_sales_date ON sales_invoices(invoice_date)", "CREATE INDEX idx_sales_posted_at ON sales_invoices(posted_at)", "CREATE INDEX idx_sales_posted_operating_date ON sales_invoices(posted_operating_date)", "CREATE INDEX idx_sales_customer_date ON sales_invoices(customer_id, invoice_date)", "CREATE INDEX idx_purchase_date ON purchase_invoices(invoice_date)", "CREATE INDEX idx_cash_transactions_account_date ON cash_transactions(account_id, created_at)", "CREATE INDEX idx_daily_cash_session_account ON daily_cash_sessions(primary_cash_account_id)", "CREATE INDEX idx_customer_credit_revaluation_invoice ON customer_credit_revaluations(invoice_id, created_at)", "CREATE INDEX idx_service_wallet_transactions_key_date ON service_wallet_transactions(service_key, created_at)", "CREATE INDEX idx_service_transfers_date ON service_transfers(created_at, service_type)", "CREATE INDEX idx_debts_party ON debts(party_type, party_id, created_at)", "CREATE INDEX idx_sync_queue_status_date ON sync_queue(status, created_at)", "CREATE INDEX idx_audit_log_entity_date ON audit_log(entity_type, entity_id, created_at)"};

    public /* synthetic */ AppDatabase(Context context, String str, byte[] bArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? DATABASE_NAME : str, (i & 4) != 0 ? null : bArr);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppDatabase(Context context, String databaseName, byte[] bArr) {
        super(context, databaseName, bArr, 33);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(databaseName, "databaseName");
    }

    @Override // net.zetetic.database.sqlcipher.SQLiteOpenHelper
    public void onConfigure(SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    public final synchronized void checkpointAndCloseForBackup$app_release() {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        Cursor cursorRawQuery = writableDatabase.rawQuery("PRAGMA wal_checkpoint(FULL)", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            if (cursor.moveToFirst() && cursor.getInt(0) != 0) {
                throw new IllegalStateException("\u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0645\u0634\u063a\u0648\u0644\u0629\u061b \u062a\u0639\u0630\u0631 \u062a\u062c\u0647\u064a\u0632 \u0646\u0633\u062e\u0629 \u0627\u062d\u062a\u064a\u0627\u0637\u064a\u0629 \u0622\u0645\u0646\u0629".toString());
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    @Override // net.zetetic.database.sqlcipher.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        db.beginTransaction();
        try {
            for (String str : SCHEMA) {
                db.execSQL(str);
            }
            PermissionPolicy.INSTANCE.ensureDefaults(db);
            ensureFifoSchema(db);
            ensureSalesReturnSchema(db);
            ensureInventoryAdjustmentSchema(db);
            ensureSupplierReturnSchema(db);
            ensureOperatingExpenseSchema(db);
            ensureSyncProtocolSchema(db);
            ensureSyncInboxActorColumn(db);
            ensureSyncDeviceIdentitySchema(db);
            ensureSyncDeviceRevocationSchema(db);
            ensureSyncActorMappingSchema(db);
            ensureSyncCashAccountMappingSchema(db);
            ensureSyncCategoryMappingSchema(db);
            ensureSyncUnitMappingSchema(db);
            ensureSyncAckDeliverySchema(db);
            ensureServiceTransferReceivableSchema(db);
            ensureServiceTransferReceivableSyncSchema(db);
            ensureServiceTransferPrecisionSchema(db);
            db.execSQL("INSERT INTO app_settings(setting_key, setting_value, updated_at)\n                   VALUES('base_currency', 'USD', strftime('%Y-%m-%dT%H:%M:%fZ','now'))");
            db.execSQL("INSERT INTO app_settings(setting_key, setting_value, updated_at)\n                   VALUES('store_currency', 'SYP', strftime('%Y-%m-%dT%H:%M:%fZ','now'))");
            db.execSQL("INSERT INTO app_settings(setting_key, setting_value, updated_at)\n                   VALUES('store_time_zone', 'Asia/Damascus', strftime('%Y-%m-%dT%H:%M:%fZ','now'))");
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override // net.zetetic.database.sqlcipher.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Intrinsics.checkNotNullParameter(db, "db");
        if (oldVersion < 2) {
            db.beginTransaction();
            try {
                db.execSQL("ALTER TABLE sales_invoices ADD COLUMN sale_type TEXT NOT NULL DEFAULT 'CASH' CHECK(sale_type IN ('CASH','CREDIT'))");
                db.execSQL("CREATE INDEX idx_sales_customer_date ON sales_invoices(customer_id, invoice_date)");
                db.setTransactionSuccessful();
                db.endTransaction();
                oldVersion = 2;
            } catch (Throwable th) {
                db.endTransaction();
                throw th;
            }
        }
        if (oldVersion < 3) {
            db.beginTransaction();
            try {
                db.execSQL("CREATE TABLE daily_cash_sessions (\n    operating_date TEXT PRIMARY KEY NOT NULL,\n    primary_cash_account_id TEXT NOT NULL,\n    created_by TEXT,\n    created_at TEXT NOT NULL,\n    FOREIGN KEY(primary_cash_account_id) REFERENCES cash_accounts(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(created_by) REFERENCES users(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
                db.execSQL("CREATE TABLE customer_credit_revaluations (\n    id TEXT PRIMARY KEY NOT NULL,\n    invoice_id TEXT NOT NULL,\n    customer_id TEXT NOT NULL,\n    outstanding_usd_cents INTEGER NOT NULL CHECK(outstanding_usd_cents > 0),\n    previous_syp_minor INTEGER NOT NULL CHECK(previous_syp_minor >= 0),\n    new_syp_minor INTEGER NOT NULL CHECK(new_syp_minor >= 0),\n    applied_rate_syp_per_usd_milli INTEGER NOT NULL CHECK(applied_rate_syp_per_usd_milli > 0),\n    calculation_basis TEXT NOT NULL,\n    operation_id TEXT NOT NULL UNIQUE,\n    created_at TEXT NOT NULL,\n    FOREIGN KEY(invoice_id) REFERENCES sales_invoices(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(customer_id) REFERENCES customers(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
                db.execSQL("CREATE INDEX idx_daily_cash_session_account ON daily_cash_sessions(primary_cash_account_id)");
                db.execSQL("CREATE INDEX idx_customer_credit_revaluation_invoice ON customer_credit_revaluations(invoice_id, created_at)");
                db.setTransactionSuccessful();
                db.endTransaction();
                oldVersion = 3;
            } catch (Throwable th2) {
                db.endTransaction();
                throw th2;
            }
        }
        if (oldVersion < 4) {
            db.beginTransaction();
            try {
                db.execSQL("ALTER TABLE cash_transactions ADD COLUMN operating_date TEXT");
                db.execSQL("UPDATE cash_transactions SET operating_date=substr(created_at,1,10) WHERE operating_date IS NULL");
                db.execSQL("CREATE TABLE daily_supplier_rollovers (\n    operating_date TEXT PRIMARY KEY NOT NULL,\n    account_id TEXT NOT NULL,\n    allocated_minor INTEGER NOT NULL CHECK(allocated_minor >= 0),\n    note TEXT,\n    updated_by TEXT NOT NULL,\n    created_at TEXT NOT NULL,\n    updated_at TEXT NOT NULL,\n    FOREIGN KEY(account_id) REFERENCES cash_accounts(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(updated_by) REFERENCES users(id) ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
                db.execSQL("CREATE TABLE wallet_daily_openings (\n    operating_date TEXT NOT NULL,\n    account_id TEXT NOT NULL,\n    opening_minor INTEGER NOT NULL CHECK(opening_minor >= 0),\n    note TEXT,\n    updated_by TEXT NOT NULL,\n    created_at TEXT NOT NULL,\n    updated_at TEXT NOT NULL,\n    PRIMARY KEY(operating_date, account_id),\n    FOREIGN KEY(account_id) REFERENCES cash_accounts(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(updated_by) REFERENCES users(id) ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
                db.execSQL("CREATE TABLE cash_account_transfers (\n    id TEXT PRIMARY KEY NOT NULL,\n    operation_id TEXT NOT NULL UNIQUE,\n    source_account_id TEXT NOT NULL,\n    destination_account_id TEXT NOT NULL,\n    amount_minor INTEGER NOT NULL CHECK(amount_minor > 0),\n    currency_code TEXT NOT NULL,\n    reference TEXT,\n    note TEXT,\n    payload_hash TEXT NOT NULL,\n    created_by TEXT NOT NULL,\n    created_at TEXT NOT NULL,\n    FOREIGN KEY(source_account_id) REFERENCES cash_accounts(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(destination_account_id) REFERENCES cash_accounts(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(created_by) REFERENCES users(id) ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
                db.execSQL("CREATE INDEX idx_cash_transactions_operating_date ON cash_transactions(operating_date, account_id)");
                db.execSQL("CREATE INDEX idx_cash_transfers_accounts ON cash_account_transfers(source_account_id, destination_account_id, created_at)");
                db.setTransactionSuccessful();
                db.endTransaction();
                oldVersion = 4;
            } catch (Throwable th3) {
                db.endTransaction();
                throw th3;
            }
        }
        if (oldVersion < 5) {
            db.beginTransaction();
            try {
                db.execSQL("CREATE TABLE service_wallets (\n    service_key TEXT PRIMARY KEY NOT NULL,\n    name TEXT NOT NULL,\n    created_at TEXT NOT NULL\n)");
                db.execSQL("CREATE TABLE service_wallet_transactions (\n    id TEXT PRIMARY KEY NOT NULL,\n    service_key TEXT NOT NULL,\n    direction TEXT NOT NULL CHECK(direction IN ('IN','OUT')),\n    amount_minor INTEGER NOT NULL CHECK(amount_minor > 0),\n    transaction_type TEXT NOT NULL,\n    source_id TEXT,\n    operation_id TEXT NOT NULL UNIQUE,\n    payload_hash TEXT,\n    fee_minor INTEGER NOT NULL DEFAULT 0 CHECK(fee_minor >= 0),\n    note TEXT,\n    created_by TEXT NOT NULL,\n    created_at TEXT NOT NULL,\n    FOREIGN KEY(service_key) REFERENCES service_wallets(service_key) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(created_by) REFERENCES users(id) ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
                db.execSQL("CREATE TABLE service_transfers (\n    id TEXT PRIMARY KEY NOT NULL,\n    operation_id TEXT NOT NULL UNIQUE,\n    service_type TEXT NOT NULL CHECK(service_type IN ('SYRIATEL_BALANCE','SYRIATEL_CASH','SHAM_CASH')),\n    service_key TEXT NOT NULL,\n    amount_delivered_minor INTEGER NOT NULL CHECK(amount_delivered_minor > 0),\n    profit_minor INTEGER NOT NULL CHECK(profit_minor >= 0),\n    provider_fee_minor INTEGER NOT NULL CHECK(provider_fee_minor >= 0),\n    customer_charge_minor INTEGER NOT NULL CHECK(customer_charge_minor > 0),\n    receipt_account_id TEXT NOT NULL,\n    receipt_channel TEXT NOT NULL CHECK(receipt_channel IN ('CASH','ELECTRONIC')),\n    reference TEXT,\n    note TEXT,\n    payload_hash TEXT NOT NULL,\n    created_by TEXT NOT NULL,\n    created_at TEXT NOT NULL,\n    status TEXT NOT NULL DEFAULT 'POSTED' CHECK(status IN ('POSTED','REVERSED')),\n    reversed_at TEXT,\n    reversed_by TEXT,\n    reversal_reason TEXT,\n    FOREIGN KEY(service_key) REFERENCES service_wallets(service_key) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(receipt_account_id) REFERENCES cash_accounts(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(created_by) REFERENCES users(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(reversed_by) REFERENCES users(id) ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
                db.execSQL("CREATE INDEX idx_service_wallet_transactions_key_date ON service_wallet_transactions(service_key, created_at)");
                db.execSQL("CREATE INDEX idx_service_transfers_date ON service_transfers(created_at, service_type)");
                db.setTransactionSuccessful();
                db.endTransaction();
                oldVersion = 5;
            } catch (Throwable th4) {
                db.endTransaction();
                throw th4;
            }
        }
        if (oldVersion < 6) {
            db.beginTransaction();
            try {
                db.execSQL("CREATE TABLE service_wallet_account_links (\n    service_key TEXT PRIMARY KEY NOT NULL,\n    account_id TEXT NOT NULL UNIQUE,\n    linked_at TEXT NOT NULL,\n    FOREIGN KEY(service_key) REFERENCES service_wallets(service_key)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(account_id) REFERENCES cash_accounts(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
                db.setTransactionSuccessful();
                db.endTransaction();
                oldVersion = 6;
            } catch (Throwable th5) {
                db.endTransaction();
                throw th5;
            }
        }
        if (oldVersion < 7) {
            db.beginTransaction();
            try {
                db.execSQL("CREATE TABLE system_cash_account_roles (\n    role_key TEXT PRIMARY KEY NOT NULL,\n    account_id TEXT NOT NULL,\n    linked_at TEXT NOT NULL,\n    FOREIGN KEY(account_id) REFERENCES cash_accounts(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
                db.setTransactionSuccessful();
                db.endTransaction();
                oldVersion = 7;
            } catch (Throwable th6) {
                db.endTransaction();
                throw th6;
            }
        }
        if (oldVersion < 8) {
            db.beginTransaction();
            try {
                db.execSQL("ALTER TABLE users ADD COLUMN must_change_password INTEGER NOT NULL DEFAULT 1\n    CHECK(must_change_password IN (0,1))");
                db.execSQL("UPDATE users SET must_change_password=1");
                db.setTransactionSuccessful();
                db.endTransaction();
                oldVersion = 8;
            } catch (Throwable th7) {
                db.endTransaction();
                throw th7;
            }
        }
        if (oldVersion < 9) {
            db.beginTransaction();
            try {
                ensureFifoSchema(db);
                backfillFifoCostLayers(db);
                db.setTransactionSuccessful();
                db.endTransaction();
                oldVersion = 9;
            } catch (Throwable th8) {
                db.endTransaction();
                throw th8;
            }
        }
        if (oldVersion < 10) {
            db.beginTransaction();
            try {
                Cursor cursorRawQuery = db.rawQuery("PRAGMA table_info(sales_invoices)", (String[]) null);
                try {
                    Cursor cursor = cursorRawQuery;
                    boolean z = false;
                    while (cursor.moveToNext()) {
                        if (Intrinsics.areEqual(cursor.getString(1), "posted_at")) {
                            z = true;
                        }
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursorRawQuery, null);
                    if (!z) {
                        db.execSQL("ALTER TABLE sales_invoices ADD COLUMN posted_at TEXT");
                    }
                    db.execSQL("UPDATE sales_invoices\nSET posted_at = COALESCE(\n    (SELECT MIN(op.created_at) FROM operation_registry op\n     WHERE op.operation_type='POST_SALE_INVOICE'\n       AND op.result_reference=sales_invoices.id),\n    (SELECT MIN(m.created_at) FROM inventory_movements m\n     WHERE m.source_id=sales_invoices.id AND m.movement_type='SALE'),\n    updated_at\n)\nWHERE status IN ('POSTED','PARTIALLY_PAID','PAID') AND posted_at IS NULL");
                    db.execSQL("CREATE INDEX IF NOT EXISTS idx_sales_posted_at ON sales_invoices(posted_at)");
                    db.setTransactionSuccessful();
                    db.endTransaction();
                    oldVersion = 10;
                } catch (Throwable th9) {
                    try {
                        throw th9;
                    } catch (Throwable th10) {
                        CloseableKt.closeFinally(cursorRawQuery, th9);
                        throw th10;
                    }
                }
            } catch (Throwable th11) {
                db.endTransaction();
                throw th11;
            }
        }
        if (oldVersion < 11) {
            db.beginTransaction();
            try {
                Cursor cursorRawQuery2 = db.rawQuery("PRAGMA table_info(sales_invoices)", (String[]) null);
                try {
                    Cursor cursor2 = cursorRawQuery2;
                    boolean z2 = false;
                    while (cursor2.moveToNext()) {
                        if (Intrinsics.areEqual(cursor2.getString(1), "posted_operating_date")) {
                            z2 = true;
                        }
                    }
                    Unit unit2 = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    if (!z2) {
                        db.execSQL("ALTER TABLE sales_invoices ADD COLUMN posted_operating_date TEXT");
                    }
                    db.execSQL("UPDATE sales_invoices\nSET posted_operating_date = COALESCE(\n    strftime('%Y-%m-%d', posted_at, 'localtime'),\n    substr(posted_at,1,10)\n)\nWHERE status IN ('POSTED','PARTIALLY_PAID','PAID')\n  AND posted_operating_date IS NULL AND posted_at IS NOT NULL");
                    db.execSQL("CREATE INDEX IF NOT EXISTS idx_sales_posted_operating_date ON sales_invoices(posted_operating_date)");
                    db.setTransactionSuccessful();
                    db.endTransaction();
                    oldVersion = 11;
                } catch (Throwable th12) {
                    try {
                        throw th12;
                    } catch (Throwable th13) {
                        CloseableKt.closeFinally(cursorRawQuery2, th12);
                        throw th13;
                    }
                }
            } catch (Throwable th14) {
                db.endTransaction();
                throw th14;
            }
        }
        if (oldVersion < 12) {
            db.beginTransaction();
            try {
                PermissionPolicy.INSTANCE.ensureDefaults(db);
                db.setTransactionSuccessful();
                db.endTransaction();
                oldVersion = 12;
            } catch (Throwable th15) {
                db.endTransaction();
                throw th15;
            }
        }
        if (oldVersion < 13) {
            db.beginTransaction();
            try {
                ensureSalesReturnSchema(db);
                db.setTransactionSuccessful();
                db.endTransaction();
                oldVersion = 13;
            } catch (Throwable th16) {
                db.endTransaction();
                throw th16;
            }
        }
        if (oldVersion < 14) {
            db.beginTransaction();
            try {
                PermissionPolicy.INSTANCE.ensureDefaults(db);
                db.setTransactionSuccessful();
                db.endTransaction();
                oldVersion = 14;
            } catch (Throwable th17) {
                db.endTransaction();
                throw th17;
            }
        }
        if (oldVersion < 15) {
            db.beginTransaction();
            try {
                ensureInventoryAdjustmentSchema(db);
                db.setTransactionSuccessful();
                db.endTransaction();
                oldVersion = 15;
            } catch (Throwable th18) {
                db.endTransaction();
                throw th18;
            }
        }
        if (oldVersion < 16) {
            db.beginTransaction();
            try {
                ensureSupplierReturnSchema(db);
                db.setTransactionSuccessful();
                db.endTransaction();
                oldVersion = 16;
            } catch (Throwable th19) {
                db.endTransaction();
                throw th19;
            }
        }
        if (oldVersion < 17) {
            db.beginTransaction();
            try {
                ensureOperatingExpenseSchema(db);
                db.setTransactionSuccessful();
                db.endTransaction();
                oldVersion = 17;
            } catch (Throwable th20) {
                db.endTransaction();
                throw th20;
            }
        }
        if (oldVersion < 18) {
            db.beginTransaction();
            try {
                ensureSyncProtocolSchema(db);
                PermissionPolicy.INSTANCE.ensureDefaults(db);
                db.setTransactionSuccessful();
                db.endTransaction();
                oldVersion = 18;
            } catch (Throwable th21) {
                db.endTransaction();
                throw th21;
            }
        }
        if (oldVersion < 19) {
            db.beginTransaction();
            try {
                ensureSyncActorMappingSchema(db);
                db.setTransactionSuccessful();
                db.endTransaction();
                oldVersion = 19;
            } catch (Throwable th22) {
                db.endTransaction();
                throw th22;
            }
        }
        if (oldVersion < 20) {
            db.beginTransaction();
            try {
                ensureSyncInboxOriginTimestampColumn(db);
                db.setTransactionSuccessful();
                db.endTransaction();
                oldVersion = 20;
            } catch (Throwable th23) {
                db.endTransaction();
                throw th23;
            }
        }
        if (oldVersion < 21) {
            db.beginTransaction();
            try {
                ensureSyncCashAccountMappingSchema(db);
                db.setTransactionSuccessful();
                db.endTransaction();
                oldVersion = 21;
            } catch (Throwable th24) {
                db.endTransaction();
                throw th24;
            }
        }
        if (oldVersion < 22) {
            db.beginTransaction();
            try {
                db.execSQL("INSERT OR IGNORE INTO app_settings(setting_key, setting_value, updated_at)\n                       VALUES('store_time_zone', 'Asia/Damascus', strftime('%Y-%m-%dT%H:%M:%fZ','now'))");
                ArrayList<Pair> arrayList = new ArrayList();
                Cursor cursorRawQuery3 = db.rawQuery("SELECT id,created_at FROM cash_transactions WHERE operating_date IS NULL OR TRIM(operating_date)=''", (String[]) null);
                try {
                    Cursor cursor3 = cursorRawQuery3;
                    while (cursor3.moveToNext()) {
                        arrayList.add(TuplesKt.to(cursor3.getString(0), cursor3.getString(1)));
                    }
                    Unit unit3 = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursorRawQuery3, null);
                    for (Pair pair : arrayList) {
                        String str = (String) pair.component1();
                        String str2 = (String) pair.component2();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("operating_date", StoreTime.INSTANCE.operatingDate(db, str2));
                        Unit unit4 = Unit.INSTANCE;
                        db.update("cash_transactions", contentValues, "id=?", new String[]{str});
                    }
                    db.setTransactionSuccessful();
                    db.endTransaction();
                    oldVersion = 22;
                } catch (Throwable th25) {
                    try {
                        throw th25;
                    } catch (Throwable th26) {
                        CloseableKt.closeFinally(cursorRawQuery3, th25);
                        throw th26;
                    }
                }
            } catch (Throwable th27) {
                db.endTransaction();
                throw th27;
            }
        }
        if (oldVersion < 23) {
            db.beginTransaction();
            try {
                onUpgrade$ensureOperatingDateColumn(db, "service_transfers");
                onUpgrade$ensureOperatingDateColumn(db, "service_wallet_transactions");
                ArrayList<Pair> arrayList2 = new ArrayList();
                Cursor cursorRawQuery4 = db.rawQuery("SELECT id,created_at FROM service_transfers WHERE operating_date IS NULL OR TRIM(operating_date)=''", (String[]) null);
                try {
                    Cursor cursor4 = cursorRawQuery4;
                    while (cursor4.moveToNext()) {
                        arrayList2.add(TuplesKt.to(cursor4.getString(0), cursor4.getString(1)));
                    }
                    Unit unit5 = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursorRawQuery4, null);
                    for (Pair pair2 : arrayList2) {
                        String str3 = (String) pair2.component1();
                        String str4 = (String) pair2.component2();
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("operating_date", StoreTime.INSTANCE.operatingDate(db, str4));
                        Unit unit6 = Unit.INSTANCE;
                        db.update("service_transfers", contentValues2, "id=?", new String[]{str3});
                    }
                    ArrayList<Pair> arrayList3 = new ArrayList();
                    Cursor cursorRawQuery5 = db.rawQuery("SELECT id,created_at FROM service_wallet_transactions WHERE operating_date IS NULL OR TRIM(operating_date)=''", (String[]) null);
                    try {
                        Cursor cursor5 = cursorRawQuery5;
                        while (cursor5.moveToNext()) {
                            arrayList3.add(TuplesKt.to(cursor5.getString(0), cursor5.getString(1)));
                        }
                        Unit unit7 = Unit.INSTANCE;
                        CloseableKt.closeFinally(cursorRawQuery5, null);
                        for (Pair pair3 : arrayList3) {
                            String str5 = (String) pair3.component1();
                            String str6 = (String) pair3.component2();
                            ContentValues contentValues3 = new ContentValues();
                            contentValues3.put("operating_date", StoreTime.INSTANCE.operatingDate(db, str6));
                            Unit unit8 = Unit.INSTANCE;
                            db.update("service_wallet_transactions", contentValues3, "id=?", new String[]{str5});
                        }
                        db.execSQL("CREATE INDEX IF NOT EXISTS idx_service_transfers_operating_date ON service_transfers(operating_date,service_type,status)");
                        db.execSQL("CREATE INDEX IF NOT EXISTS idx_service_wallet_transactions_operating_date ON service_wallet_transactions(operating_date,service_key,transaction_type)");
                        db.setTransactionSuccessful();
                        db.endTransaction();
                        oldVersion = 23;
                    } catch (Throwable th28) {
                        try {
                            throw th28;
                        } catch (Throwable th29) {
                            CloseableKt.closeFinally(cursorRawQuery5, th28);
                            throw th29;
                        }
                    }
                } catch (Throwable th30) {
                    try {
                        throw th30;
                    } catch (Throwable th31) {
                        CloseableKt.closeFinally(cursorRawQuery4, th30);
                        throw th31;
                    }
                }
            } catch (Throwable th32) {
                db.endTransaction();
                throw th32;
            }
        }
        if (oldVersion < 24) {
            db.beginTransaction();
            try {
                ensureSyncDeviceIdentitySchema(db);
                db.setTransactionSuccessful();
                db.endTransaction();
                oldVersion = 24;
            } catch (Throwable th33) {
                db.endTransaction();
                throw th33;
            }
        }
        if (oldVersion < 25) {
            db.beginTransaction();
            try {
                ensureSyncDeviceIdentitySchema(db);
                db.setTransactionSuccessful();
                db.endTransaction();
                oldVersion = 25;
            } catch (Throwable th34) {
                db.endTransaction();
                throw th34;
            }
        }
        if (oldVersion < 26) {
            db.beginTransaction();
            try {
                ensureSyncDeviceRevocationSchema(db);
                db.setTransactionSuccessful();
                db.endTransaction();
                oldVersion = 26;
            } catch (Throwable th35) {
                db.endTransaction();
                throw th35;
            }
        }
        if (oldVersion < 27) {
            db.beginTransaction();
            try {
                ensureSyncCategoryMappingSchema(db);
                db.setTransactionSuccessful();
                db.endTransaction();
                oldVersion = 27;
            } catch (Throwable th36) {
                db.endTransaction();
                throw th36;
            }
        }
        if (oldVersion < 28) {
            db.beginTransaction();
            try {
                ensureSyncUnitMappingSchema(db);
                db.setTransactionSuccessful();
                db.endTransaction();
                oldVersion = 28;
            } catch (Throwable th37) {
                db.endTransaction();
                throw th37;
            }
        }
        if (oldVersion < 29) {
            db.beginTransaction();
            try {
                ensureSyncAckDeliverySchema(db);
                db.setTransactionSuccessful();
                db.endTransaction();
                oldVersion = 29;
            } catch (Throwable th38) {
                db.endTransaction();
                throw th38;
            }
        }
        if (oldVersion < 30) {
            db.beginTransaction();
            try {
                ensureSyncProtocolSchema(db);
                ensureSyncActorMappingSchema(db);
                ensureSyncInboxActorColumn(db);
                db.setTransactionSuccessful();
                db.endTransaction();
                oldVersion = 30;
            } catch (Throwable th39) {
                db.endTransaction();
                throw th39;
            }
        }
        if (oldVersion < 31) {
            db.beginTransaction();
            try {
                ensureServiceTransferReceivableSchema(db);
                db.setTransactionSuccessful();
                db.endTransaction();
                oldVersion = 31;
            } catch (Throwable th40) {
                db.endTransaction();
                throw th40;
            }
        }
        if (oldVersion < 32) {
            db.beginTransaction();
            try {
                ensureServiceTransferReceivableSyncSchema(db);
                db.setTransactionSuccessful();
                db.endTransaction();
                oldVersion = 32;
            } catch (Throwable th41) {
                db.endTransaction();
                throw th41;
            }
        }
        if (oldVersion < 33) {
            db.beginTransaction();
            try {
                ensureServiceTransferPrecisionSchema(db);
                db.setTransactionSuccessful();
                db.endTransaction();
                oldVersion = 33;
            } catch (Throwable th42) {
                db.endTransaction();
                throw th42;
            }
        }
        if (oldVersion == newVersion) {
            return;
        }
        throw new IllegalStateException("Missing database migration from version " + oldVersion + " to " + newVersion);
    }

    private static final void onUpgrade$ensureOperatingDateColumn(SQLiteDatabase sQLiteDatabase, String str) throws IOException {
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("PRAGMA table_info(" + str + ")", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            boolean z = false;
            while (cursor.moveToNext()) {
                if (Intrinsics.areEqual(cursor.getString(1), "operating_date")) {
                    z = true;
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (z) {
                return;
            }
            sQLiteDatabase.execSQL("ALTER TABLE " + str + " ADD COLUMN operating_date TEXT");
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private static final boolean ensureServiceTransferPrecisionSchema$hasColumn(SQLiteDatabase sQLiteDatabase, String str, String str2) throws IOException {
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("PRAGMA table_info(" + str + ")", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            boolean z = false;
            while (cursor.moveToNext()) {
                if (Intrinsics.areEqual(cursor.getString(1), str2)) {
                    z = true;
                }
            }
            CloseableKt.closeFinally(cursorRawQuery, null);
            return z;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final void ensureServiceTransferPrecisionSchema(SQLiteDatabase db) {
        if (!ensureServiceTransferPrecisionSchema$hasColumn(db, "service_transfers", "amount_debited_minor")) {
            db.execSQL("ALTER TABLE service_transfers ADD COLUMN amount_debited_minor INTEGER NOT NULL DEFAULT 0");
        }
        if (!ensureServiceTransferPrecisionSchema$hasColumn(db, "service_transfers", "amount_delivered_milli_syp")) {
            db.execSQL("ALTER TABLE service_transfers ADD COLUMN amount_delivered_milli_syp INTEGER NOT NULL DEFAULT 0");
        }
        if (!ensureServiceTransferPrecisionSchema$hasColumn(db, "service_transfers", "profit_milli_syp")) {
            db.execSQL("ALTER TABLE service_transfers ADD COLUMN profit_milli_syp INTEGER NOT NULL DEFAULT 0");
        }
        if (ensureServiceTransferPrecisionSchema$hasColumn(db, "service_transfers", "amount_delivered_minor")) {
            db.execSQL("UPDATE service_transfers SET amount_debited_minor=amount_delivered_minor WHERE amount_debited_minor=0");
            db.execSQL("UPDATE service_transfers SET amount_delivered_milli_syp=amount_delivered_minor*1000 WHERE amount_delivered_milli_syp=0");
        }
        if (ensureServiceTransferPrecisionSchema$hasColumn(db, "service_transfers", "profit_minor")) {
            db.execSQL("UPDATE service_transfers SET profit_milli_syp=profit_minor*1000 WHERE profit_milli_syp=0");
        }
        if (!ensureServiceTransferPrecisionSchema$hasColumn(db, "service_transfer_receivable_mirrors", "delivered_milli_syp")) {
            db.execSQL("ALTER TABLE service_transfer_receivable_mirrors ADD COLUMN delivered_milli_syp INTEGER NOT NULL DEFAULT 0");
        }
        if (ensureServiceTransferPrecisionSchema$hasColumn(db, "service_transfer_receivable_mirrors", "delivered_minor")) {
            db.execSQL("UPDATE service_transfer_receivable_mirrors SET delivered_milli_syp=delivered_minor*1000 WHERE delivered_milli_syp=0");
        }
    }

    private final void ensureServiceTransferReceivableSyncSchema(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS service_transfer_receivable_mirrors (\n    transfer_id TEXT PRIMARY KEY NOT NULL,\n    customer_id TEXT NOT NULL,\n    customer_name TEXT NOT NULL,\n    service_type TEXT NOT NULL CHECK(service_type IN ('SYRIATEL_BALANCE','SYRIATEL_CASH','SHAM_CASH')),\n    delivered_minor INTEGER NOT NULL CHECK(delivered_minor > 0),\n    charge_minor INTEGER NOT NULL CHECK(charge_minor > 0),\n    paid_minor INTEGER NOT NULL DEFAULT 0 CHECK(paid_minor >= 0),\n    outstanding_minor INTEGER NOT NULL CHECK(outstanding_minor >= 0),\n    status TEXT NOT NULL CHECK(status IN ('OPEN','PARTIALLY_PAID','PAID','REVERSED')),\n    created_at TEXT NOT NULL,\n    operating_date TEXT NOT NULL,\n    reference TEXT,\n    note TEXT,\n    updated_at TEXT NOT NULL\n)");
        db.execSQL("CREATE TABLE IF NOT EXISTS service_transfer_receivable_payment_mirrors (\n    payment_id TEXT PRIMARY KEY NOT NULL,\n    transfer_id TEXT NOT NULL,\n    amount_minor INTEGER NOT NULL CHECK(amount_minor > 0),\n    operation_id TEXT NOT NULL UNIQUE,\n    cash_account_id TEXT NOT NULL,\n    operating_date TEXT NOT NULL,\n    created_at TEXT NOT NULL,\n    note TEXT,\n    FOREIGN KEY(transfer_id) REFERENCES service_transfer_receivable_mirrors(transfer_id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
        db.execSQL("CREATE INDEX IF NOT EXISTS idx_service_transfer_receivable_mirrors_customer ON service_transfer_receivable_mirrors(customer_id,status)");
        db.execSQL("CREATE INDEX IF NOT EXISTS idx_service_transfer_receivable_payment_mirrors_transfer ON service_transfer_receivable_payment_mirrors(transfer_id,operating_date,created_at)");
    }

    private static final boolean ensureServiceTransferReceivableSchema$hasColumn$16(SQLiteDatabase sQLiteDatabase, String str, String str2) throws IOException {
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("PRAGMA table_info(" + str + ")", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            boolean z = false;
            while (cursor.moveToNext()) {
                if (Intrinsics.areEqual(cursor.getString(1), str2)) {
                    z = true;
                }
            }
            CloseableKt.closeFinally(cursorRawQuery, null);
            return z;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final void ensureServiceTransferReceivableSchema(SQLiteDatabase db) {
        if (!ensureServiceTransferReceivableSchema$hasColumn$16(db, "service_transfers", "payment_mode")) {
            db.execSQL("ALTER TABLE service_transfers ADD COLUMN payment_mode TEXT NOT NULL DEFAULT 'CASH'\n                CHECK(payment_mode IN ('CASH','CREDIT'))");
        }
        if (!ensureServiceTransferReceivableSchema$hasColumn$16(db, "service_transfers", "customer_id")) {
            db.execSQL("ALTER TABLE service_transfers ADD COLUMN customer_id TEXT\n                REFERENCES customers(id) ON UPDATE RESTRICT ON DELETE RESTRICT");
        }
        db.execSQL("CREATE TABLE IF NOT EXISTS service_transfer_receivables (\n    transfer_id TEXT PRIMARY KEY NOT NULL,\n    customer_id TEXT NOT NULL,\n    original_amount_minor INTEGER NOT NULL CHECK(original_amount_minor > 0),\n    status TEXT NOT NULL DEFAULT 'OPEN' CHECK(status IN ('OPEN','PARTIALLY_PAID','PAID','REVERSED')),\n    note TEXT,\n    created_at TEXT NOT NULL,\n    FOREIGN KEY(transfer_id) REFERENCES service_transfers(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(customer_id) REFERENCES customers(id) ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
        db.execSQL("CREATE TABLE IF NOT EXISTS service_transfer_receivable_payments (\n    id TEXT PRIMARY KEY NOT NULL,\n    transfer_id TEXT NOT NULL,\n    amount_minor INTEGER NOT NULL CHECK(amount_minor > 0),\n    cash_transaction_id TEXT NOT NULL UNIQUE,\n    operation_id TEXT NOT NULL UNIQUE,\n    payload_hash TEXT NOT NULL,\n    created_by TEXT NOT NULL,\n    created_at TEXT NOT NULL,\n    operating_date TEXT NOT NULL,\n    note TEXT,\n    FOREIGN KEY(transfer_id) REFERENCES service_transfer_receivables(transfer_id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(cash_transaction_id) REFERENCES cash_transactions(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(created_by) REFERENCES users(id) ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
        db.execSQL("CREATE INDEX IF NOT EXISTS idx_service_transfer_receivables_customer ON service_transfer_receivables(customer_id,status)");
        db.execSQL("CREATE INDEX IF NOT EXISTS idx_service_transfer_debt_payments_transfer ON service_transfer_receivable_payments(transfer_id,created_at)");
    }

    private final void ensureSyncCategoryMappingSchema(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS sync_category_mappings (\n    primary_device_id TEXT NOT NULL,\n    primary_category_id INTEGER NOT NULL CHECK(primary_category_id > 0),\n    secondary_category_id INTEGER NOT NULL,\n    category_name TEXT NOT NULL,\n    mapped_at TEXT NOT NULL,\n    PRIMARY KEY(primary_device_id, primary_category_id),\n    UNIQUE(primary_device_id, category_name),\n    FOREIGN KEY(primary_device_id) REFERENCES sync_devices(device_id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(secondary_category_id) REFERENCES categories(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
        db.execSQL("CREATE INDEX IF NOT EXISTS idx_sync_category_mappings_local ON sync_category_mappings(secondary_category_id)");
    }

    private final void ensureSyncAckDeliverySchema(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS sync_ack_delivery (\n    operation_id TEXT NOT NULL,\n    device_id TEXT NOT NULL,\n    last_forwarded_at TEXT NOT NULL,\n    attempt_count INTEGER NOT NULL DEFAULT 1 CHECK(attempt_count > 0),\n    updated_at TEXT NOT NULL,\n    PRIMARY KEY(operation_id, device_id),\n    FOREIGN KEY(operation_id) REFERENCES sync_inbox(operation_id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(device_id) REFERENCES sync_devices(device_id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
        db.execSQL("CREATE INDEX IF NOT EXISTS idx_sync_ack_delivery_retry ON sync_ack_delivery(device_id, last_forwarded_at)");
    }

    private final void ensureSyncUnitMappingSchema(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS sync_unit_mappings (\n    primary_device_id TEXT NOT NULL,\n    primary_unit_id INTEGER NOT NULL CHECK(primary_unit_id > 0),\n    secondary_unit_id INTEGER NOT NULL,\n    unit_name TEXT NOT NULL,\n    unit_symbol TEXT NOT NULL,\n    decimal_places INTEGER NOT NULL CHECK(decimal_places BETWEEN 0 AND 3),\n    mapped_at TEXT NOT NULL,\n    PRIMARY KEY(primary_device_id, primary_unit_id),\n    UNIQUE(primary_device_id, unit_name),\n    FOREIGN KEY(primary_device_id) REFERENCES sync_devices(device_id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(secondary_unit_id) REFERENCES units(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
        db.execSQL("CREATE INDEX IF NOT EXISTS idx_sync_unit_mappings_local ON sync_unit_mappings(secondary_unit_id)");
    }

    private final void ensureSyncCashAccountMappingSchema(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS sync_cash_account_mappings (\n    device_id TEXT NOT NULL,\n    remote_cash_account_id TEXT NOT NULL,\n    primary_cash_account_id TEXT NOT NULL,\n    is_active INTEGER NOT NULL DEFAULT 1 CHECK(is_active IN (0,1)),\n    mapped_by TEXT NOT NULL,\n    mapped_at TEXT NOT NULL,\n    PRIMARY KEY(device_id, remote_cash_account_id),\n    FOREIGN KEY(device_id) REFERENCES sync_devices(device_id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(primary_cash_account_id) REFERENCES cash_accounts(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(mapped_by) REFERENCES users(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
        db.execSQL("CREATE INDEX IF NOT EXISTS idx_sync_cash_account_mappings_primary ON sync_cash_account_mappings(primary_cash_account_id,is_active)");
    }

    private final void ensureSyncInboxOriginTimestampColumn(SQLiteDatabase db) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("PRAGMA table_info(sync_inbox)", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            boolean z = false;
            while (cursor.moveToNext()) {
                if (Intrinsics.areEqual(cursor.getString(1), "origin_created_at")) {
                    z = true;
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (z) {
                return;
            }
            db.execSQL("ALTER TABLE sync_inbox ADD COLUMN origin_created_at TEXT NOT NULL DEFAULT ''");
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final void ensureSyncInboxActorColumn(SQLiteDatabase db) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("PRAGMA table_info(sync_inbox)", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            boolean z = false;
            while (cursor.moveToNext()) {
                if (Intrinsics.areEqual(cursor.getString(1), SyncDeviceManagementActivity.EXTRA_ACTOR_USER_ID)) {
                    z = true;
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (z) {
                return;
            }
            db.execSQL("ALTER TABLE sync_inbox ADD COLUMN actor_user_id TEXT");
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final void ensureSyncDeviceIdentitySchema(SQLiteDatabase db) throws IOException {
        db.execSQL("CREATE TABLE IF NOT EXISTS sync_device_public_keys (\n    device_id TEXT PRIMARY KEY NOT NULL,\n    public_key_base64 TEXT NOT NULL,\n    public_key_fingerprint TEXT NOT NULL,\n    algorithm TEXT NOT NULL CHECK(algorithm='EC_P256_SHA256'),\n    status TEXT NOT NULL CHECK(status IN ('ACTIVE','REVOKED')),\n    enrolled_at TEXT NOT NULL,\n    revoked_at TEXT,\n    FOREIGN KEY(device_id) REFERENCES sync_devices(device_id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
        Cursor cursorRawQuery = db.rawQuery("PRAGMA table_info(sync_device_public_keys)", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            boolean z = false;
            while (cursor.moveToNext()) {
                if (Intrinsics.areEqual(cursor.getString(1), "certificate_json")) {
                    z = true;
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (!z) {
                db.execSQL("ALTER TABLE sync_device_public_keys ADD COLUMN certificate_json TEXT");
            }
            db.execSQL("CREATE INDEX IF NOT EXISTS idx_sync_device_public_keys_status ON sync_device_public_keys(status,device_id)");
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final void ensureSyncDeviceRevocationSchema(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS sync_device_revocations (\n    device_id TEXT NOT NULL,\n    certificate_serial TEXT NOT NULL,\n    revocation_json TEXT NOT NULL,\n    status TEXT NOT NULL CHECK(status IN ('PENDING','SYNCED')),\n    created_at TEXT NOT NULL,\n    sent_at TEXT,\n    PRIMARY KEY(device_id,certificate_serial),\n    FOREIGN KEY(device_id) REFERENCES sync_devices(device_id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
        db.execSQL("CREATE INDEX IF NOT EXISTS idx_sync_device_revocations_status ON sync_device_revocations(status,created_at)");
    }

    private final void ensureSyncActorMappingSchema(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS sync_actor_mappings (\n    device_id TEXT NOT NULL,\n    remote_actor_user_id TEXT NOT NULL,\n    primary_user_id TEXT NOT NULL,\n    is_active INTEGER NOT NULL DEFAULT 1 CHECK(is_active IN (0,1)),\n    mapped_by TEXT NOT NULL,\n    mapped_at TEXT NOT NULL,\n    PRIMARY KEY(device_id, remote_actor_user_id),\n    FOREIGN KEY(device_id) REFERENCES sync_devices(device_id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(primary_user_id) REFERENCES users(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(mapped_by) REFERENCES users(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
        db.execSQL("CREATE INDEX IF NOT EXISTS idx_sync_actor_mappings_primary_user ON sync_actor_mappings(primary_user_id,is_active)");
    }

    private static final void ensureSalesReturnSchema$ensureColumn(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) throws IOException {
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("PRAGMA table_info(" + str + ")", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            boolean z = false;
            while (cursor.moveToNext()) {
                if (Intrinsics.areEqual(cursor.getString(1), str2)) {
                    z = true;
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (z) {
                return;
            }
            sQLiteDatabase.execSQL("ALTER TABLE " + str + " ADD COLUMN " + str2 + " " + str3);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final void ensureSalesReturnSchema(SQLiteDatabase db) throws IOException {
        ensureSalesReturnSchema$ensureColumn(db, "returns", "operation_id", "TEXT");
        ensureSalesReturnSchema$ensureColumn(db, "returns", "posted_at", "TEXT");
        ensureSalesReturnSchema$ensureColumn(db, "returns", "cash_refund_usd_cents", "INTEGER NOT NULL DEFAULT 0 CHECK(cash_refund_usd_cents >= 0)");
        ensureSalesReturnSchema$ensureColumn(db, "returns", "credit_reduction_usd_cents", "INTEGER NOT NULL DEFAULT 0 CHECK(credit_reduction_usd_cents >= 0)");
        ensureSalesReturnSchema$ensureColumn(db, "returns", "returned_cogs_usd_cents", "INTEGER CHECK(returned_cogs_usd_cents IS NULL OR returned_cogs_usd_cents >= 0)");
        ensureSalesReturnSchema$ensureColumn(db, "returns", "exchange_rate_syp_per_usd_milli", "INTEGER");
        ensureSalesReturnSchema$ensureColumn(db, "return_items", "original_sales_item_id", "TEXT");
        ensureSalesReturnSchema$ensureColumn(db, "return_items", "returned_cogs_usd_cents", "INTEGER CHECK(returned_cogs_usd_cents IS NULL OR returned_cogs_usd_cents >= 0)");
        ensureSalesReturnSchema$ensureColumn(db, "return_refund_allocations", "operation_id", "TEXT");
        ensureSalesReturnSchema$ensureColumn(db, "return_refund_allocations", "cash_transaction_id", "TEXT");
        ensureSalesReturnSchema$ensureColumn(db, "return_refund_allocations", "refund_equivalent_syp_minor", "INTEGER NOT NULL DEFAULT 0 CHECK(refund_equivalent_syp_minor >= 0)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS idx_returns_operation_id ON returns(operation_id) WHERE operation_id IS NOT NULL");
        db.execSQL("CREATE INDEX IF NOT EXISTS idx_returns_original_invoice ON returns(original_invoice_type,original_invoice_id,status)");
        db.execSQL("CREATE INDEX IF NOT EXISTS idx_return_items_original_sales_item ON return_items(original_sales_item_id)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS idx_return_refund_operation_id ON return_refund_allocations(operation_id) WHERE operation_id IS NOT NULL");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS idx_return_refund_cash_transaction ON return_refund_allocations(cash_transaction_id) WHERE cash_transaction_id IS NOT NULL");
        db.execSQL("CREATE TABLE IF NOT EXISTS return_cost_allocations (\n    id TEXT PRIMARY KEY NOT NULL,\n    return_item_id TEXT NOT NULL,\n    source_sale_allocation_id TEXT NOT NULL,\n    inventory_movement_id TEXT,\n    quantity REAL NOT NULL CHECK(quantity > 0),\n    unit_cost_usd_cents INTEGER CHECK(unit_cost_usd_cents IS NULL OR unit_cost_usd_cents > 0),\n    cost_usd_cents INTEGER CHECK(cost_usd_cents IS NULL OR cost_usd_cents >= 0),\n    created_at TEXT NOT NULL,\n    FOREIGN KEY(return_item_id) REFERENCES return_items(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(source_sale_allocation_id) REFERENCES sales_item_cost_allocations(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(inventory_movement_id) REFERENCES inventory_movements(id) ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
        db.execSQL("CREATE INDEX IF NOT EXISTS idx_return_cost_source ON return_cost_allocations(source_sale_allocation_id)");
    }

    private final void ensureInventoryAdjustmentSchema(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS inventory_adjustments (\n    id TEXT PRIMARY KEY NOT NULL,\n    operation_id TEXT NOT NULL UNIQUE,\n    product_id TEXT NOT NULL,\n    warehouse_id TEXT NOT NULL,\n    inventory_movement_id TEXT NOT NULL UNIQUE,\n    adjustment_type TEXT NOT NULL CHECK(adjustment_type IN ('INCREASE','DECREASE')),\n    quantity REAL NOT NULL CHECK(quantity > 0),\n    quantity_before REAL NOT NULL CHECK(quantity_before >= 0),\n    quantity_after REAL NOT NULL CHECK(quantity_after >= 0),\n    unit_cost_usd_cents INTEGER CHECK(unit_cost_usd_cents IS NULL OR unit_cost_usd_cents > 0),\n    cost_usd_cents INTEGER CHECK(cost_usd_cents IS NULL OR cost_usd_cents >= 0),\n    reason TEXT NOT NULL,\n    created_by TEXT NOT NULL,\n    created_at TEXT NOT NULL,\n    FOREIGN KEY(product_id) REFERENCES products(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(warehouse_id) REFERENCES warehouses(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(inventory_movement_id) REFERENCES inventory_movements(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(created_by) REFERENCES users(id) ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
        db.execSQL("CREATE INDEX IF NOT EXISTS idx_inventory_adjustments_product_date ON inventory_adjustments(product_id,created_at)");
    }

    private final void ensureSyncProtocolSchema(SQLiteDatabase db) throws IOException {
        db.execSQL("CREATE TABLE IF NOT EXISTS sync_devices (\n    device_id TEXT PRIMARY KEY NOT NULL,\n    display_name TEXT NOT NULL,\n    device_role TEXT NOT NULL CHECK(device_role IN ('PRIMARY','SECONDARY')),\n    credential_hash TEXT,\n    status TEXT NOT NULL CHECK(status IN ('ACTIVE','REVOKED')),\n    enrolled_by TEXT,\n    created_at TEXT NOT NULL,\n    last_seen_at TEXT,\n    revoked_at TEXT,\n    FOREIGN KEY(enrolled_by) REFERENCES users(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
        db.execSQL("CREATE TABLE IF NOT EXISTS sync_pairing_invitations (\n    invitation_id TEXT PRIMARY KEY NOT NULL,\n    invitation_hash TEXT NOT NULL UNIQUE,\n    created_by TEXT NOT NULL,\n    expires_at TEXT NOT NULL,\n    consumed_at TEXT,\n    created_at TEXT NOT NULL,\n    FOREIGN KEY(created_by) REFERENCES users(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
        db.execSQL("CREATE TABLE IF NOT EXISTS sync_protocol_state (\n    state_key TEXT PRIMARY KEY NOT NULL,\n    state_value TEXT NOT NULL,\n    updated_at TEXT NOT NULL\n)");
        db.execSQL("CREATE TABLE IF NOT EXISTS sync_operation_envelopes (\n    operation_id TEXT PRIMARY KEY NOT NULL,\n    sequence_number INTEGER NOT NULL UNIQUE CHECK(sequence_number > 0),\n    origin_device_id TEXT NOT NULL,\n    actor_user_id TEXT NOT NULL,\n    operation_type TEXT NOT NULL,\n    entity_type TEXT NOT NULL,\n    entity_id TEXT NOT NULL,\n    payload_json TEXT NOT NULL,\n    payload_sha256 TEXT NOT NULL,\n    created_at TEXT NOT NULL,\n    FOREIGN KEY(origin_device_id) REFERENCES sync_devices(device_id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(actor_user_id) REFERENCES users(id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
        db.execSQL("CREATE TABLE IF NOT EXISTS sync_inbox (\n    operation_id TEXT PRIMARY KEY NOT NULL,\n    origin_device_id TEXT NOT NULL,\n    sequence_number INTEGER NOT NULL CHECK(sequence_number > 0),\n    operation_type TEXT NOT NULL,\n    entity_type TEXT NOT NULL,\n    entity_id TEXT NOT NULL,\n    payload_json TEXT NOT NULL,\n    payload_sha256 TEXT NOT NULL,\n    status TEXT NOT NULL CHECK(status IN ('RECEIVED','APPLIED','REJECTED','CONFLICT')),\n    received_at TEXT NOT NULL,\n    applied_at TEXT,\n    rejection_reason TEXT,\n    FOREIGN KEY(origin_device_id) REFERENCES sync_devices(device_id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
        db.execSQL("CREATE TABLE IF NOT EXISTS sync_acknowledgements (\n    operation_id TEXT NOT NULL,\n    device_id TEXT NOT NULL,\n    sequence_number INTEGER NOT NULL CHECK(sequence_number > 0),\n    status TEXT NOT NULL CHECK(status IN ('RECEIVED','APPLIED','REJECTED')),\n    acknowledged_at TEXT NOT NULL,\n    PRIMARY KEY(operation_id, device_id),\n    FOREIGN KEY(operation_id) REFERENCES sync_operation_envelopes(operation_id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(device_id) REFERENCES sync_devices(device_id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
        db.execSQL("CREATE TABLE IF NOT EXISTS sync_cursors (\n    device_id TEXT PRIMARY KEY NOT NULL,\n    last_sequence INTEGER NOT NULL DEFAULT 0 CHECK(last_sequence >= 0),\n    updated_at TEXT NOT NULL,\n    FOREIGN KEY(device_id) REFERENCES sync_devices(device_id)\n        ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
        db.execSQL("CREATE INDEX IF NOT EXISTS idx_sync_devices_status ON sync_devices(status, device_role)");
        ensureSyncInboxOriginTimestampColumn(db);
        db.execSQL("CREATE INDEX IF NOT EXISTS idx_sync_inbox_status_sequence ON sync_inbox(status, sequence_number)");
        db.execSQL("CREATE INDEX IF NOT EXISTS idx_sync_envelopes_sequence ON sync_operation_envelopes(sequence_number)");
        db.execSQL("CREATE INDEX IF NOT EXISTS idx_sync_invitations_expiry ON sync_pairing_invitations(expires_at, consumed_at)");
    }

    private final void ensureOperatingExpenseSchema(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS expense_categories (\n    id TEXT PRIMARY KEY NOT NULL,\n    name TEXT NOT NULL COLLATE NOCASE UNIQUE,\n    is_active INTEGER NOT NULL DEFAULT 1 CHECK(is_active IN (0,1)),\n    created_by TEXT,\n    created_at TEXT NOT NULL,\n    FOREIGN KEY(created_by) REFERENCES users(id) ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
        db.execSQL("CREATE TABLE IF NOT EXISTS operating_expenses (\n    id TEXT PRIMARY KEY NOT NULL,\n    category_id TEXT NOT NULL,\n    cash_account_id TEXT NOT NULL,\n    cash_transaction_id TEXT NOT NULL UNIQUE,\n    amount_minor INTEGER NOT NULL CHECK(amount_minor > 0),\n    currency_code TEXT NOT NULL CHECK(currency_code IN ('USD','SYP')),\n    exchange_rate_syp_per_usd_milli INTEGER CHECK(\n        exchange_rate_syp_per_usd_milli IS NULL OR exchange_rate_syp_per_usd_milli > 0\n    ),\n    amount_usd_cents INTEGER NOT NULL CHECK(amount_usd_cents >= 0),\n    expense_date TEXT NOT NULL,\n    reference TEXT,\n    notes TEXT,\n    created_by TEXT NOT NULL,\n    operation_id TEXT NOT NULL UNIQUE,\n    created_at TEXT NOT NULL,\n    FOREIGN KEY(category_id) REFERENCES expense_categories(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(cash_account_id) REFERENCES cash_accounts(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(cash_transaction_id) REFERENCES cash_transactions(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(created_by) REFERENCES users(id) ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
        db.execSQL("CREATE INDEX IF NOT EXISTS idx_operating_expenses_date ON operating_expenses(expense_date,id)");
        db.execSQL("CREATE INDEX IF NOT EXISTS idx_operating_expenses_category ON operating_expenses(category_id,expense_date)");
        for (Pair pair : CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to("expense-rent", "\u0625\u064a\u062c\u0627\u0631"), TuplesKt.to("expense-salaries", "\u0631\u0648\u0627\u062a\u0628 \u0648\u0623\u062c\u0648\u0631"), TuplesKt.to("expense-transport", "\u0646\u0642\u0644 \u0648\u0645\u0648\u0627\u0635\u0644\u0627\u062a"), TuplesKt.to("expense-maintenance", "\u0635\u064a\u0627\u0646\u0629"), TuplesKt.to("expense-communications", "\u0627\u062a\u0635\u0627\u0644\u0627\u062a \u0648\u0625\u0646\u062a\u0631\u0646\u062a"), TuplesKt.to("expense-energy", "\u0648\u0642\u0648\u062f \u0648\u0637\u0627\u0642\u0629"), TuplesKt.to("expense-supplies", "\u0645\u0648\u0627\u062f \u062a\u0634\u063a\u064a\u0644\u064a\u0629"), TuplesKt.to("expense-fees", "\u0631\u0633\u0648\u0645 \u0648\u0639\u0645\u0648\u0644\u0627\u062a"), TuplesKt.to("expense-other", "\u0623\u062e\u0631\u0649")})) {
            db.execSQL("INSERT OR IGNORE INTO expense_categories(id,name,is_active,created_at) VALUES(?,?,1,strftime('%Y-%m-%dT%H:%M:%fZ','now'))", new String[]{(String) pair.component1(), (String) pair.component2()});
        }
    }

    private static final void ensureSupplierReturnSchema$ensureColumn$23(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) throws IOException {
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("PRAGMA table_info(" + str + ")", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            boolean z = false;
            while (cursor.moveToNext()) {
                if (Intrinsics.areEqual(cursor.getString(1), str2)) {
                    z = true;
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (z) {
                return;
            }
            sQLiteDatabase.execSQL("ALTER TABLE " + str + " ADD COLUMN " + str2 + " " + str3);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final void ensureSupplierReturnSchema(SQLiteDatabase db) throws IOException {
        ensureSupplierReturnSchema$ensureColumn$23(db, "return_items", "original_purchase_item_id", "TEXT");
        ensureSupplierReturnSchema$ensureColumn$23(db, "returns", "supplier_credit_reduction_syp_minor", "INTEGER NOT NULL DEFAULT 0 CHECK(supplier_credit_reduction_syp_minor >= 0)");
        ensureSupplierReturnSchema$ensureColumn$23(db, "returns", "supplier_cash_refund_syp_minor", "INTEGER NOT NULL DEFAULT 0 CHECK(supplier_cash_refund_syp_minor >= 0)");
        db.execSQL("CREATE INDEX IF NOT EXISTS idx_return_items_original_purchase_item ON return_items(original_purchase_item_id)");
        db.execSQL("CREATE TABLE IF NOT EXISTS supplier_return_refund_allocations (\n    id TEXT PRIMARY KEY NOT NULL,\n    return_id TEXT NOT NULL,\n    cash_account_id TEXT NOT NULL,\n    cash_transaction_id TEXT NOT NULL UNIQUE,\n    operation_id TEXT NOT NULL UNIQUE,\n    amount_minor INTEGER NOT NULL CHECK(amount_minor > 0),\n    currency_code TEXT NOT NULL CHECK(currency_code IN ('USD','SYP')),\n    exchange_rate_syp_per_usd_milli INTEGER NOT NULL CHECK(exchange_rate_syp_per_usd_milli > 0),\n    refund_equivalent_syp_minor INTEGER NOT NULL CHECK(refund_equivalent_syp_minor > 0),\n    created_at TEXT NOT NULL,\n    FOREIGN KEY(return_id) REFERENCES returns(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(cash_account_id) REFERENCES cash_accounts(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(cash_transaction_id) REFERENCES cash_transactions(id) ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
        db.execSQL("CREATE INDEX IF NOT EXISTS idx_supplier_return_refunds_return ON supplier_return_refund_allocations(return_id)");
        db.execSQL("CREATE TABLE IF NOT EXISTS supplier_return_cost_allocations (\n    id TEXT PRIMARY KEY NOT NULL,\n    return_item_id TEXT NOT NULL,\n    source_cost_layer_id TEXT NOT NULL,\n    inventory_movement_id TEXT NOT NULL UNIQUE,\n    quantity REAL NOT NULL CHECK(quantity > 0),\n    unit_cost_usd_cents INTEGER CHECK(unit_cost_usd_cents IS NULL OR unit_cost_usd_cents > 0),\n    cost_usd_cents INTEGER CHECK(cost_usd_cents IS NULL OR cost_usd_cents >= 0),\n    created_at TEXT NOT NULL,\n    FOREIGN KEY(return_item_id) REFERENCES return_items(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(source_cost_layer_id) REFERENCES inventory_cost_layers(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(inventory_movement_id) REFERENCES inventory_movements(id) ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
        db.execSQL("CREATE INDEX IF NOT EXISTS idx_supplier_return_cost_source ON supplier_return_cost_allocations(source_cost_layer_id)");
    }

    private final void ensureFifoSchema(SQLiteDatabase db) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("PRAGMA table_info(sales_items)", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            boolean z = false;
            while (cursor.moveToNext()) {
                if (Intrinsics.areEqual(cursor.getString(1), "cogs_usd_cents")) {
                    z = true;
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (!z) {
                db.execSQL("ALTER TABLE sales_items ADD COLUMN cogs_usd_cents INTEGER CHECK(cogs_usd_cents IS NULL OR cogs_usd_cents >= 0)");
            }
            db.execSQL("CREATE TABLE IF NOT EXISTS inventory_cost_layers (\n    id TEXT PRIMARY KEY NOT NULL,\n    product_id TEXT NOT NULL,\n    warehouse_id TEXT NOT NULL,\n    source_movement_id TEXT NOT NULL UNIQUE,\n    source_type TEXT,\n    source_id TEXT,\n    source_item_id TEXT,\n    received_quantity REAL NOT NULL CHECK(received_quantity > 0),\n    remaining_quantity REAL NOT NULL CHECK(remaining_quantity >= 0 AND remaining_quantity <= received_quantity),\n    unit_cost_usd_cents INTEGER CHECK(unit_cost_usd_cents IS NULL OR unit_cost_usd_cents > 0),\n    purchase_exchange_rate_syp_per_usd_milli INTEGER,\n    received_at TEXT NOT NULL,\n    FOREIGN KEY(product_id) REFERENCES products(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(warehouse_id) REFERENCES warehouses(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(source_movement_id) REFERENCES inventory_movements(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(source_item_id) REFERENCES purchase_items(id) ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
            db.execSQL("CREATE TABLE IF NOT EXISTS sales_item_cost_allocations (\n    id TEXT PRIMARY KEY NOT NULL,\n    sales_invoice_id TEXT NOT NULL,\n    sales_item_id TEXT,\n    cost_layer_id TEXT NOT NULL,\n    quantity REAL NOT NULL CHECK(quantity > 0),\n    unit_cost_usd_cents INTEGER CHECK(unit_cost_usd_cents IS NULL OR unit_cost_usd_cents > 0),\n    cost_usd_cents INTEGER CHECK(cost_usd_cents IS NULL OR cost_usd_cents >= 0),\n    created_at TEXT NOT NULL,\n    FOREIGN KEY(sales_invoice_id) REFERENCES sales_invoices(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(sales_item_id) REFERENCES sales_items(id) ON UPDATE RESTRICT ON DELETE RESTRICT,\n    FOREIGN KEY(cost_layer_id) REFERENCES inventory_cost_layers(id) ON UPDATE RESTRICT ON DELETE RESTRICT\n)");
            db.execSQL("CREATE INDEX IF NOT EXISTS idx_fifo_layers_available ON inventory_cost_layers(product_id,warehouse_id,received_at)");
            db.execSQL("CREATE INDEX IF NOT EXISTS idx_fifo_allocations_invoice ON sales_item_cost_allocations(sales_invoice_id,sales_item_id)");
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    /* JADX INFO: compiled from: AppDatabase.kt */
    @Metadata(d1 = {"\u00001\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\u008a\b\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\bH\u00c6\u0003J\t\u0010#\u001a\u00020\nH\u00c6\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010'\u001a\u00020\u0003H\u00c6\u0003Jx\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010)J\u0013\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010-\u001a\u00020.H\u00d6\u0001J\t\u0010/\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0012R\u0011\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0012\u00a8\u00060"}, d2 = {"com/hazem/dailystore/data/AppDatabase$backfillFifoCostLayers$Movement", "", "id", "", "productId", "warehouseId", "type", "delta", "", "unitCost", "", "sourceType", "sourceId", "operationId", "createdAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getProductId", "getWarehouseId", "getType", "getDelta", "()D", "getUnitCost", "()J", "getSourceType", "getSourceId", "getOperationId", "getCreatedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/hazem/dailystore/data/AppDatabase$backfillFifoCostLayers$Movement;", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class Movement {
        private final String createdAt;
        private final double delta;
        private final String id;
        private final String operationId;
        private final String productId;
        private final String sourceId;
        private final String sourceType;
        private final String type;
        private final long unitCost;
        private final String warehouseId;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final String getCreatedAt() {
            return this.createdAt;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getProductId() {
            return this.productId;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getWarehouseId() {
            return this.warehouseId;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final double getDelta() {
            return this.delta;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final long getUnitCost() {
            return this.unitCost;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getSourceType() {
            return this.sourceType;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getSourceId() {
            return this.sourceId;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getOperationId() {
            return this.operationId;
        }

        public final Movement copy(String id, String productId, String warehouseId, String type, double delta, long unitCost, String sourceType, String sourceId, String operationId, String createdAt) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(productId, "productId");
            Intrinsics.checkNotNullParameter(warehouseId, "warehouseId");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(createdAt, "createdAt");
            return new Movement(id, productId, warehouseId, type, delta, unitCost, sourceType, sourceId, operationId, createdAt);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Movement)) {
                return false;
            }
            Movement movement = (Movement) other;
            return Intrinsics.areEqual(this.id, movement.id) && Intrinsics.areEqual(this.productId, movement.productId) && Intrinsics.areEqual(this.warehouseId, movement.warehouseId) && Intrinsics.areEqual(this.type, movement.type) && Double.compare(this.delta, movement.delta) == 0 && this.unitCost == movement.unitCost && Intrinsics.areEqual(this.sourceType, movement.sourceType) && Intrinsics.areEqual(this.sourceId, movement.sourceId) && Intrinsics.areEqual(this.operationId, movement.operationId) && Intrinsics.areEqual(this.createdAt, movement.createdAt);
        }

        public int hashCode() {
            int iHashCode = ((((((((((this.id.hashCode() * 31) + this.productId.hashCode()) * 31) + this.warehouseId.hashCode()) * 31) + this.type.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.delta)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.unitCost)) * 31;
            String str = this.sourceType;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.sourceId;
            int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.operationId;
            return ((iHashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31) + this.createdAt.hashCode();
        }

        public String toString() {
            return "Movement(id=" + this.id + ", productId=" + this.productId + ", warehouseId=" + this.warehouseId + ", type=" + this.type + ", delta=" + this.delta + ", unitCost=" + this.unitCost + ", sourceType=" + this.sourceType + ", sourceId=" + this.sourceId + ", operationId=" + this.operationId + ", createdAt=" + this.createdAt + ")";
        }

        public Movement(String id, String productId, String warehouseId, String type, double d, long j, String str, String str2, String str3, String createdAt) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(productId, "productId");
            Intrinsics.checkNotNullParameter(warehouseId, "warehouseId");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(createdAt, "createdAt");
            this.id = id;
            this.productId = productId;
            this.warehouseId = warehouseId;
            this.type = type;
            this.delta = d;
            this.unitCost = j;
            this.sourceType = str;
            this.sourceId = str2;
            this.operationId = str3;
            this.createdAt = createdAt;
        }

        public final String getId() {
            return this.id;
        }

        public final String getProductId() {
            return this.productId;
        }

        public final String getWarehouseId() {
            return this.warehouseId;
        }

        public final double getDelta() {
            return this.delta;
        }

        public final String getType() {
            return this.type;
        }

        public final long getUnitCost() {
            return this.unitCost;
        }

        public final String getSourceId() {
            return this.sourceId;
        }

        public final String getSourceType() {
            return this.sourceType;
        }

        public final String getCreatedAt() {
            return this.createdAt;
        }

        public final String getOperationId() {
            return this.operationId;
        }
    }

    private final void backfillFifoCostLayers(SQLiteDatabase db) throws IOException {
        int i;
        int i2;
        String operationId;
        String strSubstringAfterLast$default;
        String strSubstringAfterLast$default2;
        ArrayList<Movement> arrayList = new ArrayList();
        Cursor cursorRawQuery = db.rawQuery("SELECT id,product_id,warehouse_id,movement_type,quantity_delta,\n                      unit_cost_usd_cents,source_type,source_id,operation_id,created_at\n               FROM inventory_movements ORDER BY created_at ASC,rowid ASC", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            while (true) {
                i = 2;
                if (!cursor.moveToNext()) {
                    break;
                }
                String string = cursor.getString(0);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String string2 = cursor.getString(1);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                String string3 = cursor.getString(2);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                String string4 = cursor.getString(3);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                double d = cursor.getDouble(4);
                long j = cursor.getLong(5);
                String string5 = cursor.isNull(6) ? null : cursor.getString(6);
                String string6 = cursor.isNull(7) ? null : cursor.getString(7);
                String string7 = cursor.isNull(8) ? null : cursor.getString(8);
                String string8 = cursor.getString(9);
                Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
                arrayList.add(new Movement(string, string2, string3, string4, d, j, string5, string6, string7, string8));
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            for (Movement movement : arrayList) {
                if (movement.getDelta() > 0.0d) {
                    FifoCosting fifoCosting = FifoCosting.INSTANCE;
                    String id = movement.getId();
                    String productId = movement.getProductId();
                    String warehouseId = movement.getWarehouseId();
                    double delta = movement.getDelta();
                    long unitCost = movement.getUnitCost();
                    String sourceType = movement.getSourceType();
                    String sourceId = movement.getSourceId();
                    String createdAt = movement.getCreatedAt();
                    String operationId2 = movement.getOperationId();
                    i2 = i;
                    fifoCosting.addLayer(db, id, productId, warehouseId, delta, unitCost, sourceType, sourceId, createdAt, (operationId2 == null || (strSubstringAfterLast$default2 = StringsKt.substringAfterLast$default(operationId2, ':', (String) null, i, (Object) null)) == null || !Intrinsics.areEqual(movement.getSourceType(), "PURCHASE_INVOICE")) ? null : strSubstringAfterLast$default2);
                } else {
                    i2 = i;
                    String str = (!Intrinsics.areEqual(movement.getType(), "SALE") || (operationId = movement.getOperationId()) == null || (strSubstringAfterLast$default = StringsKt.substringAfterLast$default(operationId, ':', (String) null, i2, (Object) null)) == null || StringsKt.isBlank(strSubstringAfterLast$default)) ? null : strSubstringAfterLast$default;
                    FifoCosting fifoCosting2 = FifoCosting.INSTANCE;
                    String sourceId2 = movement.getSourceId();
                    if (sourceId2 == null) {
                        if (Intrinsics.areEqual(movement.getType(), "SALE")) {
                            throw new IllegalStateException(("\u062d\u0631\u0643\u0629 \u0628\u064a\u0639 \u0633\u0627\u0644\u0628\u0629 \u0628\u0644\u0627 \u0645\u0631\u062c\u0639 \u0641\u0627\u062a\u0648\u0631\u0629: " + movement.getId()).toString());
                        }
                        sourceId2 = "LEGACY_MOVEMENT:" + movement.getId();
                    }
                    fifoCosting2.consumeForSale(db, sourceId2, str, movement.getProductId(), movement.getWarehouseId(), -movement.getDelta(), movement.getCreatedAt(), Intrinsics.areEqual(movement.getType(), "SALE"));
                }
                i = i2;
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

    /* JADX INFO: compiled from: AppDatabase.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000bH\u0000\u00a2\u0006\u0002\b\u0011J\r\u0010\u0012\u001a\u00020\rH\u0000\u00a2\u0006\u0002\b\u0013J\r\u0010\u0014\u001a\u00020\u000bH\u0000\u00a2\u0006\u0002\b\u0015J\u0015\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\tH\u0000\u00a2\u0006\u0002\b\u0018J\u000e\u0010\u0019\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u001a\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\u001fX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010 \u00a8\u0006!"}, d2 = {"Lcom/hazem/dailystore/data/AppDatabase$Companion;", "", "<init>", "()V", "DATABASE_NAME", "", "DATABASE_VERSION", "", "INSTANCE", "Lcom/hazem/dailystore/data/AppDatabase;", "plaintextBackendForTests", "", "resetForTests", "", "context", "Landroid/content/Context;", "deleteExisting", "resetForTests$app_release", "resetInstanceAfterEncryptedRestore", "resetInstanceAfterEncryptedRestore$app_release", "isPlaintextBackendForTests", "isPlaintextBackendForTests$app_release", "installForTests", "database", "installForTests$app_release", "getInstance", "createDatabaseInstance", "isLegacyPlaintextDatabase", "file", "Ljava/io/File;", "SCHEMA", "", "[Ljava/lang/String;", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ void resetForTests$app_release$default(Companion companion, Context context, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = true;
            }
            companion.resetForTests$app_release(context, z);
        }

        public final synchronized void resetForTests$app_release(Context context, boolean deleteExisting) {
            Intrinsics.checkNotNullParameter(context, "context");
            AppDatabase appDatabase = AppDatabase.INSTANCE;
            if (appDatabase != null) {
                appDatabase.close();
            }
            AppDatabase.INSTANCE = null;
            AppDatabase.plaintextBackendForTests = true;
            if (deleteExisting) {
                context.deleteDatabase(AppDatabase.DATABASE_NAME);
            }
        }

        public final synchronized void resetInstanceAfterEncryptedRestore$app_release() {
            AppDatabase appDatabase = AppDatabase.INSTANCE;
            if (appDatabase != null) {
                appDatabase.close();
            }
            AppDatabase.INSTANCE = null;
        }

        public final boolean isPlaintextBackendForTests$app_release() {
            return AppDatabase.plaintextBackendForTests;
        }

        public final synchronized void installForTests$app_release(AppDatabase database) {
            Intrinsics.checkNotNullParameter(database, "database");
            AppDatabase appDatabase = AppDatabase.INSTANCE;
            if (appDatabase != null) {
                if (appDatabase == database) {
                    appDatabase = null;
                }
                if (appDatabase != null) {
                    appDatabase.close();
                }
            }
            AppDatabase.INSTANCE = database;
        }

        public final AppDatabase getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            AppDatabase appDatabase = AppDatabase.INSTANCE;
            if (appDatabase == null) {
                synchronized (this) {
                    appDatabase = AppDatabase.INSTANCE;
                    if (appDatabase == null) {
                        Companion companion = AppDatabase.INSTANCE;
                        Context applicationContext = context.getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                        AppDatabase appDatabaseCreateDatabaseInstance = companion.createDatabaseInstance(applicationContext);
                        Companion companion2 = AppDatabase.INSTANCE;
                        AppDatabase.INSTANCE = appDatabaseCreateDatabaseInstance;
                        appDatabase = appDatabaseCreateDatabaseInstance;
                    }
                }
            }
            return appDatabase;
        }

        private final AppDatabase createDatabaseInstance(Context context) {
            if (AppDatabase.plaintextBackendForTests) {
                return new AppDatabase(context, null, null, 6, null);
            }
            File databasePath = context.getDatabasePath(AppDatabase.DATABASE_NAME);
            DatabaseKeyVault databaseKeyVault = new DatabaseKeyVault(context, null, null, 6, null);
            Intrinsics.checkNotNull(databasePath);
            if (isLegacyPlaintextDatabase(databasePath)) {
                new SqlCipherLegacyMigrationCoordinator(context, null, null, 6, null).migrate(databasePath, databaseKeyVault, new Function0() { // from class: com.hazem.dailystore.data.AppDatabase$Companion$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Unit.INSTANCE;
                    }
                });
            }
            byte[] productionDatabaseKey = SqlCipherDatabaseRuntime.INSTANCE.getProductionDatabaseKey(databaseKeyVault, databasePath);
            try {
                return new AppDatabase(context, AppDatabase.DATABASE_NAME, productionDatabaseKey);
            } finally {
                ArraysKt.fill$default(productionDatabaseKey, (byte) 0, 0, 0, 6, (Object) null);
            }
        }

        private final boolean isLegacyPlaintextDatabase(File file) throws IOException {
            boolean z = false;
            if (!file.isFile() || file.length() < 16) {
                return false;
            }
            byte[] bytes = "SQLite format 3\u0000".getBytes(Charsets.US_ASCII);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                int length = bytes.length;
                byte[] bArr = new byte[length];
                if (fileInputStream.read(bArr) == length && Arrays.equals(bArr, bytes)) {
                    z = true;
                }
                CloseableKt.closeFinally(fileInputStream, null);
                return z;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(fileInputStream, th);
                    throw th2;
                }
            }
        }
    }
}
