package com.hazem.dailystore.data;

import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;
import android.util.AtomicFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DatabaseKeyVault.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 12\u00020\u0001:\u000201B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0015\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\rH\u0000\u00a2\u0006\u0002\b\u0016J\u0016\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u000f\u0010\u001a\u001a\u0004\u0018\u00010\u0011H\u0000\u00a2\u0006\u0002\b\u001bJ\r\u0010\u001c\u001a\u00020\u0011H\u0000\u00a2\u0006\u0002\b\u001dJ\u001d\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u0011H\u0000\u00a2\u0006\u0002\b!J\u0015\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u0011H\u0000\u00a2\u0006\u0002\b$J\b\u0010%\u001a\u00020\u0013H\u0002J\b\u0010&\u001a\u00020\u0011H\u0002J \u0010'\u001a\u00020\u00182\u0006\u0010(\u001a\u00020\u00112\u0006\u0010)\u001a\u00020\u00112\u0006\u0010*\u001a\u00020\u0011H\u0002J\b\u0010+\u001a\u00020,H\u0002J\b\u0010-\u001a\u00020,H\u0002J\b\u0010.\u001a\u00020\u0018H\u0002J\b\u0010/\u001a\u00020\u0011H\u0002R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0018\u0010\t\u001a\n \n*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00062"}, d2 = {"Lcom/hazem/dailystore/data/DatabaseKeyVault;", "", "context", "Landroid/content/Context;", "metadataFileName", "", "keyAlias", "<init>", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V", "appContext", "kotlin.jvm.PlatformType", "Landroid/content/Context;", "metadataFile", "Ljava/io/File;", "atomicMetadata", "Landroid/util/AtomicFile;", "getOrCreateDatabaseKey", "", "databaseExists", "", "getOrCreateMigrationKey", "legacyDatabase", "getOrCreateMigrationKey$app_release", "importDatabaseKey", "", "databaseKey", "getImportedDatabaseKeyForRestoreRecovery", "getImportedDatabaseKeyForRestoreRecovery$app_release", "snapshotWrappedKeyMetadata", "snapshotWrappedKeyMetadata$app_release", "replaceDatabaseKeyForRestore", "replacementKey", "expectedCurrentKey", "replaceDatabaseKeyForRestore$app_release", "restoreWrappedKeyMetadata", "snapshot", "restoreWrappedKeyMetadata$app_release", "hasMetadata", "decryptExistingKey", "writeMetadata", "header", "iv", "encryptedKey", "loadExistingWrappingKey", "Ljavax/crypto/SecretKey;", "createWrappingKey", "deleteWrappingKeyIfPresent", "metadataHeader", "DatabaseKeyException", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class DatabaseKeyVault {
    private static final String ANDROID_KEYSTORE = "AndroidKeyStore";
    private static final int DATABASE_KEY_BYTES = 32;
    private static final String DEFAULT_KEY_ALIAS = "the_daily_shop_db_wrap_aes_v1";
    private static final String DEFAULT_METADATA_FILE = "the_daily_shop_db_key_v1.bin";
    private static final byte FORMAT_VERSION = 1;
    private static final int GCM_TAG_BITS = 128;
    private static final int GCM_TAG_BYTES = 16;
    private static final int HEADER_BYTES = 9;
    private static final int IV_BYTES = 12;
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private final Context appContext;
    private final AtomicFile atomicMetadata;
    private final String keyAlias;
    private final File metadataFile;
    private static final byte[] MAGIC = {84, 68, 83, 68, 66, 75, 89, 49};
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public DatabaseKeyVault(Context context, String metadataFileName, String keyAlias) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(metadataFileName, "metadataFileName");
        Intrinsics.checkNotNullParameter(keyAlias, "keyAlias");
        this.keyAlias = keyAlias;
        Context applicationContext = context.getApplicationContext();
        this.appContext = applicationContext;
        File file = new File(applicationContext.getNoBackupFilesDir(), metadataFileName);
        this.metadataFile = file;
        this.atomicMetadata = new AtomicFile(file);
        if (StringsKt.isBlank(metadataFileName) || !Intrinsics.areEqual(new File(metadataFileName).getName(), metadataFileName)) {
            throw new IllegalArgumentException("Invalid database-key metadata filename".toString());
        }
        if (StringsKt.isBlank(keyAlias)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public /* synthetic */ DatabaseKeyVault(Context context, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? DEFAULT_METADATA_FILE : str, (i & 4) != 0 ? DEFAULT_KEY_ALIAS : str2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final synchronized byte[] getOrCreateDatabaseKey(boolean databaseExists) {
        if (hasMetadata()) {
            return decryptExistingKey();
        }
        if (databaseExists) {
            throw new DatabaseKeyException("\u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0645\u0648\u062c\u0648\u062f\u0629 \u0644\u0643\u0646 \u0628\u064a\u0627\u0646\u0627\u062a \u0645\u0641\u062a\u0627\u062d \u0627\u0644\u062a\u0634\u0641\u064a\u0631 \u0645\u0641\u0642\u0648\u062f\u0629\u061b \u0623\u0648\u0642\u0641 \u0627\u0644\u0641\u062a\u062d \u0648\u0644\u0627 \u062a\u0646\u0634\u0626 \u0642\u0627\u0639\u062f\u0629 \u0628\u062f\u064a\u0644\u0629", null, 2, 0 == true ? 1 : 0);
        }
        deleteWrappingKeyIfPresent();
        byte[] bArr = new byte[32];
        SECURE_RANDOM.nextBytes(bArr);
        try {
            SecretKey secretKeyCreateWrappingKey = createWrappingKey();
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(1, secretKeyCreateWrappingKey);
            byte[] bArrMetadataHeader = metadataHeader();
            cipher.updateAAD(bArrMetadataHeader);
            byte[] bArrDoFinal = cipher.doFinal(bArr);
            byte[] iv = cipher.getIV();
            Intrinsics.checkNotNullExpressionValue(iv, "getIV(...)");
            Intrinsics.checkNotNull(bArrDoFinal);
            writeMetadata(bArrMetadataHeader, iv, bArrDoFinal);
            return bArr;
        } catch (Exception e) {
            ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
            throw new DatabaseKeyException("\u062a\u0639\u0630\u0631 \u0625\u0646\u0634\u0627\u0621 \u0645\u0641\u062a\u0627\u062d \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0628\u0623\u0645\u0627\u0646", e);
        }
    }

    public final synchronized byte[] getOrCreateMigrationKey$app_release(File legacyDatabase) {
        Intrinsics.checkNotNullParameter(legacyDatabase, "legacyDatabase");
        if (!legacyDatabase.isFile() || legacyDatabase.length() <= 0) {
            throw new IllegalArgumentException("\u0642\u0627\u0639\u062f\u0629 SQLite \u0627\u0644\u0642\u062f\u064a\u0645\u0629 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629 \u0623\u0648 \u0641\u0627\u0631\u063a\u0629\u061b \u0631\u064f\u0641\u0636 \u0625\u0646\u0634\u0627\u0621 \u0645\u0641\u062a\u0627\u062d \u062a\u0631\u062d\u064a\u0644".toString());
        }
        if (hasMetadata()) {
            return decryptExistingKey();
        }
        deleteWrappingKeyIfPresent();
        byte[] bArr = new byte[32];
        SECURE_RANDOM.nextBytes(bArr);
        try {
            SecretKey secretKeyCreateWrappingKey = createWrappingKey();
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(1, secretKeyCreateWrappingKey);
            byte[] bArrMetadataHeader = metadataHeader();
            cipher.updateAAD(bArrMetadataHeader);
            byte[] bArrDoFinal = cipher.doFinal(bArr);
            byte[] iv = cipher.getIV();
            Intrinsics.checkNotNullExpressionValue(iv, "getIV(...)");
            Intrinsics.checkNotNull(bArrDoFinal);
            writeMetadata(bArrMetadataHeader, iv, bArrDoFinal);
            ArraysKt.fill$default(bArrDoFinal, (byte) 0, 0, 0, 6, (Object) null);
            byte[] bArrDecryptExistingKey = decryptExistingKey();
            try {
                if (!MessageDigest.isEqual(bArrDecryptExistingKey, bArr)) {
                    throw new IllegalStateException("\u0641\u0634\u0644 \u0627\u0644\u062a\u062d\u0642\u0642 \u0645\u0646 \u0645\u0641\u062a\u0627\u062d \u062a\u0631\u062d\u064a\u0644 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a".toString());
                }
                ArraysKt.fill$default(bArrDecryptExistingKey, (byte) 0, 0, 0, 6, (Object) null);
                return bArr;
            } catch (Throwable th) {
                ArraysKt.fill$default(bArrDecryptExistingKey, (byte) 0, 0, 0, 6, (Object) null);
                throw th;
            }
        } catch (Exception e) {
            ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
            throw new DatabaseKeyException("\u062a\u0639\u0630\u0631 \u0625\u0646\u0634\u0627\u0621 \u0645\u0641\u062a\u0627\u062d \u0622\u0645\u0646 \u0644\u062a\u0631\u062d\u064a\u0644 \u0642\u0627\u0639\u062f\u0629 SQLite \u0627\u0644\u0642\u062f\u064a\u0645\u0629", e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final synchronized void importDatabaseKey(byte[] databaseKey, boolean databaseExists) {
        Intrinsics.checkNotNullParameter(databaseKey, "databaseKey");
        if (databaseKey.length != 32) {
            throw new IllegalArgumentException("SQLCipher key must be 256 bits".toString());
        }
        int i = 2;
        Throwable th = null;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        Object[] objArr3 = 0;
        if (databaseExists) {
            throw new DatabaseKeyException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0627\u0633\u062a\u064a\u0631\u0627\u062f \u0645\u0641\u062a\u0627\u062d \u0625\u0644\u0649 \u062c\u0647\u0627\u0632 \u064a\u062d\u062a\u0648\u064a \u0639\u0644\u0649 \u0642\u0627\u0639\u062f\u0629 \u0628\u064a\u0627\u0646\u0627\u062a \u0645\u0648\u062c\u0648\u062f\u0629", objArr2 == true ? 1 : 0, i, objArr == true ? 1 : 0);
        }
        if (hasMetadata()) {
            byte[] bArrDecryptExistingKey = decryptExistingKey();
            try {
                if (!MessageDigest.isEqual(bArrDecryptExistingKey, databaseKey)) {
                    throw new DatabaseKeyException("\u064a\u0648\u062c\u062f \u0645\u0641\u062a\u0627\u062d \u0642\u0627\u0639\u062f\u0629 \u0628\u064a\u0627\u0646\u0627\u062a \u0645\u062d\u0644\u064a \u0645\u062e\u062a\u0644\u0641\u061b \u0631\u064f\u0641\u0636 \u0627\u0633\u062a\u0628\u062f\u0627\u0644\u0647", th, i, objArr3 == true ? 1 : 0);
                }
                ArraysKt.fill$default(bArrDecryptExistingKey, (byte) 0, 0, 0, 6, (Object) null);
                return;
            } catch (Throwable th2) {
                ArraysKt.fill$default(bArrDecryptExistingKey, (byte) 0, 0, 0, 6, (Object) null);
                throw th2;
            }
        }
        deleteWrappingKeyIfPresent();
        try {
            SecretKey secretKeyCreateWrappingKey = createWrappingKey();
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(1, secretKeyCreateWrappingKey);
            byte[] bArrMetadataHeader = metadataHeader();
            cipher.updateAAD(bArrMetadataHeader);
            byte[] bArrDoFinal = cipher.doFinal(databaseKey);
            byte[] iv = cipher.getIV();
            Intrinsics.checkNotNullExpressionValue(iv, "getIV(...)");
            Intrinsics.checkNotNull(bArrDoFinal);
            writeMetadata(bArrMetadataHeader, iv, bArrDoFinal);
            return;
        } catch (Exception e) {
            throw new DatabaseKeyException("\u062a\u0639\u0630\u0631 \u0627\u0633\u062a\u064a\u0631\u0627\u062f \u0645\u0641\u062a\u0627\u062d \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0625\u0644\u0649 \u0647\u0630\u0627 \u0627\u0644\u062c\u0647\u0627\u0632", e);
        }
        throw th;
    }

    public final synchronized byte[] getImportedDatabaseKeyForRestoreRecovery$app_release() {
        if (!hasMetadata()) {
            return null;
        }
        return decryptExistingKey();
    }

    public final synchronized byte[] snapshotWrappedKeyMetadata$app_release() {
        byte[] bytes;
        if (!hasMetadata()) {
            throw new IllegalStateException("\u0644\u0627 \u062a\u0648\u062c\u062f \u0628\u064a\u0627\u0646\u0627\u062a \u0645\u0641\u062a\u0627\u062d \u0645\u062d\u0644\u064a \u0644\u062d\u0641\u0638\u0647\u0627".toString());
        }
        ArraysKt.fill$default(decryptExistingKey(), (byte) 0, 0, 0, 6, (Object) null);
        FileInputStream fileInputStreamOpenRead = this.atomicMetadata.openRead();
        try {
            FileInputStream fileInputStream = fileInputStreamOpenRead;
            Intrinsics.checkNotNull(fileInputStream);
            bytes = ByteStreamsKt.readBytes(fileInputStream);
            CloseableKt.closeFinally(fileInputStreamOpenRead, null);
            if (bytes.length != 69) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(fileInputStreamOpenRead, th);
                throw th2;
            }
        }
        return bytes;
    }

    public final synchronized void replaceDatabaseKeyForRestore$app_release(byte[] replacementKey, byte[] expectedCurrentKey) {
        Intrinsics.checkNotNullParameter(replacementKey, "replacementKey");
        Intrinsics.checkNotNullParameter(expectedCurrentKey, "expectedCurrentKey");
        if (replacementKey.length != 32 || expectedCurrentKey.length != 32) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        byte[] bArrDecryptExistingKey = decryptExistingKey();
        try {
            if (!MessageDigest.isEqual(bArrDecryptExistingKey, expectedCurrentKey)) {
                throw new IllegalStateException("\u062a\u063a\u064a\u0631 \u0645\u0641\u062a\u0627\u062d \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0645\u062d\u0644\u064a\u0629\u061b \u0623\u064f\u0648\u0642\u0641\u062a \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629".toString());
            }
            ArraysKt.fill$default(bArrDecryptExistingKey, (byte) 0, 0, 0, 6, (Object) null);
            SecretKey secretKeyLoadExistingWrappingKey = loadExistingWrappingKey();
            byte[] bArrMetadataHeader = metadataHeader();
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(1, secretKeyLoadExistingWrappingKey);
            cipher.updateAAD(bArrMetadataHeader);
            byte[] bArrDoFinal = cipher.doFinal(replacementKey);
            byte[] iv = cipher.getIV();
            Intrinsics.checkNotNullExpressionValue(iv, "getIV(...)");
            Intrinsics.checkNotNull(bArrDoFinal);
            writeMetadata(bArrMetadataHeader, iv, bArrDoFinal);
            ArraysKt.fill$default(bArrDoFinal, (byte) 0, 0, 0, 6, (Object) null);
            byte[] bArrDecryptExistingKey2 = decryptExistingKey();
            try {
                if (!MessageDigest.isEqual(bArrDecryptExistingKey2, replacementKey)) {
                    throw new IllegalStateException("\u0641\u0634\u0644 \u0627\u0644\u062a\u062d\u0642\u0642 \u0645\u0646 \u0645\u0641\u062a\u0627\u062d \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0628\u0639\u062f \u0627\u0644\u0627\u0633\u062a\u0628\u062f\u0627\u0644".toString());
                }
                ArraysKt.fill$default(bArrDecryptExistingKey2, (byte) 0, 0, 0, 6, (Object) null);
            } catch (Throwable th) {
                ArraysKt.fill$default(bArrDecryptExistingKey2, (byte) 0, 0, 0, 6, (Object) null);
                throw th;
            }
        } catch (Throwable th2) {
            ArraysKt.fill$default(bArrDecryptExistingKey, (byte) 0, 0, 0, 6, (Object) null);
            throw th2;
        }
        throw th;
    }

    public final synchronized void restoreWrappedKeyMetadata$app_release(byte[] snapshot) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        if (snapshot.length != 69) {
            throw new IllegalArgumentException("Invalid wrapped-key rollback snapshot".toString());
        }
        byte[] bArrCopyOfRange = ArraysKt.copyOfRange(snapshot, 0, 9);
        if (!Arrays.equals(bArrCopyOfRange, metadataHeader())) {
            throw new IllegalArgumentException("Unsupported wrapped-key rollback snapshot".toString());
        }
        byte[] bArrCopyOfRange2 = ArraysKt.copyOfRange(snapshot, 9, 21);
        byte[] bArrCopyOfRange3 = ArraysKt.copyOfRange(snapshot, 21, snapshot.length);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(2, loadExistingWrappingKey(), new GCMParameterSpec(128, bArrCopyOfRange2));
        cipher.updateAAD(bArrCopyOfRange);
        byte[] bArrDoFinal = cipher.doFinal(bArrCopyOfRange3);
        try {
            if (bArrDoFinal.length != 32) {
                throw new IllegalArgumentException("Invalid key in rollback snapshot".toString());
            }
            writeMetadata(bArrCopyOfRange, bArrCopyOfRange2, bArrCopyOfRange3);
            byte[] bArrDecryptExistingKey = decryptExistingKey();
            try {
                if (!MessageDigest.isEqual(bArrDecryptExistingKey, bArrDoFinal)) {
                    throw new IllegalStateException("Wrapped-key rollback verification failed".toString());
                }
                ArraysKt.fill$default(bArrDecryptExistingKey, (byte) 0, 0, 0, 6, (Object) null);
                Intrinsics.checkNotNull(bArrDoFinal);
                ArraysKt.fill$default(bArrDoFinal, (byte) 0, 0, 0, 6, (Object) null);
            } catch (Throwable th) {
                ArraysKt.fill$default(bArrDecryptExistingKey, (byte) 0, 0, 0, 6, (Object) null);
                throw th;
            }
        } catch (Throwable th2) {
            Intrinsics.checkNotNull(bArrDoFinal);
            ArraysKt.fill$default(bArrDoFinal, (byte) 0, 0, 0, 6, (Object) null);
            throw th2;
        }
    }

    private final boolean hasMetadata() {
        String path = this.metadataFile.getPath();
        StringBuilder sb = new StringBuilder();
        sb.append(path);
        sb.append(".bak");
        return this.metadataFile.exists() || new File(sb.toString()).exists();
    }

    private final byte[] decryptExistingKey() throws Exception {
        try {
            FileInputStream fileInputStreamOpenRead = this.atomicMetadata.openRead();
            try {
                FileInputStream fileInputStream = fileInputStreamOpenRead;
                Intrinsics.checkNotNull(fileInputStream);
                byte[] bytes = ByteStreamsKt.readBytes(fileInputStream);
                CloseableKt.closeFinally(fileInputStreamOpenRead, null);
                if (bytes.length != 69) {
                    throw new IllegalArgumentException("Invalid database-key metadata size".toString());
                }
                byte[] bArrCopyOfRange = ArraysKt.copyOfRange(bytes, 0, 9);
                if (!Arrays.equals(bArrCopyOfRange, metadataHeader())) {
                    throw new IllegalArgumentException("Unsupported database-key metadata format".toString());
                }
                byte[] bArrCopyOfRange2 = ArraysKt.copyOfRange(bytes, 9, 21);
                byte[] bArrCopyOfRange3 = ArraysKt.copyOfRange(bytes, 21, bytes.length);
                SecretKey secretKeyLoadExistingWrappingKey = loadExistingWrappingKey();
                Cipher cipher = Cipher.getInstance(TRANSFORMATION);
                cipher.init(2, secretKeyLoadExistingWrappingKey, new GCMParameterSpec(128, bArrCopyOfRange2));
                cipher.updateAAD(bArrCopyOfRange);
                byte[] bArrDoFinal = cipher.doFinal(bArrCopyOfRange3);
                if (bArrDoFinal.length == 32) {
                    Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "also(...)");
                    return bArrDoFinal;
                }
                throw new IllegalArgumentException("Invalid SQLCipher key length".toString());
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(fileInputStreamOpenRead, th);
                    throw th2;
                }
            }
        } catch (Exception e) {
            if (e instanceof DatabaseKeyException) {
                throw e;
            }
            throw new DatabaseKeyException("\u062a\u0639\u0630\u0631 \u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0645\u0641\u062a\u0627\u062d \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a\u061b \u0644\u0645 \u064a\u062a\u0645 \u0625\u0646\u0634\u0627\u0621 \u0645\u0641\u062a\u0627\u062d \u0628\u062f\u064a\u0644 \u0623\u0648 \u062a\u0639\u062f\u064a\u0644 \u0627\u0644\u0642\u0627\u0639\u062f\u0629", e);
        }
    }

    private final void writeMetadata(byte[] header, byte[] iv, byte[] encryptedKey) throws Exception {
        FileOutputStream fileOutputStreamStartWrite = null;
        try {
            fileOutputStreamStartWrite = this.atomicMetadata.startWrite();
            fileOutputStreamStartWrite.write(header);
            fileOutputStreamStartWrite.write(iv);
            fileOutputStreamStartWrite.write(encryptedKey);
            fileOutputStreamStartWrite.flush();
            fileOutputStreamStartWrite.getFD().sync();
            this.atomicMetadata.finishWrite(fileOutputStreamStartWrite);
        } catch (Exception e) {
            if (fileOutputStreamStartWrite != null) {
                this.atomicMetadata.failWrite(fileOutputStreamStartWrite);
            }
            throw e;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final SecretKey loadExistingWrappingKey() throws NoSuchAlgorithmException, UnrecoverableKeyException, IOException, KeyStoreException, CertificateException {
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEYSTORE);
        Throwable th = null;
        Object[] objArr = 0;
        keyStore.load(null);
        Key key = keyStore.getKey(this.keyAlias, null);
        SecretKey secretKey = key instanceof SecretKey ? (SecretKey) key : null;
        if (secretKey != null) {
            return secretKey;
        }
        throw new DatabaseKeyException("\u0645\u0641\u062a\u0627\u062d Android Keystore \u0627\u0644\u0645\u0637\u0644\u0648\u0628 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f", th, 2, objArr == true ? 1 : 0);
    }

    private final SecretKey createWrappingKey() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", ANDROID_KEYSTORE);
        keyGenerator.init(new KeyGenParameterSpec.Builder(this.keyAlias, 3).setKeySize(256).setBlockModes("GCM").setEncryptionPaddings("NoPadding").setRandomizedEncryptionRequired(true).build());
        SecretKey secretKeyGenerateKey = keyGenerator.generateKey();
        Intrinsics.checkNotNullExpressionValue(secretKeyGenerateKey, "generateKey(...)");
        return secretKeyGenerateKey;
    }

    private final void deleteWrappingKeyIfPresent() throws NoSuchAlgorithmException, IOException, KeyStoreException, CertificateException {
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEYSTORE);
        keyStore.load(null);
        if (keyStore.containsAlias(this.keyAlias)) {
            keyStore.deleteEntry(this.keyAlias);
        }
    }

    private final byte[] metadataHeader() {
        return ArraysKt.plus(MAGIC, new byte[]{1});
    }

    /* JADX INFO: compiled from: DatabaseKeyVault.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\u0018\u00002\u00060\u0001j\u0002`\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/hazem/dailystore/data/DatabaseKeyVault$DatabaseKeyException;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "message", "", "cause", "", "<init>", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class DatabaseKeyException extends IllegalStateException {
        public /* synthetic */ DatabaseKeyException(String str, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : th);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DatabaseKeyException(String message, Throwable th) {
            super(message, th);
            Intrinsics.checkNotNullParameter(message, "message");
        }
    }
}
