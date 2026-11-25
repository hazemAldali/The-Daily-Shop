package com.hazem.dailystore.data;

import java.util.ArrayDeque;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SyncRelayProcessor.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B+\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/hazem/dailystore/data/SyncRelaySendRateLimiter;", "", "maxMessages", "", "windowMillis", "", "clockMillis", "Lkotlin/Function0;", "<init>", "(IJLkotlin/jvm/functions/Function0;)V", "sentAt", "Ljava/util/ArrayDeque;", "tryAcquire", "", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SyncRelaySendRateLimiter {
    private final Function0<Long> clockMillis;
    private final int maxMessages;
    private final ArrayDeque<Long> sentAt;
    private final long windowMillis;

    public SyncRelaySendRateLimiter() {
        this(0, 0L, null, 7, null);
    }

    public SyncRelaySendRateLimiter(int i, long j, Function0<Long> clockMillis) {
        Intrinsics.checkNotNullParameter(clockMillis, "clockMillis");
        this.maxMessages = i;
        this.windowMillis = j;
        this.clockMillis = clockMillis;
        this.sentAt = new ArrayDeque<>();
        if (1 > i || i >= 60 || j <= 0) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public /* synthetic */ SyncRelaySendRateLimiter(int i, long j, Function0 function0, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 50 : i, (i2 & 2) != 0 ? 10000L : j, (i2 & 4) != 0 ? new Function0() { // from class: com.hazem.dailystore.data.SyncRelaySendRateLimiter$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Long.valueOf(SyncRelaySendRateLimiter._init_$lambda$0());
            }
        } : function0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long _init_$lambda$0() {
        return System.nanoTime() / 1000000;
    }

    public final synchronized boolean tryAcquire() {
        long jLongValue = this.clockMillis.invoke().longValue();
        while (true) {
            Long lPeekFirst = this.sentAt.peekFirst();
            if (lPeekFirst == null || jLongValue - lPeekFirst.longValue() < this.windowMillis) {
                break;
            }
            this.sentAt.removeFirst();
        }
        if (this.sentAt.size() >= this.maxMessages) {
            return false;
        }
        this.sentAt.addLast(Long.valueOf(jLongValue));
        return true;
    }
}
