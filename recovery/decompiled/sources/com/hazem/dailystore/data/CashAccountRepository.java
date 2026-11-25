package com.hazem.dailystore.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.zetetic.database.sqlcipher.SQLiteDatabase;

/* JADX INFO: compiled from: CashAccountRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0011J\u0014\u0010\u000e\u001a\u00020\u000f2\n\u0010\u0013\u001a\u00060\u0014j\u0002`\u0015H\u0002J:\u0010\u0016\u001a\u00020\u00112\n\u0010\u0013\u001a\u00060\u0014j\u0002`\u00152\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u00112\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00110\fH\u0002J$\u0010\u001b\u001a\u00020\u000f2\n\u0010\u0013\u001a\u00060\u0014j\u0002`\u00152\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u0011H\u0002J\b\u0010\u001d\u001a\u0004\u0018\u00010\rJ\u001e\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u0011J\b\u0010!\u001a\u00020\u0011H\u0002J\u0010\u0010\"\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\u0011H\u0002J\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\u0016\u0010%\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010&\u001a\u00020'H\u0002J0\u0010(\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u00112\u0006\u0010)\u001a\u00020\u00112\u0006\u0010*\u001a\u00020\u00112\u0006\u0010+\u001a\u00020\u00112\b\b\u0002\u0010,\u001a\u00020-J\u000e\u0010.\u001a\u00020'2\u0006\u0010\u001c\u001a\u00020\u0011J6\u0010/\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00112\u0006\u0010)\u001a\u00020\u00112\u0006\u0010*\u001a\u00020\u00112\u0006\u0010+\u001a\u00020\u0011J\u001c\u00100\u001a\u00020\u000f2\n\u0010\u0013\u001a\u00060\u0014j\u0002`\u00152\u0006\u0010 \u001a\u00020\u0011H\u0002J\u0010\u00101\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\u0011H\u0002J\u000e\u00102\u001a\u00020-2\u0006\u0010\u001c\u001a\u00020\u0011J\u0018\u00102\u001a\u00020-2\u0006\u0010\u001c\u001a\u00020\u00112\u0006\u00103\u001a\u00020-H\u0002J\b\u00104\u001a\u00020\u0011H\u0002R\u0018\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00065"}, d2 = {"Lcom/hazem/dailystore/data/CashAccountRepository;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "appContext", "kotlin.jvm.PlatformType", "Landroid/content/Context;", "database", "Lcom/hazem/dailystore/data/AppDatabase;", "listActive", "", "Lcom/hazem/dailystore/data/CashAccountSummary;", "ensureSystemCashAccounts", "", "systemRoleAccountId", "", "roleKey", "db", "Lnet/zetetic/database/sqlcipher/SQLiteDatabase;", "Lcom/hazem/dailystore/data/AppSqliteDatabase;", "ensureRoleAccount", "role", "systemId", "defaultName", "aliases", "setSystemRole", "accountId", "currentDayPrimaryDrawer", "setCurrentDayPrimaryDrawer", "actorRole", "actorUserId", "operatingDate", "jsonEscape", "value", "listAll", "listAccounts", "activeOnly", "", "create", "name", "accountType", "currencyCode", "openingBalanceMinor", "", "hasTransactions", "updateDetails", "requireManager", "jsonString", "balance", "opening", "timestamp", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class CashAccountRepository {
    private final Context appContext;
    private final AppDatabase database;

    public CashAccountRepository(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Context appContext = context.getApplicationContext();
        this.appContext = appContext;
        AppDatabase.Companion companion = AppDatabase.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        this.database = companion.getInstance(appContext);
    }

    public final List<CashAccountSummary> listActive() {
        ensureSystemCashAccounts();
        return listAccounts(true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void ensureSystemCashAccounts() {
        Context appContext = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        if (new SyncProtocolRepository(appContext, null, 2, null).isConfiguredSecondary()) {
            return;
        }
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        writableDatabase.beginTransaction();
        try {
            ensureSystemCashAccounts(writableDatabase);
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    public final String systemRoleAccountId(String roleKey) {
        Intrinsics.checkNotNullParameter(roleKey, "roleKey");
        ensureSystemCashAccounts();
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery("SELECT account_id FROM system_cash_account_roles WHERE role_key=?", new String[]{roleKey});
        try {
            Cursor cursor = cursorRawQuery;
            String string = cursor.moveToFirst() ? cursor.getString(0) : null;
            CloseableKt.closeFinally(cursorRawQuery, null);
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

    /* JADX WARN: Code duplicated, block: B:72:0x0159  */
    private final void ensureSystemCashAccounts(SQLiteDatabase db) throws IOException {
        Throwable th;
        String string;
        boolean z;
        String strEnsureRoleAccount;
        Throwable th2;
        String string2;
        Throwable th3;
        String string3;
        boolean z2;
        String strEnsureRoleAccount2;
        boolean z3;
        String strOperatingDate = operatingDate();
        Cursor cursorRawQuery = db.rawQuery("SELECT primary_cash_account_id FROM daily_cash_sessions WHERE operating_date=?", new String[]{strOperatingDate});
        try {
            Cursor cursor = cursorRawQuery;
            if (cursor.moveToFirst()) {
                try {
                    string = cursor.getString(0);
                } catch (Throwable th4) {
                    th = th4;
                    try {
                        throw th;
                    } catch (Throwable th5) {
                        CloseableKt.closeFinally(cursorRawQuery, th);
                        throw th5;
                    }
                }
            } else {
                string = null;
            }
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (string != null) {
                Cursor cursorRawQuery2 = db.rawQuery("SELECT 1 FROM cash_accounts WHERE id=? AND account_type='CASH_BOX' AND currency_code='SYP' AND is_active=1", new String[]{string});
                try {
                    boolean zMoveToFirst = cursorRawQuery2.moveToFirst();
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    z = zMoveToFirst;
                } catch (Throwable th6) {
                    try {
                        throw th6;
                    } catch (Throwable th7) {
                        CloseableKt.closeFinally(cursorRawQuery2, th6);
                        throw th7;
                    }
                }
            } else {
                z = false;
            }
            if (!z) {
                db.delete("system_cash_account_roles", "role_key='DAILY_DRAWER'", (String[]) null);
            }
            if (z) {
                Intrinsics.checkNotNull(string);
                strEnsureRoleAccount = string;
            } else {
                strEnsureRoleAccount = ensureRoleAccount(db, "DAILY_DRAWER", "system_cash_daily_drawer", "\u062f\u0631\u062c \u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u064a\u0648\u0645\u064a", CollectionsKt.listOf((Object[]) new String[]{"\u062f\u0631\u062c \u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u064a\u0648\u0645\u064a", "\u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u064a\u0648\u0645\u064a", "\u0627\u0644\u062f\u0631\u062c \u0627\u0644\u0631\u0626\u064a\u0633\u064a", "\u062f\u0631\u062c \u0627\u0644\u0645\u0628\u064a\u0639\u0627\u062a", "\u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0645\u0628\u064a\u0639\u0627\u062a \u0627\u0644\u064a\u0648\u0645\u064a"}));
            }
            if (!z) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("operating_date", strOperatingDate);
                contentValues.put("primary_cash_account_id", strEnsureRoleAccount);
                contentValues.putNull("created_by");
                contentValues.put("created_at", timestamp());
                if (string == null) {
                    db.insertOrThrow("daily_cash_sessions", null, contentValues);
                } else {
                    db.update("daily_cash_sessions", contentValues, "operating_date=?", new String[]{strOperatingDate});
                }
            }
            setSystemRole(db, "DAILY_DRAWER", strEnsureRoleAccount);
            Cursor cursorRawQuery3 = db.rawQuery("SELECT account_id FROM daily_supplier_rollovers WHERE operating_date=?", new String[]{strOperatingDate});
            try {
                Cursor cursor2 = cursorRawQuery3;
                if (cursor2.moveToFirst()) {
                    try {
                        string2 = cursor2.getString(0);
                    } catch (Throwable th8) {
                        th2 = th8;
                        try {
                            throw th2;
                        } catch (Throwable th9) {
                            CloseableKt.closeFinally(cursorRawQuery3, th2);
                            throw th9;
                        }
                    }
                } else {
                    string2 = null;
                }
                CloseableKt.closeFinally(cursorRawQuery3, null);
                if (string2 == null) {
                    string2 = ensureRoleAccount(db, "SUPPLIER_ROLLOVER", "system_cash_supplier_rollover", "\u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0644\u0644\u0628\u0636\u0627\u0639\u0629", CollectionsKt.listOf((Object[]) new String[]{"\u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0644\u0644\u0628\u0636\u0627\u0639\u0629", "\u0635\u0646\u062f\u0648\u0642 \u0646\u0642\u062f\u064a \u0645\u062f\u0648\u0631 \u0644\u0644\u0628\u0636\u0627\u0639\u0629", "\u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0627\u0644\u0645\u062f\u0648\u0631", "\u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0644\u0644\u0645\u0648\u0631\u062f\u064a\u0646", "\u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0628\u0636\u0627\u0639\u0629"}));
                }
                String str = string2;
                setSystemRole(db, "SUPPLIER_ROLLOVER", str);
                Cursor cursorRawQuery4 = db.rawQuery("SELECT setting_value FROM app_settings WHERE setting_key='service_recharge_cash_account_id'", (String[]) null);
                try {
                    Cursor cursor3 = cursorRawQuery4;
                    if (cursor3.moveToFirst()) {
                        try {
                            string3 = cursor3.getString(0);
                        } catch (Throwable th10) {
                            th3 = th10;
                            try {
                                throw th3;
                            } catch (Throwable th11) {
                                CloseableKt.closeFinally(cursorRawQuery4, th3);
                                throw th11;
                            }
                        }
                    } else {
                        string3 = null;
                    }
                    CloseableKt.closeFinally(cursorRawQuery4, null);
                    if (string3 != null) {
                        if (Intrinsics.areEqual(string3, strEnsureRoleAccount) || Intrinsics.areEqual(string3, str)) {
                            z3 = false;
                        } else {
                            Cursor cursorRawQuery5 = db.rawQuery("SELECT 1 FROM cash_accounts WHERE id=? AND account_type='CASH_BOX' AND currency_code='SYP' AND is_active=1", new String[]{string3});
                            try {
                                boolean zMoveToFirst2 = cursorRawQuery5.moveToFirst();
                                CloseableKt.closeFinally(cursorRawQuery5, null);
                                if (zMoveToFirst2) {
                                    z3 = true;
                                } else {
                                    z3 = false;
                                }
                            } catch (Throwable th12) {
                                try {
                                    throw th12;
                                } catch (Throwable th13) {
                                    CloseableKt.closeFinally(cursorRawQuery5, th12);
                                    throw th13;
                                }
                            }
                        }
                        z2 = z3;
                    } else {
                        z2 = false;
                    }
                    if (!z2) {
                        Cursor cursorRawQuery6 = db.rawQuery("SELECT account_id FROM system_cash_account_roles WHERE role_key='SERVICE_RECHARGE'", (String[]) null);
                        try {
                            Cursor cursor4 = cursorRawQuery6;
                            String string4 = cursor4.moveToFirst() ? cursor4.getString(0) : null;
                            CloseableKt.closeFinally(cursorRawQuery6, null);
                            if (Intrinsics.areEqual(string4, strEnsureRoleAccount) || Intrinsics.areEqual(string4, str)) {
                                db.delete("system_cash_account_roles", "role_key='SERVICE_RECHARGE'", (String[]) null);
                            }
                        } catch (Throwable th14) {
                            try {
                                throw th14;
                            } catch (Throwable th15) {
                                CloseableKt.closeFinally(cursorRawQuery6, th14);
                                throw th15;
                            }
                        }
                    }
                    if (z2) {
                        Intrinsics.checkNotNull(string3);
                        strEnsureRoleAccount2 = string3;
                    } else {
                        strEnsureRoleAccount2 = ensureRoleAccount(db, "SERVICE_RECHARGE", "system_cash_service_recharge", "\u0635\u0646\u062f\u0648\u0642 \u0634\u0631\u0627\u0621 \u0631\u0635\u064a\u062f \u0627\u0644\u062e\u062f\u0645\u0627\u062a", CollectionsKt.listOf((Object[]) new String[]{"\u0635\u0646\u062f\u0648\u0642 \u0631\u0635\u064a\u062f \u062e\u062f\u0645\u0627\u062a \u0646\u0642\u062f\u064a", "\u0635\u0646\u062f\u0648\u0642 \u0631\u0635\u064a\u062f \u0627\u0644\u062e\u062f\u0645\u0627\u062a \u0646\u0642\u062f\u064a", "\u0635\u0646\u062f\u0648\u0642 \u0631\u0635\u064a\u062f \u0627\u0644\u062e\u062f\u0645\u0627\u062a \u0627\u0644\u0646\u0642\u062f\u064a", "\u0635\u0646\u062f\u0648\u0642 \u0634\u0631\u0627\u0621 \u0631\u0635\u064a\u062f \u0627\u0644\u062e\u062f\u0645\u0627\u062a", "\u0635\u0646\u062f\u0648\u0642 \u0631\u0635\u064a\u062f \u0627\u0644\u062e\u062f\u0645\u0627\u062a", "\u0635\u0646\u062f\u0648\u0642 \u062a\u0639\u0628\u0626\u0629 \u0631\u0635\u064a\u062f \u0627\u0644\u062e\u062f\u0645\u0627\u062a"}));
                    }
                    if (Intrinsics.areEqual(strEnsureRoleAccount2, strEnsureRoleAccount) || Intrinsics.areEqual(strEnsureRoleAccount2, str)) {
                        db.delete("system_cash_account_roles", "role_key='SERVICE_RECHARGE'", (String[]) null);
                        strEnsureRoleAccount2 = UUID.randomUUID().toString();
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("id", strEnsureRoleAccount2);
                        contentValues2.put("name", "\u0635\u0646\u062f\u0648\u0642 \u0634\u0631\u0627\u0621 \u0631\u0635\u064a\u062f \u0627\u0644\u062e\u062f\u0645\u0627\u062a");
                        contentValues2.put("account_type", "CASH_BOX");
                        contentValues2.put("currency_code", "SYP");
                        contentValues2.put("opening_balance_minor", (Long) 0L);
                        contentValues2.put("is_active", (Integer) 1);
                        contentValues2.put("created_at", timestamp());
                        db.insertOrThrow("cash_accounts", null, contentValues2);
                    }
                    if (!z2 || !Intrinsics.areEqual(string3, strEnsureRoleAccount2)) {
                        ContentValues contentValues3 = new ContentValues();
                        contentValues3.put("setting_key", "service_recharge_cash_account_id");
                        contentValues3.put("setting_value", strEnsureRoleAccount2);
                        contentValues3.put("updated_at", timestamp());
                        db.insertWithOnConflict("app_settings", null, contentValues3, 5);
                    }
                    setSystemRole(db, "SERVICE_RECHARGE", strEnsureRoleAccount2);
                } catch (Throwable th16) {
                    th3 = th16;
                }
            } catch (Throwable th17) {
                th2 = th17;
            }
        } catch (Throwable th18) {
            th = th18;
        }
    }

    private final String ensureRoleAccount(SQLiteDatabase db, String role, String systemId, String defaultName, List<String> aliases) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT account_id FROM system_cash_account_roles WHERE role_key=?", new String[]{role});
        try {
            Cursor cursor = cursorRawQuery;
            String string = cursor.moveToFirst() ? cursor.getString(0) : null;
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (string != null) {
                Cursor cursorRawQuery2 = db.rawQuery("SELECT 1 FROM cash_accounts WHERE id=? AND account_type='CASH_BOX' AND currency_code='SYP' AND is_active=1", new String[]{string});
                try {
                    boolean zMoveToFirst = cursorRawQuery2.moveToFirst();
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    if (zMoveToFirst) {
                        return string;
                    }
                    throw new IllegalArgumentException(("\u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0627\u0644\u0645\u0631\u062a\u0628\u0637 \u0628\u0627\u0644\u062f\u0648\u0631 " + role + " \u063a\u064a\u0631 \u0646\u0634\u0637 \u0623\u0648 \u062a\u063a\u064a\u0631 \u0646\u0648\u0639\u0647").toString());
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(cursorRawQuery2, th);
                        throw th2;
                    }
                }
            }
            List<String> list = aliases;
            String str = "SELECT id FROM cash_accounts WHERE account_type='CASH_BOX' AND currency_code='SYP' AND is_active=1 AND lower(trim(name)) IN (" + CollectionsKt.joinToString$default(list, ",", null, null, 0, null, new Function1() { // from class: com.hazem.dailystore.data.CashAccountRepository$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return CashAccountRepository.ensureRoleAccount$lambda$15((String) obj);
                }
            }, 30, null) + ") ORDER BY created_at,id LIMIT 1";
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (String str2 : list) {
                Locale ROOT = Locale.ROOT;
                Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
                String lowerCase = str2.toLowerCase(ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                arrayList.add(lowerCase);
            }
            Cursor cursorRawQuery3 = db.rawQuery(str, (String[]) arrayList.toArray(new String[0]));
            try {
                Cursor cursor2 = cursorRawQuery3;
                String string2 = cursor2.moveToFirst() ? cursor2.getString(0) : null;
                CloseableKt.closeFinally(cursorRawQuery3, null);
                if (string2 == null) {
                    Cursor cursorRawQuery4 = db.rawQuery("SELECT 1 FROM cash_accounts WHERE id=?", new String[]{systemId});
                    try {
                        boolean zMoveToFirst2 = cursorRawQuery4.moveToFirst();
                        CloseableKt.closeFinally(cursorRawQuery4, null);
                        String string3 = zMoveToFirst2 ? UUID.randomUUID().toString() : systemId;
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("id", string3);
                        contentValues.put("name", defaultName);
                        contentValues.put("account_type", "CASH_BOX");
                        contentValues.put("currency_code", "SYP");
                        contentValues.put("opening_balance_minor", (Long) 0L);
                        contentValues.put("is_active", (Integer) 1);
                        contentValues.put("created_at", timestamp());
                        db.insertOrThrow("cash_accounts", null, contentValues);
                        string2 = string3;
                    } catch (Throwable th3) {
                        try {
                            throw th3;
                        } catch (Throwable th4) {
                            CloseableKt.closeFinally(cursorRawQuery4, th3);
                            throw th4;
                        }
                    }
                }
                setSystemRole(db, role, string2);
                return string2;
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
                CloseableKt.closeFinally(cursorRawQuery, th7);
                throw th8;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence ensureRoleAccount$lambda$15(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return "?";
    }

    private final void setSystemRole(SQLiteDatabase db, String role, String accountId) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT account_id FROM system_cash_account_roles WHERE role_key=?", new String[]{role});
        try {
            Cursor cursor = cursorRawQuery;
            String string = cursor.moveToFirst() ? cursor.getString(0) : null;
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (Intrinsics.areEqual(string, accountId)) {
                return;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("role_key", role);
            contentValues.put("account_id", accountId);
            contentValues.put("linked_at", timestamp());
            db.insertWithOnConflict("system_cash_account_roles", null, contentValues, 5);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    public final CashAccountSummary currentDayPrimaryDrawer() {
        CashAccountSummary cashAccountSummary;
        ensureSystemCashAccounts();
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery("SELECT a.id,a.name,a.account_type,a.currency_code,a.opening_balance_minor,a.is_active\n               FROM daily_cash_sessions s JOIN cash_accounts a ON a.id=s.primary_cash_account_id\n               WHERE s.operating_date=? AND a.is_active=1\n                 AND a.account_type='CASH_BOX' AND a.currency_code='SYP' LIMIT 1", new String[]{operatingDate()});
        try {
            Cursor cursor = cursorRawQuery;
            if (cursor.moveToFirst()) {
                String string = cursor.getString(0);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String string2 = cursor.getString(1);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                String string3 = cursor.getString(2);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                String string4 = cursor.getString(3);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                String string5 = cursor.getString(0);
                Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                cashAccountSummary = new CashAccountSummary(string, string2, string3, string4, balance(string5, cursor.getLong(4)), cursor.getInt(5) == 1);
            } else {
                cashAccountSummary = null;
            }
            CloseableKt.closeFinally(cursorRawQuery, null);
            return cashAccountSummary;
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
    public final void setCurrentDayPrimaryDrawer(String actorRole, String actorUserId, String accountId) {
        String str;
        Intrinsics.checkNotNullParameter(actorRole, "actorRole");
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        ensureSystemCashAccounts();
        if (!Intrinsics.areEqual(actorRole, "ADMIN") || StringsKt.isBlank(actorUserId)) {
            throw new IllegalArgumentException("\u062a\u0639\u064a\u064a\u0646 \u0627\u0644\u062f\u0631\u062c \u0627\u0644\u064a\u0648\u0645\u064a \u0627\u0644\u0631\u0626\u064a\u0633\u064a \u0645\u062a\u0627\u062d \u0644\u0644\u0645\u062f\u064a\u0631 \u0641\u0642\u0637".toString());
        }
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        writableDatabase.beginTransaction();
        try {
            PermissionPolicy.INSTANCE.require(writableDatabase, actorUserId, PermissionPolicy.DAILY_CASH_MANAGE);
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT name FROM cash_accounts WHERE id=? AND account_type='CASH_BOX' AND currency_code='SYP' AND is_active=1", new String[]{accountId});
            try {
                Cursor cursor = cursorRawQuery;
                if (!cursor.moveToFirst()) {
                    throw new IllegalArgumentException("\u0627\u062e\u062a\u0631 \u0635\u0646\u062f\u0648\u0642\u0627\u064b \u0646\u0642\u062f\u064a\u0627\u064b \u0646\u0634\u0637\u0627\u064b \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629".toString());
                }
                String string = cursor.getString(0);
                CloseableKt.closeFinally(cursorRawQuery, null);
                String strOperatingDate = operatingDate();
                Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT 1 FROM daily_supplier_rollovers WHERE operating_date=? AND account_id=?", new String[]{strOperatingDate, accountId});
                try {
                    boolean zMoveToFirst = cursorRawQuery2.moveToFirst();
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    if (zMoveToFirst) {
                        throw new IllegalArgumentException("\u0647\u0630\u0627 \u0627\u0644\u0635\u0646\u062f\u0648\u0642 \u0645\u062e\u0635\u0635 \u0644\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0644\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0627\u0644\u064a\u0648\u0645 \u0648\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u0639\u064a\u064a\u0646\u0647 \u0643\u062f\u0631\u062c \u0627\u0644\u0645\u0628\u064a\u0639\u0627\u062a \u0648\u0627\u0644\u062a\u062d\u0635\u064a\u0644".toString());
                    }
                    Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT 1 FROM app_settings WHERE setting_key='service_recharge_cash_account_id' AND setting_value=?", new String[]{accountId});
                    try {
                        boolean zMoveToFirst2 = cursorRawQuery3.moveToFirst();
                        CloseableKt.closeFinally(cursorRawQuery3, null);
                        Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT 1 FROM system_cash_account_roles WHERE role_key='SUPPLIER_ROLLOVER' AND account_id=?", new String[]{accountId});
                        try {
                            boolean zMoveToFirst3 = cursorRawQuery4.moveToFirst();
                            CloseableKt.closeFinally(cursorRawQuery4, null);
                            if (zMoveToFirst2) {
                                throw new IllegalArgumentException("\u0647\u0630\u0627 \u0627\u0644\u0635\u0646\u062f\u0648\u0642 \u0645\u062e\u0635\u0635 \u0644\u0634\u0631\u0627\u0621 \u0631\u0635\u064a\u062f \u0627\u0644\u062e\u062f\u0645\u0627\u062a \u0648\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u0639\u064a\u064a\u0646\u0647 \u0643\u062f\u0631\u062c \u0627\u0644\u0645\u0628\u064a\u0639\u0627\u062a \u0648\u0627\u0644\u062a\u062d\u0635\u064a\u0644".toString());
                            }
                            if (zMoveToFirst3) {
                                throw new IllegalArgumentException("\u0647\u0630\u0627 \u0627\u0644\u0635\u0646\u062f\u0648\u0642 \u0645\u062e\u0635\u0635 \u0644\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0644\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0648\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u0639\u064a\u064a\u0646\u0647 \u0643\u062f\u0631\u062c \u0627\u0644\u0645\u0628\u064a\u0639\u0627\u062a \u0648\u0627\u0644\u062a\u062d\u0635\u064a\u0644".toString());
                            }
                            Cursor cursorRawQuery5 = writableDatabase.rawQuery("SELECT primary_cash_account_id FROM daily_cash_sessions WHERE operating_date=?", new String[]{strOperatingDate});
                            try {
                                Cursor cursor2 = cursorRawQuery5;
                                String string2 = cursor2.moveToFirst() ? cursor2.getString(0) : null;
                                CloseableKt.closeFinally(cursorRawQuery5, null);
                                String strTimestamp = timestamp();
                                if (string2 == null) {
                                    ContentValues contentValues = new ContentValues();
                                    contentValues.put("operating_date", strOperatingDate);
                                    contentValues.put("primary_cash_account_id", accountId);
                                    contentValues.put("created_by", actorUserId);
                                    contentValues.put("created_at", strTimestamp);
                                    writableDatabase.insertOrThrow("daily_cash_sessions", null, contentValues);
                                } else {
                                    ContentValues contentValues2 = new ContentValues();
                                    contentValues2.put("primary_cash_account_id", accountId);
                                    contentValues2.put("created_by", actorUserId);
                                    contentValues2.put("created_at", strTimestamp);
                                    if (writableDatabase.update("daily_cash_sessions", contentValues2, "operating_date=?", new String[]{strOperatingDate}) != 1) {
                                        throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062d\u062f\u064a\u062b \u062f\u0631\u062c \u0627\u0644\u064a\u0648\u0645 \u0627\u0644\u0631\u0626\u064a\u0633\u064a".toString());
                                    }
                                }
                                ContentValues contentValues3 = new ContentValues();
                                contentValues3.put("id", UUID.randomUUID().toString());
                                contentValues3.put("user_id", actorUserId);
                                contentValues3.put("action", "SET_DAILY_PRIMARY_DRAWER");
                                contentValues3.put("entity_type", "DAILY_CASH_SESSION");
                                contentValues3.put("entity_id", strOperatingDate);
                                if (string2 != null) {
                                    str = "{\"primary_cash_account_id\":\"" + string2 + "\"}";
                                } else {
                                    str = null;
                                }
                                contentValues3.put("before_json", str);
                                Intrinsics.checkNotNull(string);
                                contentValues3.put("after_json", "{\"primary_cash_account_id\":\"" + accountId + "\",\"account_name\":\"" + jsonEscape(string) + "\"}");
                                contentValues3.put("created_at", strTimestamp);
                                writableDatabase.insertOrThrow("audit_log", null, contentValues3);
                                setSystemRole(writableDatabase, "DAILY_DRAWER", accountId);
                                writableDatabase.setTransactionSuccessful();
                                writableDatabase.endTransaction();
                            } catch (Throwable th) {
                                try {
                                    throw th;
                                } catch (Throwable th2) {
                                    CloseableKt.closeFinally(cursorRawQuery5, th);
                                    throw th2;
                                }
                            }
                        } catch (Throwable th3) {
                            try {
                                throw th3;
                            } catch (Throwable th4) {
                                CloseableKt.closeFinally(cursorRawQuery4, th3);
                                throw th4;
                            }
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
        } catch (Throwable th11) {
            writableDatabase.endTransaction();
            throw th11;
        }
    }

    private final String operatingDate() {
        StoreTime storeTime = StoreTime.INSTANCE;
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        return StoreTime.today$default(storeTime, readableDatabase, null, 2, null);
    }

    private final String jsonEscape(String value) {
        return StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(value, "\\", "\\\\", false, 4, (Object) null), "\"", "\\\"", false, 4, (Object) null), "\n", "\\n", false, 4, (Object) null);
    }

    public final List<CashAccountSummary> listAll() {
        ensureSystemCashAccounts();
        return listAccounts(false);
    }

    private final List<CashAccountSummary> listAccounts(boolean activeOnly) throws IOException {
        String str;
        ArrayList arrayList = new ArrayList();
        Context appContext = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        int i = 2;
        boolean zIsConfiguredSecondary = new SyncProtocolRepository(appContext, null, 2, null).isConfiguredSecondary();
        if (zIsConfiguredSecondary && activeOnly) {
            str = "WHERE is_active=1 AND EXISTS (SELECT 1 FROM sync_protocol_state s WHERE s.state_key='cash_account_sales_allowed:' || cash_accounts.id AND s.state_value='1')";
        } else if (zIsConfiguredSecondary) {
            str = "WHERE EXISTS (SELECT 1 FROM sync_protocol_state s WHERE s.state_key='cash_account_sales_allowed:' || cash_accounts.id)";
        } else if (activeOnly) {
            str = "WHERE is_active=1";
        } else {
            str = "";
        }
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery(StringsKt.trimIndent("SELECT id,name,account_type,currency_code,opening_balance_minor,is_active\n               FROM cash_accounts " + str + " ORDER BY name COLLATE NOCASE"), (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            while (cursor.moveToNext()) {
                boolean z = false;
                String string = cursor.getString(0);
                Intrinsics.checkNotNull(string);
                String string2 = cursor.getString(1);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                String string3 = cursor.getString(i);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                String string4 = cursor.getString(3);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                long jBalance = balance(string, cursor.getLong(4));
                if (cursor.getInt(5) == 1) {
                    z = true;
                }
                arrayList.add(new CashAccountSummary(string, string2, string3, string4, jBalance, z));
                i = 2;
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

    public static /* synthetic */ CashAccountSummary create$default(CashAccountRepository cashAccountRepository, String str, String str2, String str3, String str4, long j, int i, Object obj) {
        if ((i & 16) != 0) {
            j = 0;
        }
        return cashAccountRepository.create(str, str2, str3, str4, j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final CashAccountSummary create(String actorUserId, String name, String accountType, String currencyCode, long openingBalanceMinor) {
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(accountType, "accountType");
        Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
        Context appContext = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        if (new SyncProtocolRepository(appContext, null, 2, null).isConfiguredSecondary()) {
            throw new IllegalArgumentException("\u0625\u0646\u0634\u0627\u0621 \u0627\u0644\u062d\u0633\u0627\u0628\u0627\u062a \u0627\u0644\u0645\u0627\u0644\u064a\u0629 \u0645\u062a\u0627\u062d \u0645\u0646 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0641\u0642\u0637 \u062d\u062a\u0649 \u062a\u0643\u062a\u0645\u0644 \u0645\u0632\u0627\u0645\u0646\u0629 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u062d\u0633\u0627\u0628\u0627\u062a".toString());
        }
        String string = StringsKt.trim((CharSequence) name).toString();
        String string2 = StringsKt.trim((CharSequence) currencyCode).toString();
        Locale US = Locale.US;
        Intrinsics.checkNotNullExpressionValue(US, "US");
        String upperCase = string2.toUpperCase(US);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        if (string.length() <= 0) {
            throw new IllegalArgumentException("\u0627\u0633\u0645 \u0627\u0644\u062d\u0633\u0627\u0628 \u0645\u0637\u0644\u0648\u0628".toString());
        }
        if (!SetsKt.setOf((Object[]) new String[]{"CASH_BOX", "WALLET", "BANK", "OTHER"}).contains(accountType)) {
            throw new IllegalArgumentException("\u0646\u0648\u0639 \u0627\u0644\u062d\u0633\u0627\u0628 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
        }
        if (!SetsKt.setOf((Object[]) new String[]{"USD", "SYP"}).contains(upperCase)) {
            throw new IllegalArgumentException("\u0627\u0644\u0639\u0645\u0644\u0629 \u064a\u062c\u0628 \u0623\u0646 \u062a\u0643\u0648\u0646 USD \u0623\u0648 SYP".toString());
        }
        if (openingBalanceMinor < 0) {
            throw new IllegalArgumentException("\u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0627\u0641\u062a\u062a\u0627\u062d\u064a \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0633\u0627\u0644\u0628\u0627\u064b".toString());
        }
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        requireManager(writableDatabase, actorUserId);
        String string3 = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
        String strTimestamp = timestamp();
        writableDatabase.beginTransaction();
        try {
            requireManager(writableDatabase, actorUserId);
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", string3);
            contentValues.put("name", string);
            contentValues.put("account_type", accountType);
            contentValues.put("currency_code", upperCase);
            contentValues.put("opening_balance_minor", Long.valueOf(openingBalanceMinor));
            contentValues.put("is_active", (Integer) 1);
            contentValues.put("created_at", strTimestamp);
            writableDatabase.insertOrThrow("cash_accounts", null, contentValues);
            String str = "{\"name\":\"" + jsonEscape(string) + "\",\"account_type\":\"" + accountType + "\",\"currency_code\":\"" + upperCase + "\",\"opening_balance_minor\":" + openingBalanceMinor + "}";
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("id", UUID.randomUUID().toString());
            contentValues2.put("user_id", actorUserId);
            contentValues2.put("action", "CREATE");
            contentValues2.put("entity_type", "CASH_ACCOUNT");
            contentValues2.put("entity_id", string3);
            contentValues2.put("after_json", str);
            contentValues2.put("created_at", strTimestamp);
            writableDatabase.insertOrThrow("audit_log", null, contentValues2);
            Context appContext2 = this.appContext;
            Intrinsics.checkNotNullExpressionValue(appContext2, "appContext");
            new SyncProtocolRepository(appContext2, null, 2, null).enqueueCashAccountReference(writableDatabase, actorUserId, string3);
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            try {
                Result.Companion companion = Result.INSTANCE;
                CashAccountRepository cashAccountRepository = this;
                SyncRelaySession.INSTANCE.flushPending();
                Result.m381constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m381constructorimpl(ResultKt.createFailure(th));
            }
            return new CashAccountSummary(string3, string, accountType, upperCase, openingBalanceMinor, true);
        } catch (Throwable th2) {
            writableDatabase.endTransaction();
            throw th2;
        }
    }

    public final boolean hasTransactions(String accountId) {
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery("SELECT 1 FROM cash_transactions WHERE account_id=?\n               UNION ALL\n               SELECT 1 FROM cash_accounts WHERE id=? AND opening_balance_minor<>0\n               UNION ALL\n               SELECT 1 FROM service_wallet_account_links WHERE account_id=?\n               UNION ALL\n               SELECT 1 FROM system_cash_account_roles WHERE account_id=?\n               LIMIT 1", new String[]{accountId, accountId, accountId, accountId});
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

    /* JADX WARN: Code duplicated, block: B:36:0x0138 A[Catch: all -> 0x02d9, TRY_LEAVE, TryCatch #6 {all -> 0x02d9, blocks: (B:15:0x00c7, B:20:0x00f1, B:23:0x0108, B:25:0x010d, B:34:0x0133, B:36:0x0138, B:39:0x014c, B:41:0x0151, B:44:0x0165, B:72:0x019a, B:74:0x01bf, B:81:0x02a2, B:82:0x02ad, B:52:0x0170, B:53:0x0173, B:59:0x0179, B:60:0x017c, B:66:0x0182, B:67:0x0185, B:68:0x0186, B:70:0x0190, B:83:0x02ae, B:84:0x02ba, B:90:0x02c0, B:91:0x02c3, B:99:0x02d5, B:100:0x02d8, B:63:0x017f, B:16:0x00d3, B:18:0x00dc, B:92:0x02c4, B:93:0x02cf, B:96:0x02d2, B:37:0x0144, B:56:0x0176, B:42:0x015d, B:21:0x0100, B:26:0x0119, B:28:0x0122, B:87:0x02bd, B:49:0x016d), top: B:127:0x00c7, inners: #0, #1, #2, #3, #4, #7, #8, #9, #10, #11 }] */
    /* JADX WARN: Code duplicated, block: B:41:0x0151 A[Catch: all -> 0x02d9, TRY_LEAVE, TryCatch #6 {all -> 0x02d9, blocks: (B:15:0x00c7, B:20:0x00f1, B:23:0x0108, B:25:0x010d, B:34:0x0133, B:36:0x0138, B:39:0x014c, B:41:0x0151, B:44:0x0165, B:72:0x019a, B:74:0x01bf, B:81:0x02a2, B:82:0x02ad, B:52:0x0170, B:53:0x0173, B:59:0x0179, B:60:0x017c, B:66:0x0182, B:67:0x0185, B:68:0x0186, B:70:0x0190, B:83:0x02ae, B:84:0x02ba, B:90:0x02c0, B:91:0x02c3, B:99:0x02d5, B:100:0x02d8, B:63:0x017f, B:16:0x00d3, B:18:0x00dc, B:92:0x02c4, B:93:0x02cf, B:96:0x02d2, B:37:0x0144, B:56:0x0176, B:42:0x015d, B:21:0x0100, B:26:0x0119, B:28:0x0122, B:87:0x02bd, B:49:0x016d), top: B:127:0x00c7, inners: #0, #1, #2, #3, #4, #7, #8, #9, #10, #11 }] */
    /* JADX WARN: Code duplicated, block: B:68:0x0186 A[Catch: all -> 0x02d9, TryCatch #6 {all -> 0x02d9, blocks: (B:15:0x00c7, B:20:0x00f1, B:23:0x0108, B:25:0x010d, B:34:0x0133, B:36:0x0138, B:39:0x014c, B:41:0x0151, B:44:0x0165, B:72:0x019a, B:74:0x01bf, B:81:0x02a2, B:82:0x02ad, B:52:0x0170, B:53:0x0173, B:59:0x0179, B:60:0x017c, B:66:0x0182, B:67:0x0185, B:68:0x0186, B:70:0x0190, B:83:0x02ae, B:84:0x02ba, B:90:0x02c0, B:91:0x02c3, B:99:0x02d5, B:100:0x02d8, B:63:0x017f, B:16:0x00d3, B:18:0x00dc, B:92:0x02c4, B:93:0x02cf, B:96:0x02d2, B:37:0x0144, B:56:0x0176, B:42:0x015d, B:21:0x0100, B:26:0x0119, B:28:0x0122, B:87:0x02bd, B:49:0x016d), top: B:127:0x00c7, inners: #0, #1, #2, #3, #4, #7, #8, #9, #10, #11 }] */
    /* JADX WARN: Code duplicated, block: B:70:0x0190 A[Catch: all -> 0x02d9, TryCatch #6 {all -> 0x02d9, blocks: (B:15:0x00c7, B:20:0x00f1, B:23:0x0108, B:25:0x010d, B:34:0x0133, B:36:0x0138, B:39:0x014c, B:41:0x0151, B:44:0x0165, B:72:0x019a, B:74:0x01bf, B:81:0x02a2, B:82:0x02ad, B:52:0x0170, B:53:0x0173, B:59:0x0179, B:60:0x017c, B:66:0x0182, B:67:0x0185, B:68:0x0186, B:70:0x0190, B:83:0x02ae, B:84:0x02ba, B:90:0x02c0, B:91:0x02c3, B:99:0x02d5, B:100:0x02d8, B:63:0x017f, B:16:0x00d3, B:18:0x00dc, B:92:0x02c4, B:93:0x02cf, B:96:0x02d2, B:37:0x0144, B:56:0x0176, B:42:0x015d, B:21:0x0100, B:26:0x0119, B:28:0x0122, B:87:0x02bd, B:49:0x016d), top: B:127:0x00c7, inners: #0, #1, #2, #3, #4, #7, #8, #9, #10, #11 }] */
    public final void updateDetails(String actorRole, String actorUserId, String accountId, String name, String accountType, String currencyCode) {
        boolean z;
        boolean z2;
        Throwable th;
        Cursor cursorRawQuery;
        boolean zMoveToFirst;
        Cursor cursorRawQuery2;
        boolean zMoveToFirst2;
        Intrinsics.checkNotNullParameter(actorRole, "actorRole");
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(accountType, "accountType");
        Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
        Context appContext = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        if (new SyncProtocolRepository(appContext, null, 2, null).isConfiguredSecondary()) {
            throw new IllegalArgumentException("\u062a\u0639\u062f\u064a\u0644 \u0627\u0644\u062d\u0633\u0627\u0628\u0627\u062a \u0627\u0644\u0645\u0627\u0644\u064a\u0629 \u0645\u062a\u0627\u062d \u0645\u0646 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0641\u0642\u0637 \u062d\u062a\u0649 \u062a\u0643\u062a\u0645\u0644 \u0645\u0632\u0627\u0645\u0646\u0629 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u062d\u0633\u0627\u0628\u0627\u062a".toString());
        }
        if (!Intrinsics.areEqual(actorRole, "ADMIN")) {
            throw new IllegalArgumentException("\u062a\u0639\u062f\u064a\u0644 \u0627\u0644\u062d\u0633\u0627\u0628\u0627\u062a \u0627\u0644\u0645\u0627\u0644\u064a\u0629 \u0645\u062a\u0627\u062d \u0644\u0644\u0645\u062f\u064a\u0631 \u0641\u0642\u0637".toString());
        }
        if (StringsKt.isBlank(actorUserId)) {
            throw new IllegalArgumentException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u062f\u064a\u0631 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
        }
        String string = StringsKt.trim((CharSequence) name).toString();
        String string2 = StringsKt.trim((CharSequence) currencyCode).toString();
        Locale US = Locale.US;
        Intrinsics.checkNotNullExpressionValue(US, "US");
        String upperCase = string2.toUpperCase(US);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        if (string.length() <= 0) {
            throw new IllegalArgumentException("\u0627\u0633\u0645 \u0627\u0644\u062d\u0633\u0627\u0628 \u0645\u0637\u0644\u0648\u0628".toString());
        }
        if (!SetsKt.setOf((Object[]) new String[]{"CASH_BOX", "WALLET", "BANK", "OTHER"}).contains(accountType)) {
            throw new IllegalArgumentException("\u0646\u0648\u0639 \u0627\u0644\u062d\u0633\u0627\u0628 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
        }
        if (!SetsKt.setOf((Object[]) new String[]{"USD", "SYP"}).contains(upperCase)) {
            throw new IllegalArgumentException("\u0627\u0644\u0639\u0645\u0644\u0629 \u064a\u062c\u0628 \u0623\u0646 \u062a\u0643\u0648\u0646 USD \u0623\u0648 SYP".toString());
        }
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        PermissionPolicy.INSTANCE.require(writableDatabase, actorUserId, PermissionPolicy.ACCOUNTS_MANAGE);
        writableDatabase.beginTransaction();
        try {
            Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT name,account_type,currency_code FROM cash_accounts WHERE id=?", new String[]{accountId});
            try {
                Cursor cursor = cursorRawQuery3;
                if (!cursor.moveToFirst()) {
                    throw new IllegalArgumentException("\u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
                }
                Triple triple = new Triple(cursor.getString(0), cursor.getString(1), cursor.getString(2));
                CloseableKt.closeFinally(cursorRawQuery3, null);
                Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT 1 FROM cash_transactions WHERE account_id=? LIMIT 1", new String[]{accountId});
                try {
                    boolean zMoveToFirst3 = cursorRawQuery4.moveToFirst();
                    CloseableKt.closeFinally(cursorRawQuery4, null);
                    if (!zMoveToFirst3) {
                        Cursor cursorRawQuery5 = writableDatabase.rawQuery("SELECT opening_balance_minor FROM cash_accounts WHERE id=?", new String[]{accountId});
                        try {
                            Cursor cursor2 = cursorRawQuery5;
                            if (cursor2.moveToFirst()) {
                                z = false;
                                if (cursor2.getLong(0) != 0) {
                                    th = null;
                                    z2 = true;
                                }
                                CloseableKt.closeFinally(cursorRawQuery5, th);
                                if (!z2) {
                                    cursorRawQuery = writableDatabase.rawQuery("SELECT 1 FROM service_wallet_account_links WHERE account_id=? LIMIT 1", new String[]{accountId});
                                    try {
                                        zMoveToFirst = cursorRawQuery.moveToFirst();
                                        CloseableKt.closeFinally(cursorRawQuery, null);
                                        if (!zMoveToFirst) {
                                            cursorRawQuery2 = writableDatabase.rawQuery("SELECT 1 FROM system_cash_account_roles WHERE account_id=? LIMIT 1", new String[]{accountId});
                                            try {
                                                zMoveToFirst2 = cursorRawQuery2.moveToFirst();
                                                CloseableKt.closeFinally(cursorRawQuery2, null);
                                                if (zMoveToFirst2) {
                                                    if (Intrinsics.areEqual(accountType, triple.getSecond()) || !Intrinsics.areEqual(upperCase, triple.getThird())) {
                                                        throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u063a\u064a\u064a\u0631 \u0646\u0648\u0639 \u0627\u0644\u062d\u0633\u0627\u0628 \u0623\u0648 \u0639\u0645\u0644\u062a\u0647 \u0628\u0639\u062f \u062a\u0633\u062c\u064a\u0644 \u062d\u0631\u0643\u0627\u062a \u0639\u0644\u064a\u0647\u061b \u064a\u0645\u0643\u0646 \u062a\u0639\u062f\u064a\u0644 \u0627\u0644\u0627\u0633\u0645 \u0641\u0642\u0637 \u062d\u0641\u0627\u0638\u0627\u064b \u0639\u0644\u0649 \u0633\u0644\u0627\u0645\u0629 \u0627\u0644\u0633\u062c\u0644\u0627\u062a".toString());
                                                    }
                                                }
                                            } catch (Throwable th2) {
                                                try {
                                                    throw th2;
                                                } catch (Throwable th3) {
                                                    CloseableKt.closeFinally(cursorRawQuery2, th2);
                                                    throw th3;
                                                }
                                            }
                                        } else {
                                            if (Intrinsics.areEqual(accountType, triple.getSecond())) {
                                            }
                                            throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u063a\u064a\u064a\u0631 \u0646\u0648\u0639 \u0627\u0644\u062d\u0633\u0627\u0628 \u0623\u0648 \u0639\u0645\u0644\u062a\u0647 \u0628\u0639\u062f \u062a\u0633\u062c\u064a\u0644 \u062d\u0631\u0643\u0627\u062a \u0639\u0644\u064a\u0647\u061b \u064a\u0645\u0643\u0646 \u062a\u0639\u062f\u064a\u0644 \u0627\u0644\u0627\u0633\u0645 \u0641\u0642\u0637 \u062d\u0641\u0627\u0638\u0627\u064b \u0639\u0644\u0649 \u0633\u0644\u0627\u0645\u0629 \u0627\u0644\u0633\u062c\u0644\u0627\u062a".toString());
                                        }
                                    } catch (Throwable th4) {
                                        try {
                                            throw th4;
                                        } catch (Throwable th5) {
                                            CloseableKt.closeFinally(cursorRawQuery, th4);
                                            throw th5;
                                        }
                                    }
                                } else {
                                    if (Intrinsics.areEqual(accountType, triple.getSecond())) {
                                    }
                                    throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u063a\u064a\u064a\u0631 \u0646\u0648\u0639 \u0627\u0644\u062d\u0633\u0627\u0628 \u0623\u0648 \u0639\u0645\u0644\u062a\u0647 \u0628\u0639\u062f \u062a\u0633\u062c\u064a\u0644 \u062d\u0631\u0643\u0627\u062a \u0639\u0644\u064a\u0647\u061b \u064a\u0645\u0643\u0646 \u062a\u0639\u062f\u064a\u0644 \u0627\u0644\u0627\u0633\u0645 \u0641\u0642\u0637 \u062d\u0641\u0627\u0638\u0627\u064b \u0639\u0644\u0649 \u0633\u0644\u0627\u0645\u0629 \u0627\u0644\u0633\u062c\u0644\u0627\u062a".toString());
                                }
                            } else {
                                z = false;
                            }
                            z2 = z;
                            th = null;
                            CloseableKt.closeFinally(cursorRawQuery5, th);
                            if (!z2) {
                                cursorRawQuery = writableDatabase.rawQuery("SELECT 1 FROM service_wallet_account_links WHERE account_id=? LIMIT 1", new String[]{accountId});
                                zMoveToFirst = cursorRawQuery.moveToFirst();
                                CloseableKt.closeFinally(cursorRawQuery, null);
                                if (!zMoveToFirst) {
                                    cursorRawQuery2 = writableDatabase.rawQuery("SELECT 1 FROM system_cash_account_roles WHERE account_id=? LIMIT 1", new String[]{accountId});
                                    zMoveToFirst2 = cursorRawQuery2.moveToFirst();
                                    CloseableKt.closeFinally(cursorRawQuery2, null);
                                    if (zMoveToFirst2) {
                                        if (Intrinsics.areEqual(accountType, triple.getSecond())) {
                                        }
                                        throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u063a\u064a\u064a\u0631 \u0646\u0648\u0639 \u0627\u0644\u062d\u0633\u0627\u0628 \u0623\u0648 \u0639\u0645\u0644\u062a\u0647 \u0628\u0639\u062f \u062a\u0633\u062c\u064a\u0644 \u062d\u0631\u0643\u0627\u062a \u0639\u0644\u064a\u0647\u061b \u064a\u0645\u0643\u0646 \u062a\u0639\u062f\u064a\u0644 \u0627\u0644\u0627\u0633\u0645 \u0641\u0642\u0637 \u062d\u0641\u0627\u0638\u0627\u064b \u0639\u0644\u0649 \u0633\u0644\u0627\u0645\u0629 \u0627\u0644\u0633\u062c\u0644\u0627\u062a".toString());
                                    }
                                } else {
                                    if (Intrinsics.areEqual(accountType, triple.getSecond())) {
                                    }
                                    throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u063a\u064a\u064a\u0631 \u0646\u0648\u0639 \u0627\u0644\u062d\u0633\u0627\u0628 \u0623\u0648 \u0639\u0645\u0644\u062a\u0647 \u0628\u0639\u062f \u062a\u0633\u062c\u064a\u0644 \u062d\u0631\u0643\u0627\u062a \u0639\u0644\u064a\u0647\u061b \u064a\u0645\u0643\u0646 \u062a\u0639\u062f\u064a\u0644 \u0627\u0644\u0627\u0633\u0645 \u0641\u0642\u0637 \u062d\u0641\u0627\u0638\u0627\u064b \u0639\u0644\u0649 \u0633\u0644\u0627\u0645\u0629 \u0627\u0644\u0633\u062c\u0644\u0627\u062a".toString());
                                }
                            } else {
                                if (Intrinsics.areEqual(accountType, triple.getSecond())) {
                                }
                                throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u063a\u064a\u064a\u0631 \u0646\u0648\u0639 \u0627\u0644\u062d\u0633\u0627\u0628 \u0623\u0648 \u0639\u0645\u0644\u062a\u0647 \u0628\u0639\u062f \u062a\u0633\u062c\u064a\u0644 \u062d\u0631\u0643\u0627\u062a \u0639\u0644\u064a\u0647\u061b \u064a\u0645\u0643\u0646 \u062a\u0639\u062f\u064a\u0644 \u0627\u0644\u0627\u0633\u0645 \u0641\u0642\u0637 \u062d\u0641\u0627\u0638\u0627\u064b \u0639\u0644\u0649 \u0633\u0644\u0627\u0645\u0629 \u0627\u0644\u0633\u062c\u0644\u0627\u062a".toString());
                            }
                        } catch (Throwable th6) {
                            try {
                                throw th6;
                            } catch (Throwable th7) {
                                CloseableKt.closeFinally(cursorRawQuery5, th6);
                                throw th7;
                            }
                        }
                    } else {
                        if (Intrinsics.areEqual(accountType, triple.getSecond())) {
                        }
                        throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u063a\u064a\u064a\u0631 \u0646\u0648\u0639 \u0627\u0644\u062d\u0633\u0627\u0628 \u0623\u0648 \u0639\u0645\u0644\u062a\u0647 \u0628\u0639\u062f \u062a\u0633\u062c\u064a\u0644 \u062d\u0631\u0643\u0627\u062a \u0639\u0644\u064a\u0647\u061b \u064a\u0645\u0643\u0646 \u062a\u0639\u062f\u064a\u0644 \u0627\u0644\u0627\u0633\u0645 \u0641\u0642\u0637 \u062d\u0641\u0627\u0638\u0627\u064b \u0639\u0644\u0649 \u0633\u0644\u0627\u0645\u0629 \u0627\u0644\u0633\u062c\u0644\u0627\u062a".toString());
                    }
                    String strTimestamp = timestamp();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("name", string);
                    contentValues.put("account_type", accountType);
                    contentValues.put("currency_code", upperCase);
                    if (writableDatabase.update("cash_accounts", contentValues, "id=?", new String[]{accountId}) != 1) {
                        throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u0639\u062f\u064a\u0644 \u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a".toString());
                    }
                    Object first = triple.getFirst();
                    Intrinsics.checkNotNullExpressionValue(first, "<get-first>(...)");
                    String strJsonString = jsonString((String) first);
                    Object second = triple.getSecond();
                    Intrinsics.checkNotNullExpressionValue(second, "<get-second>(...)");
                    String strJsonString2 = jsonString((String) second);
                    Object third = triple.getThird();
                    Intrinsics.checkNotNullExpressionValue(third, "<get-third>(...)");
                    String str = "{\"name\":" + strJsonString + ",\"type\":" + strJsonString2 + ",\"currency\":" + jsonString((String) third) + "}";
                    String str2 = "{\"name\":" + jsonString(string) + ",\"type\":" + jsonString(accountType) + ",\"currency\":" + jsonString(upperCase) + "}";
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("id", UUID.randomUUID().toString());
                    contentValues2.put("user_id", actorUserId);
                    contentValues2.put("action", "UPDATE");
                    contentValues2.put("entity_type", "CASH_ACCOUNT");
                    contentValues2.put("entity_id", accountId);
                    contentValues2.put("before_json", str);
                    contentValues2.put("after_json", str2);
                    contentValues2.put("created_at", strTimestamp);
                    writableDatabase.insertOrThrow("audit_log", null, contentValues2);
                    Context appContext2 = this.appContext;
                    Intrinsics.checkNotNullExpressionValue(appContext2, "appContext");
                    new SyncProtocolRepository(appContext2, null, 2, null).enqueueCashAccountReference(writableDatabase, actorUserId, accountId);
                    writableDatabase.setTransactionSuccessful();
                    writableDatabase.endTransaction();
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        CashAccountRepository cashAccountRepository = this;
                        SyncRelaySession.INSTANCE.flushPending();
                        Result.m381constructorimpl(Unit.INSTANCE);
                    } catch (Throwable th8) {
                        Result.Companion companion2 = Result.INSTANCE;
                        Result.m381constructorimpl(ResultKt.createFailure(th8));
                    }
                } catch (Throwable th9) {
                    try {
                        throw th9;
                    } catch (Throwable th10) {
                        CloseableKt.closeFinally(cursorRawQuery4, th9);
                        throw th10;
                    }
                }
            } catch (Throwable th11) {
                try {
                    throw th11;
                } catch (Throwable th12) {
                    CloseableKt.closeFinally(cursorRawQuery3, th11);
                    throw th12;
                }
            }
        } catch (Throwable th13) {
            writableDatabase.endTransaction();
            throw th13;
        }
    }

    private final void requireManager(SQLiteDatabase db, String actorUserId) {
        PermissionPolicy.INSTANCE.require(db, actorUserId, PermissionPolicy.ACCOUNTS_MANAGE);
    }

    private final String jsonString(String value) {
        return "\"" + StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(value, "\\", "\\\\", false, 4, (Object) null), "\"", "\\\"", false, 4, (Object) null), "\n", "\\n", false, 4, (Object) null), "\r", "\\r", false, 4, (Object) null) + "\"";
    }

    public final long balance(String accountId) throws IOException {
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery("SELECT opening_balance_minor FROM cash_accounts WHERE id=?", new String[]{accountId});
        try {
            Cursor cursor = cursorRawQuery;
            if (!cursor.moveToFirst()) {
                throw new IllegalArgumentException("\u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
            }
            long j = cursor.getLong(0);
            CloseableKt.closeFinally(cursorRawQuery, null);
            return balance(accountId, j);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final long balance(String accountId, long opening) throws IOException {
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery("SELECT COALESCE(SUM(CASE WHEN direction='IN' THEN amount_minor\n                       WHEN direction='OUT' THEN -amount_minor ELSE 0 END),0)\n               FROM cash_transactions WHERE account_id=?", new String[]{accountId});
        try {
            Cursor cursor = cursorRawQuery;
            long j = cursor.moveToFirst() ? cursor.getLong(0) : 0L;
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

    private final String timestamp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String str = simpleDateFormat.format(new Date());
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }
}
