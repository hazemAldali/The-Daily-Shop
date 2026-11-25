package androidx.activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.Person;
import android.app.job.JobWorkItem;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.view.inspector.InspectionCompanion;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ComponentDialog$$ExternalSyntheticApiModelOutline0 {
    public static /* bridge */ /* synthetic */ Notification.MessagingStyle m(Object obj) {
        return (Notification.MessagingStyle) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ NotificationChannel m5m(Object obj) {
        return (NotificationChannel) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ NotificationChannelGroup m6m(Object obj) {
        return (NotificationChannelGroup) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Person m7m(Object obj) {
        return (Person) obj;
    }

    public static /* synthetic */ JobWorkItem m(Intent intent) {
        return new JobWorkItem(intent);
    }

    public static /* synthetic */ ShortcutInfo.Builder m(Context context, String str) {
        return new ShortcutInfo.Builder(context, str);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ ShortcutInfo m10m(Object obj) {
        return (ShortcutInfo) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ ShortcutManager m11m(Object obj) {
        return (ShortcutManager) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ InspectionCompanion.UninitializedPropertyMapException m15m() {
        return new InspectionCompanion.UninitializedPropertyMapException();
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ OnBackInvokedCallback m16m(Object obj) {
        return (OnBackInvokedCallback) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ OnBackInvokedDispatcher m17m(Object obj) {
        return (OnBackInvokedDispatcher) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Class m19m() {
        return Notification.DecoratedCustomViewStyle.class;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ void m22m() {
    }

    public static /* bridge */ /* synthetic */ Class m$1() {
        return Notification.MessagingStyle.class;
    }

    public static /* bridge */ /* synthetic */ Class m$2() {
        return ShortcutManager.class;
    }
}
