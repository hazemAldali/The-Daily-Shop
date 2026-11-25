package androidx.sqlite.db;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SupportSQLiteCompat.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0007"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteCompat;", "", "<init>", "()V", "Api16Impl", "Api19Impl", "Api29Impl", "sqlite"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SupportSQLiteCompat {
    private SupportSQLiteCompat() {
    }

    /* JADX INFO: compiled from: SupportSQLiteCompat.android.kt */
    @Deprecated(message = "Kept for ABI compatibility reasons due to b/402796648 even though minSdk is greater than 16.")
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\b\u0010\b\u001a\u00020\u0007H\u0007\u00a8\u0006\t"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteCompat$Api16Impl;", "", "<init>", "()V", "cancel", "", "cancellationSignal", "Landroid/os/CancellationSignal;", "createCancellationSignal", "sqlite"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Api16Impl {
        public static final Api16Impl INSTANCE = new Api16Impl();

        private Api16Impl() {
        }

        @JvmStatic
        public static final void cancel(CancellationSignal cancellationSignal) {
            Intrinsics.checkNotNullParameter(cancellationSignal, "cancellationSignal");
            cancellationSignal.cancel();
        }

        @JvmStatic
        public static final CancellationSignal createCancellationSignal() {
            return new CancellationSignal();
        }
    }

    /* JADX INFO: compiled from: SupportSQLiteCompat.android.kt */
    @Deprecated(message = "Kept for ABI compatibility reasons due to b/402796648 even though minSdk is greater than 19.")
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007\u00a8\u0006\f"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteCompat$Api19Impl;", "", "<init>", "()V", "getNotificationUri", "Landroid/net/Uri;", "cursor", "Landroid/database/Cursor;", "isLowRamDevice", "", "activityManager", "Landroid/app/ActivityManager;", "sqlite"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Api19Impl {
        public static final Api19Impl INSTANCE = new Api19Impl();

        private Api19Impl() {
        }

        @JvmStatic
        public static final Uri getNotificationUri(Cursor cursor) {
            Intrinsics.checkNotNullParameter(cursor, "cursor");
            Uri notificationUri = cursor.getNotificationUri();
            Intrinsics.checkNotNullExpressionValue(notificationUri, "getNotificationUri(...)");
            return notificationUri;
        }

        @JvmStatic
        public static final boolean isLowRamDevice(ActivityManager activityManager) {
            Intrinsics.checkNotNullParameter(activityManager, "activityManager");
            return activityManager.isLowRamDevice();
        }
    }

    /* JADX INFO: compiled from: SupportSQLiteCompat.android.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J(\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u000e\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bH\u0007J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007\u00a8\u0006\u000e"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteCompat$Api29Impl;", "", "<init>", "()V", "setNotificationUris", "", "cursor", "Landroid/database/Cursor;", "cr", "Landroid/content/ContentResolver;", "uris", "", "Landroid/net/Uri;", "getNotificationUris", "sqlite"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Api29Impl {
        public static final Api29Impl INSTANCE = new Api29Impl();

        private Api29Impl() {
        }

        @JvmStatic
        public static final void setNotificationUris(Cursor cursor, ContentResolver cr, List<? extends Uri> uris) {
            Intrinsics.checkNotNullParameter(cursor, "cursor");
            Intrinsics.checkNotNullParameter(cr, "cr");
            Intrinsics.checkNotNullParameter(uris, "uris");
            cursor.setNotificationUris(cr, uris);
        }

        @JvmStatic
        public static final List<Uri> getNotificationUris(Cursor cursor) {
            Intrinsics.checkNotNullParameter(cursor, "cursor");
            List<Uri> notificationUris = cursor.getNotificationUris();
            Intrinsics.checkNotNull(notificationUris);
            return notificationUris;
        }
    }
}
