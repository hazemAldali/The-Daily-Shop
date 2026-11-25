package com.hazem.dailystore.data;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SqlCipherDatabaseRuntime.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c0\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0006\u001a\u00020\u0007J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\r\u0010\u000e\u001a\u00020\u0007H\u0000\u00a2\u0006\u0002\b\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/hazem/dailystore/data/SqlCipherDatabaseRuntime;", "", "<init>", "()V", "nativeLibraryLoaded", "", "loadNativeLibrary", "", "getProductionDatabaseKey", "", "keyVault", "Lcom/hazem/dailystore/data/DatabaseKeyVault;", "databaseFile", "Ljava/io/File;", "resetLoadedStateForTests", "resetLoadedStateForTests$app_release", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SqlCipherDatabaseRuntime {
    public static final SqlCipherDatabaseRuntime INSTANCE = new SqlCipherDatabaseRuntime();
    private static volatile boolean nativeLibraryLoaded;

    private SqlCipherDatabaseRuntime() {
    }

    public final synchronized void loadNativeLibrary() {
        if (nativeLibraryLoaded) {
            return;
        }
        System.loadLibrary("sqlcipher");
        nativeLibraryLoaded = true;
    }

    public final byte[] getProductionDatabaseKey(DatabaseKeyVault keyVault, File databaseFile) {
        Intrinsics.checkNotNullParameter(keyVault, "keyVault");
        Intrinsics.checkNotNullParameter(databaseFile, "databaseFile");
        loadNativeLibrary();
        return keyVault.getOrCreateDatabaseKey(databaseFile.exists());
    }

    public final void resetLoadedStateForTests$app_release() {
        nativeLibraryLoaded = false;
    }
}
