package com.hazem.dailystore.data;

import android.util.Base64;
import java.security.PrivateKey;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: SyncDeviceCertificate.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J8\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\tJD\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00072\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\u0011\u001a\u00020\tJ\u000e\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0007J6\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u0010J0\u0010\u001f\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010 \u001a\u00020\tJ@\u0010!\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\tH\u0002J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0007H\u0002J\u0010\u0010'\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u0007H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/hazem/dailystore/data/SyncDeviceCertificate;", "", "<init>", "()V", "VERSION", "", "DOMAIN", "", "CERTIFICATE_TTL_MS", "", "issue", "storeId", "issuerDeviceId", "deviceId", "devicePublicKeyBase64", "rootPrivateKey", "Ljava/security/PrivateKey;", "nowMillis", "verify", "", "certificateJson", "expectedStoreId", "expectedDeviceId", "expectedIssuerDeviceId", "rootPublicKeyBase64", "expectedDevicePublicKeyBase64", "serial", "challengeSignature", "challenge", "expiresAt", "privateKey", "signedRevocation", "revokedAt", "certificatePayload", "publicKey", "issuedAt", "requireUuid", "", "value", "isUuid", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SyncDeviceCertificate {
    private static final long CERTIFICATE_TTL_MS = 31536000000L;
    private static final String DOMAIN = "THE_DAILY_SHOP_RELAY_AUTH_V1";
    public static final SyncDeviceCertificate INSTANCE = new SyncDeviceCertificate();
    private static final int VERSION = 1;

    private SyncDeviceCertificate() {
    }

    public final String issue(String storeId, String issuerDeviceId, String deviceId, String devicePublicKeyBase64, PrivateKey rootPrivateKey, long nowMillis) {
        Intrinsics.checkNotNullParameter(storeId, "storeId");
        Intrinsics.checkNotNullParameter(issuerDeviceId, "issuerDeviceId");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(devicePublicKeyBase64, "devicePublicKeyBase64");
        Intrinsics.checkNotNullParameter(rootPrivateKey, "rootPrivateKey");
        if (StringsKt.isBlank(storeId)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        requireUuid(issuerDeviceId);
        requireUuid(deviceId);
        int length = Base64.decode(devicePublicKeyBase64, 0).length;
        if (64 > length || length >= 257) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        String strCertificatePayload = certificatePayload(storeId, issuerDeviceId, deviceId, devicePublicKeyBase64, string, nowMillis, nowMillis + CERTIFICATE_TTL_MS);
        SyncDeviceAuthenticator syncDeviceAuthenticator = SyncDeviceAuthenticator.INSTANCE;
        byte[] bytes = strCertificatePayload.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        String string2 = new JSONObject(strCertificatePayload).put("signature", Base64.encodeToString(syncDeviceAuthenticator.signBytes(rootPrivateKey, bytes), 2)).toString();
        Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
        return string2;
    }

    public final boolean verify(String certificateJson, String expectedStoreId, String expectedDeviceId, String expectedIssuerDeviceId, String rootPublicKeyBase64, String expectedDevicePublicKeyBase64, long nowMillis) {
        Object objM381constructorimpl;
        Intrinsics.checkNotNullParameter(certificateJson, "certificateJson");
        Intrinsics.checkNotNullParameter(expectedStoreId, "expectedStoreId");
        Intrinsics.checkNotNullParameter(expectedDeviceId, "expectedDeviceId");
        Intrinsics.checkNotNullParameter(expectedIssuerDeviceId, "expectedIssuerDeviceId");
        Intrinsics.checkNotNullParameter(rootPublicKeyBase64, "rootPublicKeyBase64");
        try {
            Result.Companion companion = Result.INSTANCE;
            SyncDeviceCertificate syncDeviceCertificate = this;
            JSONObject jSONObject = new JSONObject(certificateJson);
            String string = jSONObject.getString("store_id");
            String string2 = jSONObject.getString("device_id");
            String string3 = jSONObject.getString("issuer_device_id");
            String string4 = jSONObject.getString("public_key");
            String string5 = jSONObject.getString("serial");
            long j = jSONObject.getLong("issued_at");
            long j2 = jSONObject.getLong("expires_at");
            byte[] bArrDecode = Base64.decode(jSONObject.getString("signature"), 0);
            if (jSONObject.getInt("version") != 1 || !Intrinsics.areEqual(string, expectedStoreId) || !Intrinsics.areEqual(string2, expectedDeviceId) || !Intrinsics.areEqual(string3, expectedIssuerDeviceId)) {
                return false;
            }
            if (expectedDevicePublicKeyBase64 != null && (!Intrinsics.areEqual(expectedDevicePublicKeyBase64, string4))) {
                return false;
            }
            Intrinsics.checkNotNull(string5);
            if (isUuid(string5) && j <= nowMillis + ((long) 60000) && j2 > nowMillis && j2 > j && j2 - j <= CERTIFICATE_TTL_MS) {
                Intrinsics.checkNotNull(string4);
                String strCertificatePayload = certificatePayload(string, string3, string2, string4, string5, j, j2);
                SyncDeviceAuthenticator syncDeviceAuthenticator = SyncDeviceAuthenticator.INSTANCE;
                byte[] bArrDecode2 = Base64.decode(rootPublicKeyBase64, 0);
                Intrinsics.checkNotNullExpressionValue(bArrDecode2, "decode(...)");
                byte[] bytes = strCertificatePayload.getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                Intrinsics.checkNotNull(bArrDecode);
                objM381constructorimpl = Result.m381constructorimpl(Boolean.valueOf(syncDeviceAuthenticator.verifyBytes(bArrDecode2, bytes, bArrDecode)));
            }
            return false;
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m387isFailureimpl(objM381constructorimpl)) {
            objM381constructorimpl = false;
        }
        return ((Boolean) objM381constructorimpl).booleanValue();
    }

    public final String serial(String certificateJson) throws JSONException {
        Intrinsics.checkNotNullParameter(certificateJson, "certificateJson");
        String string = new JSONObject(certificateJson).getString("serial");
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public final String challengeSignature(String storeId, String deviceId, String serial, String challenge, long expiresAt, PrivateKey privateKey) {
        Intrinsics.checkNotNullParameter(storeId, "storeId");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(serial, "serial");
        Intrinsics.checkNotNullParameter(challenge, "challenge");
        Intrinsics.checkNotNullParameter(privateKey, "privateKey");
        requireUuid(storeId);
        requireUuid(deviceId);
        if (!Intrinsics.areEqual(serial, "ROOT") && !isUuid(serial)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!new Regex("[A-Za-z0-9_-]{20,128}").matches(challenge)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (expiresAt <= 0) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        String string = new JSONArray().put(DOMAIN).put(storeId).put(deviceId).put(serial).put(challenge).put(expiresAt).toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        SyncDeviceAuthenticator syncDeviceAuthenticator = SyncDeviceAuthenticator.INSTANCE;
        byte[] bytes = string.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        String strEncodeToString = Base64.encodeToString(syncDeviceAuthenticator.signBytes(privateKey, bytes), 2);
        Intrinsics.checkNotNullExpressionValue(strEncodeToString, "encodeToString(...)");
        return strEncodeToString;
    }

    public static /* synthetic */ String signedRevocation$default(SyncDeviceCertificate syncDeviceCertificate, String str, String str2, String str3, PrivateKey privateKey, long j, int i, Object obj) {
        if ((i & 16) != 0) {
            j = System.currentTimeMillis();
        }
        return syncDeviceCertificate.signedRevocation(str, str2, str3, privateKey, j);
    }

    public final String signedRevocation(String storeId, String deviceId, String serial, PrivateKey rootPrivateKey, long revokedAt) throws JSONException {
        Intrinsics.checkNotNullParameter(storeId, "storeId");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(serial, "serial");
        Intrinsics.checkNotNullParameter(rootPrivateKey, "rootPrivateKey");
        requireUuid(storeId);
        requireUuid(deviceId);
        requireUuid(serial);
        JSONObject jSONObjectPut = new JSONObject().put("version", 1).put("store_id", storeId).put("device_id", deviceId).put("serial", serial).put("revoked_at", revokedAt);
        SyncDeviceAuthenticator syncDeviceAuthenticator = SyncDeviceAuthenticator.INSTANCE;
        String string = jSONObjectPut.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        byte[] bytes = string.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        String string2 = jSONObjectPut.put("signature", Base64.encodeToString(syncDeviceAuthenticator.signBytes(rootPrivateKey, bytes), 2)).toString();
        Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
        return string2;
    }

    private final String certificatePayload(String storeId, String issuerDeviceId, String deviceId, String publicKey, String serial, long issuedAt, long expiresAt) {
        String string = new JSONObject().put("version", 1).put("store_id", storeId).put("issuer_device_id", issuerDeviceId).put("device_id", deviceId).put("public_key", publicKey).put("serial", serial).put("issued_at", issuedAt).put("expires_at", expiresAt).toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    private final void requireUuid(String value) {
        if (!isUuid(value)) {
            throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0634\u0647\u0627\u062f\u0629 \u0627\u0644\u062c\u0647\u0627\u0632 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
        }
    }

    private final boolean isUuid(String value) {
        Object objM381constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            SyncDeviceCertificate syncDeviceCertificate = this;
            objM381constructorimpl = Result.m381constructorimpl(UUID.fromString(value));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
        }
        return Result.m388isSuccessimpl(objM381constructorimpl);
    }
}
