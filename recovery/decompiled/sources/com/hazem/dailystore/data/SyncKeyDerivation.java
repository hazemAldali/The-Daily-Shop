package com.hazem.dailystore.data;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* JADX INFO: compiled from: SyncKeyDerivation.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\b\u00c0\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/hazem/dailystore/data/SyncKeyDerivation;", "", "<init>", "()V", "salt", "", "derive", "inputKeyMaterial", "info", "outputLength", "", "HASH_LENGTH", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SyncKeyDerivation {
    private static final int HASH_LENGTH = 32;
    public static final SyncKeyDerivation INSTANCE = new SyncKeyDerivation();
    private static final byte[] salt;

    private SyncKeyDerivation() {
    }

    static {
        byte[] bytes = "THE_DAILY_SHOP_SYNC_HKDF_SALT_V1".getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        salt = bytes;
    }

    public static /* synthetic */ byte[] derive$default(SyncKeyDerivation syncKeyDerivation, byte[] bArr, byte[] bArr2, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 32;
        }
        return syncKeyDerivation.derive(bArr, bArr2, i);
    }

    public final byte[] derive(byte[] inputKeyMaterial, byte[] info, int outputLength) throws NoSuchAlgorithmException, InvalidKeyException {
        Intrinsics.checkNotNullParameter(inputKeyMaterial, "inputKeyMaterial");
        Intrinsics.checkNotNullParameter(info, "info");
        if (inputKeyMaterial.length < 32) {
            throw new IllegalArgumentException("Sync credential must contain at least 256 bits".toString());
        }
        int i = 1;
        if (1 > outputLength || outputLength >= 8161) {
            throw new IllegalArgumentException("Invalid HKDF output length".toString());
        }
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(salt, "HmacSHA256"));
        byte[] bArrDoFinal = mac.doFinal(inputKeyMaterial);
        try {
            byte[] bArr = new byte[outputLength];
            byte[] bArr2 = new byte[0];
            int i2 = 0;
            while (i2 < outputLength) {
                Mac mac2 = Mac.getInstance("HmacSHA256");
                mac2.init(new SecretKeySpec(bArrDoFinal, "HmacSHA256"));
                mac2.update(bArr2);
                mac2.update(info);
                mac2.update((byte) i);
                byte[] bArrDoFinal2 = mac2.doFinal();
                int iMin = Math.min(bArrDoFinal2.length, outputLength - i2);
                Intrinsics.checkNotNull(bArrDoFinal2);
                ArraysKt.copyInto(bArrDoFinal2, bArr, i2, 0, iMin);
                ArraysKt.fill$default(bArr2, (byte) 0, 0, 0, 6, (Object) null);
                i2 += iMin;
                i++;
                bArr2 = bArrDoFinal2;
            }
            ArraysKt.fill$default(bArr2, (byte) 0, 0, 0, 6, (Object) null);
            return bArr;
        } finally {
            Intrinsics.checkNotNull(bArrDoFinal);
            ArraysKt.fill$default(bArrDoFinal, (byte) 0, 0, 0, 6, (Object) null);
        }
    }
}
