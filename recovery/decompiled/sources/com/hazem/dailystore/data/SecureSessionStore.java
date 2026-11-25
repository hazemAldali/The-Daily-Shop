package com.hazem.dailystore.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Base64;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SecureSessionStore.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010\u0014\u001a\u00020\u0015H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/hazem/dailystore/data/SecureSessionStore;", "", "<init>", "()V", "KEYSTORE_PROVIDER", "", "KEY_ALIAS", "PREFS", "IV_KEY", "VALUE_KEY", "TRANSFORMATION", "TAG_LENGTH_BITS", "", "save", "", "context", "Landroid/content/Context;", "userId", "load", "clear", "getOrCreateKey", "Ljavax/crypto/SecretKey;", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SecureSessionStore {
    public static final SecureSessionStore INSTANCE = new SecureSessionStore();
    private static final String IV_KEY = "session_iv";
    private static final String KEYSTORE_PROVIDER = "AndroidKeyStore";
    private static final String KEY_ALIAS = "the_daily_shop_session_aes_v1";
    private static final String PREFS = "the_daily_shop_secure_session";
    private static final int TAG_LENGTH_BITS = 128;
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final String VALUE_KEY = "session_value";

    private SecureSessionStore() {
    }

    public final synchronized void save(Context context, String userId) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str = userId;
        if (str != null && !StringsKt.isBlank(str)) {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(1, getOrCreateKey());
            byte[] bytes = userId.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            if (!context.getApplicationContext().getSharedPreferences(PREFS, 0).edit().putString(IV_KEY, Base64.encodeToString(cipher.getIV(), 2)).putString(VALUE_KEY, Base64.encodeToString(cipher.doFinal(bytes), 2)).commit()) {
                throw new IllegalStateException("Unable to persist encrypted session".toString());
            }
            return;
        }
        clear(context);
    }

    public final synchronized String load(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(PREFS, 0);
        String str = null;
        String string = sharedPreferences.getString(IV_KEY, null);
        if (string == null) {
            return null;
        }
        String string2 = sharedPreferences.getString(VALUE_KEY, null);
        if (string2 == null) {
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(2, getOrCreateKey(), new GCMParameterSpec(128, Base64.decode(string, 2)));
            byte[] bArrDoFinal = cipher.doFinal(Base64.decode(string2, 2));
            Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "doFinal(...)");
            String str2 = new String(bArrDoFinal, Charsets.UTF_8);
            if (!StringsKt.isBlank(str2)) {
                str = str2;
            }
        } catch (Exception unused) {
            clear(context);
        }
        return str;
    }

    public final synchronized void clear(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        context.getApplicationContext().getSharedPreferences(PREFS, 0).edit().clear().commit();
    }

    private final SecretKey getOrCreateKey() throws NoSuchAlgorithmException, UnrecoverableKeyException, IOException, KeyStoreException, CertificateException, NoSuchProviderException, InvalidAlgorithmParameterException {
        KeyStore keyStore = KeyStore.getInstance(KEYSTORE_PROVIDER);
        keyStore.load(null);
        Key key = keyStore.getKey(KEY_ALIAS, null);
        SecretKey secretKey = key instanceof SecretKey ? (SecretKey) key : null;
        if (secretKey != null) {
            return secretKey;
        }
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", KEYSTORE_PROVIDER);
        keyGenerator.init(new KeyGenParameterSpec.Builder(KEY_ALIAS, 3).setBlockModes("GCM").setEncryptionPaddings("NoPadding").setRandomizedEncryptionRequired(true).setKeySize(256).build());
        SecretKey secretKeyGenerateKey = keyGenerator.generateKey();
        Intrinsics.checkNotNullExpressionValue(secretKeyGenerateKey, "generateKey(...)");
        return secretKeyGenerateKey;
    }
}
