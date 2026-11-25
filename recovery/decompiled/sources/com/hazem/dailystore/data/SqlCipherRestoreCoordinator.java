package com.hazem.dailystore.data;

import java.io.File;
import java.io.InputStream;
import java.security.MessageDigest;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SqlCipherRestoreCoordinator.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\b\u0000\u0018\u0000 !2\u00020\u0001:\u0002 !B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J&\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ&\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eJ\u0018\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0018\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\tH\u0002J\u0010\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/hazem/dailystore/data/SqlCipherRestoreCoordinator;", "", "journal", "Lcom/hazem/dailystore/data/SqlCipherRestoreJournal;", "<init>", "(Lcom/hazem/dailystore/data/SqlCipherRestoreJournal;)V", "commitToEmptyDevice", "", "stagingFile", "Ljava/io/File;", "destinationDatabase", "databaseKey", "", "keyVault", "Lcom/hazem/dailystore/data/DatabaseKeyVault;", "resumeFromBackup", "Lcom/hazem/dailystore/data/SqlCipherRestoreCoordinator$RecoveryResult;", "source", "Ljava/io/InputStream;", "password", "", "databaseDirectory", "recover", "resolveChild", "directory", "name", "", "validateSameDirectory", "first", "second", "requireNoSidecars", "database", "RecoveryResult", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SqlCipherRestoreCoordinator {
    private static final int DATABASE_KEY_BYTES = 32;
    private final SqlCipherRestoreJournal journal;

    /* JADX INFO: compiled from: SqlCipherRestoreCoordinator.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/hazem/dailystore/data/SqlCipherRestoreCoordinator$RecoveryResult;", "", "<init>", "(Ljava/lang/String;I)V", "NOTHING_TO_RECOVER", "WAITING_FOR_KEY", "RESTORE_COMPLETED", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public enum RecoveryResult {
        NOTHING_TO_RECOVER,
        WAITING_FOR_KEY,
        RESTORE_COMPLETED;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<RecoveryResult> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: compiled from: SqlCipherRestoreCoordinator.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SqlCipherRestoreJournal.Phase.values().length];
            try {
                iArr[SqlCipherRestoreJournal.Phase.STAGED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[SqlCipherRestoreJournal.Phase.KEY_IMPORTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[SqlCipherRestoreJournal.Phase.DATABASE_COMMITTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public SqlCipherRestoreCoordinator(SqlCipherRestoreJournal journal) {
        Intrinsics.checkNotNullParameter(journal, "journal");
        this.journal = journal;
    }

    public final void commitToEmptyDevice(File stagingFile, File destinationDatabase, byte[] databaseKey, DatabaseKeyVault keyVault) throws Throwable {
        Intrinsics.checkNotNullParameter(stagingFile, "stagingFile");
        Intrinsics.checkNotNullParameter(destinationDatabase, "destinationDatabase");
        Intrinsics.checkNotNullParameter(databaseKey, "databaseKey");
        Intrinsics.checkNotNullParameter(keyVault, "keyVault");
        if (databaseKey.length != 32) {
            throw new IllegalArgumentException("SQLCipher key must be 256 bits".toString());
        }
        validateSameDirectory(stagingFile, destinationDatabase);
        if (!stagingFile.isFile() || stagingFile.length() <= 0) {
            throw new IllegalArgumentException("\u0645\u0644\u0641 \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0627\u0644\u0645\u0624\u0642\u062a \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
        }
        if (destinationDatabase.exists()) {
            throw new IllegalArgumentException("\u062c\u0647\u0627\u0632 \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u064a\u062d\u062a\u0648\u064a \u0639\u0644\u0649 \u0642\u0627\u0639\u062f\u0629 \u0628\u064a\u0627\u0646\u0627\u062a\u061b \u0631\u064f\u0641\u0636 \u0627\u0644\u0627\u0633\u062a\u0628\u062f\u0627\u0644".toString());
        }
        requireNoSidecars(destinationDatabase);
        SqlCipherDatabaseMigrator.validateEncryptedDatabase$default(SqlCipherDatabaseMigrator.INSTANCE, stagingFile, databaseKey, null, 4, null);
        this.journal.begin(stagingFile, destinationDatabase);
        keyVault.importDatabaseKey(databaseKey, false);
        this.journal.advance(SqlCipherRestoreJournal.Phase.STAGED, SqlCipherRestoreJournal.Phase.KEY_IMPORTED);
        if (!stagingFile.renameTo(destinationDatabase)) {
            throw new IllegalStateException("\u062a\u0645 \u062d\u0641\u0638 \u0627\u0644\u0645\u0641\u062a\u0627\u062d \u0627\u0644\u0645\u062d\u0644\u064a \u0644\u0643\u0646 \u062a\u0639\u0630\u0631 \u0646\u0642\u0644 \u0645\u0644\u0641 \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629\u061b \u0623\u0639\u062f \u0627\u0644\u0645\u062d\u0627\u0648\u0644\u0629 \u062f\u0648\u0646 \u062d\u0630\u0641 \u0645\u0644\u0641 \u0627\u0644\u062a\u062c\u0647\u064a\u0632".toString());
        }
        SqlCipherDatabaseMigrator.validateEncryptedDatabase$default(SqlCipherDatabaseMigrator.INSTANCE, destinationDatabase, databaseKey, null, 4, null);
        this.journal.advance(SqlCipherRestoreJournal.Phase.KEY_IMPORTED, SqlCipherRestoreJournal.Phase.DATABASE_COMMITTED);
        this.journal.clear();
    }

    public final RecoveryResult resumeFromBackup(InputStream source, char[] password, File databaseDirectory, DatabaseKeyVault keyVault) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(databaseDirectory, "databaseDirectory");
        Intrinsics.checkNotNullParameter(keyVault, "keyVault");
        SqlCipherRestoreJournal.Record record = this.journal.read();
        if (record == null) {
            return RecoveryResult.NOTHING_TO_RECOVER;
        }
        File fileResolveChild = resolveChild(databaseDirectory, record.getDestinationName());
        byte[] bArrRecoverDatabaseKey = SqlCipherBackupContainer.INSTANCE.recoverDatabaseKey(source, password);
        try {
            if (fileResolveChild.exists()) {
                byte[] importedDatabaseKeyForRestoreRecovery$app_release = keyVault.getImportedDatabaseKeyForRestoreRecovery$app_release();
                if (importedDatabaseKeyForRestoreRecovery$app_release == null) {
                    throw new IllegalStateException("\u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0645\u0648\u062c\u0648\u062f\u0629 \u0644\u0643\u0646 \u0645\u0641\u062a\u0627\u062d\u0647\u0627 \u0627\u0644\u0645\u062d\u0644\u064a \u0645\u0641\u0642\u0648\u062f\u061b \u0623\u0648\u0642\u0641 \u0627\u0644\u0639\u0645\u0644\u064a\u0629");
                }
                try {
                    if (!MessageDigest.isEqual(importedDatabaseKeyForRestoreRecovery$app_release, bArrRecoverDatabaseKey)) {
                        throw new IllegalStateException("\u0645\u0641\u062a\u0627\u062d \u0627\u0644\u0646\u0633\u062e\u0629 \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u0627\u0644\u0645\u0641\u062a\u0627\u062d \u0627\u0644\u0645\u062d\u0644\u064a\u061b \u0631\u064f\u0641\u0636 \u0627\u0644\u0627\u0633\u062a\u0628\u062f\u0627\u0644".toString());
                    }
                    ArraysKt.fill$default(importedDatabaseKeyForRestoreRecovery$app_release, (byte) 0, 0, 0, 6, (Object) null);
                } catch (Throwable th) {
                    ArraysKt.fill$default(importedDatabaseKeyForRestoreRecovery$app_release, (byte) 0, 0, 0, 6, (Object) null);
                    throw th;
                }
            } else {
                keyVault.importDatabaseKey(bArrRecoverDatabaseKey, false);
            }
            RecoveryResult recoveryResultRecover = recover(databaseDirectory, keyVault);
            ArraysKt.fill$default(bArrRecoverDatabaseKey, (byte) 0, 0, 0, 6, (Object) null);
            return recoveryResultRecover;
        } catch (Throwable th2) {
            ArraysKt.fill$default(bArrRecoverDatabaseKey, (byte) 0, 0, 0, 6, (Object) null);
            throw th2;
        }
    }

    public final RecoveryResult recover(File databaseDirectory, DatabaseKeyVault keyVault) {
        Intrinsics.checkNotNullParameter(databaseDirectory, "databaseDirectory");
        Intrinsics.checkNotNullParameter(keyVault, "keyVault");
        SqlCipherRestoreJournal.Record record = this.journal.read();
        if (record == null) {
            return RecoveryResult.NOTHING_TO_RECOVER;
        }
        if (!databaseDirectory.isDirectory()) {
            throw new IllegalArgumentException("Restore database directory is unavailable".toString());
        }
        File fileResolveChild = resolveChild(databaseDirectory, record.getStagingName());
        File fileResolveChild2 = resolveChild(databaseDirectory, record.getDestinationName());
        validateSameDirectory(fileResolveChild, fileResolveChild2);
        byte[] importedDatabaseKeyForRestoreRecovery$app_release = keyVault.getImportedDatabaseKeyForRestoreRecovery$app_release();
        if (importedDatabaseKeyForRestoreRecovery$app_release == null) {
            return RecoveryResult.WAITING_FOR_KEY;
        }
        try {
            int i = WhenMappings.$EnumSwitchMapping$0[record.getPhase().ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        throw new NoWhenBranchMatchedException();
                    }
                    if (!fileResolveChild2.isFile() || fileResolveChild2.length() <= 0) {
                        throw new IllegalStateException("\u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0627\u0644\u0645\u0639\u062a\u0645\u062f\u0629 \u0645\u0641\u0642\u0648\u062f\u0629\u061b \u0627\u062d\u062a\u0641\u0638 \u0628\u0627\u0644\u0645\u0644\u0641\u0627\u062a \u0648\u0644\u0627 \u062a\u062d\u0630\u0641\u0647\u0627".toString());
                    }
                    SqlCipherDatabaseMigrator.validateEncryptedDatabase$default(SqlCipherDatabaseMigrator.INSTANCE, fileResolveChild2, importedDatabaseKeyForRestoreRecovery$app_release, null, 4, null);
                    if (fileResolveChild.exists() && !fileResolveChild.delete()) {
                        throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u062a\u0646\u0638\u064a\u0641 \u0645\u0644\u0641 \u0627\u0644\u062a\u062c\u0647\u064a\u0632 \u0628\u0639\u062f \u0627\u0644\u062a\u062d\u0642\u0642".toString());
                    }
                    this.journal.clear();
                    RecoveryResult recoveryResult = RecoveryResult.RESTORE_COMPLETED;
                    ArraysKt.fill$default(importedDatabaseKeyForRestoreRecovery$app_release, (byte) 0, 0, 0, 6, (Object) null);
                    return recoveryResult;
                }
            } else {
                if (!fileResolveChild.isFile() || fileResolveChild.length() <= 0) {
                    throw new IllegalStateException("\u0645\u0644\u0641 \u0627\u0644\u062a\u062c\u0647\u064a\u0632 \u0645\u0641\u0642\u0648\u062f\u061b \u0627\u062d\u062a\u0641\u0638 \u0628\u0627\u0644\u0633\u062c\u0644 \u0648\u0627\u0644\u0645\u0644\u0641\u0627\u062a \u0648\u0644\u0627 \u062a\u0646\u0634\u0626 \u0642\u0627\u0639\u062f\u0629 \u0628\u062f\u064a\u0644\u0629".toString());
                }
                SqlCipherDatabaseMigrator.validateEncryptedDatabase$default(SqlCipherDatabaseMigrator.INSTANCE, fileResolveChild, importedDatabaseKeyForRestoreRecovery$app_release, null, 4, null);
                keyVault.importDatabaseKey(importedDatabaseKeyForRestoreRecovery$app_release, false);
                this.journal.advance(SqlCipherRestoreJournal.Phase.STAGED, SqlCipherRestoreJournal.Phase.KEY_IMPORTED);
            }
            if (fileResolveChild2.exists()) {
                SqlCipherDatabaseMigrator.validateEncryptedDatabase$default(SqlCipherDatabaseMigrator.INSTANCE, fileResolveChild2, importedDatabaseKeyForRestoreRecovery$app_release, null, 4, null);
            } else {
                if (!fileResolveChild.isFile() || fileResolveChild.length() <= 0) {
                    throw new IllegalStateException("\u0645\u0644\u0641 \u0627\u0644\u062a\u062c\u0647\u064a\u0632 \u0645\u0641\u0642\u0648\u062f\u061b \u0627\u062d\u062a\u0641\u0638 \u0628\u0627\u0644\u0633\u062c\u0644 \u0648\u0627\u0644\u0645\u0644\u0641\u0627\u062a \u0648\u0644\u0627 \u062a\u062d\u0630\u0641\u0647\u0627".toString());
                }
                requireNoSidecars(fileResolveChild2);
                SqlCipherDatabaseMigrator.validateEncryptedDatabase$default(SqlCipherDatabaseMigrator.INSTANCE, fileResolveChild, importedDatabaseKeyForRestoreRecovery$app_release, null, 4, null);
                if (!fileResolveChild.renameTo(fileResolveChild2)) {
                    throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0625\u0643\u0645\u0627\u0644 \u0646\u0642\u0644 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629\u061b \u0623\u0639\u062f \u0627\u0644\u0645\u062d\u0627\u0648\u0644\u0629".toString());
                }
                SqlCipherDatabaseMigrator.validateEncryptedDatabase$default(SqlCipherDatabaseMigrator.INSTANCE, fileResolveChild2, importedDatabaseKeyForRestoreRecovery$app_release, null, 4, null);
            }
            this.journal.advance(SqlCipherRestoreJournal.Phase.KEY_IMPORTED, SqlCipherRestoreJournal.Phase.DATABASE_COMMITTED);
            if (fileResolveChild.exists() && !fileResolveChild.delete()) {
                throw new IllegalStateException("\u062a\u0645 \u0627\u0644\u062a\u062d\u0642\u0642 \u0645\u0646 \u0627\u0644\u0642\u0627\u0639\u062f\u0629 \u0644\u0643\u0646 \u062a\u0639\u0630\u0631 \u062a\u0646\u0638\u064a\u0641 \u0645\u0644\u0641 \u0627\u0644\u062a\u062c\u0647\u064a\u0632".toString());
            }
            this.journal.clear();
            RecoveryResult recoveryResult2 = RecoveryResult.RESTORE_COMPLETED;
            ArraysKt.fill$default(importedDatabaseKeyForRestoreRecovery$app_release, (byte) 0, 0, 0, 6, (Object) null);
            return recoveryResult2;
        } catch (Throwable th) {
            ArraysKt.fill$default(importedDatabaseKeyForRestoreRecovery$app_release, (byte) 0, 0, 0, 6, (Object) null);
            throw th;
        }
    }

    private final File resolveChild(File directory, String name) {
        if (StringsKt.isBlank(name) || !Intrinsics.areEqual(new File(name).getName(), name) || Intrinsics.areEqual(name, ".") || Intrinsics.areEqual(name, "..")) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        File file = new File(directory, name);
        if (Intrinsics.areEqual(file.getCanonicalFile().getParentFile(), directory.getCanonicalFile())) {
            return file;
        }
        throw new IllegalArgumentException("Restore journal path escaped the database directory".toString());
    }

    private final void validateSameDirectory(File first, File second) {
        if (first.getCanonicalFile().getParentFile() == null || !Intrinsics.areEqual(first.getCanonicalFile().getParentFile(), second.getCanonicalFile().getParentFile())) {
            throw new IllegalArgumentException("\u064a\u062c\u0628 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0645\u0644\u0641 \u0627\u0644\u062a\u062c\u0647\u064a\u0632 \u0648\u0627\u0644\u0648\u062c\u0647\u0629 \u0641\u064a \u0627\u0644\u0645\u062c\u0644\u062f \u0646\u0641\u0633\u0647".toString());
        }
        if (Intrinsics.areEqual(first.getCanonicalFile(), second.getCanonicalFile())) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    private final void requireNoSidecars(File database) {
        if (!new File(database.getPath() + "-wal").exists()) {
            if (!new File(database.getPath() + "-shm").exists()) {
                if (!new File(database.getPath() + "-journal").exists()) {
                    return;
                }
            }
        }
        throw new IllegalArgumentException("\u062a\u0648\u062c\u062f \u0645\u0644\u0641\u0627\u062a \u062c\u0627\u0646\u0628\u064a\u0629 \u0644\u0642\u0627\u0639\u062f\u0629 \u0633\u0627\u0628\u0642\u0629\u061b \u0631\u064f\u0641\u0636\u062a \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0627\u0644\u0622\u0645\u0646\u0629".toString());
    }
}
