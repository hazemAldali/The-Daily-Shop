package com.hazem.dailystore.data;

import android.content.Context;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlin.text.RegexOption;

/* JADX INFO: compiled from: SyncRelayProcessor.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000m\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0014\n\u0002\u0010\u0003\n\u0002\b\u0002*\u0001\u001e\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0006\u0010\"\u001a\u00020#J\u0006\u0010$\u001a\u00020#J\u0006\u0010'\u001a\u00020#J\b\u0010(\u001a\u00020#H\u0002J\u0006\u0010)\u001a\u00020#J\b\u0010*\u001a\u00020#H\u0002J\b\u0010+\u001a\u00020#H\u0002J\b\u0010,\u001a\u00020#H\u0016J \u0010-\u001a\u00020#2\u0006\u0010.\u001a\u00020\u00052\u0006\u0010/\u001a\u00020\u00052\u0006\u00100\u001a\u00020\u0005H\u0016J \u00101\u001a\u00020#2\u0006\u0010/\u001a\u00020\u00052\u0006\u00102\u001a\u00020\u00162\u0006\u00103\u001a\u00020\u0016H\u0016J\u0010\u00104\u001a\u00020#2\u0006\u00105\u001a\u00020\u0005H\u0016J\u0012\u00106\u001a\u00020#2\b\u00107\u001a\u0004\u0018\u000108H\u0016J\u0006\u00109\u001a\u00020#R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0018\u0010\u0011\u001a\n \u0013*\u0004\u0018\u00010\u00120\u0012X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u00020\u001eX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u001fR\u000e\u0010 \u001a\u00020!X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010%\u001a\u00020\u00168F\u00a2\u0006\u0006\u001a\u0004\b%\u0010&\u00a8\u0006:"}, d2 = {"Lcom/hazem/dailystore/data/SyncRelayRuntime;", "Lcom/hazem/dailystore/data/SyncRelayWebSocketClient$Listener;", "context", "Landroid/content/Context;", "relayBaseUrl", "", "repository", "Lcom/hazem/dailystore/data/SyncProtocolRepository;", "listener", "Lcom/hazem/dailystore/data/SyncRelayProcessor$Listener;", "<init>", "(Landroid/content/Context;Ljava/lang/String;Lcom/hazem/dailystore/data/SyncProtocolRepository;Lcom/hazem/dailystore/data/SyncRelayProcessor$Listener;)V", "client", "Lcom/hazem/dailystore/data/SyncRelayWebSocketClient;", "registryBaseUrl", "sendRateLimiter", "Lcom/hazem/dailystore/data/SyncRelaySendRateLimiter;", "scheduler", "Ljava/util/concurrent/ScheduledExecutorService;", "kotlin.jvm.PlatformType", "Ljava/util/concurrent/ScheduledExecutorService;", "desiredConnection", "", "reconnectDelaySeconds", "", "deliveryRetryDelaySeconds", "reconnectTask", "Ljava/util/concurrent/ScheduledFuture;", "deliveryRetryTask", "processorListener", "com/hazem/dailystore/data/SyncRelayRuntime$processorListener$1", "Lcom/hazem/dailystore/data/SyncRelayRuntime$processorListener$1;", "processor", "Lcom/hazem/dailystore/data/SyncRelayProcessor;", "connect", "", "disconnect", "isConnected", "()Z", "flushPending", "prepareAndConnect", "refreshDeviceRegistry", "scheduleReconnect", "scheduleDeliveryRetry", "onReady", "onEncryptedFrame", "fromDeviceId", "frameId", "encryptedFrameJson", "onDeliveryStatus", "deliveredToSocket", "retryable", "onRelayError", "code", "onDisconnected", "cause", "", "shutdown", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SyncRelayRuntime implements SyncRelayWebSocketClient.Listener {
    private SyncRelayWebSocketClient client;
    private long deliveryRetryDelaySeconds;
    private ScheduledFuture<?> deliveryRetryTask;
    private volatile boolean desiredConnection;
    private final SyncRelayProcessor.Listener listener;
    private final SyncRelayProcessor processor;
    private final SyncRelayRuntime$processorListener$1 processorListener;
    private long reconnectDelaySeconds;
    private ScheduledFuture<?> reconnectTask;
    private final String registryBaseUrl;
    private final SyncProtocolRepository repository;
    private final ScheduledExecutorService scheduler;
    private final SyncRelaySendRateLimiter sendRateLimiter;

    /* JADX WARN: Type inference failed for: r15v7, types: [com.hazem.dailystore.data.SyncRelayRuntime$processorListener$1] */
    public SyncRelayRuntime(Context context, String relayBaseUrl, SyncProtocolRepository repository, SyncRelayProcessor.Listener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(relayBaseUrl, "relayBaseUrl");
        Intrinsics.checkNotNullParameter(repository, "repository");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.repository = repository;
        this.listener = listener;
        this.registryBaseUrl = new Regex("^wss://", RegexOption.IGNORE_CASE).replaceFirst(relayBaseUrl, "https://");
        this.sendRateLimiter = new SyncRelaySendRateLimiter(0, 0L, null, 7, null);
        this.scheduler = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() { // from class: com.hazem.dailystore.data.SyncRelayRuntime$$ExternalSyntheticLambda4
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return SyncRelayRuntime.scheduler$lambda$1(runnable);
            }
        });
        this.reconnectDelaySeconds = 1L;
        this.deliveryRetryDelaySeconds = 11L;
        ?? r15 = new SyncRelayProcessor.Listener() { // from class: com.hazem.dailystore.data.SyncRelayRuntime$processorListener$1
            @Override // com.hazem.dailystore.data.SyncRelayProcessor.Listener
            public void onOperationApplied(String operationId) {
                Intrinsics.checkNotNullParameter(operationId, "operationId");
                this.this$0.listener.onOperationApplied(operationId);
            }

            @Override // com.hazem.dailystore.data.SyncRelayProcessor.Listener
            public void onApplicationAckAccepted(String operationId, boolean duplicate) {
                Intrinsics.checkNotNullParameter(operationId, "operationId");
                SyncRelayRuntime syncRelayRuntime = this.this$0;
                synchronized (syncRelayRuntime) {
                    syncRelayRuntime.deliveryRetryDelaySeconds = 11L;
                    Unit unit = Unit.INSTANCE;
                }
                this.this$0.listener.onApplicationAckAccepted(operationId, duplicate);
            }

            @Override // com.hazem.dailystore.data.SyncRelayProcessor.Listener
            public void onRetryRequired(String operationId, String reason) {
                Intrinsics.checkNotNullParameter(operationId, "operationId");
                Intrinsics.checkNotNullParameter(reason, "reason");
                this.this$0.listener.onRetryRequired(operationId, reason);
                this.this$0.scheduleDeliveryRetry();
            }

            @Override // com.hazem.dailystore.data.SyncRelayProcessor.Listener
            public void onProtocolError(String operationId, String reason) {
                Intrinsics.checkNotNullParameter(reason, "reason");
                this.this$0.listener.onProtocolError(operationId, reason);
            }
        };
        this.processorListener = r15;
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        this.processor = new SyncRelayProcessor(repository, new SyncDomainApplier(applicationContext), new Function3() { // from class: com.hazem.dailystore.data.SyncRelayRuntime$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return Boolean.valueOf(SyncRelayRuntime.processor$lambda$2(this.f$0, (String) obj, (String) obj2, (String) obj3));
            }
        }, (SyncRelayProcessor.Listener) r15);
        this.client = new SyncRelayWebSocketClient(relayBaseUrl, repository.storeId(), repository.localDeviceId(), this, null, repository.relayAuthentication(), 16, null);
    }

    public /* synthetic */ SyncRelayRuntime(Context context, String str, SyncProtocolRepository syncProtocolRepository, SyncRelayProcessor.Listener listener, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, str, syncProtocolRepository, (i & 8) != 0 ? new SyncRelayProcessor.Listener() { // from class: com.hazem.dailystore.data.SyncRelayRuntime.1
            @Override // com.hazem.dailystore.data.SyncRelayProcessor.Listener
            public void onApplicationAckAccepted(String str2, boolean z) {
                SyncRelayProcessor.Listener.DefaultImpls.onApplicationAckAccepted(this, str2, z);
            }

            @Override // com.hazem.dailystore.data.SyncRelayProcessor.Listener
            public void onOperationApplied(String str2) {
                SyncRelayProcessor.Listener.DefaultImpls.onOperationApplied(this, str2);
            }

            @Override // com.hazem.dailystore.data.SyncRelayProcessor.Listener
            public void onProtocolError(String str2, String str3) {
                SyncRelayProcessor.Listener.DefaultImpls.onProtocolError(this, str2, str3);
            }

            @Override // com.hazem.dailystore.data.SyncRelayProcessor.Listener
            public void onRetryRequired(String str2, String str3) {
                SyncRelayProcessor.Listener.DefaultImpls.onRetryRequired(this, str2, str3);
            }
        } : listener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Thread scheduler$lambda$1(Runnable runnable) {
        Thread thread = new Thread(runnable, "daily-shop-sync-retry");
        thread.setDaemon(true);
        return thread;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean processor$lambda$2(SyncRelayRuntime syncRelayRuntime, String operationId, String recipientId, String frame) {
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        Intrinsics.checkNotNullParameter(recipientId, "recipientId");
        Intrinsics.checkNotNullParameter(frame, "frame");
        SyncRelayWebSocketClient syncRelayWebSocketClient = syncRelayRuntime.client;
        if (syncRelayWebSocketClient != null) {
            SyncRelayWebSocketClient syncRelayWebSocketClient2 = null;
            if (syncRelayWebSocketClient == null) {
                Intrinsics.throwUninitializedPropertyAccessException("client");
                syncRelayWebSocketClient = null;
            }
            if (syncRelayWebSocketClient.getReady() && syncRelayRuntime.sendRateLimiter.tryAcquire()) {
                SyncRelayWebSocketClient syncRelayWebSocketClient3 = syncRelayRuntime.client;
                if (syncRelayWebSocketClient3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("client");
                } else {
                    syncRelayWebSocketClient2 = syncRelayWebSocketClient3;
                }
                return syncRelayWebSocketClient2.sendEncryptedFrame(operationId, recipientId, frame);
            }
        }
        return false;
    }

    public final synchronized void connect() {
        if (this.desiredConnection) {
            return;
        }
        this.desiredConnection = true;
        this.reconnectDelaySeconds = 1L;
        this.scheduler.execute(new Runnable() { // from class: com.hazem.dailystore.data.SyncRelayRuntime$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.prepareAndConnect();
            }
        });
    }

    public final synchronized void disconnect() {
        this.desiredConnection = false;
        ScheduledFuture<?> scheduledFuture = this.reconnectTask;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        SyncRelayWebSocketClient syncRelayWebSocketClient = null;
        this.reconnectTask = null;
        ScheduledFuture<?> scheduledFuture2 = this.deliveryRetryTask;
        if (scheduledFuture2 != null) {
            scheduledFuture2.cancel(false);
        }
        this.deliveryRetryTask = null;
        SyncRelayWebSocketClient syncRelayWebSocketClient2 = this.client;
        if (syncRelayWebSocketClient2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("client");
        } else {
            syncRelayWebSocketClient = syncRelayWebSocketClient2;
        }
        syncRelayWebSocketClient.disconnect();
    }

    public final boolean isConnected() {
        SyncRelayWebSocketClient syncRelayWebSocketClient = this.client;
        if (syncRelayWebSocketClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("client");
            syncRelayWebSocketClient = null;
        }
        return syncRelayWebSocketClient.getReady();
    }

    public final void flushPending() {
        synchronized (this) {
            if (!this.desiredConnection) {
                connect();
            } else {
                Unit unit = Unit.INSTANCE;
                this.scheduler.execute(new Runnable() { // from class: com.hazem.dailystore.data.SyncRelayRuntime$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        SyncRelayRuntime.flushPending$lambda$7(this.f$0);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void flushPending$lambda$7(SyncRelayRuntime syncRelayRuntime) {
        Object objM381constructorimpl;
        SyncRelayWebSocketClient syncRelayWebSocketClient = syncRelayRuntime.client;
        if (syncRelayWebSocketClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("client");
            syncRelayWebSocketClient = null;
        }
        if (syncRelayWebSocketClient.getReady()) {
            try {
                Result.Companion companion = Result.INSTANCE;
                syncRelayRuntime.processor.onRelayReady();
                objM381constructorimpl = Result.m381constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
            }
            Throwable thM384exceptionOrNullimpl = Result.m384exceptionOrNullimpl(objM381constructorimpl);
            if (thM384exceptionOrNullimpl != null) {
                SyncRelayProcessor.Listener listener = syncRelayRuntime.listener;
                String message = thM384exceptionOrNullimpl.getMessage();
                if (message == null) {
                    message = "\u062a\u0639\u0630\u0631 \u0625\u0631\u0633\u0627\u0644 \u0627\u0644\u0639\u0645\u0644\u064a\u0627\u062a \u0627\u0644\u0645\u0639\u0644\u0642\u0629";
                }
                listener.onProtocolError(null, message);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void prepareAndConnect() {
        try {
            SyncRelayWebSocketClient syncRelayWebSocketClient = null;
            if (Intrinsics.areEqual(this.repository.localDeviceRole(), "PRIMARY")) {
                SyncProtocolRepository.syncDeviceRegistry$default(this.repository, this.registryBaseUrl, null, 2, null);
            }
            synchronized (this) {
                if (this.desiredConnection) {
                    Unit unit = Unit.INSTANCE;
                    SyncRelayWebSocketClient syncRelayWebSocketClient2 = this.client;
                    if (syncRelayWebSocketClient2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("client");
                    } else {
                        syncRelayWebSocketClient = syncRelayWebSocketClient2;
                    }
                    syncRelayWebSocketClient.connect();
                }
            }
        } catch (Exception e) {
            SyncRelayProcessor.Listener listener = this.listener;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u062a\u062c\u0647\u064a\u0632 \u0645\u0635\u0627\u062f\u0642\u0629 \u0648\u0633\u064a\u0637 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629";
            }
            listener.onRetryRequired("", message);
            scheduleReconnect();
        }
    }

    public final void refreshDeviceRegistry() {
        if (Intrinsics.areEqual(this.repository.localDeviceRole(), "PRIMARY")) {
            this.scheduler.execute(new Runnable() { // from class: com.hazem.dailystore.data.SyncRelayRuntime$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    SyncRelayRuntime.refreshDeviceRegistry$lambda$11(this.f$0);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void refreshDeviceRegistry$lambda$11(SyncRelayRuntime syncRelayRuntime) {
        Object objM381constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            objM381constructorimpl = Result.m381constructorimpl(SyncProtocolRepository.syncDeviceRegistry$default(syncRelayRuntime.repository, syncRelayRuntime.registryBaseUrl, null, 2, null));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
        }
        Throwable thM384exceptionOrNullimpl = Result.m384exceptionOrNullimpl(objM381constructorimpl);
        if (thM384exceptionOrNullimpl != null) {
            SyncRelayProcessor.Listener listener = syncRelayRuntime.listener;
            String message = thM384exceptionOrNullimpl.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u062a\u062d\u062f\u064a\u062b \u0633\u062c\u0644 \u0634\u0647\u0627\u062f\u0627\u062a \u0627\u0644\u0623\u062c\u0647\u0632\u0629";
            }
            listener.onRetryRequired("", message);
        }
    }

    private final void scheduleReconnect() {
        ScheduledFuture<?> scheduledFuture;
        synchronized (this) {
            if (this.desiredConnection && ((scheduledFuture = this.reconnectTask) == null || scheduledFuture.isDone())) {
                long j = this.reconnectDelaySeconds;
                this.reconnectDelaySeconds = RangesKt.coerceAtMost(2 * j, 60L);
                this.reconnectTask = this.scheduler.schedule(new Runnable() { // from class: com.hazem.dailystore.data.SyncRelayRuntime$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        SyncRelayRuntime.scheduleReconnect$lambda$14$lambda$13(this.f$0);
                    }
                }, j, TimeUnit.SECONDS);
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void scheduleReconnect$lambda$14$lambda$13(SyncRelayRuntime syncRelayRuntime) {
        boolean z;
        synchronized (syncRelayRuntime) {
            syncRelayRuntime.reconnectTask = null;
            z = syncRelayRuntime.desiredConnection;
        }
        if (z) {
            syncRelayRuntime.prepareAndConnect();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void scheduleDeliveryRetry() {
        ScheduledFuture<?> scheduledFuture;
        synchronized (this) {
            if (this.desiredConnection && ((scheduledFuture = this.deliveryRetryTask) == null || scheduledFuture.isDone())) {
                long j = this.deliveryRetryDelaySeconds;
                this.deliveryRetryDelaySeconds = RangesKt.coerceAtMost(2 * j, 60L);
                this.deliveryRetryTask = this.scheduler.schedule(new Runnable() { // from class: com.hazem.dailystore.data.SyncRelayRuntime$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        SyncRelayRuntime.scheduleDeliveryRetry$lambda$19$lambda$18(this.f$0);
                    }
                }, j, TimeUnit.SECONDS);
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:12:0x001a  */
    public static final void scheduleDeliveryRetry$lambda$19$lambda$18(SyncRelayRuntime syncRelayRuntime) {
        boolean z;
        Object objM381constructorimpl;
        synchronized (syncRelayRuntime) {
            syncRelayRuntime.deliveryRetryTask = null;
            if (syncRelayRuntime.desiredConnection) {
                SyncRelayWebSocketClient syncRelayWebSocketClient = syncRelayRuntime.client;
                if (syncRelayWebSocketClient == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("client");
                    syncRelayWebSocketClient = null;
                }
                if (syncRelayWebSocketClient.getReady()) {
                    z = true;
                } else {
                    z = false;
                }
            } else {
                z = false;
            }
        }
        if (z) {
            try {
                Result.Companion companion = Result.INSTANCE;
                syncRelayRuntime.processor.onRelayReady();
                objM381constructorimpl = Result.m381constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
            }
            Throwable thM384exceptionOrNullimpl = Result.m384exceptionOrNullimpl(objM381constructorimpl);
            if (thM384exceptionOrNullimpl != null) {
                SyncRelayProcessor.Listener listener = syncRelayRuntime.listener;
                String message = thM384exceptionOrNullimpl.getMessage();
                if (message == null) {
                    message = "\u062a\u0639\u0630\u0631\u062a \u0625\u0639\u0627\u062f\u0629 \u0625\u0631\u0633\u0627\u0644 \u0627\u0644\u0639\u0645\u0644\u064a\u0627\u062a \u0627\u0644\u0645\u0639\u0644\u0642\u0629";
                }
                listener.onProtocolError(null, message);
            }
        }
    }

    @Override // com.hazem.dailystore.data.SyncRelayWebSocketClient.Listener
    public void onReady() {
        Object objM381constructorimpl;
        synchronized (this) {
            this.reconnectDelaySeconds = 1L;
            Unit unit = Unit.INSTANCE;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            SyncRelayRuntime syncRelayRuntime = this;
            this.processor.onRelayReady();
            objM381constructorimpl = Result.m381constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
        }
        Throwable thM384exceptionOrNullimpl = Result.m384exceptionOrNullimpl(objM381constructorimpl);
        if (thM384exceptionOrNullimpl != null) {
            SyncRelayProcessor.Listener listener = this.listener;
            String message = thM384exceptionOrNullimpl.getMessage();
            if (message == null) {
                message = "\u0641\u0634\u0644 \u0628\u062f\u0621 \u0645\u0632\u0627\u0645\u0646\u0629 \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0625\u0631\u0633\u0627\u0644";
            }
            listener.onProtocolError(null, message);
        }
    }

    @Override // com.hazem.dailystore.data.SyncRelayWebSocketClient.Listener
    public void onEncryptedFrame(String fromDeviceId, String frameId, String encryptedFrameJson) throws Throwable {
        Intrinsics.checkNotNullParameter(fromDeviceId, "fromDeviceId");
        Intrinsics.checkNotNullParameter(frameId, "frameId");
        Intrinsics.checkNotNullParameter(encryptedFrameJson, "encryptedFrameJson");
        this.processor.onEncryptedFrame(fromDeviceId, frameId, encryptedFrameJson);
    }

    @Override // com.hazem.dailystore.data.SyncRelayWebSocketClient.Listener
    public void onDeliveryStatus(String frameId, boolean deliveredToSocket, boolean retryable) {
        Intrinsics.checkNotNullParameter(frameId, "frameId");
        this.processor.onRelayDelivery(frameId, deliveredToSocket, retryable);
        scheduleDeliveryRetry();
    }

    @Override // com.hazem.dailystore.data.SyncRelayWebSocketClient.Listener
    public void onRelayError(String code) {
        Intrinsics.checkNotNullParameter(code, "code");
        this.listener.onProtocolError(null, "\u062e\u0637\u0623 \u0648\u0633\u064a\u0637 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629: " + code);
    }

    @Override // com.hazem.dailystore.data.SyncRelayWebSocketClient.Listener
    public void onDisconnected(Throwable cause) {
        if (cause != null) {
            SyncRelayProcessor.Listener listener = this.listener;
            String message = cause.getMessage();
            if (message == null) {
                message = "\u0627\u0646\u0642\u0637\u0639 \u0627\u062a\u0635\u0627\u0644 \u0648\u0633\u064a\u0637 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629";
            }
            listener.onRetryRequired("", message);
        }
        scheduleReconnect();
    }

    public final synchronized void shutdown() {
        disconnect();
        this.scheduler.shutdownNow();
    }
}
