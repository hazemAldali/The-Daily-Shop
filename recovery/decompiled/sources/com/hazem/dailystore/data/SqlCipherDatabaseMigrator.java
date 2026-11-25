package com.hazem.dailystore.data;

import android.database.Cursor;
import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import net.zetetic.database.DatabaseErrorHandler;
import net.zetetic.database.sqlcipher.SQLiteDatabase;
import net.zetetic.database.sqlcipher.SQLiteDatabaseHook;

/* JADX INFO: compiled from: SqlCipherDatabaseMigrator.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u00c0\u0002\u0018\u00002\u00020\u0001:\u0002\u001f B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fJ'\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\u0002\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0018\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J\u0018\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\fH\u0002J\u0018\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\fH\u0002J\f\u0010\u001d\u001a\u00020\u001e*\u00020\fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/hazem/dailystore/data/SqlCipherDatabaseMigrator;", "", "<init>", "()V", "MAX_DATABASE_BYTES", "", "exportPlainDatabase", "", "source", "Ljava/io/File;", "destination", "databaseKey", "", "validateEncryptedDatabase", "file", "sourceVersion", "", "(Ljava/io/File;[BLjava/lang/Integer;)V", "snapshot", "Lcom/hazem/dailystore/data/SqlCipherDatabaseMigrator$DatabaseSnapshot;", "database", "Lnet/zetetic/database/sqlcipher/SQLiteDatabase;", "updateLong", "digest", "Ljava/security/MessageDigest;", "value", "updateBytes", "openSnapshot", "passphrase", "toHex", "", "TableContentDigest", "DatabaseSnapshot", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SqlCipherDatabaseMigrator {
    public static final SqlCipherDatabaseMigrator INSTANCE = new SqlCipherDatabaseMigrator();
    private static final long MAX_DATABASE_BYTES = 536870912;

    private SqlCipherDatabaseMigrator() {
    }

    public final void exportPlainDatabase(File source, File destination, byte[] databaseKey) throws Exception {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(databaseKey, "databaseKey");
        if (source.isFile()) {
            long length = source.length();
            if (1 <= length && length < 536870913) {
                if (databaseKey.length != 32) {
                    throw new IllegalArgumentException("SQLCipher key must be 256 bits".toString());
                }
                if (destination.exists() && destination.length() != 0) {
                    throw new IllegalArgumentException("\u0645\u0644\u0641 \u0627\u0644\u0648\u062c\u0647\u0629 \u0645\u0648\u062c\u0648\u062f \u0645\u0633\u0628\u0642\u0627\u064b\u061b \u0631\u064f\u0641\u0636\u062a \u0627\u0644\u0643\u062a\u0627\u0628\u0629 \u0641\u0648\u0642\u0647".toString());
                }
                File parentFile = destination.getParentFile();
                if (parentFile == null) {
                    throw new IllegalArgumentException("Encrypted destination has no parent directory");
                }
                if (!parentFile.isDirectory() && !parentFile.mkdirs()) {
                    throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0625\u0646\u0634\u0627\u0621 \u0645\u062c\u0644\u062f \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0645\u0634\u0641\u0631\u0629".toString());
                }
                if (!destination.exists() && !destination.createNewFile()) {
                    throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0625\u0646\u0634\u0627\u0621 \u0645\u0644\u0641 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0645\u0634\u0641\u0631\u0629".toString());
                }
                byte[] bytes = toHex(databaseKey).getBytes(Charsets.US_ASCII);
                Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                String absolutePath = destination.getAbsolutePath();
                Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
                String strReplace$default = StringsKt.replace$default(absolutePath, "'", "''", false, 4, (Object) null);
                String strReplace$default2 = StringsKt.replace$default(new String(bytes, Charsets.US_ASCII), "'", "''", false, 4, (Object) null);
                SQLiteDatabase sQLiteDatabase = null;
                try {
                    try {
                        SqlCipherDatabaseRuntime.INSTANCE.loadNativeLibrary();
                        SQLiteDatabase sQLiteDatabaseOpenDatabase = SQLiteDatabase.openDatabase(source.getAbsolutePath(), null, 0);
                        try {
                            int version = sQLiteDatabaseOpenDatabase.getVersion();
                            Intrinsics.checkNotNull(sQLiteDatabaseOpenDatabase);
                            DatabaseSnapshot databaseSnapshotSnapshot = snapshot(sQLiteDatabaseOpenDatabase);
                            sQLiteDatabaseOpenDatabase.execSQL("ATTACH DATABASE '" + strReplace$default + "' AS encrypted KEY '" + strReplace$default2 + "'");
                            try {
                                Cursor cursorRawQuery = sQLiteDatabaseOpenDatabase.rawQuery("SELECT sqlcipher_export('encrypted')", (String[]) null);
                                try {
                                    if (!cursorRawQuery.moveToFirst()) {
                                        throw new IllegalStateException("\u0641\u0634\u0644 \u062a\u0635\u062f\u064a\u0631 \u0645\u062e\u0637\u0637 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a".toString());
                                    }
                                    Unit unit = Unit.INSTANCE;
                                    CloseableKt.closeFinally(cursorRawQuery, null);
                                    sQLiteDatabaseOpenDatabase.execSQL("PRAGMA encrypted.user_version=" + sQLiteDatabaseOpenDatabase.getVersion());
                                    sQLiteDatabaseOpenDatabase.execSQL("DETACH DATABASE encrypted");
                                    if (sQLiteDatabaseOpenDatabase != null) {
                                        sQLiteDatabaseOpenDatabase.close();
                                    }
                                    ArraysKt.fill$default(bytes, (byte) 0, 0, 0, 6, (Object) null);
                                    try {
                                        validateEncryptedDatabase(destination, databaseKey, Integer.valueOf(version));
                                        byte[] bytes2 = toHex(databaseKey).getBytes(Charsets.US_ASCII);
                                        Intrinsics.checkNotNullExpressionValue(bytes2, "getBytes(...)");
                                        if (Intrinsics.areEqual(databaseSnapshotSnapshot, openSnapshot(destination, bytes2))) {
                                            return;
                                        } else {
                                            throw new IllegalStateException("\u064a\u062e\u062a\u0644\u0641 \u0645\u062d\u062a\u0648\u0649 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0623\u0648 \u0645\u062e\u0637\u0637\u0647\u0627 \u0628\u0639\u062f \u0627\u0644\u062a\u0635\u062f\u064a\u0631\u061b \u0631\u064f\u0641\u0636 \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u0645\u0644\u0641".toString());
                                        }
                                    } catch (Exception e) {
                                        destination.delete();
                                        throw e;
                                    }
                                } catch (Throwable th) {
                                    try {
                                        throw th;
                                    } catch (Throwable th2) {
                                        CloseableKt.closeFinally(cursorRawQuery, th);
                                        throw th2;
                                    }
                                }
                            } catch (Throwable th3) {
                                sQLiteDatabaseOpenDatabase.execSQL("DETACH DATABASE encrypted");
                                throw th3;
                            }
                        } catch (Exception e2) {
                            e = e2;
                            sQLiteDatabase = sQLiteDatabaseOpenDatabase;
                            destination.delete();
                            throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u062a\u0635\u062f\u064a\u0631 \u0642\u0627\u0639\u062f\u0629 SQLite \u0625\u0644\u0649 SQLCipher", e);
                        } catch (Throwable th4) {
                            th = th4;
                            sQLiteDatabase = sQLiteDatabaseOpenDatabase;
                            if (sQLiteDatabase != null) {
                                sQLiteDatabase.close();
                            }
                            ArraysKt.fill$default(bytes, (byte) 0, 0, 0, 6, (Object) null);
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            }
        }
        throw new IllegalArgumentException("\u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0645\u0635\u062f\u0631 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629 \u0623\u0648 \u062a\u062c\u0627\u0648\u0632\u062a \u0627\u0644\u062d\u062c\u0645 \u0627\u0644\u0645\u062f\u0639\u0648\u0645".toString());
    }

    public static /* synthetic */ void validateEncryptedDatabase$default(SqlCipherDatabaseMigrator sqlCipherDatabaseMigrator, File file, byte[] bArr, Integer num, int i, Object obj) throws Throwable {
        if ((i & 4) != 0) {
            num = null;
        }
        sqlCipherDatabaseMigrator.validateEncryptedDatabase(file, bArr, num);
    }

    public final void validateEncryptedDatabase(File file, byte[] databaseKey, Integer sourceVersion) throws Throwable {
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(databaseKey, "databaseKey");
        if (file.isFile()) {
            long length = file.length();
            if (1 <= length && length < 536870913) {
                if (databaseKey.length != 32) {
                    throw new IllegalArgumentException("SQLCipher key must be 256 bits".toString());
                }
                byte[] bytes = toHex(databaseKey).getBytes(Charsets.US_ASCII);
                Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                SQLiteDatabase sQLiteDatabase = null;
                try {
                    SqlCipherDatabaseRuntime.INSTANCE.loadNativeLibrary();
                    SQLiteDatabase sQLiteDatabaseOpenDatabase = SQLiteDatabase.openDatabase(file.getAbsolutePath(), bytes, (SQLiteDatabase.CursorFactory) null, 1, (DatabaseErrorHandler) null, (SQLiteDatabaseHook) null);
                    if (sourceVersion != null) {
                        try {
                            if (sQLiteDatabaseOpenDatabase.getVersion() != sourceVersion.intValue()) {
                                throw new IllegalStateException("\u0625\u0635\u062f\u0627\u0631 \u0627\u0644\u0645\u062e\u0637\u0637 \u062a\u063a\u064a\u0631 \u0623\u062b\u0646\u0627\u0621 \u0627\u0644\u062a\u0635\u062f\u064a\u0631".toString());
                            }
                        } catch (Throwable th) {
                            th = th;
                            sQLiteDatabase = sQLiteDatabaseOpenDatabase;
                            if (sQLiteDatabase != null) {
                                sQLiteDatabase.close();
                            }
                            ArraysKt.fill$default(bytes, (byte) 0, 0, 0, 6, (Object) null);
                            throw th;
                        }
                    }
                    Cursor cursorRawQuery = sQLiteDatabaseOpenDatabase.rawQuery("PRAGMA integrity_check", (String[]) null);
                    try {
                        Cursor cursor = cursorRawQuery;
                        if (!cursor.moveToFirst() || !StringsKt.equals(cursor.getString(0), "ok", true)) {
                            throw new IllegalStateException("\u0641\u0634\u0644 \u0641\u062d\u0635 \u0633\u0644\u0627\u0645\u0629 \u0642\u0627\u0639\u062f\u0629 SQLCipher".toString());
                        }
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(cursorRawQuery, null);
                        Cursor cursorRawQuery2 = sQLiteDatabaseOpenDatabase.rawQuery("PRAGMA foreign_key_check", (String[]) null);
                        try {
                            if (cursorRawQuery2.moveToFirst()) {
                                throw new IllegalStateException("\u062a\u0648\u062c\u062f \u0639\u0644\u0627\u0642\u0627\u062a \u063a\u064a\u0631 \u0633\u0644\u064a\u0645\u0629 \u0641\u064a \u0642\u0627\u0639\u062f\u0629 SQLCipher".toString());
                            }
                            Unit unit2 = Unit.INSTANCE;
                            CloseableKt.closeFinally(cursorRawQuery2, null);
                            if (sQLiteDatabaseOpenDatabase != null) {
                                sQLiteDatabaseOpenDatabase.close();
                            }
                            ArraysKt.fill$default(bytes, (byte) 0, 0, 0, 6, (Object) null);
                            return;
                        } catch (Throwable th2) {
                            try {
                                throw th2;
                            } catch (Throwable th3) {
                                CloseableKt.closeFinally(cursorRawQuery2, th2);
                                throw th3;
                            }
                        }
                    } catch (Throwable th4) {
                        try {
                            throw th4;
                        } catch (Throwable th5) {
                            CloseableKt.closeFinally(cursorRawQuery, th4);
                            throw th5;
                        }
                    }
                } catch (Throwable th6) {
                    th = th6;
                }
            }
        }
        throw new IllegalArgumentException("\u0645\u0644\u0641 \u0642\u0627\u0639\u062f\u0629 SQLCipher \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0623\u0648 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
    }

    /* JADX INFO: compiled from: SqlCipherDatabaseMigrator.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0015"}, d2 = {"Lcom/hazem/dailystore/data/SqlCipherDatabaseMigrator$TableContentDigest;", "", "rowCount", "", "sha256", "", "<init>", "(JLjava/lang/String;)V", "getRowCount", "()J", "getSha256", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class TableContentDigest {
        private final long rowCount;
        private final String sha256;

        public static /* synthetic */ TableContentDigest copy$default(TableContentDigest tableContentDigest, long j, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                j = tableContentDigest.rowCount;
            }
            if ((i & 2) != 0) {
                str = tableContentDigest.sha256;
            }
            return tableContentDigest.copy(j, str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getRowCount() {
            return this.rowCount;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSha256() {
            return this.sha256;
        }

        public final TableContentDigest copy(long rowCount, String sha256) {
            Intrinsics.checkNotNullParameter(sha256, "sha256");
            return new TableContentDigest(rowCount, sha256);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TableContentDigest)) {
                return false;
            }
            TableContentDigest tableContentDigest = (TableContentDigest) other;
            return this.rowCount == tableContentDigest.rowCount && Intrinsics.areEqual(this.sha256, tableContentDigest.sha256);
        }

        public int hashCode() {
            return (MainActivity$$ExternalSyntheticBackport0.m(this.rowCount) * 31) + this.sha256.hashCode();
        }

        public String toString() {
            return "TableContentDigest(rowCount=" + this.rowCount + ", sha256=" + this.sha256 + ")";
        }

        public TableContentDigest(long j, String sha256) {
            Intrinsics.checkNotNullParameter(sha256, "sha256");
            this.rowCount = j;
            this.sha256 = sha256;
        }

        public final long getRowCount() {
            return this.rowCount;
        }

        public final String getSha256() {
            return this.sha256;
        }
    }

    /* JADX INFO: compiled from: SqlCipherDatabaseMigrator.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00050\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\u0017\u0010\u0013\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00050\u0005H\u00c6\u0003J\u0015\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\bH\u00c6\u0003JA\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00050\u00052\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\bH\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u0006H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001f\u0010\u0004\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00050\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u001b"}, d2 = {"Lcom/hazem/dailystore/data/SqlCipherDatabaseMigrator$DatabaseSnapshot;", "", "version", "", "schema", "", "", "tableDigests", "", "Lcom/hazem/dailystore/data/SqlCipherDatabaseMigrator$TableContentDigest;", "<init>", "(ILjava/util/List;Ljava/util/Map;)V", "getVersion", "()I", "getSchema", "()Ljava/util/List;", "getTableDigests", "()Ljava/util/Map;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class DatabaseSnapshot {
        private final List<List<String>> schema;
        private final Map<String, TableContentDigest> tableDigests;
        private final int version;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DatabaseSnapshot copy$default(DatabaseSnapshot databaseSnapshot, int i, List list, Map map, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = databaseSnapshot.version;
            }
            if ((i2 & 2) != 0) {
                list = databaseSnapshot.schema;
            }
            if ((i2 & 4) != 0) {
                map = databaseSnapshot.tableDigests;
            }
            return databaseSnapshot.copy(i, list, map);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getVersion() {
            return this.version;
        }

        public final List<List<String>> component2() {
            return this.schema;
        }

        public final Map<String, TableContentDigest> component3() {
            return this.tableDigests;
        }

        public final DatabaseSnapshot copy(int version, List<? extends List<String>> schema, Map<String, TableContentDigest> tableDigests) {
            Intrinsics.checkNotNullParameter(schema, "schema");
            Intrinsics.checkNotNullParameter(tableDigests, "tableDigests");
            return new DatabaseSnapshot(version, schema, tableDigests);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DatabaseSnapshot)) {
                return false;
            }
            DatabaseSnapshot databaseSnapshot = (DatabaseSnapshot) other;
            return this.version == databaseSnapshot.version && Intrinsics.areEqual(this.schema, databaseSnapshot.schema) && Intrinsics.areEqual(this.tableDigests, databaseSnapshot.tableDigests);
        }

        public int hashCode() {
            return (((this.version * 31) + this.schema.hashCode()) * 31) + this.tableDigests.hashCode();
        }

        public String toString() {
            return "DatabaseSnapshot(version=" + this.version + ", schema=" + this.schema + ", tableDigests=" + this.tableDigests + ")";
        }

        /* JADX WARN: Multi-variable type inference failed */
        public DatabaseSnapshot(int i, List<? extends List<String>> schema, Map<String, TableContentDigest> tableDigests) {
            Intrinsics.checkNotNullParameter(schema, "schema");
            Intrinsics.checkNotNullParameter(tableDigests, "tableDigests");
            this.version = i;
            this.schema = schema;
            this.tableDigests = tableDigests;
        }

        public final int getVersion() {
            return this.version;
        }

        public final List<List<String>> getSchema() {
            return this.schema;
        }

        public final Map<String, TableContentDigest> getTableDigests() {
            return this.tableDigests;
        }
    }

    private final DatabaseSnapshot snapshot(SQLiteDatabase database) throws NoSuchAlgorithmException, IOException {
        byte b;
        int i;
        SQLiteDatabase sQLiteDatabase = database;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        String[] strArr = null;
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("SELECT type,name,tbl_name,sql FROM sqlite_master WHERE name NOT LIKE 'sqlite_%' AND type IN ('table','index','trigger','view') ORDER BY type,name", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            while (true) {
                byte b2 = 3;
                byte b3 = 1;
                int i2 = 0;
                if (cursor.moveToNext()) {
                    IntRange intRange = new IntRange(0, 3);
                    ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
                    Iterator<Integer> it = intRange.iterator();
                    while (it.hasNext()) {
                        int iNextInt = ((IntIterator) it).nextInt();
                        arrayList3.add(cursor.isNull(iNextInt) ? null : cursor.getString(iNextInt));
                    }
                    ArrayList arrayList4 = arrayList3;
                    arrayList.add(arrayList4);
                    if (Intrinsics.areEqual(arrayList4.get(0), "table")) {
                        ArrayList arrayList5 = arrayList2;
                        Object obj = arrayList4.get(1);
                        if (obj == null) {
                            throw new IllegalArgumentException("Required value was null.".toString());
                        }
                        arrayList5.add(obj);
                    }
                } else {
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursorRawQuery, null);
                    Cursor cursorRawQuery2 = sQLiteDatabase.rawQuery("SELECT 1 FROM sqlite_master WHERE type='table' AND name='sqlite_sequence' LIMIT 1", (String[]) null);
                    try {
                        boolean zMoveToFirst = cursorRawQuery2.moveToFirst();
                        CloseableKt.closeFinally(cursorRawQuery2, null);
                        if (zMoveToFirst) {
                            arrayList2.add("sqlite_sequence");
                        }
                        LinkedHashMap linkedHashMap = new LinkedHashMap();
                        for (String str : CollectionsKt.sorted(arrayList2)) {
                            String strReplace$default = StringsKt.replace$default(str, "\"", "\"\"", false, 4, (Object) null);
                            ArrayList<String> arrayList6 = new ArrayList();
                            Cursor cursorRawQuery3 = sQLiteDatabase.rawQuery("SELECT * FROM \"" + strReplace$default + "\"", strArr);
                            try {
                                Cursor cursor2 = cursorRawQuery3;
                                while (cursor2.moveToNext()) {
                                    MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                                    int columnCount = cursor2.getColumnCount();
                                    int i3 = i2;
                                    while (i3 < columnCount) {
                                        int type = cursor2.getType(i3);
                                        if (type == 0) {
                                            b = b3;
                                            i = 0;
                                            messageDigest.update((byte) 0);
                                        } else if (type != b3) {
                                            if (type == 2) {
                                                messageDigest.update((byte) 2);
                                                SqlCipherDatabaseMigrator sqlCipherDatabaseMigrator = INSTANCE;
                                                Intrinsics.checkNotNull(messageDigest);
                                                sqlCipherDatabaseMigrator.updateLong(messageDigest, Double.doubleToLongBits(cursor2.getDouble(i3)));
                                            } else if (type == b2) {
                                                messageDigest.update(b2);
                                                SqlCipherDatabaseMigrator sqlCipherDatabaseMigrator2 = INSTANCE;
                                                Intrinsics.checkNotNull(messageDigest);
                                                String string = cursor2.getString(i3);
                                                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                                                byte[] bytes = string.getBytes(Charsets.UTF_8);
                                                Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                                                sqlCipherDatabaseMigrator2.updateBytes(messageDigest, bytes);
                                            } else if (type == 4) {
                                                messageDigest.update((byte) 4);
                                                SqlCipherDatabaseMigrator sqlCipherDatabaseMigrator3 = INSTANCE;
                                                Intrinsics.checkNotNull(messageDigest);
                                                byte[] blob = cursor2.getBlob(i3);
                                                Intrinsics.checkNotNullExpressionValue(blob, "getBlob(...)");
                                                sqlCipherDatabaseMigrator3.updateBytes(messageDigest, blob);
                                            } else {
                                                throw new IllegalStateException("\u0646\u0648\u0639 \u0628\u064a\u0627\u0646\u0627\u062a SQLite \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645 \u0641\u064a \u0645\u0642\u0627\u0631\u0646\u0629 \u0627\u0644\u062a\u0631\u062d\u064a\u0644".toString());
                                            }
                                            i = 0;
                                            b = 1;
                                        } else {
                                            b = b3;
                                            messageDigest.update(b);
                                            SqlCipherDatabaseMigrator sqlCipherDatabaseMigrator4 = INSTANCE;
                                            Intrinsics.checkNotNull(messageDigest);
                                            sqlCipherDatabaseMigrator4.updateLong(messageDigest, cursor2.getLong(i3));
                                            i = 0;
                                        }
                                        i3++;
                                        b3 = b;
                                        arrayList = arrayList;
                                        b2 = 3;
                                        i2 = i;
                                    }
                                    byte b4 = b3;
                                    SqlCipherDatabaseMigrator sqlCipherDatabaseMigrator5 = INSTANCE;
                                    byte[] bArrDigest = messageDigest.digest();
                                    Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
                                    arrayList6.add(sqlCipherDatabaseMigrator5.toHex(bArrDigest));
                                    b3 = b4;
                                    arrayList = arrayList;
                                    b2 = 3;
                                    i2 = i2;
                                }
                                byte b5 = b3;
                                int i4 = i2;
                                ArrayList arrayList7 = arrayList;
                                Unit unit2 = Unit.INSTANCE;
                                CloseableKt.closeFinally(cursorRawQuery3, null);
                                CollectionsKt.sort(arrayList6);
                                MessageDigest messageDigest2 = MessageDigest.getInstance("SHA-256");
                                SqlCipherDatabaseMigrator sqlCipherDatabaseMigrator6 = INSTANCE;
                                Intrinsics.checkNotNull(messageDigest2);
                                sqlCipherDatabaseMigrator6.updateLong(messageDigest2, arrayList6.size());
                                for (String str2 : arrayList6) {
                                    SqlCipherDatabaseMigrator sqlCipherDatabaseMigrator7 = INSTANCE;
                                    byte[] bytes2 = str2.getBytes(Charsets.US_ASCII);
                                    Intrinsics.checkNotNullExpressionValue(bytes2, "getBytes(...)");
                                    sqlCipherDatabaseMigrator7.updateBytes(messageDigest2, bytes2);
                                }
                                long size = arrayList6.size();
                                SqlCipherDatabaseMigrator sqlCipherDatabaseMigrator8 = INSTANCE;
                                byte[] bArrDigest2 = messageDigest2.digest();
                                Intrinsics.checkNotNullExpressionValue(bArrDigest2, "digest(...)");
                                linkedHashMap.put(str, new TableContentDigest(size, sqlCipherDatabaseMigrator8.toHex(bArrDigest2)));
                                strArr = null;
                                b3 = b5;
                                arrayList = arrayList7;
                                b2 = 3;
                                i2 = i4;
                                sQLiteDatabase = database;
                            } catch (Throwable th) {
                                try {
                                    throw th;
                                } catch (Throwable th2) {
                                    CloseableKt.closeFinally(cursorRawQuery3, th);
                                    throw th2;
                                }
                            }
                        }
                        return new DatabaseSnapshot(database.getVersion(), arrayList, linkedHashMap);
                    } catch (Throwable th3) {
                        try {
                            throw th3;
                        } catch (Throwable th4) {
                            CloseableKt.closeFinally(cursorRawQuery2, th3);
                            throw th4;
                        }
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

    private final void updateLong(MessageDigest digest, long value) {
        digest.update(ByteBuffer.allocate(8).putLong(value).array());
    }

    private final void updateBytes(MessageDigest digest, byte[] value) {
        digest.update(ByteBuffer.allocate(4).putInt(value.length).array());
        digest.update(value);
    }

    private final DatabaseSnapshot openSnapshot(File file, byte[] passphrase) {
        SQLiteDatabase sQLiteDatabaseOpenDatabase = null;
        try {
            sQLiteDatabaseOpenDatabase = SQLiteDatabase.openDatabase(file.getAbsolutePath(), passphrase, (SQLiteDatabase.CursorFactory) null, 1, (DatabaseErrorHandler) null, (SQLiteDatabaseHook) null);
            Intrinsics.checkNotNull(sQLiteDatabaseOpenDatabase);
            return snapshot(sQLiteDatabaseOpenDatabase);
        } finally {
            if (sQLiteDatabaseOpenDatabase != null) {
                sQLiteDatabaseOpenDatabase.close();
            }
            ArraysKt.fill$default(passphrase, (byte) 0, 0, 0, 6, (Object) null);
        }
    }

    private final String toHex(byte[] bArr) {
        return ArraysKt.joinToString$default(bArr, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.hazem.dailystore.data.SqlCipherDatabaseMigrator$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SqlCipherDatabaseMigrator.toHex$lambda$21(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence toHex$lambda$21(byte b) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }
}
