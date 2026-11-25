package com.hazem.dailystore.data;

import java.io.File;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BackupRestoreManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\b\u0010\r\u001a\u00020\u000eH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u000f"}, d2 = {"Lcom/hazem/dailystore/data/StagedSqlCipherBackup;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "databaseFile", "Ljava/io/File;", "databaseKey", "", "<init>", "(Ljava/io/File;[B)V", "getDatabaseFile", "()Ljava/io/File;", "getDatabaseKey", "()[B", "close", "", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class StagedSqlCipherBackup implements AutoCloseable {
    private final File databaseFile;
    private final byte[] databaseKey;

    public StagedSqlCipherBackup(File databaseFile, byte[] databaseKey) {
        Intrinsics.checkNotNullParameter(databaseFile, "databaseFile");
        Intrinsics.checkNotNullParameter(databaseKey, "databaseKey");
        this.databaseFile = databaseFile;
        this.databaseKey = databaseKey;
    }

    public final File getDatabaseFile() {
        return this.databaseFile;
    }

    public final byte[] getDatabaseKey() {
        return this.databaseKey;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        ArraysKt.fill$default(this.databaseKey, (byte) 0, 0, 0, 6, (Object) null);
        this.databaseFile.delete();
    }
}
