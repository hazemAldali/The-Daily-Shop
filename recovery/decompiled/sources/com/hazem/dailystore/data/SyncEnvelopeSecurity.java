package com.hazem.dailystore.data;

import com.hazem.dailystore.SyncDeviceManagementActivity;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;
import kotlin.text.CharsKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: SyncEnvelopeSecurity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003Jf\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0018J\u0016\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\nJ\u000e\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\bJ\u000e\u0010\u001f\u001a\u00020\b2\u0006\u0010 \u001a\u00020\bJ\u0012\u0010!\u001a\u00020\b2\b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u0002J\u0018\u0010#\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010$\u001a\u00020\u0018H\u0002J\u0010\u0010%\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\bH\u0002J\u0010\u0010&\u001a\u00020\b2\u0006\u0010'\u001a\u00020\u0018H\u0002J\u0010\u0010(\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lcom/hazem/dailystore/data/SyncEnvelopeSecurity;", "", "<init>", "()V", "PROTOCOL_VERSION", "", "allowedEntities", "", "", "create", "Lcom/hazem/dailystore/data/AuthenticatedSyncEnvelope;", "storeId", "operationId", "sequenceNumber", "", "originDeviceId", "actorUserId", "operationType", "entityType", "entityId", "baseRevision", "payloadJson", "createdAt", "deviceSecret", "", "verify", "", "envelope", "encode", "decode", "encoded", "canonicalObject", "payload", "canonical", "value", "hmac", "secret", "sha256", "bytesToHex", "bytes", "hexToBytes", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SyncEnvelopeSecurity {
    public static final int PROTOCOL_VERSION = 2;
    public static final SyncEnvelopeSecurity INSTANCE = new SyncEnvelopeSecurity();
    private static final Set<String> allowedEntities = SetsKt.setOf((Object[]) new String[]{"PRODUCT", "CATEGORY", "UNIT", "CUSTOMER", "SUPPLIER", "CASH_ACCOUNT", "PURCHASE_INVOICE", "SALES_INVOICE", "CUSTOMER_PAYMENT", "SUPPLIER_PAYMENT", "SALES_RETURN", "PURCHASE_RETURN", "INVENTORY_ADJUSTMENT", "OPERATING_EXPENSE", "CASH_ACCOUNT_TRANSFER", "SERVICE_TRANSFER", "SERVICE_TRANSFER_RECEIVABLE", "SERVICE_TRANSFER_DEBT_PAYMENT", "DAILY_SUPPLIER_ROLLOVER", "EXCHANGE_RATE", "STORE_SETTINGS", "SYNC_TRANSPORT_PROBE"});

    private SyncEnvelopeSecurity() {
    }

    public final AuthenticatedSyncEnvelope create(String storeId, String operationId, long sequenceNumber, String originDeviceId, String actorUserId, String operationType, String entityType, String entityId, long baseRevision, String payloadJson, String createdAt, byte[] deviceSecret) {
        Intrinsics.checkNotNullParameter(storeId, "storeId");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        Intrinsics.checkNotNullParameter(originDeviceId, "originDeviceId");
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(operationType, "operationType");
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        Intrinsics.checkNotNullParameter(entityId, "entityId");
        Intrinsics.checkNotNullParameter(payloadJson, "payloadJson");
        Intrinsics.checkNotNullParameter(createdAt, "createdAt");
        Intrinsics.checkNotNullParameter(deviceSecret, "deviceSecret");
        if (deviceSecret.length < 32) {
            throw new IllegalArgumentException("Device secret must contain at least 256 bits".toString());
        }
        if (StringsKt.isBlank(storeId) || StringsKt.isBlank(operationId) || StringsKt.isBlank(originDeviceId)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (StringsKt.isBlank(actorUserId) || StringsKt.isBlank(entityId)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (sequenceNumber <= 0 || baseRevision < 0) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!SetsKt.setOf((Object[]) new String[]{"CREATE", "UPDATE", "DELETE"}).contains(operationType)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!allowedEntities.contains(entityType)) {
            throw new IllegalArgumentException("Entity type is not allowed for synchronization".toString());
        }
        if (!new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(createdAt)) {
            throw new IllegalArgumentException("Invalid ISO-8601 timestamp".toString());
        }
        String strCanonicalObject = canonicalObject(payloadJson);
        AuthenticatedSyncEnvelope authenticatedSyncEnvelope = new AuthenticatedSyncEnvelope(2, storeId, operationId, sequenceNumber, originDeviceId, actorUserId, operationType, entityType, entityId, baseRevision, strCanonicalObject, sha256(strCanonicalObject), createdAt, "");
        return authenticatedSyncEnvelope.copy((8191 & 1) != 0 ? authenticatedSyncEnvelope.protocolVersion : 0, (8191 & 2) != 0 ? authenticatedSyncEnvelope.storeId : null, (8191 & 4) != 0 ? authenticatedSyncEnvelope.operationId : null, (8191 & 8) != 0 ? authenticatedSyncEnvelope.sequenceNumber : 0L, (8191 & 16) != 0 ? authenticatedSyncEnvelope.originDeviceId : null, (8191 & 32) != 0 ? authenticatedSyncEnvelope.actorUserId : null, (8191 & 64) != 0 ? authenticatedSyncEnvelope.operationType : null, (8191 & 128) != 0 ? authenticatedSyncEnvelope.entityType : null, (8191 & 256) != 0 ? authenticatedSyncEnvelope.entityId : null, (8191 & 512) != 0 ? authenticatedSyncEnvelope.baseRevision : 0L, (8191 & 1024) != 0 ? authenticatedSyncEnvelope.payloadJson : null, (8191 & 2048) != 0 ? authenticatedSyncEnvelope.payloadSha256 : null, (8191 & 4096) != 0 ? authenticatedSyncEnvelope.createdAt : null, (8191 & 8192) != 0 ? authenticatedSyncEnvelope.authenticationTagHex : hmac(authenticatedSyncEnvelope, deviceSecret));
    }

    public final boolean verify(AuthenticatedSyncEnvelope envelope, byte[] deviceSecret) {
        Object objM381constructorimpl;
        Intrinsics.checkNotNullParameter(envelope, "envelope");
        Intrinsics.checkNotNullParameter(deviceSecret, "deviceSecret");
        if (deviceSecret.length < 32 || envelope.getProtocolVersion() != 2 || !SetsKt.setOf((Object[]) new String[]{"CREATE", "UPDATE", "DELETE"}).contains(envelope.getOperationType()) || !allowedEntities.contains(envelope.getEntityType()) || StringsKt.isBlank(envelope.getStoreId()) || StringsKt.isBlank(envelope.getOperationId()) || StringsKt.isBlank(envelope.getOriginDeviceId()) || StringsKt.isBlank(envelope.getActorUserId()) || StringsKt.isBlank(envelope.getEntityId()) || envelope.getSequenceNumber() <= 0 || envelope.getBaseRevision() < 0) {
            return false;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            SyncEnvelopeSecurity syncEnvelopeSecurity = this;
            String strCanonicalObject = canonicalObject(envelope.getPayloadJson());
            if (!Intrinsics.areEqual(strCanonicalObject, envelope.getPayloadJson()) || !MessageDigest.isEqual(hexToBytes(sha256(strCanonicalObject)), hexToBytes(envelope.getPayloadSha256()))) {
                return false;
            }
            objM381constructorimpl = Result.m381constructorimpl(Boolean.valueOf(MessageDigest.isEqual(hexToBytes(hmac(envelope.copy((8191 & 1) != 0 ? envelope.protocolVersion : 0, (8191 & 2) != 0 ? envelope.storeId : null, (8191 & 4) != 0 ? envelope.operationId : null, (8191 & 8) != 0 ? envelope.sequenceNumber : 0L, (8191 & 16) != 0 ? envelope.originDeviceId : null, (8191 & 32) != 0 ? envelope.actorUserId : null, (8191 & 64) != 0 ? envelope.operationType : null, (8191 & 128) != 0 ? envelope.entityType : null, (8191 & 256) != 0 ? envelope.entityId : null, (8191 & 512) != 0 ? envelope.baseRevision : 0L, (8191 & 1024) != 0 ? envelope.payloadJson : null, (8191 & 2048) != 0 ? envelope.payloadSha256 : null, (8191 & 4096) != 0 ? envelope.createdAt : null, (8191 & 8192) != 0 ? envelope.authenticationTagHex : ""), deviceSecret)), hexToBytes(envelope.getAuthenticationTagHex()))));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m387isFailureimpl(objM381constructorimpl)) {
            objM381constructorimpl = false;
        }
        return ((Boolean) objM381constructorimpl).booleanValue();
    }

    public final String encode(AuthenticatedSyncEnvelope envelope) throws JSONException {
        Intrinsics.checkNotNullParameter(envelope, "envelope");
        if (!Intrinsics.areEqual(envelope.getPayloadJson(), canonicalObject(envelope.getPayloadJson()))) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("protocol_version", envelope.getProtocolVersion());
        jSONObject.put("store_id", envelope.getStoreId());
        jSONObject.put("operation_id", envelope.getOperationId());
        jSONObject.put("sequence_number", envelope.getSequenceNumber());
        jSONObject.put("origin_device_id", envelope.getOriginDeviceId());
        jSONObject.put(SyncDeviceManagementActivity.EXTRA_ACTOR_USER_ID, envelope.getActorUserId());
        jSONObject.put("operation_type", envelope.getOperationType());
        jSONObject.put("entity_type", envelope.getEntityType());
        jSONObject.put("entity_id", envelope.getEntityId());
        jSONObject.put("base_revision", envelope.getBaseRevision());
        jSONObject.put("payload_json", envelope.getPayloadJson());
        jSONObject.put("payload_sha256", envelope.getPayloadSha256());
        jSONObject.put("created_at", envelope.getCreatedAt());
        jSONObject.put("authentication_tag", envelope.getAuthenticationTagHex());
        String string = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public final AuthenticatedSyncEnvelope decode(String encoded) {
        Intrinsics.checkNotNullParameter(encoded, "encoded");
        JSONObject jSONObject = new JSONObject(encoded);
        int i = jSONObject.getInt("protocol_version");
        String string = jSONObject.getString("store_id");
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String string2 = jSONObject.getString("operation_id");
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        long j = jSONObject.getLong("sequence_number");
        String string3 = jSONObject.getString("origin_device_id");
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        String string4 = jSONObject.getString(SyncDeviceManagementActivity.EXTRA_ACTOR_USER_ID);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        String string5 = jSONObject.getString("operation_type");
        Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
        String string6 = jSONObject.getString("entity_type");
        Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
        String string7 = jSONObject.getString("entity_id");
        Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
        long j2 = jSONObject.getLong("base_revision");
        String string8 = jSONObject.getString("payload_json");
        Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
        String string9 = jSONObject.getString("payload_sha256");
        Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
        String string10 = jSONObject.getString("created_at");
        Intrinsics.checkNotNullExpressionValue(string10, "getString(...)");
        String string11 = jSONObject.getString("authentication_tag");
        Intrinsics.checkNotNullExpressionValue(string11, "getString(...)");
        return new AuthenticatedSyncEnvelope(i, string, string2, j, string3, string4, string5, string6, string7, j2, string8, string9, string10, string11);
    }

    public final String canonicalObject(String payload) {
        Intrinsics.checkNotNullParameter(payload, "payload");
        return canonical(new JSONObject(payload));
    }

    private final String canonical(final Object value) {
        if (value == null || value == JSONObject.NULL) {
            return "null";
        }
        if (value instanceof JSONObject) {
            Iterator<String> itKeys = ((JSONObject) value).keys();
            Intrinsics.checkNotNullExpressionValue(itKeys, "keys(...)");
            return CollectionsKt.joinToString$default(CollectionsKt.sorted(SequencesKt.toList(SequencesKt.asSequence(itKeys))), ",", "{", "}", 0, null, new Function1() { // from class: com.hazem.dailystore.data.SyncEnvelopeSecurity$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SyncEnvelopeSecurity.canonical$lambda$5(value, (String) obj);
                }
            }, 24, null);
        }
        if (value instanceof JSONArray) {
            return CollectionsKt.joinToString$default(RangesKt.until(0, ((JSONArray) value).length()), ",", "[", "]", 0, null, new Function1() { // from class: com.hazem.dailystore.data.SyncEnvelopeSecurity$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SyncEnvelopeSecurity.canonical$lambda$6(value, ((Integer) obj).intValue());
                }
            }, 24, null);
        }
        if (value instanceof String) {
            String strQuote = JSONObject.quote((String) value);
            Intrinsics.checkNotNullExpressionValue(strQuote, "quote(...)");
            return strQuote;
        }
        if (value instanceof Boolean) {
            return String.valueOf(((Boolean) value).booleanValue());
        }
        if (value instanceof Number) {
            return ((Number) value).toString();
        }
        throw new IllegalStateException("Unsupported JSON value".toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence canonical$lambda$5(Object obj, String str) {
        return JSONObject.quote(str) + ":" + INSTANCE.canonical(((JSONObject) obj).get(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence canonical$lambda$6(Object obj, int i) {
        return INSTANCE.canonical(((JSONArray) obj).get(i));
    }

    private final String hmac(AuthenticatedSyncEnvelope envelope, byte[] secret) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("protocol_version", envelope.getProtocolVersion());
        jSONObject.put("store_id", envelope.getStoreId());
        jSONObject.put("operation_id", envelope.getOperationId());
        jSONObject.put("sequence_number", envelope.getSequenceNumber());
        jSONObject.put("origin_device_id", envelope.getOriginDeviceId());
        jSONObject.put(SyncDeviceManagementActivity.EXTRA_ACTOR_USER_ID, envelope.getActorUserId());
        jSONObject.put("operation_type", envelope.getOperationType());
        jSONObject.put("entity_type", envelope.getEntityType());
        jSONObject.put("entity_id", envelope.getEntityId());
        jSONObject.put("base_revision", envelope.getBaseRevision());
        jSONObject.put("payload_sha256", envelope.getPayloadSha256());
        jSONObject.put("created_at", envelope.getCreatedAt());
        String strJoinToString$default = CollectionsKt.joinToString$default(CollectionsKt.listOf((Object[]) new String[]{"THE_DAILY_SHOP_SYNC_ENVELOPE_HMAC_V2", envelope.getStoreId(), envelope.getOriginDeviceId()}), "\u0000", null, null, 0, null, null, 62, null);
        Charset UTF_8 = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
        byte[] bytes = strJoinToString$default.getBytes(UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrDerive$default = SyncKeyDerivation.derive$default(SyncKeyDerivation.INSTANCE, secret, bytes, 0, 4, null);
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(bArrDerive$default, "HmacSHA256"));
            String strCanonical = canonical(jSONObject);
            Charset UTF_9 = StandardCharsets.UTF_8;
            Intrinsics.checkNotNullExpressionValue(UTF_9, "UTF_8");
            byte[] bytes2 = strCanonical.getBytes(UTF_9);
            Intrinsics.checkNotNullExpressionValue(bytes2, "getBytes(...)");
            byte[] bArrDoFinal = mac.doFinal(bytes2);
            Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "doFinal(...)");
            return bytesToHex(bArrDoFinal);
        } finally {
            ArraysKt.fill$default(bArrDerive$default, (byte) 0, 0, 0, 6, (Object) null);
        }
    }

    private final String sha256(String value) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        Charset UTF_8 = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
        byte[] bytes = value.getBytes(UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrDigest = messageDigest.digest(bytes);
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
        return bytesToHex(bArrDigest);
    }

    private final String bytesToHex(byte[] bytes) {
        return ArraysKt.joinToString$default(bytes, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.hazem.dailystore.data.SyncEnvelopeSecurity$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SyncEnvelopeSecurity.bytesToHex$lambda$8(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence bytesToHex$lambda$8(byte b) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    private final byte[] hexToBytes(String value) {
        if (value.length() % 2 == 0) {
            if (new Regex("[0-9a-fA-F]*").matches(value)) {
                int length = value.length() / 2;
                byte[] bArr = new byte[length];
                for (int i = 0; i < length; i++) {
                    int i2 = i * 2;
                    String strSubstring = value.substring(i2, i2 + 2);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                    bArr[i] = (byte) Integer.parseInt(strSubstring, CharsKt.checkRadix(16));
                }
                return bArr;
            }
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }
}
