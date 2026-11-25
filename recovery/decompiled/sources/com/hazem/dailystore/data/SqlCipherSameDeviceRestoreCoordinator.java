package com.hazem.dailystore.data;

import android.content.Context;
import android.util.AtomicFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.UUID;
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
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SqlCipherSameDeviceRestoreCoordinator.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0000\u0018\u0000 12\u00020\u0001:\u0003/01B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u00a2\u0006\u0004\b\n\u0010\u000bJ4\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\u001bJ\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0018\u001a\u00020\u0019J(\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u00102\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0010H\u0002J\u0018\u0010$\u001a\u00020\t2\u0006\u0010%\u001a\u00020\"2\u0006\u0010&\u001a\u00020\u0017H\u0002J\u0010\u0010'\u001a\u00020\t2\u0006\u0010(\u001a\u00020\u0010H\u0002J\u0018\u0010)\u001a\u00020\t2\u0006\u0010*\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u0010H\u0002J\u0018\u0010+\u001a\u00020\u00102\u0006\u0010,\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\u0005H\u0002J\u0010\u0010.\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\u0005H\u0002R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0018\u0010\f\u001a\n \r*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00062"}, d2 = {"Lcom/hazem/dailystore/data/SqlCipherSameDeviceRestoreCoordinator;", "", "context", "Landroid/content/Context;", "journalFileName", "", "afterCheckpoint", "Lkotlin/Function1;", "Lcom/hazem/dailystore/data/SqlCipherSameDeviceRestoreCoordinator$Checkpoint;", "", "<init>", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "appContext", "kotlin.jvm.PlatformType", "Landroid/content/Context;", "journalFile", "Ljava/io/File;", "journal", "Lcom/hazem/dailystore/data/SqlCipherSameDeviceRestoreJournal;", "commit", "stagingDatabase", "liveDatabase", "replacementKey", "", "keyVault", "Lcom/hazem/dailystore/data/DatabaseKeyVault;", "closeLiveDatabase", "Lkotlin/Function0;", "recover", "Lcom/hazem/dailystore/data/SqlCipherSameDeviceRestoreCoordinator$RecoveryResult;", "finalizeCommittedRestore", "live", "rollback", "snapshotAtomic", "Landroid/util/AtomicFile;", "snapshot", "writeAtomic", "file", "bytes", "ensureClosedDatabaseHasNoPendingWal", "database", "validateDatabasePair", "staging", "resolveDatabaseChild", "directory", "name", "resolvePrivateChild", "RecoveryResult", "Checkpoint", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SqlCipherSameDeviceRestoreCoordinator {
    private static final int DATABASE_KEY_BYTES = 32;
    private static final String DEFAULT_JOURNAL_NAME = "the_daily_shop_same_device_restore.journal";
    private final Function1<Checkpoint, Unit> afterCheckpoint;
    private final Context appContext;
    private final SqlCipherSameDeviceRestoreJournal journal;
    private final File journalFile;

    /* JADX INFO: compiled from: SqlCipherSameDeviceRestoreCoordinator.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f\u00a8\u0006\r"}, d2 = {"Lcom/hazem/dailystore/data/SqlCipherSameDeviceRestoreCoordinator$Checkpoint;", "", "<init>", "(Ljava/lang/String;I)V", "JOURNAL_PREPARED", "OLD_DATABASE_RENAMED", "OLD_DATABASE_BACKUP_RECORDED", "KEY_REPLACED", "KEY_REPLACEMENT_RECORDED", "NEW_DATABASE_RENAMED", "NEW_DATABASE_SWAP_RECORDED", "NEW_DATABASE_VERIFIED", "VERIFICATION_RECORDED", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public enum Checkpoint {
        JOURNAL_PREPARED,
        OLD_DATABASE_RENAMED,
        OLD_DATABASE_BACKUP_RECORDED,
        KEY_REPLACED,
        KEY_REPLACEMENT_RECORDED,
        NEW_DATABASE_RENAMED,
        NEW_DATABASE_SWAP_RECORDED,
        NEW_DATABASE_VERIFIED,
        VERIFICATION_RECORDED;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<Checkpoint> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: compiled from: SqlCipherSameDeviceRestoreCoordinator.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/hazem/dailystore/data/SqlCipherSameDeviceRestoreCoordinator$RecoveryResult;", "", "<init>", "(Ljava/lang/String;I)V", "NOTHING_TO_RECOVER", "RESTORE_COMPLETED", "RESTORE_ROLLED_BACK", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public enum RecoveryResult {
        NOTHING_TO_RECOVER,
        RESTORE_COMPLETED,
        RESTORE_ROLLED_BACK;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<RecoveryResult> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SqlCipherSameDeviceRestoreCoordinator(Context context, String journalFileName, Function1<? super Checkpoint, Unit> afterCheckpoint) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(journalFileName, "journalFileName");
        Intrinsics.checkNotNullParameter(afterCheckpoint, "afterCheckpoint");
        this.afterCheckpoint = afterCheckpoint;
        Context applicationContext = context.getApplicationContext();
        this.appContext = applicationContext;
        File file = new File(applicationContext.getNoBackupFilesDir(), journalFileName);
        this.journalFile = file;
        this.journal = new SqlCipherSameDeviceRestoreJournal(file);
        if (StringsKt.isBlank(journalFileName) || !Intrinsics.areEqual(new File(journalFileName).getName(), journalFileName)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public /* synthetic */ SqlCipherSameDeviceRestoreCoordinator(Context context, String str, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? "the_daily_shop_same_device_restore.journal" : str, (i & 4) != 0 ? new Function1() { // from class: com.hazem.dailystore.data.SqlCipherSameDeviceRestoreCoordinator$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SqlCipherSameDeviceRestoreCoordinator._init_$lambda$0((SqlCipherSameDeviceRestoreCoordinator.Checkpoint) obj);
            }
        } : function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit _init_$lambda$0(Checkpoint it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    public final synchronized void commit(File stagingDatabase, File liveDatabase, byte[] replacementKey, DatabaseKeyVault keyVault, Function0<Unit> closeLiveDatabase) {
        Intrinsics.checkNotNullParameter(stagingDatabase, "stagingDatabase");
        Intrinsics.checkNotNullParameter(liveDatabase, "liveDatabase");
        Intrinsics.checkNotNullParameter(replacementKey, "replacementKey");
        Intrinsics.checkNotNullParameter(keyVault, "keyVault");
        Intrinsics.checkNotNullParameter(closeLiveDatabase, "closeLiveDatabase");
        if (replacementKey.length != 32) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        validateDatabasePair(stagingDatabase, liveDatabase);
        if (!stagingDatabase.isFile() || stagingDatabase.length() <= 0) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!liveDatabase.isFile() || liveDatabase.length() <= 0) {
            throw new IllegalArgumentException("\u0644\u0627 \u062a\u0648\u062c\u062f \u0642\u0627\u0639\u062f\u0629 \u062d\u0627\u0644\u064a\u0629 \u0635\u0627\u0644\u062d\u0629 \u0644\u062d\u0641\u0638\u0647\u0627 \u0643\u0646\u0642\u0637\u0629 \u062a\u0631\u0627\u062c\u0639".toString());
        }
        File file = new File(liveDatabase.getParentFile(), liveDatabase.getName() + ".sqlcipher-rollback");
        byte[] importedDatabaseKeyForRestoreRecovery$app_release = keyVault.getImportedDatabaseKeyForRestoreRecovery$app_release();
        if (importedDatabaseKeyForRestoreRecovery$app_release == null) {
            throw new IllegalStateException("\u0645\u0641\u062a\u0627\u062d \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u062d\u0627\u0644\u064a\u0629 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f");
        }
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        String strReplace$default = StringsKt.replace$default(string, "-", "", false, 4, (Object) null);
        File file2 = new File(this.appContext.getNoBackupFilesDir(), "tds-same-device-key-" + strReplace$default + ".bin");
        AtomicFile atomicFile = new AtomicFile(file2);
        try {
            if (!file.exists()) {
                if (!new File(file.getPath() + "-wal").exists()) {
                    if (!new File(file.getPath() + "-shm").exists()) {
                        if (!new File(file.getPath() + "-journal").exists()) {
                            SqlCipherDatabaseMigrator.validateEncryptedDatabase$default(SqlCipherDatabaseMigrator.INSTANCE, liveDatabase, importedDatabaseKeyForRestoreRecovery$app_release, null, 4, null);
                            SqlCipherDatabaseMigrator.validateEncryptedDatabase$default(SqlCipherDatabaseMigrator.INSTANCE, stagingDatabase, replacementKey, null, 4, null);
                            closeLiveDatabase.invoke();
                            ensureClosedDatabaseHasNoPendingWal(liveDatabase);
                            ensureClosedDatabaseHasNoPendingWal(stagingDatabase);
                            writeAtomic(atomicFile, keyVault.snapshotWrappedKeyMetadata$app_release());
                            this.journal.begin(stagingDatabase, liveDatabase, file, file2);
                            this.afterCheckpoint.invoke(Checkpoint.JOURNAL_PREPARED);
                            if (!liveDatabase.renameTo(file)) {
                                throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u062d\u0641\u0638 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u062d\u0627\u0644\u064a\u0629 \u0644\u0644\u062a\u0631\u0627\u062c\u0639".toString());
                            }
                            this.afterCheckpoint.invoke(Checkpoint.OLD_DATABASE_RENAMED);
                            this.journal.advance(SqlCipherSameDeviceRestoreJournal.Phase.PREPARED, SqlCipherSameDeviceRestoreJournal.Phase.OLD_DATABASE_BACKED_UP);
                            this.afterCheckpoint.invoke(Checkpoint.OLD_DATABASE_BACKUP_RECORDED);
                            keyVault.replaceDatabaseKeyForRestore$app_release(replacementKey, importedDatabaseKeyForRestoreRecovery$app_release);
                            this.afterCheckpoint.invoke(Checkpoint.KEY_REPLACED);
                            this.journal.advance(SqlCipherSameDeviceRestoreJournal.Phase.OLD_DATABASE_BACKED_UP, SqlCipherSameDeviceRestoreJournal.Phase.KEY_REPLACED);
                            this.afterCheckpoint.invoke(Checkpoint.KEY_REPLACEMENT_RECORDED);
                            if (!stagingDatabase.renameTo(liveDatabase)) {
                                throw new IllegalStateException("\u062a\u0645 \u062a\u0628\u062f\u064a\u0644 \u0627\u0644\u0645\u0641\u062a\u0627\u062d \u0644\u0643\u0646 \u062a\u0639\u0630\u0631 \u0648\u0636\u0639 \u0627\u0644\u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u062c\u062f\u064a\u062f\u0629\u061b \u064a\u0645\u0643\u0646 \u0627\u0633\u062a\u0643\u0645\u0627\u0644 \u0627\u0644\u062a\u0631\u0627\u062c\u0639 \u0628\u0623\u0645\u0627\u0646".toString());
                            }
                            this.afterCheckpoint.invoke(Checkpoint.NEW_DATABASE_RENAMED);
                            this.journal.advance(SqlCipherSameDeviceRestoreJournal.Phase.KEY_REPLACED, SqlCipherSameDeviceRestoreJournal.Phase.DATABASE_SWAPPED);
                            this.afterCheckpoint.invoke(Checkpoint.NEW_DATABASE_SWAP_RECORDED);
                            SqlCipherDatabaseMigrator.validateEncryptedDatabase$default(SqlCipherDatabaseMigrator.INSTANCE, liveDatabase, replacementKey, null, 4, null);
                            this.afterCheckpoint.invoke(Checkpoint.NEW_DATABASE_VERIFIED);
                            this.journal.advance(SqlCipherSameDeviceRestoreJournal.Phase.DATABASE_SWAPPED, SqlCipherSameDeviceRestoreJournal.Phase.VERIFIED);
                            this.afterCheckpoint.invoke(Checkpoint.VERIFICATION_RECORDED);
                            finalizeCommittedRestore(liveDatabase, file, atomicFile, file2);
                            ArraysKt.fill$default(importedDatabaseKeyForRestoreRecovery$app_release, (byte) 0, 0, 0, 6, (Object) null);
                        }
                    }
                }
            }
            throw new IllegalStateException("\u062a\u0648\u062c\u062f \u0646\u0642\u0637\u0629 \u062a\u0631\u0627\u062c\u0639 \u0633\u0627\u0628\u0642\u0629\u061b \u064a\u062c\u0628 \u0627\u0644\u062a\u0639\u0627\u0641\u064a \u0645\u0646\u0647\u0627 \u0623\u0648\u0644\u0627\u064b".toString());
        } catch (Throwable th) {
            ArraysKt.fill$default(importedDatabaseKeyForRestoreRecovery$app_release, (byte) 0, 0, 0, 6, (Object) null);
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:103:0x01e7 A[Catch: all -> 0x01f4, TryCatch #3 {all -> 0x01f4, blocks: (B:15:0x0064, B:21:0x008b, B:25:0x0094, B:28:0x00a6, B:30:0x00ac, B:35:0x00c1, B:37:0x00c7, B:40:0x00e5, B:72:0x0176, B:74:0x017f, B:77:0x0186, B:78:0x0191, B:79:0x0192, B:81:0x0198, B:86:0x01b2, B:87:0x01bd, B:94:0x01d6, B:95:0x01dd, B:92:0x01d2, B:93:0x01d5, B:101:0x01e3, B:102:0x01e6, B:103:0x01e7, B:104:0x01f3, B:20:0x0081, B:17:0x006a, B:98:0x01e0, B:42:0x00ee, B:44:0x00f4, B:50:0x011b, B:52:0x0121, B:54:0x0127, B:57:0x012e, B:58:0x0139, B:59:0x013a, B:60:0x0146, B:61:0x0147, B:63:0x014d, B:66:0x0154, B:67:0x015f, B:49:0x0111, B:68:0x0160, B:70:0x0166, B:88:0x01be, B:89:0x01ca, B:38:0x00d3), top: B:124:0x0064, outer: #5, inners: #1, #2, #4, #6 }] */
    /* JADX WARN: Code duplicated, block: B:118:0x00fa A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:125:0x00ee A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:24:0x0093  */
    /* JADX WARN: Code duplicated, block: B:37:0x00c7 A[Catch: all -> 0x01f4, TRY_LEAVE, TryCatch #3 {all -> 0x01f4, blocks: (B:15:0x0064, B:21:0x008b, B:25:0x0094, B:28:0x00a6, B:30:0x00ac, B:35:0x00c1, B:37:0x00c7, B:40:0x00e5, B:72:0x0176, B:74:0x017f, B:77:0x0186, B:78:0x0191, B:79:0x0192, B:81:0x0198, B:86:0x01b2, B:87:0x01bd, B:94:0x01d6, B:95:0x01dd, B:92:0x01d2, B:93:0x01d5, B:101:0x01e3, B:102:0x01e6, B:103:0x01e7, B:104:0x01f3, B:20:0x0081, B:17:0x006a, B:98:0x01e0, B:42:0x00ee, B:44:0x00f4, B:50:0x011b, B:52:0x0121, B:54:0x0127, B:57:0x012e, B:58:0x0139, B:59:0x013a, B:60:0x0146, B:61:0x0147, B:63:0x014d, B:66:0x0154, B:67:0x015f, B:49:0x0111, B:68:0x0160, B:70:0x0166, B:88:0x01be, B:89:0x01ca, B:38:0x00d3), top: B:124:0x0064, outer: #5, inners: #1, #2, #4, #6 }] */
    /* JADX WARN: Code duplicated, block: B:44:0x00f4 A[Catch: all -> 0x01cb, TRY_LEAVE, TryCatch #4 {all -> 0x01cb, blocks: (B:42:0x00ee, B:44:0x00f4, B:50:0x011b, B:52:0x0121, B:54:0x0127, B:57:0x012e, B:58:0x0139, B:59:0x013a, B:60:0x0146, B:61:0x0147, B:63:0x014d, B:66:0x0154, B:67:0x015f, B:49:0x0111, B:68:0x0160, B:70:0x0166, B:88:0x01be, B:89:0x01ca, B:46:0x00fa), top: B:125:0x00ee, outer: #3, inners: #0 }] */
    /* JADX WARN: Code duplicated, block: B:52:0x0121 A[Catch: all -> 0x01cb, TryCatch #4 {all -> 0x01cb, blocks: (B:42:0x00ee, B:44:0x00f4, B:50:0x011b, B:52:0x0121, B:54:0x0127, B:57:0x012e, B:58:0x0139, B:59:0x013a, B:60:0x0146, B:61:0x0147, B:63:0x014d, B:66:0x0154, B:67:0x015f, B:49:0x0111, B:68:0x0160, B:70:0x0166, B:88:0x01be, B:89:0x01ca, B:46:0x00fa), top: B:125:0x00ee, outer: #3, inners: #0 }] */
    /* JADX WARN: Code duplicated, block: B:54:0x0127 A[Catch: all -> 0x01cb, TryCatch #4 {all -> 0x01cb, blocks: (B:42:0x00ee, B:44:0x00f4, B:50:0x011b, B:52:0x0121, B:54:0x0127, B:57:0x012e, B:58:0x0139, B:59:0x013a, B:60:0x0146, B:61:0x0147, B:63:0x014d, B:66:0x0154, B:67:0x015f, B:49:0x0111, B:68:0x0160, B:70:0x0166, B:88:0x01be, B:89:0x01ca, B:46:0x00fa), top: B:125:0x00ee, outer: #3, inners: #0 }] */
    /* JADX WARN: Code duplicated, block: B:56:0x012d  */
    /* JADX WARN: Code duplicated, block: B:57:0x012e A[Catch: all -> 0x01cb, TryCatch #4 {all -> 0x01cb, blocks: (B:42:0x00ee, B:44:0x00f4, B:50:0x011b, B:52:0x0121, B:54:0x0127, B:57:0x012e, B:58:0x0139, B:59:0x013a, B:60:0x0146, B:61:0x0147, B:63:0x014d, B:66:0x0154, B:67:0x015f, B:49:0x0111, B:68:0x0160, B:70:0x0166, B:88:0x01be, B:89:0x01ca, B:46:0x00fa), top: B:125:0x00ee, outer: #3, inners: #0 }] */
    /* JADX WARN: Code duplicated, block: B:59:0x013a A[Catch: all -> 0x01cb, TryCatch #4 {all -> 0x01cb, blocks: (B:42:0x00ee, B:44:0x00f4, B:50:0x011b, B:52:0x0121, B:54:0x0127, B:57:0x012e, B:58:0x0139, B:59:0x013a, B:60:0x0146, B:61:0x0147, B:63:0x014d, B:66:0x0154, B:67:0x015f, B:49:0x0111, B:68:0x0160, B:70:0x0166, B:88:0x01be, B:89:0x01ca, B:46:0x00fa), top: B:125:0x00ee, outer: #3, inners: #0 }] */
    /* JADX WARN: Code duplicated, block: B:70:0x0166 A[Catch: all -> 0x01cb, TRY_LEAVE, TryCatch #4 {all -> 0x01cb, blocks: (B:42:0x00ee, B:44:0x00f4, B:50:0x011b, B:52:0x0121, B:54:0x0127, B:57:0x012e, B:58:0x0139, B:59:0x013a, B:60:0x0146, B:61:0x0147, B:63:0x014d, B:66:0x0154, B:67:0x015f, B:49:0x0111, B:68:0x0160, B:70:0x0166, B:88:0x01be, B:89:0x01ca, B:46:0x00fa), top: B:125:0x00ee, outer: #3, inners: #0 }] */
    /* JADX WARN: Code duplicated, block: B:81:0x0198 A[Catch: all -> 0x01f4, TRY_LEAVE, TryCatch #3 {all -> 0x01f4, blocks: (B:15:0x0064, B:21:0x008b, B:25:0x0094, B:28:0x00a6, B:30:0x00ac, B:35:0x00c1, B:37:0x00c7, B:40:0x00e5, B:72:0x0176, B:74:0x017f, B:77:0x0186, B:78:0x0191, B:79:0x0192, B:81:0x0198, B:86:0x01b2, B:87:0x01bd, B:94:0x01d6, B:95:0x01dd, B:92:0x01d2, B:93:0x01d5, B:101:0x01e3, B:102:0x01e6, B:103:0x01e7, B:104:0x01f3, B:20:0x0081, B:17:0x006a, B:98:0x01e0, B:42:0x00ee, B:44:0x00f4, B:50:0x011b, B:52:0x0121, B:54:0x0127, B:57:0x012e, B:58:0x0139, B:59:0x013a, B:60:0x0146, B:61:0x0147, B:63:0x014d, B:66:0x0154, B:67:0x015f, B:49:0x0111, B:68:0x0160, B:70:0x0166, B:88:0x01be, B:89:0x01ca, B:38:0x00d3), top: B:124:0x0064, outer: #5, inners: #1, #2, #4, #6 }] */
    /* JADX WARN: Code duplicated, block: B:86:0x01b2 A[Catch: all -> 0x01f4, TRY_ENTER, TryCatch #3 {all -> 0x01f4, blocks: (B:15:0x0064, B:21:0x008b, B:25:0x0094, B:28:0x00a6, B:30:0x00ac, B:35:0x00c1, B:37:0x00c7, B:40:0x00e5, B:72:0x0176, B:74:0x017f, B:77:0x0186, B:78:0x0191, B:79:0x0192, B:81:0x0198, B:86:0x01b2, B:87:0x01bd, B:94:0x01d6, B:95:0x01dd, B:92:0x01d2, B:93:0x01d5, B:101:0x01e3, B:102:0x01e6, B:103:0x01e7, B:104:0x01f3, B:20:0x0081, B:17:0x006a, B:98:0x01e0, B:42:0x00ee, B:44:0x00f4, B:50:0x011b, B:52:0x0121, B:54:0x0127, B:57:0x012e, B:58:0x0139, B:59:0x013a, B:60:0x0146, B:61:0x0147, B:63:0x014d, B:66:0x0154, B:67:0x015f, B:49:0x0111, B:68:0x0160, B:70:0x0166, B:88:0x01be, B:89:0x01ca, B:38:0x00d3), top: B:124:0x0064, outer: #5, inners: #1, #2, #4, #6 }] */
    /* JADX WARN: Code duplicated, block: B:88:0x01be A[Catch: all -> 0x01cb, TRY_ENTER, TryCatch #4 {all -> 0x01cb, blocks: (B:42:0x00ee, B:44:0x00f4, B:50:0x011b, B:52:0x0121, B:54:0x0127, B:57:0x012e, B:58:0x0139, B:59:0x013a, B:60:0x0146, B:61:0x0147, B:63:0x014d, B:66:0x0154, B:67:0x015f, B:49:0x0111, B:68:0x0160, B:70:0x0166, B:88:0x01be, B:89:0x01ca, B:46:0x00fa), top: B:125:0x00ee, outer: #3, inners: #0 }] */
    /* JADX WARN: Code duplicated, block: B:94:0x01d6 A[Catch: all -> 0x01f4, TryCatch #3 {all -> 0x01f4, blocks: (B:15:0x0064, B:21:0x008b, B:25:0x0094, B:28:0x00a6, B:30:0x00ac, B:35:0x00c1, B:37:0x00c7, B:40:0x00e5, B:72:0x0176, B:74:0x017f, B:77:0x0186, B:78:0x0191, B:79:0x0192, B:81:0x0198, B:86:0x01b2, B:87:0x01bd, B:94:0x01d6, B:95:0x01dd, B:92:0x01d2, B:93:0x01d5, B:101:0x01e3, B:102:0x01e6, B:103:0x01e7, B:104:0x01f3, B:20:0x0081, B:17:0x006a, B:98:0x01e0, B:42:0x00ee, B:44:0x00f4, B:50:0x011b, B:52:0x0121, B:54:0x0127, B:57:0x012e, B:58:0x0139, B:59:0x013a, B:60:0x0146, B:61:0x0147, B:63:0x014d, B:66:0x0154, B:67:0x015f, B:49:0x0111, B:68:0x0160, B:70:0x0166, B:88:0x01be, B:89:0x01ca, B:38:0x00d3), top: B:124:0x0064, outer: #5, inners: #1, #2, #4, #6 }] */
    public final synchronized RecoveryResult recover(DatabaseKeyVault keyVault) {
        Object objM381constructorimpl;
        boolean z;
        FileInputStream fileInputStreamOpenRead;
        byte[] importedDatabaseKeyForRestoreRecovery$app_release;
        Object objM381constructorimpl2;
        Intrinsics.checkNotNullParameter(keyVault, "keyVault");
        SqlCipherSameDeviceRestoreJournal.Record record = this.journal.read();
        if (record == null) {
            return RecoveryResult.NOTHING_TO_RECOVER;
        }
        File parentFile = this.appContext.getDatabasePath(record.getLiveName()).getParentFile();
        if (parentFile == null) {
            throw new IllegalStateException("\u0645\u062c\u0644\u062f \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u063a\u064a\u0631 \u0645\u062a\u0627\u062d");
        }
        File fileResolveDatabaseChild = resolveDatabaseChild(parentFile, record.getStagingName());
        File fileResolveDatabaseChild2 = resolveDatabaseChild(parentFile, record.getLiveName());
        File fileResolveDatabaseChild3 = resolveDatabaseChild(parentFile, record.getRollbackName());
        File fileResolvePrivateChild = resolvePrivateChild(record.getKeySnapshotName());
        validateDatabasePair(fileResolveDatabaseChild, fileResolveDatabaseChild2);
        if (!Intrinsics.areEqual(fileResolveDatabaseChild3.getCanonicalFile().getParentFile(), fileResolveDatabaseChild2.getCanonicalFile().getParentFile())) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        byte[] importedDatabaseKeyForRestoreRecovery$app_release2 = keyVault.getImportedDatabaseKeyForRestoreRecovery$app_release();
        if (importedDatabaseKeyForRestoreRecovery$app_release2 == null) {
            throw new IllegalStateException("\u0645\u0641\u062a\u0627\u062d \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0645\u0641\u0642\u0648\u062f\u061b \u0623\u0648\u0642\u0641 \u0627\u0644\u062a\u0639\u0627\u0641\u064a \u0648\u0627\u062d\u062a\u0641\u0638 \u0628\u0627\u0644\u0645\u0644\u0641\u0627\u062a");
        }
        try {
            if (fileResolveDatabaseChild2.isFile()) {
                try {
                    Result.Companion companion = Result.INSTANCE;
                    SqlCipherSameDeviceRestoreCoordinator sqlCipherSameDeviceRestoreCoordinator = this;
                    SqlCipherDatabaseMigrator.validateEncryptedDatabase$default(SqlCipherDatabaseMigrator.INSTANCE, fileResolveDatabaseChild2, importedDatabaseKeyForRestoreRecovery$app_release2, null, 4, null);
                    objM381constructorimpl = Result.m381constructorimpl(Unit.INSTANCE);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m388isSuccessimpl(objM381constructorimpl)) {
                    z = true;
                } else {
                    z = false;
                }
                if (record.getPhase().ordinal() < SqlCipherSameDeviceRestoreJournal.Phase.KEY_REPLACED.ordinal() && z && !fileResolveDatabaseChild.exists()) {
                    finalizeCommittedRestore(fileResolveDatabaseChild2, fileResolveDatabaseChild3, new AtomicFile(fileResolvePrivateChild), fileResolvePrivateChild);
                    RecoveryResult recoveryResult = RecoveryResult.RESTORE_COMPLETED;
                    ArraysKt.fill$default(importedDatabaseKeyForRestoreRecovery$app_release2, (byte) 0, 0, 0, 6, (Object) null);
                    return recoveryResult;
                }
                if (fileResolvePrivateChild.isFile()) {
                    throw new IllegalArgumentException("\u0646\u0633\u062e\u0629 \u0627\u0644\u0645\u0641\u062a\u0627\u062d \u0627\u0644\u0642\u062f\u064a\u0645\u0629 \u0645\u0641\u0642\u0648\u062f\u0629\u061b \u0644\u0627 \u064a\u0645\u0643\u0646 \u0627\u0644\u062a\u0631\u0627\u062c\u0639 \u0628\u0623\u0645\u0627\u0646".toString());
                }
                fileInputStreamOpenRead = new AtomicFile(fileResolvePrivateChild).openRead();
                try {
                    FileInputStream fileInputStream = fileInputStreamOpenRead;
                    Intrinsics.checkNotNull(fileInputStream);
                    keyVault.restoreWrappedKeyMetadata$app_release(ByteStreamsKt.readBytes(fileInputStream));
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(fileInputStreamOpenRead, null);
                    importedDatabaseKeyForRestoreRecovery$app_release = keyVault.getImportedDatabaseKeyForRestoreRecovery$app_release();
                    if (importedDatabaseKeyForRestoreRecovery$app_release != null) {
                        throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0645\u0641\u062a\u0627\u062d \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0633\u0627\u0628\u0642");
                    }
                    try {
                        if (fileResolveDatabaseChild3.exists()) {
                            if (fileResolveDatabaseChild2.exists()) {
                                try {
                                    Result.Companion companion3 = Result.INSTANCE;
                                    SqlCipherSameDeviceRestoreCoordinator sqlCipherSameDeviceRestoreCoordinator2 = this;
                                    SqlCipherDatabaseMigrator.validateEncryptedDatabase$default(SqlCipherDatabaseMigrator.INSTANCE, fileResolveDatabaseChild2, importedDatabaseKeyForRestoreRecovery$app_release, null, 4, null);
                                    objM381constructorimpl2 = Result.m381constructorimpl(Unit.INSTANCE);
                                } catch (Throwable th2) {
                                    Result.Companion companion4 = Result.INSTANCE;
                                    objM381constructorimpl2 = Result.m381constructorimpl(ResultKt.createFailure(th2));
                                }
                                if (!Result.m388isSuccessimpl(objM381constructorimpl2)) {
                                    if (!fileResolveDatabaseChild.exists()) {
                                        throw new IllegalStateException("\u0645\u0644\u0641\u0627 \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0627\u0644\u062d\u0627\u0644\u064a \u0648\u0627\u0644\u0645\u0624\u0642\u062a \u0645\u0648\u062c\u0648\u062f\u0627\u0646\u061b \u0623\u064f\u0648\u0642\u0641 \u0627\u0644\u062a\u0631\u0627\u062c\u0639 \u062f\u0648\u0646 \u062d\u0630\u0641 \u0623\u064a \u0645\u0646\u0647\u0645\u0627".toString());
                                    }
                                    if (fileResolveDatabaseChild2.renameTo(fileResolveDatabaseChild)) {
                                        throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0639\u0632\u0644 \u0627\u0644\u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u062c\u062f\u064a\u062f\u0629 \u0642\u0628\u0644 \u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0627\u0644\u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0633\u0627\u0628\u0642\u0629".toString());
                                    }
                                }
                            }
                            if (!fileResolveDatabaseChild2.exists() && !fileResolveDatabaseChild3.renameTo(fileResolveDatabaseChild2)) {
                                throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0625\u0639\u0627\u062f\u0629 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0633\u0627\u0628\u0642\u0629".toString());
                            }
                        }
                        if (fileResolveDatabaseChild2.isFile()) {
                            throw new IllegalStateException("\u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0633\u0627\u0628\u0642\u0629 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629 \u0628\u0639\u062f \u0627\u0644\u062a\u0631\u0627\u062c\u0639".toString());
                        }
                        SqlCipherDatabaseMigrator.validateEncryptedDatabase$default(SqlCipherDatabaseMigrator.INSTANCE, fileResolveDatabaseChild2, importedDatabaseKeyForRestoreRecovery$app_release, null, 4, null);
                        ArraysKt.fill$default(importedDatabaseKeyForRestoreRecovery$app_release, (byte) 0, 0, 0, 6, (Object) null);
                        if (fileResolveDatabaseChild.exists() && !fileResolveDatabaseChild.delete()) {
                            throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u062a\u0646\u0638\u064a\u0641 \u0645\u0644\u0641 \u0627\u0644\u062a\u062c\u0647\u064a\u0632 \u0628\u0639\u062f \u0627\u0644\u062a\u0631\u0627\u062c\u0639".toString());
                        }
                        if (!fileResolveDatabaseChild3.exists()) {
                            throw new IllegalStateException("\u0628\u0642\u064a \u0645\u0644\u0641 \u0627\u0644\u062a\u0631\u0627\u062c\u0639\u061b \u0644\u0646 \u064a\u062a\u0645 \u062d\u0630\u0641 \u0633\u062c\u0644 \u0627\u0644\u0639\u0645\u0644\u064a\u0629".toString());
                        }
                        new AtomicFile(fileResolvePrivateChild).delete();
                        this.journal.clear();
                        RecoveryResult recoveryResult2 = RecoveryResult.RESTORE_ROLLED_BACK;
                        ArraysKt.fill$default(importedDatabaseKeyForRestoreRecovery$app_release2, (byte) 0, 0, 0, 6, (Object) null);
                        return recoveryResult2;
                    } catch (Throwable th3) {
                        ArraysKt.fill$default(importedDatabaseKeyForRestoreRecovery$app_release, (byte) 0, 0, 0, 6, (Object) null);
                        throw th3;
                    }
                } catch (Throwable th4) {
                    try {
                        throw th4;
                    } catch (Throwable th5) {
                        CloseableKt.closeFinally(fileInputStreamOpenRead, th4);
                        throw th5;
                    }
                }
            }
            z = false;
            if (record.getPhase().ordinal() < SqlCipherSameDeviceRestoreJournal.Phase.KEY_REPLACED.ordinal()) {
            }
            if (fileResolvePrivateChild.isFile()) {
                throw new IllegalArgumentException("\u0646\u0633\u062e\u0629 \u0627\u0644\u0645\u0641\u062a\u0627\u062d \u0627\u0644\u0642\u062f\u064a\u0645\u0629 \u0645\u0641\u0642\u0648\u062f\u0629\u061b \u0644\u0627 \u064a\u0645\u0643\u0646 \u0627\u0644\u062a\u0631\u0627\u062c\u0639 \u0628\u0623\u0645\u0627\u0646".toString());
            }
            fileInputStreamOpenRead = new AtomicFile(fileResolvePrivateChild).openRead();
            FileInputStream fileInputStream2 = fileInputStreamOpenRead;
            Intrinsics.checkNotNull(fileInputStream2);
            keyVault.restoreWrappedKeyMetadata$app_release(ByteStreamsKt.readBytes(fileInputStream2));
            Unit unit2 = Unit.INSTANCE;
            CloseableKt.closeFinally(fileInputStreamOpenRead, null);
            importedDatabaseKeyForRestoreRecovery$app_release = keyVault.getImportedDatabaseKeyForRestoreRecovery$app_release();
            if (importedDatabaseKeyForRestoreRecovery$app_release != null) {
                throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0645\u0641\u062a\u0627\u062d \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0633\u0627\u0628\u0642");
            }
            if (fileResolveDatabaseChild3.exists()) {
                if (fileResolveDatabaseChild2.exists()) {
                    Result.Companion companion5 = Result.INSTANCE;
                    SqlCipherSameDeviceRestoreCoordinator sqlCipherSameDeviceRestoreCoordinator3 = this;
                    SqlCipherDatabaseMigrator.validateEncryptedDatabase$default(SqlCipherDatabaseMigrator.INSTANCE, fileResolveDatabaseChild2, importedDatabaseKeyForRestoreRecovery$app_release, null, 4, null);
                    objM381constructorimpl2 = Result.m381constructorimpl(Unit.INSTANCE);
                    if (!Result.m388isSuccessimpl(objM381constructorimpl2)) {
                        if (!fileResolveDatabaseChild.exists()) {
                            throw new IllegalStateException("\u0645\u0644\u0641\u0627 \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0627\u0644\u062d\u0627\u0644\u064a \u0648\u0627\u0644\u0645\u0624\u0642\u062a \u0645\u0648\u062c\u0648\u062f\u0627\u0646\u061b \u0623\u064f\u0648\u0642\u0641 \u0627\u0644\u062a\u0631\u0627\u062c\u0639 \u062f\u0648\u0646 \u062d\u0630\u0641 \u0623\u064a \u0645\u0646\u0647\u0645\u0627".toString());
                        }
                        if (fileResolveDatabaseChild2.renameTo(fileResolveDatabaseChild)) {
                            throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0639\u0632\u0644 \u0627\u0644\u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u062c\u062f\u064a\u062f\u0629 \u0642\u0628\u0644 \u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0627\u0644\u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0633\u0627\u0628\u0642\u0629".toString());
                        }
                    }
                }
                if (!fileResolveDatabaseChild2.exists()) {
                    throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0625\u0639\u0627\u062f\u0629 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0633\u0627\u0628\u0642\u0629".toString());
                }
            }
            if (fileResolveDatabaseChild2.isFile()) {
                throw new IllegalStateException("\u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0633\u0627\u0628\u0642\u0629 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629 \u0628\u0639\u062f \u0627\u0644\u062a\u0631\u0627\u062c\u0639".toString());
            }
            SqlCipherDatabaseMigrator.validateEncryptedDatabase$default(SqlCipherDatabaseMigrator.INSTANCE, fileResolveDatabaseChild2, importedDatabaseKeyForRestoreRecovery$app_release, null, 4, null);
            ArraysKt.fill$default(importedDatabaseKeyForRestoreRecovery$app_release, (byte) 0, 0, 0, 6, (Object) null);
            if (fileResolveDatabaseChild.exists()) {
                throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u062a\u0646\u0638\u064a\u0641 \u0645\u0644\u0641 \u0627\u0644\u062a\u062c\u0647\u064a\u0632 \u0628\u0639\u062f \u0627\u0644\u062a\u0631\u0627\u062c\u0639".toString());
            }
            if (!fileResolveDatabaseChild3.exists()) {
                throw new IllegalStateException("\u0628\u0642\u064a \u0645\u0644\u0641 \u0627\u0644\u062a\u0631\u0627\u062c\u0639\u061b \u0644\u0646 \u064a\u062a\u0645 \u062d\u0630\u0641 \u0633\u062c\u0644 \u0627\u0644\u0639\u0645\u0644\u064a\u0629".toString());
            }
            new AtomicFile(fileResolvePrivateChild).delete();
            this.journal.clear();
            RecoveryResult recoveryResult3 = RecoveryResult.RESTORE_ROLLED_BACK;
            ArraysKt.fill$default(importedDatabaseKeyForRestoreRecovery$app_release2, (byte) 0, 0, 0, 6, (Object) null);
            return recoveryResult3;
        } catch (Throwable th6) {
            ArraysKt.fill$default(importedDatabaseKeyForRestoreRecovery$app_release2, (byte) 0, 0, 0, 6, (Object) null);
            throw th6;
        }
        throw th;
    }

    private final void finalizeCommittedRestore(File live, File rollback, AtomicFile snapshotAtomic, File snapshot) {
        if (!live.isFile() || live.length() <= 0) {
            throw new IllegalStateException("Check failed.");
        }
        if (rollback.exists() && !rollback.delete()) {
            throw new IllegalStateException("\u062a\u0645 \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u062c\u062f\u064a\u062f\u0629 \u0644\u0643\u0646 \u062a\u0639\u0630\u0631 \u062a\u0646\u0638\u064a\u0641 \u0646\u0633\u062e\u0629 \u0627\u0644\u062a\u0631\u0627\u062c\u0639\u061b \u0633\u064a\u064f\u0633\u062a\u0643\u0645\u0644 \u0627\u0644\u062a\u0646\u0638\u064a\u0641 \u0644\u0627\u062d\u0642\u0627\u064b".toString());
        }
        new File(rollback.getPath() + "-wal").delete();
        new File(rollback.getPath() + "-shm").delete();
        new File(rollback.getPath() + "-journal").delete();
        snapshotAtomic.delete();
        if (snapshot.exists()) {
            snapshot.delete();
        }
        this.journal.clear();
    }

    private final void writeAtomic(AtomicFile file, byte[] bytes) {
        FileOutputStream fileOutputStreamStartWrite;
        try {
            try {
                fileOutputStreamStartWrite = file.startWrite();
                try {
                    fileOutputStreamStartWrite.write(bytes);
                    fileOutputStreamStartWrite.flush();
                    fileOutputStreamStartWrite.getFD().sync();
                    file.finishWrite(fileOutputStreamStartWrite);
                    ArraysKt.fill$default(bytes, (byte) 0, 0, 0, 6, (Object) null);
                } catch (Exception e) {
                    e = e;
                    if (fileOutputStreamStartWrite != null) {
                        file.failWrite(fileOutputStreamStartWrite);
                    }
                    throw e;
                }
            } catch (Throwable th) {
                ArraysKt.fill$default(bytes, (byte) 0, 0, 0, 6, (Object) null);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            fileOutputStreamStartWrite = null;
        }
    }

    private final void ensureClosedDatabaseHasNoPendingWal(File database) {
        File file = new File(database.getPath() + "-wal");
        if (file.exists() && file.length() != 0) {
            throw new IllegalStateException("\u064a\u0648\u062c\u062f WAL \u063a\u064a\u0631 \u0645\u0641\u0631\u063a\u061b \u0623\u064f\u0648\u0642\u0641\u062a \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0644\u062d\u0645\u0627\u064a\u0629 \u0627\u0644\u0645\u0639\u0627\u0645\u0644\u0627\u062a".toString());
        }
        for (File file2 : CollectionsKt.listOf((Object[]) new File[]{file, new File(database.getPath() + "-shm"), new File(database.getPath() + "-journal")})) {
            if (file2.exists() && !file2.delete()) {
                throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u062a\u0646\u0638\u064a\u0641 \u0645\u0644\u0641 \u062c\u0627\u0646\u0628\u064a \u0628\u0639\u062f \u0625\u063a\u0644\u0627\u0642 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a".toString());
            }
        }
    }

    private final void validateDatabasePair(File staging, File live) {
        if (staging.getCanonicalFile().getParentFile() == null || !Intrinsics.areEqual(staging.getCanonicalFile().getParentFile(), live.getCanonicalFile().getParentFile()) || Intrinsics.areEqual(staging.getCanonicalFile(), live.getCanonicalFile())) {
            throw new IllegalArgumentException("\u064a\u062c\u0628 \u0623\u0646 \u062a\u0643\u0648\u0646 \u0627\u0644\u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0645\u0624\u0642\u062a\u0629 \u0648\u0627\u0644\u062d\u0627\u0644\u064a\u0629 \u0641\u064a \u0645\u062c\u0644\u062f \u0642\u0648\u0627\u0639\u062f \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0646\u0641\u0633\u0647".toString());
        }
    }

    private final File resolveDatabaseChild(File directory, String name) {
        if (StringsKt.isBlank(name) || !Intrinsics.areEqual(new File(name).getName(), name) || Intrinsics.areEqual(name, ".") || Intrinsics.areEqual(name, "..")) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        File file = new File(directory, name);
        if (Intrinsics.areEqual(file.getCanonicalFile().getParentFile(), directory.getCanonicalFile())) {
            return file;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    private final File resolvePrivateChild(String name) {
        if (StringsKt.isBlank(name) || !Intrinsics.areEqual(new File(name).getName(), name) || Intrinsics.areEqual(name, ".") || Intrinsics.areEqual(name, "..")) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        File file = new File(this.appContext.getNoBackupFilesDir(), name);
        if (Intrinsics.areEqual(file.getCanonicalFile().getParentFile(), this.appContext.getNoBackupFilesDir().getCanonicalFile())) {
            return file;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }
}
