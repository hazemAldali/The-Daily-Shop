package com.hazem.dailystore.data;

import androidx.core.view.PointerIconCompat;
import java.security.PrivateKey;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.bouncycastle.jcajce.util.AnnotatedPrivateKey;
import org.json.JSONObject;

/* JADX INFO: compiled from: SyncRelayWebSocketClient.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\u0018\u0000  2\u00020\u0001:\u0003\u001e\u001f B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\u0006\u0010\u0015\u001a\u00020\u0016J\u001e\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0003J\u0006\u0010\u001b\u001a\u00020\u0016R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u001c\u001a\u00020\u00138F\u00a2\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d\u00a8\u0006!"}, d2 = {"Lcom/hazem/dailystore/data/SyncRelayWebSocketClient;", "", "relayBaseUrl", "", "storeId", "deviceId", "listener", "Lcom/hazem/dailystore/data/SyncRelayWebSocketClient$Listener;", "client", "Lokhttp3/OkHttpClient;", "authentication", "Lcom/hazem/dailystore/data/SyncRelayDeviceAuthentication;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/hazem/dailystore/data/SyncRelayWebSocketClient$Listener;Lokhttp3/OkHttpClient;Lcom/hazem/dailystore/data/SyncRelayDeviceAuthentication;)V", "endpoint", "Lokhttp3/HttpUrl;", "socket", "Lokhttp3/WebSocket;", "ready", "", "challengeAnswered", "connect", "", "sendEncryptedFrame", "operationId", "recipientDeviceId", "encryptedFrameJson", "disconnect", "isReady", "()Z", "DeviceAuthentication", "Listener", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SyncRelayWebSocketClient {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int MAX_FRAME_CHARS = 700000;
    private final SyncRelayDeviceAuthentication authentication;
    private volatile boolean challengeAnswered;
    private final OkHttpClient client;
    private final String deviceId;
    private final HttpUrl endpoint;
    private final Listener listener;
    private volatile boolean ready;
    private volatile WebSocket socket;
    private final String storeId;

    /* JADX INFO: compiled from: SyncRelayWebSocketClient.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J \u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H&J \u0010\t\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0006H&J\u0012\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H&\u00a8\u0006\u0012"}, d2 = {"Lcom/hazem/dailystore/data/SyncRelayWebSocketClient$Listener;", "", "onReady", "", "onEncryptedFrame", "fromDeviceId", "", "frameId", "encryptedFrameJson", "onDeliveryStatus", "deliveredToSocket", "", "retryable", "onRelayError", "code", "onDisconnected", "cause", "", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Listener {
        void onDeliveryStatus(String frameId, boolean deliveredToSocket, boolean retryable);

        void onDisconnected(Throwable cause);

        void onEncryptedFrame(String fromDeviceId, String frameId, String encryptedFrameJson);

        void onReady();

        void onRelayError(String code);
    }

    public SyncRelayWebSocketClient(String relayBaseUrl, String storeId, String deviceId, Listener listener, OkHttpClient client, SyncRelayDeviceAuthentication syncRelayDeviceAuthentication) {
        Intrinsics.checkNotNullParameter(relayBaseUrl, "relayBaseUrl");
        Intrinsics.checkNotNullParameter(storeId, "storeId");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(client, "client");
        this.storeId = storeId;
        this.deviceId = deviceId;
        this.listener = listener;
        this.client = client;
        this.authentication = syncRelayDeviceAuthentication;
        this.endpoint = INSTANCE.buildEndpoint(relayBaseUrl, storeId, deviceId);
    }

    public /* synthetic */ SyncRelayWebSocketClient(String str, String str2, String str3, Listener listener, OkHttpClient okHttpClient, SyncRelayDeviceAuthentication syncRelayDeviceAuthentication, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, listener, (i & 16) != 0 ? INSTANCE.defaultClient() : okHttpClient, (i & 32) != 0 ? null : syncRelayDeviceAuthentication);
    }

    /* JADX INFO: compiled from: SyncRelayWebSocketClient.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0015"}, d2 = {"Lcom/hazem/dailystore/data/SyncRelayWebSocketClient$DeviceAuthentication;", "", "serial", "", "privateKey", "Ljava/security/PrivateKey;", "<init>", "(Ljava/lang/String;Ljava/security/PrivateKey;)V", "getSerial", "()Ljava/lang/String;", "getPrivateKey", "()Ljava/security/PrivateKey;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class DeviceAuthentication {
        private final PrivateKey privateKey;
        private final String serial;

        public static /* synthetic */ DeviceAuthentication copy$default(DeviceAuthentication deviceAuthentication, String str, PrivateKey privateKey, int i, Object obj) {
            if ((i & 1) != 0) {
                str = deviceAuthentication.serial;
            }
            if ((i & 2) != 0) {
                privateKey = deviceAuthentication.privateKey;
            }
            return deviceAuthentication.copy(str, privateKey);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getSerial() {
            return this.serial;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final PrivateKey getPrivateKey() {
            return this.privateKey;
        }

        public final DeviceAuthentication copy(String serial, PrivateKey privateKey) {
            Intrinsics.checkNotNullParameter(serial, "serial");
            Intrinsics.checkNotNullParameter(privateKey, "privateKey");
            return new DeviceAuthentication(serial, privateKey);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DeviceAuthentication)) {
                return false;
            }
            DeviceAuthentication deviceAuthentication = (DeviceAuthentication) other;
            return Intrinsics.areEqual(this.serial, deviceAuthentication.serial) && Intrinsics.areEqual(this.privateKey, deviceAuthentication.privateKey);
        }

        public int hashCode() {
            return (this.serial.hashCode() * 31) + this.privateKey.hashCode();
        }

        public String toString() {
            return "DeviceAuthentication(serial=" + this.serial + ", privateKey=" + this.privateKey + ")";
        }

        public DeviceAuthentication(String serial, PrivateKey privateKey) {
            Intrinsics.checkNotNullParameter(serial, "serial");
            Intrinsics.checkNotNullParameter(privateKey, "privateKey");
            this.serial = serial;
            this.privateKey = privateKey;
        }

        public final PrivateKey getPrivateKey() {
            return this.privateKey;
        }

        public final String getSerial() {
            return this.serial;
        }
    }

    public final synchronized void connect() {
        if (this.socket != null) {
            throw new IllegalStateException("\u0627\u062a\u0635\u0627\u0644 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u0645\u0641\u062a\u0648\u062d \u0623\u0648 \u0642\u064a\u062f \u0627\u0644\u0641\u062a\u062d \u0628\u0627\u0644\u0641\u0639\u0644".toString());
        }
        this.ready = false;
        this.challengeAnswered = false;
        this.socket = this.client.newWebSocket(new Request.Builder().url(this.endpoint).build(), new WebSocketListener() { // from class: com.hazem.dailystore.data.SyncRelayWebSocketClient.connect.2
            @Override // okhttp3.WebSocketListener
            public void onMessage(WebSocket webSocket, String text) {
                Intrinsics.checkNotNullParameter(webSocket, "webSocket");
                Intrinsics.checkNotNullParameter(text, "text");
                try {
                    JSONObject jSONObject = new JSONObject(text);
                    if (Intrinsics.areEqual(jSONObject.optString("type"), "challenge")) {
                        SyncRelayDeviceAuthentication syncRelayDeviceAuthentication = SyncRelayWebSocketClient.this.authentication;
                        if (syncRelayDeviceAuthentication == null) {
                            SyncRelayWebSocketClient.this.listener.onRelayError("AUTHENTICATION_REQUIRED");
                            webSocket.close(PointerIconCompat.TYPE_TEXT, "authentication required");
                            return;
                        }
                        String string = jSONObject.getString("challenge");
                        long j = jSONObject.getLong("expires_at");
                        SyncDeviceCertificate syncDeviceCertificate = SyncDeviceCertificate.INSTANCE;
                        String str = SyncRelayWebSocketClient.this.storeId;
                        String str2 = SyncRelayWebSocketClient.this.deviceId;
                        String serial = syncRelayDeviceAuthentication.getSerial();
                        Intrinsics.checkNotNull(string);
                        String strChallengeSignature = syncDeviceCertificate.challengeSignature(str, str2, serial, string, j, syncRelayDeviceAuthentication.getPrivateKey());
                        SyncRelayWebSocketClient.this.challengeAnswered = true;
                        String string2 = new JSONObject().put("type", "authenticate").put("serial", syncRelayDeviceAuthentication.getSerial()).put("challenge", string).put("signature", strChallengeSignature).toString();
                        Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                        if (webSocket.send(string2)) {
                            return;
                        }
                        SyncRelayWebSocketClient.this.challengeAnswered = false;
                        SyncRelayWebSocketClient.this.listener.onRelayError("AUTHENTICATION_SEND_FAILED");
                        webSocket.close(PointerIconCompat.TYPE_COPY, "authentication send failed");
                        return;
                    }
                    if (!Intrinsics.areEqual(jSONObject.optString("type"), "ready") || SyncRelayWebSocketClient.this.challengeAnswered) {
                        SyncRelayFrameCodec.ServerMessage serverMessageDecodeServerMessage = SyncRelayFrameCodec.INSTANCE.decodeServerMessage(text, SyncRelayWebSocketClient.this.deviceId);
                        if (Intrinsics.areEqual(serverMessageDecodeServerMessage, SyncRelayFrameCodec.ServerMessage.Ready.INSTANCE)) {
                            SyncRelayWebSocketClient.this.ready = true;
                            SyncRelayWebSocketClient.this.listener.onReady();
                            return;
                        } else if (serverMessageDecodeServerMessage instanceof SyncRelayFrameCodec.ServerMessage.Inbound) {
                            SyncRelayWebSocketClient.this.listener.onEncryptedFrame(((SyncRelayFrameCodec.ServerMessage.Inbound) serverMessageDecodeServerMessage).getFromDeviceId(), ((SyncRelayFrameCodec.ServerMessage.Inbound) serverMessageDecodeServerMessage).getFrameId(), ((SyncRelayFrameCodec.ServerMessage.Inbound) serverMessageDecodeServerMessage).getEncryptedFrameJson());
                            return;
                        } else if (serverMessageDecodeServerMessage instanceof SyncRelayFrameCodec.ServerMessage.Delivery) {
                            SyncRelayWebSocketClient.this.listener.onDeliveryStatus(((SyncRelayFrameCodec.ServerMessage.Delivery) serverMessageDecodeServerMessage).getFrameId(), ((SyncRelayFrameCodec.ServerMessage.Delivery) serverMessageDecodeServerMessage).getForwarded(), ((SyncRelayFrameCodec.ServerMessage.Delivery) serverMessageDecodeServerMessage).getRetryable());
                            return;
                        } else {
                            if (serverMessageDecodeServerMessage instanceof SyncRelayFrameCodec.ServerMessage.Error) {
                                SyncRelayWebSocketClient.this.listener.onRelayError(((SyncRelayFrameCodec.ServerMessage.Error) serverMessageDecodeServerMessage).getCode());
                                return;
                            }
                            throw new NoWhenBranchMatchedException();
                        }
                    }
                    SyncRelayWebSocketClient.this.listener.onRelayError("UNSOLICITED_READY");
                    webSocket.close(PointerIconCompat.TYPE_TEXT, "challenge required");
                } catch (Exception unused) {
                    SyncRelayWebSocketClient.this.listener.onRelayError("INVALID_SERVER_MESSAGE");
                }
            }

            @Override // okhttp3.WebSocketListener
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                Intrinsics.checkNotNullParameter(webSocket, "webSocket");
                Intrinsics.checkNotNullParameter(t, "t");
                SyncRelayWebSocketClient syncRelayWebSocketClient = SyncRelayWebSocketClient.this;
                synchronized (syncRelayWebSocketClient) {
                    if (syncRelayWebSocketClient.socket == webSocket) {
                        syncRelayWebSocketClient.socket = null;
                    }
                    syncRelayWebSocketClient.ready = false;
                    Unit unit = Unit.INSTANCE;
                }
                SyncRelayWebSocketClient.this.listener.onDisconnected(t);
            }

            @Override // okhttp3.WebSocketListener
            public void onClosed(WebSocket webSocket, int code, String reason) {
                Intrinsics.checkNotNullParameter(webSocket, "webSocket");
                Intrinsics.checkNotNullParameter(reason, "reason");
                SyncRelayWebSocketClient syncRelayWebSocketClient = SyncRelayWebSocketClient.this;
                synchronized (syncRelayWebSocketClient) {
                    if (syncRelayWebSocketClient.socket == webSocket) {
                        syncRelayWebSocketClient.socket = null;
                    }
                    syncRelayWebSocketClient.ready = false;
                    Unit unit = Unit.INSTANCE;
                }
                SyncRelayWebSocketClient.this.listener.onDisconnected(null);
            }
        });
    }

    public final synchronized boolean sendEncryptedFrame(String operationId, String recipientDeviceId, String encryptedFrameJson) {
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        Intrinsics.checkNotNullParameter(recipientDeviceId, "recipientDeviceId");
        Intrinsics.checkNotNullParameter(encryptedFrameJson, "encryptedFrameJson");
        WebSocket webSocket = this.socket;
        if (webSocket == null) {
            return false;
        }
        if (!this.ready) {
            return false;
        }
        return webSocket.send(SyncRelayFrameCodec.INSTANCE.encodeRelayRequest(this.storeId, this.deviceId, recipientDeviceId, operationId, encryptedFrameJson));
    }

    public final synchronized void disconnect() {
        this.ready = false;
        this.challengeAnswered = false;
        WebSocket webSocket = this.socket;
        this.socket = null;
        if (webSocket != null) {
            webSocket.close(1000, "client disconnect");
        }
    }

    /* JADX INFO: renamed from: isReady, reason: from getter */
    public final boolean getReady() {
        return this.ready;
    }

    /* JADX INFO: compiled from: SyncRelayWebSocketClient.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0002J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0002J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/hazem/dailystore/data/SyncRelayWebSocketClient$Companion;", "", "<init>", "()V", "MAX_FRAME_CHARS", "", "defaultClient", "Lokhttp3/OkHttpClient;", "buildEndpoint", "Lokhttp3/HttpUrl;", "base", "", "storeId", "deviceId", "requireUuid", "", "value", AnnotatedPrivateKey.LABEL, "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final OkHttpClient defaultClient() {
            return new OkHttpClient.Builder().connectTimeout(15L, TimeUnit.SECONDS).readTimeout(0L, TimeUnit.MILLISECONDS).retryOnConnectionFailure(false).build();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final HttpUrl buildEndpoint(String base, String storeId, String deviceId) {
            requireUuid(storeId, "\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0645\u062a\u062c\u0631");
            requireUuid(deviceId, "\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u062c\u0647\u0627\u0632");
            if (!StringsKt.startsWith(base, "wss://", true)) {
                throw new IllegalArgumentException("\u064a\u062c\u0628 \u0627\u0633\u062a\u062e\u062f\u0627\u0645 \u0639\u0646\u0648\u0627\u0646 WSS \u0645\u0634\u0641\u0631".toString());
            }
            String strSubstring = base.substring(6);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            HttpUrl httpUrl = HttpUrl.INSTANCE.parse("https://" + strSubstring);
            if (httpUrl == null) {
                throw new IllegalArgumentException("\u0639\u0646\u0648\u0627\u0646 \u0648\u0633\u064a\u0637 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d");
            }
            if (!Intrinsics.areEqual(httpUrl.scheme(), "https")) {
                throw new IllegalArgumentException("\u064a\u062c\u0628 \u0627\u0633\u062a\u062e\u062f\u0627\u0645 \u0627\u062a\u0635\u0627\u0644 WebSocket \u0645\u0634\u0641\u0631 WSS".toString());
            }
            if (httpUrl.username().length() != 0 || httpUrl.password().length() != 0 || httpUrl.query() != null || httpUrl.fragment() != null) {
                throw new IllegalArgumentException("\u0639\u0646\u0648\u0627\u0646 \u0648\u0633\u064a\u0637 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u064a\u062d\u062a\u0648\u064a \u0628\u064a\u0627\u0646\u0627\u062a \u0623\u0648 \u0645\u0639\u0627\u0645\u0644\u0627\u062a \u063a\u064a\u0631 \u0645\u0633\u0645\u0648\u062d\u0629".toString());
            }
            if (!Intrinsics.areEqual(httpUrl.encodedPath(), "/") && httpUrl.encodedPath().length() != 0) {
                throw new IllegalArgumentException("\u0623\u062f\u062e\u0644 \u0639\u0646\u0648\u0627\u0646 \u0627\u0644\u0648\u0633\u064a\u0637 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u062f\u0648\u0646 \u0645\u0633\u0627\u0631 \u0625\u0636\u0627\u0641\u064a".toString());
            }
            return httpUrl.newBuilder().addPathSegments("v1/ws/" + storeId + "/" + deviceId).build();
        }

        private final void requireUuid(String value, String label) {
            Object objM381constructorimpl;
            try {
                Result.Companion companion = Result.INSTANCE;
                Companion companion2 = this;
                objM381constructorimpl = Result.m381constructorimpl(UUID.fromString(value));
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m388isSuccessimpl(objM381constructorimpl)) {
                return;
            }
            throw new IllegalArgumentException((label + " \u063a\u064a\u0631 \u0635\u0627\u0644\u062d").toString());
        }
    }
}
