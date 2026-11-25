package com.hazem.dailystore.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Base64;
import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import net.zetetic.database.sqlcipher.SQLiteDatabase;
import org.json.JSONObject;

/* JADX INFO: compiled from: AuthRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0012\n\u0002\b\u0005\u0018\u0000 :2\u00020\u0001:\u00029:B!\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tB\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\b\u0010\nB\u0019\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\b\u0010\u000bJ\u0006\u0010\u000e\u001a\u00020\u000fJ\u001e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0011J\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0011J\u0014\u0010\u0017\u001a\u00020\u00072\n\u0010\u0018\u001a\u00060\u0019j\u0002`\u001aH\u0002J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u001c\u001a\u00020\u0011J\u001e\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u0011J\u0010\u0010 \u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0011H\u0002J\u000e\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0011J\u000e\u0010$\u001a\u00020\u00112\u0006\u0010%\u001a\u00020\"J\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'2\u0006\u0010#\u001a\u00020\u0011J\u001e\u0010)\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u00112\u0006\u0010*\u001a\u00020\u00112\u0006\u0010+\u001a\u00020\u000fJ6\u0010,\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010.\u001a\u00020\u0011J\u000e\u0010/\u001a\u00020\u00072\u0006\u0010.\u001a\u00020\u0011J\u0016\u00100\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u00112\u0006\u00101\u001a\u00020\u0011J\u0010\u00102\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u0011H\u0002J:\u00103\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010.\u001a\u00020\u00112\b\u0010#\u001a\u0004\u0018\u00010\u00112\u0006\u00104\u001a\u00020\u000fH\u0002J\u0018\u00105\u001a\u0002062\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u00107\u001a\u00020\u0011H\u0002J\b\u00108\u001a\u00020\u0011H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006;"}, d2 = {"Lcom/hazem/dailystore/data/AuthRepository;", "", "context", "Landroid/content/Context;", "pbkdf2Iterations", "", "testOnly", "", "<init>", "(Landroid/content/Context;ILkotlin/Unit;)V", "(Landroid/content/Context;)V", "(Landroid/content/Context;I)V", "database", "Lcom/hazem/dailystore/data/AppDatabase;", "needsInitialAdminSetup", "", "createInitialAdmin", "", "username", "displayName", "password", "authenticate", "Lcom/hazem/dailystore/data/AuthenticatedUser;", "ensureLoginAttemptSchema", "db", "Lnet/zetetic/database/sqlcipher/SQLiteDatabase;", "Lcom/hazem/dailystore/data/AppSqliteDatabase;", "restoreActiveSession", "userId", "changePassword", "currentPassword", "newPassword", "isStrongPassword", "exportPairingAccount", "Lcom/hazem/dailystore/data/PairingAccountSnapshot;", "actorUserId", "installPairedAccount", "account", "listUsers", "", "Lcom/hazem/dailystore/data/ManagedUserSummary;", "setUserActive", "targetUserId", "active", "createUser", "actorRole", "roleKey", "requireManager", "requirePermission", "permissionKey", "requireActiveManager", "createUserInternal", "mustChangePassword", "derive", "", "salt", "timestamp", "UserCredential", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AuthRepository {
    private static final String DUMMY_LOGIN_SALT = "AAECAwQFBgcICQoLDA0ODw==";
    private static final long LOGIN_LOCK_MS = 60000;
    private static final int MAX_LOGIN_FAILURES = 5;
    public static final int PBKDF2_ITERATIONS = 120000;
    private final AppDatabase database;
    private final int pbkdf2Iterations;
    private static final Object LOGIN_LOCK = new Object();

    private AuthRepository(Context context, int i, Unit unit) {
        this.pbkdf2Iterations = i;
        this.database = AppDatabase.INSTANCE.getInstance(context);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AuthRepository(Context context) {
        this(context, PBKDF2_ITERATIONS, Unit.INSTANCE);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AuthRepository(Context context, int i) {
        this(context, i, Unit.INSTANCE);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final boolean needsInitialAdminSetup() throws IOException {
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        Cursor cursorRawQuery = readableDatabase.rawQuery("SELECT COUNT(*) FROM users", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            int i = cursor.moveToFirst() ? cursor.getInt(0) : 0;
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (i == 0) {
                return true;
            }
            if (i != 1) {
                return false;
            }
            Cursor cursorRawQuery2 = readableDatabase.rawQuery("SELECT 1 FROM users WHERE username='admin' COLLATE NOCASE AND role_key='ADMIN' AND is_active=1 AND must_change_password=1", (String[]) null);
            try {
                boolean zMoveToFirst = cursorRawQuery2.moveToFirst();
                CloseableKt.closeFinally(cursorRawQuery2, null);
                return zMoveToFirst;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(cursorRawQuery2, th);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            try {
                throw th3;
            } catch (Throwable th4) {
                CloseableKt.closeFinally(cursorRawQuery, th3);
                throw th4;
            }
        }
    }

    public final String createInitialAdmin(String username, String displayName, String password) {
        SQLiteDatabase sQLiteDatabase;
        String string;
        SQLiteDatabase sQLiteDatabase2;
        Throwable th;
        String string2;
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        Intrinsics.checkNotNullParameter(password, "password");
        String string3 = StringsKt.trim((CharSequence) username).toString();
        String string4 = StringsKt.trim((CharSequence) displayName).toString();
        if (string3.length() < 3) {
            throw new IllegalArgumentException("\u0627\u0633\u0645 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u064a\u062c\u0628 \u0623\u0644\u0627 \u064a\u0642\u0644 \u0639\u0646 3 \u0623\u062d\u0631\u0641".toString());
        }
        if (string4.length() <= 0) {
            throw new IllegalArgumentException("\u0627\u0633\u0645 \u0627\u0644\u0639\u0631\u0636 \u0645\u0637\u0644\u0648\u0628".toString());
        }
        if (!isStrongPassword(password)) {
            throw new IllegalArgumentException("\u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631 \u064a\u062c\u0628 \u0623\u0646 \u062a\u062a\u0643\u0648\u0646 \u0645\u0646 10 \u0623\u062d\u0631\u0641 \u0639\u0644\u0649 \u0627\u0644\u0623\u0642\u0644 \u0648\u062a\u062a\u0636\u0645\u0646 \u062d\u0631\u0641\u0627\u064b \u0648\u0631\u0642\u0645\u0627\u064b".toString());
        }
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        byte[] bArr = new byte[16];
        new SecureRandom().nextBytes(bArr);
        String strEncodeToString = Base64.encodeToString(bArr, 2);
        Intrinsics.checkNotNull(strEncodeToString);
        String strEncodeToString2 = Base64.encodeToString(derive(password, strEncodeToString), 2);
        String strTimestamp = timestamp();
        writableDatabase.beginTransaction();
        try {
            try {
                Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT COUNT(*) FROM users", (String[]) null);
                try {
                    Cursor cursor = cursorRawQuery;
                    int i = cursor.moveToFirst() ? cursor.getInt(0) : 0;
                    CloseableKt.closeFinally(cursorRawQuery, null);
                    String str = "id";
                    if (i == 0) {
                        try {
                            string = UUID.randomUUID().toString();
                            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("id", string);
                            contentValues.put("username", string3);
                            contentValues.put("display_name", string4);
                            contentValues.put("password_hash", strEncodeToString2);
                            contentValues.put("password_salt", strEncodeToString);
                            contentValues.put("role_key", "ADMIN");
                            contentValues.put("is_active", (Integer) 1);
                            contentValues.put("must_change_password", (Integer) 0);
                            contentValues.put("created_at", strTimestamp);
                            contentValues.put("updated_at", strTimestamp);
                            Unit unit = Unit.INSTANCE;
                            sQLiteDatabase = writableDatabase;
                            try {
                                sQLiteDatabase.insertOrThrow("users", null, contentValues);
                                str = "id";
                                sQLiteDatabase2 = sQLiteDatabase;
                            } catch (SQLiteConstraintException unused) {
                                throw new IllegalArgumentException("\u0627\u0633\u0645 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u0645\u0633\u062a\u062e\u062f\u0645 \u0645\u0633\u0628\u0642\u0627\u064b");
                            } catch (Throwable th2) {
                                th = th2;
                                writableDatabase = sQLiteDatabase;
                                writableDatabase.endTransaction();
                                throw th;
                            }
                        } catch (SQLiteConstraintException unused2) {
                        } catch (Throwable th3) {
                            th = th3;
                            sQLiteDatabase = writableDatabase;
                        }
                    } else {
                        sQLiteDatabase2 = writableDatabase;
                        Cursor cursorRawQuery2 = sQLiteDatabase2.rawQuery("SELECT id FROM users WHERE username='admin' COLLATE NOCASE AND role_key='ADMIN' AND is_active=1 AND must_change_password=1 AND (SELECT COUNT(*) FROM users)=1", (String[]) null);
                        try {
                            Cursor cursor2 = cursorRawQuery2;
                            if (cursor2.moveToFirst()) {
                                string2 = cursor2.getString(0);
                                th = null;
                            } else {
                                th = null;
                                string2 = null;
                            }
                            CloseableKt.closeFinally(cursorRawQuery2, th);
                            if (string2 == null) {
                                throw new IllegalStateException("\u062a\u0645 \u0625\u0639\u062f\u0627\u062f \u062d\u0633\u0627\u0628 \u0645\u0633\u062a\u062e\u062f\u0645 \u0645\u0633\u0628\u0642\u0627\u064b\u061b \u0644\u0627 \u064a\u0645\u0643\u0646 \u0625\u0639\u0627\u062f\u0629 \u062a\u0647\u064a\u0626\u0629 \u0627\u0644\u0645\u062f\u064a\u0631");
                            }
                            ContentValues contentValues2 = new ContentValues();
                            contentValues2.put("username", string3);
                            contentValues2.put("display_name", string4);
                            contentValues2.put("password_hash", strEncodeToString2);
                            contentValues2.put("password_salt", strEncodeToString);
                            contentValues2.put("must_change_password", (Integer) 0);
                            contentValues2.put("updated_at", strTimestamp);
                            Unit unit2 = Unit.INSTANCE;
                            if (sQLiteDatabase2.update("users", contentValues2, "id=?", new String[]{string2}) != 1) {
                                throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062d\u0641\u0638 \u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u062f\u064a\u0631".toString());
                            }
                            string = string2;
                        } catch (Throwable th4) {
                            try {
                                throw th4;
                            } catch (Throwable th5) {
                                CloseableKt.closeFinally(cursorRawQuery2, th4);
                                throw th5;
                            }
                        }
                    }
                    ContentValues contentValues3 = new ContentValues();
                    contentValues3.put(str, UUID.randomUUID().toString());
                    contentValues3.put("user_id", string);
                    contentValues3.put("action", "INITIAL_ADMIN_SETUP");
                    contentValues3.put("entity_type", "USER");
                    contentValues3.put("entity_id", string);
                    contentValues3.put("after_json", new JSONObject().put("username", string3).put("role", "ADMIN").toString());
                    contentValues3.put("created_at", strTimestamp);
                    Unit unit3 = Unit.INSTANCE;
                    sQLiteDatabase2.insertOrThrow("audit_log", null, contentValues3);
                    sQLiteDatabase2.setTransactionSuccessful();
                    sQLiteDatabase2.endTransaction();
                    return string;
                } catch (Throwable th6) {
                    try {
                        throw th6;
                    } catch (Throwable th7) {
                        CloseableKt.closeFinally(cursorRawQuery, th6);
                        throw th7;
                    }
                }
            } catch (Throwable th8) {
                th = th8;
            }
        } catch (SQLiteConstraintException unused3) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final AuthenticatedUser authenticate(String username, String password) {
        AuthenticatedUser authenticatedUser;
        Throwable th;
        UserCredential userCredential;
        String salt;
        Object objM381constructorimpl;
        int i;
        boolean zBooleanValue;
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(password, "password");
        synchronized (LOGIN_LOCK) {
            try {
                String string = StringsKt.trim((CharSequence) username).toString();
                authenticatedUser = null;
                if (string.length() != 0 && password.length() != 0) {
                    SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
                    Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
                    ensureLoginAttemptSchema(writableDatabase);
                    MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                    Locale ROOT = Locale.ROOT;
                    Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
                    String lowerCase = string.toLowerCase(ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                    byte[] bytes = lowerCase.getBytes(Charsets.UTF_8);
                    Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                    byte[] bArrDigest = messageDigest.digest(bytes);
                    Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
                    String strJoinToString$default = ArraysKt.joinToString$default(bArrDigest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.hazem.dailystore.data.AuthRepository$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AuthRepository.authenticate$lambda$19$lambda$12(((Byte) obj).byteValue());
                        }
                    }, 30, (Object) null);
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT failed_attempts,locked_until_ms FROM auth_login_attempts WHERE username_hash=?", new String[]{strJoinToString$default});
                    try {
                        Cursor cursor = cursorRawQuery;
                        Pair pair = cursor.moveToFirst() ? TuplesKt.to(Integer.valueOf(cursor.getInt(0)), Long.valueOf(cursor.getLong(1))) : null;
                        CloseableKt.closeFinally(cursorRawQuery, null);
                        if (pair == null || ((Number) pair.getSecond()).longValue() <= jCurrentTimeMillis) {
                            Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT id,username,display_name,password_hash,password_salt,role_key,is_active,must_change_password FROM users WHERE username=? COLLATE NOCASE", new String[]{string});
                            try {
                                Cursor cursor2 = cursorRawQuery2;
                                if (cursor2.moveToFirst() && cursor2.getInt(6) == 1) {
                                    String string2 = cursor2.getString(0);
                                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                                    String string3 = cursor2.getString(1);
                                    Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                                    String string4 = cursor2.getString(2);
                                    Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                                    String string5 = cursor2.getString(3);
                                    Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                                    String string6 = cursor2.getString(4);
                                    Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                                    String string7 = cursor2.getString(5);
                                    Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
                                    userCredential = new UserCredential(string2, string3, string4, string5, string6, string7, cursor2.getInt(7) == 1);
                                    th = null;
                                } else {
                                    th = null;
                                    userCredential = null;
                                }
                                CloseableKt.closeFinally(cursorRawQuery2, th);
                                if (userCredential == null || (salt = userCredential.getSalt()) == null) {
                                    salt = DUMMY_LOGIN_SALT;
                                }
                                byte[] bArrDerive = derive(password, salt);
                                if (userCredential != null) {
                                    try {
                                        Result.Companion companion = Result.INSTANCE;
                                        AuthRepository authRepository = this;
                                        objM381constructorimpl = Result.m381constructorimpl(Boolean.valueOf(MessageDigest.isEqual(bArrDerive, Base64.decode(userCredential.getHash(), 2))));
                                    } catch (Throwable th2) {
                                        Result.Companion companion2 = Result.INSTANCE;
                                        objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th2));
                                    }
                                    i = 0;
                                    if (Result.m387isFailureimpl(objM381constructorimpl)) {
                                        objM381constructorimpl = false;
                                    }
                                    zBooleanValue = ((Boolean) objM381constructorimpl).booleanValue();
                                } else {
                                    i = 0;
                                    zBooleanValue = false;
                                }
                                if (!zBooleanValue || userCredential == null) {
                                    long jCurrentTimeMillis2 = System.currentTimeMillis();
                                    int iIntValue = (pair != null && (((Number) pair.getSecond()).longValue() == 0 || ((Number) pair.getSecond()).longValue() > jCurrentTimeMillis2)) ? ((Number) pair.getFirst()).intValue() : i;
                                    int i2 = iIntValue + 1;
                                    long j = i2 >= 5 ? jCurrentTimeMillis2 + LOGIN_LOCK_MS : 0L;
                                    ContentValues contentValues = new ContentValues();
                                    contentValues.put("username_hash", strJoinToString$default);
                                    contentValues.put("failed_attempts", Integer.valueOf(j > 0 ? i : i2));
                                    contentValues.put("locked_until_ms", Long.valueOf(j));
                                    contentValues.put("updated_at", timestamp());
                                    Unit unit = Unit.INSTANCE;
                                    writableDatabase.insertWithOnConflict("auth_login_attempts", null, contentValues, 5);
                                    authenticatedUser = null;
                                } else {
                                    writableDatabase.delete("auth_login_attempts", "username_hash=?", new String[]{strJoinToString$default});
                                    String strTimestamp = timestamp();
                                    ContentValues contentValues2 = new ContentValues();
                                    contentValues2.put("last_login_at", strTimestamp);
                                    contentValues2.put("updated_at", strTimestamp);
                                    Unit unit2 = Unit.INSTANCE;
                                    writableDatabase.update("users", contentValues2, "id=?", new String[]{userCredential.getId()});
                                    authenticatedUser = new AuthenticatedUser(userCredential.getId(), userCredential.getUsername(), userCredential.getDisplayName(), userCredential.getRole(), userCredential.getMustChangePassword());
                                }
                            } catch (Throwable th3) {
                                try {
                                    throw th3;
                                } catch (Throwable th4) {
                                    CloseableKt.closeFinally(cursorRawQuery2, th3);
                                    throw th4;
                                }
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
            } catch (Throwable th7) {
                throw th7;
            }
        }
        return authenticatedUser;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence authenticate$lambda$19$lambda$12(byte b) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    private final void ensureLoginAttemptSchema(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS auth_login_attempts (\n                username_hash TEXT PRIMARY KEY NOT NULL,\n                failed_attempts INTEGER NOT NULL DEFAULT 0 CHECK(failed_attempts >= 0),\n                locked_until_ms INTEGER NOT NULL DEFAULT 0 CHECK(locked_until_ms >= 0),\n                updated_at TEXT NOT NULL\n            )");
    }

    public final AuthenticatedUser restoreActiveSession(String userId) throws IOException {
        AuthenticatedUser authenticatedUser;
        Intrinsics.checkNotNullParameter(userId, "userId");
        if (StringsKt.isBlank(userId)) {
            return null;
        }
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery("SELECT id,username,display_name,role_key,must_change_password FROM users WHERE id=? AND is_active=1", new String[]{userId});
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
                authenticatedUser = new AuthenticatedUser(string, string2, string3, string4, cursor.getInt(4) == 1);
            } else {
                authenticatedUser = null;
            }
            CloseableKt.closeFinally(cursorRawQuery, null);
            return authenticatedUser;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    public final void changePassword(String userId, String currentPassword, String newPassword) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(currentPassword, "currentPassword");
        Intrinsics.checkNotNullParameter(newPassword, "newPassword");
        if (StringsKt.isBlank(userId)) {
            throw new IllegalArgumentException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
        }
        if (!isStrongPassword(newPassword)) {
            throw new IllegalArgumentException("\u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631 \u0627\u0644\u062c\u062f\u064a\u062f\u0629 \u064a\u062c\u0628 \u0623\u0646 \u062a\u062a\u0643\u0648\u0646 \u0645\u0646 10 \u0623\u062d\u0631\u0641 \u0639\u0644\u0649 \u0627\u0644\u0623\u0642\u0644 \u0648\u062a\u062a\u0636\u0645\u0646 \u062d\u0631\u0641\u0627\u064b \u0648\u0631\u0642\u0645\u0627\u064b".toString());
        }
        if (Intrinsics.areEqual(currentPassword, newPassword)) {
            throw new IllegalArgumentException("\u0627\u062e\u062a\u0631 \u0643\u0644\u0645\u0629 \u0645\u0631\u0648\u0631 \u0645\u062e\u062a\u0644\u0641\u0629 \u0639\u0646 \u0627\u0644\u062d\u0627\u0644\u064a\u0629".toString());
        }
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT password_hash,password_salt FROM users WHERE id=? AND is_active=1", new String[]{userId});
        try {
            Cursor cursor = cursorRawQuery;
            Pair pair = !cursor.moveToFirst() ? null : TuplesKt.to(cursor.getString(0), cursor.getString(1));
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (pair == null) {
                throw new IllegalArgumentException("\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0646\u0634\u0637");
            }
            Object second = pair.getSecond();
            Intrinsics.checkNotNullExpressionValue(second, "<get-second>(...)");
            if (!MessageDigest.isEqual(derive(currentPassword, (String) second), Base64.decode((String) pair.getFirst(), 2))) {
                throw new IllegalArgumentException("\u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631 \u0627\u0644\u062d\u0627\u0644\u064a\u0629 \u063a\u064a\u0631 \u0635\u062d\u064a\u062d\u0629".toString());
            }
            byte[] bArr = new byte[16];
            new SecureRandom().nextBytes(bArr);
            String strEncodeToString = Base64.encodeToString(bArr, 2);
            Intrinsics.checkNotNull(strEncodeToString);
            String strEncodeToString2 = Base64.encodeToString(derive(newPassword, strEncodeToString), 2);
            String strTimestamp = timestamp();
            writableDatabase.beginTransaction();
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("password_hash", strEncodeToString2);
                contentValues.put("password_salt", strEncodeToString);
                contentValues.put("must_change_password", (Integer) 0);
                contentValues.put("updated_at", strTimestamp);
                Unit unit = Unit.INSTANCE;
                if (writableDatabase.update("users", contentValues, "id=? AND is_active=1", new String[]{userId}) != 1) {
                    throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062d\u062f\u064a\u062b \u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631".toString());
                }
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("id", UUID.randomUUID().toString());
                contentValues2.put("user_id", userId);
                contentValues2.put("action", "PASSWORD_CHANGE");
                contentValues2.put("entity_type", "USER");
                contentValues2.put("entity_id", userId);
                contentValues2.put("created_at", strTimestamp);
                Unit unit2 = Unit.INSTANCE;
                writableDatabase.insertOrThrow("audit_log", null, contentValues2);
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
            } catch (Throwable th) {
                writableDatabase.endTransaction();
                throw th;
            }
        } catch (Throwable th2) {
            try {
                throw th2;
            } catch (Throwable th3) {
                CloseableKt.closeFinally(cursorRawQuery, th2);
                throw th3;
            }
        }
    }

    private final boolean isStrongPassword(String password) {
        if (password.length() < 10) {
            return false;
        }
        String str = password;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) {
                for (int i2 = 0; i2 < str.length(); i2++) {
                    if (Character.isDigit(str.charAt(i2))) {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    public final PairingAccountSnapshot exportPairingAccount(String actorUserId) throws IOException {
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        requireActiveManager(actorUserId);
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery("SELECT id,username,display_name,password_hash,password_salt,role_key FROM users WHERE id=? AND is_active=1 AND must_change_password=0", new String[]{actorUserId});
        try {
            Cursor cursor = cursorRawQuery;
            if (!cursor.moveToFirst()) {
                throw new IllegalArgumentException("\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u062f\u064a\u0631 \u063a\u064a\u0631 \u0646\u0634\u0637 \u0623\u0648 \u064a\u062a\u0637\u0644\u0628 \u062a\u063a\u064a\u064a\u0631 \u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631".toString());
            }
            String string = cursor.getString(0);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String string2 = cursor.getString(1);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            String string3 = cursor.getString(2);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
            String string4 = cursor.getString(3);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
            String string5 = cursor.getString(4);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
            String string6 = cursor.getString(5);
            Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
            PairingAccountSnapshot pairingAccountSnapshot = new PairingAccountSnapshot(string, string2, string3, string4, string5, string6);
            CloseableKt.closeFinally(cursorRawQuery, null);
            return pairingAccountSnapshot;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    public final String installPairedAccount(PairingAccountSnapshot account) {
        Triple triple;
        Intrinsics.checkNotNullParameter(account, "account");
        if (StringsKt.isBlank(account.getId()) || account.getUsername().length() < 3 || StringsKt.isBlank(account.getDisplayName()) || StringsKt.isBlank(account.getPasswordHash()) || StringsKt.isBlank(account.getPasswordSalt()) || !Intrinsics.areEqual(account.getRoleKey(), "ADMIN")) {
            throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u062f\u064a\u0631 \u0627\u0644\u0645\u0631\u0641\u0642\u0629 \u0628\u0627\u0644\u0627\u0642\u062a\u0631\u0627\u0646 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
        }
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        writableDatabase.beginTransaction();
        try {
            try {
                Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT username,role_key,is_active FROM users WHERE id=?", new String[]{account.getId()});
                try {
                    Cursor cursor = cursorRawQuery;
                    if (cursor.moveToFirst()) {
                        triple = new Triple(cursor.getString(0), cursor.getString(1), Boolean.valueOf(cursor.getInt(2) == 1));
                    } else {
                        triple = null;
                    }
                    CloseableKt.closeFinally(cursorRawQuery, null);
                    if (triple != null) {
                        if (!StringsKt.equals((String) triple.getFirst(), account.getUsername(), true) || !Intrinsics.areEqual(triple.getSecond(), "ADMIN") || !((Boolean) triple.getThird()).booleanValue()) {
                            throw new IllegalArgumentException("\u064a\u0648\u062c\u062f \u062d\u0633\u0627\u0628 \u0645\u062d\u0644\u064a \u0645\u062a\u0639\u0627\u0631\u0636\u061b \u0644\u0646 \u064a\u062a\u0645 \u0627\u0633\u062a\u0628\u062f\u0627\u0644 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645".toString());
                        }
                        writableDatabase.setTransactionSuccessful();
                        String id = account.getId();
                        writableDatabase.endTransaction();
                        return id;
                    }
                    Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT COUNT(*) FROM users", (String[]) null);
                    try {
                        Cursor cursor2 = cursorRawQuery2;
                        int i = cursor2.moveToFirst() ? cursor2.getInt(0) : 0;
                        CloseableKt.closeFinally(cursorRawQuery2, null);
                        if (i != 0) {
                            throw new IllegalArgumentException("\u0647\u0630\u0627 \u0627\u0644\u0647\u0627\u062a\u0641 \u064a\u062d\u062a\u0648\u064a \u062d\u0633\u0627\u0628\u0627\u062a \u0645\u062d\u0644\u064a\u0629\u061b \u0633\u062c\u0651\u0644 \u0627\u0644\u062f\u062e\u0648\u0644 \u0628\u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0648\u062c\u0648\u062f \u0642\u0628\u0644 \u0631\u0628\u0637\u0647 \u0628\u0627\u0644\u0645\u062a\u062c\u0631".toString());
                        }
                        String strTimestamp = timestamp();
                        PermissionPolicy.INSTANCE.ensureDefaults(writableDatabase);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("role_key", "CASHIER");
                        contentValues.put("permission_key", PermissionPolicy.SYNC_MANAGE);
                        contentValues.put("allowed", (Integer) 1);
                        Unit unit = Unit.INSTANCE;
                        writableDatabase.insertWithOnConflict("role_permissions", null, contentValues, 5);
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("id", account.getId());
                        contentValues2.put("username", account.getUsername());
                        contentValues2.put("display_name", account.getDisplayName());
                        contentValues2.put("password_hash", account.getPasswordHash());
                        contentValues2.put("password_salt", account.getPasswordSalt());
                        contentValues2.put("role_key", "CASHIER");
                        contentValues2.put("is_active", (Integer) 1);
                        contentValues2.put("must_change_password", (Integer) 0);
                        contentValues2.put("created_at", strTimestamp);
                        contentValues2.put("updated_at", strTimestamp);
                        Unit unit2 = Unit.INSTANCE;
                        writableDatabase.insertOrThrow("users", null, contentValues2);
                        ContentValues contentValues3 = new ContentValues();
                        contentValues3.put("id", UUID.randomUUID().toString());
                        contentValues3.put("user_id", account.getId());
                        contentValues3.put("action", "PAIRING_ACCOUNT_IMPORTED");
                        contentValues3.put("entity_type", "USER");
                        contentValues3.put("entity_id", account.getId());
                        contentValues3.put("created_at", strTimestamp);
                        Unit unit3 = Unit.INSTANCE;
                        writableDatabase.insertOrThrow("audit_log", null, contentValues3);
                        writableDatabase.setTransactionSuccessful();
                        String id2 = account.getId();
                        writableDatabase.endTransaction();
                        return id2;
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            CloseableKt.closeFinally(cursorRawQuery2, th);
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    try {
                        throw th3;
                    } catch (Throwable th4) {
                        CloseableKt.closeFinally(cursorRawQuery, th3);
                        throw th4;
                    }
                }
            } catch (Throwable th5) {
                writableDatabase.endTransaction();
                throw th5;
            }
        } catch (SQLiteConstraintException unused) {
            throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u0625\u0646\u0634\u0627\u0621 \u062c\u0644\u0633\u0629 \u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0648\u062b\u0642 \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a");
        }
    }

    public final List<ManagedUserSummary> listUsers(String actorUserId) throws IOException {
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        PermissionPolicy.INSTANCE.require(readableDatabase, actorUserId, PermissionPolicy.USERS_MANAGE);
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = readableDatabase.rawQuery("SELECT id,username,display_name,role_key,is_active,must_change_password FROM users ORDER BY created_at,id", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            while (cursor.moveToNext()) {
                String string = cursor.getString(0);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String string2 = cursor.getString(1);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                String string3 = cursor.getString(2);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                String string4 = cursor.getString(3);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                arrayList.add(new ManagedUserSummary(string, string2, string3, string4, cursor.getInt(4) == 1, cursor.getInt(5) == 1));
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

    public final void setUserActive(String actorUserId, String targetUserId, boolean active) {
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(targetUserId, "targetUserId");
        if (StringsKt.isBlank(targetUserId)) {
            throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u0645\u0637\u0644\u0648\u0628".toString());
        }
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        writableDatabase.beginTransaction();
        try {
            PermissionPolicy.INSTANCE.require(writableDatabase, actorUserId, PermissionPolicy.USERS_MANAGE);
            if (Intrinsics.areEqual(actorUserId, targetUserId) && !active) {
                throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0625\u064a\u0642\u0627\u0641 \u062d\u0633\u0627\u0628\u0643 \u0623\u062b\u0646\u0627\u0621 \u0627\u0633\u062a\u062e\u062f\u0627\u0645\u0647\u061b \u0633\u062c\u0651\u0644 \u0627\u0644\u062e\u0631\u0648\u062c \u0645\u0646 \u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0622\u062e\u0631 \u0623\u0648\u0644\u0627\u064b".toString());
            }
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT username,role_key,is_active FROM users WHERE id=?", new String[]{targetUserId});
            try {
                Cursor cursor = cursorRawQuery;
                if (!cursor.moveToFirst()) {
                    throw new IllegalArgumentException("\u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
                }
                Triple triple = new Triple(cursor.getString(0), cursor.getString(1), Boolean.valueOf(cursor.getInt(2) == 1));
                CloseableKt.closeFinally(cursorRawQuery, null);
                if (Intrinsics.areEqual(triple.getSecond(), "ADMIN") && ((Boolean) triple.getThird()).booleanValue() && !active) {
                    Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT COUNT(*) FROM users WHERE role_key='ADMIN' AND is_active=1 AND id<>?", new String[]{targetUserId});
                    try {
                        Cursor cursor2 = cursorRawQuery2;
                        cursor2.moveToFirst();
                        int i = cursor2.getInt(0);
                        CloseableKt.closeFinally(cursorRawQuery2, null);
                        if (i <= 0) {
                            throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0625\u064a\u0642\u0627\u0641 \u0622\u062e\u0631 \u062d\u0633\u0627\u0628 \u0645\u062f\u064a\u0631 \u0646\u0634\u0637".toString());
                        }
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            CloseableKt.closeFinally(cursorRawQuery2, th);
                            throw th2;
                        }
                    }
                }
                if (((Boolean) triple.getThird()).booleanValue() != active) {
                    String strTimestamp = timestamp();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("is_active", Integer.valueOf(active ? 1 : 0));
                    contentValues.put("updated_at", strTimestamp);
                    Unit unit = Unit.INSTANCE;
                    if (writableDatabase.update("users", contentValues, "id=?", new String[]{targetUserId}) != 1) {
                        throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062d\u062f\u064a\u062b \u062d\u0627\u0644\u0629 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645".toString());
                    }
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("id", UUID.randomUUID().toString());
                    contentValues2.put("user_id", actorUserId);
                    contentValues2.put("action", active ? "USER_ACTIVATED" : "USER_DEACTIVATED");
                    contentValues2.put("entity_type", "USER");
                    contentValues2.put("entity_id", targetUserId);
                    contentValues2.put("before_json", new JSONObject().put("is_active", ((Boolean) triple.getThird()).booleanValue()).toString());
                    contentValues2.put("after_json", new JSONObject().put("is_active", active).toString());
                    contentValues2.put("created_at", strTimestamp);
                    Unit unit2 = Unit.INSTANCE;
                    writableDatabase.insertOrThrow("audit_log", null, contentValues2);
                }
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
            } catch (Throwable th3) {
                try {
                    throw th3;
                } catch (Throwable th4) {
                    CloseableKt.closeFinally(cursorRawQuery, th3);
                    throw th4;
                }
            }
        } catch (Throwable th5) {
            writableDatabase.endTransaction();
            throw th5;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String createUser(String actorRole, String actorUserId, String username, String displayName, String password, String roleKey) {
        Intrinsics.checkNotNullParameter(actorRole, "actorRole");
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(roleKey, "roleKey");
        requireManager(actorRole);
        PermissionPolicy permissionPolicy = PermissionPolicy.INSTANCE;
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        permissionPolicy.require(writableDatabase, actorUserId, PermissionPolicy.USERS_MANAGE);
        if (!SetsKt.setOf((Object[]) new String[]{"ADMIN", "CASHIER"}).contains(roleKey)) {
            throw new IllegalArgumentException("\u0635\u0644\u0627\u062d\u064a\u0629 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
        }
        return createUserInternal(username, displayName, password, roleKey, actorUserId, true);
    }

    public final void requireManager(String roleKey) {
        Intrinsics.checkNotNullParameter(roleKey, "roleKey");
        if (!Intrinsics.areEqual(roleKey, "ADMIN")) {
            throw new IllegalArgumentException("\u0647\u0630\u0627 \u0627\u0644\u0625\u062c\u0631\u0627\u0621 \u0645\u062a\u0627\u062d \u0644\u0644\u0645\u062f\u064a\u0631 \u0641\u0642\u0637".toString());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void requirePermission(String actorUserId, String permissionKey) {
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(permissionKey, "permissionKey");
        PermissionPolicy permissionPolicy = PermissionPolicy.INSTANCE;
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        permissionPolicy.require(readableDatabase, actorUserId, permissionKey);
    }

    private final void requireActiveManager(String userId) throws IOException {
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery("SELECT 1 FROM users WHERE id=? AND role_key='ADMIN' AND is_active=1 AND must_change_password=0", new String[]{userId});
        try {
            boolean zMoveToFirst = cursorRawQuery.moveToFirst();
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (!zMoveToFirst) {
                throw new IllegalArgumentException("\u064a\u062c\u0628 \u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062f\u062e\u0648\u0644 \u0628\u062d\u0633\u0627\u0628 \u0645\u062f\u064a\u0631 \u0646\u0634\u0637 \u0644\u062a\u0646\u0641\u064a\u0630 \u0647\u0630\u0627 \u0627\u0644\u0625\u062c\u0631\u0627\u0621".toString());
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

    private final String createUserInternal(String username, String displayName, String password, String roleKey, String actorUserId, boolean mustChangePassword) {
        String string = StringsKt.trim((CharSequence) username).toString();
        String string2 = StringsKt.trim((CharSequence) displayName).toString();
        if (string.length() < 3) {
            throw new IllegalArgumentException("\u0627\u0633\u0645 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u064a\u062c\u0628 \u0623\u0644\u0627 \u064a\u0642\u0644 \u0639\u0646 3 \u0623\u062d\u0631\u0641".toString());
        }
        if (string2.length() <= 0) {
            throw new IllegalArgumentException("\u0627\u0633\u0645 \u0627\u0644\u0639\u0631\u0636 \u0645\u0637\u0644\u0648\u0628".toString());
        }
        boolean zIsStrongPassword = false;
        if (!mustChangePassword || actorUserId != null) {
            zIsStrongPassword = isStrongPassword(password);
        } else if (password.length() >= 4) {
            zIsStrongPassword = true;
        }
        if (!zIsStrongPassword) {
            throw new IllegalArgumentException("\u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631 \u064a\u062c\u0628 \u0623\u0646 \u062a\u062a\u0643\u0648\u0646 \u0645\u0646 10 \u0623\u062d\u0631\u0641 \u0639\u0644\u0649 \u0627\u0644\u0623\u0642\u0644 \u0648\u062a\u062a\u0636\u0645\u0646 \u062d\u0631\u0641\u0627\u064b \u0648\u0631\u0642\u0645\u0627\u064b".toString());
        }
        if (!SetsKt.setOf((Object[]) new String[]{"ADMIN", "CASHIER"}).contains(roleKey)) {
            throw new IllegalArgumentException("\u0635\u0644\u0627\u062d\u064a\u0629 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
        }
        byte[] bArr = new byte[16];
        new SecureRandom().nextBytes(bArr);
        String strEncodeToString = Base64.encodeToString(bArr, 2);
        Intrinsics.checkNotNull(strEncodeToString);
        String strEncodeToString2 = Base64.encodeToString(derive(password, strEncodeToString), 2);
        String string3 = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
        String strTimestamp = timestamp();
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        writableDatabase.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", string3);
            contentValues.put("username", string);
            contentValues.put("display_name", string2);
            contentValues.put("password_hash", strEncodeToString2);
            contentValues.put("password_salt", strEncodeToString);
            contentValues.put("role_key", roleKey);
            contentValues.put("is_active", (Integer) 1);
            contentValues.put("must_change_password", Integer.valueOf(mustChangePassword ? 1 : 0));
            contentValues.put("created_at", strTimestamp);
            contentValues.put("updated_at", strTimestamp);
            try {
                writableDatabase.insertOrThrow("users", null, contentValues);
                if (actorUserId != null) {
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("id", UUID.randomUUID().toString());
                    contentValues2.put("user_id", actorUserId);
                    contentValues2.put("action", "CREATE");
                    contentValues2.put("entity_type", "USER");
                    contentValues2.put("entity_id", string3);
                    contentValues2.put("after_json", "{\"username\":\"" + string + "\",\"role\":\"" + roleKey + "\"}");
                    contentValues2.put("created_at", strTimestamp);
                    writableDatabase.insertOrThrow("audit_log", null, contentValues2);
                }
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
                return string3;
            } catch (SQLiteConstraintException unused) {
                throw new IllegalArgumentException("\u0627\u0633\u0645 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u0645\u0633\u062a\u062e\u062f\u0645 \u0645\u0633\u0628\u0642\u0627\u064b");
            }
        } catch (Throwable th) {
            writableDatabase.endTransaction();
            throw th;
        }
    }

    /* JADX INFO: compiled from: AuthRepository.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\nH\u00c6\u0003JO\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\nH\u00c6\u0001J\u0013\u0010\u001e\u001a\u00020\n2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010 \u001a\u00020!H\u00d6\u0001J\t\u0010\"\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006#"}, d2 = {"Lcom/hazem/dailystore/data/AuthRepository$UserCredential;", "", "id", "", "username", "displayName", "hash", "salt", "role", "mustChangePassword", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getId", "()Ljava/lang/String;", "getUsername", "getDisplayName", "getHash", "getSalt", "getRole", "getMustChangePassword", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class UserCredential {
        private final String displayName;
        private final String hash;
        private final String id;
        private final boolean mustChangePassword;
        private final String role;
        private final String salt;
        private final String username;

        public static /* synthetic */ UserCredential copy$default(UserCredential userCredential, String str, String str2, String str3, String str4, String str5, String str6, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                str = userCredential.id;
            }
            if ((i & 2) != 0) {
                str2 = userCredential.username;
            }
            String str7 = str2;
            if ((i & 4) != 0) {
                str3 = userCredential.displayName;
            }
            String str8 = str3;
            if ((i & 8) != 0) {
                str4 = userCredential.hash;
            }
            String str9 = str4;
            if ((i & 16) != 0) {
                str5 = userCredential.salt;
            }
            String str10 = str5;
            if ((i & 32) != 0) {
                str6 = userCredential.role;
            }
            String str11 = str6;
            if ((i & 64) != 0) {
                z = userCredential.mustChangePassword;
            }
            return userCredential.copy(str, str7, str8, str9, str10, str11, z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getUsername() {
            return this.username;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getDisplayName() {
            return this.displayName;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getHash() {
            return this.hash;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getSalt() {
            return this.salt;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getRole() {
            return this.role;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final boolean getMustChangePassword() {
            return this.mustChangePassword;
        }

        public final UserCredential copy(String id, String username, String displayName, String hash, String salt, String role, boolean mustChangePassword) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(username, "username");
            Intrinsics.checkNotNullParameter(displayName, "displayName");
            Intrinsics.checkNotNullParameter(hash, "hash");
            Intrinsics.checkNotNullParameter(salt, "salt");
            Intrinsics.checkNotNullParameter(role, "role");
            return new UserCredential(id, username, displayName, hash, salt, role, mustChangePassword);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof UserCredential)) {
                return false;
            }
            UserCredential userCredential = (UserCredential) other;
            return Intrinsics.areEqual(this.id, userCredential.id) && Intrinsics.areEqual(this.username, userCredential.username) && Intrinsics.areEqual(this.displayName, userCredential.displayName) && Intrinsics.areEqual(this.hash, userCredential.hash) && Intrinsics.areEqual(this.salt, userCredential.salt) && Intrinsics.areEqual(this.role, userCredential.role) && this.mustChangePassword == userCredential.mustChangePassword;
        }

        public int hashCode() {
            return (((((((((((this.id.hashCode() * 31) + this.username.hashCode()) * 31) + this.displayName.hashCode()) * 31) + this.hash.hashCode()) * 31) + this.salt.hashCode()) * 31) + this.role.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.mustChangePassword);
        }

        public String toString() {
            return "UserCredential(id=" + this.id + ", username=" + this.username + ", displayName=" + this.displayName + ", hash=" + this.hash + ", salt=" + this.salt + ", role=" + this.role + ", mustChangePassword=" + this.mustChangePassword + ")";
        }

        public UserCredential(String id, String username, String displayName, String hash, String salt, String role, boolean z) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(username, "username");
            Intrinsics.checkNotNullParameter(displayName, "displayName");
            Intrinsics.checkNotNullParameter(hash, "hash");
            Intrinsics.checkNotNullParameter(salt, "salt");
            Intrinsics.checkNotNullParameter(role, "role");
            this.id = id;
            this.username = username;
            this.displayName = displayName;
            this.hash = hash;
            this.salt = salt;
            this.role = role;
            this.mustChangePassword = z;
        }

        public final String getDisplayName() {
            return this.displayName;
        }

        public final String getHash() {
            return this.hash;
        }

        public final String getId() {
            return this.id;
        }

        public final boolean getMustChangePassword() {
            return this.mustChangePassword;
        }

        public final String getRole() {
            return this.role;
        }

        public final String getSalt() {
            return this.salt;
        }

        public final String getUsername() {
            return this.username;
        }
    }

    private final byte[] derive(String password, String salt) {
        char[] charArray = password.toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "toCharArray(...)");
        PBEKeySpec pBEKeySpec = new PBEKeySpec(charArray, Base64.decode(salt, 2), this.pbkdf2Iterations, 256);
        try {
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(pBEKeySpec).getEncoded();
        } finally {
            pBEKeySpec.clearPassword();
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
