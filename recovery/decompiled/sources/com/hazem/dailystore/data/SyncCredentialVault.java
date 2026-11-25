package com.hazem.dailystore.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Base64;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SyncCredentialVault.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0006\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\b\u0010\u0017\u001a\u00020\u0006H\u0002J\u0010\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0018\u0010\t\u001a\n \n*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u000bR\u0018\u0010\f\u001a\n \n*\u0004\u0018\u00010\r0\rX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u000e\u00a8\u0006\u001a"}, d2 = {"Lcom/hazem/dailystore/data/SyncCredentialVault;", "", "context", "Landroid/content/Context;", "testKeyProvider", "Lkotlin/Function0;", "Ljavax/crypto/SecretKey;", "<init>", "(Landroid/content/Context;Lkotlin/jvm/functions/Function0;)V", "appContext", "kotlin.jvm.PlatformType", "Landroid/content/Context;", "preferences", "Landroid/content/SharedPreferences;", "Landroid/content/SharedPreferences;", "save", "", "deviceId", "", "credential", "", "load", "remove", "encryptionKey", "keyFor", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SyncCredentialVault {
    private static final String ANDROID_KEYSTORE = "AndroidKeyStore";
    private static final int GCM_IV_BYTES = 12;
    private static final int GCM_TAG_BITS = 128;
    private static final int GCM_TAG_BYTES = 16;
    private static final String KEY_ALIAS = "the_daily_shop_sync_credential_aes_v1";
    private static final String PREFERENCES = "the_daily_shop_sync_credentials";
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private final Context appContext;
    private final SharedPreferences preferences;
    private final Function0<SecretKey> testKeyProvider;

    /* JADX WARN: Multi-variable type inference failed */
    public SyncCredentialVault(Context context, Function0<? extends SecretKey> function0) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.testKeyProvider = function0;
        Context applicationContext = context.getApplicationContext();
        this.appContext = applicationContext;
        this.preferences = applicationContext.getSharedPreferences(PREFERENCES, 0);
    }

    public /* synthetic */ SyncCredentialVault(Context context, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : function0);
    }

    public final void save(String deviceId, byte[] credential) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(credential, "credential");
        if (StringsKt.isBlank(deviceId) || credential.length < 32) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(1, encryptionKey());
        byte[] bytes = deviceId.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        cipher.updateAAD(bytes);
        byte[] bArrDoFinal = cipher.doFinal(credential);
        byte[] iv = cipher.getIV();
        Intrinsics.checkNotNullExpressionValue(iv, "getIV(...)");
        Intrinsics.checkNotNull(bArrDoFinal);
        if (!this.preferences.edit().putString(keyFor(deviceId), Base64.encodeToString(ArraysKt.plus(iv, bArrDoFinal), 2)).commit()) {
            throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u062d\u0641\u0638 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u0628\u0634\u0643\u0644 \u0622\u0645\u0646".toString());
        }
    }

    public final byte[] load(String deviceId) {
        String string;
        Object objM381constructorimpl;
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        if (StringsKt.isBlank(deviceId) || (string = this.preferences.getString(keyFor(deviceId), null)) == null) {
            return null;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            SyncCredentialVault syncCredentialVault = this;
            byte[] bArrDecode = Base64.decode(string, 2);
            if (bArrDecode.length <= 28) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            Intrinsics.checkNotNull(bArrDecode);
            byte[] bArrCopyOfRange = ArraysKt.copyOfRange(bArrDecode, 0, 12);
            byte[] bArrCopyOfRange2 = ArraysKt.copyOfRange(bArrDecode, 12, bArrDecode.length);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(2, encryptionKey(), new GCMParameterSpec(128, bArrCopyOfRange));
            byte[] bytes = deviceId.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            cipher.updateAAD(bytes);
            byte[] bArrDoFinal = cipher.doFinal(bArrCopyOfRange2);
            if (bArrDoFinal.length >= 32) {
                objM381constructorimpl = Result.m381constructorimpl(bArrDoFinal);
                return (byte[]) (Result.m387isFailureimpl(objM381constructorimpl) ? null : objM381constructorimpl);
            }
            throw new IllegalArgumentException("Failed requirement.".toString());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
        }
    }

    public final void remove(String deviceId) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        if (!StringsKt.isBlank(deviceId) && !this.preferences.edit().remove(keyFor(deviceId)).commit()) {
            throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u062d\u0630\u0641 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u062c\u0647\u0627\u0632".toString());
        }
    }

    private final SecretKey encryptionKey() throws NoSuchAlgorithmException, UnrecoverableKeyException, IOException, KeyStoreException, CertificateException, NoSuchProviderException, InvalidAlgorithmParameterException {
        Function0<SecretKey> function0 = this.testKeyProvider;
        if (function0 != null) {
            return function0.invoke();
        }
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEYSTORE);
        keyStore.load(null);
        Key key = keyStore.getKey(KEY_ALIAS, null);
        SecretKey secretKey = key instanceof SecretKey ? (SecretKey) key : null;
        if (secretKey != null) {
            return secretKey;
        }
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", ANDROID_KEYSTORE);
        keyGenerator.init(new KeyGenParameterSpec.Builder(KEY_ALIAS, 3).setKeySize(256).setBlockModes("GCM").setEncryptionPaddings("NoPadding").setRandomizedEncryptionRequired(true).build());
        SecretKey secretKeyGenerateKey = keyGenerator.generateKey();
        Intrinsics.checkNotNullExpressionValue(secretKeyGenerateKey, "generateKey(...)");
        return secretKeyGenerateKey;
    }

    private final String keyFor(String deviceId) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = deviceId.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrDigest = messageDigest.digest(bytes);
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
        return "credential_" + ArraysKt.joinToString$default(bArrDigest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.hazem.dailystore.data.SyncCredentialVault$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SyncCredentialVault.keyFor$lambda$7(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence keyFor$lambda$7(byte b) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }
}
