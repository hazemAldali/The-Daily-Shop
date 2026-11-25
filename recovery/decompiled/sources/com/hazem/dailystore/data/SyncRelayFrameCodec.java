package com.hazem.dailystore.data;

import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: SyncRelayWebSocketClient.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u00c0\u0002\u0018\u00002\u00020\u0001:\u0001\u0014B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0007J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0007H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/hazem/dailystore/data/SyncRelayFrameCodec;", "", "<init>", "()V", "MAX_FRAME_CHARS", "", "encodeRelayRequest", "", "storeId", "senderDeviceId", "recipientDeviceId", "frameId", "encryptedFrameJson", "decodeServerMessage", "Lcom/hazem/dailystore/data/SyncRelayFrameCodec$ServerMessage;", "encoded", "localDeviceId", "requireUuid", "", "value", "ServerMessage", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SyncRelayFrameCodec {
    public static final SyncRelayFrameCodec INSTANCE = new SyncRelayFrameCodec();
    private static final int MAX_FRAME_CHARS = 700000;

    private SyncRelayFrameCodec() {
    }

    /* JADX INFO: compiled from: SyncRelayWebSocketClient.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004\u00a2\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b\u00a8\u0006\f"}, d2 = {"Lcom/hazem/dailystore/data/SyncRelayFrameCodec$ServerMessage;", "", "<init>", "()V", "Ready", "Inbound", "Delivery", "Error", "Lcom/hazem/dailystore/data/SyncRelayFrameCodec$ServerMessage$Delivery;", "Lcom/hazem/dailystore/data/SyncRelayFrameCodec$ServerMessage$Error;", "Lcom/hazem/dailystore/data/SyncRelayFrameCodec$ServerMessage$Inbound;", "Lcom/hazem/dailystore/data/SyncRelayFrameCodec$ServerMessage$Ready;", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static abstract class ServerMessage {
        public /* synthetic */ ServerMessage(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: compiled from: SyncRelayWebSocketClient.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lcom/hazem/dailystore/data/SyncRelayFrameCodec$ServerMessage$Ready;", "Lcom/hazem/dailystore/data/SyncRelayFrameCodec$ServerMessage;", "<init>", "()V", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Ready extends ServerMessage {
            public static final Ready INSTANCE = new Ready();

            private Ready() {
                super(null);
            }
        }

        private ServerMessage() {
        }

        /* JADX INFO: compiled from: SyncRelayWebSocketClient.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t\u00a8\u0006\u0017"}, d2 = {"Lcom/hazem/dailystore/data/SyncRelayFrameCodec$ServerMessage$Inbound;", "Lcom/hazem/dailystore/data/SyncRelayFrameCodec$ServerMessage;", "fromDeviceId", "", "frameId", "encryptedFrameJson", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getFromDeviceId", "()Ljava/lang/String;", "getFrameId", "getEncryptedFrameJson", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final /* data */ class Inbound extends ServerMessage {
            private final String encryptedFrameJson;
            private final String frameId;
            private final String fromDeviceId;

            public static /* synthetic */ Inbound copy$default(Inbound inbound, String str, String str2, String str3, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = inbound.fromDeviceId;
                }
                if ((i & 2) != 0) {
                    str2 = inbound.frameId;
                }
                if ((i & 4) != 0) {
                    str3 = inbound.encryptedFrameJson;
                }
                return inbound.copy(str, str2, str3);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getFromDeviceId() {
                return this.fromDeviceId;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getFrameId() {
                return this.frameId;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final String getEncryptedFrameJson() {
                return this.encryptedFrameJson;
            }

            public final Inbound copy(String fromDeviceId, String frameId, String encryptedFrameJson) {
                Intrinsics.checkNotNullParameter(fromDeviceId, "fromDeviceId");
                Intrinsics.checkNotNullParameter(frameId, "frameId");
                Intrinsics.checkNotNullParameter(encryptedFrameJson, "encryptedFrameJson");
                return new Inbound(fromDeviceId, frameId, encryptedFrameJson);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Inbound)) {
                    return false;
                }
                Inbound inbound = (Inbound) other;
                return Intrinsics.areEqual(this.fromDeviceId, inbound.fromDeviceId) && Intrinsics.areEqual(this.frameId, inbound.frameId) && Intrinsics.areEqual(this.encryptedFrameJson, inbound.encryptedFrameJson);
            }

            public int hashCode() {
                return (((this.fromDeviceId.hashCode() * 31) + this.frameId.hashCode()) * 31) + this.encryptedFrameJson.hashCode();
            }

            public String toString() {
                return "Inbound(fromDeviceId=" + this.fromDeviceId + ", frameId=" + this.frameId + ", encryptedFrameJson=" + this.encryptedFrameJson + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Inbound(String fromDeviceId, String frameId, String encryptedFrameJson) {
                super(null);
                Intrinsics.checkNotNullParameter(fromDeviceId, "fromDeviceId");
                Intrinsics.checkNotNullParameter(frameId, "frameId");
                Intrinsics.checkNotNullParameter(encryptedFrameJson, "encryptedFrameJson");
                this.fromDeviceId = fromDeviceId;
                this.frameId = frameId;
                this.encryptedFrameJson = encryptedFrameJson;
            }

            public final String getEncryptedFrameJson() {
                return this.encryptedFrameJson;
            }

            public final String getFrameId() {
                return this.frameId;
            }

            public final String getFromDeviceId() {
                return this.fromDeviceId;
            }
        }

        /* JADX INFO: compiled from: SyncRelayWebSocketClient.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f\u00a8\u0006\u0018"}, d2 = {"Lcom/hazem/dailystore/data/SyncRelayFrameCodec$ServerMessage$Delivery;", "Lcom/hazem/dailystore/data/SyncRelayFrameCodec$ServerMessage;", "frameId", "", "forwarded", "", "retryable", "<init>", "(Ljava/lang/String;ZZ)V", "getFrameId", "()Ljava/lang/String;", "getForwarded", "()Z", "getRetryable", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final /* data */ class Delivery extends ServerMessage {
            private final boolean forwarded;
            private final String frameId;
            private final boolean retryable;

            public static /* synthetic */ Delivery copy$default(Delivery delivery, String str, boolean z, boolean z2, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = delivery.frameId;
                }
                if ((i & 2) != 0) {
                    z = delivery.forwarded;
                }
                if ((i & 4) != 0) {
                    z2 = delivery.retryable;
                }
                return delivery.copy(str, z, z2);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getFrameId() {
                return this.frameId;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final boolean getForwarded() {
                return this.forwarded;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final boolean getRetryable() {
                return this.retryable;
            }

            public final Delivery copy(String frameId, boolean forwarded, boolean retryable) {
                Intrinsics.checkNotNullParameter(frameId, "frameId");
                return new Delivery(frameId, forwarded, retryable);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Delivery)) {
                    return false;
                }
                Delivery delivery = (Delivery) other;
                return Intrinsics.areEqual(this.frameId, delivery.frameId) && this.forwarded == delivery.forwarded && this.retryable == delivery.retryable;
            }

            public int hashCode() {
                return (((this.frameId.hashCode() * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.forwarded)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.retryable);
            }

            public String toString() {
                return "Delivery(frameId=" + this.frameId + ", forwarded=" + this.forwarded + ", retryable=" + this.retryable + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Delivery(String frameId, boolean z, boolean z2) {
                super(null);
                Intrinsics.checkNotNullParameter(frameId, "frameId");
                this.frameId = frameId;
                this.forwarded = z;
                this.retryable = z2;
            }

            public final boolean getForwarded() {
                return this.forwarded;
            }

            public final String getFrameId() {
                return this.frameId;
            }

            public final boolean getRetryable() {
                return this.retryable;
            }
        }

        /* JADX INFO: compiled from: SyncRelayWebSocketClient.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0011"}, d2 = {"Lcom/hazem/dailystore/data/SyncRelayFrameCodec$ServerMessage$Error;", "Lcom/hazem/dailystore/data/SyncRelayFrameCodec$ServerMessage;", "code", "", "<init>", "(Ljava/lang/String;)V", "getCode", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final /* data */ class Error extends ServerMessage {
            private final String code;

            public static /* synthetic */ Error copy$default(Error error, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = error.code;
                }
                return error.copy(str);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getCode() {
                return this.code;
            }

            public final Error copy(String code) {
                Intrinsics.checkNotNullParameter(code, "code");
                return new Error(code);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Error) && Intrinsics.areEqual(this.code, ((Error) other).code);
            }

            public int hashCode() {
                return this.code.hashCode();
            }

            public String toString() {
                return "Error(code=" + this.code + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Error(String code) {
                super(null);
                Intrinsics.checkNotNullParameter(code, "code");
                this.code = code;
            }

            public final String getCode() {
                return this.code;
            }
        }
    }

    public final String encodeRelayRequest(String storeId, String senderDeviceId, String recipientDeviceId, String frameId, String encryptedFrameJson) {
        Intrinsics.checkNotNullParameter(storeId, "storeId");
        Intrinsics.checkNotNullParameter(senderDeviceId, "senderDeviceId");
        Intrinsics.checkNotNullParameter(recipientDeviceId, "recipientDeviceId");
        Intrinsics.checkNotNullParameter(frameId, "frameId");
        Intrinsics.checkNotNullParameter(encryptedFrameJson, "encryptedFrameJson");
        requireUuid(storeId);
        requireUuid(senderDeviceId);
        requireUuid(recipientDeviceId);
        if (Intrinsics.areEqual(senderDeviceId, recipientDeviceId)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (StringsKt.isBlank(frameId) || frameId.length() > 80) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        int length = encryptedFrameJson.length();
        if (2 > length || length >= 700001) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        JSONObject jSONObject = new JSONObject(encryptedFrameJson);
        if (Intrinsics.areEqual(jSONObject.optString("store_id"), storeId) && Intrinsics.areEqual(jSONObject.optString("sender_device_id"), senderDeviceId) && Intrinsics.areEqual(jSONObject.optString("recipient_device_id"), recipientDeviceId) && Intrinsics.areEqual(jSONObject.optString("operation_id"), frameId) && Intrinsics.areEqual(jSONObject.optString("signature_algorithm"), "ECDSA_P256_SHA256")) {
            String strOptString = jSONObject.optString("signature");
            Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
            if (!StringsKt.isBlank(strOptString)) {
                String strOptString2 = jSONObject.optString("ciphertext");
                Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
                if (!StringsKt.isBlank(strOptString2)) {
                    String string = new JSONObject().put("type", "relay").put("to_device_id", recipientDeviceId).put("frame_id", frameId).put("ciphertext", encryptedFrameJson).toString();
                    Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                    return string;
                }
            }
        }
        throw new IllegalArgumentException("\u0625\u0637\u0627\u0631 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u0627\u0644\u0645\u0634\u0641\u0631 \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u0645\u0633\u0627\u0631 \u0627\u0644\u0625\u0631\u0633\u0627\u0644 \u0623\u0648 \u064a\u0641\u062a\u0642\u062f \u0627\u0644\u062a\u0648\u0642\u064a\u0639".toString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x003a, code lost:
    
        if (r4.equals("forwarded") != false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0150, code lost:
    
        if (r4.equals("not_delivered") != false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0152, code lost:
    
        r12 = r0.getString("frame_id");
        kotlin.jvm.internal.Intrinsics.checkNotNull(r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0160, code lost:
    
        if (kotlin.text.StringsKt.isBlank(r12) != false) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0166, code lost:
    
        if (r12.length() > 80) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x017f, code lost:
    
        return new com.hazem.dailystore.data.SyncRelayFrameCodec.ServerMessage.Delivery(r12, kotlin.jvm.internal.Intrinsics.areEqual(r0.getString("type"), "forwarded"), r0.optBoolean("retryable", false));
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0189, code lost:
    
        throw new java.lang.IllegalArgumentException("Failed requirement.".toString());
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final ServerMessage decodeServerMessage(String encoded, String localDeviceId) throws JSONException {
        Intrinsics.checkNotNullParameter(encoded, "encoded");
        Intrinsics.checkNotNullParameter(localDeviceId, "localDeviceId");
        int length = encoded.length();
        if (2 > length || length >= 700001) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        JSONObject jSONObject = new JSONObject(encoded);
        String string = jSONObject.getString("type");
        if (string != null) {
            switch (string.hashCode()) {
                case -490481128:
                    break;
                case 96784904:
                    if (string.equals("error")) {
                        String strOptString = jSONObject.optString("code", "RELAY_ERROR");
                        Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
                        return new ServerMessage.Error(strOptString);
                    }
                    break;
                case 108386723:
                    if (string.equals("ready")) {
                        if (jSONObject.getInt("protocol") == 2 && Intrinsics.areEqual(jSONObject.getString("device_id"), localDeviceId)) {
                            return ServerMessage.Ready.INSTANCE;
                        }
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                    break;
                case 108397201:
                    if (string.equals("relay")) {
                        String string2 = jSONObject.getString("from_device_id");
                        String string3 = jSONObject.getString("frame_id");
                        String string4 = jSONObject.getString("ciphertext");
                        Intrinsics.checkNotNull(string2);
                        requireUuid(string2);
                        if (!Intrinsics.areEqual(string2, localDeviceId)) {
                            Intrinsics.checkNotNull(string3);
                            if (!StringsKt.isBlank(string3) && string3.length() <= 80) {
                                int length2 = string4.length();
                                if (2 > length2 || length2 >= 700001) {
                                    throw new IllegalArgumentException("Failed requirement.".toString());
                                }
                                JSONObject jSONObject2 = new JSONObject(string4);
                                if (Intrinsics.areEqual(jSONObject2.optString("sender_device_id"), string2) && Intrinsics.areEqual(jSONObject2.optString("recipient_device_id"), localDeviceId) && Intrinsics.areEqual(jSONObject2.optString("operation_id"), string3) && Intrinsics.areEqual(jSONObject2.optString("signature_algorithm"), "ECDSA_P256_SHA256")) {
                                    String strOptString2 = jSONObject2.optString("signature");
                                    Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
                                    if (!StringsKt.isBlank(strOptString2)) {
                                        String strOptString3 = jSONObject2.optString("ciphertext");
                                        Intrinsics.checkNotNullExpressionValue(strOptString3, "optString(...)");
                                        if (!StringsKt.isBlank(strOptString3)) {
                                            Intrinsics.checkNotNull(string3);
                                            Intrinsics.checkNotNull(string4);
                                            return new ServerMessage.Inbound(string2, string3, string4);
                                        }
                                    }
                                }
                                throw new IllegalArgumentException("\u0631\u0633\u0627\u0644\u0629 \u0627\u0644\u0648\u0633\u064a\u0637 \u0644\u0627 \u062a\u0637\u0627\u0628\u0642 \u0647\u0648\u064a\u0629 \u0627\u0644\u0645\u0631\u0633\u0644 \u0623\u0648 \u0627\u0644\u0645\u0633\u062a\u0644\u0645".toString());
                            }
                        }
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                    break;
                case 2097807908:
                    break;
            }
        }
        throw new IllegalArgumentException("\u0631\u0633\u0627\u0644\u0629 \u0648\u0633\u064a\u0637 \u063a\u064a\u0631 \u0645\u0639\u0631\u0648\u0641\u0629");
    }

    private final void requireUuid(String value) {
        Object objM381constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            SyncRelayFrameCodec syncRelayFrameCodec = this;
            objM381constructorimpl = Result.m381constructorimpl(UUID.fromString(value));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
        }
        if (!Result.m388isSuccessimpl(objM381constructorimpl)) {
            throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 UUID \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
        }
    }
}
