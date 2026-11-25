package com.hazem.dailystore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c2\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005\u00a2\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u000e"}, d2 = {"Lcom/hazem/dailystore/BackupRestoreWorker;", "", "<init>", "()V", "executor", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "getExecutor", "()Ljava/util/concurrent/ExecutorService;", "Ljava/util/concurrent/ExecutorService;", "running", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getRunning", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class BackupRestoreWorker {
    public static final BackupRestoreWorker INSTANCE = new BackupRestoreWorker();
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();
    private static final AtomicBoolean running = new AtomicBoolean(false);

    private BackupRestoreWorker() {
    }

    public final ExecutorService getExecutor() {
        return executor;
    }

    public final AtomicBoolean getRunning() {
        return running;
    }
}
