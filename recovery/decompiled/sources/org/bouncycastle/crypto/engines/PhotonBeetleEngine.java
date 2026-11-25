package org.bouncycastle.crypto.engines;

import java.lang.reflect.Array;
import kotlin.UByte;
import org.bouncycastle.asn1.BERTags;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;

/* JADX INFO: loaded from: classes3.dex */
public class PhotonBeetleEngine extends AEADBufferBaseEngine {
    private byte[] K;
    private final int LAST_THREE_BITS_OFFSET;
    private byte[] N;
    private final int RATE_INBYTES_HALF;
    private final int STATE_INBYTES;
    private int aadLen;
    private boolean input_empty;
    private int messageLen;
    private byte[] state;
    private byte[][] state_2d;
    private final int D = 8;
    private final byte[][] RC = {new byte[]{1, 3, 7, 14, 13, 11, 6, 12, 9, 2, 5, 10}, new byte[]{0, 2, 6, 15, 12, 10, 7, 13, 8, 3, 4, 11}, new byte[]{2, 0, 4, 13, 14, 8, 5, 15, 10, 1, 6, 9}, new byte[]{6, 4, 0, 9, 10, 12, 1, 11, 14, 5, 2, 13}, new byte[]{14, 12, 8, 1, 2, 4, 9, 3, 6, 13, 10, 5}, new byte[]{15, 13, 9, 0, 3, 5, 8, 2, 7, 12, 11, 4}, new byte[]{13, 15, 11, 2, 1, 7, 10, 0, 5, 14, 9, 6}, new byte[]{9, 11, 15, 6, 5, 3, 14, 4, 1, 10, 13, 2}};
    private final byte[][] MixColMatrix = {new byte[]{2, 4, 2, 11, 2, 8, 5, 6}, new byte[]{12, 9, 8, 13, 7, 7, 5, 2}, new byte[]{4, 4, 13, 13, 9, 4, 13, 9}, new byte[]{1, 6, 5, 1, 12, 13, 15, 14}, new byte[]{15, 12, 9, 13, 14, 5, 14, 13}, new byte[]{9, 14, 5, 15, 4, 12, 9, 6}, new byte[]{12, 2, 2, 10, 3, 1, 1, 14}, new byte[]{15, 1, 13, 10, 5, 10, 2, 3}};
    private final byte[] sbox = {12, 5, 6, 11, 9, 0, 10, 13, 3, 14, 15, 8, 4, 7, 1, 2};

    public enum PhotonBeetleParameters {
        pb32,
        pb128
    }

    public PhotonBeetleEngine(PhotonBeetleParameters photonBeetleParameters) {
        int i;
        int i2;
        this.KEY_SIZE = 16;
        this.IV_SIZE = 16;
        this.MAC_SIZE = 16;
        int iOrdinal = photonBeetleParameters.ordinal();
        if (iOrdinal != 0) {
            i = iOrdinal != 1 ? 0 : 128;
            i2 = i;
        } else {
            i = 32;
            i2 = BERTags.FLAGS;
        }
        int i3 = (i + 7) >>> 3;
        this.BlockSize = i3;
        this.AADBufferSize = i3;
        this.RATE_INBYTES_HALF = this.BlockSize >>> 1;
        int i4 = i + i2;
        int i5 = (i4 + 7) >>> 3;
        this.STATE_INBYTES = i5;
        this.LAST_THREE_BITS_OFFSET = (i4 - ((i5 - 1) << 3)) - 3;
        this.initialised = false;
        this.algorithmName = "Photon-Beetle AEAD";
        this.m_aad = new byte[this.AADBufferSize];
    }

