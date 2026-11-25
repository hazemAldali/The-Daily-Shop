package com.hazem.dailystore.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Base64;
import androidx.core.app.NotificationCompat;
import com.hazem.dailystore.SyncDeviceManagementActivity;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import net.zetetic.database.sqlcipher.SQLiteDatabase;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: SyncProtocolRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u00ac\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u001c\u0018\u0000 \u0090\u00012\u00020\u0001:\u0002\u0090\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fJ\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\u000fJ:\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u000f2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u000fJH\u0010\u0019\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u000fJ$\u0010 \u001a\u00020\r2\n\u0010!\u001a\u00060\"j\u0002`#2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020\u000fH\u0002J,\u0010%\u001a\u00020\r2\n\u0010!\u001a\u00060\"j\u0002`#2\u0006\u0010&\u001a\u00020\u000f2\u0006\u0010'\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020\u000fH\u0002J\u0010\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010&\u001a\u00020\u000fJ\b\u0010*\u001a\u0004\u0018\u00010)J\u0006\u0010+\u001a\u00020,J\u0006\u0010-\u001a\u00020\u000fJ\u0010\u0010.\u001a\u0004\u0018\u00010\u000f2\u0006\u0010&\u001a\u00020\u000fJ\u0006\u0010/\u001a\u000200J\u0018\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u000f2\b\b\u0002\u00104\u001a\u000205JH\u00106\u001a\u00020\r2\u0006\u00103\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u000f2\b\b\u0002\u0010\u001d\u001a\u00020\u000fJ\u0016\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u000f2\u0006\u0010:\u001a\u00020\u000fJ\u0016\u0010;\u001a\u00020\u000f2\u0006\u00109\u001a\u00020\u000f2\u0006\u0010:\u001a\u00020\u000fJ0\u0010<\u001a\u00020\r2\u0006\u0010=\u001a\u00020>2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010?\u001a\u00020\u000f2\u0006\u0010@\u001a\u00020\u000f2\u0006\u00109\u001a\u00020\u000fH\u0002J\u000e\u0010A\u001a\u0002082\u0006\u0010B\u001a\u00020\u000fJ\u000e\u0010C\u001a\u0002082\u0006\u0010B\u001a\u00020\u000fJ\u000e\u0010D\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010E\u001a\u0004\u0018\u00010\u000f2\u0006\u00109\u001a\u00020\u000fJB\u0010F\u001a\u00020G2\n\u0010!\u001a\u00060\"j\u0002`#2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u00109\u001a\u00020\u000f2\u0006\u0010H\u001a\u00020\u000f2\u0006\u0010I\u001a\u00020\u000f2\u0006\u0010J\u001a\u00020\u000f2\u0006\u0010K\u001a\u00020\u000fJ&\u0010L\u001a\u00020\r2\u0006\u0010M\u001a\u00020\u000f2\u0006\u0010N\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010O\u001a\u00020\u000fJ&\u0010P\u001a\u00020\r2\u0006\u0010M\u001a\u00020\u000f2\u0006\u0010N\u001a\u00020\u000f2\u0006\u0010Q\u001a\u00020\u000f2\u0006\u0010R\u001a\u00020\u000fJ\u0010\u0010S\u001a\u00020\u000f2\u0006\u0010T\u001a\u00020\u000fH\u0002J\u000e\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020\u000fJ\u000e\u0010X\u001a\u0002082\u0006\u0010W\u001a\u00020\u000fJ\u000e\u0010Y\u001a\u0002082\u0006\u0010W\u001a\u00020\u000fJ\u001e\u0010Z\u001a\b\u0012\u0004\u0012\u00020\\0[2\u0006\u0010&\u001a\u00020\u000f2\b\b\u0002\u0010]\u001a\u00020^J\u000e\u0010_\u001a\u00020V2\u0006\u0010W\u001a\u00020\u000fJ\u000e\u0010`\u001a\u00020V2\u0006\u0010W\u001a\u00020\u000fJ\u0016\u0010a\u001a\u00020\r2\u0006\u0010&\u001a\u00020\u000f2\u0006\u00109\u001a\u00020\u000fJ\u0016\u0010b\u001a\u00020\r2\u0006\u0010&\u001a\u00020\u000f2\u0006\u00109\u001a\u00020\u000fJ\u0016\u0010c\u001a\u00020,2\u0006\u0010&\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u000fJ\u0016\u0010d\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u000fJQ\u0010e\u001a\u0004\u0018\u00010G2\n\u0010!\u001a\u00060\"j\u0002`#2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u00109\u001a\u00020\u000f2\u0006\u0010f\u001a\u00020\u000f2\u0006\u0010I\u001a\u00020\u000f2\u0006\u0010J\u001a\u00020\u000f2\u0006\u0010g\u001a\u00020\u000f2\u0006\u0010K\u001a\u00020\u000f\u00a2\u0006\u0002\u0010hJ)\u0010i\u001a\u0004\u0018\u00010G2\n\u0010!\u001a\u00060\"j\u0002`#2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010j\u001a\u00020\u000f\u00a2\u0006\u0002\u0010kJ\u001c\u0010l\u001a\u00020\r2\n\u0010!\u001a\u00060\"j\u0002`#2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J]\u0010m\u001a\u0004\u0018\u00010G2\n\u0010!\u001a\u00060\"j\u0002`#2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u00109\u001a\u00020\u000f2\u0006\u0010n\u001a\u00020\u000f2\u0006\u0010o\u001a\u00020\u000f2\n\b\u0002\u0010p\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010q\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010r\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\u0002\u0010hJ\u0016\u0010s\u001a\u00020\u000f2\u0006\u0010:\u001a\u00020\u000f2\u0006\u00109\u001a\u00020\u000fJ\u000e\u0010t\u001a\u00020u2\u0006\u0010B\u001a\u00020\u000fJ\b\u0010v\u001a\u0004\u0018\u00010\u000fJ\u0006\u0010w\u001a\u00020\u000fJ\b\u0010x\u001a\u0004\u0018\u00010\u000fJ\f\u0010y\u001a\b\u0012\u0004\u0012\u00020\u000f0[J\u001e\u0010z\u001a\b\u0012\u0004\u0012\u00020\\0[2\u0006\u0010&\u001a\u00020\u000f2\b\b\u0002\u0010]\u001a\u00020^J\u0010\u0010{\u001a\u0004\u0018\u00010\u000f2\u0006\u00109\u001a\u00020\u000fJ\u000e\u0010|\u001a\u00020V2\u0006\u0010W\u001a\u00020\u000fJ(\u0010}\u001a\b\u0012\u0004\u0012\u00020\u000f0[2\u0006\u0010&\u001a\u00020\u000f2\b\b\u0002\u0010]\u001a\u00020^2\b\b\u0002\u0010~\u001a\u00020GJ\u000e\u0010\u007f\u001a\u00020,2\u0006\u00109\u001a\u00020\u000fJ\u0006\u0010\u001a\u001a\u00020\u000fJ\u0006\u0010\u001d\u001a\u00020\u000fJ\u0017\u0010\u0080\u0001\u001a\b\u0012\u0004\u0012\u00020\\0[2\b\b\u0002\u0010]\u001a\u00020^J\u0007\u0010\u0081\u0001\u001a\u00020,J\u001d\u0010\u0082\u0001\u001a\u00020\r2\n\u0010!\u001a\u00060\"j\u0002`#2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J \u0010\u0083\u0001\u001a\u0004\u0018\u00010\u000f2\n\u0010!\u001a\u00060\"j\u0002`#2\u0007\u0010\u0084\u0001\u001a\u00020\u000fH\u0002J/\u0010\u0085\u0001\u001a\u00020\r2\n\u0010!\u001a\u00060\"j\u0002`#2\u0007\u0010\u0084\u0001\u001a\u00020\u000f2\u0007\u0010\u0086\u0001\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020\u000fH\u0002JM\u0010\u0087\u0001\u001a\u00020\r2\n\u0010!\u001a\u00060\"j\u0002`#2\u0007\u0010\u0088\u0001\u001a\u00020\u000f2\u0007\u0010\u0089\u0001\u001a\u00020\u000f2\u0006\u0010J\u001a\u00020\u000f2\t\u0010\u008a\u0001\u001a\u0004\u0018\u00010\u000f2\t\u0010\u008b\u0001\u001a\u0004\u0018\u00010\u000f2\u0006\u0010$\u001a\u00020\u000fH\u0002J\t\u0010\u008c\u0001\u001a\u00020\u000fH\u0002J\u0012\u0010\u008d\u0001\u001a\u00020\u000f2\u0007\u0010\u0086\u0001\u001a\u00020\u000fH\u0002J\u0014\u0010\u008e\u0001\u001a\u00020\u000f2\t\b\u0002\u0010\u008f\u0001\u001a\u00020GH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0091\u0001"}, d2 = {"Lcom/hazem/dailystore/data/SyncProtocolRepository;", "", "context", "Landroid/content/Context;", "credentialVault", "Lcom/hazem/dailystore/data/SyncCredentialVault;", "<init>", "(Landroid/content/Context;Lcom/hazem/dailystore/data/SyncCredentialVault;)V", "database", "Lcom/hazem/dailystore/data/AppDatabase;", "random", "Ljava/security/SecureRandom;", "configureAsPrimary", "", "actorUserId", "", "displayName", "createPairingInvitation", "Lcom/hazem/dailystore/data/SyncPairingInvitation;", "enrollSecondaryDevice", "Lcom/hazem/dailystore/data/SyncEnrollment;", "invitationToken", "remoteDeviceId", "remotePublicKeyBase64", "remoteActorUserId", "configureAsSecondary", "storeId", "primaryDeviceId", "credential", "storeTimeZoneId", "primaryPublicKeyBase64", "deviceCertificateJson", "clearUncommittedLocalServiceWalletSeedsForSecondary", "db", "Lnet/zetetic/database/sqlcipher/SQLiteDatabase;", "Lcom/hazem/dailystore/data/AppSqliteDatabase;", "now", "storeDevicePublicKey", "deviceId", "encodedBase64", "trustedPeerPublicKey", "", "secondaryCredential", "isConfiguredSecondary", "", "primaryRelayTrustRootJson", "deviceCertificateForRelay", "relayAuthentication", "Lcom/hazem/dailystore/data/SyncRelayDeviceAuthentication;", "syncDeviceRegistry", "Lcom/hazem/dailystore/data/SyncRelayRegistrySyncResult;", "relayBaseUrl", "registry", "Lcom/hazem/dailystore/data/SyncRelayRegistryClient;", "configureAsSecondaryFromRelay", "authenticatedOutbox", "Lcom/hazem/dailystore/data/AuthenticatedSyncEnvelope;", "operationId", "recipientDeviceId", "encryptedAuthenticatedOutbox", "verifyPeerFrameSignature", "frame", "Lorg/json/JSONObject;", "senderId", "recipientId", "verifyEncryptedIncomingFromSecondary", "frameJson", "verifyEncryptedIncomingFromPrimary", "enqueueTransportProbe", "outboxStatus", "enqueueSecondaryCommand", "", "businessOperation", "entityType", "entityId", "payloadJson", "mapSecondaryActor", "managerUserId", "secondaryDeviceId", "primaryUserId", "mapSecondaryCashAccount", "remoteCashAccountId", "primaryCashAccountId", "requiredPermissionForBusinessOperation", "operation", "recordIncomingFromSecondary", "Lcom/hazem/dailystore/data/SyncInboxDisposition;", "encodedEnvelope", "verifyIncomingFromPrimary", "verifyIncomingFromSecondary", "pendingStoreSettingsForDevice", "", "Lcom/hazem/dailystore/data/SyncOutboxItem;", "limit", "", "recordIncomingStoreSettingsFromPrimary", "recordIncomingExchangeRateFromPrimary", "acknowledgeExchangeRateApplied", "acknowledgeStoreSettingsApplied", "authenticateDevice", "revokeDevice", "enqueueLocalOperation", "operationType", "changeKind", "(Lnet/zetetic/database/sqlcipher/SQLiteDatabase;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Long;", "enqueueCashAccountReference", "accountId", "(Lnet/zetetic/database/sqlcipher/SQLiteDatabase;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Long;", "enqueueCatalogSnapshot", "enqueueInvoicePosting", "invoiceType", "invoiceId", "cashAccountId", "occurredAt", "operatingDate", "createEncryptedApplicationAck", "acceptEncryptedApplicationAck", "Lcom/hazem/dailystore/data/SyncApplicationAckDisposition;", "localDeviceRole", "localDeviceId", "authorityDeviceId", "activeSecondaryDeviceIds", "pendingOutboxForDevice", "incomingOperationStatus", "recordIncomingFromPrimary", "appliedOperationsAwaitingAckForDevice", "retryAfterMillis", "markApplicationAckForwarded", "pendingOutbox", "hasConfiguredPrimary", "requirePrimaryManager", "state", "key", "putState", "value", "audit", "actor", "action", "before", "after", "randomToken", "sha256", "timestamp", "atMillis", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SyncProtocolRepository {
    private static final long INVITATION_TTL_MS = 600000;
    private static final int MAX_PAYLOAD_CHARS = 131072;
    private final Context context;
    private final SyncCredentialVault credentialVault;
    private final AppDatabase database;
    private final SecureRandom random;
    private static final Set<String> PRIMARY_TO_SECONDARY_REFERENCE_ENTITIES = SetsKt.setOf((Object[]) new String[]{"CATEGORY", "UNIT", "PRODUCT", "PRODUCT_OPENING_STOCK", "CUSTOMER", "CASH_ACCOUNT", "EXCHANGE_RATE", "STORE_SETTINGS", "SERVICE_TRANSFER_RECEIVABLE"});

    public SyncProtocolRepository(Context context, SyncCredentialVault credentialVault) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(credentialVault, "credentialVault");
        this.context = context;
        this.credentialVault = credentialVault;
        this.database = AppDatabase.INSTANCE.getInstance(context);
        this.random = new SecureRandom();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ SyncProtocolRepository(Context context, SyncCredentialVault syncCredentialVault, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? new SyncCredentialVault(context, null, 2, 0 == true ? 1 : 0) : syncCredentialVault);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void configureAsPrimary(String actorUserId, String displayName) {
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        String string = StringsKt.trim((CharSequence) displayName).toString();
        if (string.length() <= 0 || string.length() > 80) {
            throw new IllegalArgumentException("\u0627\u0633\u0645 \u0627\u0644\u062c\u0647\u0627\u0632 \u0645\u0637\u0644\u0648\u0628 \u0648\u0628\u062d\u062f \u0623\u0642\u0635\u0649 80 \u062d\u0631\u0641\u0627\u064b".toString());
        }
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        writableDatabase.beginTransaction();
        try {
            PermissionPolicy.INSTANCE.require(writableDatabase, actorUserId, PermissionPolicy.SYNC_MANAGE);
            String strState = state(writableDatabase, "local_device_role");
            if (strState != null && !Intrinsics.areEqual(strState, "PRIMARY")) {
                throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u0631\u0642\u064a\u0629 \u062c\u0647\u0627\u0632 \u062b\u0627\u0646\u0648\u064a \u0625\u0644\u0649 \u062c\u0647\u0627\u0632 \u0623\u0633\u0627\u0633\u064a\u061b \u064a\u062c\u0628 \u0625\u0639\u062f\u0627\u062f \u062c\u0647\u0627\u0632 \u0623\u0633\u0627\u0633\u064a \u0645\u0633\u062a\u0642\u0644".toString());
            }
            String orCreate = SyncDeviceIdentity.INSTANCE.getOrCreate(this.context);
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT device_role FROM sync_devices WHERE device_id=?", new String[]{orCreate});
            try {
                Cursor cursor = cursorRawQuery;
                String string2 = cursor.moveToFirst() ? cursor.getString(0) : null;
                CloseableKt.closeFinally(cursorRawQuery, null);
                if (string2 != null && !Intrinsics.areEqual(string2, "PRIMARY")) {
                    throw new IllegalArgumentException("\u0633\u062c\u0644 \u0647\u0630\u0627 \u0627\u0644\u062c\u0647\u0627\u0632 \u064a\u062b\u0628\u062a \u0623\u0646\u0647 \u062b\u0627\u0646\u0648\u064a\u061b \u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u0631\u0642\u064a\u062a\u0647 \u0625\u0644\u0649 \u0623\u0633\u0627\u0633\u064a".toString());
                }
                String strState2 = state(writableDatabase, "authority_device_id");
                if (strState2 != null && !Intrinsics.areEqual(strState2, orCreate)) {
                    throw new IllegalArgumentException("\u064a\u0648\u062c\u062f \u062c\u0647\u0627\u0632 \u0623\u0633\u0627\u0633\u064a \u0622\u062e\u0631\u061b \u0644\u0627 \u064a\u0645\u0643\u0646 \u0646\u0642\u0644 \u0633\u0644\u0637\u0629 \u0627\u0644\u0627\u0639\u062a\u0645\u0627\u062f \u062a\u0644\u0642\u0627\u0626\u064a\u0627\u064b".toString());
                }
                String strTimestamp$default = timestamp$default(this, 0L, 1, null);
                KeyPair orCreate2 = SyncDeviceAuthenticator.INSTANCE.getOrCreate(this.context, orCreate);
                ContentValues contentValues = new ContentValues();
                contentValues.put("device_id", orCreate);
                contentValues.put("display_name", string);
                contentValues.put("device_role", "PRIMARY");
                contentValues.put(NotificationCompat.CATEGORY_STATUS, "ACTIVE");
                contentValues.put("enrolled_by", actorUserId);
                contentValues.put("created_at", strTimestamp$default);
                contentValues.put("last_seen_at", strTimestamp$default);
                Unit unit = Unit.INSTANCE;
                writableDatabase.insertWithOnConflict("sync_devices", null, contentValues, 4);
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("display_name", string);
                contentValues2.put("last_seen_at", strTimestamp$default);
                contentValues2.put(NotificationCompat.CATEGORY_STATUS, "ACTIVE");
                contentValues2.putNull("revoked_at");
                Unit unit2 = Unit.INSTANCE;
                writableDatabase.update("sync_devices", contentValues2, "device_id=?", new String[]{orCreate});
                String strEncodeToString = Base64.encodeToString(orCreate2.getPublic().getEncoded(), 2);
                Intrinsics.checkNotNullExpressionValue(strEncodeToString, "encodeToString(...)");
                storeDevicePublicKey(writableDatabase, orCreate, strEncodeToString, strTimestamp$default);
                putState(writableDatabase, "authority_device_id", orCreate, strTimestamp$default);
                putState(writableDatabase, "local_device_id", orCreate, strTimestamp$default);
                putState(writableDatabase, "local_device_role", "PRIMARY", strTimestamp$default);
                if (state(writableDatabase, "store_id") == null) {
                    String string3 = UUID.randomUUID().toString();
                    Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
                    putState(writableDatabase, "store_id", string3, strTimestamp$default);
                }
                if (state(writableDatabase, "next_sequence") == null) {
                    putState(writableDatabase, "next_sequence", "1", strTimestamp$default);
                }
                audit(writableDatabase, actorUserId, "SYNC_PRIMARY_CONFIGURED", orCreate, null, new JSONObject().put("display_name", string).toString(), strTimestamp$default);
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(cursorRawQuery, th);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            writableDatabase.endTransaction();
            throw th3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final SyncPairingInvitation createPairingInvitation(String actorUserId) {
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        writableDatabase.beginTransaction();
        try {
            requirePrimaryManager(writableDatabase, actorUserId);
            String strRandomToken = randomToken();
            String strTimestamp$default = timestamp$default(this, 0L, 1, null);
            String strTimestamp = timestamp(System.currentTimeMillis() + INVITATION_TTL_MS);
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            writableDatabase.delete("sync_pairing_invitations", "expires_at<=? OR consumed_at IS NOT NULL", new String[]{strTimestamp$default});
            ContentValues contentValues = new ContentValues();
            contentValues.put("invitation_id", string);
            contentValues.put("invitation_hash", sha256(strRandomToken));
            contentValues.put("created_by", actorUserId);
            contentValues.put("expires_at", strTimestamp);
            contentValues.put("created_at", strTimestamp$default);
            Unit unit = Unit.INSTANCE;
            writableDatabase.insertOrThrow("sync_pairing_invitations", null, contentValues);
            audit(writableDatabase, actorUserId, "SYNC_PAIRING_INVITATION_CREATED", string, null, new JSONObject().put("expires_at", strTimestamp).toString(), strTimestamp$default);
            writableDatabase.setTransactionSuccessful();
            return new SyncPairingInvitation(string, strRandomToken, strTimestamp);
        } finally {
            writableDatabase.endTransaction();
        }
    }

    public static /* synthetic */ SyncEnrollment enrollSecondaryDevice$default(SyncProtocolRepository syncProtocolRepository, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
        if ((i & 32) != 0) {
            str6 = null;
        }
        return syncProtocolRepository.enrollSecondaryDevice(str, str2, str3, str4, str5, str6);
    }

    /* JADX WARN: Code duplicated, block: B:182:0x0459 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:199:0x01e7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:57:0x01f1  */
    /* JADX WARN: Code duplicated, block: B:58:0x01f4  */
    /* JADX WARN: Code duplicated, block: B:63:0x01ff  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v65, types: [android.content.ContentValues] */
    /* JADX WARN: Type inference failed for: r0v70, types: [android.content.ContentValues] */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v14 */
    /* JADX WARN: Type inference failed for: r11v15 */
    /* JADX WARN: Type inference failed for: r11v16 */
    /* JADX WARN: Type inference failed for: r11v17 */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v28 */
    /* JADX WARN: Type inference failed for: r11v29 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v30 */
    /* JADX WARN: Type inference failed for: r11v37 */
    /* JADX WARN: Type inference failed for: r11v38 */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v43, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r11v45 */
    /* JADX WARN: Type inference failed for: r11v46 */
    /* JADX WARN: Type inference failed for: r11v47 */
    /* JADX WARN: Type inference failed for: r11v48 */
    /* JADX WARN: Type inference failed for: r11v49 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r11v50 */
    /* JADX WARN: Type inference failed for: r11v51 */
    /* JADX WARN: Type inference failed for: r11v52 */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r11v9 */
    /* JADX WARN: Type inference failed for: r13v10 */
    /* JADX WARN: Type inference failed for: r13v11 */
    /* JADX WARN: Type inference failed for: r13v12 */
    /* JADX WARN: Type inference failed for: r13v13, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r13v14 */
    /* JADX WARN: Type inference failed for: r13v19 */
    /* JADX WARN: Type inference failed for: r13v21 */
    /* JADX WARN: Type inference failed for: r13v25 */
    /* JADX WARN: Type inference failed for: r13v26 */
    /* JADX WARN: Type inference failed for: r13v30 */
    /* JADX WARN: Type inference failed for: r13v31 */
    /* JADX WARN: Type inference failed for: r13v32 */
    /* JADX WARN: Type inference failed for: r13v33 */
    /* JADX WARN: Type inference failed for: r13v34, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r13v35 */
    /* JADX WARN: Type inference failed for: r13v36 */
    /* JADX WARN: Type inference failed for: r13v37 */
    /* JADX WARN: Type inference failed for: r13v38 */
    /* JADX WARN: Type inference failed for: r13v39 */
    /* JADX WARN: Type inference failed for: r13v40 */
    /* JADX WARN: Type inference failed for: r13v41 */
    /* JADX WARN: Type inference failed for: r13v42 */
    /* JADX WARN: Type inference failed for: r13v6, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r13v7 */
    /* JADX WARN: Type inference failed for: r13v8 */
    /* JADX WARN: Type inference failed for: r13v9 */
    /* JADX WARN: Type inference failed for: r19v1 */
    /* JADX WARN: Type inference failed for: r19v2 */
    /* JADX WARN: Type inference failed for: r19v3 */
    /* JADX WARN: Type inference failed for: r19v4 */
    /* JADX WARN: Type inference failed for: r19v5 */
    /* JADX WARN: Type inference failed for: r19v6 */
    /* JADX WARN: Type inference failed for: r19v7 */
    /* JADX WARN: Type inference failed for: r19v8 */
    /* JADX WARN: Type inference failed for: r1v40, types: [com.hazem.dailystore.data.StoreTime] */
    /* JADX WARN: Type inference failed for: r35v0, types: [com.hazem.dailystore.data.SyncProtocolRepository] */
    /* JADX WARN: Type inference failed for: r36v1, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r4v20, types: [com.hazem.dailystore.data.SyncCredentialVault] */
    /* JADX WARN: Type inference failed for: r5v8, types: [java.lang.Object, net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r7v14, types: [org.json.JSONObject] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final SyncEnrollment enrollSecondaryDevice(String actorUserId, String invitationToken, String remoteDeviceId, String displayName, String remotePublicKeyBase64, String remoteActorUserId) throws Throwable {
        Object objM381constructorimpl;
        ?? r13;
        ?? r11;
        Throwable th;
        boolean z;
        boolean z2;
        ?? r14;
        ?? r12;
        boolean z3;
        ?? r15;
        ?? r16;
        ?? r17;
        ?? r18;
        ?? r19;
        ?? r110;
        String str;
        int length;
        ?? r111;
        ?? r112 = NotificationCompat.CATEGORY_STATUS;
        String str2 = "device_role";
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(invitationToken, "invitationToken");
        Intrinsics.checkNotNullParameter(remoteDeviceId, "remoteDeviceId");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        Intrinsics.checkNotNullParameter(remotePublicKeyBase64, "remotePublicKeyBase64");
        ?? string = StringsKt.trim((CharSequence) displayName).toString();
        if (((CharSequence) string).length() <= 0 || string.length() > 80) {
            throw new IllegalArgumentException("\u0627\u0633\u0645 \u0627\u0644\u062c\u0647\u0627\u0632 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            objM381constructorimpl = Result.m381constructorimpl(UUID.fromString(remoteDeviceId));
        } catch (Throwable th2) {
            Result.Companion companion2 = Result.INSTANCE;
            objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th2));
        }
        if (!Result.m388isSuccessimpl(objM381constructorimpl)) {
            throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u062c\u0647\u0627\u0632 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
        }
        if (Intrinsics.areEqual(remoteDeviceId, SyncDeviceIdentity.INSTANCE.getOrCreate(this.context))) {
            throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0643\u062c\u0647\u0627\u0632 \u062b\u0627\u0646\u0648\u064a".toString());
        }
        int length2 = invitationToken.length();
        if (32 > length2 || length2 >= 129) {
            throw new IllegalArgumentException("\u0631\u0645\u0632 \u0627\u0644\u0627\u0642\u062a\u0631\u0627\u0646 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
        }
        if (StringsKt.isBlank(remotePublicKeyBase64)) {
            throw new IllegalArgumentException("\u064a\u062c\u0628 \u0627\u0633\u062a\u0644\u0627\u0645 \u0627\u0644\u0645\u0641\u062a\u0627\u062d \u0627\u0644\u0639\u0627\u0645 \u0644\u0644\u062c\u0647\u0627\u0632 \u0642\u0628\u0644 \u062a\u0633\u062c\u064a\u0644\u0647".toString());
        }
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        requirePrimaryManager(readableDatabase, actorUserId);
        new CashAccountRepository(this.context).ensureSystemCashAccounts();
        string = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(string, "<get-writableDatabase>(...)");
        byte[] bArr = new byte[32];
        string.beginTransaction();
        try {
            try {
                try {
                    try {
                        requirePrimaryManager(string, actorUserId);
                        String strTimestamp$default = timestamp$default(this, 0L, 1, null);
                        Cursor cursorRawQuery = string.rawQuery("SELECT invitation_id FROM sync_pairing_invitations\n                   WHERE invitation_hash=? AND consumed_at IS NULL AND expires_at>?", new String[]{sha256(invitationToken), strTimestamp$default});
                        try {
                            Cursor cursor = cursorRawQuery;
                            String string2 = cursor.moveToFirst() ? cursor.getString(0) : null;
                            CloseableKt.closeFinally(cursorRawQuery, null);
                            if (string2 == null) {
                                throw new IllegalArgumentException("\u0631\u0645\u0632 \u0627\u0644\u0627\u0642\u062a\u0631\u0627\u0646 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d \u0623\u0648 \u0645\u0646\u062a\u0647\u064a \u0627\u0644\u0635\u0644\u0627\u062d\u064a\u0629 \u0623\u0648 \u0645\u0633\u062a\u062e\u062f\u0645 \u0633\u0627\u0628\u0642\u0627\u064b");
                            }
                            String strRandomToken = randomToken();
                            byte[] bArrDecode = Base64.decode(strRandomToken, 11);
                            if (bArrDecode.length != 32) {
                                throw new IllegalArgumentException("Failed requirement.".toString());
                            }
                            Intrinsics.checkNotNull(bArrDecode);
                            ArraysKt.copyInto$default(bArrDecode, bArr, 0, 0, 0, 14, (Object) null);
                            Cursor cursorRawQuery2 = string.rawQuery("SELECT status FROM sync_devices WHERE device_id=?", new String[]{remoteDeviceId});
                            try {
                                Cursor cursor2 = cursorRawQuery2;
                                String string3 = cursor2.moveToFirst() ? cursor2.getString(0) : null;
                                CloseableKt.closeFinally(cursorRawQuery2, null);
                                if (string3 != null) {
                                    try {
                                        if (!Intrinsics.areEqual(string3, "REVOKED")) {
                                            throw new IllegalArgumentException("\u0647\u0630\u0627 \u0627\u0644\u062c\u0647\u0627\u0632 \u0645\u0633\u062c\u0644 \u0628\u0627\u0644\u0641\u0639\u0644".toString());
                                        }
                                        ?? contentValues = new ContentValues();
                                        contentValues.put("device_id", remoteDeviceId);
                                        contentValues.put("display_name", string);
                                        contentValues.put("device_role", "SECONDARY");
                                        contentValues.put("credential_hash", sha256(strRandomToken));
                                        contentValues.put(NotificationCompat.CATEGORY_STATUS, "ACTIVE");
                                        r19 = bArr;
                                        String str3 = string2;
                                        try {
                                            try {
                                                contentValues.put("enrolled_by", actorUserId);
                                                contentValues.put("created_at", strTimestamp$default);
                                                contentValues.put("last_seen_at", strTimestamp$default);
                                                Unit unit = Unit.INSTANCE;
                                                try {
                                                    string.insertWithOnConflict("sync_devices", null, contentValues, 4);
                                                    ?? contentValues2 = new ContentValues();
                                                    contentValues2.put("display_name", string);
                                                    contentValues2.put("device_role", "SECONDARY");
                                                    contentValues2.put("credential_hash", sha256(strRandomToken));
                                                    contentValues2.put(NotificationCompat.CATEGORY_STATUS, "ACTIVE");
                                                    contentValues2.put("enrolled_by", actorUserId);
                                                    contentValues2.put("last_seen_at", strTimestamp$default);
                                                    contentValues2.putNull("revoked_at");
                                                    Unit unit2 = Unit.INSTANCE;
                                                    string.update("sync_devices", contentValues2, "device_id=?", new String[]{remoteDeviceId});
                                                    ContentValues contentValues3 = new ContentValues();
                                                    try {
                                                        contentValues3.put("device_id", remoteDeviceId);
                                                        if (remoteActorUserId != null) {
                                                            try {
                                                                if (StringsKt.isBlank(remoteActorUserId)) {
                                                                    str = null;
                                                                } else {
                                                                    str = remoteActorUserId;
                                                                }
                                                                if (str == null) {
                                                                    str = actorUserId;
                                                                }
                                                            } catch (Throwable th3) {
                                                                th = th3;
                                                                r13 = string;
                                                                r11 = r19;
                                                                z = false;
                                                                r16 = r11;
                                                                r15 = r13;
                                                            }
                                                        } else {
                                                            str = actorUserId;
                                                        }
                                                        contentValues3.put("remote_actor_user_id", str);
                                                        contentValues3.put("primary_user_id", actorUserId);
                                                        contentValues3.put("is_active", (Integer) 1);
                                                        contentValues3.put("mapped_by", actorUserId);
                                                        contentValues3.put("mapped_at", strTimestamp$default);
                                                        Unit unit3 = Unit.INSTANCE;
                                                        string.insertWithOnConflict("sync_actor_mappings", null, contentValues3, 5);
                                                        ContentValues contentValues4 = new ContentValues();
                                                        contentValues4.put("consumed_at", strTimestamp$default);
                                                        Unit unit4 = Unit.INSTANCE;
                                                        string.update("sync_pairing_invitations", contentValues4, "invitation_id=?", new String[]{str3});
                                                        r112 = r19;
                                                        try {
                                                            try {
                                                                byte[] bArrDecode2 = Base64.decode(remotePublicKeyBase64, 0);
                                                                length = bArrDecode2.length;
                                                                if (64 <= length || length >= 257) {
                                                                    throw new IllegalArgumentException("\u0645\u0641\u062a\u0627\u062d \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                                                }
                                                                String strEncodeToString = Base64.encodeToString(bArrDecode2, 2);
                                                                Intrinsics.checkNotNull(strEncodeToString);
                                                                storeDevicePublicKey(string, remoteDeviceId, strEncodeToString, strTimestamp$default);
                                                                r19 = r112;
                                                                KeyPair orCreate = SyncDeviceAuthenticator.INSTANCE.getOrCreate(this.context, SyncDeviceIdentity.INSTANCE.getOrCreate(this.context));
                                                                String orCreate2 = SyncDeviceIdentity.INSTANCE.getOrCreate(this.context);
                                                                String strEncodeToString2 = Base64.encodeToString(orCreate.getPublic().getEncoded(), 2);
                                                                Intrinsics.checkNotNullExpressionValue(strEncodeToString2, "encodeToString(...)");
                                                                storeDevicePublicKey(string, orCreate2, strEncodeToString2, strTimestamp$default);
                                                                String strState = state(string, "store_id");
                                                                if (strState == null) {
                                                                    throw new IllegalStateException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0645\u062a\u062c\u0631 \u063a\u064a\u0631 \u0645\u0647\u064a\u0623".toString());
                                                                }
                                                                SyncDeviceCertificate syncDeviceCertificate = SyncDeviceCertificate.INSTANCE;
                                                                String orCreate3 = SyncDeviceIdentity.INSTANCE.getOrCreate(this.context);
                                                                PrivateKey privateKey = orCreate.getPrivate();
                                                                Intrinsics.checkNotNullExpressionValue(privateKey, "getPrivate(...)");
                                                                try {
                                                                    String strIssue = syncDeviceCertificate.issue(strState, orCreate3, remoteDeviceId, strEncodeToString, privateKey, (32 & 32) != 0 ? System.currentTimeMillis() : 0L);
                                                                    ContentValues contentValues5 = new ContentValues();
                                                                    contentValues5.put("certificate_json", strIssue);
                                                                    Unit unit5 = Unit.INSTANCE;
                                                                    string.update("sync_device_public_keys", contentValues5, "device_id=?", new String[]{remoteDeviceId});
                                                                    ContentValues contentValues6 = new ContentValues();
                                                                    contentValues6.put("device_id", remoteDeviceId);
                                                                    contentValues6.put("last_sequence", (Long) 0L);
                                                                    contentValues6.put("updated_at", strTimestamp$default);
                                                                    Unit unit6 = Unit.INSTANCE;
                                                                    string.insertWithOnConflict("sync_cursors", null, contentValues6, 5);
                                                                    enqueueCatalogSnapshot(string, actorUserId);
                                                                    r112 = r19;
                                                                    this.credentialVault.save(remoteDeviceId, r112);
                                                                    try {
                                                                        String string4 = new JSONObject().put("display_name", string).put("device_role", "SECONDARY").toString();
                                                                        r111 = string;
                                                                        str2 = remoteDeviceId;
                                                                        try {
                                                                            audit(string, actorUserId, "SYNC_DEVICE_ENROLLED", remoteDeviceId, null, string4, strTimestamp$default);
                                                                            r111.setTransactionSuccessful();
                                                                            try {
                                                                                String strState2 = state(r111, "store_id");
                                                                                if (strState2 != null) {
                                                                                    SyncEnrollment syncEnrollment = new SyncEnrollment(remoteDeviceId, strRandomToken, strState2, SyncDeviceIdentity.INSTANCE.getOrCreate(this.context), StoreTime.INSTANCE.zoneId(r111), Base64.encodeToString(orCreate.getPublic().getEncoded(), 2), strIssue);
                                                                                    r111.endTransaction();
                                                                                    try {
                                                                                        Result.Companion companion3 = Result.INSTANCE;
                                                                                        SyncRelaySession.INSTANCE.flushPending();
                                                                                        Result.m381constructorimpl(Unit.INSTANCE);
                                                                                    } catch (Throwable th4) {
                                                                                        Result.Companion companion4 = Result.INSTANCE;
                                                                                        Result.m381constructorimpl(ResultKt.createFailure(th4));
                                                                                    }
                                                                                    ArraysKt.fill$default((byte[]) r112, (byte) 0, 0, 0, 6, (Object) null);
                                                                                    return syncEnrollment;
                                                                                }
                                                                                throw new IllegalStateException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0645\u062a\u062c\u0631 \u063a\u064a\u0631 \u0645\u0647\u064a\u0623".toString());
                                                                            } catch (Exception e) {
                                                                                e = e;
                                                                                z2 = true;
                                                                                z3 = true;
                                                                                r18 = r112;
                                                                                r17 = r111;
                                                                            } catch (Throwable th5) {
                                                                                th = th5;
                                                                                z = true;
                                                                                r16 = r112;
                                                                                r15 = r111;
                                                                            }
                                                                        } catch (Exception e2) {
                                                                            e = e2;
                                                                            z2 = true;
                                                                            r12 = r112;
                                                                            r14 = r111;
                                                                            z3 = false;
                                                                            r18 = r12;
                                                                            r17 = r14;
                                                                        }
                                                                    } catch (Exception e3) {
                                                                        e = e3;
                                                                        r111 = string;
                                                                        str2 = remoteDeviceId;
                                                                    }
                                                                } catch (Exception e4) {
                                                                    e = e4;
                                                                    string = string;
                                                                    str2 = remoteDeviceId;
                                                                    r112 = r19;
                                                                    z2 = false;
                                                                    r12 = r112;
                                                                    r14 = string;
                                                                } catch (Throwable th6) {
                                                                    th = th6;
                                                                    string = string;
                                                                    r112 = r19;
                                                                    th = th;
                                                                    r11 = r112;
                                                                    r13 = string;
                                                                    z = false;
                                                                    r16 = r11;
                                                                    r15 = r13;
                                                                }
                                                            } catch (Exception e5) {
                                                                e = e5;
                                                                string = string;
                                                                str2 = remoteDeviceId;
                                                            }
                                                        } catch (Throwable th7) {
                                                            th = th7;
                                                            string = string;
                                                        }
                                                    } catch (Exception e6) {
                                                        e = e6;
                                                        str2 = remoteDeviceId;
                                                        r110 = r19;
                                                        r112 = r110;
                                                    }
                                                } catch (Exception e7) {
                                                    e = e7;
                                                    str2 = remoteDeviceId;
                                                    r110 = r19;
                                                }
                                            } catch (Throwable th8) {
                                                th = th8;
                                                string = string;
                                                r112 = r19;
                                            }
                                        } catch (Exception e8) {
                                            e = e8;
                                            str2 = remoteDeviceId;
                                            r110 = r19;
                                        }
                                        z2 = false;
                                        r12 = r112;
                                        r14 = string;
                                        z3 = false;
                                        r18 = r12;
                                        r17 = r14;
                                        if (z2) {
                                            try {
                                                this.credentialVault.remove(str2);
                                            } catch (Throwable th9) {
                                                th = th9;
                                                z = z3;
                                                r16 = r18;
                                                r15 = r17;
                                            }
                                        }
                                        throw e;
                                    } catch (Throwable th10) {
                                        th = th10;
                                        r11 = bArr;
                                        r13 = string;
                                    }
                                } else {
                                    ?? contentValues7 = new ContentValues();
                                    contentValues7.put("device_id", remoteDeviceId);
                                    contentValues7.put("display_name", string);
                                    contentValues7.put("device_role", "SECONDARY");
                                    contentValues7.put("credential_hash", sha256(strRandomToken));
                                    contentValues7.put(NotificationCompat.CATEGORY_STATUS, "ACTIVE");
                                    r19 = bArr;
                                    String str4 = string2;
                                    contentValues7.put("enrolled_by", actorUserId);
                                    contentValues7.put("created_at", strTimestamp$default);
                                    contentValues7.put("last_seen_at", strTimestamp$default);
                                    Unit unit7 = Unit.INSTANCE;
                                    string.insertWithOnConflict("sync_devices", null, contentValues7, 4);
                                    ?? contentValues8 = new ContentValues();
                                    contentValues8.put("display_name", string);
                                    contentValues8.put("device_role", "SECONDARY");
                                    contentValues8.put("credential_hash", sha256(strRandomToken));
                                    contentValues8.put(NotificationCompat.CATEGORY_STATUS, "ACTIVE");
                                    contentValues8.put("enrolled_by", actorUserId);
                                    contentValues8.put("last_seen_at", strTimestamp$default);
                                    contentValues8.putNull("revoked_at");
                                    Unit unit8 = Unit.INSTANCE;
                                    string.update("sync_devices", contentValues8, "device_id=?", new String[]{remoteDeviceId});
                                    ContentValues contentValues9 = new ContentValues();
                                    contentValues9.put("device_id", remoteDeviceId);
                                    if (remoteActorUserId != null) {
                                        if (StringsKt.isBlank(remoteActorUserId)) {
                                            str = remoteActorUserId;
                                        } else {
                                            str = null;
                                        }
                                        if (str == null) {
                                            str = actorUserId;
                                        }
                                    } else {
                                        str = actorUserId;
                                    }
                                    contentValues9.put("remote_actor_user_id", str);
                                    contentValues9.put("primary_user_id", actorUserId);
                                    contentValues9.put("is_active", (Integer) 1);
                                    contentValues9.put("mapped_by", actorUserId);
                                    contentValues9.put("mapped_at", strTimestamp$default);
                                    Unit unit9 = Unit.INSTANCE;
                                    string.insertWithOnConflict("sync_actor_mappings", null, contentValues9, 5);
                                    ContentValues contentValues10 = new ContentValues();
                                    contentValues10.put("consumed_at", strTimestamp$default);
                                    Unit unit10 = Unit.INSTANCE;
                                    string.update("sync_pairing_invitations", contentValues10, "invitation_id=?", new String[]{str4});
                                    r112 = r19;
                                    byte[] bArrDecode3 = Base64.decode(remotePublicKeyBase64, 0);
                                    length = bArrDecode3.length;
                                    if (64 <= length) {
                                    }
                                    throw new IllegalArgumentException("\u0645\u0641\u062a\u0627\u062d \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                                }
                            } catch (Throwable th11) {
                                try {
                                    throw th11;
                                } catch (Throwable th12) {
                                    CloseableKt.closeFinally(cursorRawQuery2, th11);
                                    throw th12;
                                }
                            }
                            r15.endTransaction();
                            if (z) {
                                try {
                                    Result.Companion companion5 = Result.INSTANCE;
                                    SyncRelaySession.INSTANCE.flushPending();
                                    Result.m381constructorimpl(Unit.INSTANCE);
                                } catch (Throwable th13) {
                                    Result.Companion companion6 = Result.INSTANCE;
                                    Result.m381constructorimpl(ResultKt.createFailure(th13));
                                }
                            }
                            ArraysKt.fill$default((byte[]) r16, (byte) 0, 0, 0, 6, (Object) null);
                            throw th;
                        } catch (Throwable th14) {
                            try {
                                throw th14;
                            } catch (Throwable th15) {
                                CloseableKt.closeFinally(cursorRawQuery, th14);
                                throw th15;
                            }
                        }
                    } catch (Throwable th16) {
                        th = th16;
                    }
                } catch (Throwable th17) {
                    th = th17;
                    r112 = bArr;
                }
            } catch (Exception e9) {
                e = e9;
            }
        } catch (Exception e10) {
            e = e10;
            r112 = bArr;
            string = string;
            str2 = remoteDeviceId;
        }
        string = string;
        th = th;
        r11 = r112;
        r13 = string;
        z = false;
        r16 = r11;
        r15 = r13;
        r15.endTransaction();
        if (z) {
            Result.Companion companion7 = Result.INSTANCE;
            SyncRelaySession.INSTANCE.flushPending();
            Result.m381constructorimpl(Unit.INSTANCE);
        }
        ArraysKt.fill$default((byte[]) r16, (byte) 0, 0, 0, 6, (Object) null);
        throw th;
    }

    /* JADX WARN: Code duplicated, block: B:94:0x0354 A[Catch: all -> 0x035a, TRY_ENTER, TryCatch #11 {all -> 0x035a, blocks: (B:67:0x030e, B:94:0x0354, B:95:0x0359), top: B:125:0x030e }] */
    /* JADX WARN: Multi-variable type inference failed */
    public final void configureAsSecondary(String actorUserId, String storeId, String primaryDeviceId, String credential, String displayName, String storeTimeZoneId, String primaryPublicKeyBase64, String deviceCertificateJson) throws Throwable {
        Object objM381constructorimpl;
        Object objM381constructorimpl2;
        byte[] bArr;
        String str;
        boolean z;
        SyncProtocolRepository syncProtocolRepository = this;
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(storeId, "storeId");
        Intrinsics.checkNotNullParameter(primaryDeviceId, "primaryDeviceId");
        Intrinsics.checkNotNullParameter(credential, "credential");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        Intrinsics.checkNotNullParameter(storeTimeZoneId, "storeTimeZoneId");
        Intrinsics.checkNotNullParameter(primaryPublicKeyBase64, "primaryPublicKeyBase64");
        Intrinsics.checkNotNullParameter(deviceCertificateJson, "deviceCertificateJson");
        if (StringsKt.isBlank(storeId) || StringsKt.trim((CharSequence) displayName).toString().length() <= 0 || displayName.length() > 80) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!StoreTime.INSTANCE.isValidZoneId(storeTimeZoneId)) {
            throw new IllegalArgumentException("\u0645\u0646\u0637\u0642\u0629 \u0627\u0644\u0645\u062a\u062c\u0631 \u0627\u0644\u0632\u0645\u0646\u064a\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            objM381constructorimpl = Result.m381constructorimpl(UUID.fromString(primaryDeviceId));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
        }
        if (!Result.m388isSuccessimpl(objM381constructorimpl)) {
            throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
        }
        try {
            Result.Companion companion3 = Result.INSTANCE;
            objM381constructorimpl2 = Result.m381constructorimpl(Base64.decode(credential, 11));
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            objM381constructorimpl2 = Result.m381constructorimpl(ResultKt.createFailure(th2));
        }
        if (Result.m384exceptionOrNullimpl(objM381constructorimpl2) != null) {
            throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u062c\u0647\u0627\u0632 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629");
        }
        byte[] bArr2 = (byte[]) objM381constructorimpl2;
        if (bArr2.length != 32) {
            throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u062c\u0647\u0627\u0632 \u064a\u062c\u0628 \u0623\u0646 \u062a\u0643\u0648\u0646 256 \u0628\u062a".toString());
        }
        String orCreate = SyncDeviceIdentity.INSTANCE.getOrCreate(syncProtocolRepository.context);
        if (Intrinsics.areEqual(orCreate, primaryDeviceId)) {
            throw new IllegalArgumentException("\u0647\u0630\u0627 \u0627\u0644\u0647\u0627\u062a\u0641 \u0647\u0648 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0628\u0627\u0644\u0641\u0639\u0644".toString());
        }
        Cursor cursorRawQuery = syncProtocolRepository.database.getReadableDatabase().rawQuery("SELECT device_role FROM sync_devices WHERE device_id=?", new String[]{orCreate});
        try {
            Cursor cursor = cursorRawQuery;
            String string = cursor.moveToFirst() ? cursor.getString(0) : null;
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (string != null && !Intrinsics.areEqual(string, "SECONDARY")) {
                throw new IllegalArgumentException("\u0633\u062c\u0644 \u0647\u0630\u0627 \u0627\u0644\u062c\u0647\u0627\u0632 \u064a\u062b\u0628\u062a \u0623\u0646\u0647 \u0623\u0633\u0627\u0633\u064a\u061b \u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u062d\u0648\u064a\u0644\u0647 \u0625\u0644\u0649 \u062b\u0627\u0646\u0648\u064a \u062a\u0644\u0642\u0627\u0626\u064a\u0627\u064b".toString());
            }
            String strEncodeToString = Base64.encodeToString(SyncDeviceAuthenticator.INSTANCE.getOrCreate(syncProtocolRepository.context, orCreate).getPublic().getEncoded(), 2);
            if (!SyncDeviceCertificate.INSTANCE.verify(deviceCertificateJson, storeId, orCreate, primaryDeviceId, primaryPublicKeyBase64, (64 & 32) != 0 ? null : strEncodeToString, (64 & 64) != 0 ? System.currentTimeMillis() : 0L)) {
                throw new IllegalArgumentException("\u0634\u0647\u0627\u062f\u0629 \u0627\u0644\u062c\u0647\u0627\u0632 \u063a\u064a\u0631 \u0645\u0648\u0642\u0639\u0629 \u0645\u0646 \u062c\u0630\u0631 \u0627\u0644\u062b\u0642\u0629 \u0623\u0648 \u0644\u0627 \u062a\u062e\u0635 \u0647\u0630\u0627 \u0627\u0644\u062c\u0647\u0627\u0632".toString());
            }
            SQLiteDatabase writableDatabase = syncProtocolRepository.database.getWritableDatabase();
            Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
            writableDatabase.beginTransaction();
            try {
                try {
                    PermissionPolicy.INSTANCE.require(writableDatabase, actorUserId, PermissionPolicy.SYNC_MANAGE);
                    String strState = syncProtocolRepository.state(writableDatabase, "local_device_role");
                    if (strState != null && !Intrinsics.areEqual(strState, "SECONDARY")) {
                        throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u062d\u0648\u064a\u0644 \u062c\u0647\u0627\u0632 \u0623\u0633\u0627\u0633\u064a \u0642\u0627\u0626\u0645 \u0625\u0644\u0649 \u062b\u0627\u0646\u0648\u064a \u062a\u0644\u0642\u0627\u0626\u064a\u0627\u064b".toString());
                    }
                    String strTimestamp$default = timestamp$default(syncProtocolRepository, 0L, 1, null);
                    if (strState == null && string == null) {
                        syncProtocolRepository.clearUncommittedLocalServiceWalletSeedsForSecondary(writableDatabase, actorUserId, strTimestamp$default);
                    }
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("device_id", primaryDeviceId);
                    contentValues.put("display_name", "\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a");
                    contentValues.put("device_role", "PRIMARY");
                    contentValues.put(NotificationCompat.CATEGORY_STATUS, "ACTIVE");
                    contentValues.put("created_at", strTimestamp$default);
                    contentValues.put("last_seen_at", strTimestamp$default);
                    Unit unit = Unit.INSTANCE;
                    try {
                        try {
                            writableDatabase.insertWithOnConflict("sync_devices", null, contentValues, 4);
                            ContentValues contentValues2 = new ContentValues();
                            contentValues2.put(NotificationCompat.CATEGORY_STATUS, "ACTIVE");
                            contentValues2.putNull("revoked_at");
                            Unit unit2 = Unit.INSTANCE;
                            writableDatabase.update("sync_devices", contentValues2, "device_id=?", new String[]{primaryDeviceId});
                            ContentValues contentValues3 = new ContentValues();
                            str = orCreate;
                            try {
                                contentValues3.put("device_id", str);
                                contentValues3.put("display_name", StringsKt.trim((CharSequence) displayName).toString());
                                contentValues3.put("device_role", "SECONDARY");
                                contentValues3.put(NotificationCompat.CATEGORY_STATUS, "ACTIVE");
                                contentValues3.put("enrolled_by", actorUserId);
                                contentValues3.put("created_at", strTimestamp$default);
                                contentValues3.put("last_seen_at", strTimestamp$default);
                                Unit unit3 = Unit.INSTANCE;
                                writableDatabase.insertWithOnConflict("sync_devices", null, contentValues3, 4);
                                ContentValues contentValues4 = new ContentValues();
                                contentValues4.put("display_name", StringsKt.trim((CharSequence) displayName).toString());
                                contentValues4.put(NotificationCompat.CATEGORY_STATUS, "ACTIVE");
                                contentValues4.putNull("revoked_at");
                                Unit unit4 = Unit.INSTANCE;
                                writableDatabase.update("sync_devices", contentValues4, "device_id=?", new String[]{str});
                                ContentValues contentValues5 = new ContentValues();
                                contentValues5.put("device_id", primaryDeviceId);
                                contentValues5.put("last_sequence", (Long) 0L);
                                contentValues5.put("updated_at", strTimestamp$default);
                                Unit unit5 = Unit.INSTANCE;
                                writableDatabase.insertWithOnConflict("sync_cursors", null, contentValues5, 4);
                                syncProtocolRepository = this;
                                try {
                                    syncProtocolRepository.storeDevicePublicKey(writableDatabase, primaryDeviceId, primaryPublicKeyBase64, strTimestamp$default);
                                    Intrinsics.checkNotNull(strEncodeToString);
                                    syncProtocolRepository.storeDevicePublicKey(writableDatabase, str, strEncodeToString, strTimestamp$default);
                                    ContentValues contentValues6 = new ContentValues();
                                    contentValues6.put("certificate_json", deviceCertificateJson);
                                    Unit unit6 = Unit.INSTANCE;
                                    writableDatabase.update("sync_device_public_keys", contentValues6, "device_id=?", new String[]{str});
                                    syncProtocolRepository.putState(writableDatabase, "authority_device_id", primaryDeviceId, strTimestamp$default);
                                    syncProtocolRepository.putState(writableDatabase, "local_device_id", str, strTimestamp$default);
                                    syncProtocolRepository.putState(writableDatabase, "local_device_role", "SECONDARY", strTimestamp$default);
                                    syncProtocolRepository.putState(writableDatabase, "store_id", storeId, strTimestamp$default);
                                    StoreTime.INSTANCE.setZoneId(writableDatabase, storeTimeZoneId, strTimestamp$default);
                                    if (syncProtocolRepository.state(writableDatabase, "secondary_next_sequence") == null) {
                                        syncProtocolRepository.putState(writableDatabase, "secondary_next_sequence", "1", strTimestamp$default);
                                    }
                                    SyncCredentialVault syncCredentialVault = syncProtocolRepository.credentialVault;
                                    Intrinsics.checkNotNull(bArr2);
                                    try {
                                        try {
                                            syncCredentialVault.save(str, bArr2);
                                            try {
                                                bArr = bArr2;
                                                try {
                                                    try {
                                                        audit(writableDatabase, actorUserId, "SYNC_SECONDARY_CONFIGURED", str, null, new JSONObject().put("primary_device_id", primaryDeviceId).put("store_id", storeId).put("display_name", StringsKt.trim((CharSequence) displayName).toString()).toString(), strTimestamp$default);
                                                        writableDatabase.setTransactionSuccessful();
                                                        writableDatabase.endTransaction();
                                                        ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
                                                    } catch (Exception e) {
                                                        e = e;
                                                        z = true;
                                                        if (z) {
                                                            syncProtocolRepository.credentialVault.remove(str);
                                                        }
                                                        throw e;
                                                    }
                                                } catch (Throwable th3) {
                                                    th = th3;
                                                    writableDatabase.endTransaction();
                                                    Intrinsics.checkNotNull(bArr);
                                                    ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
                                                    throw th;
                                                }
                                            } catch (Exception e2) {
                                                e = e2;
                                                bArr = bArr2;
                                            }
                                        } catch (Exception e3) {
                                            e = e3;
                                            bArr = bArr2;
                                            z = false;
                                            if (z) {
                                                syncProtocolRepository.credentialVault.remove(str);
                                            }
                                            throw e;
                                        }
                                    } catch (Throwable th4) {
                                        th = th4;
                                        bArr = bArr2;
                                        writableDatabase.endTransaction();
                                        Intrinsics.checkNotNull(bArr);
                                        ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
                                        throw th;
                                    }
                                } catch (Exception e4) {
                                    e = e4;
                                    bArr = bArr2;
                                    z = false;
                                    if (z) {
                                        syncProtocolRepository.credentialVault.remove(str);
                                    }
                                    throw e;
                                }
                            } catch (Exception e5) {
                                e = e5;
                                syncProtocolRepository = this;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            bArr = bArr2;
                            writableDatabase.endTransaction();
                            Intrinsics.checkNotNull(bArr);
                            ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
                            throw th;
                        }
                    } catch (Exception e6) {
                        e = e6;
                        syncProtocolRepository = this;
                        bArr = bArr2;
                        str = orCreate;
                        z = false;
                        if (z) {
                            syncProtocolRepository.credentialVault.remove(str);
                        }
                        throw e;
                    }
                } catch (Exception e7) {
                    e = e7;
                }
            } catch (Throwable th6) {
                th = th6;
            }
        } catch (Throwable th7) {
            try {
                throw th7;
            } catch (Throwable th8) {
                CloseableKt.closeFinally(cursorRawQuery, th7);
                throw th8;
            }
        }
    }

    private final void clearUncommittedLocalServiceWalletSeedsForSecondary(SQLiteDatabase db, String actorUserId, String now) throws IOException {
        ArrayList<Triple> arrayList = new ArrayList();
        Cursor cursorRawQuery = db.rawQuery("SELECT l.service_key,l.account_id,a.opening_balance_minor\n               FROM service_wallet_account_links l\n               JOIN cash_accounts a ON a.id=l.account_id", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            while (cursor.moveToNext()) {
                arrayList.add(new Triple(cursor.getString(0), cursor.getString(1), Long.valueOf(cursor.getLong(2))));
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            for (Triple triple : arrayList) {
                String str = (String) triple.component1();
                String str2 = (String) triple.component2();
                long jLongValue = ((Number) triple.component3()).longValue();
                if (!SetsKt.setOf((Object[]) new String[]{ServiceTransferRepository.SYRIATEL_SHARED, "SHAM_CASH"}).contains(str)) {
                    throw new IllegalArgumentException("\u062a\u0648\u062c\u062f \u0645\u062d\u0641\u0638\u0629 \u062e\u062f\u0645\u0629 \u0645\u062d\u0644\u064a\u0629 \u063a\u064a\u0631 \u0645\u0639\u0631\u0648\u0641\u0629\u061b \u0623\u0648\u0642\u0641 \u0627\u0644\u0631\u0628\u0637 \u0648\u0631\u0627\u062c\u0639 \u0625\u0639\u062f\u0627\u062f\u0627\u062a\u0647\u0627 \u0623\u0648\u0644\u0627\u064b".toString());
                }
                Cursor cursorRawQuery2 = db.rawQuery("SELECT 1 FROM cash_accounts WHERE id=? AND account_type='WALLET' AND currency_code='SYP' AND is_active=1", new String[]{str2});
                try {
                    boolean zMoveToFirst = cursorRawQuery2.moveToFirst();
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    Cursor cursorRawQuery3 = db.rawQuery("SELECT 1 FROM cash_transactions WHERE account_id=? LIMIT 1", new String[]{str2});
                    try {
                        boolean zMoveToFirst2 = cursorRawQuery3.moveToFirst();
                        CloseableKt.closeFinally(cursorRawQuery3, null);
                        Cursor cursorRawQuery4 = db.rawQuery("SELECT 1 FROM service_wallet_transactions WHERE service_key=? LIMIT 1", new String[]{str});
                        try {
                            boolean zMoveToFirst3 = cursorRawQuery4.moveToFirst();
                            CloseableKt.closeFinally(cursorRawQuery4, null);
                            Cursor cursorRawQuery5 = db.rawQuery("SELECT 1 FROM service_transfers WHERE service_key=? LIMIT 1", new String[]{str});
                            try {
                                boolean zMoveToFirst4 = cursorRawQuery5.moveToFirst();
                                CloseableKt.closeFinally(cursorRawQuery5, null);
                                Cursor cursorRawQuery6 = db.rawQuery("SELECT 1 FROM cash_account_transfers WHERE source_account_id=? OR destination_account_id=? LIMIT 1", new String[]{str2, str2});
                                try {
                                    boolean zMoveToFirst5 = cursorRawQuery6.moveToFirst();
                                    CloseableKt.closeFinally(cursorRawQuery6, null);
                                    Cursor cursorRawQuery7 = db.rawQuery("SELECT 1 FROM system_cash_account_roles WHERE account_id=? LIMIT 1", new String[]{str2});
                                    try {
                                        boolean zMoveToFirst6 = cursorRawQuery7.moveToFirst();
                                        CloseableKt.closeFinally(cursorRawQuery7, null);
                                        Cursor cursorRawQuery8 = db.rawQuery("SELECT 1 FROM daily_cash_sessions WHERE primary_cash_account_id=? LIMIT 1", new String[]{str2});
                                        try {
                                            boolean zMoveToFirst7 = cursorRawQuery8.moveToFirst();
                                            CloseableKt.closeFinally(cursorRawQuery8, null);
                                            if (!zMoveToFirst || jLongValue != 0 || zMoveToFirst2 || zMoveToFirst3 || zMoveToFirst4 || zMoveToFirst5 || zMoveToFirst6 || zMoveToFirst7) {
                                                throw new IllegalArgumentException("\u062a\u0648\u062c\u062f \u0623\u0631\u0635\u062f\u0629 \u0623\u0648 \u062d\u0631\u0643\u0627\u062a \u0623\u0648 \u0627\u0631\u062a\u0628\u0627\u0637\u0627\u062a \u0645\u062d\u0644\u064a\u0629 \u0641\u064a \u0645\u062d\u0641\u0638\u0629 \u062e\u062f\u0645\u0629 \u0639\u0644\u0649 \u0647\u0630\u0627 \u0627\u0644\u0647\u0627\u062a\u0641. \u0644\u0627 \u064a\u0645\u0643\u0646 \u0631\u0628\u0637\u0647 \u0643\u062b\u0627\u0646\u0648\u064a \u0642\u0628\u0644 \u0645\u0637\u0627\u0628\u0642\u0629 \u0647\u0630\u0647 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a.".toString());
                                            }
                                            db.delete("wallet_daily_openings", "account_id=?", new String[]{str2});
                                            db.delete("service_wallet_account_links", "service_key=?", new String[]{str});
                                            db.delete("service_wallets", "service_key=?", new String[]{str});
                                            if (db.delete("cash_accounts", "id=?", new String[]{str2}) != 1) {
                                                throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u0625\u0632\u0627\u0644\u0629 \u062d\u0633\u0627\u0628 \u0645\u062d\u0641\u0638\u0629 \u0645\u062d\u0644\u064a \u0641\u0627\u0631\u063a \u0642\u0628\u0644 \u0631\u0628\u0637 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a".toString());
                                            }
                                            audit(db, actorUserId, "SECONDARY_EMPTY_SERVICE_WALLET_RESET", str2, new JSONObject().put("service_key", str).put("account_id", str2).toString(), new JSONObject().put("removed_before_primary_mirror", true).toString(), now);
                                        } catch (Throwable th) {
                                            try {
                                                throw th;
                                            } catch (Throwable th2) {
                                                CloseableKt.closeFinally(cursorRawQuery8, th);
                                                throw th2;
                                            }
                                        }
                                    } catch (Throwable th3) {
                                        try {
                                            throw th3;
                                        } catch (Throwable th4) {
                                            CloseableKt.closeFinally(cursorRawQuery7, th3);
                                            throw th4;
                                        }
                                    }
                                } catch (Throwable th5) {
                                    try {
                                        throw th5;
                                    } catch (Throwable th6) {
                                        CloseableKt.closeFinally(cursorRawQuery6, th5);
                                        throw th6;
                                    }
                                }
                            } catch (Throwable th7) {
                                try {
                                    throw th7;
                                } catch (Throwable th8) {
                                    CloseableKt.closeFinally(cursorRawQuery5, th7);
                                    throw th8;
                                }
                            }
                        } catch (Throwable th9) {
                            try {
                                throw th9;
                            } catch (Throwable th10) {
                                CloseableKt.closeFinally(cursorRawQuery4, th9);
                                throw th10;
                            }
                        }
                    } catch (Throwable th11) {
                        try {
                            throw th11;
                        } catch (Throwable th12) {
                            CloseableKt.closeFinally(cursorRawQuery3, th11);
                            throw th12;
                        }
                    }
                } catch (Throwable th13) {
                    try {
                        throw th13;
                    } catch (Throwable th14) {
                        CloseableKt.closeFinally(cursorRawQuery2, th13);
                        throw th14;
                    }
                }
            }
        } catch (Throwable th15) {
            try {
                throw th15;
            } catch (Throwable th16) {
                CloseableKt.closeFinally(cursorRawQuery, th15);
                throw th16;
            }
        }
    }

    private final void storeDevicePublicKey(SQLiteDatabase db, String deviceId, String encodedBase64, String now) throws IOException {
        Object objM381constructorimpl;
        Object objM381constructorimpl2;
        try {
            Result.Companion companion = Result.INSTANCE;
            SyncProtocolRepository syncProtocolRepository = this;
            objM381constructorimpl = Result.m381constructorimpl(Base64.decode(encodedBase64, 0));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m384exceptionOrNullimpl(objM381constructorimpl) != null) {
            throw new IllegalArgumentException("\u0627\u0644\u0645\u0641\u062a\u0627\u062d \u0627\u0644\u0639\u0627\u0645 \u0644\u0644\u062c\u0647\u0627\u0632 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d");
        }
        byte[] bArr = (byte[]) objM381constructorimpl;
        int length = bArr.length;
        if (64 > length || length >= 257) {
            throw new IllegalArgumentException("\u062d\u062c\u0645 \u0627\u0644\u0645\u0641\u062a\u0627\u062d \u0627\u0644\u0639\u0627\u0645 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
        }
        try {
            Result.Companion companion3 = Result.INSTANCE;
            SyncProtocolRepository syncProtocolRepository2 = this;
            objM381constructorimpl2 = Result.m381constructorimpl(KeyFactory.getInstance("EC").generatePublic(new X509EncodedKeySpec(bArr)));
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            objM381constructorimpl2 = Result.m381constructorimpl(ResultKt.createFailure(th2));
        }
        if (Result.m384exceptionOrNullimpl(objM381constructorimpl2) == null) {
            if (!StringsKt.equals(((PublicKey) objM381constructorimpl2).getAlgorithm(), "EC", true)) {
                throw new IllegalArgumentException("\u062e\u0648\u0627\u0631\u0632\u0645\u064a\u0629 \u0647\u0648\u064a\u0629 \u0627\u0644\u062c\u0647\u0627\u0632 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u0629".toString());
            }
            SyncDeviceAuthenticator syncDeviceAuthenticator = SyncDeviceAuthenticator.INSTANCE;
            Intrinsics.checkNotNull(bArr);
            String strFingerprint = syncDeviceAuthenticator.fingerprint(bArr);
            Cursor cursorRawQuery = db.rawQuery("SELECT public_key_fingerprint FROM sync_device_public_keys WHERE device_id=?", new String[]{deviceId});
            try {
                Cursor cursor = cursorRawQuery;
                String string = cursor.moveToFirst() ? cursor.getString(0) : null;
                CloseableKt.closeFinally(cursorRawQuery, null);
                if (string != null && !Intrinsics.areEqual(string, strFingerprint)) {
                    throw new IllegalArgumentException("\u062a\u063a\u064a\u064a\u0631 \u0645\u0641\u062a\u0627\u062d \u062c\u0647\u0627\u0632 \u0645\u0633\u062c\u0644 \u064a\u062a\u0637\u0644\u0628 \u0625\u0644\u063a\u0627\u0621 \u0627\u0644\u0627\u0642\u062a\u0631\u0627\u0646 \u0648\u0625\u0639\u0627\u062f\u0629 \u062a\u0633\u062c\u064a\u0644\u0647".toString());
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("device_id", deviceId);
                contentValues.put("public_key_base64", Base64.encodeToString(bArr, 2));
                contentValues.put("public_key_fingerprint", strFingerprint);
                contentValues.put("algorithm", "EC_P256_SHA256");
                contentValues.put(NotificationCompat.CATEGORY_STATUS, "ACTIVE");
                contentValues.put("enrolled_at", now);
                contentValues.putNull("revoked_at");
                Unit unit = Unit.INSTANCE;
                db.insertWithOnConflict("sync_device_public_keys", null, contentValues, 5);
                return;
            } catch (Throwable th3) {
                try {
                    throw th3;
                } catch (Throwable th4) {
                    CloseableKt.closeFinally(cursorRawQuery, th3);
                    throw th4;
                }
            }
        }
        throw new IllegalArgumentException("\u0635\u064a\u063a\u0629 \u0627\u0644\u0645\u0641\u062a\u0627\u062d \u0627\u0644\u0639\u0627\u0645 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u0629");
    }

    public final byte[] trustedPeerPublicKey(String deviceId) throws IOException {
        Object objM381constructorimpl;
        byte[] bArr;
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        if (StringsKt.isBlank(deviceId)) {
            return null;
        }
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        Cursor cursorRawQuery = readableDatabase.rawQuery("SELECT 1 FROM sync_devices WHERE device_id=? AND status='ACTIVE'", new String[]{deviceId});
        try {
            boolean zMoveToFirst = cursorRawQuery.moveToFirst();
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (!zMoveToFirst) {
                return null;
            }
            Cursor cursorRawQuery2 = readableDatabase.rawQuery("SELECT public_key_base64 FROM sync_device_public_keys WHERE device_id=? AND status='ACTIVE'", new String[]{deviceId});
            try {
                Cursor cursor = cursorRawQuery2;
                if (cursor.moveToFirst()) {
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        SyncProtocolRepository syncProtocolRepository = this;
                        objM381constructorimpl = Result.m381constructorimpl(Base64.decode(cursor.getString(0), 0));
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m387isFailureimpl(objM381constructorimpl)) {
                        objM381constructorimpl = null;
                    }
                    bArr = (byte[]) objM381constructorimpl;
                } else {
                    bArr = null;
                }
                CloseableKt.closeFinally(cursorRawQuery2, null);
                return bArr;
            } catch (Throwable th2) {
                try {
                    throw th2;
                } catch (Throwable th3) {
                    CloseableKt.closeFinally(cursorRawQuery2, th2);
                    throw th3;
                }
            }
        } catch (Throwable th4) {
            try {
                throw th4;
            } catch (Throwable th5) {
                CloseableKt.closeFinally(cursorRawQuery, th4);
                throw th5;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final byte[] secondaryCredential() {
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        if (Intrinsics.areEqual(state(readableDatabase, "local_device_role"), "SECONDARY")) {
            return this.credentialVault.load(SyncDeviceIdentity.INSTANCE.getOrCreate(this.context));
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isConfiguredSecondary() throws IOException {
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        if (Intrinsics.areEqual(state(readableDatabase, "local_device_role"), "SECONDARY")) {
            return true;
        }
        String strState = state(readableDatabase, "local_device_id");
        if (strState == null) {
            return false;
        }
        Cursor cursorRawQuery = readableDatabase.rawQuery("SELECT device_role FROM sync_devices WHERE device_id=?", new String[]{strState});
        try {
            Cursor cursor = cursorRawQuery;
            String string = cursor.moveToFirst() ? cursor.getString(0) : null;
            CloseableKt.closeFinally(cursorRawQuery, null);
            return Intrinsics.areEqual(string, "SECONDARY");
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String primaryRelayTrustRootJson() throws JSONException, IOException {
        if (!Intrinsics.areEqual(localDeviceRole(), "PRIMARY") || !Intrinsics.areEqual(authorityDeviceId(), localDeviceId())) {
            throw new IllegalArgumentException("\u062c\u0630\u0631 \u0627\u0644\u062b\u0642\u0629 \u0645\u062a\u0627\u062d \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0641\u0642\u0637".toString());
        }
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        String strState = state(readableDatabase, "store_id");
        if (strState == null) {
            throw new IllegalStateException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0645\u062a\u062c\u0631 \u063a\u064a\u0631 \u0645\u0647\u064a\u0623".toString());
        }
        String string = new JSONObject().put(strState, new JSONObject().put("device_id", localDeviceId()).put("public_key", Base64.encodeToString(SyncDeviceAuthenticator.INSTANCE.getOrCreate(this.context, localDeviceId()).getPublic().getEncoded(), 2))).toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public final String deviceCertificateForRelay(String deviceId) throws IOException {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        Cursor cursorRawQuery = readableDatabase.rawQuery("SELECT k.certificate_json FROM sync_device_public_keys k\n               JOIN sync_devices d ON d.device_id=k.device_id\n               WHERE k.device_id=? AND k.status='ACTIVE' AND d.status='ACTIVE'", new String[]{deviceId});
        try {
            Cursor cursor = cursorRawQuery;
            String string = (!cursor.moveToFirst() || cursor.isNull(0)) ? null : cursor.getString(0);
            CloseableKt.closeFinally(cursorRawQuery, null);
            return string;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    public final SyncRelayDeviceAuthentication relayAuthentication() throws JSONException, IOException {
        String strSerial;
        String strLocalDeviceId = localDeviceId();
        String strLocalDeviceRole = localDeviceRole();
        KeyPair orCreate = SyncDeviceAuthenticator.INSTANCE.getOrCreate(this.context, strLocalDeviceId);
        if (Intrinsics.areEqual(strLocalDeviceRole, "PRIMARY") && Intrinsics.areEqual(authorityDeviceId(), strLocalDeviceId)) {
            strSerial = "ROOT";
        } else {
            if (!Intrinsics.areEqual(strLocalDeviceRole, "SECONDARY") || Intrinsics.areEqual(strLocalDeviceId, authorityDeviceId())) {
                throw new IllegalArgumentException("\u062f\u0648\u0631 \u0627\u0644\u062c\u0647\u0627\u0632 \u063a\u064a\u0631 \u0645\u0647\u064a\u0623 \u0644\u0644\u0645\u0632\u0627\u0645\u0646\u0629".toString());
            }
            String strDeviceCertificateForRelay = deviceCertificateForRelay(strLocalDeviceId);
            if (strDeviceCertificateForRelay == null) {
                throw new IllegalStateException("\u0634\u0647\u0627\u062f\u0629 \u0627\u0644\u062c\u0647\u0627\u0632 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629 \u0623\u0648 \u0645\u0644\u063a\u0627\u0629");
            }
            strSerial = SyncDeviceCertificate.INSTANCE.serial(strDeviceCertificateForRelay);
        }
        PrivateKey privateKey = orCreate.getPrivate();
        Intrinsics.checkNotNullExpressionValue(privateKey, "getPrivate(...)");
        return new SyncRelayDeviceAuthentication(strSerial, privateKey);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SyncRelayRegistrySyncResult syncDeviceRegistry$default(SyncProtocolRepository syncProtocolRepository, String str, SyncRelayRegistryClient syncRelayRegistryClient, int i, Object obj) {
        int i2 = 2;
        if ((i & 2) != 0) {
            syncRelayRegistryClient = new SyncRelayRegistryClient(str, null, i2, 0 == true ? 1 : 0);
        }
        return syncProtocolRepository.syncDeviceRegistry(str, syncRelayRegistryClient);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v19, types: [java.lang.String[], java.lang.Throwable] */
    public final SyncRelayRegistrySyncResult syncDeviceRegistry(String relayBaseUrl, SyncRelayRegistryClient registry) throws IOException {
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(relayBaseUrl, "relayBaseUrl");
        Intrinsics.checkNotNullParameter(registry, "registry");
        String strLocalDeviceId = localDeviceId();
        if (!Intrinsics.areEqual(localDeviceRole(), "PRIMARY") || !Intrinsics.areEqual(authorityDeviceId(), strLocalDeviceId)) {
            throw new IllegalArgumentException("\u0625\u062f\u0627\u0631\u0629 \u0633\u062c\u0644 \u0634\u0647\u0627\u062f\u0627\u062a \u0627\u0644\u0648\u0633\u064a\u0637 \u0645\u062a\u0627\u062d\u0629 \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0641\u0642\u0637".toString());
        }
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        String strState = state(writableDatabase, "store_id");
        if (strState == null) {
            throw new IllegalStateException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0645\u062a\u062c\u0631 \u063a\u064a\u0631 \u0645\u0647\u064a\u0623".toString());
        }
        KeyPair orCreate = SyncDeviceAuthenticator.INSTANCE.getOrCreate(this.context, strLocalDeviceId);
        SyncRelayRegistryClient.TrustRoot trustRootFetchTrustRoot = registry.fetchTrustRoot(strState);
        if (Intrinsics.areEqual(trustRootFetchTrustRoot.getDeviceId(), strLocalDeviceId)) {
            SyncDeviceAuthenticator syncDeviceAuthenticator = SyncDeviceAuthenticator.INSTANCE;
            byte[] bArrDecode = Base64.decode(trustRootFetchTrustRoot.getPublicKeyBase64(), 0);
            Intrinsics.checkNotNullExpressionValue(bArrDecode, "decode(...)");
            String strFingerprint = syncDeviceAuthenticator.fingerprint(bArrDecode);
            SyncDeviceAuthenticator syncDeviceAuthenticator2 = SyncDeviceAuthenticator.INSTANCE;
            byte[] encoded = orCreate.getPublic().getEncoded();
            Intrinsics.checkNotNullExpressionValue(encoded, "getEncoded(...)");
            if (Intrinsics.areEqual(strFingerprint, syncDeviceAuthenticator2.fingerprint(encoded))) {
                ArrayList arrayList = new ArrayList();
                Object obj = null;
                Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT device_id,certificate_serial,revocation_json\n               FROM sync_device_revocations WHERE status='PENDING'\n               ORDER BY created_at", (String[]) null);
                try {
                    Cursor cursor = cursorRawQuery;
                    while (true) {
                        i = 1;
                        if (!cursor.moveToNext()) {
                            break;
                        }
                        arrayList.add(new Triple(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursorRawQuery, null);
                    Iterator it = arrayList.iterator();
                    int i3 = 0;
                    while (it.hasNext()) {
                        Triple triple = (Triple) it.next();
                        String str = (String) triple.component1();
                        String str2 = (String) triple.component2();
                        SyncDeviceCertificate syncDeviceCertificate = SyncDeviceCertificate.INSTANCE;
                        PrivateKey privateKey = orCreate.getPrivate();
                        Intrinsics.checkNotNullExpressionValue(privateKey, "getPrivate(...)");
                        int i4 = i3;
                        Iterator it2 = it;
                        Object obj2 = obj;
                        String strSignedRevocation$default = SyncDeviceCertificate.signedRevocation$default(syncDeviceCertificate, strState, str, str2, privateKey, 0L, 16, null);
                        if (registry.revokeCertificate(strState, str, strSignedRevocation$default)) {
                            writableDatabase.beginTransaction();
                            try {
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("revocation_json", strSignedRevocation$default);
                                contentValues.put(NotificationCompat.CATEGORY_STATUS, "SYNCED");
                                i2 = 1;
                                contentValues.put("sent_at", timestamp$default(this, 0L, 1, obj2));
                                Unit unit2 = Unit.INSTANCE;
                                writableDatabase.update("sync_device_revocations", contentValues, "device_id=? AND certificate_serial=?", new String[]{str, str2});
                                writableDatabase.setTransactionSuccessful();
                                writableDatabase.endTransaction();
                                i3 = i4 + 1;
                            } catch (Throwable th) {
                                writableDatabase.endTransaction();
                                throw th;
                            }
                        } else {
                            i2 = 1;
                            i3 = i4;
                        }
                        obj = obj2;
                        i = i2;
                        it = it2;
                    }
                    int i5 = i3;
                    int i6 = i;
                    ?? r2 = obj;
                    ArrayList arrayList2 = new ArrayList();
                    Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT d.device_id\n               FROM sync_devices d\n               LEFT JOIN sync_device_public_keys k ON k.device_id=d.device_id\n                 AND k.status='ACTIVE' AND k.certificate_json IS NOT NULL\n               WHERE d.device_role='SECONDARY' AND d.status='ACTIVE'\n                 AND k.device_id IS NULL\n               ORDER BY d.created_at", (String[]) r2);
                    try {
                        Cursor cursor2 = cursorRawQuery2;
                        while (cursor2.moveToNext()) {
                            String string = cursor2.getString(0);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                            arrayList2.add(string);
                        }
                        Unit unit3 = Unit.INSTANCE;
                        CloseableKt.closeFinally(cursorRawQuery2, r2);
                        if (!arrayList2.isEmpty()) {
                            throw new IllegalArgumentException(("\u062a\u0648\u062c\u062f \u0623\u062c\u0647\u0632\u0629 \u062b\u0627\u0646\u0648\u064a\u0629 \u0646\u0634\u0637\u0629 \u0628\u0644\u0627 \u0634\u0647\u0627\u062f\u0629 \u0635\u0627\u0644\u062d\u0629 \u0641\u064a \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0631\u0626\u064a\u0633\u064a: " + CollectionsKt.joinToString$default(arrayList2, null, null, null, 0, null, null, 63, null) + ". \u0644\u0646 \u0646\u0641\u062a\u062d \u0627\u062a\u0635\u0627\u0644\u0627\u064b \u063a\u064a\u0631 \u0645\u0648\u062b\u0651\u0642.").toString());
                        }
                        ArrayList<Pair> arrayList3 = new ArrayList();
                        Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT d.device_id,k.certificate_json\n               FROM sync_devices d JOIN sync_device_public_keys k ON k.device_id=d.device_id\n               WHERE d.device_role='SECONDARY' AND d.status='ACTIVE'\n                 AND k.status='ACTIVE' AND k.certificate_json IS NOT NULL\n               ORDER BY d.created_at", (String[]) r2);
                        try {
                            Cursor cursor3 = cursorRawQuery3;
                            while (cursor3.moveToNext()) {
                                arrayList3.add(TuplesKt.to(cursor3.getString(0), cursor3.getString(i6)));
                            }
                            int i7 = 0;
                            Unit unit4 = Unit.INSTANCE;
                            CloseableKt.closeFinally(cursorRawQuery3, r2);
                            for (Pair pair : arrayList3) {
                                if (registry.registerCertificate(strState, (String) pair.component1(), (String) pair.component2())) {
                                    i7++;
                                }
                            }
                            return new SyncRelayRegistrySyncResult(i7, i5);
                        } catch (Throwable th2) {
                            try {
                                throw th2;
                            } catch (Throwable th3) {
                                CloseableKt.closeFinally(cursorRawQuery3, th2);
                                throw th3;
                            }
                        }
                    } catch (Throwable th4) {
                        try {
                            throw th4;
                        } catch (Throwable th5) {
                            CloseableKt.closeFinally(cursorRawQuery2, th4);
                            throw th5;
                        }
                    }
                } catch (Throwable th6) {
                    try {
                        throw th6;
                    } catch (Throwable th7) {
                        CloseableKt.closeFinally(cursorRawQuery, th6);
                        throw th7;
                    }
                }
            }
        }
        throw new IllegalArgumentException("\u062c\u0630\u0631 \u0627\u0644\u062b\u0642\u0629 \u0627\u0644\u0645\u062b\u0628\u062a \u0641\u064a \u0627\u0644\u0648\u0633\u064a\u0637 \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u0647\u0648\u064a\u0629 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a\u061b \u0623\u0648\u0642\u0641\u0646\u0627 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u0627\u0644\u0622\u0645\u0646\u0629".toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void configureAsSecondaryFromRelay(String relayBaseUrl, String actorUserId, String storeId, String primaryDeviceId, String credential, String displayName, String deviceCertificateJson, String storeTimeZoneId) throws Throwable {
        Intrinsics.checkNotNullParameter(relayBaseUrl, "relayBaseUrl");
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(storeId, "storeId");
        Intrinsics.checkNotNullParameter(primaryDeviceId, "primaryDeviceId");
        Intrinsics.checkNotNullParameter(credential, "credential");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        Intrinsics.checkNotNullParameter(deviceCertificateJson, "deviceCertificateJson");
        Intrinsics.checkNotNullParameter(storeTimeZoneId, "storeTimeZoneId");
        SyncRelayRegistryClient.TrustRoot trustRootFetchTrustRoot = new SyncRelayRegistryClient(relayBaseUrl, null, 2, 0 == true ? 1 : 0).fetchTrustRoot(storeId);
        if (!Intrinsics.areEqual(trustRootFetchTrustRoot.getDeviceId(), primaryDeviceId) || !Intrinsics.areEqual(trustRootFetchTrustRoot.getAlgorithm(), "EC_P256_SHA256")) {
            throw new IllegalArgumentException("\u0647\u0648\u064a\u0629 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0644\u0627 \u062a\u0637\u0627\u0628\u0642 \u062c\u0630\u0631 \u0627\u0644\u062b\u0642\u0629 \u0627\u0644\u0645\u0646\u0634\u0648\u0631 \u0644\u062f\u0649 \u0648\u0633\u064a\u0637 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629".toString());
        }
        configureAsSecondary(actorUserId, storeId, primaryDeviceId, credential, displayName, storeTimeZoneId, trustRootFetchTrustRoot.getPublicKeyBase64(), deviceCertificateJson);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final AuthenticatedSyncEnvelope authenticatedOutbox(String operationId, String recipientDeviceId) throws IOException {
        byte[] bArrLoad;
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        Intrinsics.checkNotNullParameter(recipientDeviceId, "recipientDeviceId");
        if (StringsKt.isBlank(operationId) || StringsKt.isBlank(recipientDeviceId)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        String orCreate = SyncDeviceIdentity.INSTANCE.getOrCreate(this.context);
        String strState = state(readableDatabase, "local_device_role");
        Cursor cursorRawQuery = readableDatabase.rawQuery("SELECT origin_device_id FROM sync_operation_envelopes WHERE operation_id=?", new String[]{operationId});
        try {
            Cursor cursor = cursorRawQuery;
            if (!cursor.moveToFirst()) {
                throw new IllegalArgumentException("\u0639\u0645\u0644\u064a\u0629 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629".toString());
            }
            String string = cursor.getString(0);
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (Intrinsics.areEqual(strState, "SECONDARY") && Intrinsics.areEqual(string, orCreate)) {
                if (!Intrinsics.areEqual(recipientDeviceId, state(readableDatabase, "authority_device_id"))) {
                    throw new IllegalArgumentException("\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u064a\u0631\u0633\u0644 \u0623\u0648\u0627\u0645\u0631\u0647 \u0625\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0627\u0644\u0645\u0639\u062a\u0645\u062f \u0641\u0642\u0637".toString());
                }
                bArrLoad = this.credentialVault.load(orCreate);
                if (bArrLoad == null) {
                    throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u063a\u064a\u0631 \u0645\u062a\u0648\u0641\u0631\u0629");
                }
            } else if (Intrinsics.areEqual(strState, "PRIMARY") && Intrinsics.areEqual(string, orCreate)) {
                Cursor cursorRawQuery2 = readableDatabase.rawQuery("SELECT 1 FROM sync_devices WHERE device_id=? AND device_role='SECONDARY' AND status='ACTIVE'", new String[]{recipientDeviceId});
                try {
                    boolean zMoveToFirst = cursorRawQuery2.moveToFirst();
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    if (!zMoveToFirst) {
                        throw new IllegalArgumentException("\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0633\u062a\u0644\u0645 \u063a\u064a\u0631 \u0645\u0633\u062c\u0644 \u0623\u0648 \u063a\u064a\u0631 \u0645\u0641\u0639\u0644".toString());
                    }
                    bArrLoad = this.credentialVault.load(recipientDeviceId);
                    if (bArrLoad == null) {
                        throw new IllegalArgumentException("\u0644\u0627 \u062a\u0648\u062c\u062f \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0639\u062a\u0645\u0627\u062f \u0622\u0645\u0646\u0629 \u0644\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0633\u062a\u0644\u0645");
                    }
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(cursorRawQuery2, th);
                        throw th2;
                    }
                }
            } else {
                throw new IllegalArgumentException("\u0645\u0635\u062f\u0631 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u062f\u0648\u0631 \u0647\u0630\u0627 \u0627\u0644\u062c\u0647\u0627\u0632");
            }
            try {
                String strState2 = state(readableDatabase, "store_id");
                if (strState2 == null) {
                    throw new IllegalStateException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0645\u062a\u062c\u0631 \u063a\u064a\u0631 \u0645\u0647\u064a\u0623".toString());
                }
                Cursor cursorRawQuery3 = readableDatabase.rawQuery("SELECT operation_id,sequence_number,origin_device_id,actor_user_id,\n                          operation_type,entity_type,entity_id,payload_json,created_at\n                   FROM sync_operation_envelopes WHERE operation_id=?", new String[]{operationId});
                try {
                    Cursor cursor2 = cursorRawQuery3;
                    if (!cursor2.moveToFirst()) {
                        throw new IllegalArgumentException("\u0639\u0645\u0644\u064a\u0629 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629".toString());
                    }
                    SyncEnvelopeSecurity syncEnvelopeSecurity = SyncEnvelopeSecurity.INSTANCE;
                    String string2 = cursor2.getString(0);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    long j = cursor2.getLong(1);
                    String string3 = cursor2.getString(2);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                    String string4 = cursor2.getString(3);
                    Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                    String string5 = cursor2.getString(4);
                    Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                    String string6 = cursor2.getString(5);
                    Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                    String string7 = cursor2.getString(6);
                    Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
                    String string8 = cursor2.getString(7);
                    Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
                    String string9 = cursor2.getString(8);
                    Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
                    AuthenticatedSyncEnvelope authenticatedSyncEnvelopeCreate = syncEnvelopeSecurity.create(strState2, string2, j, string3, string4, string5, string6, string7, 0L, string8, string9, bArrLoad);
                    CloseableKt.closeFinally(cursorRawQuery3, null);
                    ArraysKt.fill$default(bArrLoad, (byte) 0, 0, 0, 6, (Object) null);
                    return authenticatedSyncEnvelopeCreate;
                } catch (Throwable th3) {
                    try {
                        throw th3;
                    } catch (Throwable th4) {
                        CloseableKt.closeFinally(cursorRawQuery3, th3);
                        throw th4;
                    }
                }
            } catch (Throwable th5) {
                ArraysKt.fill$default(bArrLoad, (byte) 0, 0, 0, 6, (Object) null);
                throw th5;
            }
        } catch (Throwable th6) {
            try {
                throw th6;
            } catch (Throwable th7) {
                CloseableKt.closeFinally(cursorRawQuery, th6);
                throw th7;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String encryptedAuthenticatedOutbox(String operationId, String recipientDeviceId) throws IOException {
        byte[] bArrLoad;
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        Intrinsics.checkNotNullParameter(recipientDeviceId, "recipientDeviceId");
        AuthenticatedSyncEnvelope authenticatedSyncEnvelopeAuthenticatedOutbox = authenticatedOutbox(operationId, recipientDeviceId);
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        String orCreate = SyncDeviceIdentity.INSTANCE.getOrCreate(this.context);
        String strState = state(readableDatabase, "local_device_role");
        if (Intrinsics.areEqual(strState, "SECONDARY") && Intrinsics.areEqual(authenticatedSyncEnvelopeAuthenticatedOutbox.getOriginDeviceId(), orCreate)) {
            if (!Intrinsics.areEqual(recipientDeviceId, state(readableDatabase, "authority_device_id"))) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            bArrLoad = this.credentialVault.load(orCreate);
            if (bArrLoad == null) {
                throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u063a\u064a\u0631 \u0645\u062a\u0648\u0641\u0631\u0629");
            }
        } else if (Intrinsics.areEqual(strState, "PRIMARY") && Intrinsics.areEqual(authenticatedSyncEnvelopeAuthenticatedOutbox.getOriginDeviceId(), orCreate)) {
            Cursor cursorRawQuery = readableDatabase.rawQuery("SELECT 1 FROM sync_devices WHERE device_id=? AND device_role='SECONDARY' AND status='ACTIVE'", new String[]{recipientDeviceId});
            try {
                boolean zMoveToFirst = cursorRawQuery.moveToFirst();
                CloseableKt.closeFinally(cursorRawQuery, null);
                if (!zMoveToFirst) {
                    throw new IllegalArgumentException("\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0633\u062a\u0644\u0645 \u063a\u064a\u0631 \u0645\u0633\u062c\u0644 \u0623\u0648 \u0645\u0648\u0642\u0648\u0641".toString());
                }
                bArrLoad = this.credentialVault.load(recipientDeviceId);
                if (bArrLoad == null) {
                    throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0633\u062a\u0644\u0645 \u063a\u064a\u0631 \u0645\u062a\u0648\u0641\u0631\u0629");
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(cursorRawQuery, th);
                    throw th2;
                }
            }
        } else {
            throw new IllegalArgumentException("\u0627\u062a\u062c\u0627\u0647 \u0625\u0631\u0633\u0627\u0644 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d");
        }
        byte[] bArr = bArrLoad;
        try {
            SyncEnvelopeCipher.PeerContext peerContext = new SyncEnvelopeCipher.PeerContext(authenticatedSyncEnvelopeAuthenticatedOutbox.getStoreId(), authenticatedSyncEnvelopeAuthenticatedOutbox.getOriginDeviceId(), recipientDeviceId, authenticatedSyncEnvelopeAuthenticatedOutbox.getOperationId());
            SyncEnvelopeCipher syncEnvelopeCipher = SyncEnvelopeCipher.INSTANCE;
            byte[] bytes = SyncEnvelopeSecurity.INSTANCE.encode(authenticatedSyncEnvelopeAuthenticatedOutbox).getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            JSONObject jSONObjectPut = new JSONObject().put("frame_version", 1).put("store_id", authenticatedSyncEnvelopeAuthenticatedOutbox.getStoreId()).put("sender_device_id", orCreate).put("recipient_device_id", recipientDeviceId).put("operation_id", authenticatedSyncEnvelopeAuthenticatedOutbox.getOperationId()).put("ciphertext", Base64.encodeToString(syncEnvelopeCipher.encrypt(bytes, new SecretKeySpec(bArr, "AES"), peerContext), 11));
            KeyPair orCreate2 = SyncDeviceAuthenticator.INSTANCE.getOrCreate(this.context, orCreate);
            SyncDeviceAuthenticator.SigningContext signingContext = new SyncDeviceAuthenticator.SigningContext(authenticatedSyncEnvelopeAuthenticatedOutbox.getStoreId(), orCreate, recipientDeviceId, authenticatedSyncEnvelopeAuthenticatedOutbox.getOperationId());
            SyncDeviceAuthenticator syncDeviceAuthenticator = SyncDeviceAuthenticator.INSTANCE;
            PrivateKey privateKey = orCreate2.getPrivate();
            Intrinsics.checkNotNullExpressionValue(privateKey, "getPrivate(...)");
            String string = jSONObjectPut.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            byte[] bytes2 = string.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes2, "getBytes(...)");
            byte[] bArrSign = syncDeviceAuthenticator.sign(privateKey, signingContext, bytes2);
            JSONObject jSONObjectPut2 = jSONObjectPut.put("signature_algorithm", "ECDSA_P256_SHA256");
            SyncDeviceAuthenticator syncDeviceAuthenticator2 = SyncDeviceAuthenticator.INSTANCE;
            byte[] encoded = orCreate2.getPublic().getEncoded();
            Intrinsics.checkNotNullExpressionValue(encoded, "getEncoded(...)");
            jSONObjectPut2.put("sender_key_fingerprint", syncDeviceAuthenticator2.fingerprint(encoded)).put("signature", Base64.encodeToString(bArrSign, 2));
            String string2 = jSONObjectPut.toString();
            Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
            return string2;
        } finally {
            ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
        }
    }

    private final void verifyPeerFrameSignature(JSONObject frame, String storeId, String senderId, String recipientId, String operationId) throws IOException {
        if (!Intrinsics.areEqual(frame.optString("signature_algorithm"), "ECDSA_P256_SHA256")) {
            throw new IllegalArgumentException("\u062e\u0648\u0627\u0631\u0632\u0645\u064a\u0629 \u062a\u0648\u0642\u064a\u0639 \u0627\u0644\u062c\u0647\u0627\u0632 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u0629".toString());
        }
        byte[] bArrTrustedPeerPublicKey = trustedPeerPublicKey(senderId);
        if (bArrTrustedPeerPublicKey == null) {
            throw new IllegalArgumentException("\u0644\u0627 \u062a\u0648\u062c\u062f \u0647\u0648\u064a\u0629 \u0645\u0648\u062b\u0648\u0642\u0629 \u0645\u062b\u0628\u062a\u0629 \u0644\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0631\u0633\u0644");
        }
        if (!Intrinsics.areEqual(frame.getString("sender_key_fingerprint"), SyncDeviceAuthenticator.INSTANCE.fingerprint(bArrTrustedPeerPublicKey))) {
            throw new IllegalArgumentException("\u0628\u0635\u0645\u0629 \u0645\u0641\u062a\u0627\u062d \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0631\u0633\u0644 \u0644\u0627 \u062a\u0637\u0627\u0628\u0642 \u0627\u0644\u0645\u0641\u062a\u0627\u062d \u0627\u0644\u0645\u0633\u062c\u0644".toString());
        }
        byte[] bArrDecode = Base64.decode(frame.getString("signature"), 0);
        JSONObject jSONObject = new JSONObject(frame.toString());
        jSONObject.remove("signature");
        jSONObject.remove("signature_algorithm");
        jSONObject.remove("sender_key_fingerprint");
        SyncDeviceAuthenticator.SigningContext signingContext = new SyncDeviceAuthenticator.SigningContext(storeId, senderId, recipientId, operationId);
        SyncDeviceAuthenticator syncDeviceAuthenticator = SyncDeviceAuthenticator.INSTANCE;
        String string = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        byte[] bytes = string.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        Intrinsics.checkNotNull(bArrDecode);
        if (!syncDeviceAuthenticator.verify(bArrTrustedPeerPublicKey, signingContext, bytes, bArrDecode)) {
            throw new IllegalArgumentException("\u0641\u0634\u0644 \u0627\u0644\u062a\u062d\u0642\u0642 \u0645\u0646 \u062a\u0648\u0642\u064a\u0639 \u0647\u0648\u064a\u0629 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0631\u0633\u0644".toString());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final AuthenticatedSyncEnvelope verifyEncryptedIncomingFromSecondary(String frameJson) throws JSONException, IOException {
        Intrinsics.checkNotNullParameter(frameJson, "frameJson");
        JSONObject jSONObject = new JSONObject(frameJson);
        if (jSONObject.getInt("frame_version") != 1) {
            throw new IllegalArgumentException("\u0625\u0635\u062f\u0627\u0631 \u0625\u0637\u0627\u0631 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645".toString());
        }
        String string = jSONObject.getString("store_id");
        String string2 = jSONObject.getString("sender_device_id");
        String string3 = jSONObject.getString("recipient_device_id");
        String string4 = jSONObject.getString("operation_id");
        String orCreate = SyncDeviceIdentity.INSTANCE.getOrCreate(this.context);
        if (!Intrinsics.areEqual(string3, orCreate)) {
            throw new IllegalArgumentException("\u0625\u0637\u0627\u0631 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u0645\u0648\u062c\u0647 \u0625\u0644\u0649 \u062c\u0647\u0627\u0632 \u0622\u062e\u0631".toString());
        }
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        if (!Intrinsics.areEqual(state(readableDatabase, "local_device_role"), "PRIMARY") || !Intrinsics.areEqual(state(readableDatabase, "authority_device_id"), orCreate)) {
            throw new IllegalArgumentException("\u0647\u0630\u0627 \u0627\u0644\u062c\u0647\u0627\u0632 \u0644\u064a\u0633 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0627\u0644\u0645\u0639\u062a\u0645\u062f".toString());
        }
        if (!Intrinsics.areEqual(string, state(readableDatabase, "store_id"))) {
            throw new IllegalArgumentException("\u0627\u0644\u0625\u0637\u0627\u0631 \u064a\u062e\u0635 \u0645\u062a\u062c\u0631\u0627\u064b \u0622\u062e\u0631".toString());
        }
        Intrinsics.checkNotNull(string);
        Intrinsics.checkNotNull(string2);
        Intrinsics.checkNotNull(string4);
        verifyPeerFrameSignature(jSONObject, string, string2, string3, string4);
        Cursor cursorRawQuery = readableDatabase.rawQuery("SELECT 1 FROM sync_devices WHERE device_id=? AND device_role='SECONDARY' AND status='ACTIVE'", new String[]{string2});
        try {
            boolean zMoveToFirst = cursorRawQuery.moveToFirst();
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (!zMoveToFirst) {
                throw new IllegalArgumentException("\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0631\u0633\u0644 \u063a\u064a\u0631 \u0645\u0633\u062c\u0644 \u0623\u0648 \u062a\u0645 \u0625\u0644\u063a\u0627\u0621 \u062a\u0633\u062c\u064a\u0644\u0647".toString());
            }
            byte[] bArrLoad = this.credentialVault.load(string2);
            if (bArrLoad == null) {
                throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0631\u0633\u0644 \u063a\u064a\u0631 \u0645\u062a\u0648\u0641\u0631\u0629");
            }
            try {
                SyncEnvelopeCipher.PeerContext peerContext = new SyncEnvelopeCipher.PeerContext(string, string2, string3, string4);
                byte[] bArrDecode = Base64.decode(jSONObject.getString("ciphertext"), 11);
                SyncEnvelopeCipher syncEnvelopeCipher = SyncEnvelopeCipher.INSTANCE;
                Intrinsics.checkNotNull(bArrDecode);
                byte[] bArrDecrypt = syncEnvelopeCipher.decrypt(bArrDecode, new SecretKeySpec(bArrLoad, "AES"), peerContext);
                if (bArrDecrypt == null) {
                    throw new IllegalArgumentException("\u0641\u0634\u0644 \u0641\u0643 \u062a\u0634\u0641\u064a\u0631 \u0625\u0637\u0627\u0631 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u0623\u0648 \u0627\u0644\u062a\u062d\u0642\u0642 \u0645\u0646 \u0633\u0644\u0627\u0645\u062a\u0647");
                }
                String str = new String(bArrDecrypt, Charsets.UTF_8);
                AuthenticatedSyncEnvelope authenticatedSyncEnvelopeDecode = SyncEnvelopeSecurity.INSTANCE.decode(str);
                if (!Intrinsics.areEqual(authenticatedSyncEnvelopeDecode.getStoreId(), string) || !Intrinsics.areEqual(authenticatedSyncEnvelopeDecode.getOriginDeviceId(), string2) || !Intrinsics.areEqual(authenticatedSyncEnvelopeDecode.getOperationId(), string4)) {
                    throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0647\u0648\u064a\u0629 \u0627\u0644\u0625\u0637\u0627\u0631 \u0644\u0627 \u062a\u0637\u0627\u0628\u0642 \u0627\u0644\u0631\u0633\u0627\u0644\u0629 \u0627\u0644\u0645\u0634\u0641\u0631\u0629".toString());
                }
                AuthenticatedSyncEnvelope authenticatedSyncEnvelopeVerifyIncomingFromSecondary = verifyIncomingFromSecondary(str);
                ArraysKt.fill$default(bArrLoad, (byte) 0, 0, 0, 6, (Object) null);
                return authenticatedSyncEnvelopeVerifyIncomingFromSecondary;
            } catch (Throwable th) {
                ArraysKt.fill$default(bArrLoad, (byte) 0, 0, 0, 6, (Object) null);
                throw th;
            }
        } catch (Throwable th2) {
            try {
                throw th2;
            } catch (Throwable th3) {
                CloseableKt.closeFinally(cursorRawQuery, th2);
                throw th3;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final AuthenticatedSyncEnvelope verifyEncryptedIncomingFromPrimary(String frameJson) throws JSONException, IOException {
        Intrinsics.checkNotNullParameter(frameJson, "frameJson");
        JSONObject jSONObject = new JSONObject(frameJson);
        if (jSONObject.getInt("frame_version") != 1) {
            throw new IllegalArgumentException("\u0625\u0635\u062f\u0627\u0631 \u0625\u0637\u0627\u0631 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645".toString());
        }
        String string = jSONObject.getString("store_id");
        String string2 = jSONObject.getString("sender_device_id");
        String string3 = jSONObject.getString("recipient_device_id");
        String string4 = jSONObject.getString("operation_id");
        String orCreate = SyncDeviceIdentity.INSTANCE.getOrCreate(this.context);
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        if (!Intrinsics.areEqual(state(readableDatabase, "local_device_role"), "SECONDARY")) {
            throw new IllegalArgumentException("\u0647\u0630\u0627 \u0627\u0644\u062c\u0647\u0627\u0632 \u0644\u064a\u0633 \u062c\u0647\u0627\u0632\u0627\u064b \u062b\u0627\u0646\u0648\u064a\u0627\u064b \u0645\u0633\u062c\u0644\u0627\u064b".toString());
        }
        if (!Intrinsics.areEqual(string3, orCreate) || !Intrinsics.areEqual(string2, state(readableDatabase, "authority_device_id"))) {
            throw new IllegalArgumentException("\u0645\u0635\u062f\u0631 \u0623\u0648 \u0645\u0633\u062a\u0644\u0645 \u0627\u0644\u0625\u0637\u0627\u0631 \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u0647\u0648\u064a\u0629 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0639\u062a\u0645\u062f\u0629".toString());
        }
        if (!Intrinsics.areEqual(string, state(readableDatabase, "store_id"))) {
            throw new IllegalArgumentException("\u0627\u0644\u0625\u0637\u0627\u0631 \u064a\u062e\u0635 \u0645\u062a\u062c\u0631\u0627\u064b \u0622\u062e\u0631".toString());
        }
        Intrinsics.checkNotNull(string);
        Intrinsics.checkNotNull(string2);
        Intrinsics.checkNotNull(string4);
        verifyPeerFrameSignature(jSONObject, string, string2, string3, string4);
        byte[] bArrLoad = this.credentialVault.load(orCreate);
        if (bArrLoad == null) {
            throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u063a\u064a\u0631 \u0645\u062a\u0648\u0641\u0631\u0629");
        }
        try {
            SyncEnvelopeCipher.PeerContext peerContext = new SyncEnvelopeCipher.PeerContext(string, string2, string3, string4);
            byte[] bArrDecode = Base64.decode(jSONObject.getString("ciphertext"), 11);
            SyncEnvelopeCipher syncEnvelopeCipher = SyncEnvelopeCipher.INSTANCE;
            Intrinsics.checkNotNull(bArrDecode);
            byte[] bArrDecrypt = syncEnvelopeCipher.decrypt(bArrDecode, new SecretKeySpec(bArrLoad, "AES"), peerContext);
            if (bArrDecrypt == null) {
                throw new IllegalArgumentException("\u0641\u0634\u0644 \u0641\u0643 \u062a\u0634\u0641\u064a\u0631 \u0625\u0637\u0627\u0631 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u0623\u0648 \u0627\u0644\u062a\u062d\u0642\u0642 \u0645\u0646 \u0633\u0644\u0627\u0645\u062a\u0647");
            }
            String str = new String(bArrDecrypt, Charsets.UTF_8);
            AuthenticatedSyncEnvelope authenticatedSyncEnvelopeDecode = SyncEnvelopeSecurity.INSTANCE.decode(str);
            if (!Intrinsics.areEqual(authenticatedSyncEnvelopeDecode.getStoreId(), string) || !Intrinsics.areEqual(authenticatedSyncEnvelopeDecode.getOriginDeviceId(), string2) || !Intrinsics.areEqual(authenticatedSyncEnvelopeDecode.getOperationId(), string4)) {
                throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0647\u0648\u064a\u0629 \u0627\u0644\u0625\u0637\u0627\u0631 \u0644\u0627 \u062a\u0637\u0627\u0628\u0642 \u0627\u0644\u0631\u0633\u0627\u0644\u0629 \u0627\u0644\u0645\u0634\u0641\u0631\u0629".toString());
            }
            AuthenticatedSyncEnvelope authenticatedSyncEnvelopeVerifyIncomingFromPrimary = verifyIncomingFromPrimary(str);
            ArraysKt.fill$default(bArrLoad, (byte) 0, 0, 0, 6, (Object) null);
            return authenticatedSyncEnvelopeVerifyIncomingFromPrimary;
        } catch (Throwable th) {
            ArraysKt.fill$default(bArrLoad, (byte) 0, 0, 0, 6, (Object) null);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String enqueueTransportProbe(String actorUserId) {
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        if (!isConfiguredSecondary()) {
            throw new IllegalArgumentException("\u0627\u062e\u062a\u0628\u0627\u0631 \u0627\u0644\u0646\u0642\u0644 \u0645\u062a\u0627\u062d \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u0627\u0644\u0645\u0631\u062a\u0628\u0637 \u0641\u0642\u0637".toString());
        }
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        writableDatabase.beginTransaction();
        try {
            PermissionPolicy.INSTANCE.requireAssigned(writableDatabase, actorUserId, PermissionPolicy.SYNC_MANAGE);
            String string2 = new JSONObject().put("probe_id", string).put("nonce", UUID.randomUUID().toString()).put("created_at", timestamp$default(this, 0L, 1, null)).toString();
            Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
            enqueueSecondaryCommand(writableDatabase, actorUserId, string, "SYNC_TRANSPORT_PROBE", "SYNC_TRANSPORT_PROBE", string, string2);
            writableDatabase.setTransactionSuccessful();
            return string;
        } finally {
            writableDatabase.endTransaction();
        }
    }

    public final String outboxStatus(String operationId) throws IOException {
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        if (StringsKt.isBlank(operationId)) {
            return null;
        }
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery("SELECT status FROM sync_queue WHERE operation_id=?", new String[]{operationId});
        try {
            Cursor cursor = cursorRawQuery;
            String string = cursor.moveToFirst() ? cursor.getString(0) : null;
            CloseableKt.closeFinally(cursorRawQuery, null);
            return string;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:29:0x0099  */
    /* JADX WARN: Code duplicated, block: B:33:0x00a5  */
    public final long enqueueSecondaryCommand(SQLiteDatabase db, String actorUserId, String operationId, String businessOperation, String entityType, String entityId, String payloadJson) throws NoSuchAlgorithmException, IOException {
        String str;
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        Intrinsics.checkNotNullParameter(businessOperation, "businessOperation");
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        Intrinsics.checkNotNullParameter(entityId, "entityId");
        Intrinsics.checkNotNullParameter(payloadJson, "payloadJson");
        if (!db.inTransaction()) {
            throw new IllegalArgumentException("\u064a\u062c\u0628 \u062d\u0641\u0638 \u0627\u0644\u0623\u0645\u0631 \u0636\u0645\u0646 \u0645\u0639\u0627\u0645\u0644\u0629 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a".toString());
        }
        if (!Intrinsics.areEqual(state(db, "local_device_role"), "SECONDARY")) {
            throw new IllegalArgumentException("\u0625\u0631\u0633\u0627\u0644 \u0627\u0644\u0623\u0648\u0627\u0645\u0631 \u0627\u0644\u0645\u0639\u0644\u0642\u0629 \u0645\u062a\u0627\u062d \u0644\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u0627\u0644\u0645\u0633\u062c\u0644 \u0641\u0642\u0637".toString());
        }
        String strState = state(db, "authority_device_id");
        if (strState == null || StringsKt.isBlank(strState)) {
            throw new IllegalArgumentException("\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u063a\u064a\u0631 \u0645\u062d\u062f\u062f".toString());
        }
        if (!SetsKt.setOf((Object[]) new String[]{"POST_SALE_INVOICE", "COLLECT_CUSTOMER_PAYMENT", "COLLECT_SERVICE_TRANSFER_DEBT", "EXECUTE_SERVICE_TRANSFER_CREDIT", "SYNC_TRANSPORT_PROBE"}).contains(businessOperation)) {
            throw new IllegalArgumentException("\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u0645\u062e\u0648\u0644 \u0628\u0625\u0631\u0633\u0627\u0644 \u0641\u0648\u0627\u062a\u064a\u0631 \u0627\u0644\u0628\u064a\u0639 \u0648\u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0648\u0646 \u0627\u0644\u0639\u0645\u0644\u0627\u0621 \u0648\u062a\u062d\u0648\u064a\u0644\u0627\u062a \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0622\u062c\u0644\u0629 \u0648\u0627\u062e\u062a\u0628\u0627\u0631\u0627\u062a \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u0641\u0642\u0637".toString());
        }
        PermissionPolicy permissionPolicy = PermissionPolicy.INSTANCE;
        switch (businessOperation) {
            case "POST_SALE_INVOICE":
                str = PermissionPolicy.SALES_CHECKOUT;
                break;
            case "COLLECT_CUSTOMER_PAYMENT":
                str = PermissionPolicy.CUSTOMERS_COLLECT;
                break;
            case "EXECUTE_SERVICE_TRANSFER_CREDIT":
                str = PermissionPolicy.SERVICE_TRANSFERS_EXECUTE;
                break;
            case "COLLECT_SERVICE_TRANSFER_DEBT":
                str = PermissionPolicy.CUSTOMERS_COLLECT;
                break;
            default:
                str = PermissionPolicy.SYNC_MANAGE;
                break;
        }
        permissionPolicy.requireAssigned(db, actorUserId, str);
        if (StringsKt.isBlank(operationId) || StringsKt.isBlank(businessOperation) || StringsKt.isBlank(entityType) || StringsKt.isBlank(entityId)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        int length = payloadJson.length();
        if (2 > length || length >= 131073) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        Cursor cursorRawQuery = db.rawQuery("SELECT 1 FROM users WHERE id=? AND is_active=1 AND must_change_password=0", new String[]{actorUserId});
        try {
            boolean zMoveToFirst = cursorRawQuery.moveToFirst();
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (!zMoveToFirst) {
                throw new IllegalArgumentException("\u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0645\u0641\u0639\u0644 \u0623\u0648 \u064a\u062c\u0628 \u062a\u063a\u064a\u064a\u0631 \u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631".toString());
            }
            SyncEnvelopeSecurity syncEnvelopeSecurity = SyncEnvelopeSecurity.INSTANCE;
            String string = new JSONObject(payloadJson).put("business_operation", businessOperation).toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            String strCanonicalObject = syncEnvelopeSecurity.canonicalObject(string);
            String strSha256 = sha256(strCanonicalObject);
            Cursor cursorRawQuery2 = db.rawQuery("SELECT sequence_number,payload_sha256,entity_type,entity_id,\n                      actor_user_id,operation_type,origin_device_id\n               FROM sync_operation_envelopes WHERE operation_id=?", new String[]{operationId});
            try {
                Cursor cursor = cursorRawQuery2;
                String[] strArr = !cursor.moveToFirst() ? null : new String[]{String.valueOf(cursor.getLong(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6)};
                CloseableKt.closeFinally(cursorRawQuery2, null);
                if (strArr != null) {
                    if (!Intrinsics.areEqual(strArr[1], strSha256) || !Intrinsics.areEqual(strArr[2], entityType) || !Intrinsics.areEqual(strArr[3], entityId) || !Intrinsics.areEqual(strArr[4], actorUserId) || !Intrinsics.areEqual(strArr[5], "CREATE") || !Intrinsics.areEqual(strArr[6], SyncDeviceIdentity.INSTANCE.getOrCreate(this.context))) {
                        throw new IllegalArgumentException("\u0623\u0639\u064a\u062f \u0627\u0633\u062a\u062e\u062f\u0627\u0645 \u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0623\u0645\u0631 \u0628\u0647\u0648\u064a\u0629 \u0623\u0648 \u0628\u064a\u0627\u0646\u0627\u062a \u0645\u062e\u062a\u0644\u0641\u0629".toString());
                    }
                    String str2 = strArr[0];
                    Intrinsics.checkNotNullExpressionValue(str2, "get(...)");
                    return Long.parseLong(str2);
                }
                String orCreate = SyncDeviceIdentity.INSTANCE.getOrCreate(this.context);
                Cursor cursorRawQuery3 = db.rawQuery("SELECT 1 FROM sync_devices WHERE device_id=? AND device_role='SECONDARY' AND status='ACTIVE'", new String[]{orCreate});
                try {
                    boolean zMoveToFirst2 = cursorRawQuery3.moveToFirst();
                    CloseableKt.closeFinally(cursorRawQuery3, null);
                    if (!zMoveToFirst2) {
                        throw new IllegalStateException("\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u063a\u064a\u0631 \u0645\u0633\u062c\u0644 \u0623\u0648 \u062a\u0645 \u0625\u064a\u0642\u0627\u0641\u0647".toString());
                    }
                    String strTimestamp$default = timestamp$default(this, 0L, 1, null);
                    String strState2 = state(db, "secondary_next_sequence");
                    if (strState2 == null) {
                        strState2 = "1";
                    }
                    long j = Long.parseLong(strState2);
                    if (j > 0 && j < Long.MAX_VALUE) {
                        putState(db, "secondary_next_sequence", String.valueOf(j + 1), strTimestamp$default);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("operation_id", operationId);
                        contentValues.put("sequence_number", Long.valueOf(j));
                        contentValues.put("origin_device_id", orCreate);
                        contentValues.put(SyncDeviceManagementActivity.EXTRA_ACTOR_USER_ID, actorUserId);
                        contentValues.put("operation_type", "CREATE");
                        contentValues.put("entity_type", entityType);
                        contentValues.put("entity_id", entityId);
                        contentValues.put("payload_json", strCanonicalObject);
                        contentValues.put("payload_sha256", strSha256);
                        contentValues.put("created_at", strTimestamp$default);
                        Unit unit = Unit.INSTANCE;
                        db.insertOrThrow("sync_operation_envelopes", null, contentValues);
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("id", UUID.randomUUID().toString());
                        contentValues2.put("operation_id", operationId);
                        contentValues2.put("entity_type", entityType);
                        contentValues2.put("entity_id", entityId);
                        contentValues2.put("operation_type", "CREATE");
                        contentValues2.put("payload_json", strCanonicalObject);
                        contentValues2.put(NotificationCompat.CATEGORY_STATUS, "PENDING");
                        contentValues2.put("retry_count", (Integer) 0);
                        contentValues2.put("created_at", strTimestamp$default);
                        contentValues2.put("updated_at", strTimestamp$default);
                        Unit unit2 = Unit.INSTANCE;
                        db.insertOrThrow("sync_queue", null, contentValues2);
                        return j;
                    }
                    throw new IllegalStateException("\u062a\u0633\u0644\u0633\u0644 \u0623\u0648\u0627\u0645\u0631 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(cursorRawQuery3, th);
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                try {
                    throw th3;
                } catch (Throwable th4) {
                    CloseableKt.closeFinally(cursorRawQuery2, th3);
                    throw th4;
                }
            }
        } catch (Throwable th5) {
            try {
                throw th5;
            } catch (Throwable th6) {
                CloseableKt.closeFinally(cursorRawQuery, th5);
                throw th6;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void mapSecondaryActor(String managerUserId, String secondaryDeviceId, String remoteActorUserId, String primaryUserId) {
        Intrinsics.checkNotNullParameter(managerUserId, "managerUserId");
        Intrinsics.checkNotNullParameter(secondaryDeviceId, "secondaryDeviceId");
        Intrinsics.checkNotNullParameter(remoteActorUserId, "remoteActorUserId");
        Intrinsics.checkNotNullParameter(primaryUserId, "primaryUserId");
        if (StringsKt.isBlank(remoteActorUserId) || StringsKt.isBlank(primaryUserId)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        writableDatabase.beginTransaction();
        try {
            requirePrimaryManager(writableDatabase, managerUserId);
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT 1 FROM sync_devices WHERE device_id=? AND device_role='SECONDARY' AND status='ACTIVE'", new String[]{secondaryDeviceId});
            try {
                boolean zMoveToFirst = cursorRawQuery.moveToFirst();
                CloseableKt.closeFinally(cursorRawQuery, null);
                if (!zMoveToFirst) {
                    throw new IllegalArgumentException("\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u063a\u064a\u0631 \u0645\u0633\u062c\u0644 \u0623\u0648 \u063a\u064a\u0631 \u0645\u0641\u0639\u0644".toString());
                }
                Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT 1 FROM users WHERE id=? AND is_active=1 AND must_change_password=0", new String[]{primaryUserId});
                try {
                    boolean zMoveToFirst2 = cursorRawQuery2.moveToFirst();
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    if (!zMoveToFirst2) {
                        throw new IllegalArgumentException("\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u063a\u064a\u0631 \u0645\u0641\u0639\u0644 \u0623\u0648 \u064a\u062a\u0637\u0644\u0628 \u062a\u063a\u064a\u064a\u0631 \u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631".toString());
                    }
                    String strTimestamp$default = timestamp$default(this, 0L, 1, null);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("device_id", secondaryDeviceId);
                    contentValues.put("remote_actor_user_id", remoteActorUserId);
                    contentValues.put("primary_user_id", primaryUserId);
                    contentValues.put("is_active", (Integer) 1);
                    contentValues.put("mapped_by", managerUserId);
                    contentValues.put("mapped_at", strTimestamp$default);
                    Unit unit = Unit.INSTANCE;
                    writableDatabase.insertWithOnConflict("sync_actor_mappings", null, contentValues, 5);
                    audit(writableDatabase, managerUserId, "SYNC_ACTOR_MAPPED", secondaryDeviceId, null, new JSONObject().put("remote_actor_user_id", remoteActorUserId).put("primary_user_id", primaryUserId).toString(), strTimestamp$default);
                    writableDatabase.setTransactionSuccessful();
                    writableDatabase.endTransaction();
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(cursorRawQuery2, th);
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                try {
                    throw th3;
                } catch (Throwable th4) {
                    CloseableKt.closeFinally(cursorRawQuery, th3);
                    throw th4;
                }
            }
        } catch (Throwable th5) {
            writableDatabase.endTransaction();
            throw th5;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void mapSecondaryCashAccount(String managerUserId, String secondaryDeviceId, String remoteCashAccountId, String primaryCashAccountId) {
        Intrinsics.checkNotNullParameter(managerUserId, "managerUserId");
        Intrinsics.checkNotNullParameter(secondaryDeviceId, "secondaryDeviceId");
        Intrinsics.checkNotNullParameter(remoteCashAccountId, "remoteCashAccountId");
        Intrinsics.checkNotNullParameter(primaryCashAccountId, "primaryCashAccountId");
        if (StringsKt.isBlank(secondaryDeviceId) || StringsKt.isBlank(remoteCashAccountId) || StringsKt.isBlank(primaryCashAccountId)) {
            throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0631\u0628\u0637 \u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a \u063a\u064a\u0631 \u0645\u0643\u062a\u0645\u0644\u0629".toString());
        }
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        writableDatabase.beginTransaction();
        try {
            requirePrimaryManager(writableDatabase, managerUserId);
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT 1 FROM sync_devices WHERE device_id=? AND device_role='SECONDARY' AND status='ACTIVE'", new String[]{secondaryDeviceId});
            try {
                boolean zMoveToFirst = cursorRawQuery.moveToFirst();
                CloseableKt.closeFinally(cursorRawQuery, null);
                if (!zMoveToFirst) {
                    throw new IllegalArgumentException("\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u063a\u064a\u0631 \u0645\u0633\u062c\u0644 \u0623\u0648 \u063a\u064a\u0631 \u0645\u0641\u0639\u0644".toString());
                }
                Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT account_type,currency_code,is_active FROM cash_accounts WHERE id=?", new String[]{primaryCashAccountId});
                try {
                    Cursor cursor = cursorRawQuery2;
                    if (!cursor.moveToFirst()) {
                        throw new IllegalArgumentException("\u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
                    }
                    if (cursor.getInt(2) != 1) {
                        throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0627\u0644\u0631\u0628\u0637 \u0628\u062d\u0633\u0627\u0628 \u0645\u0627\u0644\u064a \u0645\u0648\u0642\u0648\u0641".toString());
                    }
                    Pair pair = TuplesKt.to(cursor.getString(0), cursor.getString(1));
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    if (!SetsKt.setOf((Object[]) new String[]{"CASH_BOX", "WALLET"}).contains(pair.getFirst()) || !SetsKt.setOf((Object[]) new String[]{"USD", "SYP"}).contains(pair.getSecond())) {
                        throw new IllegalArgumentException("\u064a\u062c\u0628 \u0627\u062e\u062a\u064a\u0627\u0631 \u0635\u0646\u062f\u0648\u0642 \u0623\u0648 \u0645\u062d\u0641\u0638\u0629 \u0646\u0634\u0637\u0629 \u0628\u0639\u0645\u0644\u0629 \u0645\u062f\u0639\u0648\u0645\u0629".toString());
                    }
                    String strTimestamp$default = timestamp$default(this, 0L, 1, null);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("device_id", secondaryDeviceId);
                    contentValues.put("remote_cash_account_id", remoteCashAccountId);
                    contentValues.put("primary_cash_account_id", primaryCashAccountId);
                    contentValues.put("is_active", (Integer) 1);
                    contentValues.put("mapped_by", managerUserId);
                    contentValues.put("mapped_at", strTimestamp$default);
                    Unit unit = Unit.INSTANCE;
                    writableDatabase.insertWithOnConflict("sync_cash_account_mappings", null, contentValues, 5);
                    audit(writableDatabase, managerUserId, "SYNC_CASH_ACCOUNT_MAPPED", secondaryDeviceId, null, new JSONObject().put("remote_cash_account_id", remoteCashAccountId).put("primary_cash_account_id", primaryCashAccountId).put("account_type", pair.getFirst()).put("currency_code", pair.getSecond()).toString(), strTimestamp$default);
                    writableDatabase.setTransactionSuccessful();
                    writableDatabase.endTransaction();
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(cursorRawQuery2, th);
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                try {
                    throw th3;
                } catch (Throwable th4) {
                    CloseableKt.closeFinally(cursorRawQuery, th3);
                    throw th4;
                }
            }
        } catch (Throwable th5) {
            writableDatabase.endTransaction();
            throw th5;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0022, code lost:
    
        if (r2.equals("COLLECT_CUSTOMER_PAYMENT") != false) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0024, code lost:
    
        return com.hazem.dailystore.data.PermissionPolicy.CUSTOMERS_COLLECT;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x000e, code lost:
    
        if (r2.equals("COLLECT_SERVICE_TRANSFER_DEBT") != false) goto L13;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final String requiredPermissionForBusinessOperation(String operation) {
        switch (operation.hashCode()) {
            case -2017668012:
                if (operation.equals("POST_SALE_INVOICE")) {
                    return PermissionPolicy.SALES_CHECKOUT;
                }
                throw new IllegalArgumentException("\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u0645\u062e\u0648\u0644 \u0628\u0625\u0631\u0633\u0627\u0644 \u0641\u0648\u0627\u062a\u064a\u0631 \u0627\u0644\u0628\u064a\u0639 \u0648\u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0648\u0646 \u0627\u0644\u0639\u0645\u0644\u0627\u0621 \u0648\u062a\u062d\u0648\u064a\u0644\u0627\u062a \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0622\u062c\u0644\u0629 \u0648\u0627\u062e\u062a\u0628\u0627\u0631\u0627\u062a \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u0641\u0642\u0637");
            case -1683422950:
                break;
            case -1476903623:
                if (operation.equals("EXECUTE_SERVICE_TRANSFER_CREDIT")) {
                    return PermissionPolicy.SERVICE_TRANSFERS_EXECUTE;
                }
                throw new IllegalArgumentException("\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u0645\u062e\u0648\u0644 \u0628\u0625\u0631\u0633\u0627\u0644 \u0641\u0648\u0627\u062a\u064a\u0631 \u0627\u0644\u0628\u064a\u0639 \u0648\u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0648\u0646 \u0627\u0644\u0639\u0645\u0644\u0627\u0621 \u0648\u062a\u062d\u0648\u064a\u0644\u0627\u062a \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0622\u062c\u0644\u0629 \u0648\u0627\u062e\u062a\u0628\u0627\u0631\u0627\u062a \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u0641\u0642\u0637");
            case 1318921928:
                break;
            default:
                throw new IllegalArgumentException("\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u0645\u062e\u0648\u0644 \u0628\u0625\u0631\u0633\u0627\u0644 \u0641\u0648\u0627\u062a\u064a\u0631 \u0627\u0644\u0628\u064a\u0639 \u0648\u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0648\u0646 \u0627\u0644\u0639\u0645\u0644\u0627\u0621 \u0648\u062a\u062d\u0648\u064a\u0644\u0627\u062a \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0622\u062c\u0644\u0629 \u0648\u0627\u062e\u062a\u0628\u0627\u0631\u0627\u062a \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u0641\u0642\u0637");
        }
    }

    public final SyncInboxDisposition recordIncomingFromSecondary(String encodedEnvelope) throws IOException {
        Throwable th;
        long j;
        Object objM381constructorimpl;
        Intrinsics.checkNotNullParameter(encodedEnvelope, "encodedEnvelope");
        AuthenticatedSyncEnvelope authenticatedSyncEnvelopeVerifyIncomingFromSecondary = verifyIncomingFromSecondary(encodedEnvelope);
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        writableDatabase.beginTransaction();
        try {
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT 1 FROM sync_devices WHERE device_id=? AND device_role='SECONDARY' AND status='ACTIVE'", new String[]{authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getOriginDeviceId()});
            try {
                boolean zMoveToFirst = cursorRawQuery.moveToFirst();
                CloseableKt.closeFinally(cursorRawQuery, null);
                if (!zMoveToFirst) {
                    throw new IllegalArgumentException("\u062a\u0645 \u0625\u0644\u063a\u0627\u0621 \u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0631\u0633\u0644".toString());
                }
                Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT payload_sha256,origin_device_id,sequence_number,operation_type,\n                          entity_type,entity_id,origin_created_at,payload_json,actor_user_id,status\n                   FROM sync_inbox WHERE operation_id=?", new String[]{authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getOperationId()});
                try {
                    Cursor cursor = cursorRawQuery2;
                    String[] strArr = !cursor.moveToFirst() ? null : new String[]{cursor.getString(0), cursor.getString(1), String.valueOf(cursor.getLong(2)), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9)};
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    if (strArr != null) {
                        if (Intrinsics.areEqual(strArr[0], authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getPayloadSha256()) && Intrinsics.areEqual(strArr[1], authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getOriginDeviceId())) {
                            String str = strArr[2];
                            Intrinsics.checkNotNullExpressionValue(str, "get(...)");
                            if (Long.parseLong(str) == authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getSequenceNumber() && Intrinsics.areEqual(strArr[3], authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getOperationType()) && Intrinsics.areEqual(strArr[4], authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getEntityType()) && Intrinsics.areEqual(strArr[5], authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getEntityId()) && Intrinsics.areEqual(strArr[6], authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getCreatedAt()) && (Intrinsics.areEqual(strArr[8], authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getActorUserId()) || (strArr[8] == null && Intrinsics.areEqual(strArr[9], "APPLIED")))) {
                                writableDatabase.setTransactionSuccessful();
                                SyncInboxDisposition syncInboxDisposition = SyncInboxDisposition.DUPLICATE;
                                writableDatabase.endTransaction();
                                return syncInboxDisposition;
                            }
                        }
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("id", UUID.randomUUID().toString());
                        contentValues.put("operation_id", authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getOperationId());
                        contentValues.put("entity_type", authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getEntityType());
                        contentValues.put("entity_id", authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getEntityId());
                        contentValues.put("local_payload_json", strArr[7]);
                        contentValues.put("remote_payload_json", authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getPayloadJson());
                        contentValues.put("created_at", timestamp$default(this, 0L, 1, null));
                        Unit unit = Unit.INSTANCE;
                        writableDatabase.insertOrThrow("sync_conflicts", null, contentValues);
                        writableDatabase.setTransactionSuccessful();
                        SyncInboxDisposition syncInboxDisposition2 = SyncInboxDisposition.CONFLICT;
                        writableDatabase.endTransaction();
                        return syncInboxDisposition2;
                    }
                    JSONObject jSONObject = new JSONObject(authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getPayloadJson());
                    String strOptString = jSONObject.optString("business_operation");
                    if (!SetsKt.setOf((Object[]) new String[]{"POST_SALE_INVOICE", "COLLECT_CUSTOMER_PAYMENT", "COLLECT_SERVICE_TRANSFER_DEBT", "EXECUTE_SERVICE_TRANSFER_CREDIT", "SYNC_TRANSPORT_PROBE"}).contains(strOptString)) {
                        throw new IllegalArgumentException("\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u0645\u062e\u0648\u0644 \u0628\u0625\u0631\u0633\u0627\u0644 \u0641\u0648\u0627\u062a\u064a\u0631 \u0627\u0644\u0628\u064a\u0639 \u0648\u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0648\u0646 \u0627\u0644\u0639\u0645\u0644\u0627\u0621 \u0648\u062a\u062d\u0648\u064a\u0644\u0627\u062a \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0622\u062c\u0644\u0629 \u0648\u0627\u062e\u062a\u0628\u0627\u0631\u0627\u062a \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u0641\u0642\u0637".toString());
                    }
                    if (Intrinsics.areEqual(strOptString, "COLLECT_SERVICE_TRANSFER_DEBT")) {
                        if (Intrinsics.areEqual(authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getOperationType(), "CREATE") && Intrinsics.areEqual(authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getEntityType(), "SERVICE_TRANSFER_DEBT_PAYMENT") && Intrinsics.areEqual(jSONObject.optString("payment_id"), authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getEntityId())) {
                            String strOptString2 = jSONObject.optString("transfer_id");
                            Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
                            if (!StringsKt.isBlank(strOptString2)) {
                                String strOptString3 = jSONObject.optString("customer_id");
                                Intrinsics.checkNotNullExpressionValue(strOptString3, "optString(...)");
                                if (!StringsKt.isBlank(strOptString3) && jSONObject.optLong("amount_minor", 0L) > 0) {
                                    String strOptString4 = jSONObject.optString("cash_account_id");
                                    Intrinsics.checkNotNullExpressionValue(strOptString4, "optString(...)");
                                    if (!StringsKt.isBlank(strOptString4) && SetsKt.setOf((Object[]) new String[]{"CASH_BOX", "WALLET"}).contains(jSONObject.optString("cash_account_type")) && Intrinsics.areEqual(jSONObject.optString("cash_account_currency_code"), "SYP")) {
                                        String strOptString5 = jSONObject.optString("operating_date");
                                        Intrinsics.checkNotNullExpressionValue(strOptString5, "optString(...)");
                                        if (new Regex("\\d{4}-\\d{2}-\\d{2}").matches(strOptString5)) {
                                            String strOptString6 = jSONObject.optString("occurred_at");
                                            Intrinsics.checkNotNullExpressionValue(strOptString6, "optString(...)");
                                            if (new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(strOptString6)) {
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0646 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
                    }
                    if (Intrinsics.areEqual(strOptString, "SYNC_TRANSPORT_PROBE")) {
                        if (Intrinsics.areEqual(authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getOperationType(), "CREATE") && Intrinsics.areEqual(authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getEntityType(), "SYNC_TRANSPORT_PROBE") && Intrinsics.areEqual(authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getEntityId(), authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getOperationId()) && Intrinsics.areEqual(jSONObject.optString("probe_id"), authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getOperationId())) {
                            try {
                                Result.Companion companion = Result.INSTANCE;
                                SyncProtocolRepository syncProtocolRepository = this;
                                objM381constructorimpl = Result.m381constructorimpl(UUID.fromString(jSONObject.optString("nonce")));
                            } catch (Throwable th2) {
                                Result.Companion companion2 = Result.INSTANCE;
                                objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th2));
                            }
                            if (Result.m388isSuccessimpl(objM381constructorimpl)) {
                                String strOptString7 = jSONObject.optString("created_at");
                                Intrinsics.checkNotNullExpressionValue(strOptString7, "optString(...)");
                                if (new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(strOptString7)) {
                                }
                            }
                        }
                        throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u062e\u062a\u0628\u0627\u0631 \u0627\u0644\u0646\u0642\u0644 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
                    }
                    Intrinsics.checkNotNull(strOptString);
                    String strRequiredPermissionForBusinessOperation = requiredPermissionForBusinessOperation(strOptString);
                    Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT m.primary_user_id FROM sync_actor_mappings m\n                       JOIN users u ON u.id=m.primary_user_id\n                       WHERE m.device_id=? AND m.remote_actor_user_id=? AND m.is_active=1\n                         AND u.is_active=1 AND u.must_change_password=0", new String[]{authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getOriginDeviceId(), authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getActorUserId()});
                    try {
                        Cursor cursor2 = cursorRawQuery3;
                        String string = cursor2.moveToFirst() ? cursor2.getString(0) : null;
                        CloseableKt.closeFinally(cursorRawQuery3, null);
                        if (string == null) {
                            throw new IllegalArgumentException("\u0644\u0645 \u064a\u062a\u0645 \u0631\u0628\u0637 \u0645\u0633\u062a\u062e\u062f\u0645 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u0628\u062d\u0633\u0627\u0628 \u0645\u062e\u0648\u0644 \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a");
                        }
                        PermissionPolicy.INSTANCE.require(writableDatabase, string, strRequiredPermissionForBusinessOperation);
                    } catch (Throwable th3) {
                        try {
                            throw th3;
                        } catch (Throwable th4) {
                            CloseableKt.closeFinally(cursorRawQuery3, th3);
                            throw th4;
                        }
                    }
                    Cursor cursorRawQuery4 = writableDatabase.rawQuery("SELECT last_sequence FROM sync_cursors WHERE device_id=?", new String[]{authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getOriginDeviceId()});
                    try {
                        Cursor cursor3 = cursorRawQuery4;
                        if (cursor3.moveToFirst()) {
                            j = cursor3.getLong(0);
                            th = null;
                        } else {
                            th = null;
                            j = 0;
                        }
                        CloseableKt.closeFinally(cursorRawQuery4, th);
                        if (authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getSequenceNumber() != 1 + j) {
                            throw new IllegalArgumentException((authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getSequenceNumber() <= j ? "\u062a\u0645 \u0631\u0641\u0636 \u0625\u0639\u0627\u062f\u0629 \u062a\u0634\u063a\u064a\u0644 \u062a\u0633\u0644\u0633\u0644 \u0642\u062f\u064a\u0645" : "\u0641\u062c\u0648\u0629 \u0641\u064a \u062a\u0633\u0644\u0633\u0644 \u0623\u0648\u0627\u0645\u0631 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a\u061b \u0627\u0646\u062a\u0638\u0631 \u0627\u0644\u0623\u0645\u0631 \u0627\u0644\u0633\u0627\u0628\u0642").toString());
                        }
                        String strTimestamp$default = timestamp$default(this, 0L, 1, th);
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("operation_id", authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getOperationId());
                        contentValues2.put("origin_device_id", authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getOriginDeviceId());
                        contentValues2.put("sequence_number", Long.valueOf(authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getSequenceNumber()));
                        contentValues2.put("operation_type", authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getOperationType());
                        contentValues2.put("entity_type", authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getEntityType());
                        contentValues2.put("entity_id", authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getEntityId());
                        contentValues2.put(SyncDeviceManagementActivity.EXTRA_ACTOR_USER_ID, authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getActorUserId());
                        contentValues2.put("payload_json", authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getPayloadJson());
                        contentValues2.put("payload_sha256", authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getPayloadSha256());
                        contentValues2.put(NotificationCompat.CATEGORY_STATUS, "RECEIVED");
                        contentValues2.put("received_at", strTimestamp$default);
                        contentValues2.put("origin_created_at", authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getCreatedAt());
                        Unit unit2 = Unit.INSTANCE;
                        writableDatabase.insertOrThrow("sync_inbox", null, contentValues2);
                        ContentValues contentValues3 = new ContentValues();
                        contentValues3.put("last_sequence", Long.valueOf(authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getSequenceNumber()));
                        contentValues3.put("updated_at", strTimestamp$default);
                        Unit unit3 = Unit.INSTANCE;
                        writableDatabase.update("sync_cursors", contentValues3, "device_id=?", new String[]{authenticatedSyncEnvelopeVerifyIncomingFromSecondary.getOriginDeviceId()});
                        writableDatabase.setTransactionSuccessful();
                        SyncInboxDisposition syncInboxDisposition3 = SyncInboxDisposition.RECEIVED;
                        writableDatabase.endTransaction();
                        return syncInboxDisposition3;
                    } catch (Throwable th5) {
                        try {
                            throw th5;
                        } catch (Throwable th6) {
                            CloseableKt.closeFinally(cursorRawQuery4, th5);
                            throw th6;
                        }
                    }
                } catch (Throwable th7) {
                    try {
                        throw th7;
                    } catch (Throwable th8) {
                        CloseableKt.closeFinally(cursorRawQuery2, th7);
                        throw th8;
                    }
                }
            } catch (Throwable th9) {
                try {
                    throw th9;
                } catch (Throwable th10) {
                    CloseableKt.closeFinally(cursorRawQuery, th9);
                    throw th10;
                }
            }
        } catch (Throwable th11) {
            writableDatabase.endTransaction();
            throw th11;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final AuthenticatedSyncEnvelope verifyIncomingFromPrimary(String encodedEnvelope) {
        Intrinsics.checkNotNullParameter(encodedEnvelope, "encodedEnvelope");
        AuthenticatedSyncEnvelope authenticatedSyncEnvelopeDecode = SyncEnvelopeSecurity.INSTANCE.decode(encodedEnvelope);
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        if (!Intrinsics.areEqual(state(readableDatabase, "local_device_role"), "SECONDARY")) {
            throw new IllegalArgumentException("\u0647\u0630\u0627 \u0627\u0644\u062c\u0647\u0627\u0632 \u0644\u064a\u0633 \u062c\u0647\u0627\u0632\u0627\u064b \u062b\u0627\u0646\u0648\u064a\u0627\u064b \u0645\u0633\u062c\u0644\u0627\u064b".toString());
        }
        if (!Intrinsics.areEqual(authenticatedSyncEnvelopeDecode.getOriginDeviceId(), state(readableDatabase, "authority_device_id"))) {
            throw new IllegalArgumentException("\u0645\u0635\u062f\u0631 \u0627\u0644\u0631\u0633\u0627\u0644\u0629 \u0644\u064a\u0633 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0627\u0644\u0645\u0639\u062a\u0645\u062f".toString());
        }
        if (!Intrinsics.areEqual(authenticatedSyncEnvelopeDecode.getStoreId(), state(readableDatabase, "store_id"))) {
            throw new IllegalArgumentException("\u0627\u0644\u0631\u0633\u0627\u0644\u0629 \u062a\u062e\u0635 \u0645\u062a\u062c\u0631\u0627\u064b \u0622\u062e\u0631".toString());
        }
        byte[] bArrLoad = this.credentialVault.load(SyncDeviceIdentity.INSTANCE.getOrCreate(this.context));
        if (bArrLoad == null) {
            throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u063a\u064a\u0631 \u0645\u062a\u0648\u0641\u0631\u0629");
        }
        try {
            if (!SyncEnvelopeSecurity.INSTANCE.verify(authenticatedSyncEnvelopeDecode, bArrLoad)) {
                throw new IllegalArgumentException("\u0641\u0634\u0644 \u0627\u0644\u062a\u062d\u0642\u0642 \u0645\u0646 \u062a\u0648\u0642\u064a\u0639 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629".toString());
            }
            ArraysKt.fill$default(bArrLoad, (byte) 0, 0, 0, 6, (Object) null);
            return authenticatedSyncEnvelopeDecode;
        } catch (Throwable th) {
            ArraysKt.fill$default(bArrLoad, (byte) 0, 0, 0, 6, (Object) null);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final AuthenticatedSyncEnvelope verifyIncomingFromSecondary(String encodedEnvelope) throws IOException {
        Intrinsics.checkNotNullParameter(encodedEnvelope, "encodedEnvelope");
        AuthenticatedSyncEnvelope authenticatedSyncEnvelopeDecode = SyncEnvelopeSecurity.INSTANCE.decode(encodedEnvelope);
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        if (!Intrinsics.areEqual(state(readableDatabase, "authority_device_id"), SyncDeviceIdentity.INSTANCE.getOrCreate(this.context))) {
            throw new IllegalArgumentException("\u0647\u0630\u0627 \u0627\u0644\u0647\u0627\u062a\u0641 \u0644\u064a\u0633 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a".toString());
        }
        if (!Intrinsics.areEqual(authenticatedSyncEnvelopeDecode.getStoreId(), state(readableDatabase, "store_id"))) {
            throw new IllegalArgumentException("\u0627\u0644\u0631\u0633\u0627\u0644\u0629 \u062a\u062e\u0635 \u0645\u062a\u062c\u0631\u0627\u064b \u0622\u062e\u0631".toString());
        }
        Cursor cursorRawQuery = readableDatabase.rawQuery("SELECT 1 FROM sync_devices WHERE device_id=? AND device_role='SECONDARY' AND status='ACTIVE'", new String[]{authenticatedSyncEnvelopeDecode.getOriginDeviceId()});
        try {
            boolean zMoveToFirst = cursorRawQuery.moveToFirst();
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (!zMoveToFirst) {
                throw new IllegalArgumentException("\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0631\u0633\u0644 \u063a\u064a\u0631 \u0645\u0633\u062c\u0644 \u0623\u0648 \u062a\u0645 \u0625\u0644\u063a\u0627\u0621 \u062a\u0633\u062c\u064a\u0644\u0647".toString());
            }
            byte[] bArrLoad = this.credentialVault.load(authenticatedSyncEnvelopeDecode.getOriginDeviceId());
            if (bArrLoad == null) {
                throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0631\u0633\u0644 \u063a\u064a\u0631 \u0645\u062a\u0648\u0641\u0631\u0629");
            }
            try {
                if (!SyncEnvelopeSecurity.INSTANCE.verify(authenticatedSyncEnvelopeDecode, bArrLoad)) {
                    throw new IllegalArgumentException("\u0641\u0634\u0644 \u0627\u0644\u062a\u062d\u0642\u0642 \u0645\u0646 \u062a\u0648\u0642\u064a\u0639 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0631\u0633\u0644".toString());
                }
                ArraysKt.fill$default(bArrLoad, (byte) 0, 0, 0, 6, (Object) null);
                return authenticatedSyncEnvelopeDecode;
            } catch (Throwable th) {
                ArraysKt.fill$default(bArrLoad, (byte) 0, 0, 0, 6, (Object) null);
                throw th;
            }
        } catch (Throwable th2) {
            try {
                throw th2;
            } catch (Throwable th3) {
                CloseableKt.closeFinally(cursorRawQuery, th2);
                throw th3;
            }
        }
    }

    public static /* synthetic */ List pendingStoreSettingsForDevice$default(SyncProtocolRepository syncProtocolRepository, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 100;
        }
        return syncProtocolRepository.pendingStoreSettingsForDevice(str, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final List<SyncOutboxItem> pendingStoreSettingsForDevice(String deviceId, int limit) throws IOException {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        int i = 1;
        if (1 > limit || limit >= 501) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        if (!Intrinsics.areEqual(state(readableDatabase, "local_device_role"), "PRIMARY") || !Intrinsics.areEqual(state(readableDatabase, "authority_device_id"), SyncDeviceIdentity.INSTANCE.getOrCreate(this.context))) {
            throw new IllegalArgumentException("\u062a\u0648\u0632\u064a\u0639 \u0625\u0639\u062f\u0627\u062f\u0627\u062a \u0627\u0644\u0645\u062a\u062c\u0631 \u0645\u062a\u0627\u062d \u0645\u0646 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0641\u0642\u0637".toString());
        }
        Cursor cursorRawQuery = readableDatabase.rawQuery("SELECT 1 FROM sync_devices WHERE device_id=? AND device_role='SECONDARY' AND status='ACTIVE'", new String[]{deviceId});
        try {
            boolean zMoveToFirst = cursorRawQuery.moveToFirst();
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (!zMoveToFirst) {
                throw new IllegalArgumentException("\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0633\u062a\u0644\u0645 \u063a\u064a\u0631 \u0645\u0633\u062c\u0644 \u0623\u0648 \u063a\u064a\u0631 \u0645\u0641\u0639\u0644".toString());
            }
            ArrayList arrayList = new ArrayList();
            Cursor cursorRawQuery2 = readableDatabase.rawQuery("SELECT e.operation_id,e.sequence_number,e.operation_type,e.entity_type,e.entity_id,\n                      e.payload_json,e.payload_sha256,e.created_at,q.retry_count\n               FROM sync_operation_envelopes e\n               JOIN sync_queue q ON q.operation_id=e.operation_id\n               WHERE e.entity_type='STORE_SETTINGS'\n                 AND NOT EXISTS (\n                   SELECT 1 FROM sync_acknowledgements a\n                   WHERE a.operation_id=e.operation_id AND a.device_id=? AND a.status='APPLIED'\n                 )\n               ORDER BY e.sequence_number LIMIT ?", new String[]{deviceId, String.valueOf(limit)});
            try {
                Cursor cursor = cursorRawQuery2;
                while (cursor.moveToNext()) {
                    String string = cursor.getString(0);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    long j = cursor.getLong(i);
                    String string2 = cursor.getString(2);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    String string3 = cursor.getString(3);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                    String string4 = cursor.getString(4);
                    Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                    String string5 = cursor.getString(5);
                    Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                    String string6 = cursor.getString(6);
                    Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                    String string7 = cursor.getString(7);
                    Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
                    arrayList.add(new SyncOutboxItem(string, j, string2, string3, string4, string5, string6, string7, cursor.getInt(8)));
                    i = 1;
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(cursorRawQuery2, null);
                return arrayList;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(cursorRawQuery2, th);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            try {
                throw th3;
            } catch (Throwable th4) {
                CloseableKt.closeFinally(cursorRawQuery, th3);
                throw th4;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final SyncInboxDisposition recordIncomingStoreSettingsFromPrimary(String encodedEnvelope) {
        Long longOrNull;
        Intrinsics.checkNotNullParameter(encodedEnvelope, "encodedEnvelope");
        AuthenticatedSyncEnvelope authenticatedSyncEnvelopeVerifyIncomingFromPrimary = verifyIncomingFromPrimary(encodedEnvelope);
        if (!Intrinsics.areEqual(authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getOperationType(), "UPDATE") || !Intrinsics.areEqual(authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getEntityType(), "STORE_SETTINGS") || !Intrinsics.areEqual(authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getEntityId(), "store_time_zone")) {
            throw new IllegalArgumentException("\u0646\u0648\u0639 \u0625\u0639\u062f\u0627\u062f \u0627\u0644\u0645\u062a\u062c\u0631 \u0627\u0644\u0648\u0627\u0631\u062f \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645".toString());
        }
        JSONObject jSONObject = new JSONObject(authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getPayloadJson());
        if (Intrinsics.areEqual(jSONObject.optString("business_operation"), "UPDATE_STORE_TIME_ZONE")) {
            StoreTime storeTime = StoreTime.INSTANCE;
            String strOptString = jSONObject.optString("time_zone");
            Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
            if (storeTime.isValidZoneId(strOptString)) {
                SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
                Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
                writableDatabase.beginTransaction();
                try {
                    Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT payload_sha256 FROM sync_inbox WHERE operation_id=?", new String[]{authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getOperationId()});
                    try {
                        Cursor cursor = cursorRawQuery;
                        String string = cursor.moveToFirst() ? cursor.getString(0) : null;
                        CloseableKt.closeFinally(cursorRawQuery, null);
                        if (string != null) {
                            if (Intrinsics.areEqual(string, authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getPayloadSha256())) {
                                writableDatabase.setTransactionSuccessful();
                                SyncInboxDisposition syncInboxDisposition = SyncInboxDisposition.DUPLICATE;
                                writableDatabase.endTransaction();
                                return syncInboxDisposition;
                            }
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("id", UUID.randomUUID().toString());
                            contentValues.put("operation_id", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getOperationId());
                            contentValues.put("entity_type", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getEntityType());
                            contentValues.put("entity_id", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getEntityId());
                            contentValues.put("local_payload_json", "{}");
                            contentValues.put("remote_payload_json", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getPayloadJson());
                            contentValues.put("created_at", timestamp$default(this, 0L, 1, null));
                            Unit unit = Unit.INSTANCE;
                            writableDatabase.insertOrThrow("sync_conflicts", null, contentValues);
                            writableDatabase.setTransactionSuccessful();
                            SyncInboxDisposition syncInboxDisposition2 = SyncInboxDisposition.CONFLICT;
                            writableDatabase.endTransaction();
                            return syncInboxDisposition2;
                        }
                        String strState = state(writableDatabase, "primary_store_settings_cursor");
                        if (authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getSequenceNumber() <= ((strState == null || (longOrNull = StringsKt.toLongOrNull(strState)) == null) ? 0L : longOrNull.longValue())) {
                            throw new IllegalArgumentException("\u062a\u0645 \u0631\u0641\u0636 \u062a\u062d\u062f\u064a\u062b \u0625\u0639\u062f\u0627\u062f \u0645\u062a\u062c\u0631 \u0623\u0642\u062f\u0645 \u0623\u0648 \u0645\u0639\u0627\u062f \u0627\u0644\u062a\u0634\u063a\u064a\u0644".toString());
                        }
                        String strTimestamp$default = timestamp$default(this, 0L, 1, null);
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("operation_id", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getOperationId());
                        contentValues2.put("origin_device_id", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getOriginDeviceId());
                        contentValues2.put("sequence_number", Long.valueOf(authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getSequenceNumber()));
                        contentValues2.put("operation_type", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getOperationType());
                        contentValues2.put("entity_type", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getEntityType());
                        contentValues2.put("entity_id", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getEntityId());
                        contentValues2.put("payload_json", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getPayloadJson());
                        contentValues2.put("payload_sha256", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getPayloadSha256());
                        contentValues2.put(NotificationCompat.CATEGORY_STATUS, "RECEIVED");
                        contentValues2.put("received_at", strTimestamp$default);
                        contentValues2.put("origin_created_at", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getCreatedAt());
                        Unit unit2 = Unit.INSTANCE;
                        writableDatabase.insertOrThrow("sync_inbox", null, contentValues2);
                        putState(writableDatabase, "primary_store_settings_cursor", String.valueOf(authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getSequenceNumber()), strTimestamp$default);
                        writableDatabase.setTransactionSuccessful();
                        SyncInboxDisposition syncInboxDisposition3 = SyncInboxDisposition.RECEIVED;
                        writableDatabase.endTransaction();
                        return syncInboxDisposition3;
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            CloseableKt.closeFinally(cursorRawQuery, th);
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    writableDatabase.endTransaction();
                    throw th3;
                }
            }
        }
        throw new IllegalArgumentException("\u062a\u062d\u062f\u064a\u062b \u0627\u0644\u0645\u0646\u0637\u0642\u0629 \u0627\u0644\u0632\u0645\u0646\u064a\u0629 \u0627\u0644\u0648\u0627\u0631\u062f \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final SyncInboxDisposition recordIncomingExchangeRateFromPrimary(String encodedEnvelope) {
        Long longOrNull;
        Intrinsics.checkNotNullParameter(encodedEnvelope, "encodedEnvelope");
        AuthenticatedSyncEnvelope authenticatedSyncEnvelopeVerifyIncomingFromPrimary = verifyIncomingFromPrimary(encodedEnvelope);
        if (!Intrinsics.areEqual(authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getOperationType(), "CREATE") || !Intrinsics.areEqual(authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getEntityType(), "EXCHANGE_RATE")) {
            throw new IllegalArgumentException("\u0646\u0648\u0639 \u062a\u062d\u062f\u064a\u062b \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0627\u0644\u0648\u0627\u0631\u062f \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645".toString());
        }
        JSONObject jSONObject = new JSONObject(authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getPayloadJson());
        if (Intrinsics.areEqual(jSONObject.optString("business_operation"), "SET_EXCHANGE_RATE") && Intrinsics.areEqual(jSONObject.optString("id"), authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getEntityId()) && Intrinsics.areEqual(jSONObject.optString("currency_code"), "USD") && jSONObject.optLong("rate_syp_per_usd_milli") > 0) {
            String strOptString = jSONObject.optString("effective_at");
            Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
            if (new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(strOptString)) {
                SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
                Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
                writableDatabase.beginTransaction();
                try {
                    Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT payload_sha256 FROM sync_inbox WHERE operation_id=?", new String[]{authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getOperationId()});
                    try {
                        Cursor cursor = cursorRawQuery;
                        String string = cursor.moveToFirst() ? cursor.getString(0) : null;
                        CloseableKt.closeFinally(cursorRawQuery, null);
                        if (string != null) {
                            if (!Intrinsics.areEqual(string, authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getPayloadSha256())) {
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("id", UUID.randomUUID().toString());
                                contentValues.put("operation_id", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getOperationId());
                                contentValues.put("entity_type", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getEntityType());
                                contentValues.put("entity_id", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getEntityId());
                                contentValues.put("local_payload_json", "{}");
                                contentValues.put("remote_payload_json", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getPayloadJson());
                                contentValues.put("created_at", timestamp$default(this, 0L, 1, null));
                                Unit unit = Unit.INSTANCE;
                                writableDatabase.insertOrThrow("sync_conflicts", null, contentValues);
                                writableDatabase.setTransactionSuccessful();
                                SyncInboxDisposition syncInboxDisposition = SyncInboxDisposition.CONFLICT;
                                writableDatabase.endTransaction();
                                return syncInboxDisposition;
                            }
                            writableDatabase.setTransactionSuccessful();
                            SyncInboxDisposition syncInboxDisposition2 = SyncInboxDisposition.DUPLICATE;
                            writableDatabase.endTransaction();
                            return syncInboxDisposition2;
                        }
                        String strState = state(writableDatabase, "primary_exchange_rate_cursor");
                        if (authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getSequenceNumber() <= ((strState == null || (longOrNull = StringsKt.toLongOrNull(strState)) == null) ? 0L : longOrNull.longValue())) {
                            throw new IllegalArgumentException("\u062a\u0645 \u0631\u0641\u0636 \u0633\u0639\u0631 \u0635\u0631\u0641 \u0623\u0642\u062f\u0645 \u0623\u0648 \u0645\u0639\u0627\u062f \u0627\u0644\u062a\u0634\u063a\u064a\u0644".toString());
                        }
                        String strTimestamp$default = timestamp$default(this, 0L, 1, null);
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("operation_id", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getOperationId());
                        contentValues2.put("origin_device_id", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getOriginDeviceId());
                        contentValues2.put("sequence_number", Long.valueOf(authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getSequenceNumber()));
                        contentValues2.put("operation_type", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getOperationType());
                        contentValues2.put("entity_type", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getEntityType());
                        contentValues2.put("entity_id", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getEntityId());
                        contentValues2.put("payload_json", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getPayloadJson());
                        contentValues2.put("payload_sha256", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getPayloadSha256());
                        contentValues2.put(NotificationCompat.CATEGORY_STATUS, "RECEIVED");
                        contentValues2.put("received_at", strTimestamp$default);
                        contentValues2.put("origin_created_at", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getCreatedAt());
                        Unit unit2 = Unit.INSTANCE;
                        writableDatabase.insertOrThrow("sync_inbox", null, contentValues2);
                        putState(writableDatabase, "primary_exchange_rate_cursor", String.valueOf(authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getSequenceNumber()), strTimestamp$default);
                        writableDatabase.setTransactionSuccessful();
                        SyncInboxDisposition syncInboxDisposition3 = SyncInboxDisposition.RECEIVED;
                        writableDatabase.endTransaction();
                        return syncInboxDisposition3;
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            CloseableKt.closeFinally(cursorRawQuery, th);
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    writableDatabase.endTransaction();
                    throw th3;
                }
            }
        }
        throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0627\u0644\u0648\u0627\u0631\u062f\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void acknowledgeExchangeRateApplied(String deviceId, String operationId) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        writableDatabase.beginTransaction();
        try {
            if (!Intrinsics.areEqual(state(writableDatabase, "local_device_role"), "PRIMARY") || !Intrinsics.areEqual(state(writableDatabase, "authority_device_id"), SyncDeviceIdentity.INSTANCE.getOrCreate(this.context))) {
                throw new IllegalArgumentException("\u062a\u062b\u0628\u064a\u062a \u0625\u0642\u0631\u0627\u0631 \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0645\u062a\u0627\u062d \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0641\u0642\u0637".toString());
            }
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT 1 FROM sync_devices d\n                   JOIN sync_operation_envelopes e ON e.operation_id=?\n                   WHERE d.device_id=? AND d.device_role='SECONDARY' AND d.status='ACTIVE'\n                     AND e.origin_device_id=? AND e.entity_type='EXCHANGE_RATE'", new String[]{operationId, deviceId, SyncDeviceIdentity.INSTANCE.getOrCreate(this.context)});
            try {
                boolean zMoveToFirst = cursorRawQuery.moveToFirst();
                CloseableKt.closeFinally(cursorRawQuery, null);
                if (!zMoveToFirst) {
                    throw new IllegalArgumentException("\u0625\u0642\u0631\u0627\u0631 \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u062c\u0647\u0627\u0632\u0627\u064b \u0623\u0648 \u0639\u0645\u0644\u064a\u0629 \u0645\u0633\u062c\u0644\u0629".toString());
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("operation_id", operationId);
                contentValues.put("device_id", deviceId);
                Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT sequence_number FROM sync_operation_envelopes WHERE operation_id=?", new String[]{operationId});
                try {
                    Cursor cursor = cursorRawQuery2;
                    if (cursor.moveToFirst()) {
                        Long lValueOf = Long.valueOf(cursor.getLong(0));
                        CloseableKt.closeFinally(cursorRawQuery2, null);
                        contentValues.put("sequence_number", lValueOf);
                        contentValues.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                        contentValues.put("acknowledged_at", timestamp$default(this, 0L, 1, null));
                        Unit unit = Unit.INSTANCE;
                        writableDatabase.insertWithOnConflict("sync_acknowledgements", null, contentValues, 5);
                        writableDatabase.setTransactionSuccessful();
                        writableDatabase.endTransaction();
                        return;
                    }
                    throw new IllegalArgumentException("Failed requirement.".toString());
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(cursorRawQuery2, th);
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                try {
                    throw th3;
                } catch (Throwable th4) {
                    CloseableKt.closeFinally(cursorRawQuery, th3);
                    throw th4;
                }
            }
        } catch (Throwable th5) {
            writableDatabase.endTransaction();
            throw th5;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void acknowledgeStoreSettingsApplied(String deviceId, String operationId) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        writableDatabase.beginTransaction();
        try {
            if (!Intrinsics.areEqual(state(writableDatabase, "local_device_role"), "PRIMARY") || !Intrinsics.areEqual(state(writableDatabase, "authority_device_id"), SyncDeviceIdentity.INSTANCE.getOrCreate(this.context))) {
                throw new IllegalArgumentException("\u062a\u062b\u0628\u064a\u062a \u0625\u0642\u0631\u0627\u0631 \u0625\u0639\u062f\u0627\u062f \u0627\u0644\u0645\u062a\u062c\u0631 \u0645\u062a\u0627\u062d \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0641\u0642\u0637".toString());
            }
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT 1 FROM sync_devices d\n                   JOIN sync_operation_envelopes e ON e.operation_id=?\n                   WHERE d.device_id=? AND d.device_role='SECONDARY' AND d.status='ACTIVE'\n                     AND e.origin_device_id=? AND e.entity_type='STORE_SETTINGS'", new String[]{operationId, deviceId, SyncDeviceIdentity.INSTANCE.getOrCreate(this.context)});
            try {
                boolean zMoveToFirst = cursorRawQuery.moveToFirst();
                CloseableKt.closeFinally(cursorRawQuery, null);
                if (!zMoveToFirst) {
                    throw new IllegalArgumentException("\u0625\u0642\u0631\u0627\u0631 \u0625\u0639\u062f\u0627\u062f \u0627\u0644\u0645\u062a\u062c\u0631 \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u062c\u0647\u0627\u0632\u0627\u064b \u0623\u0648 \u0639\u0645\u0644\u064a\u0629 \u0645\u0633\u062c\u0644\u0629".toString());
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("operation_id", operationId);
                contentValues.put("device_id", deviceId);
                Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT sequence_number FROM sync_operation_envelopes WHERE operation_id=?", new String[]{operationId});
                try {
                    Cursor cursor = cursorRawQuery2;
                    if (cursor.moveToFirst()) {
                        Long lValueOf = Long.valueOf(cursor.getLong(0));
                        CloseableKt.closeFinally(cursorRawQuery2, null);
                        contentValues.put("sequence_number", lValueOf);
                        contentValues.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                        contentValues.put("acknowledged_at", timestamp$default(this, 0L, 1, null));
                        Unit unit = Unit.INSTANCE;
                        writableDatabase.insertWithOnConflict("sync_acknowledgements", null, contentValues, 5);
                        writableDatabase.setTransactionSuccessful();
                        writableDatabase.endTransaction();
                        return;
                    }
                    throw new IllegalArgumentException("Failed requirement.".toString());
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(cursorRawQuery2, th);
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                try {
                    throw th3;
                } catch (Throwable th4) {
                    CloseableKt.closeFinally(cursorRawQuery, th3);
                    throw th4;
                }
            }
        } catch (Throwable th5) {
            writableDatabase.endTransaction();
            throw th5;
        }
    }

    public final boolean authenticateDevice(String deviceId, String credential) throws IOException {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(credential, "credential");
        if (StringsKt.isBlank(deviceId) || StringsKt.isBlank(credential)) {
            return false;
        }
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery("SELECT credential_hash FROM sync_devices WHERE device_id=? AND status='ACTIVE' AND device_role='SECONDARY'", new String[]{deviceId});
        try {
            Cursor cursor = cursorRawQuery;
            String string = cursor.moveToFirst() ? cursor.getString(0) : null;
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (string == null) {
                return false;
            }
            byte[] bytes = string.getBytes(Charsets.US_ASCII);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            byte[] bytes2 = sha256(credential).getBytes(Charsets.US_ASCII);
            Intrinsics.checkNotNullExpressionValue(bytes2, "getBytes(...)");
            return MessageDigest.isEqual(bytes, bytes2);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String revokeDevice(String actorUserId, String remoteDeviceId) throws Throwable {
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(remoteDeviceId, "remoteDeviceId");
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        writableDatabase.beginTransaction();
        try {
            requirePrimaryManager(writableDatabase, actorUserId);
            try {
                if (Intrinsics.areEqual(remoteDeviceId, SyncDeviceIdentity.INSTANCE.getOrCreate(this.context))) {
                    throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0625\u0644\u063a\u0627\u0621 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0645\u0646 \u0647\u0630\u0647 \u0627\u0644\u0634\u0627\u0634\u0629".toString());
                }
                Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT d.display_name,d.status,k.certificate_json\n                   FROM sync_devices d JOIN sync_device_public_keys k ON k.device_id=d.device_id\n                   WHERE d.device_id=? AND d.device_role='SECONDARY'", new String[]{remoteDeviceId});
                try {
                    Cursor cursor = cursorRawQuery;
                    try {
                        if (!cursor.moveToFirst()) {
                            throw new IllegalArgumentException("\u0627\u0644\u062c\u0647\u0627\u0632 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0623\u0648 \u0644\u0627 \u064a\u0645\u0644\u0643 \u0634\u0647\u0627\u062f\u0629".toString());
                        }
                        String string = cursor.getString(2);
                        Intrinsics.checkNotNull(string);
                        if (StringsKt.isBlank(string)) {
                            throw new IllegalArgumentException("\u0634\u0647\u0627\u062f\u0629 \u0627\u0644\u062c\u0647\u0627\u0632 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629".toString());
                        }
                        String strSerial = SyncDeviceCertificate.INSTANCE.serial(string);
                        KeyPair orCreate = SyncDeviceAuthenticator.INSTANCE.getOrCreate(this.context, SyncDeviceIdentity.INSTANCE.getOrCreate(this.context));
                        String strState = state(writableDatabase, "store_id");
                        if (strState == null) {
                            throw new IllegalStateException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0645\u062a\u062c\u0631 \u063a\u064a\u0631 \u0645\u0647\u064a\u0623".toString());
                        }
                        SyncDeviceCertificate syncDeviceCertificate = SyncDeviceCertificate.INSTANCE;
                        PrivateKey privateKey = orCreate.getPrivate();
                        Intrinsics.checkNotNullExpressionValue(privateKey, "getPrivate(...)");
                        String strSignedRevocation$default = SyncDeviceCertificate.signedRevocation$default(syncDeviceCertificate, strState, remoteDeviceId, strSerial, privateKey, 0L, 16, null);
                        String string2 = new JSONObject().put("display_name", cursor.getString(0)).put(NotificationCompat.CATEGORY_STATUS, cursor.getString(1)).toString();
                        CloseableKt.closeFinally(cursorRawQuery, null);
                        Intrinsics.checkNotNullExpressionValue(string2, "use(...)");
                        String strTimestamp$default = timestamp$default(this, 0L, 1, null);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(NotificationCompat.CATEGORY_STATUS, "REVOKED");
                        contentValues.put("revoked_at", strTimestamp$default);
                        Unit unit = Unit.INSTANCE;
                        writableDatabase.update("sync_devices", contentValues, "device_id=?", new String[]{remoteDeviceId});
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put(NotificationCompat.CATEGORY_STATUS, "REVOKED");
                        contentValues2.put("revoked_at", strTimestamp$default);
                        Unit unit2 = Unit.INSTANCE;
                        writableDatabase.update("sync_device_public_keys", contentValues2, "device_id=?", new String[]{remoteDeviceId});
                        if (strSignedRevocation$default == null) {
                            throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0625\u0646\u0634\u0627\u0621 \u0628\u064a\u0627\u0646 \u0625\u0644\u063a\u0627\u0621 \u0627\u0644\u0634\u0647\u0627\u062f\u0629".toString());
                        }
                        String string3 = new JSONObject(strSignedRevocation$default).getString("serial");
                        ContentValues contentValues3 = new ContentValues();
                        contentValues3.put("device_id", remoteDeviceId);
                        contentValues3.put("certificate_serial", string3);
                        contentValues3.put("revocation_json", strSignedRevocation$default);
                        contentValues3.put(NotificationCompat.CATEGORY_STATUS, "PENDING");
                        contentValues3.put("created_at", strTimestamp$default);
                        Unit unit3 = Unit.INSTANCE;
                        writableDatabase.insertWithOnConflict("sync_device_revocations", null, contentValues3, 4);
                        audit(writableDatabase, actorUserId, "SYNC_DEVICE_REVOKED", remoteDeviceId, string2, new JSONObject().put(NotificationCompat.CATEGORY_STATUS, "REVOKED").toString(), strTimestamp$default);
                        writableDatabase.setTransactionSuccessful();
                        writableDatabase.endTransaction();
                        this.credentialVault.remove(remoteDeviceId);
                        if (strSignedRevocation$default != null) {
                            return strSignedRevocation$default;
                        }
                        throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0625\u0646\u0634\u0627\u0621 \u0628\u064a\u0627\u0646 \u0625\u0644\u063a\u0627\u0621 \u0627\u0644\u0634\u0647\u0627\u062f\u0629".toString());
                        writableDatabase.endTransaction();
                        throw th;
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
                Throwable th3 = th;
                try {
                    throw th3;
                } catch (Throwable th4) {
                    CloseableKt.closeFinally(cursorRawQuery, th3);
                    throw th4;
                }
            } catch (Throwable th5) {
                th = th5;
            }
        } catch (Throwable th6) {
            th = th6;
        }
    }

    public final Long enqueueLocalOperation(SQLiteDatabase db, String actorUserId, String operationId, String operationType, String entityType, String entityId, String changeKind, String payloadJson) throws JSONException, NoSuchAlgorithmException, IOException {
        Long lValueOf;
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        Intrinsics.checkNotNullParameter(operationType, "operationType");
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        Intrinsics.checkNotNullParameter(entityId, "entityId");
        Intrinsics.checkNotNullParameter(changeKind, "changeKind");
        Intrinsics.checkNotNullParameter(payloadJson, "payloadJson");
        if (!db.inTransaction()) {
            throw new IllegalArgumentException("\u064a\u062c\u0628 \u062a\u0633\u062c\u064a\u0644 \u062d\u062f\u062b \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u0636\u0645\u0646 \u0645\u0639\u0627\u0645\u0644\u0629 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0627\u0644\u0623\u0635\u0644\u064a\u0629".toString());
        }
        if (StringsKt.isBlank(operationId) || StringsKt.isBlank(operationType) || StringsKt.isBlank(entityType) || StringsKt.isBlank(entityId)) {
            throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u062d\u062f\u062b \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u063a\u064a\u0631 \u0645\u0643\u062a\u0645\u0644\u0629".toString());
        }
        if (!SetsKt.setOf((Object[]) new String[]{"CREATE", "UPDATE", "DELETE"}).contains(changeKind)) {
            throw new IllegalArgumentException("\u0646\u0648\u0639 \u062a\u063a\u064a\u064a\u0631 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
        }
        int length = payloadJson.length();
        if (2 > length || length >= 131073) {
            throw new IllegalArgumentException("\u062d\u062c\u0645 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
        }
        JSONObject jSONObjectPut = new JSONObject(new JSONObject(payloadJson).toString()).put("business_operation", operationType);
        SyncEnvelopeSecurity syncEnvelopeSecurity = SyncEnvelopeSecurity.INSTANCE;
        String string = jSONObjectPut.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        String strCanonicalObject = syncEnvelopeSecurity.canonicalObject(string);
        String orCreate = SyncDeviceIdentity.INSTANCE.getOrCreate(this.context);
        if (!Intrinsics.areEqual(state(db, "authority_device_id"), orCreate) || !PRIMARY_TO_SECONDARY_REFERENCE_ENTITIES.contains(entityType)) {
            return null;
        }
        Cursor cursorRawQuery = db.rawQuery("SELECT 1 FROM sync_devices WHERE device_id=? AND device_role='PRIMARY' AND status='ACTIVE'", new String[]{orCreate});
        try {
            boolean zMoveToFirst = cursorRawQuery.moveToFirst();
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (!zMoveToFirst) {
                throw new IllegalStateException("\u0647\u0648\u064a\u0629 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u063a\u064a\u0631 \u0645\u0641\u0639\u0644\u0629".toString());
            }
            String strSha256 = sha256(strCanonicalObject);
            Cursor cursorRawQuery2 = db.rawQuery("SELECT sequence_number,payload_sha256,operation_type,entity_type,entity_id FROM sync_operation_envelopes WHERE operation_id=?", new String[]{operationId});
            try {
                Cursor cursor = cursorRawQuery2;
                if (!cursor.moveToFirst()) {
                    lValueOf = null;
                } else {
                    if (!Intrinsics.areEqual(cursor.getString(1), strSha256) || !Intrinsics.areEqual(cursor.getString(2), changeKind) || !Intrinsics.areEqual(cursor.getString(3), entityType) || !Intrinsics.areEqual(cursor.getString(4), entityId)) {
                        throw new IllegalArgumentException("\u0623\u0639\u064a\u062f \u0627\u0633\u062a\u062e\u062f\u0627\u0645 \u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0628\u0645\u062d\u062a\u0648\u0649 \u0645\u0632\u0627\u0645\u0646\u0629 \u0645\u062e\u062a\u0644\u0641".toString());
                    }
                    lValueOf = Long.valueOf(cursor.getLong(0));
                }
                CloseableKt.closeFinally(cursorRawQuery2, null);
                if (lValueOf != null) {
                    return lValueOf;
                }
                String strTimestamp$default = timestamp$default(this, 0L, 1, null);
                String strState = state(db, "next_sequence");
                if (strState == null) {
                    strState = "1";
                }
                long j = Long.parseLong(strState);
                if (j > 0 && j < Long.MAX_VALUE) {
                    putState(db, "next_sequence", String.valueOf(1 + j), strTimestamp$default);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("operation_id", operationId);
                    contentValues.put("sequence_number", Long.valueOf(j));
                    contentValues.put("origin_device_id", orCreate);
                    contentValues.put(SyncDeviceManagementActivity.EXTRA_ACTOR_USER_ID, actorUserId);
                    contentValues.put("operation_type", changeKind);
                    contentValues.put("entity_type", entityType);
                    contentValues.put("entity_id", entityId);
                    contentValues.put("payload_json", strCanonicalObject);
                    contentValues.put("payload_sha256", strSha256);
                    contentValues.put("created_at", strTimestamp$default);
                    Unit unit = Unit.INSTANCE;
                    db.insertOrThrow("sync_operation_envelopes", null, contentValues);
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("id", UUID.randomUUID().toString());
                    contentValues2.put("operation_id", operationId);
                    contentValues2.put("entity_type", entityType);
                    contentValues2.put("entity_id", entityId);
                    contentValues2.put("operation_type", changeKind);
                    contentValues2.put("payload_json", strCanonicalObject);
                    contentValues2.put(NotificationCompat.CATEGORY_STATUS, "PENDING");
                    contentValues2.put("retry_count", (Integer) 0);
                    contentValues2.put("created_at", strTimestamp$default);
                    contentValues2.put("updated_at", strTimestamp$default);
                    Unit unit2 = Unit.INSTANCE;
                    db.insertOrThrow("sync_queue", null, contentValues2);
                    ContentValues contentValues3 = new ContentValues();
                    contentValues3.put("last_seen_at", strTimestamp$default);
                    Unit unit3 = Unit.INSTANCE;
                    db.update("sync_devices", contentValues3, "device_id=?", new String[]{orCreate});
                    return Long.valueOf(j);
                }
                throw new IllegalStateException("\u062a\u0633\u0644\u0633\u0644 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(cursorRawQuery2, th);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            try {
                throw th3;
            } catch (Throwable th4) {
                CloseableKt.closeFinally(cursorRawQuery, th3);
                throw th4;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:58:0x0143  */
    public final Long enqueueCashAccountReference(SQLiteDatabase db, String actorUserId, String accountId) throws JSONException, NoSuchAlgorithmException, IOException {
        boolean z;
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        if (!db.inTransaction()) {
            throw new IllegalArgumentException("\u064a\u062c\u0628 \u062a\u0633\u062c\u064a\u0644 \u0645\u0631\u062c\u0639 \u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a \u0636\u0645\u0646 \u0645\u0639\u0627\u0645\u0644\u0629".toString());
        }
        Cursor cursorRawQuery = db.rawQuery("SELECT name,account_type,currency_code,is_active,created_at FROM cash_accounts WHERE id=?", new String[]{accountId});
        try {
            Cursor cursor = cursorRawQuery;
            if (!cursor.moveToFirst()) {
                throw new IllegalArgumentException("\u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0639\u0646\u062f \u062a\u062c\u0647\u064a\u0632 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629".toString());
            }
            String[] strArr = {cursor.getString(0), cursor.getString(1), cursor.getString(2), String.valueOf(cursor.getInt(3)), cursor.getString(4)};
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (!SetsKt.setOf((Object[]) new String[]{"CASH_BOX", "WALLET", "BANK", "OTHER"}).contains(strArr[1])) {
                throw new IllegalArgumentException("\u0646\u0648\u0639 \u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645 \u0644\u0644\u0645\u0632\u0627\u0645\u0646\u0629".toString());
            }
            if (!SetsKt.setOf((Object[]) new String[]{"USD", "SYP"}).contains(strArr[2])) {
                throw new IllegalArgumentException("\u0639\u0645\u0644\u0629 \u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u0629 \u0644\u0644\u0645\u0632\u0627\u0645\u0646\u0629".toString());
            }
            boolean zAreEqual = Intrinsics.areEqual(strArr[3], "1");
            Cursor cursorRawQuery2 = db.rawQuery("SELECT service_key FROM service_wallet_account_links WHERE account_id=?", new String[]{accountId});
            try {
                Cursor cursor2 = cursorRawQuery2;
                String string = cursor2.moveToFirst() ? cursor2.getString(0) : null;
                CloseableKt.closeFinally(cursorRawQuery2, null);
                if (string != null && (!SetsKt.setOf((Object[]) new String[]{ServiceTransferRepository.SYRIATEL_SHARED, "SHAM_CASH"}).contains(string) || !Intrinsics.areEqual(strArr[1], "WALLET") || !Intrinsics.areEqual(strArr[2], "SYP") || !zAreEqual)) {
                    throw new IllegalArgumentException("\u0631\u0628\u0637 \u0645\u062d\u0641\u0638\u0629 \u0627\u0644\u062e\u062f\u0645\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d \u0644\u0644\u0645\u0632\u0627\u0645\u0646\u0629".toString());
                }
                Cursor cursorRawQuery3 = db.rawQuery("SELECT account_id FROM system_cash_account_roles WHERE role_key='DAILY_DRAWER'", (String[]) null);
                try {
                    Cursor cursor3 = cursorRawQuery3;
                    String string2 = cursor3.moveToFirst() ? cursor3.getString(0) : null;
                    CloseableKt.closeFinally(cursorRawQuery3, null);
                    Cursor cursorRawQuery4 = db.rawQuery("SELECT account_id FROM system_cash_account_roles WHERE role_key='SUPPLIER_ROLLOVER'", (String[]) null);
                    try {
                        Cursor cursor4 = cursorRawQuery4;
                        String string3 = cursor4.moveToFirst() ? cursor4.getString(0) : null;
                        CloseableKt.closeFinally(cursorRawQuery4, null);
                        if (zAreEqual) {
                            String str = strArr[1];
                            if (Intrinsics.areEqual(str, "WALLET") || (Intrinsics.areEqual(str, "CASH_BOX") && (Intrinsics.areEqual(strArr[2], "USD") || (Intrinsics.areEqual(strArr[2], "SYP") && Intrinsics.areEqual(accountId, string2) && !Intrinsics.areEqual(accountId, string3))))) {
                                z = true;
                            } else {
                                z = false;
                            }
                        } else {
                            z = false;
                        }
                        int i = (zAreEqual && SetsKt.setOf((Object[]) new String[]{"CASH_BOX", "WALLET"}).contains(strArr[1])) ? 1 : 0;
                        JSONObject jSONObjectPut = new JSONObject().put("business_operation", "UPSERT_CASH_ACCOUNT").put("cash_account_id", accountId).put("name", strArr[0]).put("account_type", strArr[1]).put("currency_code", strArr[2]).put("is_active", zAreEqual).put("sales_receipt_allowed", z).put("created_at", strArr[4]);
                        if (string != null) {
                            jSONObjectPut.put("service_wallet_key", string);
                        }
                        String string4 = UUID.randomUUID().toString();
                        Intrinsics.checkNotNullExpressionValue(string4, "toString(...)");
                        String string5 = jSONObjectPut.toString();
                        Intrinsics.checkNotNullExpressionValue(string5, "toString(...)");
                        Long lEnqueueLocalOperation = enqueueLocalOperation(db, actorUserId, string4, "UPSERT_CASH_ACCOUNT", "CASH_ACCOUNT", accountId, "UPDATE", string5);
                        if (Intrinsics.areEqual(state(db, "authority_device_id"), SyncDeviceIdentity.INSTANCE.getOrCreate(this.context))) {
                            String strTimestamp$default = timestamp$default(this, 0L, 1, null);
                            Cursor cursorRawQuery5 = db.rawQuery("SELECT device_id FROM sync_devices WHERE device_role='SECONDARY' AND status='ACTIVE'", (String[]) null);
                            try {
                                Cursor cursor5 = cursorRawQuery5;
                                while (cursor5.moveToNext()) {
                                    String string6 = cursor5.getString(0);
                                    ContentValues contentValues = new ContentValues();
                                    contentValues.put("device_id", string6);
                                    contentValues.put("remote_cash_account_id", accountId);
                                    contentValues.put("primary_cash_account_id", accountId);
                                    contentValues.put("is_active", Integer.valueOf(i));
                                    contentValues.put("mapped_by", actorUserId);
                                    contentValues.put("mapped_at", strTimestamp$default);
                                    Unit unit = Unit.INSTANCE;
                                    db.insertWithOnConflict("sync_cash_account_mappings", null, contentValues, 5);
                                }
                                Unit unit2 = Unit.INSTANCE;
                                CloseableKt.closeFinally(cursorRawQuery5, null);
                            } catch (Throwable th) {
                                try {
                                    throw th;
                                } catch (Throwable th2) {
                                    CloseableKt.closeFinally(cursorRawQuery5, th);
                                    throw th2;
                                }
                            }
                        }
                        return lEnqueueLocalOperation;
                    } catch (Throwable th3) {
                        try {
                            throw th3;
                        } catch (Throwable th4) {
                            CloseableKt.closeFinally(cursorRawQuery4, th3);
                            throw th4;
                        }
                    }
                } catch (Throwable th5) {
                    try {
                        throw th5;
                    } catch (Throwable th6) {
                        CloseableKt.closeFinally(cursorRawQuery3, th5);
                        throw th6;
                    }
                }
            } catch (Throwable th7) {
                try {
                    throw th7;
                } catch (Throwable th8) {
                    CloseableKt.closeFinally(cursorRawQuery2, th7);
                    throw th8;
                }
            }
        } catch (Throwable th9) {
            try {
                throw th9;
            } catch (Throwable th10) {
                CloseableKt.closeFinally(cursorRawQuery, th9);
                throw th10;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void enqueueCatalogSnapshot(SQLiteDatabase db, String actorUserId) throws JSONException, NoSuchAlgorithmException, IOException {
        String str;
        int i;
        int i2;
        String str2;
        int i3;
        String str3;
        String str4;
        String str5;
        String str6;
        if (!db.inTransaction()) {
            throw new IllegalArgumentException("\u064a\u062c\u0628 \u0625\u0646\u0634\u0627\u0621 \u0644\u0642\u0637\u0629 \u0627\u0644\u062f\u0644\u064a\u0644 \u0636\u0645\u0646 \u0645\u0639\u0627\u0645\u0644\u0629 \u0627\u0644\u0627\u0642\u062a\u0631\u0627\u0646".toString());
        }
        Cursor cursorRawQuery = db.rawQuery("SELECT id,name,created_at FROM categories ORDER BY id", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            while (true) {
                str = "created_at";
                i = 2;
                i2 = 0;
                str2 = "toString(...)";
                i3 = 1;
                if (!cursor.moveToNext()) {
                    break;
                }
                long j = cursor.getLong(0);
                String string = cursor.getString(1);
                String string2 = cursor.getString(2);
                String string3 = UUID.randomUUID().toString();
                Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
                Intrinsics.checkNotNull(string);
                Locale ROOT = Locale.ROOT;
                Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
                String lowerCase = string.toLowerCase(ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                Cursor cursor2 = cursor;
                String string4 = new JSONObject().put("name", string).put("primary_category_id", j).put("created_at", string2).toString();
                Intrinsics.checkNotNullExpressionValue(string4, "toString(...)");
                enqueueLocalOperation(db, actorUserId, string3, "CREATE_CATEGORY", "CATEGORY", lowerCase, "CREATE", string4);
                cursor = cursor2;
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            Cursor cursorRawQuery2 = db.rawQuery("SELECT id,name,symbol,decimal_places FROM units ORDER BY id", (String[]) null);
            try {
                Cursor cursor3 = cursorRawQuery2;
                while (true) {
                    str3 = "unit_name";
                    str4 = "primary_unit_id";
                    if (!cursor3.moveToNext()) {
                        break;
                    }
                    long j2 = cursor3.getLong(i2);
                    Cursor cursor4 = cursor3;
                    JSONObject jSONObjectPut = new JSONObject().put("business_operation", "UPSERT_UNIT").put("primary_unit_id", j2).put("unit_name", cursor3.getString(i3)).put("unit_symbol", cursor3.getString(i)).put("decimal_places", cursor3.getInt(3));
                    String string5 = UUID.randomUUID().toString();
                    Intrinsics.checkNotNullExpressionValue(string5, str2);
                    String strValueOf = String.valueOf(j2);
                    String string6 = jSONObjectPut.toString();
                    Intrinsics.checkNotNullExpressionValue(string6, str2);
                    enqueueLocalOperation(db, actorUserId, string5, "UPSERT_UNIT", "UNIT", strValueOf, "CREATE", string6);
                    i2 = 0;
                    str2 = str2;
                    cursor3 = cursor4;
                    str = str;
                    i3 = 1;
                    i = 2;
                }
                String str7 = str2;
                String str8 = "unit_symbol";
                String str9 = str;
                int i4 = i2;
                int i5 = i3;
                Unit unit2 = Unit.INSTANCE;
                CloseableKt.closeFinally(cursorRawQuery2, null);
                Cursor cursorRawQuery3 = db.rawQuery("SELECT p.id,p.name,p.barcode,p.sku,p.category_id,c.name,u.name,u.symbol,u.decimal_places,\n                      p.purchase_cost_usd_cents,p.sale_price_usd_cents,p.track_inventory,p.is_active,\n                      p.created_at,p.updated_at,\n                      COALESCE((SELECT SUM(ib.quantity) FROM inventory_balances ib WHERE ib.product_id=p.id),0),\n                      p.unit_id\n               FROM products p JOIN units u ON u.id=p.unit_id\n               LEFT JOIN categories c ON c.id=p.category_id\n               ORDER BY p.id", (String[]) null);
                try {
                    Cursor cursor5 = cursorRawQuery3;
                    while (true) {
                        str5 = "updated_at";
                        str6 = "is_active";
                        if (!cursor5.moveToNext()) {
                            break;
                        }
                        String string7 = cursor5.getString(i4);
                        String str10 = str8;
                        JSONObject jSONObjectPut2 = new JSONObject().put("business_operation", "UPSERT_PRODUCT").put("product_id", string7).put("name", cursor5.getString(i5)).put("barcode", cursor5.isNull(2) ? JSONObject.NULL : cursor5.getString(2)).put("sku", cursor5.isNull(3) ? JSONObject.NULL : cursor5.getString(3)).put("primary_category_id", cursor5.isNull(4) ? JSONObject.NULL : Long.valueOf(cursor5.getLong(4))).put("category_name", cursor5.isNull(5) ? JSONObject.NULL : cursor5.getString(5)).put(str3, cursor5.getString(6)).put(str8, cursor5.getString(7)).put("unit_decimal_places", cursor5.getInt(8)).put(str4, cursor5.getLong(16)).put("purchase_cost_usd_cents", cursor5.getLong(9)).put("sale_price_usd_cents", cursor5.getLong(10)).put("tracks_inventory", cursor5.getInt(11) == 1);
                        String str11 = str9;
                        JSONObject jSONObjectPut3 = jSONObjectPut2.put("is_active", cursor5.getInt(12) == 1).put(str11, cursor5.getString(13)).put("updated_at", cursor5.getString(14)).put("opening_quantity", cursor5.getInt(11) == 1 ? Math.max(0.0d, cursor5.getDouble(15)) : 0.0d);
                        String string8 = UUID.randomUUID().toString();
                        Intrinsics.checkNotNullExpressionValue(string8, str7);
                        Intrinsics.checkNotNull(string7);
                        String string9 = jSONObjectPut3.toString();
                        Intrinsics.checkNotNullExpressionValue(string9, str7);
                        enqueueLocalOperation(db, actorUserId, string8, "UPSERT_PRODUCT", "PRODUCT", string7, "CREATE", string9);
                        str9 = str11;
                        i5 = 1;
                        str8 = str10;
                        str4 = str4;
                        str3 = str3;
                        cursor5 = cursor5;
                        str7 = str7;
                        i4 = 0;
                    }
                    int i6 = i5;
                    String str12 = str7;
                    String str13 = str9;
                    Unit unit3 = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursorRawQuery3, null);
                    Cursor cursorRawQuery4 = db.rawQuery("SELECT id,name,phone,address,notes,is_active,created_at,updated_at\n               FROM customers ORDER BY id", (String[]) null);
                    try {
                        Cursor cursor6 = cursorRawQuery4;
                        while (cursor6.moveToNext()) {
                            String string10 = cursor6.getString(0);
                            JSONObject jSONObjectPut4 = new JSONObject().put("business_operation", "UPSERT_CUSTOMER").put("customer_id", string10).put("name", cursor6.getString(i6)).put("phone", cursor6.isNull(2) ? JSONObject.NULL : cursor6.getString(2)).put("address", cursor6.isNull(3) ? JSONObject.NULL : cursor6.getString(3)).put("notes", cursor6.isNull(4) ? JSONObject.NULL : cursor6.getString(4)).put(str6, cursor6.getInt(5) == i6 ? i6 : 0).put(str13, cursor6.getString(6)).put(str5, cursor6.getString(7));
                            String string11 = UUID.randomUUID().toString();
                            String str14 = str12;
                            Intrinsics.checkNotNullExpressionValue(string11, str14);
                            Intrinsics.checkNotNull(string10);
                            String string12 = jSONObjectPut4.toString();
                            Intrinsics.checkNotNullExpressionValue(string12, str14);
                            enqueueLocalOperation(db, actorUserId, string11, "UPSERT_CUSTOMER", "CUSTOMER", string10, "CREATE", string12);
                            str12 = str14;
                            str5 = str5;
                            cursor6 = cursor6;
                            str6 = str6;
                            i6 = 1;
                        }
                        String str15 = str12;
                        Unit unit4 = Unit.INSTANCE;
                        CloseableKt.closeFinally(cursorRawQuery4, null);
                        Cursor cursorRawQuery5 = db.rawQuery("SELECT id FROM cash_accounts ORDER BY id", (String[]) null);
                        try {
                            Cursor cursor7 = cursorRawQuery5;
                            while (cursor7.moveToNext()) {
                                String string13 = cursor7.getString(0);
                                Intrinsics.checkNotNullExpressionValue(string13, "getString(...)");
                                try {
                                    enqueueCashAccountReference(db, actorUserId, string13);
                                } catch (Throwable th) {
                                    th = th;
                                    Throwable th2 = th;
                                    try {
                                        throw th2;
                                    } catch (Throwable th3) {
                                        CloseableKt.closeFinally(cursorRawQuery5, th2);
                                        throw th3;
                                    }
                                }
                            }
                            Unit unit5 = Unit.INSTANCE;
                            CloseableKt.closeFinally(cursorRawQuery5, null);
                            Cursor cursorRawQuery6 = db.rawQuery("SELECT id,currency_code,rate_syp_per_usd_milli,effective_at,created_at\n               FROM exchange_rates WHERE currency_code='USD'\n               ORDER BY effective_at DESC,created_at DESC LIMIT 1", (String[]) null);
                            try {
                                Cursor cursor8 = cursorRawQuery6;
                                JSONObject jSONObjectPut5 = !cursor8.moveToFirst() ? null : new JSONObject().put("business_operation", "SET_EXCHANGE_RATE").put("id", cursor8.getString(0)).put("currency_code", cursor8.getString(1)).put("rate_syp_per_usd_milli", cursor8.getLong(2)).put("effective_at", cursor8.getString(3)).put(str13, cursor8.getString(4));
                                CloseableKt.closeFinally(cursorRawQuery6, null);
                                if (jSONObjectPut5 != null) {
                                    String string14 = UUID.randomUUID().toString();
                                    Intrinsics.checkNotNullExpressionValue(string14, str15);
                                    String string15 = jSONObjectPut5.getString("id");
                                    Intrinsics.checkNotNullExpressionValue(string15, "getString(...)");
                                    String string16 = jSONObjectPut5.toString();
                                    Intrinsics.checkNotNullExpressionValue(string16, str15);
                                    enqueueLocalOperation(db, actorUserId, string14, "SET_EXCHANGE_RATE", "EXCHANGE_RATE", string15, "CREATE", string16);
                                }
                            } catch (Throwable th4) {
                                try {
                                    throw th4;
                                } catch (Throwable th5) {
                                    CloseableKt.closeFinally(cursorRawQuery6, th4);
                                    throw th5;
                                }
                            }
                        } catch (Throwable th6) {
                            th = th6;
                        }
                    } catch (Throwable th7) {
                        try {
                            throw th7;
                        } catch (Throwable th8) {
                            CloseableKt.closeFinally(cursorRawQuery4, th7);
                            throw th8;
                        }
                    }
                } catch (Throwable th9) {
                    try {
                        throw th9;
                    } catch (Throwable th10) {
                        CloseableKt.closeFinally(cursorRawQuery3, th9);
                        throw th10;
                    }
                }
            } catch (Throwable th11) {
                try {
                    throw th11;
                } catch (Throwable th12) {
                    CloseableKt.closeFinally(cursorRawQuery2, th11);
                    throw th12;
                }
            }
        } catch (Throwable th13) {
            try {
                throw th13;
            } catch (Throwable th14) {
                CloseableKt.closeFinally(cursorRawQuery, th13);
                throw th14;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:88:0x0209  */
    public final Long enqueueInvoicePosting(SQLiteDatabase db, String actorUserId, String operationId, String invoiceType, String invoiceId, String cashAccountId, String occurredAt, String operatingDate) throws JSONException, IOException {
        JSONObject jSONObject;
        String str;
        String strTimestamp$default;
        Object objOperatingDate;
        String str2;
        String str3;
        String str4;
        Throwable th;
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        Intrinsics.checkNotNullParameter(invoiceType, "invoiceType");
        Intrinsics.checkNotNullParameter(invoiceId, "invoiceId");
        String str5 = "SALE";
        if (!Intrinsics.areEqual(invoiceType, "SALE") && !Intrinsics.areEqual(invoiceType, "PURCHASE")) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        String str6 = Intrinsics.areEqual(invoiceType, "SALE") ? "sales_invoices" : "purchase_invoices";
        String str7 = Intrinsics.areEqual(invoiceType, "SALE") ? "sales_items" : "purchase_items";
        String str8 = Intrinsics.areEqual(invoiceType, "SALE") ? "sales_invoice_id" : "purchase_invoice_id";
        Cursor cursorRawQuery = db.rawQuery(StringsKt.trimIndent("SELECT id,invoice_number," + (Intrinsics.areEqual(invoiceType, "SALE") ? "customer_id,sale_type" : "supplier_id,'PURCHASE'") + ",\n                      warehouse_id,invoice_date,status,subtotal_usd_cents,discount_usd_cents,\n                      total_usd_cents,exchange_rate_syp_per_usd_milli,notes,created_by,\n                      " + (Intrinsics.areEqual(invoiceType, "SALE") ? "posted_at,posted_operating_date" : "updated_at,invoice_date") + "\n               FROM " + str6 + " WHERE id=?"), new String[]{invoiceId});
        try {
            Cursor cursor = cursorRawQuery;
            if (!cursor.moveToFirst()) {
                throw new IllegalArgumentException("\u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629 \u0639\u0646\u062f \u062a\u062c\u0647\u064a\u0632 \u062d\u062f\u062b \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629".toString());
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("id", cursor.getString(0));
            String str9 = "id";
            jSONObject2.put("invoice_number", cursor.getString(1));
            jSONObject2.put("party_id", cursor.isNull(2) ? JSONObject.NULL : cursor.getString(2));
            jSONObject2.put("sale_type", cursor.getString(3));
            jSONObject2.put("warehouse_id", cursor.getString(4));
            jSONObject2.put("invoice_date", cursor.getString(5));
            jSONObject2.put(NotificationCompat.CATEGORY_STATUS, cursor.getString(6));
            String str10 = str8;
            jSONObject2.put("subtotal_usd_cents", cursor.getLong(7));
            jSONObject2.put("discount_usd_cents", cursor.getLong(8));
            jSONObject2.put("total_usd_cents", cursor.getLong(9));
            jSONObject2.put("exchange_rate_syp_per_usd_milli", cursor.getLong(10));
            jSONObject2.put("notes", cursor.isNull(11) ? JSONObject.NULL : cursor.getString(11));
            jSONObject2.put("created_by", cursor.isNull(12) ? JSONObject.NULL : cursor.getString(12));
            jSONObject2.put("posted_at", cursor.getString(13));
            jSONObject2.put("posted_operating_date", cursor.getString(14));
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (Intrinsics.areEqual(state(db, "local_device_role"), "SECONDARY")) {
                Cursor cursorRawQuery2 = db.rawQuery("SELECT payload_json FROM sync_operation_envelopes WHERE operation_id=?", new String[]{operationId});
                try {
                    Cursor cursor2 = cursorRawQuery2;
                    if (cursor2.moveToFirst()) {
                        jSONObject = new JSONObject(cursor2.getString(0));
                        th = null;
                    } else {
                        th = null;
                        jSONObject = null;
                    }
                    CloseableKt.closeFinally(cursorRawQuery2, th);
                } catch (Throwable th2) {
                    try {
                        throw th2;
                    } catch (Throwable th3) {
                        CloseableKt.closeFinally(cursorRawQuery2, th2);
                        throw th3;
                    }
                }
            } else {
                jSONObject = null;
            }
            if (occurredAt == null) {
                if (jSONObject == null || (strTimestamp$default = jSONObject.optString("posted_at")) == null || StringsKt.isBlank(strTimestamp$default) || Intrinsics.areEqual(strTimestamp$default, "null")) {
                    strTimestamp$default = null;
                }
                if (strTimestamp$default == null) {
                    str = "local_device_role";
                    strTimestamp$default = timestamp$default(this, 0L, 1, null);
                } else {
                    str = "local_device_role";
                }
            } else {
                str = "local_device_role";
                strTimestamp$default = occurredAt;
            }
            if (operatingDate == null) {
                if (jSONObject == null || (objOperatingDate = jSONObject.optString("posted_operating_date")) == null) {
                    objOperatingDate = null;
                } else if (!new Regex("\\d{4}-\\d{2}-\\d{2}").matches((CharSequence) objOperatingDate)) {
                    objOperatingDate = null;
                }
                if (objOperatingDate == null) {
                    objOperatingDate = StoreTime.INSTANCE.operatingDate(db, strTimestamp$default);
                }
            } else {
                objOperatingDate = operatingDate;
            }
            jSONObject2.put("posted_at", strTimestamp$default);
            jSONObject2.put("posted_operating_date", objOperatingDate);
            JSONArray jSONArray = new JSONArray();
            Cursor cursorRawQuery3 = db.rawQuery(StringsKt.trimIndent("SELECT id,product_id,quantity," + (Intrinsics.areEqual(invoiceType, "SALE") ? "unit_price_usd_cents,unit_cost_usd_cents" : "unit_cost_usd_cents,unit_cost_usd_cents") + ",line_total_usd_cents\n               FROM " + str7 + " WHERE " + str10 + "=? ORDER BY id"), new String[]{invoiceId});
            try {
                Cursor cursor3 = cursorRawQuery3;
                while (cursor3.moveToNext()) {
                    try {
                        JSONObject jSONObject3 = new JSONObject();
                        String str11 = str9;
                        jSONObject3.put(str11, cursor3.getString(0));
                        jSONObject3.put("product_id", cursor3.getString(1));
                        str9 = str11;
                        jSONObject3.put("quantity", cursor3.getDouble(2));
                        jSONObject3.put("unit_price_or_cost_usd_cents", cursor3.getLong(3));
                        jSONObject3.put("unit_cost_usd_cents", cursor3.getLong(4));
                        jSONObject3.put("line_total_usd_cents", cursor3.getLong(5));
                        jSONArray.put(jSONObject3);
                    } catch (Throwable th4) {
                        th = th4;
                        Throwable th5 = th;
                        try {
                            throw th5;
                        } catch (Throwable th6) {
                            CloseableKt.closeFinally(cursorRawQuery3, th5);
                            throw th6;
                        }
                    }
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(cursorRawQuery3, null);
                String str12 = "POST_";
                StringBuilder sb = new StringBuilder("POST_");
                sb.append(invoiceType);
                String str13 = "_INVOICE";
                sb.append("_INVOICE");
                jSONObject2.put("business_operation", sb.toString());
                jSONObject2.put("items", jSONArray);
                jSONObject2.put("cash_account_id", cashAccountId == null ? JSONObject.NULL : cashAccountId);
                if (!Intrinsics.areEqual(invoiceType, "SALE") || cashAccountId == null) {
                    str2 = "POST_";
                    str3 = "_INVOICE";
                    str4 = "SALE";
                } else {
                    Cursor cursorRawQuery4 = db.rawQuery("SELECT account_type,currency_code,is_active FROM cash_accounts WHERE id=?", new String[]{cashAccountId});
                    try {
                        Cursor cursor4 = cursorRawQuery4;
                        if (!cursor4.moveToFirst() || cursor4.getInt(2) != 1) {
                            throw new IllegalArgumentException("\u062d\u0633\u0627\u0628 \u0642\u0628\u0636 \u0627\u0644\u0628\u064a\u0639 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0623\u0648 \u0645\u0648\u0642\u0648\u0641".toString());
                        }
                        Pair pair = TuplesKt.to(cursor4.getString(0), cursor4.getString(1));
                        CloseableKt.closeFinally(cursorRawQuery4, null);
                        jSONObject2.put("cash_account_type", pair.getFirst());
                        jSONObject2.put("cash_account_currency_code", pair.getSecond());
                        JSONArray jSONArray2 = new JSONArray();
                        Cursor cursorRawQuery5 = db.rawQuery("SELECT id,amount_minor,currency_code,exchange_rate_syp_per_usd_milli\n                   FROM cash_transactions WHERE source_type='SALES_INVOICE' AND source_id=?\n                     AND transaction_type='SALE_RECEIPT' ORDER BY created_at,id", new String[]{invoiceId});
                        try {
                            Cursor cursor5 = cursorRawQuery5;
                            while (cursor5.moveToNext()) {
                                JSONObject jSONObject4 = new JSONObject();
                                String str14 = str5;
                                jSONObject4.put("cash_transaction_id", cursor5.getString(0));
                                String str15 = str12;
                                String str16 = str13;
                                jSONObject4.put("amount_minor", cursor5.getLong(1));
                                jSONObject4.put("currency_code", cursor5.getString(2));
                                if (cursor5.isNull(3)) {
                                    jSONObject4.put("exchange_rate_syp_per_usd_milli", JSONObject.NULL);
                                } else {
                                    jSONObject4.put("exchange_rate_syp_per_usd_milli", cursor5.getLong(3));
                                }
                                jSONArray2.put(jSONObject4);
                                str12 = str15;
                                str13 = str16;
                                str5 = str14;
                            }
                            str2 = str12;
                            str3 = str13;
                            str4 = str5;
                            Unit unit2 = Unit.INSTANCE;
                            CloseableKt.closeFinally(cursorRawQuery5, null);
                            if (jSONArray2.length() == 0) {
                                long jUsdCentsToSyp = jSONObject2.getLong("total_usd_cents");
                                long j = jSONObject2.getLong("exchange_rate_syp_per_usd_milli");
                                String str17 = (String) pair.getSecond();
                                if (!Intrinsics.areEqual(str17, "USD")) {
                                    if (!Intrinsics.areEqual(str17, "SYP")) {
                                        throw new IllegalArgumentException("\u0639\u0645\u0644\u0629 \u062d\u0633\u0627\u0628 \u0627\u0644\u0642\u0628\u0636 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u0629");
                                    }
                                    jUsdCentsToSyp = CurrencyConverter.INSTANCE.usdCentsToSyp(jUsdCentsToSyp, j);
                                }
                                JSONObject jSONObject5 = new JSONObject();
                                jSONObject5.put("cash_transaction_id", "pending-cash-" + operationId);
                                jSONObject5.put("amount_minor", jUsdCentsToSyp);
                                jSONObject5.put("currency_code", str17);
                                jSONObject5.put("exchange_rate_syp_per_usd_milli", j);
                                jSONArray2.put(jSONObject5);
                            }
                            jSONObject2.put("cash_receipts", jSONArray2);
                        } catch (Throwable th7) {
                            try {
                                throw th7;
                            } catch (Throwable th8) {
                                CloseableKt.closeFinally(cursorRawQuery5, th7);
                                throw th8;
                            }
                        }
                    } catch (Throwable th9) {
                        try {
                            throw th9;
                        } catch (Throwable th10) {
                            CloseableKt.closeFinally(cursorRawQuery4, th9);
                            throw th10;
                        }
                    }
                }
                if (Intrinsics.areEqual(state(db, str), "SECONDARY")) {
                    String str18 = str2 + invoiceType + str3;
                    String str19 = Intrinsics.areEqual(invoiceType, str4) ? "SALES_INVOICE" : "PURCHASE_INVOICE";
                    String string = jSONObject2.toString();
                    Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                    return Long.valueOf(enqueueSecondaryCommand(db, actorUserId, operationId, str18, str19, invoiceId, string));
                }
                String str20 = str2 + invoiceType + str3;
                String str21 = Intrinsics.areEqual(invoiceType, str4) ? "SALES_INVOICE" : "PURCHASE_INVOICE";
                String string2 = jSONObject2.toString();
                Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                return enqueueLocalOperation(db, actorUserId, operationId, str20, str21, invoiceId, "CREATE", string2);
            } catch (Throwable th11) {
                th = th11;
            }
        } catch (Throwable th12) {
            try {
                throw th12;
            } catch (Throwable th13) {
                CloseableKt.closeFinally(cursorRawQuery, th12);
                throw th13;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String createEncryptedApplicationAck(String recipientDeviceId, String operationId) throws JSONException, IOException {
        Object objM381constructorimpl;
        Intrinsics.checkNotNullParameter(recipientDeviceId, "recipientDeviceId");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        if (!StringsKt.isBlank(operationId)) {
            try {
                Result.Companion companion = Result.INSTANCE;
                SyncProtocolRepository syncProtocolRepository = this;
                objM381constructorimpl = Result.m381constructorimpl(UUID.fromString(recipientDeviceId));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m388isSuccessimpl(objM381constructorimpl)) {
                SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
                Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
                String orCreate = SyncDeviceIdentity.INSTANCE.getOrCreate(this.context);
                String strState = state(readableDatabase, "local_device_role");
                String strState2 = state(readableDatabase, "authority_device_id");
                if ((!Intrinsics.areEqual(strState, "PRIMARY") || !Intrinsics.areEqual(strState2, orCreate)) && (!Intrinsics.areEqual(strState, "SECONDARY") || !Intrinsics.areEqual(strState2, recipientDeviceId))) {
                    throw new IllegalArgumentException("\u0625\u0642\u0631\u0627\u0631 \u0627\u0644\u062a\u0637\u0628\u064a\u0642 \u064a\u0635\u062f\u0631 \u0641\u0642\u0637 \u0628\u064a\u0646 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0648\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u0627\u0644\u0645\u0639\u062a\u0645\u062f".toString());
                }
                Cursor cursorRawQuery = readableDatabase.rawQuery("SELECT 1 FROM sync_devices WHERE device_id=? AND status='ACTIVE' AND device_role=?", new String[]{recipientDeviceId, Intrinsics.areEqual(strState, "PRIMARY") ? "SECONDARY" : "PRIMARY"});
                try {
                    boolean zMoveToFirst = cursorRawQuery.moveToFirst();
                    CloseableKt.closeFinally(cursorRawQuery, null);
                    if (!zMoveToFirst) {
                        throw new IllegalArgumentException("\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0633\u062a\u0644\u0645 \u063a\u064a\u0631 \u0645\u0633\u062c\u0644 \u0623\u0648 \u0645\u0648\u0642\u0648\u0641".toString());
                    }
                    Cursor cursorRawQuery2 = readableDatabase.rawQuery("SELECT i.sequence_number,i.payload_sha256,i.status,i.origin_device_id,\n                      (SELECT r.result_reference FROM operation_registry r\n                       WHERE r.operation_id=i.operation_id ORDER BY r.created_at DESC LIMIT 1)\n               FROM sync_inbox i WHERE i.operation_id=?", new String[]{operationId});
                    try {
                        Cursor cursor = cursorRawQuery2;
                        if (!cursor.moveToFirst()) {
                            throw new IllegalArgumentException("\u0639\u0645\u0644\u064a\u0629 \u0627\u0644\u0625\u0642\u0631\u0627\u0631 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629 \u0641\u064a \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0648\u0627\u0631\u062f".toString());
                        }
                        String[] strArr = {String.valueOf(cursor.getLong(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.isNull(4) ? "" : cursor.getString(4)};
                        CloseableKt.closeFinally(cursorRawQuery2, null);
                        if (!Intrinsics.areEqual(strArr[2], "APPLIED") || !Intrinsics.areEqual(strArr[3], recipientDeviceId)) {
                            throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0625\u0635\u062f\u0627\u0631 \u0625\u0642\u0631\u0627\u0631 \u062a\u0637\u0628\u064a\u0642 \u0642\u0628\u0644 \u0646\u062c\u0627\u062d \u0627\u0644\u0645\u0639\u0627\u0644\u062c\u0629 \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0633\u062a\u0644\u0645".toString());
                        }
                        String strState3 = state(readableDatabase, "store_id");
                        if (strState3 == null) {
                            throw new IllegalStateException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0645\u062a\u062c\u0631 \u063a\u064a\u0631 \u0645\u0647\u064a\u0623".toString());
                        }
                        String strTimestamp$default = timestamp$default(this, 0L, 1, null);
                        JSONObject jSONObjectPut = new JSONObject().put("ack_version", 1).put("ack_status", "APPLIED").put("store_id", strState3).put("device_id", recipientDeviceId).put("operation_id", operationId);
                        String str = strArr[0];
                        Intrinsics.checkNotNullExpressionValue(str, "get(...)");
                        JSONObject jSONObjectPut2 = jSONObjectPut.put("sequence_number", Long.parseLong(str)).put("payload_sha256", strArr[1]);
                        String str2 = strArr[4];
                        Intrinsics.checkNotNullExpressionValue(str2, "get(...)");
                        JSONObject jSONObjectPut3 = jSONObjectPut2.put("result_reference", StringsKt.isBlank(str2) ? JSONObject.NULL : strArr[4]).put("acknowledged_at", strTimestamp$default);
                        byte[] bArrLoad = this.credentialVault.load(Intrinsics.areEqual(strState, "PRIMARY") ? recipientDeviceId : orCreate);
                        if (bArrLoad == null) {
                            throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0633\u062a\u0644\u0645 \u063a\u064a\u0631 \u0645\u062a\u0648\u0641\u0631\u0629");
                        }
                        try {
                            SyncEnvelopeCipher.PeerContext peerContext = new SyncEnvelopeCipher.PeerContext(strState3, orCreate, recipientDeviceId, operationId);
                            SyncEnvelopeCipher syncEnvelopeCipher = SyncEnvelopeCipher.INSTANCE;
                            String string = jSONObjectPut3.toString();
                            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                            byte[] bytes = string.getBytes(Charsets.UTF_8);
                            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                            JSONObject jSONObjectPut4 = new JSONObject().put("frame_version", 1).put("frame_type", "APPLICATION_ACK").put("store_id", strState3).put("sender_device_id", orCreate).put("recipient_device_id", recipientDeviceId).put("operation_id", operationId).put("ciphertext", Base64.encodeToString(syncEnvelopeCipher.encrypt(bytes, new SecretKeySpec(bArrLoad, "AES"), peerContext), 11));
                            KeyPair orCreate2 = SyncDeviceAuthenticator.INSTANCE.getOrCreate(this.context, orCreate);
                            SyncDeviceAuthenticator.SigningContext signingContext = new SyncDeviceAuthenticator.SigningContext(strState3, orCreate, recipientDeviceId, operationId);
                            SyncDeviceAuthenticator syncDeviceAuthenticator = SyncDeviceAuthenticator.INSTANCE;
                            PrivateKey privateKey = orCreate2.getPrivate();
                            Intrinsics.checkNotNullExpressionValue(privateKey, "getPrivate(...)");
                            String string2 = jSONObjectPut4.toString();
                            Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                            byte[] bytes2 = string2.getBytes(Charsets.UTF_8);
                            Intrinsics.checkNotNullExpressionValue(bytes2, "getBytes(...)");
                            byte[] bArrSign = syncDeviceAuthenticator.sign(privateKey, signingContext, bytes2);
                            JSONObject jSONObjectPut5 = jSONObjectPut4.put("signature_algorithm", "ECDSA_P256_SHA256");
                            SyncDeviceAuthenticator syncDeviceAuthenticator2 = SyncDeviceAuthenticator.INSTANCE;
                            byte[] encoded = orCreate2.getPublic().getEncoded();
                            Intrinsics.checkNotNullExpressionValue(encoded, "getEncoded(...)");
                            jSONObjectPut5.put("sender_key_fingerprint", syncDeviceAuthenticator2.fingerprint(encoded)).put("signature", Base64.encodeToString(bArrSign, 2));
                            String string3 = jSONObjectPut4.toString();
                            Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
                            return string3;
                        } finally {
                            ArraysKt.fill$default(bArrLoad, (byte) 0, 0, 0, 6, (Object) null);
                        }
                    } catch (Throwable th2) {
                        try {
                            throw th2;
                        } catch (Throwable th3) {
                            CloseableKt.closeFinally(cursorRawQuery2, th2);
                            throw th3;
                        }
                    }
                } catch (Throwable th4) {
                    try {
                        throw th4;
                    } catch (Throwable th5) {
                        CloseableKt.closeFinally(cursorRawQuery, th4);
                        throw th5;
                    }
                }
            }
        }
        throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u062c\u0647\u0627\u0632 \u0623\u0648 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
    }

    /* JADX WARN: Code duplicated, block: B:229:0x05a0  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v5 */
    /* JADX WARN: Type inference failed for: r15v6, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r15v7, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r16v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r16v2 */
    /* JADX WARN: Type inference failed for: r16v3 */
    /* JADX WARN: Type inference failed for: r16v4 */
    /* JADX WARN: Type inference failed for: r16v9 */
    /* JADX WARN: Type inference failed for: r25v0, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r4v7, types: [org.json.JSONObject] */
    /* JADX WARN: Type inference failed for: r6v38, types: [android.content.ContentValues] */
    /* JADX WARN: Type inference failed for: r6v4, types: [com.hazem.dailystore.data.AppDatabase] */
    public final SyncApplicationAckDisposition acceptEncryptedApplicationAck(String frameJson) throws Throwable {
        ?? r15;
        Throwable th;
        Throwable th2;
        String[] strArr;
        SyncApplicationAckDisposition syncApplicationAckDisposition;
        int i;
        SyncProtocolRepository syncProtocolRepository;
        Intrinsics.checkNotNullParameter(frameJson, "frameJson");
        int length = frameJson.length();
        if (2 > length || length >= 700001) {
            throw new IllegalArgumentException("\u062d\u062c\u0645 \u0625\u0642\u0631\u0627\u0631 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
        }
        JSONObject jSONObject = new JSONObject(frameJson);
        if (jSONObject.getInt("frame_version") != 1 || !Intrinsics.areEqual(jSONObject.getString("frame_type"), "APPLICATION_ACK")) {
            throw new IllegalArgumentException("\u0646\u0648\u0639 \u0623\u0648 \u0625\u0635\u062f\u0627\u0631 \u0625\u0642\u0631\u0627\u0631 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645".toString());
        }
        String string = jSONObject.getString("store_id");
        String string2 = jSONObject.getString("sender_device_id");
        String string3 = jSONObject.getString("recipient_device_id");
        String string4 = jSONObject.getString("operation_id");
        String orCreate = SyncDeviceIdentity.INSTANCE.getOrCreate(this.context);
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        String strState = state(writableDatabase, "local_device_role");
        String strState2 = state(writableDatabase, "authority_device_id");
        if (!Intrinsics.areEqual(string, state(writableDatabase, "store_id")) || !Intrinsics.areEqual(string3, orCreate) || ((!Intrinsics.areEqual(strState, "SECONDARY") || !Intrinsics.areEqual(string2, strState2)) && (!Intrinsics.areEqual(strState, "PRIMARY") || !Intrinsics.areEqual(strState2, orCreate) || Intrinsics.areEqual(string2, orCreate)))) {
            throw new IllegalArgumentException("\u0647\u0648\u064a\u0629 \u0645\u0631\u0633\u0644 \u0623\u0648 \u0645\u0633\u062a\u0644\u0645 \u0625\u0642\u0631\u0627\u0631 \u0627\u0644\u062a\u0637\u0628\u064a\u0642 \u0644\u0627 \u062a\u0637\u0627\u0628\u0642 \u0625\u0639\u062f\u0627\u062f\u0627\u062a \u0627\u0644\u062c\u0647\u0627\u0632".toString());
        }
        Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT 1 FROM sync_devices WHERE device_id=? AND status='ACTIVE' AND device_role=?", new String[]{string2, Intrinsics.areEqual(strState, "PRIMARY") ? "SECONDARY" : "PRIMARY"});
        try {
            boolean zMoveToFirst = cursorRawQuery.moveToFirst();
            CloseableKt.closeFinally(cursorRawQuery, null);
            if (!zMoveToFirst) {
                throw new IllegalArgumentException("\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0642\u0631 \u063a\u064a\u0631 \u0645\u0633\u062c\u0644 \u0623\u0648 \u0645\u0648\u0642\u0648\u0641".toString());
            }
            Intrinsics.checkNotNull(string);
            Intrinsics.checkNotNull(string2);
            Intrinsics.checkNotNull(string4);
            ?? r16 = "sequence_number";
            verifyPeerFrameSignature(jSONObject, string, string2, string3, string4);
            String str = Intrinsics.areEqual(strState, "SECONDARY") ? orCreate : string2;
            SyncCredentialVault syncCredentialVault = this.credentialVault;
            Intrinsics.checkNotNull(str);
            byte[] bArrLoad = syncCredentialVault.load(str);
            if (bArrLoad == null) {
                throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0642\u0627\u0628\u0644 \u063a\u064a\u0631 \u0645\u062a\u0648\u0641\u0631\u0629");
            }
            try {
                SyncEnvelopeCipher.PeerContext peerContext = new SyncEnvelopeCipher.PeerContext(string, string2, string3, string4);
                byte[] bArrDecode = Base64.decode(jSONObject.getString("ciphertext"), 11);
                SyncEnvelopeCipher syncEnvelopeCipher = SyncEnvelopeCipher.INSTANCE;
                Intrinsics.checkNotNull(bArrDecode);
                byte[] bArrDecrypt = syncEnvelopeCipher.decrypt(bArrDecode, new SecretKeySpec(bArrLoad, "AES"), peerContext);
                try {
                    if (bArrDecrypt == null) {
                        throw new IllegalArgumentException("\u0641\u0634\u0644 \u0627\u0644\u062a\u062d\u0642\u0642 \u0645\u0646 \u062a\u0634\u0641\u064a\u0631 \u0625\u0642\u0631\u0627\u0631 \u0627\u0644\u062a\u0637\u0628\u064a\u0642");
                    }
                    ?? jSONObject2 = new JSONObject(new String(bArrDecrypt, Charsets.UTF_8));
                    if (jSONObject2.getInt("ack_version") != 1 || !Intrinsics.areEqual(jSONObject2.getString("ack_status"), "APPLIED") || !Intrinsics.areEqual(jSONObject2.getString("store_id"), string) || !Intrinsics.areEqual(jSONObject2.getString("device_id"), orCreate) || !Intrinsics.areEqual(jSONObject2.getString("operation_id"), string4)) {
                        throw new IllegalArgumentException("\u0645\u062d\u062a\u0648\u0649 \u0625\u0642\u0631\u0627\u0631 \u0627\u0644\u062a\u0637\u0628\u064a\u0642 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d \u0623\u0648 \u0644\u0627 \u064a\u062e\u0635 \u0647\u0630\u0627 \u0627\u0644\u062c\u0647\u0627\u0632".toString());
                    }
                    long j = jSONObject2.getLong(r16);
                    String string5 = jSONObject2.getString("payload_sha256");
                    if (j > 0) {
                        Intrinsics.checkNotNull(string5);
                        if (new Regex("[0-9a-f]{64}").matches(string5)) {
                            String string6 = jSONObject2.getString("acknowledged_at");
                            Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                            byte[] bArr = bArrLoad;
                            try {
                                if (new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(string6)) {
                                    writableDatabase.beginTransaction();
                                    try {
                                        r15 = writableDatabase;
                                        try {
                                            try {
                                                Cursor cursorRawQuery2 = r15.rawQuery("SELECT e.sequence_number,e.payload_sha256,e.origin_device_id,q.status,\n                              e.entity_type,e.operation_type,e.entity_id,e.payload_json\n                       FROM sync_operation_envelopes e JOIN sync_queue q ON q.operation_id=e.operation_id\n                       WHERE e.operation_id=?", new String[]{string4});
                                                try {
                                                    Cursor cursor = cursorRawQuery2;
                                                    if (!cursor.moveToFirst()) {
                                                        try {
                                                            throw new IllegalArgumentException("\u0644\u0627 \u064a\u0648\u062c\u062f \u0623\u0645\u0631 \u0645\u062d\u0644\u064a \u0645\u0637\u0627\u0628\u0642 \u0644\u0625\u0642\u0631\u0627\u0631 \u0627\u0644\u062a\u0637\u0628\u064a\u0642".toString());
                                                        } catch (Throwable th3) {
                                                            th = th3;
                                                        }
                                                    } else {
                                                        try {
                                                            String[] strArr2 = {String.valueOf(cursor.getLong(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7)};
                                                            try {
                                                                CloseableKt.closeFinally(cursorRawQuery2, null);
                                                                String str2 = strArr2[0];
                                                                Intrinsics.checkNotNullExpressionValue(str2, "get(...)");
                                                                if (Long.parseLong(str2) != j || !Intrinsics.areEqual(strArr2[1], string5) || !Intrinsics.areEqual(strArr2[2], orCreate)) {
                                                                    throw new IllegalArgumentException("\u0627\u0644\u0625\u0642\u0631\u0627\u0631 \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u062a\u0633\u0644\u0633\u0644 \u0627\u0644\u0623\u0645\u0631 \u0623\u0648 \u0628\u0635\u0645\u0629 \u0628\u064a\u0627\u0646\u0627\u062a\u0647 \u0627\u0644\u0645\u062d\u0644\u064a\u0629".toString());
                                                                }
                                                                if (Intrinsics.areEqual(strState, "PRIMARY") && !SetsKt.setOf((Object[]) new String[]{"CATEGORY", "UNIT", "PRODUCT", "PRODUCT_OPENING_STOCK", "CUSTOMER", "CASH_ACCOUNT", "EXCHANGE_RATE", "STORE_SETTINGS", "SERVICE_TRANSFER_RECEIVABLE"}).contains(strArr2[4])) {
                                                                    throw new IllegalArgumentException("\u0644\u0627 \u064a\u062c\u0648\u0632 \u0642\u0628\u0648\u0644 \u0625\u0642\u0631\u0627\u0631 \u0645\u0646 \u062c\u0647\u0627\u0632 \u062b\u0627\u0646\u0648\u064a \u0644\u0639\u0645\u0644\u064a\u0629 \u0625\u062f\u0627\u0631\u064a\u0629 \u0623\u0648 \u0645\u0627\u0644\u064a\u0629 \u063a\u064a\u0631 \u0645\u0648\u0632\u0639\u0629 \u0639\u0644\u064a\u0647".toString());
                                                                }
                                                                if (!SetsKt.setOf((Object[]) new String[]{"PENDING", "FAILED", "IN_PROGRESS", "SYNCED"}).contains(strArr2[3])) {
                                                                    throw new IllegalArgumentException("\u062d\u0627\u0644\u0629 \u0627\u0644\u0623\u0645\u0631 \u0627\u0644\u0645\u062d\u0644\u064a \u0644\u0627 \u062a\u0633\u0645\u062d \u0628\u0642\u0628\u0648\u0644 \u0627\u0644\u0625\u0642\u0631\u0627\u0631".toString());
                                                                }
                                                                Cursor cursorRawQuery3 = r15.rawQuery("SELECT sequence_number,status FROM sync_acknowledgements WHERE operation_id=? AND device_id=?", new String[]{string4, string2});
                                                                try {
                                                                    Cursor cursor2 = cursorRawQuery3;
                                                                    if (cursor2.moveToFirst()) {
                                                                        strArr = new String[]{String.valueOf(cursor2.getLong(0)), cursor2.getString(1)};
                                                                        th2 = null;
                                                                    } else {
                                                                        th2 = null;
                                                                        strArr = null;
                                                                    }
                                                                    CloseableKt.closeFinally(cursorRawQuery3, th2);
                                                                    if (strArr != null) {
                                                                        String str3 = strArr[0];
                                                                        Intrinsics.checkNotNullExpressionValue(str3, "get(...)");
                                                                        if (Long.parseLong(str3) != j || !Intrinsics.areEqual(strArr[1], "APPLIED")) {
                                                                            throw new IllegalArgumentException("\u062a\u0639\u0627\u0631\u0636 \u0645\u0639 \u0625\u0642\u0631\u0627\u0631 \u0645\u062d\u0641\u0648\u0638 \u0633\u0627\u0628\u0642\u0627\u064b".toString());
                                                                        }
                                                                        Unit unit = Unit.INSTANCE;
                                                                    } else {
                                                                        ?? contentValues = new ContentValues();
                                                                        contentValues.put("operation_id", string4);
                                                                        contentValues.put("device_id", string2);
                                                                        contentValues.put(r16, Long.valueOf(j));
                                                                        contentValues.put(NotificationCompat.CATEGORY_STATUS, "APPLIED");
                                                                        contentValues.put("acknowledged_at", jSONObject2.getString("acknowledged_at"));
                                                                        Unit unit2 = Unit.INSTANCE;
                                                                        Long.valueOf(r15.insertOrThrow("sync_acknowledgements", null, contentValues));
                                                                    }
                                                                    if (Intrinsics.areEqual(strState, "SECONDARY")) {
                                                                        if (Intrinsics.areEqual(strArr2[3], "SYNCED")) {
                                                                            i = 0;
                                                                            syncProtocolRepository = this;
                                                                        } else {
                                                                            ContentValues contentValues2 = new ContentValues();
                                                                            contentValues2.put(NotificationCompat.CATEGORY_STATUS, "SYNCED");
                                                                            contentValues2.putNull("last_error");
                                                                            i = 0;
                                                                            syncProtocolRepository = this;
                                                                            contentValues2.put("updated_at", timestamp$default(syncProtocolRepository, 0L, 1, null));
                                                                            Unit unit3 = Unit.INSTANCE;
                                                                            if (r15.update("sync_queue", contentValues2, "operation_id=? AND status IN ('PENDING','FAILED','IN_PROGRESS')", new String[]{string4}) != 1) {
                                                                                throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062b\u0628\u064a\u062a \u0625\u0642\u0631\u0627\u0631 \u0627\u0644\u062a\u0637\u0628\u064a\u0642 \u0641\u064a \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0625\u0631\u0633\u0627\u0644".toString());
                                                                            }
                                                                        }
                                                                        if (Intrinsics.areEqual(strArr2[4], "SALES_INVOICE")) {
                                                                            JSONObject jSONObject3 = new JSONObject(strArr2[7]);
                                                                            if (Intrinsics.areEqual(jSONObject3.optString("business_operation"), "POST_SALE_INVOICE") && Intrinsics.areEqual(jSONObject3.optString("sale_type"), "CREDIT")) {
                                                                                if (!Intrinsics.areEqual(jSONObject3.optString("id"), strArr2[6])) {
                                                                                    throw new IllegalArgumentException("\u0625\u0642\u0631\u0627\u0631 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u0623\u0645\u0631 \u0628\u064a\u0639 \u0622\u062c\u0644 \u0645\u062d\u0644\u064a\u0627\u064b".toString());
                                                                                }
                                                                                String str4 = strArr2[6];
                                                                                String strOptString = jSONObject3.optString("party_id");
                                                                                Intrinsics.checkNotNull(strOptString);
                                                                                if (StringsKt.isBlank(strOptString) || Intrinsics.areEqual(strOptString, "null")) {
                                                                                    strOptString = null;
                                                                                }
                                                                                if (strOptString == null) {
                                                                                    throw new IllegalArgumentException("\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u062f\u064a\u0646 \u0627\u0644\u0645\u0624\u0643\u062f\u0629 \u0644\u0627 \u062a\u062d\u062f\u062f \u0632\u0628\u0648\u0646\u0627\u064b");
                                                                                }
                                                                                Cursor cursorRawQuery4 = r15.rawQuery("SELECT status,customer_id,total_usd_cents,exchange_rate_syp_per_usd_milli\n                               FROM sales_invoices WHERE id=?", new String[]{str4});
                                                                                try {
                                                                                    Cursor cursor3 = cursorRawQuery4;
                                                                                    if (!cursor3.moveToFirst()) {
                                                                                        throw new IllegalArgumentException("\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u062f\u064a\u0646 \u0627\u0644\u0645\u062d\u0644\u064a\u0629 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629 \u0639\u0646\u062f \u0627\u0633\u062a\u0644\u0627\u0645 \u0627\u0644\u0625\u0642\u0631\u0627\u0631".toString());
                                                                                    }
                                                                                    String[] strArr3 = {cursor3.getString(i), cursor3.getString(1), String.valueOf(cursor3.getLong(2)), String.valueOf(cursor3.getLong(3))};
                                                                                    CloseableKt.closeFinally(cursorRawQuery4, null);
                                                                                    if (Intrinsics.areEqual(strArr3[1], strOptString)) {
                                                                                        String str5 = strArr3[2];
                                                                                        Intrinsics.checkNotNullExpressionValue(str5, "get(...)");
                                                                                        if (Long.parseLong(str5) == jSONObject3.getLong("total_usd_cents")) {
                                                                                            String str6 = strArr3[3];
                                                                                            Intrinsics.checkNotNullExpressionValue(str6, "get(...)");
                                                                                            if (Long.parseLong(str6) == jSONObject3.getLong("exchange_rate_syp_per_usd_milli") && SetsKt.setOf((Object[]) new String[]{"DRAFT", "POSTED", "PARTIALLY_PAID", "PAID"}).contains(strArr3[i])) {
                                                                                                if (Intrinsics.areEqual(strArr3[i], "DRAFT")) {
                                                                                                    ContentValues contentValues3 = new ContentValues();
                                                                                                    contentValues3.put(NotificationCompat.CATEGORY_STATUS, "POSTED");
                                                                                                    contentValues3.put("posted_at", jSONObject3.getString("posted_at"));
                                                                                                    contentValues3.put("posted_operating_date", jSONObject3.getString("posted_operating_date"));
                                                                                                    contentValues3.put("updated_at", timestamp$default(syncProtocolRepository, 0L, 1, null));
                                                                                                    Unit unit4 = Unit.INSTANCE;
                                                                                                    if (r15.update("sales_invoices", contentValues3, "id=? AND status='DRAFT'", new String[]{str4}) != 1) {
                                                                                                        throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062b\u0628\u064a\u062a \u0627\u0639\u062a\u0645\u0627\u062f \u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u062f\u064a\u0646 \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a".toString());
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                    throw new IllegalArgumentException("\u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0645\u062d\u0644\u064a\u0629 \u0644\u0627 \u062a\u0637\u0627\u0628\u0642 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0622\u062c\u0644\u0629 \u0627\u0644\u062a\u064a \u0627\u0639\u062a\u0645\u062f\u0647\u0627 \u0627\u0644\u0623\u0633\u0627\u0633\u064a".toString());
                                                                                } catch (Throwable th4) {
                                                                                    try {
                                                                                        throw th4;
                                                                                    } catch (Throwable th5) {
                                                                                        CloseableKt.closeFinally(cursorRawQuery4, th4);
                                                                                        throw th5;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    } else {
                                                                        Cursor cursorRawQuery5 = r15.rawQuery("SELECT COUNT(*) FROM sync_devices d\n                           WHERE d.device_role='SECONDARY' AND d.status='ACTIVE'\n                             AND NOT EXISTS (\n                               SELECT 1 FROM sync_acknowledgements a\n                               WHERE a.operation_id=? AND a.device_id=d.device_id AND a.status='APPLIED'\n                             )", new String[]{string4});
                                                                        try {
                                                                            Cursor cursor4 = cursorRawQuery5;
                                                                            cursor4.moveToFirst();
                                                                            int i2 = cursor4.getInt(0);
                                                                            CloseableKt.closeFinally(cursorRawQuery5, null);
                                                                            if (i2 == 0 && !Intrinsics.areEqual(strArr2[3], r12)) {
                                                                                ContentValues contentValues4 = new ContentValues();
                                                                                contentValues4.put(NotificationCompat.CATEGORY_STATUS, r12);
                                                                                contentValues4.putNull("last_error");
                                                                                contentValues4.put("updated_at", timestamp$default(this, 0L, 1, null));
                                                                                Unit unit5 = Unit.INSTANCE;
                                                                                if (r15.update("sync_queue", contentValues4, "operation_id=? AND status IN ('PENDING','FAILED','IN_PROGRESS')", new String[]{string4}) != 1) {
                                                                                    throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062b\u0628\u064a\u062a \u0627\u0643\u062a\u0645\u0627\u0644 \u062a\u0648\u0632\u064a\u0639 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0639\u0644\u0649 \u0627\u0644\u0623\u062c\u0647\u0632\u0629".toString());
                                                                                }
                                                                            }
                                                                        } catch (Throwable th6) {
                                                                            try {
                                                                                throw th6;
                                                                            } catch (Throwable th7) {
                                                                                CloseableKt.closeFinally(cursorRawQuery5, th6);
                                                                                throw th7;
                                                                            }
                                                                        }
                                                                    }
                                                                    r15.setTransactionSuccessful();
                                                                    if (strArr == null) {
                                                                        syncApplicationAckDisposition = SyncApplicationAckDisposition.APPLIED;
                                                                    } else {
                                                                        syncApplicationAckDisposition = SyncApplicationAckDisposition.DUPLICATE;
                                                                    }
                                                                    r15.endTransaction();
                                                                    ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
                                                                    return syncApplicationAckDisposition;
                                                                } catch (Throwable th8) {
                                                                    try {
                                                                        throw th8;
                                                                    } catch (Throwable th9) {
                                                                        CloseableKt.closeFinally(cursorRawQuery3, th8);
                                                                        throw th9;
                                                                    }
                                                                }
                                                            } catch (Throwable th10) {
                                                                th = th10;
                                                                r15.endTransaction();
                                                                throw th;
                                                            }
                                                        } catch (Throwable th11) {
                                                            th = th11;
                                                            th = th;
                                                            throw th;
                                                        }
                                                    }
                                                } catch (Throwable th12) {
                                                    th = th12;
                                                }
                                                th = th;
                                                try {
                                                    throw th;
                                                } catch (Throwable th13) {
                                                    CloseableKt.closeFinally(cursorRawQuery2, th);
                                                    throw th13;
                                                }
                                            } catch (Throwable th14) {
                                                th = th14;
                                                r15.endTransaction();
                                                throw th;
                                            }
                                        } catch (Throwable th15) {
                                            th = th15;
                                        }
                                    } catch (Throwable th16) {
                                        th = th16;
                                        r15 = writableDatabase;
                                    }
                                }
                            } catch (Throwable th17) {
                                th = th17;
                                r16 = bArr;
                            }
                        }
                    }
                    throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u0625\u0642\u0631\u0627\u0631 \u0627\u0644\u062a\u0637\u0628\u064a\u0642 \u063a\u064a\u0631 \u0645\u0643\u062a\u0645\u0644\u0629".toString());
                } catch (Throwable th18) {
                    th = th18;
                }
            } catch (Throwable th19) {
                th = th19;
                r16 = bArrLoad;
            }
            ArraysKt.fill$default((byte[]) r16, (byte) 0, 0, 0, 6, (Object) null);
            throw th;
        } catch (Throwable th20) {
            try {
                throw th20;
            } catch (Throwable th21) {
                CloseableKt.closeFinally(cursorRawQuery, th20);
                throw th21;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String localDeviceRole() {
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        return state(readableDatabase, "local_device_role");
    }

    public final String localDeviceId() {
        return SyncDeviceIdentity.INSTANCE.getOrCreate(this.context);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String authorityDeviceId() {
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        return state(readableDatabase, "authority_device_id");
    }

    public final List<String> activeSecondaryDeviceIds() throws IOException {
        if (!Intrinsics.areEqual(localDeviceRole(), "PRIMARY") || !Intrinsics.areEqual(authorityDeviceId(), localDeviceId())) {
            throw new IllegalArgumentException("\u0642\u0627\u0626\u0645\u0629 \u0627\u0644\u0623\u062c\u0647\u0632\u0629 \u0627\u0644\u062b\u0627\u0646\u0648\u064a\u0629 \u0645\u062a\u0627\u062d\u0629 \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0641\u0642\u0637".toString());
        }
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery("SELECT device_id FROM sync_devices WHERE device_role='SECONDARY' AND status='ACTIVE' ORDER BY created_at", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            while (cursor.moveToNext()) {
                String string = cursor.getString(0);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                arrayList.add(string);
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            return arrayList;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    public static /* synthetic */ List pendingOutboxForDevice$default(SyncProtocolRepository syncProtocolRepository, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 100;
        }
        return syncProtocolRepository.pendingOutboxForDevice(str, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final List<SyncOutboxItem> pendingOutboxForDevice(String deviceId, int limit) throws IOException {
        Object objM381constructorimpl;
        String str;
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        int i = 1;
        if (1 <= limit && limit < 501) {
            try {
                Result.Companion companion = Result.INSTANCE;
                SyncProtocolRepository syncProtocolRepository = this;
                objM381constructorimpl = Result.m381constructorimpl(UUID.fromString(deviceId));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m388isSuccessimpl(objM381constructorimpl)) {
                SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
                Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
                String strState = state(readableDatabase, "local_device_role");
                String orCreate = SyncDeviceIdentity.INSTANCE.getOrCreate(this.context);
                if (Intrinsics.areEqual(strState, "PRIMARY") && Intrinsics.areEqual(state(readableDatabase, "authority_device_id"), orCreate)) {
                    Cursor cursorRawQuery = readableDatabase.rawQuery("SELECT 1 FROM sync_devices WHERE device_id=? AND device_role='SECONDARY' AND status='ACTIVE'", new String[]{deviceId});
                    try {
                        boolean zMoveToFirst = cursorRawQuery.moveToFirst();
                        CloseableKt.closeFinally(cursorRawQuery, null);
                        if (!zMoveToFirst) {
                            throw new IllegalArgumentException("\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0633\u062a\u0644\u0645 \u063a\u064a\u0631 \u0645\u0633\u062c\u0644 \u0623\u0648 \u0645\u0648\u0642\u0648\u0641".toString());
                        }
                        str = "SELECT e.operation_id,e.sequence_number,e.operation_type,e.entity_type,e.entity_id,\n                      e.payload_json,e.payload_sha256,e.created_at,q.retry_count\n               FROM sync_operation_envelopes e JOIN sync_queue q ON q.operation_id=e.operation_id\n               WHERE e.origin_device_id=?\n                 AND e.entity_type IN ('CATEGORY','UNIT','PRODUCT','PRODUCT_OPENING_STOCK','CUSTOMER','CASH_ACCOUNT','EXCHANGE_RATE','STORE_SETTINGS','SERVICE_TRANSFER_RECEIVABLE')\n                 AND NOT EXISTS (SELECT 1 FROM sync_acknowledgements a\n                   WHERE a.operation_id=e.operation_id AND a.device_id=? AND a.status='APPLIED')\n               ORDER BY e.sequence_number LIMIT ?";
                    } catch (Throwable th2) {
                        try {
                            throw th2;
                        } catch (Throwable th3) {
                            CloseableKt.closeFinally(cursorRawQuery, th2);
                            throw th3;
                        }
                    }
                } else {
                    if (!Intrinsics.areEqual(strState, "SECONDARY") || !Intrinsics.areEqual(deviceId, state(readableDatabase, "authority_device_id"))) {
                        throw new IllegalArgumentException("\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u064a\u0631\u0633\u0644 \u0625\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0627\u0644\u0645\u0639\u062a\u0645\u062f \u0641\u0642\u0637".toString());
                    }
                    str = "SELECT e.operation_id,e.sequence_number,e.operation_type,e.entity_type,e.entity_id,\n                      e.payload_json,e.payload_sha256,e.created_at,q.retry_count\n               FROM sync_operation_envelopes e JOIN sync_queue q ON q.operation_id=e.operation_id\n               WHERE e.origin_device_id=? AND q.status IN ('PENDING','FAILED')\n               ORDER BY e.sequence_number LIMIT ?";
                }
                int i2 = 3;
                int i3 = 0;
                int i4 = 2;
                String[] strArr = Intrinsics.areEqual(strState, "PRIMARY") ? new String[]{orCreate, deviceId, String.valueOf(limit)} : new String[]{orCreate, String.valueOf(limit)};
                ArrayList arrayList = new ArrayList();
                Cursor cursorRawQuery2 = readableDatabase.rawQuery(str, strArr);
                try {
                    Cursor cursor = cursorRawQuery2;
                    while (cursor.moveToNext()) {
                        String string = cursor.getString(i3);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                        long j = cursor.getLong(i);
                        String string2 = cursor.getString(i4);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                        String string3 = cursor.getString(i2);
                        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                        String string4 = cursor.getString(4);
                        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                        String string5 = cursor.getString(5);
                        Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                        String string6 = cursor.getString(6);
                        Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                        String string7 = cursor.getString(7);
                        Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
                        arrayList.add(new SyncOutboxItem(string, j, string2, string3, string4, string5, string6, string7, cursor.getInt(8)));
                        i = 1;
                        i2 = 3;
                        i3 = 0;
                        i4 = 2;
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    return arrayList;
                } catch (Throwable th4) {
                    try {
                        throw th4;
                    } catch (Throwable th5) {
                        CloseableKt.closeFinally(cursorRawQuery2, th4);
                        throw th5;
                    }
                }
            }
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    public final String incomingOperationStatus(String operationId) throws IOException {
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery("SELECT status FROM sync_inbox WHERE operation_id=?", new String[]{operationId});
        try {
            Cursor cursor = cursorRawQuery;
            String string = cursor.moveToFirst() ? cursor.getString(0) : null;
            CloseableKt.closeFinally(cursorRawQuery, null);
            return string;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final SyncInboxDisposition recordIncomingFromPrimary(String encodedEnvelope) {
        Intrinsics.checkNotNullParameter(encodedEnvelope, "encodedEnvelope");
        AuthenticatedSyncEnvelope authenticatedSyncEnvelopeVerifyIncomingFromPrimary = verifyIncomingFromPrimary(encodedEnvelope);
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        writableDatabase.beginTransaction();
        try {
            String strState = state(writableDatabase, "authority_device_id");
            if (strState == null) {
                throw new IllegalArgumentException("\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u063a\u064a\u0631 \u0645\u062d\u062f\u062f");
            }
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT 1 FROM sync_devices WHERE device_id=? AND device_role='PRIMARY' AND status='ACTIVE'", new String[]{strState});
            try {
                boolean zMoveToFirst = cursorRawQuery.moveToFirst();
                CloseableKt.closeFinally(cursorRawQuery, null);
                if (!zMoveToFirst || !Intrinsics.areEqual(authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getOriginDeviceId(), strState)) {
                    throw new IllegalArgumentException("\u0645\u0635\u062f\u0631 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0644\u064a\u0633 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0627\u0644\u0646\u0634\u0637".toString());
                }
                Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT payload_sha256,origin_device_id,sequence_number,operation_type,\n                          entity_type,entity_id,origin_created_at,payload_json\n                   FROM sync_inbox WHERE operation_id=?", new String[]{authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getOperationId()});
                try {
                    Cursor cursor = cursorRawQuery2;
                    String[] strArr = !cursor.moveToFirst() ? null : new String[]{cursor.getString(0), cursor.getString(1), String.valueOf(cursor.getLong(2)), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7)};
                    CloseableKt.closeFinally(cursorRawQuery2, null);
                    if (strArr != null) {
                        if (Intrinsics.areEqual(strArr[0], authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getPayloadSha256()) && Intrinsics.areEqual(strArr[1], authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getOriginDeviceId())) {
                            String str = strArr[2];
                            Intrinsics.checkNotNullExpressionValue(str, "get(...)");
                            if (Long.parseLong(str) == authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getSequenceNumber() && Intrinsics.areEqual(strArr[3], authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getOperationType()) && Intrinsics.areEqual(strArr[4], authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getEntityType()) && Intrinsics.areEqual(strArr[5], authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getEntityId()) && Intrinsics.areEqual(strArr[6], authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getCreatedAt())) {
                                writableDatabase.setTransactionSuccessful();
                                SyncInboxDisposition syncInboxDisposition = SyncInboxDisposition.DUPLICATE;
                                writableDatabase.endTransaction();
                                return syncInboxDisposition;
                            }
                        }
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("id", UUID.randomUUID().toString());
                        contentValues.put("operation_id", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getOperationId());
                        contentValues.put("entity_type", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getEntityType());
                        contentValues.put("entity_id", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getEntityId());
                        contentValues.put("local_payload_json", strArr[7]);
                        contentValues.put("remote_payload_json", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getPayloadJson());
                        contentValues.put("created_at", timestamp$default(this, 0L, 1, null));
                        Unit unit = Unit.INSTANCE;
                        writableDatabase.insertOrThrow("sync_conflicts", null, contentValues);
                        writableDatabase.setTransactionSuccessful();
                        SyncInboxDisposition syncInboxDisposition2 = SyncInboxDisposition.CONFLICT;
                        writableDatabase.endTransaction();
                        return syncInboxDisposition2;
                    }
                    Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT last_sequence FROM sync_cursors WHERE device_id=?", new String[]{strState});
                    try {
                        Cursor cursor2 = cursorRawQuery3;
                        long j = cursor2.moveToFirst() ? cursor2.getLong(0) : 0L;
                        CloseableKt.closeFinally(cursorRawQuery3, null);
                        if (authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getSequenceNumber() != j + 1) {
                            throw new IllegalArgumentException((authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getSequenceNumber() <= j ? "\u062a\u0645 \u0631\u0641\u0636 \u0625\u0639\u0627\u062f\u0629 \u062a\u0634\u063a\u064a\u0644 \u062a\u0633\u0644\u0633\u0644 \u0642\u062f\u064a\u0645" : "\u0641\u062c\u0648\u0629 \u0641\u064a \u062a\u0633\u0644\u0633\u0644 \u0623\u0648\u0627\u0645\u0631 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a\u061b \u0627\u0646\u062a\u0638\u0631 \u0627\u0644\u0623\u0645\u0631 \u0627\u0644\u0633\u0627\u0628\u0642").toString());
                        }
                        String strTimestamp$default = timestamp$default(this, 0L, 1, null);
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("operation_id", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getOperationId());
                        contentValues2.put("origin_device_id", strState);
                        contentValues2.put("sequence_number", Long.valueOf(authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getSequenceNumber()));
                        contentValues2.put("operation_type", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getOperationType());
                        contentValues2.put("entity_type", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getEntityType());
                        contentValues2.put("entity_id", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getEntityId());
                        contentValues2.put("payload_json", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getPayloadJson());
                        contentValues2.put("payload_sha256", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getPayloadSha256());
                        contentValues2.put(NotificationCompat.CATEGORY_STATUS, "RECEIVED");
                        contentValues2.put("received_at", strTimestamp$default);
                        contentValues2.put("origin_created_at", authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getCreatedAt());
                        Unit unit2 = Unit.INSTANCE;
                        writableDatabase.insertOrThrow("sync_inbox", null, contentValues2);
                        ContentValues contentValues3 = new ContentValues();
                        contentValues3.put("last_sequence", Long.valueOf(authenticatedSyncEnvelopeVerifyIncomingFromPrimary.getSequenceNumber()));
                        contentValues3.put("updated_at", strTimestamp$default);
                        Unit unit3 = Unit.INSTANCE;
                        writableDatabase.update("sync_cursors", contentValues3, "device_id=?", new String[]{strState});
                        writableDatabase.setTransactionSuccessful();
                        SyncInboxDisposition syncInboxDisposition3 = SyncInboxDisposition.RECEIVED;
                        writableDatabase.endTransaction();
                        return syncInboxDisposition3;
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            CloseableKt.closeFinally(cursorRawQuery3, th);
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    try {
                        throw th3;
                    } catch (Throwable th4) {
                        CloseableKt.closeFinally(cursorRawQuery2, th3);
                        throw th4;
                    }
                }
            } catch (Throwable th5) {
                try {
                    throw th5;
                } catch (Throwable th6) {
                    CloseableKt.closeFinally(cursorRawQuery, th5);
                    throw th6;
                }
            }
        } catch (Throwable th7) {
            writableDatabase.endTransaction();
            throw th7;
        }
    }

    public static /* synthetic */ List appliedOperationsAwaitingAckForDevice$default(SyncProtocolRepository syncProtocolRepository, String str, int i, long j, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 100;
        }
        if ((i2 & 4) != 0) {
            j = 60000;
        }
        return syncProtocolRepository.appliedOperationsAwaitingAckForDevice(str, i, j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final List<String> appliedOperationsAwaitingAckForDevice(String deviceId, int limit, long retryAfterMillis) throws IOException {
        Object objM381constructorimpl;
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        if (1 <= limit && limit < 501 && 0 <= retryAfterMillis && retryAfterMillis < 86400001) {
            try {
                Result.Companion companion = Result.INSTANCE;
                SyncProtocolRepository syncProtocolRepository = this;
                objM381constructorimpl = Result.m381constructorimpl(UUID.fromString(deviceId));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m388isSuccessimpl(objM381constructorimpl)) {
                SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
                Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
                String strState = state(readableDatabase, "local_device_role");
                String orCreate = SyncDeviceIdentity.INSTANCE.getOrCreate(this.context);
                String str = "PRIMARY";
                if (Intrinsics.areEqual(strState, "PRIMARY")) {
                    if (!Intrinsics.areEqual(state(readableDatabase, "authority_device_id"), orCreate)) {
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                    str = "SECONDARY";
                } else if (Intrinsics.areEqual(strState, "SECONDARY")) {
                    if (!Intrinsics.areEqual(state(readableDatabase, "authority_device_id"), deviceId)) {
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                } else {
                    throw new IllegalArgumentException("\u062f\u0648\u0631 \u0627\u0644\u062c\u0647\u0627\u0632 \u063a\u064a\u0631 \u0645\u0647\u064a\u0623");
                }
                Cursor cursorRawQuery = readableDatabase.rawQuery("SELECT 1 FROM sync_devices WHERE device_id=? AND device_role=? AND status='ACTIVE'", new String[]{deviceId, str});
                try {
                    boolean zMoveToFirst = cursorRawQuery.moveToFirst();
                    CloseableKt.closeFinally(cursorRawQuery, null);
                    if (!zMoveToFirst) {
                        throw new IllegalArgumentException("\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0642\u0627\u0628\u0644 \u063a\u064a\u0631 \u0645\u0633\u062c\u0644 \u0623\u0648 \u0645\u0648\u0642\u0648\u0641".toString());
                    }
                    String strTimestamp = timestamp(System.currentTimeMillis() - retryAfterMillis);
                    ArrayList arrayList = new ArrayList();
                    Cursor cursorRawQuery2 = readableDatabase.rawQuery("SELECT i.operation_id FROM sync_inbox i\n               JOIN sync_devices d ON d.device_id=i.origin_device_id\n               LEFT JOIN sync_ack_delivery a\n                 ON a.operation_id=i.operation_id AND a.device_id=i.origin_device_id\n               WHERE i.origin_device_id=? AND i.status='APPLIED'\n                 AND d.device_role=? AND d.status='ACTIVE'\n                 AND (a.last_forwarded_at IS NULL OR a.last_forwarded_at<=?)\n               ORDER BY CASE WHEN a.last_forwarded_at IS NULL THEN 0 ELSE 1 END,\n                        a.last_forwarded_at,i.sequence_number LIMIT ?", new String[]{deviceId, str, strTimestamp, String.valueOf(limit)});
                    try {
                        Cursor cursor = cursorRawQuery2;
                        while (cursor.moveToNext()) {
                            String string = cursor.getString(0);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                            arrayList.add(string);
                        }
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(cursorRawQuery2, null);
                        return arrayList;
                    } catch (Throwable th2) {
                        try {
                            throw th2;
                        } catch (Throwable th3) {
                            CloseableKt.closeFinally(cursorRawQuery2, th2);
                            throw th3;
                        }
                    }
                } catch (Throwable th4) {
                    try {
                        throw th4;
                    } catch (Throwable th5) {
                        CloseableKt.closeFinally(cursorRawQuery, th4);
                        throw th5;
                    }
                }
            }
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean markApplicationAckForwarded(String operationId) {
        String str = "PRIMARY";
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        if (StringsKt.isBlank(operationId)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        SQLiteDatabase writableDatabase = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
        writableDatabase.beginTransaction();
        try {
            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT origin_device_id,status FROM sync_inbox WHERE operation_id=?", new String[]{operationId});
            try {
                Cursor cursor = cursorRawQuery;
                String string = (cursor.moveToFirst() && Intrinsics.areEqual(cursor.getString(1), "APPLIED")) ? cursor.getString(0) : null;
                CloseableKt.closeFinally(cursorRawQuery, null);
                if (string != null) {
                    String strState = state(writableDatabase, "local_device_role");
                    String orCreate = SyncDeviceIdentity.INSTANCE.getOrCreate(this.context);
                    if (Intrinsics.areEqual(strState, "PRIMARY")) {
                        if (!Intrinsics.areEqual(state(writableDatabase, "authority_device_id"), orCreate)) {
                            throw new IllegalArgumentException("Failed requirement.".toString());
                        }
                        str = "SECONDARY";
                    } else if (Intrinsics.areEqual(strState, "SECONDARY")) {
                        if (!Intrinsics.areEqual(state(writableDatabase, "authority_device_id"), string)) {
                            throw new IllegalArgumentException("Failed requirement.".toString());
                        }
                    } else {
                        throw new IllegalArgumentException("\u062f\u0648\u0631 \u0627\u0644\u062c\u0647\u0627\u0632 \u063a\u064a\u0631 \u0645\u0647\u064a\u0623");
                    }
                    Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT 1 FROM sync_devices WHERE device_id=? AND device_role=? AND status='ACTIVE'", new String[]{string, str});
                    try {
                        boolean zMoveToFirst = cursorRawQuery2.moveToFirst();
                        CloseableKt.closeFinally(cursorRawQuery2, null);
                        if (!zMoveToFirst) {
                            throw new IllegalArgumentException("\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0642\u0627\u0628\u0644 \u063a\u064a\u0631 \u0645\u0633\u062c\u0644 \u0623\u0648 \u0645\u0648\u0642\u0648\u0641".toString());
                        }
                        String strTimestamp$default = timestamp$default(this, 0L, 1, null);
                        Cursor cursorRawQuery3 = writableDatabase.rawQuery("SELECT attempt_count FROM sync_ack_delivery WHERE operation_id=? AND device_id=?", new String[]{operationId, string});
                        try {
                            Cursor cursor2 = cursorRawQuery3;
                            Integer numValueOf = cursor2.moveToFirst() ? Integer.valueOf(cursor2.getInt(0)) : null;
                            CloseableKt.closeFinally(cursorRawQuery3, null);
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("last_forwarded_at", strTimestamp$default);
                            contentValues.put("attempt_count", Integer.valueOf((numValueOf != null ? numValueOf.intValue() : 0) + 1));
                            contentValues.put("updated_at", strTimestamp$default);
                            if (numValueOf == null) {
                                contentValues.put("operation_id", operationId);
                                contentValues.put("device_id", string);
                                writableDatabase.insertOrThrow("sync_ack_delivery", null, contentValues);
                            } else if (writableDatabase.update("sync_ack_delivery", contentValues, "operation_id=? AND device_id=?", new String[]{operationId, string}) != 1) {
                                throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062d\u0641\u0638 \u062d\u0627\u0644\u0629 \u062a\u0645\u0631\u064a\u0631 \u0625\u0642\u0631\u0627\u0631 \u0627\u0644\u062a\u0637\u0628\u064a\u0642".toString());
                            }
                            writableDatabase.setTransactionSuccessful();
                            writableDatabase.endTransaction();
                            return true;
                        } catch (Throwable th) {
                            try {
                                throw th;
                            } catch (Throwable th2) {
                                CloseableKt.closeFinally(cursorRawQuery3, th);
                                throw th2;
                            }
                        }
                    } catch (Throwable th3) {
                        try {
                            throw th3;
                        } catch (Throwable th4) {
                            CloseableKt.closeFinally(cursorRawQuery2, th3);
                            throw th4;
                        }
                    }
                }
                writableDatabase.endTransaction();
                return false;
            } catch (Throwable th5) {
                try {
                    throw th5;
                } catch (Throwable th6) {
                    CloseableKt.closeFinally(cursorRawQuery, th5);
                    throw th6;
                }
            }
        } catch (Throwable th7) {
            writableDatabase.endTransaction();
            throw th7;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String storeId() throws IOException {
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        String strState = state(readableDatabase, "store_id");
        if (strState != null) {
            return strState;
        }
        throw new IllegalStateException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0645\u062a\u062c\u0631 \u063a\u064a\u0631 \u0645\u0647\u064a\u0623");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String storeTimeZoneId() {
        StoreTime storeTime = StoreTime.INSTANCE;
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        return storeTime.zoneId(readableDatabase);
    }

    public static /* synthetic */ List pendingOutbox$default(SyncProtocolRepository syncProtocolRepository, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 100;
        }
        return syncProtocolRepository.pendingOutbox(i);
    }

    public final List<SyncOutboxItem> pendingOutbox(int limit) throws IOException {
        int i = 1;
        if (1 > limit || limit >= 501) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery("SELECT e.operation_id,e.sequence_number,e.operation_type,e.entity_type,e.entity_id,\n                      e.payload_json,e.payload_sha256,e.created_at,q.retry_count\n               FROM sync_operation_envelopes e JOIN sync_queue q ON q.operation_id=e.operation_id\n               WHERE q.status IN ('PENDING','FAILED') ORDER BY e.sequence_number LIMIT ?", new String[]{String.valueOf(limit)});
        try {
            Cursor cursor = cursorRawQuery;
            while (cursor.moveToNext()) {
                String string = cursor.getString(0);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                long j = cursor.getLong(i);
                String string2 = cursor.getString(2);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                String string3 = cursor.getString(3);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                String string4 = cursor.getString(4);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                String string5 = cursor.getString(5);
                Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                String string6 = cursor.getString(6);
                Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                String string7 = cursor.getString(7);
                Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
                arrayList.add(new SyncOutboxItem(string, j, string2, string3, string4, string5, string6, string7, cursor.getInt(8)));
                i = 1;
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            return arrayList;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean hasConfiguredPrimary() {
        SQLiteDatabase readableDatabase = this.database.getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        if (Intrinsics.areEqual(state(readableDatabase, "local_device_role"), "PRIMARY")) {
            SQLiteDatabase readableDatabase2 = this.database.getReadableDatabase();
            Intrinsics.checkNotNullExpressionValue(readableDatabase2, "<get-readableDatabase>(...)");
            if (Intrinsics.areEqual(state(readableDatabase2, "authority_device_id"), SyncDeviceIdentity.INSTANCE.getOrCreate(this.context))) {
                return true;
            }
        }
        return false;
    }

    private final void requirePrimaryManager(SQLiteDatabase db, String actorUserId) {
        PermissionPolicy.INSTANCE.require(db, actorUserId, PermissionPolicy.SYNC_MANAGE);
        if (!Intrinsics.areEqual(state(db, "authority_device_id"), SyncDeviceIdentity.INSTANCE.getOrCreate(this.context))) {
            throw new IllegalStateException("\u064a\u062c\u0628 \u0625\u0639\u062f\u0627\u062f \u0647\u0630\u0627 \u0627\u0644\u0647\u0627\u062a\u0641 \u0643\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0623\u0648\u0644\u0627\u064b".toString());
        }
    }

    private final String state(SQLiteDatabase db, String key) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key=?", new String[]{key});
        try {
            Cursor cursor = cursorRawQuery;
            String string = cursor.moveToFirst() ? cursor.getString(0) : null;
            CloseableKt.closeFinally(cursorRawQuery, null);
            return string;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final void putState(SQLiteDatabase db, String key, String value, String now) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("state_key", key);
        contentValues.put("state_value", value);
        contentValues.put("updated_at", now);
        Unit unit = Unit.INSTANCE;
        db.insertWithOnConflict("sync_protocol_state", null, contentValues, 5);
    }

    private final void audit(SQLiteDatabase db, String actor, String action, String entityId, String before, String after, String now) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", UUID.randomUUID().toString());
        contentValues.put("user_id", actor);
        contentValues.put("action", action);
        contentValues.put("entity_type", "SYNC_DEVICE");
        contentValues.put("entity_id", entityId);
        if (before != null) {
            contentValues.put("before_json", before);
        }
        if (after != null) {
            contentValues.put("after_json", after);
        }
        contentValues.put("device_id", SyncDeviceIdentity.INSTANCE.getOrCreate(this.context));
        contentValues.put("created_at", now);
        Unit unit = Unit.INSTANCE;
        db.insertOrThrow("audit_log", null, contentValues);
    }

    private final String randomToken() {
        byte[] bArr = new byte[32];
        this.random.nextBytes(bArr);
        String strEncodeToString = Base64.encodeToString(bArr, 11);
        Intrinsics.checkNotNullExpressionValue(strEncodeToString, "let(...)");
        return strEncodeToString;
    }

    private final String sha256(String value) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = value.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrDigest = messageDigest.digest(bytes);
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
        return ArraysKt.joinToString$default(bArrDigest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.hazem.dailystore.data.SyncProtocolRepository$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SyncProtocolRepository.sha256$lambda$299(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence sha256$lambda$299(byte b) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    static /* synthetic */ String timestamp$default(SyncProtocolRepository syncProtocolRepository, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = System.currentTimeMillis();
        }
        return syncProtocolRepository.timestamp(j);
    }

    private final String timestamp(long atMillis) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String str = simpleDateFormat.format(new Date(atMillis));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }
}
