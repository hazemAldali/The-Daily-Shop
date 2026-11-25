package com.hazem.dailystore.data;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SyncRelaySession.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\tJ\u0006\u0010\u0010\u001a\u00020\tJ\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\tJ\u0006\u0010\u0014\u001a\u00020\tR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/hazem/dailystore/data/SyncRelaySession;", "", "<init>", "()V", "runtime", "Lcom/hazem/dailystore/data/SyncRelayRuntime;", "endpoint", "", "connect", "", "context", "Landroid/content/Context;", "wssEndpoint", "repository", "Lcom/hazem/dailystore/data/SyncProtocolRepository;", "refreshDeviceRegistry", "flushPending", "isConnected", "", "disconnect", "shutdown", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SyncRelaySession {
    public static final SyncRelaySession INSTANCE = new SyncRelaySession();
    private static volatile String endpoint;
    private static volatile SyncRelayRuntime runtime;

    private SyncRelaySession() {
    }

    public final synchronized void connect(Context context, String wssEndpoint, SyncProtocolRepository repository) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(wssEndpoint, "wssEndpoint");
        Intrinsics.checkNotNullParameter(repository, "repository");
        if (!StringsKt.startsWith(wssEndpoint, "wss://", true)) {
            throw new IllegalArgumentException("\u064a\u062c\u0628 \u0627\u0633\u062a\u062e\u062f\u0627\u0645 \u0639\u0646\u0648\u0627\u0646 WSS \u0645\u0634\u0641\u0631".toString());
        }
        if (runtime == null || !Intrinsics.areEqual(endpoint, wssEndpoint)) {
            SyncRelayRuntime syncRelayRuntime = runtime;
            if (syncRelayRuntime != null) {
                syncRelayRuntime.shutdown();
            }
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            runtime = new SyncRelayRuntime(applicationContext, wssEndpoint, repository, null, 8, null);
            endpoint = wssEndpoint;
        }
        SyncRelayRuntime syncRelayRuntime2 = runtime;
        if (syncRelayRuntime2 != null) {
            syncRelayRuntime2.connect();
        }
    }

    public final synchronized void refreshDeviceRegistry() {
        SyncRelayRuntime syncRelayRuntime = runtime;
        if (syncRelayRuntime != null) {
            syncRelayRuntime.refreshDeviceRegistry();
        }
    }

    public final synchronized void flushPending() {
        SyncRelayRuntime syncRelayRuntime = runtime;
        if (syncRelayRuntime != null) {
            syncRelayRuntime.flushPending();
        }
    }

    public final synchronized boolean isConnected() {
        boolean z;
        SyncRelayRuntime syncRelayRuntime = runtime;
        z = false;
        if (syncRelayRuntime != null && syncRelayRuntime.isConnected()) {
            z = true;
        }
        return z;
    }

    public final synchronized void disconnect() {
        SyncRelayRuntime syncRelayRuntime = runtime;
        if (syncRelayRuntime != null) {
            syncRelayRuntime.disconnect();
        }
    }

    public final synchronized void shutdown() {
        SyncRelayRuntime syncRelayRuntime = runtime;
        if (syncRelayRuntime != null) {
            syncRelayRuntime.shutdown();
        }
        runtime = null;
        endpoint = null;
    }
}
