package com.hazem.dailystore;

import android.app.Application;
import android.content.Context;
import com.hazem.dailystore.data.AppDatabase;
import com.hazem.dailystore.data.BackupRestoreManager;
import com.hazem.dailystore.data.DatabaseKeyVault;
import com.hazem.dailystore.data.SqlCipherLegacyMigrationCoordinator;
import com.hazem.dailystore.data.SqlCipherSameDeviceRestoreCoordinator;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TheDailyShopApplication.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0002\u00a8\u0006\b"}, d2 = {"Lcom/hazem/dailystore/TheDailyShopApplication;", "Landroid/app/Application;", "<init>", "()V", "onCreate", "", "recoverDatabasesBeforeAnyConsumer", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TheDailyShopApplication extends Application {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final String LEGACY_MIGRATION_JOURNAL = "the_daily_shop_sqlcipher_migration.journal";

    @Deprecated
    public static final String SAME_DEVICE_RESTORE_JOURNAL = "the_daily_shop_same_device_restore.journal";

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        recoverDatabasesBeforeAnyConsumer();
    }

    private final void recoverDatabasesBeforeAnyConsumer() {
        File file = new File(getNoBackupFilesDir(), LEGACY_MIGRATION_JOURNAL);
        File file2 = new File(file.getPath() + ".bak");
        File file3 = new File(getNoBackupFilesDir(), SAME_DEVICE_RESTORE_JOURNAL);
        File file4 = new File(file3.getPath() + ".bak");
        boolean z = file.exists() || file2.exists();
        boolean z2 = file3.exists() || file4.exists();
        if (z && z2) {
            throw new IllegalStateException("\u062a\u0648\u062c\u062f \u0639\u0645\u0644\u064a\u062a\u0627 \u062a\u0639\u0627\u0641\u064d \u0645\u062a\u062f\u0627\u062e\u0644\u062a\u0627\u0646 \u0644\u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a\u061b \u0623\u064f\u0648\u0642\u0641 \u0627\u0644\u062a\u0634\u063a\u064a\u0644 \u0644\u062d\u0645\u0627\u064a\u0629 \u0627\u0644\u0645\u0644\u0641\u0627\u062a".toString());
        }
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        DatabaseKeyVault databaseKeyVault = new DatabaseKeyVault(applicationContext, null, null, 6, null);
        File databasePath = getDatabasePath(AppDatabase.DATABASE_NAME);
        Context applicationContext2 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext2, "getApplicationContext(...)");
        SqlCipherLegacyMigrationCoordinator sqlCipherLegacyMigrationCoordinator = new SqlCipherLegacyMigrationCoordinator(applicationContext2, null, null, 6, null);
        Intrinsics.checkNotNull(databasePath);
        sqlCipherLegacyMigrationCoordinator.recover(databasePath, databaseKeyVault);
        if (z2) {
            Context applicationContext3 = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext3, "getApplicationContext(...)");
            new SqlCipherSameDeviceRestoreCoordinator(applicationContext3, null, null, 6, null).recover(databaseKeyVault);
        }
        Context applicationContext4 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext4, "getApplicationContext(...)");
        new BackupRestoreManager(applicationContext4).recoverInterruptedRestore();
    }

    /* JADX INFO: compiled from: TheDailyShopApplication.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/hazem/dailystore/TheDailyShopApplication$Companion;", "", "<init>", "()V", "LEGACY_MIGRATION_JOURNAL", "", "SAME_DEVICE_RESTORE_JOURNAL", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
