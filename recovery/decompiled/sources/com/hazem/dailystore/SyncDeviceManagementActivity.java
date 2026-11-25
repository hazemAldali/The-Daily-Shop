package com.hazem.dailystore;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import com.hazem.dailystore.data.AuthRepository;
import com.hazem.dailystore.data.PairingAccountSnapshot;
import com.hazem.dailystore.data.SecureSessionStore;
import com.hazem.dailystore.data.StoreTime;
import com.hazem.dailystore.data.SyncDeviceAuthenticator;
import com.hazem.dailystore.data.SyncEnrollment;
import com.hazem.dailystore.data.SyncPairingInvitation;
import com.hazem.dailystore.data.SyncPairingRelayClient;
import com.hazem.dailystore.data.SyncProtocolRepository;
import com.hazem.dailystore.data.SyncRelayRegistryClient;
import com.hazem.dailystore.data.SyncRelayRegistrySyncResult;
import com.hazem.dailystore.data.SyncRelaySession;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import org.bouncycastle.i18n.MessageBundle;
import org.bouncycastle.jcajce.util.AnnotatedPrivateKey;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: SyncDeviceManagementActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 _2\u00020\u0001:\u0001_B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\b\u0010\u001e\u001a\u00020\u001bH\u0002J&\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00072\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001b0$H\u0002J\b\u0010%\u001a\u00020&H\u0002J(\u0010'\u001a\u00020\u001b2\u0006\u0010(\u001a\u00020\u00072\b\b\u0002\u0010)\u001a\u00020\u00132\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001b0$H\u0002J\u0018\u0010*\u001a\u00020\u001b2\u0006\u0010(\u001a\u00020\u00072\u0006\u0010+\u001a\u00020\u0007H\u0002J\u0018\u0010,\u001a\u00020\u001b2\u0006\u0010(\u001a\u00020\u00072\u0006\u0010+\u001a\u00020\u0007H\u0002J\u0010\u0010-\u001a\u00020\u001b2\u0006\u0010.\u001a\u00020\u0007H\u0002J\u0018\u0010/\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u00072\u0006\u0010+\u001a\u00020\u0007H\u0002J6\u00100\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u00072\u0006\u00101\u001a\u00020\u00072\b\b\u0002\u00102\u001a\u00020\u00132\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001b03H\u0002J\u0015\u00104\u001a\b\u0012\u0004\u0012\u00020\u001b05H\u0002\u00a2\u0006\u0004\b6\u00107J\b\u00108\u001a\u00020\u001bH\u0002J\u0010\u00109\u001a\u00020\u001b2\u0006\u0010:\u001a\u00020\u0007H\u0002J\b\u0010;\u001a\u00020\u0007H\u0002J\b\u0010<\u001a\u00020=H\u0002J\b\u0010>\u001a\u00020\u001bH\u0002J\b\u0010?\u001a\u00020\u001bH\u0002J\b\u0010@\u001a\u00020\u001bH\u0017J\u0014\u0010A\u001a\u00020\u001b2\n\b\u0002\u0010B\u001a\u0004\u0018\u00010\u0007H\u0002J \u0010C\u001a\u00020\u001b2\n\b\u0002\u0010D\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010E\u001a\u0004\u0018\u00010=H\u0002J\u0010\u0010F\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020!H\u0002J\u0018\u0010G\u001a\u00020\u001b2\u0006\u0010H\u001a\u00020\u00072\u0006\u0010I\u001a\u00020\u0007H\u0002J\b\u0010J\u001a\u00020KH\u0002J \u0010L\u001a\u00020M2\u0006\u0010N\u001a\u00020\u00072\u0006\u0010O\u001a\u00020\u00072\u0006\u0010P\u001a\u00020\u0007H\u0002J\u0018\u0010Q\u001a\u00020\u00072\u0006\u0010R\u001a\u00020\u00072\u0006\u0010S\u001a\u00020TH\u0002J0\u0010U\u001a\u00020\u00072\u0006\u0010V\u001a\u00020\u00072\u0006\u0010W\u001a\u00020X2\u0006\u0010Y\u001a\u00020\u00072\u0006\u0010Z\u001a\u00020\u00072\u0006\u0010P\u001a\u00020\u0007H\u0002J\b\u0010[\u001a\u00020\u001bH\u0002J\b\u0010\\\u001a\u00020\u001bH\u0002J\b\u0010]\u001a\u00020\u001bH\u0002J\b\u0010^\u001a\u00020\u001bH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u0018\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R#\u0010\u0014\u001a\n \f*\u0004\u0018\u00010\u00150\u00158BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006`"}, d2 = {"Lcom/hazem/dailystore/SyncDeviceManagementActivity;", "Landroid/app/Activity;", "<init>", "()V", "repository", "Lcom/hazem/dailystore/data/SyncProtocolRepository;", "actorUserId", "", NotificationCompat.CATEGORY_STATUS, "Landroid/widget/TextView;", "worker", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "Ljava/util/concurrent/ExecutorService;", "main", "Landroid/os/Handler;", "pairingMonitorRunning", "Ljava/util/concurrent/atomic/AtomicBoolean;", "bootstrapMode", "", "preferences", "Landroid/content/SharedPreferences;", "getPreferences", "()Landroid/content/SharedPreferences;", "preferences$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "render", "addButton", "parent", "Landroid/widget/LinearLayout;", MessageBundle.TITLE_ENTRY, "action", "Lkotlin/Function0;", "match", "Landroid/widget/LinearLayout$LayoutParams;", "runTask", AnnotatedPrivateKey.LABEL, "finishOnSuccess", "copyText", "value", "shareText", "handleIncomingPairingShare", "raw", "showOutput", "prompt", "hint", "multiline", "Lkotlin/Function1;", "copyTrustRoot", "Lkotlin/Result;", "copyTrustRoot-d1pmJ48", "()Ljava/lang/Object;", "beginPrimaryAddDevice", "createInvitation", "deviceName", "pairingRelayHttps", "pairingRelayClient", "Lcom/hazem/dailystore/data/SyncPairingRelayClient;", "resumePrimaryPairingMonitor", "showAdvancedPairingOptions", "onBackPressed", "createSecondaryRequest", "invitationText", "enrollSecondaryFromRequest", "sharedRequest", "pairingClient", "addEnrollmentResponseForm", "completeEnrollmentResponse", "responseText", "https", "pairingOaepSpec", "Ljavax/crypto/spec/OAEPParameterSpec;", "pairingAad", "", "storeId", "deviceId", "invitationToken", "encryptPairingResponse", "responseJson", "request", "Lorg/json/JSONObject;", "decryptPairingResponse", "envelopeJson", "privateKey", "Ljava/security/PrivateKey;", "expectedStoreId", "expectedDeviceId", "revokeSecondary", "syncDeviceRegistryNow", "configureRelay", "onDestroy", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SyncDeviceManagementActivity extends Activity {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String DEFAULT_RELAY_HTTPS = "https://the-daily-shop-relay.hazem-syrian.workers.dev";
    public static final String EXTRA_ACTOR_USER_ID = "actor_user_id";
    public static final String EXTRA_INCOMING_SHARE = "incoming_pairing_share";
    private static final String KEY_LAST_PROBE_ID = "last_transport_probe_id";
    private static final String KEY_PENDING_INVITATION = "pending_pairing_invitation";
    private static final String KEY_PENDING_STORE = "pending_pairing_store";
    private static final String KEY_RELAY_WSS = "relay_wss_url";
    private String actorUserId;
    private boolean bootstrapMode;
    private SyncProtocolRepository repository;
    private TextView status;
    private final ExecutorService worker = Executors.newSingleThreadExecutor();
    private final Handler main = new Handler(Looper.getMainLooper());
    private final AtomicBoolean pairingMonitorRunning = new AtomicBoolean(false);

    /* JADX INFO: renamed from: preferences$delegate, reason: from kotlin metadata */
    private final Lazy preferences = LazyKt.lazy(new Function0() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return SyncDeviceManagementActivity.preferences_delegate$lambda$0(this.f$0);
        }
    });

    private final SharedPreferences getPreferences() {
        return (SharedPreferences) this.preferences.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SharedPreferences preferences_delegate$lambda$0(SyncDeviceManagementActivity syncDeviceManagementActivity) {
        return syncDeviceManagementActivity.getSharedPreferences("daily_shop_sync_relay", 0);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        final String string;
        Object objM381constructorimpl;
        Object objM381constructorimpl2;
        Object objM381constructorimpl3;
        super.onCreate(savedInstanceState);
        String stringExtra = getIntent().getStringExtra(EXTRA_ACTOR_USER_ID);
        if (stringExtra == null) {
            stringExtra = "";
        }
        this.actorUserId = stringExtra;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        TextView textView = null;
        this.repository = new SyncProtocolRepository(applicationContext, null, 2, null);
        String stringExtra2 = getIntent().getStringExtra(EXTRA_INCOMING_SHARE);
        if (stringExtra2 == null || (string = StringsKt.trim((CharSequence) stringExtra2).toString()) == null || string.length() <= 0 || string.length() > 32768) {
            string = null;
        }
        String str = this.actorUserId;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("actorUserId");
            str = null;
        }
        boolean z = false;
        if (StringsKt.isBlank(str) && string != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                SyncDeviceManagementActivity syncDeviceManagementActivity = this;
                objM381constructorimpl3 = Result.m381constructorimpl(Boolean.valueOf(new JSONObject(string).has("invitation_token")));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM381constructorimpl3 = Result.m381constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m387isFailureimpl(objM381constructorimpl3)) {
                objM381constructorimpl3 = false;
            }
            if (((Boolean) objM381constructorimpl3).booleanValue()) {
                z = true;
            }
        }
        this.bootstrapMode = z;
        String str2 = this.actorUserId;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("actorUserId");
            str2 = null;
        }
        if (StringsKt.isBlank(str2) && !this.bootstrapMode) {
            finish();
            return;
        }
        try {
            Result.Companion companion3 = Result.INSTANCE;
            SyncDeviceManagementActivity syncDeviceManagementActivity2 = this;
            SyncProtocolRepository syncProtocolRepository = this.repository;
            if (syncProtocolRepository == null) {
                Intrinsics.throwUninitializedPropertyAccessException("repository");
                syncProtocolRepository = null;
            }
            objM381constructorimpl = Result.m381constructorimpl(syncProtocolRepository.localDeviceRole());
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th2));
        }
        if (!Result.m388isSuccessimpl(objM381constructorimpl)) {
            Toast.makeText(this, "\u062a\u0639\u0630\u0631 \u0642\u0631\u0627\u0621\u0629 \u0625\u0639\u062f\u0627\u062f\u0627\u062a \u0645\u0632\u0627\u0645\u0646\u0629 \u0627\u0644\u0623\u062c\u0647\u0632\u0629", 1).show();
            finish();
            return;
        }
        if (this.bootstrapMode) {
            TextView textView2 = new TextView(this);
            textView2.setText("\u0648\u0635\u0644\u062a \u062f\u0639\u0648\u0629 \u0627\u0644\u0645\u062a\u062c\u0631. \u062c\u0627\u0631\u064d \u062a\u062c\u0647\u064a\u0632 \u0631\u0628\u0637 \u0647\u0630\u0627 \u0627\u0644\u0647\u0627\u062a\u0641 \u062f\u0648\u0646 \u0625\u0646\u0634\u0627\u0621 \u062d\u0633\u0627\u0628 \u0623\u0648 \u0645\u062a\u062c\u0631 \u0645\u0633\u062a\u0642\u0644...");
            textView2.setTextSize(15.0f);
            textView2.setGravity(17);
            textView2.setPadding(28, 36, 28, 36);
            textView2.setTextColor(-15185097);
            this.status = textView2;
            setContentView(textView2);
        } else {
            render();
        }
        if (string != null) {
            getIntent().removeExtra(EXTRA_INCOMING_SHARE);
            this.main.post(new Runnable() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.handleIncomingPairingShare(string);
                }
            });
        }
        String string2 = getPreferences().getString(KEY_RELAY_WSS, null);
        if (string2 == null || StringsKt.isBlank(string2)) {
            return;
        }
        SyncProtocolRepository syncProtocolRepository2 = this.repository;
        if (syncProtocolRepository2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("repository");
            syncProtocolRepository2 = null;
        }
        if (syncProtocolRepository2.localDeviceRole() != null) {
            try {
                Result.Companion companion5 = Result.INSTANCE;
                SyncDeviceManagementActivity syncDeviceManagementActivity3 = this;
                SyncForegroundService.INSTANCE.start(this);
                objM381constructorimpl2 = Result.m381constructorimpl(Unit.INSTANCE);
            } catch (Throwable th3) {
                Result.Companion companion6 = Result.INSTANCE;
                objM381constructorimpl2 = Result.m381constructorimpl(ResultKt.createFailure(th3));
            }
            Throwable thM384exceptionOrNullimpl = Result.m384exceptionOrNullimpl(objM381constructorimpl2);
            if (thM384exceptionOrNullimpl != null) {
                TextView textView3 = this.status;
                if (textView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_STATUS);
                } else {
                    textView = textView3;
                }
                String message = thM384exceptionOrNullimpl.getMessage();
                if (message == null) {
                    message = "\u0639\u0646\u0648\u0627\u0646 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d";
                }
                textView.setText("\u062a\u0639\u0630\u0631 \u0628\u062f\u0621 \u0627\u062a\u0635\u0627\u0644 \u0627\u0644\u0648\u0633\u064a\u0637: " + message);
            }
        }
    }

    private final void render() {
        SyncProtocolRepository syncProtocolRepository = this.repository;
        if (syncProtocolRepository == null) {
            Intrinsics.throwUninitializedPropertyAccessException("repository");
            syncProtocolRepository = null;
        }
        String strLocalDeviceRole = syncProtocolRepository.localDeviceRole();
        if (strLocalDeviceRole == null) {
            strLocalDeviceRole = "\u063a\u064a\u0631 \u0645\u0647\u064a\u0623";
        }
        SyncDeviceManagementActivity syncDeviceManagementActivity = this;
        LinearLayout linearLayout = new LinearLayout(syncDeviceManagementActivity);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(28, 28, 28, 28);
        linearLayout.setBackgroundColor(-591627);
        TextView textView = new TextView(syncDeviceManagementActivity);
        textView.setText("\u0645\u0632\u0627\u0645\u0646\u0629 \u0627\u0644\u0623\u062c\u0647\u0632\u0629 \u0648\u062d\u0645\u0627\u064a\u0629 \u0627\u0644\u0627\u0642\u062a\u0631\u0627\u0646");
        textView.setTextSize(22.0f);
        textView.setGravity(5);
        textView.setTextColor(-15185097);
        linearLayout.addView(textView, match());
        TextView textView2 = new TextView(syncDeviceManagementActivity);
        SyncProtocolRepository syncProtocolRepository2 = this.repository;
        if (syncProtocolRepository2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("repository");
            syncProtocolRepository2 = null;
        }
        textView2.setText("\u0627\u0644\u062f\u0648\u0631: " + strLocalDeviceRole + "\n\u0645\u0639\u0631\u0651\u0641 \u0647\u0630\u0627 \u0627\u0644\u062c\u0647\u0627\u0632: " + syncProtocolRepository2.localDeviceId());
        textView2.setTextSize(12.0f);
        textView2.setGravity(5);
        textView2.setPadding(0, 12, 0, 18);
        textView2.setTextColor(-11378601);
        linearLayout.addView(textView2, match());
        TextView textView3 = new TextView(syncDeviceManagementActivity);
        textView3.setText("\u0644\u0627 \u064a\u062a\u0645 \u0627\u0639\u062a\u0645\u0627\u062f \u0623\u064a \u062c\u0647\u0627\u0632 \u062b\u0627\u0646\u0648\u064a \u0642\u0628\u0644 \u0627\u0644\u062a\u062d\u0642\u0642 \u0645\u0646 \u0634\u0647\u0627\u062f\u0629 \u062c\u0630\u0631 \u0627\u0644\u062b\u0642\u0629.");
        textView3.setTextSize(13.0f);
        textView3.setGravity(5);
        textView3.setPadding(0, 8, 0, 16);
        textView3.setTextColor(-11378601);
        this.status = textView3;
        linearLayout.addView(textView3, match());
        SyncProtocolRepository syncProtocolRepository3 = this.repository;
        if (syncProtocolRepository3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("repository");
            syncProtocolRepository3 = null;
        }
        String strLocalDeviceRole2 = syncProtocolRepository3.localDeviceRole();
        if (strLocalDeviceRole2 == null) {
            TextView textView4 = new TextView(syncDeviceManagementActivity);
            textView4.setText("\u062a\u0645 \u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062f\u062e\u0648\u0644. \u0647\u0630\u0627 \u0627\u0644\u0647\u0627\u062a\u0641 \u063a\u064a\u0631 \u0645\u0639\u064a\u0651\u0646 \u0628\u0639\u062f. \u0625\u0630\u0627 \u0643\u0627\u0646 \u0647\u0648 \u0627\u0644\u0647\u0627\u062a\u0641 \u0627\u0644\u0631\u0626\u064a\u0633\u064a \u0641\u0627\u0639\u062a\u0645\u062f\u0647 \u0645\u0631\u0629 \u0648\u0627\u062d\u062f\u0629. \u0623\u0645\u0627 \u0627\u0644\u0647\u0627\u062a\u0641 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u0641\u0627\u062a\u0631\u0643 \u062e\u064a\u0627\u0631 \u0627\u0644\u0631\u0626\u064a\u0633\u064a \u062f\u0648\u0646 \u0644\u0645\u0633\u060c \u0648\u0623\u0646\u0634\u0626 \u0637\u0644\u0628 \u0627\u0644\u0627\u0642\u062a\u0631\u0627\u0646 \u0645\u0646 \u0627\u0644\u062f\u0639\u0648\u0629 \u062b\u0645 \u0627\u0644\u0635\u0642 \u0631\u062f \u0627\u0644\u0647\u0627\u062a\u0641 \u0627\u0644\u0631\u0626\u064a\u0633\u064a \u0641\u064a \u0627\u0644\u062d\u0642\u0644 \u0627\u0644\u0638\u0627\u0647\u0631 \u0623\u0633\u0641\u0644 \u0647\u0630\u0647 \u0627\u0644\u0635\u0641\u062d\u0629.");
            textView4.setTextSize(14.0f);
            textView4.setGravity(5);
            textView4.setPadding(0, 0, 0, 18);
            textView4.setTextColor(-11378601);
            linearLayout.addView(textView4, match());
            addButton(linearLayout, "\u0627\u0639\u062a\u0645\u0627\u062f \u0647\u0630\u0627 \u0627\u0644\u0647\u0627\u062a\u0641 \u062c\u0647\u0627\u0632\u0627\u064b \u0631\u0626\u064a\u0633\u064a\u0627\u064b", new Function0() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SyncDeviceManagementActivity.render$lambda$17(this.f$0);
                }
            });
            addButton(linearLayout, "\u0625\u0639\u062f\u0627\u062f \u0647\u0630\u0627 \u0627\u0644\u0647\u0627\u062a\u0641 \u0643\u062c\u0647\u0627\u0632 \u062b\u0627\u0646\u0648\u064a \u2014 \u0627\u0644\u062e\u0637\u0648\u0629 1", new Function0() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SyncDeviceManagementActivity.render$lambda$18(this.f$0);
                }
            });
            addEnrollmentResponseForm(linearLayout);
        } else if (Intrinsics.areEqual(strLocalDeviceRole2, "PRIMARY")) {
            TextView textView5 = new TextView(syncDeviceManagementActivity);
            textView5.setText("\u0644\u0625\u0636\u0627\u0641\u0629 \u0647\u0627\u062a\u0641 \u062c\u062f\u064a\u062f\u060c \u0627\u0636\u063a\u0637 \u0627\u0644\u0632\u0631 \u0623\u062f\u0646\u0627\u0647 \u0648\u0627\u0643\u062a\u0628 \u0627\u0633\u0645\u0647. \u0633\u064a\u064f\u0646\u0634\u0626 \u0627\u0644\u062a\u0637\u0628\u064a\u0642 \u062f\u0639\u0648\u0629 \u0622\u0645\u0646\u0629 \u0648\u064a\u062a\u064a\u062d \u0644\u0643 \u0645\u0634\u0627\u0631\u0643\u062a\u0647\u0627 \u0645\u0628\u0627\u0634\u0631\u0629.");
            textView5.setTextSize(14.0f);
            textView5.setGravity(5);
            textView5.setPadding(0, 0, 0, 12);
            textView5.setTextColor(-11378601);
            linearLayout.addView(textView5, match());
            addButton(linearLayout, "\u0625\u0636\u0627\u0641\u0629 \u062c\u0647\u0627\u0632 \u062b\u0627\u0646\u0648\u064a", new Function0() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SyncDeviceManagementActivity.render$lambda$20(this.f$0);
                }
            });
            String string = getPreferences().getString(KEY_PENDING_INVITATION, null);
            if (string != null && !StringsKt.isBlank(string)) {
                addButton(linearLayout, "\u0645\u062a\u0627\u0628\u0639\u0629 \u0627\u0646\u062a\u0638\u0627\u0631 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a", new Function0() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda17
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SyncDeviceManagementActivity.render$lambda$21(this.f$0);
                    }
                });
            }
            addButton(linearLayout, "\u0625\u0639\u062f\u0627\u062f\u0627\u062a \u0627\u0644\u0631\u0628\u0637 \u0627\u0644\u0645\u062a\u0642\u062f\u0645\u0629", new Function0() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda18
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SyncDeviceManagementActivity.render$lambda$22(this.f$0);
                }
            });
        } else if (Intrinsics.areEqual(strLocalDeviceRole2, "SECONDARY")) {
            addButton(linearLayout, "\u0625\u0646\u0634\u0627\u0621 \u0637\u0644\u0628 \u0625\u0639\u0627\u062f\u0629 \u0627\u0642\u062a\u0631\u0627\u0646 \u0644\u0647\u0630\u0627 \u0627\u0644\u062c\u0647\u0627\u0632", new Function0() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda19
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SyncDeviceManagementActivity.render$lambda$23(this.f$0);
                }
            });
            addEnrollmentResponseForm(linearLayout);
            addButton(linearLayout, "\u0625\u0639\u062f\u0627\u062f \u0639\u0646\u0648\u0627\u0646 \u0648\u0633\u064a\u0637 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u0648\u0627\u0644\u0627\u062a\u0635\u0627\u0644", new Function0() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda20
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SyncDeviceManagementActivity.render$lambda$24(this.f$0);
                }
            });
            addButton(linearLayout, "\u0625\u0631\u0633\u0627\u0644 \u0627\u062e\u062a\u0628\u0627\u0631 \u0646\u0642\u0644 \u0645\u0634\u0641\u0631 \u0625\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a", new Function0() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda21
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SyncDeviceManagementActivity.render$lambda$26(this.f$0);
                }
            });
            addButton(linearLayout, "\u0641\u062d\u0635 \u0646\u062a\u064a\u062c\u0629 \u0627\u062e\u062a\u0628\u0627\u0631 \u0627\u0644\u0646\u0642\u0644", new Function0() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda23
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SyncDeviceManagementActivity.render$lambda$27(this.f$0);
                }
            });
        } else {
            addButton(linearLayout, "\u0625\u0646\u0634\u0627\u0621 \u0637\u0644\u0628 \u0627\u0642\u062a\u0631\u0627\u0646 \u0644\u0647\u0630\u0627 \u0627\u0644\u0647\u0627\u062a\u0641", new Function0() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda24
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SyncDeviceManagementActivity.render$lambda$28(this.f$0);
                }
            });
            addEnrollmentResponseForm(linearLayout);
            addButton(linearLayout, "\u0625\u0639\u062f\u0627\u062f \u0639\u0646\u0648\u0627\u0646 \u0648\u0633\u064a\u0637 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u0648\u0627\u0644\u0627\u062a\u0635\u0627\u0644", new Function0() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SyncDeviceManagementActivity.render$lambda$29(this.f$0);
                }
            });
        }
        addButton(linearLayout, "\u062a\u062d\u062f\u064a\u062b \u062d\u0627\u0644\u0629 \u0627\u0644\u0627\u062a\u0635\u0627\u0644", new Function0() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SyncDeviceManagementActivity.render$lambda$30(this.f$0);
            }
        });
        addButton(linearLayout, "\u0642\u0637\u0639 \u0627\u062a\u0635\u0627\u0644 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629", new Function0() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SyncDeviceManagementActivity.render$lambda$31(this.f$0);
            }
        });
        if (strLocalDeviceRole2 != null) {
            addButton(linearLayout, "\u0639\u0648\u062f\u0629", new Function0() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SyncDeviceManagementActivity.render$lambda$32(this.f$0);
                }
            });
        }
        ScrollView scrollView = new ScrollView(syncDeviceManagementActivity);
        scrollView.addView(linearLayout);
        setContentView(scrollView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit render$lambda$17(final SyncDeviceManagementActivity syncDeviceManagementActivity) {
        new AlertDialog.Builder(syncDeviceManagementActivity).setTitle("\u062a\u0623\u0643\u064a\u062f \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0631\u0626\u064a\u0633\u064a").setMessage("\u0633\u064a\u0635\u0628\u062d \u0647\u0630\u0627 \u0627\u0644\u0647\u0627\u062a\u0641 \u0627\u0644\u0645\u0631\u062c\u0639 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0645\u062a\u062c\u0631\u060c \u0648\u0633\u064a\u0635\u062f\u0631 \u062f\u0639\u0648\u0627\u062a \u0627\u0642\u062a\u0631\u0627\u0646 \u0644\u0644\u0623\u062c\u0647\u0632\u0629 \u0627\u0644\u062b\u0627\u0646\u0648\u064a\u0629. \u0644\u0627 \u062a\u062e\u062a\u064e\u0631 \u0647\u0630\u0627 \u0627\u0644\u062e\u064a\u0627\u0631 \u0639\u0644\u0649 \u0627\u0644\u0647\u0627\u062a\u0641 \u0627\u0644\u062b\u0627\u0646\u0648\u064a.").setNegativeButton("\u0631\u062c\u0648\u0639", (DialogInterface.OnClickListener) null).setPositiveButton("\u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0631\u0626\u064a\u0633\u064a", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda51
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SyncDeviceManagementActivity.render$lambda$17$lambda$16(this.f$0, dialogInterface, i);
            }
        }).show();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void render$lambda$17$lambda$16(final SyncDeviceManagementActivity syncDeviceManagementActivity, DialogInterface dialogInterface, int i) {
        runTask$default(syncDeviceManagementActivity, "\u062a\u0647\u064a\u0626\u0629 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0631\u0626\u064a\u0633\u064a", false, new Function0() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda52
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SyncDeviceManagementActivity.render$lambda$17$lambda$16$lambda$15(this.f$0);
            }
        }, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit render$lambda$17$lambda$16$lambda$15(SyncDeviceManagementActivity syncDeviceManagementActivity) {
        SyncProtocolRepository syncProtocolRepository = syncDeviceManagementActivity.repository;
        String str = null;
        if (syncProtocolRepository == null) {
            Intrinsics.throwUninitializedPropertyAccessException("repository");
            syncProtocolRepository = null;
        }
        String str2 = syncDeviceManagementActivity.actorUserId;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("actorUserId");
        } else {
            str = str2;
        }
        syncProtocolRepository.configureAsPrimary(str, "\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0631\u0626\u064a\u0633\u064a");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit render$lambda$18(SyncDeviceManagementActivity syncDeviceManagementActivity) {
        createSecondaryRequest$default(syncDeviceManagementActivity, null, 1, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit render$lambda$20(SyncDeviceManagementActivity syncDeviceManagementActivity) {
        syncDeviceManagementActivity.beginPrimaryAddDevice();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit render$lambda$21(SyncDeviceManagementActivity syncDeviceManagementActivity) {
        syncDeviceManagementActivity.resumePrimaryPairingMonitor();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit render$lambda$22(SyncDeviceManagementActivity syncDeviceManagementActivity) {
        syncDeviceManagementActivity.showAdvancedPairingOptions();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit render$lambda$23(SyncDeviceManagementActivity syncDeviceManagementActivity) {
        createSecondaryRequest$default(syncDeviceManagementActivity, null, 1, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit render$lambda$24(SyncDeviceManagementActivity syncDeviceManagementActivity) {
        syncDeviceManagementActivity.configureRelay();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit render$lambda$26(final SyncDeviceManagementActivity syncDeviceManagementActivity) {
        runTask$default(syncDeviceManagementActivity, "\u0625\u0631\u0633\u0627\u0644 \u0627\u062e\u062a\u0628\u0627\u0631 \u0627\u0644\u0646\u0642\u0644", false, new Function0() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda46
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SyncDeviceManagementActivity.render$lambda$26$lambda$25(this.f$0);
            }
        }, 2, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit render$lambda$26$lambda$25(SyncDeviceManagementActivity syncDeviceManagementActivity) {
        SyncProtocolRepository syncProtocolRepository = syncDeviceManagementActivity.repository;
        String str = null;
        if (syncProtocolRepository == null) {
            Intrinsics.throwUninitializedPropertyAccessException("repository");
            syncProtocolRepository = null;
        }
        String str2 = syncDeviceManagementActivity.actorUserId;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("actorUserId");
        } else {
            str = str2;
        }
        syncDeviceManagementActivity.getPreferences().edit().putString(KEY_LAST_PROBE_ID, syncProtocolRepository.enqueueTransportProbe(str)).apply();
        SyncRelaySession.INSTANCE.flushPending();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:25:0x004d  */
    /* JADX WARN: Code duplicated, block: B:30:0x005d  */
    public static final Unit render$lambda$27(SyncDeviceManagementActivity syncDeviceManagementActivity) throws IOException {
        String str;
        SyncProtocolRepository syncProtocolRepository = null;
        String string = syncDeviceManagementActivity.getPreferences().getString(KEY_LAST_PROBE_ID, null);
        TextView textView = syncDeviceManagementActivity.status;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_STATUS);
            textView = null;
        }
        SyncProtocolRepository syncProtocolRepository2 = syncDeviceManagementActivity.repository;
        if (syncProtocolRepository2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("repository");
        } else {
            syncProtocolRepository = syncProtocolRepository2;
        }
        if (string == null) {
            string = "";
        }
        String strOutboxStatus = syncProtocolRepository.outboxStatus(string);
        if (strOutboxStatus != null) {
            switch (strOutboxStatus) {
                case "SYNCED":
                    str = "\u0646\u062c\u062d \u0627\u062e\u062a\u0628\u0627\u0631 \u0627\u0644\u0646\u0642\u0644: \u0648\u0635\u0644 \u0627\u0644\u0623\u0645\u0631 \u0625\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0648\u0639\u0627\u062f \u0627\u0644\u0625\u0642\u0631\u0627\u0631 \u0627\u0644\u0645\u0634\u0641\u0631 \u0627\u0644\u0645\u0648\u062b\u0642.";
                    break;
                case "IN_PROGRESS":
                case "PENDING":
                case "FAILED":
                    str = "\u0627\u062e\u062a\u0628\u0627\u0631 \u0627\u0644\u0646\u0642\u0644 \u0645\u062d\u0641\u0648\u0638 \u0648\u064a\u0646\u062a\u0638\u0631 \u0627\u0644\u0625\u0642\u0631\u0627\u0631 \u0627\u0644\u0645\u0648\u062b\u0642 \u0645\u0646 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a.";
                    break;
                default:
                    str = "\u0644\u0627 \u064a\u0648\u062c\u062f \u0627\u062e\u062a\u0628\u0627\u0631 \u0646\u0642\u0644 \u0645\u062d\u0641\u0648\u0638 \u0639\u0644\u0649 \u0647\u0630\u0627 \u0627\u0644\u062c\u0647\u0627\u0632.";
                    break;
            }
        } else {
            str = "\u0644\u0627 \u064a\u0648\u062c\u062f \u0627\u062e\u062a\u0628\u0627\u0631 \u0646\u0642\u0644 \u0645\u062d\u0641\u0648\u0638 \u0639\u0644\u0649 \u0647\u0630\u0627 \u0627\u0644\u062c\u0647\u0627\u0632.";
        }
        textView.setText(str);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit render$lambda$28(SyncDeviceManagementActivity syncDeviceManagementActivity) {
        createSecondaryRequest$default(syncDeviceManagementActivity, null, 1, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit render$lambda$29(SyncDeviceManagementActivity syncDeviceManagementActivity) {
        syncDeviceManagementActivity.configureRelay();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit render$lambda$30(SyncDeviceManagementActivity syncDeviceManagementActivity) {
        TextView textView = syncDeviceManagementActivity.status;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_STATUS);
            textView = null;
        }
        textView.setText(SyncRelaySession.INSTANCE.isConnected() ? "\u0627\u0644\u0627\u062a\u0635\u0627\u0644 \u0627\u0644\u0645\u0634\u0641\u0631 \u062c\u0627\u0647\u0632." : "\u0627\u0644\u0627\u062a\u0635\u0627\u0644 \u063a\u064a\u0631 \u062c\u0627\u0647\u0632\u061b \u0633\u062a\u0633\u062a\u0645\u0631 \u0625\u0639\u0627\u062f\u0629 \u0627\u0644\u0645\u062d\u0627\u0648\u0644\u0629 \u062a\u0644\u0642\u0627\u0626\u064a\u0627\u064b \u0639\u0646\u062f \u0648\u062c\u0648\u062f \u0625\u0639\u062f\u0627\u062f \u0635\u0627\u0644\u062d.");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit render$lambda$31(SyncDeviceManagementActivity syncDeviceManagementActivity) {
        syncDeviceManagementActivity.getPreferences().edit().remove(KEY_RELAY_WSS).apply();
        SyncForegroundService.INSTANCE.stop(syncDeviceManagementActivity);
        TextView textView = syncDeviceManagementActivity.status;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_STATUS);
            textView = null;
        }
        textView.setText("\u062a\u0645 \u0642\u0637\u0639 \u0627\u0644\u0627\u062a\u0635\u0627\u0644 \u0648\u0625\u064a\u0642\u0627\u0641 \u0625\u0639\u0627\u062f\u0629 \u0627\u0644\u0627\u062a\u0635\u0627\u0644 \u0627\u0644\u062a\u0644\u0642\u0627\u0626\u064a\u0629.");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit render$lambda$32(SyncDeviceManagementActivity syncDeviceManagementActivity) {
        syncDeviceManagementActivity.finish();
        return Unit.INSTANCE;
    }

    private final void addButton(LinearLayout parent, String title, final Function0<Unit> action) {
        Button button = new Button(this);
        button.setText(title);
        button.setAllCaps(false);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                action.invoke();
            }
        });
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = 6;
        layoutParams.bottomMargin = 6;
        Unit unit = Unit.INSTANCE;
        parent.addView(button, layoutParams);
    }

    private final LinearLayout.LayoutParams match() {
        return new LinearLayout.LayoutParams(-1, -2);
    }

    static /* synthetic */ void runTask$default(SyncDeviceManagementActivity syncDeviceManagementActivity, String str, boolean z, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        syncDeviceManagementActivity.runTask(str, z, function0);
    }

    private final void runTask(final String label, final boolean finishOnSuccess, final Function0<Unit> action) {
        this.main.post(new Runnable() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda27
            @Override // java.lang.Runnable
            public final void run() {
                SyncDeviceManagementActivity.runTask$lambda$37(this.f$0, label);
            }
        });
        this.worker.execute(new Runnable() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda28
            @Override // java.lang.Runnable
            public final void run() {
                SyncDeviceManagementActivity.runTask$lambda$40(action, this, label, finishOnSuccess);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runTask$lambda$37(SyncDeviceManagementActivity syncDeviceManagementActivity, String str) {
        if (syncDeviceManagementActivity.isFinishing() || syncDeviceManagementActivity.isDestroyed()) {
            return;
        }
        TextView textView = syncDeviceManagementActivity.status;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_STATUS);
            textView = null;
        }
        textView.setText(str + "...");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runTask$lambda$40(Function0 function0, final SyncDeviceManagementActivity syncDeviceManagementActivity, final String str, final boolean z) {
        try {
            function0.invoke();
            syncDeviceManagementActivity.main.post(new Runnable() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda29
                @Override // java.lang.Runnable
                public final void run() {
                    SyncDeviceManagementActivity.runTask$lambda$40$lambda$38(this.f$0, str, z);
                }
            });
        } catch (Exception e) {
            syncDeviceManagementActivity.main.post(new Runnable() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda30
                @Override // java.lang.Runnable
                public final void run() {
                    SyncDeviceManagementActivity.runTask$lambda$40$lambda$39(this.f$0, str, e);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runTask$lambda$40$lambda$38(SyncDeviceManagementActivity syncDeviceManagementActivity, String str, boolean z) {
        TextView textView = syncDeviceManagementActivity.status;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_STATUS);
            textView = null;
        }
        textView.setText(str + ": \u0627\u0643\u062a\u0645\u0644\u062a \u0627\u0644\u0639\u0645\u0644\u064a\u0629.");
        Toast.makeText(syncDeviceManagementActivity, str + ": \u062a\u0645 \u0628\u0646\u062c\u0627\u062d", 1).show();
        boolean z2 = syncDeviceManagementActivity.bootstrapMode;
        if (z2 && z) {
            syncDeviceManagementActivity.setResult(-1);
            syncDeviceManagementActivity.finish();
        } else {
            if (z2) {
                return;
            }
            syncDeviceManagementActivity.render();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runTask$lambda$40$lambda$39(SyncDeviceManagementActivity syncDeviceManagementActivity, String str, Exception exc) {
        TextView textView = syncDeviceManagementActivity.status;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_STATUS);
            textView = null;
        }
        String message = exc.getMessage();
        if (message == null) {
            message = "\u0641\u0634\u0644\u062a \u0627\u0644\u0639\u0645\u0644\u064a\u0629";
        }
        textView.setText(str + ": " + message);
        SyncDeviceManagementActivity syncDeviceManagementActivity2 = syncDeviceManagementActivity;
        String message2 = exc.getMessage();
        if (message2 == null) {
            message2 = "\u062a\u0639\u0630\u0631 \u0625\u062a\u0645\u0627\u0645 \u0627\u0644\u0639\u0645\u0644\u064a\u0629";
        }
        Toast.makeText(syncDeviceManagementActivity2, message2, 1).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void copyText(String label, String value) {
        Object systemService = getSystemService("clipboard");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
        ((ClipboardManager) systemService).setPrimaryClip(ClipData.newPlainText(label, value));
        showOutput(label, value);
        Toast.makeText(this, "\u062a\u0645 \u0646\u0633\u062e \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0625\u0644\u0649 \u0627\u0644\u062d\u0627\u0641\u0638\u0629", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void shareText(String label, String value) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", label);
        intent.putExtra("android.intent.extra.TEXT", value);
        startActivity(Intent.createChooser(intent, "\u0645\u0634\u0627\u0631\u0643\u0629 " + label));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:44:0x00b7  */
    public final void handleIncomingPairingShare(String raw) {
        Object objM381constructorimpl;
        String strReplaceFirst;
        try {
            Result.Companion companion = Result.INSTANCE;
            SyncDeviceManagementActivity syncDeviceManagementActivity = this;
            objM381constructorimpl = Result.m381constructorimpl(new JSONObject(raw));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
        }
        TextView textView = null;
        if (Result.m384exceptionOrNullimpl(objM381constructorimpl) != null) {
            TextView textView2 = this.status;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_STATUS);
            } else {
                textView = textView2;
            }
            textView.setText("\u0627\u0644\u0645\u062d\u062a\u0648\u0649 \u0627\u0644\u0645\u0633\u062a\u0644\u0645 \u0644\u064a\u0633 \u062d\u0632\u0645\u0629 \u0627\u0642\u062a\u0631\u0627\u0646 \u0635\u0627\u0644\u062d\u0629.");
            return;
        }
        JSONObject jSONObject = (JSONObject) objM381constructorimpl;
        if (jSONObject.has("request_signature") && jSONObject.has("device_id")) {
            SyncProtocolRepository syncProtocolRepository = this.repository;
            if (syncProtocolRepository == null) {
                Intrinsics.throwUninitializedPropertyAccessException("repository");
                syncProtocolRepository = null;
            }
            if (!Intrinsics.areEqual(syncProtocolRepository.localDeviceRole(), "PRIMARY")) {
                TextView textView3 = this.status;
                if (textView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_STATUS);
                } else {
                    textView = textView3;
                }
                textView.setText("\u0647\u0630\u0627 \u0637\u0644\u0628 \u0635\u0627\u062f\u0631 \u0645\u0646 \u062c\u0647\u0627\u0632 \u062b\u0627\u0646\u0648\u064a\u061b \u0627\u0641\u062a\u062d \u0627\u0644\u0645\u0634\u0627\u0631\u0643\u0629 \u0639\u0644\u0649 \u0627\u0644\u0647\u0627\u062a\u0641 \u0627\u0644\u0631\u0626\u064a\u0633\u064a.");
                return;
            }
            enrollSecondaryFromRequest$default(this, raw, null, 2, null);
            return;
        }
        if (jSONObject.has("device_certificate_json") && jSONObject.has("credential")) {
            SyncProtocolRepository syncProtocolRepository2 = this.repository;
            if (syncProtocolRepository2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("repository");
                syncProtocolRepository2 = null;
            }
            if (Intrinsics.areEqual(syncProtocolRepository2.localDeviceRole(), "PRIMARY")) {
                TextView textView4 = this.status;
                if (textView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_STATUS);
                } else {
                    textView = textView4;
                }
                textView.setText("\u0647\u0630\u0647 \u0627\u0633\u062a\u062c\u0627\u0628\u0629 \u0627\u0642\u062a\u0631\u0627\u0646\u061b \u0627\u0641\u062a\u062d \u0627\u0644\u0645\u0634\u0627\u0631\u0643\u0629 \u0639\u0644\u0649 \u0627\u0644\u0647\u0627\u062a\u0641 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u0627\u0644\u0645\u0642\u0635\u0648\u062f.");
                return;
            }
            String string = getPreferences().getString(KEY_RELAY_WSS, null);
            if (string != null) {
                strReplaceFirst = new Regex("^wss://", RegexOption.IGNORE_CASE).replaceFirst(string, "https://");
                if (strReplaceFirst == null) {
                    strReplaceFirst = DEFAULT_RELAY_HTTPS;
                }
            } else {
                strReplaceFirst = DEFAULT_RELAY_HTTPS;
            }
            completeEnrollmentResponse(raw, strReplaceFirst);
            return;
        }
        if (jSONObject.has("invitation_token")) {
            SyncProtocolRepository syncProtocolRepository3 = this.repository;
            if (syncProtocolRepository3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("repository");
                syncProtocolRepository3 = null;
            }
            if (Intrinsics.areEqual(syncProtocolRepository3.localDeviceRole(), "PRIMARY")) {
                TextView textView5 = this.status;
                if (textView5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_STATUS);
                } else {
                    textView = textView5;
                }
                textView.setText("\u0647\u0630\u0647 \u062f\u0639\u0648\u0629 \u0635\u0627\u062f\u0631\u0629 \u0645\u0646 \u0627\u0644\u0647\u0627\u062a\u0641 \u0627\u0644\u0631\u0626\u064a\u0633\u064a\u061b \u0627\u0641\u062a\u062d \u0627\u0644\u0645\u0634\u0627\u0631\u0643\u0629 \u0639\u0644\u0649 \u0627\u0644\u0647\u0627\u062a\u0641 \u0627\u0644\u062b\u0627\u0646\u0648\u064a.");
                return;
            }
            createSecondaryRequest(raw);
            return;
        }
        TextView textView6 = this.status;
        if (textView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_STATUS);
        } else {
            textView = textView6;
        }
        textView.setText("\u0644\u0645 \u0623\u062a\u0639\u0631\u0641 \u0639\u0644\u0649 \u0646\u0648\u0639 \u062d\u0632\u0645\u0629 \u0627\u0644\u0627\u0642\u062a\u0631\u0627\u0646 \u0627\u0644\u0645\u0633\u062a\u0644\u0645\u0629.");
    }

    private final void showOutput(final String title, final String value) {
        SyncDeviceManagementActivity syncDeviceManagementActivity = this;
        TextView textView = new TextView(syncDeviceManagementActivity);
        textView.setText(value);
        textView.setTextSize(12.0f);
        textView.setTextIsSelectable(true);
        textView.setPadding(20, 12, 20, 12);
        AlertDialog.Builder title2 = new AlertDialog.Builder(syncDeviceManagementActivity).setTitle(title);
        ScrollView scrollView = new ScrollView(syncDeviceManagementActivity);
        scrollView.addView(textView);
        title2.setView(scrollView).setPositiveButton("\u0646\u0633\u062e", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda38
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SyncDeviceManagementActivity.showOutput$lambda$46(this.f$0, title, value, dialogInterface, i);
            }
        }).setNeutralButton("\u0645\u0634\u0627\u0631\u0643\u0629", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda39
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.shareText(title, value);
            }
        }).setNegativeButton("\u0625\u063a\u0644\u0627\u0642", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showOutput$lambda$46(SyncDeviceManagementActivity syncDeviceManagementActivity, String str, String str2, DialogInterface dialogInterface, int i) {
        Object systemService = syncDeviceManagementActivity.getSystemService("clipboard");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
        ((ClipboardManager) systemService).setPrimaryClip(ClipData.newPlainText(str, str2));
        Toast.makeText(syncDeviceManagementActivity, "\u062a\u0645 \u0627\u0644\u0646\u0633\u062e", 0).show();
    }

    static /* synthetic */ void prompt$default(SyncDeviceManagementActivity syncDeviceManagementActivity, String str, String str2, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        syncDeviceManagementActivity.prompt(str, str2, z, function1);
    }

    private final void prompt(String title, String hint, boolean multiline, final Function1<? super String, Unit> action) {
        SyncDeviceManagementActivity syncDeviceManagementActivity = this;
        final EditText editText = new EditText(syncDeviceManagementActivity);
        editText.setHint(hint);
        if (multiline) {
            editText.setMinLines(4);
            editText.setGravity(53);
        }
        editText.setTextIsSelectable(true);
        new AlertDialog.Builder(syncDeviceManagementActivity).setTitle(title).setView(editText).setPositiveButton("\u0645\u062a\u0627\u0628\u0639\u0629", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SyncDeviceManagementActivity.prompt$lambda$49(action, editText, dialogInterface, i);
            }
        }).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void prompt$lambda$49(Function1 function1, EditText editText, DialogInterface dialogInterface, int i) {
        function1.invoke(StringsKt.trim((CharSequence) editText.getText().toString()).toString());
    }

    /* JADX INFO: renamed from: copyTrustRoot-d1pmJ48, reason: not valid java name */
    private final Object m358copyTrustRootd1pmJ48() {
        Object objM381constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            SyncDeviceManagementActivity syncDeviceManagementActivity = this;
            SyncProtocolRepository syncProtocolRepository = this.repository;
            if (syncProtocolRepository == null) {
                Intrinsics.throwUninitializedPropertyAccessException("repository");
                syncProtocolRepository = null;
            }
            copyText("STORE_ROOT_PUBLIC_KEYS", syncProtocolRepository.primaryRelayTrustRootJson());
            objM381constructorimpl = Result.m381constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
        }
        Throwable thM384exceptionOrNullimpl = Result.m384exceptionOrNullimpl(objM381constructorimpl);
        if (thM384exceptionOrNullimpl != null) {
            SyncDeviceManagementActivity syncDeviceManagementActivity2 = this;
            String message = thM384exceptionOrNullimpl.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u0627\u0633\u062a\u062e\u0631\u0627\u062c \u062c\u0630\u0631 \u0627\u0644\u062b\u0642\u0629";
            }
            Toast.makeText(syncDeviceManagementActivity2, message, 1).show();
        }
        return objM381constructorimpl;
    }

    private final void beginPrimaryAddDevice() {
        SyncDeviceManagementActivity syncDeviceManagementActivity = this;
        final EditText editText = new EditText(syncDeviceManagementActivity);
        editText.setHint("\u0645\u062b\u0644\u0627\u064b: \u0647\u0627\u062a\u0641 \u0627\u0644\u0643\u0627\u0634\u064a\u0631");
        editText.setSingleLine(true);
        new AlertDialog.Builder(syncDeviceManagementActivity).setTitle("\u0625\u0636\u0627\u0641\u0629 \u062c\u0647\u0627\u0632 \u062b\u0627\u0646\u0648\u064a").setMessage("\u0627\u0643\u062a\u0628 \u0627\u0633\u0645\u0627\u064b \u064a\u0645\u064a\u0632 \u0627\u0644\u0647\u0627\u062a\u0641 \u0627\u0644\u062c\u062f\u064a\u062f. \u0633\u0646\u0646\u0634\u0626 \u062f\u0639\u0648\u0629 \u0622\u0645\u0646\u0629 \u064a\u0645\u0643\u0646\u0643 \u0645\u0634\u0627\u0631\u0643\u062a\u0647\u0627 \u0645\u0639\u0647.").setView(editText).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u0625\u0646\u0634\u0627\u0621 \u0627\u0644\u062f\u0639\u0648\u0629", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda47
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SyncDeviceManagementActivity.beginPrimaryAddDevice$lambda$53(editText, this, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void beginPrimaryAddDevice$lambda$53(EditText editText, SyncDeviceManagementActivity syncDeviceManagementActivity, DialogInterface dialogInterface, int i) {
        String string = StringsKt.trim((CharSequence) editText.getText().toString()).toString();
        if (StringsKt.isBlank(string)) {
            Toast.makeText(syncDeviceManagementActivity, "\u0627\u0643\u062a\u0628 \u0627\u0633\u0645\u0627\u064b \u0644\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a", 1).show();
        } else {
            syncDeviceManagementActivity.createInvitation(string);
        }
    }

    private final void createInvitation(final String deviceName) {
        runTask$default(this, "\u0625\u0646\u0634\u0627\u0621 \u062f\u0639\u0648\u0629 \u0644\u0644\u062c\u0647\u0627\u0632: " + deviceName, false, new Function0() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda22
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SyncDeviceManagementActivity.createInvitation$lambda$56(this.f$0, deviceName);
            }
        }, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit createInvitation$lambda$56(final SyncDeviceManagementActivity syncDeviceManagementActivity, final String str) throws JSONException {
        SyncProtocolRepository syncProtocolRepository = syncDeviceManagementActivity.repository;
        SyncProtocolRepository syncProtocolRepository2 = null;
        if (syncProtocolRepository == null) {
            Intrinsics.throwUninitializedPropertyAccessException("repository");
            syncProtocolRepository = null;
        }
        String str2 = syncDeviceManagementActivity.actorUserId;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("actorUserId");
            str2 = null;
        }
        SyncPairingInvitation syncPairingInvitationCreatePairingInvitation = syncProtocolRepository.createPairingInvitation(str2);
        JSONObject jSONObjectPut = new JSONObject().put("version", 1);
        SyncProtocolRepository syncProtocolRepository3 = syncDeviceManagementActivity.repository;
        if (syncProtocolRepository3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("repository");
            syncProtocolRepository3 = null;
        }
        JSONObject jSONObjectPut2 = jSONObjectPut.put("store_id", syncProtocolRepository3.storeId());
        SyncProtocolRepository syncProtocolRepository4 = syncDeviceManagementActivity.repository;
        if (syncProtocolRepository4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("repository");
            syncProtocolRepository4 = null;
        }
        JSONObject jSONObjectPut3 = jSONObjectPut2.put("primary_device_id", syncProtocolRepository4.localDeviceId()).put("invitation_token", syncPairingInvitationCreatePairingInvitation.getToken()).put("requested_device_name", str);
        SyncProtocolRepository syncProtocolRepository5 = syncDeviceManagementActivity.repository;
        if (syncProtocolRepository5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("repository");
            syncProtocolRepository5 = null;
        }
        final String string = jSONObjectPut3.put("store_time_zone_id", syncProtocolRepository5.storeTimeZoneId()).put("expires_at", syncPairingInvitationCreatePairingInvitation.getExpiresAt()).toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        SharedPreferences.Editor editorPutString = syncDeviceManagementActivity.getPreferences().edit().putString(KEY_PENDING_INVITATION, syncPairingInvitationCreatePairingInvitation.getToken());
        SyncProtocolRepository syncProtocolRepository6 = syncDeviceManagementActivity.repository;
        if (syncProtocolRepository6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("repository");
        } else {
            syncProtocolRepository2 = syncProtocolRepository6;
        }
        editorPutString.putString(KEY_PENDING_STORE, syncProtocolRepository2.storeId()).apply();
        syncDeviceManagementActivity.main.post(new Runnable() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda32
            @Override // java.lang.Runnable
            public final void run() {
                SyncDeviceManagementActivity.createInvitation$lambda$56$lambda$55(this.f$0, str, string);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createInvitation$lambda$56$lambda$55(SyncDeviceManagementActivity syncDeviceManagementActivity, String str, String str2) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", "\u062f\u0639\u0648\u0629 \u0631\u0628\u0637 \u062c\u0647\u0627\u0632 The Daily Shop");
        intent.putExtra("android.intent.extra.TEXT", str2);
        syncDeviceManagementActivity.startActivity(Intent.createChooser(intent, "\u0645\u0634\u0627\u0631\u0643\u0629 \u062f\u0639\u0648\u0629 \u0627\u0644\u062c\u0647\u0627\u0632 " + str));
        TextView textView = syncDeviceManagementActivity.status;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_STATUS);
            textView = null;
        }
        textView.setText("\u062a\u0645 \u0625\u0646\u0634\u0627\u0621 \u062f\u0639\u0648\u0629 \u0622\u0645\u0646\u0629 \u0644\u0644\u062c\u0647\u0627\u0632 " + str + ". \u0634\u0627\u0631\u0643\u0647\u0627 \u0645\u0639 \u0627\u0644\u0647\u0627\u062a\u0641 \u0627\u0644\u062b\u0627\u0646\u0648\u064a. \u0633\u064a\u0633\u062a\u0642\u0628\u0644 \u0647\u0630\u0627 \u0627\u0644\u0647\u0627\u062a\u0641 \u0627\u0644\u0637\u0644\u0628 \u0639\u0628\u0631 \u0627\u0644\u0648\u0633\u064a\u0637.");
        syncDeviceManagementActivity.resumePrimaryPairingMonitor();
    }

    private final String pairingRelayHttps() {
        String string = getPreferences().getString(KEY_RELAY_WSS, null);
        if (string != null) {
            String strReplaceFirst = new Regex("^wss://", RegexOption.IGNORE_CASE).replaceFirst(string, "https://");
            if (strReplaceFirst != null) {
                return strReplaceFirst;
            }
        }
        return DEFAULT_RELAY_HTTPS;
    }

    private final SyncPairingRelayClient pairingRelayClient() {
        return new SyncPairingRelayClient(pairingRelayHttps(), null, 2, null);
    }

    private final void resumePrimaryPairingMonitor() {
        TextView textView = null;
        String string = getPreferences().getString(KEY_PENDING_INVITATION, null);
        final String string2 = string != null ? StringsKt.trim((CharSequence) string).toString() : null;
        if (string2 == null) {
            string2 = "";
        }
        String string3 = getPreferences().getString(KEY_PENDING_STORE, null);
        String string4 = string3 != null ? StringsKt.trim((CharSequence) string3).toString() : null;
        final String str = string4 != null ? string4 : "";
        if (StringsKt.isBlank(string2) || StringsKt.isBlank(str)) {
            TextView textView2 = this.status;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_STATUS);
            } else {
                textView = textView2;
            }
            textView.setText("\u0644\u0627 \u062a\u0648\u062c\u062f \u062f\u0639\u0648\u0629 \u0633\u0627\u0631\u064a\u0629 \u0644\u0627\u0646\u062a\u0638\u0627\u0631 \u062c\u0647\u0627\u0632 \u062b\u0627\u0646\u0648\u064a.");
            return;
        }
        if (!this.pairingMonitorRunning.compareAndSet(false, true)) {
            TextView textView3 = this.status;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_STATUS);
            } else {
                textView = textView3;
            }
            textView.setText("\u064a\u062c\u0631\u064a \u0627\u0646\u062a\u0638\u0627\u0631 \u0637\u0644\u0628 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u0628\u0627\u0644\u0641\u0639\u0644.");
            return;
        }
        TextView textView4 = this.status;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_STATUS);
        } else {
            textView = textView4;
        }
        textView.setText("\u0628\u0627\u0646\u062a\u0638\u0627\u0631 \u0637\u0644\u0628 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u0639\u0628\u0631 \u0648\u0633\u064a\u0637 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629...");
        this.worker.execute(new Runnable() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda44
            @Override // java.lang.Runnable
            public final void run() {
                SyncDeviceManagementActivity.resumePrimaryPairingMonitor$lambda$59(this.f$0, str, string2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resumePrimaryPairingMonitor$lambda$59(final SyncDeviceManagementActivity syncDeviceManagementActivity, String str, String str2) {
        try {
            try {
                final SyncPairingRelayClient syncPairingRelayClientPairingRelayClient = syncDeviceManagementActivity.pairingRelayClient();
                long jCurrentTimeMillis = System.currentTimeMillis() + 600000;
                final String strReceiveRequest = null;
                while (System.currentTimeMillis() < jCurrentTimeMillis && !syncDeviceManagementActivity.isFinishing() && !syncDeviceManagementActivity.isDestroyed() && (strReceiveRequest = syncPairingRelayClientPairingRelayClient.receiveRequest(str, str2)) == null) {
                    Thread.sleep(2000L);
                }
                if (strReceiveRequest == null) {
                    throw new IllegalStateException("\u0627\u0646\u062a\u0647\u062a \u0645\u0647\u0644\u0629 \u0627\u0646\u062a\u0638\u0627\u0631 \u0627\u0644\u062c\u0647\u0627\u0632\u061b \u064a\u0645\u0643\u0646\u0643 \u0625\u0646\u0634\u0627\u0621 \u062f\u0639\u0648\u0629 \u062c\u062f\u064a\u062f\u0629.");
                }
                syncDeviceManagementActivity.main.post(new Runnable() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda36
                    @Override // java.lang.Runnable
                    public final void run() {
                        SyncDeviceManagementActivity.resumePrimaryPairingMonitor$lambda$59$lambda$57(this.f$0, strReceiveRequest, syncPairingRelayClientPairingRelayClient);
                    }
                });
                syncDeviceManagementActivity.pairingMonitorRunning.set(false);
            } catch (Exception e) {
                syncDeviceManagementActivity.main.post(new Runnable() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda37
                    @Override // java.lang.Runnable
                    public final void run() {
                        SyncDeviceManagementActivity.resumePrimaryPairingMonitor$lambda$59$lambda$58(this.f$0, e);
                    }
                });
            }
        } catch (Throwable th) {
            syncDeviceManagementActivity.pairingMonitorRunning.set(false);
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resumePrimaryPairingMonitor$lambda$59$lambda$57(SyncDeviceManagementActivity syncDeviceManagementActivity, String str, SyncPairingRelayClient syncPairingRelayClient) {
        TextView textView = syncDeviceManagementActivity.status;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_STATUS);
            textView = null;
        }
        textView.setText("\u0648\u0635\u0644 \u0637\u0644\u0628 \u062c\u0647\u0627\u0632 \u062b\u0627\u0646\u0648\u064a. \u0631\u0627\u062c\u0639 \u0627\u0633\u0645\u0647 \u062b\u0645 \u0648\u0627\u0641\u0642 \u0639\u0644\u0649 \u0627\u0639\u062a\u0645\u0627\u062f\u0647.");
        syncDeviceManagementActivity.enrollSecondaryFromRequest(str, syncPairingRelayClient);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resumePrimaryPairingMonitor$lambda$59$lambda$58(SyncDeviceManagementActivity syncDeviceManagementActivity, Exception exc) {
        TextView textView = syncDeviceManagementActivity.status;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_STATUS);
            textView = null;
        }
        String message = exc.getMessage();
        if (message == null) {
            message = "\u062e\u0637\u0623 \u0627\u062a\u0635\u0627\u0644";
        }
        textView.setText("\u062a\u0639\u0630\u0631 \u0627\u0633\u062a\u0642\u0628\u0627\u0644 \u0637\u0644\u0628 \u0627\u0644\u0627\u0642\u062a\u0631\u0627\u0646: " + message);
    }

    private final void showAdvancedPairingOptions() {
        new AlertDialog.Builder(this).setTitle("\u0625\u0639\u062f\u0627\u062f\u0627\u062a \u0627\u0644\u0631\u0628\u0637 \u0627\u0644\u0645\u062a\u0642\u062f\u0645\u0629").setItems(new String[]{"\u0646\u0633\u062e \u062c\u0630\u0631 \u0627\u0644\u062b\u0642\u0629 \u0644\u062a\u062b\u0628\u064a\u062a\u0647 \u0641\u064a Cloudflare", "\u062a\u0633\u062c\u064a\u0644 \u0637\u0644\u0628 \u0627\u0642\u062a\u0631\u0627\u0646 \u0648\u0627\u0631\u062f \u0645\u0646 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a", "\u062a\u0633\u062c\u064a\u0644 \u0634\u0647\u0627\u062f\u0627\u062a \u0627\u0644\u0623\u062c\u0647\u0632\u0629 \u0644\u062f\u0649 \u0627\u0644\u0648\u0633\u064a\u0637", "\u0625\u0639\u062f\u0627\u062f \u0639\u0646\u0648\u0627\u0646 \u0648\u0633\u064a\u0637 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629", "\u0625\u0644\u063a\u0627\u0621 \u0634\u0647\u0627\u062f\u0629 \u062c\u0647\u0627\u0632 \u062b\u0627\u0646\u0648\u064a"}, new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda45
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SyncDeviceManagementActivity.showAdvancedPairingOptions$lambda$60(this.f$0, dialogInterface, i);
            }
        }).setNegativeButton("\u0625\u063a\u0644\u0627\u0642", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAdvancedPairingOptions$lambda$60(SyncDeviceManagementActivity syncDeviceManagementActivity, DialogInterface dialogInterface, int i) {
        if (i == 0) {
            syncDeviceManagementActivity.m358copyTrustRootd1pmJ48();
            return;
        }
        if (i == 1) {
            enrollSecondaryFromRequest$default(syncDeviceManagementActivity, null, null, 3, null);
            return;
        }
        if (i == 2) {
            syncDeviceManagementActivity.syncDeviceRegistryNow();
        } else if (i == 3) {
            syncDeviceManagementActivity.configureRelay();
        } else {
            if (i != 4) {
                return;
            }
            syncDeviceManagementActivity.revokeSecondary();
        }
    }

    @Override // android.app.Activity
    @Deprecated(message = "The role must be selected before leaving device setup.")
    public void onBackPressed() {
        SyncProtocolRepository syncProtocolRepository = this.repository;
        if (syncProtocolRepository == null) {
            Intrinsics.throwUninitializedPropertyAccessException("repository");
            syncProtocolRepository = null;
        }
        if (syncProtocolRepository.localDeviceRole() == null) {
            Toast.makeText(this, "\u062d\u062f\u0651\u062f \u062f\u0648\u0631 \u0647\u0630\u0627 \u0627\u0644\u0647\u0627\u062a\u0641 \u0623\u0648\u0644\u0627\u064b: \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0631\u0626\u064a\u0633\u064a \u0623\u0648 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a.", 1).show();
        } else {
            super.onBackPressed();
        }
    }

    static /* synthetic */ void createSecondaryRequest$default(SyncDeviceManagementActivity syncDeviceManagementActivity, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        syncDeviceManagementActivity.createSecondaryRequest(str);
    }

    private final void createSecondaryRequest(String invitationText) {
        Object objM381constructorimpl;
        SyncDeviceManagementActivity syncDeviceManagementActivity = this;
        LinearLayout linearLayout = new LinearLayout(syncDeviceManagementActivity);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(18, 8, 18, 0);
        final EditText editText = new EditText(syncDeviceManagementActivity);
        editText.setHint("\u0627\u0644\u0635\u0642 \u062d\u0632\u0645\u0629 \u062f\u0639\u0648\u0629 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a");
        editText.setMinLines(3);
        editText.setGravity(53);
        editText.setText(invitationText == null ? "" : invitationText);
        try {
            Result.Companion companion = Result.INSTANCE;
            SyncDeviceManagementActivity syncDeviceManagementActivity2 = this;
            if (invitationText == null) {
                invitationText = "";
            }
            objM381constructorimpl = Result.m381constructorimpl(new JSONObject(invitationText).optString("requested_device_name"));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
        }
        Object obj = Result.m387isFailureimpl(objM381constructorimpl) ? "" : objM381constructorimpl;
        final EditText editText2 = new EditText(syncDeviceManagementActivity);
        editText2.setHint("\u0627\u0633\u0645 \u0647\u0630\u0627 \u0627\u0644\u062c\u0647\u0627\u0632");
        editText2.setSingleLine(true);
        editText2.setText((String) obj);
        linearLayout.addView(editText, match());
        linearLayout.addView(editText2, match());
        new AlertDialog.Builder(syncDeviceManagementActivity).setTitle("\u0625\u0639\u062f\u0627\u062f \u0647\u0630\u0627 \u0627\u0644\u0647\u0627\u062a\u0641 \u0643\u062c\u0647\u0627\u0632 \u062b\u0627\u0646\u0648\u064a").setMessage("\u064a\u062c\u0628 \u0623\u0648\u0644\u0627\u064b \u062a\u0647\u064a\u0626\u0629 \u0627\u0644\u0647\u0627\u062a\u0641 \u0627\u0644\u0631\u0626\u064a\u0633\u064a \u0648\u0625\u0646\u0634\u0627\u0621 \u062f\u0639\u0648\u0629 \u0627\u0642\u062a\u0631\u0627\u0646 \u0645\u0646\u0647. \u0627\u0644\u0635\u0642 \u0627\u0644\u062f\u0639\u0648\u0629 \u0647\u0646\u0627 \u062b\u0645 \u0623\u0646\u0634\u0626 \u0627\u0644\u0637\u0644\u0628.").setView(linearLayout).setPositiveButton("\u0625\u0646\u0634\u0627\u0621 \u0627\u0644\u0637\u0644\u0628", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda11
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SyncDeviceManagementActivity.createSecondaryRequest$lambda$76(editText, editText2, this, dialogInterface, i);
            }
        }).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createSecondaryRequest$lambda$76(EditText editText, EditText editText2, final SyncDeviceManagementActivity syncDeviceManagementActivity, DialogInterface dialogInterface, int i) {
        final String string = StringsKt.trim((CharSequence) editText.getText().toString()).toString();
        final String string2 = StringsKt.trim((CharSequence) editText2.getText().toString()).toString();
        runTask$default(syncDeviceManagementActivity, "\u0625\u0646\u0634\u0627\u0621 \u0637\u0644\u0628 \u0627\u0644\u0627\u0642\u062a\u0631\u0627\u0646", false, new Function0() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SyncDeviceManagementActivity.createSecondaryRequest$lambda$76$lambda$75(string, string2, syncDeviceManagementActivity);
            }
        }, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit createSecondaryRequest$lambda$76$lambda$75(String str, String str2, final SyncDeviceManagementActivity syncDeviceManagementActivity) throws JSONException, InterruptedException, NoSuchAlgorithmException {
        if (StringsKt.isBlank(str)) {
            throw new IllegalArgumentException("\u0627\u0644\u0635\u0642 \u062f\u0639\u0648\u0629 \u0635\u0627\u062f\u0631\u0629 \u0645\u0646 \u0627\u0644\u0647\u0627\u062a\u0641 \u0627\u0644\u0631\u0626\u064a\u0633\u064a \u0623\u0648\u0644\u0627\u064b".toString());
        }
        String str3 = str2;
        if (StringsKt.isBlank(str3)) {
            throw new IllegalArgumentException("\u0627\u0643\u062a\u0628 \u0627\u0633\u0645\u0627\u064b \u064a\u0645\u064a\u0632 \u0647\u0630\u0627 \u0627\u0644\u0647\u0627\u062a\u0641".toString());
        }
        JSONObject jSONObject = new JSONObject(str);
        if (jSONObject.getInt("version") != 1) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        String string = jSONObject.getString("store_id");
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        if (StringsKt.isBlank(string)) {
            throw new IllegalArgumentException("\u062f\u0639\u0648\u0629 \u0627\u0644\u0627\u0642\u062a\u0631\u0627\u0646 \u0644\u0627 \u062a\u062d\u062a\u0648\u064a \u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0645\u062a\u062c\u0631".toString());
        }
        String string2 = jSONObject.getString("primary_device_id");
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        if (StringsKt.isBlank(string2)) {
            throw new IllegalArgumentException("\u062f\u0639\u0648\u0629 \u0627\u0644\u0627\u0642\u062a\u0631\u0627\u0646 \u0644\u0627 \u062a\u062d\u062a\u0648\u064a \u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0631\u0626\u064a\u0633\u064a".toString());
        }
        String strOptString = jSONObject.optString("requested_device_name");
        Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
        String string3 = StringsKt.trim((CharSequence) strOptString).toString();
        if (StringsKt.isBlank(str3)) {
            str3 = string3;
        }
        String str4 = str3;
        if (StringsKt.isBlank(str4)) {
            throw new IllegalArgumentException("\u0627\u0643\u062a\u0628 \u0627\u0633\u0645\u0627\u064b \u064a\u0645\u064a\u0632 \u0647\u0630\u0627 \u0627\u0644\u0647\u0627\u062a\u0641".toString());
        }
        SyncProtocolRepository syncProtocolRepository = syncDeviceManagementActivity.repository;
        if (syncProtocolRepository == null) {
            Intrinsics.throwUninitializedPropertyAccessException("repository");
            syncProtocolRepository = null;
        }
        String strLocalDeviceId = syncProtocolRepository.localDeviceId();
        KeyPair orCreate = SyncDeviceAuthenticator.INSTANCE.getOrCreate(syncDeviceManagementActivity, strLocalDeviceId);
        byte[] bArr = new byte[32];
        new SecureRandom().nextBytes(bArr);
        String strEncodeToString = Base64.encodeToString(bArr, 11);
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPairGenerateKeyPair = keyPairGenerator.generateKeyPair();
        JSONObject jSONObjectPut = new JSONObject().put("version", 1).put("store_id", jSONObject.getString("store_id")).put("primary_device_id", jSONObject.getString("primary_device_id")).put("invitation_token", jSONObject.getString("invitation_token")).put("device_id", strLocalDeviceId).put("display_name", str4).put("public_key", Base64.encodeToString(orCreate.getPublic().getEncoded(), 2)).put("response_token", strEncodeToString).put("response_encryption_public_key", Base64.encodeToString(keyPairGenerateKeyPair.getPublic().getEncoded(), 2));
        String str5 = syncDeviceManagementActivity.actorUserId;
        if (str5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("actorUserId");
            str5 = null;
        }
        if (!StringsKt.isBlank(str5)) {
            String str6 = syncDeviceManagementActivity.actorUserId;
            if (str6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("actorUserId");
                str6 = null;
            }
            jSONObjectPut.put(EXTRA_ACTOR_USER_ID, str6);
        }
        SyncDeviceAuthenticator syncDeviceAuthenticator = SyncDeviceAuthenticator.INSTANCE;
        PrivateKey privateKey = orCreate.getPrivate();
        Intrinsics.checkNotNullExpressionValue(privateKey, "getPrivate(...)");
        String string4 = jSONObjectPut.toString();
        Intrinsics.checkNotNullExpressionValue(string4, "toString(...)");
        byte[] bytes = string4.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        String string5 = new JSONObject(jSONObjectPut.toString()).put("request_signature", Base64.encodeToString(syncDeviceAuthenticator.signBytes(privateKey, bytes), 2)).toString();
        Intrinsics.checkNotNullExpressionValue(string5, "toString(...)");
        SyncPairingRelayClient syncPairingRelayClientPairingRelayClient = syncDeviceManagementActivity.pairingRelayClient();
        String string6 = jSONObject.getString("store_id");
        String string7 = jSONObject.getString("invitation_token");
        Intrinsics.checkNotNull(string6);
        Intrinsics.checkNotNull(string7);
        Intrinsics.checkNotNull(strEncodeToString);
        syncPairingRelayClientPairingRelayClient.publishRequest(string6, string7, strEncodeToString, string5);
        syncDeviceManagementActivity.main.post(new Runnable() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda40
            @Override // java.lang.Runnable
            public final void run() {
                SyncDeviceManagementActivity.createSecondaryRequest$lambda$76$lambda$75$lambda$73(this.f$0);
            }
        });
        long jCurrentTimeMillis = System.currentTimeMillis() + 600000;
        String strReceiveResponse = null;
        while (System.currentTimeMillis() < jCurrentTimeMillis && (strReceiveResponse = syncPairingRelayClientPairingRelayClient.receiveResponse(string6, string7, strLocalDeviceId, strEncodeToString)) == null) {
            Thread.sleep(2000L);
        }
        String str7 = strReceiveResponse;
        if (str7 == null) {
            throw new IllegalArgumentException("\u0627\u0646\u062a\u0647\u062a \u0645\u0647\u0644\u0629 \u0627\u0646\u062a\u0638\u0627\u0631 \u0645\u0648\u0627\u0641\u0642\u0629 \u0627\u0644\u0647\u0627\u062a\u0641 \u0627\u0644\u0631\u0626\u064a\u0633\u064a\u061b \u0623\u0646\u0634\u0626 \u062f\u0639\u0648\u0629 \u062c\u062f\u064a\u062f\u0629.".toString());
        }
        Intrinsics.checkNotNull(str7);
        PrivateKey privateKey2 = keyPairGenerateKeyPair.getPrivate();
        Intrinsics.checkNotNullExpressionValue(privateKey2, "getPrivate(...)");
        String string8 = jSONObject.getString("store_id");
        Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
        syncDeviceManagementActivity.completeEnrollmentResponse(syncDeviceManagementActivity.decryptPairingResponse(str7, privateKey2, string8, strLocalDeviceId, string7), syncDeviceManagementActivity.pairingRelayHttps());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createSecondaryRequest$lambda$76$lambda$75$lambda$73(SyncDeviceManagementActivity syncDeviceManagementActivity) {
        TextView textView = syncDeviceManagementActivity.status;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_STATUS);
            textView = null;
        }
        textView.setText("\u0623\u064f\u0631\u0633\u0644 \u0637\u0644\u0628 \u0647\u0630\u0627 \u0627\u0644\u062c\u0647\u0627\u0632 \u0628\u0623\u0645\u0627\u0646. \u0646\u0646\u062a\u0638\u0631 \u0645\u0648\u0627\u0641\u0642\u0629 \u0627\u0644\u0647\u0627\u062a\u0641 \u0627\u0644\u0631\u0626\u064a\u0633\u064a...");
    }

    static /* synthetic */ void enrollSecondaryFromRequest$default(SyncDeviceManagementActivity syncDeviceManagementActivity, String str, SyncPairingRelayClient syncPairingRelayClient, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            syncPairingRelayClient = null;
        }
        syncDeviceManagementActivity.enrollSecondaryFromRequest(str, syncPairingRelayClient);
    }

    private final void enrollSecondaryFromRequest(final String sharedRequest, final SyncPairingRelayClient pairingClient) {
        final Function1 function1 = new Function1() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda55
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SyncDeviceManagementActivity.enrollSecondaryFromRequest$lambda$85(this.f$0, pairingClient, (String) obj);
            }
        };
        if (sharedRequest != null) {
            new AlertDialog.Builder(this).setTitle("\u0637\u0644\u0628 \u0631\u0628\u0637 \u062c\u0647\u0627\u0632 \u062c\u062f\u064a\u062f").setMessage("\u0627\u0633\u062a\u0644\u0645 \u0627\u0644\u0647\u0627\u062a\u0641 \u0627\u0644\u0631\u0626\u064a\u0633\u064a \u0637\u0644\u0628 \u0627\u0642\u062a\u0631\u0627\u0646. \u0628\u0639\u062f \u0627\u0644\u062a\u062d\u0642\u0642 \u0645\u0646 \u062a\u0648\u0642\u064a\u0639 \u0627\u0644\u062c\u0647\u0627\u0632\u060c \u0633\u064a\u0635\u062f\u0631 \u0627\u0644\u062a\u0637\u0628\u064a\u0642 \u0634\u0647\u0627\u062f\u0629 \u0644\u0647\u0630\u0627 \u0627\u0644\u0647\u0627\u062a\u0641.").setNegativeButton("\u0631\u0641\u0636", (DialogInterface.OnClickListener) null).setPositiveButton("\u0627\u0644\u062a\u062d\u0642\u0642 \u0648\u0625\u0635\u062f\u0627\u0631 \u0627\u0644\u0634\u0647\u0627\u062f\u0629", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda56
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    function1.invoke(sharedRequest);
                }
            }).show();
        } else {
            prompt("\u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a", "\u0627\u0644\u0635\u0642 \u0637\u0644\u0628 \u0627\u0644\u0627\u0642\u062a\u0631\u0627\u0646 \u0627\u0644\u0642\u0627\u062f\u0645 \u0645\u0646 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a", true, new Function1() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SyncDeviceManagementActivity.enrollSecondaryFromRequest$lambda$87(function1, (String) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit enrollSecondaryFromRequest$lambda$85(final SyncDeviceManagementActivity syncDeviceManagementActivity, final SyncPairingRelayClient syncPairingRelayClient, final String raw) {
        Intrinsics.checkNotNullParameter(raw, "raw");
        runTask$default(syncDeviceManagementActivity, "\u0625\u0635\u062f\u0627\u0631 \u0634\u0647\u0627\u062f\u0629 \u0627\u0644\u062c\u0647\u0627\u0632", false, new Function0() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda50
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SyncDeviceManagementActivity.enrollSecondaryFromRequest$lambda$85$lambda$84(raw, syncDeviceManagementActivity, syncPairingRelayClient);
            }
        }, 2, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit enrollSecondaryFromRequest$lambda$85$lambda$84(String str, final SyncDeviceManagementActivity syncDeviceManagementActivity, SyncPairingRelayClient syncPairingRelayClient) throws Throwable {
        SyncProtocolRepository syncProtocolRepository;
        final JSONObject jSONObjectPut;
        JSONObject jSONObject = new JSONObject(str);
        if (jSONObject.getInt("version") != 1) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        String string = jSONObject.getString("store_id");
        SyncProtocolRepository syncProtocolRepository2 = syncDeviceManagementActivity.repository;
        if (syncProtocolRepository2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("repository");
            syncProtocolRepository2 = null;
        }
        if (!Intrinsics.areEqual(string, syncProtocolRepository2.storeId())) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        String string2 = jSONObject.getString("primary_device_id");
        SyncProtocolRepository syncProtocolRepository3 = syncDeviceManagementActivity.repository;
        if (syncProtocolRepository3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("repository");
            syncProtocolRepository3 = null;
        }
        if (!Intrinsics.areEqual(string2, syncProtocolRepository3.localDeviceId())) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        String strOptString = jSONObject.optString(EXTRA_ACTOR_USER_ID);
        Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
        if (StringsKt.isBlank(strOptString) && syncPairingRelayClient == null) {
            throw new IllegalArgumentException("\u0631\u0628\u0637 \u0647\u0627\u062a\u0641 \u0628\u0644\u0627 \u062d\u0633\u0627\u0628 \u0645\u062d\u0644\u064a \u064a\u062a\u0637\u0644\u0628 \u0641\u062a\u062d \u0627\u0644\u062f\u0639\u0648\u0629 \u0645\u0628\u0627\u0634\u0631\u0629 \u0639\u0644\u0649 \u0627\u0644\u0647\u0627\u062a\u0641 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u0648\u0627\u0644\u0627\u062a\u0635\u0627\u0644 \u0628\u0648\u0633\u064a\u0637 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629".toString());
        }
        String string3 = jSONObject.getString("public_key");
        JSONObject jSONObjectPut2 = new JSONObject().put("version", jSONObject.getInt("version")).put("store_id", jSONObject.getString("store_id")).put("primary_device_id", jSONObject.getString("primary_device_id")).put("invitation_token", jSONObject.getString("invitation_token")).put("device_id", jSONObject.getString("device_id")).put("display_name", jSONObject.getString("display_name")).put("public_key", string3);
        if (jSONObject.has("response_token")) {
            jSONObjectPut2.put("response_token", jSONObject.getString("response_token"));
        }
        if (jSONObject.has("response_encryption_public_key")) {
            jSONObjectPut2.put("response_encryption_public_key", jSONObject.getString("response_encryption_public_key"));
        }
        if (jSONObject.has(EXTRA_ACTOR_USER_ID)) {
            jSONObjectPut2.put(EXTRA_ACTOR_USER_ID, jSONObject.getString(EXTRA_ACTOR_USER_ID));
        }
        SyncDeviceAuthenticator syncDeviceAuthenticator = SyncDeviceAuthenticator.INSTANCE;
        byte[] bArrDecode = Base64.decode(string3, 0);
        Intrinsics.checkNotNullExpressionValue(bArrDecode, "decode(...)");
        String string4 = jSONObjectPut2.toString();
        Intrinsics.checkNotNullExpressionValue(string4, "toString(...)");
        byte[] bytes = string4.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrDecode2 = Base64.decode(jSONObject.getString("request_signature"), 0);
        Intrinsics.checkNotNullExpressionValue(bArrDecode2, "decode(...)");
        if (!syncDeviceAuthenticator.verifyBytes(bArrDecode, bytes, bArrDecode2)) {
            throw new IllegalArgumentException("\u0637\u0644\u0628 \u0627\u0644\u0627\u0642\u062a\u0631\u0627\u0646 \u0644\u0627 \u064a\u062b\u0628\u062a \u0627\u0645\u062a\u0644\u0627\u0643 \u0627\u0644\u0645\u0641\u062a\u0627\u062d \u0627\u0644\u062e\u0627\u0635 \u0644\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a".toString());
        }
        SyncProtocolRepository syncProtocolRepository4 = syncDeviceManagementActivity.repository;
        if (syncProtocolRepository4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("repository");
            syncProtocolRepository = null;
        } else {
            syncProtocolRepository = syncProtocolRepository4;
        }
        String str2 = syncDeviceManagementActivity.actorUserId;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("actorUserId");
            str2 = null;
        }
        String string5 = jSONObject.getString("invitation_token");
        Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
        String string6 = jSONObject.getString("device_id");
        Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
        String string7 = jSONObject.getString("display_name");
        Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
        Intrinsics.checkNotNull(string3);
        String strOptString2 = jSONObject.optString(EXTRA_ACTOR_USER_ID);
        Intrinsics.checkNotNull(strOptString2);
        SyncEnrollment syncEnrollmentEnrollSecondaryDevice = syncProtocolRepository.enrollSecondaryDevice(str2, string5, string6, string7, string3, !StringsKt.isBlank(strOptString2) ? strOptString2 : null);
        String strOptString3 = jSONObject.optString(EXTRA_ACTOR_USER_ID);
        Intrinsics.checkNotNullExpressionValue(strOptString3, "optString(...)");
        if (StringsKt.isBlank(strOptString3)) {
            AuthRepository authRepository = new AuthRepository(syncDeviceManagementActivity);
            String str3 = syncDeviceManagementActivity.actorUserId;
            if (str3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("actorUserId");
                str3 = null;
            }
            PairingAccountSnapshot pairingAccountSnapshotExportPairingAccount = authRepository.exportPairingAccount(str3);
            jSONObjectPut = new JSONObject().put("id", pairingAccountSnapshotExportPairingAccount.getId()).put("username", pairingAccountSnapshotExportPairingAccount.getUsername()).put("display_name", pairingAccountSnapshotExportPairingAccount.getDisplayName()).put("password_hash", pairingAccountSnapshotExportPairingAccount.getPasswordHash()).put("password_salt", pairingAccountSnapshotExportPairingAccount.getPasswordSalt()).put("role_key", pairingAccountSnapshotExportPairingAccount.getRoleKey());
        } else {
            jSONObjectPut = null;
        }
        JSONObject jSONObjectPut3 = new JSONObject().put("version", 1).put("store_id", syncEnrollmentEnrollSecondaryDevice.getStoreId()).put("primary_device_id", syncEnrollmentEnrollSecondaryDevice.getPrimaryDeviceId()).put("device_id", syncEnrollmentEnrollSecondaryDevice.getDeviceId()).put("credential", syncEnrollmentEnrollSecondaryDevice.getCredential()).put("display_name", jSONObject.getString("display_name")).put("store_time_zone_id", syncEnrollmentEnrollSecondaryDevice.getStoreTimeZoneId()).put("device_certificate_json", syncEnrollmentEnrollSecondaryDevice.getDeviceCertificateJson());
        if (jSONObjectPut != null) {
            jSONObjectPut3.put("paired_user", jSONObjectPut);
        }
        SyncDeviceAuthenticator syncDeviceAuthenticator2 = SyncDeviceAuthenticator.INSTANCE;
        SyncDeviceManagementActivity syncDeviceManagementActivity2 = syncDeviceManagementActivity;
        SyncProtocolRepository syncProtocolRepository5 = syncDeviceManagementActivity.repository;
        if (syncProtocolRepository5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("repository");
            syncProtocolRepository5 = null;
        }
        KeyPair orCreate = syncDeviceAuthenticator2.getOrCreate(syncDeviceManagementActivity2, syncProtocolRepository5.localDeviceId());
        SyncDeviceAuthenticator syncDeviceAuthenticator3 = SyncDeviceAuthenticator.INSTANCE;
        PrivateKey privateKey = orCreate.getPrivate();
        Intrinsics.checkNotNullExpressionValue(privateKey, "getPrivate(...)");
        String string8 = jSONObjectPut3.toString();
        Intrinsics.checkNotNullExpressionValue(string8, "toString(...)");
        byte[] bytes2 = string8.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes2, "getBytes(...)");
        final String string9 = new JSONObject(jSONObjectPut3.toString()).put("primary_signature", Base64.encodeToString(syncDeviceAuthenticator3.signBytes(privateKey, bytes2), 2)).toString();
        Intrinsics.checkNotNullExpressionValue(string9, "toString(...)");
        if (syncPairingRelayClient != null) {
            try {
                String strEncryptPairingResponse = syncDeviceManagementActivity.encryptPairingResponse(string9, jSONObject);
                String storeId = syncEnrollmentEnrollSecondaryDevice.getStoreId();
                String string10 = jSONObject.getString("invitation_token");
                Intrinsics.checkNotNullExpressionValue(string10, "getString(...)");
                String deviceId = syncEnrollmentEnrollSecondaryDevice.getDeviceId();
                String string11 = jSONObject.getString("response_token");
                Intrinsics.checkNotNullExpressionValue(string11, "getString(...)");
                syncPairingRelayClient.publishResponse(storeId, string10, deviceId, string11, strEncryptPairingResponse);
                syncDeviceManagementActivity.getPreferences().edit().remove(KEY_PENDING_INVITATION).remove(KEY_PENDING_STORE).apply();
                syncDeviceManagementActivity.main.post(new Runnable() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda41
                    @Override // java.lang.Runnable
                    public final void run() {
                        SyncDeviceManagementActivity.enrollSecondaryFromRequest$lambda$85$lambda$84$lambda$81(this.f$0);
                    }
                });
            } catch (Exception e) {
                syncDeviceManagementActivity.main.post(new Runnable() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda42
                    @Override // java.lang.Runnable
                    public final void run() {
                        SyncDeviceManagementActivity.enrollSecondaryFromRequest$lambda$85$lambda$84$lambda$82(jSONObjectPut, syncDeviceManagementActivity, string9, e);
                    }
                });
            }
        } else {
            syncDeviceManagementActivity.main.post(new Runnable() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda43
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.copyText("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0642\u062a\u0631\u0627\u0646 \u0633\u0631\u064a\u0629 \u2014 \u0627\u0646\u0642\u0644\u0647\u0627 \u0645\u0628\u0627\u0634\u0631\u0629 \u0644\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0642\u0635\u0648\u062f", string9);
                }
            });
        }
        SyncRelaySession.INSTANCE.refreshDeviceRegistry();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void enrollSecondaryFromRequest$lambda$85$lambda$84$lambda$81(SyncDeviceManagementActivity syncDeviceManagementActivity) {
        TextView textView = syncDeviceManagementActivity.status;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_STATUS);
            textView = null;
        }
        textView.setText("\u0627\u0639\u062a\u0645\u062f \u0627\u0644\u0647\u0627\u062a\u0641 \u0627\u0644\u0631\u0626\u064a\u0633\u064a \u0627\u0644\u062c\u0647\u0627\u0632 \u0648\u0623\u0631\u0633\u0644 \u0627\u0644\u0634\u0647\u0627\u062f\u0629 \u0648\u0627\u0644\u0627\u0639\u062a\u0645\u0627\u062f \u0639\u0628\u0631 \u0627\u0644\u0648\u0633\u064a\u0637. \u064a\u0643\u0645\u0644 \u0627\u0644\u0647\u0627\u062a\u0641 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u0627\u0644\u0625\u0639\u062f\u0627\u062f \u062a\u0644\u0642\u0627\u0626\u064a\u0627\u064b.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void enrollSecondaryFromRequest$lambda$85$lambda$84$lambda$82(JSONObject jSONObject, SyncDeviceManagementActivity syncDeviceManagementActivity, String str, Exception exc) {
        TextView textView = null;
        if (jSONObject == null) {
            syncDeviceManagementActivity.copyText("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0642\u062a\u0631\u0627\u0646 \u0633\u0631\u064a\u0629 \u2014 \u0627\u0646\u0642\u0644\u0647\u0627 \u0645\u0628\u0627\u0634\u0631\u0629 \u0644\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0642\u0635\u0648\u062f", str);
        } else {
            TextView textView2 = syncDeviceManagementActivity.status;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_STATUS);
                textView2 = null;
            }
            textView2.setText("\u062a\u0639\u0630\u0631 \u062a\u0633\u0644\u064a\u0645 \u0628\u064a\u0627\u0646\u0627\u062a \u0628\u062f\u0621 \u0627\u0644\u062f\u062e\u0648\u0644 \u0627\u0644\u0645\u0634\u0641\u0631\u0629 \u0639\u0628\u0631 \u0627\u0644\u0648\u0633\u064a\u0637\u061b \u0644\u0627 \u062a\u0634\u0627\u0631\u0643 \u0631\u062f\u0627\u064b \u063a\u064a\u0631 \u0645\u0634\u0641\u0631.");
        }
        TextView textView3 = syncDeviceManagementActivity.status;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_STATUS);
        } else {
            textView = textView3;
        }
        String message = exc.getMessage();
        if (message == null) {
            message = "\u062e\u0637\u0623 \u0627\u062a\u0635\u0627\u0644";
        }
        textView.setText("\u062a\u0645 \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u062c\u0647\u0627\u0632\u060c \u0644\u0643\u0646 \u062a\u0639\u0630\u0631 \u062a\u0633\u0644\u064a\u0645 \u0627\u0644\u0631\u062f \u0639\u0628\u0631 \u0627\u0644\u0648\u0633\u064a\u0637: " + message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit enrollSecondaryFromRequest$lambda$87(Function1 function1, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        function1.invoke(it);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x007b  */
    private final void addEnrollmentResponseForm(LinearLayout parent) {
        String strReplaceFirst;
        SyncDeviceManagementActivity syncDeviceManagementActivity = this;
        LinearLayout linearLayout = new LinearLayout(syncDeviceManagementActivity);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(0, 18, 0, 18);
        TextView textView = new TextView(syncDeviceManagementActivity);
        textView.setText("\u0627\u0644\u062e\u0637\u0648\u0629 \u0627\u0644\u0623\u062e\u064a\u0631\u0629 \u2014 \u0627\u0644\u0635\u0642 \u0647\u0646\u0627 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0627\u0642\u062a\u0631\u0627\u0646 \u0627\u0644\u062a\u064a \u0646\u0633\u062e\u062a\u0647\u0627 \u0645\u0646 \u0627\u0644\u0647\u0627\u062a\u0641 \u0627\u0644\u0631\u0626\u064a\u0633\u064a");
        textView.setTextSize(16.0f);
        textView.setGravity(5);
        textView.setPadding(0, 0, 0, 10);
        textView.setTextColor(-15185097);
        linearLayout.addView(textView, match());
        final EditText editText = new EditText(syncDeviceManagementActivity);
        editText.setHint("\u0627\u0644\u0635\u0642 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0627\u0642\u062a\u0631\u0627\u0646 \u0627\u0644\u0633\u0631\u064a\u0629 \u0643\u0627\u0645\u0644\u0629 \u0647\u0646\u0627");
        editText.setMinLines(5);
        editText.setSingleLine(false);
        editText.setInputType(655361);
        editText.setGravity(53);
        String string = getPreferences().getString(KEY_RELAY_WSS, null);
        if (string != null) {
            strReplaceFirst = new Regex("^wss://", RegexOption.IGNORE_CASE).replaceFirst(string, "https://");
            if (strReplaceFirst == null) {
                strReplaceFirst = DEFAULT_RELAY_HTTPS;
            }
        } else {
            strReplaceFirst = DEFAULT_RELAY_HTTPS;
        }
        final EditText editText2 = new EditText(syncDeviceManagementActivity);
        editText2.setHint("\u0639\u0646\u0648\u0627\u0646 HTTPS \u0644\u0644\u0648\u0633\u064a\u0637");
        editText2.setSingleLine(true);
        editText2.setText(strReplaceFirst);
        linearLayout.addView(editText, match());
        linearLayout.addView(editText2, match());
        addButton(linearLayout, "\u062a\u062d\u0642\u0642 \u0648\u0627\u062d\u0641\u0638 \u0627\u0644\u0627\u0642\u062a\u0631\u0627\u0646 \u0639\u0644\u0649 \u0647\u0630\u0627 \u0627\u0644\u0647\u0627\u062a\u0641", new Function0() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SyncDeviceManagementActivity.addEnrollmentResponseForm$lambda$92(editText, editText2, this);
            }
        });
        parent.addView(linearLayout, match());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addEnrollmentResponseForm$lambda$92(EditText editText, EditText editText2, SyncDeviceManagementActivity syncDeviceManagementActivity) {
        String string = StringsKt.trim((CharSequence) editText.getText().toString()).toString();
        String string2 = StringsKt.trim((CharSequence) editText2.getText().toString()).toString();
        if (StringsKt.isBlank(string)) {
            editText.setError("\u0627\u0644\u0635\u0642 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0627\u0642\u062a\u0631\u0627\u0646 \u0627\u0644\u062a\u064a \u0635\u062f\u0631\u062a \u0645\u0646 \u0627\u0644\u0647\u0627\u062a\u0641 \u0627\u0644\u0631\u0626\u064a\u0633\u064a");
        } else if (StringsKt.isBlank(string2)) {
            editText2.setError("\u0639\u0646\u0648\u0627\u0646 \u0627\u0644\u0648\u0633\u064a\u0637 \u0645\u0637\u0644\u0648\u0628");
        } else {
            syncDeviceManagementActivity.completeEnrollmentResponse(string, string2);
        }
        return Unit.INSTANCE;
    }

    private final void completeEnrollmentResponse(final String responseText, final String https) {
        runTask("\u0627\u0644\u062a\u062d\u0642\u0642 \u0645\u0646 \u0627\u0644\u0634\u0647\u0627\u062f\u0629 \u0648\u062d\u0641\u0638 \u0627\u0644\u0627\u0642\u062a\u0631\u0627\u0646", this.bootstrapMode, new Function0() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda34
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SyncDeviceManagementActivity.completeEnrollmentResponse$lambda$98(https, responseText, this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit completeEnrollmentResponse$lambda$98(String str, String str2, SyncDeviceManagementActivity syncDeviceManagementActivity) throws Throwable {
        String str3;
        if (!StringsKt.startsWith(str, "https://", true)) {
            throw new IllegalArgumentException("\u0623\u062f\u062e\u0644 \u0639\u0646\u0648\u0627\u0646 \u0627\u0644\u0648\u0633\u064a\u0637 \u0628\u0635\u064a\u063a\u0629 HTTPS".toString());
        }
        JSONObject jSONObject = new JSONObject(str2);
        if (jSONObject.getInt("version") != 1) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        String string = jSONObject.getString("device_id");
        SyncProtocolRepository syncProtocolRepository = syncDeviceManagementActivity.repository;
        if (syncProtocolRepository == null) {
            Intrinsics.throwUninitializedPropertyAccessException("repository");
            syncProtocolRepository = null;
        }
        if (!Intrinsics.areEqual(string, syncProtocolRepository.localDeviceId())) {
            throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0627\u0642\u062a\u0631\u0627\u0646 \u062a\u062e\u0635 \u062c\u0647\u0627\u0632\u0627\u064b \u0622\u062e\u0631\u061b \u0627\u0633\u062a\u062e\u062f\u0645 \u0627\u0644\u0631\u062f \u0627\u0644\u0630\u064a \u0635\u062f\u0631 \u0644\u0647\u0630\u0627 \u0627\u0644\u0647\u0627\u062a\u0641 \u062a\u062d\u062f\u064a\u062f\u0627\u064b".toString());
        }
        SyncRelayRegistryClient syncRelayRegistryClient = new SyncRelayRegistryClient(str, null, 2, null);
        String string2 = jSONObject.getString("store_id");
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        SyncRelayRegistryClient.TrustRoot trustRootFetchTrustRoot = syncRelayRegistryClient.fetchTrustRoot(string2);
        if (!Intrinsics.areEqual(trustRootFetchTrustRoot.getDeviceId(), jSONObject.getString("primary_device_id"))) {
            throw new IllegalArgumentException("\u0647\u0648\u064a\u0629 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0631\u0626\u064a\u0633\u064a \u0644\u0627 \u062a\u0637\u0627\u0628\u0642 \u062c\u0630\u0631 \u0627\u0644\u062b\u0642\u0629 \u0627\u0644\u0645\u0646\u0634\u0648\u0631 \u0644\u062f\u0649 \u0627\u0644\u0648\u0633\u064a\u0637".toString());
        }
        JSONObject jSONObjectPut = new JSONObject().put("version", jSONObject.getInt("version")).put("store_id", jSONObject.getString("store_id")).put("primary_device_id", jSONObject.getString("primary_device_id")).put("device_id", jSONObject.getString("device_id")).put("credential", jSONObject.getString("credential")).put("display_name", jSONObject.getString("display_name")).put("store_time_zone_id", jSONObject.optString("store_time_zone_id", StoreTime.DEFAULT_ZONE_ID)).put("device_certificate_json", jSONObject.getString("device_certificate_json"));
        if (jSONObject.has("paired_user")) {
            jSONObjectPut.put("paired_user", jSONObject.getJSONObject("paired_user"));
        }
        SyncDeviceAuthenticator syncDeviceAuthenticator = SyncDeviceAuthenticator.INSTANCE;
        byte[] bArrDecode = Base64.decode(trustRootFetchTrustRoot.getPublicKeyBase64(), 0);
        Intrinsics.checkNotNullExpressionValue(bArrDecode, "decode(...)");
        String string3 = jSONObjectPut.toString();
        Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
        byte[] bytes = string3.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrDecode2 = Base64.decode(jSONObject.getString("primary_signature"), 0);
        Intrinsics.checkNotNullExpressionValue(bArrDecode2, "decode(...)");
        if (!syncDeviceAuthenticator.verifyBytes(bArrDecode, bytes, bArrDecode2)) {
            throw new IllegalArgumentException("\u062a\u0648\u0642\u064a\u0639 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0631\u0626\u064a\u0633\u064a \u0639\u0644\u0649 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0627\u0642\u062a\u0631\u0627\u0646 \u063a\u064a\u0631 \u0635\u062d\u064a\u062d".toString());
        }
        String str4 = syncDeviceManagementActivity.actorUserId;
        if (str4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("actorUserId");
            str4 = null;
        }
        if (StringsKt.isBlank(str4)) {
            if (!jSONObject.has("paired_user")) {
                throw new IllegalArgumentException("\u0644\u0645 \u062a\u0635\u0644 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0648\u062b\u0642 \u0645\u0646 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0631\u0626\u064a\u0633\u064a".toString());
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject("paired_user");
            AuthRepository authRepository = new AuthRepository(syncDeviceManagementActivity);
            String string4 = jSONObject2.getString("id");
            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
            String string5 = jSONObject2.getString("username");
            Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
            String string6 = jSONObject2.getString("display_name");
            Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
            String string7 = jSONObject2.getString("password_hash");
            Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
            String string8 = jSONObject2.getString("password_salt");
            Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
            String string9 = jSONObject2.getString("role_key");
            Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
            syncDeviceManagementActivity.actorUserId = authRepository.installPairedAccount(new PairingAccountSnapshot(string4, string5, string6, string7, string8, string9));
        }
        SyncProtocolRepository syncProtocolRepository2 = syncDeviceManagementActivity.repository;
        if (syncProtocolRepository2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("repository");
            syncProtocolRepository2 = null;
        }
        String str5 = syncDeviceManagementActivity.actorUserId;
        if (str5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("actorUserId");
            str5 = null;
        }
        String string10 = jSONObject.getString("store_id");
        Intrinsics.checkNotNullExpressionValue(string10, "getString(...)");
        String string11 = jSONObject.getString("primary_device_id");
        Intrinsics.checkNotNullExpressionValue(string11, "getString(...)");
        String string12 = jSONObject.getString("credential");
        Intrinsics.checkNotNullExpressionValue(string12, "getString(...)");
        String string13 = jSONObject.getString("display_name");
        Intrinsics.checkNotNullExpressionValue(string13, "getString(...)");
        String string14 = jSONObject.getString("device_certificate_json");
        Intrinsics.checkNotNullExpressionValue(string14, "getString(...)");
        String strOptString = jSONObject.optString("store_time_zone_id", StoreTime.DEFAULT_ZONE_ID);
        Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
        syncProtocolRepository2.configureAsSecondaryFromRelay(str, str5, string10, string11, string12, string13, string14, strOptString);
        String strReplaceFirst = new Regex("^https://", RegexOption.IGNORE_CASE).replaceFirst(str, "wss://");
        if (!StringsKt.startsWith$default(strReplaceFirst, "wss://", false, 2, (Object) null)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        syncDeviceManagementActivity.getPreferences().edit().putString(KEY_RELAY_WSS, strReplaceFirst).apply();
        SyncDeviceManagementActivity syncDeviceManagementActivity2 = syncDeviceManagementActivity;
        SyncForegroundService.INSTANCE.start(syncDeviceManagementActivity2);
        if (syncDeviceManagementActivity.bootstrapMode) {
            SecureSessionStore secureSessionStore = SecureSessionStore.INSTANCE;
            String str6 = syncDeviceManagementActivity.actorUserId;
            if (str6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("actorUserId");
                str3 = null;
            } else {
                str3 = str6;
            }
            secureSessionStore.save(syncDeviceManagementActivity2, str3);
        }
        return Unit.INSTANCE;
    }

    private final OAEPParameterSpec pairingOaepSpec() {
        return new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT);
    }

    private final byte[] pairingAad(String storeId, String deviceId, String invitationToken) {
        byte[] bytes = ("THE_DAILY_SHOP_PAIRING_RESPONSE_V1:" + storeId + ":" + deviceId + ":" + invitationToken).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        return bytes;
    }

    private final String encryptPairingResponse(String responseJson, JSONObject request) throws JSONException, BadPaddingException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        String string = request.getString("store_id");
        String string2 = request.getString("device_id");
        String string3 = request.getString("invitation_token");
        PublicKey publicKeyGeneratePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(request.getString("response_encryption_public_key"), 0)));
        byte[] bArr = new byte[32];
        new SecureRandom().nextBytes(bArr);
        byte[] bArr2 = new byte[12];
        new SecureRandom().nextBytes(bArr2);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(1, new SecretKeySpec(bArr, "AES"), new GCMParameterSpec(128, bArr2));
        Intrinsics.checkNotNull(string);
        Intrinsics.checkNotNull(string2);
        Intrinsics.checkNotNull(string3);
        cipher.updateAAD(pairingAad(string, string2, string3));
        byte[] bytes = responseJson.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrDoFinal = cipher.doFinal(bytes);
        Cipher cipher2 = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher2.init(1, publicKeyGeneratePublic, pairingOaepSpec());
        String string4 = new JSONObject().put("version", 1).put("store_id", string).put("device_id", string2).put("encrypted_key", Base64.encodeToString(cipher2.doFinal(bArr), 2)).put("iv", Base64.encodeToString(bArr2, 2)).put("ciphertext", Base64.encodeToString(bArrDoFinal, 2)).toString();
        Intrinsics.checkNotNullExpressionValue(string4, "toString(...)");
        return string4;
    }

    private final String decryptPairingResponse(String envelopeJson, PrivateKey privateKey, String expectedStoreId, String expectedDeviceId, String invitationToken) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        JSONObject jSONObject = new JSONObject(envelopeJson);
        if (jSONObject.getInt("version") != 1) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!Intrinsics.areEqual(jSONObject.getString("store_id"), expectedStoreId)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!Intrinsics.areEqual(jSONObject.getString("device_id"), expectedDeviceId)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(2, privateKey, pairingOaepSpec());
        byte[] bArrDoFinal = cipher.doFinal(Base64.decode(jSONObject.getString("encrypted_key"), 0));
        if (bArrDoFinal.length != 32) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        Cipher cipher2 = Cipher.getInstance("AES/GCM/NoPadding");
        cipher2.init(2, new SecretKeySpec(bArrDoFinal, "AES"), new GCMParameterSpec(128, Base64.decode(jSONObject.getString("iv"), 0)));
        cipher2.updateAAD(pairingAad(expectedStoreId, expectedDeviceId, invitationToken));
        byte[] bArrDoFinal2 = cipher2.doFinal(Base64.decode(jSONObject.getString("ciphertext"), 0));
        Intrinsics.checkNotNullExpressionValue(bArrDoFinal2, "doFinal(...)");
        return new String(bArrDoFinal2, Charsets.UTF_8);
    }

    private final void revokeSecondary() {
        runTask$default(this, "\u062a\u062d\u0645\u064a\u0644 \u0627\u0644\u0623\u062c\u0647\u0632\u0629 \u0627\u0644\u062b\u0627\u0646\u0648\u064a\u0629 \u0627\u0644\u0645\u0633\u062c\u0644\u0629", false, new Function0() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda54
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SyncDeviceManagementActivity.revokeSecondary$lambda$106(this.f$0);
            }
        }, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit revokeSecondary$lambda$106(final SyncDeviceManagementActivity syncDeviceManagementActivity) throws IOException {
        SyncProtocolRepository syncProtocolRepository = syncDeviceManagementActivity.repository;
        if (syncProtocolRepository == null) {
            Intrinsics.throwUninitializedPropertyAccessException("repository");
            syncProtocolRepository = null;
        }
        final List<String> listActiveSecondaryDeviceIds = syncProtocolRepository.activeSecondaryDeviceIds();
        syncDeviceManagementActivity.main.post(new Runnable() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda33
            @Override // java.lang.Runnable
            public final void run() {
                SyncDeviceManagementActivity.revokeSecondary$lambda$106$lambda$105(listActiveSecondaryDeviceIds, syncDeviceManagementActivity);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void revokeSecondary$lambda$106$lambda$105(final List list, final SyncDeviceManagementActivity syncDeviceManagementActivity) {
        TextView textView = null;
        if (list.isEmpty()) {
            TextView textView2 = syncDeviceManagementActivity.status;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_STATUS);
            } else {
                textView = textView2;
            }
            textView.setText("\u0644\u0627 \u062a\u0648\u062c\u062f \u0623\u062c\u0647\u0632\u0629 \u062b\u0627\u0646\u0648\u064a\u0629 \u0646\u0634\u0637\u0629 \u0644\u0625\u0644\u063a\u0627\u0621 \u0635\u0644\u0627\u062d\u064a\u062a\u0647\u0627.");
            return;
        }
        new AlertDialog.Builder(syncDeviceManagementActivity).setTitle("\u0627\u062e\u062a\u0631 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0631\u0627\u062f \u0625\u0644\u063a\u0627\u0621 \u0634\u0647\u0627\u062f\u062a\u0647").setItems((CharSequence[]) list.toArray(new String[0]), new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda48
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SyncDeviceManagementActivity.revokeSecondary$lambda$106$lambda$105$lambda$104(list, syncDeviceManagementActivity, dialogInterface, i);
            }
        }).setNegativeButton("\u0625\u063a\u0644\u0627\u0642", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void revokeSecondary$lambda$106$lambda$105$lambda$104(List list, final SyncDeviceManagementActivity syncDeviceManagementActivity, DialogInterface dialogInterface, int i) {
        final String str = (String) list.get(i);
        new AlertDialog.Builder(syncDeviceManagementActivity).setTitle("\u062a\u0623\u0643\u064a\u062f \u0625\u0644\u063a\u0627\u0621 \u0627\u0644\u0634\u0647\u0627\u062f\u0629").setMessage("\u0633\u064a\u064f\u0645\u0646\u0639 \u0647\u0630\u0627 \u0627\u0644\u062c\u0647\u0627\u0632 \u0645\u0646 \u0627\u0644\u0627\u062a\u0635\u0627\u0644 \u0628\u0639\u062f \u0648\u0635\u0648\u0644 \u0627\u0644\u0625\u0644\u063a\u0627\u0621 \u0625\u0644\u0649 \u0627\u0644\u0648\u0633\u064a\u0637.\n\n" + str).setPositiveButton("\u0625\u0644\u063a\u0627\u0621 \u0627\u0644\u0634\u0647\u0627\u062f\u0629", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda31
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface2, int i2) {
                SyncDeviceManagementActivity.revokeSecondary$lambda$106$lambda$105$lambda$104$lambda$103(this.f$0, str, dialogInterface2, i2);
            }
        }).setNegativeButton("\u062a\u0631\u0627\u062c\u0639", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void revokeSecondary$lambda$106$lambda$105$lambda$104$lambda$103(final SyncDeviceManagementActivity syncDeviceManagementActivity, final String str, DialogInterface dialogInterface, int i) {
        runTask$default(syncDeviceManagementActivity, "\u0625\u0644\u063a\u0627\u0621 \u0634\u0647\u0627\u062f\u0629 \u0627\u0644\u062c\u0647\u0627\u0632", false, new Function0() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda49
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SyncDeviceManagementActivity.revokeSecondary$lambda$106$lambda$105$lambda$104$lambda$103$lambda$102(this.f$0, str);
            }
        }, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit revokeSecondary$lambda$106$lambda$105$lambda$104$lambda$103$lambda$102(final SyncDeviceManagementActivity syncDeviceManagementActivity, String str) throws Throwable {
        SyncProtocolRepository syncProtocolRepository = syncDeviceManagementActivity.repository;
        String str2 = null;
        if (syncProtocolRepository == null) {
            Intrinsics.throwUninitializedPropertyAccessException("repository");
            syncProtocolRepository = null;
        }
        String str3 = syncDeviceManagementActivity.actorUserId;
        if (str3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("actorUserId");
        } else {
            str2 = str3;
        }
        syncProtocolRepository.revokeDevice(str2, str);
        SyncRelaySession.INSTANCE.refreshDeviceRegistry();
        syncDeviceManagementActivity.main.post(new Runnable() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                SyncDeviceManagementActivity.revokeSecondary$lambda$106$lambda$105$lambda$104$lambda$103$lambda$102$lambda$101(this.f$0);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void revokeSecondary$lambda$106$lambda$105$lambda$104$lambda$103$lambda$102$lambda$101(SyncDeviceManagementActivity syncDeviceManagementActivity) {
        TextView textView = syncDeviceManagementActivity.status;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_STATUS);
            textView = null;
        }
        textView.setText("\u0623\u064f\u0644\u063a\u064a\u062a \u0635\u0644\u0627\u062d\u064a\u0629 \u0627\u0644\u062c\u0647\u0627\u0632 \u0645\u062d\u0644\u064a\u0627\u064b\u061b \u0623\u064f\u0636\u064a\u0641 \u0628\u064a\u0627\u0646 \u0627\u0644\u0625\u0644\u063a\u0627\u0621 \u0625\u0644\u0649 \u0642\u0627\u0626\u0645\u0629 \u0627\u0644\u0625\u0631\u0633\u0627\u0644 \u0627\u0644\u0645\u0624\u062c\u0644.");
    }

    private final void syncDeviceRegistryNow() {
        TextView textView = null;
        String string = getPreferences().getString(KEY_RELAY_WSS, null);
        String string2 = string != null ? StringsKt.trim((CharSequence) string).toString() : null;
        if (string2 == null) {
            string2 = "";
        }
        if (!StringsKt.startsWith(string2, "wss://", true)) {
            TextView textView2 = this.status;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_STATUS);
            } else {
                textView = textView2;
            }
            textView.setText("\u0627\u062d\u0641\u0638 \u0639\u0646\u0648\u0627\u0646 \u0648\u0633\u064a\u0637 WSS \u0635\u0627\u0644\u062d\u0627\u064b \u0623\u0648\u0644\u0627\u064b.");
            return;
        }
        final String strReplaceFirst = new Regex("^wss://", RegexOption.IGNORE_CASE).replaceFirst(string2, "https://");
        TextView textView3 = this.status;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_STATUS);
        } else {
            textView = textView3;
        }
        textView.setText("\u062c\u0627\u0631\u064d \u0627\u0644\u062a\u062d\u0642\u0642 \u0645\u0646 \u062c\u0630\u0631 \u0627\u0644\u062b\u0642\u0629 \u0648\u062a\u0633\u062c\u064a\u0644 \u0634\u0647\u0627\u062f\u0627\u062a \u0627\u0644\u0623\u062c\u0647\u0632\u0629 \u0627\u0644\u062b\u0627\u0646\u0648\u064a\u0629...");
        this.worker.execute(new Runnable() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda53
            @Override // java.lang.Runnable
            public final void run() {
                SyncDeviceManagementActivity.syncDeviceRegistryNow$lambda$109(this.f$0, strReplaceFirst);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void syncDeviceRegistryNow$lambda$109(final SyncDeviceManagementActivity syncDeviceManagementActivity, String str) {
        try {
            SyncProtocolRepository syncProtocolRepository = syncDeviceManagementActivity.repository;
            if (syncProtocolRepository == null) {
                Intrinsics.throwUninitializedPropertyAccessException("repository");
                syncProtocolRepository = null;
            }
            final int size = syncProtocolRepository.activeSecondaryDeviceIds().size();
            SyncProtocolRepository syncProtocolRepository2 = syncDeviceManagementActivity.repository;
            if (syncProtocolRepository2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("repository");
                syncProtocolRepository2 = null;
            }
            final SyncRelayRegistrySyncResult syncRelayRegistrySyncResultSyncDeviceRegistry$default = SyncProtocolRepository.syncDeviceRegistry$default(syncProtocolRepository2, str, null, 2, null);
            syncDeviceManagementActivity.main.post(new Runnable() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda25
                @Override // java.lang.Runnable
                public final void run() {
                    SyncDeviceManagementActivity.syncDeviceRegistryNow$lambda$109$lambda$107(this.f$0, size, syncRelayRegistrySyncResultSyncDeviceRegistry$default);
                }
            });
        } catch (Exception e) {
            syncDeviceManagementActivity.main.post(new Runnable() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda26
                @Override // java.lang.Runnable
                public final void run() {
                    SyncDeviceManagementActivity.syncDeviceRegistryNow$lambda$109$lambda$108(this.f$0, e);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void syncDeviceRegistryNow$lambda$109$lambda$107(SyncDeviceManagementActivity syncDeviceManagementActivity, int i, SyncRelayRegistrySyncResult syncRelayRegistrySyncResult) {
        TextView textView = syncDeviceManagementActivity.status;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_STATUS);
            textView = null;
        }
        textView.setText("\u0627\u0643\u062a\u0645\u0644 \u062a\u062d\u062f\u064a\u062b \u0633\u062c\u0644 \u0627\u0644\u0648\u0633\u064a\u0637. \u0627\u0644\u0623\u062c\u0647\u0632\u0629 \u0627\u0644\u062b\u0627\u0646\u0648\u064a\u0629 \u0627\u0644\u0646\u0634\u0637\u0629: " + i + ". \u0627\u0644\u0634\u0647\u0627\u062f\u0627\u062a \u0627\u0644\u0645\u0633\u062c\u0644\u0629 \u0623\u0648 \u0627\u0644\u0645\u0624\u0643\u062f\u0629: " + syncRelayRegistrySyncResult.getCertificatesRegistered() + ". \u0627\u0644\u0625\u0644\u063a\u0627\u0621\u0627\u062a \u0627\u0644\u0645\u0631\u0633\u0644\u0629: " + syncRelayRegistrySyncResult.getRevocationsSent() + ".");
        Toast.makeText(syncDeviceManagementActivity, "\u062a\u0645 \u062a\u062d\u062f\u064a\u062b \u0633\u062c\u0644 \u0634\u0647\u0627\u062f\u0627\u062a \u0627\u0644\u0623\u062c\u0647\u0632\u0629", 1).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void syncDeviceRegistryNow$lambda$109$lambda$108(SyncDeviceManagementActivity syncDeviceManagementActivity, Exception exc) {
        TextView textView = syncDeviceManagementActivity.status;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_STATUS);
            textView = null;
        }
        String message = exc.getMessage();
        if (message == null) {
            message = "\u062e\u0637\u0623 \u063a\u064a\u0631 \u0645\u062d\u062f\u062f";
        }
        textView.setText("\u0641\u0634\u0644 \u062a\u062d\u062f\u064a\u062b \u0633\u062c\u0644 \u0634\u0647\u0627\u062f\u0627\u062a \u0627\u0644\u0623\u062c\u0647\u0632\u0629: " + message);
        Toast.makeText(syncDeviceManagementActivity, "\u062a\u0639\u0630\u0631 \u062a\u0633\u062c\u064a\u0644 \u0634\u0647\u0627\u062f\u0627\u062a \u0627\u0644\u0623\u062c\u0647\u0632\u0629", 1).show();
    }

    private final void configureRelay() {
        prompt$default(this, "\u0639\u0646\u0648\u0627\u0646 \u0648\u0633\u064a\u0637 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629", "wss://your-worker.workers.dev", false, new Function1() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SyncDeviceManagementActivity.configureRelay$lambda$112(this.f$0, (String) obj);
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit configureRelay$lambda$112(final SyncDeviceManagementActivity syncDeviceManagementActivity, final String endpoint) {
        Intrinsics.checkNotNullParameter(endpoint, "endpoint");
        runTask$default(syncDeviceManagementActivity, "\u062d\u0641\u0638 \u0639\u0646\u0648\u0627\u0646 \u0627\u0644\u0648\u0633\u064a\u0637 \u0648\u0628\u062f\u0621 \u0627\u0644\u0645\u0635\u0627\u062f\u0642\u0629", false, new Function0() { // from class: com.hazem.dailystore.SyncDeviceManagementActivity$$ExternalSyntheticLambda35
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SyncDeviceManagementActivity.configureRelay$lambda$112$lambda$111(endpoint, syncDeviceManagementActivity);
            }
        }, 2, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit configureRelay$lambda$112$lambda$111(String str, SyncDeviceManagementActivity syncDeviceManagementActivity) {
        if (!StringsKt.startsWith(str, "wss://", true)) {
            throw new IllegalArgumentException("\u064a\u062c\u0628 \u0625\u062f\u062e\u0627\u0644 \u0639\u0646\u0648\u0627\u0646 WSS \u0645\u0634\u0641\u0631".toString());
        }
        syncDeviceManagementActivity.getPreferences().edit().putString(KEY_RELAY_WSS, str).apply();
        SyncForegroundService.INSTANCE.start(syncDeviceManagementActivity);
        return Unit.INSTANCE;
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        this.worker.shutdown();
        super.onDestroy();
    }

    /* JADX INFO: compiled from: SyncDeviceManagementActivity.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/hazem/dailystore/SyncDeviceManagementActivity$Companion;", "", "<init>", "()V", "EXTRA_ACTOR_USER_ID", "", "EXTRA_INCOMING_SHARE", "KEY_RELAY_WSS", "KEY_LAST_PROBE_ID", "KEY_PENDING_INVITATION", "KEY_PENDING_STORE", "DEFAULT_RELAY_HTTPS", "intent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "actorUserId", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Intent intent(Context context, String actorUserId) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
            Intent intentPutExtra = new Intent(context, (Class<?>) SyncDeviceManagementActivity.class).putExtra(SyncDeviceManagementActivity.EXTRA_ACTOR_USER_ID, actorUserId);
            Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
            return intentPutExtra;
        }
    }
}
