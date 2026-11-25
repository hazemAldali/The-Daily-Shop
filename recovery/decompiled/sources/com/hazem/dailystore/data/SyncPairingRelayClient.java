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

/* JADX INFO: compiled from: SyncPairingRelayClient.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000f\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J&\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003J\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00032\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003J.\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0003J(\u0010\u0014\u001a\u0004\u0018\u00010\u00032\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0003J>\u0010\u0015\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/hazem/dailystore/data/SyncPairingRelayClient;", "", "relayBaseUrl", "", "client", "Lokhttp3/OkHttpClient;", "<init>", "(Ljava/lang/String;Lokhttp3/OkHttpClient;)V", "base", "Lokhttp3/HttpUrl;", "publishRequest", "", "storeId", "invitationToken", "responseToken", "requestJson", "receiveRequest", "publishResponse", "deviceId", "responseJson", "receiveResponse", "execute", "url", "method", "body", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SyncPairingRelayClient {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final MediaType JSON = MediaType.INSTANCE.get("application/json; charset=utf-8");
    private final HttpUrl base;
    private final OkHttpClient client;

    public SyncPairingRelayClient(String relayBaseUrl, OkHttpClient client) {
        Intrinsics.checkNotNullParameter(relayBaseUrl, "relayBaseUrl");
        Intrinsics.checkNotNullParameter(client, "client");
        this.client = client;
        this.base = INSTANCE.parseBase(relayBaseUrl);
    }

    public /* synthetic */ SyncPairingRelayClient(String str, OkHttpClient okHttpClient, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? new OkHttpClient.Builder().connectTimeout(10L, TimeUnit.SECONDS).readTimeout(15L, TimeUnit.SECONDS).build() : okHttpClient);
    }

    public final void publishRequest(String storeId, String invitationToken, String responseToken, String requestJson) throws JSONException {
        Intrinsics.checkNotNullParameter(storeId, "storeId");
        Intrinsics.checkNotNullParameter(invitationToken, "invitationToken");
        Intrinsics.checkNotNullParameter(responseToken, "responseToken");
        Intrinsics.checkNotNullParameter(requestJson, "requestJson");
        INSTANCE.requireUuid(storeId);
        JSONObject jSONObjectPut = new JSONObject().put("response_token", responseToken).put("request", new JSONObject(requestJson));
        execute$default(this, this.base.newBuilder().addPathSegments("v1/pairing/" + storeId + "/request").build(), "POST", jSONObjectPut.toString(), null, invitationToken, 8, null);
    }

    public final String receiveRequest(String storeId, String invitationToken) {
        String string;
        Intrinsics.checkNotNullParameter(storeId, "storeId");
        Intrinsics.checkNotNullParameter(invitationToken, "invitationToken");
        INSTANCE.requireUuid(storeId);
        String strExecute$default = execute$default(this, this.base.newBuilder().addPathSegments("v1/pairing/" + storeId + "/request").build(), "GET", null, null, invitationToken, 12, null);
        if (strExecute$default == null) {
            return null;
        }
        JSONObject jSONObjectOptJSONObject = new JSONObject(strExecute$default).optJSONObject("request");
        if (jSONObjectOptJSONObject == null || (string = jSONObjectOptJSONObject.toString()) == null) {
            throw new IllegalStateException("\u0627\u0633\u062a\u062c\u0627\u0628\u0629 \u0637\u0644\u0628 \u0627\u0644\u0627\u0642\u062a\u0631\u0627\u0646 \u0645\u0646 \u0627\u0644\u0648\u0633\u064a\u0637 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
        }
        return string;
    }

    public final void publishResponse(String storeId, String invitationToken, String deviceId, String responseToken, String responseJson) throws JSONException {
        Intrinsics.checkNotNullParameter(storeId, "storeId");
        Intrinsics.checkNotNullParameter(invitationToken, "invitationToken");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(responseToken, "responseToken");
        Intrinsics.checkNotNullParameter(responseJson, "responseJson");
        Companion companion = INSTANCE;
        companion.requireUuid(storeId);
        companion.requireUuid(deviceId);
        JSONObject jSONObjectPut = new JSONObject().put("device_id", deviceId).put("response_token", responseToken).put("response", new JSONObject(responseJson));
        execute$default(this, this.base.newBuilder().addPathSegments("v1/pairing/" + storeId + "/response").build(), "POST", jSONObjectPut.toString(), null, invitationToken, 8, null);
    }

    public final String receiveResponse(String storeId, String invitationToken, String deviceId, String responseToken) {
        String string;
        Intrinsics.checkNotNullParameter(storeId, "storeId");
        Intrinsics.checkNotNullParameter(invitationToken, "invitationToken");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(responseToken, "responseToken");
        Companion companion = INSTANCE;
        companion.requireUuid(storeId);
        companion.requireUuid(deviceId);
        String strExecute$default = execute$default(this, this.base.newBuilder().addPathSegments("v1/pairing/" + storeId + "/response").addQueryParameter("device_id", deviceId).build(), "GET", null, responseToken, invitationToken, 4, null);
        if (strExecute$default == null) {
            return null;
        }
        JSONObject jSONObjectOptJSONObject = new JSONObject(strExecute$default).optJSONObject("response");
        if (jSONObjectOptJSONObject == null || (string = jSONObjectOptJSONObject.toString()) == null) {
            throw new IllegalStateException("\u0627\u0633\u062a\u062c\u0627\u0628\u0629 \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u062c\u0647\u0627\u0632 \u0645\u0646 \u0627\u0644\u0648\u0633\u064a\u0637 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
        }
        return string;
    }

    static /* synthetic */ String execute$default(SyncPairingRelayClient syncPairingRelayClient, HttpUrl httpUrl, String str, String str2, String str3, String str4, int i, Object obj) {
        return syncPairingRelayClient.execute(httpUrl, str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : str4);
    }

    private final String execute(HttpUrl url, String method, String body, String responseToken, String invitationToken) throws IOException {
        String strString;
        String str;
        Request.Builder builderUrl = new Request.Builder().url(url);
        if (responseToken != null) {
            builderUrl.header("X-Pairing-Response-Token", responseToken);
        }
        if (invitationToken != null) {
            builderUrl.header("X-Pairing-Invitation-Token", invitationToken);
        }
        if (Intrinsics.areEqual(method, "POST")) {
            RequestBody.Companion companion = RequestBody.INSTANCE;
            if (body == null) {
                throw new IllegalArgumentException("Required value was null.".toString());
            }
            builderUrl.post(companion.create(body, JSON));
        } else {
            builderUrl.get();
        }
        Response responseExecute = this.client.newCall(builderUrl.build()).execute();
        try {
            Response response = responseExecute;
            if (response.code() == 404 && Intrinsics.areEqual(method, "GET")) {
                CloseableKt.closeFinally(responseExecute, null);
                return null;
            }
            if (!response.isSuccessful()) {
                int iCode = response.code();
                if (iCode == 403) {
                    str = "\u0631\u0641\u0636 \u0648\u0633\u064a\u0637 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u0637\u0644\u0628 \u0627\u0644\u0627\u0642\u062a\u0631\u0627\u0646 \u0623\u0648 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0648\u0635\u0648\u0644";
                } else if (iCode == 503) {
                    str = "\u062c\u0630\u0631 \u0627\u0644\u062b\u0642\u0629 \u063a\u064a\u0631 \u0645\u0647\u064a\u0623 \u0639\u0644\u0649 \u0648\u0633\u064a\u0637 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629";
                } else if (iCode == 409) {
                    str = "\u062a\u0648\u062c\u062f \u0645\u062d\u0627\u0648\u0644\u0629 \u0627\u0642\u062a\u0631\u0627\u0646 \u0623\u062e\u0631\u0649 \u0644\u0647\u0630\u0647 \u0627\u0644\u062f\u0639\u0648\u0629";
                } else if (iCode == 410) {
                    str = "\u0627\u0646\u062a\u0647\u062a \u0635\u0644\u0627\u062d\u064a\u0629 \u062f\u0639\u0648\u0629 \u0627\u0644\u0627\u0642\u062a\u0631\u0627\u0646\u061b \u0623\u0646\u0634\u0626 \u062f\u0639\u0648\u0629 \u062c\u062f\u064a\u062f\u0629";
                } else {
                    str = "\u062a\u0639\u0630\u0631 \u0625\u062a\u0645\u0627\u0645 \u062a\u0628\u0627\u062f\u0644 \u0627\u0644\u0627\u0642\u062a\u0631\u0627\u0646 \u0639\u0628\u0631 \u0627\u0644\u0648\u0633\u064a\u0637 (" + response.code() + ")";
                }
                throw new IllegalStateException(str.toString());
            }
            ResponseBody responseBodyBody = response.body();
            if (responseBodyBody == null || (strString = responseBodyBody.string()) == null) {
                strString = "{}";
            }
            CloseableKt.closeFinally(responseExecute, null);
            return strString;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(responseExecute, th);
                throw th2;
            }
        }
    }

    /* JADX INFO: compiled from: SyncPairingRelayClient.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/hazem/dailystore/data/SyncPairingRelayClient$Companion;", "", "<init>", "()V", "JSON", "Lokhttp3/MediaType;", "parseBase", "Lokhttp3/HttpUrl;", "value", "", "requireUuid", "", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final HttpUrl parseBase(String value) {
            if (!StringsKt.startsWith(value, "https://", true)) {
                throw new IllegalArgumentException("\u064a\u062c\u0628 \u0627\u0633\u062a\u062e\u062f\u0627\u0645 HTTPS \u0645\u0634\u0641\u0631 \u0644\u0644\u0627\u0642\u062a\u0631\u0627\u0646".toString());
            }
            HttpUrl httpUrl = HttpUrl.INSTANCE.parse(value);
            if (httpUrl == null) {
                throw new IllegalStateException("\u0639\u0646\u0648\u0627\u0646 \u0648\u0633\u064a\u0637 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
            }
            if (Intrinsics.areEqual(httpUrl.scheme(), "https") && httpUrl.username().length() == 0 && httpUrl.password().length() == 0 && httpUrl.query() == null && httpUrl.fragment() == null && (Intrinsics.areEqual(httpUrl.encodedPath(), "/") || httpUrl.encodedPath().length() == 0)) {
                return httpUrl;
            }
            throw new IllegalArgumentException("\u0639\u0646\u0648\u0627\u0646 \u0627\u0644\u0648\u0633\u064a\u0637 \u064a\u062c\u0628 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0623\u0633\u0627\u0633 HTTPS \u062f\u0648\u0646 \u0645\u0633\u0627\u0631\u0627\u062a \u0625\u0636\u0627\u0641\u064a\u0629".toString());
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
                throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0645\u062a\u062c\u0631 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
            }
        }
    }
}
