package com.hazem.dailystore;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: SplashActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014J\b\u0010\f\u001a\u00020\tH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/hazem/dailystore/SplashActivity;", "Landroid/app/Activity;", "<init>", "()V", "handler", "Landroid/os/Handler;", "openLogin", "Ljava/lang/Runnable;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SplashActivity extends Activity {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final long MIN_SPLASH_DURATION_MS = 450;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable openLogin = new Runnable() { // from class: com.hazem.dailystore.SplashActivity$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            SplashActivity.openLogin$lambda$0(this.f$0);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openLogin$lambda$0(SplashActivity splashActivity) {
        splashActivity.startActivity(new Intent(splashActivity, (Class<?>) MainActivity.class));
        splashActivity.finish();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.rgb(24, 75, 55));
        getWindow().setNavigationBarColor(Color.rgb(24, 75, 55));
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.the_daily_shop_splash);
        imageView.setBackgroundColor(Color.rgb(24, 75, 55));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setContentDescription("The Daily Shop");
        setContentView(imageView);
        this.handler.postDelayed(this.openLogin, 450L);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        this.handler.removeCallbacks(this.openLogin);
        super.onDestroy();
    }

    /* JADX INFO: compiled from: SplashActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/hazem/dailystore/SplashActivity$Companion;", "", "<init>", "()V", "MIN_SPLASH_DURATION_MS", "", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
