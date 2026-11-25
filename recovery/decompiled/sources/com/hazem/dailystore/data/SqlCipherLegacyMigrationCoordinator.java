package com.hazem.dailystore.data;

import android.content.Context;
import android.util.AtomicFile;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SqlCipherLegacyMigrationCoordinator.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0000\u0018\u0000 22\u00020\u0001:\u0005./012B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u00a2\u0006\u0004\b\n\u0010\u000bJ$\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\t0\u0019J\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0017J\u0018\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J \u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010\"\u001a\u00020\t2\u0006\u0010#\u001a\u00020\u0010H\u0002J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0010H\u0002J\u0018\u0010'\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u00102\u0006\u0010(\u001a\u00020\u0005H\u0002J!\u0010)\u001a\u0002H*\"\u0004\b\u0000\u0010*2\f\u0010+\u001a\b\u0012\u0004\u0012\u0002H*0\u0019H\u0002\u00a2\u0006\u0002\u0010,J!\u0010-\u001a\u0002H*\"\u0004\b\u0000\u0010*2\f\u0010+\u001a\b\u0012\u0004\u0012\u0002H*0\u0019H\u0002\u00a2\u0006\u0002\u0010,R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0018\u0010\f\u001a\n \r*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00063"}, d2 = {"Lcom/hazem/dailystore/data/SqlCipherLegacyMigrationCoordinator;", "", "context", "Landroid/content/Context;", "journalFileName", "", "afterCheckpoint", "Lkotlin/Function1;", "Lcom/hazem/dailystore/data/SqlCipherLegacyMigrationCoordinator$Checkpoint;", "", "<init>", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "appContext", "kotlin.jvm.PlatformType", "Landroid/content/Context;", "journalFile", "Ljava/io/File;", "journal", "Lcom/hazem/dailystore/data/SqlCipherLegacyMigrationCoordinator$MigrationJournal;", "migrate", "Lcom/hazem/dailystore/data/SqlCipherLegacyMigrationCoordinator$MigrationResult;", "legacyDatabase", "keyVault", "Lcom/hazem/dailystore/data/DatabaseKeyVault;", "closeLegacyDatabase", "Lkotlin/Function0;", "recover", "Lcom/hazem/dailystore/data/SqlCipherLegacyMigrationCoordinator$RecoveryResult;", "recoverLocked", "finishCommittedMigration", "live", "rollback", "databaseKey", "", "ensureClosedLegacyDatabaseHasNoPendingWal", "database", "isPlainSqlite", "", "file", "sibling", "suffix", "withMigrationLock", "T", "block", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withFileLock", "MigrationResult", "RecoveryResult", "Checkpoint", "MigrationJournal", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SqlCipherLegacyMigrationCoordinator {
    private static final String DEFAULT_JOURNAL_NAME = "the_daily_shop_sqlcipher_migration.journal";
    private static final String LOCK_FILE_NAME = "the_daily_shop_sqlcipher_migration.lock";
    private static final String ROLLBACK_SUFFIX = ".sqlite-migration-rollback";
    private static final byte[] SQLITE_HEADER;
    private static final String STAGING_SUFFIX = ".sqlcipher-migration-staging";
    private final Function1<Checkpoint, Unit> afterCheckpoint;
    private final Context appContext;
    private final MigrationJournal journal;
    private final File journalFile;

    /* JADX INFO: compiled from: SqlCipherLegacyMigrationCoordinator.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b\u00a8\u0006\f"}, d2 = {"Lcom/hazem/dailystore/data/SqlCipherLegacyMigrationCoordinator$Checkpoint;", "", "<init>", "(Ljava/lang/String;I)V", "ENCRYPTED_STAGE_VERIFIED", "JOURNAL_PREPARED", "LEGACY_DATABASE_RENAMED", "LEGACY_BACKUP_RECORDED", "ENCRYPTED_DATABASE_RENAMED", "ENCRYPTED_SWAP_RECORDED", "ENCRYPTED_DATABASE_VERIFIED", "VERIFICATION_RECORDED", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public enum Checkpoint {
        ENCRYPTED_STAGE_VERIFIED,
        JOURNAL_PREPARED,
        LEGACY_DATABASE_RENAMED,
        LEGACY_BACKUP_RECORDED,
        ENCRYPTED_DATABASE_RENAMED,
        ENCRYPTED_SWAP_RECORDED,
        ENCRYPTED_DATABASE_VERIFIED,
        VERIFICATION_RECORDED;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<Checkpoint> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: compiled from: SqlCipherLegacyMigrationCoordinator.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/hazem/dailystore/data/SqlCipherLegacyMigrationCoordinator$MigrationResult;", "", "<init>", "(Ljava/lang/String;I)V", "MIGRATED", "ALREADY_ENCRYPTED", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public enum MigrationResult {
        MIGRATED,
        ALREADY_ENCRYPTED;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<MigrationResult> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: compiled from: SqlCipherLegacyMigrationCoordinator.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/hazem/dailystore/data/SqlCipherLegacyMigrationCoordinator$RecoveryResult;", "", "<init>", "(Ljava/lang/String;I)V", "NOTHING_TO_RECOVER", "MIGRATION_COMPLETED", "MIGRATION_ROLLED_BACK", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public enum RecoveryResult {
        NOTHING_TO_RECOVER,
        MIGRATION_COMPLETED,
        MIGRATION_ROLLED_BACK;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<RecoveryResult> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SqlCipherLegacyMigrationCoordinator(Context context, String journalFileName, Function1<? super Checkpoint, Unit> afterCheckpoint) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(journalFileName, "journalFileName");
        Intrinsics.checkNotNullParameter(afterCheckpoint, "afterCheckpoint");
        this.afterCheckpoint = afterCheckpoint;
        Context applicationContext = context.getApplicationContext();
        this.appContext = applicationContext;
        File file = new File(applicationContext.getNoBackupFilesDir(), journalFileName);
        this.journalFile = file;
        this.journal = new MigrationJournal(file);
        if (StringsKt.isBlank(journalFileName) || !Intrinsics.areEqual(new File(journalFileName).getName(), journalFileName)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        File noBackupFilesDir = applicationContext.getNoBackupFilesDir();
        if (!noBackupFilesDir.isDirectory() && !noBackupFilesDir.mkdirs()) {
            throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0625\u0646\u0634\u0627\u0621 \u0645\u062c\u0644\u062f \u0645\u0644\u0641\u0627\u062a \u0627\u0644\u062a\u0637\u0628\u064a\u0642 \u063a\u064a\u0631 \u0627\u0644\u0642\u0627\u0628\u0644\u0629 \u0644\u0644\u0646\u0633\u062e \u0627\u0644\u0627\u062d\u062a\u064a\u0627\u0637\u064a".toString());
        }
    }

    public /* synthetic */ SqlCipherLegacyMigrationCoordinator(Context context, String str, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? "the_daily_shop_sqlcipher_migration.journal" : str, (i & 4) != 0 ? new Function1() { // from class: com.hazem.dailystore.data.SqlCipherLegacyMigrationCoordinator$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SqlCipherLegacyMigrationCoordinator._init_$lambda$0((SqlCipherLegacyMigrationCoordinator.Checkpoint) obj);
            }
        } : function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit _init_$lambda$0(Checkpoint it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    public final synchronized MigrationResult migrate(final File legacyDatabase, final DatabaseKeyVault keyVault, final Function0<Unit> closeLegacyDatabase) {
        Intrinsics.checkNotNullParameter(legacyDatabase, "legacyDatabase");
        Intrinsics.checkNotNullParameter(keyVault, "keyVault");
        Intrinsics.checkNotNullParameter(closeLegacyDatabase, "closeLegacyDatabase");
        return (MigrationResult) withMigrationLock(new Function0() { // from class: com.hazem.dailystore.data.SqlCipherLegacyMigrationCoordinator$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SqlCipherLegacyMigrationCoordinator.migrate$lambda$6(this.f$0, legacyDatabase, keyVault, closeLegacyDatabase);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3 */
    public static final MigrationResult migrate$lambda$6(SqlCipherLegacyMigrationCoordinator sqlCipherLegacyMigrationCoordinator, File file, DatabaseKeyVault databaseKeyVault, Function0 function0) throws Throwable {
        if (sqlCipherLegacyMigrationCoordinator.recoverLocked(file, databaseKeyVault) == RecoveryResult.MIGRATION_COMPLETED) {
            return MigrationResult.MIGRATED;
        }
        if (!file.isFile() || file.length() <= 0) {
            throw new IllegalArgumentException("\u0642\u0627\u0639\u062f\u0629 SQLite \u0627\u0644\u0642\u062f\u064a\u0645\u0629 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629 \u0623\u0648 \u0641\u0627\u0631\u063a\u0629".toString());
        }
        File fileSibling = sqlCipherLegacyMigrationCoordinator.sibling(file, STAGING_SUFFIX);
        File fileSibling2 = sqlCipherLegacyMigrationCoordinator.sibling(file, ROLLBACK_SUFFIX);
        if (fileSibling.exists() || fileSibling2.exists()) {
            throw new IllegalStateException("\u062a\u0648\u062c\u062f \u0645\u0644\u0641\u0627\u062a \u062a\u0631\u062d\u064a\u0644 \u0633\u0627\u0628\u0642\u0629 \u0628\u0644\u0627 \u0633\u062c\u0644 \u0642\u0627\u0628\u0644 \u0644\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629\u061b \u0627\u062d\u062a\u0641\u0638 \u0628\u0647\u0627 \u0648\u0623\u0648\u0642\u0641 \u0627\u0644\u062a\u0631\u062d\u064a\u0644".toString());
        }
        ?? r6 = 1;
        if (!sqlCipherLegacyMigrationCoordinator.isPlainSqlite(file)) {
            byte[] orCreateDatabaseKey = databaseKeyVault.getOrCreateDatabaseKey(true);
            try {
                SqlCipherDatabaseMigrator.validateEncryptedDatabase$default(SqlCipherDatabaseMigrator.INSTANCE, file, orCreateDatabaseKey, null, 4, null);
                return MigrationResult.ALREADY_ENCRYPTED;
            } finally {
                ArraysKt.fill$default(orCreateDatabaseKey, (byte) 0, 0, 0, 6, (Object) null);
            }
        }
        byte[] orCreateMigrationKey$app_release = databaseKeyVault.getOrCreateMigrationKey$app_release(file);
        try {
            try {
                try {
                    function0.invoke();
                    sqlCipherLegacyMigrationCoordinator.ensureClosedLegacyDatabaseHasNoPendingWal(file);
                    SqlCipherDatabaseMigrator.INSTANCE.exportPlainDatabase(file, fileSibling, orCreateMigrationKey$app_release);
                    SqlCipherDatabaseMigrator sqlCipherDatabaseMigrator = SqlCipherDatabaseMigrator.INSTANCE;
                    SqlCipherDatabaseMigrator.validateEncryptedDatabase$default(sqlCipherDatabaseMigrator, fileSibling, orCreateMigrationKey$app_release, null, 4, null);
                    sqlCipherLegacyMigrationCoordinator.afterCheckpoint.invoke(Checkpoint.ENCRYPTED_STAGE_VERIFIED);
                    sqlCipherLegacyMigrationCoordinator.journal.begin(fileSibling, file, fileSibling2);
                    try {
                        sqlCipherLegacyMigrationCoordinator.afterCheckpoint.invoke(Checkpoint.JOURNAL_PREPARED);
                        try {
                            if (!file.renameTo(fileSibling2)) {
                                throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u062d\u0641\u0638 \u0642\u0627\u0639\u062f\u0629 SQLite \u0627\u0644\u0623\u0635\u0644\u064a\u0629 \u0643\u0646\u0642\u0637\u0629 \u062a\u0631\u0627\u062c\u0639".toString());
                            }
                            sqlCipherLegacyMigrationCoordinator.afterCheckpoint.invoke(Checkpoint.LEGACY_DATABASE_RENAMED);
                            sqlCipherLegacyMigrationCoordinator.journal.advance(MigrationJournal.Phase.PREPARED, MigrationJournal.Phase.LEGACY_BACKED_UP);
                            sqlCipherLegacyMigrationCoordinator.afterCheckpoint.invoke(Checkpoint.LEGACY_BACKUP_RECORDED);
                            if (!fileSibling.renameTo(file)) {
                                throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u062a\u062b\u0628\u064a\u062a \u0642\u0627\u0639\u062f\u0629 SQLCipher\u061b \u0633\u062a\u062a\u0645 \u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0627\u0644\u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0623\u0635\u0644\u064a\u0629 \u0639\u0646\u062f \u0627\u0644\u062a\u0639\u0627\u0641\u064a".toString());
                            }
                            sqlCipherLegacyMigrationCoordinator.afterCheckpoint.invoke(Checkpoint.ENCRYPTED_DATABASE_RENAMED);
                            sqlCipherLegacyMigrationCoordinator.journal.advance(MigrationJournal.Phase.LEGACY_BACKED_UP, MigrationJournal.Phase.ENCRYPTED_INSTALLED);
                            sqlCipherLegacyMigrationCoordinator.afterCheckpoint.invoke(Checkpoint.ENCRYPTED_SWAP_RECORDED);
                            SqlCipherDatabaseMigrator.validateEncryptedDatabase$default(SqlCipherDatabaseMigrator.INSTANCE, file, orCreateMigrationKey$app_release, null, 4, null);
                            sqlCipherLegacyMigrationCoordinator.afterCheckpoint.invoke(Checkpoint.ENCRYPTED_DATABASE_VERIFIED);
                            sqlCipherLegacyMigrationCoordinator.journal.advance(MigrationJournal.Phase.ENCRYPTED_INSTALLED, MigrationJournal.Phase.VERIFIED);
                            sqlCipherLegacyMigrationCoordinator.afterCheckpoint.invoke(Checkpoint.VERIFICATION_RECORDED);
                            sqlCipherLegacyMigrationCoordinator.finishCommittedMigration(file, fileSibling2, orCreateMigrationKey$app_release);
                            MigrationResult migrationResult = MigrationResult.MIGRATED;
                            ArraysKt.fill$default(orCreateMigrationKey$app_release, (byte) 0, 0, 0, 6, (Object) null);
                            return migrationResult;
                        } catch (Exception e) {
                            e = e;
                            r6 = sqlCipherDatabaseMigrator;
                        }
                    } catch (Exception e2) {
                        e = e2;
                    }
                } catch (Throwable th) {
                    th = th;
                    ArraysKt.fill$default(orCreateMigrationKey$app_release, (byte) 0, 0, 0, 6, (Object) null);
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                r6 = 0;
            }
        } catch (Throwable th2) {
            th = th2;
            ArraysKt.fill$default(orCreateMigrationKey$app_release, (byte) 0, 0, 0, 6, (Object) null);
            throw th;
        }
        if (r6 == 0) {
            fileSibling.delete();
        }
        throw e;
    }

    public final synchronized RecoveryResult recover(final File legacyDatabase, final DatabaseKeyVault keyVault) {
        Intrinsics.checkNotNullParameter(legacyDatabase, "legacyDatabase");
        Intrinsics.checkNotNullParameter(keyVault, "keyVault");
        return (RecoveryResult) withMigrationLock(new Function0() { // from class: com.hazem.dailystore.data.SqlCipherLegacyMigrationCoordinator$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.recoverLocked(legacyDatabase, keyVault);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final RecoveryResult recoverLocked(File legacyDatabase, DatabaseKeyVault keyVault) {
        Object objM381constructorimpl;
        Object objM381constructorimpl2;
        Object objM381constructorimpl3;
        File fileSibling = sibling(legacyDatabase, STAGING_SUFFIX);
        File fileSibling2 = sibling(legacyDatabase, ROLLBACK_SUFFIX);
        MigrationJournal.Record record = this.journal.read();
        if (record == null) {
            if (fileSibling.exists() || fileSibling2.exists()) {
                throw new IllegalStateException("\u062a\u0648\u062c\u062f \u0645\u0644\u0641\u0627\u062a \u062a\u0631\u062d\u064a\u0644 \u064a\u062a\u064a\u0645\u0629 \u0628\u0644\u0627 \u0633\u062c\u0644\u061b \u0644\u0645 \u064a\u062a\u0645 \u062d\u0630\u0641 \u0623\u064a \u0645\u0644\u0641".toString());
            }
            return RecoveryResult.NOTHING_TO_RECOVER;
        }
        if (!Intrinsics.areEqual(record.getLiveName(), legacyDatabase.getName()) || !Intrinsics.areEqual(record.getStagingName(), fileSibling.getName()) || !Intrinsics.areEqual(record.getRollbackName(), fileSibling2.getName())) {
            throw new IllegalArgumentException("\u0633\u062c\u0644 \u0627\u0644\u062a\u0631\u062d\u064a\u0644 \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0645\u0637\u0644\u0648\u0628\u0629\u061b \u0623\u0648\u0642\u0641 \u0627\u0644\u062a\u0639\u0627\u0641\u064a".toString());
        }
        byte[] importedDatabaseKeyForRestoreRecovery$app_release = keyVault.getImportedDatabaseKeyForRestoreRecovery$app_release();
        if (importedDatabaseKeyForRestoreRecovery$app_release == null) {
            throw new IllegalStateException("\u0645\u0641\u062a\u0627\u062d \u0627\u0644\u062a\u0631\u062d\u064a\u0644 \u0645\u0641\u0642\u0648\u062f\u061b \u0627\u062d\u062a\u0641\u0638 \u0628\u062c\u0645\u064a\u0639 \u0627\u0644\u0645\u0644\u0641\u0627\u062a \u0648\u0623\u0648\u0642\u0641 \u0627\u0644\u062a\u0639\u0627\u0641\u064a");
        }
        try {
            if (legacyDatabase.isFile()) {
                try {
                    Result.Companion companion = Result.INSTANCE;
                    SqlCipherLegacyMigrationCoordinator sqlCipherLegacyMigrationCoordinator = this;
                    SqlCipherDatabaseMigrator.validateEncryptedDatabase$default(SqlCipherDatabaseMigrator.INSTANCE, legacyDatabase, importedDatabaseKeyForRestoreRecovery$app_release, null, 4, null);
                    objM381constructorimpl = Result.m381constructorimpl(Unit.INSTANCE);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m388isSuccessimpl(objM381constructorimpl)) {
                    if (fileSibling2.exists() && !isPlainSqlite(fileSibling2)) {
                        throw new IllegalStateException("\u0646\u0633\u062e\u0629 \u0627\u0644\u062a\u0631\u0627\u062c\u0639 \u0644\u064a\u0633\u062a \u0642\u0627\u0639\u062f\u0629 SQLite \u0623\u0635\u0644\u064a\u0629 \u0645\u0639\u0631\u0648\u0641\u0629\u061b \u0644\u0646 \u064a\u062a\u0645 \u062d\u0630\u0641\u0647\u0627".toString());
                    }
                    if (fileSibling.exists()) {
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            SqlCipherLegacyMigrationCoordinator sqlCipherLegacyMigrationCoordinator2 = this;
                            SqlCipherDatabaseMigrator.validateEncryptedDatabase$default(SqlCipherDatabaseMigrator.INSTANCE, fileSibling, importedDatabaseKeyForRestoreRecovery$app_release, null, 4, null);
                            objM381constructorimpl2 = Result.m381constructorimpl(Unit.INSTANCE);
                        } catch (Throwable th2) {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM381constructorimpl2 = Result.m381constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (!Result.m388isSuccessimpl(objM381constructorimpl2)) {
                            throw new IllegalStateException("\u0645\u0644\u0641 \u0627\u0644\u062a\u062c\u0647\u064a\u0632 \u0627\u0644\u0645\u062a\u0628\u0642\u064a \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u061b \u0627\u062d\u062a\u0641\u0638 \u0628\u0647 \u0648\u0623\u0648\u0642\u0641 \u0627\u0644\u062a\u0646\u0638\u064a\u0641".toString());
                        }
                        if (!fileSibling.delete()) {
                            throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u062a\u0646\u0638\u064a\u0641 \u0645\u0644\u0641 \u0627\u0644\u062a\u062c\u0647\u064a\u0632 \u0628\u0639\u062f \u0646\u062c\u0627\u062d \u0627\u0644\u062a\u0631\u062d\u064a\u0644".toString());
                        }
                    }
                    if (fileSibling2.exists() && !fileSibling2.delete()) {
                        throw new IllegalStateException("\u0627\u0644\u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0645\u0634\u0641\u0631\u0629 \u0635\u0627\u0644\u062d\u0629 \u0644\u0643\u0646 \u062a\u0639\u0630\u0631 \u062d\u0630\u0641 \u0646\u0633\u062e\u0629 SQLite \u0627\u0644\u0627\u062d\u062a\u064a\u0627\u0637\u064a\u0629\u061b \u0633\u064a\u064f\u0633\u062a\u0643\u0645\u0644 \u0627\u0644\u062a\u0646\u0638\u064a\u0641 \u0644\u0627\u062d\u0642\u0627\u064b".toString());
                    }
                    this.journal.clear();
                    RecoveryResult recoveryResult = RecoveryResult.MIGRATION_COMPLETED;
                    ArraysKt.fill$default(importedDatabaseKeyForRestoreRecovery$app_release, (byte) 0, 0, 0, 6, (Object) null);
                    return recoveryResult;
                }
                ArraysKt.fill$default(importedDatabaseKeyForRestoreRecovery$app_release, (byte) 0, 0, 0, 6, (Object) null);
                throw th;
            }
            if (fileSibling2.exists()) {
                if (!isPlainSqlite(fileSibling2)) {
                    throw new IllegalStateException("\u0646\u0633\u062e\u0629 \u0627\u0644\u062a\u0631\u0627\u062c\u0639 \u0644\u0627 \u062a\u062d\u0645\u0644 \u062a\u0631\u0648\u064a\u0633\u0629 SQLite \u0627\u0644\u0623\u0635\u0644\u064a\u0629\u061b \u0644\u0645 \u064a\u062a\u0645 \u0627\u0633\u062a\u0628\u062f\u0627\u0644 \u0623\u064a \u0645\u0644\u0641".toString());
                }
                if (legacyDatabase.exists()) {
                    if (fileSibling.exists()) {
                        throw new IllegalStateException("\u0645\u0644\u0641 \u0627\u0644\u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u062d\u0627\u0644\u064a \u0648\u0645\u0644\u0641 \u0627\u0644\u062a\u062c\u0647\u064a\u0632 \u0645\u0648\u062c\u0648\u062f\u0627\u0646 \u0645\u0639 \u0646\u0633\u062e\u0629 \u0627\u0644\u062a\u0631\u0627\u062c\u0639\u061b \u0623\u0648\u0642\u0641 \u0627\u0644\u062a\u0639\u0627\u0641\u064a \u062f\u0648\u0646 \u062d\u0630\u0641".toString());
                    }
                    if (!legacyDatabase.renameTo(fileSibling)) {
                        throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0639\u0632\u0644 \u0645\u0644\u0641 \u0627\u0644\u0642\u0627\u0639\u062f\u0629 \u063a\u064a\u0631 \u0627\u0644\u0645\u0639\u062a\u0645\u062f \u0642\u0628\u0644 \u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0627\u0644\u0623\u0635\u0644".toString());
                    }
                }
                if (!fileSibling2.renameTo(legacyDatabase)) {
                    throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0625\u0639\u0627\u062f\u0629 \u0642\u0627\u0639\u062f\u0629 SQLite \u0627\u0644\u0623\u0635\u0644\u064a\u0629 \u0625\u0644\u0649 \u0645\u0633\u0627\u0631\u0647\u0627".toString());
                }
                if (!isPlainSqlite(legacyDatabase)) {
                    throw new IllegalStateException("\u0627\u0633\u062a\u0639\u064a\u062f \u0645\u0644\u0641 \u0627\u0644\u0642\u0627\u0639\u062f\u0629 \u0644\u0643\u0646 \u062a\u0631\u0648\u064a\u0633\u0629 SQLite \u063a\u064a\u0631 \u0633\u0644\u064a\u0645\u0629\u061b \u0627\u062d\u062a\u0641\u0638 \u0628\u0627\u0644\u0645\u0644\u0641".toString());
                }
                if (fileSibling.exists() && !fileSibling.delete()) {
                    throw new IllegalStateException("\u062a\u0645\u062a \u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0627\u0644\u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0623\u0635\u0644\u064a\u0629 \u0644\u0643\u0646 \u062a\u0639\u0630\u0631 \u062a\u0646\u0638\u064a\u0641 \u0645\u0644\u0641 \u0627\u0644\u062a\u062c\u0647\u064a\u0632".toString());
                }
                this.journal.clear();
                RecoveryResult recoveryResult2 = RecoveryResult.MIGRATION_ROLLED_BACK;
                ArraysKt.fill$default(importedDatabaseKeyForRestoreRecovery$app_release, (byte) 0, 0, 0, 6, (Object) null);
                return recoveryResult2;
            }
            if (!legacyDatabase.isFile() || !isPlainSqlite(legacyDatabase)) {
                throw new IllegalStateException("\u0644\u0627 \u062a\u0648\u062c\u062f \u0646\u0633\u062e\u0629 \u062a\u0631\u0627\u062c\u0639 \u0648\u0644\u0627 \u064a\u0645\u0643\u0646 \u0625\u062b\u0628\u0627\u062a \u0633\u0644\u0627\u0645\u0629 \u0642\u0627\u0639\u062f\u0629 SQLite \u0627\u0644\u0623\u0635\u0644\u064a\u0629\u061b \u0623\u0648\u0642\u0641 \u0627\u0644\u062a\u0639\u0627\u0641\u064a".toString());
            }
            if (fileSibling.exists()) {
                try {
                    Result.Companion companion5 = Result.INSTANCE;
                    SqlCipherLegacyMigrationCoordinator sqlCipherLegacyMigrationCoordinator3 = this;
                    SqlCipherDatabaseMigrator.validateEncryptedDatabase$default(SqlCipherDatabaseMigrator.INSTANCE, fileSibling, importedDatabaseKeyForRestoreRecovery$app_release, null, 4, null);
                    objM381constructorimpl3 = Result.m381constructorimpl(Unit.INSTANCE);
                } catch (Throwable th3) {
                    Result.Companion companion6 = Result.INSTANCE;
                    objM381constructorimpl3 = Result.m381constructorimpl(ResultKt.createFailure(th3));
                }
                if (!Result.m388isSuccessimpl(objM381constructorimpl3)) {
                    throw new IllegalStateException("\u0645\u0644\u0641 \u0627\u0644\u062a\u062c\u0647\u064a\u0632 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u061b \u0627\u062d\u062a\u0641\u0638 \u0628\u0647 \u0648\u0623\u0648\u0642\u0641 \u0627\u0644\u062a\u0639\u0627\u0641\u064a".toString());
                }
                if (!fileSibling.delete()) {
                    throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u062a\u0646\u0638\u064a\u0641 \u0645\u0644\u0641 \u0627\u0644\u062a\u062c\u0647\u064a\u0632 \u0628\u0639\u062f \u0627\u0644\u062a\u0631\u0627\u062c\u0639".toString());
                }
            }
            this.journal.clear();
            RecoveryResult recoveryResult3 = RecoveryResult.MIGRATION_ROLLED_BACK;
            ArraysKt.fill$default(importedDatabaseKeyForRestoreRecovery$app_release, (byte) 0, 0, 0, 6, (Object) null);
            return recoveryResult3;
        } catch (Throwable th4) {
            ArraysKt.fill$default(importedDatabaseKeyForRestoreRecovery$app_release, (byte) 0, 0, 0, 6, (Object) null);
            throw th4;
        }
    }

    private final void finishCommittedMigration(File live, File rollback, byte[] databaseKey) throws Throwable {
        SqlCipherDatabaseMigrator.validateEncryptedDatabase$default(SqlCipherDatabaseMigrator.INSTANCE, live, databaseKey, null, 4, null);
        if (!isPlainSqlite(rollback)) {
            throw new IllegalStateException("\u0646\u0633\u062e\u0629 \u0627\u0644\u062a\u0631\u0627\u062c\u0639 \u0627\u0644\u0623\u0635\u0644\u064a\u0629 \u063a\u064a\u0631 \u0633\u0644\u064a\u0645\u0629\u061b \u0644\u0646 \u064a\u062a\u0645 \u062d\u0630\u0641\u0647\u0627".toString());
        }
        if (!rollback.delete()) {
            throw new IllegalStateException("\u0646\u062c\u062d \u0627\u0644\u062a\u0631\u062d\u064a\u0644 \u0644\u0643\u0646 \u062a\u0639\u0630\u0631 \u062d\u0630\u0641 \u0646\u0633\u062e\u0629 \u0627\u0644\u062a\u0631\u0627\u062c\u0639\u061b \u0633\u064a\u064f\u0633\u062a\u0643\u0645\u0644 \u0627\u0644\u062a\u0646\u0638\u064a\u0641 \u0639\u0646\u062f \u0627\u0644\u062a\u0634\u063a\u064a\u0644 \u0627\u0644\u062a\u0627\u0644\u064a".toString());
        }
        this.journal.clear();
    }

    private final void ensureClosedLegacyDatabaseHasNoPendingWal(File database) {
        File file = new File(database.getPath() + "-wal");
        if (file.exists() && file.length() != 0) {
            throw new IllegalStateException("\u064a\u0648\u062c\u062f WAL \u063a\u064a\u0631 \u0645\u0641\u0631\u063a \u0628\u0639\u062f \u0625\u063a\u0644\u0627\u0642 \u0642\u0627\u0639\u062f\u0629 SQLite\u061b \u0623\u064f\u0648\u0642\u0641 \u0627\u0644\u062a\u0631\u062d\u064a\u0644 \u0644\u062d\u0645\u0627\u064a\u0629 \u0627\u0644\u0645\u0639\u0627\u0645\u0644\u0627\u062a".toString());
        }
        for (File file2 : CollectionsKt.listOf((Object[]) new File[]{file, new File(database.getPath() + "-shm"), new File(database.getPath() + "-journal")})) {
            if (file2.exists() && !file2.delete()) {
                throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u062a\u0646\u0638\u064a\u0641 \u0645\u0644\u0641 \u062c\u0627\u0646\u0628\u064a \u0628\u0639\u062f \u0625\u063a\u0644\u0627\u0642 \u0642\u0627\u0639\u062f\u0629 SQLite".toString());
            }
        }
    }

    private final boolean isPlainSqlite(File file) throws IOException {
        boolean z = false;
        if (file.isFile()) {
            long length = file.length();
            byte[] bArr = SQLITE_HEADER;
            if (length >= bArr.length) {
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    int length2 = bArr.length;
                    byte[] bArr2 = new byte[length2];
                    if (fileInputStream.read(bArr2) == length2 && Arrays.equals(bArr2, bArr)) {
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
        return false;
    }

    private final File sibling(File database, String suffix) {
        File parentFile = database.getParentFile();
        if (parentFile == null) {
            throw new IllegalStateException("Database parent directory unavailable".toString());
        }
        return new File(parentFile, database.getName() + suffix);
    }

    private final <T> T withMigrationLock(Function0<? extends T> block) {
        return (T) withFileLock(block);
    }

    private final <T> T withFileLock(Function0<? extends T> block) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(new File(this.appContext.getNoBackupFilesDir(), LOCK_FILE_NAME), "rw");
        try {
            FileLock fileLockLock = randomAccessFile.getChannel().lock();
            try {
                FileLock fileLock = fileLockLock;
                T tInvoke = block.invoke();
                AutoCloseableKt.closeFinally(fileLockLock, null);
                CloseableKt.closeFinally(randomAccessFile, null);
                return tInvoke;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    AutoCloseableKt.closeFinally(fileLockLock, th);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            try {
                throw th3;
            } catch (Throwable th4) {
                CloseableKt.closeFinally(randomAccessFile, th3);
                throw th4;
            }
        }
    }

    /* JADX INFO: compiled from: SqlCipherLegacyMigrationCoordinator.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0002\u0018\u0000 \u001b2\u00020\u0001:\u0003\u0019\u001a\u001bB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0003J\u0016\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fJ\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012J\u0006\u0010\u0013\u001a\u00020\tJ\u0010\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0012H\u0002J\u0010\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/hazem/dailystore/data/SqlCipherLegacyMigrationCoordinator$MigrationJournal;", "", "file", "Ljava/io/File;", "<init>", "(Ljava/io/File;)V", "atomic", "Landroid/util/AtomicFile;", "begin", "", "staging", "live", "rollback", "advance", "expected", "Lcom/hazem/dailystore/data/SqlCipherLegacyMigrationCoordinator$MigrationJournal$Phase;", "next", "read", "Lcom/hazem/dailystore/data/SqlCipherLegacyMigrationCoordinator$MigrationJournal$Record;", "clear", "write", "record", "validateName", "name", "", "Phase", "Record", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final class MigrationJournal {
        private static final int DIGEST_BYTES = 32;
        private static final int VERSION = 1;
        private final AtomicFile atomic;
        private static final byte[] MAGIC = {84, 68, 83, 77, 73, 71, 74, 49};

        /* JADX INFO: compiled from: SqlCipherLegacyMigrationCoordinator.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lcom/hazem/dailystore/data/SqlCipherLegacyMigrationCoordinator$MigrationJournal$Phase;", "", "<init>", "(Ljava/lang/String;I)V", "PREPARED", "LEGACY_BACKED_UP", "ENCRYPTED_INSTALLED", "VERIFIED", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public enum Phase {
            PREPARED,
            LEGACY_BACKED_UP,
            ENCRYPTED_INSTALLED,
            VERIFIED;

            private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

            public static EnumEntries<Phase> getEntries() {
                return $ENTRIES;
            }
        }

        public MigrationJournal(File file) {
            Intrinsics.checkNotNullParameter(file, "file");
            this.atomic = new AtomicFile(file);
        }

        /* JADX INFO: compiled from: SqlCipherLegacyMigrationCoordinator.kt */
        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0005H\u00c6\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r\u00a8\u0006\u001b"}, d2 = {"Lcom/hazem/dailystore/data/SqlCipherLegacyMigrationCoordinator$MigrationJournal$Record;", "", "phase", "Lcom/hazem/dailystore/data/SqlCipherLegacyMigrationCoordinator$MigrationJournal$Phase;", "stagingName", "", "liveName", "rollbackName", "<init>", "(Lcom/hazem/dailystore/data/SqlCipherLegacyMigrationCoordinator$MigrationJournal$Phase;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getPhase", "()Lcom/hazem/dailystore/data/SqlCipherLegacyMigrationCoordinator$MigrationJournal$Phase;", "getStagingName", "()Ljava/lang/String;", "getLiveName", "getRollbackName", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final /* data */ class Record {
            private final String liveName;
            private final Phase phase;
            private final String rollbackName;
            private final String stagingName;

            public static /* synthetic */ Record copy$default(Record record, Phase phase, String str, String str2, String str3, int i, Object obj) {
                if ((i & 1) != 0) {
                    phase = record.phase;
                }
                if ((i & 2) != 0) {
                    str = record.stagingName;
                }
                if ((i & 4) != 0) {
                    str2 = record.liveName;
                }
                if ((i & 8) != 0) {
                    str3 = record.rollbackName;
                }
                return record.copy(phase, str, str2, str3);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final Phase getPhase() {
                return this.phase;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getStagingName() {
                return this.stagingName;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final String getLiveName() {
                return this.liveName;
            }

            /* JADX INFO: renamed from: component4, reason: from getter */
            public final String getRollbackName() {
                return this.rollbackName;
            }

            public final Record copy(Phase phase, String stagingName, String liveName, String rollbackName) {
                Intrinsics.checkNotNullParameter(phase, "phase");
                Intrinsics.checkNotNullParameter(stagingName, "stagingName");
                Intrinsics.checkNotNullParameter(liveName, "liveName");
                Intrinsics.checkNotNullParameter(rollbackName, "rollbackName");
                return new Record(phase, stagingName, liveName, rollbackName);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Record)) {
                    return false;
                }
                Record record = (Record) other;
                return this.phase == record.phase && Intrinsics.areEqual(this.stagingName, record.stagingName) && Intrinsics.areEqual(this.liveName, record.liveName) && Intrinsics.areEqual(this.rollbackName, record.rollbackName);
            }

            public int hashCode() {
                return (((((this.phase.hashCode() * 31) + this.stagingName.hashCode()) * 31) + this.liveName.hashCode()) * 31) + this.rollbackName.hashCode();
            }

            public String toString() {
                return "Record(phase=" + this.phase + ", stagingName=" + this.stagingName + ", liveName=" + this.liveName + ", rollbackName=" + this.rollbackName + ")";
            }

            public Record(Phase phase, String stagingName, String liveName, String rollbackName) {
                Intrinsics.checkNotNullParameter(phase, "phase");
                Intrinsics.checkNotNullParameter(stagingName, "stagingName");
                Intrinsics.checkNotNullParameter(liveName, "liveName");
                Intrinsics.checkNotNullParameter(rollbackName, "rollbackName");
                this.phase = phase;
                this.stagingName = stagingName;
                this.liveName = liveName;
                this.rollbackName = rollbackName;
            }

            public final Phase getPhase() {
                return this.phase;
            }

            public final String getStagingName() {
                return this.stagingName;
            }

            public final String getLiveName() {
                return this.liveName;
            }

            public final String getRollbackName() {
                return this.rollbackName;
            }
        }

        public final synchronized void begin(File staging, File live, File rollback) {
            Intrinsics.checkNotNullParameter(staging, "staging");
            Intrinsics.checkNotNullParameter(live, "live");
            Intrinsics.checkNotNullParameter(rollback, "rollback");
            if (!this.atomic.getBaseFile().exists()) {
                if (!new File(this.atomic.getBaseFile().getPath() + ".bak").exists()) {
                    File parentFile = live.getCanonicalFile().getParentFile();
                    if (parentFile == null || !Intrinsics.areEqual(staging.getCanonicalFile().getParentFile(), parentFile) || !Intrinsics.areEqual(rollback.getCanonicalFile().getParentFile(), parentFile) || Intrinsics.areEqual(staging.getCanonicalFile(), live.getCanonicalFile()) || Intrinsics.areEqual(rollback.getCanonicalFile(), live.getCanonicalFile()) || Intrinsics.areEqual(staging.getCanonicalFile(), rollback.getCanonicalFile())) {
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                    Phase phase = Phase.PREPARED;
                    String name = staging.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                    String name2 = live.getName();
                    Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
                    String name3 = rollback.getName();
                    Intrinsics.checkNotNullExpressionValue(name3, "getName(...)");
                    write(new Record(phase, name, name2, name3));
                }
            }
            throw new IllegalStateException("\u064a\u0648\u062c\u062f \u0633\u062c\u0644 \u062a\u0631\u062d\u064a\u0644 \u0633\u0627\u0628\u0642\u061b \u064a\u062c\u0628 \u0627\u0644\u062a\u0639\u0627\u0641\u064a \u0645\u0646\u0647 \u0623\u0648\u0644\u0627\u064b".toString());
        }

        public final synchronized void advance(Phase expected, Phase next) {
            Intrinsics.checkNotNullParameter(expected, "expected");
            Intrinsics.checkNotNullParameter(next, "next");
            if (next.ordinal() != expected.ordinal() + 1) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            Record record = read();
            if (record == null) {
                throw new IllegalStateException("Migration journal is missing".toString());
            }
            if (record.getPhase() != expected) {
                throw new IllegalStateException("Migration journal phase changed unexpectedly".toString());
            }
            write(Record.copy$default(record, next, null, null, null, 14, null));
        }

        public final synchronized Record read() {
            if (!this.atomic.getBaseFile().exists()) {
                if (!new File(this.atomic.getBaseFile().getPath() + ".bak").exists()) {
                    return null;
                }
            }
            try {
                FileInputStream fileInputStreamOpenRead = this.atomic.openRead();
                try {
                    FileInputStream fileInputStream = fileInputStreamOpenRead;
                    Intrinsics.checkNotNull(fileInputStream);
                    byte[] bytes = ByteStreamsKt.readBytes(fileInputStream);
                    CloseableKt.closeFinally(fileInputStreamOpenRead, null);
                    if (bytes.length <= 32) {
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                    byte[] bArrCopyOfRange = ArraysKt.copyOfRange(bytes, 0, bytes.length - 32);
                    if (!MessageDigest.isEqual(MessageDigest.getInstance("SHA-256").digest(bArrCopyOfRange), ArraysKt.copyOfRange(bytes, bytes.length - 32, bytes.length))) {
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                    DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArrCopyOfRange));
                    byte[] bArr = MAGIC;
                    byte[] bArr2 = new byte[bArr.length];
                    dataInputStream.readFully(bArr2);
                    if (!Arrays.equals(bArr2, bArr) || dataInputStream.readInt() != 1) {
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                    int i = dataInputStream.readInt();
                    if (i < 0 || i >= Phase.values().length) {
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                    ArrayList arrayList = new ArrayList(3);
                    for (int i2 = 0; i2 < 3; i2++) {
                        arrayList.add(dataInputStream.readUTF());
                    }
                    ArrayList arrayList2 = arrayList;
                    if (dataInputStream.read() != -1 || CollectionsKt.toSet(arrayList2).size() != arrayList2.size()) {
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                    Iterator it = arrayList2.iterator();
                    while (it.hasNext()) {
                        validateName((String) it.next());
                    }
                    Phase phase = Phase.values()[i];
                    Object obj = arrayList2.get(0);
                    Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                    Object obj2 = arrayList2.get(1);
                    Intrinsics.checkNotNullExpressionValue(obj2, "get(...)");
                    Object obj3 = arrayList2.get(2);
                    Intrinsics.checkNotNullExpressionValue(obj3, "get(...)");
                    return new Record(phase, (String) obj, (String) obj2, (String) obj3);
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(fileInputStreamOpenRead, th);
                        throw th2;
                    }
                }
            } catch (Exception e) {
                throw new IllegalStateException("\u0633\u062c\u0644 \u062a\u0631\u062d\u064a\u0644 SQLCipher \u062a\u0627\u0644\u0641\u061b \u0627\u062d\u062a\u0641\u0638 \u0628\u0643\u0644 \u0627\u0644\u0645\u0644\u0641\u0627\u062a \u0648\u0623\u0648\u0642\u0641 \u0627\u0644\u062a\u0639\u0627\u0641\u064a", e);
            }
        }

        public final synchronized void clear() {
            this.atomic.delete();
        }

        private final void write(Record record) throws IOException {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                DataOutputStream dataOutputStream2 = dataOutputStream;
                dataOutputStream2.write(MAGIC);
                dataOutputStream2.writeInt(1);
                dataOutputStream2.writeInt(record.getPhase().ordinal());
                dataOutputStream2.writeUTF(record.getStagingName());
                dataOutputStream2.writeUTF(record.getLiveName());
                dataOutputStream2.writeUTF(record.getRollbackName());
                Unit unit = Unit.INSTANCE;
                FileOutputStream fileOutputStreamStartWrite = null;
                CloseableKt.closeFinally(dataOutputStream, null);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byte[] bArrDigest = MessageDigest.getInstance("SHA-256").digest(byteArray);
                try {
                    fileOutputStreamStartWrite = this.atomic.startWrite();
                    fileOutputStreamStartWrite.write(byteArray);
                    fileOutputStreamStartWrite.write(bArrDigest);
                    fileOutputStreamStartWrite.flush();
                    fileOutputStreamStartWrite.getFD().sync();
                    this.atomic.finishWrite(fileOutputStreamStartWrite);
                } catch (Exception e) {
                    if (fileOutputStreamStartWrite != null) {
                        this.atomic.failWrite(fileOutputStreamStartWrite);
                    }
                    throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u062d\u0641\u0638 \u0633\u062c\u0644 \u062a\u0631\u062d\u064a\u0644 SQLCipher", e);
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(dataOutputStream, th);
                    throw th2;
                }
            }
        }

        private final void validateName(String name) {
            String str = name;
            if (StringsKt.isBlank(str) || Intrinsics.areEqual(name, ".") || Intrinsics.areEqual(name, "..") || !Intrinsics.areEqual(new File(name).getName(), name) || StringsKt.contains$default((CharSequence) str, '/', false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, '\\', false, 2, (Object) null)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
        }
    }

    static {
        byte[] bytes = "SQLite format 3\u0000".getBytes(Charsets.US_ASCII);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        SQLITE_HEADER = bytes;
    }
}
