package com.hazem.dailystore.data;

import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SyncDeviceAuthenticator.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001'B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\u000e\u001a\u00020\u000f2\u0018\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\u000bH\u0000\u00a2\u0006\u0002\b\u0011J\u0016\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0005J\u001e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0016J&\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u0016J\u0016\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010 \u001a\u00020\u0016J\u001e\u0010!\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00162\u0006\u0010 \u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u0016J\u000e\u0010\"\u001a\u00020\u00162\u0006\u0010#\u001a\u00020\rJ\u000e\u0010$\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0016J\u0010\u0010%\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0005H\u0002J\u0018\u0010&\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0016H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\"\u0010\n\u001a\u0016\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/hazem/dailystore/data/SyncDeviceAuthenticator;", "", "<init>", "()V", "ANDROID_KEYSTORE", "", "KEY_ALIAS_PREFIX", "CURVE", "SIGNATURE_ALGORITHM", "DOMAIN", "testKeyPairProvider", "Lkotlin/Function2;", "Landroid/content/Context;", "Ljava/security/KeyPair;", "installTestKeyPairProvider", "", "provider", "installTestKeyPairProvider$app_release", "getOrCreate", "context", "deviceId", "sign", "", "privateKey", "Ljava/security/PrivateKey;", "Lcom/hazem/dailystore/data/SyncDeviceAuthenticator$SigningContext;", "frame", "verify", "", "encodedPublicKey", "signatureBytes", "signBytes", "payload", "verifyBytes", "publicKeyBytes", "keyPair", "fingerprint", "aliasFor", "canonicalSignedBytes", "SigningContext", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SyncDeviceAuthenticator {
    private static final String ANDROID_KEYSTORE = "AndroidKeyStore";
    private static final String CURVE = "secp256r1";
    private static final String DOMAIN = "THE_DAILY_SHOP_SYNC_DEVICE_SIGNATURE_V1";
    public static final SyncDeviceAuthenticator INSTANCE = new SyncDeviceAuthenticator();
    private static final String KEY_ALIAS_PREFIX = "daily_shop_sync_identity_v1_";
    private static final String SIGNATURE_ALGORITHM = "SHA256withECDSA";
    private static volatile Function2<? super Context, ? super String, KeyPair> testKeyPairProvider;

    private SyncDeviceAuthenticator() {
    }

    /* JADX INFO: compiled from: SyncDeviceAuthenticator.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J1\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n\u00a8\u0006\u0019"}, d2 = {"Lcom/hazem/dailystore/data/SyncDeviceAuthenticator$SigningContext;", "", "storeId", "", "senderDeviceId", "recipientDeviceId", "operationId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getStoreId", "()Ljava/lang/String;", "getSenderDeviceId", "getRecipientDeviceId", "getOperationId", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class SigningContext {
        private final String operationId;
        private final String recipientDeviceId;
        private final String senderDeviceId;
        private final String storeId;

        public static /* synthetic */ SigningContext copy$default(SigningContext signingContext, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = signingContext.storeId;
            }
            if ((i & 2) != 0) {
                str2 = signingContext.senderDeviceId;
            }
            if ((i & 4) != 0) {
                str3 = signingContext.recipientDeviceId;
            }
            if ((i & 8) != 0) {
                str4 = signingContext.operationId;
            }
            return signingContext.copy(str, str2, str3, str4);
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

        public final SigningContext copy(String storeId, String senderDeviceId, String recipientDeviceId, String operationId) {
            Intrinsics.checkNotNullParameter(storeId, "storeId");
            Intrinsics.checkNotNullParameter(senderDeviceId, "senderDeviceId");
            Intrinsics.checkNotNullParameter(recipientDeviceId, "recipientDeviceId");
            Intrinsics.checkNotNullParameter(operationId, "operationId");
            return new SigningContext(storeId, senderDeviceId, recipientDeviceId, operationId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SigningContext)) {
                return false;
            }
            SigningContext signingContext = (SigningContext) other;
            return Intrinsics.areEqual(this.storeId, signingContext.storeId) && Intrinsics.areEqual(this.senderDeviceId, signingContext.senderDeviceId) && Intrinsics.areEqual(this.recipientDeviceId, signingContext.recipientDeviceId) && Intrinsics.areEqual(this.operationId, signingContext.operationId);
        }

        public int hashCode() {
            return (((((this.storeId.hashCode() * 31) + this.senderDeviceId.hashCode()) * 31) + this.recipientDeviceId.hashCode()) * 31) + this.operationId.hashCode();
        }

        public String toString() {
            return "SigningContext(storeId=" + this.storeId + ", senderDeviceId=" + this.senderDeviceId + ", recipientDeviceId=" + this.recipientDeviceId + ", operationId=" + this.operationId + ")";
        }

        public SigningContext(String storeId, String senderDeviceId, String recipientDeviceId, String operationId) {
            Intrinsics.checkNotNullParameter(storeId, "storeId");
            Intrinsics.checkNotNullParameter(senderDeviceId, "senderDeviceId");
            Intrinsics.checkNotNullParameter(recipientDeviceId, "recipientDeviceId");
            Intrinsics.checkNotNullParameter(operationId, "operationId");
            this.storeId = storeId;
            this.senderDeviceId = senderDeviceId;
            this.recipientDeviceId = recipientDeviceId;
            this.operationId = operationId;
            if (StringsKt.isBlank(storeId) || StringsKt.isBlank(senderDeviceId)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            if (StringsKt.isBlank(recipientDeviceId) || StringsKt.isBlank(operationId)) {
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

    public final void installTestKeyPairProvider$app_release(Function2<? super Context, ? super String, KeyPair> provider) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        throw new IllegalStateException("Test key providers are disabled in release builds".toString());
    }

    public final KeyPair getOrCreate(Context context, String deviceId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        if (StringsKt.isBlank(deviceId)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        String strAliasFor = aliasFor(deviceId);
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEYSTORE);
        keyStore.load(null);
        Certificate certificate = keyStore.getCertificate(strAliasFor);
        if (certificate != null) {
            Key key = keyStore.getKey(strAliasFor, null);
            PrivateKey privateKey = key instanceof PrivateKey ? (PrivateKey) key : null;
            if (privateKey == null) {
                throw new IllegalStateException("\u0645\u0641\u062a\u0627\u062d \u0647\u0648\u064a\u0629 \u0627\u0644\u062c\u0647\u0627\u0632 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d");
            }
            return new KeyPair(certificate.getPublicKey(), privateKey);
        }
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", ANDROID_KEYSTORE);
        keyPairGenerator.initialize(new KeyGenParameterSpec.Builder(strAliasFor, 12).setAlgorithmParameterSpec(new ECGenParameterSpec(CURVE)).setDigests("SHA-256").setUserAuthenticationRequired(false).build());
        KeyPair keyPairGenerateKeyPair = keyPairGenerator.generateKeyPair();
        Intrinsics.checkNotNullExpressionValue(keyPairGenerateKeyPair, "generateKeyPair(...)");
        return keyPairGenerateKeyPair;
    }

    public final byte[] sign(PrivateKey privateKey, SigningContext context, byte[] frame) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        Intrinsics.checkNotNullParameter(privateKey, "privateKey");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(frame, "frame");
        if (frame.length == 0) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(canonicalSignedBytes(context, frame));
        byte[] bArrSign = signature.sign();
        Intrinsics.checkNotNullExpressionValue(bArrSign, "sign(...)");
        return bArrSign;
    }

    public final boolean verify(byte[] encodedPublicKey, SigningContext context, byte[] frame, byte[] signatureBytes) {
        Object objM381constructorimpl;
        Intrinsics.checkNotNullParameter(encodedPublicKey, "encodedPublicKey");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(frame, "frame");
        Intrinsics.checkNotNullParameter(signatureBytes, "signatureBytes");
        if (encodedPublicKey.length == 0 || frame.length == 0 || signatureBytes.length == 0) {
            return false;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            SyncDeviceAuthenticator syncDeviceAuthenticator = this;
            PublicKey publicKeyGeneratePublic = KeyFactory.getInstance("EC").generatePublic(new X509EncodedKeySpec(encodedPublicKey));
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(publicKeyGeneratePublic);
            signature.update(canonicalSignedBytes(context, frame));
            objM381constructorimpl = Result.m381constructorimpl(Boolean.valueOf(signature.verify(signatureBytes)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m387isFailureimpl(objM381constructorimpl)) {
            objM381constructorimpl = false;
        }
        return ((Boolean) objM381constructorimpl).booleanValue();
    }

    public final byte[] signBytes(PrivateKey privateKey, byte[] payload) {
        Intrinsics.checkNotNullParameter(privateKey, "privateKey");
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (payload.length == 0) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(payload);
        byte[] bArrSign = signature.sign();
        Intrinsics.checkNotNullExpressionValue(bArrSign, "run(...)");
        return bArrSign;
    }

    public final boolean verifyBytes(byte[] encodedPublicKey, byte[] payload, byte[] signatureBytes) {
        Object objM381constructorimpl;
        Intrinsics.checkNotNullParameter(encodedPublicKey, "encodedPublicKey");
        Intrinsics.checkNotNullParameter(payload, "payload");
        Intrinsics.checkNotNullParameter(signatureBytes, "signatureBytes");
        if (encodedPublicKey.length == 0 || payload.length == 0 || signatureBytes.length == 0) {
            return false;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            SyncDeviceAuthenticator syncDeviceAuthenticator = this;
            PublicKey publicKeyGeneratePublic = KeyFactory.getInstance("EC").generatePublic(new X509EncodedKeySpec(encodedPublicKey));
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(publicKeyGeneratePublic);
            signature.update(payload);
            objM381constructorimpl = Result.m381constructorimpl(Boolean.valueOf(signature.verify(signatureBytes)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m387isFailureimpl(objM381constructorimpl)) {
            objM381constructorimpl = false;
        }
        return ((Boolean) objM381constructorimpl).booleanValue();
    }

    public final byte[] publicKeyBytes(KeyPair keyPair) {
        Intrinsics.checkNotNullParameter(keyPair, "keyPair");
        byte[] encoded = keyPair.getPublic().getEncoded();
        Intrinsics.checkNotNullExpressionValue(encoded, "getEncoded(...)");
        byte[] bArrCopyOf = Arrays.copyOf(encoded, encoded.length);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
        return bArrCopyOf;
    }

    public final String fingerprint(byte[] encodedPublicKey) {
        Intrinsics.checkNotNullParameter(encodedPublicKey, "encodedPublicKey");
        byte[] bArrDigest = MessageDigest.getInstance("SHA-256").digest(encodedPublicKey);
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
        return ArraysKt.joinToString$default(bArrDigest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.hazem.dailystore.data.SyncDeviceAuthenticator$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SyncDeviceAuthenticator.fingerprint$lambda$7(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence fingerprint$lambda$7(byte b) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    private final String aliasFor(String deviceId) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = deviceId.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrDigest = messageDigest.digest(bytes);
        Intrinsics.checkNotNull(bArrDigest);
        return KEY_ALIAS_PREFIX + CollectionsKt.joinToString$default(ArraysKt.take(bArrDigest, 16), "", null, null, 0, null, new Function1() { // from class: com.hazem.dailystore.data.SyncDeviceAuthenticator$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SyncDeviceAuthenticator.aliasFor$lambda$8(((Byte) obj).byteValue());
            }
        }, 30, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence aliasFor$lambda$8(byte b) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    private final byte[] canonicalSignedBytes(SigningContext context, byte[] frame) {
        List listListOf = CollectionsKt.listOf((Object[]) new String[]{DOMAIN, context.getStoreId(), context.getSenderDeviceId(), context.getRecipientDeviceId(), context.getOperationId()});
        Iterator it = listListOf.iterator();
        int length = 0;
        while (it.hasNext()) {
            byte[] bytes = ((String) it.next()).getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            length += bytes.length + 4;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(length + 4 + frame.length);
        Iterator it2 = listListOf.iterator();
        while (it2.hasNext()) {
            byte[] bytes2 = ((String) it2.next()).getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes2, "getBytes(...)");
            byteBufferAllocate.putInt(bytes2.length);
            byteBufferAllocate.put(bytes2);
        }
        byteBufferAllocate.putInt(frame.length);
        byteBufferAllocate.put(frame);
        byte[] bArrArray = byteBufferAllocate.array();
        Intrinsics.checkNotNullExpressionValue(bArrArray, "array(...)");
        return bArrArray;
    }
}
