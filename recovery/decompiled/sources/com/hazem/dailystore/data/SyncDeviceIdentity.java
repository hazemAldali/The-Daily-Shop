package com.hazem.dailystore.data;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SyncDeviceIdentity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0005"}, d2 = {"Lcom/hazem/dailystore/data/SyncDeviceIdentity;", "", "<init>", "()V", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SyncDeviceIdentity {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String DEVICE_ID_KEY = "device_id";
    private static final String PREFERENCES_NAME = "the_daily_shop_sync_identity";

    private SyncDeviceIdentity() {
    }

    /* JADX INFO: compiled from: SyncDeviceIdentity.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/hazem/dailystore/data/SyncDeviceIdentity$Companion;", "", "<init>", "()V", "PREFERENCES_NAME", "", "DEVICE_ID_KEY", "getOrCreate", "context", "Landroid/content/Context;", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getOrCreate(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(SyncDeviceIdentity.PREFERENCES_NAME, 0);
            synchronized (SyncDeviceIdentity.class) {
                String string = sharedPreferences.getString(SyncDeviceIdentity.DEVICE_ID_KEY, null);
                String str = string;
                if (str != null && !StringsKt.isBlank(str)) {
                    return string;
                }
                String string2 = UUID.randomUUID().toString();
                Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                if (sharedPreferences.edit().putString(SyncDeviceIdentity.DEVICE_ID_KEY, string2).commit()) {
                    return string2;
                }
                throw new IllegalStateException("Unable to persist synchronization device identity".toString());
            }
        }
    }
}
