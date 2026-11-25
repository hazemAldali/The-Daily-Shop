package com.hazem.dailystore;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SyncBootReceiver.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016\u00a8\u0006\n"}, d2 = {"Lcom/hazem/dailystore/SyncBootReceiver;", "Landroid/content/BroadcastReceiver;", "<init>", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SyncBootReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action;
        Intrinsics.checkNotNullParameter(context, "context");
        if (intent == null || (action = intent.getAction()) == null) {
            return;
        }
        if ((Intrinsics.areEqual(action, "android.intent.action.BOOT_COMPLETED") || Intrinsics.areEqual(action, "android.intent.action.MY_PACKAGE_REPLACED")) && SyncForegroundService.INSTANCE.shouldRun$app_release(context)) {
            try {
                Result.Companion companion = Result.INSTANCE;
                SyncBootReceiver syncBootReceiver = this;
                SyncForegroundService.Companion companion2 = SyncForegroundService.INSTANCE;
                Context applicationContext = context.getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                companion2.start(applicationContext);
                Result.m381constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                Result.m381constructorimpl(ResultKt.createFailure(th));
            }
        }
    }
}