    private void PHOTON_Permutation() {
        for (int i = 0; i < 64; i++) {
            this.state_2d[i >>> 3][i & 7] = (byte) (((this.state[i >> 1] & UByte.MAX_VALUE) >>> ((i & 1) * 4)) & 15);
        }
        for (int i2 = 0; i2 < 12; i2++) {
            for (int i3 = 0; i3 < 8; i3++) {
                byte[] bArr = this.state_2d[i3];
                bArr[0] = (byte) (bArr[0] ^ this.RC[i3][i2]);
            }
            for (int i4 = 0; i4 < 8; i4++) {
                for (int i5 = 0; i5 < 8; i5++) {
                    byte[] bArr2 = this.state_2d[i4];
                    bArr2[i5] = this.sbox[bArr2[i5]];
                }
            }
            for (int i6 = 1; i6 < 8; i6++) {
                System.arraycopy(this.state_2d[i6], 0, this.state, 0, 8);
                int i7 = 8 - i6;
                System.arraycopy(this.state, i6, this.state_2d[i6], 0, i7);
                System.arraycopy(this.state, 0, this.state_2d[i6], i7, i6);
            }
            for (int i8 = 0; i8 < 8; i8++) {
                for (int i9 = 0; i9 < 8; i9++) {
                    int i10 = 0;
                    for (int i11 = 0; i11 < 8; i11++) {
                        byte b = this.MixColMatrix[i9][i11];
                        byte b2 = this.state_2d[i11][i8];
                        i10 = (((i10 ^ ((b2 & 1) * b)) ^ ((b2 & 2) * b)) ^ ((b2 & 4) * b)) ^ (b * (b2 & 8));
                    }
                    int i12 = i10 >>> 4;
                    int i13 = (i12 << 1) ^ ((i10 & 15) ^ i12);
                    int i14 = i13 >>> 4;
                    this.state[i9] = (byte) (((i13 & 15) ^ i14) ^ (i14 << 1));
                }
                for (int i15 = 0; i15 < 8; i15++) {
                    this.state_2d[i15][i8] = this.state[i15];
                }
            }
        }
        for (int i16 = 0; i16 < 64; i16 += 2) {
            byte[] bArr3 = this.state_2d[i16 >>> 3];
            this.state[i16 >>> 1] = (byte) (((bArr3[(i16 + 1) & 7] & 15) << 4) | (bArr3[i16 & 7] & 15));
        }
    }

    private void XOR(byte[] bArr, int i, int i2) {
        int i3 = 0;
        while (i3 < i2) {
            byte[] bArr2 = this.state;
            bArr2[i3] = (byte) (bArr[i] ^ bArr2[i3]);
            i3++;
            i++;
        }
    }

    private void rhoohr(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        int i4;
        int i5 = 0;
        byte[] bArr3 = this.state_2d[0];
        int iMin = Math.min(i3, this.RATE_INBYTES_HALF);
        int i6 = 0;
        while (true) {
            i4 = this.RATE_INBYTES_HALF;
            if (i6 >= i4 - 1) {
                break;
            }
            byte[] bArr4 = this.state;
            int i7 = i6 + 1;
            bArr3[i6] = (byte) (((bArr4[i7] & 1) << 7) | ((bArr4[i6] & UByte.MAX_VALUE) >>> 1));
            i6 = i7;
        }
        byte[] bArr5 = this.state;
        bArr3[i4 - 1] = (byte) (((bArr5[i6] & UByte.MAX_VALUE) >>> 1) | ((bArr5[0] & 1) << 7));
        while (i5 < iMin) {
            bArr[i5 + i] = (byte) (bArr2[i5 + i2] ^ this.state[this.RATE_INBYTES_HALF + i5]);
            i5++;
        }
        while (i5 < i3) {
            bArr[i5 + i] = (byte) (bArr2[i5 + i2] ^ bArr3[i5 - this.RATE_INBYTES_HALF]);
            i5++;
        }
        if (this.forEncryption) {
            XOR(bArr2, i2, i3);
        } else {
            XOR(bArr, i, i3);
        }
    }

    private byte select(boolean z, boolean z2, byte b, byte b2) {
        if (z && z2) {
            return (byte) 1;
        }
        if (z) {
            return (byte) 2;
        }
        return z2 ? b : b2;
    }

