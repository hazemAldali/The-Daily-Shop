package com.hazem.dailystore.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import androidx.core.app.NotificationCompat;
import com.google.android.material.card.MaterialCardViewHelper;
import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import net.zetetic.database.sqlcipher.SQLiteDatabase;
import org.json.JSONObject;

/* JADX INFO: compiled from: OperatingExpenseRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\b\u0002\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013J\u001e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0010JN\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00132\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0013J.\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\r2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010&\u001a\u00020'J\u001e\u0010(\u001a\u00020\u001e2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0013J$\u0010)\u001a\u00020\u001a2\n\u0010*\u001a\u00060+j\u0002`,2\u0006\u0010-\u001a\u00020\u00132\u0006\u0010.\u001a\u00020\u0010H\u0002J\u0014\u0010/\u001a\u00020\u001e2\n\u0010*\u001a\u00060+j\u0002`,H\u0002J$\u00100\u001a\u00020\u001e2\n\u0010*\u001a\u00060+j\u0002`,2\u0006\u00101\u001a\u00020\u00132\u0006\u00102\u001a\u00020\u001eH\u0002J\u0010\u00103\u001a\u00020\u00132\u0006\u00104\u001a\u00020\u0013H\u0002J\b\u00105\u001a\u00020\u0013H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0018\u0010\u0002\u001a\n \b*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00066"}, d2 = {"Lcom/hazem/dailystore/data/OperatingExpenseRepository;", "", "context", "Landroid/content/Context;", "credentialVault", "Lcom/hazem/dailystore/data/SyncCredentialVault;", "<init>", "(Landroid/content/Context;Lcom/hazem/dailystore/data/SyncCredentialVault;)V", "kotlin.jvm.PlatformType", "Landroid/content/Context;", "database", "Lcom/hazem/dailystore/data/AppDatabase;", "listCategories", "", "Lcom/hazem/dailystore/data/ExpenseCategory;", "activeOnly", "", "createCategory", "actorUserId", "", "name", "setCategoryActive", "", "categoryId", "active", "postExpense", "Lcom/hazem/dailystore/data/OperatingExpenseResult;", "operationId", "cashAccountId", "amountMinor", "", "expenseDate", "reference", "notes", "listExpenses", "Lcom/hazem/dailystore/data/OperatingExpense;", "fromDate", "throughDate", "limit", "", "totalUsdCents", "loadResult", "db", "Lnet/zetetic/database/sqlcipher/SQLiteDatabase;", "Lcom/hazem/dailystore/data/AppSqliteDatabase;", "id", "replayed", "currentRate", "cashBalance", "accountId", "opening", "hash", "value", "timestamp", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class OperatingExpenseRepository {
    private final Context context;
    private final SyncCredentialVault credentialVault;
    private final AppDatabase database;

    public OperatingExpenseRepository(Context context, SyncCredentialVault credentialVault) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(credentialVault, "credentialVault");
        this.credentialVault = credentialVault;
        this.context = context.getApplicationContext();
        this.database = AppDatabase.INSTANCE.getInstance(context);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ OperatingExpenseRepository(Context context, SyncCredentialVault syncCredentialVault, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? new SyncCredentialVault(context, null, 2, 0 == true ? 1 : 0) : syncCredentialVault);
    }

    public static /* synthetic */ List listCategories$default(OperatingExpenseRepository operatingExpenseRepository, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return operatingExpenseRepository.listCategories(z);
    }

    public final List<ExpenseCategory> listCategories(boolean activeOnly) throws IOException {
        ArrayList arrayList = new ArrayList();
        String str = activeOnly ? "WHERE is_active=1" : "";
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery("SELECT id,name,is_active FROM expense_categories " + str + " ORDER BY name COLLATE NOCASE", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            while (cursor.moveToNext()) {
                boolean z = false;
                String string = cursor.getString(0);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String string2 = cursor.getString(1);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                if (cursor.getInt(2) == 1) {
                    z = true;
                }
                arrayList.add(new ExpenseCategory(string, string2, z));
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

    public final ExpenseCategory createCategory(String actorUserId, String name) {
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(name, "name");
        String string = StringsKt.trim((CharSequence) name).toString();
        if (string.length() <= 0 || string.length() > 80) {
            throw new IllegalArgumentException("\u0627\u0633\u0645 \u062a\u0635\u0646\u064a\u0641 \u0627\u0644\u0645\u0635\u0631\u0648\u0641 \u0645\u0637\u0644\u0648\u0628 \u0648\u0628\u062d\u062f \u0623\u0642\u0635\u0649 80 \u062d\u0631\u0641\u0627\u064b".toString());
        }
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        writableDatabase.beginTransaction();
        try {
            PermissionPolicy.INSTANCE.require(writableDatabase, actorUserId, PermissionPolicy.EXPENSES_MANAGE);
            String string2 = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
            String strTimestamp = timestamp();
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", string2);
            contentValues.put("name", string);
            contentValues.put("is_active", (Integer) 1);
            contentValues.put("created_by", actorUserId);
            contentValues.put("created_at", strTimestamp);
            Unit unit = Unit.INSTANCE;
            writableDatabase.insertOrThrow("expense_categories", null, contentValues);
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("id", UUID.randomUUID().toString());
            contentValues2.put("user_id", actorUserId);
            contentValues2.put("action", "EXPENSE_CATEGORY_CREATED");
            contentValues2.put("entity_type", "EXPENSE_CATEGORY");
            contentValues2.put("entity_id", string2);
            contentValues2.put("after_json", new JSONObject().put("name", string).toString());
            contentValues2.put("created_at", strTimestamp);
            Unit unit2 = Unit.INSTANCE;
            writableDatabase.insertOrThrow("audit_log", null, contentValues2);
            writableDatabase.setTransactionSuccessful();
            return new ExpenseCategory(string2, string, true);
        } finally {
            writableDatabase.endTransaction();
        }
    }

    public final void setCategoryActive(String actorUserId, String categoryId, boolean active) {
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(categoryId, "categoryId");
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        writableDatabase.beginTransaction();
        try {
            PermissionPolicy.INSTANCE.require(writableDatabase, actorUserId, PermissionPolicy.EXPENSES_MANAGE);
            ContentValues contentValues = new ContentValues();
            contentValues.put("is_active", Integer.valueOf(active ? 1 : 0));
            Unit unit = Unit.INSTANCE;
            if (writableDatabase.update("expense_categories", contentValues, "id=?", new String[]{categoryId}) != 1) {
                throw new IllegalArgumentException("\u062a\u0635\u0646\u064a\u0641 \u0627\u0644\u0645\u0635\u0631\u0648\u0641 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
            }
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("id", UUID.randomUUID().toString());
            contentValues2.put("user_id", actorUserId);
            contentValues2.put("action", active ? "EXPENSE_CATEGORY_ACTIVATED" : "EXPENSE_CATEGORY_DEACTIVATED");
            contentValues2.put("entity_type", "EXPENSE_CATEGORY");
            contentValues2.put("entity_id", categoryId);
            contentValues2.put("after_json", new JSONObject().put("is_active", active).toString());
            contentValues2.put("created_at", timestamp());
            Unit unit2 = Unit.INSTANCE;
            writableDatabase.insertOrThrow("audit_log", null, contentValues2);
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
        } catch (Throwable th) {
            writableDatabase.endTransaction();
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:107:0x02ad  */
    /* JADX WARN: Code duplicated, block: B:121:0x02dd A[Catch: all -> 0x08f7, TRY_ENTER, TRY_LEAVE, TryCatch #14 {all -> 0x08f7, blocks: (B:37:0x0115, B:43:0x012d, B:72:0x020d, B:73:0x0219, B:89:0x0269, B:91:0x0271, B:99:0x0290, B:101:0x0296, B:109:0x02b1, B:111:0x02b7, B:121:0x02dd, B:277:0x0860, B:283:0x087d, B:284:0x0894, B:285:0x0895, B:286:0x08a2, B:292:0x08a9, B:293:0x08ac, B:305:0x08c2, B:306:0x08c5, B:307:0x08c6, B:308:0x08d2, B:314:0x08d9, B:315:0x08dc, B:321:0x08e3, B:322:0x08e6, B:332:0x08f3, B:333:0x08f6, B:318:0x08e0, B:227:0x0563, B:289:0x08a6, B:38:0x011d, B:40:0x0127, B:329:0x08f0, B:302:0x08bf, B:215:0x0508, B:311:0x08d6), top: B:369:0x0115, inners: #0, #1, #3, #9, #10, #17, #22, #25 }] */
    /* JADX WARN: Code duplicated, block: B:124:0x02eb  */
    /* JADX WARN: Code duplicated, block: B:125:0x02ee A[Catch: all -> 0x0434, TryCatch #12 {all -> 0x0434, blocks: (B:46:0x0166, B:49:0x0181, B:51:0x0186, B:58:0x01b6, B:60:0x01cf, B:62:0x01e1, B:65:0x01f5, B:67:0x01fa, B:74:0x021a, B:78:0x022a, B:87:0x0264, B:118:0x02d3, B:122:0x02e1, B:126:0x02f7, B:125:0x02ee, B:97:0x028a, B:77:0x0225), top: B:365:0x0166 }] */
    /* JADX WARN: Code duplicated, block: B:130:0x0378 A[Catch: all -> 0x08ea, TryCatch #6 {all -> 0x08ea, blocks: (B:128:0x030b, B:130:0x0378, B:132:0x037d, B:134:0x0385, B:136:0x038a, B:144:0x03d3, B:145:0x03d6, B:146:0x03d7, B:147:0x03e5, B:153:0x03ed, B:154:0x03f0, B:155:0x03f1, B:156:0x03ff, B:168:0x0417, B:169:0x041a, B:170:0x041b, B:171:0x0428, B:177:0x0430, B:178:0x0433, B:181:0x0439, B:63:0x01ed, B:47:0x0179, B:150:0x03ea, B:174:0x042d, B:141:0x03d0, B:165:0x0414), top: B:354:0x0164, inners: #2, #5, #7, #8, #21, #23 }] */
    /* JADX WARN: Code duplicated, block: B:131:0x037b  */
    /* JADX WARN: Code duplicated, block: B:134:0x0385 A[Catch: all -> 0x08ea, TryCatch #6 {all -> 0x08ea, blocks: (B:128:0x030b, B:130:0x0378, B:132:0x037d, B:134:0x0385, B:136:0x038a, B:144:0x03d3, B:145:0x03d6, B:146:0x03d7, B:147:0x03e5, B:153:0x03ed, B:154:0x03f0, B:155:0x03f1, B:156:0x03ff, B:168:0x0417, B:169:0x041a, B:170:0x041b, B:171:0x0428, B:177:0x0430, B:178:0x0433, B:181:0x0439, B:63:0x01ed, B:47:0x0179, B:150:0x03ea, B:174:0x042d, B:141:0x03d0, B:165:0x0414), top: B:354:0x0164, inners: #2, #5, #7, #8, #21, #23 }] */
    /* JADX WARN: Code duplicated, block: B:135:0x0388  */
    /* JADX WARN: Code duplicated, block: B:97:0x028a A[Catch: all -> 0x0434, TRY_ENTER, TRY_LEAVE, TryCatch #12 {all -> 0x0434, blocks: (B:46:0x0166, B:49:0x0181, B:51:0x0186, B:58:0x01b6, B:60:0x01cf, B:62:0x01e1, B:65:0x01f5, B:67:0x01fa, B:74:0x021a, B:78:0x022a, B:87:0x0264, B:118:0x02d3, B:122:0x02e1, B:126:0x02f7, B:125:0x02ee, B:97:0x028a, B:77:0x0225), top: B:365:0x0166 }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v10, types: [com.hazem.dailystore.data.PermissionPolicy] */
    /* JADX WARN: Type inference failed for: r11v14 */
    /* JADX WARN: Type inference failed for: r11v2, types: [java.lang.Object, net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r11v25, types: [com.hazem.dailystore.data.OperatingExpenseRepository] */
    /* JADX WARN: Type inference failed for: r11v26 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v31 */
    /* JADX WARN: Type inference failed for: r11v4, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r11v41 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r1v58, types: [com.hazem.dailystore.data.SyncProtocolRepository] */
    /* JADX WARN: Type inference failed for: r24v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r24v1 */
    /* JADX WARN: Type inference failed for: r24v3 */
    /* JADX WARN: Type inference failed for: r24v9 */
    /* JADX WARN: Type inference failed for: r2v24, types: [com.hazem.dailystore.data.SyncProtocolRepository] */
    /* JADX WARN: Type inference failed for: r2v44, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r2v63 */
    /* JADX WARN: Type inference failed for: r2v64 */
    /* JADX WARN: Type inference failed for: r39v0, types: [com.hazem.dailystore.data.OperatingExpenseRepository] */
    /* JADX WARN: Type inference failed for: r4v7, types: [com.hazem.dailystore.data.StoreTime] */
    /* JADX WARN: Type inference failed for: r7v10, types: [com.hazem.dailystore.data.PermissionPolicy] */
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
    public final OperatingExpenseResult postExpense(String actorUserId, String operationId, String categoryId, String cashAccountId, long amountMinor, String expenseDate, String reference, String notes) throws Throwable {
        String string;
        Throwable th;
        JSONObject jSONObject;
        String strTimestamp;
        String str;
        String str2;
        String strOperatingDate;
        long jSypToUsdCents;
        Object obj;
        Object obj2;
        String strOptString;
        String strOptString2;
        String strOptString3;
        Throwable th2;
        Triple triple;
        Throwable th3;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String string2;
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        Intrinsics.checkNotNullParameter(categoryId, "categoryId");
        Intrinsics.checkNotNullParameter(cashAccountId, "cashAccountId");
        Intrinsics.checkNotNullParameter(expenseDate, "expenseDate");
        if (StringsKt.isBlank(actorUserId) || StringsKt.isBlank(operationId) || StringsKt.isBlank(categoryId) || StringsKt.isBlank(cashAccountId)) {
            throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0645\u0635\u0631\u0648\u0641 \u063a\u064a\u0631 \u0645\u0643\u062a\u0645\u0644\u0629".toString());
        }
        if (amountMinor <= 0) {
            throw new IllegalArgumentException("\u0642\u064a\u0645\u0629 \u0627\u0644\u0645\u0635\u0631\u0648\u0641 \u064a\u062c\u0628 \u0623\u0646 \u062a\u0643\u0648\u0646 \u0623\u0643\u0628\u0631 \u0645\u0646 \u0635\u0641\u0631".toString());
        }
        if (!new Regex("\\d{4}-\\d{2}-\\d{2}").matches(expenseDate)) {
            throw new IllegalArgumentException("\u062a\u0627\u0631\u064a\u062e \u0627\u0644\u0645\u0635\u0631\u0648\u0641 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
        }
        if (reference == null || (string = StringsKt.trim((CharSequence) reference).toString()) == null || string.length() <= 0) {
            string = null;
        }
        String str10 = (notes == null || (string2 = StringsKt.trim((CharSequence) notes).toString()) == null || string2.length() <= 0) ? null : string2;
        String str11 = string;
        String str12 = str10;
        ?? writableDatabase = "pending-expense-";
        String strHash = hash(CollectionsKt.joinToString$default(CollectionsKt.listOf((Object[]) new String[]{categoryId, cashAccountId, String.valueOf(amountMinor), expenseDate, string == null ? "" : string, str10 == null ? "" : str10}), "|", null, null, 0, null, null, 62, null));
        writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        writableDatabase.beginTransaction();
        try {
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", null);
            try {
                Cursor cursor = cursorRawQuery;
                String string3 = cursor.moveToFirst() ? cursor.getString(0) : null;
                CloseableKt.closeFinally(cursorRawQuery, null);
                try {
                    if (Intrinsics.areEqual(string3, "SECONDARY")) {
                        try {
                            PermissionPolicy.INSTANCE.requireAssigned(writableDatabase, actorUserId, PermissionPolicy.EXPENSES_MANAGE);
                            Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT 1 FROM expense_categories WHERE id=? AND is_active=1", new String[]{categoryId});
                            try {
                                boolean zMoveToFirst = cursorRawQuery2.moveToFirst();
                                CloseableKt.closeFinally(cursorRawQuery2, null);
                                if (!zMoveToFirst) {
                                    throw new IllegalArgumentException("\u062a\u0635\u0646\u064a\u0641 \u0627\u0644\u0645\u0635\u0631\u0648\u0641 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0623\u0648 \u0645\u0648\u0642\u0648\u0641 \u0639\u0644\u0649 \u0647\u0630\u0627 \u0627\u0644\u062c\u0647\u0627\u0632".toString());
                                }
                                Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT account_type,currency_code,is_active FROM cash_accounts WHERE id=?", new String[]{cashAccountId});
                                try {
                                    Cursor cursor2 = cursorRawQuery3;
                                    if (cursor2.moveToFirst() && cursor2.getInt(2) == 1) {
                                        Pair pair = TuplesKt.to(cursor2.getString(0), cursor2.getString(1));
                                        CloseableKt.closeFinally(cursorRawQuery3, null);
                                        if (!SetsKt.setOf((Object[]) new String[]{"CASH_BOX", "WALLET"}).contains(pair.getFirst()) || !SetsKt.setOf((Object[]) new String[]{"USD", "SYP"}).contains(pair.getSecond())) {
                                            throw new IllegalArgumentException("\u0646\u0648\u0639 \u0623\u0648 \u0639\u0645\u0644\u0629 \u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0635\u0631\u0648\u0641 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u064a\u0646".toString());
                                        }
                                        Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT 1 FROM system_cash_account_roles WHERE role_key='SUPPLIER_ROLLOVER' AND account_id=?", new String[]{cashAccountId});
                                        try {
                                            boolean zMoveToFirst2 = cursorRawQuery4.moveToFirst();
                                            CloseableKt.closeFinally(cursorRawQuery4, null);
                                            if (zMoveToFirst2) {
                                                throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u0633\u062c\u064a\u0644 \u0645\u0635\u0631\u0648\u0641 \u0645\u0646 \u0645\u062e\u0635\u0635 \u062f\u0641\u0639\u0627\u062a \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0627\u0644\u064a\u0648\u0645\u064a".toString());
                                            }
                                            long jCurrentRate = currentRate(writableDatabase);
                                            if (Intrinsics.areEqual(pair.getSecond(), "SYP") && jCurrentRate <= 0) {
                                                throw new IllegalArgumentException("\u064a\u062c\u0628 \u062a\u062d\u062f\u064a\u062f \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0642\u0628\u0644 \u062a\u0633\u062c\u064a\u0644 \u0645\u0635\u0631\u0648\u0641 \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629".toString());
                                            }
                                            if (!Intrinsics.areEqual(pair.getSecond(), "USD")) {
                                                CurrencyConverter.INSTANCE.sypToUsdCents(amountMinor, jCurrentRate);
                                            }
                                            Context context = this.context;
                                            Intrinsics.checkNotNullExpressionValue(context, "context");
                                            ?? syncProtocolRepository = new SyncProtocolRepository(context, this.credentialVault);
                                            Cursor cursorRawQuery5 = writableDatabase.rawQuery("SELECT payload_json FROM sync_operation_envelopes WHERE operation_id=?", new String[]{operationId});
                                            try {
                                                Cursor cursor3 = cursorRawQuery5;
                                                if (cursor3.moveToFirst()) {
                                                    try {
                                                        jSONObject = new JSONObject(cursor3.getString(0));
                                                    } catch (Throwable th4) {
                                                        th = th4;
                                                        try {
                                                            throw th;
                                                        } catch (Throwable th5) {
                                                            CloseableKt.closeFinally(cursorRawQuery5, th);
                                                            throw th5;
                                                        }
                                                    }
                                                } else {
                                                    jSONObject = null;
                                                }
                                                CloseableKt.closeFinally(cursorRawQuery5, null);
                                                if (jSONObject == null || (strOptString3 = jSONObject.optString("occurred_at")) == null) {
                                                    strTimestamp = timestamp();
                                                } else {
                                                    strTimestamp = new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(strOptString3) ? strOptString3 : null;
                                                    if (strTimestamp == null) {
                                                        strTimestamp = timestamp();
                                                    }
                                                }
                                                if (jSONObject == null || (strOptString2 = jSONObject.optString("expense_date")) == null) {
                                                    str = expenseDate;
                                                } else {
                                                    str = new Regex("\\d{4}-\\d{2}-\\d{2}").matches(strOptString2) ? strOptString2 : null;
                                                    if (str == null) {
                                                        str = expenseDate;
                                                    }
                                                }
                                                if (jSONObject != null && (strOptString = jSONObject.optString("operating_date")) != null) {
                                                    str2 = "operating_date";
                                                    strOperatingDate = new Regex("\\d{4}-\\d{2}-\\d{2}").matches(strOptString) ? strOptString : null;
                                                    if (strOperatingDate == null) {
                                                    }
                                                    String str13 = strOperatingDate;
                                                    if (jSONObject != null) {
                                                        jCurrentRate = jSONObject.optLong("exchange_rate_syp_per_usd_milli", jCurrentRate);
                                                    }
                                                    if (Intrinsics.areEqual(pair.getSecond(), "USD")) {
                                                        jSypToUsdCents = amountMinor;
                                                    } else {
                                                        jSypToUsdCents = CurrencyConverter.INSTANCE.sypToUsdCents(amountMinor, jCurrentRate);
                                                    }
                                                    String str14 = ((String) writableDatabase) + operationId;
                                                    String str15 = "pending-cash-" + operationId;
                                                    JSONObject jSONObjectPut = new JSONObject().put("expense_id", str14).put("cash_transaction_id", str15).put("category_id", categoryId).put("cash_account_id", cashAccountId).put("cash_account_type", pair.getFirst()).put("cash_account_currency_code", pair.getSecond()).put("amount_minor", amountMinor).put("currency_code", pair.getSecond()).put("exchange_rate_syp_per_usd_milli", jCurrentRate).put("amount_usd_cents", jSypToUsdCents).put("expense_date", str).put(str2, str13).put("occurred_at", strTimestamp);
                                                    if (str11 == null) {
                                                        obj = JSONObject.NULL;
                                                    } else {
                                                        obj = str11;
                                                    }
                                                    JSONObject jSONObjectPut2 = jSONObjectPut.put("reference", obj);
                                                    if (str12 == null) {
                                                        obj2 = JSONObject.NULL;
                                                    } else {
                                                        obj2 = str12;
                                                    }
                                                    String string4 = jSONObjectPut2.put("notes", obj2).toString();
                                                    Intrinsics.checkNotNullExpressionValue(string4, "toString(...)");
                                                    syncProtocolRepository.enqueueSecondaryCommand(writableDatabase, actorUserId, operationId, "POST_OPERATING_EXPENSE", "OPERATING_EXPENSE", str14, string4);
                                                    writableDatabase.setTransactionSuccessful();
                                                    Object second = pair.getSecond();
                                                    Intrinsics.checkNotNullExpressionValue(second, "<get-second>(...)");
                                                    OperatingExpenseResult operatingExpenseResult = new OperatingExpenseResult(str14, str15, amountMinor, (String) second, jSypToUsdCents, false);
                                                    writableDatabase.endTransaction();
                                                    return operatingExpenseResult;
                                                }
                                                str2 = "operating_date";
                                                strOperatingDate = StoreTime.INSTANCE.operatingDate(writableDatabase, strTimestamp);
                                                String str16 = strOperatingDate;
                                                if (jSONObject != null) {
                                                    jCurrentRate = jSONObject.optLong("exchange_rate_syp_per_usd_milli", jCurrentRate);
                                                }
                                                if (Intrinsics.areEqual(pair.getSecond(), "USD")) {
                                                    jSypToUsdCents = amountMinor;
                                                } else {
                                                    jSypToUsdCents = CurrencyConverter.INSTANCE.sypToUsdCents(amountMinor, jCurrentRate);
                                                }
                                                String str17 = ((String) writableDatabase) + operationId;
                                                String str18 = "pending-cash-" + operationId;
                                                JSONObject jSONObjectPut3 = new JSONObject().put("expense_id", str17).put("cash_transaction_id", str18).put("category_id", categoryId).put("cash_account_id", cashAccountId).put("cash_account_type", pair.getFirst()).put("cash_account_currency_code", pair.getSecond()).put("amount_minor", amountMinor).put("currency_code", pair.getSecond()).put("exchange_rate_syp_per_usd_milli", jCurrentRate).put("amount_usd_cents", jSypToUsdCents).put("expense_date", str).put(str2, str16).put("occurred_at", strTimestamp);
                                                if (str11 == null) {
                                                    obj = JSONObject.NULL;
                                                } else {
                                                    obj = str11;
                                                }
                                                JSONObject jSONObjectPut4 = jSONObjectPut3.put("reference", obj);
                                                if (str12 == null) {
                                                    obj2 = JSONObject.NULL;
                                                } else {
                                                    obj2 = str12;
                                                }
                                                String string5 = jSONObjectPut4.put("notes", obj2).toString();
                                                Intrinsics.checkNotNullExpressionValue(string5, "toString(...)");
                                                syncProtocolRepository.enqueueSecondaryCommand(writableDatabase, actorUserId, operationId, "POST_OPERATING_EXPENSE", "OPERATING_EXPENSE", str17, string5);
                                                writableDatabase.setTransactionSuccessful();
                                                Object second2 = pair.getSecond();
                                                Intrinsics.checkNotNullExpressionValue(second2, "<get-second>(...)");
                                                OperatingExpenseResult operatingExpenseResult2 = new OperatingExpenseResult(str17, str18, amountMinor, (String) second2, jSypToUsdCents, false);
                                                writableDatabase.endTransaction();
                                                return operatingExpenseResult2;
                                            } catch (Throwable th6) {
                                                th = th6;
                                            }
                                        } catch (Throwable th7) {
                                            writableDatabase = writableDatabase;
                                            try {
                                                throw th7;
                                            } catch (Throwable th8) {
                                                CloseableKt.closeFinally(cursorRawQuery4, th7);
                                                throw th8;
                                            }
                                        }
                                    } else {
                                        try {
                                            throw new IllegalArgumentException("\u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0623\u0648 \u0645\u0648\u0642\u0648\u0641".toString());
                                        } catch (Throwable th9) {
                                            th = th9;
                                        }
                                    }
                                } catch (Throwable th10) {
                                    th = th10;
                                }
                                Throwable th11 = th;
                                try {
                                    throw th11;
                                } catch (Throwable th12) {
                                    CloseableKt.closeFinally(cursorRawQuery3, th11);
                                    throw th12;
                                }
                            } catch (Throwable th13) {
                                writableDatabase = writableDatabase;
                                try {
                                    throw th13;
                                } catch (Throwable th14) {
                                    CloseableKt.closeFinally(cursorRawQuery2, th13);
                                    throw th14;
                                }
                            }
                        } catch (Throwable th15) {
                            th = th15;
                        }
                    } else {
                        writableDatabase = writableDatabase;
                        try {
                            PermissionPolicy.INSTANCE.require(writableDatabase, actorUserId, PermissionPolicy.EXPENSES_MANAGE);
                            Cursor cursorRawQuery6 = writableDatabase.rawQuery("SELECT operation_type,payload_hash,result_reference FROM operation_registry WHERE operation_id=?", new String[]{operationId});
                            try {
                                Cursor cursor4 = cursorRawQuery6;
                                if (cursor4.moveToFirst()) {
                                    try {
                                        triple = new Triple(cursor4.getString(0), cursor4.getString(1), cursor4.isNull(2) ? null : cursor4.getString(2));
                                        th3 = null;
                                    } catch (Throwable th16) {
                                        th2 = th16;
                                        try {
                                            throw th2;
                                        } catch (Throwable th17) {
                                            CloseableKt.closeFinally(cursorRawQuery6, th2);
                                            throw th17;
                                        }
                                    }
                                } else {
                                    th3 = null;
                                    triple = null;
                                }
                                CloseableKt.closeFinally(cursorRawQuery6, th3);
                                if (triple != null) {
                                    try {
                                        if (!Intrinsics.areEqual(triple.getFirst(), "POST_OPERATING_EXPENSE") || !Intrinsics.areEqual(triple.getSecond(), strHash)) {
                                            throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0645\u0633\u062a\u062e\u062f\u0645 \u0644\u0645\u0635\u0631\u0648\u0641 \u0645\u062e\u062a\u0644\u0641 \u0623\u0648 \u0628\u064a\u0627\u0646\u0627\u062a \u0645\u062e\u062a\u0644\u0641\u0629".toString());
                                        }
                                        String str19 = (String) triple.getThird();
                                        if (str19 == null) {
                                            throw new IllegalStateException("\u0645\u0631\u062c\u0639 \u0627\u0644\u0645\u0635\u0631\u0648\u0641 \u0627\u0644\u0633\u0627\u0628\u0642 \u0645\u0641\u0642\u0648\u062f".toString());
                                        }
                                        OperatingExpenseResult operatingExpenseResultLoadResult = loadResult(writableDatabase, str19, true);
                                        writableDatabase.setTransactionSuccessful();
                                        writableDatabase.endTransaction();
                                        return operatingExpenseResultLoadResult;
                                    } catch (Throwable th18) {
                                        th = th18;
                                        writableDatabase = writableDatabase;
                                        writableDatabase.endTransaction();
                                        throw th;
                                    }
                                }
                                writableDatabase = this;
                                Cursor cursorRawQuery7 = writableDatabase.rawQuery("SELECT 1 FROM expense_categories WHERE id=? AND is_active=1", new String[]{categoryId});
                                try {
                                    boolean zMoveToFirst3 = cursorRawQuery7.moveToFirst();
                                    CloseableKt.closeFinally(cursorRawQuery7, null);
                                    if (!zMoveToFirst3) {
                                        throw new IllegalArgumentException("\u062a\u0635\u0646\u064a\u0641 \u0627\u0644\u0645\u0635\u0631\u0648\u0641 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0623\u0648 \u0645\u0648\u0642\u0648\u0641".toString());
                                    }
                                    Cursor cursorRawQuery8 = writableDatabase.rawQuery("SELECT currency_code,is_active,opening_balance_minor FROM cash_accounts WHERE id=?", new String[]{cashAccountId});
                                    try {
                                        Cursor cursor5 = cursorRawQuery8;
                                        if (cursor5.moveToFirst() && cursor5.getInt(1) == 1) {
                                            Triple triple2 = new Triple(cursor5.getString(0), Integer.valueOf(cursor5.getInt(1)), Long.valueOf(cursor5.getLong(2)));
                                            CloseableKt.closeFinally(cursorRawQuery8, null);
                                            Cursor cursorRawQuery9 = writableDatabase.rawQuery("SELECT 1 FROM system_cash_account_roles WHERE role_key='SUPPLIER_ROLLOVER' AND account_id=?", new String[]{cashAccountId});
                                            try {
                                                boolean zMoveToFirst4 = cursorRawQuery9.moveToFirst();
                                                CloseableKt.closeFinally(cursorRawQuery9, null);
                                                if (zMoveToFirst4) {
                                                    throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u0633\u062c\u064a\u0644 \u0645\u0635\u0631\u0648\u0641 \u0645\u0646 \u0645\u062e\u0635\u0635 \u062f\u0641\u0639\u0627\u062a \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0627\u0644\u064a\u0648\u0645\u064a".toString());
                                                }
                                                String str20 = (String) triple2.getFirst();
                                                if (!Intrinsics.areEqual(str20, "USD") && !Intrinsics.areEqual(str20, "SYP")) {
                                                    throw new IllegalArgumentException("\u0639\u0645\u0644\u0629 \u0627\u0644\u062d\u0633\u0627\u0628 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u0629 \u0644\u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u0645\u0635\u0631\u0648\u0641".toString());
                                                }
                                                long jCurrentRate2 = writableDatabase.currentRate(writableDatabase);
                                                if (Intrinsics.areEqual(str20, "SYP") && jCurrentRate2 <= 0) {
                                                    throw new IllegalArgumentException("\u064a\u062c\u0628 \u062a\u062d\u062f\u064a\u062f \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0642\u0628\u0644 \u062a\u0633\u062c\u064a\u0644 \u0645\u0635\u0631\u0648\u0641 \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629".toString());
                                                }
                                                long jSypToUsdCents2 = Intrinsics.areEqual(str20, "USD") ? amountMinor : CurrencyConverter.INSTANCE.sypToUsdCents(amountMinor, jCurrentRate2);
                                                long jCashBalance = writableDatabase.cashBalance(writableDatabase, cashAccountId, ((Number) triple2.getThird()).longValue());
                                                if (jCashBalance < amountMinor) {
                                                    throw new IllegalArgumentException(("\u0631\u0635\u064a\u062f \u0627\u0644\u062d\u0633\u0627\u0628 \u0644\u0627 \u064a\u0643\u0641\u064a \u0644\u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u0645\u0635\u0631\u0648\u0641. \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0645\u062a\u0627\u062d: " + jCashBalance).toString());
                                                }
                                                String strTimestamp2 = timestamp();
                                                String string6 = UUID.randomUUID().toString();
                                                Intrinsics.checkNotNullExpressionValue(string6, "toString(...)");
                                                String string7 = UUID.randomUUID().toString();
                                                Intrinsics.checkNotNullExpressionValue(string7, "toString(...)");
                                                String str21 = operationId + ":CASH";
                                                ContentValues contentValues = new ContentValues();
                                                contentValues.put("id", string7);
                                                long j = jSypToUsdCents2;
                                                contentValues.put("account_id", cashAccountId);
                                                contentValues.put("direction", "OUT");
                                                contentValues.put("amount_minor", Long.valueOf(amountMinor));
                                                contentValues.put("currency_code", str20);
                                                if (jCurrentRate2 > 0) {
                                                    Long lValueOf = Long.valueOf(jCurrentRate2);
                                                    str3 = "exchange_rate_syp_per_usd_milli";
                                                    contentValues.put(str3, lValueOf);
                                                } else {
                                                    str3 = "exchange_rate_syp_per_usd_milli";
                                                    contentValues.putNull(str3);
                                                }
                                                contentValues.put("transaction_type", "OPERATING_EXPENSE");
                                                contentValues.put("source_type", "OPERATING_EXPENSE");
                                                contentValues.put("source_id", string6);
                                                contentValues.put("operation_id", str21);
                                                if (str11 == null) {
                                                    str4 = str12 == null ? categoryId : str12;
                                                } else {
                                                    str4 = str11;
                                                }
                                                String str22 = str3;
                                                contentValues.put("description", "\u0645\u0635\u0631\u0648\u0641 \u062a\u0634\u063a\u064a\u0644\u064a: " + str4);
                                                contentValues.put("created_by", actorUserId);
                                                contentValues.put("created_at", strTimestamp2);
                                                contentValues.put("operating_date", expenseDate);
                                                Unit unit = Unit.INSTANCE;
                                                writableDatabase.insertOrThrow("cash_transactions", null, contentValues);
                                                ContentValues contentValues2 = new ContentValues();
                                                contentValues2.put("id", string6);
                                                contentValues2.put("category_id", categoryId);
                                                contentValues2.put("cash_account_id", cashAccountId);
                                                contentValues2.put("cash_transaction_id", string7);
                                                contentValues2.put("amount_minor", Long.valueOf(amountMinor));
                                                contentValues2.put("currency_code", str20);
                                                if (jCurrentRate2 > 0) {
                                                    str5 = str22;
                                                    contentValues2.put(str5, Long.valueOf(jCurrentRate2));
                                                } else {
                                                    str5 = str22;
                                                    contentValues2.putNull(str5);
                                                }
                                                String str23 = str5;
                                                contentValues2.put("amount_usd_cents", Long.valueOf(j));
                                                contentValues2.put("expense_date", expenseDate);
                                                if (str11 == null) {
                                                    str7 = "reference";
                                                    contentValues2.putNull(str7);
                                                    str6 = str11;
                                                } else {
                                                    str6 = str11;
                                                    str7 = "reference";
                                                    contentValues2.put(str7, str6);
                                                }
                                                String str24 = str6;
                                                if (str12 == null) {
                                                    contentValues2.putNull("notes");
                                                    str9 = "notes";
                                                    str8 = str12;
                                                } else {
                                                    str8 = str12;
                                                    contentValues2.put("notes", str8);
                                                    str9 = "notes";
                                                }
                                                contentValues2.put("created_by", actorUserId);
                                                String str25 = str8;
                                                contentValues2.put("operation_id", operationId);
                                                contentValues2.put("created_at", strTimestamp2);
                                                Unit unit2 = Unit.INSTANCE;
                                                writableDatabase.insertOrThrow("operating_expenses", null, contentValues2);
                                                ContentValues contentValues3 = new ContentValues();
                                                contentValues3.put("operation_id", operationId);
                                                contentValues3.put("operation_type", "POST_OPERATING_EXPENSE");
                                                contentValues3.put("payload_hash", strHash);
                                                contentValues3.put(NotificationCompat.CATEGORY_STATUS, "COMPLETED");
                                                contentValues3.put("result_reference", string6);
                                                contentValues3.put("created_at", strTimestamp2);
                                                contentValues3.put("completed_at", strTimestamp2);
                                                Unit unit3 = Unit.INSTANCE;
                                                writableDatabase.insertOrThrow("operation_registry", null, contentValues3);
                                                ContentValues contentValues4 = new ContentValues();
                                                contentValues4.put("id", UUID.randomUUID().toString());
                                                contentValues4.put("user_id", actorUserId);
                                                contentValues4.put("action", "OPERATING_EXPENSE_POSTED");
                                                contentValues4.put("entity_type", "OPERATING_EXPENSE");
                                                contentValues4.put("entity_id", string6);
                                                String str26 = str7;
                                                JSONObject jSONObjectPut5 = new JSONObject().put("category_id", categoryId).put("cash_account_id", cashAccountId).put("amount_minor", amountMinor).put("currency_code", str20).put("amount_usd_cents", j).put("expense_date", expenseDate).put(str26, str24);
                                                String str27 = str9;
                                                contentValues4.put("after_json", jSONObjectPut5.put(str27, str25).toString());
                                                contentValues4.put("created_at", strTimestamp2);
                                                Unit unit4 = Unit.INSTANCE;
                                                writableDatabase.insertOrThrow("audit_log", null, contentValues4);
                                                Context context2 = this.context;
                                                Intrinsics.checkNotNullExpressionValue(context2, "context");
                                                try {
                                                    ?? syncProtocolRepository2 = new SyncProtocolRepository(context2, null, 2, 0 == true ? 1 : 0);
                                                    String string8 = new JSONObject().put("expense_id", string6).put("cash_transaction_id", string7).put("category_id", categoryId).put("cash_account_id", cashAccountId).put("amount_minor", amountMinor).put("currency_code", str20).put(str23, jCurrentRate2).put("amount_usd_cents", j).put("expense_date", expenseDate).put(str26, str24).put(str27, str25).put("created_at", strTimestamp2).toString();
                                                    Intrinsics.checkNotNullExpressionValue(string8, "toString(...)");
                                                    syncProtocolRepository2.enqueueLocalOperation(writableDatabase, actorUserId, operationId, "POST_OPERATING_EXPENSE", "OPERATING_EXPENSE", string6, "CREATE", string8);
                                                    writableDatabase.setTransactionSuccessful();
                                                    OperatingExpenseResult operatingExpenseResult3 = new OperatingExpenseResult(string6, string7, amountMinor, str20, j, false);
                                                    writableDatabase.endTransaction();
                                                    return operatingExpenseResult3;
                                                } catch (Throwable th19) {
                                                    th = th19;
                                                    writableDatabase = writableDatabase;
                                                }
                                            } catch (Throwable th20) {
                                                writableDatabase = writableDatabase;
                                                try {
                                                    throw th20;
                                                } catch (Throwable th21) {
                                                    CloseableKt.closeFinally(cursorRawQuery9, th20);
                                                    throw th21;
                                                }
                                            }
                                        } else {
                                            try {
                                                throw new IllegalArgumentException("\u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0623\u0648 \u0645\u0648\u0642\u0648\u0641".toString());
                                            } catch (Throwable th22) {
                                                th = th22;
                                            }
                                        }
                                    } catch (Throwable th23) {
                                        th = th23;
                                    }
                                    Throwable th24 = th;
                                    try {
                                        throw th24;
                                    } catch (Throwable th25) {
                                        CloseableKt.closeFinally(cursorRawQuery8, th24);
                                        throw th25;
                                    }
                                } catch (Throwable th26) {
                                    writableDatabase = writableDatabase;
                                    try {
                                        throw th26;
                                    } catch (Throwable th27) {
                                        CloseableKt.closeFinally(cursorRawQuery7, th26);
                                        throw th27;
                                    }
                                }
                            } catch (Throwable th28) {
                                th2 = th28;
                            }
                        } catch (Throwable th29) {
                            th = th29;
                        }
                    }
                } catch (Throwable th30) {
                    th = th30;
                    writableDatabase = writableDatabase;
                }
            } catch (Throwable th31) {
                try {
                    throw th31;
                } catch (Throwable th32) {
                    CloseableKt.closeFinally(cursorRawQuery, th31);
                    throw th32;
                }
            }
        } catch (Throwable th33) {
            th = th33;
        }
        writableDatabase.endTransaction();
        throw th;
    }

    public static /* synthetic */ List listExpenses$default(OperatingExpenseRepository operatingExpenseRepository, String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = null;
        }
        if ((i2 & 2) != 0) {
            str2 = null;
        }
        if ((i2 & 4) != 0) {
            i = MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION;
        }
        return operatingExpenseRepository.listExpenses(str, str2, i);
    }

    public final List<OperatingExpense> listExpenses(String fromDate, String throughDate, int limit) throws IOException {
        String[] strArr;
        int i = 0;
        int i2 = 1;
        if ((fromDate == null) != (throughDate == null)) {
            throw new IllegalArgumentException("\u064a\u062c\u0628 \u062a\u062d\u062f\u064a\u062f \u062a\u0627\u0631\u064a\u062e\u064a \u0627\u0644\u0628\u062f\u0627\u064a\u0629 \u0648\u0627\u0644\u0646\u0647\u0627\u064a\u0629 \u0645\u0639\u0627\u064b".toString());
        }
        if (fromDate != null) {
            Intrinsics.checkNotNull(throughDate);
            if (fromDate.compareTo(throughDate) > 0) {
                throw new IllegalArgumentException("\u0646\u0637\u0627\u0642 \u0627\u0644\u062a\u0627\u0631\u064a\u062e \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
            }
        }
        if (1 > limit || limit >= 1001) {
            throw new IllegalArgumentException("\u0639\u062f\u062f \u0627\u0644\u0633\u062c\u0644\u0627\u062a \u0627\u0644\u0645\u0637\u0644\u0648\u0628 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
        }
        String str = fromDate == null ? "" : "WHERE e.expense_date>=? AND e.expense_date<=?";
        int i3 = 2;
        if (fromDate == null) {
            strArr = new String[]{String.valueOf(limit)};
        } else {
            Intrinsics.checkNotNull(throughDate);
            strArr = new String[]{fromDate, throughDate, String.valueOf(limit)};
        }
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery(StringsKt.trimIndent("SELECT e.id,e.expense_date,e.category_id,c.name,e.cash_account_id,a.name,\n                      e.amount_minor,e.currency_code,e.amount_usd_cents,e.reference,e.notes,\n                      e.created_by,e.created_at\n               FROM operating_expenses e JOIN expense_categories c ON c.id=e.category_id\n               JOIN cash_accounts a ON a.id=e.cash_account_id\n               " + str + " ORDER BY e.expense_date DESC,e.created_at DESC LIMIT ?"), strArr);
        try {
            Cursor cursor = cursorRawQuery;
            while (true) {
                String string = null;
                if (cursor.moveToNext()) {
                    String string2 = cursor.getString(i);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    String string3 = cursor.getString(i2);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                    String string4 = cursor.getString(i3);
                    Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                    String string5 = cursor.getString(3);
                    Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                    String string6 = cursor.getString(4);
                    Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                    String string7 = cursor.getString(5);
                    Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
                    long j = cursor.getLong(6);
                    String string8 = cursor.getString(7);
                    Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
                    long j2 = cursor.getLong(8);
                    String string9 = cursor.isNull(9) ? null : cursor.getString(9);
                    if (!cursor.isNull(10)) {
                        string = cursor.getString(10);
                    }
                    String str2 = string;
                    String string10 = cursor.getString(11);
                    Intrinsics.checkNotNullExpressionValue(string10, "getString(...)");
                    String string11 = cursor.getString(12);
                    Intrinsics.checkNotNullExpressionValue(string11, "getString(...)");
                    arrayList.add(new OperatingExpense(string2, string3, string4, string5, string6, string7, j, string8, j2, string9, str2, string10, string11));
                    i = 0;
                    i2 = 1;
                    i3 = 2;
                } else {
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursorRawQuery, null);
                    return arrayList;
                }
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

    public static /* synthetic */ long totalUsdCents$default(OperatingExpenseRepository operatingExpenseRepository, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        return operatingExpenseRepository.totalUsdCents(str, str2);
    }

    public final long totalUsdCents(String fromDate, String throughDate) throws IOException {
        String[] strArr;
        if ((fromDate == null) != (throughDate == null)) {
            throw new IllegalArgumentException("\u064a\u062c\u0628 \u062a\u062d\u062f\u064a\u062f \u062a\u0627\u0631\u064a\u062e\u064a \u0627\u0644\u0628\u062f\u0627\u064a\u0629 \u0648\u0627\u0644\u0646\u0647\u0627\u064a\u0629 \u0645\u0639\u0627\u064b".toString());
        }
        if (fromDate != null) {
            Intrinsics.checkNotNull(throughDate);
            if (fromDate.compareTo(throughDate) > 0) {
                throw new IllegalArgumentException("\u0646\u0637\u0627\u0642 \u0627\u0644\u062a\u0627\u0631\u064a\u062e \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
            }
        }
        String str = fromDate == null ? "" : "WHERE expense_date>=? AND expense_date<=?";
        if (fromDate == null) {
            strArr = null;
        } else {
            Intrinsics.checkNotNull(throughDate);
            strArr = new String[]{fromDate, throughDate};
        }
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery("SELECT COALESCE(SUM(amount_usd_cents),0) FROM operating_expenses ".concat(str), strArr);
        try {
            Cursor cursor = cursorRawQuery;
            cursor.moveToFirst();
            long j = cursor.getLong(0);
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

    private final OperatingExpenseResult loadResult(SQLiteDatabase db, String id, boolean replayed) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT cash_transaction_id,amount_minor,currency_code,amount_usd_cents FROM operating_expenses WHERE id=?", new String[]{id});
        try {
            Cursor cursor = cursorRawQuery;
            if (cursor.moveToFirst()) {
                String string = cursor.getString(0);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                long j = cursor.getLong(1);
                String string2 = cursor.getString(2);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                OperatingExpenseResult operatingExpenseResult = new OperatingExpenseResult(id, string, j, string2, cursor.getLong(3), replayed);
                CloseableKt.closeFinally(cursorRawQuery, null);
                return operatingExpenseResult;
            }
            throw new IllegalArgumentException("\u0627\u0644\u0645\u0635\u0631\u0648\u0641 \u0627\u0644\u0633\u0627\u0628\u0642 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final long currentRate(SQLiteDatabase db) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT rate_syp_per_usd_milli FROM exchange_rates WHERE currency_code='USD' ORDER BY effective_at DESC,created_at DESC LIMIT 1", (String[]) null);
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

    private final long cashBalance(SQLiteDatabase db, String accountId, long opening) throws IOException {
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

    private final String hash(String value) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = value.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrDigest = messageDigest.digest(bytes);
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
        return ArraysKt.joinToString$default(bArrDigest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.hazem.dailystore.data.OperatingExpenseRepository$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return OperatingExpenseRepository.hash$lambda$51(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence hash$lambda$51(byte b) {
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
