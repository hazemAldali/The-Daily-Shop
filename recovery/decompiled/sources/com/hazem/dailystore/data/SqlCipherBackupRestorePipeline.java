package com.hazem.dailystore.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SqlCipherBackupRestorePipeline.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u001e\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0007H\u0002J\u001e\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u0007J&\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u001a\u00a8\u0006\u001c"}, d2 = {"Lcom/hazem/dailystore/data/SqlCipherBackupRestorePipeline;", "", "<init>", "()V", "createBackup", "", "closedDatabase", "Ljava/io/File;", "databaseKey", "", "password", "", "destination", "Ljava/io/OutputStream;", "stageLegacyPlaintextBackup", "legacyDatabase", "encryptedStagingFile", "validateLegacyPlaintextDatabase", "file", "stageRestore", "source", "Ljava/io/InputStream;", "stagingFile", "commitRestoreToEmptyDevice", "destinationDatabase", "keyVault", "Lcom/hazem/dailystore/data/DatabaseKeyVault;", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SqlCipherBackupRestorePipeline {
    private static final int DATABASE_KEY_BYTES = 32;
    private static final long MAX_DATABASE_BYTES = 536870912;

    public final void createBackup(File closedDatabase, byte[] databaseKey, char[] password, OutputStream destination) throws Throwable {
        Intrinsics.checkNotNullParameter(closedDatabase, "closedDatabase");
        Intrinsics.checkNotNullParameter(databaseKey, "databaseKey");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (!closedDatabase.isFile() || closedDatabase.length() <= 0) {
            throw new IllegalArgumentException("\u0645\u0644\u0641 \u0642\u0627\u0639\u062f\u0629 SQLCipher \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0623\u0648 \u0641\u0627\u0631\u063a".toString());
        }
        if (closedDatabase.length() > MAX_DATABASE_BYTES) {
            throw new IllegalArgumentException("\u062d\u062c\u0645 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u064a\u062a\u062c\u0627\u0648\u0632 \u0627\u0644\u062d\u062f \u0627\u0644\u0645\u062f\u0639\u0648\u0645".toString());
        }
        File file = new File(closedDatabase.getPath() + "-wal");
        if (file.exists() && file.length() != 0) {
            throw new IllegalArgumentException("\u064a\u062c\u0628 \u0625\u063a\u0644\u0627\u0642 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0648\u0625\u062a\u0645\u0627\u0645 WAL checkpoint \u0642\u0628\u0644 \u0627\u0644\u0646\u0633\u062e".toString());
        }
        SqlCipherDatabaseMigrator.validateEncryptedDatabase$default(SqlCipherDatabaseMigrator.INSTANCE, closedDatabase, databaseKey, null, 4, null);
        SqlCipherBackupContainer.INSTANCE.write(closedDatabase, databaseKey, password, destination);
    }

    public final void stageLegacyPlaintextBackup(File legacyDatabase, File encryptedStagingFile, byte[] databaseKey) throws Exception {
        Intrinsics.checkNotNullParameter(legacyDatabase, "legacyDatabase");
        Intrinsics.checkNotNullParameter(encryptedStagingFile, "encryptedStagingFile");
        Intrinsics.checkNotNullParameter(databaseKey, "databaseKey");
        if (legacyDatabase.isFile()) {
            long length = legacyDatabase.length();
            if (1 <= length && length < 536870913) {
                if (databaseKey.length != 32) {
                    throw new IllegalArgumentException("SQLCipher key must be 256 bits".toString());
                }
                if (encryptedStagingFile.exists() && encryptedStagingFile.length() != 0) {
                    throw new IllegalArgumentException("\u0645\u0644\u0641 SQLCipher \u0627\u0644\u0645\u0624\u0642\u062a \u0645\u0648\u062c\u0648\u062f \u0645\u0633\u0628\u0642\u0627\u064b\u061b \u0631\u064f\u0641\u0636\u062a \u0627\u0644\u0643\u062a\u0627\u0628\u0629 \u0641\u0648\u0642\u0647".toString());
                }
                validateLegacyPlaintextDatabase(legacyDatabase);
                SqlCipherDatabaseMigrator.INSTANCE.exportPlainDatabase(legacyDatabase, encryptedStagingFile, databaseKey);
                SqlCipherDatabaseMigrator.validateEncryptedDatabase$default(SqlCipherDatabaseMigrator.INSTANCE, encryptedStagingFile, databaseKey, null, 4, null);
                return;
            }
        }
        throw new IllegalArgumentException("\u0645\u0644\u0641 \u0642\u0627\u0639\u062f\u0629 SQLite \u0627\u0644\u0645\u0633\u062a\u0639\u0627\u062f\u0629 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0623\u0648 \u062a\u062c\u0627\u0648\u0632 \u0627\u0644\u062d\u062c\u0645 \u0627\u0644\u0645\u062f\u0639\u0648\u0645".toString());
    }

    private final void validateLegacyPlaintextDatabase(File file) throws IOException {
        byte[] bArr = new byte[16];
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            if (fileInputStream.read(bArr) == 16) {
                byte[] bytes = "SQLite format 3\u0000".getBytes(Charsets.US_ASCII);
                Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                if (Arrays.equals(bArr, bytes)) {
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(fileInputStream, null);
                    SQLiteDatabase sQLiteDatabaseOpenDatabase = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, 1);
                    try {
                        int version = sQLiteDatabaseOpenDatabase.getVersion();
                        if (1 > version || version >= 34) {
                            throw new IllegalArgumentException("\u0625\u0635\u062f\u0627\u0631 \u0642\u0627\u0639\u062f\u0629 SQLite \u062f\u0627\u062e\u0644 \u0627\u0644\u0646\u0633\u062e\u0629 \u0627\u0644\u0642\u062f\u064a\u0645\u0629 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645".toString());
                        }
                        Cursor cursorRawQuery = sQLiteDatabaseOpenDatabase.rawQuery("PRAGMA integrity_check", null);
                        try {
                            Cursor cursor = cursorRawQuery;
                            if (!cursor.moveToFirst() || !StringsKt.equals(cursor.getString(0), "ok", true)) {
                                throw new IllegalArgumentException("\u0641\u0634\u0644 \u0641\u062d\u0635 \u0633\u0644\u0627\u0645\u0629 \u0642\u0627\u0639\u062f\u0629 SQLite \u0627\u0644\u0642\u062f\u064a\u0645\u0629".toString());
                            }
                            Unit unit2 = Unit.INSTANCE;
                            CloseableKt.closeFinally(cursorRawQuery, null);
                            Cursor cursorRawQuery2 = sQLiteDatabaseOpenDatabase.rawQuery("PRAGMA foreign_key_check", null);
                            try {
                                if (cursorRawQuery2.moveToFirst()) {
                                    throw new IllegalArgumentException("\u062a\u062d\u062a\u0648\u064a \u0642\u0627\u0639\u062f\u0629 SQLite \u0627\u0644\u0642\u062f\u064a\u0645\u0629 \u0639\u0644\u0649 \u0639\u0644\u0627\u0642\u0627\u062a \u063a\u064a\u0631 \u0633\u0644\u064a\u0645\u0629".toString());
                                }
                                Unit unit3 = Unit.INSTANCE;
                                CloseableKt.closeFinally(cursorRawQuery2, null);
                                for (String str : CollectionsKt.listOf((Object[]) new String[]{"users", "products", "app_settings", "sales_invoices"})) {
                                    Cursor cursorRawQuery3 = sQLiteDatabaseOpenDatabase.rawQuery("SELECT 1 FROM sqlite_master WHERE type='table' AND name=?", new String[]{str});
                                    try {
                                        if (!cursorRawQuery3.moveToFirst()) {
                                            throw new IllegalArgumentException(("\u0627\u0644\u0646\u0633\u062e\u0629 \u0627\u0644\u0642\u062f\u064a\u0645\u0629 \u0644\u0627 \u062a\u062d\u062a\u0648\u064a \u0639\u0644\u0649 \u062c\u062f\u0648\u0644 \u0623\u0633\u0627\u0633\u064a: " + str).toString());
                                        }
                                        Unit unit4 = Unit.INSTANCE;
                                        CloseableKt.closeFinally(cursorRawQuery3, null);
                                    } catch (Throwable th) {
                                        try {
                                            throw th;
                                        } catch (Throwable th2) {
                                            CloseableKt.closeFinally(cursorRawQuery3, th);
                                            throw th2;
                                        }
                                    }
                                    sQLiteDatabaseOpenDatabase.close();
                                    throw th;
                                }
                                sQLiteDatabaseOpenDatabase.close();
                                return;
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
                    } catch (Throwable th7) {
                        sQLiteDatabaseOpenDatabase.close();
                        throw th7;
                    }
                }
            }
            throw new IllegalArgumentException("\u0645\u0644\u0641 \u0627\u0644\u0646\u0633\u062e\u0629 \u0627\u0644\u0642\u062f\u064a\u0645\u0629 \u0644\u064a\u0633 \u0642\u0627\u0639\u062f\u0629 SQLite plaintext \u0645\u0639\u0631\u0648\u0641\u0629".toString());
        } catch (Throwable th8) {
            try {
                throw th8;
            } catch (Throwable th9) {
                CloseableKt.closeFinally(fileInputStream, th8);
                throw th9;
            }
        }
    }

    public final byte[] stageRestore(InputStream source, char[] password, File stagingFile) throws Throwable {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(stagingFile, "stagingFile");
        if (stagingFile.exists() && stagingFile.length() != 0) {
            throw new IllegalArgumentException("\u0645\u0644\u0641 \u0627\u0644\u062a\u062c\u0647\u064a\u0632 \u0645\u0648\u062c\u0648\u062f \u0645\u0633\u0628\u0642\u0627\u064b\u061b \u0631\u064f\u0641\u0636\u062a \u0627\u0644\u0643\u062a\u0627\u0628\u0629 \u0641\u0648\u0642\u0647".toString());
        }
        File parentFile = stagingFile.getParentFile();
        if (parentFile == null) {
            throw new IllegalArgumentException("Restore staging file has no parent directory");
        }
        if (!parentFile.isDirectory() && !parentFile.mkdirs()) {
            throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0625\u0646\u0634\u0627\u0621 \u0645\u062c\u0644\u062f \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0627\u0644\u0645\u0624\u0642\u062a".toString());
        }
        byte[] bArrExtract = null;
        try {
            bArrExtract = SqlCipherBackupContainer.INSTANCE.extract(source, stagingFile, password);
            SqlCipherDatabaseMigrator.validateEncryptedDatabase$default(SqlCipherDatabaseMigrator.INSTANCE, stagingFile, bArrExtract, null, 4, null);
            return bArrExtract;
        } catch (Exception e) {
            stagingFile.delete();
            byte[] bArr = bArrExtract;
            if (bArr != null) {
                ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
            }
            throw new IllegalArgumentException("\u0641\u0634\u0644 \u0627\u0644\u062a\u062d\u0642\u0642 \u0645\u0646 \u0627\u0644\u0646\u0633\u062e\u0629 \u0627\u0644\u0645\u0634\u0641\u0631\u0629\u061b \u0644\u0645 \u064a\u062a\u0645 \u0644\u0645\u0633 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u062d\u0627\u0644\u064a\u0629", e);
        }
    }

    public final void commitRestoreToEmptyDevice(File stagingFile, File destinationDatabase, byte[] databaseKey, DatabaseKeyVault keyVault) throws Throwable {
        Intrinsics.checkNotNullParameter(stagingFile, "stagingFile");
        Intrinsics.checkNotNullParameter(destinationDatabase, "destinationDatabase");
        Intrinsics.checkNotNullParameter(databaseKey, "databaseKey");
        Intrinsics.checkNotNullParameter(keyVault, "keyVault");
        if (!stagingFile.isFile() || stagingFile.length() <= 0) {
            throw new IllegalArgumentException("\u0645\u0644\u0641 \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0627\u0644\u0645\u0624\u0642\u062a \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
        }
        if (databaseKey.length != 32) {
            throw new IllegalArgumentException("SQLCipher key must be 256 bits".toString());
        }
        if (destinationDatabase.exists()) {
            throw new IllegalArgumentException("\u062c\u0647\u0627\u0632 \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u064a\u062d\u062a\u0648\u064a \u0639\u0644\u0649 \u0642\u0627\u0639\u062f\u0629 \u0628\u064a\u0627\u0646\u0627\u062a\u061b \u0631\u064f\u0641\u0636 \u0627\u0644\u0627\u0633\u062a\u0628\u062f\u0627\u0644".toString());
        }
        if (!new File(destinationDatabase.getPath() + "-wal").exists()) {
            if (!new File(destinationDatabase.getPath() + "-shm").exists()) {
                if (!new File(destinationDatabase.getPath() + "-journal").exists()) {
                    if (!Intrinsics.areEqual(stagingFile.getCanonicalFile().getParentFile(), destinationDatabase.getCanonicalFile().getParentFile())) {
                        throw new IllegalArgumentException("\u064a\u062c\u0628 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0645\u0644\u0641 \u0627\u0644\u062a\u062c\u0647\u064a\u0632 \u0648\u0627\u0644\u0648\u062c\u0647\u0629 \u0641\u064a \u0627\u0644\u0645\u062c\u0644\u062f \u0646\u0641\u0633\u0647 \u0644\u0636\u0645\u0627\u0646 \u0627\u0644\u0646\u0642\u0644 \u0627\u0644\u0630\u0631\u064a".toString());
                    }
                    SqlCipherDatabaseMigrator.validateEncryptedDatabase$default(SqlCipherDatabaseMigrator.INSTANCE, stagingFile, databaseKey, null, 4, null);
                    keyVault.importDatabaseKey(databaseKey, false);
                    if (!stagingFile.renameTo(destinationDatabase)) {
                        throw new IllegalStateException("\u062a\u0645 \u062d\u0641\u0638 \u0627\u0644\u0645\u0641\u062a\u0627\u062d \u0627\u0644\u0645\u062d\u0644\u064a \u0644\u0643\u0646 \u062a\u0639\u0630\u0631 \u0646\u0642\u0644 \u0645\u0644\u0641 \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629\u061b \u0623\u0639\u062f \u0627\u0644\u0645\u062d\u0627\u0648\u0644\u0629 \u062f\u0648\u0646 \u062d\u0630\u0641 \u0645\u0644\u0641 \u0627\u0644\u062a\u062c\u0647\u064a\u0632".toString());
                    }
                    if (new File(destinationDatabase.getPath() + "-wal").exists()) {
                        throw new IllegalStateException("\u0638\u0647\u0631 \u0645\u0644\u0641 WAL \u063a\u064a\u0631 \u0645\u062a\u0648\u0642\u0639 \u0628\u0639\u062f \u0646\u0642\u0644 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a".toString());
                    }
                    return;
                }
            }
        }
        throw new IllegalArgumentException("\u062a\u0648\u062c\u062f \u0645\u0644\u0641\u0627\u062a \u062c\u0627\u0646\u0628\u064a\u0629 \u0644\u0642\u0627\u0639\u062f\u0629 \u0633\u0627\u0628\u0642\u0629\u061b \u0631\u064f\u0641\u0636\u062a \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0627\u0644\u0622\u0645\u0646\u0629".toString());
    }
}
