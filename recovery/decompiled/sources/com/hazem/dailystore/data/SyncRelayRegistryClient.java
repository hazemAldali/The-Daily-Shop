package com.hazem.dailystore.data;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: SyncRelayRegistryClient.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u0000 \u00142\u00020\u0001:\u0002\u0013\u0014B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003J\u001e\u0010\r\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0003J\u001e\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/hazem/dailystore/data/SyncRelayRegistryClient;", "", "relayBaseUrl", "", "client", "Lokhttp3/OkHttpClient;", "<init>", "(Ljava/lang/String;Lokhttp3/OkHttpClient;)V", "base", "Lokhttp3/HttpUrl;", "fetchTrustRoot", "Lcom/hazem/dailystore/data/SyncRelayRegistryClient$TrustRoot;", "storeId", "registerCertificate", "", "deviceId", "certificateJson", "revokeCertificate", "signedRevocationJson", "TrustRoot", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SyncRelayRegistryClient {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final MediaType JSON = MediaType.INSTANCE.get("application/json; charset=utf-8");
    private final HttpUrl base;
    private final OkHttpClient client;

    public SyncRelayRegistryClient(String relayBaseUrl, OkHttpClient client) {
        Intrinsics.checkNotNullParameter(relayBaseUrl, "relayBaseUrl");
        Intrinsics.checkNotNullParameter(client, "client");
        this.client = client;
        this.base = INSTANCE.parseBase(relayBaseUrl);
    }

    public /* synthetic */ SyncRelayRegistryClient(String str, OkHttpClient okHttpClient, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? new OkHttpClient.Builder().connectTimeout(15L, TimeUnit.SECONDS).readTimeout(15L, TimeUnit.SECONDS).build() : okHttpClient);
    }

    /* JADX INFO: compiled from: SyncRelayRegistryClient.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J1\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n\u00a8\u0006\u0019"}, d2 = {"Lcom/hazem/dailystore/data/SyncRelayRegistryClient$TrustRoot;", "", "storeId", "", "deviceId", "publicKeyBase64", "algorithm", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getStoreId", "()Ljava/lang/String;", "getDeviceId", "getPublicKeyBase64", "getAlgorithm", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class TrustRoot {
        private final String algorithm;
        private final String deviceId;
        private final String publicKeyBase64;
        private final String storeId;

        public static /* synthetic */ TrustRoot copy$default(TrustRoot trustRoot, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = trustRoot.storeId;
            }
            if ((i & 2) != 0) {
                str2 = trustRoot.deviceId;
            }
            if ((i & 4) != 0) {
                str3 = trustRoot.publicKeyBase64;
            }
            if ((i & 8) != 0) {
                str4 = trustRoot.algorithm;
            }
            return trustRoot.copy(str, str2, str3, str4);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getStoreId() {
            return this.storeId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getDeviceId() {
            return this.deviceId;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getPublicKeyBase64() {
            return this.publicKeyBase64;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getAlgorithm() {
            return this.algorithm;
        }

        public final TrustRoot copy(String storeId, String deviceId, String publicKeyBase64, String algorithm) {
            Intrinsics.checkNotNullParameter(storeId, "storeId");
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            Intrinsics.checkNotNullParameter(publicKeyBase64, "publicKeyBase64");
            Intrinsics.checkNotNullParameter(algorithm, "algorithm");
            return new TrustRoot(storeId, deviceId, publicKeyBase64, algorithm);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TrustRoot)) {
                return false;
            }
            TrustRoot trustRoot = (TrustRoot) other;
            return Intrinsics.areEqual(this.storeId, trustRoot.storeId) && Intrinsics.areEqual(this.deviceId, trustRoot.deviceId) && Intrinsics.areEqual(this.publicKeyBase64, trustRoot.publicKeyBase64) && Intrinsics.areEqual(this.algorithm, trustRoot.algorithm);
        }

        public int hashCode() {
            return (((((this.storeId.hashCode() * 31) + this.deviceId.hashCode()) * 31) + this.publicKeyBase64.hashCode()) * 31) + this.algorithm.hashCode();
        }

        public String toString() {
            return "TrustRoot(storeId=" + this.storeId + ", deviceId=" + this.deviceId + ", publicKeyBase64=" + this.publicKeyBase64 + ", algorithm=" + this.algorithm + ")";
        }

        public TrustRoot(String storeId, String deviceId, String publicKeyBase64, String algorithm) {
            Intrinsics.checkNotNullParameter(storeId, "storeId");
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            Intrinsics.checkNotNullParameter(publicKeyBase64, "publicKeyBase64");
            Intrinsics.checkNotNullParameter(algorithm, "algorithm");
            this.storeId = storeId;
            this.deviceId = deviceId;
            this.publicKeyBase64 = publicKeyBase64;
            this.algorithm = algorithm;
        }

        public final String getStoreId() {
            return this.storeId;
        }

        public final String getDeviceId() {
            return this.deviceId;
        }

        public final String getPublicKeyBase64() {
            return this.publicKeyBase64;
        }

        public final String getAlgorithm() {
            return this.algorithm;
        }
    }

    public final TrustRoot fetchTrustRoot(String storeId) {
        String strString;
        Intrinsics.checkNotNullParameter(storeId, "storeId");
        Companion companion = INSTANCE;
        companion.requireUuid(storeId);
        Response responseExecute = this.client.newCall(new Request.Builder().url(this.base.newBuilder().addPathSegments("v1/trust/" + storeId).build()).get().build()).execute();
        try {
            Response response = responseExecute;
            if (!response.isSuccessful()) {
                throw new IllegalStateException(("\u062a\u0639\u0630\u0631 \u062c\u0644\u0628 \u062c\u0630\u0631 \u0627\u0644\u062b\u0642\u0629 \u0645\u0646 \u0648\u0633\u064a\u0637 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 (" + response.code() + ")").toString());
            }
            ResponseBody responseBodyBody = response.body();
            if (responseBodyBody == null || (strString = responseBodyBody.string()) == null) {
                throw new IllegalStateException("\u0627\u0633\u062a\u062c\u0627\u0628\u0629 \u062c\u0630\u0631 \u0627\u0644\u062b\u0642\u0629 \u0641\u0627\u0631\u063a\u0629".toString());
            }
            JSONObject jSONObject = new JSONObject(strString);
            if (!Intrinsics.areEqual(jSONObject.getString("store_id"), storeId)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            String string = jSONObject.getString("device_id");
            Intrinsics.checkNotNull(string);
            companion.requireUuid(string);
            String string2 = jSONObject.getString("algorithm");
            if (!Intrinsics.areEqual(string2, "EC_P256_SHA256")) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            String string3 = jSONObject.getString("public_key");
            Intrinsics.checkNotNull(string3);
            if (StringsKt.isBlank(string3)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            TrustRoot trustRoot = new TrustRoot(storeId, string, string3, string2);
            CloseableKt.closeFinally(responseExecute, null);
            return trustRoot;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(responseExecute, th);
                throw th2;
            }
        }
    }

    public final boolean registerCertificate(String storeId, String deviceId, String certificateJson) throws JSONException, IOException {
        String strString;
        Intrinsics.checkNotNullParameter(storeId, "storeId");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(certificateJson, "certificateJson");
        Companion companion = INSTANCE;
        companion.requireUuid(storeId);
        companion.requireUuid(deviceId);
        JSONObject jSONObject = new JSONObject(certificateJson);
        if (Intrinsics.areEqual(jSONObject.getString("store_id"), storeId) && Intrinsics.areEqual(jSONObject.getString("device_id"), deviceId)) {
            String string = jSONObject.getString("signature");
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            if (!StringsKt.isBlank(string)) {
                Response responseExecute = this.client.newCall(new Request.Builder().url(this.base.newBuilder().addPathSegments("v1/devices/" + storeId + "/" + deviceId + "/certificate").build()).post(RequestBody.INSTANCE.create(certificateJson, JSON)).build()).execute();
                try {
                    Response response = responseExecute;
                    if (!response.isSuccessful()) {
                        throw new IllegalStateException(("\u0631\u0641\u0636 \u0648\u0633\u064a\u0637 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u0634\u0647\u0627\u062f\u0629 \u0627\u0644\u062c\u0647\u0627\u0632 (" + response.code() + ")").toString());
                    }
                    ResponseBody responseBodyBody = response.body();
                    if (responseBodyBody == null || (strString = responseBodyBody.string()) == null) {
                        strString = "{}";
                    }
                    boolean zOptBoolean = new JSONObject(strString).optBoolean("registered");
                    CloseableKt.closeFinally(responseExecute, null);
                    return zOptBoolean;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(responseExecute, th);
                        throw th2;
                    }
                }
            }
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    public final boolean revokeCertificate(String storeId, String deviceId, String signedRevocationJson) throws JSONException, IOException {
        String strString;
        Intrinsics.checkNotNullParameter(storeId, "storeId");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(signedRevocationJson, "signedRevocationJson");
        Companion companion = INSTANCE;
        companion.requireUuid(storeId);
        companion.requireUuid(deviceId);
        JSONObject jSONObject = new JSONObject(signedRevocationJson);
        if (Intrinsics.areEqual(jSONObject.getString("store_id"), storeId) && Intrinsics.areEqual(jSONObject.getString("device_id"), deviceId)) {
            String string = jSONObject.getString("signature");
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            if (!StringsKt.isBlank(string)) {
                Response responseExecute = this.client.newCall(new Request.Builder().url(this.base.newBuilder().addPathSegments("v1/devices/" + storeId + "/" + deviceId + "/revoke").build()).post(RequestBody.INSTANCE.create(signedRevocationJson, JSON)).build()).execute();
                try {
                    Response response = responseExecute;
                    if (!response.isSuccessful()) {
                        throw new IllegalStateException(("\u0631\u0641\u0636 \u0648\u0633\u064a\u0637 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u0625\u0644\u063a\u0627\u0621 \u0627\u0644\u062c\u0647\u0627\u0632 (" + response.code() + ")").toString());
                    }
                    ResponseBody responseBodyBody = response.body();
                    if (responseBodyBody == null || (strString = responseBodyBody.string()) == null) {
                        strString = "{}";
                    }
                    boolean zOptBoolean = new JSONObject(strString).optBoolean("revoked");
                    CloseableKt.closeFinally(responseExecute, null);
                    return zOptBoolean;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(responseExecute, th);
                        throw th2;
                    }
                }
            }
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    /* JADX INFO: compiled from: SyncRelayRegistryClient.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/hazem/dailystore/data/SyncRelayRegistryClient$Companion;", "", "<init>", "()V", "JSON", "Lokhttp3/MediaType;", "parseBase", "Lokhttp3/HttpUrl;", "value", "", "requireUuid", "", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final HttpUrl parseBase(String value) {
            if (!StringsKt.startsWith(value, "https://", true)) {
                throw new IllegalArgumentException("\u064a\u062c\u0628 \u0627\u0633\u062a\u062e\u062f\u0627\u0645 HTTPS \u0645\u0634\u0641\u0631 \u0644\u0625\u062f\u0627\u0631\u0629 \u0634\u0647\u0627\u062f\u0627\u062a \u0627\u0644\u0623\u062c\u0647\u0632\u0629".toString());
            }
            HttpUrl httpUrl = HttpUrl.INSTANCE.parse(value);
            if (httpUrl == null) {
                throw new IllegalArgumentException("\u0639\u0646\u0648\u0627\u0646 \u0648\u0633\u064a\u0637 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d");
            }
            if (Intrinsics.areEqual(httpUrl.scheme(), "https") && httpUrl.username().length() == 0 && httpUrl.password().length() == 0 && httpUrl.query() == null && httpUrl.fragment() == null && (Intrinsics.areEqual(httpUrl.encodedPath(), "/") || httpUrl.encodedPath().length() == 0)) {
                return httpUrl;
            }
            throw new IllegalArgumentException("\u0639\u0646\u0648\u0627\u0646 \u0648\u0633\u064a\u0637 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u064a\u062c\u0628 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0623\u0633\u0627\u0633 HTTPS \u062f\u0648\u0646 \u0645\u0633\u0627\u0631\u0627\u062a \u0623\u0648 \u0628\u064a\u0627\u0646\u0627\u062a \u0625\u0636\u0627\u0641\u064a\u0629".toString());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void requireUuid(String value) {
            Object objM381constructorimpl;
            try {
                Result.Companion companion = Result.INSTANCE;
                Companion companion2 = this;
                objM381constructorimpl = Result.m381constructorimpl(UUID.fromString(value));
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
            }
            if (!Result.m388isSuccessimpl(objM381constructorimpl)) {
                throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0645\u062a\u062c\u0631 \u0623\u0648 \u0627\u0644\u062c\u0647\u0627\u0632 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
            }
        }
    }
}