    @Override // org.bouncycastle.crypto.engines.AEADBufferBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ int doFinal(byte[] bArr, int i) throws InvalidCipherTextException, IllegalStateException {
        return super.doFinal(bArr, i);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ String getAlgorithmName() {
        return super.getAlgorithmName();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBufferBaseEngine
    public /* bridge */ /* synthetic */ int getBlockSize() {
        return super.getBlockSize();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    public /* bridge */ /* synthetic */ int getIVBytesSize() {
        return super.getIVBytesSize();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    public /* bridge */ /* synthetic */ int getKeyBytesSize() {
        return super.getKeyBytesSize();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ byte[] getMac() {
        return super.getMac();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBufferBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ int getOutputSize(int i) {
        return super.getOutputSize(i);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBufferBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ int getUpdateOutputSize(int i) {
        return super.getUpdateOutputSize(i);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void init(boolean z, CipherParameters cipherParameters) {
        super.init(z, cipherParameters);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void init(byte[] bArr, byte[] bArr2) throws IllegalArgumentException {
        this.K = bArr;
        this.N = bArr2;
        this.state = new byte[this.STATE_INBYTES];
        this.state_2d = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, 8, 8);
        this.mac = new byte[this.MAC_SIZE];
        this.initialised = true;
        this.m_buf = new byte[this.BlockSize + (this.forEncryption ? 0 : this.MAC_SIZE)];
        this.m_state = this.forEncryption ? AEADBufferBaseEngine.State.EncInit : AEADBufferBaseEngine.State.DecInit;
        reset(false);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBufferBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public void processAADByte(byte b) {
        this.aadLen++;
        super.processAADByte(b);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBufferBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public void processAADBytes(byte[] bArr, int i, int i2) {
        this.aadLen += i2;
        super.processAADBytes(bArr, i, i2);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBufferBaseEngine
    protected void processBuffer(byte[] bArr, int i, byte[] bArr2, int i2) {
        PHOTON_Permutation();
        rhoohr(bArr2, i2, bArr, i, this.BlockSize);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBufferBaseEngine
    protected void processBufferAAD(byte[] bArr, int i) {
        PHOTON_Permutation();
        XOR(bArr, i, this.BlockSize);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ int processByte(byte b, byte[] bArr, int i) throws DataLengthException {
        return super.processByte(b, bArr, i);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBufferBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        this.messageLen += i2;
        return super.processBytes(bArr, i, i2, bArr2, i3);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBufferBaseEngine
    protected void processFinalAAD() {
        if (this.aadFinished) {
            return;
        }
        if (this.aadLen != 0) {
            if (this.m_aadPos != 0) {
                PHOTON_Permutation();
                XOR(this.m_aad, 0, this.m_aadPos);
                if (this.m_aadPos < this.BlockSize) {
                    byte[] bArr = this.state;
                    int i = this.m_aadPos;
                    bArr[i] = (byte) (bArr[i] ^ 1);
                }
            }
            byte[] bArr2 = this.state;
            int i2 = this.STATE_INBYTES - 1;
            bArr2[i2] = (byte) (bArr2[i2] ^ (select(this.messageLen - (this.forEncryption ? 0 : this.MAC_SIZE) > 0, this.aadLen % this.BlockSize == 0, (byte) 3, (byte) 4) << this.LAST_THREE_BITS_OFFSET));
        }
        this.m_aadPos = 0;
        this.aadFinished = true;
    }

    @Override // org.bouncycastle.crypto.engines.AEADBufferBaseEngine
    protected void processFinalBlock(byte[] bArr, int i) {
        int i2 = this.messageLen - (this.forEncryption ? 0 : this.MAC_SIZE);
        int i3 = this.m_bufPos;
        int i4 = this.aadLen;
        if (i4 != 0 || i2 != 0) {
            this.input_empty = false;
        }
        byte bSelect = select(i4 != 0, i2 % this.BlockSize == 0, (byte) 5, (byte) 6);
        if (i2 != 0) {
            if (i3 != 0) {
                PHOTON_Permutation();
                rhoohr(bArr, i, this.m_buf, 0, i3);
                if (i3 < this.BlockSize) {
                    byte[] bArr2 = this.state;
                    bArr2[i3] = (byte) (bArr2[i3] ^ 1);
                }
            }
            byte[] bArr3 = this.state;
            int i5 = this.STATE_INBYTES - 1;
            bArr3[i5] = (byte) (bArr3[i5] ^ (bSelect << this.LAST_THREE_BITS_OFFSET));
        }
        if (this.input_empty) {
            byte[] bArr4 = this.state;
            int i6 = this.STATE_INBYTES - 1;
            bArr4[i6] = (byte) (bArr4[i6] ^ (1 << this.LAST_THREE_BITS_OFFSET));
        }
        PHOTON_Permutation();
        this.mac = new byte[this.MAC_SIZE];
        System.arraycopy(this.state, 0, this.mac, 0, this.MAC_SIZE);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void reset() {
        super.reset();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void reset(boolean z) {
        if (!this.initialised) {
            throw new IllegalArgumentException("Need call init function before encryption/decryption");
        }
        bufferReset();
        this.input_empty = true;
        this.aadLen = 0;
        this.aadFinished = false;
        this.messageLen = 0;
        byte[] bArr = this.K;
        System.arraycopy(bArr, 0, this.state, 0, bArr.length);
        byte[] bArr2 = this.N;
        System.arraycopy(bArr2, 0, this.state, this.K.length, bArr2.length);
        super.reset(z);
    }
}
