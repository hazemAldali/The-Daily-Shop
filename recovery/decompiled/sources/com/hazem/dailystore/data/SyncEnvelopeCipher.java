package com.hazem.dailystore.data;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SyncEnvelopeCipher.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u0019B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J \u0010\u0015\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u0010\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/hazem/dailystore/data/SyncEnvelopeCipher;", "", "<init>", "()V", "VERSION", "", "NONCE_BYTES", "", "TAG_BITS", "TAG_BYTES", "TRANSFORMATION", "", "random", "Ljava/security/SecureRandom;", "encrypt", "", "plaintext", "key", "Ljavax/crypto/SecretKey;", "context", "Lcom/hazem/dailystore/data/SyncEnvelopeCipher$PeerContext;", "decrypt", "frame", "keyInfo", "associatedData", "PeerContext", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SyncEnvelopeCipher {
    private static final int NONCE_BYTES = 12;
    private static final int TAG_BITS = 128;
    private static final int TAG_BYTES = 16;
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final byte VERSION = 2;
    public static final SyncEnvelopeCipher INSTANCE = new SyncEnvelopeCipher();
    private static final SecureRandom random = new SecureRandom();

    private SyncEnvelopeCipher() {
    }

    /* JADX INFO: compiled from: SyncEnvelopeCipher.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J1\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n\u00a8\u0006\u0019"}, d2 = {"Lcom/hazem/dailystore/data/SyncEnvelopeCipher$PeerContext;", "", "storeId", "", "senderDeviceId", "recipientDeviceId", "operationId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getStoreId", "()Ljava/lang/String;", "getSenderDeviceId", "getRecipientDeviceId", "getOperationId", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class PeerContext {
        private final String operationId;
        private final String recipientDeviceId;
        private final String senderDeviceId;
        private final String storeId;

        public static /* synthetic */ PeerContext copy$default(PeerContext peerContext, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = peerContext.storeId;
            }
            if ((i & 2) != 0) {
                str2 = peerContext.senderDeviceId;
            }
            if ((i & 4) != 0) {
                str3 = peerContext.recipientDeviceId;
            }
            if ((i & 8) != 0) {
                str4 = peerContext.operationId;
            }
            return peerContext.copy(str, str2, str3, str4);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getStoreId() {
            return this.storeId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSenderDeviceId() {
            return this.senderDeviceId;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getRecipientDeviceId() {
            return this.recipientDeviceId;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getOperationId() {
            return this.operationId;
        }

        public final PeerContext copy(String storeId, String senderDeviceId, String recipientDeviceId, String operationId) {
            Intrinsics.checkNotNullParameter(storeId, "storeId");
            Intrinsics.checkNotNullParameter(senderDeviceId, "senderDeviceId");
            Intrinsics.checkNotNullParameter(recipientDeviceId, "recipientDeviceId");
            Intrinsics.checkNotNullParameter(operationId, "operationId");
            return new PeerContext(storeId, senderDeviceId, recipientDeviceId, operationId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PeerContext)) {
                return false;
            }
            PeerContext peerContext = (PeerContext) other;
            return Intrinsics.areEqual(this.storeId, peerContext.storeId) && Intrinsics.areEqual(this.senderDeviceId, peerContext.senderDeviceId) && Intrinsics.areEqual(this.recipientDeviceId, peerContext.recipientDeviceId) && Intrinsics.areEqual(this.operationId, peerContext.operationId);
        }

        public int hashCode() {
            return (((((this.storeId.hashCode() * 31) + this.senderDeviceId.hashCode()) * 31) + this.recipientDeviceId.hashCode()) * 31) + this.operationId.hashCode();
        }

        public String toString() {
            return "PeerContext(storeId=" + this.storeId + ", senderDeviceId=" + this.senderDeviceId + ", recipientDeviceId=" + this.recipientDeviceId + ", operationId=" + this.operationId + ")";
        }

        public PeerContext(String storeId, String senderDeviceId, String recipientDeviceId, String operationId) {
            Intrinsics.checkNotNullParameter(storeId, "storeId");
            Intrinsics.checkNotNullParameter(senderDeviceId, "senderDeviceId");
            Intrinsics.checkNotNullParameter(recipientDeviceId, "recipientDeviceId");
            Intrinsics.checkNotNullParameter(operationId, "operationId");
            this.storeId = storeId;
            this.senderDeviceId = senderDeviceId;
            this.recipientDeviceId = recipientDeviceId;
            this.operationId = operationId;
            if (StringsKt.isBlank(storeId)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            if (StringsKt.isBlank(senderDeviceId)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            if (StringsKt.isBlank(recipientDeviceId)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            if (Intrinsics.areEqual(senderDeviceId, recipientDeviceId)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            if (StringsKt.isBlank(operationId)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
        }

        public final String getStoreId() {
            return this.storeId;
        }

        public final String getSenderDeviceId() {
            return this.senderDeviceId;
        }

        public final String getRecipientDeviceId() {
            return this.recipientDeviceId;
        }

        public final String getOperationId() {
            return this.operationId;
        }
    }

    public final byte[] encrypt(byte[] plaintext, SecretKey key, PeerContext context) {
        Intrinsics.checkNotNullParameter(plaintext, "plaintext");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(context, "context");
        if (plaintext.length == 0) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!StringsKt.equals(key.getAlgorithm(), "AES", true)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        byte[] encoded = key.getEncoded();
        if (encoded == null) {
            throw new IllegalArgumentException("Sync encryption key must be exportable pairing material".toString());
        }
        byte[] bArrDerive$default = SyncKeyDerivation.derive$default(SyncKeyDerivation.INSTANCE, encoded, keyInfo(context), 0, 4, null);
        ArraysKt.fill$default(encoded, (byte) 0, 0, 0, 6, (Object) null);
        try {
            byte[] bArr = new byte[12];
            random.nextBytes(bArr);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(1, new SecretKeySpec(bArrDerive$default, "AES"), new GCMParameterSpec(128, bArr));
            cipher.updateAAD(associatedData(context));
            byte[] bArrDoFinal = cipher.doFinal(plaintext);
            byte[] bArrArray = ByteBuffer.allocate(bArrDoFinal.length + 13).put((byte) 2).put(bArr).put(bArrDoFinal).array();
            Intrinsics.checkNotNullExpressionValue(bArrArray, "array(...)");
            return bArrArray;
        } finally {
            ArraysKt.fill$default(bArrDerive$default, (byte) 0, 0, 0, 6, (Object) null);
        }
    }

    public final byte[] decrypt(byte[] frame, SecretKey key, PeerContext context) {
        Object objM381constructorimpl;
        Intrinsics.checkNotNullParameter(frame, "frame");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(context, "context");
        if (frame.length < 29 || frame[0] != 2 || !StringsKt.equals(key.getAlgorithm(), "AES", true)) {
            return null;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            SyncEnvelopeCipher syncEnvelopeCipher = this;
            byte[] encoded = key.getEncoded();
            if (encoded == null) {
                throw new IllegalArgumentException("Required value was null.".toString());
            }
            byte[] bArrDerive$default = SyncKeyDerivation.derive$default(SyncKeyDerivation.INSTANCE, encoded, keyInfo(context), 0, 4, null);
            ArraysKt.fill$default(encoded, (byte) 0, 0, 0, 6, (Object) null);
            try {
                byte[] bArrCopyOfRange = ArraysKt.copyOfRange(frame, 1, 13);
                byte[] bArrCopyOfRange2 = ArraysKt.copyOfRange(frame, 13, frame.length);
                Cipher cipher = Cipher.getInstance(TRANSFORMATION);
                cipher.init(2, new SecretKeySpec(bArrDerive$default, "AES"), new GCMParameterSpec(128, bArrCopyOfRange));
                cipher.updateAAD(associatedData(context));
                byte[] bArrDoFinal = cipher.doFinal(bArrCopyOfRange2);
                ArraysKt.fill$default(bArrDerive$default, (byte) 0, 0, 0, 6, (Object) null);
                objM381constructorimpl = Result.m381constructorimpl(bArrDoFinal);
            } catch (Throwable th) {
                ArraysKt.fill$default(bArrDerive$default, (byte) 0, 0, 0, 6, (Object) null);
                throw th;
            }
        } catch (Throwable th2) {
            Result.Companion companion2 = Result.INSTANCE;
            objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th2));
        }
        return (byte[]) (Result.m387isFailureimpl(objM381constructorimpl) ? null : objM381constructorimpl);
    }

    private final byte[] keyInfo(PeerContext context) {
        byte[] bytes = CollectionsKt.joinToString$default(CollectionsKt.listOf((Object[]) new String[]{"THE_DAILY_SHOP_SYNC_AES_GCM_V2", context.getStoreId(), context.getSenderDeviceId(), context.getRecipientDeviceId()}), "|", null, null, 0, null, null, 62, null).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        return bytes;
    }

    private final byte[] associatedData(PeerContext context) throws NoSuchAlgorithmException {
        String strJoinToString$default = CollectionsKt.joinToString$default(CollectionsKt.listOf((Object[]) new String[]{"the-daily-shop-sync", "2", context.getStoreId(), context.getSenderDeviceId(), context.getRecipientDeviceId(), context.getOperationId()}), "\u0000", null, null, 0, null, null, 62, null);
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = strJoinToString$default.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrDigest = messageDigest.digest(bytes);
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
        return bArrDigest;
    }
}
