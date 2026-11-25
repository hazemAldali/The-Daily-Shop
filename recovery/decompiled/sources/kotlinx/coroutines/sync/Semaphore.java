package kotlinx.coroutines.sync;

import com.hazem.dailystore.BuildConfig;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: Semaphore.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0011\u0010\u0006\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\u0007H&J\b\u0010\n\u001a\u00020\u000bH&R\u0012\u0010\u0002\u001a\u00020\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\f"}, d2 = {"Lkotlinx/coroutines/sync/Semaphore;", "", "availablePermits", "", "getAvailablePermits", "()I", "acquire", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", BuildConfig.BUILD_TYPE, "tryAcquire", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface Semaphore {
    Object acquire(Continuation<? super Unit> continuation);

    int getAvailablePermits();

    void release();

    boolean tryAcquire();
}
