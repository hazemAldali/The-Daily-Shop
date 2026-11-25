package com.hazem.dailystore;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.IBinder;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.core.app.NotificationCompat;
import com.hazem.dailystore.data.SyncCredentialVault;
import com.hazem.dailystore.data.SyncProtocolRepository;
import com.hazem.dailystore.data.SyncRelaySession;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SyncForegroundService.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\"\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007H\u0016J\b\u0010\f\u001a\u00020\u0005H\u0002J\b\u0010\r\u001a\u00020\u0005H\u0002J\b\u0010\u000e\u001a\u00020\u0005H\u0016J\u0014\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016\u00a8\u0006\u0012"}, d2 = {"Lcom/hazem/dailystore/SyncForegroundService;", "Landroid/app/Service;", "<init>", "()V", "onCreate", "", "onStartCommand", "", "intent", "Landroid/content/Intent;", "flags", "startId", "startVisibleForeground", "createNotificationChannel", "onDestroy", "onBind", "Landroid/os/IBinder;", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SyncForegroundService extends Service {
    public static final String ACTION_START = "com.hazem.dailystore.sync.START";
    private static final String CHANNEL_ID = "daily_shop_sync";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String ENDPOINT_KEY = "relay_wss_url";
    private static final int NOTIFICATION_ID = 7401;
    private static final String PREFS = "daily_shop_sync_relay";

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        int i = 2;
        if (!INSTANCE.shouldRun$app_release(this)) {
            stopSelf(startId);
            return 2;
        }
        SyncCredentialVault syncCredentialVault = null;
        Object[] objArr = 0;
        String string = getSharedPreferences(PREFS, 0).getString(ENDPOINT_KEY, null);
        if (string == null) {
            stopSelf(startId);
            return 2;
        }
        startVisibleForeground();
        try {
            Context applicationContext = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            SyncProtocolRepository syncProtocolRepository = new SyncProtocolRepository(applicationContext, syncCredentialVault, i, objArr == true ? 1 : 0);
            if (syncProtocolRepository.localDeviceRole() == null) {
                stopSelf(startId);
                return 2;
            }
            SyncRelaySession syncRelaySession = SyncRelaySession.INSTANCE;
            Context applicationContext2 = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext2, "getApplicationContext(...)");
            syncRelaySession.connect(applicationContext2, string, syncProtocolRepository);
            return 1;
        } catch (Exception unused) {
            stopSelf(startId);
            return 2;
        }
    }

    private final void startVisibleForeground() {
        Notification.Builder builder;
        SyncForegroundService syncForegroundService = this;
        PendingIntent activity = PendingIntent.getActivity(syncForegroundService, 0, new Intent(syncForegroundService, (Class<?>) MainActivity.class), 67108864 | 134217728);
        if (Build.VERSION.SDK_INT >= 26) {
            SyncForegroundService$$ExternalSyntheticApiModelOutline0.m359m();
            builder = SyncForegroundService$$ExternalSyntheticApiModelOutline0.m(syncForegroundService, CHANNEL_ID);
        } else {
            builder = new Notification.Builder(syncForegroundService);
        }
        Notification notificationBuild = builder.setSmallIcon(android.R.drawable.stat_notify_sync_noanim).setContentTitle("\u0645\u0632\u0627\u0645\u0646\u0629 The Daily Shop").setContentText("\u062e\u062f\u0645\u0629 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u062a\u0639\u0645\u0644\u061b \u062a\u0628\u0642\u0649 \u0627\u0644\u0639\u0645\u0644\u064a\u0627\u062a \u0645\u0639\u0644\u0642\u0629 \u062d\u062a\u0649 \u0625\u0642\u0631\u0627\u0631 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a").setContentIntent(activity).setOngoing(true).setCategory(NotificationCompat.CATEGORY_SERVICE).build();
        Intrinsics.checkNotNullExpressionValue(notificationBuild, "build(...)");
        if (Build.VERSION.SDK_INT >= 34) {
            startForeground(NOTIFICATION_ID, notificationBuild, BasicMeasure.EXACTLY);
        } else {
            startForeground(NOTIFICATION_ID, notificationBuild);
        }
    }

    private final void createNotificationChannel() {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        SyncForegroundService$$ExternalSyntheticApiModelOutline0.m$1();
        NotificationChannel notificationChannelM = SyncForegroundService$$ExternalSyntheticApiModelOutline0.m(CHANNEL_ID, "\u0645\u0632\u0627\u0645\u0646\u0629 \u0627\u0644\u0645\u062a\u062c\u0631", 2);
        notificationChannelM.setDescription("\u0625\u0628\u0642\u0627\u0621 \u0627\u062a\u0635\u0627\u0644 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u0627\u0644\u0645\u0634\u0641\u0631 \u0628\u064a\u0646 \u0623\u062c\u0647\u0632\u0629 \u0627\u0644\u0645\u062a\u062c\u0631");
        notificationChannelM.setShowBadge(false);
        ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannelM);
    }

    @Override // android.app.Service
    public void onDestroy() {
        SyncRelaySession.INSTANCE.shutdown();
        super.onDestroy();
    }

    /* JADX INFO: compiled from: SyncForegroundService.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0000\u00a2\u0006\u0002\b\u000fJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/hazem/dailystore/SyncForegroundService$Companion;", "", "<init>", "()V", "ACTION_START", "", "PREFS", "ENDPOINT_KEY", "CHANNEL_ID", "NOTIFICATION_ID", "", "shouldRun", "", "context", "Landroid/content/Context;", "shouldRun$app_release", "start", "", "stop", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean shouldRun$app_release(Context context) {
            String string;
            Intrinsics.checkNotNullParameter(context, "context");
            SharedPreferences sharedPreferences = context.getSharedPreferences(SyncForegroundService.PREFS, 0);
            return (!sharedPreferences.getBoolean("sync_enabled", false) || (string = sharedPreferences.getString(SyncForegroundService.ENDPOINT_KEY, null)) == null || StringsKt.isBlank(string)) ? false : true;
        }

        public final void start(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.getSharedPreferences(SyncForegroundService.PREFS, 0).edit().putBoolean("sync_enabled", true).apply();
            Intent action = new Intent(context, (Class<?>) SyncForegroundService.class).setAction(SyncForegroundService.ACTION_START);
            Intrinsics.checkNotNullExpressionValue(action, "setAction(...)");
            if (Build.VERSION.SDK_INT >= 26) {
                context.startForegroundService(action);
            } else {
                context.startService(action);
            }
        }

        public final void stop(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.getSharedPreferences(SyncForegroundService.PREFS, 0).edit().putBoolean("sync_enabled", false).apply();
            context.stopService(new Intent(context, (Class<?>) SyncForegroundService.class));
        }
    }
}
