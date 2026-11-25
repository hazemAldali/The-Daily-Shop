package com.hazem.dailystore.data;

import android.content.Context;
import android.database.Cursor;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import net.zetetic.database.sqlcipher.SQLiteDatabase;

/* JADX INFO: compiled from: BackupRestoreManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 ;2\u00020\u0001:\u0002:;B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000\u00a2\u0006\u0002\b\u0010J6\u0010\u0011\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u00142\u0016\b\u0002\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0016J6\u0010\u0017\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0016\b\u0002\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0016J6\u0010\u0018\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u00142\u0014\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0016H\u0002J6\u0010\u0019\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0014\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0016H\u0002J\u0006\u0010\u001a\u001a\u00020\u0012J\u0018\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u001e\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\tH\u0002J*\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\u001d2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\"\u001a\u00020#H\u0002J*\u0010$\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\r2\u0006\u0010!\u001a\u00020\u001d2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010%\u001a\u00020&H\u0002J\"\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020(2\b\b\u0002\u0010\"\u001a\u00020#H\u0002J\u001a\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020(2\b\b\u0002\u0010%\u001a\u00020&H\u0002J2\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020#2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020(2\b\b\u0002\u0010\"\u001a\u00020#H\u0002J\u0010\u00101\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0014\u00102\u001a\u00020\u00122\n\u00103\u001a\u000604j\u0002`5H\u0002J\u0010\u00106\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u00107\u001a\u00020\u00122\u0006\u00108\u001a\u00020\u001dH\u0002J\u0018\u00109\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\u001dH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006<"}, d2 = {"Lcom/hazem/dailystore/data/BackupRestoreManager;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "stageBackupForSqlCipherRestore", "Lcom/hazem/dailystore/data/StagedSqlCipherBackup;", "actorUserId", "", "password", "", "source", "Ljava/io/InputStream;", "keyVault", "Lcom/hazem/dailystore/data/DatabaseKeyVault;", "stageBackupForSqlCipherRestore$app_release", "createBackup", "", "destination", "Ljava/io/OutputStream;", "onProgress", "Lkotlin/Function1;", "restoreBackup", "createPlaintextFixtureBackup", "restorePlaintextFixtureBackup", "recoverInterruptedRestore", "validateDatabaseForRecovery", "file", "Ljava/io/File;", "requireBackupPermission", "encryptSnapshot", "snapshot", "target", "iterations", "", "decryptToFile", "allowTestFixtureIterations", "", "buildHeader", "", "salt", "iv", "parseHeader", "Lcom/hazem/dailystore/data/BackupRestoreManager$ParsedHeader;", "header", "cipher", "Ljavax/crypto/Cipher;", "mode", "validateDatabase", "validateOpenDatabase", "db", "Lnet/zetetic/database/sqlcipher/SQLiteDatabase;", "Lcom/hazem/dailystore/data/AppSqliteDatabase;", "validatePassword", "deleteSidecars", "database", "copyFile", "ParsedHeader", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class BackupRestoreManager {
    private static final int FORMAT_VERSION = 1;
    private static final int HEADER_BYTES = 44;
    private static final int IV_BYTES = 12;
    private static final int KEY_BITS = 256;
    private static final long MAX_DATABASE_BYTES = 536870912;
    private static final int MAX_PBKDF2_ITERATIONS = 500000;
    private static final int MIN_PASSWORD_LENGTH = 10;
    private static final int MIN_PBKDF2_ITERATIONS = 200000;
    private static final int PBKDF2_ITERATIONS = 310000;
    private static final int SALT_BYTES = 16;
    private static final int TAG_BITS = 128;
    private static final int TEST_FIXTURE_PBKDF2_ITERATIONS = 10000;
    private final Context context;
    private static final byte[] LEGACY_BACKUP_MAGIC = {84, 68, 83, 66, 65, 67, 75, 49};
    private static final byte[] SQLCIPHER_BACKUP_MAGIC = {84, 68, 83, 66, 75, 85, 80, 49};
    private static final Object LOCK = new Object();
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final byte[] MAGIC = {84, 68, 83, 66, 65, 67, 75, 49};

    public BackupRestoreManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public final StagedSqlCipherBackup stageBackupForSqlCipherRestore$app_release(String actorUserId, char[] password, InputStream source, DatabaseKeyVault keyVault) throws IOException {
        byte[] orCreateMigrationKey$app_release;
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(keyVault, "keyVault");
        requireBackupPermission(actorUserId);
        validatePassword(password);
        File parentFile = this.context.getDatabasePath(AppDatabase.DATABASE_NAME).getParentFile();
        if (parentFile == null) {
            throw new IllegalStateException("\u0645\u062c\u0644\u062f \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u063a\u064a\u0631 \u0645\u062a\u0627\u062d");
        }
        if (!parentFile.isDirectory() && !parentFile.mkdirs()) {
            throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0625\u0646\u0634\u0627\u0621 \u0645\u062c\u0644\u062f \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a".toString());
        }
        long jNanoTime = System.nanoTime();
        File file = new File(parentFile, "the_daily_shop.db.restore-sqlcipher-" + jNanoTime + ".db");
        File file2 = new File(this.context.getCacheDir(), "tds-legacy-restore-" + jNanoTime + ".db");
        byte[] bArr = new byte[8];
        new DataInputStream(source).readFully(bArr);
        SequenceInputStream sequenceInputStream = new SequenceInputStream(new ByteArrayInputStream(bArr), source);
        try {
            try {
                if (Arrays.equals(bArr, SQLCIPHER_BACKUP_MAGIC)) {
                    orCreateMigrationKey$app_release = new SqlCipherBackupRestorePipeline().stageRestore(sequenceInputStream, password, file);
                } else if (Arrays.equals(bArr, LEGACY_BACKUP_MAGIC)) {
                    decryptToFile$default(this, sequenceInputStream, file2, password, false, 8, null);
                    validateDatabase(file2);
                    orCreateMigrationKey$app_release = keyVault.getOrCreateMigrationKey$app_release(file2);
                    new SqlCipherBackupRestorePipeline().stageLegacyPlaintextBackup(file2, file, orCreateMigrationKey$app_release);
                } else {
                    throw new IllegalArgumentException("\u0635\u064a\u063a\u0629 \u0627\u0644\u0646\u0633\u062e\u0629 \u0627\u0644\u0627\u062d\u062a\u064a\u0627\u0637\u064a\u0629 \u063a\u064a\u0631 \u0645\u0639\u0631\u0648\u0641\u0629");
                }
                Intrinsics.checkNotNull(orCreateMigrationKey$app_release);
                StagedSqlCipherBackup stagedSqlCipherBackup = new StagedSqlCipherBackup(file, orCreateMigrationKey$app_release);
                file2.delete();
                return stagedSqlCipherBackup;
            } catch (Exception e) {
                file.delete();
                byte[] bArr2 = (byte[]) null;
                if (bArr2 != null) {
                    ArraysKt.fill$default(bArr2, (byte) 0, 0, 0, 6, (Object) null);
                }
                throw e;
            }
        } catch (Throwable th) {
            file2.delete();
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void createBackup$default(BackupRestoreManager backupRestoreManager, String str, char[] cArr, OutputStream outputStream, Function1 function1, int i, Object obj) {
        if ((i & 8) != 0) {
            function1 = null;
        }
        backupRestoreManager.createBackup(str, cArr, outputStream, function1);
    }

    public final void createBackup(String actorUserId, char[] password, OutputStream destination, Function1<? super String, Unit> onProgress) {
        byte[] bArr;
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(destination, "destination");
        requireBackupPermission(actorUserId);
        validatePassword(password);
        if (AppDatabase.INSTANCE.isPlaintextBackendForTests$app_release()) {
            createPlaintextFixtureBackup(actorUserId, password, destination, onProgress);
            return;
        }
        if (onProgress != null) {
            onProgress.invoke("\u0625\u063a\u0644\u0627\u0642 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0648\u062a\u062c\u0647\u064a\u0632 \u0644\u0642\u0637\u0629 \u0622\u0645\u0646\u0629...");
        }
        synchronized (LOCK) {
            File databasePath = this.context.getDatabasePath(AppDatabase.DATABASE_NAME);
            DatabaseKeyVault databaseKeyVault = new DatabaseKeyVault(this.context, null, null, 6, null);
            try {
                SqlCipherDatabaseRuntime sqlCipherDatabaseRuntime = SqlCipherDatabaseRuntime.INSTANCE;
                Intrinsics.checkNotNull(databasePath);
                byte[] productionDatabaseKey = sqlCipherDatabaseRuntime.getProductionDatabaseKey(databaseKeyVault, databasePath);
                try {
                    AppDatabase.INSTANCE.getInstance(this.context).checkpointAndCloseForBackup$app_release();
                    if (onProgress != null) {
                        onProgress.invoke("\u0627\u0644\u062a\u062d\u0642\u0642 \u0645\u0646 \u0642\u0627\u0639\u062f\u0629 SQLCipher \u0648\u062a\u062c\u0647\u064a\u0632 \u0627\u0644\u0646\u0633\u062e\u0629...");
                    }
                    new SqlCipherBackupRestorePipeline().createBackup(databasePath, productionDatabaseKey, password, destination);
                    destination.flush();
                    if (onProgress != null) {
                        onProgress.invoke("\u0627\u0643\u062a\u0645\u0644 \u0625\u0646\u0634\u0627\u0621 \u0627\u0644\u0646\u0633\u062e\u0629 \u0627\u0644\u0627\u062d\u062a\u064a\u0627\u0637\u064a\u0629 \u0627\u0644\u0645\u0634\u0641\u0651\u0631\u0629.");
                        Unit unit = Unit.INSTANCE;
                    }
                    if (productionDatabaseKey != null) {
                        ArraysKt.fill$default(productionDatabaseKey, (byte) 0, 0, 0, 6, (Object) null);
                    }
                } catch (Throwable th) {
                    th = th;
                    bArr = productionDatabaseKey;
                    if (bArr != null) {
                        ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                bArr = null;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void restoreBackup$default(BackupRestoreManager backupRestoreManager, String str, char[] cArr, InputStream inputStream, Function1 function1, int i, Object obj) {
        if ((i & 8) != 0) {
            function1 = null;
        }
        backupRestoreManager.restoreBackup(str, cArr, inputStream, function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void restoreBackup(String actorUserId, char[] password, InputStream source, Function1<? super String, Unit> onProgress) {
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(source, "source");
        requireBackupPermission(actorUserId);
        validatePassword(password);
        if (AppDatabase.INSTANCE.isPlaintextBackendForTests$app_release()) {
            restorePlaintextFixtureBackup(actorUserId, password, source, onProgress);
            return;
        }
        if (onProgress != null) {
            onProgress.invoke("\u0641\u0643 \u062a\u0634\u0641\u064a\u0631 \u0627\u0644\u0646\u0633\u062e\u0629 \u0648\u0627\u0644\u062a\u062d\u0642\u0642 \u0645\u0646 \u0633\u0644\u0627\u0645\u062a\u0647\u0627...");
        }
        synchronized (LOCK) {
            DatabaseKeyVault databaseKeyVault = new DatabaseKeyVault(this.context, null, null, 6, null);
            StagedSqlCipherBackup stagedSqlCipherBackupStageBackupForSqlCipherRestore$app_release = stageBackupForSqlCipherRestore$app_release(actorUserId, password, source, databaseKeyVault);
            try {
                File databasePath = this.context.getDatabasePath(AppDatabase.DATABASE_NAME);
                if (onProgress != null) {
                    onProgress.invoke("\u062d\u0641\u0638 \u0627\u0644\u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u062d\u0627\u0644\u064a\u0629 \u0648\u0646\u0642\u0637\u0629 \u0627\u0644\u062a\u0631\u0627\u062c\u0639...");
                }
                SqlCipherSameDeviceRestoreCoordinator sqlCipherSameDeviceRestoreCoordinator = new SqlCipherSameDeviceRestoreCoordinator(this.context, null, null, 6, null);
                File databaseFile = stagedSqlCipherBackupStageBackupForSqlCipherRestore$app_release.getDatabaseFile();
                Intrinsics.checkNotNull(databasePath);
                sqlCipherSameDeviceRestoreCoordinator.commit(databaseFile, databasePath, stagedSqlCipherBackupStageBackupForSqlCipherRestore$app_release.getDatabaseKey(), databaseKeyVault, new Function0() { // from class: com.hazem.dailystore.data.BackupRestoreManager$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return BackupRestoreManager.restoreBackup$lambda$4$lambda$3(this.f$0);
                    }
                });
                AppDatabase.INSTANCE.resetInstanceAfterEncryptedRestore$app_release();
                if (onProgress != null) {
                    onProgress.invoke("\u0627\u0644\u062a\u062d\u0642\u0642 \u0645\u0646 \u0641\u062a\u062d \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0645\u0633\u062a\u0639\u0627\u062f\u0629...");
                }
                SQLiteDatabase writableDatabase = AppDatabase.INSTANCE.getInstance(this.context).getWritableDatabase();
                Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
                validateOpenDatabase(writableDatabase);
                if (onProgress != null) {
                    onProgress.invoke("\u0627\u0643\u062a\u0645\u0644\u062a \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0648\u0627\u0644\u062a\u062d\u0642\u0642 \u0645\u0646 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a.");
                    Unit unit = Unit.INSTANCE;
                }
                stagedSqlCipherBackupStageBackupForSqlCipherRestore$app_release.close();
            } catch (Throwable th) {
                stagedSqlCipherBackupStageBackupForSqlCipherRestore$app_release.close();
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit restoreBackup$lambda$4$lambda$3(BackupRestoreManager backupRestoreManager) {
        AppDatabase.INSTANCE.getInstance(backupRestoreManager.context).checkpointAndCloseForBackup$app_release();
        return Unit.INSTANCE;
    }

    private final void createPlaintextFixtureBackup(String actorUserId, char[] password, OutputStream destination, Function1<? super String, Unit> onProgress) {
        requireBackupPermission(actorUserId);
        synchronized (LOCK) {
            File file = new File(this.context.getCacheDir(), "tds-test-backup-snapshot-" + System.nanoTime() + ".db");
            File file2 = new File(this.context.getCacheDir(), "tds-test-backup-encrypted-" + System.nanoTime() + ".bin");
            try {
                AppDatabase.INSTANCE.getInstance(this.context).checkpointAndCloseForBackup$app_release();
                File databasePath = this.context.getDatabasePath(AppDatabase.DATABASE_NAME);
                if (!databasePath.isFile() || databasePath.length() <= 0) {
                    throw new IllegalStateException("\u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629 \u0623\u0648 \u0641\u0627\u0631\u063a\u0629".toString());
                }
                if (databasePath.length() > MAX_DATABASE_BYTES) {
                    throw new IllegalStateException("\u062d\u062c\u0645 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u064a\u062a\u062c\u0627\u0648\u0632 \u0627\u0644\u062d\u062f \u0627\u0644\u0645\u062f\u0639\u0648\u0645 \u0644\u0644\u0646\u0633\u062e \u0627\u0644\u0627\u062d\u062a\u064a\u0627\u0637\u064a".toString());
                }
                if (onProgress != null) {
                    onProgress.invoke("\u0646\u0633\u062e \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0627\u062e\u062a\u0628\u0627\u0631 \u0625\u0644\u0649 \u0645\u0644\u0641 \u0645\u0624\u0642\u062a...");
                }
                Intrinsics.checkNotNull(databasePath);
                copyFile(databasePath, file);
                encryptSnapshot(file, file2, password, TEST_FIXTURE_PBKDF2_ITERATIONS);
                FileInputStream fileInputStream = new FileInputStream(file2);
                try {
                    ByteStreamsKt.copyTo$default(fileInputStream, destination, 0, 2, null);
                    CloseableKt.closeFinally(fileInputStream, null);
                    destination.flush();
                    file.delete();
                    file2.delete();
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(fileInputStream, th);
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                file.delete();
                file2.delete();
                throw th3;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void restorePlaintextFixtureBackup(String actorUserId, char[] password, InputStream source, Function1<? super String, Unit> onProgress) {
        requireBackupPermission(actorUserId);
        synchronized (LOCK) {
            File file = new File(this.context.getDatabasePath(AppDatabase.DATABASE_NAME).getParentFile(), "the_daily_shop.test-restore-" + System.nanoTime() + ".db");
            File databasePath = this.context.getDatabasePath(AppDatabase.DATABASE_NAME);
            File file2 = new File(databasePath.getParentFile(), databasePath.getName() + ".restore-rollback");
            boolean z = true;
            boolean z2 = false;
            try {
                try {
                    decryptToFile(source, file, password, true);
                    validateDatabase(file);
                    AppDatabase.INSTANCE.getInstance(this.context).checkpointAndCloseForBackup$app_release();
                    Intrinsics.checkNotNull(databasePath);
                    deleteSidecars(databasePath);
                    if (file2.exists() && !file2.delete()) {
                        throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0625\u0632\u0627\u0644\u0629 \u0645\u0644\u0641 \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0627\u0644\u0645\u0624\u0642\u062a \u0627\u0644\u0633\u0627\u0628\u0642");
                    }
                    if (!databasePath.exists()) {
                        z = false;
                    } else if (!databasePath.renameTo(file2)) {
                        throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u062d\u0641\u0638 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0627\u062e\u062a\u0628\u0627\u0631 \u0627\u0644\u062d\u0627\u0644\u064a\u0629 \u0644\u0644\u062a\u0631\u0627\u062c\u0639".toString());
                    }
                    try {
                        if (!file.renameTo(databasePath)) {
                            throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0648\u0636\u0639 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0627\u062e\u062a\u0628\u0627\u0631 \u0627\u0644\u0645\u0633\u062a\u0639\u0627\u062f\u0629".toString());
                        }
                        try {
                            SQLiteDatabase writableDatabase = AppDatabase.INSTANCE.getInstance(this.context).getWritableDatabase();
                            Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
                            validateOpenDatabase(writableDatabase);
                            file2.delete();
                            file.delete();
                            if (file2.exists() && databasePath.exists()) {
                                file2.delete();
                            }
                            if (onProgress != null) {
                                onProgress.invoke("\u0627\u0643\u062a\u0645\u0644\u062a \u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0627\u062e\u062a\u0628\u0627\u0631.");
                                Unit unit = Unit.INSTANCE;
                            }
                        } catch (Exception e) {
                            AppDatabase.INSTANCE.getInstance(this.context).close();
                            databasePath.delete();
                            deleteSidecars(databasePath);
                            if (z && file2.exists() && !file2.renameTo(databasePath)) {
                                throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0627\u062e\u062a\u0628\u0627\u0631 \u0627\u0644\u0633\u0627\u0628\u0642\u0629".toString());
                            }
                            AppDatabase.INSTANCE.getInstance(this.context).getWritableDatabase();
                            throw new IllegalStateException("\u0641\u0634\u0644\u062a \u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0627\u062e\u062a\u0628\u0627\u0631\u061b \u0623\u064f\u0639\u064a\u062f\u062a \u0627\u0644\u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0633\u0627\u0628\u0642\u0629", e);
                        }
                    } catch (Exception e2) {
                        e = e2;
                        z2 = z;
                        if (z2 && file2.exists() && !databasePath.exists() && !file2.renameTo(databasePath)) {
                            throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0627\u0644\u062a\u0631\u0627\u062c\u0639 \u0639\u0646 \u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0627\u062e\u062a\u0628\u0627\u0631".toString());
                        }
                        throw e;
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            } catch (Throwable th) {
                file.delete();
                if (file2.exists() && databasePath.exists()) {
                    file2.delete();
                }
                throw th;
            }
        }
    }

    public final void recoverInterruptedRestore() {
        File[] fileArrListFiles;
        Object objM381constructorimpl;
        Object objM381constructorimpl2;
        synchronized (LOCK) {
            AppDatabase.INSTANCE.resetInstanceAfterEncryptedRestore$app_release();
            File databasePath = this.context.getDatabasePath(AppDatabase.DATABASE_NAME);
            File file = new File(databasePath.getParentFile(), databasePath.getName() + ".restore-rollback");
            DatabaseKeyVault databaseKeyVault = new DatabaseKeyVault(this.context, null, null, 6, null);
            if (file.exists()) {
                if (databasePath.isFile()) {
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        BackupRestoreManager backupRestoreManager = this;
                        Intrinsics.checkNotNull(databasePath);
                        validateDatabaseForRecovery(databasePath, databaseKeyVault);
                        objM381constructorimpl = Result.m381constructorimpl(Unit.INSTANCE);
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m388isSuccessimpl(objM381constructorimpl)) {
                        if (!file.delete() && file.exists()) {
                            throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u062a\u0646\u0638\u064a\u0641 \u0646\u0633\u062e\u0629 \u0627\u0644\u062a\u0631\u0627\u062c\u0639 \u0628\u0639\u062f \u0627\u0644\u062a\u062d\u0642\u0642 \u0645\u0646 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u062d\u0627\u0644\u064a\u0629".toString());
                        }
                    }
                }
                try {
                    Result.Companion companion3 = Result.INSTANCE;
                    BackupRestoreManager backupRestoreManager2 = this;
                    validateDatabaseForRecovery(file, databaseKeyVault);
                    objM381constructorimpl2 = Result.m381constructorimpl(Unit.INSTANCE);
                } catch (Throwable th2) {
                    Result.Companion companion4 = Result.INSTANCE;
                    objM381constructorimpl2 = Result.m381constructorimpl(ResultKt.createFailure(th2));
                }
                if (!Result.m388isSuccessimpl(objM381constructorimpl2)) {
                    throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0627\u0644\u062a\u0639\u0627\u0641\u064a: \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u062d\u0627\u0644\u064a\u0629 \u0648\u0646\u0633\u062e\u0629 \u0627\u0644\u062a\u0631\u0627\u062c\u0639 \u063a\u064a\u0631 \u0633\u0644\u064a\u0645\u062a\u064a\u0646\u061b \u0627\u062d\u062a\u0641\u0638 \u0628\u0627\u0644\u0645\u0644\u0641\u0627\u062a \u0648\u0644\u0627 \u062a\u062d\u0630\u0641\u0647\u0627".toString());
                }
                Intrinsics.checkNotNull(databasePath);
                deleteSidecars(databasePath);
                if (databasePath.exists() && !databasePath.delete()) {
                    throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0625\u0632\u0627\u0644\u0629 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u063a\u064a\u0631 \u0627\u0644\u0633\u0644\u064a\u0645\u0629".toString());
                }
                if (!file.renameTo(databasePath)) {
                    throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0633\u0627\u0628\u0642\u0629 \u0628\u0639\u062f \u0627\u0646\u0642\u0637\u0627\u0639 \u0627\u0644\u062a\u0637\u0628\u064a\u0642".toString());
                }
            }
            String name = databasePath.getName();
            Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
            String str = StringsKt.removeSuffix(name, (CharSequence) ".db") + ".restore-";
            File parentFile = databasePath.getParentFile();
            if (parentFile != null && (fileArrListFiles = parentFile.listFiles()) != null) {
                ArrayList arrayList = new ArrayList();
                for (File file2 : fileArrListFiles) {
                    String name2 = file2.getName();
                    Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
                    if (StringsKt.startsWith$default(name2, str, false, 2, (Object) null)) {
                        Intrinsics.checkNotNull(file2);
                        if (Intrinsics.areEqual(FilesKt.getExtension(file2), "db")) {
                            arrayList.add(file2);
                        }
                    }
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((File) it.next()).delete();
                }
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    private final void validateDatabaseForRecovery(File file, DatabaseKeyVault keyVault) throws IOException {
        if (!file.isFile() || file.length() <= 0) {
            throw new IllegalArgumentException("\u0645\u0644\u0641 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0623\u0648 \u0641\u0627\u0631\u063a".toString());
        }
        byte[] bytes = "SQLite format 3\u0000".getBytes(Charsets.US_ASCII);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            int length = bytes.length;
            byte[] bArr = new byte[length];
            boolean z = fileInputStream.read(bArr) == length && Arrays.equals(bArr, bytes);
            CloseableKt.closeFinally(fileInputStream, null);
            if (z) {
                validateDatabase(file);
                return;
            }
            byte[] importedDatabaseKeyForRestoreRecovery$app_release = keyVault.getImportedDatabaseKeyForRestoreRecovery$app_release();
            if (importedDatabaseKeyForRestoreRecovery$app_release == null) {
                throw new IllegalStateException("\u0645\u0641\u062a\u0627\u062d \u0642\u0627\u0639\u062f\u0629 SQLCipher \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f");
            }
            try {
                SqlCipherDatabaseMigrator.validateEncryptedDatabase$default(SqlCipherDatabaseMigrator.INSTANCE, file, importedDatabaseKeyForRestoreRecovery$app_release, null, 4, null);
            } finally {
                ArraysKt.fill$default(importedDatabaseKeyForRestoreRecovery$app_release, (byte) 0, 0, 0, 6, (Object) null);
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(fileInputStream, th);
                throw th2;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void requireBackupPermission(String actorUserId) {
        PermissionPolicy permissionPolicy = PermissionPolicy.INSTANCE;
        SQLiteDatabase readableDatabase = AppDatabase.INSTANCE.getInstance(this.context).getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        permissionPolicy.require(readableDatabase, actorUserId, PermissionPolicy.BACKUP_MANAGE);
    }

    static /* synthetic */ void encryptSnapshot$default(BackupRestoreManager backupRestoreManager, File file, File file2, char[] cArr, int i, int i2, Object obj) throws IOException {
        if ((i2 & 8) != 0) {
            i = PBKDF2_ITERATIONS;
        }
        backupRestoreManager.encryptSnapshot(file, file2, cArr, i);
    }

    private final void encryptSnapshot(File snapshot, File target, char[] password, int iterations) throws IOException {
        byte[] bArr = new byte[16];
        SecureRandom secureRandom = SECURE_RANDOM;
        secureRandom.nextBytes(bArr);
        byte[] bArr2 = new byte[12];
        secureRandom.nextBytes(bArr2);
        byte[] bArrBuildHeader = buildHeader(bArr, bArr2, iterations);
        Cipher cipher = cipher(1, password, bArr, bArr2, iterations);
        cipher.updateAAD(bArrBuildHeader);
        FileOutputStream fileOutputStream = new FileOutputStream(target);
        try {
            FileOutputStream fileOutputStream2 = fileOutputStream;
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream2);
            bufferedOutputStream.write(bArrBuildHeader);
            CipherOutputStream cipherOutputStream = new CipherOutputStream(new FilterOutputStream(bufferedOutputStream) { // from class: com.hazem.dailystore.data.BackupRestoreManager$encryptSnapshot$1$nonClosing$1
                {
                    super(bufferedOutputStream);
                }

                @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                    flush();
                }
            }, cipher);
            try {
                CipherOutputStream cipherOutputStream2 = cipherOutputStream;
                FileInputStream fileInputStream = new FileInputStream(snapshot);
                try {
                    ByteStreamsKt.copyTo$default(fileInputStream, cipherOutputStream2, 0, 2, null);
                    CloseableKt.closeFinally(fileInputStream, null);
                    CloseableKt.closeFinally(cipherOutputStream, null);
                    bufferedOutputStream.flush();
                    fileOutputStream2.getFD().sync();
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(fileOutputStream, null);
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(fileInputStream, th);
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                try {
                    throw th3;
                } catch (Throwable th4) {
                    CloseableKt.closeFinally(cipherOutputStream, th3);
                    throw th4;
                }
            }
        } catch (Throwable th5) {
            try {
                throw th5;
            } catch (Throwable th6) {
                CloseableKt.closeFinally(fileOutputStream, th5);
                throw th6;
            }
        }
    }

    static /* synthetic */ void decryptToFile$default(BackupRestoreManager backupRestoreManager, InputStream inputStream, File file, char[] cArr, boolean z, int i, Object obj) throws IOException {
        if ((i & 8) != 0) {
            z = false;
        }
        backupRestoreManager.decryptToFile(inputStream, file, cArr, z);
    }

    private final void decryptToFile(InputStream source, File target, char[] password, boolean allowTestFixtureIterations) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(source));
        byte[] bArr = new byte[44];
        dataInputStream.readFully(bArr);
        ParsedHeader header = parseHeader(bArr, allowTestFixtureIterations);
        Cipher cipher = cipher(2, password, header.getSalt(), header.getIv(), header.getIterations());
        cipher.updateAAD(bArr);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(target);
            try {
                FileOutputStream fileOutputStream2 = fileOutputStream;
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream2);
                CipherInputStream cipherInputStream = new CipherInputStream(dataInputStream, cipher);
                try {
                    CipherInputStream cipherInputStream2 = cipherInputStream;
                    byte[] bArr2 = new byte[8192];
                    long j = 0;
                    while (true) {
                        int i = cipherInputStream2.read(bArr2);
                        if (i < 0) {
                            Unit unit = Unit.INSTANCE;
                            CloseableKt.closeFinally(cipherInputStream, null);
                            bufferedOutputStream.flush();
                            fileOutputStream2.getFD().sync();
                            Unit unit2 = Unit.INSTANCE;
                            CloseableKt.closeFinally(fileOutputStream, null);
                            if (!target.isFile() || target.length() <= 0) {
                                throw new IllegalArgumentException("\u0645\u0644\u0641 \u0627\u0644\u0646\u0633\u062e\u0629 \u0627\u0644\u0627\u062d\u062a\u064a\u0627\u0637\u064a\u0629 \u0641\u0627\u0631\u063a".toString());
                            }
                            return;
                        }
                        j += (long) i;
                        if (j > MAX_DATABASE_BYTES) {
                            throw new IllegalArgumentException("\u062d\u062c\u0645 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0623\u0643\u0628\u0631 \u0645\u0646 \u0627\u0644\u062d\u062f \u0627\u0644\u0645\u0633\u0645\u0648\u062d".toString());
                        }
                        bufferedOutputStream.write(bArr2, 0, i);
                    }
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(cipherInputStream, th);
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                try {
                    throw th3;
                } catch (Throwable th4) {
                    CloseableKt.closeFinally(fileOutputStream, th3);
                    throw th4;
                }
            }
        } catch (Exception e) {
            target.delete();
            throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u0641\u0643 \u062a\u0634\u0641\u064a\u0631 \u0627\u0644\u0646\u0633\u062e\u0629. \u062a\u062d\u0642\u0642 \u0645\u0646 \u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631 \u0648\u0633\u0644\u0627\u0645\u0629 \u0627\u0644\u0645\u0644\u0641", e);
        }
    }

    static /* synthetic */ byte[] buildHeader$default(BackupRestoreManager backupRestoreManager, byte[] bArr, byte[] bArr2, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = PBKDF2_ITERATIONS;
        }
        return backupRestoreManager.buildHeader(bArr, bArr2, i);
    }

    private final byte[] buildHeader(byte[] salt, byte[] iv, int iterations) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            DataOutputStream dataOutputStream2 = dataOutputStream;
            dataOutputStream2.write(MAGIC);
            dataOutputStream2.writeInt(1);
            dataOutputStream2.writeInt(iterations);
            dataOutputStream2.write(salt);
            dataOutputStream2.write(iv);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(dataOutputStream, null);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
            return byteArray;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(dataOutputStream, th);
                throw th2;
            }
        }
    }

    /* JADX INFO: compiled from: BackupRestoreManager.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0006H\u00c6\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0006H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0018"}, d2 = {"Lcom/hazem/dailystore/data/BackupRestoreManager$ParsedHeader;", "", "salt", "", "iv", "iterations", "", "<init>", "([B[BI)V", "getSalt", "()[B", "getIv", "getIterations", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class ParsedHeader {
        private final int iterations;
        private final byte[] iv;
        private final byte[] salt;

        public static /* synthetic */ ParsedHeader copy$default(ParsedHeader parsedHeader, byte[] bArr, byte[] bArr2, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                bArr = parsedHeader.salt;
            }
            if ((i2 & 2) != 0) {
                bArr2 = parsedHeader.iv;
            }
            if ((i2 & 4) != 0) {
                i = parsedHeader.iterations;
            }
            return parsedHeader.copy(bArr, bArr2, i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final byte[] getSalt() {
            return this.salt;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final byte[] getIv() {
            return this.iv;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getIterations() {
            return this.iterations;
        }

        public final ParsedHeader copy(byte[] salt, byte[] iv, int iterations) {
            Intrinsics.checkNotNullParameter(salt, "salt");
            Intrinsics.checkNotNullParameter(iv, "iv");
            return new ParsedHeader(salt, iv, iterations);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ParsedHeader)) {
                return false;
            }
            ParsedHeader parsedHeader = (ParsedHeader) other;
            return Intrinsics.areEqual(this.salt, parsedHeader.salt) && Intrinsics.areEqual(this.iv, parsedHeader.iv) && this.iterations == parsedHeader.iterations;
        }

        public int hashCode() {
            return (((Arrays.hashCode(this.salt) * 31) + Arrays.hashCode(this.iv)) * 31) + this.iterations;
        }

        public String toString() {
            return "ParsedHeader(salt=" + Arrays.toString(this.salt) + ", iv=" + Arrays.toString(this.iv) + ", iterations=" + this.iterations + ")";
        }

        public ParsedHeader(byte[] salt, byte[] iv, int i) {
            Intrinsics.checkNotNullParameter(salt, "salt");
            Intrinsics.checkNotNullParameter(iv, "iv");
            this.salt = salt;
            this.iv = iv;
            this.iterations = i;
        }

        public final int getIterations() {
            return this.iterations;
        }

        public final byte[] getIv() {
            return this.iv;
        }

        public final byte[] getSalt() {
            return this.salt;
        }
    }

    static /* synthetic */ ParsedHeader parseHeader$default(BackupRestoreManager backupRestoreManager, byte[] bArr, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return backupRestoreManager.parseHeader(bArr, z);
    }

    private final ParsedHeader parseHeader(byte[] header, boolean allowTestFixtureIterations) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(header));
        byte[] bArr = MAGIC;
        byte[] bArr2 = new byte[bArr.length];
        dataInputStream.readFully(bArr2);
        if (!Arrays.equals(bArr2, bArr)) {
            throw new IllegalArgumentException("\u0627\u0644\u0645\u0644\u0641 \u0644\u064a\u0633 \u0646\u0633\u062e\u0629 \u0627\u062d\u062a\u064a\u0627\u0637\u064a\u0629 \u0645\u0639\u0631\u0648\u0641\u0629 \u0645\u0646 The Daily Shop".toString());
        }
        if (dataInputStream.readInt() != 1) {
            throw new IllegalArgumentException("\u0625\u0635\u062f\u0627\u0631 \u0645\u0644\u0641 \u0627\u0644\u0646\u0633\u062e\u0629 \u0627\u0644\u0627\u062d\u062a\u064a\u0627\u0637\u064a\u0629 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645".toString());
        }
        int i = dataInputStream.readInt();
        if ((allowTestFixtureIterations ? 1 : MIN_PBKDF2_ITERATIONS) > i || i >= 500001) {
            throw new IllegalArgumentException("\u0625\u0639\u062f\u0627\u062f\u0627\u062a \u062d\u0645\u0627\u064a\u0629 \u0627\u0644\u0646\u0633\u062e\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
        }
        byte[] bArr3 = new byte[16];
        dataInputStream.readFully(bArr3);
        byte[] bArr4 = new byte[12];
        dataInputStream.readFully(bArr4);
        return new ParsedHeader(bArr3, bArr4, i);
    }

    static /* synthetic */ Cipher cipher$default(BackupRestoreManager backupRestoreManager, int i, char[] cArr, byte[] bArr, byte[] bArr2, int i2, int i3, Object obj) {
        if ((i3 & 16) != 0) {
            i2 = PBKDF2_ITERATIONS;
        }
        return backupRestoreManager.cipher(i, cArr, bArr, bArr2, i2);
    }

    private final Cipher cipher(int mode, char[] password, byte[] salt, byte[] iv, int iterations) {
        PBEKeySpec pBEKeySpec = new PBEKeySpec(password, salt, iterations, 256);
        try {
            byte[] encoded = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(pBEKeySpec).getEncoded();
            pBEKeySpec.clearPassword();
            try {
                Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
                cipher.init(mode, new SecretKeySpec(encoded, "AES"), new GCMParameterSpec(128, iv));
                return cipher;
            } finally {
                Intrinsics.checkNotNull(encoded);
                ArraysKt.fill$default(encoded, (byte) 0, 0, 0, 6, (Object) null);
            }
        } catch (Throwable th) {
            pBEKeySpec.clearPassword();
            throw th;
        }
    }

    private final void validateDatabase(File file) {
        SqlCipherDatabaseRuntime.INSTANCE.loadNativeLibrary();
        SQLiteDatabase sQLiteDatabaseOpenDatabase = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, 1);
        try {
            int version = sQLiteDatabaseOpenDatabase.getVersion();
            if (1 > version || version >= 34) {
                throw new IllegalArgumentException("\u0625\u0635\u062f\u0627\u0631 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u062f\u0627\u062e\u0644 \u0627\u0644\u0646\u0633\u062e\u0629 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645".toString());
            }
            Intrinsics.checkNotNull(sQLiteDatabaseOpenDatabase);
            validateOpenDatabase(sQLiteDatabaseOpenDatabase);
            sQLiteDatabaseOpenDatabase.close();
        } catch (Throwable th) {
            sQLiteDatabaseOpenDatabase.close();
            throw th;
        }
    }

    private final void validateOpenDatabase(SQLiteDatabase db) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("PRAGMA integrity_check", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            if (!cursor.moveToFirst() || !StringsKt.equals(cursor.getString(0), "ok", true)) {
                throw new IllegalArgumentException("\u0641\u0634\u0644 \u0641\u062d\u0635 \u0633\u0644\u0627\u0645\u0629 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a".toString());
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            Cursor cursorRawQuery2 = db.rawQuery("PRAGMA foreign_key_check", (String[]) null);
            try {
                if (cursorRawQuery2.moveToFirst()) {
                    throw new IllegalArgumentException("\u062a\u062d\u062a\u0648\u064a \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0639\u0644\u0649 \u0639\u0644\u0627\u0642\u0627\u062a \u063a\u064a\u0631 \u0633\u0644\u064a\u0645\u0629".toString());
                }
                Unit unit2 = Unit.INSTANCE;
                CloseableKt.closeFinally(cursorRawQuery2, null);
                for (String str : CollectionsKt.listOf((Object[]) new String[]{"users", "products", "app_settings", "sales_invoices"})) {
                    Cursor cursorRawQuery3 = db.rawQuery("SELECT 1 FROM sqlite_master WHERE type='table' AND name=?", new String[]{str});
                    try {
                        if (!cursorRawQuery3.moveToFirst()) {
                            throw new IllegalArgumentException(("\u0627\u0644\u0646\u0633\u062e\u0629 \u0644\u0627 \u062a\u062d\u062a\u0648\u064a \u0639\u0644\u0649 \u062c\u062f\u0648\u0644 \u0623\u0633\u0627\u0633\u064a: " + str).toString());
                        }
                        Unit unit3 = Unit.INSTANCE;
                        CloseableKt.closeFinally(cursorRawQuery3, null);
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            CloseableKt.closeFinally(cursorRawQuery3, th);
                            throw th2;
                        }
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

    private final void validatePassword(char[] password) {
        if (password.length < 10) {
            throw new IllegalArgumentException("\u0643\u0644\u0645\u0629 \u0645\u0631\u0648\u0631 \u0627\u0644\u0646\u0633\u062e\u0629 \u0627\u0644\u0627\u062d\u062a\u064a\u0627\u0637\u064a\u0629 \u064a\u062c\u0628 \u0623\u0644\u0627 \u062a\u0642\u0644 \u0639\u0646 10 \u0623\u062d\u0631\u0641".toString());
        }
    }

    private final void deleteSidecars(File database) {
        new File(database.getParentFile(), database.getName() + "-wal").delete();
        new File(database.getParentFile(), database.getName() + "-shm").delete();
        new File(database.getParentFile(), database.getName() + "-journal").delete();
    }

    private final void copyFile(File source, File target) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(source);
        try {
            FileInputStream fileInputStream2 = fileInputStream;
            FileOutputStream fileOutputStream = new FileOutputStream(target);
            try {
                ByteStreamsKt.copyTo$default(fileInputStream2, fileOutputStream, 0, 2, null);
                CloseableKt.closeFinally(fileOutputStream, null);
                CloseableKt.closeFinally(fileInputStream, null);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(fileOutputStream, th);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            try {
                throw th3;
            } catch (Throwable th4) {
                CloseableKt.closeFinally(fileInputStream, th3);
                throw th4;
            }
        }
    }
}
