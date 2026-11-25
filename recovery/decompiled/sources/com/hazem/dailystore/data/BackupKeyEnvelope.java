package com.hazem.dailystore.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;

/* JADX INFO: compiled from: BackupKeyEnvelope.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u0019\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\b\u00c0\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u001aJ\u0016\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u001aJ(\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\t2\u0006\u0010!\u001a\u00020\tH\u0002J \u0010\"\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\tH\u0002J \u0010#\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\tH\u0002J\u0010\u0010$\u001a\u00020%2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/hazem/dailystore/data/BackupKeyEnvelope;", "", "<init>", "()V", "random", "Ljava/security/SecureRandom;", "magic", "", "LEGACY_VERSION", "", "VERSION", "ITERATIONS", "LEGACY_MIN_ITERATIONS", "LEGACY_MAX_ITERATIONS", "MIN_ITERATIONS", "MAX_ITERATIONS", "SALT_BYTES", "IV_BYTES", "KEY_BYTES", "TAG_BITS", "MIN_PASSWORD_CHARS", "HEADER_BYTES", "ENVELOPE_BYTES", "wrap", "databaseKey", "password", "", "unwrap", "envelope", "header", "salt", "iv", "iterations", "version", "deriveSha256", "deriveLegacySha1", "validatePassword", "", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class BackupKeyEnvelope {
    private static final int ENVELOPE_BYTES = 92;
    private static final int HEADER_BYTES = 44;
    private static final int ITERATIONS = 600000;
    private static final int IV_BYTES = 12;
    private static final int KEY_BYTES = 32;
    private static final int LEGACY_MAX_ITERATIONS = 500000;
    private static final int LEGACY_MIN_ITERATIONS = 200000;
    private static final int LEGACY_VERSION = 1;
    private static final int MAX_ITERATIONS = 1000000;
    private static final int MIN_ITERATIONS = 600000;
    private static final int MIN_PASSWORD_CHARS = 10;
    private static final int SALT_BYTES = 16;
    private static final int TAG_BITS = 128;
    private static final int VERSION = 2;
    public static final BackupKeyEnvelope INSTANCE = new BackupKeyEnvelope();
    private static final SecureRandom random = new SecureRandom();
    private static final byte[] magic = {84, 68, 83, 75, 69, 89, 48, 49};

    private BackupKeyEnvelope() {
    }

    public final byte[] wrap(byte[] databaseKey, char[] password) throws IOException {
        Intrinsics.checkNotNullParameter(databaseKey, "databaseKey");
        Intrinsics.checkNotNullParameter(password, "password");
        if (databaseKey.length != 32) {
            throw new IllegalArgumentException("SQLCipher key must be 256 bits".toString());
        }
        validatePassword(password);
        byte[] bArr = new byte[16];
        SecureRandom secureRandom = random;
        secureRandom.nextBytes(bArr);
        byte[] bArr2 = new byte[12];
        secureRandom.nextBytes(bArr2);
        byte[] bArrHeader = header(bArr, bArr2, 600000, 2);
        byte[] bArrDeriveSha256 = deriveSha256(password, bArr, 600000);
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(1, new SecretKeySpec(bArrDeriveSha256, "AES"), new GCMParameterSpec(128, bArr2));
            cipher.updateAAD(bArrHeader);
            byte[] bArrDoFinal = cipher.doFinal(databaseKey);
            Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "doFinal(...)");
            return ArraysKt.plus(bArrHeader, bArrDoFinal);
        } finally {
            ArraysKt.fill$default(bArrDeriveSha256, (byte) 0, 0, 0, 6, (Object) null);
        }
    }

    public final byte[] unwrap(byte[] envelope, char[] password) throws IOException {
        byte[] bArrDeriveSha256;
        Intrinsics.checkNotNullParameter(envelope, "envelope");
        Intrinsics.checkNotNullParameter(password, "password");
        validatePassword(password);
        if (envelope.length != ENVELOPE_BYTES) {
            throw new IllegalArgumentException("Invalid database-key envelope size".toString());
        }
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(envelope));
        byte[] bArr = magic;
        byte[] bArr2 = new byte[bArr.length];
        dataInputStream.readFully(bArr2);
        if (!Arrays.equals(bArr2, bArr)) {
            throw new IllegalArgumentException("Unknown database-key envelope".toString());
        }
        int i = dataInputStream.readInt();
        if (i != 1 && i != 2) {
            throw new IllegalArgumentException("Unsupported database-key envelope version".toString());
        }
        int i2 = dataInputStream.readInt();
        if (i != 1 ? 600000 > i2 || i2 >= 1000001 : LEGACY_MIN_ITERATIONS > i2 || i2 >= 500001) {
            throw new IllegalArgumentException("Invalid key-derivation parameters".toString());
        }
        byte[] bArr3 = new byte[16];
        dataInputStream.readFully(bArr3);
        byte[] bArr4 = new byte[12];
        dataInputStream.readFully(bArr4);
        byte[] bArrCopyOfRange = ArraysKt.copyOfRange(envelope, 0, 44);
        byte[] bArrCopyOfRange2 = ArraysKt.copyOfRange(envelope, 44, envelope.length);
        if (i == 1) {
            bArrDeriveSha256 = deriveLegacySha1(password, bArr3, i2);
        } else {
            bArrDeriveSha256 = deriveSha256(password, bArr3, i2);
        }
        byte[] bArr5 = bArrDeriveSha256;
        try {
            try {
                Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
                cipher.init(2, new SecretKeySpec(bArr5, "AES"), new GCMParameterSpec(128, bArr4));
                cipher.updateAAD(bArrCopyOfRange);
                byte[] bArrDoFinal = cipher.doFinal(bArrCopyOfRange2);
                if (bArrDoFinal.length != 32) {
                    throw new IllegalArgumentException("Invalid recovered SQLCipher key size".toString());
                }
                ArraysKt.fill$default(bArr5, (byte) 0, 0, 0, 6, (Object) null);
                return bArrDoFinal;
            } catch (Exception e) {
                throw new IllegalArgumentException("Backup password is incorrect or key envelope is damaged", e);
            }
        } catch (Throwable th) {
            ArraysKt.fill$default(bArr5, (byte) 0, 0, 0, 6, (Object) null);
            throw th;
        }
    }

    private final byte[] header(byte[] salt, byte[] iv, int iterations, int version) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(44);
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            DataOutputStream dataOutputStream2 = dataOutputStream;
            dataOutputStream2.write(magic);
            dataOutputStream2.writeInt(version);
            dataOutputStream2.writeInt(iterations);
            dataOutputStream2.write(salt);
            dataOutputStream2.write(iv);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(dataOutputStream, null);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
            return byteArray;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(dataOutputStream, th);
                throw th2;
            }
        }
    }

    private final byte[] deriveSha256(char[] password, byte[] salt, int iterations) {
        ByteBuffer byteBufferEncode = StandardCharsets.UTF_8.encode(CharBuffer.wrap(password));
        byte[] bArr = new byte[byteBufferEncode.remaining()];
        byteBufferEncode.get(bArr);
        if (byteBufferEncode.hasArray()) {
            byte[] bArrArray = byteBufferEncode.array();
            Intrinsics.checkNotNullExpressionValue(bArrArray, "array(...)");
            ArraysKt.fill$default(bArrArray, (byte) 0, 0, 0, 6, (Object) null);
        }
        try {
            PKCS5S2ParametersGenerator pKCS5S2ParametersGenerator = new PKCS5S2ParametersGenerator(new SHA256Digest());
            pKCS5S2ParametersGenerator.init(bArr, salt, iterations);
            CipherParameters cipherParametersGenerateDerivedParameters = pKCS5S2ParametersGenerator.generateDerivedParameters(256);
            Intrinsics.checkNotNull(cipherParametersGenerateDerivedParameters, "null cannot be cast to non-null type org.bouncycastle.crypto.params.KeyParameter");
            byte[] key = ((KeyParameter) cipherParametersGenerateDerivedParameters).getKey();
            if (key.length != 32) {
                throw new IllegalArgumentException("Invalid PBKDF2-SHA256 output size".toString());
            }
            ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
            return key;
        } catch (Throwable th) {
            ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
            throw th;
        }
    }

    private final byte[] deriveLegacySha1(char[] password, byte[] salt, int iterations) {
        PBEKeySpec pBEKeySpec = new PBEKeySpec(password, salt, iterations, 256);
        try {
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(pBEKeySpec).getEncoded();
        } finally {
            pBEKeySpec.clearPassword();
        }
    }

    private final void validatePassword(char[] password) {
        if (password.length < 10) {
            throw new IllegalArgumentException("Backup password must contain at least 10 characters".toString());
        }
    }
}
